[Ivy]
16BD4CADD9FCEB4E 9.2.0 #module
>Proto >Proto Collection #zClass
Os0 OpenPortalCaseDetails Big #zClass
Os0 B #cInfo
Os0 #process
Os0 @TextInP .resExport .resExport #zField
Os0 @TextInP .type .type #zField
Os0 @TextInP .processKind .processKind #zField
Os0 @AnnotationInP-0n ai ai #zField
Os0 @MessageFlowInP-0n messageIn messageIn #zField
Os0 @MessageFlowOutP-0n messageOut messageOut #zField
Os0 @TextInP .xml .xml #zField
Os0 @TextInP .responsibility .responsibility #zField
Os0 @StartSub f0 '' #zField
Os0 @EndSub f1 '' #zField
Os0 @UserDialog f2 '' #zField
Os0 @PushWFArc f3 '' #zField
Os0 @PushWFArc f4 '' #zField
Os0 @StartSub f5 '' #zField
Os0 @EndSub f6 '' #zField
Os0 @UserDialog f7 '' #zField
Os0 @PushWFArc f8 '' #zField
Os0 @PushWFArc f9 '' #zField
>Proto Os0 Os0 OpenPortalCaseDetails #zField
Os0 f0 inParamDecl '<ch.ivyteam.ivy.workflow.ICase caseData,Boolean isShowBackButton> param;' #txt
Os0 f0 inParamTable 'out.caseView=param.caseData;
out.isShowBackButton=param.isShowBackButton;
' #txt
Os0 f0 outParamDecl '<> result;' #txt
Os0 f0 callSignature call(ch.ivyteam.ivy.workflow.ICase,Boolean) #txt
Os0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name></name>
    </language>
</elementInfo>
' #txt
Os0 f0 81 49 30 30 0 15 #rect
Os0 f0 @|StartSubIcon #fIcon
Os0 f1 337 49 30 30 0 15 #rect
Os0 f1 @|EndSubIcon #fIcon
Os0 f2 dialogId ch.ivy.addon.portal.generic.PortalCaseDetails #txt
Os0 f2 startMethod start(ch.ivyteam.ivy.workflow.ICase,Boolean) #txt
Os0 f2 requestActionDecl '<ch.ivyteam.ivy.workflow.ICase caseInfo,Boolean isShowBackButton> param;' #txt
Os0 f2 requestMappingAction 'param.caseInfo=in.caseView;
param.isShowBackButton=in.isShowBackButton;
' #txt
Os0 f2 responseMappingAction 'out=in;
' #txt
Os0 f2 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>PortalCaseDetails</name>
    </language>
</elementInfo>
' #txt
Os0 f2 168 42 112 44 -50 -8 #rect
Os0 f2 @|UserDialogIcon #fIcon
Os0 f3 111 64 168 64 #arcP
Os0 f4 280 64 337 64 #arcP
Os0 f5 inParamDecl '<ch.ivyteam.ivy.workflow.ICase caseData,Boolean isShowBackButton> param;' #txt
Os0 f5 inParamTable 'out.caseView=param.caseData;
out.isShowBackButton=param.isShowBackButton;
' #txt
Os0 f5 outParamDecl '<> result;' #txt
Os0 f5 callSignature callForIFrame(ch.ivyteam.ivy.workflow.ICase,Boolean) #txt
Os0 f5 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>callForIFrame(ICase,Boolean)</name>
    </language>
</elementInfo>
' #txt
Os0 f5 81 177 30 30 -65 23 #rect
Os0 f5 @|StartSubIcon #fIcon
Os0 f6 337 177 30 30 0 15 #rect
Os0 f6 @|EndSubIcon #fIcon
Os0 f7 dialogId ch.ivy.addon.portal.component.iframe.CaseInformationInIFrame #txt
Os0 f7 startMethod start(Long,Boolean) #txt
Os0 f7 requestActionDecl '<Long caseId,Boolean showBackButton> param;' #txt
Os0 f7 requestMappingAction 'param.caseId=in.caseView.getId();
param.showBackButton=in.isShowBackButton;
' #txt
Os0 f7 responseMappingAction 'out=in;
' #txt
Os0 f7 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>CaseInformationInIFrame</name>
    </language>
</elementInfo>
' #txt
Os0 f7 144 170 160 44 -70 -8 #rect
Os0 f7 @|UserDialogIcon #fIcon
Os0 f8 111 192 144 192 #arcP
Os0 f9 304 192 337 192 #arcP
>Proto Os0 .type ch.ivy.addon.portal.generic.OpenPortalCaseDetailsData #txt
>Proto Os0 .processKind CALLABLE_SUB #txt
>Proto Os0 0 0 32 24 18 0 #rect
>Proto Os0 @|BIcon #fIcon
Os0 f0 mainOut f3 tail #connect
Os0 f3 head f2 mainIn #connect
Os0 f2 mainOut f4 tail #connect
Os0 f4 head f1 mainIn #connect
Os0 f5 mainOut f8 tail #connect
Os0 f8 head f7 mainIn #connect
Os0 f7 mainOut f9 tail #connect
Os0 f9 head f6 mainIn #connect
