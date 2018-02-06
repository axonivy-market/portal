[Ivy]
153358BE9219FD4C 3.20 #module
>Proto >Proto Collection #zClass
Cs0 CaseItemRelatedTaskProcess Big #zClass
Cs0 RD #cInfo
Cs0 #process
Cs0 @TextInP .ui2RdDataAction .ui2RdDataAction #zField
Cs0 @TextInP .rdData2UIAction .rdData2UIAction #zField
Cs0 @TextInP .resExport .resExport #zField
Cs0 @TextInP .type .type #zField
Cs0 @TextInP .processKind .processKind #zField
Cs0 @AnnotationInP-0n ai ai #zField
Cs0 @MessageFlowInP-0n messageIn messageIn #zField
Cs0 @MessageFlowOutP-0n messageOut messageOut #zField
Cs0 @TextInP .xml .xml #zField
Cs0 @TextInP .responsibility .responsibility #zField
Cs0 @RichDialogInitStart f0 '' #zField
Cs0 @RichDialogProcessEnd f1 '' #zField
Cs0 @CallSub f66 '' #zField
Cs0 @RichDialogMethodStart f6 '' #zField
Cs0 @PushWFArc f8 '' #zField
Cs0 @RichDialogProcessStart f2 '' #zField
Cs0 @CallSub f4 '' #zField
Cs0 @GridStep f23 '' #zField
Cs0 @PushWFArc f11 '' #zField
Cs0 @PushWFArc f5 '' #zField
Cs0 @CallSub f12 '' #zField
Cs0 @CallSub f71 '' #zField
Cs0 @GridStep f69 '' #zField
Cs0 @RichDialogProcessStart f68 '' #zField
Cs0 @RichDialogProcessEnd f73 '' #zField
Cs0 @PushWFArc f70 '' #zField
Cs0 @PushWFArc f74 '' #zField
Cs0 @PushWFArc f72 '' #zField
Cs0 @Split f13 '' #zField
Cs0 @GridStep f15 '' #zField
Cs0 @GridStep f16 '' #zField
Cs0 @PushWFArc f3 '' #zField
Cs0 @PushWFArc f14 '' #zField
Cs0 @Join f17 '' #zField
Cs0 @PushWFArc f20 '' #zField
Cs0 @PushWFArc f10 '' #zField
Cs0 @PushWFArc f9 '' #zField
Cs0 @SJArc f19 '' #zField
Cs0 @Alternative f21 '' #zField
Cs0 @PushWFArc f22 '' #zField
Cs0 @PushWFArc f7 '' #zField
Cs0 @Alternative f24 '' #zField
Cs0 @PushWFArc f25 '' #zField
Cs0 @SJArc f18 '' #zField
Cs0 @PushWFArc f26 '' #zField
>Proto Cs0 Cs0 CaseItemRelatedTaskProcess #zField
Cs0 f0 guid 153358BE9322E3ED #txt
Cs0 f0 type ch.ivy.addon.portalkit.component.CaseItemRelatedTask.CaseItemRelatedTaskData #txt
Cs0 f0 method start() #txt
Cs0 f0 disableUIEvents true #txt
Cs0 f0 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<> param = methodEvent.getInputArguments();
' #txt
Cs0 f0 outParameterDecl '<> result;
' #txt
Cs0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start()</name>
    </language>
</elementInfo>
' #txt
Cs0 f0 53 85 22 22 14 0 #rect
Cs0 f0 @|RichDialogInitStartIcon #fIcon
Cs0 f1 type ch.ivy.addon.portalkit.component.CaseItemRelatedTask.CaseItemRelatedTaskData #txt
Cs0 f1 53 453 22 22 14 0 #rect
Cs0 f1 @|RichDialogProcessEndIcon #fIcon
Cs0 f66 type ch.ivy.addon.portalkit.component.CaseItemRelatedTask.CaseItemRelatedTaskData #txt
Cs0 f66 processCall MultiPortal/TaskService:findTasksByCriteria(Long,ch.ivy.ws.addon.TaskSearchCriteria,Integer,Integer) #txt
Cs0 f66 doCall true #txt
Cs0 f66 requestActionDecl '<java.lang.Long serverId,ch.ivy.ws.addon.TaskSearchCriteria taskSearchCriteria,java.lang.Integer startIndex,java.lang.Integer count> param;
' #txt
Cs0 f66 requestMappingAction 'param.serverId=in.remoteCase.server.#id;
param.taskSearchCriteria=in.taskSearchCriteria;
' #txt
Cs0 f66 responseActionDecl 'ch.ivy.addon.portalkit.component.CaseItemRelatedTask.CaseItemRelatedTaskData out;
' #txt
Cs0 f66 responseMappingAction 'out=in;
out.relatedTasks=in.taskSearchCriteria.ignoreInvolvedUser ? result.allTasks : result.tasks;
' #txt
Cs0 f66 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>find all tasks
 of case</name>
        <nameStyle>23,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f66 331 276 36 24 20 -2 #rect
Cs0 f66 @|CallSubIcon #fIcon
Cs0 f6 guid 153359D0C3F2A4AB #txt
Cs0 f6 type ch.ivy.addon.portalkit.component.CaseItemRelatedTask.CaseItemRelatedTaskData #txt
Cs0 f6 method initDatas(ch.ivy.addon.portalkit.bo.RemoteCase) #txt
Cs0 f6 disableUIEvents false #txt
Cs0 f6 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<ch.ivy.addon.portalkit.bo.RemoteCase remoteCase> param = methodEvent.getInputArguments();
' #txt
Cs0 f6 inParameterMapAction 'out.remoteCase=param.remoteCase;
' #txt
Cs0 f6 outParameterDecl '<> result;
' #txt
Cs0 f6 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>initDatas(RemoteCase)</name>
        <nameStyle>21,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f6 245 85 22 22 14 0 #rect
Cs0 f6 @|RichDialogMethodStartIcon #fIcon
Cs0 f8 expr out #txt
Cs0 f8 64 107 64 453 #arcP
Cs0 f2 guid 15498D4175D375D1 #txt
Cs0 f2 type ch.ivy.addon.portalkit.component.CaseItemRelatedTask.CaseItemRelatedTaskData #txt
Cs0 f2 actionDecl 'ch.ivy.addon.portalkit.component.CaseItemRelatedTask.CaseItemRelatedTaskData out;
' #txt
Cs0 f2 actionTable 'out=in;
' #txt
Cs0 f2 actionCode 'import ch.ivy.addon.portalkit.dto.GlobalCaseId;
import org.primefaces.component.commandlink.CommandLink;

out.caseId = GlobalCaseId.inServer(in.remoteCase.server.id).caseId(in.remoteCase.id).isBusinessCase(in.remoteCase.isBusinessCase()).build();
out.caseName = in.remoteCase.name;

CommandLink commandLink = event.getSource() as CommandLink;
out.taskId = commandLink.getAttributes().get("taskId") as Long;' #txt
Cs0 f2 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>openPortalTasks</name>
        <nameStyle>15,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f2 485 85 22 22 14 0 #rect
Cs0 f2 @|RichDialogProcessStartIcon #fIcon
Cs0 f4 type ch.ivy.addon.portalkit.component.CaseItemRelatedTask.CaseItemRelatedTaskData #txt
Cs0 f4 processCall 'Functional Processes/Navigator:viewTask(Long,ch.ivy.addon.portalkit.dto.GlobalCaseId,String)' #txt
Cs0 f4 doCall true #txt
Cs0 f4 requestActionDecl '<java.lang.Long taskId,ch.ivy.addon.portalkit.dto.GlobalCaseId caseId,java.lang.String caseName> param;
' #txt
Cs0 f4 requestMappingAction 'param.taskId=in.taskId;
param.caseId=in.caseId;
param.caseName=in.caseName;
' #txt
Cs0 f4 responseActionDecl 'ch.ivy.addon.portalkit.component.CaseItemRelatedTask.CaseItemRelatedTaskData out;
' #txt
Cs0 f4 responseMappingAction 'out=in;
' #txt
Cs0 f4 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Navigate to task detail</name>
        <nameStyle>23,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f4 478 212 36 24 20 -2 #rect
Cs0 f4 @|CallSubIcon #fIcon
Cs0 f23 actionDecl 'ch.ivy.addon.portalkit.component.CaseItemRelatedTask.CaseItemRelatedTaskData out;
' #txt
Cs0 f23 actionTable 'out=in;
' #txt
Cs0 f23 actionCode 'import ch.ivy.addon.portalkit.util.MenuUtils;
MenuUtils.clearMenuState();' #txt
Cs0 f23 type ch.ivy.addon.portalkit.component.CaseItemRelatedTask.CaseItemRelatedTaskData #txt
Cs0 f23 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>clear menu state</name>
        <nameStyle>16,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f23 478 148 36 24 20 -2 #rect
Cs0 f23 @|StepIcon #fIcon
Cs0 f11 expr out #txt
Cs0 f11 496 107 496 148 #arcP
Cs0 f5 expr out #txt
Cs0 f5 496 172 496 212 #arcP
Cs0 f12 type ch.ivy.addon.portalkit.component.CaseItemRelatedTask.CaseItemRelatedTaskData #txt
Cs0 f12 processCall MultiPortal/CaseService:findCasesByCriteria(Long,Integer,Integer,ch.ivy.ws.addon.CaseSearchCriteria) #txt
Cs0 f12 doCall true #txt
Cs0 f12 requestActionDecl '<java.lang.Long serverId,java.lang.Integer startIndex,java.lang.Integer count,ch.ivy.ws.addon.CaseSearchCriteria caseSearchCriteria> param;
' #txt
Cs0 f12 requestMappingAction 'param.serverId=in.remoteCase.server.#id;
param.caseSearchCriteria=in.caseSearchCriteria;
' #txt
Cs0 f12 responseActionDecl 'ch.ivy.addon.portalkit.component.CaseItemRelatedTask.CaseItemRelatedTaskData out;
' #txt
Cs0 f12 responseMappingAction 'out=in;
out.technicalCases=result.cases;
' #txt
Cs0 f12 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>find all 
technical cases</name>
        <nameStyle>25,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f12 238 324 36 24 -120 -20 #rect
Cs0 f12 @|CallSubIcon #fIcon
Cs0 f71 type ch.ivy.addon.portalkit.component.CaseItemRelatedTask.CaseItemRelatedTaskData #txt
Cs0 f71 processCall 'Functional Processes/Navigator:viewCase(String,ch.ivy.addon.portalkit.dto.GlobalCaseId)' #txt
Cs0 f71 doCall true #txt
Cs0 f71 requestActionDecl '<java.lang.String caseName,ch.ivy.addon.portalkit.dto.GlobalCaseId caseId> param;
' #txt
Cs0 f71 requestMappingAction 'param.caseName=in.selectedCase.name;
param.caseId=in.selectedGlobalCaseId;
' #txt
Cs0 f71 responseActionDecl 'ch.ivy.addon.portalkit.component.CaseItemRelatedTask.CaseItemRelatedTaskData out;
' #txt
Cs0 f71 responseMappingAction 'out=in;
' #txt
Cs0 f71 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Navigator</name>
        <nameStyle>9,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f71 732 216 36 24 20 -2 #rect
Cs0 f71 @|CallSubIcon #fIcon
Cs0 f69 actionDecl 'ch.ivy.addon.portalkit.component.CaseItemRelatedTask.CaseItemRelatedTaskData out;
' #txt
Cs0 f69 actionTable 'out=in;
' #txt
Cs0 f69 actionCode 'import ch.ivy.addon.portalkit.dto.GlobalCaseId;
in.selectedGlobalCaseId = GlobalCaseId.inServer(in.remoteCase.getServer().id).caseId(in.selectedCase.id).isBusinessCase(in.selectedCase.isBusinessCase()).build();' #txt
Cs0 f69 type ch.ivy.addon.portalkit.component.CaseItemRelatedTask.CaseItemRelatedTaskData #txt
Cs0 f69 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>prepare global case id</name>
        <nameStyle>22,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f69 732 152 36 24 20 -2 #rect
Cs0 f69 @|StepIcon #fIcon
Cs0 f68 guid 15B7AF6A81A3C6E8 #txt
Cs0 f68 type ch.ivy.addon.portalkit.component.CaseItemRelatedTask.CaseItemRelatedTaskData #txt
Cs0 f68 actionDecl 'ch.ivy.addon.portalkit.component.CaseItemRelatedTask.CaseItemRelatedTaskData out;
' #txt
Cs0 f68 actionTable 'out=in;
' #txt
Cs0 f68 actionCode 'import ch.ivy.addon.portalkit.bo.RemoteCase;
import org.primefaces.component.commandlink.CommandLink;

CommandLink commandLink = event.getSource() as CommandLink;
out.selectedCase = commandLink.getAttributes().get("selectedCase") as RemoteCase;' #txt
Cs0 f68 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>navigateToRelatedCase</name>
        <nameStyle>21,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f68 739 89 22 22 14 0 #rect
Cs0 f68 @|RichDialogProcessStartIcon #fIcon
Cs0 f73 type ch.ivy.addon.portalkit.component.CaseItemRelatedTask.CaseItemRelatedTaskData #txt
Cs0 f73 739 313 22 22 14 0 #rect
Cs0 f73 @|RichDialogProcessEndIcon #fIcon
Cs0 f70 expr out #txt
Cs0 f70 750 111 750 152 #arcP
Cs0 f74 expr out #txt
Cs0 f74 750 240 750 313 #arcP
Cs0 f72 expr out #txt
Cs0 f72 750 176 750 216 #arcP
Cs0 f13 actionDecl 'ch.ivy.addon.portalkit.component.CaseItemRelatedTask.CaseItemRelatedTaskData out1;
ch.ivy.addon.portalkit.component.CaseItemRelatedTask.CaseItemRelatedTaskData out2;
' #txt
Cs0 f13 actionTable 'out1=in;
out2=in;
' #txt
Cs0 f13 type ch.ivy.addon.portalkit.component.CaseItemRelatedTask.CaseItemRelatedTaskData #txt
Cs0 f13 242 138 28 28 14 0 #rect
Cs0 f13 @|ThreadIcon #fIcon
Cs0 f15 actionDecl 'ch.ivy.addon.portalkit.component.CaseItemRelatedTask.CaseItemRelatedTaskData out;
' #txt
Cs0 f15 actionTable 'out=in;
' #txt
Cs0 f15 actionCode 'import ch.ivy.addon.portalkit.service.CaseQueryService;
import ch.ivy.addon.portalkit.enums.CaseSortField;
import ch.ivy.addon.portalkit.support.CaseQueryCriteria;
import ch.ivy.ws.addon.CaseSearchCriteria;

//prepare search criteria for technical case
if(in.remoteCase.isBusinessCase()) {
	in.caseSearchCriteria.setTechnicalCase(true);
	in.caseSearchCriteria.setBusinessCaseId(in.remoteCase.getId());
	in.caseSearchCriteria.involvedUsername = ivy.session.getSessionUserName();
	
	CaseQueryCriteria queryCriteria = new CaseQueryCriteria();
	queryCriteria.newQueryCreated = true;
	queryCriteria.sortField = CaseSortField.NAME.toString();
	in.caseSearchCriteria.jsonQuery = CaseQueryService.service().createQuery(queryCriteria).asJson();
}

	' #txt
Cs0 f15 type ch.ivy.addon.portalkit.component.CaseItemRelatedTask.CaseItemRelatedTaskData #txt
Cs0 f15 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>prepare case
search criteria</name>
        <nameStyle>28,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f15 238 195 36 24 -109 -14 #rect
Cs0 f15 @|StepIcon #fIcon
Cs0 f16 actionDecl 'ch.ivy.addon.portalkit.component.CaseItemRelatedTask.CaseItemRelatedTaskData out;
' #txt
Cs0 f16 actionTable 'out=in;
' #txt
Cs0 f16 actionCode 'import ch.ivy.addon.portalkit.util.PermissionUtils;
import ch.ivy.addon.portalkit.enums.TaskSortField;
import ch.ivy.addon.portalkit.service.TaskQueryService;
import ch.ivy.addon.portalkit.support.TaskQueryCriteria;
import ch.ivyteam.ivy.workflow.TaskState;

List<TaskState> includedStates = [];
includedStates.add(TaskState.SUSPENDED);
includedStates.add(TaskState.RESUMED);
includedStates.add(TaskState.PARKED);
includedStates.add(TaskState.UNASSIGNED);

TaskQueryCriteria queryCriteria = new TaskQueryCriteria();
queryCriteria.caseId = in.remoteCase.id;
queryCriteria.includedStates = includedStates;
queryCriteria.newQueryCreated = true;
queryCriteria.sortField = TaskSortField.NAME.toString();
if(in.remoteCase.isBusinessCase()){
	queryCriteria.setQueryByBusinessCaseId(true);
}
out.taskSearchCriteria.jsonQuery = TaskQueryService.service().createQuery(queryCriteria).asJson();
out.taskSearchCriteria.setIgnoreInvolvedUser(PermissionUtils.checkReadAllTasksPermission() || PermissionUtils.checkTaskReadOwnCaseTasksPermission());
out.taskSearchCriteria.setInvolvedUsername(ivy.session.getSessionUserName());' #txt
Cs0 f16 type ch.ivy.addon.portalkit.component.CaseItemRelatedTask.CaseItemRelatedTaskData #txt
Cs0 f16 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>prepare task
search criteria</name>
        <nameStyle>28,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f16 331 196 36 24 20 -14 #rect
Cs0 f16 @|StepIcon #fIcon
Cs0 f3 expr out #txt
Cs0 f3 256 107 256 138 #arcP
Cs0 f14 expr out2 #txt
Cs0 f14 270 152 349 196 #arcP
Cs0 f14 1 349 152 #addKink
Cs0 f14 0 0.826023128828962 0 0 #arcLabel
Cs0 f17 actionDecl 'ch.ivy.addon.portalkit.component.CaseItemRelatedTask.CaseItemRelatedTaskData out;
' #txt
Cs0 f17 actionTable 'out=in1;
out.relatedTasks=in2.relatedTasks;
out.technicalCases=in1.technicalCases;
' #txt
Cs0 f17 242 450 28 28 14 0 #rect
Cs0 f17 @|JoinIcon #fIcon
Cs0 f20 expr out #txt
Cs0 f20 242 464 75 464 #arcP
Cs0 f10 expr out #txt
Cs0 f10 349 220 349 276 #arcP
Cs0 f9 expr out1 #txt
Cs0 f9 256 166 256 195 #arcP
Cs0 f19 expr out #txt
Cs0 f19 type ch.ivy.addon.portalkit.component.CaseItemRelatedTask.CaseItemRelatedTaskData #txt
Cs0 f19 var in2 #txt
Cs0 f19 349 300 270 464 #arcP
Cs0 f19 1 349 464 #addKink
Cs0 f19 1 0.2728286559589661 0 0 #arcLabel
Cs0 f21 type ch.ivy.addon.portalkit.component.CaseItemRelatedTask.CaseItemRelatedTaskData #txt
Cs0 f21 242 258 28 28 14 0 #rect
Cs0 f21 @|AlternativeIcon #fIcon
Cs0 f22 expr out #txt
Cs0 f22 256 219 256 258 #arcP
Cs0 f7 expr in #txt
Cs0 f7 outCond in.remoteCase.isBusinessCase() #txt
Cs0 f7 256 286 256 324 #arcP
Cs0 f24 type ch.ivy.addon.portalkit.component.CaseItemRelatedTask.CaseItemRelatedTaskData #txt
Cs0 f24 242 386 28 28 14 0 #rect
Cs0 f24 @|AlternativeIcon #fIcon
Cs0 f25 expr out #txt
Cs0 f25 256 348 256 386 #arcP
Cs0 f18 expr in #txt
Cs0 f18 type ch.ivy.addon.portalkit.component.CaseItemRelatedTask.CaseItemRelatedTaskData #txt
Cs0 f18 var in1 #txt
Cs0 f18 256 414 256 450 #arcP
Cs0 f26 expr in #txt
Cs0 f26 242 272 242 400 #arcP
Cs0 f26 1 208 272 #addKink
Cs0 f26 2 208 400 #addKink
Cs0 f26 1 0.5 0 0 #arcLabel
>Proto Cs0 .type ch.ivy.addon.portalkit.component.CaseItemRelatedTask.CaseItemRelatedTaskData #txt
>Proto Cs0 .processKind HTML_DIALOG #txt
>Proto Cs0 -8 -8 16 16 16 26 #rect
>Proto Cs0 '' #fIcon
Cs0 f0 mainOut f8 tail #connect
Cs0 f8 head f1 mainIn #connect
Cs0 f2 mainOut f11 tail #connect
Cs0 f11 head f23 mainIn #connect
Cs0 f23 mainOut f5 tail #connect
Cs0 f5 head f4 mainIn #connect
Cs0 f68 mainOut f70 tail #connect
Cs0 f70 head f69 mainIn #connect
Cs0 f69 mainOut f72 tail #connect
Cs0 f72 head f71 mainIn #connect
Cs0 f71 mainOut f74 tail #connect
Cs0 f74 head f73 mainIn #connect
Cs0 f6 mainOut f3 tail #connect
Cs0 f3 head f13 in #connect
Cs0 f13 out f9 tail #connect
Cs0 f9 head f15 mainIn #connect
Cs0 f13 out f14 tail #connect
Cs0 f14 head f16 mainIn #connect
Cs0 f16 mainOut f10 tail #connect
Cs0 f10 head f66 mainIn #connect
Cs0 f66 mainOut f19 tail #connect
Cs0 f19 head f17 in #connect
Cs0 f17 mainOut f20 tail #connect
Cs0 f20 head f1 mainIn #connect
Cs0 f15 mainOut f22 tail #connect
Cs0 f22 head f21 in #connect
Cs0 f21 out f7 tail #connect
Cs0 f7 head f12 mainIn #connect
Cs0 f12 mainOut f25 tail #connect
Cs0 f25 head f24 in #connect
Cs0 f24 out f18 tail #connect
Cs0 f18 head f17 in #connect
Cs0 f21 out f26 tail #connect
Cs0 f26 head f24 in #connect
