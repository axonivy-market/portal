[Ivy]
172CAF291EB6C4D8 7.5.0 #module
>Proto >Proto Collection #zClass
Cs0 CreateTaskWidgetDialogProcess Big #zClass
Cs0 RD #cInfo
Cs0 #process
Cs0 @TextInP .type .type #zField
Cs0 @TextInP .processKind .processKind #zField
Cs0 @TextInP .xml .xml #zField
Cs0 @TextInP .responsibility .responsibility #zField
Cs0 @UdInit f0 '' #zField
Cs0 @UdProcessEnd f1 '' #zField
Cs0 @PushWFArc f2 '' #zField
Cs0 @UdEvent f3 '' #zField
Cs0 @UdExitEnd f4 '' #zField
Cs0 @PushWFArc f5 '' #zField
Cs0 @UdMethod f6 '' #zField
Cs0 @UdProcessEnd f7 '' #zField
Cs0 @GridStep f9 '' #zField
Cs0 @PushWFArc f10 '' #zField
Cs0 @PushWFArc f8 '' #zField
Cs0 @UdMethod f11 '' #zField
Cs0 @GridStep f12 '' #zField
Cs0 @UdProcessEnd f13 '' #zField
Cs0 @PushWFArc f14 '' #zField
Cs0 @PushWFArc f15 '' #zField
Cs0 @UdMethod f16 '' #zField
Cs0 @GridStep f18 '' #zField
Cs0 @PushWFArc f19 '' #zField
Cs0 @PushWFArc f21 '' #zField
>Proto Cs0 Cs0 CreateTaskWidgetDialogProcess #zField
Cs0 f0 guid 172CAF291F5C429A #txt
Cs0 f0 method start() #txt
Cs0 f0 inParameterDecl '<> param;' #txt
Cs0 f0 outParameterDecl '<> result;' #txt
Cs0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start()</name>
    </language>
</elementInfo>
' #txt
Cs0 f0 83 51 26 26 -16 15 #rect
Cs0 f0 @|UdInitIcon #fIcon
Cs0 f1 211 51 26 26 0 12 #rect
Cs0 f1 @|UdProcessEndIcon #fIcon
Cs0 f2 109 64 211 64 #arcP
Cs0 f3 guid 172CAF291FE24DF8 #txt
Cs0 f3 actionTable 'out=in;
' #txt
Cs0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>close</name>
    </language>
</elementInfo>
' #txt
Cs0 f3 83 147 26 26 -15 15 #rect
Cs0 f3 @|UdEventIcon #fIcon
Cs0 f4 211 147 26 26 0 12 #rect
Cs0 f4 @|UdExitEndIcon #fIcon
Cs0 f5 109 160 211 160 #arcP
Cs0 f6 guid 172CC049A51294A8 #txt
Cs0 f6 method initialize() #txt
Cs0 f6 inParameterDecl '<> param;' #txt
Cs0 f6 outParameterDecl '<> result;' #txt
Cs0 f6 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>initialize()</name>
    </language>
</elementInfo>
' #txt
Cs0 f6 83 243 26 26 -25 15 #rect
Cs0 f6 @|UdMethodIcon #fIcon
Cs0 f7 339 243 26 26 0 12 #rect
Cs0 f7 @|UdProcessEndIcon #fIcon
Cs0 f9 actionTable 'out=in;
' #txt
Cs0 f9 actionCode 'import ch.ivy.addon.portal.generic.bean.DashboardBean;
import ch.ivy.addon.portalkit.jsf.ManagedBeans;

DashboardBean bean = ManagedBeans.get("dashboardBean") as DashboardBean;
in.widget = bean.getDefaultTaskDashboardWidget();
in.widget.id = null;
in.selectedTaskListType = in.widget.taskDashboardWidgetType.name();' #txt
Cs0 f9 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Init new task widget</name>
    </language>
</elementInfo>
' #txt
Cs0 f9 168 234 112 44 -53 -8 #rect
Cs0 f9 @|StepIcon #fIcon
Cs0 f10 109 256 168 256 #arcP
Cs0 f8 280 256 339 256 #arcP
Cs0 f11 guid 172E02FDA7470B65 #txt
Cs0 f11 method updateType() #txt
Cs0 f11 inParameterDecl '<> param;' #txt
Cs0 f11 outParameterDecl '<> result;' #txt
Cs0 f11 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>updateType()</name>
    </language>
</elementInfo>
' #txt
Cs0 f11 83 467 26 26 -25 15 #rect
Cs0 f11 @|UdMethodIcon #fIcon
Cs0 f12 actionTable 'out=in;
' #txt
Cs0 f12 actionCode 'import ch.ivy.addon.portalkit.jsf.ManagedBeans;
import ch.ivy.addon.portal.generic.bean.TaskWidgetConfigurationPrototypeBean;
import ch.ivy.addon.portalkit.enums.TaskDashboardWidgetType;

in.widget.taskDashboardWidgetType = TaskDashboardWidgetType.valueOf(in.selectedTaskListType);

TaskWidgetConfigurationPrototypeBean bean = ManagedBeans.get("taskWidgetConfigurationPrototypeBean") as TaskWidgetConfigurationPrototypeBean;
bean.clearTaskFilters(in.widget);' #txt
Cs0 f12 168 458 112 44 0 -8 #rect
Cs0 f12 @|StepIcon #fIcon
Cs0 f13 339 467 26 26 0 12 #rect
Cs0 f13 @|UdProcessEndIcon #fIcon
Cs0 f14 109 480 168 480 #arcP
Cs0 f15 280 480 339 480 #arcP
Cs0 f16 guid 172E44464E4557A6 #txt
Cs0 f16 method createTaskWidget() #txt
Cs0 f16 inParameterDecl '<> param;' #txt
Cs0 f16 outParameterDecl '<> result;' #txt
Cs0 f16 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>createTaskWidget()</name>
    </language>
</elementInfo>
' #txt
Cs0 f16 83 339 26 26 -47 23 #rect
Cs0 f16 @|UdMethodIcon #fIcon
Cs0 f18 actionTable 'out=in;
' #txt
Cs0 f18 actionCode 'import ch.ivy.addon.portal.generic.bean.TaskWidgetConfigurationPrototypeBean;
import ch.ivy.addon.portalkit.jsf.ManagedBeans;

TaskWidgetConfigurationPrototypeBean bean = ManagedBeans.get("taskWidgetConfigurationPrototypeBean") as TaskWidgetConfigurationPrototypeBean;
bean.createTaskDashboardWidget(in.widget);' #txt
Cs0 f18 security system #txt
Cs0 f18 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Create task widget</name>
    </language>
</elementInfo>
' #txt
Cs0 f18 168 330 112 44 -51 -8 #rect
Cs0 f18 @|StepIcon #fIcon
Cs0 f19 109 352 168 352 #arcP
Cs0 f21 224 330 224 278 #arcP
>Proto Cs0 .type ch.ivy.addon.portal.generic.prototype.CreateTaskWidgetDialog.CreateTaskWidgetDialogData #txt
>Proto Cs0 .processKind HTML_DIALOG #txt
>Proto Cs0 -8 -8 16 16 16 26 #rect
>Proto Cs0 '' #fIcon
Cs0 f0 mainOut f2 tail #connect
Cs0 f2 head f1 mainIn #connect
Cs0 f3 mainOut f5 tail #connect
Cs0 f5 head f4 mainIn #connect
Cs0 f6 mainOut f10 tail #connect
Cs0 f10 head f9 mainIn #connect
Cs0 f9 mainOut f8 tail #connect
Cs0 f8 head f7 mainIn #connect
Cs0 f11 mainOut f14 tail #connect
Cs0 f14 head f12 mainIn #connect
Cs0 f12 mainOut f15 tail #connect
Cs0 f15 head f13 mainIn #connect
Cs0 f16 mainOut f19 tail #connect
Cs0 f19 head f18 mainIn #connect
Cs0 f18 mainOut f21 tail #connect
Cs0 f21 head f9 mainIn #connect
