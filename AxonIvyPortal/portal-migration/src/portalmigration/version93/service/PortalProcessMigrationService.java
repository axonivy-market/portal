package portalmigration.version93.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import ch.ivyteam.ivy.application.IApplication;
import ch.ivyteam.ivy.security.IUser;
import ch.ivyteam.ivy.security.query.UserQuery;
import ch.ivyteam.ivy.security.query.UserQuery.FilterLink;
import ch.ivyteam.ivy.vars.Variables;
import ch.ivyteam.ivy.workflow.WorkflowNavigationUtil;
import ch.ivyteam.ivy.workflow.start.IWebStartable;
import portalmigration.enums.PortalVariable;
import portalmigration.enums.ProcessType;
import portalmigration.persistence.converter.BusinessEntityConverter;
import portalmigration.service.PortalMigrationService;
import portalmigration.version93.configuration.ExternalLink;
import portalmigration.version93.configuration.UserProcess;

public class PortalProcessMigrationService extends PortalMigrationService {

  private static int pageSize = 1000;

  public static List<String> startMigration(IApplication app) {
    List<String> error = new ArrayList<>();
    
    migratePublicExternalLink(app);
    
    migrateUserProcess(app, error);
    
    migrateUserExternalLink(app);
    return error;
  }

  private static void migratePublicExternalLink(IApplication app) {
    var externalLinkJSON = Variables.of(app).get(PortalVariable.EXTERNAL_LINK.key);
    List<ExternalLink> externalLinks = BusinessEntityConverter.jsonValueToEntities(externalLinkJSON, ExternalLink.class);
    if (CollectionUtils.isEmpty(externalLinks)) {
      return;
    }
    
    for (ExternalLink externalLink : externalLinks) {
      externalLink.setIsPublic(true);
      IUser user = findUserInAppByName(app, externalLink.getCreator());
      if (user != null) {
        externalLink.setCreatorId(user.getId());
      }
    }
    
    Variables.of(app).set(PortalVariable.EXTERNAL_LINK.key, BusinessEntityConverter.entityToJsonValue(externalLinks));
  }

  private static void migrateUserProcess(IApplication application, List<String> error) {
    int index = 0;
    long totalCount = countActiveUserByApp(application.getId());

    do {
      List<IUser> users = queryActiveUserByApp(index, pageSize, application.getId());

      updateProcessProperties(users, application, error);

      index += pageSize;

    } while (index == totalCount);
  }
  
  private static void migrateUserExternalLink(IApplication application) {
    int index = 0;
    long totalCount = countActiveUserByApp(application.getId());

    do {
      List<IUser> users = queryActiveUserByApp(index, pageSize, application.getId());

      updateUserExternalProperties(users);

      index += pageSize;

    } while (index == totalCount);
  }

  private static void updateUserExternalProperties(List<IUser> users) {
    for (IUser user : users) {
      var externalLinkJSON = user.getProperty(PortalVariable.EXTERNAL_LINK.key);
      List<ExternalLink> externalLinks = BusinessEntityConverter.jsonValueToEntities(externalLinkJSON, ExternalLink.class);
      for (ExternalLink externalLink : externalLinks) {
        externalLink.setIsPublic(false);
        if (externalLink.getCreatorId() == null) {
          externalLink.setCreatorId(user.getId());
        }
        externalLink.setCreator(null);
      }

      user.setProperty(PortalVariable.EXTERNAL_LINK.key, BusinessEntityConverter.entityToJsonValue(externalLinks));
    }
  }

  private static List<IUser> queryActiveUserByApp(int index, int pageSize, long appId) {
    return createQueryActiveUserByApp(appId).executor().resultsPaged().window(index, pageSize);
  }

  private static long countActiveUserByApp(long appId) {
    return createQueryActiveUserByApp(appId).executor().count();
  }

  private static FilterLink createQueryActiveUserByApp(long appId) {
    return UserQuery.create().where().enabled().isTrue();
    //TODO consider applicationId(), original code return UserQuery.create().where().enabled().isTrue().and().applicationId().isEqual(appId);
  }

  private static IUser findUserInAppByName(IApplication app, String userName) {
    return app.getSecurityContext().users().find(userName);
  }
  
  private static void updateProcessProperties(List<IUser> users, IApplication app,
      @SuppressWarnings("unused") List<String> error) {

    for (IUser user : users) {
      var favoriteProcesses = user.getProperty(PortalVariable.FAVORITE_PROCESS.key);
      List<UserProcess> userProcesses = BusinessEntityConverter.jsonValueToEntities(favoriteProcesses, UserProcess.class);
      for (UserProcess process : userProcesses) {
        String link = process.getLink();
        if (StringUtils.isBlank(process.getWorkflowId())) {
          link = updateAppForLink(process.getLink(), app.getName());
        }
        process.setLink(link);

        if (StringUtils.isNotBlank(process.getWorkflowId())) {
          process.setProcessType(process.isExternalLink() ? ProcessType.EXTERNAL_LINK : ProcessType.EXPRESS_PROCESS);
          process.setProcessId(process.getWorkflowId());
        } else {
          List<IWebStartable> startables = WorkflowNavigationUtil.getWorkflowContext(app).getStartables(user);
          startables.forEach(start -> {
            if (start.getLink().getRelativeEncoded().equals(process.getLink())) {
              process.setProcessId(start.getId());
            }
          });
          process.setProcessType(ProcessType.IVY_PROCESS);
        }

        if (StringUtils.isNotBlank(process.getProcessId())) {
          process.setLink("");
        }
      }

      user.setProperty(PortalVariable.FAVORITE_PROCESS.key, BusinessEntityConverter.entityToJsonValue(userProcesses));
    }
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
