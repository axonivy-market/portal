[Ivy]
1723B30AE7B858C4 7.5.0 #module
>Proto >Proto Collection #zClass
Es0 WelcomePortalDialogProcess Big #zClass
Es0 RD #cInfo
Es0 #process
Es0 @TextInP .type .type #zField
Es0 @TextInP .processKind .processKind #zField
Es0 @AnnotationInP-0n ai ai #zField
Es0 @MessageFlowInP-0n messageIn messageIn #zField
Es0 @MessageFlowOutP-0n messageOut messageOut #zField
Es0 @TextInP .xml .xml #zField
Es0 @TextInP .responsibility .responsibility #zField
Es0 @UdInit f0 '' #zField
Es0 @UdProcessEnd f1 '' #zField
Es0 @GridStep f3 '' #zField
Es0 @UdMethod f5 '' #zField
Es0 @PushWFArc f6 '' #zField
Es0 @PushWFArc f7 '' #zField
Es0 @UdProcessEnd f4 '' #zField
Es0 @PushWFArc f8 '' #zField
>Proto Es0 Es0 WelcomePortalDialogProcess #zField
Es0 f0 guid 15276F281563C3C0 #txt
Es0 f0 method start() #txt
Es0 f0 inParameterDecl '<> param;' #txt
Es0 f0 outParameterDecl '<> result;' #txt
Es0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start()</name>
    </language>
</elementInfo>
' #txt
Es0 f0 85 85 22 22 -19 17 #rect
Es0 f0 @|UdInitIcon #fIcon
Es0 f1 245 85 22 22 14 0 #rect
Es0 f1 @|UdProcessEndIcon #fIcon
Es0 f3 actionTable 'out=in;
' #txt
Es0 f3 actionCode 'import org.apache.commons.lang3.StringUtils;
import ch.ivy.addon.portalkit.enums.PortalLibrary;
import ch.ivyteam.ivy.application.ILibrary;

ILibrary portalLibrary = ivy.wf.getApplication().findReleasedLibrary(PortalLibrary.PORTAL_KIT.getValue());
String portalVersion = portalLibrary.getQualifiedVersion().toString();
String latestPortalGuideLink = "https://developer.axonivy.com/portal/dev/doc";
String portalGuideLink;
if (StringUtils.contains(portalVersion, "SNAPSHOT")) {
	portalGuideLink = latestPortalGuideLink;
} else {
	portalGuideLink = String.format("https://developer.axonivy.com/documentation/portal-guide/", portalVersion);
}

in.content = ivy.cms.co("/ch.ivy.addon.portalkit.ui.jsf/components/guide/welcome/content", [portalGuideLink]);' #txt
Es0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Builde content included guide link</name>
    </language>
</elementInfo>
' #txt
Es0 f3 160 170 176 44 -85 -8 #rect
Es0 f3 @|StepIcon #fIcon
Es0 f5 guid 1727DA86A81C804E #txt
Es0 f5 method buildContent() #txt
Es0 f5 inParameterDecl '<> param;' #txt
Es0 f5 outParameterDecl '<String content> result;' #txt
Es0 f5 outParameterMapAction 'result.content=in.content;
' #txt
Es0 f5 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>buildContent()</name>
    </language>
</elementInfo>
' #txt
Es0 f5 83 179 26 26 -23 15 #rect
Es0 f5 @|UdMethodIcon #fIcon
Es0 f6 109 192 160 192 #arcP
Es0 f7 expr out #txt
Es0 f7 107 96 245 96 #arcP
Es0 f4 403 179 26 26 0 12 #rect
Es0 f4 @|UdProcessEndIcon #fIcon
Es0 f8 336 192 403 192 #arcP
>Proto Es0 .type ch.ivy.addon.portalkit.component.WelcomePortalDialog.WelcomePortalDialogData #txt
>Proto Es0 .processKind HTML_DIALOG #txt
>Proto Es0 -8 -8 16 16 16 26 #rect
>Proto Es0 '' #fIcon
Es0 f5 mainOut f6 tail #connect
Es0 f6 head f3 mainIn #connect
Es0 f0 mainOut f7 tail #connect
Es0 f7 head f1 mainIn #connect
Es0 f3 mainOut f8 tail #connect
Es0 f8 head f4 mainIn #connect
