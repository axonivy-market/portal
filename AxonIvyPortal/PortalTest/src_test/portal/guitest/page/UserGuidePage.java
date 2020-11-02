package portal.guitest.page;

import org.openqa.selenium.By;

public class UserGuidePage extends TemplatePage {
  
  private static final String WELCOME_NEXT_BTN_ID = "welcome-portal-guide-component:welcome-guide-next";
  private static final String MAIN_MENU_NEXT_BTN_ID = "left-menu-guide-component:next";
  private static final String PROCESS_NEXT_BTN_ID = "process-widget-guide-component:next";
  private static final String TASK_NEXT_BTN_ID = "task-widget-guide-component:next";
  private static final String USERNAME_NEXT_BTN_ID = "setting-container-guide-component:next";
  private static final String STATISTIC_FINISH_BTN_ID = "statistic-widget-guide-component:finish";

  @Override
  protected String getLoadedLocator() {
    return "id('" + WELCOME_NEXT_BTN_ID + "')";
  }

  public void nextToMainMenuGuide() {
    click(By.id(WELCOME_NEXT_BTN_ID));
    waitForElementDisplayed(By.id(MAIN_MENU_NEXT_BTN_ID), true);
  }

  public void nextToProcessGuide() {
    click(By.id(MAIN_MENU_NEXT_BTN_ID));
    waitForElementDisplayed(By.id(PROCESS_NEXT_BTN_ID), true);
  }
  
  public void nextToTaskGuide() {
    click(By.id(PROCESS_NEXT_BTN_ID));
    waitForElementDisplayed(By.id(TASK_NEXT_BTN_ID), true);
  }
  
  public void nextToUsernameGuide() {
    click(By.id(TASK_NEXT_BTN_ID));
    waitForElementDisplayed(By.id(USERNAME_NEXT_BTN_ID), true);
  }
  
  public void nextToStatisticGuide() {
    click(By.id(USERNAME_NEXT_BTN_ID));
    waitForElementDisplayed(By.id(STATISTIC_FINISH_BTN_ID), true);
  }
  
  public void finishInStatisticGuide() {
    click(By.id(STATISTIC_FINISH_BTN_ID));
    waitForElementDisplayed(By.className("no-task-message"), true);
  }
  
  public boolean isFinishButtonDisplay() {
    return isElementDisplayedById(STATISTIC_FINISH_BTN_ID);
  }
}
