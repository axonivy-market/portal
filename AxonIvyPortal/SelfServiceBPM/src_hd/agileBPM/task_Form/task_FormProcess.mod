[Ivy]
[>Created: Tue Nov 24 10:47:01 CET 2015]
1492E077AA2879D3 3.18 #module
>Proto >Proto Collection #zClass
ts0 task_FormProcess Big #zClass
ts0 RD #cInfo
ts0 #process
ts0 @TextInP .ui2RdDataAction .ui2RdDataAction #zField
ts0 @TextInP .rdData2UIAction .rdData2UIAction #zField
ts0 @TextInP .resExport .resExport #zField
ts0 @TextInP .type .type #zField
ts0 @TextInP .processKind .processKind #zField
ts0 @AnnotationInP-0n ai ai #zField
ts0 @MessageFlowInP-0n messageIn messageIn #zField
ts0 @MessageFlowOutP-0n messageOut messageOut #zField
ts0 @TextInP .xml .xml #zField
ts0 @TextInP .responsibility .responsibility #zField
ts0 @RichDialogInitStart f0 '' #zField
ts0 @RichDialogProcessEnd f1 '' #zField
ts0 @RichDialogProcessStart f3 '' #zField
ts0 @RichDialogEnd f4 '' #zField
ts0 @RichDialogProcessStart f6 '' #zField
ts0 @GridStep f9 '' #zField
ts0 @PushWFArc f10 '' #zField
ts0 @PushWFArc f5 '' #zField
ts0 @GridStep f11 '' #zField
ts0 @PushWFArc f7 '' #zField
ts0 @GridStep f8 '' #zField
ts0 @RichDialogProcessStart f12 '' #zField
ts0 @PushWFArc f14 '' #zField
ts0 @GridStep f16 '' #zField
ts0 @PushWFArc f17 '' #zField
ts0 @PushWFArc f13 '' #zField
ts0 @RichDialogMethodStart f20 '' #zField
ts0 @GridStep f21 '' #zField
ts0 @PushWFArc f22 '' #zField
ts0 @PushWFArc f23 '' #zField
ts0 @GridStep f37 '' #zField
ts0 @RichDialogProcessStart f34 '' #zField
ts0 @RichDialogProcessEnd f35 '' #zField
ts0 @PushWFArc f38 '' #zField
ts0 @PushWFArc f36 '' #zField
ts0 @GridStep f15 '' #zField
ts0 @PushWFArc f26 '' #zField
ts0 @PushWFArc f2 '' #zField
ts0 @RichDialogProcessEnd f24 '' #zField
ts0 @GridStep f28 '' #zField
ts0 @RichDialogProcessStart f29 '' #zField
ts0 @PushWFArc f30 '' #zField
ts0 @RichDialogMethodStart f33 '' #zField
ts0 @RichDialogProcessEnd f39 '' #zField
ts0 @PushWFArc f32 '' #zField
ts0 @PushWFArc f31 '' #zField
ts0 @GridStep f27 '' #zField
ts0 @PushWFArc f25 '' #zField
ts0 @PushWFArc f19 '' #zField
ts0 @GridStep f18 '' #zField
ts0 @PushWFArc f40 '' #zField
ts0 @PushWFArc f42 '' #zField
ts0 @GridStep f41 '' #zField
>Proto ts0 ts0 task_FormProcess #zField
ts0 f0 guid 1492E077AC02C00B #txt
ts0 f0 type agileBPM.task_Form.task_FormData #txt
ts0 f0 method start(selfServiceBPM.CaseDef,selfServiceBPM.TaskDef,List<selfServiceBPM.TaskDef>) #txt
ts0 f0 disableUIEvents true #txt
ts0 f0 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<selfServiceBPM.CaseDef caseInfo,selfServiceBPM.TaskDef nextTask,List<selfServiceBPM.TaskDef> definedTasks> param = methodEvent.getInputArguments();
' #txt
ts0 f0 inParameterMapAction 'out.caseInfo=param.caseInfo;
out.definedTasks=param.definedTasks;
out.nextTask=param.nextTask;
' #txt
ts0 f0 inActionCode 'import selfServiceBPM.TaskDef;
import ch.ivyteam.ivy.workflow.ITask;
for(int i=0; i<ivy.case.getTasks().size()-1; i++)
{
	ITask itask = ivy.case.getTasks().get(i);
	if(!"#SYSTEM".equalsIgnoreCase(itask.getActivatorName()) 
		&& itask.getEndTimestamp().toString().length()>0
		&& !"AD_HOC_MODIFIED".equals(itask.getCustomVarCharField1()))
	{ 
		TaskDef def = 	new TaskDef();
		def.actor = itask.getWorkerUserName();
		def.kind = itask.getKindCode();
		def.done = itask.getEndTimestamp();
		def.taskNotes = itask.getNotes();
		out.taskHistory.add(def);
	}
}
out.addTask = new selfServiceBPM.TaskDef();
selfServiceBPM.TaskDef oldTask = param.nextTask; //out.taskHistory.get(out.taskHistory.size()-1);
out.addTask.setActor(ivy.session.getSessionUserName());
out.addTask.setAssigned(new DateTime());
out.addTask.setUntil(ivy.cal.getWorkDayIn(1,new Time()));
out.addTask.kind = oldTask.kind;

out.caseNotes = ivy.case.getNotes();' #txt
ts0 f0 outParameterDecl '<List<selfServiceBPM.TaskDef> definedTasks> result;
' #txt
ts0 f0 outParameterMapAction 'result.definedTasks=in.definedTasks;
' #txt
ts0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start(CaseDef,TaskDef)</name>
        <nameStyle>22,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
ts0 f0 83 51 26 26 -65 15 #rect
ts0 f0 @|RichDialogInitStartIcon #fIcon
ts0 f1 type agileBPM.task_Form.task_FormData #txt
ts0 f1 339 51 26 26 0 12 #rect
ts0 f1 @|RichDialogProcessEndIcon #fIcon
ts0 f3 guid 1492E077ACABAB84 #txt
ts0 f3 type agileBPM.task_Form.task_FormData #txt
ts0 f3 actionDecl 'agileBPM.task_Form.task_FormData out;
' #txt
ts0 f3 actionTable 'out=in;
' #txt
ts0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>close</name>
        <nameStyle>5,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
ts0 f3 83 147 26 26 -15 15 #rect
ts0 f3 @|RichDialogProcessStartIcon #fIcon
ts0 f4 type agileBPM.task_Form.task_FormData #txt
ts0 f4 guid 1492E077ACA40C72 #txt
ts0 f4 499 147 26 26 0 12 #rect
ts0 f4 @|RichDialogEndIcon #fIcon
ts0 f6 guid 14B02755D931FD2D #txt
ts0 f6 type agileBPM.task_Form.task_FormData #txt
ts0 f6 actionDecl 'agileBPM.task_Form.task_FormData out;
' #txt
ts0 f6 actionTable 'out=in;
' #txt
ts0 f6 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>followupQ</name>
        <nameStyle>9,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
ts0 f6 83 259 26 26 -27 15 #rect
ts0 f6 @|RichDialogProcessStartIcon #fIcon
ts0 f9 actionDecl 'agileBPM.task_Form.task_FormData out;
' #txt
ts0 f9 actionTable 'out=in;
' #txt
ts0 f9 actionCode 'import ch.ivyteam.ivy.workflow.INote;
if(in.nextTask.kind.equalsIgnoreCase("APPROVAL"))
{
		ivy.case.createNote(ivy.session, "Approved: "+ in.ok+" "+in.answer);
		ivy.task.createNote(ivy.session, "Approved: "+ in.ok+" "+in.answer);
}	
else if(in.nextTask.kind.equalsIgnoreCase("QA"))
{
		ivy.case.createNote(ivy.session, "Answer: " +in.answer);
		ivy.task.createNote(ivy.session, "Answer: " +in.answer);
}
else if(in.nextTask.kind.equalsIgnoreCase("TASK"))
{
		ivy.case.createNote(ivy.session, "Comment: " +in.answer);
		ivy.task.createNote(ivy.session, "Comment: " +in.answer);
}		
else if(in.nextTask.kind.equalsIgnoreCase("RESPONSE"))
{
		ivy.case.createNote(ivy.session, "Ok");
		ivy.task.createNote(ivy.session, "Ok");
}	
' #txt
ts0 f9 type agileBPM.task_Form.task_FormData #txt
ts0 f9 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>set historyNote</name>
        <nameStyle>15,7
</nameStyle>
    </language>
</elementInfo>
' #txt
ts0 f9 336 138 112 44 -41 -8 #rect
ts0 f9 @|StepIcon #fIcon
ts0 f10 expr out #txt
ts0 f10 109 160 336 160 #arcP
ts0 f5 expr out #txt
ts0 f5 448 160 499 160 #arcP
ts0 f11 actionDecl 'agileBPM.task_Form.task_FormData out;
' #txt
ts0 f11 actionTable 'out=in;
' #txt
ts0 f11 actionCode 'selfServiceBPM.TaskDef newTask1 = new selfServiceBPM.TaskDef();
selfServiceBPM.TaskDef oldTask = out.taskHistory.get(out.taskHistory.size()-1);
newTask1.setActor(oldTask.actor);
newTask1.setAssigned(new DateTime());
newTask1.setUntil(ivy.cal.getWorkDayIn(1,new Time()));
newTask1.kind = "QA";
newTask1.description = in.remark;
out.definedTasks.add(0,newTask1);

out.definedTasks.add(1,in.nextTask.setUntil(ivy.cal.getWorkDayIn(2,new Time())));

' #txt
ts0 f11 security system #txt
ts0 f11 type agileBPM.task_Form.task_FormData #txt
ts0 f11 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>add new Tasks to List </name>
        <nameStyle>22,7
</nameStyle>
    </language>
</elementInfo>
' #txt
ts0 f11 160 250 128 44 -61 -8 #rect
ts0 f11 @|StepIcon #fIcon
ts0 f7 expr out #txt
ts0 f7 109 272 160 272 #arcP
ts0 f8 actionDecl 'agileBPM.task_Form.task_FormData out;
' #txt
ts0 f8 actionTable 'out=in;
' #txt
ts0 f8 actionCode 'selfServiceBPM.TaskDef newTask2 = new selfServiceBPM.TaskDef();
newTask2.setActor(in.addTask.actor);
newTask2.setAssigned(new DateTime());
newTask2.setUntil(ivy.cal.getWorkDayIn(1,new Time()));
newTask2.kind = "TASK";
newTask2.description = in.remark;
newTask2.setAdhoc(true);

out.definedTasks.add(0,newTask2);
out.msg = in.addTask.actor;
' #txt
ts0 f8 type agileBPM.task_Form.task_FormData #txt
ts0 f8 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>add new Task to List</name>
        <nameStyle>20,7
</nameStyle>
    </language>
</elementInfo>
' #txt
ts0 f8 160 490 128 44 -56 -8 #rect
ts0 f8 @|StepIcon #fIcon
ts0 f12 guid 14B733FBF37CF8B5 #txt
ts0 f12 type agileBPM.task_Form.task_FormData #txt
ts0 f12 actionDecl 'agileBPM.task_Form.task_FormData out;
' #txt
ts0 f12 actionTable 'out=in;
' #txt
ts0 f12 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>appendTask</name>
        <nameStyle>10,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
ts0 f12 83 499 26 26 -34 15 #rect
ts0 f12 @|RichDialogProcessStartIcon #fIcon
ts0 f14 expr out #txt
ts0 f14 109 512 160 512 #arcP
ts0 f14 0 0.5000000000000001 0 0 #arcLabel
ts0 f16 actionDecl 'agileBPM.task_Form.task_FormData out;
' #txt
ts0 f16 actionTable 'out=in;
' #txt
ts0 f16 actionCode 'import ch.ivyteam.ivy.workflow.INote;
if(in.nextTask.kind.equalsIgnoreCase("RESPONSE"))
{
		ivy.task.createNote(ivy.session, "New question: " +in.remark);
}	' #txt
ts0 f16 type agileBPM.task_Form.task_FormData #txt
ts0 f16 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>set historyNote</name>
        <nameStyle>15,7
</nameStyle>
    </language>
</elementInfo>
' #txt
ts0 f16 336 250 112 44 -41 -8 #rect
ts0 f16 @|StepIcon #fIcon
ts0 f17 expr out #txt
ts0 f17 288 272 336 272 #arcP
ts0 f17 0 0.2601685954614588 0 0 #arcLabel
ts0 f13 expr out #txt
ts0 f13 448 272 512 173 #arcP
ts0 f13 1 512 272 #addKink
ts0 f13 1 0.4877973440059553 0 0 #arcLabel
ts0 f20 guid 14B7E1153B0E4196 #txt
ts0 f20 type agileBPM.task_Form.task_FormData #txt
ts0 f20 method done(Boolean) #txt
ts0 f20 disableUIEvents false #txt
ts0 f20 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<java.lang.Boolean done> param = methodEvent.getInputArguments();
' #txt
ts0 f20 inParameterMapAction 'out.done=param.done;
' #txt
ts0 f20 outParameterDecl '<> result;
' #txt
ts0 f20 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>done(Boolean)</name>
        <nameStyle>13,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
ts0 f20 83 403 26 26 -41 15 #rect
ts0 f20 @|RichDialogMethodStartIcon #fIcon
ts0 f21 actionDecl 'agileBPM.task_Form.task_FormData out;
' #txt
ts0 f21 actionTable 'out=in;
' #txt
ts0 f21 actionCode 'import ch.ivyteam.ivy.workflow.INote;
if(in.nextTask.kind.equalsIgnoreCase("TODO"))
{
	ivy.case.createNote(ivy.session, "Done "+(in.done ? "confirmed" : "refused"));
	ivy.task.createNote(ivy.session, "Done "+(in.done ? "confirmed" : "refused"));
}


' #txt
ts0 f21 type agileBPM.task_Form.task_FormData #txt
ts0 f21 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>set historyNote</name>
        <nameStyle>15,7
</nameStyle>
    </language>
</elementInfo>
' #txt
ts0 f21 328 394 112 44 -41 -8 #rect
ts0 f21 @|StepIcon #fIcon
ts0 f22 expr out #txt
ts0 f22 109 416 328 416 #arcP
ts0 f23 expr out #txt
ts0 f23 440 416 512 173 #arcP
ts0 f23 1 512 416 #addKink
ts0 f23 1 0.36970684039087953 0 0 #arcLabel
ts0 f37 actionDecl 'agileBPM.task_Form.task_FormData out;
' #txt
ts0 f37 actionTable 'out=in;
' #txt
ts0 f37 actionCode 'out.addTask.actor="";
import ch.ivyteam.ivy.security.IUser;
for(IUser u: in.userSelection)
{
	out.addTask.actor = out.addTask.actor+", "+u.getName();	
}	
out.addTask.actor = out.addTask.actor.replaceFirst(", ","");' #txt
ts0 f37 type agileBPM.task_Form.task_FormData #txt
ts0 f37 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>update users</name>
        <nameStyle>12,7
</nameStyle>
    </language>
</elementInfo>
' #txt
ts0 f37 152 602 112 44 -36 -8 #rect
ts0 f37 @|StepIcon #fIcon
ts0 f34 guid 14BEFA696A6EEA50 #txt
ts0 f34 type agileBPM.task_Form.task_FormData #txt
ts0 f34 actionDecl 'agileBPM.task_Form.task_FormData out;
' #txt
ts0 f34 actionTable 'out=in;
' #txt
ts0 f34 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>updateUserList</name>
        <nameStyle>14,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
ts0 f34 83 611 26 26 -42 15 #rect
ts0 f34 @|RichDialogProcessStartIcon #fIcon
ts0 f35 type agileBPM.task_Form.task_FormData #txt
ts0 f35 307 611 26 26 0 12 #rect
ts0 f35 @|RichDialogProcessEndIcon #fIcon
ts0 f38 expr out #txt
ts0 f38 109 624 152 624 #arcP
ts0 f36 expr out #txt
ts0 f36 264 624 307 624 #arcP
ts0 f15 actionDecl 'agileBPM.task_Form.task_FormData out;
' #txt
ts0 f15 actionTable 'out=in;
' #txt
ts0 f15 actionCode 'import ch.ivyteam.ivy.Helper;

// wfuserlist
// not that gravatar is used for portrait images
import ch.ivyteam.ivy.security.IUser;
List users = ivy.wf.getSecurityContext().getUsers();
in.userList.clear();

for(IUser user : users)
{
	if(user.getName() != "SYSTEM")
	{
		out.userList.add(user);
	}
}
out.userList = Helper.sortUsers(out.userList);' #txt
ts0 f15 type agileBPM.task_Form.task_FormData #txt
ts0 f15 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>init userlist</name>
        <nameStyle>13,7
</nameStyle>
    </language>
</elementInfo>
' #txt
ts0 f15 168 42 112 44 -30 -8 #rect
ts0 f15 @|StepIcon #fIcon
ts0 f26 expr out #txt
ts0 f26 109 64 168 64 #arcP
ts0 f2 expr out #txt
ts0 f2 280 64 339 64 #arcP
ts0 f24 type agileBPM.task_Form.task_FormData #txt
ts0 f24 499 499 26 26 0 12 #rect
ts0 f24 @|RichDialogProcessEndIcon #fIcon
ts0 f28 actionDecl 'agileBPM.task_Form.task_FormData out;
' #txt
ts0 f28 actionTable 'out=in;
' #txt
ts0 f28 actionCode 'import ch.ivyteam.ivy.SystemDo;
SystemDo.setCustomVarCharField1(ivy.task, "AD_HOC_MODIFIED");

out.definedTasks.add(0,in.nextTask);

selfServiceBPM.TaskDef newTask2 = new selfServiceBPM.TaskDef();
newTask2.setActor(in.addTask.actor);
newTask2.setAssigned(new DateTime());
newTask2.setUntil(ivy.cal.getWorkDayIn(1,new Time()));
newTask2.kind = "TASK";
newTask2.description = in.remark;
out.definedTasks.add(0,newTask2);

out.msg = in.addTask.actor;

' #txt
ts0 f28 type agileBPM.task_Form.task_FormData #txt
ts0 f28 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>insert new Tasks to List </name>
        <nameStyle>25,7
</nameStyle>
    </language>
</elementInfo>
' #txt
ts0 f28 152 330 144 44 -66 -8 #rect
ts0 f28 @|StepIcon #fIcon
ts0 f29 guid 14C46CB289D62C1B #txt
ts0 f29 type agileBPM.task_Form.task_FormData #txt
ts0 f29 actionDecl 'agileBPM.task_Form.task_FormData out;
' #txt
ts0 f29 actionTable 'out=in;
' #txt
ts0 f29 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>insertTask</name>
        <nameStyle>10,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
ts0 f29 83 339 26 26 -29 15 #rect
ts0 f29 @|RichDialogProcessStartIcon #fIcon
ts0 f30 expr out #txt
ts0 f30 109 352 152 352 #arcP
ts0 f33 guid 14C46FC6DC345ABA #txt
ts0 f33 type agileBPM.task_Form.task_FormData #txt
ts0 f33 method deleteTask(selfServiceBPM.TaskDef) #txt
ts0 f33 disableUIEvents false #txt
ts0 f33 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<selfServiceBPM.TaskDef taskDef> param = methodEvent.getInputArguments();
' #txt
ts0 f33 inActionCode 'out.definedTasks = out.definedTasks.remove(param.taskDef);
out.msg = param.taskDef.actor;' #txt
ts0 f33 outParameterDecl '<> result;
' #txt
ts0 f33 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>deleteTask(TaskDef)</name>
        <nameStyle>19,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
ts0 f33 83 691 26 26 -57 15 #rect
ts0 f33 @|RichDialogMethodStartIcon #fIcon
ts0 f39 type agileBPM.task_Form.task_FormData #txt
ts0 f39 499 691 26 26 0 12 #rect
ts0 f39 @|RichDialogProcessEndIcon #fIcon
ts0 f32 expr out #txt
ts0 f32 448 352 512 173 #arcP
ts0 f32 1 512 352 #addKink
ts0 f32 1 0.32122905027932963 0 0 #arcLabel
ts0 f31 expr out #txt
ts0 f31 296 352 336 352 #arcP
ts0 f31 0 0.2601685954614588 0 0 #arcLabel
ts0 f27 actionDecl 'agileBPM.task_Form.task_FormData out;
' #txt
ts0 f27 actionTable 'out=in;
' #txt
ts0 f27 actionCode 'import ch.ivyteam.ivy.workflow.INote;

ivy.task.createNote(ivy.session, "Task for ["+in.msg+"] inserted: "+in.remark);
' #txt
ts0 f27 type agileBPM.task_Form.task_FormData #txt
ts0 f27 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>set historyNote</name>
        <nameStyle>15,7
</nameStyle>
    </language>
</elementInfo>
' #txt
ts0 f27 336 330 112 44 -41 -8 #rect
ts0 f27 @|StepIcon #fIcon
ts0 f25 expr out #txt
ts0 f25 440 512 499 512 #arcP
ts0 f19 expr out #txt
ts0 f19 288 512 328 512 #arcP
ts0 f19 0 0.5460591952111739 0 0 #arcLabel
ts0 f18 actionDecl 'agileBPM.task_Form.task_FormData out;
' #txt
ts0 f18 actionTable 'out=in;
' #txt
ts0 f18 actionCode 'import ch.ivyteam.ivy.workflow.INote;

ivy.task.createNote(ivy.session, "Task appended: ["+in.msg+"] "+in.remark);





' #txt
ts0 f18 type agileBPM.task_Form.task_FormData #txt
ts0 f18 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>set historyNote</name>
        <nameStyle>15,7
</nameStyle>
    </language>
</elementInfo>
' #txt
ts0 f18 328 490 112 44 -41 -8 #rect
ts0 f18 @|StepIcon #fIcon
ts0 f40 expr out #txt
ts0 f40 440 704 499 704 #arcP
ts0 f42 expr out #txt
ts0 f42 109 704 328 704 #arcP
ts0 f41 actionDecl 'agileBPM.task_Form.task_FormData out;
' #txt
ts0 f41 actionTable 'out=in;
' #txt
ts0 f41 actionCode 'import ch.ivyteam.ivy.workflow.INote;

ivy.task.createNote(ivy.session, "Pending task deleted: ["+in.msg+"]");





' #txt
ts0 f41 type agileBPM.task_Form.task_FormData #txt
ts0 f41 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>set historyNote</name>
        <nameStyle>15,7
</nameStyle>
    </language>
</elementInfo>
' #txt
ts0 f41 328 682 112 44 -41 -8 #rect
ts0 f41 @|StepIcon #fIcon
>Proto ts0 .type agileBPM.task_Form.task_FormData #txt
>Proto ts0 .processKind HTML_DIALOG #txt
>Proto ts0 -8 -8 16 16 16 26 #rect
>Proto ts0 '' #fIcon
ts0 f3 mainOut f10 tail #connect
ts0 f10 head f9 mainIn #connect
ts0 f9 mainOut f5 tail #connect
ts0 f5 head f4 mainIn #connect
ts0 f6 mainOut f7 tail #connect
ts0 f7 head f11 mainIn #connect
ts0 f12 mainOut f14 tail #connect
ts0 f14 head f8 mainIn #connect
ts0 f11 mainOut f17 tail #connect
ts0 f17 head f16 mainIn #connect
ts0 f8 mainOut f19 tail #connect
ts0 f19 head f18 mainIn #connect
ts0 f16 mainOut f13 tail #connect
ts0 f13 head f4 mainIn #connect
ts0 f20 mainOut f22 tail #connect
ts0 f22 head f21 mainIn #connect
ts0 f21 mainOut f23 tail #connect
ts0 f23 head f4 mainIn #connect
ts0 f34 mainOut f38 tail #connect
ts0 f38 head f37 mainIn #connect
ts0 f37 mainOut f36 tail #connect
ts0 f36 head f35 mainIn #connect
ts0 f0 mainOut f26 tail #connect
ts0 f26 head f15 mainIn #connect
ts0 f15 mainOut f2 tail #connect
ts0 f2 head f1 mainIn #connect
ts0 f18 mainOut f25 tail #connect
ts0 f25 head f24 mainIn #connect
ts0 f29 mainOut f30 tail #connect
ts0 f30 head f28 mainIn #connect
ts0 f28 mainOut f31 tail #connect
ts0 f31 head f27 mainIn #connect
ts0 f27 mainOut f32 tail #connect
ts0 f32 head f4 mainIn #connect
ts0 f33 mainOut f42 tail #connect
ts0 f42 head f41 mainIn #connect
ts0 f41 mainOut f40 tail #connect
ts0 f40 head f39 mainIn #connect
