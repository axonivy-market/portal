[Ivy]
156F869FC3FCD1D9 3.23 #module
>Proto >Proto Collection #zClass
Ps0 PortalTaskMenuProcess Big #zClass
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
Ps0 @RichDialogProcessEnd f4 '' #zField
Ps0 @GridStep f9 '' #zField
Ps0 @CallSub f38 '' #zField
Ps0 @GridStep f7 '' #zField
Ps0 @PushWFArc f17 '' #zField
Ps0 @GridStep f28 '' #zField
Ps0 @PushWFArc f30 '' #zField
Ps0 @CallSub f33 '' #zField
Ps0 @RichDialogInitStart f0 '' #zField
Ps0 @RichDialogProcessEnd f1 '' #zField
Ps0 @PushWFArc f2 '' #zField
Ps0 @RichDialogMethodStart f3 '' #zField
Ps0 @PushWFArc f15 '' #zField
Ps0 @RichDialogProcessStart f12 '' #zField
Ps0 @PushWFArc f16 '' #zField
Ps0 @CallSub f14 '' #zField
Ps0 @PushWFArc f19 '' #zField
Ps0 @Split f5 '' #zField
Ps0 @PushWFArc f6 '' #zField
Ps0 @Join f8 '' #zField
Ps0 @CallSub f20 '' #zField
Ps0 @CallSub f26 '' #zField
Ps0 @SJArc f31 '' #zField
Ps0 @GridStep f32 '' #zField
Ps0 @PushWFArc f27 '' #zField
Ps0 @PushWFArc f35 '' #zField
Ps0 @CallSub f36 '' #zField
Ps0 @GridStep f37 '' #zField
Ps0 @PushWFArc f39 '' #zField
Ps0 @PushWFArc f40 '' #zField
Ps0 @SJArc f41 '' #zField
Ps0 @GridStep f42 '' #zField
Ps0 @CallSub f43 '' #zField
Ps0 @PushWFArc f44 '' #zField
Ps0 @Alternative f45 '' #zField
Ps0 @PushWFArc f46 '' #zField
Ps0 @Alternative f47 '' #zField
Ps0 @PushWFArc f48 '' #zField
Ps0 @SJArc f49 '' #zField
Ps0 @PushWFArc f50 '' #zField
Ps0 @PushWFArc f51 '' #zField
Ps0 @GridStep f13 '' #zField
Ps0 @PushWFArc f18 '' #zField
Ps0 @PushWFArc f29 '' #zField
Ps0 @PushWFArc f52 '' #zField
Ps0 @SJArc f10 '' #zField
Ps0 @PushWFArc f34 '' #zField
>Proto Ps0 Ps0 PortalTaskMenuProcess #zField
Ps0 f4 type ch.ivy.addon.portal.generic.PortalTaskMenu.PortalTaskMenuData #txt
Ps0 f4 1107 211 26 26 0 12 #rect
Ps0 f4 @|RichDialogProcessEndIcon #fIcon
Ps0 f9 actionDecl 'ch.ivy.addon.portal.generic.PortalTaskMenu.PortalTaskMenuData out;
' #txt
Ps0 f9 actionTable 'out=in;
' #txt
Ps0 f9 actionCode 'import ch.ivy.addon.portalkit.util.PermissionUtils;

in.hasReadAllTasksPermisson = PermissionUtils.checkReadAllTasksPermission();' #txt
Ps0 f9 type ch.ivy.addon.portal.generic.PortalTaskMenu.PortalTaskMenuData #txt
Ps0 f9 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Check permission</name>
        <nameStyle>16,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ps0 f9 320 200 128 48 -45 -8 #rect
Ps0 f9 @|StepIcon #fIcon
Ps0 f38 type ch.ivy.addon.portal.generic.PortalTaskMenu.PortalTaskMenuData #txt
Ps0 f38 processCall 'Functional Processes/OpenPortalTasks:useView(ch.ivy.addon.portal.generic.view.TaskView)' #txt
Ps0 f38 doCall true #txt
Ps0 f38 requestActionDecl '<ch.ivy.addon.portal.generic.view.TaskView taskView> param;
' #txt
Ps0 f38 requestMappingAction 'param.taskView=in.taskView;
' #txt
Ps0 f38 responseActionDecl 'ch.ivy.addon.portal.generic.PortalTaskMenu.PortalTaskMenuData out;
' #txt
Ps0 f38 responseMappingAction 'out=in;
' #txt
Ps0 f38 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>OpenPortalTasks</name>
        <nameStyle>15,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ps0 f38 632 392 128 48 -48 -12 #rect
Ps0 f38 @|CallSubIcon #fIcon
Ps0 f7 actionDecl 'ch.ivy.addon.portal.generic.PortalTaskMenu.PortalTaskMenuData out;
' #txt
Ps0 f7 actionTable 'out=in;
' #txt
Ps0 f7 actionCode 'import ch.ivyteam.ivy.workflow.TaskState;
import ch.ivy.addon.portalkit.util.SecurityServiceUtils;

String appName = SecurityServiceUtils.getApplicationNameFromSession();
if (#appName is initialized) {
	out.allCategoryCriteria.apps = [appName];
	out.myCategoryCriteria.apps = [appName];
	out.groupCategoryCriteria.apps = [appName];
	out.unassignedCategoryCriteria.apps = [appName];
}

out.allCategoryCriteria.includedStates = [TaskState.SUSPENDED, TaskState.RESUMED, TaskState.PARKED, TaskState.DONE];
out.myCategoryCriteria.includedStates = [TaskState.SUSPENDED, TaskState.RESUMED, TaskState.PARKED, TaskState.DONE];
out.groupCategoryCriteria.includedStates = [TaskState.SUSPENDED, TaskState.RESUMED, TaskState.PARKED, TaskState.DONE];
out.unassignedCategoryCriteria.includedStates = [TaskState.SUSPENDED, TaskState.RESUMED, TaskState.PARKED, TaskState.DONE];

' #txt
Ps0 f7 type ch.ivy.addon.portal.generic.PortalTaskMenu.PortalTaskMenuData #txt
Ps0 f7 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Build criteria</name>
    </language>
</elementInfo>
' #txt
Ps0 f7 168 202 112 44 -34 -8 #rect
Ps0 f7 @|StepIcon #fIcon
Ps0 f17 expr out #txt
Ps0 f17 280 224 320 224 #arcP
Ps0 f28 actionDecl 'ch.ivy.addon.portal.generic.PortalTaskMenu.PortalTaskMenuData out;
' #txt
Ps0 f28 actionTable 'out=in;
' #txt
Ps0 f28 actionCode 'import ch.ivy.addon.portalkit.bo.TaskNode;
import ch.ivy.addon.portalkit.enums.SessionAttribute;
import ch.ivy.addon.portalkit.util.SecurityServiceUtils;

SecurityServiceUtils.setSessionAttribute(SessionAttribute.TASK_CATEGORY.toString(), in.selectedMenuItem as TaskNode);' #txt
Ps0 f28 type ch.ivy.addon.portal.generic.PortalTaskMenu.PortalTaskMenuData #txt
Ps0 f28 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Store the last page
to session</name>
        <nameStyle>30
</nameStyle>
    </language>
</elementInfo>
' #txt
Ps0 f28 472 394 128 44 -45 -16 #rect
Ps0 f28 @|StepIcon #fIcon
Ps0 f30 expr out #txt
Ps0 f30 600 416 632 416 #arcP
Ps0 f33 type ch.ivy.addon.portal.generic.PortalTaskMenu.PortalTaskMenuData #txt
Ps0 f33 processCall 'Functional Processes/InitializeTaskDataModel:call()' #txt
Ps0 f33 doCall true #txt
Ps0 f33 requestActionDecl '<> param;
' #txt
Ps0 f33 responseActionDecl 'ch.ivy.addon.portal.generic.PortalTaskMenu.PortalTaskMenuData out;
' #txt
Ps0 f33 responseMappingAction 'out=in;
out.dataModel=result.dataModel;
' #txt
Ps0 f33 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>InitializeTaskDataModel</name>
    </language>
</elementInfo>
' #txt
Ps0 f33 152 394 144 44 -65 -8 #rect
Ps0 f33 @|CallSubIcon #fIcon
Ps0 f0 guid 16816A4A509B4B43 #txt
Ps0 f0 type ch.ivy.addon.portal.generic.PortalTaskMenu.PortalTaskMenuData #txt
Ps0 f0 method start() #txt
Ps0 f0 disableUIEvents true #txt
Ps0 f0 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<> param = methodEvent.getInputArguments();
' #txt
Ps0 f0 outParameterDecl '<> result;
' #txt
Ps0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start()</name>
    </language>
</elementInfo>
' #txt
Ps0 f0 83 83 26 26 -16 15 #rect
Ps0 f0 @|RichDialogInitStartIcon #fIcon
Ps0 f1 type ch.ivy.addon.portal.generic.PortalTaskMenu.PortalTaskMenuData #txt
Ps0 f1 307 83 26 26 0 12 #rect
Ps0 f1 @|RichDialogProcessEndIcon #fIcon
Ps0 f2 expr out #txt
Ps0 f2 109 96 307 96 #arcP
Ps0 f3 guid 16816A4F156A6A14 #txt
Ps0 f3 type ch.ivy.addon.portal.generic.PortalTaskMenu.PortalTaskMenuData #txt
Ps0 f3 method findTaskCategory() #txt
Ps0 f3 disableUIEvents false #txt
Ps0 f3 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<> param = methodEvent.getInputArguments();
' #txt
Ps0 f3 outParameterDecl '<> result;
' #txt
Ps0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>findTaskCategory()</name>
    </language>
</elementInfo>
' #txt
Ps0 f3 83 211 26 26 -52 15 #rect
Ps0 f3 @|RichDialogMethodStartIcon #fIcon
Ps0 f15 expr out #txt
Ps0 f15 109 224 168 224 #arcP
Ps0 f12 guid 1681828FC3D718DB #txt
Ps0 f12 type ch.ivy.addon.portal.generic.PortalTaskMenu.PortalTaskMenuData #txt
Ps0 f12 actionDecl 'ch.ivy.addon.portal.generic.PortalTaskMenu.PortalTaskMenuData out;
' #txt
Ps0 f12 actionTable 'out=in;
' #txt
Ps0 f12 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>loadTask</name>
    </language>
</elementInfo>
' #txt
Ps0 f12 83 403 26 26 -26 15 #rect
Ps0 f12 @|RichDialogProcessStartIcon #fIcon
Ps0 f16 expr out #txt
Ps0 f16 109 416 152 416 #arcP
Ps0 f14 type ch.ivy.addon.portal.generic.PortalTaskMenu.PortalTaskMenuData #txt
Ps0 f14 processCall 'Functional Processes/BuildTaskQuery:buildTaskQuery()' #txt
Ps0 f14 doCall true #txt
Ps0 f14 requestActionDecl '<> param;
' #txt
Ps0 f14 responseActionDecl 'ch.ivy.addon.portal.generic.PortalTaskMenu.PortalTaskMenuData out;
' #txt
Ps0 f14 responseMappingAction 'out=in;
out.allCategoryCriteria.customTaskQuery=result.#taskQuery;
out.groupCategoryCriteria.customTaskQuery=result.#taskQuery;
out.myCategoryCriteria.customTaskQuery=result.#taskQuery;
out.unassignedCategoryCriteria.customTaskQuery=result.#taskQuery;
' #txt
Ps0 f14 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>BuildTaskQuery</name>
    </language>
</elementInfo>
' #txt
Ps0 f14 480 202 112 44 -43 -8 #rect
Ps0 f14 @|CallSubIcon #fIcon
Ps0 f19 expr out #txt
Ps0 f19 448 224 480 224 #arcP
Ps0 f5 actionDecl 'ch.ivy.addon.portal.generic.PortalTaskMenu.PortalTaskMenuData out1;
ch.ivy.addon.portal.generic.PortalTaskMenu.PortalTaskMenuData out2;
ch.ivy.addon.portal.generic.PortalTaskMenu.PortalTaskMenuData out3;
ch.ivy.addon.portal.generic.PortalTaskMenu.PortalTaskMenuData out4;
' #txt
Ps0 f5 actionTable 'out1=in;
out1.allCategoryCriteria=in.allCategoryCriteria;
out2.myCategoryCriteria=in.myCategoryCriteria;
out3.groupCategoryCriteria=in.groupCategoryCriteria;
out4.unassignedCategoryCriteria=in.unassignedCategoryCriteria;
' #txt
Ps0 f5 type ch.ivy.addon.portal.generic.PortalTaskMenu.PortalTaskMenuData #txt
Ps0 f5 624 208 32 32 0 16 #rect
Ps0 f5 @|ThreadIcon #fIcon
Ps0 f6 expr out #txt
Ps0 f6 592 224 624 224 #arcP
Ps0 f8 actionDecl 'ch.ivy.addon.portal.generic.PortalTaskMenu.PortalTaskMenuData out;
' #txt
Ps0 f8 actionTable 'out=in1;
out.allTaskCategoryTree=in1.allTaskCategoryTree;
out.groupTaskCategoryTree=in3.groupTaskCategoryTree;
out.myTaskCategoryTree=in2.myTaskCategoryTree;
out.unassignedTaskCategoryTree=in4.#unassignedTaskCategoryTree;
' #txt
Ps0 f8 1040 208 32 32 0 16 #rect
Ps0 f8 @|JoinIcon #fIcon
Ps0 f20 type ch.ivy.addon.portal.generic.PortalTaskMenu.PortalTaskMenuData #txt
Ps0 f20 processCall 'Ivy Data Processes/TaskService:findCategoriesByCriteria(ch.ivy.addon.portalkit.ivydata.searchcriteria.TaskCategorySearchCriteria)' #txt
Ps0 f20 doCall true #txt
Ps0 f20 requestActionDecl '<ch.ivy.addon.portalkit.ivydata.searchcriteria.TaskCategorySearchCriteria taskCategorySearchCriteria> param;
' #txt
Ps0 f20 requestMappingAction 'param.taskCategorySearchCriteria=in.allCategoryCriteria;
' #txt
Ps0 f20 responseActionDecl 'ch.ivy.addon.portal.generic.PortalTaskMenu.PortalTaskMenuData out;
' #txt
Ps0 f20 responseMappingAction 'out=in;
out.allTaskCategoryTree=result.categoryTree;
' #txt
Ps0 f20 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>TaskService</name>
    </language>
</elementInfo>
' #txt
Ps0 f20 776 106 112 44 -33 -8 #rect
Ps0 f20 @|CallSubIcon #fIcon
Ps0 f26 type ch.ivy.addon.portal.generic.PortalTaskMenu.PortalTaskMenuData #txt
Ps0 f26 processCall 'Ivy Data Processes/TaskService:findCategoriesByCriteria(ch.ivy.addon.portalkit.ivydata.searchcriteria.TaskCategorySearchCriteria)' #txt
Ps0 f26 doCall true #txt
Ps0 f26 requestActionDecl '<ch.ivy.addon.portalkit.ivydata.searchcriteria.TaskCategorySearchCriteria taskCategorySearchCriteria> param;
' #txt
Ps0 f26 requestMappingAction 'param.taskCategorySearchCriteria=in.myCategoryCriteria;
' #txt
Ps0 f26 responseActionDecl 'ch.ivy.addon.portal.generic.PortalTaskMenu.PortalTaskMenuData out;
' #txt
Ps0 f26 responseMappingAction 'out=in;
out.myTaskCategoryTree=result.categoryTree;
' #txt
Ps0 f26 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>TaskService</name>
    </language>
</elementInfo>
' #txt
Ps0 f26 856 170 112 44 -33 -8 #rect
Ps0 f26 @|CallSubIcon #fIcon
Ps0 f31 expr out #txt
Ps0 f31 type ch.ivy.addon.portal.generic.PortalTaskMenu.PortalTaskMenuData #txt
Ps0 f31 var in2 #txt
Ps0 f31 968 192 1056 208 #arcP
Ps0 f31 1 1056 192 #addKink
Ps0 f31 0 0.7797197062858272 0 0 #arcLabel
Ps0 f32 actionDecl 'ch.ivy.addon.portal.generic.PortalTaskMenu.PortalTaskMenuData out;
' #txt
Ps0 f32 actionTable 'out=in;
' #txt
Ps0 f32 actionCode 'import ch.ivyteam.ivy.workflow.TaskState;
import ch.ivyteam.ivy.workflow.query.TaskQuery;

in.myCategoryCriteria.involvedUsername = ivy.session.getSessionUserName();
TaskQuery taskQuery = in.myCategoryCriteria.getFinalQuery();
TaskQuery reservedTaskQuery = TaskQuery.create().where().activatorRoleId().isNotNull().and().state().isEqual(TaskState.PARKED);
taskQuery.where().and().activatorUserId().isNotNull().or(reservedTaskQuery);

in.myCategoryCriteria.setFinalQuery(taskQuery);' #txt
Ps0 f32 type ch.ivy.addon.portal.generic.PortalTaskMenu.PortalTaskMenuData #txt
Ps0 f32 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Extend query</name>
    </language>
</elementInfo>
' #txt
Ps0 f32 704 170 112 44 -35 -8 #rect
Ps0 f32 @|StepIcon #fIcon
Ps0 f27 expr out #txt
Ps0 f27 816 192 856 192 #arcP
Ps0 f27 0 0.2202802937141729 0 0 #arcLabel
Ps0 f35 expr out #txt
Ps0 f35 1072 224 1107 224 #arcP
Ps0 f36 type ch.ivy.addon.portal.generic.PortalTaskMenu.PortalTaskMenuData #txt
Ps0 f36 processCall 'Ivy Data Processes/TaskService:findCategoriesByCriteria(ch.ivy.addon.portalkit.ivydata.searchcriteria.TaskCategorySearchCriteria)' #txt
Ps0 f36 doCall true #txt
Ps0 f36 requestActionDecl '<ch.ivy.addon.portalkit.ivydata.searchcriteria.TaskCategorySearchCriteria taskCategorySearchCriteria> param;
' #txt
Ps0 f36 requestMappingAction 'param.taskCategorySearchCriteria=in.groupCategoryCriteria;
' #txt
Ps0 f36 responseActionDecl 'ch.ivy.addon.portal.generic.PortalTaskMenu.PortalTaskMenuData out;
' #txt
Ps0 f36 responseMappingAction 'out=in;
out.groupTaskCategoryTree=result.categoryTree;
' #txt
Ps0 f36 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>TaskService</name>
    </language>
</elementInfo>
' #txt
Ps0 f36 856 234 112 44 -33 -8 #rect
Ps0 f36 @|CallSubIcon #fIcon
Ps0 f37 actionDecl 'ch.ivy.addon.portal.generic.PortalTaskMenu.PortalTaskMenuData out;
' #txt
Ps0 f37 actionTable 'out=in;
' #txt
Ps0 f37 actionCode 'import ch.ivyteam.ivy.workflow.TaskState;
import ch.ivyteam.ivy.workflow.query.TaskQuery;

in.groupCategoryCriteria.involvedUsername = ivy.session.getSessionUserName();
TaskQuery taskQuery = in.groupCategoryCriteria.getFinalQuery();
taskQuery.where().and().activatorRoleId().isNotNull();

in.groupCategoryCriteria.setFinalQuery(taskQuery);' #txt
Ps0 f37 type ch.ivy.addon.portal.generic.PortalTaskMenu.PortalTaskMenuData #txt
Ps0 f37 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Extend query</name>
    </language>
</elementInfo>
' #txt
Ps0 f37 704 234 112 44 -35 -8 #rect
Ps0 f37 @|StepIcon #fIcon
Ps0 f39 expr out #txt
Ps0 f39 816 256 856 256 #arcP
Ps0 f39 0 0.2202802937141729 0 0 #arcLabel
Ps0 f40 expr out3 #txt
Ps0 f40 640 240 704 256 #arcP
Ps0 f40 1 640 256 #addKink
Ps0 f40 1 0.14115012303089255 0 0 #arcLabel
Ps0 f41 expr out #txt
Ps0 f41 type ch.ivy.addon.portal.generic.PortalTaskMenu.PortalTaskMenuData #txt
Ps0 f41 var in3 #txt
Ps0 f41 968 256 1056 240 #arcP
Ps0 f41 1 1056 256 #addKink
Ps0 f41 0 0.8859746521921745 0 0 #arcLabel
Ps0 f42 actionDecl 'ch.ivy.addon.portal.generic.PortalTaskMenu.PortalTaskMenuData out;
' #txt
Ps0 f42 actionTable 'out=in;
' #txt
Ps0 f42 actionCode 'import ch.ivyteam.ivy.workflow.TaskState;

in.unassignedCategoryCriteria.setIncludedStates([TaskState.UNASSIGNED]);' #txt
Ps0 f42 type ch.ivy.addon.portal.generic.PortalTaskMenu.PortalTaskMenuData #txt
Ps0 f42 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Extend query</name>
    </language>
</elementInfo>
' #txt
Ps0 f42 712 298 112 44 -35 -8 #rect
Ps0 f42 @|StepIcon #fIcon
Ps0 f43 type ch.ivy.addon.portal.generic.PortalTaskMenu.PortalTaskMenuData #txt
Ps0 f43 processCall 'Ivy Data Processes/TaskService:findCategoriesByCriteria(ch.ivy.addon.portalkit.ivydata.searchcriteria.TaskCategorySearchCriteria)' #txt
Ps0 f43 doCall true #txt
Ps0 f43 requestActionDecl '<ch.ivy.addon.portalkit.ivydata.searchcriteria.TaskCategorySearchCriteria taskCategorySearchCriteria> param;
' #txt
Ps0 f43 requestMappingAction 'param.taskCategorySearchCriteria=in.unassignedCategoryCriteria;
' #txt
Ps0 f43 responseActionDecl 'ch.ivy.addon.portal.generic.PortalTaskMenu.PortalTaskMenuData out;
' #txt
Ps0 f43 responseMappingAction 'out=in;
out.unassignedTaskCategoryTree=result.categoryTree;
' #txt
Ps0 f43 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>TaskService</name>
    </language>
</elementInfo>
' #txt
Ps0 f43 864 298 112 44 -33 -8 #rect
Ps0 f43 @|CallSubIcon #fIcon
Ps0 f44 expr out #txt
Ps0 f44 824 320 864 320 #arcP
Ps0 f44 0 0.2202802937141729 0 0 #arcLabel
Ps0 f45 type ch.ivy.addon.portal.generic.PortalTaskMenu.PortalTaskMenuData #txt
Ps0 f45 656 304 32 32 0 16 #rect
Ps0 f45 @|AlternativeIcon #fIcon
Ps0 f46 expr in #txt
Ps0 f46 outCond in.hasReadAllTasksPermisson #txt
Ps0 f46 688 320 712 320 #arcP
Ps0 f47 type ch.ivy.addon.portal.generic.PortalTaskMenu.PortalTaskMenuData #txt
Ps0 f47 1008 304 32 32 0 16 #rect
Ps0 f47 @|AlternativeIcon #fIcon
Ps0 f48 expr out #txt
Ps0 f48 976 320 1008 320 #arcP
Ps0 f49 expr in #txt
Ps0 f49 type ch.ivy.addon.portal.generic.PortalTaskMenu.PortalTaskMenuData #txt
Ps0 f49 var in4 #txt
Ps0 f49 1040 320 1056 240 #arcP
Ps0 f49 1 1056 320 #addKink
Ps0 f49 1 0.31887730715271106 0 0 #arcLabel
Ps0 f50 expr out4 #txt
Ps0 f50 640 240 656 320 #arcP
Ps0 f50 1 640 320 #addKink
Ps0 f50 0 0.6811226928472891 0 0 #arcLabel
Ps0 f51 expr in #txt
Ps0 f51 672 336 1024 336 #arcP
Ps0 f51 1 672 368 #addKink
Ps0 f51 2 1024 368 #addKink
Ps0 f51 1 0.5 0 0 #arcLabel
Ps0 f13 actionDecl 'ch.ivy.addon.portal.generic.PortalTaskMenu.PortalTaskMenuData out;
' #txt
Ps0 f13 actionTable 'out=in;
' #txt
Ps0 f13 actionCode 'import ch.ivyteam.ivy.workflow.TaskState;
import java.util.Arrays;
import ch.ivy.addon.portalkit.enums.TaskAssigneeType;
import ch.ivy.addon.portal.generic.common.TreeNodeType;
import ch.ivy.addon.portalkit.util.MenuUtils;
import ch.ivy.addon.portalkit.bean.MenuStateBean;
import ch.ivy.addon.portalkit.jsf.ManagedBeans;
import ch.ivy.addon.portalkit.util.SecurityServiceUtils;
import ch.ivy.addon.portal.generic.view.TaskView;
import ch.ivy.addon.portalkit.datamodel.TaskLazyDataModel;
import ch.ivy.addon.portalkit.util.TaskUtils;
import ch.ivy.addon.portalkit.bo.TaskNode;
import ch.ivy.addon.portalkit.bo.MainMenuNode;
import ch.ivy.addon.portalkit.util.PermissionUtils;

in.selectedMenuItem = in.selectedNode.getData() as MainMenuNode;
TaskNode categoryMenu = in.selectedMenuItem as TaskNode;

in.dataModel.setCategory(categoryMenu.categoryRawPath);
in.hasReadAllTasksPermisson = PermissionUtils.checkReadAllTasksPermission();
if(in.selectedNode.type.startsWith(TreeNodeType.TASKS_ALL_TASKS)){
	in.dataModel.setTaskAssigneeType(TaskAssigneeType.ALL);
	in.dataModel.setAdminQuery(in.hasReadAllTasksPermisson);
	in.dataModel.setQueryForUnassignedTask(false);
} else if(in.selectedNode.type.startsWith(TreeNodeType.TASKS_MY_TASKS)) {
	in.dataModel.setTaskAssigneeType(TaskAssigneeType.USER);
	in.dataModel.setAdminQuery(false);
	if (in.hasReadAllTasksPermisson) {
		in.dataModel.addIncludedStates(Arrays.asList(TaskState.DONE));
	}
	in.dataModel.setQueryForUnassignedTask(false);
} else if(in.selectedNode.type.startsWith(TreeNodeType.TASKS_GROUP_TASKS)) {
	in.dataModel.setTaskAssigneeType(TaskAssigneeType.ROLE);
	in.dataModel.setAdminQuery(false);
	if (in.hasReadAllTasksPermisson) {
		in.dataModel.addIncludedStates(Arrays.asList(TaskState.DONE));
	}
	in.dataModel.setQueryForUnassignedTask(false);
} else if(in.selectedNode.type.startsWith(TreeNodeType.TASKS_UNASSIGNED_TASKS)) {
	in.dataModel.setTaskAssigneeType(TaskAssigneeType.ALL);
	in.dataModel.setAdminQuery(true);
	in.dataModel.setIncludedStates(Arrays.asList(TaskState.DONE));
	in.dataModel.setQueryForUnassignedTask(true);
}

in.taskView = TaskView.create().category(categoryMenu).dataModel(in.dataModel).pageTitle(categoryMenu.value).showHeaderToolbar(false).createNewTaskView();' #txt
Ps0 f13 type ch.ivy.addon.portal.generic.PortalTaskMenu.PortalTaskMenuData #txt
Ps0 f13 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Build data model,&#xD;
task view</name>
    </language>
</elementInfo>
' #txt
Ps0 f13 320 394 128 44 -44 -16 #rect
Ps0 f13 @|StepIcon #fIcon
Ps0 f18 expr out #txt
Ps0 f18 296 416 320 416 #arcP
Ps0 f29 expr out #txt
Ps0 f29 448 416 472 416 #arcP
Ps0 f52 expr out1 #txt
Ps0 f52 640 208 776 128 #arcP
Ps0 f52 1 640 128 #addKink
Ps0 f52 0 0.7562475879084162 0 0 #arcLabel
Ps0 f10 expr out #txt
Ps0 f10 type ch.ivy.addon.portal.generic.PortalTaskMenu.PortalTaskMenuData #txt
Ps0 f10 var in1 #txt
Ps0 f10 888 128 1056 208 #arcP
Ps0 f10 1 1056 128 #addKink
Ps0 f10 0 0.7380952380952381 0 0 #arcLabel
Ps0 f34 expr out2 #txt
Ps0 f34 640 208 704 192 #arcP
Ps0 f34 1 640 192 #addKink
Ps0 f34 1 0.2202802937141729 0 0 #arcLabel
>Proto Ps0 .type ch.ivy.addon.portal.generic.PortalTaskMenu.PortalTaskMenuData #txt
>Proto Ps0 .processKind HTML_DIALOG #txt
>Proto Ps0 -8 -8 16 16 16 26 #rect
>Proto Ps0 '' #fIcon
Ps0 f7 mainOut f17 tail #connect
Ps0 f17 head f9 mainIn #connect
Ps0 f28 mainOut f30 tail #connect
Ps0 f30 head f38 mainIn #connect
Ps0 f0 mainOut f2 tail #connect
Ps0 f2 head f1 mainIn #connect
Ps0 f3 mainOut f15 tail #connect
Ps0 f15 head f7 mainIn #connect
Ps0 f12 mainOut f16 tail #connect
Ps0 f16 head f33 mainIn #connect
Ps0 f9 mainOut f19 tail #connect
Ps0 f19 head f14 mainIn #connect
Ps0 f14 mainOut f6 tail #connect
Ps0 f6 head f5 in #connect
Ps0 f26 mainOut f31 tail #connect
Ps0 f31 head f8 in #connect
Ps0 f34 head f32 mainIn #connect
Ps0 f32 mainOut f27 tail #connect
Ps0 f27 head f26 mainIn #connect
Ps0 f8 mainOut f35 tail #connect
Ps0 f35 head f4 mainIn #connect
Ps0 f37 mainOut f39 tail #connect
Ps0 f39 head f36 mainIn #connect
Ps0 f40 head f37 mainIn #connect
Ps0 f36 mainOut f41 tail #connect
Ps0 f41 head f8 in #connect
Ps0 f42 mainOut f44 tail #connect
Ps0 f44 head f43 mainIn #connect
Ps0 f45 out f46 tail #connect
Ps0 f46 head f42 mainIn #connect
Ps0 f43 mainOut f48 tail #connect
Ps0 f48 head f47 in #connect
Ps0 f47 out f49 tail #connect
Ps0 f49 head f8 in #connect
Ps0 f50 head f45 in #connect
Ps0 f45 out f51 tail #connect
Ps0 f51 head f47 in #connect
Ps0 f33 mainOut f18 tail #connect
Ps0 f18 head f13 mainIn #connect
Ps0 f13 mainOut f29 tail #connect
Ps0 f29 head f28 mainIn #connect
Ps0 f5 out f52 tail #connect
Ps0 f52 head f20 mainIn #connect
Ps0 f5 out f34 tail #connect
Ps0 f5 out f40 tail #connect
Ps0 f5 out f50 tail #connect
Ps0 f20 mainOut f10 tail #connect
Ps0 f10 head f8 in #connect
