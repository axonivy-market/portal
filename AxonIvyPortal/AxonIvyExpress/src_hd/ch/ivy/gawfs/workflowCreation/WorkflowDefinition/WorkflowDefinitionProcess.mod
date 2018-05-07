[Ivy]
1576FA61C4EDC8B1 3.23 #module
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
Fs0 @RichDialogMethodStart f25 '' #zField
Fs0 @RichDialogEnd f30 '' #zField
Fs0 @GridStep f28 '' #zField
Fs0 @PushWFArc f32 '' #zField
Fs0 @PushWFArc f31 '' #zField
Fs0 @PushWFArc f2 '' #zField
Fs0 @RichDialogMethodStart f17 '' #zField
Fs0 @RichDialogProcessEnd f18 '' #zField
Fs0 @Alternative f36 '' #zField
Fs0 @GridStep f38 '' #zField
Fs0 @PushWFArc f39 '' #zField
Fs0 @PushWFArc f21 '' #zField
Fs0 @PushWFArc f40 '' #zField
Fs0 @GridStep f34 '' #zField
Fs0 @PushWFArc f35 '' #zField
Fs0 @PushWFArc f37 '' #zField
Fs0 @RichDialogProcessStart f41 '' #zField
Fs0 @RichDialogProcessEnd f42 '' #zField
Fs0 @PushWFArc f43 '' #zField
Fs0 @GridStep f27 '' #zField
Fs0 @PushWFArc f29 '' #zField
Fs0 @PushWFArc f22 '' #zField
Fs0 @GridStep f33 '' #zField
Fs0 @PushWFArc f44 '' #zField
Fs0 @PushWFArc f26 '' #zField
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
Fs0 f3 83 531 26 26 -15 12 #rect
Fs0 f3 @|RichDialogProcessStartIcon #fIcon
Fs0 f4 type ch.ivy.gawfs.workflowCreation.WorkflowDefinition.WorkflowDefinitionData #txt
Fs0 f4 guid 1576FA61CB08591F #txt
Fs0 f4 435 531 26 26 0 12 #rect
Fs0 f4 @|RichDialogEndIcon #fIcon
Fs0 f5 expr out #txt
Fs0 f5 109 544 435 544 #arcP
Fs0 f6 actionDecl 'ch.ivy.gawfs.workflowCreation.WorkflowDefinition.WorkflowDefinitionData out;
' #txt
Fs0 f6 actionTable 'out=in;
out.one=1;
' #txt
Fs0 f6 actionCode 'import java.util.ArrayList;
import java.util.Collections;
import ch.ivyteam.ivy.security.IRole;
import ch.ivy.gawfs.Helper;
import gawfs.TaskDef;
import ch.ivyteam.ivy.security.IUser;


ivy.task.setName(ivy.cms.co("/Dialogs/workflowCreation/WorkflowDefinition/WorkflowPropertiesStep"));
ivy.task.setDescription(ivy.cms.co("/Dialogs/Tasks/WorkflowProperties/TaskDescription"));


if(in.data.definedTasks.size()<1){
	TaskDef task = new TaskDef();
	task.position = 1;
	task.untilDays = 1;
	task.responsibles = new ArrayList();
	in.data.definedTasks.add(task);
}else{
	in.data.definedTasks = Helper.sortTasks(in.data.definedTasks);
	
}

in.isAssignToUser = true;' #txt
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
Fs0 f10 actionCode 'import java.util.ArrayList;
import gawfs.TaskDef;


TaskDef task = new TaskDef();
task.position = in.data.definedTasks.get(in.data.definedTasks.size()-1).position + 1;
task.untilDays = 1;
task.responsibles = new ArrayList();

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
<gawfs.TaskDef taskDefinition> param = methodEvent.getInputArguments();
' #txt
Fs0 f16 inParameterMapAction 'out.taskDefinition=param.taskDefinition;
' #txt
Fs0 f16 inActionCode 'int indexOfSelectedTaskDef = out.data.definedTasks.indexOf(param.taskDefinition);
out.selectedResponsibleFieldId =  "form:defined-tasks-list:" + indexOfSelectedTaskDef + ":task-responsible";' #txt
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
Fs0 f20 595 339 26 26 0 12 #rect
Fs0 f20 @|RichDialogProcessEndIcon #fIcon
Fs0 f19 guid 1579333111525F97 #txt
Fs0 f19 type ch.ivy.gawfs.workflowCreation.WorkflowDefinition.WorkflowDefinitionData #txt
Fs0 f19 method updateResponsibles() #txt
Fs0 f19 disableUIEvents false #txt
Fs0 f19 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<> param = methodEvent.getInputArguments();
' #txt
Fs0 f19 outParameterDecl '<> result;
' #txt
Fs0 f19 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>updateResponsibles()</name>
        <nameStyle>20,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Fs0 f19 83 435 26 26 -62 15 #rect
Fs0 f19 @|RichDialogMethodStartIcon #fIcon
Fs0 f23 actionDecl 'ch.ivy.gawfs.workflowCreation.WorkflowDefinition.WorkflowDefinitionData out;
' #txt
Fs0 f23 actionTable 'out=in;
' #txt
Fs0 f23 actionCode 'import org.apache.commons.lang3.StringUtils;
import ch.ivyteam.ivy.security.ISecurityMember;
import java.util.ArrayList;

List<String> responsibleNames = new ArrayList();
out.taskDefinition.responsibles.clear();

for (ISecurityMember responsible : out.selectedAssigneeList) {
		out.taskDefinition.responsibles.add(responsible.getMemberName());

		if (!StringUtils.isBlank(responsible.getDisplayName())) {
    	responsibleNames.add(responsible.getDisplayName());
  	} else {
    	responsibleNames.add(responsible.getName());
  	}
}

out.taskDefinition.responsibleDisplayName = String.join(", ", responsibleNames);' #txt
Fs0 f23 security system #txt
Fs0 f23 type ch.ivy.gawfs.workflowCreation.WorkflowDefinition.WorkflowDefinitionData #txt
Fs0 f23 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>update responsibles</name>
    </language>
</elementInfo>
' #txt
Fs0 f23 192 426 128 44 -57 -8 #rect
Fs0 f23 @|StepIcon #fIcon
Fs0 f24 expr out #txt
Fs0 f24 109 448 192 448 #arcP
Fs0 f24 0 0.6592264889893998 0 0 #arcLabel
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
Fs0 f25 83 595 26 26 -18 15 #rect
Fs0 f25 @|RichDialogMethodStartIcon #fIcon
Fs0 f30 type ch.ivy.gawfs.workflowCreation.WorkflowDefinition.WorkflowDefinitionData #txt
Fs0 f30 guid 15AF77AA5D3C3910 #txt
Fs0 f30 435 595 26 26 0 12 #rect
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
Fs0 f28 192 586 112 44 -37 -8 #rect
Fs0 f28 @|StepIcon #fIcon
Fs0 f32 expr out #txt
Fs0 f32 109 608 192 608 #arcP
Fs0 f31 expr out #txt
Fs0 f31 304 608 435 608 #arcP
Fs0 f2 expr out #txt
Fs0 f2 312 64 435 64 #arcP
Fs0 f17 guid 1628E5D94C6FDB31 #txt
Fs0 f17 type ch.ivy.gawfs.workflowCreation.WorkflowDefinition.WorkflowDefinitionData #txt
Fs0 f17 method addAssignee() #txt
Fs0 f17 disableUIEvents false #txt
Fs0 f17 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<> param = methodEvent.getInputArguments();
' #txt
Fs0 f17 outParameterDecl '<> result;
' #txt
Fs0 f17 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>addAssignee()</name>
        <nameStyle>13,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Fs0 f17 83 787 26 26 -40 15 #rect
Fs0 f17 @|RichDialogMethodStartIcon #fIcon
Fs0 f18 type ch.ivy.gawfs.workflowCreation.WorkflowDefinition.WorkflowDefinitionData #txt
Fs0 f18 595 787 26 26 0 12 #rect
Fs0 f18 @|RichDialogProcessEndIcon #fIcon
Fs0 f36 type ch.ivy.gawfs.workflowCreation.WorkflowDefinition.WorkflowDefinitionData #txt
Fs0 f36 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Validated?</name>
        <nameStyle>10
</nameStyle>
    </language>
</elementInfo>
' #txt
Fs0 f36 336 784 32 32 -27 -37 #rect
Fs0 f36 @|AlternativeIcon #fIcon
Fs0 f38 actionDecl 'ch.ivy.gawfs.workflowCreation.WorkflowDefinition.WorkflowDefinitionData out;
' #txt
Fs0 f38 actionTable 'out=in;
' #txt
Fs0 f38 actionCode 'import ch.ivyteam.ivy.security.ISecurityMember;

ISecurityMember selectedAssignee = in.#selectedUser is initialized ? in.selectedUser : in.selectedRole;
in.selectedAssigneeList.add(selectedAssignee);' #txt
Fs0 f38 type ch.ivy.gawfs.workflowCreation.WorkflowDefinition.WorkflowDefinitionData #txt
Fs0 f38 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Add assignee</name>
    </language>
</elementInfo>
' #txt
Fs0 f38 448 778 112 44 -38 -8 #rect
Fs0 f38 @|StepIcon #fIcon
Fs0 f39 expr in #txt
Fs0 f39 outCond in.isValidAssignee #txt
Fs0 f39 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>YES</name>
        <nameStyle>3
</nameStyle>
    </language>
</elementInfo>
' #txt
Fs0 f39 368 800 448 800 #arcP
Fs0 f39 0 0.425 0 -11 #arcLabel
Fs0 f21 expr out #txt
Fs0 f21 560 800 595 800 #arcP
Fs0 f40 expr in #txt
Fs0 f40 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>NO</name>
        <nameStyle>2
</nameStyle>
    </language>
</elementInfo>
' #txt
Fs0 f40 352 816 608 813 #arcP
Fs0 f40 1 352 864 #addKink
Fs0 f40 2 608 864 #addKink
Fs0 f40 1 0.1875 0 -10 #arcLabel
Fs0 f34 actionDecl 'ch.ivy.gawfs.workflowCreation.WorkflowDefinition.WorkflowDefinitionData out;
' #txt
Fs0 f34 actionTable 'out=in;
' #txt
Fs0 f34 actionCode 'import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import ch.ivyteam.ivy.security.ISecurityMember;

ISecurityMember selectedAssignee = in.#selectedUser is initialized ? in.selectedUser : in.selectedRole;
in.isValidAssignee = true;

if (in.selectedAssigneeList.contains(selectedAssignee)) {
	in.isValidAssignee = false;
	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", ivy.cms.co("/Dialogs/agileBPM/define_WF/ErrorSelectInvalidUser")));
}
' #txt
Fs0 f34 type ch.ivy.gawfs.workflowCreation.WorkflowDefinition.WorkflowDefinitionData #txt
Fs0 f34 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Validate</name>
    </language>
</elementInfo>
' #txt
Fs0 f34 168 778 112 44 -22 -8 #rect
Fs0 f34 @|StepIcon #fIcon
Fs0 f35 expr out #txt
Fs0 f35 109 800 168 800 #arcP
Fs0 f37 expr out #txt
Fs0 f37 280 800 336 800 #arcP
Fs0 f41 guid 1628EC1A853E57B8 #txt
Fs0 f41 type ch.ivy.gawfs.workflowCreation.WorkflowDefinition.WorkflowDefinitionData #txt
Fs0 f41 actionDecl 'ch.ivy.gawfs.workflowCreation.WorkflowDefinition.WorkflowDefinitionData out;
' #txt
Fs0 f41 actionTable 'out=in;
' #txt
Fs0 f41 actionCode 'if (out.isAssignToUser) {
	out.selectedRole = null;
} else {
	out.selectedUser = null;
}' #txt
Fs0 f41 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>changeAssigneeType</name>
        <nameStyle>18,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Fs0 f41 83 691 26 26 -59 15 #rect
Fs0 f41 @|RichDialogProcessStartIcon #fIcon
Fs0 f42 type ch.ivy.gawfs.workflowCreation.WorkflowDefinition.WorkflowDefinitionData #txt
Fs0 f42 435 691 26 26 0 12 #rect
Fs0 f42 @|RichDialogProcessEndIcon #fIcon
Fs0 f43 expr out #txt
Fs0 f43 109 704 435 704 #arcP
Fs0 f27 actionDecl 'ch.ivy.gawfs.workflowCreation.WorkflowDefinition.WorkflowDefinitionData out;
' #txt
Fs0 f27 actionTable 'out=in;
' #txt
Fs0 f27 actionCode 'import java.util.ArrayList;

out.selectedAssigneeList = new ArrayList();
out.selectedRole = null;
out.selectedUser = null;
out.isAssignToUser = true;' #txt
Fs0 f27 type ch.ivy.gawfs.workflowCreation.WorkflowDefinition.WorkflowDefinitionData #txt
Fs0 f27 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Reset dialog inputs</name>
    </language>
</elementInfo>
' #txt
Fs0 f27 384 426 112 44 -53 -8 #rect
Fs0 f27 @|StepIcon #fIcon
Fs0 f29 expr out #txt
Fs0 f29 320 448 384 448 #arcP
Fs0 f29 0 0.6592264889893998 0 0 #arcLabel
Fs0 f22 expr out #txt
Fs0 f22 496 448 608 365 #arcP
Fs0 f22 1 608 448 #addKink
Fs0 f22 0 0.6592264889893998 0 0 #arcLabel
Fs0 f33 actionDecl 'ch.ivy.gawfs.workflowCreation.WorkflowDefinition.WorkflowDefinitionData out;
' #txt
Fs0 f33 actionTable 'out=in;
' #txt
Fs0 f33 actionCode 'import ch.ivyteam.ivy.security.ISecurityMember;

for (String assigneeName : in.taskDefinition.responsibles) {
	ISecurityMember assignee = ivy.session.getSecurityContext().findSecurityMember(assigneeName);
	if (#assignee is initialized) {
		in.selectedAssigneeList.add(assignee);
	}
}' #txt
Fs0 f33 type ch.ivy.gawfs.workflowCreation.WorkflowDefinition.WorkflowDefinitionData #txt
Fs0 f33 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Initialize dialog</name>
    </language>
</elementInfo>
' #txt
Fs0 f33 192 330 112 44 -40 -8 #rect
Fs0 f33 @|StepIcon #fIcon
Fs0 f44 expr out #txt
Fs0 f44 109 352 192 352 #arcP
Fs0 f26 expr out #txt
Fs0 f26 304 352 595 352 #arcP
>Proto Fs0 .type ch.ivy.gawfs.workflowCreation.WorkflowDefinition.WorkflowDefinitionData #txt
>Proto Fs0 .processKind HTML_DIALOG #txt
>Proto Fs0 -8 -8 16 16 16 26 #rect
>Proto Fs0 '' #fIcon
Fs0 f3 mainOut f5 tail #connect
Fs0 f5 head f4 mainIn #connect
Fs0 f0 mainOut f7 tail #connect
Fs0 f7 head f6 mainIn #connect
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
Fs0 f25 mainOut f32 tail #connect
Fs0 f32 head f28 mainIn #connect
Fs0 f28 mainOut f31 tail #connect
Fs0 f31 head f30 mainIn #connect
Fs0 f6 mainOut f2 tail #connect
Fs0 f2 head f1 mainIn #connect
Fs0 f36 out f39 tail #connect
Fs0 f39 head f38 mainIn #connect
Fs0 f38 mainOut f21 tail #connect
Fs0 f21 head f18 mainIn #connect
Fs0 f36 out f40 tail #connect
Fs0 f40 head f18 mainIn #connect
Fs0 f17 mainOut f35 tail #connect
Fs0 f35 head f34 mainIn #connect
Fs0 f34 mainOut f37 tail #connect
Fs0 f37 head f36 in #connect
Fs0 f41 mainOut f43 tail #connect
Fs0 f43 head f42 mainIn #connect
Fs0 f23 mainOut f29 tail #connect
Fs0 f29 head f27 mainIn #connect
Fs0 f27 mainOut f22 tail #connect
Fs0 f22 head f20 mainIn #connect
Fs0 f16 mainOut f44 tail #connect
Fs0 f44 head f33 mainIn #connect
Fs0 f33 mainOut f26 tail #connect
Fs0 f26 head f20 mainIn #connect
