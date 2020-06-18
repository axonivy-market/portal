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
    return findElementByXpath("//span[contains(text(),'" + groupChatID + "')]").isDisplayed();
  }

  public void selectChatUser(String name) {
    waitForElementDisplayed(By.xpath("//span[text()='" + name + "']"), true);
    click(findElementByXpath("//span[text()='" + name + "']"));
  }

  public void selectChatGroup() {
    waitForElementDisplayedByCssSelector("span.js-group-card-name[title$='Portal Demo User']", 5);
    click(findElementByCssSelector("span.js-group-card-name[title$='Portal Demo User']"));
  }

  public void sendMessage(String chatMessage) {
    waitForElementExisted("textarea[id='message-input-field']", true, DEFAULT_TIMEOUT);
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
    waitForElementDisplayed(By.id("chat-assignee-selection-form:chat-group-create-button"), false, DEFAULT_TIMEOUT);
    waitAjaxIndicatorDisappear();
  }

  private void chooseResponsible(String responsible, boolean isGroup) {
    if (isGroup) {
      selectRoleAssigneeCheckbox();
      waitAjaxIndicatorDisappear();
      type(By.cssSelector("input[id$='selection_input']"), responsible);
      waitForElementDisplayed(By.id("chat-assignee-selection-form:chat-role-selection_panel"), true);
      click(By.xpath("//*[@id='chat-assignee-selection-form:chat-role-selection_panel']/ul/li/span"));
    } else {
      type(By.cssSelector("input[id$='selection_input']"), responsible);
      waitForElementDisplayed(By.cssSelector("span[id$='chat-user-selection_panel']"), true);
      click(By.xpath(
          "//*[@id='chat-assignee-selection-form:chat-user-selection-component:chat-user-selection_panel']/table/tbody/tr"));
    }
    waitForElementEnabled(By.id("chat-assignee-selection-form:chat-add-assignee-button"), true, 5);
    click(By.id("chat-assignee-selection-form:chat-add-assignee-button"));
    waitAjaxIndicatorDisappear();
  }

  private void selectRoleAssigneeCheckbox() {
    WebElement checkboxLabel =
        findElementByXpath(String.format("//label[@for='%s']", "chat-assignee-selection-form:chat-assignee-type:1"));
    click(checkboxLabel);
    waitAjaxIndicatorDisappear();
    waitForElementDisplayed(By.cssSelector("input[id$='role-selection_input']"), true);
  }

  public String getAllParticipants() {
    waitForElementDisplayed(By.cssSelector("a[id$='show-participants-link']"), true);
    click(By.cssSelector("a[id$='show-participants-link']"));
    waitAjaxIndicatorDisappear();
    return GetUserParticipantsList() + "," + GetRolesParticipantsList();
  }

  private String GetUserParticipantsList() {
    waitForElementDisplayed(By.id("participants-list-dialog"), true);
    return findListElementsByCssSelector("ul[id='user-participants-list'] li").stream().map(WebElement::getText)
        .collect(Collectors.joining(","));
  }

  private String GetRolesParticipantsList() {
    waitForElementDisplayed(By.id("participants-list-dialog"), true);
    return findListElementsByCssSelector("ul[id='roles-participants-list'] li ul li").stream().map(WebElement::getText)
        .collect(Collectors.joining(","));
  }

  public void closeModalParticipants() {
    waitForElementDisplayed(By.xpath("//div[@id='participants-list-dialog']//span[contains(text(),'Close')]"), true);
    click(By.xpath("//div[@id='participants-list-dialog']//span[contains(text(),'Close')]"));
  }

  public void closeChatMessageList() {
    waitForElementDisplayed(By.cssSelector("span[class$='js-close-message-list']"), true);
    click(By.cssSelector("span[class$='js-close-message-list']"));
  }

  public boolean isNotificationBadgeChat() {
    waitForElementDisplayed(By.cssSelector("a.notification-badge"), true);
    return isElementPresent(By.cssSelector("a[data-badge=' ']"));
  }

  public boolean isNotificationContactChat() {
    return isElementPresent(By.cssSelector("span[class$='js-notification']"));
  }
}
