package com.axonivy.portal.selenium.page;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Selenide.$;

import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

import com.codeborne.selenide.ScrollIntoViewOptions;
import com.codeborne.selenide.ScrollIntoViewOptions.Block;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;

public class ReorderDashboardPage extends TemplatePage {
  @Override
  protected String getLoadedLocator() {
    return "i.dashboard-icon-drag-drop";
  }

  public void toggleVisibility(String dashboardName) {
    SelenideElement toggleVisibility = findPrivateDashboardRowByName(dashboardName).$("a[id$='toggle-visibility']");
    toggleVisibility.click();
    toggleVisibility.shouldBe(disappear, DEFAULT_TIMEOUT);
  }

  public void reorderPrivateDashboard(String fromDashboardName, String toDashboardName) {
    var toRow = findPrivateDashboardRowByName(toDashboardName).$("i.ti-menu-2");
    var fromRow = findPrivateDashboardRowByName(fromDashboardName).$("i.ti-menu-2");
    dragAndDropTo(toRow, fromRow);
  }

  private void dragAndDropTo(SelenideElement toRow, SelenideElement fromRow) {
    SelenideElement targetCssSelector = $("[id$='" + toRow.getAttribute("id") + "']");
    // Center both rows in the viewport first: a native Actions move computes an absolute on-screen point
    // (here the target's position plus a 50,20 offset), and that throws MoveTargetOutOfBoundsException if
    // the row sits near the bottom/edge of a short viewport instead of being scrolled into comfortable view.
    fromRow.scrollIntoView(ScrollIntoViewOptions.instant().block(Block.center));
    targetCssSelector.scrollIntoView(ScrollIntoViewOptions.instant().block(Block.center));
    Actions builder = new Actions(WebDriverRunner.getWebDriver());
    Action dragAndDrop = builder.clickAndHold(fromRow).pause(500)
        .moveToElement(targetCssSelector, 50, 20).pause(500).release(targetCssSelector)
        .pause(500)
        .build();
    dragAndDrop.perform();

  }

  public void reorderPublicDashboard(String fromDashboardName, String toDashboardName) {
    var toRow = findPublicDashboardRowByName(toDashboardName).$("i.ti-menu-2");
    var fromRow = findPublicDashboardRowByName(fromDashboardName).$("i.ti-menu-2");
    dragAndDropTo(toRow, fromRow);
  }

  public void saveSetting() {
    $("button[id$='save-settings']").shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
    $("[id$='dashboard-configuration-content']").shouldBe(appear, DEFAULT_TIMEOUT);
  }

  private SelenideElement findPublicDashboardRowByName(String dashboardName) {
    return $("[id$=':dashboard-table_data']").shouldBe(appear, DEFAULT_TIMEOUT).$$("tr.ui-widget-content")
        .asFixedIterable().stream()
        .filter(row -> row.$("[id$=':dashboard-title-group']").getText().equals(dashboardName)).findFirst().get();
  }

  private SelenideElement findPrivateDashboardRowByName(String dashboardName) {
    $("[id$=':reorder-dashboard-form:dashboard-table']").shouldHave(appear, DEFAULT_TIMEOUT);
    return $("[id$=':dashboard-table_data']").shouldBe(appear, DEFAULT_TIMEOUT)
        .$$("tr.ui-widget-content").asFixedIterable()
        .stream().filter(row -> row.$("span[id$=':dashboard-title']").getText().equals(dashboardName)).findFirst()
        .get();
  }

}
