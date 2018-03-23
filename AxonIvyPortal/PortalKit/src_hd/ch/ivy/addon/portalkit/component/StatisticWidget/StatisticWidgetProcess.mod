[Ivy]
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
Cs0 @RichDialogMethodStart f1 '' #zField
Cs0 @RichDialogProcessEnd f2 '' #zField
Cs0 @PushWFArc f3 '' #zField
Cs0 @RichDialogMethodStart f4 '' #zField
Cs0 @RichDialogProcessEnd f5 '' #zField
Cs0 @PushWFArc f6 '' #zField
Cs0 @RichDialogProcessEnd f7 '' #zField
Cs0 @RichDialogProcessEnd f10 '' #zField
Cs0 @RichDialogProcessStart f12 '' #zField
Cs0 @PushWFArc f8 '' #zField
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
}' #txt
Cs0 f0 outParameterDecl '<> result;
' #txt
Cs0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start()</name>
        <nameStyle>7,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f0 83 51 26 26 -16 15 #rect
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
Cs0 f1 83 147 26 26 -36 15 #rect
Cs0 f1 @|RichDialogMethodStartIcon #fIcon
Cs0 f2 type ch.ivy.addon.portalkit.component.StatisticWidget.StatisticWidgetData #txt
Cs0 f2 275 147 26 26 0 12 #rect
Cs0 f2 @|RichDialogProcessEndIcon #fIcon
Cs0 f3 expr out #txt
Cs0 f3 109 160 275 160 #arcP
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
Cs0 f4 83 243 26 26 -75 15 #rect
Cs0 f4 @|RichDialogMethodStartIcon #fIcon
Cs0 f5 type ch.ivy.addon.portalkit.component.StatisticWidget.StatisticWidgetData #txt
Cs0 f5 275 243 26 26 0 12 #rect
Cs0 f5 @|RichDialogProcessEndIcon #fIcon
Cs0 f6 expr out #txt
Cs0 f6 109 256 275 256 #arcP
Cs0 f7 type ch.ivy.addon.portalkit.component.StatisticWidget.StatisticWidgetData #txt
Cs0 f7 275 51 26 26 0 12 #rect
Cs0 f7 @|RichDialogProcessEndIcon #fIcon
Cs0 f10 type ch.ivy.addon.portalkit.component.StatisticWidget.StatisticWidgetData #txt
Cs0 f10 275 339 26 26 0 12 #rect
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
Cs0 f12 83 339 26 26 -51 15 #rect
Cs0 f12 @|RichDialogProcessStartIcon #fIcon
Cs0 f8 expr out #txt
Cs0 f8 109 64 275 64 #arcP
Cs0 f13 expr out #txt
Cs0 f13 109 352 275 352 #arcP
Cs0 f15 type ch.ivy.addon.portalkit.component.StatisticWidget.StatisticWidgetData #txt
Cs0 f15 275 436 26 26 0 12 #rect
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
Cs0 f16 83 436 26 26 -58 15 #rect
Cs0 f16 @|RichDialogProcessStartIcon #fIcon
Cs0 f17 expr out #txt
Cs0 f17 109 449 275 449 #arcP
Cs0 f18 type ch.ivy.addon.portalkit.component.StatisticWidget.StatisticWidgetData #txt
Cs0 f18 273 532 26 26 0 12 #rect
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
Cs0 f19 81 532 26 26 -84 15 #rect
Cs0 f19 @|RichDialogProcessStartIcon #fIcon
Cs0 f20 expr out #txt
Cs0 f20 107 545 273 545 #arcP
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
Cs0 f9 85 634 26 26 -89 15 #rect
Cs0 f9 @|RichDialogProcessStartIcon #fIcon
Cs0 f11 type ch.ivy.addon.portalkit.component.StatisticWidget.StatisticWidgetData #txt
Cs0 f11 277 634 26 26 0 12 #rect
Cs0 f11 @|RichDialogProcessEndIcon #fIcon
Cs0 f14 expr out #txt
Cs0 f14 111 647 277 647 #arcP
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
Cs0 f21 83 739 26 26 -68 15 #rect
Cs0 f21 @|RichDialogMethodStartIcon #fIcon
Cs0 f22 type ch.ivy.addon.portalkit.component.StatisticWidget.StatisticWidgetData #txt
Cs0 f22 275 739 26 26 0 12 #rect
Cs0 f22 @|RichDialogProcessEndIcon #fIcon
Cs0 f23 expr out #txt
Cs0 f23 109 752 275 752 #arcP
>Proto Cs0 .type ch.ivy.addon.portalkit.component.StatisticWidget.StatisticWidgetData #txt
>Proto Cs0 .processKind HTML_DIALOG #txt
>Proto Cs0 -8 -8 16 16 16 26 #rect
>Proto Cs0 '' #fIcon
Cs0 f1 mainOut f3 tail #connect
Cs0 f3 head f2 mainIn #connect
Cs0 f4 mainOut f6 tail #connect
Cs0 f6 head f5 mainIn #connect
Cs0 f0 mainOut f8 tail #connect
Cs0 f8 head f7 mainIn #connect
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
