package ch.ivy.ws.addon.transformer;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import ch.ivy.ws.addon.types.IvyCase;
import ch.ivyteam.ivy.workflow.ICase;

/**
 * Transform a ICase object to an IvyCase object
 * 
 * @author mde
 *
 */
public class IvyCaseTransformer {

  public static IvyCase transformToIvyCase(ICase ivyCase) {
    return new IvyCaseTransformer().transform(ivyCase);
  }

  public static List<IvyCase> transformToIvyCases(List<ICase> ivyCases) {
    return ivyCases.stream().map(IvyCaseTransformer::transformToIvyCase).collect(Collectors.toList());
  }

  public IvyCase transform(ICase c) {
    IvyCase result = new IvyCase();

    result.setBusinessCorrespondentId(c.getBusinessCorrespondentId());
    result.setBusinessCreatorUser(c.getBusinessCreatorUser());
    result.setBusinessMainContactDocumentDatabaseCode(c.getBusinessMainContactDocumentDatabaseCode());
    result.setBusinessMainContactFolderId(c.getBusinessMainContactFolderId());
    result.setBusinessMainContactId(c.getBusinessMainContactId());
    result.setBusinessMainContactName(c.getBusinessMainContactName());
    result.setBusinessMainContactType(c.getBusinessMainContactType());
    result.setBusinessMilestoneTimestamp(c.getBusinessMilestoneTimestamp());
    result.setBusinessObjectCode(c.getBusinessObjectCode());
    result.setBusinessObjectDocumentDatabaseCode(c.getBusinessObjectDocumentDatabaseCode());
    result.setBusinessObjectFolderId(c.getBusinessObjectFolderId());
    result.setBusinessObjectName(c.getBusinessObjectName());
    result.setBusinessPriority(c.getBusinessPriority());
    result.setBusinessStartTimestamp(c.getBusinessStartTimestamp());

    result.setCreatorUserName(c.getCreatorUserName());
    if (c.getCreatorUser() != null) {
      result.setCreatorFullName(c.getCreatorUser().getFullName());
    }

    if (c.getCustomDecimalField1() != null) {
      result.setCustomDecimalField1(c.getCustomDecimalField1().floatValue());
    }
    if (c.getCustomDecimalField2() != null) {
      result.setCustomDecimalField2(c.getCustomDecimalField2().floatValue());
    }
    if (c.getCustomDecimalField3() != null) {
      result.setCustomDecimalField3(c.getCustomDecimalField3().floatValue());
    }
    if (c.getCustomDecimalField4() != null) {
      result.setCustomDecimalField4(c.getCustomDecimalField4().floatValue());
    }
    if (c.getCustomDecimalField5() != null) {
      result.setCustomDecimalField5(c.getCustomDecimalField5().floatValue());
    }
    result.setCustomTimestampField1(c.getCustomTimestampField1());
    result.setCustomTimestampField2(c.getCustomTimestampField2());
    result.setCustomTimestampField3(c.getCustomTimestampField3());
    result.setCustomTimestampField4(c.getCustomTimestampField4());
    result.setCustomTimestampField5(c.getCustomTimestampField5());

    result.setCustomVarCharField1(c.getCustomVarCharField1());
    result.setCustomVarCharField2(c.getCustomVarCharField2());
    result.setCustomVarCharField3(c.getCustomVarCharField3());
    result.setCustomVarCharField4(c.getCustomVarCharField4());
    result.setCustomVarCharField5(c.getCustomVarCharField5());

    result.setDescription(c.getDescription());
    result.setEndTimestamp(c.getEndTimestamp());
    result.setId(c.getId());
    result.setName(c.getName());
    if (c.getPriority() != null) {
      result.setPriority(c.getPriority().name());
    }

    result.setProcessCategoryCode(c.getProcessCategoryCode());
    result.setProcessCategoryName(c.getProcessCategoryName());
    result.setProcessCode(c.getProcessCode());
    result.setProcessName(c.getProcessName());

    result.setStartTimestamp(c.getStartTimestamp());
    if (c.getState() != null) {
      result.setState(c.getState().name());
    }
    result.setSubTypeCode(c.getSubTypeCode());
    result.setSubTypeName(c.getSubTypeName());

    result.setTypeCode(c.getTypeCode());
    result.setTypeName(c.getTypeName());

    ch.ivyteam.ivy.application.IApplication a = c.getApplication();
    String name = a != null ? a.getName() : "";
    result.setApplicationName(name);

    result.setProcessModelName(c.getProcessModel().getName());
    result.setProcessModelVersionNumber(c.getProcessModelVersion().getVersionNumber());
    result.setIvyNotes(new IvyNoteTransformer().transform(c.getNotes()));
    result.setIsBusinessCase(c.isBusinessCase());
    return result;
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
