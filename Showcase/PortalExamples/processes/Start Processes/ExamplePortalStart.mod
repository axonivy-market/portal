[Ivy]
164211E97C598DAA 7.5.0 #module
>Proto >Proto Collection #zClass
Pt0 ExamplePortalStart Big #zClass
Pt0 B #cInfo
Pt0 #process
Pt0 @TextInP .resExport .resExport #zField
Pt0 @TextInP .type .type #zField
Pt0 @TextInP .processKind .processKind #zField
Pt0 @AnnotationInP-0n ai ai #zField
Pt0 @MessageFlowInP-0n messageIn messageIn #zField
Pt0 @MessageFlowOutP-0n messageOut messageOut #zField
Pt0 @TextInP .xml .xml #zField
Pt0 @TextInP .responsibility .responsibility #zField
Pt0 @GridStep f19 '' #zField
Pt0 @CallSub f84 '' #zField
Pt0 @StartRequest f103 '' #zField
Pt0 @StartRequest f0 '' #zField
Pt0 @CallSub f104 '' #zField
Pt0 @GridStep f59 '' #zField
Pt0 @UserDialog f24 '' #zField
Pt0 @UserDialog f53 '' #zField
Pt0 @StartRequest f76 '' #zField
Pt0 @CallSub f37 '' #zField
Pt0 @GridStep f93 '' #zField
Pt0 @Alternative f15 '' #zField
Pt0 @InfoButton f83 '' #zField
Pt0 @Alternative f4 '' #zField
Pt0 @GridStep f11 '' #zField
Pt0 @Alternative f41 '' #zField
Pt0 @CallSub f9 '' #zField
Pt0 @EndTask f57 '' #zField
Pt0 @GridStep f106 '' #zField
Pt0 @GridStep f22 '' #zField
Pt0 @GridStep f17 '' #zField
Pt0 @GridStep f69 '' #zField
Pt0 @EndTask f64 '' #zField
Pt0 @UserDialog f74 '' #zField
Pt0 @CallSub f62 '' #zField
Pt0 @UserDialog f36 '' #zField
Pt0 @Alternative f21 '' #zField
Pt0 @GridStep f2 '' #zField
Pt0 @GridStep f92 '' #zField
Pt0 @CallSub f23 '' #zField
Pt0 @StartRequest f25 '' #zField
Pt0 @CallSub f12 '' #zField
Pt0 @GridStep f3 '' #zField
Pt0 @StartRequest f10 '' #zField
Pt0 @Alternative f43 '' #zField
Pt0 @EndTask f105 '' #zField
Pt0 @GridStep f51 '' #zField
Pt0 @StartRequest f58 '' #zField
Pt0 @UserDialog f7 '' #zField
Pt0 @StartRequest f63 '' #zField
Pt0 @CallSub f6 '' #zField
Pt0 @GridStep f52 '' #zField
Pt0 @Alternative f30 '' #zField
Pt0 @StartRequest f70 '' #zField
Pt0 @UserDialog f13 '' #zField
Pt0 @StartRequest f31 '' #zField
Pt0 @CallSub f73 '' #zField
Pt0 @CallSub f55 '' #zField
Pt0 @Alternative f16 '' #zField
Pt0 @GridStep f68 '' #zField
Pt0 @GridStep f20 '' #zField
Pt0 @StartRequest f28 '' #zField
Pt0 @PushWFArc f29 '' #zField
Pt0 @PushWFArc f71 '' #zField
Pt0 @PushWFArc f14 '' #zField
Pt0 @PushWFArc f45 '' #zField
Pt0 @PushWFArc f35 '' #zField
Pt0 @PushWFArc f66 '' #zField
Pt0 @PushWFArc f78 '' #zField
Pt0 @PushWFArc f75 '' #zField
Pt0 @PushWFArc f18 '' #zField
Pt0 @PushWFArc f72 '' #zField
Pt0 @PushWFArc f39 '' #zField
Pt0 @PushWFArc f38 '' #zField
Pt0 @PushWFArc f95 '' #zField
Pt0 @PushWFArc f118 '' #zField
Pt0 @PushWFArc f1 '' #zField
Pt0 @PushWFArc f5 '' #zField
Pt0 @PushWFArc f79 '' #zField
Pt0 @PushWFArc f108 '' #zField
Pt0 @PushWFArc f49 '' #zField
Pt0 @PushWFArc f98 '' #zField
Pt0 @PushWFArc f77 '' #zField
Pt0 @PushWFArc f60 '' #zField
Pt0 @PushWFArc f61 '' #zField
Pt0 @PushWFArc f54 '' #zField
Pt0 @PushWFArc f109 '' #zField
Pt0 @PushWFArc f50 '' #zField
Pt0 @PushWFArc f81 '' #zField
Pt0 @PushWFArc f26 '' #zField
Pt0 @PushWFArc f34 '' #zField
Pt0 @PushWFArc f82 '' #zField
Pt0 @PushWFArc f56 '' #zField
Pt0 @PushWFArc f107 '' #zField
Pt0 @PushWFArc f46 '' #zField
Pt0 @PushWFArc f42 '' #zField
Pt0 @PushWFArc f47 '' #zField
Pt0 @PushWFArc f85 '' #zField
Pt0 @PushWFArc f65 '' #zField
Pt0 @PushWFArc f33 '' #zField
Pt0 @PushWFArc f80 '' #zField
Pt0 @PushWFArc f32 '' #zField
Pt0 @PushWFArc f67 '' #zField
Pt0 @PushWFArc f44 '' #zField
Pt0 @PushWFArc f48 '' #zField
Pt0 @PushWFArc f8 '' #zField
Pt0 @PushWFArc f40 '' #zField
Pt0 @PushWFArc f27 '' #zField
>Proto Pt0 Pt0 ExamplePortalStart #zField
Pt0 f19 actionTable 'out=in;
' #txt
Pt0 f19 actionCode 'import ch.ivy.addon.portalkit.enums.SessionAttribute;
import ch.ivy.addon.portalkit.bo.TaskNode;
import ch.ivy.addon.portalkit.enums.TaskSortField;
import ch.ivy.addon.portalkit.util.SecurityServiceUtils;
import ch.ivy.addon.portalkit.datamodel.TaskLazyDataModel;
import ch.ivy.addon.portal.generic.view.TaskView;
import java.util.Map;
import org.primefaces.extensions.util.json.GsonConverter;
import java.util.Arrays;

if (!in.isDataModelInitialized) {
	boolean canLinkBackToCaseDetail = in.#dataModel.#criteria.#caseId is initialized; 
	
	in.taskView = TaskView.create().dataModel(in.dataModel)
												.canLinkBackCaseDetail(canLinkBackToCaseDetail).showHeaderToolbar(false)
												.taskId(in.selectedTaskId).createNewTaskView();
} else {
	in.dataModel.compactMode = false;
	in.dataModel.getCriteria().setNewQueryCreated(true);
	in.dataModel.setAdminQuery(true);
	in.dataModel.setSortField(TaskSortField.PRIORITY.toString(), false);
		
	Map taskInfo = GsonConverter.getGson().fromJson(in.parameters,Map.class) as Map;
	long taskId = Long.parseLong(taskInfo.get("taskId") as String);
	in.dataModel.setTaskId(taskId);
		
	String pageTitle = ivy.cms.co("/Labels/Task");
	String noTaskMessage = ivy.cms.co("/ch.ivy.addon.portalkit.ui.jsf/taskWarning/taskNotFound");

	out.taskView = TaskView.create().dataModel(in.dataModel).pageTitle(pageTitle).taskId(taskId).hideTaskFilter(true).showHeaderToolbar(false).noTaskFoundMessage(noTaskMessage).createNewTaskView();
}' #txt
Pt0 f19 security system #txt
Pt0 f19 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>build data model,
 task </name>
        <nameStyle>24,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Pt0 f19 1488 452 128 56 -43 -16 #rect
Pt0 f19 @|StepIcon #fIcon
Pt0 f84 processCall 'Functional Processes/OpenPortalTasks:useView(ch.ivy.addon.portal.generic.view.TaskView)' #txt
Pt0 f84 requestActionDecl '<ch.ivy.addon.portal.generic.view.TaskView taskView> param;' #txt
Pt0 f84 requestMappingAction 'param.taskView=in.taskView;
' #txt
Pt0 f84 responseActionDecl 'ch.ivy.addon.portal.generic.PortalStartData out;
' #txt
Pt0 f84 responseMappingAction 'out=in;
' #txt
Pt0 f84 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>OpenPortalTasks</name>
        <nameStyle>15,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Pt0 f84 540 748 104 40 -48 -12 #rect
Pt0 f84 @|CallSubIcon #fIcon
Pt0 f103 outLink startPortalCaseDetails.ivp #txt
Pt0 f103 inParamDecl '<Long caseId> param;' #txt
Pt0 f103 inParamTable 'out.caseId=param.caseId;
' #txt
Pt0 f103 requestEnabled true #txt
Pt0 f103 triggerEnabled false #txt
Pt0 f103 callSignature startPortalCaseDetails(Long) #txt
Pt0 f103 persist false #txt
Pt0 f103 caseData businessCase.attach=true #txt
Pt0 f103 showInStartList 0 #txt
Pt0 f103 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>startPortalCaseDetails.ivp</name>
    </language>
</elementInfo>
' #txt
Pt0 f103 @C|.responsibility Everybody #txt
Pt0 f103 81 1041 30 30 -58 19 #rect
Pt0 f103 @|StartRequestIcon #fIcon
Pt0 f0 outLink PortalStart.ivp #txt
Pt0 f0 inParamDecl '<String parameters,String portalNavigator> param;' #txt
Pt0 f0 inParamTable 'out.parameters=param.parameters;
out.portalPage=param.parameters.isEmpty() ? ch.ivy.addon.portalkit.enums.PortalPage.HOME_PAGE : ch.ivy.addon.portalkit.enums.PortalPage.valueOf(param.portalNavigator);
' #txt
Pt0 f0 requestEnabled true #txt
Pt0 f0 triggerEnabled false #txt
Pt0 f0 callSignature PortalStart(String,String) #txt
Pt0 f0 persist false #txt
Pt0 f0 startName <%=ivy.cms.co("/Processes/portalHome")%> #txt
Pt0 f0 taskData 'TaskTriggered.CATEGORY=<%\=ivy.cms.co("/Processes/Cases/PortalCategory")%>
TaskTriggered.DESC=<%\=ivy.cms.co("/Processes/Cases/PortalInternalProcess/PortalInternalProcessDescription")%>
TaskTriggered.EXPRI=2
TaskTriggered.EXROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.NAM=<%\=ivy.cms.co("/Processes/Cases/PortalCategory")%>\: <%\=ivy.cms.co("/Processes/portalSettingSaved")%>
TaskTriggered.PRI=2
TaskTriggered.ROL=Everybody
TaskTriggered.TYPE=0' #txt
Pt0 f0 caseData 'businessCase.attach=true
case.category=<%\=ivy.cms.co("/Processes/Cases/PortalCategory")%>
case.description=<%\=ivy.cms.co("/Processes/Cases/PortalInternalProcess/PortalInternalProcessDescription")%>
case.name=<%\=ivy.cms.co("/Processes/Cases/PortalCategory")%>\: <%\=ivy.cms.co("/Processes/portalSettingSaved")%>' #txt
Pt0 f0 showInStartList 1 #txt
Pt0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>PortalStart.ivp</name>
    </language>
</elementInfo>
' #txt
Pt0 f0 @C|.responsibility Everybody #txt
Pt0 f0 83 179 26 26 -31 17 #rect
Pt0 f0 @|StartRequestIcon #fIcon
Pt0 f104 processCall 'Functional Processes/OpenPortalCaseDetailsHook:call(ICase,Boolean)' #txt
Pt0 f104 requestActionDecl '<ch.ivyteam.ivy.workflow.ICase caseData,Boolean isShowBackButton> param;' #txt
Pt0 f104 requestMappingAction 'param.caseData=in.caseSelected;
param.isShowBackButton=true;
' #txt
Pt0 f104 responseActionDecl 'ch.ivy.addon.portal.generic.PortalStartData out;
' #txt
Pt0 f104 responseMappingAction 'out=in;
' #txt
Pt0 f104 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>OpenPortalCaseDetails</name>
    </language>
</elementInfo>
' #txt
Pt0 f104 344 1034 144 44 -65 -8 #rect
Pt0 f104 @|CallSubIcon #fIcon
Pt0 f59 actionTable 'out=in;
' #txt
Pt0 f59 actionCode 'import ch.ivy.addon.portalkit.util.PermissionUtils;
import ch.ivy.addon.portalkit.util.SecurityServiceUtils;
import ch.ivy.addon.portal.generic.view.CaseView;

boolean hasReadAllCasesPermission = PermissionUtils.checkReadAllCasesPermission();
in.caseDataModel.setAdminQuery(hasReadAllCasesPermission);

in.caseView = CaseView.create().dataModel(in.caseDataModel).buildNewView();' #txt
Pt0 f59 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>build data model&#xD;
and case view</name>
        <nameStyle>31,5
</nameStyle>
    </language>
</elementInfo>
' #txt
Pt0 f59 336 650 128 44 -43 -16 #rect
Pt0 f59 @|StepIcon #fIcon
Pt0 f24 dialogId ch.ivy.addon.portal.generic.Processes #txt
Pt0 f24 startMethod start() #txt
Pt0 f24 requestActionDecl '<> param;' #txt
Pt0 f24 responseMappingAction 'out=in;
' #txt
Pt0 f24 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>processes</name>
    </language>
</elementInfo>
' #txt
Pt0 f24 192 938 112 44 -29 -8 #rect
Pt0 f24 @|UserDialogIcon #fIcon
Pt0 f53 dialogId ch.ivy.addon.portal.generic.PortalTaskListCallback #txt
Pt0 f53 startMethod start(String) #txt
Pt0 f53 requestActionDecl '<String callbackUrl> param;' #txt
Pt0 f53 requestMappingAction 'param.callbackUrl=in.callbackUrl;
' #txt
Pt0 f53 responseMappingAction 'out=in;
' #txt
Pt0 f53 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Redirect to&#13;
callbackUrl</name>
    </language>
</elementInfo>
' #txt
Pt0 f53 536 554 112 44 -30 -16 #rect
Pt0 f53 @|UserDialogIcon #fIcon
Pt0 f76 outLink startPortalProcess.ivp #txt
Pt0 f76 inParamDecl '<> param;' #txt
Pt0 f76 requestEnabled true #txt
Pt0 f76 triggerEnabled false #txt
Pt0 f76 callSignature startPortalProcess() #txt
Pt0 f76 persist false #txt
Pt0 f76 taskData 'TaskTriggered.EXPRI=2
TaskTriggered.EXROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.PRI=2
TaskTriggered.ROL=Everybody
TaskTriggered.TYPE=0' #txt
Pt0 f76 caseData businessCase.attach=true #txt
Pt0 f76 showInStartList 0 #txt
Pt0 f76 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>startPortalProcess.ivp</name>
    </language>
</elementInfo>
' #txt
Pt0 f76 @C|.responsibility Everybody #txt
Pt0 f76 81 945 30 30 -60 17 #rect
Pt0 f76 @|StartRequestIcon #fIcon
Pt0 f37 processCall 'Functional Processes/OpenPortalSearch:call(ch.ivy.addon.portalkit.datamodel.SearchResultsDataModel,Number)' #txt
Pt0 f37 requestActionDecl '<ch.ivy.addon.portalkit.datamodel.SearchResultsDataModel searchResultsDataModel,Number activeTabIndex> param;' #txt
Pt0 f37 requestMappingAction 'param.searchResultsDataModel=in.searchResultsDataModel;
param.activeTabIndex=1;
' #txt
Pt0 f37 responseActionDecl 'ch.ivy.addon.portal.generic.PortalStartData out;
' #txt
Pt0 f37 responseMappingAction 'out=in;
' #txt
Pt0 f37 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>OpenPortalSearch</name>
    </language>
</elementInfo>
' #txt
Pt0 f37 1704 554 112 44 -50 -8 #rect
Pt0 f37 @|CallSubIcon #fIcon
Pt0 f93 actionTable 'out=in;
' #txt
Pt0 f93 actionCode 'import ch.ivy.addon.portalkit.enums.PortalLibrary;
import org.apache.commons.lang3.StringUtils;
import ch.ivyteam.ivy.workflow.StandardProcessType;

String defaultEndPage = ivy.wf.getStandardProcessImplementationLibrary(StandardProcessType.DEFAULT_PAGES_PROCESS_TYPES);

if (StringUtils.isBlank(defaultEndPage)) {
	ivy.wf.setStandardProcessImplementationLibrary(StandardProcessType.DEFAULT_PAGES_PROCESS_TYPES, "ch.ivyteam.ivy.project.portal:portalExamples");
}

String defaultMailNotification = ivy.wf.getStandardProcessImplementationLibrary(StandardProcessType.MAIL_NOTIFICATION_PROCESS_TYPES);

if (StringUtils.isBlank(defaultMailNotification)) {
	ivy.wf.setStandardProcessImplementationLibrary(StandardProcessType.MAIL_NOTIFICATION_PROCESS_TYPES, "ch.ivyteam.ivy.project.portal:portalExamples");
}' #txt
Pt0 f93 security system #txt
Pt0 f93 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Set default end page 
to Portal</name>
        <nameStyle>31,5
</nameStyle>
    </language>
</elementInfo>
' #txt
Pt0 f93 344 362 144 44 -53 -16 #rect
Pt0 f93 @|StepIcon #fIcon
Pt0 f15 464 272 32 32 0 16 #rect
Pt0 f15 @|AlternativeIcon #fIcon
Pt0 f83 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>When finish a task in IFrame,&#13;
display a blank page in IFrame&#13;
before the outside page redirects</name>
    </language>
</elementInfo>
' #txt
Pt0 f83 688 346 176 60 -85 -24 #rect
Pt0 f83 @|IBIcon #fIcon
Pt0 f4 576 176 32 32 0 16 #rect
Pt0 f4 @|AlternativeIcon #fIcon
Pt0 f11 actionTable 'out=in;
' #txt
Pt0 f11 actionCode 'import ch.ivyteam.ivy.workflow.custom.field.ICustomField;
import ch.ivy.addon.portalkit.service.StickyTaskListService;
import ch.ivy.addon.portalkit.enums.SessionAttribute;
import ch.ivy.addon.portalkit.util.SecurityServiceUtils;
import ch.ivy.addon.portalkit.enums.PortalPage;
import org.apache.commons.lang3.StringUtils;
import ch.ivy.addon.portalkit.enums.AdditionalProperty;
import ch.ivyteam.ivy.workflow.ITask;

ITask task = ivy.wf.findTask(in.endedTaskId);
ITask taskWithTaskEndInfo = StickyTaskListService.service().getPreviousTaskWithTaskEndInfo(task);
boolean isTaskStarted = false;
String callbackUrl;
if  (#task is initialized) {
	isTaskStarted = task.getStartProcessData() is initialized;
	if(#taskWithTaskEndInfo is initialized) {
		callbackUrl = taskWithTaskEndInfo.customFields().stringField(AdditionalProperty.PORTAL_TASK_CALLBACK_URI.toString()).getOrDefault("");
	}
}

if (isTaskStarted && StringUtils.isNotBlank(callbackUrl)) {
	out.callbackUrl = callbackUrl + "?endedTaskId=" + taskWithTaskEndInfo.getId();
} else {
	out.portalPage = PortalPage.HOME_PAGE;
}' #txt
Pt0 f11 security system #txt
Pt0 f11 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Handle end page</name>
        <nameStyle>15
</nameStyle>
    </language>
</elementInfo>
' #txt
Pt0 f11 192 459 112 44 -47 -8 #rect
Pt0 f11 @|StepIcon #fIcon
Pt0 f41 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Task data model
need to be initialized?</name>
        <nameStyle>39
</nameStyle>
    </language>
</elementInfo>
' #txt
Pt0 f41 656 176 32 32 -59 18 #rect
Pt0 f41 @|AlternativeIcon #fIcon
Pt0 f9 processCall 'Functional Processes/InitializeTaskDataModel:call()' #txt
Pt0 f9 requestActionDecl '<> param;' #txt
Pt0 f9 responseActionDecl 'ch.ivy.addon.portal.generic.PortalStartData out;
' #txt
Pt0 f9 responseMappingAction 'out=in;
out.dataModel=result.dataModel;
' #txt
Pt0 f9 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>InitializeTaskDataModel</name>
        <nameStyle>23,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Pt0 f9 744 170 144 44 -65 -8 #rect
Pt0 f9 @|CallSubIcon #fIcon
Pt0 f57 757 658 30 30 0 15 #rect
Pt0 f57 @|EndIcon #fIcon
Pt0 f106 actionTable 'out=in;
' #txt
Pt0 f106 actionCode 'import ch.ivyteam.ivy.workflow.ICase;
import ch.ivyteam.ivy.workflow.query.CaseQuery;

out.caseSelected = ivy.wf.getGlobalContext().getCaseQueryExecutor().getFirstResult(CaseQuery.create().where().caseId().isEqual(in.caseId)) as ICase;' #txt
Pt0 f106 security system #txt
Pt0 f106 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Find Case</name>
    </language>
</elementInfo>
' #txt
Pt0 f106 192 1034 112 44 -28 -8 #rect
Pt0 f106 @|StepIcon #fIcon
Pt0 f22 actionTable 'out=in;
' #txt
Pt0 f22 actionCode 'import ch.ivy.addon.portal.generic.view.TaskView;

in.taskView = TaskView.create().dataModel(in.dataModel).showHeaderToolbar(true).noTaskFoundMessage("").createNewTaskView();
' #txt
Pt0 f22 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Recover task
list''s configuration</name>
        <nameStyle>33
</nameStyle>
    </language>
</elementInfo>
' #txt
Pt0 f22 1496 168 112 48 -45 -17 #rect
Pt0 f22 @|StepIcon #fIcon
Pt0 f17 actionTable 'out=in;
' #txt
Pt0 f17 actionCode 'import ch.ivyteam.ivy.workflow.ITask;
import ch.ivyteam.ivy.workflow.ICase;
import ch.ivyteam.ivy.workflow.query.CaseQuery;
import java.util.Map;
import org.primefaces.extensions.util.json.GsonConverter;

Map caseInfor = GsonConverter.getGson().fromJson(in.parameters,Map.class) as Map;
long caseId;
if (#caseInfor is initialized) {
	caseId = Long.parseLong(caseInfor.get("caseId") as String); 
} else if (in.endedTaskId is initialized) {
	ITask task = ivy.wf.findTask(in.endedTaskId);
	caseId = task.getCase().getId();
}
out.caseSelected = ivy.wf.getGlobalContext().getCaseQueryExecutor().getFirstResult(CaseQuery.create().where().caseId().isEqual(caseId)) as ICase;' #txt
Pt0 f17 security system #txt
Pt0 f17 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Find case</name>
    </language>
</elementInfo>
' #txt
Pt0 f17 1496 354 112 44 -27 -8 #rect
Pt0 f17 @|StepIcon #fIcon
Pt0 f69 actionTable 'out=in;
' #txt
Pt0 f69 actionCode 'import ch.ivy.addon.portalkit.enums.TaskAssigneeType;
import ch.ivy.addon.portal.generic.view.TaskView;
import ch.ivy.addon.portalkit.util.PermissionUtils;

boolean hasReadAllTasksPermisson = PermissionUtils.checkReadAllTasksPermission();
in.dataModel.setAdminQuery(hasReadAllTasksPermisson);
in.dataModel.setTaskAssigneeType(TaskAssigneeType.ALL);

in.taskView = TaskView.create().dataModel(in.dataModel).noTaskFoundMessage("").showHeaderToolbar(false).createNewTaskView();
' #txt
Pt0 f69 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>build data model &#xD;
and task view</name>
        <nameStyle>32,5
</nameStyle>
    </language>
</elementInfo>
' #txt
Pt0 f69 344 744 112 48 -39 -16 #rect
Pt0 f69 @|StepIcon #fIcon
Pt0 f64 757 753 30 30 0 15 #rect
Pt0 f64 @|EndIcon #fIcon
Pt0 f74 dialogId ch.ivy.addon.portal.error.ErrorPage #txt
Pt0 f74 startMethod start(String) #txt
Pt0 f74 requestActionDecl '<String errorCode> param;' #txt
Pt0 f74 requestActionCode 'import org.primefaces.extensions.util.json.GsonConverter;
import java.util.Map;
Map parameters = GsonConverter.getGson().fromJson(in.parameters, Map.class) as Map;

param.errorCode = parameters.get("errorCode").toString();' #txt
Pt0 f74 responseMappingAction 'out=in;
' #txt
Pt0 f74 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Error Page</name>
    </language>
</elementInfo>
' #txt
Pt0 f74 1704 266 112 44 -29 -8 #rect
Pt0 f74 @|UserDialogIcon #fIcon
Pt0 f62 processCall 'Functional Processes/InitializeCaseDataModel:call()' #txt
Pt0 f62 requestActionDecl '<> param;' #txt
Pt0 f62 responseActionDecl 'ch.ivy.addon.portal.generic.PortalStartData out;
' #txt
Pt0 f62 responseMappingAction 'out=in;
out.caseDataModel=result.caseDataModel;
' #txt
Pt0 f62 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Initialize case 
data model</name>
        <nameStyle>27,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Pt0 f62 188 651 112 44 -38 -20 #rect
Pt0 f62 @|CallSubIcon #fIcon
Pt0 f36 dialogId ch.ivyteam.ivy.project.portal.examples.PortalHome #txt
Pt0 f36 startMethod start(ch.ivy.addon.portal.generic.view.TaskView) #txt
Pt0 f36 requestActionDecl '<ch.ivy.addon.portal.generic.view.TaskView taskView> param;' #txt
Pt0 f36 requestMappingAction 'param.taskView=in.taskView;
' #txt
Pt0 f36 responseMappingAction 'out=in;
' #txt
Pt0 f36 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Portal Home Page</name>
    </language>
</elementInfo>
' #txt
Pt0 f36 1704 170 112 44 -50 -8 #rect
Pt0 f36 @|UserDialogIcon #fIcon
Pt0 f21 1266 178 28 28 14 0 #rect
Pt0 f21 @|AlternativeIcon #fIcon
Pt0 f2 actionTable 'out=in;
' #txt
Pt0 f2 actionCode 'import ch.ivy.addon.portalkit.enums.NavigationHistory;
import ch.ivyteam.ivy.workflow.ITask;
import ch.ivy.addon.portalkit.util.SecurityServiceUtils;
import ch.ivy.addon.portalkit.dto.TaskEndInfo;
import ch.ivy.addon.portalkit.service.StickyTaskListService;
import ch.ivy.addon.portalkit.enums.PortalPage;

ITask task = ivy.wf.findTask(in.endedTaskId);
ITask taskWithTaskEndInfo = StickyTaskListService.service().getPreviousTaskWithTaskEndInfo(task);
String taskEndInfoSessionAttributeKey = StickyTaskListService.service().getTaskEndInfoSessionAttributeKey(taskWithTaskEndInfo.getId());
TaskEndInfo taskEndInfo = SecurityServiceUtils.getSessionAttribute(taskEndInfoSessionAttributeKey) as TaskEndInfo;

in.dataModel = taskEndInfo.dataModel;
in.isTaskStartedInDetails = taskEndInfo.isStartedInTaskDetails;
in.portalPage = taskEndInfo.portalPage;
in.isInIFrame = taskEndInfo.isInIFrame;

if (in.isInIFrame) {
	taskEndInfo.setIsInIFrame(false);
	SecurityServiceUtils.setSessionAttribute(taskEndInfoSessionAttributeKey, taskEndInfo);
} else {
	SecurityServiceUtils.removeSessionAttribute(taskEndInfoSessionAttributeKey);
}' #txt
Pt0 f2 security system #txt
Pt0 f2 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Recover task
list''s configuration</name>
        <nameStyle>33
</nameStyle>
    </language>
</elementInfo>
' #txt
Pt0 f2 184 266 128 44 -44 -16 #rect
Pt0 f2 @|StepIcon #fIcon
Pt0 f92 actionTable 'out=in;
' #txt
Pt0 f92 actionCode 'import ch.ivy.addon.portalkit.enums.SessionAttribute;
import ch.ivy.addon.portalkit.util.SecurityServiceUtils;
SecurityServiceUtils.setSessionAttribute(SessionAttribute.PORTAL_START_PMV_ID.toString(), ivy.task.getProcessModelVersion().getId());
' #txt
Pt0 f92 security system #txt
Pt0 f92 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>store portal start &#xD;
pmv </name>
    </language>
</elementInfo>
' #txt
Pt0 f92 184 362 128 44 -43 -16 #rect
Pt0 f92 @|StepIcon #fIcon
Pt0 f23 processCall 'Functional Processes/OpenPortalCaseDetailsHook:call(ICase,Boolean)' #txt
Pt0 f23 requestActionDecl '<ch.ivyteam.ivy.workflow.ICase caseData,Boolean isShowBackButton> param;' #txt
Pt0 f23 requestMappingAction 'param.caseData=in.caseSelected;
param.isShowBackButton=false;
' #txt
Pt0 f23 responseActionDecl 'ch.ivy.addon.portal.generic.PortalStartData out;
' #txt
Pt0 f23 responseMappingAction 'out=in;
' #txt
Pt0 f23 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>OpenPortalCaseDetailHook</name>
        <nameStyle>24,5
</nameStyle>
    </language>
</elementInfo>
' #txt
Pt0 f23 1688 354 160 44 -76 -8 #rect
Pt0 f23 @|CallSubIcon #fIcon
Pt0 f25 outLink restorePortalTaskList.ivp #txt
Pt0 f25 inParamDecl '<Number endedTaskId> param;' #txt
Pt0 f25 inParamTable 'out.endedTaskId=param.endedTaskId;
' #txt
Pt0 f25 requestEnabled true #txt
Pt0 f25 triggerEnabled false #txt
Pt0 f25 callSignature restorePortalTaskList(Number) #txt
Pt0 f25 persist false #txt
Pt0 f25 taskData 'TaskTriggered.EXPRI=2
TaskTriggered.EXROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.PRI=2
TaskTriggered.ROL=Everybody
TaskTriggered.TYPE=0' #txt
Pt0 f25 caseData businessCase.attach=true #txt
Pt0 f25 showInStartList 0 #txt
Pt0 f25 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>restorePortalTaskList.ivp</name>
        <nameStyle>25,5,7
</nameStyle>
        <desc>Display the same task list after task is finished</desc>
    </language>
</elementInfo>
' #txt
Pt0 f25 @C|.responsibility Everybody #txt
Pt0 f25 81 273 30 30 -49 15 #rect
Pt0 f25 @|StartRequestIcon #fIcon
Pt0 f12 processCall 'Functional Processes/OpenPortalTasks:useView(ch.ivy.addon.portal.generic.view.TaskView)' #txt
Pt0 f12 requestActionDecl '<ch.ivy.addon.portal.generic.view.TaskView taskView> param;' #txt
Pt0 f12 requestMappingAction 'param.taskView=in.taskView;
' #txt
Pt0 f12 responseActionDecl 'ch.ivy.addon.portal.generic.PortalStartData out;
' #txt
Pt0 f12 responseMappingAction 'out=in;
' #txt
Pt0 f12 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>OpenPortalTasks</name>
    </language>
</elementInfo>
' #txt
Pt0 f12 1708 456 104 48 -46 -6 #rect
Pt0 f12 @|CallSubIcon #fIcon
Pt0 f3 actionTable 'out=in;
' #txt
Pt0 f3 actionCode 'in.dataModel.compactMode = true;
in.isDataModelInitialized = true;' #txt
Pt0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Initialize data model</name>
        <nameStyle>21
</nameStyle>
    </language>
</elementInfo>
' #txt
Pt0 f3 944 170 128 44 -54 -8 #rect
Pt0 f3 @|StepIcon #fIcon
Pt0 f10 outLink DefaultEndPage.ivp #txt
Pt0 f10 inParamDecl '<Number endedTaskId> param;' #txt
Pt0 f10 inParamTable 'out.endedTaskId=param.endedTaskId;
' #txt
Pt0 f10 requestEnabled true #txt
Pt0 f10 triggerEnabled false #txt
Pt0 f10 callSignature DefaultEndPage(Number) #txt
Pt0 f10 persist false #txt
Pt0 f10 taskData 'TaskTriggered.EXPRI=2
TaskTriggered.EXROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.PRI=2
TaskTriggered.ROL=Everybody
TaskTriggered.TYPE=0' #txt
Pt0 f10 caseData businessCase.attach=true #txt
Pt0 f10 showInStartList 0 #txt
Pt0 f10 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>DefaultEndPage.ivp</name>
    </language>
</elementInfo>
' #txt
Pt0 f10 @C|.responsibility Everybody #txt
Pt0 f10 83 468 26 26 -46 17 #rect
Pt0 f10 @|StartRequestIcon #fIcon
Pt0 f43 1136 176 32 32 0 16 #rect
Pt0 f43 @|AlternativeIcon #fIcon
Pt0 f105 753 1041 30 30 0 15 #rect
Pt0 f105 @|EndIcon #fIcon
Pt0 f51 actionTable 'out=in;
' #txt
Pt0 f51 actionCode 'import ch.ivy.addon.portalkit.enums.SessionAttribute;
import ch.ivy.addon.portalkit.util.SecurityServiceUtils;
SecurityServiceUtils.setSessionAttribute(SessionAttribute.PORTAL_START_PMV_ID.toString(), ivy.task.getProcessModelVersion().getId());
' #txt
Pt0 f51 security system #txt
Pt0 f51 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>store portal start &#xD;
pmv </name>
    </language>
</elementInfo>
' #txt
Pt0 f51 184 170 128 44 -43 -16 #rect
Pt0 f51 @|StepIcon #fIcon
Pt0 f58 outLink startPortalCase.ivp #txt
Pt0 f58 inParamDecl '<> param;' #txt
Pt0 f58 requestEnabled true #txt
Pt0 f58 triggerEnabled false #txt
Pt0 f58 callSignature startPortalCase() #txt
Pt0 f58 persist false #txt
Pt0 f58 taskData 'TaskTriggered.EXPRI=2
TaskTriggered.EXROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.PRI=2
TaskTriggered.ROL=Everybody
TaskTriggered.TYPE=0' #txt
Pt0 f58 caseData businessCase.attach=true #txt
Pt0 f58 showInStartList 0 #txt
Pt0 f58 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>startPortalCase.ivp</name>
    </language>
</elementInfo>
' #txt
Pt0 f58 @C|.responsibility Everybody #txt
Pt0 f58 81 658 30 30 -52 17 #rect
Pt0 f58 @|StartRequestIcon #fIcon
Pt0 f7 dialogId ch.ivy.addon.portal.generic.iframe.BlankPageForIFrame #txt
Pt0 f7 startMethod start() #txt
Pt0 f7 requestActionDecl '<> param;' #txt
Pt0 f7 responseMappingAction 'out=in;
' #txt
Pt0 f7 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>BlankPageForIFrame</name>
    </language>
</elementInfo>
' #txt
Pt0 f7 704 266 128 44 -55 -8 #rect
Pt0 f7 @|UserDialogIcon #fIcon
Pt0 f63 outLink startPortalTask.ivp #txt
Pt0 f63 inParamDecl '<> param;' #txt
Pt0 f63 requestEnabled true #txt
Pt0 f63 triggerEnabled false #txt
Pt0 f63 callSignature startPortalTask() #txt
Pt0 f63 persist false #txt
Pt0 f63 taskData 'TaskTriggered.EXPRI=2
TaskTriggered.EXROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.PRI=2
TaskTriggered.ROL=Everybody
TaskTriggered.TYPE=0' #txt
Pt0 f63 caseData businessCase.attach=true #txt
Pt0 f63 showInStartList 0 #txt
Pt0 f63 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>startPortalTask.ivp</name>
    </language>
</elementInfo>
' #txt
Pt0 f63 @C|.responsibility Everybody #txt
Pt0 f63 81 753 30 30 -50 17 #rect
Pt0 f63 @|StartRequestIcon #fIcon
Pt0 f6 processCall 'Functional Processes/OpenPortalTaskDetailsHook:call(ch.ivyteam.ivy.workflow.ITask,ch.ivy.addon.portalkit.datamodel.TaskLazyDataModel,ch.ivy.addon.portalkit.enums.PortalPage,Boolean)' #txt
Pt0 f6 requestActionDecl '<ch.ivyteam.ivy.workflow.ITask task,ch.ivy.addon.portalkit.datamodel.TaskLazyDataModel dataModel,ch.ivy.addon.portalkit.enums.PortalPage portalPage,Boolean isFromTaskList> param;' #txt
Pt0 f6 requestMappingAction 'param.task=in.taskSelected;
param.dataModel=in.dataModel;
param.portalPage=in.portalPage;
param.isFromTaskList=false;
' #txt
Pt0 f6 responseActionDecl 'ch.ivy.addon.portal.generic.PortalStartData out;
' #txt
Pt0 f6 responseMappingAction 'out=in;
' #txt
Pt0 f6 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>OpenPortalTaskDetailsHook</name>
    </language>
</elementInfo>
' #txt
Pt0 f6 1448 106 176 44 -78 -8 #rect
Pt0 f6 @|CallSubIcon #fIcon
Pt0 f52 actionTable 'out=in;
' #txt
Pt0 f52 actionCode 'import ch.ivy.addon.portalkit.datamodel.CaseLazyDataModel;
import ch.ivy.addon.portalkit.datamodel.SearchResultsDataModel;

in.searchResultsDataModel = new SearchResultsDataModel();
in.searchResultsDataModel.taskDataModel = in.dataModel;
in.searchResultsDataModel.keyword = in.searchResultsDataModel.taskDataModel.criteria.keyword;
in.searchResultsDataModel.caseDataModel.notKeepFilter = true;
in.searchResultsDataModel.caseDataModel.getCriteria().keyword = in.searchResultsDataModel.taskDataModel.criteria.keyword;

' #txt
Pt0 f52 security system #txt
Pt0 f52 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>build data model</name>
        <nameStyle>16,5
</nameStyle>
    </language>
</elementInfo>
' #txt
Pt0 f52 1496 554 112 44 -46 -8 #rect
Pt0 f52 @|StepIcon #fIcon
Pt0 f30 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>is there callbackUrl?</name>
        <nameStyle>21
</nameStyle>
    </language>
</elementInfo>
' #txt
Pt0 f30 576 465 32 32 13 7 #rect
Pt0 f30 @|AlternativeIcon #fIcon
Pt0 f70 outLink startPortalStatistic.ivp #txt
Pt0 f70 inParamDecl '<> param;' #txt
Pt0 f70 requestEnabled true #txt
Pt0 f70 triggerEnabled false #txt
Pt0 f70 callSignature startPortalStatistic() #txt
Pt0 f70 persist false #txt
Pt0 f70 taskData 'TaskTriggered.EXPRI=2
TaskTriggered.EXROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.PRI=2
TaskTriggered.ROL=Everybody
TaskTriggered.TYPE=0' #txt
Pt0 f70 caseData businessCase.attach=true #txt
Pt0 f70 showInStartList 0 #txt
Pt0 f70 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>startPortalStatistic.ivp</name>
    </language>
</elementInfo>
' #txt
Pt0 f70 @C|.responsibility Everybody #txt
Pt0 f70 81 850 30 30 -58 17 #rect
Pt0 f70 @|StartRequestIcon #fIcon
Pt0 f13 dialogId ch.ivy.addon.portal.generic.admin.PortalDashBoard #txt
Pt0 f13 startMethod start() #txt
Pt0 f13 requestActionDecl '<> param;' #txt
Pt0 f13 responseMappingAction 'out=in;
' #txt
Pt0 f13 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>statistic</name>
    </language>
</elementInfo>
' #txt
Pt0 f13 192 842 112 44 -21 -8 #rect
Pt0 f13 @|UserDialogIcon #fIcon
Pt0 f31 outLink DefaultLoginPage.ivp #txt
Pt0 f31 inParamDecl '<String originalUrl> param;' #txt
Pt0 f31 inParamTable 'out.callbackUrl=param.#originalUrl is initialized ? param.originalUrl : null;
' #txt
Pt0 f31 requestEnabled true #txt
Pt0 f31 triggerEnabled false #txt
Pt0 f31 callSignature DefaultLoginPage(String) #txt
Pt0 f31 persist false #txt
Pt0 f31 taskData 'TaskTriggered.EXPRI=2
TaskTriggered.EXROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.PRI=2
TaskTriggered.ROL=Everybody
TaskTriggered.TYPE=0' #txt
Pt0 f31 caseData businessCase.attach=true #txt
Pt0 f31 showInStartList 0 #txt
Pt0 f31 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>DefaultLoginPage.ivp</name>
        <nameStyle>20,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Pt0 f31 @C|.responsibility Everybody #txt
Pt0 f31 81 369 30 30 -45 18 #rect
Pt0 f31 @|StartRequestIcon #fIcon
Pt0 f73 processCall 'Functional Processes/InitializeTaskDataModel:call()' #txt
Pt0 f73 requestActionDecl '<> param;' #txt
Pt0 f73 responseActionDecl 'ch.ivy.addon.portal.generic.PortalStartData out;
' #txt
Pt0 f73 responseMappingAction 'out=in;
out.dataModel=result.dataModel;
' #txt
Pt0 f73 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Init data model</name>
        <nameStyle>15
</nameStyle>
    </language>
</elementInfo>
' #txt
Pt0 f73 188 746 112 44 -40 -8 #rect
Pt0 f73 @|CallSubIcon #fIcon
Pt0 f55 processCall 'Functional Processes/OpenPortalCases:useView(ch.ivy.addon.portal.generic.view.CaseView)' #txt
Pt0 f55 requestActionDecl '<ch.ivy.addon.portal.generic.view.CaseView view> param;' #txt
Pt0 f55 requestMappingAction 'param.view=in.caseView;
' #txt
Pt0 f55 responseActionDecl 'ch.ivy.addon.portal.generic.PortalStartData out;
' #txt
Pt0 f55 responseMappingAction 'out=in;
' #txt
Pt0 f55 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>OpenPortalCases</name>
        <nameStyle>15,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Pt0 f55 536 649 112 48 -49 -12 #rect
Pt0 f55 @|CallSubIcon #fIcon
Pt0 f16 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Cancel task and back &#xD;
to task details?</name>
    </language>
</elementInfo>
' #txt
Pt0 f16 1200 176 32 32 -54 18 #rect
Pt0 f16 @|AlternativeIcon #fIcon
Pt0 f68 actionTable 'out=in;
' #txt
Pt0 f68 actionCode 'ivy.session.removeAttribute(ch.ivy.addon.portalkit.enums.SessionAttribute.IS_TASK_NOT_FINISHED.toString());
out.taskSelected = ivy.wf.findTask(in.endedTaskId);' #txt
Pt0 f68 security system #txt
Pt0 f68 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Remove session attribute&#xD;
and find task by id</name>
    </language>
</elementInfo>
' #txt
Pt0 f68 1256 106 176 44 -67 -16 #rect
Pt0 f68 @|StepIcon #fIcon
Pt0 f20 actionTable 'out=in;
' #txt
Pt0 f20 actionCode 'import ch.ivy.addon.portalkit.enums.PortalLibrary;
import org.apache.commons.lang3.StringUtils;
import ch.ivyteam.ivy.workflow.StandardProcessType;

String defaultEndPage = ivy.wf.getStandardProcessImplementationLibrary(StandardProcessType.DEFAULT_PAGES_PROCESS_TYPES);

if (StringUtils.isBlank(defaultEndPage)) {
	ivy.wf.setStandardProcessImplementationLibrary(StandardProcessType.DEFAULT_PAGES_PROCESS_TYPES, "ch.ivyteam.ivy.project.portal:portalExamples");
}

String defaultMailNotification = ivy.wf.getStandardProcessImplementationLibrary(StandardProcessType.MAIL_NOTIFICATION_PROCESS_TYPES);

if (StringUtils.isBlank(defaultMailNotification)) {
	ivy.wf.setStandardProcessImplementationLibrary(StandardProcessType.MAIL_NOTIFICATION_PROCESS_TYPES, "ch.ivyteam.ivy.project.portal:portalExamples");
}' #txt
Pt0 f20 security system #txt
Pt0 f20 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Set default end page 
to Portal</name>
        <nameStyle>31,5
</nameStyle>
    </language>
</elementInfo>
' #txt
Pt0 f20 344 170 144 44 -53 -16 #rect
Pt0 f20 @|StepIcon #fIcon
Pt0 f28 outLink DefaultApplicationHomePage.ivp #txt
Pt0 f28 inParamDecl '<> param;' #txt
Pt0 f28 requestEnabled true #txt
Pt0 f28 triggerEnabled false #txt
Pt0 f28 callSignature DefaultApplicationHomePage() #txt
Pt0 f28 persist false #txt
Pt0 f28 taskData 'TaskTriggered.EXPRI=2
TaskTriggered.EXROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.PRI=2
TaskTriggered.ROL=Everybody
TaskTriggered.TYPE=0' #txt
Pt0 f28 caseData businessCase.attach=true #txt
Pt0 f28 showInStartList 0 #txt
Pt0 f28 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>DefaultApplicationHomePage.ivp</name>
    </language>
</elementInfo>
' #txt
Pt0 f28 @C|.responsibility Everybody #txt
Pt0 f28 81 81 30 30 -45 18 #rect
Pt0 f28 @|StartRequestIcon #fIcon
Pt0 f29 expr in #txt
Pt0 f29 outCond 'java.util.Objects.equals(ch.ivy.addon.portalkit.enums.PortalPage.TASK_LIST, in.#portalPage)' #txt
Pt0 f29 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>TASK_LIST</name>
        <nameStyle>12
</nameStyle>
    </language>
</elementInfo>
' #txt
Pt0 f29 1281 205 1488 480 #arcP
Pt0 f29 1 1312 480 #addKink
Pt0 f29 1 0.2556818181818182 0 -14 #arcLabel
Pt0 f71 expr out #txt
Pt0 f71 111 673 188 673 #arcP
Pt0 f14 expr out #txt
Pt0 f14 312 192 344 192 #arcP
Pt0 f45 expr in #txt
Pt0 f45 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>NO</name>
        <nameStyle>2
</nameStyle>
    </language>
</elementInfo>
' #txt
Pt0 f45 672 176 1152 176 #arcP
Pt0 f45 1 672 128 #addKink
Pt0 f45 2 1152 128 #addKink
Pt0 f45 0 0.4166666666666667 -10 0 #arcLabel
Pt0 f35 expr in #txt
Pt0 f35 outCond '!in.#dataModel is initialized' #txt
Pt0 f35 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>YES</name>
        <nameStyle>3
</nameStyle>
    </language>
</elementInfo>
' #txt
Pt0 f35 688 192 744 192 #arcP
Pt0 f35 0 0.4305555555555556 0 -11 #arcLabel
Pt0 f66 expr out #txt
Pt0 f66 648 673 757 673 #arcP
Pt0 f78 expr in #txt
Pt0 f78 outCond 'java.util.Objects.equals(ch.ivy.addon.portalkit.enums.PortalPage.HOME_PAGE, in.#portalPage) || java.util.Objects.isNull(in.#portalPage)' #txt
Pt0 f78 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>HOME_PAGE</name>
        <nameStyle>9
</nameStyle>
    </language>
</elementInfo>
' #txt
Pt0 f78 1294 192 1496 192 #arcP
Pt0 f78 0 0.5148514851485149 0 13 #arcLabel
Pt0 f75 expr in #txt
Pt0 f75 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>SEARCH_RESULTS</name>
        <nameStyle>14,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Pt0 f75 1280 206 1496 576 #arcP
Pt0 f75 1 1280 576 #addKink
Pt0 f75 1 0.4278121882195234 0 -21 #arcLabel
Pt0 f18 110 864 192 864 #arcP
Pt0 f72 expr in #txt
Pt0 f72 outCond 'in.isTaskStartedInDetails && ivy.session.getAttribute(ch.ivy.addon.portalkit.enums.SessionAttribute.IS_TASK_NOT_FINISHED.toString()) as Boolean' #txt
Pt0 f72 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>YES</name>
    </language>
</elementInfo>
' #txt
Pt0 f72 1216 176 1256 128 #arcP
Pt0 f72 1 1216 128 #addKink
Pt0 f72 1 0.3852515795233524 -1 -9 #arcLabel
Pt0 f39 expr out #txt
Pt0 f39 1616 480 1708 480 #arcP
Pt0 f39 0 0.7418894103244236 0 0 #arcLabel
Pt0 f38 1608 192 1704 192 #arcP
Pt0 f95 expr out #txt
Pt0 f95 312 384 344 384 #arcP
Pt0 f118 expr out #txt
Pt0 f118 300 768 344 768 #arcP
Pt0 f1 expr out #txt
Pt0 f1 111 288 184 288 #arcP
Pt0 f5 expr out #txt
Pt0 f5 488 192 576 192 #arcP
Pt0 f79 expr in #txt
Pt0 f79 592 465 592 208 #arcP
Pt0 f108 expr out #txt
Pt0 f108 111 1056 192 1056 #arcP
Pt0 f49 expr out #txt
Pt0 f49 1608 576 1704 576 #arcP
Pt0 f98 expr out #txt
Pt0 f98 111 384 184 384 #arcP
Pt0 f98 0 0.5022241929816383 0 0 #arcLabel
Pt0 f77 expr out #txt
Pt0 f77 111 768 188 768 #arcP
Pt0 f60 expr out #txt
Pt0 f60 464 672 536 673 #arcP
Pt0 f61 expr out #txt
Pt0 f61 300 673 336 672 #arcP
Pt0 f54 expr out #txt
Pt0 f54 109 192 184 192 #arcP
Pt0 f109 expr out #txt
Pt0 f109 488 1056 753 1056 #arcP
Pt0 f50 expr out #txt
Pt0 f50 304 481 576 481 #arcP
Pt0 f81 expr out #txt
Pt0 f81 312 288 464 288 #arcP
Pt0 f81 0 0.49687471886202816 0 0 #arcLabel
Pt0 f26 expr out #txt
Pt0 f26 1608 376 1688 376 #arcP
Pt0 f34 expr in #txt
Pt0 f34 1168 192 1200 192 #arcP
Pt0 f82 496 288 704 288 #arcP
Pt0 f56 expr in #txt
Pt0 f56 outCond 'java.util.Objects.equals(ch.ivy.addon.portalkit.enums.PortalPage.ERROR_PAGE, in.#portalPage) ' #txt
Pt0 f56 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>ERROR</name>
    </language>
</elementInfo>
' #txt
Pt0 f56 1287 199 1704 288 #arcP
Pt0 f56 1 1368 288 #addKink
Pt0 f56 1 0.33035714285714285 0 -10 #arcLabel
Pt0 f107 expr out #txt
Pt0 f107 304 1056 344 1056 #arcP
Pt0 f46 expr out #txt
Pt0 f46 111 96 592 176 #arcP
Pt0 f46 1 592 96 #addKink
Pt0 f46 0 0.6515872258938749 0 0 #arcLabel
Pt0 f42 expr out #txt
Pt0 f42 1432 128 1448 128 #arcP
Pt0 f42 0 0.075 0 -9 #arcLabel
Pt0 f47 expr out #txt
Pt0 f47 488 384 587 470 #arcP
Pt0 f47 1 544 384 #addKink
Pt0 f47 0 0.9638478923705887 0 0 #arcLabel
Pt0 f85 expr in #txt
Pt0 f85 outCond !in.isInIFrame #txt
Pt0 f85 489 281 583 199 #arcP
Pt0 f65 expr out #txt
Pt0 f65 644 768 757 768 #arcP
Pt0 f33 111 960 192 960 #arcP
Pt0 f80 expr in #txt
Pt0 f80 outCond 'org.apache.commons.lang3.StringUtils.isNotBlank(in.#callbackUrl)' #txt
Pt0 f80 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>YES</name>
    </language>
</elementInfo>
' #txt
Pt0 f80 592 497 592 554 #arcP
Pt0 f80 0 0.40350877192982454 -20 0 #arcLabel
Pt0 f32 expr in #txt
Pt0 f32 outCond 'java.util.Objects.equals(ch.ivy.addon.portalkit.enums.PortalPage.CASE_DETAIL_FROM_TASK, in.#portalPage)' #txt
Pt0 f32 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>CASE_DETAIL_FROM_TASK</name>
        <nameStyle>21
</nameStyle>
    </language>
</elementInfo>
' #txt
Pt0 f32 1284 202 1496 376 #arcP
Pt0 f32 1 1344 376 #addKink
Pt0 f32 0 1.0 80 -25 #arcLabel
Pt0 f67 expr out #txt
Pt0 f67 456 768 540 768 #arcP
Pt0 f44 expr out #txt
Pt0 f44 1072 192 1136 192 #arcP
Pt0 f48 expr out #txt
Pt0 f48 109 481 192 481 #arcP
Pt0 f8 expr out #txt
Pt0 f8 888 192 944 192 #arcP
Pt0 f40 expr in #txt
Pt0 f40 1232 192 1266 192 #arcP
Pt0 f27 expr in #txt
Pt0 f27 608 192 656 192 #arcP
>Proto Pt0 .type ch.ivy.addon.portal.generic.PortalStartData #txt
>Proto Pt0 .processKind NORMAL #txt
>Proto Pt0 0 0 32 24 18 0 #rect
>Proto Pt0 @|BIcon #fIcon
Pt0 f25 mainOut f1 tail #connect
Pt0 f1 head f2 mainIn #connect
Pt0 f20 mainOut f5 tail #connect
Pt0 f5 head f4 in #connect
Pt0 f17 mainOut f26 tail #connect
Pt0 f26 head f23 mainIn #connect
Pt0 f19 mainOut f39 tail #connect
Pt0 f39 head f12 mainIn #connect
Pt0 f9 mainOut f8 tail #connect
Pt0 f8 head f3 mainIn #connect
Pt0 f41 out f35 tail #connect
Pt0 f35 head f9 mainIn #connect
Pt0 f3 mainOut f44 tail #connect
Pt0 f44 head f43 in #connect
Pt0 f41 out f45 tail #connect
Pt0 f45 head f43 in #connect
Pt0 f11 mainOut f50 tail #connect
Pt0 f50 head f30 in #connect
Pt0 f28 mainOut f46 tail #connect
Pt0 f46 head f4 in #connect
Pt0 f52 mainOut f49 tail #connect
Pt0 f49 head f37 mainIn #connect
Pt0 f0 mainOut f54 tail #connect
Pt0 f54 head f51 mainIn #connect
Pt0 f51 mainOut f14 tail #connect
Pt0 f14 head f20 mainIn #connect
Pt0 f62 mainOut f61 tail #connect
Pt0 f61 head f59 mainIn #connect
Pt0 f59 mainOut f60 tail #connect
Pt0 f60 head f55 mainIn #connect
Pt0 f55 mainOut f66 tail #connect
Pt0 f66 head f57 mainIn #connect
Pt0 f58 mainOut f71 tail #connect
Pt0 f71 head f62 mainIn #connect
Pt0 f73 mainOut f118 tail #connect
Pt0 f118 head f69 mainIn #connect
Pt0 f69 mainOut f67 tail #connect
Pt0 f67 head f84 mainIn #connect
Pt0 f84 mainOut f65 tail #connect
Pt0 f65 head f64 mainIn #connect
Pt0 f63 mainOut f77 tail #connect
Pt0 f77 head f73 mainIn #connect
Pt0 f92 mainOut f95 tail #connect
Pt0 f95 head f93 mainIn #connect
Pt0 f31 mainOut f98 tail #connect
Pt0 f98 head f92 mainIn #connect
Pt0 f93 mainOut f47 tail #connect
Pt0 f47 head f30 in #connect
Pt0 f10 mainOut f48 tail #connect
Pt0 f48 head f11 mainIn #connect
Pt0 f103 mainOut f108 tail #connect
Pt0 f108 head f106 mainIn #connect
Pt0 f106 mainOut f107 tail #connect
Pt0 f107 head f104 mainIn #connect
Pt0 f104 mainOut f109 tail #connect
Pt0 f109 head f105 mainIn #connect
Pt0 f4 out f27 tail #connect
Pt0 f27 head f41 in #connect
Pt0 f43 out f34 tail #connect
Pt0 f34 head f16 in #connect
Pt0 f40 head f21 in #connect
Pt0 f16 out f72 tail #connect
Pt0 f72 head f68 mainIn #connect
Pt0 f16 out f40 tail #connect
Pt0 f68 mainOut f42 tail #connect
Pt0 f42 head f6 mainIn #connect
Pt0 f70 mainOut f18 tail #connect
Pt0 f18 head f13 mainIn #connect
Pt0 f76 mainOut f33 tail #connect
Pt0 f33 head f24 mainIn #connect
Pt0 f22 mainOut f38 tail #connect
Pt0 f38 head f36 mainIn #connect
Pt0 f21 out f78 tail #connect
Pt0 f78 head f22 mainIn #connect
Pt0 f21 out f32 tail #connect
Pt0 f32 head f17 mainIn #connect
Pt0 f21 out f29 tail #connect
Pt0 f29 head f19 mainIn #connect
Pt0 f21 out f56 tail #connect
Pt0 f56 head f74 mainIn #connect
Pt0 f21 out f75 tail #connect
Pt0 f75 head f52 mainIn #connect
Pt0 f30 out f80 tail #connect
Pt0 f80 head f53 mainIn #connect
Pt0 f30 out f79 tail #connect
Pt0 f79 head f4 in #connect
Pt0 f2 mainOut f81 tail #connect
Pt0 f81 head f15 in #connect
Pt0 f15 out f85 tail #connect
Pt0 f85 head f4 in #connect
Pt0 f15 out f82 tail #connect
Pt0 f82 head f7 mainIn #connect
