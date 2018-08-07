[Ivy]
[>Created: Mon Dec 28 11:09:15 ICT 2015]
14E8716736559EDB 3.18 #module
>Proto >Proto Collection #zClass
Ie0 IsAliveServiceIntegrator Big #zClass
Ie0 B #cInfo
Ie0 #process
Ie0 @TextInP .resExport .resExport #zField
Ie0 @TextInP .type .type #zField
Ie0 @TextInP .processKind .processKind #zField
Ie0 @AnnotationInP-0n ai ai #zField
Ie0 @TextInP .xml .xml #zField
Ie0 @TextInP .responsibility .responsibility #zField
Ie0 @StartSub f0 '' #zField
Ie0 @EndSub f1 '' #zField
Ie0 @Alternative f2 '' #zField
Ie0 @WSElement f3 '' #zField
Ie0 @PushWFArc f5 '' #zField
Ie0 @PushWFArc f6 '' #zField
Ie0 @PushWFArc f7 '' #zField
Ie0 @ProcessException f10 '' #zField
Ie0 @EndSub f11 '' #zField
Ie0 @PushWFArc f12 '' #zField
Ie0 @WSElement f13 '' #zField
Ie0 @PushWFArc f14 '' #zField
Ie0 @PushWFArc f4 '' #zField
>Proto Ie0 Ie0 IsAliveServiceIntegrator #zField
Ie0 f0 inParamDecl '<java.lang.String endpoint,List<java.lang.String> applicationNames,ch.ivy.addon.portalkit.persistence.domain.Server server> param;' #txt
Ie0 f0 inParamTable 'out.applicationNames=param.applicationNames;
out.endpoint=param.endpoint;
out.server=param.server;
' #txt
Ie0 f0 outParamDecl '<List<ch.ivy.ws.addon.WsException> errors,List<ch.ivy.ws.addon.IvyApplication> applications,java.lang.Boolean isServerAlive> result;
' #txt
Ie0 f0 outParamTable 'result.errors=in.errors;
result.applications=in.applications;
result.isServerAlive=in.isServerAlive;
' #txt
Ie0 f0 actionDecl 'ch.ivy.add.portalkit.service.integrators.IsAliveServiceIntegratorData out;
' #txt
Ie0 f0 callSignature isAlive(String,List<String>,ch.ivy.addon.portalkit.persistence.domain.Server) #txt
Ie0 f0 type ch.ivy.add.portalkit.service.integrators.IsAliveServiceIntegratorData #txt
Ie0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>isAlive(String,List&lt;String&gt;,Server)</name>
        <nameStyle>35,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ie0 f0 83 51 26 26 14 0 #rect
Ie0 f0 @|StartSubIcon #fIcon
Ie0 f1 type ch.ivy.add.portalkit.service.integrators.IsAliveServiceIntegratorData #txt
Ie0 f1 83 307 26 26 14 0 #rect
Ie0 f1 @|EndSubIcon #fIcon
Ie0 f2 type ch.ivy.add.portalkit.service.integrators.IsAliveServiceIntegratorData #txt
Ie0 f2 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>User NTLM Authentication?</name>
        <nameStyle>25,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ie0 f2 82 114 28 28 16 -22 #rect
Ie0 f2 @|AlternativeIcon #fIcon
Ie0 f3 type ch.ivy.add.portalkit.service.integrators.IsAliveServiceIntegratorData #txt
Ie0 f3 actionDecl 'ch.ivy.add.portalkit.service.integrators.IsAliveServiceIntegratorData out;
' #txt
Ie0 f3 actionTable 'out=in;
out.applications=wsResponse.isAliveResponse.result.applicationList;
out.errors=wsResponse.isAliveResponse.result.errors;
out.isServerAlive=wsResponse.isAliveResponse.result.isAlive;
' #txt
Ie0 f3 cache '{/cache false /invalidation false /scope 0 /groupname ""/lifetime_group "0"/invalidation_time_group ""/identifier ""/lifetime_entry "0"/invalidation_time_entry ""}' #txt
Ie0 f3 timeout 10 #txt
Ie0 f3 beanConfig '"KEY_PASSWORD=<%\\=ivy.var.PortalWSPassword%>
KEY_AXIS_PORTNAME=IsAliveServicePort
KEY_WEBSERVICECONFIG_ID=146C8FD0DD5620DA
KEY_DOMAIN=<%\\=ivy.var.PortalWSDomain%>
KEY_USERNAME=<%\\=ivy.var.PortalWSUsername%>
KEY_OPERATION=isAlive
KEY_AUTHENTICATION_KIND=4
KEY_HOST=<%\\=ivy.var.PortalWSHost%>
KEY_USE_AUTHENTICATION=true
KEY_AXIS_CSL_PARAMETER_DATA=""arg0.isAlive.apps__@@__Array<String>__@@__in.applicationNames"""' #txt
Ie0 f3 exceptionHandler 14E8716736559EDB-f10-buffer #txt
Ie0 f3 timeoutExceptionHandler 14E8716736559EDB-f10-buffer #txt
Ie0 f3 returningObjectList '[wsResponse]' #txt
Ie0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Call IsAliveService
with NTLM Override
and Global Variable Settings</name>
        <nameStyle>20,7
47,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ie0 f3 78 204 36 24 20 -2 #rect
Ie0 f3 @|WebServiceIcon #fIcon
Ie0 f5 expr out #txt
Ie0 f5 96 77 96 114 #arcP
Ie0 f6 expr in #txt
Ie0 f6 outCond in.server.isNTLMAuthentication #txt
Ie0 f6 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>yes</name>
        <nameStyle>3,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ie0 f6 96 142 96 204 #arcP
Ie0 f7 expr out #txt
Ie0 f7 96 228 96 307 #arcP
Ie0 f10 .resExport export #txt
Ie0 f10 actionDecl 'ch.ivy.add.portalkit.service.integrators.IsAliveServiceIntegratorData out;
' #txt
Ie0 f10 actionTable 'out=in;
' #txt
Ie0 f10 actionCode 'import java.util.Calendar;
import ch.ivy.ws.addon.WsErrorType;
import ch.ivy.ws.addon.WsException;

WsException we;// = new WsException(WsErrorType.WARNING, 9999, exception, l, null);
we.errorCode = 11001;
we.errorType = WsErrorType.WARNING;
Calendar c = Calendar.getInstance();
c.setTime(new java.util.Date());
we.errorDateTime = c;
we.userText = ivy.cms.co("/errors/11001/userText", [in.server.name]);
we.server = in.server.name;
in.errors.add(we);' #txt
Ie0 f10 type ch.ivy.add.portalkit.service.integrators.IsAliveServiceIntegratorData #txt
Ie0 f10 errorCode unspecified #txt
Ie0 f10 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Exception Handler</name>
        <nameStyle>17,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ie0 f10 499 51 26 26 14 0 #rect
Ie0 f10 @|ExceptionIcon #fIcon
Ie0 f11 type ch.ivy.add.portalkit.service.integrators.IsAliveServiceIntegratorData #txt
Ie0 f11 499 203 26 26 14 0 #rect
Ie0 f11 @|EndSubIcon #fIcon
Ie0 f12 expr out #txt
Ie0 f12 512 77 512 203 #arcP
Ie0 f13 type ch.ivy.add.portalkit.service.integrators.IsAliveServiceIntegratorData #txt
Ie0 f13 actionDecl 'ch.ivy.add.portalkit.service.integrators.IsAliveServiceIntegratorData out;
' #txt
Ie0 f13 actionTable 'out=in;
out.applications=wsResponse.isAliveResponse.result.applicationList;
out.errors=wsResponse.isAliveResponse.result.errors;
out.isServerAlive=wsResponse.isAliveResponse.result.isAlive;
' #txt
Ie0 f13 cache '{/cache false /invalidation false /scope 0 /groupname ""/lifetime_group "0"/invalidation_time_group ""/identifier ""/lifetime_entry "0"/invalidation_time_entry ""}' #txt
Ie0 f13 timeout 10 #txt
Ie0 f13 beanConfig '"KEY_PASSWORD=
KEY_AXIS_PORTNAME=IsAliveServicePort
KEY_WEBSERVICECONFIG_ID=146C8FD0DD5620DA
KEY_DOMAIN=
KEY_USERNAME=
KEY_OPERATION=isAlive
KEY_AUTHENTICATION_KIND=0
KEY_HOST=
KEY_USE_AUTHENTICATION=false
KEY_AXIS_CSL_PARAMETER_DATA=""arg0.isAlive.apps__@@__Array<String>__@@__in.applicationNames"""' #txt
Ie0 f13 exceptionHandler 14E8716736559EDB-f10-buffer #txt
Ie0 f13 timeoutExceptionHandler 14E8716736559EDB-f10-buffer #txt
Ie0 f13 returningObjectList '[wsResponse]' #txt
Ie0 f13 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Call IsAliveService
with default settings</name>
        <nameStyle>20,7
21,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ie0 f13 270 204 36 24 20 -2 #rect
Ie0 f13 @|WebServiceIcon #fIcon
Ie0 f14 expr in #txt
Ie0 f14 110 128 288 204 #arcP
Ie0 f14 1 288 128 #addKink
Ie0 f14 0 0.7649209778540405 0 0 #arcLabel
Ie0 f4 expr out #txt
Ie0 f4 288 228 109 320 #arcP
Ie0 f4 1 288 320 #addKink
Ie0 f4 1 0.16236415866444226 0 0 #arcLabel
>Proto Ie0 .type ch.ivy.add.portalkit.service.integrators.IsAliveServiceIntegratorData #txt
>Proto Ie0 .processKind CALLABLE_SUB #txt
>Proto Ie0 0 0 32 24 18 0 #rect
>Proto Ie0 @|BIcon #fIcon
Ie0 f0 mainOut f5 tail #connect
Ie0 f5 head f2 in #connect
Ie0 f2 out f6 tail #connect
Ie0 f6 head f3 mainIn #connect
Ie0 f3 mainOut f7 tail #connect
Ie0 f7 head f1 mainIn #connect
Ie0 f10 mainOut f12 tail #connect
Ie0 f12 head f11 mainIn #connect
Ie0 f2 out f14 tail #connect
Ie0 f14 head f13 mainIn #connect
Ie0 f13 mainOut f4 tail #connect
Ie0 f4 head f1 mainIn #connect
