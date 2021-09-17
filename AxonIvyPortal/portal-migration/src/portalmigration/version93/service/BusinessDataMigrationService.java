package portalmigration.version93.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;

import ch.ivyteam.ivy.application.IApplication;
import ch.ivyteam.ivy.business.data.store.BusinessDataInfo;
import ch.ivyteam.ivy.business.data.store.BusinessDataRepository;
import ch.ivyteam.ivy.business.data.store.search.Filter;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.IUser;
import ch.ivyteam.ivy.vars.Variables;
import portalmigration.dto.DisplayName;

public class BusinessDataMigrationService {

  private static final String EXPRESS_PROCESS_ID = "processID";

  public static void startMigrationData(IApplication app, List<String> errors) {
    Ivy.log().info("***Start migrating PortalBusinessData");
    Ivy.log().info("***Migrating Portal Announcement");
    migrateAnnouncement(app);

    Ivy.log().info("***Migrating Portal Express processes");
    migrateExpressProcesses(app);

    Ivy.log().info("***Migrating Portal Statistic Charts");
    migrateStatisticCharts(app);

    if (errors.isEmpty()) {
      Ivy.log().info("***End migrating Portal Data successfully");
      return;
    }
    Ivy.log().error("***End migrating Portal Data with {0} error(s)", errors.size());
  }

  private static void migrateStatisticCharts(IApplication app) {
    var removeIds = new ArrayList<String>();
    var newDefaultCharts = new ArrayList<portalmigration.version93.configuration.StatisticChart>();
    var newChartByUserMap = new HashMap<Long, List<portalmigration.version93.configuration.StatisticChart>>();

    for (var savedChart : findAllByAppID(ch.ivy.addon.portalkit.statistics.StatisticChart.class, app.getId())) {

      var isDefaultChart = BooleanUtils.toBoolean(savedChart.getDefaultChart());
      var newChart = new portalmigration.version93.configuration.StatisticChart();
      newChart.setFilter(savedChart.getFilter());
      newChart.setName(savedChart.getName());
      newChart.setNames(savedChart.getNames());
      newChart.setPosition(savedChart.getPosition());
      newChart.setType(savedChart.getType());

      if (isDefaultChart) {
        newChart.setIsPublic(true);
        if (!checkDuplicateDefaultChart(newDefaultCharts, newChart)) {
          newDefaultCharts.add(newChart);
        }
      } else {
        var userCharts = new ArrayList<portalmigration.version93.configuration.StatisticChart>();
        var userId = savedChart.getUserId();
        if (newChartByUserMap.containsKey(userId)) {
          userCharts.addAll(newChartByUserMap.get(userId));
        }
        userCharts.add(newChart);
        newChartByUserMap.put(userId, userCharts);
      }

      removeIds.add(savedChart.getId());
    }

    if (CollectionUtils.isNotEmpty(newDefaultCharts)) {
      createVariable(app, portalmigration.enums.PortalVariable.STATISTIC_CHART, newDefaultCharts);
    }

    if (!newChartByUserMap.isEmpty()) {
      newChartByUserMap.keySet().forEach(userId -> {
        var user = findUserById(app, userId);
        if (user != null) {
          user.setProperty(portalmigration.enums.PortalVariable.STATISTIC_CHART.key,
              toJsonValue(newChartByUserMap.get(userId)));
        }
      });
    }

    deleteBusinessData(removeIds, "Statistic Chart");
  }

  private static boolean checkDuplicateDefaultChart(List<portalmigration.version93.configuration.StatisticChart> newDefaultCharts,
      portalmigration.version93.configuration.StatisticChart newChart) {
    var sameChartsType = newDefaultCharts.stream()
              .filter(chart -> chart.getType() == newChart.getType())
              .filter(chart -> StringUtils.equalsIgnoreCase(chart.getName(), newChart.getName()))
              .collect(Collectors.toList());
    if (CollectionUtils.isEmpty(sameChartsType)) {
      return false;
    }

    for (var chart : sameChartsType) {
      if (CollectionUtils.isNotEmpty(chart.getNames()) && CollectionUtils.isNotEmpty(newChart.getNames())) {
        var chartNameMap = chart.getNames().stream().collect(toDisplayNameMap());
        var newChartNameMap = newChart.getNames().stream().collect(toDisplayNameMap());
        if (chartNameMap.equals(newChartNameMap)) {
          return true;
        }
      }
    }
    return false;
  }

  private static Collector<DisplayName, ?, Map<Locale, String>> toDisplayNameMap() {
    return Collectors.toMap(portalmigration.dto.DisplayName::getLocale, portalmigration.dto.DisplayName::getValue);
  }

  private static String toJsonValue(Object object) {
    return portalmigration.persistence.converter.BusinessEntityConverter.entityToJsonValue(object);
  }

  private static IUser findUserById(IApplication app, Long userId) {
    return app.getSecurityContext().users().find(userId);
  }

  private static void migrateExpressProcesses(IApplication app) {
    var removeIds = new ArrayList<String>();
    var newAxonExpressProcesses = new ArrayList<portalmigration.version93.bo.ExpressProcess>();

    for (var savedProcess : findAllByAppID(ch.ivy.addon.portalkit.bo.ExpressProcess.class, app.getId())) {

      var processId = savedProcess.getId();
      var newExpressProcess = new portalmigration.version93.bo.ExpressProcess();
      newExpressProcess.setIcon(savedProcess.getIcon());
      newExpressProcess.setProcessCoOwners(savedProcess.getProcessCoOwners());
      newExpressProcess.setProcessDescription(savedProcess.getProcessDescription());
      newExpressProcess.setProcessFolder(savedProcess.getProcessFolder());
      newExpressProcess.setProcessName(savedProcess.getProcessName());
      newExpressProcess.setProcessOwner(savedProcess.getProcessOwner());
      newExpressProcess.setProcessPermissions(savedProcess.getProcessPermissions());
      newExpressProcess.setProcessType(savedProcess.getProcessType());
      newExpressProcess.setReadyToExecute(savedProcess.isReadyToExecute());
      newExpressProcess.setUseDefaultUI(savedProcess.isUseDefaultUI());
      newExpressProcess.setTaskDefinitions(convertExpressTaskDefinition(removeIds, processId));

      newAxonExpressProcesses.add(newExpressProcess);
      removeIds.add(processId);
    }

    createVariable(app, portalmigration.enums.PortalVariable.EXPRESS_PROCESS, newAxonExpressProcesses);

    deleteBusinessData(removeIds, "Axon Express");
  }

  private static void createVariable(IApplication app, portalmigration.enums.PortalVariable varName, Object varObject) {
    Variables.of(app).set(varName.key, toJsonValue(varObject));
  }

  private static List<portalmigration.version93.bo.ExpressTaskDefinition> convertExpressTaskDefinition(
      ArrayList<String> removeIds, String processId) {
    var newTaskDefinitions = new ArrayList<portalmigration.version93.bo.ExpressTaskDefinition>();
    var queryTaskDef = repo().search(ch.ivy.addon.portalkit.bo.ExpressTaskDefinition.class)
        .textField(EXPRESS_PROCESS_ID).isEqualToIgnoringCase(processId);
    for (var savedTaskDefinition : searchAll(queryTaskDef)) {
      var newTaskDef = new portalmigration.version93.bo.ExpressTaskDefinition();
      newTaskDef.setDescription(savedTaskDefinition.getDescription());
      newTaskDef.setResponsibleDisplayName(savedTaskDefinition.getResponsibleDisplayName());
      newTaskDef.setResponsibles(savedTaskDefinition.getResponsibles());
      newTaskDef.setSubject(savedTaskDefinition.getSubject());
      newTaskDef.setTaskPosition(savedTaskDefinition.getTaskPosition());
      newTaskDef.setType(savedTaskDefinition.getType());
      newTaskDef.setUntilDays(savedTaskDefinition.getUntilDays());
      newTaskDef.setEmail(convertEmailTask(savedTaskDefinition.getEmail()));
      newTaskDef.setFormElements(convertExpressFormElement(removeIds, processId));

      newTaskDefinitions.add(newTaskDef);
      removeIds.add(savedTaskDefinition.getId());
    }

    return newTaskDefinitions;
  }

  private static portalmigration.version93.bo.ExpressUserEmail convertEmailTask(ch.ivy.addon.portalkit.bo.ExpressUserEmail savedEmail) {
    var newEmail = new portalmigration.version93.bo.ExpressUserEmail();
    if (savedEmail != null) {
      newEmail.setContent(savedEmail.getContent());
      newEmail.setRecipients(savedEmail.getRecipients());
      newEmail.setResponseTo(savedEmail.getResponseTo());
      newEmail.setSubject(savedEmail.getSubject());
      // mapping attachments
      var newAttachments = new ArrayList<portalmigration.version93.dto.ExpressAttachment>();
      var saveEmailAttachments = savedEmail.getAttachments();
      if (CollectionUtils.isNotEmpty(saveEmailAttachments)) {
        for (var savedAttach : saveEmailAttachments) {
          var newAttach = new portalmigration.version93.dto.ExpressAttachment();
          newAttach.setContent(savedAttach.getContent());
          newAttach.setContentType(savedAttach.getContentType());
          newAttach.setName(savedAttach.getName());
          newAttach.setPath(savedAttach.getPath());
          newAttach.setSize(savedAttach.getSize());
          newAttach.setStatus(savedAttach.getStatus());

          newAttachments.add(newAttach);
        }
      }
      newEmail.setAttachments(newAttachments);
    }
    return newEmail;
  }

  private static List<portalmigration.version93.bo.ExpressFormElement> convertExpressFormElement(
      ArrayList<String> removeIds, String processId) {
    var newFormElements = new ArrayList<portalmigration.version93.bo.ExpressFormElement>();
    var queryFormElement = repo().search(ch.ivy.addon.portalkit.bo.ExpressFormElement.class)
        .textField(EXPRESS_PROCESS_ID).isEqualToIgnoringCase(processId);

    for (var savedForm : searchAll(queryFormElement)) {

      var newForm = new portalmigration.version93.bo.ExpressFormElement();
      newForm.setElementID(savedForm.getElementID());
      newForm.setElementPosition(savedForm.getElementPosition());
      newForm.setElementType(savedForm.getElementType());
      newForm.setIndexInPanel(savedForm.getIndexInPanel());
      newForm.setIntSetting(savedForm.getIntSetting());
      newForm.setLabel(savedForm.getLabel());
      newForm.setName(savedForm.getName());
      newForm.setOptionStrs(savedForm.getOptionStrs());
      newForm.setRequired(savedForm.isRequired());

      newFormElements.add(newForm);
      removeIds.add(savedForm.getId());
    }
    return newFormElements;
  }

  private static void migrateAnnouncement(IApplication app) {
    var removeIds = new ArrayList<String>();
    var languageToAnnouncements = new HashMap<String, ch.ivy.addon.portalkit.bo.Announcement>();

    var status = findLatestObjectByAppID(ch.ivy.addon.portalkit.bo.AnnouncementStatus.class, app.getId());

    for (var announcement : findAllByAppID(ch.ivy.addon.portalkit.bo.Announcement.class, app.getId())) {
      languageToAnnouncements.put(StringUtils.lowerCase(announcement.getLanguage().toLowerCase()), announcement);
      removeIds.add(announcement.getId());
    }

    if (languageToAnnouncements.isEmpty()) {
      return;
    }

    var newLacalizationContents = new ArrayList<portalmigration.version93.configuration.LocalizationContent>();
    languageToAnnouncements.values().forEach(announcement -> {
      newLacalizationContents.add(new portalmigration.version93.configuration.LocalizationContent(announcement.getLanguage(),
          announcement.getValue()));
    });

    var newAnnouncement = new portalmigration.version93.configuration.Announcement();
    newAnnouncement.setContents(newLacalizationContents);
    if (status != null) {
      newAnnouncement.setEnabled(BooleanUtils.toBoolean(status.getEnabled()));
      removeIds.add(status.getId());
    }

    createVariable(app, portalmigration.enums.PortalVariable.ANNOUNCEMENT, newAnnouncement);

    deleteBusinessData(removeIds, "Announcement");
  }

  private static void deleteBusinessData(ArrayList<String> removeIds, String type) {
    removeIds.forEach(id -> {
      deleteById(id);
      Ivy.log().info("***Migrating Portal: removed {0} content with id {1} in BusinessData", type, id);
    });
  }

  private static <T> List<T> findAllByAppID(Class<T> classType, long appID) {
    return portalmigration.util.IvyExecutor.executeAsSystem(() -> {
      var query = repo().search(classType);
      var totalCount = query.limit(10).execute().totalCount();
      Ivy.log().info("***Total count of {0} in repo is {1}", classType, totalCount);

      var resultInfos = query.limit((int) totalCount).execute().getAllInfos();
      var resultIds = resultInfos.stream().filter(info -> info.getCreatedByAppId() == appID)
          .map(BusinessDataInfo::getId).collect(Collectors.toList());

      var result = new ArrayList<T>();
      resultIds.forEach(entityId -> {
        Ivy.log().info("***Find entity in repo by ID {0}", entityId);
        var entity = repo().find(entityId, classType);
        if (entity != null) {
          result.add(entity);
        }
      });
      Ivy.log().info("***Total count of {0} in return list", result.size());
      return result;
    });
  }

  private static <T> T findLatestObjectByAppID(Class<T> classType, long appID) {
    return portalmigration.util.IvyExecutor.executeAsSystem(() -> {
      var query = repo().search(classType);
      var totalCount = query.limit(10).execute().totalCount();
      Ivy.log().info("***Total count of {0} in repo is {1}", classType, totalCount);

      var resultInfos = query.limit((int) totalCount).execute().getAllInfos();
      var resultId = resultInfos.stream().filter(info -> info.getCreatedByAppId() == appID)
          .sorted(Comparator.comparingLong(BusinessDataInfo::getVersion)).map(BusinessDataInfo::getId)
          .sorted(Comparator.reverseOrder()).findFirst().orElse("");
      Ivy.log().info("***findLatestObjectByAppID: Found ID {0} of {1}", resultId, classType);
      return repo().find(resultId, classType);
    });
  }

  private static void deleteById(String id) {
    portalmigration.util.IvyExecutor.executeAsSystem(() -> {
      repo().deleteById(id);
      return Void.class;
    });
  }

  private static <T> List<T> searchAll(Filter<T> query) {
    var queryResult = query.execute();
    var totalCount = queryResult.totalCount();
    if (totalCount > 10) {
      queryResult = query.limit(Math.toIntExact(totalCount)).execute();
    }
    var result = queryResult.getAll();
    return CollectionUtils.isEmpty(result) ? new ArrayList<>() : result;
  }

  public static BusinessDataRepository repo() {
    return portalmigration.util.IvyExecutor.executeAsSystem(() -> {
      return Ivy.repo();
    });
  }
}
