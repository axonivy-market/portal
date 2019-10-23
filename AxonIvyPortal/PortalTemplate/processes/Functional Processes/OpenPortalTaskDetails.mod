[Ivy]
16D28A867A2A1802 7.5.0 #module
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
Os0 f0 @|StartSubIcon #fIcon
Os0 f1 433 49 30 30 0 15 #rect
Os0 f1 @|EndSubIcon #fIcon
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
Os0 f2 184 42 112 44 -49 -8 #rect
Os0 f2 @|UserDialogIcon #fIcon
Os0 f3 111 64 184 64 #arcP
Os0 f4 296 64 433 64 #arcP
>Proto Os0 .type ch.ivy.addon.portal.generic.OpenPortalTaskDetailsData #txt
>Proto Os0 .processKind CALLABLE_SUB #txt
>Proto Os0 0 0 32 24 18 0 #rect
>Proto Os0 @|BIcon #fIcon
Os0 f0 mainOut f3 tail #connect
Os0 f3 head f2 mainIn #connect
Os0 f2 mainOut f4 tail #connect
Os0 f4 head f1 mainIn #connect
