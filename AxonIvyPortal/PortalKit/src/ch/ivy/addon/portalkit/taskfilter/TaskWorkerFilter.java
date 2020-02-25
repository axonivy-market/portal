package ch.ivy.addon.portalkit.taskfilter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;

import ch.ivy.addon.portalkit.constant.PortalConstants;
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
    if (getSelectedWorkerMemberName() == null) {
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
    if (CollectionUtils.isEmpty(workers)) {
      workers = UserUtils.findUsers(StringUtils.EMPTY, 0, PortalConstants.MAX_USERS_IN_AUTOCOMPLETE, new ArrayList<>(),
          new ArrayList<>());
    }
  }

  public void setWorkers(List<UserDTO> workers) {
    this.workers = workers;
  }

  public UserDTO getSelectedWorker() {
    return selectedWorker;
  }

  public void setSelectedWorker(UserDTO selectedWorker) {
    this.selectedWorker = selectedWorker;
    this.selectedWorkerMemberName = Optional.ofNullable(selectedWorker).map(UserDTO::getMemberName).orElse(StringUtils.EMPTY);
  }

  /**
   * Check selectedWorkerMemberName which is saved on BusinessData
   * Then find correspond UserDTO of selectedWorkerMemberName.
   * @return Member name of UserDTO
   */
  public String getSelectedWorkerMemberName() {
    if (StringUtils.isEmpty(selectedWorkerMemberName)) {
      setSelectedWorker(null);
      return null;
    } else if (selectedWorker == null || !StringUtils.equals(selectedWorkerMemberName, selectedWorker.getMemberName())) {
      setSelectedWorker(getWorkers().stream()
          .filter(worker -> StringUtils.equals(worker.getMemberName(), selectedWorkerMemberName))
          .findFirst()
          .orElse(null));
    }
    return selectedWorkerMemberName;
  }

  public void setSelectedWorkerMemberName(String selectedWorkerMemberName) {
    this.selectedWorkerMemberName = selectedWorkerMemberName;
  }
  
}
