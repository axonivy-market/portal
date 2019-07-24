[Ivy]
14E9127DCD7A83EF 3.20 #module
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
Gr0 @PushWFArc f3 '' #zField
Gr0 @WSElement f7 '' #zField
Gr0 @PushWFArc f9 '' #zField
Gr0 @ProcessException f10 '' #zField
Gr0 @EndSub f11 '' #zField
Gr0 @PushWFArc f12 '' #zField
Gr0 @PushWFArc f15 '' #zField
Gr0 @WSElement f6 '' #zField
Gr0 @PushWFArc f13 '' #zField
Gr0 @PushWFArc f8 '' #zField
Gr0 @PushWFArc f14 '' #zField
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
Gr0 f0 211 51 26 26 14 0 #rect
Gr0 f0 @|StartSubIcon #fIcon
Gr0 f1 type ch.ivy.add.portalkit.service.integrators.GetSupportedLanguageIntegratorData #txt
Gr0 f1 211 339 26 26 14 0 #rect
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
Gr0 f2 timeout 60 #txt
Gr0 f2 beanConfig '"KEY_PASSWORD=<%\\=in.server.password%>
KEY_AXIS_PORTNAME=SupportedLanguageServicePort
KEY_WEBSERVICECONFIG_ID=14E91368EA389FD7
KEY_DOMAIN=<%\\=in.server.domain%>
KEY_REQUEST_PARAMETER_MAPPINGS_OPTIONS_AUTO_INITIALIZE_FIRST_LEVEL_FIELDS=true
KEY_REQUEST_PARAMETER_MAPPINGS_OPTIONS_MAP_NULL_VALUES=false
KEY_USERNAME=<%\\=in.server.username%>
KEY_OPERATION=getSupportedLanguages
KEY_AUTHENTICATION_KIND=4
KEY_HOST=<%\\=in.server.host%>
KEY_USE_AUTHENTICATION=true
KEY_AXIS_CSL_PARAMETER_DATA=""arg0.getSupportedLanguages.applicationName__@@__String__@@__in.applicationName"""' #txt
Gr0 f2 exceptionHandler 14E9127DCD7A83EF-f10-buffer #txt
Gr0 f2 returningObjectList '[wsResponse]' #txt
Gr0 f2 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>NTLM</name>
        <nameStyle>4,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Gr0 f2 206 244 36 24 20 -2 #rect
Gr0 f2 @|WebServiceIcon #fIcon
Gr0 f4 expr out #txt
Gr0 f4 224 268 224 339 #arcP
Gr0 f5 type ch.ivy.add.portalkit.service.integrators.GetSupportedLanguageIntegratorData #txt
Gr0 f5 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Authentication Type?</name>
        <nameStyle>20,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Gr0 f5 210 146 28 28 12 -18 #rect
Gr0 f5 @|AlternativeIcon #fIcon
Gr0 f3 expr in #txt
Gr0 f3 outCond 'ch.ivy.addon.portalkit.enums.WSAuthenticationType.NTLM == in.server.wsAuthenticationType' #txt
Gr0 f3 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name></name>
    </language>
</elementInfo>
' #txt
Gr0 f3 224 174 224 244 #arcP
Gr0 f3 0 0.2 -12 0 #arcLabel
Gr0 f7 type ch.ivy.add.portalkit.service.integrators.GetSupportedLanguageIntegratorData #txt
Gr0 f7 actionDecl 'ch.ivy.add.portalkit.service.integrators.GetSupportedLanguageIntegratorData out;
' #txt
Gr0 f7 actionTable 'out=in;
out.supportedLanguages=wsResponse.getSupportedLanguagesResponse.result.supportedLanguages;
out.wsExceptions=wsResponse.getSupportedLanguagesResponse.result.errors;
' #txt
Gr0 f7 cache '{/cache false /invalidation false /scope 0 /groupname ""/lifetime_group "0"/invalidation_time_group ""/identifier ""/lifetime_entry "0"/invalidation_time_entry ""}' #txt
Gr0 f7 timeout 60 #txt
Gr0 f7 beanConfig '"KEY_PASSWORD=
KEY_AXIS_PORTNAME=SupportedLanguageServicePort
KEY_WEBSERVICECONFIG_ID=14E91368EA389FD7
KEY_DOMAIN=
KEY_REQUEST_PARAMETER_MAPPINGS_OPTIONS_AUTO_INITIALIZE_FIRST_LEVEL_FIELDS=true
KEY_REQUEST_PARAMETER_MAPPINGS_OPTIONS_MAP_NULL_VALUES=false
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
        <name>default settings</name>
        <nameStyle>16,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Gr0 f7 366 244 36 24 21 -2 #rect
Gr0 f7 @|WebServiceIcon #fIcon
Gr0 f9 expr out #txt
Gr0 f9 384 268 237 352 #arcP
Gr0 f9 1 384 352 #addKink
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
Gr0 f15 expr out #txt
Gr0 f15 224 77 224 146 #arcP
Gr0 f6 type ch.ivy.add.portalkit.service.integrators.GetSupportedLanguageIntegratorData #txt
Gr0 f6 actionDecl 'ch.ivy.add.portalkit.service.integrators.GetSupportedLanguageIntegratorData out;
' #txt
Gr0 f6 actionTable 'out=in;
out.supportedLanguages=wsResponse.getSupportedLanguagesResponse.result.supportedLanguages;
out.wsExceptions=wsResponse.getSupportedLanguagesResponse.result.errors;
' #txt
Gr0 f6 testData 'in.applicationName="asdjkas";
' #txt
Gr0 f6 cache '{/cache false /invalidation false /scope 0 /groupname ""/lifetime_group "0"/invalidation_time_group ""/identifier ""/lifetime_entry "0"/invalidation_time_entry ""}' #txt
Gr0 f6 timeout 60 #txt
Gr0 f6 beanConfig '"KEY_PASSWORD=<%\\=in.server.password%>
KEY_AXIS_PORTNAME=SupportedLanguageServicePort
KEY_WEBSERVICECONFIG_ID=14E91368EA389FD7
KEY_DOMAIN=
KEY_REQUEST_PARAMETER_MAPPINGS_OPTIONS_AUTO_INITIALIZE_FIRST_LEVEL_FIELDS=true
KEY_REQUEST_PARAMETER_MAPPINGS_OPTIONS_MAP_NULL_VALUES=false
KEY_USERNAME=<%\\=in.server.username%>
KEY_OPERATION=getSupportedLanguages
KEY_AUTHENTICATION_KIND=1
KEY_HOST=
KEY_USE_AUTHENTICATION=true
KEY_AXIS_CSL_PARAMETER_DATA=""arg0.getSupportedLanguages.applicationName__@@__String__@@__in.applicationName"""' #txt
Gr0 f6 exceptionHandler 14E9127DCD7A83EF-f10-buffer #txt
Gr0 f6 returningObjectList '[wsResponse]' #txt
Gr0 f6 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>HTTP Basic</name>
        <nameStyle>10,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Gr0 f6 46 244 36 24 20 -2 #rect
Gr0 f6 @|WebServiceIcon #fIcon
Gr0 f13 expr in #txt
Gr0 f13 outCond 'ch.ivy.addon.portalkit.enums.WSAuthenticationType.HTTP_BASIC == in.server.wsAuthenticationType' #txt
Gr0 f13 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name></name>
    </language>
</elementInfo>
' #txt
Gr0 f13 210 160 64 244 #arcP
Gr0 f13 1 64 160 #addKink
Gr0 f13 0 0.3150684931506849 0 10 #arcLabel
Gr0 f8 expr out #txt
Gr0 f8 64 268 211 352 #arcP
Gr0 f8 1 64 352 #addKink
Gr0 f8 1 0.13542290994670902 0 0 #arcLabel
Gr0 f14 expr in #txt
Gr0 f14 238 160 384 244 #arcP
Gr0 f14 1 384 160 #addKink
Gr0 f14 0 0.7875410210393261 0 0 #arcLabel
>Proto Gr0 .type ch.ivy.add.portalkit.service.integrators.GetSupportedLanguageIntegratorData #txt
>Proto Gr0 .processKind CALLABLE_SUB #txt
>Proto Gr0 0 0 32 24 18 0 #rect
>Proto Gr0 @|BIcon #fIcon
Gr0 f2 mainOut f4 tail #connect
Gr0 f4 head f1 mainIn #connect
Gr0 f5 out f3 tail #connect
Gr0 f3 head f2 mainIn #connect
Gr0 f7 mainOut f9 tail #connect
Gr0 f9 head f1 mainIn #connect
Gr0 f10 mainOut f12 tail #connect
Gr0 f12 head f11 mainIn #connect
Gr0 f0 mainOut f15 tail #connect
Gr0 f15 head f5 in #connect
Gr0 f5 out f13 tail #connect
Gr0 f13 head f6 mainIn #connect
Gr0 f6 mainOut f8 tail #connect
Gr0 f8 head f1 mainIn #connect
Gr0 f5 out f14 tail #connect
Gr0 f14 head f7 mainIn #connect
