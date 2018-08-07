[Ivy]
[>Created: Thu Jun 09 17:28:43 ICT 2016]
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
Te0 @EndWS f35 '' #zField
Te0 @StartWS f38 '' #zField
Te0 @GridStep f41 '' #zField
Te0 @PushWFArc f42 '' #zField
Te0 @GridStep f43 '' #zField
Te0 @StartWS f44 '' #zField
Te0 @PushWFArc f46 '' #zField
Te0 @StartWS f48 '' #zField
Te0 @GridStep f50 '' #zField
Te0 @PushWFArc f51 '' #zField
Te0 @Alternative f58 '' #zField
Te0 @CallSub f59 '' #zField
Te0 @PushWFArc f60 '' #zField
Te0 @PushWFArc f61 '' #zField
Te0 @PushWFArc f55 '' #zField
Te0 @PushWFArc f37 '' #zField
Te0 @PushWFArc f17 '' #zField
Te0 @PushWFArc f22 '' #zField
Te0 @PushWFArc f27 '' #zField
Te0 @PushWFArc f40 '' #zField
Te0 @PushWFArc f47 '' #zField
Te0 @GridStep f24 '' #zField
Te0 @StartWS f28 '' #zField
Te0 @PushWFArc f32 '' #zField
Te0 @PushWFArc f39 '' #zField
Te0 @GridStep f4 '' #zField
Te0 @StartWS f10 '' #zField
Te0 @PushWFArc f15 '' #zField
Te0 @PushWFArc f18 '' #zField
Te0 @GridStep f45 '' #zField
Te0 @StartWS f49 '' #zField
Te0 @PushWFArc f52 '' #zField
Te0 @PushWFArc f53 '' #zField
Te0 @GridStep f54 '' #zField
Te0 @StartWS f56 '' #zField
Te0 @Split f0 '' #zField
Te0 @PushWFArc f1 '' #zField
Te0 @PushWFArc f2 '' #zField
Te0 @Join f3 '' #zField
Te0 @SJArc f5 '' #zField
Te0 @PushWFArc f6 '' #zField
Te0 @GridStep f7 '' #zField
Te0 @PushWFArc f12 '' #zField
Te0 @GridStep f29 '' #zField
Te0 @PushWFArc f30 '' #zField
Te0 @GridStep f31 '' #zField
Te0 @PushWFArc f33 '' #zField
Te0 @SJArc f34 '' #zField
Te0 @SJArc f36 '' #zField
Te0 @SJArc f57 '' #zField
Te0 @StartWS f62 '' #zField
Te0 @GridStep f63 '' #zField
Te0 @PushWFArc f64 '' #zField
Te0 @PushWFArc f65 '' #zField
>Proto Te0 Te0 TaskService #zField
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
Te0 f8 678 124 36 24 20 -2 #rect
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
Te0 f9 683 51 26 26 14 0 #rect
Te0 f9 @|StartWSIcon #fIcon
Te0 f11 expr out #txt
Te0 f11 696 77 696 124 #arcP
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
Te0 f13 867 51 26 26 14 0 #rect
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
Te0 f14 862 124 36 24 20 -2 #rect
Te0 f14 @|StepIcon #fIcon
Te0 f16 expr out #txt
Te0 f16 880 77 880 124 #arcP
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
Te0 f19 1067 51 26 26 14 0 #rect
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
Te0 f20 1062 124 36 24 20 -2 #rect
Te0 f20 @|StepIcon #fIcon
Te0 f21 expr out #txt
Te0 f21 1080 77 1080 124 #arcP
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
Te0 f23 1254 124 36 24 20 -2 #rect
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
Te0 f25 callSignature findTaskNotes(Long) #txt
Te0 f25 useUserDefinedException false #txt
Te0 f25 taskData TaskTriggered.PRI=2 #txt
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
        <name>findTaskNotes</name>
        <nameStyle>13,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Te0 f25 @C|.responsibility Everybody #txt
Te0 f25 1259 51 26 26 14 0 #rect
Te0 f25 @|StartWSIcon #fIcon
Te0 f26 expr out #txt
Te0 f26 1272 77 1272 124 #arcP
Te0 f35 type ch.ivy.ws.addon.TaskServiceData #txt
Te0 f35 1259 435 26 26 14 0 #rect
Te0 f35 @|EndWSIcon #fIcon
Te0 f38 inParamDecl '<java.lang.String currentUserName,java.lang.String serverUrl,java.lang.Long id> param;' #txt
Te0 f38 inParamTable 'out.ivyTask.id=param.id;
out.serverUrl=param.serverUrl;
out.user=param.currentUserName;
' #txt
Te0 f38 outParamDecl '<List<ch.ivy.ws.addon.WSException> errors,ch.ivy.ws.addon.types.IvyTask ivyTask> result;
' #txt
Te0 f38 outParamTable 'result.errors=in.errors;
result.ivyTask=in.ivyTask;
' #txt
Te0 f38 actionDecl 'ch.ivy.ws.addon.TaskServiceData out;
' #txt
Te0 f38 callSignature resetTask(String,String,Long) #txt
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
        <name>resetTask(String,String,Long)</name>
        <nameStyle>29,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Te0 f38 @C|.responsibility Everybody #txt
Te0 f38 1451 51 26 26 14 0 #rect
Te0 f38 @|StartWSIcon #fIcon
Te0 f41 actionDecl 'ch.ivy.ws.addon.TaskServiceData out;
' #txt
Te0 f41 actionTable 'out=in;
' #txt
Te0 f41 actionCode 'import ch.ivy.ws.addon.bo.TaskServiceResult;
import ch.ivy.ws.addon.WsServiceFactory;
import ch.ivy.ws.addon.WSException;

try{
	TaskServiceResult tsResult = WsServiceFactory.getTaskService().resetTask(in.user, in.ivyTask.id, in.serverUrl	);
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
Te0 f41 1446 124 36 24 20 -2 #rect
Te0 f41 @|StepIcon #fIcon
Te0 f42 expr out #txt
Te0 f42 1464 77 1464 124 #arcP
Te0 f43 actionDecl 'ch.ivy.ws.addon.TaskServiceData out;
' #txt
Te0 f43 actionTable 'out=in;
' #txt
Te0 f43 actionCode 'import ch.ivy.ws.addon.bo.TaskServiceResult;
import ch.ivy.ws.addon.WsServiceFactory;
import ch.ivy.ws.addon.WSException;

try{
	TaskServiceResult tsResult = WsServiceFactory.getTaskService().findTasksByCriteria(in.taskSearchCriteria, in.serverId, in.serverUrl, in.startIndex, in.count);
	in.ivyTasks = tsResult.getTasks();
	in.allIvyTasks = tsResult.getAllTasks();	
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
Te0 f43 46 124 36 24 20 -2 #rect
Te0 f43 @|StepIcon #fIcon
Te0 f44 inParamDecl '<java.lang.Long serverId,java.lang.Integer startIndex,java.lang.Integer count,java.lang.String serverUrl,ch.ivy.ws.addon.service.TaskSearchCriteria taskSearchCriteria> param;' #txt
Te0 f44 inParamTable 'out.count=param.count;
out.serverId=param.serverId;
out.serverUrl=param.serverUrl;
out.startIndex=param.startIndex;
out.taskSearchCriteria=param.taskSearchCriteria;
' #txt
Te0 f44 outParamDecl '<List<ch.ivy.ws.addon.types.IvyTask> allIvyTasks,List<ch.ivy.ws.addon.WSException> errors,List<ch.ivy.ws.addon.types.IvyTask> ivyTasks> result;
' #txt
Te0 f44 outParamTable 'result.allIvyTasks=in.allIvyTasks;
result.errors=in.errors;
result.ivyTasks=in.ivyTasks;
' #txt
Te0 f44 actionDecl 'ch.ivy.ws.addon.TaskServiceData out;
' #txt
Te0 f44 callSignature findTasksByCriteria(Long,Integer,Integer,String,ch.ivy.ws.addon.service.TaskSearchCriteria) #txt
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
        <nameStyle>46,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Te0 f44 @C|.responsibility Everybody #txt
Te0 f44 51 51 26 26 14 0 #rect
Te0 f44 @|StartWSIcon #fIcon
Te0 f46 expr out #txt
Te0 f46 64 77 64 124 #arcP
Te0 f48 inParamDecl '<java.lang.Long id,java.lang.String userName> param;' #txt
Te0 f48 inParamTable 'out.ivyTask.id=param.id;
out.user=param.userName;
' #txt
Te0 f48 outParamDecl '<java.lang.String workerUserName,java.lang.Boolean canUserResumeTask,List<ch.ivy.ws.addon.WSException> errors> result;
' #txt
Te0 f48 outParamTable 'result.workerUserName=in.workerUserName;
result.canUserResumeTask=in.canUserResumeTask;
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
Te0 f48 1643 51 26 26 14 0 #rect
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
	in.workerUserName = tsResult.workerUserName;	
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
Te0 f50 1638 116 36 24 20 -2 #rect
Te0 f50 @|StepIcon #fIcon
Te0 f51 expr out #txt
Te0 f51 1656 77 1656 116 #arcP
Te0 f58 type ch.ivy.ws.addon.TaskServiceData #txt
Te0 f58 1258 298 28 28 14 0 #rect
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
Te0 f59 1254 364 36 24 20 -2 #rect
Te0 f59 @|CallSubIcon #fIcon
Te0 f60 expr in #txt
Te0 f60 1272 326 1272 364 #arcP
Te0 f61 expr out #txt
Te0 f61 1272 388 1272 435 #arcP
Te0 f55 expr out #txt
Te0 f55 696 148 1258 312 #arcP
Te0 f55 1 696 312 #addKink
Te0 f55 1 0.9805359095139481 0 0 #arcLabel
Te0 f37 expr out #txt
Te0 f37 880 148 1258 312 #arcP
Te0 f37 1 880 312 #addKink
Te0 f37 1 0.9841320950935127 0 0 #arcLabel
Te0 f17 expr out #txt
Te0 f17 1080 148 1258 312 #arcP
Te0 f17 1 1080 312 #addKink
Te0 f17 1 0.98579404476964 0 0 #arcLabel
Te0 f22 expr out #txt
Te0 f22 1272 148 1272 298 #arcP
Te0 f22 0 0.5354082826661823 0 0 #arcLabel
Te0 f27 expr out #txt
Te0 f27 1464 148 1286 312 #arcP
Te0 f27 1 1464 312 #addKink
Te0 f27 1 0.4533394530682278 0 0 #arcLabel
Te0 f40 expr out #txt
Te0 f40 64 148 1258 312 #arcP
Te0 f40 1 64 312 #addKink
Te0 f40 1 0.4566039884861746 0 0 #arcLabel
Te0 f47 expr out #txt
Te0 f47 1656 140 1286 312 #arcP
Te0 f47 1 1656 312 #addKink
Te0 f47 1 0.4649668975722141 0 0 #arcLabel
Te0 f24 actionDecl 'ch.ivy.ws.addon.TaskServiceData out;
' #txt
Te0 f24 actionTable 'out=in;
' #txt
Te0 f24 actionCode 'import ch.ivy.ws.addon.bo.TaskServiceResult;
import ch.ivy.ws.addon.WsServiceFactory;
import ch.ivy.ws.addon.WSException;

try{
	TaskServiceResult tsResult = WsServiceFactory.getTaskService().countTasksByCriteria(in.taskSearchCriteria);
	in.taskCount = tsResult.getTaskCount();
	in.errors = tsResult.getErrors();
}catch(WSException e){
	in.errors.add(e);
}' #txt
Te0 f24 type ch.ivy.ws.addon.TaskServiceData #txt
Te0 f24 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>countTasksByCriteria</name>
        <nameStyle>20,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Te0 f24 1822 124 36 24 20 -2 #rect
Te0 f24 @|StepIcon #fIcon
Te0 f28 inParamDecl '<ch.ivy.ws.addon.service.TaskSearchCriteria taskSearchCriteria> param;' #txt
Te0 f28 inParamTable 'out.taskSearchCriteria=param.taskSearchCriteria;
' #txt
Te0 f28 outParamDecl '<java.lang.Long taskCount,List<ch.ivy.ws.addon.WSException> errors> result;
' #txt
Te0 f28 outParamTable 'result.taskCount=in.taskCount;
result.errors=in.errors;
' #txt
Te0 f28 actionDecl 'ch.ivy.ws.addon.TaskServiceData out;
' #txt
Te0 f28 callSignature countTasksByCriteria(ch.ivy.ws.addon.service.TaskSearchCriteria) #txt
Te0 f28 useUserDefinedException false #txt
Te0 f28 taskData TaskTriggered.PRI=2 #txt
Te0 f28 taskAndCaseSetupAction 'import ch.ivyteam.ivy.workflow.TaskUpdateDefinition;
ch.ivyteam.ivy.workflow.TaskUpdateDefinition taskUpdDef = new ch.ivyteam.ivy.workflow.TaskUpdateDefinition();
import ch.ivyteam.ivy.request.impl.DefaultCalendarProxy;
DefaultCalendarProxy calendarProxy = ivy.cal as DefaultCalendarProxy;
taskUpdDef.setPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
taskUpdDef.setExpiryActivator("Everybody");
taskUpdDef.setExpiryPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
engine.updateCurrentTask(taskUpdDef);
' #txt
Te0 f28 type ch.ivy.ws.addon.TaskServiceData #txt
Te0 f28 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>countTasksByCriteria
(TaskSearchCriteria)</name>
        <nameStyle>41,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Te0 f28 @C|.responsibility Everybody #txt
Te0 f28 1827 51 26 26 13 0 #rect
Te0 f28 @|StartWSIcon #fIcon
Te0 f32 expr out #txt
Te0 f32 1840 77 1840 124 #arcP
Te0 f39 expr out #txt
Te0 f39 1840 148 1286 312 #arcP
Te0 f39 1 1840 312 #addKink
Te0 f39 1 0.4731520316611164 0 0 #arcLabel
Te0 f4 actionDecl 'ch.ivy.ws.addon.TaskServiceData out;
' #txt
Te0 f4 actionTable 'out=in;
' #txt
Te0 f4 actionCode 'import ch.ivy.ws.addon.bo.TaskServiceResult;
import ch.ivy.ws.addon.WsServiceFactory;
import ch.ivy.ws.addon.WSException;

try{
	TaskServiceResult tsResult = WsServiceFactory.getTaskService().findCategories(in.taskSearchCriteria.involvedUsername, in.taskSearchCriteria.involvedApplications);
	in.categories = tsResult.getCategories();
	in.errors = tsResult.getErrors();
}catch(WSException e){
	in.errors.add(e);
}' #txt
Te0 f4 type ch.ivy.ws.addon.TaskServiceData #txt
Te0 f4 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>findCategories</name>
        <nameStyle>14,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Te0 f4 366 124 36 24 20 -2 #rect
Te0 f4 @|StepIcon #fIcon
Te0 f10 inParamDecl '<java.lang.String userName,List<java.lang.String> apps> param;' #txt
Te0 f10 inParamTable 'out.taskSearchCriteria.involvedApplications=param.apps;
out.taskSearchCriteria.involvedUsername=param.userName;
' #txt
Te0 f10 outParamDecl '<java.util.List<java.lang.String> categories,List<ch.ivy.ws.addon.WSException> errors> result;
' #txt
Te0 f10 outParamTable 'result.categories=in.categories;
result.errors=in.errors;
' #txt
Te0 f10 actionDecl 'ch.ivy.ws.addon.TaskServiceData out;
' #txt
Te0 f10 callSignature findCategories(String,List<String>) #txt
Te0 f10 useUserDefinedException false #txt
Te0 f10 taskData TaskTriggered.PRI=2 #txt
Te0 f10 taskAndCaseSetupAction 'import ch.ivyteam.ivy.workflow.TaskUpdateDefinition;
ch.ivyteam.ivy.workflow.TaskUpdateDefinition taskUpdDef = new ch.ivyteam.ivy.workflow.TaskUpdateDefinition();
import ch.ivyteam.ivy.request.impl.DefaultCalendarProxy;
DefaultCalendarProxy calendarProxy = ivy.cal as DefaultCalendarProxy;
taskUpdDef.setPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
taskUpdDef.setExpiryActivator("Everybody");
taskUpdDef.setExpiryPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
engine.updateCurrentTask(taskUpdDef);
' #txt
Te0 f10 type ch.ivy.ws.addon.TaskServiceData #txt
Te0 f10 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>findCategories(String,List&lt;String&gt;)</name>
    </language>
</elementInfo>
' #txt
Te0 f10 @C|.responsibility Everybody #txt
Te0 f10 371 51 26 26 13 0 #rect
Te0 f10 @|StartWSIcon #fIcon
Te0 f15 expr out #txt
Te0 f15 384 77 384 124 #arcP
Te0 f18 expr out #txt
Te0 f18 384 148 1258 312 #arcP
Te0 f18 1 384 312 #addKink
Te0 f18 1 0.4763779527559055 0 0 #arcLabel
Te0 f45 actionDecl 'ch.ivy.ws.addon.TaskServiceData out;
' #txt
Te0 f45 actionTable 'out=in;
' #txt
Te0 f45 actionCode 'import ch.ivy.ws.addon.bo.TaskServiceResult;
import ch.ivy.ws.addon.WsServiceFactory;
import ch.ivy.ws.addon.WSException;

try{
	TaskServiceResult tsResult = WsServiceFactory.getTaskService().analyzePriorityStatistic(in.taskSearchCriteria.involvedUsername, in.taskSearchCriteria.involvedApplications);
	in.priorityStatistic = tsResult.getPriorityStatistic();
	in.errors = tsResult.getErrors();
}catch(WSException e){
	in.errors.add(e);
}' #txt
Te0 f45 type ch.ivy.ws.addon.TaskServiceData #txt
Te0 f45 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>analyze priority</name>
        <nameStyle>16,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Te0 f45 2062 124 36 24 20 -2 #rect
Te0 f45 @|StepIcon #fIcon
Te0 f49 inParamDecl '<java.lang.String userName,List<java.lang.String> apps> param;' #txt
Te0 f49 inParamTable 'out.taskSearchCriteria.involvedApplications=param.apps;
out.taskSearchCriteria.involvedUsername=param.userName;
' #txt
Te0 f49 outParamDecl '<ch.ivy.ws.addon.types.PriorityStatistic priorityStatistic,List<ch.ivy.ws.addon.WSException> errors> result;
' #txt
Te0 f49 outParamTable 'result.priorityStatistic=in.priorityStatistic;
result.errors=in.errors;
' #txt
Te0 f49 actionDecl 'ch.ivy.ws.addon.TaskServiceData out;
' #txt
Te0 f49 callSignature analyzePriorityStatistic(String,List<String>) #txt
Te0 f49 useUserDefinedException false #txt
Te0 f49 taskData TaskTriggered.PRI=2 #txt
Te0 f49 taskAndCaseSetupAction 'import ch.ivyteam.ivy.workflow.TaskUpdateDefinition;
ch.ivyteam.ivy.workflow.TaskUpdateDefinition taskUpdDef = new ch.ivyteam.ivy.workflow.TaskUpdateDefinition();
import ch.ivyteam.ivy.request.impl.DefaultCalendarProxy;
DefaultCalendarProxy calendarProxy = ivy.cal as DefaultCalendarProxy;
taskUpdDef.setPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
taskUpdDef.setExpiryActivator("Everybody");
taskUpdDef.setExpiryPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
engine.updateCurrentTask(taskUpdDef);
' #txt
Te0 f49 type ch.ivy.ws.addon.TaskServiceData #txt
Te0 f49 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>analyzePriorityStatistic(String,List&lt;String&gt;)</name>
        <nameStyle>45,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Te0 f49 @C|.responsibility Everybody #txt
Te0 f49 2067 51 26 26 13 0 #rect
Te0 f49 @|StartWSIcon #fIcon
Te0 f52 expr out #txt
Te0 f52 2080 77 2080 124 #arcP
Te0 f53 expr out #txt
Te0 f53 2080 148 1286 312 #arcP
Te0 f53 1 2080 312 #addKink
Te0 f53 1 0.47967086156824784 0 0 #arcLabel
Te0 f54 actionDecl 'ch.ivy.ws.addon.TaskServiceData out;
' #txt
Te0 f54 actionTable 'out=in;
' #txt
Te0 f54 actionCode 'import ch.ivy.ws.addon.types.NumberOfExpiryTasks;
import ch.ivy.ws.addon.util.JavaDates;
import ch.ivy.ws.addon.WsServiceFactory;
import ch.ivy.ws.addon.WSException;

try {
	NumberOfExpiryTasks result = WsServiceFactory.getTaskService().countExpiryTasksByDate(in.taskSearchCriteria.involvedUsername, in.taskSearchCriteria.involvedApplications, JavaDates.today());
	in.expireToday = result.getNumberOfExpiryTasks();
	in.errors.addAll(result.getErrors());
}catch(WSException e){
	in.errors.add(e);
}' #txt
Te0 f54 type ch.ivy.ws.addon.TaskServiceData #txt
Te0 f54 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>analyze expiry
today</name>
        <nameStyle>20,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Te0 f54 2222 180 36 24 20 -2 #rect
Te0 f54 @|StepIcon #fIcon
Te0 f56 inParamDecl '<java.lang.String userName,List<java.lang.String> apps> param;' #txt
Te0 f56 inParamTable 'out.taskSearchCriteria.involvedApplications=param.apps;
out.taskSearchCriteria.involvedUsername=param.userName;
' #txt
Te0 f56 outParamDecl '<ch.ivy.ws.addon.types.ExpiryStatistic expiryStatistic,List<ch.ivy.ws.addon.WSException> errors> result;' #txt
Te0 f56 outParamTable 'result.expiryStatistic=in.expiryStatistic;
result.errors=in.errors;
' #txt
Te0 f56 actionDecl 'ch.ivy.ws.addon.TaskServiceData out;
' #txt
Te0 f56 callSignature analyzeExpiryStatistic(String,List<String>) #txt
Te0 f56 useUserDefinedException false #txt
Te0 f56 taskData TaskTriggered.PRI=2 #txt
Te0 f56 taskAndCaseSetupAction 'import ch.ivyteam.ivy.workflow.TaskUpdateDefinition;
ch.ivyteam.ivy.workflow.TaskUpdateDefinition taskUpdDef = new ch.ivyteam.ivy.workflow.TaskUpdateDefinition();
import ch.ivyteam.ivy.request.impl.DefaultCalendarProxy;
DefaultCalendarProxy calendarProxy = ivy.cal as DefaultCalendarProxy;
taskUpdDef.setPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
taskUpdDef.setExpiryActivator("Everybody");
taskUpdDef.setExpiryPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
engine.updateCurrentTask(taskUpdDef);
' #txt
Te0 f56 type ch.ivy.ws.addon.TaskServiceData #txt
Te0 f56 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>analyzeExpiryStatistic(String,List&lt;String&gt;)</name>
        <nameStyle>43,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Te0 f56 @C|.responsibility Everybody #txt
Te0 f56 2419 51 26 26 13 0 #rect
Te0 f56 @|StartWSIcon #fIcon
Te0 f0 actionDecl 'ch.ivy.ws.addon.TaskServiceData out1;
ch.ivy.ws.addon.TaskServiceData out2;
ch.ivy.ws.addon.TaskServiceData out3;
ch.ivy.ws.addon.TaskServiceData out4;
' #txt
Te0 f0 actionTable 'out1=in;
out2=in;
out3=in;
out4=in;
' #txt
Te0 f0 type ch.ivy.ws.addon.TaskServiceData #txt
Te0 f0 2418 114 28 28 14 0 #rect
Te0 f0 @|ThreadIcon #fIcon
Te0 f1 expr out #txt
Te0 f1 2432 77 2432 114 #arcP
Te0 f2 expr out1 #txt
Te0 f2 2418 128 2240 180 #arcP
Te0 f2 1 2240 128 #addKink
Te0 f2 0 0.7790944249033374 0 0 #arcLabel
Te0 f3 actionDecl 'ch.ivy.ws.addon.TaskServiceData out;
' #txt
Te0 f3 actionTable 'out=in1;
out.errors=in1.errors.addAll(in2.errors).addAll(in3.errors).addAll(in4.errors);
out.expiryStatistic.in2Days=in3.expireIn2Days;
out.expiryStatistic.in3Days=in4.expireIn3Days;
out.expiryStatistic.today=in1.expireToday;
out.expiryStatistic.tomorrow=in2.expireTomorrow;
' #txt
Te0 f3 2418 242 28 28 14 0 #rect
Te0 f3 @|JoinIcon #fIcon
Te0 f5 expr out #txt
Te0 f5 type ch.ivy.ws.addon.TaskServiceData #txt
Te0 f5 var in1 #txt
Te0 f5 2240 204 2418 256 #arcP
Te0 f5 1 2240 256 #addKink
Te0 f5 1 0.1931044146498145 0 0 #arcLabel
Te0 f6 expr out #txt
Te0 f6 2432 270 1286 312 #arcP
Te0 f6 1 2432 312 #addKink
Te0 f6 1 0.4821580288870008 0 0 #arcLabel
Te0 f7 actionDecl 'ch.ivy.ws.addon.TaskServiceData out;
' #txt
Te0 f7 actionTable 'out=in;
' #txt
Te0 f7 actionCode 'import ch.ivy.ws.addon.types.NumberOfExpiryTasks;
import ch.ivy.ws.addon.util.JavaDates;
import ch.ivy.ws.addon.WsServiceFactory;
import ch.ivy.ws.addon.WSException;

try {
	NumberOfExpiryTasks result = WsServiceFactory.getTaskService().countExpiryTasksByDate(in.taskSearchCriteria.involvedUsername, in.taskSearchCriteria.involvedApplications, JavaDates.plusDays(JavaDates.today(), 1));
	in.expireTomorrow = result.getNumberOfExpiryTasks();
	in.errors = result.getErrors();
}catch(WSException e){
	in.errors.add(e);
}' #txt
Te0 f7 type ch.ivy.ws.addon.TaskServiceData #txt
Te0 f7 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>analyze expiry
tomorrow</name>
        <nameStyle>23,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Te0 f7 2350 180 36 24 20 -2 #rect
Te0 f7 @|StepIcon #fIcon
Te0 f12 expr out2 #txt
Te0 f12 2425 135 2380 180 #arcP
Te0 f29 actionDecl 'ch.ivy.ws.addon.TaskServiceData out;
' #txt
Te0 f29 actionTable 'out=in;
' #txt
Te0 f29 actionCode 'import ch.ivy.ws.addon.types.NumberOfExpiryTasks;
import ch.ivy.ws.addon.util.JavaDates;
import ch.ivy.ws.addon.WsServiceFactory;
import ch.ivy.ws.addon.WSException;

try {
	NumberOfExpiryTasks result = WsServiceFactory.getTaskService().countExpiryTasksByDate(in.taskSearchCriteria.involvedUsername, in.taskSearchCriteria.involvedApplications, JavaDates.plusDays(JavaDates.today(), 2));
	in.expireIn2Days = result.getNumberOfExpiryTasks();
	in.errors.addAll(result.getErrors());
}catch(WSException e){
	in.errors.add(e);
}' #txt
Te0 f29 type ch.ivy.ws.addon.TaskServiceData #txt
Te0 f29 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>analyze expiry
in 2 days</name>
        <nameStyle>24,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Te0 f29 2494 180 36 24 20 -2 #rect
Te0 f29 @|StepIcon #fIcon
Te0 f30 expr out3 #txt
Te0 f30 2440 134 2497 180 #arcP
Te0 f31 actionDecl 'ch.ivy.ws.addon.TaskServiceData out;
' #txt
Te0 f31 actionTable 'out=in;
' #txt
Te0 f31 actionCode 'import ch.ivy.ws.addon.types.NumberOfExpiryTasks;
import ch.ivy.ws.addon.util.JavaDates;
import ch.ivy.ws.addon.WsServiceFactory;
import ch.ivy.ws.addon.WSException;

try {
	NumberOfExpiryTasks result = WsServiceFactory.getTaskService().countExpiryTasksByDate(in.taskSearchCriteria.involvedUsername, in.taskSearchCriteria.involvedApplications, JavaDates.plusDays(JavaDates.today(), 3));
	in.expireIn3Days = result.getNumberOfExpiryTasks();
	in.errors.addAll(result.getErrors());
}catch(WSException e){
	in.errors.add(e);
}' #txt
Te0 f31 type ch.ivy.ws.addon.TaskServiceData #txt
Te0 f31 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>analyze expiry
in 3 days</name>
        <nameStyle>24,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Te0 f31 2638 180 36 24 20 -2 #rect
Te0 f31 @|StepIcon #fIcon
Te0 f33 expr out4 #txt
Te0 f33 2446 128 2656 180 #arcP
Te0 f33 1 2656 128 #addKink
Te0 f33 0 0.6560480922534767 0 0 #arcLabel
Te0 f34 expr out #txt
Te0 f34 type ch.ivy.ws.addon.TaskServiceData #txt
Te0 f34 var in2 #txt
Te0 f34 2380 204 2425 249 #arcP
Te0 f36 expr out #txt
Te0 f36 type ch.ivy.ws.addon.TaskServiceData #txt
Te0 f36 var in3 #txt
Te0 f36 2497 204 2440 250 #arcP
Te0 f57 expr out #txt
Te0 f57 type ch.ivy.ws.addon.TaskServiceData #txt
Te0 f57 var in4 #txt
Te0 f57 2656 204 2446 256 #arcP
Te0 f57 1 2656 256 #addKink
Te0 f57 1 0.34395190774652323 0 0 #arcLabel
Te0 f62 inParamDecl '<ch.ivy.ws.addon.types.IvyTask task> param;' #txt
Te0 f62 inParamTable 'out.ivyTask=param.task;
' #txt
Te0 f62 outParamDecl '<List<ch.ivy.ws.addon.WSException> errors> result;' #txt
Te0 f62 outParamTable 'result.errors=in.errors;
' #txt
Te0 f62 actionDecl 'ch.ivy.ws.addon.TaskServiceData out;
' #txt
Te0 f62 callSignature save(ch.ivy.ws.addon.types.IvyTask) #txt
Te0 f62 useUserDefinedException false #txt
Te0 f62 taskData TaskTriggered.PRI=2 #txt
Te0 f62 taskAndCaseSetupAction 'import ch.ivyteam.ivy.workflow.TaskUpdateDefinition;
ch.ivyteam.ivy.workflow.TaskUpdateDefinition taskUpdDef = new ch.ivyteam.ivy.workflow.TaskUpdateDefinition();
import ch.ivyteam.ivy.request.impl.DefaultCalendarProxy;
DefaultCalendarProxy calendarProxy = ivy.cal as DefaultCalendarProxy;
taskUpdDef.setPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
taskUpdDef.setExpiryActivator("Everybody");
taskUpdDef.setExpiryPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
engine.updateCurrentTask(taskUpdDef);
' #txt
Te0 f62 type ch.ivy.ws.addon.TaskServiceData #txt
Te0 f62 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>save(IvyTask)</name>
    </language>
</elementInfo>
' #txt
Te0 f62 @C|.responsibility Everybody #txt
Te0 f62 2931 51 26 26 14 0 #rect
Te0 f62 @|StartWSIcon #fIcon
Te0 f63 actionDecl 'ch.ivy.ws.addon.TaskServiceData out;
' #txt
Te0 f63 actionTable 'out=in;
' #txt
Te0 f63 actionCode 'import ch.ivy.ws.addon.WSException;
import ch.ivy.ws.addon.WsServiceFactory;

try {
	WsServiceFactory.getTaskService().save(in.ivyTask);
} catch(WSException e) {
	in.errors.add(e);
}' #txt
Te0 f63 type ch.ivy.ws.addon.TaskServiceData #txt
Te0 f63 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>save task</name>
        <nameStyle>9,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Te0 f63 2926 164 36 24 20 -2 #rect
Te0 f63 @|StepIcon #fIcon
Te0 f64 expr out #txt
Te0 f64 2944 77 2944 164 #arcP
Te0 f65 expr out #txt
Te0 f65 2944 188 1286 312 #arcP
Te0 f65 1 2944 312 #addKink
Te0 f65 1 0.4626055488540411 0 0 #arcLabel
>Proto Te0 .webServiceName ch.ivy.ws.addon.TaskService #txt
>Proto Te0 .authenticationType 'HTTP Basic' #txt
>Proto Te0 .type ch.ivy.ws.addon.TaskServiceData #txt
>Proto Te0 .processKind WEB_SERVICE #txt
>Proto Te0 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <swimlaneLabel>Find tasks by criteria</swimlaneLabel>
        <swimlaneLabel>Find categories</swimlaneLabel>
        <swimlaneLabel>Park task</swimlaneLabel>
        <swimlaneLabel>Delegate task</swimlaneLabel>
        <swimlaneLabel>Create note</swimlaneLabel>
        <swimlaneLabel>Find notes</swimlaneLabel>
        <swimlaneLabel>Reset task</swimlaneLabel>
        <swimlaneLabel>canUserResumeTask</swimlaneLabel>
        <swimlaneLabel>Count tasks
</swimlaneLabel>
        <swimlaneLabel>Statistic
</swimlaneLabel>
        <swimlaneLabel>Save
</swimlaneLabel>
        <swimlaneLabel></swimlaneLabel>
    </language>
    <swimlaneSize>320</swimlaneSize>
    <swimlaneSize>288</swimlaneSize>
    <swimlaneSize>192</swimlaneSize>
    <swimlaneSize>192</swimlaneSize>
    <swimlaneSize>192</swimlaneSize>
    <swimlaneSize>192</swimlaneSize>
    <swimlaneSize>192</swimlaneSize>
    <swimlaneSize>192</swimlaneSize>
    <swimlaneSize>232</swimlaneSize>
    <swimlaneSize>824</swimlaneSize>
    <swimlaneSize>264</swimlaneSize>
    <swimlaneColor gradient="true">-26113</swimlaneColor>
    <swimlaneColor gradient="true">-6710785</swimlaneColor>
    <swimlaneColor gradient="true">-26113</swimlaneColor>
    <swimlaneColor gradient="true">-6710785</swimlaneColor>
    <swimlaneColor gradient="true">-6710785</swimlaneColor>
    <swimlaneColor gradient="true">-26113</swimlaneColor>
    <swimlaneColor gradient="true">-1</swimlaneColor>
    <swimlaneColor gradient="true">-1</swimlaneColor>
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
    <swimlaneSpaceBefore>0</swimlaneSpaceBefore>
    <swimlaneSpaceBefore>0</swimlaneSpaceBefore>
</elementInfo>
' #txt
>Proto Te0 -8 -8 16 16 16 26 #rect
>Proto Te0 '' #fIcon
Te0 f9 mainOut f11 tail #connect
Te0 f11 head f8 mainIn #connect
Te0 f13 mainOut f16 tail #connect
Te0 f16 head f14 mainIn #connect
Te0 f19 mainOut f21 tail #connect
Te0 f21 head f20 mainIn #connect
Te0 f25 mainOut f26 tail #connect
Te0 f26 head f23 mainIn #connect
Te0 f38 mainOut f42 tail #connect
Te0 f42 head f41 mainIn #connect
Te0 f44 mainOut f46 tail #connect
Te0 f46 head f43 mainIn #connect
Te0 f48 mainOut f51 tail #connect
Te0 f51 head f50 mainIn #connect
Te0 f58 out f60 tail #connect
Te0 f60 head f59 mainIn #connect
Te0 f59 mainOut f61 tail #connect
Te0 f61 head f35 mainIn #connect
Te0 f8 mainOut f55 tail #connect
Te0 f55 head f58 in #connect
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
Te0 f28 mainOut f32 tail #connect
Te0 f32 head f24 mainIn #connect
Te0 f24 mainOut f39 tail #connect
Te0 f39 head f58 in #connect
Te0 f10 mainOut f15 tail #connect
Te0 f15 head f4 mainIn #connect
Te0 f4 mainOut f18 tail #connect
Te0 f18 head f58 in #connect
Te0 f49 mainOut f52 tail #connect
Te0 f52 head f45 mainIn #connect
Te0 f45 mainOut f53 tail #connect
Te0 f53 head f58 in #connect
Te0 f56 mainOut f1 tail #connect
Te0 f1 head f0 in #connect
Te0 f0 out f2 tail #connect
Te0 f2 head f54 mainIn #connect
Te0 f54 mainOut f5 tail #connect
Te0 f5 head f3 in #connect
Te0 f3 mainOut f6 tail #connect
Te0 f6 head f58 in #connect
Te0 f0 out f12 tail #connect
Te0 f12 head f7 mainIn #connect
Te0 f0 out f30 tail #connect
Te0 f30 head f29 mainIn #connect
Te0 f0 out f33 tail #connect
Te0 f33 head f31 mainIn #connect
Te0 f7 mainOut f34 tail #connect
Te0 f34 head f3 in #connect
Te0 f29 mainOut f36 tail #connect
Te0 f36 head f3 in #connect
Te0 f31 mainOut f57 tail #connect
Te0 f57 head f3 in #connect
Te0 f62 mainOut f64 tail #connect
Te0 f64 head f63 mainIn #connect
Te0 f63 mainOut f65 tail #connect
Te0 f65 head f58 in #connect
