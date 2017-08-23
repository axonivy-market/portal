[Ivy]
[>Created: Wed Aug 23 11:16:08 ICT 2017]
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
Ps0 @GridStep f3 '' #zField
Ps0 @PushWFArc f4 '' #zField
Ps0 @Alternative f10 '' #zField
Ps0 @PushWFArc f11 '' #zField
Ps0 @RichDialogProcessEnd f12 '' #zField
Ps0 @PushWFArc f13 '' #zField
Ps0 @CallSub f14 '' #zField
Ps0 @PushWFArc f15 '' #zField
Ps0 @PushWFArc f5 '' #zField
Ps0 @PushWFArc f8 '' #zField
Ps0 @GridStep f9 '' #zField
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
Ps0 f7 187 515 26 26 0 12 #rect
Ps0 f7 @|RichDialogProcessEndIcon #fIcon
Ps0 f3 actionDecl 'ch.ivy.addon.portalkit.multiapp.settings.PasswordSetting.PasswordSettingData out;
' #txt
Ps0 f3 actionTable 'out=in;
' #txt
Ps0 f3 actionCode 'import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;


in.isCurrentPasswordCorrect = ivy.session.checkPassword(in.currentPassword);

if(!in.isCurrentPasswordCorrect) {
	FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR, ivy.cms.co("/ch.ivy.addon.portalkit.ui.jsf/common/errorNotification") , ivy.cms.co("/ch.ivy.addon.portalkit.ui.jsf/passwordSetting/wrongPassword")));
	FacesContext.getCurrentInstance().validationFailed();
}' #txt
Ps0 f3 type ch.ivy.addon.portalkit.multiapp.settings.PasswordSetting.PasswordSettingData #txt
Ps0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Check current
password</name>
        <nameStyle>14,7
8,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ps0 f3 152 136 96 48 -38 -16 #rect
Ps0 f3 @|StepIcon #fIcon
Ps0 f4 expr out #txt
Ps0 f4 200 77 200 136 #arcP
Ps0 f10 type ch.ivy.addon.portalkit.multiapp.settings.PasswordSetting.PasswordSettingData #txt
Ps0 f10 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>is Password Correct</name>
        <nameStyle>19,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ps0 f10 184 240 32 32 -56 18 #rect
Ps0 f10 @|AlternativeIcon #fIcon
Ps0 f11 expr out #txt
Ps0 f11 200 184 200 240 #arcP
Ps0 f12 type ch.ivy.addon.portalkit.multiapp.settings.PasswordSetting.PasswordSettingData #txt
Ps0 f12 355 507 26 26 0 12 #rect
Ps0 f12 @|RichDialogProcessEndIcon #fIcon
Ps0 f13 expr in #txt
Ps0 f13 216 256 368 507 #arcP
Ps0 f13 1 368 256 #addKink
Ps0 f13 0 0.9723186064089575 0 0 #arcLabel
Ps0 f14 type ch.ivy.addon.portalkit.multiapp.settings.PasswordSetting.PasswordSettingData #txt
Ps0 f14 processCall MultiPortal/PasswordService:changePassword(String,String) #txt
Ps0 f14 doCall true #txt
Ps0 f14 requestActionDecl '<java.lang.String username,java.lang.String newPassword> param;
' #txt
Ps0 f14 requestMappingAction 'param.username=ivy.session.getSessionUserName();
param.newPassword=in.newPassword;
' #txt
Ps0 f14 responseActionDecl 'ch.ivy.addon.portalkit.multiapp.settings.PasswordSetting.PasswordSettingData out;
' #txt
Ps0 f14 responseMappingAction 'out=in;
out.errors=in.errors;
' #txt
Ps0 f14 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>PasswordService</name>
        <nameStyle>15,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ps0 f14 144 330 112 44 -48 -8 #rect
Ps0 f14 @|CallSubIcon #fIcon
Ps0 f15 expr in #txt
Ps0 f15 outCond in.isCurrentPasswordCorrect #txt
Ps0 f15 200 272 200 330 #arcP
Ps0 f5 expr out #txt
Ps0 f5 200 374 200 432 #arcP
Ps0 f8 expr out #txt
Ps0 f8 200 464 200 515 #arcP
Ps0 f9 actionDecl 'ch.ivy.addon.portalkit.multiapp.settings.PasswordSetting.PasswordSettingData out;
' #txt
Ps0 f9 actionTable 'out=in;
' #txt
Ps0 f9 actionCode 'import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
if(in.errors.size() > 0) {
	FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR, ivy.cms.co("/ch.ivy.addon.portalkit.ui.jsf/common/errorNotification") , ivy.cms.co("/ch.ivy.addon.portalkit.ui.jsf/passwordSetting/changePasswordWSError")));
	FacesContext.getCurrentInstance().validationFailed();
}
else{
	FacesContext.getCurrentInstance().addMessage("change-password-successful",new FacesMessage(FacesMessage.SEVERITY_INFO,ivy.cms.co("/ch.ivy.addon.portalkit.ui.jsf/common/information") , ivy.cms.co("/ch.ivy.addon.portalkit.ui.jsf/passwordSetting/updatePasswordSuccessfully")));
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
Ps0 f9 172 432 56 32 37 -10 #rect
Ps0 f9 @|StepIcon #fIcon
>Proto Ps0 .type ch.ivy.addon.portalkit.multiapp.settings.PasswordSetting.PasswordSettingData #txt
>Proto Ps0 .processKind HTML_DIALOG #txt
>Proto Ps0 -8 -8 16 16 16 26 #rect
>Proto Ps0 '' #fIcon
Ps0 f0 mainOut f2 tail #connect
Ps0 f2 head f1 mainIn #connect
Ps0 f9 mainOut f8 tail #connect
Ps0 f8 head f7 mainIn #connect
Ps0 f6 mainOut f4 tail #connect
Ps0 f4 head f3 mainIn #connect
Ps0 f3 mainOut f11 tail #connect
Ps0 f11 head f10 in #connect
Ps0 f13 head f12 mainIn #connect
Ps0 f10 out f15 tail #connect
Ps0 f15 head f14 mainIn #connect
Ps0 f10 out f13 tail #connect
Ps0 f14 mainOut f5 tail #connect
Ps0 f5 head f9 mainIn #connect
