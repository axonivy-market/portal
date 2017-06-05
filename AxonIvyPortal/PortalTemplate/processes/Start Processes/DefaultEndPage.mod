[Ivy]
[>Created: Mon Jun 05 14:10:04 ICT 2017]
15C685ACE14B261F 3.20 #module
>Proto >Proto Collection #zClass
De0 DefaultEndPage Big #zClass
De0 B #cInfo
De0 #process
De0 @TextInP .resExport .resExport #zField
De0 @TextInP .type .type #zField
De0 @TextInP .processKind .processKind #zField
De0 @AnnotationInP-0n ai ai #zField
De0 @MessageFlowInP-0n messageIn messageIn #zField
De0 @MessageFlowOutP-0n messageOut messageOut #zField
De0 @TextInP .xml .xml #zField
De0 @TextInP .responsibility .responsibility #zField
De0 @EndTask f27 '' #zField
De0 @Alternative f30 '' #zField
De0 @StartRequest f10 '' #zField
De0 @GridStep f11 '' #zField
De0 @RichDialog f33 '' #zField
De0 @PushWFArc f29 '' #zField
De0 @PushWFArc f31 '' #zField
De0 @PushWFArc f34 '' #zField
De0 @PushWFArc f28 '' #zField
De0 @CallSub f35 '' #zField
De0 @PushWFArc f0 '' #zField
>Proto De0 De0 DefaultEndPage #zField
De0 f27 type ch.ivy.addon.portal.generic.PortalStartData #txt
De0 f27 383 272 30 30 0 15 #rect
De0 f27 @|EndIcon #fIcon
De0 f30 type ch.ivy.addon.portal.generic.PortalStartData #txt
De0 f30 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>is there callbackUrl?</name>
        <nameStyle>21
</nameStyle>
    </language>
</elementInfo>
' #txt
De0 f30 382 79 32 32 -84 -30 #rect
De0 f30 @|AlternativeIcon #fIcon
De0 f10 outLink DefaultEndPage.ivp #txt
De0 f10 type ch.ivy.addon.portal.generic.PortalStartData #txt
De0 f10 inParamDecl '<java.lang.Number endedTaskId> param;' #txt
De0 f10 inParamTable 'out.endedTaskId=param.endedTaskId;
' #txt
De0 f10 actionDecl 'ch.ivy.addon.portal.generic.PortalStartData out;
' #txt
De0 f10 guid 15C685D8C79FCF1F #txt
De0 f10 requestEnabled true #txt
De0 f10 triggerEnabled false #txt
De0 f10 callSignature DefaultEndPage(Number) #txt
De0 f10 persist false #txt
De0 f10 taskData 'TaskTriggered.ROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.EXPRI=2
TaskTriggered.TYPE=0
TaskTriggered.PRI=2
TaskTriggered.EXROL=Everybody' #txt
De0 f10 caseData businessCase.attach=true #txt
De0 f10 showInStartList 0 #txt
De0 f10 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>DefaultEndPage.ivp</name>
        <nameStyle>18,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
De0 f10 @C|.responsibility Everybody #txt
De0 f10 81 82 26 26 -46 17 #rect
De0 f10 @|StartRequestIcon #fIcon
De0 f11 actionDecl 'ch.ivy.addon.portal.generic.PortalStartData out;
' #txt
De0 f11 actionTable 'out=in;
' #txt
De0 f11 actionCode 'import ch.ivy.addon.portalkit.enums.SessionAttribute;
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
De0 f11 security system #txt
De0 f11 type ch.ivy.addon.portal.generic.PortalStartData #txt
De0 f11 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Handle end page</name>
        <nameStyle>15
</nameStyle>
    </language>
</elementInfo>
' #txt
De0 f11 190 73 112 44 -47 -8 #rect
De0 f11 @|StepIcon #fIcon
De0 f33 targetWindow NEW #txt
De0 f33 targetDisplay TOP #txt
De0 f33 richDialogId ch.ivy.addon.portal.generic.PortalTaskListCallback #txt
De0 f33 startMethod start(String) #txt
De0 f33 type ch.ivy.addon.portal.generic.PortalStartData #txt
De0 f33 requestActionDecl '<String callbackUrl> param;' #txt
De0 f33 requestMappingAction 'param.callbackUrl=in.callbackUrl;
' #txt
De0 f33 responseActionDecl 'ch.ivy.addon.portal.generic.PortalStartData out;
' #txt
De0 f33 responseMappingAction 'out=in;
' #txt
De0 f33 isAsynch false #txt
De0 f33 isInnerRd false #txt
De0 f33 userContext '* ' #txt
De0 f33 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Redirect to
callbackUrl</name>
        <nameStyle>23
</nameStyle>
    </language>
</elementInfo>
' #txt
De0 f33 342 169 112 44 -30 -16 #rect
De0 f33 @|RichDialogIcon #fIcon
De0 f29 expr out #txt
De0 f29 398 213 398 272 #arcP
De0 f29 0 0.2732919254658385 9 0 #arcLabel
De0 f31 expr out #txt
De0 f31 302 95 382 95 #arcP
De0 f34 expr in #txt
De0 f34 outCond 'org.apache.commons.lang3.StringUtils.isNotBlank(in.#callbackUrl)' #txt
De0 f34 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>YES</name>
        <nameStyle>3
</nameStyle>
    </language>
</elementInfo>
' #txt
De0 f34 398 111 398 169 #arcP
De0 f34 0 0.3793103448275862 14 0 #arcLabel
De0 f28 expr out #txt
De0 f28 107 95 190 95 #arcP
De0 f35 type ch.ivy.addon.portal.generic.PortalStartData #txt
De0 f35 processCall 'Functional Processes/PortalStartPageCallable:call(ch.ivy.addon.portal.generic.PortalStartData)' #txt
De0 f35 doCall true #txt
De0 f35 requestActionDecl '<ch.ivy.addon.portal.generic.PortalStartData data> param;
' #txt
De0 f35 requestMappingAction 'param.data=in;
' #txt
De0 f35 responseActionDecl 'ch.ivy.addon.portal.generic.PortalStartData out;
' #txt
De0 f35 responseMappingAction 'out=in;
' #txt
De0 f35 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>PortalStartPageCallable</name>
    </language>
</elementInfo>
' #txt
De0 f35 506 73 144 44 -66 -8 #rect
De0 f35 @|CallSubIcon #fIcon
De0 f0 expr in #txt
De0 f0 414 95 506 95 #arcP
>Proto De0 .type ch.ivy.addon.portal.generic.PortalStartData #txt
>Proto De0 .processKind NORMAL #txt
>Proto De0 0 0 32 24 18 0 #rect
>Proto De0 @|BIcon #fIcon
De0 f10 mainOut f28 tail #connect
De0 f28 head f11 mainIn #connect
De0 f11 mainOut f31 tail #connect
De0 f31 head f30 in #connect
De0 f30 out f34 tail #connect
De0 f34 head f33 mainIn #connect
De0 f33 mainOut f29 tail #connect
De0 f29 head f27 mainIn #connect
De0 f30 out f0 tail #connect
De0 f0 head f35 mainIn #connect
