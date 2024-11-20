package com.axonivy.portal.selenium.page;

public class CaseWidgetPage extends CaseWidgetNewDashBoardPage {

  public CaseDetailsPage openDetailsOfCaseHasName(String name) {
    return openDetailsCase(name); //TODO z1 refactor
  }

  public CaseDetailsPage openCase(String name) {
    return openDetailsCase(name); // TODO z1 refactor
  }

  public CaseDetailsPage openCaseDetailsFromActionMenuByCaseName(String name) {
    return openDetailsCase(name); // TODO z1 refactor
  }

}
