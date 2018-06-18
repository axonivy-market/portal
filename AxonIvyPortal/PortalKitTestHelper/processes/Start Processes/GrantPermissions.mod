[Ivy]
14DE09882B540AD5 3.23 #module
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
Is0 @StartRequest f27 '' #zField
Is0 @StartRequest f28 '' #zField
Is0 @GridStep f31 '' #zField
Is0 @PushWFArc f32 '' #zField
Is0 @PushWFArc f29 '' #zField
Is0 @GridStep f33 '' #zField
Is0 @PushWFArc f34 '' #zField
Is0 @PushWFArc f30 '' #zField
Is0 @StartRequest f35 '' #zField
Is0 @GridStep f37 '' #zField
Is0 @PushWFArc f38 '' #zField
Is0 @PushWFArc f36 '' #zField
Is0 @StartRequest f39 '' #zField
Is0 @GridStep f41 '' #zField
Is0 @PushWFArc f42 '' #zField
Is0 @PushWFArc f40 '' #zField
Is0 @StartRequest f43 '' #zField
Is0 @GridStep f45 '' #zField
Is0 @PushWFArc f46 '' #zField
Is0 @PushWFArc f44 '' #zField
Is0 @StartRequest f47 '' #zField
Is0 @GridStep f49 '' #zField
Is0 @PushWFArc f50 '' #zField
Is0 @PushWFArc f48 '' #zField
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
Is0 f27 outLink grantDocumentOfInvolvedCaseWritePemission.ivp #txt
Is0 f27 type ch.ivy.add.portalkit.Data #txt
Is0 f27 inParamDecl '<> param;' #txt
Is0 f27 actionDecl 'ch.ivy.add.portalkit.Data out;
' #txt
Is0 f27 guid 1631F95315497CB5 #txt
Is0 f27 requestEnabled true #txt
Is0 f27 triggerEnabled false #txt
Is0 f27 callSignature grantDocumentOfInvolvedCaseWritePemission() #txt
Is0 f27 persist false #txt
Is0 f27 taskData 'TaskTriggered.ROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.EXPRI=2
TaskTriggered.TYPE=0
TaskTriggered.PRI=2
TaskTriggered.EXROL=Everybody' #txt
Is0 f27 caseData businessCase.attach=true #txt
Is0 f27 showInStartList 1 #txt
Is0 f27 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>grantDocumentOfInvolvedCaseWritePemission.ivp</name>
    </language>
</elementInfo>
' #txt
Is0 f27 @C|.responsibility Everybody #txt
Is0 f27 65 689 30 30 -62 20 #rect
Is0 f27 @|StartRequestIcon #fIcon
Is0 f28 outLink denyDocumentOfInvolvedCaseWritePemission.ivp #txt
Is0 f28 type ch.ivy.add.portalkit.Data #txt
Is0 f28 inParamDecl '<> param;' #txt
Is0 f28 actionDecl 'ch.ivy.add.portalkit.Data out;
' #txt
Is0 f28 guid 1631F953CCB82896 #txt
Is0 f28 requestEnabled true #txt
Is0 f28 triggerEnabled false #txt
Is0 f28 callSignature denyDocumentOfInvolvedCaseWritePemission() #txt
Is0 f28 persist false #txt
Is0 f28 taskData 'TaskTriggered.ROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.EXPRI=2
TaskTriggered.TYPE=0
TaskTriggered.PRI=2
TaskTriggered.EXROL=Everybody' #txt
Is0 f28 caseData businessCase.attach=true #txt
Is0 f28 showInStartList 1 #txt
Is0 f28 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>denyDocumentOfInvolvedCaseWritePemission.ivp</name>
    </language>
</elementInfo>
' #txt
Is0 f28 @C|.responsibility Everybody #txt
Is0 f28 65 753 30 30 -60 15 #rect
Is0 f28 @|StartRequestIcon #fIcon
Is0 f31 actionDecl 'ch.ivy.add.portalkit.Data out;
' #txt
Is0 f31 actionTable 'out=in;
' #txt
Is0 f31 actionCode 'import ch.ivyteam.ivy.security.IPermission;

ivy.task.getApplication().getSecurityDescriptor().grantPermission(IPermission.DOCUMENT_WRITE, ivy.session.getSessionUser());
ivy.task.getApplication().getSecurityDescriptor().grantPermission(IPermission.DOCUMENT_OF_INVOLVED_CASE_WRITE, ivy.session.getSessionUser());
' #txt
Is0 f31 security system #txt
Is0 f31 type ch.ivy.add.portalkit.Data #txt
Is0 f31 156 692 40 24 0 -8 #rect
Is0 f31 @|StepIcon #fIcon
Is0 f32 expr out #txt
Is0 f32 95 704 156 704 #arcP
Is0 f32 0 0.7960339943342776 0 0 #arcLabel
Is0 f29 expr out #txt
Is0 f29 196 704 400 543 #arcP
Is0 f29 1 400 704 #addKink
Is0 f29 0 0.7960339943342776 0 0 #arcLabel
Is0 f33 actionDecl 'ch.ivy.add.portalkit.Data out;
' #txt
Is0 f33 actionTable 'out=in;
' #txt
Is0 f33 actionCode 'import ch.ivyteam.ivy.security.IPermission;

ivy.task.getApplication().getSecurityDescriptor().denyPermission(IPermission.DOCUMENT_WRITE, ivy.session.getSessionUser());
ivy.task.getApplication().getSecurityDescriptor().denyPermission(IPermission.DOCUMENT_OF_INVOLVED_CASE_WRITE, ivy.session.getSessionUser());
' #txt
Is0 f33 security system #txt
Is0 f33 type ch.ivy.add.portalkit.Data #txt
Is0 f33 156 756 40 24 0 -8 #rect
Is0 f33 @|StepIcon #fIcon
Is0 f34 expr out #txt
Is0 f34 95 768 156 768 #arcP
Is0 f34 0 0.8741317568482905 0 0 #arcLabel
Is0 f30 expr out #txt
Is0 f30 196 768 400 543 #arcP
Is0 f30 1 400 768 #addKink
Is0 f30 0 0.8741317568482905 0 0 #arcLabel
Is0 f35 outLink grantOnlyDelegateOwnTasksPermission.ivp #txt
Is0 f35 type ch.ivy.add.portalkit.Data #txt
Is0 f35 inParamDecl '<> param;' #txt
Is0 f35 actionDecl 'ch.ivy.add.portalkit.Data out;
' #txt
Is0 f35 guid 1633F02576A75588 #txt
Is0 f35 requestEnabled true #txt
Is0 f35 triggerEnabled false #txt
Is0 f35 callSignature grantOnlyDelegateOwnTasksPermission() #txt
Is0 f35 persist false #txt
Is0 f35 taskData 'TaskTriggered.ROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.EXPRI=2
TaskTriggered.TYPE=0
TaskTriggered.PRI=2
TaskTriggered.EXROL=Everybody' #txt
Is0 f35 caseData businessCase.attach=true #txt
Is0 f35 showInStartList 1 #txt
Is0 f35 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>grantOnlyDelegateOwnTasksPermission.ivp</name>
        <nameStyle>39,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Is0 f35 @C|.responsibility Everybody #txt
Is0 f35 49 833 30 30 -55 26 #rect
Is0 f35 @|StartRequestIcon #fIcon
Is0 f37 actionDecl 'ch.ivy.add.portalkit.Data out;
' #txt
Is0 f37 actionTable 'out=in;
' #txt
Is0 f37 actionCode 'import ch.ivy.addon.portalkit.enums.PortalPermission;
import ch.ivyteam.ivy.security.IPermission;
import ch.ivyteam.ivy.security.restricted.permission.IPermissionRepository;

IPermission delegateOwnTaskPermission = IPermissionRepository.get().findByName(PortalPermission.TASK_WRITE_ACTIVATOR_OWN_TASKS.getValue());
if(#delegateOwnTaskPermission is initialized) {
	ivy.wf.getApplication().getSecurityDescriptor().grantPermission(delegateOwnTaskPermission, ivy.session.getSessionUser());
}
ivy.wf.getApplication().getSecurityDescriptor().denyPermission(IPermission.TASK_WRITE_ACTIVATOR, ivy.session.getSessionUser());' #txt
Is0 f37 security system #txt
Is0 f37 type ch.ivy.add.portalkit.Data #txt
Is0 f37 156 836 40 24 0 -8 #rect
Is0 f37 @|StepIcon #fIcon
Is0 f38 expr out #txt
Is0 f38 79 848 156 848 #arcP
Is0 f38 0 0.978319783197832 0 0 #arcLabel
Is0 f36 expr out #txt
Is0 f36 196 848 400 543 #arcP
Is0 f36 1 400 848 #addKink
Is0 f36 0 0.978319783197832 0 0 #arcLabel
Is0 f39 outLink undoOnlyDelegateOwnTasksPermission.ivp #txt
Is0 f39 type ch.ivy.add.portalkit.Data #txt
Is0 f39 inParamDecl '<> param;' #txt
Is0 f39 actionDecl 'ch.ivy.add.portalkit.Data out;
' #txt
Is0 f39 guid 1633F040419BF552 #txt
Is0 f39 requestEnabled true #txt
Is0 f39 triggerEnabled false #txt
Is0 f39 callSignature undoOnlyDelegateOwnTasksPermission() #txt
Is0 f39 persist false #txt
Is0 f39 taskData 'TaskTriggered.ROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.EXPRI=2
TaskTriggered.TYPE=0
TaskTriggered.PRI=2
TaskTriggered.EXROL=Everybody' #txt
Is0 f39 caseData businessCase.attach=true #txt
Is0 f39 showInStartList 1 #txt
Is0 f39 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>undoOnlyDelegateOwnTasksPermission.ivp</name>
        <nameStyle>38,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Is0 f39 @C|.responsibility Everybody #txt
Is0 f39 40 928 32 32 -41 25 #rect
Is0 f39 @|StartRequestIcon #fIcon
Is0 f41 actionDecl 'ch.ivy.add.portalkit.Data out;
' #txt
Is0 f41 actionTable 'out=in;
' #txt
Is0 f41 actionCode 'import ch.ivy.addon.portalkit.enums.PortalPermission;
import ch.ivyteam.ivy.security.IPermission;
import ch.ivyteam.ivy.security.restricted.permission.IPermissionRepository;

IPermission delegateOwnTaskPermission = IPermissionRepository.get().findByName(PortalPermission.TASK_WRITE_ACTIVATOR_OWN_TASKS.getValue());
if(#delegateOwnTaskPermission is initialized) {
	ivy.wf.getApplication().getSecurityDescriptor().denyPermission(delegateOwnTaskPermission, ivy.session.getSessionUser());
}
ivy.wf.getApplication().getSecurityDescriptor().grantPermission(IPermission.TASK_WRITE_ACTIVATOR, ivy.session.getSessionUser());' #txt
Is0 f41 security system #txt
Is0 f41 type ch.ivy.add.portalkit.Data #txt
Is0 f41 156 932 40 24 0 -8 #rect
Is0 f41 @|StepIcon #fIcon
Is0 f42 expr out #txt
Is0 f42 72 944 156 944 #arcP
Is0 f42 0 0.08158561403068428 0 0 #arcLabel
Is0 f40 expr out #txt
Is0 f40 196 944 400 543 #arcP
Is0 f40 1 400 944 #addKink
Is0 f40 1 0.08158561403068428 0 0 #arcLabel
Is0 f43 outLink denyPortalPermission.ivp #txt
Is0 f43 type ch.ivy.add.portalkit.Data #txt
Is0 f43 inParamDecl '<> param;' #txt
Is0 f43 actionDecl 'ch.ivy.add.portalkit.Data out;
' #txt
Is0 f43 guid 163D4644125C3741 #txt
Is0 f43 requestEnabled true #txt
Is0 f43 triggerEnabled false #txt
Is0 f43 callSignature denyPortalPermission() #txt
Is0 f43 persist false #txt
Is0 f43 taskData 'TaskTriggered.ROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.EXPRI=2
TaskTriggered.TYPE=0
TaskTriggered.PRI=2
TaskTriggered.EXROL=Everybody' #txt
Is0 f43 caseData businessCase.attach=true #txt
Is0 f43 showInStartList 1 #txt
Is0 f43 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>denyPortalPermission.ivp</name>
    </language>
</elementInfo>
' #txt
Is0 f43 @C|.responsibility Everybody #txt
Is0 f43 33 1025 30 30 -33 21 #rect
Is0 f43 @|StartRequestIcon #fIcon
Is0 f45 actionDecl 'ch.ivy.add.portalkit.Data out;
' #txt
Is0 f45 actionTable 'out=in;
' #txt
Is0 f45 actionCode 'import java.util.Objects;
import ch.ivy.addon.portalkit.enums.PortalPermission;
import ch.ivyteam.ivy.security.restricted.permission.IPermissionRepository;
import ch.ivyteam.ivy.security.IPermission;

IPermission iPermission = IPermissionRepository.get().findByName(PortalPermission.STATISTIC_ADD_DASHBOARD_CHART.getValue());
    if (Objects.nonNull(iPermission)){
      ivy.wf.getApplication().getSecurityDescriptor().denyPermission(iPermission, ivy.session.getSessionUser());
    }
iPermission = IPermissionRepository.get().findByName(PortalPermission.EXPRESS_CREATE_WORKFLOW.getValue());
    if (Objects.nonNull(iPermission)){
      ivy.wf.getApplication().getSecurityDescriptor().denyPermission(iPermission, ivy.session.getSessionUser());
    }
iPermission = IPermissionRepository.get().findByName(PortalPermission.STATISTIC_ANALYZE_TASK.getValue());
    if (Objects.nonNull(iPermission)){
      ivy.wf.getApplication().getSecurityDescriptor().denyPermission(iPermission, ivy.session.getSessionUser());
    }' #txt
Is0 f45 security system #txt
Is0 f45 type ch.ivy.add.portalkit.Data #txt
Is0 f45 180 1028 40 24 0 -8 #rect
Is0 f45 @|StepIcon #fIcon
Is0 f46 expr out #txt
Is0 f46 63 1040 180 1040 #arcP
Is0 f46 0 0.16096579476861167 0 0 #arcLabel
Is0 f44 expr out #txt
Is0 f44 220 1040 400 543 #arcP
Is0 f44 1 400 1040 #addKink
Is0 f44 1 0.16096579476861167 0 0 #arcLabel
Is0 f47 outLink grantPortalPermission.ivp #txt
Is0 f47 type ch.ivy.add.portalkit.Data #txt
Is0 f47 inParamDecl '<> param;' #txt
Is0 f47 actionDecl 'ch.ivy.add.portalkit.Data out;
' #txt
Is0 f47 guid 163D469107B79DF2 #txt
Is0 f47 requestEnabled true #txt
Is0 f47 triggerEnabled false #txt
Is0 f47 callSignature grantPortalPermission() #txt
Is0 f47 persist false #txt
Is0 f47 taskData 'TaskTriggered.ROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.EXPRI=2
TaskTriggered.TYPE=0
TaskTriggered.PRI=2
TaskTriggered.EXROL=Everybody' #txt
Is0 f47 caseData businessCase.attach=true #txt
Is0 f47 showInStartList 1 #txt
Is0 f47 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>grantPortalPermission.ivp</name>
    </language>
</elementInfo>
' #txt
Is0 f47 @C|.responsibility Everybody #txt
Is0 f47 33 1121 30 30 -24 30 #rect
Is0 f47 @|StartRequestIcon #fIcon
Is0 f49 actionDecl 'ch.ivy.add.portalkit.Data out;
' #txt
Is0 f49 actionTable 'out=in;
' #txt
Is0 f49 actionCode 'import java.util.Objects;
import ch.ivy.addon.portalkit.enums.PortalPermission;
import ch.ivyteam.ivy.security.restricted.permission.IPermissionRepository;
import ch.ivyteam.ivy.security.IPermission;

IPermission iPermission = IPermissionRepository.get().findByName(PortalPermission.STATISTIC_ADD_DASHBOARD_CHART.getValue());
    if (Objects.nonNull(iPermission)){
      ivy.wf.getApplication().getSecurityDescriptor().grantPermission(iPermission, ivy.session.getSessionUser());
    }
    
iPermission = IPermissionRepository.get().findByName(PortalPermission.EXPRESS_CREATE_WORKFLOW.getValue());
    if (Objects.nonNull(iPermission)){
      ivy.wf.getApplication().getSecurityDescriptor().grantPermission(iPermission, ivy.session.getSessionUser());
    }
iPermission = IPermissionRepository.get().findByName(PortalPermission.STATISTIC_ANALYZE_TASK.getValue());
    if (Objects.nonNull(iPermission)){
      ivy.wf.getApplication().getSecurityDescriptor().grantPermission(iPermission, ivy.session.getSessionUser());
    }' #txt
Is0 f49 security system #txt
Is0 f49 type ch.ivy.add.portalkit.Data #txt
Is0 f49 180 1124 40 24 0 -8 #rect
Is0 f49 @|StepIcon #fIcon
Is0 f50 expr out #txt
Is0 f50 63 1136 180 1136 #arcP
Is0 f50 0 0.21585160202360876 0 0 #arcLabel
Is0 f48 expr out #txt
Is0 f48 220 1136 400 543 #arcP
Is0 f48 1 400 1136 #addKink
Is0 f48 1 0.21585160202360876 0 0 #arcLabel
>Proto Is0 .type ch.ivy.add.portalkit.Data #txt
>Proto Is0 .processKind NORMAL #txt
>Proto Is0 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <swimlaneLabel>Request start</swimlaneLabel>
    </language>
    <swimlaneOrientation>true</swimlaneOrientation>
    <swimlaneSize>1160</swimlaneSize>
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
Is0 f27 mainOut f32 tail #connect
Is0 f32 head f31 mainIn #connect
Is0 f31 mainOut f29 tail #connect
Is0 f29 head f7 mainIn #connect
Is0 f28 mainOut f34 tail #connect
Is0 f34 head f33 mainIn #connect
Is0 f33 mainOut f30 tail #connect
Is0 f30 head f7 mainIn #connect
Is0 f35 mainOut f38 tail #connect
Is0 f38 head f37 mainIn #connect
Is0 f37 mainOut f36 tail #connect
Is0 f36 head f7 mainIn #connect
Is0 f39 mainOut f42 tail #connect
Is0 f42 head f41 mainIn #connect
Is0 f41 mainOut f40 tail #connect
Is0 f40 head f7 mainIn #connect
Is0 f43 mainOut f46 tail #connect
Is0 f46 head f45 mainIn #connect
Is0 f45 mainOut f44 tail #connect
Is0 f44 head f7 mainIn #connect
Is0 f47 mainOut f50 tail #connect
Is0 f50 head f49 mainIn #connect
Is0 f49 mainOut f48 tail #connect
Is0 f48 head f7 mainIn #connect
