[Ivy]
156F869FC3FCD1D9 3.20 #module
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
Ps0 @RichDialogInitStart f0 '' #zField
Ps0 @RichDialogProcessEnd f1 '' #zField
Ps0 @PushWFArc f2 '' #zField
Ps0 @RichDialogMethodStart f3 '' #zField
Ps0 @RichDialogProcessEnd f4 '' #zField
Ps0 @GridStep f9 '' #zField
Ps0 @GridStep f13 '' #zField
Ps0 @CallSub f38 '' #zField
Ps0 @RichDialogProcessStart f12 '' #zField
Ps0 @GridStep f7 '' #zField
Ps0 @PushWFArc f15 '' #zField
Ps0 @PushWFArc f17 '' #zField
Ps0 @CallSub f25 '' #zField
Ps0 @PushWFArc f26 '' #zField
Ps0 @GridStep f28 '' #zField
Ps0 @PushWFArc f29 '' #zField
Ps0 @PushWFArc f30 '' #zField
Ps0 @CallSub f33 '' #zField
Ps0 @PushWFArc f34 '' #zField
Ps0 @PushWFArc f18 '' #zField
Ps0 @CallSub f35 '' #zField
Ps0 @PushWFArc f39 '' #zField
Ps0 @GridStep f14 '' #zField
Ps0 @PushWFArc f36 '' #zField
Ps0 @PushWFArc f6 '' #zField
>Proto Ps0 Ps0 PortalTaskMenuProcess #zField
Ps0 f0 guid 156F869FC6B3D9ED #txt
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
Ps0 f0 45 85 22 22 14 0 #rect
Ps0 f0 @|RichDialogInitStartIcon #fIcon
Ps0 f1 type ch.ivy.addon.portal.generic.PortalTaskMenu.PortalTaskMenuData #txt
Ps0 f1 261 85 22 22 14 0 #rect
Ps0 f1 @|RichDialogProcessEndIcon #fIcon
Ps0 f2 expr out #txt
Ps0 f2 67 96 261 96 #arcP
Ps0 f3 guid 156F86D9BA2F2903 #txt
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
Ps0 f3 45 189 22 22 -52 13 #rect
Ps0 f3 @|RichDialogMethodStartIcon #fIcon
Ps0 f4 type ch.ivy.addon.portal.generic.PortalTaskMenu.PortalTaskMenuData #txt
Ps0 f4 939 187 26 26 0 12 #rect
Ps0 f4 @|RichDialogProcessEndIcon #fIcon
Ps0 f9 actionDecl 'ch.ivy.addon.portal.generic.PortalTaskMenu.PortalTaskMenuData out;
' #txt
Ps0 f9 actionTable 'out=in;
' #txt
Ps0 f9 actionCode 'import ch.ivy.addon.portalkit.util.TaskUtils;
in.loginUser = ivy.session.getSessionUserName();
in.hasReadAllTasksPermisson = TaskUtils.checkReadAllTasksPermission();' #txt
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
Ps0 f9 304 176 128 48 -45 -8 #rect
Ps0 f9 @|StepIcon #fIcon
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
import ch.ivy.addon.portalkit.util.TaskUtils;

in.selectedMenuItem = in.selectedNode.getData() as MainMenuNode;
TaskNode categoryMenu = in.selectedMenuItem as TaskNode;

in.dataModel.setCategory(categoryMenu.categoryRawPath);

if(in.selectedNode.type.startsWith(TreeNodeType.TASKS_ALL_TASKS)){
	in.dataModel.setTaskAssigneeType(TaskAssigneeType.ALL);
	in.hasReadAllTasksPermisson = TaskUtils.checkReadAllTasksPermission();
	in.dataModel.setIgnoreInvolvedUser(in.hasReadAllTasksPermisson);
} else if(in.selectedNode.type.startsWith(TreeNodeType.TASKS_MY_TASKS)) {
	in.dataModel.setTaskAssigneeType(TaskAssigneeType.USER);
	in.dataModel.setIgnoreInvolvedUser(false);
} else if(in.selectedNode.type.startsWith(TreeNodeType.TASKS_GROUP_TASKS)) {
	in.dataModel.setTaskAssigneeType(TaskAssigneeType.ROLE);
	in.dataModel.setIgnoreInvolvedUser(false);
} else if(in.selectedNode.type.startsWith(TreeNodeType.TASKS_UNASSIGNED_TASKS)) {
	in.dataModel.setTaskAssigneeType(TaskAssigneeType.ALL);
	in.dataModel.setIgnoreInvolvedUser(true);
	in.dataModel.setIncludedStates(Arrays.asList(TaskState.UNASSIGNED));
}

in.taskView = TaskView.create().category(categoryMenu).dataModel(in.dataModel).pageTitle(categoryMenu.value).showHeaderToolbar(false).createNewTaskView();' #txt
Ps0 f13 type ch.ivy.addon.portal.generic.PortalTaskMenu.PortalTaskMenuData #txt
Ps0 f13 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>build data model,
 task view</name>
        <nameStyle>28,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ps0 f13 294 340 36 24 -47 14 #rect
Ps0 f13 @|StepIcon #fIcon
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
Ps0 f38 544 328 128 48 -48 -12 #rect
Ps0 f38 @|CallSubIcon #fIcon
Ps0 f12 guid 156FDAF210A2DE69 #txt
Ps0 f12 type ch.ivy.addon.portal.generic.PortalTaskMenu.PortalTaskMenuData #txt
Ps0 f12 actionDecl 'ch.ivy.addon.portal.generic.PortalTaskMenu.PortalTaskMenuData out;
' #txt
Ps0 f12 actionTable 'out=in;
' #txt
Ps0 f12 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>loadTask</name>
        <nameStyle>8,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ps0 f12 45 341 22 22 -27 15 #rect
Ps0 f12 @|RichDialogProcessStartIcon #fIcon
Ps0 f7 actionDecl 'ch.ivy.addon.portal.generic.PortalTaskMenu.PortalTaskMenuData out;
' #txt
Ps0 f7 actionTable 'out=in;
' #txt
Ps0 f7 actionCode 'import ch.ivy.addon.portalkit.util.SecurityServiceUtils;
String appName = SecurityServiceUtils.getApplicationNameFromSession();
if (#appName is initialized) {
	in.involvedApplications = [appName];
}' #txt
Ps0 f7 type ch.ivy.addon.portal.generic.PortalTaskMenu.PortalTaskMenuData #txt
Ps0 f7 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>set involved applications</name>
        <nameStyle>25,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ps0 f7 120 176 160 48 -66 -9 #rect
Ps0 f7 @|StepIcon #fIcon
Ps0 f15 expr out #txt
Ps0 f15 67 200 120 200 #arcP
Ps0 f17 expr out #txt
Ps0 f17 280 200 304 200 #arcP
Ps0 f25 type ch.ivy.addon.portal.generic.PortalTaskMenu.PortalTaskMenuData #txt
Ps0 f25 processCall 'Functional Processes/BuildTaskJsonQuery:buildTaskJsonQuery()' #txt
Ps0 f25 doCall true #txt
Ps0 f25 requestActionDecl '<> param;
' #txt
Ps0 f25 responseActionDecl 'ch.ivy.addon.portal.generic.PortalTaskMenu.PortalTaskMenuData out;
' #txt
Ps0 f25 responseMappingAction 'out=in;
out.jsonQuery=result.jsonQuery;
' #txt
Ps0 f25 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>BuildTaskJsonQuery</name>
    </language>
</elementInfo>
' #txt
Ps0 f25 492 188 36 24 -51 18 #rect
Ps0 f25 @|CallSubIcon #fIcon
Ps0 f26 expr out #txt
Ps0 f26 432 200 492 200 #arcP
Ps0 f28 actionDecl 'ch.ivy.addon.portal.generic.PortalTaskMenu.PortalTaskMenuData out;
' #txt
Ps0 f28 actionTable 'out=in;
' #txt
Ps0 f28 actionCode 'import ch.ivy.addon.portalkit.bo.TaskNode;
import ch.ivy.addon.portal.generic.navigation.PortalPage;
import ch.ivy.addon.portalkit.enums.SessionAttribute;
import ch.ivy.addon.portalkit.util.SecurityServiceUtils;

SecurityServiceUtils.setSessionAttribute(SessionAttribute.LAST_PAGE.toString(), PortalPage.LINK_TO_TASK);
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
Ps0 f28 384 330 128 44 -45 -16 #rect
Ps0 f28 @|StepIcon #fIcon
Ps0 f29 expr out #txt
Ps0 f29 330 352 384 352 #arcP
Ps0 f29 0 0.24467500942153303 0 0 #arcLabel
Ps0 f30 expr out #txt
Ps0 f30 512 352 544 352 #arcP
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
Ps0 f33 112 330 144 44 -65 -8 #rect
Ps0 f33 @|CallSubIcon #fIcon
Ps0 f34 expr out #txt
Ps0 f34 67 352 112 352 #arcP
Ps0 f18 expr out #txt
Ps0 f18 256 352 294 352 #arcP
Ps0 f35 type ch.ivy.addon.portal.generic.PortalTaskMenu.PortalTaskMenuData #txt
Ps0 f35 processCall MultiPortal/TaskService:findAllCategories(Boolean,String,String,List<String>,Long) #txt
Ps0 f35 doCall true #txt
Ps0 f35 requestActionDecl '<java.lang.Boolean isHavingReadAllTaskPermission,java.lang.String jsonQuery,java.lang.String userName,List<java.lang.String> apps,java.lang.Long serverId> param;
' #txt
Ps0 f35 requestMappingAction 'param.isHavingReadAllTaskPermission=in.hasReadAllTasksPermisson;
param.jsonQuery=in.jsonQuery;
param.userName=in.loginUser;
param.apps=in.involvedApplications;
param.serverId=ch.ivy.addon.portalkit.util.SecurityServiceUtils.getServerIdFromSession();
' #txt
Ps0 f35 responseActionDecl 'ch.ivy.addon.portal.generic.PortalTaskMenu.PortalTaskMenuData out;
' #txt
Ps0 f35 responseMappingAction 'out=in;
out.errors=result.errors;
out.returnCategories=result.categories;
' #txt
Ps0 f35 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>TaskService</name>
        <nameStyle>11,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ps0 f35 608 178 112 44 -33 -8 #rect
Ps0 f35 @|CallSubIcon #fIcon
Ps0 f39 expr out #txt
Ps0 f39 528 200 608 200 #arcP
Ps0 f14 actionDecl 'ch.ivy.addon.portal.generic.PortalTaskMenu.PortalTaskMenuData out;
' #txt
Ps0 f14 actionTable 'out=in;
' #txt
Ps0 f14 actionCode 'import ch.ivy.ws.addon.CategoryData;
import ch.ivy.ws.addon.CategoryDataType;
import java.util.Map;
import ch.ivy.addon.portalkit.util.TaskUtils;

Map categoriesGroupByType = TaskUtils.groupTaskCategoryByType(in.returnCategories);
in.allTaskCategories = categoriesGroupByType.get(CategoryDataType.ALL_USERS_CATEGORY) as java.util.List;
in.groupTaskCategories = categoriesGroupByType.get(CategoryDataType.GROUP_TASK_CATEGORY) as java.util.List;
in.myTaskCategories = categoriesGroupByType.get(CategoryDataType.MY_TASK_CATEGORY) as java.util.List;
in.unassignedTaskCategories = categoriesGroupByType.get(CategoryDataType.UNASSIGNED_CATEGORY) as java.util.List;

' #txt
Ps0 f14 type ch.ivy.addon.portal.generic.PortalTaskMenu.PortalTaskMenuData #txt
Ps0 f14 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>get categories output</name>
        <nameStyle>21,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ps0 f14 760 178 128 44 -57 -8 #rect
Ps0 f14 @|StepIcon #fIcon
Ps0 f36 expr out #txt
Ps0 f36 720 200 760 200 #arcP
Ps0 f6 expr out #txt
Ps0 f6 888 200 939 200 #arcP
>Proto Ps0 .type ch.ivy.addon.portal.generic.PortalTaskMenu.PortalTaskMenuData #txt
>Proto Ps0 .processKind HTML_DIALOG #txt
>Proto Ps0 -8 -8 16 16 16 26 #rect
>Proto Ps0 '' #fIcon
Ps0 f0 mainOut f2 tail #connect
Ps0 f2 head f1 mainIn #connect
Ps0 f3 mainOut f15 tail #connect
Ps0 f15 head f7 mainIn #connect
Ps0 f7 mainOut f17 tail #connect
Ps0 f17 head f9 mainIn #connect
Ps0 f9 mainOut f26 tail #connect
Ps0 f26 head f25 mainIn #connect
Ps0 f13 mainOut f29 tail #connect
Ps0 f29 head f28 mainIn #connect
Ps0 f28 mainOut f30 tail #connect
Ps0 f30 head f38 mainIn #connect
Ps0 f12 mainOut f34 tail #connect
Ps0 f34 head f33 mainIn #connect
Ps0 f33 mainOut f18 tail #connect
Ps0 f18 head f13 mainIn #connect
Ps0 f25 mainOut f39 tail #connect
Ps0 f39 head f35 mainIn #connect
Ps0 f35 mainOut f36 tail #connect
Ps0 f36 head f14 mainIn #connect
Ps0 f14 mainOut f6 tail #connect
Ps0 f6 head f4 mainIn #connect
