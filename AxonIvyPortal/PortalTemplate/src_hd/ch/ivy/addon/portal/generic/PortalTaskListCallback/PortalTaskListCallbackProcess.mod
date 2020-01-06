[Ivy]
15C67FEA143420EE 3.20 #module
>Proto >Proto Collection #zClass
Ps0 PortalTaskListCallbackProcess Big #zClass
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
Ps0 @RichDialogMethodStart f3 '' #zField
Ps0 @GridStep f4 '' #zField
Ps0 @RichDialogProcessEnd f6 '' #zField
Ps0 @PushWFArc f7 '' #zField
Ps0 @GridStep f8 '' #zField
Ps0 @PushWFArc f9 '' #zField
Ps0 @PushWFArc f5 '' #zField
>Proto Ps0 Ps0 PortalTaskListCallbackProcess #zField
Ps0 f0 guid 15C67E57F20669EF #txt
Ps0 f0 type ch.ivy.addon.portal.generic.PortalTaskListCallback.PortalTaskListCallbackData #txt
Ps0 f0 method start(String,Boolean) #txt
Ps0 f0 disableUIEvents true #txt
Ps0 f0 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<java.lang.String callbackUrl,java.lang.Boolean isTaskFinished> param = methodEvent.getInputArguments();
' #txt
Ps0 f0 inParameterMapAction 'out.callbackUrl=param.callbackUrl;
out.isTaskFinished=param.isTaskFinished;
' #txt
Ps0 f0 outParameterDecl '<> result;
' #txt
Ps0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start()</name>
        <nameStyle>7,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ps0 f0 83 51 26 26 -16 15 #rect
Ps0 f0 @|RichDialogInitStartIcon #fIcon
Ps0 f1 type ch.ivy.addon.portal.generic.PortalTaskListCallback.PortalTaskListCallbackData #txt
Ps0 f1 211 51 26 26 0 12 #rect
Ps0 f1 @|RichDialogProcessEndIcon #fIcon
Ps0 f2 expr out #txt
Ps0 f2 109 64 211 64 #arcP
Ps0 f3 guid 15C67E8753E2C68C #txt
Ps0 f3 type ch.ivy.addon.portal.generic.PortalTaskListCallback.PortalTaskListCallbackData #txt
Ps0 f3 method redirect() #txt
Ps0 f3 disableUIEvents false #txt
Ps0 f3 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<> param = methodEvent.getInputArguments();
' #txt
Ps0 f3 outParameterDecl '<> result;
' #txt
Ps0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>redirect()</name>
    </language>
</elementInfo>
' #txt
Ps0 f3 83 147 26 26 -24 15 #rect
Ps0 f3 @|RichDialogMethodStartIcon #fIcon
Ps0 f4 actionDecl 'ch.ivy.addon.portal.generic.PortalTaskListCallback.PortalTaskListCallbackData out;
' #txt
Ps0 f4 actionTable 'out=in;
' #txt
Ps0 f4 actionCode 'import org.apache.commons.lang3.StringUtils;
import javax.faces.context.ExternalContext;
import ch.ivy.addon.portal.generic.navigation.PortalNavigator;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import ch.ivyteam.ivy.request.OpenRedirectVulnerabilityUtil;
import java.net.URLDecoder;

HttpServletRequest request = null;
ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
if (context != null){
	request = context.getRequest() as HttpServletRequest;
}

String url = URLDecoder.decode(in.callbackUrl, "UTF-8");

if (request != null && StringUtils.isNotBlank(url) && OpenRedirectVulnerabilityUtil.isValid(url, request)){
	context.redirect(url);
} else {
	PortalNavigator navigator = new PortalNavigator();
	navigator.navigateToPortalHome();
}' #txt
Ps0 f4 type ch.ivy.addon.portal.generic.PortalTaskListCallback.PortalTaskListCallbackData #txt
Ps0 f4 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>check url and redirect</name>
        <nameStyle>22,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ps0 f4 320 138 128 44 -58 -8 #rect
Ps0 f4 @|StepIcon #fIcon
Ps0 f6 type ch.ivy.addon.portal.generic.PortalTaskListCallback.PortalTaskListCallbackData #txt
Ps0 f6 499 147 26 26 0 12 #rect
Ps0 f6 @|RichDialogProcessEndIcon #fIcon
Ps0 f7 expr out #txt
Ps0 f7 448 160 499 160 #arcP
Ps0 f8 actionDecl 'ch.ivy.addon.portal.generic.PortalTaskListCallback.PortalTaskListCallbackData out;
' #txt
Ps0 f8 actionTable 'out=in;
' #txt
Ps0 f8 actionCode 'import ch.ivy.addon.portalkit.service.GlobalSettingService;
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
Ps0 f8 type ch.ivy.addon.portal.generic.PortalTaskListCallback.PortalTaskListCallbackData #txt
Ps0 f8 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Display message after
finish or cancel a task</name>
        <nameStyle>45,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ps0 f8 152 138 144 44 -54 -16 #rect
Ps0 f8 @|StepIcon #fIcon
Ps0 f9 expr out #txt
Ps0 f9 109 160 152 160 #arcP
Ps0 f5 expr out #txt
Ps0 f5 296 160 320 160 #arcP
>Proto Ps0 .type ch.ivy.addon.portal.generic.PortalTaskListCallback.PortalTaskListCallbackData #txt
>Proto Ps0 .processKind HTML_DIALOG #txt
>Proto Ps0 -8 -8 16 16 16 26 #rect
>Proto Ps0 '' #fIcon
Ps0 f0 mainOut f2 tail #connect
Ps0 f2 head f1 mainIn #connect
Ps0 f4 mainOut f7 tail #connect
Ps0 f7 head f6 mainIn #connect
Ps0 f3 mainOut f9 tail #connect
Ps0 f9 head f8 mainIn #connect
Ps0 f8 mainOut f5 tail #connect
Ps0 f5 head f4 mainIn #connect
