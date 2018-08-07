package ch.ivy.addon.portalkit.showroom.singleapp.process.SampleProcessChain;

/**
 */
@SuppressWarnings("all")
@javax.annotation.Generated(comments="This is the java file of the ivy data class SampleProcessChainData", value={"ch.ivyteam.ivy.scripting.streamInOut.IvyScriptJavaClassBuilder"})
public class SampleProcessChainData extends ch.ivyteam.ivy.scripting.objects.CompositeObject
{
  /** SerialVersionUID */
  private static final long serialVersionUID = -2746497890664646031L;

  private ch.ivyteam.ivy.scripting.objects.List<java.lang.String> steps;

  /**
   * Gets the field steps.
   * @return the value of the field steps; may be null.
   */
  public ch.ivyteam.ivy.scripting.objects.List<java.lang.String> getSteps()
  {
    return steps;
  }

  /**
   * Sets the field steps.
   * @param _steps the new value of the field steps.
   */
  public void setSteps(ch.ivyteam.ivy.scripting.objects.List<java.lang.String> _steps)
  {
    steps = _steps;
  }

  private java.lang.Number actualStepIndex;

  /**
   * Gets the field actualStepIndex.
   * @return the value of the field actualStepIndex; may be null.
   */
  public java.lang.Number getActualStepIndex()
  {
    return actualStepIndex;
  }

  /**
   * Sets the field actualStepIndex.
   * @param _actualStepIndex the new value of the field actualStepIndex.
   */
  public void setActualStepIndex(java.lang.Number _actualStepIndex)
  {
    actualStepIndex = _actualStepIndex;
  }

  private java.lang.String currentView;

  /**
   * Gets the field currentView.
   * @return the value of the field currentView; may be null.
   */
  public java.lang.String getCurrentView()
  {
    return currentView;
  }

  /**
   * Sets the field currentView.
   * @param _currentView the new value of the field currentView.
   */
  public void setCurrentView(java.lang.String _currentView)
  {
    currentView = _currentView;
  }

  private java.lang.String nextView;

  /**
   * Gets the field nextView.
   * @return the value of the field nextView; may be null.
   */
  public java.lang.String getNextView()
  {
    return nextView;
  }

  /**
   * Sets the field nextView.
   * @param _nextView the new value of the field nextView.
   */
  public void setNextView(java.lang.String _nextView)
  {
    nextView = _nextView;
  }

}
