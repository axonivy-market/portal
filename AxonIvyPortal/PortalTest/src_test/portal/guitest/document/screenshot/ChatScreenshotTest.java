package portal.guitest.document.screenshot;

import static portal.guitest.common.Variable.ENABLE_GROUP_CHAT;
import static portal.guitest.common.Variable.ENABLE_PRIVATE_CHAT;

import java.io.IOException;

import org.junit.Test;
import org.openqa.selenium.Dimension;

import ch.ivy.addon.portalkit.util.ScreenshotMargin;
import ch.ivy.addon.portalkit.util.ScreenshotUtil;
import portal.guitest.common.ScreenshotTest;
import portal.guitest.common.Sleeper;
import portal.guitest.common.TestAccount;
import portal.guitest.page.ChatPage;
import portal.guitest.page.HomePage;
import portal.guitest.page.TaskTemplatePage;
import portal.guitest.page.TaskWidgetPage;

public class ChatScreenshotTest extends ScreenshotTest {
  
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
    TaskWidgetPage taskWidgetPage = new TaskWidgetPage();
    ScreenshotUtil.resizeBrowser(new Dimension(1500, 600));
    new HomePage().waitForStatisticRendered();
    ScreenshotUtil.captureHalfTopRightPageScreenShot(ScreenshotUtil.CHAT_FOLDER + "access-chat");
    TaskTemplatePage taskTemplatePage = taskWidgetPage.startTask(0);
    Sleeper.sleep(3000);//wait for chat service initialize
    taskTemplatePage.clickTaskActionMenu();
    executeDecorateJs("highlightJoinGroupChatOption()");
    ScreenshotUtil.capturePageScreenshot(ScreenshotUtil.CHAT_FOLDER + "create-new-group-chat");
    
    taskTemplatePage.clickChatGroup(false);
    Sleeper.sleep(3000);//wait for focus animation finish to capture screenshot
    ScreenshotUtil.captureElementWithMarginOptionScreenshot(taskTemplatePage.getAddMemberToChatDialog(), ScreenshotUtil.CHAT_FOLDER + "chat-group-assignee", new ScreenshotMargin(20));
    taskTemplatePage.clickCreateGroupChatBtn();
    
    redirectToRelativeLink(HomePage.PORTAL_HOME_PAGE_URL);
    HomePage homePage = new HomePage();
    ChatPage chatPage= homePage.getChat();
    chatPage.openFirstGroupChat();
    ScreenshotUtil.resizeBrowser(new Dimension(1200, 800));
    ScreenshotUtil.capturePageScreenshot(ScreenshotUtil.CHAT_FOLDER + "chat");
    
  }
}
