[Ivy]
[>Created: Mon May 09 17:06:08 ICT 2016]
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
Ps0 @RichDialogInitStart f14 '' #zField
Ps0 @PushWFArc f0 '' #zField
>Proto Ps0 Ps0 PortalTasksProcess #zField
Ps0 f1 type ch.ivy.addon.portal.generic.PortalTasks.PortalTasksData #txt
Ps0 f1 229 133 22 22 14 0 #rect
Ps0 f1 @|RichDialogProcessEndIcon #fIcon
Ps0 f14 guid 15493A984A66C001 #txt
Ps0 f14 type ch.ivy.addon.portal.generic.PortalTasks.PortalTasksData #txt
Ps0 f14 method start(ch.ivy.addon.portal.generic.view.TaskView) #txt
Ps0 f14 disableUIEvents true #txt
Ps0 f14 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<ch.ivy.addon.portal.generic.view.TaskView taskView> param = methodEvent.getInputArguments();
' #txt
Ps0 f14 inParameterMapAction 'out.view=param.taskView;
' #txt
Ps0 f14 outParameterDecl '<> result;
' #txt
Ps0 f14 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start(TaskView)</name>
        <nameStyle>15,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ps0 f14 85 133 22 22 -36 17 #rect
Ps0 f14 @|RichDialogInitStartIcon #fIcon
Ps0 f0 expr out #txt
Ps0 f0 107 144 229 144 #arcP
>Proto Ps0 .type ch.ivy.addon.portal.generic.PortalTasks.PortalTasksData #txt
>Proto Ps0 .processKind HTML_DIALOG #txt
>Proto Ps0 -8 -8 16 16 16 26 #rect
>Proto Ps0 '' #fIcon
Ps0 f14 mainOut f0 tail #connect
Ps0 f0 head f1 mainIn #connect
