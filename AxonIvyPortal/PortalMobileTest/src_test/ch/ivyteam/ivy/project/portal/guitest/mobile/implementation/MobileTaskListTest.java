package ch.ivyteam.ivy.project.portal.guitest.mobile.implementation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ch.ivyteam.ivy.project.portal.guitest.mobile.common.TestAccount;
import ch.ivyteam.ivy.project.portal.guitest.mobile.page.MobileTaskPage;

public class MobileTaskListTest extends MobileBaseTest{

  private MobileTaskPage mobileTaskPage;
  
  @Override
  @BeforeEach
  public void setup() {
    super.setup();
    createTestingTasks();
    mobileTaskPage = login(TestAccount.DEMO_USER);
  }
  
  @Test
  public void testFilterTask() {
    Assertions.assertEquals(3, mobileTaskPage.countTasks());
    mobileTaskPage.filterTasksBy("Annual Leave Request");
    Assertions.assertEquals(1, mobileTaskPage.countTasks());
    mobileTaskPage.filterTasksBy("Leave Request");
    Assertions.assertEquals(3, mobileTaskPage.countTasks());
  }
  
  @Test
  public void testTaskSort() {
    mobileTaskPage.sortTaskList("Expiry (Oldest first)");
    Assertions.assertEquals("Annual Leave Request", mobileTaskPage.getNameOfTaskAt(0));
    mobileTaskPage.sortTaskList("Expiry (Newest first)");
    Assertions.assertEquals("Maternity Leave Request", mobileTaskPage.getNameOfTaskAt(0));
    mobileTaskPage.sortTaskList("Priority (High to low)");
    Assertions.assertEquals("Sick Leave Request", mobileTaskPage.getNameOfTaskAt(0));
    Assertions.assertEquals("Annual Leave Request", mobileTaskPage.getNameOfTaskAt(1));
    Assertions.assertEquals("Maternity Leave Request", mobileTaskPage.getNameOfTaskAt(2));
    mobileTaskPage.sortTaskList("Priority (Low to high)");
    Assertions.assertEquals("Maternity Leave Request", mobileTaskPage.getNameOfTaskAt(0));
    Assertions.assertEquals("Annual Leave Request", mobileTaskPage.getNameOfTaskAt(1));
    Assertions.assertEquals("Sick Leave Request", mobileTaskPage.getNameOfTaskAt(2));
    
  }
}
