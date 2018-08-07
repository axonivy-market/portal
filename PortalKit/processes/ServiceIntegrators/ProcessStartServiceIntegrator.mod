[Ivy]
[>Created: Fri Mar 25 18:03:48 ICT 2016]
14E8ABD50C85F769 3.18 #module
>Proto >Proto Collection #zClass
Pe0 ProcessStartServiceIntegrator Big #zClass
Pe0 B #cInfo
Pe0 #process
Pe0 @TextInP .resExport .resExport #zField
Pe0 @TextInP .type .type #zField
Pe0 @TextInP .processKind .processKind #zField
Pe0 @AnnotationInP-0n ai ai #zField
Pe0 @TextInP .xml .xml #zField
Pe0 @TextInP .responsibility .responsibility #zField
Pe0 @ProcessException f0 '' #zField
Pe0 @EndSub f1 '' #zField
Pe0 @PushWFArc f2 '' #zField
Pe0 @EndSub f6 '' #zField
Pe0 @WSElement f7 '' #zField
Pe0 @WSElement f8 '' #zField
Pe0 @StartSub f9 '' #zField
Pe0 @Alternative f10 '' #zField
Pe0 @PushWFArc f12 '' #zField
Pe0 @PushWFArc f13 '' #zField
Pe0 @PushWFArc f14 '' #zField
Pe0 @PushWFArc f15 '' #zField
Pe0 @GridStep f18 '' #zField
Pe0 @PushWFArc f19 '' #zField
Pe0 @PushWFArc f11 '' #zField
>Proto Pe0 Pe0 ProcessStartServiceIntegrator #zField
Pe0 f0 .resExport export #txt
Pe0 f0 actionDecl 'ch.ivy.add.portalkit.service.integrators.ProcessStartServiceIntegratorData out;
' #txt
Pe0 f0 actionTable 'out=in;
' #txt
Pe0 f0 actionCode ivy.log.fatal(exception); #txt
Pe0 f0 type ch.ivy.add.portalkit.service.integrators.ProcessStartServiceIntegratorData #txt
Pe0 f0 errorCode unspecified #txt
Pe0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Exception Handler</name>
        <nameStyle>17,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Pe0 f0 691 51 26 26 14 0 #rect
Pe0 f0 @|ExceptionIcon #fIcon
Pe0 f1 type ch.ivy.add.portalkit.service.integrators.ProcessStartServiceIntegratorData #txt
Pe0 f1 691 179 26 26 14 0 #rect
Pe0 f1 @|EndSubIcon #fIcon
Pe0 f2 expr out #txt
Pe0 f2 704 77 704 179 #arcP
Pe0 f6 type ch.ivy.add.portalkit.service.integrators.ProcessStartServiceIntegratorData #txt
Pe0 f6 115 355 26 26 14 0 #rect
Pe0 f6 @|EndSubIcon #fIcon
Pe0 f7 type ch.ivy.add.portalkit.service.integrators.ProcessStartServiceIntegratorData #txt
Pe0 f7 actionDecl 'ch.ivy.add.portalkit.service.integrators.ProcessStartServiceIntegratorData out;
' #txt
Pe0 f7 actionTable 'out=in;
out.errors=wsResponse.findProcessesStartsByCriteriaResponse.result.errors;
out.processStarts=wsResponse.findProcessesStartsByCriteriaResponse.result.processStarts;
' #txt
Pe0 f7 cache '{/cache false /invalidation false /scope 0 /groupname ""/lifetime_group "0"/invalidation_time_group ""/identifier ""/lifetime_entry "0"/invalidation_time_entry ""}' #txt
Pe0 f7 timeout 10 #txt
Pe0 f7 beanConfig '"KEY_PASSWORD=<%\\=ivy.var.PortalWSPassword%>
KEY_AXIS_PORTNAME=ProcessStartServicePort
KEY_WEBSERVICECONFIG_ID=1473A1D4EEBABA93
KEY_DOMAIN=<%\\=ivy.var.PortalWSDomain%>
KEY_USERNAME=<%\\=ivy.var.PortalWSUsername%>
KEY_OPERATION=findProcessesStartsByCriteria
KEY_AUTHENTICATION_KIND=4
KEY_HOST=<%\\=ivy.var.PortalWSHost%>
KEY_USE_AUTHENTICATION=true
KEY_AXIS_CSL_PARAMETER_DATA=""arg0.findProcessesStartsByCriteria.language__@@__String__@@__in.language"",""arg0.findProcessesStartsByCriteria.processSearchCriteria__@@__ch.ivy.ws.addon.ProcessSearchCriteria__@@__in.processSearchCriteria"",""arg0.findProcessesStartsByCriteria.serverUrl__@@__String__@@__in.serverUrl"""' #txt
Pe0 f7 exceptionHandler 14E8ABD50C85F769-f0-buffer #txt
Pe0 f7 timeoutExceptionHandler 14E8ABD50C85F769-f0-buffer #txt
Pe0 f7 returningObjectList '[wsResponse]' #txt
Pe0 f7 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Call ProcessStartService:GetProcessStarts
with NTLM Override
and Global Variable Settings</name>
        <nameStyle>42,7
47,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Pe0 f7 110 260 36 24 20 -2 #rect
Pe0 f7 @|WebServiceIcon #fIcon
Pe0 f8 type ch.ivy.add.portalkit.service.integrators.ProcessStartServiceIntegratorData #txt
Pe0 f8 actionDecl 'ch.ivy.add.portalkit.service.integrators.ProcessStartServiceIntegratorData out;
' #txt
Pe0 f8 actionTable 'out=in;
out.errors=wsResponse.findProcessesStartsByCriteriaResponse.result.errors;
out.processStarts=wsResponse.findProcessesStartsByCriteriaResponse.result.processStarts;
' #txt
Pe0 f8 cache '{/cache false /invalidation false /scope 0 /groupname ""/lifetime_group "0"/invalidation_time_group ""/identifier ""/lifetime_entry "0"/invalidation_time_entry ""}' #txt
Pe0 f8 timeout 10 #txt
Pe0 f8 beanConfig '"KEY_PASSWORD=
KEY_AXIS_PORTNAME=ProcessStartServicePort
KEY_WEBSERVICECONFIG_ID=1473A1D4EEBABA93
KEY_DOMAIN=
KEY_USERNAME=
KEY_OPERATION=findProcessesStartsByCriteria
KEY_AUTHENTICATION_KIND=0
KEY_HOST=
KEY_USE_AUTHENTICATION=false
KEY_AXIS_CSL_PARAMETER_DATA=""arg0.findProcessesStartsByCriteria.language__@@__String__@@__in.language"",""arg0.findProcessesStartsByCriteria.processSearchCriteria__@@__ch.ivy.ws.addon.ProcessSearchCriteria__@@__in.processSearchCriteria"",""arg0.findProcessesStartsByCriteria.serverUrl__@@__String__@@__in.serverUrl"""' #txt
Pe0 f8 exceptionHandler 14E8ABD50C85F769-f0-buffer #txt
Pe0 f8 timeoutExceptionHandler 14E8ABD50C85F769-f0-buffer #txt
Pe0 f8 returningObjectList '[wsResponse]' #txt
Pe0 f8 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Call ProcessStartService:GetProcessStarts
with default settings</name>
        <nameStyle>42,7
21,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Pe0 f8 398 260 36 24 20 -2 #rect
Pe0 f8 @|WebServiceIcon #fIcon
Pe0 f9 inParamDecl '<java.lang.String endpoint,java.lang.String language,ch.ivy.ws.addon.ProcessSearchCriteria processSearchCriteria,ch.ivy.addon.portalkit.persistence.domain.Server server> param;' #txt
Pe0 f9 inParamTable 'out.endpoint=param.endpoint;
out.language=param.language;
out.processSearchCriteria=param.processSearchCriteria;
out.server=param.server;
' #txt
Pe0 f9 outParamDecl '<List<ch.ivy.ws.addon.IvyProcessStart> processStarts,List<ch.ivy.ws.addon.WsException> errors> result;
' #txt
Pe0 f9 outParamTable 'result.processStarts=in.processStarts;
result.errors=in.errors;
' #txt
Pe0 f9 actionDecl 'ch.ivy.add.portalkit.service.integrators.ProcessStartServiceIntegratorData out;
' #txt
Pe0 f9 callSignature findProcessStartsByCriteria(String,String,ch.ivy.ws.addon.ProcessSearchCriteria,ch.ivy.addon.portalkit.persistence.domain.Server) #txt
Pe0 f9 type ch.ivy.add.portalkit.service.integrators.ProcessStartServiceIntegratorData #txt
Pe0 f9 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>findProcessStartsByCriteria(String,ProcessSearchCriteria,Server)</name>
        <nameStyle>64,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Pe0 f9 115 51 26 26 14 0 #rect
Pe0 f9 @|StartSubIcon #fIcon
Pe0 f10 type ch.ivy.add.portalkit.service.integrators.ProcessStartServiceIntegratorData #txt
Pe0 f10 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>User NTLM Authentication?</name>
        <nameStyle>25,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Pe0 f10 114 178 28 28 14 -22 #rect
Pe0 f10 @|AlternativeIcon #fIcon
Pe0 f12 expr in #txt
Pe0 f12 outCond in.server.isNTLMAuthentication #txt
Pe0 f12 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>yes</name>
        <nameStyle>3,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Pe0 f12 128 206 128 260 #arcP
Pe0 f13 expr out #txt
Pe0 f13 128 284 128 355 #arcP
Pe0 f14 expr in #txt
Pe0 f14 141 193 416 260 #arcP
Pe0 f14 1 416 192 #addKink
Pe0 f14 0 0.7302200350148695 0 0 #arcLabel
Pe0 f15 expr out #txt
Pe0 f15 416 284 141 368 #arcP
Pe0 f15 1 416 368 #addKink
Pe0 f15 1 0.33375490208843767 0 0 #arcLabel
Pe0 f18 actionDecl 'ch.ivy.add.portalkit.service.integrators.ProcessStartServiceIntegratorData out;
' #txt
Pe0 f18 actionTable 'out=in;
' #txt
Pe0 f18 actionCode 'import ch.ivy.addon.portalkit.support.UrlDetector;
UrlDetector detector = new UrlDetector();
in.serverUrl = detector.getHost(in.server.path);' #txt
Pe0 f18 type ch.ivy.add.portalkit.service.integrators.ProcessStartServiceIntegratorData #txt
Pe0 f18 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>get server Url from 
portal connector path</name>
        <nameStyle>42,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Pe0 f18 110 116 36 24 20 -19 #rect
Pe0 f18 @|StepIcon #fIcon
Pe0 f19 expr out #txt
Pe0 f19 128 77 128 116 #arcP
Pe0 f11 expr out #txt
Pe0 f11 128 140 128 178 #arcP
>Proto Pe0 .type ch.ivy.add.portalkit.service.integrators.ProcessStartServiceIntegratorData #txt
>Proto Pe0 .processKind CALLABLE_SUB #txt
>Proto Pe0 0 0 32 24 18 0 #rect
>Proto Pe0 @|BIcon #fIcon
Pe0 f0 mainOut f2 tail #connect
Pe0 f2 head f1 mainIn #connect
Pe0 f10 out f12 tail #connect
Pe0 f12 head f7 mainIn #connect
Pe0 f7 mainOut f13 tail #connect
Pe0 f13 head f6 mainIn #connect
Pe0 f10 out f14 tail #connect
Pe0 f14 head f8 mainIn #connect
Pe0 f8 mainOut f15 tail #connect
Pe0 f15 head f6 mainIn #connect
Pe0 f9 mainOut f19 tail #connect
Pe0 f19 head f18 mainIn #connect
Pe0 f18 mainOut f11 tail #connect
Pe0 f11 head f10 in #connect
