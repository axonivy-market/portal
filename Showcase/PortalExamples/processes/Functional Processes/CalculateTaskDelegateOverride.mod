[Ivy]
16F3229F28D3A455 7.5.0 #module
>Proto >Proto Collection #zClass
Ce0 CalculateTaskDelegate Big #zClass
Ce0 B #cInfo
Ce0 #process
Ce0 @TextInP .type .type #zField
Ce0 @TextInP .processKind .processKind #zField
Ce0 @AnnotationInP-0n ai ai #zField
Ce0 @MessageFlowInP-0n messageIn messageIn #zField
Ce0 @MessageFlowOutP-0n messageOut messageOut #zField
Ce0 @TextInP .xml .xml #zField
Ce0 @TextInP .responsibility .responsibility #zField
Ce0 @StartSub f0 '' #zField
Ce0 @InfoButton f7 '' #zField
Ce0 @GridStep f6 '' #zField
Ce0 @PushWFArc f1 '' #zField
Ce0 @AnnotationArc f2 '' #zField
Ce0 @EndSub f3 '' #zField
Ce0 @PushWFArc f4 '' #zField
>Proto Ce0 Ce0 CalculateTaskDelegate #zField
Ce0 f0 inParamDecl '<java.util.List<ch.ivy.addon.portalkit.dto.RoleDTO> roles,java.util.List<ch.ivy.addon.portalkit.dto.UserDTO> users,ch.ivy.addon.portalkit.dto.SecurityMemberDTO currentUser,ch.ivyteam.ivy.workflow.ITask task> param;' #txt
Ce0 f0 inParamTable 'out.currentUser=param.currentUser;
out.roles=param.roles;
out.task=param.task;
out.users=param.users;
' #txt
Ce0 f0 outParamDecl '<java.util.List<ch.ivy.addon.portalkit.dto.UserDTO> users,java.util.List<ch.ivy.addon.portalkit.dto.RoleDTO> roles> result;' #txt
Ce0 f0 outParamTable 'result.users=in.users;
result.roles=in.roles;
' #txt
Ce0 f0 callSignature call(java.util.List<ch.ivy.addon.portalkit.dto.RoleDTO>,java.util.List<ch.ivy.addon.portalkit.dto.UserDTO>,ch.ivy.addon.portalkit.dto.SecurityMemberDTO,ch.ivyteam.ivy.workflow.ITask) #txt
Ce0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name></name>
    </language>
</elementInfo>
' #txt
Ce0 f0 51 83 26 26 -51 21 #rect
Ce0 f0 @|StartSubIcon #fIcon
Ce0 f7 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Return your own list of users &amp; roles to delegate task to

if ("admin".equalsIgnoreCase(in.currentUser.getDisplayName()) &amp;&amp; "System task".equalsIgnoreCase(in.task.getName())) {
  in.users = new ArrayList();
  in.roles = new ArrayList();
}</name>
        <nameStyle>238,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ce0 f7 120 151 688 108 -335 -48 #rect
Ce0 f7 @|IBIcon #fIcon
Ce0 f6 actionTable 'out=in;
' #txt
Ce0 f6 actionCode 'import java.util.ArrayList;
if ("#admin".equalsIgnoreCase(in.currentUser.getMemberName())) {
	if ("Maternity Leave Request".equalsIgnoreCase(in.task.getName())) {
		in.users = new ArrayList();
	}
} else {
	in.users = new ArrayList();
	in.roles = new ArrayList();
}' #txt
Ce0 f6 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Calculate</name>
    </language>
</elementInfo>
' #txt
Ce0 f6 160 74 112 44 -26 -8 #rect
Ce0 f6 @|StepIcon #fIcon
Ce0 f1 expr out #txt
Ce0 f1 77 96 160 96 #arcP
Ce0 f2 464 151 216 118 #arcP
Ce0 f3 337 81 30 30 0 15 #rect
Ce0 f3 @|EndSubIcon #fIcon
Ce0 f4 expr out #txt
Ce0 f4 272 96 337 96 #arcP
>Proto Ce0 .type _ch.ivyteam.ivy.project.portal.examples.CalculateTaskDelegateOverrideData #txt
>Proto Ce0 .processKind CALLABLE_SUB #txt
>Proto Ce0 0 0 32 24 18 0 #rect
>Proto Ce0 @|BIcon #fIcon
Ce0 f0 mainOut f1 tail #connect
Ce0 f1 head f6 mainIn #connect
Ce0 f7 ao f2 tail #connect
Ce0 f2 head f6 @CG|ai #connect
Ce0 f6 mainOut f4 tail #connect
Ce0 f4 head f3 mainIn #connect
