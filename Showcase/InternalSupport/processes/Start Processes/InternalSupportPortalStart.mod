[Ivy]
[>Created: Mon Jun 05 15:06:19 ICT 2017]
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
Pt0 @StartRequest f0 '' #zField
Pt0 @StartRequest f25 '' #zField
Pt0 @InfoButton f16 '' #zField
Pt0 @EndTask f27 '' #zField
Pt0 @CallSub f35 '' #zField
Pt0 @RichDialog f33 '' #zField
Pt0 @StartRequest f10 '' #zField
Pt0 @GridStep f20 '' #zField
Pt0 @GridStep f2 '' #zField
Pt0 @Alternative f4 '' #zField
Pt0 @Alternative f30 '' #zField
Pt0 @GridStep f3 '' #zField
Pt0 @GridStep f11 '' #zField
Pt0 @PushWFArc f28 '' #zField
Pt0 @PushWFArc f5 '' #zField
Pt0 @PushWFArc f8 '' #zField
Pt0 @PushWFArc f6 '' #zField
Pt0 @PushWFArc f15 '' #zField
Pt0 @PushWFArc f34 '' #zField
Pt0 @PushWFArc f31 '' #zField
Pt0 @PushWFArc f7 '' #zField
Pt0 @PushWFArc f14 '' #zField
Pt0 @PushWFArc f1 '' #zField
Pt0 @PushWFArc f29 '' #zField
Pt0 @AnnotationArc f9 '' #zField
>Proto Pt0 Pt0 InternalSupportPortalStart #zField
Pt0 f0 outLink PortalStart.ivp #txt
Pt0 f0 type ch.ivy.addon.portal.generic.PortalStartData #txt
Pt0 f0 inParamDecl '<java.lang.String parameters,java.lang.String portalNavigator> param;' #txt
Pt0 f0 inParamTable 'out.parameters=param.parameters;
out.portalPage=param.parameters.isEmpty() ? ch.ivy.addon.portal.generic.navigation.PortalPage.HOME_PAGE : ch.ivy.addon.portal.generic.navigation.PortalPage.valueOf(param.portalNavigator);
' #txt
Pt0 f0 actionDecl 'ch.ivy.addon.portal.generic.PortalStartData out;
' #txt
Pt0 f0 guid 15C774A2E3C38D1C #txt
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
Pt0 f0 54 84 26 26 -31 17 #rect
Pt0 f0 @|StartRequestIcon #fIcon
Pt0 f25 outLink restorePortalTaskList.ivp #txt
Pt0 f25 type ch.ivy.addon.portal.generic.PortalStartData #txt
Pt0 f25 inParamDecl '<> param;' #txt
Pt0 f25 actionDecl 'ch.ivy.addon.portal.generic.PortalStartData out;
' #txt
Pt0 f25 guid 15C774A2E3CB5448 #txt
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
Pt0 f25 52 210 30 30 -49 15 #rect
Pt0 f25 @|StartRequestIcon #fIcon
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
Pt0 f16 507 184 480 92 -230 -40 #rect
Pt0 f16 @|IBIcon #fIcon
Pt0 f27 type ch.ivy.addon.portal.generic.PortalStartData #txt
Pt0 f27 694 339 30 30 0 15 #rect
Pt0 f27 @|EndIcon #fIcon
Pt0 f35 type ch.ivy.addon.portal.generic.PortalStartData #txt
Pt0 f35 processCall 'Functional Processes/PortalStartPageCallable:call(ch.ivy.addon.portal.generic.PortalStartData)' #txt
Pt0 f35 doCall true #txt
Pt0 f35 requestActionDecl '<ch.ivy.addon.portal.generic.PortalStartData data> param;
' #txt
Pt0 f35 requestMappingAction 'param.data=in;
' #txt
Pt0 f35 responseActionDecl 'ch.ivy.addon.portal.generic.PortalStartData out;
' #txt
Pt0 f35 responseMappingAction 'out=in;
' #txt
Pt0 f35 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>PortalStartPageCallable</name>
    </language>
</elementInfo>
' #txt
Pt0 f35 673 75 144 44 -66 -8 #rect
Pt0 f35 @|CallSubIcon #fIcon
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
Pt0 f33 509 332 112 44 -30 -16 #rect
Pt0 f33 @|RichDialogIcon #fIcon
Pt0 f10 outLink DefaultEndPage.ivp #txt
Pt0 f10 type ch.ivy.addon.portal.generic.PortalStartData #txt
Pt0 f10 inParamDecl '<java.lang.Number endedTaskId> param;' #txt
Pt0 f10 inParamTable 'out.endedTaskId=param.endedTaskId;
' #txt
Pt0 f10 actionDecl 'ch.ivy.addon.portal.generic.PortalStartData out;
' #txt
Pt0 f10 guid 15C774A2E3ECCA52 #txt
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
Pt0 f10 54 341 26 26 -46 17 #rect
Pt0 f10 @|StartRequestIcon #fIcon
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
Pt0 f20 193 67 144 60 -54 -24 #rect
Pt0 f20 @|StepIcon #fIcon
Pt0 f2 actionDecl 'ch.ivy.addon.portal.generic.PortalStartData out;
' #txt
Pt0 f2 actionTable 'out=in;
' #txt
Pt0 f2 actionCode 'import ch.ivy.addon.portalkit.datamodel.TaskLazyDataModel;
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
Pt0 f2 201 203 128 44 -44 -16 #rect
Pt0 f2 @|StepIcon #fIcon
Pt0 f4 type ch.ivy.addon.portal.generic.PortalStartData #txt
Pt0 f4 409 81 32 32 0 16 #rect
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
Pt0 f30 409 338 32 32 -118 5 #rect
Pt0 f30 @|AlternativeIcon #fIcon
Pt0 f3 actionDecl 'ch.ivy.addon.portal.generic.PortalStartData out;
' #txt
Pt0 f3 actionTable 'out=in;
' #txt
Pt0 f3 actionCode 'import ch.ivy.addon.portalkit.datamodel.TaskLazyDataModel;

if (!in.#dataModel is initialized) {
	in.dataModel = new TaskLazyDataModel();
	in.dataModel.compactMode = true;
	in.isDataModelInitialized = true;
}' #txt
Pt0 f3 type ch.ivy.addon.portal.generic.PortalStartData #txt
Pt0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Initialize data model
if it is null</name>
        <nameStyle>35
</nameStyle>
    </language>
</elementInfo>
' #txt
Pt0 f3 481 75 144 44 -51 -16 #rect
Pt0 f3 @|StepIcon #fIcon
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
boolean isTaskStarted = #task is initialized ? task.getStartProcessData() is initialized : false;
String callbackUrl = task.getAdditionalProperty(AdditionalProperty.PORTAL_TASK_CALLBACK_URI.toString());

if (isTaskStarted && StringUtils.isNotBlank(callbackUrl)) {
	out.callbackUrl = callbackUrl;
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
Pt0 f11 157 332 112 44 -47 -8 #rect
Pt0 f11 @|StepIcon #fIcon
Pt0 f28 expr out #txt
Pt0 f28 80 354 157 354 #arcP
Pt0 f5 expr out #txt
Pt0 f5 337 97 409 97 #arcP
Pt0 f8 expr in #txt
Pt0 f8 441 97 481 97 #arcP
Pt0 f6 expr out #txt
Pt0 f6 625 97 673 97 #arcP
Pt0 f15 expr in #txt
Pt0 f15 425 338 425 113 #arcP
Pt0 f15 0 0.47620766243207985 0 0 #arcLabel
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
Pt0 f34 441 354 509 354 #arcP
Pt0 f34 0 0.3793103448275862 0 -13 #arcLabel
Pt0 f31 expr out #txt
Pt0 f31 269 354 409 354 #arcP
Pt0 f7 expr out #txt
Pt0 f7 329 225 425 113 #arcP
Pt0 f7 1 425 225 #addKink
Pt0 f7 0 0.7634111543228898 0 0 #arcLabel
Pt0 f14 expr out #txt
Pt0 f14 80 97 193 97 #arcP
Pt0 f1 expr out #txt
Pt0 f1 82 225 201 225 #arcP
Pt0 f29 expr out #txt
Pt0 f29 621 354 694 354 #arcP
Pt0 f29 0 0.2732919254658385 0 -9 #arcLabel
Pt0 f9 747 184 553 119 #arcP
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
Pt0 f4 out f8 tail #connect
Pt0 f8 head f3 mainIn #connect
Pt0 f3 mainOut f6 tail #connect
Pt0 f6 head f35 mainIn #connect
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
Pt0 f16 ao f9 tail #connect
Pt0 f9 head f3 @CG|ai #connect
