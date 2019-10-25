[Ivy]
1624D072528F8B85 7.5.0 #module
>Proto >Proto Collection #zClass
Os0 OpenPortalTasks Big #zClass
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
Os0 @UserDialog f3 '' #zField
Os0 @PushWFArc f2 '' #zField
Os0 @PushWFArc f7 '' #zField
>Proto Os0 Os0 OpenPortalTasks #zField
Os0 f0 inParamDecl '<ch.ivy.addon.portal.generic.view.TaskView taskView> param;' #txt
Os0 f0 inParamTable 'out.taskView=param.taskView;
' #txt
Os0 f0 outParamDecl '<> result;' #txt
Os0 f0 callSignature useView(ch.ivy.addon.portal.generic.view.TaskView) #txt
Os0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>useView(TaskView)</name>
    </language>
</elementInfo>
' #txt
Os0 f0 83 99 26 26 14 0 #rect
Os0 f0 @|StartSubIcon #fIcon
Os0 f1 83 259 26 26 14 0 #rect
Os0 f1 @|EndSubIcon #fIcon
Os0 f3 dialogId ch.ivyteam.ivy.project.portal.examples.component.customize.TaskWidget #txt
Os0 f3 startMethod useTaskView(TaskView) #txt
Os0 f3 requestActionDecl '<ch.ivy.addon.portal.generic.view.TaskView taskView> param;' #txt
Os0 f3 requestMappingAction 'param.taskView=in.taskView;
' #txt
Os0 f3 responseActionDecl '_ch.ivyteam.ivy.project.portal.examples.OpenPortalTasksOverrideData out;
' #txt
Os0 f3 responseMappingAction 'out=in;
' #txt
Os0 f3 78 178 36 24 20 -2 #rect
Os0 f3 @|UserDialogIcon #fIcon
Os0 f2 expr out #txt
Os0 f2 96 202 96 259 #arcP
Os0 f7 expr out #txt
Os0 f7 96 125 96 178 #arcP
>Proto Os0 .type _ch.ivyteam.ivy.project.portal.examples.OpenPortalTasksOverrideData #txt
>Proto Os0 .processKind CALLABLE_SUB #txt
>Proto Os0 0 0 32 24 18 0 #rect
>Proto Os0 @|BIcon #fIcon
Os0 f3 mainOut f2 tail #connect
Os0 f2 head f1 mainIn #connect
Os0 f0 mainOut f7 tail #connect
Os0 f7 head f3 mainIn #connect
