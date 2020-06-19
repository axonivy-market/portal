[Ivy]
172CB6279B7A43D2 7.5.0 #module
>Proto >Proto Collection #zClass
Ps0 PreviewTaskWidgetForCreationProcess Big #zClass
Ps0 RD #cInfo
Ps0 #process
Ps0 @TextInP .type .type #zField
Ps0 @TextInP .processKind .processKind #zField
Ps0 @TextInP .xml .xml #zField
Ps0 @TextInP .responsibility .responsibility #zField
Ps0 @CallSub f8 '' #zField
Ps0 @GridStep f13 '' #zField
Ps0 @UdEvent f3 '' #zField
Ps0 @UdProcessEnd f1 '' #zField
Ps0 @UdInit f0 '' #zField
Ps0 @UdMethod f6 '' #zField
Ps0 @UdExitEnd f4 '' #zField
Ps0 @UdProcessEnd f7 '' #zField
Ps0 @PushWFArc f16 '' #zField
Ps0 @PushWFArc f5 '' #zField
Ps0 @PushWFArc f9 '' #zField
Ps0 @PushWFArc f2 '' #zField
Ps0 @PushWFArc f10 '' #zField
>Proto Ps0 Ps0 PreviewTaskWidgetForCreationProcess #zField
Ps0 f8 processCall 'Ivy Data Processes/TaskService:findTasksByCriteria(ch.ivy.addon.portalkit.ivydata.searchcriteria.TaskSearchCriteria,Integer,Integer)' #txt
Ps0 f8 requestActionDecl '<ch.ivy.addon.portalkit.ivydata.searchcriteria.TaskSearchCriteria taskSearchCriteria,Integer startIndex,Integer count> param;' #txt
Ps0 f8 requestMappingAction 'param.taskSearchCriteria=in.taskSearchCriteria;
param.taskSearchCriteria.adminQuery=true;
param.taskSearchCriteria.finalTaskQuery=in.taskQuery;
param.startIndex=0;
param.count=-1;
' #txt
Ps0 f8 responseMappingAction 'out=in;
out.tasks=result.tasks;
' #txt
Ps0 f8 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Ivy Data Processes/TaskService</name>
    </language>
</elementInfo>
' #txt
Ps0 f8 328 234 192 44 -87 -8 #rect
Ps0 f8 @|CallSubIcon #fIcon
Ps0 f13 actionTable 'out=in;
' #txt
Ps0 f13 actionCode 'import ch.ivy.addon.portalkit.dto.TaskDashboardWidget;
import ch.ivy.addon.portalkit.service.TaskDashboardWidgetService;

TaskDashboardWidgetService service = new TaskDashboardWidgetService();
in.taskQuery = service.loadTaskQuery(in.taskWidget);' #txt
Ps0 f13 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Initialize query</name>
    </language>
</elementInfo>
' #txt
Ps0 f13 136 234 112 44 -38 -8 #rect
Ps0 f13 @|StepIcon #fIcon
Ps0 f3 guid 172C69283A971E4F #txt
Ps0 f3 actionTable 'out=in;
' #txt
Ps0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>close</name>
    </language>
</elementInfo>
' #txt
Ps0 f3 43 139 26 26 -15 15 #rect
Ps0 f3 @|UdEventIcon #fIcon
Ps0 f1 595 43 26 26 0 12 #rect
Ps0 f1 @|UdProcessEndIcon #fIcon
Ps0 f0 guid 172C69283A9429BC #txt
Ps0 f0 method start() #txt
Ps0 f0 inParameterDecl '<> param;' #txt
Ps0 f0 outParameterDecl '<> result;' #txt
Ps0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start()</name>
    </language>
</elementInfo>
' #txt
Ps0 f0 43 43 26 26 -16 15 #rect
Ps0 f0 @|UdInitIcon #fIcon
Ps0 f6 guid 172C69283A98F656 #txt
Ps0 f6 method initialize(ch.ivy.addon.portalkit.dto.TaskDashboardWidget) #txt
Ps0 f6 inParameterDecl '<ch.ivy.addon.portalkit.dto.TaskDashboardWidget taskWidget> param;' #txt
Ps0 f6 inParameterMapAction 'out.taskWidget=param.taskWidget;
' #txt
Ps0 f6 outParameterDecl '<> result;' #txt
Ps0 f6 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>initialize(TaskDashboardWidget)</name>
    </language>
</elementInfo>
' #txt
Ps0 f6 51 243 26 26 -51 21 #rect
Ps0 f6 @|UdMethodIcon #fIcon
Ps0 f4 171 139 26 26 0 12 #rect
Ps0 f4 @|UdExitEndIcon #fIcon
Ps0 f7 595 243 26 26 0 12 #rect
Ps0 f7 @|UdProcessEndIcon #fIcon
Ps0 f16 77 256 136 256 #arcP
Ps0 f5 69 152 171 152 #arcP
Ps0 f9 248 256 328 256 #arcP
Ps0 f2 69 56 595 56 #arcP
Ps0 f10 520 256 595 256 #arcP
>Proto Ps0 .type ch.ivy.addon.portal.generic.prototype.PreviewTaskWidgetForCreation.PreviewTaskWidgetForCreationData #txt
>Proto Ps0 .processKind HTML_DIALOG #txt
>Proto Ps0 -8 -8 16 16 16 26 #rect
>Proto Ps0 '' #fIcon
Ps0 f3 mainOut f5 tail #connect
Ps0 f5 head f4 mainIn #connect
Ps0 f6 mainOut f16 tail #connect
Ps0 f16 head f13 mainIn #connect
Ps0 f13 mainOut f9 tail #connect
Ps0 f9 head f8 mainIn #connect
Ps0 f0 mainOut f2 tail #connect
Ps0 f2 head f1 mainIn #connect
Ps0 f8 mainOut f10 tail #connect
Ps0 f10 head f7 mainIn #connect
