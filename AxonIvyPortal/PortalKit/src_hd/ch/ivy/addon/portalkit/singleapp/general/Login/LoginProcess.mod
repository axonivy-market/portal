[Ivy]
14BE3DBF5F8C376B 9.2.0 #module
>Proto >Proto Collection #zClass
Ls0 LoginProcess Big #zClass
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
Ls0 @GridStep f5 '' #zField
Ls0 @PushWFArc f6 '' #zField
Ls0 @Alternative f8 '' #zField
Ls0 @PushWFArc f9 '' #zField
Ls0 @CallSub f10 '' #zField
Ls0 @PushWFArc f11 '' #zField
Ls0 @UdProcessEnd f13 '' #zField
Ls0 @PushWFArc f12 '' #zField
Ls0 @GridStep f22 '' #zField
Ls0 @PushWFArc f4 '' #zField
>Proto Ls0 Ls0 LoginProcess #zField
Ls0 f0 guid 14739D9F00572FE2 #txt
Ls0 f0 method start(String) #txt
Ls0 f0 inParameterDecl '<String callbackUrl> param;' #txt
Ls0 f0 inParameterMapAction 'out.callbackUrl=param.callbackUrl;
' #txt
Ls0 f0 outParameterDecl '<Boolean loginOk> result;' #txt
Ls0 f0 outParameterMapAction 'result.loginOk=in.loginOk;
' #txt
Ls0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start(String)</name>
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
Ls0 f3 214 54 20 20 13 0 #rect
Ls0 f3 @|UdEventIcon #fIcon
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
		} else {
			// Application Default
			Locale default = ivy.request.getApplication().getDefaultEMailLanguage();
			String language = default.getLanguage();
			String country = default.getCountry();
			Locale l = new Locale(language, country, "APPLICATION_DEFAULT");
			ivy.session.setContentLocale(l);
			ivy.session.setFormattingLocale(l);
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
Ls0 f5 206 116 36 24 20 -2 #rect
Ls0 f5 @|StepIcon #fIcon
Ls0 f6 expr out #txt
Ls0 f6 224 74 224 116 #arcP
Ls0 f8 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>loginOK?</name>
    </language>
</elementInfo>
' #txt
Ls0 f8 208 208 32 32 0 16 #rect
Ls0 f8 @|AlternativeIcon #fIcon
Ls0 f9 expr out #txt
Ls0 f9 224 140 224 208 #arcP
Ls0 f10 processCall 'Functional Processes/Login:call()' #txt
Ls0 f10 requestActionDecl '<> param;' #txt
Ls0 f10 responseActionDecl 'ch.ivy.addon.portalkit.singleapp.general.Login.LoginData out;
' #txt
Ls0 f10 responseMappingAction 'out=in;
' #txt
Ls0 f10 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Handle other stuff&#xD;
after login</name>
    </language>
</elementInfo>
' #txt
Ls0 f10 168 298 112 44 -48 -20 #rect
Ls0 f10 @|CallSubIcon #fIcon
Ls0 f11 expr in #txt
Ls0 f11 outCond in.loginOk #txt
Ls0 f11 224 240 224 298 #arcP
Ls0 f13 115 307 26 26 0 12 #rect
Ls0 f13 @|UdProcessEndIcon #fIcon
Ls0 f12 expr in #txt
Ls0 f12 208 224 128 307 #arcP
Ls0 f12 1 128 224 #addKink
Ls0 f12 1 0.3323574919922086 0 0 #arcLabel
Ls0 f22 actionTable 'out=in;
' #txt
Ls0 f22 actionCode 'import ch.ivy.addon.portalkit.util.RequestUtil;
RequestUtil.redirect(in.callbackUrl);' #txt
Ls0 f22 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Redirect to callbackUrl</name>
    </language>
</elementInfo>
' #txt
Ls0 f22 160 394 128 44 -58 -8 #rect
Ls0 f22 @|StepIcon #fIcon
Ls0 f4 expr out #txt
Ls0 f4 224 342 224 394 #arcP
>Proto Ls0 .type ch.ivy.addon.portalkit.singleapp.general.Login.LoginData #txt
>Proto Ls0 .processKind HTML_DIALOG #txt
>Proto Ls0 -8 -8 16 16 16 26 #rect
>Proto Ls0 '' #fIcon
Ls0 f0 mainOut f2 tail #connect
Ls0 f2 head f1 mainIn #connect
Ls0 f3 mainOut f6 tail #connect
Ls0 f6 head f5 mainIn #connect
Ls0 f5 mainOut f9 tail #connect
Ls0 f9 head f8 in #connect
Ls0 f8 out f11 tail #connect
Ls0 f11 head f10 mainIn #connect
Ls0 f8 out f12 tail #connect
Ls0 f12 head f13 mainIn #connect
Ls0 f10 mainOut f4 tail #connect
Ls0 f4 head f22 mainIn #connect
