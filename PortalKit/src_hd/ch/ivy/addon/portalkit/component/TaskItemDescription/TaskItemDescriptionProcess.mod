[Ivy]
[>Created: Mon Jul 11 15:48:51 ICT 2016]
15493AB38C76D698 3.18 #module
>Proto >Proto Collection #zClass
Ts0 TaskItemDescriptionProcess Big #zClass
Ts0 RD #cInfo
Ts0 #process
Ts0 @TextInP .ui2RdDataAction .ui2RdDataAction #zField
Ts0 @TextInP .rdData2UIAction .rdData2UIAction #zField
Ts0 @TextInP .resExport .resExport #zField
Ts0 @TextInP .type .type #zField
Ts0 @TextInP .processKind .processKind #zField
Ts0 @AnnotationInP-0n ai ai #zField
Ts0 @MessageFlowInP-0n messageIn messageIn #zField
Ts0 @MessageFlowOutP-0n messageOut messageOut #zField
Ts0 @TextInP .xml .xml #zField
Ts0 @TextInP .responsibility .responsibility #zField
Ts0 @RichDialogInitStart f0 '' #zField
Ts0 @RichDialogProcessEnd f1 '' #zField
Ts0 @PushWFArc f2 '' #zField
Ts0 @RichDialogProcessStart f3 '' #zField
Ts0 @RichDialogEnd f4 '' #zField
Ts0 @PushWFArc f5 '' #zField
Ts0 @RichDialogMethodStart f6 '' #zField
Ts0 @RichDialogProcessEnd f7 '' #zField
Ts0 @GridStep f9 '' #zField
Ts0 @PushWFArc f10 '' #zField
Ts0 @CallSub f11 '' #zField
Ts0 @PushWFArc f12 '' #zField
Ts0 @PushWFArc f8 '' #zField
>Proto Ts0 Ts0 TaskItemDescriptionProcess #zField
Ts0 f0 guid 15493AB38F1A359C #txt
Ts0 f0 type ch.ivy.addon.portalkit.component.TaskItemDescription.TaskItemDescriptionData #txt
Ts0 f0 method start() #txt
Ts0 f0 disableUIEvents true #txt
Ts0 f0 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<> param = methodEvent.getInputArguments();
' #txt
Ts0 f0 outParameterDecl '<> result;
' #txt
Ts0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start()</name>
    </language>
</elementInfo>
' #txt
Ts0 f0 53 85 22 22 14 0 #rect
Ts0 f0 @|RichDialogInitStartIcon #fIcon
Ts0 f1 type ch.ivy.addon.portalkit.component.TaskItemDescription.TaskItemDescriptionData #txt
Ts0 f1 53 213 22 22 14 0 #rect
Ts0 f1 @|RichDialogProcessEndIcon #fIcon
Ts0 f2 expr out #txt
Ts0 f2 64 107 64 213 #arcP
Ts0 f3 guid 15493AB38F7E067F #txt
Ts0 f3 type ch.ivy.addon.portalkit.component.TaskItemDescription.TaskItemDescriptionData #txt
Ts0 f3 actionDecl 'ch.ivy.addon.portalkit.component.TaskItemDescription.TaskItemDescriptionData out;
' #txt
Ts0 f3 actionTable 'out=in;
' #txt
Ts0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>close</name>
    </language>
</elementInfo>
' #txt
Ts0 f3 149 85 22 22 14 0 #rect
Ts0 f3 @|RichDialogProcessStartIcon #fIcon
Ts0 f4 type ch.ivy.addon.portalkit.component.TaskItemDescription.TaskItemDescriptionData #txt
Ts0 f4 guid 15493AB38F86B027 #txt
Ts0 f4 149 213 22 22 14 0 #rect
Ts0 f4 @|RichDialogEndIcon #fIcon
Ts0 f5 expr out #txt
Ts0 f5 160 107 160 213 #arcP
Ts0 f6 guid 155D81BDA88774E0 #txt
Ts0 f6 type ch.ivy.addon.portalkit.component.TaskItemDescription.TaskItemDescriptionData #txt
Ts0 f6 method updateTaskDescription(ch.ivy.addon.portalkit.bo.RemoteTask) #txt
Ts0 f6 disableUIEvents false #txt
Ts0 f6 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<ch.ivy.addon.portalkit.bo.RemoteTask task> param = methodEvent.getInputArguments();
' #txt
Ts0 f6 inParameterMapAction 'out.task=param.task;
' #txt
Ts0 f6 outParameterDecl '<> result;
' #txt
Ts0 f6 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>updateTaskDescription(RemoteTask)</name>
        <nameStyle>33,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ts0 f6 277 85 22 22 14 0 #rect
Ts0 f6 @|RichDialogMethodStartIcon #fIcon
Ts0 f7 type ch.ivy.addon.portalkit.component.TaskItemDescription.TaskItemDescriptionData #txt
Ts0 f7 277 277 22 22 14 0 #rect
Ts0 f7 @|RichDialogProcessEndIcon #fIcon
Ts0 f9 actionDecl 'ch.ivy.addon.portalkit.component.TaskItemDescription.TaskItemDescriptionData out;
' #txt
Ts0 f9 actionTable 'out=in;
' #txt
Ts0 f9 actionCode 'import java.util.Calendar;
import ch.ivy.ws.addon.IvyTask;

in.ivyTask = new IvyTask();

in.ivyTask.id = in.task.id;

if(in.task.#priority is initialized) {
	in.ivyTask.priority = in.task.priority.name();
}

if (in.task.#expiryTimestamp is initialized) {
	Calendar cal = Calendar.getInstance();
	in.ivyTask.expireTimestamp = cal.setTime(in.task.expiryTimestamp);
} else {
	in.ivyTask.expireTimestamp = null;
}

if(in.task.#description is initialized) {
  in.ivyTask.description = in.task.description;
}

in.ivyTask.serverId = in.task.applicationRegister.serverId;

' #txt
Ts0 f9 type ch.ivy.addon.portalkit.component.TaskItemDescription.TaskItemDescriptionData #txt
Ts0 f9 270 148 36 24 20 -2 #rect
Ts0 f9 @|StepIcon #fIcon
Ts0 f10 expr out #txt
Ts0 f10 288 107 288 148 #arcP
Ts0 f11 type ch.ivy.addon.portalkit.component.TaskItemDescription.TaskItemDescriptionData #txt
Ts0 f11 processCall MultiPortal/TaskService:save(ch.ivy.ws.addon.IvyTask,Long) #txt
Ts0 f11 doCall true #txt
Ts0 f11 requestActionDecl '<ch.ivy.ws.addon.IvyTask task,java.lang.Long serverId> param;
' #txt
Ts0 f11 requestMappingAction 'param.task=in.ivyTask;
param.serverId=in.ivyTask.getServerId();
' #txt
Ts0 f11 responseActionDecl 'ch.ivy.addon.portalkit.component.TaskItemDescription.TaskItemDescriptionData out;
' #txt
Ts0 f11 responseMappingAction 'out=in;
' #txt
Ts0 f11 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>TaskService</name>
    </language>
</elementInfo>
' #txt
Ts0 f11 270 212 36 24 20 -2 #rect
Ts0 f11 @|CallSubIcon #fIcon
Ts0 f12 expr out #txt
Ts0 f12 288 172 288 212 #arcP
Ts0 f8 expr out #txt
Ts0 f8 288 236 288 277 #arcP
>Proto Ts0 .type ch.ivy.addon.portalkit.component.TaskItemDescription.TaskItemDescriptionData #txt
>Proto Ts0 .processKind HTML_DIALOG #txt
>Proto Ts0 -8 -8 16 16 16 26 #rect
>Proto Ts0 '' #fIcon
Ts0 f0 mainOut f2 tail #connect
Ts0 f2 head f1 mainIn #connect
Ts0 f3 mainOut f5 tail #connect
Ts0 f5 head f4 mainIn #connect
Ts0 f6 mainOut f10 tail #connect
Ts0 f10 head f9 mainIn #connect
Ts0 f9 mainOut f12 tail #connect
Ts0 f12 head f11 mainIn #connect
Ts0 f11 mainOut f8 tail #connect
Ts0 f8 head f7 mainIn #connect
