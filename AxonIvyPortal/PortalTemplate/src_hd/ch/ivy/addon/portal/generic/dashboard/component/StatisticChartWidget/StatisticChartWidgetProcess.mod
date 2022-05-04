[Ivy]
17FF80435B6470D3 9.4.6 #module
>Proto >Proto Collection #zClass
Ss0 StatisticChartWidgetProcess Big #zClass
Ss0 RD #cInfo
Ss0 #process
Ss0 @AnnotationInP-0n ai ai #zField
Ss0 @TextInP .type .type #zField
Ss0 @TextInP .processKind .processKind #zField
Ss0 @TextInP .colors .colors #zField
Ss0 @TextInP .xml .xml #zField
Ss0 @TextInP .responsibility .responsibility #zField
Ss0 @TextInP color color #zField
Ss0 @UdInit f0 '' #zField
Ss0 @UdProcessEnd f1 '' #zField
Ss0 @PushWFArc f2 '' #zField
Ss0 @UdMethod f6 '' #zField
Ss0 @Alternative f39 '' #zField
Ss0 @CallSub f7 '' #zField
Ss0 @UdMethod f83 '' #zField
Ss0 @UdProcessEnd f46 '' #zField
Ss0 @CallSub f42 '' #zField
Ss0 @GridStep f36 '' #zField
Ss0 @UdMethod f41 '' #zField
Ss0 @UdMethod f35 '' #zField
Ss0 @PushWFArc f84 '' #zField
Ss0 @PushWFArc f40 '' #zField
Ss0 @PushWFArc f38 '' #zField
Ss0 @PushWFArc f43 '' #zField
Ss0 @PushWFArc f37 '' #zField
Ss0 @PushWFArc f8 '' #zField
Ss0 @PushWFArc f47 '' #zField
>Proto Ss0 Ss0 StatisticChartWidgetProcess #zField
Ss0 f0 guid 17FF8043641EBB62 #txt
Ss0 f0 method start() #txt
Ss0 f0 inParameterDecl '<> param;' #txt
Ss0 f0 outParameterDecl '<> result;' #txt
Ss0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start()</name>
    </language>
</elementInfo>
' #txt
Ss0 f0 83 51 26 26 -16 15 #rect
Ss0 f1 211 51 26 26 0 12 #rect
Ss0 f2 109 64 211 64 #arcP
Ss0 f6 guid 17FFE85588CFA06E #txt
Ss0 f6 method drilldownCaseByState(ch.ivy.addon.portalkit.statistics.StatisticChart,ch.ivyteam.ivy.workflow.query.CaseQuery) #txt
Ss0 f6 inParameterDecl '<ch.ivy.addon.portalkit.statistics.StatisticChart selectedStatisticChart,ch.ivyteam.ivy.workflow.query.CaseQuery caseQuery> param;' #txt
Ss0 f6 inParameterMapAction 'out.caseQuery=param.caseQuery;
out.selectedStatisticChart=param.selectedStatisticChart;
' #txt
Ss0 f6 outParameterDecl '<> result;' #txt
Ss0 f6 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>drilldownCaseByState(StatisticChart,CaseQuery)</name>
    </language>
</elementInfo>
' #txt
Ss0 f6 83 403 26 26 -64 14 #rect
Ss0 f39 184 176 32 32 0 16 #rect
Ss0 f7 processCall 'Functional Processes/Navigator:viewTaskForAnalytic(String,ch.ivyteam.ivy.workflow.query.TaskQuery)' #txt
Ss0 f7 requestActionDecl '<String chartName,ch.ivyteam.ivy.workflow.query.TaskQuery taskQuery> param;' #txt
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
    </language>
</elementInfo>
' #txt
Ss0 f7 427 322 112 44 -26 -8 #rect
Ss0 f83 guid 17FFE85588DA0A15 #txt
Ss0 f83 method drilldownTaskByPriority(String,ch.ivyteam.ivy.workflow.query.TaskQuery) #txt
Ss0 f83 inParameterDecl '<String taskListName,ch.ivyteam.ivy.workflow.query.TaskQuery taskQuery> param;' #txt
Ss0 f83 inParameterMapAction 'out.taskListName=param.taskListName;
out.taskQuery=param.taskQuery;
' #txt
Ss0 f83 outParameterDecl '<> result;' #txt
Ss0 f83 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>drilldownTaskByPriority(String,TaskQuery)</name>
    </language>
</elementInfo>
' #txt
Ss0 f83 83 331 26 26 -64 12 #rect
Ss0 f46 470 115 26 26 0 12 #rect
Ss0 f42 processCall 'Functional Processes/Navigator:viewCaseForAnalytic(String,ch.ivyteam.ivy.workflow.query.CaseQuery)' #txt
Ss0 f42 requestActionDecl '<String chartName,ch.ivyteam.ivy.workflow.query.CaseQuery caseQuery> param;' #txt
Ss0 f42 requestMappingAction 'param.chartName=in.chartNameOfCurrentLanguage;
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
    </language>
</elementInfo>
' #txt
Ss0 f42 264 394 112 44 -26 -8 #rect
Ss0 f36 actionTable 'out=in;
' #txt
Ss0 f36 actionCode 'import ch.ivy.addon.portalkit.service.StatisticService;
import ch.ivy.addon.portalkit.ivydata.service.impl.LanguageService;
import ch.ivy.addon.portalkit.ivydata.service.impl.LanguageService;
import org.apache.commons.lang3.StringUtils;
import ch.ivy.addon.portalkit.datamodel.TaskLazyDataModel;
import ch.ivy.addon.portalkit.service.StatisticService;
import ch.ivy.addon.portalkit.statistics.StatisticChartQueryUtils;
import ch.ivy.addon.portalkit.statistics.StatisticChart;

if (StatisticService.selectMonthOfYear(in.selectedItemOfDrilldown))  {
	in.previousSelectedMonth = in.selectedItemOfDrilldown;
} else if (StatisticService.selectWeekOfMonth(in.selectedItemOfDrilldown))  {
	in.previousSelectedWeek = in.selectedItemOfDrilldown;
} else if (StatisticService.selectDayOfWeek(in.selectedItemOfDrilldown)) {
	in.previousSelectedDay = in.selectedItemOfDrilldown;
}

StatisticService service = StatisticService.getInstance();
in.chartNameOfCurrentLanguage = service.getDisplayNameInUserLanguageForChart(in.selectedStatisticChart).getValue();

in.taskListName = in.chartNameOfCurrentLanguage + " - " + in.selectedItemOfDrilldown;
if (StatisticService.selectHourOfDay(in.selectedItemOfDrilldown)) {
	in.taskListName += " " + ivy.cms.co("/ch.ivy.addon.portalkit.ui.jsf/statistic/chart/taskByExpiry/hour");
}
in.selectedItemOfDrilldown = StringUtils.EMPTY;
' #txt
Ss0 f36 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Build task query</name>
    </language>
</elementInfo>
' #txt
Ss0 f36 264 170 112 44 -43 -8 #rect
Ss0 f41 guid 17FFE85588D604E1 #txt
Ss0 f41 method toTaskByExpiryTaskList(String,ch.ivy.addon.portalkit.statistics.StatisticChart,ch.ivyteam.ivy.workflow.query.TaskQuery) #txt
Ss0 f41 inParameterDecl '<String selectedItemOfDrilldown,ch.ivy.addon.portalkit.statistics.StatisticChart selectedStatisticChart,ch.ivyteam.ivy.workflow.query.TaskQuery taskQuery> param;' #txt
Ss0 f41 inParameterMapAction 'out.selectedItemOfDrilldown=param.selectedItemOfDrilldown;
out.selectedStatisticChart=param.selectedStatisticChart;
out.taskQuery=param.taskQuery;
' #txt
Ss0 f41 outParameterDecl '<> result;' #txt
Ss0 f41 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>toTaskByExpiryTaskList(String,StatisticChart,TaskQuery)</name>
    </language>
</elementInfo>
' #txt
Ss0 f41 83 259 26 26 -66 11 #rect
Ss0 f35 guid 17FFE85588DC1FA9 #txt
Ss0 f35 method onSelectDrilldownTaskByExpiry(Boolean,String,ch.ivy.addon.portalkit.statistics.StatisticChart,ch.ivyteam.ivy.workflow.query.TaskQuery) #txt
Ss0 f35 inParameterDecl '<Boolean isDrilldownToTaskList,String selectedItemOfDrilldown,ch.ivy.addon.portalkit.statistics.StatisticChart selectedStatisticChart,ch.ivyteam.ivy.workflow.query.TaskQuery taskQuery> param;' #txt
Ss0 f35 inParameterMapAction 'out.isDrilldownToTaskList=param.isDrilldownToTaskList;
out.selectedItemOfDrilldown=param.selectedItemOfDrilldown;
out.selectedStatisticChart=param.selectedStatisticChart;
out.taskQuery=param.taskQuery;
' #txt
Ss0 f35 outParameterDecl '<> result;' #txt
Ss0 f35 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>onSelectDrilldownTaskByExpiry(Boolean,String,StatisticChart,TaskQuery)</name>
    </language>
</elementInfo>
' #txt
Ss0 f35 83 179 26 26 -63 28 #rect
Ss0 f84 109 344 427 344 #arcP
Ss0 f84 0 0.5039194664928536 0 0 #arcLabel
Ss0 f40 109 192 184 192 #arcP
Ss0 f38 expr out #txt
Ss0 f38 376 192 483 322 #arcP
Ss0 f38 1 483 192 #addKink
Ss0 f38 0 0.7876935071335653 0 0 #arcLabel
Ss0 f43 109 272 320 214 #arcP
Ss0 f43 1 320 272 #addKink
Ss0 f43 0 0.6935149083325024 0 0 #arcLabel
Ss0 f37 expr in #txt
Ss0 f37 outCond 'in.showTaskListImmediately || in.isDrilldownToTaskList' #txt
Ss0 f37 216 192 264 192 #arcP
Ss0 f8 109 416 264 416 #arcP
Ss0 f8 0 0.5123943661971831 0 0 #arcLabel
Ss0 f47 expr in #txt
Ss0 f47 200 176 470 128 #arcP
Ss0 f47 1 200 128 #addKink
Ss0 f47 1 0.30783775632694704 0 0 #arcLabel
>Proto Ss0 .type ch.ivy.addon.portal.generic.dashboard.component.StatisticChartWidget.StatisticChartWidgetData #txt
>Proto Ss0 .processKind HTML_DIALOG #txt
>Proto Ss0 -8 -8 16 16 16 26 #rect
Ss0 f0 mainOut f2 tail #connect
Ss0 f2 head f1 mainIn #connect
Ss0 f36 mainOut f38 tail #connect
Ss0 f38 head f7 mainIn #connect
Ss0 f39 out f37 tail #connect
Ss0 f37 head f36 mainIn #connect
Ss0 f39 out f47 tail #connect
Ss0 f47 head f46 mainIn #connect
Ss0 f83 mainOut f84 tail #connect
Ss0 f84 head f7 mainIn #connect
Ss0 f6 mainOut f8 tail #connect
Ss0 f8 head f42 mainIn #connect
Ss0 f35 mainOut f40 tail #connect
Ss0 f40 head f39 in #connect
Ss0 f41 mainOut f43 tail #connect
Ss0 f43 head f36 mainIn #connect
