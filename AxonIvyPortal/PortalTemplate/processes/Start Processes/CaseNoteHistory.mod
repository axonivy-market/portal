[Ivy]
1603506A872272C6 3.20 #module
>Proto >Proto Collection #zClass
Cy0 CaseNoteHistory Big #zClass
Cy0 B #cInfo
Cy0 #process
Cy0 @TextInP .resExport .resExport #zField
Cy0 @TextInP .type .type #zField
Cy0 @TextInP .processKind .processKind #zField
Cy0 @AnnotationInP-0n ai ai #zField
Cy0 @MessageFlowInP-0n messageIn messageIn #zField
Cy0 @MessageFlowOutP-0n messageOut messageOut #zField
Cy0 @TextInP .xml .xml #zField
Cy0 @TextInP .responsibility .responsibility #zField
Cy0 @StartRequest f0 '' #zField
Cy0 @EndTask f1 '' #zField
Cy0 @RichDialog f3 '' #zField
Cy0 @PushWFArc f2 '' #zField
Cy0 @GridStep f5 '' #zField
Cy0 @PushWFArc f6 '' #zField
Cy0 @CallSub f7 '' #zField
Cy0 @PushWFArc f8 '' #zField
Cy0 @GridStep f4 '' #zField
Cy0 @CallSub f9 '' #zField
Cy0 @GridStep f10 '' #zField
Cy0 @PushWFArc f11 '' #zField
Cy0 @PushWFArc f12 '' #zField
Cy0 @PushWFArc f13 '' #zField
Cy0 @PushWFArc f14 '' #zField
Cy0 @StartRequest f15 '' #zField
Cy0 @GridStep f17 '' #zField
Cy0 @PushWFArc f18 '' #zField
Cy0 @EndTask f16 '' #zField
Cy0 @RichDialog f20 '' #zField
Cy0 @PushWFArc f21 '' #zField
Cy0 @PushWFArc f19 '' #zField
>Proto Cy0 Cy0 CaseNoteHistory #zField
Cy0 f0 outLink showCaseNoteHistory.ivp #txt
Cy0 f0 type ch.ivy.addon.portal.generic.CaseNoteHistoryData #txt
Cy0 f0 inParamDecl '<java.lang.Long serverId,java.lang.Long caseId> param;' #txt
Cy0 f0 inParamTable 'out.remoteCaseId=param.caseId;
out.serverId=param.serverId;
' #txt
Cy0 f0 actionDecl 'ch.ivy.addon.portal.generic.CaseNoteHistoryData out;
' #txt
Cy0 f0 guid 1603506A874F3E01 #txt
Cy0 f0 requestEnabled true #txt
Cy0 f0 triggerEnabled false #txt
Cy0 f0 callSignature showCaseNoteHistory(Long,Long) #txt
Cy0 f0 persist false #txt
Cy0 f0 taskData 'TaskTriggered.ROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.EXPRI=2
TaskTriggered.TYPE=0
TaskTriggered.PRI=2
TaskTriggered.EXROL=Everybody' #txt
Cy0 f0 caseData businessCase.attach=true #txt
Cy0 f0 showInStartList 0 #txt
Cy0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>showCaseNoteHistory.ivp</name>
        <nameStyle>23,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cy0 f0 @C|.responsibility Everybody #txt
Cy0 f0 65 73 30 30 -71 17 #rect
Cy0 f0 @|StartRequestIcon #fIcon
Cy0 f1 type ch.ivy.addon.portal.generic.CaseNoteHistoryData #txt
Cy0 f1 1345 73 30 30 0 15 #rect
Cy0 f1 @|EndIcon #fIcon
Cy0 f3 targetWindow NEW #txt
Cy0 f3 targetDisplay TOP #txt
Cy0 f3 richDialogId ch.ivy.addon.portal.generic.CaseNoteHistory #txt
Cy0 f3 startMethod start(List<ch.ivy.addon.portalkit.bo.History>,String) #txt
Cy0 f3 type ch.ivy.addon.portal.generic.CaseNoteHistoryData #txt
Cy0 f3 requestActionDecl '<List<ch.ivy.addon.portalkit.bo.History> histories, String exportedFileName> param;' #txt
Cy0 f3 requestMappingAction 'param.histories=in.histories;
param.exportedFileName=ivy.cms.co("/ch.ivy.addon.portalkit.ui.jsf/noteHistory/caseExportedFileNamePrefix", java.util.Arrays.asList(in.remoteCase.name));
' #txt
Cy0 f3 responseActionDecl 'ch.ivy.addon.portal.generic.CaseNoteHistoryData out;
' #txt
Cy0 f3 responseMappingAction 'out=in;
' #txt
Cy0 f3 isAsynch false #txt
Cy0 f3 isInnerRd false #txt
Cy0 f3 userContext '* ' #txt
Cy0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Case Note History</name>
        <nameStyle>17,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cy0 f3 1096 66 112 44 -50 -8 #rect
Cy0 f3 @|RichDialogIcon #fIcon
Cy0 f2 expr out #txt
Cy0 f2 1208 88 1345 88 #arcP
Cy0 f5 actionDecl 'ch.ivy.addon.portal.generic.CaseNoteHistoryData out;
' #txt
Cy0 f5 actionTable 'out=in;
' #txt
Cy0 f5 actionCode 'import java.util.Arrays;
import ch.ivy.addon.portalkit.service.CaseQueryService;
import ch.ivy.addon.portalkit.support.CaseQueryCriteria;
import ch.ivy.ws.addon.CaseSearchCriteria;

CaseQueryCriteria queryCriteria = new CaseQueryCriteria();
List<String> applications = Arrays.asList(ivy.request.getApplication().getName());
out.caseSearchCriteria.involvedApplications = applications;
queryCriteria.caseId = in.remoteCaseId;
queryCriteria.newQueryCreated = true;
out.caseSearchCriteria.jsonQuery = CaseQueryService.service().createQuery(queryCriteria).asJson();' #txt
Cy0 f5 type ch.ivy.addon.portal.generic.CaseNoteHistoryData #txt
Cy0 f5 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>prepare case search criteria</name>
        <nameStyle>28,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cy0 f5 168 66 160 44 -77 -8 #rect
Cy0 f5 @|StepIcon #fIcon
Cy0 f6 expr out #txt
Cy0 f6 95 88 168 88 #arcP
Cy0 f7 type ch.ivy.addon.portal.generic.CaseNoteHistoryData #txt
Cy0 f7 processCall MultiPortal/CaseService:findCasesByCriteria(Long,Integer,Integer,ch.ivy.ws.addon.CaseSearchCriteria) #txt
Cy0 f7 doCall true #txt
Cy0 f7 requestActionDecl '<java.lang.Long serverId,java.lang.Integer startIndex,java.lang.Integer count,ch.ivy.ws.addon.CaseSearchCriteria caseSearchCriteria> param;
' #txt
Cy0 f7 requestMappingAction 'param.serverId=in.serverId;
param.caseSearchCriteria=in.caseSearchCriteria;
' #txt
Cy0 f7 responseActionDecl 'ch.ivy.addon.portal.generic.CaseNoteHistoryData out;
' #txt
Cy0 f7 responseMappingAction 'out=in;
out.remoteCase=result.cases.get(0);
' #txt
Cy0 f7 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>get remote case</name>
        <nameStyle>15,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cy0 f7 384 66 112 44 -44 -8 #rect
Cy0 f7 @|CallSubIcon #fIcon
Cy0 f8 expr out #txt
Cy0 f8 328 88 384 88 #arcP
Cy0 f4 actionDecl 'ch.ivy.addon.portal.generic.CaseNoteHistoryData out;
' #txt
Cy0 f4 actionTable 'out=in;
' #txt
Cy0 f4 actionCode 'import ch.ivy.addon.portalkit.service.TaskQueryService;
import ch.ivy.addon.portalkit.support.TaskQueryCriteria;
import ch.ivyteam.ivy.workflow.TaskState;

List<TaskState> includedStates = [];
includedStates.add(TaskState.DONE);
includedStates.add(TaskState.DESTROYED);

includedStates.add(TaskState.ZOMBIE);
includedStates.add(TaskState.CREATED);

TaskQueryCriteria queryCriteria = new TaskQueryCriteria();
queryCriteria.caseId = in.remoteCase.id;
queryCriteria.includedStates = includedStates;
queryCriteria.newQueryCreated = true;
queryCriteria.setQueryByBusinessCaseId(in.remoteCase.isBusinessCase());
out.taskSearchCriteria.jsonQuery = TaskQueryService.service().createQuery(queryCriteria).asJson();' #txt
Cy0 f4 type ch.ivy.addon.portal.generic.CaseNoteHistoryData #txt
Cy0 f4 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>prepare task
search criteria</name>
        <nameStyle>28,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cy0 f4 536 66 112 44 -39 -16 #rect
Cy0 f4 @|StepIcon #fIcon
Cy0 f9 type ch.ivy.addon.portal.generic.CaseNoteHistoryData #txt
Cy0 f9 processCall MultiPortal/TaskService:findTasksByCriteria(Long,ch.ivy.ws.addon.TaskSearchCriteria,Integer,Integer) #txt
Cy0 f9 doCall true #txt
Cy0 f9 requestActionDecl '<java.lang.Long serverId,ch.ivy.ws.addon.TaskSearchCriteria taskSearchCriteria,java.lang.Integer startIndex,java.lang.Integer count> param;
' #txt
Cy0 f9 requestMappingAction 'param.serverId=in.remoteCase.server.#id;
param.taskSearchCriteria=in.taskSearchCriteria;
' #txt
Cy0 f9 responseActionDecl 'ch.ivy.addon.portal.generic.CaseNoteHistoryData out;
' #txt
Cy0 f9 responseMappingAction 'out=in;
out.remoteTasks=result.tasks;
' #txt
Cy0 f9 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>find done tasks
 of case</name>
        <nameStyle>24,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cy0 f9 696 66 112 44 -42 -20 #rect
Cy0 f9 @|CallSubIcon #fIcon
Cy0 f10 actionDecl 'ch.ivy.addon.portal.generic.CaseNoteHistoryData out;
' #txt
Cy0 f10 actionTable 'out=in;
' #txt
Cy0 f10 actionCode 'import ch.ivy.addon.portalkit.service.HistoryService;
import ch.ivy.addon.portalkit.service.GlobalSettingService;

HistoryService historyService = new HistoryService();
GlobalSettingService globalSettingSerive = new GlobalSettingService();
boolean excludeTechnicalHistory = globalSettingSerive.findHideSystemTasksFromHistorySettingValue();
in.histories = historyService.createHistories(in.remoteTasks, in.remoteCase.remoteNotes, excludeTechnicalHistory);
' #txt
Cy0 f10 type ch.ivy.addon.portal.generic.CaseNoteHistoryData #txt
Cy0 f10 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>create histories 
from tasks and notes</name>
        <nameStyle>38,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cy0 f10 880 66 144 44 -53 -16 #rect
Cy0 f10 @|StepIcon #fIcon
Cy0 f11 expr out #txt
Cy0 f11 496 88 536 88 #arcP
Cy0 f12 expr out #txt
Cy0 f12 648 88 696 88 #arcP
Cy0 f13 expr out #txt
Cy0 f13 808 88 880 88 #arcP
Cy0 f14 expr out #txt
Cy0 f14 1024 88 1096 88 #arcP
Cy0 f15 outLink showCaseNoteHistoryInTask.ivp #txt
Cy0 f15 type ch.ivy.addon.portal.generic.CaseNoteHistoryData #txt
Cy0 f15 inParamDecl '<java.lang.Long caseId> param;' #txt
Cy0 f15 inParamTable 'out.caseId=param.caseId;
' #txt
Cy0 f15 actionDecl 'ch.ivy.addon.portal.generic.CaseNoteHistoryData out;
' #txt
Cy0 f15 guid 16048E4424ED1B60 #txt
Cy0 f15 requestEnabled true #txt
Cy0 f15 triggerEnabled false #txt
Cy0 f15 callSignature showCaseNoteHistoryInTask(Long) #txt
Cy0 f15 persist false #txt
Cy0 f15 taskData 'TaskTriggered.ROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.EXPRI=2
TaskTriggered.TYPE=0
TaskTriggered.PRI=2
TaskTriggered.EXROL=Everybody' #txt
Cy0 f15 caseData businessCase.attach=true #txt
Cy0 f15 showInStartList 0 #txt
Cy0 f15 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>showCaseNoteHistoryInTask.ivp</name>
        <nameStyle>29,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cy0 f15 @C|.responsibility Everybody #txt
Cy0 f15 73 209 30 30 -89 17 #rect
Cy0 f15 @|StartRequestIcon #fIcon
Cy0 f17 actionDecl 'ch.ivy.addon.portal.generic.CaseNoteHistoryData out;
' #txt
Cy0 f17 actionTable 'out=in;
' #txt
Cy0 f17 actionCode 'import java.util.ArrayList;
import ch.ivyteam.ivy.workflow.TaskState;
import ch.ivyteam.ivy.workflow.ITask;
import ch.ivy.addon.portalkit.service.HistoryService;
import ch.ivy.addon.portalkit.service.GlobalSettingService;

List<ITask> finishedTasks = new ArrayList();
in.internalCase = ivy.wf.findCase(in.caseId);
for(ITask task : in.internalCase.getTasks()) {
	if(task.getState().equals(TaskState.DONE) || task.getState().equals(TaskState.DESTROYED) || task.getState().equals(TaskState.ZOMBIE)) {
		finishedTasks.add(task);
	}
}

HistoryService historyService = new HistoryService();
GlobalSettingService globalSettingSerive = new GlobalSettingService();
boolean excludeTechnicalHistory = globalSettingSerive.findHideSystemTasksFromHistorySettingValue();
in.histories = historyService.getHistories(finishedTasks, in.internalCase.getNotes(), excludeTechnicalHistory);' #txt
Cy0 f17 security system #txt
Cy0 f17 type ch.ivy.addon.portal.generic.CaseNoteHistoryData #txt
Cy0 f17 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>get case
create histories 
from tasks and notes</name>
        <nameStyle>47,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cy0 f17 208 194 144 60 -53 -24 #rect
Cy0 f17 @|StepIcon #fIcon
Cy0 f18 expr out #txt
Cy0 f18 103 224 208 224 #arcP
Cy0 f16 type ch.ivy.addon.portal.generic.CaseNoteHistoryData #txt
Cy0 f16 585 209 30 30 0 15 #rect
Cy0 f16 @|EndIcon #fIcon
Cy0 f20 targetWindow NEW #txt
Cy0 f20 targetDisplay TOP #txt
Cy0 f20 richDialogId ch.ivy.addon.portal.generic.CaseNoteHistory #txt
Cy0 f20 startMethod start(List<ch.ivy.addon.portalkit.bo.History>,String) #txt
Cy0 f20 type ch.ivy.addon.portal.generic.CaseNoteHistoryData #txt
Cy0 f20 requestActionDecl '<List<ch.ivy.addon.portalkit.bo.History> histories, String exportedFileName> param;' #txt
Cy0 f20 requestMappingAction 'param.histories=in.histories;
param.exportedFileName=ivy.cms.co("/ch.ivy.addon.portalkit.ui.jsf/noteHistory/caseExportedFileNamePrefix", java.util.Arrays.asList(in.internalCase.name));
' #txt
Cy0 f20 responseActionDecl 'ch.ivy.addon.portal.generic.CaseNoteHistoryData out;
' #txt
Cy0 f20 responseMappingAction 'out=in;
' #txt
Cy0 f20 isAsynch false #txt
Cy0 f20 isInnerRd false #txt
Cy0 f20 userContext '* ' #txt
Cy0 f20 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Case note history</name>
        <nameStyle>17,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cy0 f20 416 202 112 44 -48 -8 #rect
Cy0 f20 @|RichDialogIcon #fIcon
Cy0 f21 expr out #txt
Cy0 f21 352 224 416 224 #arcP
Cy0 f19 expr out #txt
Cy0 f19 528 224 585 224 #arcP
>Proto Cy0 .type ch.ivy.addon.portal.generic.CaseNoteHistoryData #txt
>Proto Cy0 .processKind NORMAL #txt
>Proto Cy0 0 0 32 24 18 0 #rect
>Proto Cy0 @|BIcon #fIcon
Cy0 f3 mainOut f2 tail #connect
Cy0 f2 head f1 mainIn #connect
Cy0 f0 mainOut f6 tail #connect
Cy0 f6 head f5 mainIn #connect
Cy0 f5 mainOut f8 tail #connect
Cy0 f8 head f7 mainIn #connect
Cy0 f7 mainOut f11 tail #connect
Cy0 f11 head f4 mainIn #connect
Cy0 f4 mainOut f12 tail #connect
Cy0 f12 head f9 mainIn #connect
Cy0 f9 mainOut f13 tail #connect
Cy0 f13 head f10 mainIn #connect
Cy0 f10 mainOut f14 tail #connect
Cy0 f14 head f3 mainIn #connect
Cy0 f15 mainOut f18 tail #connect
Cy0 f18 head f17 mainIn #connect
Cy0 f17 mainOut f21 tail #connect
Cy0 f21 head f20 mainIn #connect
Cy0 f20 mainOut f19 tail #connect
Cy0 f19 head f16 mainIn #connect
