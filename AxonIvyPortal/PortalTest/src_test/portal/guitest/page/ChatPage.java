package portal.guitest.page;

import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import portal.guitest.bean.ExpressResponsible;

public class ChatPage extends TemplatePage {

	private static final String DIALOG_TITLE = "toggle-chat-panel-command";

	public ChatPage() {
		waitForElementDisplayed(By.id(DIALOG_TITLE), true);
	}

	@Override
	protected String getLoadedLocator() {
		return "id('" + DIALOG_TITLE + "')";
	}

	@Override
	public boolean isDisplayed() {
		return findElementById(DIALOG_TITLE).isDisplayed();
	}

	public boolean isChatGroupDisplayed(String groupChatID) {
		return findElementByXpath("//div[contains(text(),'"+groupChatID+"')]").isDisplayed();
	}
	
	public void selectChatUser(String name) {
		findElementByXpath("//span[text()='" + name + "']").click();
	}

	public void selectChatGroup() {
		waitForElementDisplayed(By.xpath("//div[contains(text(),'Group chat of')]"), true);
		findElementByXpath("//div[contains(text(),'Group chat of')]").click();
	}

	public void sendMessage(String chatMessage) {
		waitForElementEnabled(By.id("message-input-field"), true, DEFAULT_TIMEOUT);
		WebElement input = findElementById("message-input-field");
		input.sendKeys(chatMessage);
		input.sendKeys(Keys.ENTER);
	}

	public String getAllMessagesChatLog() {
		waitForElementDisplayed(By.id("chat-message-list"), true, DEFAULT_TIMEOUT);
		return findListElementsByClassName("js-message").stream().map(WebElement::getText).collect(Collectors.joining(","));
	}

	public void addUserToChatGroup(List<ExpressResponsible> responsibles) {
		ensureNoBackgroundRequest();
		waitForElementDisplayed(By.id("chat-assignee-dialog"), true);
		for (ExpressResponsible responsible : responsibles) {
			chooseResponsible(responsible.getResponsibleName(), responsible.isGroup());
		}
		click(By.id("chat-assignee-selection-form:chat-group-create-button"));
		waitAjaxIndicatorDisappear();
	}

	private void chooseResponsible(String responsible, boolean isGroup) {
		if (isGroup) {
			selectCheckbox("chat-assignee-selection-form:chat-assignee-type:1");
			waitAjaxIndicatorDisappear();
			type(By.cssSelector("input[id$='selection_input']"), responsible);
			waitForElementDisplayed(By.id("chat-assignee-selection-form:chat-role-selection_panel"), true);
			click(By.xpath("//*[@id='chat-assignee-selection-form:chat-role-selection_panel']/ul/li/span"));
		} else {
			type(By.cssSelector("input[id$='selection_input']"), responsible);
			waitForElementDisplayed(By.id("chat-assignee-selection-form:chat-user-selection_panel"), true);
			click(By.xpath("//*[@id='chat-assignee-selection-form:chat-user-selection_panel']/table/tbody/tr"));
		}
		waitForElementEnabled(By.id("chat-assignee-selection-form:chat-add-assignee-button"), true, 5);
		click(By.id("chat-assignee-selection-form:chat-add-assignee-button"));
		waitAjaxIndicatorDisappear();
	}

	private void selectCheckbox(String forAttribute) {
		WebElement checkboxLabel = findElementByXpath(String.format("//label[@for='%s']", forAttribute));
		checkboxLabel.click();
		waitAjaxIndicatorDisappear();
		waitForElementDisplayed(By.cssSelector("input[id$='selection_input']"), true);
	}

	public String getAllParticipants() {
		waitForElementDisplayed(By.cssSelector("a[id$='show-participants-link']"), true);
		click(By.cssSelector("a[id$='show-participants-link']"));
		waitAjaxIndicatorDisappear();
		return GetUserParticipantsList() + "," + GetRolesParticipantsList();
	}

	private String GetUserParticipantsList() {
		waitForElementDisplayed(By.cssSelector("ul[id='user-participants-list'] li"), true);
		return findListElementsByCssSelector("ul[id='user-participants-list'] li").stream().map(WebElement::getText)
				.collect(Collectors.joining(","));
	}

	private String GetRolesParticipantsList() {
		waitForElementDisplayed(By.cssSelector("ul[id='roles-participants-list'] li ul li"), true);
		return findListElementsByCssSelector("ul[id='roles-participants-list'] li ul li").stream().map(WebElement::getText)
				.collect(Collectors.joining(","));
	}
	
	public void closeModalParticipants() {
		waitForElementDisplayed(By.cssSelector("button[class$='ui-confirmdialog-no']"), true);
		click(By.cssSelector("button[class$='ui-confirmdialog-no']"));
	}

	public void closeChatMessageList() {
		waitForElementDisplayed(By.cssSelector("span[class$='js-close-message-list']"), true);
		click(By.cssSelector("span[class$='js-close-message-list']"));
	}
	public boolean isNotificationBadgeChat() {
		waitForElementDisplayed(By.cssSelector("a[data-badge=' ']"), true);
		return isElementPresent(By.cssSelector("a[data-badge=' ']"));
	}
	
	public boolean isNotificationContactChat() {
		return !isElementPresent(By.cssSelector("span[class$='u-hiden']"));
	}
}
