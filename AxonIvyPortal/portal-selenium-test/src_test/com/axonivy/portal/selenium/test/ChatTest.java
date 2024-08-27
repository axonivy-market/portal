package com.axonivy.portal.selenium.test;

import java.time.Duration;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WindowType;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.bean.ExpressResponsible;
import com.axonivy.portal.selenium.common.BaseTest;
import com.axonivy.portal.selenium.common.NavigationHelper;
import com.axonivy.portal.selenium.common.TestAccount;
import com.axonivy.portal.selenium.common.Variable;
import com.axonivy.portal.selenium.common.WaitHelper;
import com.axonivy.portal.selenium.page.ChatPage;
import com.axonivy.portal.selenium.page.HomePage;
import com.axonivy.portal.selenium.page.NewDashboardPage;
import com.axonivy.portal.selenium.page.TaskTemplatePage;
import com.axonivy.portal.selenium.page.TaskWidgetPage;
import com.codeborne.selenide.WebDriverRunner;



@IvyWebTest(headless = false)
public class ChatTest extends BaseTest {
  private static final String ADMIN1_2 = "admin1-2";
  private static final String ADMIN1_1 = "admin1-1";
  private static final String DEMO1_2 = "demo1-2";
  private static final String DEMO1_1 = "demo1-1";

  private static final String ENABLE_PRIVATE_CHAT_SETTING = Variable.ENABLE_PRIVATE_CHAT.getKey();
  private static final String ENABLE_GROUP_CHAT_SETTING = Variable.ENABLE_GROUP_CHAT.getKey();
  private static final String CHAT_MESSAGE_USER_DEMO = "Hi i'm demo user";
  private static final String CHAT_MESSAGE_USER_ADMIN = "Hi i'm admin user";
  
  private static final int CHAT_PAGE_DEMO_1_BROWSER = 0;
  private static final int CHAT_PAGE_DEMO_2_BROWSER = 1;
  private static final int CHAT_PAGE_ADMIN_1_BROWSER = 2;
  private static final int CHAT_PAGE_ADMIN_2_BROWSER = 3;

  @Override
  @BeforeEach
  public void setup() {
    super.setup();
  }

  @Test
  public void chatAddGroup() {
    ExpressResponsible chatUser1 = setExpressResponsible(TestAccount.ADMIN_USER.getUsername(), false);
    ChatPage chatPage = enableChatGroup();
    createChatGroup(TestAccount.DEMO_USER);
    joinChatGroupWhichAlreadyHadChatGroup(TestAccount.ADMIN_USER);
    chatMessageInGroup(TestAccount.DEMO_USER, CHAT_MESSAGE_USER_DEMO);

    openNewTabOrWindow(WindowType.WINDOW);
    launchBrowserAndGotoRelativeLink(NewDashboardPage.PORTAL_HOME_PAGE_URL);
    ChatPage chatPage2 = chatMessageInGroup(TestAccount.ADMIN_USER, CHAT_MESSAGE_USER_ADMIN);

    openNewTabOrWindow(WindowType.WINDOW);
    launchBrowserAndGotoRelativeLink(NewDashboardPage.PORTAL_HOME_PAGE_URL);

    ExpressResponsible chatGroupEveryBody = setExpressResponsible("Everybody", true);
    createChatGroup(TestAccount.GUEST_USER, chatUser1, chatGroupEveryBody);

    login(TestAccount.ADMIN_USER);
    assertEquals(2, chatPage2.refreshAndCountGroupChat());

    login(TestAccount.DEMO_USER);
    assertEquals(2, chatPage.refreshAndCountGroupChat());
  }

  @Test
  public void chatGroupOnTwoInstanceOfBrowser() {
    ChatPage chatPage = enableChatGroup();
    createChatGroup(TestAccount.DEMO_USER);
    joinChatGroupWhichAlreadyHadChatGroup(TestAccount.ADMIN_USER);
    chatMessageInGroup(TestAccount.DEMO_USER, CHAT_MESSAGE_USER_DEMO);

    launchBrowserAndGotoRelativeLink(NewDashboardPage.PORTAL_HOME_PAGE_URL);
    ChatPage chatPage2 = chatMessageInGroup(TestAccount.ADMIN_USER, CHAT_MESSAGE_USER_ADMIN);
    chatPage2.closeChatMessageList();

    chatPage = chatMessageInGroup(TestAccount.DEMO_USER, CHAT_MESSAGE_USER_DEMO);
    chatPage.sendMessage("from 1 to 2");

    chatPage2 = getChatGroup(TestAccount.ADMIN_USER);
    chatPage2.waitForPageLoad();
    assertChatNotification(chatPage2, true);

    chatPage2.openFirstGroupChat();
    chatPage2.sendMessage("from 2 to 1");

    chatPage = openChatGroup(TestAccount.DEMO_USER);
    chatPage.waitForPageLoad();
    assertContainMessage(chatPage, "from 2 to 1");

    chatPage2 = openChatGroup(TestAccount.ADMIN_USER);
    chatPage2.waitForPageLoad();
    assertContainMessage(chatPage2, "from 1 to 2");
  }

  @Test
  public void chatDisplay() {
    enableChatGroup();
    new NewDashboardPage().isChatDisplayed();
  }

  @Test
  public void chatPrivate() {
    ChatPage chatPage = enableChatPrivate();
    chatPage.selectChatUser("demo");
    chatPage.sendMessage(CHAT_MESSAGE_USER_ADMIN);

    login(TestAccount.DEMO_USER);
    new NewDashboardPage().getChat();
    chatPage.selectChatUser("admin");
    chatPage.sendMessage(CHAT_MESSAGE_USER_DEMO);
  }

  public void joinChatGroupAlreadyCreated() {
    ChatPage chatPage = enableChatGroup();
    createChatGroup(TestAccount.DEMO_USER);
    joinChatGroupWhichAlreadyHadChatGroup(TestAccount.ADMIN_USER);
    chatMessageInGroup(TestAccount.DEMO_USER, CHAT_MESSAGE_USER_DEMO);
    chatMessageInGroup(TestAccount.ADMIN_USER, CHAT_MESSAGE_USER_ADMIN);

    assertContainMessage(chatPage, CHAT_MESSAGE_USER_DEMO);
    assertContainMessage(chatPage, CHAT_MESSAGE_USER_ADMIN);

    login(TestAccount.DEMO_USER);
    new NewDashboardPage().getChat().isNotificationContactChat();
  }

  private ChatPage createChatGroup(TestAccount creatorChatGroup, ExpressResponsible... participants) {
    redirectToRelativeLink(createTestingCaseUrlForDefaultAdditionalCaseDetails);
    login(creatorChatGroup);
//    redirectToRelativeLink(NewDashboardPage.PORTAL_HOME_PAGE_URL);
    ChatPage chatPage = new NewDashboardPage().getChat();
    chatPage.waitForPageLoad();
    // Create chat group via task
    TaskWidgetPage taskWidgetPage = NavigationHelper.navigateToTaskList();
    TaskTemplatePage taskTemplatePage = taskWidgetPage.startTask(0);
    taskTemplatePage.clickTaskActionMenu();
    taskTemplatePage.clickChatGroup();
    if (participants.length != 0) {
      chatPage.addUserToChatGroup(Arrays.asList(participants));
    }
    taskTemplatePage.clickCreateGroupChatBtn();
    return chatPage;
  }

  private ChatPage enableChatGroup() {
    updatePortalSetting(ENABLE_GROUP_CHAT_SETTING, "true");
    login(TestAccount.ADMIN_USER);
    return new ChatPage();
  }

  private ChatPage enableChatPrivate() {
    updatePortalSetting(ENABLE_PRIVATE_CHAT_SETTING, "true");
    login(TestAccount.ADMIN_USER);
    new NewDashboardPage().getChat();
    return new ChatPage();
  }

  private void joinChatGroupWhichAlreadyHadChatGroup(TestAccount userJoined) {
    login(userJoined);
    TaskWidgetPage taskWidgetPage = NavigationHelper.navigateToTaskList();
    taskWidgetPage.filterTasksInExpandedModeBy("Sick Leave Request Default Case Details Page");
    TaskTemplatePage taskTemplatePage = taskWidgetPage.startTask(0);
    taskTemplatePage.clickTaskActionMenu();
    taskTemplatePage.clickChatGroup();
    taskTemplatePage.joinProcessChatAlreadyCreated();
  }

  private ChatPage chatMessageInGroup(TestAccount chatUser, String chatMessage) {
    ChatPage chatPage = openChatGroup(chatUser);
    chatPage.sendMessage(chatMessage);
    return chatPage;
  }

  private ChatPage openChatGroup(TestAccount chatUser) {
    login(chatUser);
    redirectToRelativeLink(NewDashboardPage.PORTAL_HOME_PAGE_URL);
    ChatPage chatPage = getChatGroup(chatUser);
    chatPage.openFirstGroupChat();
    return chatPage;
  }

  private ChatPage getChatGroup(TestAccount chatUser) {
    login(chatUser);
//    redirectToRelativeLink(NewDashboardPage.PORTAL_HOME_PAGE_URL);
    return new NewDashboardPage().getChat();
  }

  private void assertContainMessage(ChatPage chatPage, String message) {
    WaitHelper.assertTrueWithWait(() -> chatPage.getAllMessagesChatLog().contains(message));
  }

  private void assertChatNotification(ChatPage chatPage, boolean hasNotification) {
    chatPage.isNotificationBadgeChat();
    chatPage.isNotificationContactChat();
  }

  /**
   * Note: need more time for this test
   */
  @Test
  public void chatGroupMultiTabs() {
    enableChatGroup();
    createChatGroup(TestAccount.DEMO_USER);
    ChatPage chatPageDemo1 = new ChatPage();
    launchBrowserAndGotoRelativeLink(PORTAL_HOME_PAGE_URL);
    ChatPage chatPageDemo2 = openChatGroup(TestAccount.DEMO_USER);

    openNewTabOrWindow(WindowType.WINDOW);
    launchBrowserAndGotoRelativeLink(PORTAL_HOME_PAGE_URL);
    joinChatGroupWhichAlreadyHadChatGroup(TestAccount.ADMIN_USER);
    ChatPage chatPageAdmin1 = new ChatPage();
    openNewTabOrWindow(WindowType.WINDOW);
    launchBrowserAndGotoRelativeLink(PORTAL_HOME_PAGE_URL);
    ChatPage chatPageAdmin2 = openChatGroup(TestAccount.ADMIN_USER);
//    chatPageAdmin1.closeChatMessageList();
//    chatPageAdmin2.closeChatMessageList();
    
    chatPageDemo1.sendMessage(DEMO1_1);
//    assertChatNotification(chatPageDemo2, false);
    assertContainMessage(chatPageDemo2, DEMO1_1);
//    assertChatNotification(chatPageAdmin1, true);
//    assertChatNotification(chatPageAdmin2, true);
    // admin1 opens group
    chatPageAdmin1.openFirstGroupChat();
    assertContainMessage(chatPageAdmin1, DEMO1_1);
//    assertChatNotification(chatPageAdmin2, false);

    // demo1, demo2, admin1 with group opened, admin2 with group closed, demo1 sends message
    chatPageDemo1.sendMessage(DEMO1_2);
    assertContainMessage(chatPageAdmin1, DEMO1_2);
//    assertChatNotification(chatPageAdmin2, false);

    // demo1, demo2, admin2 with group opened, admin2 with group closed, admin1 sends message
    chatPageAdmin1.sendMessage(ADMIN1_1);
//    assertChatNotification(chatPageAdmin2, false);
    assertContainMessage(chatPageDemo1, ADMIN1_1);
    assertContainMessage(chatPageDemo2, ADMIN1_1);

    // demo1, demo2, demo3, demo4, admin2 with group opened, admin2 with group closed, admin1 sends message
    openNewTabOrWindow(WindowType.WINDOW);
    launchBrowserAndGotoRelativeLink(HomePage.PORTAL_HOME_PAGE_URL);
    ChatPage chatPageDemo3 = openChatGroup(TestAccount.DEMO_USER);
    openNewTabOrWindow(WindowType.WINDOW);
    launchBrowserAndGotoRelativeLink(HomePage.PORTAL_HOME_PAGE_URL);
    ChatPage chatPageDemo4 = openChatGroup(TestAccount.DEMO_USER);
    chatPageAdmin1.sendMessage(ADMIN1_2);
    //TODO need to be fixed - Workaround for wait message render
    WebDriverRunner.getWebDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

    assertContainMessage(chatPageDemo1, ADMIN1_2);
    assertContainMessage(chatPageDemo2, ADMIN1_2);
    assertContainMessage(chatPageDemo3, ADMIN1_2);
    assertContainMessage(chatPageDemo4, ADMIN1_2);
  }

}
