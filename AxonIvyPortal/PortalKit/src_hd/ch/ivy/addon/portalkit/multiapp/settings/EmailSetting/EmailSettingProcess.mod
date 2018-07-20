[Ivy]
14C2C99D7388AB9B 3.20 #module
>Proto >Proto Collection #zClass
Es0 EmailSettingProcess Big #zClass
Es0 RD #cInfo
Es0 #process
Es0 @TextInP .ui2RdDataAction .ui2RdDataAction #zField
Es0 @TextInP .rdData2UIAction .rdData2UIAction #zField
Es0 @TextInP .resExport .resExport #zField
Es0 @TextInP .type .type #zField
Es0 @TextInP .processKind .processKind #zField
Es0 @AnnotationInP-0n ai ai #zField
Es0 @TextInP .xml .xml #zField
Es0 @TextInP .responsibility .responsibility #zField
Es0 @RichDialogInitStart f0 '' #zField
Es0 @RichDialogProcessStart f8 '' #zField
Es0 @RichDialogProcessEnd f9 '' #zField
Es0 @RichDialogProcessStart f11 '' #zField
Es0 @RichDialogEnd f12 '' #zField
Es0 @CallSub f14 '' #zField
Es0 @GridStep f16 '' #zField
Es0 @PushWFArc f17 '' #zField
Es0 @GridStep f6 '' #zField
Es0 @PushWFArc f7 '' #zField
Es0 @PushWFArc f10 '' #zField
Es0 @CallSub f18 '' #zField
Es0 @GridStep f20 '' #zField
Es0 @PushWFArc f21 '' #zField
Es0 @PushWFArc f19 '' #zField
Es0 @RichDialogProcessEnd f24 '' #zField
Es0 @PushWFArc f25 '' #zField
Es0 @RichDialogProcessEnd f1 '' #zField
Es0 @GridStep f15 '' #zField
Es0 @PushWFArc f26 '' #zField
Es0 @PushWFArc f2 '' #zField
Es0 @GridStep f27 '' #zField
Es0 @PushWFArc f28 '' #zField
Es0 @PushWFArc f13 '' #zField
Es0 @RichDialogProcessStart f29 '' #zField
Es0 @RichDialogProcessEnd f30 '' #zField
Es0 @GridStep f31 '' #zField
Es0 @PushWFArc f32 '' #zField
Es0 @PushWFArc f33 '' #zField
Es0 @RichDialogMethodStart f34 '' #zField
Es0 @PushWFArc f22 '' #zField
>Proto Es0 Es0 EmailSettingProcess #zField
Es0 f0 guid 14BD8AF4A523241B #txt
Es0 f0 type ch.ivy.addon.portalkit.multiapp.settings.EmailSetting.EmailSettingData #txt
Es0 f0 method start() #txt
Es0 f0 disableUIEvents true #txt
Es0 f0 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<> param = methodEvent.getInputArguments();
' #txt
Es0 f0 inActionCode 'out.user = ivy.session.getSessionUserName();' #txt
Es0 f0 outParameterDecl '<> result;
' #txt
Es0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start()</name>
        <nameStyle>7,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Es0 f0 62 54 20 20 13 0 #rect
Es0 f0 @|RichDialogInitStartIcon #fIcon
Es0 f8 guid 14BDD83061E58491 #txt
Es0 f8 type ch.ivy.addon.portalkit.multiapp.settings.EmailSetting.EmailSettingData #txt
Es0 f8 actionDecl 'ch.ivy.addon.portalkit.multiapp.settings.EmailSetting.EmailSettingData out;
' #txt
Es0 f8 actionTable 'out=in;
' #txt
Es0 f8 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>settingChange</name>
        <nameStyle>13,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Es0 f8 446 54 20 20 13 0 #rect
Es0 f8 @|RichDialogProcessStartIcon #fIcon
Es0 f9 type ch.ivy.addon.portalkit.multiapp.settings.EmailSetting.EmailSettingData #txt
Es0 f9 446 182 20 20 13 0 #rect
Es0 f9 @|RichDialogProcessEndIcon #fIcon
Es0 f11 guid 14BDE9863B2922D3 #txt
Es0 f11 type ch.ivy.addon.portalkit.multiapp.settings.EmailSetting.EmailSettingData #txt
Es0 f11 actionDecl 'ch.ivy.addon.portalkit.multiapp.settings.EmailSetting.EmailSettingData out;
' #txt
Es0 f11 actionTable 'out=in;
' #txt
Es0 f11 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>save</name>
        <nameStyle>4,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Es0 f11 606 54 20 20 13 0 #rect
Es0 f11 @|RichDialogProcessStartIcon #fIcon
Es0 f12 type ch.ivy.addon.portalkit.multiapp.settings.EmailSetting.EmailSettingData #txt
Es0 f12 guid 14BDE9889E3889DC #txt
Es0 f12 606 310 20 20 13 0 #rect
Es0 f12 @|RichDialogEndIcon #fIcon
Es0 f14 type ch.ivy.addon.portalkit.multiapp.settings.EmailSetting.EmailSettingData #txt
Es0 f14 processCall MultiPortal/EmailSettingService:findAllEmailSettings(String) #txt
Es0 f14 doCall true #txt
Es0 f14 requestActionDecl '<java.lang.String user> param;
' #txt
Es0 f14 requestMappingAction 'param.user=in.user;
' #txt
Es0 f14 responseActionDecl 'ch.ivy.addon.portalkit.multiapp.settings.EmailSetting.EmailSettingData out;
' #txt
Es0 f14 responseMappingAction 'out=in;
out.errors=result.errors;
out.remoteEmailSettings=result.emailSettings;
' #txt
Es0 f14 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>findAllEmailSettings(String)</name>
        <nameStyle>28,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Es0 f14 166 116 36 24 20 -2 #rect
Es0 f14 @|CallSubIcon #fIcon
Es0 f16 actionDecl 'ch.ivy.addon.portalkit.multiapp.settings.EmailSetting.EmailSettingData out;
' #txt
Es0 f16 actionTable 'out=in;
' #txt
Es0 f16 actionCode 'import org.primefaces.context.RequestContext;
import ch.ivyteam.util.date.Weekday;
import ch.ivy.addon.portalkit.bo.RemoteEmailSetting;
import ch.ivy.ws.addon.IvyEmailSetting;
import ch.ivy.addon.portalkit.enums.RemoteWeekDay;


if (!in.remoteEmailSettings.isEmpty()) {
	in.generalEmailSetting = in.remoteEmailSettings.get(0);
}

in.dailySummaryList = RemoteWeekDay.values();
' #txt
Es0 f16 type ch.ivy.addon.portalkit.multiapp.settings.EmailSetting.EmailSettingData #txt
Es0 f16 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>check for setting</name>
        <nameStyle>17,7
</nameStyle>
        <desc>Check for display general setting or sepatated setting.</desc>
    </language>
</elementInfo>
' #txt
Es0 f16 166 180 36 24 20 -2 #rect
Es0 f16 @|StepIcon #fIcon
Es0 f17 expr out #txt
Es0 f17 184 140 184 180 #arcP
Es0 f6 actionDecl 'ch.ivy.addon.portalkit.multiapp.settings.EmailSetting.EmailSettingData out;
' #txt
Es0 f6 actionTable 'out=in;
' #txt
Es0 f6 actionCode 'import ch.ivy.addon.portalkit.bo.RemoteEmailSetting;
in.generalEmailSetting = new RemoteEmailSetting();' #txt
Es0 f6 type ch.ivy.addon.portalkit.multiapp.settings.EmailSetting.EmailSettingData #txt
Es0 f6 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>set general setting</name>
        <nameStyle>19,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Es0 f6 438 116 36 24 20 -2 #rect
Es0 f6 @|StepIcon #fIcon
Es0 f7 expr out #txt
Es0 f7 456 74 456 116 #arcP
Es0 f10 expr out #txt
Es0 f10 456 140 456 182 #arcP
Es0 f18 type ch.ivy.addon.portalkit.multiapp.settings.EmailSetting.EmailSettingData #txt
Es0 f18 processCall MultiPortal/EmailSettingService:saveAllEmailSettings(String,List<ch.ivy.addon.portalkit.bo.RemoteEmailSetting>) #txt
Es0 f18 doCall true #txt
Es0 f18 requestActionDecl '<java.lang.String user,List<ch.ivy.addon.portalkit.bo.RemoteEmailSetting> emailSettingMaps> param;
' #txt
Es0 f18 requestMappingAction 'param.user=in.user;
param.emailSettingMaps=in.remoteEmailSettings;
' #txt
Es0 f18 responseActionDecl 'ch.ivy.addon.portalkit.multiapp.settings.EmailSetting.EmailSettingData out;
' #txt
Es0 f18 responseMappingAction 'out=in;
' #txt
Es0 f18 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>saveAllEmailSettings</name>
        <nameStyle>20,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Es0 f18 598 180 36 24 20 -2 #rect
Es0 f18 @|CallSubIcon #fIcon
Es0 f20 actionDecl 'ch.ivy.addon.portalkit.multiapp.settings.EmailSetting.EmailSettingData out;
' #txt
Es0 f20 actionTable 'out=in;
' #txt
Es0 f20 actionCode 'import ch.ivy.addon.portalkit.util.EmailSettingUtils;

if (in.settingForAllApp) {
	EmailSettingUtils.updateRemoteEmailSettings(in.remoteEmailSettings, in.generalEmailSetting);
}' #txt
Es0 f20 type ch.ivy.addon.portalkit.multiapp.settings.EmailSetting.EmailSettingData #txt
Es0 f20 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Set data</name>
        <nameStyle>8,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Es0 f20 598 116 36 24 20 -2 #rect
Es0 f20 @|StepIcon #fIcon
Es0 f21 expr out #txt
Es0 f21 616 74 616 116 #arcP
Es0 f19 expr out #txt
Es0 f19 616 140 616 180 #arcP
Es0 f24 type ch.ivy.addon.portalkit.multiapp.settings.EmailSetting.EmailSettingData #txt
Es0 f24 62 118 20 20 13 0 #rect
Es0 f24 @|RichDialogProcessEndIcon #fIcon
Es0 f25 expr out #txt
Es0 f25 72 74 72 118 #arcP
Es0 f1 type ch.ivy.addon.portalkit.multiapp.settings.EmailSetting.EmailSettingData #txt
Es0 f1 174 310 20 20 13 0 #rect
Es0 f1 @|RichDialogProcessEndIcon #fIcon
Es0 f15 actionDecl 'ch.ivy.addon.portalkit.multiapp.settings.EmailSetting.EmailSettingData out;
' #txt
Es0 f15 actionTable 'out=in;
' #txt
Es0 f15 actionCode 'import ch.ivy.addon.portalkit.bo.RemoteEmailSetting;
import org.primefaces.context.RequestContext;
import javax.faces.context.FacesContext;
import ch.ivy.ws.addon.WsException;
import javax.faces.application.FacesMessage;


//Create error message
if(in.errors.size()>0){
	in.errorLink = ivy.cms.co("/ch.ivy.addon.portalkit.ui.jsf/common/found") + in.errors.size() + ivy.cms.co("/ch.ivy.addon.portalkit.ui.jsf/common/clickToShowDetail");
	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, ivy.cms.co("/ch.ivy.addon.portalkit.ui.jsf/common/errorNotification"), "<span style=\"cursor:pointer\"><a onclick=\"PF(''errorDialogEmail'').show(); hideGrowl()\">"+in.errorLink+"</a></span>"));
	}

RequestContext request = RequestContext.getCurrentInstance(); 

if (in.remoteEmailSettings.size() == 0) {
	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, null, ivy.cms.co("/ch.ivy.addon.portalkit.ui.jsf/emailSetting/noSettingMsg")));
  request.addCallbackParam("settingEmpty", true);
} else {
	request.addCallbackParam("settingEmpty", false);
}

request.addCallbackParam("settingForAllApp", in.settingForAllApp);

if(in.#generalEmailSetting == null){
	in.generalEmailSetting = new RemoteEmailSetting();
}	' #txt
Es0 f15 type ch.ivy.addon.portalkit.multiapp.settings.EmailSetting.EmailSettingData #txt
Es0 f15 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>error handler</name>
        <nameStyle>13,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Es0 f15 166 244 36 24 20 -2 #rect
Es0 f15 @|StepIcon #fIcon
Es0 f26 expr out #txt
Es0 f26 184 204 184 244 #arcP
Es0 f2 expr out #txt
Es0 f2 184 268 184 310 #arcP
Es0 f27 actionDecl 'ch.ivy.addon.portalkit.multiapp.settings.EmailSetting.EmailSettingData out;
' #txt
Es0 f27 actionTable 'out=in;
' #txt
Es0 f27 actionCode 'import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

//Create error message
if(in.errors.size()>0){
	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, ivy.cms.co("/ch.ivy.addon.portalkit.ui.jsf/common/note"), ivy.cms.co("/ch.ivy.addon.portalkit.ui.jsf/emailSetting/errorEmailSavingWS")));
	FacesContext.getCurrentInstance().validationFailed();
}' #txt
Es0 f27 type ch.ivy.addon.portalkit.multiapp.settings.EmailSetting.EmailSettingData #txt
Es0 f27 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>error handler</name>
        <nameStyle>13,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Es0 f27 598 244 36 24 20 -2 #rect
Es0 f27 @|StepIcon #fIcon
Es0 f28 expr out #txt
Es0 f28 616 204 616 244 #arcP
Es0 f13 expr out #txt
Es0 f13 616 268 616 310 #arcP
Es0 f29 guid 14D513665ECB5173 #txt
Es0 f29 type ch.ivy.addon.portalkit.multiapp.settings.EmailSetting.EmailSettingData #txt
Es0 f29 actionDecl 'ch.ivy.addon.portalkit.multiapp.settings.EmailSetting.EmailSettingData out;
' #txt
Es0 f29 actionTable 'out=in;
' #txt
Es0 f29 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>updateGrowl</name>
        <nameStyle>11,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Es0 f29 774 342 20 20 13 0 #rect
Es0 f29 @|RichDialogProcessStartIcon #fIcon
Es0 f30 type ch.ivy.addon.portalkit.multiapp.settings.EmailSetting.EmailSettingData #txt
Es0 f30 774 534 20 20 13 0 #rect
Es0 f30 @|RichDialogProcessEndIcon #fIcon
Es0 f31 actionDecl 'ch.ivy.addon.portalkit.multiapp.settings.EmailSetting.EmailSettingData out;
' #txt
Es0 f31 actionTable 'out=in;
' #txt
Es0 f31 actionCode 'import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,ivy.cms.co("/ch.ivy.addon.portalkit.ui.jsf/common/information") , ivy.cms.co("/ch.ivy.addon.portalkit.ui.jsf/common/saveSuccessfully")));' #txt
Es0 f31 type ch.ivy.addon.portalkit.multiapp.settings.EmailSetting.EmailSettingData #txt
Es0 f31 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>add msg</name>
        <nameStyle>7,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Es0 f31 766 436 36 24 24 -5 #rect
Es0 f31 @|StepIcon #fIcon
Es0 f32 expr out #txt
Es0 f32 784 362 784 436 #arcP
Es0 f33 expr out #txt
Es0 f33 784 460 784 534 #arcP
Es0 f34 guid 015F6C3CD75AFF3C #txt
Es0 f34 type ch.ivy.addon.portalkit.multiapp.settings.EmailSetting.EmailSettingData #txt
Es0 f34 method startEmailSetting() #txt
Es0 f34 disableUIEvents false #txt
Es0 f34 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<> param = methodEvent.getInputArguments();
' #txt
Es0 f34 outParameterDecl '<> result;
' #txt
Es0 f34 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>startEmailSetting()</name>
    </language>
</elementInfo>
' #txt
Es0 f34 171 51 26 26 -51 15 #rect
Es0 f34 @|RichDialogMethodStartIcon #fIcon
Es0 f22 expr out #txt
Es0 f22 184 77 184 116 #arcP
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
Es0 f14 mainOut f17 tail #connect
Es0 f17 head f16 mainIn #connect
Es0 f8 mainOut f7 tail #connect
Es0 f7 head f6 mainIn #connect
Es0 f6 mainOut f10 tail #connect
Es0 f10 head f9 mainIn #connect
Es0 f11 mainOut f21 tail #connect
Es0 f21 head f20 mainIn #connect
Es0 f20 mainOut f19 tail #connect
Es0 f19 head f18 mainIn #connect
Es0 f0 mainOut f25 tail #connect
Es0 f25 head f24 mainIn #connect
Es0 f16 mainOut f26 tail #connect
Es0 f26 head f15 mainIn #connect
Es0 f15 mainOut f2 tail #connect
Es0 f2 head f1 mainIn #connect
Es0 f18 mainOut f28 tail #connect
Es0 f28 head f27 mainIn #connect
Es0 f27 mainOut f13 tail #connect
Es0 f13 head f12 mainIn #connect
Es0 f29 mainOut f32 tail #connect
Es0 f32 head f31 mainIn #connect
Es0 f31 mainOut f33 tail #connect
Es0 f33 head f30 mainIn #connect
Es0 f34 mainOut f22 tail #connect
Es0 f22 head f14 mainIn #connect
