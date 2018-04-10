[Ivy]
153362B0AC312EFB 3.23 #module
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
Cs0 @PushWFArc f2 '' #zField
Cs0 @RichDialogMethodStart f3 '' #zField
Cs0 @RichDialogProcessEnd f4 '' #zField
Cs0 @CallSub f66 '' #zField
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
Cs0 @GridStep f8 '' #zField
Cs0 @PushWFArc f9 '' #zField
Cs0 @PushWFArc f6 '' #zField
Cs0 @RichDialogProcessStart f10 '' #zField
Cs0 @CallSub f11 '' #zField
Cs0 @GridStep f23 '' #zField
Cs0 @PushWFArc f14 '' #zField
Cs0 @PushWFArc f13 '' #zField
Cs0 @CallSub f15 '' #zField
Cs0 @PushWFArc f17 '' #zField
Cs0 @PushWFArc f5 '' #zField
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
Cs0 f1 53 213 22 22 14 0 #rect
Cs0 f1 @|RichDialogProcessEndIcon #fIcon
Cs0 f2 expr out #txt
Cs0 f2 64 107 64 213 #arcP
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
Cs0 f3 165 85 22 22 14 0 #rect
Cs0 f3 @|RichDialogMethodStartIcon #fIcon
Cs0 f4 type ch.ivy.addon.portalkit.component.CaseItemHistory.CaseItemHistoryData #txt
Cs0 f4 165 389 22 22 14 0 #rect
Cs0 f4 @|RichDialogProcessEndIcon #fIcon
Cs0 f66 type ch.ivy.addon.portalkit.component.CaseItemHistory.CaseItemHistoryData #txt
Cs0 f66 processCall MultiPortal/TaskService:findTasksByCriteria(Long,ch.ivy.ws.addon.TaskSearchCriteria,Integer,Integer) #txt
Cs0 f66 doCall true #txt
Cs0 f66 requestActionDecl '<java.lang.Long serverId,ch.ivy.ws.addon.TaskSearchCriteria taskSearchCriteria,java.lang.Integer startIndex,java.lang.Integer count> param;
' #txt
Cs0 f66 requestMappingAction 'param.serverId=in.remoteCase.server.#id;
param.taskSearchCriteria=in.taskSearchCriteria;
' #txt
Cs0 f66 responseActionDecl 'ch.ivy.addon.portalkit.component.CaseItemHistory.CaseItemHistoryData out;
' #txt
Cs0 f66 responseMappingAction 'out=in;
out.remoteTasks=result.tasks;
' #txt
Cs0 f66 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>find done tasks
 of case</name>
        <nameStyle>24,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f66 158 212 36 24 20 -2 #rect
Cs0 f66 @|CallSubIcon #fIcon
Cs0 f78 actionDecl 'ch.ivy.addon.portalkit.component.CaseItemHistory.CaseItemHistoryData out;
' #txt
Cs0 f78 actionTable 'out=in;
' #txt
Cs0 f78 actionCode 'import ch.ivy.addon.portalkit.service.HistoryService;

HistoryService historyService = new HistoryService();
in.histories = historyService.createHistories(in.remoteTasks, in.remoteCase.remoteNotes);
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
Cs0 f78 158 332 36 24 20 -1 #rect
Cs0 f78 @|StepIcon #fIcon
Cs0 f7 expr out #txt
Cs0 f7 176 356 176 389 #arcP
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
Cs0 f64 590 260 36 24 20 -2 #rect
Cs0 f64 @|StepIcon #fIcon
Cs0 f70 type ch.ivy.addon.portalkit.component.CaseItemHistory.CaseItemHistoryData #txt
Cs0 f70 797 373 22 22 14 0 #rect
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
Cs0 f72 390 372 36 24 -36 18 #rect
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
Cs0 f67 390 164 36 24 -31 19 #rect
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
Cs0 f63 397 93 22 22 14 0 #rect
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
Cs0 f16 526 372 36 24 -32 -49 #rect
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
Cs0 f65 394 258 28 28 -36 -30 #rect
Cs0 f65 @|AlternativeIcon #fIcon
Cs0 f39 actionDecl 'ch.ivy.addon.portalkit.component.CaseItemHistory.CaseItemHistoryData out;
' #txt
Cs0 f39 actionTable 'out=in;
' #txt
Cs0 f39 actionCode 'import ch.ivy.addon.portalkit.service.HistoryService;

HistoryService historyService = new HistoryService();
in.histories = historyService.createHistories(in.remoteTasks, in.remoteCase.remoteNotes);' #txt
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
Cs0 f39 654 372 36 24 20 -1 #rect
Cs0 f39 @|StepIcon #fIcon
Cs0 f68 expr out #txt
Cs0 f68 408 115 408 164 #arcP
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
Cs0 f73 408 286 408 372 #arcP
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
Cs0 f74 422 272 590 272 #arcP
Cs0 f74 0 0.43288049074433405 1 13 #arcLabel
Cs0 f76 expr out #txt
Cs0 f76 626 272 808 373 #arcP
Cs0 f76 1 808 272 #addKink
Cs0 f76 0 0.589419446648472 0 0 #arcLabel
Cs0 f40 expr out #txt
Cs0 f40 562 384 654 384 #arcP
Cs0 f12 expr out #txt
Cs0 f12 408 188 408 258 #arcP
Cs0 f38 expr out #txt
Cs0 f38 426 384 526 384 #arcP
Cs0 f41 expr out #txt
Cs0 f41 690 384 797 384 #arcP
Cs0 f8 actionDecl 'ch.ivy.addon.portalkit.component.CaseItemHistory.CaseItemHistoryData out;
' #txt
Cs0 f8 actionTable 'out=in;
' #txt
Cs0 f8 actionCode 'import ch.ivy.addon.portalkit.service.TaskQueryService;
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
Cs0 f8 type ch.ivy.addon.portalkit.component.CaseItemHistory.CaseItemHistoryData #txt
Cs0 f8 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>prepare search criteria</name>
        <nameStyle>23,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f8 158 148 36 24 20 -2 #rect
Cs0 f8 @|StepIcon #fIcon
Cs0 f9 expr out #txt
Cs0 f9 176 107 176 148 #arcP
Cs0 f6 expr out #txt
Cs0 f6 176 172 176 212 #arcP
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
Cs0 f10 117 437 22 22 14 0 #rect
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
Cs0 f11 110 548 36 24 20 -2 #rect
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
Cs0 f23 110 500 36 24 20 -2 #rect
Cs0 f23 @|StepIcon #fIcon
Cs0 f14 expr out #txt
Cs0 f14 128 459 128 500 #arcP
Cs0 f13 expr out #txt
Cs0 f13 128 524 128 548 #arcP
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
Cs0 f15 136 260 80 40 -35 -8 #rect
Cs0 f15 @|CallSubIcon #fIcon
Cs0 f17 expr out #txt
Cs0 f17 176 236 176 260 #arcP
Cs0 f5 expr out #txt
Cs0 f5 176 300 176 332 #arcP
>Proto Cs0 .type ch.ivy.addon.portalkit.component.CaseItemHistory.CaseItemHistoryData #txt
>Proto Cs0 .processKind HTML_DIALOG #txt
>Proto Cs0 -8 -8 16 16 16 26 #rect
>Proto Cs0 '' #fIcon
Cs0 f0 mainOut f2 tail #connect
Cs0 f2 head f1 mainIn #connect
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
Cs0 f3 mainOut f9 tail #connect
Cs0 f9 head f8 mainIn #connect
Cs0 f8 mainOut f6 tail #connect
Cs0 f6 head f66 mainIn #connect
Cs0 f10 mainOut f14 tail #connect
Cs0 f14 head f23 mainIn #connect
Cs0 f23 mainOut f13 tail #connect
Cs0 f13 head f11 mainIn #connect
Cs0 f66 mainOut f17 tail #connect
Cs0 f17 head f15 mainIn #connect
Cs0 f15 mainOut f5 tail #connect
Cs0 f5 head f78 mainIn #connect
