package com.axonivy.portal.selenium.page;

import org.openqa.selenium.By;
import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Selenide.$;


public class UserGuidePage extends TemplatePage {
  
  private static final String WELCOME_NEXT_BTN_ID = "welcome-portal-guide-component:welcome-guide-next";
  private static final String MAIN_MENU_NEXT_BTN_ID = "left-menu-guide-component:next";
  private static final String PROCESS_NEXT_BTN_ID = "process-widget-guide-component:next";
  private static final String TASK_NEXT_BTN_ID = "task-widget-guide-component:next";
  private static final String USERNAME_NEXT_BTN_ID = "setting-container-guide-component:next";
  private static final String STATISTIC_FINISH_BTN_ID = "statistic-widget-guide-component:finish";

  @Override
  protected String getLoadedLocator() {
    return "[id='" + WELCOME_NEXT_BTN_ID + "']";
  }

  public void nextToMainMenuGuide() {
    $(By.id(WELCOME_NEXT_BTN_ID)).shouldBe(getClickableCondition()).click();
    $(By.id(MAIN_MENU_NEXT_BTN_ID)).shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public void nextToProcessGuide() {
    $(By.id(MAIN_MENU_NEXT_BTN_ID)).shouldBe(getClickableCondition()).click();
    $(By.id(PROCESS_NEXT_BTN_ID)).shouldBe(appear, DEFAULT_TIMEOUT);
  }
  
  public void nextToTaskGuide() {
    $(By.id(PROCESS_NEXT_BTN_ID)).shouldBe(getClickableCondition()).click();
    $(By.id(TASK_NEXT_BTN_ID)).shouldBe(appear, DEFAULT_TIMEOUT);
  }
  
  public void nextToUsernameGuide() {
    $(By.id(TASK_NEXT_BTN_ID)).shouldBe(getClickableCondition()).click();
    $(By.id(USERNAME_NEXT_BTN_ID)).shouldBe(appear, DEFAULT_TIMEOUT);
  }
  
  public void nextToStatisticGuide() {
    $(By.id(USERNAME_NEXT_BTN_ID)).shouldBe(getClickableCondition()).click();
    $(By.id(STATISTIC_FINISH_BTN_ID)).shouldBe(appear, DEFAULT_TIMEOUT);
  }
  
  public void finishInStatisticGuide() {
    $(By.id(STATISTIC_FINISH_BTN_ID)).shouldBe(getClickableCondition()).click();
    $(By.className("no-task-message")).shouldBe(appear, DEFAULT_TIMEOUT);
  }
  
  public boolean isFinishButtonDisplay() {
    return isElementDisplayedById(STATISTIC_FINISH_BTN_ID);
  }

}
