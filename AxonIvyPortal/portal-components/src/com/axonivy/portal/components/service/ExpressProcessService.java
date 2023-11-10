package com.axonivy.portal.components.service;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.axonivy.portal.components.bo.ExpressProcess;
import com.axonivy.portal.components.enums.PortalVariable;

@Deprecated(forRemoval = true, since = "10.0.13")
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

    @Override
    public Class<ExpressProcess> getType() {
        return ExpressProcess.class;
    }

    @Override
    public String getConfigKey() {
        return EXPRESS_PROCESSES;
    }
}