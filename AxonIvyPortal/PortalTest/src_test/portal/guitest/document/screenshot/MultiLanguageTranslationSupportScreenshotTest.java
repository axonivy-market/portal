package portal.guitest.document.screenshot;

import static portal.guitest.common.Variable.DEEPL_AUTH_KEY;
import static portal.guitest.common.Variable.ENABLE_DEEPL_TRANSLATION;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import ch.ivy.addon.portalkit.util.ScreenshotUtil;
import portal.guitest.common.ScreenshotTest;
import portal.guitest.common.TestAccount;
import portal.guitest.page.NewDashboardPage;

public class MultiLanguageTranslationSupportScreenshotTest extends ScreenshotTest {
  
  @Override
  @Before
  public void setup() {
    super.setup();
    updatePortalSetting(DEEPL_AUTH_KEY.getKey(), "DEEPL_AUTH_KEY");
    updatePortalSetting(ENABLE_DEEPL_TRANSLATION.getKey(), "true");
  }
  
  @Test
  public void screenshotDashboardConfigurationUserGuide() throws IOException {
    login(TestAccount.ADMIN_USER);
    redirectToRelativeLink(createSampleDashboardUrl);
    showNewDashboard();
    NewDashboardPage homePage = new NewDashboardPage();
    var dashboardConfigurationPage = homePage.openDashboardConfigurationPage();
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
