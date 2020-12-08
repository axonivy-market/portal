[Ivy]
17641AF79AEE04D7 9.2.0 #module
>Proto >Proto Collection #zClass
Rd0 ResetPassword Big #zClass
Rd0 B #cInfo
Rd0 #process
Rd0 @TextInP .type .type #zField
Rd0 @TextInP .processKind .processKind #zField
Rd0 @TextInP .xml .xml #zField
Rd0 @TextInP .responsibility .responsibility #zField
Rd0 @StartSub f0 '' #zField
Rd0 @EndSub f1 '' #zField
Rd0 @GridStep f16 '' #zField
Rd0 @PushWFArc f3 '' #zField
Rd0 @PushWFArc f2 '' #zField
>Proto Rd0 Rd0 ResetPassword #zField
Rd0 f0 inParamDecl '<String newPassword,String passwordConfirmation,String token,String username> param;' #txt
Rd0 f0 inParamTable 'out.newPassword=param.newPassword;
out.passwordConfirmation=param.passwordConfirmation;
out.token=param.token;
out.username=param.username;
' #txt
Rd0 f0 outParamDecl '<String message,Boolean resetSuccess> result;' #txt
Rd0 f0 outParamTable 'result.message=in.message;
result.resetSuccess=in.resetSuccess;
' #txt
Rd0 f0 callSignature call(String,String,String,String) #txt
Rd0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>call(String,String,String,String)</name>
    </language>
</elementInfo>
' #txt
Rd0 f0 81 49 30 30 -82 17 #rect
Rd0 f0 @|StartSubIcon #fIcon
Rd0 f1 433 49 30 30 0 15 #rect
Rd0 f1 @|EndSubIcon #fIcon
Rd0 f16 actionTable 'out=in;
' #txt
Rd0 f16 actionCode 'import ch.ivy.addon.portalkit.util.UserUtils;
import ch.ivy.addon.portalkit.constant.UserProperty;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.apache.commons.lang.StringUtils;
import ch.ivyteam.ivy.security.IUser;
import ch.ivy.addon.portalkit.util.UserUtils;
import ch.ivyteam.ivy.environment.Ivy;
in.message = "";
in.resetSuccess = false;
try {
	// find user by username
	IUser user = StringUtils.isNotBlank(in.username) ? UserUtils.findUserByUsername(in.username) : null;
	// validate token
	if (UserUtils.isValidPasswordResetToken(in.token, user)) {
		// validate new password
		if (StringUtils.isNotBlank(in.newPassword)) {
			if (in.newPassword.equals(in.passwordConfirmation)) {
				// update user password
				user.setPassword(in.newPassword);
				user.setProperty(UserProperty.RESET_PASSWORD_TOKEN, "");
				user.setProperty(UserProperty.RESET_PASSWORD_TOKEN_EXPIRY, "");
				in.message = ivy.cms.co("/ch.ivy.addon.portalkit.ui.jsf/forgotPassword/passwordResetSuccess");
				in.resetSuccess = true;
			} else {
				in.message = ivy.cms.co("/ch.ivy.addon.portalkit.ui.jsf/passwordSetting/confirmPasswordHaveMatch");
			}
		} else {
			in.message = ivy.cms.co("/ch.ivy.addon.portalkit.ui.jsf/passwordSetting/requireNewPassword");
		}
	} else {
		in.message = ivy.cms.co("/ch.ivy.addon.portalkit.ui.jsf/forgotPassword/invalidToken");
	}
} catch (Exception e) {
	Ivy.log().error("An error occurred while changing your password: {0}", e.getMessage());
	in.message = ivy.cms.co("/ch.ivy.addon.portalkit.ui.jsf/passwordSetting/changePasswordWSError");
}
if (!in.resetSuccess) {
	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, in.message, null));
	FacesContext.getCurrentInstance().validationFailed();
} else {
	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, in.message, null));
}



' #txt
Rd0 f16 security system #txt
Rd0 f16 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>reset password</name>
    </language>
</elementInfo>
' #txt
Rd0 f16 216 42 112 44 -43 -8 #rect
Rd0 f16 @|StepIcon #fIcon
Rd0 f3 111 64 216 64 #arcP
Rd0 f2 328 64 433 64 #arcP
>Proto Rd0 .type portalkit.ResetPasswordData #txt
>Proto Rd0 .processKind CALLABLE_SUB #txt
>Proto Rd0 0 0 32 24 18 0 #rect
>Proto Rd0 @|BIcon #fIcon
Rd0 f0 mainOut f3 tail #connect
Rd0 f3 head f16 mainIn #connect
Rd0 f16 mainOut f2 tail #connect
Rd0 f2 head f1 mainIn #connect
