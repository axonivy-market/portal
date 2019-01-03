[Ivy]
15C9F795D7A23730 3.20 #module
>Proto >Proto Collection #zClass
Gh0 GlobalSearch Big #zClass
Gh0 B #cInfo
Gh0 #process
Gh0 @TextInP .resExport .resExport #zField
Gh0 @TextInP .type .type #zField
Gh0 @TextInP .processKind .processKind #zField
Gh0 @AnnotationInP-0n ai ai #zField
Gh0 @MessageFlowInP-0n messageIn messageIn #zField
Gh0 @MessageFlowOutP-0n messageOut messageOut #zField
Gh0 @TextInP .xml .xml #zField
Gh0 @TextInP .responsibility .responsibility #zField
Gh0 @StartSub f0 '' #zField
Gh0 @EndSub f1 '' #zField
Gh0 @Split f3 '' #zField
Gh0 @Join f5 '' #zField
Gh0 @GridStep f7 '' #zField
Gh0 @GridStep f8 '' #zField
Gh0 @GridStep f10 '' #zField
Gh0 @PushWFArc f11 '' #zField
Gh0 @PushWFArc f12 '' #zField
Gh0 @CallSub f13 '' #zField
Gh0 @CallSub f14 '' #zField
Gh0 @PushWFArc f15 '' #zField
Gh0 @CallSub f16 '' #zField
Gh0 @GridStep f17 '' #zField
Gh0 @PushWFArc f24 '' #zField
Gh0 @PushWFArc f2 '' #zField
Gh0 @SJArc f6 '' #zField
Gh0 @SJArc f18 '' #zField
Gh0 @SJArc f19 '' #zField
Gh0 @PushWFArc f25 '' #zField
Gh0 @PushWFArc f20 '' #zField
Gh0 @PushWFArc f21 '' #zField
Gh0 @PushWFArc f9 '' #zField
Gh0 @InfoButton f4 '' #zField
>Proto Gh0 Gh0 GlobalSearch #zField
Gh0 f0 inParamDecl '<java.lang.String keyword,java.lang.Long serverId,java.lang.String applicationName> param;' #txt
Gh0 f0 inParamTable 'out.applicationName=param.applicationName;
out.keyword=param.keyword;
out.serverId=param.#serverId;
' #txt
Gh0 f0 outParamDecl '<List<ch.ivy.addon.portalkit.bo.RemoteTask> tasks,List<ch.ivy.addon.portalkit.bo.RemoteCase> cases,List<ch.ivy.addon.portalkit.bo.RemoteWebStartable> webStartables> result;
' #txt
Gh0 f0 outParamTable 'result.tasks=in.tasks;
result.cases=in.cases;
result.webStartables=in.webStartables;
' #txt
Gh0 f0 actionDecl 'ch.ivy.addon.portal.generic.GlobalSearchData out;
' #txt
Gh0 f0 callSignature call(String,Long,String) #txt
Gh0 f0 type ch.ivy.addon.portal.generic.GlobalSearchData #txt
Gh0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>call(String,Long,String)</name>
        <nameStyle>24,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Gh0 f0 81 145 30 30 -62 17 #rect
Gh0 f0 @|StartSubIcon #fIcon
Gh0 f1 type ch.ivy.addon.portal.generic.GlobalSearchData #txt
Gh0 f1 849 145 30 30 0 15 #rect
Gh0 f1 @|EndSubIcon #fIcon
Gh0 f3 actionDecl 'ch.ivy.addon.portal.generic.GlobalSearchData out1;
ch.ivy.addon.portal.generic.GlobalSearchData out2;
ch.ivy.addon.portal.generic.GlobalSearchData out3;
' #txt
Gh0 f3 actionTable 'out1=in;
out2=in;
out3=in;
' #txt
Gh0 f3 type ch.ivy.addon.portal.generic.GlobalSearchData #txt
Gh0 f3 176 144 32 32 0 16 #rect
Gh0 f3 @|ThreadIcon #fIcon
Gh0 f5 actionDecl 'ch.ivy.addon.portal.generic.GlobalSearchData out;
' #txt
Gh0 f5 actionTable 'out=in1;
out.cases=in3.cases;
out.tasks=in2.tasks;
out.webStartables=in1.webStartables;
' #txt
Gh0 f5 752 144 32 32 0 16 #rect
Gh0 f5 @|JoinIcon #fIcon
Gh0 f7 actionDecl 'ch.ivy.addon.portal.generic.GlobalSearchData out;
' #txt
Gh0 f7 actionTable 'out=in;
' #txt
Gh0 f7 actionCode 'import java.util.Arrays;

in.webStartableSearchCriteria.keyword = in.keyword;
in.webStartableSearchCriteria.involvedUsername = ivy.session.getSessionUserName();

if (ivy.session.getSessionUser().getEMailLanguage() != null) {
	in.language = ivy.session.getSessionUser().getEMailLanguage().getLanguage();
}

if (in.#serverId is initialized) {
	in.webStartableSearchCriteria.serverId = in.serverId;
}

if (in.#applicationName is initialized) {
	List<String> involvedApplications = Arrays.asList(in.applicationName);
	in.webStartableSearchCriteria.involvedApplications = involvedApplications;
}' #txt
Gh0 f7 type ch.ivy.addon.portal.generic.GlobalSearchData #txt
Gh0 f7 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Prepare web startable
search criteria</name>
        <nameStyle>37
</nameStyle>
    </language>
</elementInfo>
' #txt
Gh0 f7 224 42 144 44 -54 -16 #rect
Gh0 f7 @|StepIcon #fIcon
Gh0 f8 actionDecl 'ch.ivy.addon.portal.generic.GlobalSearchData out;
' #txt
Gh0 f8 actionTable 'out=in;
' #txt
Gh0 f8 actionCode 'import ch.ivy.addon.portalkit.enums.TaskSortField;
import ch.ivy.addon.portalkit.enums.TaskAssigneeType;
import ch.ivy.addon.portalkit.util.PermissionUtils;
import ch.ivy.addon.portalkit.service.TaskQueryService;
import ch.ivy.addon.portalkit.support.TaskQueryCriteria;
import ch.ivyteam.ivy.workflow.TaskState;
import java.util.Arrays;


TaskQueryCriteria queryCriteria = new TaskQueryCriteria();
List<TaskState> states = new List<TaskState>();
states.add(TaskState.SUSPENDED);
states.add(TaskState.RESUMED);
states.add(TaskState.PARKED);
if (PermissionUtils.checkReadAllTasksPermission() == true){
	states.add(TaskState.UNASSIGNED);
	states.add(TaskState.DONE);
}
queryCriteria.includedStates = states;
queryCriteria.keyword = in.keyword;
queryCriteria.newQueryCreated = true;
queryCriteria.setSortField(TaskSortField.NAME.toString());
queryCriteria.setTaskAssigneeType(TaskAssigneeType.ALL);

in.taskSearchCriteria.jsonQuery = TaskQueryService.service().createQuery(queryCriteria).asJson();
in.taskSearchCriteria.setIgnoreInvolvedUser(PermissionUtils.checkReadAllTasksPermission());
in.taskSearchCriteria.involvedUsername = ivy.session.getSessionUserName();

if (in.#applicationName is initialized) {
	List<String> involvedApplications = Arrays.asList(in.applicationName);
	in.taskSearchCriteria.involvedApplications = involvedApplications;
}' #txt
Gh0 f8 type ch.ivy.addon.portal.generic.GlobalSearchData #txt
Gh0 f8 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Prepare task 
search criteria</name>
        <nameStyle>29,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Gh0 f8 240 138 112 44 -39 -16 #rect
Gh0 f8 @|StepIcon #fIcon
Gh0 f10 actionDecl 'ch.ivy.addon.portal.generic.GlobalSearchData out;
' #txt
Gh0 f10 actionTable 'out=in;
' #txt
Gh0 f10 actionCode 'import ch.ivy.addon.portalkit.service.CaseQueryService;
import ch.ivy.addon.portalkit.enums.CaseSortField;
import ch.ivy.addon.portalkit.support.CaseQueryCriteria;
import ch.ivyteam.ivy.workflow.CaseState;
import ch.ivy.addon.portalkit.util.PermissionUtils;
import java.util.Arrays;

in.caseSearchCriteria.involvedUsername = ivy.session.getSessionUserName();

List<CaseState> states = new List<CaseState>();
states.add(CaseState.CREATED);
states.add(CaseState.RUNNING);

in.caseSearchCriteria.setInvolvedUsername(ivy.session.getSessionUserName());
in.caseSearchCriteria.setBusinessCase(true);
in.caseSearchCriteria.setIgnoreInvolvedUser(PermissionUtils.checkReadAllCasesPermission());
if (PermissionUtils.checkReadAllCasesPermission() == true){
	states.add(CaseState.DONE);
}
if (in.#applicationName is initialized) {
	List<String> involvedApplications = Arrays.asList(in.applicationName);
	in.caseSearchCriteria.involvedApplications = involvedApplications;
}

CaseQueryCriteria queryCriteria = new CaseQueryCriteria();
queryCriteria.newQueryCreated = true;
queryCriteria.keyword = in.keyword;
queryCriteria.sortField = CaseSortField.NAME.toString();
queryCriteria.setIncludedStates(states);
in.caseSearchCriteria.jsonQuery = CaseQueryService.service().createQuery(queryCriteria).asJson();' #txt
Gh0 f10 type ch.ivy.addon.portal.generic.GlobalSearchData #txt
Gh0 f10 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Prepare case 
search criteria</name>
        <nameStyle>29,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Gh0 f10 240 234 112 44 -39 -16 #rect
Gh0 f10 @|StepIcon #fIcon
Gh0 f11 expr out2 #txt
Gh0 f11 208 160 240 160 #arcP
Gh0 f12 expr out3 #txt
Gh0 f12 192 176 240 256 #arcP
Gh0 f12 1 192 256 #addKink
Gh0 f12 1 0.04688882764461169 0 0 #arcLabel
Gh0 f13 type ch.ivy.addon.portal.generic.GlobalSearchData #txt
Gh0 f13 processCall MultiPortal/WebStartableService:findWebStartablesByCriteria(String,ch.ivy.ws.addon.WebStartableSearchCriteria) #txt
Gh0 f13 doCall true #txt
Gh0 f13 requestActionDecl '<java.lang.String language,ch.ivy.ws.addon.WebStartableSearchCriteria webStartableSearchCriteria> param;
' #txt
Gh0 f13 requestMappingAction 'param.language=in.language;
param.webStartableSearchCriteria=in.webStartableSearchCriteria;
' #txt
Gh0 f13 responseActionDecl 'ch.ivy.addon.portal.generic.GlobalSearchData out;
' #txt
Gh0 f13 responseMappingAction 'out=in;
out.webStartables=result.webStartables;
' #txt
Gh0 f13 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>WebStartableService</name>
        <nameStyle>19,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Gh0 f13 408 42 128 44 -57 -8 #rect
Gh0 f13 @|CallSubIcon #fIcon
Gh0 f14 type ch.ivy.addon.portal.generic.GlobalSearchData #txt
Gh0 f14 processCall MultiPortal/TaskService:findTasksByCriteria(Long,ch.ivy.ws.addon.TaskSearchCriteria,Integer,Integer) #txt
Gh0 f14 doCall true #txt
Gh0 f14 requestActionDecl '<java.lang.Long serverId,ch.ivy.ws.addon.TaskSearchCriteria taskSearchCriteria,java.lang.Integer startIndex,java.lang.Integer count> param;
' #txt
Gh0 f14 requestMappingAction 'param.serverId=in.#serverId;
param.taskSearchCriteria=in.taskSearchCriteria;
param.startIndex=0;
param.count=ch.ivy.addon.portal.generic.bean.UserMenuBean.MAX_NUMBER_OF_RESULTS_IN_TASK_CASE_GLOBAL_SEARCH + 1;
' #txt
Gh0 f14 responseActionDecl 'ch.ivy.addon.portal.generic.GlobalSearchData out;
' #txt
Gh0 f14 responseMappingAction 'out=in;
out.tasks=in.taskSearchCriteria.ignoreInvolvedUser ? result.allTasks : result.tasks;
' #txt
Gh0 f14 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>TaskService</name>
        <nameStyle>11,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Gh0 f14 418 138 112 44 -33 -8 #rect
Gh0 f14 @|CallSubIcon #fIcon
Gh0 f15 expr out #txt
Gh0 f15 352 160 418 160 #arcP
Gh0 f16 type ch.ivy.addon.portal.generic.GlobalSearchData #txt
Gh0 f16 processCall MultiPortal/CaseService:findCasesByCriteria(Long,Integer,Integer,ch.ivy.ws.addon.CaseSearchCriteria) #txt
Gh0 f16 doCall true #txt
Gh0 f16 requestActionDecl '<java.lang.Long serverId,java.lang.Integer startIndex,java.lang.Integer count,ch.ivy.ws.addon.CaseSearchCriteria caseSearchCriteria> param;
' #txt
Gh0 f16 requestMappingAction 'param.serverId=in.#serverId;
param.startIndex=0;
param.count=ch.ivy.addon.portal.generic.bean.UserMenuBean.MAX_NUMBER_OF_RESULTS_IN_TASK_CASE_GLOBAL_SEARCH + 1;
param.caseSearchCriteria=in.caseSearchCriteria;
' #txt
Gh0 f16 responseActionDecl 'ch.ivy.addon.portal.generic.GlobalSearchData out;
' #txt
Gh0 f16 responseMappingAction 'out=in;
out.cases=result.cases;
' #txt
Gh0 f16 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>CaseService</name>
        <nameStyle>11,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Gh0 f16 418 234 112 44 -35 -8 #rect
Gh0 f16 @|CallSubIcon #fIcon
Gh0 f17 actionDecl 'ch.ivy.addon.portal.generic.GlobalSearchData out;
' #txt
Gh0 f17 actionTable 'out=in;
' #txt
Gh0 f17 actionCode 'import org.apache.commons.lang3.StringUtils;
import ch.ivy.addon.portalkit.service.ExpressServiceRegistry;
import ch.ivy.addon.portalkit.bo.ExpressProcess;
import ch.ivy.addon.portalkit.bo.RemoteWebStartable;
import ch.ivy.addon.portalkit.comparator.WebStartableNameComparator;
import ch.ivyteam.ivy.security.IUser;
import ch.ivyteam.ivy.security.IRole;
import ch.ivy.addon.portalkit.service.ProcessStartCollector;
import ch.ivy.addon.portalkit.persistence.domain.UserProcess;

ProcessStartCollector processStartCollector = new ProcessStartCollector(ivy.request.getApplication());
String expressStartLink = processStartCollector.findExpressWorkflowStartLink();
if (!StringUtils.isEmpty(expressStartLink)) {
	List<ExpressProcess> workflows = ExpressServiceRegistry.getProcessService().findAllOrderByName();
	for (ExpressProcess wf : workflows) {
		String lowercaseKeyword = in.keyword.toLowerCase();
		if (wf.getProcessName().toLowerCase().contains(lowercaseKeyword) || wf.getProcessDescription().toLowerCase().contains(lowercaseKeyword)) {
			IRole permittedRole = ivy.request.getApplication().getSecurityContext().findRole(wf.processPermission);
			IUser owner = ivy.request.getApplication().getSecurityContext().findUser(wf.processOwner.substring(1));
			
			if (ivy.session.hasRole(permittedRole, false) || ivy.session.hasRole(ivy.request.getApplication().getSecurityContext().findRole("AXONIVY_PORTAL_ADMIN"), false) || ivy.session.canActAsUser(owner)) {
				RemoteWebStartable workflowProcess = new RemoteWebStartable();
				workflowProcess.setName(wf.processName);
				workflowProcess.setDisplayName(wf.processName);
				workflowProcess.setDescription(wf.processDescription);
				String startLink = processStartCollector.findExpressWorkflowStartLink() + "?workflowID=" + wf.id;
				workflowProcess.setStartLink(startLink);
				in.webStartables.add(workflowProcess);
			}
		}
	}
}
in.webStartables.sort(new WebStartableNameComparator());
' #txt
Gh0 f17 type ch.ivy.addon.portal.generic.GlobalSearchData #txt
Gh0 f17 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Find workflow processes
and add to processes</name>
        <nameStyle>44,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Gh0 f17 568 42 160 44 -62 -16 #rect
Gh0 f17 @|StepIcon #fIcon
Gh0 f24 expr out #txt
Gh0 f24 352 256 418 256 #arcP
Gh0 f2 expr out #txt
Gh0 f2 784 160 849 160 #arcP
Gh0 f6 expr out #txt
Gh0 f6 type ch.ivy.addon.portal.generic.GlobalSearchData #txt
Gh0 f6 var in1 #txt
Gh0 f6 728 64 768 144 #arcP
Gh0 f6 1 768 64 #addKink
Gh0 f6 0 0.866614339103259 0 0 #arcLabel
Gh0 f18 expr out #txt
Gh0 f18 type ch.ivy.addon.portal.generic.GlobalSearchData #txt
Gh0 f18 var in2 #txt
Gh0 f18 530 160 752 160 #arcP
Gh0 f19 expr out #txt
Gh0 f19 type ch.ivy.addon.portal.generic.GlobalSearchData #txt
Gh0 f19 var in3 #txt
Gh0 f19 530 256 768 176 #arcP
Gh0 f19 1 768 256 #addKink
Gh0 f19 0 0.6949145126847597 0 0 #arcLabel
Gh0 f25 expr out1 #txt
Gh0 f25 192 144 224 64 #arcP
Gh0 f25 1 192 64 #addKink
Gh0 f25 1 0.08437593569205071 0 0 #arcLabel
Gh0 f20 expr out #txt
Gh0 f20 368 64 408 64 #arcP
Gh0 f21 expr out #txt
Gh0 f21 111 160 176 160 #arcP
Gh0 f9 expr out #txt
Gh0 f9 536 64 568 64 #arcP
Gh0 f4 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>search criteria is the same condition as case/task list</name>
        <nameStyle>55,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Gh0 f4 208 193 304 30 -145 -8 #rect
Gh0 f4 @|IBIcon #fIcon
>Proto Gh0 .type ch.ivy.addon.portal.generic.GlobalSearchData #txt
>Proto Gh0 .processKind CALLABLE_SUB #txt
>Proto Gh0 0 0 32 24 18 0 #rect
>Proto Gh0 @|BIcon #fIcon
Gh0 f11 head f8 mainIn #connect
Gh0 f12 head f10 mainIn #connect
Gh0 f8 mainOut f15 tail #connect
Gh0 f15 head f14 mainIn #connect
Gh0 f10 mainOut f24 tail #connect
Gh0 f24 head f16 mainIn #connect
Gh0 f5 mainOut f2 tail #connect
Gh0 f2 head f1 mainIn #connect
Gh0 f17 mainOut f6 tail #connect
Gh0 f6 head f5 in #connect
Gh0 f14 mainOut f18 tail #connect
Gh0 f18 head f5 in #connect
Gh0 f16 mainOut f19 tail #connect
Gh0 f19 head f5 in #connect
Gh0 f3 out f25 tail #connect
Gh0 f25 head f7 mainIn #connect
Gh0 f3 out f11 tail #connect
Gh0 f3 out f12 tail #connect
Gh0 f7 mainOut f20 tail #connect
Gh0 f20 head f13 mainIn #connect
Gh0 f0 mainOut f21 tail #connect
Gh0 f21 head f3 in #connect
Gh0 f13 mainOut f9 tail #connect
Gh0 f9 head f17 mainIn #connect
