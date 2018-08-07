[Ivy]
[>Created: Thu Feb 25 11:54:53 ICT 2016]
14E8BC51CC56193A 3.18 #module
>Proto >Proto Collection #zClass
Te0 TaskServiceIntegrator Big #zClass
Te0 B #cInfo
Te0 #process
Te0 @TextInP .resExport .resExport #zField
Te0 @TextInP .type .type #zField
Te0 @TextInP .processKind .processKind #zField
Te0 @AnnotationInP-0n ai ai #zField
Te0 @TextInP .xml .xml #zField
Te0 @TextInP .responsibility .responsibility #zField
Te0 @WSElement f3 '' #zField
Te0 @EndSub f1 '' #zField
Te0 @StartSub f0 '' #zField
Te0 @Alternative f2 '' #zField
Te0 @PushWFArc f5 '' #zField
Te0 @PushWFArc f6 '' #zField
Te0 @ProcessException f7 '' #zField
Te0 @EndSub f8 '' #zField
Te0 @PushWFArc f9 '' #zField
Te0 @WSElement f13 '' #zField
Te0 @PushWFArc f14 '' #zField
Te0 @PushWFArc f10 '' #zField
Te0 @StartSub f11 '' #zField
Te0 @Alternative f12 '' #zField
Te0 @WSElement f16 '' #zField
Te0 @EndSub f17 '' #zField
Te0 @PushWFArc f18 '' #zField
Te0 @PushWFArc f19 '' #zField
Te0 @WSElement f20 '' #zField
Te0 @PushWFArc f21 '' #zField
Te0 @PushWFArc f22 '' #zField
Te0 @WSElement f23 '' #zField
Te0 @EndSub f24 '' #zField
Te0 @Alternative f25 '' #zField
Te0 @StartSub f26 '' #zField
Te0 @WSElement f27 '' #zField
Te0 @PushWFArc f29 '' #zField
Te0 @PushWFArc f30 '' #zField
Te0 @PushWFArc f31 '' #zField
Te0 @PushWFArc f32 '' #zField
Te0 @EndSub f33 '' #zField
Te0 @WSElement f34 '' #zField
Te0 @StartSub f35 '' #zField
Te0 @Alternative f36 '' #zField
Te0 @WSElement f37 '' #zField
Te0 @PushWFArc f39 '' #zField
Te0 @PushWFArc f40 '' #zField
Te0 @PushWFArc f41 '' #zField
Te0 @PushWFArc f42 '' #zField
Te0 @StartSub f45 '' #zField
Te0 @WSElement f46 '' #zField
Te0 @Alternative f47 '' #zField
Te0 @EndSub f48 '' #zField
Te0 @WSElement f49 '' #zField
Te0 @PushWFArc f51 '' #zField
Te0 @PushWFArc f52 '' #zField
Te0 @PushWFArc f53 '' #zField
Te0 @PushWFArc f54 '' #zField
Te0 @StartSub f43 '' #zField
Te0 @EndSub f44 '' #zField
Te0 @Alternative f55 '' #zField
Te0 @PushWFArc f56 '' #zField
Te0 @WSElement f63 '' #zField
Te0 @PushWFArc f61 '' #zField
Te0 @WSElement f57 '' #zField
Te0 @PushWFArc f59 '' #zField
Te0 @PushWFArc f58 '' #zField
Te0 @PushWFArc f60 '' #zField
Te0 @GridStep f62 '' #zField
Te0 @PushWFArc f64 '' #zField
Te0 @PushWFArc f4 '' #zField
Te0 @GridStep f65 '' #zField
Te0 @PushWFArc f66 '' #zField
Te0 @PushWFArc f28 '' #zField
Te0 @GridStep f67 '' #zField
Te0 @PushWFArc f68 '' #zField
Te0 @PushWFArc f69 '' #zField
Te0 @GridStep f38 '' #zField
Te0 @PushWFArc f70 '' #zField
Te0 @PushWFArc f71 '' #zField
Te0 @GridStep f15 '' #zField
Te0 @PushWFArc f72 '' #zField
Te0 @PushWFArc f50 '' #zField
Te0 @WSElement f73 '' #zField
Te0 @StartSub f74 '' #zField
Te0 @GridStep f75 '' #zField
Te0 @WSElement f76 '' #zField
Te0 @Alternative f77 '' #zField
Te0 @EndSub f78 '' #zField
Te0 @PushWFArc f79 '' #zField
Te0 @PushWFArc f80 '' #zField
Te0 @PushWFArc f81 '' #zField
Te0 @PushWFArc f82 '' #zField
Te0 @PushWFArc f83 '' #zField
Te0 @PushWFArc f84 '' #zField
Te0 @StartSub f85 '' #zField
Te0 @WSElement f86 '' #zField
Te0 @GridStep f87 '' #zField
Te0 @Alternative f88 '' #zField
Te0 @WSElement f89 '' #zField
Te0 @EndSub f90 '' #zField
Te0 @PushWFArc f91 '' #zField
Te0 @PushWFArc f92 '' #zField
Te0 @PushWFArc f93 '' #zField
Te0 @PushWFArc f94 '' #zField
Te0 @PushWFArc f95 '' #zField
Te0 @PushWFArc f96 '' #zField
>Proto Te0 Te0 TaskServiceIntegrator #zField
Te0 f3 type ch.ivy.add.portalkit.service.integrators.TaskServiceIntegratorData #txt
Te0 f3 actionDecl 'ch.ivy.add.portalkit.service.integrators.TaskServiceIntegratorData out;
' #txt
Te0 f3 actionTable 'out=in;
out.errors=wsResponse.findActiveTasksByFilterResponse.result.errors;
out.tasks=wsResponse.findActiveTasksByFilterResponse.result.ivyTasks;
' #txt
Te0 f3 cache '{/cache false /invalidation false /scope 0 /groupname ""/lifetime_group "0"/invalidation_time_group ""/identifier ""/lifetime_entry "0"/invalidation_time_entry ""}' #txt
Te0 f3 timeout 10 #txt
Te0 f3 beanConfig '"KEY_PASSWORD=<%\\=ivy.var.PortalWSPassword%>
KEY_AXIS_PORTNAME=TaskServicePort
KEY_WEBSERVICECONFIG_ID=146B90974252183F
KEY_DOMAIN=<%\\=ivy.var.PortalWSDomain%>
KEY_USERNAME=<%\\=ivy.var.PortalWSUsername%>
KEY_OPERATION=findActiveTasksByFilter
KEY_AUTHENTICATION_KIND=4
KEY_HOST=<%\\=ivy.var.PortalWSHost%>
KEY_USE_AUTHENTICATION=true
KEY_AXIS_CSL_PARAMETER_DATA=""arg0.findActiveTasksByFilter.apps__@@__Array<String>__@@__in.applicationNames"",""arg0.findActiveTasksByFilter.serverId__@@__Long__@@__in.server.id"",""arg0.findActiveTasksByFilter.serverUrl__@@__String__@@__in.serverUrl"",""arg0.findActiveTasksByFilter.user__@@__String__@@__in.userName"""' #txt
Te0 f3 exceptionHandler 14E8BC51CC56193A-f7-buffer #txt
Te0 f3 timeoutExceptionHandler 14E8BC51CC56193A-f7-buffer #txt
Te0 f3 returningObjectList '[wsResponse]' #txt
Te0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Call TaskService:FindActiveTaskByFilter
with NTLM Override
and Global Variable Settings</name>
        <nameStyle>40,7
47,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Te0 f3 78 260 36 24 20 -2 #rect
Te0 f3 @|WebServiceIcon #fIcon
Te0 f1 type ch.ivy.add.portalkit.service.integrators.TaskServiceIntegratorData #txt
Te0 f1 83 355 26 26 14 0 #rect
Te0 f1 @|EndSubIcon #fIcon
Te0 f0 inParamDecl '<java.lang.String endpoint,java.lang.String userName,List<java.lang.String> applicationNames,ch.ivy.addon.portalkit.persistence.domain.Server server> param;' #txt
Te0 f0 inParamTable 'out.applicationNames=param.applicationNames;
out.endpoint=param.endpoint;
out.server=param.server;
out.userName=param.userName;
' #txt
Te0 f0 outParamDecl '<List<ch.ivy.ws.addon.WsException> errors,List<ch.ivy.ws.addon.IvyTask> tasks> result;
' #txt
Te0 f0 outParamTable 'result.errors=in.errors;
result.tasks=in.tasks;
' #txt
Te0 f0 actionDecl 'ch.ivy.add.portalkit.service.integrators.TaskServiceIntegratorData out;
' #txt
Te0 f0 callSignature findActiveTasksByFilter(String,String,List<String>,ch.ivy.addon.portalkit.persistence.domain.Server) #txt
Te0 f0 type ch.ivy.add.portalkit.service.integrators.TaskServiceIntegratorData #txt
Te0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>findActiveTasksByFilter(String,String,List&lt;String&gt;,Server)</name>
        <nameStyle>58,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Te0 f0 83 51 26 26 14 0 #rect
Te0 f0 @|StartSubIcon #fIcon
Te0 f2 type ch.ivy.add.portalkit.service.integrators.TaskServiceIntegratorData #txt
Te0 f2 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>User NTLM Authentication?</name>
        <nameStyle>25,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Te0 f2 82 178 28 28 14 -22 #rect
Te0 f2 @|AlternativeIcon #fIcon
Te0 f5 expr in #txt
Te0 f5 outCond in.server.isNTLMAuthentication #txt
Te0 f5 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>yes</name>
        <nameStyle>3,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Te0 f5 96 206 96 260 #arcP
Te0 f6 expr out #txt
Te0 f6 96 284 96 355 #arcP
Te0 f7 .resExport export #txt
Te0 f7 actionDecl 'ch.ivy.add.portalkit.service.integrators.TaskServiceIntegratorData out;
' #txt
Te0 f7 actionTable 'out=in;
' #txt
Te0 f7 actionCode ivy.log.error(exception); #txt
Te0 f7 type ch.ivy.add.portalkit.service.integrators.TaskServiceIntegratorData #txt
Te0 f7 errorCode unspecified #txt
Te0 f7 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Exception Handler</name>
        <nameStyle>17,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Te0 f7 91 771 26 26 14 0 #rect
Te0 f7 @|ExceptionIcon #fIcon
Te0 f8 type ch.ivy.add.portalkit.service.integrators.TaskServiceIntegratorData #txt
Te0 f8 91 899 26 26 14 0 #rect
Te0 f8 @|EndSubIcon #fIcon
Te0 f9 expr out #txt
Te0 f9 104 797 104 899 #arcP
Te0 f13 type ch.ivy.add.portalkit.service.integrators.TaskServiceIntegratorData #txt
Te0 f13 actionDecl 'ch.ivy.add.portalkit.service.integrators.TaskServiceIntegratorData out;
' #txt
Te0 f13 actionTable 'out=in;
out.errors=wsResponse.findActiveTasksByFilterResponse.result.errors;
out.tasks=wsResponse.findActiveTasksByFilterResponse.result.ivyTasks;
' #txt
Te0 f13 cache '{/cache false /invalidation false /scope 0 /groupname ""/lifetime_group "0"/invalidation_time_group ""/identifier ""/lifetime_entry "0"/invalidation_time_entry ""}' #txt
Te0 f13 timeout 10 #txt
Te0 f13 beanConfig '"KEY_PASSWORD=
KEY_AXIS_PORTNAME=TaskServicePort
KEY_WEBSERVICECONFIG_ID=146B90974252183F
KEY_DOMAIN=
KEY_USERNAME=
KEY_OPERATION=findActiveTasksByFilter
KEY_AUTHENTICATION_KIND=0
KEY_HOST=
KEY_USE_AUTHENTICATION=false
KEY_AXIS_CSL_PARAMETER_DATA=""arg0.findActiveTasksByFilter.apps__@@__Array<String>__@@__in.applicationNames"",""arg0.findActiveTasksByFilter.serverId__@@__Long__@@__in.server.id"",""arg0.findActiveTasksByFilter.serverUrl__@@__String__@@__in.serverUrl"",""arg0.findActiveTasksByFilter.user__@@__String__@@__in.userName"""' #txt
Te0 f13 exceptionHandler 14E8BC51CC56193A-f7-buffer #txt
Te0 f13 timeoutExceptionHandler 14E8BC51CC56193A-f7-buffer #txt
Te0 f13 returningObjectList '[wsResponse]' #txt
Te0 f13 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Call TaskService:FindActiveTaskByFilter
with default settings</name>
        <nameStyle>40,7
21,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Te0 f13 342 260 36 24 20 -2 #rect
Te0 f13 @|WebServiceIcon #fIcon
Te0 f14 expr in #txt
Te0 f14 110 192 360 260 #arcP
Te0 f14 1 360 192 #addKink
Te0 f14 0 0.7302200350148694 0 0 #arcLabel
Te0 f10 expr out #txt
Te0 f10 360 284 109 368 #arcP
Te0 f10 1 360 368 #addKink
Te0 f10 1 0.3198727889829455 0 0 #arcLabel
Te0 f11 inParamDecl '<java.lang.String endpoint,ch.ivy.addon.portalkit.persistence.domain.Server server,java.lang.Long taskId> param;' #txt
Te0 f11 inParamTable 'out.endpoint=param.endpoint;
out.server=param.server;
out.taskId=param.taskId;
' #txt
Te0 f11 outParamDecl '<List<ch.ivy.ws.addon.WsException> errors,ch.ivy.ws.addon.IvyTask task> result;
' #txt
Te0 f11 outParamTable 'result.errors=in.errors;
result.task=in.task;
' #txt
Te0 f11 actionDecl 'ch.ivy.add.portalkit.service.integrators.TaskServiceIntegratorData out;
' #txt
Te0 f11 callSignature resetTask(String,ch.ivy.addon.portalkit.persistence.domain.Server,Long) #txt
Te0 f11 type ch.ivy.add.portalkit.service.integrators.TaskServiceIntegratorData #txt
Te0 f11 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>resetTask(String,Server,Long)</name>
        <nameStyle>29,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Te0 f11 691 51 26 26 14 0 #rect
Te0 f11 @|StartSubIcon #fIcon
Te0 f12 type ch.ivy.add.portalkit.service.integrators.TaskServiceIntegratorData #txt
Te0 f12 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>NTML Authencation?</name>
        <nameStyle>18,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Te0 f12 690 130 28 28 14 0 #rect
Te0 f12 @|AlternativeIcon #fIcon
Te0 f16 type ch.ivy.add.portalkit.service.integrators.TaskServiceIntegratorData #txt
Te0 f16 actionDecl 'ch.ivy.add.portalkit.service.integrators.TaskServiceIntegratorData out;
' #txt
Te0 f16 actionTable 'out=in;
out.errors=wsResponse.resetTaskResponse.result.errors;
out.task=wsResponse.resetTaskResponse.result.ivyTask;
' #txt
Te0 f16 cache '{/cache false /invalidation false /scope 0 /groupname ""/lifetime_group "0"/invalidation_time_group ""/identifier ""/lifetime_entry "0"/invalidation_time_entry ""}' #txt
Te0 f16 timeout 10 #txt
Te0 f16 beanConfig '"KEY_PASSWORD=<%\\=ivy.var.PortalWSPassword%>
KEY_AXIS_PORTNAME=TaskServicePort
KEY_WEBSERVICECONFIG_ID=146B90974252183F
KEY_DOMAIN=<%\\=ivy.var.PortalWSDomain%>
KEY_USERNAME=<%\\=ivy.var.PortalWSUsername%>
KEY_OPERATION=resetTask
KEY_AUTHENTICATION_KIND=4
KEY_HOST=<%\\=ivy.var.PortalWSHost%>
KEY_USE_AUTHENTICATION=true
KEY_AXIS_CSL_PARAMETER_DATA=""arg0.resetTask.id__@@__Long__@@__in.taskId"",""arg0.resetTask.serverUrl__@@__String__@@__in.serverUrl"""' #txt
Te0 f16 exceptionHandler 14E8BC51CC56193A-f7-buffer #txt
Te0 f16 timeoutExceptionHandler 14E8BC51CC56193A-f7-buffer #txt
Te0 f16 returningObjectList '[wsResponse]' #txt
Te0 f16 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>with NTML authentication</name>
        <nameStyle>24,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Te0 f16 686 212 36 24 20 -2 #rect
Te0 f16 @|WebServiceIcon #fIcon
Te0 f17 type ch.ivy.add.portalkit.service.integrators.TaskServiceIntegratorData #txt
Te0 f17 691 307 26 26 14 0 #rect
Te0 f17 @|EndSubIcon #fIcon
Te0 f18 expr in #txt
Te0 f18 outCond in.server.isNTLMAuthentication #txt
Te0 f18 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>yes</name>
        <nameStyle>3,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Te0 f18 704 158 704 212 #arcP
Te0 f19 expr out #txt
Te0 f19 704 236 704 307 #arcP
Te0 f20 type ch.ivy.add.portalkit.service.integrators.TaskServiceIntegratorData #txt
Te0 f20 actionDecl 'ch.ivy.add.portalkit.service.integrators.TaskServiceIntegratorData out;
' #txt
Te0 f20 actionTable 'out=in;
out.errors=wsResponse.resetTaskResponse.result.errors;
out.task=wsResponse.resetTaskResponse.result.ivyTask;
' #txt
Te0 f20 cache '{/cache false /invalidation false /scope 0 /groupname ""/lifetime_group "0"/invalidation_time_group ""/identifier ""/lifetime_entry "0"/invalidation_time_entry ""}' #txt
Te0 f20 timeout 10 #txt
Te0 f20 beanConfig '"KEY_PASSWORD=
KEY_AXIS_PORTNAME=TaskServicePort
KEY_WEBSERVICECONFIG_ID=146B90974252183F
KEY_DOMAIN=
KEY_USERNAME=
KEY_OPERATION=resetTask
KEY_AUTHENTICATION_KIND=0
KEY_HOST=
KEY_USE_AUTHENTICATION=false
KEY_AXIS_CSL_PARAMETER_DATA=""arg0.resetTask.id__@@__Long__@@__in.taskId"",""arg0.resetTask.serverUrl__@@__String__@@__in.serverUrl"""' #txt
Te0 f20 exceptionHandler 14E8BC51CC56193A-f7-buffer #txt
Te0 f20 timeoutExceptionHandler 14E8BC51CC56193A-f7-buffer #txt
Te0 f20 returningObjectList '[wsResponse]' #txt
Te0 f20 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>with default settings</name>
        <nameStyle>21,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Te0 f20 886 212 36 24 20 -2 #rect
Te0 f20 @|WebServiceIcon #fIcon
Te0 f21 expr in #txt
Te0 f21 718 144 904 212 #arcP
Te0 f21 1 904 144 #addKink
Te0 f21 0 0.6554122903055368 0 0 #arcLabel
Te0 f22 expr out #txt
Te0 f22 904 236 717 320 #arcP
Te0 f22 1 904 320 #addKink
Te0 f22 1 0.3139694183456486 0 0 #arcLabel
Te0 f23 type ch.ivy.add.portalkit.service.integrators.TaskServiceIntegratorData #txt
Te0 f23 actionDecl 'ch.ivy.add.portalkit.service.integrators.TaskServiceIntegratorData out;
' #txt
Te0 f23 actionTable 'out=in;
out.errors=wsResponse.delegateTaskResponse.result.errors;
out.task=wsResponse.delegateTaskResponse.result.ivyTask;
' #txt
Te0 f23 cache '{/cache false /invalidation false /scope 0 /groupname ""/lifetime_group "0"/invalidation_time_group ""/identifier ""/lifetime_entry "0"/invalidation_time_entry ""}' #txt
Te0 f23 timeout 10 #txt
Te0 f23 beanConfig '"KEY_PASSWORD=<%\\=ivy.var.PortalWSPassword%>
KEY_AXIS_PORTNAME=TaskServicePort
KEY_WEBSERVICECONFIG_ID=146B90974252183F
KEY_DOMAIN=<%\\=ivy.var.PortalWSDomain%>
KEY_USERNAME=<%\\=ivy.var.PortalWSUsername%>
KEY_OPERATION=delegateTask
KEY_AUTHENTICATION_KIND=4
KEY_HOST=<%\\=ivy.var.PortalWSHost%>
KEY_USE_AUTHENTICATION=true
KEY_AXIS_CSL_PARAMETER_DATA=""arg0.delegateTask.id__@@__Long__@@__in.taskId"",""arg0.delegateTask.ivySecurityMember__@@__ch.ivy.ws.addon.IvySecurityMember__@@__in.ivySecurityMember"",""arg0.delegateTask.serverUrl__@@__String__@@__in.serverUrl"""' #txt
Te0 f23 exceptionHandler 14E8BC51CC56193A-f7-buffer #txt
Te0 f23 timeoutExceptionHandler 14E8BC51CC56193A-f7-buffer #txt
Te0 f23 returningObjectList '[wsResponse]' #txt
Te0 f23 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>with NTML authentication</name>
        <nameStyle>24,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Te0 f23 1134 260 36 24 11 -31 #rect
Te0 f23 @|WebServiceIcon #fIcon
Te0 f24 type ch.ivy.add.portalkit.service.integrators.TaskServiceIntegratorData #txt
Te0 f24 1139 355 26 26 14 0 #rect
Te0 f24 @|EndSubIcon #fIcon
Te0 f25 type ch.ivy.add.portalkit.service.integrators.TaskServiceIntegratorData #txt
Te0 f25 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>NTML Authencation?</name>
        <nameStyle>18,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Te0 f25 1138 178 28 28 14 0 #rect
Te0 f25 @|AlternativeIcon #fIcon
Te0 f26 inParamDecl '<ch.ivy.ws.addon.IvySecurityMember ivySecurityMember,java.lang.String endpoint,ch.ivy.addon.portalkit.persistence.domain.Server server,java.lang.Long taskId> param;' #txt
Te0 f26 inParamTable 'out.endpoint=param.endpoint;
out.ivySecurityMember=param.ivySecurityMember;
out.server=param.server;
out.taskId=param.taskId;
' #txt
Te0 f26 outParamDecl '<List<ch.ivy.ws.addon.WsException> errors,ch.ivy.ws.addon.IvyTask task> result;
' #txt
Te0 f26 outParamTable 'result.errors=in.errors;
result.task=in.task;
' #txt
Te0 f26 actionDecl 'ch.ivy.add.portalkit.service.integrators.TaskServiceIntegratorData out;
' #txt
Te0 f26 callSignature delegateTask(ch.ivy.ws.addon.IvySecurityMember,String,ch.ivy.addon.portalkit.persistence.domain.Server,Long) #txt
Te0 f26 type ch.ivy.add.portalkit.service.integrators.TaskServiceIntegratorData #txt
Te0 f26 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>delegateTask(IvySecurityMember,String,Server,Long)</name>
        <nameStyle>50,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Te0 f26 1139 51 26 26 14 0 #rect
Te0 f26 @|StartSubIcon #fIcon
Te0 f27 type ch.ivy.add.portalkit.service.integrators.TaskServiceIntegratorData #txt
Te0 f27 actionDecl 'ch.ivy.add.portalkit.service.integrators.TaskServiceIntegratorData out;
' #txt
Te0 f27 actionTable 'out=in;
out.errors=wsResponse.delegateTaskResponse.result.errors;
out.task=wsResponse.delegateTaskResponse.result.ivyTask;
' #txt
Te0 f27 cache '{/cache false /invalidation false /scope 0 /groupname ""/lifetime_group "0"/invalidation_time_group ""/identifier ""/lifetime_entry "0"/invalidation_time_entry ""}' #txt
Te0 f27 timeout 10 #txt
Te0 f27 beanConfig '"KEY_PASSWORD=
KEY_AXIS_PORTNAME=TaskServicePort
KEY_WEBSERVICECONFIG_ID=146B90974252183F
KEY_DOMAIN=
KEY_USERNAME=
KEY_OPERATION=delegateTask
KEY_AUTHENTICATION_KIND=0
KEY_HOST=
KEY_USE_AUTHENTICATION=false
KEY_AXIS_CSL_PARAMETER_DATA=""arg0.delegateTask.id__@@__Long__@@__in.taskId"",""arg0.delegateTask.ivySecurityMember__@@__ch.ivy.ws.addon.IvySecurityMember__@@__in.ivySecurityMember"",""arg0.delegateTask.serverUrl__@@__String__@@__in.serverUrl"""' #txt
Te0 f27 exceptionHandler 14E8BC51CC56193A-f7-buffer #txt
Te0 f27 timeoutExceptionHandler 14E8BC51CC56193A-f7-buffer #txt
Te0 f27 returningObjectList '[wsResponse]' #txt
Te0 f27 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>with default settings</name>
        <nameStyle>21,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Te0 f27 1342 260 36 24 5 -32 #rect
Te0 f27 @|WebServiceIcon #fIcon
Te0 f29 expr in #txt
Te0 f29 outCond in.server.isNTLMAuthentication #txt
Te0 f29 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>yes</name>
        <nameStyle>3,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Te0 f29 1152 206 1152 260 #arcP
Te0 f29 0 0.24074074074074073 -16 0 #arcLabel
Te0 f30 expr out #txt
Te0 f30 1152 284 1152 355 #arcP
Te0 f31 expr in #txt
Te0 f31 1166 192 1360 260 #arcP
Te0 f31 1 1360 192 #addKink
Te0 f31 0 0.6554122903055368 0 0 #arcLabel
Te0 f32 expr out #txt
Te0 f32 1360 284 1165 368 #arcP
Te0 f32 1 1360 368 #addKink
Te0 f32 1 0.3139694183456486 0 0 #arcLabel
Te0 f33 type ch.ivy.add.portalkit.service.integrators.TaskServiceIntegratorData #txt
Te0 f33 83 707 26 26 14 0 #rect
Te0 f33 @|EndSubIcon #fIcon
Te0 f34 type ch.ivy.add.portalkit.service.integrators.TaskServiceIntegratorData #txt
Te0 f34 actionDecl 'ch.ivy.add.portalkit.service.integrators.TaskServiceIntegratorData out;
' #txt
Te0 f34 actionTable 'out=in;
out.errors=wsResponse.findTasksByCriteriaResponse.result.errors;
out.tasks=wsResponse.findTasksByCriteriaResponse.result.ivyTasks;
' #txt
Te0 f34 cache '{/cache false /invalidation false /scope 0 /groupname ""/lifetime_group "0"/invalidation_time_group ""/identifier ""/lifetime_entry "0"/invalidation_time_entry ""}' #txt
Te0 f34 timeout 10 #txt
Te0 f34 beanConfig '"KEY_PASSWORD=
KEY_AXIS_PORTNAME=TaskServicePort
KEY_WEBSERVICECONFIG_ID=146B90974252183F
KEY_DOMAIN=
KEY_USERNAME=
KEY_OPERATION=findTasksByCriteria
KEY_AUTHENTICATION_KIND=0
KEY_HOST=
KEY_USE_AUTHENTICATION=false
KEY_AXIS_CSL_PARAMETER_DATA=""arg0.findTasksByCriteria.serverUrl__@@__String__@@__in.serverUrl"",""arg0.findTasksByCriteria.taskSearchCriteria__@@__ch.ivy.ws.addon.TaskSearchCriteria__@@__in.taskSearchCriteria"""' #txt
Te0 f34 exceptionHandler 14E8BC51CC56193A-f7-buffer #txt
Te0 f34 timeoutExceptionHandler 14E8BC51CC56193A-f7-buffer #txt
Te0 f34 returningObjectList '[wsResponse]' #txt
Te0 f34 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Call TaskService:Search Tasks
with default settings</name>
        <nameStyle>30,7
21,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Te0 f34 342 612 36 24 20 -2 #rect
Te0 f34 @|WebServiceIcon #fIcon
Te0 f35 inParamDecl '<ch.ivy.ws.addon.TaskSearchCriteria taskSearchCriteria,java.lang.String endpoint,ch.ivy.addon.portalkit.persistence.domain.Server server> param;' #txt
Te0 f35 inParamTable 'out.endpoint=param.endpoint;
out.server=param.server;
out.taskSearchCriteria=param.taskSearchCriteria;
' #txt
Te0 f35 outParamDecl '<List<ch.ivy.ws.addon.WsException> errors,List<ch.ivy.ws.addon.IvyTask> tasks> result;
' #txt
Te0 f35 outParamTable 'result.errors=in.errors;
result.tasks=in.tasks;
' #txt
Te0 f35 actionDecl 'ch.ivy.add.portalkit.service.integrators.TaskServiceIntegratorData out;
' #txt
Te0 f35 callSignature findTasksByCriteria(ch.ivy.ws.addon.TaskSearchCriteria,String,ch.ivy.addon.portalkit.persistence.domain.Server) #txt
Te0 f35 type ch.ivy.add.portalkit.service.integrators.TaskServiceIntegratorData #txt
Te0 f35 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>findTasksByCriteria(TaskSearchCriteria,String,Server)</name>
    </language>
</elementInfo>
' #txt
Te0 f35 83 411 26 26 14 0 #rect
Te0 f35 @|StartSubIcon #fIcon
Te0 f36 type ch.ivy.add.portalkit.service.integrators.TaskServiceIntegratorData #txt
Te0 f36 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>User NTLM Authentication?</name>
        <nameStyle>25,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Te0 f36 82 530 28 28 14 -22 #rect
Te0 f36 @|AlternativeIcon #fIcon
Te0 f37 type ch.ivy.add.portalkit.service.integrators.TaskServiceIntegratorData #txt
Te0 f37 actionDecl 'ch.ivy.add.portalkit.service.integrators.TaskServiceIntegratorData out;
' #txt
Te0 f37 actionTable 'out=in;
out.errors=wsResponse.findTasksByCriteriaResponse.result.errors;
out.tasks=wsResponse.findTasksByCriteriaResponse.result.ivyTasks;
' #txt
Te0 f37 cache '{/cache false /invalidation false /scope 0 /groupname ""/lifetime_group "0"/invalidation_time_group ""/identifier ""/lifetime_entry "0"/invalidation_time_entry ""}' #txt
Te0 f37 timeout 10 #txt
Te0 f37 beanConfig '"KEY_PASSWORD=<%\\=ivy.var.PortalWSPassword%>
KEY_AXIS_PORTNAME=TaskServicePort
KEY_WEBSERVICECONFIG_ID=146B90974252183F
KEY_DOMAIN=<%\\=ivy.var.PortalWSDomain%>
KEY_USERNAME=<%\\=ivy.var.PortalWSUsername%>
KEY_OPERATION=findTasksByCriteria
KEY_AUTHENTICATION_KIND=4
KEY_HOST=<%\\=ivy.var.PortalWSHost%>
KEY_USE_AUTHENTICATION=true
KEY_AXIS_CSL_PARAMETER_DATA=""arg0.findTasksByCriteria.serverUrl__@@__String__@@__in.serverUrl"",""arg0.findTasksByCriteria.taskSearchCriteria__@@__ch.ivy.ws.addon.TaskSearchCriteria__@@__in.taskSearchCriteria"""' #txt
Te0 f37 exceptionHandler 14E8BC51CC56193A-f7-buffer #txt
Te0 f37 timeoutExceptionHandler 14E8BC51CC56193A-f7-buffer #txt
Te0 f37 returningObjectList '[wsResponse]' #txt
Te0 f37 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Call TaskService:SearchTasks
with NTLM Override
and Global Variable Settings</name>
        <nameStyle>29,7
47,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Te0 f37 78 612 36 24 20 -2 #rect
Te0 f37 @|WebServiceIcon #fIcon
Te0 f39 expr in #txt
Te0 f39 outCond in.server.isNTLMAuthentication #txt
Te0 f39 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>yes</name>
        <nameStyle>3,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Te0 f39 96 558 96 612 #arcP
Te0 f40 expr out #txt
Te0 f40 96 636 96 707 #arcP
Te0 f41 expr in #txt
Te0 f41 110 544 360 612 #arcP
Te0 f41 1 360 544 #addKink
Te0 f41 0 0.7302200350148694 0 0 #arcLabel
Te0 f42 expr out #txt
Te0 f42 360 636 109 720 #arcP
Te0 f42 1 360 720 #addKink
Te0 f42 1 0.3198727889829455 0 0 #arcLabel
Te0 f45 inParamDecl '<ch.ivy.addon.portalkit.persistence.domain.Server server,java.lang.String userName,java.lang.String endpoint,java.lang.Long taskId> param;' #txt
Te0 f45 inParamTable 'out.endpoint=param.endpoint;
out.server=param.server;
out.taskId=param.taskId;
out.userName=param.userName;
' #txt
Te0 f45 outParamDecl '<List<ch.ivy.ws.addon.WsException> errors,java.lang.Boolean canUserResumeTask> result;
' #txt
Te0 f45 outParamTable 'result.errors=in.errors;
result.canUserResumeTask=in.canUserResumeTask;
' #txt
Te0 f45 actionDecl 'ch.ivy.add.portalkit.service.integrators.TaskServiceIntegratorData out;
' #txt
Te0 f45 callSignature canUserResumeTask(ch.ivy.addon.portalkit.persistence.domain.Server,String,String,Long) #txt
Te0 f45 type ch.ivy.add.portalkit.service.integrators.TaskServiceIntegratorData #txt
Te0 f45 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>canUserResumeTask(Server,String,String,Long)</name>
        <nameStyle>44,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Te0 f45 595 403 26 26 14 0 #rect
Te0 f45 @|StartSubIcon #fIcon
Te0 f46 type ch.ivy.add.portalkit.service.integrators.TaskServiceIntegratorData #txt
Te0 f46 actionDecl 'ch.ivy.add.portalkit.service.integrators.TaskServiceIntegratorData out;
' #txt
Te0 f46 actionTable 'out=in;
out.canUserResumeTask=wsResponse.canUserResumeTaskResponse.result.canUserResumeTask;
out.errors=wsResponse.canUserResumeTaskResponse.result.errors;
' #txt
Te0 f46 cache '{/cache false /invalidation false /scope 0 /groupname ""/lifetime_group "0"/invalidation_time_group ""/identifier ""/lifetime_entry "0"/invalidation_time_entry ""}' #txt
Te0 f46 timeout 10 #txt
Te0 f46 beanConfig '"KEY_PASSWORD=
KEY_AXIS_PORTNAME=TaskServicePort
KEY_WEBSERVICECONFIG_ID=146B90974252183F
KEY_DOMAIN=
KEY_USERNAME=
KEY_OPERATION=canUserResumeTask
KEY_AUTHENTICATION_KIND=0
KEY_HOST=
KEY_USE_AUTHENTICATION=false
KEY_AXIS_CSL_PARAMETER_DATA=""arg0.canUserResumeTask.id__@@__Long__@@__in.taskId"",""arg0.canUserResumeTask.userName__@@__String__@@__in.userName"""' #txt
Te0 f46 exceptionHandler 14E8BC51CC56193A-f7-buffer #txt
Te0 f46 timeoutExceptionHandler 14E8BC51CC56193A-f7-buffer #txt
Te0 f46 returningObjectList '[wsResponse]' #txt
Te0 f46 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Call TaskService:Search Tasks
with default settings</name>
        <nameStyle>30,7
21,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Te0 f46 822 604 36 24 20 -2 #rect
Te0 f46 @|WebServiceIcon #fIcon
Te0 f47 type ch.ivy.add.portalkit.service.integrators.TaskServiceIntegratorData #txt
Te0 f47 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>User NTLM Authentication?</name>
        <nameStyle>25,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Te0 f47 594 522 28 28 14 -22 #rect
Te0 f47 @|AlternativeIcon #fIcon
Te0 f48 type ch.ivy.add.portalkit.service.integrators.TaskServiceIntegratorData #txt
Te0 f48 595 699 26 26 14 0 #rect
Te0 f48 @|EndSubIcon #fIcon
Te0 f49 type ch.ivy.add.portalkit.service.integrators.TaskServiceIntegratorData #txt
Te0 f49 actionDecl 'ch.ivy.add.portalkit.service.integrators.TaskServiceIntegratorData out;
' #txt
Te0 f49 actionTable 'out=in;
out.canUserResumeTask=wsResponse.canUserResumeTaskResponse.result.canUserResumeTask;
out.errors=wsResponse.canUserResumeTaskResponse.result.errors;
' #txt
Te0 f49 cache '{/cache false /invalidation false /scope 0 /groupname ""/lifetime_group "0"/invalidation_time_group ""/identifier ""/lifetime_entry "0"/invalidation_time_entry ""}' #txt
Te0 f49 timeout 10 #txt
Te0 f49 beanConfig '"KEY_PASSWORD=<%\\=ivy.var.PortalWSPassword%>
KEY_AXIS_PORTNAME=TaskServicePort
KEY_WEBSERVICECONFIG_ID=146B90974252183F
KEY_DOMAIN=<%\\=ivy.var.PortalWSDomain%>
KEY_USERNAME=<%\\=ivy.var.PortalWSUsername%>
KEY_OPERATION=canUserResumeTask
KEY_AUTHENTICATION_KIND=4
KEY_HOST=<%\\=ivy.var.PortalWSHost%>
KEY_USE_AUTHENTICATION=true
KEY_AXIS_CSL_PARAMETER_DATA=""arg0.canUserResumeTask.id__@@__Long__@@__in.taskId"",""arg0.canUserResumeTask.userName__@@__String__@@__in.userName"""' #txt
Te0 f49 exceptionHandler 14E8BC51CC56193A-f7-buffer #txt
Te0 f49 timeoutExceptionHandler 14E8BC51CC56193A-f7-buffer #txt
Te0 f49 returningObjectList '[wsResponse]' #txt
Te0 f49 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Call TaskService:SearchTasks
with NTLM Override
and Global Variable Settings</name>
        <nameStyle>29,7
47,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Te0 f49 590 604 36 24 20 -2 #rect
Te0 f49 @|WebServiceIcon #fIcon
Te0 f51 expr in #txt
Te0 f51 outCond in.server.isNTLMAuthentication #txt
Te0 f51 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>yes</name>
        <nameStyle>3,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Te0 f51 608 550 608 604 #arcP
Te0 f52 expr out #txt
Te0 f52 608 628 608 699 #arcP
Te0 f53 expr in #txt
Te0 f53 622 536 840 604 #arcP
Te0 f53 1 840 536 #addKink
Te0 f53 0 0.7302200350148694 0 0 #arcLabel
Te0 f54 expr out #txt
Te0 f54 840 628 621 712 #arcP
Te0 f54 1 840 712 #addKink
Te0 f54 1 0.3353862987498499 0 0 #arcLabel
Te0 f43 inParamDecl '<java.lang.String endpoint,java.lang.String username,ch.ivy.addon.portalkit.persistence.domain.Server server,java.lang.String noteContent,java.lang.Long taskId> param;' #txt
Te0 f43 inParamTable 'out.endpoint=param.endpoint;
out.noteContent=param.noteContent;
out.server=param.server;
out.taskId=param.taskId;
out.userName=param.username;
' #txt
Te0 f43 outParamDecl '<List<ch.ivy.ws.addon.WsException> errors,ch.ivy.ws.addon.IvyNote note> result;
' #txt
Te0 f43 outParamTable 'result.errors=in.errors;
result.note=in.note;
' #txt
Te0 f43 actionDecl 'ch.ivy.add.portalkit.service.integrators.TaskServiceIntegratorData out;
' #txt
Te0 f43 callSignature createNote(String,String,ch.ivy.addon.portalkit.persistence.domain.Server,String,Long) #txt
Te0 f43 type ch.ivy.add.portalkit.service.integrators.TaskServiceIntegratorData #txt
Te0 f43 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>createNote(endpoint,username,server,noteContent,taskId)</name>
        <nameStyle>55,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Te0 f43 1603 59 26 26 14 0 #rect
Te0 f43 @|StartSubIcon #fIcon
Te0 f44 type ch.ivy.add.portalkit.service.integrators.TaskServiceIntegratorData #txt
Te0 f44 1603 275 26 26 14 0 #rect
Te0 f44 @|EndSubIcon #fIcon
Te0 f55 type ch.ivy.add.portalkit.service.integrators.TaskServiceIntegratorData #txt
Te0 f55 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>NTLM authentication?</name>
        <nameStyle>20,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Te0 f55 1602 122 28 28 10 -23 #rect
Te0 f55 @|AlternativeIcon #fIcon
Te0 f56 expr out #txt
Te0 f56 1616 85 1616 122 #arcP
Te0 f63 type ch.ivy.add.portalkit.service.integrators.TaskServiceIntegratorData #txt
Te0 f63 actionDecl 'ch.ivy.add.portalkit.service.integrators.TaskServiceIntegratorData out;
' #txt
Te0 f63 actionTable 'out=in;
out.errors=wsResponse.createTaskNoteResponse.result.errors;
out.note=wsResponse.createTaskNoteResponse.result.note;
' #txt
Te0 f63 testData 'in.server.id=1;
in.server.isNTLMAuthentication=false;
in.server.isOnline=true;
in.server.path="http://aavn-ws-53:8081/ivy/ws/designer/PortalConnector";
in.taskId=6;
in.userName="Developer";
' #txt
Te0 f63 cache '{/cache false /invalidation false /scope 0 /groupname ""/lifetime_group "0"/invalidation_time_group ""/identifier ""/lifetime_entry "0"/invalidation_time_entry ""}' #txt
Te0 f63 timeout 10 #txt
Te0 f63 beanConfig '"KEY_PASSWORD=
KEY_AXIS_PORTNAME=TaskServicePort
KEY_WEBSERVICECONFIG_ID=146B90974252183F
KEY_DOMAIN=
KEY_USERNAME=
KEY_OPERATION=createTaskNote
KEY_AUTHENTICATION_KIND=0
KEY_HOST=
KEY_USE_AUTHENTICATION=false
KEY_AXIS_CSL_PARAMETER_DATA=""arg0.createTaskNote.id__@@__Long__@@__in.taskId"",""arg0.createTaskNote.message__@@__String__@@__in.noteContent"",""arg0.createTaskNote.user__@@__String__@@__in.userName"""' #txt
Te0 f63 returningObjectList '[wsResponse]' #txt
Te0 f63 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>with default authentication</name>
        <nameStyle>27,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Te0 f63 1806 204 36 24 4 13 #rect
Te0 f63 @|WebServiceIcon #fIcon
Te0 f61 expr out #txt
Te0 f61 1824 228 1629 288 #arcP
Te0 f61 1 1824 288 #addKink
Te0 f61 1 0.3239971163451311 0 0 #arcLabel
Te0 f57 type ch.ivy.add.portalkit.service.integrators.TaskServiceIntegratorData #txt
Te0 f57 actionDecl 'ch.ivy.add.portalkit.service.integrators.TaskServiceIntegratorData out;
' #txt
Te0 f57 actionTable 'out=in;
out.errors=wsResponse.createTaskNoteResponse.result.errors;
out.note=wsResponse.createTaskNoteResponse.result.note;
' #txt
Te0 f57 testData 'in.server.id=1;
in.server.isNTLMAuthentication=false;
in.server.isOnline=true;
in.server.path="http://aavn-ws-53:8081/ivy/ws/designer/PortalConnector";
in.taskId=6;
in.userName="Developer";
' #txt
Te0 f57 cache '{/cache false /invalidation false /scope 0 /groupname ""/lifetime_group "0"/invalidation_time_group ""/identifier ""/lifetime_entry "0"/invalidation_time_entry ""}' #txt
Te0 f57 timeout 10 #txt
Te0 f57 beanConfig '"KEY_PASSWORD=<%\\=ivy.var.PortalWSPassword%>
KEY_AXIS_PORTNAME=TaskServicePort
KEY_WEBSERVICECONFIG_ID=146B90974252183F
KEY_DOMAIN=<%\\=ivy.var.PortalWSDomain%>
KEY_USERNAME=<%\\=ivy.var.PortalWSUsername%>
KEY_OPERATION=createTaskNote
KEY_AUTHENTICATION_KIND=4
KEY_HOST=<%\\=ivy.var.PortalWSHost%>
KEY_USE_AUTHENTICATION=true
KEY_AXIS_CSL_PARAMETER_DATA=""arg0.createTaskNote.id__@@__Long__@@__in.taskId"",""arg0.createTaskNote.message__@@__String__@@__in.noteContent"",""arg0.createTaskNote.user__@@__String__@@__in.userName"""' #txt
Te0 f57 exceptionHandler 14E8BC51CC56193A-f7-buffer #txt
Te0 f57 returningObjectList '[wsResponse]' #txt
Te0 f57 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>with NTLM authentication</name>
        <nameStyle>24,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Te0 f57 1598 196 36 24 8 16 #rect
Te0 f57 @|WebServiceIcon #fIcon
Te0 f59 expr out #txt
Te0 f59 1616 220 1616 275 #arcP
Te0 f58 expr in #txt
Te0 f58 outCond in.server.isNTLMAuthentication #txt
Te0 f58 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>yes</name>
        <nameStyle>3,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Te0 f58 1616 150 1616 196 #arcP
Te0 f58 0 0.30434782608695654 -8 0 #arcLabel
Te0 f60 expr in #txt
Te0 f60 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>no</name>
        <nameStyle>2,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Te0 f60 1630 136 1824 204 #arcP
Te0 f60 1 1824 136 #addKink
Te0 f60 1 0.4117647058823529 -9 0 #arcLabel
Te0 f62 actionDecl 'ch.ivy.add.portalkit.service.integrators.TaskServiceIntegratorData out;
' #txt
Te0 f62 actionTable 'out=in;
' #txt
Te0 f62 actionCode 'import ch.ivy.addon.portalkit.support.UrlDetector;
UrlDetector detector = new UrlDetector();
in.serverUrl = detector.getHost(in.server.path);' #txt
Te0 f62 type ch.ivy.add.portalkit.service.integrators.TaskServiceIntegratorData #txt
Te0 f62 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>get server Url from 
portal connector path</name>
        <nameStyle>42,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Te0 f62 78 116 36 24 20 -17 #rect
Te0 f62 @|StepIcon #fIcon
Te0 f64 expr out #txt
Te0 f64 96 77 96 116 #arcP
Te0 f4 expr out #txt
Te0 f4 96 140 96 178 #arcP
Te0 f65 actionDecl 'ch.ivy.add.portalkit.service.integrators.TaskServiceIntegratorData out;
' #txt
Te0 f65 actionTable 'out=in;
' #txt
Te0 f65 actionCode 'import ch.ivy.addon.portalkit.support.UrlDetector;
UrlDetector detector = new UrlDetector();
in.serverUrl = detector.getHost(in.server.path);' #txt
Te0 f65 type ch.ivy.add.portalkit.service.integrators.TaskServiceIntegratorData #txt
Te0 f65 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>get server Url from 
portal connector path</name>
        <nameStyle>42,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Te0 f65 1134 116 36 24 20 -17 #rect
Te0 f65 @|StepIcon #fIcon
Te0 f66 expr out #txt
Te0 f66 1152 77 1152 116 #arcP
Te0 f28 expr out #txt
Te0 f28 1152 140 1152 178 #arcP
Te0 f67 actionDecl 'ch.ivy.add.portalkit.service.integrators.TaskServiceIntegratorData out;
' #txt
Te0 f67 actionTable 'out=in;
' #txt
Te0 f67 actionCode 'import ch.ivy.addon.portalkit.support.UrlDetector;
UrlDetector detector = new UrlDetector();
in.serverUrl = detector.getHost(in.server.path);' #txt
Te0 f67 type ch.ivy.add.portalkit.service.integrators.TaskServiceIntegratorData #txt
Te0 f67 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>get server Url from 
portal connector path</name>
        <nameStyle>42,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Te0 f67 78 468 36 24 20 -19 #rect
Te0 f67 @|StepIcon #fIcon
Te0 f68 expr out #txt
Te0 f68 96 492 96 530 #arcP
Te0 f69 expr out #txt
Te0 f69 96 437 96 468 #arcP
Te0 f38 actionDecl 'ch.ivy.add.portalkit.service.integrators.TaskServiceIntegratorData out;
' #txt
Te0 f38 actionTable 'out=in;
' #txt
Te0 f38 actionCode 'import ch.ivy.addon.portalkit.support.UrlDetector;
UrlDetector detector = new UrlDetector();
in.serverUrl = detector.getHost(in.server.path);' #txt
Te0 f38 type ch.ivy.add.portalkit.service.integrators.TaskServiceIntegratorData #txt
Te0 f38 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>get server Url from 
portal connector path</name>
        <nameStyle>42,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Te0 f38 686 92 36 24 20 -17 #rect
Te0 f38 @|StepIcon #fIcon
Te0 f70 expr out #txt
Te0 f70 704 116 704 130 #arcP
Te0 f71 expr out #txt
Te0 f71 704 77 704 92 #arcP
Te0 f15 actionDecl 'ch.ivy.add.portalkit.service.integrators.TaskServiceIntegratorData out;
' #txt
Te0 f15 actionTable 'out=in;
' #txt
Te0 f15 actionCode 'import ch.ivy.addon.portalkit.support.UrlDetector;
UrlDetector detector = new UrlDetector();
in.serverUrl = detector.getHost(in.server.path);' #txt
Te0 f15 type ch.ivy.add.portalkit.service.integrators.TaskServiceIntegratorData #txt
Te0 f15 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>get server Url from 
portal connector path</name>
        <nameStyle>42,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Te0 f15 590 468 36 24 22 -19 #rect
Te0 f15 @|StepIcon #fIcon
Te0 f72 expr out #txt
Te0 f72 608 429 608 468 #arcP
Te0 f50 expr out #txt
Te0 f50 608 492 608 522 #arcP
Te0 f73 type ch.ivy.add.portalkit.service.integrators.TaskServiceIntegratorData #txt
Te0 f73 actionDecl 'ch.ivy.add.portalkit.service.integrators.TaskServiceIntegratorData out;
' #txt
Te0 f73 actionTable 'out=in;
out.allTasks=wsResponse.findAllRunningTasksResponse.result.allTasks;
out.errors=wsResponse.findAllRunningTasksResponse.result.errors;
out.tasks=wsResponse.findAllRunningTasksResponse.result.personalTasks;
' #txt
Te0 f73 cache '{/cache false /invalidation false /scope 0 /groupname ""/lifetime_group "0"/invalidation_time_group ""/identifier ""/lifetime_entry "0"/invalidation_time_entry ""}' #txt
Te0 f73 timeout 10 #txt
Te0 f73 beanConfig '"KEY_PASSWORD=<%\\=ivy.var.PortalWSPassword%>
KEY_AXIS_PORTNAME=TaskServicePort
KEY_WEBSERVICECONFIG_ID=146B90974252183F
KEY_DOMAIN=<%\\=ivy.var.PortalWSDomain%>
KEY_USERNAME=<%\\=ivy.var.PortalWSUsername%>
KEY_OPERATION=findAllRunningTasks
KEY_AUTHENTICATION_KIND=4
KEY_HOST=<%\\=ivy.var.PortalWSHost%>
KEY_USE_AUTHENTICATION=true
KEY_AXIS_CSL_PARAMETER_DATA=""arg0.findAllRunningTasks.apps__@@__Array<String>__@@__in.applicationNames"",""arg0.findAllRunningTasks.serverId__@@__Long__@@__in.server.id"",""arg0.findAllRunningTasks.serverUrl__@@__String__@@__in.serverUrl"",""arg0.findAllRunningTasks.user__@@__String__@@__in.userName"""' #txt
Te0 f73 exceptionHandler 14E8BC51CC56193A-f7-buffer #txt
Te0 f73 timeoutExceptionHandler 14E8BC51CC56193A-f7-buffer #txt
Te0 f73 returningObjectList '[wsResponse]' #txt
Te0 f73 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Call TaskService:FindAllRunningTasks
with NTLM Override
and Global Variable Settings</name>
        <nameStyle>37,7
47,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Te0 f73 1134 644 36 24 20 -2 #rect
Te0 f73 @|WebServiceIcon #fIcon
Te0 f74 inParamDecl '<java.lang.String endpoint,java.lang.String userName,List<java.lang.String> applicationNames,ch.ivy.addon.portalkit.persistence.domain.Server server> param;' #txt
Te0 f74 inParamTable 'out.applicationNames=param.applicationNames;
out.endpoint=param.endpoint;
out.server=param.server;
out.userName=param.userName;
' #txt
Te0 f74 outParamDecl '<List<ch.ivy.ws.addon.WsException> errors,List<ch.ivy.ws.addon.IvyTask> personalTasks,List<ch.ivy.ws.addon.IvyTask> allTasks> result;
' #txt
Te0 f74 outParamTable 'result.errors=in.errors;
result.personalTasks=in.tasks;
result.allTasks=in.allTasks;
' #txt
Te0 f74 actionDecl 'ch.ivy.add.portalkit.service.integrators.TaskServiceIntegratorData out;
' #txt
Te0 f74 callSignature findAllRunningTasks(String,String,List<String>,ch.ivy.addon.portalkit.persistence.domain.Server) #txt
Te0 f74 type ch.ivy.add.portalkit.service.integrators.TaskServiceIntegratorData #txt
Te0 f74 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>findAllRunningTasks(String,String,List&lt;String&gt;,Server)</name>
        <nameStyle>54,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Te0 f74 1139 435 26 26 14 0 #rect
Te0 f74 @|StartSubIcon #fIcon
Te0 f75 actionDecl 'ch.ivy.add.portalkit.service.integrators.TaskServiceIntegratorData out;
' #txt
Te0 f75 actionTable 'out=in;
' #txt
Te0 f75 actionCode 'import ch.ivy.addon.portalkit.support.UrlDetector;
UrlDetector detector = new UrlDetector();
in.serverUrl = detector.getHost(in.server.path);' #txt
Te0 f75 type ch.ivy.add.portalkit.service.integrators.TaskServiceIntegratorData #txt
Te0 f75 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>get server Url from 
portal connector path</name>
        <nameStyle>42,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Te0 f75 1134 500 36 24 20 -17 #rect
Te0 f75 @|StepIcon #fIcon
Te0 f76 type ch.ivy.add.portalkit.service.integrators.TaskServiceIntegratorData #txt
Te0 f76 actionDecl 'ch.ivy.add.portalkit.service.integrators.TaskServiceIntegratorData out;
' #txt
Te0 f76 actionTable 'out=in;
out.allTasks=wsResponse.findAllRunningTasksResponse.result.allTasks;
out.errors=wsResponse.findAllRunningTasksResponse.result.errors;
out.tasks=wsResponse.findAllRunningTasksResponse.result.personalTasks;
' #txt
Te0 f76 cache '{/cache false /invalidation false /scope 0 /groupname ""/lifetime_group "0"/invalidation_time_group ""/identifier ""/lifetime_entry "0"/invalidation_time_entry ""}' #txt
Te0 f76 timeout 10 #txt
Te0 f76 beanConfig '"KEY_PASSWORD=
KEY_AXIS_PORTNAME=TaskServicePort
KEY_WEBSERVICECONFIG_ID=146B90974252183F
KEY_DOMAIN=
KEY_USERNAME=
KEY_OPERATION=findAllRunningTasks
KEY_AUTHENTICATION_KIND=0
KEY_HOST=
KEY_USE_AUTHENTICATION=false
KEY_AXIS_CSL_PARAMETER_DATA=""arg0.findAllRunningTasks.apps__@@__Array<String>__@@__in.applicationNames"",""arg0.findAllRunningTasks.serverId__@@__Long__@@__in.server.id"",""arg0.findAllRunningTasks.serverUrl__@@__String__@@__in.serverUrl"",""arg0.findAllRunningTasks.user__@@__String__@@__in.userName"""' #txt
Te0 f76 exceptionHandler 14E8BC51CC56193A-f7-buffer #txt
Te0 f76 timeoutExceptionHandler 14E8BC51CC56193A-f7-buffer #txt
Te0 f76 returningObjectList '[wsResponse]' #txt
Te0 f76 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Call TaskService:FindAllRunningTasks
with default settings</name>
        <nameStyle>37,7
21,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Te0 f76 1398 644 36 24 20 -2 #rect
Te0 f76 @|WebServiceIcon #fIcon
Te0 f77 type ch.ivy.add.portalkit.service.integrators.TaskServiceIntegratorData #txt
Te0 f77 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>User NTLM Authentication?</name>
        <nameStyle>25,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Te0 f77 1138 562 28 28 14 -22 #rect
Te0 f77 @|AlternativeIcon #fIcon
Te0 f78 type ch.ivy.add.portalkit.service.integrators.TaskServiceIntegratorData #txt
Te0 f78 1139 739 26 26 14 0 #rect
Te0 f78 @|EndSubIcon #fIcon
Te0 f79 expr in #txt
Te0 f79 outCond in.server.isNTLMAuthentication #txt
Te0 f79 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>yes</name>
        <nameStyle>3,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Te0 f79 1152 590 1152 644 #arcP
Te0 f80 expr out #txt
Te0 f80 1152 668 1152 739 #arcP
Te0 f81 expr in #txt
Te0 f81 1166 576 1416 644 #arcP
Te0 f81 1 1416 576 #addKink
Te0 f81 0 0.7302200350148694 0 0 #arcLabel
Te0 f82 expr out #txt
Te0 f82 1416 668 1165 752 #arcP
Te0 f82 1 1416 752 #addKink
Te0 f82 1 0.3198727889829455 0 0 #arcLabel
Te0 f83 expr out #txt
Te0 f83 1152 461 1152 500 #arcP
Te0 f84 expr out #txt
Te0 f84 1152 524 1152 562 #arcP
Te0 f85 inParamDecl '<java.lang.String endpoint,ch.ivy.addon.portalkit.persistence.domain.Server server,java.lang.Long caseId> param;' #txt
Te0 f85 inParamTable 'out.caseId=param.caseId;
out.endpoint=param.endpoint;
out.server=param.server;
' #txt
Te0 f85 outParamDecl '<List<ch.ivy.ws.addon.WsException> errors,List<ch.ivy.ws.addon.IvyTask> tasks> result;
' #txt
Te0 f85 outParamTable 'result.errors=in.errors;
result.tasks=in.tasks;
' #txt
Te0 f85 actionDecl 'ch.ivy.add.portalkit.service.integrators.TaskServiceIntegratorData out;
' #txt
Te0 f85 callSignature findTasksOfCase(String,ch.ivy.addon.portalkit.persistence.domain.Server,Long) #txt
Te0 f85 type ch.ivy.add.portalkit.service.integrators.TaskServiceIntegratorData #txt
Te0 f85 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>findTasksOfCase(String,Server,String)</name>
        <nameStyle>44,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Te0 f85 1611 443 26 26 14 0 #rect
Te0 f85 @|StartSubIcon #fIcon
Te0 f86 type ch.ivy.add.portalkit.service.integrators.TaskServiceIntegratorData #txt
Te0 f86 actionDecl 'ch.ivy.add.portalkit.service.integrators.TaskServiceIntegratorData out;
' #txt
Te0 f86 actionTable 'out=in;
out.errors=wsResponse.findTasksOfCaseResponse.result.errors;
out.tasks=wsResponse.findTasksOfCaseResponse.result.ivyTasks;
' #txt
Te0 f86 cache '{/cache false /invalidation false /scope 0 /groupname ""/lifetime_group "0"/invalidation_time_group ""/identifier ""/lifetime_entry "0"/invalidation_time_entry ""}' #txt
Te0 f86 timeout 10 #txt
Te0 f86 beanConfig '"KEY_PASSWORD=
KEY_AXIS_PORTNAME=TaskServicePort
KEY_WEBSERVICECONFIG_ID=146B90974252183F
KEY_DOMAIN=
KEY_USERNAME=
KEY_OPERATION=findTasksOfCase
KEY_AUTHENTICATION_KIND=0
KEY_HOST=
KEY_USE_AUTHENTICATION=false
KEY_AXIS_CSL_PARAMETER_DATA=""arg0.findTasksOfCase.id__@@__Long__@@__in.caseId"",""arg0.findTasksOfCase.serverUrl__@@__String__@@__in.serverUrl"""' #txt
Te0 f86 exceptionHandler 14E8BC51CC56193A-f7-buffer #txt
Te0 f86 timeoutExceptionHandler 14E8BC51CC56193A-f7-buffer #txt
Te0 f86 returningObjectList '[wsResponse]' #txt
Te0 f86 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>with default settings</name>
        <nameStyle>21,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Te0 f86 1870 652 36 24 20 -2 #rect
Te0 f86 @|WebServiceIcon #fIcon
Te0 f87 actionDecl 'ch.ivy.add.portalkit.service.integrators.TaskServiceIntegratorData out;
' #txt
Te0 f87 actionTable 'out=in;
' #txt
Te0 f87 actionCode 'import ch.ivy.addon.portalkit.support.UrlDetector;
UrlDetector detector = new UrlDetector();
in.serverUrl = detector.getHost(in.server.path);' #txt
Te0 f87 type ch.ivy.add.portalkit.service.integrators.TaskServiceIntegratorData #txt
Te0 f87 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>get server Url from 
portal connector path</name>
        <nameStyle>42,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Te0 f87 1606 508 36 24 20 -17 #rect
Te0 f87 @|StepIcon #fIcon
Te0 f88 type ch.ivy.add.portalkit.service.integrators.TaskServiceIntegratorData #txt
Te0 f88 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>User NTLM Authentication?</name>
        <nameStyle>25,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Te0 f88 1610 570 28 28 14 -22 #rect
Te0 f88 @|AlternativeIcon #fIcon
Te0 f89 type ch.ivy.add.portalkit.service.integrators.TaskServiceIntegratorData #txt
Te0 f89 actionDecl 'ch.ivy.add.portalkit.service.integrators.TaskServiceIntegratorData out;
' #txt
Te0 f89 actionTable 'out=in;
out.errors=wsResponse.findTasksOfCaseResponse.result.errors;
out.tasks=wsResponse.findTasksOfCaseResponse.result.ivyTasks;
' #txt
Te0 f89 cache '{/cache false /invalidation false /scope 0 /groupname ""/lifetime_group "0"/invalidation_time_group ""/identifier ""/lifetime_entry "0"/invalidation_time_entry ""}' #txt
Te0 f89 timeout 10 #txt
Te0 f89 beanConfig '"KEY_PASSWORD=<%\\=ivy.var.PortalWSPassword%>
KEY_AXIS_PORTNAME=TaskServicePort
KEY_WEBSERVICECONFIG_ID=146B90974252183F
KEY_DOMAIN=<%\\=ivy.var.PortalWSDomain%>
KEY_USERNAME=<%\\=ivy.var.PortalWSUsername%>
KEY_OPERATION=findTasksOfCase
KEY_AUTHENTICATION_KIND=4
KEY_HOST=<%\\=ivy.var.PortalWSHost%>
KEY_USE_AUTHENTICATION=true
KEY_AXIS_CSL_PARAMETER_DATA=""arg0.findTasksOfCase.id__@@__Long__@@__in.caseId"",""arg0.findTasksOfCase.serverUrl__@@__String__@@__in.serverUrl"""' #txt
Te0 f89 exceptionHandler 14E8BC51CC56193A-f7-buffer #txt
Te0 f89 timeoutExceptionHandler 14E8BC51CC56193A-f7-buffer #txt
Te0 f89 returningObjectList '[wsResponse]' #txt
Te0 f89 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>with NTLM Override</name>
        <nameStyle>18,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Te0 f89 1606 652 36 24 20 -2 #rect
Te0 f89 @|WebServiceIcon #fIcon
Te0 f90 type ch.ivy.add.portalkit.service.integrators.TaskServiceIntegratorData #txt
Te0 f90 1611 747 26 26 14 0 #rect
Te0 f90 @|EndSubIcon #fIcon
Te0 f91 expr in #txt
Te0 f91 outCond in.server.isNTLMAuthentication #txt
Te0 f91 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>yes</name>
        <nameStyle>3,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Te0 f91 1624 598 1624 652 #arcP
Te0 f92 expr out #txt
Te0 f92 1624 676 1624 747 #arcP
Te0 f93 expr in #txt
Te0 f93 1638 584 1888 652 #arcP
Te0 f93 1 1888 584 #addKink
Te0 f93 0 0.7302200350148694 0 0 #arcLabel
Te0 f94 expr out #txt
Te0 f94 1888 676 1637 760 #arcP
Te0 f94 1 1888 760 #addKink
Te0 f94 1 0.3198727889829455 0 0 #arcLabel
Te0 f95 expr out #txt
Te0 f95 1624 469 1624 508 #arcP
Te0 f96 expr out #txt
Te0 f96 1624 532 1624 570 #arcP
>Proto Te0 .type ch.ivy.add.portalkit.service.integrators.TaskServiceIntegratorData #txt
>Proto Te0 .processKind CALLABLE_SUB #txt
>Proto Te0 0 0 32 24 18 0 #rect
>Proto Te0 @|BIcon #fIcon
Te0 f2 out f5 tail #connect
Te0 f5 head f3 mainIn #connect
Te0 f3 mainOut f6 tail #connect
Te0 f6 head f1 mainIn #connect
Te0 f7 mainOut f9 tail #connect
Te0 f9 head f8 mainIn #connect
Te0 f2 out f14 tail #connect
Te0 f14 head f13 mainIn #connect
Te0 f13 mainOut f10 tail #connect
Te0 f10 head f1 mainIn #connect
Te0 f12 out f18 tail #connect
Te0 f18 head f16 mainIn #connect
Te0 f16 mainOut f19 tail #connect
Te0 f19 head f17 mainIn #connect
Te0 f12 out f21 tail #connect
Te0 f21 head f20 mainIn #connect
Te0 f20 mainOut f22 tail #connect
Te0 f22 head f17 mainIn #connect
Te0 f25 out f29 tail #connect
Te0 f29 head f23 mainIn #connect
Te0 f23 mainOut f30 tail #connect
Te0 f30 head f24 mainIn #connect
Te0 f25 out f31 tail #connect
Te0 f31 head f27 mainIn #connect
Te0 f27 mainOut f32 tail #connect
Te0 f32 head f24 mainIn #connect
Te0 f36 out f39 tail #connect
Te0 f39 head f37 mainIn #connect
Te0 f37 mainOut f40 tail #connect
Te0 f40 head f33 mainIn #connect
Te0 f36 out f41 tail #connect
Te0 f41 head f34 mainIn #connect
Te0 f34 mainOut f42 tail #connect
Te0 f42 head f33 mainIn #connect
Te0 f47 out f51 tail #connect
Te0 f51 head f49 mainIn #connect
Te0 f49 mainOut f52 tail #connect
Te0 f52 head f48 mainIn #connect
Te0 f47 out f53 tail #connect
Te0 f53 head f46 mainIn #connect
Te0 f46 mainOut f54 tail #connect
Te0 f54 head f48 mainIn #connect
Te0 f43 mainOut f56 tail #connect
Te0 f56 head f55 in #connect
Te0 f63 mainOut f61 tail #connect
Te0 f61 head f44 mainIn #connect
Te0 f57 mainOut f59 tail #connect
Te0 f59 head f44 mainIn #connect
Te0 f55 out f58 tail #connect
Te0 f58 head f57 mainIn #connect
Te0 f55 out f60 tail #connect
Te0 f60 head f63 mainIn #connect
Te0 f0 mainOut f64 tail #connect
Te0 f64 head f62 mainIn #connect
Te0 f62 mainOut f4 tail #connect
Te0 f4 head f2 in #connect
Te0 f26 mainOut f66 tail #connect
Te0 f66 head f65 mainIn #connect
Te0 f65 mainOut f28 tail #connect
Te0 f28 head f25 in #connect
Te0 f67 mainOut f68 tail #connect
Te0 f68 head f36 in #connect
Te0 f35 mainOut f69 tail #connect
Te0 f69 head f67 mainIn #connect
Te0 f38 mainOut f70 tail #connect
Te0 f70 head f12 in #connect
Te0 f11 mainOut f71 tail #connect
Te0 f71 head f38 mainIn #connect
Te0 f45 mainOut f72 tail #connect
Te0 f72 head f15 mainIn #connect
Te0 f15 mainOut f50 tail #connect
Te0 f50 head f47 in #connect
Te0 f77 out f79 tail #connect
Te0 f79 head f73 mainIn #connect
Te0 f73 mainOut f80 tail #connect
Te0 f80 head f78 mainIn #connect
Te0 f77 out f81 tail #connect
Te0 f81 head f76 mainIn #connect
Te0 f76 mainOut f82 tail #connect
Te0 f82 head f78 mainIn #connect
Te0 f74 mainOut f83 tail #connect
Te0 f83 head f75 mainIn #connect
Te0 f75 mainOut f84 tail #connect
Te0 f84 head f77 in #connect
Te0 f88 out f91 tail #connect
Te0 f91 head f89 mainIn #connect
Te0 f89 mainOut f92 tail #connect
Te0 f92 head f90 mainIn #connect
Te0 f88 out f93 tail #connect
Te0 f93 head f86 mainIn #connect
Te0 f86 mainOut f94 tail #connect
Te0 f94 head f90 mainIn #connect
Te0 f85 mainOut f95 tail #connect
Te0 f95 head f87 mainIn #connect
Te0 f87 mainOut f96 tail #connect
Te0 f96 head f88 in #connect
