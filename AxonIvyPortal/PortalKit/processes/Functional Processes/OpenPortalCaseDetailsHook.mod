[Ivy]
16DCDC775655EFD5 9.3.1 #module
>Proto >Proto Collection #zClass
Ok0 OpenPortalCaseDetailsHook Big #zClass
Ok0 B #cInfo
Ok0 #process
Ok0 @TextInP .type .type #zField
Ok0 @TextInP .processKind .processKind #zField
Ok0 @TextInP .xml .xml #zField
Ok0 @TextInP .responsibility .responsibility #zField
Ok0 @StartSub f0 '' #zField
Ok0 @EndSub f1 '' #zField
Ok0 @PushWFArc f2 '' #zField
Ok0 @EndSub f3 '' #zField
Ok0 @StartSub f4 '' #zField
Ok0 @PushWFArc f5 '' #zField
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
Ok0 f1 337 49 30 30 0 15 #rect
Ok0 f2 111 64 337 64 #arcP
Ok0 f3 337 177 30 30 0 15 #rect
Ok0 f4 inParamDecl '<ch.ivyteam.ivy.workflow.ICase caseData,Boolean isShowBackButton> param;' #txt
Ok0 f4 inParamTable 'out.caseView=param.caseData;
out.isShowBackButton=param.isShowBackButton;
' #txt
Ok0 f4 outParamDecl '<> result;' #txt
Ok0 f4 callSignature callForIFrame(ch.ivyteam.ivy.workflow.ICase,Boolean) #txt
Ok0 f4 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>callForIFrame(ICase,Boolean)</name>
    </language>
</elementInfo>
' #txt
Ok0 f4 81 177 30 30 -13 17 #rect
Ok0 f5 111 192 337 192 #arcP
>Proto Ok0 .type ch.ivy.add.portalkit.OpenPortalCaseDetailsHookData #txt
>Proto Ok0 .processKind CALLABLE_SUB #txt
>Proto Ok0 0 0 32 24 18 0 #rect
>Proto Ok0 @|BIcon #fIcon
Ok0 f0 mainOut f2 tail #connect
Ok0 f2 head f1 mainIn #connect
Ok0 f4 mainOut f5 tail #connect
Ok0 f5 head f3 mainIn #connect
