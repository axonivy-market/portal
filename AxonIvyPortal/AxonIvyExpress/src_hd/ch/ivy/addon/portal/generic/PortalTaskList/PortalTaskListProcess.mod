[Ivy]
[>Created: Fri Mar 24 15:21:41 CET 2017]
14BEE662F0A69DFE 3.18 #module
>Proto >Proto Collection #zClass
Ps0 PortalTaskListProcess Big #zClass
Ps0 RD #cInfo
Ps0 #process
Ps0 @TextInP .ui2RdDataAction .ui2RdDataAction #zField
Ps0 @TextInP .rdData2UIAction .rdData2UIAction #zField
Ps0 @TextInP .resExport .resExport #zField
Ps0 @TextInP .type .type #zField
Ps0 @TextInP .processKind .processKind #zField
Ps0 @AnnotationInP-0n ai ai #zField
Ps0 @MessageFlowInP-0n messageIn messageIn #zField
Ps0 @MessageFlowOutP-0n messageOut messageOut #zField
Ps0 @TextInP .xml .xml #zField
Ps0 @TextInP .responsibility .responsibility #zField
Ps0 @RichDialogInitStart f0 '' #zField
Ps0 @RichDialogProcessStart f3 '' #zField
Ps0 @RichDialogEnd f4 '' #zField
Ps0 @PushWFArc f5 '' #zField
Ps0 @RichDialogProcessEnd f7 '' #zField
Ps0 @RichDialogMethodStart f11 '' #zField
Ps0 @RichDialogMethodStart f6 '' #zField
Ps0 @RichDialogProcessEnd f10 '' #zField
Ps0 @RichDialogInitStart f16 '' #zField
Ps0 @RichDialogProcessEnd f17 '' #zField
Ps0 @GridStep f21 '' #zField
Ps0 @CallSub f24 '' #zField
Ps0 @GridStep f1 '' #zField
Ps0 @PushWFArc f15 '' #zField
Ps0 @PushWFArc f13 '' #zField
Ps0 @GridStep f14 '' #zField
Ps0 @PushWFArc f25 '' #zField
Ps0 @PushWFArc f20 '' #zField
Ps0 @GridStep f19 '' #zField
Ps0 @PushWFArc f8 '' #zField
Ps0 @GridStep f9 '' #zField
Ps0 @PushWFArc f2 '' #zField
Ps0 @PushWFArc f22 '' #zField
Ps0 @PushWFArc f23 '' #zField
Ps0 @RichDialogProcessStart f26 '' #zField
Ps0 @PushWFArc f27 '' #zField
Ps0 @GridStep f28 '' #zField
Ps0 @PushWFArc f18 '' #zField
Ps0 @PushWFArc f12 '' #zField
Ps0 @PushWFArc f31 '' #zField
Ps0 @GridStep f30 '' #zField
Ps0 @CallSub f39 '' #zField
Ps0 @PushWFArc f32 '' #zField
Ps0 @PushWFArc f29 '' #zField
>Proto Ps0 Ps0 PortalTaskListProcess #zField
Ps0 f0 guid 14BEE662F1E36851 #txt
Ps0 f0 type ch.ivy.addon.portal.generic.PortalTaskList.PortalTaskListData #txt
Ps0 f0 method start() #txt
Ps0 f0 disableUIEvents true #txt
Ps0 f0 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<> param = methodEvent.getInputArguments();
' #txt
Ps0 f0 inActionCode 'out.taskInformationVisible = false;' #txt
Ps0 f0 outParameterDecl '<> result;
' #txt
Ps0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start()</name>
        <nameStyle>7,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ps0 f0 35 83 26 26 -12 -38 #rect
Ps0 f0 @|RichDialogInitStartIcon #fIcon
Ps0 f3 guid 14BEE662F287FC71 #txt
Ps0 f3 type ch.ivy.addon.portal.generic.PortalTaskList.PortalTaskListData #txt
Ps0 f3 actionDecl 'ch.ivy.addon.portal.generic.PortalTaskList.PortalTaskListData out;
' #txt
Ps0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>close</name>
    </language>
</elementInfo>
' #txt
Ps0 f3 563 83 26 26 -16 -43 #rect
Ps0 f3 @|RichDialogProcessStartIcon #fIcon
Ps0 f4 type ch.ivy.addon.portal.generic.PortalTaskList.PortalTaskListData #txt
Ps0 f4 guid 14BEE662F281C986 #txt
Ps0 f4 563 203 26 26 0 12 #rect
Ps0 f4 @|RichDialogEndIcon #fIcon
Ps0 f5 expr out #txt
Ps0 f5 576 109 576 203 #arcP
Ps0 f7 type ch.ivy.addon.portal.generic.PortalTaskList.PortalTaskListData #txt
Ps0 f7 878 278 20 20 13 0 #rect
Ps0 f7 @|RichDialogProcessEndIcon #fIcon
Ps0 f11 guid 14C2C92412CAAB6D #txt
Ps0 f11 type ch.ivy.addon.portal.generic.PortalTaskList.PortalTaskListData #txt
Ps0 f11 method onTaskNodeSelect(org.primefaces.event.NodeSelectEvent) #txt
Ps0 f11 disableUIEvents false #txt
Ps0 f11 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<org.primefaces.event.NodeSelectEvent event> param = methodEvent.getInputArguments();
' #txt
Ps0 f11 inActionCode '
' #txt
Ps0 f11 outParameterDecl '<> result;
' #txt
Ps0 f11 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>onTaskNodeSelect</name>
        <nameStyle>16,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ps0 f11 878 86 20 20 -31 -38 #rect
Ps0 f11 @|RichDialogMethodStartIcon #fIcon
Ps0 f6 guid 14C49C654EDD5C4B #txt
Ps0 f6 type ch.ivy.addon.portal.generic.PortalTaskList.PortalTaskListData #txt
Ps0 f6 method tableRowSelected(org.primefaces.event.SelectEvent) #txt
Ps0 f6 disableUIEvents false #txt
Ps0 f6 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<org.primefaces.event.SelectEvent selection> param = methodEvent.getInputArguments();
' #txt
Ps0 f6 outParameterDecl '<> result;
' #txt
Ps0 f6 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>tableRowSelected</name>
        <nameStyle>16,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ps0 f6 990 86 20 20 -18 -39 #rect
Ps0 f6 @|RichDialogMethodStartIcon #fIcon
Ps0 f10 type ch.ivy.addon.portal.generic.PortalTaskList.PortalTaskListData #txt
Ps0 f10 990 254 20 20 13 0 #rect
Ps0 f10 @|RichDialogProcessEndIcon #fIcon
Ps0 f16 guid 14C6DF9747784EFF #txt
Ps0 f16 type ch.ivy.addon.portal.generic.PortalTaskList.PortalTaskListData #txt
Ps0 f16 method start(Number) #txt
Ps0 f16 disableUIEvents true #txt
Ps0 f16 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<java.lang.Number taskId> param = methodEvent.getInputArguments();
' #txt
Ps0 f16 inParameterMapAction 'out.startTaskId=param.taskId;
' #txt
Ps0 f16 outParameterDecl '<> result;
' #txt
Ps0 f16 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start(taskId)</name>
        <nameStyle>13,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ps0 f16 118 86 20 20 -9 -37 #rect
Ps0 f16 @|RichDialogInitStartIcon #fIcon
Ps0 f17 type ch.ivy.addon.portal.generic.PortalTaskList.PortalTaskListData #txt
Ps0 f17 118 534 20 20 13 0 #rect
Ps0 f17 @|RichDialogProcessEndIcon #fIcon
Ps0 f21 actionDecl 'ch.ivy.addon.portal.generic.PortalTaskList.PortalTaskListData out;
' #txt
Ps0 f21 actionTable 'out=in;
' #txt
Ps0 f21 actionCode 'import ch.ivy.addon.portalkit.util.TaskUtils;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivy.addon.portalkit.util.TaskTreeUtils;

//initialization
in.taskTree = TaskTreeUtils.convertTaskListToRunningTaskTree(Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/treeTask/myTasks"));

out.hasAdministratorWorkflowPermisson = TaskUtils.checkReadAllTasksPermission();

if(out.hasAdministratorWorkflowPermisson){
	in.adminTaskTree = TaskTreeUtils.convertAllTasksToRunningTaskTree(Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/treeTask/allTasks"));
}
' #txt
Ps0 f21 type ch.ivy.addon.portal.generic.PortalTaskList.PortalTaskListData #txt
Ps0 f21 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>query invoked task
&amp; build tree</name>
        <nameStyle>31,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ps0 f21 110 212 36 24 20 -2 #rect
Ps0 f21 @|StepIcon #fIcon
Ps0 f24 type ch.ivy.addon.portal.generic.PortalTaskList.PortalTaskListData #txt
Ps0 f24 processCall 'Functional Processes/GetPortalConfig:call()' #txt
Ps0 f24 doCall true #txt
Ps0 f24 requestActionDecl '<> param;
' #txt
Ps0 f24 responseActionDecl 'ch.ivy.addon.portal.generic.PortalTaskList.PortalTaskListData out;
' #txt
Ps0 f24 responseMappingAction 'out=in;
out.homeLink=result.portalConfig.homeLink;
' #txt
Ps0 f24 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>GetPortalConfig</name>
        <nameStyle>15,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ps0 f24 72 330 112 44 -43 -8 #rect
Ps0 f24 @|CallSubIcon #fIcon
Ps0 f1 actionDecl 'ch.ivy.addon.portal.generic.PortalTaskList.PortalTaskListData out;
' #txt
Ps0 f1 actionTable 'out=in;
' #txt
Ps0 f1 actionCode 'import ch.ivy.addon.portalkit.util.TaskUtils;

//find start_task for selected task in TaskTree & TaskList
in.isTaskSearchMode = true;
in.startTask = TaskUtils.findTaskUserHasPermissionToSee(in.startTaskId);' #txt
Ps0 f1 type ch.ivy.addon.portal.generic.PortalTaskList.PortalTaskListData #txt
Ps0 f1 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>find 
start task</name>
        <nameStyle>16,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ps0 f1 110 148 36 24 20 -2 #rect
Ps0 f1 @|StepIcon #fIcon
Ps0 f15 expr out #txt
Ps0 f15 1000 106 1000 188 #arcP
Ps0 f13 expr out #txt
Ps0 f13 1000 212 1000 254 #arcP
Ps0 f14 actionDecl 'ch.ivy.addon.portal.generic.PortalTaskList.PortalTaskListData out;
' #txt
Ps0 f14 actionTable 'out=in;
' #txt
Ps0 f14 actionCode 'in.taskInformationVisible = true;
in.taskNotFound = false;' #txt
Ps0 f14 type ch.ivy.addon.portal.generic.PortalTaskList.PortalTaskListData #txt
Ps0 f14 982 188 36 24 20 -2 #rect
Ps0 f14 @|StepIcon #fIcon
Ps0 f25 expr out #txt
Ps0 f25 128 300 128 330 #arcP
Ps0 f20 expr out #txt
Ps0 f20 128 236 128 276 #arcP
Ps0 f19 actionDecl 'ch.ivy.addon.portal.generic.PortalTaskList.PortalTaskListData out;
' #txt
Ps0 f19 actionTable 'out=in;
' #txt
Ps0 f19 actionCode 'import ch.ivy.addon.portalkit.bo.TaskNode;
import ch.ivy.addon.portalkit.util.TaskUtils;
import ch.ivy.addon.portalkit.util.TaskTreeUtils;

//initialization
in.customTasks = [];

//TaskTree : selected node from start_task
if(in.#startTask is initialized){
	in.selectedTaskTreeNode = TaskTreeUtils.getIndexOfTree(in.taskTree, in.startTask);
	in.selectedTaskTreeNode.selected = true;
}else{
	TaskNode taskNode = in.taskTree.getChildren().get(0).getData() as TaskNode;
	in.taskTree.getChildren().get(0).selected = true;
	in.customTasks = TaskUtils.convertTasksToCustomTasks(taskNode.getiTasks());
}
//TaskList : 
if(in.#selectedTaskTreeNode is initialized){
	TaskNode taskNode = in.selectedTaskTreeNode.getData() as TaskNode;
	in.customTasks = TaskUtils.convertTasksToCustomTasks(taskNode.getiTasks());
}

//TaskInformation
if(in.#startTask is initialized){
	in.selectedTask = TaskUtils.convertToCustomTask(in.startTask);
	in.taskInformationVisible = true;
} else {
	if(in.isTaskSearchMode) {
		in.taskInformationVisible = true;
		in.taskNotFound = true;
	}	
}' #txt
Ps0 f19 type ch.ivy.addon.portal.generic.PortalTaskList.PortalTaskListData #txt
Ps0 f19 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>init
selection</name>
        <nameStyle>14,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ps0 f19 110 276 36 24 20 -2 #rect
Ps0 f19 @|StepIcon #fIcon
Ps0 f8 expr out #txt
Ps0 f8 888 236 888 278 #arcP
Ps0 f9 actionDecl 'ch.ivy.addon.portal.generic.PortalTaskList.PortalTaskListData out;
' #txt
Ps0 f9 actionTable 'out=in;
' #txt
Ps0 f9 actionCode 'in.taskNotFound = false;' #txt
Ps0 f9 type ch.ivy.addon.portal.generic.PortalTaskList.PortalTaskListData #txt
Ps0 f9 871 212 34 24 20 -2 #rect
Ps0 f9 @|StepIcon #fIcon
Ps0 f2 expr out #txt
Ps0 f2 128 106 128 148 #arcP
Ps0 f22 expr out #txt
Ps0 f22 48 109 110 224 #arcP
Ps0 f22 1 48 224 #addKink
Ps0 f22 0 0.8521469323629549 0 0 #arcLabel
Ps0 f23 expr out #txt
Ps0 f23 128 172 128 212 #arcP
Ps0 f26 guid 15A6E0AFBD81E0D3 #txt
Ps0 f26 type ch.ivy.addon.portal.generic.PortalTaskList.PortalTaskListData #txt
Ps0 f26 actionDecl 'ch.ivy.addon.portal.generic.PortalTaskList.PortalTaskListData out;
' #txt
Ps0 f26 actionTable 'out=in;
' #txt
Ps0 f26 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>selectTaskNode</name>
        <nameStyle>14,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ps0 f26 661 85 22 22 14 0 #rect
Ps0 f26 @|RichDialogProcessStartIcon #fIcon
Ps0 f27 expr out #txt
Ps0 f27 672 107 871 224 #arcP
Ps0 f27 1 672 224 #addKink
Ps0 f27 1 0.3423352058359417 0 0 #arcLabel
Ps0 f28 actionDecl 'ch.ivy.addon.portal.generic.PortalTaskList.PortalTaskListData out;
' #txt
Ps0 f28 actionTable 'out=in;
' #txt
Ps0 f28 actionCode 'import ch.ivy.addon.portalkit.util.UrlHelper;
import ch.ivy.addon.portalkit.util.TaskUtils;
import ch.ivy.addon.portalkit.datamodel.TaskLazyDataModel;

in.tasks = TaskUtils.convertCustomerTasksToTaskBOs(in.customTasks);
in.tasks = TaskUtils.sortTaskByDateNewest(in.tasks);
in.taskLazyDataModel = new TaskLazyDataModel(in.tasks);' #txt
Ps0 f28 type ch.ivy.addon.portal.generic.PortalTaskList.PortalTaskListData #txt
Ps0 f28 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>lazy load task</name>
        <nameStyle>14,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ps0 f28 110 468 36 24 20 -2 #rect
Ps0 f28 @|StepIcon #fIcon
Ps0 f18 expr out #txt
Ps0 f18 128 492 128 534 #arcP
Ps0 f12 expr out #txt
Ps0 f12 888 172 888 212 #arcP
Ps0 f31 expr out #txt
Ps0 f31 888 106 888 148 #arcP
Ps0 f30 actionDecl 'ch.ivy.addon.portal.generic.PortalTaskList.PortalTaskListData out;
' #txt
Ps0 f30 actionTable 'out=in;
' #txt
Ps0 f30 actionCode 'import org.primefaces.model.TreeNode;
import ch.ivy.addon.portalkit.bo.TaskNode;
import org.primefaces.context.RequestContext;
import ch.ivy.addon.portalkit.util.TaskUtils;
import ch.ivy.addon.portalkit.util.UrlHelper;

import ch.ivy.addon.portalkit.datamodel.TaskLazyDataModel;

TaskNode nodeData =  in.selectedTaskTreeNode.getData() as TaskNode;
out.customTasks = TaskUtils.convertTasksToCustomTasks(nodeData.getiTasks());

out.taskInformationVisible = false;

RequestContext.getCurrentInstance().execute("PF(''taskTableVar'').clearFilters();");

in.tasks = TaskUtils.convertCustomerTasksToTaskBOs(in.customTasks);
in.tasks = TaskUtils.sortTaskByDateNewest(in.tasks);
in.taskLazyDataModel = new TaskLazyDataModel(in.tasks);
' #txt
Ps0 f30 type ch.ivy.addon.portal.generic.PortalTaskList.PortalTaskListData #txt
Ps0 f30 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Set data</name>
        <nameStyle>8,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ps0 f30 870 148 36 24 20 -2 #rect
Ps0 f30 @|StepIcon #fIcon
Ps0 f39 type ch.ivy.addon.portal.generic.PortalTaskList.PortalTaskListData #txt
Ps0 f39 processCall 'Functional Processes/CustomPortalListsConfig:call()' #txt
Ps0 f39 doCall true #txt
Ps0 f39 requestActionDecl '<> param;
' #txt
Ps0 f39 responseActionDecl 'ch.ivy.addon.portal.generic.PortalTaskList.PortalTaskListData out;
' #txt
Ps0 f39 responseMappingAction 'out=in;
out.portalListsConfig=result.portalListsConfig;
' #txt
Ps0 f39 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>CustomPortalListsConfig</name>
        <nameStyle>23,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ps0 f39 110 404 36 24 20 -2 #rect
Ps0 f39 @|CallSubIcon #fIcon
Ps0 f32 expr out #txt
Ps0 f32 128 374 128 404 #arcP
Ps0 f29 expr out #txt
Ps0 f29 128 428 128 468 #arcP
>Proto Ps0 .type ch.ivy.addon.portal.generic.PortalTaskList.PortalTaskListData #txt
>Proto Ps0 .processKind HTML_DIALOG #txt
>Proto Ps0 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <swimlaneLabel>Start</swimlaneLabel>
        <swimlaneLabel>Event
</swimlaneLabel>
        <swimlaneLabel>Methods</swimlaneLabel>
        <swimlaneLabel></swimlaneLabel>
    </language>
    <swimlaneSize>504</swimlaneSize>
    <swimlaneSize>248</swimlaneSize>
    <swimlaneSize>352</swimlaneSize>
    <swimlaneColor>-1</swimlaneColor>
    <swimlaneColor>-6684775</swimlaneColor>
    <swimlaneColor>-103</swimlaneColor>
</elementInfo>
' #txt
>Proto Ps0 -8 -8 16 16 16 26 #rect
>Proto Ps0 '' #fIcon
Ps0 f3 mainOut f5 tail #connect
Ps0 f5 head f4 mainIn #connect
Ps0 f9 mainOut f8 tail #connect
Ps0 f8 head f7 mainIn #connect
Ps0 f6 mainOut f15 tail #connect
Ps0 f15 head f14 mainIn #connect
Ps0 f14 mainOut f13 tail #connect
Ps0 f13 head f10 mainIn #connect
Ps0 f21 mainOut f20 tail #connect
Ps0 f20 head f19 mainIn #connect
Ps0 f16 mainOut f2 tail #connect
Ps0 f2 head f1 mainIn #connect
Ps0 f19 mainOut f25 tail #connect
Ps0 f25 head f24 mainIn #connect
Ps0 f0 mainOut f22 tail #connect
Ps0 f22 head f21 mainIn #connect
Ps0 f1 mainOut f23 tail #connect
Ps0 f23 head f21 mainIn #connect
Ps0 f26 mainOut f27 tail #connect
Ps0 f27 head f9 mainIn #connect
Ps0 f28 mainOut f18 tail #connect
Ps0 f18 head f17 mainIn #connect
Ps0 f11 mainOut f31 tail #connect
Ps0 f31 head f30 mainIn #connect
Ps0 f30 mainOut f12 tail #connect
Ps0 f12 head f9 mainIn #connect
Ps0 f24 mainOut f32 tail #connect
Ps0 f32 head f39 mainIn #connect
Ps0 f39 mainOut f29 tail #connect
Ps0 f29 head f28 mainIn #connect
