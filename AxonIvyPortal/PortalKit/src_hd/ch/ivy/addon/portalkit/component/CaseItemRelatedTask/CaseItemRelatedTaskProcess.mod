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
Cs0 @UdEvent f5 '' #zField
Cs0 @CallSub f4 '' #zField
Cs0 @UdEvent f22 '' #zField
Cs0 @CallSub f23 '' #zField
Cs0 @UdEvent f25 '' #zField
Cs0 @CallSub f26 '' #zField
Cs0 @CallSub f28 '' #zField
Cs0 @PushWFArc f29 '' #zField
Cs0 @SJArc f21 '' #zField
Cs0 @CallSub f31 '' #zField
Cs0 @UdEvent f30 '' #zField
Cs0 @GridStep f39 '' #zField
Cs0 @GridStep f41 '' #zField
Cs0 @GridStep f45 '' #zField
Cs0 @UdProcessEnd f46 '' #zField
Cs0 @PushWFArc f48 '' #zField
Cs0 @GridStep f2 '' #zField
Cs0 @PushWFArc f52 '' #zField
Cs0 @GridStep f53 '' #zField
Cs0 @Alternative f58 '' #zField
Cs0 @GridStep f32 '' #zField
Cs0 @PushWFArc f34 '' #zField
Cs0 @GridStep f40 '' #zField
Cs0 @PushWFArc f42 '' #zField
Cs0 @GridStep f47 '' #zField
Cs0 @PushWFArc f65 '' #zField
Cs0 @PushWFArc f27 '' #zField
Cs0 @PushWFArc f64 '' #zField
Cs0 @PushWFArc f24 '' #zField
Cs0 @PushWFArc f36 '' #zField
Cs0 @PushWFArc f43 '' #zField
Cs0 @PushWFArc f16 '' #zField
Cs0 @PushWFArc f11 '' #zField
Cs0 @PushWFArc f33 '' #zField
Cs0 @PushWFArc f35 '' #zField
Cs0 @PushWFArc f37 '' #zField
Cs0 @PushWFArc f44 '' #zField
Cs0 @PushWFArc f50 '' #zField
Cs0 @UdMethod f38 '' #zField
Cs0 @PushWFArc f59 '' #zField
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
ISession session = ServiceUtilities.findUserWorkflowSession(ivy.session.getSessionUserName(), in.iCase.getApplication());
boolean isOwner = in.iCase.#owner != null ? in.iCase.getOwner().isMember(ivy.session, true) : false;
boolean ableToSeeAllRelatedTaskOfCase = PermissionUtils.checkReadAllTasksPermission() || PermissionUtils.checkTaskReadOwnCaseTasksPermission() || isOwner;
for (ITask task : in.iCase.getTasks()) {
	if ((task.getState() == TaskState.SUSPENDED || task.getState() == TaskState.RESUMED || task.getState() == TaskState.UNASSIGNED || task.getState() == TaskState.PARKED || task.getState() == TaskState.CREATED)
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
Cs0 f5 guid 1682B962CA36E3CC #txt
Cs0 f5 actionTable 'out=in;
' #txt
Cs0 f5 actionCode 'import org.primefaces.component.commandlink.CommandLink;
CommandLink commandLink = event.getSource() as CommandLink;
in.taskId = commandLink.getAttributes().get("taskId") as Long;
out.isWorkingOnTask = commandLink.getAttributes().get("isWorkingOnTask") as Boolean;' #txt
Cs0 f5 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>openPortalTasks</name>
    </language>
</elementInfo>
' #txt
Cs0 f5 83 659 26 26 -47 15 #rect
Cs0 f5 @|UdEventIcon #fIcon
Cs0 f4 processCall 'Functional Processes/Navigator:viewTask(Long,ch.ivy.addon.portalkit.dto.GlobalCaseId,String)' #txt
Cs0 f4 requestActionDecl '<Long taskId,ch.ivy.addon.portalkit.dto.GlobalCaseId caseId,String caseName> param;' #txt
Cs0 f4 requestMappingAction 'param.taskId=in.taskId;
param.caseId=ch.ivy.addon.portalkit.dto.GlobalCaseId.caseId(in.iCase.getId()).isBusinessCase(in.iCase.isBusinessCase()).build();
param.caseName=ch.ivy.addon.portalkit.util.PermissionUtils.getCaseName(in.iCase);
' #txt
Cs0 f4 responseActionDecl 'ch.ivy.addon.portalkit.component.CaseItemRelatedTask.CaseItemRelatedTaskData out;
' #txt
Cs0 f4 responseMappingAction 'out=in;
' #txt
Cs0 f4 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Navigate to task list</name>
    </language>
</elementInfo>
' #txt
Cs0 f4 936 650 112 44 -50 -8 #rect
Cs0 f4 @|CallSubIcon #fIcon
Cs0 f22 guid 1682B96F9503687C #txt
Cs0 f22 actionTable 'out=in;
' #txt
Cs0 f22 actionCode 'import ch.ivyteam.ivy.workflow.ICase;
import org.primefaces.component.commandlink.CommandLink;

CommandLink commandLink = event.getSource() as CommandLink;
out.selectedTechnicalCase = commandLink.getAttributes().get("selectedCase") as ICase;
out.isWorkingOnTask = commandLink.getAttributes().get("isWorkingOnTask") as Boolean;' #txt
Cs0 f22 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>navigateToRelatedCaseDetails</name>
    </language>
</elementInfo>
' #txt
Cs0 f22 83 563 26 26 -47 15 #rect
Cs0 f22 @|UdEventIcon #fIcon
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
        <name>Navigate to related cases</name>
    </language>
</elementInfo>
' #txt
Cs0 f23 936 554 144 44 -69 -8 #rect
Cs0 f23 @|CallSubIcon #fIcon
Cs0 f25 guid 1690E16A5D3AD46C #txt
Cs0 f25 actionTable 'out=in;
' #txt
Cs0 f25 actionCode 'import ch.ivyteam.ivy.workflow.ICase;
import org.primefaces.component.commandlink.CommandLink;

CommandLink commandLink = event.getSource() as CommandLink;
out.selectedTechnicalCase = commandLink.getAttributes().get("selectedCase") as ICase;
out.isWorkingOnTask = commandLink.getAttributes().get("isWorkingOnTask") as Boolean;' #txt
Cs0 f25 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>navigateToTechnicalCasesOfBusinessCase</name>
    </language>
</elementInfo>
' #txt
Cs0 f25 83 467 26 26 -88 17 #rect
Cs0 f25 @|UdEventIcon #fIcon
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
        <name>Navigate to technical case detail</name>
    </language>
</elementInfo>
' #txt
Cs0 f26 936 458 192 44 -88 -8 #rect
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
Cs0 f31 processCall 'Functional Processes/Navigator:viewRelatedTask(Long)' #txt
Cs0 f31 requestActionDecl '<Long taskId> param;' #txt
Cs0 f31 requestMappingAction 'param.taskId=in.taskId;
' #txt
Cs0 f31 responseActionDecl 'ch.ivy.addon.portalkit.component.CaseItemRelatedTask.CaseItemRelatedTaskData out;
' #txt
Cs0 f31 responseMappingAction 'out=in;
' #txt
Cs0 f31 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Navigator to related task</name>
    </language>
</elementInfo>
' #txt
Cs0 f31 936 362 144 44 -66 -8 #rect
Cs0 f31 @|CallSubIcon #fIcon
Cs0 f30 guid 16D8A8E9B8ABB666 #txt
Cs0 f30 actionTable 'out=in;
' #txt
Cs0 f30 actionCode 'import ch.ivyteam.ivy.workflow.ICase;
import org.primefaces.component.commandlink.CommandLink;

CommandLink commandLink = event.getSource() as CommandLink;
out.taskId = commandLink.getAttributes().get("taskId") as Long;
out.isWorkingOnTask = commandLink.getAttributes().get("isWorkingOnTask") as Boolean;' #txt
Cs0 f30 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>navigateToRelatedTaskDetails</name>
    </language>
</elementInfo>
' #txt
Cs0 f30 83 371 26 26 -72 20 #rect
Cs0 f30 @|UdEventIcon #fIcon
Cs0 f39 actionTable 'out=in;
' #txt
Cs0 f39 actionCode 'import ch.ivy.addon.portalkit.enums.SessionAttribute;
import ch.ivy.addon.portalkit.util.TaskUtils;

ivy.session.setAttribute(SessionAttribute.NAVIGATE_FROM_RELATED_CASE.toString(), "true");
TaskUtils.updateTaskStartedAttribute(false);' #txt
Cs0 f39 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>mark navigate &#13;
from related list</name>
    </language>
</elementInfo>
' #txt
Cs0 f39 464 458 128 44 -41 -16 #rect
Cs0 f39 @|StepIcon #fIcon
Cs0 f41 actionTable 'out=in;
' #txt
Cs0 f41 actionCode 'import ch.ivy.addon.portalkit.util.TaskUtils;
import ch.ivy.addon.portalkit.enums.SessionAttribute;

ivy.session.setAttribute(SessionAttribute.NAVIGATE_FROM_RELATED_CASE.toString(), "true");
TaskUtils.updateTaskStartedAttribute(false);' #txt
Cs0 f41 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>mark navigate &#13;
from related list</name>
    </language>
</elementInfo>
' #txt
Cs0 f41 464 554 128 44 -41 -16 #rect
Cs0 f41 @|StepIcon #fIcon
Cs0 f45 actionTable 'out=in;
' #txt
Cs0 f45 actionCode 'import org.primefaces.PrimeFaces;

PrimeFaces.current().executeScript("PF(''leave-case-info-warning-dialog'').show()");' #txt
Cs0 f45 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Show warning dialog</name>
    </language>
</elementInfo>
' #txt
Cs0 f45 936 746 128 44 -57 -8 #rect
Cs0 f45 @|StepIcon #fIcon
Cs0 f46 1147 755 26 26 0 12 #rect
Cs0 f46 @|UdProcessEndIcon #fIcon
Cs0 f48 1064 768 1147 768 #arcP
Cs0 f2 actionTable 'out=in;
' #txt
Cs0 f2 actionCode 'import ch.ivy.addon.portalkit.enums.CaseNavigationHistory;
in.navigationHistory = CaseNavigationHistory.RELATED_TASKS;' #txt
Cs0 f2 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>store navigate history</name>
    </language>
</elementInfo>
' #txt
Cs0 f2 232 650 128 44 -58 -8 #rect
Cs0 f2 @|StepIcon #fIcon
Cs0 f52 109 672 232 672 #arcP
Cs0 f53 actionTable 'out=in;
' #txt
Cs0 f53 actionCode 'import org.apache.commons.lang3.BooleanUtils;
import ch.ivy.addon.portalkit.service.StickyTaskListService;
import ch.ivy.addon.portalkit.util.SecurityServiceUtils;
import ch.ivy.addon.portalkit.enums.SessionAttribute;

in.isWorkingOnTask = false;
SecurityServiceUtils.setSessionAttribute(SessionAttribute.NAVIGATE_TO_CASE_LIST.toString(), true);' #txt
Cs0 f53 security system #txt
Cs0 f53 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>reset isWorkingOnTask indicator</name>
    </language>
</elementInfo>
' #txt
Cs0 f53 232 746 192 44 -89 -8 #rect
Cs0 f53 @|StepIcon #fIcon
Cs0 f58 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>is working on task?</name>
    </language>
</elementInfo>
' #txt
Cs0 f58 728 560 32 32 -37 20 #rect
Cs0 f58 @|AlternativeIcon #fIcon
Cs0 f32 actionTable 'out=in;
' #txt
Cs0 f32 actionCode 'import ch.ivy.addon.portalkit.enums.CaseNavigationHistory;
in.navigationHistory = CaseNavigationHistory.TASK_DETAILS;' #txt
Cs0 f32 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>store navigate history</name>
    </language>
</elementInfo>
' #txt
Cs0 f32 232 362 128 44 -58 -8 #rect
Cs0 f32 @|StepIcon #fIcon
Cs0 f34 109 384 232 384 #arcP
Cs0 f40 actionTable 'out=in;
' #txt
Cs0 f40 actionCode 'import ch.ivy.addon.portalkit.enums.CaseNavigationHistory;
in.navigationHistory = CaseNavigationHistory.TECHNICAL_CASE_DETAILS;' #txt
Cs0 f40 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>store navigate history</name>
    </language>
</elementInfo>
' #txt
Cs0 f40 232 458 128 44 -58 -8 #rect
Cs0 f40 @|StepIcon #fIcon
Cs0 f42 109 480 232 480 #arcP
Cs0 f47 actionTable 'out=in;
' #txt
Cs0 f47 actionCode 'import ch.ivy.addon.portalkit.enums.CaseNavigationHistory;
in.navigationHistory = CaseNavigationHistory.TECHNICAL_CASES;' #txt
Cs0 f47 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>story navigate history</name>
    </language>
</elementInfo>
' #txt
Cs0 f47 232 554 128 44 -57 -8 #rect
Cs0 f47 @|StepIcon #fIcon
Cs0 f65 109 576 232 576 #arcP
Cs0 f27 360 576 464 576 #arcP
Cs0 f64 360 480 464 480 #arcP
Cs0 f24 592 480 738 566 #arcP
Cs0 f24 1 680 480 #addKink
Cs0 f24 1 8.89376781604952E-4 0 0 #arcLabel
Cs0 f36 360 384 740 564 #arcP
Cs0 f36 1 680 384 #addKink
Cs0 f36 1 0.1197715437851424 0 0 #arcLabel
Cs0 f43 360 672 738 586 #arcP
Cs0 f43 1 680 672 #addKink
Cs0 f43 1 0.2425665925194799 0 0 #arcLabel
Cs0 f16 592 576 728 576 #arcP
Cs0 f16 0 0.7095738145339603 0 0 #arcLabel
Cs0 f11 424 768 740 588 #arcP
Cs0 f11 1 680 768 #addKink
Cs0 f11 0 0.7661907995468817 0 0 #arcLabel
Cs0 f33 expr in #txt
Cs0 f33 outCond in.isWorkingOnTask #txt
Cs0 f33 749 587 936 768 #arcP
Cs0 f33 1 840 768 #addKink
Cs0 f35 expr in #txt
Cs0 f35 outCond '!in.isWorkingOnTask && in.navigationHistory == ch.ivy.addon.portalkit.enums.CaseNavigationHistory.RELATED_TASKS' #txt
Cs0 f35 752 584 936 672 #arcP
Cs0 f35 1 840 672 #addKink
Cs0 f37 expr in #txt
Cs0 f37 outCond '!in.isWorkingOnTask && in.navigationHistory == ch.ivy.addon.portalkit.enums.CaseNavigationHistory.TASK_DETAILS' #txt
Cs0 f37 749 565 936 384 #arcP
Cs0 f37 1 840 384 #addKink
Cs0 f44 expr in #txt
Cs0 f44 outCond '!in.isWorkingOnTask && in.navigationHistory == ch.ivy.addon.portalkit.enums.CaseNavigationHistory.TECHNICAL_CASE_DETAILS' #txt
Cs0 f44 752 568 936 480 #arcP
Cs0 f44 1 840 480 #addKink
Cs0 f50 expr in #txt
Cs0 f50 outCond !in.isWorkingOnTask #txt
Cs0 f50 760 576 936 576 #arcP
Cs0 f38 guid 174DDD33CDAA46B2 #txt
Cs0 f38 method leaveTask() #txt
Cs0 f38 inParameterDecl '<> param;' #txt
Cs0 f38 outParameterDecl '<> result;' #txt
Cs0 f38 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>leaveTask()</name>
    </language>
</elementInfo>
' #txt
Cs0 f38 83 755 26 26 -25 15 #rect
Cs0 f38 @|UdMethodIcon #fIcon
Cs0 f59 109 768 232 768 #arcP
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
Cs0 f45 mainOut f48 tail #connect
Cs0 f48 head f46 mainIn #connect
Cs0 f5 mainOut f52 tail #connect
Cs0 f52 head f2 mainIn #connect
Cs0 f30 mainOut f34 tail #connect
Cs0 f34 head f32 mainIn #connect
Cs0 f25 mainOut f42 tail #connect
Cs0 f42 head f40 mainIn #connect
Cs0 f22 mainOut f65 tail #connect
Cs0 f65 head f47 mainIn #connect
Cs0 f47 mainOut f27 tail #connect
Cs0 f27 head f41 mainIn #connect
Cs0 f40 mainOut f64 tail #connect
Cs0 f64 head f39 mainIn #connect
Cs0 f39 mainOut f24 tail #connect
Cs0 f24 head f58 in #connect
Cs0 f32 mainOut f36 tail #connect
Cs0 f36 head f58 in #connect
Cs0 f2 mainOut f43 tail #connect
Cs0 f43 head f58 in #connect
Cs0 f41 mainOut f16 tail #connect
Cs0 f16 head f58 in #connect
Cs0 f53 mainOut f11 tail #connect
Cs0 f11 head f58 in #connect
Cs0 f58 out f33 tail #connect
Cs0 f33 head f45 mainIn #connect
Cs0 f58 out f35 tail #connect
Cs0 f35 head f4 mainIn #connect
Cs0 f58 out f37 tail #connect
Cs0 f37 head f31 mainIn #connect
Cs0 f58 out f44 tail #connect
Cs0 f44 head f26 mainIn #connect
Cs0 f58 out f50 tail #connect
Cs0 f50 head f23 mainIn #connect
Cs0 f38 mainOut f59 tail #connect
Cs0 f59 head f53 mainIn #connect
