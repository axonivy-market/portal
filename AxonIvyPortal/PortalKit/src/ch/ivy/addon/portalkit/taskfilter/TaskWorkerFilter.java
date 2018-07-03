package ch.ivy.addon.portalkit.taskfilter;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import ch.ivy.addon.portalkit.bo.RemoteSecurityMember;
import ch.ivy.addon.portalkit.util.UserUtils;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.query.TaskQuery;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class TaskWorkerFilter extends TaskFilter {

  @JsonIgnore
  private List<RemoteSecurityMember> workers;
  private RemoteSecurityMember selectedWorker;
  @JsonIgnore
  private static final String SECURITY_SERVICE_CALLABLE = "MultiPortal/SecurityService";

  @Override
  public String label() {
    return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/common/filter/worker");
  }

  @Override
  public String value() {
    if (selectedWorker == null) {
      return ALL;
    }
    return String.format(DOUBLE_QUOTES, formatName(selectedWorker));
  }

  @Override
  public TaskQuery buildQuery() {
    if (selectedWorker == null) {
      return null;
    }

    String memberName = selectedWorker.getMemberName();
    return TaskQuery.create().where().workerUserName().isEqual(memberName);
  }

  @Override
  public void resetValues() {
    selectedWorker = null;
  }

  public String formatName(RemoteSecurityMember worker) {
    if (StringUtils.isBlank(worker.getDisplayName())) {
      return worker.getMemberName();
    }
    return worker.getDisplayName() + " (" + worker.getMemberName() + ")";
  }

  public List<RemoteSecurityMember> getWorkers() {
    if (workers == null) {
      initWorkers();
    }
    return workers;
  }

  private void initWorkers() {
    workers = UserUtils.findAllUserByApplication("Can't get list of users in worker filter");
  }

  public void setWorkers(List<RemoteSecurityMember> workers) {
    this.workers = workers;
  }

  public RemoteSecurityMember getSelectedWorker() {
    return selectedWorker;
  }

  public void setSelectedWorker(RemoteSecurityMember selectedWorker) {
    this.selectedWorker = selectedWorker;
  }
}
