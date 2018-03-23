[Ivy]
15B0ED067061F4DE 3.20 #module
>Proto >Proto Collection #zClass
Pt0 InternalSupportPortalStart Big #zClass
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
Pt0 @Alternative f43 '' #zField
Pt0 @Alternative f4 '' #zField
Pt0 @RichDialog f18 '' #zField
Pt0 @GridStep f22 '' #zField
Pt0 @RichDialog f13 '' #zField
Pt0 @StartRequest f0 '' #zField
Pt0 @CallSub f23 '' #zField
Pt0 @Alternative f21 '' #zField
Pt0 @CallSub f12 '' #zField
Pt0 @GridStep f17 '' #zField
Pt0 @Alternative f41 '' #zField
Pt0 @StartRequest f25 '' #zField
Pt0 @CallSub f9 '' #zField
Pt0 @EndTask f27 '' #zField
Pt0 @GridStep f2 '' #zField
Pt0 @StartRequest f10 '' #zField
Pt0 @GridStep f11 '' #zField
Pt0 @GridStep f20 '' #zField
Pt0 @Alternative f30 '' #zField
Pt0 @RichDialog f33 '' #zField
Pt0 @GridStep f19 '' #zField
Pt0 @GridStep f3 '' #zField
Pt0 @PushWFArc f26 '' #zField
Pt0 @PushWFArc f45 '' #zField
Pt0 @PushWFArc f24 '' #zField
Pt0 @PushWFArc f7 '' #zField
Pt0 @PushWFArc f36 '' #zField
Pt0 @PushWFArc f35 '' #zField
Pt0 @PushWFArc f15 '' #zField
Pt0 @PushWFArc f39 '' #zField
Pt0 @PushWFArc f8 '' #zField
Pt0 @PushWFArc f1 '' #zField
Pt0 @PushWFArc f42 '' #zField
Pt0 @PushWFArc f40 '' #zField
Pt0 @PushWFArc f32 '' #zField
Pt0 @PushWFArc f28 '' #zField
Pt0 @PushWFArc f44 '' #zField
Pt0 @PushWFArc f14 '' #zField
Pt0 @PushWFArc f37 '' #zField
Pt0 @PushWFArc f34 '' #zField
Pt0 @PushWFArc f31 '' #zField
Pt0 @PushWFArc f5 '' #zField
Pt0 @PushWFArc f29 '' #zField
Pt0 @CallSub f6 '' #zField
Pt0 @PushWFArc f16 '' #zField
Pt0 @PushWFArc f38 '' #zField
>Proto Pt0 Pt0 InternalSupportPortalStart #zField
Pt0 f43 type ch.ivy.addon.portal.generic.PortalStartData #txt
Pt0 f43 946 110 32 32 0 16 #rect
Pt0 f43 @|AlternativeIcon #fIcon
Pt0 f4 type ch.ivy.addon.portal.generic.PortalStartData #txt
Pt0 f4 370 110 32 32 0 16 #rect
Pt0 f4 @|AlternativeIcon #fIcon
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
Pt0 f18 1210 190 96 48 -22 -8 #rect
Pt0 f18 @|RichDialogIcon #fIcon
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
Pt0 f22 1216 114 36 24 20 -2 #rect
Pt0 f22 @|StepIcon #fIcon
Pt0 f13 targetWindow NEW:card: #txt
Pt0 f13 targetDisplay TOP #txt
Pt0 f13 richDialogId internalPortal.PortalHome #txt
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
Pt0 f13 1398 102 120 48 -45 -8 #rect
Pt0 f13 @|RichDialogIcon #fIcon
Pt0 f0 outLink PortalStart.ivp #txt
Pt0 f0 type ch.ivy.addon.portal.generic.PortalStartData #txt
Pt0 f0 inParamDecl '<java.lang.String parameters,java.lang.String portalNavigator> param;' #txt
Pt0 f0 inParamTable 'out.parameters=param.parameters;
out.portalPage=param.parameters.isEmpty() ? ch.ivy.addon.portal.generic.navigation.PortalPage.HOME_PAGE : ch.ivy.addon.portal.generic.navigation.PortalPage.valueOf(param.portalNavigator);
' #txt
Pt0 f0 actionDecl 'ch.ivy.addon.portal.generic.PortalStartData out;
' #txt
Pt0 f0 guid 15DBBC83FF8173F2 #txt
Pt0 f0 requestEnabled true #txt
Pt0 f0 triggerEnabled false #txt
Pt0 f0 callSignature PortalStart(String,String) #txt
Pt0 f0 persist false #txt
Pt0 f0 startName <%=ivy.cms.co("/Processes/portalHome")%> #txt
Pt0 f0 taskData 'TaskTriggered.DESC=<%\=ivy.cms.co("/Processes/Cases/PortalInternalProcess/PortalInternalProcessDescription")%>
TaskTriggered.EXTYPE=0
TaskTriggered.CATEGORY=<%\=ivy.cms.co("/Processes/Cases/PortalCategory")%>
TaskTriggered.TYPE=0
TaskTriggered.PRI=2
TaskTriggered.EXPRI=2
TaskTriggered.NAM=<%\=ivy.cms.co("/Processes/Cases/PortalCategory")%>\: <%\=ivy.cms.co("/Processes/portalSettingSaved")%>
TaskTriggered.ROL=Everybody
TaskTriggered.EXROL=Everybody' #txt
Pt0 f0 caseData 'case.name=<%\=ivy.cms.co("/Processes/Cases/PortalCategory")%>\: <%\=ivy.cms.co("/Processes/portalSettingSaved")%>
case.description=<%\=ivy.cms.co("/Processes/Cases/PortalInternalProcess/PortalInternalProcessDescription")%>
case.category=<%\=ivy.cms.co("/Processes/Cases/PortalCategory")%>
businessCase.attach=true' #txt
Pt0 f0 showInStartList 1 #txt
Pt0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>PortalStart.ivp</name>
        <nameStyle>15,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Pt0 f0 @C|.responsibility Everybody #txt
Pt0 f0 55 113 26 26 -31 17 #rect
Pt0 f0 @|StartRequestIcon #fIcon
Pt0 f23 type ch.ivy.addon.portal.generic.PortalStartData #txt
Pt0 f23 processCall 'Functional Processes/OpenPortalCases:useView(ch.ivy.addon.portal.generic.view.CaseView)' #txt
Pt0 f23 doCall true #txt
Pt0 f23 requestActionDecl '<ch.ivy.addon.portal.generic.view.CaseView view> param;
' #txt
Pt0 f23 requestMappingAction 'param.view=in.caseView;
' #txt
Pt0 f23 responseActionDecl 'ch.ivy.addon.portal.generic.PortalStartData out;
' #txt
Pt0 f23 responseMappingAction 'out=in;
' #txt
Pt0 f23 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>OpenPortalCases</name>
        <nameStyle>15,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Pt0 f23 1536 280 112 48 -49 -12 #rect
Pt0 f23 @|CallSubIcon #fIcon
Pt0 f21 type ch.ivy.addon.portal.generic.PortalStartData #txt
Pt0 f21 1044 112 28 28 14 0 #rect
Pt0 f21 @|AlternativeIcon #fIcon
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
Pt0 f12 1358 390 104 48 -46 -6 #rect
Pt0 f12 @|CallSubIcon #fIcon
Pt0 f17 actionDecl 'ch.ivy.addon.portal.generic.PortalStartData out;
' #txt
Pt0 f17 actionTable 'out=in;
' #txt
Pt0 f17 actionCode 'import java.util.Map;
import org.primefaces.extensions.util.json.GsonConverter;
import ch.ivy.addon.portalkit.dto.GlobalCaseId;
import java.util.Arrays;
import ch.ivy.addon.portalkit.datamodel.CaseLazyDataModel;
import ch.ivy.addon.portal.generic.view.CaseView;

Map caseInfor = GsonConverter.getGson().fromJson(in.parameters,Map.class) as Map;
long caseId = Long.parseLong(caseInfor.get("caseId") as String); 
String caseName = caseInfor.get("caseName") as String;
long serverId = Long.parseLong(caseInfor.get("serverId") as String);

String title = ivy.cms.co("/ch.ivy.addon.portalkit.ui.jsf/caseList/relatedCaseHeader", Arrays.asList(caseId.toString(), caseName));

in.caseDataModel.setCaseId(caseId);
out.caseView = CaseView.create().dataModel(in.caseDataModel).withTitle(title).autoSelectIfExists(GlobalCaseId.inServer(serverId).caseId(caseId).build()).buildNewView();' #txt
Pt0 f17 type ch.ivy.addon.portal.generic.PortalStartData #txt
Pt0 f17 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>prepare case view</name>
        <nameStyle>17,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Pt0 f17 1380 280 104 48 -46 -4 #rect
Pt0 f17 @|StepIcon #fIcon
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
Pt0 f41 466 110 32 32 -59 18 #rect
Pt0 f41 @|AlternativeIcon #fIcon
Pt0 f25 outLink restorePortalTaskList.ivp #txt
Pt0 f25 type ch.ivy.addon.portal.generic.PortalStartData #txt
Pt0 f25 inParamDecl '<java.lang.Number endedTaskId> param;' #txt
Pt0 f25 inParamTable 'out.endedTaskId=param.endedTaskId;
' #txt
Pt0 f25 actionDecl 'ch.ivy.addon.portal.generic.PortalStartData out;
' #txt
Pt0 f25 guid 15DBBC83FFA20596 #txt
Pt0 f25 requestEnabled true #txt
Pt0 f25 triggerEnabled false #txt
Pt0 f25 callSignature restorePortalTaskList(Number) #txt
Pt0 f25 persist false #txt
Pt0 f25 taskData 'TaskTriggered.ROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.EXPRI=2
TaskTriggered.TYPE=0
TaskTriggered.PRI=2
TaskTriggered.EXROL=Everybody' #txt
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
Pt0 f25 53 239 30 30 -49 15 #rect
Pt0 f25 @|StartRequestIcon #fIcon
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
Pt0 f9 570 104 144 44 -65 -8 #rect
Pt0 f9 @|CallSubIcon #fIcon
Pt0 f27 type ch.ivy.addon.portal.generic.PortalStartData #txt
Pt0 f27 503 464 30 30 0 15 #rect
Pt0 f27 @|EndIcon #fIcon
Pt0 f2 actionDecl 'ch.ivy.addon.portal.generic.PortalStartData out;
' #txt
Pt0 f2 actionTable 'out=in;
' #txt
Pt0 f2 actionCode 'import ch.ivyteam.ivy.workflow.ITask;
import ch.ivy.addon.portalkit.util.SecurityServiceUtils;
import ch.ivy.addon.portalkit.dto.TaskEndInfo;
import ch.ivy.addon.portalkit.service.StickyTaskListService;
import ch.ivy.addon.portal.generic.navigation.PortalPage;

ITask task = ivy.wf.findTask(in.endedTaskId);
ITask taskWithTaskEndInfo = StickyTaskListService.service().getPreviousTaskWithTaskEndInfo(task);
String taskEndInfoSessionAttributeKey = StickyTaskListService.service().getTaskEndInfoSessionAttributeKey(taskWithTaskEndInfo.getId());
TaskEndInfo taskEndInfo = SecurityServiceUtils.getSessionAttribute(taskEndInfoSessionAttributeKey) as TaskEndInfo;

in.dataModel = taskEndInfo.dataModel;
in.portalPage = taskEndInfo.isFromPortalHome ? PortalPage.HOME_PAGE : PortalPage.LINK_TO_TASK;
SecurityServiceUtils.removeSessionAttribute(taskEndInfoSessionAttributeKey);' #txt
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
Pt0 f2 162 232 128 44 -44 -16 #rect
Pt0 f2 @|StepIcon #fIcon
Pt0 f10 outLink DefaultEndPage.ivp #txt
Pt0 f10 type ch.ivy.addon.portal.generic.PortalStartData #txt
Pt0 f10 inParamDecl '<java.lang.Number endedTaskId> param;' #txt
Pt0 f10 inParamTable 'out.endedTaskId=param.endedTaskId;
' #txt
Pt0 f10 actionDecl 'ch.ivy.addon.portal.generic.PortalStartData out;
' #txt
Pt0 f10 guid 15DBBC83FFAB27B2 #txt
Pt0 f10 requestEnabled true #txt
Pt0 f10 triggerEnabled false #txt
Pt0 f10 callSignature DefaultEndPage(Number) #txt
Pt0 f10 persist false #txt
Pt0 f10 taskData 'TaskTriggered.ROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.EXPRI=2
TaskTriggered.TYPE=0
TaskTriggered.PRI=2
TaskTriggered.EXROL=Everybody' #txt
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
Pt0 f10 55 370 26 26 -46 17 #rect
Pt0 f10 @|StartRequestIcon #fIcon
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
Pt0 f11 150 361 112 44 -47 -8 #rect
Pt0 f11 @|StepIcon #fIcon
Pt0 f20 actionDecl 'ch.ivy.addon.portal.generic.PortalStartData out;
' #txt
Pt0 f20 actionTable 'out=in;
' #txt
Pt0 f20 actionCode 'import org.apache.commons.lang3.StringUtils;
import ch.ivy.addon.portalkit.enums.SessionAttribute;
import ch.ivy.addon.portalkit.util.SecurityServiceUtils;
import ch.ivyteam.ivy.workflow.StandardProcessType;

String defaultEndPage = ivy.wf.getStandardProcessImplementationLibrary(StandardProcessType.DEFAULT_PAGES_PROCESS_TYPES);

if (StringUtils.isBlank(defaultEndPage)) {
	String defaultProcessLibraryName = "ch.ivyteam.ivy.project.portal:portalTemplate";
	ivy.wf.setStandardProcessImplementationLibrary(StandardProcessType.DEFAULT_PAGES_PROCESS_TYPES, defaultProcessLibraryName);
}' #txt
Pt0 f20 security system #txt
Pt0 f20 type ch.ivy.addon.portal.generic.PortalStartData #txt
Pt0 f20 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Set default end page 
to Portal and store the 
last page to session</name>
        <nameStyle>67
</nameStyle>
    </language>
</elementInfo>
' #txt
Pt0 f20 146 96 144 60 -54 -24 #rect
Pt0 f20 @|StepIcon #fIcon
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
Pt0 f30 370 367 32 32 -118 5 #rect
Pt0 f30 @|AlternativeIcon #fIcon
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
Pt0 f33 330 457 112 44 -30 -16 #rect
Pt0 f33 @|RichDialogIcon #fIcon
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
	boolean canLinkBackToCaseDetail = in.#dataModel.#queryCriteria.#caseId is initialized; 
	
	in.taskView = TaskView.create().category(#taskCategory).dataModel(in.dataModel)
												.canLinkBackCaseDetail(canLinkBackToCaseDetail).showHeaderToolbar(false)
												.remoteTaskId(in.selectedTaskId).createNewTaskView();
	
	SecurityServiceUtils.removeSessionAttribute(SessionAttribute.TASK_CATEGORY.toString());
} else {
	in.dataModel.compactMode = false;
	in.dataModel.getQueryCriteria().setNewQueryCreated(true);
	in.dataModel.setIgnoreInvolvedUser(true);
	in.dataModel.setSortField(TaskSortField.PRIORITY.toString(), false);
		
	Map taskInfo = GsonConverter.getGson().fromJson(in.parameters,Map.class) as Map;
	long taskId = Long.parseLong(taskInfo.get("taskId") as String);
	in.dataModel.setTaskId(taskId);
		
	String pageTitle = ivy.cms.co("/Labels/Task");
	MainMenuNode category = new MainMenuNode();
	category.value = pageTitle;
	String noTaskMessage = ivy.cms.co("/ch.ivy.addon.portalkit.ui.jsf/taskWarning/taskNotFound");
		
	out.taskView = TaskView.create().dataModel(in.dataModel).pageTitle(pageTitle).category(category).remoteTaskId(taskId).hideTaskFilter(true).showHeaderToolbar(false).noTaskFoundMessage(noTaskMessage).createNewTaskView();
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
Pt0 f19 1162 386 128 56 -43 -16 #rect
Pt0 f19 @|StepIcon #fIcon
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
Pt0 f3 762 104 128 44 -54 -8 #rect
Pt0 f3 @|StepIcon #fIcon
Pt0 f26 expr out #txt
Pt0 f26 1484 304 1536 304 #arcP
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
Pt0 f45 482 110 962 110 #arcP
Pt0 f45 1 482 64 #addKink
Pt0 f45 2 962 64 #addKink
Pt0 f45 0 0.4166666666666667 -10 0 #arcLabel
Pt0 f24 expr out #txt
Pt0 f24 1252 126 1398 126 #arcP
Pt0 f24 0 0.37297345905809204 -1 -17 #arcLabel
Pt0 f7 expr out #txt
Pt0 f7 290 254 386 142 #arcP
Pt0 f7 1 386 254 #addKink
Pt0 f7 0 0.7634111543228898 0 0 #arcLabel
Pt0 f36 expr in #txt
Pt0 f36 outCond 'java.util.Objects.equals(ch.ivy.addon.portal.generic.navigation.PortalPage.ERROR_PAGE ,in.portalPage) ' #txt
Pt0 f36 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>ERROR_PAGE</name>
        <nameStyle>10
</nameStyle>
    </language>
</elementInfo>
' #txt
Pt0 f36 1058 140 1210 214 #arcP
Pt0 f36 1 1058 214 #addKink
Pt0 f36 1 0.37012987012987014 0 -13 #arcLabel
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
Pt0 f35 498 126 570 126 #arcP
Pt0 f35 0 0.4305555555555556 1 -10 #arcLabel
Pt0 f15 expr in #txt
Pt0 f15 386 367 386 142 #arcP
Pt0 f15 0 0.47620766243207985 0 0 #arcLabel
Pt0 f39 expr out #txt
Pt0 f39 1290 414 1358 414 #arcP
Pt0 f39 0 0.7418894103244236 0 0 #arcLabel
Pt0 f8 expr out #txt
Pt0 f8 714 126 762 126 #arcP
Pt0 f1 expr out #txt
Pt0 f1 83 254 162 254 #arcP
Pt0 f42 expr in #txt
Pt0 f42 402 126 466 126 #arcP
Pt0 f40 expr in #txt
Pt0 f40 978 126 1044 126 #arcP
Pt0 f32 expr in #txt
Pt0 f32 outCond 'java.util.Objects.equals(ch.ivy.addon.portal.generic.navigation.PortalPage.HOME_PAGE, in.portalPage) || java.util.Objects.isNull(in.portalPage)' #txt
Pt0 f32 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>HOME_PAGE</name>
        <nameStyle>9
</nameStyle>
    </language>
</elementInfo>
' #txt
Pt0 f32 1072 126 1216 126 #arcP
Pt0 f32 0 0.32167832167832167 0 -9 #arcLabel
Pt0 f28 expr out #txt
Pt0 f28 81 383 150 383 #arcP
Pt0 f44 expr out #txt
Pt0 f44 890 126 946 126 #arcP
Pt0 f14 expr out #txt
Pt0 f14 81 126 146 126 #arcP
Pt0 f37 expr in #txt
Pt0 f37 outCond 'java.util.Objects.equals(ch.ivy.addon.portal.generic.navigation.PortalPage.LINK_TO_TASK, in.portalPage)' #txt
Pt0 f37 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>LINK_TO_TASK</name>
        <nameStyle>12
</nameStyle>
    </language>
</elementInfo>
' #txt
Pt0 f37 1058 140 1162 414 #arcP
Pt0 f37 1 1058 414 #addKink
Pt0 f37 0 0.9525547445255474 47 0 #arcLabel
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
Pt0 f34 386 399 386 457 #arcP
Pt0 f34 0 0.3793103448275862 13 -1 #arcLabel
Pt0 f31 expr out #txt
Pt0 f31 262 383 370 383 #arcP
Pt0 f5 expr out #txt
Pt0 f5 290 126 370 126 #arcP
Pt0 f29 expr out #txt
Pt0 f29 442 479 503 479 #arcP
Pt0 f29 0 0.2732919254658385 -1 -8 #arcLabel
Pt0 f6 type ch.ivy.addon.portal.generic.PortalStartData #txt
Pt0 f6 processCall 'Functional Processes/InitializeCaseDataModel:call()' #txt
Pt0 f6 doCall true #txt
Pt0 f6 requestActionDecl '<> param;
' #txt
Pt0 f6 responseActionDecl 'ch.ivy.addon.portal.generic.PortalStartData out;
' #txt
Pt0 f6 responseMappingAction 'out=in;
out.caseDataModel=result.caseDataModel;
' #txt
Pt0 f6 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>InitializeCaseDataModel</name>
    </language>
</elementInfo>
' #txt
Pt0 f6 1200 282 144 44 -66 -8 #rect
Pt0 f6 @|CallSubIcon #fIcon
Pt0 f16 expr in #txt
Pt0 f16 outCond 'java.util.Objects.equals(ch.ivy.addon.portal.generic.navigation.PortalPage.CASE_DETAIL_FROM_TASK, in.portalPage)' #txt
Pt0 f16 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>CASE_DETAIL_FROM_TASK</name>
        <nameStyle>21
</nameStyle>
    </language>
</elementInfo>
' #txt
Pt0 f16 1058 140 1200 304 #arcP
Pt0 f16 1 1058 304 #addKink
Pt0 f16 0 0.7621951219512195 85 0 #arcLabel
Pt0 f38 expr out #txt
Pt0 f38 1344 304 1380 304 #arcP
Pt0 f38 0 0.5114942528735632 0 -12 #arcLabel
>Proto Pt0 .type ch.ivy.addon.portal.generic.PortalStartData #txt
>Proto Pt0 .processKind NORMAL #txt
>Proto Pt0 0 0 32 24 18 0 #rect
>Proto Pt0 @|BIcon #fIcon
Pt0 f0 mainOut f14 tail #connect
Pt0 f14 head f20 mainIn #connect
Pt0 f25 mainOut f1 tail #connect
Pt0 f1 head f2 mainIn #connect
Pt0 f20 mainOut f5 tail #connect
Pt0 f5 head f4 in #connect
Pt0 f2 mainOut f7 tail #connect
Pt0 f7 head f4 in #connect
Pt0 f10 mainOut f28 tail #connect
Pt0 f28 head f11 mainIn #connect
Pt0 f11 mainOut f31 tail #connect
Pt0 f31 head f30 in #connect
Pt0 f30 out f34 tail #connect
Pt0 f34 head f33 mainIn #connect
Pt0 f33 mainOut f29 tail #connect
Pt0 f29 head f27 mainIn #connect
Pt0 f30 out f15 tail #connect
Pt0 f15 head f4 in #connect
Pt0 f21 out f36 tail #connect
Pt0 f36 head f18 mainIn #connect
Pt0 f17 mainOut f26 tail #connect
Pt0 f26 head f23 mainIn #connect
Pt0 f37 head f19 mainIn #connect
Pt0 f19 mainOut f39 tail #connect
Pt0 f39 head f12 mainIn #connect
Pt0 f21 out f32 tail #connect
Pt0 f32 head f22 mainIn #connect
Pt0 f22 mainOut f24 tail #connect
Pt0 f24 head f13 mainIn #connect
Pt0 f9 mainOut f8 tail #connect
Pt0 f8 head f3 mainIn #connect
Pt0 f4 out f42 tail #connect
Pt0 f42 head f41 in #connect
Pt0 f41 out f35 tail #connect
Pt0 f35 head f9 mainIn #connect
Pt0 f3 mainOut f44 tail #connect
Pt0 f44 head f43 in #connect
Pt0 f43 out f40 tail #connect
Pt0 f40 head f21 in #connect
Pt0 f41 out f45 tail #connect
Pt0 f45 head f43 in #connect
Pt0 f21 out f16 tail #connect
Pt0 f16 head f6 mainIn #connect
Pt0 f21 out f37 tail #connect
Pt0 f6 mainOut f38 tail #connect
Pt0 f38 head f17 mainIn #connect
