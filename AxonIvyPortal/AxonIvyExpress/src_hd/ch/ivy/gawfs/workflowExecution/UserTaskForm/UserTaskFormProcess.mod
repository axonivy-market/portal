[Ivy]
162F0A4F9FF3C1EC 7.5.0 #module
>Proto >Proto Collection #zClass
Us0 UserTaskFormProcess Big #zClass
Us0 RD #cInfo
Us0 #process
Us0 @TextInP .type .type #zField
Us0 @TextInP .processKind .processKind #zField
Us0 @AnnotationInP-0n ai ai #zField
Us0 @MessageFlowInP-0n messageIn messageIn #zField
Us0 @MessageFlowOutP-0n messageOut messageOut #zField
Us0 @TextInP .xml .xml #zField
Us0 @TextInP .responsibility .responsibility #zField
Us0 @UdInit f0 '' #zField
Us0 @UdProcessEnd f1 '' #zField
Us0 @UdEvent f3 '' #zField
Us0 @UdExitEnd f4 '' #zField
Us0 @GridStep f6 '' #zField
Us0 @PushWFArc f7 '' #zField
Us0 @PushWFArc f2 '' #zField
Us0 @UdMethod f8 '' #zField
Us0 @GridStep f9 '' #zField
Us0 @PushWFArc f10 '' #zField
Us0 @UdProcessEnd f11 '' #zField
Us0 @PushWFArc f14 '' #zField
Us0 @GridStep f12 '' #zField
Us0 @PushWFArc f13 '' #zField
Us0 @PushWFArc f5 '' #zField
>Proto Us0 Us0 UserTaskFormProcess #zField
Us0 f0 guid 162F0A4FA20C17F1 #txt
Us0 f0 method start(gawfs.ExecutePredefinedWorkflowData) #txt
Us0 f0 inParameterDecl '<gawfs.ExecutePredefinedWorkflowData executePredefinedWorkflowData> param;' #txt
Us0 f0 inParameterMapAction 'out.executePredefinedWorkflowData=param.executePredefinedWorkflowData;
' #txt
Us0 f0 outParameterDecl '<gawfs.ExecutePredefinedWorkflowData executePredefinedWorkflowData> result;' #txt
Us0 f0 outParameterMapAction 'result.executePredefinedWorkflowData=in.executePredefinedWorkflowData;
' #txt
Us0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start()</name>
        <nameStyle>7,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Us0 f0 83 51 26 26 -16 15 #rect
Us0 f0 @|UdInitIcon #fIcon
Us0 f1 403 51 26 26 0 12 #rect
Us0 f1 @|UdProcessEndIcon #fIcon
Us0 f3 guid 162F0A4FA24F861A #txt
Us0 f3 actionTable 'out=in;
' #txt
Us0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>close</name>
    </language>
</elementInfo>
' #txt
Us0 f3 83 147 26 26 -15 12 #rect
Us0 f3 @|UdEventIcon #fIcon
Us0 f4 403 147 26 26 0 12 #rect
Us0 f4 @|UdExitEndIcon #fIcon
Us0 f6 actionTable 'out=in;
' #txt
Us0 f6 actionCode 'import ch.ivyteam.ivy.security.IUser;
import gawfs.TaskDef;

in.executePredefinedWorkflowData.currentTask.dynaFormController.createForm();

for (TaskDef task : in.executePredefinedWorkflowData.finishedTasks) {
	task.actualApplicant = ivy.wf.getSecurityContext().findUser(task.actualApplicantName);
}' #txt
Us0 f6 security system #txt
Us0 f6 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Initialize</name>
    </language>
</elementInfo>
' #txt
Us0 f6 192 42 112 44 -22 -8 #rect
Us0 f6 @|StepIcon #fIcon
Us0 f7 expr out #txt
Us0 f7 109 64 192 64 #arcP
Us0 f2 expr out #txt
Us0 f2 304 64 403 64 #arcP
Us0 f8 guid 162F0A6B9E82BE32 #txt
Us0 f8 method cancel() #txt
Us0 f8 inParameterDecl '<> param;' #txt
Us0 f8 outParameterDecl '<> result;' #txt
Us0 f8 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>cancel()</name>
    </language>
</elementInfo>
' #txt
Us0 f8 83 243 26 26 -22 15 #rect
Us0 f8 @|UdMethodIcon #fIcon
Us0 f9 actionTable 'out=in;
' #txt
Us0 f9 actionCode 'import ch.ivy.addon.portal.generic.navigation.PortalNavigator;
ivy.task.reset();
PortalNavigator navigator = new PortalNavigator();
navigator.navigateToPortalEndPage();' #txt
Us0 f9 security system #txt
Us0 f9 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Reset task</name>
    </language>
</elementInfo>
' #txt
Us0 f9 192 234 112 44 -29 -8 #rect
Us0 f9 @|StepIcon #fIcon
Us0 f10 expr out #txt
Us0 f10 109 256 192 256 #arcP
Us0 f11 403 243 26 26 0 12 #rect
Us0 f11 @|UdProcessEndIcon #fIcon
Us0 f14 expr out #txt
Us0 f14 304 256 403 256 #arcP
Us0 f12 actionTable 'out=in;
' #txt
Us0 f12 actionCode 'import ch.ivy.addon.portalkit.util.AdhocUtils;

in.executePredefinedWorkflowData.currentTask.actualApplicantName = ivy.session.getSessionUser().getName();
if (in.executePredefinedWorkflowData.originalTaskId != null && in.executePredefinedWorkflowData.originalTaskId > 0) {
	String description = AdhocUtils.getDescriptionOfFirstTask(in.executePredefinedWorkflowData);
	AdhocUtils.storeHistory(in.executePredefinedWorkflowData.getOriginalTaskId(), description);
	ivy.case.getBusinessCase().createNote(ivy.session, ivy.task.getName() + ": " + description);
}' #txt
Us0 f12 security system #txt
Us0 f12 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Update applicant&#xD;
information</name>
    </language>
</elementInfo>
' #txt
Us0 f12 192 138 128 44 -43 -16 #rect
Us0 f12 @|StepIcon #fIcon
Us0 f13 expr out #txt
Us0 f13 109 160 192 160 #arcP
Us0 f5 expr out #txt
Us0 f5 320 160 403 160 #arcP
>Proto Us0 .type ch.ivy.gawfs.workflowExecution.UserTaskForm.UserTaskFormData #txt
>Proto Us0 .processKind HTML_DIALOG #txt
>Proto Us0 -8 -8 16 16 16 26 #rect
>Proto Us0 '' #fIcon
Us0 f0 mainOut f7 tail #connect
Us0 f7 head f6 mainIn #connect
Us0 f6 mainOut f2 tail #connect
Us0 f2 head f1 mainIn #connect
Us0 f8 mainOut f10 tail #connect
Us0 f10 head f9 mainIn #connect
Us0 f9 mainOut f14 tail #connect
Us0 f14 head f11 mainIn #connect
Us0 f3 mainOut f13 tail #connect
Us0 f13 head f12 mainIn #connect
Us0 f12 mainOut f5 tail #connect
Us0 f5 head f4 mainIn #connect
