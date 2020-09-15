[Ivy]
160451A075522446 7.5.0 #module
>Proto >Proto Collection #zClass
Ts0 TaskNoteHistoryProcess Big #zClass
Ts0 RD #cInfo
Ts0 #process
Ts0 @TextInP .type .type #zField
Ts0 @TextInP .processKind .processKind #zField
Ts0 @AnnotationInP-0n ai ai #zField
Ts0 @MessageFlowInP-0n messageIn messageIn #zField
Ts0 @MessageFlowOutP-0n messageOut messageOut #zField
Ts0 @TextInP .xml .xml #zField
Ts0 @TextInP .responsibility .responsibility #zField
Ts0 @UdInit f0 '' #zField
Ts0 @UdProcessEnd f1 '' #zField
Ts0 @PushWFArc f2 '' #zField
Ts0 @UdInit f3 '' #zField
Ts0 @UdProcessEnd f4 '' #zField
Ts0 @GridStep f6 '' #zField
Ts0 @PushWFArc f7 '' #zField
Ts0 @PushWFArc f5 '' #zField
Ts0 @GridStep f8 '' #zField
Ts0 @UdMethod f9 '' #zField
Ts0 @PushWFArc f10 '' #zField
Ts0 @UdProcessEnd f11 '' #zField
Ts0 @PushWFArc f12 '' #zField
>Proto Ts0 Ts0 TaskNoteHistoryProcess #zField
Ts0 f0 guid 160451A076641B8C #txt
Ts0 f0 method start(java.util.List<ch.ivyteam.ivy.workflow.INote>,String) #txt
Ts0 f0 inParameterDecl '<java.util.List<ch.ivyteam.ivy.workflow.INote> notes,String exportedFileName> param;' #txt
Ts0 f0 inParameterMapAction 'out.exportedFileName=param.exportedFileName;
out.filteredNotes=param.notes;
out.notes=param.notes;
' #txt
Ts0 f0 outParameterDecl '<> result;' #txt
Ts0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start(List&lt;INote&gt;,String)</name>
    </language>
</elementInfo>
' #txt
Ts0 f0 83 51 26 26 -52 15 #rect
Ts0 f0 @|UdInitIcon #fIcon
Ts0 f1 211 51 26 26 0 12 #rect
Ts0 f1 @|UdProcessEndIcon #fIcon
Ts0 f2 expr out #txt
Ts0 f2 109 64 211 64 #arcP
Ts0 f3 guid 173D76A4286257BE #txt
Ts0 f3 method startHistories(ch.ivyteam.ivy.workflow.ITask,String,java.util.List<ch.ivyteam.ivy.workflow.INote>) #txt
Ts0 f3 inParameterDecl '<ch.ivyteam.ivy.workflow.ITask task,String exportedFileName,java.util.List<ch.ivyteam.ivy.workflow.INote> notes> param;' #txt
Ts0 f3 inParameterMapAction 'out.exportedFileName=param.exportedFileName;
out.filteredNotes=param.notes;
out.notes=param.notes;
out.task=param.task;
' #txt
Ts0 f3 outParameterDecl '<> result;' #txt
Ts0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>startHistories(ITask,String,List&lt;INote&gt;)</name>
    </language>
</elementInfo>
' #txt
Ts0 f3 83 187 26 26 -62 24 #rect
Ts0 f3 @|UdInitIcon #fIcon
Ts0 f4 435 187 26 26 0 12 #rect
Ts0 f4 @|UdProcessEndIcon #fIcon
Ts0 f6 actionTable 'out=in;
' #txt
Ts0 f6 actionCode 'import ch.ivy.addon.portalkit.util.PermissionUtils;

in.isRenderEventsInfo = PermissionUtils.checkReadAllWorkflowEventPermission();' #txt
Ts0 f6 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>init</name>
    </language>
</elementInfo>
' #txt
Ts0 f6 240 178 112 44 -8 -8 #rect
Ts0 f6 @|StepIcon #fIcon
Ts0 f7 109 200 240 200 #arcP
Ts0 f5 352 200 435 200 #arcP
Ts0 f8 actionTable 'out=in;
' #txt
Ts0 f8 actionCode 'import ch.ivy.addon.portalkit.util.PermissionUtils;
import ch.ivy.addon.portalkit.service.GlobalSettingService;
import ch.ivyteam.ivy.workflow.TaskState;
import ch.ivy.addon.portalkit.service.HistoryService;

GlobalSettingService globalSettingService = new GlobalSettingService();
boolean excludeSystemNotes = globalSettingService.findHideSystemNotesFromHistorySettingValue();

HistoryService historyService = new HistoryService();
in.workflowEvents.clear();
if (PermissionUtils.checkReadAllTasksPermission()
		&& (in.task.getState() == TaskState.FAILED || in.task.getState() == TaskState.JOIN_FAILED)) {
	in.workflowEvents.add(historyService.createHistoryFrom(in.task));
}

if (PermissionUtils.checkReadAllWorkflowEventPermission()) {
	in.workflowEvents.addAll(historyService.createHistoryForTaskWorkflowEvents(in.task));
}' #txt
Ts0 f8 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Find Events</name>
    </language>
</elementInfo>
' #txt
Ts0 f8 232 330 112 44 -32 -8 #rect
Ts0 f8 @|StepIcon #fIcon
Ts0 f9 guid 173DB4D850437010 #txt
Ts0 f9 method getWorkflowEvents() #txt
Ts0 f9 inParameterDecl '<> param;' #txt
Ts0 f9 outParameterDecl '<> result;' #txt
Ts0 f9 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>getWorkflowEvents()</name>
    </language>
</elementInfo>
' #txt
Ts0 f9 83 339 26 26 -52 18 #rect
Ts0 f9 @|UdMethodIcon #fIcon
Ts0 f10 109 352 232 352 #arcP
Ts0 f11 435 339 26 26 0 12 #rect
Ts0 f11 @|UdProcessEndIcon #fIcon
Ts0 f12 344 352 435 352 #arcP
>Proto Ts0 .type ch.ivy.addon.portal.generic.TaskNoteHistory.TaskNoteHistoryData #txt
>Proto Ts0 .processKind HTML_DIALOG #txt
>Proto Ts0 -8 -8 16 16 16 26 #rect
>Proto Ts0 '' #fIcon
Ts0 f0 mainOut f2 tail #connect
Ts0 f2 head f1 mainIn #connect
Ts0 f3 mainOut f7 tail #connect
Ts0 f7 head f6 mainIn #connect
Ts0 f6 mainOut f5 tail #connect
Ts0 f5 head f4 mainIn #connect
Ts0 f9 mainOut f10 tail #connect
Ts0 f10 head f8 mainIn #connect
Ts0 f8 mainOut f12 tail #connect
Ts0 f12 head f11 mainIn #connect
