[Ivy]
[>Created: Mon Dec 28 11:10:08 ICT 2015]
14E8AD127675284A 3.18 #module
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
Se0 @PushWFArc f61 '' #zField
Se0 @PushWFArc f62 '' #zField
Se0 @PushWFArc f63 '' #zField
Se0 @WSElement f0 '' #zField
Se0 @PushWFArc f1 '' #zField
Se0 @PushWFArc f2 '' #zField
Se0 @ProcessException f3 '' #zField
Se0 @EndSub f4 '' #zField
Se0 @PushWFArc f5 '' #zField
Se0 @WSElement f6 '' #zField
Se0 @StartSub f7 '' #zField
Se0 @Alternative f8 '' #zField
Se0 @EndSub f9 '' #zField
Se0 @WSElement f10 '' #zField
Se0 @PushWFArc f11 '' #zField
Se0 @PushWFArc f12 '' #zField
Se0 @PushWFArc f13 '' #zField
Se0 @PushWFArc f14 '' #zField
Se0 @PushWFArc f15 '' #zField
Se0 @Alternative f16 '' #zField
Se0 @EndSub f17 '' #zField
Se0 @WSElement f18 '' #zField
Se0 @WSElement f19 '' #zField
Se0 @StartSub f20 '' #zField
Se0 @PushWFArc f21 '' #zField
Se0 @PushWFArc f22 '' #zField
Se0 @PushWFArc f23 '' #zField
Se0 @PushWFArc f24 '' #zField
Se0 @PushWFArc f25 '' #zField
>Proto Se0 Se0 SecurityServiceIntegrator #zField
Se0 f58 type ch.ivy.add.portalkit.service.integrators.SecurityServiceIntegratorData #txt
Se0 f58 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>User NTLM Authentication?</name>
        <nameStyle>25,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Se0 f58 82 114 28 28 14 -22 #rect
Se0 f58 @|AlternativeIcon #fIcon
Se0 f57 type ch.ivy.add.portalkit.service.integrators.SecurityServiceIntegratorData #txt
Se0 f57 83 291 26 26 14 0 #rect
Se0 f57 @|EndSubIcon #fIcon
Se0 f60 type ch.ivy.add.portalkit.service.integrators.SecurityServiceIntegratorData #txt
Se0 f60 actionDecl 'ch.ivy.add.portalkit.service.integrators.SecurityServiceIntegratorData out;
' #txt
Se0 f60 actionTable 'out=in;
out.errors=wsResponse.findAllRolesResponse.result.errors;
out.roles=wsResponse.findAllRolesResponse.result.roles;
' #txt
Se0 f60 cache '{/cache false /invalidation false /scope 0 /groupname ""/lifetime_group "0"/invalidation_time_group ""/identifier ""/lifetime_entry "0"/invalidation_time_entry ""}' #txt
Se0 f60 timeout 10 #txt
Se0 f60 beanConfig '"KEY_PASSWORD=<%\\=ivy.var.PortalWSPassword%>
KEY_AXIS_PORTNAME=SecurityServicePort
KEY_WEBSERVICECONFIG_ID=1485F2F441BFB375
KEY_DOMAIN=<%\\=ivy.var.PortalWSDomain%>
KEY_USERNAME=<%\\=ivy.var.PortalWSUsername%>
KEY_OPERATION=findAllRoles
KEY_AUTHENTICATION_KIND=4
KEY_HOST=<%\\=ivy.var.PortalWSHost%>
KEY_USE_AUTHENTICATION=true
KEY_AXIS_CSL_PARAMETER_DATA=""arg0.findAllRoles.apps__@@__Array<String>__@@__in.applicationNames"""' #txt
Se0 f60 exceptionHandler 14E8AD127675284A-f3-buffer #txt
Se0 f60 timeoutExceptionHandler 14E8AD127675284A-f3-buffer #txt
Se0 f60 returningObjectList '[wsResponse]' #txt
Se0 f60 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Call SecurityService:FindAllRoles
with NTLM Override
and Global Variable Settings</name>
        <nameStyle>34,7
47,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Se0 f60 78 196 36 24 20 -2 #rect
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
Se0 f59 83 35 26 26 14 0 #rect
Se0 f59 @|StartSubIcon #fIcon
Se0 f61 expr out #txt
Se0 f61 96 61 96 114 #arcP
Se0 f62 expr in #txt
Se0 f62 outCond in.server.isNTLMAuthentication #txt
Se0 f62 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>yes</name>
        <nameStyle>3,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Se0 f62 96 142 96 196 #arcP
Se0 f63 expr out #txt
Se0 f63 96 220 96 291 #arcP
Se0 f0 type ch.ivy.add.portalkit.service.integrators.SecurityServiceIntegratorData #txt
Se0 f0 actionDecl 'ch.ivy.add.portalkit.service.integrators.SecurityServiceIntegratorData out;
' #txt
Se0 f0 actionTable 'out=in;
out.errors=wsResponse.findAllRolesResponse.result.errors;
out.roles=wsResponse.findAllRolesResponse.result.roles;
' #txt
Se0 f0 cache '{/cache false /invalidation false /scope 0 /groupname ""/lifetime_group "0"/invalidation_time_group ""/identifier ""/lifetime_entry "0"/invalidation_time_entry ""}' #txt
Se0 f0 timeout 10 #txt
Se0 f0 beanConfig '"KEY_PASSWORD=
KEY_AXIS_PORTNAME=SecurityServicePort
KEY_WEBSERVICECONFIG_ID=1485F2F441BFB375
KEY_DOMAIN=
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
        <name>Call SecurityService:FindAllRoles
with default settings</name>
        <nameStyle>34,7
21,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Se0 f0 334 196 36 24 20 -2 #rect
Se0 f0 @|WebServiceIcon #fIcon
Se0 f1 expr in #txt
Se0 f1 110 128 352 196 #arcP
Se0 f1 1 352 128 #addKink
Se0 f1 0 0.7302200350148694 0 0 #arcLabel
Se0 f2 expr out #txt
Se0 f2 352 220 109 304 #arcP
Se0 f2 1 352 304 #addKink
Se0 f2 1 0.2892337303118032 0 0 #arcLabel
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
Se0 f3 83 1059 26 26 14 0 #rect
Se0 f3 @|ExceptionIcon #fIcon
Se0 f4 type ch.ivy.add.portalkit.service.integrators.SecurityServiceIntegratorData #txt
Se0 f4 83 1187 26 26 14 0 #rect
Se0 f4 @|EndSubIcon #fIcon
Se0 f5 expr out #txt
Se0 f5 96 1085 96 1187 #arcP
Se0 f6 type ch.ivy.add.portalkit.service.integrators.SecurityServiceIntegratorData #txt
Se0 f6 actionDecl 'ch.ivy.add.portalkit.service.integrators.SecurityServiceIntegratorData out;
' #txt
Se0 f6 actionTable 'out=in;
out.errors=wsResponse.findAllUsersResponse.result.errors;
out.users=wsResponse.findAllUsersResponse.result.users;
' #txt
Se0 f6 cache '{/cache false /invalidation false /scope 0 /groupname ""/lifetime_group "0"/invalidation_time_group ""/identifier ""/lifetime_entry "0"/invalidation_time_entry ""}' #txt
Se0 f6 timeout 10 #txt
Se0 f6 beanConfig '"KEY_PASSWORD=
KEY_AXIS_PORTNAME=SecurityServicePort
KEY_WEBSERVICECONFIG_ID=1485F2F441BFB375
KEY_DOMAIN=
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
        <name>Call SecurityService:FindAllUsers
with default settings</name>
        <nameStyle>34,7
21,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Se0 f6 334 540 36 24 20 -2 #rect
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
Se0 f7 83 379 26 26 14 0 #rect
Se0 f7 @|StartSubIcon #fIcon
Se0 f8 type ch.ivy.add.portalkit.service.integrators.SecurityServiceIntegratorData #txt
Se0 f8 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>User NTLM Authentication?</name>
        <nameStyle>25,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Se0 f8 82 458 28 28 14 -22 #rect
Se0 f8 @|AlternativeIcon #fIcon
Se0 f9 type ch.ivy.add.portalkit.service.integrators.SecurityServiceIntegratorData #txt
Se0 f9 83 627 26 26 14 0 #rect
Se0 f9 @|EndSubIcon #fIcon
Se0 f10 type ch.ivy.add.portalkit.service.integrators.SecurityServiceIntegratorData #txt
Se0 f10 actionDecl 'ch.ivy.add.portalkit.service.integrators.SecurityServiceIntegratorData out;
' #txt
Se0 f10 actionTable 'out=in;
out.errors=wsResponse.findAllUsersResponse.result.errors;
out.users=wsResponse.findAllUsersResponse.result.users;
' #txt
Se0 f10 cache '{/cache false /invalidation false /scope 0 /groupname ""/lifetime_group "0"/invalidation_time_group ""/identifier ""/lifetime_entry "0"/invalidation_time_entry ""}' #txt
Se0 f10 timeout 10 #txt
Se0 f10 beanConfig '"KEY_PASSWORD=<%\\=ivy.var.PortalWSPassword%>
KEY_AXIS_PORTNAME=SecurityServicePort
KEY_WEBSERVICECONFIG_ID=1485F2F441BFB375
KEY_DOMAIN=<%\\=ivy.var.PortalWSDomain%>
KEY_USERNAME=<%\\=ivy.var.PortalWSUsername%>
KEY_OPERATION=findAllUsers
KEY_AUTHENTICATION_KIND=4
KEY_HOST=<%\\=ivy.var.PortalWSHost%>
KEY_USE_AUTHENTICATION=true
KEY_AXIS_CSL_PARAMETER_DATA=""arg0.findAllUsers.apps__@@__Array<String>__@@__in.applicationNames"""' #txt
Se0 f10 exceptionHandler 14E8AD127675284A-f3-buffer #txt
Se0 f10 timeoutExceptionHandler 14E8AD127675284A-f3-buffer #txt
Se0 f10 returningObjectList '[wsResponse]' #txt
Se0 f10 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Call SecurityService:FindAllUsers
with NTLM Override
and Global Variable Settings</name>
        <nameStyle>34,7
47,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Se0 f10 78 540 36 24 20 -2 #rect
Se0 f10 @|WebServiceIcon #fIcon
Se0 f11 expr out #txt
Se0 f11 96 405 96 458 #arcP
Se0 f12 expr in #txt
Se0 f12 outCond in.server.isNTLMAuthentication #txt
Se0 f12 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>yes</name>
        <nameStyle>3,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Se0 f12 96 486 96 540 #arcP
Se0 f13 expr out #txt
Se0 f13 96 564 96 627 #arcP
Se0 f14 expr in #txt
Se0 f14 110 472 352 540 #arcP
Se0 f14 1 352 472 #addKink
Se0 f14 0 0.7302200350148694 0 0 #arcLabel
Se0 f15 expr out #txt
Se0 f15 352 564 109 640 #arcP
Se0 f15 1 352 640 #addKink
Se0 f15 1 0.2892337303118032 0 0 #arcLabel
Se0 f16 type ch.ivy.add.portalkit.service.integrators.SecurityServiceIntegratorData #txt
Se0 f16 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>User NTLM Authentication?</name>
        <nameStyle>25,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Se0 f16 82 802 28 28 14 -22 #rect
Se0 f16 @|AlternativeIcon #fIcon
Se0 f17 type ch.ivy.add.portalkit.service.integrators.SecurityServiceIntegratorData #txt
Se0 f17 83 979 26 26 14 0 #rect
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
Se0 f18 timeout 10 #txt
Se0 f18 beanConfig '"KEY_PASSWORD=
KEY_AXIS_PORTNAME=SecurityServicePort
KEY_WEBSERVICECONFIG_ID=1485F2F441BFB375
KEY_DOMAIN=
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
        <name>Call SecurityService:FindAllRoles
with default settings</name>
        <nameStyle>34,7
21,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Se0 f18 334 884 36 24 20 -2 #rect
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
Se0 f19 timeout 10 #txt
Se0 f19 beanConfig '"KEY_PASSWORD=<%\\=ivy.var.PortalWSPassword%>
KEY_AXIS_PORTNAME=SecurityServicePort
KEY_WEBSERVICECONFIG_ID=1485F2F441BFB375
KEY_DOMAIN=<%\\=ivy.var.PortalWSDomain%>
KEY_USERNAME=<%\\=ivy.var.PortalWSUsername%>
KEY_OPERATION=findSecurityMembersToDelegate
KEY_AUTHENTICATION_KIND=4
KEY_HOST=<%\\=ivy.var.PortalWSHost%>
KEY_USE_AUTHENTICATION=true
KEY_AXIS_CSL_PARAMETER_DATA=""arg0.findSecurityMembersToDelegate.taskID__@@__Long__@@__in.taskId"""' #txt
Se0 f19 exceptionHandler 14E8AD127675284A-f3-buffer #txt
Se0 f19 timeoutExceptionHandler 14E8AD127675284A-f3-buffer #txt
Se0 f19 returningObjectList '[wsResponse]' #txt
Se0 f19 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Call SecurityService:FindAllRoles
with NTLM Override
and Global Variable Settings</name>
        <nameStyle>34,7
47,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Se0 f19 78 884 36 24 20 -2 #rect
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
Se0 f20 83 723 26 26 14 0 #rect
Se0 f20 @|StartSubIcon #fIcon
Se0 f21 expr out #txt
Se0 f21 96 749 96 802 #arcP
Se0 f22 expr in #txt
Se0 f22 outCond in.server.isNTLMAuthentication #txt
Se0 f22 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>yes</name>
        <nameStyle>3,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Se0 f22 96 830 96 884 #arcP
Se0 f23 expr out #txt
Se0 f23 96 908 96 979 #arcP
Se0 f24 expr in #txt
Se0 f24 110 816 352 884 #arcP
Se0 f24 1 352 816 #addKink
Se0 f24 0 0.7302200350148694 0 0 #arcLabel
Se0 f25 expr out #txt
Se0 f25 352 908 109 992 #arcP
Se0 f25 1 352 992 #addKink
Se0 f25 1 0.2892337303118032 0 0 #arcLabel
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
    <swimlaneSize>352</swimlaneSize>
    <swimlaneSize>328</swimlaneSize>
    <swimlaneSize>344</swimlaneSize>
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
Se0 f59 mainOut f61 tail #connect
Se0 f61 head f58 in #connect
Se0 f58 out f62 tail #connect
Se0 f62 head f60 mainIn #connect
Se0 f60 mainOut f63 tail #connect
Se0 f63 head f57 mainIn #connect
Se0 f58 out f1 tail #connect
Se0 f1 head f0 mainIn #connect
Se0 f0 mainOut f2 tail #connect
Se0 f2 head f57 mainIn #connect
Se0 f3 mainOut f5 tail #connect
Se0 f5 head f4 mainIn #connect
Se0 f7 mainOut f11 tail #connect
Se0 f11 head f8 in #connect
Se0 f8 out f12 tail #connect
Se0 f12 head f10 mainIn #connect
Se0 f10 mainOut f13 tail #connect
Se0 f13 head f9 mainIn #connect
Se0 f8 out f14 tail #connect
Se0 f14 head f6 mainIn #connect
Se0 f6 mainOut f15 tail #connect
Se0 f15 head f9 mainIn #connect
Se0 f20 mainOut f21 tail #connect
Se0 f21 head f16 in #connect
Se0 f16 out f22 tail #connect
Se0 f22 head f19 mainIn #connect
Se0 f19 mainOut f23 tail #connect
Se0 f23 head f17 mainIn #connect
Se0 f16 out f24 tail #connect
Se0 f24 head f18 mainIn #connect
Se0 f18 mainOut f25 tail #connect
Se0 f25 head f17 mainIn #connect
