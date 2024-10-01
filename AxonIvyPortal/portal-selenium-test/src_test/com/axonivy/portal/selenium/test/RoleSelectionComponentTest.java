package com.axonivy.portal.selenium.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.BaseTest;
import com.axonivy.portal.selenium.page.component.RoleSelectionComponentPage;

@IvyWebTest
public class RoleSelectionComponentTest extends BaseTest {

  @Override
  @BeforeEach
  public void setup() {
    super.setup();
    redirectToRelativeLink(roleSelectionComponentShowcaseUrl);
  }

  @Test
  public void testDefaultRoleSelectionComponent() {
    RoleSelectionComponentPage roleSelectionComponentPage = new RoleSelectionComponentPage();
    String value = roleSelectionComponentPage.selectFirstItemForDefaultRoleSelectionComponent("Backend Developer");
    assertEquals(value, roleSelectionComponentPage.getDefaultRoleSelection());
  }

  @Test
  public void testRoleFromDefinedRoleSelectionComponent() {
    RoleSelectionComponentPage roleSelectionComponentPage = new RoleSelectionComponentPage();
    String value =
        roleSelectionComponentPage.selectFirstItemForRoleFromDefinedRoleSelectionComponent("Backend Developer");
    assertEquals(value, roleSelectionComponentPage.getRoleFromDefinedRoleSelection());
  }

  @Test
  public void testFloatingLabelAndExcludeRoleSelection() {
    RoleSelectionComponentPage roleSelectionComponentPage = new RoleSelectionComponentPage();
    String value =
        roleSelectionComponentPage.selectFirstItemForFloatingLabelAndExcludeRoleSelectionComponent("Backend Developer");
    assertEquals(value, roleSelectionComponentPage.getFloatingLabelAndExcludeRoleSelection());
  }

  @Test
  public void testAjaxEventRoleSelection() {
    RoleSelectionComponentPage roleSelectionComponentPage = new RoleSelectionComponentPage();
    String value = roleSelectionComponentPage.selectFirstItemForAjaxEventRoleSelectionComponent("Backend Developer");
    assertEquals(value, roleSelectionComponentPage.getAjaxEventRoleSelection());
  }

}
