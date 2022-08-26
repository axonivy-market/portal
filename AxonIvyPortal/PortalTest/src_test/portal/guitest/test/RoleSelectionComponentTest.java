package portal.guitest.test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import portal.guitest.common.BaseTest;
import portal.guitest.page.RoleSelectionComponentPage;

public class RoleSelectionComponentTest extends BaseTest {

  @Override
  @Before
  public void setup() {
    super.setup();
    redirectToRelativeLink(roleSelectionComponentShowcaseUrl);
  }

  @Test
  public void testDefaultRoleSelectionComponent() {
    RoleSelectionComponentPage roleSelectionComponentPage = new RoleSelectionComponentPage();
    String value = roleSelectionComponentPage.getFirstItemForDefaultRoleSelectionComponent("Backend Developer");
    roleSelectionComponentPage.selectFirstItemForDefaultRoleSelectionComponent("Backend Developer");
    assertEquals(value, roleSelectionComponentPage.getDefaultRoleSelection());
  }
  
  @Test
  public void testRoleFromDefinedRoleSelectionComponent() {
    RoleSelectionComponentPage roleSelectionComponentPage = new RoleSelectionComponentPage();
    String value = roleSelectionComponentPage.getFirstItemForRoleFromDefinedRoleSelectionComponent("Backend Developer");
    roleSelectionComponentPage.selectFirstItemForRoleFromDefinedRoleSelectionComponent("Backend Developer");
    assertEquals(value, roleSelectionComponentPage.getRoleFromDefinedRoleSelection());
  }
  
  @Test
  public void testFloatingLabelAndExcludeRoleSelection() {
    RoleSelectionComponentPage roleSelectionComponentPage = new RoleSelectionComponentPage();
    String value = roleSelectionComponentPage.getFirstItemForFloatingLabelAndExcludeRoleSelectionComponent("Backend Developer");
    roleSelectionComponentPage.selectFirstItemForFloatingLabelAndExcludeRoleSelectionComponent("Backend Developer");
    assertEquals(value, roleSelectionComponentPage.getFloatingLabelAndExcludeRoleSelection());
  }
  
  @Test
  public void testAjaxEventRoleSelection() {
    RoleSelectionComponentPage roleSelectionComponentPage = new RoleSelectionComponentPage();
    String value = roleSelectionComponentPage.getFirstItemForAjaxEventRoleSelectionComponent("Backend Developer");
    roleSelectionComponentPage.selectFirstItemForAjaxEventRoleSelectionComponent("Backend Developer");
    assertEquals(value, roleSelectionComponentPage.getAjaxEventRoleSelection());
  }

}
