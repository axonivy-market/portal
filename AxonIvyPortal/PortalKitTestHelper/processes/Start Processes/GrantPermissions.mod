[Ivy]
[>Created: Wed Aug 10 10:42:57 ICT 2016]
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
Is0 @StartRequest f10 '' #zField
Is0 @GridStep f14 '' #zField
Is0 @PushWFArc f15 '' #zField
Is0 @EndTask f11 '' #zField
Is0 @PushWFArc f12 '' #zField
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
Is0 f10 outLink updatePermissionsOfTestUsers.ivp #txt
Is0 f10 type ch.ivy.add.portalkit.Data #txt
Is0 f10 inParamDecl '<> param;' #txt
Is0 f10 actionDecl 'ch.ivy.add.portalkit.Data out;
' #txt
Is0 f10 guid 1566F40C1F6ACB8B #txt
Is0 f10 requestEnabled true #txt
Is0 f10 triggerEnabled false #txt
Is0 f10 callSignature updatePermissionsOfTestUsers() #txt
Is0 f10 persist false #txt
Is0 f10 startDescription updatePermissionsOfDefaultUsersAsPreconditionToRunTests #txt
Is0 f10 taskData 'TaskTriggered.ROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.EXPRI=2
TaskTriggered.TYPE=0
TaskTriggered.PRI=2
TaskTriggered.EXROL=Everybody' #txt
Is0 f10 showInStartList 1 #txt
Is0 f10 taskAndCaseSetupAction 'import ch.ivyteam.ivy.workflow.TaskUpdateDefinition;
ch.ivyteam.ivy.workflow.TaskUpdateDefinition taskUpdDef = new ch.ivyteam.ivy.workflow.TaskUpdateDefinition();
import ch.ivyteam.ivy.request.impl.DefaultCalendarProxy;
DefaultCalendarProxy calendarProxy = ivy.cal as DefaultCalendarProxy;
taskUpdDef.setPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
taskUpdDef.setExpiryActivator("Everybody");
taskUpdDef.setExpiryPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
engine.updateCurrentTask(taskUpdDef);
' #txt
Is0 f10 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>updatePermissionsOfTestUsers.ivp</name>
    </language>
</elementInfo>
' #txt
Is0 f10 @C|.responsibility Everybody #txt
Is0 f10 395 83 26 26 -78 -31 #rect
Is0 f10 @|StartRequestIcon #fIcon
Is0 f14 actionDecl 'ch.ivy.add.portalkit.Data out;
' #txt
Is0 f14 actionTable 'out=in;
' #txt
Is0 f14 actionCode 'import ch.ivy.addon.portalkit.test.util.SecurityUtils;

SecurityUtils.updatePermissionsOfTestUsers();' #txt
Is0 f14 security system #txt
Is0 f14 type ch.ivy.add.portalkit.Data #txt
Is0 f14 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name></name>
    </language>
</elementInfo>
' #txt
Is0 f14 390 181 36 24 20 -2 #rect
Is0 f14 @|StepIcon #fIcon
Is0 f15 expr out #txt
Is0 f15 408 109 408 181 #arcP
Is0 f11 type ch.ivy.add.portalkit.Data #txt
Is0 f11 395 339 26 26 14 0 #rect
Is0 f11 @|EndIcon #fIcon
Is0 f12 expr out #txt
Is0 f12 408 205 408 339 #arcP
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
Is0 f10 mainOut f15 tail #connect
Is0 f15 head f14 mainIn #connect
Is0 f14 mainOut f12 tail #connect
Is0 f12 head f11 mainIn #connect
