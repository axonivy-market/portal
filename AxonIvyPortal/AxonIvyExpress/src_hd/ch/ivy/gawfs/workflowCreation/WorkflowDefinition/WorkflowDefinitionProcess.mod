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
Fs0 @GridStep f45 '' #zField
Fs0 @PushWFArc f46 '' #zField
Fs0 @PushWFArc f2 '' #zField
Fs0 @RichDialogMethodStart f47 '' #zField
Fs0 @RichDialogProcessEnd f48 '' #zField
Fs0 @GridStep f49 '' #zField
Fs0 @PushWFArc f51 '' #zField
Fs0 @GridStep f52 '' #zField
Fs0 @PushWFArc f53 '' #zField
Fs0 @PushWFArc f5 '' #zField
Fs0 @RichDialogProcessStart f54 '' #zField
Fs0 @PushWFArc f55 '' #zField
Fs0 @PushWFArc f50 '' #zField
Fs0 @RichDialogProcessEnd f57 '' #zField
Fs0 @RichDialogMethodStart f56 '' #zField
Fs0 @PushWFArc f58 '' #zField
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
Fs0 f1 531 51 26 26 0 12 #rect
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
Fs0 f3 83 571 26 26 -15 12 #rect
Fs0 f3 @|RichDialogProcessStartIcon #fIcon
Fs0 f4 type ch.ivy.gawfs.workflowCreation.WorkflowDefinition.WorkflowDefinitionData #txt
Fs0 f4 guid 1576FA61CB08591F #txt
Fs0 f4 435 571 26 26 0 12 #rect
Fs0 f4 @|RichDialogEndIcon #fIcon
Fs0 f6 actionDecl 'ch.ivy.gawfs.workflowCreation.WorkflowDefinition.WorkflowDefinitionData out;
' #txt
Fs0 f6 actionTable 'out=in;
out.one=1;
' #txt
Fs0 f6 actionCode 'import ch.ivy.gawfs.enums.ProcessType;
import java.util.ArrayList;
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

in.isAssignToUser = true;
if (!in.data.#isUseDefaultUI is initialized) {
	in.data.isUseDefaultUI = false;
}
if (!in.data.#processType is initialized) {
	in.data.processType = ProcessType.AD_HOC;
}' #txt
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
Fs0 f12 320 160 544 77 #arcP
Fs0 f12 1 544 160 #addKink
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
Fs0 f15 328 256 544 77 #arcP
Fs0 f15 1 544 256 #addKink
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
if (out.data.isUseDefaultUI) {
	out.selectedResponsibleFieldId =  "form:defined-tasks-list:" + indexOfSelectedTaskDef + ":default-task-responsible";
} else {
	out.selectedResponsibleFieldId =  "form:defined-tasks-list:" + indexOfSelectedTaskDef + ":task-responsible";
}
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
Fs0 f20 659 339 26 26 0 12 #rect
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
Fs0 f25 83 635 26 26 -18 15 #rect
Fs0 f25 @|RichDialogMethodStartIcon #fIcon
Fs0 f30 type ch.ivy.gawfs.workflowCreation.WorkflowDefinition.WorkflowDefinitionData #txt
Fs0 f30 guid 15AF77AA5D3C3910 #txt
Fs0 f30 435 635 26 26 0 12 #rect
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
        <name>Turn on discard flag</name>
    </language>
</elementInfo>
' #txt
Fs0 f28 192 626 128 44 -54 -8 #rect
Fs0 f28 @|StepIcon #fIcon
Fs0 f32 expr out #txt
Fs0 f32 109 648 192 648 #arcP
Fs0 f31 expr out #txt
Fs0 f31 320 648 435 648 #arcP
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
Fs0 f17 83 819 26 26 -40 15 #rect
Fs0 f17 @|RichDialogMethodStartIcon #fIcon
Fs0 f18 type ch.ivy.gawfs.workflowCreation.WorkflowDefinition.WorkflowDefinitionData #txt
Fs0 f18 659 819 26 26 0 12 #rect
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
Fs0 f36 360 816 32 32 -27 -37 #rect
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
Fs0 f38 472 810 112 44 -38 -8 #rect
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
Fs0 f39 392 832 472 832 #arcP
Fs0 f39 0 0.425 0 -11 #arcLabel
Fs0 f21 expr out #txt
Fs0 f21 584 832 659 832 #arcP
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
Fs0 f40 376 848 672 845 #arcP
Fs0 f40 1 376 896 #addKink
Fs0 f40 2 672 896 #addKink
Fs0 f40 1 0.17229729729729729 0 -10 #arcLabel
Fs0 f34 actionDecl 'ch.ivy.gawfs.workflowCreation.WorkflowDefinition.WorkflowDefinitionData out;
' #txt
Fs0 f34 actionTable 'out=in;
' #txt
Fs0 f34 actionCode 'import org.apache.commons.lang3.StringUtils;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import ch.ivyteam.ivy.security.ISecurityMember;

ISecurityMember selectedAssignee = in.#selectedUser is initialized ? in.selectedUser : in.selectedRole;
in.isValidAssignee = true;

if (!(#selectedAssignee is initialized) || in.selectedAssigneeList.contains(selectedAssignee)) {
	in.isValidAssignee = false;
	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", ivy.cms.co("/Dialogs/agileBPM/define_WF/ErrorSelectInvalidAssignee")));
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
Fs0 f34 192 810 112 44 -22 -8 #rect
Fs0 f34 @|StepIcon #fIcon
Fs0 f35 expr out #txt
Fs0 f35 109 832 192 832 #arcP
Fs0 f37 expr out #txt
Fs0 f37 304 832 360 832 #arcP
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
Fs0 f41 83 723 26 26 -59 15 #rect
Fs0 f41 @|RichDialogProcessStartIcon #fIcon
Fs0 f42 type ch.ivy.gawfs.workflowCreation.WorkflowDefinition.WorkflowDefinitionData #txt
Fs0 f42 435 723 26 26 0 12 #rect
Fs0 f42 @|RichDialogProcessEndIcon #fIcon
Fs0 f43 expr out #txt
Fs0 f43 109 736 435 736 #arcP
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
Fs0 f22 496 448 672 365 #arcP
Fs0 f22 1 672 448 #addKink
Fs0 f22 0 0.6592264889893998 0 0 #arcLabel
Fs0 f33 actionDecl 'ch.ivy.gawfs.workflowCreation.WorkflowDefinition.WorkflowDefinitionData out;
' #txt
Fs0 f33 actionTable 'out=in;
' #txt
Fs0 f33 actionCode 'import javax.faces.context.FacesContext;
import ch.ivyteam.ivy.security.ISecurityMember;

for (String assigneeName : in.taskDefinition.responsibles) {
	ISecurityMember assignee = ivy.session.getSecurityContext().findSecurityMember(assigneeName);
	if (#assignee is initialized) {
		if (!in.selectedAssigneeList.contains(assignee)) {
			in.selectedAssigneeList.add(assignee);
		}
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
Fs0 f26 304 352 659 352 #arcP
Fs0 f45 actionDecl 'ch.ivy.gawfs.workflowCreation.WorkflowDefinition.WorkflowDefinitionData out;
' #txt
Fs0 f45 actionTable 'out=in;
' #txt
Fs0 f45 actionCode 'import ch.ivy.gawfs.DragAndDropController;
import ch.ivy.gawfs.DynaFormController;

in.defaultDragAndDropController = new DragAndDropController();
in.defaultDragAndDropController.dynaFormController = new DynaFormController(in.defaultDragAndDropController);
in.defaultDragAndDropController.dynaFormController.initializeDefaultUI();' #txt
Fs0 f45 type ch.ivy.gawfs.workflowCreation.WorkflowDefinition.WorkflowDefinitionData #txt
Fs0 f45 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Initialize default UI</name>
    </language>
</elementInfo>
' #txt
Fs0 f45 360 42 112 44 -49 -8 #rect
Fs0 f45 @|StepIcon #fIcon
Fs0 f46 expr out #txt
Fs0 f46 312 64 360 64 #arcP
Fs0 f2 expr out #txt
Fs0 f2 472 64 531 64 #arcP
Fs0 f47 guid 16347C45756F58EB #txt
Fs0 f47 type ch.ivy.gawfs.workflowCreation.WorkflowDefinition.WorkflowDefinitionData #txt
Fs0 f47 method changeUISetting(Boolean) #txt
Fs0 f47 disableUIEvents false #txt
Fs0 f47 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<java.lang.Boolean isAgreed> param = methodEvent.getInputArguments();
' #txt
Fs0 f47 inActionCode 'out.data.isUseDefaultUI = param.isAgreed ?  out.data.isUseDefaultUI : !out.data.isUseDefaultUI;
out.data.processType = out.data.processType;' #txt
Fs0 f47 outParameterDecl '<> result;
' #txt
Fs0 f47 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>changeUISetting()</name>
        <nameStyle>17,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Fs0 f47 819 339 26 26 -49 15 #rect
Fs0 f47 @|RichDialogMethodStartIcon #fIcon
Fs0 f48 type ch.ivy.gawfs.workflowCreation.WorkflowDefinition.WorkflowDefinitionData #txt
Fs0 f48 1171 339 26 26 0 12 #rect
Fs0 f48 @|RichDialogProcessEndIcon #fIcon
Fs0 f49 actionDecl 'ch.ivy.gawfs.workflowCreation.WorkflowDefinition.WorkflowDefinitionData out;
' #txt
Fs0 f49 actionTable 'out=in;
' #txt
Fs0 f49 actionCode 'import java.util.ArrayList;
import gawfs.TaskDef;

TaskDef task = new TaskDef();
task.position = 1;
task.untilDays = 1;
task.responsibles = new ArrayList();

in.data.definedTasks.clear();
in.data.definedTasks.add(task);' #txt
Fs0 f49 type ch.ivy.gawfs.workflowCreation.WorkflowDefinition.WorkflowDefinitionData #txt
Fs0 f49 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Reset defined tasks</name>
    </language>
</elementInfo>
' #txt
Fs0 f49 992 330 128 44 -55 -8 #rect
Fs0 f49 @|StepIcon #fIcon
Fs0 f51 expr out #txt
Fs0 f51 1120 352 1171 352 #arcP
Fs0 f52 actionDecl 'ch.ivy.gawfs.workflowCreation.WorkflowDefinition.WorkflowDefinitionData out;
' #txt
Fs0 f52 actionTable 'out=in;
' #txt
Fs0 f52 actionCode 'import ch.ivy.gawfs.enums.TaskType;
import gawfs.TaskDef;

if (in.data.isUseDefaultUI) {
	String nameOfUserTask = in.data.definedTasks.get(0).subject;
	for (TaskDef taskDef : in.data.definedTasks) {
		if (in.data.definedTasks.indexOf(taskDef) == 0) {
			taskDef.taskType = TaskType.USER_TASK;
			taskDef.dragAndDropController = in.defaultDragAndDropController;
			taskDef.dynaFormController = in.defaultDragAndDropController.dynaFormController;
		} else {
			taskDef.taskType = TaskType.APPROVAL;
			taskDef.subject = ivy.cms.co("/Dialogs/workflowCreation/TaskType/Approval") + " " + in.data.definedTasks.indexOf(taskDef) + ": " + nameOfUserTask;
		}
	}
}' #txt
Fs0 f52 type ch.ivy.gawfs.workflowCreation.WorkflowDefinition.WorkflowDefinitionData #txt
Fs0 f52 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Update list when&#xD;
use default template</name>
    </language>
</elementInfo>
' #txt
Fs0 f52 192 562 144 44 -52 -16 #rect
Fs0 f52 @|StepIcon #fIcon
Fs0 f53 expr out #txt
Fs0 f53 109 584 192 584 #arcP
Fs0 f5 expr out #txt
Fs0 f5 336 584 435 584 #arcP
Fs0 f54 guid 163497DDEE05D6E5 #txt
Fs0 f54 type ch.ivy.gawfs.workflowCreation.WorkflowDefinition.WorkflowDefinitionData #txt
Fs0 f54 actionDecl 'ch.ivy.gawfs.workflowCreation.WorkflowDefinition.WorkflowDefinitionData out;
' #txt
Fs0 f54 actionTable 'out=in;
' #txt
Fs0 f54 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>closeDialog</name>
        <nameStyle>11,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Fs0 f54 83 499 26 26 -33 15 #rect
Fs0 f54 @|RichDialogProcessStartIcon #fIcon
Fs0 f55 expr out #txt
Fs0 f55 109 512 440 470 #arcP
Fs0 f55 1 440 512 #addKink
Fs0 f55 0 0.8593296567373708 0 0 #arcLabel
Fs0 f50 expr out #txt
Fs0 f50 845 352 992 352 #arcP
Fs0 f57 type ch.ivy.gawfs.workflowCreation.WorkflowDefinition.WorkflowDefinitionData #txt
Fs0 f57 1171 467 26 26 0 12 #rect
Fs0 f57 @|RichDialogProcessEndIcon #fIcon
Fs0 f56 guid 1634EB11643305EE #txt
Fs0 f56 type ch.ivy.gawfs.workflowCreation.WorkflowDefinition.WorkflowDefinitionData #txt
Fs0 f56 method removeSelectedAssignee(ch.ivyteam.ivy.security.ISecurityMember) #txt
Fs0 f56 disableUIEvents false #txt
Fs0 f56 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<ch.ivyteam.ivy.security.ISecurityMember assignee> param = methodEvent.getInputArguments();
' #txt
Fs0 f56 inActionCode out.selectedAssigneeList.remove(param.assignee); #txt
Fs0 f56 outParameterDecl '<> result;
' #txt
Fs0 f56 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>removeSelectedAssignee(ISecurityMember)</name>
        <nameStyle>39,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Fs0 f56 819 467 26 26 -120 15 #rect
Fs0 f56 @|RichDialogMethodStartIcon #fIcon
Fs0 f58 expr out #txt
Fs0 f58 845 480 1171 480 #arcP
>Proto Fs0 .type ch.ivy.gawfs.workflowCreation.WorkflowDefinition.WorkflowDefinitionData #txt
>Proto Fs0 .processKind HTML_DIALOG #txt
>Proto Fs0 -8 -8 16 16 16 26 #rect
>Proto Fs0 '' #fIcon
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
Fs0 f6 mainOut f46 tail #connect
Fs0 f46 head f45 mainIn #connect
Fs0 f45 mainOut f2 tail #connect
Fs0 f2 head f1 mainIn #connect
Fs0 f49 mainOut f51 tail #connect
Fs0 f51 head f48 mainIn #connect
Fs0 f3 mainOut f53 tail #connect
Fs0 f53 head f52 mainIn #connect
Fs0 f52 mainOut f5 tail #connect
Fs0 f5 head f4 mainIn #connect
Fs0 f54 mainOut f55 tail #connect
Fs0 f55 head f27 mainIn #connect
Fs0 f47 mainOut f50 tail #connect
Fs0 f50 head f49 mainIn #connect
Fs0 f56 mainOut f58 tail #connect
Fs0 f58 head f57 mainIn #connect
