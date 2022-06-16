[Ivy]
1816B8337C036391 7.5.0 #module
>Proto >Proto Collection #zClass
Ok0 OpenPortalCaseDetailsHook Big #zClass
Ok0 B #cInfo
Ok0 #process
Ok0 @AnnotationInP-0n ai ai #zField
Ok0 @TextInP .type .type #zField
Ok0 @TextInP .processKind .processKind #zField
Ok0 @TextInP .xml .xml #zField
Ok0 @TextInP .responsibility .responsibility #zField
Ok0 @StartSub f0 '' #zField
Ok0 @EndSub f1 '' #zField
Ok0 @UserDialog f3 '' #zField
Ok0 @PushWFArc f4 '' #zField
Ok0 @PushWFArc f2 '' #zField
>Proto Ok0 Ok0 OpenPortalCaseDetailsHook #zField
Ok0 f0 inParamDecl '<ch.ivyteam.ivy.workflow.ICase caseData,Boolean isShowBackButton> param;' #txt
Ok0 f0 inParamTable 'out.caseView=param.caseData;
out.isShowBackButton=param.isShowBackButton;
' #txt
Ok0 f0 outParamDecl '<> result;' #txt
Ok0 f0 callSignature call(ch.ivyteam.ivy.workflow.ICase,Boolean) #txt
Ok0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>call(ICase,Boolean)</name>
    </language>
</elementInfo>
' #txt
Ok0 f0 81 49 30 30 -13 17 #rect
Ok0 f0 @|StartSubIcon #fIcon
Ok0 f1 353 49 30 30 0 15 #rect
Ok0 f1 @|EndSubIcon #fIcon
Ok0 f3 dialogId ch.ivyteam.ivy.project.portal.examples.component.customize.CaseItemDetails #txt
Ok0 f3 startMethod start(ch.ivyteam.ivy.workflow.ICase,Boolean) #txt
Ok0 f3 requestActionDecl '<ch.ivyteam.ivy.workflow.ICase caseInfo,Boolean isShowBackButton> param;' #txt
Ok0 f3 requestMappingAction 'param.caseInfo=in.caseView;
param.isShowBackButton=in.isShowBackButton;
' #txt
Ok0 f3 responseMappingAction 'out=in;
' #txt
Ok0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>CaseItemDetails</name>
    </language>
</elementInfo>
' #txt
Ok0 f3 184 42 112 44 -46 -8 #rect
Ok0 f3 @|UserDialogIcon #fIcon
Ok0 f4 111 64 184 64 #arcP
Ok0 f2 296 64 353 64 #arcP
>Proto Ok0 .type _ch.ivyteam.ivy.project.portal.examples.OpenPortalCaseDetailsOverrideData #txt
>Proto Ok0 .processKind CALLABLE_SUB #txt
>Proto Ok0 0 0 32 24 18 0 #rect
>Proto Ok0 @|BIcon #fIcon
Ok0 f0 mainOut f4 tail #connect
Ok0 f4 head f3 mainIn #connect
Ok0 f3 mainOut f2 tail #connect
Ok0 f2 head f1 mainIn #connect
