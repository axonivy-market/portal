[Ivy]
1612B5181308632F 3.20 #module
>Proto >Proto Collection #zClass
Ws0 WarningBeforeLeavingTaskProcess Big #zClass
Ws0 RD #cInfo
Ws0 #process
Ws0 @TextInP .ui2RdDataAction .ui2RdDataAction #zField
Ws0 @TextInP .rdData2UIAction .rdData2UIAction #zField
Ws0 @TextInP .resExport .resExport #zField
Ws0 @TextInP .type .type #zField
Ws0 @TextInP .processKind .processKind #zField
Ws0 @AnnotationInP-0n ai ai #zField
Ws0 @MessageFlowInP-0n messageIn messageIn #zField
Ws0 @MessageFlowOutP-0n messageOut messageOut #zField
Ws0 @TextInP .xml .xml #zField
Ws0 @TextInP .responsibility .responsibility #zField
Ws0 @RichDialogInitStart f0 '' #zField
Ws0 @RichDialogProcessEnd f1 '' #zField
Ws0 @PushWFArc f2 '' #zField
Ws0 @RichDialogMethodStart f3 '' #zField
Ws0 @RichDialogProcessEnd f5 '' #zField
Ws0 @GridStep f8 '' #zField
Ws0 @PushWFArc f4 '' #zField
Ws0 @RichDialogMethodStart f6 '' #zField
Ws0 @PushWFArc f7 '' #zField
Ws0 @PushWFArc f9 '' #zField
>Proto Ws0 Ws0 WarningBeforeLeavingTaskProcess #zField
Ws0 f0 guid 15F80B73AFE43AE4 #txt
Ws0 f0 type ch.ivy.addon.portalkit.component.WarningBeforeLeavingTask.WarningBeforeLeavingTaskData #txt
Ws0 f0 method start() #txt
Ws0 f0 disableUIEvents true #txt
Ws0 f0 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<> param = methodEvent.getInputArguments();
' #txt
Ws0 f0 outParameterDecl '<> result;
' #txt
Ws0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start()</name>
    </language>
</elementInfo>
' #txt
Ws0 f0 83 51 26 26 -16 15 #rect
Ws0 f0 @|RichDialogInitStartIcon #fIcon
Ws0 f1 type ch.ivy.addon.portalkit.component.WarningBeforeLeavingTask.WarningBeforeLeavingTaskData #txt
Ws0 f1 211 51 26 26 0 12 #rect
Ws0 f1 @|RichDialogProcessEndIcon #fIcon
Ws0 f2 expr out #txt
Ws0 f2 109 64 211 64 #arcP
Ws0 f3 guid 16F9D9EDCB6DF622 #txt
Ws0 f3 type ch.ivy.addon.portalkit.component.WarningBeforeLeavingTask.WarningBeforeLeavingTaskData #txt
Ws0 f3 method leave() #txt
Ws0 f3 disableUIEvents false #txt
Ws0 f3 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<> param = methodEvent.getInputArguments();
' #txt
Ws0 f3 outParameterDecl '<> result;
' #txt
Ws0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>leave</name>
        <nameStyle>5,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ws0 f3 83 147 26 26 -14 15 #rect
Ws0 f3 @|RichDialogMethodStartIcon #fIcon
Ws0 f5 type ch.ivy.addon.portalkit.component.WarningBeforeLeavingTask.WarningBeforeLeavingTaskData #txt
Ws0 f5 403 147 26 26 0 12 #rect
Ws0 f5 @|RichDialogProcessEndIcon #fIcon
Ws0 f8 actionDecl 'ch.ivy.addon.portalkit.component.WarningBeforeLeavingTask.WarningBeforeLeavingTaskData out;
' #txt
Ws0 f8 actionTable 'out=in;
' #txt
Ws0 f8 actionCode 'import ch.ivy.addon.portalkit.service.GlobalSettingService;
import ch.ivy.addon.portalkit.persistence.variable.GlobalVariable;
import javax.faces.context.Flash;
import javax.faces.context.FacesContext;
import javax.faces.application.FacesMessage;

boolean displayMessageAfterFinishOrLeaveTask = new GlobalSettingService().findGlobalSettingValueAsBoolean(GlobalVariable.DISPLAY_MESSAGE_AFTER_FINISH_TASK);
if (displayMessageAfterFinishOrLeaveTask && !ivy.session.isSessionUserUnknown()) {
	Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
	if (!flash.containsKey("overridePortalGrowl")) {
		FacesMessage message = new FacesMessage(in.isTaskFinished ? ivy.cms.co("/ch.ivy.addon.portalkit.ui.jsf/common/taskFinishedSuccessfully") : ivy.cms.co("/ch.ivy.addon.portalkit.ui.jsf/common/taskCanceledAndLeftSuccessfully"));
		FacesContext.getCurrentInstance().addMessage("portal-global-growl-message", message);
	}
	flash.setRedirect(true);
	flash.setKeepMessages(true);
}' #txt
Ws0 f8 type ch.ivy.addon.portalkit.component.WarningBeforeLeavingTask.WarningBeforeLeavingTaskData #txt
Ws0 f8 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Display message after
finish or leave a task</name>
        <nameStyle>44,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ws0 f8 184 138 144 44 -54 -16 #rect
Ws0 f8 @|StepIcon #fIcon
Ws0 f4 expr out #txt
Ws0 f4 109 160 184 160 #arcP
Ws0 f6 guid 16F9D9FF5CF8385A #txt
Ws0 f6 type ch.ivy.addon.portalkit.component.WarningBeforeLeavingTask.WarningBeforeLeavingTaskData #txt
Ws0 f6 method reserve() #txt
Ws0 f6 disableUIEvents false #txt
Ws0 f6 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<> param = methodEvent.getInputArguments();
' #txt
Ws0 f6 outParameterDecl '<> result;
' #txt
Ws0 f6 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>reserve</name>
        <nameStyle>7,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ws0 f6 83 211 26 26 -20 15 #rect
Ws0 f6 @|RichDialogMethodStartIcon #fIcon
Ws0 f7 expr out #txt
Ws0 f7 328 160 403 160 #arcP
Ws0 f9 expr out #txt
Ws0 f9 109 224 256 182 #arcP
Ws0 f9 1 256 224 #addKink
Ws0 f9 0 0.7347330585157841 0 0 #arcLabel
>Proto Ws0 .type ch.ivy.addon.portalkit.component.WarningBeforeLeavingTask.WarningBeforeLeavingTaskData #txt
>Proto Ws0 .processKind HTML_DIALOG #txt
>Proto Ws0 -8 -8 16 16 16 26 #rect
>Proto Ws0 '' #fIcon
Ws0 f0 mainOut f2 tail #connect
Ws0 f2 head f1 mainIn #connect
Ws0 f3 mainOut f4 tail #connect
Ws0 f4 head f8 mainIn #connect
Ws0 f8 mainOut f7 tail #connect
Ws0 f7 head f5 mainIn #connect
Ws0 f6 mainOut f9 tail #connect
Ws0 f9 head f8 mainIn #connect
