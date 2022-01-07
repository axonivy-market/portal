package portal.guitest.page;

public class AddAbsencePage extends SettingDeputyPage {

  @Override
  protected String getLoadedLocator() {
    return "id('absence-settings:add-new-absence-dialog_title')";
  }
  
  @Override
  public void proceedWhenCreatingAbsence() {
    String selector = "button[id*='save-substitutes']";
    proceed(selector);
  }
}
