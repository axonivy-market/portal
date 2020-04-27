package portal.guitest.test;

import static junit.framework.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import portal.guitest.common.BaseTest;
import portal.guitest.page.HomePage;
import portal.guitest.page.LanguagePage;
import portal.guitest.page.TaskWidgetPage;

public class LanguageSettingTest extends BaseTest {

  @Override
  @Before
  public void setup() {
    super.setup();
    createTestDataAndRedirectToHomePage();
  }

  @Test
  public void testChangeLanguageWhenWorkingOnTask() {
    TaskWidgetPage taskWidgetPage = new TaskWidgetPage();
    taskWidgetPage.startTask(0);

    LanguagePage languagePage = taskWidgetPage.openLanguagePage();
    assertTrue(languagePage.isWarningMessageShownOn());

    languagePage.cancel();
  }

  @Test
  public void testChangeLanguageAtHomepage() {
    HomePage homePage = new HomePage();
    LanguagePage languagePage = homePage.openLanguagePage();
    assertTrue(!languagePage.isWarningMessageShownOn());

    languagePage.cancel();
  }

  private void createTestDataAndRedirectToHomePage() {
    redirectToRelativeLink(createTestingTasksUrl);
    redirectToRelativeLink(HomePage.PORTAL_HOME_PAGE_URL);
  }

}
