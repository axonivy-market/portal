[Ivy]
[>Created: Wed Mar 09 17:08:54 ICT 2016]
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
Ps0 @PushWFArc f2 '' #zField
Ps0 @PushWFArc f3 '' #zField
Ps0 @RichDialogInitStart f4 '' #zField
Ps0 @PushWFArc f6 '' #zField
Ps0 @RichDialogInitStart f7 '' #zField
Ps0 @GridStep f9 '' #zField
Ps0 @RichDialogMethodStart f11 '' #zField
Ps0 @RichDialogProcessEnd f12 '' #zField
Ps0 @CallSub f14 '' #zField
Ps0 @GridStep f13 '' #zField
Ps0 @PushWFArc f15 '' #zField
Ps0 @PushWFArc f16 '' #zField
Ps0 @PushWFArc f17 '' #zField
Ps0 @PushWFArc f10 '' #zField
Ps0 @PushWFArc f8 '' #zField
>Proto Ps0 Ps0 PortalTasksProcess #zField
Ps0 f1 type ch.ivy.addon.portal.generic.PortalTasks.PortalTasksData #txt
Ps0 f1 501 85 22 22 14 0 #rect
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
Ps0 f5 52 84 24 24 14 0 #rect
Ps0 f5 @|RichDialogInitStartIcon #fIcon
Ps0 f0 type ch.ivy.addon.portal.generic.PortalTasks.PortalTasksData #txt
Ps0 f0 242 82 28 28 14 0 #rect
Ps0 f0 @|AlternativeIcon #fIcon
Ps0 f2 expr out #txt
Ps0 f2 76 96 242 96 #arcP
Ps0 f2 0 0.49210561529161195 0 0 #arcLabel
Ps0 f3 expr in #txt
Ps0 f3 270 96 501 96 #arcP
Ps0 f3 0 0.49210561529161195 0 0 #arcLabel
Ps0 f4 guid 1531BE22FA79335F #txt
Ps0 f4 type ch.ivy.addon.portal.generic.PortalTasks.PortalTasksData #txt
Ps0 f4 method start(String,java.lang.Long,java.lang.Long) #txt
Ps0 f4 disableUIEvents true #txt
Ps0 f4 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<java.lang.String keyword,java.lang.Long remoteTaskId,java.lang.Long serverId> param = methodEvent.getInputArguments();
' #txt
Ps0 f4 inParameterMapAction 'out.category.value=ivy.cms.co("/ch.ivy.addon.portalkit.ui.jsf/searchResult/searchResultsFor", java.util.Arrays.asList(ivy.cms.co("/ch.ivy.addon.portalkit.ui.jsf/searchResult/task"), param.keyword));
out.keyword=param.keyword;
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
Ps0 f4 53 181 22 22 14 0 #rect
Ps0 f4 @|RichDialogInitStartIcon #fIcon
Ps0 f6 expr out #txt
Ps0 f6 75 192 256 110 #arcP
Ps0 f6 1 256 192 #addKink
Ps0 f6 0 0.749753712903868 0 0 #arcLabel
Ps0 f7 guid 15355B14B166AB89 #txt
Ps0 f7 type ch.ivy.addon.portal.generic.PortalTasks.PortalTasksData #txt
Ps0 f7 method start(java.lang.Long,java.lang.Long,java.lang.Long) #txt
Ps0 f7 disableUIEvents true #txt
Ps0 f7 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<java.lang.Long remoteTaskId,java.lang.Long caseId,java.lang.Long serverId> param = methodEvent.getInputArguments();
' #txt
Ps0 f7 inParameterMapAction 'out.caseId=param.caseId;
out.category.value=ivy.cms.co("/ch.ivy.addon.portalkit.ui.jsf/caseDetails/relatedTasks");
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
Ps0 f7 53 277 22 22 14 0 #rect
Ps0 f7 @|RichDialogInitStartIcon #fIcon
Ps0 f9 actionDecl 'ch.ivy.addon.portal.generic.PortalTasks.PortalTasksData out;
' #txt
Ps0 f9 actionTable 'out=in;
' #txt
Ps0 f9 actionCode 'import ch.ivy.addon.portalkit.persistence.domain.Server;
import ch.ivy.addon.portalkit.service.ServerService;

ServerService service = new ServerService();
in.server = service.findById(in.serverId) as Server;' #txt
Ps0 f9 type ch.ivy.addon.portal.generic.PortalTasks.PortalTasksData #txt
Ps0 f9 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>find server by id</name>
        <nameStyle>17
</nameStyle>
    </language>
</elementInfo>
' #txt
Ps0 f9 238 404 36 24 20 -2 #rect
Ps0 f9 @|StepIcon #fIcon
Ps0 f11 guid 15355B9C36FA903C #txt
Ps0 f11 type ch.ivy.addon.portal.generic.PortalTasks.PortalTasksData #txt
Ps0 f11 method findTasksOfCase() #txt
Ps0 f11 disableUIEvents false #txt
Ps0 f11 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<> param = methodEvent.getInputArguments();
' #txt
Ps0 f11 outParameterDecl '<List<ch.ivy.addon.portalkit.bo.RemoteTask> remoteTasks> result;
' #txt
Ps0 f11 outParameterMapAction 'result.remoteTasks=in.remoteTasks;
' #txt
Ps0 f11 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>findTasksOfCase()</name>
        <nameStyle>17,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ps0 f11 53 405 22 22 14 0 #rect
Ps0 f11 @|RichDialogMethodStartIcon #fIcon
Ps0 f12 type ch.ivy.addon.portal.generic.PortalTasks.PortalTasksData #txt
Ps0 f12 693 405 22 22 14 0 #rect
Ps0 f12 @|RichDialogProcessEndIcon #fIcon
Ps0 f14 type ch.ivy.addon.portal.generic.PortalTasks.PortalTasksData #txt
Ps0 f14 processCall MultiPortal/TaskService:findTasksOfCase(String,ch.ivy.addon.portalkit.persistence.domain.Server,Long) #txt
Ps0 f14 doCall true #txt
Ps0 f14 requestActionDecl '<java.lang.String userName,ch.ivy.addon.portalkit.persistence.domain.Server server,java.lang.Long caseId> param;
' #txt
Ps0 f14 requestMappingAction 'param.userName=ivy.session.getSessionUserName();
param.server=in.server;
param.caseId=in.caseId;
' #txt
Ps0 f14 responseActionDecl 'ch.ivy.addon.portal.generic.PortalTasks.PortalTasksData out;
' #txt
Ps0 f14 responseMappingAction 'out=in;
out.remoteTasks=result.tasks;
' #txt
Ps0 f14 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>TaskService</name>
        <nameStyle>11,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ps0 f14 398 404 36 24 20 -2 #rect
Ps0 f14 @|CallSubIcon #fIcon
Ps0 f13 actionDecl 'ch.ivy.addon.portal.generic.PortalTasks.PortalTasksData out;
' #txt
Ps0 f13 actionTable 'out=in;
' #txt
Ps0 f13 actionCode 'import ch.ivy.addon.portalkit.comparator.TaskPriorityComparator;

in.remoteTasks.sort(new TaskPriorityComparator());' #txt
Ps0 f13 type ch.ivy.addon.portal.generic.PortalTasks.PortalTasksData #txt
Ps0 f13 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>sort tasks by priority</name>
        <nameStyle>22
</nameStyle>
    </language>
</elementInfo>
' #txt
Ps0 f13 542 404 36 24 20 -2 #rect
Ps0 f13 @|StepIcon #fIcon
Ps0 f15 expr out #txt
Ps0 f15 434 416 542 416 #arcP
Ps0 f16 expr out #txt
Ps0 f16 578 416 693 416 #arcP
Ps0 f17 expr out #txt
Ps0 f17 75 288 256 110 #arcP
Ps0 f17 1 256 288 #addKink
Ps0 f17 0 0.9868233769269045 0 0 #arcLabel
Ps0 f10 expr out #txt
Ps0 f10 274 416 398 416 #arcP
Ps0 f10 0 0.9998906235175515 0 0 #arcLabel
Ps0 f8 expr out #txt
Ps0 f8 75 416 238 416 #arcP
>Proto Ps0 .type ch.ivy.addon.portal.generic.PortalTasks.PortalTasksData #txt
>Proto Ps0 .processKind HTML_DIALOG #txt
>Proto Ps0 -8 -8 16 16 16 26 #rect
>Proto Ps0 '' #fIcon
Ps0 f5 mainOut f2 tail #connect
Ps0 f2 head f0 in #connect
Ps0 f0 out f3 tail #connect
Ps0 f3 head f1 mainIn #connect
Ps0 f4 mainOut f6 tail #connect
Ps0 f6 head f0 in #connect
Ps0 f14 mainOut f15 tail #connect
Ps0 f15 head f13 mainIn #connect
Ps0 f13 mainOut f16 tail #connect
Ps0 f16 head f12 mainIn #connect
Ps0 f7 mainOut f17 tail #connect
Ps0 f17 head f0 in #connect
Ps0 f9 mainOut f10 tail #connect
Ps0 f10 head f14 mainIn #connect
Ps0 f11 mainOut f8 tail #connect
Ps0 f8 head f9 mainIn #connect
