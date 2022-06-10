package portalmigration.version91.migrate.config.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.collections4.map.HashedMap;
import org.apache.commons.lang3.StringUtils;

import ch.ivyteam.ivy.application.ActivityState;
import ch.ivyteam.ivy.application.IApplication;
import ch.ivyteam.ivy.application.app.IApplicationRepository;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.IUser;
import ch.ivyteam.ivy.workflow.WorkflowNavigationUtil;
import ch.ivyteam.ivy.workflow.start.IWebStartable;
import portalmigration.enums.ProcessType;
import portalmigration.util.IvyExecutor;
import portalmigration.version91.bo.ExternalLink;
import portalmigration.version91.persistence.dao.ApplicationDao;
import portalmigration.version91.persistence.dao.ExternalLinkDao;
import portalmigration.version91.persistence.dao.GlobalSettingDao;
import portalmigration.version91.persistence.dao.UserProcessDao;
import portalmigration.version91.persistence.domain.Application;
import portalmigration.version91.persistence.domain.GlobalSetting;
import portalmigration.version91.persistence.domain.UserProcess;

public class ApplicationPropertyMigrationService {
  private static final String SLASH = "/";
  private static Map<Long, List<ExternalLink>> savedExternalLink = new HashedMap<>();
  public static void migrate() {
    IApplication systemApp = getSystemApp();
    if (systemApp == null) {
      Ivy.log().error("SYSTEM application not found");
      return;
    }
    ApplicationDao applicationDao = new ApplicationDao();
    applicationDao.setIvyApplication(systemApp);
    List<Application> configuredApps = applicationDao.findAll().stream().filter(app -> app.getServerId() != null).collect(Collectors.toList());

    List<IApplication> ivyApps = new ArrayList<>();
    if (configuredApps.isEmpty()) {
      ivyApps.add(IApplication.current());
    } else {
      for (Application item : configuredApps) {
        var foundApp = IApplicationRepository.instance().findByName(item.getName()).orElse(null);
        if (foundApp != null && foundApp.getActivityState() == ActivityState.ACTIVE) {
          ivyApps.add(foundApp);
        }
      }
    }

    migrateThirdPartyApplication(ivyApps);
    // Must migrate external link before user process
    migrateExternalLink(ivyApps);
    migrateUserProcess(ivyApps);
    migrateGlobalSetting(ivyApps);

  }

  private static void migrateThirdPartyApplication(List<IApplication> ivyApps) {
    List<Application> result = new ArrayList<>();
    IApplication systemApp = getSystemApp();
    ApplicationDao applicationDao = new ApplicationDao();
    applicationDao.setIvyApplication(systemApp);
    List<Application> findAll = applicationDao.findAll();
    List<Application> thirdPartyApps = findAll.stream().filter(app -> app.getServerId() == null).collect(Collectors.toList());

    if (!thirdPartyApps.isEmpty()) {
      for (IApplication app : ivyApps) {
        ApplicationDao currentApplicationDao = new ApplicationDao();
        currentApplicationDao.setIvyApplication(app);

        thirdPartyApps.forEach(thirdApp ->{
          Application item = new Application();
          item.setDisplayName(thirdApp.getDisplayName());
          item.setLink(thirdApp.getLink());
          item.setMenuIcon(thirdApp.getMenuIcon());
          item.setMenuOrdinal(thirdApp.getMenuOrdinal());
          item.setName(thirdApp.getName());
          result.add(item);
        });
        currentApplicationDao.saveAll(result);
      }
    }
  }

  @SuppressWarnings("deprecation")
  private static void migrateUserProcess(List<IApplication> ivyApps) {
    UserProcessDao systemAppUserProcessDao = new UserProcessDao();
    systemAppUserProcessDao.setIvyApplication(getSystemApp());
    List<UserProcess> allUserProcess = systemAppUserProcessDao.findAll();
    if (!allUserProcess.isEmpty()) {

      for (UserProcess userProcess : allUserProcess) {
          String username = "";
          UserProcessDao userProcessDao = new UserProcessDao();
          // find username
          if (userProcess.getUserId() != null) {
            for (IApplication app : ivyApps) {
              IUser user = findUserId(userProcess.getUserId(), app);
              if (user != null) {
                username = user.getName();
              }
            }
          } else {
            username = userProcess.getUserName();
          }


          for (IApplication app : ivyApps) {

            userProcessDao.setIvyApplication(app);
            UserProcess item = new UserProcess();

            item.setApplicationId(app.getId());
            item.setDefaultProcess(userProcess.isDefaultProcess());
            item.setDescription(userProcess.getDescription());
            item.setIcon(userProcess.getIcon());
            item.setIndex(userProcess.getIndex());
            item.setLink(StringUtils.isNotBlank(userProcess.getWorkflowId()) ? userProcess.getLink() : updateAppForLink(userProcess.getLink(), app.getName()));
            item.setNames(userProcess.getNames());
            item.setProcessName(userProcess.getProcessName());

            item.setExternalLink(userProcess.isExternalLink());
            item.setWorkflowId(userProcess.getWorkflowId());

            // Update workflow id for external link since id is changed
            if (item.isExternalLink()) {
              List<ExternalLink> list = savedExternalLink.get(app.getId());
              ExternalLink found = list.stream().filter(link -> link.getLink().equals(item.getLink()) && link.getName().equals(item.getProcessName())).findFirst().orElse(null);
              if (found != null) {
                item.setWorkflowId(found.getId().toString());
              }
            }

            Long findUserId = findUserId(username, app);
            item.setUserId(findUserId);


            if (StringUtils.isNotBlank(item.getWorkflowId())) {
              item.setProcessType(userProcess.isExternalLink()? ProcessType.EXTERNAL_LINK : ProcessType.EXPRESS_PROCESS);
              item.setProcessId(item.getWorkflowId());
            } else {
              IUser user = findUserId(item.getUserId(), app);
              if (user != null) {

                List<IWebStartable> startables = WorkflowNavigationUtil.getWorkflowContext(app).getStartables(user);
                startables.forEach(x -> {
                  if (x.getLink().getRelativeEncoded().equals(item.getLink())) {
                    item.setProcessId(x.getId());
                  }
                });
              }
              item.setProcessType(ProcessType.IVY_PROCESS);
            }

            if (StringUtils.isNotBlank(item.getProcessId())) {
              item.setLink("");
            }

            userProcessDao.save(item);
          }

      }
    }
  }

  private static IUser findUserId(Long userId, IApplication app) {
    return IvyExecutor.executeAsSystem(() ->{
      return app.getSecurityContext().users().find(userId);
    });
  }

  private static Long findUserId(String username, IApplication application) {
    return IvyExecutor.executeAsSystem(() ->{
      IUser findUser = application.getSecurityContext().users().find(username);
      return findUser != null ? findUser.getId() : -1;
    });
  }

  private static void migrateExternalLink(List<IApplication> ivyApps) {

    ExternalLinkDao systemExternalLinkDao = new ExternalLinkDao();
    systemExternalLinkDao.setIvyApplication(getSystemApp());
    List<ExternalLink> allExternalLink = systemExternalLinkDao.findAll();

    ExternalLinkDao externalLinkDao = new ExternalLinkDao();
    if (!allExternalLink.isEmpty()) {
      for (ExternalLink link : allExternalLink) {
        String username = "";
        if (link.getCreatorId() != null) {
          for (IApplication app : ivyApps) {
            IUser user = findUserId(link.getCreatorId(), app);//app.getSecurityContext().users().find(link.getCreatorId());
            if (user != null) {
              username = user.getName();
            }
          }
        } else {
          username = link.getCreator();
        }
        for (IApplication app : ivyApps) {
          externalLinkDao.setIvyApplication(app);

          ExternalLink item = new ExternalLink();
          item.setLink(link.getLink());
          item.setName(link.getName());
          item.setPublic(link.isPublic());

          Long findUserId = findUserId(username, app);
          item.setCreatorId(findUserId);
          ExternalLink save = externalLinkDao.save(item);
          List<ExternalLink> list = savedExternalLink.get(app.getId());
          if (list == null) {
            list = new ArrayList<ExternalLink>();
          }
          list.add(save);
          savedExternalLink.put(app.getId(), list);
        }
      }
    }
  }

  private static void migrateGlobalSetting(List<IApplication> ivyApps) {
    List<GlobalSetting> result = new ArrayList<>();
    GlobalSettingDao globalSettingDao = new GlobalSettingDao();
    globalSettingDao.setIvyApplication(getSystemApp());
    List<GlobalSetting> allGlobalSetting = globalSettingDao.findAll();
    if (!allGlobalSetting.isEmpty()) {
      for (IApplication app : ivyApps) {
          GlobalSettingDao currentAppGlobalSettingDao = new GlobalSettingDao();
          currentAppGlobalSettingDao.setIvyApplication(app);
          allGlobalSetting.forEach(setting -> {
            GlobalSetting item = new GlobalSetting();
            item.setKey(setting.getKey());
            item.setValue(setting.getValue());

            result.add(item);
          });
          currentAppGlobalSettingDao.saveAll(result);
        }
    }

  }

  private static IApplication getSystemApp() {
    return IvyExecutor.executeAsSystem(() -> IApplicationRepository.instance().system().orElse(null));
  }

  private static String updateAppForLink(String link, String appName) {
    if (link != null && link.length() > 1) {
      int indexOfSecondSlash = link.indexOf(SLASH, 1);
      if (indexOfSecondSlash != -1) {
        return SLASH + appName + link.substring(indexOfSecondSlash);
      }
    }
    return link;
  }

}
