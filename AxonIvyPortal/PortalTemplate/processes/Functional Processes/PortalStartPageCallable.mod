[Ivy]
[>Created: Fri Jun 02 17:32:10 ICT 2017]
15C685BB425F6192 3.20 #module
>Proto >Proto Collection #zClass
Pe0 PortalStartPageCallable Big #zClass
Pe0 B #cInfo
Pe0 #process
Pe0 @TextInP .resExport .resExport #zField
Pe0 @TextInP .type .type #zField
Pe0 @TextInP .processKind .processKind #zField
Pe0 @AnnotationInP-0n ai ai #zField
Pe0 @MessageFlowInP-0n messageIn messageIn #zField
Pe0 @MessageFlowOutP-0n messageOut messageOut #zField
Pe0 @TextInP .xml .xml #zField
Pe0 @TextInP .responsibility .responsibility #zField
Pe0 @StartSub f0 '' #zField
Pe0 @RichDialog f8 '' #zField
Pe0 @Alternative f6 '' #zField
Pe0 @GridStep f15 '' #zField
Pe0 @InfoButton f16 '' #zField
Pe0 @CallSub f9 '' #zField
Pe0 @RichDialog f5 '' #zField
Pe0 @GridStep f22 '' #zField
Pe0 @CallSub f17 '' #zField
Pe0 @GridStep f7 '' #zField
Pe0 @PushWFArc f21 '' #zField
Pe0 @PushWFArc f19 '' #zField
Pe0 @PushWFArc f13 '' #zField
Pe0 @PushWFArc f12 '' #zField
Pe0 @PushWFArc f23 '' #zField
Pe0 @PushWFArc f18 '' #zField
Pe0 @PushWFArc f24 '' #zField
Pe0 @PushWFArc f1 '' #zField
Pe0 @AnnotationArc f2 '' #zField
>Proto Pe0 Pe0 PortalStartPageCallable #zField
Pe0 f0 inParamDecl '<ch.ivy.addon.portal.generic.PortalStartData data> param;' #txt
Pe0 f0 inParamTable 'out=param.data;
' #txt
Pe0 f0 outParamDecl '<> result;
' #txt
Pe0 f0 actionDecl 'ch.ivy.addon.portal.generic.PortalStartData out;
' #txt
Pe0 f0 callSignature call(ch.ivy.addon.portal.generic.PortalStartData) #txt
Pe0 f0 type ch.ivy.addon.portal.generic.PortalStartData #txt
Pe0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>call(PortalStartData)</name>
    </language>
</elementInfo>
' #txt
Pe0 f0 81 177 30 30 -55 17 #rect
Pe0 f0 @|StartSubIcon #fIcon
Pe0 f8 targetWindow NEW:card: #txt
Pe0 f8 targetDisplay TOP #txt
Pe0 f8 richDialogId ch.ivy.addon.portal.error.ErrorPage #txt
Pe0 f8 startMethod start(String) #txt
Pe0 f8 type ch.ivy.addon.portal.generic.PortalStartData #txt
Pe0 f8 requestActionDecl '<String errorCode> param;' #txt
Pe0 f8 requestActionCode 'import org.primefaces.extensions.util.json.GsonConverter;
import java.util.Map;
Map parameters = GsonConverter.getGson().fromJson(in.parameters, Map.class) as Map;

param.errorCode = parameters.get("errorCode").toString();' #txt
Pe0 f8 responseActionDecl 'ch.ivy.addon.portal.generic.PortalStartData out;
' #txt
Pe0 f8 responseMappingAction 'out=in;
' #txt
Pe0 f8 windowConfiguration '* ' #txt
Pe0 f8 isAsynch false #txt
Pe0 f8 isInnerRd false #txt
Pe0 f8 userContext '* ' #txt
Pe0 f8 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Error Page</name>
        <nameStyle>10
</nameStyle>
    </language>
</elementInfo>
' #txt
Pe0 f8 520 257 96 48 -22 -8 #rect
Pe0 f8 @|RichDialogIcon #fIcon
Pe0 f6 type ch.ivy.addon.portal.generic.PortalStartData #txt
Pe0 f6 210 179 28 28 14 0 #rect
Pe0 f6 @|AlternativeIcon #fIcon
Pe0 f15 actionDecl 'ch.ivy.addon.portal.generic.PortalStartData out;
' #txt
Pe0 f15 actionTable 'out=in;
' #txt
Pe0 f15 actionCode 'import ch.ivy.addon.portalkit.enums.SessionAttribute;
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
Pe0 f15 security system #txt
Pe0 f15 type ch.ivy.addon.portal.generic.PortalStartData #txt
Pe0 f15 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>build data model,
 task </name>
        <nameStyle>24,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Pe0 f15 472 453 128 56 -43 -16 #rect
Pe0 f15 @|StepIcon #fIcon
Pe0 f16 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
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
Pe0 f16 296 54 480 92 -230 -40 #rect
Pe0 f16 @|IBIcon #fIcon
Pe0 f9 type ch.ivy.addon.portal.generic.PortalStartData #txt
Pe0 f9 processCall 'Functional Processes/OpenPortalTasks:useView(ch.ivy.addon.portal.generic.view.TaskView)' #txt
Pe0 f9 doCall true #txt
Pe0 f9 requestActionDecl '<ch.ivy.addon.portal.generic.view.TaskView taskView> param;
' #txt
Pe0 f9 requestMappingAction 'param.taskView=in.taskView;
' #txt
Pe0 f9 responseActionDecl 'ch.ivy.addon.portal.generic.PortalStartData out;
' #txt
Pe0 f9 responseMappingAction 'out=in;
' #txt
Pe0 f9 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>OpenPortalTasks</name>
    </language>
</elementInfo>
' #txt
Pe0 f9 668 457 104 48 -46 -6 #rect
Pe0 f9 @|CallSubIcon #fIcon
Pe0 f5 targetWindow NEW:card: #txt
Pe0 f5 targetDisplay TOP #txt
Pe0 f5 richDialogId ch.ivy.addon.portal.generic.PortalHome #txt
Pe0 f5 startMethod start(ch.ivy.addon.portal.generic.view.TaskView) #txt
Pe0 f5 type ch.ivy.addon.portal.generic.PortalStartData #txt
Pe0 f5 requestActionDecl '<ch.ivy.addon.portal.generic.view.TaskView taskView> param;' #txt
Pe0 f5 requestMappingAction 'param.taskView=in.taskView;
' #txt
Pe0 f5 responseActionDecl 'ch.ivy.addon.portal.generic.PortalStartData out;
' #txt
Pe0 f5 responseMappingAction 'out=in;
' #txt
Pe0 f5 windowConfiguration '* ' #txt
Pe0 f5 isAsynch false #txt
Pe0 f5 isInnerRd false #txt
Pe0 f5 userContext '* ' #txt
Pe0 f5 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Portal Home Page</name>
        <nameStyle>16
</nameStyle>
    </language>
</elementInfo>
' #txt
Pe0 f5 708 169 120 48 -45 -8 #rect
Pe0 f5 @|RichDialogIcon #fIcon
Pe0 f22 actionDecl 'ch.ivy.addon.portal.generic.PortalStartData out;
' #txt
Pe0 f22 actionTable 'out=in;
' #txt
Pe0 f22 actionCode 'import ch.ivy.addon.portalkit.datamodel.TaskLazyDataModel;
import ch.ivy.addon.portal.generic.view.TaskView;

if (!in.#dataModel is initialized) {
	in.dataModel = new TaskLazyDataModel();
	in.dataModel.compactMode = true;
}

in.taskView = TaskView.create().dataModel(in.dataModel).showHeaderToolbar(true).noTaskFoundMessage("").createNewTaskView();
' #txt
Pe0 f22 type ch.ivy.addon.portal.generic.PortalStartData #txt
Pe0 f22 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Recover task
list''s configuration</name>
        <nameStyle>33
</nameStyle>
    </language>
</elementInfo>
' #txt
Pe0 f22 524 181 36 24 20 -2 #rect
Pe0 f22 @|StepIcon #fIcon
Pe0 f17 type ch.ivy.addon.portal.generic.PortalStartData #txt
Pe0 f17 processCall 'Functional Processes/OpenPortalCases:useView(ch.ivy.addon.portal.generic.view.CaseView)' #txt
Pe0 f17 doCall true #txt
Pe0 f17 requestActionDecl '<ch.ivy.addon.portal.generic.view.CaseView view> param;
' #txt
Pe0 f17 requestMappingAction 'param.view=in.caseView;
' #txt
Pe0 f17 responseActionDecl 'ch.ivy.addon.portal.generic.PortalStartData out;
' #txt
Pe0 f17 responseMappingAction 'out=in;
' #txt
Pe0 f17 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>OpenPortalCases</name>
        <nameStyle>15,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Pe0 f17 696 345 112 48 -49 -12 #rect
Pe0 f17 @|CallSubIcon #fIcon
Pe0 f7 actionDecl 'ch.ivy.addon.portal.generic.PortalStartData out;
' #txt
Pe0 f7 actionTable 'out=in;
' #txt
Pe0 f7 actionCode 'import java.util.Map;
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
Pe0 f7 type ch.ivy.addon.portal.generic.PortalStartData #txt
Pe0 f7 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>prepare case view</name>
        <nameStyle>17,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Pe0 f7 540 345 104 48 -46 -4 #rect
Pe0 f7 @|StepIcon #fIcon
Pe0 f21 expr out #txt
Pe0 f21 600 481 668 481 #arcP
Pe0 f21 0 0.7418894103244236 0 0 #arcLabel
Pe0 f19 expr in #txt
Pe0 f19 outCond 'java.util.Objects.equals(ch.ivy.addon.portal.generic.navigation.PortalPage.ERROR_PAGE ,in.portalPage) ' #txt
Pe0 f19 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>ERROR_PAGE</name>
        <nameStyle>10
</nameStyle>
    </language>
</elementInfo>
' #txt
Pe0 f19 229 202 520 281 #arcP
Pe0 f19 1 272 281 #addKink
Pe0 f19 1 0.24193548387096775 0 -11 #arcLabel
Pe0 f13 expr in #txt
Pe0 f13 outCond 'java.util.Objects.equals(ch.ivy.addon.portal.generic.navigation.PortalPage.LINK_TO_TASK, in.portalPage)' #txt
Pe0 f13 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>LINK_TO_TASK</name>
        <nameStyle>12
</nameStyle>
    </language>
</elementInfo>
' #txt
Pe0 f13 224 207 472 481 #arcP
Pe0 f13 1 224 481 #addKink
Pe0 f13 1 0.2540322580645161 0 -14 #arcLabel
Pe0 f12 expr out #txt
Pe0 f12 560 193 708 193 #arcP
Pe0 f12 0 0.37297345905809204 0 -17 #arcLabel
Pe0 f23 expr in #txt
Pe0 f23 outCond 'java.util.Objects.equals(ch.ivy.addon.portal.generic.navigation.PortalPage.HOME_PAGE, in.portalPage) || java.util.Objects.isNull(in.portalPage)' #txt
Pe0 f23 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>HOME_PAGE</name>
        <nameStyle>9
</nameStyle>
    </language>
</elementInfo>
' #txt
Pe0 f23 238 193 524 193 #arcP
Pe0 f23 0 0.32167832167832167 0 -9 #arcLabel
Pe0 f18 expr out #txt
Pe0 f18 644 369 696 369 #arcP
Pe0 f24 expr in #txt
Pe0 f24 outCond 'java.util.Objects.equals(ch.ivy.addon.portal.generic.navigation.PortalPage.CASE_DETAIL_FROM_TASK, in.portalPage)' #txt
Pe0 f24 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>CASE_DETAIL_FROM_TASK</name>
        <nameStyle>21
</nameStyle>
    </language>
</elementInfo>
' #txt
Pe0 f24 227 204 540 369 #arcP
Pe0 f24 1 272 368 #addKink
Pe0 f24 1 0.32068221371388794 1 -15 #arcLabel
Pe0 f1 expr out #txt
Pe0 f1 110 192 210 193 #arcP
Pe0 f2 536 146 524 193 #arcP
>Proto Pe0 .type ch.ivy.addon.portal.generic.PortalStartData #txt
>Proto Pe0 .processKind CALLABLE_SUB #txt
>Proto Pe0 0 0 32 24 18 0 #rect
>Proto Pe0 @|BIcon #fIcon
Pe0 f6 out f19 tail #connect
Pe0 f19 head f8 mainIn #connect
Pe0 f7 mainOut f18 tail #connect
Pe0 f18 head f17 mainIn #connect
Pe0 f24 head f7 mainIn #connect
Pe0 f13 head f15 mainIn #connect
Pe0 f15 mainOut f21 tail #connect
Pe0 f21 head f9 mainIn #connect
Pe0 f6 out f23 tail #connect
Pe0 f23 head f22 mainIn #connect
Pe0 f6 out f24 tail #connect
Pe0 f6 out f13 tail #connect
Pe0 f22 mainOut f12 tail #connect
Pe0 f12 head f5 mainIn #connect
Pe0 f0 mainOut f1 tail #connect
Pe0 f1 head f6 in #connect
Pe0 f16 ao f2 tail #connect
Pe0 f2 head f22 @CG|ai #connect
