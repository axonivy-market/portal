[Ivy]
[>Created: Mon Apr 11 11:35:41 ICT 2016]
153358BE9219FD4C 3.18 #module
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
Cs0 f66 processCall MultiPortal/TaskService:findTasksByCriteria(ch.ivy.ws.addon.TaskSearchCriteria,Integer,Integer) #txt
Cs0 f66 doCall true #txt
Cs0 f66 requestActionDecl '<ch.ivy.ws.addon.TaskSearchCriteria taskSearchCriteria,java.lang.Integer startIndex,java.lang.Integer count> param;
' #txt
Cs0 f66 requestMappingAction 'param.taskSearchCriteria=in.taskSearchCriteria;
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
Cs0 f3 actionCode 'import ch.ivy.addon.portalkit.util.SecurityServiceUtils;
import ch.ivy.ws.addon.TaskState;

List<TaskState> includedStates = [];
includedStates.add(TaskState.SUSPENDED);
includedStates.add(TaskState.RESUMED);
includedStates.add(TaskState.PARKED);

out.taskSearchCriteria.caseId = in.remoteCase.id;
out.taskSearchCriteria.includedStates = includedStates;

out.tasksOfCaseProcessLink = SecurityServiceUtils.findProcessByUserFriendlyRequestPath("Start Processes/PortalTemplatePages/PortalTaskListOfCase.ivp");' #txt
Cs0 f3 type ch.ivy.addon.portalkit.component.CaseItemRelatedTask.CaseItemRelatedTaskData #txt
Cs0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>prepare search criteria</name>
        <nameStyle>23
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
Cs0 f10 256 236 74 303 #arcP
Cs0 f10 1 256 288 #addKink
Cs0 f10 1 0.3176868602803867 0 0 #arcLabel
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
