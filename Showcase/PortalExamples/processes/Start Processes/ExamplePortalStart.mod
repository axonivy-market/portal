[Ivy]
164211E97C598DAA 3.26 #module
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
Pt0 @RichDialog f18 '' #zField
Pt0 @GridStep f2 '' #zField
Pt0 @StartRequest f63 '' #zField
Pt0 @RichDialog f82 '' #zField
Pt0 @Alternative f21 '' #zField
Pt0 @GridStep f51 '' #zField
Pt0 @CallSub f12 '' #zField
Pt0 @StartRequest f10 '' #zField
Pt0 @StartRequest f31 '' #zField
Pt0 @GridStep f52 '' #zField
Pt0 @StartRequest f25 '' #zField
Pt0 @Alternative f4 '' #zField
Pt0 @Alternative f30 '' #zField
Pt0 @CallSub f37 '' #zField
Pt0 @Alternative f43 '' #zField
Pt0 @GridStep f93 '' #zField
Pt0 @GridStep f20 '' #zField
Pt0 @Alternative f41 '' #zField
Pt0 @GridStep f59 '' #zField
Pt0 @StartRequest f70 '' #zField
Pt0 @EndTask f64 '' #zField
Pt0 @StartRequest f0 '' #zField
Pt0 @EndTask f57 '' #zField
Pt0 @GridStep f92 '' #zField
Pt0 @StartRequest f58 '' #zField
Pt0 @StartRequest f28 '' #zField
Pt0 @CallSub f9 '' #zField
Pt0 @StartRequest f76 '' #zField
Pt0 @CallSub f62 '' #zField
Pt0 @GridStep f11 '' #zField
Pt0 @GridStep f69 '' #zField
Pt0 @GridStep f3 '' #zField
Pt0 @CallSub f73 '' #zField
Pt0 @CallSub f55 '' #zField
Pt0 @RichDialog f74 '' #zField
Pt0 @RichDialog f33 '' #zField
Pt0 @GridStep f22 '' #zField
Pt0 @GridStep f19 '' #zField
Pt0 @CallSub f84 '' #zField
Pt0 @RichDialog f13 '' #zField
Pt0 @PushWFArc f32 '' #zField
Pt0 @PushWFArc f8 '' #zField
Pt0 @PushWFArc f60 '' #zField
Pt0 @PushWFArc f77 '' #zField
Pt0 @PushWFArc f36 '' #zField
Pt0 @PushWFArc f78 '' #zField
Pt0 @PushWFArc f7 '' #zField
Pt0 @PushWFArc f53 '' #zField
Pt0 @PushWFArc f50 '' #zField
Pt0 @PushWFArc f44 '' #zField
Pt0 @PushWFArc f65 '' #zField
Pt0 @PushWFArc f61 '' #zField
Pt0 @PushWFArc f79 '' #zField
Pt0 @PushWFArc f46 '' #zField
Pt0 @PushWFArc f40 '' #zField
Pt0 @PushWFArc f56 '' #zField
Pt0 @PushWFArc f1 '' #zField
Pt0 @PushWFArc f35 '' #zField
Pt0 @PushWFArc f67 '' #zField
Pt0 @PushWFArc f45 '' #zField
Pt0 @PushWFArc f118 '' #zField
Pt0 @PushWFArc f48 '' #zField
Pt0 @PushWFArc f5 '' #zField
Pt0 @PushWFArc f14 '' #zField
Pt0 @PushWFArc f39 '' #zField
Pt0 @PushWFArc f15 '' #zField
Pt0 @PushWFArc f95 '' #zField
Pt0 @PushWFArc f71 '' #zField
Pt0 @PushWFArc f66 '' #zField
Pt0 @PushWFArc f49 '' #zField
Pt0 @PushWFArc f47 '' #zField
Pt0 @PushWFArc f34 '' #zField
Pt0 @PushWFArc f24 '' #zField
Pt0 @PushWFArc f98 '' #zField
Pt0 @PushWFArc f54 '' #zField
Pt0 @StartRequest f103 '' #zField
Pt0 @CallSub f104 '' #zField
Pt0 @EndTask f105 '' #zField
Pt0 @GridStep f106 '' #zField
Pt0 @PushWFArc f108 '' #zField
Pt0 @PushWFArc f109 '' #zField
Pt0 @PushWFArc f107 '' #zField
Pt0 @StartRequest f110 '' #zField
Pt0 @GridStep f117 '' #zField
Pt0 @EndTask f112 '' #zField
Pt0 @CallSub f115 '' #zField
Pt0 @PushWFArc f114 '' #zField
Pt0 @PushWFArc f116 '' #zField
Pt0 @PushWFArc f119 '' #zField
Pt0 @PushWFArc f42 '' #zField
Pt0 @CallSub f23 '' #zField
Pt0 @GridStep f17 '' #zField
Pt0 @PushWFArc f26 '' #zField
Pt0 @PushWFArc f6 '' #zField
>Proto Pt0 Pt0 ExamplePortalStart #zField
Pt0 f18 targetWindow NEW:card: #txt
Pt0 f18 targetDisplay TOP #txt
Pt0 f18 richDialogId ch.ivy.addon.portal.error.ErrorPage #txt
Pt0 f18 startMethod start(String) #txt
Pt0 f18 type ch.ivy.addon.portal.generic.PortalStartData #txt
Pt0 f18 requestActionDecl '<String errorCode> param;' #txt
Pt0 f18 requestActionCode 'import org.primefaces.extensions.util.json.GsonConverter;
import java.util.Map;
Map parameters = GsonConverter.getGson().fromJson(in.parameters, Map.class) as Map;

param.errorCode = parameters.get("errorCode").toString();' #txt
Pt0 f18 responseActionDecl 'ch.ivy.addon.portal.generic.PortalStartData out;
' #txt
Pt0 f18 responseMappingAction 'out=in;
' #txt
Pt0 f18 windowConfiguration '* ' #txt
Pt0 f18 isAsynch false #txt
Pt0 f18 isInnerRd false #txt
Pt0 f18 userContext '* ' #txt
Pt0 f18 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Error Page</name>
        <nameStyle>10
</nameStyle>
    </language>
</elementInfo>
' #txt
Pt0 f18 1808 240 96 48 -22 -8 #rect
Pt0 f18 @|RichDialogIcon #fIcon
Pt0 f2 actionDecl 'ch.ivy.addon.portal.generic.PortalStartData out;
' #txt
Pt0 f2 actionTable 'out=in;
' #txt
Pt0 f2 actionCode 'import ch.ivy.addon.portalkit.enums.NavigationHistory;
import ch.ivyteam.ivy.workflow.ITask;
import ch.ivy.addon.portalkit.util.SecurityServiceUtils;
import ch.ivy.addon.portalkit.dto.TaskEndInfo;
import ch.ivy.addon.portalkit.service.StickyTaskListService;
import ch.ivy.addon.portal.generic.navigation.PortalPage;

ITask task = ivy.wf.findTask(in.endedTaskId);
ITask taskWithTaskEndInfo = StickyTaskListService.service().getPreviousTaskWithTaskEndInfo(task);
String taskEndInfoSessionAttributeKey = StickyTaskListService.service().getTaskEndInfoSessionAttributeKey(taskWithTaskEndInfo.getId());
TaskEndInfo taskEndInfo = SecurityServiceUtils.getSessionAttribute(taskEndInfoSessionAttributeKey) as TaskEndInfo;

in.dataModel = taskEndInfo.dataModel;
if (taskEndInfo.#navigationHistory is initialized && NavigationHistory.SEARCH_RESULTS == taskEndInfo.navigationHistory) {
	in.portalPage = PortalPage.SEARCH_RESULTS;
} else {
	in.portalPage = taskEndInfo.isFromPortalHome ? PortalPage.HOME_PAGE : PortalPage.LINK_TO_TASK;
}
SecurityServiceUtils.removeSessionAttribute(taskEndInfoSessionAttributeKey);

' #txt
Pt0 f2 security system #txt
Pt0 f2 type ch.ivy.addon.portal.generic.PortalStartData #txt
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
Pt0 f2 152 242 128 44 -44 -16 #rect
Pt0 f2 @|StepIcon #fIcon
Pt0 f63 outLink startPortalTask.ivp #txt
Pt0 f63 type ch.ivy.addon.portal.generic.PortalTaskData #txt
Pt0 f63 inParamDecl '<> param;' #txt
Pt0 f63 actionDecl 'ch.ivy.addon.portal.generic.PortalTaskData out;
' #txt
Pt0 f63 guid 166EC4390FBDB60D #txt
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
Pt0 f63 49 729 30 30 -50 17 #rect
Pt0 f63 @|StartRequestIcon #fIcon
Pt0 f82 targetWindow NEW:card: #txt
Pt0 f82 targetDisplay TOP #txt
Pt0 f82 richDialogId ch.ivy.addon.portal.generic.admin.PortalDashBoard #txt
Pt0 f82 startMethod startWithMenuState(String) #txt
Pt0 f82 type ch.ivy.addon.portal.generic.PortalStartData #txt
Pt0 f82 requestActionDecl '<String menuState> param;' #txt
Pt0 f82 requestMappingAction 'param.menuState=ch.ivy.addon.portalkit.util.MenuUtils.getMenuState();
' #txt
Pt0 f82 responseActionDecl 'ch.ivy.addon.portal.generic.PortalStartData out;
' #txt
Pt0 f82 responseMappingAction 'out=in;
' #txt
Pt0 f82 windowConfiguration '* ' #txt
Pt0 f82 isAsynch false #txt
Pt0 f82 isInnerRd false #txt
Pt0 f82 userContext '* ' #txt
Pt0 f82 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>statistic</name>
        <nameStyle>9,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Pt0 f82 156 819 112 44 -21 -8 #rect
Pt0 f82 @|RichDialogIcon #fIcon
Pt0 f21 type ch.ivy.addon.portal.generic.PortalStartData #txt
Pt0 f21 1570 154 28 28 14 0 #rect
Pt0 f21 @|AlternativeIcon #fIcon
Pt0 f51 actionDecl 'ch.ivy.addon.portal.generic.PortalStartData out;
' #txt
Pt0 f51 actionTable 'out=in;
' #txt
Pt0 f51 actionCode 'import ch.ivy.addon.portalkit.enums.SessionAttribute;
import ch.ivy.addon.portalkit.util.SecurityServiceUtils;
SecurityServiceUtils.setSessionAttribute(SessionAttribute.PORTAL_START_PMV_ID.toString(), ivy.task.getProcessModelVersion().getId());
' #txt
Pt0 f51 security system #txt
Pt0 f51 type ch.ivy.addon.portal.generic.PortalStartData #txt
Pt0 f51 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>store portal start &#xD;
pmv </name>
    </language>
</elementInfo>
' #txt
Pt0 f51 152 146 128 44 -43 -16 #rect
Pt0 f51 @|StepIcon #fIcon
Pt0 f12 type ch.ivy.addon.portal.generic.PortalStartData #txt
Pt0 f12 processCall 'Functional Processes/OpenPortalTasks:useView(ch.ivy.addon.portal.generic.view.TaskView)' #txt
Pt0 f12 doCall true #txt
Pt0 f12 requestActionDecl '<ch.ivy.addon.portal.generic.view.TaskView taskView> param;
' #txt
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
Pt0 f12 2012 432 104 48 -46 -6 #rect
Pt0 f12 @|CallSubIcon #fIcon
Pt0 f10 outLink DefaultEndPage.ivp #txt
Pt0 f10 type ch.ivy.addon.portal.generic.PortalStartData #txt
Pt0 f10 inParamDecl '<java.lang.Number endedTaskId> param;' #txt
Pt0 f10 inParamTable 'out.endedTaskId=param.endedTaskId;
' #txt
Pt0 f10 actionDecl 'ch.ivy.addon.portal.generic.PortalStartData out;
' #txt
Pt0 f10 guid 166EC4390FC2DADF #txt
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
        <nameStyle>18,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Pt0 f10 @C|.responsibility Everybody #txt
Pt0 f10 51 444 26 26 -46 17 #rect
Pt0 f10 @|StartRequestIcon #fIcon
Pt0 f31 outLink DefaultLoginPage.ivp #txt
Pt0 f31 type ch.ivy.addon.portal.generic.PortalStartData #txt
Pt0 f31 inParamDecl '<java.lang.String originalUrl> param;' #txt
Pt0 f31 inParamTable 'out.callbackUrl=param.#originalUrl is initialized ? param.originalUrl : null;
' #txt
Pt0 f31 actionDecl 'ch.ivy.addon.portal.generic.PortalStartData out;
' #txt
Pt0 f31 guid 166EC4390FC9357E #txt
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
Pt0 f31 49 345 30 30 -45 18 #rect
Pt0 f31 @|StartRequestIcon #fIcon
Pt0 f52 actionDecl 'ch.ivy.addon.portal.generic.PortalStartData out;
' #txt
Pt0 f52 actionTable 'out=in;
' #txt
Pt0 f52 actionCode 'import ch.ivy.addon.portalkit.datamodel.CaseLazyDataModel;
import ch.ivy.addon.portalkit.datamodel.SearchResultsDataModel;

in.searchResultsDataModel = new SearchResultsDataModel();
in.searchResultsDataModel.taskDataModel = in.dataModel;
in.searchResultsDataModel.keyword = in.searchResultsDataModel.taskDataModel.criteria.keyword;
in.searchResultsDataModel.caseDataModel.notKeepFilter = true;
in.searchResultsDataModel.caseDataModel.criteria.keyword = in.searchResultsDataModel.taskDataModel.criteria.keyword;

' #txt
Pt0 f52 security system #txt
Pt0 f52 type ch.ivy.addon.portal.generic.PortalStartData #txt
Pt0 f52 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>build data model</name>
        <nameStyle>16,5
</nameStyle>
    </language>
</elementInfo>
' #txt
Pt0 f52 1800 530 112 44 -46 -8 #rect
Pt0 f52 @|StepIcon #fIcon
Pt0 f25 outLink restorePortalTaskList.ivp #txt
Pt0 f25 type ch.ivy.addon.portal.generic.PortalStartData #txt
Pt0 f25 inParamDecl '<java.lang.Number endedTaskId> param;' #txt
Pt0 f25 inParamTable 'out.endedTaskId=param.endedTaskId;
' #txt
Pt0 f25 actionDecl 'ch.ivy.addon.portal.generic.PortalStartData out;
' #txt
Pt0 f25 guid 166EC4390FDAF71B #txt
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
Pt0 f25 49 249 30 30 -49 15 #rect
Pt0 f25 @|StartRequestIcon #fIcon
Pt0 f4 type ch.ivy.addon.portal.generic.PortalStartData #txt
Pt0 f4 544 152 32 32 0 16 #rect
Pt0 f4 @|AlternativeIcon #fIcon
Pt0 f30 type ch.ivy.addon.portal.generic.PortalStartData #txt
Pt0 f30 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>is there callbackUrl?</name>
        <nameStyle>21
</nameStyle>
    </language>
</elementInfo>
' #txt
Pt0 f30 544 441 32 32 27 -7 #rect
Pt0 f30 @|AlternativeIcon #fIcon
Pt0 f37 type ch.ivy.addon.portal.generic.PortalStartData #txt
Pt0 f37 processCall 'Functional Processes/OpenPortalSearch:call(ch.ivy.addon.portalkit.datamodel.SearchResultsDataModel,Number)' #txt
Pt0 f37 doCall true #txt
Pt0 f37 requestActionDecl '<ch.ivy.addon.portalkit.datamodel.SearchResultsDataModel searchResultsDataModel,java.lang.Number activeTabIndex> param;
' #txt
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
Pt0 f37 2008 530 112 44 -50 -8 #rect
Pt0 f37 @|CallSubIcon #fIcon
Pt0 f43 type ch.ivy.addon.portal.generic.PortalStartData #txt
Pt0 f43 1360 152 32 32 0 16 #rect
Pt0 f43 @|AlternativeIcon #fIcon
Pt0 f93 actionDecl 'ch.ivy.addon.portal.generic.PortalStartData out;
' #txt
Pt0 f93 actionTable 'out=in;
' #txt
Pt0 f93 actionCode 'import ch.ivy.addon.portalkit.enums.PortalLibrary;
import org.apache.commons.lang3.StringUtils;
import ch.ivyteam.ivy.workflow.StandardProcessType;

String defaultEndPage = ivy.wf.getStandardProcessImplementationLibrary(StandardProcessType.DEFAULT_PAGES_PROCESS_TYPES);

if (StringUtils.isBlank(defaultEndPage)) {
	ivy.wf.setStandardProcessImplementationLibrary(StandardProcessType.DEFAULT_PAGES_PROCESS_TYPES, PortalLibrary.PORTAL_TEMPLATE.getValue());
}' #txt
Pt0 f93 security system #txt
Pt0 f93 type ch.ivy.addon.portal.generic.PortalStartData #txt
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
Pt0 f93 312 338 144 44 -53 -16 #rect
Pt0 f93 @|StepIcon #fIcon
Pt0 f20 actionDecl 'ch.ivy.addon.portal.generic.PortalStartData out;
' #txt
Pt0 f20 actionTable 'out=in;
' #txt
Pt0 f20 actionCode 'import ch.ivy.addon.portalkit.enums.PortalLibrary;
import org.apache.commons.lang3.StringUtils;
import ch.ivyteam.ivy.workflow.StandardProcessType;

String defaultEndPage = ivy.wf.getStandardProcessImplementationLibrary(StandardProcessType.DEFAULT_PAGES_PROCESS_TYPES);

if (StringUtils.isBlank(defaultEndPage)) {
	ivy.wf.setStandardProcessImplementationLibrary(StandardProcessType.DEFAULT_PAGES_PROCESS_TYPES, PortalLibrary.PORTAL_TEMPLATE.getValue());
}' #txt
Pt0 f20 security system #txt
Pt0 f20 type ch.ivy.addon.portal.generic.PortalStartData #txt
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
Pt0 f20 312 146 144 44 -53 -16 #rect
Pt0 f20 @|StepIcon #fIcon
Pt0 f41 type ch.ivy.addon.portal.generic.PortalStartData #txt
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
Pt0 f41 880 152 32 32 -59 18 #rect
Pt0 f41 @|AlternativeIcon #fIcon
Pt0 f59 actionDecl 'ch.ivy.addon.portal.generic.PortalStartData out;
' #txt
Pt0 f59 actionTable 'out=in;
' #txt
Pt0 f59 actionCode 'import ch.ivy.addon.portalkit.util.PermissionUtils;
import ch.ivy.addon.portalkit.util.SecurityServiceUtils;
import ch.ivy.addon.portal.generic.view.CaseView;

boolean hasReadAllCasesPermission = PermissionUtils.checkReadAllCasesPermission();
in.caseDataModel.setAdminQuery(hasReadAllCasesPermission);

in.caseView = CaseView.create().dataModel(in.caseDataModel).buildNewView();' #txt
Pt0 f59 type ch.ivy.addon.portal.generic.PortalStartData #txt
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
Pt0 f59 304 626 128 44 -43 -16 #rect
Pt0 f59 @|StepIcon #fIcon
Pt0 f70 outLink startPortalStatistic.ivp #txt
Pt0 f70 type ch.ivy.addon.portal.generic.PortalStartData #txt
Pt0 f70 inParamDecl '<> param;' #txt
Pt0 f70 actionDecl 'ch.ivy.addon.portal.generic.PortalStartData out;
' #txt
Pt0 f70 guid 166EC4390FE7C8E2 #txt
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
Pt0 f70 49 826 30 30 -58 17 #rect
Pt0 f70 @|StartRequestIcon #fIcon
Pt0 f64 type ch.ivy.addon.portal.generic.PortalStartData #txt
Pt0 f64 789 729 30 30 0 15 #rect
Pt0 f64 @|EndIcon #fIcon
Pt0 f0 outLink PortalStart.ivp #txt
Pt0 f0 type ch.ivy.addon.portal.generic.PortalStartData #txt
Pt0 f0 inParamDecl '<java.lang.String parameters,java.lang.String portalNavigator> param;' #txt
Pt0 f0 inParamTable 'out.parameters=param.parameters;
out.portalPage=param.parameters.isEmpty() ? ch.ivy.addon.portal.generic.navigation.PortalPage.HOME_PAGE : ch.ivy.addon.portal.generic.navigation.PortalPage.valueOf(param.portalNavigator);
' #txt
Pt0 f0 actionDecl 'ch.ivy.addon.portal.generic.PortalStartData out;
' #txt
Pt0 f0 guid 166EC4390FF4DC7B #txt
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
Pt0 f0 51 155 26 26 -31 17 #rect
Pt0 f0 @|StartRequestIcon #fIcon
Pt0 f57 type ch.ivy.addon.portal.generic.PortalStartData #txt
Pt0 f57 789 634 30 30 0 15 #rect
Pt0 f57 @|EndIcon #fIcon
Pt0 f92 actionDecl 'ch.ivy.addon.portal.generic.PortalStartData out;
' #txt
Pt0 f92 actionTable 'out=in;
' #txt
Pt0 f92 actionCode 'import ch.ivy.addon.portalkit.enums.SessionAttribute;
import ch.ivy.addon.portalkit.util.SecurityServiceUtils;
SecurityServiceUtils.setSessionAttribute(SessionAttribute.PORTAL_START_PMV_ID.toString(), ivy.task.getProcessModelVersion().getId());
' #txt
Pt0 f92 security system #txt
Pt0 f92 type ch.ivy.addon.portal.generic.PortalStartData #txt
Pt0 f92 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>store portal start &#xD;
pmv </name>
    </language>
</elementInfo>
' #txt
Pt0 f92 152 338 128 44 -43 -16 #rect
Pt0 f92 @|StepIcon #fIcon
Pt0 f58 outLink startPortalCase.ivp #txt
Pt0 f58 type ch.ivy.addon.portal.generic.PortalCaseData #txt
Pt0 f58 inParamDecl '<> param;' #txt
Pt0 f58 actionDecl 'ch.ivy.addon.portal.generic.PortalCaseData out;
' #txt
Pt0 f58 guid 166EC4390FFD4CE9 #txt
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
Pt0 f58 49 634 30 30 -52 17 #rect
Pt0 f58 @|StartRequestIcon #fIcon
Pt0 f28 outLink DefaultApplicationHomePage.ivp #txt
Pt0 f28 type ch.ivy.addon.portal.generic.PortalStartData #txt
Pt0 f28 inParamDecl '<> param;' #txt
Pt0 f28 actionDecl 'ch.ivy.addon.portal.generic.PortalStartData out;
' #txt
Pt0 f28 guid 166EC4390FF7B062 #txt
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
Pt0 f28 49 57 30 30 -45 18 #rect
Pt0 f28 @|StartRequestIcon #fIcon
Pt0 f9 type ch.ivy.addon.portal.generic.PortalStartData #txt
Pt0 f9 processCall 'Functional Processes/InitializeTaskDataModel:call()' #txt
Pt0 f9 doCall true #txt
Pt0 f9 requestActionDecl '<> param;
' #txt
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
Pt0 f9 976 146 144 44 -65 -8 #rect
Pt0 f9 @|CallSubIcon #fIcon
Pt0 f76 outLink startPortalProcess.ivp #txt
Pt0 f76 type ch.ivy.addon.portal.generic.PortalStartData #txt
Pt0 f76 inParamDecl '<> param;' #txt
Pt0 f76 actionDecl 'ch.ivy.addon.portal.generic.PortalStartData out;
' #txt
Pt0 f76 guid 166EC43910024A55 #txt
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
Pt0 f76 49 921 30 30 -60 17 #rect
Pt0 f76 @|StartRequestIcon #fIcon
Pt0 f62 type ch.ivy.addon.portal.generic.PortalStartData #txt
Pt0 f62 processCall 'Functional Processes/InitializeCaseDataModel:call()' #txt
Pt0 f62 doCall true #txt
Pt0 f62 requestActionDecl '<> param;
' #txt
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
Pt0 f62 156 627 112 44 -38 -20 #rect
Pt0 f62 @|CallSubIcon #fIcon
Pt0 f11 actionDecl 'ch.ivy.addon.portal.generic.PortalStartData out;
' #txt
Pt0 f11 actionTable 'out=in;
' #txt
Pt0 f11 actionCode 'import ch.ivy.addon.portalkit.service.StickyTaskListService;
import ch.ivy.addon.portalkit.enums.SessionAttribute;
import ch.ivy.addon.portalkit.util.SecurityServiceUtils;
import ch.ivy.addon.portal.generic.navigation.PortalPage;
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
		callbackUrl = taskWithTaskEndInfo.getAdditionalProperty(AdditionalProperty.PORTAL_TASK_CALLBACK_URI.toString());
	}
}

if (isTaskStarted && StringUtils.isNotBlank(callbackUrl)) {
	out.callbackUrl = callbackUrl + "?endedTaskId=" + taskWithTaskEndInfo.getId();
} else {
	out.portalPage = PortalPage.HOME_PAGE;
}' #txt
Pt0 f11 security system #txt
Pt0 f11 type ch.ivy.addon.portal.generic.PortalStartData #txt
Pt0 f11 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Handle end page</name>
        <nameStyle>15
</nameStyle>
    </language>
</elementInfo>
' #txt
Pt0 f11 160 435 112 44 -47 -8 #rect
Pt0 f11 @|StepIcon #fIcon
Pt0 f69 actionDecl 'ch.ivy.addon.portal.generic.PortalStartData out;
' #txt
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
Pt0 f69 type ch.ivy.addon.portal.generic.PortalStartData #txt
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
Pt0 f69 312 720 112 48 -39 -16 #rect
Pt0 f69 @|StepIcon #fIcon
Pt0 f3 actionDecl 'ch.ivy.addon.portal.generic.PortalStartData out;
' #txt
Pt0 f3 actionTable 'out=in;
' #txt
Pt0 f3 actionCode 'in.dataModel.compactMode = true;
in.isDataModelInitialized = true;' #txt
Pt0 f3 type ch.ivy.addon.portal.generic.PortalStartData #txt
Pt0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Initialize data model</name>
        <nameStyle>21
</nameStyle>
    </language>
</elementInfo>
' #txt
Pt0 f3 1176 146 128 44 -54 -8 #rect
Pt0 f3 @|StepIcon #fIcon
Pt0 f73 type ch.ivy.addon.portal.generic.PortalStartData #txt
Pt0 f73 processCall 'Functional Processes/InitializeTaskDataModel:call()' #txt
Pt0 f73 doCall true #txt
Pt0 f73 requestActionDecl '<> param;
' #txt
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
Pt0 f73 156 722 112 44 -40 -8 #rect
Pt0 f73 @|CallSubIcon #fIcon
Pt0 f55 type ch.ivy.addon.portal.generic.PortalStartData #txt
Pt0 f55 processCall 'Functional Processes/OpenPortalCases:useView(ch.ivy.addon.portal.generic.view.CaseView)' #txt
Pt0 f55 doCall true #txt
Pt0 f55 requestActionDecl '<ch.ivy.addon.portal.generic.view.CaseView view> param;
' #txt
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
Pt0 f55 504 625 112 48 -49 -12 #rect
Pt0 f55 @|CallSubIcon #fIcon
Pt0 f74 targetWindow NEW:card: #txt
Pt0 f74 targetDisplay TOP #txt
Pt0 f74 richDialogId ch.ivy.addon.portal.generic.Processes #txt
Pt0 f74 startMethod start(String) #txt
Pt0 f74 type ch.ivy.addon.portal.generic.PortalStartData #txt
Pt0 f74 requestActionDecl '<String menuState> param;' #txt
Pt0 f74 requestMappingAction 'param.menuState=ch.ivy.addon.portalkit.util.MenuUtils.getMenuState();
' #txt
Pt0 f74 responseActionDecl 'ch.ivy.addon.portal.generic.PortalStartData out;
' #txt
Pt0 f74 responseMappingAction 'out=in;
' #txt
Pt0 f74 windowConfiguration '* ' #txt
Pt0 f74 isAsynch false #txt
Pt0 f74 isInnerRd false #txt
Pt0 f74 userContext '* ' #txt
Pt0 f74 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>processes</name>
        <nameStyle>9,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Pt0 f74 164 916 104 40 -22 -8 #rect
Pt0 f74 @|RichDialogIcon #fIcon
Pt0 f33 targetWindow NEW #txt
Pt0 f33 targetDisplay TOP #txt
Pt0 f33 richDialogId ch.ivy.addon.portal.generic.PortalTaskListCallback #txt
Pt0 f33 startMethod start(String) #txt
Pt0 f33 type ch.ivy.addon.portal.generic.PortalStartData #txt
Pt0 f33 requestActionDecl '<String callbackUrl> param;' #txt
Pt0 f33 requestMappingAction 'param.callbackUrl=in.callbackUrl;
' #txt
Pt0 f33 responseActionDecl 'ch.ivy.addon.portal.generic.PortalStartData out;
' #txt
Pt0 f33 responseMappingAction 'out=in;
' #txt
Pt0 f33 isAsynch false #txt
Pt0 f33 isInnerRd false #txt
Pt0 f33 userContext '* ' #txt
Pt0 f33 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Redirect to
callbackUrl</name>
        <nameStyle>23
</nameStyle>
    </language>
</elementInfo>
' #txt
Pt0 f33 504 531 112 44 -30 -16 #rect
Pt0 f33 @|RichDialogIcon #fIcon
Pt0 f22 actionDecl 'ch.ivy.addon.portal.generic.PortalStartData out;
' #txt
Pt0 f22 actionTable 'out=in;
' #txt
Pt0 f22 actionCode 'import ch.ivy.addon.portal.generic.view.TaskView;

in.taskView = TaskView.create().dataModel(in.dataModel).showHeaderToolbar(true).noTaskFoundMessage("").createNewTaskView();
' #txt
Pt0 f22 type ch.ivy.addon.portal.generic.PortalStartData #txt
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
Pt0 f22 1800 144 112 48 -45 -17 #rect
Pt0 f22 @|StepIcon #fIcon
Pt0 f19 actionDecl 'ch.ivy.addon.portal.generic.PortalStartData out;
' #txt
Pt0 f19 actionTable 'out=in;
' #txt
Pt0 f19 actionCode 'import ch.ivy.addon.portalkit.enums.SessionAttribute;
import ch.ivy.addon.portalkit.bo.TaskNode;
import ch.ivy.addon.portalkit.enums.TaskSortField;
import ch.ivy.addon.portalkit.util.SecurityServiceUtils;
import ch.ivy.addon.portalkit.datamodel.TaskLazyDataModel;
import ch.ivy.addon.portalkit.bo.MainMenuNode;
import ch.ivy.addon.portal.generic.view.TaskView;
import java.util.Map;
import org.primefaces.extensions.util.json.GsonConverter;
import java.util.Arrays;

if (!in.isDataModelInitialized) {
	TaskNode taskCategory;
	try {
		taskCategory = SecurityServiceUtils.getSessionAttribute(SessionAttribute.TASK_CATEGORY.toString()) as TaskNode;
	} catch (Exception e) {}
	boolean canLinkBackToCaseDetail = in.#dataModel.#criteria.#caseId is initialized; 
	
	in.taskView = TaskView.create().category(#taskCategory).dataModel(in.dataModel)
												.canLinkBackCaseDetail(canLinkBackToCaseDetail).showHeaderToolbar(false)
												.taskId(in.selectedTaskId).createNewTaskView();
	
	SecurityServiceUtils.removeSessionAttribute(SessionAttribute.TASK_CATEGORY.toString());
} else {
	in.dataModel.compactMode = false;
	in.dataModel.getCriteria().setNewQueryCreated(true);
	in.dataModel.setAdminQuery(true);
	in.dataModel.setSortField(TaskSortField.PRIORITY.toString(), false);
		
	Map taskInfo = GsonConverter.getGson().fromJson(in.parameters,Map.class) as Map;
	long taskId = Long.parseLong(taskInfo.get("taskId") as String);
	in.dataModel.setTaskId(taskId);
		
	String pageTitle = ivy.cms.co("/Labels/Task");
	MainMenuNode category = new MainMenuNode();
	category.value = pageTitle;
	String noTaskMessage = ivy.cms.co("/ch.ivy.addon.portalkit.ui.jsf/taskWarning/taskNotFound");
		
	out.taskView = TaskView.create().dataModel(in.dataModel).pageTitle(pageTitle).category(category).taskId(taskId).hideTaskFilter(true).showHeaderToolbar(false).noTaskFoundMessage(noTaskMessage).createNewTaskView();
}' #txt
Pt0 f19 security system #txt
Pt0 f19 type ch.ivy.addon.portal.generic.PortalStartData #txt
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
Pt0 f19 1792 428 128 56 -43 -16 #rect
Pt0 f19 @|StepIcon #fIcon
Pt0 f84 type ch.ivy.addon.portal.generic.PortalStartData #txt
Pt0 f84 processCall 'Functional Processes/OpenPortalTasks:useView(ch.ivy.addon.portal.generic.view.TaskView)' #txt
Pt0 f84 doCall true #txt
Pt0 f84 requestActionDecl '<ch.ivy.addon.portal.generic.view.TaskView taskView> param;
' #txt
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
Pt0 f84 508 724 104 40 -48 -12 #rect
Pt0 f84 @|CallSubIcon #fIcon
Pt0 f13 targetWindow NEW:card: #txt
Pt0 f13 targetDisplay TOP #txt
Pt0 f13 richDialogId ch.ivy.addon.portal.generic.PortalHome #txt
Pt0 f13 startMethod start(ch.ivy.addon.portal.generic.view.TaskView) #txt
Pt0 f13 type ch.ivy.addon.portal.generic.PortalStartData #txt
Pt0 f13 requestActionDecl '<ch.ivy.addon.portal.generic.view.TaskView taskView> param;' #txt
Pt0 f13 requestMappingAction 'param.taskView=in.taskView;
' #txt
Pt0 f13 responseActionDecl 'ch.ivy.addon.portal.generic.PortalStartData out;
' #txt
Pt0 f13 responseMappingAction 'out=in;
' #txt
Pt0 f13 windowConfiguration '* ' #txt
Pt0 f13 isAsynch false #txt
Pt0 f13 isInnerRd false #txt
Pt0 f13 userContext '* ' #txt
Pt0 f13 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Portal Home Page</name>
        <nameStyle>16
</nameStyle>
    </language>
</elementInfo>
' #txt
Pt0 f13 2004 144 120 48 -45 -8 #rect
Pt0 f13 @|RichDialogIcon #fIcon
Pt0 f32 expr in #txt
Pt0 f32 outCond 'java.util.Objects.equals(ch.ivy.addon.portal.generic.navigation.PortalPage.HOME_PAGE, in.#portalPage) || java.util.Objects.isNull(in.#portalPage)' #txt
Pt0 f32 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>HOME_PAGE</name>
        <nameStyle>9
</nameStyle>
    </language>
</elementInfo>
' #txt
Pt0 f32 1598 168 1800 168 #arcP
Pt0 f32 0 0.32167832167832167 0 -9 #arcLabel
Pt0 f8 expr out #txt
Pt0 f8 1120 168 1176 168 #arcP
Pt0 f60 expr out #txt
Pt0 f60 432 648 504 649 #arcP
Pt0 f77 expr out #txt
Pt0 f77 79 744 156 744 #arcP
Pt0 f36 expr in #txt
Pt0 f36 outCond 'java.util.Objects.equals(ch.ivy.addon.portal.generic.navigation.PortalPage.ERROR_PAGE, in.#portalPage) ' #txt
Pt0 f36 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>ERROR_PAGE</name>
        <nameStyle>10
</nameStyle>
    </language>
</elementInfo>
' #txt
Pt0 f36 1590 176 1808 264 #arcP
Pt0 f36 1 1648 264 #addKink
Pt0 f36 1 0.37012987012987014 0 -13 #arcLabel
Pt0 f78 expr out #txt
Pt0 f78 79 841 156 841 #arcP
Pt0 f7 expr out #txt
Pt0 f7 280 264 547 171 #arcP
Pt0 f7 0 0.05098425735552965 0 0 #arcLabel
Pt0 f53 expr in #txt
Pt0 f53 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>SEARCH_RESULTS</name>
        <nameStyle>14,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Pt0 f53 1584 182 1800 552 #arcP
Pt0 f53 1 1584 552 #addKink
Pt0 f53 1 0.46987517298157394 0 -11 #arcLabel
Pt0 f50 expr out #txt
Pt0 f50 272 457 544 457 #arcP
Pt0 f44 expr out #txt
Pt0 f44 1304 168 1360 168 #arcP
Pt0 f65 expr out #txt
Pt0 f65 612 744 789 744 #arcP
Pt0 f61 expr out #txt
Pt0 f61 268 649 304 648 #arcP
Pt0 f79 expr out #txt
Pt0 f79 79 936 164 936 #arcP
Pt0 f46 expr out #txt
Pt0 f46 79 72 560 152 #arcP
Pt0 f46 1 560 72 #addKink
Pt0 f46 0 0.6515872258938749 0 0 #arcLabel
Pt0 f40 expr in #txt
Pt0 f40 1392 168 1570 168 #arcP
Pt0 f56 expr in #txt
Pt0 f56 outCond 'java.util.Objects.equals(ch.ivy.addon.portal.generic.navigation.PortalPage.LINK_TO_TASK, in.#portalPage)' #txt
Pt0 f56 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>LINK_TO_TASK</name>
        <nameStyle>12
</nameStyle>
    </language>
</elementInfo>
' #txt
Pt0 f56 1585 181 1792 456 #arcP
Pt0 f56 1 1616 456 #addKink
Pt0 f56 1 0.5125 0 -13 #arcLabel
Pt0 f1 expr out #txt
Pt0 f1 79 264 152 264 #arcP
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
Pt0 f35 912 168 976 168 #arcP
Pt0 f35 0 0.4305555555555556 0 -10 #arcLabel
Pt0 f67 expr out #txt
Pt0 f67 424 744 508 744 #arcP
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
Pt0 f45 896 152 1376 152 #arcP
Pt0 f45 1 896 96 #addKink
Pt0 f45 2 1376 96 #addKink
Pt0 f45 0 0.4166666666666667 -10 0 #arcLabel
Pt0 f118 expr out #txt
Pt0 f118 268 744 312 744 #arcP
Pt0 f48 expr out #txt
Pt0 f48 77 457 160 457 #arcP
Pt0 f5 expr out #txt
Pt0 f5 456 168 544 168 #arcP
Pt0 f14 expr out #txt
Pt0 f14 280 168 312 168 #arcP
Pt0 f39 expr out #txt
Pt0 f39 1920 456 2012 456 #arcP
Pt0 f39 0 0.7418894103244236 0 0 #arcLabel
Pt0 f15 expr in #txt
Pt0 f15 560 441 560 184 #arcP
Pt0 f15 0 0.4763694877411137 0 0 #arcLabel
Pt0 f95 expr out #txt
Pt0 f95 280 360 312 360 #arcP
Pt0 f71 expr out #txt
Pt0 f71 79 649 156 649 #arcP
Pt0 f66 expr out #txt
Pt0 f66 616 649 789 649 #arcP
Pt0 f49 expr out #txt
Pt0 f49 1912 552 2008 552 #arcP
Pt0 f47 expr out #txt
Pt0 f47 456 360 555 446 #arcP
Pt0 f47 1 512 360 #addKink
Pt0 f47 0 0.9638478923705887 0 0 #arcLabel
Pt0 f34 expr in #txt
Pt0 f34 outCond 'org.apache.commons.lang3.StringUtils.isNotBlank(in.#callbackUrl)' #txt
Pt0 f34 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>YES</name>
        <nameStyle>3
</nameStyle>
    </language>
</elementInfo>
' #txt
Pt0 f34 560 473 560 531 #arcP
Pt0 f34 0 0.3793103448275862 14 3 #arcLabel
Pt0 f24 expr out #txt
Pt0 f24 1912 168 2004 168 #arcP
Pt0 f24 0 0.37297345905809204 -1 -17 #arcLabel
Pt0 f98 expr out #txt
Pt0 f98 79 360 152 360 #arcP
Pt0 f98 0 0.5022241929816383 0 0 #arcLabel
Pt0 f54 expr out #txt
Pt0 f54 77 168 152 168 #arcP
Pt0 f103 outLink startPortalCaseDetails.ivp #txt
Pt0 f103 type ch.ivy.addon.portal.generic.CaseWidgetData #txt
Pt0 f103 inParamDecl '<java.lang.Long caseId> param;' #txt
Pt0 f103 inParamTable 'out.caseId=param.caseId;
' #txt
Pt0 f103 actionDecl 'ch.ivy.addon.portal.generic.CaseWidgetData out;
' #txt
Pt0 f103 guid 16BD5D42F6B954CD #txt
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
Pt0 f103 49 1009 30 30 -57 17 #rect
Pt0 f103 @|StartRequestIcon #fIcon
Pt0 f104 type ch.ivy.addon.portal.generic.PortalStartData #txt
Pt0 f104 processCall 'Functional Processes/OpenPortalCaseDetails:call(ch.ivyteam.ivy.workflow.ICase)' #txt
Pt0 f104 doCall true #txt
Pt0 f104 requestActionDecl '<ch.ivyteam.ivy.workflow.ICase caseData> param;
' #txt
Pt0 f104 requestMappingAction 'param.caseData=in.caseSelected;
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
Pt0 f104 360 1002 144 44 -65 -8 #rect
Pt0 f104 @|CallSubIcon #fIcon
Pt0 f105 type ch.ivy.addon.portal.generic.PortalStartData #txt
Pt0 f105 617 1009 30 30 0 15 #rect
Pt0 f105 @|EndIcon #fIcon
Pt0 f106 actionDecl 'ch.ivy.addon.portal.generic.PortalStartData out;
' #txt
Pt0 f106 actionTable 'out=in;
' #txt
Pt0 f106 actionCode 'out.caseSelected = ivy.wf.findCase(in.caseId);' #txt
Pt0 f106 security system #txt
Pt0 f106 type ch.ivy.addon.portal.generic.PortalStartData #txt
Pt0 f106 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Find Case</name>
    </language>
</elementInfo>
' #txt
Pt0 f106 176 1002 112 44 -28 -8 #rect
Pt0 f106 @|StepIcon #fIcon
Pt0 f108 expr out #txt
Pt0 f108 79 1024 176 1024 #arcP
Pt0 f109 expr out #txt
Pt0 f109 504 1024 617 1024 #arcP
Pt0 f107 expr out #txt
Pt0 f107 288 1024 360 1024 #arcP
Pt0 f110 outLink startPortalTaskDetail.ivp #txt
Pt0 f110 type ch.ivy.addon.portal.generic.PortalStartData #txt
Pt0 f110 inParamDecl '<java.lang.Long taskId> param;' #txt
Pt0 f110 inParamTable 'out.taskId=param.taskId;
' #txt
Pt0 f110 actionDecl 'ch.ivy.addon.portal.generic.PortalStartData out;
' #txt
Pt0 f110 guid 16C4217ED029B3C0 #txt
Pt0 f110 requestEnabled true #txt
Pt0 f110 triggerEnabled false #txt
Pt0 f110 callSignature startPortalTaskDetail(Long) #txt
Pt0 f110 persist false #txt
Pt0 f110 caseData businessCase.attach=true #txt
Pt0 f110 showInStartList 0 #txt
Pt0 f110 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>startPortalTaskDetail.ivp</name>
    </language>
</elementInfo>
' #txt
Pt0 f110 @C|.responsibility Everybody #txt
Pt0 f110 49 1121 30 30 -55 34 #rect
Pt0 f110 @|StartRequestIcon #fIcon
Pt0 f117 actionDecl 'ch.ivy.addon.portal.generic.PortalStartData out;
' #txt
Pt0 f117 actionTable 'out=in;
' #txt
Pt0 f117 actionCode 'out.taskSelected = ivy.wf.findTask(in.taskId);' #txt
Pt0 f117 security system #txt
Pt0 f117 type ch.ivy.addon.portal.generic.PortalStartData #txt
Pt0 f117 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Find Task</name>
    </language>
</elementInfo>
' #txt
Pt0 f117 176 1114 112 44 -27 -8 #rect
Pt0 f117 @|StepIcon #fIcon
Pt0 f112 type ch.ivy.addon.portal.generic.PortalStartData #txt
Pt0 f112 641 1121 30 30 0 15 #rect
Pt0 f112 @|EndIcon #fIcon
Pt0 f115 type ch.ivy.addon.portal.generic.PortalStartData #txt
Pt0 f115 processCall 'Functional Processes/OpenPortalTaskDetail:call(ch.ivyteam.ivy.workflow.ITask)' #txt
Pt0 f115 doCall true #txt
Pt0 f115 requestActionDecl '<ch.ivyteam.ivy.workflow.ITask taskData> param;
' #txt
Pt0 f115 requestMappingAction 'param.taskData=in.taskSelected;
' #txt
Pt0 f115 responseActionDecl 'ch.ivy.addon.portal.generic.PortalStartData out;
' #txt
Pt0 f115 responseMappingAction 'out=in;
' #txt
Pt0 f115 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>OpenPortalTaskDetail</name>
    </language>
</elementInfo>
' #txt
Pt0 f115 356 1112 152 48 -60 -8 #rect
Pt0 f115 @|CallSubIcon #fIcon
Pt0 f114 expr out #txt
Pt0 f114 508 1136 641 1136 #arcP
Pt0 f116 expr out #txt
Pt0 f116 288 1136 356 1136 #arcP
Pt0 f119 expr out #txt
Pt0 f119 79 1136 176 1136 #arcP
Pt0 f42 expr in #txt
Pt0 f42 576 168 880 168 #arcP
Pt0 f23 type ch.ivy.addon.portal.generic.PortalStartData #txt
Pt0 f23 processCall 'Functional Processes/OpenPortalCaseDetails:call(ch.ivyteam.ivy.workflow.ICase)' #txt
Pt0 f23 doCall true #txt
Pt0 f23 requestActionDecl '<ch.ivyteam.ivy.workflow.ICase caseData> param;
' #txt
Pt0 f23 requestMappingAction 'param.caseData=in.caseSelected;
' #txt
Pt0 f23 responseActionDecl 'ch.ivy.addon.portal.generic.PortalStartData out;
' #txt
Pt0 f23 responseMappingAction 'out=in;
' #txt
Pt0 f23 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>OpenPortalCaseDetail</name>
        <nameStyle>20,5
</nameStyle>
    </language>
</elementInfo>
' #txt
Pt0 f23 1992 338 128 44 -61 -8 #rect
Pt0 f23 @|CallSubIcon #fIcon
Pt0 f17 actionDecl 'ch.ivy.addon.portal.generic.PortalStartData out;
' #txt
Pt0 f17 actionTable 'out=in;
' #txt
Pt0 f17 actionCode 'import ch.ivyteam.ivy.workflow.ICase;
import ch.ivyteam.ivy.workflow.query.CaseQuery;
import java.util.Map;
import org.primefaces.extensions.util.json.GsonConverter;

Map caseInfor = GsonConverter.getGson().fromJson(in.parameters,Map.class) as Map;
long caseId = Long.parseLong(caseInfor.get("caseId") as String); 
out.caseSelected = ivy.wf.getGlobalContext().getCaseQueryExecutor().getFirstResult(CaseQuery.create().where().caseId().isEqual(caseId)) as ICase;' #txt
Pt0 f17 security system #txt
Pt0 f17 type ch.ivy.addon.portal.generic.PortalStartData #txt
Pt0 f17 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Find case</name>
    </language>
</elementInfo>
' #txt
Pt0 f17 1816 338 112 44 -27 -8 #rect
Pt0 f17 @|StepIcon #fIcon
Pt0 f26 expr out #txt
Pt0 f26 1928 360 1992 360 #arcP
Pt0 f6 expr in #txt
Pt0 f6 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>CASE_DETAIL_FROM_TASK</name>
    </language>
</elementInfo>
' #txt
Pt0 f6 1588 179 1816 360 #arcP
Pt0 f6 1 1648 360 #addKink
Pt0 f6 0 1.0 65 22 #arcLabel
>Proto Pt0 .type ch.ivy.addon.portal.generic.PortalStartData #txt
>Proto Pt0 .processKind NORMAL #txt
>Proto Pt0 0 0 32 24 18 0 #rect
>Proto Pt0 @|BIcon #fIcon
Pt0 f25 mainOut f1 tail #connect
Pt0 f1 head f2 mainIn #connect
Pt0 f20 mainOut f5 tail #connect
Pt0 f5 head f4 in #connect
Pt0 f2 mainOut f7 tail #connect
Pt0 f7 head f4 in #connect
Pt0 f30 out f34 tail #connect
Pt0 f34 head f33 mainIn #connect
Pt0 f30 out f15 tail #connect
Pt0 f15 head f4 in #connect
Pt0 f21 out f36 tail #connect
Pt0 f36 head f18 mainIn #connect
Pt0 f19 mainOut f39 tail #connect
Pt0 f39 head f12 mainIn #connect
Pt0 f21 out f32 tail #connect
Pt0 f32 head f22 mainIn #connect
Pt0 f22 mainOut f24 tail #connect
Pt0 f24 head f13 mainIn #connect
Pt0 f9 mainOut f8 tail #connect
Pt0 f8 head f3 mainIn #connect
Pt0 f41 out f35 tail #connect
Pt0 f35 head f9 mainIn #connect
Pt0 f3 mainOut f44 tail #connect
Pt0 f44 head f43 in #connect
Pt0 f43 out f40 tail #connect
Pt0 f40 head f21 in #connect
Pt0 f41 out f45 tail #connect
Pt0 f45 head f43 in #connect
Pt0 f10 mainOut f48 tail #connect
Pt0 f48 head f11 mainIn #connect
Pt0 f11 mainOut f50 tail #connect
Pt0 f50 head f30 in #connect
Pt0 f28 mainOut f46 tail #connect
Pt0 f46 head f4 in #connect
Pt0 f53 head f52 mainIn #connect
Pt0 f21 out f56 tail #connect
Pt0 f56 head f19 mainIn #connect
Pt0 f21 out f53 tail #connect
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
Pt0 f70 mainOut f78 tail #connect
Pt0 f78 head f82 mainIn #connect
Pt0 f76 mainOut f79 tail #connect
Pt0 f79 head f74 mainIn #connect
Pt0 f92 mainOut f95 tail #connect
Pt0 f95 head f93 mainIn #connect
Pt0 f31 mainOut f98 tail #connect
Pt0 f98 head f92 mainIn #connect
Pt0 f93 mainOut f47 tail #connect
Pt0 f47 head f30 in #connect
Pt0 f103 mainOut f108 tail #connect
Pt0 f108 head f106 mainIn #connect
Pt0 f106 mainOut f107 tail #connect
Pt0 f107 head f104 mainIn #connect
Pt0 f104 mainOut f109 tail #connect
Pt0 f109 head f105 mainIn #connect
Pt0 f115 mainOut f114 tail #connect
Pt0 f114 head f112 mainIn #connect
Pt0 f110 mainOut f119 tail #connect
Pt0 f119 head f117 mainIn #connect
Pt0 f117 mainOut f116 tail #connect
Pt0 f116 head f115 mainIn #connect
Pt0 f4 out f42 tail #connect
Pt0 f42 head f41 in #connect
Pt0 f17 mainOut f26 tail #connect
Pt0 f26 head f23 mainIn #connect
Pt0 f21 out f6 tail #connect
Pt0 f6 head f17 mainIn #connect
