[Ivy]
16BF45FE92EEA6A7 3.26 #module
>Proto >Proto Collection #zClass
Ol0 OpenPortalTaskDetail Big #zClass
Ol0 B #cInfo
Ol0 #process
Ol0 @TextInP .resExport .resExport #zField
Ol0 @TextInP .type .type #zField
Ol0 @TextInP .processKind .processKind #zField
Ol0 @AnnotationInP-0n ai ai #zField
Ol0 @MessageFlowInP-0n messageIn messageIn #zField
Ol0 @MessageFlowOutP-0n messageOut messageOut #zField
Ol0 @TextInP .xml .xml #zField
Ol0 @TextInP .responsibility .responsibility #zField
Ol0 @StartSub f0 '' #zField
Ol0 @EndSub f1 '' #zField
Ol0 @RichDialog f7 '' #zField
Ol0 @PushWFArc f6 '' #zField
Ol0 @PushWFArc f2 '' #zField
>Proto Ol0 Ol0 OpenPortalTaskDetail #zField
Ol0 f0 inParamDecl '<ch.ivyteam.ivy.workflow.ITask taskData> param;' #txt
Ol0 f0 inParamTable 'out.taskView=param.taskData;
' #txt
Ol0 f0 outParamDecl '<> result;
' #txt
Ol0 f0 actionDecl 'ch.ivy.addon.portal.generic.OpenPortalTaskDetailsData out;
' #txt
Ol0 f0 callSignature call(ch.ivyteam.ivy.workflow.ITask) #txt
Ol0 f0 type ch.ivy.addon.portal.generic.OpenPortalTaskDetailsData #txt
Ol0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>call(ITask)</name>
    </language>
</elementInfo>
' #txt
Ol0 f0 81 49 30 30 -64 25 #rect
Ol0 f0 @|StartSubIcon #fIcon
Ol0 f1 type ch.ivy.addon.portal.generic.OpenPortalTaskDetailsData #txt
Ol0 f1 465 49 30 30 0 15 #rect
Ol0 f1 @|EndSubIcon #fIcon
Ol0 f7 richDialogId ch.ivy.addon.portal.generic.PortalTaskDetails #txt
Ol0 f7 startMethod start(ch.ivyteam.ivy.workflow.ITask,String) #txt
Ol0 f7 type ch.ivy.addon.portal.generic.OpenPortalTaskDetailsData #txt
Ol0 f7 requestActionDecl '<ch.ivyteam.ivy.workflow.ITask task,String menuState> param;' #txt
Ol0 f7 requestMappingAction 'param.task=in.taskView;
param.menuState=ch.ivy.addon.portalkit.util.MenuUtils.getMenuState();
' #txt
Ol0 f7 responseActionDecl 'ch.ivy.addon.portal.generic.OpenPortalTaskDetailsData out;
' #txt
Ol0 f7 responseMappingAction 'out=in;
' #txt
Ol0 f7 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>open portal task detail</name>
    </language>
</elementInfo>
' #txt
Ol0 f7 208 42 128 44 -60 -8 #rect
Ol0 f7 @|RichDialogIcon #fIcon
Ol0 f6 expr out #txt
Ol0 f6 336 64 465 64 #arcP
Ol0 f2 expr out #txt
Ol0 f2 111 64 208 64 #arcP
>Proto Ol0 .type ch.ivy.addon.portal.generic.OpenPortalTaskDetailsData #txt
>Proto Ol0 .processKind CALLABLE_SUB #txt
>Proto Ol0 0 0 32 24 18 0 #rect
>Proto Ol0 @|BIcon #fIcon
Ol0 f7 mainOut f6 tail #connect
Ol0 f6 head f1 mainIn #connect
Ol0 f0 mainOut f2 tail #connect
Ol0 f2 head f7 mainIn #connect
