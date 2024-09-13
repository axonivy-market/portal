package com.axonivy.portal.selenium.test.dashboard;

import static com.codeborne.selenide.CollectionCondition.size;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.BaseTest;
import com.axonivy.portal.selenium.common.LinkNavigator;
import com.axonivy.portal.selenium.common.TestAccount;
import com.axonivy.portal.selenium.page.DashboardModificationPage;
import com.axonivy.portal.selenium.page.DashboardNewsWidgetConfigurationPage;
import com.axonivy.portal.selenium.page.DashboardNewsWidgetPage;
import com.axonivy.portal.selenium.page.MainMenuPage;
import com.axonivy.portal.selenium.page.NewDashboardDetailsEditPage;
import com.axonivy.portal.selenium.page.NewDashboardPage;
import com.axonivy.portal.selenium.page.UserMenuPage;
import com.axonivy.portal.selenium.page.UserProfilePage;
import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;

import ch.ivy.addon.portalkit.enums.PortalVariable;

@IvyWebTest
public class DashboardNewsWidgetTest extends BaseTest {

  private static final String DEFAULT_NEWS_WIDGET_NAME = "News feed";
  private static final String PORTAL_NEWS_WIDGET_NAME = "Portal News feed";
  private static final String NEWS_ITEM_LANG = "en";
  private static final String NEWS_ITEM_ICON = "si-send-email";
  private static final String NEWS_ITEM_TITLE = "Welcome to Portal News feed";
  private static final String NEWS_ITEM_CONTENT =
      "This is great place to share relevant information as News Feed into Axon Ivy.";
  private NewDashboardPage newDashboardPage;
  private DashboardNewsWidgetPage newsWidget;

  @Override
  @BeforeEach
  public void setup() {
    super.setup();
    newDashboardPage = new NewDashboardPage();
    resetLanguageOfCurrentUser();
  }

  @Test
  public void testAddNewsFeedWidget() {
    login(TestAccount.ADMIN_USER);
    var configurationPage = LinkNavigator.navigateToPortalDashboardConfiguration();
    DashboardModificationPage modificationPage = configurationPage.openEditPublicDashboardsPage();
    NewDashboardDetailsEditPage newDashboardDetailsEditPage =
        modificationPage.navigateToEditDashboardDetailsByName("Dashboard");
    newDashboardDetailsEditPage.addWidget();
    DashboardNewsWidgetConfigurationPage newsWidgetConfiguration = newDashboardDetailsEditPage.addNewsFeedWidget();
    newsWidgetConfiguration.changeWidgetTitle(PORTAL_NEWS_WIDGET_NAME);
    newsWidgetConfiguration.save();
    newsWidget = newDashboardPage.selectNewsFeedWidget(PORTAL_NEWS_WIDGET_NAME);
    newsWidget.expand().shouldHave(size(1));
  }

  @Test
  public void testAddNewsItemToWidget() {
  int newsItemIndex = 0;
  newsWidget = loginAsAdminAndPreDashboard();
  newsWidget.openAddNewsFeedItemDialog();
  newsWidget.enterNewsItemData(NEWS_ITEM_LANG, NEWS_ITEM_ICON, NEWS_ITEM_TITLE, NEWS_ITEM_CONTENT);
  newsWidget.clickOnCancelAddingNewsItem();
  newsWidget.getNewsEmptyMessage().shouldBe(Condition.appear, DEFAULT_TIMEOUT);
  publishSampleNewsFeed(NEWS_ITEM_LANG);
  newsWidget.getTotalNewsItem().shouldHave(CollectionCondition.size(1));
  newsWidget.getNewsItemIcon(newsItemIndex).shouldHave(Condition.cssClass(NEWS_ITEM_ICON));
  newsWidget.getNewsItemTitle(newsItemIndex).shouldHave(Condition.text(NEWS_ITEM_TITLE));
  newsWidget.getNewsItemContent(newsItemIndex).shouldHave(Condition.text(NEWS_ITEM_CONTENT));
  }

  @Test
  public void testAddNewsItemAndChangeLaguage() {
    int newsItemIndex = 0;
    String frenchIcon = "si-messages-bubble-add-2";
    String frenchTitle = NEWS_ITEM_TITLE + "- French";
    String frenchContent = NEWS_ITEM_CONTENT + "- French";
    newsWidget = loginAsAdminAndPreDashboard();
    publishSampleNewsFeed(NEWS_ITEM_LANG);
    newsWidget.getTotalNewsItem().shouldHave(CollectionCondition.size(1));
    editExistingNewsItem(newsItemIndex, "fr", frenchIcon, frenchTitle, frenchContent);
    // Change to French and verify news
    changeLanguageToFrench("My profile", 2);
    newsWidget = newDashboardPage.selectNewsFeedWidget(DEFAULT_NEWS_WIDGET_NAME);
    newsWidget.expand().shouldHave(size(1));
    newsWidget.getNewsItemIcon(newsItemIndex).shouldHave(Condition.cssClass(frenchIcon));
    newsWidget.getNewsItemTitle(newsItemIndex).shouldHave(Condition.text(frenchTitle));
    newsWidget.getNewsItemContent(newsItemIndex).shouldHave(Condition.text(frenchContent));

    // Change back to English and verify news
    changeLanguageToFrench("Mon profil", 0);
    newsWidget = newDashboardPage.selectNewsFeedWidget(DEFAULT_NEWS_WIDGET_NAME);
    newsWidget.expand().shouldHave(size(1));
    newsWidget.getNewsItemIcon(newsItemIndex).shouldHave(Condition.cssClass(NEWS_ITEM_ICON));
    newsWidget.getNewsItemTitle(newsItemIndex).shouldHave(Condition.text(NEWS_ITEM_TITLE));
    newsWidget.getNewsItemContent(newsItemIndex).shouldHave(Condition.text(NEWS_ITEM_CONTENT));
  }

  private void changeLanguageToFrench(String menuName, int languageIndex) {
    var mainMenuPage = new MainMenuPage();
    mainMenuPage.openUserSettingMenu();
    var userMenuPage = new UserMenuPage();
    userMenuPage.accessMenu(menuName);
    var userProfilePage = new UserProfilePage();
    userProfilePage.selectLanguage(languageIndex);
    userProfilePage.save();
    newDashboardPage = new NewDashboardPage();
  }

  @Test
  public void testEditAnExistingNewsItem() {
    int newsItemIndex = 0;
    String editIcon = "si-pencil";
    String editTitle = NEWS_ITEM_TITLE + "- Edited";
    String editContent = NEWS_ITEM_CONTENT + "- Edited";
    newsWidget = loginAsAdminAndPreDashboard();
    publishSampleNewsFeed(NEWS_ITEM_LANG);
    newsWidget.getTotalNewsItem().shouldHave(CollectionCondition.size(1));
    editExistingNewsItem(newsItemIndex, NEWS_ITEM_LANG, editIcon, editTitle, editContent);
    newsWidget.getNewsItemIcon(newsItemIndex).shouldHave(Condition.cssClass(editIcon));
    newsWidget.getNewsItemTitle(newsItemIndex).shouldHave(Condition.text(editTitle));
    newsWidget.getNewsItemContent(newsItemIndex).shouldHave(Condition.text(editContent));
  }

  private void editExistingNewsItem(int newsItemIndex, String langTag, String editIcon, String editTitle,
      String editContent) {
    newsWidget.clickOnEditIconOfNews(newsItemIndex);
    String tabIndex = newsWidget.enterNewsItemData(langTag, editIcon, editTitle, editContent);
    newsWidget.clickOnTitle(tabIndex);
    String tabIndexFr = tabIndex;
    if (!"fr".equalsIgnoreCase(langTag)) {
      tabIndexFr = newsWidget.selectNewsLanguage("fr");
      newsWidget.clickOnTitle(tabIndexFr);
    }
    newsWidget.publishNews();
    newsWidget.waitForNewsWidgetContentDisplayClearly(1);
    newsWidget.getTotalNewsItem().shouldHave(CollectionCondition.size(1));
  }

  @Test
  public void testDeleteAnExistingNewsItem() {
    newsWidget = loginAsAdminAndPreDashboard();
    publishSampleNewsFeed(NEWS_ITEM_LANG);
    newsWidget.getTotalNewsItem().shouldHave(CollectionCondition.size(1));
    newsWidget.clickOnDeleteIconOfNews(0);
    newsWidget.confirmAndRemoveNewsItem();
    newsWidget.getNewsEmptyMessage().shouldBe(Condition.appear, DEFAULT_TIMEOUT);
  }

  private void publishSampleNewsFeed(String languageTag) {
    newsWidget.openAddNewsFeedItemDialog();
    newsWidget.enterNewsItemData(languageTag, NEWS_ITEM_ICON, NEWS_ITEM_TITLE, NEWS_ITEM_CONTENT);
    newsWidget.publishNews();
    newsWidget.waitForNewsWidgetContentDisplayClearly(1);
  }

  private DashboardNewsWidgetPage loginAsAdminAndPreDashboard() {
    login(TestAccount.ADMIN_USER);
    redirectToRelativeLink(cleanPortalNewsFeedUrl);
    createJSonFile("dashboard-has-newsfeed.json", PortalVariable.DASHBOARD.key);
    redirectToNewDashBoard();
    DashboardNewsWidgetPage newsWidget = newDashboardPage.selectNewsFeedWidget(DEFAULT_NEWS_WIDGET_NAME);
    newsWidget.expand().shouldHave(size(1));
    return newsWidget;
  }

  @AfterEach
  public void resetUserLanguage() {
    resetLanguageOfCurrentUser();
  }
}
