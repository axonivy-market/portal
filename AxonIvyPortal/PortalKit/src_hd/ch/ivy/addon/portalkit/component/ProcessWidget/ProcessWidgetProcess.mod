[Ivy]
[>Created: Mon Feb 29 10:23:02 ICT 2016]
14FEEC13F8B8E7D2 3.18 #module
>Proto >Proto Collection #zClass
Ps0 ProcessWidgetProcess Big #zClass
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
Ps0 @RichDialogProcessEnd f1 '' #zField
Ps0 @RichDialogMethodStart f3 '' #zField
Ps0 @RichDialogProcessEnd f4 '' #zField
Ps0 @Trigger f6 '' #zField
Ps0 @PushWFArc f7 '' #zField
Ps0 @PushWFArc f5 '' #zField
Ps0 @RichDialogMethodStart f8 '' #zField
Ps0 @RichDialogProcessEnd f9 '' #zField
Ps0 @CallSub f11 '' #zField
Ps0 @PushWFArc f10 '' #zField
Ps0 @RichDialogProcessEnd f14 '' #zField
Ps0 @Trigger f15 '' #zField
Ps0 @PushWFArc f16 '' #zField
Ps0 @RichDialogMethodStart f20 '' #zField
Ps0 @PushWFArc f13 '' #zField
Ps0 @RichDialogMethodStart f17 '' #zField
Ps0 @RichDialogProcessEnd f18 '' #zField
Ps0 @Trigger f21 '' #zField
Ps0 @PushWFArc f22 '' #zField
Ps0 @PushWFArc f19 '' #zField
Ps0 @RichDialogMethodStart f23 '' #zField
Ps0 @RichDialogProcessEnd f24 '' #zField
Ps0 @CallSub f26 '' #zField
Ps0 @PushWFArc f25 '' #zField
Ps0 @Alternative f28 '' #zField
Ps0 @PushWFArc f29 '' #zField
Ps0 @PushWFArc f27 '' #zField
Ps0 @PushWFArc f30 '' #zField
Ps0 @RichDialogInitStart f12 '' #zField
Ps0 @PushWFArc f31 '' #zField
Ps0 @GridStep f0 '' #zField
Ps0 @PushWFArc f2 '' #zField
Ps0 @PushWFArc f32 '' #zField
>Proto Ps0 Ps0 ProcessWidgetProcess #zField
Ps0 f1 type ch.ivy.addon.portalkit.component.ProcessWidget.ProcessWidgetData #txt
Ps0 f1 53 213 22 22 14 0 #rect
Ps0 f1 @|RichDialogProcessEndIcon #fIcon
Ps0 f3 guid 150470B90F27C9F0 #txt
Ps0 f3 type ch.ivy.addon.portalkit.component.ProcessWidget.ProcessWidgetData #txt
Ps0 f3 method synchronizeUserProcessToPortalServer(ch.ivy.addon.portalkit.persistence.domain.UserProcess) #txt
Ps0 f3 disableUIEvents false #txt
Ps0 f3 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<ch.ivy.addon.portalkit.persistence.domain.UserProcess userProcess> param = methodEvent.getInputArguments();
' #txt
Ps0 f3 inParameterMapAction 'out.userProcess=param.userProcess;
' #txt
Ps0 f3 outParameterDecl '<> result;
' #txt
Ps0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>synchronizeUserProcessToPortalServer(UserProcess)</name>
        <nameStyle>49,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ps0 f3 341 85 22 22 14 0 #rect
Ps0 f3 @|RichDialogMethodStartIcon #fIcon
Ps0 f4 type ch.ivy.addon.portalkit.component.ProcessWidget.ProcessWidgetData #txt
Ps0 f4 341 213 22 22 14 0 #rect
Ps0 f4 @|RichDialogProcessEndIcon #fIcon
Ps0 f6 type ch.ivy.addon.portalkit.component.ProcessWidget.ProcessWidgetData #txt
Ps0 f6 processCall 'Business Processes/AbstractSynchronizingConfiguration:addOrUpdateOne(ch.ivy.addon.portalkit.persistence.domain.BusinessEntity)' #txt
Ps0 f6 doCall true #txt
Ps0 f6 requestActionDecl '<ch.ivy.addon.portalkit.persistence.domain.BusinessEntity businessEntity> param;
' #txt
Ps0 f6 requestMappingAction 'param.businessEntity=in.userProcess;
' #txt
Ps0 f6 responseActionDecl 'ch.ivy.addon.portalkit.component.ProcessWidget.ProcessWidgetData out;
' #txt
Ps0 f6 responseMappingAction 'out=in;
' #txt
Ps0 f6 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>trigger synchronize user process to all portal server</name>
        <nameStyle>53,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ps0 f6 334 148 36 24 20 -2 #rect
Ps0 f6 @|TriggerIcon #fIcon
Ps0 f7 expr out #txt
Ps0 f7 352 107 352 148 #arcP
Ps0 f5 expr out #txt
Ps0 f5 352 172 352 213 #arcP
Ps0 f8 guid 150CB9B4B521448F #txt
Ps0 f8 type ch.ivy.addon.portalkit.component.ProcessWidget.ProcessWidgetData #txt
Ps0 f8 method collectAllProcesses() #txt
Ps0 f8 disableUIEvents false #txt
Ps0 f8 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<> param = methodEvent.getInputArguments();
' #txt
Ps0 f8 outParameterDecl '<List<ch.ivy.addon.portalkit.persistence.domain.UserProcess> processes> result;
' #txt
Ps0 f8 outParameterMapAction 'result.processes=in.processes;
' #txt
Ps0 f8 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>collectAllProcesses()</name>
        <nameStyle>21,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ps0 f8 237 277 22 22 14 0 #rect
Ps0 f8 @|RichDialogMethodStartIcon #fIcon
Ps0 f9 type ch.ivy.addon.portalkit.component.ProcessWidget.ProcessWidgetData #txt
Ps0 f9 237 469 22 22 14 0 #rect
Ps0 f9 @|RichDialogProcessEndIcon #fIcon
Ps0 f11 type ch.ivy.addon.portalkit.component.ProcessWidget.ProcessWidgetData #txt
Ps0 f11 processCall MultiPortal/ProcessStart:findProcessStartsByCriteria(ch.ivy.ws.addon.ProcessSearchCriteria) #txt
Ps0 f11 doCall true #txt
Ps0 f11 requestActionDecl '<ch.ivy.ws.addon.ProcessSearchCriteria processSearchCriteria> param;
' #txt
Ps0 f11 requestMappingAction 'param.processSearchCriteria=in.processSearchCriteria;
' #txt
Ps0 f11 responseActionDecl 'ch.ivy.addon.portalkit.component.ProcessWidget.ProcessWidgetData out;
' #txt
Ps0 f11 responseMappingAction 'out=in;
out.processes=new ch.ivy.addon.portalkit.converter.UserProcessConverter().convert(result.processStarts);
' #txt
Ps0 f11 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>collect start processes 
from all servers</name>
        <nameStyle>41,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ps0 f11 230 404 36 24 20 -2 #rect
Ps0 f11 @|CallSubIcon #fIcon
Ps0 f10 expr out #txt
Ps0 f10 248 428 248 469 #arcP
Ps0 f14 type ch.ivy.addon.portalkit.component.ProcessWidget.ProcessWidgetData #txt
Ps0 f14 781 213 22 22 14 0 #rect
Ps0 f14 @|RichDialogProcessEndIcon #fIcon
Ps0 f15 type ch.ivy.addon.portalkit.component.ProcessWidget.ProcessWidgetData #txt
Ps0 f15 processCall 'Business Processes/AbstractSynchronizingConfiguration:addOrUpdateMany(List<ch.ivy.addon.portalkit.persistence.domain.BusinessEntity>)' #txt
Ps0 f15 doCall true #txt
Ps0 f15 requestActionDecl '<List<ch.ivy.addon.portalkit.persistence.domain.BusinessEntity> businessEntities> param;
' #txt
Ps0 f15 requestMappingAction 'param.businessEntities=in.synData;
' #txt
Ps0 f15 responseActionDecl 'ch.ivy.addon.portalkit.component.ProcessWidget.ProcessWidgetData out;
' #txt
Ps0 f15 responseMappingAction 'out=in;
' #txt
Ps0 f15 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>trigger synchronize user setting and process to all portal server</name>
        <nameStyle>65,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ps0 f15 774 148 36 24 20 -2 #rect
Ps0 f15 @|TriggerIcon #fIcon
Ps0 f16 expr out #txt
Ps0 f16 792 172 792 213 #arcP
Ps0 f20 guid 150FF00C2B47217B #txt
Ps0 f20 type ch.ivy.addon.portalkit.component.ProcessWidget.ProcessWidgetData #txt
Ps0 f20 method synchronizeDataToPortalServer(ch.ivy.addon.portalkit.persistence.domain.UserSetting,ch.ivy.addon.portalkit.persistence.domain.UserProcess) #txt
Ps0 f20 disableUIEvents false #txt
Ps0 f20 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<ch.ivy.addon.portalkit.persistence.domain.UserSetting userSetting,ch.ivy.addon.portalkit.persistence.domain.UserProcess userProcess> param = methodEvent.getInputArguments();
' #txt
Ps0 f20 inActionCode 'out.synData.clear();
out.synData.add(param.userSetting);
out.synData.add(param.userProcess);' #txt
Ps0 f20 outParameterDecl '<> result;
' #txt
Ps0 f20 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>synchronizeDataToPortalServer(UserSetting,UserProcess)</name>
        <nameStyle>54,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ps0 f20 781 85 22 22 14 0 #rect
Ps0 f20 @|RichDialogMethodStartIcon #fIcon
Ps0 f13 expr out #txt
Ps0 f13 792 107 792 148 #arcP
Ps0 f17 guid 1510E8468CE6D0AC #txt
Ps0 f17 type ch.ivy.addon.portalkit.component.ProcessWidget.ProcessWidgetData #txt
Ps0 f17 method synchronizeDeletedDataToPortalServer(java.util.List<ch.ivy.addon.portalkit.persistence.domain.UserProcess>) #txt
Ps0 f17 disableUIEvents false #txt
Ps0 f17 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<java.util.List<ch.ivy.addon.portalkit.persistence.domain.UserProcess> userProcesses> param = methodEvent.getInputArguments();
' #txt
Ps0 f17 inParameterMapAction 'out.processes=param.userProcesses;
' #txt
Ps0 f17 outParameterDecl '<> result;
' #txt
Ps0 f17 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>synchronizeDeletedDataToPortalServer(List&lt;UserProcess&gt;)</name>
        <nameStyle>55,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ps0 f17 485 277 22 22 14 0 #rect
Ps0 f17 @|RichDialogMethodStartIcon #fIcon
Ps0 f18 type ch.ivy.addon.portalkit.component.ProcessWidget.ProcessWidgetData #txt
Ps0 f18 485 405 22 22 14 0 #rect
Ps0 f18 @|RichDialogProcessEndIcon #fIcon
Ps0 f21 type ch.ivy.addon.portalkit.component.ProcessWidget.ProcessWidgetData #txt
Ps0 f21 processCall 'Business Processes/AbstractSynchronizingConfiguration:deleteManyProperties(List<ch.ivy.addon.portalkit.persistence.domain.BusinessEntity>)' #txt
Ps0 f21 doCall true #txt
Ps0 f21 requestActionDecl '<List<ch.ivy.addon.portalkit.persistence.domain.BusinessEntity> businessEntities> param;
' #txt
Ps0 f21 requestMappingAction 'param.businessEntities=in.processes;
' #txt
Ps0 f21 responseActionDecl 'ch.ivy.addon.portalkit.component.ProcessWidget.ProcessWidgetData out;
' #txt
Ps0 f21 responseMappingAction 'out=in;
' #txt
Ps0 f21 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>AbstractSynchronizingConfiguration</name>
        <nameStyle>34,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ps0 f21 478 340 36 24 20 -2 #rect
Ps0 f21 @|TriggerIcon #fIcon
Ps0 f22 expr out #txt
Ps0 f22 496 299 496 340 #arcP
Ps0 f19 expr out #txt
Ps0 f19 496 364 496 405 #arcP
Ps0 f23 guid 151D8B55F244B7CD #txt
Ps0 f23 type ch.ivy.addon.portalkit.component.ProcessWidget.ProcessWidgetData #txt
Ps0 f23 method collectAllProcessStarts() #txt
Ps0 f23 disableUIEvents false #txt
Ps0 f23 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<> param = methodEvent.getInputArguments();
' #txt
Ps0 f23 outParameterDecl '<List<ch.ivyteam.ivy.workflow.IProcessStart> processStarts> result;
' #txt
Ps0 f23 outParameterMapAction 'result.processStarts=in.processStarts;
' #txt
Ps0 f23 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>collectAllProcessStarts()</name>
        <nameStyle>25,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ps0 f23 53 277 22 22 14 0 #rect
Ps0 f23 @|RichDialogMethodStartIcon #fIcon
Ps0 f24 type ch.ivy.addon.portalkit.component.ProcessWidget.ProcessWidgetData #txt
Ps0 f24 53 469 22 22 14 0 #rect
Ps0 f24 @|RichDialogProcessEndIcon #fIcon
Ps0 f26 type ch.ivy.addon.portalkit.component.ProcessWidget.ProcessWidgetData #txt
Ps0 f26 processCall MultiPortal/ProcessStart:findProcessStartsByCriteria(ch.ivy.ws.addon.ProcessSearchCriteria) #txt
Ps0 f26 doCall true #txt
Ps0 f26 requestActionDecl '<ch.ivy.ws.addon.ProcessSearchCriteria processSearchCriteria> param;
' #txt
Ps0 f26 requestMappingAction 'param.processSearchCriteria.involvedUsername=ivy.session.getSessionUserName();
' #txt
Ps0 f26 responseActionDecl 'ch.ivy.addon.portalkit.component.ProcessWidget.ProcessWidgetData out;
' #txt
Ps0 f26 responseMappingAction 'out=in;
out.processStarts=result.processStarts;
' #txt
Ps0 f26 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>collect start processes 
from all servers</name>
        <nameStyle>41,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ps0 f26 46 404 36 24 20 -13 #rect
Ps0 f26 @|CallSubIcon #fIcon
Ps0 f25 expr out #txt
Ps0 f25 64 428 64 469 #arcP
Ps0 f28 type ch.ivy.addon.portalkit.component.ProcessWidget.ProcessWidgetData #txt
Ps0 f28 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>startProcesses
already collected?</name>
        <nameStyle>33,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ps0 f28 50 338 28 28 16 -15 #rect
Ps0 f28 @|AlternativeIcon #fIcon
Ps0 f29 expr out #txt
Ps0 f29 64 299 64 338 #arcP
Ps0 f27 expr in #txt
Ps0 f27 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>no</name>
        <nameStyle>2,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ps0 f27 64 366 64 404 #arcP
Ps0 f27 0 0.5208333333333333 0 0 #arcLabel
Ps0 f30 expr in #txt
Ps0 f30 outCond 'in.#processStarts is initialized' #txt
Ps0 f30 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>yes</name>
        <nameStyle>3,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ps0 f30 50 352 53 480 #arcP
Ps0 f30 1 32 352 #addKink
Ps0 f30 2 32 480 #addKink
Ps0 f30 1 0.5188239009388282 0 0 #arcLabel
Ps0 f12 guid 1531CA33D93D08C3 #txt
Ps0 f12 type ch.ivy.addon.portalkit.component.ProcessWidget.ProcessWidgetData #txt
Ps0 f12 method start(String) #txt
Ps0 f12 disableUIEvents true #txt
Ps0 f12 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<java.lang.String keyword> param = methodEvent.getInputArguments();
' #txt
Ps0 f12 inParameterMapAction 'out.processSearchCriteria.keyword=param.#keyword is initialized ? param.keyword : null;
' #txt
Ps0 f12 outParameterDecl '<> result;
' #txt
Ps0 f12 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start(String)</name>
        <nameStyle>13,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ps0 f12 53 85 22 22 14 0 #rect
Ps0 f12 @|RichDialogInitStartIcon #fIcon
Ps0 f31 expr out #txt
Ps0 f31 64 107 64 213 #arcP
Ps0 f0 actionDecl 'ch.ivy.addon.portalkit.component.ProcessWidget.ProcessWidgetData out;
' #txt
Ps0 f0 actionTable 'out=in;
' #txt
Ps0 f0 actionCode 'in.processSearchCriteria.involvedUsername = ivy.session.getSessionUserName();' #txt
Ps0 f0 type ch.ivy.addon.portalkit.component.ProcessWidget.ProcessWidgetData #txt
Ps0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>prepare search criteria</name>
        <nameStyle>23
</nameStyle>
    </language>
</elementInfo>
' #txt
Ps0 f0 230 340 36 24 20 -2 #rect
Ps0 f0 @|StepIcon #fIcon
Ps0 f2 expr out #txt
Ps0 f2 248 299 248 340 #arcP
Ps0 f32 expr out #txt
Ps0 f32 248 364 248 404 #arcP
>Proto Ps0 .type ch.ivy.addon.portalkit.component.ProcessWidget.ProcessWidgetData #txt
>Proto Ps0 .processKind HTML_DIALOG #txt
>Proto Ps0 -8 -8 16 16 16 26 #rect
>Proto Ps0 '' #fIcon
Ps0 f3 mainOut f7 tail #connect
Ps0 f7 head f6 mainIn #connect
Ps0 f6 mainOut f5 tail #connect
Ps0 f5 head f4 mainIn #connect
Ps0 f11 mainOut f10 tail #connect
Ps0 f10 head f9 mainIn #connect
Ps0 f15 mainOut f16 tail #connect
Ps0 f16 head f14 mainIn #connect
Ps0 f20 mainOut f13 tail #connect
Ps0 f13 head f15 mainIn #connect
Ps0 f17 mainOut f22 tail #connect
Ps0 f22 head f21 mainIn #connect
Ps0 f21 mainOut f19 tail #connect
Ps0 f19 head f18 mainIn #connect
Ps0 f26 mainOut f25 tail #connect
Ps0 f25 head f24 mainIn #connect
Ps0 f23 mainOut f29 tail #connect
Ps0 f29 head f28 in #connect
Ps0 f27 head f26 mainIn #connect
Ps0 f28 out f30 tail #connect
Ps0 f30 head f24 mainIn #connect
Ps0 f28 out f27 tail #connect
Ps0 f12 mainOut f31 tail #connect
Ps0 f31 head f1 mainIn #connect
Ps0 f8 mainOut f2 tail #connect
Ps0 f2 head f0 mainIn #connect
Ps0 f0 mainOut f32 tail #connect
Ps0 f32 head f11 mainIn #connect
