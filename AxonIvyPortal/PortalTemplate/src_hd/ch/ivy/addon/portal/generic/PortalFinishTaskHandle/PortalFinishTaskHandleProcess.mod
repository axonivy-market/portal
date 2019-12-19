[Ivy]
16EF3459A3FCF253 7.5.0 #module
>Proto >Proto Collection #zClass
Ps0 PortalFinishTaskHandleProcess Big #zClass
Ps0 RD #cInfo
Ps0 #process
Ps0 @TextInP .type .type #zField
Ps0 @TextInP .processKind .processKind #zField
Ps0 @AnnotationInP-0n ai ai #zField
Ps0 @MessageFlowInP-0n messageIn messageIn #zField
Ps0 @MessageFlowOutP-0n messageOut messageOut #zField
Ps0 @TextInP .xml .xml #zField
Ps0 @TextInP .responsibility .responsibility #zField
Ps0 @UdInit f0 '' #zField
Ps0 @UdProcessEnd f1 '' #zField
Ps0 @PushWFArc f2 '' #zField
Ps0 @GridStep f8 '' #zField
Ps0 @UdExitEnd f4 '' #zField
Ps0 @PushWFArc f5 '' #zField
Ps0 @UdMethod f3 '' #zField
Ps0 @PushWFArc f6 '' #zField
>Proto Ps0 Ps0 PortalFinishTaskHandleProcess #zField
Ps0 f0 guid 15C67E57F20669EF #txt
Ps0 f0 method start(Boolean) #txt
Ps0 f0 inParameterDecl '<Boolean isTaskFinished> param;' #txt
Ps0 f0 inParameterMapAction 'out.isTaskFinished=param.isTaskFinished;
' #txt
Ps0 f0 outParameterDecl '<> result;' #txt
Ps0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start(isTaskFinished)</name>
        <nameStyle>21,5
</nameStyle>
    </language>
</elementInfo>
' #txt
Ps0 f0 83 51 26 26 -16 15 #rect
Ps0 f0 @|UdInitIcon #fIcon
Ps0 f1 211 51 26 26 0 12 #rect
Ps0 f1 @|UdProcessEndIcon #fIcon
Ps0 f2 expr out #txt
Ps0 f2 109 64 211 64 #arcP
Ps0 f8 actionTable 'out=in;
' #txt
Ps0 f8 actionCode 'import org.apache.commons.lang3.StringUtils;
import ch.ivy.addon.portalkit.service.GlobalSettingService;
import ch.ivy.addon.portalkit.enums.GlobalVariable;
import javax.faces.context.Flash;
import javax.faces.context.FacesContext;
import javax.faces.application.FacesMessage;

String displayMessageAfterFinishTaskVariable = new GlobalSettingService().findGlobalSettingValue(GlobalVariable.DISPLAY_MESSAGE_AFTER_FINISH_TASK.toString());
boolean displayMessageAfterFinishTask = StringUtils.isNotBlank(displayMessageAfterFinishTaskVariable) ? Boolean.parseBoolean(displayMessageAfterFinishTaskVariable) : true;
if (displayMessageAfterFinishTask) {
	Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
	if (!flash.containsKey("overridePortalGrowl")) {
		FacesMessage message = new FacesMessage(in.isTaskFinished ? ivy.cms.co("/ch.ivy.addon.portalkit.ui.jsf/common/taskFinishedSuccessfully") : ivy.cms.co("/ch.ivy.addon.portalkit.ui.jsf/common/taskCanceledAndLeftSuccessfully"));
		FacesContext.getCurrentInstance().addMessage("portal-global-growl-message", message);
	}
	flash.setRedirect(true);
	flash.setKeepMessages(true);
}' #txt
Ps0 f8 security system #txt
Ps0 f8 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Display message after&#xD;
finish task</name>
    </language>
</elementInfo>
' #txt
Ps0 f8 152 138 144 44 -54 -16 #rect
Ps0 f8 @|StepIcon #fIcon
Ps0 f4 339 147 26 26 0 12 #rect
Ps0 f4 @|UdExitEndIcon #fIcon
Ps0 f5 296 160 339 160 #arcP
Ps0 f3 guid 16EF347B4B0F3ECF #txt
Ps0 f3 method close() #txt
Ps0 f3 inParameterDecl '<> param;' #txt
Ps0 f3 outParameterDecl '<> result;' #txt
Ps0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>close()</name>
    </language>
</elementInfo>
' #txt
Ps0 f3 83 147 26 26 -23 15 #rect
Ps0 f3 @|UdMethodIcon #fIcon
Ps0 f6 109 160 152 160 #arcP
>Proto Ps0 .type ch.ivy.addon.portal.generic.PortalFinishTaskHandle.PortalFinishTaskHandleData #txt
>Proto Ps0 .processKind HTML_DIALOG #txt
>Proto Ps0 -8 -8 16 16 16 26 #rect
>Proto Ps0 '' #fIcon
Ps0 f0 mainOut f2 tail #connect
Ps0 f2 head f1 mainIn #connect
Ps0 f8 mainOut f5 tail #connect
Ps0 f5 head f4 mainIn #connect
Ps0 f3 mainOut f6 tail #connect
Ps0 f6 head f8 mainIn #connect
