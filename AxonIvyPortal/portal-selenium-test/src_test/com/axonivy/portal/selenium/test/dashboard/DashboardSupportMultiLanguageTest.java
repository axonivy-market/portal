package com.axonivy.portal.selenium.test.dashboard;

import static com.axonivy.portal.selenium.common.Variable.DEEPL_AUTH_KEY;
import static com.axonivy.portal.selenium.common.Variable.ENABLE_DEEPL_TRANSLATION;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.BaseTest;
import com.axonivy.portal.selenium.common.LinkNavigator;
import com.axonivy.portal.selenium.common.TestAccount;
import com.axonivy.portal.selenium.page.DashboardConfigurationPage;
import com.axonivy.portal.selenium.page.DashboardModificationPage;
import com.axonivy.portal.selenium.page.MainMenuPage;
import com.axonivy.portal.selenium.page.NewDashboardDetailsEditPage;
import com.axonivy.portal.selenium.page.UserMenuPage;
import com.axonivy.portal.selenium.page.UserProfilePage;
import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

@IvyWebTest
public class DashboardSupportMultiLanguageTest extends BaseTest {
  private UserProfilePage userProfilePage;
  private MainMenuPage mainMenuPage;
  private UserMenuPage userMenuPage;

  @Override
  @BeforeEach
  public void setup() {
    super.setup();
    login(TestAccount.ADMIN_USER);
    redirectToNewDashBoard();
    resetLanguageOfCurrentUser();
    updatePortalSetting(ENABLE_DEEPL_TRANSLATION.getKey(), "true");
    updatePortalSetting(DEEPL_AUTH_KEY.getKey(), "deepLAuthKey");
  }

  @Test
  public void testAddPublicDashboard() {
    String name = "New public dashboard";
    String updatedName = "German public dashboard";
    List<String> permissions = Arrays.asList("Everybody");
    LinkNavigator.redirectToPortalDashboardConfiguration();
    var configurationPage = new DashboardConfigurationPage();
    configurationPage.openCreatePublicDashboardMenu();
    var newDashboardDialog = configurationPage.openCreateDashboardDialog();
    configurationPage.changeDashboardTitle(newDashboardDialog, name);
    var addLanguageButton = configurationPage.getAddLanguageButton();
    addLanguageButton.click();
    var multipleLanguageDialog = configurationPage.getMultipleLanguageDialog();
    var elementsInput = multipleLanguageDialog.$$("td input");
    elementsInput.get(0).shouldBe(Condition.value(name));
    elementsInput.get(1).shouldBe(Condition.value(name));
    elementsInput.get(2).shouldBe(Condition.value(name));
    elementsInput.get(3).shouldBe(Condition.value(name));

    elementsInput.get(2).setValue(updatedName);
    elementsInput.get(1).click();
    SelenideElement translation = configurationPage.getTranslationOverlayPanel(1);
    translation.$("span.ui-icon-closethick").click();

    multipleLanguageDialog.$("button[type='submit']").click();
    multipleLanguageDialog.shouldBe(Condition.disappear, DEFAULT_TIMEOUT);

    configurationPage.createPublicDashboardFromScratch(newDashboardDialog, permissions);

    NewDashboardDetailsEditPage newDashboardDetailsEditPage = new NewDashboardDetailsEditPage();
    newDashboardDetailsEditPage.getTitleByIndex(0).shouldBe(Condition.exactText(name));
    newDashboardDetailsEditPage.getWidgets().shouldBe(CollectionCondition.empty);

    changeUserLanguage();
    LinkNavigator.redirectToPortalDashboardConfiguration();
    var configurationPage2 = new DashboardConfigurationPage();

    DashboardModificationPage modificationPage = configurationPage2.openEditPublicDashboardsPage("Bearbeiten");
    SelenideElement dashboard = modificationPage.getDashboardRowByName(updatedName);
    dashboard.shouldBe(Condition.appear);
  }

  @Test
  public void testAddPrivateDashboard() {
    String name = "New private dashboard";
    String updatedName = "German private dashboard";
    LinkNavigator.redirectToPortalDashboardConfiguration();
    var configurationPage = new DashboardConfigurationPage();
    configurationPage.openCreatePrivateDashboardMenu();
    var newDashboardDialog = configurationPage.openCreateDashboardDialog();
    configurationPage.changeDashboardTitle(newDashboardDialog, name);
    var addLanguageButton = configurationPage.getAddLanguageButton();
    addLanguageButton.click();
    var multipleLanguageDialog = configurationPage.getMultipleLanguageDialog();
    var elementsInput = multipleLanguageDialog.$$("td input");
    elementsInput.get(0).shouldBe(Condition.value(name));
    elementsInput.get(1).shouldBe(Condition.value(name));
    elementsInput.get(2).shouldBe(Condition.value(name));
    elementsInput.get(3).shouldBe(Condition.value(name));

    elementsInput.get(2).setValue(updatedName);
    elementsInput.get(1).click();
    SelenideElement translation = configurationPage.getTranslationOverlayPanel(1);
    translation.$("span.ui-icon-closethick").click();

    multipleLanguageDialog.$("button[type='submit']").click();
    multipleLanguageDialog.shouldBe(Condition.disappear, DEFAULT_TIMEOUT);

    configurationPage.createPublicDashboardFromScratch(newDashboardDialog, null);

    NewDashboardDetailsEditPage newDashboardDetailsEditPage = new NewDashboardDetailsEditPage();
    newDashboardDetailsEditPage.getTitleByIndex(0).shouldBe(Condition.exactText(name));
    newDashboardDetailsEditPage.getWidgets().shouldBe(CollectionCondition.empty);
    changeUserLanguage();
    LinkNavigator.redirectToPortalDashboardConfiguration();
    var configurationPage2 = new DashboardConfigurationPage();

    DashboardModificationPage modificationPage = configurationPage2.openEditPrivateDashboardPage("Bearbeiten");
    SelenideElement dashboard = modificationPage.getDashboardRowByName(updatedName);
    dashboard.shouldBe(Condition.appear);
  }

  private void changeUserLanguage() {
    NewDashboardDetailsEditPage newDashboardDetailsEditPage = new NewDashboardDetailsEditPage();
    var configurationPage = newDashboardDetailsEditPage.backToConfigurationPage();
    configurationPage.backToHomePage();
    mainMenuPage = new MainMenuPage();
    mainMenuPage.openUserSettingMenu();
    userMenuPage = new UserMenuPage();
    userMenuPage.accessMenu("My profile");
    userProfilePage = new UserProfilePage();
    userProfilePage.selectLanguage(3);
    userProfilePage.save();
  }

  @AfterEach
  public void resetUserLanguage() {
    resetLanguageOfCurrentUser();
  }
}
