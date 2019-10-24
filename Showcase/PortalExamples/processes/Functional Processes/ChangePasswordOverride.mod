[Ivy]
1657FC622FD937C4 7.5.0 #module
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
Cd0 @CallSub f14 '' #zField
Cd0 @InfoButton f5 '' #zField
Cd0 @GridStep f6 '' #zField
Cd0 @PushWFArc f7 '' #zField
Cd0 @Alternative f3 '' #zField
Cd0 @PushWFArc f8 '' #zField
Cd0 @PushWFArc f10 '' #zField
Cd0 @Alternative f11 '' #zField
Cd0 @PushWFArc f2 '' #zField
Cd0 @GridStep f9 '' #zField
Cd0 @PushWFArc f12 '' #zField
Cd0 @PushWFArc f4 '' #zField
Cd0 @PushWFArc f17 '' #zField
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
Cd0 f0 169 49 30 30 -86 17 #rect
Cd0 f0 @|StartSubIcon #fIcon
Cd0 f1 169 625 30 30 0 15 #rect
Cd0 f1 @|EndSubIcon #fIcon
Cd0 f14 processCall 'Ivy Data Processes/PasswordService:updatePassword(String,String)' #txt
Cd0 f14 requestActionDecl '<String username,String newPassword> param;' #txt
Cd0 f14 requestMappingAction 'param.username=ivy.session.getSessionUserName();
param.newPassword=in.newPassword;
' #txt
Cd0 f14 responseActionDecl '_ch.ivyteam.ivy.project.portal.examples.ChangePasswordOverrideData out;
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
Cd0 f14 128 330 112 44 -48 -8 #rect
Cd0 f14 @|CallSubIcon #fIcon
Cd0 f5 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>This process overrides ChangePassword process in Portal Kit. Now the password change process 
will also check if the new password has at least 1 special character, 1 number, 1 uppercase and 1 lowercase
character.</name>
        <nameStyle>212,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cd0 f5 416 154 608 60 -297 -24 #rect
Cd0 f5 @|IBIcon #fIcon
Cd0 f6 actionTable 'out=in;
' #txt
Cd0 f6 actionCode 'import ch.ivy.addon.portalkit.enums.ChangePasswordStatus;
import ch.ivyteam.ivy.project.portal.examples.service.PasswordCheckingService;

if(!ivy.session.checkPassword(in.currentPassword)) {
	in.status = ChangePasswordStatus.FAIL;
	in.message = "Authentication failed, your password seems to be wrong!";
} else if(!new PasswordCheckingService().isPasswordStrongEnough(in.newPassword)){
	in.status = ChangePasswordStatus.FAIL;
	in.message = "New password is not strong enough. Must contains at least 1 special character, 1 number, 1 uppercase and 1 lowercase character!";
} else {
	in.status = ChangePasswordStatus.OK;
}
' #txt
Cd0 f6 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Check if can change password</name>
        <nameStyle>28,5
</nameStyle>
    </language>
</elementInfo>
' #txt
Cd0 f6 97 165 176 44 -84 -8 #rect
Cd0 f6 @|StepIcon #fIcon
Cd0 f7 expr out #txt
Cd0 f7 184 78 185 165 #arcP
Cd0 f3 168 256 32 32 0 16 #rect
Cd0 f3 @|AlternativeIcon #fIcon
Cd0 f8 expr out #txt
Cd0 f8 185 209 184 256 #arcP
Cd0 f10 expr in #txt
Cd0 f10 outCond 'in.status != ch.ivy.addon.portalkit.enums.ChangePasswordStatus.FAIL' #txt
Cd0 f10 184 288 184 330 #arcP
Cd0 f11 168 528 32 32 0 16 #rect
Cd0 f11 @|AlternativeIcon #fIcon
Cd0 f2 expr in #txt
Cd0 f2 184 560 184 625 #arcP
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
Cd0 f9 112 426 144 44 -64 -8 #rect
Cd0 f9 @|StepIcon #fIcon
Cd0 f12 expr out #txt
Cd0 f12 184 374 184 426 #arcP
Cd0 f4 expr out #txt
Cd0 f4 184 470 184 528 #arcP
Cd0 f17 expr in #txt
Cd0 f17 200 272 200 544 #arcP
Cd0 f17 1 344 272 #addKink
Cd0 f17 2 344 544 #addKink
Cd0 f17 2 0.5984799407087763 0 0 #arcLabel
>Proto Cd0 .type _ch.ivyteam.ivy.project.portal.examples.ChangePasswordOverrideData #txt
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
Cd0 f3 out f17 tail #connect
Cd0 f17 head f11 in #connect
