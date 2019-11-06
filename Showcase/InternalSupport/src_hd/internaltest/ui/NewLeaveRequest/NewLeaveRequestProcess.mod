[Ivy]
16E3F96130A7ECCA 7.5.0 #module
>Proto >Proto Collection #zClass
Ns0 NewLeaveRequestProcess Big #zClass
Ns0 RD #cInfo
Ns0 #process
Ns0 @TextInP .type .type #zField
Ns0 @TextInP .processKind .processKind #zField
Ns0 @TextInP .xml .xml #zField
Ns0 @TextInP .responsibility .responsibility #zField
Ns0 @UdInit f0 '' #zField
Ns0 @UdProcessEnd f1 '' #zField
Ns0 @PushWFArc f2 '' #zField
Ns0 @UdEvent f3 '' #zField
Ns0 @UdExitEnd f4 '' #zField
Ns0 @PushWFArc f5 '' #zField
>Proto Ns0 Ns0 NewLeaveRequestProcess #zField
Ns0 f0 guid 16E3F961316394DC #txt
Ns0 f0 method start() #txt
Ns0 f0 inParameterDecl '<> param;' #txt
Ns0 f0 outParameterDecl '<> result;' #txt
Ns0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start()</name>
    </language>
</elementInfo>
' #txt
Ns0 f0 83 51 26 26 -16 15 #rect
Ns0 f0 @|UdInitIcon #fIcon
Ns0 f1 211 51 26 26 0 12 #rect
Ns0 f1 @|UdProcessEndIcon #fIcon
Ns0 f2 109 64 211 64 #arcP
Ns0 f3 guid 16E3F961321A0099 #txt
Ns0 f3 actionTable 'out=in;
' #txt
Ns0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>close</name>
    </language>
</elementInfo>
' #txt
Ns0 f3 83 147 26 26 -15 15 #rect
Ns0 f3 @|UdEventIcon #fIcon
Ns0 f4 211 147 26 26 0 12 #rect
Ns0 f4 @|UdExitEndIcon #fIcon
Ns0 f5 109 160 211 160 #arcP
>Proto Ns0 .type internaltest.ui.NewLeaveRequest.NewLeaveRequestData #txt
>Proto Ns0 .processKind HTML_DIALOG #txt
>Proto Ns0 -8 -8 16 16 16 26 #rect
>Proto Ns0 '' #fIcon
Ns0 f0 mainOut f2 tail #connect
Ns0 f2 head f1 mainIn #connect
Ns0 f3 mainOut f5 tail #connect
Ns0 f5 head f4 mainIn #connect
