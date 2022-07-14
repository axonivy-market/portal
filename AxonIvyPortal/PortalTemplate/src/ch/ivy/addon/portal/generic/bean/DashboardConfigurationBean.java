package ch.ivy.addon.portal.generic.bean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import ch.ivy.addon.portalkit.util.PermissionUtils;
import ch.ivy.addon.portalkit.util.RequestUtils;

@ManagedBean
@ViewScoped
public class DashboardConfigurationBean implements Serializable {

  private static final long serialVersionUID = 3541979834658757687L;

  private boolean canEditPrivateDashboard;
  private boolean canEditPublicDashboard;
  private boolean isPublicDashboard;
  private boolean isSelectingAction = true;
  private boolean isSelectingTemplate;
  private boolean isEditingDashboard;
  private boolean isReorderingDashboard;

  @PostConstruct
  public void initConfigurationBean() {
    initPermissions();
    resetAllIndicators();
    isSelectingAction = true;
  }

  private void initPermissions() {
    boolean isMobileDevice = RequestUtils.isMobileDevice();
    canEditPrivateDashboard = PermissionUtils.hasDashboardWriteOwnPermission() && !isMobileDevice;
    canEditPublicDashboard = PermissionUtils.hasDashboardWritePublicPermission() && !isMobileDevice;
  }

  private void resetAllIndicators() {
    isSelectingAction = false;
    isSelectingTemplate = false;
    isEditingDashboard = false;
    isReorderingDashboard = false;
  }

  public void switchDashboardType(boolean isPublicDashboard) {
    resetAllIndicators();
    this.isSelectingAction = true;
    this.isPublicDashboard = isPublicDashboard;
  }

  public void backToConfigurationTab() {
    resetAllIndicators();
    isSelectingAction = true;
  }

  public void addNewDashboard(boolean isPublicDashboard) {
    resetAllIndicators();
    this.isSelectingTemplate = true;
    this.isPublicDashboard = isPublicDashboard;
  }

  public void accessToEditDashboard(boolean isPublicDashboard) {
    resetAllIndicators();
    this.isEditingDashboard = true;
    this.isPublicDashboard = isPublicDashboard;
  }

  public void accessToReorderDashboard(boolean isPublicDashboard) {
    resetAllIndicators();
    this.isReorderingDashboard = true;
    this.isPublicDashboard = isPublicDashboard;
  }

  public boolean isCanEditPrivateDashboard() {
    return canEditPrivateDashboard;
  }

  public void setCanEditPrivateDashboard(boolean canEditPrivateDashboard) {
    this.canEditPrivateDashboard = canEditPrivateDashboard;
  }

  public boolean isCanEditPublicDashboard() {
    return canEditPublicDashboard;
  }

  public void setCanEditPublicDashboard(boolean canEditPublicDashboard) {
    this.canEditPublicDashboard = canEditPublicDashboard;
  }

  public boolean isPublicDashboard() {
    return isPublicDashboard;
  }

  public void setPublicDashboard(boolean isPublicDashboard) {
    this.isPublicDashboard = isPublicDashboard;
  }

  public boolean isSelectingAction() {
    return isSelectingAction;
  }

  public void setSelectingAction(boolean isSelectingAction) {
    this.isSelectingAction = isSelectingAction;
  }

  public boolean isSelectingTemplate() {
    return isSelectingTemplate;
  }

  public void setSelectingTemplate(boolean isSelectingTemplate) {
    this.isSelectingTemplate = isSelectingTemplate;
  }

  public boolean isEditingDashboard() {
    return isEditingDashboard;
  }

  public void setEditingDashboard(boolean isEditingDashboard) {
    this.isEditingDashboard = isEditingDashboard;
  }

  public boolean isReorderingDashboard() {
    return isReorderingDashboard;
  }

  public void setReorderingDashboard(boolean isReorderingDashboard) {
    this.isReorderingDashboard = isReorderingDashboard;
  }
}
