package portal.guitest.test;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import portal.guitest.bean.ExpressResponsible;
import portal.guitest.common.BaseTest;
import portal.guitest.common.TestAccount;
import portal.guitest.page.AdminSettingsPage;
import portal.guitest.page.ChatPage;
import portal.guitest.page.HomePage;
import portal.guitest.page.LoginPage;
import portal.guitest.page.TaskTemplatePage;
import portal.guitest.page.TaskWidgetPage;

public class ChatTest extends BaseTest {
	private AdminSettingsPage adminSettingsPage;
	private static final String CHAT_MESSAGE_USER_DEMO = "Hi i'm demo user";
	private static final String CHAT_MESSAGE_USER_ADMIN = "Hi i'm admin user";
	ExpressResponsible chatUser1 = new ExpressResponsible(TestAccount.ADMIN_USER.getUsername(), false);
	ExpressResponsible chatGroup1 = new ExpressResponsible("Human resources department", true);

	@Override
	@Before
	public void setup() {
		super.setup();
		navigateToUrl(HomePage.PORTAL_HOME_PAGE_URL);
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
		new HomePage().getChat();
		chatPage.selectChatGroup();
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

		assertTrue("Chat message demo", chatPage.getAllMessagesChatLog().contains(CHAT_MESSAGE_USER_DEMO));
		assertTrue("Chat message admin", chatPage.getAllMessagesChatLog().contains(CHAT_MESSAGE_USER_ADMIN));

		login(TestAccount.DEMO_USER);
		assertTrue(chatPage.isNotificationBadgeChat());
		assertTrue(new HomePage().getChat().isNotificationContactChat());
	}

	private void chatMessageInGroup(TestAccount chatUser, String chatMessage) {
		redirectToRelativeLink(HomePage.PORTAL_HOME_PAGE_URL);
		login(chatUser);
		ChatPage chatPage = new HomePage().getChat();
		chatPage.selectChatGroup();
		chatPage.sendMessage(chatMessage);
	}

	private void createChatGroupWithPredifinedGroup(boolean isPredifinedGroup, TestAccount creatorChatGroup) {
		if (isPredifinedGroup) {
			navigateToUrl(createTestingTasksUrl);
		} else {
			navigateToUrl(createTestingCaseUrlForDefaultAdditionalCaseDetails);
		}
		redirectToRelativeLink(HomePage.PORTAL_HOME_PAGE_URL);
		login(creatorChatGroup);
		// Create chat group via task
		TaskWidgetPage taskWidgetPage = new TaskWidgetPage();
		TaskTemplatePage taskTemplatePage = taskWidgetPage.startTask(0);
		taskTemplatePage.clickChatGroup();
	}

	private void joinChatGroupWhichAlreadyHadChatGroup(TestAccount userJoined) {
		login(userJoined);
		TaskWidgetPage taskWidgetPage = new TaskWidgetPage();
		TaskTemplatePage taskTemplatePage = taskWidgetPage.startTask(0);
		taskTemplatePage.clickChatGroup();
		taskTemplatePage.joinProcessChatAlreadyCreated();
	}

	private ChatPage enableChatGroup() {
		new LoginPage(TestAccount.ADMIN_USER).login();
		adminSettingsPage = new HomePage().openAdminSettings();
		adminSettingsPage.setChatGroup();
		return new ChatPage();
	}

	private ChatPage enableChatPrivate() {
		new LoginPage(TestAccount.ADMIN_USER).login();
		adminSettingsPage = new HomePage().openAdminSettings();
		adminSettingsPage.setChatPrivate();
		new HomePage().getChat();
		return new ChatPage();
	}

}
