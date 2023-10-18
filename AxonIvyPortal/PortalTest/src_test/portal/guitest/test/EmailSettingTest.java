package portal.guitest.test;

import org.junit.Before;

import portal.guitest.common.BaseTest;

public class EmailSettingTest  extends BaseTest{
  @Override
  @Before
  public void setup() {
    super.setup();
  }

  // @Test
  // public void testChangeEmailSetting() {
  // HomePage homePage = new HomePage();
  // UserProfilePage userProfilePage = homePage.openMyProfilePage();
  // userProfilePage.switchOnEmailOnTaskAssignmentSetting();
  // userProfilePage.switchOnFurtherEmailFromAppSetting();
  // userProfilePage.selectDaysForDailySummary(Arrays.asList(1,2,3,4));
  // assertEquals(4, userProfilePage.getSelectedDaySummary());
  // homePage = userProfilePage.save();
  // userProfilePage = homePage.openMyProfilePage();
  // assertTrue(userProfilePage.isEmailOnTaskAssignmentSettingSwitchedOn());
  // assertTrue(userProfilePage.isFurtherEmailFromAppSettingSwitchedOn());
  // userProfilePage.switchOffFurtherEmailFromAppSetting();
  // userProfilePage.switchOffEmailOnTaskAssignmentSetting();
  // homePage = userProfilePage.save();
  // userProfilePage = homePage.openMyProfilePage();
  // assertFalse(userProfilePage.isEmailOnTaskAssignmentSettingSwitchedOn());
  // assertFalse(userProfilePage.isFurtherEmailFromAppSettingSwitchedOn());
  // }

}
