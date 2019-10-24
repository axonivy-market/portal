[Ivy]
16583DC1ADB95AD5 7.5.0 #module
>Proto >Proto Collection #zClass
Ns0 NavigateBackTaskPageProcess Big #zClass
Ns0 RD #cInfo
Ns0 #process
Ns0 @TextInP .type .type #zField
Ns0 @TextInP .processKind .processKind #zField
Ns0 @AnnotationInP-0n ai ai #zField
Ns0 @MessageFlowInP-0n messageIn messageIn #zField
Ns0 @MessageFlowOutP-0n messageOut messageOut #zField
Ns0 @TextInP .xml .xml #zField
Ns0 @TextInP .responsibility .responsibility #zField
Ns0 @UdInit f0 '' #zField
Ns0 @UdProcessEnd f1 '' #zField
Ns0 @PushWFArc f2 '' #zField
Ns0 @UdEvent f3 '' #zField
Ns0 @UdEvent f6 '' #zField
Ns0 @UdExitEnd f7 '' #zField
Ns0 @PushWFArc f8 '' #zField
Ns0 @UdProcessEnd f4 '' #zField
Ns0 @GridStep f9 '' #zField
Ns0 @PushWFArc f10 '' #zField
Ns0 @PushWFArc f5 '' #zField
Ns0 @InfoButton f11 '' #zField
Ns0 @AnnotationArc f12 '' #zField
>Proto Ns0 Ns0 NavigateBackTaskPageProcess #zField
Ns0 f0 guid 16583DC1AEFC0C09 #txt
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
Ns0 f2 expr out #txt
Ns0 f2 109 64 211 64 #arcP
Ns0 f3 guid 16583DC1B11CF51A #txt
Ns0 f3 actionTable 'out=in;
' #txt
Ns0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>back</name>
    </language>
</elementInfo>
' #txt
Ns0 f3 83 187 26 26 -15 12 #rect
Ns0 f3 @|UdEventIcon #fIcon
Ns0 f6 guid 16583E3EA1B37F47 #txt
Ns0 f6 actionTable 'out=in;
' #txt
Ns0 f6 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>finish</name>
    </language>
</elementInfo>
' #txt
Ns0 f6 83 115 26 26 -19 17 #rect
Ns0 f6 @|UdEventIcon #fIcon
Ns0 f7 211 115 26 26 0 12 #rect
Ns0 f7 @|UdExitEndIcon #fIcon
Ns0 f8 expr out #txt
Ns0 f8 109 128 211 128 #arcP
Ns0 f4 339 187 26 26 0 12 #rect
Ns0 f4 @|UdProcessEndIcon #fIcon
Ns0 f9 actionTable 'out=in;
' #txt
Ns0 f9 actionCode 'import ch.ivy.addon.portal.generic.navigation.PortalNavigator;
PortalNavigator navigator = new PortalNavigator();
navigator.navigateToPortalEndPage();' #txt
Ns0 f9 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Navigate back</name>
    </language>
</elementInfo>
' #txt
Ns0 f9 176 178 112 44 -38 -8 #rect
Ns0 f9 @|StepIcon #fIcon
Ns0 f10 expr out #txt
Ns0 f10 109 200 176 200 #arcP
Ns0 f5 expr out #txt
Ns0 f5 288 200 339 200 #arcP
Ns0 f11 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Call the method navigateToPortalEndPage of PortalNavigator to navigate back to the previous task list.</name>
        <nameStyle>102,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ns0 f11 168 265 560 30 -277 -8 #rect
Ns0 f11 @|IBIcon #fIcon
Ns0 f12 448 265 232 222 #arcP
>Proto Ns0 .type ch.ivyteam.ivy.project.portal.examples.NavigateBackTaskPage.NavigateBackTaskPageData #txt
>Proto Ns0 .processKind HTML_DIALOG #txt
>Proto Ns0 -8 -8 16 16 16 26 #rect
>Proto Ns0 '' #fIcon
Ns0 f0 mainOut f2 tail #connect
Ns0 f2 head f1 mainIn #connect
Ns0 f6 mainOut f8 tail #connect
Ns0 f8 head f7 mainIn #connect
Ns0 f3 mainOut f10 tail #connect
Ns0 f10 head f9 mainIn #connect
Ns0 f9 mainOut f5 tail #connect
Ns0 f5 head f4 mainIn #connect
Ns0 f11 ao f12 tail #connect
Ns0 f12 head f9 @CG|ai #connect
