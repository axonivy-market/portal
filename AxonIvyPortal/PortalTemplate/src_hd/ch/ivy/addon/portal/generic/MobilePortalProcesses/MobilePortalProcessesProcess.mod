[Ivy]
166E19046B7AABAF 3.23 #module
>Proto >Proto Collection #zClass
Ps0 MobilePortalProcessesProcess Big #zClass
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
Ps0 @RichDialogInitStart f2 '' #zField
Ps0 @PushWFArc f3 '' #zField
Ps0 @RichDialogMethodStart f27 '' #zField
Ps0 @RichDialogProcessEnd f46 '' #zField
Ps0 @GridStep f45 '' #zField
Ps0 @RichDialogMethodStart f35 '' #zField
Ps0 @RichDialogProcessEnd f43 '' #zField
Ps0 @GridStep f48 '' #zField
Ps0 @PushWFArc f49 '' #zField
Ps0 @PushWFArc f29 '' #zField
Ps0 @PushWFArc f47 '' #zField
Ps0 @PushWFArc f44 '' #zField
Ps0 @RichDialogProcessEnd f9 '' #zField
Ps0 @GridStep f50 '' #zField
Ps0 @GridStep f0 '' #zField
Ps0 @RichDialogMethodStart f8 '' #zField
Ps0 @CallSub f11 '' #zField
Ps0 @PushWFArc f4 '' #zField
Ps0 @PushWFArc f51 '' #zField
Ps0 @PushWFArc f10 '' #zField
Ps0 @PushWFArc f32 '' #zField
>Proto Ps0 Ps0 MobilePortalProcessesProcess #zField
Ps0 f1 type ch.ivy.addon.portal.generic.MobilePortalProcesses.MobilePortalProcessesData #txt
Ps0 f1 229 133 22 22 14 0 #rect
Ps0 f1 @|RichDialogProcessEndIcon #fIcon
Ps0 f2 guid 1573377403EC1C55 #txt
Ps0 f2 type ch.ivy.addon.portal.generic.MobilePortalProcesses.MobilePortalProcessesData #txt
Ps0 f2 method start(String) #txt
Ps0 f2 disableUIEvents true #txt
Ps0 f2 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<java.lang.String keyword> param = methodEvent.getInputArguments();
' #txt
Ps0 f2 inParameterMapAction 'out.webStartableSearchCriteria.keyword=param.keyword;
' #txt
Ps0 f2 outParameterDecl '<> result;
' #txt
Ps0 f2 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start(String)</name>
    </language>
</elementInfo>
' #txt
Ps0 f2 77 133 22 22 -36 17 #rect
Ps0 f2 @|RichDialogInitStartIcon #fIcon
Ps0 f3 expr out #txt
Ps0 f3 99 144 229 144 #arcP
Ps0 f27 guid 167345D1546DA8A5 #txt
Ps0 f27 type ch.ivy.addon.portal.generic.MobilePortalProcesses.MobilePortalProcessesData #txt
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
Ps0 f27 789 93 22 22 14 0 #rect
Ps0 f27 @|RichDialogMethodStartIcon #fIcon
Ps0 f46 type ch.ivy.addon.portal.generic.MobilePortalProcesses.MobilePortalProcessesData #txt
Ps0 f46 1013 221 22 22 14 0 #rect
Ps0 f46 @|RichDialogProcessEndIcon #fIcon
Ps0 f45 actionDecl 'ch.ivy.addon.portal.generic.MobilePortalProcesses.MobilePortalProcessesData out;
' #txt
Ps0 f45 actionTable 'out=in;
' #txt
Ps0 f45 actionCode 'import ch.ivy.addon.portalkit.service.UserProcessService;
import ch.ivy.addon.portalkit.persistence.domain.UserProcess;
import ch.ivy.addon.portalkit.util.UserUtils;

UserProcessService userProcessService = new UserProcessService();
String userName = UserUtils.getSessionUserName();
out.processes = userProcessService.getDefaultUserProcessesFromSubProcess();' #txt
Ps0 f45 type ch.ivy.addon.portal.generic.MobilePortalProcesses.MobilePortalProcessesData #txt
Ps0 f45 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>get default processes</name>
        <nameStyle>21,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ps0 f45 782 156 36 24 20 -2 #rect
Ps0 f45 @|StepIcon #fIcon
Ps0 f35 guid 167345D1548AB12F #txt
Ps0 f35 type ch.ivy.addon.portal.generic.MobilePortalProcesses.MobilePortalProcessesData #txt
Ps0 f35 method startProcess(String) #txt
Ps0 f35 disableUIEvents false #txt
Ps0 f35 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<java.lang.String link> param = methodEvent.getInputArguments();
' #txt
Ps0 f35 inParameterMapAction 'out.processLink=param.link;
' #txt
Ps0 f35 outParameterDecl '<> result;
' #txt
Ps0 f35 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>startProcess(String)</name>
        <nameStyle>20,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ps0 f35 1013 93 22 22 14 0 #rect
Ps0 f35 @|RichDialogMethodStartIcon #fIcon
Ps0 f43 type ch.ivy.addon.portal.generic.MobilePortalProcesses.MobilePortalProcessesData #txt
Ps0 f43 789 221 22 22 14 0 #rect
Ps0 f43 @|RichDialogProcessEndIcon #fIcon
Ps0 f48 actionDecl 'ch.ivy.addon.portal.generic.MobilePortalProcesses.MobilePortalProcessesData out;
' #txt
Ps0 f48 actionTable 'out=in;
' #txt
Ps0 f48 actionCode 'import javax.faces.context.FacesContext;

FacesContext.getCurrentInstance().getExternalContext().redirect(in.processLink);' #txt
Ps0 f48 type ch.ivy.addon.portal.generic.MobilePortalProcesses.MobilePortalProcessesData #txt
Ps0 f48 1006 156 36 24 20 -2 #rect
Ps0 f48 @|StepIcon #fIcon
Ps0 f49 expr out #txt
Ps0 f49 1024 115 1024 156 #arcP
Ps0 f29 expr out #txt
Ps0 f29 800 115 800 156 #arcP
Ps0 f47 expr out #txt
Ps0 f47 1024 180 1024 221 #arcP
Ps0 f44 expr out #txt
Ps0 f44 800 180 800 221 #arcP
Ps0 f9 type ch.ivy.addon.portal.generic.MobilePortalProcesses.MobilePortalProcessesData #txt
Ps0 f9 453 557 22 22 14 0 #rect
Ps0 f9 @|RichDialogProcessEndIcon #fIcon
Ps0 f50 actionDecl 'ch.ivy.addon.portal.generic.MobilePortalProcesses.MobilePortalProcessesData out;
' #txt
Ps0 f50 actionTable 'out=in;
' #txt
Ps0 f50 actionCode 'import ch.ivy.addon.portalkit.util.PermissionUtils;
import ch.ivyteam.ivy.security.ISecurityMember;
import org.apache.commons.lang3.StringUtils;
import ch.ivy.addon.portalkit.service.ExpressServiceRegistry;
import ch.ivy.addon.portalkit.bo.ExpressProcess;
import ch.ivyteam.ivy.security.IUser;
import ch.ivyteam.ivy.security.IRole;
import ch.ivy.addon.portalkit.service.ProcessStartCollector;
import ch.ivy.addon.portalkit.persistence.domain.UserProcess;

ProcessStartCollector processStartCollector = new ProcessStartCollector(ivy.request.getApplication());
String expressStartLink = processStartCollector.findExpressWorkflowStartLink();
if (!StringUtils.isEmpty(expressStartLink)) {
	List<ExpressProcess> workflows = ExpressServiceRegistry.getProcessService().findReadyToExecuteProcessOrderByName();
	for(ExpressProcess wf : workflows) {
		if (PermissionUtils.canStartExpressWorkflow(wf)) {
			UserProcess userProcess = new UserProcess();
		  userProcess.setProcessName(wf.processName);
		  userProcess.setUserName(wf.processOwner);
			String startLink = processStartCollector.findExpressWorkflowStartLink() + "?workflowID=" + wf.id;
		  userProcess.setLink(startLink);
		  userProcess.setDefaultProcess(false);
			userProcess.setWorkflowId(wf.id);
			userProcess.setDescription(wf.processDescription);
		  in.processes.add(userProcess);
		}
	}	
}

' #txt
Ps0 f50 type ch.ivy.addon.portal.generic.MobilePortalProcesses.MobilePortalProcessesData #txt
Ps0 f50 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Collect all workflows</name>
        <nameStyle>21,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ps0 f50 448 460 32 24 21 -5 #rect
Ps0 f50 @|StepIcon #fIcon
Ps0 f0 actionDecl 'ch.ivy.addon.portal.generic.MobilePortalProcesses.MobilePortalProcessesData out;
' #txt
Ps0 f0 actionTable 'out=in;
' #txt
Ps0 f0 actionCode 'import ch.ivy.addon.portalkit.util.SecurityServiceUtils;
import ch.ivyteam.ivy.security.IUser;

IUser involvedUser = ivy.session.getSessionUser();
in.webStartableSearchCriteria.involvedUsername = involvedUser.getName();

if (involvedUser.getEMailLanguage() is initialized) {
	in.language = involvedUser.getEMailLanguage().getLanguage();
}

Long serverId = SecurityServiceUtils.getServerIdFromSession();
if (#serverId is initialized) {
	in.webStartableSearchCriteria.setServerId(serverId);
}

String applicationName = SecurityServiceUtils.getApplicationNameFromSession();
if (#applicationName is initialized) {
	List<String> involvedApplications = [applicationName];
	in.webStartableSearchCriteria.setInvolvedApplications(involvedApplications);
}
' #txt
Ps0 f0 type ch.ivy.addon.portal.generic.MobilePortalProcesses.MobilePortalProcessesData #txt
Ps0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>prepare search criteria</name>
        <nameStyle>23,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ps0 f0 446 300 36 24 20 -2 #rect
Ps0 f0 @|StepIcon #fIcon
Ps0 f8 guid 1673501E0F0D2042 #txt
Ps0 f8 type ch.ivy.addon.portal.generic.MobilePortalProcesses.MobilePortalProcessesData #txt
Ps0 f8 method collectAllProcesses() #txt
Ps0 f8 disableUIEvents false #txt
Ps0 f8 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<> param = methodEvent.getInputArguments();
' #txt
Ps0 f8 outParameterDecl '<List<ch.ivy.addon.portalkit.persistence.domain.UserProcess> processes> result;
' #txt
Ps0 f8 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>collectAllProcesses()</name>
    </language>
</elementInfo>
' #txt
Ps0 f8 453 237 22 22 14 0 #rect
Ps0 f8 @|RichDialogMethodStartIcon #fIcon
Ps0 f11 type ch.ivy.addon.portal.generic.MobilePortalProcesses.MobilePortalProcessesData #txt
Ps0 f11 processCall MultiPortal/WebStartableService:findWebStartablesByCriteria(String,ch.ivy.ws.addon.WebStartableSearchCriteria) #txt
Ps0 f11 doCall true #txt
Ps0 f11 requestActionDecl '<java.lang.String language,ch.ivy.ws.addon.WebStartableSearchCriteria webStartableSearchCriteria> param;
' #txt
Ps0 f11 requestMappingAction 'param.language=in.language;
param.webStartableSearchCriteria=in.webStartableSearchCriteria;
' #txt
Ps0 f11 responseActionDecl 'ch.ivy.addon.portal.generic.MobilePortalProcesses.MobilePortalProcessesData out;
' #txt
Ps0 f11 responseMappingAction 'out=in;
out.processes=new ch.ivy.addon.portalkit.converter.UserProcessConverter().convert(result.webStartables);
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
Ps0 f11 446 364 36 24 20 -2 #rect
Ps0 f11 @|CallSubIcon #fIcon
Ps0 f4 expr out #txt
Ps0 f4 464 259 464 300 #arcP
Ps0 f51 expr out #txt
Ps0 f51 464 388 464 460 #arcP
Ps0 f10 expr out #txt
Ps0 f10 464 484 464 557 #arcP
Ps0 f32 expr out #txt
Ps0 f32 464 324 464 364 #arcP
>Proto Ps0 .type ch.ivy.addon.portal.generic.MobilePortalProcesses.MobilePortalProcessesData #txt
>Proto Ps0 .processKind HTML_DIALOG #txt
>Proto Ps0 -8 -8 16 16 16 26 #rect
>Proto Ps0 '' #fIcon
Ps0 f2 mainOut f3 tail #connect
Ps0 f3 head f1 mainIn #connect
Ps0 f45 mainOut f44 tail #connect
Ps0 f44 head f43 mainIn #connect
Ps0 f27 mainOut f29 tail #connect
Ps0 f29 head f45 mainIn #connect
Ps0 f35 mainOut f49 tail #connect
Ps0 f49 head f48 mainIn #connect
Ps0 f48 mainOut f47 tail #connect
Ps0 f47 head f46 mainIn #connect
Ps0 f8 mainOut f4 tail #connect
Ps0 f4 head f0 mainIn #connect
Ps0 f0 mainOut f32 tail #connect
Ps0 f32 head f11 mainIn #connect
Ps0 f11 mainOut f51 tail #connect
Ps0 f51 head f50 mainIn #connect
Ps0 f50 mainOut f10 tail #connect
Ps0 f10 head f9 mainIn #connect
