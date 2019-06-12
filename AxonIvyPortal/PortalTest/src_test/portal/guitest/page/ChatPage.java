package portal.guitest.page;

import org.openqa.selenium.By;

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

}
