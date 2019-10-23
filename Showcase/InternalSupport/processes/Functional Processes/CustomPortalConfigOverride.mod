[Ivy]
14C7990BF6917FC5 7.5.0 #module
>Proto >Proto Collection #zClass
Cg0 CustomPortalConfig Big #zClass
Cg0 B #cInfo
Cg0 #process
Cg0 @TextInP .type .type #zField
Cg0 @TextInP .processKind .processKind #zField
Cg0 @AnnotationInP-0n ai ai #zField
Cg0 @MessageFlowInP-0n messageIn messageIn #zField
Cg0 @MessageFlowOutP-0n messageOut messageOut #zField
Cg0 @TextInP .xml .xml #zField
Cg0 @TextInP .responsibility .responsibility #zField
Cg0 @StartSub f0 '' #zField
Cg0 @EndSub f1 '' #zField
Cg0 @GridStep f5 '' #zField
Cg0 @PushWFArc f2 '' #zField
Cg0 @InfoButton f7 '' #zField
Cg0 @AnnotationArc f8 '' #zField
Cg0 @PushWFArc f3 '' #zField
Cg0 @InfoButton f4 '' #zField
Cg0 @AnnotationArc f6 '' #zField
Cg0 @InfoButton f9 '' #zField
Cg0 @AnnotationArc f10 '' #zField
Cg0 @InfoButton f11 '' #zField
>Proto Cg0 Cg0 CustomPortalConfig #zField
Cg0 f0 inParamDecl '<ch.ivy.addon.portal.generic.PortalConfig portalConfig> param;' #txt
Cg0 f0 inParamTable 'out.portalConfig=param.portalConfig;
' #txt
Cg0 f0 outParamDecl '<ch.ivy.addon.portal.generic.PortalConfig portalConfig> result;' #txt
Cg0 f0 outParamTable 'result.portalConfig=in.portalConfig;
' #txt
Cg0 f0 callSignature call(ch.ivy.addon.portal.generic.PortalConfig) #txt
Cg0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>call(PortalConfig)</name>
        <nameStyle>18,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cg0 f0 81 201 30 30 -47 17 #rect
Cg0 f0 @|StartSubIcon #fIcon
Cg0 f1 369 201 30 30 0 15 #rect
Cg0 f1 @|EndSubIcon #fIcon
Cg0 f5 actionTable 'out=in;
' #txt
Cg0 f5 actionCode 'import ch.ivy.addon.portal.generic.CustomPortalLink;


out.portalConfig.customLinkList = new List<ch.ivy.addon.portal.generic.CustomPortalLink>();

out.portalConfig.hideCreateNewMenuItem = false;
out.portalConfig.hideShowAllCaseMenuItem = false;
out.portalConfig.hideDashboardMenuItem = false;
out.portalConfig.hideShowTaskMenuItem = false;
out.portalConfig.hideShowCaseMenuItem = false;

//custome link here
/*
CustomPortalLink link = new CustomPortalLink();
link.displayName = "Custom ASCON Page";
link.url = ivy.html.startref("Start Processes/AsconCustomPages/AnyCustomAsconPage.ivp");
link.icon = "fa fa-twitter";
out.portalConfig.customLinkList.add(link);
*/
CustomPortalLink holiday = new CustomPortalLink();
holiday.displayName = "Holiday Request";
holiday.url = ivy.html.startref("Start Processes/InternalSupportPortalHome/holidayRequest.ivp");
holiday.icon = "fa fa-gittip";
out.portalConfig.customLinkList.add(holiday);

CustomPortalLink publication = new CustomPortalLink();
publication.displayName = "Publication Request";
publication.url = ivy.html.startref("Start Processes/InternalSupportPortalHome/publicactionRequest.ivp");
publication.icon = "fa fa-book";
out.portalConfig.customLinkList.add(publication);

CustomPortalLink providedCaseList = new CustomPortalLink();
providedCaseList.displayName = "Provided Case List";
providedCaseList.url = ivy.html.startref("Start Processes/InternalSupportPortalHome/ProvidedCaseList.ivp");
providedCaseList.icon = "fa fa-youtube";
out.portalConfig.customLinkList.add(providedCaseList);

CustomPortalLink link = new CustomPortalLink();
link.displayName = "Link to web.de";
link.url = "http://www.web.de";
link.icon = "fa fa-share-alt";
out.portalConfig.customLinkList.add(link);' #txt
Cg0 f5 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>apply app config</name>
        <nameStyle>16,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cg0 f5 192 194 112 44 -44 -8 #rect
Cg0 f5 @|StepIcon #fIcon
Cg0 f2 expr out #txt
Cg0 f2 304 216 369 216 #arcP
Cg0 f7 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>add application specific
configuration changes here</name>
        <nameStyle>25,7
26,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cg0 f7 208 290 160 44 -75 -16 #rect
Cg0 f7 @|IBIcon #fIcon
Cg0 f8 288 290 248 238 #arcP
Cg0 f3 expr out #txt
Cg0 f3 111 216 192 216 #arcP
Cg0 f4 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>default config object comes in here</name>
        <nameStyle>35,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cg0 f4 48 361 208 30 -95 -8 #rect
Cg0 f4 @|IBIcon #fIcon
Cg0 f6 152 361 91 230 #arcP
Cg0 f9 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>adapted config object goes out here</name>
        <nameStyle>35,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cg0 f9 352 369 208 30 -98 -8 #rect
Cg0 f9 @|IBIcon #fIcon
Cg0 f10 456 369 381 230 #arcP
Cg0 f11 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Override this project in your application portal in order
to apply appplication specifiy configuration.

Important note:
Never call this process to get config object. 
Call &quot;GetPortalConfig&quot; sub process instead.</name>
        <nameStyle>121,7
90,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cg0 f11 64 26 304 108 -145 -48 #rect
Cg0 f11 @|IBIcon #fIcon
Cg0 f11 -1|-1|-65536 #nodeStyle
>Proto Cg0 .type internalPortal.CustomPortalConfigOverrideData #txt
>Proto Cg0 .processKind CALLABLE_SUB #txt
>Proto Cg0 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language/>
</elementInfo>
' #txt
>Proto Cg0 0 0 32 24 18 0 #rect
>Proto Cg0 @|BIcon #fIcon
Cg0 f5 mainOut f2 tail #connect
Cg0 f2 head f1 mainIn #connect
Cg0 f7 ao f8 tail #connect
Cg0 f8 head f5 @CG|ai #connect
Cg0 f0 mainOut f3 tail #connect
Cg0 f3 head f5 mainIn #connect
Cg0 f4 ao f6 tail #connect
Cg0 f6 head f0 @CG|ai #connect
Cg0 f9 ao f10 tail #connect
Cg0 f10 head f1 @CG|ai #connect
