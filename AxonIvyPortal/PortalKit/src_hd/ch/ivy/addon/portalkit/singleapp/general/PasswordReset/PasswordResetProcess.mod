[Ivy]
175F97002EA7435A 9.2.0 #module
>Proto >Proto Collection #zClass
Ps0 PasswordResetProcess Big #zClass
Ps0 RD #cInfo
Ps0 #process
Ps0 @TextInP .type .type #zField
Ps0 @TextInP .processKind .processKind #zField
Ps0 @TextInP .xml .xml #zField
Ps0 @TextInP .responsibility .responsibility #zField
Ps0 @UdInit f0 '' #zField
Ps0 @UdProcessEnd f1 '' #zField
Ps0 @PushWFArc f2 '' #zField
Ps0 @UdEvent f3 '' #zField
Ps0 @UdExitEnd f4 '' #zField
Ps0 @PushWFArc f5 '' #zField
Ps0 @GridStep f16 '' #zField
Ps0 @UdProcessEnd f6 '' #zField
Ps0 @UdEvent f10 '' #zField
Ps0 @PushWFArc f7 '' #zField
Ps0 @PushWFArc f8 '' #zField
>Proto Ps0 Ps0 PasswordResetProcess #zField
Ps0 f0 guid 175F970030C8B97C #txt
Ps0 f0 method start(String,String) #txt
Ps0 f0 inParameterDecl '<String token,String username> param;' #txt
Ps0 f0 inParameterMapAction 'out.token=param.token;
out.username=param.username;
' #txt
Ps0 f0 outParameterDecl '<> result;' #txt
Ps0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start(String,String)</name>
    </language>
</elementInfo>
' #txt
Ps0 f0 83 51 26 26 -16 15 #rect
Ps0 f0 @|UdInitIcon #fIcon
Ps0 f1 211 51 26 26 0 12 #rect
Ps0 f1 @|UdProcessEndIcon #fIcon
Ps0 f2 109 64 211 64 #arcP
Ps0 f3 guid 175F9700322D7666 #txt
Ps0 f3 actionTable 'out=in;
' #txt
Ps0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>close</name>
    </language>
</elementInfo>
' #txt
Ps0 f3 83 147 26 26 -15 15 #rect
Ps0 f3 @|UdEventIcon #fIcon
Ps0 f4 211 147 26 26 0 12 #rect
Ps0 f4 @|UdExitEndIcon #fIcon
Ps0 f5 109 160 211 160 #arcP
Ps0 f16 actionTable 'out=in;
' #txt
Ps0 f16 actionCode 'import java.util.Calendar;
import ch.ivy.addon.portalkit.constant.UserProperty;
import ch.ivyteam.ivy.server.ServerFactory;
import java.util.Arrays;
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
	if (user != null) {
		// validate token
	  String token = user.getProperty(UserProperty.RESET_PASSWORD_TOKEN);
		long expiryTime = Long.valueOf(user.getProperty(UserProperty.RESET_PASSWORD_TOKEN_EXPIRY));
		long currentTime = Calendar.getInstance().getTimeInMillis();
		if(StringUtils.isNotBlank(in.token) && in.token.equals(token) && currentTime < expiryTime) {
			// validate new password
			if(StringUtils.isNotBlank(in.newPassword)) {
				if(in.newPassword.equals(in.passwordConfirmation)) {
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
	} else {
	  in.message = ivy.cms.co("/ch.ivy.addon.portalkit.ui.jsf/forgotPassword/userNotFound");
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
Ps0 f16 security system #txt
Ps0 f16 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>reset password</name>
    </language>
</elementInfo>
' #txt
Ps0 f16 200 234 112 44 -43 -8 #rect
Ps0 f16 @|StepIcon #fIcon
Ps0 f6 403 243 26 26 0 12 #rect
Ps0 f6 @|UdProcessEndIcon #fIcon
Ps0 f10 guid 176217D65744540F #txt
Ps0 f10 actionTable 'out=in;
out.resetSuccess=false;
' #txt
Ps0 f10 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>reset</name>
    </language>
</elementInfo>
' #txt
Ps0 f10 83 243 26 26 -14 15 #rect
Ps0 f10 @|UdEventIcon #fIcon
Ps0 f7 109 256 200 256 #arcP
Ps0 f8 312 256 403 256 #arcP
>Proto Ps0 .type ch.ivy.addon.portalkit.singleapp.general.PasswordReset.PasswordResetData #txt
>Proto Ps0 .processKind HTML_DIALOG #txt
>Proto Ps0 -8 -8 16 16 16 26 #rect
>Proto Ps0 '' #fIcon
Ps0 f0 mainOut f2 tail #connect
Ps0 f2 head f1 mainIn #connect
Ps0 f3 mainOut f5 tail #connect
Ps0 f5 head f4 mainIn #connect
Ps0 f10 mainOut f7 tail #connect
Ps0 f7 head f16 mainIn #connect
Ps0 f16 mainOut f8 tail #connect
Ps0 f8 head f6 mainIn #connect
