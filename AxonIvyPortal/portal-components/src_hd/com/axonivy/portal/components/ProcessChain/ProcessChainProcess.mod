[Ivy]
18118F75C3E2A5CF 7.5.0 #module
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
Ps0 f0 86 54 20 20 -14 13 #rect
Ps0 f0 @|UdInitIcon #fIcon
Ps0 f1 246 54 20 20 13 0 #rect
Ps0 f1 @|UdProcessEndIcon #fIcon
Ps0 f2 106 64 246 64 #arcP
Ps0 f3 guid 1769416CED6AA844 #txt
Ps0 f3 method validate(Integer,Integer) #txt
Ps0 f3 inParameterDecl '<Integer stepsSize,Integer currentStep> param;' #txt
Ps0 f3 inActionCode 'import java.util.Arrays;
import javax.faces.context.FacesContext;
import javax.faces.application.FacesMessage;
if (param.currentStep < 0 || param.currentStep >= param.stepsSize && !(param.currentStep == 0 && param.stepsSize ==0)) {
	FacesMessage error = new javax.faces.application.FacesMessage(FacesMessage.SEVERITY_ERROR, ivy.cms.co("/Dialogs/com/axonivy/portal/components/ProcessChain/CurrentStepIsNotDefined", Arrays.asList(param.currentStep)), ivy.cms.co("/Dialogs/com/axonivy/portal/components/ProcessChain/CurrentStepIsNotDefined", Arrays.asList(param.currentStep)));
	FacesContext.getCurrentInstance().addMessage("portal-global-growl-message", error);
	FacesContext.getCurrentInstance().validationFailed();
}' #txt
Ps0 f3 outParameterDecl '<> result;' #txt
Ps0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>validate(Integer,Integer)</name>
    </language>
</elementInfo>
' #txt
Ps0 f3 83 179 26 26 -25 15 #rect
Ps0 f3 @|UdMethodIcon #fIcon
Ps0 f4 243 179 26 26 0 12 #rect
Ps0 f4 @|UdProcessEndIcon #fIcon
Ps0 f5 109 192 243 192 #arcP
>Proto Ps0 .type com.axonivy.portal.components.ProcessChain.ProcessChainData #txt
>Proto Ps0 .processKind HTML_DIALOG #txt
>Proto Ps0 -8 -8 16 16 16 26 #rect
>Proto Ps0 '' #fIcon
Ps0 f0 mainOut f2 tail #connect
Ps0 f2 head f1 mainIn #connect
Ps0 f3 mainOut f5 tail #connect
Ps0 f5 head f4 mainIn #connect
