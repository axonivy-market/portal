[Ivy]
1657E0FF6C38F055 7.5.0 #module
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
Cs0 @UdProcessEnd f1 '' #zField
Cs0 @UdExitEnd f4 '' #zField
Cs0 @UdEvent f3 '' #zField
Cs0 @GridStep f5 '' #zField
Cs0 @UdInit f0 '' #zField
Cs0 @PushWFArc f7 '' #zField
Cs0 @PushWFArc f6 '' #zField
Cs0 @PushWFArc f2 '' #zField
>Proto Cs0 Cs0 CustomizedLoginPageProcess #zField
Cs0 f1 51 157 20 20 13 0 #rect
Cs0 f1 @|UdProcessEndIcon #fIcon
Cs0 f4 173 157 20 20 13 0 #rect
Cs0 f4 @|UdExitEndIcon #fIcon
Cs0 f3 guid 1657B2902F7CC5ED #txt
Cs0 f3 actionTable 'out=in;
' #txt
Cs0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>login</name>
        <nameStyle>5,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f3 173 29 20 20 13 0 #rect
Cs0 f3 @|UdEventIcon #fIcon
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
        <nameStyle>6,7
17,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f5 165 91 36 24 20 -2 #rect
Cs0 f5 @|StepIcon #fIcon
Cs0 f0 guid 1657B2902F8447B8 #txt
Cs0 f0 method start() #txt
Cs0 f0 inParameterDecl '<> param;' #txt
Cs0 f0 outParameterDecl '<> result;' #txt
Cs0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start()</name>
    </language>
</elementInfo>
' #txt
Cs0 f0 51 29 20 20 13 0 #rect
Cs0 f0 @|UdInitIcon #fIcon
Cs0 f7 expr out #txt
Cs0 f7 183 115 183 157 #arcP
Cs0 f6 expr out #txt
Cs0 f6 183 49 183 91 #arcP
Cs0 f2 expr out #txt
Cs0 f2 61 49 61 157 #arcP
>Proto Cs0 .type ch.ivyteam.ivy.project.portal.examples.component.CustomizedLoginPage.CustomizedLoginPageData #txt
>Proto Cs0 .processKind HTML_DIALOG #txt
>Proto Cs0 -8 -8 16 16 16 26 #rect
>Proto Cs0 '' #fIcon
Cs0 f0 mainOut f2 tail #connect
Cs0 f2 head f1 mainIn #connect
Cs0 f3 mainOut f6 tail #connect
Cs0 f6 head f5 mainIn #connect
Cs0 f5 mainOut f7 tail #connect
Cs0 f7 head f4 mainIn #connect
