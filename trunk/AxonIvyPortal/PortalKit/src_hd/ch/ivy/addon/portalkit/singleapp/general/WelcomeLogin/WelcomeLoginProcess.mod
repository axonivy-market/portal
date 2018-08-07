[Ivy]
[>Created: Wed Aug 09 14:50:54 ICT 2017]
15DBAF386D08165F 3.20 #module
>Proto >Proto Collection #zClass
Fs0 WelcomeLoginProcess Big #zClass
Fs0 RD #cInfo
Fs0 #process
Fs0 @TextInP .ui2RdDataAction .ui2RdDataAction #zField
Fs0 @TextInP .rdData2UIAction .rdData2UIAction #zField
Fs0 @TextInP .resExport .resExport #zField
Fs0 @TextInP .type .type #zField
Fs0 @TextInP .processKind .processKind #zField
Fs0 @AnnotationInP-0n ai ai #zField
Fs0 @MessageFlowInP-0n messageIn messageIn #zField
Fs0 @MessageFlowOutP-0n messageOut messageOut #zField
Fs0 @TextInP .xml .xml #zField
Fs0 @TextInP .responsibility .responsibility #zField
Fs0 @RichDialogInitStart f0 '' #zField
Fs0 @RichDialogProcessEnd f1 '' #zField
Fs0 @PushWFArc f2 '' #zField
Fs0 @RichDialogProcessStart f6 '' #zField
Fs0 @GridStep f9 '' #zField
Fs0 @PushWFArc f10 '' #zField
Fs0 @RichDialogEnd f11 '' #zField
Fs0 @PushWFArc f12 '' #zField
Fs0 @RichDialogProcessStart f3 '' #zField
Fs0 @PushWFArc f7 '' #zField
>Proto Fs0 Fs0 WelcomeLoginProcess #zField
Fs0 f0 guid 15DBAF38701397C8 #txt
Fs0 f0 type ch.ivy.addon.portalkit.singleapp.general.WelcomeLogin.WelcomeLoginData #txt
Fs0 f0 method start() #txt
Fs0 f0 disableUIEvents true #txt
Fs0 f0 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<> param = methodEvent.getInputArguments();
' #txt
Fs0 f0 inParameterMapAction 'out.loginOk=true;
' #txt
Fs0 f0 outParameterDecl '<> result;
' #txt
Fs0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start()</name>
        <nameStyle>7,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Fs0 f0 83 51 26 26 -16 15 #rect
Fs0 f0 @|RichDialogInitStartIcon #fIcon
Fs0 f1 type ch.ivy.addon.portalkit.singleapp.general.WelcomeLogin.WelcomeLoginData #txt
Fs0 f1 211 51 26 26 0 12 #rect
Fs0 f1 @|RichDialogProcessEndIcon #fIcon
Fs0 f2 expr out #txt
Fs0 f2 109 64 211 64 #arcP
Fs0 f6 guid 15DC0B73CAFCF09E #txt
Fs0 f6 type ch.ivy.addon.portalkit.singleapp.general.WelcomeLogin.WelcomeLoginData #txt
Fs0 f6 actionDecl 'ch.ivy.addon.portalkit.singleapp.general.WelcomeLogin.WelcomeLoginData out;
' #txt
Fs0 f6 actionTable 'out=in;
out.loginOk=false;
out.password="Developer";
out.username="Developer1";
' #txt
Fs0 f6 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>loginAsAdmin</name>
        <nameStyle>12,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Fs0 f6 83 243 26 26 -38 15 #rect
Fs0 f6 @|RichDialogProcessStartIcon #fIcon
Fs0 f9 actionDecl 'ch.ivy.addon.portalkit.singleapp.general.WelcomeLogin.WelcomeLoginData out;
' #txt
Fs0 f9 actionTable 'out=in;
' #txt
Fs0 f9 actionCode 'import javax.faces.context.FacesContext;
import javax.faces.application.FacesMessage;
import ch.ivyteam.ivy.security.IUser;
import java.util.Locale;
in.loginOk = ivy.session.loginSessionUser(in.username, in.password);
out.password = null;
if (!in.loginOk) 
{
	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, ivy.cms.co("/ch.ivy.addon.portalkit.ui.jsf/login/welcomeLoginFailed"), ""));
	FacesContext.getCurrentInstance().validationFailed();
}else{
		
	//set language from user settings or application details
	IUser sessionUser = ivy.session.getSessionUser();
	
	if (sessionUser != null && sessionUser.getEMailLanguage() != null && sessionUser.getEMailLanguage() instanceof Locale) {
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
Fs0 f9 type ch.ivy.addon.portalkit.singleapp.general.WelcomeLogin.WelcomeLoginData #txt
Fs0 f9 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>login</name>
        <nameStyle>5
</nameStyle>
    </language>
</elementInfo>
' #txt
Fs0 f9 232 234 112 44 -13 -8 #rect
Fs0 f9 @|StepIcon #fIcon
Fs0 f10 expr out #txt
Fs0 f10 109 256 232 256 #arcP
Fs0 f11 type ch.ivy.addon.portalkit.singleapp.general.WelcomeLogin.WelcomeLoginData #txt
Fs0 f11 guid 15DC0BBF2C2E920E #txt
Fs0 f11 499 243 26 26 0 12 #rect
Fs0 f11 @|RichDialogEndIcon #fIcon
Fs0 f12 expr out #txt
Fs0 f12 344 256 499 256 #arcP
Fs0 f3 guid 15DC5F8E83BCDBA1 #txt
Fs0 f3 type ch.ivy.addon.portalkit.singleapp.general.WelcomeLogin.WelcomeLoginData #txt
Fs0 f3 actionDecl 'ch.ivy.addon.portalkit.singleapp.general.WelcomeLogin.WelcomeLoginData out;
' #txt
Fs0 f3 actionTable 'out=in;
' #txt
Fs0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>goToLogin</name>
    </language>
</elementInfo>
' #txt
Fs0 f3 83 339 26 26 -29 12 #rect
Fs0 f3 @|RichDialogProcessStartIcon #fIcon
Fs0 f7 expr out #txt
Fs0 f7 109 352 512 269 #arcP
Fs0 f7 1 512 352 #addKink
Fs0 f7 0 0.6174406710932551 0 0 #arcLabel
>Proto Fs0 .type ch.ivy.addon.portalkit.singleapp.general.WelcomeLogin.WelcomeLoginData #txt
>Proto Fs0 .processKind HTML_DIALOG #txt
>Proto Fs0 -8 -8 16 16 16 26 #rect
>Proto Fs0 '' #fIcon
Fs0 f0 mainOut f2 tail #connect
Fs0 f2 head f1 mainIn #connect
Fs0 f6 mainOut f10 tail #connect
Fs0 f10 head f9 mainIn #connect
Fs0 f9 mainOut f12 tail #connect
Fs0 f12 head f11 mainIn #connect
Fs0 f3 mainOut f7 tail #connect
Fs0 f7 head f11 mainIn #connect
