[Ivy]
[>Created: Tue Aug 08 10:21:30 ICT 2017]
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
Ps0 @GridStep f9 '' #zField
Ps0 @PushWFArc f10 '' #zField
Ps0 @PushWFArc f8 '' #zField
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
Ps0 f6 187 51 26 26 11 10 #rect
Ps0 f6 @|RichDialogMethodStartIcon #fIcon
Ps0 f7 type ch.ivy.addon.portalkit.multiapp.settings.PasswordSetting.PasswordSettingData #txt
Ps0 f7 187 203 26 26 0 12 #rect
Ps0 f7 @|RichDialogProcessEndIcon #fIcon
Ps0 f9 actionDecl 'ch.ivy.addon.portalkit.multiapp.settings.PasswordSetting.PasswordSettingData out;
' #txt
Ps0 f9 actionTable 'out=in;
' #txt
Ps0 f9 actionCode 'import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

FacesContext.getCurrentInstance().addMessage("change-password-successful",new FacesMessage(FacesMessage.SEVERITY_INFO,ivy.cms.co("/ch.ivy.addon.portalkit.ui.jsf/common/information") , ivy.cms.co("/ch.ivy.addon.portalkit.ui.jsf/passwordSetting/updatePasswordSuccessfully")));' #txt
Ps0 f9 type ch.ivy.addon.portalkit.multiapp.settings.PasswordSetting.PasswordSettingData #txt
Ps0 f9 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Update
password</name>
        <nameStyle>15
</nameStyle>
    </language>
</elementInfo>
' #txt
Ps0 f9 172 120 56 32 37 -10 #rect
Ps0 f9 @|StepIcon #fIcon
Ps0 f10 expr out #txt
Ps0 f10 200 77 200 120 #arcP
Ps0 f8 expr out #txt
Ps0 f8 200 152 200 203 #arcP
>Proto Ps0 .type ch.ivy.addon.portalkit.multiapp.settings.PasswordSetting.PasswordSettingData #txt
>Proto Ps0 .processKind HTML_DIALOG #txt
>Proto Ps0 -8 -8 16 16 16 26 #rect
>Proto Ps0 '' #fIcon
Ps0 f0 mainOut f2 tail #connect
Ps0 f2 head f1 mainIn #connect
Ps0 f6 mainOut f10 tail #connect
Ps0 f10 head f9 mainIn #connect
Ps0 f9 mainOut f8 tail #connect
Ps0 f8 head f7 mainIn #connect
