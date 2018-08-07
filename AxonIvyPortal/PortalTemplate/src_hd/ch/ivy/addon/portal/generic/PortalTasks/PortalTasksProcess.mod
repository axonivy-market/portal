[Ivy]
[>Created: Tue Mar 01 15:23:11 ICT 2016]
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
>Proto Ps0 Ps0 PortalTasksProcess #zField
Ps0 f1 type ch.ivy.addon.portal.generic.PortalTasks.PortalTasksData #txt
Ps0 f1 437 85 22 22 14 0 #rect
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
out.tasks=new List<ch.ivy.addon.portalkit.bo.RemoteTask>();
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
Ps0 f3 270 96 437 96 #arcP
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
