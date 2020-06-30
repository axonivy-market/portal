[Ivy]
172FF89736CD0826 7.5.0 #module
>Proto >Proto Collection #zClass
Ns0 NewPaymentProcess Big #zClass
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
Ns0 @UdEvent f6 '' #zField
Ns0 @UdProcessEnd f7 '' #zField
Ns0 @GridStep f9 '' #zField
Ns0 @PushWFArc f10 '' #zField
Ns0 @PushWFArc f8 '' #zField
>Proto Ns0 Ns0 NewPaymentProcess #zField
Ns0 f0 guid 172FF89737C11463 #txt
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
Ns0 f3 guid 172FF8973857F648 #txt
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
Ns0 f6 guid 172FF8BC352D3D14 #txt
Ns0 f6 actionTable 'out=in;
' #txt
Ns0 f6 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>cancel</name>
    </language>
</elementInfo>
' #txt
Ns0 f6 83 243 26 26 -14 15 #rect
Ns0 f6 @|UdEventIcon #fIcon
Ns0 f7 371 243 26 26 0 12 #rect
Ns0 f7 @|UdProcessEndIcon #fIcon
Ns0 f9 actionTable 'out=in;
' #txt
Ns0 f9 actionCode 'import ch.ivy.addon.portal.generic.navigation.PortalNavigator;
PortalNavigator navigator = new PortalNavigator();
navigator.navigateToPortalEndPage();
' #txt
Ns0 f9 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>NavigateToPortalEndPage</name>
    </language>
</elementInfo>
' #txt
Ns0 f9 160 234 160 44 -72 -8 #rect
Ns0 f9 @|StepIcon #fIcon
Ns0 f10 109 256 160 256 #arcP
Ns0 f8 320 256 371 256 #arcP
>Proto Ns0 .type com.axonivy.portal.developerexamples.NewPayment.NewPaymentData #txt
>Proto Ns0 .processKind HTML_DIALOG #txt
>Proto Ns0 -8 -8 16 16 16 26 #rect
>Proto Ns0 '' #fIcon
Ns0 f0 mainOut f2 tail #connect
Ns0 f2 head f1 mainIn #connect
Ns0 f3 mainOut f5 tail #connect
Ns0 f5 head f4 mainIn #connect
Ns0 f6 mainOut f10 tail #connect
Ns0 f10 head f9 mainIn #connect
Ns0 f9 mainOut f8 tail #connect
Ns0 f8 head f7 mainIn #connect
