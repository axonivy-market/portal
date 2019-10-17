[Ivy]
14DE09882B540AD5 7.5.0 #module
>Proto >Proto Collection #zClass
Is0 GrantPermissions Big #zClass
Is0 B #cInfo
Is0 #process
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
Is0 @StartRequest f47 '' #zField
Is0 @GridStep f49 '' #zField
Is0 @PushWFArc f48 '' #zField
Is0 @PushWFArc f50 '' #zField
Is0 @GridStep f53 '' #zField
Is0 @StartRequest f60 '' #zField
Is0 @GridStep f63 '' #zField
Is0 @PushWFArc f64 '' #zField
Is0 @PushWFArc f66 '' #zField
Is0 @GridStep f62 '' #zField
Is0 @PushWFArc f67 '' #zField
Is0 @GridStep f45 '' #zField
Is0 @PushWFArc f44 '' #zField
Is0 @PushWFArc f46 '' #zField
Is0 @StartRequest f61 '' #zField
Is0 @StartRequest f51 '' #zField
Is0 @StartRequest f52 '' #zField
Is0 @GridStep f57 '' #zField
Is0 @PushWFArc f58 '' #zField
Is0 @PushWFArc f59 '' #zField
Is0 @PushWFArc f56 '' #zField
Is0 @PushWFArc f55 '' #zField
Is0 @GridStep f54 '' #zField
Is0 @PushWFArc f65 '' #zField
Is0 @StartRequest f68 '' #zField
Is0 @GridStep f69 '' #zField
Is0 @StartRequest f70 '' #zField
Is0 @GridStep f71 '' #zField
Is0 @PushWFArc f74 '' #zField
Is0 @PushWFArc f75 '' #zField
Is0 @UserDialog f76 '' #zField
Is0 @PushWFArc f77 '' #zField
Is0 @PushWFArc f72 '' #zField
Is0 @UserDialog f78 '' #zField
Is0 @PushWFArc f79 '' #zField
Is0 @PushWFArc f73 '' #zField
>Proto Is0 Is0 GrantPermissions #zField
Is0 f0 outLink grantAllPermissionsToCurrentUser.ivp #txt
Is0 f0 inParamDecl '<> param;' #txt
Is0 f0 requestEnabled true #txt
Is0 f0 triggerEnabled false #txt
Is0 f0 callSignature grantAllPermissionsToCurrentUser() #txt
Is0 f0 persist false #txt
Is0 f0 startName 'Grant all permissions to current user in Designer' #txt
Is0 f0 taskData 'TaskTriggered.EXPRI=2
TaskTriggered.EXROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.PRI=2
TaskTriggered.ROL=Everybody
TaskTriggered.TYPE=0' #txt
Is0 f0 caseData businessCase.attach=true #txt
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
Is0 f1 67 339 26 26 14 0 #rect
Is0 f1 @|EndIcon #fIcon
Is0 f3 actionTable 'out=in;
' #txt
Is0 f3 actionCode 'import ch.ivyteam.ivy.security.IPermission;
import ch.ivy.addon.portalkit.test.util.SecurityUtils;

ivy.task.getApplication().getSecurityDescriptor().grantPermission(IPermission.TASK_WRITE_EXPIRY_TIMESTAMP, ivy.session.getSessionUser());' #txt
Is0 f3 security system #txt
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
Is0 f10 inParamDecl '<> param;' #txt
Is0 f10 requestEnabled true #txt
Is0 f10 triggerEnabled false #txt
Is0 f10 callSignature updatePermissionsOfTestUsers() #txt
Is0 f10 persist false #txt
Is0 f10 startDescription updatePermissionsOfDefaultUsersAsPreconditionToRunTests #txt
Is0 f10 taskData 'TaskTriggered.EXPRI=2
TaskTriggered.EXROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.PRI=2
TaskTriggered.ROL=Everybody
TaskTriggered.TYPE=0' #txt
Is0 f10 caseData businessCase.attach=true #txt
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
Is0 f14 actionTable 'out=in;
' #txt
Is0 f14 actionCode 'import ch.ivy.addon.portalkit.test.util.SecurityUtils;

SecurityUtils.updatePermissionsOfTestUsers();' #txt
Is0 f14 security system #txt
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
Is0 f11 395 339 26 26 14 0 #rect
Is0 f11 @|EndIcon #fIcon
Is0 f12 expr out #txt
Is0 f12 408 205 408 339 #arcP
Is0 f5 outLink grantTaskReadAllPermissionsToCurrentUser.ivp #txt
Is0 f5 inParamDecl '<> param;' #txt
Is0 f5 requestEnabled true #txt
Is0 f5 triggerEnabled false #txt
Is0 f5 callSignature grantTaskReadAllPermissionsToCurrentUser() #txt
Is0 f5 persist false #txt
Is0 f5 startName 'Grant TASK_READ_ALL to current user in Designer' #txt
Is0 f5 taskData 'TaskTriggered.EXPRI=2
TaskTriggered.EXROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.PRI=2
TaskTriggered.ROL=Everybody
TaskTriggered.TYPE=0' #txt
Is0 f5 caseData businessCase.attach=true #txt
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
Is0 f6 actionTable 'out=in;
' #txt
Is0 f6 actionCode 'import ch.ivyteam.ivy.security.IPermission;
import ch.ivy.addon.portalkit.test.util.SecurityUtils;

ivy.task.getApplication().getSecurityDescriptor().grantPermission(IPermission.TASK_READ_ALL, ivy.session.getSessionUser());' #txt
Is0 f6 security system #txt
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
Is0 f13 inParamDecl '<> param;' #txt
Is0 f13 requestEnabled true #txt
Is0 f13 triggerEnabled false #txt
Is0 f13 callSignature grantCaseReadAllPermissionsToCurrentUser() #txt
Is0 f13 persist false #txt
Is0 f13 startName 'Grant CASE_READ_ALL permissions to current user in Designer' #txt
Is0 f13 taskData 'TaskTriggered.EXPRI=2
TaskTriggered.EXROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.PRI=2
TaskTriggered.ROL=Everybody
TaskTriggered.TYPE=0' #txt
Is0 f13 caseData businessCase.attach=true #txt
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
Is0 f17 actionTable 'out=in;
' #txt
Is0 f17 actionCode 'import ch.ivyteam.ivy.security.IPermission;
import ch.ivy.addon.portalkit.test.util.SecurityUtils;

ivy.task.getApplication().getSecurityDescriptor().grantPermission(IPermission.CASE_READ_ALL, ivy.session.getSessionUser());' #txt
Is0 f17 security system #txt
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
Is0 f20 actionTable 'out=in;
' #txt
Is0 f20 actionCode 'import ch.ivyteam.ivy.security.IPermission;
import ch.ivy.addon.portalkit.test.util.SecurityUtils;

ivy.task.getApplication().getSecurityDescriptor().grantPermission(IPermission.TASK_READ_OWN_CASE_TASKS, ivy.session.getSessionUser());' #txt
Is0 f20 security system #txt
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
Is0 f21 inParamDecl '<> param;' #txt
Is0 f21 requestEnabled true #txt
Is0 f21 triggerEnabled false #txt
Is0 f21 callSignature grantTaskReadOwnCaseTaskPermissionsToCurrentUser() #txt
Is0 f21 persist false #txt
Is0 f21 startName 'Grant TASK_READ_OWN_CASE_TASKS permissions to current user in Designer' #txt
Is0 f21 taskData 'TaskTriggered.EXPRI=2
TaskTriggered.EXROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.PRI=2
TaskTriggered.ROL=Everybody
TaskTriggered.TYPE=0' #txt
Is0 f21 caseData businessCase.attach=true #txt
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
Is0 f7 393 513 30 30 16 0 #rect
Is0 f7 @|EndIcon #fIcon
Is0 f9 expr out #txt
Is0 f9 185 464 408 513 #arcP
Is0 f9 1 408 464 #addKink
Is0 f9 0 0.6438292660099184 0 0 #arcLabel
Is0 f16 expr out #txt
Is0 f16 193 527 393 527 #arcP
Is0 f18 expr out #txt
Is0 f18 193 594 408 543 #arcP
Is0 f18 1 408 594 #addKink
Is0 f18 0 0.6493809127148381 0 0 #arcLabel
Is0 f22 actionTable 'out=in;
' #txt
Is0 f22 actionCode 'import ch.ivyteam.ivy.security.IPermission;
import ch.ivy.addon.portalkit.test.util.SecurityUtils;

ivy.task.getApplication().getSecurityDescriptor().denyPermission(IPermission.TASK_READ_OWN_CASE_TASKS, ivy.session.getSessionUser());
ivy.task.getApplication().getSecurityDescriptor().denyPermission(IPermission.TASK_READ_ALL, ivy.session.getSessionUser());
ivy.task.getApplication().getSecurityDescriptor().denyPermission(IPermission.CASE_READ_ALL, ivy.session.getSessionUser());' #txt
Is0 f22 security system #txt
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
Is0 f23 inParamDecl '<> param;' #txt
Is0 f23 requestEnabled true #txt
Is0 f23 triggerEnabled false #txt
Is0 f23 callSignature denyReadAllPermissionFromCurrentUser() #txt
Is0 f23 persist false #txt
Is0 f23 startName 'deny ReadAll permissions to current user in Designer' #txt
Is0 f23 taskData 'TaskTriggered.EXPRI=2
TaskTriggered.EXROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.PRI=2
TaskTriggered.ROL=Everybody
TaskTriggered.TYPE=0' #txt
Is0 f23 caseData businessCase.attach=true #txt
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
Is0 f26 194 653 408 543 #arcP
Is0 f26 1 408 653 #addKink
Is0 f26 0 0.7956172575541481 0 0 #arcLabel
Is0 f27 outLink grantDocumentOfInvolvedCaseWritePemission.ivp #txt
Is0 f27 inParamDecl '<> param;' #txt
Is0 f27 requestEnabled true #txt
Is0 f27 triggerEnabled false #txt
Is0 f27 callSignature grantDocumentOfInvolvedCaseWritePemission() #txt
Is0 f27 persist false #txt
Is0 f27 taskData 'TaskTriggered.EXPRI=2
TaskTriggered.EXROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.PRI=2
TaskTriggered.ROL=Everybody
TaskTriggered.TYPE=0' #txt
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
Is0 f28 inParamDecl '<> param;' #txt
Is0 f28 requestEnabled true #txt
Is0 f28 triggerEnabled false #txt
Is0 f28 callSignature denyDocumentOfInvolvedCaseWritePemission() #txt
Is0 f28 persist false #txt
Is0 f28 taskData 'TaskTriggered.EXPRI=2
TaskTriggered.EXROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.PRI=2
TaskTriggered.ROL=Everybody
TaskTriggered.TYPE=0' #txt
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
Is0 f31 actionTable 'out=in;
' #txt
Is0 f31 actionCode 'import ch.ivyteam.ivy.security.IPermission;

ivy.task.getApplication().getSecurityDescriptor().grantPermission(IPermission.DOCUMENT_WRITE, ivy.session.getSessionUser());
ivy.task.getApplication().getSecurityDescriptor().grantPermission(IPermission.DOCUMENT_OF_INVOLVED_CASE_WRITE, ivy.session.getSessionUser());
' #txt
Is0 f31 security system #txt
Is0 f31 156 692 40 24 0 -8 #rect
Is0 f31 @|StepIcon #fIcon
Is0 f32 expr out #txt
Is0 f32 95 704 156 704 #arcP
Is0 f32 0 0.7960339943342776 0 0 #arcLabel
Is0 f29 expr out #txt
Is0 f29 196 704 408 543 #arcP
Is0 f29 1 408 704 #addKink
Is0 f29 0 0.7960339943342776 0 0 #arcLabel
Is0 f33 actionTable 'out=in;
' #txt
Is0 f33 actionCode 'import ch.ivyteam.ivy.security.IPermission;

ivy.task.getApplication().getSecurityDescriptor().denyPermission(IPermission.DOCUMENT_WRITE, ivy.session.getSessionUser());
ivy.task.getApplication().getSecurityDescriptor().denyPermission(IPermission.DOCUMENT_OF_INVOLVED_CASE_WRITE, ivy.session.getSessionUser());
' #txt
Is0 f33 security system #txt
Is0 f33 156 756 40 24 0 -8 #rect
Is0 f33 @|StepIcon #fIcon
Is0 f34 expr out #txt
Is0 f34 95 768 156 768 #arcP
Is0 f34 0 0.8741317568482905 0 0 #arcLabel
Is0 f30 expr out #txt
Is0 f30 196 768 408 543 #arcP
Is0 f30 1 408 768 #addKink
Is0 f30 0 0.8741317568482905 0 0 #arcLabel
Is0 f35 outLink grantOnlyDelegateOwnTasksPermission.ivp #txt
Is0 f35 inParamDecl '<> param;' #txt
Is0 f35 requestEnabled true #txt
Is0 f35 triggerEnabled false #txt
Is0 f35 callSignature grantOnlyDelegateOwnTasksPermission() #txt
Is0 f35 persist false #txt
Is0 f35 taskData 'TaskTriggered.EXPRI=2
TaskTriggered.EXROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.PRI=2
TaskTriggered.ROL=Everybody
TaskTriggered.TYPE=0' #txt
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
Is0 f37 156 836 40 24 0 -8 #rect
Is0 f37 @|StepIcon #fIcon
Is0 f38 expr out #txt
Is0 f38 79 848 156 848 #arcP
Is0 f38 0 0.978319783197832 0 0 #arcLabel
Is0 f36 expr out #txt
Is0 f36 196 848 408 543 #arcP
Is0 f36 1 408 848 #addKink
Is0 f36 0 0.978319783197832 0 0 #arcLabel
Is0 f39 outLink undoOnlyDelegateOwnTasksPermission.ivp #txt
Is0 f39 inParamDecl '<> param;' #txt
Is0 f39 requestEnabled true #txt
Is0 f39 triggerEnabled false #txt
Is0 f39 callSignature undoOnlyDelegateOwnTasksPermission() #txt
Is0 f39 persist false #txt
Is0 f39 taskData 'TaskTriggered.EXPRI=2
TaskTriggered.EXROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.PRI=2
TaskTriggered.ROL=Everybody
TaskTriggered.TYPE=0' #txt
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
Is0 f41 156 932 40 24 0 -8 #rect
Is0 f41 @|StepIcon #fIcon
Is0 f42 expr out #txt
Is0 f42 72 944 156 944 #arcP
Is0 f42 0 0.08158561403068428 0 0 #arcLabel
Is0 f40 expr out #txt
Is0 f40 196 944 408 543 #arcP
Is0 f40 1 408 944 #addKink
Is0 f40 1 0.08158561403068428 0 0 #arcLabel
Is0 f43 outLink denyPortalPermission.ivp #txt
Is0 f43 inParamDecl '<> param;' #txt
Is0 f43 requestEnabled true #txt
Is0 f43 triggerEnabled false #txt
Is0 f43 callSignature denyPortalPermission() #txt
Is0 f43 persist false #txt
Is0 f43 taskData 'TaskTriggered.EXPRI=2
TaskTriggered.EXROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.PRI=2
TaskTriggered.ROL=Everybody
TaskTriggered.TYPE=0' #txt
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
Is0 f47 outLink grantPortalPermission.ivp #txt
Is0 f47 inParamDecl '<> param;' #txt
Is0 f47 requestEnabled true #txt
Is0 f47 triggerEnabled false #txt
Is0 f47 callSignature grantPortalPermission() #txt
Is0 f47 persist false #txt
Is0 f47 taskData 'TaskTriggered.EXPRI=2
TaskTriggered.EXROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.PRI=2
TaskTriggered.ROL=Everybody
TaskTriggered.TYPE=0' #txt
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
    }

    iPermission = IPermissionRepository.get().findByName(PortalPermission.SHOW_CASE_DETAILS.getValue());
    if (Objects.nonNull(iPermission)){
      ivy.wf.getApplication().getSecurityDescriptor().grantPermission(iPermission, ivy.session.getSessionUser());
    }
    
    iPermission = IPermissionRepository.get().findByName(PortalPermission.SHOW_ALL_TASKS_OF_CASE.getValue());
    if (Objects.nonNull(iPermission)){
      ivy.wf.getApplication().getSecurityDescriptor().grantPermission(iPermission, ivy.session.getSessionUser());
    }
    
    iPermission = IPermissionRepository.get().findByName(PortalPermission.TASK_CASE_ADD_NOTE.getValue());
    if (Objects.nonNull(iPermission)){
      ivy.wf.getApplication().getSecurityDescriptor().grantPermission(iPermission, ivy.session.getSessionUser());
    }
    
    iPermission = IPermissionRepository.get().findByName(PortalPermission.TASK_CASE_SHOW_MORE_NOTE.getValue());
    if (Objects.nonNull(iPermission)){
      ivy.wf.getApplication().getSecurityDescriptor().grantPermission(iPermission, ivy.session.getSessionUser());
    }
    
    iPermission = IPermissionRepository.get().findByName(PortalPermission.TASK_DISPLAY_RESET_ACTION.getValue());
    if (Objects.nonNull(iPermission)){
      ivy.wf.getApplication().getSecurityDescriptor().grantPermission(iPermission, ivy.session.getSessionUser());
    }
    
    iPermission = IPermissionRepository.get().findByName(PortalPermission.TASK_DISPLAY_RESERVE_ACTION.getValue());
    if (Objects.nonNull(iPermission)){
      ivy.wf.getApplication().getSecurityDescriptor().grantPermission(iPermission, ivy.session.getSessionUser());
    }
    
    iPermission = IPermissionRepository.get().findByName(PortalPermission.TASK_DISPLAY_DELEGATE_ACTION.getValue());
    if (Objects.nonNull(iPermission)){
      ivy.wf.getApplication().getSecurityDescriptor().grantPermission(iPermission, ivy.session.getSessionUser());
    }
    
    iPermission = IPermissionRepository.get().findByName(PortalPermission.TASK_DISPLAY_ADDITIONAL_OPTIONS.getValue());
    if (Objects.nonNull(iPermission)){
      ivy.wf.getApplication().getSecurityDescriptor().grantPermission(iPermission, ivy.session.getSessionUser());
    }' #txt
Is0 f49 security system #txt
Is0 f49 180 1124 40 24 0 -8 #rect
Is0 f49 @|StepIcon #fIcon
Is0 f48 expr out #txt
Is0 f48 63 1136 180 1136 #arcP
Is0 f50 expr out #txt
Is0 f50 220 1136 408 543 #arcP
Is0 f50 1 408 1136 #addKink
Is0 f50 1 0.33135304817177313 0 0 #arcLabel
Is0 f53 actionTable 'out=in;
' #txt
Is0 f53 actionCode 'import java.util.Objects;
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
    }
    
    /*iPermission = IPermissionRepository.get().findByName(PortalPermission.ACCESS_FULL_PROCESS_LIST.getValue());
    if (Objects.nonNull(iPermission)){
      ivy.wf.getApplication().getSecurityDescriptor().denyPermission(iPermission, ivy.session.getSessionUser());
    }*/
    
    iPermission = IPermissionRepository.get().findByName(PortalPermission.ACCESS_FULL_TASK_LIST.getValue());
    if (Objects.nonNull(iPermission)){
      ivy.wf.getApplication().getSecurityDescriptor().denyPermission(iPermission, ivy.session.getSessionUser());
    }
        iPermission = IPermissionRepository.get().findByName(PortalPermission.ACCESS_FULL_CASE_LIST.getValue());
    if (Objects.nonNull(iPermission)){
      ivy.wf.getApplication().getSecurityDescriptor().denyPermission(iPermission, ivy.session.getSessionUser());
    }
        iPermission = IPermissionRepository.get().findByName(PortalPermission.ACCESS_FULL_STATISTICS_LIST.getValue());
    if (Objects.nonNull(iPermission)){
      ivy.wf.getApplication().getSecurityDescriptor().denyPermission(iPermission, ivy.session.getSessionUser());
    }
    
    iPermission = IPermissionRepository.get().findByName(PortalPermission.SHOW_CASE_DETAILS.getValue());
    if (Objects.nonNull(iPermission)){
      ivy.wf.getApplication().getSecurityDescriptor().denyPermission(iPermission, ivy.session.getSessionUser());
    }
    
    iPermission = IPermissionRepository.get().findByName(PortalPermission.SHOW_ALL_TASKS_OF_CASE.getValue());
    if (Objects.nonNull(iPermission)){
      ivy.wf.getApplication().getSecurityDescriptor().denyPermission(iPermission, ivy.session.getSessionUser());
    }
    
    iPermission = IPermissionRepository.get().findByName(PortalPermission.TASK_CASE_ADD_NOTE.getValue());
    if (Objects.nonNull(iPermission)){
      ivy.wf.getApplication().getSecurityDescriptor().denyPermission(iPermission, ivy.session.getSessionUser());
    }
    
    iPermission = IPermissionRepository.get().findByName(PortalPermission.TASK_CASE_SHOW_MORE_NOTE.getValue());
    if (Objects.nonNull(iPermission)){
      ivy.wf.getApplication().getSecurityDescriptor().denyPermission(iPermission, ivy.session.getSessionUser());
    }
    
    iPermission = IPermissionRepository.get().findByName(PortalPermission.TASK_DISPLAY_RESET_ACTION.getValue());
    if (Objects.nonNull(iPermission)){
      ivy.wf.getApplication().getSecurityDescriptor().denyPermission(iPermission, ivy.session.getSessionUser());
    }
    
    iPermission = IPermissionRepository.get().findByName(PortalPermission.TASK_DISPLAY_RESERVE_ACTION.getValue());
    if (Objects.nonNull(iPermission)){
      ivy.wf.getApplication().getSecurityDescriptor().denyPermission(iPermission, ivy.session.getSessionUser());
    }
    
    iPermission = IPermissionRepository.get().findByName(PortalPermission.TASK_DISPLAY_DELEGATE_ACTION.getValue());
    if (Objects.nonNull(iPermission)){
      ivy.wf.getApplication().getSecurityDescriptor().denyPermission(iPermission, ivy.session.getSessionUser());
    }
    
    iPermission = IPermissionRepository.get().findByName(PortalPermission.TASK_DISPLAY_ADDITIONAL_OPTIONS.getValue());
    if (Objects.nonNull(iPermission)){
      ivy.wf.getApplication().getSecurityDescriptor().denyPermission(iPermission, ivy.session.getSessionUser());
    }' #txt
Is0 f53 security system #txt
Is0 f53 452 324 40 24 0 -8 #rect
Is0 f53 @|StepIcon #fIcon
Is0 f60 outLink denySpecificPortalPermission.ivp #txt
Is0 f60 inParamDecl '<String portalPermission> param;' #txt
Is0 f60 inParamTable 'out.portalPermission=param.portalPermission;
' #txt
Is0 f60 requestEnabled true #txt
Is0 f60 triggerEnabled false #txt
Is0 f60 callSignature denySpecificPortalPermission(String) #txt
Is0 f60 persist false #txt
Is0 f60 caseData businessCase.attach=true #txt
Is0 f60 showInStartList 0 #txt
Is0 f60 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>denySpecificPortalPermission.ivp</name>
    </language>
</elementInfo>
' #txt
Is0 f60 @C|.responsibility Everybody #txt
Is0 f60 33 1217 30 30 -26 18 #rect
Is0 f60 @|StartRequestIcon #fIcon
Is0 f63 actionTable 'out=in;
' #txt
Is0 f63 actionCode 'import java.util.Objects;
import ch.ivy.addon.portalkit.enums.PortalPermission;
import ch.ivyteam.ivy.security.restricted.permission.IPermissionRepository;
import ch.ivyteam.ivy.security.IPermission;

IPermission iPermission = IPermissionRepository.get().findByName(in.portalPermission);
if (Objects.nonNull(iPermission)){
  ivy.wf.getApplication().getSecurityDescriptor().denyPermission(iPermission, ivy.session.getSessionUser());
}' #txt
Is0 f63 security system #txt
Is0 f63 180 1220 40 24 0 -8 #rect
Is0 f63 @|StepIcon #fIcon
Is0 f64 expr out #txt
Is0 f64 63 1232 180 1232 #arcP
Is0 f66 expr out #txt
Is0 f66 220 1232 408 543 #arcP
Is0 f66 1 408 1232 #addKink
Is0 f66 1 0.39399293286219084 0 0 #arcLabel
Is0 f62 actionTable 'out=in;
' #txt
Is0 f62 actionCode 'import java.util.Objects;
import ch.ivy.addon.portalkit.enums.PortalPermission;
import ch.ivyteam.ivy.security.restricted.permission.IPermissionRepository;
import ch.ivyteam.ivy.security.IPermission;

IPermission iPermission = IPermissionRepository.get().findByName(in.portalPermission);
if (Objects.nonNull(iPermission)){
  ivy.wf.getApplication().getSecurityDescriptor().grantPermission(iPermission, ivy.session.getSessionUser());
}' #txt
Is0 f62 security system #txt
Is0 f62 180 1284 40 24 0 -8 #rect
Is0 f62 @|StepIcon #fIcon
Is0 f67 expr out #txt
Is0 f67 220 1296 408 543 #arcP
Is0 f67 1 408 1296 #addKink
Is0 f67 1 0.4014238773274918 0 0 #arcLabel
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
    }

    iPermission = IPermissionRepository.get().findByName(PortalPermission.SHOW_CASE_DETAILS.getValue());
    if (Objects.nonNull(iPermission)){
      ivy.wf.getApplication().getSecurityDescriptor().denyPermission(iPermission, ivy.session.getSessionUser());
    }
    
    iPermission = IPermissionRepository.get().findByName(PortalPermission.SHOW_ALL_TASKS_OF_CASE.getValue());
    if (Objects.nonNull(iPermission)){
      ivy.wf.getApplication().getSecurityDescriptor().denyPermission(iPermission, ivy.session.getSessionUser());
    }
    
    iPermission = IPermissionRepository.get().findByName(PortalPermission.TASK_CASE_ADD_NOTE.getValue());
    if (Objects.nonNull(iPermission)){
      ivy.wf.getApplication().getSecurityDescriptor().denyPermission(iPermission, ivy.session.getSessionUser());
    }
    
    iPermission = IPermissionRepository.get().findByName(PortalPermission.TASK_CASE_SHOW_MORE_NOTE.getValue());
    if (Objects.nonNull(iPermission)){
      ivy.wf.getApplication().getSecurityDescriptor().denyPermission(iPermission, ivy.session.getSessionUser());
    }
    
    iPermission = IPermissionRepository.get().findByName(PortalPermission.TASK_DISPLAY_RESET_ACTION.getValue());
    if (Objects.nonNull(iPermission)){
      ivy.wf.getApplication().getSecurityDescriptor().denyPermission(iPermission, ivy.session.getSessionUser());
    }
    
    iPermission = IPermissionRepository.get().findByName(PortalPermission.TASK_DISPLAY_RESERVE_ACTION.getValue());
    if (Objects.nonNull(iPermission)){
      ivy.wf.getApplication().getSecurityDescriptor().denyPermission(iPermission, ivy.session.getSessionUser());
    }
    
    iPermission = IPermissionRepository.get().findByName(PortalPermission.TASK_DISPLAY_DELEGATE_ACTION.getValue());
    if (Objects.nonNull(iPermission)){
      ivy.wf.getApplication().getSecurityDescriptor().denyPermission(iPermission, ivy.session.getSessionUser());
    }
    
    iPermission = IPermissionRepository.get().findByName(PortalPermission.TASK_DISPLAY_ADDITIONAL_OPTIONS.getValue());
    if (Objects.nonNull(iPermission)){
      ivy.wf.getApplication().getSecurityDescriptor().denyPermission(iPermission, ivy.session.getSessionUser());
    }' #txt
Is0 f45 security system #txt
Is0 f45 180 1028 40 24 0 -8 #rect
Is0 f45 @|StepIcon #fIcon
Is0 f44 expr out #txt
Is0 f44 63 1040 180 1040 #arcP
Is0 f46 expr out #txt
Is0 f46 220 1040 408 543 #arcP
Is0 f46 1 408 1040 #addKink
Is0 f46 1 0.2992533122461412 0 0 #arcLabel
Is0 f61 outLink grantSpecificPortalPermission.ivp #txt
Is0 f61 inParamDecl '<String portalPermission> param;' #txt
Is0 f61 inParamTable 'out.portalPermission=param.portalPermission;
' #txt
Is0 f61 requestEnabled true #txt
Is0 f61 triggerEnabled false #txt
Is0 f61 callSignature grantSpecificPortalPermission(String) #txt
Is0 f61 persist false #txt
Is0 f61 caseData businessCase.attach=true #txt
Is0 f61 showInStartList 0 #txt
Is0 f61 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>grantSpecificPortalPermission.ivp</name>
    </language>
</elementInfo>
' #txt
Is0 f61 @C|.responsibility Everybody #txt
Is0 f61 33 1281 30 30 -23 20 #rect
Is0 f61 @|StartRequestIcon #fIcon
Is0 f51 outLink denyAccessFullListPermissions.ivp #txt
Is0 f51 inParamDecl '<> param;' #txt
Is0 f51 requestEnabled true #txt
Is0 f51 triggerEnabled false #txt
Is0 f51 callSignature denyAccessFullListPermissions() #txt
Is0 f51 persist false #txt
Is0 f51 caseData businessCase.attach=true #txt
Is0 f51 showInStartList 1 #txt
Is0 f51 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>denyAccessFullListPermissions.ivp</name>
    </language>
</elementInfo>
' #txt
Is0 f51 @C|.responsibility Everybody #txt
Is0 f51 33 1345 30 30 -23 21 #rect
Is0 f51 @|StartRequestIcon #fIcon
Is0 f52 outLink grantAccessFullListPermissions.ivp #txt
Is0 f52 inParamDecl '<> param;' #txt
Is0 f52 requestEnabled true #txt
Is0 f52 triggerEnabled false #txt
Is0 f52 callSignature grantAccessFullListPermissions() #txt
Is0 f52 persist false #txt
Is0 f52 caseData businessCase.attach=true #txt
Is0 f52 showInStartList 1 #txt
Is0 f52 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>grantAccessFullListPermissions.ivp</name>
    </language>
</elementInfo>
' #txt
Is0 f52 @C|.responsibility Everybody #txt
Is0 f52 33 1409 30 30 -24 18 #rect
Is0 f52 @|StartRequestIcon #fIcon
Is0 f57 actionTable 'out=in;
' #txt
Is0 f57 actionCode 'import java.util.Objects;
import ch.ivy.addon.portalkit.enums.PortalPermission;
import ch.ivyteam.ivy.security.restricted.permission.IPermissionRepository;
import ch.ivyteam.ivy.security.IPermission;

IPermission iPermission = IPermissionRepository.get().findByName(PortalPermission.ACCESS_FULL_PROCESS_LIST.getValue());
if (Objects.nonNull(iPermission)){
  ivy.wf.getApplication().getSecurityDescriptor().denyPermission(iPermission, ivy.session.getSessionUser());
}
    
iPermission = IPermissionRepository.get().findByName(PortalPermission.ACCESS_FULL_TASK_LIST.getValue());
if (Objects.nonNull(iPermission)){
  ivy.wf.getApplication().getSecurityDescriptor().denyPermission(iPermission, ivy.session.getSessionUser());
}

iPermission = IPermissionRepository.get().findByName(PortalPermission.ACCESS_FULL_CASE_LIST.getValue());
if (Objects.nonNull(iPermission)){
  ivy.wf.getApplication().getSecurityDescriptor().denyPermission(iPermission, ivy.session.getSessionUser());
}

iPermission = IPermissionRepository.get().findByName(PortalPermission.ACCESS_FULL_STATISTICS_LIST.getValue());
if (Objects.nonNull(iPermission)){
  ivy.wf.getApplication().getSecurityDescriptor().denyPermission(iPermission, ivy.session.getSessionUser());
}' #txt
Is0 f57 security system #txt
Is0 f57 180 1348 40 24 0 -8 #rect
Is0 f57 @|StepIcon #fIcon
Is0 f58 expr out #txt
Is0 f58 63 1360 180 1360 #arcP
Is0 f59 expr out #txt
Is0 f59 220 1360 408 543 #arcP
Is0 f59 1 408 1360 #addKink
Is0 f59 1 0.3849449204406365 0 0 #arcLabel
Is0 f56 expr out #txt
Is0 f56 220 1424 408 543 #arcP
Is0 f56 1 408 1424 #addKink
Is0 f56 1 0.38641858494407466 0 0 #arcLabel
Is0 f55 expr out #txt
Is0 f55 63 1424 180 1424 #arcP
Is0 f54 actionTable 'out=in;
' #txt
Is0 f54 actionCode 'import java.util.Objects;
import ch.ivy.addon.portalkit.enums.PortalPermission;
import ch.ivyteam.ivy.security.restricted.permission.IPermissionRepository;
import ch.ivyteam.ivy.security.IPermission;

IPermission iPermission = IPermissionRepository.get().findByName(PortalPermission.ACCESS_FULL_PROCESS_LIST.getValue());
if (Objects.nonNull(iPermission)){
  ivy.wf.getApplication().getSecurityDescriptor().grantPermission(iPermission, ivy.session.getSessionUser());
}
    
iPermission = IPermissionRepository.get().findByName(PortalPermission.ACCESS_FULL_TASK_LIST.getValue());
if (Objects.nonNull(iPermission)){
  ivy.wf.getApplication().getSecurityDescriptor().grantPermission(iPermission, ivy.session.getSessionUser());
}
    
iPermission = IPermissionRepository.get().findByName(PortalPermission.ACCESS_FULL_CASE_LIST.getValue());
if (Objects.nonNull(iPermission)){
  ivy.wf.getApplication().getSecurityDescriptor().grantPermission(iPermission, ivy.session.getSessionUser());
}
    
iPermission = IPermissionRepository.get().findByName(PortalPermission.ACCESS_FULL_STATISTICS_LIST.getValue());
if (Objects.nonNull(iPermission)){
  ivy.wf.getApplication().getSecurityDescriptor().grantPermission(iPermission, ivy.session.getSessionUser());
}' #txt
Is0 f54 security system #txt
Is0 f54 180 1412 40 24 0 -8 #rect
Is0 f54 @|StepIcon #fIcon
Is0 f65 expr out #txt
Is0 f65 63 1296 180 1296 #arcP
Is0 f68 outLink denySpecificPermission.ivp #txt
Is0 f68 inParamDecl '<String portalPermission> param;' #txt
Is0 f68 inParamTable 'out.portalPermission=param.portalPermission;
' #txt
Is0 f68 requestEnabled true #txt
Is0 f68 triggerEnabled false #txt
Is0 f68 callSignature denySpecificPermission(String) #txt
Is0 f68 persist false #txt
Is0 f68 caseData businessCase.attach=true #txt
Is0 f68 showInStartList 1 #txt
Is0 f68 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>denySpecificPermission.ivp</name>
    </language>
</elementInfo>
' #txt
Is0 f68 @C|.responsibility Everybody #txt
Is0 f68 33 1473 30 30 -26 18 #rect
Is0 f68 @|StartRequestIcon #fIcon
Is0 f69 actionTable 'out=in;
' #txt
Is0 f69 actionCode 'import java.util.Objects;
import ch.ivy.addon.portalkit.enums.PortalPermission;
import ch.ivyteam.ivy.security.restricted.permission.IPermissionRepository;
import ch.ivyteam.ivy.security.IPermission;

IPermission iPermission = IPermissionRepository.get().findByName(in.portalPermission);
if (Objects.nonNull(iPermission)){
  ivy.wf.getApplication().getSecurityDescriptor().denyPermission(iPermission, ivy.session.getSessionUser());
}' #txt
Is0 f69 security system #txt
Is0 f69 284 1476 40 24 0 -8 #rect
Is0 f69 @|StepIcon #fIcon
Is0 f70 outLink grantSpecificPermission.ivp #txt
Is0 f70 inParamDecl '<String portalPermission> param;' #txt
Is0 f70 inParamTable 'out.portalPermission=param.portalPermission;
' #txt
Is0 f70 requestEnabled true #txt
Is0 f70 triggerEnabled false #txt
Is0 f70 callSignature grantSpecificPermission(String) #txt
Is0 f70 persist false #txt
Is0 f70 caseData businessCase.attach=true #txt
Is0 f70 showInStartList 1 #txt
Is0 f70 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>grantSpecificPermission.ivp</name>
    </language>
</elementInfo>
' #txt
Is0 f70 @C|.responsibility Everybody #txt
Is0 f70 33 1537 30 30 -23 20 #rect
Is0 f70 @|StartRequestIcon #fIcon
Is0 f71 actionTable 'out=in;
' #txt
Is0 f71 actionCode 'import java.util.Objects;
import ch.ivy.addon.portalkit.enums.PortalPermission;
import ch.ivyteam.ivy.security.restricted.permission.IPermissionRepository;
import ch.ivyteam.ivy.security.IPermission;

IPermission iPermission = IPermissionRepository.get().findByName(in.portalPermission);
if (Objects.nonNull(iPermission)){
  ivy.wf.getApplication().getSecurityDescriptor().grantPermission(iPermission, ivy.session.getSessionUser());
}' #txt
Is0 f71 security system #txt
Is0 f71 284 1540 40 24 0 -8 #rect
Is0 f71 @|StepIcon #fIcon
Is0 f74 expr out #txt
Is0 f74 324 1488 408 543 #arcP
Is0 f74 1 408 1488 #addKink
Is0 f74 1 0.40476190476190477 0 0 #arcLabel
Is0 f75 expr out #txt
Is0 f75 324 1552 408 543 #arcP
Is0 f75 1 408 1552 #addKink
Is0 f75 1 0.410802775024777 0 0 #arcLabel
Is0 f76 dialogId ch.ivy.addon.portalkit.test.GrantOrDenySpecificPermission #txt
Is0 f76 startMethod start(Boolean) #txt
Is0 f76 requestActionDecl '<Boolean isGrantPermission> param;' #txt
Is0 f76 requestMappingAction 'param.isGrantPermission=false;
' #txt
Is0 f76 responseActionDecl 'portalKit_test.GrantPermissionsData out;
' #txt
Is0 f76 responseMappingAction 'out=in;
out.portalPermission=result.permission;
' #txt
Is0 f76 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Deny permission HTML</name>
    </language>
</elementInfo>
' #txt
Is0 f76 104 1466 144 44 -64 -8 #rect
Is0 f76 @|UserDialogIcon #fIcon
Is0 f77 expr out #txt
Is0 f77 63 1488 104 1488 #arcP
Is0 f72 expr out #txt
Is0 f72 248 1488 284 1488 #arcP
Is0 f78 dialogId ch.ivy.addon.portalkit.test.GrantOrDenySpecificPermission #txt
Is0 f78 startMethod start(Boolean) #txt
Is0 f78 requestActionDecl '<Boolean isGrantPermission> param;' #txt
Is0 f78 requestMappingAction 'param.isGrantPermission=true;
' #txt
Is0 f78 responseActionDecl 'portalKit_test.GrantPermissionsData out;
' #txt
Is0 f78 responseMappingAction 'out=in;
out.portalPermission=result.permission;
' #txt
Is0 f78 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Grant permission HTML</name>
    </language>
</elementInfo>
' #txt
Is0 f78 104 1530 144 44 -65 -8 #rect
Is0 f78 @|UserDialogIcon #fIcon
Is0 f79 expr out #txt
Is0 f79 63 1552 104 1552 #arcP
Is0 f73 expr out #txt
Is0 f73 248 1552 284 1552 #arcP
>Proto Is0 .type portalKit_test.GrantPermissionsData #txt
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
Is0 f48 head f49 mainIn #connect
Is0 f50 head f7 mainIn #connect
Is0 f47 mainOut f48 tail #connect
Is0 f49 mainOut f50 tail #connect
Is0 f60 mainOut f64 tail #connect
Is0 f64 head f63 mainIn #connect
Is0 f63 mainOut f66 tail #connect
Is0 f66 head f7 mainIn #connect
Is0 f61 mainOut f65 tail #connect
Is0 f65 head f62 mainIn #connect
Is0 f62 mainOut f67 tail #connect
Is0 f67 head f7 mainIn #connect
Is0 f44 head f45 mainIn #connect
Is0 f46 head f7 mainIn #connect
Is0 f43 mainOut f44 tail #connect
Is0 f45 mainOut f46 tail #connect
Is0 f52 mainOut f55 tail #connect
Is0 f55 head f54 mainIn #connect
Is0 f54 mainOut f56 tail #connect
Is0 f56 head f7 mainIn #connect
Is0 f51 mainOut f58 tail #connect
Is0 f58 head f57 mainIn #connect
Is0 f57 mainOut f59 tail #connect
Is0 f59 head f7 mainIn #connect
Is0 f69 mainOut f74 tail #connect
Is0 f74 head f7 mainIn #connect
Is0 f71 mainOut f75 tail #connect
Is0 f75 head f7 mainIn #connect
Is0 f68 mainOut f77 tail #connect
Is0 f77 head f76 mainIn #connect
Is0 f76 mainOut f72 tail #connect
Is0 f72 head f69 mainIn #connect
Is0 f70 mainOut f79 tail #connect
Is0 f79 head f78 mainIn #connect
Is0 f78 mainOut f73 tail #connect
Is0 f73 head f71 mainIn #connect
