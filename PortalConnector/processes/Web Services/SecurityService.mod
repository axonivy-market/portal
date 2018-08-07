[Ivy]
[>Created: Wed Feb 03 16:49:00 ICT 2016]
138056017E3F98C2 3.18 #module
>Proto >Proto Collection #zClass
Se0 SecurityService Big #zClass
Se0 WS #cInfo
Se0 #process
Se0 @TextInP .webServiceName .webServiceName #zField
Se0 @TextInP .implementationClassName .implementationClassName #zField
Se0 @TextInP .authenticationType .authenticationType #zField
Se0 @TextInP .resExport .resExport #zField
Se0 @TextInP .type .type #zField
Se0 @TextInP .processKind .processKind #zField
Se0 @AnnotationInP-0n ai ai #zField
Se0 @TextInP .xml .xml #zField
Se0 @TextInP .responsibility .responsibility #zField
Se0 @GridStep f0 '' #zField
Se0 @GridStep f5 '' #zField
Se0 @GridStep f8 '' #zField
Se0 @StartWS f1 '' #zField
Se0 @PushWFArc f4 '' #zField
Se0 @StartWS f6 '' #zField
Se0 @PushWFArc f10 '' #zField
Se0 @StartWS f11 '' #zField
Se0 @PushWFArc f13 '' #zField
Se0 @StartWS f14 '' #zField
Se0 @GridStep f17 '' #zField
Se0 @GridStep f19 '' #zField
Se0 @PushWFArc f20 '' #zField
Se0 @PushWFArc f18 '' #zField
Se0 @CallSub f59 '' #zField
Se0 @Alternative f58 '' #zField
Se0 @EndWS f35 '' #zField
Se0 @PushWFArc f60 '' #zField
Se0 @PushWFArc f61 '' #zField
Se0 @PushWFArc f21 '' #zField
Se0 @PushWFArc f7 '' #zField
Se0 @PushWFArc f2 '' #zField
Se0 @PushWFArc f12 '' #zField
>Proto Se0 Se0 SecurityService #zField
Se0 f0 actionDecl 'ch.ivy.ws.addon.SecurityServiceData out;
' #txt
Se0 f0 actionTable 'out=in;
' #txt
Se0 f0 actionCode 'import ch.ivy.ws.addon.bo.SecurityServiceResult;
import ch.ivy.ws.addon.WsServiceFactory;
import ch.ivy.ws.addon.WSException;

try{
SecurityServiceResult ssResult = WsServiceFactory.getSecurityService().findAllUsers(in.applications);
in.ivyUsers = ssResult.ivyUsers;
in.errors = ssResult.getErrors();

}catch(WSException e){
	in.errors.add(e);
}' #txt
Se0 f0 type ch.ivy.ws.addon.SecurityServiceData #txt
Se0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>find all
users</name>
        <nameStyle>14,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Se0 f0 30 116 36 24 20 -2 #rect
Se0 f0 @|StepIcon #fIcon
Se0 f5 actionDecl 'ch.ivy.ws.addon.SecurityServiceData out;
' #txt
Se0 f5 actionTable 'out=in;
' #txt
Se0 f5 actionCode 'import ch.ivy.ws.addon.bo.SecurityServiceResult;
import ch.ivy.ws.addon.WsServiceFactory;
import ch.ivy.ws.addon.WSException;

try{
	SecurityServiceResult ssResult = WsServiceFactory.getSecurityService().findAllRoles(in.applications);
	in.ivyRoles  = ssResult.getIvyRoles();
	in.errors = ssResult.getErrors();
	
}catch(WSException e){
	in.errors.add(e);
}' #txt
Se0 f5 type ch.ivy.ws.addon.SecurityServiceData #txt
Se0 f5 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>find all
roles</name>
        <nameStyle>14,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Se0 f5 182 116 36 24 20 -2 #rect
Se0 f5 @|StepIcon #fIcon
Se0 f8 actionDecl 'ch.ivy.ws.addon.SecurityServiceData out;
' #txt
Se0 f8 actionTable 'out=in;
' #txt
Se0 f8 actionCode 'import ch.ivy.ws.addon.bo.SecurityServiceResult;
import ch.ivy.ws.addon.WsServiceFactory;
import ch.ivy.ws.addon.WSException;

try{
	SecurityServiceResult ssResult = WsServiceFactory.getSecurityService().findUsersByRoleId(in.application, in.ivyRole.id);
	in.ivyUsers = ssResult.ivyUsers;
	in.errors = ssResult.getErrors();
}catch(WSException e){
	in.errors.add(e);
}' #txt
Se0 f8 type ch.ivy.ws.addon.SecurityServiceData #txt
Se0 f8 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>find users
by role id</name>
        <nameStyle>21,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Se0 f8 342 116 36 24 20 -2 #rect
Se0 f8 @|StepIcon #fIcon
Se0 f1 inParamDecl '<List<java.lang.String> apps> param;' #txt
Se0 f1 inParamTable 'out.applications=param.apps;
' #txt
Se0 f1 outParamDecl '<List<ch.ivy.ws.addon.WSException> errors,List<ch.ivy.ws.addon.types.IvyUser> users> result;
' #txt
Se0 f1 outParamTable 'result.errors=in.errors;
result.users=in.ivyUsers;
' #txt
Se0 f1 actionDecl 'ch.ivy.ws.addon.SecurityServiceData out;
' #txt
Se0 f1 callSignature findAllUsers(List<String>) #txt
Se0 f1 useUserDefinedException false #txt
Se0 f1 taskData '#
#Wed Sep 10 21:22:25 CEST 2014
TaskTriggered.PRI=2
' #txt
Se0 f1 caseData '#
#Wed Sep 10 21:22:25 CEST 2014
businessCalendarName=
businessCreator.user=
businessMilestone.timestamp=
businessObject.code=
businessObject.docDb.code=
businessObject.folder.id=
businessObject.name=
businessPriority=
businessStart.timestamp=
case.description=
case.name=
correspondent.id=
mainContact.docDb.code=
mainContact.folder.id=
mainContact.id=
mainContact.name=
mainContact.type=
process.code=
process.name=
processCategory.code=
processCategory.name=
subType.code=
subType.name=
type.code=
type.name=
' #txt
Se0 f1 taskAndCaseSetupAction 'import ch.ivyteam.ivy.workflow.TaskUpdateDefinition;
ch.ivyteam.ivy.workflow.TaskUpdateDefinition taskUpdDef = new ch.ivyteam.ivy.workflow.TaskUpdateDefinition();
import ch.ivyteam.ivy.request.impl.DefaultCalendarProxy;
DefaultCalendarProxy calendarProxy = ivy.cal as DefaultCalendarProxy;
taskUpdDef.setPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
taskUpdDef.setExpiryActivator("Everybody");
taskUpdDef.setExpiryPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
engine.updateCurrentTask(taskUpdDef);
' #txt
Se0 f1 type ch.ivy.ws.addon.SecurityServiceData #txt
Se0 f1 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>findUsers</name>
        <nameStyle>9,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Se0 f1 @C|.responsibility Everybody #txt
Se0 f1 35 43 26 26 14 0 #rect
Se0 f1 @|StartWSIcon #fIcon
Se0 f4 expr out #txt
Se0 f4 48 69 48 116 #arcP
Se0 f6 inParamDecl '<List<java.lang.String> apps> param;' #txt
Se0 f6 inParamTable 'out.applications=param.apps;
' #txt
Se0 f6 outParamDecl '<List<ch.ivy.ws.addon.WSException> errors,List<ch.ivy.ws.addon.types.IvyRole> roles> result;
' #txt
Se0 f6 outParamTable 'result.errors=in.errors;
result.roles=in.ivyRoles;
' #txt
Se0 f6 actionDecl 'ch.ivy.ws.addon.SecurityServiceData out;
' #txt
Se0 f6 callSignature findAllRoles(List<String>) #txt
Se0 f6 useUserDefinedException false #txt
Se0 f6 taskData '#
#Wed Sep 10 21:22:34 CEST 2014
TaskTriggered.PRI=2
' #txt
Se0 f6 caseData '#
#Wed Sep 10 21:22:34 CEST 2014
businessCalendarName=
businessCreator.user=
businessMilestone.timestamp=
businessObject.code=
businessObject.docDb.code=
businessObject.folder.id=
businessObject.name=
businessPriority=
businessStart.timestamp=
case.description=
case.name=
correspondent.id=
mainContact.docDb.code=
mainContact.folder.id=
mainContact.id=
mainContact.name=
mainContact.type=
process.code=
process.name=
processCategory.code=
processCategory.name=
subType.code=
subType.name=
type.code=
type.name=
' #txt
Se0 f6 taskAndCaseSetupAction 'import ch.ivyteam.ivy.workflow.TaskUpdateDefinition;
ch.ivyteam.ivy.workflow.TaskUpdateDefinition taskUpdDef = new ch.ivyteam.ivy.workflow.TaskUpdateDefinition();
import ch.ivyteam.ivy.request.impl.DefaultCalendarProxy;
DefaultCalendarProxy calendarProxy = ivy.cal as DefaultCalendarProxy;
taskUpdDef.setPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
taskUpdDef.setExpiryActivator("Everybody");
taskUpdDef.setExpiryPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
engine.updateCurrentTask(taskUpdDef);
' #txt
Se0 f6 type ch.ivy.ws.addon.SecurityServiceData #txt
Se0 f6 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>findAllRoles</name>
        <nameStyle>12,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Se0 f6 @C|.responsibility Everybody #txt
Se0 f6 187 43 26 26 14 0 #rect
Se0 f6 @|StartWSIcon #fIcon
Se0 f10 expr out #txt
Se0 f10 200 69 200 116 #arcP
Se0 f11 inParamDecl '<java.lang.String app,java.lang.Long id> param;' #txt
Se0 f11 inParamTable 'out.application=param.app;
out.ivyRole.id=param.id;
' #txt
Se0 f11 outParamDecl '<List<ch.ivy.ws.addon.WSException> errors,List<ch.ivy.ws.addon.types.IvyUser> users> result;
' #txt
Se0 f11 outParamTable 'result.errors=in.errors;
result.users=in.ivyUsers;
' #txt
Se0 f11 actionDecl 'ch.ivy.ws.addon.SecurityServiceData out;
' #txt
Se0 f11 callSignature findUsersByRoleId(String,Long) #txt
Se0 f11 useUserDefinedException false #txt
Se0 f11 taskData '#
#Wed Sep 10 21:23:51 CEST 2014
TaskTriggered.PRI=2
' #txt
Se0 f11 caseData '#
#Wed Sep 10 21:23:51 CEST 2014
businessCalendarName=
businessCreator.user=
businessMilestone.timestamp=
businessObject.code=
businessObject.docDb.code=
businessObject.folder.id=
businessObject.name=
businessPriority=
businessStart.timestamp=
case.description=
case.name=
correspondent.id=
mainContact.docDb.code=
mainContact.folder.id=
mainContact.id=
mainContact.name=
mainContact.type=
process.code=
process.name=
processCategory.code=
processCategory.name=
subType.code=
subType.name=
type.code=
type.name=
' #txt
Se0 f11 taskAndCaseSetupAction 'import ch.ivyteam.ivy.workflow.TaskUpdateDefinition;
ch.ivyteam.ivy.workflow.TaskUpdateDefinition taskUpdDef = new ch.ivyteam.ivy.workflow.TaskUpdateDefinition();
import ch.ivyteam.ivy.request.impl.DefaultCalendarProxy;
DefaultCalendarProxy calendarProxy = ivy.cal as DefaultCalendarProxy;
taskUpdDef.setPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
taskUpdDef.setExpiryActivator("Everybody");
taskUpdDef.setExpiryPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
engine.updateCurrentTask(taskUpdDef);
' #txt
Se0 f11 type ch.ivy.ws.addon.SecurityServiceData #txt
Se0 f11 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>findUsersByRoleId</name>
        <nameStyle>17,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Se0 f11 @C|.responsibility Everybody #txt
Se0 f11 347 43 26 26 14 0 #rect
Se0 f11 @|StartWSIcon #fIcon
Se0 f13 expr out #txt
Se0 f13 360 69 360 116 #arcP
Se0 f14 inParamDecl '<java.lang.Long taskID> param;' #txt
Se0 f14 inParamTable 'out.taskId=param.taskID;
' #txt
Se0 f14 outParamDecl '<List<ch.ivy.ws.addon.WSException> errors,List<ch.ivy.ws.addon.types.IvyUser> users,List<ch.ivy.ws.addon.types.IvyRole> roles> result;
' #txt
Se0 f14 outParamTable 'result.errors=in.errors;
result.users=in.ivyUsers;
result.roles=in.ivyRoles;
' #txt
Se0 f14 actionDecl 'ch.ivy.ws.addon.SecurityServiceData out;
' #txt
Se0 f14 callSignature findSecurityMembersToDelegate(Long) #txt
Se0 f14 useUserDefinedException false #txt
Se0 f14 taskData TaskTriggered.PRI=2 #txt
Se0 f14 taskAndCaseSetupAction 'import ch.ivyteam.ivy.workflow.TaskUpdateDefinition;
ch.ivyteam.ivy.workflow.TaskUpdateDefinition taskUpdDef = new ch.ivyteam.ivy.workflow.TaskUpdateDefinition();
import ch.ivyteam.ivy.request.impl.DefaultCalendarProxy;
DefaultCalendarProxy calendarProxy = ivy.cal as DefaultCalendarProxy;
taskUpdDef.setPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
taskUpdDef.setExpiryActivator("Everybody");
taskUpdDef.setExpiryPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
engine.updateCurrentTask(taskUpdDef);
' #txt
Se0 f14 type ch.ivy.ws.addon.SecurityServiceData #txt
Se0 f14 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>findSecurityMembersToDelegate</name>
        <nameStyle>29,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Se0 f14 @C|.responsibility Everybody #txt
Se0 f14 531 43 26 26 14 0 #rect
Se0 f14 @|StartWSIcon #fIcon
Se0 f17 actionDecl 'ch.ivy.ws.addon.SecurityServiceData out;
' #txt
Se0 f17 actionTable 'out=in;
' #txt
Se0 f17 actionCode 'import ch.ivy.ws.addon.bo.SecurityServiceResult;
import ch.ivy.ws.addon.WsServiceFactory;
import ch.ivy.ws.addon.WSException;

try{
	SecurityServiceResult ssResult = WsServiceFactory.getSecurityService().findSecurityMembersToDelegate(in.task);
	in.ivyUsers = ssResult.ivyUsers;
	in.ivyRoles = ssResult.ivyRoles;
	in.errors = ssResult.getErrors();
}catch(WSException e){
	in.errors.add(e);
}' #txt
Se0 f17 type ch.ivy.ws.addon.SecurityServiceData #txt
Se0 f17 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>find security members
to delegate</name>
        <nameStyle>22,7
11,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Se0 f17 526 180 36 24 20 -2 #rect
Se0 f17 @|StepIcon #fIcon
Se0 f19 actionDecl 'ch.ivy.ws.addon.SecurityServiceData out;
' #txt
Se0 f19 actionTable 'out=in;
' #txt
Se0 f19 actionCode 'import ch.ivy.ws.addon.WsServiceFactory;

in.task = WsServiceFactory.getTaskService().findTask(in.taskId, in.errors);' #txt
Se0 f19 type ch.ivy.ws.addon.SecurityServiceData #txt
Se0 f19 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>find task by id</name>
        <nameStyle>15,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Se0 f19 526 116 36 24 20 -2 #rect
Se0 f19 @|StepIcon #fIcon
Se0 f20 expr out #txt
Se0 f20 544 69 544 116 #arcP
Se0 f18 expr out #txt
Se0 f18 544 140 544 180 #arcP
Se0 f59 type ch.ivy.ws.addon.SecurityServiceData #txt
Se0 f59 processCall FunctionalProcesses/ErrorLog:logError(List<java.lang.Exception>) #txt
Se0 f59 doCall true #txt
Se0 f59 requestActionDecl '<java.util.List<java.lang.Exception> errors> param;
' #txt
Se0 f59 requestMappingAction 'param.errors=in.errors;
' #txt
Se0 f59 responseActionDecl 'ch.ivy.ws.addon.SecurityServiceData out;
' #txt
Se0 f59 responseMappingAction 'out=in;
' #txt
Se0 f59 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>log errors if any</name>
        <nameStyle>17,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Se0 f59 182 324 36 24 20 -2 #rect
Se0 f59 @|CallSubIcon #fIcon
Se0 f58 type ch.ivy.ws.addon.SecurityServiceData #txt
Se0 f58 186 242 28 28 14 0 #rect
Se0 f58 @|AlternativeIcon #fIcon
Se0 f35 type ch.ivy.ws.addon.SecurityServiceData #txt
Se0 f35 187 395 26 26 14 0 #rect
Se0 f35 @|EndWSIcon #fIcon
Se0 f60 expr in #txt
Se0 f60 200 270 200 324 #arcP
Se0 f61 expr out #txt
Se0 f61 200 348 200 395 #arcP
Se0 f21 expr out #txt
Se0 f21 200 140 200 242 #arcP
Se0 f7 expr out #txt
Se0 f7 48 140 186 256 #arcP
Se0 f7 1 48 256 #addKink
Se0 f7 1 0.16712782878691168 0 0 #arcLabel
Se0 f2 expr out #txt
Se0 f2 360 140 214 256 #arcP
Se0 f2 1 360 256 #addKink
Se0 f2 1 0.19280617016642218 0 0 #arcLabel
Se0 f12 expr out #txt
Se0 f12 544 204 214 256 #arcP
Se0 f12 1 544 256 #addKink
Se0 f12 1 0.4125234740062913 0 0 #arcLabel
>Proto Se0 .webServiceName ch.ivy.ws.addon.SecurityService #txt
>Proto Se0 .type ch.ivy.ws.addon.SecurityServiceData #txt
>Proto Se0 .processKind WEB_SERVICE #txt
>Proto Se0 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <swimlaneLabel>findUsers</swimlaneLabel>
        <swimlaneLabel>findRoles</swimlaneLabel>
        <swimlaneLabel>findUsersByRoleId</swimlaneLabel>
        <swimlaneLabel>Find security members to delegate</swimlaneLabel>
        <swimlaneLabel></swimlaneLabel>
    </language>
    <swimlaneSize>152</swimlaneSize>
    <swimlaneSize>152</swimlaneSize>
    <swimlaneSize>176</swimlaneSize>
    <swimlaneSize>280</swimlaneSize>
    <swimlaneColor gradient="true">-6710785</swimlaneColor>
    <swimlaneColor gradient="true">-26164</swimlaneColor>
    <swimlaneColor gradient="true">-6710785</swimlaneColor>
    <swimlaneColor gradient="true">-1</swimlaneColor>
    <swimlaneType>LANE</swimlaneType>
    <swimlaneType>LANE</swimlaneType>
    <swimlaneType>LANE</swimlaneType>
    <swimlaneType>LANE</swimlaneType>
    <swimlaneSpaceBefore>0</swimlaneSpaceBefore>
    <swimlaneSpaceBefore>0</swimlaneSpaceBefore>
    <swimlaneSpaceBefore>0</swimlaneSpaceBefore>
    <swimlaneSpaceBefore>0</swimlaneSpaceBefore>
</elementInfo>
' #txt
>Proto Se0 -8 -8 16 16 16 26 #rect
>Proto Se0 '' #fIcon
Se0 f1 mainOut f4 tail #connect
Se0 f4 head f0 mainIn #connect
Se0 f6 mainOut f10 tail #connect
Se0 f10 head f5 mainIn #connect
Se0 f11 mainOut f13 tail #connect
Se0 f13 head f8 mainIn #connect
Se0 f14 mainOut f20 tail #connect
Se0 f20 head f19 mainIn #connect
Se0 f19 mainOut f18 tail #connect
Se0 f18 head f17 mainIn #connect
Se0 f58 out f60 tail #connect
Se0 f60 head f59 mainIn #connect
Se0 f59 mainOut f61 tail #connect
Se0 f61 head f35 mainIn #connect
Se0 f5 mainOut f21 tail #connect
Se0 f21 head f58 in #connect
Se0 f0 mainOut f7 tail #connect
Se0 f7 head f58 in #connect
Se0 f8 mainOut f2 tail #connect
Se0 f2 head f58 in #connect
Se0 f17 mainOut f12 tail #connect
Se0 f12 head f58 in #connect
