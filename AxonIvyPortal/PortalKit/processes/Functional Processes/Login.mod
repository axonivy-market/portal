[Ivy]
1694805B8DB28D00 7.5.0 #module
>Proto >Proto Collection #zClass
Ln0 Login Big #zClass
Ln0 B #cInfo
Ln0 #process
Ln0 @TextInP .type .type #zField
Ln0 @TextInP .processKind .processKind #zField
Ln0 @AnnotationInP-0n ai ai #zField
Ln0 @MessageFlowInP-0n messageIn messageIn #zField
Ln0 @MessageFlowOutP-0n messageOut messageOut #zField
Ln0 @TextInP .xml .xml #zField
Ln0 @TextInP .responsibility .responsibility #zField
Ln0 @StartSub f0 '' #zField
Ln0 @EndSub f1 '' #zField
Ln0 @PushWFArc f2 '' #zField
>Proto Ln0 Ln0 Login #zField
Ln0 f0 inParamDecl '<> param;' #txt
Ln0 f0 outParamDecl '<> result;' #txt
Ln0 f0 callSignature call() #txt
Ln0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>call()</name>
    </language>
</elementInfo>
' #txt
Ln0 f0 81 49 30 30 -13 17 #rect
Ln0 f0 @|StartSubIcon #fIcon
Ln0 f1 337 49 30 30 0 15 #rect
Ln0 f1 @|EndSubIcon #fIcon
Ln0 f2 expr out #txt
Ln0 f2 111 64 337 64 #arcP
>Proto Ln0 .type ch.ivy.add.portalkit.LoginData #txt
>Proto Ln0 .processKind CALLABLE_SUB #txt
>Proto Ln0 0 0 32 24 18 0 #rect
>Proto Ln0 @|BIcon #fIcon
Ln0 f0 mainOut f2 tail #connect
Ln0 f2 head f1 mainIn #connect
