[Ivy]
1764BD49A4B3CE69 9.2.0 #module
>Proto >Proto Collection #zClass
Sl0 SendPasswordResetEmail Big #zClass
Sl0 B #cInfo
Sl0 #process
Sl0 @TextInP .type .type #zField
Sl0 @TextInP .processKind .processKind #zField
Sl0 @TextInP .xml .xml #zField
Sl0 @TextInP .responsibility .responsibility #zField
Sl0 @StartSub f0 '' #zField
Sl0 @EndSub f1 '' #zField
Sl0 @GridStep f16 '' #zField
Sl0 @GridStep f11 '' #zField
Sl0 @EMail f9 '' #zField
Sl0 @Alternative f13 '' #zField
Sl0 @PushWFArc f10 '' #zField
Sl0 @PushWFArc f14 '' #zField
Sl0 @PushWFArc f12 '' #zField
Sl0 @PushWFArc f3 '' #zField
Sl0 @PushWFArc f2 '' #zField
Sl0 @PushWFArc f4 '' #zField
Sl0 @GridStep f6 '' #zField
Sl0 @ErrorBoundaryEvent f5 '' #zField
Sl0 @PushWFArc f7 '' #zField
Sl0 @PushWFArc f8 '' #zField
>Proto Sl0 Sl0 SendPasswordResetEmail #zField
Sl0 f0 inParamDecl '<String email> param;' #txt
Sl0 f0 inParamTable 'out.email=param.email;
' #txt
Sl0 f0 outParamDecl '<> result;' #txt
Sl0 f0 callSignature call(String) #txt
Sl0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>call(String)</name>
    </language>
</elementInfo>
' #txt
Sl0 f0 81 49 30 30 -13 17 #rect
Sl0 f0 @|StartSubIcon #fIcon
Sl0 f1 945 49 30 30 0 15 #rect
Sl0 f1 @|EndSubIcon #fIcon
Sl0 f16 actionTable 'out=in;
' #txt
Sl0 f16 actionCode 'import org.apache.commons.collections.CollectionUtils;
import java.util.Calendar;
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
in.message = "";
in.isValid = false;
try {
	// generate token and set to user
	UserQuery query = Ivy.wf().getSecurityContext().users().query();
	query.where().eMailAddress().isEqualIgnoreCase(in.email);
	List<IUser> users = query.executor().results();
	if (CollectionUtils.isEmpty(users)) {
		in.message = ivy.cms.co("/ch.ivy.addon.portalkit.ui.jsf/forgotPassword/userNotFound");
	} if (users.size() == 1) {
		in.user = users.get(0);
		String token = "123";
		long expiryTime = Calendar.getInstance().getTimeInMillis() + 5*60000;
		in.user.setProperty(UserProperty.RESET_PASSWORD_TOKEN, token);
		in.user.setProperty(UserProperty.RESET_PASSWORD_TOKEN_EXPIRY, String.valueOf(expiryTime));
		String resetUrl = ServerFactory.getServer().getServerInfo().getInfoPageUrl().replaceAll("/+$$", "") + PortalNavigator.getPasswordResetUrl(token, in.user.getName());
		in.emailContent = ivy.cms.co("/ch.ivy.addon.portalkit.ui.jsf/forgotPassword/passwordResetEmailContent", Arrays.asList(in.user.getFullName(), resetUrl));
		in.isValid = true;
	} else {
		in.message = ivy.cms.co("/ch.ivy.addon.portalkit.ui.jsf/forgotPassword/usersHaveSameEmail");
	}
} catch (Exception e) {
	Ivy.log().error("An error occurred while processing forgot password request: {0}", e.getMessage());
	in.message = ivy.cms.co("/ch.ivy.addon.portalkit.ui.jsf/passwordSetting/forgotPasswordError");
}
if (!in.isValid) {
	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, in.message, ""));
	FacesContext.getCurrentInstance().validationFailed();
}' #txt
Sl0 f16 security system #txt
Sl0 f16 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>generate token</name>
    </language>
</elementInfo>
' #txt
Sl0 f16 216 42 112 44 -41 -8 #rect
Sl0 f16 @|StepIcon #fIcon
Sl0 f11 actionTable 'out=in;
' #txt
Sl0 f11 actionCode 'import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

in.message = ivy.cms.co("/ch.ivy.addon.portalkit.ui.jsf/forgotPassword/passwordResetEmailSent");
FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, in.message, ""));' #txt
Sl0 f11 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>set success message</name>
    </language>
</elementInfo>
' #txt
Sl0 f11 736 42 128 44 -61 -8 #rect
Sl0 f11 @|StepIcon #fIcon
Sl0 f9 beanConfig '"{/emailSubject ""<%=ivy.cms.co(\\""/ch.ivy.addon.portalkit.ui.jsf/forgotPassword/passwordResetEmailSubject\\"")%>""/emailFrom """"/emailReplyTo """"/emailTo ""<%=in.user.eMailAddress%>""/emailCC """"/emailBCC """"/exceptionMissingEmailAttachments ""false""/emailMessage ""<%=in.emailContent%>""/emailAttachments * }"' #txt
Sl0 f9 type _com.axonivy.portal.developerexamples.SendPasswordResetEmailOverrideData #txt
Sl0 f9 timeout 0 #txt
Sl0 f9 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>send email</name>
    </language>
</elementInfo>
' #txt
Sl0 f9 552 42 112 44 -31 -8 #rect
Sl0 f9 @|EMailIcon #fIcon
Sl0 f13 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>isValid?</name>
    </language>
</elementInfo>
' #txt
Sl0 f13 416 48 32 32 0 16 #rect
Sl0 f13 @|AlternativeIcon #fIcon
Sl0 f10 expr in #txt
Sl0 f10 outCond in.isValid #txt
Sl0 f10 448 64 552 64 #arcP
Sl0 f14 328 64 416 64 #arcP
Sl0 f12 expr out #txt
Sl0 f12 664 64 736 64 #arcP
Sl0 f3 111 64 216 64 #arcP
Sl0 f2 864 64 945 64 #arcP
Sl0 f4 expr in #txt
Sl0 f4 432 48 960 49 #arcP
Sl0 f4 1 432 32 #addKink
Sl0 f4 2 960 32 #addKink
Sl0 f4 1 0.5022590361445783 0 0 #arcLabel
Sl0 f6 actionTable 'out=in;
' #txt
Sl0 f6 actionCode 'import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

in.message = ivy.cms.co("/ch.ivy.addon.portalkit.ui.jsf/forgotPassword/passwordResetEmailCantSend");
FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, in.message, ""));' #txt
Sl0 f6 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>set email sending exception message</name>
    </language>
</elementInfo>
' #txt
Sl0 f6 688 106 224 44 -105 -8 #rect
Sl0 f6 @|StepIcon #fIcon
Sl0 f5 actionTable 'out=in;
' #txt
Sl0 f5 attachedToRef 1764BD49A4B3CE69-f9 #txt
Sl0 f5 625 81 30 30 0 15 #rect
Sl0 f5 @|ErrorBoundaryEventIcon #fIcon
Sl0 f7 640 111 688 128 #arcP
Sl0 f7 1 640 128 #addKink
Sl0 f7 1 0.19697680567278672 0 0 #arcLabel
Sl0 f8 912 128 960 79 #arcP
Sl0 f8 1 960 128 #addKink
Sl0 f8 0 0.6877282896898732 0 0 #arcLabel
>Proto Sl0 .type _com.axonivy.portal.developerexamples.SendPasswordResetEmailOverrideData #txt
>Proto Sl0 .processKind CALLABLE_SUB #txt
>Proto Sl0 0 0 32 24 18 0 #rect
>Proto Sl0 @|BIcon #fIcon
Sl0 f9 mainOut f12 tail #connect
Sl0 f12 head f11 mainIn #connect
Sl0 f16 mainOut f14 tail #connect
Sl0 f14 head f13 in #connect
Sl0 f13 out f10 tail #connect
Sl0 f10 head f9 mainIn #connect
Sl0 f0 mainOut f3 tail #connect
Sl0 f3 head f16 mainIn #connect
Sl0 f11 mainOut f2 tail #connect
Sl0 f2 head f1 mainIn #connect
Sl0 f13 out f4 tail #connect
Sl0 f4 head f1 mainIn #connect
Sl0 f5 mainOut f7 tail #connect
Sl0 f7 head f6 mainIn #connect
Sl0 f6 mainOut f8 tail #connect
Sl0 f8 head f1 mainIn #connect
