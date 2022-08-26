package portal.guitest.test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import portal.guitest.common.BaseTest;
import portal.guitest.page.UserSelectionComponentPage;

public class UserSelectionComponentTest extends BaseTest {

  @Override
  @Before
  public void setup() {
    super.setup();
    redirectToRelativeLink(userSelectionComponentShowcaseUrl);
  }

  @Test
  public void testNormalUserSelection() {
    UserSelectionComponentPage userSelectionComponentPage = new UserSelectionComponentPage();
    String value = userSelectionComponentPage.getFirstItemForNormalUserSelectionComponent("Backend Developer 1");
    userSelectionComponentPage.selectFirstItemForNormalUserSelectionComponent("Backend Developer 1");
    assertEquals(value, userSelectionComponentPage.getNormalUserSelection());
  }
  
  @Test
  public void testFloatingLabelUserSelection() {
    UserSelectionComponentPage userSelectionComponentPage = new UserSelectionComponentPage();
    String value = userSelectionComponentPage.getFirstItemForFloatingLabelUserSelectionComponent("Backend Developer 1");
    userSelectionComponentPage.selectFirstItemForFloatingLabelUserSelectionComponent("Backend Developer 1");
    assertEquals(value, userSelectionComponentPage.getFloatingLabelUserSelection());
  }
  
  @Test
  public void testAjaxEventUserSelection() {
    UserSelectionComponentPage userSelectionComponentPage = new UserSelectionComponentPage();
    String value = userSelectionComponentPage.getFirstItemForFloatingLabelUserSelectionComponent("Backend Developer 1");
    userSelectionComponentPage.selectFirstItemForFloatingLabelUserSelectionComponent("Backend Developer 1");
    assertEquals(value, userSelectionComponentPage.getFloatingLabelUserSelection());
  }

}
