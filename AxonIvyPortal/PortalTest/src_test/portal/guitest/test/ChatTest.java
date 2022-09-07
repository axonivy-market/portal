package portal.guitest.test;

import static org.junit.Assert.assertTrue;
import static portal.guitest.common.Variable.ENABLE_GROUP_CHAT;
import static portal.guitest.common.Variable.ENABLE_PRIVATE_CHAT;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import portal.guitest.bean.ExpressResponsible;
import portal.guitest.common.BaseTest;
import portal.guitest.common.Sleeper;
import portal.guitest.common.TestAccount;
import portal.guitest.common.WaitHelper;
import portal.guitest.page.ChatPage;
import portal.guitest.page.HomePage;
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
    createChatGroupWithPredifinedGroup(true, TestAccount.DEMO_USER);
    joinChatGroupWhichAlreadyHadChatGroup(TestAccount.ADMIN_USER);
    chatMessageInGroup(TestAccount.DEMO_USER, CHAT_MESSAGE_USER_DEMO);
    launchBrowserAndGotoRelativeLink(HomePage.PORTAL_HOME_PAGE_URL);
    ChatPage chatPage2 = chatMessageInGroup(TestAccount.ADMIN_USER, CHAT_MESSAGE_USER_ADMIN);

    launchBrowserAndGotoRelativeLink(HomePage.PORTAL_HOME_PAGE_URL);
    ChatPage chatPage3 = createChatGroupWithPredifinedGroup(false, TestAccount.GUEST_USER);
    chatPage3.addUserToChatGroup(Arrays.asList(chatUser1, chatGroupEveryBody));

    assertTrue(chatPage2.isChatGroupDisplayed("Leave Request"));
    assertTrue(chatPage.isChatGroupDisplayed("Leave Request"));
  }

  @Test
  public void chatGroupOnTwoInstanceOfBrowser() {
    ChatPage chatPage = enableChatGroup();
    createChatGroupWithPredifinedGroup(true, TestAccount.DEMO_USER);
    joinChatGroupWhichAlreadyHadChatGroup(TestAccount.ADMIN_USER);
    chatMessageInGroup(TestAccount.DEMO_USER, CHAT_MESSAGE_USER_DEMO);
    launchBrowserAndGotoRelativeLink(HomePage.PORTAL_HOME_PAGE_URL);
    ChatPage chatPage2 = chatMessageInGroup(TestAccount.ADMIN_USER, CHAT_MESSAGE_USER_ADMIN);
    chatPage2.closeChatMessageList();
    chatPage.sendMessage("from 1 to 2");
    assertChatNotification(chatPage2, true);
    chatPage2.selectPortalDemoUserChatGroup();
    chatPage2.sendMessage("from 2 to 1");
    assertContainMessage(chatPage, "from 2 to 1");
    assertContainMessage(chatPage2, "from 1 to 2");
  }

  @Test
  public void chatDisplay() {
    enableChatGroup();
    assertTrue("Chat Shown", new HomePage().isChatDisplayed());
  }

  @Test
  public void chatPrivate() {
    ChatPage chatPage = enableChatPrivate();
    chatPage.selectChatUser("demo");
    chatPage.sendMessage(CHAT_MESSAGE_USER_ADMIN);

    login(TestAccount.DEMO_USER);
    new HomePage().getChat();
    chatPage.selectChatUser("admin");
    chatPage.sendMessage(CHAT_MESSAGE_USER_DEMO);
  }

  @Test
  public void chatGroupWithPredifinedGroup() {
    enableChatGroup();
    createChatGroupWithPredifinedGroup(true, TestAccount.DEMO_USER);
  }

  @Test
  public void chatGroupWithOutPredifinedGroup() {
    ChatPage chatPage = enableChatGroup();
    createChatGroupWithPredifinedGroup(false, TestAccount.DEMO_USER);
    chatPage.addUserToChatGroup(Arrays.asList(chatUser1, chatGroup1));

    redirectToRelativeLink(HomePage.PORTAL_HOME_PAGE_URL);
    Sleeper.sleep(1000); // chat groups are not loading at the beginning, wait a bit for Firefox browser
    new HomePage().getChat();
    chatPage.selectPortalDemoUserChatGroup();
    chatPage.getAllParticipants();

    assertTrue("Admin in chat group", chatPage.getAllParticipants().contains(TestAccount.ADMIN_USER.getUsername()));
    assertTrue("Demo in chat group", chatPage.getAllParticipants().contains(TestAccount.DEMO_USER.getUsername()));
    chatPage.closeModalParticipants();
    chatPage.closeChatMessageList();
  }

  @Test
  public void joinChatGroupAlreadyCreated() {
    ChatPage chatPage = enableChatGroup();
    createChatGroupWithPredifinedGroup(true, TestAccount.DEMO_USER);
    joinChatGroupWhichAlreadyHadChatGroup(TestAccount.ADMIN_USER);
    chatMessageInGroup(TestAccount.DEMO_USER, CHAT_MESSAGE_USER_DEMO);
    chatMessageInGroup(TestAccount.ADMIN_USER, CHAT_MESSAGE_USER_ADMIN);

    assertContainMessage(chatPage, CHAT_MESSAGE_USER_DEMO);
    assertContainMessage(chatPage, CHAT_MESSAGE_USER_ADMIN);

    login(TestAccount.DEMO_USER);
    WaitHelper.assertTrueWithWait(() -> new HomePage().getChat().isNotificationContactChat());
  }

  @Test
  public void chatGroupMultiTabs() {
    enableChatGroup();
    createChatGroupWithPredifinedGroup(true, TestAccount.DEMO_USER);
    ChatPage chatPageDemo1 = new ChatPage();
    launchBrowserAndGotoRelativeLink(HomePage.PORTAL_HOME_PAGE_URL);
    ChatPage chatPageDemo2 = openChatGroup(TestAccount.DEMO_USER);
    launchBrowserAndGotoRelativeLink(HomePage.PORTAL_HOME_PAGE_URL);
    joinChatGroupWhichAlreadyHadChatGroup(TestAccount.ADMIN_USER);
    ChatPage chatPageAdmin1 = new ChatPage();
    launchBrowserAndGotoRelativeLink(HomePage.PORTAL_HOME_PAGE_URL);
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
    chatPageAdmin1.selectPortalDemoUserChatGroup();
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
    launchBrowserAndGotoRelativeLink(HomePage.PORTAL_HOME_PAGE_URL);
    ChatPage chatPageDemo3 = openChatGroup(TestAccount.DEMO_USER);
    launchBrowserAndGotoRelativeLink(HomePage.PORTAL_HOME_PAGE_URL);
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
    redirectToRelativeLink(HomePage.PORTAL_HOME_PAGE_URL);
    ChatPage chatPage = new HomePage().getChat();
    chatPage.selectPortalDemoUserChatGroup();
    return chatPage;
  }

  private ChatPage createChatGroupWithPredifinedGroup(boolean isPredifinedGroup, TestAccount creatorChatGroup) {
    if (isPredifinedGroup) {
      redirectToRelativeLink(createTestingTasksUrl);
    } else {
      redirectToRelativeLink(createTestingCaseUrlForDefaultAdditionalCaseDetails);
    }
    login(creatorChatGroup);
    redirectToRelativeLink(HomePage.PORTAL_HOME_PAGE_URL);
    ChatPage chatPage = new HomePage().getChat();
    // Create chat group via task
    TaskWidgetPage taskWidgetPage = new TaskWidgetPage();
    TaskTemplatePage taskTemplatePage = taskWidgetPage.startTask(0);
    taskTemplatePage.clickTaskActionMenu();
    taskTemplatePage.clickChatGroup(isPredifinedGroup);
    return chatPage;
  }

  private void joinChatGroupWhichAlreadyHadChatGroup(TestAccount userJoined) {
    login(userJoined);
    redirectToRelativeLink(HomePage.PORTAL_HOME_PAGE_URL);
    TaskWidgetPage taskWidgetPage = new TaskWidgetPage();
    TaskTemplatePage taskTemplatePage = taskWidgetPage.startTask(0);
    taskTemplatePage.clickTaskActionMenu();
    taskTemplatePage.clickChatGroup(false);
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
    new HomePage().getChat();
    return new ChatPage();
  }

}
