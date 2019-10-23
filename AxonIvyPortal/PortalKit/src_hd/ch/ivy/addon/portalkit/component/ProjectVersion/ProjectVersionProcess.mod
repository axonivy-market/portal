[Ivy]
16260402DF4A712B 7.5.0 #module
>Proto >Proto Collection #zClass
Ss0 ProjectVersionProcess Big #zClass
Ss0 RD #cInfo
Ss0 #process
Ss0 @TextInP .type .type #zField
Ss0 @TextInP .processKind .processKind #zField
Ss0 @AnnotationInP-0n ai ai #zField
Ss0 @MessageFlowInP-0n messageIn messageIn #zField
Ss0 @MessageFlowOutP-0n messageOut messageOut #zField
Ss0 @TextInP .xml .xml #zField
Ss0 @TextInP .responsibility .responsibility #zField
Ss0 @UdInit f0 '' #zField
Ss0 @UdProcessEnd f1 '' #zField
Ss0 @PushWFArc f2 '' #zField
Ss0 @UdEvent f3 '' #zField
Ss0 @UdExitEnd f4 '' #zField
Ss0 @PushWFArc f5 '' #zField
>Proto Ss0 Ss0 ProjectVersionProcess #zField
Ss0 f0 guid 152EE20AC7136182 #txt
Ss0 f0 method start() #txt
Ss0 f0 inParameterDecl '<> param;' #txt
Ss0 f0 outParameterDecl '<> result;' #txt
Ss0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start()</name>
    </language>
</elementInfo>
' #txt
Ss0 f0 53 85 22 22 14 0 #rect
Ss0 f0 @|UdInitIcon #fIcon
Ss0 f1 53 213 22 22 14 0 #rect
Ss0 f1 @|UdProcessEndIcon #fIcon
Ss0 f2 expr out #txt
Ss0 f2 64 107 64 213 #arcP
Ss0 f3 guid 152EE20AC7C8E477 #txt
Ss0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>close</name>
    </language>
</elementInfo>
' #txt
Ss0 f3 149 85 22 22 14 0 #rect
Ss0 f3 @|UdEventIcon #fIcon
Ss0 f4 149 213 22 22 14 0 #rect
Ss0 f4 @|UdExitEndIcon #fIcon
Ss0 f5 expr out #txt
Ss0 f5 160 107 160 213 #arcP
>Proto Ss0 .type ch.ivy.addon.portalkit.component.ProjectVersion.ProjectVersionData #txt
>Proto Ss0 .processKind HTML_DIALOG #txt
>Proto Ss0 -8 -8 16 16 16 26 #rect
>Proto Ss0 '' #fIcon
Ss0 f0 mainOut f2 tail #connect
Ss0 f2 head f1 mainIn #connect
Ss0 f3 mainOut f5 tail #connect
Ss0 f5 head f4 mainIn #connect
