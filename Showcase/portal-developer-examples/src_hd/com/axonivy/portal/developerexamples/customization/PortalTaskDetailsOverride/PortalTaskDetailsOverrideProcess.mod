[Ivy]
176CBB4874D688E6 9.3.0 #module
>Proto >Proto Collection #zClass
Ps0 PortalTaskDetailsOverrideProcess Big #zClass
Ps0 RD #cInfo
Ps0 #process
Ps0 @TextInP .type .type #zField
Ps0 @TextInP .processKind .processKind #zField
Ps0 @AnnotationInP-0n ai ai #zField
Ps0 @MessageFlowInP-0n messageIn messageIn #zField
Ps0 @MessageFlowOutP-0n messageOut messageOut #zField
Ps0 @TextInP .xml .xml #zField
Ps0 @TextInP .responsibility .responsibility #zField
Ps0 @UdInit f0 '' #zField
Ps0 @UdProcessEnd f4 '' #zField
Ps0 @UdEvent f3 '' #zField
Ps0 @UdExitEnd f1 '' #zField
Ps0 @PushWFArc f2 '' #zField
Ps0 @GridStep f6 '' #zField
Ps0 @PushWFArc f7 '' #zField
Ps0 @PushWFArc f5 '' #zField
Ps0 @UdInit f9 '' #zField
Ps0 @UdProcessEnd f10 '' #zField
Ps0 @GridStep f8 '' #zField
Ps0 @PushWFArc f12 '' #zField
Ps0 @PushWFArc f11 '' #zField
>Proto Ps0 Ps0 PortalTaskDetailsOverrideProcess #zField
Ps0 f0 guid 16C421FBB76376F6 #txt
Ps0 f0 method start(ch.ivyteam.ivy.workflow.ITask,ch.ivy.addon.portalkit.datamodel.TaskLazyDataModel,ch.ivy.addon.portalkit.enums.PortalPage,Boolean) #txt
Ps0 f0 inParameterDecl '<ch.ivyteam.ivy.workflow.ITask task,ch.ivy.addon.portalkit.datamodel.TaskLazyDataModel dataModel,ch.ivy.addon.portalkit.enums.PortalPage portalPage,Boolean isFromTaskList> param;' #txt
Ps0 f0 inParameterMapAction 'out.dataModel=param.dataModel;
out.isFromTaskList=param.isFromTaskList;
out.portalPage=param.portalPage;
out.task=param.task;
' #txt
Ps0 f0 outParameterDecl '<> result;' #txt
Ps0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start(ITask,TaskLazyDataModel,PortalPage,Boolean)</name>
    </language>
</elementInfo>
' #txt
Ps0 f0 83 83 26 26 -66 25 #rect
Ps0 f4 411 83 26 26 0 12 #rect
Ps0 f3 guid 16C45AE394A575BF #txt
Ps0 f3 actionTable 'out=in;
' #txt
Ps0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>close</name>
    </language>
</elementInfo>
' #txt
Ps0 f3 83 275 26 26 -15 12 #rect
Ps0 f1 211 275 26 26 0 12 #rect
Ps0 f2 expr out #txt
Ps0 f2 109 288 211 288 #arcP
Ps0 f6 actionTable 'out=in;
' #txt
Ps0 f6 actionCode 'import ch.ivy.addon.portalkit.enums.SessionAttribute;

in.isTaskStartedInDetails = ivy.session.getAttribute(SessionAttribute.IS_TASK_STARTED_IN_DETAILS.toString()) as Boolean;' #txt
Ps0 f6 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Check task is started</name>
    </language>
</elementInfo>
' #txt
Ps0 f6 192 74 128 44 -57 -8 #rect
Ps0 f7 expr out #txt
Ps0 f7 109 96 192 96 #arcP
Ps0 f5 320 96 411 96 #arcP
Ps0 f9 guid 17AEB168B5E6A631 #txt
Ps0 f9 method startFromDashboard(ch.ivyteam.ivy.workflow.ITask,ch.ivy.addon.portalkit.datamodel.DashboardTaskLazyDataModel,ch.ivy.addon.portalkit.enums.PortalPage,Boolean) #txt
Ps0 f9 inParameterDecl '<ch.ivyteam.ivy.workflow.ITask task,ch.ivy.addon.portalkit.datamodel.DashboardTaskLazyDataModel dataModel,ch.ivy.addon.portalkit.enums.PortalPage portalPage,Boolean isFromTaskList> param;' #txt
Ps0 f9 inParameterMapAction 'out.dashboardDataModel=param.dataModel;
out.isFromTaskList=param.isFromTaskList;
out.portalPage=param.portalPage;
out.task=param.task;
' #txt
Ps0 f9 outParameterDecl '<> result;' #txt
Ps0 f9 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>startFromDashboard(ITask,DashboardTaskLazyDataModel,PortalPage,Boolean)</name>
    </language>
</elementInfo>
' #txt
Ps0 f9 83 179 26 26 -79 25 #rect
Ps0 f10 339 179 26 26 0 12 #rect
Ps0 f8 actionTable 'out=in;
' #txt
Ps0 f8 actionCode 'import ch.ivy.addon.portalkit.enums.SessionAttribute;

in.isTaskStartedInDetails = ivy.session.getAttribute(SessionAttribute.IS_TASK_STARTED_IN_DETAILS.toString()) as Boolean;' #txt
Ps0 f8 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Check task is started</name>
    </language>
</elementInfo>
' #txt
Ps0 f8 160 170 128 44 -57 -8 #rect
Ps0 f12 288 192 339 192 #arcP
Ps0 f11 expr out #txt
Ps0 f11 109 192 160 192 #arcP
>Proto Ps0 .type com.axonivy.portal.developerexamples.customization.PortalTaskDetailsOverride.PortalTaskDetailsOverrideData #txt
>Proto Ps0 .processKind HTML_DIALOG #txt
>Proto Ps0 -8 -8 16 16 16 26 #rect
Ps0 f3 mainOut f2 tail #connect
Ps0 f2 head f1 mainIn #connect
Ps0 f0 mainOut f7 tail #connect
Ps0 f7 head f6 mainIn #connect
Ps0 f6 mainOut f5 tail #connect
Ps0 f5 head f4 mainIn #connect
Ps0 f9 mainOut f11 tail #connect
Ps0 f11 head f8 mainIn #connect
Ps0 f8 mainOut f12 tail #connect
Ps0 f12 head f10 mainIn #connect
