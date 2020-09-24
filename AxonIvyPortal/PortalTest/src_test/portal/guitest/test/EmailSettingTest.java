package portal.guitest.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import portal.guitest.common.BaseTest;
import portal.guitest.page.HomePage;
import portal.guitest.page.UserProfilePage;

public class EmailSettingTest  extends BaseTest{
  @Override
  @Before
  public void setup() {
    super.setup();
  }

  @Test
  public void testChangeEmailSetting() {
    HomePage homePage = new HomePage();
    UserProfilePage userProfilePage = homePage.openMyProfilePage();
    userProfilePage.switchOnEmailOnTaskAssignmentSetting();
    userProfilePage.switchOnFurtherEmailFromAppSetting();
    userProfilePage.selectDaysForDailySummary(Arrays.asList(1,2,3,4));
    assertEquals(4, userProfilePage.getSelectedDaySummary());
    userProfilePage = userProfilePage.save();
    assertTrue(userProfilePage.isEmailOnTaskAssignmentSettingSwitchedOn());
    assertTrue(userProfilePage.isFurtherEmailFromAppSettingSwitchedOn());
    userProfilePage.switchOffFurtherEmailFromAppSetting();
    userProfilePage.switchOffEmailOnTaskAssignmentSetting();
    userProfilePage = userProfilePage.save();
    assertFalse(userProfilePage.isEmailOnTaskAssignmentSettingSwitchedOn());
    assertFalse(userProfilePage.isFurtherEmailFromAppSettingSwitchedOn());
  }

}
