[Ivy]
16BD4CADD9FCEB4E 9.3.1 #module
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
Os0 @InfoButton f10 '' #zField
Os0 @AnnotationArc f11 '' #zField
Os0 @InfoButton f12 '' #zField
Os0 @AnnotationArc f13 '' #zField
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
        <name>call(ICase,Boolean)</name>
    </language>
</elementInfo>
' #txt
Os0 f0 113 81 30 30 -40 -37 #rect
Os0 f1 849 81 30 30 0 15 #rect
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
Os0 f2 440 74 112 44 -50 -8 #rect
Os0 f3 143 96 440 96 #arcP
Os0 f4 552 96 849 96 #arcP
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
Os0 f5 113 401 30 30 -64 -37 #rect
Os0 f6 849 401 30 30 0 15 #rect
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
Os0 f7 416 394 160 44 -70 -8 #rect
Os0 f8 143 416 416 416 #arcP
Os0 f9 576 416 849 416 #arcP
Os0 f10 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Deprecated Note&#13;
&#13;
This callable will be removed from Portal 11.&#13;
To override PortalCase item details UI: you should use Axon Ivy HTMLOverride Dialog to override the PortalCaseDetails Html dialog.</name>
        <nameStyle>15,5,8,0
4,5
45,5,0
132,5
</nameStyle>
    </language>
</elementInfo>
' #txt
Os0 f10 224 162 720 76 -357 -34 #rect
Os0 f11 224 200 138 107 #arcP
Os0 f12 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Deprecated Note&#13;
&#13;
This callable will be removed from Portal 11.&#13;
To override PortalCase item details UI: you should use Axon Ivy HTMLOverride Dialog to override the CaseInformationInIFrame Html dialog.</name>
        <nameStyle>15,5,8,0
4,5
45,5,0
138,5
</nameStyle>
    </language>
</elementInfo>
' #txt
Os0 f12 224 482 768 76 -378 -34 #rect
Os0 f13 224 520 138 427 #arcP
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
Os0 f10 ao f11 tail #connect
Os0 f11 head f0 @CG|ai #connect
Os0 f12 ao f13 tail #connect
Os0 f13 head f5 @CG|ai #connect
