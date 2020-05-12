[Ivy]
171061A88CF2D492 7.5.0 #module
>Proto >Proto Collection #zClass
Bs0 PortalBreadcrumbProcess Big #zClass
Bs0 RD #cInfo
Bs0 #process
Bs0 @TextInP .type .type #zField
Bs0 @TextInP .processKind .processKind #zField
Bs0 @TextInP .xml .xml #zField
Bs0 @TextInP .responsibility .responsibility #zField
Bs0 @UdInit f0 '' #zField
Bs0 @UdProcessEnd f1 '' #zField
Bs0 @PushWFArc f2 '' #zField
Bs0 @UdEvent f3 '' #zField
Bs0 @UdExitEnd f4 '' #zField
Bs0 @PushWFArc f5 '' #zField
Bs0 @UdMethod f28 '' #zField
Bs0 @GridStep f29 '' #zField
Bs0 @PushWFArc f30 '' #zField
Bs0 @UdProcessEnd f31 '' #zField
Bs0 @PushWFArc f32 '' #zField
>Proto Bs0 Bs0 PortalBreadcrumbProcess #zField
Bs0 f0 guid 171061A8BDE2A71D #txt
Bs0 f0 method start() #txt
Bs0 f0 inParameterDecl '<> param;' #txt
Bs0 f0 outParameterDecl '<> result;' #txt
Bs0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start()</name>
    </language>
</elementInfo>
' #txt
Bs0 f0 83 51 26 26 -16 15 #rect
Bs0 f0 @|UdInitIcon #fIcon
Bs0 f1 211 51 26 26 0 12 #rect
Bs0 f1 @|UdProcessEndIcon #fIcon
Bs0 f2 109 64 211 64 #arcP
Bs0 f3 guid 171061A8C498ABA1 #txt
Bs0 f3 actionTable 'out=in;
' #txt
Bs0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>close</name>
    </language>
</elementInfo>
' #txt
Bs0 f3 307 51 26 26 -15 15 #rect
Bs0 f3 @|UdEventIcon #fIcon
Bs0 f4 435 51 26 26 0 12 #rect
Bs0 f4 @|UdExitEndIcon #fIcon
Bs0 f5 333 64 435 64 #arcP
Bs0 f28 guid 1715770AAA949799 #txt
Bs0 f28 method navigateToCaseList() #txt
Bs0 f28 inParameterDecl '<> param;' #txt
Bs0 f28 outParameterDecl '<> result;' #txt
Bs0 f28 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>navigateToCaseList()</name>
    </language>
</elementInfo>
' #txt
Bs0 f28 83 147 26 26 -46 14 #rect
Bs0 f28 @|UdMethodIcon #fIcon
Bs0 f29 actionTable 'out=in;
' #txt
Bs0 f29 actionCode 'import ch.ivy.addon.portal.generic.navigation.PortalNavigator;

PortalNavigator navigator = new PortalNavigator();
navigator.navigateToPortalCase();
' #txt
Bs0 f29 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Navigate</name>
    </language>
</elementInfo>
' #txt
Bs0 f29 224 138 112 44 -24 -8 #rect
Bs0 f29 @|StepIcon #fIcon
Bs0 f30 109 160 224 160 #arcP
Bs0 f31 499 147 26 26 0 12 #rect
Bs0 f31 @|UdProcessEndIcon #fIcon
Bs0 f32 336 160 499 160 #arcP
>Proto Bs0 .type ch.ivy.addon.portal.generic.PortalBreadcrumb.PortalBreadcrumbData #txt
>Proto Bs0 .processKind HTML_DIALOG #txt
>Proto Bs0 -8 -8 16 16 16 26 #rect
>Proto Bs0 '' #fIcon
Bs0 f0 mainOut f2 tail #connect
Bs0 f2 head f1 mainIn #connect
Bs0 f3 mainOut f5 tail #connect
Bs0 f5 head f4 mainIn #connect
Bs0 f28 mainOut f30 tail #connect
Bs0 f30 head f29 mainIn #connect
Bs0 f29 mainOut f32 tail #connect
Bs0 f32 head f31 mainIn #connect
