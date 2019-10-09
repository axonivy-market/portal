[Ivy]
16BF516D50A96350 3.28 #module
>Proto >Proto Collection #zClass
Ts0 SideStepProcess Big #zClass
Ts0 RD #cInfo
Ts0 #process
Ts0 @TextInP .type .type #zField
Ts0 @TextInP .processKind .processKind #zField
Ts0 @AnnotationInP-0n ai ai #zField
Ts0 @MessageFlowInP-0n messageIn messageIn #zField
Ts0 @MessageFlowOutP-0n messageOut messageOut #zField
Ts0 @TextInP .xml .xml #zField
Ts0 @TextInP .responsibility .responsibility #zField
Ts0 @UdProcessEnd f94 '' #zField
Ts0 @UdProcessEnd f106 '' #zField
Ts0 @UdEvent f36 '' #zField
Ts0 @UdProcessEnd f17 '' #zField
Ts0 @UdProcessEnd f62 '' #zField
Ts0 @UdProcessEnd f11 '' #zField
Ts0 @UdProcessEnd f70 '' #zField
Ts0 @CallSub f103 '' #zField
Ts0 @UdMethod f13 '' #zField
Ts0 @UdProcessEnd f1 '' #zField
Ts0 @UdProcessEnd f3 '' #zField
Ts0 @UdMethod f12 '' #zField
Ts0 @GridStep f19 '' #zField
Ts0 @PushWFArc f8 '' #zField
Ts0 @GridStep f7 '' #zField
Ts0 @PushWFArc f104 '' #zField
Ts0 @CallSub f20 '' #zField
Ts0 @PushWFArc f23 '' #zField
Ts0 @PushWFArc f14 '' #zField
Ts0 @GridStep f10 '' #zField
Ts0 @PushWFArc f95 '' #zField
Ts0 @UdMethod f76 '' #zField
Ts0 @UdMethod f39 '' #zField
Ts0 @PushWFArc f52 '' #zField
Ts0 @PushWFArc f102 '' #zField
Ts0 @GridStep f24 '' #zField
Ts0 @PushWFArc f21 '' #zField
Ts0 @PushWFArc f5 '' #zField
Ts0 @GridStep f4 '' #zField
Ts0 @PushWFArc f25 '' #zField
Ts0 @PushWFArc f26 '' #zField
Ts0 @GridStep f15 '' #zField
Ts0 @PushWFArc f69 '' #zField
Ts0 @UdMethod f96 '' #zField
Ts0 @PushWFArc f111 '' #zField
Ts0 @PushWFArc f107 '' #zField
Ts0 @GridStep f105 '' #zField
Ts0 @PushWFArc f63 '' #zField
Ts0 @PushWFArc f61 '' #zField
Ts0 @GridStep f48 '' #zField
Ts0 @UdInit f0 '' #zField
Ts0 @UdMethod f6 '' #zField
Ts0 @UdProcessEnd f9 '' #zField
Ts0 @GridStep f16 '' #zField
Ts0 @PushWFArc f18 '' #zField
Ts0 @PushWFArc f22 '' #zField
Ts0 @PushWFArc f2 '' #zField
>Proto Ts0 Ts0 SideStepProcess #zField
Ts0 f94 339 563 26 26 0 12 #rect
Ts0 f94 @|UdProcessEndIcon #fIcon
Ts0 f106 819 467 26 26 0 12 #rect
Ts0 f106 @|UdProcessEndIcon #fIcon
Ts0 f36 guid 16816B4C738DA4B0 #txt
Ts0 f36 actionTable 'out=in;
' #txt
Ts0 f36 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>changeAssignType</name>
    </language>
</elementInfo>
' #txt
Ts0 f36 83 371 26 26 -47 15 #rect
Ts0 f36 @|UdEventIcon #fIcon
Ts0 f17 -106 1334 20 20 13 0 #rect
Ts0 f17 @|UdProcessEndIcon #fIcon
Ts0 f62 339 275 26 26 0 12 #rect
Ts0 f62 @|UdProcessEndIcon #fIcon
Ts0 f11 819 755 26 26 0 12 #rect
Ts0 f11 @|UdProcessEndIcon #fIcon
Ts0 f70 339 659 26 26 0 12 #rect
Ts0 f70 @|UdProcessEndIcon #fIcon
Ts0 f103 processCall 'Functional Processes/CalculateTaskDelegate:call(List<ch.ivyteam.ivy.security.IRole>,List<ch.ivyteam.ivy.security.IUser>,ch.ivyteam.ivy.security.ISecurityMember,ch.ivyteam.ivy.workflow.ITask)' #txt
Ts0 f103 requestActionDecl '<java.util.List<ch.ivyteam.ivy.security.IRole> roles,java.util.List<ch.ivyteam.ivy.security.IUser> users,ch.ivyteam.ivy.security.ISecurityMember currentUser,ch.ivyteam.ivy.workflow.ITask task> param;
' #txt
Ts0 f103 requestMappingAction 'param.roles=in.rolesToDelegate;
param.users=in.usersToDelegate;
param.currentUser=ivy.session.getSessionUser();
param.task=in.task;
' #txt
Ts0 f103 responseActionDecl 'ch.ivy.addon.portalkit.component.SideStep.SideStepData out;
' #txt
Ts0 f103 responseMappingAction 'out=in;
out.rolesToDelegate=result.roles;
out.usersToDelegate=result.users;
' #txt
Ts0 f103 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>CalculateTaskDelegate</name>
    </language>
</elementInfo>
' #txt
Ts0 f103 472 458 144 44 -64 -8 #rect
Ts0 f103 @|CallSubIcon #fIcon
Ts0 f13 guid 16817CBC68AF60CA #txt
Ts0 f13 method delegateTask() #txt
Ts0 f13 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<> param = methodEvent.getInputArguments();
' #txt
Ts0 f13 outParameterDecl '<> result;
' #txt
Ts0 f13 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>delegateTask()</name>
    </language>
</elementInfo>
' #txt
Ts0 f13 83 755 26 26 -41 15 #rect
Ts0 f13 @|UdMethodIcon #fIcon
Ts0 f1 339 83 26 26 0 12 #rect
Ts0 f1 @|UdProcessEndIcon #fIcon
Ts0 f3 339 371 26 26 0 12 #rect
Ts0 f3 @|UdProcessEndIcon #fIcon
Ts0 f12 guid 168179A956B8EBA7 #txt
Ts0 f12 method initDataToDelegate(ch.ivyteam.ivy.workflow.ITask) #txt
Ts0 f12 inParameterDecl '<ch.ivyteam.ivy.workflow.ITask task> param;' #txt
Ts0 f12 inParameterMapAction 'out.task=param.task;
' #txt
Ts0 f12 outParameterDecl '<> result;' #txt
Ts0 f12 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>initDataToDelegate(ITask)</name>
    </language>
</elementInfo>
' #txt
Ts0 f12 83 467 26 26 -72 15 #rect
Ts0 f12 @|UdMethodIcon #fIcon
Ts0 f19 actionTable 'out=in;
' #txt
Ts0 f19 actionCode 'import ch.ivy.addon.portalkit.util.TaskUtils;

TaskUtils.delegateTask(in.task, in.delegatedSecurityMember);' #txt
Ts0 f19 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Delegate task</name>
    </language>
</elementInfo>
' #txt
Ts0 f19 488 746 112 44 -38 -8 #rect
Ts0 f19 @|StepIcon #fIcon
Ts0 f8 expr out #txt
Ts0 f8 448 768 488 768 #arcP
Ts0 f7 actionTable 'out=in;
' #txt
Ts0 f7 actionCode 'import org.apache.commons.lang.StringUtils;

String newResponsibleName = in.delegatedSecurityMember.getDisplayName();
String oldResponsibleName = in.task.getActivator() != null? in.task.getActivator().getDisplayName() : StringUtils.stripStart(in.task.getActivatorName(), "#");

in.delegateComment = ivy.cms.co("/ch.ivy.addon.portalkit.ui.jsf/taskDelegate/delegateComment", [in.task.getId(), oldResponsibleName, newResponsibleName]);' #txt
Ts0 f7 security system #txt
Ts0 f7 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Create note</name>
    </language>
</elementInfo>
' #txt
Ts0 f7 336 746 112 44 -32 -8 #rect
Ts0 f7 @|StepIcon #fIcon
Ts0 f104 expr out #txt
Ts0 f104 440 480 472 480 #arcP
Ts0 f20 processCall 'Ivy Data Processes/SecurityService:findSecurityMembers(ch.ivyteam.ivy.application.IApplication)' #txt
Ts0 f20 requestActionDecl '<ch.ivyteam.ivy.application.IApplication application> param;
' #txt
Ts0 f20 requestMappingAction 'param.application=in.application;
' #txt
Ts0 f20 responseActionDecl 'ch.ivy.addon.portalkit.component.SideStep.SideStepData out;
' #txt
Ts0 f20 responseMappingAction 'out=in;
out.errors=result.errors;
out.rolesToDelegate=result.roles;
out.usersToDelegate=result.users;
' #txt
Ts0 f20 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>SecurityService</name>
    </language>
</elementInfo>
' #txt
Ts0 f20 328 458 112 44 -41 -8 #rect
Ts0 f20 @|CallSubIcon #fIcon
Ts0 f23 expr out #txt
Ts0 f23 296 768 336 768 #arcP
Ts0 f14 expr out #txt
Ts0 f14 109 768 184 768 #arcP
Ts0 f10 actionTable 'out=in;
' #txt
Ts0 f10 actionCode 'import ch.ivy.addon.portalkit.util.TaskUtils;

TaskUtils.resetTask(in.task);' #txt
Ts0 f10 security system #txt
Ts0 f10 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Reset task</name>
    </language>
</elementInfo>
' #txt
Ts0 f10 184 746 112 44 -29 -8 #rect
Ts0 f10 @|StepIcon #fIcon
Ts0 f95 expr out #txt
Ts0 f95 109 672 339 672 #arcP
Ts0 f76 guid 15F8F5A744248DE3 #txt
Ts0 f76 method autoCompleteForUserDelegate(String) #txt
Ts0 f76 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<java.lang.String query> param = methodEvent.getInputArguments();
' #txt
Ts0 f76 inParameterMapAction 'out.queryAutoComplete=param.query;
' #txt
Ts0 f76 outParameterDecl '<java.util.List<ch.ivyteam.ivy.security.IUser> usersToDelegate> result;
' #txt
Ts0 f76 outActionCode 'import ch.ivy.addon.portalkit.util.UserUtils;

result.usersToDelegate = UserUtils.filterUsers(in.usersToDelegate, in.queryAutoComplete);
' #txt
Ts0 f76 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>autoCompleteForUserDelegate(String)</name>
        <nameStyle>35,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ts0 f76 83 659 26 26 -52 12 #rect
Ts0 f76 @|UdMethodIcon #fIcon
Ts0 f39 guid 16816B96E5CA66CF #txt
Ts0 f39 method parkTask(ch.ivyteam.ivy.workflow.ITask) #txt
Ts0 f39 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<ch.ivyteam.ivy.workflow.ITask task> param = methodEvent.getInputArguments();
' #txt
Ts0 f39 inParameterMapAction 'out.task=param.task;
' #txt
Ts0 f39 outParameterDecl '<> result;
' #txt
Ts0 f39 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>parkTask(ITask)</name>
    </language>
</elementInfo>
' #txt
Ts0 f39 83 275 26 26 -44 15 #rect
Ts0 f39 @|UdMethodIcon #fIcon
Ts0 f52 expr out #txt
Ts0 f52 280 480 328 480 #arcP
Ts0 f102 expr out #txt
Ts0 f102 109 480 168 480 #arcP
Ts0 f24 actionTable 'out=in;
' #txt
Ts0 f24 actionCode 'in.disabledDelegateButton = true;
in.isUserDelegated = true;
in.delegatedSecurityMember = null;
in.application = in.task.getApplication();' #txt
Ts0 f24 security system #txt
Ts0 f24 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Init data</name>
    </language>
</elementInfo>
' #txt
Ts0 f24 168 458 112 44 -21 -8 #rect
Ts0 f24 @|StepIcon #fIcon
Ts0 f21 expr out #txt
Ts0 f21 109 384 152 384 #arcP
Ts0 f5 expr out #txt
Ts0 f5 296 384 339 384 #arcP
Ts0 f4 actionTable 'out=in;
' #txt
Ts0 f4 actionCode 'out.delegatedSecurityMember = null;
out.disabledDelegateButton = true;
' #txt
Ts0 f4 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Change assignee type&#xD;
to delegate</name>
    </language>
</elementInfo>
' #txt
Ts0 f4 152 362 144 44 -54 -16 #rect
Ts0 f4 @|StepIcon #fIcon
Ts0 f25 expr out #txt
Ts0 f25 760 768 819 768 #arcP
Ts0 f26 expr out #txt
Ts0 f26 600 768 648 768 #arcP
Ts0 f15 actionTable 'out=in;
' #txt
Ts0 f15 actionCode 'in.task.getCase().getBusinessCase().createNote(ivy.session, in.delegateComment);' #txt
Ts0 f15 security system #txt
Ts0 f15 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Add note</name>
    </language>
</elementInfo>
' #txt
Ts0 f15 648 746 112 44 -24 -8 #rect
Ts0 f15 @|StepIcon #fIcon
Ts0 f69 expr out #txt
Ts0 f69 109 576 339 576 #arcP
Ts0 f69 0 0.61063400144005 0 0 #arcLabel
Ts0 f96 guid 15F940442EBC4F26 #txt
Ts0 f96 method autoCompleteForRoleDelegate(String) #txt
Ts0 f96 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<java.lang.String query> param = methodEvent.getInputArguments();
' #txt
Ts0 f96 inParameterMapAction 'out.queryAutoComplete=param.query;
' #txt
Ts0 f96 outParameterDecl '<java.util.List<ch.ivyteam.ivy.security.IRole> rolesToDelegate> result;
' #txt
Ts0 f96 outActionCode 'import ch.ivy.addon.portalkit.util.RoleUtils;

result.rolesToDelegate = RoleUtils.filterRoles(in.rolesToDelegate, in.queryAutoComplete);' #txt
Ts0 f96 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>autoCompleteForRoleDelegate(String)</name>
        <nameStyle>35,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ts0 f96 83 563 26 26 -56 13 #rect
Ts0 f96 @|UdMethodIcon #fIcon
Ts0 f111 expr out #txt
Ts0 f111 760 480 819 480 #arcP
Ts0 f107 expr out #txt
Ts0 f107 616 480 648 480 #arcP
Ts0 f105 actionTable 'out=in;
' #txt
Ts0 f105 actionCode 'in.canDelegateTask = !(in.rolesToDelegate.isEmpty() && in.usersToDelegate.isEmpty());

if (in.canDelegateTask) {
	if (in.rolesToDelegate.isEmpty()) {
		in.isUserDelegated = true;
	}
	if (in.usersToDelegate.isEmpty()) {
		in.isUserDelegated = false;
	}
}
' #txt
Ts0 f105 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Check if can&#xD;
delegate task</name>
    </language>
</elementInfo>
' #txt
Ts0 f105 648 458 112 44 -37 -16 #rect
Ts0 f105 @|StepIcon #fIcon
Ts0 f63 expr out #txt
Ts0 f63 280 288 339 288 #arcP
Ts0 f61 expr out #txt
Ts0 f61 109 288 168 288 #arcP
Ts0 f48 actionTable 'out=in;
' #txt
Ts0 f48 actionCode 'import ch.ivy.addon.portalkit.util.TaskUtils;

TaskUtils.parkTask(in.task);' #txt
Ts0 f48 security system #txt
Ts0 f48 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Park task</name>
    </language>
</elementInfo>
' #txt
Ts0 f48 168 266 112 44 -25 -8 #rect
Ts0 f48 @|StepIcon #fIcon
Ts0 f0 guid 16816B3D5FE7A2CB #txt
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
Ts0 f0 83 83 26 26 -16 15 #rect
Ts0 f0 @|UdInitIcon #fIcon
Ts0 f6 guid 16BF8B07EB1F1B35 #txt
Ts0 f6 method resetTask(ch.ivyteam.ivy.workflow.ITask) #txt
Ts0 f6 inParameterDecl '<ch.ivyteam.ivy.workflow.ITask task> param;' #txt
Ts0 f6 inParameterMapAction 'out.task=param.task;
' #txt
Ts0 f6 outParameterDecl '<> result;' #txt
Ts0 f6 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>resetTask(ITask)</name>
    </language>
</elementInfo>
' #txt
Ts0 f6 83 179 26 26 -46 15 #rect
Ts0 f6 @|UdMethodIcon #fIcon
Ts0 f9 339 179 26 26 0 12 #rect
Ts0 f9 @|UdProcessEndIcon #fIcon
Ts0 f16 actionTable 'out=in;
' #txt
Ts0 f16 actionCode 'import ch.ivy.addon.portalkit.util.TaskUtils;

TaskUtils.resetTask(in.task);' #txt
Ts0 f16 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Reset Task</name>
    </language>
</elementInfo>
' #txt
Ts0 f16 168 170 112 44 -31 -8 #rect
Ts0 f16 @|StepIcon #fIcon
Ts0 f18 expr out #txt
Ts0 f18 109 192 168 192 #arcP
Ts0 f22 expr out #txt
Ts0 f22 280 192 339 192 #arcP
Ts0 f2 expr out #txt
Ts0 f2 109 96 339 96 #arcP
>Proto Ts0 .type ch.ivy.addon.portalkit.component.SideStep.SideStepData #txt
>Proto Ts0 .processKind HTML_DIALOG #txt
>Proto Ts0 -8 -8 16 16 16 26 #rect
>Proto Ts0 '' #fIcon
Ts0 f96 mainOut f69 tail #connect
Ts0 f69 head f94 mainIn #connect
Ts0 f76 mainOut f95 tail #connect
Ts0 f95 head f70 mainIn #connect
Ts0 f4 mainOut f5 tail #connect
Ts0 f5 head f3 mainIn #connect
Ts0 f36 mainOut f21 tail #connect
Ts0 f21 head f4 mainIn #connect
Ts0 f39 mainOut f61 tail #connect
Ts0 f61 head f48 mainIn #connect
Ts0 f48 mainOut f63 tail #connect
Ts0 f63 head f62 mainIn #connect
Ts0 f12 mainOut f102 tail #connect
Ts0 f102 head f24 mainIn #connect
Ts0 f24 mainOut f52 tail #connect
Ts0 f52 head f20 mainIn #connect
Ts0 f20 mainOut f104 tail #connect
Ts0 f104 head f103 mainIn #connect
Ts0 f103 mainOut f107 tail #connect
Ts0 f107 head f105 mainIn #connect
Ts0 f105 mainOut f111 tail #connect
Ts0 f111 head f106 mainIn #connect
Ts0 f13 mainOut f14 tail #connect
Ts0 f14 head f10 mainIn #connect
Ts0 f10 mainOut f23 tail #connect
Ts0 f23 head f7 mainIn #connect
Ts0 f7 mainOut f8 tail #connect
Ts0 f8 head f19 mainIn #connect
Ts0 f19 mainOut f26 tail #connect
Ts0 f26 head f15 mainIn #connect
Ts0 f15 mainOut f25 tail #connect
Ts0 f25 head f11 mainIn #connect
Ts0 f6 mainOut f18 tail #connect
Ts0 f18 head f16 mainIn #connect
Ts0 f16 mainOut f22 tail #connect
Ts0 f22 head f9 mainIn #connect
Ts0 f0 mainOut f2 tail #connect
Ts0 f2 head f1 mainIn #connect
