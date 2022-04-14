[Ivy]
18026C6534B6FBA3 9.4.6 #module
>Proto >Proto Collection #zClass
Ps0 ProcessItemActionProcess Big #zClass
Ps0 RD #cInfo
Ps0 #process
Ps0 @AnnotationInP-0n ai ai #zField
Ps0 @TextInP .type .type #zField
Ps0 @TextInP .processKind .processKind #zField
Ps0 @TextInP .colors .colors #zField
Ps0 @TextInP .xml .xml #zField
Ps0 @TextInP .responsibility .responsibility #zField
Ps0 @TextInP color color #zField
Ps0 @UdInit f0 '' #zField
Ps0 @UdProcessEnd f1 '' #zField
Ps0 @PushWFArc f2 '' #zField
Ps0 @UdEvent f3 '' #zField
Ps0 @UdExitEnd f4 '' #zField
Ps0 @PushWFArc f5 '' #zField
Ps0 @UdMethod f6 '' #zField
Ps0 @UdProcessEnd f7 '' #zField
Ps0 @PushWFArc f8 '' #zField
>Proto Ps0 Ps0 ProcessItemActionProcess #zField
Ps0 f0 guid 18026C6536D98FD9 #txt
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
Ps0 f1 211 51 26 26 0 12 #rect
Ps0 f2 109 64 211 64 #arcP
Ps0 f3 guid 18026C6537FA53B5 #txt
Ps0 f3 actionTable 'out=in;
' #txt
Ps0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>close</name>
    </language>
</elementInfo>
' #txt
Ps0 f3 83 147 26 26 -15 15 #rect
Ps0 f4 211 147 26 26 0 12 #rect
Ps0 f5 109 160 211 160 #arcP
Ps0 f6 guid 18026FF059CA2BBD #txt
Ps0 f6 method collectProcesses() #txt
Ps0 f6 inParameterDecl '<> param;' #txt
Ps0 f6 outParameterDecl '<> result;' #txt
Ps0 f6 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>collectProcesses()</name>
    </language>
</elementInfo>
' #txt
Ps0 f6 83 275 26 26 -25 15 #rect
Ps0 f7 227 275 26 26 0 12 #rect
Ps0 f8 109 288 227 288 #arcP
>Proto Ps0 .type ch.ivy.addon.portalkit.component.process.ProcessItemAction.ProcessItemActionData #txt
>Proto Ps0 .processKind HTML_DIALOG #txt
>Proto Ps0 -8 -8 16 16 16 26 #rect
Ps0 f0 mainOut f2 tail #connect
Ps0 f2 head f1 mainIn #connect
Ps0 f3 mainOut f5 tail #connect
Ps0 f5 head f4 mainIn #connect
Ps0 f6 mainOut f8 tail #connect
Ps0 f8 head f7 mainIn #connect
