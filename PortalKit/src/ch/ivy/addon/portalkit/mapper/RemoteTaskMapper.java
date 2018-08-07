package ch.ivy.addon.portalkit.mapper;

import ch.ivy.addon.portalkit.bo.RemoteApplication;
import ch.ivy.addon.portalkit.bo.RemoteCase;
import ch.ivy.addon.portalkit.bo.RemoteSecurityMember;
import ch.ivy.addon.portalkit.bo.RemoteTask;
import ch.ivy.ws.addon.IvyTask;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.scripting.objects.List;
import ch.ivyteam.ivy.security.ISecurityMember;
import ch.ivyteam.ivy.workflow.TaskState;
import ch.ivyteam.ivy.workflow.WorkflowPriority;

/**
 * This class creates Book objects from a given recordset.
 * 
 * @author tauser
 */
public class RemoteTaskMapper {

  /**
   * Convert IvyTask to RemoteTask.
   * 
   * @param ivyTask IvyTask
   * @return RemoteTask
   * @see IvyTask
   * @see RemoteTask
   */
  protected static RemoteTask mapTask(IvyTask ivyTask) {
    RemoteTask result = new RemoteTask();

    result.setName(ivyTask.getName());

    result.setCustomDecimalField1(ivyTask.getCustomDecimalField1());
    result.setCustomDecimalField2(ivyTask.getCustomDecimalField2());
    result.setCustomDecimalField3(ivyTask.getCustomDecimalField3());
    result.setCustomDecimalField4(ivyTask.getCustomDecimalField4());
    result.setCustomDecimalField5(ivyTask.getCustomDecimalField5());

    result.setCustomVarCharField1(ivyTask.getCustomVarCharField1());
    result.setCustomVarCharField2(ivyTask.getCustomVarCharField2());
    result.setCustomVarCharField3(ivyTask.getCustomVarCharField3());
    result.setCustomVarCharField4(ivyTask.getCustomVarCharField4());
    result.setCustomVarCharField5(ivyTask.getCustomVarCharField5());

    result.setCustomTimestampField1(ivyTask.getCustomTimestampField1() == null ? null : ivyTask
        .getCustomTimestampField1().getTime());
    result.setCustomTimestampField2(ivyTask.getCustomTimestampField2() == null ? null : ivyTask
        .getCustomTimestampField2().getTime());
    result.setCustomTimestampField3(ivyTask.getCustomTimestampField3() == null ? null : ivyTask
        .getCustomTimestampField3().getTime());
    result.setCustomTimestampField4(ivyTask.getCustomTimestampField4() == null ? null : ivyTask
        .getCustomTimestampField4().getTime());
    result.setCustomTimestampField5(ivyTask.getCustomTimestampField5() == null ? null : ivyTask
        .getCustomTimestampField5().getTime());
    result.setDelayTimestamp(ivyTask.getDelayTimestamp() == null ? null : ivyTask.getDelayTimestamp().getTime());
    result.setExpiryTimestamp(ivyTask.getExpireTimestamp() == null ? null : ivyTask.getExpireTimestamp().getTime());

    result.setDescription(ivyTask.getDescription());
    result.setRemoteNotes(RemoteNoteMapper.mapNoteArray(ivyTask.getIvyNotes()));

    try {
      result.setExpiryPriority(WorkflowPriority.valueOf(ivyTask.getExpireActivatorName()));
      result.setOriginalPriority(WorkflowPriority.valueOf(ivyTask.getExpireActivatorName()));
    } catch (Exception e) {
    }

    /*
     * 2. - setters must be implemented as NEW METHODS in RemoteTask
     */
    /* - getters must be implemented (override) */

    result.setStartTimestamp(ivyTask.getStartTimestamp().getTime());
    if (ivyTask.getExpireTimestamp() != null) {
      result.setExpiryTimestamp(ivyTask.getExpireTimestamp().getTime());
    }
    result.setBusinessCreatorUser(ivyTask.getBusinessCreatorUser());
    result.setDisplayDescriptionTemplate(ivyTask.getDisplayDescriptionTemplate());
    result.setDisplayNameTemplate(ivyTask.getDisplayNameTemplate());
    result.setExpiryActivatorName(ivyTask.getExpireActivatorName());
    result.setId(ivyTask.getId());
    result.setIsExpired(ivyTask.getIsExpired());
    result.setPriority(WorkflowPriority.valueOf(ivyTask.getPriority()));
    result.setFullRequestPath(ivyTask.getFullRequestPath());
    result.setKindCode(ivyTask.getKindCode());
    result.setKindName(ivyTask.getKindName());
    result.setOriginalActivatorName(ivyTask.getOriginalActivatorName());
    result.setState(TaskState.valueOf(ivyTask.getState()));
    result.setWorkerUserName(ivyTask.getWorkerUserName());
    result.setWorkerFullName(ivyTask.getWorkerFullName());

    /*
     * Implementation (ICase, ISecurityMember, IApplication)
     */
    RemoteCase rc = RemoteCaseMapper.mapCaseFromTask(ivyTask);
    result.setCase(rc);

    RemoteApplication ra = new RemoteApplication();
    ra.setName(ivyTask.getApplicationName());
    result.setApplication(ra);

    RemoteSecurityMember rsm = getLocalSecurityMember(ivyTask.getActivatorName());
    result.setActivator(rsm);
    result.setCanDelegate(ivyTask.getCanDelegate());
    result.setCanReset(ivyTask.getCanReset());
    result.setCanResume(ivyTask.getCanResume());
    result.setCanPark(ivyTask.getCanPark());

    /*
     * fill CASE INFO !!!
     */

    return result;
  }

  /**
   * Get Local Security Member by member name.
   * 
   * @param memberName
   * @return RemoteSecurityMember
   * @see RemoteSecurityMember
   */
  protected static RemoteSecurityMember getLocalSecurityMember(String memberName) {
    if (memberName == null) {
      return null;
    }
    RemoteSecurityMember rsm = new RemoteSecurityMember(memberName);
    if (memberName.startsWith("#")) {
      // TODO: find user and set DisplayName
      ISecurityMember sm = Ivy.session().getSecurityContext().findSecurityMember(memberName);
      if (sm != null) {
        rsm.setDisplayName(sm.getDisplayName());
      } else {
        rsm.setDisplayName(memberName.substring(1));
      }
    } else {
      rsm.setDisplayName(memberName);
    }
    return rsm;
  }

  /**
   * Maps list of IvyTask to list of RemoteTask.
   * 
   * @param ivyTasks List<{@link IvyTask}>
   * @return List<RemoteTask>
   * @see IvyTask
   * @see RemoteTask
   */
  public static List<RemoteTask> mapTasks(List<IvyTask> ivyTasks) {
    List<RemoteTask> outTasks = List.create(RemoteTask.class);

    for (IvyTask t : ivyTasks) {
      RemoteTask task = mapTask(t);

      if (null != task) {
        outTasks.add(task);
      }
    }

    return outTasks;
  }

}
