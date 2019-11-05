[Ivy]
1657E10E845660E8 7.5.0 #module
>Proto >Proto Collection #zClass
Ws0 WelcomePageProcess Big #zClass
Ws0 RD #cInfo
Ws0 #process
Ws0 @TextInP .type .type #zField
Ws0 @TextInP .processKind .processKind #zField
Ws0 @AnnotationInP-0n ai ai #zField
Ws0 @MessageFlowInP-0n messageIn messageIn #zField
Ws0 @MessageFlowOutP-0n messageOut messageOut #zField
Ws0 @TextInP .xml .xml #zField
Ws0 @TextInP .responsibility .responsibility #zField
Ws0 @UdInit f0 '' #zField
Ws0 @UdProcessEnd f1 '' #zField
Ws0 @PushWFArc f2 '' #zField
Ws0 @UdEvent f3 '' #zField
Ws0 @UdExitEnd f4 '' #zField
Ws0 @PushWFArc f5 '' #zField
>Proto Ws0 Ws0 WelcomePageProcess #zField
Ws0 f0 guid 1657E10E86AADF16 #txt
Ws0 f0 method start() #txt
Ws0 f0 inParameterDecl '<> param;' #txt
Ws0 f0 outParameterDecl '<> result;' #txt
Ws0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start()</name>
    </language>
</elementInfo>
' #txt
Ws0 f0 83 51 26 26 -16 15 #rect
Ws0 f0 @|UdInitIcon #fIcon
Ws0 f1 211 51 26 26 0 12 #rect
Ws0 f1 @|UdProcessEndIcon #fIcon
Ws0 f2 expr out #txt
Ws0 f2 109 64 211 64 #arcP
Ws0 f3 guid 1657E10E8B51A5A3 #txt
Ws0 f3 actionTable 'out=in;
' #txt
Ws0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>close</name>
    </language>
</elementInfo>
' #txt
Ws0 f3 83 147 26 26 -15 12 #rect
Ws0 f3 @|UdEventIcon #fIcon
Ws0 f4 211 147 26 26 0 12 #rect
Ws0 f4 @|UdExitEndIcon #fIcon
Ws0 f5 expr out #txt
Ws0 f5 109 160 211 160 #arcP
>Proto Ws0 .type ch.ivyteam.ivy.project.portal.examples.WelcomePage.WelcomePageData #txt
>Proto Ws0 .processKind HTML_DIALOG #txt
>Proto Ws0 -8 -8 16 16 16 26 #rect
>Proto Ws0 '' #fIcon
Ws0 f0 mainOut f2 tail #connect
Ws0 f2 head f1 mainIn #connect
Ws0 f3 mainOut f5 tail #connect
Ws0 f5 head f4 mainIn #connect
