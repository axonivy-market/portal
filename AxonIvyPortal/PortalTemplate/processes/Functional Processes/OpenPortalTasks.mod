[Ivy]
[>Created: Fri Sep 16 21:48:21 ICT 2016]
15493AEB89F5A807 3.18 #module
>Proto >Proto Collection #zClass
Os0 OpenPortalTasks Big #zClass
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
Os0 @RichDialog f3 '' #zField
Os0 @PushWFArc f4 '' #zField
Os0 @PushWFArc f2 '' #zField
>Proto Os0 Os0 OpenPortalTasks #zField
Os0 f0 inParamDecl '<ch.ivy.addon.portal.generic.view.TaskView taskView> param;' #txt
Os0 f0 inParamTable 'out.taskView=param.taskView;
' #txt
Os0 f0 outParamDecl '<> result;
' #txt
Os0 f0 actionDecl 'ch.ivy.addon.portal.generic.OpenPortalTasksData out;
' #txt
Os0 f0 callSignature useView(ch.ivy.addon.portal.generic.view.TaskView) #txt
Os0 f0 type ch.ivy.addon.portal.generic.OpenPortalTasksData #txt
Os0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>useView(TaskView)</name>
    </language>
</elementInfo>
' #txt
Os0 f0 347 99 26 26 14 0 #rect
Os0 f0 @|StartSubIcon #fIcon
Os0 f1 type ch.ivy.addon.portal.generic.OpenPortalTasksData #txt
Os0 f1 347 355 26 26 14 0 #rect
Os0 f1 @|EndSubIcon #fIcon
Os0 f3 targetWindow NEW:card: #txt
Os0 f3 targetDisplay TOP #txt
Os0 f3 richDialogId ch.ivy.addon.portal.generic.PortalTasks #txt
Os0 f3 startMethod useTaskViewWithMenuState(ch.ivy.addon.portal.generic.view.TaskView,String) #txt
Os0 f3 type ch.ivy.addon.portal.generic.OpenPortalTasksData #txt
Os0 f3 requestActionDecl '<ch.ivy.addon.portal.generic.view.TaskView taskView, String menuState> param;' #txt
Os0 f3 requestMappingAction 'param.taskView=in.taskView;
param.menuState=ch.ivy.addon.portalkit.util.MenuUtils.getMenuState();
' #txt
Os0 f3 responseActionDecl 'ch.ivy.addon.portal.generic.OpenPortalTasksData out;
' #txt
Os0 f3 responseMappingAction 'out=in;
' #txt
Os0 f3 windowConfiguration '* ' #txt
Os0 f3 isAsynch false #txt
Os0 f3 isInnerRd false #txt
Os0 f3 userContext '* ' #txt
Os0 f3 342 228 36 24 20 -2 #rect
Os0 f3 @|RichDialogIcon #fIcon
Os0 f4 expr out #txt
Os0 f4 360 125 360 228 #arcP
Os0 f2 expr out #txt
Os0 f2 360 252 360 355 #arcP
>Proto Os0 .type ch.ivy.addon.portal.generic.OpenPortalTasksData #txt
>Proto Os0 .processKind CALLABLE_SUB #txt
>Proto Os0 0 0 32 24 18 0 #rect
>Proto Os0 @|BIcon #fIcon
Os0 f0 mainOut f4 tail #connect
Os0 f4 head f3 mainIn #connect
Os0 f3 mainOut f2 tail #connect
Os0 f2 head f1 mainIn #connect
