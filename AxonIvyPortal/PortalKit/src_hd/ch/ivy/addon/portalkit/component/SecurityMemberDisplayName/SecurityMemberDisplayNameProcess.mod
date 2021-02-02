[Ivy]
17255D74942B8265 9.2.0 #module
>Proto >Proto Collection #zClass
Ss0 SecurityMemberDisplayNameProcess Big #zClass
Ss0 RD #cInfo
Ss0 #process
Ss0 @TextInP .type .type #zField
Ss0 @TextInP .processKind .processKind #zField
Ss0 @TextInP .xml .xml #zField
Ss0 @TextInP .responsibility .responsibility #zField
Ss0 @CallSub f13 '' #zField
Ss0 @GridStep f12 '' #zField
Ss0 @GridStep f15 '' #zField
Ss0 @UdInit f0 '' #zField
Ss0 @UdProcessEnd f9 '' #zField
Ss0 @UdMethod f7 '' #zField
Ss0 @CallSub f8 '' #zField
Ss0 @UdProcessEnd f1 '' #zField
Ss0 @UdMethod f3 '' #zField
Ss0 @UdProcessEnd f4 '' #zField
Ss0 @PushWFArc f11 '' #zField
Ss0 @PushWFArc f6 '' #zField
Ss0 @PushWFArc f14 '' #zField
Ss0 @PushWFArc f2 '' #zField
Ss0 @PushWFArc f16 '' #zField
Ss0 @PushWFArc f5 '' #zField
Ss0 @PushWFArc f10 '' #zField
>Proto Ss0 Ss0 SecurityMemberDisplayNameProcess #zField
Ss0 f13 processCall 'Ivy Data Processes/SecurityService:findUsers(String,Integer,Integer,java.util.List<String>,java.util.List<String>)' #txt
Ss0 f13 requestActionDecl '<String query,Integer startIndex,Integer count,java.util.List<String> fromRoles,java.util.List<String> excludedUsernames> param;' #txt
Ss0 f13 requestMappingAction 'param.query=in.keyword;
param.startIndex=0;
param.count=100;
param.fromRoles=java.util.Arrays.asList(in.roleName);
' #txt
Ss0 f13 responseMappingAction 'out=in;
out.memberNames=result.users;
' #txt
Ss0 f13 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Ivy Data Processes/SecurityService</name>
    </language>
</elementInfo>
' #txt
Ss0 f13 224 330 208 44 -95 -8 #rect
Ss0 f12 actionTable 'out=in;
' #txt
Ss0 f12 actionCode 'import org.apache.commons.lang3.BooleanUtils;
import ch.ivy.addon.portalkit.enums.GlobalVariable;
import ch.ivy.addon.portalkit.service.GlobalSettingService;

GlobalSettingService service = new GlobalSettingService();
String showAllUserConfig = service.findGlobalSettingValue(GlobalVariable.DISPLAY_ALL_USERS_OF_TASK_ACTIVATOR.name());
in.isShowAllUser = BooleanUtils.toBoolean(showAllUserConfig);' #txt
Ss0 f12 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Read Global config</name>
    </language>
</elementInfo>
' #txt
Ss0 f12 224 74 112 44 -52 -8 #rect
Ss0 f15 actionTable 'out=in;
' #txt
Ss0 f15 actionCode 'import ch.ivy.addon.portalkit.dto.UserDTO;
if (in.memberNames.size() >= 100) {
	UserDTO user = new UserDTO();
	user.displayName = ivy.cms.co("/ch.ivy.addon.portalkit.ui.jsf/components/SecurityMemberDisplayName/filterToSee").concat(" ...");
	in.memberNames.add(user);
}' #txt
Ss0 f15 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Add a dummy user</name>
    </language>
</elementInfo>
' #txt
Ss0 f15 496 202 112 44 -51 -8 #rect
Ss0 f0 guid 177418D19BD97AC4 #txt
Ss0 f0 method start() #txt
Ss0 f0 inParameterDecl '<> param;' #txt
Ss0 f0 outParameterDecl '<> result;' #txt
Ss0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start()</name>
    </language>
</elementInfo>
' #txt
Ss0 f0 117 85 22 22 -9 9 #rect
Ss0 f9 499 339 26 26 0 12 #rect
Ss0 f7 guid 177418D19BDA7947 #txt
Ss0 f7 method filterUserByName(String) #txt
Ss0 f7 inParameterDecl '<String userName> param;' #txt
Ss0 f7 inParameterMapAction 'out.keyword=param.userName;
' #txt
Ss0 f7 outParameterDecl '<> result;' #txt
Ss0 f7 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>filterUserByName(String)</name>
    </language>
</elementInfo>
' #txt
Ss0 f7 115 339 26 26 -56 15 #rect
Ss0 f8 processCall 'Ivy Data Processes/SecurityService:findAllUsersOfRoles(Integer,Integer,java.util.List<String>,java.util.List<String>)' #txt
Ss0 f8 requestActionDecl '<Integer startIndex,Integer count,java.util.List<String> fromRoles,java.util.List<String> excludedUsernames> param;' #txt
Ss0 f8 requestMappingAction 'param.startIndex=0;
param.count=100;
param.fromRoles=java.util.Arrays.asList(in.roleName);
' #txt
Ss0 f8 responseMappingAction 'out=in;
out.memberNames=result.users;
' #txt
Ss0 f8 responseActionCode '
' #txt
Ss0 f8 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Ivy Data Processes/SecurityService</name>
    </language>
</elementInfo>
' #txt
Ss0 f8 224 202 208 44 -95 -8 #rect
Ss0 f1 397 85 22 22 14 0 #rect
Ss0 f3 guid 177418D19BD6A884 #txt
Ss0 f3 method loadUsersOfRole(String) #txt
Ss0 f3 inParameterDecl '<String roleName> param;' #txt
Ss0 f3 inParameterMapAction 'out.roleName=param.roleName;
' #txt
Ss0 f3 inActionCode 'out.memberNames.clear();
out.keyword = "";' #txt
Ss0 f3 outParameterDecl '<> result;' #txt
Ss0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>loadUsersOfRole(String)</name>
    </language>
</elementInfo>
' #txt
Ss0 f3 115 211 26 26 -50 22 #rect
Ss0 f4 667 211 26 26 0 12 #rect
Ss0 f11 141 352 224 352 #arcP
Ss0 f6 141 224 224 224 #arcP
Ss0 f14 expr out #txt
Ss0 f14 139 96 224 96 #arcP
Ss0 f2 336 96 397 96 #arcP
Ss0 f16 432 224 496 224 #arcP
Ss0 f5 608 224 667 224 #arcP
Ss0 f10 432 352 499 352 #arcP
>Proto Ss0 .type ch.ivy.addon.portalkit.component.SecurityMemberDisplayName.SecurityMemberDisplayNameData #txt
>Proto Ss0 .processKind HTML_DIALOG #txt
>Proto Ss0 -8 -8 16 16 16 26 #rect
Ss0 f3 mainOut f6 tail #connect
Ss0 f6 head f8 mainIn #connect
Ss0 f13 mainOut f10 tail #connect
Ss0 f10 head f9 mainIn #connect
Ss0 f7 mainOut f11 tail #connect
Ss0 f11 head f13 mainIn #connect
Ss0 f0 mainOut f14 tail #connect
Ss0 f14 head f12 mainIn #connect
Ss0 f12 mainOut f2 tail #connect
Ss0 f2 head f1 mainIn #connect
Ss0 f8 mainOut f16 tail #connect
Ss0 f16 head f15 mainIn #connect
Ss0 f15 mainOut f5 tail #connect
Ss0 f5 head f4 mainIn #connect
