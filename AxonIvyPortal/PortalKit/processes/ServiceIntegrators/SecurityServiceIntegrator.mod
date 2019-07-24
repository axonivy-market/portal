[Ivy]
14E8AD127675284A 3.20 #module
>Proto >Proto Collection #zClass
Se0 SecurityServiceIntegrator Big #zClass
Se0 B #cInfo
Se0 #process
Se0 @TextInP .resExport .resExport #zField
Se0 @TextInP .type .type #zField
Se0 @TextInP .processKind .processKind #zField
Se0 @AnnotationInP-0n ai ai #zField
Se0 @TextInP .xml .xml #zField
Se0 @TextInP .responsibility .responsibility #zField
Se0 @Alternative f58 '' #zField
Se0 @EndSub f57 '' #zField
Se0 @WSElement f60 '' #zField
Se0 @StartSub f59 '' #zField
Se0 @PushWFArc f62 '' #zField
Se0 @PushWFArc f63 '' #zField
Se0 @WSElement f0 '' #zField
Se0 @PushWFArc f2 '' #zField
Se0 @ProcessException f3 '' #zField
Se0 @EndSub f4 '' #zField
Se0 @PushWFArc f5 '' #zField
Se0 @WSElement f6 '' #zField
Se0 @StartSub f7 '' #zField
Se0 @Alternative f8 '' #zField
Se0 @EndSub f9 '' #zField
Se0 @WSElement f10 '' #zField
Se0 @PushWFArc f12 '' #zField
Se0 @PushWFArc f13 '' #zField
Se0 @PushWFArc f15 '' #zField
Se0 @Alternative f16 '' #zField
Se0 @EndSub f17 '' #zField
Se0 @WSElement f18 '' #zField
Se0 @WSElement f19 '' #zField
Se0 @StartSub f20 '' #zField
Se0 @PushWFArc f22 '' #zField
Se0 @PushWFArc f23 '' #zField
Se0 @PushWFArc f25 '' #zField
Se0 @WSElement f33 '' #zField
Se0 @PushWFArc f34 '' #zField
Se0 @PushWFArc f26 '' #zField
Se0 @PushWFArc f27 '' #zField
Se0 @PushWFArc f1 '' #zField
Se0 @PushWFArc f28 '' #zField
Se0 @WSElement f11 '' #zField
Se0 @PushWFArc f29 '' #zField
Se0 @PushWFArc f14 '' #zField
Se0 @PushWFArc f30 '' #zField
Se0 @PushWFArc f35 '' #zField
Se0 @WSElement f21 '' #zField
Se0 @PushWFArc f31 '' #zField
Se0 @PushWFArc f24 '' #zField
Se0 @PushWFArc f32 '' #zField
>Proto Se0 Se0 SecurityServiceIntegrator #zField
Se0 f58 type ch.ivy.add.portalkit.service.integrators.SecurityServiceIntegratorData #txt
Se0 f58 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Authentication Type?</name>
        <nameStyle>20,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Se0 f58 242 146 28 28 14 -22 #rect
Se0 f58 @|AlternativeIcon #fIcon
Se0 f57 type ch.ivy.add.portalkit.service.integrators.SecurityServiceIntegratorData #txt
Se0 f57 243 339 26 26 14 0 #rect
Se0 f57 @|EndSubIcon #fIcon
Se0 f60 type ch.ivy.add.portalkit.service.integrators.SecurityServiceIntegratorData #txt
Se0 f60 actionDecl 'ch.ivy.add.portalkit.service.integrators.SecurityServiceIntegratorData out;
' #txt
Se0 f60 actionTable 'out=in;
out.errors=wsResponse.findAllRolesResponse.result.errors;
out.roles=wsResponse.findAllRolesResponse.result.roles;
' #txt
Se0 f60 cache '{/cache false /invalidation false /scope 0 /groupname ""/lifetime_group "0"/invalidation_time_group ""/identifier ""/lifetime_entry "0"/invalidation_time_entry ""}' #txt
Se0 f60 timeout 60 #txt
Se0 f60 beanConfig '"KEY_PASSWORD=<%\\=in.server.password%>
KEY_AXIS_PORTNAME=SecurityServicePort
KEY_WEBSERVICECONFIG_ID=1485F2F441BFB375
KEY_DOMAIN=<%\\=in.server.domain%>
KEY_REQUEST_PARAMETER_MAPPINGS_OPTIONS_AUTO_INITIALIZE_FIRST_LEVEL_FIELDS=true
KEY_REQUEST_PARAMETER_MAPPINGS_OPTIONS_MAP_NULL_VALUES=false
KEY_USERNAME=<%\\=in.server.username%>
KEY_OPERATION=findAllRoles
KEY_AUTHENTICATION_KIND=4
KEY_HOST=<%\\=in.server.host%>
KEY_USE_AUTHENTICATION=true
KEY_AXIS_CSL_PARAMETER_DATA=""arg0.findAllRoles.apps__@@__Array<String>__@@__in.applicationNames"""' #txt
Se0 f60 exceptionHandler 14E8AD127675284A-f3-buffer #txt
Se0 f60 timeoutExceptionHandler 14E8AD127675284A-f3-buffer #txt
Se0 f60 returningObjectList '[wsResponse]' #txt
Se0 f60 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>NTLM</name>
        <nameStyle>4,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Se0 f60 238 244 36 24 20 -2 #rect
Se0 f60 @|WebServiceIcon #fIcon
Se0 f59 inParamDecl '<java.lang.String endpoint,List<java.lang.String> applicationNames,ch.ivy.addon.portalkit.persistence.domain.Server server> param;' #txt
Se0 f59 inParamTable 'out.applicationNames=param.applicationNames;
out.endpoint=param.endpoint;
out.server=param.server;
' #txt
Se0 f59 outParamDecl '<List<ch.ivy.ws.addon.WsException> errors,List<ch.ivy.ws.addon.IvyRole> roles> result;
' #txt
Se0 f59 outParamTable 'result.errors=in.errors;
result.roles=in.roles;
' #txt
Se0 f59 actionDecl 'ch.ivy.add.portalkit.service.integrators.SecurityServiceIntegratorData out;
' #txt
Se0 f59 callSignature findAllRoles(String,List<String>,ch.ivy.addon.portalkit.persistence.domain.Server) #txt
Se0 f59 type ch.ivy.add.portalkit.service.integrators.SecurityServiceIntegratorData #txt
Se0 f59 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>findAllRoles(String,List&lt;String&gt;,Server)</name>
        <nameStyle>40,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Se0 f59 243 51 26 26 14 0 #rect
Se0 f59 @|StartSubIcon #fIcon
Se0 f62 expr in #txt
Se0 f62 outCond 'ch.ivy.addon.portalkit.enums.WSAuthenticationType.NTLM == in.server.wsAuthenticationType' #txt
Se0 f62 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name></name>
    </language>
</elementInfo>
' #txt
Se0 f62 256 174 256 244 #arcP
Se0 f62 0 0.5170769821034609 0 0 #arcLabel
Se0 f63 expr out #txt
Se0 f63 256 268 256 339 #arcP
Se0 f0 type ch.ivy.add.portalkit.service.integrators.SecurityServiceIntegratorData #txt
Se0 f0 actionDecl 'ch.ivy.add.portalkit.service.integrators.SecurityServiceIntegratorData out;
' #txt
Se0 f0 actionTable 'out=in;
out.errors=wsResponse.findAllRolesResponse.result.errors;
out.roles=wsResponse.findAllRolesResponse.result.roles;
' #txt
Se0 f0 cache '{/cache false /invalidation false /scope 0 /groupname ""/lifetime_group "0"/invalidation_time_group ""/identifier ""/lifetime_entry "0"/invalidation_time_entry ""}' #txt
Se0 f0 timeout 60 #txt
Se0 f0 beanConfig '"KEY_PASSWORD=
KEY_AXIS_PORTNAME=SecurityServicePort
KEY_WEBSERVICECONFIG_ID=1485F2F441BFB375
KEY_DOMAIN=
KEY_REQUEST_PARAMETER_MAPPINGS_OPTIONS_AUTO_INITIALIZE_FIRST_LEVEL_FIELDS=true
KEY_REQUEST_PARAMETER_MAPPINGS_OPTIONS_MAP_NULL_VALUES=false
KEY_USERNAME=
KEY_OPERATION=findAllRoles
KEY_AUTHENTICATION_KIND=0
KEY_HOST=
KEY_USE_AUTHENTICATION=false
KEY_AXIS_CSL_PARAMETER_DATA=""arg0.findAllRoles.apps__@@__Array<String>__@@__in.applicationNames"""' #txt
Se0 f0 exceptionHandler 14E8AD127675284A-f3-buffer #txt
Se0 f0 timeoutExceptionHandler 14E8AD127675284A-f3-buffer #txt
Se0 f0 returningObjectList '[wsResponse]' #txt
Se0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>default settings</name>
        <nameStyle>16,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Se0 f0 398 244 36 24 20 -2 #rect
Se0 f0 @|WebServiceIcon #fIcon
Se0 f2 expr out #txt
Se0 f2 416 268 269 352 #arcP
Se0 f2 1 416 352 #addKink
Se0 f2 1 0.2604287685518714 0 0 #arcLabel
Se0 f3 .resExport export #txt
Se0 f3 actionDecl 'ch.ivy.add.portalkit.service.integrators.SecurityServiceIntegratorData out;
' #txt
Se0 f3 actionTable 'out=in;
' #txt
Se0 f3 actionCode ivy.log.error(exception); #txt
Se0 f3 type ch.ivy.add.portalkit.service.integrators.SecurityServiceIntegratorData #txt
Se0 f3 errorCode unspecified #txt
Se0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Exception Handler</name>
        <nameStyle>17,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Se0 f3 83 1219 26 26 14 0 #rect
Se0 f3 @|ExceptionIcon #fIcon
Se0 f4 type ch.ivy.add.portalkit.service.integrators.SecurityServiceIntegratorData #txt
Se0 f4 83 1347 26 26 14 0 #rect
Se0 f4 @|EndSubIcon #fIcon
Se0 f5 expr out #txt
Se0 f5 96 1245 96 1347 #arcP
Se0 f6 type ch.ivy.add.portalkit.service.integrators.SecurityServiceIntegratorData #txt
Se0 f6 actionDecl 'ch.ivy.add.portalkit.service.integrators.SecurityServiceIntegratorData out;
' #txt
Se0 f6 actionTable 'out=in;
out.errors=wsResponse.findAllUsersResponse.result.errors;
out.users=wsResponse.findAllUsersResponse.result.users;
' #txt
Se0 f6 cache '{/cache false /invalidation false /scope 0 /groupname ""/lifetime_group "0"/invalidation_time_group ""/identifier ""/lifetime_entry "0"/invalidation_time_entry ""}' #txt
Se0 f6 timeout 60 #txt
Se0 f6 beanConfig '"KEY_PASSWORD=
KEY_AXIS_PORTNAME=SecurityServicePort
KEY_WEBSERVICECONFIG_ID=1485F2F441BFB375
KEY_DOMAIN=
KEY_REQUEST_PARAMETER_MAPPINGS_OPTIONS_AUTO_INITIALIZE_FIRST_LEVEL_FIELDS=true
KEY_REQUEST_PARAMETER_MAPPINGS_OPTIONS_MAP_NULL_VALUES=false
KEY_USERNAME=
KEY_OPERATION=findAllUsers
KEY_AUTHENTICATION_KIND=0
KEY_HOST=
KEY_USE_AUTHENTICATION=false
KEY_AXIS_CSL_PARAMETER_DATA=""arg0.findAllUsers.apps__@@__Array<String>__@@__in.applicationNames"""' #txt
Se0 f6 exceptionHandler 14E8AD127675284A-f3-buffer #txt
Se0 f6 timeoutExceptionHandler 14E8AD127675284A-f3-buffer #txt
Se0 f6 returningObjectList '[wsResponse]' #txt
Se0 f6 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>default settings</name>
        <nameStyle>16,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Se0 f6 398 628 36 24 20 -2 #rect
Se0 f6 @|WebServiceIcon #fIcon
Se0 f7 inParamDecl '<java.lang.String endpoint,List<java.lang.String> applicationNames,ch.ivy.addon.portalkit.persistence.domain.Server server> param;' #txt
Se0 f7 inParamTable 'out.applicationNames=param.applicationNames;
out.endpoint=param.endpoint;
out.server=param.server;
' #txt
Se0 f7 outParamDecl '<List<ch.ivy.ws.addon.WsException> errors,List<ch.ivy.ws.addon.IvyUser> users> result;
' #txt
Se0 f7 outParamTable 'result.errors=in.errors;
result.users=in.users;
' #txt
Se0 f7 actionDecl 'ch.ivy.add.portalkit.service.integrators.SecurityServiceIntegratorData out;
' #txt
Se0 f7 callSignature findAllUsers(String,List<String>,ch.ivy.addon.portalkit.persistence.domain.Server) #txt
Se0 f7 type ch.ivy.add.portalkit.service.integrators.SecurityServiceIntegratorData #txt
Se0 f7 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>findAllUsers(String,List&lt;String&gt;,Server)</name>
        <nameStyle>40,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Se0 f7 243 435 26 26 14 0 #rect
Se0 f7 @|StartSubIcon #fIcon
Se0 f8 type ch.ivy.add.portalkit.service.integrators.SecurityServiceIntegratorData #txt
Se0 f8 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Authentication Type?</name>
        <nameStyle>20,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Se0 f8 242 530 28 28 14 -22 #rect
Se0 f8 @|AlternativeIcon #fIcon
Se0 f9 type ch.ivy.add.portalkit.service.integrators.SecurityServiceIntegratorData #txt
Se0 f9 243 723 26 26 14 0 #rect
Se0 f9 @|EndSubIcon #fIcon
Se0 f10 type ch.ivy.add.portalkit.service.integrators.SecurityServiceIntegratorData #txt
Se0 f10 actionDecl 'ch.ivy.add.portalkit.service.integrators.SecurityServiceIntegratorData out;
' #txt
Se0 f10 actionTable 'out=in;
out.errors=wsResponse.findAllUsersResponse.result.errors;
out.users=wsResponse.findAllUsersResponse.result.users;
' #txt
Se0 f10 cache '{/cache false /invalidation false /scope 0 /groupname ""/lifetime_group "0"/invalidation_time_group ""/identifier ""/lifetime_entry "0"/invalidation_time_entry ""}' #txt
Se0 f10 timeout 60 #txt
Se0 f10 beanConfig '"KEY_PASSWORD=<%\\=in.server.password%>
KEY_AXIS_PORTNAME=SecurityServicePort
KEY_WEBSERVICECONFIG_ID=1485F2F441BFB375
KEY_DOMAIN=<%\\=in.server.domain%>
KEY_REQUEST_PARAMETER_MAPPINGS_OPTIONS_AUTO_INITIALIZE_FIRST_LEVEL_FIELDS=true
KEY_REQUEST_PARAMETER_MAPPINGS_OPTIONS_MAP_NULL_VALUES=false
KEY_USERNAME=<%\\=in.server.username%>
KEY_OPERATION=findAllUsers
KEY_AUTHENTICATION_KIND=4
KEY_HOST=<%\\=in.server.host%>
KEY_USE_AUTHENTICATION=true
KEY_AXIS_CSL_PARAMETER_DATA=""arg0.findAllUsers.apps__@@__Array<String>__@@__in.applicationNames"""' #txt
Se0 f10 exceptionHandler 14E8AD127675284A-f3-buffer #txt
Se0 f10 timeoutExceptionHandler 14E8AD127675284A-f3-buffer #txt
Se0 f10 returningObjectList '[wsResponse]' #txt
Se0 f10 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>NTLM</name>
        <nameStyle>4,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Se0 f10 238 628 36 24 19 -1 #rect
Se0 f10 @|WebServiceIcon #fIcon
Se0 f12 expr in #txt
Se0 f12 outCond 'ch.ivy.addon.portalkit.enums.WSAuthenticationType.NTLM == in.server.wsAuthenticationType' #txt
Se0 f12 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name></name>
    </language>
</elementInfo>
' #txt
Se0 f12 256 558 256 628 #arcP
Se0 f13 expr out #txt
Se0 f13 256 652 256 723 #arcP
Se0 f15 expr out #txt
Se0 f15 416 652 269 736 #arcP
Se0 f15 1 416 736 #addKink
Se0 f15 1 0.2592987781715275 0 0 #arcLabel
Se0 f16 type ch.ivy.add.portalkit.service.integrators.SecurityServiceIntegratorData #txt
Se0 f16 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Authentication Type?</name>
        <nameStyle>20,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Se0 f16 242 914 28 28 14 -22 #rect
Se0 f16 @|AlternativeIcon #fIcon
Se0 f17 type ch.ivy.add.portalkit.service.integrators.SecurityServiceIntegratorData #txt
Se0 f17 243 1107 26 26 14 0 #rect
Se0 f17 @|EndSubIcon #fIcon
Se0 f18 type ch.ivy.add.portalkit.service.integrators.SecurityServiceIntegratorData #txt
Se0 f18 actionDecl 'ch.ivy.add.portalkit.service.integrators.SecurityServiceIntegratorData out;
' #txt
Se0 f18 actionTable 'out=in;
out.errors=wsResponse.findSecurityMembersToDelegateResponse.result.errors;
out.roles=wsResponse.findSecurityMembersToDelegateResponse.result.roles;
out.users=wsResponse.findSecurityMembersToDelegateResponse.result.users;
' #txt
Se0 f18 cache '{/cache false /invalidation false /scope 0 /groupname ""/lifetime_group "0"/invalidation_time_group ""/identifier ""/lifetime_entry "0"/invalidation_time_entry ""}' #txt
Se0 f18 timeout 60 #txt
Se0 f18 beanConfig '"KEY_PASSWORD=
KEY_AXIS_PORTNAME=SecurityServicePort
KEY_WEBSERVICECONFIG_ID=1485F2F441BFB375
KEY_DOMAIN=
KEY_REQUEST_PARAMETER_MAPPINGS_OPTIONS_AUTO_INITIALIZE_FIRST_LEVEL_FIELDS=true
KEY_REQUEST_PARAMETER_MAPPINGS_OPTIONS_MAP_NULL_VALUES=false
KEY_USERNAME=
KEY_OPERATION=findSecurityMembersToDelegate
KEY_AUTHENTICATION_KIND=0
KEY_HOST=
KEY_USE_AUTHENTICATION=false
KEY_AXIS_CSL_PARAMETER_DATA=""arg0.findSecurityMembersToDelegate.taskID__@@__Long__@@__in.taskId"""' #txt
Se0 f18 exceptionHandler 14E8AD127675284A-f3-buffer #txt
Se0 f18 timeoutExceptionHandler 14E8AD127675284A-f3-buffer #txt
Se0 f18 returningObjectList '[wsResponse]' #txt
Se0 f18 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>default settings</name>
        <nameStyle>16,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Se0 f18 398 1012 36 24 20 -2 #rect
Se0 f18 @|WebServiceIcon #fIcon
Se0 f19 type ch.ivy.add.portalkit.service.integrators.SecurityServiceIntegratorData #txt
Se0 f19 actionDecl 'ch.ivy.add.portalkit.service.integrators.SecurityServiceIntegratorData out;
' #txt
Se0 f19 actionTable 'out=in;
out.errors=wsResponse.findSecurityMembersToDelegateResponse.result.errors;
out.roles=wsResponse.findSecurityMembersToDelegateResponse.result.roles;
out.users=wsResponse.findSecurityMembersToDelegateResponse.result.users;
' #txt
Se0 f19 cache '{/cache false /invalidation false /scope 0 /groupname ""/lifetime_group "0"/invalidation_time_group ""/identifier ""/lifetime_entry "0"/invalidation_time_entry ""}' #txt
Se0 f19 timeout 60 #txt
Se0 f19 beanConfig '"KEY_PASSWORD=<%\\=in.server.password%>
KEY_AXIS_PORTNAME=SecurityServicePort
KEY_WEBSERVICECONFIG_ID=1485F2F441BFB375
KEY_DOMAIN=<%\\=in.server.domain%>
KEY_REQUEST_PARAMETER_MAPPINGS_OPTIONS_AUTO_INITIALIZE_FIRST_LEVEL_FIELDS=true
KEY_REQUEST_PARAMETER_MAPPINGS_OPTIONS_MAP_NULL_VALUES=false
KEY_USERNAME=<%\\=in.server.username%>
KEY_OPERATION=findSecurityMembersToDelegate
KEY_AUTHENTICATION_KIND=4
KEY_HOST=<%\\=in.server.host%>
KEY_USE_AUTHENTICATION=true
KEY_AXIS_CSL_PARAMETER_DATA=""arg0.findSecurityMembersToDelegate.taskID__@@__Long__@@__in.taskId"""' #txt
Se0 f19 exceptionHandler 14E8AD127675284A-f3-buffer #txt
Se0 f19 timeoutExceptionHandler 14E8AD127675284A-f3-buffer #txt
Se0 f19 returningObjectList '[wsResponse]' #txt
Se0 f19 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>NTLM</name>
        <nameStyle>4,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Se0 f19 238 1012 36 24 20 -2 #rect
Se0 f19 @|WebServiceIcon #fIcon
Se0 f20 inParamDecl '<java.lang.Long taskId,java.lang.String endpoint,ch.ivy.addon.portalkit.persistence.domain.Server server> param;' #txt
Se0 f20 inParamTable 'out.endpoint=param.endpoint;
out.server=param.server;
out.taskId=param.taskId;
' #txt
Se0 f20 outParamDecl '<List<ch.ivy.ws.addon.IvyUser> users,List<ch.ivy.ws.addon.WsException> errors,List<ch.ivy.ws.addon.IvyRole> roles> result;
' #txt
Se0 f20 outParamTable 'result.users=in.users;
result.errors=in.errors;
result.roles=in.roles;
' #txt
Se0 f20 actionDecl 'ch.ivy.add.portalkit.service.integrators.SecurityServiceIntegratorData out;
' #txt
Se0 f20 callSignature findSecurityMembersToDelegate(Long,String,ch.ivy.addon.portalkit.persistence.domain.Server) #txt
Se0 f20 type ch.ivy.add.portalkit.service.integrators.SecurityServiceIntegratorData #txt
Se0 f20 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>findSecurityMembersToDelegate(String,Server)</name>
        <nameStyle>44,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Se0 f20 243 819 26 26 14 0 #rect
Se0 f20 @|StartSubIcon #fIcon
Se0 f22 expr in #txt
Se0 f22 outCond 'ch.ivy.addon.portalkit.enums.WSAuthenticationType.NTLM == in.server.wsAuthenticationType' #txt
Se0 f22 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>yes</name>
        <nameStyle>3,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Se0 f22 256 942 256 1012 #arcP
Se0 f23 expr out #txt
Se0 f23 256 1036 256 1107 #arcP
Se0 f25 expr out #txt
Se0 f25 416 1036 269 1120 #arcP
Se0 f25 1 416 1120 #addKink
Se0 f25 0 0.903041742646316 0 0 #arcLabel
Se0 f33 type ch.ivy.add.portalkit.service.integrators.SecurityServiceIntegratorData #txt
Se0 f33 actionDecl 'ch.ivy.add.portalkit.service.integrators.SecurityServiceIntegratorData out;
' #txt
Se0 f33 actionTable 'out=in;
out.errors=wsResponse.findAllRolesResponse.result.errors;
out.roles=wsResponse.findAllRolesResponse.result.roles;
' #txt
Se0 f33 cache '{/cache false /invalidation false /scope 0 /groupname ""/lifetime_group "0"/invalidation_time_group ""/identifier ""/lifetime_entry "0"/invalidation_time_entry ""}' #txt
Se0 f33 timeout 60 #txt
Se0 f33 beanConfig '"KEY_PASSWORD=<%\\=in.server.password%>
KEY_AXIS_PORTNAME=SecurityServicePort
KEY_WEBSERVICECONFIG_ID=1485F2F441BFB375
KEY_DOMAIN=
KEY_REQUEST_PARAMETER_MAPPINGS_OPTIONS_AUTO_INITIALIZE_FIRST_LEVEL_FIELDS=true
KEY_REQUEST_PARAMETER_MAPPINGS_OPTIONS_MAP_NULL_VALUES=false
KEY_USERNAME=<%\\=in.server.username%>
KEY_OPERATION=findAllRoles
KEY_AUTHENTICATION_KIND=1
KEY_HOST=
KEY_USE_AUTHENTICATION=true
KEY_AXIS_CSL_PARAMETER_DATA=""arg0.findAllRoles.apps__@@__Array<String>__@@__in.applicationNames"""' #txt
Se0 f33 exceptionHandler 14E8AD127675284A-f3-buffer #txt
Se0 f33 timeoutExceptionHandler 14E8AD127675284A-f3-buffer #txt
Se0 f33 returningObjectList '[wsResponse]' #txt
Se0 f33 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>HTTP Basic</name>
        <nameStyle>10,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Se0 f33 78 244 36 24 20 -2 #rect
Se0 f33 @|WebServiceIcon #fIcon
Se0 f34 expr out #txt
Se0 f34 256 77 256 146 #arcP
Se0 f26 expr out #txt
Se0 f26 96 268 243 352 #arcP
Se0 f26 1 96 352 #addKink
Se0 f26 1 0.1099110746629384 0 0 #arcLabel
Se0 f27 expr in #txt
Se0 f27 outCond 'ch.ivy.addon.portalkit.enums.WSAuthenticationType.HTTP_BASIC == in.server.wsAuthenticationType' #txt
Se0 f27 242 160 96 244 #arcP
Se0 f27 1 96 160 #addKink
Se0 f27 0 0.7302200350148694 0 0 #arcLabel
Se0 f1 expr in #txt
Se0 f1 270 160 416 244 #arcP
Se0 f1 1 416 160 #addKink
Se0 f1 0 0.6673343943931535 0 0 #arcLabel
Se0 f28 expr out #txt
Se0 f28 256 461 256 530 #arcP
Se0 f11 type ch.ivy.add.portalkit.service.integrators.SecurityServiceIntegratorData #txt
Se0 f11 actionDecl 'ch.ivy.add.portalkit.service.integrators.SecurityServiceIntegratorData out;
' #txt
Se0 f11 actionTable 'out=in;
out.errors=wsResponse.findAllUsersResponse.result.errors;
out.users=wsResponse.findAllUsersResponse.result.users;
' #txt
Se0 f11 cache '{/cache false /invalidation false /scope 0 /groupname ""/lifetime_group "0"/invalidation_time_group ""/identifier ""/lifetime_entry "0"/invalidation_time_entry ""}' #txt
Se0 f11 timeout 60 #txt
Se0 f11 beanConfig '"KEY_PASSWORD=<%\\=in.server.password%>
KEY_AXIS_PORTNAME=SecurityServicePort
KEY_WEBSERVICECONFIG_ID=1485F2F441BFB375
KEY_DOMAIN=
KEY_REQUEST_PARAMETER_MAPPINGS_OPTIONS_AUTO_INITIALIZE_FIRST_LEVEL_FIELDS=true
KEY_REQUEST_PARAMETER_MAPPINGS_OPTIONS_MAP_NULL_VALUES=false
KEY_USERNAME=<%\\=in.server.username%>
KEY_OPERATION=findAllUsers
KEY_AUTHENTICATION_KIND=1
KEY_HOST=
KEY_USE_AUTHENTICATION=true
KEY_AXIS_CSL_PARAMETER_DATA=""arg0.findAllUsers.apps__@@__Array<String>__@@__in.applicationNames"""' #txt
Se0 f11 exceptionHandler 14E8AD127675284A-f3-buffer #txt
Se0 f11 timeoutExceptionHandler 14E8AD127675284A-f3-buffer #txt
Se0 f11 returningObjectList '[wsResponse]' #txt
Se0 f11 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>HTTP Basic</name>
        <nameStyle>10,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Se0 f11 78 628 36 24 19 -1 #rect
Se0 f11 @|WebServiceIcon #fIcon
Se0 f29 expr in #txt
Se0 f29 outCond 'ch.ivy.addon.portalkit.enums.WSAuthenticationType.HTTP_BASIC == in.server.wsAuthenticationType' #txt
Se0 f29 242 544 96 628 #arcP
Se0 f29 1 96 544 #addKink
Se0 f29 0 0.8794328011923068 0 0 #arcLabel
Se0 f14 expr out #txt
Se0 f14 96 652 243 736 #arcP
Se0 f14 1 96 736 #addKink
Se0 f14 1 0.25582569530129295 0 0 #arcLabel
Se0 f30 expr in #txt
Se0 f30 270 544 416 628 #arcP
Se0 f30 1 416 544 #addKink
Se0 f30 0 0.7713744462412113 0 0 #arcLabel
Se0 f35 expr out #txt
Se0 f35 256 845 256 914 #arcP
Se0 f21 type ch.ivy.add.portalkit.service.integrators.SecurityServiceIntegratorData #txt
Se0 f21 actionDecl 'ch.ivy.add.portalkit.service.integrators.SecurityServiceIntegratorData out;
' #txt
Se0 f21 actionTable 'out=in;
out.errors=wsResponse.findSecurityMembersToDelegateResponse.result.errors;
out.roles=wsResponse.findSecurityMembersToDelegateResponse.result.roles;
out.users=wsResponse.findSecurityMembersToDelegateResponse.result.users;
' #txt
Se0 f21 cache '{/cache false /invalidation false /scope 0 /groupname ""/lifetime_group "0"/invalidation_time_group ""/identifier ""/lifetime_entry "0"/invalidation_time_entry ""}' #txt
Se0 f21 timeout 60 #txt
Se0 f21 beanConfig '"KEY_PASSWORD=<%\\=in.server.password%>
KEY_AXIS_PORTNAME=SecurityServicePort
KEY_WEBSERVICECONFIG_ID=1485F2F441BFB375
KEY_DOMAIN=
KEY_REQUEST_PARAMETER_MAPPINGS_OPTIONS_AUTO_INITIALIZE_FIRST_LEVEL_FIELDS=true
KEY_REQUEST_PARAMETER_MAPPINGS_OPTIONS_MAP_NULL_VALUES=false
KEY_USERNAME=<%\\=in.server.username%>
KEY_OPERATION=findSecurityMembersToDelegate
KEY_AUTHENTICATION_KIND=1
KEY_HOST=
KEY_USE_AUTHENTICATION=true
KEY_AXIS_CSL_PARAMETER_DATA=""arg0.findSecurityMembersToDelegate.taskID__@@__Long__@@__in.taskId"""' #txt
Se0 f21 exceptionHandler 14E8AD127675284A-f3-buffer #txt
Se0 f21 timeoutExceptionHandler 14E8AD127675284A-f3-buffer #txt
Se0 f21 returningObjectList '[wsResponse]' #txt
Se0 f21 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>HTTP Basic</name>
        <nameStyle>10,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Se0 f21 78 1012 36 24 20 -2 #rect
Se0 f21 @|WebServiceIcon #fIcon
Se0 f31 expr in #txt
Se0 f31 outCond 'ch.ivy.addon.portalkit.enums.WSAuthenticationType.HTTP_BASIC == in.server.wsAuthenticationType' #txt
Se0 f31 242 928 96 1012 #arcP
Se0 f31 1 96 928 #addKink
Se0 f31 0 0.7302200350148694 0 0 #arcLabel
Se0 f24 expr in #txt
Se0 f24 270 928 416 1012 #arcP
Se0 f24 1 416 928 #addKink
Se0 f24 0 0.7875410210393261 0 0 #arcLabel
Se0 f32 expr out #txt
Se0 f32 96 1036 243 1120 #arcP
Se0 f32 1 96 1120 #addKink
Se0 f32 1 0.20679742620065877 0 0 #arcLabel
>Proto Se0 .type ch.ivy.add.portalkit.service.integrators.SecurityServiceIntegratorData #txt
>Proto Se0 .processKind CALLABLE_SUB #txt
>Proto Se0 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <swimlaneLabel>Role</swimlaneLabel>
        <swimlaneLabel>User</swimlaneLabel>
        <swimlaneLabel>SecurityMember</swimlaneLabel>
        <swimlaneLabel>Exception Handler</swimlaneLabel>
        <swimlaneLabel></swimlaneLabel>
    </language>
    <swimlaneOrientation>false</swimlaneOrientation>
    <swimlaneSize>416</swimlaneSize>
    <swimlaneSize>384</swimlaneSize>
    <swimlaneSize>384</swimlaneSize>
    <swimlaneSize>224</swimlaneSize>
    <swimlaneColor gradient="true">-10040065</swimlaneColor>
    <swimlaneColor gradient="true">-16724737</swimlaneColor>
    <swimlaneColor gradient="true">-1</swimlaneColor>
    <swimlaneColor gradient="true">-39322</swimlaneColor>
    <swimlaneType>LANE</swimlaneType>
    <swimlaneType>LANE</swimlaneType>
    <swimlaneType>LANE</swimlaneType>
    <swimlaneType>LANE</swimlaneType>
    <swimlaneSpaceBefore>0</swimlaneSpaceBefore>
    <swimlaneSpaceBefore>0</swimlaneSpaceBefore>
    <swimlaneSpaceBefore>0</swimlaneSpaceBefore>
    <swimlaneSpaceBefore>0</swimlaneSpaceBefore>
</elementInfo>
' #txt
>Proto Se0 0 0 32 24 18 0 #rect
>Proto Se0 @|BIcon #fIcon
Se0 f58 out f62 tail #connect
Se0 f62 head f60 mainIn #connect
Se0 f60 mainOut f63 tail #connect
Se0 f63 head f57 mainIn #connect
Se0 f0 mainOut f2 tail #connect
Se0 f2 head f57 mainIn #connect
Se0 f3 mainOut f5 tail #connect
Se0 f5 head f4 mainIn #connect
Se0 f8 out f12 tail #connect
Se0 f12 head f10 mainIn #connect
Se0 f10 mainOut f13 tail #connect
Se0 f13 head f9 mainIn #connect
Se0 f6 mainOut f15 tail #connect
Se0 f15 head f9 mainIn #connect
Se0 f16 out f22 tail #connect
Se0 f22 head f19 mainIn #connect
Se0 f19 mainOut f23 tail #connect
Se0 f23 head f17 mainIn #connect
Se0 f18 mainOut f25 tail #connect
Se0 f25 head f17 mainIn #connect
Se0 f59 mainOut f34 tail #connect
Se0 f34 head f58 in #connect
Se0 f33 mainOut f26 tail #connect
Se0 f26 head f57 mainIn #connect
Se0 f58 out f27 tail #connect
Se0 f27 head f33 mainIn #connect
Se0 f58 out f1 tail #connect
Se0 f1 head f0 mainIn #connect
Se0 f7 mainOut f28 tail #connect
Se0 f28 head f8 in #connect
Se0 f8 out f29 tail #connect
Se0 f29 head f11 mainIn #connect
Se0 f11 mainOut f14 tail #connect
Se0 f14 head f9 mainIn #connect
Se0 f8 out f30 tail #connect
Se0 f30 head f6 mainIn #connect
Se0 f20 mainOut f35 tail #connect
Se0 f35 head f16 in #connect
Se0 f16 out f31 tail #connect
Se0 f31 head f21 mainIn #connect
Se0 f16 out f24 tail #connect
Se0 f24 head f18 mainIn #connect
Se0 f21 mainOut f32 tail #connect
Se0 f32 head f17 mainIn #connect
