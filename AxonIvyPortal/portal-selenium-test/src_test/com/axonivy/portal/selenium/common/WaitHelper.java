package com.axonivy.portal.selenium.common;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import com.codeborne.selenide.Condition;

public final class WaitHelper {

  protected static final long DEFAULT_TIMEOUT = 45000;

  public static void waitForNavigation(Runnable navigationAcion) {
    String viewState = $("input[name='javax.faces.ViewState']").getAttribute("value");
    navigationAcion.run();
    $$("input[value='" + viewState + "']").shouldHave(size(0), DEFAULT_TIMEOUT);
    $(".layout-menu li[role='menuitem'] a.ripplelink.DASHBOARD").waitUntil(Condition.appear, DEFAULT_TIMEOUT);
  }
}
