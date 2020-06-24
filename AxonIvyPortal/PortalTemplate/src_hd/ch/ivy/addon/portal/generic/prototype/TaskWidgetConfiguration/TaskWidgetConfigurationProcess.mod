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
Ts0 @UdEvent f3 '' #zField
Ts0 @UdExitEnd f4 '' #zField
Ts0 @PushWFArc f5 '' #zField
Ts0 @UdMethod f6 '' #zField
Ts0 @UdProcessEnd f7 '' #zField
Ts0 @PushWFArc f2 '' #zField
Ts0 @PushWFArc f8 '' #zField
Ts0 @UdMethod f9 '' #zField
Ts0 @GridStep f10 '' #zField
Ts0 @UdProcessEnd f11 '' #zField
Ts0 @PushWFArc f12 '' #zField
Ts0 @PushWFArc f13 '' #zField
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
Ts0 f6 inParameterMapAction 'out.selectedTaskListType=param.taskWidget.taskDashboardWidgetType.name();
out.taskWidget=param.taskWidget;
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
Ts0 f7 339 243 26 26 0 12 #rect
Ts0 f7 @|UdProcessEndIcon #fIcon
Ts0 f2 109 64 339 64 #arcP
Ts0 f8 109 256 339 256 #arcP
Ts0 f9 guid 172E03948FE42FE1 #txt
Ts0 f9 method updateType() #txt
Ts0 f9 inParameterDecl '<> param;' #txt
Ts0 f9 outParameterDecl '<> result;' #txt
Ts0 f9 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>updateType()</name>
    </language>
</elementInfo>
' #txt
Ts0 f9 83 339 26 26 -25 15 #rect
Ts0 f9 @|UdMethodIcon #fIcon
Ts0 f10 actionTable 'out=in;
' #txt
Ts0 f10 actionCode 'import ch.ivy.addon.portal.generic.bean.TaskWidgetConfigurationPrototypeBean;
import ch.ivy.addon.portalkit.jsf.ManagedBeans;
import ch.ivy.addon.portalkit.enums.TaskDashboardWidgetType;

in.taskWidget.taskDashboardWidgetType = TaskDashboardWidgetType.valueOf(in.selectedTaskListType);

TaskWidgetConfigurationPrototypeBean bean = ManagedBeans.get("taskWidgetConfigurationPrototypeBean") as TaskWidgetConfigurationPrototypeBean;
bean.clearTaskFilters(in.taskWidget);' #txt
Ts0 f10 168 330 112 44 0 -8 #rect
Ts0 f10 @|StepIcon #fIcon
Ts0 f11 371 339 26 26 0 12 #rect
Ts0 f11 @|UdProcessEndIcon #fIcon
Ts0 f12 109 352 168 352 #arcP
Ts0 f13 280 352 371 352 #arcP
>Proto Ts0 .type ch.ivy.addon.portal.generic.prototype.TaskWidgetConfiguration.TaskWidgetConfigurationData #txt
>Proto Ts0 .processKind HTML_DIALOG #txt
>Proto Ts0 -8 -8 16 16 16 26 #rect
>Proto Ts0 '' #fIcon
Ts0 f3 mainOut f5 tail #connect
Ts0 f5 head f4 mainIn #connect
Ts0 f0 mainOut f2 tail #connect
Ts0 f2 head f1 mainIn #connect
Ts0 f6 mainOut f8 tail #connect
Ts0 f8 head f7 mainIn #connect
Ts0 f9 mainOut f12 tail #connect
Ts0 f12 head f10 mainIn #connect
Ts0 f10 mainOut f13 tail #connect
Ts0 f13 head f11 mainIn #connect
