[Ivy]
1771F48F5DB4A59C 7.5.0 #module
>Proto >Proto Collection #zClass
Ts0 SecurityMemberDisplayNameProcess Big #zClass
Ts0 RD #cInfo
Ts0 #process
Ts0 @TextInP .type .type #zField
Ts0 @TextInP .processKind .processKind #zField
Ts0 @AnnotationInP-0n ai ai #zField
Ts0 @MessageFlowInP-0n messageIn messageIn #zField
Ts0 @MessageFlowOutP-0n messageOut messageOut #zField
Ts0 @TextInP .xml .xml #zField
Ts0 @TextInP .responsibility .responsibility #zField
Ts0 @UdInit f0 '' #zField
Ts0 @UdProcessEnd f1 '' #zField
Ts0 @UdMethod f3 '' #zField
Ts0 @UdProcessEnd f4 '' #zField
Ts0 @CallSub f8 '' #zField
Ts0 @PushWFArc f6 '' #zField
Ts0 @UdMethod f7 '' #zField
Ts0 @UdProcessEnd f9 '' #zField
Ts0 @CallSub f13 '' #zField
Ts0 @PushWFArc f10 '' #zField
Ts0 @PushWFArc f11 '' #zField
Ts0 @GridStep f12 '' #zField
Ts0 @PushWFArc f14 '' #zField
Ts0 @PushWFArc f2 '' #zField
Ts0 @GridStep f15 '' #zField
Ts0 @PushWFArc f16 '' #zField
Ts0 @PushWFArc f5 '' #zField
Ts0 @UdMethod f17 '' #zField
Ts0 @UdProcessEnd f18 '' #zField
Ts0 @UdProcessEnd f20 '' #zField
Ts0 @Alternative f21 '' #zField
Ts0 @PushWFArc f22 '' #zField
Ts0 @PushWFArc f23 '' #zField
Ts0 @GridStep f24 '' #zField
Ts0 @PushWFArc f25 '' #zField
Ts0 @PushWFArc f19 '' #zField
>Proto Ts0 Ts0 SecurityMemberDisplayNameProcess #zField
Ts0 f0 guid 15493AB38F1A359C #txt
Ts0 f0 method start() #txt
Ts0 f0 inParameterDecl '<> param;' #txt
Ts0 f0 outParameterDecl '<> result;' #txt
Ts0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start()</name>
    </language>
</elementInfo>
' #txt
Ts0 f0 85 85 22 22 -9 9 #rect
Ts0 f0 @|UdInitIcon #fIcon
Ts0 f1 365 85 22 22 14 0 #rect
Ts0 f1 @|UdProcessEndIcon #fIcon
Ts0 f3 guid 1771DE16B5973CC3 #txt
Ts0 f3 method loadUsersOfRole(String) #txt
Ts0 f3 inParameterDecl '<String roleName> param;' #txt
Ts0 f3 inParameterMapAction 'out.roleName=param.roleName;
' #txt
Ts0 f3 inActionCode 'out.memberNames.clear();
out.keyword = "";' #txt
Ts0 f3 outParameterDecl '<> result;' #txt
Ts0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>loadUsersOfRole(String)</name>
    </language>
</elementInfo>
' #txt
Ts0 f3 83 211 26 26 -50 22 #rect
Ts0 f3 @|UdMethodIcon #fIcon
Ts0 f4 635 211 26 26 0 12 #rect
Ts0 f4 @|UdProcessEndIcon #fIcon
Ts0 f8 processCall 'Ivy Data Processes/SecurityService:findAllUsersOfRoles(ch.ivyteam.ivy.application.IApplication,Integer,Integer,java.util.List<String>,java.util.List<String>)' #txt
Ts0 f8 requestActionDecl '<ch.ivyteam.ivy.application.IApplication application,Integer startIndex,Integer count,java.util.List<String> fromRoles,java.util.List<String> excludedUsernames> param;' #txt
Ts0 f8 requestMappingAction 'param.application=ivy.request.getApplication();
param.startIndex=0;
param.count=100;
param.fromRoles=java.util.Arrays.asList(in.roleName);
' #txt
Ts0 f8 responseMappingAction 'out=in;
out.memberNames=result.users;
' #txt
Ts0 f8 responseActionCode '
' #txt
Ts0 f8 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Ivy Data Processes/SecurityService</name>
    </language>
</elementInfo>
' #txt
Ts0 f8 192 202 208 44 -95 -8 #rect
Ts0 f8 @|CallSubIcon #fIcon
Ts0 f6 109 224 192 224 #arcP
Ts0 f7 guid 1771E14A66279842 #txt
Ts0 f7 method filterUserByName(String) #txt
Ts0 f7 inParameterDecl '<String userName> param;' #txt
Ts0 f7 inParameterMapAction 'out.keyword=param.userName;
' #txt
Ts0 f7 outParameterDecl '<> result;' #txt
Ts0 f7 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>filterUserByName(String)</name>
    </language>
</elementInfo>
' #txt
Ts0 f7 83 339 26 26 -56 15 #rect
Ts0 f7 @|UdMethodIcon #fIcon
Ts0 f9 467 339 26 26 0 12 #rect
Ts0 f9 @|UdProcessEndIcon #fIcon
Ts0 f13 processCall 'Ivy Data Processes/SecurityService:findUsers(ch.ivyteam.ivy.application.IApplication,String,Integer,Integer,java.util.List<String>,java.util.List<String>)' #txt
Ts0 f13 requestActionDecl '<ch.ivyteam.ivy.application.IApplication application,String query,Integer startIndex,Integer count,java.util.List<String> fromRoles,java.util.List<String> excludedUsernames> param;' #txt
Ts0 f13 requestMappingAction 'param.application=ivy.request.getApplication();
param.query=in.keyword;
param.startIndex=0;
param.count=100;
param.fromRoles=java.util.Arrays.asList(in.roleName);
' #txt
Ts0 f13 responseMappingAction 'out=in;
out.memberNames=result.users;
' #txt
Ts0 f13 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Ivy Data Processes/SecurityService</name>
    </language>
</elementInfo>
' #txt
Ts0 f13 192 330 208 44 -95 -8 #rect
Ts0 f13 @|CallSubIcon #fIcon
Ts0 f10 400 352 467 352 #arcP
Ts0 f11 109 352 192 352 #arcP
Ts0 f12 actionTable 'out=in;
' #txt
Ts0 f12 actionCode 'import org.apache.commons.lang3.BooleanUtils;
import ch.ivy.addon.portalkit.enums.GlobalVariable;
import ch.ivy.addon.portalkit.service.GlobalSettingService;

GlobalSettingService service = new GlobalSettingService();
String showAllUserConfig = service.findGlobalSettingValue(GlobalVariable.DISPLAY_USERS_OF_ROLE.name());
in.isShowAllUser = BooleanUtils.toBoolean(showAllUserConfig);' #txt
Ts0 f12 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Read Global config</name>
    </language>
</elementInfo>
' #txt
Ts0 f12 192 74 112 44 -52 -8 #rect
Ts0 f12 @|StepIcon #fIcon
Ts0 f14 expr out #txt
Ts0 f14 107 96 192 96 #arcP
Ts0 f2 304 96 365 96 #arcP
Ts0 f15 actionTable 'out=in;
' #txt
Ts0 f15 actionCode 'import ch.ivy.addon.portalkit.dto.UserDTO;
if (in.memberNames.size() >= 100) {
	UserDTO user = new UserDTO();
	user.displayName = ivy.cms.co("/ch.ivy.addon.portalkit.ui.jsf/components/SecurityMemberDisplayName/filterToSee").concat(" ...");
	in.memberNames.add(user);
}' #txt
Ts0 f15 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Add a dummy user</name>
    </language>
</elementInfo>
' #txt
Ts0 f15 464 202 112 44 -51 -8 #rect
Ts0 f15 @|StepIcon #fIcon
Ts0 f16 400 224 464 224 #arcP
Ts0 f5 576 224 635 224 #arcP
Ts0 f17 guid 178FE4592368A822 #txt
Ts0 f17 method loadUsersForTooltip(ch.ivyteam.ivy.security.ISecurityMember) #txt
Ts0 f17 inParameterDecl '<ch.ivyteam.ivy.security.ISecurityMember securityMember> param;' #txt
Ts0 f17 inActionCode 'if (param.securityMember != null) {
	out.isRenderUserOfRole = !param.securityMember.isUser();
	out.roleName = param.securityMember.getName();
}' #txt
Ts0 f17 outParameterDecl '<> result;' #txt
Ts0 f17 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>loadUsersForTooltip(ISecurityMember)</name>
    </language>
</elementInfo>
' #txt
Ts0 f17 83 467 26 26 -83 16 #rect
Ts0 f17 @|UdMethodIcon #fIcon
Ts0 f18 435 467 26 26 0 12 #rect
Ts0 f18 @|UdProcessEndIcon #fIcon
Ts0 f20 299 563 26 26 0 12 #rect
Ts0 f20 @|UdProcessEndIcon #fIcon
Ts0 f21 176 464 32 32 0 16 #rect
Ts0 f21 @|AlternativeIcon #fIcon
Ts0 f22 109 480 176 480 #arcP
Ts0 f23 expr in #txt
Ts0 f23 192 496 299 576 #arcP
Ts0 f23 1 192 576 #addKink
Ts0 f23 0 0.6781710119583497 0 0 #arcLabel
Ts0 f24 actionTable 'out=in;
' #txt
Ts0 f24 actionCode 'import ch.ivyteam.ivy.persistence.query.IPagedResult;
import ch.ivyteam.ivy.security.IUser;
import ch.ivyteam.ivy.security.IRole;
import ch.ivy.addon.portalkit.util.SecurityMemberUtils;

IRole role = ivy.wf.getSecurityContext().findRole(in.roleName);
IPagedResult<IUser> result = role.users().assignedPaged(10);
List<IUser> users = result.page(1);
long totalCount = result.count();

in.usersInTooltips = SecurityMemberUtils.buildTooltipFromUsers(role, users, totalCount);' #txt
Ts0 f24 security system #txt
Ts0 f24 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Load 10 users for tooltip</name>
    </language>
</elementInfo>
' #txt
Ts0 f24 248 458 144 44 -66 -8 #rect
Ts0 f24 @|StepIcon #fIcon
Ts0 f25 expr in #txt
Ts0 f25 outCond 'in.isShowAllUser && in.isRenderUserOfRole' #txt
Ts0 f25 208 480 248 480 #arcP
Ts0 f19 392 480 435 480 #arcP
>Proto Ts0 .type ch.ivy.addon.portalkit.component.SecurityMemberDisplayName.SecurityMemberDisplayNameData #txt
>Proto Ts0 .processKind HTML_DIALOG #txt
>Proto Ts0 -8 -8 16 16 16 26 #rect
>Proto Ts0 '' #fIcon
Ts0 f3 mainOut f6 tail #connect
Ts0 f6 head f8 mainIn #connect
Ts0 f13 mainOut f10 tail #connect
Ts0 f10 head f9 mainIn #connect
Ts0 f7 mainOut f11 tail #connect
Ts0 f11 head f13 mainIn #connect
Ts0 f0 mainOut f14 tail #connect
Ts0 f14 head f12 mainIn #connect
Ts0 f12 mainOut f2 tail #connect
Ts0 f2 head f1 mainIn #connect
Ts0 f8 mainOut f16 tail #connect
Ts0 f16 head f15 mainIn #connect
Ts0 f15 mainOut f5 tail #connect
Ts0 f5 head f4 mainIn #connect
Ts0 f17 mainOut f22 tail #connect
Ts0 f22 head f21 in #connect
Ts0 f23 head f20 mainIn #connect
Ts0 f21 out f25 tail #connect
Ts0 f25 head f24 mainIn #connect
Ts0 f21 out f23 tail #connect
Ts0 f24 mainOut f19 tail #connect
Ts0 f19 head f18 mainIn #connect
