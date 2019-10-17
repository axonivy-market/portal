[Ivy]
15E13D0DD56B6D3B 7.5.0 #module
>Proto >Proto Collection #zClass
Cd0 ChangePassword Big #zClass
Cd0 B #cInfo
Cd0 #process
Cd0 @TextInP .type .type #zField
Cd0 @TextInP .processKind .processKind #zField
Cd0 @AnnotationInP-0n ai ai #zField
Cd0 @MessageFlowInP-0n messageIn messageIn #zField
Cd0 @MessageFlowOutP-0n messageOut messageOut #zField
Cd0 @TextInP .xml .xml #zField
Cd0 @TextInP .responsibility .responsibility #zField
Cd0 @StartSub f0 '' #zField
Cd0 @EndSub f1 '' #zField
Cd0 @InfoButton f5 '' #zField
Cd0 @GridStep f6 '' #zField
Cd0 @PushWFArc f7 '' #zField
Cd0 @Alternative f3 '' #zField
Cd0 @PushWFArc f8 '' #zField
Cd0 @Alternative f11 '' #zField
Cd0 @PushWFArc f2 '' #zField
Cd0 @GridStep f9 '' #zField
Cd0 @PushWFArc f4 '' #zField
Cd0 @GridStep f15 '' #zField
Cd0 @PushWFArc f16 '' #zField
Cd0 @PushWFArc f13 '' #zField
Cd0 @PushWFArc f12 '' #zField
Cd0 @CallSub f14 '' #zField
Cd0 @PushWFArc f10 '' #zField
>Proto Cd0 Cd0 ChangePassword #zField
Cd0 f0 inParamDecl '<String currentPassword,String newPassword> param;' #txt
Cd0 f0 inParamTable 'out.currentPassword=param.currentPassword;
out.newPassword=param.newPassword;
' #txt
Cd0 f0 outParamDecl '<ch.ivy.addon.portalkit.enums.ChangePasswordStatus status,String message> result;' #txt
Cd0 f0 outParamTable 'result.status=in.status;
result.message=in.message;
' #txt
Cd0 f0 callSignature changePassword(String,String) #txt
Cd0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>changePassword(String,String)</name>
    </language>
</elementInfo>
' #txt
Cd0 f0 81 49 30 30 -86 17 #rect
Cd0 f0 @|StartSubIcon #fIcon
Cd0 f1 81 625 30 30 0 15 #rect
Cd0 f1 @|EndSubIcon #fIcon
Cd0 f5 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Overwrite this process to add more handling for change password

INPUT:
 - current password
 - new password

OUTPUT:
- ChangePasswordStatus (OK, FAIL)
- message</name>
        <nameStyle>160,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cd0 f5 360 106 368 156 -180 -72 #rect
Cd0 f5 @|IBIcon #fIcon
Cd0 f6 actionTable 'out=in;
' #txt
Cd0 f6 actionCode 'in.isCurrentPasswordCorrect = ivy.session.checkPassword(in.currentPassword);' #txt
Cd0 f6 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Check current
password</name>
        <nameStyle>14,7
8,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cd0 f6 49 163 96 48 -38 -16 #rect
Cd0 f6 @|StepIcon #fIcon
Cd0 f7 expr out #txt
Cd0 f7 96 78 97 163 #arcP
Cd0 f3 80 256 32 32 0 16 #rect
Cd0 f3 @|AlternativeIcon #fIcon
Cd0 f8 expr out #txt
Cd0 f8 97 211 96 256 #arcP
Cd0 f11 80 528 32 32 0 16 #rect
Cd0 f11 @|AlternativeIcon #fIcon
Cd0 f2 expr in #txt
Cd0 f2 96 560 96 625 #arcP
Cd0 f9 actionTable 'out=in;
' #txt
Cd0 f9 actionCode 'import ch.ivy.addon.portalkit.enums.ChangePasswordStatus;
if(in.errors.size() > 0) {
	in.status = ChangePasswordStatus.FAIL;
	in.message = ivy.cms.co("/ch.ivy.addon.portalkit.ui.jsf/passwordSetting/changePasswordWSError");
}
else{
	in.status = ChangePasswordStatus.OK;
	in.message = ivy.cms.co("/ch.ivy.addon.portalkit.ui.jsf/passwordSetting/updatePasswordSuccessfully");
}' #txt
Cd0 f9 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Create return message</name>
        <nameStyle>21,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cd0 f9 24 426 144 44 -64 -8 #rect
Cd0 f9 @|StepIcon #fIcon
Cd0 f4 expr out #txt
Cd0 f4 96 470 96 528 #arcP
Cd0 f15 actionTable 'out=in;
' #txt
Cd0 f15 actionCode 'import ch.ivy.addon.portalkit.enums.ChangePasswordStatus;

in.status = ChangePasswordStatus.FAIL;
in.message = ivy.cms.co("/ch.ivy.addon.portalkit.ui.jsf/passwordSetting/wrongPassword");
' #txt
Cd0 f15 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Wrong password
message</name>
        <nameStyle>15,7
7,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cd0 f15 176 362 128 44 -43 -16 #rect
Cd0 f15 @|StepIcon #fIcon
Cd0 f16 expr in #txt
Cd0 f16 112 272 240 362 #arcP
Cd0 f16 1 240 272 #addKink
Cd0 f16 1 0.5 0 0 #arcLabel
Cd0 f13 expr out #txt
Cd0 f13 240 406 112 544 #arcP
Cd0 f13 1 240 544 #addKink
Cd0 f13 1 0.5 0 0 #arcLabel
Cd0 f12 expr out #txt
Cd0 f12 96 374 96 426 #arcP
Cd0 f14 processCall 'Ivy Data Processes/PasswordService:updatePassword(String,String)' #txt
Cd0 f14 requestActionDecl '<String username,String newPassword> param;' #txt
Cd0 f14 requestMappingAction 'param.username=ivy.session.getSessionUserName();
param.newPassword=in.newPassword;
' #txt
Cd0 f14 responseActionDecl 'ch.ivy.add.portalkit.ChangePasswordData out;
' #txt
Cd0 f14 responseMappingAction 'out=in;
out.errors=result.errors;
' #txt
Cd0 f14 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>PasswordService</name>
        <nameStyle>15,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cd0 f14 40 330 112 44 -48 -8 #rect
Cd0 f14 @|CallSubIcon #fIcon
Cd0 f10 expr in #txt
Cd0 f10 outCond in.isCurrentPasswordCorrect #txt
Cd0 f10 96 288 96 330 #arcP
>Proto Cd0 .type ch.ivy.add.portalkit.ChangePasswordData #txt
>Proto Cd0 .processKind CALLABLE_SUB #txt
>Proto Cd0 0 0 32 24 18 0 #rect
>Proto Cd0 @|BIcon #fIcon
Cd0 f0 mainOut f7 tail #connect
Cd0 f7 head f6 mainIn #connect
Cd0 f6 mainOut f8 tail #connect
Cd0 f8 head f3 in #connect
Cd0 f3 out f10 tail #connect
Cd0 f10 head f14 mainIn #connect
Cd0 f11 out f2 tail #connect
Cd0 f2 head f1 mainIn #connect
Cd0 f14 mainOut f12 tail #connect
Cd0 f12 head f9 mainIn #connect
Cd0 f9 mainOut f4 tail #connect
Cd0 f4 head f11 in #connect
Cd0 f3 out f16 tail #connect
Cd0 f16 head f15 mainIn #connect
Cd0 f15 mainOut f13 tail #connect
Cd0 f13 head f11 in #connect
