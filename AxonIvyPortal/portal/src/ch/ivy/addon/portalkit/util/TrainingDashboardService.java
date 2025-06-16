package ch.ivy.addon.portalkit.util;

import java.util.HashMap;
import java.util.Map;

import com.axonivy.portal.components.service.IvyAdapterService;
import com.axonivy.portal.enums.PortalCustomSignature;

import ch.ivy.addon.portalkit.dto.dashboard.Dashboard;
import ch.ivyteam.ivy.environment.Ivy;

/**
 * Utility class for training dashboard operations
 */
public class TrainingDashboardService {

  private static final String TRAINING_DASHBOARD_PROCESS_SIGNATURE = "getTrainingDashboard()";
  
  /**
   * Load training dashboard from portal-user-examples project
   * @return List of dashboard maps or empty list if not available
   */
  public static Dashboard loadTrainingDashboard() {
    try {
      // Call the training dashboard process using IvyAdapterService
      Map<String, Object> result = IvyAdapterService.startSubProcessInSecurityContext(
          PortalCustomSignature.GET_TRAINING_DASHBOARD.getSignature(),
          new HashMap<>()
      );
      
      if (result == null || result.isEmpty()) {
        Ivy.log().info("Training dashboard process not found - Portal User Examples project may not be installed");
        return null;
      }
      
      Object dashboard = result.get("traningDashboard");
      Dashboard traningDashboard = DashboardUtils.jsonToDashboard((String) dashboard);
      return traningDashboard;
    } catch (Exception e) {
      Ivy.log().error("Error loading training dashboard", e);
      return null;
    }
  }
  
  /**
   * Check if training dashboard is available
   * @return true if training dashboard process exists
   */
  public static boolean isTrainingDashboardAvailable() {
    try {
      // Try to call the training dashboard process to check if it's available
      Map<String, Object> result = IvyAdapterService.startSubProcessInSecurityContext(
          TRAINING_DASHBOARD_PROCESS_SIGNATURE, 
          new HashMap<>()
      );
      return result != null && !result.isEmpty();
    } catch (Exception e) {
      return false;
    }
  }
}
