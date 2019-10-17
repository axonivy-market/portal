[Ivy]
15AF5A391C3CEE4D 7.5.0 #module
>Proto >Proto Collection #zClass
Ts0 TaskListHeaderProcess Big #zClass
Ts0 RD #cInfo
Ts0 #process
Ts0 @TextInP .type .type #zField
Ts0 @TextInP .processKind .processKind #zField
Ts0 @AnnotationInP-0n ai ai #zField
Ts0 @MessageFlowInP-0n messageIn messageIn #zField
Ts0 @MessageFlowOutP-0n messageOut messageOut #zField
Ts0 @TextInP .xml .xml #zField
Ts0 @TextInP .responsibility .responsibility #zField
Ts0 @UdInit f0 '' #zField
Ts0 @UdProcessEnd f1 '' #zField
Ts0 @PushWFArc f2 '' #zField
>Proto Ts0 Ts0 TaskListHeaderProcess #zField
Ts0 f0 guid 15AE9A0BAA4D09B7 #txt
Ts0 f0 method start() #txt
Ts0 f0 inParameterDecl '<> param;' #txt
Ts0 f0 outParameterDecl '<> result;' #txt
Ts0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start()</name>
        <nameStyle>7,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ts0 f0 53 85 22 22 14 0 #rect
Ts0 f0 @|UdInitIcon #fIcon
Ts0 f1 53 213 22 22 14 0 #rect
Ts0 f1 @|UdProcessEndIcon #fIcon
Ts0 f2 expr out #txt
Ts0 f2 64 107 64 213 #arcP
>Proto Ts0 .type ch.ivy.addon.portalkit.component.task.column.TaskListHeader.TaskListHeaderData #txt
>Proto Ts0 .processKind HTML_DIALOG #txt
>Proto Ts0 -8 -8 16 16 16 26 #rect
>Proto Ts0 '' #fIcon
Ts0 f0 mainOut f2 tail #connect
Ts0 f2 head f1 mainIn #connect
