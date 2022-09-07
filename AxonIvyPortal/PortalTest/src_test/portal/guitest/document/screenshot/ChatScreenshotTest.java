package portal.guitest.document.screenshot;

import static portal.guitest.common.Variable.ENABLE_GROUP_CHAT;
import static portal.guitest.common.Variable.ENABLE_PRIVATE_CHAT;

import java.io.IOException;

import org.junit.Test;
import org.openqa.selenium.Dimension;

import ch.ivy.addon.portalkit.util.ScreenshotMargin;
import ch.ivy.addon.portalkit.util.ScreenshotUtil;
import portal.guitest.common.ScreenshotTest;
import portal.guitest.common.TestAccount;
import portal.guitest.page.ChatPage;
import portal.guitest.page.HomePage;
import portal.guitest.page.NewDashboardPage;
import portal.guitest.page.TaskTemplatePage;
import portal.guitest.page.TaskWidgetPage;

public class ChatScreenshotTest extends ScreenshotTest {
  private NewDashboardPage newDashboardPage;
  
  @Override
  public void setup() {
    super.setup();
    login(TestAccount.HR_ROLE_USER);
    updatePortalSetting(ENABLE_GROUP_CHAT.getKey(), "true");
    updatePortalSetting(ENABLE_PRIVATE_CHAT.getKey(), "true");
    redirectToRelativeLink(createTestingTasksUrl);
  }

  @Test
  public void screenshotChat() throws IOException {
    showNewDashboard();
    newDashboardPage = new NewDashboardPage();
    ScreenshotUtil.resizeBrowser(new Dimension(1500, 800));
    ScreenshotUtil.captureHalfTopRightPageScreenShot(ScreenshotUtil.CHAT_FOLDER + "access-chat");
    TaskWidgetPage taskWidgetPage = newDashboardPage.openTaskList();
    TaskTemplatePage taskTemplatePage = taskWidgetPage.startTask(0);
    taskTemplatePage.waitForChatServiceReady();
    taskTemplatePage.clickTaskActionMenu();
    executeDecorateJs("highlightJoinGroupChatOption()");
    ScreenshotUtil.capturePageScreenshot(ScreenshotUtil.CHAT_FOLDER + "create-new-group-chat");
    
    taskTemplatePage.clickChatGroup(false);
    ScreenshotUtil.captureElementWithMarginOptionScreenshot(taskTemplatePage.getAddMemberToChatDialog(), ScreenshotUtil.CHAT_FOLDER + "chat-group-assignee", new ScreenshotMargin(20));
    taskTemplatePage.clickCreateGroupChatBtn();
    
    redirectToRelativeLink(HomePage.PORTAL_HOME_PAGE_URL);
    newDashboardPage = new NewDashboardPage();
    ChatPage chatPage = newDashboardPage.getChat();
    chatPage.openFirstGroupChat();
    ScreenshotUtil.resizeBrowser(new Dimension(1200, 800));
    ScreenshotUtil.capturePageScreenshot(ScreenshotUtil.CHAT_FOLDER + "chat");
  }
}
