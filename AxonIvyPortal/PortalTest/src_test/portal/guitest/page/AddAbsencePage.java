package portal.guitest.page;

public class AddAbsencePage extends TemplatePage {

  @Override
  protected String getLoadedLocator() {
    return "id('absence-settings:add-new-absence-dialog_title')";
  }
  
  public void proceedWhenCreatingAbsence() {
    String selector = "button[id*='save-substitutes']";
    proceed(selector);
  }

  private void proceed(String selector) {
    clickByCssSelector(selector);
    waitAjaxIndicatorDisappear();
  }

}
