[Ivy]
178A0F7DD8A74A94 9.3.1 #module
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
Gs0 @GridStep f13 '' #zField
Gs0 @UdEvent f7 '' #zField
Gs0 @GridStep f9 '' #zField
Gs0 @GridStep f6 '' #zField
Gs0 @UdEvent f8 '' #zField
Gs0 @GridStep f11 '' #zField
Gs0 @UdProcessEnd f12 '' #zField
Gs0 @UdExitEnd f10 '' #zField
Gs0 @PushWFArc f14 '' #zField
Gs0 @PushWFArc f15 '' #zField
Gs0 @PushWFArc f16 '' #zField
Gs0 @PushWFArc f17 '' #zField
Gs0 @PushWFArc f18 '' #zField
Gs0 @PushWFArc f19 '' #zField
>Proto Gs0 Gs0 GlobalGrowlIFrame8Process #zField
Gs0 f0 guid 178A0F7DDBA91996 #txt
Gs0 f0 method start(Boolean) #txt
Gs0 f0 inParameterDecl '<Boolean isCustomized> param;' #txt
Gs0 f0 inParameterMapAction 'out.isCustomized=param.isCustomized;
' #txt
Gs0 f0 outParameterDecl '<> result;' #txt
Gs0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start(Boolean)</name>
    </language>
</elementInfo>
' #txt
Gs0 f0 83 51 26 26 -16 15 #rect
Gs0 f1 211 51 26 26 0 12 #rect
Gs0 f2 109 64 211 64 #arcP
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
Gs0 f13 376 170 112 44 -25 -8 #rect
Gs0 f7 guid 178A0FAB70F306AE #txt
Gs0 f7 actionTable 'out=in;
' #txt
Gs0 f7 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>close</name>
    </language>
</elementInfo>
' #txt
Gs0 f7 83 115 26 26 -14 15 #rect
Gs0 f9 actionTable 'out=in;
' #txt
Gs0 f9 actionCode 'import ch.ivy.addon.portalkit.publicapi.PortalGlobalGrowInIFrameAPI;
if (in.isCustomized) {
	PortalGlobalGrowInIFrameAPI api = new PortalGlobalGrowInIFrameAPI();
	api.displayCustomizedMessage("Task is cancelled");
}' #txt
Gs0 f9 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Display growl</name>
    </language>
</elementInfo>
' #txt
Gs0 f9 184 170 112 44 -35 -8 #rect
Gs0 f6 actionTable 'out=in;
' #txt
Gs0 f6 actionCode 'import ch.ivy.addon.portalkit.publicapi.PortalGlobalGrowInIFrameAPI;
if (in.isCustomized) {
	PortalGlobalGrowInIFrameAPI api = new PortalGlobalGrowInIFrameAPI();
	api.displayCustomizedMessage("Task is finished");
}' #txt
Gs0 f6 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Display growl</name>
    </language>
</elementInfo>
' #txt
Gs0 f6 184 106 112 44 -35 -8 #rect
Gs0 f8 guid 178A0FAB70F5A1A7 #txt
Gs0 f8 actionTable 'out=in;
' #txt
Gs0 f8 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>cancel</name>
    </language>
</elementInfo>
' #txt
Gs0 f8 83 179 26 26 -14 15 #rect
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
Gs0 f11 576 170 128 44 -40 -16 #rect
Gs0 f12 787 179 26 26 0 12 #rect
Gs0 f10 371 115 26 26 0 12 #rect
Gs0 f14 296 128 371 128 #arcP
Gs0 f15 109 192 184 192 #arcP
Gs0 f16 704 192 787 192 #arcP
Gs0 f17 109 128 184 128 #arcP
Gs0 f18 296 192 376 192 #arcP
Gs0 f19 488 192 576 192 #arcP
>Proto Gs0 .type com.axonivy.portal.developerexamples.showcase.GlobalGrowlIFrame8.GlobalGrowlIFrame8Data #txt
>Proto Gs0 .processKind HTML_DIALOG #txt
>Proto Gs0 -8 -8 16 16 16 26 #rect
Gs0 f0 mainOut f2 tail #connect
Gs0 f2 head f1 mainIn #connect
Gs0 f8 mainOut f15 tail #connect
Gs0 f15 head f9 mainIn #connect
Gs0 f11 mainOut f16 tail #connect
Gs0 f16 head f12 mainIn #connect
Gs0 f7 mainOut f17 tail #connect
Gs0 f17 head f6 mainIn #connect
Gs0 f6 mainOut f14 tail #connect
Gs0 f14 head f10 mainIn #connect
Gs0 f9 mainOut f18 tail #connect
Gs0 f18 head f13 mainIn #connect
Gs0 f13 mainOut f19 tail #connect
Gs0 f19 head f11 mainIn #connect
