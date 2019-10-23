[Ivy]
16B3FA08E0ADDCE6 7.5.0 #module
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
At0 @GridStep f5 '' #zField
At0 @PushWFArc f6 '' #zField
At0 @PushWFArc f2 '' #zField
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
At0 f0 57 17 30 30 5 17 #rect
At0 f0 @|StartSubIcon #fIcon
At0 f1 57 225 30 30 0 15 #rect
At0 f1 @|EndSubIcon #fIcon
At0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Use this callable to configure a predefined list of responsible roles for group chat feature. &#xD;
By default, this does nothing. Set value of in.roles to customize this role list.&#xD;
&#xD;
e.g. set predefined role HR for the process leave request.&#xD;
String processPath = in.task.getCase().getProcessStart().getUserFriendlyRequestPath();&#xD;
if (processPath.contains("Start Processes/CreateTestData/CategoriedLeaveRequest.ivp")) {&#xD;
  in.roles.add(ivy.wf.getSecurityContext().findRole("HR"));&#xD;
}</name>
    </language>
</elementInfo>
' #txt
At0 f3 136 74 512 140 -250 -64 #rect
At0 f3 @|IBIcon #fIcon
At0 f5 actionTable 'out=in;
' #txt
At0 f5 actionCode 'import java.util.ArrayList;
String processPath = in.task.getCase().getProcessStart().getUserFriendlyRequestPath();
if (processPath.contains("Start Processes/CreateTestData/CategoriedLeaveRequest.ivp")) {
	in.roles.add(ivy.wf.getSecurityContext().findRole("HR"));
}' #txt
At0 f5 security system #txt
At0 f5 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Configure</name>
    </language>
</elementInfo>
' #txt
At0 f5 16 106 112 44 -27 -8 #rect
At0 f5 @|StepIcon #fIcon
At0 f6 expr out #txt
At0 f6 72 47 72 106 #arcP
At0 f2 expr out #txt
At0 f2 72 150 72 225 #arcP
>Proto At0 .type _ch.ivyteam.ivy.project.portal.examples.AssignRolesGroupChatOverrideData #txt
>Proto At0 .processKind CALLABLE_SUB #txt
>Proto At0 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language/>
</elementInfo>
' #txt
>Proto At0 0 0 32 24 18 0 #rect
>Proto At0 @|BIcon #fIcon
At0 f0 mainOut f6 tail #connect
At0 f6 head f5 mainIn #connect
At0 f5 mainOut f2 tail #connect
At0 f2 head f1 mainIn #connect
