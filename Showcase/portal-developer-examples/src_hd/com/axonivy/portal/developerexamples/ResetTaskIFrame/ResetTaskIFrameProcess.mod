[Ivy]
17C597F3BE08B8C1 9.3.0 #module
>Proto >Proto Collection #zClass
Rs0 ResetTaskIFrameProcess Big #zClass
Rs0 RD #cInfo
Rs0 #process
Rs0 @AnnotationInP-0n ai ai #zField
Rs0 @TextInP .type .type #zField
Rs0 @TextInP .processKind .processKind #zField
Rs0 @TextInP .xml .xml #zField
Rs0 @TextInP .responsibility .responsibility #zField
Rs0 @UdInit f0 '' #zField
Rs0 @UdProcessEnd f1 '' #zField
Rs0 @PushWFArc f2 '' #zField
Rs0 @UdEvent f3 '' #zField
Rs0 @UdExitEnd f4 '' #zField
Rs0 @PushWFArc f5 '' #zField
Rs0 @UdEvent f6 '' #zField
Rs0 @GridStep f7 '' #zField
Rs0 @PushWFArc f8 '' #zField
Rs0 @UdEvent f9 '' #zField
Rs0 @GridStep f10 '' #zField
Rs0 @PushWFArc f11 '' #zField
Rs0 @UdEvent f12 '' #zField
Rs0 @GridStep f13 '' #zField
Rs0 @PushWFArc f14 '' #zField
>Proto Rs0 Rs0 ResetTaskIFrameProcess #zField
Rs0 f0 guid 17C597F3BEA36134 #txt
Rs0 f0 method start() #txt
Rs0 f0 inParameterDecl '<> param;' #txt
Rs0 f0 outParameterDecl '<> result;' #txt
Rs0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start()</name>
    </language>
</elementInfo>
' #txt
Rs0 f0 83 51 26 26 -16 15 #rect
Rs0 f1 211 51 26 26 0 12 #rect
Rs0 f2 109 64 211 64 #arcP
Rs0 f3 guid 17C597F3BF1F5BDA #txt
Rs0 f3 actionTable 'out=in;
' #txt
Rs0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>proceed</name>
    </language>
</elementInfo>
' #txt
Rs0 f3 83 147 26 26 -15 15 #rect
Rs0 f4 211 147 26 26 0 12 #rect
Rs0 f5 109 160 211 160 #arcP
Rs0 f6 guid 17C597FABC0AD89C #txt
Rs0 f6 actionTable 'out=in;
' #txt
Rs0 f6 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>cancelToHome</name>
    </language>
</elementInfo>
' #txt
Rs0 f6 83 243 26 26 -14 15 #rect
Rs0 f7 actionTable 'out=in;
' #txt
Rs0 f7 actionCode 'import ch.ivy.addon.portalkit.publicapi.PortalNavigatorInFrameAPI;
ivy.task.reset();
PortalNavigatorInFrameAPI.navigateToUrl(ivy.html.applicationHomeRef());' #txt
Rs0 f7 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Reset task&#13;
Back to homepage</name>
    </language>
</elementInfo>
' #txt
Rs0 f7 184 234 128 44 -45 -16 #rect
Rs0 f8 109 256 184 256 #arcP
Rs0 f9 guid 17C597FC9FEB64A4 #txt
Rs0 f9 actionTable 'out=in;
' #txt
Rs0 f9 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>cancelToEndpage</name>
    </language>
</elementInfo>
' #txt
Rs0 f9 83 339 26 26 -14 15 #rect
Rs0 f10 actionTable 'out=in;
' #txt
Rs0 f10 actionCode 'import ch.ivy.addon.portalkit.publicapi.PortalNavigatorInFrameAPI;
ivy.task.reset();
PortalNavigatorInFrameAPI.navigateToPortalEndPage();' #txt
Rs0 f10 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Reset task&#13;
Back to previous page</name>
    </language>
</elementInfo>
' #txt
Rs0 f10 176 330 144 44 -54 -16 #rect
Rs0 f11 109 352 176 352 #arcP
Rs0 f12 guid 17C597FCAD4E5D9D #txt
Rs0 f12 actionTable 'out=in;
' #txt
Rs0 f12 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>cancelToUrl</name>
    </language>
</elementInfo>
' #txt
Rs0 f12 83 435 26 26 -14 15 #rect
Rs0 f13 actionTable 'out=in;
' #txt
Rs0 f13 actionCode 'import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import ch.ivy.addon.portalkit.publicapi.PortalNavigatorInFrameAPI;
ivy.task.reset();

String createInvestmentURL = "/pro/portal-developer-examples/16E5DB746865BCEC/CreateInvestment.ivp?embedInFrame";
ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
String urlPattern = "%s://%s:%d%s%s";
String url = String.format(urlPattern, context.getRequestScheme(), context.getRequestServerName(), context.getRequestServerPort(), context.getApplicationContextPath(), createInvestmentURL);
ivy.log.info("url ne {0}", url);
PortalNavigatorInFrameAPI.navigateToUrl(url);' #txt
Rs0 f13 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Reset task&#13;
Go to CreateInvestment example</name>
    </language>
</elementInfo>
' #txt
Rs0 f13 176 426 208 44 -84 -16 #rect
Rs0 f14 109 448 176 448 #arcP
>Proto Rs0 .type com.axonivy.portal.developerexamples.ResetTaskIFrame.ResetTaskIFrameData #txt
>Proto Rs0 .processKind HTML_DIALOG #txt
>Proto Rs0 -8 -8 16 16 16 26 #rect
Rs0 f0 mainOut f2 tail #connect
Rs0 f2 head f1 mainIn #connect
Rs0 f3 mainOut f5 tail #connect
Rs0 f5 head f4 mainIn #connect
Rs0 f6 mainOut f8 tail #connect
Rs0 f8 head f7 mainIn #connect
Rs0 f9 mainOut f11 tail #connect
Rs0 f11 head f10 mainIn #connect
Rs0 f12 mainOut f14 tail #connect
Rs0 f14 head f13 mainIn #connect
