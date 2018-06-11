[Ivy]
1521B28EE98E8444 3.23 #module
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
Cs0 @RichDialogMethodStart f1 '' #zField
Cs0 @RichDialogProcessEnd f2 '' #zField
Cs0 @PushWFArc f3 '' #zField
Cs0 @RichDialogMethodStart f4 '' #zField
Cs0 @RichDialogProcessEnd f5 '' #zField
Cs0 @RichDialogProcessEnd f7 '' #zField
Cs0 @RichDialogProcessEnd f10 '' #zField
Cs0 @RichDialogProcessStart f12 '' #zField
Cs0 @PushWFArc f13 '' #zField
Cs0 @RichDialogProcessEnd f15 '' #zField
Cs0 @RichDialogProcessStart f16 '' #zField
Cs0 @PushWFArc f17 '' #zField
Cs0 @RichDialogProcessEnd f18 '' #zField
Cs0 @RichDialogProcessStart f19 '' #zField
Cs0 @PushWFArc f20 '' #zField
Cs0 @RichDialogProcessStart f9 '' #zField
Cs0 @RichDialogProcessEnd f11 '' #zField
Cs0 @PushWFArc f14 '' #zField
Cs0 @RichDialogMethodStart f21 '' #zField
Cs0 @RichDialogProcessEnd f22 '' #zField
Cs0 @PushWFArc f23 '' #zField
Cs0 @CallSub f24 '' #zField
Cs0 @GridStep f26 '' #zField
Cs0 @PushWFArc f27 '' #zField
Cs0 @PushWFArc f8 '' #zField
Cs0 @RichDialogProcessStart f28 '' #zField
Cs0 @RichDialogProcessEnd f29 '' #zField
Cs0 @GridStep f31 '' #zField
Cs0 @PushWFArc f32 '' #zField
Cs0 @GridStep f30 '' #zField
Cs0 @Alternative f34 '' #zField
Cs0 @PushWFArc f35 '' #zField
Cs0 @PushWFArc f6 '' #zField
Cs0 @GridStep f36 '' #zField
Cs0 @PushWFArc f38 '' #zField
Cs0 @PushWFArc f33 '' #zField
Cs0 @PushWFArc f39 '' #zField
Cs0 @PushWFArc f25 '' #zField
Cs0 @GridStep f40 '' #zField
Cs0 @CallSub f41 '' #zField
Cs0 @PushWFArc f42 '' #zField
Cs0 @PushWFArc f43 '' #zField
Cs0 @PushWFArc f37 '' #zField
>Proto Cs0 Cs0 StatisticWidgetProcess #zField
Cs0 f0 guid 16034D800DC77D9C #txt
Cs0 f0 type ch.ivy.addon.portalkit.component.StatisticWidget.StatisticWidgetData #txt
Cs0 f0 method start(java.util.List<ch.ivy.addon.portalkit.statistics.StatisticChart>) #txt
Cs0 f0 disableUIEvents true #txt
Cs0 f0 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<java.util.List<ch.ivy.addon.portalkit.statistics.StatisticChart> statisticChartList> param = methodEvent.getInputArguments();
' #txt
Cs0 f0 inParameterMapAction 'out.createMode=false;
' #txt
Cs0 f0 inActionCode 'import ch.ivy.addon.portalkit.service.StatisticService;

if(!param.#statisticChartList is initialized) {
	StatisticService service = new StatisticService();
	out.statisticChartList = service.findStatisticChartsByUserId(ivy.session.getSessionUser().getId());
} else {	
	out.statisticChartList = param.statisticChartList;
}
StatisticService service = new StatisticService();' #txt
Cs0 f0 outParameterDecl '<> result;
' #txt
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
Cs0 f0 @|RichDialogInitStartIcon #fIcon
Cs0 f1 guid 16034D80ACC379F5 #txt
Cs0 f1 type ch.ivy.addon.portalkit.component.StatisticWidget.StatisticWidgetData #txt
Cs0 f1 method switchMode() #txt
Cs0 f1 disableUIEvents false #txt
Cs0 f1 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<> param = methodEvent.getInputArguments();
' #txt
Cs0 f1 inActionCode 'out.compactMode = !out.compactMode;' #txt
Cs0 f1 outParameterDecl '<> result;
' #txt
Cs0 f1 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>switchMode()</name>
        <nameStyle>12,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f1 81 115 26 26 -36 15 #rect
Cs0 f1 @|RichDialogMethodStartIcon #fIcon
Cs0 f2 type ch.ivy.addon.portalkit.component.StatisticWidget.StatisticWidgetData #txt
Cs0 f2 251 115 26 26 0 12 #rect
Cs0 f2 @|RichDialogProcessEndIcon #fIcon
Cs0 f3 expr out #txt
Cs0 f3 107 128 251 128 #arcP
Cs0 f4 guid 16034D8582DD119E #txt
Cs0 f4 type ch.ivy.addon.portalkit.component.StatisticWidget.StatisticWidgetData #txt
Cs0 f4 method setCompactMode(Boolean) #txt
Cs0 f4 disableUIEvents false #txt
Cs0 f4 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<java.lang.Boolean compactMode> param = methodEvent.getInputArguments();
' #txt
Cs0 f4 inParameterMapAction 'out.compactMode=param.compactMode;
' #txt
Cs0 f4 outParameterDecl '<> result;
' #txt
Cs0 f4 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>setCompactMode(Boolean)</name>
        <nameStyle>23,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f4 81 243 26 26 -75 15 #rect
Cs0 f4 @|RichDialogMethodStartIcon #fIcon
Cs0 f5 type ch.ivy.addon.portalkit.component.StatisticWidget.StatisticWidgetData #txt
Cs0 f5 723 243 26 26 0 12 #rect
Cs0 f5 @|RichDialogProcessEndIcon #fIcon
Cs0 f7 type ch.ivy.addon.portalkit.component.StatisticWidget.StatisticWidgetData #txt
Cs0 f7 595 51 26 26 0 12 #rect
Cs0 f7 @|RichDialogProcessEndIcon #fIcon
Cs0 f10 type ch.ivy.addon.portalkit.component.StatisticWidget.StatisticWidgetData #txt
Cs0 f10 251 307 26 26 0 12 #rect
Cs0 f10 @|RichDialogProcessEndIcon #fIcon
Cs0 f12 guid 16048C1223A5C53A #txt
Cs0 f12 type ch.ivy.addon.portalkit.component.StatisticWidget.StatisticWidgetData #txt
Cs0 f12 actionDecl 'ch.ivy.addon.portalkit.component.StatisticWidget.StatisticWidgetData out;
' #txt
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
Cs0 f12 81 307 26 26 -51 15 #rect
Cs0 f12 @|RichDialogProcessStartIcon #fIcon
Cs0 f13 expr out #txt
Cs0 f13 107 320 251 320 #arcP
Cs0 f15 type ch.ivy.addon.portalkit.component.StatisticWidget.StatisticWidgetData #txt
Cs0 f15 251 372 26 26 0 12 #rect
Cs0 f15 @|RichDialogProcessEndIcon #fIcon
Cs0 f16 guid 161BBCEDA1C24D09 #txt
Cs0 f16 type ch.ivy.addon.portalkit.component.StatisticWidget.StatisticWidgetData #txt
Cs0 f16 actionDecl 'ch.ivy.addon.portalkit.component.StatisticWidget.StatisticWidgetData out;
' #txt
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
Cs0 f16 81 372 26 26 -58 15 #rect
Cs0 f16 @|RichDialogProcessStartIcon #fIcon
Cs0 f17 expr out #txt
Cs0 f17 107 385 251 385 #arcP
Cs0 f18 type ch.ivy.addon.portalkit.component.StatisticWidget.StatisticWidgetData #txt
Cs0 f18 251 436 26 26 0 12 #rect
Cs0 f18 @|RichDialogProcessEndIcon #fIcon
Cs0 f19 guid 161BBD78F902AFC2 #txt
Cs0 f19 type ch.ivy.addon.portalkit.component.StatisticWidget.StatisticWidgetData #txt
Cs0 f19 actionDecl 'ch.ivy.addon.portalkit.component.StatisticWidget.StatisticWidgetData out;
' #txt
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
Cs0 f19 81 436 26 26 -84 15 #rect
Cs0 f19 @|RichDialogProcessStartIcon #fIcon
Cs0 f20 expr out #txt
Cs0 f20 107 449 251 449 #arcP
Cs0 f9 guid 16250B3D0BF56AEB #txt
Cs0 f9 type ch.ivy.addon.portalkit.component.StatisticWidget.StatisticWidgetData #txt
Cs0 f9 actionDecl 'ch.ivy.addon.portalkit.component.StatisticWidget.StatisticWidgetData out;
' #txt
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
Cs0 f9 81 498 26 26 -89 15 #rect
Cs0 f9 @|RichDialogProcessStartIcon #fIcon
Cs0 f11 type ch.ivy.addon.portalkit.component.StatisticWidget.StatisticWidgetData #txt
Cs0 f11 251 498 26 26 0 12 #rect
Cs0 f11 @|RichDialogProcessEndIcon #fIcon
Cs0 f14 expr out #txt
Cs0 f14 107 511 251 511 #arcP
Cs0 f21 guid 1625100B1C7D96D2 #txt
Cs0 f21 type ch.ivy.addon.portalkit.component.StatisticWidget.StatisticWidgetData #txt
Cs0 f21 method drillDownExpiryListener() #txt
Cs0 f21 disableUIEvents false #txt
Cs0 f21 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<> param = methodEvent.getInputArguments();
' #txt
Cs0 f21 inParameterMapAction 'out.isBackFromDrilldown=false;
out.isDrilldownExpiryChart=true;
' #txt
Cs0 f21 outParameterDecl '<> result;
' #txt
Cs0 f21 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>drillDownExpiryListener()</name>
        <nameStyle>25,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f21 81 563 26 26 -68 15 #rect
Cs0 f21 @|RichDialogMethodStartIcon #fIcon
Cs0 f22 type ch.ivy.addon.portalkit.component.StatisticWidget.StatisticWidgetData #txt
Cs0 f22 251 563 26 26 0 12 #rect
Cs0 f22 @|RichDialogProcessEndIcon #fIcon
Cs0 f23 expr out #txt
Cs0 f23 107 576 251 576 #arcP
Cs0 f24 type ch.ivy.addon.portalkit.component.StatisticWidget.StatisticWidgetData #txt
Cs0 f24 processCall 'Functional Processes/DefaultChart:createDefaultChart()' #txt
Cs0 f24 doCall true #txt
Cs0 f24 requestActionDecl '<> param;
' #txt
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
Cs0 f24 211 42 112 44 -34 -8 #rect
Cs0 f24 @|CallSubIcon #fIcon
Cs0 f26 actionDecl 'ch.ivy.addon.portalkit.component.StatisticWidget.StatisticWidgetData out;
' #txt
Cs0 f26 actionTable 'out=in;
' #txt
Cs0 f26 actionCode in.statisticChartList.addAll(in.defaultCharts); #txt
Cs0 f26 type ch.ivy.addon.portalkit.component.StatisticWidget.StatisticWidgetData #txt
Cs0 f26 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>add to chart list</name>
    </language>
</elementInfo>
' #txt
Cs0 f26 379 42 112 44 -41 -8 #rect
Cs0 f26 @|StepIcon #fIcon
Cs0 f27 expr out #txt
Cs0 f27 323 64 379 64 #arcP
Cs0 f8 expr out #txt
Cs0 f8 107 64 211 64 #arcP
Cs0 f28 guid 163AF4A8A1BFD729 #txt
Cs0 f28 type ch.ivy.addon.portalkit.component.StatisticWidget.StatisticWidgetData #txt
Cs0 f28 actionDecl 'ch.ivy.addon.portalkit.component.StatisticWidget.StatisticWidgetData out;
' #txt
Cs0 f28 actionTable 'out=in;
' #txt
Cs0 f28 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>restoreDefault</name>
    </language>
</elementInfo>
' #txt
Cs0 f28 81 627 26 26 -39 12 #rect
Cs0 f28 @|RichDialogProcessStartIcon #fIcon
Cs0 f29 type ch.ivy.addon.portalkit.component.StatisticWidget.StatisticWidgetData #txt
Cs0 f29 683 627 26 26 0 12 #rect
Cs0 f29 @|RichDialogProcessEndIcon #fIcon
Cs0 f31 actionDecl 'ch.ivy.addon.portalkit.component.StatisticWidget.StatisticWidgetData out;
' #txt
Cs0 f31 actionTable 'out=in;
' #txt
Cs0 f31 actionCode 'import ch.ivy.addon.portalkit.statistics.StatisticChart;
import ch.ivy.addon.portalkit.service.StatisticService;

in.statisticChartList.clear();

StatisticService service = new StatisticService();
service.removeStatisticChartsByUserId(ivy.session.getSessionUser().getId());
' #txt
Cs0 f31 type ch.ivy.addon.portalkit.component.StatisticWidget.StatisticWidgetData #txt
Cs0 f31 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>restore default charts</name>
    </language>
</elementInfo>
' #txt
Cs0 f31 200 618 128 44 -58 -8 #rect
Cs0 f31 @|StepIcon #fIcon
Cs0 f32 expr out #txt
Cs0 f32 107 640 200 640 #arcP
Cs0 f30 actionDecl 'ch.ivy.addon.portalkit.component.StatisticWidget.StatisticWidgetData out;
' #txt
Cs0 f30 actionTable 'out=in;
' #txt
Cs0 f30 actionCode 'import ch.ivy.addon.portalkit.enums.PortalPermission;
import ch.ivy.addon.portalkit.util.PermissionUtils;

in.canCreateNewCharts = PermissionUtils.hasPortalPermission(PortalPermission.ADD_DASHBOARDS_CHARTS);' #txt
Cs0 f30 security system #txt
Cs0 f30 type ch.ivy.addon.portalkit.component.StatisticWidget.StatisticWidgetData #txt
Cs0 f30 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Check permission&#xD;
to creating new charts</name>
    </language>
</elementInfo>
' #txt
Cs0 f30 488 170 144 44 -54 -16 #rect
Cs0 f30 @|StepIcon #fIcon
Cs0 f34 type ch.ivy.addon.portalkit.component.StatisticWidget.StatisticWidgetData #txt
Cs0 f34 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>is compact mode?</name>
        <nameStyle>16
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f34 248 240 32 32 -46 14 #rect
Cs0 f34 @|AlternativeIcon #fIcon
Cs0 f35 expr out #txt
Cs0 f35 107 256 248 256 #arcP
Cs0 f6 expr in #txt
Cs0 f6 outCond in.compactMode #txt
Cs0 f6 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>yes</name>
        <nameStyle>3
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f6 280 256 723 256 #arcP
Cs0 f6 0 0.4748858447488584 0 12 #arcLabel
Cs0 f36 actionDecl 'ch.ivy.addon.portalkit.component.StatisticWidget.StatisticWidgetData out;
' #txt
Cs0 f36 actionTable 'out=in;
' #txt
Cs0 f36 actionCode 'import ch.ivy.addon.portalkit.service.StatisticService;
StatisticService service = new StatisticService();
in.hasDefaultChart = service.hasDefaultChart(ivy.session.getSessionUser().getId());' #txt
Cs0 f36 type ch.ivy.addon.portalkit.component.StatisticWidget.StatisticWidgetData #txt
Cs0 f36 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>check default chart</name>
    </language>
</elementInfo>
' #txt
Cs0 f36 328 170 112 44 -51 -8 #rect
Cs0 f36 @|StepIcon #fIcon
Cs0 f38 expr in #txt
Cs0 f38 264 240 328 192 #arcP
Cs0 f38 1 264 192 #addKink
Cs0 f38 1 0.14115788860486556 0 0 #arcLabel
Cs0 f33 expr out #txt
Cs0 f33 440 192 488 192 #arcP
Cs0 f33 0 0.4955005072988749 0 0 #arcLabel
Cs0 f39 expr out #txt
Cs0 f39 632 192 736 243 #arcP
Cs0 f39 1 736 192 #addKink
Cs0 f39 0 0.7579963419912229 0 0 #arcLabel
Cs0 f25 expr out #txt
Cs0 f25 491 64 595 64 #arcP
Cs0 f40 actionDecl 'ch.ivy.addon.portalkit.component.StatisticWidget.StatisticWidgetData out;
' #txt
Cs0 f40 actionTable 'out=in;
' #txt
Cs0 f40 actionCode in.statisticChartList.addAll(in.defaultCharts); #txt
Cs0 f40 type ch.ivy.addon.portalkit.component.StatisticWidget.StatisticWidgetData #txt
Cs0 f40 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>add to chart list</name>
    </language>
</elementInfo>
' #txt
Cs0 f40 537 619 112 44 -41 -8 #rect
Cs0 f40 @|StepIcon #fIcon
Cs0 f41 type ch.ivy.addon.portalkit.component.StatisticWidget.StatisticWidgetData #txt
Cs0 f41 processCall 'Functional Processes/DefaultChart:createDefaultChart()' #txt
Cs0 f41 doCall true #txt
Cs0 f41 requestActionDecl '<> param;
' #txt
Cs0 f41 responseActionDecl 'ch.ivy.addon.portalkit.component.StatisticWidget.StatisticWidgetData out;
' #txt
Cs0 f41 responseMappingAction 'out=in;
out.defaultCharts=result.charts;
' #txt
Cs0 f41 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>DefaultChart</name>
        <nameStyle>12,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f41 369 619 112 44 -34 -8 #rect
Cs0 f41 @|CallSubIcon #fIcon
Cs0 f42 expr out #txt
Cs0 f42 481 641 537 641 #arcP
Cs0 f43 expr out #txt
Cs0 f43 328 640 369 641 #arcP
Cs0 f37 expr out #txt
Cs0 f37 649 641 683 640 #arcP
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
Cs0 f0 mainOut f8 tail #connect
Cs0 f8 head f24 mainIn #connect
Cs0 f28 mainOut f32 tail #connect
Cs0 f32 head f31 mainIn #connect
Cs0 f4 mainOut f35 tail #connect
Cs0 f35 head f34 in #connect
Cs0 f34 out f6 tail #connect
Cs0 f6 head f5 mainIn #connect
Cs0 f34 out f38 tail #connect
Cs0 f38 head f36 mainIn #connect
Cs0 f36 mainOut f33 tail #connect
Cs0 f33 head f30 mainIn #connect
Cs0 f30 mainOut f39 tail #connect
Cs0 f39 head f5 mainIn #connect
Cs0 f26 mainOut f25 tail #connect
Cs0 f25 head f7 mainIn #connect
Cs0 f41 mainOut f42 tail #connect
Cs0 f42 head f40 mainIn #connect
Cs0 f31 mainOut f43 tail #connect
Cs0 f43 head f41 mainIn #connect
Cs0 f40 mainOut f37 tail #connect
Cs0 f37 head f29 mainIn #connect
