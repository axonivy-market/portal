package ch.ivy.addon.portal.generic.TaskSearchResult;

/**
 */
@SuppressWarnings("all")
@javax.annotation.Generated(comments="This is the java file of the ivy data class TaskSearchResultData", value={"ch.ivyteam.ivy.scripting.streamInOut.IvyScriptJavaClassBuilder"})
public class TaskSearchResultData extends ch.ivyteam.ivy.scripting.objects.CompositeObject
{
  /** SerialVersionUID */
  private static final long serialVersionUID = -7605055765904672091L;

  private ch.ivy.addon.portal.generic.view.TaskView view;

  /**
   * Gets the field view.
   * @return the value of the field view; may be null.
   */
  public ch.ivy.addon.portal.generic.view.TaskView getView()
  {
    return view;
  }

  /**
   * Sets the field view.
   * @param _view the new value of the field view.
   */
  public void setView(ch.ivy.addon.portal.generic.view.TaskView _view)
  {
    view = _view;
  }

}
