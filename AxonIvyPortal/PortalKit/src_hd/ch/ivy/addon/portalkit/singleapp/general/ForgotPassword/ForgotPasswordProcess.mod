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
Fs0 @UdProcessEnd f6 '' #zField
Fs0 @CallSub f7 '' #zField
Fs0 @PushWFArc f8 '' #zField
Fs0 @GridStep f11 '' #zField
Fs0 @PushWFArc f10 '' #zField
Fs0 @PushWFArc f9 '' #zField
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
Fs0 f6 627 243 26 26 0 12 #rect
Fs0 f6 @|UdProcessEndIcon #fIcon
Fs0 f7 processCall 'Functional Processes/SendPasswordResetEmail:call(String)' #txt
Fs0 f7 requestActionDecl '<String email> param;' #txt
Fs0 f7 requestMappingAction 'param.email=in.email;
' #txt
Fs0 f7 responseMappingAction 'out=in;
out.isValid=result.isValid;
out.message=result.message;
' #txt
Fs0 f7 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>send email</name>
    </language>
</elementInfo>
' #txt
Fs0 f7 200 234 112 44 -31 -8 #rect
Fs0 f7 @|CallSubIcon #fIcon
Fs0 f8 109 256 200 256 #arcP
Fs0 f11 actionTable 'out=in;
' #txt
Fs0 f11 actionCode 'import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

if (!in.isValid) {
	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, in.message, ""));
	FacesContext.getCurrentInstance().validationFailed();
} else {
	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, in.message, null));
}' #txt
Fs0 f11 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>show result</name>
    </language>
</elementInfo>
' #txt
Fs0 f11 408 234 112 44 -32 -8 #rect
Fs0 f11 @|StepIcon #fIcon
Fs0 f10 312 256 408 256 #arcP
Fs0 f9 520 256 627 256 #arcP
>Proto Fs0 .type ch.ivy.addon.portalkit.singleapp.general.ForgotPassword.ForgotPasswordData #txt
>Proto Fs0 .processKind HTML_DIALOG #txt
>Proto Fs0 -8 -8 16 16 16 26 #rect
>Proto Fs0 '' #fIcon
Fs0 f0 mainOut f2 tail #connect
Fs0 f2 head f1 mainIn #connect
Fs0 f3 mainOut f5 tail #connect
Fs0 f5 head f4 mainIn #connect
Fs0 f18 mainOut f8 tail #connect
Fs0 f8 head f7 mainIn #connect
Fs0 f7 mainOut f10 tail #connect
Fs0 f10 head f11 mainIn #connect
Fs0 f11 mainOut f9 tail #connect
Fs0 f9 head f6 mainIn #connect
