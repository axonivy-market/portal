package ch.ivy.addon.portalkit.taskfilter;

import java.util.List;
import java.util.Optional;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;

import ch.ivy.addon.portalkit.dto.UserDTO;
import ch.ivy.addon.portalkit.util.UserUtils;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.query.TaskQuery;

public class TaskWorkerFilter extends TaskFilter {

  @JsonIgnore
  private List<UserDTO> workers;
  @JsonIgnore
  private UserDTO selectedWorker;
  private String selectedWorkerMemberName;

  @Override
  public String label() {
    return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/common/filter/worker");
  }

  @Override
  public String value() {
    if (getSelectedWorker() == null) {
      return ALL;
    }
    return String.format(DOUBLE_QUOTES, formatName(selectedWorker));
  }

  @Override
  public TaskQuery buildQuery() {
    if (getSelectedWorker() == null) {
      return null;
    }

    return TaskQuery.create().where().workerUserName().isEqual(selectedWorker.getName());
  }

  @Override
  public void resetValues() {
    selectedWorker = null;
    selectedWorkerMemberName = null;
  }

  public String formatName(UserDTO worker) {
    if (StringUtils.isBlank(worker.getDisplayName())) {
      return worker.getName();
    }
    return worker.getDisplayName() + " (" + worker.getName() + ")";
  }

  public List<UserDTO> getWorkers() {
    if (workers == null) {
      initWorkers();
    }
    return workers;
  }

  private void initWorkers() {
    workers = UserUtils.findAllUserDTOByApplication();
  }

  public void setWorkers(List<UserDTO> workers) {
    this.workers = workers;
  }

  public UserDTO getSelectedWorker() {
    if (selectedWorker == null && CollectionUtils.isNotEmpty(getWorkers())) {
      selectedWorker = workers.stream()
          .filter(worker -> StringUtils.equals(worker.getMemberName(), selectedWorkerMemberName))
          .findFirst()
          .orElse(null);
    }
    return selectedWorker;
  }

  public void setSelectedWorker(UserDTO selectedWorker) {
    this.selectedWorker = selectedWorker;
    this.selectedWorkerMemberName = Optional.ofNullable(selectedWorker).map(UserDTO::getMemberName).orElse(StringUtils.EMPTY);
  }

  public String getSelectedWorkerMemberName() {
    return selectedWorkerMemberName;
  }

  public void setSelectedWorkerMemberName(String selectedWorkerMemberName) {
    this.selectedWorkerMemberName = selectedWorkerMemberName;
  }
  
}
