[Ivy]
[>Created: Mon Oct 17 16:55:12 ICT 2016]
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
Ps0 @RichDialogMethodStart f17 '' #zField
Ps0 @RichDialogProcessEnd f18 '' #zField
Ps0 @Trigger f21 '' #zField
Ps0 @PushWFArc f19 '' #zField
Ps0 @RichDialogMethodStart f23 '' #zField
Ps0 @RichDialogProcessEnd f24 '' #zField
Ps0 @CallSub f26 '' #zField
Ps0 @PushWFArc f25 '' #zField
Ps0 @Alternative f28 '' #zField
Ps0 @PushWFArc f30 '' #zField
Ps0 @RichDialogInitStart f12 '' #zField
Ps0 @GridStep f0 '' #zField
Ps0 @PushWFArc f2 '' #zField
Ps0 @GridStep f13 '' #zField
Ps0 @GridStep f16 '' #zField
Ps0 @PushWFArc f31 '' #zField
Ps0 @Alternative f33 '' #zField
Ps0 @PushWFArc f34 '' #zField
Ps0 @Trigger f36 '' #zField
Ps0 @PushWFArc f37 '' #zField
Ps0 @Alternative f22 '' #zField
Ps0 @PushWFArc f38 '' #zField
Ps0 @PushWFArc f39 '' #zField
Ps0 @PushWFArc f40 '' #zField
Ps0 @PushWFArc f41 '' #zField
Ps0 @PushWFArc f42 '' #zField
Ps0 @PushWFArc f14 '' #zField
Ps0 @PushWFArc f15 '' #zField
Ps0 @PushWFArc f32 '' #zField
Ps0 @RichDialogProcessEnd f43 '' #zField
Ps0 @GridStep f45 '' #zField
Ps0 @PushWFArc f44 '' #zField
Ps0 @PushWFArc f20 '' #zField
Ps0 @RichDialogMethodStart f27 '' #zField
Ps0 @PushWFArc f29 '' #zField
>Proto Ps0 Ps0 ProcessWidgetProcess #zField
Ps0 f1 type ch.ivy.addon.portalkit.component.ProcessWidget.ProcessWidgetData #txt
Ps0 f1 85 213 22 22 14 0 #rect
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
Ps0 f3 277 85 22 22 14 0 #rect
Ps0 f3 @|RichDialogMethodStartIcon #fIcon
Ps0 f4 type ch.ivy.addon.portalkit.component.ProcessWidget.ProcessWidgetData #txt
Ps0 f4 277 213 22 22 14 0 #rect
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
Ps0 f6 270 148 36 24 20 -2 #rect
Ps0 f6 @|TriggerIcon #fIcon
Ps0 f7 expr out #txt
Ps0 f7 288 107 288 148 #arcP
Ps0 f5 expr out #txt
Ps0 f5 288 172 288 213 #arcP
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
Ps0 f8 269 309 22 22 14 0 #rect
Ps0 f8 @|RichDialogMethodStartIcon #fIcon
Ps0 f9 type ch.ivy.addon.portalkit.component.ProcessWidget.ProcessWidgetData #txt
Ps0 f9 269 501 22 22 14 0 #rect
Ps0 f9 @|RichDialogProcessEndIcon #fIcon
Ps0 f11 type ch.ivy.addon.portalkit.component.ProcessWidget.ProcessWidgetData #txt
Ps0 f11 processCall MultiPortal/ProcessStart:findProcessStartsByCriteria(String,ch.ivy.ws.addon.ProcessSearchCriteria) #txt
Ps0 f11 doCall true #txt
Ps0 f11 requestActionDecl '<java.lang.String language,ch.ivy.ws.addon.ProcessSearchCriteria processSearchCriteria> param;
' #txt
Ps0 f11 requestMappingAction 'param.language=in.language;
param.processSearchCriteria=in.processSearchCriteria;
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
Ps0 f11 262 436 36 24 20 -2 #rect
Ps0 f11 @|CallSubIcon #fIcon
Ps0 f10 expr out #txt
Ps0 f10 280 460 280 501 #arcP
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
Ps0 f17 517 309 22 22 14 -1 #rect
Ps0 f17 @|RichDialogMethodStartIcon #fIcon
Ps0 f18 type ch.ivy.addon.portalkit.component.ProcessWidget.ProcessWidgetData #txt
Ps0 f18 517 693 22 22 14 0 #rect
Ps0 f18 @|RichDialogProcessEndIcon #fIcon
Ps0 f21 type ch.ivy.addon.portalkit.component.ProcessWidget.ProcessWidgetData #txt
Ps0 f21 processCall 'Business Processes/AbstractSynchronizingConfiguration:deleteManyProperties(List<ch.ivy.addon.portalkit.persistence.domain.BusinessEntity>)' #txt
Ps0 f21 doCall true #txt
Ps0 f21 requestActionDecl '<List<ch.ivy.addon.portalkit.persistence.domain.BusinessEntity> businessEntities> param;
' #txt
Ps0 f21 requestMappingAction 'param.businessEntities=in.processesToBeDeleted;
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
Ps0 f21 510 628 36 24 20 -2 #rect
Ps0 f21 @|TriggerIcon #fIcon
Ps0 f19 expr out #txt
Ps0 f19 528 652 528 693 #arcP
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
Ps0 f23 85 309 22 22 14 0 #rect
Ps0 f23 @|RichDialogMethodStartIcon #fIcon
Ps0 f24 type ch.ivy.addon.portalkit.component.ProcessWidget.ProcessWidgetData #txt
Ps0 f24 85 565 22 22 14 0 #rect
Ps0 f24 @|RichDialogProcessEndIcon #fIcon
Ps0 f26 type ch.ivy.addon.portalkit.component.ProcessWidget.ProcessWidgetData #txt
Ps0 f26 processCall MultiPortal/ProcessStart:findProcessStartsByCriteria(String,ch.ivy.ws.addon.ProcessSearchCriteria) #txt
Ps0 f26 doCall true #txt
Ps0 f26 requestActionDecl '<java.lang.String language,ch.ivy.ws.addon.ProcessSearchCriteria processSearchCriteria> param;
' #txt
Ps0 f26 requestMappingAction 'param.language=in.language;
param.processSearchCriteria=in.processSearchCriteria;
param.processSearchCriteria.involvedUsername=ivy.session.getSessionUserName();
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
Ps0 f26 78 500 36 24 20 -13 #rect
Ps0 f26 @|CallSubIcon #fIcon
Ps0 f25 expr out #txt
Ps0 f25 96 524 96 565 #arcP
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
Ps0 f28 82 370 28 28 16 -15 #rect
Ps0 f28 @|AlternativeIcon #fIcon
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
Ps0 f30 82 384 85 576 #arcP
Ps0 f30 1 32 384 #addKink
Ps0 f30 2 32 576 #addKink
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
Ps0 f12 85 85 22 22 14 0 #rect
Ps0 f12 @|RichDialogInitStartIcon #fIcon
Ps0 f0 actionDecl 'ch.ivy.addon.portalkit.component.ProcessWidget.ProcessWidgetData out;
' #txt
Ps0 f0 actionTable 'out=in;
' #txt
Ps0 f0 actionCode 'import ch.ivy.addon.portalkit.util.SecurityServiceUtils;
import ch.ivyteam.ivy.security.IUser;

IUser involvedUser = ivy.session.getSessionUser();
in.processSearchCriteria.involvedUsername = involvedUser.getName();

if (involvedUser.getEMailLanguage() is initialized) {
	in.language = involvedUser.getEMailLanguage().getLanguage();
}

Long serverId = SecurityServiceUtils.getServerIdFromSession();
if (#serverId is initialized) {
	in.processSearchCriteria.setServerId(serverId);
}

String applicationName = SecurityServiceUtils.getApplicationNameFromSession();
if (#applicationName is initialized) {
	List<String> involvedApplications = [applicationName];
	in.processSearchCriteria.setInvolvedApplications(involvedApplications);
}
' #txt
Ps0 f0 type ch.ivy.addon.portalkit.component.ProcessWidget.ProcessWidgetData #txt
Ps0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>prepare search criteria</name>
        <nameStyle>23,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ps0 f0 262 372 36 24 20 -2 #rect
Ps0 f0 @|StepIcon #fIcon
Ps0 f2 expr out #txt
Ps0 f2 280 331 280 372 #arcP
Ps0 f13 actionDecl 'ch.ivy.addon.portalkit.component.ProcessWidget.ProcessWidgetData out;
' #txt
Ps0 f13 actionTable 'out=in;
' #txt
Ps0 f13 actionCode 'import ch.ivy.addon.portalkit.util.SecurityServiceUtils;
import ch.ivyteam.ivy.security.IUser;

IUser involvedUser = ivy.session.getSessionUser();
in.processSearchCriteria.involvedUsername = involvedUser.getName();

if (involvedUser.getEMailLanguage() is initialized) {
	in.language = involvedUser.getEMailLanguage().getLanguage();
}

Long serverId = SecurityServiceUtils.getServerIdFromSession();
if (#serverId is initialized) {
	in.processSearchCriteria.setServerId(serverId);
}

String applicationName = SecurityServiceUtils.getApplicationNameFromSession();
if (#applicationName is initialized) {
	List<String> involvedApplications = [applicationName];
	in.processSearchCriteria.setInvolvedApplications(involvedApplications);
}
' #txt
Ps0 f13 type ch.ivy.addon.portalkit.component.ProcessWidget.ProcessWidgetData #txt
Ps0 f13 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>set language</name>
        <nameStyle>12,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ps0 f13 78 436 36 24 20 -2 #rect
Ps0 f13 @|StepIcon #fIcon
Ps0 f16 actionDecl 'ch.ivy.addon.portalkit.component.ProcessWidget.ProcessWidgetData out;
' #txt
Ps0 f16 actionTable 'out=in;
' #txt
Ps0 f16 actionCode 'import java.util.Iterator;
import ch.ivy.addon.portalkit.persistence.domain.UserProcess;

in.processesToBeDeleted.clear();

Iterator<UserProcess> i = in.processes.iterator();
while (i.hasNext()) {
	UserProcess process = i.next() as UserProcess;
	if (!process.defaultProcess) {
		in.processesToBeDeleted.add(process);
		i.remove();
	}
}' #txt
Ps0 f16 type ch.ivy.addon.portalkit.component.ProcessWidget.ProcessWidgetData #txt
Ps0 f16 510 372 36 24 20 -2 #rect
Ps0 f16 @|StepIcon #fIcon
Ps0 f31 expr out #txt
Ps0 f31 528 331 528 372 #arcP
Ps0 f33 type ch.ivy.addon.portalkit.component.ProcessWidget.ProcessWidgetData #txt
Ps0 f33 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>has default
processes deleted?</name>
        <nameStyle>30
</nameStyle>
    </language>
</elementInfo>
' #txt
Ps0 f33 514 434 28 28 14 0 #rect
Ps0 f33 @|AlternativeIcon #fIcon
Ps0 f34 expr out #txt
Ps0 f34 528 396 528 434 #arcP
Ps0 f36 type ch.ivy.addon.portalkit.component.ProcessWidget.ProcessWidgetData #txt
Ps0 f36 processCall 'Business Processes/AbstractSynchronizingConfiguration:addOrUpdateMany(List<ch.ivy.addon.portalkit.persistence.domain.BusinessEntity>)' #txt
Ps0 f36 doCall true #txt
Ps0 f36 requestActionDecl '<List<ch.ivy.addon.portalkit.persistence.domain.BusinessEntity> businessEntities> param;
' #txt
Ps0 f36 requestMappingAction 'param.businessEntities=in.processes;
' #txt
Ps0 f36 responseActionDecl 'ch.ivy.addon.portalkit.component.ProcessWidget.ProcessWidgetData out;
' #txt
Ps0 f36 responseMappingAction 'out=in;
' #txt
Ps0 f36 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>AbstractSynchronizingConfiguration</name>
        <nameStyle>34,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ps0 f36 510 500 36 24 20 -2 #rect
Ps0 f36 @|TriggerIcon #fIcon
Ps0 f37 expr in #txt
Ps0 f37 outCond !in.processes.isEmpty() #txt
Ps0 f37 528 462 528 500 #arcP
Ps0 f22 type ch.ivy.addon.portalkit.component.ProcessWidget.ProcessWidgetData #txt
Ps0 f22 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>has deleted
non default processes?</name>
        <nameStyle>34
</nameStyle>
    </language>
</elementInfo>
' #txt
Ps0 f22 514 562 28 28 14 0 #rect
Ps0 f22 @|AlternativeIcon #fIcon
Ps0 f38 expr out #txt
Ps0 f38 528 524 528 562 #arcP
Ps0 f39 expr in #txt
Ps0 f39 outCond !in.processesToBeDeleted.isEmpty() #txt
Ps0 f39 528 590 528 628 #arcP
Ps0 f40 expr in #txt
Ps0 f40 514 576 517 704 #arcP
Ps0 f40 1 448 576 #addKink
Ps0 f40 2 448 704 #addKink
Ps0 f40 1 0.51171875 0 0 #arcLabel
Ps0 f41 expr in #txt
Ps0 f41 542 448 542 576 #arcP
Ps0 f41 1 608 448 #addKink
Ps0 f41 2 608 576 #addKink
Ps0 f41 1 0.5 0 0 #arcLabel
Ps0 f42 expr out #txt
Ps0 f42 96 331 96 370 #arcP
Ps0 f14 expr out #txt
Ps0 f14 96 460 96 500 #arcP
Ps0 f15 expr in #txt
Ps0 f15 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>no</name>
        <nameStyle>2,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ps0 f15 96 398 96 436 #arcP
Ps0 f15 0 0.5208333333333333 0 0 #arcLabel
Ps0 f32 expr out #txt
Ps0 f32 280 396 280 436 #arcP
Ps0 f43 type ch.ivy.addon.portalkit.component.ProcessWidget.ProcessWidgetData #txt
Ps0 f43 757 213 22 22 14 0 #rect
Ps0 f43 @|RichDialogProcessEndIcon #fIcon
Ps0 f45 actionDecl 'ch.ivy.addon.portalkit.component.ProcessWidget.ProcessWidgetData out;
' #txt
Ps0 f45 actionTable 'out=in;
' #txt
Ps0 f45 actionCode 'import ch.ivy.addon.portalkit.service.UserProcessService;
import ch.ivy.addon.portalkit.persistence.domain.UserProcess;
import ch.ivy.addon.portalkit.util.UserUtils;

UserProcessService userProcessService = new UserProcessService();
String userName = UserUtils.getSessionUserName();
List<UserProcess> processesFromSubProcess = userProcessService.getDefaultUserProcessesFromSubProcess();
List<UserProcess> deletedDefaultProcesses = userProcessService.findDeletedDefaultProcessesByUserName(userName);
out.processes = userProcessService.subtractByLink(processesFromSubProcess, deletedDefaultProcesses);' #txt
Ps0 f45 type ch.ivy.addon.portalkit.component.ProcessWidget.ProcessWidgetData #txt
Ps0 f45 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>get default processes</name>
        <nameStyle>21,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ps0 f45 750 148 36 24 20 -2 #rect
Ps0 f45 @|StepIcon #fIcon
Ps0 f44 expr out #txt
Ps0 f44 768 172 768 213 #arcP
Ps0 f20 expr out #txt
Ps0 f20 96 107 96 213 #arcP
Ps0 f27 guid 157D210CFFD83014 #txt
Ps0 f27 type ch.ivy.addon.portalkit.component.ProcessWidget.ProcessWidgetData #txt
Ps0 f27 method collectDefaultProcesses() #txt
Ps0 f27 disableUIEvents false #txt
Ps0 f27 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<> param = methodEvent.getInputArguments();
' #txt
Ps0 f27 outParameterDecl '<List<ch.ivy.addon.portalkit.persistence.domain.UserProcess> userProcesses> result;
' #txt
Ps0 f27 outParameterMapAction 'result.userProcesses=in.processes;
' #txt
Ps0 f27 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>collectDefaultProcesses()</name>
        <nameStyle>25,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ps0 f27 757 53 22 22 14 0 #rect
Ps0 f27 @|RichDialogMethodStartIcon #fIcon
Ps0 f29 expr out #txt
Ps0 f29 768 75 768 148 #arcP
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
Ps0 f21 mainOut f19 tail #connect
Ps0 f19 head f18 mainIn #connect
Ps0 f26 mainOut f25 tail #connect
Ps0 f25 head f24 mainIn #connect
Ps0 f28 out f30 tail #connect
Ps0 f30 head f24 mainIn #connect
Ps0 f8 mainOut f2 tail #connect
Ps0 f2 head f0 mainIn #connect
Ps0 f0 mainOut f32 tail #connect
Ps0 f32 head f11 mainIn #connect
Ps0 f17 mainOut f31 tail #connect
Ps0 f31 head f16 mainIn #connect
Ps0 f16 mainOut f34 tail #connect
Ps0 f34 head f33 in #connect
Ps0 f33 out f37 tail #connect
Ps0 f37 head f36 mainIn #connect
Ps0 f36 mainOut f38 tail #connect
Ps0 f38 head f22 in #connect
Ps0 f22 out f39 tail #connect
Ps0 f39 head f21 mainIn #connect
Ps0 f22 out f40 tail #connect
Ps0 f40 head f18 mainIn #connect
Ps0 f33 out f41 tail #connect
Ps0 f41 head f22 in #connect
Ps0 f23 mainOut f42 tail #connect
Ps0 f42 head f28 in #connect
Ps0 f13 mainOut f14 tail #connect
Ps0 f14 head f26 mainIn #connect
Ps0 f28 out f15 tail #connect
Ps0 f15 head f13 mainIn #connect
Ps0 f45 mainOut f44 tail #connect
Ps0 f44 head f43 mainIn #connect
Ps0 f12 mainOut f20 tail #connect
Ps0 f20 head f1 mainIn #connect
Ps0 f27 mainOut f29 tail #connect
Ps0 f29 head f45 mainIn #connect
