[Ivy]
[>Created: Thu Apr 27 11:59:41 ICT 2017]
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
Pt0 @RichDialog f7 '' #zField
Pt0 @RichDialog f3 '' #zField
Pt0 @Alternative f5 '' #zField
Pt0 @PushWFArc f8 '' #zField
Pt0 @CallSub f17 '' #zField
Pt0 @GridStep f1 '' #zField
Pt0 @PushWFArc f18 '' #zField
Pt0 @PushWFArc f2 '' #zField
Pt0 @CallSub f9 '' #zField
Pt0 @PushWFArc f13 '' #zField
Pt0 @GridStep f15 '' #zField
Pt0 @StartRequest f10 '' #zField
Pt0 @GridStep f4 '' #zField
Pt0 @PushWFArc f11 '' #zField
Pt0 @GridStep f20 '' #zField
Pt0 @PushWFArc f21 '' #zField
Pt0 @PushWFArc f19 '' #zField
Pt0 @PushWFArc f12 '' #zField
Pt0 @GridStep f22 '' #zField
Pt0 @PushWFArc f23 '' #zField
Pt0 @PushWFArc f6 '' #zField
Pt0 @PushWFArc f14 '' #zField
Pt0 @InfoButton f16 '' #zField
Pt0 @AnnotationArc f24 '' #zField
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
Pt0 f0 51 139 26 26 -31 17 #rect
Pt0 f0 @|StartRequestIcon #fIcon
Pt0 f7 targetWindow NEW:card: #txt
Pt0 f7 targetDisplay TOP #txt
Pt0 f7 richDialogId ch.ivy.addon.portal.error.ErrorPage #txt
Pt0 f7 startMethod start(String) #txt
Pt0 f7 type ch.ivy.addon.portal.generic.PortalStartData #txt
Pt0 f7 requestActionDecl '<String errorCode> param;' #txt
Pt0 f7 requestActionCode 'import org.primefaces.extensions.util.json.GsonConverter;
import java.util.Map;
Map parameters = GsonConverter.getGson().fromJson(in.parameters, Map.class) as Map;

param.errorCode = parameters.get("errorCode").toString();' #txt
Pt0 f7 responseActionDecl 'ch.ivy.addon.portal.generic.PortalStartData out;
' #txt
Pt0 f7 responseMappingAction 'out=in;
' #txt
Pt0 f7 windowConfiguration '* ' #txt
Pt0 f7 isAsynch false #txt
Pt0 f7 isInnerRd false #txt
Pt0 f7 userContext '* ' #txt
Pt0 f7 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Error Page</name>
        <nameStyle>10
</nameStyle>
    </language>
</elementInfo>
' #txt
Pt0 f7 586 216 96 48 -22 -8 #rect
Pt0 f7 @|RichDialogIcon #fIcon
Pt0 f3 targetWindow NEW:card: #txt
Pt0 f3 targetDisplay TOP #txt
Pt0 f3 richDialogId ch.ivy.addon.portal.generic.PortalHome #txt
Pt0 f3 startMethod start(ch.ivy.addon.portal.generic.view.TaskView) #txt
Pt0 f3 type ch.ivy.addon.portal.generic.PortalStartData #txt
Pt0 f3 requestActionDecl '<ch.ivy.addon.portal.generic.view.TaskView taskView> param;' #txt
Pt0 f3 requestMappingAction 'param.taskView=in.taskView;
' #txt
Pt0 f3 responseActionDecl 'ch.ivy.addon.portal.generic.PortalStartData out;
' #txt
Pt0 f3 responseMappingAction 'out=in;
' #txt
Pt0 f3 windowConfiguration '* ' #txt
Pt0 f3 isAsynch false #txt
Pt0 f3 isInnerRd false #txt
Pt0 f3 userContext '* ' #txt
Pt0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Portal Home Page</name>
        <nameStyle>16
</nameStyle>
    </language>
</elementInfo>
' #txt
Pt0 f3 774 128 120 48 -45 -8 #rect
Pt0 f3 @|RichDialogIcon #fIcon
Pt0 f5 type ch.ivy.addon.portal.generic.PortalStartData #txt
Pt0 f5 276 138 28 28 14 0 #rect
Pt0 f5 @|AlternativeIcon #fIcon
Pt0 f8 expr in #txt
Pt0 f8 outCond 'java.util.Objects.equals(ch.ivy.addon.portal.generic.navigation.PortalPage.ERROR_PAGE ,in.portalPage) ' #txt
Pt0 f8 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Navigator equal with ERROR_PAGE</name>
        <nameStyle>31
</nameStyle>
    </language>
</elementInfo>
' #txt
Pt0 f8 290 166 586 240 #arcP
Pt0 f8 1 290 240 #addKink
Pt0 f8 1 0.40202702702702703 0 -10 #arcLabel
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
Pt0 f17 762 304 112 48 -49 -12 #rect
Pt0 f17 @|CallSubIcon #fIcon
Pt0 f1 actionDecl 'ch.ivy.addon.portal.generic.PortalStartData out;
' #txt
Pt0 f1 actionTable 'out=in;
' #txt
Pt0 f1 actionCode 'import java.util.Map;
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
Pt0 f1 type ch.ivy.addon.portal.generic.PortalStartData #txt
Pt0 f1 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>prepare case view</name>
        <nameStyle>17,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Pt0 f1 606 304 104 48 -46 -4 #rect
Pt0 f1 @|StepIcon #fIcon
Pt0 f18 expr out #txt
Pt0 f18 710 328 762 328 #arcP
Pt0 f2 expr in #txt
Pt0 f2 outCond 'java.util.Objects.equals(ch.ivy.addon.portal.generic.navigation.PortalPage.CASE_DETAIL_FROM_TASK, in.portalPage)' #txt
Pt0 f2 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Navigator equal with CASE_DETAIL_FROM_TASK</name>
        <nameStyle>42
</nameStyle>
    </language>
</elementInfo>
' #txt
Pt0 f2 290 166 606 328 #arcP
Pt0 f2 1 290 328 #addKink
Pt0 f2 1 0.5063291139240507 0 -14 #arcLabel
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
Pt0 f9 734 416 104 48 -46 -6 #rect
Pt0 f9 @|CallSubIcon #fIcon
Pt0 f13 expr in #txt
Pt0 f13 outCond 'java.util.Objects.equals(ch.ivy.addon.portal.generic.navigation.PortalPage.LINK_TO_TASK, in.portalPage)' #txt
Pt0 f13 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Navigator equal with LINK_TO_TASK</name>
        <nameStyle>33
</nameStyle>
    </language>
</elementInfo>
' #txt
Pt0 f13 290 166 538 440 #arcP
Pt0 f13 1 290 440 #addKink
Pt0 f13 2 288 440 #addKink
Pt0 f13 2 0.4838709677419355 0 -14 #arcLabel
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
Pt0 f15 538 412 128 56 -43 -16 #rect
Pt0 f15 @|StepIcon #fIcon
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
Pt0 f10 51 51 26 26 -46 17 #rect
Pt0 f10 @|StartRequestIcon #fIcon
Pt0 f4 actionDecl 'ch.ivy.addon.portal.generic.PortalStartData out;
' #txt
Pt0 f4 actionTable 'out=in;
' #txt
Pt0 f4 actionCode 'import ch.ivyteam.ivy.workflow.ICase;
import ch.ivy.addon.portalkit.bo.TaskNode;
import ch.ivyteam.ivy.workflow.ITask;
import ch.ivy.addon.portalkit.datamodel.TaskLazyDataModel;
import ch.ivy.addon.portal.generic.navigation.PortalPage;
import ch.ivy.addon.portalkit.enums.SessionAttribute;
import ch.ivy.addon.portalkit.util.SecurityServiceUtils;

ITask task = ivy.wf.findTask(in.endedTaskId);
boolean isTaskStarted = false;
if (#task is initialized) {
	ICase case = task.getCase();
	isTaskStarted = (task.getStartProcessData() is initialized) || (!task.getStartProcessData() is initialized && case.getCreatorTask() is initialized && case.getFirstTask().equals(task));
}

if (isTaskStarted && SecurityServiceUtils.getSessionAttribute(SessionAttribute.LAST_PAGE.toString()) is initialized) {
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
Pt0 f4 174 52 36 24 20 -2 #rect
Pt0 f4 @|StepIcon #fIcon
Pt0 f11 expr out #txt
Pt0 f11 77 64 174 64 #arcP
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
        <name>Set default end page to 
Portal and store the 
last page to session</name>
        <nameStyle>67
</nameStyle>
    </language>
</elementInfo>
' #txt
Pt0 f20 104 122 160 60 -60 -24 #rect
Pt0 f20 @|StepIcon #fIcon
Pt0 f21 expr out #txt
Pt0 f21 264 152 276 152 #arcP
Pt0 f21 0 0.37297345905809204 6 -17 #arcLabel
Pt0 f19 expr out #txt
Pt0 f19 666 440 734 440 #arcP
Pt0 f19 0 0.7418894103244236 0 0 #arcLabel
Pt0 f12 expr out #txt
Pt0 f12 210 64 290 138 #arcP
Pt0 f12 1 290 64 #addKink
Pt0 f12 0 0.9625 0 0 #arcLabel
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
Pt0 f22 590 140 36 24 20 -2 #rect
Pt0 f22 @|StepIcon #fIcon
Pt0 f23 expr in #txt
Pt0 f23 outCond 'java.util.Objects.equals(ch.ivy.addon.portal.generic.navigation.PortalPage.HOME_PAGE, in.portalPage) || java.util.Objects.isNull(in.portalPage)' #txt
Pt0 f23 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Navigator equal with HOME_PAGE</name>
        <nameStyle>30
</nameStyle>
    </language>
</elementInfo>
' #txt
Pt0 f23 304 152 590 152 #arcP
Pt0 f23 0 0.37297345905809204 0 -17 #arcLabel
Pt0 f6 expr out #txt
Pt0 f6 626 152 774 152 #arcP
Pt0 f6 0 0.37297345905809204 0 -17 #arcLabel
Pt0 f14 expr out #txt
Pt0 f14 77 152 104 152 #arcP
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
Pt0 f16 936 50 480 92 -230 -40 #rect
Pt0 f16 @|IBIcon #fIcon
Pt0 f24 936 96 608 140 #arcP
Pt0 f24 1 608 96 #addKink
Pt0 f24 0 0.5846569396557643 0 0 #arcLabel
>Proto Pt0 .type ch.ivy.addon.portal.generic.PortalStartData #txt
>Proto Pt0 .processKind NORMAL #txt
>Proto Pt0 0 0 32 24 18 0 #rect
>Proto Pt0 @|BIcon #fIcon
Pt0 f5 out f8 tail #connect
Pt0 f8 head f7 mainIn #connect
Pt0 f1 mainOut f18 tail #connect
Pt0 f18 head f17 mainIn #connect
Pt0 f2 head f1 mainIn #connect
Pt0 f13 head f15 mainIn #connect
Pt0 f10 mainOut f11 tail #connect
Pt0 f11 head f4 mainIn #connect
Pt0 f20 mainOut f21 tail #connect
Pt0 f21 head f5 in #connect
Pt0 f15 mainOut f19 tail #connect
Pt0 f19 head f9 mainIn #connect
Pt0 f4 mainOut f12 tail #connect
Pt0 f12 head f5 in #connect
Pt0 f5 out f23 tail #connect
Pt0 f23 head f22 mainIn #connect
Pt0 f5 out f2 tail #connect
Pt0 f5 out f13 tail #connect
Pt0 f22 mainOut f6 tail #connect
Pt0 f6 head f3 mainIn #connect
Pt0 f0 mainOut f14 tail #connect
Pt0 f14 head f20 mainIn #connect
Pt0 f16 ao f24 tail #connect
Pt0 f24 head f22 @CG|ai #connect
