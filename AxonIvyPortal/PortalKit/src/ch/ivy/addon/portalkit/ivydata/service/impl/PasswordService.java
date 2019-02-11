package ch.ivy.addon.portalkit.ivydata.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;

import ch.ivy.addon.portalkit.ivydata.dto.IvyPasswordResultDTO;
import ch.ivy.addon.portalkit.ivydata.exception.PortalIvyDataErrorType;
import ch.ivy.addon.portalkit.ivydata.exception.PortalIvyDataException;
import ch.ivy.addon.portalkit.ivydata.service.IPasswordService;
import ch.ivy.addon.portalkit.ivydata.utils.ServiceUtilities;
import ch.ivy.addon.portalkit.util.IvyExecutor;
import ch.ivyteam.ivy.application.IApplication;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.IUser;

public class PasswordService implements IPasswordService {

  private PasswordService() {}

  public static PasswordService newInstance() {
    return new PasswordService();
  }

  @Override
  public IvyPasswordResultDTO changePassword(String username, String newPassword, List<String> apps) {
    return IvyExecutor.executeAsSystem(() -> { 
      IvyPasswordResultDTO result = new IvyPasswordResultDTO();
      if (CollectionUtils.isEmpty(apps)) {
        return result;
      }

      List<PortalIvyDataException> errors = new ArrayList<>();
      for (String appName : apps) {
        try {
          IApplication app = ServiceUtilities.findApp(appName);
          IUser user = ServiceUtilities.findUser(username, app);
          user.setPassword(newPassword);
        } catch (PortalIvyDataException e) {
          errors.add(e);
        } catch (Exception ex) {
          Ivy.log().error("Error in changing password of user {0} within app {1}", ex, username, appName);
          errors.add(new PortalIvyDataException(appName, PortalIvyDataErrorType.FAIL_TO_CHANGE_PASSWORD.toString()));
        }
      }
      result.setErrors(errors);
      return result;
    });
  }
}
