package com.axonivy.portal.selenium.accessibility;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.cssClass;
import static com.codeborne.selenide.Condition.focused;
import static com.codeborne.selenide.Condition.hidden;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.actions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.BaseTest;
import com.axonivy.portal.selenium.page.NewDashboardPage;
import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

@IvyWebTest
public class ActionMenuKeyboardTest extends BaseTest {

  @Override
  @BeforeEach
  public void setup() {
    super.setup();
    redirectToRelativeLink(createTestingTasksUrl);
    redirectToNewDashBoard();
    new NewDashboardPage().waitForCaseWidgetLoaded();
  }

  @Test
  public void taskActionMenuSupportsMenuKeyboard() {
    assertMenuKeyboard("button[id$='dashboard-task-side-steps-menu']");
  }

  @Test
  public void caseActionMenuSupportsMenuKeyboard() {
    assertMenuKeyboard("button[id$='dashboard-case-side-steps-menu']");
  }

  private void assertMenuKeyboard(String triggerSelector) {
    SelenideElement trigger = $$(triggerSelector).filter(visible).first().shouldBe(visible, DEFAULT_TIMEOUT);

    ElementsCollection items = openMenu(trigger);
    items.first().shouldBe(focused);
    pressKey(Keys.ARROW_DOWN);
    items.get(1).shouldBe(focused);
    pressKey(Keys.ARROW_UP);
    items.first().shouldBe(focused);
    pressKey(Keys.ARROW_UP);
    items.last().shouldBe(focused);
    pressKey(Keys.HOME);
    items.first().shouldBe(focused);
    pressKey(Keys.END);
    items.last().shouldBe(focused);

    pressKey(Keys.ESCAPE);
    items.first().shouldBe(hidden, DEFAULT_TIMEOUT);
    trigger.shouldBe(focused, DEFAULT_TIMEOUT);

    items = openMenu(trigger);
    pressKey(Keys.TAB);
    items.first().shouldBe(hidden, DEFAULT_TIMEOUT);
    trigger.shouldBe(focused, DEFAULT_TIMEOUT);
  }

  private ElementsCollection openMenu(SelenideElement trigger) {
    trigger.click();
    SelenideElement panel = $$("div.portal-action-menu").filter(visible).first().shouldBe(visible, DEFAULT_TIMEOUT);
    panel.$(".ui-overlaypanel-content").shouldHave(attribute("role", "menu"), DEFAULT_TIMEOUT);
    return panel.$$("a.portal-action-menu-item[role='menuitem']").filter(visible).exclude(cssClass("ui-state-disabled"))
        .shouldHave(CollectionCondition.sizeGreaterThan(1), DEFAULT_TIMEOUT);
  }

  private void pressKey(Keys key) {
    actions().sendKeys(key).perform();
  }
}
