[Ivy]
[>Created: Fri Jun 02 15:50:10 ICT 2017]
1549F58C18A6C562 3.20 #module
>Proto >Proto Collection #zClass
Pt0 PortalStart Big #zClass
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
Pt0 @StartRequest f0 '' #zField
Pt0 @GridStep f4 '' #zField
Pt0 @GridStep f20 '' #zField
Pt0 @PushWFArc f14 '' #zField
Pt0 @StartRequest f25 '' #zField
Pt0 @PushWFArc f26 '' #zField
Pt0 @StartRequest f10 '' #zField
Pt0 @GridStep f11 '' #zField
Pt0 @PushWFArc f28 '' #zField
Pt0 @EndTask f27 '' #zField
Pt0 @Alternative f30 '' #zField
Pt0 @PushWFArc f31 '' #zField
Pt0 @RichDialog f5 '' #zField
Pt0 @CallSub f17 '' #zField
Pt0 @InfoButton f16 '' #zField
Pt0 @Alternative f6 '' #zField
Pt0 @CallSub f9 '' #zField
Pt0 @GridStep f22 '' #zField
Pt0 @GridStep f15 '' #zField
Pt0 @GridStep f7 '' #zField
Pt0 @RichDialog f8 '' #zField
Pt0 @PushWFArc f12 '' #zField
Pt0 @PushWFArc f13 '' #zField
Pt0 @PushWFArc f18 '' #zField
Pt0 @PushWFArc f19 '' #zField
Pt0 @PushWFArc f21 '' #zField
Pt0 @PushWFArc f23 '' #zField
Pt0 @PushWFArc f24 '' #zField
Pt0 @PushWFArc f32 '' #zField
Pt0 @PushWFArc f1 '' #zField
Pt0 @PushWFArc f2 '' #zField
Pt0 @AnnotationArc f3 '' #zField
Pt0 @RichDialog f33 '' #zField
Pt0 @PushWFArc f34 '' #zField
Pt0 @PushWFArc f29 '' #zField
>Proto Pt0 Pt0 PortalStart #zField
Pt0 f0 outLink PortalStart.ivp #txt
Pt0 f0 type ch.ivy.addon.portal.generic.PortalStartData #txt
Pt0 f0 inParamDecl '<java.lang.String parameters,java.lang.String portalNavigator> param;' #txt
Pt0 f0 inParamTable 'out.parameters=param.parameters;
out.portalPage=param.parameters.isEmpty() ? ch.ivy.addon.portal.generic.navigation.PortalPage.HOME_PAGE : ch.ivy.addon.portal.generic.navigation.PortalPage.valueOf(param.portalNavigator);
' #txt
Pt0 f0 actionDecl 'ch.ivy.addon.portal.generic.PortalStartData out;
' #txt
Pt0 f0 guid 1549F58C18C4ED13 #txt
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
Pt0 f0 51 243 26 26 -31 17 #rect
Pt0 f0 @|StartRequestIcon #fIcon
Pt0 f4 actionDecl 'ch.ivy.addon.portal.generic.PortalStartData out;
' #txt
Pt0 f4 actionTable 'out=in;
' #txt
Pt0 f4 actionCode 'import ch.ivy.addon.portalkit.datamodel.TaskLazyDataModel;
import ch.ivy.addon.portal.generic.navigation.PortalPage;
import ch.ivy.addon.portalkit.enums.SessionAttribute;
import ch.ivy.addon.portalkit.util.SecurityServiceUtils;

if (SecurityServiceUtils.getSessionAttribute(SessionAttribute.LAST_PAGE.toString()) is initialized) {
	in.dataModel = SecurityServiceUtils.getSessionAttribute(SessionAttribute.TASK_DATA_MODEL.toString()) as TaskLazyDataModel;
	in.portalPage = in.#dataModel is initialized ? in.portalPage = SecurityServiceUtils.getSessionAttribute(SessionAttribute.LAST_PAGE.toString()) as PortalPage : PortalPage.HOME_PAGE;
	
	SecurityServiceUtils.removeSessionAttribute(SessionAttribute.TASK_DATA_MODEL.toString());
} else {
	in.portalPage = PortalPage.HOME_PAGE;
	SecurityServiceUtils.setSessionAttribute(SessionAttribute.LAST_PAGE.toString(), in.portalPage);
}' #txt
Pt0 f4 security system #txt
Pt0 f4 type ch.ivy.addon.portal.generic.PortalStartData #txt
Pt0 f4 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Recover task
list''s configuration</name>
        <nameStyle>33
</nameStyle>
    </language>
</elementInfo>
' #txt
Pt0 f4 270 84 36 24 20 -2 #rect
Pt0 f4 @|StepIcon #fIcon
Pt0 f20 actionDecl 'ch.ivy.addon.portal.generic.PortalStartData out;
' #txt
Pt0 f20 actionTable 'out=in;
' #txt
Pt0 f20 actionCode 'import ch.ivy.addon.portalkit.persistence.variable.GlobalVariable;
import ch.ivy.addon.portalkit.service.GlobalSettingService;
import ch.ivy.addon.portalkit.enums.SessionAttribute;
import ch.ivy.addon.portalkit.util.SecurityServiceUtils;
import ch.ivyteam.ivy.workflow.StandardProcessType;

GlobalSettingService service = new GlobalSettingService();
String isDefaultEndPageToPortal = service.findGlobalSettingValue(GlobalVariable.DEFAULT_END_PAGE_TO_PORTAL);

if (#isDefaultEndPageToPortal is initialized || isDefaultEndPageToPortal.toBoolean()) {
	String defaultProcessLibraryName = "ch.ivyteam.ivy.project.portal:portalTemplate";
	ivy.wf.setStandardProcessImplementationLibrary(StandardProcessType.DEFAULT_PAGES_PROCESS_TYPES, defaultProcessLibraryName);
}

SecurityServiceUtils.setSessionAttribute(SessionAttribute.LAST_PAGE.toString(), in.portalPage);' #txt
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
Pt0 f20 192 226 144 60 -54 -24 #rect
Pt0 f20 @|StepIcon #fIcon
Pt0 f14 expr out #txt
Pt0 f14 77 256 192 256 #arcP
Pt0 f25 outLink restorePortalTaskList.ivp #txt
Pt0 f25 type ch.ivy.addon.portal.generic.PortalStartData #txt
Pt0 f25 inParamDecl '<> param;' #txt
Pt0 f25 actionDecl 'ch.ivy.addon.portal.generic.PortalStartData out;
' #txt
Pt0 f25 guid 15C61CC0C2071113 #txt
Pt0 f25 requestEnabled true #txt
Pt0 f25 triggerEnabled false #txt
Pt0 f25 callSignature restorePortalTaskList() #txt
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
Pt0 f25 49 81 30 30 -49 15 #rect
Pt0 f25 @|StartRequestIcon #fIcon
Pt0 f26 expr out #txt
Pt0 f26 79 96 270 96 #arcP
Pt0 f26 0 0.5369666661485084 0 0 #arcLabel
Pt0 f10 outLink DefaultEndPage.ivp #txt
Pt0 f10 type ch.ivy.addon.portal.generic.PortalStartData #txt
Pt0 f10 inParamDecl '<java.lang.Number endedTaskId> param;' #txt
Pt0 f10 inParamTable 'out.endedTaskId=param.endedTaskId;
' #txt
Pt0 f10 actionDecl 'ch.ivy.addon.portal.generic.PortalStartData out;
' #txt
Pt0 f10 guid 154C32A59CF7FDC9 #txt
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
Pt0 f10 51 371 26 26 -46 17 #rect
Pt0 f10 @|StartRequestIcon #fIcon
Pt0 f11 actionDecl 'ch.ivy.addon.portal.generic.PortalStartData out;
' #txt
Pt0 f11 actionTable 'out=in;
' #txt
Pt0 f11 actionCode 'import ch.ivy.addon.portalkit.enums.SessionAttribute;
import ch.ivy.addon.portalkit.util.SecurityServiceUtils;
import ch.ivy.addon.portal.generic.navigation.PortalPage;
import org.apache.commons.lang3.StringUtils;
import ch.ivy.addon.portalkit.enums.AdditionalProperty;
import ch.ivyteam.ivy.request.IHttpResponse;
import ch.ivyteam.ivy.workflow.ITask;

ITask task = ivy.wf.findTask(in.endedTaskId);
in.isTaskStarted = #task is initialized ? task.getStartProcessData() is initialized : false;

if (in.isTaskStarted) {
	out.callbackUrl = task.getAdditionalProperty(AdditionalProperty.PORTAL_TASK_CALLBACK_URI.toString());
} else {
	out.portalPage = PortalPage.HOME_PAGE;
	SecurityServiceUtils.setSessionAttribute(SessionAttribute.LAST_PAGE.toString(), in.portalPage);
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
Pt0 f11 160 362 112 44 -47 -8 #rect
Pt0 f11 @|StepIcon #fIcon
Pt0 f28 expr out #txt
Pt0 f28 77 384 160 384 #arcP
Pt0 f27 type ch.ivy.addon.portal.generic.PortalStartData #txt
Pt0 f27 353 561 30 30 0 15 #rect
Pt0 f27 @|EndIcon #fIcon
Pt0 f30 type ch.ivy.addon.portal.generic.PortalStartData #txt
Pt0 f30 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>is task started?</name>
        <nameStyle>16
</nameStyle>
    </language>
</elementInfo>
' #txt
Pt0 f30 352 368 32 32 -84 -30 #rect
Pt0 f30 @|AlternativeIcon #fIcon
Pt0 f31 expr out #txt
Pt0 f31 272 384 352 384 #arcP
Pt0 f5 targetWindow NEW:card: #txt
Pt0 f5 targetDisplay TOP #txt
Pt0 f5 richDialogId ch.ivy.addon.portal.generic.PortalHome #txt
Pt0 f5 startMethod start(ch.ivy.addon.portal.generic.view.TaskView) #txt
Pt0 f5 type ch.ivy.addon.portal.generic.PortalStartData #txt
Pt0 f5 requestActionDecl '<ch.ivy.addon.portal.generic.view.TaskView taskView> param;' #txt
Pt0 f5 requestMappingAction 'param.taskView=in.taskView;
' #txt
Pt0 f5 responseActionDecl 'ch.ivy.addon.portal.generic.PortalStartData out;
' #txt
Pt0 f5 responseMappingAction 'out=in;
' #txt
Pt0 f5 windowConfiguration '* ' #txt
Pt0 f5 isAsynch false #txt
Pt0 f5 isInnerRd false #txt
Pt0 f5 userContext '* ' #txt
Pt0 f5 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Portal Home Page</name>
        <nameStyle>16
</nameStyle>
    </language>
</elementInfo>
' #txt
Pt0 f5 964 233 120 48 -45 -8 #rect
Pt0 f5 @|RichDialogIcon #fIcon
Pt0 f17 type ch.ivy.addon.portal.generic.PortalStartData #txt
Pt0 f17 processCall 'Functional Processes/OpenPortalCases:useView(ch.ivy.addon.portal.generic.view.CaseView)' #txt
Pt0 f17 doCall true #txt
Pt0 f17 requestActionDecl '<ch.ivy.addon.portal.generic.view.CaseView view> param;
' #txt
Pt0 f17 requestMappingAction 'param.view=in.caseView;
' #txt
Pt0 f17 responseActionDecl 'ch.ivy.addon.portal.generic.PortalStartData out;
' #txt
Pt0 f17 responseMappingAction 'out=in;
' #txt
Pt0 f17 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>OpenPortalCases</name>
        <nameStyle>15,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Pt0 f17 952 409 112 48 -49 -12 #rect
Pt0 f17 @|CallSubIcon #fIcon
Pt0 f16 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>NOTE: IF THERE IS A CUSTOMIZED DATA MODEL, PLEASE REPLACE THE SCRIPT

in.dataModel = new TaskLazyDataModel();
BY
in.dataModel = new &lt;CUSTOMIZED_DATA_MODEL&gt;();</name>
        <nameStyle>158
</nameStyle>
    </language>
</elementInfo>
' #txt
Pt0 f16 666 100 480 92 -230 -40 #rect
Pt0 f16 @|IBIcon #fIcon
Pt0 f6 type ch.ivy.addon.portal.generic.PortalStartData #txt
Pt0 f6 466 243 28 28 14 0 #rect
Pt0 f6 @|AlternativeIcon #fIcon
Pt0 f9 type ch.ivy.addon.portal.generic.PortalStartData #txt
Pt0 f9 processCall 'Functional Processes/OpenPortalTasks:useView(ch.ivy.addon.portal.generic.view.TaskView)' #txt
Pt0 f9 doCall true #txt
Pt0 f9 requestActionDecl '<ch.ivy.addon.portal.generic.view.TaskView taskView> param;
' #txt
Pt0 f9 requestMappingAction 'param.taskView=in.taskView;
' #txt
Pt0 f9 responseActionDecl 'ch.ivy.addon.portal.generic.PortalStartData out;
' #txt
Pt0 f9 responseMappingAction 'out=in;
' #txt
Pt0 f9 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>OpenPortalTasks</name>
    </language>
</elementInfo>
' #txt
Pt0 f9 924 521 104 48 -46 -6 #rect
Pt0 f9 @|CallSubIcon #fIcon
Pt0 f22 actionDecl 'ch.ivy.addon.portal.generic.PortalStartData out;
' #txt
Pt0 f22 actionTable 'out=in;
' #txt
Pt0 f22 actionCode 'import ch.ivy.addon.portalkit.datamodel.TaskLazyDataModel;
import ch.ivy.addon.portal.generic.view.TaskView;

if (!in.#dataModel is initialized) {
	in.dataModel = new TaskLazyDataModel();
	in.dataModel.compactMode = true;
}

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
Pt0 f22 780 245 36 24 20 -2 #rect
Pt0 f22 @|StepIcon #fIcon
Pt0 f15 actionDecl 'ch.ivy.addon.portal.generic.PortalStartData out;
' #txt
Pt0 f15 actionTable 'out=in;
' #txt
Pt0 f15 actionCode 'import ch.ivy.addon.portalkit.enums.SessionAttribute;
import ch.ivy.addon.portalkit.bo.TaskNode;
import ch.ivy.addon.portalkit.enums.TaskSortField;
import ch.ivy.addon.portalkit.util.SecurityServiceUtils;
import ch.ivy.addon.portalkit.datamodel.TaskLazyDataModel;
import ch.ivy.addon.portalkit.bo.MainMenuNode;
import ch.ivy.addon.portal.generic.view.TaskView;
import java.util.Map;
import org.primefaces.extensions.util.json.GsonConverter;
import java.util.Arrays;

if (in.#dataModel is initialized) {
	TaskNode taskCategory = SecurityServiceUtils.getSessionAttribute(SessionAttribute.TASK_CATEGORY.toString()) as TaskNode;
	boolean canLinkBackToCaseDetail = in.#dataModel.#queryCriteria.#caseId is initialized; 
	
	in.taskView = TaskView.create().category(taskCategory).dataModel(in.dataModel)
												.canLinkBackCaseDetail(canLinkBackToCaseDetail).showHeaderToolbar(false)
												.remoteTaskId(in.selectedTaskId).createNewTaskView();
	
	SecurityServiceUtils.removeSessionAttribute(SessionAttribute.TASK_CATEGORY.toString());
} else {
	TaskLazyDataModel dataModel = new TaskLazyDataModel();
	dataModel.getQueryCriteria().setNewQueryCreated(true);
	dataModel.setIgnoreInvolvedUser(true);
	dataModel.setSortField(TaskSortField.PRIORITY.toString(), false);
		
	Map taskInfo = GsonConverter.getGson().fromJson(in.parameters,Map.class) as Map;
	long taskId = Long.parseLong(taskInfo.get("taskId") as String);
	dataModel.setTaskId(taskId);
		
	String pageTitle = ivy.cms.co("/Labels/Task");
	MainMenuNode category = new MainMenuNode();
	category.value = pageTitle;
	String noTaskMessage = ivy.cms.co("/ch.ivy.addon.portalkit.ui.jsf/taskWarning/taskNotFound");
		
	out.taskView = TaskView.create().dataModel(dataModel).pageTitle(pageTitle).category(category).remoteTaskId(taskId).hideTaskFilter(true).showHeaderToolbar(false).noTaskFoundMessage(noTaskMessage).createNewTaskView();
}' #txt
Pt0 f15 security system #txt
Pt0 f15 type ch.ivy.addon.portal.generic.PortalStartData #txt
Pt0 f15 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>build data model,
 task </name>
        <nameStyle>24,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Pt0 f15 728 517 128 56 -43 -16 #rect
Pt0 f15 @|StepIcon #fIcon
Pt0 f7 actionDecl 'ch.ivy.addon.portal.generic.PortalStartData out;
' #txt
Pt0 f7 actionTable 'out=in;
' #txt
Pt0 f7 actionCode 'import java.util.Map;
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
CaseLazyDataModel dataModel = new CaseLazyDataModel();
dataModel.setCaseId(caseId);
out.caseView = CaseView.create().dataModel(dataModel).withTitle(title).autoSelectIfExists(GlobalCaseId.inServer(serverId).caseId(caseId).build()).buildNewView();' #txt
Pt0 f7 type ch.ivy.addon.portal.generic.PortalStartData #txt
Pt0 f7 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>prepare case view</name>
        <nameStyle>17,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Pt0 f7 796 409 104 48 -46 -4 #rect
Pt0 f7 @|StepIcon #fIcon
Pt0 f8 targetWindow NEW:card: #txt
Pt0 f8 targetDisplay TOP #txt
Pt0 f8 richDialogId ch.ivy.addon.portal.error.ErrorPage #txt
Pt0 f8 startMethod start(String) #txt
Pt0 f8 type ch.ivy.addon.portal.generic.PortalStartData #txt
Pt0 f8 requestActionDecl '<String errorCode> param;' #txt
Pt0 f8 requestActionCode 'import org.primefaces.extensions.util.json.GsonConverter;
import java.util.Map;
Map parameters = GsonConverter.getGson().fromJson(in.parameters, Map.class) as Map;

param.errorCode = parameters.get("errorCode").toString();' #txt
Pt0 f8 responseActionDecl 'ch.ivy.addon.portal.generic.PortalStartData out;
' #txt
Pt0 f8 responseMappingAction 'out=in;
' #txt
Pt0 f8 windowConfiguration '* ' #txt
Pt0 f8 isAsynch false #txt
Pt0 f8 isInnerRd false #txt
Pt0 f8 userContext '* ' #txt
Pt0 f8 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Error Page</name>
        <nameStyle>10
</nameStyle>
    </language>
</elementInfo>
' #txt
Pt0 f8 776 321 96 48 -22 -8 #rect
Pt0 f8 @|RichDialogIcon #fIcon
Pt0 f12 expr out #txt
Pt0 f12 816 257 964 257 #arcP
Pt0 f12 0 0.37297345905809204 0 -17 #arcLabel
Pt0 f13 expr in #txt
Pt0 f13 outCond 'java.util.Objects.equals(ch.ivy.addon.portal.generic.navigation.PortalPage.LINK_TO_TASK, in.portalPage)' #txt
Pt0 f13 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>LINK_TO_TASK</name>
        <nameStyle>12
</nameStyle>
    </language>
</elementInfo>
' #txt
Pt0 f13 480 271 728 545 #arcP
Pt0 f13 1 480 545 #addKink
Pt0 f13 1 0.6691286514986969 0 -16 #arcLabel
Pt0 f18 expr out #txt
Pt0 f18 900 433 952 433 #arcP
Pt0 f19 expr in #txt
Pt0 f19 outCond 'java.util.Objects.equals(ch.ivy.addon.portal.generic.navigation.PortalPage.ERROR_PAGE ,in.portalPage) ' #txt
Pt0 f19 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>ERROR_PAGE</name>
        <nameStyle>10
</nameStyle>
    </language>
</elementInfo>
' #txt
Pt0 f19 485 266 776 345 #arcP
Pt0 f19 1 536 345 #addKink
Pt0 f19 1 0.4333578467613107 1 -12 #arcLabel
Pt0 f21 expr out #txt
Pt0 f21 856 545 924 545 #arcP
Pt0 f21 0 0.7418894103244236 0 0 #arcLabel
Pt0 f23 expr in #txt
Pt0 f23 outCond 'java.util.Objects.equals(ch.ivy.addon.portal.generic.navigation.PortalPage.HOME_PAGE, in.portalPage) || java.util.Objects.isNull(in.portalPage)' #txt
Pt0 f23 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>HOME_PAGE</name>
        <nameStyle>9
</nameStyle>
    </language>
</elementInfo>
' #txt
Pt0 f23 494 257 780 257 #arcP
Pt0 f23 0 0.32167832167832167 0 -9 #arcLabel
Pt0 f24 expr in #txt
Pt0 f24 outCond 'java.util.Objects.equals(ch.ivy.addon.portal.generic.navigation.PortalPage.CASE_DETAIL_FROM_TASK, in.portalPage)' #txt
Pt0 f24 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>CASE_DETAIL_FROM_TASK</name>
        <nameStyle>21
</nameStyle>
    </language>
</elementInfo>
' #txt
Pt0 f24 483 268 796 433 #arcP
Pt0 f24 1 536 433 #addKink
Pt0 f24 1 0.4450917120371631 0 -15 #arcLabel
Pt0 f32 expr out #txt
Pt0 f32 306 96 480 243 #arcP
Pt0 f32 1 480 96 #addKink
Pt0 f32 0 0.9221703792864407 0 0 #arcLabel
Pt0 f1 expr out #txt
Pt0 f1 336 256 466 257 #arcP
Pt0 f2 expr in #txt
Pt0 f2 375 375 473 264 #arcP
Pt0 f3 906 192 784 245 #arcP
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
Pt0 f33 312 458 112 44 -30 -16 #rect
Pt0 f33 @|RichDialogIcon #fIcon
Pt0 f34 expr in #txt
Pt0 f34 outCond in.isTaskStarted #txt
Pt0 f34 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>YES</name>
        <nameStyle>3
</nameStyle>
    </language>
</elementInfo>
' #txt
Pt0 f34 368 400 368 458 #arcP
Pt0 f34 0 0.2732919254658385 9 0 #arcLabel
Pt0 f29 expr out #txt
Pt0 f29 368 502 368 561 #arcP
Pt0 f29 0 0.2732919254658385 9 0 #arcLabel
>Proto Pt0 .type ch.ivy.addon.portal.generic.PortalStartData #txt
>Proto Pt0 .processKind NORMAL #txt
>Proto Pt0 0 0 32 24 18 0 #rect
>Proto Pt0 @|BIcon #fIcon
Pt0 f0 mainOut f14 tail #connect
Pt0 f14 head f20 mainIn #connect
Pt0 f25 mainOut f26 tail #connect
Pt0 f26 head f4 mainIn #connect
Pt0 f10 mainOut f28 tail #connect
Pt0 f28 head f11 mainIn #connect
Pt0 f11 mainOut f31 tail #connect
Pt0 f31 head f30 in #connect
Pt0 f6 out f19 tail #connect
Pt0 f19 head f8 mainIn #connect
Pt0 f7 mainOut f18 tail #connect
Pt0 f18 head f17 mainIn #connect
Pt0 f24 head f7 mainIn #connect
Pt0 f13 head f15 mainIn #connect
Pt0 f15 mainOut f21 tail #connect
Pt0 f21 head f9 mainIn #connect
Pt0 f6 out f23 tail #connect
Pt0 f23 head f22 mainIn #connect
Pt0 f6 out f24 tail #connect
Pt0 f6 out f13 tail #connect
Pt0 f22 mainOut f12 tail #connect
Pt0 f12 head f5 mainIn #connect
Pt0 f4 mainOut f32 tail #connect
Pt0 f32 head f6 in #connect
Pt0 f20 mainOut f1 tail #connect
Pt0 f1 head f6 in #connect
Pt0 f2 head f6 in #connect
Pt0 f16 ao f3 tail #connect
Pt0 f3 head f22 @CG|ai #connect
Pt0 f30 out f34 tail #connect
Pt0 f34 head f33 mainIn #connect
Pt0 f30 out f2 tail #connect
Pt0 f33 mainOut f29 tail #connect
Pt0 f29 head f27 mainIn #connect
