package com.axonivy.portal.selenium.page;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebElementCondition;

public class DashboardNewsWidgetPage extends TemplatePage {

  private static final String MANAGE_NEWS_TABVIEW_FORMAT = "[id$=':manage-news-tabview:%s:%s']";
  private static final String NEWS_ITEM_ATTR_FORMAT = "[id$=':%d:%s']";

  private String widgetName;

  public DashboardNewsWidgetPage(String widgetName) {
    super();
    this.widgetName = widgetName;
  }

  @Override
  protected String getLoadedLocator() {
    return ".news-widget__content-panel";
  }

  public ElementsCollection expand() {
    $$("div.widget__header").filter(text(widgetName)).first().shouldBe(appear, DEFAULT_TIMEOUT);
    return $$("div.widget__header").filter(text(widgetName));
  }

  public void openAddNewsFeedItemDialog() {
    $(".news-widget__content-panel").shouldBe(appear, DEFAULT_TIMEOUT).$("a[id$=':add-news-button']")
        .shouldBe(getClickableCondition()).click();
    manageNewsDialogShouldBe(appear).$("[id$=':news-icon:awesome-icon-selection']").shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public String enterNewsItemData(String languageTag, String icon, String title, String content) {
    // Select language
    var tabIndex = selectNewsLanguage(languageTag);
    // News Icon
    selectNewsIcon(icon, tabIndex);
    // News title
    enterNewsTitle(title, tabIndex);
    // News content
    enterNewsContent(content, tabIndex);
    return tabIndex;
  }

  private void enterNewsContent(String content, String tabIndex) {
    $(String.format(MANAGE_NEWS_TABVIEW_FORMAT, tabIndex, "news-content_editor")).$(".ql-editor")
        .shouldBe(appear, DEFAULT_TIMEOUT).setValue(content);
  }

  private void enterNewsTitle(String title, String tabIndex) {
    SelenideElement newsTitleInput = $("input" + String.format(MANAGE_NEWS_TABVIEW_FORMAT, tabIndex, "news-title"))
        .shouldBe(appear, DEFAULT_TIMEOUT);
    newsTitleInput.clear();
    newsTitleInput.sendKeys(title);
  }

  private void selectNewsIcon(String icon, String tabIndex) {
    $(String.format(MANAGE_NEWS_TABVIEW_FORMAT, tabIndex, "manage-news-details-container")).shouldBe(appear,
        DEFAULT_TIMEOUT);
    $(String.format(MANAGE_NEWS_TABVIEW_FORMAT, tabIndex, "news-icon:icon-link")).shouldBe(getClickableCondition())
        .click();
    String selectIconDialogId = String.format(MANAGE_NEWS_TABVIEW_FORMAT, tabIndex, "news-icon:select-icon-dialog");
    $(selectIconDialogId).shouldBe(appear, DEFAULT_TIMEOUT);
    SelenideElement selectIconDialog = $(selectIconDialogId);
    String iconSelector = "a.icon-selection-dialog-selecting-icon ." + icon;
    selectIconDialog.$(iconSelector).shouldBe(getClickableCondition()).click();
    $(selectIconDialogId).shouldBe(Condition.disappear, DEFAULT_TIMEOUT);
  }

  public String selectNewsLanguage(String languageTag) {
    var languageTabClass = "li.ui-tabs-header.news-language-tab-" + languageTag;
    $("[id$=':manage-news-tabview']").shouldBe(appear, DEFAULT_TIMEOUT).$("ul.ui-tabs-nav").$(languageTabClass)
        .shouldBe(getClickableCondition()).click();
    return $(languageTabClass).shouldBe(appear, DEFAULT_TIMEOUT).shouldHave(Condition.cssClass("ui-tabs-selected"))
        .attr("data-index");
  }

  public void clickOnCancelAddingNewsItem() {
    manageNewsDialogShouldBe(appear);
    $("[id$=':cancel-news-link']").shouldBe(appear, DEFAULT_TIMEOUT).shouldBe(getClickableCondition()).click();
    manageNewsDialogShouldBe(disappear);
  }

  public ElementsCollection getTotalNewsItem() {
    var newsContent = $("[id$=':news-widget__content']").shouldBe(appear, DEFAULT_TIMEOUT);
    return newsContent.find(".news-list__datascroller").$$(".news-item");
  }

  public SelenideElement getNewsEmptyMessage() {
    var newsContent = $("[id$=':news-widget__content']").shouldBe(appear, DEFAULT_TIMEOUT);
    return newsContent.find(".empty-message-container");
  }

  public void waitForNewsWidgetContentDisplayClearly(int widgetIndex) {
    var newsWidgetContent = $(".news-widget__content-panel.news_" + widgetIndex).shouldBe(appear, DEFAULT_TIMEOUT);
    newsWidgetContent.$("js-loading-news_" + widgetIndex).shouldBe(disappear, DEFAULT_TIMEOUT);
    newsWidgetContent.$(".news-list__datascroller").shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public void publishNews() {
    manageNewsDialogShouldBe(appear);
    $("[id$=':publish-news-button']").shouldBe(appear, DEFAULT_TIMEOUT).shouldBe(getClickableCondition()).click();
    manageNewsDialogShouldBe(disappear);
  }

  private SelenideElement manageNewsDialogShouldBe(WebElementCondition expectCondition) {
    return $("[id$=':manage-news-dialog']").shouldBe(expectCondition, DEFAULT_TIMEOUT);
  }

  public void clickOnEditIconOfNews(int index) {
    getNewsItem(index).$(String.format(NEWS_ITEM_ATTR_FORMAT, index, "news-item-edit-link"))
        .shouldBe(getClickableCondition()).click();
    manageNewsDialogShouldBe(appear);
  }

  public SelenideElement getNewsItemIcon(int index) {
    return getNewsItem(index).$(String.format(NEWS_ITEM_ATTR_FORMAT, index, "news-icon:current-icon-display"))
        .shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public SelenideElement getNewsItemTitle(int index) {
    return getNewsItem(index).$(String.format(NEWS_ITEM_ATTR_FORMAT, index, "news__title")).shouldBe(appear,
        DEFAULT_TIMEOUT);
  }

  public SelenideElement getNewsItemContent(int index) {
    return getNewsItem(index).$(".news__content").shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public void clickOnDeleteIconOfNews(int index) {
    getNewsItem(index).$("[id$=':" + index + ":news-item-delete-link']").shouldBe(appear, DEFAULT_TIMEOUT)
        .shouldBe(getClickableCondition()).click();
    $("[id$=':delete-news-dialog']").shouldBe(appear, DEFAULT_TIMEOUT);
  }

  private SelenideElement getNewsItem(int index) {
    return $("[id$=':news-list']").shouldBe(appear, DEFAULT_TIMEOUT).$$(".news-item").get(index).shouldBe(appear,
        DEFAULT_TIMEOUT);
  }

  public void confirmAndRemoveNewsItem() {
    $("[id$=':delete-news-dialog']").shouldBe(appear, DEFAULT_TIMEOUT).$("[id$=':remove-widget-button']")
        .shouldBe(getClickableCondition()).click();
    $("[id$=':delete-news-dialog']").shouldBe(disappear, DEFAULT_TIMEOUT);
  }

  public SelenideElement getTranslationOverlayPanel(int index) {
    SelenideElement translationOverlay = $(String.format("div[id$=':%s:overlay-panel-input']", index));
    waitUntilElementToBeClickable(translationOverlay);
    translationOverlay.shouldBe(Condition.appear, DEFAULT_TIMEOUT);

    return translationOverlay;
  }

  public void clickOnTitle(String tabIndex) {
    SelenideElement newsTitleInput = $("input" + String.format(MANAGE_NEWS_TABVIEW_FORMAT, tabIndex, "news-title"))
        .shouldBe(appear, DEFAULT_TIMEOUT);
    newsTitleInput.click();
  }

  public void findTranslationButton(String tabIndex) {
    $(String.format("[id$=':%s:translate-language-button']", tabIndex)).shouldBe(appear, DEFAULT_TIMEOUT);
  }
}
