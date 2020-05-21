package portal.guitest.test;

import static org.junit.Assert.assertTrue;

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
    createTestData();
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

  private void createTestData() {
    redirectToRelativeLink(createTestingTasksUrl);
  }

}
