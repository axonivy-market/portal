[Ivy]
1816B861FD857CD2 7.5.0 #module
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
Oa0 @PushWFArc f6 '' #zField
Oa0 @PushWFArc f2 '' #zField
Oa0 @UserDialog f7 '' #zField
Oa0 @PushWFArc f8 '' #zField
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
Oa0 f0 81 49 30 30 -66 24 #rect
Oa0 f0 @|StartSubIcon #fIcon
Oa0 f1 505 49 30 30 0 15 #rect
Oa0 f1 @|EndSubIcon #fIcon
Oa0 f3 176 48 32 32 0 16 #rect
Oa0 f3 @|AlternativeIcon #fIcon
Oa0 f4 111 64 176 64 #arcP
Oa0 f5 dialogId ch.ivyteam.ivy.project.portal.examples.component.customize.CaseItemDetailsInFrame #txt
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
        <name>CaseItemDetailsInFrame</name>
    </language>
</elementInfo>
' #txt
Oa0 f5 320 42 144 44 -69 -8 #rect
Oa0 f5 @|UserDialogIcon #fIcon
Oa0 f6 expr in #txt
Oa0 f6 outCond in.isOpenInFrame #txt
Oa0 f6 208 64 320 64 #arcP
Oa0 f2 464 64 505 64 #arcP
Oa0 f7 dialogId ch.ivyteam.ivy.project.portal.examples.component.customize.CaseItemDetails #txt
Oa0 f7 startMethod start(ch.ivyteam.ivy.workflow.ICase,Boolean) #txt
Oa0 f7 requestActionDecl '<ch.ivyteam.ivy.workflow.ICase caseInfo,Boolean isShowBackButton> param;' #txt
Oa0 f7 requestMappingAction 'param.caseInfo=in.caseView;
param.isShowBackButton=in.isShowBackButton;
' #txt
Oa0 f7 responseMappingAction 'out=in;
' #txt
Oa0 f7 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>CaseItemDetails</name>
    </language>
</elementInfo>
' #txt
Oa0 f7 320 138 112 44 -46 -8 #rect
Oa0 f7 @|UserDialogIcon #fIcon
Oa0 f8 expr in #txt
Oa0 f8 192 80 320 160 #arcP
Oa0 f8 1 192 160 #addKink
Oa0 f8 1 0.2127237139422069 0 0 #arcLabel
>Proto Oa0 .type _ch.ivyteam.ivy.project.portal.examples.OpenCaseDetailsHookOverrideData #txt
>Proto Oa0 .processKind CALLABLE_SUB #txt
>Proto Oa0 0 0 32 24 18 0 #rect
>Proto Oa0 @|BIcon #fIcon
Oa0 f0 mainOut f4 tail #connect
Oa0 f4 head f3 in #connect
Oa0 f3 out f6 tail #connect
Oa0 f6 head f5 mainIn #connect
Oa0 f5 mainOut f2 tail #connect
Oa0 f2 head f1 mainIn #connect
Oa0 f3 out f8 tail #connect
Oa0 f8 head f7 mainIn #connect
