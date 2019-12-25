[Ivy]
16440D2C03DFC5B4 3.20 #module
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
Pt0 @StartRequest f0 '' #zField
Pt0 @GridStep f20 '' #zField
Pt0 @StartRequest f25 '' #zField
Pt0 @GridStep f2 '' #zField
Pt0 @PushWFArc f1 '' #zField
Pt0 @Alternative f4 '' #zField
Pt0 @PushWFArc f5 '' #zField
Pt0 @PushWFArc f7 '' #zField
Pt0 @GridStep f3 '' #zField
Pt0 @Alternative f30 '' #zField
Pt0 @RichDialog f33 '' #zField
Pt0 @EndTask f27 '' #zField
Pt0 @StartRequest f10 '' #zField
Pt0 @GridStep f11 '' #zField
Pt0 @PushWFArc f34 '' #zField
Pt0 @PushWFArc f29 '' #zField
Pt0 @PushWFArc f15 '' #zField
Pt0 @CallSub f12 '' #zField
Pt0 @GridStep f22 '' #zField
Pt0 @RichDialog f13 '' #zField
Pt0 @GridStep f17 '' #zField
Pt0 @RichDialog f18 '' #zField
Pt0 @GridStep f19 '' #zField
Pt0 @Alternative f21 '' #zField
Pt0 @CallSub f23 '' #zField
Pt0 @PushWFArc f24 '' #zField
Pt0 @PushWFArc f26 '' #zField
Pt0 @PushWFArc f32 '' #zField
Pt0 @PushWFArc f36 '' #zField
Pt0 @PushWFArc f37 '' #zField
Pt0 @PushWFArc f39 '' #zField
Pt0 @CallSub f9 '' #zField
Pt0 @PushWFArc f8 '' #zField
Pt0 @Alternative f41 '' #zField
Pt0 @PushWFArc f42 '' #zField
Pt0 @PushWFArc f35 '' #zField
Pt0 @Alternative f43 '' #zField
Pt0 @PushWFArc f44 '' #zField
Pt0 @PushWFArc f40 '' #zField
Pt0 @PushWFArc f45 '' #zField
Pt0 @CallSub f6 '' #zField
Pt0 @PushWFArc f16 '' #zField
Pt0 @PushWFArc f38 '' #zField
Pt0 @PushWFArc f48 '' #zField
Pt0 @PushWFArc f50 '' #zField
Pt0 @CallSub f56 '' #zField
Pt0 @CallSub f62 '' #zField
Pt0 @CallSub f84 '' #zField
Pt0 @GridStep f69 '' #zField
Pt0 @CallSub f73 '' #zField
Pt0 @GridStep f28 '' #zField
Pt0 @PushWFArc f118 '' #zField
Pt0 @PushWFArc f52 '' #zField
Pt0 @PushWFArc f31 '' #zField
Pt0 @PushWFArc f46 '' #zField
Pt0 @RichDialog f71 '' #zField
Pt0 @RichDialog f82 '' #zField
Pt0 @StartRequest f47 '' #zField
Pt0 @PushWFArc f49 '' #zField
Pt0 @EndTask f51 '' #zField
Pt0 @PushWFArc f53 '' #zField
Pt0 @StartRequest f54 '' #zField
Pt0 @PushWFArc f55 '' #zField
Pt0 @EndTask f57 '' #zField
Pt0 @PushWFArc f58 '' #zField
Pt0 @StartRequest f59 '' #zField
Pt0 @PushWFArc f60 '' #zField
Pt0 @EndTask f61 '' #zField
Pt0 @PushWFArc f63 '' #zField
Pt0 @StartRequest f64 '' #zField
Pt0 @PushWFArc f65 '' #zField
Pt0 @EndTask f66 '' #zField
Pt0 @PushWFArc f67 '' #zField
Pt0 @GridStep f68 '' #zField
Pt0 @PushWFArc f70 '' #zField
Pt0 @PushWFArc f14 '' #zField
>Proto Pt0 Pt0 ExamplePortalStart #zField
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
Pt0 f0 51 147 26 26 -31 17 #rect
Pt0 f0 @|StartRequestIcon #fIcon
Pt0 f20 actionDecl 'ch.ivy.addon.portal.generic.PortalStartData out;
' #txt
Pt0 f20 actionTable 'out=in;
' #txt
Pt0 f20 actionCode 'import ch.ivy.addon.portalkit.enums.PortalLibrary;
import org.apache.commons.lang3.StringUtils;
import ch.ivy.addon.portalkit.enums.SessionAttribute;
import ch.ivy.addon.portalkit.util.SecurityServiceUtils;
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
to Portal and store the 
last page to session</name>
        <nameStyle>67
</nameStyle>
    </language>
</elementInfo>
' #txt
Pt0 f20 312 130 144 60 -54 -24 #rect
Pt0 f20 @|StepIcon #fIcon
Pt0 f25 outLink restorePortalTaskList.ivp #txt
Pt0 f25 type ch.ivy.addon.portal.generic.PortalStartData #txt
Pt0 f25 inParamDecl '<java.lang.Number endedTaskId> param;' #txt
Pt0 f25 inParamTable 'out.endedTaskId=param.endedTaskId;
' #txt
Pt0 f25 actionDecl 'ch.ivy.addon.portal.generic.PortalStartData out;
' #txt
Pt0 f25 guid 15C771DA29560A83 #txt
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
Pt0 f25 49 273 30 30 -49 15 #rect
Pt0 f25 @|StartRequestIcon #fIcon
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
Pt0 f2 148 266 128 44 -44 -16 #rect
Pt0 f2 @|StepIcon #fIcon
Pt0 f1 expr out #txt
Pt0 f1 79 288 148 288 #arcP
Pt0 f4 type ch.ivy.addon.portal.generic.PortalStartData #txt
Pt0 f4 544 144 32 32 0 16 #rect
Pt0 f4 @|AlternativeIcon #fIcon
Pt0 f5 expr out #txt
Pt0 f5 456 160 544 160 #arcP
Pt0 f7 expr out #txt
Pt0 f7 276 288 550 166 #arcP
Pt0 f7 1 336 288 #addKink
Pt0 f7 0 0.7634111543228898 0 0 #arcLabel
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
Pt0 f3 936 138 128 44 -54 -8 #rect
Pt0 f3 @|StepIcon #fIcon
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
Pt0 f30 368 401 32 32 27 -7 #rect
Pt0 f30 @|AlternativeIcon #fIcon
Pt0 f33 targetWindow NEW #txt
Pt0 f33 targetDisplay TOP #txt
Pt0 f33 richDialogId ch.ivy.addon.portal.generic.PortalTaskListCallback #txt
Pt0 f33 startMethod start(String,Boolean) #txt
Pt0 f33 type ch.ivy.addon.portal.generic.PortalStartData #txt
Pt0 f33 requestActionDecl '<String callbackUrl, Boolean isTaskFinished> param;' #txt
Pt0 f33 requestMappingAction 'param.callbackUrl=in.callbackUrl;
param.isTaskFinished=in.isTaskFinished;
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
        <nameStyle>23,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Pt0 f33 328 523 112 44 -30 -16 #rect
Pt0 f33 @|RichDialogIcon #fIcon
Pt0 f27 type ch.ivy.addon.portal.generic.PortalStartData #txt
Pt0 f27 501 530 30 30 0 15 #rect
Pt0 f27 @|EndIcon #fIcon
Pt0 f10 outLink DefaultEndPage.ivp #txt
Pt0 f10 type ch.ivy.addon.portal.generic.PortalStartData #txt
Pt0 f10 inParamDecl '<java.lang.Number endedTaskId> param;' #txt
Pt0 f10 inParamTable 'out.endedTaskId=param.endedTaskId;
' #txt
Pt0 f10 actionDecl 'ch.ivy.addon.portal.generic.PortalStartData out;
' #txt
Pt0 f10 guid 15C772AB9EB519C9 #txt
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
Pt0 f10 51 404 26 26 -46 17 #rect
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
Pt0 f11 156 395 112 44 -47 -8 #rect
Pt0 f11 @|StepIcon #fIcon
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
Pt0 f34 384 433 384 523 #arcP
Pt0 f34 0 0.3793103448275862 13 -1 #arcLabel
Pt0 f29 expr out #txt
Pt0 f29 440 545 501 545 #arcP
Pt0 f29 0 0.2732919254658385 -1 -8 #arcLabel
Pt0 f15 expr in #txt
Pt0 f15 391 408 553 169 #arcP
Pt0 f15 0 0.4763694877411137 0 0 #arcLabel
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
Pt0 f12 1612 520 104 48 -46 -6 #rect
Pt0 f12 @|CallSubIcon #fIcon
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
Pt0 f22 1438 148 36 24 20 -2 #rect
Pt0 f22 @|StepIcon #fIcon
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
Pt0 f13 1604 136 120 48 -45 -8 #rect
Pt0 f13 @|RichDialogIcon #fIcon
Pt0 f17 actionDecl 'ch.ivy.addon.portal.generic.PortalStartData out;
' #txt
Pt0 f17 actionTable 'out=in;
' #txt
Pt0 f17 actionCode 'import java.util.Map;
import org.primefaces.extensions.util.json.GsonConverter;
import ch.ivy.addon.portalkit.dto.GlobalCaseId;
import java.util.Arrays;
import ch.ivy.addon.portal.generic.view.CaseView;

Map caseInfor = GsonConverter.getGson().fromJson(in.parameters,Map.class) as Map;
long caseId = Long.parseLong(caseInfor.get("caseId") as String); 
String caseName = caseInfor.get("caseName") as String;
long serverId = Long.parseLong(caseInfor.get("serverId") as String);

String title = ivy.cms.co("/ch.ivy.addon.portalkit.ui.jsf/caseList/relatedCaseHeader", Arrays.asList(caseId.toString(), caseName));

in.caseDataModel.setCaseId(caseId);
out.caseView = CaseView.create().dataModel(in.caseDataModel).withTitle(title).hideCaseFilter(true).autoSelectIfExists(GlobalCaseId.inServer(serverId).caseId(caseId).build()).buildNewView();' #txt
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
Pt0 f17 1612 392 104 48 -46 -4 #rect
Pt0 f17 @|StepIcon #fIcon
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
Pt0 f18 1408 264 96 48 -22 -8 #rect
Pt0 f18 @|RichDialogIcon #fIcon
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
Pt0 f19 1392 516 128 56 -43 -16 #rect
Pt0 f19 @|StepIcon #fIcon
Pt0 f21 type ch.ivy.addon.portal.generic.PortalStartData #txt
Pt0 f21 1218 146 28 28 14 0 #rect
Pt0 f21 @|AlternativeIcon #fIcon
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
Pt0 f23 1816 392 112 48 -49 -12 #rect
Pt0 f23 @|CallSubIcon #fIcon
Pt0 f24 expr out #txt
Pt0 f24 1474 160 1604 160 #arcP
Pt0 f24 0 0.37297345905809204 -1 -17 #arcLabel
Pt0 f26 expr out #txt
Pt0 f26 1716 416 1816 416 #arcP
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
Pt0 f32 1246 160 1438 160 #arcP
Pt0 f32 0 0.32167832167832167 0 -9 #arcLabel
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
Pt0 f36 1238 168 1408 288 #arcP
Pt0 f36 1 1324 288 #addKink
Pt0 f36 1 0.37012987012987014 0 -13 #arcLabel
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
Pt0 f37 1232 174 1392 544 #arcP
Pt0 f37 1 1232 544 #addKink
Pt0 f37 1 0.5125 0 -13 #arcLabel
Pt0 f39 expr out #txt
Pt0 f39 1520 544 1612 544 #arcP
Pt0 f39 0 0.7418894103244236 0 0 #arcLabel
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
Pt0 f9 744 138 144 44 -65 -8 #rect
Pt0 f9 @|CallSubIcon #fIcon
Pt0 f8 expr out #txt
Pt0 f8 888 160 936 160 #arcP
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
Pt0 f41 640 144 32 32 -59 18 #rect
Pt0 f41 @|AlternativeIcon #fIcon
Pt0 f42 expr in #txt
Pt0 f42 576 160 640 160 #arcP
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
Pt0 f35 672 160 744 160 #arcP
Pt0 f35 0 0.4305555555555556 0 -10 #arcLabel
Pt0 f43 type ch.ivy.addon.portal.generic.PortalStartData #txt
Pt0 f43 1120 144 32 32 0 16 #rect
Pt0 f43 @|AlternativeIcon #fIcon
Pt0 f44 expr out #txt
Pt0 f44 1064 160 1120 160 #arcP
Pt0 f40 expr in #txt
Pt0 f40 1152 160 1218 160 #arcP
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
Pt0 f45 656 144 1136 144 #arcP
Pt0 f45 1 656 96 #addKink
Pt0 f45 2 1136 96 #addKink
Pt0 f45 0 0.4166666666666667 -10 0 #arcLabel
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
        <nameStyle>23,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Pt0 f6 1384 394 144 44 -66 -8 #rect
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
Pt0 f16 1235 171 1384 416 #arcP
Pt0 f16 1 1292 416 #addKink
Pt0 f16 0 0.9563894205126596 98 -22 #arcLabel
Pt0 f38 expr out #txt
Pt0 f38 1528 416 1612 416 #arcP
Pt0 f38 0 0.5114942528735632 0 -12 #arcLabel
Pt0 f48 expr out #txt
Pt0 f48 77 417 156 417 #arcP
Pt0 f50 expr out #txt
Pt0 f50 268 417 368 417 #arcP
Pt0 f56 type ch.ivy.addon.portal.generic.PortalStartData #txt
Pt0 f56 processCall 'Functional Processes/OpenPortalCases:useView(ch.ivy.addon.portal.generic.view.CaseView)' #txt
Pt0 f56 doCall true #txt
Pt0 f56 requestActionDecl '<ch.ivy.addon.portal.generic.view.CaseView view> param;
' #txt
Pt0 f56 requestMappingAction 'param.view=in.caseView;
' #txt
Pt0 f56 responseActionDecl 'ch.ivy.addon.portal.generic.PortalStartData out;
' #txt
Pt0 f56 responseMappingAction 'out=in;
' #txt
Pt0 f56 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>OpenPortalCases</name>
        <nameStyle>15,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Pt0 f56 496 892 104 40 -49 -12 #rect
Pt0 f56 @|CallSubIcon #fIcon
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
Pt0 f62 184 890 112 44 -38 -20 #rect
Pt0 f62 @|CallSubIcon #fIcon
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
Pt0 f84 496 780 104 40 -48 -12 #rect
Pt0 f84 @|CallSubIcon #fIcon
Pt0 f69 actionDecl 'ch.ivy.addon.portal.generic.PortalStartData out;
' #txt
Pt0 f69 actionTable 'out=in;
' #txt
Pt0 f69 actionCode 'import ch.ivy.addon.portalkit.enums.TaskAssigneeType;
import ch.ivy.addon.portal.generic.view.TaskView;
import ch.ivy.addon.portalkit.util.PermissionUtils;

boolean hasReadAllTasksPermisson = PermissionUtils.checkReadAllTasksPermission();

in.dataModel.setIgnoreInvolvedUser(hasReadAllTasksPermisson);
in.dataModel.setTaskAssigneeType(TaskAssigneeType.ALL);

in.taskView = TaskView.create().dataModel(in.dataModel).noTaskFoundMessage("").showHeaderToolbar(false).createNewTaskView();
' #txt
Pt0 f69 type ch.ivy.addon.portal.generic.PortalStartData #txt
Pt0 f69 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>build data model, task view</name>
        <nameStyle>27,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Pt0 f69 366 788 36 24 -65 19 #rect
Pt0 f69 @|StepIcon #fIcon
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
Pt0 f73 184 778 112 44 -40 -8 #rect
Pt0 f73 @|CallSubIcon #fIcon
Pt0 f28 actionDecl 'ch.ivy.addon.portal.generic.PortalStartData out;
' #txt
Pt0 f28 actionTable 'out=in;
' #txt
Pt0 f28 actionCode 'import ch.ivy.addon.portalkit.util.PermissionUtils;
import ch.ivy.addon.portalkit.util.SecurityServiceUtils;
import ch.ivy.addon.portal.generic.view.CaseView;

boolean hasReadAllCasesPermission = PermissionUtils.checkReadAllCasesPermission();
in.caseDataModel.setIgnoreInvolvedUser(hasReadAllCasesPermission);

in.caseDataModel.setIgnoreInvolvedUser(hasReadAllCasesPermission);

Long serverId = SecurityServiceUtils.getServerIdFromSession();
if (#serverId is initialized) {
	in.caseDataModel.setServerId(serverId);
}

String applicationName = SecurityServiceUtils.getApplicationNameFromSession();
if (#applicationName is initialized) {
	in.caseDataModel.setInvolvedApplications(applicationName);
}

in.caseView = CaseView.create().dataModel(in.caseDataModel).buildNewView();' #txt
Pt0 f28 type ch.ivy.addon.portal.generic.PortalStartData #txt
Pt0 f28 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>build data model, case view</name>
        <nameStyle>27,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Pt0 f28 366 900 36 24 -66 18 #rect
Pt0 f28 @|StepIcon #fIcon
Pt0 f118 expr out #txt
Pt0 f118 296 800 366 800 #arcP
Pt0 f52 expr out #txt
Pt0 f52 296 912 366 912 #arcP
Pt0 f31 expr out #txt
Pt0 f31 402 912 496 912 #arcP
Pt0 f46 expr out #txt
Pt0 f46 402 800 496 800 #arcP
Pt0 f71 targetWindow NEW:card: #txt
Pt0 f71 targetDisplay TOP #txt
Pt0 f71 richDialogId ch.ivy.addon.portal.generic.Processes #txt
Pt0 f71 startMethod startWithMenuState(String) #txt
Pt0 f71 type ch.ivy.addon.portal.generic.PortalStartData #txt
Pt0 f71 requestActionDecl '<String menuState> param;' #txt
Pt0 f71 requestMappingAction 'param.menuState=ch.ivy.addon.portalkit.util.MenuUtils.getMenuState();
' #txt
Pt0 f71 responseActionDecl 'ch.ivy.addon.portal.generic.PortalStartData out;
' #txt
Pt0 f71 responseMappingAction 'out=in;
' #txt
Pt0 f71 windowConfiguration '* ' #txt
Pt0 f71 isAsynch false #txt
Pt0 f71 isInnerRd false #txt
Pt0 f71 userContext '* ' #txt
Pt0 f71 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>processes</name>
        <nameStyle>9,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Pt0 f71 192 620 96 40 -22 -8 #rect
Pt0 f71 @|RichDialogIcon #fIcon
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
Pt0 f82 184 698 112 44 -21 -8 #rect
Pt0 f82 @|RichDialogIcon #fIcon
Pt0 f47 outLink startPortalProcess.ivp #txt
Pt0 f47 type ch.ivy.addon.portal.generic.PortalStartData #txt
Pt0 f47 inParamDecl '<> param;' #txt
Pt0 f47 actionDecl 'ch.ivy.addon.portal.generic.PortalStartData out;
' #txt
Pt0 f47 guid 16440C281DBCDA2A #txt
Pt0 f47 requestEnabled true #txt
Pt0 f47 triggerEnabled false #txt
Pt0 f47 callSignature startPortalProcess() #txt
Pt0 f47 persist false #txt
Pt0 f47 taskData 'TaskTriggered.ROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.EXPRI=2
TaskTriggered.TYPE=0
TaskTriggered.PRI=2
TaskTriggered.EXROL=Everybody' #txt
Pt0 f47 caseData businessCase.attach=true #txt
Pt0 f47 showInStartList 1 #txt
Pt0 f47 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>startPortalProcess.ivp</name>
    </language>
</elementInfo>
' #txt
Pt0 f47 @C|.responsibility Everybody #txt
Pt0 f47 49 625 30 30 -60 17 #rect
Pt0 f47 @|StartRequestIcon #fIcon
Pt0 f49 expr out #txt
Pt0 f49 79 640 192 640 #arcP
Pt0 f51 type ch.ivy.addon.portal.generic.PortalStartData #txt
Pt0 f51 369 625 30 30 0 15 #rect
Pt0 f51 @|EndIcon #fIcon
Pt0 f53 expr out #txt
Pt0 f53 288 640 369 640 #arcP
Pt0 f54 outLink startPortalStatistic.ivp #txt
Pt0 f54 type ch.ivy.addon.portal.generic.PortalStartData #txt
Pt0 f54 inParamDecl '<> param;' #txt
Pt0 f54 actionDecl 'ch.ivy.addon.portal.generic.PortalStartData out;
' #txt
Pt0 f54 guid 16440C2A472B88C5 #txt
Pt0 f54 requestEnabled true #txt
Pt0 f54 triggerEnabled false #txt
Pt0 f54 callSignature startPortalStatistic() #txt
Pt0 f54 persist false #txt
Pt0 f54 taskData 'TaskTriggered.ROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.EXPRI=2
TaskTriggered.TYPE=0
TaskTriggered.PRI=2
TaskTriggered.EXROL=Everybody' #txt
Pt0 f54 caseData businessCase.attach=true #txt
Pt0 f54 showInStartList 1 #txt
Pt0 f54 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>startPortalStatistic.ivp</name>
    </language>
</elementInfo>
' #txt
Pt0 f54 @C|.responsibility Everybody #txt
Pt0 f54 49 705 30 30 -58 17 #rect
Pt0 f54 @|StartRequestIcon #fIcon
Pt0 f55 expr out #txt
Pt0 f55 79 720 184 720 #arcP
Pt0 f57 type ch.ivy.addon.portal.generic.PortalStartData #txt
Pt0 f57 369 705 30 30 0 15 #rect
Pt0 f57 @|EndIcon #fIcon
Pt0 f58 expr out #txt
Pt0 f58 296 720 369 720 #arcP
Pt0 f59 outLink startPortalTask.ivp #txt
Pt0 f59 type ch.ivy.addon.portal.generic.PortalStartData #txt
Pt0 f59 inParamDecl '<> param;' #txt
Pt0 f59 actionDecl 'ch.ivy.addon.portal.generic.PortalStartData out;
' #txt
Pt0 f59 guid 16440C2C65451BD4 #txt
Pt0 f59 requestEnabled true #txt
Pt0 f59 triggerEnabled false #txt
Pt0 f59 callSignature startPortalTask() #txt
Pt0 f59 persist false #txt
Pt0 f59 taskData 'TaskTriggered.ROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.EXPRI=2
TaskTriggered.TYPE=0
TaskTriggered.PRI=2
TaskTriggered.EXROL=Everybody' #txt
Pt0 f59 caseData businessCase.attach=true #txt
Pt0 f59 showInStartList 1 #txt
Pt0 f59 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>startPortalTask.ivp</name>
    </language>
</elementInfo>
' #txt
Pt0 f59 @C|.responsibility Everybody #txt
Pt0 f59 49 785 30 30 -50 17 #rect
Pt0 f59 @|StartRequestIcon #fIcon
Pt0 f60 expr out #txt
Pt0 f60 79 800 184 800 #arcP
Pt0 f61 type ch.ivy.addon.portal.generic.PortalStartData #txt
Pt0 f61 705 785 30 30 0 15 #rect
Pt0 f61 @|EndIcon #fIcon
Pt0 f63 expr out #txt
Pt0 f63 600 800 705 800 #arcP
Pt0 f64 outLink startPortalCase.ivp #txt
Pt0 f64 type ch.ivy.addon.portal.generic.PortalStartData #txt
Pt0 f64 inParamDecl '<> param;' #txt
Pt0 f64 actionDecl 'ch.ivy.addon.portal.generic.PortalStartData out;
' #txt
Pt0 f64 guid 16440C405D1620D0 #txt
Pt0 f64 requestEnabled true #txt
Pt0 f64 triggerEnabled false #txt
Pt0 f64 callSignature startPortalCase() #txt
Pt0 f64 persist false #txt
Pt0 f64 taskData 'TaskTriggered.ROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.EXPRI=2
TaskTriggered.TYPE=0
TaskTriggered.PRI=2
TaskTriggered.EXROL=Everybody' #txt
Pt0 f64 caseData businessCase.attach=true #txt
Pt0 f64 showInStartList 1 #txt
Pt0 f64 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>startPortalCase.ivp</name>
    </language>
</elementInfo>
' #txt
Pt0 f64 @C|.responsibility Everybody #txt
Pt0 f64 49 897 30 30 -52 17 #rect
Pt0 f64 @|StartRequestIcon #fIcon
Pt0 f65 expr out #txt
Pt0 f65 79 912 184 912 #arcP
Pt0 f66 type ch.ivy.addon.portal.generic.PortalStartData #txt
Pt0 f66 705 897 30 30 0 15 #rect
Pt0 f66 @|EndIcon #fIcon
Pt0 f67 expr out #txt
Pt0 f67 600 912 705 912 #arcP
Pt0 f68 actionDecl 'ch.ivy.addon.portal.generic.PortalStartData out;
' #txt
Pt0 f68 actionTable 'out=in;
' #txt
Pt0 f68 actionCode 'import ch.ivy.addon.portalkit.enums.SessionAttribute;
import ch.ivy.addon.portalkit.util.SecurityServiceUtils;
SecurityServiceUtils.setSessionAttribute(SessionAttribute.PORTAL_START_PMV_ID.toString(), ivy.task.getProcessModelVersion().getId());' #txt
Pt0 f68 type ch.ivy.addon.portal.generic.PortalStartData #txt
Pt0 f68 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>store portal start pmv id</name>
        <nameStyle>25
</nameStyle>
    </language>
</elementInfo>
' #txt
Pt0 f68 120 138 144 44 -64 -8 #rect
Pt0 f68 @|StepIcon #fIcon
Pt0 f70 expr out #txt
Pt0 f70 77 160 120 160 #arcP
Pt0 f14 expr out #txt
Pt0 f14 264 160 312 160 #arcP
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
Pt0 f10 mainOut f48 tail #connect
Pt0 f48 head f11 mainIn #connect
Pt0 f11 mainOut f50 tail #connect
Pt0 f50 head f30 in #connect
Pt0 f73 mainOut f118 tail #connect
Pt0 f118 head f69 mainIn #connect
Pt0 f28 mainOut f31 tail #connect
Pt0 f31 head f56 mainIn #connect
Pt0 f62 mainOut f52 tail #connect
Pt0 f52 head f28 mainIn #connect
Pt0 f69 mainOut f46 tail #connect
Pt0 f46 head f84 mainIn #connect
Pt0 f47 mainOut f49 tail #connect
Pt0 f49 head f71 mainIn #connect
Pt0 f71 mainOut f53 tail #connect
Pt0 f53 head f51 mainIn #connect
Pt0 f54 mainOut f55 tail #connect
Pt0 f55 head f82 mainIn #connect
Pt0 f82 mainOut f58 tail #connect
Pt0 f58 head f57 mainIn #connect
Pt0 f59 mainOut f60 tail #connect
Pt0 f60 head f73 mainIn #connect
Pt0 f84 mainOut f63 tail #connect
Pt0 f63 head f61 mainIn #connect
Pt0 f64 mainOut f65 tail #connect
Pt0 f65 head f62 mainIn #connect
Pt0 f56 mainOut f67 tail #connect
Pt0 f67 head f66 mainIn #connect
Pt0 f0 mainOut f70 tail #connect
Pt0 f70 head f68 mainIn #connect
Pt0 f68 mainOut f14 tail #connect
Pt0 f14 head f20 mainIn #connect
