[Ivy]
1679B436E9169973 3.20 #module
>Proto >Proto Collection #zClass
Pt0 PortalInit Big #zClass
Pt0 B #cInfo
Pt0 #process
Pt0 @TextInP .resExport .resExport #zField
Pt0 @TextInP .type .type #zField
Pt0 @TextInP .processKind .processKind #zField
Pt0 @AnnotationInP-0n ai ai #zField
Pt0 @MessageFlowInP-0n messageIn messageIn #zField
Pt0 @MessageFlowOutP-0n messageOut messageOut #zField
Pt0 @TextInP .xml .xml #zField
Pt0 @TextInP .responsibility .responsibility #zField
Pt0 @EndTask f1 '' #zField
Pt0 @StartEvent f0 '' #zField
Pt0 @PushWFArc f2 '' #zField
>Proto Pt0 Pt0 PortalInit #zField
Pt0 f1 type ch.ivy.add.portalkit.Data #txt
Pt0 f1 337 49 30 30 0 15 #rect
Pt0 f1 @|EndIcon #fIcon
Pt0 f0 outerBean "ch.ivy.addon.portalkit.util.PortalDataInitBean" #txt
Pt0 f0 outLink eventLink.ivp #txt
Pt0 f0 type ch.ivy.add.portalkit.Data #txt
Pt0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Init time-consuming portal data</name>
        <nameStyle>31,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Pt0 f0 @C|.responsibility Everybody #txt
Pt0 f0 81 49 30 30 -85 17 #rect
Pt0 f0 @|StartEventIcon #fIcon
Pt0 f2 expr out #txt
Pt0 f2 111 64 337 64 #arcP
>Proto Pt0 .type ch.ivy.add.portalkit.Data #txt
>Proto Pt0 .processKind NORMAL #txt
>Proto Pt0 0 0 32 24 18 0 #rect
>Proto Pt0 @|BIcon #fIcon
Pt0 f0 mainOut f2 tail #connect
Pt0 f2 head f1 mainIn #connect
