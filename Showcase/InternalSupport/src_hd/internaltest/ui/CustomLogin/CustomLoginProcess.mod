[Ivy]
14E9B792756E0040 7.5.0 #module
>Proto >Proto Collection #zClass
Ls0 CustomLoginProcess Big #zClass
Ls0 RD #cInfo
Ls0 #process
Ls0 @TextInP .type .type #zField
Ls0 @TextInP .processKind .processKind #zField
Ls0 @AnnotationInP-0n ai ai #zField
Ls0 @TextInP .xml .xml #zField
Ls0 @TextInP .responsibility .responsibility #zField
Ls0 @UdInit f0 '' #zField
Ls0 @UdProcessEnd f1 '' #zField
Ls0 @PushWFArc f2 '' #zField
Ls0 @UdEvent f3 '' #zField
Ls0 @UdExitEnd f4 '' #zField
Ls0 @GridStep f5 '' #zField
Ls0 @PushWFArc f6 '' #zField
Ls0 @PushWFArc f7 '' #zField
>Proto Ls0 Ls0 CustomLoginProcess #zField
Ls0 f0 guid 14739D9F00572FE2 #txt
Ls0 f0 method start() #txt
Ls0 f0 inParameterDecl '<> param;' #txt
Ls0 f0 outParameterDecl '<Boolean loginOk> result;' #txt
Ls0 f0 outParameterMapAction 'result.loginOk=in.loginOk;
' #txt
Ls0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start()</name>
        <nameStyle>7,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ls0 f0 86 54 20 20 13 0 #rect
Ls0 f0 @|UdInitIcon #fIcon
Ls0 f1 86 182 20 20 13 0 #rect
Ls0 f1 @|UdProcessEndIcon #fIcon
Ls0 f2 expr out #txt
Ls0 f2 96 74 96 182 #arcP
Ls0 f3 guid 14739DB1F4253947 #txt
Ls0 f3 actionTable 'out=in;
' #txt
Ls0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>login</name>
        <nameStyle>5,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ls0 f3 182 54 20 20 13 0 #rect
Ls0 f3 @|UdEventIcon #fIcon
Ls0 f4 182 198 20 20 13 0 #rect
Ls0 f4 @|UdExitEndIcon #fIcon
Ls0 f5 actionTable 'out=in;
' #txt
Ls0 f5 actionCode 'import javax.faces.context.FacesContext;
import javax.faces.application.FacesMessage;
import ch.ivyteam.ivy.security.IUser;
import java.util.Locale;
in.loginOk = ivy.session.loginSessionUser(in.username, in.password);
out.password = null;
if (!in.loginOk) 
{
	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, ivy.cms.co("/ch.ivy.addon.portalkit.ui.jsf/login/loginFailed"), ""));
	FacesContext.getCurrentInstance().validationFailed();
}else{
		
		//set language from user settings or application details
		IUser sessionUser = ivy.session.getSessionUser();
		
		if (sessionUser.getEMailLanguage() != null &&
				sessionUser.getEMailLanguage() instanceof Locale) {
			Locale l = ivy.session.getSessionUser().getEMailLanguage();
			ivy.session.setContentLocale(l);
			ivy.session.setFormattingLocale(l);
			ivy.log.debug("User Settings: " + l.getDisplayName());
		} else {
			// Application Default
			Locale default = ivy.request.getApplication().getDefaultEMailLanguage();
			String language = default.getLanguage();
			String country = default.getCountry();
			Locale l = new Locale(language, country, "APPLICATION_DEFAULT");
			ivy.session.setContentLocale(l);
			ivy.session.setFormattingLocale(l);
			ivy.log.debug("Application Default: " + l.getDisplayName());
	}
	
}' #txt
Ls0 f5 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>login
get user settings</name>
        <nameStyle>6,7
17,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ls0 f5 174 116 36 24 20 -2 #rect
Ls0 f5 @|StepIcon #fIcon
Ls0 f6 expr out #txt
Ls0 f6 192 74 192 116 #arcP
Ls0 f7 expr out #txt
Ls0 f7 192 140 192 198 #arcP
>Proto Ls0 .type internaltest.ui.CustomLogin.CustomLoginData #txt
>Proto Ls0 .processKind HTML_DIALOG #txt
>Proto Ls0 -8 -8 16 16 16 26 #rect
>Proto Ls0 '' #fIcon
Ls0 f0 mainOut f2 tail #connect
Ls0 f2 head f1 mainIn #connect
Ls0 f3 mainOut f6 tail #connect
Ls0 f6 head f5 mainIn #connect
Ls0 f5 mainOut f7 tail #connect
Ls0 f7 head f4 mainIn #connect
