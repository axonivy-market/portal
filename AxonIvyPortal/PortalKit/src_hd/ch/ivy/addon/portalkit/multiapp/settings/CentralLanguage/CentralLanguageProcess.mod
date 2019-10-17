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
Es0 @PushWFArc f17 '' #zField
Es0 @UdMethod f2 '' #zField
Es0 @PushWFArc f4 '' #zField
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
Es0 f6 627 147 26 26 0 12 #rect
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
Es0 f8 168 138 112 44 -48 -8 #rect
Es0 f8 @|CallSubIcon #fIcon
Es0 f13 actionTable 'out=in;
' #txt
Es0 f13 actionCode 'import javax.faces.context.FacesContext;
import ch.ivy.addon.portalkit.util.UserUtils;

UserUtils.setLanguague();' #txt
Es0 f13 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Set locale</name>
    </language>
</elementInfo>
' #txt
Es0 f13 320 138 112 44 -27 -8 #rect
Es0 f13 @|StepIcon #fIcon
Es0 f14 expr out #txt
Es0 f14 280 160 320 160 #arcP
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
Es0 f15 464 138 128 44 -57 -8 #rect
Es0 f15 @|StepIcon #fIcon
Es0 f16 expr out #txt
Es0 f16 432 160 464 160 #arcP
Es0 f17 expr out #txt
Es0 f17 592 160 627 160 #arcP
Es0 f2 guid 16781CDB54BEA49E #txt
Es0 f2 method findUserLanguages() #txt
Es0 f2 inParameterDecl '<> param;' #txt
Es0 f2 outParameterDecl '<> result;' #txt
Es0 f2 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>findUserLanguages()</name>
    </language>
</elementInfo>
' #txt
Es0 f2 83 147 26 26 -59 15 #rect
Es0 f2 @|UdMethodIcon #fIcon
Es0 f4 expr out #txt
Es0 f4 109 160 168 160 #arcP
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
Es0 f5 83 243 26 26 -62 15 #rect
Es0 f5 @|UdMethodIcon #fIcon
Es0 f7 723 243 26 26 0 12 #rect
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
Es0 f10 176 234 112 44 -48 -8 #rect
Es0 f10 @|CallSubIcon #fIcon
Es0 f11 expr out #txt
Es0 f11 109 256 176 256 #arcP
Es0 f18 actionTable 'out=in;
' #txt
Es0 f18 actionCode 'import ch.ivy.addon.portalkit.util.UserUtils;

UserUtils.setLanguague();' #txt
Es0 f18 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Set locale</name>
    </language>
</elementInfo>
' #txt
Es0 f18 328 234 112 44 -27 -8 #rect
Es0 f18 @|StepIcon #fIcon
Es0 f20 expr out #txt
Es0 f20 288 256 328 256 #arcP
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
Es0 f12 544 330 112 44 -38 -8 #rect
Es0 f12 @|StepIcon #fIcon
Es0 f9 expr out #txt
Es0 f9 656 352 736 269 #arcP
Es0 f9 1 736 352 #addKink
Es0 f9 0 0.8254647321695485 0 0 #arcLabel
Es0 f21 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>successful?</name>
    </language>
</elementInfo>
' #txt
Es0 f21 464 240 32 32 -33 -36 #rect
Es0 f21 @|AlternativeIcon #fIcon
Es0 f22 expr out #txt
Es0 f22 440 256 464 256 #arcP
Es0 f23 expr in #txt
Es0 f23 outCond in.errors.isEmpty() #txt
Es0 f23 496 256 723 256 #arcP
Es0 f19 expr in #txt
Es0 f19 480 272 544 352 #arcP
Es0 f19 1 480 352 #addKink
Es0 f19 1 0.13338566089674114 0 0 #arcLabel
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
Es0 f15 mainOut f17 tail #connect
Es0 f17 head f6 mainIn #connect
Es0 f2 mainOut f4 tail #connect
Es0 f4 head f8 mainIn #connect
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
