[Ivy]
1785E2E27F48F5D0 9.3.1 #module
>Proto >Proto Collection #zClass
Ps0 PortalDashboardConfigurationProcess Big #zClass
Ps0 RD #cInfo
Ps0 #process
Ps0 @AnnotationInP-0n ai ai #zField
Ps0 @TextInP .type .type #zField
Ps0 @TextInP .processKind .processKind #zField
Ps0 @TextInP .xml .xml #zField
Ps0 @TextInP .responsibility .responsibility #zField
Ps0 @UdInit f0 '' #zField
Ps0 @UdProcessEnd f1 '' #zField
Ps0 @PushWFArc f2 '' #zField
Ps0 @UdEvent f3 '' #zField
Ps0 @UdExitEnd f4 '' #zField
Ps0 @PushWFArc f5 '' #zField
Ps0 @UdProcessEnd f6 '' #zField
Ps0 @UdInit f7 '' #zField
Ps0 @PushWFArc f8 '' #zField
>Proto Ps0 Ps0 PortalDashboardConfigurationProcess #zField
Ps0 f0 guid 1785E2E27FD69201 #txt
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
Ps0 f0 83 51 26 26 -16 15 #rect
Ps0 f1 211 51 26 26 0 12 #rect
Ps0 f2 109 64 211 64 #arcP
Ps0 f3 guid 1785E2E2809D4FA0 #txt
Ps0 f3 actionTable 'out=in;
' #txt
Ps0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>close</name>
    </language>
</elementInfo>
' #txt
Ps0 f3 83 131 26 26 -15 15 #rect
Ps0 f4 211 131 26 26 0 12 #rect
Ps0 f5 109 144 211 144 #arcP
Ps0 f6 211 211 26 26 0 12 #rect
Ps0 f7 guid 17D9E910B3EA06A6 #txt
Ps0 f7 method start() #txt
Ps0 f7 inParameterDecl '<> param;' #txt
Ps0 f7 outParameterDecl '<> result;' #txt
Ps0 f7 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start()</name>
    </language>
</elementInfo>
' #txt
Ps0 f7 83 211 26 26 -16 15 #rect
Ps0 f8 109 224 211 224 #arcP
>Proto Ps0 .type ch.ivy.addon.portal.generic.dashboard.PortalDashboardConfiguration.PortalDashboardConfigurationData #txt
>Proto Ps0 .processKind HTML_DIALOG #txt
>Proto Ps0 -8 -8 16 16 16 26 #rect
Ps0 f0 mainOut f2 tail #connect
Ps0 f2 head f1 mainIn #connect
Ps0 f3 mainOut f5 tail #connect
Ps0 f5 head f4 mainIn #connect
Ps0 f7 mainOut f8 tail #connect
Ps0 f8 head f6 mainIn #connect
