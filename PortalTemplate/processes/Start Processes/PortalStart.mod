[Ivy]
[>Created: Mon Jul 04 14:38:42 ICT 2016]
1549F58C18A6C562 3.18 #module
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
Pt0 @PushWFArc f6 '' #zField
Pt0 @RichDialog f7 '' #zField
Pt0 @PushWFArc f4 '' #zField
Pt0 @RichDialog f3 '' #zField
Pt0 @Alternative f5 '' #zField
Pt0 @PushWFArc f8 '' #zField
Pt0 @CallSub f17 '' #zField
Pt0 @GridStep f1 '' #zField
Pt0 @PushWFArc f18 '' #zField
Pt0 @PushWFArc f2 '' #zField
Pt0 @CallSub f9 '' #zField
Pt0 @PushWFArc f13 '' #zField
Pt0 @PushWFArc f12 '' #zField
Pt0 @GridStep f15 '' #zField
Pt0 @StartRequest f10 '' #zField
Pt0 @PushWFArc f11 '' #zField
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
Pt0 f0 taskData 'TaskTriggered.ROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.EXPRI=2
TaskTriggered.TYPE=0
TaskTriggered.PRI=2
TaskTriggered.EXROL=Everybody' #txt
Pt0 f0 showInStartList 1 #txt
Pt0 f0 taskAndCaseSetupAction 'import ch.ivyteam.ivy.workflow.TaskUpdateDefinition;
ch.ivyteam.ivy.workflow.TaskUpdateDefinition taskUpdDef = new ch.ivyteam.ivy.workflow.TaskUpdateDefinition();
import ch.ivyteam.ivy.request.impl.DefaultCalendarProxy;
DefaultCalendarProxy calendarProxy = ivy.cal as DefaultCalendarProxy;
taskUpdDef.setPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
taskUpdDef.setExpiryActivator("Everybody");
taskUpdDef.setExpiryPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
engine.updateCurrentTask(taskUpdDef);
' #txt
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
Pt0 f6 expr out #txt
Pt0 f6 77 152 170 152 #arcP
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
Pt0 f7 480 216 96 48 -22 -8 #rect
Pt0 f7 @|RichDialogIcon #fIcon
Pt0 f4 expr in #txt
Pt0 f4 outCond 'java.util.Objects.equals(ch.ivy.addon.portal.generic.navigation.PortalPage.HOME_PAGE, in.portalPage) || java.util.Objects.isNull(in.portalPage)' #txt
Pt0 f4 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Navigator equal with HOME_PAGE</name>
        <nameStyle>30
</nameStyle>
    </language>
</elementInfo>
' #txt
Pt0 f4 197 153 476 152 #arcP
Pt0 f4 0 0.37297345905809204 0 -17 #arcLabel
Pt0 f3 targetWindow NEW:card: #txt
Pt0 f3 targetDisplay TOP #txt
Pt0 f3 richDialogId ch.ivy.addon.portal.generic.PortalHome #txt
Pt0 f3 startMethod start() #txt
Pt0 f3 type ch.ivy.addon.portal.generic.PortalStartData #txt
Pt0 f3 requestActionDecl '<> param;' #txt
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
Pt0 f3 476 128 120 48 -45 -8 #rect
Pt0 f3 @|RichDialogIcon #fIcon
Pt0 f5 type ch.ivy.addon.portal.generic.PortalStartData #txt
Pt0 f5 170 138 28 28 14 0 #rect
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
Pt0 f8 184 166 480 240 #arcP
Pt0 f8 1 184 240 #addKink
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
Pt0 f17 656 304 112 48 -49 -12 #rect
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
Pt0 f1 500 304 104 48 -46 -4 #rect
Pt0 f1 @|StepIcon #fIcon
Pt0 f18 expr out #txt
Pt0 f18 604 328 656 328 #arcP
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
Pt0 f2 184 166 500 328 #arcP
Pt0 f2 1 184 328 #addKink
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
Pt0 f9 660 416 104 48 -46 -6 #rect
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
Pt0 f13 184 166 488 440 #arcP
Pt0 f13 1 184 336 #addKink
Pt0 f13 2 184 304 #addKink
Pt0 f13 3 184 440 #addKink
Pt0 f13 3 0.41776315789473684 0 -14 #arcLabel
Pt0 f12 expr out #txt
Pt0 f12 616 440 660 440 #arcP
Pt0 f12 0 0.7418894103244236 0 0 #arcLabel
Pt0 f15 actionDecl 'ch.ivy.addon.portal.generic.PortalStartData out;
' #txt
Pt0 f15 actionTable 'out=in;
' #txt
Pt0 f15 actionCode 'import ch.ivy.addon.portalkit.util.SecurityServiceUtils;
import ch.ivy.addon.portalkit.datamodel.TaskLazyDataModel;
import ch.ivy.addon.portalkit.bo.MainMenuNode;
import ch.ivy.addon.portal.generic.view.TaskView;
import java.util.Map;
import org.primefaces.extensions.util.json.GsonConverter;
import java.util.Arrays;

TaskLazyDataModel dataModel = new TaskLazyDataModel();
dataModel.setIgnoreInvolvedUser(true);
dataModel.setSortType(ch.ivy.addon.portalkit.enums.SortType.BY_PRIORITY);

Map taskInfo = GsonConverter.getGson().fromJson(in.parameters,Map.class) as Map;
long taskId = Long.parseLong(taskInfo.get("taskId") as String);
dataModel.setTaskId(taskId);

Long serverId = SecurityServiceUtils.getServerIdFromSession();
if (#serverId is initialized) {
	dataModel.setServerId(serverId);
}

String applicationName = SecurityServiceUtils.getApplicationNameFromSession();
if (#applicationName is initialized) {
	dataModel.setInvolvedApplications(applicationName);
}

String pageTitle = ivy.cms.co("/Labels/Task");
MainMenuNode category = new MainMenuNode();
category.value = pageTitle;
String noTaskMessage = ivy.cms.co("/ch.ivy.addon.portalkit.ui.jsf/taskWarning/taskNotFound");

out.taskView = TaskView.create().dataModel(dataModel).pageTitle(pageTitle).category(category).remoteTaskId(taskId).hideTaskFilter(true).showHeaderToolbar(false).noTaskFoundMessage(noTaskMessage).createNewTaskView();
' #txt
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
Pt0 f15 488 412 128 56 -43 -16 #rect
Pt0 f15 @|StepIcon #fIcon
Pt0 f10 outLink DefaultEndPage.ivp #txt
Pt0 f10 type ch.ivy.addon.portal.generic.PortalStartData #txt
Pt0 f10 inParamDecl '<java.lang.Number endedTaskID> param;' #txt
Pt0 f10 inParamTable 'out.portalPage=ch.ivy.addon.portal.generic.navigation.PortalPage.HOME_PAGE;
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
Pt0 f10 showInStartList 0 #txt
Pt0 f10 taskAndCaseSetupAction 'import ch.ivyteam.ivy.workflow.TaskUpdateDefinition;
ch.ivyteam.ivy.workflow.TaskUpdateDefinition taskUpdDef = new ch.ivyteam.ivy.workflow.TaskUpdateDefinition();
import ch.ivyteam.ivy.request.impl.DefaultCalendarProxy;
DefaultCalendarProxy calendarProxy = ivy.cal as DefaultCalendarProxy;
taskUpdDef.setPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
taskUpdDef.setExpiryActivator("Everybody");
taskUpdDef.setExpiryPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
engine.updateCurrentTask(taskUpdDef);
' #txt
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
Pt0 f10 51 59 26 26 -46 17 #rect
Pt0 f10 @|StartRequestIcon #fIcon
Pt0 f11 expr out #txt
Pt0 f11 77 72 184 138 #arcP
Pt0 f11 1 184 72 #addKink
Pt0 f11 0 0.8735104143066533 0 0 #arcLabel
>Proto Pt0 .type ch.ivy.addon.portal.generic.PortalStartData #txt
>Proto Pt0 .processKind NORMAL #txt
>Proto Pt0 0 0 32 24 18 0 #rect
>Proto Pt0 @|BIcon #fIcon
Pt0 f0 mainOut f6 tail #connect
Pt0 f6 head f5 in #connect
Pt0 f5 out f8 tail #connect
Pt0 f8 head f7 mainIn #connect
Pt0 f5 out f4 tail #connect
Pt0 f4 head f3 mainIn #connect
Pt0 f1 mainOut f18 tail #connect
Pt0 f18 head f17 mainIn #connect
Pt0 f5 out f2 tail #connect
Pt0 f2 head f1 mainIn #connect
Pt0 f15 mainOut f12 tail #connect
Pt0 f12 head f9 mainIn #connect
Pt0 f5 out f13 tail #connect
Pt0 f13 head f15 mainIn #connect
Pt0 f10 mainOut f11 tail #connect
Pt0 f11 head f5 in #connect
