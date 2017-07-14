package ch.ivy.addon.portalkit.service;


import java.util.List;

import ch.ivy.addon.portalkit.bo.TaskDefinition;
import ch.ivyteam.ivy.business.data.store.BusinessDataInfo;
import ch.ivyteam.ivy.business.data.store.BusinessDataRepository;
import ch.ivyteam.ivy.environment.Ivy;

public class TaskDefinitionService {

  private static TaskDefinitionService service = new TaskDefinitionService();

  public static TaskDefinitionService getInstance() {
    return service;
  }

  public BusinessDataInfo<TaskDefinition> save(TaskDefinition taskDefinition) {
    return repo().save(taskDefinition);
  }

  public TaskDefinition findById(String id) {
    return repo().find(id, TaskDefinition.class);
  }

  public void deleteByProcessId(String processId) {
    List<TaskDefinition> taskDefinitions = findByProcessId(processId);
    for (TaskDefinition taskDefinition : taskDefinitions) {
      repo().delete(taskDefinition);
    }
  }

  public List<TaskDefinition> findByProcessId(String processId) {
    return repo().search(TaskDefinition.class).textField("processID").isEqualToIgnoringCase(processId).execute()
        .getAll();
  }

  private BusinessDataRepository repo() {
    return Ivy.repo();
  }
}
