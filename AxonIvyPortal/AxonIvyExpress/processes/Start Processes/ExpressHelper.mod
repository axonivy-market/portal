[Ivy]
16F4048921116813 7.5.0 #module
>Proto >Proto Collection #zClass
Er0 ExpressHelper Big #zClass
Er0 B #cInfo
Er0 #process
Er0 @TextInP .type .type #zField
Er0 @TextInP .processKind .processKind #zField
Er0 @TextInP .xml .xml #zField
Er0 @TextInP .responsibility .responsibility #zField
Er0 @StartRequest f3 '' #zField
Er0 @EndTask f4 '' #zField
Er0 @UserDialog f6 '' #zField
Er0 @PushWFArc f7 '' #zField
Er0 @PushWFArc f5 '' #zField
>Proto Er0 Er0 ExpressHelper #zField
Er0 f3 outLink expressHelper.ivp #txt
Er0 f3 inParamDecl '<> param;' #txt
Er0 f3 requestEnabled true #txt
Er0 f3 triggerEnabled false #txt
Er0 f3 callSignature expressHelper() #txt
Er0 f3 caseData businessCase.attach=true #txt
Er0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>expressHelper.ivp</name>
    </language>
</elementInfo>
' #txt
Er0 f3 @C|.responsibility Everybody #txt
Er0 f3 81 153 30 30 -46 23 #rect
Er0 f3 @|StartRequestIcon #fIcon
Er0 f4 377 153 30 30 0 15 #rect
Er0 f4 @|EndIcon #fIcon
Er0 f6 dialogId ch.ivy.gawfs.workflowCreation.ExpressHelper #txt
Er0 f6 startMethod start() #txt
Er0 f6 requestActionDecl '<> param;' #txt
Er0 f6 responseMappingAction 'out=in;
' #txt
Er0 f6 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>ExpressHelper</name>
    </language>
</elementInfo>
' #txt
Er0 f6 200 146 112 44 -41 -8 #rect
Er0 f6 @|UserDialogIcon #fIcon
Er0 f7 111 168 200 168 #arcP
Er0 f5 312 168 377 168 #arcP
>Proto Er0 .type gawfs.ExpressHelperData #txt
>Proto Er0 .processKind NORMAL #txt
>Proto Er0 0 0 32 24 18 0 #rect
>Proto Er0 @|BIcon #fIcon
Er0 f3 mainOut f7 tail #connect
Er0 f7 head f6 mainIn #connect
Er0 f6 mainOut f5 tail #connect
Er0 f5 head f4 mainIn #connect
