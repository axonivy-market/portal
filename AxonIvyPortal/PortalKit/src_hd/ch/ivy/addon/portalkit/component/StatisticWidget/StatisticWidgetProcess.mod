[Ivy]
[>Created: Fri Apr 21 11:02:57 ICT 2017]
1521B28EE98E8444 3.20 #module
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
Cs0 @PushWFArc f10 '' #zField
Cs0 @GridStep f11 '' #zField
Cs0 @PushWFArc f2 '' #zField
Cs0 @Alternative f15 '' #zField
Cs0 @PushWFArc f17 '' #zField
Cs0 @PushWFArc f18 '' #zField
Cs0 @PushWFArc f9 '' #zField
Cs0 @CallSub f19 '' #zField
Cs0 @CallSub f20 '' #zField
Cs0 @Join f21 '' #zField
Cs0 @Split f22 '' #zField
Cs0 @PushWFArc f23 '' #zField
Cs0 @SJArc f24 '' #zField
Cs0 @PushWFArc f25 '' #zField
Cs0 @SJArc f26 '' #zField
Cs0 @Alternative f27 '' #zField
Cs0 @PushWFArc f28 '' #zField
Cs0 @PushWFArc f16 '' #zField
Cs0 @PushWFArc f30 '' #zField
Cs0 @GridStep f31 '' #zField
Cs0 @PushWFArc f32 '' #zField
Cs0 @CallSub f33 '' #zField
Cs0 @PushWFArc f34 '' #zField
Cs0 @PushWFArc f29 '' #zField
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
Cs0 f8 actionCode 'import org.primefaces.model.chart.PieChartModel;
import ch.ivy.addon.portalkit.statistics.StatisticChartModel;

StatisticChartModel statisticCharModel = new StatisticChartModel(in.priorityStatistic, in.expiryStatistic);
in.priorityPieModel = statisticCharModel.createPriorityPieChart(in.lowPriorityColor, in.normalPriorityColor, in.highPriorityColor, in.exceptionPriorityColor);
in.expiryBarChartModel = statisticCharModel.createExpiryTasksBarChart(in.expiryBarColor);' #txt
Cs0 f8 type ch.ivy.addon.portalkit.component.StatisticWidget.StatisticWidgetData #txt
Cs0 f8 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>create Chart</name>
        <nameStyle>12
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f8 782 660 36 24 20 -2 #rect
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
Cs0 f6 method setStatistics(ch.ivy.ws.addon.PriorityStatistic,ch.ivy.ws.addon.ExpiryStatistic) #txt
Cs0 f6 disableUIEvents false #txt
Cs0 f6 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<ch.ivy.ws.addon.PriorityStatistic priorityStatistic,ch.ivy.ws.addon.ExpiryStatistic expiryStatistic> param = methodEvent.getInputArguments();
' #txt
Cs0 f6 inParameterMapAction 'out.expiryStatistic=param.#expiryStatistic;
out.priorityStatistic=param.#priorityStatistic;
' #txt
Cs0 f6 outParameterDecl '<> result;
' #txt
Cs0 f6 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>setStatistics(PriorityStatistic,ExpiryStatistic)</name>
        <nameStyle>48,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f6 789 85 22 22 14 0 #rect
Cs0 f6 @|RichDialogMethodStartIcon #fIcon
Cs0 f7 type ch.ivy.addon.portalkit.component.StatisticWidget.StatisticWidgetData #txt
Cs0 f7 789 725 22 22 14 0 #rect
Cs0 f7 @|RichDialogProcessEndIcon #fIcon
Cs0 f10 expr out #txt
Cs0 f10 96 107 96 221 #arcP
Cs0 f11 actionDecl 'ch.ivy.addon.portalkit.component.StatisticWidget.StatisticWidgetData out;
' #txt
Cs0 f11 actionTable 'out=in;
' #txt
Cs0 f11 actionCode 'in.hasStatistic = in.priorityStatistic.low > 0 || in.priorityStatistic.normal > 0 || in.priorityStatistic.high > 0 || in.priorityStatistic.exception > 0;' #txt
Cs0 f11 type ch.ivy.addon.portalkit.component.StatisticWidget.StatisticWidgetData #txt
Cs0 f11 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>check if has data</name>
        <nameStyle>17
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f11 782 532 36 24 20 -2 #rect
Cs0 f11 @|StepIcon #fIcon
Cs0 f2 expr out #txt
Cs0 f2 800 684 800 725 #arcP
Cs0 f15 type ch.ivy.addon.portalkit.component.StatisticWidget.StatisticWidgetData #txt
Cs0 f15 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>has data?</name>
        <nameStyle>9
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f15 786 594 28 28 14 0 #rect
Cs0 f15 @|AlternativeIcon #fIcon
Cs0 f17 expr out #txt
Cs0 f17 800 556 800 594 #arcP
Cs0 f18 expr in #txt
Cs0 f18 outCond in.hasStatistic #txt
Cs0 f18 800 622 800 660 #arcP
Cs0 f9 expr in #txt
Cs0 f9 786 608 789 736 #arcP
Cs0 f9 1 736 608 #addKink
Cs0 f9 2 736 736 #addKink
Cs0 f9 1 0.51171875 0 0 #arcLabel
Cs0 f19 type ch.ivy.addon.portalkit.component.StatisticWidget.StatisticWidgetData #txt
Cs0 f19 processCall MultiPortal/TaskService:analyzeExpiryStatistic(String,List<String>,Long,String) #txt
Cs0 f19 doCall true #txt
Cs0 f19 requestActionDecl '<java.lang.String jsonQuery,List<java.lang.String> apps,java.lang.Long serverId,java.lang.String userName> param;
' #txt
Cs0 f19 requestMappingAction 'param.jsonQuery=in.jsonQuery;
param.apps=in.involvedApplications;
param.serverId=in.serverId;
param.userName=ivy.session.getSessionUserName();
' #txt
Cs0 f19 responseActionDecl 'ch.ivy.addon.portalkit.component.StatisticWidget.StatisticWidgetData out;
' #txt
Cs0 f19 responseMappingAction 'out=in;
out.expiryStatistic=result.expiryStatistic;
' #txt
Cs0 f19 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Expiry Statistic</name>
        <nameStyle>16
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f19 686 404 36 24 20 -2 #rect
Cs0 f19 @|CallSubIcon #fIcon
Cs0 f20 type ch.ivy.addon.portalkit.component.StatisticWidget.StatisticWidgetData #txt
Cs0 f20 processCall MultiPortal/TaskService:analyzePriorityStatistic(String,List<String>,Long,String) #txt
Cs0 f20 doCall true #txt
Cs0 f20 requestActionDecl '<java.lang.String jsonQuery,List<java.lang.String> apps,java.lang.Long serverId,java.lang.String userName> param;
' #txt
Cs0 f20 requestMappingAction 'param.jsonQuery=in.jsonQuery;
param.apps=in.involvedApplications;
param.serverId=in.serverId;
param.userName=ivy.session.getSessionUserName();
' #txt
Cs0 f20 responseActionDecl 'ch.ivy.addon.portalkit.component.StatisticWidget.StatisticWidgetData out;
' #txt
Cs0 f20 responseMappingAction 'out=in;
out.priorityStatistic=result.priorityStatistic;
' #txt
Cs0 f20 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Priority Statistic</name>
        <nameStyle>18,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f20 878 404 36 24 -41 14 #rect
Cs0 f20 @|CallSubIcon #fIcon
Cs0 f21 actionDecl 'ch.ivy.addon.portalkit.component.StatisticWidget.StatisticWidgetData out;
' #txt
Cs0 f21 actionTable 'out=in1;
out.expiryStatistic=in2.expiryStatistic;
out.priorityStatistic=in1.priorityStatistic;
' #txt
Cs0 f21 786 466 28 28 14 0 #rect
Cs0 f21 @|JoinIcon #fIcon
Cs0 f22 actionDecl 'ch.ivy.addon.portalkit.component.StatisticWidget.StatisticWidgetData out1;
ch.ivy.addon.portalkit.component.StatisticWidget.StatisticWidgetData out2;
' #txt
Cs0 f22 actionTable 'out1=in;
out2=in;
' #txt
Cs0 f22 type ch.ivy.addon.portalkit.component.StatisticWidget.StatisticWidgetData #txt
Cs0 f22 786 338 28 28 14 0 #rect
Cs0 f22 @|ThreadIcon #fIcon
Cs0 f23 expr out1 #txt
Cs0 f23 814 352 896 404 #arcP
Cs0 f23 1 896 352 #addKink
Cs0 f23 0 0.8119307153972276 0 0 #arcLabel
Cs0 f24 expr out #txt
Cs0 f24 type ch.ivy.addon.portalkit.component.StatisticWidget.StatisticWidgetData #txt
Cs0 f24 var in1 #txt
Cs0 f24 896 428 814 480 #arcP
Cs0 f24 1 896 480 #addKink
Cs0 f24 1 0.008702834448251838 0 0 #arcLabel
Cs0 f25 expr out2 #txt
Cs0 f25 786 352 704 404 #arcP
Cs0 f25 1 704 352 #addKink
Cs0 f25 1 0.18903366574067884 0 0 #arcLabel
Cs0 f26 expr out #txt
Cs0 f26 type ch.ivy.addon.portalkit.component.StatisticWidget.StatisticWidgetData #txt
Cs0 f26 var in2 #txt
Cs0 f26 704 428 786 480 #arcP
Cs0 f26 1 704 480 #addKink
Cs0 f26 1 0.21092866635369523 0 0 #arcLabel
Cs0 f27 type ch.ivy.addon.portalkit.component.StatisticWidget.StatisticWidgetData #txt
Cs0 f27 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>not initialized?</name>
        <nameStyle>16
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f27 786 146 28 28 14 0 #rect
Cs0 f27 @|AlternativeIcon #fIcon
Cs0 f28 expr out #txt
Cs0 f28 800 107 800 146 #arcP
Cs0 f16 expr out #txt
Cs0 f16 800 494 800 532 #arcP
Cs0 f16 0 0.19011418837928204 0 0 #arcLabel
Cs0 f30 expr in #txt
Cs0 f30 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>no</name>
        <nameStyle>2
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f30 814 160 818 544 #arcP
Cs0 f30 1 960 160 #addKink
Cs0 f30 2 960 544 #addKink
Cs0 f30 1 0.484375 9 0 #arcLabel
Cs0 f31 actionDecl 'ch.ivy.addon.portalkit.component.StatisticWidget.StatisticWidgetData out;
' #txt
Cs0 f31 actionTable 'out=in;
' #txt
Cs0 f31 actionCode 'import ch.ivy.addon.portalkit.util.SecurityServiceUtils;

in.serverId = SecurityServiceUtils.getServerIdFromSession();
String applicationName = SecurityServiceUtils.getApplicationNameFromSession();
if (#applicationName is initialized) {
	in.involvedApplications = [applicationName];
}' #txt
Cs0 f31 type ch.ivy.addon.portalkit.component.StatisticWidget.StatisticWidgetData #txt
Cs0 f31 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>get selected apps</name>
        <nameStyle>17
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f31 782 212 36 24 20 -2 #rect
Cs0 f31 @|StepIcon #fIcon
Cs0 f32 expr in #txt
Cs0 f32 outCond '!in.#priorityStatistic is initialized' #txt
Cs0 f32 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>yes</name>
        <nameStyle>3
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f32 800 174 800 212 #arcP
Cs0 f32 0 0.4444444444444444 -14 0 #arcLabel
Cs0 f33 type ch.ivy.addon.portalkit.component.StatisticWidget.StatisticWidgetData #txt
Cs0 f33 processCall 'Functional Processes/BuildTaskJsonQuery:buildTaskJsonQuery()' #txt
Cs0 f33 doCall true #txt
Cs0 f33 responseActionDecl 'ch.ivy.addon.portalkit.component.StatisticWidget.StatisticWidgetData out;
' #txt
Cs0 f33 responseMappingAction 'out=in;
out.jsonQuery=result.jsonQuery;
' #txt
Cs0 f33 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>BuildTaskJsonQuery</name>
    </language>
</elementInfo>
' #txt
Cs0 f33 783 275 36 24 10 15 #rect
Cs0 f33 @|CallSubIcon #fIcon
Cs0 f34 expr out #txt
Cs0 f34 800 236 801 275 #arcP
Cs0 f34 0 0.4444444444444444 -13 0 #arcLabel
Cs0 f29 expr out #txt
Cs0 f29 801 299 800 338 #arcP
>Proto Cs0 .type ch.ivy.addon.portalkit.component.StatisticWidget.StatisticWidgetData #txt
>Proto Cs0 .processKind HTML_DIALOG #txt
>Proto Cs0 -8 -8 16 16 16 26 #rect
>Proto Cs0 '' #fIcon
Cs0 f3 mainOut f5 tail #connect
Cs0 f5 head f4 mainIn #connect
Cs0 f12 mainOut f14 tail #connect
Cs0 f14 head f13 mainIn #connect
Cs0 f0 mainOut f10 tail #connect
Cs0 f10 head f1 mainIn #connect
Cs0 f8 mainOut f2 tail #connect
Cs0 f2 head f7 mainIn #connect
Cs0 f11 mainOut f17 tail #connect
Cs0 f17 head f15 in #connect
Cs0 f15 out f18 tail #connect
Cs0 f18 head f8 mainIn #connect
Cs0 f15 out f9 tail #connect
Cs0 f9 head f7 mainIn #connect
Cs0 f22 out f23 tail #connect
Cs0 f23 head f20 mainIn #connect
Cs0 f20 mainOut f24 tail #connect
Cs0 f24 head f21 in #connect
Cs0 f22 out f25 tail #connect
Cs0 f25 head f19 mainIn #connect
Cs0 f19 mainOut f26 tail #connect
Cs0 f26 head f21 in #connect
Cs0 f6 mainOut f28 tail #connect
Cs0 f28 head f27 in #connect
Cs0 f21 mainOut f16 tail #connect
Cs0 f16 head f11 mainIn #connect
Cs0 f30 head f11 mainIn #connect
Cs0 f27 out f32 tail #connect
Cs0 f32 head f31 mainIn #connect
Cs0 f27 out f30 tail #connect
Cs0 f31 mainOut f34 tail #connect
Cs0 f34 head f33 mainIn #connect
Cs0 f33 mainOut f29 tail #connect
Cs0 f29 head f22 in #connect
