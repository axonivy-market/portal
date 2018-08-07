[Ivy]
[>Created: Thu Apr 14 18:16:37 ICT 2016]
1520FC11167E4897 3.18 #module
>Proto >Proto Collection #zClass
Ps0 PortalTasksProcess Big #zClass
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
Ps0 @RichDialogProcessEnd f1 '' #zField
Ps0 @RichDialogInitStart f5 '' #zField
Ps0 @Alternative f0 '' #zField
Ps0 @RichDialogInitStart f4 '' #zField
Ps0 @RichDialogInitStart f7 '' #zField
Ps0 @GridStep f18 '' #zField
Ps0 @PushWFArc f19 '' #zField
Ps0 @PushWFArc f27 '' #zField
Ps0 @GridStep f2 '' #zField
Ps0 @PushWFArc f8 '' #zField
Ps0 @PushWFArc f3 '' #zField
Ps0 @GridStep f9 '' #zField
Ps0 @PushWFArc f11 '' #zField
Ps0 @GridStep f12 '' #zField
Ps0 @PushWFArc f13 '' #zField
Ps0 @PushWFArc f6 '' #zField
Ps0 @GridStep f16 '' #zField
Ps0 @PushWFArc f17 '' #zField
Ps0 @PushWFArc f10 '' #zField
>Proto Ps0 Ps0 PortalTasksProcess #zField
Ps0 f1 type ch.ivy.addon.portal.generic.PortalTasks.PortalTasksData #txt
Ps0 f1 573 85 22 22 14 0 #rect
Ps0 f1 @|RichDialogProcessEndIcon #fIcon
Ps0 f5 guid 015216A789F21674 #txt
Ps0 f5 type ch.ivy.addon.portal.generic.PortalTasks.PortalTasksData #txt
Ps0 f5 method start(org.primefaces.model.TreeNode) #txt
Ps0 f5 disableUIEvents true #txt
Ps0 f5 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<org.primefaces.model.TreeNode category> param = methodEvent.getInputArguments();
' #txt
Ps0 f5 inParameterMapAction 'out.category=param.category.getData() as ch.ivy.addon.portalkit.bo.MainMenuNode;
out.selectedNode=param.category;
' #txt
Ps0 f5 outParameterDecl '<> result;
' #txt
Ps0 f5 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start(TreeNode)</name>
        <nameStyle>15,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ps0 f5 52 84 24 24 -42 18 #rect
Ps0 f5 @|RichDialogInitStartIcon #fIcon
Ps0 f0 type ch.ivy.addon.portal.generic.PortalTasks.PortalTasksData #txt
Ps0 f0 402 82 28 28 14 0 #rect
Ps0 f0 @|AlternativeIcon #fIcon
Ps0 f4 guid 1531BE22FA79335F #txt
Ps0 f4 type ch.ivy.addon.portal.generic.PortalTasks.PortalTasksData #txt
Ps0 f4 method start(String,java.lang.Long,java.lang.Long) #txt
Ps0 f4 disableUIEvents true #txt
Ps0 f4 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<java.lang.String keyword,java.lang.Long remoteTaskId,java.lang.Long serverId> param = methodEvent.getInputArguments();
' #txt
Ps0 f4 inParameterMapAction 'out.category.value=ivy.cms.co("/ch.ivy.addon.portalkit.ui.jsf/searchResult/searchResultsFor", java.util.Arrays.asList(ivy.cms.co("/ch.ivy.addon.portalkit.ui.jsf/searchResult/task"), param.keyword));
out.keyword=param.keyword;
out.pageTitle=ivy.cms.co("/ch.ivy.addon.portalkit.ui.jsf/searchResult/searchResultsFor", java.util.Arrays.asList(ivy.cms.co("/ch.ivy.addon.portalkit.ui.jsf/searchResult/task"), param.keyword));
out.remoteTaskId=param.remoteTaskId;
out.serverId=param.serverId;
' #txt
Ps0 f4 outParameterDecl '<> result;
' #txt
Ps0 f4 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start(String,Long,Long)</name>
        <nameStyle>23,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ps0 f4 53 181 22 22 -43 19 #rect
Ps0 f4 @|RichDialogInitStartIcon #fIcon
Ps0 f7 guid 15355B14B166AB89 #txt
Ps0 f7 type ch.ivy.addon.portal.generic.PortalTasks.PortalTasksData #txt
Ps0 f7 method start(java.lang.Long,java.lang.Long,java.lang.Long,String) #txt
Ps0 f7 disableUIEvents true #txt
Ps0 f7 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<java.lang.Long remoteTaskId,java.lang.Long caseId,java.lang.Long serverId,java.lang.String caseName> param = methodEvent.getInputArguments();
' #txt
Ps0 f7 inParameterMapAction 'out.caseId=param.caseId;
out.caseName=param.caseName;
out.remoteTaskId=param.remoteTaskId;
out.serverId=param.serverId;
' #txt
Ps0 f7 outParameterDecl '<> result;
' #txt
Ps0 f7 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start(Long,Long,Long)</name>
        <nameStyle>21,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ps0 f7 53 277 22 22 -49 19 #rect
Ps0 f7 @|RichDialogInitStartIcon #fIcon
Ps0 f18 actionDecl 'ch.ivy.addon.portal.generic.PortalTasks.PortalTasksData out;
' #txt
Ps0 f18 actionTable 'out=in;
out.pageTitle=in.category.value;
' #txt
Ps0 f18 actionCode 'import ch.ivy.addon.portalkit.util.TaskUtils;
import ch.ivy.addon.portalkit.bo.TaskNode;
import ch.ivy.addon.portal.generic.common.TreeNodeType;

TaskNode categoryMenu = in.category as TaskNode;

if(in.selectedNode.type.equals(TreeNodeType.TASKS)) {
	out.category.value = ivy.cms.co("/ch.ivy.addon.portal.generic/CustomLinkGenerator/tasks");
	out.dataModel.setIgnoreInvolvedUser(categoryMenu.rootAllTask);
} else if(in.selectedNode.type.equals(TreeNodeType.TASKS_MY_TASKS)){
	out.category.value = ivy.cms.co("/ch.ivy.addon.portal.generic/CustomLinkGenerator/myTasks");
	out.dataModel.setIgnoreInvolvedUser(false);
} else if(in.selectedNode.type.equals(TreeNodeType.TASKS_ALL_TASKS)){
	out.category.value = ivy.cms.co("/ch.ivy.addon.portalkit.ui.jsf/AllTasks/title");
	out.dataModel.setIgnoreInvolvedUser(true);
} else {
	out.dataModel.setCategory(categoryMenu.category);
	out.dataModel.setIgnoreInvolvedUser(categoryMenu.rootAllTask);
}' #txt
Ps0 f18 type ch.ivy.addon.portal.generic.PortalTasks.PortalTasksData #txt
Ps0 f18 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Update selected category
if language changed and
set task search criteria</name>
        <nameStyle>73,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ps0 f18 182 84 36 24 -50 16 #rect
Ps0 f18 @|StepIcon #fIcon
Ps0 f19 expr out #txt
Ps0 f19 76 96 182 96 #arcP
Ps0 f19 0 0.49210561529161195 0 0 #arcLabel
Ps0 f27 expr out #txt
Ps0 f27 218 96 402 96 #arcP
Ps0 f27 0 0.49210561529161195 0 0 #arcLabel
Ps0 f2 actionDecl 'ch.ivy.addon.portal.generic.PortalTasks.PortalTasksData out;
' #txt
Ps0 f2 actionTable 'out=in;
' #txt
Ps0 f2 actionCode out.dataModel.setSortType(ch.ivy.addon.portalkit.enums.SortType.BY_PRIORITY); #txt
Ps0 f2 type ch.ivy.addon.portal.generic.PortalTasks.PortalTasksData #txt
Ps0 f2 494 84 36 24 20 -2 #rect
Ps0 f2 @|StepIcon #fIcon
Ps0 f8 expr in #txt
Ps0 f8 430 96 494 96 #arcP
Ps0 f8 0 0.49210561529161195 0 0 #arcLabel
Ps0 f3 expr out #txt
Ps0 f3 530 96 573 96 #arcP
Ps0 f3 0 0.49210561529161195 0 0 #arcLabel
Ps0 f9 actionDecl 'ch.ivy.addon.portal.generic.PortalTasks.PortalTasksData out;
' #txt
Ps0 f9 actionTable 'out=in;
' #txt
Ps0 f9 actionCode 'out.dataModel.setCaseId(in.caseId);
out.dataModel.setTaskId(in.remoteTaskId);' #txt
Ps0 f9 type ch.ivy.addon.portal.generic.PortalTasks.PortalTasksData #txt
Ps0 f9 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>prepare search criteria</name>
        <nameStyle>23
</nameStyle>
    </language>
</elementInfo>
' #txt
Ps0 f9 302 276 36 24 -37 16 #rect
Ps0 f9 @|StepIcon #fIcon
Ps0 f11 expr out #txt
Ps0 f11 338 288 416 110 #arcP
Ps0 f11 1 416 288 #addKink
Ps0 f11 0 0.9868233769269045 0 0 #arcLabel
Ps0 f12 actionDecl 'ch.ivy.addon.portal.generic.PortalTasks.PortalTasksData out;
' #txt
Ps0 f12 actionTable 'out=in;
' #txt
Ps0 f12 actionCode 'out.dataModel.setKeyword(in.keyword);
out.dataModel.setTaskId(in.remoteTaskId);' #txt
Ps0 f12 type ch.ivy.addon.portal.generic.PortalTasks.PortalTasksData #txt
Ps0 f12 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>prepare search criteria</name>
        <nameStyle>23
</nameStyle>
    </language>
</elementInfo>
' #txt
Ps0 f12 174 180 36 24 -32 17 #rect
Ps0 f12 @|StepIcon #fIcon
Ps0 f13 expr out #txt
Ps0 f13 75 192 174 192 #arcP
Ps0 f13 0 0.749753712903868 0 0 #arcLabel
Ps0 f6 expr out #txt
Ps0 f6 210 192 416 110 #arcP
Ps0 f6 1 416 192 #addKink
Ps0 f6 0 0.749753712903868 0 0 #arcLabel
Ps0 f16 actionDecl 'ch.ivy.addon.portal.generic.PortalTasks.PortalTasksData out;
' #txt
Ps0 f16 actionTable 'out=in;
' #txt
Ps0 f16 actionCode 'import ch.ivyteam.ivy.server.ServerFactory;
import ch.ivyteam.ivy.request.RequestUriFactory;
import ch.ivy.addon.portalkit.util.SecurityServiceUtils;
import java.util.Arrays;

StringBuilder sb = new StringBuilder();
sb.append("<a class=''ui-link ui-widget'' href=''/");
sb.append(RequestUriFactory.getIvyContextName(ServerFactory.getServer().getApplicationConfigurationManager()));
sb.append(SecurityServiceUtils.findProcessByUserFriendlyRequestPath("Start Processes/PortalTemplatePages/PortalCaseListLinkedFromTask.ivp"));
sb.append("?caseId=");
sb.append(in.caseId);
sb.append("&amp;serverId=");
sb.append(in.serverId);
sb.append("''>#");
sb.append(in.caseId);
sb.append("</a>");

out.category.value = ivy.cms.co("/ch.ivy.addon.portalkit.ui.jsf/taskList/headerTitle/relatedTasksHeader", Arrays.asList(sb.toString(), in.caseName));
out.pageTitle = ivy.cms.co("/ch.ivy.addon.portalkit.ui.jsf/taskList/headerTitle/relatedTasksHeader", Arrays.asList(in.caseId, in.caseName));' #txt
Ps0 f16 security system #txt
Ps0 f16 type ch.ivy.addon.portal.generic.PortalTasks.PortalTasksData #txt
Ps0 f16 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>build header</name>
        <nameStyle>12
</nameStyle>
    </language>
</elementInfo>
' #txt
Ps0 f16 174 276 36 24 -22 15 #rect
Ps0 f16 @|StepIcon #fIcon
Ps0 f17 expr out #txt
Ps0 f17 75 288 174 288 #arcP
Ps0 f17 0 0.9868233769269045 0 0 #arcLabel
Ps0 f10 expr out #txt
Ps0 f10 210 288 302 288 #arcP
>Proto Ps0 .type ch.ivy.addon.portal.generic.PortalTasks.PortalTasksData #txt
>Proto Ps0 .processKind HTML_DIALOG #txt
>Proto Ps0 -8 -8 16 16 16 26 #rect
>Proto Ps0 '' #fIcon
Ps0 f5 mainOut f19 tail #connect
Ps0 f19 head f18 mainIn #connect
Ps0 f18 mainOut f27 tail #connect
Ps0 f27 head f0 in #connect
Ps0 f0 out f8 tail #connect
Ps0 f8 head f2 mainIn #connect
Ps0 f2 mainOut f3 tail #connect
Ps0 f3 head f1 mainIn #connect
Ps0 f9 mainOut f11 tail #connect
Ps0 f11 head f0 in #connect
Ps0 f4 mainOut f13 tail #connect
Ps0 f13 head f12 mainIn #connect
Ps0 f12 mainOut f6 tail #connect
Ps0 f6 head f0 in #connect
Ps0 f7 mainOut f17 tail #connect
Ps0 f17 head f16 mainIn #connect
Ps0 f16 mainOut f10 tail #connect
Ps0 f10 head f9 mainIn #connect
