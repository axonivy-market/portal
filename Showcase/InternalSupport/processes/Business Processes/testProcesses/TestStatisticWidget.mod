[Ivy]
16150F67EE2A7F31 7.5.0 #module
>Proto >Proto Collection #zClass
Tt0 TestStatisticWidget Big #zClass
Tt0 B #cInfo
Tt0 #process
Tt0 @TextInP .type .type #zField
Tt0 @TextInP .processKind .processKind #zField
Tt0 @AnnotationInP-0n ai ai #zField
Tt0 @MessageFlowInP-0n messageIn messageIn #zField
Tt0 @MessageFlowOutP-0n messageOut messageOut #zField
Tt0 @TextInP .xml .xml #zField
Tt0 @TextInP .responsibility .responsibility #zField
Tt0 @StartRequest f0 '' #zField
Tt0 @EndTask f1 '' #zField
Tt0 @UserDialog f3 '' #zField
Tt0 @PushWFArc f4 '' #zField
Tt0 @PushWFArc f2 '' #zField
>Proto Tt0 Tt0 TestStatisticWidget #zField
Tt0 f0 outLink start.ivp #txt
Tt0 f0 inParamDecl '<> param;' #txt
Tt0 f0 requestEnabled true #txt
Tt0 f0 triggerEnabled false #txt
Tt0 f0 callSignature start() #txt
Tt0 f0 caseData businessCase.attach=true #txt
Tt0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start.ivp</name>
    </language>
</elementInfo>
' #txt
Tt0 f0 @C|.responsibility Everybody #txt
Tt0 f0 81 49 30 30 -21 17 #rect
Tt0 f0 @|StartRequestIcon #fIcon
Tt0 f1 337 49 30 30 0 15 #rect
Tt0 f1 @|EndIcon #fIcon
Tt0 f3 dialogId internalPortal.TestStatisticWidgetPage #txt
Tt0 f3 startMethod start() #txt
Tt0 f3 requestActionDecl '<> param;' #txt
Tt0 f3 responseActionDecl 'internaltest.TestStatisticWidgetData out;
' #txt
Tt0 f3 responseMappingAction 'out=in;
' #txt
Tt0 f3 168 42 112 44 0 -8 #rect
Tt0 f3 @|UserDialogIcon #fIcon
Tt0 f4 expr out #txt
Tt0 f4 111 64 168 64 #arcP
Tt0 f2 expr out #txt
Tt0 f2 280 64 337 64 #arcP
>Proto Tt0 .type internaltest.TestStatisticWidgetData #txt
>Proto Tt0 .processKind NORMAL #txt
>Proto Tt0 0 0 32 24 18 0 #rect
>Proto Tt0 @|BIcon #fIcon
Tt0 f0 mainOut f4 tail #connect
Tt0 f4 head f3 mainIn #connect
Tt0 f3 mainOut f2 tail #connect
Tt0 f2 head f1 mainIn #connect
