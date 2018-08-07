package ch.ivy.ws.addon.transformer;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.text.StrSubstitutor;

import ch.ivy.ws.addon.types.IvyCase;
import ch.ivy.ws.addon.types.IvyTask;
import ch.ivyteam.ivy.application.IApplicationConfigurationManager;
import ch.ivyteam.ivy.request.RequestUriFactory;
import ch.ivyteam.ivy.server.ServerFactory;
import ch.ivyteam.ivy.workflow.ITask;

/**
 * Transform a ITask object to an IvyTask object
 * 
 * @author mde
 *
 */
public class IvyTaskTransformer {
  private String serverUrl;

  public IvyTaskTransformer(String serverUrl) {
    this.serverUrl = serverUrl;
  }

  public IvyTask transform(ITask t) {
    return transform(t, true);
  }

  public IvyTask transform(ITask t, boolean addCaseToTask) {
    IvyTask result = new IvyTask();

    result.setActivatorName(t.getActivatorName());

    result.setBusinessCreatorUser(t.getBusinessCreatorUser());

    if (t.getCustomDecimalField1() != null) {
      result.setCustomDecimalField1(t.getCustomDecimalField1().floatValue());
    }
    if (t.getCustomDecimalField2() != null) {
      result.setCustomDecimalField2(t.getCustomDecimalField2().floatValue());
    }
    if (t.getCustomDecimalField3() != null) {
      result.setCustomDecimalField3(t.getCustomDecimalField3().floatValue());
    }
    if (t.getCustomDecimalField4() != null) {
      result.setCustomDecimalField4(t.getCustomDecimalField4().floatValue());
    }
    if (t.getCustomDecimalField5() != null) {
      result.setCustomDecimalField5(t.getCustomDecimalField5().floatValue());
    }
    result.setCustomTimestampField1(t.getCustomTimestampField1());
    result.setCustomTimestampField2(t.getCustomTimestampField2());
    result.setCustomTimestampField3(t.getCustomTimestampField3());
    result.setCustomTimestampField4(t.getCustomTimestampField4());
    result.setCustomTimestampField5(t.getCustomTimestampField5());

    result.setCustomVarCharField1(t.getCustomVarCharField1());
    result.setCustomVarCharField2(t.getCustomVarCharField2());
    result.setCustomVarCharField3(t.getCustomVarCharField3());
    result.setCustomVarCharField4(t.getCustomVarCharField4());
    result.setCustomVarCharField5(t.getCustomVarCharField5());

    result.setDelayTimestamp(t.getDelayTimestamp());
    result.setDescription(t.getDescription());
    result.setDisplayDescriptionTemplate(t.getDisplayDescriptionTemplate());
    result.setDisplayNameTemplate(t.getDisplayNameTemplate());
    if (t.getExpiryActivatorName() != null) {
      result.setExpireActivatorName(t.getExpiryActivatorName().replaceAll("#", ""));
    }
    if (t.getExpiryPriority() != null) {
      result.setExpirePriority(t.getExpiryPriority().name());
    }
    result.setId(t.getId());
    result.setIsExpired(t.isExpired());
    result.setName(t.getName());

    if (t.getPriority() != null) {
      result.setPriority(t.getPriority().name());
    }

    result.setFullRequestPath(getStartLink(t));
    result.setKindCode(t.getKindCode());
    result.setKindName(t.getKindName());
    if (t.getOriginalActivatorName() != null) {
      result.setOriginalActivatorName(t.getOriginalActivatorName().replaceAll("#", ""));
    }
    if (t.getOriginalPriority() != null) {
      result.setOriginalPriority(t.getOriginalPriority().name());

    }
    result.setStartTimestamp(t.getStartTimestamp());
    result.setExpireTimestamp(t.getExpiryTimestamp());
    if (t.getState() != null) {
      result.setState(t.getState().name());
    }
    if (t.getWorkerUserName() != null) {
      result.setWorkerUserName(t.getWorkerUserName().replaceAll("#", ""));
      result.setWorkerFullName(t.getWorkerUser().getFullName());
    }

    result.setApplicationName(t.getCase().getApplication().getName());
    IvyNoteTransformer ivyNoteTransformer = new IvyNoteTransformer();
    result.setIvyNotes(ivyNoteTransformer.transform(t.getNotes()));

    // Add case object
    if (addCaseToTask) {
      IvyCaseTransformer caseTransformer = new IvyCaseTransformer();
      IvyCase c = caseTransformer.transform(t.getCase());
      result.setCaseBusinessCorrespondentId(c.getBusinessCorrespondentId());
      result.setCaseBusinessCreatorUser(c.getBusinessCreatorUser());
      result.setCaseBusinessMainContactDocumentDatabaseCode(c.getBusinessMainContactDocumentDatabaseCode());
      result.setCaseBusinessMainContactFolderId(c.getBusinessMainContactFolderId());
      result.setCaseBusinessMainContactId(c.getBusinessMainContactId());
      result.setCaseBusinessMainContactName(c.getBusinessMainContactName());
      result.setCaseBusinessMainContactType(c.getBusinessMainContactType());
      result.setCaseBusinessMilestoneTimestamp(c.getBusinessMilestoneTimestamp());
      result.setCaseBusinessObjectCode(c.getBusinessObjectCode());
      result.setCaseBusinessObjectDocumentDatabaseCode(c.getBusinessObjectDocumentDatabaseCode());
      result.setCaseBusinessObjectFolderId(c.getBusinessObjectFolderId());
      result.setCaseBusinessObjectName(c.getBusinessObjectName());
      result.setCaseBusinessPriority(c.getBusinessPriority());
      result.setCaseBusinessStartTimestamp(c.getBusinessStartTimestamp());
      result.setCaseCreatorUserName(c.getCreatorUserName());

      result.setCaseCustomDecimalField1(c.getCustomDecimalField1());
      result.setCaseCustomDecimalField2(c.getCustomDecimalField2());
      result.setCaseCustomDecimalField3(c.getCustomDecimalField3());
      result.setCaseCustomDecimalField4(c.getCustomDecimalField4());
      result.setCaseCustomDecimalField5(c.getCustomDecimalField5());

      result.setCaseCustomTimestampField1(c.getCustomTimestampField1());
      result.setCaseCustomTimestampField2(c.getCustomTimestampField2());
      result.setCaseCustomTimestampField3(c.getCustomTimestampField3());
      result.setCaseCustomTimestampField4(c.getCustomTimestampField4());
      result.setCaseCustomTimestampField5(c.getCustomTimestampField5());

      result.setCaseCustomVarCharField1(c.getCustomVarCharField1());
      result.setCaseCustomVarCharField2(c.getCustomVarCharField2());
      result.setCaseCustomVarCharField3(c.getCustomVarCharField3());
      result.setCaseCustomVarCharField4(c.getCustomVarCharField4());
      result.setCaseCustomVarCharField5(c.getCustomVarCharField5());

      result.setCaseDescription(c.getDescription());
      result.setCaseEndTimestamp(c.getEndTimestamp());
      result.setCaseId(c.getId());
      result.setCaseName(c.getName());
      result.setCasePriority(c.getPriority());

      result.setCaseProcessCategoryCode(c.getProcessCategoryCode());
      result.setCaseProcessCategoryName(c.getProcessCategoryName());
      result.setCaseProcessCode(c.getProcessCode());
      result.setCaseProcessName(c.getProcessName());

      result.setCaseStartTimestamp(c.getStartTimestamp());
      result.setCaseState(c.getState());
      result.setCaseSubTypeCode(c.getSubTypeCode());
      result.setCaseSubTypeName(c.getSubTypeName());

      result.setCaseTypeCode(c.getTypeCode());
      result.setCaseTypeName(c.getTypeName());
    }
    return result;
  }

  public List<IvyTask> transform(List<ITask> tasks, boolean addCaseToTask) {
    List<IvyTask> result = new ArrayList<IvyTask>();

    if (tasks != null) {
      for (ITask t : tasks) {
        result.add(transform(t, addCaseToTask));
      }
    }
    return result;

  }

  public List<IvyTask> transform(List<ITask> tasks) {
    return transform(tasks, true);
  }

  private String getStartLink(ITask task) {
    IApplicationConfigurationManager appConfig = ServerFactory.getServer().getApplicationConfigurationManager();
    URI taskUri = RequestUriFactory.createTaskStartUri(appConfig, task);
    String taskStartLinkFormat = "${serverURL}${taskRequestPath}";
    Map<String, String> stringFormatParams = new HashMap<String, String>();
    stringFormatParams.put("serverURL", serverUrl);
    stringFormatParams.put("taskRequestPath", taskUri.toString());
    StrSubstitutor strSubstitutor = new StrSubstitutor(stringFormatParams);
    String taskStartLink = strSubstitutor.replace(taskStartLinkFormat);
    return taskStartLink;
  }
}
