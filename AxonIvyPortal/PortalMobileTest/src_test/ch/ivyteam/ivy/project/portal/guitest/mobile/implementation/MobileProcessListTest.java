package ch.ivyteam.ivy.project.portal.guitest.mobile.implementation;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;

import ch.ivyteam.ivy.project.portal.guitest.mobile.common.TestAccount;
import ch.ivyteam.ivy.project.portal.guitest.mobile.page.MobileProcessPage;
import ch.ivyteam.ivy.project.portal.guitest.mobile.page.MobileTaskPage;

public class MobileProcessListTest extends MobileBaseTest{
  
  private MobileProcessPage mobileProcessPage;
  private MobileTaskPage mobileTaskPage;
  
  @Override
  @BeforeEach
  public void setup() {
    super.setup();
    mobileTaskPage = login(TestAccount.DEMO_USER);
    mobileProcessPage = mobileTaskPage.openProcessTab();
  }
  
  @Test
  public void testSearchProcessName() {
    mobileProcessPage.filterProcess("Categoried Leave Request");
    mobileTaskPage = mobileProcessPage.startFirstProcess();
    Assertions.assertEquals("Maternity Leave Request", mobileTaskPage.getNameOfTaskAt(0));
    Assertions.assertEquals("Sick Leave Request", mobileTaskPage.getNameOfTaskAt(1));
  }
  
  @Test
  public void testGoToFieldsetByLetter() {
    mobileProcessPage.selectGroup("G");
    List<WebElement> processItemsOfSelectedFieldset = mobileProcessPage.getAllProcessUnderGroup("G");
    processItemsOfSelectedFieldset.forEach(item -> Assertions.assertTrue(item.getText().startsWith("G")));
  }
  
  @Test
  public void testStyleOfProcessTab() {
    String unselectedTextColor = "rgba(0, 0, 0, 1)";
    String unselectedBorderBottomColor = "rgba(0, 0, 0, 0.05)";
    String selectedColor = "rgba(175, 202, 5, 1)";
    WebElement processTab =  mobileProcessPage.getProcessTab();
    WebElement taskTab = mobileProcessPage.getTaskTab();
    
    Assertions.assertEquals(unselectedTextColor, taskTab.getCssValue("color"));
    Assertions.assertEquals(unselectedBorderBottomColor, taskTab.getCssValue("border-bottom-color"));
    Assertions.assertEquals(selectedColor, processTab.getCssValue("color"));
    Assertions.assertEquals(selectedColor, processTab.getCssValue("border-bottom-color"));
    
    mobileTaskPage = mobileProcessPage.openTaskTab();
    processTab =  mobileTaskPage.getProcessTab();
    taskTab = mobileTaskPage.getTaskTab();
    Assertions.assertEquals(unselectedTextColor, processTab.getCssValue("color"));
    Assertions.assertEquals(unselectedBorderBottomColor, processTab.getCssValue("border-bottom-color"));
    Assertions.assertEquals(selectedColor, taskTab.getCssValue("color"));
    Assertions.assertEquals(selectedColor, taskTab.getCssValue("border-bottom-color"));
  }
}
