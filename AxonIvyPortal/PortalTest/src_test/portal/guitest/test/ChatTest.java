package portal.guitest.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static portal.guitest.common.Variable.ENABLE_GROUP_CHAT;
import static portal.guitest.common.Variable.ENABLE_PRIVATE_CHAT;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import portal.guitest.bean.ExpressResponsible;
import portal.guitest.common.BaseTest;
import portal.guitest.common.NavigationHelper;
import portal.guitest.common.Sleeper;
import portal.guitest.common.TestAccount;
import portal.guitest.common.WaitHelper;
import portal.guitest.page.ChatPage;
import portal.guitest.page.NewDashboardPage2;
import portal.guitest.page.TaskTemplatePage;
import portal.guitest.page.TaskWidgetPage;

public class ChatTest extends BaseTest {
  private static final String ADMIN1_2 = "admin1-2";
  private static final String ADMIN1_1 = "admin1-1";
  private static final String DEMO1_2 = "demo1-2";
  private static final String DEMO1_1 = "demo1-1";
  private static final String ENABLE_PRIVATE_CHAT_SETTING = ENABLE_PRIVATE_CHAT.getKey();
  private static final String ENABLE_GROUP_CHAT_SETTING = ENABLE_GROUP_CHAT.getKey();
  private static final String CHAT_MESSAGE_USER_DEMO = "Hi i'm demo user";
  private static final String CHAT_MESSAGE_USER_ADMIN = "Hi i'm admin user";
  ExpressResponsible chatUser1 = new ExpressResponsible(TestAccount.ADMIN_USER.getUsername(), false);
  ExpressResponsible chatGroup1 = new ExpressResponsible("Human resources department", true);
  ExpressResponsible chatGroupEveryBody = new ExpressResponsible("Everybody", true);

  @Override
  @Before
  public void setup() {
    super.setup();
  }

  @Test
  public void chatAddGroup() {
    ChatPage chatPage = enableChatGroup();
    createChatGroup(TestAccount.DEMO_USER);
    joinChatGroupWhichAlreadyHadChatGroup(TestAccount.ADMIN_USER);
    chatMessageInGroup(TestAccount.DEMO_USER, CHAT_MESSAGE_USER_DEMO);
    launchBrowserAndGotoRelativeLink(NewDashboardPage2.PORTAL_HOME_PAGE_URL);
    ChatPage chatPage2 = chatMessageInGroup(TestAccount.ADMIN_USER, CHAT_MESSAGE_USER_ADMIN);

    launchBrowserAndGotoRelativeLink(NewDashboardPage2.PORTAL_HOME_PAGE_URL);
    createChatGroup(TestAccount.GUEST_USER, chatUser1, chatGroupEveryBody);


    assertEquals(2, chatPage2.refreshAndCountGroupChat());
    assertEquals(2, chatPage.refreshAndCountGroupChat());
  }

  @Test
  public void chatGroupOnTwoInstanceOfBrowser() {
    ChatPage chatPage = enableChatGroup();
    createChatGroup(TestAccount.DEMO_USER);
    joinChatGroupWhichAlreadyHadChatGroup(TestAccount.ADMIN_USER);
    chatMessageInGroup(TestAccount.DEMO_USER, CHAT_MESSAGE_USER_DEMO);
    launchBrowserAndGotoRelativeLink(NewDashboardPage2.PORTAL_HOME_PAGE_URL);
    ChatPage chatPage2 = chatMessageInGroup(TestAccount.ADMIN_USER, CHAT_MESSAGE_USER_ADMIN);
    chatPage2.closeChatMessageList();
    chatPage.sendMessage("from 1 to 2");
    assertChatNotification(chatPage2, true);
    chatPage2.openFirstGroupChat();
    chatPage2.sendMessage("from 2 to 1");
    assertContainMessage(chatPage, "from 2 to 1");
    assertContainMessage(chatPage2, "from 1 to 2");
  }

  @Test
  public void chatDisplay() {
    enableChatGroup();
    assertTrue("Chat Shown", new NewDashboardPage2().isChatDisplayed());
  }

  @Test
  public void chatPrivate() {
    ChatPage chatPage = enableChatPrivate();
    chatPage.selectChatUser("demo");
    chatPage.sendMessage(CHAT_MESSAGE_USER_ADMIN);

    login(TestAccount.DEMO_USER);
    new NewDashboardPage2().getChat();
    chatPage.selectChatUser("admin");
    chatPage.sendMessage(CHAT_MESSAGE_USER_DEMO);
  }

  @Test
  public void joinChatGroupAlreadyCreated() {
    ChatPage chatPage = enableChatGroup();
    createChatGroup(TestAccount.DEMO_USER);
    joinChatGroupWhichAlreadyHadChatGroup(TestAccount.ADMIN_USER);
    chatMessageInGroup(TestAccount.DEMO_USER, CHAT_MESSAGE_USER_DEMO);
    chatMessageInGroup(TestAccount.ADMIN_USER, CHAT_MESSAGE_USER_ADMIN);

    assertContainMessage(chatPage, CHAT_MESSAGE_USER_DEMO);
    assertContainMessage(chatPage, CHAT_MESSAGE_USER_ADMIN);

    login(TestAccount.DEMO_USER);
    WaitHelper.assertTrueWithWait(() -> new NewDashboardPage2().getChat().isNotificationContactChat());
  }

  @Test
  public void chatGroupMultiTabs() {
    enableChatGroup();
    createChatGroup(TestAccount.DEMO_USER);
    ChatPage chatPageDemo1 = new ChatPage();
    launchBrowserAndGotoRelativeLink(NewDashboardPage2.PORTAL_HOME_PAGE_URL);
    ChatPage chatPageDemo2 = openChatGroup(TestAccount.DEMO_USER);
    launchBrowserAndGotoRelativeLink(NewDashboardPage2.PORTAL_HOME_PAGE_URL);
    joinChatGroupWhichAlreadyHadChatGroup(TestAccount.ADMIN_USER);
    ChatPage chatPageAdmin1 = new ChatPage();
    launchBrowserAndGotoRelativeLink(NewDashboardPage2.PORTAL_HOME_PAGE_URL);
    ChatPage chatPageAdmin2 = openChatGroup(TestAccount.ADMIN_USER);
    chatPageAdmin1.closeChatMessageList();
    chatPageAdmin2.closeChatMessageList();

    // demo1, demo2 with group opened, admin1, admin2 with group closed, demo1 sends message
    chatPageDemo1.sendMessage(DEMO1_1);
    assertChatNotification(chatPageDemo2, false);
    assertContainMessage(chatPageDemo2, DEMO1_1);
    assertChatNotification(chatPageAdmin1, true);
    assertChatNotification(chatPageAdmin2, true);
    // admin1 opens group
    chatPageAdmin1.openFirstGroupChat();
    assertContainMessage(chatPageAdmin1, DEMO1_1);
    assertChatNotification(chatPageAdmin2, false);

    // demo1, demo2, admin1 with group opened, admin2 with group closed, demo1 sends message
    chatPageDemo1.sendMessage(DEMO1_2);
    assertContainMessage(chatPageAdmin1, DEMO1_2);
    assertChatNotification(chatPageAdmin2, false);

    // demo1, demo2, admin2 with group opened, admin2 with group closed, admin1 sends message
    chatPageAdmin1.sendMessage(ADMIN1_1);
    assertChatNotification(chatPageAdmin2, false);
    assertContainMessage(chatPageDemo1, ADMIN1_1);
    assertContainMessage(chatPageDemo2, ADMIN1_1);

    // demo1, demo2, demo3, demo4, admin2 with group opened, admin2 with group closed, admin1 sends message
    launchBrowserAndGotoRelativeLink(NewDashboardPage2.PORTAL_HOME_PAGE_URL);
    ChatPage chatPageDemo3 = openChatGroup(TestAccount.DEMO_USER);
    launchBrowserAndGotoRelativeLink(NewDashboardPage2.PORTAL_HOME_PAGE_URL);
    ChatPage chatPageDemo4 = openChatGroup(TestAccount.DEMO_USER);
    chatPageAdmin1.sendMessage(ADMIN1_2);
    //TODO need to be fixed - Workaround for wait message render
    Sleeper.sleep(3000);
    assertContainMessage(chatPageDemo1, ADMIN1_2);
    assertContainMessage(chatPageDemo2, ADMIN1_2);
    assertContainMessage(chatPageDemo3, ADMIN1_2);
    assertContainMessage(chatPageDemo4, ADMIN1_2);
  }

  private void assertContainMessage(ChatPage chatPage, String message) {
    WaitHelper.assertTrueWithWait(() -> chatPage.getAllMessagesChatLog().contains(message));
  }

  private void assertChatNotification(ChatPage chatPage, boolean hasNotification) {
    WaitHelper.assertTrueWithWait(() -> hasNotification == chatPage.isNotificationBadgeChat());
    WaitHelper.assertTrueWithWait(() -> hasNotification == chatPage.isNotificationContactChat());
  }

  private ChatPage chatMessageInGroup(TestAccount chatUser, String chatMessage) {
    ChatPage chatPage = openChatGroup(chatUser);
    chatPage.sendMessage(chatMessage);
    return chatPage;
  }

  private ChatPage openChatGroup(TestAccount chatUser) {
    login(chatUser);
    redirectToRelativeLink(NewDashboardPage2.PORTAL_HOME_PAGE_URL);
    ChatPage chatPage = new NewDashboardPage2().getChat();
    chatPage.openFirstGroupChat();
    return chatPage;
  }

  private ChatPage createChatGroup(TestAccount creatorChatGroup, ExpressResponsible ...participants) {
    redirectToRelativeLink(createTestingCaseUrlForDefaultAdditionalCaseDetails);
    login(creatorChatGroup);
    redirectToRelativeLink(NewDashboardPage2.PORTAL_HOME_PAGE_URL);
    ChatPage chatPage = new NewDashboardPage2().getChat();
    // Create chat group via task
    TaskWidgetPage taskWidgetPage = NavigationHelper.navigateToTasList();
    TaskTemplatePage taskTemplatePage = taskWidgetPage.startTask(0);
    taskTemplatePage.clickTaskActionMenu();
    taskTemplatePage.clickChatGroup();
    if (participants.length != 0) {
      chatPage.addUserToChatGroup(Arrays.asList(participants));
    }
    taskTemplatePage.clickCreateGroupChatBtn();
    return chatPage;
  }

  private void joinChatGroupWhichAlreadyHadChatGroup(TestAccount userJoined) {
    login(userJoined);
    redirectToRelativeLink(NewDashboardPage2.PORTAL_HOME_PAGE_URL);
    TaskWidgetPage taskWidgetPage = NavigationHelper.navigateToTasList();
    taskWidgetPage.filterTasksInExpandedModeBy("Sick Leave Request Default Case Details Page");
    TaskTemplatePage taskTemplatePage = taskWidgetPage.startTask(0);
    taskTemplatePage.clickTaskActionMenu();
    taskTemplatePage.clickChatGroup();
    taskTemplatePage.joinProcessChatAlreadyCreated();
  }

  private ChatPage enableChatGroup() {
    updatePortalSetting(ENABLE_GROUP_CHAT_SETTING, "true");
    login(TestAccount.ADMIN_USER);
    return new ChatPage();
  }

  private ChatPage enableChatPrivate() {
    updatePortalSetting(ENABLE_PRIVATE_CHAT_SETTING, "true");
    login(TestAccount.ADMIN_USER);
    new NewDashboardPage2().getChat();
    return new ChatPage();
  }

}
