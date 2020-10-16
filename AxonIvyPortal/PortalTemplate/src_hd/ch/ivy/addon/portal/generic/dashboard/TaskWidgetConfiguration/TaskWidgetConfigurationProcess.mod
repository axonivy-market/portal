[Ivy]
172C13E97C96B7E3 7.5.0 #module
>Proto >Proto Collection #zClass
Ts0 TaskWidgetConfigurationProcess Big #zClass
Ts0 RD #cInfo
Ts0 #process
Ts0 @TextInP .type .type #zField
Ts0 @TextInP .processKind .processKind #zField
Ts0 @TextInP .xml .xml #zField
Ts0 @TextInP .responsibility .responsibility #zField
Ts0 @UdInit f0 '' #zField
Ts0 @UdProcessEnd f1 '' #zField
Ts0 @UdMethod f6 '' #zField
Ts0 @UdProcessEnd f7 '' #zField
Ts0 @PushWFArc f2 '' #zField
Ts0 @PushWFArc f8 '' #zField
>Proto Ts0 Ts0 TaskWidgetConfigurationProcess #zField
Ts0 f0 guid 172C13E9818620F8 #txt
Ts0 f0 method start() #txt
Ts0 f0 inParameterDecl '<> param;' #txt
Ts0 f0 outParameterDecl '<> result;' #txt
Ts0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start()</name>
    </language>
</elementInfo>
' #txt
Ts0 f0 83 51 26 26 -16 15 #rect
Ts0 f0 @|UdInitIcon #fIcon
Ts0 f1 339 51 26 26 0 12 #rect
Ts0 f1 @|UdProcessEndIcon #fIcon
Ts0 f6 guid 172CBBC5DFC88030 #txt
Ts0 f6 method initialize(ch.ivy.addon.portalkit.dto.dashboard.TaskDashboardWidget) #txt
Ts0 f6 inParameterDecl '<ch.ivy.addon.portalkit.dto.dashboard.TaskDashboardWidget taskWidget> param;' #txt
Ts0 f6 inParameterMapAction 'out.taskWidget=param.taskWidget;
out.widgetId=param.taskWidget.id;
' #txt
Ts0 f6 outParameterDecl '<> result;' #txt
Ts0 f6 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>initialize(TaskDashboardWidget)</name>
    </language>
</elementInfo>
' #txt
Ts0 f6 83 147 26 26 -25 15 #rect
Ts0 f6 @|UdMethodIcon #fIcon
Ts0 f7 339 147 26 26 0 12 #rect
Ts0 f7 @|UdProcessEndIcon #fIcon
Ts0 f2 109 64 339 64 #arcP
Ts0 f8 109 160 339 160 #arcP
>Proto Ts0 .type ch.ivy.addon.portal.generic.dashboard.TaskWidgetConfiguration.TaskWidgetConfigurationData #txt
>Proto Ts0 .processKind HTML_DIALOG #txt
>Proto Ts0 -8 -8 16 16 16 26 #rect
>Proto Ts0 '' #fIcon
Ts0 f0 mainOut f2 tail #connect
Ts0 f2 head f1 mainIn #connect
Ts0 f6 mainOut f8 tail #connect
Ts0 f8 head f7 mainIn #connect
