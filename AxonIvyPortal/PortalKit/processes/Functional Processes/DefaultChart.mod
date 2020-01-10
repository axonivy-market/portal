[Ivy]
163AA0C4B3771751 7.5.0 #module
>Proto >Proto Collection #zClass
Dt0 DefaultChart Big #zClass
Dt0 B #cInfo
Dt0 #process
Dt0 @TextInP .type .type #zField
Dt0 @TextInP .processKind .processKind #zField
Dt0 @AnnotationInP-0n ai ai #zField
Dt0 @MessageFlowInP-0n messageIn messageIn #zField
Dt0 @MessageFlowOutP-0n messageOut messageOut #zField
Dt0 @TextInP .xml .xml #zField
Dt0 @TextInP .responsibility .responsibility #zField
Dt0 @StartSub f0 '' #zField
Dt0 @EndSub f1 '' #zField
Dt0 @GridStep f3 '' #zField
Dt0 @PushWFArc f2 '' #zField
Dt0 @InfoButton f5 '' #zField
Dt0 @PushWFArc f4 '' #zField
>Proto Dt0 Dt0 DefaultChart #zField
Dt0 f0 inParamDecl '<> param;' #txt
Dt0 f0 outParamDecl '<java.util.List<ch.ivy.addon.portalkit.statistics.StatisticChart> charts> result;' #txt
Dt0 f0 outParamTable 'result.charts=in.defaultCharts;
' #txt
Dt0 f0 callSignature createDefaultChart() #txt
Dt0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>createDefaultChart()</name>
        <nameStyle>20,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Dt0 f0 81 49 30 30 23 -6 #rect
Dt0 f0 @|StartSubIcon #fIcon
Dt0 f1 81 241 30 30 0 15 #rect
Dt0 f1 @|EndSubIcon #fIcon
Dt0 f3 actionTable 'out=in;
' #txt
Dt0 f3 actionCode 'import ch.ivy.addon.portalkit.statistics.StatisticFilter;
import ch.ivy.addon.portalkit.enums.StatisticChartType;
import ch.ivy.addon.portalkit.statistics.StatisticChart;
import ch.ivy.addon.portalkit.service.StatisticService;

StatisticService service = new StatisticService();
String chartName = "Tasks by Priority";
StatisticChartType chartType = StatisticChartType.TASK_BY_PRIORITY;

if (!service.checkDefaultStatisticChartNameExisted(ivy.session.getSessionUser().getId(), chartName)) {
  StatisticFilter statisticFilter = new StatisticFilter();
  StatisticChart newChart = service.createStatisticChart(statisticFilter, chartName, chartType, ivy.session.getSessionUser().getId(), true);
  in.defaultCharts.add(newChart);
}
' #txt
Dt0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>create default chart</name>
    </language>
</elementInfo>
' #txt
Dt0 f3 40 138 112 44 -52 -8 #rect
Dt0 f3 @|StepIcon #fIcon
Dt0 f2 expr out #txt
Dt0 f2 96 182 96 241 #arcP
Dt0 f5 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>// FOLLOW THIS INSTRUCTION TO CREATE DEFAULT CHART

import ch.ivy.addon.portalkit.statistics.StatisticFilter;
import ch.ivy.addon.portalkit.enums.StatisticChartType;
import ch.ivy.addon.portalkit.statistics.StatisticChart;
import ch.ivy.addon.portalkit.service.StatisticService;

StatisticService service = new StatisticService();
String chartName = "My default chart";
StatisticChartType chartType = StatisticChartType.TASK_BY_PRIORITY;
StatisticFilter statisticFilter = new StatisticFilter();

if (!service.checkDefaultStatisticChartNameExisted(ivy.session.getSessionUser().getId(), chartName)) {
  StatisticChart newChart = service.createStatisticChart(statisticFilter, chartName, chartType, ivy.session.getSessionUser().getId(), true);
  in.defaultCharts.add(newChart);		
}</name>
        <nameStyle>777,5
</nameStyle>
    </language>
</elementInfo>
' #txt
Dt0 f5 288 34 752 268 -368 -128 #rect
Dt0 f5 @|IBIcon #fIcon
Dt0 f4 96 79 96 138 #arcP
>Proto Dt0 .type ch.ivy.addon.portalkit.DefaultChartData #txt
>Proto Dt0 .processKind CALLABLE_SUB #txt
>Proto Dt0 0 0 32 24 18 0 #rect
>Proto Dt0 @|BIcon #fIcon
Dt0 f3 mainOut f2 tail #connect
Dt0 f2 head f1 mainIn #connect
Dt0 f0 mainOut f4 tail #connect
Dt0 f4 head f3 mainIn #connect
