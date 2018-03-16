package ch.ivy.ws.addon.transformer;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import ch.ivy.ws.addon.types.IvyCase;
import ch.ivy.ws.addon.util.ServerUrlUtils;
import ch.ivyteam.ivy.workflow.ICase;

/**
 * Transform a ICase object to an IvyCase object
 * 
 * @author mde
 *
 */
@SuppressWarnings("deprecation")
public class IvyCaseTransformer {

  public static IvyCase transformToIvyCase(ICase iCase) {
    return new IvyCaseTransformer().transform(iCase);
  }

  public static List<IvyCase> transformToIvyCases(List<ICase> iCases) {
    return iCases.stream().map(IvyCaseTransformer::transformToIvyCase).collect(Collectors.toList());
  }

  public IvyCase transform(ICase iCase) {
    IvyCase ivyCase = new IvyCase();

    ivyCase.setBusinessCorrespondentId(iCase.getBusinessCorrespondentId());
    ivyCase.setBusinessCreatorUser(iCase.getBusinessCreatorUser());
    ivyCase.setBusinessMainContactDocumentDatabaseCode(iCase.getBusinessMainContactDocumentDatabaseCode());
    ivyCase.setBusinessMainContactFolderId(iCase.getBusinessMainContactFolderId());
    ivyCase.setBusinessMainContactId(iCase.getBusinessMainContactId());
    ivyCase.setBusinessMainContactName(iCase.getBusinessMainContactName());
    ivyCase.setBusinessMainContactType(iCase.getBusinessMainContactType());
    ivyCase.setBusinessMilestoneTimestamp(iCase.getBusinessMilestoneTimestamp());
    ivyCase.setBusinessObjectCode(iCase.getBusinessObjectCode());
    ivyCase.setBusinessObjectDocumentDatabaseCode(iCase.getBusinessObjectDocumentDatabaseCode());
    ivyCase.setBusinessObjectFolderId(iCase.getBusinessObjectFolderId());
    ivyCase.setBusinessObjectName(iCase.getBusinessObjectName());
    ivyCase.setBusinessPriority(iCase.getBusinessPriority());
    ivyCase.setBusinessStartTimestamp(iCase.getBusinessStartTimestamp());

    ivyCase.setCreatorUserName(iCase.getCreatorUserName());
    if (iCase.getCreatorUser() != null) {
      ivyCase.setCreatorFullName(iCase.getCreatorUser().getFullName());
    }

    if (iCase.getCustomDecimalField1() != null) {
      ivyCase.setCustomDecimalField1(iCase.getCustomDecimalField1().floatValue());
    }
    if (iCase.getCustomDecimalField2() != null) {
      ivyCase.setCustomDecimalField2(iCase.getCustomDecimalField2().floatValue());
    }
    if (iCase.getCustomDecimalField3() != null) {
      ivyCase.setCustomDecimalField3(iCase.getCustomDecimalField3().floatValue());
    }
    if (iCase.getCustomDecimalField4() != null) {
      ivyCase.setCustomDecimalField4(iCase.getCustomDecimalField4().floatValue());
    }
    if (iCase.getCustomDecimalField5() != null) {
      ivyCase.setCustomDecimalField5(iCase.getCustomDecimalField5().floatValue());
    }
    ivyCase.setCustomTimestampField1(iCase.getCustomTimestampField1());
    ivyCase.setCustomTimestampField2(iCase.getCustomTimestampField2());
    ivyCase.setCustomTimestampField3(iCase.getCustomTimestampField3());
    ivyCase.setCustomTimestampField4(iCase.getCustomTimestampField4());
    ivyCase.setCustomTimestampField5(iCase.getCustomTimestampField5());

    ivyCase.setCustomVarCharField1(iCase.getCustomVarCharField1());
    ivyCase.setCustomVarCharField2(iCase.getCustomVarCharField2());
    ivyCase.setCustomVarCharField3(iCase.getCustomVarCharField3());
    ivyCase.setCustomVarCharField4(iCase.getCustomVarCharField4());
    ivyCase.setCustomVarCharField5(iCase.getCustomVarCharField5());

    ivyCase.setDescription(iCase.getDescription());
    ivyCase.setEndTimestamp(iCase.getEndTimestamp());
    ivyCase.setId(iCase.getId());
    ivyCase.setName(iCase.getName());
    if (iCase.getPriority() != null) {
      ivyCase.setPriority(iCase.getPriority().name());
    }

    ivyCase.setProcessCategoryCode(iCase.getProcessCategoryCode());
    ivyCase.setProcessCategoryName(iCase.getProcessCategoryName());
    ivyCase.setProcessCode(iCase.getProcessCode());
    ivyCase.setProcessName(iCase.getProcessName());

    ivyCase.setStartTimestamp(iCase.getStartTimestamp());
    if (iCase.getState() != null) {
      ivyCase.setState(iCase.getState().name());
    }
    ivyCase.setSubTypeCode(iCase.getSubTypeCode());
    ivyCase.setSubTypeName(iCase.getSubTypeName());

    ivyCase.setTypeCode(iCase.getTypeCode());
    ivyCase.setTypeName(iCase.getTypeName());

    ch.ivyteam.ivy.application.IApplication a = iCase.getApplication();
    String name = a != null ? a.getName() : "";
    ivyCase.setApplicationName(name);

    ivyCase.setProcessModelName(iCase.getProcessModel().getName());
    ivyCase.setProcessModelVersionNumber(iCase.getProcessModelVersion().getVersionNumber());
    ivyCase.setIvyNotes(new IvyNoteTransformer().transform(iCase.getNotes()));
    ivyCase.setIsBusinessCase(iCase.isBusinessCase());
    ivyCase.setServerUrl(ServerUrlUtils.buildUrlFromSystemProperties());
    return ivyCase;
  }

  public List<IvyCase> transform(List<ICase> cases) {
    List<IvyCase> result = new ArrayList<IvyCase>();

    if (cases != null) {
      for (ICase c : cases) {
        result.add(transform(c));
      }
    }
    return result;
  }
}
