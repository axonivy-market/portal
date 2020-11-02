[Ivy]
163D2D12302195CE 9.2.0 #module
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
import java.util.ArrayList;
import ch.ivy.addon.portalkit.dto.DisplayName;
import java.util.HashMap;
import java.util.Map;
import ch.ivy.addon.portalkit.ivydata.service.impl.LanguageService;
import ch.ivy.addon.portalkit.statistics.StatisticFilter;
import ch.ivy.addon.portalkit.enums.StatisticChartType;
import ch.ivy.addon.portalkit.statistics.StatisticChart;
import ch.ivy.addon.portalkit.service.StatisticService;

StatisticService service = new StatisticService();
StatisticFilter statisticFilter = new StatisticFilter();
String chartName1 = "My default chart 1";
String chartName2 = "My default chart 2";
long sessionUserId = ivy.session.getSessionUser().getId();

List supportedLanguages = LanguageService.newInstance().findUserLanguages().ivyLanguage.supportedLanguages;
boolean existedChart1 = false;
boolean existedChart2 = false;

for (String language : supportedLanguages) {
	if (service.checkDefaultStatisticChartNameExisted(ivy.session.getSessionUser().getId(), chartName1, language)) {
		existedChart1 = true;
		in.defaultCharts.add(service.findStatisticChartByUserIdAndChartNameAndLanguage(ivy.session.getSessionUser().getId(), chartName1, language));
	}
	if (service.checkDefaultStatisticChartNameExisted(ivy.session.getSessionUser().getId(), chartName2, language)) {
		existedChart2 = true;
		in.defaultCharts.add(service.findStatisticChartByUserIdAndChartNameAndLanguage(ivy.session.getSessionUser().getId(), chartName2, language));
	} 
}

if (!existedChart1) {
	List<DisplayName> chartNames1 = new ArrayList();
	for (String language : supportedLanguages) {
		DisplayName newName = new DisplayName();
		newName.locale = Locale.forLanguageTag(language);
		newName.value = chartName1;	
		chartNames1.add(newName);
	}
	StatisticChart newChart1 = service.createStatisticChart(statisticFilter, chartNames1, StatisticChartType.TASK_BY_EXPIRY, sessionUserId, true);
  in.defaultCharts.add(newChart1);		
}

if (!existedChart2) {
	List<DisplayName> chartNames2 = new ArrayList();
	for (String language : supportedLanguages) {
		DisplayName newName = new DisplayName();
		newName.locale = Locale.forLanguageTag(language);
		newName.value = chartName1;	
		chartNames2.add(newName);
	}
	StatisticChart newChart2 = service.createStatisticChart(statisticFilter, chartNames2, StatisticChartType.CASES_BY_STATE, sessionUserId, true);
  in.defaultCharts.add(newChart2);
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
        <name>This process will override Default Chart process of Portal Kit and create 2 default charts</name>
        <nameStyle>90
</nameStyle>
    </language>
</elementInfo>
' #txt
Dt0 f5 224 105 480 30 -237 -8 #rect
Dt0 f5 @|IBIcon #fIcon
Dt0 f4 96 79 96 138 #arcP
>Proto Dt0 .type _com.axonivy.portal.developerexamples.DefaultChartOverrideData #txt
>Proto Dt0 .processKind CALLABLE_SUB #txt
>Proto Dt0 0 0 32 24 18 0 #rect
>Proto Dt0 @|BIcon #fIcon
Dt0 f3 mainOut f2 tail #connect
Dt0 f2 head f1 mainIn #connect
Dt0 f0 mainOut f4 tail #connect
Dt0 f4 head f3 mainIn #connect
