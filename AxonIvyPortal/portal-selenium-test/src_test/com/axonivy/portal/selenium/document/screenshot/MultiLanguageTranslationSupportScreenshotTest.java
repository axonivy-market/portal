package com.axonivy.portal.selenium.document.screenshot;


import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.ScreenshotBaseTest;
import com.axonivy.portal.selenium.common.ScreenshotUtil;
import com.axonivy.portal.selenium.common.TestAccount;
import com.axonivy.portal.selenium.common.Variable;
import com.axonivy.portal.selenium.page.NewDashboardPage;

@IvyWebTest
public class MultiLanguageTranslationSupportScreenshotTest extends ScreenshotBaseTest {
  
  @Override
  @BeforeEach
  public void setup() {
    super.setup();
    updatePortalSetting(Variable.DEEPL_AUTH_KEY.getKey(), "DEEPL_AUTH_KEY");
    updatePortalSetting(Variable.ENABLE_DEEPL_TRANSLATION.getKey(), "true");
  }

  @Test
  public void screenshotDashboardConfigurationUserGuide() throws IOException {
    login(TestAccount.ADMIN_USER);
    redirectToRelativeLink(createSampleDashboardUrl);
    showNewDashboard();
    var dashboardConfigurationPage = new NewDashboardPage().openDashboardConfigurationPage();
    dashboardConfigurationPage.createPrivateDashboardFromScratch();
    ScreenshotUtil.captureElementScreenshot(dashboardConfigurationPage.getDashboardCreationDialog(),
        ScreenshotUtil.DASHBOARD_CONFIGURATION_FOLDER + "create-private-dashboard-dialog-ml");
    dashboardConfigurationPage.openMultiLanguageDialog();
    ScreenshotUtil.captureElementScreenshot(dashboardConfigurationPage.getDashboardMultiLanguageDialog(),
        ScreenshotUtil.DASHBOARD_CONFIGURATION_FOLDER + "dashboard-multi-language-dialog-ml");
    dashboardConfigurationPage.setTranslatedTitle();
    dashboardConfigurationPage.clickOnTextToTranslate(1);
    ScreenshotUtil.captureElementScreenshot(dashboardConfigurationPage.getDashboardMultiLanguageDialog(),
        ScreenshotUtil.DASHBOARD_CONFIGURATION_FOLDER + "overlay-panel-translation-ml");
    dashboardConfigurationPage.clickOkMultiLanguageDialog();
    dashboardConfigurationPage.clickOkCreateDashboard();
  }
}
