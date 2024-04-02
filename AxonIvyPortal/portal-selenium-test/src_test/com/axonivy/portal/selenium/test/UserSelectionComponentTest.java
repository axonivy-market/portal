package com.axonivy.portal.selenium.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.BaseTest;
import com.axonivy.portal.selenium.page.component.UserSelectionComponentPage;
import com.codeborne.selenide.Condition;

@IvyWebTest
public class UserSelectionComponentTest extends BaseTest {

  @Override
  @BeforeEach
  public void setup() {
    super.setup();
    redirectToRelativeLink(userSelectionComponentShowcaseUrl);
  }

  @Test
  public void testNormalUserSelection() {
    UserSelectionComponentPage userSelectionComponentPage = new UserSelectionComponentPage();
    String value = userSelectionComponentPage.selectFirstItemForNormalUserSelectionComponent("Backend Developer 1");
    userSelectionComponentPage.getNormalUserSelectionElement().shouldHave(Condition.cssValue("value", value));
  }

  @Test
  public void testFloatingLabelUserSelection() {
    UserSelectionComponentPage userSelectionComponentPage = new UserSelectionComponentPage();
    String value =
        userSelectionComponentPage.selectFirstItemForFloatingLabelUserSelectionComponent("Backend Developer 1");
    userSelectionComponentPage.getFloatingLabelUserSelectionElem().shouldHave(Condition.cssValue("value", value));
  }

  @Test
  public void testAjaxEventUserSelection() {
    UserSelectionComponentPage userSelectionComponentPage = new UserSelectionComponentPage();
    String value =
        userSelectionComponentPage.selectFirstItemForFloatingLabelUserSelectionComponent("Backend Developer 1");
    assertEquals(value, userSelectionComponentPage.getFloatingLabelUserSelection());
  }
}
