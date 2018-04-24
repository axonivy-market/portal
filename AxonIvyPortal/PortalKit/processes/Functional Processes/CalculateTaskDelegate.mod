[Ivy]
15B8089000CE1FF7 3.20 #module
>Proto >Proto Collection #zClass
Ce0 CalculateTaskDelegate Big #zClass
Ce0 B #cInfo
Ce0 #process
Ce0 @TextInP .resExport .resExport #zField
Ce0 @TextInP .type .type #zField
Ce0 @TextInP .processKind .processKind #zField
Ce0 @AnnotationInP-0n ai ai #zField
Ce0 @MessageFlowInP-0n messageIn messageIn #zField
Ce0 @MessageFlowOutP-0n messageOut messageOut #zField
Ce0 @TextInP .xml .xml #zField
Ce0 @TextInP .responsibility .responsibility #zField
Ce0 @StartSub f0 '' #zField
Ce0 @EndSub f1 '' #zField
Ce0 @GridStep f3 '' #zField
Ce0 @PushWFArc f4 '' #zField
Ce0 @PushWFArc f2 '' #zField
Ce0 @InfoButton f7 '' #zField
Ce0 @AnnotationArc f5 '' #zField
>Proto Ce0 Ce0 CalculateTaskDelegate #zField
Ce0 f0 inParamDecl '<ch.ivyteam.wf.processes.SecurityMemberData possibleTaskDelegates,ch.ivyteam.ivy.security.ISecurityMember currentUser,ch.ivy.addon.portalkit.bo.RemoteTask task> param;' #txt
Ce0 f0 inParamTable 'out.currentUser=param.currentUser;
out.roles=param.possibleTaskDelegates.ivyRoles;
out.task=param.task;
out.users=param.possibleTaskDelegates.ivyUsers;
' #txt
Ce0 f0 outParamDecl '<List<ch.ivy.ws.addon.IvyUser> users,List<ch.ivy.ws.addon.IvyRole> roles> result;
' #txt
Ce0 f0 outParamTable 'result.users=in.users;
result.roles=in.roles;
' #txt
Ce0 f0 actionDecl 'ch.ivy.add.portalkit.CaculateTaskDelegateData out;
' #txt
Ce0 f0 callSignature call(ch.ivyteam.wf.processes.SecurityMemberData,ch.ivyteam.ivy.security.ISecurityMember,ch.ivy.addon.portalkit.bo.RemoteTask) #txt
Ce0 f0 type ch.ivy.add.portalkit.CaculateTaskDelegateData #txt
Ce0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>call(SecurityMemberData,ISecurityMember,RemoteTask)</name>
    </language>
</elementInfo>
' #txt
Ce0 f0 51 83 26 26 14 0 #rect
Ce0 f0 @|StartSubIcon #fIcon
Ce0 f1 type ch.ivy.add.portalkit.CaculateTaskDelegateData #txt
Ce0 f1 51 339 26 26 14 0 #rect
Ce0 f1 @|EndSubIcon #fIcon
Ce0 f3 actionDecl 'ch.ivy.add.portalkit.CaculateTaskDelegateData out;
' #txt
Ce0 f3 actionTable 'out=in;
' #txt
Ce0 f3 type ch.ivy.add.portalkit.CaculateTaskDelegateData #txt
Ce0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Calculate</name>
        <nameStyle>9,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ce0 f3 46 196 36 24 5 19 #rect
Ce0 f3 @|StepIcon #fIcon
Ce0 f4 expr out #txt
Ce0 f4 64 109 64 196 #arcP
Ce0 f2 expr out #txt
Ce0 f2 64 220 64 339 #arcP
Ce0 f7 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Return your own list of users &amp; roles to delegate task to

if ("portaladmin".equalsIgnoreCase(in.currentUser.getDisplayName()) &amp;&amp; "System task".equalsIgnoreCase(in.task.getName())) {
  in.users = new ArrayList();
  in.roles = new ArrayList();
}</name>
        <nameStyle>244,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ce0 f7 144 151 720 108 -351 -48 #rect
Ce0 f7 @|IBIcon #fIcon
Ce0 f5 144 205 82 208 #arcP
>Proto Ce0 .type ch.ivy.add.portalkit.CaculateTaskDelegateData #txt
>Proto Ce0 .processKind CALLABLE_SUB #txt
>Proto Ce0 0 0 32 24 18 0 #rect
>Proto Ce0 @|BIcon #fIcon
Ce0 f0 mainOut f4 tail #connect
Ce0 f4 head f3 mainIn #connect
Ce0 f3 mainOut f2 tail #connect
Ce0 f2 head f1 mainIn #connect
Ce0 f7 ao f5 tail #connect
Ce0 f5 head f3 @CG|ai #connect
