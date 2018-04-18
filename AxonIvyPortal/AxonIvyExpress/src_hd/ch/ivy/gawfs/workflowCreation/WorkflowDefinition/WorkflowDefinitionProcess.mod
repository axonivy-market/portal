[Ivy]
1576FA61C4EDC8B1 3.20 #module
>Proto >Proto Collection #zClass
Fs0 WorkflowDefinitionProcess Big #zClass
Fs0 RD #cInfo
Fs0 #process
Fs0 @TextInP .ui2RdDataAction .ui2RdDataAction #zField
Fs0 @TextInP .rdData2UIAction .rdData2UIAction #zField
Fs0 @TextInP .resExport .resExport #zField
Fs0 @TextInP .type .type #zField
Fs0 @TextInP .processKind .processKind #zField
Fs0 @AnnotationInP-0n ai ai #zField
Fs0 @MessageFlowInP-0n messageIn messageIn #zField
Fs0 @MessageFlowOutP-0n messageOut messageOut #zField
Fs0 @TextInP .xml .xml #zField
Fs0 @TextInP .responsibility .responsibility #zField
Fs0 @RichDialogInitStart f0 '' #zField
Fs0 @RichDialogProcessEnd f1 '' #zField
Fs0 @RichDialogProcessStart f3 '' #zField
Fs0 @RichDialogEnd f4 '' #zField
Fs0 @PushWFArc f5 '' #zField
Fs0 @GridStep f6 '' #zField
Fs0 @PushWFArc f7 '' #zField
Fs0 @PushWFArc f2 '' #zField
Fs0 @RichDialogMethodStart f9 '' #zField
Fs0 @RichDialogMethodStart f8 '' #zField
Fs0 @GridStep f10 '' #zField
Fs0 @PushWFArc f11 '' #zField
Fs0 @PushWFArc f12 '' #zField
Fs0 @GridStep f13 '' #zField
Fs0 @PushWFArc f14 '' #zField
Fs0 @PushWFArc f15 '' #zField
Fs0 @RichDialogMethodStart f16 '' #zField
Fs0 @RichDialogProcessEnd f20 '' #zField
Fs0 @RichDialogMethodStart f19 '' #zField
Fs0 @GridStep f23 '' #zField
Fs0 @PushWFArc f24 '' #zField
Fs0 @PushWFArc f22 '' #zField
Fs0 @RichDialogMethodStart f25 '' #zField
Fs0 @RichDialogEnd f30 '' #zField
Fs0 @GridStep f28 '' #zField
Fs0 @PushWFArc f32 '' #zField
Fs0 @PushWFArc f31 '' #zField
Fs0 @PushWFArc f26 '' #zField
Fs0 @RichDialogMethodStart f17 '' #zField
Fs0 @RichDialogProcessEnd f18 '' #zField
Fs0 @PushWFArc f21 '' #zField
>Proto Fs0 Fs0 WorkflowDefinitionProcess #zField
Fs0 f0 guid 1576FA61C9D81A51 #txt
Fs0 f0 type ch.ivy.gawfs.workflowCreation.WorkflowDefinition.WorkflowDefinitionData #txt
Fs0 f0 method start(gawfs.Data) #txt
Fs0 f0 disableUIEvents true #txt
Fs0 f0 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<gawfs.Data data> param = methodEvent.getInputArguments();
' #txt
Fs0 f0 inParameterMapAction 'out.data=param.data;
' #txt
Fs0 f0 outParameterDecl '<gawfs.Data data> result;
' #txt
Fs0 f0 outParameterMapAction 'result.data=in.data;
' #txt
Fs0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start(Data)</name>
    </language>
</elementInfo>
' #txt
Fs0 f0 83 51 26 26 -29 15 #rect
Fs0 f0 @|RichDialogInitStartIcon #fIcon
Fs0 f1 type ch.ivy.gawfs.workflowCreation.WorkflowDefinition.WorkflowDefinitionData #txt
Fs0 f1 435 51 26 26 0 12 #rect
Fs0 f1 @|RichDialogProcessEndIcon #fIcon
Fs0 f3 guid 1576FA61CAF25C4A #txt
Fs0 f3 type ch.ivy.gawfs.workflowCreation.WorkflowDefinition.WorkflowDefinitionData #txt
Fs0 f3 actionDecl 'ch.ivy.gawfs.workflowCreation.WorkflowDefinition.WorkflowDefinitionData out;
' #txt
Fs0 f3 actionTable 'out=in;
' #txt
Fs0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>close</name>
    </language>
</elementInfo>
' #txt
Fs0 f3 83 499 26 26 -15 12 #rect
Fs0 f3 @|RichDialogProcessStartIcon #fIcon
Fs0 f4 type ch.ivy.gawfs.workflowCreation.WorkflowDefinition.WorkflowDefinitionData #txt
Fs0 f4 guid 1576FA61CB08591F #txt
Fs0 f4 435 499 26 26 0 12 #rect
Fs0 f4 @|RichDialogEndIcon #fIcon
Fs0 f5 expr out #txt
Fs0 f5 109 512 435 512 #arcP
Fs0 f6 actionDecl 'ch.ivy.gawfs.workflowCreation.WorkflowDefinition.WorkflowDefinitionData out;
' #txt
Fs0 f6 actionTable 'out=in;
out.one=1;
' #txt
Fs0 f6 actionCode 'import java.util.Collections;
import ch.ivyteam.ivy.security.IRole;
import ch.ivy.gawfs.Helper;
import gawfs.TaskDef;
import ch.ivyteam.ivy.security.IUser;


ivy.task.setName(ivy.cms.co("/Dialogs/workflowCreation/WorkflowDefinition/WorkflowPropertiesStep"));
ivy.task.setDescription(ivy.cms.co("/Dialogs/Tasks/WorkflowProperties/TaskDescription"));


if(in.data.definedTasks.size()<1){
	TaskDef task = new TaskDef();
	task.count = 1;
	task.untilDays = 1;
	in.data.definedTasks.add(task);
}else{
	in.data.definedTasks = Helper.sortTasks(in.data.definedTasks);
	
}


//Get Users
List users = ivy.wf.getSecurityContext().getUsers();
in.AvailableRolesAndUsers.clear();

for(IUser user : users)
{
	if(user.getName() != "SYSTEM")
	{
			out.AvailableRolesAndUsers.add(user);
	}
}

//Get Roles
List roles = ivy.wf.getSecurityContext().getRoles();

for(IRole role : roles){
	if (role.getProperty("HIDE") is initialized) {
              continue;
       }
	out.AvailableRolesAndUsers.add(role);
}

out.AvailableRolesAndUsers = Helper.sortSecurityMembers(out.AvailableRolesAndUsers);

//init user selection 

in.selectedRoleOrUser = in.AvailableRolesAndUsers.get(0);
' #txt
Fs0 f6 security system #txt
Fs0 f6 type ch.ivy.gawfs.workflowCreation.WorkflowDefinition.WorkflowDefinitionData #txt
Fs0 f6 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>init first task</name>
        <nameStyle>15,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Fs0 f6 200 44 112 40 -32 -8 #rect
Fs0 f6 @|StepIcon #fIcon
Fs0 f7 expr out #txt
Fs0 f7 109 64 200 64 #arcP
Fs0 f2 expr out #txt
Fs0 f2 312 64 435 64 #arcP
Fs0 f9 guid 157748D911FE549A #txt
Fs0 f9 type ch.ivy.gawfs.workflowCreation.WorkflowDefinition.WorkflowDefinitionData #txt
Fs0 f9 method addProcessStep() #txt
Fs0 f9 disableUIEvents false #txt
Fs0 f9 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<> param = methodEvent.getInputArguments();
' #txt
Fs0 f9 outParameterDecl '<> result;
' #txt
Fs0 f9 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>addProcessStep()</name>
    </language>
</elementInfo>
' #txt
Fs0 f9 83 147 26 26 -50 15 #rect
Fs0 f9 @|RichDialogMethodStartIcon #fIcon
Fs0 f8 guid 157748DA5B03C33F #txt
Fs0 f8 type ch.ivy.gawfs.workflowCreation.WorkflowDefinition.WorkflowDefinitionData #txt
Fs0 f8 method deleteProcessStep() #txt
Fs0 f8 disableUIEvents false #txt
Fs0 f8 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<> param = methodEvent.getInputArguments();
' #txt
Fs0 f8 outParameterDecl '<> result;
' #txt
Fs0 f8 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>deleteProcessStep()</name>
    </language>
</elementInfo>
' #txt
Fs0 f8 83 243 26 26 -56 15 #rect
Fs0 f8 @|RichDialogMethodStartIcon #fIcon
Fs0 f10 actionDecl 'ch.ivy.gawfs.workflowCreation.WorkflowDefinition.WorkflowDefinitionData out;
' #txt
Fs0 f10 actionTable 'out=in;
' #txt
Fs0 f10 actionCode 'import gawfs.TaskDef;


TaskDef task = new TaskDef();
task.count = in.data.definedTasks.get(in.data.definedTasks.size()-1).count + 1;
task.untilDays = 1;

in.data.definedTasks.add(task);' #txt
Fs0 f10 security system #txt
Fs0 f10 type ch.ivy.gawfs.workflowCreation.WorkflowDefinition.WorkflowDefinitionData #txt
Fs0 f10 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>add new ProcessStep</name>
        <nameStyle>19,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Fs0 f10 192 138 128 44 -60 -8 #rect
Fs0 f10 @|StepIcon #fIcon
Fs0 f11 expr out #txt
Fs0 f11 109 160 192 160 #arcP
Fs0 f12 expr out #txt
Fs0 f12 320 160 448 77 #arcP
Fs0 f12 1 448 160 #addKink
Fs0 f12 0 0.768395079364037 0 0 #arcLabel
Fs0 f13 actionDecl 'ch.ivy.gawfs.workflowCreation.WorkflowDefinition.WorkflowDefinitionData out;
' #txt
Fs0 f13 actionTable 'out=in;
' #txt
Fs0 f13 actionCode 'import gawfs.TaskDef;


TaskDef task = in.data.definedTasks.get(in.data.definedTasks.size()-1);

in.data.definedTasks.remove(task);



' #txt
Fs0 f13 security system #txt
Fs0 f13 type ch.ivy.gawfs.workflowCreation.WorkflowDefinition.WorkflowDefinitionData #txt
Fs0 f13 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>delete last ProcessStep</name>
        <nameStyle>23,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Fs0 f13 184 234 144 44 -65 -8 #rect
Fs0 f13 @|StepIcon #fIcon
Fs0 f14 expr out #txt
Fs0 f14 109 256 184 256 #arcP
Fs0 f15 expr out #txt
Fs0 f15 328 256 448 77 #arcP
Fs0 f15 1 448 256 #addKink
Fs0 f15 0 0.9835526315789473 0 0 #arcLabel
Fs0 f16 guid 157930AFEEC5B0A0 #txt
Fs0 f16 type ch.ivy.gawfs.workflowCreation.WorkflowDefinition.WorkflowDefinitionData #txt
Fs0 f16 method initAssginement(gawfs.TaskDef) #txt
Fs0 f16 disableUIEvents false #txt
Fs0 f16 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<gawfs.TaskDef task> param = methodEvent.getInputArguments();
' #txt
Fs0 f16 inParameterMapAction 'out.openTask=param.task;
' #txt
Fs0 f16 outParameterDecl '<> result;
' #txt
Fs0 f16 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>iniAssginement(TaskDef)</name>
        <nameStyle>23,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Fs0 f16 83 339 26 26 -70 15 #rect
Fs0 f16 @|RichDialogMethodStartIcon #fIcon
Fs0 f20 type ch.ivy.gawfs.workflowCreation.WorkflowDefinition.WorkflowDefinitionData #txt
Fs0 f20 435 339 26 26 0 12 #rect
Fs0 f20 @|RichDialogProcessEndIcon #fIcon
Fs0 f19 guid 1579333111525F97 #txt
Fs0 f19 type ch.ivy.gawfs.workflowCreation.WorkflowDefinition.WorkflowDefinitionData #txt
Fs0 f19 method updateAssignement() #txt
Fs0 f19 disableUIEvents false #txt
Fs0 f19 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<> param = methodEvent.getInputArguments();
' #txt
Fs0 f19 outParameterDecl '<> result;
' #txt
Fs0 f19 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>updateAssignement()</name>
    </language>
</elementInfo>
' #txt
Fs0 f19 83 435 26 26 -59 15 #rect
Fs0 f19 @|RichDialogMethodStartIcon #fIcon
Fs0 f23 actionDecl 'ch.ivy.gawfs.workflowCreation.WorkflowDefinition.WorkflowDefinitionData out;
' #txt
Fs0 f23 actionTable 'out=in;
' #txt
Fs0 f23 actionCode 'out.openTask.actor = in.selectedRoleOrUser.getMemberName();
out.openTask.actorDisplayName = in.selectedRoleOrUser.getDisplayName();
' #txt
Fs0 f23 security system #txt
Fs0 f23 type ch.ivy.gawfs.workflowCreation.WorkflowDefinition.WorkflowDefinitionData #txt
Fs0 f23 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>updateAssignement</name>
        <nameStyle>17,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Fs0 f23 192 426 128 44 -55 -8 #rect
Fs0 f23 @|StepIcon #fIcon
Fs0 f24 expr out #txt
Fs0 f24 109 448 192 448 #arcP
Fs0 f24 0 0.6592264889893998 0 0 #arcLabel
Fs0 f22 expr out #txt
Fs0 f22 320 448 448 365 #arcP
Fs0 f22 1 448 448 #addKink
Fs0 f22 0 0.6592264889893998 0 0 #arcLabel
Fs0 f25 guid 1589422E488B03CA #txt
Fs0 f25 type ch.ivy.gawfs.workflowCreation.WorkflowDefinition.WorkflowDefinitionData #txt
Fs0 f25 method cancel() #txt
Fs0 f25 disableUIEvents false #txt
Fs0 f25 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<> param = methodEvent.getInputArguments();
' #txt
Fs0 f25 outParameterDecl '<> result;
' #txt
Fs0 f25 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>cancel</name>
        <nameStyle>6,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Fs0 f25 83 563 26 26 -18 15 #rect
Fs0 f25 @|RichDialogMethodStartIcon #fIcon
Fs0 f30 type ch.ivy.gawfs.workflowCreation.WorkflowDefinition.WorkflowDefinitionData #txt
Fs0 f30 guid 15AF77AA5D3C3910 #txt
Fs0 f30 435 563 26 26 0 12 #rect
Fs0 f30 @|RichDialogEndIcon #fIcon
Fs0 f28 actionDecl 'ch.ivy.gawfs.workflowCreation.WorkflowDefinition.WorkflowDefinitionData out;
' #txt
Fs0 f28 actionTable 'out=in;
' #txt
Fs0 f28 actionCode 'in.data.discard = true;' #txt
Fs0 f28 security system #txt
Fs0 f28 type ch.ivy.gawfs.workflowCreation.WorkflowDefinition.WorkflowDefinitionData #txt
Fs0 f28 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>discard = true</name>
        <nameStyle>14,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Fs0 f28 200 554 112 44 -37 -8 #rect
Fs0 f28 @|StepIcon #fIcon
Fs0 f32 expr out #txt
Fs0 f32 109 576 200 576 #arcP
Fs0 f31 expr out #txt
Fs0 f31 312 576 435 576 #arcP
Fs0 f26 expr out #txt
Fs0 f26 109 352 435 352 #arcP
Fs0 f17 guid 15F94B0886B676AB #txt
Fs0 f17 type ch.ivy.gawfs.workflowCreation.WorkflowDefinition.WorkflowDefinitionData #txt
Fs0 f17 method autoCompleteFilter(String) #txt
Fs0 f17 disableUIEvents false #txt
Fs0 f17 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<java.lang.String query> param = methodEvent.getInputArguments();
' #txt
Fs0 f17 inParameterMapAction 'out.autoCompleteQuery=param.query;
' #txt
Fs0 f17 outParameterDecl '<java.util.List<ch.ivyteam.ivy.security.ISecurityMember> availableRolesAndUsers> result;
' #txt
Fs0 f17 outActionCode 'import ch.ivy.gawfs.Helper;

result.availableRolesAndUsers = Helper.filterSecurityMembers(in.AvailableRolesAndUsers, in.autoCompleteQuery);' #txt
Fs0 f17 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>autoCompleteFilter(String)</name>
        <nameStyle>26,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Fs0 f17 83 659 26 26 -72 15 #rect
Fs0 f17 @|RichDialogMethodStartIcon #fIcon
Fs0 f18 type ch.ivy.gawfs.workflowCreation.WorkflowDefinition.WorkflowDefinitionData #txt
Fs0 f18 435 659 26 26 0 12 #rect
Fs0 f18 @|RichDialogProcessEndIcon #fIcon
Fs0 f21 expr out #txt
Fs0 f21 109 672 435 672 #arcP
>Proto Fs0 .type ch.ivy.gawfs.workflowCreation.WorkflowDefinition.WorkflowDefinitionData #txt
>Proto Fs0 .processKind HTML_DIALOG #txt
>Proto Fs0 -8 -8 16 16 16 26 #rect
>Proto Fs0 '' #fIcon
Fs0 f3 mainOut f5 tail #connect
Fs0 f5 head f4 mainIn #connect
Fs0 f0 mainOut f7 tail #connect
Fs0 f7 head f6 mainIn #connect
Fs0 f6 mainOut f2 tail #connect
Fs0 f2 head f1 mainIn #connect
Fs0 f9 mainOut f11 tail #connect
Fs0 f11 head f10 mainIn #connect
Fs0 f10 mainOut f12 tail #connect
Fs0 f12 head f1 mainIn #connect
Fs0 f8 mainOut f14 tail #connect
Fs0 f14 head f13 mainIn #connect
Fs0 f13 mainOut f15 tail #connect
Fs0 f15 head f1 mainIn #connect
Fs0 f19 mainOut f24 tail #connect
Fs0 f24 head f23 mainIn #connect
Fs0 f23 mainOut f22 tail #connect
Fs0 f22 head f20 mainIn #connect
Fs0 f25 mainOut f32 tail #connect
Fs0 f32 head f28 mainIn #connect
Fs0 f28 mainOut f31 tail #connect
Fs0 f31 head f30 mainIn #connect
Fs0 f16 mainOut f26 tail #connect
Fs0 f26 head f20 mainIn #connect
Fs0 f17 mainOut f21 tail #connect
Fs0 f21 head f18 mainIn #connect
