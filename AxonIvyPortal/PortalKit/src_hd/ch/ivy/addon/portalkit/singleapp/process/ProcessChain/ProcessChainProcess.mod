[Ivy]
14BCA45FFAD46D2B 9.2.0 #module
>Proto >Proto Collection #zClass
Ps0 ProcessChainProcess Big #zClass
Ps0 RD #cInfo
Ps0 #process
Ps0 @TextInP .type .type #zField
Ps0 @TextInP .processKind .processKind #zField
Ps0 @AnnotationInP-0n ai ai #zField
Ps0 @TextInP .xml .xml #zField
Ps0 @TextInP .responsibility .responsibility #zField
Ps0 @UdInit f0 '' #zField
Ps0 @UdProcessEnd f1 '' #zField
Ps0 @PushWFArc f2 '' #zField
Ps0 @UdMethod f3 '' #zField
Ps0 @UdProcessEnd f4 '' #zField
Ps0 @PushWFArc f5 '' #zField
>Proto Ps0 Ps0 ProcessChainProcess #zField
Ps0 f0 guid 14B71A2F7A9C7C5F #txt
Ps0 f0 method start() #txt
Ps0 f0 inParameterDecl '<> param;' #txt
Ps0 f0 outParameterDecl '<> result;' #txt
Ps0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start()</name>
    </language>
</elementInfo>
' #txt
Ps0 f0 86 54 20 20 13 0 #rect
Ps0 f0 @|UdInitIcon #fIcon
Ps0 f1 86 150 20 20 13 0 #rect
Ps0 f1 @|UdProcessEndIcon #fIcon
Ps0 f2 expr out #txt
Ps0 f2 96 74 96 150 #arcP
Ps0 f3 guid 176981E34798AC09 #txt
Ps0 f3 method validate(java.util.List<String>,Integer) #txt
Ps0 f3 inParameterDecl '<java.util.List<String> steps,Integer currentStep> param;' #txt
Ps0 f3 inActionCode 'import java.util.Arrays;
import javax.faces.context.FacesContext;
import javax.faces.application.FacesMessage;
if (param.currentStep < 0 || param.currentStep >= param.steps.size()) {
	FacesMessage error = new javax.faces.application.FacesMessage(FacesMessage.SEVERITY_ERROR, ivy.cms.co("/ch.ivy.addon.portalkit.ui.jsf/components/processChain/currentStepIsNotDefined", Arrays.asList(param.currentStep)), ivy.cms.co("/ch.ivy.addon.portalkit.ui.jsf/components/processChain/currentStepIsNotDefined", Arrays.asList(param.currentStep)));
	FacesContext.getCurrentInstance().addMessage("portal-global-growl-message", error);
	FacesContext.getCurrentInstance().validationFailed();
}' #txt
Ps0 f3 outParameterDecl '<> result;' #txt
Ps0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>validate(List&lt;String&gt;,Integer)</name>
    </language>
</elementInfo>
' #txt
Ps0 f3 83 243 26 26 -25 15 #rect
Ps0 f3 @|UdMethodIcon #fIcon
Ps0 f4 339 243 26 26 0 12 #rect
Ps0 f4 @|UdProcessEndIcon #fIcon
Ps0 f5 109 256 339 256 #arcP
>Proto Ps0 .type ch.ivy.addon.portalkit.singleapp.process.ProcessChain.ProcessChainData #txt
>Proto Ps0 .processKind HTML_DIALOG #txt
>Proto Ps0 -8 -8 16 16 16 26 #rect
>Proto Ps0 '' #fIcon
Ps0 f0 mainOut f2 tail #connect
Ps0 f2 head f1 mainIn #connect
Ps0 f3 mainOut f5 tail #connect
Ps0 f5 head f4 mainIn #connect
