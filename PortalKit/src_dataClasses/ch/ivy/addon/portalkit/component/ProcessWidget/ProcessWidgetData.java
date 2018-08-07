package ch.ivy.addon.portalkit.component.ProcessWidget;

/**
 */
@SuppressWarnings("all")
@javax.annotation.Generated(comments="This is the java file of the ivy data class ProcessWidgetData", value={"ch.ivyteam.ivy.scripting.streamInOut.IvyScriptJavaClassBuilder"})
public class ProcessWidgetData extends ch.ivyteam.ivy.scripting.objects.CompositeObject
{
  /** SerialVersionUID */
  private static final long serialVersionUID = 8386524605232657995L;

  private ch.ivyteam.ivy.scripting.objects.List<ch.ivy.addon.portalkit.persistence.domain.UserProcess> processes;

  /**
   * Gets the field processes.
   * @return the value of the field processes; may be null.
   */
  public ch.ivyteam.ivy.scripting.objects.List<ch.ivy.addon.portalkit.persistence.domain.UserProcess> getProcesses()
  {
    return processes;
  }

  /**
   * Sets the field processes.
   * @param _processes the new value of the field processes.
   */
  public void setProcesses(ch.ivyteam.ivy.scripting.objects.List<ch.ivy.addon.portalkit.persistence.domain.UserProcess> _processes)
  {
    processes = _processes;
  }

  private ch.ivy.addon.portalkit.persistence.domain.UserProcess userProcess;

  /**
   * Gets the field userProcess.
   * @return the value of the field userProcess; may be null.
   */
  public ch.ivy.addon.portalkit.persistence.domain.UserProcess getUserProcess()
  {
    return userProcess;
  }

  /**
   * Sets the field userProcess.
   * @param _userProcess the new value of the field userProcess.
   */
  public void setUserProcess(ch.ivy.addon.portalkit.persistence.domain.UserProcess _userProcess)
  {
    userProcess = _userProcess;
  }

  /**
   * List contain userSetting and userProcess to synchronize with other servers
   */
  private ch.ivyteam.ivy.scripting.objects.List<ch.ivy.addon.portalkit.persistence.domain.BusinessEntity> synData;

  /**
   * Gets the field synData.
   * @return the value of the field synData; may be null.
   */
  public ch.ivyteam.ivy.scripting.objects.List<ch.ivy.addon.portalkit.persistence.domain.BusinessEntity> getSynData()
  {
    return synData;
  }

  /**
   * Sets the field synData.
   * @param _synData the new value of the field synData.
   */
  public void setSynData(ch.ivyteam.ivy.scripting.objects.List<ch.ivy.addon.portalkit.persistence.domain.BusinessEntity> _synData)
  {
    synData = _synData;
  }

  private ch.ivyteam.ivy.scripting.objects.List<ch.ivyteam.ivy.workflow.IProcessStart> processStarts;

  /**
   * Gets the field processStarts.
   * @return the value of the field processStarts; may be null.
   */
  public ch.ivyteam.ivy.scripting.objects.List<ch.ivyteam.ivy.workflow.IProcessStart> getProcessStarts()
  {
    return processStarts;
  }

  /**
   * Sets the field processStarts.
   * @param _processStarts the new value of the field processStarts.
   */
  public void setProcessStarts(ch.ivyteam.ivy.scripting.objects.List<ch.ivyteam.ivy.workflow.IProcessStart> _processStarts)
  {
    processStarts = _processStarts;
  }

  private ch.ivy.ws.addon.ProcessSearchCriteria processSearchCriteria;

  /**
   * Gets the field processSearchCriteria.
   * @return the value of the field processSearchCriteria; may be null.
   */
  public ch.ivy.ws.addon.ProcessSearchCriteria getProcessSearchCriteria()
  {
    return processSearchCriteria;
  }

  /**
   * Sets the field processSearchCriteria.
   * @param _processSearchCriteria the new value of the field processSearchCriteria.
   */
  public void setProcessSearchCriteria(ch.ivy.ws.addon.ProcessSearchCriteria _processSearchCriteria)
  {
    processSearchCriteria = _processSearchCriteria;
  }

  private java.lang.Boolean changeDefaultProcess;

  /**
   * Gets the field changeDefaultProcess.
   * @return the value of the field changeDefaultProcess; may be null.
   */
  public java.lang.Boolean getChangeDefaultProcess()
  {
    return changeDefaultProcess;
  }

  /**
   * Sets the field changeDefaultProcess.
   * @param _changeDefaultProcess the new value of the field changeDefaultProcess.
   */
  public void setChangeDefaultProcess(java.lang.Boolean _changeDefaultProcess)
  {
    changeDefaultProcess = _changeDefaultProcess;
  }

  private ch.ivyteam.ivy.scripting.objects.List<ch.ivy.addon.portalkit.persistence.domain.UserProcess> processesToBeDeleted;

  /**
   * Gets the field processesToBeDeleted.
   * @return the value of the field processesToBeDeleted; may be null.
   */
  public ch.ivyteam.ivy.scripting.objects.List<ch.ivy.addon.portalkit.persistence.domain.UserProcess> getProcessesToBeDeleted()
  {
    return processesToBeDeleted;
  }

  /**
   * Sets the field processesToBeDeleted.
   * @param _processesToBeDeleted the new value of the field processesToBeDeleted.
   */
  public void setProcessesToBeDeleted(ch.ivyteam.ivy.scripting.objects.List<ch.ivy.addon.portalkit.persistence.domain.UserProcess> _processesToBeDeleted)
  {
    processesToBeDeleted = _processesToBeDeleted;
  }

  private java.lang.Boolean hasDataToSynch;

  /**
   * Gets the field hasDataToSynch.
   * @return the value of the field hasDataToSynch; may be null.
   */
  public java.lang.Boolean getHasDataToSynch()
  {
    return hasDataToSynch;
  }

  /**
   * Sets the field hasDataToSynch.
   * @param _hasDataToSynch the new value of the field hasDataToSynch.
   */
  public void setHasDataToSynch(java.lang.Boolean _hasDataToSynch)
  {
    hasDataToSynch = _hasDataToSynch;
  }

  private java.lang.String language;

  /**
   * Gets the field language.
   * @return the value of the field language; may be null.
   */
  public java.lang.String getLanguage()
  {
    return language;
  }

  /**
   * Sets the field language.
   * @param _language the new value of the field language.
   */
  public void setLanguage(java.lang.String _language)
  {
    language = _language;
  }

}
