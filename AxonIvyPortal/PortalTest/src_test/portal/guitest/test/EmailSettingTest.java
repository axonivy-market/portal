package portal.guitest.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import portal.guitest.common.BaseTest;
import portal.guitest.page.NewDashboardPage2;
import portal.guitest.page.UserProfilePage;

public class EmailSettingTest  extends BaseTest{
  @Override
  @Before
  public void setup() {
    super.setup();
  }

  @Test
  public void testChangeEmailSetting() {
    NewDashboardPage2 newDashboardPage2 = new NewDashboardPage2();
    UserProfilePage userProfilePage = newDashboardPage2.openMyProfilePage();
    userProfilePage.switchOnEmailOnTaskAssignmentSetting();
    userProfilePage.switchOnFurtherEmailFromAppSetting();
    userProfilePage.selectDaysForDailySummary(Arrays.asList(1,2,3,4));
    assertEquals(4, userProfilePage.getSelectedDaySummary());
    newDashboardPage2 = userProfilePage.save();
    userProfilePage = newDashboardPage2.openMyProfilePage();
    assertTrue(userProfilePage.isEmailOnTaskAssignmentSettingSwitchedOn());
    assertTrue(userProfilePage.isFurtherEmailFromAppSettingSwitchedOn());
    userProfilePage.switchOffFurtherEmailFromAppSetting();
    userProfilePage.switchOffEmailOnTaskAssignmentSetting();
    newDashboardPage2 = userProfilePage.save();
    userProfilePage = newDashboardPage2.openMyProfilePage();
    assertFalse(userProfilePage.isEmailOnTaskAssignmentSettingSwitchedOn());
    assertFalse(userProfilePage.isFurtherEmailFromAppSettingSwitchedOn());
  }

}
