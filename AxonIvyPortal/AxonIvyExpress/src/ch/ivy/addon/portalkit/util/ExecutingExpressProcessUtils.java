package ch.ivy.addon.portalkit.util;

import gawfs.ApprovalTaskResult;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import ch.ivyteam.ivy.environment.Ivy;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public final class ExecutingExpressProcessUtils {
  private static final String APPROVAL_RESULTS = "approvalResults";

  private ExecutingExpressProcessUtils() {}

  public static void storeApprovalTaskResultToCaseProperty(ApprovalTaskResult approvalTaskResult) {
    String approvalResultsJson = Ivy.wfCase().getAdditionalProperty(APPROVAL_RESULTS);
    Gson gson = new Gson();
    Type type = new TypeToken<List<ApprovalTaskResult>>() {}.getType();
    List<ApprovalTaskResult> approvalResults = gson.fromJson(approvalResultsJson, type);
    if (approvalResults == null) {
      approvalResults = new ArrayList<>();
    }
    approvalResults.add(approvalTaskResult);
    final List<ApprovalTaskResult> finalApprovalResults = approvalResults;
    IvyExecutor.executeAsSystem(() -> {
      Ivy.wfCase().setAdditionalProperty(APPROVAL_RESULTS, gson.toJson(finalApprovalResults));
      return null;
    });
  }

  public static List<ApprovalTaskResult> getApprovalTaskResultsFromCaseProperty() {
    String approvalResultsJson = Ivy.wfCase().getAdditionalProperty(APPROVAL_RESULTS);
    Gson gson = new Gson();
    Type type = new TypeToken<List<ApprovalTaskResult>>() {}.getType();
    return gson.fromJson(approvalResultsJson, type);
  }

  public static void removeApprovalTaskResultsFromCaseProperty() {
    IvyExecutor.executeAsSystem(() -> {
      Ivy.wfCase().setAdditionalProperty(APPROVAL_RESULTS, null);
      return null;
    });
  }

}
