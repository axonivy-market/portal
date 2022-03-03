[Ivy]
176CC37C73C85D55 9.4.0 #module
>Proto >Proto Collection #zClass
Cs0 CustomizedLoginPageProcess Big #zClass
Cs0 RD #cInfo
Cs0 #process
Cs0 @TextInP .type .type #zField
Cs0 @TextInP .processKind .processKind #zField
Cs0 @AnnotationInP-0n ai ai #zField
Cs0 @MessageFlowInP-0n messageIn messageIn #zField
Cs0 @MessageFlowOutP-0n messageOut messageOut #zField
Cs0 @TextInP .xml .xml #zField
Cs0 @TextInP .responsibility .responsibility #zField
Cs0 @UdExitEnd f4 '' #zField
Cs0 @UdEvent f3 '' #zField
Cs0 @GridStep f5 '' #zField
Cs0 @PushWFArc f7 '' #zField
Cs0 @PushWFArc f6 '' #zField
Cs0 @UdProcessEnd f8 '' #zField
Cs0 @UdInit f9 '' #zField
Cs0 @PushWFArc f10 '' #zField
>Proto Cs0 Cs0 CustomizedLoginPageProcess #zField
Cs0 f4 389 278 20 20 13 0 #rect
Cs0 f3 guid 1657B2902F7CC5ED #txt
Cs0 f3 actionTable 'out=in;
' #txt
Cs0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>login</name>
    </language>
</elementInfo>
' #txt
Cs0 f3 109 278 20 20 13 0 #rect
Cs0 f5 actionTable 'out=in;
' #txt
Cs0 f5 actionCode 'import javax.faces.context.FacesContext;
import javax.faces.application.FacesMessage;
import ch.ivyteam.ivy.security.IUser;
import java.util.Locale;
in.loginOk = ivy.session.loginSessionUser(in.username, in.password);
out.password = null;
if (!in.loginOk) 
{
	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, ivy.cms.co("/ch.ivy.addon.portalkit.ui.jsf/login/loginFailed"), ""));
	FacesContext.getCurrentInstance().validationFailed();
}' #txt
Cs0 f5 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>login
get user settings</name>
    </language>
</elementInfo>
' #txt
Cs0 f5 229 276 36 24 20 -2 #rect
Cs0 f7 expr out #txt
Cs0 f7 265 288 389 288 #arcP
Cs0 f6 expr out #txt
Cs0 f6 129 288 229 288 #arcP
Cs0 f8 310 118 20 20 13 0 #rect
Cs0 f9 guid 176CC396F6456056 #txt
Cs0 f9 method start(String) #txt
Cs0 f9 inParameterDecl '<String callbackUrl> param;' #txt
Cs0 f9 inParameterMapAction 'out.callbackUrl=param.callbackUrl;
' #txt
Cs0 f9 outParameterDecl '<Boolean loginOk> result;' #txt
Cs0 f9 outParameterMapAction 'result.loginOk=in.loginOk;
' #txt
Cs0 f9 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start(String)</name>
    </language>
</elementInfo>
' #txt
Cs0 f9 110 118 20 20 13 0 #rect
Cs0 f10 expr out #txt
Cs0 f10 130 128 310 128 #arcP
>Proto Cs0 .type com.axonivy.portal.developerexamples.customization.component.CustomizedLoginPage.CustomizedLoginPageData #txt
>Proto Cs0 .processKind HTML_DIALOG #txt
>Proto Cs0 -8 -8 16 16 16 26 #rect
Cs0 f3 mainOut f6 tail #connect
Cs0 f6 head f5 mainIn #connect
Cs0 f5 mainOut f7 tail #connect
Cs0 f7 head f4 mainIn #connect
Cs0 f9 mainOut f10 tail #connect
Cs0 f10 head f8 mainIn #connect
