[Ivy]
15F999559D09FFA4 7.5.0 #module
>Proto >Proto Collection #zClass
He0 HideSystemCase Big #zClass
He0 B #cInfo
He0 #process
He0 @TextInP .type .type #zField
He0 @TextInP .processKind .processKind #zField
He0 @AnnotationInP-0n ai ai #zField
He0 @MessageFlowInP-0n messageIn messageIn #zField
He0 @MessageFlowOutP-0n messageOut messageOut #zField
He0 @TextInP .xml .xml #zField
He0 @TextInP .responsibility .responsibility #zField
He0 @StartSub f0 '' #zField
He0 @EndSub f1 '' #zField
He0 @GridStep f3 '' #zField
He0 @PushWFArc f5 '' #zField
He0 @PushWFArc f2 '' #zField
>Proto He0 He0 HideSystemCase #zField
He0 f0 inParamDecl '<Boolean hideBusinessCase> param;' #txt
He0 f0 inParamTable 'out.hideBusinessCase=param.#hideBusinessCase is initialized ? param.hideBusinessCase : false;
' #txt
He0 f0 outParamDecl '<> result;' #txt
He0 f0 callSignature call(Boolean) #txt
He0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>call(Boolean)</name>
    </language>
</elementInfo>
' #txt
He0 f0 81 113 30 30 -36 17 #rect
He0 f0 @|StartSubIcon #fIcon
He0 f1 465 113 30 30 0 15 #rect
He0 f1 @|EndSubIcon #fIcon
He0 f3 actionTable 'out=in;
' #txt
He0 f3 actionCode 'import ch.ivyteam.ivy.workflow.query.CaseQuery;
import ch.ivyteam.ivy.workflow.ITask;
import ch.ivy.addon.portalkit.util.TaskUtils;
import ch.ivyteam.ivy.workflow.ICase;
import ch.ivy.addon.portalkit.util.CaseUtils;

ICase selectedCase = in.hideBusinessCase ? ivy.wf.findCase(ivy.case.getBusinessCase().getId()) : ivy.case;
CaseUtils.setHidePropertyToHideInPortal(selectedCase);

if(selectedCase.isBusinessCase()) {
	CaseQuery query = CaseQuery.create();
	query.where().and().isNotBusinessCase().and().businessCaseId().isEqual(selectedCase.getId());
	List technicalCases = ivy.wf.getCaseQueryExecutor().getResults(query) as List;
	for(ICase technicalCase : technicalCases) {
		CaseUtils.setHidePropertyToHideInPortal(technicalCase);
	}
}

for(ITask task : selectedCase.getTasks()) {
	TaskUtils.setHidePropertyToHideInPortal(task);
}' #txt
He0 f3 security system #txt
He0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Hide system case
and related tasks</name>
        <nameStyle>34
</nameStyle>
    </language>
</elementInfo>
' #txt
He0 f3 232 106 128 44 -44 -16 #rect
He0 f3 @|StepIcon #fIcon
He0 f5 expr out #txt
He0 f5 360 128 465 128 #arcP
He0 f2 expr out #txt
He0 f2 111 128 232 128 #arcP
>Proto He0 .type ch.ivy.add.portalkit.HideSystemCaseData #txt
>Proto He0 .processKind CALLABLE_SUB #txt
>Proto He0 0 0 32 24 18 0 #rect
>Proto He0 @|BIcon #fIcon
He0 f3 mainOut f5 tail #connect
He0 f5 head f1 mainIn #connect
He0 f0 mainOut f2 tail #connect
He0 f2 head f3 mainIn #connect
