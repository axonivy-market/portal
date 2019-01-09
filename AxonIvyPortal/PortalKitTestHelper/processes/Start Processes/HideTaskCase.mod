[Ivy]
1682D01F36A61D47 3.20 #module
>Proto >Proto Collection #zClass
He0 HideTaskCase Big #zClass
He0 B #cInfo
He0 #process
He0 @TextInP .resExport .resExport #zField
He0 @TextInP .type .type #zField
He0 @TextInP .processKind .processKind #zField
He0 @AnnotationInP-0n ai ai #zField
He0 @MessageFlowInP-0n messageIn messageIn #zField
He0 @MessageFlowOutP-0n messageOut messageOut #zField
He0 @TextInP .xml .xml #zField
He0 @TextInP .responsibility .responsibility #zField
He0 @StartRequest f0 '' #zField
He0 @EndTask f1 '' #zField
He0 @PushWFArc f2 '' #zField
>Proto He0 He0 HideTaskCase #zField
He0 f0 outLink start.ivp #txt
He0 f0 type portalKit_test.Data #txt
He0 f0 inParamDecl '<> param;' #txt
He0 f0 actionDecl 'portalKit_test.Data out;
' #txt
He0 f0 guid 1682D01F391773E0 #txt
He0 f0 requestEnabled true #txt
He0 f0 triggerEnabled false #txt
He0 f0 callSignature start() #txt
He0 f0 caseData businessCase.attach=true #txt
He0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start.ivp</name>
    </language>
</elementInfo>
' #txt
He0 f0 @C|.responsibility Everybody #txt
He0 f0 81 49 30 30 -21 17 #rect
He0 f0 @|StartRequestIcon #fIcon
He0 f1 type portalKit_test.Data #txt
He0 f1 337 49 30 30 0 15 #rect
He0 f1 @|EndIcon #fIcon
He0 f2 111 64 337 64 #arcP
>Proto He0 .type portalKit_test.Data #txt
>Proto He0 .processKind NORMAL #txt
>Proto He0 0 0 32 24 18 0 #rect
>Proto He0 @|BIcon #fIcon
He0 f0 mainOut f2 tail #connect
He0 f2 head f1 mainIn #connect
