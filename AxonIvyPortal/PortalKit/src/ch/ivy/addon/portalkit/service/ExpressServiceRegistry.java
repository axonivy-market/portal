package ch.ivy.addon.portalkit.service;

public final class ExpressServiceRegistry {
  private static ExpressProcessService processService;
  private static ExpressFormElementService formElementService;
  private static ExpressTaskDefinitionService taskDefinitionService;

  public static final ExpressProcessService getProcessService() {
    if (processService == null) {
      processService = new ExpressProcessService();
    }
    return processService;
  }


  public static final ExpressFormElementService getFormElementService() {
    if (formElementService == null) {
      formElementService = new ExpressFormElementService();
    }
    return formElementService;
  }


  public static final ExpressTaskDefinitionService getTaskDefinitionService() {
    if (taskDefinitionService == null) {
      taskDefinitionService = new ExpressTaskDefinitionService();
    }
    return taskDefinitionService;
  }

}
