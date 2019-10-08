[Ivy]
153362B0AC312EFB 3.20 #module
>Proto >Proto Collection #zClass
Cs0 CaseItemHistoryProcess Big #zClass
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
Cs0 @RichDialogMethodStart f3 '' #zField
Cs0 @RichDialogProcessEnd f4 '' #zField
Cs0 @GridStep f78 '' #zField
Cs0 @PushWFArc f7 '' #zField
Cs0 @GridStep f64 '' #zField
Cs0 @RichDialogProcessEnd f70 '' #zField
Cs0 @GridStep f72 '' #zField
Cs0 @CallSub f67 '' #zField
Cs0 @RichDialogMethodStart f63 '' #zField
Cs0 @CallSub f16 '' #zField
Cs0 @Alternative f65 '' #zField
Cs0 @GridStep f39 '' #zField
Cs0 @PushWFArc f68 '' #zField
Cs0 @PushWFArc f73 '' #zField
Cs0 @PushWFArc f74 '' #zField
Cs0 @PushWFArc f76 '' #zField
Cs0 @PushWFArc f40 '' #zField
Cs0 @PushWFArc f12 '' #zField
Cs0 @PushWFArc f38 '' #zField
Cs0 @PushWFArc f41 '' #zField
Cs0 @RichDialogProcessStart f10 '' #zField
Cs0 @CallSub f11 '' #zField
Cs0 @GridStep f23 '' #zField
Cs0 @PushWFArc f14 '' #zField
Cs0 @PushWFArc f13 '' #zField
Cs0 @CallSub f15 '' #zField
Cs0 @PushWFArc f5 '' #zField
Cs0 @GridStep f6 '' #zField
Cs0 @CallSub f66 '' #zField
Cs0 @PushWFArc f9 '' #zField
Cs0 @GridStep f18 '' #zField
Cs0 @PushWFArc f19 '' #zField
Cs0 @Alternative f20 '' #zField
Cs0 @PushWFArc f21 '' #zField
Cs0 @PushWFArc f17 '' #zField
Cs0 @GridStep f22 '' #zField
Cs0 @PushWFArc f24 '' #zField
Cs0 @PushWFArc f25 '' #zField
Cs0 @GridStep f26 '' #zField
Cs0 @PushWFArc f27 '' #zField
Cs0 @PushWFArc f8 '' #zField
Cs0 @PushWFArc f30 '' #zField
>Proto Cs0 Cs0 CaseItemHistoryProcess #zField
Cs0 f0 guid 153362B0ADC5C7D8 #txt
Cs0 f0 type ch.ivy.addon.portalkit.component.CaseItemHistory.CaseItemHistoryData #txt
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
Cs0 f1 type ch.ivy.addon.portalkit.component.CaseItemHistory.CaseItemHistoryData #txt
Cs0 f1 53 309 22 22 14 0 #rect
Cs0 f1 @|RichDialogProcessEndIcon #fIcon
Cs0 f3 guid 153362E296C125C3 #txt
Cs0 f3 type ch.ivy.addon.portalkit.component.CaseItemHistory.CaseItemHistoryData #txt
Cs0 f3 method initData(ch.ivy.addon.portalkit.bo.RemoteCase) #txt
Cs0 f3 disableUIEvents false #txt
Cs0 f3 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<ch.ivy.addon.portalkit.bo.RemoteCase remoteCase> param = methodEvent.getInputArguments();
' #txt
Cs0 f3 inParameterMapAction 'out.remoteCase=param.remoteCase;
' #txt
Cs0 f3 outParameterDecl '<> result;
' #txt
Cs0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>initData(RemoteCase)</name>
        <nameStyle>20,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f3 181 85 22 22 14 0 #rect
Cs0 f3 @|RichDialogMethodStartIcon #fIcon
Cs0 f4 type ch.ivy.addon.portalkit.component.CaseItemHistory.CaseItemHistoryData #txt
Cs0 f4 181 621 22 22 14 0 #rect
Cs0 f4 @|RichDialogProcessEndIcon #fIcon
Cs0 f78 actionDecl 'ch.ivy.addon.portalkit.component.CaseItemHistory.CaseItemHistoryData out;
' #txt
Cs0 f78 actionTable 'out=in;
' #txt
Cs0 f78 actionCode 'import ch.ivy.addon.portalkit.service.HistoryService;
import ch.ivy.addon.portalkit.service.GlobalSettingService;

HistoryService historyService = new HistoryService();
GlobalSettingService globalSettingService = new GlobalSettingService();
boolean excludeTechnicalHistory = globalSettingService.findHideSystemTasksFromHistorySettingValue();
in.histories = historyService.createHistories(in.remoteTasks, in.remoteCase.remoteNotes, excludeTechnicalHistory);
' #txt
Cs0 f78 type ch.ivy.addon.portalkit.component.CaseItemHistory.CaseItemHistoryData #txt
Cs0 f78 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>create histories 
from tasks and notes</name>
        <nameStyle>38,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f78 174 564 36 24 20 -1 #rect
Cs0 f78 @|StepIcon #fIcon
Cs0 f7 expr out #txt
Cs0 f7 192 588 192 621 #arcP
Cs0 f64 actionDecl 'ch.ivy.addon.portalkit.component.CaseItemHistory.CaseItemHistoryData out;
' #txt
Cs0 f64 actionTable 'out=in;
' #txt
Cs0 f64 actionCode 'import ch.ivy.ws.addon.WsException;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

for (WsException error : in.errors) {
	FacesContext.getCurrentInstance().addMessage("note-content", new FacesMessage(FacesMessage.SEVERITY_ERROR, error.userText, null));
}
FacesContext.getCurrentInstance().validationFailed();' #txt
Cs0 f64 type ch.ivy.addon.portalkit.component.CaseItemHistory.CaseItemHistoryData #txt
Cs0 f64 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Show errors</name>
        <nameStyle>11,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f64 726 252 36 24 20 -2 #rect
Cs0 f64 @|StepIcon #fIcon
Cs0 f70 type ch.ivy.addon.portalkit.component.CaseItemHistory.CaseItemHistoryData #txt
Cs0 f70 933 365 22 22 14 0 #rect
Cs0 f70 @|RichDialogProcessEndIcon #fIcon
Cs0 f72 actionDecl 'ch.ivy.addon.portalkit.component.CaseItemHistory.CaseItemHistoryData out;
' #txt
Cs0 f72 actionTable 'out=in;
' #txt
Cs0 f72 actionCode 'import ch.ivy.addon.portalkit.bo.RemoteNote;

out.noteContent = "";
RemoteNote remoteNote = new RemoteNote(in.note);
out.remoteCase.getRemoteNotes().add(remoteNote);' #txt
Cs0 f72 type ch.ivy.addon.portalkit.component.CaseItemHistory.CaseItemHistoryData #txt
Cs0 f72 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>clear note field
update case notes</name>
        <nameStyle>34,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f72 526 364 36 24 -36 18 #rect
Cs0 f72 @|StepIcon #fIcon
Cs0 f67 type ch.ivy.addon.portalkit.component.CaseItemHistory.CaseItemHistoryData #txt
Cs0 f67 processCall MultiPortal/CaseService:createNote(ch.ivy.addon.portalkit.persistence.domain.Server,Long,String,String) #txt
Cs0 f67 doCall true #txt
Cs0 f67 requestActionDecl '<ch.ivy.addon.portalkit.persistence.domain.Server server,java.lang.Long caseId,java.lang.String username,java.lang.String content> param;
' #txt
Cs0 f67 requestMappingAction 'param.server=in.remoteCase.server;
param.caseId=in.remoteCase.id;
param.username=ivy.session.getSessionUserName();
param.content=in.noteContent;
' #txt
Cs0 f67 responseActionDecl 'ch.ivy.addon.portalkit.component.CaseItemHistory.CaseItemHistoryData out;
' #txt
Cs0 f67 responseMappingAction 'out=in;
out.errors=result.errors;
out.note=result.note;
' #txt
Cs0 f67 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>CaseService
createNote</name>
        <nameStyle>22,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f67 526 156 36 24 -31 19 #rect
Cs0 f67 @|CallSubIcon #fIcon
Cs0 f63 guid 1533632E93F2AB48 #txt
Cs0 f63 type ch.ivy.addon.portalkit.component.CaseItemHistory.CaseItemHistoryData #txt
Cs0 f63 method createNote() #txt
Cs0 f63 disableUIEvents false #txt
Cs0 f63 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<> param = methodEvent.getInputArguments();
' #txt
Cs0 f63 outParameterDecl '<> result;
' #txt
Cs0 f63 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>createNote()</name>
        <nameStyle>12,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f63 533 85 22 22 14 0 #rect
Cs0 f63 @|RichDialogMethodStartIcon #fIcon
Cs0 f16 type ch.ivy.addon.portalkit.component.CaseItemHistory.CaseItemHistoryData #txt
Cs0 f16 processCall MultiPortal/TaskService:findTasksByCriteria(Long,ch.ivy.ws.addon.TaskSearchCriteria,Integer,Integer) #txt
Cs0 f16 doCall true #txt
Cs0 f16 requestActionDecl '<java.lang.Long serverId,ch.ivy.ws.addon.TaskSearchCriteria taskSearchCriteria,java.lang.Integer startIndex,java.lang.Integer count> param;
' #txt
Cs0 f16 requestMappingAction 'param.serverId=in.remoteCase.server.#id;
param.taskSearchCriteria=in.taskSearchCriteria;
' #txt
Cs0 f16 responseActionDecl 'ch.ivy.addon.portalkit.component.CaseItemHistory.CaseItemHistoryData out;
' #txt
Cs0 f16 responseMappingAction 'out=in;
out.remoteTasks=result.tasks;
' #txt
Cs0 f16 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>find done tasks
 of case</name>
        <nameStyle>24,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f16 662 364 36 24 -32 -49 #rect
Cs0 f16 @|CallSubIcon #fIcon
Cs0 f65 type ch.ivy.addon.portalkit.component.CaseItemHistory.CaseItemHistoryData #txt
Cs0 f65 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>successfully?</name>
        <nameStyle>13,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f65 530 250 28 28 -36 -30 #rect
Cs0 f65 @|AlternativeIcon #fIcon
Cs0 f39 actionDecl 'ch.ivy.addon.portalkit.component.CaseItemHistory.CaseItemHistoryData out;
' #txt
Cs0 f39 actionTable 'out=in;
' #txt
Cs0 f39 actionCode 'import ch.ivy.addon.portalkit.service.HistoryService;
import ch.ivy.addon.portalkit.service.GlobalSettingService;

HistoryService historyService = new HistoryService();
GlobalSettingService globalSettingService = new GlobalSettingService();
boolean excludeTechnicalHistory = globalSettingService.findHideSystemTasksFromHistorySettingValue();
in.histories = historyService.createHistories(in.remoteTasks, in.remoteCase.remoteNotes, excludeTechnicalHistory);' #txt
Cs0 f39 type ch.ivy.addon.portalkit.component.CaseItemHistory.CaseItemHistoryData #txt
Cs0 f39 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>create histories 
from tasks and notes</name>
        <nameStyle>38,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f39 790 364 36 24 20 -1 #rect
Cs0 f39 @|StepIcon #fIcon
Cs0 f68 expr out #txt
Cs0 f68 544 107 544 156 #arcP
Cs0 f73 expr in #txt
Cs0 f73 outCond in.errors.isEmpty() #txt
Cs0 f73 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>yes</name>
        <nameStyle>3,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f73 544 278 544 364 #arcP
Cs0 f73 0 0.5925925925925926 14 0 #arcLabel
Cs0 f74 expr in #txt
Cs0 f74 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>no</name>
        <nameStyle>2,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f74 558 264 726 264 #arcP
Cs0 f74 0 0.43288049074433405 1 13 #arcLabel
Cs0 f76 expr out #txt
Cs0 f76 762 264 944 365 #arcP
Cs0 f76 1 944 264 #addKink
Cs0 f76 0 0.589419446648472 0 0 #arcLabel
Cs0 f40 expr out #txt
Cs0 f40 698 376 790 376 #arcP
Cs0 f12 expr out #txt
Cs0 f12 544 180 544 250 #arcP
Cs0 f38 expr out #txt
Cs0 f38 562 376 662 376 #arcP
Cs0 f41 expr out #txt
Cs0 f41 826 376 933 376 #arcP
Cs0 f10 guid 15498F0AC28118E3 #txt
Cs0 f10 type ch.ivy.addon.portalkit.component.CaseItemHistory.CaseItemHistoryData #txt
Cs0 f10 actionDecl 'ch.ivy.addon.portalkit.component.CaseItemHistory.CaseItemHistoryData out;
' #txt
Cs0 f10 actionTable 'out=in;
' #txt
Cs0 f10 actionCode 'import org.primefaces.component.commandlink.CommandLink;
CommandLink commandLink = event.getSource() as CommandLink;
in.taskId = commandLink.getAttributes().get("taskId") as Long;' #txt
Cs0 f10 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>openPortalTasks</name>
        <nameStyle>15,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f10 1053 77 22 22 14 0 #rect
Cs0 f10 @|RichDialogProcessStartIcon #fIcon
Cs0 f11 type ch.ivy.addon.portalkit.component.CaseItemHistory.CaseItemHistoryData #txt
Cs0 f11 processCall 'Functional Processes/Navigator:viewTask(Long,ch.ivy.addon.portalkit.dto.GlobalCaseId,String)' #txt
Cs0 f11 doCall true #txt
Cs0 f11 requestActionDecl '<java.lang.Long taskId,ch.ivy.addon.portalkit.dto.GlobalCaseId caseId,java.lang.String caseName> param;
' #txt
Cs0 f11 requestMappingAction 'param.taskId=in.taskId;
param.caseId=ch.ivy.addon.portalkit.dto.GlobalCaseId.inServer(in.remoteCase.server.id).caseId(in.remoteCase.id).isBusinessCase(in.remoteCase.isBusinessCase()).build();
param.caseName=in.remoteCase.name;
' #txt
Cs0 f11 responseActionDecl 'ch.ivy.addon.portalkit.component.CaseItemHistory.CaseItemHistoryData out;
' #txt
Cs0 f11 responseMappingAction 'out=in;
' #txt
Cs0 f11 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Navigate to task detail</name>
        <nameStyle>23,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f11 1046 188 36 24 20 -2 #rect
Cs0 f11 @|CallSubIcon #fIcon
Cs0 f23 actionDecl 'ch.ivy.addon.portalkit.component.CaseItemHistory.CaseItemHistoryData out;
' #txt
Cs0 f23 actionTable 'out=in;
' #txt
Cs0 f23 actionCode 'import ch.ivy.addon.portalkit.util.MenuUtils;
MenuUtils.clearMenuState();' #txt
Cs0 f23 type ch.ivy.addon.portalkit.component.CaseItemHistory.CaseItemHistoryData #txt
Cs0 f23 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>clear menu state</name>
        <nameStyle>16,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f23 1046 140 36 24 20 -2 #rect
Cs0 f23 @|StepIcon #fIcon
Cs0 f14 expr out #txt
Cs0 f14 1064 99 1064 140 #arcP
Cs0 f13 expr out #txt
Cs0 f13 1064 164 1064 188 #arcP
Cs0 f15 type ch.ivy.addon.portalkit.component.CaseItemHistory.CaseItemHistoryData #txt
Cs0 f15 processCall MultiPortal/CaseService:findCase(ch.ivy.addon.portalkit.persistence.domain.Server,Long) #txt
Cs0 f15 doCall true #txt
Cs0 f15 requestActionDecl '<ch.ivy.addon.portalkit.persistence.domain.Server server,java.lang.Long caseId> param;
' #txt
Cs0 f15 requestMappingAction 'param.server=in.remoteCase.server;
param.caseId=in.remoteCase.id;
' #txt
Cs0 f15 responseActionDecl 'ch.ivy.addon.portalkit.component.CaseItemHistory.CaseItemHistoryData out;
' #txt
Cs0 f15 responseMappingAction 'out=in;
out.errors=result.errors;
out.remoteCase=result.remoteCase;
' #txt
Cs0 f15 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>CaseService</name>
        <nameStyle>11,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f15 152 492 80 40 -35 -8 #rect
Cs0 f15 @|CallSubIcon #fIcon
Cs0 f5 expr out #txt
Cs0 f5 192 532 192 564 #arcP
Cs0 f6 actionDecl 'ch.ivy.addon.portalkit.component.CaseItemHistory.CaseItemHistoryData out;
' #txt
Cs0 f6 actionTable 'out=in;
' #txt
Cs0 f6 actionCode 'import ch.ivyteam.ivy.workflow.TaskState;
import ch.ivy.addon.portalkit.bo.RemoteTask;

List<TaskState> doneStates = [TaskState.DONE, TaskState.DESTROYED, TaskState.ZOMBIE, TaskState.CREATED];
List<RemoteTask> doneTasks = [];
for (RemoteTask task : in.remoteTasks) {
	if (doneStates.contains(task.getState())) {
		doneTasks.add(task);
	}
}

out.remoteTasks = doneTasks;' #txt
Cs0 f6 type ch.ivy.addon.portalkit.component.CaseItemHistory.CaseItemHistoryData #txt
Cs0 f6 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>get done tasks</name>
        <nameStyle>14,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f6 174 436 36 24 20 -2 #rect
Cs0 f6 @|StepIcon #fIcon
Cs0 f66 type ch.ivy.addon.portalkit.component.CaseItemHistory.CaseItemHistoryData #txt
Cs0 f66 processCall MultiPortal/TaskService:findTasksByCase(Long,String,ch.ivy.addon.portalkit.persistence.domain.Server) #txt
Cs0 f66 doCall true #txt
Cs0 f66 requestActionDecl '<java.lang.Long caseId,java.lang.String involvedUserName,ch.ivy.addon.portalkit.persistence.domain.Server server> param;
' #txt
Cs0 f66 requestMappingAction 'param.caseId=in.remoteCase.id;
param.involvedUserName=null;
param.server=in.remoteCase.server;
' #txt
Cs0 f66 responseActionDecl 'ch.ivy.addon.portalkit.component.CaseItemHistory.CaseItemHistoryData out;
' #txt
Cs0 f66 responseMappingAction 'out=in;
out.remoteTasks=result.tasks;
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
Cs0 f66 174 308 36 24 20 -2 #rect
Cs0 f66 @|CallSubIcon #fIcon
Cs0 f9 expr out #txt
Cs0 f9 192 460 192 492 #arcP
Cs0 f18 actionDecl 'ch.ivy.addon.portalkit.component.CaseItemHistory.CaseItemHistoryData out;
' #txt
Cs0 f18 actionTable 'out=in;
' #txt
Cs0 f18 actionCode 'import ch.ivy.addon.portalkit.persistence.variable.PortalCacheConstants;

out.cacheEntry = ivy.datacache.getSessionCache().getEntry(PortalCacheConstants.TASK_IN_CASE_DETAILS_CACHE_GROUP_NAME, PortalCacheConstants.TASK_IN_CASE_DETAILS_CACHE_ENTRY_NAME + in.remoteCase.id);' #txt
Cs0 f18 type ch.ivy.addon.portalkit.component.CaseItemHistory.CaseItemHistoryData #txt
Cs0 f18 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Get cache</name>
        <nameStyle>9
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f18 136 146 112 44 -27 -8 #rect
Cs0 f18 @|StepIcon #fIcon
Cs0 f19 expr out #txt
Cs0 f19 192 107 192 146 #arcP
Cs0 f20 type ch.ivy.addon.portalkit.component.CaseItemHistory.CaseItemHistoryData #txt
Cs0 f20 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>no cache?</name>
        <nameStyle>9
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f20 176 240 32 32 -28 18 #rect
Cs0 f20 @|AlternativeIcon #fIcon
Cs0 f21 expr out #txt
Cs0 f21 192 190 192 240 #arcP
Cs0 f17 expr in #txt
Cs0 f17 outCond 'in.cacheEntry == null' #txt
Cs0 f17 192 272 192 308 #arcP
Cs0 f22 actionDecl 'ch.ivy.addon.portalkit.component.CaseItemHistory.CaseItemHistoryData out;
' #txt
Cs0 f22 actionTable 'out=in;
' #txt
Cs0 f22 actionCode 'import ch.ivy.addon.portalkit.persistence.variable.PortalCacheConstants;

out.remoteTasks = in.cacheEntry.getValue() as List;
ivy.datacache.getSessionCache().invalidateEntry(ivy.datacache.getSessionCache().getGroup(PortalCacheConstants.TASK_IN_CASE_DETAILS_CACHE_GROUP_NAME), in.cacheEntry);' #txt
Cs0 f22 type ch.ivy.addon.portalkit.component.CaseItemHistory.CaseItemHistoryData #txt
Cs0 f22 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Get from cache
and invalidate it</name>
        <nameStyle>32
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f22 304 290 128 44 -41 -16 #rect
Cs0 f22 @|StepIcon #fIcon
Cs0 f24 expr in #txt
Cs0 f24 208 256 368 290 #arcP
Cs0 f24 1 368 256 #addKink
Cs0 f24 0 0.740259798223094 0 0 #arcLabel
Cs0 f25 expr out #txt
Cs0 f25 368 334 210 448 #arcP
Cs0 f25 1 368 448 #addKink
Cs0 f25 1 0.29266251721548964 0 0 #arcLabel
Cs0 f26 actionDecl 'ch.ivy.addon.portalkit.component.CaseItemHistory.CaseItemHistoryData out;
' #txt
Cs0 f26 actionTable 'out=in;
' #txt
Cs0 f26 actionCode 'import ch.ivy.addon.portalkit.persistence.variable.PortalCacheConstants;

ivy.datacache.getSessionCache().setEntry(PortalCacheConstants.TASK_IN_CASE_DETAILS_CACHE_GROUP_NAME, PortalCacheConstants.TASK_IN_CASE_DETAILS_CACHE_ENTRY_NAME + in.remoteCase.id, 10, in.remoteTasks);' #txt
Cs0 f26 type ch.ivy.addon.portalkit.component.CaseItemHistory.CaseItemHistoryData #txt
Cs0 f26 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Store to cache</name>
        <nameStyle>14
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f26 136 362 112 44 -39 -8 #rect
Cs0 f26 @|StepIcon #fIcon
Cs0 f27 expr out #txt
Cs0 f27 192 332 192 362 #arcP
Cs0 f27 0 0.8128777323151737 0 0 #arcLabel
Cs0 f8 expr out #txt
Cs0 f8 192 406 192 436 #arcP
Cs0 f8 0 0.8128777323151737 0 0 #arcLabel
Cs0 f30 expr out #txt
Cs0 f30 64 107 64 309 #arcP
>Proto Cs0 .type ch.ivy.addon.portalkit.component.CaseItemHistory.CaseItemHistoryData #txt
>Proto Cs0 .processKind HTML_DIALOG #txt
>Proto Cs0 -8 -8 16 16 16 26 #rect
>Proto Cs0 '' #fIcon
Cs0 f78 mainOut f7 tail #connect
Cs0 f7 head f4 mainIn #connect
Cs0 f63 mainOut f68 tail #connect
Cs0 f68 head f67 mainIn #connect
Cs0 f65 out f73 tail #connect
Cs0 f73 head f72 mainIn #connect
Cs0 f65 out f74 tail #connect
Cs0 f74 head f64 mainIn #connect
Cs0 f64 mainOut f76 tail #connect
Cs0 f76 head f70 mainIn #connect
Cs0 f16 mainOut f40 tail #connect
Cs0 f40 head f39 mainIn #connect
Cs0 f67 mainOut f12 tail #connect
Cs0 f12 head f65 in #connect
Cs0 f72 mainOut f38 tail #connect
Cs0 f38 head f16 mainIn #connect
Cs0 f39 mainOut f41 tail #connect
Cs0 f41 head f70 mainIn #connect
Cs0 f10 mainOut f14 tail #connect
Cs0 f14 head f23 mainIn #connect
Cs0 f23 mainOut f13 tail #connect
Cs0 f13 head f11 mainIn #connect
Cs0 f15 mainOut f5 tail #connect
Cs0 f5 head f78 mainIn #connect
Cs0 f6 mainOut f9 tail #connect
Cs0 f9 head f15 mainIn #connect
Cs0 f3 mainOut f19 tail #connect
Cs0 f19 head f18 mainIn #connect
Cs0 f18 mainOut f21 tail #connect
Cs0 f21 head f20 in #connect
Cs0 f20 out f17 tail #connect
Cs0 f17 head f66 mainIn #connect
Cs0 f20 out f24 tail #connect
Cs0 f24 head f22 mainIn #connect
Cs0 f22 mainOut f25 tail #connect
Cs0 f25 head f6 mainIn #connect
Cs0 f66 mainOut f27 tail #connect
Cs0 f27 head f26 mainIn #connect
Cs0 f26 mainOut f8 tail #connect
Cs0 f8 head f6 mainIn #connect
Cs0 f0 mainOut f30 tail #connect
Cs0 f30 head f1 mainIn #connect
