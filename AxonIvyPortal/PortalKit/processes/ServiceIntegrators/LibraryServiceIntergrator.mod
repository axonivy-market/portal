[Ivy]
16266C24CFBD7885 3.20 #module
>Proto >Proto Collection #zClass
Ar0 LibraryServiceIntergrator Big #zClass
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
Ar0 @ProcessException f30 '' #zField
Ar0 @EndSub f31 '' #zField
Ar0 @GridStep f33 '' #zField
Ar0 @PushWFArc f34 '' #zField
Ar0 @PushWFArc f32 '' #zField
Ar0 @WSElement f24 '' #zField
Ar0 @PushWFArc f25 '' #zField
Ar0 @PushWFArc f26 '' #zField
Ar0 @PushWFArc f21 '' #zField
Ar0 @PushWFArc f35 '' #zField
Ar0 @StartSub f1 '' #zField
Ar0 @WSElement f2 '' #zField
Ar0 @WSElement f3 '' #zField
Ar0 @EndSub f4 '' #zField
Ar0 @WSElement f5 '' #zField
Ar0 @Alternative f6 '' #zField
Ar0 @PushWFArc f7 '' #zField
Ar0 @PushWFArc f8 '' #zField
Ar0 @PushWFArc f10 '' #zField
Ar0 @PushWFArc f11 '' #zField
Ar0 @PushWFArc f12 '' #zField
Ar0 @PushWFArc f13 '' #zField
Ar0 @PushWFArc f14 '' #zField
>Proto Ar0 Ar0 LibraryServiceIntergrator #zField
Ar0 f17 type ch.ivy.add.portalkit.service.integrators.LibraryServiceIntergratorData #txt
Ar0 f17 actionDecl 'ch.ivy.add.portalkit.service.integrators.LibraryServiceIntergratorData out;
' #txt
Ar0 f17 actionTable 'out=in;
out.errors=wsResponse.getLibrariesResponse.result.errors;
out.libraries=wsResponse.getLibrariesResponse.result.libraries;
' #txt
Ar0 f17 cache '{/cache false /invalidation false /scope 2 /groupname ""/invalidation_time_group ""/lifetime_group "0"/identifier ""/invalidation_time_entry ""/lifetime_entry "0"}' #txt
Ar0 f17 timeout 0 #txt
Ar0 f17 beanConfig '"KEY_PASSWORD=
KEY_AXIS_PORTNAME=LibraryServicePort
KEY_WEBSERVICECONFIG_ID=16266B7AEB1665F0
KEY_DOMAIN=
KEY_REQUEST_PARAMETER_MAPPINGS_OPTIONS_AUTO_INITIALIZE_FIRST_LEVEL_FIELDS=true
KEY_REQUEST_PARAMETER_MAPPINGS_OPTIONS_MAP_NULL_VALUES=false
KEY_USERNAME=
KEY_OPERATION=getLibraries
KEY_AUTHENTICATION_KIND=0
KEY_HOST=
KEY_USE_AUTHENTICATION=false
KEY_AXIS_CSL_PARAMETER_DATA=""arg0.getLibraries.apps__@@__Array<String>__@@__in.applicationNames"""' #txt
Ar0 f17 exceptionHandler 16266C24CFBD7885-f30-buffer #txt
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
Ar0 f15 type ch.ivy.add.portalkit.service.integrators.LibraryServiceIntergratorData #txt
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
Ar0 f9 type ch.ivy.add.portalkit.service.integrators.LibraryServiceIntergratorData #txt
Ar0 f9 actionDecl 'ch.ivy.add.portalkit.service.integrators.LibraryServiceIntergratorData out;
' #txt
Ar0 f9 actionTable 'out=in;
out.errors=wsResponse.getLibrariesResponse.result.errors;
out.libraries=wsResponse.getLibrariesResponse.result.libraries;
' #txt
Ar0 f9 cache '{/cache false /invalidation false /scope 2 /groupname ""/invalidation_time_group ""/lifetime_group "0"/identifier ""/invalidation_time_entry ""/lifetime_entry "0"}' #txt
Ar0 f9 timeout 0 #txt
Ar0 f9 beanConfig '"KEY_PASSWORD=<%\\=in.server.password%>
KEY_AXIS_PORTNAME=LibraryServicePort
KEY_WEBSERVICECONFIG_ID=16266B7AEB1665F0
KEY_DOMAIN=<%\\=in.server.domain%>
KEY_REQUEST_PARAMETER_MAPPINGS_OPTIONS_AUTO_INITIALIZE_FIRST_LEVEL_FIELDS=true
KEY_REQUEST_PARAMETER_MAPPINGS_OPTIONS_MAP_NULL_VALUES=false
KEY_USERNAME=<%\\=in.server.username%>
KEY_OPERATION=getLibraries
KEY_AUTHENTICATION_KIND=4
KEY_HOST=<%\\=in.server.host%>
KEY_USE_AUTHENTICATION=true
KEY_AXIS_CSL_PARAMETER_DATA=""arg0.getLibraries.apps__@@__Array<String>__@@__in.applicationNames"""' #txt
Ar0 f9 exceptionHandler 16266C24CFBD7885-f30-buffer #txt
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
Ar0 f16 type ch.ivy.add.portalkit.service.integrators.LibraryServiceIntergratorData #txt
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
Ar0 f0 inParamDecl '<List<java.lang.String> apps,java.lang.String endpoint,ch.ivy.addon.portalkit.persistence.domain.Server server> param;' #txt
Ar0 f0 inParamTable 'out.applicationNames=param.apps;
out.endpoint=param.endpoint;
out.server=param.server;
' #txt
Ar0 f0 outParamDecl '<List<ch.ivy.ws.addon.IvyLibrary> libraries,List<ch.ivy.ws.addon.WsException> errors> result;
' #txt
Ar0 f0 outParamTable 'result.libraries=in.libraries;
result.errors=in.errors;
' #txt
Ar0 f0 actionDecl 'ch.ivy.add.portalkit.service.integrators.LibraryServiceIntergratorData out;
' #txt
Ar0 f0 callSignature getLibraries(List<String>,String,ch.ivy.addon.portalkit.persistence.domain.Server) #txt
Ar0 f0 type ch.ivy.add.portalkit.service.integrators.LibraryServiceIntergratorData #txt
Ar0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>getLibraries(List&lt;String&gt;,String,Server)</name>
        <nameStyle>40,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ar0 f0 339 51 26 26 14 0 #rect
Ar0 f0 @|StartSubIcon #fIcon
Ar0 f30 .resExport export #txt
Ar0 f30 actionDecl 'ch.ivy.add.portalkit.service.integrators.LibraryServiceIntergratorData out;
' #txt
Ar0 f30 actionTable 'out=in;
' #txt
Ar0 f30 type ch.ivy.add.portalkit.service.integrators.LibraryServiceIntergratorData #txt
Ar0 f30 errorCode ivy:portalkit:webservice:intergrator:library #txt
Ar0 f30 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>ivy:portalkit:webservice:intergrator:library</name>
        <nameStyle>44,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ar0 f30 195 555 26 26 14 0 #rect
Ar0 f30 @|ExceptionIcon #fIcon
Ar0 f31 type ch.ivy.add.portalkit.service.integrators.LibraryServiceIntergratorData #txt
Ar0 f31 195 763 26 26 14 0 #rect
Ar0 f31 @|EndSubIcon #fIcon
Ar0 f33 actionDecl 'ch.ivy.add.portalkit.service.integrators.LibraryServiceIntergratorData out;
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
Ar0 f33 type ch.ivy.add.portalkit.service.integrators.LibraryServiceIntergratorData #txt
Ar0 f33 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Handle WS Exception</name>
        <nameStyle>19
</nameStyle>
    </language>
</elementInfo>
' #txt
Ar0 f33 136 640 144 48 -53 -8 #rect
Ar0 f33 @|StepIcon #fIcon
Ar0 f34 expr out #txt
Ar0 f34 208 581 208 640 #arcP
Ar0 f34 0 0.5007315431205012 0 0 #arcLabel
Ar0 f32 expr out #txt
Ar0 f32 208 688 208 763 #arcP
Ar0 f24 type ch.ivy.add.portalkit.service.integrators.LibraryServiceIntergratorData #txt
Ar0 f24 actionDecl 'ch.ivy.add.portalkit.service.integrators.LibraryServiceIntergratorData out;
' #txt
Ar0 f24 actionTable 'out=in;
out.errors=wsResponse.getLibrariesResponse.result.errors;
out.libraries=wsResponse.getLibrariesResponse.result.libraries;
' #txt
Ar0 f24 cache '{/cache false /invalidation false /scope 2 /groupname ""/invalidation_time_group ""/lifetime_group "0"/identifier ""/invalidation_time_entry ""/lifetime_entry "0"}' #txt
Ar0 f24 timeout 0 #txt
Ar0 f24 beanConfig '"KEY_PASSWORD=<%\\=in.server.password%>
KEY_AXIS_PORTNAME=LibraryServicePort
KEY_WEBSERVICECONFIG_ID=16266B7AEB1665F0
KEY_DOMAIN=
KEY_REQUEST_PARAMETER_MAPPINGS_OPTIONS_AUTO_INITIALIZE_FIRST_LEVEL_FIELDS=true
KEY_REQUEST_PARAMETER_MAPPINGS_OPTIONS_MAP_NULL_VALUES=false
KEY_USERNAME=<%\\=in.server.username%>
KEY_OPERATION=getLibraries
KEY_AUTHENTICATION_KIND=1
KEY_HOST=
KEY_USE_AUTHENTICATION=true
KEY_AXIS_CSL_PARAMETER_DATA=""arg0.getLibraries.apps__@@__Array<String>__@@__in.applicationNames"""' #txt
Ar0 f24 exceptionHandler 16266C24CFBD7885-f30-buffer #txt
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
Ar0 f35 expr out #txt
Ar0 f35 352 77 352 178 #arcP
Ar0 f1 inParamDecl '<java.lang.String endpoint,ch.ivy.addon.portalkit.persistence.domain.Server server,java.lang.String appName,java.lang.String libraryId> param;' #txt
Ar0 f1 inParamTable 'out.appName=param.appName;
out.endpoint=param.endpoint;
out.libraryId=param.libraryId;
out.server=param.server;
' #txt
Ar0 f1 outParamDecl '<ch.ivy.ws.addon.IvyLibrary library,List<ch.ivy.ws.addon.WsException> errors> result;
' #txt
Ar0 f1 outParamTable 'result.library=in.library;
result.errors=in.errors;
' #txt
Ar0 f1 actionDecl 'ch.ivy.add.portalkit.service.integrators.LibraryServiceIntergratorData out;
' #txt
Ar0 f1 callSignature getLibrary(String,ch.ivy.addon.portalkit.persistence.domain.Server,String,String) #txt
Ar0 f1 type ch.ivy.add.portalkit.service.integrators.LibraryServiceIntergratorData #txt
Ar0 f1 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>getLibrary(String,Server,String,String)</name>
        <nameStyle>39,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ar0 f1 1116 52 24 24 14 0 #rect
Ar0 f1 @|StartSubIcon #fIcon
Ar0 f2 type ch.ivy.add.portalkit.service.integrators.LibraryServiceIntergratorData #txt
Ar0 f2 actionDecl 'ch.ivy.add.portalkit.service.integrators.LibraryServiceIntergratorData out;
' #txt
Ar0 f2 actionTable 'out=in;
out.errors=wsResponse.getLibraryResponse.result.errors;
out.library=wsResponse.getLibraryResponse.result.library;
' #txt
Ar0 f2 cache '{/cache false /invalidation false /scope 2 /groupname ""/invalidation_time_group ""/lifetime_group "0"/identifier ""/invalidation_time_entry ""/lifetime_entry "0"}' #txt
Ar0 f2 timeout 0 #txt
Ar0 f2 beanConfig '"KEY_PASSWORD=
KEY_AXIS_PORTNAME=LibraryServicePort
KEY_WEBSERVICECONFIG_ID=16266B7AEB1665F0
KEY_DOMAIN=
KEY_REQUEST_PARAMETER_MAPPINGS_OPTIONS_AUTO_INITIALIZE_FIRST_LEVEL_FIELDS=true
KEY_REQUEST_PARAMETER_MAPPINGS_OPTIONS_MAP_NULL_VALUES=false
KEY_USERNAME=
KEY_OPERATION=getLibrary
KEY_AUTHENTICATION_KIND=0
KEY_HOST=
KEY_USE_AUTHENTICATION=false
KEY_AXIS_CSL_PARAMETER_DATA=""arg0.getLibrary.appName__@@__String__@@__in.appName"",""arg0.getLibrary.libraryId__@@__String__@@__in.libraryId"""' #txt
Ar0 f2 exceptionHandler 16266C24CFBD7885-f30-buffer #txt
Ar0 f2 returningObjectList '[wsResponse]' #txt
Ar0 f2 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Call ApplicationService
with default settings</name>
        <nameStyle>45,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ar0 f2 1273 305 160 44 -59 -16 #rect
Ar0 f2 @|WebServiceIcon #fIcon
Ar0 f3 type ch.ivy.add.portalkit.service.integrators.LibraryServiceIntergratorData #txt
Ar0 f3 actionDecl 'ch.ivy.add.portalkit.service.integrators.LibraryServiceIntergratorData out;
' #txt
Ar0 f3 actionTable 'out=in;
out.errors=wsResponse.getLibraryResponse.result.errors;
out.library=wsResponse.getLibraryResponse.result.library;
' #txt
Ar0 f3 cache '{/cache false /invalidation false /scope 2 /groupname ""/invalidation_time_group ""/lifetime_group "0"/identifier ""/invalidation_time_entry ""/lifetime_entry "0"}' #txt
Ar0 f3 timeout 0 #txt
Ar0 f3 beanConfig '"KEY_PASSWORD=<%\\=in.server.password%>
KEY_AXIS_PORTNAME=LibraryServicePort
KEY_WEBSERVICECONFIG_ID=16266B7AEB1665F0
KEY_DOMAIN=<%\\=in.server.domain%>
KEY_REQUEST_PARAMETER_MAPPINGS_OPTIONS_AUTO_INITIALIZE_FIRST_LEVEL_FIELDS=true
KEY_REQUEST_PARAMETER_MAPPINGS_OPTIONS_MAP_NULL_VALUES=false
KEY_USERNAME=<%\\=in.server.username%>
KEY_OPERATION=getLibrary
KEY_AUTHENTICATION_KIND=4
KEY_HOST=<%\\=in.server.host%>
KEY_USE_AUTHENTICATION=true
KEY_AXIS_CSL_PARAMETER_DATA=""arg0.getLibrary.appName__@@__String__@@__in.appName"",""arg0.getLibrary.libraryId__@@__String__@@__in.libraryId"""' #txt
Ar0 f3 exceptionHandler 16266C24CFBD7885-f30-buffer #txt
Ar0 f3 returningObjectList '[wsResponse]' #txt
Ar0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
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
Ar0 f3 1041 297 176 60 -70 -24 #rect
Ar0 f3 @|WebServiceIcon #fIcon
Ar0 f4 type ch.ivy.add.portalkit.service.integrators.LibraryServiceIntergratorData #txt
Ar0 f4 1116 442 26 26 14 0 #rect
Ar0 f4 @|EndSubIcon #fIcon
Ar0 f5 type ch.ivy.add.portalkit.service.integrators.LibraryServiceIntergratorData #txt
Ar0 f5 actionDecl 'ch.ivy.add.portalkit.service.integrators.LibraryServiceIntergratorData out;
' #txt
Ar0 f5 actionTable 'out=in;
out.errors=wsResponse.getLibraryResponse.result.errors;
out.library=wsResponse.getLibraryResponse.result.library;
' #txt
Ar0 f5 cache '{/cache false /invalidation false /scope 2 /groupname ""/invalidation_time_group ""/lifetime_group "0"/identifier ""/invalidation_time_entry ""/lifetime_entry "0"}' #txt
Ar0 f5 timeout 0 #txt
Ar0 f5 beanConfig '"KEY_PASSWORD=<%\\=in.server.password%>
KEY_AXIS_PORTNAME=LibraryServicePort
KEY_WEBSERVICECONFIG_ID=16266B7AEB1665F0
KEY_DOMAIN=
KEY_REQUEST_PARAMETER_MAPPINGS_OPTIONS_AUTO_INITIALIZE_FIRST_LEVEL_FIELDS=true
KEY_REQUEST_PARAMETER_MAPPINGS_OPTIONS_MAP_NULL_VALUES=false
KEY_USERNAME=<%\\=in.server.username%>
KEY_OPERATION=getLibrary
KEY_AUTHENTICATION_KIND=1
KEY_HOST=
KEY_USE_AUTHENTICATION=true
KEY_AXIS_CSL_PARAMETER_DATA=""arg0.getLibrary.appName__@@__String__@@__in.appName"",""arg0.getLibrary.libraryId__@@__String__@@__in.libraryId"""' #txt
Ar0 f5 exceptionHandler 16266C24CFBD7885-f30-buffer #txt
Ar0 f5 returningObjectList '[wsResponse]' #txt
Ar0 f5 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Call ApplicationService
with HTTP Basic</name>
        <nameStyle>39,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ar0 f5 825 305 160 44 -59 -16 #rect
Ar0 f5 @|WebServiceIcon #fIcon
Ar0 f6 type ch.ivy.add.portalkit.service.integrators.LibraryServiceIntergratorData #txt
Ar0 f6 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Authentication Type?</name>
        <nameStyle>20,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ar0 f6 1115 177 28 28 8 -28 #rect
Ar0 f6 @|AlternativeIcon #fIcon
Ar0 f7 expr out #txt
Ar0 f7 1353 349 1142 455 #arcP
Ar0 f7 1 1353 455 #addKink
Ar0 f7 0 0.561886529604993 0 0 #arcLabel
Ar0 f8 expr out #txt
Ar0 f8 905 349 1116 455 #arcP
Ar0 f8 1 905 455 #addKink
Ar0 f8 1 0.2688637914256368 0 0 #arcLabel
Ar0 f10 expr out #txt
Ar0 f10 1129 357 1129 442 #arcP
Ar0 f11 expr in #txt
Ar0 f11 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>None</name>
        <nameStyle>4
</nameStyle>
    </language>
</elementInfo>
' #txt
Ar0 f11 1143 191 1353 305 #arcP
Ar0 f11 1 1353 191 #addKink
Ar0 f11 0 0.7986561412607155 -1 -11 #arcLabel
Ar0 f12 expr in #txt
Ar0 f12 outCond 'ch.ivy.addon.portalkit.enums.WSAuthenticationType.NTLM == in.server.wsAuthenticationType' #txt
Ar0 f12 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>NTLM</name>
        <nameStyle>4
</nameStyle>
    </language>
</elementInfo>
' #txt
Ar0 f12 1129 205 1129 297 #arcP
Ar0 f12 0 0.2608695652173913 -20 0 #arcLabel
Ar0 f13 expr in #txt
Ar0 f13 outCond 'ch.ivy.addon.portalkit.enums.WSAuthenticationType.HTTP_BASIC == in.server.wsAuthenticationType' #txt
Ar0 f13 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>HTTP Basic</name>
        <nameStyle>10
</nameStyle>
    </language>
</elementInfo>
' #txt
Ar0 f13 1115 191 905 305 #arcP
Ar0 f13 1 905 191 #addKink
Ar0 f13 0 0.7196013786683974 0 -10 #arcLabel
Ar0 f14 expr out #txt
Ar0 f14 1128 75 1129 177 #arcP
>Proto Ar0 .type ch.ivy.add.portalkit.service.integrators.LibraryServiceIntergratorData #txt
>Proto Ar0 .processKind CALLABLE_SUB #txt
>Proto Ar0 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language/>
</elementInfo>
' #txt
>Proto Ar0 0 0 32 24 18 0 #rect
>Proto Ar0 @|BIcon #fIcon
Ar0 f15 out f19 tail #connect
Ar0 f19 head f9 mainIn #connect
Ar0 f9 mainOut f20 tail #connect
Ar0 f20 head f16 mainIn #connect
Ar0 f17 mainOut f22 tail #connect
Ar0 f22 head f16 mainIn #connect
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
Ar0 f0 mainOut f35 tail #connect
Ar0 f35 head f15 in #connect
Ar0 f6 out f12 tail #connect
Ar0 f12 head f3 mainIn #connect
Ar0 f3 mainOut f10 tail #connect
Ar0 f10 head f4 mainIn #connect
Ar0 f2 mainOut f7 tail #connect
Ar0 f7 head f4 mainIn #connect
Ar0 f6 out f13 tail #connect
Ar0 f13 head f5 mainIn #connect
Ar0 f5 mainOut f8 tail #connect
Ar0 f8 head f4 mainIn #connect
Ar0 f6 out f11 tail #connect
Ar0 f11 head f2 mainIn #connect
Ar0 f1 mainOut f14 tail #connect
Ar0 f14 head f6 in #connect
