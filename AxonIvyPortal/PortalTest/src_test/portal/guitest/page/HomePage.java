package portal.guitest.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import portal.guitest.common.WaitHelper;


public class HomePage extends TemplatePage {

	public final static String PORTAL_HOME_PAGE_URL = "portalTemplate/1549F58C18A6C562/DefaultApplicationHomePage.ivp";
	public final static String PORTAL_EXAMPLES_HOME_PAGE_URL = "portal-developer-examples/164211E97C598DAA/DefaultApplicationHomePage.ivp";
	public final static String PORTAL_EXAMPLES_PROCESS_CHAIN = "portal-developer-examples/164DB506D12B25CF/showSampleProcessChain.ivp";
	private final static String TASK_SWITCH_MODE_BTN_LOCATOR = "id('task-widget:task-list-link:task-list-link')";
	private static final String SHOW_ALL_PROCESSES_LINK_ID = "process-widget:process-link:process-link-label";
	private static final String SHOW_TASK_LIST_LINK_ID = "task-widget:task-list-link:task-list-link-label";
	private static final String SHOW_ALL_CHARTS_LINK_ID = "statistics-widget:statistic-link:statistic-link-label";

	@Override
	protected String getLoadedLocator() {
		return TASK_SWITCH_MODE_BTN_LOCATOR;
	}

	public TaskWidgetPage getTaskWidget() {
		return new TaskWidgetPage();
	}

	public ProcessWidgetPage getProcessWidget() {
		return new ProcessWidgetPage();
	}

	public boolean isShowAllProcessesLinkDisplayed() {
		return isElementDisplayedById(SHOW_ALL_PROCESSES_LINK_ID);
	}

	public boolean isShowTaskListLinkDisplayed() {
		return isElementDisplayedById(SHOW_TASK_LIST_LINK_ID);
	}

	public boolean isShowAllChartsLinkDisplayed() {
		return isElementDisplayedById(SHOW_ALL_CHARTS_LINK_ID);
	}
	
	public void waitForStatisticRendered() {
    WaitHelper.assertTrueWithRefreshPage(this, () -> {
      waitForElementDisplayed(By.cssSelector("a[class$='chart-info']"), true, 2);
      return true;
    });
	}
	
	public String getAnnouncementMessage() {
		waitForElementDisplayed(By.cssSelector("div[class*='announcement-message-customizable']"), true);
		return driver.findElement(By.cssSelector("div[class*='announcement-message-customizable']")).getText();
	}

	public boolean isAnnouncementMessageNotDisplayed() {
		if (driver.findElements(By.cssSelector("div[class*='announcement-message-customizable']")).size() == 0) {
			return true;
		}
		return false;
	}

	public boolean isChatDisplayed() {
		if (driver.findElements(By.id("toggle-chat-panel-command")).size() == 0) {
			return false;
		}
		return true;
	}

	public String getEnviromentInfo() {
		waitForElementDisplayed(By.cssSelector("span[id$='server-infor']"), true, 5);
		return findElementByCssSelector("span[id$='server-infor']").getText();
	}
	
	public WebElement getStatisticWidgetElement() {
	  return findElementById("statistics-widget");
	}
	
	public WebElement getProcessWidgetElement() {
	  return findElementById("process-widget");
	}
	
	public WebElement getTaskWidgetElement() {
	  return findElementById("task-widget");
	}
	
}
