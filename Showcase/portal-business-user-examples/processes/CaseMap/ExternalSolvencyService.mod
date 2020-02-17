[Ivy]
17032237DA934868 7.5.0 #module
>Proto >Proto Collection #zClass
Ek0 ExternalSolvencyService Big #zClass
Ek0 B #cInfo
Ek0 #process
Ek0 @TextInP .type .type #zField
Ek0 @TextInP .processKind .processKind #zField
Ek0 @AnnotationInP-0n ai ai #zField
Ek0 @MessageFlowInP-0n messageIn messageIn #zField
Ek0 @MessageFlowOutP-0n messageOut messageOut #zField
Ek0 @TextInP .xml .xml #zField
Ek0 @TextInP .responsibility .responsibility #zField
Ek0 @StartRequest f0 '' #zField
Ek0 @EndTask f1 '' #zField
Ek0 @PushWFArc f2 '' #zField
>Proto Ek0 Ek0 ExternalSolvencyService #zField
Ek0 f0 outLink start.ivp #txt
Ek0 f0 inParamDecl '<> param;' #txt
Ek0 f0 requestEnabled true #txt
Ek0 f0 triggerEnabled false #txt
Ek0 f0 callSignature start() #txt
Ek0 f0 persist false #txt
Ek0 f0 taskData 'TaskTriggered.EXPRI=2
TaskTriggered.EXROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.NAM=<%\=ivy.cms.co("/Processes/CaseMap/externalSolvencyCheck")%>
TaskTriggered.PRI=2
TaskTriggered.ROL=Everybody
TaskTriggered.TYPE=0' #txt
Ek0 f0 caseData businessCase.attach=true #txt
Ek0 f0 showInStartList 0 #txt
Ek0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start.ivp</name>
        <nameStyle>9,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ek0 f0 @C|.responsibility Everybody #txt
Ek0 f0 81 49 30 30 -21 17 #rect
Ek0 f0 @|StartRequestIcon #fIcon
Ek0 f1 337 49 30 30 0 15 #rect
Ek0 f1 @|EndIcon #fIcon
Ek0 f2 expr out #txt
Ek0 f2 111 64 337 64 #arcP
>Proto Ek0 .type com.axonivy.portal.businessuserexamples.Data #txt
>Proto Ek0 .processKind NORMAL #txt
>Proto Ek0 0 0 32 24 18 0 #rect
>Proto Ek0 @|BIcon #fIcon
Ek0 f0 mainOut f2 tail #connect
Ek0 f2 head f1 mainIn #connect
