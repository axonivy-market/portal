[Ivy]
175F90CDEB5D001F 9.2.0 #module
>Proto >Proto Collection #zClass
Fs0 ForgotPasswordProcess Big #zClass
Fs0 RD #cInfo
Fs0 #process
Fs0 @TextInP .type .type #zField
Fs0 @TextInP .processKind .processKind #zField
Fs0 @TextInP .xml .xml #zField
Fs0 @TextInP .responsibility .responsibility #zField
Fs0 @UdInit f0 '' #zField
Fs0 @UdProcessEnd f1 '' #zField
Fs0 @PushWFArc f2 '' #zField
Fs0 @UdEvent f3 '' #zField
Fs0 @UdExitEnd f4 '' #zField
Fs0 @PushWFArc f5 '' #zField
Fs0 @UdEvent f18 '' #zField
Fs0 @GridStep f16 '' #zField
Fs0 @PushWFArc f7 '' #zField
Fs0 @UdProcessEnd f6 '' #zField
Fs0 @EMail f9 '' #zField
Fs0 @GridStep f11 '' #zField
Fs0 @PushWFArc f12 '' #zField
Fs0 @PushWFArc f8 '' #zField
Fs0 @Alternative f13 '' #zField
Fs0 @PushWFArc f14 '' #zField
Fs0 @PushWFArc f10 '' #zField
Fs0 @PushWFArc f15 '' #zField
>Proto Fs0 Fs0 ForgotPasswordProcess #zField
Fs0 f0 guid 175F90CDEF7A1E5C #txt
Fs0 f0 method start() #txt
Fs0 f0 inParameterDecl '<> param;' #txt
Fs0 f0 outParameterDecl '<> result;' #txt
Fs0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start()</name>
    </language>
</elementInfo>
' #txt
Fs0 f0 83 51 26 26 -16 15 #rect
Fs0 f0 @|UdInitIcon #fIcon
Fs0 f1 211 51 26 26 0 12 #rect
Fs0 f1 @|UdProcessEndIcon #fIcon
Fs0 f2 109 64 211 64 #arcP
Fs0 f3 guid 0175F90CDFB96F15 #txt
Fs0 f3 actionTable 'out=in;
' #txt
Fs0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>close</name>
    </language>
</elementInfo>
' #txt
Fs0 f3 83 147 26 26 -15 15 #rect
Fs0 f3 @|UdEventIcon #fIcon
Fs0 f4 211 147 26 26 0 12 #rect
Fs0 f4 @|UdExitEndIcon #fIcon
Fs0 f5 109 160 211 160 #arcP
Fs0 f18 guid 175F92152EC548D2 #txt
Fs0 f18 actionTable 'out=in;
' #txt
Fs0 f18 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>send</name>
    </language>
</elementInfo>
' #txt
Fs0 f18 83 243 26 26 -14 15 #rect
Fs0 f18 @|UdEventIcon #fIcon
Fs0 f16 actionTable 'out=in;
' #txt
Fs0 f16 actionCode 'import java.util.Calendar;
import ch.ivy.addon.portalkit.constant.UserProperty;
import ch.ivyteam.ivy.server.ServerFactory;
import java.util.Arrays;
import ch.ivy.addon.portal.generic.navigation.PortalNavigator;
import ch.ivy.addon.portalkit.util.HashUtils;
import ch.ivyteam.ivy.security.query.UserQuery;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import ch.ivyteam.ivy.security.IUser;
import ch.ivyteam.ivy.environment.Ivy;
String message = "";
in.isValid = false;
try {
	// generate token and set to user
  UserQuery query = Ivy.wf().getSecurityContext().users().query();
  query.where().eMailAddress().isEqualIgnoreCase(in.email);
	List<IUser> users = query.executor().results();
	if (users != null && users.size() == 1) {
		in.user = users.get(0);
		String token = HashUtils.hash(in.user.getName());
		long expiryTime = Calendar.getInstance().getTimeInMillis() + 5*60000;
		in.user.setProperty(UserProperty.RESET_PASSWORD_TOKEN, token);
		in.user.setProperty(UserProperty.RESET_PASSWORD_TOKEN_EXPIRY, String.valueOf(expiryTime));
		String resetUrl = ServerFactory.getServer().getServerInfo().getInfoPageUrl() + PortalNavigator.getPasswordResetUrl(token, in.user.getName());
		in.emailContent = ivy.cms.co("/ch.ivy.addon.portalkit.ui.jsf/forgotPassword/passwordResetEmailContent", Arrays.asList(in.user.getFullName(), resetUrl));
		in.isValid = true;
	} else if (users == null || users.size() == 0) {
		message = ivy.cms.co("/ch.ivy.addon.portalkit.ui.jsf/forgotPassword/userNotFound");
	} else {
		message = ivy.cms.co("/ch.ivy.addon.portalkit.ui.jsf/forgotPassword/usersHaveSameEmail");
	}
} catch (Exception e) {
	Ivy.log().error("An error occurred while processing forgot password request: {0}", e.getMessage());
	message = ivy.cms.co("/ch.ivy.addon.portalkit.ui.jsf/passwordSetting/forgotPasswordError");
}
if (!in.isValid) {
	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, message, ""));
	FacesContext.getCurrentInstance().validationFailed();
}' #txt
Fs0 f16 security system #txt
Fs0 f16 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>generate token</name>
    </language>
</elementInfo>
' #txt
Fs0 f16 200 234 112 44 -41 -8 #rect
Fs0 f16 @|StepIcon #fIcon
Fs0 f7 109 256 200 256 #arcP
Fs0 f6 931 243 26 26 0 12 #rect
Fs0 f6 @|UdProcessEndIcon #fIcon
Fs0 f9 beanConfig '"{/emailSubject ""<%=ivy.cms.co(\\""/ch.ivy.addon.portalkit.ui.jsf/forgotPassword/passwordResetEmailSubject\\"")%>""/emailFrom """"/emailReplyTo """"/emailTo ""<%=in.user.eMailAddress%>""/emailCC """"/emailBCC """"/exceptionMissingEmailAttachments ""false""/emailMessage ""<%=in.emailContent%>""/emailAttachments * }"' #txt
Fs0 f9 type ch.ivy.addon.portalkit.singleapp.general.ForgotPassword.ForgotPasswordData #txt
Fs0 f9 timeout 0 #txt
Fs0 f9 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>send email</name>
    </language>
</elementInfo>
' #txt
Fs0 f9 536 234 112 44 -31 -8 #rect
Fs0 f9 @|EMailIcon #fIcon
Fs0 f11 actionTable 'out=in;
' #txt
Fs0 f11 actionCode 'import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, ivy.cms.co("/ch.ivy.addon.portalkit.ui.jsf/forgotPassword/passwordResetEmailSent"), null));' #txt
Fs0 f11 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>show result</name>
    </language>
</elementInfo>
' #txt
Fs0 f11 728 234 112 44 -32 -8 #rect
Fs0 f11 @|StepIcon #fIcon
Fs0 f12 expr out #txt
Fs0 f12 648 256 728 256 #arcP
Fs0 f8 840 256 931 256 #arcP
Fs0 f13 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>isValid?</name>
    </language>
</elementInfo>
' #txt
Fs0 f13 400 240 32 32 0 16 #rect
Fs0 f13 @|AlternativeIcon #fIcon
Fs0 f14 312 256 400 256 #arcP
Fs0 f10 expr in #txt
Fs0 f10 outCond in.isValid #txt
Fs0 f10 432 256 536 256 #arcP
Fs0 f15 expr in #txt
Fs0 f15 416 272 944 269 #arcP
Fs0 f15 1 416 304 #addKink
Fs0 f15 2 944 304 #addKink
Fs0 f15 1 0.5022590361445783 0 0 #arcLabel
>Proto Fs0 .type ch.ivy.addon.portalkit.singleapp.general.ForgotPassword.ForgotPasswordData #txt
>Proto Fs0 .processKind HTML_DIALOG #txt
>Proto Fs0 -8 -8 16 16 16 26 #rect
>Proto Fs0 '' #fIcon
Fs0 f0 mainOut f2 tail #connect
Fs0 f2 head f1 mainIn #connect
Fs0 f3 mainOut f5 tail #connect
Fs0 f5 head f4 mainIn #connect
Fs0 f18 mainOut f7 tail #connect
Fs0 f7 head f16 mainIn #connect
Fs0 f9 mainOut f12 tail #connect
Fs0 f12 head f11 mainIn #connect
Fs0 f11 mainOut f8 tail #connect
Fs0 f8 head f6 mainIn #connect
Fs0 f16 mainOut f14 tail #connect
Fs0 f14 head f13 in #connect
Fs0 f13 out f10 tail #connect
Fs0 f10 head f9 mainIn #connect
Fs0 f13 out f15 tail #connect
Fs0 f15 head f6 mainIn #connect
