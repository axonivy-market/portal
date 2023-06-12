[Ivy]
14BE3DBF5F8C376B 7.5.0 #module
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
Ls0 @UdExitEnd f4 '' #zField
Ls0 @PushWFArc f6 '' #zField
Ls0 @Alternative f8 '' #zField
Ls0 @PushWFArc f9 '' #zField
Ls0 @CallSub f10 '' #zField
Ls0 @PushWFArc f11 '' #zField
Ls0 @PushWFArc f7 '' #zField
Ls0 @PushWFArc f12 '' #zField
>Proto Ls0 Ls0 LoginProcess #zField
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
Ls0 f3 208 54 20 20 13 0 #rect
Ls0 f3 @|UdEventIcon #fIcon
Ls0 f5 actionTable 'out=in;
' #txt
Ls0 f5 actionCode 'import ch.ivy.addon.portalkit.util.UserUtils;
import javax.faces.context.FacesContext;
import javax.faces.application.FacesMessage;

in.loginOk = ivy.session.loginSessionUser(in.username, in.password);
out.password = null;

if (!in.loginOk) {
	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, ivy.cms.co("/ch.ivy.addon.portalkit.ui.jsf/login/loginFailed"), ""));
	FacesContext.getCurrentInstance().validationFailed();
}else{
	UserUtils.setLanguage();
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
Ls0 f5 200 116 36 24 20 -2 #rect
Ls0 f5 @|StepIcon #fIcon
Ls0 f4 206 406 20 20 13 0 #rect
Ls0 f4 @|UdExitEndIcon #fIcon
Ls0 f6 expr out #txt
Ls0 f6 218 74 218 116 #arcP
Ls0 f8 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>loginOK?</name>
    </language>
</elementInfo>
' #txt
Ls0 f8 200 208 32 32 0 16 #rect
Ls0 f8 @|AlternativeIcon #fIcon
Ls0 f9 expr out #txt
Ls0 f9 218 140 216 208 #arcP
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
Ls0 f10 160 298 112 44 -48 -20 #rect
Ls0 f10 @|CallSubIcon #fIcon
Ls0 f11 expr in #txt
Ls0 f11 outCond in.loginOk #txt
Ls0 f11 216 240 216 298 #arcP
Ls0 f7 expr out #txt
Ls0 f7 216 342 216 406 #arcP
Ls0 f12 expr in #txt
Ls0 f12 200 224 206 416 #arcP
Ls0 f12 1 112 224 #addKink
Ls0 f12 2 112 416 #addKink
Ls0 f12 1 0.515625 0 0 #arcLabel
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
Ls0 f10 mainOut f7 tail #connect
Ls0 f7 head f4 mainIn #connect
Ls0 f8 out f12 tail #connect
Ls0 f12 head f4 mainIn #connect
