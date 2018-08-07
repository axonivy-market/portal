package ch.ivy.addon.portalkit.service;


import java.util.List;

import ch.ivy.addon.portalkit.bo.ExpressTaskDefinition;

public class ExpressTaskDefinitionService extends AbstractExpressService<ExpressTaskDefinition> {

  public void deleteByProcessId(String processId) {
    List<ExpressTaskDefinition> taskDefinitions = findByProcessId(processId);
    for (ExpressTaskDefinition taskDefinition : taskDefinitions) {
      repo().delete(taskDefinition);
    }
  }

  public List<ExpressTaskDefinition> findByProcessId(String processId) {
    return repo().search(ExpressTaskDefinition.class).textField("processID").isEqualToIgnoringCase(processId).execute()
        .getAll();
  }

  @Override
  public Class<ExpressTaskDefinition> getType() {
    return ExpressTaskDefinition.class;
  }
}
