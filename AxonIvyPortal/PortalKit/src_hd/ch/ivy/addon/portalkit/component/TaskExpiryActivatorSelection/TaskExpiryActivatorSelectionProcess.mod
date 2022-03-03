[Ivy]
178DA0DE8672E8A1 9.4.0 #module
>Proto >Proto Collection #zClass
Ts0 TaskExpiryActivatorSelectionProcess Big #zClass
Ts0 RD #cInfo
Ts0 #process
Ct3 Component Big #zClass
Ct3 B #cInfo
Ct2 Component Big #zClass
Ct2 B #cInfo
Ts0 Ct3 S10 'Sub 1' #zField
Ts0 @TextInP .type .type #zField
Ts0 @TextInP .processKind .processKind #zField
Ts0 @TextInP .xml .xml #zField
Ts0 @TextInP .responsibility .responsibility #zField
Ts0 @UdInit f0 '' #zField
Ts0 @UdProcessEnd f1 '' #zField
Ts0 @PushWFArc f2 '' #zField
Ts0 @UdProcessEnd f66 '' #zField
Ts0 @UdEvent f108 '' #zField
Ts0 @CallSub f86 '' #zField
Ts0 @UdMethod f73 '' #zField
Ts0 @UdProcessEnd f106 '' #zField
Ts0 @GridStep f107 '' #zField
Ts0 @UdMethod f71 '' #zField
Ts0 @UdMethod f74 '' #zField
Ts0 @UdProcessEnd f69 '' #zField
Ts0 @GridStep f77 '' #zField
Ts0 @PushWFArc f110 '' #zField
Ts0 @PushWFArc f109 '' #zField
Ts0 @PushWFArc f6 '' #zField
Ts0 @CallSub f9 '' #zField
Ts0 @PushWFArc f10 '' #zField
Ts0 @PushWFArc f11 '' #zField
Ts0 @PushWFArc f26 '' #zField
Ts0 Ct2 S30 'Sub 3' #zField
Ts0 @PushWFArc f4 '' #zField
Ts0 @UdProcessEnd f13 '' #zField
Ts0 @UdMethod f16 '' #zField
Ts0 @PushWFArc f21 '' #zField
Ts0 @PushWFArc f22 '' #zField
Ts0 @PushWFArc f3 '' #zField
Ts0 @PushWFArc f5 '' #zField
>Proto Ts0 Ts0 TaskExpiryActivatorSelectionProcess #zField
Ct3 @AnnotationInP-0n ai ai #zField
Ct3 @TextInP .type .type #zField
Ct3 @TextInP .processKind .processKind #zField
Ct3 @TextInP .xml .xml #zField
Ct3 @TextInP .responsibility .responsibility #zField
Ct3 @PushWFArc f98 '' #zField
Ct3 @PushWFArc f20 '' #zField
Ct3 @CallSub f5 '' #zField
Ct3 @PushWFArc f18 '' #zField
Ct3 @CallSub f17 '' #zField
Ct3 @CallSub f15 '' #zField
Ct3 @GridStep f89 '' #zField
Ct3 @PushTrueWFInG-01 g0 '' #zField
Ct3 @PushWFArc f0 '' #zField
Ct3 @PushTrueWFOutG-01 g1 '' #zField
Ct3 @PushWFArc f1 '' #zField
>Proto Ct3 Ct0 Component #zField
Ct2 @AnnotationInP-0n ai ai #zField
Ct2 @TextInP .type .type #zField
Ct2 @TextInP .processKind .processKind #zField
Ct2 @TextInP .xml .xml #zField
Ct2 @TextInP .responsibility .responsibility #zField
Ct2 @GridStep f4 '' #zField
Ct2 @PushWFArc f12 '' #zField
Ct2 @GridStep f8 '' #zField
Ct2 @PushTrueWFInG-01 g0 '' #zField
Ct2 @PushTrueWFOutG-01 g1 '' #zField
Ct2 @PushWFArc f0 '' #zField
Ct2 @PushWFArc f1 '' #zField
>Proto Ct2 Ct1 Component #zField
Ts0 S10 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language lang="en">
        <name>Check any user/roles available to show</name>
    </language>
</elementInfo>
' #txt
Ts0 S10 224 138 224 44 -107 -8 #rect
Ts0 f0 guid 16EE8CACE694620C #txt
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
Ts0 f0 83 51 26 26 -16 15 #rect
Ts0 f1 211 51 26 26 0 12 #rect
Ts0 f2 109 64 211 64 #arcP
Ts0 f66 211 243 26 26 0 12 #rect
Ts0 f108 guid 16EE8CC0919A3695 #txt
Ts0 f108 actionTable 'out=in;
' #txt
Ts0 f108 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>changeAssignType</name>
    </language>
</elementInfo>
' #txt
Ts0 f108 83 531 26 26 -47 15 #rect
Ts0 f86 processCall 'Ivy Data Processes/SecurityService:findUsers(String,Integer,Integer,java.util.List<String>,java.util.List<String>)' #txt
Ts0 f86 requestActionDecl '<String query,Integer startIndex,Integer count,java.util.List<String> fromRoles,java.util.List<String> excludedUsernames> param;' #txt
Ts0 f86 requestMappingAction 'param.query=in.queryAutoComplete;
param.startIndex=0;
param.count=101;
' #txt
Ts0 f86 responseActionDecl 'ch.ivy.addon.portalkit.component.SideStep.SideStepData out;
' #txt
Ts0 f86 responseMappingAction 'out=in;
out.users=result.users;
' #txt
Ts0 f86 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>SecurityService - Find Users</name>
    </language>
</elementInfo>
' #txt
Ts0 f86 224 330 160 44 -77 -8 #rect
Ts0 f73 guid 16EE8CC091943071 #txt
Ts0 f73 method autoCompleteForUser(String) #txt
Ts0 f73 inParameterDecl '<String query> param;' #txt
Ts0 f73 inParameterMapAction 'out.queryAutoComplete=param.query;
' #txt
Ts0 f73 outParameterDecl '<java.util.List<ch.ivy.addon.portalkit.dto.UserDTO> usersToDelegate> result;' #txt
Ts0 f73 outParameterMapAction 'result.usersToDelegate=in.users;
' #txt
Ts0 f73 outActionCode '
' #txt
Ts0 f73 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>autoCompleteForUser(String)</name>
    </language>
</elementInfo>
' #txt
Ts0 f73 83 339 26 26 -52 12 #rect
Ts0 f106 659 531 26 26 0 12 #rect
Ts0 f107 actionTable 'out=in;
' #txt
Ts0 f107 actionCode 'out.selectedActivator = null;
out.selectedRole = null;
out.selectedUser = null;
out.isDisabledAssignButton = true;' #txt
Ts0 f107 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Change assignee type</name>
    </language>
</elementInfo>
' #txt
Ts0 f107 440 522 128 44 -61 -8 #rect
Ts0 f71 guid 16EE8CC091941043 #txt
Ts0 f71 method assignTask() #txt
Ts0 f71 inParameterDecl '<> param;' #txt
Ts0 f71 outParameterDecl '<> result;' #txt
Ts0 f71 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>assignTask()</name>
    </language>
</elementInfo>
' #txt
Ts0 f71 83 435 26 26 -41 15 #rect
Ts0 f74 guid 16EE8CC09192B441 #txt
Ts0 f74 method autoCompleteForRole(String) #txt
Ts0 f74 inParameterDecl '<String query> param;' #txt
Ts0 f74 inParameterMapAction 'out.queryAutoComplete=param.query;
' #txt
Ts0 f74 outParameterDecl '<java.util.List<ch.ivy.addon.portalkit.dto.RoleDTO> rolesToDelegate> result;' #txt
Ts0 f74 outActionCode 'import ch.ivy.addon.portalkit.util.RoleUtils;

result.rolesToDelegate = RoleUtils.filterRoleDTO(in.roles, in.queryAutoComplete);' #txt
Ts0 f74 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>autoCompleteForRole(String)</name>
    </language>
</elementInfo>
' #txt
Ts0 f74 83 243 26 26 -56 13 #rect
Ts0 f69 659 339 26 26 0 12 #rect
Ts0 f77 actionTable 'out=in;
' #txt
Ts0 f77 actionCode 'in.task.getCase().createNote(ivy.session, in.notes);' #txt
Ts0 f77 security system #txt
Ts0 f77 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Add note</name>
    </language>
</elementInfo>
' #txt
Ts0 f77 448 426 112 44 -24 -8 #rect
Ts0 f110 expr out #txt
Ts0 f110 109 544 440 544 #arcP
Ts0 f109 expr out #txt
Ts0 f109 568 544 659 544 #arcP
Ts0 f6 expr out #txt
Ts0 f6 109 352 224 352 #arcP
Ts0 f9 processCall 'Functional Processes/CalculateTaskDelegate:call(java.util.List<ch.ivy.addon.portalkit.dto.RoleDTO>,java.util.List<ch.ivy.addon.portalkit.dto.UserDTO>,ch.ivy.addon.portalkit.dto.SecurityMemberDTO,ch.ivyteam.ivy.workflow.ITask)' #txt
Ts0 f9 requestActionDecl '<java.util.List<ch.ivy.addon.portalkit.dto.RoleDTO> roles,java.util.List<ch.ivy.addon.portalkit.dto.UserDTO> users,ch.ivy.addon.portalkit.dto.SecurityMemberDTO currentUser,ch.ivyteam.ivy.workflow.ITask task> param;' #txt
Ts0 f9 requestMappingAction 'param.roles=in.roles;
param.users=in.users;
param.currentUser=ch.ivy.addon.portalkit.util.SecurityMemberUtils.getCurrentSessionUserAsSecurityMemberDTO();
param.task=in.task;
' #txt
Ts0 f9 responseActionDecl 'ch.ivy.addon.portalkit.component.SideStep.SideStepData out;
' #txt
Ts0 f9 responseMappingAction 'out=in;
out.users=result.users;
' #txt
Ts0 f9 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>CalculateTaskDelegate</name>
    </language>
</elementInfo>
' #txt
Ts0 f9 448 330 144 44 -64 -8 #rect
Ts0 f10 384 352 448 352 #arcP
Ts0 f11 592 352 659 352 #arcP
Ts0 f26 384 448 448 448 #arcP
Ts0 f26 0 0.5344590225601971 0 0 #arcLabel
Ts0 S30 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language lang="en">
        <name>Change Task ExpiryActivator</name>
    </language>
</elementInfo>
' #txt
Ts0 S30 224 426 160 44 -77 -8 #rect
Ts0 f4 109 448 224 448 #arcP
Ts0 f13 531 147 26 26 0 12 #rect
Ts0 f16 guid 178DA529D18507DC #txt
Ts0 f16 method calculateTaskDelegateOptions(ch.ivyteam.ivy.workflow.ITask) #txt
Ts0 f16 inParameterDecl '<ch.ivyteam.ivy.workflow.ITask task> param;' #txt
Ts0 f16 inParameterMapAction 'out.isDisabledAssignButton=true;
out.isUserAssigned=true;
out.task=param.task;
' #txt
Ts0 f16 outParameterDecl '<> result;' #txt
Ts0 f16 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>calculateTaskDelegateOptions(ITask)</name>
    </language>
</elementInfo>
' #txt
Ts0 f16 83 147 26 26 -72 15 #rect
Ts0 f21 109 160 224 160 #arcP
Ts0 f22 448 160 531 160 #arcP
Ts0 f3 expr out #txt
Ts0 f3 109 256 211 256 #arcP
Ts0 f3 0 0.61063400144005 0 0 #arcLabel
Ts0 f5 expr out #txt
Ts0 f5 504 470 504 522 #arcP
>Proto Ts0 .type ch.ivy.addon.portalkit.component.TaskExpiryActivatorSelection.TaskExpiryActivatorSelectionData #txt
>Proto Ts0 .processKind HTML_DIALOG #txt
>Proto Ts0 -8 -8 16 16 16 26 #rect
Ct3 f98 expr out #txt
Ct3 f98 736 160 832 160 #arcP
Ct3 f20 296 160 368 160 #arcP
Ct3 f5 processCall 'Ivy Data Processes/SecurityService:findRolesDTO()' #txt
Ct3 f5 requestActionDecl '<> param;' #txt
Ct3 f5 responseMappingAction 'out=in;
out.roles=result.roles;
' #txt
Ct3 f5 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>SecurityService - find roles</name>
    </language>
</elementInfo>
' #txt
Ct3 f5 368 138 160 44 -72 -8 #rect
Ct3 f18 528 160 592 160 #arcP
Ct3 f17 processCall 'Functional Processes/CalculateTaskDelegate:call(java.util.List<ch.ivy.addon.portalkit.dto.RoleDTO>,java.util.List<ch.ivy.addon.portalkit.dto.UserDTO>,ch.ivy.addon.portalkit.dto.SecurityMemberDTO,ch.ivyteam.ivy.workflow.ITask)' #txt
Ct3 f17 requestActionDecl '<java.util.List<ch.ivy.addon.portalkit.dto.RoleDTO> roles,java.util.List<ch.ivy.addon.portalkit.dto.UserDTO> users,ch.ivy.addon.portalkit.dto.SecurityMemberDTO currentUser,ch.ivyteam.ivy.workflow.ITask task> param;' #txt
Ct3 f17 requestMappingAction 'param.roles=in.roles;
param.users=in.users;
param.currentUser=ch.ivy.addon.portalkit.util.SecurityMemberUtils.getCurrentSessionUserAsSecurityMemberDTO();
param.task=in.task;
' #txt
Ct3 f17 responseActionDecl 'ch.ivy.addon.portalkit.component.SideStep.SideStepData out;
' #txt
Ct3 f17 responseMappingAction 'out=in;
out.roles=result.roles;
out.users=result.users;
' #txt
Ct3 f17 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>CalculateTaskDelegate</name>
    </language>
</elementInfo>
' #txt
Ct3 f17 592 138 144 44 -64 -8 #rect
Ct3 f15 processCall 'Ivy Data Processes/SecurityService:findUsers(String,Integer,Integer,java.util.List<String>,java.util.List<String>)' #txt
Ct3 f15 requestActionDecl '<String query,Integer startIndex,Integer count,java.util.List<String> fromRoles,java.util.List<String> excludedUsernames> param;' #txt
Ct3 f15 requestMappingAction 'param.query="";
param.startIndex=0;
param.count=101;
' #txt
Ct3 f15 responseActionDecl 'ch.ivy.addon.portalkit.component.SideStep.SideStepData out;
' #txt
Ct3 f15 responseMappingAction 'out=in;
out.users=result.users;
' #txt
Ct3 f15 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>SecurityService - Find Users</name>
    </language>
</elementInfo>
' #txt
Ct3 f15 136 138 160 44 -77 -8 #rect
Ct3 f89 actionTable 'out=in;
' #txt
Ct3 f89 actionCode 'in.canChangeTaskActivator = !(in.roles.isEmpty() && in.users.isEmpty());

if (in.canChangeTaskActivator) {
	if (in.roles.isEmpty()) {
		in.isUserAssigned = true;
	}
	if (in.users.isEmpty()) {
		in.isUserAssigned = false;
	}
}
' #txt
Ct3 f89 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Check if can&#xD;
delegate task</name>
    </language>
</elementInfo>
' #txt
Ct3 f89 832 138 112 44 -37 -16 #rect
Ct3 g0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language lang="en">
        <name>in 1</name>
    </language>
</elementInfo>
' #txt
Ct3 g0 51 147 26 26 -19 17 #rect
Ct3 f0 77 160 136 160 #arcP
Ct3 g1 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language lang="en">
        <name>out 1</name>
    </language>
</elementInfo>
' #txt
Ct3 g1 1011 147 26 26 -7 11 #rect
Ct3 f1 expr out #txt
Ct3 f1 944 160 1011 160 #arcP
>Proto Ct0 0 0 32 24 18 0 #rect
>Proto Ct0 @|BIcon #fIcon
Ct2 f4 actionTable 'out=in;
' #txt
Ct2 f4 actionCode 'import ch.ivy.addon.portalkit.util.SecurityMemberUtils;
import org.apache.commons.lang.StringUtils;

if (in.#selectedUser != null) {
	in.selectedActivator = SecurityMemberUtils.findISecurityMemberFromUserDTO(in.selectedUser);
} else if (in.#selectedRole != null) {
	in.selectedActivator = SecurityMemberUtils.findISecurityMemberFromRoleDTO(in.selectedRole);
}

String oldResponsibleName = ivy.cms.co("/ch.ivy.addon.portalkit.ui.jsf/common/notAvailable");
if (in.task.getExpiryActivator() != null) {
	oldResponsibleName = in.task.getExpiryActivator().getDisplayName();
}
String newResponsibleName = in.selectedActivator.getDisplayName();

in.notes = ivy.cms.co("/ch.ivy.addon.portalkit.ui.jsf/taskDelegate/afterEscalation/delegateNotes", [in.task.getId(), oldResponsibleName, newResponsibleName]);
if (StringUtils.isNotBlank(in.#comment)) {
	in.notes = ivy.cms.co("/ch.ivy.addon.portalkit.ui.jsf/taskDelegate/afterEscalation/delegateNotesWithComment", [in.task.getId(), oldResponsibleName, newResponsibleName, in.#comment.trim()]);
}

in.comment = "";' #txt
Ct2 f4 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Create notes</name>
    </language>
</elementInfo>
' #txt
Ct2 f4 232 138 112 44 -35 -8 #rect
Ct2 f12 344 160 432 160 #arcP
Ct2 f8 actionTable 'out=in;
' #txt
Ct2 f8 actionCode 'import ch.ivy.addon.portalkit.util.TaskUtils;

TaskUtils.delegateTaskAfterEscalation(in.task, in.selectedActivator);' #txt
Ct2 f8 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Change TaskEscalationActivator</name>
    </language>
</elementInfo>
' #txt
Ct2 f8 432 138 192 44 -88 -8 #rect
Ct2 g0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language lang="en">
        <name>in 1</name>
    </language>
</elementInfo>
' #txt
Ct2 g0 51 147 26 26 -8 13 #rect
Ct2 g1 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language lang="en">
        <name>out 1</name>
    </language>
</elementInfo>
' #txt
Ct2 g1 915 147 26 26 0 5 #rect
Ct2 f0 77 160 232 160 #arcP
Ct2 f0 0 0.4112627986348123 0 0 #arcLabel
Ct2 f1 624 160 915 160 #arcP
>Proto Ct1 0 0 32 24 18 0 #rect
>Proto Ct1 @|BIcon #fIcon
Ts0 f0 mainOut f2 tail #connect
Ts0 f2 head f1 mainIn #connect
Ts0 f107 mainOut f109 tail #connect
Ts0 f109 head f106 mainIn #connect
Ts0 f108 mainOut f110 tail #connect
Ts0 f110 head f107 mainIn #connect
Ts0 f73 mainOut f6 tail #connect
Ts0 f6 head f86 mainIn #connect
Ts0 f86 mainOut f10 tail #connect
Ts0 f10 head f9 mainIn #connect
Ts0 f9 mainOut f11 tail #connect
Ts0 f11 head f69 mainIn #connect
Ts0 S30 g1 f26 tail #connect
Ts0 f26 head f77 mainIn #connect
Ts0 f71 mainOut f4 tail #connect
Ts0 f4 head S30 g0 #connect
Ts0 f21 head S10 g0 #connect
Ts0 S10 g1 f22 tail #connect
Ts0 f22 head f13 mainIn #connect
Ts0 f16 mainOut f21 tail #connect
Ts0 f74 mainOut f3 tail #connect
Ts0 f3 head f66 mainIn #connect
Ts0 f77 mainOut f5 tail #connect
Ts0 f5 head f107 mainIn #connect
Ct3 f17 mainOut f98 tail #connect
Ct3 f98 head f89 mainIn #connect
Ct3 f5 mainOut f18 tail #connect
Ct3 f18 head f17 mainIn #connect
Ct3 f15 mainOut f20 tail #connect
Ct3 f20 head f5 mainIn #connect
Ct3 g0 m f0 tail #connect
Ct3 f0 head f15 mainIn #connect
Ct3 f1 head g1 m #connect
Ct3 f89 mainOut f1 tail #connect
Ct3 0 0 1088 320 0 #ivRect
Ct2 f4 mainOut f12 tail #connect
Ct2 f12 head f8 mainIn #connect
Ct2 g0 m f0 tail #connect
Ct2 f0 head f4 mainIn #connect
Ct2 f8 mainOut f1 tail #connect
Ct2 f1 head g1 m #connect
Ct2 0 0 992 392 0 #ivRect
