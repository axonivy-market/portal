[Ivy]
17FABECA599F49AF 9.4.6 #module
>Proto >Proto Collection #zClass
Ps0 ProcessViewerProcess Big #zClass
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
Ps0 @UdEvent f3 '' #zField
Ps0 @UdExitEnd f4 '' #zField
Ps0 @PushWFArc f5 '' #zField
Ps0 @GridStep f6 '' #zField
Ps0 @PushWFArc f7 '' #zField
Ps0 @PushWFArc f2 '' #zField
Ps0 @UdMethod f8 '' #zField
Ps0 @UdProcessEnd f9 '' #zField
Ps0 @GridStep f10 '' #zField
Ps0 @PushWFArc f11 '' #zField
Ps0 @PushWFArc f12 '' #zField
>Proto Ps0 Ps0 ProcessViewerProcess #zField
Ps0 f0 guid 17FABECA5A299E70 #txt
Ps0 f0 method start(Long,String) #txt
Ps0 f0 inParameterDecl '<Long caseId,String processId> param;' #txt
Ps0 f0 inParameterMapAction 'out.caseId=param.caseId;
out.processId=param.processId;
' #txt
Ps0 f0 outParameterDecl '<> result;' #txt
Ps0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start(Long,String)</name>
    </language>
</elementInfo>
' #txt
Ps0 f0 83 51 26 26 -35 15 #rect
Ps0 f1 339 51 26 26 0 12 #rect
Ps0 f3 guid 17FABECA5A9CBAA5 #txt
Ps0 f3 actionTable 'out=in;
' #txt
Ps0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>close</name>
    </language>
</elementInfo>
' #txt
Ps0 f3 83 243 26 26 -15 15 #rect
Ps0 f4 211 243 26 26 0 12 #rect
Ps0 f5 109 256 211 256 #arcP
Ps0 f6 actionTable 'out=in;
' #txt
Ps0 f6 actionCode 'import ch.ivyteam.ivy.workflow.start.IWebStartable;
import org.apache.commons.lang3.StringUtils;
import ch.ivy.addon.portalkit.util.ProcessViewerUtils;
import ch.ivy.addon.portalkit.util.CaseUtils;

in.isError = false;
in.selectedCase = CaseUtils.findCase(in.caseId);
IWebStartable webStartable = ProcessViewerUtils.findWebStartable(in.caseId, in.processId);
if (webStartable == null) {
	// Show error
	in.isError = true;
}
in.webStartable = webStartable;' #txt
Ps0 f6 security system #txt
Ps0 f6 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Init ProcessViewer</name>
    </language>
</elementInfo>
' #txt
Ps0 f6 168 42 112 44 -51 -8 #rect
Ps0 f7 109 64 168 64 #arcP
Ps0 f2 color default #txt
Ps0 f2 280 64 339 64 #arcP
Ps0 f8 guid 18027A3E37E17363 #txt
Ps0 f8 method loadProcessDetails() #txt
Ps0 f8 inParameterDecl '<> param;' #txt
Ps0 f8 outParameterDecl '<> result;' #txt
Ps0 f8 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>loadProcessDetails()</name>
    </language>
</elementInfo>
' #txt
Ps0 f8 83 147 26 26 -43 14 #rect
Ps0 f9 339 147 26 26 0 12 #rect
Ps0 f10 actionTable 'out=in;
' #txt
Ps0 f10 actionCode 'in.activator = in.webStartable.getActivator();
if (in.activator != null) {
	in.activatorName = in.activator.getDisplayName();
}

if (in.webStartable.getCategory() != null) {
	in.category = in.webStartable.getCategory().getName();
}
' #txt
Ps0 f10 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Combile process info</name>
    </language>
</elementInfo>
' #txt
Ps0 f10 168 138 128 44 -59 -8 #rect
Ps0 f11 109 160 168 160 #arcP
Ps0 f12 296 160 339 160 #arcP
>Proto Ps0 .type ch.ivy.addon.portal.generic.ProcessViewer.ProcessViewerData #txt
>Proto Ps0 .processKind HTML_DIALOG #txt
>Proto Ps0 .colors 'default=;
' #txt
>Proto Ps0 -8 -8 16 16 16 26 #rect
Ps0 f3 mainOut f5 tail #connect
Ps0 f5 head f4 mainIn #connect
Ps0 f0 mainOut f7 tail #connect
Ps0 f7 head f6 mainIn #connect
Ps0 f6 mainOut f2 tail #connect
Ps0 f2 head f1 mainIn #connect
Ps0 f8 mainOut f11 tail #connect
Ps0 f11 head f10 mainIn #connect
Ps0 f10 mainOut f12 tail #connect
Ps0 f12 head f9 mainIn #connect
