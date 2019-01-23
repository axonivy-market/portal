package ch.ivy.addon.portalkit.taskfilter;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;

import ch.ivy.addon.portalkit.util.UserUtils;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.IUser;
import ch.ivyteam.ivy.workflow.query.TaskQuery;

public class TaskWorkerFilter extends TaskFilter {

  @JsonIgnore
  private List<IUser> workers;
  private IUser selectedWorker;

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

    return TaskQuery.create().where().workerUserId().isEqual(selectedWorker.getId());
  }

  @Override
  public void resetValues() {
    selectedWorker = null;
  }

  public String formatName(IUser worker) {
    if (StringUtils.isBlank(worker.getFullName())) {
      return worker.getName();
    }
    return worker.getFullName() + " (" + worker.getName() + ")";
  }

  public List<IUser> getWorkers() throws Exception {
    if (workers == null) {
      initWorkers();
    }
    return workers;
  }

  private void initWorkers() {
    workers = UserUtils.findAllUserByApplication();
  }

  public void setWorkers(List<IUser> workers) {
    this.workers = workers;
  }

  public IUser getSelectedWorker() {
    return selectedWorker;
  }

  public void setSelectedWorker(IUser selectedWorker) {
    this.selectedWorker = selectedWorker;
  }
}
