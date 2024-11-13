package com.axonivy.portal.selenium.document.screenshot;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Dimension;

import com.axonivy.portal.selenium.common.ScreenshotBaseTest;
import com.axonivy.portal.selenium.common.ScreenshotMargin;
import com.axonivy.portal.selenium.common.ScreenshotUtils;
import com.axonivy.portal.selenium.common.TestAccount;
import com.axonivy.portal.selenium.common.Variable;
import com.axonivy.portal.selenium.page.ChatPage;
import com.axonivy.portal.selenium.page.MainMenuPage;
import com.axonivy.portal.selenium.page.NewDashboardPage;
import com.axonivy.portal.selenium.page.TaskTemplatePage;
import com.axonivy.portal.selenium.page.TopMenuTaskWidgetPage;


public class ChatScreenshotTest extends ScreenshotBaseTest {


  
  @Override
  @BeforeEach
  public void setup() {
    super.setup();
    login(TestAccount.HR_ROLE_USER);
    updatePortalSetting(Variable.ENABLE_GROUP_CHAT.getKey(), "true");
    updatePortalSetting(Variable.ENABLE_PRIVATE_CHAT.getKey(), "true");
    redirectToRelativeLink(createTestingTasksUrl);
  }

  @Test
  public void screenshotChat() throws IOException {
    showNewDashboard();
    NewDashboardPage newDashboardPage = new NewDashboardPage();
    newDashboardPage.waitForCaseWidgetLoaded();
    ScreenshotUtils.resizeBrowser(new Dimension(1500, 800));
    ScreenshotUtils.executeDecorateJs("highlightChatIcon()");
    ScreenshotUtils.captureHalfTopRightPageScreenShot(ScreenshotUtils.CHAT_FOLDER + "access-chat");
    MainMenuPage menu = new MainMenuPage();
    menu.openTaskList();
    TopMenuTaskWidgetPage taskWidget = new TopMenuTaskWidgetPage();
    taskWidget.setInputForQuickSearch("Maternity Leave Request");
    taskWidget.startTask(0);
    TaskTemplatePage taskTemplatePage = new TaskTemplatePage();

    taskTemplatePage.clickActionButton();
    ScreenshotUtils.executeDecorateJs("highlightJoinGroupChatOption()");
    ScreenshotUtils.capturePageScreenshot(ScreenshotUtils.CHAT_FOLDER + "create-new-group-chat");

    taskTemplatePage.clickChatGroup();
    ScreenshotUtils.captureElementWithMarginOptionScreenshot(taskTemplatePage.getAddMemberToChatDialog(),
        ScreenshotUtils.CHAT_FOLDER + "chat-group-assignee", new ScreenshotMargin(20));
    taskTemplatePage.clickCreateGroupChatButton();

    showNewDashboard();
    newDashboardPage = new NewDashboardPage();
    ChatPage chatPage = newDashboardPage.openChatDialog();
    chatPage.openFirstGroupChat();
    ScreenshotUtils.resizeBrowser(new Dimension(1200, 800));
    ScreenshotUtils.capturePageScreenshot(ScreenshotUtils.CHAT_FOLDER + "chat");
  }
}
