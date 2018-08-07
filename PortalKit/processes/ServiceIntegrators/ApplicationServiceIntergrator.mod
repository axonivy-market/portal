[Ivy]
[>Created: Fri May 27 16:59:25 ICT 2016]
154F0F14C9113E05 3.18 #module
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
Ar0 @PushWFArc f21 '' #zField
Ar0 @PushWFArc f22 '' #zField
Ar0 @StartSub f0 '' #zField
Ar0 @PushWFArc f1 '' #zField
Ar0 @StartSub f2 '' #zField
Ar0 @WSElement f7 '' #zField
Ar0 @Alternative f8 '' #zField
Ar0 @EndSub f10 '' #zField
Ar0 @WSElement f11 '' #zField
Ar0 @PushWFArc f13 '' #zField
Ar0 @PushWFArc f14 '' #zField
Ar0 @PushWFArc f18 '' #zField
Ar0 @PushWFArc f23 '' #zField
Ar0 @GridStep f28 '' #zField
Ar0 @PushWFArc f29 '' #zField
Ar0 @PushWFArc f27 '' #zField
Ar0 @ProcessException f30 '' #zField
Ar0 @EndSub f31 '' #zField
Ar0 @GridStep f33 '' #zField
Ar0 @PushWFArc f34 '' #zField
Ar0 @PushWFArc f32 '' #zField
>Proto Ar0 Ar0 ApplicationServiceIntergrator #zField
Ar0 f17 type ch.ivy.add.portalkit.service.integrators.ApplicationServiceIntergratorData #txt
Ar0 f17 actionDecl 'ch.ivy.add.portalkit.service.integrators.ApplicationServiceIntergratorData out;
' #txt
Ar0 f17 actionTable 'out=in;
out.applications=wsResponse.getAllApplicationsResponse.result.applications;
out.errors=wsResponse.getAllApplicationsResponse.result.errors;
' #txt
Ar0 f17 cache '{/cache false /invalidation false /scope 0 /groupname ""/lifetime_group "0"/invalidation_time_group ""/identifier ""/lifetime_entry "0"/invalidation_time_entry ""}' #txt
Ar0 f17 timeout 0 #txt
Ar0 f17 beanConfig '"KEY_PASSWORD=
KEY_AXIS_PORTNAME=ApplicationServicePort
KEY_WEBSERVICECONFIG_ID=154F091E0FE17F3C
KEY_DOMAIN=
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
        <nameStyle>45
</nameStyle>
    </language>
</elementInfo>
' #txt
Ar0 f17 384 244 160 56 -59 -16 #rect
Ar0 f17 @|WebServiceIcon #fIcon
Ar0 f15 type ch.ivy.add.portalkit.service.integrators.ApplicationServiceIntergratorData #txt
Ar0 f15 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>User NTLM Authentication?</name>
        <nameStyle>25
</nameStyle>
    </language>
</elementInfo>
' #txt
Ar0 f15 170 146 28 28 9 -42 #rect
Ar0 f15 @|AlternativeIcon #fIcon
Ar0 f9 type ch.ivy.add.portalkit.service.integrators.ApplicationServiceIntergratorData #txt
Ar0 f9 actionDecl 'ch.ivy.add.portalkit.service.integrators.ApplicationServiceIntergratorData out;
' #txt
Ar0 f9 actionTable 'out=in;
out.applications=wsResponse.getAllApplicationsResponse.result.applications;
out.errors=wsResponse.getAllApplicationsResponse.result.errors;
' #txt
Ar0 f9 cache '{/cache false /invalidation false /scope 0 /groupname ""/lifetime_group "0"/invalidation_time_group ""/identifier ""/lifetime_entry "0"/invalidation_time_entry ""}' #txt
Ar0 f9 timeout 0 #txt
Ar0 f9 beanConfig '"KEY_PASSWORD=<%\\=ivy.var.PortalWSPassword%>
KEY_AXIS_PORTNAME=ApplicationServicePort
KEY_WEBSERVICECONFIG_ID=154F091E0FE17F3C
KEY_DOMAIN=<%\\=ivy.var.PortalWSDomain%>
KEY_USERNAME=<%\\=ivy.var.PortalWSUsername%>
KEY_OPERATION=getAllApplications
KEY_AUTHENTICATION_KIND=4
KEY_HOST=<%\\=ivy.var.PortalWSHost%>
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
        <nameStyle>71
</nameStyle>
    </language>
</elementInfo>
' #txt
Ar0 f9 96 242 176 60 -70 -24 #rect
Ar0 f9 @|WebServiceIcon #fIcon
Ar0 f16 type ch.ivy.add.portalkit.service.integrators.ApplicationServiceIntergratorData #txt
Ar0 f16 171 427 26 26 14 0 #rect
Ar0 f16 @|EndSubIcon #fIcon
Ar0 f19 expr in #txt
Ar0 f19 outCond in.server.isNTLMAuthentication #txt
Ar0 f19 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>yes</name>
        <nameStyle>3
</nameStyle>
    </language>
</elementInfo>
' #txt
Ar0 f19 184 174 184 242 #arcP
Ar0 f19 0 0.27941176470588236 -14 0 #arcLabel
Ar0 f20 expr out #txt
Ar0 f20 184 302 184 427 #arcP
Ar0 f21 expr in #txt
Ar0 f21 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>no</name>
        <nameStyle>2
</nameStyle>
    </language>
</elementInfo>
' #txt
Ar0 f21 198 160 464 244 #arcP
Ar0 f21 1 464 160 #addKink
Ar0 f21 0 0.6691729323308271 0 -7 #arcLabel
Ar0 f22 expr out #txt
Ar0 f22 464 300 197 440 #arcP
Ar0 f22 1 464 440 #addKink
Ar0 f22 1 0.18283027938602842 0 0 #arcLabel
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
Ar0 f0 171 51 26 26 14 0 #rect
Ar0 f0 @|StartSubIcon #fIcon
Ar0 f1 expr out #txt
Ar0 f1 184 77 184 146 #arcP
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
Ar0 f2 675 51 26 26 14 0 #rect
Ar0 f2 @|StartSubIcon #fIcon
Ar0 f7 type ch.ivy.add.portalkit.service.integrators.ApplicationServiceIntergratorData #txt
Ar0 f7 actionDecl 'ch.ivy.add.portalkit.service.integrators.ApplicationServiceIntergratorData out;
' #txt
Ar0 f7 actionTable 'out=in;
out.applications=wsResponse.getApplicationsByAppNamesResponse.result.applications;
out.errors=wsResponse.getApplicationsByAppNamesResponse.result.errors;
' #txt
Ar0 f7 cache '{/cache false /invalidation false /scope 0 /groupname ""/lifetime_group "0"/invalidation_time_group ""/identifier ""/lifetime_entry "0"/invalidation_time_entry ""}' #txt
Ar0 f7 timeout 0 #txt
Ar0 f7 beanConfig '"KEY_PASSWORD=<%\\=ivy.var.PortalWSPassword%>
KEY_AXIS_PORTNAME=ApplicationServicePort
KEY_WEBSERVICECONFIG_ID=154F091E0FE17F3C
KEY_DOMAIN=<%\\=ivy.var.PortalWSDomain%>
KEY_USERNAME=<%\\=ivy.var.PortalWSUsername%>
KEY_OPERATION=getApplicationsByAppNames
KEY_AUTHENTICATION_KIND=4
KEY_HOST=<%\\=ivy.var.PortalWSHost%>
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
        <nameStyle>71
</nameStyle>
    </language>
</elementInfo>
' #txt
Ar0 f7 600 282 176 60 -70 -24 #rect
Ar0 f7 @|WebServiceIcon #fIcon
Ar0 f8 type ch.ivy.add.portalkit.service.integrators.ApplicationServiceIntergratorData #txt
Ar0 f8 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>User NTLM Authentication?</name>
        <nameStyle>25
</nameStyle>
    </language>
</elementInfo>
' #txt
Ar0 f8 674 186 28 28 9 -42 #rect
Ar0 f8 @|AlternativeIcon #fIcon
Ar0 f10 type ch.ivy.add.portalkit.service.integrators.ApplicationServiceIntergratorData #txt
Ar0 f10 675 467 26 26 14 0 #rect
Ar0 f10 @|EndSubIcon #fIcon
Ar0 f11 type ch.ivy.add.portalkit.service.integrators.ApplicationServiceIntergratorData #txt
Ar0 f11 actionDecl 'ch.ivy.add.portalkit.service.integrators.ApplicationServiceIntergratorData out;
' #txt
Ar0 f11 actionTable 'out=in;
out.applications=wsResponse.getApplicationsByAppNamesResponse.result.applications;
out.errors=wsResponse.getApplicationsByAppNamesResponse.result.errors;
' #txt
Ar0 f11 cache '{/cache false /invalidation false /scope 0 /groupname ""/lifetime_group "0"/invalidation_time_group ""/identifier ""/lifetime_entry "0"/invalidation_time_entry ""}' #txt
Ar0 f11 timeout 0 #txt
Ar0 f11 beanConfig '"KEY_PASSWORD=
KEY_AXIS_PORTNAME=ApplicationServicePort
KEY_WEBSERVICECONFIG_ID=154F091E0FE17F3C
KEY_DOMAIN=
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
        <nameStyle>45
</nameStyle>
    </language>
</elementInfo>
' #txt
Ar0 f11 888 284 160 56 -59 -16 #rect
Ar0 f11 @|WebServiceIcon #fIcon
Ar0 f13 expr in #txt
Ar0 f13 outCond in.server.isNTLMAuthentication #txt
Ar0 f13 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>yes</name>
        <nameStyle>3
</nameStyle>
    </language>
</elementInfo>
' #txt
Ar0 f13 688 214 688 282 #arcP
Ar0 f13 0 0.27941176470588236 -15 0 #arcLabel
Ar0 f14 expr out #txt
Ar0 f14 688 342 688 467 #arcP
Ar0 f18 expr in #txt
Ar0 f18 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>no</name>
        <nameStyle>2
</nameStyle>
    </language>
</elementInfo>
' #txt
Ar0 f18 702 200 968 284 #arcP
Ar0 f18 1 968 200 #addKink
Ar0 f18 0 0.6691729323308271 0 -7 #arcLabel
Ar0 f23 expr out #txt
Ar0 f23 968 340 701 480 #arcP
Ar0 f23 1 968 480 #addKink
Ar0 f23 1 0.18283027938602842 0 0 #arcLabel
Ar0 f28 actionDecl 'ch.ivy.add.portalkit.service.integrators.ApplicationServiceIntergratorData out;
' #txt
Ar0 f28 actionTable 'out=in;
' #txt
Ar0 f28 actionCode 'import ch.ivy.addon.portalkit.util.SecurityServiceUtils;

in.applicationNames = SecurityServiceUtils.getAppNames(in.server);' #txt
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
Ar0 f28 670 116 36 24 20 -2 #rect
Ar0 f28 @|StepIcon #fIcon
Ar0 f29 expr out #txt
Ar0 f29 688 77 688 116 #arcP
Ar0 f27 expr out #txt
Ar0 f27 688 140 688 186 #arcP
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
Ar0 f30 1235 59 26 26 14 0 #rect
Ar0 f30 @|ExceptionIcon #fIcon
Ar0 f31 type ch.ivy.add.portalkit.service.integrators.ApplicationServiceIntergratorData #txt
Ar0 f31 1235 275 26 26 14 0 #rect
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
Ar0 f33 1176 168 144 48 -53 -8 #rect
Ar0 f33 @|StepIcon #fIcon
Ar0 f34 expr out #txt
Ar0 f34 1248 85 1248 168 #arcP
Ar0 f34 0 0.5007315431205012 0 0 #arcLabel
Ar0 f32 expr out #txt
Ar0 f32 1248 216 1248 275 #arcP
>Proto Ar0 .type ch.ivy.add.portalkit.service.integrators.ApplicationServiceIntergratorData #txt
>Proto Ar0 .processKind CALLABLE_SUB #txt
>Proto Ar0 0 0 32 24 18 0 #rect
>Proto Ar0 @|BIcon #fIcon
Ar0 f15 out f19 tail #connect
Ar0 f19 head f9 mainIn #connect
Ar0 f9 mainOut f20 tail #connect
Ar0 f20 head f16 mainIn #connect
Ar0 f15 out f21 tail #connect
Ar0 f21 head f17 mainIn #connect
Ar0 f17 mainOut f22 tail #connect
Ar0 f22 head f16 mainIn #connect
Ar0 f0 mainOut f1 tail #connect
Ar0 f1 head f15 in #connect
Ar0 f8 out f13 tail #connect
Ar0 f13 head f7 mainIn #connect
Ar0 f7 mainOut f14 tail #connect
Ar0 f14 head f10 mainIn #connect
Ar0 f8 out f18 tail #connect
Ar0 f18 head f11 mainIn #connect
Ar0 f11 mainOut f23 tail #connect
Ar0 f23 head f10 mainIn #connect
Ar0 f2 mainOut f29 tail #connect
Ar0 f29 head f28 mainIn #connect
Ar0 f28 mainOut f27 tail #connect
Ar0 f27 head f8 in #connect
Ar0 f30 mainOut f34 tail #connect
Ar0 f34 head f33 mainIn #connect
Ar0 f33 mainOut f32 tail #connect
Ar0 f32 head f31 mainIn #connect
