[Ivy]
16D28A867A2A1802 9.4.0 #module
>Proto >Proto Collection #zClass
Os0 OpenPortalTaskDetailsHook Big #zClass
Os0 B #cInfo
Os0 #process
Os0 @TextInP .resExport .resExport #zField
Os0 @TextInP .type .type #zField
Os0 @TextInP .processKind .processKind #zField
Os0 @AnnotationInP-0n ai ai #zField
Os0 @MessageFlowInP-0n messageIn messageIn #zField
Os0 @MessageFlowOutP-0n messageOut messageOut #zField
Os0 @TextInP .xml .xml #zField
Os0 @TextInP .responsibility .responsibility #zField
Os0 @StartSub f0 '' #zField
Os0 @EndSub f1 '' #zField
Os0 @UserDialog f2 '' #zField
Os0 @PushWFArc f3 '' #zField
Os0 @PushWFArc f4 '' #zField
Os0 @StartSub f5 '' #zField
Os0 @EndSub f6 '' #zField
Os0 @UserDialog f7 '' #zField
Os0 @PushWFArc f8 '' #zField
Os0 @PushWFArc f9 '' #zField
Os0 @InfoButton f10 '' #zField
Os0 @InfoButton f12 '' #zField
Os0 @AnnotationArc f13 '' #zField
Os0 @AnnotationArc f11 '' #zField
Os0 @StartSub f14 '' #zField
Os0 @InfoButton f15 '' #zField
Os0 @EndSub f16 '' #zField
Os0 @UserDialog f17 '' #zField
Os0 @PushWFArc f18 '' #zField
Os0 @PushWFArc f19 '' #zField
>Proto Os0 Os0 OpenPortalTaskDetailsHook #zField
Os0 f0 inParamDecl '<ch.ivyteam.ivy.workflow.ITask task,ch.ivy.addon.portalkit.datamodel.TaskLazyDataModel dataModel,ch.ivy.addon.portalkit.enums.PortalPage portalPage,Boolean isFromTaskList> param;' #txt
Os0 f0 inParamTable 'out.dataModel=param.dataModel;
out.isFromTaskList=param.#isFromTaskList is initialized ? param.isFromTaskList : true;
out.portalPage=param.portalPage;
out.task=param.task;
' #txt
Os0 f0 outParamDecl '<> result;' #txt
Os0 f0 callSignature call(ch.ivyteam.ivy.workflow.ITask,ch.ivy.addon.portalkit.datamodel.TaskLazyDataModel,ch.ivy.addon.portalkit.enums.PortalPage,Boolean) #txt
Os0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>call(ITask,TaskLazyDataModel,PortalPage,Boolean)</name>
    </language>
</elementInfo>
' #txt
Os0 f0 81 113 30 30 -43 -36 #rect
Os0 f1 753 113 30 30 0 15 #rect
Os0 f2 dialogId ch.ivy.addon.portal.generic.PortalTaskDetails #txt
Os0 f2 startMethod start(ch.ivyteam.ivy.workflow.ITask,ch.ivy.addon.portalkit.datamodel.TaskLazyDataModel,ch.ivy.addon.portalkit.enums.PortalPage,Boolean) #txt
Os0 f2 requestActionDecl '<ch.ivyteam.ivy.workflow.ITask task,ch.ivy.addon.portalkit.datamodel.TaskLazyDataModel dataModel,ch.ivy.addon.portalkit.enums.PortalPage portalPage,Boolean isFromTaskList> param;' #txt
Os0 f2 requestMappingAction 'param.task=in.task;
param.dataModel=in.dataModel;
param.portalPage=in.portalPage;
param.isFromTaskList=in.isFromTaskList;
' #txt
Os0 f2 responseMappingAction 'out=in;
' #txt
Os0 f2 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>PortalTaskDetails</name>
    </language>
</elementInfo>
' #txt
Os0 f2 400 106 112 44 -49 -8 #rect
Os0 f3 111 128 400 128 #arcP
Os0 f4 512 128 753 128 #arcP
Os0 f5 inParamDecl '<ch.ivyteam.ivy.workflow.ITask task,ch.ivy.addon.portalkit.datamodel.TaskLazyDataModel dataModel,ch.ivy.addon.portalkit.enums.PortalPage portalPage,Boolean isFromTaskList> param;' #txt
Os0 f5 inParamTable 'out.dataModel=param.dataModel;
out.isFromTaskList=param.#isFromTaskList is initialized ? param.isFromTaskList : true;
out.portalPage=param.portalPage;
out.task=param.task;
' #txt
Os0 f5 outParamDecl '<> result;' #txt
Os0 f5 callSignature callInFrame(ch.ivyteam.ivy.workflow.ITask,ch.ivy.addon.portalkit.datamodel.TaskLazyDataModel,ch.ivy.addon.portalkit.enums.PortalPage,Boolean) #txt
Os0 f5 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>callInFrame(ITask,TaskLazyDataModel,PortalPage,Boolean)</name>
    </language>
</elementInfo>
' #txt
Os0 f5 81 465 30 30 -74 -36 #rect
Os0 f6 753 465 30 30 0 15 #rect
Os0 f7 dialogId ch.ivy.addon.portal.component.iframe.PortalTaskItemDetailsInIFrame #txt
Os0 f7 startMethod start(ch.ivyteam.ivy.workflow.ITask,ch.ivy.addon.portalkit.datamodel.TaskLazyDataModel,ch.ivy.addon.portalkit.enums.PortalPage,Boolean) #txt
Os0 f7 requestActionDecl '<ch.ivyteam.ivy.workflow.ITask task,ch.ivy.addon.portalkit.datamodel.TaskLazyDataModel model,ch.ivy.addon.portalkit.enums.PortalPage portalPage,Boolean isFromTaskList> param;' #txt
Os0 f7 requestMappingAction 'param.task=in.task;
param.model=in.dataModel;
param.portalPage=in.portalPage;
param.isFromTaskList=in.isFromTaskList;
' #txt
Os0 f7 responseMappingAction 'out=in;
' #txt
Os0 f7 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>PortalTaskItemDetailsInIFrame</name>
    </language>
</elementInfo>
' #txt
Os0 f7 368 458 176 44 -85 -8 #rect
Os0 f8 111 480 368 480 #arcP
Os0 f9 544 480 753 480 #arcP
Os0 f10 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>**Deprecated** **Note**&#13;
&#13;
**This callable will be removed from Portal 11.**&#13;
To override Portal task item details UI: you should use Axon Ivy HTMLOverride Dialog to override the PortalTaskDetails Html dialog.</name>
    </language>
</elementInfo>
' #txt
Os0 f10 160 202 720 76 -354 -34 #rect
Os0 f12 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>**Deprecated** **Note**&#13;
&#13;
**This callable will be removed from Portal 11.**&#13;
To override Portal task item details UI: you should use Axon Ivy HTMLOverride Dialog to override the PortalTaskItemDetailsInIFrame Html dialog.</name>
    </language>
</elementInfo>
' #txt
Os0 f12 152 554 800 76 -391 -34 #rect
Os0 f13 152 592 102 493 #arcP
Os0 f11 160 240 103 141 #arcP
Os0 f14 inParamDecl '<ch.ivyteam.ivy.workflow.ITask task,ch.ivy.addon.portalkit.datamodel.DashboardTaskLazyDataModel dataModel,ch.ivy.addon.portalkit.enums.PortalPage portalPage,Boolean isFromTaskList> param;' #txt
Os0 f14 inParamTable 'out.dashboardDataModel=param.dataModel;
out.isFromTaskList=param.#isFromTaskList is initialized ? param.isFromTaskList : true;
out.portalPage=param.portalPage;
out.task=param.task;
' #txt
Os0 f14 outParamDecl '<> result;' #txt
Os0 f14 callSignature callFromDashboard(ch.ivyteam.ivy.workflow.ITask,ch.ivy.addon.portalkit.datamodel.DashboardTaskLazyDataModel,ch.ivy.addon.portalkit.enums.PortalPage,Boolean) #txt
Os0 f14 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>callFromDashboard(ITask,DashboardTaskLazyDataModel,PortalPage,Boolean)</name>
    </language>
</elementInfo>
' #txt
Os0 f14 1041 113 30 30 -95 -42 #rect
Os0 f15 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>**Deprecated** **Note**&#13;
&#13;
**This callable will be removed from Portal 11.**&#13;
To override Portal task item details UI: you should use Axon.ivy HTMLOverride Dialog to override the PortalTaskDetails Html dialog.</name>
    </language>
</elementInfo>
' #txt
Os0 f15 1120 202 720 76 -354 -34 #rect
Os0 f16 1713 113 30 30 0 15 #rect
Os0 f17 dialogId ch.ivy.addon.portal.generic.PortalTaskDetails #txt
Os0 f17 startMethod startFromDashboard(ch.ivyteam.ivy.workflow.ITask,ch.ivy.addon.portalkit.datamodel.DashboardTaskLazyDataModel,ch.ivy.addon.portalkit.enums.PortalPage,Boolean) #txt
Os0 f17 requestActionDecl '<ch.ivyteam.ivy.workflow.ITask task,ch.ivy.addon.portalkit.datamodel.DashboardTaskLazyDataModel dataModel,ch.ivy.addon.portalkit.enums.PortalPage portalPage,Boolean isFromTaskList> param;' #txt
Os0 f17 requestMappingAction 'param.task=in.task;
param.dataModel=in.dashboardDataModel;
param.portalPage=in.portalPage;
param.isFromTaskList=in.isFromTaskList;
' #txt
Os0 f17 responseMappingAction 'out=in;
' #txt
Os0 f17 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>PortalTaskDetails</name>
    </language>
</elementInfo>
' #txt
Os0 f17 1360 106 112 44 -49 -8 #rect
Os0 f18 1071 128 1360 128 #arcP
Os0 f19 1472 128 1713 128 #arcP
>Proto Os0 .type ch.ivy.addon.portal.generic.OpenPortalTaskDetailsData #txt
>Proto Os0 .processKind CALLABLE_SUB #txt
>Proto Os0 0 0 32 24 18 0 #rect
>Proto Os0 @|BIcon #fIcon
Os0 f0 mainOut f3 tail #connect
Os0 f3 head f2 mainIn #connect
Os0 f2 mainOut f4 tail #connect
Os0 f4 head f1 mainIn #connect
Os0 f5 mainOut f8 tail #connect
Os0 f8 head f7 mainIn #connect
Os0 f7 mainOut f9 tail #connect
Os0 f9 head f6 mainIn #connect
Os0 f12 ao f13 tail #connect
Os0 f13 head f5 @CG|ai #connect
Os0 f10 ao f11 tail #connect
Os0 f11 head f0 @CG|ai #connect
Os0 f14 mainOut f18 tail #connect
Os0 f18 head f17 mainIn #connect
Os0 f17 mainOut f19 tail #connect
Os0 f19 head f16 mainIn #connect
