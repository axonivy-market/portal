[Ivy]
[>Created: Mon Feb 06 17:30:54 ICT 2017]
153358BE9219FD4C 3.19 #module
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
Cs0 @GridStep f3 '' #zField
Cs0 @PushWFArc f9 '' #zField
Cs0 @PushWFArc f7 '' #zField
Cs0 @PushWFArc f10 '' #zField
Cs0 @RichDialogProcessStart f2 '' #zField
Cs0 @CallSub f4 '' #zField
Cs0 @GridStep f23 '' #zField
Cs0 @PushWFArc f11 '' #zField
Cs0 @PushWFArc f5 '' #zField
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
Cs0 f1 53 293 22 22 14 0 #rect
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
out.relatedTasks=result.tasks;
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
Cs0 f66 238 212 36 24 20 -2 #rect
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
Cs0 f8 64 107 64 293 #arcP
Cs0 f3 actionDecl 'ch.ivy.addon.portalkit.component.CaseItemRelatedTask.CaseItemRelatedTaskData out;
' #txt
Cs0 f3 actionTable 'out=in;
' #txt
Cs0 f3 actionCode 'import ch.ivy.ws.addon.TaskState;

List<TaskState> includedStates = [];
includedStates.add(TaskState.SUSPENDED);
includedStates.add(TaskState.RESUMED);
includedStates.add(TaskState.PARKED);
includedStates.add(TaskState.UNASSIGNED);

out.taskSearchCriteria.caseId = in.remoteCase.id;
out.taskSearchCriteria.includedStates = includedStates;
' #txt
Cs0 f3 type ch.ivy.addon.portalkit.component.CaseItemRelatedTask.CaseItemRelatedTaskData #txt
Cs0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>prepare search criteria</name>
        <nameStyle>23,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f3 238 148 36 24 20 -2 #rect
Cs0 f3 @|StepIcon #fIcon
Cs0 f9 expr out #txt
Cs0 f9 256 107 256 148 #arcP
Cs0 f7 expr out #txt
Cs0 f7 256 172 256 212 #arcP
Cs0 f10 expr out #txt
Cs0 f10 256 236 75 304 #arcP
Cs0 f10 1 256 304 #addKink
Cs0 f10 1 0.3176868602803867 0 0 #arcLabel
Cs0 f2 guid 15498D4175D375D1 #txt
Cs0 f2 type ch.ivy.addon.portalkit.component.CaseItemRelatedTask.CaseItemRelatedTaskData #txt
Cs0 f2 actionDecl 'ch.ivy.addon.portalkit.component.CaseItemRelatedTask.CaseItemRelatedTaskData out;
' #txt
Cs0 f2 actionTable 'out=in;
' #txt
Cs0 f2 actionCode 'import ch.ivy.addon.portalkit.dto.GlobalCaseId;
import org.primefaces.component.commandlink.CommandLink;

out.caseId = GlobalCaseId.inServer(in.remoteCase.server.id).caseId(in.remoteCase.id).build();
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
>Proto Cs0 .type ch.ivy.addon.portalkit.component.CaseItemRelatedTask.CaseItemRelatedTaskData #txt
>Proto Cs0 .processKind HTML_DIALOG #txt
>Proto Cs0 -8 -8 16 16 16 26 #rect
>Proto Cs0 '' #fIcon
Cs0 f0 mainOut f8 tail #connect
Cs0 f8 head f1 mainIn #connect
Cs0 f6 mainOut f9 tail #connect
Cs0 f9 head f3 mainIn #connect
Cs0 f3 mainOut f7 tail #connect
Cs0 f7 head f66 mainIn #connect
Cs0 f66 mainOut f10 tail #connect
Cs0 f10 head f1 mainIn #connect
Cs0 f2 mainOut f11 tail #connect
Cs0 f11 head f23 mainIn #connect
Cs0 f23 mainOut f5 tail #connect
Cs0 f5 head f4 mainIn #connect
