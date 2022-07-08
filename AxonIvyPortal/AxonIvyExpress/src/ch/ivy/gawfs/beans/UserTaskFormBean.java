package ch.ivy.gawfs.beans;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.apache.commons.lang3.StringUtils;

import ch.ivy.addon.portalkit.service.CaseDocumentService;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.IUser;

@ManagedBean
@RequestScoped
public class UserTaskFormBean implements Serializable {

  private static final long serialVersionUID = -6718109284635965763L;
  private static final String USER_NAME_FORMAT = "%s (%s)";

  /**
   * Generate user name based on given member name
   * 
   * @param userMemberName
   * @return user name
   */
  public String generateUserName(String userMemberName) {
    IUser user = Ivy.security().users().find(userMemberName);

    if (StringUtils.isBlank(user.getDisplayName())) {
      return user.getName();
    } else {
      return String.format(USER_NAME_FORMAT, user.getDisplayName(), user.getName());
    }
  }

  public List<String> getAllowedUploadFileType() {
    return CaseDocumentService.getAllowedUploadFileType();
  }

  public boolean getEnableScriptCheckingForUploadedDocument() {
    return CaseDocumentService.enableScriptCheckingForUploadedDocument();
  }

  public boolean getEnableVirusScannerForUploadedDocument() {
    return CaseDocumentService.enableVirusScannerForUploadedDocument();
  }
}