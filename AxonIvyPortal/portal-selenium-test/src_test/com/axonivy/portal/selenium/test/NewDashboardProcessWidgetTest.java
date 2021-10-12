package com.axonivy.portal.selenium.test;

import static com.codeborne.selenide.Selenide.$;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.BaseTest;
import com.axonivy.portal.selenium.common.TestAccount;
import com.axonivy.portal.selenium.page.NewDashBoardPage;
import com.axonivy.portal.selenium.page.ProcessEditWidgetNewDashBoardPage;
import com.codeborne.selenide.Condition;

@IvyWebTest(headless = false)
public class NewDashboardProcessWidgetTest extends BaseTest {
  private NewDashBoardPage newDashBoardPage;
  private static final String IMAGE_URI = "PROCESSMODELING";
  private static final long DEFAULT_TIMEOUT = 45000;
  
  @Override
  @BeforeEach
  public void setup() {
    super.setup();
    newDashBoardPage = new NewDashBoardPage();
    login(TestAccount.ADMIN_USER);
    redirectToNewDashBoard();
  }
  
  @Test
  public void testPreviewButtonImageProcess() {
    ProcessEditWidgetNewDashBoardPage editProcessWidgetConfiguration = newDashBoardPage.editProcessWidgetConfiguration();
    editProcessWidgetConfiguration.previewImageMode("Categoried Leave Request");
    editProcessWidgetConfiguration.getStartButton().is(Condition.disabled);
    editProcessWidgetConfiguration.getMoreInformationLink().is(Condition.disabled);
  }
  
  @Test
  public void testChangeImageProcess() {
    ProcessEditWidgetNewDashBoardPage editProcessWidgetConfiguration = newDashBoardPage.editProcessWidgetConfiguration();
    editProcessWidgetConfiguration.selectImageMode("Categoried Leave Request");
    checkStartButtonAndImageShown();
    editProcessWidgetConfiguration = newDashBoardPage.editImageProcess();
    String newProcessName = "Appraisal";
    editProcessWidgetConfiguration.changeImageProcess(newProcessName);
    newDashBoardPage.getProcessItemName().getText().equals(newProcessName);
  }

  private void checkStartButtonAndImageShown() {
    newDashBoardPage.getStartProcessButton().isDisplayed();
    newDashBoardPage.getProcessImage().attr("src").contains(IMAGE_URI);
  }
  
  @Test
  public void testDeleteImageProcess() {
    ProcessEditWidgetNewDashBoardPage editProcessWidgetConfiguration = newDashBoardPage.editProcessWidgetConfiguration();
    editProcessWidgetConfiguration.selectImageMode("Categoried Leave Request");
    checkStartButtonAndImageShown();
    newDashBoardPage.deleteImageProcess();
    assertFalse(newDashBoardPage.getImageContainer().isDisplayed());
  }

  @Test
  public void testStartImageProcess() {
    ProcessEditWidgetNewDashBoardPage editProcessWidgetConfiguration = newDashBoardPage.editProcessWidgetConfiguration();
    editProcessWidgetConfiguration.selectImageMode("Showcase Data Table");
    checkStartButtonAndImageShown();
    newDashBoardPage.switchToViewMode();
    newDashBoardPage.getStartProcessButton();
    newDashBoardPage.startProcess();
    
    // make sure process is started
    $("span[id='title']").waitUntil(Condition.appear, DEFAULT_TIMEOUT).isDisplayed();
    assertEquals($("span[id='title']").getAttribute("title"), "Start DataTable Showcase"); 
  }
  
  @Test
  public void testMoreInfoLinkImageProcess() {
    ProcessEditWidgetNewDashBoardPage editProcessWidgetConfiguration = newDashBoardPage.editProcessWidgetConfiguration();
    editProcessWidgetConfiguration.selectImageMode("Showcase Data Table");
    checkStartButtonAndImageShown();
    newDashBoardPage.switchToViewMode();
    newDashBoardPage.getStartProcessButton();
    newDashBoardPage.startMoreInfoLink();
    
    // make sure back and start button is shown
    $("a[id='back-link']").waitUntil(Condition.appear, DEFAULT_TIMEOUT).isDisplayed();
    $("button[id='start-process-button']").waitUntil(Condition.appear, DEFAULT_TIMEOUT).isDisplayed();
  }
}
