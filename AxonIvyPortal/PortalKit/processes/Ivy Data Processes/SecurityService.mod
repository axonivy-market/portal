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
>Proto Pt0 Pt0 SecurityService #zField
Pt0 f0 inParamDecl '<String username> param;' #txt
Pt0 f0 inParamTable 'out.username=param.username;
' #txt
Pt0 f0 outParamDecl '<java.util.List<ch.ivy.addon.portalkit.ivydata.exception.PortalIvyDataException> errors,java.util.Map usersByApp> result;' #txt
Pt0 f0 outParamTable 'result.errors=in.errors;
result.usersByApp=in.usersByApp;
' #txt
Pt0 f0 callSignature findUsersOverAllApplications(String) #txt
Pt0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>findUsersOverAllApplications(String)</name>
    </language>
</elementInfo>
' #txt
Pt0 f0 81 81 30 30 -87 22 #rect
Pt0 f0 @|StartSubIcon #fIcon
Pt0 f1 721 81 30 30 0 15 #rect
Pt0 f1 @|EndSubIcon #fIcon
Pt0 f10 actionTable 'out=in;
' #txt
Pt0 f10 actionCode 'import ch.ivy.addon.portalkit.service.RegisteredApplicationService;

RegisteredApplicationService service = new RegisteredApplicationService();
out.apps = service.findActiveIvyAppsUserCanWorkOn(in.username);
' #txt
Pt0 f10 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Find applications can work on</name>
    </language>
</elementInfo>
' #txt
Pt0 f10 160 74 176 44 -81 -8 #rect
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
Pt0 f24 544 74 112 44 -35 -8 #rect
Pt0 f24 @|CallSubIcon #fIcon
Pt0 f20 actionTable 'out=in;
' #txt
Pt0 f20 actionCode 'import ch.ivy.addon.portalkit.ivydata.service.impl.SecurityService;
import ch.ivy.addon.portalkit.ivydata.dto.IvySecurityResultDTO;

IvySecurityResultDTO dto = SecurityService.newInstance().findUsers(in.apps);
out.usersByApp = dto.usersByApp;
out.errors = dto.errors;
' #txt
Pt0 f20 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Find users</name>
    </language>
</elementInfo>
' #txt
Pt0 f20 384 74 112 44 -29 -8 #rect
Pt0 f20 @|StepIcon #fIcon
Pt0 f26 expr out #txt
Pt0 f26 336 96 384 96 #arcP
Pt0 f27 expr out #txt
Pt0 f27 496 96 544 96 #arcP
Pt0 f2 expr out #txt
Pt0 f2 111 96 160 96 #arcP
Pt0 f3 expr out #txt
Pt0 f3 656 96 721 96 #arcP
Pt0 f4 inParamDecl '<ch.ivyteam.ivy.application.IApplication application> param;' #txt
Pt0 f4 inParamTable 'out.application=param.application;
' #txt
Pt0 f4 outParamDecl '<java.util.List<ch.ivy.addon.portalkit.dto.SecurityMemberDTO> members,java.util.List<ch.ivy.addon.portalkit.ivydata.exception.PortalIvyDataException> errors> result;' #txt
Pt0 f4 outParamTable 'result.members=in.securityMembers;
result.errors=in.errors;
' #txt
Pt0 f4 callSignature findSecurityMembers(ch.ivyteam.ivy.application.IApplication) #txt
Pt0 f4 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>findSecurityMembers(IApplication)</name>
    </language>
</elementInfo>
' #txt
Pt0 f4 81 561 30 30 -90 19 #rect
Pt0 f4 @|StartSubIcon #fIcon
Pt0 f5 actionTable 'out=in;
' #txt
Pt0 f5 actionCode 'import ch.ivy.addon.portalkit.ivydata.service.impl.SecurityService;
import ch.ivy.addon.portalkit.ivydata.dto.IvySecurityResultDTO;

IvySecurityResultDTO dto = SecurityService.newInstance().findSecurityMembers(in.application);
out.securityMembers = dto.securityMembers;

out.errors = dto.errors;
' #txt
Pt0 f5 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Find security members</name>
    </language>
</elementInfo>
' #txt
Pt0 f5 248 554 144 44 -63 -8 #rect
Pt0 f5 @|StepIcon #fIcon
Pt0 f6 expr out #txt
Pt0 f6 111 576 248 576 #arcP
Pt0 f7 593 561 30 30 0 15 #rect
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
Pt0 f8 432 554 112 44 -35 -8 #rect
Pt0 f8 @|CallSubIcon #fIcon
Pt0 f9 expr out #txt
Pt0 f9 544 576 593 576 #arcP
Pt0 f11 expr out #txt
Pt0 f11 392 576 432 576 #arcP
Pt0 f12 inParamDecl '<String username> param;' #txt
Pt0 f12 inParamTable 'out.username=param.username;
' #txt
Pt0 f12 outParamDecl '<java.util.List<ch.ivy.addon.portalkit.dto.SecurityMemberDTO> members,java.util.List<ch.ivy.addon.portalkit.ivydata.exception.PortalIvyDataException> errors> result;' #txt
Pt0 f12 outParamTable 'result.members=in.securityMembers;
result.errors=in.errors;
' #txt
Pt0 f12 callSignature findSecurityMembersOverAllApplications(String) #txt
Pt0 f12 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>findSecurityMembersOverAllApplications(String)</name>
    </language>
</elementInfo>
' #txt
Pt0 f12 81 465 30 30 -90 19 #rect
Pt0 f12 @|StartSubIcon #fIcon
Pt0 f13 actionTable 'out=in;
' #txt
Pt0 f13 actionCode 'import ch.ivy.addon.portalkit.ivydata.service.impl.SecurityService;
import ch.ivy.addon.portalkit.ivydata.dto.IvySecurityResultDTO;

IvySecurityResultDTO dto = SecurityService.newInstance().findSecurityMembers(in.apps);
out.securityMembers = dto.securityMembers;

out.errors = dto.errors;
' #txt
Pt0 f13 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Find security members</name>
    </language>
</elementInfo>
' #txt
Pt0 f13 376 458 144 44 -63 -8 #rect
Pt0 f13 @|StepIcon #fIcon
Pt0 f14 actionTable 'out=in;
' #txt
Pt0 f14 actionCode 'import ch.ivy.addon.portalkit.service.RegisteredApplicationService;

RegisteredApplicationService service = new RegisteredApplicationService();
out.apps = service.findActiveIvyAppsUserCanWorkOn(in.username);
' #txt
Pt0 f14 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Find applications can work on</name>
    </language>
</elementInfo>
' #txt
Pt0 f14 168 458 176 44 -81 -8 #rect
Pt0 f14 @|StepIcon #fIcon
Pt0 f16 729 465 30 30 0 15 #rect
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
Pt0 f17 552 458 112 44 -35 -8 #rect
Pt0 f17 @|CallSubIcon #fIcon
Pt0 f18 expr out #txt
Pt0 f18 664 480 729 480 #arcP
Pt0 f21 expr out #txt
Pt0 f21 344 480 376 480 #arcP
Pt0 f22 expr out #txt
Pt0 f22 520 480 552 480 #arcP
Pt0 f15 expr out #txt
Pt0 f15 111 480 168 480 #arcP
Pt0 f19 inParamDecl '<ch.ivyteam.ivy.application.IApplication application> param;' #txt
Pt0 f19 inParamTable 'out.application=param.application;
' #txt
Pt0 f19 outParamDecl '<java.util.List<ch.ivy.addon.portalkit.ivydata.exception.PortalIvyDataException> errors,java.util.List<ch.ivy.addon.portalkit.dto.UserDTO> users> result;' #txt
Pt0 f19 outParamTable 'result.errors=in.errors;
result.users=in.users;
' #txt
Pt0 f19 callSignature findUsers(ch.ivyteam.ivy.application.IApplication) #txt
Pt0 f19 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>findUsers(IApplication)</name>
    </language>
</elementInfo>
' #txt
Pt0 f19 81 177 30 30 -53 20 #rect
Pt0 f19 @|StartSubIcon #fIcon
Pt0 f25 actionTable 'out=in;
' #txt
Pt0 f25 actionCode 'import ch.ivy.addon.portalkit.ivydata.service.impl.SecurityService;
import ch.ivy.addon.portalkit.ivydata.dto.IvySecurityResultDTO;

IvySecurityResultDTO dto = SecurityService.newInstance().findUsers(in.application);
out.users = dto.users;
out.errors = dto.errors;
' #txt
Pt0 f25 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Find users</name>
    </language>
</elementInfo>
' #txt
Pt0 f25 248 170 112 44 -29 -8 #rect
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
Pt0 f28 408 170 112 44 -35 -8 #rect
Pt0 f28 @|CallSubIcon #fIcon
Pt0 f29 585 177 30 30 0 15 #rect
Pt0 f29 @|EndSubIcon #fIcon
Pt0 f30 expr out #txt
Pt0 f30 520 192 585 192 #arcP
Pt0 f33 expr out #txt
Pt0 f33 360 192 408 192 #arcP
Pt0 f23 expr out #txt
Pt0 f23 111 192 248 192 #arcP
Pt0 f31 actionTable 'out=in;
' #txt
Pt0 f31 actionCode 'import ch.ivy.addon.portalkit.ivydata.service.impl.SecurityService;
import ch.ivy.addon.portalkit.ivydata.dto.IvySecurityResultDTO;

IvySecurityResultDTO dto = SecurityService.newInstance().findRoles(in.apps);
out.rolesByApp = dto.rolesByApp;
out.errors = dto.errors;
' #txt
Pt0 f31 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Find roles</name>
    </language>
</elementInfo>
' #txt
Pt0 f31 384 266 112 44 -27 -8 #rect
Pt0 f31 @|StepIcon #fIcon
Pt0 f32 721 273 30 30 0 15 #rect
Pt0 f32 @|EndSubIcon #fIcon
Pt0 f34 actionTable 'out=in;
' #txt
Pt0 f34 actionCode 'import ch.ivy.addon.portalkit.service.RegisteredApplicationService;

RegisteredApplicationService service = new RegisteredApplicationService();
out.apps = service.findActiveIvyAppsUserCanWorkOn(in.username);
' #txt
Pt0 f34 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Find applications can work on</name>
    </language>
</elementInfo>
' #txt
Pt0 f34 160 266 176 44 -81 -8 #rect
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
Pt0 f35 544 266 112 44 -35 -8 #rect
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
Pt0 f36 408 362 112 44 -35 -8 #rect
Pt0 f36 @|CallSubIcon #fIcon
Pt0 f37 actionTable 'out=in;
' #txt
Pt0 f37 actionCode 'import ch.ivy.addon.portalkit.ivydata.service.impl.SecurityService;
import ch.ivy.addon.portalkit.ivydata.dto.IvySecurityResultDTO;

IvySecurityResultDTO dto = SecurityService.newInstance().findRoles(in.application);
out.roles = dto.roles;
out.errors = dto.errors;
' #txt
Pt0 f37 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Find roles</name>
    </language>
</elementInfo>
' #txt
Pt0 f37 248 362 112 44 -27 -8 #rect
Pt0 f37 @|StepIcon #fIcon
Pt0 f38 585 369 30 30 0 15 #rect
Pt0 f38 @|EndSubIcon #fIcon
Pt0 f39 inParamDecl '<String username> param;' #txt
Pt0 f39 inParamTable 'out.username=param.username;
' #txt
Pt0 f39 outParamDecl '<java.util.List<ch.ivy.addon.portalkit.ivydata.exception.PortalIvyDataException> errors,java.util.Map rolesByApp> result;' #txt
Pt0 f39 outParamTable 'result.errors=in.errors;
result.rolesByApp=in.rolesByApp;
' #txt
Pt0 f39 callSignature findRolesOverAllApplications(String) #txt
Pt0 f39 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>findRolesOverAllApplications(String)</name>
    </language>
</elementInfo>
' #txt
Pt0 f39 81 273 30 30 -87 22 #rect
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
Pt0 f40 81 369 30 30 -53 20 #rect
Pt0 f40 @|StartSubIcon #fIcon
Pt0 f41 expr out #txt
Pt0 f41 336 288 384 288 #arcP
Pt0 f42 expr out #txt
Pt0 f42 656 288 721 288 #arcP
Pt0 f43 expr out #txt
Pt0 f43 111 288 160 288 #arcP
Pt0 f44 expr out #txt
Pt0 f44 496 288 544 288 #arcP
Pt0 f45 expr out #txt
Pt0 f45 111 384 248 384 #arcP
Pt0 f46 expr out #txt
Pt0 f46 360 384 408 384 #arcP
Pt0 f47 expr out #txt
Pt0 f47 520 384 585 384 #arcP
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
Pt0 f48 240 642 112 44 -27 -8 #rect
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
Pt0 f49 400 642 112 44 -35 -8 #rect
Pt0 f49 @|CallSubIcon #fIcon
Pt0 f50 577 649 30 30 0 15 #rect
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
Pt0 f51 73 649 30 30 -53 20 #rect
Pt0 f51 @|StartSubIcon #fIcon
Pt0 f52 expr out #txt
Pt0 f52 103 664 240 664 #arcP
Pt0 f53 expr out #txt
Pt0 f53 352 664 400 664 #arcP
Pt0 f54 expr out #txt
Pt0 f54 512 664 577 664 #arcP
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
    <swimlaneSize>304</swimlaneSize>
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
