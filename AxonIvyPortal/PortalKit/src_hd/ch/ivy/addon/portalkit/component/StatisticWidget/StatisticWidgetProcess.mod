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
Cs0 @RichDialogMethodStart f9 '' #zField
Cs0 @RichDialogProcessEnd f11 '' #zField
Cs0 @PushWFArc f14 '' #zField
Cs0 @PushWFArc f13 '' #zField
>Proto Cs0 Cs0 StatisticWidgetProcess #zField
Cs0 f0 guid 16034D800DC77D9C #txt
Cs0 f0 type ch.ivy.addon.portalkit.component.StatisticWidget.StatisticWidgetData #txt
Cs0 f0 method start() #txt
Cs0 f0 disableUIEvents true #txt
Cs0 f0 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<> param = methodEvent.getInputArguments();
' #txt
Cs0 f0 inParameterMapAction 'out.createMode=false;
' #txt
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
Cs0 f1 83 243 26 26 -36 15 #rect
Cs0 f1 @|RichDialogMethodStartIcon #fIcon
Cs0 f2 type ch.ivy.addon.portalkit.component.StatisticWidget.StatisticWidgetData #txt
Cs0 f2 275 243 26 26 0 12 #rect
Cs0 f2 @|RichDialogProcessEndIcon #fIcon
Cs0 f3 expr out #txt
Cs0 f3 109 256 275 256 #arcP
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
Cs0 f4 83 339 26 26 -75 15 #rect
Cs0 f4 @|RichDialogMethodStartIcon #fIcon
Cs0 f5 type ch.ivy.addon.portalkit.component.StatisticWidget.StatisticWidgetData #txt
Cs0 f5 275 339 26 26 0 12 #rect
Cs0 f5 @|RichDialogProcessEndIcon #fIcon
Cs0 f6 expr out #txt
Cs0 f6 109 352 275 352 #arcP
Cs0 f7 type ch.ivy.addon.portalkit.component.StatisticWidget.StatisticWidgetData #txt
Cs0 f7 275 51 26 26 0 12 #rect
Cs0 f7 @|RichDialogProcessEndIcon #fIcon
Cs0 f10 type ch.ivy.addon.portalkit.component.StatisticWidget.StatisticWidgetData #txt
Cs0 f10 275 435 26 26 0 12 #rect
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
Cs0 f12 83 435 26 26 -51 15 #rect
Cs0 f12 @|RichDialogProcessStartIcon #fIcon
Cs0 f8 expr out #txt
Cs0 f8 109 64 275 64 #arcP
Cs0 f9 guid 1604F419BC8CE6D6 #txt
Cs0 f9 type ch.ivy.addon.portalkit.component.StatisticWidget.StatisticWidgetData #txt
Cs0 f9 method intialize(java.util.List<ch.ivy.addon.portalkit.statistics.StatisticChart>) #txt
Cs0 f9 disableUIEvents false #txt
Cs0 f9 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<java.util.List<ch.ivy.addon.portalkit.statistics.StatisticChart> statisticChartList> param = methodEvent.getInputArguments();
' #txt
Cs0 f9 inParameterMapAction 'out.statisticChartList=param.statisticChartList;
' #txt
Cs0 f9 outParameterDecl '<> result;
' #txt
Cs0 f9 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>intialize(List&lt;StatisticChart&gt;)</name>
        <nameStyle>31,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f9 83 147 26 26 -78 15 #rect
Cs0 f9 @|RichDialogMethodStartIcon #fIcon
Cs0 f11 type ch.ivy.addon.portalkit.component.StatisticWidget.StatisticWidgetData #txt
Cs0 f11 275 147 26 26 0 12 #rect
Cs0 f11 @|RichDialogProcessEndIcon #fIcon
Cs0 f14 expr out #txt
Cs0 f14 109 160 275 160 #arcP
Cs0 f13 expr out #txt
Cs0 f13 109 448 275 448 #arcP
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
Cs0 f9 mainOut f14 tail #connect
Cs0 f14 head f11 mainIn #connect
