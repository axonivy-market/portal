[Ivy]
171E83014BFD91A0 9.3.1 #module
>Proto >Proto Collection #zClass
Ws0 PageHeaderProcess Big #zClass
Ws0 RD #cInfo
Ws0 #process
Ws0 @TextInP .type .type #zField
Ws0 @TextInP .processKind .processKind #zField
Ws0 @AnnotationInP-0n ai ai #zField
Ws0 @TextInP .xml .xml #zField
Ws0 @TextInP .responsibility .responsibility #zField
Ws0 @UdInit f0 '' #zField
Ws0 @UdProcessEnd f1 '' #zField
Ws0 @PushWFArc f2 '' #zField
>Proto Ws0 Ws0 PageHeaderProcess #zField
Ws0 f0 guid 14F86CFEA7E27E33 #txt
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
Ws0 f0 86 54 20 20 13 0 #rect
Ws0 f1 86 150 20 20 13 0 #rect
Ws0 f2 expr out #txt
Ws0 f2 96 74 96 150 #arcP
>Proto Ws0 .type ch.ivy.addon.portalkit.component.PageHeader.PageHeaderData #txt
>Proto Ws0 .processKind HTML_DIALOG #txt
>Proto Ws0 -8 -8 16 16 16 26 #rect
Ws0 f0 mainOut f2 tail #connect
Ws0 f2 head f1 mainIn #connect
