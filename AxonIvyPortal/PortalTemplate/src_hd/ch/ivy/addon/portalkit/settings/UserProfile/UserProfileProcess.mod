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
Us0 @UdInit f0 '' #zField
Us0 @UdProcessEnd f1 '' #zField
Us0 @PushWFArc f2 '' #zField
Us0 @UdEvent f3 '' #zField
Us0 @UdExitEnd f4 '' #zField
Us0 @PushWFArc f5 '' #zField
Us0 @UdMethod f6 '' #zField
Us0 @UdProcessEnd f15 '' #zField
Us0 @UdProcessEnd f8 '' #zField
Us0 @GridStep f9 '' #zField
Us0 @GridStep f10 '' #zField
Us0 @CallSub f11 '' #zField
Us0 @UdMethod f12 '' #zField
Us0 @PushWFArc f17 '' #zField
Us0 @PushWFArc f24 '' #zField
Us0 @PushWFArc f25 '' #zField
Us0 @PushWFArc f26 '' #zField
Us0 @GridStep f7 '' #zField
Us0 @PushWFArc f13 '' #zField
Us0 @PushWFArc f14 '' #zField
Us0 @Alternative f21 '' #zField
Us0 @UdProcessEnd f16 '' #zField
Us0 @CallSub f18 '' #zField
Us0 @UdMethod f19 '' #zField
Us0 @GridStep f20 '' #zField
Us0 @GridStep f22 '' #zField
Us0 @PushWFArc f23 '' #zField
Us0 @PushWFArc f27 '' #zField
Us0 @PushWFArc f28 '' #zField
Us0 @PushWFArc f29 '' #zField
Us0 @PushWFArc f30 '' #zField
Us0 @PushWFArc f31 '' #zField
>Proto Us0 Us0 UserProfileProcess #zField
Us0 f0 guid 172546E1FE4FCAB2 #txt
Us0 f0 method start() #txt
Us0 f0 inParameterDecl '<> param;' #txt
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
Us0 f1 211 51 26 26 0 12 #rect
Us0 f1 @|UdProcessEndIcon #fIcon
Us0 f2 109 64 211 64 #arcP
Us0 f3 guid 172546E1FEBF2567 #txt
Us0 f3 actionTable 'out=in;
' #txt
Us0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>close</name>
    </language>
</elementInfo>
' #txt
Us0 f3 83 147 26 26 -15 15 #rect
Us0 f3 @|UdEventIcon #fIcon
Us0 f4 211 147 26 26 0 12 #rect
Us0 f4 @|UdExitEndIcon #fIcon
Us0 f5 109 160 211 160 #arcP
Us0 f6 guid 1725540CDDEA5C0D #txt
Us0 f6 method save() #txt
Us0 f6 inParameterDecl '<> param;' #txt
Us0 f6 outParameterDecl '<> result;' #txt
Us0 f6 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>save()</name>
    </language>
</elementInfo>
' #txt
Us0 f6 83 243 26 26 -25 15 #rect
Us0 f6 @|UdMethodIcon #fIcon
Us0 f15 715 243 26 26 0 12 #rect
Us0 f15 @|UdProcessEndIcon #fIcon
Us0 f8 691 315 26 26 0 12 #rect
Us0 f8 @|UdProcessEndIcon #fIcon
Us0 f9 actionTable 'out=in;
' #txt
Us0 f9 actionCode 'import javax.faces.context.FacesContext;
import ch.ivy.addon.portalkit.util.UserUtils;

UserUtils.setLanguague();' #txt
Us0 f9 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Set locale</name>
    </language>
</elementInfo>
' #txt
Us0 f9 328 306 112 44 -27 -8 #rect
Us0 f9 @|StepIcon #fIcon
Us0 f10 actionTable 'out=in;
' #txt
Us0 f10 actionCode 'import ch.ivy.addon.portalkit.util.BeanUtils;

BeanUtils.invokeBeanMethodViaMethodExpression("#{errorDisplayBean.displayErrors}", in.errors);' #txt
Us0 f10 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Display errors if have</name>
    </language>
</elementInfo>
' #txt
Us0 f10 480 306 128 44 -57 -8 #rect
Us0 f10 @|StepIcon #fIcon
Us0 f11 processCall 'Ivy Data Processes/LanguageService:findUserLanguages(String)' #txt
Us0 f11 requestActionDecl '<String username> param;' #txt
Us0 f11 requestMappingAction 'param.username=ivy.session.getSessionUserName();
' #txt
Us0 f11 responseActionDecl 'ch.ivy.addon.portalkit.multiapp.settings.CentralLanguage.CentralLanguageData out;
' #txt
Us0 f11 responseMappingAction 'out=in;
out.errors=result.errors;
out.language=result.language;
' #txt
Us0 f11 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>LanguageService</name>
    </language>
</elementInfo>
' #txt
Us0 f11 176 306 112 44 -48 -8 #rect
Us0 f11 @|CallSubIcon #fIcon
Us0 f12 guid 17255599F044D2FD #txt
Us0 f12 method initLanguageData() #txt
Us0 f12 inParameterDecl '<> param;' #txt
Us0 f12 outParameterDecl '<> result;' #txt
Us0 f12 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>initLanguageData()</name>
    </language>
</elementInfo>
' #txt
Us0 f12 83 315 26 26 -59 15 #rect
Us0 f12 @|UdMethodIcon #fIcon
Us0 f17 608 328 691 328 #arcP
Us0 f24 expr out #txt
Us0 f24 440 328 480 328 #arcP
Us0 f25 expr out #txt
Us0 f25 288 328 328 328 #arcP
Us0 f26 109 328 176 328 #arcP
Us0 f7 actionTable 'out=in;
' #txt
Us0 f7 actionCode 'import java.util.Arrays;
import ch.ivy.addon.portalkit.bean.IvyComponentLogicCaller;
IvyComponentLogicCaller<ITask> leaveTask = new IvyComponentLogicCaller<ITask>();
leaveTask.invokeComponentLogic("language-settings", "#{logic.saveUserLanguages}", Arrays.asList());' #txt
Us0 f7 328 234 112 44 0 -8 #rect
Us0 f7 @|StepIcon #fIcon
Us0 f13 109 256 328 256 #arcP
Us0 f14 440 256 715 256 #arcP
Us0 f21 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>successful?</name>
    </language>
</elementInfo>
' #txt
Us0 f21 464 408 32 32 -33 -36 #rect
Us0 f21 @|AlternativeIcon #fIcon
Us0 f16 723 411 26 26 0 12 #rect
Us0 f16 @|UdProcessEndIcon #fIcon
Us0 f18 processCall 'Ivy Data Processes/LanguageService:saveUserLanguage(String,ch.ivy.addon.portalkit.ivydata.bo.IvyLanguage)' #txt
Us0 f18 requestActionDecl '<String username,ch.ivy.addon.portalkit.ivydata.bo.IvyLanguage language> param;' #txt
Us0 f18 requestMappingAction 'param.username=ivy.session.getSessionUserName();
param.language=in.language;
' #txt
Us0 f18 responseActionDecl 'ch.ivy.addon.portalkit.multiapp.settings.CentralLanguage.CentralLanguageData out;
' #txt
Us0 f18 responseMappingAction 'out=in;
out.errors=result.errors;
' #txt
Us0 f18 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>LanguageService</name>
    </language>
</elementInfo>
' #txt
Us0 f18 176 402 112 44 -48 -8 #rect
Us0 f18 @|CallSubIcon #fIcon
Us0 f19 guid 17255B3EA938571E #txt
Us0 f19 method saveUserLanguages() #txt
Us0 f19 inParameterDecl '<> param;' #txt
Us0 f19 outParameterDecl '<> result;' #txt
Us0 f19 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>saveUserLanguages()</name>
    </language>
</elementInfo>
' #txt
Us0 f19 83 411 26 26 -62 15 #rect
Us0 f19 @|UdMethodIcon #fIcon
Us0 f20 actionTable 'out=in;
' #txt
Us0 f20 actionCode 'import ch.ivy.addon.portalkit.util.UserUtils;

UserUtils.setLanguague();' #txt
Us0 f20 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Set locale</name>
    </language>
</elementInfo>
' #txt
Us0 f20 328 402 112 44 -27 -8 #rect
Us0 f20 @|StepIcon #fIcon
Us0 f22 actionTable 'out=in;
' #txt
Us0 f22 actionCode 'import ch.ivy.addon.portalkit.util.BeanUtils;

BeanUtils.invokeBeanMethodViaMethodExpression("#{errorDisplayBean.displayErrors}", in.errors);' #txt
Us0 f22 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Display errors</name>
    </language>
</elementInfo>
' #txt
Us0 f22 544 490 112 44 -38 -8 #rect
Us0 f22 @|StepIcon #fIcon
Us0 f23 expr in #txt
Us0 f23 480 440 544 512 #arcP
Us0 f23 1 480 512 #addKink
Us0 f23 1 0.3550161077912236 0 0 #arcLabel
Us0 f27 expr out #txt
Us0 f27 440 424 464 424 #arcP
Us0 f28 expr out #txt
Us0 f28 656 512 736 437 #arcP
Us0 f28 1 736 512 #addKink
Us0 f28 0 0.6455203302508128 0 0 #arcLabel
Us0 f29 expr out #txt
Us0 f29 109 424 176 424 #arcP
Us0 f30 expr in #txt
Us0 f30 outCond in.errors.isEmpty() #txt
Us0 f30 496 424 723 424 #arcP
Us0 f31 expr out #txt
Us0 f31 288 424 328 424 #arcP
>Proto Us0 .type ch.ivy.addon.portalkit.settings.UserProfile.UserProfileData #txt
>Proto Us0 .processKind HTML_DIALOG #txt
>Proto Us0 -8 -8 16 16 16 26 #rect
>Proto Us0 '' #fIcon
Us0 f0 mainOut f2 tail #connect
Us0 f2 head f1 mainIn #connect
Us0 f3 mainOut f5 tail #connect
Us0 f5 head f4 mainIn #connect
Us0 f11 mainOut f25 tail #connect
Us0 f25 head f9 mainIn #connect
Us0 f9 mainOut f24 tail #connect
Us0 f24 head f10 mainIn #connect
Us0 f12 mainOut f26 tail #connect
Us0 f26 head f11 mainIn #connect
Us0 f10 mainOut f17 tail #connect
Us0 f17 head f8 mainIn #connect
Us0 f6 mainOut f13 tail #connect
Us0 f13 head f7 mainIn #connect
Us0 f7 mainOut f14 tail #connect
Us0 f14 head f15 mainIn #connect
Us0 f19 mainOut f29 tail #connect
Us0 f29 head f18 mainIn #connect
Us0 f18 mainOut f31 tail #connect
Us0 f31 head f20 mainIn #connect
Us0 f22 mainOut f28 tail #connect
Us0 f28 head f16 mainIn #connect
Us0 f20 mainOut f27 tail #connect
Us0 f27 head f21 in #connect
Us0 f21 out f30 tail #connect
Us0 f30 head f16 mainIn #connect
Us0 f21 out f23 tail #connect
Us0 f23 head f22 mainIn #connect
