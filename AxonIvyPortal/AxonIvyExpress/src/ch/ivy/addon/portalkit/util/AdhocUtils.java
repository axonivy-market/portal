package ch.ivy.addon.portalkit.util;

import java.util.Optional;

import org.primefaces.extensions.model.dynaform.DynaFormControl;
import org.primefaces.extensions.model.dynaform.DynaFormModel;

import ch.ivy.addon.portalkit.bo.AdhocHistory;
import ch.ivy.addon.portalkit.service.AdhocHistoryService;
import ch.ivy.gawfs.DynaFormController;
import ch.ivy.gawfs.Formelement;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.ICase;
import gawfs.ExecutePredefinedWorkflowData;
import gawfs.TaskDef;

public class AdhocUtils {

  public static String getDescriptionOfFirstTask(ExecutePredefinedWorkflowData data) {
    Object dynaData = Optional.ofNullable(data.getCurrentTask())
    		.map(TaskDef::getDynaFormController)
    		.map(DynaFormController::getModel)
    		.map(DynaFormModel::getControls)
    		.map(controls -> controls.get(0))
    		.map(DynaFormControl::getData)
    		.orElse(null);
    return Optional.ofNullable((Formelement) dynaData).map(Formelement::getValue).map(String::valueOf).orElse(null);
  }
  
  public static void storeHistory(long originalTaskId, String content) {
    AdhocHistory adhocHistory = new AdhocHistory();
    adhocHistory.setOriginalTaskId(originalTaskId);
    adhocHistory.setContent(content);
    adhocHistory.setTaskName(Ivy.wfTask().getName());
    adhocHistory.setTimestamp(Ivy.wfTask().getStartTimestamp());
    adhocHistory.setAuthorUsername(Ivy.wfTask().getWorkerUserName());
    AdhocHistoryService adhocHistoryService = new AdhocHistoryService();
    adhocHistoryService.save(adhocHistory);
  }
  
  public static void attachToBusinessCase(final ICase icase, final Long businessCaseId) {
    IvyExecutor.executeAsSystem(() -> {
        icase.attachToBusinessCase(businessCaseId);
        return null;
    });
  }
}
