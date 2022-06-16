[Ivy]
1816B7BD8BFD9F2A 7.5.0 #module
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
Oa0 @PushWFArc f2 '' #zField
>Proto Oa0 Oa0 OpenCaseDetailsHook #zField
Oa0 f0 inParamDecl '<ch.ivyteam.ivy.workflow.ICase caseView,Boolean isOpenInFrame,Boolean isShowBackButton> param;' #txt
Oa0 f0 inParamTable 'out.caseView=param.caseView;
out.isOpenInFrame=param.isOpenInFrame;
out.isShowBackButton=param.isShowBackButton;
' #txt
Oa0 f0 outParamDecl '<> result;' #txt
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
Oa0 f1 337 49 30 30 0 15 #rect
Oa0 f1 @|EndSubIcon #fIcon
Oa0 f2 111 64 337 64 #arcP
>Proto Oa0 .type com.axonivy.portal.OpenCaseDetailsHookData #txt
>Proto Oa0 .processKind CALLABLE_SUB #txt
>Proto Oa0 0 0 32 24 18 0 #rect
>Proto Oa0 @|BIcon #fIcon
Oa0 f0 mainOut f2 tail #connect
Oa0 f2 head f1 mainIn #connect
