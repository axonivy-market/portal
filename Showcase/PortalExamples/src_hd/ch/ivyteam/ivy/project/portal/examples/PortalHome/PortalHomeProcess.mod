[Ivy]
1624C8263F9A746E 7.5.0 #module
>Proto >Proto Collection #zClass
Ps0 PortalHomeProcess Big #zClass
Ps0 RD #cInfo
Ps0 #process
Ps0 @TextInP .type .type #zField
Ps0 @TextInP .processKind .processKind #zField
Ps0 @AnnotationInP-0n ai ai #zField
Ps0 @MessageFlowInP-0n messageIn messageIn #zField
Ps0 @MessageFlowOutP-0n messageOut messageOut #zField
Ps0 @TextInP .xml .xml #zField
Ps0 @TextInP .responsibility .responsibility #zField
Ps0 @UdInit f0 '' #zField
Ps0 @UdMethod f1 '' #zField
Ps0 @UdProcessEnd f3 '' #zField
Ps0 @PushWFArc f4 '' #zField
Ps0 @PushWFArc f2 '' #zField
>Proto Ps0 Ps0 PortalHomeProcess #zField
Ps0 f0 guid 1624C82641E60027 #txt
Ps0 f0 method start() #txt
Ps0 f0 inParameterDecl '<> param;' #txt
Ps0 f0 outParameterDecl '<> result;' #txt
Ps0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start()</name>
    </language>
</elementInfo>
' #txt
Ps0 f0 83 51 26 26 -16 15 #rect
Ps0 f0 @|UdInitIcon #fIcon
Ps0 f1 guid 1681819D0CB59915 #txt
Ps0 f1 method checkValue() #txt
Ps0 f1 inParameterDecl '<> param;' #txt
Ps0 f1 outParameterDecl '<> result;' #txt
Ps0 f1 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>checkValue()</name>
    </language>
</elementInfo>
' #txt
Ps0 f1 83 147 26 26 -35 15 #rect
Ps0 f1 @|UdMethodIcon #fIcon
Ps0 f3 275 147 26 26 0 12 #rect
Ps0 f3 @|UdProcessEndIcon #fIcon
Ps0 f4 expr out #txt
Ps0 f4 109 160 275 160 #arcP
Ps0 f2 expr out #txt
Ps0 f2 109 64 288 147 #arcP
Ps0 f2 1 288 64 #addKink
Ps0 f2 0 0.7507233480937238 0 0 #arcLabel
>Proto Ps0 .type ch.ivyteam.ivy.project.portal.examples.PortalHome.PortalHomeData #txt
>Proto Ps0 .processKind HTML_DIALOG #txt
>Proto Ps0 -8 -8 16 16 16 26 #rect
>Proto Ps0 '' #fIcon
Ps0 f1 mainOut f4 tail #connect
Ps0 f4 head f3 mainIn #connect
Ps0 f0 mainOut f2 tail #connect
Ps0 f2 head f3 mainIn #connect
