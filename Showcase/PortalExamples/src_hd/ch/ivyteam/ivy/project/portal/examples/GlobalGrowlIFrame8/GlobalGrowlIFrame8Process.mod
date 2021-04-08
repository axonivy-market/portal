[Ivy]
178A032DA86514D9 7.5.0 #module
>Proto >Proto Collection #zClass
Gs0 GlobalGrowlIFrame8Process Big #zClass
Gs0 RD #cInfo
Gs0 #process
Gs0 @AnnotationInP-0n ai ai #zField
Gs0 @TextInP .type .type #zField
Gs0 @TextInP .processKind .processKind #zField
Gs0 @TextInP .xml .xml #zField
Gs0 @TextInP .responsibility .responsibility #zField
Gs0 @UdInit f0 '' #zField
Gs0 @UdProcessEnd f1 '' #zField
Gs0 @PushWFArc f2 '' #zField
Gs0 @UdEvent f3 '' #zField
Gs0 @UdExitEnd f4 '' #zField
Gs0 @GridStep f13 '' #zField
Gs0 @UdProcessEnd f12 '' #zField
Gs0 @UdEvent f9 '' #zField
Gs0 @GridStep f11 '' #zField
Gs0 @GridStep f14 '' #zField
Gs0 @PushWFArc f16 '' #zField
Gs0 @PushWFArc f17 '' #zField
Gs0 @PushWFArc f20 '' #zField
Gs0 @PushWFArc f22 '' #zField
Gs0 @GridStep f10 '' #zField
Gs0 @PushWFArc f19 '' #zField
Gs0 @PushWFArc f5 '' #zField
>Proto Gs0 Gs0 GlobalGrowlIFrame8Process #zField
Gs0 f0 guid 178A032DABBB1F4E #txt
Gs0 f0 method start(Boolean) #txt
Gs0 f0 inParameterDecl '<Boolean isCustomized> param;' #txt
Gs0 f0 inParameterMapAction 'out.isCustomized=param.isCustomized;
' #txt
Gs0 f0 outParameterDecl '<> result;' #txt
Gs0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start()</name>
    </language>
</elementInfo>
' #txt
Gs0 f0 83 51 26 26 -16 15 #rect
Gs0 f0 @|UdInitIcon #fIcon
Gs0 f1 211 51 26 26 0 12 #rect
Gs0 f1 @|UdProcessEndIcon #fIcon
Gs0 f2 109 64 211 64 #arcP
Gs0 f3 guid 178A032DAD140BA9 #txt
Gs0 f3 actionTable 'out=in;
' #txt
Gs0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>close</name>
    </language>
</elementInfo>
' #txt
Gs0 f3 83 115 26 26 -15 15 #rect
Gs0 f3 @|UdEventIcon #fIcon
Gs0 f4 339 115 26 26 0 12 #rect
Gs0 f4 @|UdExitEndIcon #fIcon
Gs0 f13 actionTable 'out=in;
' #txt
Gs0 f13 actionCode ivy.task.reset(); #txt
Gs0 f13 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>reset task</name>
    </language>
</elementInfo>
' #txt
Gs0 f13 360 170 112 44 -25 -8 #rect
Gs0 f13 @|StepIcon #fIcon
Gs0 f12 755 179 26 26 0 12 #rect
Gs0 f12 @|UdProcessEndIcon #fIcon
Gs0 f9 guid 178A03EE6D310720 #txt
Gs0 f9 actionTable 'out=in;
' #txt
Gs0 f9 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>cancel</name>
    </language>
</elementInfo>
' #txt
Gs0 f9 83 179 26 26 -14 15 #rect
Gs0 f9 @|UdEventIcon #fIcon
Gs0 f11 actionTable 'out=in;
' #txt
Gs0 f11 actionCode 'import ch.ivy.addon.portal.generic.navigation.PortalNavigatorInFrame;

PortalNavigatorInFrame navigator = new PortalNavigatorInFrame();
navigator.navigateToPortalEndPage();
' #txt
Gs0 f11 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>back to task list&#13;
iframe logic</name>
    </language>
</elementInfo>
' #txt
Gs0 f11 544 170 128 44 -40 -16 #rect
Gs0 f11 @|StepIcon #fIcon
Gs0 f14 actionTable 'out=in;
' #txt
Gs0 f14 actionCode 'import ch.ivy.addon.portalkit.publicapi.PortalGlobalGrowInIFrameAPI;
if (in.isCustomized) {
	PortalGlobalGrowInIFrameAPI api = new PortalGlobalGrowInIFrameAPI();
	api.displayCustomizedMessage("Task is cancelled");
}' #txt
Gs0 f14 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Display growl</name>
    </language>
</elementInfo>
' #txt
Gs0 f14 168 170 112 44 -35 -8 #rect
Gs0 f14 @|StepIcon #fIcon
Gs0 f16 672 192 755 192 #arcP
Gs0 f17 472 192 544 192 #arcP
Gs0 f20 280 192 360 192 #arcP
Gs0 f22 109 192 168 192 #arcP
Gs0 f10 actionTable 'out=in;
' #txt
Gs0 f10 actionCode 'import ch.ivy.addon.portalkit.publicapi.PortalGlobalGrowInIFrameAPI;
if (in.isCustomized) {
	PortalGlobalGrowInIFrameAPI api = new PortalGlobalGrowInIFrameAPI();
	api.displayCustomizedMessage("Task is finished");
}' #txt
Gs0 f10 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Display growl</name>
    </language>
</elementInfo>
' #txt
Gs0 f10 168 106 112 44 -35 -8 #rect
Gs0 f10 @|StepIcon #fIcon
Gs0 f19 109 128 168 128 #arcP
Gs0 f5 280 128 339 128 #arcP
>Proto Gs0 .type ch.ivyteam.ivy.project.portal.examples.GlobalGrowlIFrame8.GlobalGrowlIFrame8Data #txt
>Proto Gs0 .processKind HTML_DIALOG #txt
>Proto Gs0 -8 -8 16 16 16 26 #rect
>Proto Gs0 '' #fIcon
Gs0 f0 mainOut f2 tail #connect
Gs0 f2 head f1 mainIn #connect
Gs0 f9 mainOut f22 tail #connect
Gs0 f22 head f14 mainIn #connect
Gs0 f11 mainOut f16 tail #connect
Gs0 f16 head f12 mainIn #connect
Gs0 f14 mainOut f20 tail #connect
Gs0 f20 head f13 mainIn #connect
Gs0 f13 mainOut f17 tail #connect
Gs0 f17 head f11 mainIn #connect
Gs0 f3 mainOut f19 tail #connect
Gs0 f19 head f10 mainIn #connect
Gs0 f10 mainOut f5 tail #connect
Gs0 f5 head f4 mainIn #connect
