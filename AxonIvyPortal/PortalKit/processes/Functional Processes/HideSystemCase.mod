[Ivy]
15F999559D09FFA4 3.20 #module
>Proto >Proto Collection #zClass
He0 HideSystemCase Big #zClass
He0 B #cInfo
He0 #process
He0 @TextInP .resExport .resExport #zField
He0 @TextInP .type .type #zField
He0 @TextInP .processKind .processKind #zField
He0 @AnnotationInP-0n ai ai #zField
He0 @MessageFlowInP-0n messageIn messageIn #zField
He0 @MessageFlowOutP-0n messageOut messageOut #zField
He0 @TextInP .xml .xml #zField
He0 @TextInP .responsibility .responsibility #zField
He0 @StartSub f0 '' #zField
He0 @EndSub f1 '' #zField
He0 @GridStep f12 '' #zField
He0 @CallSub f14 '' #zField
He0 @PushWFArc f15 '' #zField
He0 @GridStep f16 '' #zField
He0 @PushWFArc f18 '' #zField
He0 @GridStep f3 '' #zField
He0 @PushWFArc f6 '' #zField
He0 @PushWFArc f2 '' #zField
He0 @PushWFArc f4 '' #zField
>Proto He0 He0 HideSystemCase #zField
He0 f0 inParamDecl '<java.lang.Long serverId,java.lang.Boolean hideBusinessCase> param;' #txt
He0 f0 inParamTable 'out.hideBusinessCase=param.#hideBusinessCase is initialized ? param.hideBusinessCase : false;
out.serverId=param.#serverId is initialized? param.serverId : ch.ivy.addon.portalkit.util.SecurityServiceUtils.getServerIdFromSession();
' #txt
He0 f0 outParamDecl '<> result;
' #txt
He0 f0 actionDecl 'ch.ivy.add.portalkit.HideSystemCaseData out;
' #txt
He0 f0 callSignature call(Long,Boolean) #txt
He0 f0 type ch.ivy.add.portalkit.HideSystemCaseData #txt
He0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>call(Long,Boolean)</name>
        <nameStyle>18,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
He0 f0 81 113 30 30 -52 17 #rect
He0 f0 @|StartSubIcon #fIcon
He0 f1 type ch.ivy.add.portalkit.HideSystemCaseData #txt
He0 f1 1041 113 30 30 0 15 #rect
He0 f1 @|EndSubIcon #fIcon
He0 f12 actionDecl 'ch.ivy.add.portalkit.HideSystemCaseData out;
' #txt
He0 f12 actionTable 'out=in;
' #txt
He0 f12 actionCode 'import ch.ivy.addon.portalkit.service.CaseQueryService;
import ch.ivy.addon.portalkit.enums.CaseSortField;
import ch.ivy.addon.portalkit.support.CaseQueryCriteria;

in.selectedCase = in.hideBusinessCase ? ivy.wf.findCase(ivy.case.getBusinessCase().getId()) : ivy.case;
in.caseSearchCriteria.setTechnicalCase(true);
in.caseSearchCriteria.setBusinessCaseId(in.selectedCase.getId());
in.caseSearchCriteria.involvedUsername = ivy.session.getSessionUserName();

CaseQueryCriteria queryCriteria = new CaseQueryCriteria();
queryCriteria.newQueryCreated = true;

in.caseSearchCriteria.jsonQuery = CaseQueryService.service().createQuery(queryCriteria).asJson();' #txt
He0 f12 security system #txt
He0 f12 type ch.ivy.add.portalkit.HideSystemCaseData #txt
He0 f12 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Prepare search criteria to get
related technical cases</name>
        <nameStyle>54
</nameStyle>
    </language>
</elementInfo>
' #txt
He0 f12 192 106 192 44 -75 -16 #rect
He0 f12 @|StepIcon #fIcon
He0 f14 type ch.ivy.add.portalkit.HideSystemCaseData #txt
He0 f14 processCall MultiPortal/CaseService:findCasesByCriteria(Long,Integer,Integer,ch.ivy.ws.addon.CaseSearchCriteria) #txt
He0 f14 doCall true #txt
He0 f14 requestActionDecl '<java.lang.Long serverId,java.lang.Integer startIndex,java.lang.Integer count,ch.ivy.ws.addon.CaseSearchCriteria caseSearchCriteria> param;
' #txt
He0 f14 requestMappingAction 'param.serverId=in.serverId;
param.caseSearchCriteria=in.caseSearchCriteria;
' #txt
He0 f14 responseActionDecl 'ch.ivy.add.portalkit.HideSystemCaseData out;
' #txt
He0 f14 responseMappingAction 'out=in;
out.technicalCases=result.cases;
' #txt
He0 f14 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>CaseService</name>
        <nameStyle>11,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
He0 f14 448 106 112 44 -35 -8 #rect
He0 f14 @|CallSubIcon #fIcon
He0 f15 expr out #txt
He0 f15 384 128 448 128 #arcP
He0 f16 actionDecl 'ch.ivy.add.portalkit.HideSystemCaseData out;
' #txt
He0 f16 actionTable 'out=in;
' #txt
He0 f16 actionCode 'import ch.ivy.addon.portalkit.util.TaskUtils;
import ch.ivyteam.ivy.workflow.ITask;
import ch.ivyteam.ivy.workflow.ICase;
import ch.ivy.addon.portalkit.util.CaseUtils;

for(ICase technicalCase : in.technicalCases) {
	CaseUtils.setHidePropertyToHideInPortal(technicalCase);
	List<ITask> tasks = technicalCase.getTasks();
	for (ITask task : tasks) {
		TaskUtils.setHidePropertyToHideInPortal(task);
	}
}' #txt
He0 f16 security system #txt
He0 f16 type ch.ivy.add.portalkit.HideSystemCaseData #txt
He0 f16 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Hide related
technical cases</name>
        <nameStyle>28
</nameStyle>
    </language>
</elementInfo>
' #txt
He0 f16 832 106 128 44 -41 -16 #rect
He0 f16 @|StepIcon #fIcon
He0 f18 expr out #txt
He0 f18 960 128 1041 128 #arcP
He0 f3 actionDecl 'ch.ivy.add.portalkit.HideSystemCaseData out;
' #txt
He0 f3 actionTable 'out=in;
' #txt
He0 f3 actionCode 'import ch.ivyteam.ivy.workflow.ITask;
import ch.ivy.addon.portalkit.util.TaskUtils;
import ch.ivyteam.ivy.workflow.ICase;
import ch.ivy.addon.portalkit.util.CaseUtils;

CaseUtils.setHidePropertyToHideInPortal(in.selectedCase);

for(ITask task : in.selectedCase.getTasks()) {
	TaskUtils.setHidePropertyToHideInPortal(task);
}' #txt
He0 f3 security system #txt
He0 f3 type ch.ivy.add.portalkit.HideSystemCaseData #txt
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
He0 f3 640 106 128 44 -44 -16 #rect
He0 f3 @|StepIcon #fIcon
He0 f6 expr out #txt
He0 f6 560 128 640 128 #arcP
He0 f2 expr out #txt
He0 f2 111 128 192 128 #arcP
He0 f4 expr out #txt
He0 f4 768 128 832 128 #arcP
>Proto He0 .type ch.ivy.add.portalkit.HideSystemCaseData #txt
>Proto He0 .processKind CALLABLE_SUB #txt
>Proto He0 0 0 32 24 18 0 #rect
>Proto He0 @|BIcon #fIcon
He0 f12 mainOut f15 tail #connect
He0 f15 head f14 mainIn #connect
He0 f16 mainOut f18 tail #connect
He0 f18 head f1 mainIn #connect
He0 f14 mainOut f6 tail #connect
He0 f6 head f3 mainIn #connect
He0 f0 mainOut f2 tail #connect
He0 f2 head f12 mainIn #connect
He0 f3 mainOut f4 tail #connect
He0 f4 head f16 mainIn #connect
