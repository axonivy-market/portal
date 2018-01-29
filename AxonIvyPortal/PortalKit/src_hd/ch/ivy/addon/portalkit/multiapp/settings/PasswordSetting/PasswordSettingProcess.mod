[Ivy]
15DBB7002BEF4583 3.20 #module
>Proto >Proto Collection #zClass
Ps0 PasswordSettingProcess Big #zClass
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
Ps0 @RichDialogMethodStart f6 '' #zField
Ps0 @RichDialogProcessEnd f7 '' #zField
Ps0 @PushWFArc f8 '' #zField
Ps0 @GridStep f9 '' #zField
Ps0 @GridStep f10 '' #zField
Ps0 @PushWFArc f11 '' #zField
Ps0 @PushWFArc f3 '' #zField
>Proto Ps0 Ps0 PasswordSettingProcess #zField
Ps0 f0 guid 15DBB70037BA9E81 #txt
Ps0 f0 type ch.ivy.addon.portalkit.multiapp.settings.PasswordSetting.PasswordSettingData #txt
Ps0 f0 method start() #txt
Ps0 f0 disableUIEvents true #txt
Ps0 f0 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<> param = methodEvent.getInputArguments();
' #txt
Ps0 f0 outParameterDecl '<> result;
' #txt
Ps0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start()</name>
    </language>
</elementInfo>
' #txt
Ps0 f0 83 51 26 26 16 6 #rect
Ps0 f0 @|RichDialogInitStartIcon #fIcon
Ps0 f1 type ch.ivy.addon.portalkit.multiapp.settings.PasswordSetting.PasswordSettingData #txt
Ps0 f1 83 147 26 26 0 12 #rect
Ps0 f1 @|RichDialogProcessEndIcon #fIcon
Ps0 f2 expr out #txt
Ps0 f2 96 77 96 147 #arcP
Ps0 f6 guid 15DBB787242E846A #txt
Ps0 f6 type ch.ivy.addon.portalkit.multiapp.settings.PasswordSetting.PasswordSettingData #txt
Ps0 f6 method updatePassword() #txt
Ps0 f6 disableUIEvents false #txt
Ps0 f6 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<> param = methodEvent.getInputArguments();
' #txt
Ps0 f6 outParameterDecl '<> result;
' #txt
Ps0 f6 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>updatePassword()</name>
    </language>
</elementInfo>
' #txt
Ps0 f6 195 51 26 26 11 10 #rect
Ps0 f6 @|RichDialogMethodStartIcon #fIcon
Ps0 f7 type ch.ivy.addon.portalkit.multiapp.settings.PasswordSetting.PasswordSettingData #txt
Ps0 f7 195 307 26 26 0 12 #rect
Ps0 f7 @|RichDialogProcessEndIcon #fIcon
Ps0 f8 expr out #txt
Ps0 f8 208 256 208 307 #arcP
Ps0 f9 actionDecl 'ch.ivy.addon.portalkit.multiapp.settings.PasswordSetting.PasswordSettingData out;
' #txt
Ps0 f9 actionTable 'out=in;
' #txt
Ps0 f9 actionCode 'import ch.ivy.addon.portalkit.enums.ChangePasswordStatus;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
if(in.status ==  ChangePasswordStatus.FAIL) {
	FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR, ivy.cms.co("/ch.ivy.addon.portalkit.ui.jsf/common/errorNotification") , in.message));
	FacesContext.getCurrentInstance().validationFailed();
}
else{
	FacesContext.getCurrentInstance().addMessage("change-password-successful",new FacesMessage(FacesMessage.SEVERITY_INFO,ivy.cms.co("/ch.ivy.addon.portalkit.ui.jsf/common/information") , in.message));
}
' #txt
Ps0 f9 type ch.ivy.addon.portalkit.multiapp.settings.PasswordSetting.PasswordSettingData #txt
Ps0 f9 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Return message</name>
        <nameStyle>14,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ps0 f9 180 224 56 32 37 -10 #rect
Ps0 f9 @|StepIcon #fIcon
Ps0 f10 actionDecl 'ch.ivy.addon.portalkit.multiapp.settings.PasswordSetting.PasswordSettingData out;
' #txt
Ps0 f10 actionTable 'out=in;
' #txt
Ps0 f10 actionCode 'import ch.ivy.addon.portalkit.enums.ChangePasswordStatus;
import java.util.HashMap;
import ch.ivy.addon.portalkit.enums.PortalLibrary;
import java.util.Arrays;
import ch.ivy.addon.portalkit.service.IvyAdapterService;
import java.util.Map;

Map parameters = new HashMap();
parameters.put("currentPassword", in.currentPassword);
parameters.put("newPassword", in.newPassword);
Map response = IvyAdapterService.startSubProcess("changePassword(String, String)", parameters, Arrays.asList(PortalLibrary.PORTAL_KIT.getValue()));
in.message = response.get("message") as String;
in.status = response.get("status") as ChangePasswordStatus;' #txt
Ps0 f10 type ch.ivy.addon.portalkit.multiapp.settings.PasswordSetting.PasswordSettingData #txt
Ps0 f10 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Change password</name>
        <nameStyle>15,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ps0 f10 152 138 112 44 -51 -8 #rect
Ps0 f10 @|StepIcon #fIcon
Ps0 f11 expr out #txt
Ps0 f11 208 77 208 138 #arcP
Ps0 f3 expr out #txt
Ps0 f3 208 182 208 224 #arcP
>Proto Ps0 .type ch.ivy.addon.portalkit.multiapp.settings.PasswordSetting.PasswordSettingData #txt
>Proto Ps0 .processKind HTML_DIALOG #txt
>Proto Ps0 -8 -8 16 16 16 26 #rect
>Proto Ps0 '' #fIcon
Ps0 f0 mainOut f2 tail #connect
Ps0 f2 head f1 mainIn #connect
Ps0 f9 mainOut f8 tail #connect
Ps0 f8 head f7 mainIn #connect
Ps0 f6 mainOut f11 tail #connect
Ps0 f11 head f10 mainIn #connect
Ps0 f10 mainOut f3 tail #connect
Ps0 f3 head f9 mainIn #connect
