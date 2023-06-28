package com.axonivy.portal.selenium.test;

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
	}

	@Test
	public void testAddPublicDashboard() {
		String name = "New public dashboard";
		String updatedName = "German public dashboard";
		List<String> permissions = Arrays.asList("Everybody");
		LinkNavigator.redirectToPortalDashboardConfiguration();
		var configurationPage = new DashboardConfigurationPage();
		configurationPage.openCreatePublicDashboardMenu();
		var newDashboardDialog = configurationPage.setupDataPublicDashboardFromScratch(name);
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
		multipleLanguageDialog.$("button[type='submit']").click();

		configurationPage.createPublicDashboardFromScratch(newDashboardDialog, permissions);

		NewDashboardDetailsEditPage newDashboardDetailsEditPage = new NewDashboardDetailsEditPage();
		newDashboardDetailsEditPage.getTitleByIndex(0).shouldBe(Condition.exactText(name));
		newDashboardDetailsEditPage.getWidgets().shouldBe(CollectionCondition.empty);

		changeUserLanguage();
		LinkNavigator.redirectToPortalDashboardConfiguration();
		var configurationPage2 = new DashboardConfigurationPage();

		DashboardModificationPage modificationPage = configurationPage2.openEditPublicDashboardsPage();
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
		var newDashboardDialog = configurationPage.setupDataPublicDashboardFromScratch(name);
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
		multipleLanguageDialog.$("button[type='submit']").click();

		configurationPage.createPublicDashboardFromScratch(newDashboardDialog, null);

		NewDashboardDetailsEditPage newDashboardDetailsEditPage = new NewDashboardDetailsEditPage();
		newDashboardDetailsEditPage.getTitleByIndex(0).shouldBe(Condition.exactText(name));
		newDashboardDetailsEditPage.getWidgets().shouldBe(CollectionCondition.empty);
		changeUserLanguage();
		LinkNavigator.redirectToPortalDashboardConfiguration();
		var configurationPage2 = new DashboardConfigurationPage();

		DashboardModificationPage modificationPage = configurationPage2.openEditPrivateDashboardsPage();
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
