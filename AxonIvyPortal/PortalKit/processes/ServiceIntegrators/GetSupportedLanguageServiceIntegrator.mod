[Ivy]
[>Created: Mon Dec 28 11:09:02 ICT 2015]
14E9127DCD7A83EF 3.18 #module
>Proto >Proto Collection #zClass
Gr0 GetSupportedLanguageServiceIntegrator Big #zClass
Gr0 B #cInfo
Gr0 #process
Gr0 @TextInP .resExport .resExport #zField
Gr0 @TextInP .type .type #zField
Gr0 @TextInP .processKind .processKind #zField
Gr0 @AnnotationInP-0n ai ai #zField
Gr0 @TextInP .xml .xml #zField
Gr0 @TextInP .responsibility .responsibility #zField
Gr0 @StartSub f0 '' #zField
Gr0 @EndSub f1 '' #zField
Gr0 @WSElement f2 '' #zField
Gr0 @PushWFArc f4 '' #zField
Gr0 @Alternative f5 '' #zField
Gr0 @PushWFArc f6 '' #zField
Gr0 @PushWFArc f3 '' #zField
Gr0 @WSElement f7 '' #zField
Gr0 @PushWFArc f8 '' #zField
Gr0 @PushWFArc f9 '' #zField
Gr0 @ProcessException f10 '' #zField
Gr0 @EndSub f11 '' #zField
Gr0 @PushWFArc f12 '' #zField
>Proto Gr0 Gr0 GetSupportedLanguageServiceIntegrator #zField
Gr0 f0 inParamDecl '<ch.ivy.addon.portalkit.persistence.domain.Server server,java.lang.String endpoint,java.lang.String applicationName> param;' #txt
Gr0 f0 inParamTable 'out.applicationName=param.applicationName;
out.endpoint=param.endpoint;
out.server=param.server;
' #txt
Gr0 f0 outParamDecl '<List<java.lang.String> supportedLanguages> result;
' #txt
Gr0 f0 outParamTable 'result.supportedLanguages=in.supportedLanguages;
' #txt
Gr0 f0 actionDecl 'ch.ivy.add.portalkit.service.integrators.GetSupportedLanguageIntegratorData out;
' #txt
Gr0 f0 callSignature getSupportedLanguages(ch.ivy.addon.portalkit.persistence.domain.Server,String,String) #txt
Gr0 f0 type ch.ivy.add.portalkit.service.integrators.GetSupportedLanguageIntegratorData #txt
Gr0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>getSupportedLanguages(Server,String)</name>
        <nameStyle>36,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Gr0 f0 83 51 26 26 14 0 #rect
Gr0 f0 @|StartSubIcon #fIcon
Gr0 f1 type ch.ivy.add.portalkit.service.integrators.GetSupportedLanguageIntegratorData #txt
Gr0 f1 83 307 26 26 14 0 #rect
Gr0 f1 @|EndSubIcon #fIcon
Gr0 f2 type ch.ivy.add.portalkit.service.integrators.GetSupportedLanguageIntegratorData #txt
Gr0 f2 actionDecl 'ch.ivy.add.portalkit.service.integrators.GetSupportedLanguageIntegratorData out;
' #txt
Gr0 f2 actionTable 'out=in;
out.supportedLanguages=wsResponse.getSupportedLanguagesResponse.result.supportedLanguages;
out.wsExceptions=wsResponse.getSupportedLanguagesResponse.result.errors;
' #txt
Gr0 f2 testData 'in.applicationName="asdjkas";
' #txt
Gr0 f2 cache '{/cache false /invalidation false /scope 0 /groupname ""/lifetime_group "0"/invalidation_time_group ""/identifier ""/lifetime_entry "0"/invalidation_time_entry ""}' #txt
Gr0 f2 timeout 0 #txt
Gr0 f2 beanConfig '"KEY_PASSWORD=<%\\=ivy.var.PortalWSPassword%>
KEY_AXIS_PORTNAME=SupportedLanguageServicePort
KEY_WEBSERVICECONFIG_ID=14E91368EA389FD7
KEY_DOMAIN=<%\\=ivy.var.PortalWSDomain%>
KEY_USERNAME=<%\\=ivy.var.PortalWSUsername%>
KEY_OPERATION=getSupportedLanguages
KEY_AUTHENTICATION_KIND=4
KEY_HOST=<%\\=ivy.var.PortalWSHost%>
KEY_USE_AUTHENTICATION=true
KEY_AXIS_CSL_PARAMETER_DATA=""arg0.getSupportedLanguages.applicationName__@@__String__@@__in.applicationName"""' #txt
Gr0 f2 exceptionHandler 14E9127DCD7A83EF-f10-buffer #txt
Gr0 f2 returningObjectList '[wsResponse]' #txt
Gr0 f2 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Call getSupportedLanguages
with NTLM Override
and Global Variable Settings</name>
        <nameStyle>74,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Gr0 f2 78 212 36 24 20 -2 #rect
Gr0 f2 @|WebServiceIcon #fIcon
Gr0 f4 expr out #txt
Gr0 f4 96 236 96 307 #arcP
Gr0 f5 type ch.ivy.add.portalkit.service.integrators.GetSupportedLanguageIntegratorData #txt
Gr0 f5 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>is 
NTLMAuthentication?</name>
        <nameStyle>23,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Gr0 f5 82 114 28 28 8 9 #rect
Gr0 f5 @|AlternativeIcon #fIcon
Gr0 f6 expr out #txt
Gr0 f6 96 77 96 114 #arcP
Gr0 f3 expr in #txt
Gr0 f3 outCond in.server.isNTLMAuthentication #txt
Gr0 f3 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>yes</name>
        <nameStyle>3,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Gr0 f3 96 142 96 212 #arcP
Gr0 f3 0 0.2 -12 0 #arcLabel
Gr0 f7 type ch.ivy.add.portalkit.service.integrators.GetSupportedLanguageIntegratorData #txt
Gr0 f7 actionDecl 'ch.ivy.add.portalkit.service.integrators.GetSupportedLanguageIntegratorData out;
' #txt
Gr0 f7 actionTable 'out=in;
out.supportedLanguages=wsResponse.getSupportedLanguagesResponse.result.supportedLanguages;
out.wsExceptions=wsResponse.getSupportedLanguagesResponse.result.errors;
' #txt
Gr0 f7 cache '{/cache false /invalidation false /scope 0 /groupname ""/lifetime_group "0"/invalidation_time_group ""/identifier ""/lifetime_entry "0"/invalidation_time_entry ""}' #txt
Gr0 f7 timeout 0 #txt
Gr0 f7 beanConfig '"KEY_PASSWORD=
KEY_AXIS_PORTNAME=SupportedLanguageServicePort
KEY_WEBSERVICECONFIG_ID=14E91368EA389FD7
KEY_DOMAIN=
KEY_USERNAME=
KEY_OPERATION=getSupportedLanguages
KEY_AUTHENTICATION_KIND=0
KEY_HOST=
KEY_USE_AUTHENTICATION=false
KEY_AXIS_CSL_PARAMETER_DATA=""arg0.getSupportedLanguages.applicationName__@@__String__@@__in.applicationName"""' #txt
Gr0 f7 exceptionHandler 14E9127DCD7A83EF-f10-buffer #txt
Gr0 f7 returningObjectList '[wsResponse]' #txt
Gr0 f7 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Call getSupportedLanguages
with default settings</name>
        <nameStyle>48,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Gr0 f7 334 212 36 24 21 -2 #rect
Gr0 f7 @|WebServiceIcon #fIcon
Gr0 f8 expr in #txt
Gr0 f8 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>no</name>
        <nameStyle>2,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Gr0 f8 110 128 352 212 #arcP
Gr0 f8 1 352 128 #addKink
Gr0 f8 0 0.3153846153846154 0 -10 #arcLabel
Gr0 f9 expr out #txt
Gr0 f9 352 236 109 320 #arcP
Gr0 f9 1 352 320 #addKink
Gr0 f9 0 0.985529147231887 0 0 #arcLabel
Gr0 f10 .resExport export #txt
Gr0 f10 actionDecl 'ch.ivy.add.portalkit.service.integrators.GetSupportedLanguageIntegratorData out;
' #txt
Gr0 f10 actionTable 'out=in;
' #txt
Gr0 f10 actionCode ivy.log.error(in.wsExceptions); #txt
Gr0 f10 type ch.ivy.add.portalkit.service.integrators.GetSupportedLanguageIntegratorData #txt
Gr0 f10 errorCode unspecified #txt
Gr0 f10 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>exception handling</name>
        <nameStyle>18,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Gr0 f10 627 43 26 26 14 0 #rect
Gr0 f10 @|ExceptionIcon #fIcon
Gr0 f11 type ch.ivy.add.portalkit.service.integrators.GetSupportedLanguageIntegratorData #txt
Gr0 f11 627 171 26 26 14 0 #rect
Gr0 f11 @|EndSubIcon #fIcon
Gr0 f12 expr out #txt
Gr0 f12 640 69 640 171 #arcP
>Proto Gr0 .type ch.ivy.add.portalkit.service.integrators.GetSupportedLanguageIntegratorData #txt
>Proto Gr0 .processKind CALLABLE_SUB #txt
>Proto Gr0 0 0 32 24 18 0 #rect
>Proto Gr0 @|BIcon #fIcon
Gr0 f2 mainOut f4 tail #connect
Gr0 f4 head f1 mainIn #connect
Gr0 f0 mainOut f6 tail #connect
Gr0 f6 head f5 in #connect
Gr0 f5 out f3 tail #connect
Gr0 f3 head f2 mainIn #connect
Gr0 f5 out f8 tail #connect
Gr0 f8 head f7 mainIn #connect
Gr0 f7 mainOut f9 tail #connect
Gr0 f9 head f1 mainIn #connect
Gr0 f10 mainOut f12 tail #connect
Gr0 f12 head f11 mainIn #connect
