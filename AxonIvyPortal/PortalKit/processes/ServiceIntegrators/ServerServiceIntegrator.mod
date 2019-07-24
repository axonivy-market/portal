[Ivy]
155B554F725A24A5 3.20 #module
>Proto >Proto Collection #zClass
Sr0 ServerServiceIntegrator Big #zClass
Sr0 B #cInfo
Sr0 #process
Sr0 @TextInP .resExport .resExport #zField
Sr0 @TextInP .type .type #zField
Sr0 @TextInP .processKind .processKind #zField
Sr0 @AnnotationInP-0n ai ai #zField
Sr0 @MessageFlowInP-0n messageIn messageIn #zField
Sr0 @MessageFlowOutP-0n messageOut messageOut #zField
Sr0 @TextInP .xml .xml #zField
Sr0 @TextInP .responsibility .responsibility #zField
Sr0 @StartSub f0 '' #zField
Sr0 @WSElement f24 '' #zField
Sr0 @Alternative f15 '' #zField
Sr0 @WSElement f9 '' #zField
Sr0 @WSElement f17 '' #zField
Sr0 @EndSub f16 '' #zField
Sr0 @PushWFArc f19 '' #zField
Sr0 @PushWFArc f20 '' #zField
Sr0 @PushWFArc f22 '' #zField
Sr0 @PushWFArc f25 '' #zField
Sr0 @PushWFArc f26 '' #zField
Sr0 @PushWFArc f21 '' #zField
Sr0 @PushWFArc f3 '' #zField
Sr0 @GridStep f33 '' #zField
Sr0 @ProcessException f30 '' #zField
Sr0 @EndSub f31 '' #zField
Sr0 @PushWFArc f34 '' #zField
Sr0 @PushWFArc f32 '' #zField
>Proto Sr0 Sr0 ServerServiceIntegrator #zField
Sr0 f0 inParamDecl '<java.lang.String endpoint,ch.ivy.addon.portalkit.persistence.domain.Server server> param;' #txt
Sr0 f0 inParamTable 'out.endpoint=param.endpoint;
out.server=param.server;
' #txt
Sr0 f0 outParamDecl '<java.lang.String externalPath,List<ch.ivy.ws.addon.WsException> errors> result;
' #txt
Sr0 f0 outParamTable 'result.externalPath=in.externalPath;
result.errors=in.errors;
' #txt
Sr0 f0 actionDecl 'ch.ivy.add.portalkit.service.integrators.ServerServiceIntegratorData out;
' #txt
Sr0 f0 callSignature getExternalHost(String,ch.ivy.addon.portalkit.persistence.domain.Server) #txt
Sr0 f0 type ch.ivy.add.portalkit.service.integrators.ServerServiceIntegratorData #txt
Sr0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>getExternalHost(String,Server)</name>
    </language>
</elementInfo>
' #txt
Sr0 f0 491 99 26 26 14 0 #rect
Sr0 f0 @|StartSubIcon #fIcon
Sr0 f24 type ch.ivy.add.portalkit.service.integrators.ServerServiceIntegratorData #txt
Sr0 f24 actionDecl 'ch.ivy.add.portalkit.service.integrators.ServerServiceIntegratorData out;
' #txt
Sr0 f24 actionTable 'out=in;
out.errors=wsResponse.getExternalHostResponse.result.erros;
out.externalPath=wsResponse.getExternalHostResponse.result.externalHost;
' #txt
Sr0 f24 cache '{/cache false /invalidation false /scope 0 /groupname ""/lifetime_group "0"/invalidation_time_group ""/identifier ""/lifetime_entry "0"/invalidation_time_entry ""}' #txt
Sr0 f24 timeout 60 #txt
Sr0 f24 beanConfig '"KEY_PASSWORD=<%\\=in.server.password%>
KEY_AXIS_PORTNAME=ServerServicePort
KEY_WEBSERVICECONFIG_ID=155B8E8D03D38D19
KEY_DOMAIN=
KEY_REQUEST_PARAMETER_MAPPINGS_OPTIONS_AUTO_INITIALIZE_FIRST_LEVEL_FIELDS=true
KEY_REQUEST_PARAMETER_MAPPINGS_OPTIONS_MAP_NULL_VALUES=false
KEY_USERNAME=<%\\=in.server.username%>
KEY_OPERATION=getExternalHost
KEY_AUTHENTICATION_KIND=1
KEY_HOST=
KEY_USE_AUTHENTICATION=true
KEY_AXIS_CSL_PARAMETER_DATA="' #txt
Sr0 f24 exceptionHandler 155B554F725A24A5-f30-buffer #txt
Sr0 f24 returningObjectList '[wsResponse]' #txt
Sr0 f24 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Call ApplicationService
with HTTP Basic</name>
        <nameStyle>39,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Sr0 f24 200 322 160 44 -59 -16 #rect
Sr0 f24 @|WebServiceIcon #fIcon
Sr0 f15 type ch.ivy.add.portalkit.service.integrators.ServerServiceIntegratorData #txt
Sr0 f15 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Authentication Type?</name>
        <nameStyle>20,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Sr0 f15 490 202 28 28 8 -28 #rect
Sr0 f15 @|AlternativeIcon #fIcon
Sr0 f9 type ch.ivy.add.portalkit.service.integrators.ServerServiceIntegratorData #txt
Sr0 f9 actionDecl 'ch.ivy.add.portalkit.service.integrators.ServerServiceIntegratorData out;
' #txt
Sr0 f9 actionTable 'out=in;
out.errors=wsResponse.getExternalHostResponse.result.erros;
out.externalPath=wsResponse.getExternalHostResponse.result.externalHost;
' #txt
Sr0 f9 cache '{/cache false /invalidation false /scope 0 /groupname ""/lifetime_group "0"/invalidation_time_group ""/identifier ""/lifetime_entry "0"/invalidation_time_entry ""}' #txt
Sr0 f9 timeout 60 #txt
Sr0 f9 beanConfig '"KEY_PASSWORD=<%\\=in.server.password%>
KEY_AXIS_PORTNAME=ServerServicePort
KEY_WEBSERVICECONFIG_ID=155B8E8D03D38D19
KEY_DOMAIN=<%\\=in.server.domain%>
KEY_REQUEST_PARAMETER_MAPPINGS_OPTIONS_AUTO_INITIALIZE_FIRST_LEVEL_FIELDS=true
KEY_REQUEST_PARAMETER_MAPPINGS_OPTIONS_MAP_NULL_VALUES=false
KEY_USERNAME=<%\\=in.server.username%>
KEY_OPERATION=getExternalHost
KEY_AUTHENTICATION_KIND=4
KEY_HOST=<%\\=in.server.host%>
KEY_USE_AUTHENTICATION=true
KEY_AXIS_CSL_PARAMETER_DATA="' #txt
Sr0 f9 exceptionHandler 155B554F725A24A5-f30-buffer #txt
Sr0 f9 returningObjectList '[wsResponse]' #txt
Sr0 f9 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
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
Sr0 f9 416 314 176 60 -70 -24 #rect
Sr0 f9 @|WebServiceIcon #fIcon
Sr0 f17 type ch.ivy.add.portalkit.service.integrators.ServerServiceIntegratorData #txt
Sr0 f17 actionDecl 'ch.ivy.add.portalkit.service.integrators.ServerServiceIntegratorData out;
' #txt
Sr0 f17 actionTable 'out=in;
out.errors=wsResponse.getExternalHostResponse.result.erros;
out.externalPath=wsResponse.getExternalHostResponse.result.externalHost;
' #txt
Sr0 f17 cache '{/cache false /invalidation false /scope 0 /groupname ""/lifetime_group "0"/invalidation_time_group ""/identifier ""/lifetime_entry "0"/invalidation_time_entry ""}' #txt
Sr0 f17 timeout 60 #txt
Sr0 f17 beanConfig '"KEY_PASSWORD=
KEY_AXIS_PORTNAME=ServerServicePort
KEY_WEBSERVICECONFIG_ID=155B8E8D03D38D19
KEY_DOMAIN=
KEY_REQUEST_PARAMETER_MAPPINGS_OPTIONS_AUTO_INITIALIZE_FIRST_LEVEL_FIELDS=true
KEY_REQUEST_PARAMETER_MAPPINGS_OPTIONS_MAP_NULL_VALUES=false
KEY_USERNAME=
KEY_OPERATION=getExternalHost
KEY_AUTHENTICATION_KIND=0
KEY_HOST=
KEY_USE_AUTHENTICATION=false
KEY_AXIS_CSL_PARAMETER_DATA="' #txt
Sr0 f17 exceptionHandler 155B554F725A24A5-f30-buffer #txt
Sr0 f17 returningObjectList '[wsResponse]' #txt
Sr0 f17 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Call ApplicationService
with default settings</name>
        <nameStyle>45,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Sr0 f17 648 322 160 44 -59 -16 #rect
Sr0 f17 @|WebServiceIcon #fIcon
Sr0 f16 type ch.ivy.add.portalkit.service.integrators.ServerServiceIntegratorData #txt
Sr0 f16 491 459 26 26 14 0 #rect
Sr0 f16 @|EndSubIcon #fIcon
Sr0 f19 expr in #txt
Sr0 f19 outCond 'ch.ivy.addon.portalkit.enums.WSAuthenticationType.NTLM == in.server.wsAuthenticationType' #txt
Sr0 f19 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>NTLM</name>
        <nameStyle>4
</nameStyle>
    </language>
</elementInfo>
' #txt
Sr0 f19 504 230 504 314 #arcP
Sr0 f19 0 0.27380952380952384 -13 0 #arcLabel
Sr0 f20 expr out #txt
Sr0 f20 504 374 504 459 #arcP
Sr0 f22 expr out #txt
Sr0 f22 728 366 517 472 #arcP
Sr0 f22 1 728 472 #addKink
Sr0 f22 0 0.5636261753528126 0 0 #arcLabel
Sr0 f25 expr in #txt
Sr0 f25 outCond 'ch.ivy.addon.portalkit.enums.WSAuthenticationType.HTTP_BASIC == in.server.wsAuthenticationType' #txt
Sr0 f25 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>HTTP Basic</name>
        <nameStyle>10
</nameStyle>
    </language>
</elementInfo>
' #txt
Sr0 f25 490 216 280 322 #arcP
Sr0 f25 1 280 216 #addKink
Sr0 f25 0 0.719047619047619 0 -10 #arcLabel
Sr0 f26 expr out #txt
Sr0 f26 280 366 491 472 #arcP
Sr0 f26 1 280 472 #addKink
Sr0 f26 1 0.25011774148469074 0 0 #arcLabel
Sr0 f21 expr in #txt
Sr0 f21 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>None</name>
        <nameStyle>4
</nameStyle>
    </language>
</elementInfo>
' #txt
Sr0 f21 518 216 728 322 #arcP
Sr0 f21 1 728 216 #addKink
Sr0 f21 0 0.7986561412607155 -1 -11 #arcLabel
Sr0 f3 expr out #txt
Sr0 f3 504 125 504 202 #arcP
Sr0 f33 actionDecl 'ch.ivy.add.portalkit.service.integrators.ServerServiceIntegratorData out;
' #txt
Sr0 f33 actionTable 'out=in;
' #txt
Sr0 f33 actionCode 'import java.util.Calendar;
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
Sr0 f33 type ch.ivy.add.portalkit.service.integrators.ServerServiceIntegratorData #txt
Sr0 f33 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Handle WS Exception</name>
        <nameStyle>19
</nameStyle>
    </language>
</elementInfo>
' #txt
Sr0 f33 888 200 144 48 -53 -8 #rect
Sr0 f33 @|StepIcon #fIcon
Sr0 f30 .resExport export #txt
Sr0 f30 actionDecl 'ch.ivy.add.portalkit.service.integrators.ServerServiceIntegratorData out;
' #txt
Sr0 f30 actionTable 'out=in;
' #txt
Sr0 f30 type ch.ivy.add.portalkit.service.integrators.ServerServiceIntegratorData #txt
Sr0 f30 errorCode ivy:portalkit:webservice:intergrator:application #txt
Sr0 f30 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>ivy:portalkit:webservice:intergrator:server</name>
        <nameStyle>43,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Sr0 f30 947 115 26 26 14 0 #rect
Sr0 f30 @|ExceptionIcon #fIcon
Sr0 f31 type ch.ivy.add.portalkit.service.integrators.ServerServiceIntegratorData #txt
Sr0 f31 947 323 26 26 14 0 #rect
Sr0 f31 @|EndSubIcon #fIcon
Sr0 f34 expr out #txt
Sr0 f34 960 141 960 200 #arcP
Sr0 f34 0 0.5007315431205012 0 0 #arcLabel
Sr0 f32 expr out #txt
Sr0 f32 960 248 960 323 #arcP
>Proto Sr0 .type ch.ivy.add.portalkit.service.integrators.ServerServiceIntegratorData #txt
>Proto Sr0 .processKind CALLABLE_SUB #txt
>Proto Sr0 0 0 32 24 18 0 #rect
>Proto Sr0 @|BIcon #fIcon
Sr0 f15 out f19 tail #connect
Sr0 f19 head f9 mainIn #connect
Sr0 f9 mainOut f20 tail #connect
Sr0 f20 head f16 mainIn #connect
Sr0 f17 mainOut f22 tail #connect
Sr0 f22 head f16 mainIn #connect
Sr0 f15 out f25 tail #connect
Sr0 f25 head f24 mainIn #connect
Sr0 f24 mainOut f26 tail #connect
Sr0 f26 head f16 mainIn #connect
Sr0 f15 out f21 tail #connect
Sr0 f21 head f17 mainIn #connect
Sr0 f0 mainOut f3 tail #connect
Sr0 f3 head f15 in #connect
Sr0 f30 mainOut f34 tail #connect
Sr0 f34 head f33 mainIn #connect
Sr0 f33 mainOut f32 tail #connect
Sr0 f32 head f31 mainIn #connect
