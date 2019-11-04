[Ivy]
14C2C99D7388AB9B 7.5.0 #module
>Proto >Proto Collection #zClass
Es0 EmailSettingProcess Big #zClass
Es0 RD #cInfo
Es0 #process
Es0 @TextInP .type .type #zField
Es0 @TextInP .processKind .processKind #zField
Es0 @AnnotationInP-0n ai ai #zField
Es0 @TextInP .xml .xml #zField
Es0 @TextInP .responsibility .responsibility #zField
Es0 @UdInit f0 '' #zField
Es0 @UdProcessEnd f3 '' #zField
Es0 @PushWFArc f4 '' #zField
Es0 @UdMethod f5 '' #zField
Es0 @CallSub f23 '' #zField
Es0 @PushWFArc f24 '' #zField
Es0 @GridStep f36 '' #zField
Es0 @UdProcessEnd f38 '' #zField
Es0 @PushWFArc f39 '' #zField
Es0 @UdEvent f40 '' #zField
Es0 @UdProcessEnd f41 '' #zField
Es0 @GridStep f42 '' #zField
Es0 @PushWFArc f43 '' #zField
Es0 @PushWFArc f44 '' #zField
Es0 @UdEvent f1 '' #zField
Es0 @GridStep f2 '' #zField
Es0 @PushWFArc f6 '' #zField
Es0 @CallSub f7 '' #zField
Es0 @PushWFArc f8 '' #zField
Es0 @GridStep f9 '' #zField
Es0 @UdProcessEnd f14 '' #zField
Es0 @PushWFArc f15 '' #zField
Es0 @UdEvent f11 '' #zField
Es0 @UdProcessEnd f12 '' #zField
Es0 @GridStep f16 '' #zField
Es0 @PushWFArc f17 '' #zField
Es0 @PushWFArc f13 '' #zField
Es0 @PushWFArc f18 '' #zField
Es0 @Alternative f19 '' #zField
Es0 @PushWFArc f20 '' #zField
Es0 @PushWFArc f10 '' #zField
Es0 @PushWFArc f21 '' #zField
Es0 @GridStep f25 '' #zField
Es0 @UdEvent f22 '' #zField
Es0 @PushWFArc f26 '' #zField
Es0 @UdProcessEnd f27 '' #zField
Es0 @PushWFArc f28 '' #zField
>Proto Es0 Es0 EmailSettingProcess #zField
Es0 f0 guid 167961CFD377EB28 #txt
Es0 f0 method start() #txt
Es0 f0 inParameterDecl 'ch.ivy.addon.portalkit.multiapp.settings.EmailSetting.EmailSettingData out;
' #txt
Es0 f0 inParameterMapAction 'out.user=ivy.session.getSessionUserName();
' #txt
Es0 f0 inActionCode 'import ch.ivyteam.util.date.Weekday;
out.dailySummaryList = [Weekday.MONDAY, Weekday.TUESDAY, Weekday.WEDNESDAY, Weekday.THURSDAY, Weekday.FRIDAY, Weekday.SATURDAY, Weekday.SUNDAY];' #txt
Es0 f0 outParameterDecl '<> result;' #txt
Es0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start()</name>
    </language>
</elementInfo>
' #txt
Es0 f0 51 51 26 26 -16 15 #rect
Es0 f0 @|UdInitIcon #fIcon
Es0 f3 211 51 26 26 0 12 #rect
Es0 f3 @|UdProcessEndIcon #fIcon
Es0 f4 expr out #txt
Es0 f4 77 64 211 64 #arcP
Es0 f5 guid 167965C29E430919 #txt
Es0 f5 method findEmailSettings() #txt
Es0 f5 inParameterDecl '<> param;' #txt
Es0 f5 outParameterDecl '<> result;' #txt
Es0 f5 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>findEmailSettings()</name>
    </language>
</elementInfo>
' #txt
Es0 f5 51 147 26 26 -52 15 #rect
Es0 f5 @|UdMethodIcon #fIcon
Es0 f23 processCall 'Ivy Data Processes/EmailSettingService:findEmailSettings(String)' #txt
Es0 f23 requestActionDecl '<String username> param;' #txt
Es0 f23 requestMappingAction 'param.username=in.user;
' #txt
Es0 f23 responseActionDecl 'ch.ivy.addon.portalkit.multiapp.settings.EmailSetting.EmailSettingData out;
' #txt
Es0 f23 responseMappingAction 'out=in;
out.emailSettings=result.ivyEmailSettings;
out.errors=result.errors;
' #txt
Es0 f23 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>EmailSettingService</name>
    </language>
</elementInfo>
' #txt
Es0 f23 128 138 128 44 -55 -8 #rect
Es0 f23 @|CallSubIcon #fIcon
Es0 f24 expr out #txt
Es0 f24 77 160 128 160 #arcP
Es0 f36 actionTable 'out=in;
' #txt
Es0 f36 actionCode 'import org.primefaces.PrimeFaces;
import ch.ivy.addon.portalkit.ivydata.bo.IvyEmailSetting;
import org.apache.commons.collections4.CollectionUtils;
import javax.faces.context.FacesContext;
import javax.faces.application.FacesMessage;
import ch.ivy.addon.portalkit.util.BeanUtils;

BeanUtils.invokeBeanMethodViaMethodExpression("#{errorDisplayBean.displayErrors}", in.errors);

PrimeFaces primeFaces = PrimeFaces.current();
boolean isEmailSettingsEmpty = CollectionUtils.isEmpty(in.emailSettings);
if (isEmailSettingsEmpty) {
	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, null, ivy.cms.co("/ch.ivy.addon.portalkit.ui.jsf/emailSetting/noSettingMsg")));
}

primeFaces.ajax().addCallbackParam("settingEmpty", isEmailSettingsEmpty);
primeFaces.ajax().addCallbackParam("settingForAllApp", in.settingForAllApp);

if(in.#generalEmailSetting == null){
	out.generalEmailSetting = new IvyEmailSetting();
}' #txt
Es0 f36 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Display errors if have</name>
    </language>
</elementInfo>
' #txt
Es0 f36 296 138 128 44 -57 -8 #rect
Es0 f36 @|StepIcon #fIcon
Es0 f38 507 147 26 26 0 12 #rect
Es0 f38 @|UdProcessEndIcon #fIcon
Es0 f39 expr out #txt
Es0 f39 424 160 507 160 #arcP
Es0 f40 guid 167967216E1FCB61 #txt
Es0 f40 actionTable 'out=in;
' #txt
Es0 f40 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>settingChange</name>
    </language>
</elementInfo>
' #txt
Es0 f40 51 243 26 26 -47 15 #rect
Es0 f40 @|UdEventIcon #fIcon
Es0 f41 371 243 26 26 0 12 #rect
Es0 f41 @|UdProcessEndIcon #fIcon
Es0 f42 actionTable 'out=in;
' #txt
Es0 f42 actionCode 'import ch.ivy.addon.portalkit.ivydata.bo.IvyEmailSetting;

out.generalEmailSetting = new IvyEmailSetting();' #txt
Es0 f42 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Set general &#xD;
email setting</name>
    </language>
</elementInfo>
' #txt
Es0 f42 168 234 112 44 -35 -16 #rect
Es0 f42 @|StepIcon #fIcon
Es0 f43 expr out #txt
Es0 f43 77 256 168 256 #arcP
Es0 f44 expr out #txt
Es0 f44 280 256 371 256 #arcP
Es0 f1 guid 1679674F3DCE40E8 #txt
Es0 f1 actionTable 'out=in;
' #txt
Es0 f1 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>saveEmailSettings</name>
    </language>
</elementInfo>
' #txt
Es0 f1 51 339 26 26 -47 15 #rect
Es0 f1 @|UdEventIcon #fIcon
Es0 f2 actionTable 'out=in;
' #txt
Es0 f2 actionCode 'import ch.ivy.addon.portalkit.ivydata.service.impl.EmailSettingService;

if (in.settingForAllApp) {
	EmailSettingService.newInstance().updateIvyEmailSettings(in.generalEmailSetting, in.emailSettings);
}' #txt
Es0 f2 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Update all email&#xD;
settings if all app is selected</name>
    </language>
</elementInfo>
' #txt
Es0 f2 104 330 176 44 -70 -16 #rect
Es0 f2 @|StepIcon #fIcon
Es0 f6 expr out #txt
Es0 f6 77 352 104 352 #arcP
Es0 f7 processCall 'Ivy Data Processes/EmailSettingService:saveEmailSettings(String,List<ch.ivy.addon.portalkit.ivydata.bo.IvyEmailSetting>)' #txt
Es0 f7 requestActionDecl '<String username,java.util.List<ch.ivy.addon.portalkit.ivydata.bo.IvyEmailSetting> emailSettings> param;' #txt
Es0 f7 requestMappingAction 'param.username=in.user;
param.emailSettings=in.emailSettings;
' #txt
Es0 f7 responseActionDecl 'ch.ivy.addon.portalkit.multiapp.settings.EmailSetting.EmailSettingData out;
' #txt
Es0 f7 responseMappingAction 'out=in;
out.errors=result.errors;
' #txt
Es0 f7 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>EmailSettingService</name>
    </language>
</elementInfo>
' #txt
Es0 f7 320 330 128 44 -55 -8 #rect
Es0 f7 @|CallSubIcon #fIcon
Es0 f8 expr out #txt
Es0 f8 280 352 320 352 #arcP
Es0 f9 actionTable 'out=in;
' #txt
Es0 f9 actionCode 'import ch.ivy.addon.portalkit.util.BeanUtils;

BeanUtils.invokeBeanMethodViaMethodExpression("#{errorDisplayBean.displayErrors}", in.errors);' #txt
Es0 f9 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Display errors if have</name>
    </language>
</elementInfo>
' #txt
Es0 f9 568 426 128 44 -57 -8 #rect
Es0 f9 @|StepIcon #fIcon
Es0 f14 755 339 26 26 0 12 #rect
Es0 f14 @|UdProcessEndIcon #fIcon
Es0 f15 expr out #txt
Es0 f15 696 448 768 365 #arcP
Es0 f15 1 768 448 #addKink
Es0 f15 0 0.8254647321695485 0 0 #arcLabel
Es0 f11 guid 16796FC348D700CB #txt
Es0 f11 actionTable 'out=in;
' #txt
Es0 f11 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>updateGrowl</name>
    </language>
</elementInfo>
' #txt
Es0 f11 51 531 26 26 -47 15 #rect
Es0 f11 @|UdEventIcon #fIcon
Es0 f12 371 531 26 26 0 12 #rect
Es0 f12 @|UdProcessEndIcon #fIcon
Es0 f16 actionTable 'out=in;
' #txt
Es0 f16 actionCode 'import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, ivy.cms.co("/ch.ivy.addon.portalkit.ui.jsf/common/note"), ivy.cms.co("/ch.ivy.addon.portalkit.ui.jsf/emailSetting/saveEmailSettingsSuccessfully")));' #txt
Es0 f16 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Display the sucessful saving message</name>
    </language>
</elementInfo>
' #txt
Es0 f16 112 522 224 44 -106 -8 #rect
Es0 f16 @|StepIcon #fIcon
Es0 f17 expr out #txt
Es0 f17 77 544 112 544 #arcP
Es0 f13 expr out #txt
Es0 f13 336 544 371 544 #arcP
Es0 f18 expr out #txt
Es0 f18 256 160 296 160 #arcP
Es0 f19 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>successful?</name>
    </language>
</elementInfo>
' #txt
Es0 f19 496 336 32 32 -37 -34 #rect
Es0 f19 @|AlternativeIcon #fIcon
Es0 f20 expr out #txt
Es0 f20 448 352 496 352 #arcP
Es0 f10 expr in #txt
Es0 f10 outCond in.errors.isEmpty() #txt
Es0 f10 528 352 755 352 #arcP
Es0 f21 expr in #txt
Es0 f21 512 368 568 448 #arcP
Es0 f21 1 512 448 #addKink
Es0 f21 1 0.13338566089674114 0 0 #arcLabel
Es0 f25 actionTable 'out=in;
' #txt
Es0 f25 actionCode 'import ch.ivy.addon.portalkit.ivydata.service.impl.EmailSettingService;
EmailSettingService.newInstance().clearSelectedDailySummary(in.emailSettings);' #txt
Es0 f25 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Clear selected day</name>
    </language>
</elementInfo>
' #txt
Es0 f25 136 618 112 44 -51 -8 #rect
Es0 f25 @|StepIcon #fIcon
Es0 f22 guid 16D8A994508AA2CD #txt
Es0 f22 actionTable 'out=in;
' #txt
Es0 f22 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>updateDailyList</name>
    </language>
</elementInfo>
' #txt
Es0 f22 51 627 26 26 -14 15 #rect
Es0 f22 @|UdEventIcon #fIcon
Es0 f26 77 640 136 640 #arcP
Es0 f27 371 627 26 26 0 12 #rect
Es0 f27 @|UdProcessEndIcon #fIcon
Es0 f28 248 640 371 640 #arcP
>Proto Es0 .type ch.ivy.addon.portalkit.multiapp.settings.EmailSetting.EmailSettingData #txt
>Proto Es0 .processKind HTML_DIALOG #txt
>Proto Es0 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <swimlaneLabel>Start Method</swimlaneLabel>
        <swimlaneLabel>Events</swimlaneLabel>
        <swimlaneLabel></swimlaneLabel>
    </language>
    <swimlaneSize>384</swimlaneSize>
    <swimlaneSize>496</swimlaneSize>
    <swimlaneColor>-1</swimlaneColor>
    <swimlaneColor>-1</swimlaneColor>
</elementInfo>
' #txt
>Proto Es0 -8 -8 16 16 16 26 #rect
>Proto Es0 '' #fIcon
Es0 f0 mainOut f4 tail #connect
Es0 f4 head f3 mainIn #connect
Es0 f5 mainOut f24 tail #connect
Es0 f24 head f23 mainIn #connect
Es0 f36 mainOut f39 tail #connect
Es0 f39 head f38 mainIn #connect
Es0 f40 mainOut f43 tail #connect
Es0 f43 head f42 mainIn #connect
Es0 f42 mainOut f44 tail #connect
Es0 f44 head f41 mainIn #connect
Es0 f1 mainOut f6 tail #connect
Es0 f6 head f2 mainIn #connect
Es0 f2 mainOut f8 tail #connect
Es0 f8 head f7 mainIn #connect
Es0 f9 mainOut f15 tail #connect
Es0 f15 head f14 mainIn #connect
Es0 f11 mainOut f17 tail #connect
Es0 f17 head f16 mainIn #connect
Es0 f16 mainOut f13 tail #connect
Es0 f13 head f12 mainIn #connect
Es0 f23 mainOut f18 tail #connect
Es0 f18 head f36 mainIn #connect
Es0 f7 mainOut f20 tail #connect
Es0 f20 head f19 in #connect
Es0 f19 out f10 tail #connect
Es0 f10 head f14 mainIn #connect
Es0 f19 out f21 tail #connect
Es0 f21 head f9 mainIn #connect
Es0 f22 mainOut f26 tail #connect
Es0 f26 head f25 mainIn #connect
Es0 f25 mainOut f28 tail #connect
Es0 f28 head f27 mainIn #connect
