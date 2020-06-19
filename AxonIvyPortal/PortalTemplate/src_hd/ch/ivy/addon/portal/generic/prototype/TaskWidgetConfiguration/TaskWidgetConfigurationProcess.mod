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
Ts0 @PushWFArc f2 '' #zField
Ts0 @UdEvent f3 '' #zField
Ts0 @UdExitEnd f4 '' #zField
Ts0 @PushWFArc f5 '' #zField
Ts0 @UdMethod f6 '' #zField
Ts0 @UdProcessEnd f7 '' #zField
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
Ts0 f1 211 51 26 26 0 12 #rect
Ts0 f1 @|UdProcessEndIcon #fIcon
Ts0 f2 109 64 211 64 #arcP
Ts0 f3 guid 172C13E982ACE78D #txt
Ts0 f3 actionTable 'out=in;
' #txt
Ts0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>close</name>
    </language>
</elementInfo>
' #txt
Ts0 f3 83 147 26 26 -15 15 #rect
Ts0 f3 @|UdEventIcon #fIcon
Ts0 f4 211 147 26 26 0 12 #rect
Ts0 f4 @|UdExitEndIcon #fIcon
Ts0 f5 109 160 211 160 #arcP
Ts0 f6 guid 172CBBC5DFC88030 #txt
Ts0 f6 method initialize(ch.ivy.addon.portalkit.dto.TaskDashboardWidget) #txt
Ts0 f6 inParameterDecl '<ch.ivy.addon.portalkit.dto.TaskDashboardWidget taskWidget> param;' #txt
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
Ts0 f6 83 243 26 26 -25 15 #rect
Ts0 f6 @|UdMethodIcon #fIcon
Ts0 f7 307 243 26 26 0 12 #rect
Ts0 f7 @|UdProcessEndIcon #fIcon
Ts0 f8 109 256 307 256 #arcP
>Proto Ts0 .type ch.ivy.addon.portal.generic.prototype.TaskWidgetConfiguration.TaskWidgetConfigurationData #txt
>Proto Ts0 .processKind HTML_DIALOG #txt
>Proto Ts0 -8 -8 16 16 16 26 #rect
>Proto Ts0 '' #fIcon
Ts0 f0 mainOut f2 tail #connect
Ts0 f2 head f1 mainIn #connect
Ts0 f3 mainOut f5 tail #connect
Ts0 f5 head f4 mainIn #connect
Ts0 f6 mainOut f8 tail #connect
Ts0 f8 head f7 mainIn #connect
