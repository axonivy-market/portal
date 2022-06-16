[Ivy]
1816B91764BD1180 7.5.0 #module
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
Oa0 @UserDialog f3 '' #zField
Oa0 @PushWFArc f4 '' #zField
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
Oa0 f1 433 49 30 30 0 15 #rect
Oa0 f1 @|EndSubIcon #fIcon
Oa0 f3 dialogId com.axonivy.portal.component.example.ProcessHistoryDetailsExample #txt
Oa0 f3 startMethod start(ch.ivyteam.ivy.workflow.ICase) #txt
Oa0 f3 requestActionDecl '<ch.ivyteam.ivy.workflow.ICase caseView> param;' #txt
Oa0 f3 requestMappingAction 'param.caseView=in.caseView;
' #txt
Oa0 f3 responseMappingAction 'out=in;
' #txt
Oa0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>ProcessHistoryDetailsExample</name>
    </language>
</elementInfo>
' #txt
Oa0 f3 216 42 176 44 -85 -8 #rect
Oa0 f3 @|UserDialogIcon #fIcon
Oa0 f4 111 64 216 64 #arcP
Oa0 f2 392 64 433 64 #arcP
>Proto Oa0 .type com.axonivy.portal.component.example.override.OpenCaseDetailsHookOverrideData #txt
>Proto Oa0 .processKind CALLABLE_SUB #txt
>Proto Oa0 0 0 32 24 18 0 #rect
>Proto Oa0 @|BIcon #fIcon
Oa0 f0 mainOut f4 tail #connect
Oa0 f4 head f3 mainIn #connect
Oa0 f3 mainOut f2 tail #connect
Oa0 f2 head f1 mainIn #connect
