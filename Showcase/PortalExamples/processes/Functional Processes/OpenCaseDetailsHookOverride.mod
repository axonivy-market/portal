[Ivy]
01816C35F188C4B9 7.5.0 #module
>Proto >Proto Collection #zClass
Oa0 OpenCaseDetailsHook Big #zClass
Oa0 B #cInfo
Oa0 #process
Oa0 @AnnotationInP-0n ai ai #zField
Oa0 @TextInP .type .type #zField
Oa0 @TextInP .processKind .processKind #zField
Oa0 @TextInP .xml .xml #zField
Oa0 @TextInP .responsibility .responsibility #zField
Oa0 @StartSub f0 '' #zField
Oa0 @EndSub f1 '' #zField
Oa0 @Alternative f3 '' #zField
Oa0 @PushWFArc f4 '' #zField
Oa0 @UserDialog f5 '' #zField
Oa0 @GridStep f6 '' #zField
Oa0 @PushWFArc f7 '' #zField
Oa0 @PushWFArc f2 '' #zField
Oa0 @PushWFArc f8 '' #zField
Oa0 @PushWFArc f9 '' #zField
>Proto Oa0 Oa0 OpenCaseDetailsHook #zField
Oa0 f0 inParamDecl '<ch.ivyteam.ivy.workflow.ICase caseView,Boolean isOpenInFrame,Boolean isShowBackButton> param;' #txt
Oa0 f0 inParamTable 'out.caseView=param.caseView;
out.isOpenInFrame=param.isOpenInFrame;
out.isShowBackButton=param.isShowBackButton;
' #txt
Oa0 f0 outParamDecl '<String caseDetailsUrl> result;' #txt
Oa0 f0 outParamTable 'result.caseDetailsUrl=in.caseDetailsUrl;
' #txt
Oa0 f0 callSignature call(ch.ivyteam.ivy.workflow.ICase,Boolean,Boolean) #txt
Oa0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>call(ICase,Boolean,Boolean)</name>
    </language>
</elementInfo>
' #txt
Oa0 f0 81 49 30 30 -13 17 #rect
Oa0 f0 @|StartSubIcon #fIcon
Oa0 f1 561 49 30 30 0 15 #rect
Oa0 f1 @|EndSubIcon #fIcon
Oa0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Is open in Iframe ?</name>
    </language>
</elementInfo>
' #txt
Oa0 f3 240 48 32 32 -46 -36 #rect
Oa0 f3 @|AlternativeIcon #fIcon
Oa0 f4 111 64 240 64 #arcP
Oa0 f5 dialogId ch.ivyteam.ivy.project.portal.examples.component.customize.CaseItemDetails #txt
Oa0 f5 startMethod start(ch.ivyteam.ivy.workflow.ICase,Boolean) #txt
Oa0 f5 requestActionDecl '<ch.ivyteam.ivy.workflow.ICase caseInfo,Boolean isShowBackButton> param;' #txt
Oa0 f5 requestMappingAction 'param.caseInfo=in.caseView;
param.isShowBackButton=in.isShowBackButton;
' #txt
Oa0 f5 responseMappingAction 'out=in;
' #txt
Oa0 f5 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>CaseItemDetails</name>
    </language>
</elementInfo>
' #txt
Oa0 f5 360 162 112 44 -46 -8 #rect
Oa0 f5 @|UserDialogIcon #fIcon
Oa0 f6 actionTable 'out=in;
' #txt
Oa0 f6 actionCode 'import org.apache.commons.lang3.StringUtils;
import ch.ivy.addon.portalkit.util.CaseUtils;
import ch.ivy.addon.portalkit.util.SecurityServiceUtils;

String customizePortalFriendlyRequestPath = SecurityServiceUtils.findFriendlyRequestPathContainsKeyword("CaseDetailsPage.ivp");

if (StringUtils.isEmpty(customizePortalFriendlyRequestPath)) {
	customizePortalFriendlyRequestPath = "Start Processes/PortalStart/CaseDetailsPage.ivp";
}

in.caseDetailsUrl = CaseUtils.getProcessStartUriWithCaseParameters(in.caseView, customizePortalFriendlyRequestPath);' #txt
Oa0 f6 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>get case details URL</name>
    </language>
</elementInfo>
' #txt
Oa0 f6 352 42 128 44 -57 -8 #rect
Oa0 f6 @|StepIcon #fIcon
Oa0 f7 expr in #txt
Oa0 f7 outCond in.isOpenInFrame #txt
Oa0 f7 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>yes</name>
    </language>
</elementInfo>
' #txt
Oa0 f7 272 64 352 64 #arcP
Oa0 f7 0 0.375 0 10 #arcLabel
Oa0 f2 480 64 561 64 #arcP
Oa0 f8 expr in #txt
Oa0 f8 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>no</name>
    </language>
</elementInfo>
' #txt
Oa0 f8 256 80 360 184 #arcP
Oa0 f8 1 256 184 #addKink
Oa0 f8 1 0.47115384615384615 0 -8 #arcLabel
Oa0 f9 472 184 576 79 #arcP
Oa0 f9 1 576 184 #addKink
Oa0 f9 0 0.8088796633877624 0 0 #arcLabel
>Proto Oa0 .type _ch.ivyteam.ivy.project.portal.examples.OpenCaseDetailsHookOverrideData #txt
>Proto Oa0 .processKind CALLABLE_SUB #txt
>Proto Oa0 0 0 32 24 18 0 #rect
>Proto Oa0 @|BIcon #fIcon
Oa0 f0 mainOut f4 tail #connect
Oa0 f4 head f3 in #connect
Oa0 f3 out f7 tail #connect
Oa0 f7 head f6 mainIn #connect
Oa0 f6 mainOut f2 tail #connect
Oa0 f2 head f1 mainIn #connect
Oa0 f3 out f8 tail #connect
Oa0 f8 head f5 mainIn #connect
Oa0 f5 mainOut f9 tail #connect
Oa0 f9 head f1 mainIn #connect
