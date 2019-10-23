[Ivy]
169B2A4D199FA6EA 7.5.0 #module
>Proto >Proto Collection #zClass
At0 AssignRolesGroupChat Big #zClass
At0 B #cInfo
At0 #process
At0 @TextInP .type .type #zField
At0 @TextInP .processKind .processKind #zField
At0 @AnnotationInP-0n ai ai #zField
At0 @MessageFlowInP-0n messageIn messageIn #zField
At0 @MessageFlowOutP-0n messageOut messageOut #zField
At0 @TextInP .xml .xml #zField
At0 @TextInP .responsibility .responsibility #zField
At0 @StartSub f0 '' #zField
At0 @EndSub f1 '' #zField
At0 @InfoButton f3 '' #zField
At0 @PushWFArc f2 '' #zField
At0 @AnnotationArc f4 '' #zField
>Proto At0 At0 AssignRolesGroupChat #zField
At0 f0 inParamDecl '<ch.ivyteam.ivy.workflow.ITask task> param;' #txt
At0 f0 inParamTable 'out.task=param.task;
' #txt
At0 f0 outParamDecl '<java.util.List<ch.ivyteam.ivy.security.IRole> roles> result;' #txt
At0 f0 outParamTable 'result.roles=in.roles;
' #txt
At0 f0 callSignature configureRolesForGroupChat(ch.ivyteam.ivy.workflow.ITask) #txt
At0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>configureRolesForGroupChat(ITask)</name>
    </language>
</elementInfo>
' #txt
At0 f0 33 25 30 30 5 17 #rect
At0 f0 @|StartSubIcon #fIcon
At0 f1 33 233 30 30 0 15 #rect
At0 f1 @|EndSubIcon #fIcon
At0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Use this callable to configure a predefined list of responsible roles for group chat feature. 
By default, this does nothing. Set value of in.roles to customize this role list.&#xD;
&#xD;
e.g. set predefined role HR for the process leave request.&#xD;
String processPath = in.task.getCase().getProcessStart().getUserFriendlyRequestPath();&#xD;
if (processPath.contains("Start Processes/CreateTestData/CategoriedLeaveRequest.ivp")) {&#xD;
  in.roles.add(ivy.wf.getSecurityContext().findRole("HR"));&#xD;
}</name>
        <nameStyle>5,5
475,5
</nameStyle>
    </language>
</elementInfo>
' #txt
At0 f3 72 82 512 140 -250 -64 #rect
At0 f3 @|IBIcon #fIcon
At0 f2 expr out #txt
At0 f2 48 55 48 233 #arcP
At0 f4 72 152 48 144 #arcP
>Proto At0 .type ch.ivy.addon.portal.generic.AssignRolesGroupChatData #txt
>Proto At0 .processKind CALLABLE_SUB #txt
>Proto At0 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language/>
</elementInfo>
' #txt
>Proto At0 0 0 32 24 18 0 #rect
>Proto At0 @|BIcon #fIcon
At0 f0 mainOut f2 tail #connect
At0 f2 head f1 mainIn #connect
At0 f3 ao f4 tail #connect
At0 f4 head f2 ai #connect
