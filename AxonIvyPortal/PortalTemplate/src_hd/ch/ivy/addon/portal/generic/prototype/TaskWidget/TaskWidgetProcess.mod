[Ivy]
1706725B0593DA17 7.5.0 #module
>Proto >Proto Collection #zClass
Ts0 TaskWidgetProcess Big #zClass
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
Ts0 @CallSub f8 '' #zField
Ts0 @GridStep f13 '' #zField
Ts0 @PushWFArc f16 '' #zField
Ts0 @PushWFArc f9 '' #zField
Ts0 @PushWFArc f2 '' #zField
Ts0 @PushWFArc f10 '' #zField
>Proto Ts0 Ts0 TaskWidgetProcess #zField
Ts0 f0 guid 1706725B11A36A72 #txt
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
Ts0 f1 627 51 26 26 0 12 #rect
Ts0 f1 @|UdProcessEndIcon #fIcon
Ts0 f3 guid 1706725B136A149D #txt
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
Ts0 f6 guid 170672A73A55F17D #txt
Ts0 f6 method initialize(ch.ivy.addon.portalkit.dto.TaskDashboardWidget) #txt
Ts0 f6 inParameterDecl '<ch.ivy.addon.portalkit.dto.TaskDashboardWidget taskWidget> param;' #txt
Ts0 f6 inParameterMapAction 'out.taskWidget=param.taskWidget;
' #txt
Ts0 f6 outParameterDecl '<> result;' #txt
Ts0 f6 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>initialize(TaskDashboardWidget)</name>
    </language>
</elementInfo>
' #txt
Ts0 f6 83 243 26 26 -51 21 #rect
Ts0 f6 @|UdMethodIcon #fIcon
Ts0 f7 627 243 26 26 0 12 #rect
Ts0 f7 @|UdProcessEndIcon #fIcon
Ts0 f8 processCall 'Ivy Data Processes/TaskService:findTasksByCriteria(ch.ivy.addon.portalkit.ivydata.searchcriteria.TaskSearchCriteria,Integer,Integer)' #txt
Ts0 f8 requestActionDecl '<ch.ivy.addon.portalkit.ivydata.searchcriteria.TaskSearchCriteria taskSearchCriteria,Integer startIndex,Integer count> param;' #txt
Ts0 f8 requestMappingAction 'param.taskSearchCriteria=in.taskSearchCriteria;
param.taskSearchCriteria.adminQuery=true;
param.taskSearchCriteria.finalTaskQuery=in.taskQuery;
param.startIndex=0;
param.count=-1;
' #txt
Ts0 f8 responseMappingAction 'out=in;
out.tasks=result.tasks;
' #txt
Ts0 f8 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Ivy Data Processes/TaskService</name>
    </language>
</elementInfo>
' #txt
Ts0 f8 360 234 192 44 -87 -8 #rect
Ts0 f8 @|CallSubIcon #fIcon
Ts0 f13 actionTable 'out=in;
' #txt
Ts0 f13 actionCode 'import ch.ivy.addon.portalkit.dto.TaskDashboardWidget;
import ch.ivy.addon.portalkit.service.TaskDashboardWidgetService;

TaskDashboardWidgetService service = new TaskDashboardWidgetService();
in.taskQuery = service.loadTaskQuery(in.taskWidget);' #txt
Ts0 f13 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Initialize query</name>
    </language>
</elementInfo>
' #txt
Ts0 f13 168 234 112 44 -38 -8 #rect
Ts0 f13 @|StepIcon #fIcon
Ts0 f16 109 256 168 256 #arcP
Ts0 f9 280 256 360 256 #arcP
Ts0 f2 109 64 627 64 #arcP
Ts0 f10 552 256 627 256 #arcP
>Proto Ts0 .type ch.ivy.addon.portal.generic.prototype.TaskWidget.TaskWidgetData #txt
>Proto Ts0 .processKind HTML_DIALOG #txt
>Proto Ts0 -8 -8 16 16 16 26 #rect
>Proto Ts0 '' #fIcon
Ts0 f3 mainOut f5 tail #connect
Ts0 f5 head f4 mainIn #connect
Ts0 f6 mainOut f16 tail #connect
Ts0 f16 head f13 mainIn #connect
Ts0 f13 mainOut f9 tail #connect
Ts0 f9 head f8 mainIn #connect
Ts0 f0 mainOut f2 tail #connect
Ts0 f2 head f1 mainIn #connect
Ts0 f8 mainOut f10 tail #connect
Ts0 f10 head f7 mainIn #connect
