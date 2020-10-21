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
Dt0 f3 actionCode 'import java.util.Locale;
import java.util.Arrays;
import ch.ivy.addon.portalkit.ivydata.service.impl.LanguageService;
import ch.ivy.addon.portalkit.ivydata.bo.IvyLanguage;
import java.util.ArrayList;
import ch.ivy.addon.portalkit.dto.DisplayName;

import ch.ivy.addon.portalkit.statistics.StatisticFilter;
import ch.ivy.addon.portalkit.enums.StatisticChartType;
import ch.ivy.addon.portalkit.statistics.StatisticChart;
import ch.ivy.addon.portalkit.service.StatisticService;

StatisticService service = new StatisticService();
String chartName = "Tasks by Priority";
StatisticChartType chartType = StatisticChartType.TASK_BY_PRIORITY;

List<DisplayName> chartNames = new ArrayList();
IvyLanguage ivyLanguage = LanguageService.newInstance().findUserLanguages(ivy.session.getSessionUserName(), Arrays.asList(ivy.wf.getApplication().getName())).ivyLanguages.get(0);

boolean isExistedDefaultChart = false;
for(String language : ivyLanguage.supportedLanguages) {
	DisplayName name = new DisplayName();
	name.locale = Locale.forLanguageTag(language);
	name.value = chartName;
	chartNames.add(name);
	
	if (service.checkDefaultStatisticChartNameExisted(ivy.session.getSessionUser().getId(), chartName, language) && !isExistedDefaultChart) {
 	 in.defaultCharts.add(service.findStatisticChartByUserIdAndChartNameAndLanguage(ivy.session.getSessionUser().getId(), chartName, ivyLanguage.userLanguage));
 	 isExistedDefaultChart = true;
	}
}

if (!isExistedDefaultChart) {
  StatisticFilter statisticFilter = new StatisticFilter();
  StatisticChart newChart = service.createStatisticChart(statisticFilter, chartNames, chartType, ivy.session.getSessionUser().getId(), true);
  in.defaultCharts.add(newChart);
}' #txt
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

import java.util.Locale;&#13;
import java.util.Arrays;&#13;
import ch.ivy.addon.portalkit.ivydata.service.impl.LanguageService;&#13;
import ch.ivy.addon.portalkit.ivydata.bo.IvyLanguage;&#13;
import java.util.ArrayList;&#13;
import ch.ivy.addon.portalkit.dto.DisplayName;&#13;
&#13;
import ch.ivy.addon.portalkit.statistics.StatisticFilter;&#13;
import ch.ivy.addon.portalkit.enums.StatisticChartType;&#13;
import ch.ivy.addon.portalkit.statistics.StatisticChart;&#13;
import ch.ivy.addon.portalkit.service.StatisticService;&#13;
&#13;
StatisticService service = new StatisticService();&#13;
String chartName = "Tasks by Priority";&#13;
StatisticChartType chartType = StatisticChartType.TASK_BY_PRIORITY;&#13;
&#13;
List&lt;DisplayName&gt; chartNames = new ArrayList();&#13;
IvyLanguage ivyLanguage = LanguageService.newInstance().findUserLanguages(ivy.session.getSessionUserName(), Arrays.asList(ivy.wf.getApplication().getName())).ivyLanguages.get(0);&#13;
&#13;
boolean isExistedDefaultChart = false;&#13;
for(String language : ivyLanguage.supportedLanguages) {&#13;
  DisplayName name = new DisplayName();&#13;
  name.locale = Locale.forLanguageTag(language);&#13;
  name.value = chartName;&#13;
  chartNames.add(name);&#13;
	&#13;
  if (service.checkDefaultStatisticChartNameExisted(ivy.session.getSessionUser().getId(), chartName, language) &amp;&amp; !isExistedDefaultChart) {&#13;
    in.defaultCharts.add(service.findStatisticChartByUserIdAndChartNameAndLanguage(ivy.session.getSessionUser().getId(), chartName, ivyLanguage.userLanguage));&#13;
    isExistedDefaultChart = true;&#13;
  }&#13;
}&#13;
&#13;
if (!isExistedDefaultChart) {&#13;
  StatisticFilter statisticFilter = new StatisticFilter();&#13;
  StatisticChart newChart = service.createStatisticChart(statisticFilter, chartNames, chartType, ivy.session.getSessionUser().getId(), true);&#13;
  in.defaultCharts.add(newChart);&#13;
}</name>
        <nameStyle>1795,5
</nameStyle>
    </language>
</elementInfo>
' #txt
Dt0 f5 264 42 1040 636 -514 -312 #rect
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
