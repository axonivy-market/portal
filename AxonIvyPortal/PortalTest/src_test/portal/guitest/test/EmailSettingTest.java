package portal.guitest.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import portal.guitest.common.BaseTest;
import portal.guitest.page.NewDashboardPage;
import portal.guitest.page.UserProfilePage;

public class EmailSettingTest  extends BaseTest{
  @Override
  @Before
  public void setup() {
    super.setup();
  }

  @Test
  public void testChangeEmailSetting() {
    NewDashboardPage newDashboardPage = new NewDashboardPage();
    UserProfilePage userProfilePage = newDashboardPage.openMyProfilePage();
    userProfilePage.switchOnEmailOnTaskAssignmentSetting();
    userProfilePage.switchOnFurtherEmailFromAppSetting();
    userProfilePage.selectDaysForDailySummary(Arrays.asList(1,2,3,4));
    assertEquals(4, userProfilePage.getSelectedDaySummary());
    newDashboardPage = userProfilePage.save();
    userProfilePage = newDashboardPage.openMyProfilePage();
    assertTrue(userProfilePage.isEmailOnTaskAssignmentSettingSwitchedOn());
    assertTrue(userProfilePage.isFurtherEmailFromAppSettingSwitchedOn());
    userProfilePage.switchOffFurtherEmailFromAppSetting();
    userProfilePage.switchOffEmailOnTaskAssignmentSetting();
    newDashboardPage = userProfilePage.save();
    userProfilePage = newDashboardPage.openMyProfilePage();
    assertFalse(userProfilePage.isEmailOnTaskAssignmentSettingSwitchedOn());
    assertFalse(userProfilePage.isFurtherEmailFromAppSettingSwitchedOn());
  }

}
