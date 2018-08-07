[Ivy]
[>Created: Thu Jan 14 11:28:01 ICT 2016]
14DE09882B540AD5 3.18 #module
>Proto >Proto Collection #zClass
Is0 GrantPermissions Big #zClass
Is0 B #cInfo
Is0 #process
Is0 @TextInP .resExport .resExport #zField
Is0 @TextInP .type .type #zField
Is0 @TextInP .processKind .processKind #zField
Is0 @AnnotationInP-0n ai ai #zField
Is0 @MessageFlowInP-0n messageIn messageIn #zField
Is0 @MessageFlowOutP-0n messageOut messageOut #zField
Is0 @TextInP .xml .xml #zField
Is0 @TextInP .responsibility .responsibility #zField
Is0 @StartRequest f0 '' #zField
Is0 @EndTask f1 '' #zField
Is0 @GridStep f3 '' #zField
Is0 @PushWFArc f4 '' #zField
Is0 @PushWFArc f2 '' #zField
Is0 @StartRequest f5 '' #zField
Is0 @GridStep f6 '' #zField
Is0 @EndTask f7 '' #zField
Is0 @PushWFArc f8 '' #zField
Is0 @PushWFArc f9 '' #zField
>Proto Is0 Is0 GrantPermissions #zField
Is0 f0 outLink grantAllPermissionsToCurrentUser.ivp #txt
Is0 f0 type ch.ivy.add.portalkit.Data #txt
Is0 f0 inParamDecl '<> param;' #txt
Is0 f0 actionDecl 'ch.ivy.add.portalkit.Data out;
' #txt
Is0 f0 guid 14DE09882C3DA6FB #txt
Is0 f0 requestEnabled true #txt
Is0 f0 triggerEnabled false #txt
Is0 f0 callSignature grantAllPermissionsToCurrentUser() #txt
Is0 f0 persist false #txt
Is0 f0 startName 'Grant all permissions to current user in Designer' #txt
Is0 f0 taskData 'TaskTriggered.ROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.EXPRI=2
TaskTriggered.TYPE=0
TaskTriggered.PRI=2
TaskTriggered.EXROL=Everybody' #txt
Is0 f0 showInStartList 1 #txt
Is0 f0 taskAndCaseSetupAction 'import ch.ivyteam.ivy.workflow.TaskUpdateDefinition;
ch.ivyteam.ivy.workflow.TaskUpdateDefinition taskUpdDef = new ch.ivyteam.ivy.workflow.TaskUpdateDefinition();
import ch.ivyteam.ivy.request.impl.DefaultCalendarProxy;
DefaultCalendarProxy calendarProxy = ivy.cal as DefaultCalendarProxy;
taskUpdDef.setPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
taskUpdDef.setExpiryActivator("Everybody");
taskUpdDef.setExpiryPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
engine.updateCurrentTask(taskUpdDef);
' #txt
Is0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>grantAllPermissionsToCurrentUser.ivp</name>
        <nameStyle>36,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Is0 f0 @C|.responsibility Everybody #txt
Is0 f0 67 83 26 26 -61 -36 #rect
Is0 f0 @|StartRequestIcon #fIcon
Is0 f1 type ch.ivy.add.portalkit.Data #txt
Is0 f1 67 339 26 26 14 0 #rect
Is0 f1 @|EndIcon #fIcon
Is0 f3 actionDecl 'ch.ivy.add.portalkit.Data out;
' #txt
Is0 f3 actionTable 'out=in;
' #txt
Is0 f3 actionCode 'import ch.ivy.addon.portalkit.test.util.SecurityUtils;

SecurityUtils.grantAllPermissionsTo(ivy.session.getSessionUser());' #txt
Is0 f3 security system #txt
Is0 f3 type ch.ivy.add.portalkit.Data #txt
Is0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name></name>
    </language>
</elementInfo>
' #txt
Is0 f3 62 212 36 24 20 -2 #rect
Is0 f3 @|StepIcon #fIcon
Is0 f4 expr out #txt
Is0 f4 80 109 80 212 #arcP
Is0 f2 expr out #txt
Is0 f2 80 236 80 339 #arcP
Is0 f5 outLink grantPermissionsToAdmin.ivp #txt
Is0 f5 type ch.ivy.add.portalkit.Data #txt
Is0 f5 inParamDecl '<> param;' #txt
Is0 f5 actionDecl 'ch.ivy.add.portalkit.Data out;
' #txt
Is0 f5 guid 1521A4CE65350EE1 #txt
Is0 f5 requestEnabled true #txt
Is0 f5 triggerEnabled false #txt
Is0 f5 callSignature grantPermissionsToAdmin() #txt
Is0 f5 persist false #txt
Is0 f5 startName '(For autotest) Grant needed permissions to Admin in Designer' #txt
Is0 f5 taskData 'TaskTriggered.ROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.EXPRI=2
TaskTriggered.TYPE=0
TaskTriggered.PRI=2
TaskTriggered.EXROL=Everybody' #txt
Is0 f5 showInStartList 1 #txt
Is0 f5 taskAndCaseSetupAction 'import ch.ivyteam.ivy.workflow.TaskUpdateDefinition;
ch.ivyteam.ivy.workflow.TaskUpdateDefinition taskUpdDef = new ch.ivyteam.ivy.workflow.TaskUpdateDefinition();
import ch.ivyteam.ivy.request.impl.DefaultCalendarProxy;
DefaultCalendarProxy calendarProxy = ivy.cal as DefaultCalendarProxy;
taskUpdDef.setPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
taskUpdDef.setExpiryActivator("Everybody");
taskUpdDef.setExpiryPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
engine.updateCurrentTask(taskUpdDef);
' #txt
Is0 f5 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>grantPermissionsToAdmin.ivp</name>
        <nameStyle>27,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Is0 f5 @C|.responsibility Everybody #txt
Is0 f5 363 83 26 26 -70 -32 #rect
Is0 f5 @|StartRequestIcon #fIcon
Is0 f6 actionDecl 'ch.ivy.add.portalkit.Data out;
' #txt
Is0 f6 actionTable 'out=in;
' #txt
Is0 f6 actionCode 'import ch.ivy.addon.portalkit.test.util.SecurityUtils;

SecurityUtils.grantPermissionToAdminUser();' #txt
Is0 f6 security system #txt
Is0 f6 type ch.ivy.add.portalkit.Data #txt
Is0 f6 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name></name>
    </language>
</elementInfo>
' #txt
Is0 f6 358 212 36 24 20 -2 #rect
Is0 f6 @|StepIcon #fIcon
Is0 f7 type ch.ivy.add.portalkit.Data #txt
Is0 f7 363 339 26 26 14 0 #rect
Is0 f7 @|EndIcon #fIcon
Is0 f8 expr out #txt
Is0 f8 376 109 376 212 #arcP
Is0 f9 expr out #txt
Is0 f9 376 236 376 339 #arcP
>Proto Is0 .type ch.ivy.add.portalkit.Data #txt
>Proto Is0 .processKind NORMAL #txt
>Proto Is0 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <swimlaneLabel>Request start</swimlaneLabel>
    </language>
    <swimlaneOrientation>true</swimlaneOrientation>
    <swimlaneSize>512</swimlaneSize>
    <swimlaneColor gradient="false">-1</swimlaneColor>
    <swimlaneType>LANE</swimlaneType>
    <swimlaneSpaceBefore>0</swimlaneSpaceBefore>
</elementInfo>
' #txt
>Proto Is0 0 0 32 24 18 0 #rect
>Proto Is0 @|BIcon #fIcon
Is0 f0 mainOut f4 tail #connect
Is0 f4 head f3 mainIn #connect
Is0 f3 mainOut f2 tail #connect
Is0 f2 head f1 mainIn #connect
Is0 f5 mainOut f8 tail #connect
Is0 f8 head f6 mainIn #connect
Is0 f6 mainOut f9 tail #connect
Is0 f9 head f7 mainIn #connect
