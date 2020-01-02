[Ivy]
16F6556CB9585B58 3.20 #module
>Proto >Proto Collection #zClass
Ps0 PortalFinishTaskHandleProcess Big #zClass
Ps0 RD #cInfo
Ps0 #process
Ps0 @TextInP .ui2RdDataAction .ui2RdDataAction #zField
Ps0 @TextInP .rdData2UIAction .rdData2UIAction #zField
Ps0 @TextInP .resExport .resExport #zField
Ps0 @TextInP .type .type #zField
Ps0 @TextInP .processKind .processKind #zField
Ps0 @AnnotationInP-0n ai ai #zField
Ps0 @MessageFlowInP-0n messageIn messageIn #zField
Ps0 @MessageFlowOutP-0n messageOut messageOut #zField
Ps0 @TextInP .xml .xml #zField
Ps0 @TextInP .responsibility .responsibility #zField
Ps0 @RichDialogInitStart f0 '' #zField
Ps0 @RichDialogProcessEnd f1 '' #zField
Ps0 @PushWFArc f2 '' #zField
Ps0 @RichDialogMethodStart f8 '' #zField
Ps0 @GridStep f9 '' #zField
Ps0 @PushWFArc f10 '' #zField
Ps0 @RichDialogEnd f3 '' #zField
Ps0 @PushWFArc f4 '' #zField
>Proto Ps0 Ps0 PortalFinishTaskHandleProcess #zField
Ps0 f0 guid 16F6556CBA5FE6CA #txt
Ps0 f0 type ch.ivy.addon.portal.generic.PortalFinishTaskHandle.PortalFinishTaskHandleData #txt
Ps0 f0 method start(Boolean) #txt
Ps0 f0 disableUIEvents true #txt
Ps0 f0 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<java.lang.Boolean isTaskFinished> param = methodEvent.getInputArguments();
' #txt
Ps0 f0 inParameterMapAction 'out.isTaskFinished=param.isTaskFinished;
' #txt
Ps0 f0 outParameterDecl '<> result;
' #txt
Ps0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start(Boolean)</name>
        <nameStyle>14,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ps0 f0 83 51 26 26 -39 15 #rect
Ps0 f0 @|RichDialogInitStartIcon #fIcon
Ps0 f1 type ch.ivy.addon.portal.generic.PortalFinishTaskHandle.PortalFinishTaskHandleData #txt
Ps0 f1 211 51 26 26 0 12 #rect
Ps0 f1 @|RichDialogProcessEndIcon #fIcon
Ps0 f2 expr out #txt
Ps0 f2 109 64 211 64 #arcP
Ps0 f8 guid 16F6557A5E288242 #txt
Ps0 f8 type ch.ivy.addon.portal.generic.PortalFinishTaskHandle.PortalFinishTaskHandleData #txt
Ps0 f8 method close() #txt
Ps0 f8 disableUIEvents false #txt
Ps0 f8 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<> param = methodEvent.getInputArguments();
' #txt
Ps0 f8 outParameterDecl '<> result;
' #txt
Ps0 f8 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>close()</name>
        <nameStyle>7,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ps0 f8 83 163 26 26 -19 15 #rect
Ps0 f8 @|RichDialogMethodStartIcon #fIcon
Ps0 f9 actionDecl 'ch.ivy.addon.portal.generic.PortalFinishTaskHandle.PortalFinishTaskHandleData out;
' #txt
Ps0 f9 actionTable 'out=in;
' #txt
Ps0 f9 actionCode 'import ch.ivy.addon.portalkit.service.GlobalSettingService;
import ch.ivy.addon.portalkit.persistence.variable.GlobalVariable;
import javax.faces.context.Flash;
import javax.faces.context.FacesContext;
import javax.faces.application.FacesMessage;

boolean displayMessageAfterFinishOrCancelTask = new GlobalSettingService().findGlobalSettingValueAsBoolean(GlobalVariable.DISPLAY_MESSAGE_AFTER_FINISH_TASK);
if (displayMessageAfterFinishOrCancelTask && !ivy.session.isSessionUserUnknown()) {
	Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
	if (!flash.containsKey("overridePortalGrowl")) {
		FacesMessage message = new FacesMessage(in.isTaskFinished ? ivy.cms.co("/ch.ivy.addon.portalkit.ui.jsf/common/taskFinishedSuccessfully") : ivy.cms.co("/ch.ivy.addon.portalkit.ui.jsf/common/taskCanceledAndLeftSuccessfully"));
		FacesContext.getCurrentInstance().addMessage("portal-global-growl-message", message);
	}
	flash.setRedirect(true);
	flash.setKeepMessages(true);
}' #txt
Ps0 f9 type ch.ivy.addon.portal.generic.PortalFinishTaskHandle.PortalFinishTaskHandleData #txt
Ps0 f9 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Display message after
finish or cancel a task</name>
        <nameStyle>45,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ps0 f9 152 154 144 44 -54 -16 #rect
Ps0 f9 @|StepIcon #fIcon
Ps0 f10 expr out #txt
Ps0 f10 109 176 152 176 #arcP
Ps0 f3 type ch.ivy.addon.portal.generic.PortalFinishTaskHandle.PortalFinishTaskHandleData #txt
Ps0 f3 guid 16F6557F372ECAEE #txt
Ps0 f3 347 163 26 26 0 12 #rect
Ps0 f3 @|RichDialogEndIcon #fIcon
Ps0 f4 expr out #txt
Ps0 f4 296 176 347 176 #arcP
>Proto Ps0 .type ch.ivy.addon.portal.generic.PortalFinishTaskHandle.PortalFinishTaskHandleData #txt
>Proto Ps0 .processKind HTML_DIALOG #txt
>Proto Ps0 -8 -8 16 16 16 26 #rect
>Proto Ps0 '' #fIcon
Ps0 f0 mainOut f2 tail #connect
Ps0 f2 head f1 mainIn #connect
Ps0 f8 mainOut f10 tail #connect
Ps0 f10 head f9 mainIn #connect
Ps0 f9 mainOut f4 tail #connect
Ps0 f4 head f3 mainIn #connect
