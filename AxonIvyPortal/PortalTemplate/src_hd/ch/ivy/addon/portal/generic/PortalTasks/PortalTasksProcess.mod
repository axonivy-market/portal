[Ivy]
[>Created: Mon Oct 03 13:30:34 ICT 2016]
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
Ps0 @RichDialogInitStart f2 '' #zField
Ps0 @PushWFArc f3 '' #zField
>Proto Ps0 Ps0 PortalTasksProcess #zField
Ps0 f1 type ch.ivy.addon.portal.generic.PortalTasks.PortalTasksData #txt
Ps0 f1 229 133 22 22 14 0 #rect
Ps0 f1 @|RichDialogProcessEndIcon #fIcon
Ps0 f2 guid 1573377403EC1C55 #txt
Ps0 f2 type ch.ivy.addon.portal.generic.PortalTasks.PortalTasksData #txt
Ps0 f2 method useTaskViewWithMenuState(ch.ivy.addon.portal.generic.view.TaskView,String) #txt
Ps0 f2 disableUIEvents true #txt
Ps0 f2 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<ch.ivy.addon.portal.generic.view.TaskView taskView,java.lang.String menuState> param = methodEvent.getInputArguments();
' #txt
Ps0 f2 inParameterMapAction 'out.menuState=param.menuState;
out.taskView=param.taskView;
' #txt
Ps0 f2 outParameterDecl '<> result;
' #txt
Ps0 f2 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>useTaskViewWithMenuState(TaskView,String)</name>
        <nameStyle>41,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ps0 f2 77 133 22 22 -36 17 #rect
Ps0 f2 @|RichDialogInitStartIcon #fIcon
Ps0 f3 expr out #txt
Ps0 f3 99 144 229 144 #arcP
>Proto Ps0 .type ch.ivy.addon.portal.generic.PortalTasks.PortalTasksData #txt
>Proto Ps0 .processKind HTML_DIALOG #txt
>Proto Ps0 -8 -8 16 16 16 26 #rect
>Proto Ps0 '' #fIcon
Ps0 f2 mainOut f3 tail #connect
Ps0 f3 head f1 mainIn #connect
