[Ivy]
16EEF13CDA450E67 7.5.0 #module
>Proto >Proto Collection #zClass
Ps0 PortalPages Big #zClass
Ps0 B #cInfo
Ps0 #process
Ps0 @TextInP .type .type #zField
Ps0 @TextInP .processKind .processKind #zField
Ps0 @TextInP .xml .xml #zField
Ps0 @TextInP .responsibility .responsibility #zField
Ps0 @StartRequest f70 '' #zField
Ps0 @UserDialog f24 '' #zField
Ps0 @UserDialog f13 '' #zField
Ps0 @StartRequest f76 '' #zField
Ps0 @PushWFArc f18 '' #zField
Ps0 @PushWFArc f33 '' #zField
Ps0 @StartRequest f31 '' #zField
Ps0 @GridStep f20 '' #zField
Ps0 @GridStep f51 '' #zField
Ps0 @PushWFArc f14 '' #zField
Ps0 @PushWFArc f0 '' #zField
Ps0 @UserDialog f53 '' #zField
Ps0 @PushWFArc f1 '' #zField
Ps0 @StartRequest f2 '' #zField
Ps0 @UserDialog f3 '' #zField
Ps0 @PushWFArc f4 '' #zField
Ps0 @UserDialog f5 '' #zField
Ps0 @StartRequest f6 '' #zField
Ps0 @PushWFArc f7 '' #zField
>Proto Ps0 Ps0 PortalPages #zField
Ps0 f70 outLink StatisticPage.ivp #txt
Ps0 f70 inParamDecl '<> param;' #txt
Ps0 f70 requestEnabled true #txt
Ps0 f70 triggerEnabled false #txt
Ps0 f70 callSignature StatisticPage() #txt
Ps0 f70 persist false #txt
Ps0 f70 taskData 'TaskTriggered.EXPRI=2
TaskTriggered.EXROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.PRI=2
TaskTriggered.ROL=Everybody
TaskTriggered.TYPE=0' #txt
Ps0 f70 caseData businessCase.attach=true #txt
Ps0 f70 showInStartList 0 #txt
Ps0 f70 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>StatisticPage.ivp</name>
    </language>
</elementInfo>
' #txt
Ps0 f70 @C|.responsibility Everybody #txt
Ps0 f70 81 178 30 30 -46 17 #rect
Ps0 f70 @|StartRequestIcon #fIcon
Ps0 f24 dialogId ch.ivy.addon.portal.generic.Processes #txt
Ps0 f24 startMethod start() #txt
Ps0 f24 requestActionDecl '<> param;' #txt
Ps0 f24 responseMappingAction 'out=in;
' #txt
Ps0 f24 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>processes</name>
    </language>
</elementInfo>
' #txt
Ps0 f24 192 74 112 44 -29 -8 #rect
Ps0 f24 @|UserDialogIcon #fIcon
Ps0 f13 dialogId ch.ivy.addon.portal.generic.admin.PortalDashBoard #txt
Ps0 f13 startMethod start() #txt
Ps0 f13 requestActionDecl '<> param;' #txt
Ps0 f13 responseMappingAction 'out=in;
' #txt
Ps0 f13 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>statistic</name>
    </language>
</elementInfo>
' #txt
Ps0 f13 192 171 112 44 -21 -8 #rect
Ps0 f13 @|UserDialogIcon #fIcon
Ps0 f76 outLink DefaultProcessStartListPage.ivp #txt
Ps0 f76 inParamDecl '<> param;' #txt
Ps0 f76 requestEnabled true #txt
Ps0 f76 triggerEnabled false #txt
Ps0 f76 callSignature DefaultProcessStartListPage() #txt
Ps0 f76 persist false #txt
Ps0 f76 taskData 'TaskTriggered.EXPRI=2
TaskTriggered.EXROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.PRI=2
TaskTriggered.ROL=Everybody
TaskTriggered.TYPE=0' #txt
Ps0 f76 caseData businessCase.attach=true #txt
Ps0 f76 showInStartList 0 #txt
Ps0 f76 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>DefaultProcessStartListPage.ivp</name>
    </language>
</elementInfo>
' #txt
Ps0 f76 @C|.responsibility Everybody #txt
Ps0 f76 81 81 30 30 -60 17 #rect
Ps0 f76 @|StartRequestIcon #fIcon
Ps0 f18 111 193 192 193 #arcP
Ps0 f33 111 96 192 96 #arcP
Ps0 f31 outLink DefaultLoginPage.ivp #txt
Ps0 f31 inParamDecl '<String originalUrl> param;' #txt
Ps0 f31 inParamTable 'out.callbackUrl=param.#originalUrl is initialized ? param.originalUrl : null;
' #txt
Ps0 f31 requestEnabled true #txt
Ps0 f31 triggerEnabled false #txt
Ps0 f31 callSignature DefaultLoginPage(String) #txt
Ps0 f31 persist false #txt
Ps0 f31 taskData 'TaskTriggered.EXPRI=2
TaskTriggered.EXROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.PRI=2
TaskTriggered.ROL=Everybody
TaskTriggered.TYPE=0' #txt
Ps0 f31 caseData businessCase.attach=true #txt
Ps0 f31 showInStartList 0 #txt
Ps0 f31 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>DefaultLoginPage.ivp</name>
        <nameStyle>20,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ps0 f31 @C|.responsibility Everybody #txt
Ps0 f31 81 273 30 30 -45 18 #rect
Ps0 f31 @|StartRequestIcon #fIcon
Ps0 f20 actionTable 'out=in;
' #txt
Ps0 f20 actionCode 'import ch.ivy.addon.portalkit.enums.PortalLibrary;
import org.apache.commons.lang3.StringUtils;
import ch.ivyteam.ivy.workflow.StandardProcessType;

String library = PortalLibrary.PORTAL_TEMPLATE.getValue();

String defaultEndPage = ivy.wf.getStandardProcessImplementationLibrary(StandardProcessType.DEFAULT_PAGES_PROCESS_TYPES);
if (StringUtils.isBlank(defaultEndPage)) {
	ivy.wf.setStandardProcessImplementationLibrary(StandardProcessType.DEFAULT_PAGES_PROCESS_TYPES, library);
}' #txt
Ps0 f20 security system #txt
Ps0 f20 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Set default end page 
to Portal</name>
        <nameStyle>31,5
</nameStyle>
    </language>
</elementInfo>
' #txt
Ps0 f20 352 266 144 44 -53 -16 #rect
Ps0 f20 @|StepIcon #fIcon
Ps0 f51 actionTable 'out=in;
' #txt
Ps0 f51 actionCode 'import ch.ivy.addon.portalkit.enums.SessionAttribute;
import ch.ivy.addon.portalkit.util.SecurityServiceUtils;
SecurityServiceUtils.setSessionAttribute(SessionAttribute.PORTAL_START_PMV_ID.toString(), ivy.task.getProcessModelVersion().getId());
' #txt
Ps0 f51 security system #txt
Ps0 f51 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>store portal start &#xD;
pmv </name>
    </language>
</elementInfo>
' #txt
Ps0 f51 192 266 128 44 -43 -16 #rect
Ps0 f51 @|StepIcon #fIcon
Ps0 f14 expr out #txt
Ps0 f14 320 288 352 288 #arcP
Ps0 f0 111 288 192 288 #arcP
Ps0 f53 dialogId ch.ivy.addon.portal.generic.PortalUrlCallback #txt
Ps0 f53 startMethod start(String) #txt
Ps0 f53 requestActionDecl '<String callbackUrl> param;' #txt
Ps0 f53 requestMappingAction 'param.callbackUrl=in.callbackUrl;
' #txt
Ps0 f53 responseMappingAction 'out=in;
' #txt
Ps0 f53 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Redirect to&#13;
callbackUrl</name>
    </language>
</elementInfo>
' #txt
Ps0 f53 552 266 112 44 -30 -16 #rect
Ps0 f53 @|UserDialogIcon #fIcon
Ps0 f1 496 288 552 288 #arcP
Ps0 f2 outLink Error500Page.ivp #txt
Ps0 f2 inParamDecl '<> param;' #txt
Ps0 f2 requestEnabled true #txt
Ps0 f2 triggerEnabled false #txt
Ps0 f2 callSignature Error500Page() #txt
Ps0 f2 caseData businessCase.attach=true #txt
Ps0 f2 showInStartList 0 #txt
Ps0 f2 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Error500Page.ivp</name>
    </language>
</elementInfo>
' #txt
Ps0 f2 @C|.responsibility Everybody #txt
Ps0 f2 81 369 30 30 -44 17 #rect
Ps0 f2 @|StartRequestIcon #fIcon
Ps0 f3 dialogId ch.ivy.addon.portal.error.ErrorPage #txt
Ps0 f3 startMethod start(String) #txt
Ps0 f3 requestActionDecl '<String errorCode> param;' #txt
Ps0 f3 requestMappingAction 'param.errorCode="500";
' #txt
Ps0 f3 responseMappingAction 'out=in;
' #txt
Ps0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>ErrorPage</name>
    </language>
</elementInfo>
' #txt
Ps0 f3 192 362 112 44 -27 -8 #rect
Ps0 f3 @|UserDialogIcon #fIcon
Ps0 f4 111 384 192 384 #arcP
Ps0 f5 dialogId ch.ivy.addon.portal.error.ErrorPage #txt
Ps0 f5 startMethod start(String) #txt
Ps0 f5 requestActionDecl '<String errorCode> param;' #txt
Ps0 f5 requestMappingAction 'param.errorCode="404";
' #txt
Ps0 f5 responseMappingAction 'out=in;
' #txt
Ps0 f5 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>ErrorPage</name>
    </language>
</elementInfo>
' #txt
Ps0 f5 192 458 112 44 -27 -8 #rect
Ps0 f5 @|UserDialogIcon #fIcon
Ps0 f6 outLink Error404Page.ivp #txt
Ps0 f6 inParamDecl '<> param;' #txt
Ps0 f6 requestEnabled true #txt
Ps0 f6 triggerEnabled false #txt
Ps0 f6 callSignature Error404Page() #txt
Ps0 f6 caseData businessCase.attach=true #txt
Ps0 f6 showInStartList 0 #txt
Ps0 f6 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Error404Page.ivp</name>
    </language>
</elementInfo>
' #txt
Ps0 f6 @C|.responsibility Everybody #txt
Ps0 f6 81 465 30 30 -39 17 #rect
Ps0 f6 @|StartRequestIcon #fIcon
Ps0 f7 111 480 192 480 #arcP
>Proto Ps0 .type ch.ivy.addon.portal.generic.PortalPagesData #txt
>Proto Ps0 .processKind NORMAL #txt
>Proto Ps0 0 0 32 24 18 0 #rect
>Proto Ps0 @|BIcon #fIcon
Ps0 f70 mainOut f18 tail #connect
Ps0 f18 head f13 mainIn #connect
Ps0 f76 mainOut f33 tail #connect
Ps0 f33 head f24 mainIn #connect
Ps0 f51 mainOut f14 tail #connect
Ps0 f14 head f20 mainIn #connect
Ps0 f31 mainOut f0 tail #connect
Ps0 f0 head f51 mainIn #connect
Ps0 f20 mainOut f1 tail #connect
Ps0 f1 head f53 mainIn #connect
Ps0 f2 mainOut f4 tail #connect
Ps0 f4 head f3 mainIn #connect
Ps0 f6 mainOut f7 tail #connect
Ps0 f7 head f5 mainIn #connect
