[Ivy]
14C2C99A2B54EEF3 3.23 #module
>Proto >Proto Collection #zClass
Es0 CentralLanguageProcess Big #zClass
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
Es0 @RichDialogProcessEnd f1 '' #zField
Es0 @RichDialogProcessStart f7 '' #zField
Es0 @RichDialogProcessEnd f9 '' #zField
Es0 @CallSub f11 '' #zField
Es0 @RichDialogProcessEnd f14 '' #zField
Es0 @RichDialogProcessStart f15 '' #zField
Es0 @RichDialogProcessEnd f19 '' #zField
Es0 @CallSub f21 '' #zField
Es0 @PushWFArc f6 '' #zField
Es0 @GridStep f2 '' #zField
Es0 @PushWFArc f4 '' #zField
Es0 @GridStep f5 '' #zField
Es0 @PushWFArc f10 '' #zField
Es0 @PushWFArc f24 '' #zField
Es0 @PushWFArc f3 '' #zField
Es0 @GridStep f27 '' #zField
Es0 @PushWFArc f28 '' #zField
Es0 @PushWFArc f23 '' #zField
Es0 @RichDialogMethodStart f29 '' #zField
Es0 @PushWFArc f20 '' #zField
Es0 @PushWFArc f8 '' #zField
>Proto Es0 Es0 CentralLanguageProcess #zField
Es0 f0 guid 14BD8AF4A523241B #txt
Es0 f0 type ch.ivy.addon.portalkit.multiapp.settings.CentralLanguage.CentralLanguageData #txt
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
Es0 f1 type ch.ivy.addon.portalkit.multiapp.settings.CentralLanguage.CentralLanguageData #txt
Es0 f1 62 198 20 20 13 0 #rect
Es0 f1 @|RichDialogProcessEndIcon #fIcon
Es0 f7 guid 14BED4F08A38964A #txt
Es0 f7 type ch.ivy.addon.portalkit.multiapp.settings.CentralLanguage.CentralLanguageData #txt
Es0 f7 actionDecl 'ch.ivy.addon.portalkit.multiapp.settings.CentralLanguage.CentralLanguageData out;
' #txt
Es0 f7 actionTable 'out=in;
' #txt
Es0 f7 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>save</name>
        <nameStyle>4,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Es0 f7 214 54 20 20 13 -8 #rect
Es0 f7 @|RichDialogProcessStartIcon #fIcon
Es0 f9 type ch.ivy.addon.portalkit.multiapp.settings.CentralLanguage.CentralLanguageData #txt
Es0 f9 214 270 20 20 13 0 #rect
Es0 f9 @|RichDialogProcessEndIcon #fIcon
Es0 f11 type ch.ivy.addon.portalkit.multiapp.settings.CentralLanguage.CentralLanguageData #txt
Es0 f11 processCall MultiPortal/LanguageSettingService:saveAllEmailSettings(List<ch.ivy.addon.portalkit.bo.RemoteLanguageSetting>,String) #txt
Es0 f11 doCall true #txt
Es0 f11 requestActionDecl '<List<ch.ivy.addon.portalkit.bo.RemoteLanguageSetting> remoteLanguageSettings,java.lang.String user> param;
' #txt
Es0 f11 requestMappingAction 'param.remoteLanguageSettings=in.remoteLanguageSettings;
param.user=in.user;
' #txt
Es0 f11 responseActionDecl 'ch.ivy.addon.portalkit.multiapp.settings.CentralLanguage.CentralLanguageData out;
' #txt
Es0 f11 responseMappingAction 'out=in;
out.errors=result.errors;
' #txt
Es0 f11 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>saveAllEmailSettings(List&lt;RemoteLanguageSetting&gt;,String)</name>
        <nameStyle>56,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Es0 f11 206 124 36 24 20 -5 #rect
Es0 f11 @|CallSubIcon #fIcon
Es0 f14 type ch.ivy.addon.portalkit.multiapp.settings.CentralLanguage.CentralLanguageData #txt
Es0 f14 606 198 20 20 13 0 #rect
Es0 f14 @|RichDialogProcessEndIcon #fIcon
Es0 f15 guid 14BED5E4A4A44F76 #txt
Es0 f15 type ch.ivy.addon.portalkit.multiapp.settings.CentralLanguage.CentralLanguageData #txt
Es0 f15 actionDecl 'ch.ivy.addon.portalkit.multiapp.settings.CentralLanguage.CentralLanguageData out;
' #txt
Es0 f15 actionTable 'out=in;
' #txt
Es0 f15 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>cancel</name>
        <nameStyle>6,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Es0 f15 606 62 20 20 13 -8 #rect
Es0 f15 @|RichDialogProcessStartIcon #fIcon
Es0 f19 type ch.ivy.addon.portalkit.multiapp.settings.CentralLanguage.CentralLanguageData #txt
Es0 f19 214 534 20 20 13 0 #rect
Es0 f19 @|RichDialogProcessEndIcon #fIcon
Es0 f21 type ch.ivy.addon.portalkit.multiapp.settings.CentralLanguage.CentralLanguageData #txt
Es0 f21 processCall MultiPortal/LanguageSettingService:findAllLanguageSettings(List<String>,String) #txt
Es0 f21 doCall true #txt
Es0 f21 requestActionDecl '<java.util.List<java.lang.String> appNames,java.lang.String user> param;
' #txt
Es0 f21 requestMappingAction 'param.user=in.user;
' #txt
Es0 f21 responseActionDecl 'ch.ivy.addon.portalkit.multiapp.settings.CentralLanguage.CentralLanguageData out;
' #txt
Es0 f21 responseMappingAction 'out=in;
out.errors=result.errors;
out.remoteLanguageSettings=result.languageSettings;
' #txt
Es0 f21 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>findAllLanguageSettings(List&lt;String&gt;,String)</name>
        <nameStyle>44,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Es0 f21 206 396 36 24 20 -6 #rect
Es0 f21 @|CallSubIcon #fIcon
Es0 f6 expr out #txt
Es0 f6 72 74 72 198 #arcP
Es0 f2 actionDecl 'ch.ivy.addon.portalkit.multiapp.settings.CentralLanguage.CentralLanguageData out;
' #txt
Es0 f2 actionTable 'out=in;
' #txt
Es0 f2 actionCode 'import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

//Create error message
if(in.errors.size()>0){
	in.errorLink = ivy.cms.co("/ch.ivy.addon.portalkit.ui.jsf/common/found") + in.errors.size() + ivy.cms.co("/ch.ivy.addon.portalkit.ui.jsf/common/clickToShowDetail");
	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, ivy.cms.co("/ch.ivy.addon.portalkit.ui.jsf/common/errorNotification"), "<span style=\"cursor:pointer\"><a onclick=\"PF(''errorLanguageDialog'').show(); \">"+in.errorLink+"</a></span>"));
	FacesContext.getCurrentInstance().validationFailed();
}' #txt
Es0 f2 type ch.ivy.addon.portalkit.multiapp.settings.CentralLanguage.CentralLanguageData #txt
Es0 f2 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Create error messages</name>
        <nameStyle>21,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Es0 f2 206 469 36 22 22 -7 #rect
Es0 f2 @|StepIcon #fIcon
Es0 f4 expr out #txt
Es0 f4 224 491 224 534 #arcP
Es0 f5 actionDecl 'ch.ivy.addon.portalkit.multiapp.settings.CentralLanguage.CentralLanguageData out;
' #txt
Es0 f5 actionTable 'out=in;
' #txt
Es0 f5 actionCode 'import org.primefaces.context.RequestContext;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

//Create error message
RequestContext.getCurrentInstance().addCallbackParam("mailLanguageNotFound",false);
if(in.errors.size()>0){
	if (in.errors.size() == 1 && in.errors.get(0).getErrorCode() == 10048){
		RequestContext.getCurrentInstance().addCallbackParam("mailLanguageNotFound",true);
	} else {
		FacesContext.getCurrentInstance().validationFailed();
	}
}' #txt
Es0 f5 type ch.ivy.addon.portalkit.multiapp.settings.CentralLanguage.CentralLanguageData #txt
Es0 f5 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Create error messages</name>
        <nameStyle>21,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Es0 f5 206 220 36 24 22 -7 #rect
Es0 f5 @|StepIcon #fIcon
Es0 f10 expr out #txt
Es0 f10 224 244 224 270 #arcP
Es0 f24 expr out #txt
Es0 f24 224 74 224 124 #arcP
Es0 f3 expr out #txt
Es0 f3 224 420 224 469 #arcP
Es0 f27 actionDecl 'ch.ivy.addon.portalkit.multiapp.settings.CentralLanguage.CentralLanguageData out;
' #txt
Es0 f27 actionTable 'out=in;
' #txt
Es0 f27 actionCode 'import ch.ivy.addon.portalkit.util.UserUtils;
UserUtils.setLanguague();' #txt
Es0 f27 type ch.ivy.addon.portalkit.multiapp.settings.CentralLanguage.CentralLanguageData #txt
Es0 f27 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Set locale</name>
        <nameStyle>10,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Es0 f27 206 172 36 24 20 -2 #rect
Es0 f27 @|StepIcon #fIcon
Es0 f28 expr out #txt
Es0 f28 224 148 224 172 #arcP
Es0 f23 expr out #txt
Es0 f23 224 196 224 220 #arcP
Es0 f29 guid 15F6C3F5914708A5 #txt
Es0 f29 type ch.ivy.addon.portalkit.multiapp.settings.CentralLanguage.CentralLanguageData #txt
Es0 f29 method loadLanguageSetting() #txt
Es0 f29 disableUIEvents false #txt
Es0 f29 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<> param = methodEvent.getInputArguments();
' #txt
Es0 f29 outParameterDecl '<> result;
' #txt
Es0 f29 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>loadLanguageSetting()</name>
    </language>
</elementInfo>
' #txt
Es0 f29 211 339 26 26 -63 15 #rect
Es0 f29 @|RichDialogMethodStartIcon #fIcon
Es0 f20 expr out #txt
Es0 f20 224 365 224 396 #arcP
Es0 f8 expr out #txt
Es0 f8 616 82 616 198 #arcP
>Proto Es0 .type ch.ivy.addon.portalkit.multiapp.settings.CentralLanguage.CentralLanguageData #txt
>Proto Es0 .processKind HTML_DIALOG #txt
>Proto Es0 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <swimlaneLabel>Start Method</swimlaneLabel>
        <swimlaneLabel>Events</swimlaneLabel>
        <swimlaneLabel></swimlaneLabel>
    </language>
    <swimlaneSize>160</swimlaneSize>
    <swimlaneSize>632</swimlaneSize>
    <swimlaneColor>-1</swimlaneColor>
    <swimlaneColor>-1</swimlaneColor>
</elementInfo>
' #txt
>Proto Es0 -8 -8 16 16 16 26 #rect
>Proto Es0 '' #fIcon
Es0 f0 mainOut f6 tail #connect
Es0 f6 head f1 mainIn #connect
Es0 f2 mainOut f4 tail #connect
Es0 f4 head f19 mainIn #connect
Es0 f5 mainOut f10 tail #connect
Es0 f10 head f9 mainIn #connect
Es0 f7 mainOut f24 tail #connect
Es0 f24 head f11 mainIn #connect
Es0 f21 mainOut f3 tail #connect
Es0 f3 head f2 mainIn #connect
Es0 f11 mainOut f28 tail #connect
Es0 f28 head f27 mainIn #connect
Es0 f27 mainOut f23 tail #connect
Es0 f23 head f5 mainIn #connect
Es0 f29 mainOut f20 tail #connect
Es0 f20 head f21 mainIn #connect
Es0 f15 mainOut f8 tail #connect
Es0 f8 head f14 mainIn #connect
