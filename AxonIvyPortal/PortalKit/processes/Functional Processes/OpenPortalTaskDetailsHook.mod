[Ivy]
16D289860FF9CFCA 9.3.1 #module
>Proto >Proto Collection #zClass
Os0 OpenPortalTaskDetailsHook Big #zClass
Os0 B #cInfo
Os0 #process
Os0 @TextInP .type .type #zField
Os0 @TextInP .processKind .processKind #zField
Os0 @AnnotationInP-0n ai ai #zField
Os0 @MessageFlowInP-0n messageIn messageIn #zField
Os0 @MessageFlowOutP-0n messageOut messageOut #zField
Os0 @TextInP .xml .xml #zField
Os0 @TextInP .responsibility .responsibility #zField
Os0 @StartSub f0 '' #zField
Os0 @EndSub f1 '' #zField
Os0 @PushWFArc f2 '' #zField
Os0 @EndSub f3 '' #zField
Os0 @StartSub f4 '' #zField
Os0 @PushWFArc f5 '' #zField
Os0 @EndSub f6 '' #zField
Os0 @StartSub f7 '' #zField
Os0 @PushWFArc f8 '' #zField
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
Os0 f0 81 49 30 30 -64 15 #rect
Os0 f1 337 49 30 30 0 15 #rect
Os0 f2 expr out #txt
Os0 f2 111 64 337 64 #arcP
Os0 f3 337 145 30 30 0 15 #rect
Os0 f4 inParamDecl '<ch.ivyteam.ivy.workflow.ITask task,ch.ivy.addon.portalkit.datamodel.TaskLazyDataModel dataModel,ch.ivy.addon.portalkit.enums.PortalPage portalPage,Boolean isFromTaskList> param;' #txt
Os0 f4 inParamTable 'out.dataModel=param.dataModel;
out.isFromTaskList=param.#isFromTaskList is initialized ? param.isFromTaskList : true;
out.portalPage=param.portalPage;
out.task=param.task;
' #txt
Os0 f4 outParamDecl '<> result;' #txt
Os0 f4 callSignature callInFrame(ch.ivyteam.ivy.workflow.ITask,ch.ivy.addon.portalkit.datamodel.TaskLazyDataModel,ch.ivy.addon.portalkit.enums.PortalPage,Boolean) #txt
Os0 f4 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>callInFrame(ITask,TaskLazyDataModel,PortalPage,Boolean)</name>
    </language>
</elementInfo>
' #txt
Os0 f4 81 145 30 30 -64 15 #rect
Os0 f5 expr out #txt
Os0 f5 111 160 337 160 #arcP
Os0 f6 337 273 30 30 0 15 #rect
Os0 f7 inParamDecl '<ch.ivyteam.ivy.workflow.ITask task,ch.ivy.addon.portalkit.datamodel.DashboardTaskLazyDataModel dataModel,ch.ivy.addon.portalkit.enums.PortalPage portalPage,Boolean isFromTaskList> param;' #txt
Os0 f7 inParamTable 'out.dashboardDataModel=param.dataModel;
out.isFromTaskList=param.#isFromTaskList is initialized ? param.isFromTaskList : true;
out.portalPage=param.portalPage;
out.task=param.task;
' #txt
Os0 f7 outParamDecl '<> result;' #txt
Os0 f7 callSignature callFromDashboard(ch.ivyteam.ivy.workflow.ITask,ch.ivy.addon.portalkit.datamodel.DashboardTaskLazyDataModel,ch.ivy.addon.portalkit.enums.PortalPage,Boolean) #txt
Os0 f7 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>callFromDashboard(ITask,DashboardTaskLazyDataModel,PortalPage,Boolean)</name>
    </language>
</elementInfo>
' #txt
Os0 f7 81 273 30 30 -64 15 #rect
Os0 f8 expr out #txt
Os0 f8 111 288 337 288 #arcP
>Proto Os0 .type ch.ivy.add.portalkit.OpenPortalTaskDetailsData #txt
>Proto Os0 .processKind CALLABLE_SUB #txt
>Proto Os0 0 0 32 24 18 0 #rect
>Proto Os0 @|BIcon #fIcon
Os0 f0 mainOut f2 tail #connect
Os0 f2 head f1 mainIn #connect
Os0 f4 mainOut f5 tail #connect
Os0 f5 head f3 mainIn #connect
Os0 f7 mainOut f8 tail #connect
Os0 f8 head f6 mainIn #connect
