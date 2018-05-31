[Ivy]
14BE3DBF5F8C376B 3.23 #module
>Proto >Proto Collection #zClass
Ls0 LoginProcess Big #zClass
Ls0 RD #cInfo
Ls0 #process
Ls0 @TextInP .ui2RdDataAction .ui2RdDataAction #zField
Ls0 @TextInP .rdData2UIAction .rdData2UIAction #zField
Ls0 @TextInP .resExport .resExport #zField
Ls0 @TextInP .type .type #zField
Ls0 @TextInP .processKind .processKind #zField
Ls0 @AnnotationInP-0n ai ai #zField
Ls0 @TextInP .xml .xml #zField
Ls0 @TextInP .responsibility .responsibility #zField
Ls0 @RichDialogInitStart f0 '' #zField
Ls0 @RichDialogProcessEnd f1 '' #zField
Ls0 @PushWFArc f2 '' #zField
Ls0 @RichDialogProcessStart f3 '' #zField
Ls0 @GridStep f5 '' #zField
Ls0 @CallSub f8 '' #zField
Ls0 @PushWFArc f9 '' #zField
Ls0 @PushWFArc f6 '' #zField
Ls0 @CallSub f10 '' #zField
Ls0 @PushWFArc f11 '' #zField
Ls0 @PushWFArc f7 '' #zField
Ls0 @RichDialogEnd f4 '' #zField
>Proto Ls0 Ls0 LoginProcess #zField
Ls0 f0 guid 14739D9F00572FE2 #txt
Ls0 f0 type ch.ivy.addon.portalkit.singleapp.general.Login.LoginData #txt
Ls0 f0 method start() #txt
Ls0 f0 disableUIEvents true #txt
Ls0 f0 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<> param = methodEvent.getInputArguments();
' #txt
Ls0 f0 outParameterDecl '<java.lang.Boolean loginOk> result;
' #txt
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
Ls0 f0 @|RichDialogInitStartIcon #fIcon
Ls0 f1 type ch.ivy.addon.portalkit.singleapp.general.Login.LoginData #txt
Ls0 f1 86 182 20 20 13 0 #rect
Ls0 f1 @|RichDialogProcessEndIcon #fIcon
Ls0 f2 expr out #txt
Ls0 f2 96 74 96 182 #arcP
Ls0 f3 guid 14739DB1F4253947 #txt
Ls0 f3 type ch.ivy.addon.portalkit.singleapp.general.Login.LoginData #txt
Ls0 f3 actionDecl 'ch.ivy.addon.portalkit.singleapp.general.Login.LoginData out;
' #txt
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
Ls0 f3 208 54 20 20 13 0 #rect
Ls0 f3 @|RichDialogProcessStartIcon #fIcon
Ls0 f5 actionDecl 'ch.ivy.addon.portalkit.singleapp.general.Login.LoginData out;
' #txt
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
Ls0 f5 type ch.ivy.addon.portalkit.singleapp.general.Login.LoginData #txt
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
Ls0 f5 200 180 36 24 20 -2 #rect
Ls0 f5 @|StepIcon #fIcon
Ls0 f8 type ch.ivy.addon.portalkit.singleapp.general.Login.LoginData #txt
Ls0 f8 processCall 'Functional Processes/PreLogin:call()' #txt
Ls0 f8 doCall true #txt
Ls0 f8 requestActionDecl '<> param;
' #txt
Ls0 f8 responseActionDecl 'ch.ivy.addon.portalkit.singleapp.general.Login.LoginData out;
' #txt
Ls0 f8 responseMappingAction 'out=in;
' #txt
Ls0 f8 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>PreLogin</name>
    </language>
</elementInfo>
' #txt
Ls0 f8 162 106 112 44 -25 -8 #rect
Ls0 f8 @|CallSubIcon #fIcon
Ls0 f9 expr out #txt
Ls0 f9 218 74 218 106 #arcP
Ls0 f6 expr out #txt
Ls0 f6 218 150 218 180 #arcP
Ls0 f10 type ch.ivy.addon.portalkit.singleapp.general.Login.LoginData #txt
Ls0 f10 processCall 'Functional Processes/PostLogin:call()' #txt
Ls0 f10 doCall true #txt
Ls0 f10 requestActionDecl '<> param;
' #txt
Ls0 f10 responseActionDecl 'ch.ivy.addon.portalkit.singleapp.general.Login.LoginData out;
' #txt
Ls0 f10 responseMappingAction 'out=in;
' #txt
Ls0 f10 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>PostLogin</name>
    </language>
</elementInfo>
' #txt
Ls0 f10 162 234 112 44 -28 -8 #rect
Ls0 f10 @|CallSubIcon #fIcon
Ls0 f11 expr out #txt
Ls0 f11 218 204 218 234 #arcP
Ls0 f7 expr out #txt
Ls0 f7 218 278 218 342 #arcP
Ls0 f4 type ch.ivy.addon.portalkit.singleapp.general.Login.LoginData #txt
Ls0 f4 guid 14739DB3653B702D #txt
Ls0 f4 208 342 20 20 13 0 #rect
Ls0 f4 @|RichDialogEndIcon #fIcon
>Proto Ls0 .type ch.ivy.addon.portalkit.singleapp.general.Login.LoginData #txt
>Proto Ls0 .processKind HTML_DIALOG #txt
>Proto Ls0 -8 -8 16 16 16 26 #rect
>Proto Ls0 '' #fIcon
Ls0 f0 mainOut f2 tail #connect
Ls0 f2 head f1 mainIn #connect
Ls0 f3 mainOut f9 tail #connect
Ls0 f9 head f8 mainIn #connect
Ls0 f8 mainOut f6 tail #connect
Ls0 f6 head f5 mainIn #connect
Ls0 f5 mainOut f11 tail #connect
Ls0 f11 head f10 mainIn #connect
Ls0 f10 mainOut f7 tail #connect
Ls0 f7 head f4 mainIn #connect
