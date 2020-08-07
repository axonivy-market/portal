[Ivy]
172546E1FDEB1FF7 7.5.0 #module
>Proto >Proto Collection #zClass
Us0 UserProfileProcess Big #zClass
Us0 RD #cInfo
Us0 #process
Us0 @TextInP .type .type #zField
Us0 @TextInP .processKind .processKind #zField
Us0 @TextInP .xml .xml #zField
Us0 @TextInP .responsibility .responsibility #zField
Us0 @UdProcessEnd f1 '' #zField
Us0 @UdProcessEnd f8 '' #zField
Us0 @GridStep f9 '' #zField
Us0 @GridStep f10 '' #zField
Us0 @CallSub f11 '' #zField
Us0 @UdMethod f12 '' #zField
Us0 @PushWFArc f17 '' #zField
Us0 @PushWFArc f25 '' #zField
Us0 @PushWFArc f26 '' #zField
Us0 @CallSub f18 '' #zField
Us0 @UdMethod f19 '' #zField
Us0 @GridStep f20 '' #zField
Us0 @PushWFArc f29 '' #zField
Us0 @PushWFArc f31 '' #zField
Us0 @UdProcessEnd f36 '' #zField
Us0 @CallSub f39 '' #zField
Us0 @GridStep f41 '' #zField
Us0 @UdProcessEnd f42 '' #zField
Us0 @CallSub f45 '' #zField
Us0 @PushWFArc f58 '' #zField
Us0 @UdMethod f51 '' #zField
Us0 @PushWFArc f33 '' #zField
Us0 @PushWFArc f2 '' #zField
Us0 @UdInit f0 '' #zField
Us0 @PushWFArc f3 '' #zField
Us0 @PushWFArc f4 '' #zField
Us0 @PushWFArc f6 '' #zField
Us0 @GridStep f5 '' #zField
Us0 @PushWFArc f13 '' #zField
Us0 @PushWFArc f14 '' #zField
>Proto Us0 Us0 UserProfileProcess #zField
Us0 f1 211 51 26 26 0 12 #rect
Us0 f1 @|UdProcessEndIcon #fIcon
Us0 f8 811 131 26 26 0 12 #rect
Us0 f8 @|UdProcessEndIcon #fIcon
Us0 f9 actionTable 'out=in;
' #txt
Us0 f9 actionCode 'import javax.faces.context.FacesContext;
import ch.ivy.addon.portalkit.util.UserUtils;

UserUtils.setLanguague();
' #txt
Us0 f9 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Set locale</name>
    </language>
</elementInfo>
' #txt
Us0 f9 296 122 112 44 -27 -8 #rect
Us0 f9 @|StepIcon #fIcon
Us0 f10 actionTable 'out=in;
' #txt
Us0 f10 actionCode 'import org.primefaces.PrimeFaces;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import ch.ivy.addon.portalkit.util.BeanUtils;

boolean isEmailSettingsEmpty = !in.#emailSetting is initialized;
if (isEmailSettingsEmpty) {
	FacesContext.getCurrentInstance().addMessage("errors-message", new FacesMessage(FacesMessage.SEVERITY_WARN, null, ivy.cms.co("/ch.ivy.addon.portalkit.ui.jsf/emailSetting/noSettingMsg")));
}' #txt
Us0 f10 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Display errors if &#13;
no settings found</name>
    </language>
</elementInfo>
' #txt
Us0 f10 616 122 128 44 -43 -16 #rect
Us0 f10 @|StepIcon #fIcon
Us0 f11 processCall 'Ivy Data Processes/LanguageService:findUserLanguages()' #txt
Us0 f11 requestActionDecl '<> param;' #txt
Us0 f11 responseActionDecl 'ch.ivy.addon.portalkit.multiapp.settings.CentralLanguage.CentralLanguageData out;
' #txt
Us0 f11 responseMappingAction 'out=in;
out.language=result.language;
' #txt
Us0 f11 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>LanguageService</name>
    </language>
</elementInfo>
' #txt
Us0 f11 144 122 112 44 -48 -8 #rect
Us0 f11 @|CallSubIcon #fIcon
Us0 f12 guid 17255599F044D2FD #txt
Us0 f12 method initSettings() #txt
Us0 f12 inParameterDecl '<> param;' #txt
Us0 f12 outParameterDecl '<> result;' #txt
Us0 f12 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>initSettings()</name>
    </language>
</elementInfo>
' #txt
Us0 f12 83 131 26 26 -22 17 #rect
Us0 f12 @|UdMethodIcon #fIcon
Us0 f17 744 144 811 144 #arcP
Us0 f25 expr out #txt
Us0 f25 256 144 296 144 #arcP
Us0 f26 109 144 144 144 #arcP
Us0 f18 processCall 'Ivy Data Processes/LanguageService:saveUserLanguage(ch.ivy.addon.portalkit.ivydata.bo.IvyLanguage)' #txt
Us0 f18 requestActionDecl '<ch.ivy.addon.portalkit.ivydata.bo.IvyLanguage language> param;' #txt
Us0 f18 requestMappingAction 'param.language=in.language;
' #txt
Us0 f18 responseActionDecl 'ch.ivy.addon.portalkit.multiapp.settings.CentralLanguage.CentralLanguageData out;
' #txt
Us0 f18 responseMappingAction 'out=in;
' #txt
Us0 f18 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>LanguageService</name>
    </language>
</elementInfo>
' #txt
Us0 f18 144 202 112 44 -48 -8 #rect
Us0 f18 @|CallSubIcon #fIcon
Us0 f19 guid 17255B3EA938571E #txt
Us0 f19 method save() #txt
Us0 f19 inParameterDecl '<> param;' #txt
Us0 f19 outParameterDecl '<> result;' #txt
Us0 f19 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>save()</name>
    </language>
</elementInfo>
' #txt
Us0 f19 83 211 26 26 -16 20 #rect
Us0 f19 @|UdMethodIcon #fIcon
Us0 f20 actionTable 'out=in;
' #txt
Us0 f20 actionCode 'import ch.ivy.addon.portalkit.util.UserUtils;

UserUtils.setLanguague();
' #txt
Us0 f20 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Set locale</name>
    </language>
</elementInfo>
' #txt
Us0 f20 296 202 112 44 -27 -8 #rect
Us0 f20 @|StepIcon #fIcon
Us0 f29 expr out #txt
Us0 f29 109 224 144 224 #arcP
Us0 f31 expr out #txt
Us0 f31 256 224 296 224 #arcP
Us0 f36 315 347 26 26 0 12 #rect
Us0 f36 @|UdProcessEndIcon #fIcon
Us0 f39 processCall 'Ivy Data Processes/EmailSettingService:findEmailSetting()' #txt
Us0 f39 requestActionDecl '<> param;' #txt
Us0 f39 responseActionDecl 'ch.ivy.addon.portalkit.multiapp.settings.EmailSetting.EmailSettingData out;
' #txt
Us0 f39 responseMappingAction 'out=in;
out.emailSetting=result.ivyEmailSetting;
' #txt
Us0 f39 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>EmailSettingService</name>
    </language>
</elementInfo>
' #txt
Us0 f39 448 122 128 44 -55 -8 #rect
Us0 f39 @|CallSubIcon #fIcon
Us0 f41 actionTable 'out=in;
' #txt
Us0 f41 actionCode 'import ch.ivy.addon.portalkit.ivydata.service.impl.EmailSettingService;
EmailSettingService.newInstance().clearSelectedDailySummary(in.emailSetting);' #txt
Us0 f41 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Clear selected day</name>
    </language>
</elementInfo>
' #txt
Us0 f41 160 338 112 44 -51 -8 #rect
Us0 f41 @|StepIcon #fIcon
Us0 f42 811 211 26 26 0 12 #rect
Us0 f42 @|UdProcessEndIcon #fIcon
Us0 f45 processCall 'Ivy Data Processes/EmailSettingService:saveEmailSetting(ch.ivy.addon.portalkit.ivydata.bo.IvyEmailSetting)' #txt
Us0 f45 requestActionDecl '<ch.ivy.addon.portalkit.ivydata.bo.IvyEmailSetting emailSetting> param;' #txt
Us0 f45 requestMappingAction 'param.emailSetting=in.emailSetting;
' #txt
Us0 f45 responseMappingAction 'out=in;
' #txt
Us0 f45 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>EmailSettingService</name>
    </language>
</elementInfo>
' #txt
Us0 f45 448 202 128 44 -55 -8 #rect
Us0 f45 @|CallSubIcon #fIcon
Us0 f58 272 360 315 360 #arcP
Us0 f51 guid 1725FC03D28A7AF7 #txt
Us0 f51 method updateDailyList() #txt
Us0 f51 inParameterDecl '<> param;' #txt
Us0 f51 outParameterDecl '<> result;' #txt
Us0 f51 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>updateDailyList()</name>
    </language>
</elementInfo>
' #txt
Us0 f51 83 347 26 26 -32 20 #rect
Us0 f51 @|UdMethodIcon #fIcon
Us0 f33 109 360 160 360 #arcP
Us0 f2 109 64 211 64 #arcP
Us0 f0 guid 172546E1FE4FCAB2 #txt
Us0 f0 method start() #txt
Us0 f0 inParameterDecl '<> param;' #txt
Us0 f0 inActionCode 'import ch.ivyteam.util.date.Weekday;
out.dailySummaryList = [Weekday.MONDAY, Weekday.TUESDAY, Weekday.WEDNESDAY, Weekday.THURSDAY, Weekday.FRIDAY, Weekday.SATURDAY, Weekday.SUNDAY];' #txt
Us0 f0 outParameterDecl '<> result;' #txt
Us0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start()</name>
    </language>
</elementInfo>
' #txt
Us0 f0 83 51 26 26 -16 15 #rect
Us0 f0 @|UdInitIcon #fIcon
Us0 f3 expr out #txt
Us0 f3 408 144 448 144 #arcP
Us0 f4 576 144 616 144 #arcP
Us0 f6 expr out #txt
Us0 f6 408 224 448 224 #arcP
Us0 f6 0 0.4704168002362267 0 0 #arcLabel
Us0 f5 actionTable 'out=in;
' #txt
Us0 f5 actionCode 'import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.application.FacesMessage;
import ch.ivy.addon.portal.generic.bean.UserMenuBean;


FacesContext context = FacesContext.getCurrentInstance();

Flash flash = context.getExternalContext().getFlash();
FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, ivy.cms.co("/ch.ivy.addon.portalkit.ui.jsf/common/note"), ivy.cms.co("/ch.ivy.addon.portalkit.ui.jsf/userProfile/saveUserSettingsSuccessfully"));
context.addMessage("user-profile-message", message);
flash.setRedirect(true);
flash.setKeepMessages(true);

UserMenuBean userMenuBean = context.getApplication().evaluateExpressionGet(context, "#{userMenuBean}", UserMenuBean.class) as UserMenuBean;
userMenuBean.navigateToUserProfile();
' #txt
Us0 f5 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Show successful message</name>
    </language>
</elementInfo>
' #txt
Us0 f5 616 202 160 44 -75 -8 #rect
Us0 f5 @|StepIcon #fIcon
Us0 f13 776 224 811 224 #arcP
Us0 f13 0 0.43569339242613336 0 0 #arcLabel
Us0 f14 576 224 616 224 #arcP
Us0 f14 0 0.5344876828921243 0 0 #arcLabel
>Proto Us0 .type ch.ivy.addon.portalkit.settings.UserProfile.UserProfileData #txt
>Proto Us0 .processKind HTML_DIALOG #txt
>Proto Us0 -8 -8 16 16 16 26 #rect
>Proto Us0 '' #fIcon
Us0 f0 mainOut f2 tail #connect
Us0 f2 head f1 mainIn #connect
Us0 f11 mainOut f25 tail #connect
Us0 f25 head f9 mainIn #connect
Us0 f12 mainOut f26 tail #connect
Us0 f26 head f11 mainIn #connect
Us0 f10 mainOut f17 tail #connect
Us0 f17 head f8 mainIn #connect
Us0 f19 mainOut f29 tail #connect
Us0 f29 head f18 mainIn #connect
Us0 f18 mainOut f31 tail #connect
Us0 f31 head f20 mainIn #connect
Us0 f41 mainOut f58 tail #connect
Us0 f58 head f36 mainIn #connect
Us0 f51 mainOut f33 tail #connect
Us0 f33 head f41 mainIn #connect
Us0 f9 mainOut f3 tail #connect
Us0 f3 head f39 mainIn #connect
Us0 f39 mainOut f4 tail #connect
Us0 f4 head f10 mainIn #connect
Us0 f20 mainOut f6 tail #connect
Us0 f6 head f45 mainIn #connect
Us0 f5 mainOut f13 tail #connect
Us0 f13 head f42 mainIn #connect
Us0 f45 mainOut f14 tail #connect
Us0 f14 head f5 mainIn #connect
