[Ivy]
15C7B1DACB6CCA9D 7.5.0 #module
>Proto >Proto Collection #zClass
Ua0 UploadAdditionalData Big #zClass
Ua0 B #cInfo
Ua0 #process
Ua0 @TextInP .type .type #zField
Ua0 @TextInP .processKind .processKind #zField
Ua0 @AnnotationInP-0n ai ai #zField
Ua0 @MessageFlowInP-0n messageIn messageIn #zField
Ua0 @MessageFlowOutP-0n messageOut messageOut #zField
Ua0 @TextInP .xml .xml #zField
Ua0 @TextInP .responsibility .responsibility #zField
Ua0 @StartRequest f0 '' #zField
Ua0 @EndTask f1 '' #zField
Ua0 @PushWFArc f2 '' #zField
>Proto Ua0 Ua0 UploadAdditionalData #zField
Ua0 f0 outLink start.ivp #txt
Ua0 f0 inParamDecl '<> param;' #txt
Ua0 f0 requestEnabled true #txt
Ua0 f0 triggerEnabled false #txt
Ua0 f0 callSignature start() #txt
Ua0 f0 caseData businessCase.attach=true #txt
Ua0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start.ivp</name>
    </language>
</elementInfo>
' #txt
Ua0 f0 @C|.responsibility Everybody #txt
Ua0 f0 81 49 30 30 -21 17 #rect
Ua0 f0 @|StartRequestIcon #fIcon
Ua0 f1 337 49 30 30 0 15 #rect
Ua0 f1 @|EndIcon #fIcon
Ua0 f2 111 64 337 64 #arcP
>Proto Ua0 .type internaltest.Data #txt
>Proto Ua0 .processKind NORMAL #txt
>Proto Ua0 0 0 32 24 18 0 #rect
>Proto Ua0 @|BIcon #fIcon
Ua0 f0 mainOut f2 tail #connect
Ua0 f2 head f1 mainIn #connect
