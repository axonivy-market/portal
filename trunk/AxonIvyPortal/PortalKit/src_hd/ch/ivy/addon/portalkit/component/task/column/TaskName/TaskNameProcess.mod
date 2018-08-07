[Ivy]
[>Created: Mon Mar 20 15:10:20 ICT 2017]
15AE9A62C6A66DD8 3.20 #module
>Proto >Proto Collection #zClass
Ts0 TaskNameProcess Big #zClass
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
Ts0 @RichDialogProcessEnd f69 '' #zField
Ts0 @RichDialogMethodStart f68 '' #zField
Ts0 @PushWFArc f70 '' #zField
Ts0 @GridStep f64 '' #zField
Ts0 @CallSub f58 '' #zField
Ts0 @RichDialogMethodStart f54 '' #zField
Ts0 @RichDialogProcessEnd f57 '' #zField
Ts0 @GridStep f60 '' #zField
Ts0 @CallSub f66 '' #zField
Ts0 @PushWFArc f61 '' #zField
Ts0 @PushWFArc f67 '' #zField
Ts0 @PushWFArc f62 '' #zField
Ts0 @PushWFArc f65 '' #zField
Ts0 @PushWFArc f63 '' #zField
>Proto Ts0 Ts0 TaskNameProcess #zField
Ts0 f0 guid 15AE9A0BAA4D09B7 #txt
Ts0 f0 type ch.ivy.addon.portalkit.component.task.column.TaskName.TaskNameData #txt
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
Ts0 f1 type ch.ivy.addon.portalkit.component.task.column.TaskName.TaskNameData #txt
Ts0 f1 53 213 22 22 14 0 #rect
Ts0 f1 @|RichDialogProcessEndIcon #fIcon
Ts0 f2 expr out #txt
Ts0 f2 64 107 64 213 #arcP
Ts0 f69 type ch.ivy.addon.portalkit.component.task.column.TaskName.TaskNameData #txt
Ts0 f69 182 212 22 22 14 0 #rect
Ts0 f69 @|RichDialogProcessEndIcon #fIcon
Ts0 f68 guid 15AE9B50820D660B #txt
Ts0 f68 type ch.ivy.addon.portalkit.component.task.column.TaskName.TaskNameData #txt
Ts0 f68 method keepOldNameValue(javax.faces.event.ValueChangeEvent) #txt
Ts0 f68 disableUIEvents false #txt
Ts0 f68 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<javax.faces.event.ValueChangeEvent event> param = methodEvent.getInputArguments();
' #txt
Ts0 f68 inParameterMapAction 'out.oldName=param.event.getOldValue() as String;
' #txt
Ts0 f68 outParameterDecl '<> result;
' #txt
Ts0 f68 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>keepOldNameValue(ValueChangeEvent)</name>
    </language>
</elementInfo>
' #txt
Ts0 f68 182 84 22 22 14 0 #rect
Ts0 f68 @|RichDialogMethodStartIcon #fIcon
Ts0 f70 expr out #txt
Ts0 f70 193 106 193 212 #arcP
Ts0 f64 actionDecl 'ch.ivy.addon.portalkit.component.task.column.TaskName.TaskNameData out;
' #txt
Ts0 f64 actionTable 'out=in;
' #txt
Ts0 f64 actionCode 'import ch.ivy.addon.portalkit.service.TaskInforActionService;

TaskInforActionService service = new TaskInforActionService();
in.changeNameNoteContent = service.prepareChaneNameNoteContent(ivy.session.getSessionUser().getDisplayName(), in.task.name, in.oldName);' #txt
Ts0 f64 type ch.ivy.addon.portalkit.component.task.column.TaskName.TaskNameData #txt
Ts0 f64 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>prepare note content</name>
        <nameStyle>20,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ts0 f64 463 277 36 24 20 -2 #rect
Ts0 f64 @|StepIcon #fIcon
Ts0 f58 type ch.ivy.addon.portalkit.component.task.column.TaskName.TaskNameData #txt
Ts0 f58 processCall MultiPortal/TaskService:save(ch.ivy.ws.addon.IvyTask,Long) #txt
Ts0 f58 doCall true #txt
Ts0 f58 requestActionDecl '<ch.ivy.ws.addon.IvyTask task,java.lang.Long serverId> param;
' #txt
Ts0 f58 requestMappingAction 'param.task=in.ivyTask;
param.serverId=in.task.applicationRegister.getServerId();
' #txt
Ts0 f58 responseActionDecl 'ch.ivy.addon.portalkit.component.task.column.TaskName.TaskNameData out;
' #txt
Ts0 f58 responseMappingAction 'out=in;
' #txt
Ts0 f58 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>TaskService</name>
        <nameStyle>11,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ts0 f58 463 213 36 24 20 -2 #rect
Ts0 f58 @|CallSubIcon #fIcon
Ts0 f54 guid 15AE9B6F01034C2C #txt
Ts0 f54 type ch.ivy.addon.portalkit.component.task.column.TaskName.TaskNameData #txt
Ts0 f54 method updateTask(ch.ivy.addon.portalkit.bo.RemoteTask) #txt
Ts0 f54 disableUIEvents false #txt
Ts0 f54 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<ch.ivy.addon.portalkit.bo.RemoteTask task> param = methodEvent.getInputArguments();
' #txt
Ts0 f54 inParameterMapAction 'out.task=param.task;
' #txt
Ts0 f54 outParameterDecl '<> result;
' #txt
Ts0 f54 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>updateName(RemoteTask)</name>
        <nameStyle>22,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ts0 f54 470 86 22 22 14 0 #rect
Ts0 f54 @|RichDialogMethodStartIcon #fIcon
Ts0 f57 type ch.ivy.addon.portalkit.component.task.column.TaskName.TaskNameData #txt
Ts0 f57 470 404 22 22 14 0 #rect
Ts0 f57 @|RichDialogProcessEndIcon #fIcon
Ts0 f60 actionDecl 'ch.ivy.addon.portalkit.component.task.column.TaskName.TaskNameData out;
' #txt
Ts0 f60 actionTable 'out=in;
' #txt
Ts0 f60 actionCode 'import ch.ivy.ws.addon.IvyTask;
import ch.ivy.addon.portalkit.service.TaskInforActionService;

TaskInforActionService service = new TaskInforActionService();
in.task.setName(in.task.name.trim());
in.ivyTask = service.prepareDataToSaveTask(in.task);' #txt
Ts0 f60 type ch.ivy.addon.portalkit.component.task.column.TaskName.TaskNameData #txt
Ts0 f60 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>prepare data</name>
        <nameStyle>12,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ts0 f60 463 149 36 24 20 -2 #rect
Ts0 f60 @|StepIcon #fIcon
Ts0 f66 type ch.ivy.addon.portalkit.component.task.column.TaskName.TaskNameData #txt
Ts0 f66 processCall MultiPortal/CaseService:createNote(ch.ivy.addon.portalkit.persistence.domain.Server,Long,String,String) #txt
Ts0 f66 doCall true #txt
Ts0 f66 requestActionDecl '<ch.ivy.addon.portalkit.persistence.domain.Server server,java.lang.Long caseId,java.lang.String username,java.lang.String content> param;
' #txt
Ts0 f66 requestMappingAction 'param.server=in.task.#applicationRegister.#server;
param.caseId=in.task.case.getId();
param.username=ivy.session.getSessionUserName();
param.content=in.changeNameNoteContent;
' #txt
Ts0 f66 responseActionDecl 'ch.ivy.addon.portalkit.component.task.column.TaskName.TaskNameData out;
' #txt
Ts0 f66 responseMappingAction 'out=in;
' #txt
Ts0 f66 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Add note</name>
        <nameStyle>8,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ts0 f66 463 341 36 24 20 -2 #rect
Ts0 f66 @|CallSubIcon #fIcon
Ts0 f61 expr out #txt
Ts0 f61 481 173 481 213 #arcP
Ts0 f67 expr out #txt
Ts0 f67 481 301 481 341 #arcP
Ts0 f62 expr out #txt
Ts0 f62 481 108 481 149 #arcP
Ts0 f65 expr out #txt
Ts0 f65 481 237 481 277 #arcP
Ts0 f63 expr out #txt
Ts0 f63 481 365 481 404 #arcP
>Proto Ts0 .type ch.ivy.addon.portalkit.component.task.column.TaskName.TaskNameData #txt
>Proto Ts0 .processKind HTML_DIALOG #txt
>Proto Ts0 -8 -8 16 16 16 26 #rect
>Proto Ts0 '' #fIcon
Ts0 f0 mainOut f2 tail #connect
Ts0 f2 head f1 mainIn #connect
Ts0 f68 mainOut f70 tail #connect
Ts0 f70 head f69 mainIn #connect
Ts0 f60 mainOut f61 tail #connect
Ts0 f61 head f58 mainIn #connect
Ts0 f54 mainOut f62 tail #connect
Ts0 f62 head f60 mainIn #connect
Ts0 f58 mainOut f65 tail #connect
Ts0 f65 head f64 mainIn #connect
Ts0 f64 mainOut f67 tail #connect
Ts0 f67 head f66 mainIn #connect
Ts0 f66 mainOut f63 tail #connect
Ts0 f63 head f57 mainIn #connect
