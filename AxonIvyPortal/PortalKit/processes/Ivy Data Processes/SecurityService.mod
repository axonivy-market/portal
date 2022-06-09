[Ivy]
1485F329FE84F01E 7.5.0 #module
>Proto >Proto Collection #zClass
Pt0 SecurityService Big #zClass
Pt0 B #cInfo
Pt0 #process
Pt0 @TextInP .type .type #zField
Pt0 @TextInP .processKind .processKind #zField
Pt0 @AnnotationInP-0n ai ai #zField
Pt0 @TextInP .xml .xml #zField
Pt0 @TextInP .responsibility .responsibility #zField
Pt0 @StartSub f0 '' #zField
Pt0 @EndSub f1 '' #zField
Pt0 @GridStep f10 '' #zField
Pt0 @CallSub f24 '' #zField
Pt0 @GridStep f20 '' #zField
Pt0 @PushWFArc f26 '' #zField
Pt0 @PushWFArc f27 '' #zField
Pt0 @PushWFArc f2 '' #zField
Pt0 @PushWFArc f3 '' #zField
Pt0 @StartSub f4 '' #zField
Pt0 @GridStep f5 '' #zField
Pt0 @PushWFArc f6 '' #zField
Pt0 @EndSub f7 '' #zField
Pt0 @CallSub f8 '' #zField
Pt0 @PushWFArc f9 '' #zField
Pt0 @PushWFArc f11 '' #zField
Pt0 @StartSub f12 '' #zField
Pt0 @GridStep f13 '' #zField
Pt0 @GridStep f14 '' #zField
Pt0 @EndSub f16 '' #zField
Pt0 @CallSub f17 '' #zField
Pt0 @PushWFArc f18 '' #zField
Pt0 @PushWFArc f21 '' #zField
Pt0 @PushWFArc f22 '' #zField
Pt0 @PushWFArc f15 '' #zField
Pt0 @StartSub f19 '' #zField
Pt0 @GridStep f25 '' #zField
Pt0 @CallSub f28 '' #zField
Pt0 @EndSub f29 '' #zField
Pt0 @PushWFArc f30 '' #zField
Pt0 @PushWFArc f33 '' #zField
Pt0 @PushWFArc f23 '' #zField
Pt0 @GridStep f31 '' #zField
Pt0 @EndSub f32 '' #zField
Pt0 @GridStep f34 '' #zField
Pt0 @CallSub f35 '' #zField
Pt0 @CallSub f36 '' #zField
Pt0 @GridStep f37 '' #zField
Pt0 @EndSub f38 '' #zField
Pt0 @StartSub f39 '' #zField
Pt0 @StartSub f40 '' #zField
Pt0 @PushWFArc f41 '' #zField
Pt0 @PushWFArc f42 '' #zField
Pt0 @PushWFArc f43 '' #zField
Pt0 @PushWFArc f44 '' #zField
Pt0 @PushWFArc f45 '' #zField
Pt0 @PushWFArc f46 '' #zField
Pt0 @PushWFArc f47 '' #zField
Pt0 @GridStep f48 '' #zField
Pt0 @CallSub f49 '' #zField
Pt0 @EndSub f50 '' #zField
Pt0 @StartSub f51 '' #zField
Pt0 @PushWFArc f52 '' #zField
Pt0 @PushWFArc f53 '' #zField
Pt0 @PushWFArc f54 '' #zField
Pt0 @EndSub f56 '' #zField
Pt0 @GridStep f58 '' #zField
Pt0 @CallSub f60 '' #zField
Pt0 @PushWFArc f61 '' #zField
Pt0 @PushWFArc f57 '' #zField
Pt0 @StartSub f55 '' #zField
Pt0 @PushWFArc f59 '' #zField
Pt0 @EndSub f63 '' #zField
Pt0 @GridStep f64 '' #zField
Pt0 @CallSub f65 '' #zField
Pt0 @GridStep f68 '' #zField
Pt0 @StartSub f70 '' #zField
Pt0 @PushWFArc f71 '' #zField
Pt0 @PushWFArc f74 '' #zField
Pt0 @PushWFArc f76 '' #zField
Pt0 @PushWFArc f77 '' #zField
>Proto Pt0 Pt0 SecurityService #zField
Pt0 f0 inParamDecl '<String username,String query,Integer startIndex,Integer count,java.util.List<String> fromRoles,java.util.List<String> excludedUsernames> param;' #txt
Pt0 f0 inParamTable 'out.count=param.count;
out.excludedUsernames=param.excludedUsernames;
out.fromRoles=param.fromRoles;
out.query=param.query;
out.startIndex=param.startIndex;
out.username=param.username;
' #txt
Pt0 f0 outParamDecl '<java.util.List<ch.ivy.addon.portalkit.ivydata.exception.PortalIvyDataException> errors,java.util.List<ch.ivy.addon.portalkit.dto.UserDTO> users> result;' #txt
Pt0 f0 outParamTable 'result.errors=in.errors;
result.users=in.users;
' #txt
Pt0 f0 callSignature findUsersOverAllApplications(String,String,Integer,Integer,java.util.List<String>,java.util.List<String>) #txt
Pt0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>findUsersOverAllApplications(String,String,Integer,Integer,List&lt;String&gt;,List&lt;String&gt;)</name>
    </language>
</elementInfo>
' #txt
Pt0 f0 128 80 32 32 -87 22 #rect
Pt0 f0 @|StartSubIcon #fIcon
Pt0 f1 785 81 30 30 0 15 #rect
Pt0 f1 @|EndSubIcon #fIcon
Pt0 f10 actionTable 'out=in;
' #txt
Pt0 f10 actionCode 'import ch.ivy.addon.portalkit.service.RegisteredApplicationService;

RegisteredApplicationService service = new RegisteredApplicationService();
out.apps = service.findActiveIvyAppsUserCanWorkOn(in.username);
' #txt
Pt0 f10 security system #txt
Pt0 f10 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Find applications can work on</name>
    </language>
</elementInfo>
' #txt
Pt0 f10 224 74 176 44 -81 -8 #rect
Pt0 f10 @|StepIcon #fIcon
Pt0 f24 processCall 'Functional Processes/ErrorHandler:handle(List<ch.ivy.addon.portalkit.ivydata.exception.PortalIvyDataException>)' #txt
Pt0 f24 requestActionDecl '<java.util.List<ch.ivy.addon.portalkit.ivydata.exception.PortalIvyDataException> exceptions> param;' #txt
Pt0 f24 requestMappingAction 'param.exceptions=in.errors;
' #txt
Pt0 f24 responseActionDecl 'ch.ivyteam.wf.processes.SecurityServiceData out;
' #txt
Pt0 f24 responseMappingAction 'out=in;
' #txt
Pt0 f24 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>ErrorHandler</name>
    </language>
</elementInfo>
' #txt
Pt0 f24 616 74 112 44 -35 -8 #rect
Pt0 f24 @|CallSubIcon #fIcon
Pt0 f20 actionTable 'out=in;
' #txt
Pt0 f20 actionCode 'import ch.ivy.addon.portalkit.ivydata.service.impl.SecurityService;
import ch.ivy.addon.portalkit.ivydata.dto.IvySecurityResultDTO;

IvySecurityResultDTO dto = SecurityService.newInstance().findUsers(in.query, in.apps, in.startIndex, in.count, in.fromRoles, in.excludedUsernames);
out.users = dto.users;
out.errors = dto.errors;
' #txt
Pt0 f20 security system #txt
Pt0 f20 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Find users</name>
    </language>
</elementInfo>
' #txt
Pt0 f20 448 74 112 44 -29 -8 #rect
Pt0 f20 @|StepIcon #fIcon
Pt0 f26 expr out #txt
Pt0 f26 400 96 448 96 #arcP
Pt0 f27 expr out #txt
Pt0 f27 560 96 616 96 #arcP
Pt0 f2 expr out #txt
Pt0 f2 160 96 224 96 #arcP
Pt0 f3 expr out #txt
Pt0 f3 728 96 785 96 #arcP
Pt0 f4 inParamDecl '<ch.ivyteam.ivy.application.IApplication application,String query,Integer startIndex,Integer count> param;' #txt
Pt0 f4 inParamTable 'out.application=param.application;
out.count=param.count;
out.query=param.query;
out.startIndex=param.startIndex;
' #txt
Pt0 f4 outParamDecl '<java.util.List<ch.ivy.addon.portalkit.dto.SecurityMemberDTO> members,java.util.List<ch.ivy.addon.portalkit.ivydata.exception.PortalIvyDataException> errors> result;' #txt
Pt0 f4 outParamTable 'result.members=in.securityMembers;
result.errors=in.errors;
' #txt
Pt0 f4 callSignature findSecurityMembers(ch.ivyteam.ivy.application.IApplication,String,Integer,Integer) #txt
Pt0 f4 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>findSecurityMembers(IApplication,String,Integer,Integer)</name>
    </language>
</elementInfo>
' #txt
Pt0 f4 129 561 30 30 -90 19 #rect
Pt0 f4 @|StartSubIcon #fIcon
Pt0 f5 actionTable 'out=in;
' #txt
Pt0 f5 actionCode 'import ch.ivy.addon.portalkit.ivydata.service.impl.SecurityService;
import ch.ivy.addon.portalkit.ivydata.dto.IvySecurityResultDTO;

IvySecurityResultDTO dto = SecurityService.newInstance().findSecurityMembers(in.query, in.application, in.startIndex, in.count);
out.securityMembers = dto.securityMembers;

out.errors = dto.errors;
' #txt
Pt0 f5 security system #txt
Pt0 f5 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Find security members</name>
    </language>
</elementInfo>
' #txt
Pt0 f5 224 554 144 44 -63 -8 #rect
Pt0 f5 @|StepIcon #fIcon
Pt0 f6 expr out #txt
Pt0 f6 159 576 224 576 #arcP
Pt0 f7 785 561 30 30 0 15 #rect
Pt0 f7 @|EndSubIcon #fIcon
Pt0 f8 processCall 'Functional Processes/ErrorHandler:handle(List<ch.ivy.addon.portalkit.ivydata.exception.PortalIvyDataException>)' #txt
Pt0 f8 requestActionDecl '<java.util.List<ch.ivy.addon.portalkit.ivydata.exception.PortalIvyDataException> exceptions> param;' #txt
Pt0 f8 requestMappingAction 'param.exceptions=in.errors;
' #txt
Pt0 f8 responseActionDecl 'ch.ivyteam.wf.processes.SecurityServiceData out;
' #txt
Pt0 f8 responseMappingAction 'out=in;
' #txt
Pt0 f8 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>ErrorHandler</name>
    </language>
</elementInfo>
' #txt
Pt0 f8 616 554 112 44 -35 -8 #rect
Pt0 f8 @|CallSubIcon #fIcon
Pt0 f9 expr out #txt
Pt0 f9 728 576 785 576 #arcP
Pt0 f11 expr out #txt
Pt0 f11 368 576 616 576 #arcP
Pt0 f12 inParamDecl '<String username,String query,Integer startIndex,Integer count> param;' #txt
Pt0 f12 inParamTable 'out.count=param.count;
out.query=param.query;
out.startIndex=param.startIndex;
out.username=param.username;
' #txt
Pt0 f12 outParamDecl '<java.util.List<ch.ivy.addon.portalkit.dto.SecurityMemberDTO> members,java.util.List<ch.ivy.addon.portalkit.ivydata.exception.PortalIvyDataException> errors> result;' #txt
Pt0 f12 outParamTable 'result.members=in.securityMembers;
result.errors=in.errors;
' #txt
Pt0 f12 callSignature findSecurityMembersOverAllApplications(String,String,Integer,Integer) #txt
Pt0 f12 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>findSecurityMembersOverAllApplications(String,String,Integer,Integer)</name>
    </language>
</elementInfo>
' #txt
Pt0 f12 129 465 30 30 -90 19 #rect
Pt0 f12 @|StartSubIcon #fIcon
Pt0 f13 actionTable 'out=in;
' #txt
Pt0 f13 actionCode 'import ch.ivy.addon.portalkit.ivydata.service.impl.SecurityService;
import ch.ivy.addon.portalkit.ivydata.dto.IvySecurityResultDTO;

IvySecurityResultDTO dto = SecurityService.newInstance().findSecurityMembers(in.query, in.apps, in.startIndex, in.count);
out.securityMembers = dto.securityMembers;

out.errors = dto.errors;
' #txt
Pt0 f13 security system #txt
Pt0 f13 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Find security members</name>
    </language>
</elementInfo>
' #txt
Pt0 f13 432 458 144 44 -63 -8 #rect
Pt0 f13 @|StepIcon #fIcon
Pt0 f14 actionTable 'out=in;
' #txt
Pt0 f14 actionCode 'import ch.ivy.addon.portalkit.service.RegisteredApplicationService;

RegisteredApplicationService service = new RegisteredApplicationService();
out.apps = service.findActiveIvyAppsUserCanWorkOn(in.username);
' #txt
Pt0 f14 security system #txt
Pt0 f14 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Find applications can work on</name>
    </language>
</elementInfo>
' #txt
Pt0 f14 224 458 176 44 -81 -8 #rect
Pt0 f14 @|StepIcon #fIcon
Pt0 f16 785 465 30 30 0 15 #rect
Pt0 f16 @|EndSubIcon #fIcon
Pt0 f17 processCall 'Functional Processes/ErrorHandler:handle(List<ch.ivy.addon.portalkit.ivydata.exception.PortalIvyDataException>)' #txt
Pt0 f17 requestActionDecl '<java.util.List<ch.ivy.addon.portalkit.ivydata.exception.PortalIvyDataException> exceptions> param;' #txt
Pt0 f17 requestMappingAction 'param.exceptions=in.errors;
' #txt
Pt0 f17 responseActionDecl 'ch.ivyteam.wf.processes.SecurityServiceData out;
' #txt
Pt0 f17 responseMappingAction 'out=in;
' #txt
Pt0 f17 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>ErrorHandler</name>
    </language>
</elementInfo>
' #txt
Pt0 f17 616 458 112 44 -35 -8 #rect
Pt0 f17 @|CallSubIcon #fIcon
Pt0 f18 expr out #txt
Pt0 f18 728 480 785 480 #arcP
Pt0 f21 expr out #txt
Pt0 f21 400 480 432 480 #arcP
Pt0 f22 expr out #txt
Pt0 f22 576 480 616 480 #arcP
Pt0 f15 expr out #txt
Pt0 f15 159 480 224 480 #arcP
Pt0 f19 inParamDecl '<ch.ivyteam.ivy.application.IApplication application,String query,Integer startIndex,Integer count,java.util.List<String> fromRoles,java.util.List<String> excludedUsernames> param;' #txt
Pt0 f19 inParamTable 'out.application=param.application;
out.count=param.count;
out.excludedUsernames=param.excludedUsernames;
out.fromRoles=param.fromRoles;
out.query=param.query;
out.startIndex=param.startIndex;
' #txt
Pt0 f19 outParamDecl '<java.util.List<ch.ivy.addon.portalkit.ivydata.exception.PortalIvyDataException> errors,java.util.List<ch.ivy.addon.portalkit.dto.UserDTO> users> result;' #txt
Pt0 f19 outParamTable 'result.errors=in.errors;
result.users=in.users;
' #txt
Pt0 f19 callSignature findUsers(ch.ivyteam.ivy.application.IApplication,String,Integer,Integer,java.util.List<String>,java.util.List<String>) #txt
Pt0 f19 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>findUsers(IApplication,String,Integer,Integer,List&lt;String&gt;,List&lt;String&gt;)</name>
    </language>
</elementInfo>
' #txt
Pt0 f19 129 177 30 30 -53 20 #rect
Pt0 f19 @|StartSubIcon #fIcon
Pt0 f25 actionTable 'out=in;
' #txt
Pt0 f25 actionCode 'import ch.ivy.addon.portalkit.ivydata.service.impl.SecurityService;
import ch.ivy.addon.portalkit.ivydata.dto.IvySecurityResultDTO;

IvySecurityResultDTO dto = SecurityService.newInstance().findUsers(in.query, in.application, in.startIndex, in.count, in.fromRoles, in.excludedUsernames);
out.users = dto.users;
out.errors = dto.errors;
' #txt
Pt0 f25 security system #txt
Pt0 f25 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Find users</name>
    </language>
</elementInfo>
' #txt
Pt0 f25 224 170 112 44 -29 -8 #rect
Pt0 f25 @|StepIcon #fIcon
Pt0 f28 processCall 'Functional Processes/ErrorHandler:handle(List<ch.ivy.addon.portalkit.ivydata.exception.PortalIvyDataException>)' #txt
Pt0 f28 requestActionDecl '<java.util.List<ch.ivy.addon.portalkit.ivydata.exception.PortalIvyDataException> exceptions> param;' #txt
Pt0 f28 requestMappingAction 'param.exceptions=in.errors;
' #txt
Pt0 f28 responseActionDecl 'ch.ivyteam.wf.processes.SecurityServiceData out;
' #txt
Pt0 f28 responseMappingAction 'out=in;
' #txt
Pt0 f28 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>ErrorHandler</name>
    </language>
</elementInfo>
' #txt
Pt0 f28 616 170 112 44 -35 -8 #rect
Pt0 f28 @|CallSubIcon #fIcon
Pt0 f29 785 177 30 30 0 15 #rect
Pt0 f29 @|EndSubIcon #fIcon
Pt0 f30 expr out #txt
Pt0 f30 728 192 785 192 #arcP
Pt0 f33 expr out #txt
Pt0 f33 336 192 616 192 #arcP
Pt0 f23 expr out #txt
Pt0 f23 159 192 224 192 #arcP
Pt0 f31 actionTable 'out=in;
' #txt
Pt0 f31 actionCode 'import ch.ivy.addon.portalkit.ivydata.service.impl.SecurityService;
import ch.ivy.addon.portalkit.ivydata.dto.IvySecurityResultDTO;

IvySecurityResultDTO dto = SecurityService.newInstance().findRoles(in.apps);
out.roles = dto.roles;
out.errors = dto.errors;
' #txt
Pt0 f31 security system #txt
Pt0 f31 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Find roles</name>
    </language>
</elementInfo>
' #txt
Pt0 f31 448 266 112 44 -27 -8 #rect
Pt0 f31 @|StepIcon #fIcon
Pt0 f32 785 273 30 30 0 15 #rect
Pt0 f32 @|EndSubIcon #fIcon
Pt0 f34 actionTable 'out=in;
' #txt
Pt0 f34 actionCode 'import ch.ivy.addon.portalkit.service.RegisteredApplicationService;

RegisteredApplicationService service = new RegisteredApplicationService();
out.apps = service.findActiveIvyAppsUserCanWorkOn(in.username);
' #txt
Pt0 f34 security system #txt
Pt0 f34 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Find applications can work on</name>
    </language>
</elementInfo>
' #txt
Pt0 f34 224 266 176 44 -81 -8 #rect
Pt0 f34 @|StepIcon #fIcon
Pt0 f35 processCall 'Functional Processes/ErrorHandler:handle(List<ch.ivy.addon.portalkit.ivydata.exception.PortalIvyDataException>)' #txt
Pt0 f35 requestActionDecl '<java.util.List<ch.ivy.addon.portalkit.ivydata.exception.PortalIvyDataException> exceptions> param;' #txt
Pt0 f35 requestMappingAction 'param.exceptions=in.errors;
' #txt
Pt0 f35 responseActionDecl 'ch.ivyteam.wf.processes.SecurityServiceData out;
' #txt
Pt0 f35 responseMappingAction 'out=in;
' #txt
Pt0 f35 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>ErrorHandler</name>
    </language>
</elementInfo>
' #txt
Pt0 f35 616 266 112 44 -35 -8 #rect
Pt0 f35 @|CallSubIcon #fIcon
Pt0 f36 processCall 'Functional Processes/ErrorHandler:handle(List<ch.ivy.addon.portalkit.ivydata.exception.PortalIvyDataException>)' #txt
Pt0 f36 requestActionDecl '<java.util.List<ch.ivy.addon.portalkit.ivydata.exception.PortalIvyDataException> exceptions> param;' #txt
Pt0 f36 requestMappingAction 'param.exceptions=in.errors;
' #txt
Pt0 f36 responseActionDecl 'ch.ivyteam.wf.processes.SecurityServiceData out;
' #txt
Pt0 f36 responseMappingAction 'out=in;
' #txt
Pt0 f36 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>ErrorHandler</name>
    </language>
</elementInfo>
' #txt
Pt0 f36 616 362 112 44 -35 -8 #rect
Pt0 f36 @|CallSubIcon #fIcon
Pt0 f37 actionTable 'out=in;
' #txt
Pt0 f37 actionCode 'import ch.ivy.addon.portalkit.ivydata.service.impl.SecurityService;
import ch.ivy.addon.portalkit.ivydata.dto.IvySecurityResultDTO;

IvySecurityResultDTO dto = SecurityService.newInstance().findRoles(in.application);
out.roles = dto.roles;
out.errors = dto.errors;
' #txt
Pt0 f37 security system #txt
Pt0 f37 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Find roles</name>
    </language>
</elementInfo>
' #txt
Pt0 f37 224 362 112 44 -27 -8 #rect
Pt0 f37 @|StepIcon #fIcon
Pt0 f38 785 369 30 30 0 15 #rect
Pt0 f38 @|EndSubIcon #fIcon
Pt0 f39 inParamDecl '<String username> param;' #txt
Pt0 f39 inParamTable 'out.username=param.username;
' #txt
Pt0 f39 outParamDecl '<java.util.List<ch.ivy.addon.portalkit.ivydata.exception.PortalIvyDataException> errors,java.util.List<ch.ivyteam.ivy.security.IRole> roles> result;' #txt
Pt0 f39 outParamTable 'result.errors=in.errors;
result.roles=in.roles;
' #txt
Pt0 f39 callSignature findRolesOverAllApplications(String) #txt
Pt0 f39 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>findRolesOverAllApplications(String)</name>
    </language>
</elementInfo>
' #txt
Pt0 f39 129 273 30 30 -87 22 #rect
Pt0 f39 @|StartSubIcon #fIcon
Pt0 f40 inParamDecl '<ch.ivyteam.ivy.application.IApplication application> param;' #txt
Pt0 f40 inParamTable 'out.application=param.application;
' #txt
Pt0 f40 outParamDecl '<java.util.List<ch.ivy.addon.portalkit.ivydata.exception.PortalIvyDataException> errors,java.util.List<ch.ivyteam.ivy.security.IRole> roles> result;' #txt
Pt0 f40 outParamTable 'result.errors=in.errors;
result.roles=in.roles;
' #txt
Pt0 f40 callSignature findRoles(ch.ivyteam.ivy.application.IApplication) #txt
Pt0 f40 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>findRoles(IApplication)</name>
    </language>
</elementInfo>
' #txt
Pt0 f40 129 369 30 30 -53 20 #rect
Pt0 f40 @|StartSubIcon #fIcon
Pt0 f41 expr out #txt
Pt0 f41 400 288 448 288 #arcP
Pt0 f42 expr out #txt
Pt0 f42 728 288 785 288 #arcP
Pt0 f43 expr out #txt
Pt0 f43 159 288 224 288 #arcP
Pt0 f44 expr out #txt
Pt0 f44 560 288 616 288 #arcP
Pt0 f45 expr out #txt
Pt0 f45 159 384 224 384 #arcP
Pt0 f46 expr out #txt
Pt0 f46 336 384 616 384 #arcP
Pt0 f47 expr out #txt
Pt0 f47 728 384 785 384 #arcP
Pt0 f48 actionTable 'out=in;
' #txt
Pt0 f48 actionCode 'import ch.ivy.addon.portalkit.ivydata.service.impl.SecurityService;
import ch.ivy.addon.portalkit.ivydata.dto.IvySecurityResultDTO;

IvySecurityResultDTO dto = SecurityService.newInstance().findRoleDTOs(in.application);
out.roleDTOs = dto.roleDTOs;
out.errors = dto.errors;
' #txt
Pt0 f48 security system #txt
Pt0 f48 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Find roles</name>
    </language>
</elementInfo>
' #txt
Pt0 f48 224 642 112 44 -27 -8 #rect
Pt0 f48 @|StepIcon #fIcon
Pt0 f49 processCall 'Functional Processes/ErrorHandler:handle(List<ch.ivy.addon.portalkit.ivydata.exception.PortalIvyDataException>)' #txt
Pt0 f49 requestActionDecl '<java.util.List<ch.ivy.addon.portalkit.ivydata.exception.PortalIvyDataException> exceptions> param;' #txt
Pt0 f49 requestMappingAction 'param.exceptions=in.errors;
' #txt
Pt0 f49 responseActionDecl 'ch.ivyteam.wf.processes.SecurityServiceData out;
' #txt
Pt0 f49 responseMappingAction 'out=in;
' #txt
Pt0 f49 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>ErrorHandler</name>
    </language>
</elementInfo>
' #txt
Pt0 f49 616 642 112 44 -35 -8 #rect
Pt0 f49 @|CallSubIcon #fIcon
Pt0 f50 785 649 30 30 0 15 #rect
Pt0 f50 @|EndSubIcon #fIcon
Pt0 f51 inParamDecl '<ch.ivyteam.ivy.application.IApplication application> param;' #txt
Pt0 f51 inParamTable 'out.application=param.application;
' #txt
Pt0 f51 outParamDecl '<java.util.List<ch.ivy.addon.portalkit.ivydata.exception.PortalIvyDataException> errors,java.util.List<ch.ivy.addon.portalkit.dto.RoleDTO> roles> result;' #txt
Pt0 f51 outParamTable 'result.errors=in.errors;
result.roles=in.roleDTOs;
' #txt
Pt0 f51 callSignature findRolesDTO(ch.ivyteam.ivy.application.IApplication) #txt
Pt0 f51 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>findRolesDTO(IApplication)</name>
    </language>
</elementInfo>
' #txt
Pt0 f51 129 649 30 30 -53 20 #rect
Pt0 f51 @|StartSubIcon #fIcon
Pt0 f52 expr out #txt
Pt0 f52 159 664 224 664 #arcP
Pt0 f53 expr out #txt
Pt0 f53 336 664 616 664 #arcP
Pt0 f54 expr out #txt
Pt0 f54 728 664 785 664 #arcP
Pt0 f56 785 753 30 30 0 15 #rect
Pt0 f56 @|EndSubIcon #fIcon
Pt0 f58 actionTable 'out=in;
' #txt
Pt0 f58 actionCode 'import ch.ivy.addon.portalkit.ivydata.service.impl.SecurityService;
import ch.ivy.addon.portalkit.ivydata.dto.IvySecurityResultDTO;

IvySecurityResultDTO dto = SecurityService.newInstance().findAllUsersOfRoles(in.application, in.startIndex, in.count, in.fromRoles, in.excludedUsernames);
out.users = dto.users;
out.errors = dto.errors;
' #txt
Pt0 f58 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Find users of given role</name>
    </language>
</elementInfo>
' #txt
Pt0 f58 208 746 144 44 -64 -8 #rect
Pt0 f58 @|StepIcon #fIcon
Pt0 f60 processCall 'Functional Processes/ErrorHandler:handle(List<ch.ivy.addon.portalkit.ivydata.exception.PortalIvyDataException>)' #txt
Pt0 f60 requestActionDecl '<java.util.List<ch.ivy.addon.portalkit.ivydata.exception.PortalIvyDataException> exceptions> param;' #txt
Pt0 f60 requestMappingAction 'param.exceptions=in.errors;
' #txt
Pt0 f60 responseActionDecl 'ch.ivyteam.wf.processes.SecurityServiceData out;
' #txt
Pt0 f60 responseMappingAction 'out=in;
' #txt
Pt0 f60 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>ErrorHandler</name>
    </language>
</elementInfo>
' #txt
Pt0 f60 616 746 112 44 -35 -8 #rect
Pt0 f60 @|CallSubIcon #fIcon
Pt0 f61 352 768 616 768 #arcP
Pt0 f57 728 768 785 768 #arcP
Pt0 f55 inParamDecl '<ch.ivyteam.ivy.application.IApplication application,Integer startIndex,Integer count,java.util.List<String> fromRoles,java.util.List<String> excludedUsernames> param;' #txt
Pt0 f55 inParamTable 'out.application=param.application;
out.count=param.count;
out.excludedUsernames=param.excludedUsernames;
out.fromRoles=param.fromRoles;
out.startIndex=param.startIndex;
' #txt
Pt0 f55 outParamDecl '<java.util.List<ch.ivy.addon.portalkit.ivydata.exception.PortalIvyDataException> errors,java.util.List<ch.ivy.addon.portalkit.dto.UserDTO> users> result;' #txt
Pt0 f55 outParamTable 'result.errors=in.errors;
result.users=in.users;
' #txt
Pt0 f55 callSignature findAllUsersOfRoles(ch.ivyteam.ivy.application.IApplication,Integer,Integer,java.util.List<String>,java.util.List<String>) #txt
Pt0 f55 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>findAllUsersOfRoles(IApplication,Integer,Integer,List&lt;String&gt;,List&lt;String&gt;)</name>
    </language>
</elementInfo>
' #txt
Pt0 f55 129 753 30 30 -67 22 #rect
Pt0 f55 @|StartSubIcon #fIcon
Pt0 f59 159 768 208 768 #arcP
Pt0 f63 785 881 30 30 0 15 #rect
Pt0 f63 @|EndSubIcon #fIcon
Pt0 f64 actionTable 'out=in;
' #txt
Pt0 f64 actionCode 'import ch.ivy.addon.portalkit.ivydata.service.impl.SecurityService;
import ch.ivy.addon.portalkit.ivydata.dto.IvySecurityResultDTO;

IvySecurityResultDTO dto = SecurityService.newInstance().findRoles(in.apps, in.username);
out.roles = dto.roles;
out.errors = dto.errors;
' #txt
Pt0 f64 security system #txt
Pt0 f64 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Find roles</name>
    </language>
</elementInfo>
' #txt
Pt0 f64 448 874 112 44 -27 -8 #rect
Pt0 f64 @|StepIcon #fIcon
Pt0 f65 processCall 'Functional Processes/ErrorHandler:handle(List<ch.ivy.addon.portalkit.ivydata.exception.PortalIvyDataException>)' #txt
Pt0 f65 requestActionDecl '<java.util.List<ch.ivy.addon.portalkit.ivydata.exception.PortalIvyDataException> exceptions> param;' #txt
Pt0 f65 requestMappingAction 'param.exceptions=in.errors;
' #txt
Pt0 f65 responseActionDecl 'ch.ivyteam.wf.processes.SecurityServiceData out;
' #txt
Pt0 f65 responseMappingAction 'out=in;
' #txt
Pt0 f65 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>ErrorHandler</name>
    </language>
</elementInfo>
' #txt
Pt0 f65 616 874 112 44 -35 -8 #rect
Pt0 f65 @|CallSubIcon #fIcon
Pt0 f68 actionTable 'out=in;
' #txt
Pt0 f68 actionCode 'import ch.ivy.addon.portalkit.service.RegisteredApplicationService;

RegisteredApplicationService service = new RegisteredApplicationService();
out.apps = service.findActiveIvyAppsUserCanWorkOn(in.username);
' #txt
Pt0 f68 security system #txt
Pt0 f68 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Find applications can work on</name>
    </language>
</elementInfo>
' #txt
Pt0 f68 224 874 176 44 -81 -8 #rect
Pt0 f68 @|StepIcon #fIcon
Pt0 f70 inParamDecl '<String username> param;' #txt
Pt0 f70 inParamTable 'out.username=param.username;
' #txt
Pt0 f70 outParamDecl '<java.util.List<ch.ivy.addon.portalkit.ivydata.exception.PortalIvyDataException> errors,java.util.List<ch.ivyteam.ivy.security.IRole> roles> result;' #txt
Pt0 f70 outParamTable 'result.errors=in.errors;
result.roles=in.roles;
' #txt
Pt0 f70 callSignature findRolesOfUserOverAllApplications(String) #txt
Pt0 f70 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>findRolesOfUserOverAllApplications(String)</name>
    </language>
</elementInfo>
' #txt
Pt0 f70 129 881 30 30 -87 22 #rect
Pt0 f70 @|StartSubIcon #fIcon
Pt0 f71 expr out #txt
Pt0 f71 400 896 448 896 #arcP
Pt0 f74 expr out #txt
Pt0 f74 159 896 224 896 #arcP
Pt0 f76 expr out #txt
Pt0 f76 728 896 785 896 #arcP
Pt0 f77 expr out #txt
Pt0 f77 560 896 616 896 #arcP
>Proto Pt0 .type ch.ivyteam.wf.processes.SecurityServiceData #txt
>Proto Pt0 .processKind CALLABLE_SUB #txt
>Proto Pt0 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <swimlaneLabel>Find all roles</swimlaneLabel>
        <swimlaneLabel>Find all users</swimlaneLabel>
        <swimlaneLabel>Find security members to delegate</swimlaneLabel>
        <swimlaneLabel></swimlaneLabel>
    </language>
    <swimlaneSize>352</swimlaneSize>
    <swimlaneSize>312</swimlaneSize>
    <swimlaneSize>264</swimlaneSize>
    <swimlaneColor gradient="true">-1</swimlaneColor>
    <swimlaneColor gradient="true">-10027162</swimlaneColor>
    <swimlaneColor gradient="true">-1</swimlaneColor>
    <swimlaneType>LANE</swimlaneType>
    <swimlaneType>LANE</swimlaneType>
    <swimlaneType>LANE</swimlaneType>
    <swimlaneSpaceBefore>0</swimlaneSpaceBefore>
    <swimlaneSpaceBefore>0</swimlaneSpaceBefore>
    <swimlaneSpaceBefore>0</swimlaneSpaceBefore>
</elementInfo>
' #txt
>Proto Pt0 0 0 32 24 18 0 #rect
>Proto Pt0 @|BIcon #fIcon
Pt0 f10 mainOut f26 tail #connect
Pt0 f26 head f20 mainIn #connect
Pt0 f20 mainOut f27 tail #connect
Pt0 f27 head f24 mainIn #connect
Pt0 f0 mainOut f2 tail #connect
Pt0 f2 head f10 mainIn #connect
Pt0 f24 mainOut f3 tail #connect
Pt0 f3 head f1 mainIn #connect
Pt0 f4 mainOut f6 tail #connect
Pt0 f6 head f5 mainIn #connect
Pt0 f8 mainOut f9 tail #connect
Pt0 f9 head f7 mainIn #connect
Pt0 f5 mainOut f11 tail #connect
Pt0 f11 head f8 mainIn #connect
Pt0 f14 mainOut f21 tail #connect
Pt0 f21 head f13 mainIn #connect
Pt0 f13 mainOut f22 tail #connect
Pt0 f22 head f17 mainIn #connect
Pt0 f17 mainOut f18 tail #connect
Pt0 f18 head f16 mainIn #connect
Pt0 f12 mainOut f15 tail #connect
Pt0 f15 head f14 mainIn #connect
Pt0 f25 mainOut f33 tail #connect
Pt0 f33 head f28 mainIn #connect
Pt0 f28 mainOut f30 tail #connect
Pt0 f30 head f29 mainIn #connect
Pt0 f19 mainOut f23 tail #connect
Pt0 f23 head f25 mainIn #connect
Pt0 f34 mainOut f41 tail #connect
Pt0 f41 head f31 mainIn #connect
Pt0 f31 mainOut f44 tail #connect
Pt0 f44 head f35 mainIn #connect
Pt0 f39 mainOut f43 tail #connect
Pt0 f43 head f34 mainIn #connect
Pt0 f35 mainOut f42 tail #connect
Pt0 f42 head f32 mainIn #connect
Pt0 f37 mainOut f46 tail #connect
Pt0 f46 head f36 mainIn #connect
Pt0 f36 mainOut f47 tail #connect
Pt0 f47 head f38 mainIn #connect
Pt0 f40 mainOut f45 tail #connect
Pt0 f45 head f37 mainIn #connect
Pt0 f48 mainOut f53 tail #connect
Pt0 f53 head f49 mainIn #connect
Pt0 f49 mainOut f54 tail #connect
Pt0 f54 head f50 mainIn #connect
Pt0 f51 mainOut f52 tail #connect
Pt0 f52 head f48 mainIn #connect
Pt0 f58 mainOut f61 tail #connect
Pt0 f61 head f60 mainIn #connect
Pt0 f60 mainOut f57 tail #connect
Pt0 f57 head f56 mainIn #connect
Pt0 f55 mainOut f59 tail #connect
Pt0 f59 head f58 mainIn #connect
Pt0 f68 mainOut f71 tail #connect
Pt0 f71 head f64 mainIn #connect
Pt0 f64 mainOut f77 tail #connect
Pt0 f77 head f65 mainIn #connect
Pt0 f70 mainOut f74 tail #connect
Pt0 f74 head f68 mainIn #connect
Pt0 f65 mainOut f76 tail #connect
Pt0 f76 head f63 mainIn #connect
