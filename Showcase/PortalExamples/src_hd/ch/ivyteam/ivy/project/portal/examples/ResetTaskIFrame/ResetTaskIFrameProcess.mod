[Ivy]
17C550AC89F4BA08 7.5.0 #module
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
Rs0 @GridStep f9 '' #zField
Rs0 @PushWFArc f10 '' #zField
Rs0 @UdEvent f11 '' #zField
Rs0 @UdEvent f12 '' #zField
Rs0 @GridStep f19 '' #zField
Rs0 @PushWFArc f20 '' #zField
Rs0 @GridStep f21 '' #zField
Rs0 @PushWFArc f22 '' #zField
>Proto Rs0 Rs0 ResetTaskIFrameProcess #zField
Rs0 f0 guid 17C550AC8AA55EB1 #txt
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
Rs0 f0 @|UdInitIcon #fIcon
Rs0 f1 211 51 26 26 0 12 #rect
Rs0 f1 @|UdProcessEndIcon #fIcon
Rs0 f2 109 64 211 64 #arcP
Rs0 f3 guid 17C550AC8B89EFA0 #txt
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
Rs0 f3 @|UdEventIcon #fIcon
Rs0 f4 211 147 26 26 0 12 #rect
Rs0 f4 @|UdExitEndIcon #fIcon
Rs0 f5 109 160 211 160 #arcP
Rs0 f6 guid 17C55114086E2C4E #txt
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
Rs0 f6 @|UdEventIcon #fIcon
Rs0 f9 actionTable 'out=in;
' #txt
Rs0 f9 actionCode 'import ch.ivy.addon.portalkit.publicapi.PortalNavigatorInFrameAPI;
ivy.task.reset();
PortalNavigatorInFrameAPI.navigateToPortalHome();' #txt
Rs0 f9 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Reset task&#13;
Back to homepage</name>
    </language>
</elementInfo>
' #txt
Rs0 f9 192 234 128 44 -45 -16 #rect
Rs0 f9 @|StepIcon #fIcon
Rs0 f10 109 256 192 256 #arcP
Rs0 f11 guid 17C552CF82CF0788 #txt
Rs0 f11 actionTable 'out=in;
' #txt
Rs0 f11 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>cancelToEndpage</name>
    </language>
</elementInfo>
' #txt
Rs0 f11 83 339 26 26 -14 15 #rect
Rs0 f11 @|UdEventIcon #fIcon
Rs0 f12 guid 17C552D0297BB4FA #txt
Rs0 f12 actionTable 'out=in;
' #txt
Rs0 f12 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>cancelToUrl</name>
    </language>
</elementInfo>
' #txt
Rs0 f12 83 435 26 26 -18 15 #rect
Rs0 f12 @|UdEventIcon #fIcon
Rs0 f19 actionTable 'out=in;
' #txt
Rs0 f19 actionCode 'import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import ch.ivy.addon.portalkit.publicapi.PortalNavigatorInFrameAPI;
ivy.task.reset();

String createInvestmentURL = "/pro/designer/PortalExamples/16E5DB746865BCEC/CreateInvestment.ivp?embedInFrame";
ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
String urlPattern = "%s://%s:%d%s%s";
String url = String.format(urlPattern, context.getRequestScheme(), context.getRequestServerName(), context.getRequestServerPort(), context.getApplicationContextPath(), createInvestmentURL);

PortalNavigatorInFrameAPI.navigateToUrl(url);' #txt
Rs0 f19 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Reset task&#13;
Go to CreateInvestment example</name>
    </language>
</elementInfo>
' #txt
Rs0 f19 192 426 208 44 -84 -16 #rect
Rs0 f19 @|StepIcon #fIcon
Rs0 f20 109 448 192 448 #arcP
Rs0 f21 actionTable 'out=in;
' #txt
Rs0 f21 actionCode 'import ch.ivy.addon.portalkit.publicapi.PortalNavigatorInFrameAPI;
ivy.task.reset();
PortalNavigatorInFrameAPI.navigateToPortalEndPage();' #txt
Rs0 f21 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Reset task&#13;
Back to previous page</name>
    </language>
</elementInfo>
' #txt
Rs0 f21 184 330 144 44 -54 -16 #rect
Rs0 f21 @|StepIcon #fIcon
Rs0 f22 109 352 184 352 #arcP
>Proto Rs0 .type ch.ivyteam.ivy.project.portal.examples.ResetTaskIFrame.ResetTaskIFrameData #txt
>Proto Rs0 .processKind HTML_DIALOG #txt
>Proto Rs0 -8 -8 16 16 16 26 #rect
>Proto Rs0 '' #fIcon
Rs0 f0 mainOut f2 tail #connect
Rs0 f2 head f1 mainIn #connect
Rs0 f3 mainOut f5 tail #connect
Rs0 f5 head f4 mainIn #connect
Rs0 f6 mainOut f10 tail #connect
Rs0 f10 head f9 mainIn #connect
Rs0 f12 mainOut f20 tail #connect
Rs0 f20 head f19 mainIn #connect
Rs0 f11 mainOut f22 tail #connect
Rs0 f22 head f21 mainIn #connect
