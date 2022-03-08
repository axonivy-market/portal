[Ivy]
1747BF67941CE352 7.5.0 #module
>Proto >Proto Collection #zClass
Ts0 TaskItemWorkflowEventsProcess Big #zClass
Ts0 RD #cInfo
Ts0 #process
Ts0 @TextInP .type .type #zField
Ts0 @TextInP .processKind .processKind #zField
Ts0 @TextInP .xml .xml #zField
Ts0 @TextInP .responsibility .responsibility #zField
Ts0 @UdInit f0 '' #zField
Ts0 @GridStep f8 '' #zField
Ts0 @UdProcessEnd f11 '' #zField
Ts0 @UdMethod f10 '' #zField
Ts0 @PushWFArc f13 '' #zField
Ts0 @PushWFArc f2 '' #zField
Ts0 @UdProcessEnd f3 '' #zField
Ts0 @PushWFArc f1 '' #zField
>Proto Ts0 Ts0 TaskItemWorkflowEventsProcess #zField
Ts0 f0 guid 1747BF679887637F #txt
Ts0 f0 method start() #txt
Ts0 f0 inParameterDecl '<> param;' #txt
Ts0 f0 outParameterDecl '<> result;' #txt
Ts0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start()</name>
    </language>
</elementInfo>
' #txt
Ts0 f0 83 51 26 26 -16 15 #rect
Ts0 f0 @|UdInitIcon #fIcon
Ts0 f8 actionTable 'out=in;
' #txt
Ts0 f8 actionCode 'import ch.ivy.addon.portalkit.bo.History;
import ch.ivyteam.ivy.workflow.TaskState;
import ch.ivy.addon.portalkit.util.PermissionUtils;
import ch.ivy.addon.portalkit.service.HistoryService;

HistoryService historyService = new HistoryService();
in.workflowEvents.clear();
if (PermissionUtils.checkReadAllWorkflowEventPermission()) {
	in.workflowEvents.addAll(historyService.createHistoryForTaskWorkflowEvents(in.task));
}

if (PermissionUtils.checkReadAllTasksPermission()
		&& (in.task.getState() == TaskState.FAILED || in.task.getState() == TaskState.JOIN_FAILED)) {
		History failedReason = historyService.createFailedReasonFrom(in.task);
		failedReason.involvedFullname = "";
		in.workflowEvents.add(failedReason);
}' #txt
Ts0 f8 security system #txt
Ts0 f8 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Find Events</name>
    </language>
</elementInfo>
' #txt
Ts0 f8 192 170 112 44 -32 -8 #rect
Ts0 f8 @|StepIcon #fIcon
Ts0 f11 371 179 26 26 0 12 #rect
Ts0 f11 @|UdProcessEndIcon #fIcon
Ts0 f10 guid 1747C052FA32B78C #txt
Ts0 f10 method getWorkflowEvents(Long) #txt
Ts0 f10 inParameterDecl '<Long taskId> param;' #txt
Ts0 f10 inActionCode 'import ch.ivy.addon.portalkit.util.TaskUtils;
if (param.taskId != null) {
	out.task = TaskUtils.findTaskById(param.taskId);
}' #txt
Ts0 f10 outParameterDecl '<> result;' #txt
Ts0 f10 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>getWorkflowEvents(Long)</name>
    </language>
</elementInfo>
' #txt
Ts0 f10 83 179 26 26 -52 18 #rect
Ts0 f10 @|UdMethodIcon #fIcon
Ts0 f13 304 192 371 192 #arcP
Ts0 f2 109 192 192 192 #arcP
Ts0 f3 243 51 26 26 0 12 #rect
Ts0 f3 @|UdProcessEndIcon #fIcon
Ts0 f1 109 64 243 64 #arcP
>Proto Ts0 .type ch.ivy.addon.portalkit.component.TaskItemWorkflowEvents.TaskItemWorkflowEventsData #txt
>Proto Ts0 .processKind HTML_DIALOG #txt
>Proto Ts0 -8 -8 16 16 16 26 #rect
>Proto Ts0 '' #fIcon
Ts0 f8 mainOut f13 tail #connect
Ts0 f13 head f11 mainIn #connect
Ts0 f10 mainOut f2 tail #connect
Ts0 f2 head f8 mainIn #connect
Ts0 f0 mainOut f1 tail #connect
Ts0 f1 head f3 mainIn #connect
