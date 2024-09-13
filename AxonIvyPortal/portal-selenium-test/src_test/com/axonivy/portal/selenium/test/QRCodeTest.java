package com.axonivy.portal.selenium.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.BaseTest;
import com.axonivy.portal.selenium.common.TestAccount;
import com.axonivy.portal.selenium.common.Variable;
import com.axonivy.portal.selenium.page.NewDashboardPage;
import com.axonivy.portal.selenium.page.QRCodePage;

@IvyWebTest
public class QRCodeTest extends BaseTest {
  @Override
  @BeforeEach
  public void setup() {
    super.setup();
    login(TestAccount.DEMO_USER);
    updatePortalSetting(Variable.SHOW_QR_CODE.getKey(), "true");
  }
  
  @Test
  @Disabled("Only on LE, should remove")
  public void testOpenQRCode(){
    redirectToNewDashBoard();
    NewDashboardPage homePage = new NewDashboardPage();
    QRCodePage qrCodePage = homePage.openQRCode();
    qrCodePage.isQRCodeDisplayed();
    qrCodePage.closeQRCodeDialog();
  }

}
