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
Pt0 @StartSub f4 '' #zField
Pt0 @GridStep f5 '' #zField
Pt0 @PushWFArc f6 '' #zField
Pt0 @EndSub f7 '' #zField
Pt0 @StartSub f19 '' #zField
Pt0 @GridStep f25 '' #zField
Pt0 @EndSub f29 '' #zField
Pt0 @PushWFArc f23 '' #zField
Pt0 @GridStep f37 '' #zField
Pt0 @EndSub f38 '' #zField
Pt0 @StartSub f40 '' #zField
Pt0 @PushWFArc f45 '' #zField
Pt0 @GridStep f48 '' #zField
Pt0 @EndSub f50 '' #zField
Pt0 @StartSub f51 '' #zField
Pt0 @PushWFArc f52 '' #zField
Pt0 @PushWFArc f8 '' #zField
Pt0 @PushWFArc f9 '' #zField
Pt0 @PushWFArc f11 '' #zField
Pt0 @PushWFArc f18 '' #zField
>Proto Pt0 Pt0 SecurityService #zField
Pt0 f4 inParamDecl '<ch.ivyteam.ivy.application.IApplication application,String query,Integer startIndex,Integer count> param;' #txt
Pt0 f4 inParamTable 'out.application=param.application;
out.count=param.count;
out.query=param.query;
out.startIndex=param.startIndex;
' #txt
Pt0 f4 outParamDecl '<java.util.List<ch.ivy.addon.portalkit.dto.SecurityMemberDTO> members,java.util.List<ch.ivy.addon.portalkit.ivydata.exception.PortalIvyDataException> errors> result;' #txt
Pt0 f4 outParamTable 'result.members=in.securityMembers;
' #txt
Pt0 f4 callSignature findSecurityMembers(ch.ivyteam.ivy.application.IApplication,String,Integer,Integer) #txt
Pt0 f4 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>findSecurityMembers(IApplication,String,Integer,Integer)</name>
    </language>
</elementInfo>
' #txt
Pt0 f4 129 241 30 30 -90 19 #rect
Pt0 f4 @|StartSubIcon #fIcon
Pt0 f5 actionTable 'out=in;
' #txt
Pt0 f5 actionCode 'import ch.ivy.addon.portalkit.ivydata.service.impl.SecurityService;
import ch.ivy.addon.portalkit.ivydata.dto.IvySecurityResultDTO;

IvySecurityResultDTO dto = SecurityService.newInstance().findSecurityMembers(in.query, in.application, in.startIndex, in.count);
out.securityMembers = dto.securityMembers;


' #txt
Pt0 f5 security system #txt
Pt0 f5 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Find security members</name>
    </language>
</elementInfo>
' #txt
Pt0 f5 392 234 144 44 -63 -8 #rect
Pt0 f5 @|StepIcon #fIcon
Pt0 f6 expr out #txt
Pt0 f6 159 256 392 256 #arcP
Pt0 f7 641 241 30 30 0 15 #rect
Pt0 f7 @|EndSubIcon #fIcon
Pt0 f19 inParamDecl '<String query,Integer startIndex,Integer count,java.util.List<String> fromRoles,java.util.List<String> excludedUsernames> param;' #txt
Pt0 f19 inParamTable 'out.count=param.count;
out.excludedUsernames=param.excludedUsernames;
out.fromRoles=param.fromRoles;
out.query=param.query;
out.startIndex=param.startIndex;
' #txt
Pt0 f19 outParamDecl '<java.util.List<ch.ivy.addon.portalkit.ivydata.exception.PortalIvyDataException> errors,java.util.List<ch.ivy.addon.portalkit.dto.UserDTO> users> result;' #txt
Pt0 f19 outParamTable 'result.users=in.users;
' #txt
Pt0 f19 callSignature findUsers(String,Integer,Integer,java.util.List<String>,java.util.List<String>) #txt
Pt0 f19 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>findUsers(String,Integer,Integer,List&lt;String&gt;,List&lt;String&gt;)</name>
    </language>
</elementInfo>
' #txt
Pt0 f19 129 49 30 30 -53 20 #rect
Pt0 f19 @|StartSubIcon #fIcon
Pt0 f25 actionTable 'out=in;
' #txt
Pt0 f25 actionCode 'import ch.ivy.addon.portalkit.ivydata.service.impl.SecurityService;
import ch.ivy.addon.portalkit.ivydata.dto.IvySecurityResultDTO;

IvySecurityResultDTO dto = SecurityService.newInstance().findUsers(in.query, in.startIndex, in.count, in.fromRoles, in.excludedUsernames);
out.users = dto.users;

' #txt
Pt0 f25 security system #txt
Pt0 f25 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Find users</name>
    </language>
</elementInfo>
' #txt
Pt0 f25 408 42 112 44 -29 -8 #rect
Pt0 f25 @|StepIcon #fIcon
Pt0 f29 641 49 30 30 0 15 #rect
Pt0 f29 @|EndSubIcon #fIcon
Pt0 f23 expr out #txt
Pt0 f23 159 64 408 64 #arcP
Pt0 f37 actionTable 'out=in;
' #txt
Pt0 f37 actionCode 'import ch.ivy.addon.portalkit.ivydata.service.impl.SecurityService;
import ch.ivy.addon.portalkit.ivydata.dto.IvySecurityResultDTO;

IvySecurityResultDTO dto = SecurityService.newInstance().findRoles();
out.roles = dto.roles;

' #txt
Pt0 f37 security system #txt
Pt0 f37 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Find roles</name>
    </language>
</elementInfo>
' #txt
Pt0 f37 408 138 112 44 -27 -8 #rect
Pt0 f37 @|StepIcon #fIcon
Pt0 f38 641 145 30 30 0 15 #rect
Pt0 f38 @|EndSubIcon #fIcon
Pt0 f40 inParamDecl '<> param;' #txt
Pt0 f40 outParamDecl '<java.util.List<ch.ivy.addon.portalkit.ivydata.exception.PortalIvyDataException> errors,java.util.List<ch.ivyteam.ivy.security.IRole> roles> result;' #txt
Pt0 f40 outParamTable 'result.roles=in.roles;
' #txt
Pt0 f40 callSignature findRoles() #txt
Pt0 f40 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>findRoles()</name>
    </language>
</elementInfo>
' #txt
Pt0 f40 129 145 30 30 -53 20 #rect
Pt0 f40 @|StartSubIcon #fIcon
Pt0 f45 expr out #txt
Pt0 f45 159 160 408 160 #arcP
Pt0 f48 actionTable 'out=in;
' #txt
Pt0 f48 actionCode 'import ch.ivy.addon.portalkit.ivydata.service.impl.SecurityService;
import ch.ivy.addon.portalkit.ivydata.dto.IvySecurityResultDTO;

IvySecurityResultDTO dto = SecurityService.newInstance().findRoleDTOs();
out.roleDTOs = dto.roleDTOs;
' #txt
Pt0 f48 security system #txt
Pt0 f48 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Find roles</name>
    </language>
</elementInfo>
' #txt
Pt0 f48 408 330 112 44 -27 -8 #rect
Pt0 f48 @|StepIcon #fIcon
Pt0 f50 641 337 30 30 0 15 #rect
Pt0 f50 @|EndSubIcon #fIcon
Pt0 f51 inParamDecl '<> param;' #txt
Pt0 f51 outParamDecl '<java.util.List<ch.ivy.addon.portalkit.ivydata.exception.PortalIvyDataException> errors,java.util.List<ch.ivy.addon.portalkit.dto.RoleDTO> roles> result;' #txt
Pt0 f51 outParamTable 'result.roles=in.roleDTOs;
' #txt
Pt0 f51 callSignature findRolesDTO() #txt
Pt0 f51 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>findRolesDTO()</name>
    </language>
</elementInfo>
' #txt
Pt0 f51 129 337 30 30 -53 20 #rect
Pt0 f51 @|StartSubIcon #fIcon
Pt0 f52 expr out #txt
Pt0 f52 159 352 408 352 #arcP
Pt0 f8 536 256 641 256 #arcP
Pt0 f9 520 352 641 352 #arcP
Pt0 f11 520 160 641 160 #arcP
Pt0 f18 520 64 641 64 #arcP
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
Pt0 f4 mainOut f6 tail #connect
Pt0 f6 head f5 mainIn #connect
Pt0 f19 mainOut f23 tail #connect
Pt0 f23 head f25 mainIn #connect
Pt0 f40 mainOut f45 tail #connect
Pt0 f45 head f37 mainIn #connect
Pt0 f51 mainOut f52 tail #connect
Pt0 f52 head f48 mainIn #connect
Pt0 f5 mainOut f8 tail #connect
Pt0 f8 head f7 mainIn #connect
Pt0 f48 mainOut f9 tail #connect
Pt0 f9 head f50 mainIn #connect
Pt0 f37 mainOut f11 tail #connect
Pt0 f11 head f38 mainIn #connect
Pt0 f25 mainOut f18 tail #connect
Pt0 f18 head f29 mainIn #connect
