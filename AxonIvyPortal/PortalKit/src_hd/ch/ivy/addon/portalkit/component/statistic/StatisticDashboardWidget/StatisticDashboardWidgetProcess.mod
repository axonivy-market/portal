[Ivy]
1600AC95D2CA7CEA 3.26 #module
>Proto >Proto Collection #zClass
Ss0 StatisticDashboardWidgetProcess Big #zClass
Ss0 RD #cInfo
Ss0 #process
Ss0 @TextInP .ui2RdDataAction .ui2RdDataAction #zField
Ss0 @TextInP .rdData2UIAction .rdData2UIAction #zField
Ss0 @TextInP .resExport .resExport #zField
Ss0 @TextInP .type .type #zField
Ss0 @TextInP .processKind .processKind #zField
Ss0 @AnnotationInP-0n ai ai #zField
Ss0 @MessageFlowInP-0n messageIn messageIn #zField
Ss0 @MessageFlowOutP-0n messageOut messageOut #zField
Ss0 @TextInP .xml .xml #zField
Ss0 @TextInP .responsibility .responsibility #zField
Ss0 @RichDialogProcessStart f3 '' #zField
Ss0 @RichDialogEnd f4 '' #zField
Ss0 @PushWFArc f5 '' #zField
Ss0 @GridStep f8 '' #zField
Ss0 @RichDialogInitStart f11 '' #zField
Ss0 @RichDialogProcessEnd f14 '' #zField
Ss0 @PushWFArc f0 '' #zField
Ss0 @RichDialogMethodStart f1 '' #zField
Ss0 @RichDialogProcessEnd f2 '' #zField
Ss0 @RichDialogMethodStart f13 '' #zField
Ss0 @RichDialogMethodStart f15 '' #zField
Ss0 @RichDialogMethodStart f16 '' #zField
Ss0 @RichDialogMethodStart f17 '' #zField
Ss0 @RichDialogProcessEnd f18 '' #zField
Ss0 @GridStep f19 '' #zField
Ss0 @PushWFArc f20 '' #zField
Ss0 @PushWFArc f21 '' #zField
Ss0 @PushWFArc f22 '' #zField
Ss0 @PushWFArc f23 '' #zField
Ss0 @PushWFArc f24 '' #zField
Ss0 @RichDialogMethodStart f25 '' #zField
Ss0 @GridStep f26 '' #zField
Ss0 @PushWFArc f27 '' #zField
Ss0 @RichDialogProcessEnd f28 '' #zField
Ss0 @PushWFArc f29 '' #zField
Ss0 @GridStep f30 '' #zField
Ss0 @PushWFArc f12 '' #zField
Ss0 @CallSub f7 '' #zField
Ss0 @PushWFArc f10 '' #zField
Ss0 @GridStep f36 '' #zField
Ss0 @PushWFArc f38 '' #zField
Ss0 @GridStep f40 '' #zField
Ss0 @CallSub f42 '' #zField
Ss0 @PushWFArc f43 '' #zField
Ss0 @RichDialogProcessStart f6 '' #zField
Ss0 @PushWFArc f9 '' #zField
Ss0 @RichDialogProcessStart f44 '' #zField
Ss0 @PushWFArc f35 '' #zField
Ss0 @RichDialogProcessStart f45 '' #zField
Ss0 @RichDialogMethodStart f32 '' #zField
Ss0 @RichDialogProcessEnd f33 '' #zField
Ss0 @PushWFArc f34 '' #zField
Ss0 @Alternative f39 '' #zField
Ss0 @PushWFArc f41 '' #zField
Ss0 @PushWFArc f37 '' #zField
Ss0 @RichDialogProcessEnd f46 '' #zField
Ss0 @RichDialogProcessStart f50 '' #zField
Ss0 @RichDialogProcessEnd f48 '' #zField
Ss0 @PushWFArc f49 '' #zField
Ss0 @RichDialogProcessStart f51 '' #zField
Ss0 @RichDialogProcessEnd f52 '' #zField
Ss0 @GridStep f53 '' #zField
Ss0 @PushWFArc f54 '' #zField
Ss0 @PushWFArc f55 '' #zField
Ss0 @RichDialogProcessStart f56 '' #zField
Ss0 @PushWFArc f57 '' #zField
Ss0 @PushWFArc f47 '' #zField
Ss0 @RichDialogProcessStart f58 '' #zField
Ss0 @PushWFArc f59 '' #zField
Ss0 @Alternative f60 '' #zField
Ss0 @PushWFArc f61 '' #zField
Ss0 @PushWFArc f31 '' #zField
Ss0 @PushWFArc f62 '' #zField
Ss0 @RichDialogMethodStart f63 '' #zField
Ss0 @RichDialogProcessEnd f64 '' #zField
Ss0 @GridStep f65 '' #zField
Ss0 @PushWFArc f66 '' #zField
Ss0 @PushWFArc f67 '' #zField
>Proto Ss0 Ss0 StatisticDashboardWidgetProcess #zField
Ss0 f3 guid 1600AC95D5A96D44 #txt
Ss0 f3 type ch.ivy.addon.portalkit.component.statistic.StatisticDashboardWidget.StatisticDashboardWidgetData #txt
Ss0 f3 actionDecl 'ch.ivy.addon.portalkit.component.statistic.StatisticDashboardWidget.StatisticDashboardWidgetData out;
' #txt
Ss0 f3 actionTable 'out=in;
' #txt
Ss0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>close</name>
    </language>
</elementInfo>
' #txt
Ss0 f3 371 51 26 26 -15 12 #rect
Ss0 f3 @|RichDialogProcessStartIcon #fIcon
Ss0 f4 type ch.ivy.addon.portalkit.component.statistic.StatisticDashboardWidget.StatisticDashboardWidgetData #txt
Ss0 f4 guid 1600AC95D5A90957 #txt
Ss0 f4 499 51 26 26 0 12 #rect
Ss0 f4 @|RichDialogEndIcon #fIcon
Ss0 f5 expr out #txt
Ss0 f5 397 64 499 64 #arcP
Ss0 f8 actionDecl 'ch.ivy.addon.portalkit.component.statistic.StatisticDashboardWidget.StatisticDashboardWidgetData out;
' #txt
Ss0 f8 actionTable 'out=in;
' #txt
Ss0 f8 actionCode 'import ch.ivy.addon.portalkit.statistics.StatisticChartQueryUtils;

in.taskQuery = StatisticChartQueryUtils.getQueryForSelectedItemOfTaskByPriorityChart(in.event, in.selectedStatisticChart);
in.taskListName = in.selectedStatisticChart.name;' #txt
Ss0 f8 type ch.ivy.addon.portalkit.component.statistic.StatisticDashboardWidget.StatisticDashboardWidgetData #txt
Ss0 f8 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Build task query</name>
        <nameStyle>16
</nameStyle>
    </language>
</elementInfo>
' #txt
Ss0 f8 280 402 112 44 -43 -8 #rect
Ss0 f8 @|StepIcon #fIcon
Ss0 f11 guid 1604F11B5BA97CC5 #txt
Ss0 f11 type ch.ivy.addon.portalkit.component.statistic.StatisticDashboardWidget.StatisticDashboardWidgetData #txt
Ss0 f11 method start() #txt
Ss0 f11 disableUIEvents true #txt
Ss0 f11 inParameterDecl 'ch.ivy.addon.portalkit.component.statistic.StatisticDashboardWidget.StatisticDashboardWidgetData out;
' #txt
Ss0 f11 inParameterMapAction 'out.isFinishLoadCharts=false;
' #txt
Ss0 f11 outParameterDecl '<> result;
' #txt
Ss0 f11 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start()</name>
        <nameStyle>7,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ss0 f11 115 51 26 26 -16 15 #rect
Ss0 f11 @|RichDialogInitStartIcon #fIcon
Ss0 f14 type ch.ivy.addon.portalkit.component.statistic.StatisticDashboardWidget.StatisticDashboardWidgetData #txt
Ss0 f14 243 51 26 26 0 12 #rect
Ss0 f14 @|RichDialogProcessEndIcon #fIcon
Ss0 f0 expr out #txt
Ss0 f0 141 64 243 64 #arcP
Ss0 f1 guid 1604F3D5F5A87086 #txt
Ss0 f1 type ch.ivy.addon.portalkit.component.statistic.StatisticDashboardWidget.StatisticDashboardWidgetData #txt
Ss0 f1 method initialize(java.util.List<ch.ivy.addon.portalkit.statistics.StatisticChart>,Boolean,Boolean,Boolean) #txt
Ss0 f1 disableUIEvents false #txt
Ss0 f1 inParameterDecl '<java.util.List<ch.ivy.addon.portalkit.statistics.StatisticChart> statisticChartList,java.lang.Boolean showTaskListImmediately,java.lang.Boolean isBackFromDrilldown,java.lang.Boolean isCompactMode> param;' #txt
Ss0 f1 inParameterMapAction 'out.isBackFromDrilldown=param.isBackFromDrilldown;
out.isCompactMode=param.isCompactMode;
out.showTaskListImmediately=param.showTaskListImmediately;
out.statisticChartList=param.statisticChartList;
' #txt
Ss0 f1 outParameterDecl '<> result;' #txt
Ss0 f1 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>initialize</name>
        <nameStyle>10,5
</nameStyle>
    </language>
</elementInfo>
' #txt
Ss0 f1 643 51 26 26 -27 16 #rect
Ss0 f1 @|RichDialogMethodStartIcon #fIcon
Ss0 f2 type ch.ivy.addon.portalkit.component.statistic.StatisticDashboardWidget.StatisticDashboardWidgetData #txt
Ss0 f2 1099 51 26 26 0 12 #rect
Ss0 f2 @|RichDialogProcessEndIcon #fIcon
Ss0 f13 guid 16052B0A98BF3B09 #txt
Ss0 f13 type ch.ivy.addon.portalkit.component.statistic.StatisticDashboardWidget.StatisticDashboardWidgetData #txt
Ss0 f13 method moveUp(ch.ivy.addon.portalkit.statistics.StatisticChart) #txt
Ss0 f13 disableUIEvents false #txt
Ss0 f13 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<ch.ivy.addon.portalkit.statistics.StatisticChart statisticChart> param = methodEvent.getInputArguments();
' #txt
Ss0 f13 inParameterMapAction 'out.selectedStatisticChart=param.statisticChart;
' #txt
Ss0 f13 inActionCode 'import java.util.Collections;

int index = out.statisticChartList.indexOf(out.selectedStatisticChart);
Collections.swap(out.statisticChartList, index, index - 2);' #txt
Ss0 f13 outParameterDecl '<> result;
' #txt
Ss0 f13 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>moveUp(StatisticChart)</name>
        <nameStyle>22,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ss0 f13 107 659 26 26 -63 15 #rect
Ss0 f13 @|RichDialogMethodStartIcon #fIcon
Ss0 f15 guid 16052B0AD987D1FF #txt
Ss0 f15 type ch.ivy.addon.portalkit.component.statistic.StatisticDashboardWidget.StatisticDashboardWidgetData #txt
Ss0 f15 method moveDown(ch.ivy.addon.portalkit.statistics.StatisticChart) #txt
Ss0 f15 disableUIEvents false #txt
Ss0 f15 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<ch.ivy.addon.portalkit.statistics.StatisticChart statisticChart> param = methodEvent.getInputArguments();
' #txt
Ss0 f15 inParameterMapAction 'out.selectedStatisticChart=param.statisticChart;
' #txt
Ss0 f15 inActionCode 'import java.util.Collections;

int index = out.statisticChartList.indexOf(out.selectedStatisticChart);
Collections.swap(out.statisticChartList, index, index + 2);
' #txt
Ss0 f15 outParameterDecl '<> result;
' #txt
Ss0 f15 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>moveDown(StatisticChart)</name>
        <nameStyle>24,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ss0 f15 107 755 26 26 -71 15 #rect
Ss0 f15 @|RichDialogMethodStartIcon #fIcon
Ss0 f16 guid 16052B0B212D3060 #txt
Ss0 f16 type ch.ivy.addon.portalkit.component.statistic.StatisticDashboardWidget.StatisticDashboardWidgetData #txt
Ss0 f16 method moveLeft(ch.ivy.addon.portalkit.statistics.StatisticChart) #txt
Ss0 f16 disableUIEvents false #txt
Ss0 f16 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<ch.ivy.addon.portalkit.statistics.StatisticChart statisticChart> param = methodEvent.getInputArguments();
' #txt
Ss0 f16 inParameterMapAction 'out.selectedStatisticChart=param.statisticChart;
' #txt
Ss0 f16 inActionCode 'import java.util.Collections;

int index = out.statisticChartList.indexOf(out.selectedStatisticChart);
Collections.swap(out.statisticChartList, index, index -1);' #txt
Ss0 f16 outParameterDecl '<> result;
' #txt
Ss0 f16 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>moveLeft(StatisticChart)</name>
        <nameStyle>24,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ss0 f16 107 851 26 26 -65 15 #rect
Ss0 f16 @|RichDialogMethodStartIcon #fIcon
Ss0 f17 guid 16052B0B70AD24B1 #txt
Ss0 f17 type ch.ivy.addon.portalkit.component.statistic.StatisticDashboardWidget.StatisticDashboardWidgetData #txt
Ss0 f17 method moveRight(ch.ivy.addon.portalkit.statistics.StatisticChart) #txt
Ss0 f17 disableUIEvents false #txt
Ss0 f17 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<ch.ivy.addon.portalkit.statistics.StatisticChart statisticChart> param = methodEvent.getInputArguments();
' #txt
Ss0 f17 inParameterMapAction 'out.selectedStatisticChart=param.statisticChart;
' #txt
Ss0 f17 inActionCode 'import java.util.Collections;

int index = out.statisticChartList.indexOf(out.selectedStatisticChart);
Collections.swap(out.statisticChartList, index, index + 1);' #txt
Ss0 f17 outParameterDecl '<> result;
' #txt
Ss0 f17 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>moveRight(StatisticChart)</name>
        <nameStyle>25,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ss0 f17 107 947 26 26 -70 15 #rect
Ss0 f17 @|RichDialogMethodStartIcon #fIcon
Ss0 f18 type ch.ivy.addon.portalkit.component.statistic.StatisticDashboardWidget.StatisticDashboardWidgetData #txt
Ss0 f18 491 659 26 26 0 12 #rect
Ss0 f18 @|RichDialogProcessEndIcon #fIcon
Ss0 f19 actionDecl 'ch.ivy.addon.portalkit.component.statistic.StatisticDashboardWidget.StatisticDashboardWidgetData out;
' #txt
Ss0 f19 actionTable 'out=in;
' #txt
Ss0 f19 actionCode 'import ch.ivy.addon.portalkit.service.StatisticService;
import ch.ivy.addon.portalkit.statistics.StatisticChart;

StatisticService service = new StatisticService();

for(StatisticChart chart : in.statisticChartList) {
    chart.setPosition(in.statisticChartList.indexOf(chart));
    service.save(chart);
}' #txt
Ss0 f19 type ch.ivy.addon.portalkit.component.statistic.StatisticDashboardWidget.StatisticDashboardWidgetData #txt
Ss0 f19 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Update charts position</name>
        <nameStyle>22
</nameStyle>
    </language>
</elementInfo>
' #txt
Ss0 f19 280 650 128 44 -61 -8 #rect
Ss0 f19 @|StepIcon #fIcon
Ss0 f20 expr out #txt
Ss0 f20 408 672 491 672 #arcP
Ss0 f21 expr out #txt
Ss0 f21 133 672 280 672 #arcP
Ss0 f21 0 0.8266142598914986 0 0 #arcLabel
Ss0 f22 expr out #txt
Ss0 f22 133 768 344 694 #arcP
Ss0 f22 1 344 768 #addKink
Ss0 f22 0 0.9814183125561957 0 0 #arcLabel
Ss0 f23 expr out #txt
Ss0 f23 133 864 344 694 #arcP
Ss0 f23 1 344 864 #addKink
Ss0 f23 0 0.6753694528085658 0 0 #arcLabel
Ss0 f24 expr out #txt
Ss0 f24 133 960 344 694 #arcP
Ss0 f24 1 344 960 #addKink
Ss0 f24 0 0.7583859937504577 0 0 #arcLabel
Ss0 f25 guid 16052B8CCBF953C6 #txt
Ss0 f25 type ch.ivy.addon.portalkit.component.statistic.StatisticDashboardWidget.StatisticDashboardWidgetData #txt
Ss0 f25 method deleteChart() #txt
Ss0 f25 disableUIEvents false #txt
Ss0 f25 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<> param = methodEvent.getInputArguments();
' #txt
Ss0 f25 outParameterDecl '<> result;
' #txt
Ss0 f25 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>deleteChart(StatisticChart)</name>
        <nameStyle>27,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ss0 f25 115 1011 26 26 -72 15 #rect
Ss0 f25 @|RichDialogMethodStartIcon #fIcon
Ss0 f26 actionDecl 'ch.ivy.addon.portalkit.component.statistic.StatisticDashboardWidget.StatisticDashboardWidgetData out;
' #txt
Ss0 f26 actionTable 'out=in;
' #txt
Ss0 f26 actionCode 'import ch.ivy.addon.portalkit.statistics.StatisticChart;
import ch.ivy.addon.portalkit.service.StatisticService;

StatisticService service = new StatisticService();

in.statisticChartList.remove(in.selectedStatisticChart);
service.delete(in.selectedStatisticChart.id);

for(StatisticChart chart : in.statisticChartList) {
      chart.setPosition(in.statisticChartList.indexOf(chart));
	service.save(chart);
}' #txt
Ss0 f26 type ch.ivy.addon.portalkit.component.statistic.StatisticDashboardWidget.StatisticDashboardWidgetData #txt
Ss0 f26 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Delete chart</name>
        <nameStyle>12
</nameStyle>
    </language>
</elementInfo>
' #txt
Ss0 f26 288 1002 112 44 -33 -8 #rect
Ss0 f26 @|StepIcon #fIcon
Ss0 f27 expr out #txt
Ss0 f27 141 1024 288 1024 #arcP
Ss0 f28 type ch.ivy.addon.portalkit.component.statistic.StatisticDashboardWidget.StatisticDashboardWidgetData #txt
Ss0 f28 499 1011 26 26 0 12 #rect
Ss0 f28 @|RichDialogProcessEndIcon #fIcon
Ss0 f29 expr out #txt
Ss0 f29 400 1024 499 1024 #arcP
Ss0 f30 actionDecl 'ch.ivy.addon.portalkit.component.statistic.StatisticDashboardWidget.StatisticDashboardWidgetData out;
' #txt
Ss0 f30 actionTable 'out=in;
' #txt
Ss0 f30 actionCode 'import org.primefaces.context.RequestContext;
import ch.ivy.addon.portalkit.statistics.StatisticChart;
import ch.ivy.addon.portalkit.service.StatisticService;

StatisticService service = new StatisticService();
if (in.isBackFromDrilldown) {
	in.statisticChartList = service.findStatisticChartsByUserId(ivy.session.getSessionUser().getId());
	in.selectedItemOfDrilldown = null;
}

if (in.statisticChartList.size() != 0) {
	in.hasStatistic = true;
	RequestContext.getCurrentInstance().execute("bindCursorChangeEvent()");
	if(in.selectedItemOfDrilldown.isEmpty()){
		service.generateChartModelForStatisticCharts(in.statisticChartList);
	}else{
	  StatisticChart newChart = service.drilldownExpiryChart(in.selectedItemOfDrilldown,in.selectedStatisticChart,in.previousSelectedMonth, in.previousSelectedWeek);
	  service.drilldownExpiryChart(in.selectedItemOfDrilldown,in.selectedStatisticChart,in.previousSelectedMonth, in.previousSelectedWeek);
		in.statisticChartList.clear();
		in.statisticChartList.add(in.selectedStatisticChart);
		in.statisticChartList.add(newChart);
	}
} else {
	in.hasStatistic = false;
}
in.isFinishLoadCharts = true;' #txt
Ss0 f30 type ch.ivy.addon.portalkit.component.statistic.StatisticDashboardWidget.StatisticDashboardWidgetData #txt
Ss0 f30 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Generate chart models</name>
        <nameStyle>21
</nameStyle>
    </language>
</elementInfo>
' #txt
Ss0 f30 856 42 144 44 -63 -8 #rect
Ss0 f30 @|StepIcon #fIcon
Ss0 f12 expr out #txt
Ss0 f12 1000 64 1099 64 #arcP
Ss0 f7 type ch.ivy.addon.portalkit.component.statistic.StatisticDashboardWidget.StatisticDashboardWidgetData #txt
Ss0 f7 processCall 'Functional Processes/Navigator:viewTaskForAnalytic(String,ch.ivyteam.ivy.workflow.query.TaskQuery)' #txt
Ss0 f7 doCall true #txt
Ss0 f7 requestActionDecl '<java.lang.String chartName,ch.ivyteam.ivy.workflow.query.TaskQuery taskQuery> param;
' #txt
Ss0 f7 requestMappingAction 'param.chartName=in.taskListName;
param.taskQuery=in.taskQuery;
' #txt
Ss0 f7 responseActionDecl 'ch.ivy.addon.portalkit.component.statistic.StatisticDashboardWidget.StatisticDashboardWidgetData out;
' #txt
Ss0 f7 responseMappingAction 'out=in;
' #txt
Ss0 f7 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Navigator</name>
        <nameStyle>9,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ss0 f7 480 402 112 44 -26 -8 #rect
Ss0 f7 @|CallSubIcon #fIcon
Ss0 f10 expr out #txt
Ss0 f10 392 424 480 424 #arcP
Ss0 f36 actionDecl 'ch.ivy.addon.portalkit.component.statistic.StatisticDashboardWidget.StatisticDashboardWidgetData out;
' #txt
Ss0 f36 actionTable 'out=in;
' #txt
Ss0 f36 actionCode 'import org.apache.commons.lang3.StringUtils;
import ch.ivy.addon.portalkit.datamodel.TaskLazyDataModel;
import ch.ivy.addon.portalkit.service.StatisticService;
import ch.ivy.addon.portalkit.statistics.StatisticChartQueryUtils;
import ch.ivy.addon.portalkit.statistics.StatisticChart;

String selectedChartId = out.event.getComponent().getAttributes().get("selectedChartId") as String;
in.selectedItemOfDrilldown = StatisticService.getSelectedValueOfBarChart(in.event);

if (StatisticService.selectMonthOfYear(in.selectedItemOfDrilldown))  {
	in.previousSelectedMonth = in.selectedItemOfDrilldown;
} else if (StatisticService.selectWeekOfMonth(in.selectedItemOfDrilldown))  {
	in.previousSelectedWeek = in.selectedItemOfDrilldown;
} else if (StatisticService.selectDayOfWeek(in.selectedItemOfDrilldown)) {
	in.previousSelectedDay = in.selectedItemOfDrilldown;
}

for (StatisticChart chart : in.statisticChartList) {
	if (chart.id == selectedChartId) {
		in.selectedStatisticChart = chart;
		break;
	}
}

in.taskQuery = StatisticChartQueryUtils.getQueryForSelectedItemOfTaskByExpiryChart(in.event, in.selectedStatisticChart, in.previousSelectedMonth, in.previousSelectedWeek, in.previousSelectedDay);
in.taskListName = in.selectedStatisticChart.name + " - " + in.selectedItemOfDrilldown;
if (StatisticService.selectHourOfDay(in.selectedItemOfDrilldown)) {
	in.taskListName += " " + ivy.cms.co("/ch.ivy.addon.portalkit.ui.jsf/statistic/chart/taskByExpiry/hour");
}
in.selectedItemOfDrilldown = StringUtils.EMPTY;
' #txt
Ss0 f36 type ch.ivy.addon.portalkit.component.statistic.StatisticDashboardWidget.StatisticDashboardWidgetData #txt
Ss0 f36 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Build task query</name>
        <nameStyle>16
</nameStyle>
    </language>
</elementInfo>
' #txt
Ss0 f36 288 250 112 44 -43 -8 #rect
Ss0 f36 @|StepIcon #fIcon
Ss0 f38 expr out #txt
Ss0 f38 400 272 536 402 #arcP
Ss0 f38 1 536 272 #addKink
Ss0 f38 0 0.7876935071335653 0 0 #arcLabel
Ss0 f40 actionDecl 'ch.ivy.addon.portalkit.component.statistic.StatisticDashboardWidget.StatisticDashboardWidgetData out;
' #txt
Ss0 f40 actionTable 'out=in;
' #txt
Ss0 f40 actionCode 'import ch.ivy.addon.portalkit.statistics.StatisticChartQueryUtils;

in.caseQuery = StatisticChartQueryUtils.getQueryForSelectedItemByCaseByState(in.event, in.selectedStatisticChart);' #txt
Ss0 f40 type ch.ivy.addon.portalkit.component.statistic.StatisticDashboardWidget.StatisticDashboardWidgetData #txt
Ss0 f40 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Build case query</name>
        <nameStyle>16
</nameStyle>
    </language>
</elementInfo>
' #txt
Ss0 f40 280 482 112 44 -45 -8 #rect
Ss0 f40 @|StepIcon #fIcon
Ss0 f42 type ch.ivy.addon.portalkit.component.statistic.StatisticDashboardWidget.StatisticDashboardWidgetData #txt
Ss0 f42 processCall 'Functional Processes/Navigator:viewCaseForAnalytic(String,ch.ivyteam.ivy.workflow.query.CaseQuery)' #txt
Ss0 f42 doCall true #txt
Ss0 f42 requestActionDecl '<java.lang.String chartName,ch.ivyteam.ivy.workflow.query.CaseQuery caseQuery> param;
' #txt
Ss0 f42 requestMappingAction 'param.chartName=in.selectedStatisticChart.name;
param.caseQuery=in.caseQuery;
' #txt
Ss0 f42 responseActionDecl 'ch.ivy.addon.portalkit.component.statistic.StatisticDashboardWidget.StatisticDashboardWidgetData out;
' #txt
Ss0 f42 responseMappingAction 'out=in;
' #txt
Ss0 f42 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Navigator</name>
        <nameStyle>9,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ss0 f42 480 482 112 44 -26 -8 #rect
Ss0 f42 @|CallSubIcon #fIcon
Ss0 f43 expr out #txt
Ss0 f43 392 504 480 504 #arcP
Ss0 f6 guid 1608CEAE326E61AC #txt
Ss0 f6 type ch.ivy.addon.portalkit.component.statistic.StatisticDashboardWidget.StatisticDashboardWidgetData #txt
Ss0 f6 actionDecl 'ch.ivy.addon.portalkit.component.statistic.StatisticDashboardWidget.StatisticDashboardWidgetData out;
' #txt
Ss0 f6 actionTable 'out=in;
' #txt
Ss0 f6 actionCode 'import ch.ivy.addon.portalkit.statistics.StatisticChart;
import org.primefaces.event.ItemSelectEvent;

out.event = event as ItemSelectEvent;
String selectedChartId = out.event.getComponent().getAttributes().get("selectedChartId") as String;

for (StatisticChart chart : out.statisticChartList) {
	if (chart.id == selectedChartId) {
		out.selectedStatisticChart = chart;
		break;
	}
}' #txt
Ss0 f6 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>drilldownTaskByPriority</name>
        <nameStyle>23,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ss0 f6 107 411 26 26 -63 15 #rect
Ss0 f6 @|RichDialogProcessStartIcon #fIcon
Ss0 f9 expr out #txt
Ss0 f9 133 424 280 424 #arcP
Ss0 f44 guid 1608CECF9E359730 #txt
Ss0 f44 type ch.ivy.addon.portalkit.component.statistic.StatisticDashboardWidget.StatisticDashboardWidgetData #txt
Ss0 f44 actionDecl 'ch.ivy.addon.portalkit.component.statistic.StatisticDashboardWidget.StatisticDashboardWidgetData out;
' #txt
Ss0 f44 actionTable 'out=in;
' #txt
Ss0 f44 actionCode 'import ch.ivy.addon.portalkit.service.StatisticService;
import org.primefaces.event.ItemSelectEvent;

out.event = event as ItemSelectEvent;
String selectedDrilldownItem = StatisticService.getSelectedValueOfBarChart(in.event);
out.isDrilldownToTaskList = StatisticService.selectHourOfDay(selectedDrilldownItem);' #txt
Ss0 f44 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>onSelectDrilldownTaskByExpiry</name>
        <nameStyle>29,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ss0 f44 115 259 26 26 -86 15 #rect
Ss0 f44 @|RichDialogProcessStartIcon #fIcon
Ss0 f35 expr out #txt
Ss0 f35 133 504 280 504 #arcP
Ss0 f45 guid 1608CED0281A1901 #txt
Ss0 f45 type ch.ivy.addon.portalkit.component.statistic.StatisticDashboardWidget.StatisticDashboardWidgetData #txt
Ss0 f45 actionDecl 'ch.ivy.addon.portalkit.component.statistic.StatisticDashboardWidget.StatisticDashboardWidgetData out;
' #txt
Ss0 f45 actionTable 'out=in;
' #txt
Ss0 f45 actionCode 'import ch.ivy.addon.portalkit.statistics.StatisticChart;
import org.primefaces.event.ItemSelectEvent;

out.event = event as ItemSelectEvent;
String selectedChartId = out.event.getComponent().getAttributes().get("selectedChartId") as String;

for (StatisticChart chart : out.statisticChartList) {
	if (chart.id == selectedChartId) {
		out.selectedStatisticChart = chart;
		break;
	}
}' #txt
Ss0 f45 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>drilldownCaseByState</name>
        <nameStyle>20,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ss0 f45 107 491 26 26 -60 15 #rect
Ss0 f45 @|RichDialogProcessStartIcon #fIcon
Ss0 f32 guid 160E2C0243084AFE #txt
Ss0 f32 type ch.ivy.addon.portalkit.component.statistic.StatisticDashboardWidget.StatisticDashboardWidgetData #txt
Ss0 f32 method selectChart(ch.ivy.addon.portalkit.statistics.StatisticChart) #txt
Ss0 f32 disableUIEvents false #txt
Ss0 f32 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<ch.ivy.addon.portalkit.statistics.StatisticChart chart> param = methodEvent.getInputArguments();
' #txt
Ss0 f32 inParameterMapAction 'out.selectedStatisticChart=param.chart;
' #txt
Ss0 f32 outParameterDecl '<> result;
' #txt
Ss0 f32 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>selectChart(StatisticChart)</name>
        <nameStyle>27,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ss0 f32 115 1107 26 26 -72 15 #rect
Ss0 f32 @|RichDialogMethodStartIcon #fIcon
Ss0 f33 type ch.ivy.addon.portalkit.component.statistic.StatisticDashboardWidget.StatisticDashboardWidgetData #txt
Ss0 f33 499 1107 26 26 0 12 #rect
Ss0 f33 @|RichDialogProcessEndIcon #fIcon
Ss0 f34 expr out #txt
Ss0 f34 141 1120 499 1120 #arcP
Ss0 f39 type ch.ivy.addon.portalkit.component.statistic.StatisticDashboardWidget.StatisticDashboardWidgetData #txt
Ss0 f39 208 256 32 32 0 16 #rect
Ss0 f39 @|AlternativeIcon #fIcon
Ss0 f41 expr out #txt
Ss0 f41 141 272 208 272 #arcP
Ss0 f37 expr in #txt
Ss0 f37 outCond 'in.showTaskListImmediately || in.isDrilldownToTaskList' #txt
Ss0 f37 240 272 288 272 #arcP
Ss0 f46 type ch.ivy.addon.portalkit.component.statistic.StatisticDashboardWidget.StatisticDashboardWidgetData #txt
Ss0 f46 531 195 26 26 0 12 #rect
Ss0 f46 @|RichDialogProcessEndIcon #fIcon
Ss0 f50 guid 161BBDE048B4425D #txt
Ss0 f50 type ch.ivy.addon.portalkit.component.statistic.StatisticDashboardWidget.StatisticDashboardWidgetData #txt
Ss0 f50 actionDecl 'ch.ivy.addon.portalkit.component.statistic.StatisticDashboardWidget.StatisticDashboardWidgetData out;
' #txt
Ss0 f50 actionTable 'out=in;
' #txt
Ss0 f50 actionCode 'import ch.ivy.addon.portalkit.statistics.StatisticChart;
import ch.ivy.addon.portalkit.service.StatisticService;
import org.primefaces.event.ItemSelectEvent;

out.event = event as ItemSelectEvent;

String selectedChartId = out.event.getComponent().getAttributes().get("selectedChartId") as String;
in.selectedCaseCategory = StatisticService.getSelectedValueOfBarChart(out.event);

for (StatisticChart chart : out.statisticChartList) {
	if (chart.id == selectedChartId) {
		out.selectedStatisticChart = chart;
		break;
	}
}' #txt
Ss0 f50 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>drilldownElapsedTime</name>
        <nameStyle>20,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ss0 f50 110 563 26 26 -62 15 #rect
Ss0 f50 @|RichDialogProcessStartIcon #fIcon
Ss0 f48 type ch.ivy.addon.portalkit.component.statistic.StatisticDashboardWidget.StatisticDashboardWidgetData #txt
Ss0 f48 494 563 26 26 0 12 #rect
Ss0 f48 @|RichDialogProcessEndIcon #fIcon
Ss0 f49 expr out #txt
Ss0 f49 136 576 494 576 #arcP
Ss0 f51 guid 163DD299EBA17A9E #txt
Ss0 f51 type ch.ivy.addon.portalkit.component.statistic.StatisticDashboardWidget.StatisticDashboardWidgetData #txt
Ss0 f51 actionDecl 'ch.ivy.addon.portalkit.component.statistic.StatisticDashboardWidget.StatisticDashboardWidgetData out;
' #txt
Ss0 f51 actionTable 'out=in;
' #txt
Ss0 f51 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>drilldownTaskByExpiry</name>
        <nameStyle>21,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ss0 f51 659 259 26 26 -61 15 #rect
Ss0 f51 @|RichDialogProcessStartIcon #fIcon
Ss0 f52 type ch.ivy.addon.portalkit.component.statistic.StatisticDashboardWidget.StatisticDashboardWidgetData #txt
Ss0 f52 931 259 26 26 0 12 #rect
Ss0 f52 @|RichDialogProcessEndIcon #fIcon
Ss0 f53 actionDecl 'ch.ivy.addon.portalkit.component.statistic.StatisticDashboardWidget.StatisticDashboardWidgetData out;
' #txt
Ss0 f53 actionTable 'out=in;
' #txt
Ss0 f53 actionCode 'import ch.ivy.addon.portalkit.statistics.StatisticChartConstants;
import org.apache.commons.lang3.StringUtils;
import ch.ivy.addon.portalkit.service.StatisticService;
import ch.ivy.addon.portalkit.statistics.StatisticChart;
import org.primefaces.event.ItemSelectEvent;

String selectedChartId = out.event.getComponent().getAttributes().get("selectedChartId") as String;
in.selectedItemOfDrilldown = StatisticService.getSelectedValueOfBarChart(in.event);
if (StatisticService.selectMonthOfYear(in.selectedItemOfDrilldown))  {
	in.previousSelectedMonth = in.selectedItemOfDrilldown;
} else if (StatisticService.selectWeekOfMonth(in.selectedItemOfDrilldown))  {
	in.previousSelectedWeek = in.selectedItemOfDrilldown;
} else if (StatisticService.selectDayOfWeek(in.selectedItemOfDrilldown)) {
	in.previousSelectedDay = in.selectedItemOfDrilldown;
}

for (StatisticChart chart : out.statisticChartList) {
	if (chart.id == selectedChartId) {
		out.selectedStatisticChart = chart;
		break;
	}
}' #txt
Ss0 f53 type ch.ivy.addon.portalkit.component.statistic.StatisticDashboardWidget.StatisticDashboardWidgetData #txt
Ss0 f53 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Set data</name>
    </language>
</elementInfo>
' #txt
Ss0 f53 776 250 112 44 -22 -8 #rect
Ss0 f53 @|StepIcon #fIcon
Ss0 f54 expr out #txt
Ss0 f54 685 272 776 272 #arcP
Ss0 f55 expr out #txt
Ss0 f55 888 272 931 272 #arcP
Ss0 f56 guid 163DD43587A98693 #txt
Ss0 f56 type ch.ivy.addon.portalkit.component.statistic.StatisticDashboardWidget.StatisticDashboardWidgetData #txt
Ss0 f56 actionDecl 'ch.ivy.addon.portalkit.component.statistic.StatisticDashboardWidget.StatisticDashboardWidgetData out;
' #txt
Ss0 f56 actionTable 'out=in;
' #txt
Ss0 f56 actionCode 'import org.primefaces.event.ItemSelectEvent;
if(!out.#event is initialized) {
	out.event = event as ItemSelectEvent;
}' #txt
Ss0 f56 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>toTaskByExpiryTaskList</name>
        <nameStyle>22,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ss0 f56 115 339 26 26 -64 15 #rect
Ss0 f56 @|RichDialogProcessStartIcon #fIcon
Ss0 f57 expr out #txt
Ss0 f57 141 352 344 294 #arcP
Ss0 f57 1 344 352 #addKink
Ss0 f57 0 0.6889232284912005 0 0 #arcLabel
Ss0 f47 expr in #txt
Ss0 f47 224 256 531 208 #arcP
Ss0 f47 1 224 208 #addKink
Ss0 f47 1 0.30783775632694704 0 0 #arcLabel
Ss0 f58 guid 16421D52DC7B863C #txt
Ss0 f58 type ch.ivy.addon.portalkit.component.statistic.StatisticDashboardWidget.StatisticDashboardWidgetData #txt
Ss0 f58 actionDecl 'ch.ivy.addon.portalkit.component.statistic.StatisticDashboardWidget.StatisticDashboardWidgetData out;
' #txt
Ss0 f58 actionTable 'out=in;
' #txt
Ss0 f58 actionCode 'import org.primefaces.event.ItemSelectEvent;
out.event = event as ItemSelectEvent;' #txt
Ss0 f58 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>toTaskByExpiryTaskListFromHomePage</name>
        <nameStyle>34,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ss0 f58 451 339 26 26 -135 16 #rect
Ss0 f58 @|RichDialogProcessStartIcon #fIcon
Ss0 f59 expr out #txt
Ss0 f59 451 352 344 294 #arcP
Ss0 f59 1 344 352 #addKink
Ss0 f59 0 0.8154754678204545 0 0 #arcLabel
Ss0 f60 type ch.ivy.addon.portalkit.component.statistic.StatisticDashboardWidget.StatisticDashboardWidgetData #txt
Ss0 f60 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Compact mode?</name>
    </language>
</elementInfo>
' #txt
Ss0 f60 752 48 32 32 -49 -36 #rect
Ss0 f60 @|AlternativeIcon #fIcon
Ss0 f61 expr out #txt
Ss0 f61 669 64 752 64 #arcP
Ss0 f31 expr in #txt
Ss0 f31 outCond !in.isCompactMode #txt
Ss0 f31 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>false</name>
    </language>
</elementInfo>
' #txt
Ss0 f31 784 64 856 64 #arcP
Ss0 f31 0 0.3888888888888889 0 -10 #arcLabel
Ss0 f62 expr in #txt
Ss0 f62 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>true</name>
    </language>
</elementInfo>
' #txt
Ss0 f62 768 80 1112 77 #arcP
Ss0 f62 1 768 112 #addKink
Ss0 f62 2 1112 112 #addKink
Ss0 f62 0 0.5 15 0 #arcLabel
Ss0 f63 guid 168FFD6C153F13B5 #txt
Ss0 f63 type ch.ivy.addon.portalkit.component.statistic.StatisticDashboardWidget.StatisticDashboardWidgetData #txt
Ss0 f63 method initializeCompactMode() #txt
Ss0 f63 disableUIEvents false #txt
Ss0 f63 inParameterDecl 'ch.ivy.addon.portalkit.component.statistic.StatisticDashboardWidget.StatisticDashboardWidgetData out;
' #txt
Ss0 f63 outParameterDecl '<> result;
' #txt
Ss0 f63 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>initializeCompactMode()</name>
    </language>
</elementInfo>
' #txt
Ss0 f63 627 147 26 26 -66 15 #rect
Ss0 f63 @|RichDialogMethodStartIcon #fIcon
Ss0 f64 type ch.ivy.addon.portalkit.component.statistic.StatisticDashboardWidget.StatisticDashboardWidgetData #txt
Ss0 f64 1099 147 26 26 0 12 #rect
Ss0 f64 @|RichDialogProcessEndIcon #fIcon
Ss0 f65 actionDecl 'ch.ivy.addon.portalkit.component.statistic.StatisticDashboardWidget.StatisticDashboardWidgetData out;
' #txt
Ss0 f65 actionTable 'out=in;
' #txt
Ss0 f65 actionCode 'import ch.ivy.addon.portalkit.statistics.StatisticChart;
import ch.ivy.addon.portalkit.service.StatisticService;

if (in.statisticChartList.size() != 0) {
	StatisticService service = new StatisticService();
	service.generateChartModelForStatisticCharts(in.statisticChartList);

	in.hasStatistic = true;
} else {
	in.hasStatistic = false;
}

in.isFinishLoadCharts = true;' #txt
Ss0 f65 type ch.ivy.addon.portalkit.component.statistic.StatisticDashboardWidget.StatisticDashboardWidgetData #txt
Ss0 f65 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Generate chart models</name>
    </language>
</elementInfo>
' #txt
Ss0 f65 824 138 144 44 -63 -8 #rect
Ss0 f65 @|StepIcon #fIcon
Ss0 f66 expr out #txt
Ss0 f66 653 160 824 160 #arcP
Ss0 f67 expr out #txt
Ss0 f67 968 160 1099 160 #arcP
>Proto Ss0 .type ch.ivy.addon.portalkit.component.statistic.StatisticDashboardWidget.StatisticDashboardWidgetData #txt
>Proto Ss0 .processKind HTML_DIALOG #txt
>Proto Ss0 -8 -8 16 16 16 26 #rect
>Proto Ss0 '' #fIcon
Ss0 f3 mainOut f5 tail #connect
Ss0 f5 head f4 mainIn #connect
Ss0 f11 mainOut f0 tail #connect
Ss0 f0 head f14 mainIn #connect
Ss0 f19 mainOut f20 tail #connect
Ss0 f20 head f18 mainIn #connect
Ss0 f13 mainOut f21 tail #connect
Ss0 f21 head f19 mainIn #connect
Ss0 f15 mainOut f22 tail #connect
Ss0 f22 head f19 mainIn #connect
Ss0 f16 mainOut f23 tail #connect
Ss0 f23 head f19 mainIn #connect
Ss0 f17 mainOut f24 tail #connect
Ss0 f24 head f19 mainIn #connect
Ss0 f25 mainOut f27 tail #connect
Ss0 f27 head f26 mainIn #connect
Ss0 f26 mainOut f29 tail #connect
Ss0 f29 head f28 mainIn #connect
Ss0 f30 mainOut f12 tail #connect
Ss0 f12 head f2 mainIn #connect
Ss0 f8 mainOut f10 tail #connect
Ss0 f10 head f7 mainIn #connect
Ss0 f36 mainOut f38 tail #connect
Ss0 f38 head f7 mainIn #connect
Ss0 f40 mainOut f43 tail #connect
Ss0 f43 head f42 mainIn #connect
Ss0 f6 mainOut f9 tail #connect
Ss0 f9 head f8 mainIn #connect
Ss0 f45 mainOut f35 tail #connect
Ss0 f35 head f40 mainIn #connect
Ss0 f32 mainOut f34 tail #connect
Ss0 f34 head f33 mainIn #connect
Ss0 f44 mainOut f41 tail #connect
Ss0 f41 head f39 in #connect
Ss0 f39 out f37 tail #connect
Ss0 f37 head f36 mainIn #connect
Ss0 f50 mainOut f49 tail #connect
Ss0 f49 head f48 mainIn #connect
Ss0 f51 mainOut f54 tail #connect
Ss0 f54 head f53 mainIn #connect
Ss0 f53 mainOut f55 tail #connect
Ss0 f55 head f52 mainIn #connect
Ss0 f56 mainOut f57 tail #connect
Ss0 f57 head f36 mainIn #connect
Ss0 f39 out f47 tail #connect
Ss0 f47 head f46 mainIn #connect
Ss0 f58 mainOut f59 tail #connect
Ss0 f59 head f36 mainIn #connect
Ss0 f1 mainOut f61 tail #connect
Ss0 f61 head f60 in #connect
Ss0 f60 out f31 tail #connect
Ss0 f31 head f30 mainIn #connect
Ss0 f60 out f62 tail #connect
Ss0 f62 head f2 mainIn #connect
Ss0 f63 mainOut f66 tail #connect
Ss0 f66 head f65 mainIn #connect
Ss0 f65 mainOut f67 tail #connect
Ss0 f67 head f64 mainIn #connect
