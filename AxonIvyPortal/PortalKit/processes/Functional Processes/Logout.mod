[Ivy]
15EF0843F1DB39F2 3.20 #module
>Proto >Proto Collection #zClass
Lt0 Logout Big #zClass
Lt0 B #cInfo
Lt0 #process
Lt0 @TextInP .resExport .resExport #zField
Lt0 @TextInP .type .type #zField
Lt0 @TextInP .processKind .processKind #zField
Lt0 @AnnotationInP-0n ai ai #zField
Lt0 @MessageFlowInP-0n messageIn messageIn #zField
Lt0 @MessageFlowOutP-0n messageOut messageOut #zField
Lt0 @TextInP .xml .xml #zField
Lt0 @TextInP .responsibility .responsibility #zField
Lt0 @StartSub f0 '' #zField
Lt0 @EndSub f1 '' #zField
Lt0 @PushWFArc f2 '' #zField
>Proto Lt0 Lt0 Logout #zField
Lt0 f0 inParamDecl '<> param;' #txt
Lt0 f0 outParamDecl '<> result;
' #txt
Lt0 f0 actionDecl 'portalkit.LogoutData out;
' #txt
Lt0 f0 callSignature call() #txt
Lt0 f0 type portalkit.LogoutData #txt
Lt0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>call()</name>
    </language>
</elementInfo>
' #txt
Lt0 f0 81 49 30 30 -13 17 #rect
Lt0 f0 @|StartSubIcon #fIcon
Lt0 f1 type portalkit.LogoutData #txt
Lt0 f1 337 49 30 30 0 15 #rect
Lt0 f1 @|EndSubIcon #fIcon
Lt0 f2 111 64 337 64 #arcP
>Proto Lt0 .type portalkit.LogoutData #txt
>Proto Lt0 .processKind CALLABLE_SUB #txt
>Proto Lt0 0 0 32 24 18 0 #rect
>Proto Lt0 @|BIcon #fIcon
Lt0 f0 mainOut f2 tail #connect
Lt0 f2 head f1 mainIn #connect
