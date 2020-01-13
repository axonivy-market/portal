package ch.ivy.addon.portalkit.bo;

import java.io.Serializable;
import java.util.List;

public class ExpressWorkFlow implements Serializable {

  private static final long serialVersionUID = -3913837967758293604L;
  private ExpressProcess expressProcess;
  private List<ExpressTaskDefinition> expressTaskDefinitions;
  private List<ExpressFormElement> expressFormElements;

  public ExpressProcess getExpressProcess() {
    return expressProcess;
  }

  public void setExpressProcess(ExpressProcess expressProcess) {
    this.expressProcess = expressProcess;
  }

  public List<ExpressTaskDefinition> getExpressTaskDefinitions() {
    return expressTaskDefinitions;
  }

  public void setExpressTaskDefinitions(List<ExpressTaskDefinition> expressTaskDefinitions) {
    this.expressTaskDefinitions = expressTaskDefinitions;
  }

  public List<ExpressFormElement> getExpressFormElements() {
    return expressFormElements;
  }

  public void setExpressFormElements(List<ExpressFormElement> expressFormElements) {
    this.expressFormElements = expressFormElements;
  }

}
