[Ivy]
18098B8A2F5FCF38 7.5.0 #module
>Proto >Proto Collection #zClass
Pt0 PortalComponentSecurityService Big #zClass
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
Pt0 @StartSub f19 '' #zField
Pt0 @GridStep f25 '' #zField
Pt0 @CallSub f28 '' #zField
Pt0 @EndSub f29 '' #zField
Pt0 @PushWFArc f30 '' #zField
Pt0 @PushWFArc f33 '' #zField
Pt0 @PushWFArc f23 '' #zField
>Proto Pt0 Pt0 PortalComponentSecurityService #zField
Pt0 f0 inParamDecl '<String username,String query,Integer startIndex,Integer count,java.util.List<String> fromRoles,java.util.List<String> excludedUsernames> param;' #txt
Pt0 f0 inParamTable 'out.count=param.count;
out.excludedUsernames=param.excludedUsernames;
out.fromRoles=param.fromRoles;
out.query=param.query;
out.startIndex=param.startIndex;
out.username=param.username;
' #txt
Pt0 f0 outParamDecl '<java.util.List<com.axonivy.portal.component.ivydata.exception.PortalIvyDataException> errors,java.util.List<com.axonivy.portal.component.dto.UserDTO> users> result;' #txt
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
Pt0 f10 actionCode 'import com.axonivy.portal.component.service.RegisteredApplicationService;

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
Pt0 f24 processCall 'Functional Processes/PortalComponentErrorHandler:handle(java.util.List<com.axonivy.portal.component.ivydata.exception.PortalIvyDataException>)' #txt
Pt0 f24 requestActionDecl '<java.util.List<com.axonivy.portal.component.ivydata.exception.PortalIvyDataException> exceptions> param;' #txt
Pt0 f24 requestMappingAction 'param.exceptions=in.errors;
' #txt
Pt0 f24 responseActionDecl 'com.axonivy.portal.component.service.PortalComponentSecurityServiceData out;
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
Pt0 f20 actionCode 'import com.axonivy.portal.component.ivydata.service.impl.SecurityService;
import com.axonivy.portal.component.ivydata.dto.IvySecurityResultDTO;

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
Pt0 f19 inParamDecl '<ch.ivyteam.ivy.application.IApplication application,String query,Integer startIndex,Integer count,java.util.List<String> fromRoles,java.util.List<String> excludedUsernames> param;' #txt
Pt0 f19 inParamTable 'out.application=param.application;
out.count=param.count;
out.excludedUsernames=param.excludedUsernames;
out.fromRoles=param.fromRoles;
out.query=param.query;
out.startIndex=param.startIndex;
' #txt
Pt0 f19 outParamDecl '<java.util.List<com.axonivy.portal.component.ivydata.exception.PortalIvyDataException> errors,java.util.List<com.axonivy.portal.component.dto.UserDTO> users> result;' #txt
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
Pt0 f25 actionCode 'import com.axonivy.portal.component.ivydata.service.impl.SecurityService;
import com.axonivy.portal.component.ivydata.dto.IvySecurityResultDTO;

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
Pt0 f28 processCall 'Functional Processes/PortalComponentErrorHandler:handle(java.util.List<com.axonivy.portal.component.ivydata.exception.PortalIvyDataException>)' #txt
Pt0 f28 requestActionDecl '<java.util.List<com.axonivy.portal.component.ivydata.exception.PortalIvyDataException> exceptions> param;' #txt
Pt0 f28 requestMappingAction 'param.exceptions=in.errors;
' #txt
Pt0 f28 responseActionDecl 'com.axonivy.portal.component.service.PortalComponentSecurityServiceData out;
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
>Proto Pt0 .type com.axonivy.portal.component.service.PortalComponentSecurityServiceData #txt
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
Pt0 f25 mainOut f33 tail #connect
Pt0 f33 head f28 mainIn #connect
Pt0 f28 mainOut f30 tail #connect
Pt0 f30 head f29 mainIn #connect
Pt0 f19 mainOut f23 tail #connect
Pt0 f23 head f25 mainIn #connect
