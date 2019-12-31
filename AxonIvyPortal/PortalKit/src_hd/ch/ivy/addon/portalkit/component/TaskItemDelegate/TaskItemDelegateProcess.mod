[Ivy]
16EE8CACE2CB2C75 7.5.0 #module
>Proto >Proto Collection #zClass
Ts0 TaskItemDelegateProcess Big #zClass
Ts0 RD #cInfo
Ts0 #process
Ts0 @TextInP .type .type #zField
Ts0 @TextInP .processKind .processKind #zField
Ts0 @TextInP .xml .xml #zField
Ts0 @TextInP .responsibility .responsibility #zField
Ts0 @UdInit f0 '' #zField
Ts0 @UdProcessEnd f1 '' #zField
Ts0 @PushWFArc f2 '' #zField
Ts0 @UdProcessEnd f66 '' #zField
Ts0 @UdEvent f108 '' #zField
Ts0 @UdProcessEnd f90 '' #zField
Ts0 @UdProcessEnd f79 '' #zField
Ts0 @GridStep f93 '' #zField
Ts0 @CallSub f86 '' #zField
Ts0 @UdMethod f73 '' #zField
Ts0 @GridStep f80 '' #zField
Ts0 @GridStep f87 '' #zField
Ts0 @UdProcessEnd f106 '' #zField
Ts0 @GridStep f107 '' #zField
Ts0 @UdMethod f71 '' #zField
Ts0 @UdMethod f74 '' #zField
Ts0 @CallSub f81 '' #zField
Ts0 @GridStep f92 '' #zField
Ts0 @GridStep f76 '' #zField
Ts0 @Alternative f88 '' #zField
Ts0 @UdMethod f82 '' #zField
Ts0 @GridStep f89 '' #zField
Ts0 @UdProcessEnd f69 '' #zField
Ts0 @GridStep f77 '' #zField
Ts0 @PushWFArc f100 '' #zField
Ts0 @PushWFArc f103 '' #zField
Ts0 @PushWFArc f110 '' #zField
Ts0 @PushWFArc f99 '' #zField
Ts0 @PushWFArc f102 '' #zField
Ts0 @PushWFArc f94 '' #zField
Ts0 @PushWFArc f109 '' #zField
Ts0 @PushWFArc f105 '' #zField
Ts0 @PushWFArc f104 '' #zField
Ts0 @PushWFArc f97 '' #zField
Ts0 @PushWFArc f98 '' #zField
Ts0 @PushWFArc f101 '' #zField
Ts0 @PushWFArc f95 '' #zField
Ts0 @PushWFArc f91 '' #zField
Ts0 @PushWFArc f3 '' #zField
Ts0 @PushWFArc f4 '' #zField
Ts0 @CallSub f5 '' #zField
Ts0 @PushWFArc f6 '' #zField
Ts0 @PushWFArc f7 '' #zField
>Proto Ts0 Ts0 TaskItemDelegateProcess #zField
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
Ts0 f0 @|UdInitIcon #fIcon
Ts0 f1 211 51 26 26 0 12 #rect
Ts0 f1 @|UdProcessEndIcon #fIcon
Ts0 f2 109 64 211 64 #arcP
Ts0 f66 339 323 26 26 0 12 #rect
Ts0 f66 @|UdProcessEndIcon #fIcon
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
Ts0 f108 83 643 26 26 -47 15 #rect
Ts0 f108 @|UdEventIcon #fIcon
Ts0 f90 1339 155 26 26 0 12 #rect
Ts0 f90 @|UdProcessEndIcon #fIcon
Ts0 f79 819 515 26 26 0 12 #rect
Ts0 f79 @|UdProcessEndIcon #fIcon
Ts0 f93 actionTable 'out=in;
' #txt
Ts0 f93 actionCode 'import ch.ivy.addon.portalkit.util.TaskUtils;

TaskUtils.resetTask(in.task);' #txt
Ts0 f93 security system #txt
Ts0 f93 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Reset task</name>
    </language>
</elementInfo>
' #txt
Ts0 f93 184 506 112 44 -29 -8 #rect
Ts0 f93 @|StepIcon #fIcon
Ts0 f86 processCall 'Ivy Data Processes/SecurityService:findUsers(ch.ivyteam.ivy.application.IApplication)' #txt
Ts0 f86 requestActionDecl '<ch.ivyteam.ivy.application.IApplication application> param;' #txt
Ts0 f86 requestMappingAction 'param.application=in.application;
' #txt
Ts0 f86 responseActionDecl 'ch.ivy.addon.portalkit.component.SideStep.SideStepData out;
' #txt
Ts0 f86 responseMappingAction 'out=in;
out.usersToDelegate=result.users;
' #txt
Ts0 f86 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>SecurityService - Find Users</name>
    </language>
</elementInfo>
' #txt
Ts0 f86 464 146 160 44 -77 -8 #rect
Ts0 f86 @|CallSubIcon #fIcon
Ts0 f73 guid 16EE8CC091943071 #txt
Ts0 f73 method autoCompleteForUserDelegate(String) #txt
Ts0 f73 inParameterDecl '<String query> param;' #txt
Ts0 f73 inParameterMapAction 'out.queryAutoComplete=param.query;
' #txt
Ts0 f73 outParameterDecl '<java.util.List<ch.ivy.addon.portalkit.dto.UserDTO> usersToDelegate> result;' #txt
Ts0 f73 outActionCode 'import ch.ivy.addon.portalkit.util.UserUtils;

result.usersToDelegate = UserUtils.filterUsersDTO(in.usersToDelegate, in.queryAutoComplete);
' #txt
Ts0 f73 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>autoCompleteForUserDelegate(String)</name>
        <nameStyle>35,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ts0 f73 83 419 26 26 -52 12 #rect
Ts0 f73 @|UdMethodIcon #fIcon
Ts0 f80 actionTable 'out=in;
' #txt
Ts0 f80 actionCode 'import ch.ivy.addon.portalkit.util.SecurityMemberUtils;
import org.apache.commons.lang.StringUtils;

String newResponsibleName = "";

if (in.#selectedUser != null) {
	newResponsibleName = in.#selectedUser.#displayName;
	in.delegatedSecurityMember = SecurityMemberUtils.findISecurityMemberFromUserDTO(in.selectedUser);
} else if (in.#selectedRole != null) {
	newResponsibleName = in.#selectedRole.#displayName;
	in.delegatedSecurityMember = SecurityMemberUtils.findISecurityMemberFromRoleDTO(in.selectedRole);
}

String oldResponsibleName = in.task.getActivator() != null? in.task.getActivator().getDisplayName() : StringUtils.stripStart(in.task.getActivatorName(), "#");

in.delegateComment = ivy.cms.co("/ch.ivy.addon.portalkit.ui.jsf/taskDelegate/delegateComment", [in.task.getId(), oldResponsibleName, newResponsibleName]);' #txt
Ts0 f80 security system #txt
Ts0 f80 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Create note</name>
    </language>
</elementInfo>
' #txt
Ts0 f80 336 506 112 44 -32 -8 #rect
Ts0 f80 @|StepIcon #fIcon
Ts0 f87 actionTable 'out=in;
' #txt
Ts0 f87 actionCode 'in.disabledDelegateButton = true;
in.isUserDelegated = true;
in.delegatedSecurityMember = null;
in.application = in.task.getApplication();
in.selectedRole = null;
in.selectedUser = null;

' #txt
Ts0 f87 security system #txt
Ts0 f87 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Init data</name>
    </language>
</elementInfo>
' #txt
Ts0 f87 304 146 112 44 -21 -8 #rect
Ts0 f87 @|StepIcon #fIcon
Ts0 f106 339 643 26 26 0 12 #rect
Ts0 f106 @|UdProcessEndIcon #fIcon
Ts0 f107 actionTable 'out=in;
' #txt
Ts0 f107 actionCode 'out.delegatedSecurityMember = null;
out.disabledDelegateButton = true;
' #txt
Ts0 f107 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Change assignee type&#xD;
to delegate</name>
    </language>
</elementInfo>
' #txt
Ts0 f107 152 634 144 44 -54 -16 #rect
Ts0 f107 @|StepIcon #fIcon
Ts0 f71 guid 16EE8CC091941043 #txt
Ts0 f71 method delegateTask() #txt
Ts0 f71 inParameterDecl '<> param;' #txt
Ts0 f71 outParameterDecl '<> result;' #txt
Ts0 f71 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>delegateTask()</name>
    </language>
</elementInfo>
' #txt
Ts0 f71 83 515 26 26 -41 15 #rect
Ts0 f71 @|UdMethodIcon #fIcon
Ts0 f74 guid 16EE8CC09192B441 #txt
Ts0 f74 method autoCompleteForRoleDelegate(String) #txt
Ts0 f74 inParameterDecl '<String query> param;' #txt
Ts0 f74 inParameterMapAction 'out.queryAutoComplete=param.query;
' #txt
Ts0 f74 outParameterDecl '<java.util.List<ch.ivy.addon.portalkit.dto.RoleDTO> rolesToDelegate> result;' #txt
Ts0 f74 outActionCode 'import ch.ivy.addon.portalkit.util.RoleUtils;

result.rolesToDelegate = RoleUtils.filterRoleDTO(in.rolesToDelegate, in.queryAutoComplete);' #txt
Ts0 f74 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>autoCompleteForRoleDelegate(String)</name>
        <nameStyle>35,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ts0 f74 83 323 26 26 -56 13 #rect
Ts0 f74 @|UdMethodIcon #fIcon
Ts0 f81 processCall 'Functional Processes/CalculateTaskDelegate:call(java.util.List<ch.ivy.addon.portalkit.dto.RoleDTO>,java.util.List<ch.ivy.addon.portalkit.dto.UserDTO>,ch.ivy.addon.portalkit.dto.SecurityMemberDTO,ch.ivyteam.ivy.workflow.ITask)' #txt
Ts0 f81 requestActionDecl '<java.util.List<ch.ivy.addon.portalkit.dto.RoleDTO> roles,java.util.List<ch.ivy.addon.portalkit.dto.UserDTO> users,ch.ivy.addon.portalkit.dto.SecurityMemberDTO currentUser,ch.ivyteam.ivy.workflow.ITask task> param;' #txt
Ts0 f81 requestMappingAction 'param.roles=in.rolesToDelegate;
param.users=in.usersToDelegate;
param.currentUser=ch.ivy.addon.portalkit.util.SecurityMemberUtils.getCurrentSessionUserAsSecurityMemberDTO();
param.task=in.task;
' #txt
Ts0 f81 responseActionDecl 'ch.ivy.addon.portalkit.component.SideStep.SideStepData out;
' #txt
Ts0 f81 responseMappingAction 'out=in;
out.rolesToDelegate=result.roles;
out.usersToDelegate=result.users;
' #txt
Ts0 f81 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>CalculateTaskDelegate</name>
    </language>
</elementInfo>
' #txt
Ts0 f81 928 146 144 44 -64 -8 #rect
Ts0 f81 @|CallSubIcon #fIcon
Ts0 f92 actionTable 'out=in;
' #txt
Ts0 f92 actionCode 'import ch.ivy.addon.portalkit.util.TaskUtils;

TaskUtils.delegateTask(in.task, in.delegatedSecurityMember);' #txt
Ts0 f92 security system #txt
Ts0 f92 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Delegate task</name>
    </language>
</elementInfo>
' #txt
Ts0 f92 488 506 112 44 -38 -8 #rect
Ts0 f92 @|StepIcon #fIcon
Ts0 f76 actionTable 'out=in;
' #txt
Ts0 f76 actionCode 'in.canDelegateTask = false;' #txt
Ts0 f76 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>can not delegate task</name>
    </language>
</elementInfo>
' #txt
Ts0 f76 488 242 128 44 -58 -8 #rect
Ts0 f76 @|StepIcon #fIcon
Ts0 f88 208 152 32 32 0 16 #rect
Ts0 f88 @|AlternativeIcon #fIcon
Ts0 f82 guid 16EE8CC0919DC8DC #txt
Ts0 f82 method initDataToDelegate(ch.ivyteam.ivy.workflow.ITask) #txt
Ts0 f82 inParameterDecl '<ch.ivyteam.ivy.workflow.ITask task> param;' #txt
Ts0 f82 inParameterMapAction 'out.task=param.task;
' #txt
Ts0 f82 outParameterDecl '<> result;' #txt
Ts0 f82 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>initDataToDelegate(ITask)</name>
    </language>
</elementInfo>
' #txt
Ts0 f82 83 155 26 26 -72 15 #rect
Ts0 f82 @|UdMethodIcon #fIcon
Ts0 f89 actionTable 'out=in;
' #txt
Ts0 f89 actionCode 'in.canDelegateTask = !(in.rolesToDelegate.isEmpty() && in.usersToDelegate.isEmpty());

if (in.canDelegateTask) {
	if (in.rolesToDelegate.isEmpty()) {
		in.isUserDelegated = true;
	}
	if (in.usersToDelegate.isEmpty()) {
		in.isUserDelegated = false;
	}
}
' #txt
Ts0 f89 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Check if can&#xD;
delegate task</name>
    </language>
</elementInfo>
' #txt
Ts0 f89 1168 146 112 44 -37 -16 #rect
Ts0 f89 @|StepIcon #fIcon
Ts0 f69 339 419 26 26 0 12 #rect
Ts0 f69 @|UdProcessEndIcon #fIcon
Ts0 f77 actionTable 'out=in;
' #txt
Ts0 f77 actionCode 'in.task.getCase().getBusinessCase().createNote(ivy.session, in.delegateComment);' #txt
Ts0 f77 security system #txt
Ts0 f77 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Add note</name>
    </language>
</elementInfo>
' #txt
Ts0 f77 648 506 112 44 -24 -8 #rect
Ts0 f77 @|StepIcon #fIcon
Ts0 f100 expr out #txt
Ts0 f100 448 528 488 528 #arcP
Ts0 f103 expr out #txt
Ts0 f103 600 528 648 528 #arcP
Ts0 f110 expr out #txt
Ts0 f110 109 656 152 656 #arcP
Ts0 f99 expr out #txt
Ts0 f99 296 528 336 528 #arcP
Ts0 f102 expr out #txt
Ts0 f102 109 528 184 528 #arcP
Ts0 f94 expr out #txt
Ts0 f94 109 168 208 168 #arcP
Ts0 f109 expr out #txt
Ts0 f109 296 656 339 656 #arcP
Ts0 f105 expr out #txt
Ts0 f105 760 528 819 528 #arcP
Ts0 f104 expr out #txt
Ts0 f104 109 432 339 432 #arcP
Ts0 f97 expr out #txt
Ts0 f97 1280 168 1339 168 #arcP
Ts0 f98 expr out #txt
Ts0 f98 1072 168 1168 168 #arcP
Ts0 f101 expr out #txt
Ts0 f101 109 336 339 336 #arcP
Ts0 f101 0 0.61063400144005 0 0 #arcLabel
Ts0 f95 616 264 1352 181 #arcP
Ts0 f95 1 1352 264 #addKink
Ts0 f95 0 0.7300842237076132 0 0 #arcLabel
Ts0 f91 expr out #txt
Ts0 f91 416 168 464 168 #arcP
Ts0 f3 expr in #txt
Ts0 f3 outCond ch.ivy.addon.portalkit.util.TaskUtils.isTaskCurrentOpeningTask(in.task) #txt
Ts0 f3 224 184 488 264 #arcP
Ts0 f3 1 224 264 #addKink
Ts0 f4 expr in #txt
Ts0 f4 240 168 304 168 #arcP
Ts0 f5 processCall 'Ivy Data Processes/SecurityService:findRolesDTO(ch.ivyteam.ivy.application.IApplication)' #txt
Ts0 f5 requestActionDecl '<ch.ivyteam.ivy.application.IApplication application> param;' #txt
Ts0 f5 requestMappingAction 'param.application=in.application;
' #txt
Ts0 f5 responseMappingAction 'out=in;
out.rolesToDelegate=result.roles;
' #txt
Ts0 f5 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>SecurityService - find roles</name>
    </language>
</elementInfo>
' #txt
Ts0 f5 704 146 160 44 -72 -8 #rect
Ts0 f5 @|CallSubIcon #fIcon
Ts0 f6 expr out #txt
Ts0 f6 624 168 704 168 #arcP
Ts0 f7 864 168 928 168 #arcP
>Proto Ts0 .type ch.ivy.addon.portalkit.component.TaskItemDelegate.TaskItemDelegateData #txt
>Proto Ts0 .processKind HTML_DIALOG #txt
>Proto Ts0 -8 -8 16 16 16 26 #rect
>Proto Ts0 '' #fIcon
Ts0 f0 mainOut f2 tail #connect
Ts0 f2 head f1 mainIn #connect
Ts0 f87 mainOut f91 tail #connect
Ts0 f91 head f86 mainIn #connect
Ts0 f81 mainOut f98 tail #connect
Ts0 f98 head f89 mainIn #connect
Ts0 f89 mainOut f97 tail #connect
Ts0 f97 head f90 mainIn #connect
Ts0 f82 mainOut f94 tail #connect
Ts0 f94 head f88 in #connect
Ts0 f76 mainOut f95 tail #connect
Ts0 f95 head f90 mainIn #connect
Ts0 f74 mainOut f101 tail #connect
Ts0 f101 head f66 mainIn #connect
Ts0 f73 mainOut f104 tail #connect
Ts0 f104 head f69 mainIn #connect
Ts0 f71 mainOut f102 tail #connect
Ts0 f102 head f93 mainIn #connect
Ts0 f93 mainOut f99 tail #connect
Ts0 f99 head f80 mainIn #connect
Ts0 f80 mainOut f100 tail #connect
Ts0 f100 head f92 mainIn #connect
Ts0 f92 mainOut f103 tail #connect
Ts0 f103 head f77 mainIn #connect
Ts0 f77 mainOut f105 tail #connect
Ts0 f105 head f79 mainIn #connect
Ts0 f107 mainOut f109 tail #connect
Ts0 f109 head f106 mainIn #connect
Ts0 f108 mainOut f110 tail #connect
Ts0 f110 head f107 mainIn #connect
Ts0 f88 out f3 tail #connect
Ts0 f3 head f76 mainIn #connect
Ts0 f88 out f4 tail #connect
Ts0 f4 head f87 mainIn #connect
Ts0 f86 mainOut f6 tail #connect
Ts0 f6 head f5 mainIn #connect
Ts0 f5 mainOut f7 tail #connect
Ts0 f7 head f81 mainIn #connect
