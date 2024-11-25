package com.axonivy.portal.selenium.test;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WindowType;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.BaseTest;
import com.axonivy.portal.selenium.common.NavigationHelper;
import com.axonivy.portal.selenium.common.TestAccount;
import com.axonivy.portal.selenium.common.Variable;
import com.axonivy.portal.selenium.common.WaitHelper;
import com.axonivy.portal.selenium.page.ChatPage;
import com.axonivy.portal.selenium.page.NewDashboardPage;
import com.axonivy.portal.selenium.page.TaskTemplatePage;
import com.axonivy.portal.selenium.page.TopMenuTaskWidgetPage;

import ch.ivyteam.ivy.project.portal.test.Responsible;

@IvyWebTest(headless = false)
public class ChatTest extends BaseTest {
  private static final String ENABLE_PRIVATE_CHAT_SETTING = Variable.ENABLE_PRIVATE_CHAT.getKey();
  private static final String ENABLE_GROUP_CHAT_SETTING = Variable.ENABLE_GROUP_CHAT.getKey();
  private static final String CHAT_MESSAGE_USER_DEMO = "Hi i'm demo user";
  private static final String CHAT_MESSAGE_USER_ADMIN = "Hi i'm admin user";

  @Override
  @BeforeEach
  public void setup() {
    super.setup();
  }

  // Currently still not have any idea how to open two browser with difference session to test chat between multiple
  // user
  // Tests that still not implement
  // TODO chatGroupMultiTabs, chatGroupOnTwoInstanceOfBrowser

  @Test
  public void chatAddGroup() {
    Responsible chatUser1 = setResponsible(TestAccount.ADMIN_USER.getUsername(), false);
    ChatPage chatPage = enableChatGroup();
    createChatGroup(TestAccount.DEMO_USER);
    joinChatGroupWhichAlreadyHadChatGroup(TestAccount.ADMIN_USER);
    chatMessageInGroup(TestAccount.DEMO_USER, CHAT_MESSAGE_USER_DEMO);

    openNewTabOrWindow(WindowType.WINDOW);
    launchBrowserAndGotoRelativeLink(NewDashboardPage.PORTAL_HOME_PAGE_URL);
    ChatPage chatPage2 = chatMessageInGroup(TestAccount.ADMIN_USER, CHAT_MESSAGE_USER_ADMIN);

    openNewTabOrWindow(WindowType.WINDOW);
    launchBrowserAndGotoRelativeLink(NewDashboardPage.PORTAL_HOME_PAGE_URL);

    Responsible chatGroupEveryBody = setResponsible("Everybody", true);
    createChatGroup(TestAccount.GUEST_USER, chatUser1, chatGroupEveryBody);

    login(TestAccount.ADMIN_USER);
    assertEquals(2, chatPage2.refreshAndCountGroupChat());

    login(TestAccount.DEMO_USER);
    assertEquals(2, chatPage.refreshAndCountGroupChat());
  }

  // @Test
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
    assertChatNotification(chatPage2, true);

    chatPage2.openFirstGroupChat();
    chatPage2.sendMessage("from 2 to 1");

    chatPage = getChatGroup(TestAccount.DEMO_USER);
    assertContainMessage(chatPage, "from 2 to 1");
    chatPage2 = getChatGroup(TestAccount.ADMIN_USER);
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
    new NewDashboardPage().getChat().isNotificationContactChat();
  }

  private ChatPage createChatGroup(TestAccount creatorChatGroup, Responsible... participants) {
    redirectToRelativeLink(createTestingCaseUrlForDefaultAdditionalCaseDetails);
    login(creatorChatGroup);
    redirectToRelativeLink(NewDashboardPage.PORTAL_HOME_PAGE_URL);
    ChatPage chatPage = new NewDashboardPage().getChat();
    // Create chat group via task
    NavigationHelper.navigateToTaskList();
    TopMenuTaskWidgetPage taskWidget = new TopMenuTaskWidgetPage();
    TaskTemplatePage taskTemplatePage = taskWidget.startTaskByIndex(0);
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
    NavigationHelper.navigateToTaskList();
    TopMenuTaskWidgetPage taskWidget = new TopMenuTaskWidgetPage();
    taskWidget.setInputForQuickSearch("Sick Leave Request Default Case Details Page");
    TaskTemplatePage taskTemplatePage = taskWidget.startTaskByIndex(0);
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
    redirectToRelativeLink(NewDashboardPage.PORTAL_HOME_PAGE_URL);
    return new NewDashboardPage().getChat();
  }

  private void assertContainMessage(ChatPage chatPage, String message) {
    WaitHelper.assertTrueWithWait(() -> chatPage.getAllMessagesChatLog().contains(message));
  }

  private void assertChatNotification(ChatPage chatPage, boolean hasNotification) {
    chatPage.isNotificationBadgeChat();
    chatPage.isNotificationContactChat();
  }

}
