[Ivy]
[>Created: Tue Jan 05 17:08:26 ICT 2016]
144633F679C3A22D 3.18 #module
>Proto >Proto Collection #zClass
ds0 define_WFProcess Big #zClass
ds0 RD #cInfo
ds0 #process
ds0 @TextInP .ui2RdDataAction .ui2RdDataAction #zField
ds0 @TextInP .rdData2UIAction .rdData2UIAction #zField
ds0 @TextInP .resExport .resExport #zField
ds0 @TextInP .type .type #zField
ds0 @TextInP .processKind .processKind #zField
ds0 @AnnotationInP-0n ai ai #zField
ds0 @MessageFlowInP-0n messageIn messageIn #zField
ds0 @MessageFlowOutP-0n messageOut messageOut #zField
ds0 @TextInP .xml .xml #zField
ds0 @TextInP .responsibility .responsibility #zField
ds0 @RichDialogInitStart f0 '' #zField
ds0 @RichDialogProcessEnd f1 '' #zField
ds0 @RichDialogEnd f4 '' #zField
ds0 @RichDialogProcessStart f28 '' #zField
ds0 @GridStep f3 '' #zField
ds0 @PushWFArc f5 '' #zField
ds0 @PushWFArc f10 '' #zField
ds0 @PushWFArc f14 '' #zField
ds0 @GridStep f11 '' #zField
ds0 @RichDialogProcessStart f8 '' #zField
ds0 @RichDialogProcessEnd f9 '' #zField
ds0 @GridStep f7 '' #zField
ds0 @PushWFArc f12 '' #zField
ds0 @PushWFArc f2 '' #zField
ds0 @RichDialogMethodStart f13 '' #zField
ds0 @RichDialogProcessEnd f15 '' #zField
ds0 @PushWFArc f16 '' #zField
ds0 @RichDialogProcessEnd f17 '' #zField
ds0 @GridStep f18 '' #zField
ds0 @RichDialogProcessStart f19 '' #zField
ds0 @PushWFArc f20 '' #zField
ds0 @PushWFArc f21 '' #zField
ds0 @Alternative f22 '' #zField
ds0 @PushWFArc f23 '' #zField
ds0 @PushWFArc f6 '' #zField
ds0 @RichDialogProcessEnd f24 '' #zField
ds0 @GridStep f26 '' #zField
ds0 @PushWFArc f27 '' #zField
ds0 @PushWFArc f25 '' #zField
ds0 @RichDialogProcessStart f29 '' #zField
ds0 @GridStep f32 '' #zField
ds0 @PushWFArc f33 '' #zField
ds0 @PushWFArc f31 '' #zField
ds0 @RichDialogProcessEnd f30 '' #zField
ds0 @RichDialogProcessStart f34 '' #zField
ds0 @RichDialogProcessEnd f35 '' #zField
ds0 @GridStep f37 '' #zField
ds0 @PushWFArc f38 '' #zField
ds0 @PushWFArc f36 '' #zField
ds0 @GridStep f39 '' #zField
ds0 @RichDialogProcessEnd f40 '' #zField
ds0 @PushWFArc f43 '' #zField
ds0 @RichDialogMethodStart f44 '' #zField
ds0 @PushWFArc f41 '' #zField
ds0 @RichDialogMethodStart f47 '' #zField
ds0 @RichDialogProcessEnd f48 '' #zField
ds0 @PushWFArc f49 '' #zField
ds0 @RichDialogProcessStart f50 '' #zField
ds0 @RichDialogProcessEnd f51 '' #zField
ds0 @PushWFArc f52 '' #zField
>Proto ds0 ds0 define_WFProcess #zField
ds0 f0 guid 144633F67BB43F5D #txt
ds0 f0 type agileBPM.define_WF.define_WFData #txt
ds0 f0 method start() #txt
ds0 f0 disableUIEvents true #txt
ds0 f0 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<> param = methodEvent.getInputArguments();
' #txt
ds0 f0 outParameterDecl '<List<selfServiceBPM.TaskDef> definedTasks,selfServiceBPM.CaseDef caseInfo> result;
' #txt
ds0 f0 outParameterMapAction 'result.definedTasks=in.definedTasks;
result.caseInfo=in.caseInfo;
' #txt
ds0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start(List&lt;TaskDef&gt;,CaseDef)</name>
        <nameStyle>28,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
ds0 f0 51 51 26 26 -54 23 #rect
ds0 f0 @|RichDialogInitStartIcon #fIcon
ds0 f1 type agileBPM.define_WF.define_WFData #txt
ds0 f1 275 51 26 26 0 12 #rect
ds0 f1 @|RichDialogProcessEndIcon #fIcon
ds0 f4 type agileBPM.define_WF.define_WFData #txt
ds0 f4 guid 144633F67BB99807 #txt
ds0 f4 531 147 26 26 0 12 #rect
ds0 f4 @|RichDialogEndIcon #fIcon
ds0 f28 guid 14913CDEFB31F084 #txt
ds0 f28 type agileBPM.define_WF.define_WFData #txt
ds0 f28 actionDecl 'agileBPM.define_WF.define_WFData out;
' #txt
ds0 f28 actionTable 'out=in;
' #txt
ds0 f28 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>go</name>
        <nameStyle>2,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
ds0 f28 51 147 26 26 -7 15 #rect
ds0 f28 @|RichDialogProcessStartIcon #fIcon
ds0 f3 actionDecl 'agileBPM.define_WF.define_WFData out;
' #txt
ds0 f3 actionTable 'out=in;
' #txt
ds0 f3 actionCode 'import ch.ivyteam.ivy.SystemDo;
import selfServiceBPM.TaskDef;
out.errormsg = null;
for(TaskDef tdef: in.definedTasks)
{
	List<String> names = tdef.actor.split(",");
	for(String name: names)
	{
		if(name.trim().length()>0 && ivy.wf.getSecurityContext().findUser(name.trim())== null)
		{
			out.errormsg = "WF User "+name.trim()+" not found";
		}	
	}	
}

if(out.errormsg.length() == 0)
{
	SystemDo.setCaseName(ivy.case,in.caseInfo.kind + ": " + in.caseInfo.subject);
	SystemDo.setCaseDescription(ivy.case,in.caseInfo.description);
	SystemDo.setProcess(ivy.case,in.caseInfo.kind, in.caseInfo.kind);
	
	out.definedTasks.addAll(in.additionalTasks);
	ivy.case.createNote(ivy.session,"Started");
}
' #txt
ds0 f3 type agileBPM.define_WF.define_WFData #txt
ds0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Setup Case</name>
        <nameStyle>10,7
</nameStyle>
    </language>
</elementInfo>
' #txt
ds0 f3 120 138 112 44 -32 -8 #rect
ds0 f3 @|StepIcon #fIcon
ds0 f5 expr out #txt
ds0 f5 77 160 120 160 #arcP
ds0 f10 expr out #txt
ds0 f10 240 256 275 256 #arcP
ds0 f14 expr out #txt
ds0 f14 77 256 112 256 #arcP
ds0 f11 actionDecl 'agileBPM.define_WF.define_WFData out;
' #txt
ds0 f11 actionTable 'out=in;
' #txt
ds0 f11 actionCode 'selfServiceBPM.TaskDef newTask = new selfServiceBPM.TaskDef();
newTask.setActor(ivy.session.getSessionUserName());
newTask.setAssigned(new DateTime());
if("AD-HOC".equals(in.caseInfo.kind))
{
	newTask.kind = "TASK";
	newTask.description = "...";
}
else
{
	newTask.kind = in.caseInfo.kind;
}	
if(out.definedTasks.size()>0 && out.definedTasks.get(out.definedTasks.size()-1).until is initialized)
{
	newTask.setUntil(ivy.cal.getWorkDayIn(out.definedTasks.get(out.definedTasks.size()-1).until, 1));
}
else
{
	newTask.setUntil(ivy.cal.getWorkDayIn(1,new Time()));
}	
out.definedTasks.add(newTask);
' #txt
ds0 f11 type agileBPM.define_WF.define_WFData #txt
ds0 f11 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>add new Task to List</name>
        <nameStyle>20,7
</nameStyle>
    </language>
</elementInfo>
' #txt
ds0 f11 112 234 128 44 -56 -8 #rect
ds0 f11 @|StepIcon #fIcon
ds0 f8 guid 144689B5E8531C29 #txt
ds0 f8 type agileBPM.define_WF.define_WFData #txt
ds0 f8 actionDecl 'agileBPM.define_WF.define_WFData out;
' #txt
ds0 f8 actionTable 'out=in;
' #txt
ds0 f8 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>addTaskDef</name>
    </language>
</elementInfo>
' #txt
ds0 f8 51 243 26 26 -33 12 #rect
ds0 f8 @|RichDialogProcessStartIcon #fIcon
ds0 f9 type agileBPM.define_WF.define_WFData #txt
ds0 f9 275 243 26 26 0 12 #rect
ds0 f9 @|RichDialogProcessEndIcon #fIcon
ds0 f7 actionDecl 'agileBPM.define_WF.define_WFData out;
' #txt
ds0 f7 actionTable 'out=in;
out.caseInfo.kind="TODO";
out.started=new DateTime();
' #txt
ds0 f7 actionCode 'import javax.faces.context.FacesContext;
import ch.ivyteam.ivy.Helper;
import java.util.Collection;

// creator task
out.newTask = new selfServiceBPM.TaskDef();
out.newTask.setActor(ivy.session.getSessionUserName());
out.newTask.setKind(in.caseInfo.kind);
out.newTask.setAssigned(new DateTime());
out.newTask.setUntil(ivy.cal.getWorkDayIn(1,new Time()));

// a first task
selfServiceBPM.TaskDef newTask = new selfServiceBPM.TaskDef();
newTask.setActor(ivy.session.getSessionUserName());
newTask.setKind(in.caseInfo.kind);
newTask.setAssigned(new DateTime());
newTask.setUntil(ivy.cal.getWorkDayIn(1,new Time()));
out.definedTasks.add(newTask);

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
ds0 f7 security system #txt
ds0 f7 type agileBPM.define_WF.define_WFData #txt
ds0 f7 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Init</name>
        <nameStyle>4,7
</nameStyle>
    </language>
</elementInfo>
' #txt
ds0 f7 120 42 112 44 -8 -8 #rect
ds0 f7 @|StepIcon #fIcon
ds0 f12 expr out #txt
ds0 f12 77 64 120 64 #arcP
ds0 f2 expr out #txt
ds0 f2 232 64 275 64 #arcP
ds0 f13 guid 14ADE4A40F9E30A6 #txt
ds0 f13 type agileBPM.define_WF.define_WFData #txt
ds0 f13 method deleteTask(selfServiceBPM.TaskDef) #txt
ds0 f13 disableUIEvents false #txt
ds0 f13 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<selfServiceBPM.TaskDef taskdef> param = methodEvent.getInputArguments();
' #txt
ds0 f13 inActionCode 'out.definedTasks = out.definedTasks.remove(param.taskdef);' #txt
ds0 f13 outParameterDecl '<> result;
' #txt
ds0 f13 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>deleteTask(TaskDef)</name>
    </language>
</elementInfo>
' #txt
ds0 f13 51 387 26 26 -57 15 #rect
ds0 f13 @|RichDialogMethodStartIcon #fIcon
ds0 f15 type agileBPM.define_WF.define_WFData #txt
ds0 f15 275 387 26 26 0 12 #rect
ds0 f15 @|RichDialogProcessEndIcon #fIcon
ds0 f16 expr out #txt
ds0 f16 77 400 275 400 #arcP
ds0 f17 type agileBPM.define_WF.define_WFData #txt
ds0 f17 275 315 26 26 0 12 #rect
ds0 f17 @|RichDialogProcessEndIcon #fIcon
ds0 f18 actionDecl 'agileBPM.define_WF.define_WFData out;
' #txt
ds0 f18 actionTable 'out=in;
' #txt
ds0 f18 actionCode 'out.definedTasks.clear();
out.additionalTasks.clear();

selfServiceBPM.TaskDef newTask = new selfServiceBPM.TaskDef();
newTask.setActor(ivy.session.getSessionUserName());
newTask.setAssigned(new DateTime());
newTask.setUntil(ivy.cal.getWorkDayIn(1,new Time()));

if(in.caseInfo.kind.equalsIgnoreCase("TODO"))
{
newTask.setKind("TODO");
out.definedTasks.add(newTask);		
}	
else if(in.caseInfo.kind.equalsIgnoreCase("APPROVAL"))
{
newTask.setKind("APPROVAL");	
selfServiceBPM.TaskDef newTask2 = new selfServiceBPM.TaskDef();
newTask2.setActor("SYSTEM");
newTask2.setKind("EMail to Creator");
newTask2.setAssigned(new DateTime());
out.additionalTasks.add(newTask2);		
out.definedTasks.add(newTask);	
}	
else if(in.caseInfo.kind.equalsIgnoreCase("QA"))
{
newTask.setKind("QA");	
selfServiceBPM.TaskDef newTask2 = new selfServiceBPM.TaskDef();
newTask2.setActor(ivy.session.getSessionUserName());
newTask2.setKind("RESPONSE");
newTask2.setAssigned(new DateTime());
newTask2.setUntil(ivy.cal.getWorkDayIn(1,new Time()));
out.additionalTasks.add(newTask2);	
out.definedTasks.add(newTask);
}	
else
{ 
	// AD-HOC
}
' #txt
ds0 f18 type agileBPM.define_WF.define_WFData #txt
ds0 f18 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>init List of Tasks</name>
        <nameStyle>18,7
</nameStyle>
    </language>
</elementInfo>
' #txt
ds0 f18 120 306 112 44 -44 -8 #rect
ds0 f18 @|StepIcon #fIcon
ds0 f19 guid 14AEE59B893711B7 #txt
ds0 f19 type agileBPM.define_WF.define_WFData #txt
ds0 f19 actionDecl 'agileBPM.define_WF.define_WFData out;
' #txt
ds0 f19 actionTable 'out=in;
' #txt
ds0 f19 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>initTaskDef</name>
        <nameStyle>11,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
ds0 f19 51 315 26 26 -31 15 #rect
ds0 f19 @|RichDialogProcessStartIcon #fIcon
ds0 f20 expr out #txt
ds0 f20 232 328 275 328 #arcP
ds0 f21 expr out #txt
ds0 f21 77 328 120 328 #arcP
ds0 f22 type agileBPM.define_WF.define_WFData #txt
ds0 f22 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>wf user ok?</name>
        <nameStyle>11
</nameStyle>
    </language>
</elementInfo>
' #txt
ds0 f22 272 144 32 32 -31 18 #rect
ds0 f22 @|AlternativeIcon #fIcon
ds0 f23 expr out #txt
ds0 f23 232 160 272 160 #arcP
ds0 f6 expr in #txt
ds0 f6 outCond 'in.errormsg.length() == 0' #txt
ds0 f6 304 160 531 160 #arcP
ds0 f24 type agileBPM.define_WF.define_WFData #txt
ds0 f24 531 99 26 26 0 12 #rect
ds0 f24 @|RichDialogProcessEndIcon #fIcon
ds0 f26 actionDecl 'agileBPM.define_WF.define_WFData out;
' #txt
ds0 f26 actionTable 'out=in;
' #txt
ds0 f26 actionCode 'import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

FacesContext.getCurrentInstance().addMessage(null,
	new FacesMessage(FacesMessage.SEVERITY_ERROR,
	in.errormsg, "Task assignmemt not valid."));' #txt
ds0 f26 type agileBPM.define_WF.define_WFData #txt
ds0 f26 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Error msg</name>
        <nameStyle>9
</nameStyle>
    </language>
</elementInfo>
' #txt
ds0 f26 360 90 112 44 -27 -8 #rect
ds0 f26 @|StepIcon #fIcon
ds0 f27 expr in #txt
ds0 f27 288 144 360 112 #arcP
ds0 f27 1 288 112 #addKink
ds0 f27 1 0.3735742203768011 0 0 #arcLabel
ds0 f25 expr out #txt
ds0 f25 472 112 531 112 #arcP
ds0 f25 0 0.3735742203768011 0 0 #arcLabel
ds0 f29 guid 14BE55BAABBA28CB #txt
ds0 f29 type agileBPM.define_WF.define_WFData #txt
ds0 f29 actionDecl 'agileBPM.define_WF.define_WFData out;
' #txt
ds0 f29 actionTable 'out=in;
' #txt
ds0 f29 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>setUser</name>
        <nameStyle>7,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
ds0 f29 51 467 26 26 -22 15 #rect
ds0 f29 @|RichDialogProcessStartIcon #fIcon
ds0 f32 actionDecl 'agileBPM.define_WF.define_WFData out;
' #txt
ds0 f32 actionTable 'out=in;
' #txt
ds0 f32 actionCode 'if(in.user.length()>0)
{
	in.editTask.setActor(in.user);
}	
out.userSelection.clear();' #txt
ds0 f32 type agileBPM.define_WF.define_WFData #txt
ds0 f32 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>editTask</name>
        <nameStyle>8
</nameStyle>
    </language>
</elementInfo>
' #txt
ds0 f32 120 458 112 44 -23 -8 #rect
ds0 f32 @|StepIcon #fIcon
ds0 f33 expr out #txt
ds0 f33 77 480 120 480 #arcP
ds0 f31 expr out #txt
ds0 f31 232 480 275 480 #arcP
ds0 f30 type agileBPM.define_WF.define_WFData #txt
ds0 f30 275 467 26 26 0 12 #rect
ds0 f30 @|RichDialogProcessEndIcon #fIcon
ds0 f34 guid 14BEECEBE2A5DE80 #txt
ds0 f34 type agileBPM.define_WF.define_WFData #txt
ds0 f34 actionDecl 'agileBPM.define_WF.define_WFData out;
' #txt
ds0 f34 actionTable 'out=in;
' #txt
ds0 f34 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>updateUserList</name>
        <nameStyle>14,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
ds0 f34 51 563 26 26 -42 15 #rect
ds0 f34 @|RichDialogProcessStartIcon #fIcon
ds0 f35 type agileBPM.define_WF.define_WFData #txt
ds0 f35 275 563 26 26 0 12 #rect
ds0 f35 @|RichDialogProcessEndIcon #fIcon
ds0 f37 actionDecl 'agileBPM.define_WF.define_WFData out;
' #txt
ds0 f37 actionTable 'out=in;
' #txt
ds0 f37 actionCode 'out.user="";
import ch.ivyteam.ivy.security.IUser;
for(IUser u: in.userSelection)
{
	out.user = out.user+", "+u.getName();	
}	
out.user = out.user.replaceFirst(", ","");' #txt
ds0 f37 type agileBPM.define_WF.define_WFData #txt
ds0 f37 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>update users</name>
        <nameStyle>12
</nameStyle>
    </language>
</elementInfo>
' #txt
ds0 f37 120 554 112 44 -36 -8 #rect
ds0 f37 @|StepIcon #fIcon
ds0 f38 expr out #txt
ds0 f38 77 576 120 576 #arcP
ds0 f36 expr out #txt
ds0 f36 232 576 275 576 #arcP
ds0 f39 actionDecl 'agileBPM.define_WF.define_WFData out;
' #txt
ds0 f39 actionTable 'out=in;
' #txt
ds0 f39 actionCode 'List<String> users = in.editTask.actor.split(",");
import ch.ivyteam.ivy.security.IUser;
out.userSelection.clear();
if(users.size()>1)
{
	for(String u: users)
	{
		out.userSelection.add(ivy.session.getSecurityContext().findUser(u.trim()));
	}	
}' #txt
ds0 f39 type agileBPM.define_WF.define_WFData #txt
ds0 f39 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>init users</name>
        <nameStyle>10
</nameStyle>
    </language>
</elementInfo>
' #txt
ds0 f39 120 650 112 44 -25 -8 #rect
ds0 f39 @|StepIcon #fIcon
ds0 f40 type agileBPM.define_WF.define_WFData #txt
ds0 f40 275 659 26 26 0 12 #rect
ds0 f40 @|RichDialogProcessEndIcon #fIcon
ds0 f43 expr out #txt
ds0 f43 232 672 275 672 #arcP
ds0 f44 guid 14BEF560D05EF4D6 #txt
ds0 f44 type agileBPM.define_WF.define_WFData #txt
ds0 f44 method initUserList(selfServiceBPM.TaskDef) #txt
ds0 f44 disableUIEvents false #txt
ds0 f44 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<selfServiceBPM.TaskDef taskDef> param = methodEvent.getInputArguments();
' #txt
ds0 f44 inParameterMapAction 'out.editTask=param.taskDef;
out.user=param.taskDef.actor;
' #txt
ds0 f44 outParameterDecl '<> result;
' #txt
ds0 f44 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>initUserList(TaskDef)</name>
        <nameStyle>21,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
ds0 f44 51 659 26 26 -58 15 #rect
ds0 f44 @|RichDialogMethodStartIcon #fIcon
ds0 f41 expr out #txt
ds0 f41 77 672 120 672 #arcP
ds0 f47 guid 150143FB91A313FB #txt
ds0 f47 type agileBPM.define_WF.define_WFData #txt
ds0 f47 method editTaskDescription(selfServiceBPM.TaskDef) #txt
ds0 f47 disableUIEvents false #txt
ds0 f47 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<selfServiceBPM.TaskDef taskDef> param = methodEvent.getInputArguments();
' #txt
ds0 f47 inParameterMapAction 'out.editTask=param.taskDef;
out.taskDescription="...".equals(param.taskDef.description) ? "" : param.taskDef.description;
' #txt
ds0 f47 outParameterDecl '<> result;
' #txt
ds0 f47 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>editTaskDescription(TaskDef)</name>
        <nameStyle>28,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
ds0 f47 51 755 26 26 -82 15 #rect
ds0 f47 @|RichDialogMethodStartIcon #fIcon
ds0 f48 type agileBPM.define_WF.define_WFData #txt
ds0 f48 179 755 26 26 0 12 #rect
ds0 f48 @|RichDialogProcessEndIcon #fIcon
ds0 f49 77 768 179 768 #arcP
ds0 f50 guid 150144175FAED33C #txt
ds0 f50 type agileBPM.define_WF.define_WFData #txt
ds0 f50 actionDecl 'agileBPM.define_WF.define_WFData out;
' #txt
ds0 f50 actionTable 'out=in;
out.editTask.description=in.taskDescription.length() == 0 ? "..." : in.taskDescription;
' #txt
ds0 f50 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>setTaskDescription</name>
        <nameStyle>18,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
ds0 f50 51 851 26 26 -53 15 #rect
ds0 f50 @|RichDialogProcessStartIcon #fIcon
ds0 f51 type agileBPM.define_WF.define_WFData #txt
ds0 f51 179 851 26 26 0 12 #rect
ds0 f51 @|RichDialogProcessEndIcon #fIcon
ds0 f52 77 864 179 864 #arcP
>Proto ds0 .type agileBPM.define_WF.define_WFData #txt
>Proto ds0 .processKind HTML_DIALOG #txt
>Proto ds0 -8 -8 16 16 16 26 #rect
>Proto ds0 '' #fIcon
ds0 f11 mainOut f10 tail #connect
ds0 f10 head f9 mainIn #connect
ds0 f8 mainOut f14 tail #connect
ds0 f14 head f11 mainIn #connect
ds0 f28 mainOut f5 tail #connect
ds0 f5 head f3 mainIn #connect
ds0 f0 mainOut f12 tail #connect
ds0 f12 head f7 mainIn #connect
ds0 f7 mainOut f2 tail #connect
ds0 f2 head f1 mainIn #connect
ds0 f13 mainOut f16 tail #connect
ds0 f16 head f15 mainIn #connect
ds0 f18 mainOut f20 tail #connect
ds0 f20 head f17 mainIn #connect
ds0 f19 mainOut f21 tail #connect
ds0 f21 head f18 mainIn #connect
ds0 f3 mainOut f23 tail #connect
ds0 f23 head f22 in #connect
ds0 f22 out f6 tail #connect
ds0 f6 head f4 mainIn #connect
ds0 f22 out f27 tail #connect
ds0 f27 head f26 mainIn #connect
ds0 f26 mainOut f25 tail #connect
ds0 f25 head f24 mainIn #connect
ds0 f29 mainOut f33 tail #connect
ds0 f33 head f32 mainIn #connect
ds0 f32 mainOut f31 tail #connect
ds0 f31 head f30 mainIn #connect
ds0 f34 mainOut f38 tail #connect
ds0 f38 head f37 mainIn #connect
ds0 f37 mainOut f36 tail #connect
ds0 f36 head f35 mainIn #connect
ds0 f39 mainOut f43 tail #connect
ds0 f43 head f40 mainIn #connect
ds0 f44 mainOut f41 tail #connect
ds0 f41 head f39 mainIn #connect
ds0 f47 mainOut f49 tail #connect
ds0 f49 head f48 mainIn #connect
ds0 f50 mainOut f52 tail #connect
ds0 f52 head f51 mainIn #connect
