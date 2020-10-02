[Ivy]
1703223D3B2BFDE3 9.2.0 #module
>Proto >Proto Collection #zClass
Rt0 ResumeRequest Big #zClass
Rt0 B #cInfo
Rt0 #process
Rt0 @TextInP .type .type #zField
Rt0 @TextInP .processKind .processKind #zField
Rt0 @AnnotationInP-0n ai ai #zField
Rt0 @MessageFlowInP-0n messageIn messageIn #zField
Rt0 @MessageFlowOutP-0n messageOut messageOut #zField
Rt0 @TextInP .xml .xml #zField
Rt0 @TextInP .responsibility .responsibility #zField
Rt0 @StartRequest f0 '' #zField
Rt0 @EndTask f1 '' #zField
Rt0 @PushWFArc f2 '' #zField
>Proto Rt0 Rt0 ResumeRequest #zField
Rt0 f0 outLink start.ivp #txt
Rt0 f0 inParamDecl '<> param;' #txt
Rt0 f0 requestEnabled true #txt
Rt0 f0 triggerEnabled false #txt
Rt0 f0 callSignature start() #txt
Rt0 f0 persist false #txt
Rt0 f0 taskData 'TaskTriggered.EXPRI=2
TaskTriggered.EXROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.NAM=<%\=ivy.cms.co("/Processes/CaseMap/resumeRequest")%>
TaskTriggered.PRI=2
TaskTriggered.ROL=Everybody
TaskTriggered.TYPE=0' #txt
Rt0 f0 caseData businessCase.attach=true #txt
Rt0 f0 showInStartList 0 #txt
Rt0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start.ivp</name>
        <nameStyle>9,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Rt0 f0 @C|.responsibility Everybody #txt
Rt0 f0 81 49 30 30 -21 17 #rect
Rt0 f0 @|StartRequestIcon #fIcon
Rt0 f1 337 49 30 30 0 15 #rect
Rt0 f1 @|EndIcon #fIcon
Rt0 f2 expr out #txt
Rt0 f2 111 64 337 64 #arcP
>Proto Rt0 .type com.axonivy.portal.userexamples.Data #txt
>Proto Rt0 .processKind NORMAL #txt
>Proto Rt0 0 0 32 24 18 0 #rect
>Proto Rt0 @|BIcon #fIcon
Rt0 f0 mainOut f2 tail #connect
Rt0 f2 head f1 mainIn #connect
