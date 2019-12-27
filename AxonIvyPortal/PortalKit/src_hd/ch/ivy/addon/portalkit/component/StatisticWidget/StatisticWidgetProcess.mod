[Ivy]
1521B28EE98E8444 7.5.0 #module
>Proto >Proto Collection #zClass
Cs0 StatisticWidgetProcess Big #zClass
Cs0 RD #cInfo
Cs0 #process
Cs0 @TextInP .type .type #zField
Cs0 @TextInP .processKind .processKind #zField
Cs0 @AnnotationInP-0n ai ai #zField
Cs0 @MessageFlowInP-0n messageIn messageIn #zField
Cs0 @MessageFlowOutP-0n messageOut messageOut #zField
Cs0 @TextInP .xml .xml #zField
Cs0 @TextInP .responsibility .responsibility #zField
Cs0 @UdInit f0 '' #zField
Cs0 @UdMethod f1 '' #zField
Cs0 @UdProcessEnd f2 '' #zField
Cs0 @PushWFArc f3 '' #zField
Cs0 @UdMethod f4 '' #zField
Cs0 @UdProcessEnd f5 '' #zField
Cs0 @UdProcessEnd f7 '' #zField
Cs0 @UdProcessEnd f10 '' #zField
Cs0 @UdEvent f12 '' #zField
Cs0 @PushWFArc f13 '' #zField
Cs0 @UdProcessEnd f15 '' #zField
Cs0 @UdEvent f16 '' #zField
Cs0 @PushWFArc f17 '' #zField
Cs0 @UdProcessEnd f18 '' #zField
Cs0 @UdEvent f19 '' #zField
Cs0 @PushWFArc f20 '' #zField
Cs0 @UdEvent f9 '' #zField
Cs0 @UdProcessEnd f11 '' #zField
Cs0 @PushWFArc f14 '' #zField
Cs0 @UdMethod f21 '' #zField
Cs0 @UdProcessEnd f22 '' #zField
Cs0 @PushWFArc f23 '' #zField
Cs0 @CallSub f24 '' #zField
Cs0 @GridStep f26 '' #zField
Cs0 @PushWFArc f27 '' #zField
Cs0 @UdEvent f28 '' #zField
Cs0 @GridStep f31 '' #zField
Cs0 @PushWFArc f32 '' #zField
Cs0 @GridStep f30 '' #zField
Cs0 @Alternative f34 '' #zField
Cs0 @PushWFArc f35 '' #zField
Cs0 @GridStep f36 '' #zField
Cs0 @PushWFArc f38 '' #zField
Cs0 @PushWFArc f33 '' #zField
Cs0 @PushWFArc f39 '' #zField
Cs0 @PushWFArc f25 '' #zField
Cs0 @UdEvent f29 '' #zField
Cs0 @UdProcessEnd f37 '' #zField
Cs0 @GridStep f41 '' #zField
Cs0 @PushWFArc f42 '' #zField
Cs0 @PushWFArc f40 '' #zField
Cs0 @GridStep f44 '' #zField
Cs0 @PushWFArc f45 '' #zField
Cs0 @PushWFArc f6 '' #zField
Cs0 @CallSub f46 '' #zField
Cs0 @PushWFArc f48 '' #zField
Cs0 @PushWFArc f8 '' #zField
Cs0 @UdProcessEnd f47 '' #zField
Cs0 @PushWFArc f49 '' #zField
>Proto Cs0 Cs0 StatisticWidgetProcess #zField
Cs0 f0 guid 16034D800DC77D9C #txt
Cs0 f0 method start(java.util.List<ch.ivy.addon.portalkit.statistics.StatisticChart>) #txt
Cs0 f0 inParameterDecl '<java.util.List<ch.ivy.addon.portalkit.statistics.StatisticChart> statisticChartList> param;' #txt
Cs0 f0 inParameterMapAction 'out.createMode=false;
' #txt
Cs0 f0 inActionCode 'import ch.ivy.addon.portalkit.service.StatisticService;

if(!param.#statisticChartList is initialized) {
	StatisticService service = new StatisticService();
	out.statisticChartList = service.findStatisticChartsByUserId(ivy.session.getSessionUser().getId());
} else {	
	out.statisticChartList = param.statisticChartList;
}' #txt
Cs0 f0 outParameterDecl '<> result;' #txt
Cs0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start(List&lt;StatisticChart&gt;)</name>
        <nameStyle>27,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f0 81 51 26 26 -69 15 #rect
Cs0 f0 @|UdInitIcon #fIcon
Cs0 f1 guid 16034D80ACC379F5 #txt
Cs0 f1 method switchMode() #txt
Cs0 f1 inParameterDecl '<> param;' #txt
Cs0 f1 inActionCode 'out.compactMode = !out.compactMode;' #txt
Cs0 f1 outParameterDecl '<> result;' #txt
Cs0 f1 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>switchMode()</name>
        <nameStyle>12,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f1 81 243 26 26 -36 15 #rect
Cs0 f1 @|UdMethodIcon #fIcon
Cs0 f2 251 243 26 26 0 12 #rect
Cs0 f2 @|UdProcessEndIcon #fIcon
Cs0 f3 expr out #txt
Cs0 f3 107 256 251 256 #arcP
Cs0 f4 guid 16034D8582DD119E #txt
Cs0 f4 method setCompactMode(Boolean) #txt
Cs0 f4 inParameterDecl '<Boolean compactMode> param;' #txt
Cs0 f4 inParameterMapAction 'out.compactMode=param.compactMode;
' #txt
Cs0 f4 outParameterDecl '<> result;' #txt
Cs0 f4 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>setCompactMode(Boolean)</name>
        <nameStyle>23,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f4 81 371 26 26 -75 15 #rect
Cs0 f4 @|UdMethodIcon #fIcon
Cs0 f5 723 371 26 26 0 12 #rect
Cs0 f5 @|UdProcessEndIcon #fIcon
Cs0 f7 963 147 26 26 0 12 #rect
Cs0 f7 @|UdProcessEndIcon #fIcon
Cs0 f10 251 435 26 26 0 12 #rect
Cs0 f10 @|UdProcessEndIcon #fIcon
Cs0 f12 guid 16048C1223A5C53A #txt
Cs0 f12 actionTable 'out=in;
' #txt
Cs0 f12 actionCode 'out.createMode = !out.createMode;' #txt
Cs0 f12 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>switchCreateMode</name>
        <nameStyle>16,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f12 81 435 26 26 -51 15 #rect
Cs0 f12 @|UdEventIcon #fIcon
Cs0 f13 expr out #txt
Cs0 f13 107 448 251 448 #arcP
Cs0 f15 251 500 26 26 0 12 #rect
Cs0 f15 @|UdProcessEndIcon #fIcon
Cs0 f16 guid 161BBCEDA1C24D09 #txt
Cs0 f16 actionTable 'out=in;
out.isDrilldownElapsedTime=false;
' #txt
Cs0 f16 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>switchDrilldownMode</name>
        <nameStyle>19,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f16 81 500 26 26 -58 15 #rect
Cs0 f16 @|UdEventIcon #fIcon
Cs0 f17 expr out #txt
Cs0 f17 107 513 251 513 #arcP
Cs0 f18 251 564 26 26 0 12 #rect
Cs0 f18 @|UdProcessEndIcon #fIcon
Cs0 f19 guid 161BBD78F902AFC2 #txt
Cs0 f19 actionTable 'out=in;
out.isDrilldownElapsedTime=true;
' #txt
Cs0 f19 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>drilldownElapsedTimeListener</name>
        <nameStyle>28,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f19 81 564 26 26 -84 15 #rect
Cs0 f19 @|UdEventIcon #fIcon
Cs0 f20 expr out #txt
Cs0 f20 107 577 251 577 #arcP
Cs0 f9 guid 16250B3D0BF56AEB #txt
Cs0 f9 actionTable 'out=in;
out.isBackFromDrilldown=true;
out.isDrilldownExpiryChart=false;
' #txt
Cs0 f9 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>switchDrilldownExpiryChartMode</name>
        <nameStyle>30,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f9 81 626 26 26 -89 15 #rect
Cs0 f9 @|UdEventIcon #fIcon
Cs0 f11 251 626 26 26 0 12 #rect
Cs0 f11 @|UdProcessEndIcon #fIcon
Cs0 f14 expr out #txt
Cs0 f14 107 639 251 639 #arcP
Cs0 f21 guid 1625100B1C7D96D2 #txt
Cs0 f21 method drillDownExpiryListener() #txt
Cs0 f21 inParameterDecl '<> param;' #txt
Cs0 f21 inParameterMapAction 'out.isBackFromDrilldown=false;
out.isDrilldownExpiryChart=true;
' #txt
Cs0 f21 outParameterDecl '<> result;' #txt
Cs0 f21 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>drillDownExpiryListener()</name>
        <nameStyle>25,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f21 81 691 26 26 -68 15 #rect
Cs0 f21 @|UdMethodIcon #fIcon
Cs0 f22 251 691 26 26 0 12 #rect
Cs0 f22 @|UdProcessEndIcon #fIcon
Cs0 f23 expr out #txt
Cs0 f23 107 704 251 704 #arcP
Cs0 f24 processCall 'Functional Processes/DefaultChart:createDefaultChart()' #txt
Cs0 f24 requestActionDecl '<> param;' #txt
Cs0 f24 responseActionDecl 'ch.ivy.addon.portalkit.component.StatisticWidget.StatisticWidgetData out;
' #txt
Cs0 f24 responseMappingAction 'out=in;
out.defaultCharts=result.charts;
' #txt
Cs0 f24 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>DefaultChart</name>
        <nameStyle>12,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f24 592 138 112 44 -34 -8 #rect
Cs0 f24 @|CallSubIcon #fIcon
Cs0 f26 actionTable 'out=in;
' #txt
Cs0 f26 actionCode in.statisticChartList.addAll(in.defaultCharts); #txt
Cs0 f26 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>add to chart list</name>
    </language>
</elementInfo>
' #txt
Cs0 f26 768 138 112 44 -41 -8 #rect
Cs0 f26 @|StepIcon #fIcon
Cs0 f27 expr out #txt
Cs0 f27 704 160 768 160 #arcP
Cs0 f28 guid 163AF4A8A1BFD729 #txt
Cs0 f28 actionTable 'out=in;
' #txt
Cs0 f28 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>restoreDefault</name>
    </language>
</elementInfo>
' #txt
Cs0 f28 81 147 26 26 -39 12 #rect
Cs0 f28 @|UdEventIcon #fIcon
Cs0 f31 actionTable 'out=in;
' #txt
Cs0 f31 actionCode 'import ch.ivy.addon.portalkit.statistics.StatisticChart;
import ch.ivy.addon.portalkit.service.StatisticService;

in.statisticChartList.clear();

StatisticService service = new StatisticService();
service.removeStatisticChartsByUserId(ivy.session.getSessionUser().getId());
' #txt
Cs0 f31 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>restore default charts</name>
    </language>
</elementInfo>
' #txt
Cs0 f31 196 136 120 48 -58 -8 #rect
Cs0 f31 @|StepIcon #fIcon
Cs0 f32 expr out #txt
Cs0 f32 107 160 196 160 #arcP
Cs0 f30 actionTable 'out=in;
' #txt
Cs0 f30 actionCode 'import ch.ivy.addon.portalkit.enums.PortalPermission;
import ch.ivy.addon.portalkit.util.PermissionUtils;

in.canCreateNewCharts = PermissionUtils.hasPortalPermission(PortalPermission.STATISTIC_ADD_DASHBOARD_CHART);
in.canAnalyzeTask = PermissionUtils.hasPortalPermission(PortalPermission.STATISTIC_ANALYZE_TASK);' #txt
Cs0 f30 security system #txt
Cs0 f30 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Check permission&#xD;
to creating new charts</name>
    </language>
</elementInfo>
' #txt
Cs0 f30 488 298 144 44 -54 -16 #rect
Cs0 f30 @|StepIcon #fIcon
Cs0 f34 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>is compact mode?</name>
        <nameStyle>16
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f34 248 368 32 32 -46 14 #rect
Cs0 f34 @|AlternativeIcon #fIcon
Cs0 f35 expr out #txt
Cs0 f35 107 384 248 384 #arcP
Cs0 f36 actionTable 'out=in;
' #txt
Cs0 f36 actionCode 'import ch.ivy.addon.portalkit.service.StatisticService;

StatisticService service = new StatisticService();
if(!in.#statisticChartList is initialized || !in.statisticChartList.isEmpty()) {
	in.hasDefaultChart = service.hasDefaultChart(ivy.session.getSessionUser().getId());
} else {
	in.hasDefaultChart = service.isDefaultChart(in.statisticChartList);
}' #txt
Cs0 f36 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>check default chart</name>
    </language>
</elementInfo>
' #txt
Cs0 f36 328 298 112 44 -51 -8 #rect
Cs0 f36 @|StepIcon #fIcon
Cs0 f38 expr in #txt
Cs0 f38 264 368 328 320 #arcP
Cs0 f38 1 264 320 #addKink
Cs0 f38 1 0.14115788860486556 0 0 #arcLabel
Cs0 f33 expr out #txt
Cs0 f33 440 320 488 320 #arcP
Cs0 f33 0 0.4955005072988749 0 0 #arcLabel
Cs0 f39 expr out #txt
Cs0 f39 632 320 736 371 #arcP
Cs0 f39 1 736 320 #addKink
Cs0 f39 0 0.7579963419912229 0 0 #arcLabel
Cs0 f25 expr out #txt
Cs0 f25 880 160 963 160 #arcP
Cs0 f29 guid 16411B8894BC5C90 #txt
Cs0 f29 actionTable 'out=in;
' #txt
Cs0 f29 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>navigateToTaskAnalysisPage</name>
        <nameStyle>26,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f29 83 771 26 26 -81 15 #rect
Cs0 f29 @|UdEventIcon #fIcon
Cs0 f37 467 771 26 26 0 12 #rect
Cs0 f37 @|UdProcessEndIcon #fIcon
Cs0 f41 actionTable 'out=in;
' #txt
Cs0 f41 actionCode 'import javax.faces.context.FacesContext;

String taskAnalysisUrl = ivy.html.startref("Start Processes/TaskAnalysis/start.ivp");
FacesContext.getCurrentInstance().getExternalContext().redirect(taskAnalysisUrl);' #txt
Cs0 f41 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Redirect to analysis page</name>
    </language>
</elementInfo>
' #txt
Cs0 f41 216 762 144 44 -69 -8 #rect
Cs0 f41 @|StepIcon #fIcon
Cs0 f42 expr out #txt
Cs0 f42 109 784 216 784 #arcP
Cs0 f40 expr out #txt
Cs0 f40 360 784 467 784 #arcP
Cs0 f44 actionTable 'out=in;
' #txt
Cs0 f44 actionCode 'import ch.ivy.addon.portalkit.util.PermissionUtils;
in.isDisplayShowAllChartLink = PermissionUtils.checkAccessFullStatisticsListPermission();' #txt
Cs0 f44 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Check permission &#xD;
to see show all chart link</name>
    </language>
</elementInfo>
' #txt
Cs0 f44 400 362 160 44 -61 -16 #rect
Cs0 f44 @|StepIcon #fIcon
Cs0 f45 expr in #txt
Cs0 f45 outCond in.compactMode #txt
Cs0 f45 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>yes</name>
        <nameStyle>3
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f45 280 384 400 384 #arcP
Cs0 f45 0 0.4748858447488584 0 12 #arcLabel
Cs0 f6 expr out #txt
Cs0 f6 560 384 723 384 #arcP
Cs0 f6 0 0.4748858447488584 0 12 #arcLabel
Cs0 f46 processCall 'Functional Processes/DefaultChartColors:defaultChartColors()' #txt
Cs0 f46 requestActionDecl '<> param;' #txt
Cs0 f46 responseMappingAction 'out=in;
out.chartColors=result.chartColors;
' #txt
Cs0 f46 responseActionCode 'import ch.ivy.addon.portalkit.service.StatisticService;

StatisticService service = new StatisticService();
service.setStatisticsColors(result.chartColors);' #txt
Cs0 f46 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>DefaultChartColors</name>
    </language>
</elementInfo>
' #txt
Cs0 f46 376 138 112 44 -53 -8 #rect
Cs0 f46 @|CallSubIcon #fIcon
Cs0 f48 expr out #txt
Cs0 f48 316 160 376 160 #arcP
Cs0 f8 488 160 592 160 #arcP
Cs0 f47 251 51 26 26 0 12 #rect
Cs0 f47 @|UdProcessEndIcon #fIcon
Cs0 f49 expr out #txt
Cs0 f49 107 64 251 64 #arcP
Cs0 f49 0 0.5808494517759799 0 0 #arcLabel
>Proto Cs0 .type ch.ivy.addon.portalkit.component.StatisticWidget.StatisticWidgetData #txt
>Proto Cs0 .processKind HTML_DIALOG #txt
>Proto Cs0 -8 -8 16 16 16 26 #rect
>Proto Cs0 '' #fIcon
Cs0 f1 mainOut f3 tail #connect
Cs0 f3 head f2 mainIn #connect
Cs0 f12 mainOut f13 tail #connect
Cs0 f13 head f10 mainIn #connect
Cs0 f16 mainOut f17 tail #connect
Cs0 f17 head f15 mainIn #connect
Cs0 f19 mainOut f20 tail #connect
Cs0 f20 head f18 mainIn #connect
Cs0 f9 mainOut f14 tail #connect
Cs0 f14 head f11 mainIn #connect
Cs0 f21 mainOut f23 tail #connect
Cs0 f23 head f22 mainIn #connect
Cs0 f24 mainOut f27 tail #connect
Cs0 f27 head f26 mainIn #connect
Cs0 f28 mainOut f32 tail #connect
Cs0 f32 head f31 mainIn #connect
Cs0 f4 mainOut f35 tail #connect
Cs0 f35 head f34 in #connect
Cs0 f38 head f36 mainIn #connect
Cs0 f36 mainOut f33 tail #connect
Cs0 f33 head f30 mainIn #connect
Cs0 f30 mainOut f39 tail #connect
Cs0 f39 head f5 mainIn #connect
Cs0 f26 mainOut f25 tail #connect
Cs0 f25 head f7 mainIn #connect
Cs0 f29 mainOut f42 tail #connect
Cs0 f42 head f41 mainIn #connect
Cs0 f41 mainOut f40 tail #connect
Cs0 f40 head f37 mainIn #connect
Cs0 f34 out f45 tail #connect
Cs0 f45 head f44 mainIn #connect
Cs0 f34 out f38 tail #connect
Cs0 f44 mainOut f6 tail #connect
Cs0 f6 head f5 mainIn #connect
Cs0 f31 mainOut f48 tail #connect
Cs0 f48 head f46 mainIn #connect
Cs0 f46 mainOut f8 tail #connect
Cs0 f8 head f24 mainIn #connect
Cs0 f0 mainOut f49 tail #connect
Cs0 f49 head f47 mainIn #connect
