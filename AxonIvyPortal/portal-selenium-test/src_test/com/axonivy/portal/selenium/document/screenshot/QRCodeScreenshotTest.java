package com.axonivy.portal.selenium.document.screenshot;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Dimension;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.ScreenshotBaseTest;
import com.axonivy.portal.selenium.common.ScreenshotMargin;
import com.axonivy.portal.selenium.common.ScreenshotUtils;
import com.axonivy.portal.selenium.common.TestAccount;
import com.axonivy.portal.selenium.common.Variable;
import com.axonivy.portal.selenium.page.NewDashboardPage;
import com.axonivy.portal.selenium.page.QRCodePage;

@IvyWebTest
public class QRCodeScreenshotTest extends ScreenshotBaseTest {
  @Override
  @BeforeEach
  public void setup() {
    super.setup();
    login(TestAccount.DEMO_USER);
    updatePortalSetting(Variable.SHOW_QR_CODE.getKey(), "true");
  }
  
  @Test
  public void screenshotQRCode() throws IOException {
    NewDashboardPage homePage = new NewDashboardPage();
    
    QRCodePage qrCodePage = homePage.openQRCode();
    ScreenshotUtils.captureElementWithMarginOptionScreenshot(qrCodePage.getQRCodeDialog(), ScreenshotUtils.MOBILE_FOLDER + "qr-code", new ScreenshotMargin(5, 5));
  }
  
  @Test
  public void screenshotQRCodeWithAnnotation() throws IOException {
    NewDashboardPage homePage = new NewDashboardPage();
    homePage.openUserSettingMenu();

    ScreenshotUtils.resizeBrowser(new Dimension(1500, 800));
    ScreenshotUtils.executeDecorateJs("highlightMobileApp()");
    ScreenshotUtils.captureHalfTopPageScreenShot(ScreenshotUtils.MOBILE_FOLDER + "mobile-app-menu");
  }
}
