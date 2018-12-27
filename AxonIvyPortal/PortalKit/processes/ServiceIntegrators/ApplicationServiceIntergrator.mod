[Ivy]
154F0F14C9113E05 3.20 #module
>Proto >Proto Collection #zClass
Ar0 ApplicationServiceIntergrator Big #zClass
Ar0 B #cInfo
Ar0 #process
Ar0 @TextInP .resExport .resExport #zField
Ar0 @TextInP .type .type #zField
Ar0 @TextInP .processKind .processKind #zField
Ar0 @AnnotationInP-0n ai ai #zField
Ar0 @MessageFlowInP-0n messageIn messageIn #zField
Ar0 @MessageFlowOutP-0n messageOut messageOut #zField
Ar0 @TextInP .xml .xml #zField
Ar0 @TextInP .responsibility .responsibility #zField
Ar0 @WSElement f17 '' #zField
Ar0 @Alternative f15 '' #zField
Ar0 @WSElement f9 '' #zField
Ar0 @EndSub f16 '' #zField
Ar0 @PushWFArc f19 '' #zField
Ar0 @PushWFArc f20 '' #zField
Ar0 @PushWFArc f22 '' #zField
Ar0 @StartSub f0 '' #zField
Ar0 @StartSub f2 '' #zField
Ar0 @WSElement f7 '' #zField
Ar0 @Alternative f8 '' #zField
Ar0 @EndSub f10 '' #zField
Ar0 @WSElement f11 '' #zField
Ar0 @PushWFArc f13 '' #zField
Ar0 @PushWFArc f14 '' #zField
Ar0 @PushWFArc f23 '' #zField
Ar0 @GridStep f28 '' #zField
Ar0 @PushWFArc f27 '' #zField
Ar0 @ProcessException f30 '' #zField
Ar0 @EndSub f31 '' #zField
Ar0 @GridStep f33 '' #zField
Ar0 @PushWFArc f34 '' #zField
Ar0 @PushWFArc f32 '' #zField
Ar0 @WSElement f24 '' #zField
Ar0 @PushWFArc f25 '' #zField
Ar0 @PushWFArc f26 '' #zField
Ar0 @PushWFArc f21 '' #zField
Ar0 @PushWFArc f1 '' #zField
Ar0 @WSElement f3 '' #zField
Ar0 @PushWFArc f4 '' #zField
Ar0 @PushWFArc f5 '' #zField
Ar0 @PushWFArc f6 '' #zField
Ar0 @PushWFArc f35 '' #zField
>Proto Ar0 Ar0 ApplicationServiceIntergrator #zField
Ar0 f17 type ch.ivy.add.portalkit.service.integrators.ApplicationServiceIntergratorData #txt
Ar0 f17 actionDecl 'ch.ivy.add.portalkit.service.integrators.ApplicationServiceIntergratorData out;
' #txt
Ar0 f17 actionTable 'out=in;
out.applications=wsResponse.getAllApplicationsResponse.result.applications;
out.errors=wsResponse.getAllApplicationsResponse.result.errors;
' #txt
Ar0 f17 cache '{/cache true /invalidation false /scope 2 /groupname "ch.ivy.addon.portalkit.persistence.variable.PortalCacheConstants.GET_APPLICATIONS_WS_CACHE_GROUP_NAME"/lifetime_group "ch.ivy.addon.portalkit.persistence.variable.PortalCacheConstants.GET_APPLICATIONS_WS_CACHE_LIFETIME"/invalidation_time_group ""/identifier "\"getAllApplications\""/lifetime_entry "0"/invalidation_time_entry ""}' #txt
Ar0 f17 timeout 0 #txt
Ar0 f17 beanConfig '"KEY_PASSWORD=
KEY_AXIS_PORTNAME=ApplicationServicePort
KEY_WEBSERVICECONFIG_ID=154F091E0FE17F3C
KEY_DOMAIN=
KEY_REQUEST_PARAMETER_MAPPINGS_OPTIONS_AUTO_INITIALIZE_FIRST_LEVEL_FIELDS=true
KEY_REQUEST_PARAMETER_MAPPINGS_OPTIONS_MAP_NULL_VALUES=false
KEY_USERNAME=
KEY_OPERATION=getAllApplications
KEY_AUTHENTICATION_KIND=0
KEY_HOST=
KEY_USE_AUTHENTICATION=false
KEY_AXIS_CSL_PARAMETER_DATA="' #txt
Ar0 f17 exceptionHandler 154F0F14C9113E05-f30-buffer #txt
Ar0 f17 returningObjectList '[wsResponse]' #txt
Ar0 f17 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Call ApplicationService
with default settings</name>
        <nameStyle>45,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ar0 f17 496 298 160 44 -59 -16 #rect
Ar0 f17 @|WebServiceIcon #fIcon
Ar0 f15 type ch.ivy.add.portalkit.service.integrators.ApplicationServiceIntergratorData #txt
Ar0 f15 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Authentication Type?</name>
        <nameStyle>20,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ar0 f15 338 178 28 28 8 -28 #rect
Ar0 f15 @|AlternativeIcon #fIcon
Ar0 f9 type ch.ivy.add.portalkit.service.integrators.ApplicationServiceIntergratorData #txt
Ar0 f9 actionDecl 'ch.ivy.add.portalkit.service.integrators.ApplicationServiceIntergratorData out;
' #txt
Ar0 f9 actionTable 'out=in;
out.applications=wsResponse.getAllApplicationsResponse.result.applications;
out.errors=wsResponse.getAllApplicationsResponse.result.errors;
' #txt
Ar0 f9 cache '{/cache true /invalidation false /scope 2 /groupname "ch.ivy.addon.portalkit.persistence.variable.PortalCacheConstants.GET_APPLICATIONS_WS_CACHE_GROUP_NAME"/lifetime_group "ch.ivy.addon.portalkit.persistence.variable.PortalCacheConstants.GET_APPLICATIONS_WS_CACHE_LIFETIME"/invalidation_time_group ""/identifier "\"getAllApplications\""/lifetime_entry "0"/invalidation_time_entry ""}' #txt
Ar0 f9 timeout 0 #txt
Ar0 f9 beanConfig '"KEY_PASSWORD=<%\\=in.server.password%>
KEY_AXIS_PORTNAME=ApplicationServicePort
KEY_WEBSERVICECONFIG_ID=154F091E0FE17F3C
KEY_DOMAIN=<%\\=in.server.domain%>
KEY_REQUEST_PARAMETER_MAPPINGS_OPTIONS_AUTO_INITIALIZE_FIRST_LEVEL_FIELDS=true
KEY_REQUEST_PARAMETER_MAPPINGS_OPTIONS_MAP_NULL_VALUES=false
KEY_USERNAME=<%\\=in.server.username%>
KEY_OPERATION=getAllApplications
KEY_AUTHENTICATION_KIND=4
KEY_HOST=<%\\=in.server.host%>
KEY_USE_AUTHENTICATION=true
KEY_AXIS_CSL_PARAMETER_DATA="' #txt
Ar0 f9 exceptionHandler 154F0F14C9113E05-f30-buffer #txt
Ar0 f9 returningObjectList '[wsResponse]' #txt
Ar0 f9 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Call ApplicationService
with NTLM Override
and Global Variable Settings</name>
        <nameStyle>71,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ar0 f9 264 290 176 60 -70 -24 #rect
Ar0 f9 @|WebServiceIcon #fIcon
Ar0 f16 type ch.ivy.add.portalkit.service.integrators.ApplicationServiceIntergratorData #txt
Ar0 f16 339 435 26 26 14 0 #rect
Ar0 f16 @|EndSubIcon #fIcon
Ar0 f19 expr in #txt
Ar0 f19 outCond 'ch.ivy.addon.portalkit.enums.WSAuthenticationType.NTLM == in.server.wsAuthenticationType' #txt
Ar0 f19 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>NTLM</name>
        <nameStyle>4
</nameStyle>
    </language>
</elementInfo>
' #txt
Ar0 f19 352 206 352 290 #arcP
Ar0 f19 0 0.27380952380952384 -13 0 #arcLabel
Ar0 f20 expr out #txt
Ar0 f20 352 350 352 435 #arcP
Ar0 f22 expr out #txt
Ar0 f22 576 342 365 448 #arcP
Ar0 f22 1 576 448 #addKink
Ar0 f22 0 0.5636261753528126 0 0 #arcLabel
Ar0 f0 inParamDecl '<java.lang.String endpoint,ch.ivy.addon.portalkit.persistence.domain.Server server> param;' #txt
Ar0 f0 inParamTable 'out.endpoint=param.endpoint;
out.server=param.server;
' #txt
Ar0 f0 outParamDecl '<List<ch.ivy.ws.addon.IvyApplication> applications,List<ch.ivy.ws.addon.WsException> errors> result;
' #txt
Ar0 f0 outParamTable 'result.applications=in.applications;
result.errors=in.errors;
' #txt
Ar0 f0 actionDecl 'ch.ivy.add.portalkit.service.integrators.ApplicationServiceIntergratorData out;
' #txt
Ar0 f0 callSignature getAllApplications(String,ch.ivy.addon.portalkit.persistence.domain.Server) #txt
Ar0 f0 type ch.ivy.add.portalkit.service.integrators.ApplicationServiceIntergratorData #txt
Ar0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>getAllApplications(String,Server)</name>
        <nameStyle>33,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ar0 f0 339 51 26 26 14 0 #rect
Ar0 f0 @|StartSubIcon #fIcon
Ar0 f2 inParamDecl '<ch.ivy.addon.portalkit.persistence.domain.Server server,java.lang.String endpoint> param;' #txt
Ar0 f2 inParamTable 'out.endpoint=param.endpoint;
out.server=param.server;
' #txt
Ar0 f2 outParamDecl '<List<ch.ivy.ws.addon.IvyApplication> applications,List<ch.ivy.ws.addon.WsException> errors> result;
' #txt
Ar0 f2 outParamTable 'result.applications=in.applications;
result.errors=in.errors;
' #txt
Ar0 f2 actionDecl 'ch.ivy.add.portalkit.service.integrators.ApplicationServiceIntergratorData out;
' #txt
Ar0 f2 callSignature getApplicationsConfiguredOnServer(ch.ivy.addon.portalkit.persistence.domain.Server,String) #txt
Ar0 f2 type ch.ivy.add.portalkit.service.integrators.ApplicationServiceIntergratorData #txt
Ar0 f2 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>getApplicationsByConfiguredServer(Server,String)</name>
        <nameStyle>48,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ar0 f2 1011 51 26 26 14 0 #rect
Ar0 f2 @|StartSubIcon #fIcon
Ar0 f7 type ch.ivy.add.portalkit.service.integrators.ApplicationServiceIntergratorData #txt
Ar0 f7 actionDecl 'ch.ivy.add.portalkit.service.integrators.ApplicationServiceIntergratorData out;
' #txt
Ar0 f7 actionTable 'out=in;
out.applications=wsResponse.getApplicationsByAppNamesResponse.result.applications;
out.errors=wsResponse.getApplicationsByAppNamesResponse.result.errors;
' #txt
Ar0 f7 cache '{/cache true /invalidation false /scope 2 /groupname "ch.ivy.addon.portalkit.persistence.variable.PortalCacheConstants.GET_APPLICATIONS_WS_CACHE_GROUP_NAME"/lifetime_group "ch.ivy.addon.portalkit.persistence.variable.PortalCacheConstants.GET_APPLICATIONS_WS_CACHE_LIFETIME"/invalidation_time_group ""/identifier "\"getApplicationsConfiguredOnServer\""/lifetime_entry "0"/invalidation_time_entry ""}' #txt
Ar0 f7 timeout 0 #txt
Ar0 f7 beanConfig '"KEY_PASSWORD=<%\\=in.server.password%>
KEY_AXIS_PORTNAME=ApplicationServicePort
KEY_WEBSERVICECONFIG_ID=154F091E0FE17F3C
KEY_DOMAIN=<%\\=in.server.domain%>
KEY_REQUEST_PARAMETER_MAPPINGS_OPTIONS_AUTO_INITIALIZE_FIRST_LEVEL_FIELDS=true
KEY_REQUEST_PARAMETER_MAPPINGS_OPTIONS_MAP_NULL_VALUES=false
KEY_USERNAME=<%\\=in.server.username%>
KEY_OPERATION=getApplicationsByAppNames
KEY_AUTHENTICATION_KIND=4
KEY_HOST=<%\\=in.server.host%>
KEY_USE_AUTHENTICATION=true
KEY_AXIS_CSL_PARAMETER_DATA=""arg0.getApplicationsByAppNames.applicationNames__@@__Array<String>__@@__in.applicationNames"""' #txt
Ar0 f7 exceptionHandler 154F0F14C9113E05-f30-buffer #txt
Ar0 f7 returningObjectList '[wsResponse]' #txt
Ar0 f7 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Call ApplicationService
with NTLM Override
and Global Variable Settings</name>
        <nameStyle>71,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ar0 f7 936 282 176 60 -70 -24 #rect
Ar0 f7 @|WebServiceIcon #fIcon
Ar0 f8 type ch.ivy.add.portalkit.service.integrators.ApplicationServiceIntergratorData #txt
Ar0 f8 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Authentication Type?</name>
        <nameStyle>20,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ar0 f8 1010 178 28 28 11 -25 #rect
Ar0 f8 @|AlternativeIcon #fIcon
Ar0 f10 type ch.ivy.add.portalkit.service.integrators.ApplicationServiceIntergratorData #txt
Ar0 f10 1011 435 26 26 14 0 #rect
Ar0 f10 @|EndSubIcon #fIcon
Ar0 f11 type ch.ivy.add.portalkit.service.integrators.ApplicationServiceIntergratorData #txt
Ar0 f11 actionDecl 'ch.ivy.add.portalkit.service.integrators.ApplicationServiceIntergratorData out;
' #txt
Ar0 f11 actionTable 'out=in;
out.applications=wsResponse.getApplicationsByAppNamesResponse.result.applications;
out.errors=wsResponse.getApplicationsByAppNamesResponse.result.errors;
' #txt
Ar0 f11 cache '{/cache true /invalidation false /scope 2 /groupname "ch.ivy.addon.portalkit.persistence.variable.PortalCacheConstants.GET_APPLICATIONS_WS_CACHE_GROUP_NAME"/lifetime_group "ch.ivy.addon.portalkit.persistence.variable.PortalCacheConstants.GET_APPLICATIONS_WS_CACHE_LIFETIME"/invalidation_time_group ""/identifier "\"getApplicationsConfiguredOnServer\""/lifetime_entry "0"/invalidation_time_entry ""}' #txt
Ar0 f11 timeout 0 #txt
Ar0 f11 beanConfig '"KEY_PASSWORD=
KEY_AXIS_PORTNAME=ApplicationServicePort
KEY_WEBSERVICECONFIG_ID=154F091E0FE17F3C
KEY_DOMAIN=
KEY_REQUEST_PARAMETER_MAPPINGS_OPTIONS_AUTO_INITIALIZE_FIRST_LEVEL_FIELDS=true
KEY_REQUEST_PARAMETER_MAPPINGS_OPTIONS_MAP_NULL_VALUES=false
KEY_USERNAME=
KEY_OPERATION=getApplicationsByAppNames
KEY_AUTHENTICATION_KIND=0
KEY_HOST=
KEY_USE_AUTHENTICATION=false
KEY_AXIS_CSL_PARAMETER_DATA=""arg0.getApplicationsByAppNames.applicationNames__@@__Array<String>__@@__in.applicationNames"""' #txt
Ar0 f11 exceptionHandler 154F0F14C9113E05-f30-buffer #txt
Ar0 f11 returningObjectList '[wsResponse]' #txt
Ar0 f11 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Call ApplicationService
with default settings</name>
        <nameStyle>45,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ar0 f11 1200 290 160 44 -59 -16 #rect
Ar0 f11 @|WebServiceIcon #fIcon
Ar0 f13 expr in #txt
Ar0 f13 outCond 'ch.ivy.addon.portalkit.enums.WSAuthenticationType.NTLM == in.server.wsAuthenticationType' #txt
Ar0 f13 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>NTLM</name>
        <nameStyle>4
</nameStyle>
    </language>
</elementInfo>
' #txt
Ar0 f13 1024 206 1024 282 #arcP
Ar0 f13 0 0.27631578947368424 -16 0 #arcLabel
Ar0 f14 expr out #txt
Ar0 f14 1024 342 1024 435 #arcP
Ar0 f23 expr out #txt
Ar0 f23 1280 334 1037 448 #arcP
Ar0 f23 1 1280 448 #addKink
Ar0 f23 0 0.6469516562241339 0 0 #arcLabel
Ar0 f28 actionDecl 'ch.ivy.add.portalkit.service.integrators.ApplicationServiceIntergratorData out;
' #txt
Ar0 f28 actionTable 'out=in;
' #txt
Ar0 f28 actionCode 'import ch.ivy.addon.portalkit.util.SecurityServiceUtils;

in.applicationNames = SecurityServiceUtils.getAppNames(in.server);


' #txt
Ar0 f28 type ch.ivy.add.portalkit.service.integrators.ApplicationServiceIntergratorData #txt
Ar0 f28 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>get configured application names of server </name>
        <nameStyle>43
</nameStyle>
    </language>
</elementInfo>
' #txt
Ar0 f28 1006 116 36 24 20 -2 #rect
Ar0 f28 @|StepIcon #fIcon
Ar0 f27 expr out #txt
Ar0 f27 1024 140 1024 178 #arcP
Ar0 f30 .resExport export #txt
Ar0 f30 actionDecl 'ch.ivy.add.portalkit.service.integrators.ApplicationServiceIntergratorData out;
' #txt
Ar0 f30 actionTable 'out=in;
' #txt
Ar0 f30 type ch.ivy.add.portalkit.service.integrators.ApplicationServiceIntergratorData #txt
Ar0 f30 errorCode ivy:portalkit:webservice:intergrator:application #txt
Ar0 f30 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>ivy:portalkit:webservice:intergrator:application</name>
    </language>
</elementInfo>
' #txt
Ar0 f30 1555 51 26 26 14 0 #rect
Ar0 f30 @|ExceptionIcon #fIcon
Ar0 f31 type ch.ivy.add.portalkit.service.integrators.ApplicationServiceIntergratorData #txt
Ar0 f31 1555 259 26 26 14 0 #rect
Ar0 f31 @|EndSubIcon #fIcon
Ar0 f33 actionDecl 'ch.ivy.add.portalkit.service.integrators.ApplicationServiceIntergratorData out;
' #txt
Ar0 f33 actionTable 'out=in;
' #txt
Ar0 f33 actionCode 'import java.util.Calendar;
import ch.ivy.ws.addon.WsErrorType;
import ch.ivy.ws.addon.WsException;

WsException exception;
exception.errorCode = 11001;
exception.errorType = WsErrorType.WARNING;
Calendar c = Calendar.getInstance();
c.setTime(new java.util.Date());
exception.errorDateTime = c;
exception.userText = ivy.cms.co("/errors/11001/userText", [in.server.name]);
exception.server = in.server.name;
in.errors.add(exception);' #txt
Ar0 f33 type ch.ivy.add.portalkit.service.integrators.ApplicationServiceIntergratorData #txt
Ar0 f33 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Handle WS Exception</name>
        <nameStyle>19
</nameStyle>
    </language>
</elementInfo>
' #txt
Ar0 f33 1496 136 144 48 -53 -8 #rect
Ar0 f33 @|StepIcon #fIcon
Ar0 f34 expr out #txt
Ar0 f34 1568 77 1568 136 #arcP
Ar0 f34 0 0.5007315431205012 0 0 #arcLabel
Ar0 f32 expr out #txt
Ar0 f32 1568 184 1568 259 #arcP
Ar0 f24 type ch.ivy.add.portalkit.service.integrators.ApplicationServiceIntergratorData #txt
Ar0 f24 actionDecl 'ch.ivy.add.portalkit.service.integrators.ApplicationServiceIntergratorData out;
' #txt
Ar0 f24 actionTable 'out=in;
out.applications=wsResponse.getAllApplicationsResponse.result.applications;
out.errors=wsResponse.getAllApplicationsResponse.result.errors;
' #txt
Ar0 f24 cache '{/cache true /invalidation false /scope 2 /groupname "ch.ivy.addon.portalkit.persistence.variable.PortalCacheConstants.GET_APPLICATIONS_WS_CACHE_GROUP_NAME"/lifetime_group "ch.ivy.addon.portalkit.persistence.variable.PortalCacheConstants.GET_APPLICATIONS_WS_CACHE_LIFETIME"/invalidation_time_group ""/identifier "\"getAllApplications\""/lifetime_entry "0"/invalidation_time_entry ""}' #txt
Ar0 f24 timeout 0 #txt
Ar0 f24 beanConfig '"KEY_PASSWORD=<%\\=in.server.password%>
KEY_AXIS_PORTNAME=ApplicationServicePort
KEY_WEBSERVICECONFIG_ID=154F091E0FE17F3C
KEY_DOMAIN=
KEY_REQUEST_PARAMETER_MAPPINGS_OPTIONS_AUTO_INITIALIZE_FIRST_LEVEL_FIELDS=true
KEY_REQUEST_PARAMETER_MAPPINGS_OPTIONS_MAP_NULL_VALUES=false
KEY_USERNAME=<%\\=in.server.username%>
KEY_OPERATION=getAllApplications
KEY_AUTHENTICATION_KIND=1
KEY_HOST=
KEY_USE_AUTHENTICATION=true
KEY_AXIS_CSL_PARAMETER_DATA="' #txt
Ar0 f24 exceptionHandler 154F0F14C9113E05-f30-buffer #txt
Ar0 f24 returningObjectList '[wsResponse]' #txt
Ar0 f24 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Call ApplicationService
with HTTP Basic</name>
        <nameStyle>39,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ar0 f24 48 298 160 44 -59 -16 #rect
Ar0 f24 @|WebServiceIcon #fIcon
Ar0 f25 expr in #txt
Ar0 f25 outCond 'ch.ivy.addon.portalkit.enums.WSAuthenticationType.HTTP_BASIC == in.server.wsAuthenticationType' #txt
Ar0 f25 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>HTTP Basic</name>
        <nameStyle>10
</nameStyle>
    </language>
</elementInfo>
' #txt
Ar0 f25 338 192 128 298 #arcP
Ar0 f25 1 128 192 #addKink
Ar0 f25 0 0.719047619047619 0 -10 #arcLabel
Ar0 f26 expr out #txt
Ar0 f26 128 342 339 448 #arcP
Ar0 f26 1 128 448 #addKink
Ar0 f26 1 0.25011774148469074 0 0 #arcLabel
Ar0 f21 expr in #txt
Ar0 f21 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>None</name>
        <nameStyle>4
</nameStyle>
    </language>
</elementInfo>
' #txt
Ar0 f21 366 192 576 298 #arcP
Ar0 f21 1 576 192 #addKink
Ar0 f21 0 0.7986561412607155 -1 -11 #arcLabel
Ar0 f1 expr out #txt
Ar0 f1 1024 77 1024 116 #arcP
Ar0 f3 type ch.ivy.add.portalkit.service.integrators.ApplicationServiceIntergratorData #txt
Ar0 f3 actionDecl 'ch.ivy.add.portalkit.service.integrators.ApplicationServiceIntergratorData out;
' #txt
Ar0 f3 actionTable 'out=in;
out.applications=wsResponse.getApplicationsByAppNamesResponse.result.applications;
out.errors=wsResponse.getApplicationsByAppNamesResponse.result.errors;
' #txt
Ar0 f3 cache '{/cache true /invalidation false /scope 2 /groupname "ch.ivy.addon.portalkit.persistence.variable.PortalCacheConstants.GET_APPLICATIONS_WS_CACHE_GROUP_NAME"/lifetime_group "ch.ivy.addon.portalkit.persistence.variable.PortalCacheConstants.GET_APPLICATIONS_WS_CACHE_LIFETIME"/invalidation_time_group ""/identifier "\"getApplicationsConfiguredOnServer\""/lifetime_entry "0"/invalidation_time_entry ""}' #txt
Ar0 f3 timeout 0 #txt
Ar0 f3 beanConfig '"KEY_PASSWORD=<%\\=in.server.password%>
KEY_AXIS_PORTNAME=ApplicationServicePort
KEY_WEBSERVICECONFIG_ID=154F091E0FE17F3C
KEY_DOMAIN=
KEY_REQUEST_PARAMETER_MAPPINGS_OPTIONS_AUTO_INITIALIZE_FIRST_LEVEL_FIELDS=true
KEY_REQUEST_PARAMETER_MAPPINGS_OPTIONS_MAP_NULL_VALUES=false
KEY_USERNAME=<%\\=in.server.username%>
KEY_OPERATION=getApplicationsByAppNames
KEY_AUTHENTICATION_KIND=1
KEY_HOST=
KEY_USE_AUTHENTICATION=true
KEY_AXIS_CSL_PARAMETER_DATA=""arg0.getApplicationsByAppNames.applicationNames__@@__Array<String>__@@__in.applicationNames"""' #txt
Ar0 f3 exceptionHandler 154F0F14C9113E05-f30-buffer #txt
Ar0 f3 returningObjectList '[wsResponse]' #txt
Ar0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Call ApplicationService
with HTTP Basic</name>
        <nameStyle>39,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ar0 f3 720 290 160 44 -59 -16 #rect
Ar0 f3 @|WebServiceIcon #fIcon
Ar0 f4 expr in #txt
Ar0 f4 outCond 'ch.ivy.addon.portalkit.enums.WSAuthenticationType.HTTP_BASIC == in.server.wsAuthenticationType' #txt
Ar0 f4 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>HTTP Basic</name>
        <nameStyle>10
</nameStyle>
    </language>
</elementInfo>
' #txt
Ar0 f4 1010 192 800 290 #arcP
Ar0 f4 1 800 192 #addKink
Ar0 f4 0 0.7523809523809524 0 -11 #arcLabel
Ar0 f5 expr out #txt
Ar0 f5 800 334 1011 448 #arcP
Ar0 f5 1 800 448 #addKink
Ar0 f5 1 0.2501592864131127 0 0 #arcLabel
Ar0 f6 expr in #txt
Ar0 f6 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>None</name>
        <nameStyle>4
</nameStyle>
    </language>
</elementInfo>
' #txt
Ar0 f6 1038 192 1280 290 #arcP
Ar0 f6 1 1280 192 #addKink
Ar0 f6 0 0.7107438016528925 0 -12 #arcLabel
Ar0 f35 expr out #txt
Ar0 f35 352 77 352 178 #arcP
>Proto Ar0 .type ch.ivy.add.portalkit.service.integrators.ApplicationServiceIntergratorData #txt
>Proto Ar0 .processKind CALLABLE_SUB #txt
>Proto Ar0 0 0 32 24 18 0 #rect
>Proto Ar0 @|BIcon #fIcon
Ar0 f15 out f19 tail #connect
Ar0 f19 head f9 mainIn #connect
Ar0 f9 mainOut f20 tail #connect
Ar0 f20 head f16 mainIn #connect
Ar0 f17 mainOut f22 tail #connect
Ar0 f22 head f16 mainIn #connect
Ar0 f8 out f13 tail #connect
Ar0 f13 head f7 mainIn #connect
Ar0 f7 mainOut f14 tail #connect
Ar0 f14 head f10 mainIn #connect
Ar0 f11 mainOut f23 tail #connect
Ar0 f23 head f10 mainIn #connect
Ar0 f28 mainOut f27 tail #connect
Ar0 f27 head f8 in #connect
Ar0 f30 mainOut f34 tail #connect
Ar0 f34 head f33 mainIn #connect
Ar0 f33 mainOut f32 tail #connect
Ar0 f32 head f31 mainIn #connect
Ar0 f15 out f25 tail #connect
Ar0 f25 head f24 mainIn #connect
Ar0 f24 mainOut f26 tail #connect
Ar0 f26 head f16 mainIn #connect
Ar0 f15 out f21 tail #connect
Ar0 f21 head f17 mainIn #connect
Ar0 f2 mainOut f1 tail #connect
Ar0 f1 head f28 mainIn #connect
Ar0 f8 out f4 tail #connect
Ar0 f4 head f3 mainIn #connect
Ar0 f3 mainOut f5 tail #connect
Ar0 f5 head f10 mainIn #connect
Ar0 f8 out f6 tail #connect
Ar0 f6 head f11 mainIn #connect
Ar0 f0 mainOut f35 tail #connect
Ar0 f35 head f15 in #connect
