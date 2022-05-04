package ch.ivy.gawfs.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.collections4.CollectionUtils;

import ch.ivy.addon.portalkit.ivydata.utils.ServiceUtilities;
import ch.ivy.addon.portalkit.jsf.Attrs;
import ch.ivy.addon.portalkit.util.ExecutingExpressProcessUtils;
import ch.ivy.gawfs.enums.TaskType;
import ch.ivyteam.ivy.application.IApplication;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.IUser;
import ch.ivyteam.ivy.workflow.CaseState;
import ch.ivyteam.ivy.workflow.ICase;
import ch.ivyteam.ivy.workflow.ITask;
import gawfs.ApprovalTaskResult;
import gawfs.ExecutePredefinedWorkflowData;
import gawfs.TaskDef;

@ManagedBean
@ViewScoped
public class ExpressBusinessViewBean implements Serializable {

  private static final long serialVersionUID = 7992058340186960309L;

  private long caseId;
  private ICase expressCase;
  private ITask expressTask;
  private List<String> workflowSteps;
  private int finishedActualStepIndex;
  private List<TaskDef> finishedTasks;
  private List<ExecutePredefinedWorkflowData> executeWorkflowDataList;

  @PostConstruct
  public void init() {
    this.caseId = Attrs.currentContext().getAttribute("#{data.caseId}", Long.class);
    expressCase = ExecutingExpressProcessUtils.getExpressCase(caseId);
    if (expressCase != null) {
      executeWorkflowDataList = collectPredefinedWorkflowData(expressCase.getCategory().getName());
      initializeProcessSteps(executeWorkflowDataList);
      updateActualStepIndex(executeWorkflowDataList, expressCase);
      initializeTaskDefData(executeWorkflowDataList);
    }
  }

  private List<ExecutePredefinedWorkflowData> collectPredefinedWorkflowData(String caseCategory) {
    List<Object> taskEndProcessList = ExecutingExpressProcessUtils.getExpressTaskEndProcessData(caseId, caseCategory);
    if (CollectionUtils.isEmpty(taskEndProcessList)) {
      // Get First task
      workflowSteps = new ArrayList<>();
      finishedTasks = new ArrayList<>();
    }

    List<ExecutePredefinedWorkflowData> workflowList = new ArrayList<>();
    taskEndProcessList.forEach(data -> {
      if (data instanceof ExecutePredefinedWorkflowData) {
        workflowList.add((ExecutePredefinedWorkflowData) data);
      }
    });
    return workflowList;
  }

  private void updateActualStepIndex(List<ExecutePredefinedWorkflowData> executeWorkflowDataList, ICase expressCase) {
    if (CollectionUtils.isNotEmpty(executeWorkflowDataList)) {
      if (expressCase.getState() == CaseState.DONE) {
        finishedActualStepIndex = this.workflowSteps.size() - 1;
        return;
      }
      finishedActualStepIndex = CollectionUtils.emptyIfNull(executeWorkflowDataList).stream()
          .map(ExecutePredefinedWorkflowData::getActualStepIndex).mapToInt(index -> index).max().orElseThrow();
    }
  }

  private void initializeProcessSteps(List<ExecutePredefinedWorkflowData> executeWorkflowDataList) {
    workflowSteps = new ArrayList<>();
    if (CollectionUtils.isNotEmpty(executeWorkflowDataList)) {
      workflowSteps.addAll(executeWorkflowDataList.get(0).getSteps());
    }
  }

  public void initializeTaskDefData(List<ExecutePredefinedWorkflowData> executeWorkflowDataList) {
    finishedTasks = new ArrayList<>();
    List<ApprovalTaskResult> approvalTaskResults = new ArrayList<>();
    for (ExecutePredefinedWorkflowData executeDefinedWorkflowData : executeWorkflowDataList) {
      TaskDef taskDef = executeDefinedWorkflowData.getCurrentTask();
      if (taskDef.getTaskType() == TaskType.APPROVAL) {
        approvalTaskResults.add(executeDefinedWorkflowData.getApprovalTaskResult());
      } else {
        // Find responsible
        if (taskDef.getActualApplicantName() != null) {
          taskDef.setActualApplicant(findActualApplicantByUsername(taskDef.getActualApplicantName()));
          // Collect finished Tasks
          finishedTasks.add(taskDef);
        }
      }
    }

    // Refine Approval result
    if (!approvalTaskResults.isEmpty()) {
      TaskDef approvalResult = finishedTasks.get(finishedTasks.size() - 1);
      approvalResult.setApprovalResultList(approvalTaskResults);
    }
  }

  private IUser findActualApplicantByUsername(String userName) {
    return ServiceUtilities.findUser(userName, IApplication.current());
  }

  public long getCaseId() {
    return caseId;
  }

  public void setCaseId(long caseId) {
    this.caseId = caseId;
  }

  public ICase getExpressCase() {
    return expressCase;
  }

  public void setExpressCase(ICase expressCase) {
    this.expressCase = expressCase;
  }

  public ITask getExpressTask() {
    return expressTask;
  }

  public void setExpressTask(ITask expressTask) {
    this.expressTask = expressTask;
  }

  public List<String> getWorkflowSteps() {
    return workflowSteps;
  }

  public void setWorkflowSteps(List<String> workflowSteps) {
    this.workflowSteps = workflowSteps;
  }

  public int getFinishedActualStepIndex() {
    return finishedActualStepIndex;
  }

  public void setFinishedActualStepIndex(int finishedActualStepIndex) {
    this.finishedActualStepIndex = finishedActualStepIndex;
  }

  public List<TaskDef> getFinishedTasks() {
    return finishedTasks;
  }

  public void setFinishedTasks(List<TaskDef> finishedTasks) {
    this.finishedTasks = finishedTasks;
  }

}
