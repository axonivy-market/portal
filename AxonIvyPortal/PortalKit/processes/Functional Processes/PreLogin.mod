[Ivy]
16371691870E74BA 3.23 #module
>Proto >Proto Collection #zClass
Pn0 PreLogin Big #zClass
Pn0 B #cInfo
Pn0 #process
Pn0 @TextInP .resExport .resExport #zField
Pn0 @TextInP .type .type #zField
Pn0 @TextInP .processKind .processKind #zField
Pn0 @AnnotationInP-0n ai ai #zField
Pn0 @MessageFlowInP-0n messageIn messageIn #zField
Pn0 @MessageFlowOutP-0n messageOut messageOut #zField
Pn0 @TextInP .xml .xml #zField
Pn0 @TextInP .responsibility .responsibility #zField
Pn0 @StartSub f0 '' #zField
Pn0 @EndSub f1 '' #zField
Pn0 @PushWFArc f2 '' #zField
Pn0 @InfoButton f3 '' #zField
Pn0 @AnnotationArc f4 '' #zField
>Proto Pn0 Pn0 PreLogin #zField
Pn0 f0 inParamDecl '<> param;' #txt
Pn0 f0 outParamDecl '<> result;
' #txt
Pn0 f0 actionDecl 'ch.ivy.add.portalkit.PreLoginData out;
' #txt
Pn0 f0 callSignature call() #txt
Pn0 f0 type ch.ivy.add.portalkit.PreLoginData #txt
Pn0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>call()</name>
    </language>
</elementInfo>
' #txt
Pn0 f0 81 49 30 30 -13 17 #rect
Pn0 f0 @|StartSubIcon #fIcon
Pn0 f1 type ch.ivy.add.portalkit.PreLoginData #txt
Pn0 f1 337 49 30 30 0 15 #rect
Pn0 f1 @|EndSubIcon #fIcon
Pn0 f2 111 64 337 64 #arcP
Pn0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Add your customized pre-login step</name>
        <nameStyle>34
</nameStyle>
    </language>
</elementInfo>
' #txt
Pn0 f3 112 153 208 30 -96 -8 #rect
Pn0 f3 @|IBIcon #fIcon
Pn0 f4 216 153 224 64 #arcP
>Proto Pn0 .type ch.ivy.add.portalkit.PreLoginData #txt
>Proto Pn0 .processKind CALLABLE_SUB #txt
>Proto Pn0 0 0 32 24 18 0 #rect
>Proto Pn0 @|BIcon #fIcon
Pn0 f0 mainOut f2 tail #connect
Pn0 f2 head f1 mainIn #connect
Pn0 f3 ao f4 tail #connect
Pn0 f4 head f2 ai #connect
