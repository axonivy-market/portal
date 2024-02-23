[Ivy]
14C2C99A2B54EEF3 7.5.0 #module
>Proto >Proto Collection #zClass
Es0 CentralLanguageProcess Big #zClass
Es0 RD #cInfo
Es0 #process
Es0 @TextInP .type .type #zField
Es0 @TextInP .processKind .processKind #zField
Es0 @AnnotationInP-0n ai ai #zField
Es0 @TextInP .xml .xml #zField
Es0 @TextInP .responsibility .responsibility #zField
Es0 @UdInit f0 '' #zField
Es0 @UdProcessEnd f1 '' #zField
Es0 @PushWFArc f3 '' #zField
Es0 @UdProcessEnd f6 '' #zField
Es0 @CallSub f8 '' #zField
Es0 @GridStep f13 '' #zField
Es0 @PushWFArc f14 '' #zField
Es0 @GridStep f15 '' #zField
Es0 @PushWFArc f16 '' #zField
Es0 @UdMethod f2 '' #zField
Es0 @UdMethod f5 '' #zField
Es0 @UdProcessEnd f7 '' #zField
Es0 @CallSub f10 '' #zField
Es0 @PushWFArc f11 '' #zField
Es0 @GridStep f18 '' #zField
Es0 @PushWFArc f20 '' #zField
Es0 @GridStep f12 '' #zField
Es0 @PushWFArc f9 '' #zField
Es0 @Alternative f21 '' #zField
Es0 @PushWFArc f22 '' #zField
Es0 @PushWFArc f23 '' #zField
Es0 @PushWFArc f19 '' #zField
Es0 @GridStep f24 '' #zField
Es0 @PushWFArc f4 '' #zField
Es0 @PushWFArc f25 '' #zField
Es0 @PushWFArc f17 '' #zField
>Proto Es0 Es0 CentralLanguageProcess #zField
Es0 f0 guid 16781C1477C10928 #txt
Es0 f0 method start() #txt
Es0 f0 inParameterDecl '<> param;' #txt
Es0 f0 outParameterDecl '<> result;' #txt
Es0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start()</name>
    </language>
</elementInfo>
' #txt
Es0 f0 83 51 26 26 -16 15 #rect
Es0 f0 @|UdInitIcon #fIcon
Es0 f1 275 51 26 26 0 12 #rect
Es0 f1 @|UdProcessEndIcon #fIcon
Es0 f3 expr out #txt
Es0 f3 109 64 275 64 #arcP
Es0 f6 827 147 26 26 0 12 #rect
Es0 f6 @|UdProcessEndIcon #fIcon
Es0 f8 processCall 'Ivy Data Processes/LanguageService:findUserLanguages(String)' #txt
Es0 f8 requestActionDecl '<String username> param;' #txt
Es0 f8 requestMappingAction 'param.username=ivy.session.getSessionUserName();
' #txt
Es0 f8 responseActionDecl 'ch.ivy.addon.portalkit.multiapp.settings.CentralLanguage.CentralLanguageData out;
' #txt
Es0 f8 responseMappingAction 'out=in;
out.errors=result.errors;
out.languages=result.languages;
' #txt
Es0 f8 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>LanguageService</name>
    </language>
</elementInfo>
' #txt
Es0 f8 176 138 112 44 -48 -8 #rect
Es0 f8 @|CallSubIcon #fIcon
Es0 f13 actionTable 'out=in;
' #txt
Es0 f13 actionCode 'import javax.faces.context.FacesContext;
import ch.ivy.addon.portalkit.util.UserUtils;

UserUtils.setLanguage();' #txt
Es0 f13 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Set locale</name>
    </language>
</elementInfo>
' #txt
Es0 f13 328 138 112 44 -27 -8 #rect
Es0 f13 @|StepIcon #fIcon
Es0 f14 expr out #txt
Es0 f14 288 160 328 160 #arcP
Es0 f15 actionTable 'out=in;
' #txt
Es0 f15 actionCode 'import ch.ivy.addon.portalkit.util.BeanUtils;

BeanUtils.invokeBeanMethodViaMethodExpression("#{errorDisplayBean.displayErrors}", in.errors);' #txt
Es0 f15 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Display errors if have</name>
    </language>
</elementInfo>
' #txt
Es0 f15 472 138 128 44 -57 -8 #rect
Es0 f15 @|StepIcon #fIcon
Es0 f16 expr out #txt
Es0 f16 440 160 472 160 #arcP
Es0 f2 guid 16781CDB54BEA49E #txt
Es0 f2 method initLanguageData(Boolean) #txt
Es0 f2 inParameterDecl '<Boolean isWorkingOnATask> param;' #txt
Es0 f2 inParameterMapAction 'out.isWorkingOnATask=param.isWorkingOnATask;
' #txt
Es0 f2 outParameterDecl '<> result;' #txt
Es0 f2 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>initLanguageData(Boolean)</name>
    </language>
</elementInfo>
' #txt
Es0 f2 83 147 26 26 -59 15 #rect
Es0 f2 @|UdMethodIcon #fIcon
Es0 f5 guid 16786B6BAC43695F #txt
Es0 f5 method saveUserLanguages() #txt
Es0 f5 inParameterDecl '<> param;' #txt
Es0 f5 outParameterDecl '<> result;' #txt
Es0 f5 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>saveUserLanguages()</name>
    </language>
</elementInfo>
' #txt
Es0 f5 83 275 26 26 -62 15 #rect
Es0 f5 @|UdMethodIcon #fIcon
Es0 f7 723 275 26 26 0 12 #rect
Es0 f7 @|UdProcessEndIcon #fIcon
Es0 f10 processCall 'Ivy Data Processes/LanguageService:saveUserLanguages(String,List<ch.ivy.addon.portalkit.ivydata.bo.IvyLanguage>)' #txt
Es0 f10 requestActionDecl '<String username,java.util.List<ch.ivy.addon.portalkit.ivydata.bo.IvyLanguage> languages> param;' #txt
Es0 f10 requestMappingAction 'param.username=ivy.session.getSessionUserName();
param.languages=in.languages;
' #txt
Es0 f10 responseActionDecl 'ch.ivy.addon.portalkit.multiapp.settings.CentralLanguage.CentralLanguageData out;
' #txt
Es0 f10 responseMappingAction 'out=in;
out.errors=result.errors;
' #txt
Es0 f10 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>LanguageService</name>
    </language>
</elementInfo>
' #txt
Es0 f10 176 266 112 44 -48 -8 #rect
Es0 f10 @|CallSubIcon #fIcon
Es0 f11 expr out #txt
Es0 f11 109 288 176 288 #arcP
Es0 f18 actionTable 'out=in;
' #txt
Es0 f18 actionCode 'import ch.ivy.addon.portalkit.util.UserUtils;

UserUtils.setLanguage();' #txt
Es0 f18 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Set locale</name>
    </language>
</elementInfo>
' #txt
Es0 f18 328 266 112 44 -27 -8 #rect
Es0 f18 @|StepIcon #fIcon
Es0 f20 expr out #txt
Es0 f20 288 288 328 288 #arcP
Es0 f12 actionTable 'out=in;
' #txt
Es0 f12 actionCode 'import ch.ivy.addon.portalkit.util.BeanUtils;

BeanUtils.invokeBeanMethodViaMethodExpression("#{errorDisplayBean.displayErrors}", in.errors);' #txt
Es0 f12 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Display errors</name>
    </language>
</elementInfo>
' #txt
Es0 f12 544 362 112 44 -38 -8 #rect
Es0 f12 @|StepIcon #fIcon
Es0 f9 expr out #txt
Es0 f9 656 384 736 301 #arcP
Es0 f9 1 736 384 #addKink
Es0 f9 0 0.8254647321695485 0 0 #arcLabel
Es0 f21 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>successful?</name>
    </language>
</elementInfo>
' #txt
Es0 f21 464 272 32 32 -33 -36 #rect
Es0 f21 @|AlternativeIcon #fIcon
Es0 f22 expr out #txt
Es0 f22 440 288 464 288 #arcP
Es0 f23 expr in #txt
Es0 f23 outCond in.errors.isEmpty() #txt
Es0 f23 496 288 723 288 #arcP
Es0 f19 expr in #txt
Es0 f19 480 304 544 384 #arcP
Es0 f19 1 480 384 #addKink
Es0 f19 1 0.13338566089674114 0 0 #arcLabel
Es0 f24 actionTable 'out=in;
' #txt
Es0 f24 actionCode 'import com.axonivy.portal.components.util.FacesMessageUtils;
import org.primefaces.PrimeFaces;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

// If isWorkingOnATask is true, show a warning message to user
if (in.isWorkingOnATask) {
	String messageId = "language-settings-form:language-settings:change-language-warning-message";
	
	FacesContext.getCurrentInstance().addMessage(messageId, FacesMessageUtils.sanitizedMessage(FacesMessage.SEVERITY_WARN, 
		ivy.cms.co("/ch.ivy.addon.portalkit.ui.jsf/languageSetting/warningMessageForChangeLanguage"), null));
}' #txt
Es0 f24 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Build warning message</name>
    </language>
</elementInfo>
' #txt
Es0 f24 648 138 144 44 -65 -8 #rect
Es0 f24 @|StepIcon #fIcon
Es0 f4 109 160 176 160 #arcP
Es0 f25 expr out #txt
Es0 f25 600 160 648 160 #arcP
Es0 f17 792 160 827 160 #arcP
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
Es0 f0 mainOut f3 tail #connect
Es0 f3 head f1 mainIn #connect
Es0 f8 mainOut f14 tail #connect
Es0 f14 head f13 mainIn #connect
Es0 f13 mainOut f16 tail #connect
Es0 f16 head f15 mainIn #connect
Es0 f5 mainOut f11 tail #connect
Es0 f11 head f10 mainIn #connect
Es0 f10 mainOut f20 tail #connect
Es0 f20 head f18 mainIn #connect
Es0 f12 mainOut f9 tail #connect
Es0 f9 head f7 mainIn #connect
Es0 f18 mainOut f22 tail #connect
Es0 f22 head f21 in #connect
Es0 f21 out f23 tail #connect
Es0 f23 head f7 mainIn #connect
Es0 f21 out f19 tail #connect
Es0 f19 head f12 mainIn #connect
Es0 f2 mainOut f4 tail #connect
Es0 f4 head f8 mainIn #connect
Es0 f15 mainOut f25 tail #connect
Es0 f25 head f24 mainIn #connect
Es0 f24 mainOut f17 tail #connect
Es0 f17 head f6 mainIn #connect
