[Ivy]
15FFC669C88F7E0B 3.20 #module
>Proto >Proto Collection #zClass
Cs0 ChartCreationWidgetProcess Big #zClass
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
Cs0 @RichDialogProcessStart f3 '' #zField
Cs0 @RichDialogEnd f4 '' #zField
Cs0 @PushWFArc f5 '' #zField
Cs0 @RichDialogMethodStart f35 '' #zField
Cs0 @RichDialogMethodStart f40 '' #zField
Cs0 @RichDialogMethodStart f42 '' #zField
Cs0 @RichDialogProcessEnd f43 '' #zField
Cs0 @PushWFArc f45 '' #zField
Cs0 @PushWFArc f46 '' #zField
Cs0 @RichDialogMethodStart f6 '' #zField
Cs0 @GridStep f15 '' #zField
Cs0 @PushWFArc f2 '' #zField
Cs0 @RichDialogProcessEnd f7 '' #zField
Cs0 @PushWFArc f8 '' #zField
Cs0 @PushWFArc f9 '' #zField
Cs0 @RichDialogProcessEnd f11 '' #zField
Cs0 @PushWFArc f44 '' #zField
Cs0 @RichDialogMethodStart f41 '' #zField
Cs0 @GridStep f13 '' #zField
Cs0 @PushWFArc f12 '' #zField
Cs0 @RichDialogMethodStart f17 '' #zField
Cs0 @PushWFArc f18 '' #zField
Cs0 @Alternative f21 '' #zField
Cs0 @PushWFArc f16 '' #zField
Cs0 @PushWFArc f19 '' #zField
Cs0 @PushWFArc f20 '' #zField
Cs0 @PushWFArc f14 '' #zField
Cs0 @RichDialogProcessStart f10 '' #zField
Cs0 @RichDialogMethodStart f22 '' #zField
Cs0 @PushWFArc f23 '' #zField
Cs0 @RichDialogMethodStart f24 '' #zField
Cs0 @PushWFArc f25 '' #zField
>Proto Cs0 Cs0 ChartCreationWidgetProcess #zField
Cs0 f0 guid 15FFC669CAD8BE32 #txt
Cs0 f0 type ch.ivy.addon.portalkit.component.statistic.ChartCreationWidget.ChartCreationWidgetData #txt
Cs0 f0 method start() #txt
Cs0 f0 disableUIEvents true #txt
Cs0 f0 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<> param = methodEvent.getInputArguments();
' #txt
Cs0 f0 outParameterDecl '<> result;
' #txt
Cs0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start()</name>
    </language>
</elementInfo>
' #txt
Cs0 f0 115 51 26 26 -16 15 #rect
Cs0 f0 @|RichDialogInitStartIcon #fIcon
Cs0 f1 type ch.ivy.addon.portalkit.component.statistic.ChartCreationWidget.ChartCreationWidgetData #txt
Cs0 f1 435 51 26 26 0 12 #rect
Cs0 f1 @|RichDialogProcessEndIcon #fIcon
Cs0 f3 guid 15FFC669CBAE4804 #txt
Cs0 f3 type ch.ivy.addon.portalkit.component.statistic.ChartCreationWidget.ChartCreationWidgetData #txt
Cs0 f3 actionDecl 'ch.ivy.addon.portalkit.component.statistic.ChartCreationWidget.ChartCreationWidgetData out;
' #txt
Cs0 f3 actionTable 'out=in;
' #txt
Cs0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>close</name>
    </language>
</elementInfo>
' #txt
Cs0 f3 115 147 26 26 -15 12 #rect
Cs0 f3 @|RichDialogProcessStartIcon #fIcon
Cs0 f4 type ch.ivy.addon.portalkit.component.statistic.ChartCreationWidget.ChartCreationWidgetData #txt
Cs0 f4 guid 15FFC669CBAFD69D #txt
Cs0 f4 435 147 26 26 0 12 #rect
Cs0 f4 @|RichDialogEndIcon #fIcon
Cs0 f5 expr out #txt
Cs0 f5 141 160 435 160 #arcP
Cs0 f35 guid 1601F664C9C78C71 #txt
Cs0 f35 type ch.ivy.addon.portalkit.component.statistic.ChartCreationWidget.ChartCreationWidgetData #txt
Cs0 f35 method createStatisticChart(java.util.List<ch.ivy.addon.portalkit.statistics.StatisticChart>) #txt
Cs0 f35 disableUIEvents false #txt
Cs0 f35 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<java.util.List<ch.ivy.addon.portalkit.statistics.StatisticChart> statisticChartList> param = methodEvent.getInputArguments();
' #txt
Cs0 f35 inParameterMapAction 'out.statisticChartList=param.statisticChartList;
' #txt
Cs0 f35 inActionCode 'import org.apache.commons.lang3.StringUtils;
import ch.ivy.addon.portalkit.statistics.StatisticChart;
import javax.faces.context.FacesContext;
import javax.faces.application.FacesMessage;
import ch.ivy.addon.portalkit.service.StatisticService;
import java.util.Arrays;

out.isChartNameExisted = false;
StatisticService service = new StatisticService();
out.chartName = out.chartName.trim();
if (service.checkStatisticChartNameExisted(ivy.session.getSessionUser().getId(), out.chartName)) {
	FacesMessage message = new FacesMessage( FacesMessage.SEVERITY_ERROR, ivy.cms.co("/ch.ivy.addon.portalkit.ui.jsf/statistic/validationErrors/nameIsExisted"), "");
	FacesContext.getCurrentInstance().addMessage("chart-name-input", message);
	FacesContext.getCurrentInstance().validationFailed();
	out.isChartNameExisted = true;
} else {
	StatisticChart newChart = service.createStatisticChart(out.statisticFilter, out.chartName, out.chartType, ivy.session.getSessionUser().getId());
	param.statisticChartList.add(newChart);
	
	String growlTitle = ivy.cms.co("/ch.ivy.addon.portalkit.ui.jsf/statistic/chart/chartCreationSuccessTitle");
	String growlDetail = ivy.cms.co("/ch.ivy.addon.portalkit.ui.jsf/statistic/chart/chartCreationSuccessDetailMsg", Arrays.asList(out.chartName));
	FacesMessage message = new FacesMessage( FacesMessage.SEVERITY_INFO, growlTitle, growlDetail);
	FacesContext.getCurrentInstance().addMessage(null, message);
	
}' #txt
Cs0 f35 outParameterDecl '<> result;
' #txt
Cs0 f35 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>createStatisticChart(List&lt;StatisticChart&gt;)</name>
        <nameStyle>42,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f35 115 859 26 26 -111 15 #rect
Cs0 f35 @|RichDialogMethodStartIcon #fIcon
Cs0 f40 guid 1601F9BADD2EC1C0 #txt
Cs0 f40 type ch.ivy.addon.portalkit.component.statistic.ChartCreationWidget.ChartCreationWidgetData #txt
Cs0 f40 method updateBeforeAddCaseByState() #txt
Cs0 f40 disableUIEvents false #txt
Cs0 f40 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<> param = methodEvent.getInputArguments();
' #txt
Cs0 f40 inActionCode 'import ch.ivy.addon.portalkit.enums.StatisticChartType;

out.chartType = StatisticChartType.CASES_BY_STATE;' #txt
Cs0 f40 outParameterDecl '<> result;
' #txt
Cs0 f40 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>updateBeforeAddCaseByState()</name>
        <nameStyle>28,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f40 115 435 26 26 -87 15 #rect
Cs0 f40 @|RichDialogMethodStartIcon #fIcon
Cs0 f42 guid 1601F9BBC3E25AD8 #txt
Cs0 f42 type ch.ivy.addon.portalkit.component.statistic.ChartCreationWidget.ChartCreationWidgetData #txt
Cs0 f42 method updateBeforeAddTaskByExpiry() #txt
Cs0 f42 disableUIEvents false #txt
Cs0 f42 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<> param = methodEvent.getInputArguments();
' #txt
Cs0 f42 inActionCode 'import ch.ivy.addon.portalkit.enums.StatisticChartType;

out.chartType = StatisticChartType.TASK_BY_EXPIRY;' #txt
Cs0 f42 outParameterDecl '<> result;
' #txt
Cs0 f42 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>updateBeforeAddTaskByExpiry()</name>
        <nameStyle>29,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f42 115 531 26 26 -87 15 #rect
Cs0 f42 @|RichDialogMethodStartIcon #fIcon
Cs0 f43 type ch.ivy.addon.portalkit.component.statistic.ChartCreationWidget.ChartCreationWidgetData #txt
Cs0 f43 435 483 26 26 0 12 #rect
Cs0 f43 @|RichDialogProcessEndIcon #fIcon
Cs0 f45 expr out #txt
Cs0 f45 141 448 448 483 #arcP
Cs0 f45 1 448 448 #addKink
Cs0 f45 0 0.577599710494189 0 0 #arcLabel
Cs0 f46 expr out #txt
Cs0 f46 141 544 448 509 #arcP
Cs0 f46 1 448 544 #addKink
Cs0 f46 0 0.7534182567993608 0 0 #arcLabel
Cs0 f6 guid 160358C7FBCD2AE2 #txt
Cs0 f6 type ch.ivy.addon.portalkit.component.statistic.ChartCreationWidget.ChartCreationWidgetData #txt
Cs0 f6 method initialize() #txt
Cs0 f6 disableUIEvents false #txt
Cs0 f6 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<> param = methodEvent.getInputArguments();
' #txt
Cs0 f6 outParameterDecl '<> result;
' #txt
Cs0 f6 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>initialize()</name>
        <nameStyle>12,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f6 115 243 26 26 -26 15 #rect
Cs0 f6 @|RichDialogMethodStartIcon #fIcon
Cs0 f15 actionDecl 'ch.ivy.addon.portalkit.component.statistic.ChartCreationWidget.ChartCreationWidgetData out;
' #txt
Cs0 f15 actionTable 'out=in;
' #txt
Cs0 f15 actionCode 'import ch.ivy.addon.portalkit.statistics.StatisticFilter;

in.statisticFilter = new StatisticFilter();' #txt
Cs0 f15 type ch.ivy.addon.portalkit.component.statistic.ChartCreationWidget.ChartCreationWidgetData #txt
Cs0 f15 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Initialize</name>
        <nameStyle>10
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f15 222 234 112 44 -22 -8 #rect
Cs0 f15 @|StepIcon #fIcon
Cs0 f2 expr out #txt
Cs0 f2 141 256 222 256 #arcP
Cs0 f7 type ch.ivy.addon.portalkit.component.statistic.ChartCreationWidget.ChartCreationWidgetData #txt
Cs0 f7 435 243 26 26 0 12 #rect
Cs0 f7 @|RichDialogProcessEndIcon #fIcon
Cs0 f8 expr out #txt
Cs0 f8 334 256 435 256 #arcP
Cs0 f9 expr out #txt
Cs0 f9 141 64 435 64 #arcP
Cs0 f11 type ch.ivy.addon.portalkit.component.statistic.ChartCreationWidget.ChartCreationWidgetData #txt
Cs0 f11 435 955 26 26 0 12 #rect
Cs0 f11 @|RichDialogProcessEndIcon #fIcon
Cs0 f44 expr out #txt
Cs0 f44 141 352 448 483 #arcP
Cs0 f44 1 448 352 #addKink
Cs0 f44 0 0.7890617926546363 0 0 #arcLabel
Cs0 f41 guid 1601F9BB6832261A #txt
Cs0 f41 type ch.ivy.addon.portalkit.component.statistic.ChartCreationWidget.ChartCreationWidgetData #txt
Cs0 f41 method updateBeforeAddTaskByPriority() #txt
Cs0 f41 disableUIEvents false #txt
Cs0 f41 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<> param = methodEvent.getInputArguments();
' #txt
Cs0 f41 inActionCode 'import ch.ivy.addon.portalkit.enums.StatisticChartType;
out.chartType  = StatisticChartType.TASK_BY_PRIORITY;' #txt
Cs0 f41 outParameterDecl '<> result;
' #txt
Cs0 f41 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>updateBeforeAddTaskByPriority()</name>
        <nameStyle>31,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f41 115 339 26 26 -90 15 #rect
Cs0 f41 @|RichDialogMethodStartIcon #fIcon
Cs0 f13 actionDecl 'ch.ivy.addon.portalkit.component.statistic.ChartCreationWidget.ChartCreationWidgetData out;
' #txt
Cs0 f13 actionTable 'out=in;
' #txt
Cs0 f13 actionCode 'in.chartName = "";' #txt
Cs0 f13 type ch.ivy.addon.portalkit.component.statistic.ChartCreationWidget.ChartCreationWidgetData #txt
Cs0 f13 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Clear chart name</name>
        <nameStyle>16
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f13 232 946 112 44 -47 -8 #rect
Cs0 f13 @|StepIcon #fIcon
Cs0 f12 expr out #txt
Cs0 f12 344 968 435 968 #arcP
Cs0 f17 guid 160485D2143D0001 #txt
Cs0 f17 type ch.ivy.addon.portalkit.component.statistic.ChartCreationWidget.ChartCreationWidgetData #txt
Cs0 f17 method updateBeforeAddElapsedTime() #txt
Cs0 f17 disableUIEvents false #txt
Cs0 f17 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<> param = methodEvent.getInputArguments();
' #txt
Cs0 f17 inActionCode 'import ch.ivy.addon.portalkit.enums.StatisticChartType;

out.chartType = StatisticChartType.ELAPSED_TIME_BY_CASE_CATEGORY;' #txt
Cs0 f17 outParameterDecl '<> result;
' #txt
Cs0 f17 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>updateBeforeAddElapsedTime()</name>
        <nameStyle>28,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f17 115 627 26 26 -88 15 #rect
Cs0 f17 @|RichDialogMethodStartIcon #fIcon
Cs0 f18 expr out #txt
Cs0 f18 141 640 448 509 #arcP
Cs0 f18 1 448 640 #addKink
Cs0 f18 0 0.8015192349629681 0 0 #arcLabel
Cs0 f21 type ch.ivy.addon.portalkit.component.statistic.ChartCreationWidget.ChartCreationWidgetData #txt
Cs0 f21 272 856 32 32 0 16 #rect
Cs0 f21 @|AlternativeIcon #fIcon
Cs0 f16 expr out #txt
Cs0 f16 141 872 272 872 #arcP
Cs0 f19 expr in #txt
Cs0 f19 outCond !in.isChartNameExisted #txt
Cs0 f19 288 888 288 946 #arcP
Cs0 f20 expr in #txt
Cs0 f20 304 872 448 955 #arcP
Cs0 f20 1 448 872 #addKink
Cs0 f20 0 0.7991119562493567 0 0 #arcLabel
Cs0 f14 expr out #txt
Cs0 f14 141 968 232 968 #arcP
Cs0 f10 guid 160911F3882D9937 #txt
Cs0 f10 type ch.ivy.addon.portalkit.component.statistic.ChartCreationWidget.ChartCreationWidgetData #txt
Cs0 f10 actionDecl 'ch.ivy.addon.portalkit.component.statistic.ChartCreationWidget.ChartCreationWidgetData out;
' #txt
Cs0 f10 actionTable 'out=in;
' #txt
Cs0 f10 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>clearChartInput</name>
        <nameStyle>15,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f10 115 955 26 26 -42 15 #rect
Cs0 f10 @|RichDialogProcessStartIcon #fIcon
Cs0 f22 guid 1621EDBADBEB0978 #txt
Cs0 f22 type ch.ivy.addon.portalkit.component.statistic.ChartCreationWidget.ChartCreationWidgetData #txt
Cs0 f22 method updateBeforeAddCaseByFinishedTask() #txt
Cs0 f22 disableUIEvents false #txt
Cs0 f22 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<> param = methodEvent.getInputArguments();
' #txt
Cs0 f22 inActionCode 'import ch.ivy.addon.portalkit.enums.StatisticChartType;

out.chartType = StatisticChartType.CASES_BY_FINISHED_TASK;' #txt
Cs0 f22 outParameterDecl '<> result;
' #txt
Cs0 f22 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>updateBeforeAddCaseByFinishedTask()</name>
    </language>
</elementInfo>
' #txt
Cs0 f22 99 723 26 26 -95 16 #rect
Cs0 f22 @|RichDialogMethodStartIcon #fIcon
Cs0 f23 expr out #txt
Cs0 f23 125 736 448 509 #arcP
Cs0 f23 1 448 736 #addKink
Cs0 f23 0 0.8576523140311042 0 0 #arcLabel
Cs0 f24 guid 162233637AE65F94 #txt
Cs0 f24 type ch.ivy.addon.portalkit.component.statistic.ChartCreationWidget.ChartCreationWidgetData #txt
Cs0 f24 method updateBeforeAddCaseByFinishedTime() #txt
Cs0 f24 disableUIEvents false #txt
Cs0 f24 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<> param = methodEvent.getInputArguments();
' #txt
Cs0 f24 inActionCode 'import ch.ivy.addon.portalkit.enums.StatisticChartType;

out.chartType = StatisticChartType.CASES_BY_FINISHED_TIME;' #txt
Cs0 f24 outParameterDecl '<> result;
' #txt
Cs0 f24 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>updateBeforeAddCaseByFinishedTime()</name>
        <nameStyle>35,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f24 99 795 26 26 -111 15 #rect
Cs0 f24 @|RichDialogMethodStartIcon #fIcon
Cs0 f25 expr out #txt
Cs0 f25 125 808 448 509 #arcP
Cs0 f25 1 448 808 #addKink
Cs0 f25 0 0.9649071720549873 0 0 #arcLabel
>Proto Cs0 .type ch.ivy.addon.portalkit.component.statistic.ChartCreationWidget.ChartCreationWidgetData #txt
>Proto Cs0 .processKind HTML_DIALOG #txt
>Proto Cs0 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language/>
    <swimlaneOrientation>false</swimlaneOrientation>
</elementInfo>
' #txt
>Proto Cs0 -8 -8 16 16 16 26 #rect
>Proto Cs0 '' #fIcon
Cs0 f3 mainOut f5 tail #connect
Cs0 f5 head f4 mainIn #connect
Cs0 f41 mainOut f44 tail #connect
Cs0 f44 head f43 mainIn #connect
Cs0 f40 mainOut f45 tail #connect
Cs0 f45 head f43 mainIn #connect
Cs0 f42 mainOut f46 tail #connect
Cs0 f46 head f43 mainIn #connect
Cs0 f6 mainOut f2 tail #connect
Cs0 f2 head f15 mainIn #connect
Cs0 f15 mainOut f8 tail #connect
Cs0 f8 head f7 mainIn #connect
Cs0 f0 mainOut f9 tail #connect
Cs0 f9 head f1 mainIn #connect
Cs0 f13 mainOut f12 tail #connect
Cs0 f12 head f11 mainIn #connect
Cs0 f17 mainOut f18 tail #connect
Cs0 f18 head f43 mainIn #connect
Cs0 f35 mainOut f16 tail #connect
Cs0 f16 head f21 in #connect
Cs0 f21 out f19 tail #connect
Cs0 f19 head f13 mainIn #connect
Cs0 f21 out f20 tail #connect
Cs0 f20 head f11 mainIn #connect
Cs0 f10 mainOut f14 tail #connect
Cs0 f14 head f13 mainIn #connect
Cs0 f22 mainOut f23 tail #connect
Cs0 f23 head f43 mainIn #connect
Cs0 f24 mainOut f25 tail #connect
Cs0 f25 head f43 mainIn #connect
