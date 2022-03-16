[Ivy]
17F4E85A986DB827 9.4.1 #module
>Proto >Proto Collection #zClass
Ps0 PortalDashboardConfigurationProcess Big #zClass
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
Ps0 @UdMethod f16 '' #zField
Ps0 @UdProcessEnd f21 '' #zField
Ps0 @GridStep f14 '' #zField
Ps0 @PushWFArc f15 '' #zField
Ps0 @PushWFArc f2 '' #zField
Ps0 @PushWFArc f22 '' #zField
>Proto Ps0 Ps0 PortalDashboardConfigurationProcess #zField
Ps0 f0 guid 17F4E86175D200C3 #txt
Ps0 f0 method start(Boolean) #txt
Ps0 f0 inParameterDecl '<Boolean isPublicDashboard> param;' #txt
Ps0 f0 inParameterMapAction 'out.isPublicDashboard=param.isPublicDashboard;
' #txt
Ps0 f0 outParameterDecl '<> result;' #txt
Ps0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start(Boolean)</name>
    </language>
</elementInfo>
' #txt
Ps0 f0 75 43 26 26 -16 15 #rect
Ps0 f1 203 43 26 26 0 12 #rect
Ps0 f16 guid 17F4E86175DF7C4E #txt
Ps0 f16 method backToHome() #txt
Ps0 f16 inParameterDecl '<> param;' #txt
Ps0 f16 outParameterDecl '<> result;' #txt
Ps0 f16 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>backToHome()</name>
    </language>
</elementInfo>
' #txt
Ps0 f16 75 139 26 26 -25 15 #rect
Ps0 f21 475 139 26 26 0 12 #rect
Ps0 f14 actionTable 'out=in;
' #txt
Ps0 f14 actionCode 'import ch.ivy.addon.portalkit.publicapi.PortalNavigatorAPI;

PortalNavigatorAPI.navigateToPortalEndPage();' #txt
Ps0 f14 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Navigate To Portal EndPage</name>
    </language>
</elementInfo>
' #txt
Ps0 f14 184 130 160 44 -77 -8 #rect
Ps0 f15 101 152 184 152 #arcP
Ps0 f2 101 56 203 56 #arcP
Ps0 f22 344 152 475 152 #arcP
>Proto Ps0 .type ch.ivy.addon.portal.generic.dashboard.PortalDashboardConfiguration.PortalDashboardConfigurationData #txt
>Proto Ps0 .processKind HTML_DIALOG #txt
>Proto Ps0 -8 -8 16 16 16 26 #rect
Ps0 f0 mainOut f2 tail #connect
Ps0 f2 head f1 mainIn #connect
Ps0 f16 mainOut f15 tail #connect
Ps0 f15 head f14 mainIn #connect
Ps0 f14 mainOut f22 tail #connect
Ps0 f22 head f21 mainIn #connect
