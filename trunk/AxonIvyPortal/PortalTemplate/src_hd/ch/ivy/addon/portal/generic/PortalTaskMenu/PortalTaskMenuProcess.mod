[Ivy]
[>Created: Wed Oct 05 17:28:29 ICT 2016]
156F869FC3FCD1D9 3.18 #module
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
Ps0 @CallSub f16 '' #zField
Ps0 @GridStep f9 '' #zField
Ps0 @CallSub f21 '' #zField
Ps0 @GridStep f13 '' #zField
Ps0 @CallSub f38 '' #zField
Ps0 @PushWFArc f39 '' #zField
Ps0 @RichDialogProcessStart f12 '' #zField
Ps0 @PushWFArc f18 '' #zField
Ps0 @GridStep f7 '' #zField
Ps0 @PushWFArc f15 '' #zField
Ps0 @PushWFArc f17 '' #zField
Ps0 @CallSub f19 '' #zField
Ps0 @PushWFArc f27 '' #zField
Ps0 @PushWFArc f6 '' #zField
Ps0 @CallSub f8 '' #zField
Ps0 @Alternative f10 '' #zField
Ps0 @PushWFArc f14 '' #zField
Ps0 @PushWFArc f5 '' #zField
Ps0 @PushWFArc f20 '' #zField
Ps0 @Alternative f22 '' #zField
Ps0 @PushWFArc f23 '' #zField
Ps0 @PushWFArc f11 '' #zField
Ps0 @PushWFArc f24 '' #zField
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
Ps0 f4 883 187 26 26 0 12 #rect
Ps0 f4 @|RichDialogProcessEndIcon #fIcon
Ps0 f16 type ch.ivy.addon.portal.generic.PortalTaskMenu.PortalTaskMenuData #txt
Ps0 f16 processCall MultiPortal/TaskService:findPersonalCategories(String,List<String>,Long) #txt
Ps0 f16 doCall true #txt
Ps0 f16 requestActionDecl '<java.lang.String userName,List<java.lang.String> apps,java.lang.Long serverId> param;
' #txt
Ps0 f16 requestMappingAction 'param.userName=in.loginUser;
param.apps=in.involvedApplications;
param.serverId=ch.ivy.addon.portalkit.util.SecurityServiceUtils.getServerIdFromSession();
' #txt
Ps0 f16 responseActionDecl 'ch.ivy.addon.portal.generic.PortalTaskMenu.PortalTaskMenuData out;
' #txt
Ps0 f16 responseMappingAction 'out=in;
out.errors=result.errors;
out.myTaskCategories=result.categories;
' #txt
Ps0 f16 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>FindCategories of
my tasks</name>
        <nameStyle>26,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ps0 f16 694 188 36 24 -49 14 #rect
Ps0 f16 @|CallSubIcon #fIcon
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
Ps0 f21 type ch.ivy.addon.portal.generic.PortalTaskMenu.PortalTaskMenuData #txt
Ps0 f21 processCall MultiPortal/TaskService:findCategories(String,List<String>,Long) #txt
Ps0 f21 doCall true #txt
Ps0 f21 requestActionDecl '<java.lang.String userName,List<java.lang.String> apps,java.lang.Long serverId> param;
' #txt
Ps0 f21 requestMappingAction 'param.apps=in.involvedApplications;
param.serverId=ch.ivy.addon.portalkit.util.SecurityServiceUtils.getServerIdFromSession();
' #txt
Ps0 f21 responseActionDecl 'ch.ivy.addon.portal.generic.PortalTaskMenu.PortalTaskMenuData out;
' #txt
Ps0 f21 responseMappingAction 'out=in;
out.allTaskCategories=result.categories;
out.errors=result.errors;
' #txt
Ps0 f21 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>FindCategories of
all tasks of all users</name>
        <nameStyle>40,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ps0 f21 550 140 36 24 -55 14 #rect
Ps0 f21 @|CallSubIcon #fIcon
Ps0 f13 actionDecl 'ch.ivy.addon.portal.generic.PortalTaskMenu.PortalTaskMenuData out;
' #txt
Ps0 f13 actionTable 'out=in;
' #txt
Ps0 f13 actionCode 'import ch.ivy.ws.addon.TaskAssigneeType;
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
TaskLazyDataModel dataModel = new TaskLazyDataModel();

dataModel.setCategory(categoryMenu.category);

if(in.selectedNode.type.startsWith(TreeNodeType.TASKS_ALL_TASKS)){
	dataModel.setTaskAssigneeType(TaskAssigneeType.ALL);
  in.hasReadAllTasksPermisson = TaskUtils.checkReadAllTasksPermission();
	dataModel.setIgnoreInvolvedUser(in.hasReadAllTasksPermisson);
} else if(in.selectedNode.type.startsWith(TreeNodeType.TASKS_MY_TASKS)) {
	dataModel.setTaskAssigneeType(TaskAssigneeType.USER);
	dataModel.setIgnoreInvolvedUser(false);
} else {
  dataModel.setTaskAssigneeType(TaskAssigneeType.ROLE);
  dataModel.setIgnoreInvolvedUser(false);
}

Long serverId = SecurityServiceUtils.getServerIdFromSession();
if (#serverId is initialized) {
	dataModel.setServerId(serverId);
}

String applicationName = SecurityServiceUtils.getApplicationNameFromSession();
if (#applicationName is initialized) {
	dataModel.setInvolvedApplications(applicationName);
}

in.taskView = TaskView.create().category(categoryMenu).dataModel(dataModel).pageTitle(categoryMenu.value).createNewTaskView();' #txt
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
Ps0 f13 190 292 36 24 -47 14 #rect
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
Ps0 f38 312 280 128 48 -48 -12 #rect
Ps0 f38 @|CallSubIcon #fIcon
Ps0 f39 expr out #txt
Ps0 f39 226 304 312 304 #arcP
Ps0 f39 0 0.24467500942153303 0 0 #arcLabel
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
Ps0 f12 45 293 22 22 14 0 #rect
Ps0 f12 @|RichDialogProcessStartIcon #fIcon
Ps0 f18 expr out #txt
Ps0 f18 67 304 190 304 #arcP
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
Ps0 f19 type ch.ivy.addon.portal.generic.PortalTaskMenu.PortalTaskMenuData #txt
Ps0 f19 processCall MultiPortal/TaskService:findGroupCategories(String,List<String>,Long) #txt
Ps0 f19 doCall true #txt
Ps0 f19 requestActionDecl '<java.lang.String userName,List<java.lang.String> apps,java.lang.Long serverId> param;
' #txt
Ps0 f19 requestMappingAction 'param.userName=in.loginUser;
param.apps=in.involvedApplications;
param.serverId=ch.ivy.addon.portalkit.util.SecurityServiceUtils.getServerIdFromSession();
' #txt
Ps0 f19 responseActionDecl 'ch.ivy.addon.portal.generic.PortalTaskMenu.PortalTaskMenuData out;
' #txt
Ps0 f19 responseMappingAction 'out=in;
out.errors=result.errors;
out.groupTaskCategories=result.categories;
' #txt
Ps0 f19 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>FindCategories of
group tasks</name>
        <nameStyle>29,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ps0 f19 798 188 36 24 -49 14 #rect
Ps0 f19 @|CallSubIcon #fIcon
Ps0 f27 expr out #txt
Ps0 f27 730 200 798 200 #arcP
Ps0 f27 0 0.6115088733370625 0 0 #arcLabel
Ps0 f6 expr out #txt
Ps0 f6 834 200 883 200 #arcP
Ps0 f8 type ch.ivy.addon.portal.generic.PortalTaskMenu.PortalTaskMenuData #txt
Ps0 f8 processCall MultiPortal/TaskService:findCategories(String,List<String>,Long) #txt
Ps0 f8 doCall true #txt
Ps0 f8 requestActionDecl '<java.lang.String userName,List<java.lang.String> apps,java.lang.Long serverId> param;
' #txt
Ps0 f8 requestMappingAction 'param.userName=in.loginUser;
param.apps=in.involvedApplications;
param.serverId=ch.ivy.addon.portalkit.util.SecurityServiceUtils.getServerIdFromSession();
' #txt
Ps0 f8 responseActionDecl 'ch.ivy.addon.portal.generic.PortalTaskMenu.PortalTaskMenuData out;
' #txt
Ps0 f8 responseMappingAction 'out=in;
out.allTaskCategories=result.categories;
out.errors=result.errors;
' #txt
Ps0 f8 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>FindCategories of
all tasks of current user</name>
        <nameStyle>43,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ps0 f8 550 236 36 24 -64 14 #rect
Ps0 f8 @|CallSubIcon #fIcon
Ps0 f10 type ch.ivy.addon.portal.generic.PortalTaskMenu.PortalTaskMenuData #txt
Ps0 f10 466 186 28 28 14 0 #rect
Ps0 f10 @|AlternativeIcon #fIcon
Ps0 f14 expr out #txt
Ps0 f14 432 200 466 200 #arcP
Ps0 f5 expr in #txt
Ps0 f5 outCond in.hasReadAllTasksPermisson #txt
Ps0 f5 480 186 550 152 #arcP
Ps0 f5 1 480 152 #addKink
Ps0 f5 1 0.0989409665044952 0 0 #arcLabel
Ps0 f20 expr in #txt
Ps0 f20 480 214 550 248 #arcP
Ps0 f20 1 480 248 #addKink
Ps0 f20 1 0.05066689691311281 0 0 #arcLabel
Ps0 f22 type ch.ivy.addon.portal.generic.PortalTaskMenu.PortalTaskMenuData #txt
Ps0 f22 642 186 28 28 14 0 #rect
Ps0 f22 @|AlternativeIcon #fIcon
Ps0 f23 expr out #txt
Ps0 f23 586 152 656 186 #arcP
Ps0 f23 1 656 152 #addKink
Ps0 f23 0 0.9065519332699279 0 0 #arcLabel
Ps0 f11 expr in #txt
Ps0 f11 670 200 694 200 #arcP
Ps0 f24 expr out #txt
Ps0 f24 586 248 656 214 #arcP
Ps0 f24 1 656 248 #addKink
Ps0 f24 0 0.9493331030868872 0 0 #arcLabel
>Proto Ps0 .type ch.ivy.addon.portal.generic.PortalTaskMenu.PortalTaskMenuData #txt
>Proto Ps0 .processKind HTML_DIALOG #txt
>Proto Ps0 -8 -8 16 16 16 26 #rect
>Proto Ps0 '' #fIcon
Ps0 f0 mainOut f2 tail #connect
Ps0 f2 head f1 mainIn #connect
Ps0 f13 mainOut f39 tail #connect
Ps0 f39 head f38 mainIn #connect
Ps0 f12 mainOut f18 tail #connect
Ps0 f18 head f13 mainIn #connect
Ps0 f3 mainOut f15 tail #connect
Ps0 f15 head f7 mainIn #connect
Ps0 f7 mainOut f17 tail #connect
Ps0 f17 head f9 mainIn #connect
Ps0 f16 mainOut f27 tail #connect
Ps0 f27 head f19 mainIn #connect
Ps0 f19 mainOut f6 tail #connect
Ps0 f6 head f4 mainIn #connect
Ps0 f9 mainOut f14 tail #connect
Ps0 f14 head f10 in #connect
Ps0 f10 out f5 tail #connect
Ps0 f5 head f21 mainIn #connect
Ps0 f10 out f20 tail #connect
Ps0 f20 head f8 mainIn #connect
Ps0 f21 mainOut f23 tail #connect
Ps0 f23 head f22 in #connect
Ps0 f22 out f11 tail #connect
Ps0 f11 head f16 mainIn #connect
Ps0 f8 mainOut f24 tail #connect
Ps0 f24 head f22 in #connect
