package ch.ivy.addon.portalkit.util;

import static java.util.Objects.isNull;
import static org.apache.commons.lang3.StringUtils.EMPTY;

import java.util.List;
import java.util.Optional;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.EnumUtils;
import org.apache.commons.lang3.StringUtils;

import com.axonivy.portal.components.publicapi.ProcessStartAPI;
import com.axonivy.portal.components.service.impl.ProcessService;

import ch.ivy.addon.portalkit.dto.dashboard.CustomDashboardWidget;
import ch.ivy.addon.portalkit.dto.dashboard.DashboardWidget;
import ch.ivy.addon.portalkit.dto.dashboard.ProcessViewerDashboardWidget;
import ch.ivy.addon.portalkit.dto.dashboard.process.DashboardProcess;
import ch.ivy.addon.portalkit.enums.CustomWidgetParam;
import ch.ivy.addon.portalkit.enums.DashboardCustomWidgetType;
import ch.ivy.addon.portalkit.ivydata.dto.IvyProcessStartDTO;
import ch.ivyteam.ivy.security.IUser;
import ch.ivyteam.ivy.workflow.ICase;
import ch.ivyteam.ivy.workflow.ITask;
import ch.ivyteam.ivy.workflow.custom.field.ICustomField;
import ch.ivyteam.ivy.workflow.custom.field.ICustomFields;
import ch.ivyteam.ivy.workflow.start.IWebStartable;

public class CustomWidgetUtils {
  public static final String PROPERTY_KEY_PATTERN_DELIMITER = "\\.";
  public static final String TASK_PROPERTY_PREFIX = "task";
  public static final String CASE_PROPERTY_PREFIX = "case";
  public static final String CUSTOM_FIELD_PREFIX = "customFields";
  public static final String USER_PREFIX = "user";
  public static final String PROPERTY_PREFIX = "property";

  public static List<IWebStartable> allPortalProcesses;
  public static List<IWebStartable> allCustomDashboardProcesses;

  public static String getPropertyByKeyPattern(Long referenceId , String keyPattern) {
    String propertyValue = keyPattern;
    String[] keyParts = keyPattern.split(PROPERTY_KEY_PATTERN_DELIMITER);
    if (isValidKey(keyParts)) {
      switch (getPrefixKey(keyParts)) {
        case TASK_PROPERTY_PREFIX:
          propertyValue = getTaskPropertyKey(referenceId, keyParts);
          break;
        case CASE_PROPERTY_PREFIX:
          propertyValue = getCasePropertyKey(referenceId, keyParts);
          break;
        case USER_PREFIX:
          propertyValue = getUserPropertyKey(referenceId, keyParts);
          break;
        default:
          break;
      }
    }
    return propertyValue;
  }
  
  public static String getPropertyPrefix(String keyPattern) {
    String[] keyParts = keyPattern.split(PROPERTY_KEY_PATTERN_DELIMITER);
    return isValidKey(keyParts) ? getPrefixKey(keyParts) : EMPTY;
  }
  
  public static String getTaskPropertyByKeyPattern(ITask task, String keyPattern) {
    return getPropertyByKeyPattern(task.getId(), keyPattern);
  }

  public static String getUserPropertyByKeyPattern(IUser user, String keyPattern) {
    return getPropertyByKeyPattern(user.getId(), keyPattern);
  }

  private static String getPrefixKey(String[] keyParts) {
    return keyParts[0];
  }

  private static String getCasePropertyKey(Long referenceId, String[] keyParts) {
    String propertyValue = EMPTY;
    ICase caze = CaseUtils.findCase(referenceId);
    if (caze == null) {
      return propertyValue;
    }

    if(hasCustomFields(keyParts)) {
      switch (keyParts[1]) {
        case CUSTOM_FIELD_PREFIX:
          propertyValue = getCustomFieldByKey(caze.customFields(), keyParts[2]);
          break;
        default:
          break;
      }
    } else {
      propertyValue = getCasePropertyByKey(caze, keyParts[1]);
    }
    return propertyValue;
  }

  private static String getTaskPropertyKey(Long referenceId, String[] keyParts) {
    String propertyValue = EMPTY;
    ITask task =  TaskUtils.findTaskById(referenceId);
    if (task == null) {
      return propertyValue;
    }

    if(hasCustomFields(keyParts)) {
      switch (keyParts[1]) {
        case CUSTOM_FIELD_PREFIX:
          propertyValue = getCustomFieldByKey(task.customFields(), keyParts[2]);
          break;
        default:
          break;
      }
    } else {
      propertyValue = getTaskPropertyByKey(task, keyParts[1]);
    }
    return propertyValue;
  }

  private static String getUserPropertyKey(Long referenceId, String[] keyParts) {
    String propertyValue = EMPTY;
    IUser user =  UserUtils.findUserByUserId(referenceId);
    if (user == null) {
      return propertyValue;
    }

    boolean isUserProperty = keyParts.length == 3
        && keyParts[1].contentEquals(PROPERTY_PREFIX);
    if(isUserProperty) {
      switch (keyParts[1]) {
        case PROPERTY_PREFIX:
          propertyValue = user.getProperty(keyParts[2]);
          break;
        default:
          break;
      }
    } else {
      propertyValue = getUserPropertyByKey(user, keyParts[1]);
    }
    return propertyValue;
  }

  private static boolean hasCustomFields(String[] keyParts) {
    return keyParts.length >= 3;
  }

  private static boolean isValidKey(String[] keyParts) {
    return keyParts.length >= 2;
  }

  private static String getCasePropertyByKey(ICase caze, String key) {
    CustomWidgetParam foundKey = EnumUtils.getEnum(CustomWidgetParam.class, key.toUpperCase());
    switch (foundKey) {
      case ID:
        return String.valueOf(caze.getId());
      case CATEGORY:
        return caze.getCategory().getPath();
      default:
        return StringUtils.EMPTY;
    }
  }

  private static String getTaskPropertyByKey(ITask task, String key) {
    CustomWidgetParam foundKey = EnumUtils.getEnum(CustomWidgetParam.class, key.toUpperCase());
    switch(foundKey) {
      case ID:
          return String.valueOf(task.getId());
      case CATEGORY:
          return String.valueOf(task.getCategory().getPath());
      default:
          return StringUtils.EMPTY;
    }
  }

  private static String getUserPropertyByKey(IUser user, String key) {
    CustomWidgetParam foundKey = EnumUtils.getEnum(CustomWidgetParam.class, key.toUpperCase());
    switch(foundKey) {
      case USERNAME:
        return String.valueOf(user.getName());
      case EMAIL:
        return String.valueOf(user.getEMailAddress());
      default:
        return StringUtils.EMPTY;
    }
  }

  private static String getCustomFieldByKey(ICustomFields customfields, String key) {
    ICustomField<?> customField = customfields.all().stream()
            .filter(field -> field.name().equals(key))
            .findFirst().orElse(null);
    if (isNull(customField) || customField.get().isEmpty()) {
      return EMPTY;
    }
    return String.valueOf(customField.getOrNull());
  }

  public static void loadDataForCustomWidget(DashboardWidget widget) {
    var customWidget = (CustomDashboardWidget) widget;
    String processPath = customWidget.getData().getProcessPath();
    if (StringUtils.isNotBlank(processPath)) {
      customWidget.getData().setUrl(EMPTY);
      IWebStartable startable = findStartableOfCustomDashboardProcess(processPath);
      if (isNull(startable)) {
        customWidget.getData().setStartRequestPath(EMPTY);
        return;
      }
      if (isNull(customWidget.getData().getIvyProcessStartDTO())) {
        customWidget.getData().setIvyProcessStartDTO(new IvyProcessStartDTO());
      }
      customWidget.getData().getIvyProcessStartDTO().setStartableProcessStart(startable);
      customWidget.loadParameters();
      customWidget.getData().setStartRequestPath(startable.getLink().getRelative());
      customWidget.getData().setType(DashboardCustomWidgetType.PROCESS);
    } else {
      customWidget.getData().setProcessPath(EMPTY);
      customWidget.getData().setType(DashboardCustomWidgetType.EXTERNAL_URL);
    }
  }

  public static void loadDataForProcessViewerWidget(DashboardWidget widget) {
    var processViewerWidget = (ProcessViewerDashboardWidget) widget;
    String processPath = processViewerWidget.getProcessPath();
    if (StringUtils.isNotBlank(processPath)) {
      Optional.ofNullable(findWebStartableByProcessPath(processPath))
          .ifPresent(webStartable -> processViewerWidget.setProcess(new DashboardProcess(webStartable)));
    }
  }

  public static IWebStartable findWebStartableByProcessPath(String processPath) {
    // Find IWebStartable by ProcessID first
    // If not found, try to find by IWebStartable by friendly request path then find IWebStartable by link
    IWebStartable webStartable = getAllPortalProcesses().stream()
        .filter(proccess -> proccess.getId().equals(processPath))
        .findAny().orElse(null);
    if (isNull(webStartable)) {
      String processStartLink = ProcessStartAPI.findRelativeUrlByProcessStartFriendlyRequestPath(processPath);
      webStartable = getAllPortalProcesses().stream()
          .filter(proccess -> proccess.getLink().getRelative().equals(processStartLink))
          .findAny().orElse(null);
    }
    return webStartable;
  }

  public static IWebStartable findStartableOfCustomDashboardProcess(String processPath) {
    IWebStartable startable = getAllCustomDashboardProcesses().stream()
        .filter(proccess -> proccess.getId().endsWith(processPath))
        .findAny().orElse(null);
    if (isNull(startable)) {
      String processStartLink = ProcessStartAPI.findRelativeUrlByProcessStartFriendlyRequestPath(processPath);
      startable = getAllCustomDashboardProcesses().stream()
          .filter(proccess -> proccess.getLink().getRelative().equals(processStartLink))
          .findAny().orElse(null);
    }
    return startable;
  }

  private static List<IWebStartable> getAllPortalProcesses() {
    if (CollectionUtils.isEmpty(allPortalProcesses)) {
      allPortalProcesses = ProcessService.getInstance().findProcesses().getProcesses();
    }
    return allPortalProcesses;
  }

  public static List<IWebStartable> getAllCustomDashboardProcesses() {
    if (CollectionUtils.isEmpty(allCustomDashboardProcesses)) {
      allCustomDashboardProcesses = ProcessService.getInstance().findCustomDashboardProcesses();
    }
    return allCustomDashboardProcesses;
  }
}
