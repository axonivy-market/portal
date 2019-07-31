[Ivy]
16C421E0556A3ED7 3.26 #module
>Proto >Proto Collection #zClass
Os0 OpenPortalCaseDetails Big #zClass
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
Os0 @EndSub f1 '' #zField
Os0 @RichDialog f3 '' #zField
Os0 @PushWFArc f2 '' #zField
Os0 @StartSub f0 '' #zField
Os0 @PushWFArc f4 '' #zField
>Proto Os0 Os0 OpenPortalCaseDetails #zField
Os0 f1 type _ch.ivyteam.ivy.project.portal.examples.OpenPortalTaskDetailsOverrideData #txt
Os0 f1 337 49 30 30 0 15 #rect
Os0 f1 @|EndSubIcon #fIcon
Os0 f3 richDialogId ch.ivyteam.ivy.project.portal.examples.component.customize.TaskItemDetails #txt
Os0 f3 startMethod start(ch.ivyteam.ivy.workflow.ITask,String) #txt
Os0 f3 type _ch.ivyteam.ivy.project.portal.examples.OpenPortalTaskDetailsOverrideData #txt
Os0 f3 requestActionDecl '<ch.ivyteam.ivy.workflow.ITask task,String menuState> param;' #txt
Os0 f3 requestMappingAction 'param.task=in.task;
param.menuState=ch.ivy.addon.portalkit.util.MenuUtils.getMenuState();
' #txt
Os0 f3 responseActionDecl '_ch.ivyteam.ivy.project.portal.examples.OpenPortalTaskDetailsOverrideData out;
' #txt
Os0 f3 responseMappingAction 'out=in;
' #txt
Os0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Open portal task detail</name>
    </language>
</elementInfo>
' #txt
Os0 f3 160 42 128 44 -61 -8 #rect
Os0 f3 @|RichDialogIcon #fIcon
Os0 f2 expr out #txt
Os0 f2 288 64 337 64 #arcP
Os0 f0 inParamDecl '<ch.ivyteam.ivy.workflow.ITask taskData> param;' #txt
Os0 f0 inParamTable 'out.task=param.taskData;
' #txt
Os0 f0 outParamDecl '<> result;' #txt
Os0 f0 actionDecl '_ch.ivyteam.ivy.project.portal.examples.OpenPortalTaskDetailsOverrideData out;
' #txt
Os0 f0 callSignature call(ch.ivyteam.ivy.workflow.ITask) #txt
Os0 f0 type _ch.ivyteam.ivy.project.portal.examples.OpenPortalTaskDetailsOverrideData #txt
Os0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>call(ITask)</name>
    </language>
</elementInfo>
' #txt
Os0 f0 65 49 30 30 0 15 #rect
Os0 f0 @|StartSubIcon #fIcon
Os0 f4 expr out #txt
Os0 f4 95 64 160 64 #arcP
>Proto Os0 .type _ch.ivyteam.ivy.project.portal.examples.OpenPortalTaskDetailsOverrideData #txt
>Proto Os0 .processKind CALLABLE_SUB #txt
>Proto Os0 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language/>
</elementInfo>
' #txt
>Proto Os0 0 0 32 24 18 0 #rect
>Proto Os0 @|BIcon #fIcon
Os0 f3 mainOut f2 tail #connect
Os0 f2 head f1 mainIn #connect
Os0 f0 mainOut f4 tail #connect
Os0 f4 head f3 mainIn #connect
