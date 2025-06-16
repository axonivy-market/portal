package ch.ivy.addon.portal.generic.bean;

import java.io.IOException;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.lang3.StringUtils;

import ch.ivy.addon.portal.generic.navigation.PortalNavigator;
import ch.ivy.addon.portalkit.util.TrainingDashboardUtils;
import ch.ivyteam.ivy.environment.Ivy;

/**
 * Managed bean for training dashboard functionality
 */
@ManagedBean
@ViewScoped
public class TrainingDashboardBean implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private boolean showTrainingDashboard;
    private String trainingDashboardJson;
    
    @PostConstruct
    public void init() {
        showTrainingDashboard = TrainingDashboardUtils.shouldShowTrainingDashboard();
    }
    
    /**
     * Check if training dashboard should be displayed
     * @return true if both conditions are met
     */
    public boolean isShowTrainingDashboard() {
        return showTrainingDashboard;
    }
    
    /**
     * Get the training dashboard JSON content
     * @return JSON string for training dashboard configuration
     */
    public String getTrainingDashboardJson() {
        if (StringUtils.isBlank(trainingDashboardJson)) {
            // TODO: Load from configuration or CMS
            trainingDashboardJson = getDefaultTrainingDashboardJson();
        }
        return trainingDashboardJson;
    }
    
    /**
     * Handle training completion
     * @throws IOException if redirect fails
     */
    public void completeTraining() throws IOException {
        TrainingDashboardUtils.completeTraining();
        navigateToHomepage();
    }
    
    /**
     * Handle training dismissal (skip)
     * @throws IOException if redirect fails
     */
    public void dismissTraining() throws IOException {
        TrainingDashboardUtils.completeTraining();
        navigateToHomepage();
    }
    
    /**
     * Navigate to normal homepage after training completion/dismissal
     * @throws IOException if redirect fails
     */
    private void navigateToHomepage() throws IOException {
        String homepageUrl = PortalNavigator.getPortalStartUrl();
        FacesContext.getCurrentInstance().getExternalContext().redirect(homepageUrl);
    }
    
    /**
     * Default training dashboard JSON configuration
     * This should be replaced with actual training content
     * @return default JSON configuration
     */
    private String getDefaultTrainingDashboardJson() {
        // Default training dashboard configuration
        // This will be replaced with the actual trainingDashboardJson content
        return """
        {
          "title": "Welcome to Portal!",
          "subtitle": "Let's get you started with a quick tour",
          "steps": [
            {
              "title": "Welcome",
              "content": "Welcome to your Portal dashboard. Here you can manage tasks, cases, and processes.",
              "target": "#main-content"
            },
            {
              "title": "Navigation Menu",
              "content": "Use this menu to navigate between different sections of the Portal.",
              "target": "#left-menu"
            },
            {
              "title": "User Menu",
              "content": "Access your profile settings and logout from here.",
              "target": "#user-settings-menu"
            }
          ],
          "showCompleteButton": true,
          "showDismissButton": true,
          "completeButtonText": "Complete Training",
          "dismissButtonText": "Skip Training"
        }
        """;
    }
    
    /**
     * Check if Portal User Example project is installed
     * @return true if project is available
     */
    public boolean isPortalUserExampleProjectInstalled() {
        return TrainingDashboardUtils.isPortalUserExampleProjectInstalled();
    }
    
    /**
     * Check if current user is first time login
     * @return true if first login
     */
    public boolean isFirstLogin() {
        return TrainingDashboardUtils.isFirstLogin();
    }
}
