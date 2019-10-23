[Ivy]
15C7B1E826E6485F 7.5.0 #module
>Proto >Proto Collection #zClass
Sn0 SubmitLeaveReason Big #zClass
Sn0 B #cInfo
Sn0 #process
Sn0 @TextInP .type .type #zField
Sn0 @TextInP .processKind .processKind #zField
Sn0 @AnnotationInP-0n ai ai #zField
Sn0 @MessageFlowInP-0n messageIn messageIn #zField
Sn0 @MessageFlowOutP-0n messageOut messageOut #zField
Sn0 @TextInP .xml .xml #zField
Sn0 @TextInP .responsibility .responsibility #zField
Sn0 @StartRequest f0 '' #zField
Sn0 @EndTask f1 '' #zField
Sn0 @PushWFArc f2 '' #zField
>Proto Sn0 Sn0 SubmitLeaveReason #zField
Sn0 f0 outLink start.ivp #txt
Sn0 f0 inParamDecl '<> param;' #txt
Sn0 f0 requestEnabled true #txt
Sn0 f0 triggerEnabled false #txt
Sn0 f0 callSignature start() #txt
Sn0 f0 caseData businessCase.attach=true #txt
Sn0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start.ivp</name>
    </language>
</elementInfo>
' #txt
Sn0 f0 @C|.responsibility Everybody #txt
Sn0 f0 81 49 30 30 -21 17 #rect
Sn0 f0 @|StartRequestIcon #fIcon
Sn0 f1 337 49 30 30 0 15 #rect
Sn0 f1 @|EndIcon #fIcon
Sn0 f2 111 64 337 64 #arcP
>Proto Sn0 .type internaltest.Data #txt
>Proto Sn0 .processKind NORMAL #txt
>Proto Sn0 0 0 32 24 18 0 #rect
>Proto Sn0 @|BIcon #fIcon
Sn0 f0 mainOut f2 tail #connect
Sn0 f2 head f1 mainIn #connect
