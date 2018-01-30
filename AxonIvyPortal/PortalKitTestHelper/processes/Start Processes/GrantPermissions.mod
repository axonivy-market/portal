[Ivy]
14DE09882B540AD5 3.20 #module
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
Is0 @StartRequest f5 '' #zField
Is0 @GridStep f6 '' #zField
Is0 @PushWFArc f8 '' #zField
Is0 @StartRequest f13 '' #zField
Is0 @GridStep f17 '' #zField
Is0 @PushWFArc f19 '' #zField
Is0 @GridStep f20 '' #zField
Is0 @StartRequest f21 '' #zField
Is0 @PushWFArc f24 '' #zField
Is0 @EndTask f7 '' #zField
Is0 @PushWFArc f9 '' #zField
Is0 @PushWFArc f16 '' #zField
Is0 @PushWFArc f18 '' #zField
Is0 @GridStep f22 '' #zField
Is0 @StartRequest f23 '' #zField
Is0 @PushWFArc f25 '' #zField
Is0 @PushWFArc f26 '' #zField
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
Is0 f3 actionCode 'import ch.ivyteam.ivy.security.IPermission;
import com.ulcjava.base.client.IPendingRequestsOwner;
import ch.ivy.addon.portalkit.test.util.SecurityUtils;

ivy.task.getApplication().getSecurityDescriptor().grantPermission(IPermission.TASK_WRITE_EXPIRY_TIMESTAMP, ivy.session.getSessionUser());' #txt
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
Is0 f5 outLink grantTaskReadAllPermissionsToCurrentUser.ivp #txt
Is0 f5 type ch.ivy.add.portalkit.Data #txt
Is0 f5 inParamDecl '<> param;' #txt
Is0 f5 actionDecl 'ch.ivy.add.portalkit.Data out;
' #txt
Is0 f5 guid 16144EA1731575FE #txt
Is0 f5 requestEnabled true #txt
Is0 f5 triggerEnabled false #txt
Is0 f5 callSignature grantTaskReadAllPermissionsToCurrentUser() #txt
Is0 f5 persist false #txt
Is0 f5 startName 'Grant TASK_READ_ALL to current user in Designer' #txt
Is0 f5 taskData 'TaskTriggered.ROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.EXPRI=2
TaskTriggered.TYPE=0
TaskTriggered.PRI=2
TaskTriggered.EXROL=Everybody' #txt
Is0 f5 caseData businessCase.attach=false #txt
Is0 f5 showInStartList 1 #txt
Is0 f5 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>grantTaskReadAllPermissionsToCurrentUser.ivp</name>
        <nameStyle>44,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Is0 f5 @C|.responsibility Everybody #txt
Is0 f5 66 451 26 26 -61 -33 #rect
Is0 f5 @|StartRequestIcon #fIcon
Is0 f6 actionDecl 'ch.ivy.add.portalkit.Data out;
' #txt
Is0 f6 actionTable 'out=in;
' #txt
Is0 f6 actionCode 'import ch.ivyteam.ivy.security.IPermission;
import com.ulcjava.base.client.IPendingRequestsOwner;
import ch.ivy.addon.portalkit.test.util.SecurityUtils;

ivy.task.getApplication().getSecurityDescriptor().grantPermission(IPermission.TASK_READ_ALL, ivy.session.getSessionUser());' #txt
Is0 f6 security system #txt
Is0 f6 type ch.ivy.add.portalkit.Data #txt
Is0 f6 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name></name>
    </language>
</elementInfo>
' #txt
Is0 f6 149 452 36 24 20 -2 #rect
Is0 f6 @|StepIcon #fIcon
Is0 f8 expr out #txt
Is0 f8 92 464 149 464 #arcP
Is0 f13 outLink grantCaseReadAllPermissionsToCurrentUser.ivp #txt
Is0 f13 type ch.ivy.add.portalkit.Data #txt
Is0 f13 inParamDecl '<> param;' #txt
Is0 f13 actionDecl 'ch.ivy.add.portalkit.Data out;
' #txt
Is0 f13 guid 16144EA264D7BAD3 #txt
Is0 f13 requestEnabled true #txt
Is0 f13 triggerEnabled false #txt
Is0 f13 callSignature grantCaseReadAllPermissionsToCurrentUser() #txt
Is0 f13 persist false #txt
Is0 f13 startName 'Grant CASE_READ_ALL permissions to current user in Designer' #txt
Is0 f13 taskData 'TaskTriggered.ROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.EXPRI=2
TaskTriggered.TYPE=0
TaskTriggered.PRI=2
TaskTriggered.EXROL=Everybody' #txt
Is0 f13 caseData businessCase.attach=false #txt
Is0 f13 showInStartList 1 #txt
Is0 f13 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>grantCaseReadAllPermissionsToCurrentUser.ivp</name>
        <nameStyle>44,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Is0 f13 @C|.responsibility Everybody #txt
Is0 f13 66 514 26 26 -62 -31 #rect
Is0 f13 @|StartRequestIcon #fIcon
Is0 f17 actionDecl 'ch.ivy.add.portalkit.Data out;
' #txt
Is0 f17 actionTable 'out=in;
' #txt
Is0 f17 actionCode 'import ch.ivyteam.ivy.security.IPermission;
import com.ulcjava.base.client.IPendingRequestsOwner;
import ch.ivy.addon.portalkit.test.util.SecurityUtils;

ivy.task.getApplication().getSecurityDescriptor().grantPermission(IPermission.CASE_READ_ALL, ivy.session.getSessionUser());' #txt
Is0 f17 security system #txt
Is0 f17 type ch.ivy.add.portalkit.Data #txt
Is0 f17 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name></name>
    </language>
</elementInfo>
' #txt
Is0 f17 157 515 36 24 20 -2 #rect
Is0 f17 @|StepIcon #fIcon
Is0 f19 expr out #txt
Is0 f19 92 527 157 527 #arcP
Is0 f20 actionDecl 'ch.ivy.add.portalkit.Data out;
' #txt
Is0 f20 actionTable 'out=in;
' #txt
Is0 f20 actionCode 'import ch.ivyteam.ivy.security.IPermission;
import com.ulcjava.base.client.IPendingRequestsOwner;
import ch.ivy.addon.portalkit.test.util.SecurityUtils;

ivy.task.getApplication().getSecurityDescriptor().grantPermission(IPermission.TASK_READ_OWN_CASE_TASKS, ivy.session.getSessionUser());' #txt
Is0 f20 security system #txt
Is0 f20 type ch.ivy.add.portalkit.Data #txt
Is0 f20 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name></name>
    </language>
</elementInfo>
' #txt
Is0 f20 157 582 36 24 20 -2 #rect
Is0 f20 @|StepIcon #fIcon
Is0 f21 outLink grantTaskReadOwnCaseTaskPermissionsToCurrentUser.ivp #txt
Is0 f21 type ch.ivy.add.portalkit.Data #txt
Is0 f21 inParamDecl '<> param;' #txt
Is0 f21 actionDecl 'ch.ivy.add.portalkit.Data out;
' #txt
Is0 f21 guid 16144EB9A63782B1 #txt
Is0 f21 requestEnabled true #txt
Is0 f21 triggerEnabled false #txt
Is0 f21 callSignature grantTaskReadOwnCaseTaskPermissionsToCurrentUser() #txt
Is0 f21 persist false #txt
Is0 f21 startName 'Grant TASK_READ_OWN_CASE_TASKS permissions to current user in Designer' #txt
Is0 f21 taskData 'TaskTriggered.ROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.EXPRI=2
TaskTriggered.TYPE=0
TaskTriggered.PRI=2
TaskTriggered.EXROL=Everybody' #txt
Is0 f21 caseData businessCase.attach=false #txt
Is0 f21 showInStartList 1 #txt
Is0 f21 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>grantTaskReadOwnCaseTaskPermissionsToCurrentUser.ivp</name>
        <nameStyle>52,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Is0 f21 @C|.responsibility Everybody #txt
Is0 f21 66 581 26 26 -70 -33 #rect
Is0 f21 @|StartRequestIcon #fIcon
Is0 f24 expr out #txt
Is0 f24 92 594 157 594 #arcP
Is0 f7 type ch.ivy.add.portalkit.Data #txt
Is0 f7 385 513 30 30 16 0 #rect
Is0 f7 @|EndIcon #fIcon
Is0 f9 expr out #txt
Is0 f9 185 464 400 513 #arcP
Is0 f9 1 400 464 #addKink
Is0 f9 0 0.6438292660099184 0 0 #arcLabel
Is0 f16 expr out #txt
Is0 f16 193 527 385 527 #arcP
Is0 f18 expr out #txt
Is0 f18 193 594 400 543 #arcP
Is0 f18 1 400 594 #addKink
Is0 f18 0 0.6493809127148381 0 0 #arcLabel
Is0 f22 actionDecl 'ch.ivy.add.portalkit.Data out;
' #txt
Is0 f22 actionTable 'out=in;
' #txt
Is0 f22 actionCode 'import ch.ivyteam.ivy.security.IPermission;
import com.ulcjava.base.client.IPendingRequestsOwner;
import ch.ivy.addon.portalkit.test.util.SecurityUtils;

ivy.task.getApplication().getSecurityDescriptor().denyPermission(IPermission.TASK_READ_OWN_CASE_TASKS, ivy.session.getSessionUser());
ivy.task.getApplication().getSecurityDescriptor().denyPermission(IPermission.TASK_READ_ALL, ivy.session.getSessionUser());
ivy.task.getApplication().getSecurityDescriptor().denyPermission(IPermission.CASE_READ_ALL, ivy.session.getSessionUser());' #txt
Is0 f22 security system #txt
Is0 f22 type ch.ivy.add.portalkit.Data #txt
Is0 f22 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name></name>
    </language>
</elementInfo>
' #txt
Is0 f22 158 641 36 24 20 -2 #rect
Is0 f22 @|StepIcon #fIcon
Is0 f23 outLink denyReadAllPermissionFromCurrentUser.ivp #txt
Is0 f23 type ch.ivy.add.portalkit.Data #txt
Is0 f23 inParamDecl '<> param;' #txt
Is0 f23 actionDecl 'ch.ivy.add.portalkit.Data out;
' #txt
Is0 f23 guid 161450E30523CD5B #txt
Is0 f23 requestEnabled true #txt
Is0 f23 triggerEnabled false #txt
Is0 f23 callSignature denyReadAllPermissionFromCurrentUser() #txt
Is0 f23 persist false #txt
Is0 f23 startName 'deny ReadAll permissions to current user in Designer' #txt
Is0 f23 taskData 'TaskTriggered.ROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.EXPRI=2
TaskTriggered.TYPE=0
TaskTriggered.PRI=2
TaskTriggered.EXROL=Everybody' #txt
Is0 f23 caseData businessCase.attach=false #txt
Is0 f23 showInStartList 1 #txt
Is0 f23 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>denyReadAllPermissionFromCurrentUser.ivp</name>
        <nameStyle>40,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Is0 f23 @C|.responsibility Everybody #txt
Is0 f23 67 640 26 26 -70 -33 #rect
Is0 f23 @|StartRequestIcon #fIcon
Is0 f25 expr out #txt
Is0 f25 93 653 158 653 #arcP
Is0 f26 expr out #txt
Is0 f26 194 653 400 543 #arcP
Is0 f26 1 400 653 #addKink
Is0 f26 0 0.7956172575541481 0 0 #arcLabel
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
Is0 f5 mainOut f8 tail #connect
Is0 f8 head f6 mainIn #connect
Is0 f13 mainOut f19 tail #connect
Is0 f19 head f17 mainIn #connect
Is0 f21 mainOut f24 tail #connect
Is0 f24 head f20 mainIn #connect
Is0 f6 mainOut f9 tail #connect
Is0 f9 head f7 mainIn #connect
Is0 f17 mainOut f16 tail #connect
Is0 f16 head f7 mainIn #connect
Is0 f20 mainOut f18 tail #connect
Is0 f18 head f7 mainIn #connect
Is0 f23 mainOut f25 tail #connect
Is0 f25 head f22 mainIn #connect
Is0 f22 mainOut f26 tail #connect
Is0 f26 head f7 mainIn #connect
