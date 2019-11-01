package portal.guitest.test;

import static junit.framework.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import portal.guitest.bean.ExpressResponsible;
import portal.guitest.common.BaseTest;
import portal.guitest.common.TestAccount;
import portal.guitest.page.AdminSettingsPage;
import portal.guitest.page.ChatPage;
import portal.guitest.page.HomePage;
import portal.guitest.page.TaskTemplatePage;
import portal.guitest.page.TaskWidgetPage;

@Ignore
public class ChatTest extends BaseTest {
	private AdminSettingsPage adminSettingsPage;
	private static final String CHAT_MESSAGE_USER_DEMO = "Hi i'm demo user";
	private static final String CHAT_MESSAGE_USER_ADMIN = "Hi i'm admin user";
	ExpressResponsible chatUser1 = new ExpressResponsible(TestAccount.ADMIN_USER.getUsername(), false);
	ExpressResponsible chatGroup1 = new ExpressResponsible("Human resources department", true);
	ExpressResponsible chatGroupEveryBody = new ExpressResponsible("Everybody", true);

	@Override
	@Before
	public void setup() {
		super.setup();
		redirectToRelativeLink(HomePage.PORTAL_HOME_PAGE_URL);
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

		assertTrue(chatPage2.isChatGroupDisplayed("Group chat of: Portal Guest User"));
		assertTrue(chatPage.isChatGroupDisplayed("Group chat of: Portal Guest User"));
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
		assertTrue(chatPage2.isNotificationBadgeChat());
		assertTrue(chatPage2.isNotificationContactChat());
		chatPage2.selectChatGroup();
		chatPage2.sendMessage("from 2 to 1");
		assertTrue("from 2 to 1", chatPage.getAllMessagesChatLog().contains("from 2 to 1"));
		assertTrue("from 1 to 2", chatPage2.getAllMessagesChatLog().contains("from 1 to 2"));
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

	private ChatPage chatMessageInGroup(TestAccount chatUser, String chatMessage) {
		redirectToRelativeLink(HomePage.PORTAL_HOME_PAGE_URL);
		login(chatUser);
		ChatPage chatPage = new HomePage().getChat();
		chatPage.selectChatGroup();
		chatPage.sendMessage(chatMessage);
		return chatPage;
	}

	private ChatPage createChatGroupWithPredifinedGroup(boolean isPredifinedGroup, TestAccount creatorChatGroup) {
		if (isPredifinedGroup) {
			redirectToRelativeLink(createTestingTasksUrl);
		} else {
			redirectToRelativeLink(createTestingCaseUrlForDefaultAdditionalCaseDetails);
		}
		redirectToRelativeLink(HomePage.PORTAL_HOME_PAGE_URL);
		login(creatorChatGroup);
		ChatPage chatPage = new HomePage().getChat();
		// Create chat group via task
		TaskWidgetPage taskWidgetPage = new TaskWidgetPage();
		TaskTemplatePage taskTemplatePage = taskWidgetPage.startTask(0);
		taskTemplatePage.clickChatGroup();
		return chatPage;
	}

	private void joinChatGroupWhichAlreadyHadChatGroup(TestAccount userJoined) {
		login(userJoined);
		TaskWidgetPage taskWidgetPage = new TaskWidgetPage();
		TaskTemplatePage taskTemplatePage = taskWidgetPage.startTask(0);
		taskTemplatePage.clickChatGroup();
		taskTemplatePage.joinProcessChatAlreadyCreated();
	}

	private ChatPage enableChatGroup() {
	  login(TestAccount.ADMIN_USER);
		adminSettingsPage = new HomePage().openAdminSettings();
		adminSettingsPage.setChatGroup();
		return new ChatPage();
	}

	private ChatPage enableChatPrivate() {
	  login(TestAccount.ADMIN_USER);
		adminSettingsPage = new HomePage().openAdminSettings();
		adminSettingsPage.setChatPrivate();
		new HomePage().getChat();
		return new ChatPage();
	}

}
