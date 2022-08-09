package ch.ivy.addon.portalkit.service;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import ch.ivy.addon.portalkit.bo.ExpressProcess;
import ch.ivy.addon.portalkit.enums.PortalVariable;
import ch.ivy.addon.portalkit.util.ExpressManagementUtils;

public class ExpressProcessService extends JsonConfigurationService<ExpressProcess> {

  private static final String EXPRESS_PROCESSES = PortalVariable.EXPRESS_PROCESS.key;
  private static ExpressProcessService instance;

  public static ExpressProcessService getInstance() {
    if (instance == null) {
      instance = new ExpressProcessService();
    }
    return instance;
  }

  public List<ExpressProcess> findReadyToExecuteProcessOrderByName() {
    List<ExpressProcess> expressProcesses = findAll();
    return expressProcesses.stream()
        .filter(ExpressProcess::isReadyToExecute)
        .sorted(Comparator.comparing(ExpressProcess::getProcessName))
        .collect(Collectors.toList());
  }

  public ExpressProcess findReadyToExecuteProcessByName(String processName) {
    List<ExpressProcess> readyToExecuteProcesses = findReadyToExecuteProcessOrderByName();
    return readyToExecuteProcesses.stream()
        .filter(filterByName(processName))
        .findFirst().orElse(null);
  }

  private Predicate<? super ExpressProcess> filterByName(String processName) {
    return process -> process.getProcessName().equals(processName);
  }
  
  public ExpressProcess findExpressProcessByName(String processName) {
    return findAll().stream().filter(filterByName(processName)).findFirst().orElse(null);
  }

  public List<ExpressProcess> findReadyToExecuteProcessByProcessType(String processType) {
    List<ExpressProcess> readyToExecuteProcesses = findReadyToExecuteProcessOrderByName();
    return readyToExecuteProcesses.stream()
        .filter(process -> process.getProcessType().equals(processType))
        .collect(Collectors.toList());
  }

  public ExpressProcess findExpressProcessById(String id) {
    ExpressProcess result = findById(id);
    if (result != null) {
      result.setProcessOwner(ExpressManagementUtils.getValidMemberName(result.getProcessOwner()));
      result.setProcessCoOwners(ExpressManagementUtils.getValidMemberNames(result.getProcessCoOwners()));
      result.setProcessPermissions(ExpressManagementUtils.getValidMemberNames(result.getProcessPermissions()));
    }
    return result;
  }

  @Override
  public Class<ExpressProcess> getType() {
    return ExpressProcess.class;
  }

  @Override
  public String getConfigKey() {
    return EXPRESS_PROCESSES;
  }
}