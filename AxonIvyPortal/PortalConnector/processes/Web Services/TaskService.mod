[Ivy]
[>Created: Thu Feb 25 11:33:50 ICT 2016]
1380566F9095B9C4 3.18 #module
>Proto >Proto Collection #zClass
Te0 TaskService Big #zClass
Te0 WS #cInfo
Te0 #process
Te0 @TextInP .webServiceName .webServiceName #zField
Te0 @TextInP .implementationClassName .implementationClassName #zField
Te0 @TextInP .authenticationType .authenticationType #zField
Te0 @TextInP .resExport .resExport #zField
Te0 @TextInP .type .type #zField
Te0 @TextInP .processKind .processKind #zField
Te0 @AnnotationInP-0n ai ai #zField
Te0 @TextInP .xml .xml #zField
Te0 @TextInP .responsibility .responsibility #zField
Te0 @StartWS ws0 '' #zField
Te0 @GridStep f0 '' #zField
Te0 @PushWFArc f1 '' #zField
Te0 @StartWS f3 '' #zField
Te0 @GridStep f5 '' #zField
Te0 @PushWFArc f6 '' #zField
Te0 @GridStep f8 '' #zField
Te0 @StartWS f9 '' #zField
Te0 @PushWFArc f11 '' #zField
Te0 @StartWS f13 '' #zField
Te0 @GridStep f14 '' #zField
Te0 @PushWFArc f16 '' #zField
Te0 @StartWS f19 '' #zField
Te0 @GridStep f20 '' #zField
Te0 @PushWFArc f21 '' #zField
Te0 @GridStep f23 '' #zField
Te0 @StartWS f25 '' #zField
Te0 @PushWFArc f26 '' #zField
Te0 @GridStep f29 '' #zField
Te0 @StartWS f30 '' #zField
Te0 @PushWFArc f31 '' #zField
Te0 @GridStep f33 '' #zField
Te0 @StartWS f34 '' #zField
Te0 @EndWS f35 '' #zField
Te0 @PushWFArc f36 '' #zField
Te0 @StartWS f38 '' #zField
Te0 @GridStep f41 '' #zField
Te0 @PushWFArc f42 '' #zField
Te0 @GridStep f43 '' #zField
Te0 @StartWS f44 '' #zField
Te0 @PushWFArc f46 '' #zField
Te0 @StartWS f48 '' #zField
Te0 @GridStep f50 '' #zField
Te0 @PushWFArc f51 '' #zField
Te0 @StartWS f53 '' #zField
Te0 @GridStep f56 '' #zField
Te0 @PushWFArc f57 '' #zField
Te0 @Alternative f58 '' #zField
Te0 @CallSub f59 '' #zField
Te0 @PushWFArc f60 '' #zField
Te0 @PushWFArc f61 '' #zField
Te0 @PushWFArc f7 '' #zField
Te0 @PushWFArc f62 '' #zField
Te0 @PushWFArc f2 '' #zField
Te0 @PushWFArc f32 '' #zField
Te0 @PushWFArc f55 '' #zField
Te0 @PushWFArc f12 '' #zField
Te0 @PushWFArc f37 '' #zField
Te0 @PushWFArc f17 '' #zField
Te0 @PushWFArc f22 '' #zField
Te0 @PushWFArc f27 '' #zField
Te0 @PushWFArc f40 '' #zField
Te0 @PushWFArc f47 '' #zField
Te0 @StartWS f4 '' #zField
Te0 @GridStep f10 '' #zField
Te0 @PushWFArc f15 '' #zField
Te0 @PushWFArc f18 '' #zField
>Proto Te0 Te0 TaskService #zField
Te0 ws0 inParamDecl '<java.lang.String serverUrl,java.lang.Long serverId,List<java.lang.String> apps,java.lang.String user,java.lang.String filter> param;' #txt
Te0 ws0 inParamTable 'out.applications=param.apps;
out.serverId=param.serverId;
out.serverUrl=param.serverUrl;
out.simpleFilter=param.filter;
out.user=param.user;
' #txt
Te0 ws0 outParamDecl '<List<ch.ivy.ws.addon.WSException> errors,List<ch.ivy.ws.addon.types.IvyTask> ivyTasks> result;
' #txt
Te0 ws0 outParamTable 'result.errors=in.errors;
result.ivyTasks=in.ivyTasks;
' #txt
Te0 ws0 actionDecl 'ch.ivy.ws.addon.TaskServiceData out;
' #txt
Te0 ws0 callSignature findTasksByFilter(String,Long,List<String>,String,String) #txt
Te0 ws0 useUserDefinedException false #txt
Te0 ws0 taskData TaskTriggered.PRI=2 #txt
Te0 ws0 taskAndCaseSetupAction 'import ch.ivyteam.ivy.workflow.TaskUpdateDefinition;
ch.ivyteam.ivy.workflow.TaskUpdateDefinition taskUpdDef = new ch.ivyteam.ivy.workflow.TaskUpdateDefinition();
import ch.ivyteam.ivy.request.impl.DefaultCalendarProxy;
DefaultCalendarProxy calendarProxy = ivy.cal as DefaultCalendarProxy;
taskUpdDef.setPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
taskUpdDef.setExpiryActivator("Everybody");
taskUpdDef.setExpiryPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
engine.updateCurrentTask(taskUpdDef);
' #txt
Te0 ws0 type ch.ivy.ws.addon.TaskServiceData #txt
Te0 ws0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>findTaskByFilter</name>
        <nameStyle>16,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Te0 ws0 @C|.responsibility Everybody #txt
Te0 ws0 155 51 26 26 14 0 #rect
Te0 ws0 @|StartWSIcon #fIcon
Te0 f0 actionDecl 'ch.ivy.ws.addon.TaskServiceData out;
' #txt
Te0 f0 actionTable 'out=in;
' #txt
Te0 f0 actionCode 'import ch.ivy.ws.addon.bo.TaskServiceResult;
import ch.ivy.ws.addon.WsServiceFactory;
import ch.ivy.ws.addon.WSException;

try{
	TaskServiceResult tsResult = WsServiceFactory.getTaskService().findTasksByFilter(in.user, in.simpleFilter, in.applications, in.serverId, in.serverUrl);
	in.ivyTasks = tsResult.getTasks();
	in.errors = tsResult.getErrors();
}catch(WSException e){
	in.errors.add(e);
}' #txt
Te0 f0 type ch.ivy.ws.addon.TaskServiceData #txt
Te0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>findTasks
ByFilter</name>
        <nameStyle>18,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Te0 f0 150 124 36 24 20 -2 #rect
Te0 f0 @|StepIcon #fIcon
Te0 f1 expr out #txt
Te0 f1 168 77 168 124 #arcP
Te0 f3 inParamDecl '<java.lang.String serverUrl,java.lang.Long id> param;' #txt
Te0 f3 inParamTable 'out.ivyTask.id=param.id;
out.serverUrl=param.serverUrl;
' #txt
Te0 f3 outParamDecl '<List<ch.ivy.ws.addon.WSException> errors,ch.ivy.ws.addon.types.IvyTask ivyTask> result;
' #txt
Te0 f3 outParamTable 'result.errors=in.errors;
result.ivyTask=in.ivyTask;
' #txt
Te0 f3 actionDecl 'ch.ivy.ws.addon.TaskServiceData out;
' #txt
Te0 f3 callSignature findTask(String,Long) #txt
Te0 f3 useUserDefinedException false #txt
Te0 f3 taskData TaskTriggered.PRI=2 #txt
Te0 f3 taskAndCaseSetupAction 'import ch.ivyteam.ivy.workflow.TaskUpdateDefinition;
ch.ivyteam.ivy.workflow.TaskUpdateDefinition taskUpdDef = new ch.ivyteam.ivy.workflow.TaskUpdateDefinition();
import ch.ivyteam.ivy.request.impl.DefaultCalendarProxy;
DefaultCalendarProxy calendarProxy = ivy.cal as DefaultCalendarProxy;
taskUpdDef.setPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
taskUpdDef.setExpiryActivator("Everybody");
taskUpdDef.setExpiryPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
engine.updateCurrentTask(taskUpdDef);
' #txt
Te0 f3 type ch.ivy.ws.addon.TaskServiceData #txt
Te0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>findTask</name>
        <nameStyle>8,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Te0 f3 @C|.responsibility Everybody #txt
Te0 f3 27 51 26 26 14 0 #rect
Te0 f3 @|StartWSIcon #fIcon
Te0 f5 actionDecl 'ch.ivy.ws.addon.TaskServiceData out;
' #txt
Te0 f5 actionTable 'out=in;
' #txt
Te0 f5 actionCode 'import ch.ivy.ws.addon.bo.TaskServiceResult;
import ch.ivy.ws.addon.WsServiceFactory;
import ch.ivy.ws.addon.WSException;

try{
	TaskServiceResult tsResult = WsServiceFactory.getTaskService().findTask(in.ivyTask.id, in.serverUrl);
	in.ivyTask = tsResult.getTask();
	in.errors = tsResult.getErrors();
}catch(WSException e){
	in.errors.add(e);
}' #txt
Te0 f5 type ch.ivy.ws.addon.TaskServiceData #txt
Te0 f5 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>findTasks
ByFilter</name>
        <nameStyle>18,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Te0 f5 22 124 36 24 20 -2 #rect
Te0 f5 @|StepIcon #fIcon
Te0 f6 expr out #txt
Te0 f6 40 77 40 124 #arcP
Te0 f8 actionDecl 'ch.ivy.ws.addon.TaskServiceData out;
' #txt
Te0 f8 actionTable 'out=in;
' #txt
Te0 f8 actionCode 'import ch.ivy.ws.addon.WsServiceFactory;
import ch.ivy.ws.addon.WSException;

try{
	in.ivyTask = WsServiceFactory.getTaskService().parkTask(in.user,in.ivyTask.id, in.serverUrl	);
}catch(WSException e){
	in.error = e;
}' #txt
Te0 f8 type ch.ivy.ws.addon.TaskServiceData #txt
Te0 f8 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>park
task</name>
        <nameStyle>9,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Te0 f8 870 124 36 24 20 -2 #rect
Te0 f8 @|StepIcon #fIcon
Te0 f9 inParamDecl '<java.lang.String serverUrl,java.lang.String user,java.lang.Long id> param;' #txt
Te0 f9 inParamTable 'out.ivyTask.id=param.id;
out.serverUrl=param.serverUrl;
out.user=param.user;
' #txt
Te0 f9 outParamDecl '<ch.ivy.ws.addon.WSException error,ch.ivy.ws.addon.types.IvyTask ivyTask> result;
' #txt
Te0 f9 outParamTable 'result.error=in.error;
result.ivyTask=in.ivyTask;
' #txt
Te0 f9 actionDecl 'ch.ivy.ws.addon.TaskServiceData out;
' #txt
Te0 f9 callSignature parkTask(String,String,Long) #txt
Te0 f9 useUserDefinedException false #txt
Te0 f9 taskData TaskTriggered.PRI=2 #txt
Te0 f9 taskAndCaseSetupAction 'import ch.ivyteam.ivy.workflow.TaskUpdateDefinition;
ch.ivyteam.ivy.workflow.TaskUpdateDefinition taskUpdDef = new ch.ivyteam.ivy.workflow.TaskUpdateDefinition();
import ch.ivyteam.ivy.request.impl.DefaultCalendarProxy;
DefaultCalendarProxy calendarProxy = ivy.cal as DefaultCalendarProxy;
taskUpdDef.setPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
taskUpdDef.setExpiryActivator("Everybody");
taskUpdDef.setExpiryPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
engine.updateCurrentTask(taskUpdDef);
' #txt
Te0 f9 type ch.ivy.ws.addon.TaskServiceData #txt
Te0 f9 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>parkTask</name>
        <nameStyle>8,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Te0 f9 @C|.responsibility Everybody #txt
Te0 f9 875 51 26 26 14 0 #rect
Te0 f9 @|StartWSIcon #fIcon
Te0 f11 expr out #txt
Te0 f11 888 77 888 124 #arcP
Te0 f13 inParamDecl '<java.lang.String serverUrl,ch.ivy.ws.addon.types.IvySecurityMember ivySecurityMember,java.lang.Long id> param;' #txt
Te0 f13 inParamTable 'out.ivySecurityMember=param.ivySecurityMember;
out.ivyTask.id=param.id;
out.serverUrl=param.serverUrl;
' #txt
Te0 f13 outParamDecl '<List<ch.ivy.ws.addon.WSException> errors,ch.ivy.ws.addon.types.IvyTask ivyTask> result;
' #txt
Te0 f13 outParamTable 'result.errors=in.errors;
result.ivyTask=in.ivyTask;
' #txt
Te0 f13 actionDecl 'ch.ivy.ws.addon.TaskServiceData out;
' #txt
Te0 f13 callSignature delegateTask(String,ch.ivy.ws.addon.types.IvySecurityMember,Long) #txt
Te0 f13 useUserDefinedException false #txt
Te0 f13 taskData TaskTriggered.PRI=2 #txt
Te0 f13 taskAndCaseSetupAction 'import ch.ivyteam.ivy.workflow.TaskUpdateDefinition;
ch.ivyteam.ivy.workflow.TaskUpdateDefinition taskUpdDef = new ch.ivyteam.ivy.workflow.TaskUpdateDefinition();
import ch.ivyteam.ivy.request.impl.DefaultCalendarProxy;
DefaultCalendarProxy calendarProxy = ivy.cal as DefaultCalendarProxy;
taskUpdDef.setPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
taskUpdDef.setExpiryActivator("Everybody");
taskUpdDef.setExpiryPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
engine.updateCurrentTask(taskUpdDef);
' #txt
Te0 f13 type ch.ivy.ws.addon.TaskServiceData #txt
Te0 f13 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>delegateTask</name>
        <nameStyle>12,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Te0 f13 @C|.responsibility Everybody #txt
Te0 f13 1059 51 26 26 14 0 #rect
Te0 f13 @|StartWSIcon #fIcon
Te0 f14 actionDecl 'ch.ivy.ws.addon.TaskServiceData out;
' #txt
Te0 f14 actionTable 'out=in;
' #txt
Te0 f14 actionCode 'import ch.ivy.ws.addon.bo.TaskServiceResult;
import ch.ivy.ws.addon.WsServiceFactory;
import ch.ivy.ws.addon.WSException;

try{
	TaskServiceResult tsResult = WsServiceFactory.getTaskService().delegateTask(in.ivyTask.id,in.ivySecurityMember, in.serverUrl	);
	in.ivyTask = tsResult.getTask();
	in.errors = tsResult.getErrors();
}catch(WSException e){
	in.errors.add(e);
}' #txt
Te0 f14 type ch.ivy.ws.addon.TaskServiceData #txt
Te0 f14 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>delegate
task</name>
        <nameStyle>13,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Te0 f14 1054 124 36 24 20 -2 #rect
Te0 f14 @|StepIcon #fIcon
Te0 f16 expr out #txt
Te0 f16 1072 77 1072 124 #arcP
Te0 f19 inParamDecl '<java.lang.String user,java.lang.String message,java.lang.Long id> param;' #txt
Te0 f19 inParamTable 'out.ivyTask.id=param.id;
out.note.message=param.message;
out.user=param.user;
' #txt
Te0 f19 outParamDecl '<List<ch.ivy.ws.addon.WSException> errors,ch.ivy.ws.addon.types.IvyNote note> result;
' #txt
Te0 f19 outParamTable 'result.errors=in.errors;
result.note=in.note;
' #txt
Te0 f19 actionDecl 'ch.ivy.ws.addon.TaskServiceData out;
' #txt
Te0 f19 callSignature createTaskNote(String,String,Long) #txt
Te0 f19 useUserDefinedException false #txt
Te0 f19 taskData TaskTriggered.PRI=2 #txt
Te0 f19 taskAndCaseSetupAction 'import ch.ivyteam.ivy.workflow.TaskUpdateDefinition;
ch.ivyteam.ivy.workflow.TaskUpdateDefinition taskUpdDef = new ch.ivyteam.ivy.workflow.TaskUpdateDefinition();
import ch.ivyteam.ivy.request.impl.DefaultCalendarProxy;
DefaultCalendarProxy calendarProxy = ivy.cal as DefaultCalendarProxy;
taskUpdDef.setPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
taskUpdDef.setExpiryActivator("Everybody");
taskUpdDef.setExpiryPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
engine.updateCurrentTask(taskUpdDef);
' #txt
Te0 f19 type ch.ivy.ws.addon.TaskServiceData #txt
Te0 f19 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>createTaskNote</name>
        <nameStyle>14,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Te0 f19 @C|.responsibility Everybody #txt
Te0 f19 1259 51 26 26 14 0 #rect
Te0 f19 @|StartWSIcon #fIcon
Te0 f20 actionDecl 'ch.ivy.ws.addon.TaskServiceData out;
' #txt
Te0 f20 actionTable 'out=in;
' #txt
Te0 f20 actionCode 'import ch.ivy.ws.addon.bo.NoteServiceResult;
import ch.ivy.ws.addon.WsServiceFactory;
import ch.ivy.ws.addon.WSException;

try{
	NoteServiceResult nsResult = WsServiceFactory.getTaskService().createNote(in.user,in.ivyTask.id,in.note.message);
	in.note = nsResult.getNewNote();
	in.errors = nsResult.getErrors();
}catch(WSException e){
	in.errors.add(e);
}' #txt
Te0 f20 type ch.ivy.ws.addon.TaskServiceData #txt
Te0 f20 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>create note</name>
        <nameStyle>11,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Te0 f20 1254 124 36 24 20 -2 #rect
Te0 f20 @|StepIcon #fIcon
Te0 f21 expr out #txt
Te0 f21 1272 77 1272 124 #arcP
Te0 f23 actionDecl 'ch.ivy.ws.addon.TaskServiceData out;
' #txt
Te0 f23 actionTable 'out=in;
' #txt
Te0 f23 actionCode 'import ch.ivy.ws.addon.bo.NoteServiceResult;
import ch.ivy.ws.addon.WsServiceFactory;
import ch.ivy.ws.addon.WSException;

try{
	NoteServiceResult nsResult = WsServiceFactory.getTaskService().findNotes(in.ivyTask.id);
	in.notes = nsResult.getNotes();
	in.errors = nsResult.getErrors();
}catch(WSException e){
	in.errors.add(e);
}' #txt
Te0 f23 type ch.ivy.ws.addon.TaskServiceData #txt
Te0 f23 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>find notes
for task</name>
        <nameStyle>19,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Te0 f23 1446 124 36 24 20 -2 #rect
Te0 f23 @|StepIcon #fIcon
Te0 f25 inParamDecl '<java.lang.Long id> param;' #txt
Te0 f25 inParamTable 'out.ivyTask.id=param.id;
' #txt
Te0 f25 outParamDecl '<List<ch.ivy.ws.addon.WSException> errors,List<ch.ivy.ws.addon.types.IvyNote> ivyNotes> result;
' #txt
Te0 f25 outParamTable 'result.errors=in.errors;
result.ivyNotes=in.notes;
' #txt
Te0 f25 actionDecl 'ch.ivy.ws.addon.TaskServiceData out;
' #txt
Te0 f25 callSignature findNotes(Long) #txt
Te0 f25 useUserDefinedException false #txt
Te0 f25 taskData '#
#Wed Jul 02 10:42:25 CEST 2014
TaskTriggered.PRI=2
' #txt
Te0 f25 caseData '#
#Wed Jul 02 10:42:25 CEST 2014
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
Te0 f25 taskAndCaseSetupAction 'import ch.ivyteam.ivy.workflow.TaskUpdateDefinition;
ch.ivyteam.ivy.workflow.TaskUpdateDefinition taskUpdDef = new ch.ivyteam.ivy.workflow.TaskUpdateDefinition();
import ch.ivyteam.ivy.request.impl.DefaultCalendarProxy;
DefaultCalendarProxy calendarProxy = ivy.cal as DefaultCalendarProxy;
taskUpdDef.setPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
taskUpdDef.setExpiryActivator("Everybody");
taskUpdDef.setExpiryPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
engine.updateCurrentTask(taskUpdDef);
' #txt
Te0 f25 type ch.ivy.ws.addon.TaskServiceData #txt
Te0 f25 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>findNotes</name>
        <nameStyle>9,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Te0 f25 @C|.responsibility Everybody #txt
Te0 f25 1451 51 26 26 14 0 #rect
Te0 f25 @|StartWSIcon #fIcon
Te0 f26 expr out #txt
Te0 f26 1464 77 1464 124 #arcP
Te0 f29 actionDecl 'ch.ivy.ws.addon.TaskServiceData out;
' #txt
Te0 f29 actionTable 'out=in;
' #txt
Te0 f29 actionCode 'import ch.ivy.ws.addon.bo.TaskServiceResult;
import ch.ivyteam.ivy.workflow.TaskState;
import ch.ivy.ws.addon.WsServiceFactory;
import ch.ivy.ws.addon.WSException;

try{
	TaskServiceResult tsResult = WsServiceFactory.getTaskService().findTasksByFilter(in.user, in.simpleFilter, in.applications, in.serverId, in.serverUrl, TaskState.DONE);
	in.ivyTasks = tsResult.getTasks();
	in.errors = tsResult.getErrors();
}catch(WSException e){
	in.errors.add(e);
}' #txt
Te0 f29 type ch.ivy.ws.addon.TaskServiceData #txt
Te0 f29 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>findDoneTasks
ByFilter</name>
        <nameStyle>22,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Te0 f29 278 124 36 24 20 -2 #rect
Te0 f29 @|StepIcon #fIcon
Te0 f30 inParamDecl '<java.lang.String serverUrl,java.lang.Long serverId,List<java.lang.String> apps,java.lang.String user,java.lang.String filter> param;' #txt
Te0 f30 inParamTable 'out.applications=param.apps;
out.serverId=param.serverId;
out.serverUrl=param.serverUrl;
out.simpleFilter=param.filter;
out.user=param.user;
' #txt
Te0 f30 outParamDecl '<List<ch.ivy.ws.addon.WSException> errors,List<ch.ivy.ws.addon.types.IvyTask> ivyTasks> result;
' #txt
Te0 f30 outParamTable 'result.errors=in.errors;
result.ivyTasks=in.ivyTasks;
' #txt
Te0 f30 actionDecl 'ch.ivy.ws.addon.TaskServiceData out;
' #txt
Te0 f30 callSignature findDoneTasksByFilter(String,Long,List<String>,String,String) #txt
Te0 f30 useUserDefinedException false #txt
Te0 f30 taskData TaskTriggered.PRI=2 #txt
Te0 f30 taskAndCaseSetupAction 'import ch.ivyteam.ivy.workflow.TaskUpdateDefinition;
ch.ivyteam.ivy.workflow.TaskUpdateDefinition taskUpdDef = new ch.ivyteam.ivy.workflow.TaskUpdateDefinition();
import ch.ivyteam.ivy.request.impl.DefaultCalendarProxy;
DefaultCalendarProxy calendarProxy = ivy.cal as DefaultCalendarProxy;
taskUpdDef.setPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
taskUpdDef.setExpiryActivator("Everybody");
taskUpdDef.setExpiryPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
engine.updateCurrentTask(taskUpdDef);
' #txt
Te0 f30 type ch.ivy.ws.addon.TaskServiceData #txt
Te0 f30 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>findDoneTaskByFilter</name>
        <nameStyle>20,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Te0 f30 @C|.responsibility Everybody #txt
Te0 f30 283 51 26 26 14 0 #rect
Te0 f30 @|StartWSIcon #fIcon
Te0 f31 expr out #txt
Te0 f31 296 77 296 124 #arcP
Te0 f33 actionDecl 'ch.ivy.ws.addon.TaskServiceData out;
' #txt
Te0 f33 actionTable 'out=in;
' #txt
Te0 f33 actionCode 'import ch.ivy.ws.addon.bo.TaskServiceResult;
import ch.ivyteam.ivy.workflow.TaskState;
import ch.ivy.ws.addon.WsServiceFactory;
import ch.ivy.ws.addon.WSException;

try{
	TaskServiceResult tsResult = WsServiceFactory.getTaskService().findTasksByFilter(in.user, in.simpleFilter, in.applications, in.serverId, in.serverUrl, TaskState.SUSPENDED, TaskState.PARKED, TaskState.RESUMED);
	in.ivyTasks = tsResult.getTasks();
	in.errors = tsResult.getErrors();
}catch(WSException e){
	in.errors.add(e);
}' #txt
Te0 f33 type ch.ivy.ws.addon.TaskServiceData #txt
Te0 f33 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>find active Tasks
ByFilter</name>
        <nameStyle>26,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Te0 f33 446 132 36 24 20 -2 #rect
Te0 f33 @|StepIcon #fIcon
Te0 f34 inParamDecl '<java.lang.String serverUrl,java.lang.Long serverId,List<java.lang.String> apps,java.lang.String user,java.lang.String filter> param;' #txt
Te0 f34 inParamTable 'out.applications=param.apps;
out.serverId=param.serverId;
out.serverUrl=param.serverUrl;
out.simpleFilter=param.filter;
out.user=param.user;
' #txt
Te0 f34 outParamDecl '<List<ch.ivy.ws.addon.WSException> errors,List<ch.ivy.ws.addon.types.IvyTask> ivyTasks> result;
' #txt
Te0 f34 outParamTable 'result.errors=in.errors;
result.ivyTasks=in.ivyTasks;
' #txt
Te0 f34 actionDecl 'ch.ivy.ws.addon.TaskServiceData out;
' #txt
Te0 f34 callSignature findActiveTasksByFilter(String,Long,List<String>,String,String) #txt
Te0 f34 useUserDefinedException false #txt
Te0 f34 taskData TaskTriggered.PRI=2 #txt
Te0 f34 taskAndCaseSetupAction 'import ch.ivyteam.ivy.workflow.TaskUpdateDefinition;
ch.ivyteam.ivy.workflow.TaskUpdateDefinition taskUpdDef = new ch.ivyteam.ivy.workflow.TaskUpdateDefinition();
import ch.ivyteam.ivy.request.impl.DefaultCalendarProxy;
DefaultCalendarProxy calendarProxy = ivy.cal as DefaultCalendarProxy;
taskUpdDef.setPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
taskUpdDef.setExpiryActivator("Everybody");
taskUpdDef.setExpiryPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
engine.updateCurrentTask(taskUpdDef);
' #txt
Te0 f34 type ch.ivy.ws.addon.TaskServiceData #txt
Te0 f34 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>findActiveTasksByFilter</name>
        <nameStyle>23,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Te0 f34 @C|.responsibility Everybody #txt
Te0 f34 451 59 26 26 14 0 #rect
Te0 f34 @|StartWSIcon #fIcon
Te0 f35 type ch.ivy.ws.addon.TaskServiceData #txt
Te0 f35 627 355 26 26 14 0 #rect
Te0 f35 @|EndWSIcon #fIcon
Te0 f36 expr out #txt
Te0 f36 464 85 464 132 #arcP
Te0 f38 inParamDecl '<java.lang.String serverUrl,java.lang.Long id> param;' #txt
Te0 f38 inParamTable 'out.ivyTask.id=param.id;
out.serverUrl=param.serverUrl;
' #txt
Te0 f38 outParamDecl '<List<ch.ivy.ws.addon.WSException> errors,ch.ivy.ws.addon.types.IvyTask ivyTask> result;
' #txt
Te0 f38 outParamTable 'result.errors=in.errors;
result.ivyTask=in.ivyTask;
' #txt
Te0 f38 actionDecl 'ch.ivy.ws.addon.TaskServiceData out;
' #txt
Te0 f38 callSignature resetTask(String,Long) #txt
Te0 f38 useUserDefinedException false #txt
Te0 f38 taskData TaskTriggered.PRI=2 #txt
Te0 f38 taskAndCaseSetupAction 'import ch.ivyteam.ivy.workflow.TaskUpdateDefinition;
ch.ivyteam.ivy.workflow.TaskUpdateDefinition taskUpdDef = new ch.ivyteam.ivy.workflow.TaskUpdateDefinition();
import ch.ivyteam.ivy.request.impl.DefaultCalendarProxy;
DefaultCalendarProxy calendarProxy = ivy.cal as DefaultCalendarProxy;
taskUpdDef.setPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
taskUpdDef.setExpiryActivator("Everybody");
taskUpdDef.setExpiryPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
engine.updateCurrentTask(taskUpdDef);
' #txt
Te0 f38 type ch.ivy.ws.addon.TaskServiceData #txt
Te0 f38 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>resetTask(String,Long)</name>
    </language>
</elementInfo>
' #txt
Te0 f38 @C|.responsibility Everybody #txt
Te0 f38 1643 51 26 26 14 0 #rect
Te0 f38 @|StartWSIcon #fIcon
Te0 f41 actionDecl 'ch.ivy.ws.addon.TaskServiceData out;
' #txt
Te0 f41 actionTable 'out=in;
' #txt
Te0 f41 actionCode 'import ch.ivy.ws.addon.bo.TaskServiceResult;
import ch.ivy.ws.addon.WsServiceFactory;
import ch.ivy.ws.addon.WSException;

try{
	TaskServiceResult tsResult = WsServiceFactory.getTaskService().resetTask(in.ivyTask.id, in.serverUrl	);
	in.ivyTask = tsResult.getTask();
	in.errors = tsResult.getErrors();
}catch(WSException e){
	in.errors.add(e);
}' #txt
Te0 f41 type ch.ivy.ws.addon.TaskServiceData #txt
Te0 f41 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>reset task</name>
        <nameStyle>10,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Te0 f41 1638 124 36 24 20 -2 #rect
Te0 f41 @|StepIcon #fIcon
Te0 f42 expr out #txt
Te0 f42 1656 77 1656 124 #arcP
Te0 f43 actionDecl 'ch.ivy.ws.addon.TaskServiceData out;
' #txt
Te0 f43 actionTable 'out=in;
' #txt
Te0 f43 actionCode 'import ch.ivy.ws.addon.bo.TaskServiceResult;
import ch.ivy.ws.addon.WsServiceFactory;
import ch.ivy.ws.addon.WSException;

try{
	TaskServiceResult tsResult = WsServiceFactory.getTaskService().findTasksByCriteria(in.taskSearchCriteria, in.serverUrl);
	in.ivyTasks = tsResult.getTasks();
	in.errors = tsResult.getErrors();
}catch(WSException e){
	in.errors.add(e);
}' #txt
Te0 f43 type ch.ivy.ws.addon.TaskServiceData #txt
Te0 f43 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>findTasksByCriteria</name>
        <nameStyle>19,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Te0 f43 1814 116 36 24 20 -2 #rect
Te0 f43 @|StepIcon #fIcon
Te0 f44 inParamDecl '<java.lang.String serverUrl,ch.ivy.ws.addon.service.TaskSearchCriteria taskSearchCriteria> param;' #txt
Te0 f44 inParamTable 'out.serverUrl=param.serverUrl;
out.taskSearchCriteria=param.taskSearchCriteria;
' #txt
Te0 f44 outParamDecl '<List<ch.ivy.ws.addon.WSException> errors,List<ch.ivy.ws.addon.types.IvyTask> ivyTasks> result;
' #txt
Te0 f44 outParamTable 'result.errors=in.errors;
result.ivyTasks=in.ivyTasks;
' #txt
Te0 f44 actionDecl 'ch.ivy.ws.addon.TaskServiceData out;
' #txt
Te0 f44 callSignature findTasksByCriteria(String,ch.ivy.ws.addon.service.TaskSearchCriteria) #txt
Te0 f44 useUserDefinedException false #txt
Te0 f44 taskData TaskTriggered.PRI=2 #txt
Te0 f44 taskAndCaseSetupAction 'import ch.ivyteam.ivy.workflow.TaskUpdateDefinition;
ch.ivyteam.ivy.workflow.TaskUpdateDefinition taskUpdDef = new ch.ivyteam.ivy.workflow.TaskUpdateDefinition();
import ch.ivyteam.ivy.request.impl.DefaultCalendarProxy;
DefaultCalendarProxy calendarProxy = ivy.cal as DefaultCalendarProxy;
taskUpdDef.setPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
taskUpdDef.setExpiryActivator("Everybody");
taskUpdDef.setExpiryPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
engine.updateCurrentTask(taskUpdDef);
' #txt
Te0 f44 type ch.ivy.ws.addon.TaskServiceData #txt
Te0 f44 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>findTasksByCriteria(String,TaskSearchCriteria)</name>
    </language>
</elementInfo>
' #txt
Te0 f44 @C|.responsibility Everybody #txt
Te0 f44 1819 43 26 26 14 0 #rect
Te0 f44 @|StartWSIcon #fIcon
Te0 f46 expr out #txt
Te0 f46 1832 69 1832 116 #arcP
Te0 f48 inParamDecl '<java.lang.Long id,java.lang.String userName> param;' #txt
Te0 f48 inParamTable 'out.ivyTask.id=param.id;
out.user=param.userName;
' #txt
Te0 f48 outParamDecl '<java.lang.Boolean canUserResumeTask,List<ch.ivy.ws.addon.WSException> errors> result;
' #txt
Te0 f48 outParamTable 'result.canUserResumeTask=in.canUserResumeTask;
result.errors=in.errors;
' #txt
Te0 f48 actionDecl 'ch.ivy.ws.addon.TaskServiceData out;
' #txt
Te0 f48 callSignature canUserResumeTask(Long,String) #txt
Te0 f48 useUserDefinedException false #txt
Te0 f48 taskData TaskTriggered.PRI=2 #txt
Te0 f48 taskAndCaseSetupAction 'import ch.ivyteam.ivy.workflow.TaskUpdateDefinition;
ch.ivyteam.ivy.workflow.TaskUpdateDefinition taskUpdDef = new ch.ivyteam.ivy.workflow.TaskUpdateDefinition();
import ch.ivyteam.ivy.request.impl.DefaultCalendarProxy;
DefaultCalendarProxy calendarProxy = ivy.cal as DefaultCalendarProxy;
taskUpdDef.setPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
taskUpdDef.setExpiryActivator("Everybody");
taskUpdDef.setExpiryPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
engine.updateCurrentTask(taskUpdDef);
' #txt
Te0 f48 type ch.ivy.ws.addon.TaskServiceData #txt
Te0 f48 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>canUserResumeTask
(Long,String)</name>
        <nameStyle>31,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Te0 f48 @C|.responsibility Everybody #txt
Te0 f48 2131 51 26 26 14 0 #rect
Te0 f48 @|StartWSIcon #fIcon
Te0 f50 actionDecl 'ch.ivy.ws.addon.TaskServiceData out;
' #txt
Te0 f50 actionTable 'out=in;
' #txt
Te0 f50 actionCode 'import ch.ivy.ws.addon.bo.TaskServiceResult;
import ch.ivy.ws.addon.WsServiceFactory;
import ch.ivy.ws.addon.WSException;

try{
	TaskServiceResult tsResult = WsServiceFactory.getTaskService().canUserResumeTask(in.ivyTask.id, in.user);
	in.canUserResumeTask = tsResult.canUserResumeTask;
	in.errors = tsResult.getErrors();
}catch(WSException e){
	in.errors.add(e);
}' #txt
Te0 f50 type ch.ivy.ws.addon.TaskServiceData #txt
Te0 f50 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>check if user 
can resume task</name>
        <nameStyle>30,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Te0 f50 2126 116 36 24 20 -2 #rect
Te0 f50 @|StepIcon #fIcon
Te0 f51 expr out #txt
Te0 f51 2144 77 2144 116 #arcP
Te0 f53 inParamDecl '<List<java.lang.String> apps,java.lang.Long serverId,java.lang.String serverUrl,java.lang.String user> param;' #txt
Te0 f53 inParamTable 'out.applications=param.apps;
out.serverId=param.serverId;
out.serverUrl=param.serverUrl;
out.user=param.user;
' #txt
Te0 f53 outParamDecl '<List<ch.ivy.ws.addon.WSException> errors,List<ch.ivy.ws.addon.types.IvyTask> personalTasks,List<ch.ivy.ws.addon.types.IvyTask> allTasks> result;
' #txt
Te0 f53 outParamTable 'result.errors=in.errors;
result.personalTasks=in.ivyTasks;
result.allTasks=in.allIvyTasks;
' #txt
Te0 f53 actionDecl 'ch.ivy.ws.addon.TaskServiceData out;
' #txt
Te0 f53 callSignature findAllRunningTasks(List<String>,Long,String,String) #txt
Te0 f53 useUserDefinedException false #txt
Te0 f53 taskData TaskTriggered.PRI=2 #txt
Te0 f53 taskAndCaseSetupAction 'import ch.ivyteam.ivy.workflow.TaskUpdateDefinition;
ch.ivyteam.ivy.workflow.TaskUpdateDefinition taskUpdDef = new ch.ivyteam.ivy.workflow.TaskUpdateDefinition();
import ch.ivyteam.ivy.request.impl.DefaultCalendarProxy;
DefaultCalendarProxy calendarProxy = ivy.cal as DefaultCalendarProxy;
taskUpdDef.setPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
taskUpdDef.setExpiryActivator("Everybody");
taskUpdDef.setExpiryPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
engine.updateCurrentTask(taskUpdDef);
' #txt
Te0 f53 type ch.ivy.ws.addon.TaskServiceData #txt
Te0 f53 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>findAllRunningTasks
(List&lt;String&gt;,Long,String,String)</name>
        <nameStyle>53,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Te0 f53 @C|.responsibility Everybody #txt
Te0 f53 627 51 26 26 14 0 #rect
Te0 f53 @|StartWSIcon #fIcon
Te0 f56 actionDecl 'ch.ivy.ws.addon.TaskServiceData out;
' #txt
Te0 f56 actionTable 'out=in;
' #txt
Te0 f56 actionCode 'import ch.ivy.ws.addon.bo.TaskServiceResult;
import ch.ivyteam.ivy.workflow.TaskState;
import ch.ivy.ws.addon.WsServiceFactory;
import ch.ivy.ws.addon.WSException;

try{
	TaskServiceResult tsResult = WsServiceFactory.getTaskService().findAllRunningTasks(in.user, in.applications, in.serverId, in.serverUrl);
	in.ivyTasks = tsResult.getTasks();
	in.allIvyTasks = tsResult.getAllTasks();
	in.errors = tsResult.getErrors();
}catch(WSException e){
	in.errors.add(e);
}' #txt
Te0 f56 type ch.ivy.ws.addon.TaskServiceData #txt
Te0 f56 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>find running tasks</name>
        <nameStyle>18,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Te0 f56 622 132 36 24 20 -2 #rect
Te0 f56 @|StepIcon #fIcon
Te0 f57 expr out #txt
Te0 f57 640 77 640 132 #arcP
Te0 f58 type ch.ivy.ws.addon.TaskServiceData #txt
Te0 f58 626 218 28 28 14 0 #rect
Te0 f58 @|AlternativeIcon #fIcon
Te0 f59 type ch.ivy.ws.addon.TaskServiceData #txt
Te0 f59 processCall FunctionalProcesses/ErrorLog:logError(List<java.lang.Exception>) #txt
Te0 f59 doCall true #txt
Te0 f59 requestActionDecl '<java.util.List<java.lang.Exception> errors> param;
' #txt
Te0 f59 requestMappingAction 'param.errors=in.errors;
' #txt
Te0 f59 responseActionDecl 'ch.ivy.ws.addon.TaskServiceData out;
' #txt
Te0 f59 responseMappingAction 'out=in;
' #txt
Te0 f59 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>log errors if any</name>
        <nameStyle>17,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Te0 f59 622 284 36 24 20 -2 #rect
Te0 f59 @|CallSubIcon #fIcon
Te0 f60 expr in #txt
Te0 f60 640 246 640 284 #arcP
Te0 f61 expr out #txt
Te0 f61 640 308 640 355 #arcP
Te0 f7 expr out #txt
Te0 f7 40 148 626 232 #arcP
Te0 f7 1 40 232 #addKink
Te0 f7 1 0.3855271544343356 0 0 #arcLabel
Te0 f62 expr out #txt
Te0 f62 168 148 626 232 #arcP
Te0 f62 1 168 232 #addKink
Te0 f62 1 0.3415286554155283 0 0 #arcLabel
Te0 f2 expr out #txt
Te0 f2 296 148 626 232 #arcP
Te0 f2 1 296 232 #addKink
Te0 f2 1 0.2451318315836341 0 0 #arcLabel
Te0 f32 expr out #txt
Te0 f32 640 156 640 218 #arcP
Te0 f32 0 0.9800965556674334 0 0 #arcLabel
Te0 f55 expr out #txt
Te0 f55 888 148 654 232 #arcP
Te0 f55 1 888 232 #addKink
Te0 f55 1 0.9805359095139481 0 0 #arcLabel
Te0 f12 expr out #txt
Te0 f12 464 156 626 232 #arcP
Te0 f12 1 464 232 #addKink
Te0 f12 0 0.7614986028153429 0 0 #arcLabel
Te0 f37 expr out #txt
Te0 f37 1072 148 654 232 #arcP
Te0 f37 1 1072 232 #addKink
Te0 f37 1 0.9841320950935127 0 0 #arcLabel
Te0 f17 expr out #txt
Te0 f17 1272 148 654 232 #arcP
Te0 f17 1 1272 232 #addKink
Te0 f17 1 0.98579404476964 0 0 #arcLabel
Te0 f22 expr out #txt
Te0 f22 1464 148 654 232 #arcP
Te0 f22 1 1464 232 #addKink
Te0 f22 1 0.443798648262331 0 0 #arcLabel
Te0 f27 expr out #txt
Te0 f27 1656 148 654 232 #arcP
Te0 f27 1 1656 232 #addKink
Te0 f27 1 0.4533394530682278 0 0 #arcLabel
Te0 f40 expr out #txt
Te0 f40 1832 140 654 232 #arcP
Te0 f40 1 1832 232 #addKink
Te0 f40 1 0.4566039884861746 0 0 #arcLabel
Te0 f47 expr out #txt
Te0 f47 2144 140 654 232 #arcP
Te0 f47 1 2144 232 #addKink
Te0 f47 1 0.4649668975722141 0 0 #arcLabel
Te0 f4 inParamDecl '<java.lang.String serverUrl,java.lang.Long id> param;' #txt
Te0 f4 inParamTable 'out.ivyCase.id=param.id;
out.serverUrl=param.serverUrl;
' #txt
Te0 f4 outParamDecl '<java.util.List<ch.ivy.ws.addon.types.IvyTask> ivyTasks,java.util.List<ch.ivy.ws.addon.WSException> errors> result;
' #txt
Te0 f4 outParamTable 'result.ivyTasks=in.ivyTasks;
result.errors=in.errors;
' #txt
Te0 f4 actionDecl 'ch.ivy.ws.addon.TaskServiceData out;
' #txt
Te0 f4 callSignature findTasksOfCase(String,Long) #txt
Te0 f4 useUserDefinedException false #txt
Te0 f4 taskData TaskTriggered.PRI=2 #txt
Te0 f4 taskAndCaseSetupAction 'import ch.ivyteam.ivy.workflow.TaskUpdateDefinition;
ch.ivyteam.ivy.workflow.TaskUpdateDefinition taskUpdDef = new ch.ivyteam.ivy.workflow.TaskUpdateDefinition();
import ch.ivyteam.ivy.request.impl.DefaultCalendarProxy;
DefaultCalendarProxy calendarProxy = ivy.cal as DefaultCalendarProxy;
taskUpdDef.setPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
taskUpdDef.setExpiryActivator("Everybody");
taskUpdDef.setExpiryPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
engine.updateCurrentTask(taskUpdDef);
' #txt
Te0 f4 type ch.ivy.ws.addon.TaskServiceData #txt
Te0 f4 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>findTasksOfCase
(String,String)</name>
        <nameStyle>38,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Te0 f4 @C|.responsibility Everybody #txt
Te0 f4 2323 51 26 26 14 0 #rect
Te0 f4 @|StartWSIcon #fIcon
Te0 f10 actionDecl 'ch.ivy.ws.addon.TaskServiceData out;
' #txt
Te0 f10 actionTable 'out=in;
' #txt
Te0 f10 actionCode 'import ch.ivy.ws.addon.WSException;
import ch.ivy.ws.addon.bo.TaskServiceResult;
import ch.ivy.ws.addon.service.TaskServiceImpl;
TaskServiceImpl taskService = new TaskServiceImpl();

try {
  TaskServiceResult result = taskService.findTasksOfCase(in.ivyCase.id,in.serverUrl);
  in.ivyTasks = result.getTasks();
  in.errors = result.getErrors();
} catch (WSException e) {
	in.errors.add(e);
}

' #txt
Te0 f10 type ch.ivy.ws.addon.TaskServiceData #txt
Te0 f10 2318 116 36 24 20 -2 #rect
Te0 f10 @|StepIcon #fIcon
Te0 f15 expr out #txt
Te0 f15 2336 77 2336 116 #arcP
Te0 f18 expr out #txt
Te0 f18 2336 140 654 232 #arcP
Te0 f18 1 2336 232 #addKink
Te0 f18 1 0.4726516052318669 0 0 #arcLabel
>Proto Te0 .webServiceName ch.ivy.ws.addon.TaskService #txt
>Proto Te0 .type ch.ivy.ws.addon.TaskServiceData #txt
>Proto Te0 .processKind WEB_SERVICE #txt
>Proto Te0 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <swimlaneLabel>Find task</swimlaneLabel>
        <swimlaneLabel>Find tasks by filter</swimlaneLabel>
        <swimlaneLabel>Park task</swimlaneLabel>
        <swimlaneLabel>Delegate task</swimlaneLabel>
        <swimlaneLabel>Create note</swimlaneLabel>
        <swimlaneLabel>Find notes</swimlaneLabel>
        <swimlaneLabel>Reset task</swimlaneLabel>
        <swimlaneLabel>Search tasks</swimlaneLabel>
        <swimlaneLabel>canUserResumeTask</swimlaneLabel>
        <swimlaneLabel></swimlaneLabel>
    </language>
    <swimlaneSize>128</swimlaneSize>
    <swimlaneSize>672</swimlaneSize>
    <swimlaneSize>192</swimlaneSize>
    <swimlaneSize>192</swimlaneSize>
    <swimlaneSize>192</swimlaneSize>
    <swimlaneSize>192</swimlaneSize>
    <swimlaneSize>192</swimlaneSize>
    <swimlaneSize>304</swimlaneSize>
    <swimlaneSize>192</swimlaneSize>
    <swimlaneColor gradient="true">-26113</swimlaneColor>
    <swimlaneColor gradient="true">-6710785</swimlaneColor>
    <swimlaneColor gradient="true">-26113</swimlaneColor>
    <swimlaneColor gradient="true">-6710785</swimlaneColor>
    <swimlaneColor gradient="true">-6710785</swimlaneColor>
    <swimlaneColor gradient="true">-26113</swimlaneColor>
    <swimlaneColor gradient="true">-1</swimlaneColor>
    <swimlaneColor gradient="true">-1</swimlaneColor>
    <swimlaneColor gradient="true">-1</swimlaneColor>
    <swimlaneType>LANE</swimlaneType>
    <swimlaneType>LANE</swimlaneType>
    <swimlaneType>LANE</swimlaneType>
    <swimlaneType>LANE</swimlaneType>
    <swimlaneType>LANE</swimlaneType>
    <swimlaneType>LANE</swimlaneType>
    <swimlaneType>LANE</swimlaneType>
    <swimlaneType>LANE</swimlaneType>
    <swimlaneType>LANE</swimlaneType>
    <swimlaneSpaceBefore>0</swimlaneSpaceBefore>
    <swimlaneSpaceBefore>0</swimlaneSpaceBefore>
    <swimlaneSpaceBefore>0</swimlaneSpaceBefore>
    <swimlaneSpaceBefore>0</swimlaneSpaceBefore>
    <swimlaneSpaceBefore>0</swimlaneSpaceBefore>
    <swimlaneSpaceBefore>0</swimlaneSpaceBefore>
    <swimlaneSpaceBefore>0</swimlaneSpaceBefore>
    <swimlaneSpaceBefore>0</swimlaneSpaceBefore>
    <swimlaneSpaceBefore>0</swimlaneSpaceBefore>
</elementInfo>
' #txt
>Proto Te0 -8 -8 16 16 16 26 #rect
>Proto Te0 '' #fIcon
Te0 ws0 mainOut f1 tail #connect
Te0 f1 head f0 mainIn #connect
Te0 f3 mainOut f6 tail #connect
Te0 f6 head f5 mainIn #connect
Te0 f9 mainOut f11 tail #connect
Te0 f11 head f8 mainIn #connect
Te0 f13 mainOut f16 tail #connect
Te0 f16 head f14 mainIn #connect
Te0 f19 mainOut f21 tail #connect
Te0 f21 head f20 mainIn #connect
Te0 f25 mainOut f26 tail #connect
Te0 f26 head f23 mainIn #connect
Te0 f30 mainOut f31 tail #connect
Te0 f31 head f29 mainIn #connect
Te0 f34 mainOut f36 tail #connect
Te0 f36 head f33 mainIn #connect
Te0 f38 mainOut f42 tail #connect
Te0 f42 head f41 mainIn #connect
Te0 f44 mainOut f46 tail #connect
Te0 f46 head f43 mainIn #connect
Te0 f48 mainOut f51 tail #connect
Te0 f51 head f50 mainIn #connect
Te0 f53 mainOut f57 tail #connect
Te0 f57 head f56 mainIn #connect
Te0 f58 out f60 tail #connect
Te0 f60 head f59 mainIn #connect
Te0 f59 mainOut f61 tail #connect
Te0 f61 head f35 mainIn #connect
Te0 f5 mainOut f7 tail #connect
Te0 f7 head f58 in #connect
Te0 f0 mainOut f62 tail #connect
Te0 f62 head f58 in #connect
Te0 f29 mainOut f2 tail #connect
Te0 f2 head f58 in #connect
Te0 f56 mainOut f32 tail #connect
Te0 f32 head f58 in #connect
Te0 f8 mainOut f55 tail #connect
Te0 f55 head f58 in #connect
Te0 f33 mainOut f12 tail #connect
Te0 f12 head f58 in #connect
Te0 f14 mainOut f37 tail #connect
Te0 f37 head f58 in #connect
Te0 f20 mainOut f17 tail #connect
Te0 f17 head f58 in #connect
Te0 f23 mainOut f22 tail #connect
Te0 f22 head f58 in #connect
Te0 f41 mainOut f27 tail #connect
Te0 f27 head f58 in #connect
Te0 f43 mainOut f40 tail #connect
Te0 f40 head f58 in #connect
Te0 f50 mainOut f47 tail #connect
Te0 f47 head f58 in #connect
Te0 f4 mainOut f15 tail #connect
Te0 f15 head f10 mainIn #connect
Te0 f10 mainOut f18 tail #connect
Te0 f18 head f58 in #connect
