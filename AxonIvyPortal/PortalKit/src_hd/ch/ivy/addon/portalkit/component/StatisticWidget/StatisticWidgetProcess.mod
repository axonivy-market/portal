[Ivy]
[>Created: Tue Mar 01 14:35:26 ICT 2016]
1521B28EE98E8444 3.18 #module
>Proto >Proto Collection #zClass
Cs0 StatisticWidgetProcess Big #zClass
Cs0 RD #cInfo
Cs0 #process
Cs0 @TextInP .ui2RdDataAction .ui2RdDataAction #zField
Cs0 @TextInP .rdData2UIAction .rdData2UIAction #zField
Cs0 @TextInP .resExport .resExport #zField
Cs0 @TextInP .type .type #zField
Cs0 @TextInP .processKind .processKind #zField
Cs0 @AnnotationInP-0n ai ai #zField
Cs0 @MessageFlowInP-0n messageIn messageIn #zField
Cs0 @MessageFlowOutP-0n messageOut messageOut #zField
Cs0 @TextInP .xml .xml #zField
Cs0 @TextInP .responsibility .responsibility #zField
Cs0 @RichDialogInitStart f0 '' #zField
Cs0 @RichDialogProcessEnd f1 '' #zField
Cs0 @GridStep f8 '' #zField
Cs0 @RichDialogMethodStart f3 '' #zField
Cs0 @RichDialogProcessEnd f4 '' #zField
Cs0 @PushWFArc f5 '' #zField
Cs0 @RichDialogMethodStart f12 '' #zField
Cs0 @RichDialogProcessEnd f13 '' #zField
Cs0 @PushWFArc f14 '' #zField
Cs0 @RichDialogMethodStart f6 '' #zField
Cs0 @RichDialogProcessEnd f7 '' #zField
Cs0 @PushWFArc f2 '' #zField
Cs0 @PushWFArc f9 '' #zField
Cs0 @PushWFArc f10 '' #zField
>Proto Cs0 Cs0 StatisticWidgetProcess #zField
Cs0 f0 guid 1522EF563D511214 #txt
Cs0 f0 type ch.ivy.addon.portalkit.component.StatisticWidget.StatisticWidgetData #txt
Cs0 f0 method start(String,String,String,String,String) #txt
Cs0 f0 disableUIEvents true #txt
Cs0 f0 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<java.lang.String lowPriorityColor,java.lang.String normalPriorityColor,java.lang.String highPriorityColor,java.lang.String exceptionPriorityColor,java.lang.String expiryBarColor> param = methodEvent.getInputArguments();
' #txt
Cs0 f0 inParameterMapAction 'out.compactMode=true;
out.exceptionPriorityColor=param.exceptionPriorityColor;
out.expiryBarColor=param.expiryBarColor;
out.highPriorityColor=param.highPriorityColor;
out.lowPriorityColor=param.lowPriorityColor;
out.normalPriorityColor=param.normalPriorityColor;
out.username=ivy.session.getSessionUserName();
' #txt
Cs0 f0 outParameterDecl '<> result;
' #txt
Cs0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start(String,String,String,String,String)</name>
    </language>
</elementInfo>
' #txt
Cs0 f0 85 85 22 22 14 0 #rect
Cs0 f0 @|RichDialogInitStartIcon #fIcon
Cs0 f1 type ch.ivy.addon.portalkit.component.StatisticWidget.StatisticWidgetData #txt
Cs0 f1 85 221 22 22 14 0 #rect
Cs0 f1 @|RichDialogProcessEndIcon #fIcon
Cs0 f8 actionDecl 'ch.ivy.addon.portalkit.component.StatisticWidget.StatisticWidgetData out;
' #txt
Cs0 f8 actionTable 'out=in;
' #txt
Cs0 f8 actionCode 'import ch.ivy.addon.portalkit.statistics.StatisticChartModel;
StatisticChartModel statisticCharModel = new StatisticChartModel(in.tasks);
in.priorityPieModel = statisticCharModel.createPriorityPieChart(in.lowPriorityColor, in.normalPriorityColor, in.highPriorityColor, in.exceptionPriorityColor);
in.expiryBarChartModel = statisticCharModel.createExpiryTasksBarChart(in.expiryBarColor);' #txt
Cs0 f8 type ch.ivy.addon.portalkit.component.StatisticWidget.StatisticWidgetData #txt
Cs0 f8 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>create Priority Pie Chart</name>
        <nameStyle>25
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f8 782 148 36 24 20 -2 #rect
Cs0 f8 @|StepIcon #fIcon
Cs0 f3 guid 1523035B9C068DDB #txt
Cs0 f3 type ch.ivy.addon.portalkit.component.StatisticWidget.StatisticWidgetData #txt
Cs0 f3 method switchMode() #txt
Cs0 f3 disableUIEvents false #txt
Cs0 f3 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<> param = methodEvent.getInputArguments();
' #txt
Cs0 f3 inActionCode 'out.compactMode = !out.compactMode;' #txt
Cs0 f3 outParameterDecl '<> result;
' #txt
Cs0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>switchMode()</name>
        <nameStyle>12,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f3 341 85 22 22 14 0 #rect
Cs0 f3 @|RichDialogMethodStartIcon #fIcon
Cs0 f4 type ch.ivy.addon.portalkit.component.StatisticWidget.StatisticWidgetData #txt
Cs0 f4 341 213 22 22 14 0 #rect
Cs0 f4 @|RichDialogProcessEndIcon #fIcon
Cs0 f5 expr out #txt
Cs0 f5 352 107 352 213 #arcP
Cs0 f12 guid 1523086B37B411A8 #txt
Cs0 f12 type ch.ivy.addon.portalkit.component.StatisticWidget.StatisticWidgetData #txt
Cs0 f12 method setCompactMode(Boolean) #txt
Cs0 f12 disableUIEvents false #txt
Cs0 f12 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<java.lang.Boolean compactMode> param = methodEvent.getInputArguments();
' #txt
Cs0 f12 inParameterMapAction 'out.compactMode=param.compactMode;
' #txt
Cs0 f12 outParameterDecl '<> result;
' #txt
Cs0 f12 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>setCompactMode(Boolean)</name>
        <nameStyle>23,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f12 533 85 22 22 14 0 #rect
Cs0 f12 @|RichDialogMethodStartIcon #fIcon
Cs0 f13 type ch.ivy.addon.portalkit.component.StatisticWidget.StatisticWidgetData #txt
Cs0 f13 533 213 22 22 14 0 #rect
Cs0 f13 @|RichDialogProcessEndIcon #fIcon
Cs0 f14 expr out #txt
Cs0 f14 544 107 544 213 #arcP
Cs0 f6 guid 15308130CE4F4ACD #txt
Cs0 f6 type ch.ivy.addon.portalkit.component.StatisticWidget.StatisticWidgetData #txt
Cs0 f6 method setTasks(java.util.List<ch.ivy.addon.portalkit.bo.RemoteTask>) #txt
Cs0 f6 disableUIEvents false #txt
Cs0 f6 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<java.util.List<ch.ivy.addon.portalkit.bo.RemoteTask> tasks> param = methodEvent.getInputArguments();
' #txt
Cs0 f6 inParameterMapAction 'out.tasks=param.tasks;
' #txt
Cs0 f6 outParameterDecl '<> result;
' #txt
Cs0 f6 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>setTasks(List&lt;RemoteTask&gt;)</name>
    </language>
</elementInfo>
' #txt
Cs0 f6 789 85 22 22 14 0 #rect
Cs0 f6 @|RichDialogMethodStartIcon #fIcon
Cs0 f7 type ch.ivy.addon.portalkit.component.StatisticWidget.StatisticWidgetData #txt
Cs0 f7 789 213 22 22 14 0 #rect
Cs0 f7 @|RichDialogProcessEndIcon #fIcon
Cs0 f2 expr out #txt
Cs0 f2 800 107 800 148 #arcP
Cs0 f9 expr out #txt
Cs0 f9 800 172 800 213 #arcP
Cs0 f10 expr out #txt
Cs0 f10 96 107 96 221 #arcP
>Proto Cs0 .type ch.ivy.addon.portalkit.component.StatisticWidget.StatisticWidgetData #txt
>Proto Cs0 .processKind HTML_DIALOG #txt
>Proto Cs0 -8 -8 16 16 16 26 #rect
>Proto Cs0 '' #fIcon
Cs0 f3 mainOut f5 tail #connect
Cs0 f5 head f4 mainIn #connect
Cs0 f12 mainOut f14 tail #connect
Cs0 f14 head f13 mainIn #connect
Cs0 f6 mainOut f2 tail #connect
Cs0 f2 head f8 mainIn #connect
Cs0 f8 mainOut f9 tail #connect
Cs0 f9 head f7 mainIn #connect
Cs0 f0 mainOut f10 tail #connect
Cs0 f10 head f1 mainIn #connect
