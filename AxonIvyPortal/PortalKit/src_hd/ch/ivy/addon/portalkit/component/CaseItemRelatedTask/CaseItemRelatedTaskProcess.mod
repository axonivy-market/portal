[Ivy]
153358BE9219FD4C 7.5.0 #module
>Proto >Proto Collection #zClass
Cs0 CaseItemRelatedTaskProcess Big #zClass
Cs0 RD #cInfo
Cs0 #process
Cs0 @TextInP .type .type #zField
Cs0 @TextInP .processKind .processKind #zField
Cs0 @AnnotationInP-0n ai ai #zField
Cs0 @MessageFlowInP-0n messageIn messageIn #zField
Cs0 @MessageFlowOutP-0n messageOut messageOut #zField
Cs0 @TextInP .xml .xml #zField
Cs0 @TextInP .responsibility .responsibility #zField
Cs0 @UdInit f0 '' #zField
Cs0 @UdProcessEnd f1 '' #zField
Cs0 @PushWFArc f3 '' #zField
Cs0 @UdMethod f6 '' #zField
Cs0 @UdProcessEnd f7 '' #zField
Cs0 @Split f8 '' #zField
Cs0 @Join f9 '' #zField
Cs0 @PushWFArc f10 '' #zField
Cs0 @PushWFArc f12 '' #zField
Cs0 @GridStep f14 '' #zField
Cs0 @PushWFArc f15 '' #zField
Cs0 @SJArc f13 '' #zField
Cs0 @GridStep f17 '' #zField
Cs0 @PushWFArc f18 '' #zField
Cs0 @CallSub f19 '' #zField
Cs0 @PushWFArc f20 '' #zField
Cs0 @CallSub f23 '' #zField
Cs0 @CallSub f26 '' #zField
Cs0 @CallSub f28 '' #zField
Cs0 @PushWFArc f29 '' #zField
Cs0 @SJArc f21 '' #zField
Cs0 @GridStep f11 '' #zField
Cs0 @GridStep f33 '' #zField
Cs0 @GridStep f35 '' #zField
Cs0 @GridStep f37 '' #zField
Cs0 @GridStep f4 '' #zField
Cs0 @PushWFArc f16 '' #zField
Cs0 @GridStep f31 '' #zField
Cs0 @GridStep f39 '' #zField
Cs0 @PushWFArc f41 '' #zField
Cs0 @PushWFArc f36 '' #zField
Cs0 @PushWFArc f40 '' #zField
Cs0 @GridStep f34 '' #zField
Cs0 @PushWFArc f42 '' #zField
Cs0 @PushWFArc f38 '' #zField
Cs0 @UdMethod f45 '' #zField
Cs0 @PushWFArc f47 '' #zField
Cs0 @PushWFArc f44 '' #zField
Cs0 @UdMethod f43 '' #zField
Cs0 @UdMethod f2 '' #zField
Cs0 @PushWFArc f5 '' #zField
Cs0 @UdMethod f25 '' #zField
Cs0 @PushWFArc f27 '' #zField
>Proto Cs0 Cs0 CaseItemRelatedTaskProcess #zField
Cs0 f0 guid 167E9A75EF3D0909 #txt
Cs0 f0 method start() #txt
Cs0 f0 inParameterDecl '<> param;' #txt
Cs0 f0 outParameterDecl '<> result;' #txt
Cs0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start()</name>
    </language>
</elementInfo>
' #txt
Cs0 f0 83 83 26 26 -16 15 #rect
Cs0 f0 @|UdInitIcon #fIcon
Cs0 f1 275 83 26 26 0 12 #rect
Cs0 f1 @|UdProcessEndIcon #fIcon
Cs0 f3 expr out #txt
Cs0 f3 109 96 275 96 #arcP
Cs0 f6 guid 167E9A777AB171EA #txt
Cs0 f6 method initData(ch.ivyteam.ivy.workflow.ICase) #txt
Cs0 f6 inParameterDecl '<ch.ivyteam.ivy.workflow.ICase iCase> param;' #txt
Cs0 f6 inParameterMapAction 'out.iCase=param.iCase;
' #txt
Cs0 f6 outParameterDecl '<> result;' #txt
Cs0 f6 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>initData(ICase)</name>
    </language>
</elementInfo>
' #txt
Cs0 f6 83 179 26 26 -41 15 #rect
Cs0 f6 @|UdMethodIcon #fIcon
Cs0 f7 731 179 26 26 0 12 #rect
Cs0 f7 @|UdProcessEndIcon #fIcon
Cs0 f8 actionTable 'out1=in;
out1.iCase=in.iCase;
out2.iCase=in.iCase;
' #txt
Cs0 f8 176 176 32 32 0 16 #rect
Cs0 f8 @|ThreadIcon #fIcon
Cs0 f9 actionTable 'out=in1;
out.relatedTasks=in1.relatedTasks;
out.technicalCases=in2.technicalCases;
out.totalRelatedCases=in2.totalRelatedCases;
out.totalRelatedTasks=in1.totalRelatedTasks;
' #txt
Cs0 f9 632 176 32 32 0 16 #rect
Cs0 f9 @|JoinIcon #fIcon
Cs0 f10 expr out #txt
Cs0 f10 664 192 731 192 #arcP
Cs0 f12 expr out #txt
Cs0 f12 109 192 176 192 #arcP
Cs0 f14 actionTable 'out=in;
' #txt
Cs0 f14 actionCode 'import ch.ivy.addon.portalkit.util.PermissionUtils;
import ch.ivy.addon.portalkit.ivydata.utils.ServiceUtilities;
import ch.ivyteam.ivy.security.ISession;
import ch.ivy.addon.portalkit.util.HiddenTasksCasesConfig;
import org.apache.commons.lang3.StringUtils;
import ch.ivy.addon.portalkit.enums.AdditionalProperty;
import ch.ivyteam.ivy.workflow.TaskState;
import ch.ivyteam.ivy.workflow.ITask;

in.relatedTasks.clear();

int count = 1;
in.totalRelatedTasks = 0;
boolean excludeHiddenTasks = Boolean.parseBoolean(ivy.var.get(HiddenTasksCasesConfig.PORTAL_HIDDEN_TASK_CASE_EXCLUDED));
ISession session = ivy.session;
boolean isOwner = in.iCase.#owner != null ? in.iCase.getOwner().isMember(ivy.session, true) : false;
boolean ableToSeeAllRelatedTaskOfCase = PermissionUtils.checkReadAllTasksPermission() || PermissionUtils.checkTaskReadOwnCaseTasksPermission() || isOwner;
for (ITask task : in.iCase.getTasks()) {
	if ((task.getState() == TaskState.SUSPENDED || task.getState() == TaskState.RESUMED || task.getState() == TaskState.PARKED || task.getState() == TaskState.CREATED)
				&& (excludeHiddenTasks ? StringUtils.isEmpty(task.customFields().stringField(AdditionalProperty.HIDE.toString()).getOrNull()) : true) && task.isPersistent()) {
		if (ableToSeeAllRelatedTaskOfCase) {
			in.totalRelatedTasks++;
			if (count <= 21) {//get only 21 tasks
					in.relatedTasks.add(task);
			}
			count++;
		} else {
			if(task.canUserResumeTask(session).wasSuccessful()) {
				in.totalRelatedTasks++;
				if (count <= 21) {//get only 21 tasks
						in.relatedTasks.add(task);
				}
				count++;
			}
		}
	}
}
' #txt
Cs0 f14 security system #txt
Cs0 f14 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Find related tasks</name>
    </language>
</elementInfo>
' #txt
Cs0 f14 336 170 112 44 -49 -8 #rect
Cs0 f14 @|StepIcon #fIcon
Cs0 f15 expr out1 #txt
Cs0 f15 208 192 336 192 #arcP
Cs0 f13 expr out #txt
Cs0 f13 type ch.ivy.addon.portalkit.component.CaseItemRelatedTask.CaseItemRelatedTaskData #txt
Cs0 f13 var in1 #txt
Cs0 f13 448 192 632 192 #arcP
Cs0 f17 actionTable 'out=in;
' #txt
Cs0 f17 actionCode 'import ch.ivy.addon.portalkit.util.PermissionUtils;
import ch.ivy.addon.portalkit.service.RegisteredApplicationService;
import ch.ivyteam.ivy.workflow.CaseState;
import java.util.Arrays;

String currentUser = ivy.session.getSessionUserName();
out.caseSearchCriteria.businessCaseId = in.iCase.getId();
out.caseSearchCriteria.setIncludedStates(Arrays.asList(CaseState.CREATED, CaseState.RUNNING, CaseState.DONE));
out.caseSearchCriteria.technicalCase = true;
out.caseSearchCriteria.involvedUsername = currentUser;
out.caseSearchCriteria.apps = new RegisteredApplicationService().findActiveIvyAppsBasedOnConfiguration(currentUser);
out.caseSearchCriteria.adminQuery = PermissionUtils.checkReadAllCasesPermission();' #txt
Cs0 f17 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Prepare case&#xD;
search criteria</name>
    </language>
</elementInfo>
' #txt
Cs0 f17 240 266 112 44 -39 -16 #rect
Cs0 f17 @|StepIcon #fIcon
Cs0 f18 expr out2 #txt
Cs0 f18 192 208 240 288 #arcP
Cs0 f18 1 192 288 #addKink
Cs0 f18 1 0.08437593569205071 0 0 #arcLabel
Cs0 f19 processCall 'Ivy Data Processes/CaseService:findCasesByCriteria(ch.ivy.addon.portalkit.ivydata.searchcriteria.CaseSearchCriteria,Integer,Integer)' #txt
Cs0 f19 requestActionDecl '<ch.ivy.addon.portalkit.ivydata.searchcriteria.CaseSearchCriteria caseSearchCriteria,Integer startIndex,Integer count> param;' #txt
Cs0 f19 requestMappingAction 'param.caseSearchCriteria=in.caseSearchCriteria;
param.startIndex=0;
param.count=21;
' #txt
Cs0 f19 responseActionDecl 'ch.ivy.addon.portalkit.component.CaseItemRelatedTask.CaseItemRelatedTaskData out;
' #txt
Cs0 f19 responseMappingAction 'out=in;
out.technicalCases=result.cases;
' #txt
Cs0 f19 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>CaseService</name>
    </language>
</elementInfo>
' #txt
Cs0 f19 408 266 112 44 -35 -8 #rect
Cs0 f19 @|CallSubIcon #fIcon
Cs0 f20 expr out #txt
Cs0 f20 352 288 408 288 #arcP
Cs0 f23 processCall 'Functional Processes/Navigator:viewCase(ch.ivy.addon.portalkit.dto.GlobalCaseId)' #txt
Cs0 f23 requestActionDecl '<ch.ivy.addon.portalkit.dto.GlobalCaseId caseId> param;' #txt
Cs0 f23 requestMappingAction 'param.caseId=ch.ivy.addon.portalkit.dto.GlobalCaseId.caseId(in.selectedTechnicalCase.getId()).isBusinessCase(in.selectedTechnicalCase.isBusinessCase()).build();
' #txt
Cs0 f23 responseActionDecl 'ch.ivy.addon.portalkit.component.CaseItemRelatedTask.CaseItemRelatedTaskData out;
' #txt
Cs0 f23 responseMappingAction 'out=in;
' #txt
Cs0 f23 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Navigate to case detail</name>
    </language>
</elementInfo>
' #txt
Cs0 f23 696 674 128 44 -61 -8 #rect
Cs0 f23 @|CallSubIcon #fIcon
Cs0 f26 processCall 'Functional Processes/Navigator:viewTechnicalCasesOfBusniessCase(String,ch.ivy.addon.portalkit.dto.GlobalCaseId)' #txt
Cs0 f26 requestActionDecl '<String businessCaseName,ch.ivy.addon.portalkit.dto.GlobalCaseId businessCaseId> param;' #txt
Cs0 f26 requestMappingAction 'param.businessCaseName=ch.ivy.addon.portalkit.util.PermissionUtils.getCaseName(in.selectedTechnicalCase);
param.businessCaseId=ch.ivy.addon.portalkit.dto.GlobalCaseId.caseId(in.selectedTechnicalCase.getId()).isBusinessCase(in.selectedTechnicalCase.isBusinessCase()).build();
' #txt
Cs0 f26 responseActionDecl 'ch.ivy.addon.portalkit.component.CaseItemRelatedTask.CaseItemRelatedTaskData out;
' #txt
Cs0 f26 responseMappingAction 'out=in;
' #txt
Cs0 f26 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Navigate to case detail</name>
    </language>
</elementInfo>
' #txt
Cs0 f26 696 570 128 44 -61 -8 #rect
Cs0 f26 @|CallSubIcon #fIcon
Cs0 f28 processCall 'Ivy Data Processes/CaseService:countCasesByCriteria(ch.ivy.addon.portalkit.ivydata.searchcriteria.CaseSearchCriteria)' #txt
Cs0 f28 requestActionDecl '<ch.ivy.addon.portalkit.ivydata.searchcriteria.CaseSearchCriteria caseSearchCriteria> param;' #txt
Cs0 f28 requestMappingAction 'param.caseSearchCriteria=in.caseSearchCriteria;
' #txt
Cs0 f28 responseActionDecl 'ch.ivy.addon.portalkit.component.CaseItemRelatedTask.CaseItemRelatedTaskData out;
' #txt
Cs0 f28 responseMappingAction 'out=in;
out.totalRelatedCases=result.totalCases.intValue();
' #txt
Cs0 f28 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Count technical cases</name>
    </language>
</elementInfo>
' #txt
Cs0 f28 584 266 128 44 -61 -8 #rect
Cs0 f28 @|CallSubIcon #fIcon
Cs0 f29 expr out #txt
Cs0 f29 520 288 584 288 #arcP
Cs0 f29 0 NaN 0 0 #arcLabel
Cs0 f21 expr out #txt
Cs0 f21 var in2 #txt
Cs0 f21 648 266 648 208 #arcP
Cs0 f21 0 0.8623545764087797 0 0 #arcLabel
Cs0 f11 actionTable 'out=in;
' #txt
Cs0 f11 actionCode 'import org.apache.commons.lang3.StringUtils;
import ch.ivy.addon.portalkit.service.GlobalSettingService;
import ch.ivy.addon.portalkit.enums.GlobalVariable;
import javax.faces.context.Flash;
import javax.faces.context.FacesContext;
import javax.faces.application.FacesMessage;

if(in.isWorkingOnTask) {
	String displayMessageAfterFinishOrLeaveTaskVariable = new GlobalSettingService().findGlobalSettingValue(GlobalVariable.DISPLAY_MESSAGE_AFTER_FINISH_TASK.toString());
	boolean displayMessageAfterFinishOrLeaveTask = StringUtils.isNotBlank(displayMessageAfterFinishOrLeaveTaskVariable) ? Boolean.parseBoolean(displayMessageAfterFinishOrLeaveTaskVariable) : true;
	if (displayMessageAfterFinishOrLeaveTask && !ivy.session.isSessionUserUnknown()) {
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		if (!flash.containsKey("overridePortalGrowl")) {
			FacesMessage message = new FacesMessage(ivy.cms.co(in.isTaskFinished ? "/ch.ivy.addon.portalkit.ui.jsf/common/taskFinishedSuccessfully":"/ch.ivy.addon.portalkit.ui.jsf/common/taskCanceledAndLeftSuccessfully"));
			FacesContext.getCurrentInstance().addMessage("portal-global-growl-message", message);
		}
		flash.setRedirect(true);
		flash.setKeepMessages(true);
	}
}
' #txt
Cs0 f11 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Display message after&#13;
finish or leave task</name>
    </language>
</elementInfo>
' #txt
Cs0 f11 176 362 144 44 -54 -16 #rect
Cs0 f11 @|StepIcon #fIcon
Cs0 f33 actionTable 'out=in;
' #txt
Cs0 f33 actionCode 'import ch.ivy.addon.portalkit.util.TaskUtils;
import org.apache.commons.lang3.StringUtils;
import ch.ivy.addon.portalkit.service.GlobalSettingService;
import ch.ivy.addon.portalkit.enums.GlobalVariable;
import javax.faces.context.Flash;
import javax.faces.context.FacesContext;
import javax.faces.application.FacesMessage;

if(in.isWorkingOnTask) {
	String displayMessageAfterFinishOrLeaveTaskVariable = new GlobalSettingService().findGlobalSettingValue(GlobalVariable.DISPLAY_MESSAGE_AFTER_FINISH_TASK.toString());
	boolean displayMessageAfterFinishOrLeaveTask = StringUtils.isNotBlank(displayMessageAfterFinishOrLeaveTaskVariable) ? Boolean.parseBoolean(displayMessageAfterFinishOrLeaveTaskVariable) : true;
	if (displayMessageAfterFinishOrLeaveTask && !ivy.session.isSessionUserUnknown()) {
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		if (!flash.containsKey("overridePortalGrowl")) {
			FacesMessage message = new FacesMessage(in.isTaskFinished ? ivy.cms.co("/ch.ivy.addon.portalkit.ui.jsf/common/taskFinishedSuccessfully") : ivy.cms.co("/ch.ivy.addon.portalkit.ui.jsf/common/taskCanceledAndLeftSuccessfully"));
			FacesContext.getCurrentInstance().addMessage("portal-global-growl-message", message);
		}
		flash.setRedirect(true);
		flash.setKeepMessages(true);
	}
}

TaskUtils.updateTaskStartedAttribute(false);' #txt
Cs0 f33 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Display message after&#13;
finish or leave task</name>
    </language>
</elementInfo>
' #txt
Cs0 f33 176 466 144 44 -54 -16 #rect
Cs0 f33 @|StepIcon #fIcon
Cs0 f35 actionTable 'out=in;
' #txt
Cs0 f35 actionCode 'import org.apache.commons.lang3.StringUtils;
import ch.ivy.addon.portalkit.service.GlobalSettingService;
import ch.ivy.addon.portalkit.enums.GlobalVariable;
import javax.faces.context.Flash;
import javax.faces.context.FacesContext;
import javax.faces.application.FacesMessage;

if(in.isWorkingOnTask) {
	String displayMessageAfterFinishOrLeaveTaskVariable = new GlobalSettingService().findGlobalSettingValue(GlobalVariable.DISPLAY_MESSAGE_AFTER_FINISH_TASK.toString());
	boolean displayMessageAfterFinishOrLeaveTask = StringUtils.isNotBlank(displayMessageAfterFinishOrLeaveTaskVariable) ? Boolean.parseBoolean(displayMessageAfterFinishOrLeaveTaskVariable) : true;
	if (displayMessageAfterFinishOrLeaveTask && !ivy.session.isSessionUserUnknown()) {
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		if (!flash.containsKey("overridePortalGrowl")) {
			FacesMessage message = new FacesMessage(ivy.cms.co(in.isTaskFinished ? "/ch.ivy.addon.portalkit.ui.jsf/common/taskFinishedSuccessfully":"/ch.ivy.addon.portalkit.ui.jsf/common/taskCanceledAndLeftSuccessfully"));
			FacesContext.getCurrentInstance().addMessage("portal-global-growl-message", message);
		}
		flash.setRedirect(true);
		flash.setKeepMessages(true);
	}
}' #txt
Cs0 f35 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Display message after&#13;
finish or leave task</name>
    </language>
</elementInfo>
' #txt
Cs0 f35 264 570 144 44 -54 -16 #rect
Cs0 f35 @|StepIcon #fIcon
Cs0 f37 actionTable 'out=in;
' #txt
Cs0 f37 actionCode 'import org.apache.commons.lang3.StringUtils;
import ch.ivy.addon.portalkit.service.GlobalSettingService;
import ch.ivy.addon.portalkit.enums.GlobalVariable;
import javax.faces.context.Flash;
import javax.faces.context.FacesContext;
import javax.faces.application.FacesMessage;

if(in.isWorkingOnTask) {
	String displayMessageAfterFinishOrLeaveTaskVariable = new GlobalSettingService().findGlobalSettingValue(GlobalVariable.DISPLAY_MESSAGE_AFTER_FINISH_TASK.toString());
	boolean displayMessageAfterFinishOrLeaveTask = StringUtils.isNotBlank(displayMessageAfterFinishOrLeaveTaskVariable) ? Boolean.parseBoolean(displayMessageAfterFinishOrLeaveTaskVariable) : true;
	if (displayMessageAfterFinishOrLeaveTask && !ivy.session.isSessionUserUnknown()) {
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		if (!flash.containsKey("overridePortalGrowl")) {
			FacesMessage message = new FacesMessage(in.isTaskFinished ? ivy.cms.co("/ch.ivy.addon.portalkit.ui.jsf/common/taskFinishedSuccessfully") : ivy.cms.co("/ch.ivy.addon.portalkit.ui.jsf/common/taskCanceledAndLeftSuccessfully"));
			FacesContext.getCurrentInstance().addMessage("portal-global-growl-message", message);
		}
		flash.setRedirect(true);
		flash.setKeepMessages(true);
	}
}' #txt
Cs0 f37 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Display message after&#13;
finish or leave task</name>
    </language>
</elementInfo>
' #txt
Cs0 f37 264 674 144 44 -54 -16 #rect
Cs0 f37 @|StepIcon #fIcon
Cs0 f4 actionTable 'out=in;
' #txt
Cs0 f4 actionCode 'import ch.ivy.addon.portalkit.util.PermissionUtils;
import ch.ivy.addon.portal.generic.navigation.PortalNavigator;

PortalNavigator navigator = new PortalNavigator();
navigator.navigateToPortalRelatedTasksOfCase(in.iCase.getId(), true, PermissionUtils.getCaseName(in.iCase));' #txt
Cs0 f4 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Navigate to task list</name>
    </language>
</elementInfo>
' #txt
Cs0 f4 392 362 112 44 -50 -8 #rect
Cs0 f4 @|StepIcon #fIcon
Cs0 f16 320 384 392 384 #arcP
Cs0 f31 actionTable 'out=in;
' #txt
Cs0 f31 actionCode 'import ch.ivy.addon.portal.generic.navigation.PortalNavigator;

PortalNavigator navigator = new PortalNavigator();
navigator.navigateToPortalTaskDetails(in.taskId);' #txt
Cs0 f31 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Navigate to task details</name>
    </language>
</elementInfo>
' #txt
Cs0 f31 384 464 176 48 -59 -8 #rect
Cs0 f31 @|StepIcon #fIcon
Cs0 f39 actionTable 'out=in;
' #txt
Cs0 f39 actionCode 'import ch.ivy.addon.portalkit.util.TaskUtils;
import ch.ivy.addon.portalkit.enums.SessionAttribute;

ivy.session.setAttribute(SessionAttribute.NAVIGATE_FROM_RELATED_CASE.toString(), "true");
TaskUtils.updateTaskStartedAttribute(false);' #txt
Cs0 f39 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>mark navigate from related list</name>
    </language>
</elementInfo>
' #txt
Cs0 f39 456 570 176 44 -82 -8 #rect
Cs0 f39 @|StepIcon #fIcon
Cs0 f41 408 592 456 592 #arcP
Cs0 f36 320 488 384 488 #arcP
Cs0 f40 632 592 696 592 #arcP
Cs0 f34 actionTable 'out=in;
' #txt
Cs0 f34 actionCode 'import ch.ivy.addon.portalkit.util.TaskUtils;
import ch.ivy.addon.portalkit.enums.SessionAttribute;

ivy.session.setAttribute(SessionAttribute.NAVIGATE_FROM_RELATED_CASE.toString(), "true");
TaskUtils.updateTaskStartedAttribute(false);' #txt
Cs0 f34 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>mark navigate from related list</name>
    </language>
</elementInfo>
' #txt
Cs0 f34 456 674 176 44 -82 -8 #rect
Cs0 f34 @|StepIcon #fIcon
Cs0 f42 408 696 456 696 #arcP
Cs0 f38 632 696 696 696 #arcP
Cs0 f45 guid 1720C62D9BF92EB1 #txt
Cs0 f45 method navigateToSelectedTaskDetails(Long,Boolean) #txt
Cs0 f45 inParameterDecl '<Long taskId,Boolean isWorkingOnTask> param;' #txt
Cs0 f45 inParameterMapAction 'out.isWorkingOnTask=param.isWorkingOnTask;
out.taskId=param.taskId;
' #txt
Cs0 f45 outParameterDecl '<> result;' #txt
Cs0 f45 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>navigateToSelectedTaskDetails(Long,Boolean)</name>
    </language>
</elementInfo>
' #txt
Cs0 f45 83 475 26 26 -77 20 #rect
Cs0 f45 @|UdMethodIcon #fIcon
Cs0 f47 109 488 176 488 #arcP
Cs0 f47 0 0.5541990710792534 0 0 #arcLabel
Cs0 f44 109 384 176 384 #arcP
Cs0 f44 0 0.5658938645158399 0 0 #arcLabel
Cs0 f43 guid 1720C5CCA7DA3932 #txt
Cs0 f43 method navigateToRelatedTasksOfCase(Boolean) #txt
Cs0 f43 inParameterDecl '<Boolean isWorkingOnTask> param;' #txt
Cs0 f43 inParameterMapAction 'out.isWorkingOnTask=param.isWorkingOnTask;
' #txt
Cs0 f43 outParameterDecl '<> result;' #txt
Cs0 f43 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>navigateToRelatedTasksOfCase(Boolean)</name>
    </language>
</elementInfo>
' #txt
Cs0 f43 83 371 26 26 -78 23 #rect
Cs0 f43 @|UdMethodIcon #fIcon
Cs0 f2 guid 1720CC0AEC970FA8 #txt
Cs0 f2 method navigateToTechnicalCasesOfBusinessCase(ch.ivyteam.ivy.workflow.ICase,Boolean) #txt
Cs0 f2 inParameterDecl '<ch.ivyteam.ivy.workflow.ICase selectedTechnicalCase,Boolean isWorkingOnTask> param;' #txt
Cs0 f2 inParameterMapAction 'out.isWorkingOnTask=param.isWorkingOnTask;
out.selectedTechnicalCase=param.selectedTechnicalCase;
' #txt
Cs0 f2 outParameterDecl '<> result;' #txt
Cs0 f2 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>navigateToTechnicalCasesOfBusinessCase(ICase,Boolean)</name>
    </language>
</elementInfo>
' #txt
Cs0 f2 83 579 26 26 -90 19 #rect
Cs0 f2 @|UdMethodIcon #fIcon
Cs0 f5 109 592 264 592 #arcP
Cs0 f25 guid 1720CC59596D550D #txt
Cs0 f25 method navigateToRelatedCaseDetails(ch.ivyteam.ivy.workflow.ICase,Boolean) #txt
Cs0 f25 inParameterDecl '<ch.ivyteam.ivy.workflow.ICase selectedTechnicalCase,Boolean isWorkingOnTask> param;' #txt
Cs0 f25 inParameterMapAction 'out.isWorkingOnTask=param.isWorkingOnTask;
out.selectedTechnicalCase=param.selectedTechnicalCase;
' #txt
Cs0 f25 outParameterDecl '<> result;' #txt
Cs0 f25 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>navigateToRelatedCaseDetails(ICase,Boolean)</name>
    </language>
</elementInfo>
' #txt
Cs0 f25 83 683 26 26 -78 20 #rect
Cs0 f25 @|UdMethodIcon #fIcon
Cs0 f27 109 696 264 696 #arcP
>Proto Cs0 .type ch.ivy.addon.portalkit.component.CaseItemRelatedTask.CaseItemRelatedTaskData #txt
>Proto Cs0 .processKind HTML_DIALOG #txt
>Proto Cs0 -8 -8 16 16 16 26 #rect
>Proto Cs0 '' #fIcon
Cs0 f0 mainOut f3 tail #connect
Cs0 f3 head f1 mainIn #connect
Cs0 f9 mainOut f10 tail #connect
Cs0 f10 head f7 mainIn #connect
Cs0 f6 mainOut f12 tail #connect
Cs0 f12 head f8 in #connect
Cs0 f8 out f15 tail #connect
Cs0 f15 head f14 mainIn #connect
Cs0 f14 mainOut f13 tail #connect
Cs0 f13 head f9 in #connect
Cs0 f8 out f18 tail #connect
Cs0 f18 head f17 mainIn #connect
Cs0 f17 mainOut f20 tail #connect
Cs0 f20 head f19 mainIn #connect
Cs0 f19 mainOut f29 tail #connect
Cs0 f29 head f28 mainIn #connect
Cs0 f28 mainOut f21 tail #connect
Cs0 f21 head f9 in #connect
Cs0 f11 mainOut f16 tail #connect
Cs0 f16 head f4 mainIn #connect
Cs0 f35 mainOut f41 tail #connect
Cs0 f41 head f39 mainIn #connect
Cs0 f33 mainOut f36 tail #connect
Cs0 f36 head f31 mainIn #connect
Cs0 f39 mainOut f40 tail #connect
Cs0 f40 head f26 mainIn #connect
Cs0 f37 mainOut f42 tail #connect
Cs0 f42 head f34 mainIn #connect
Cs0 f34 mainOut f38 tail #connect
Cs0 f38 head f23 mainIn #connect
Cs0 f43 mainOut f44 tail #connect
Cs0 f44 head f11 mainIn #connect
Cs0 f45 mainOut f47 tail #connect
Cs0 f47 head f33 mainIn #connect
Cs0 f2 mainOut f5 tail #connect
Cs0 f5 head f35 mainIn #connect
Cs0 f25 mainOut f27 tail #connect
Cs0 f27 head f37 mainIn #connect
