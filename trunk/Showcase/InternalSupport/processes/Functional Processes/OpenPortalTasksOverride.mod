[Ivy]
[>Created: Mon Mar 27 16:52:25 ICT 2017]
15B0E93BE3F66FF7 3.20 #module
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
Os0 @PushWFArc f2 '' #zField
Os0 @GridStep f5 '' #zField
Os0 @PushWFArc f6 '' #zField
Os0 @PushWFArc f4 '' #zField
Os0 @InfoButton f7 '' #zField
Os0 @AnnotationArc f8 '' #zField
>Proto Os0 Os0 OpenPortalTasks #zField
Os0 f0 inParamDecl '<ch.ivy.addon.portal.generic.view.TaskView taskView> param;' #txt
Os0 f0 inParamTable 'out.taskView=param.taskView;
' #txt
Os0 f0 outParamDecl '<> result;
' #txt
Os0 f0 actionDecl 'internaltest.OpenPortalTasksOverrideData out;
' #txt
Os0 f0 callSignature useView(ch.ivy.addon.portal.generic.view.TaskView) #txt
Os0 f0 type internaltest.OpenPortalTasksOverrideData #txt
Os0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>useView(TaskView)</name>
    </language>
</elementInfo>
' #txt
Os0 f0 83 99 26 26 14 0 #rect
Os0 f0 @|StartSubIcon #fIcon
Os0 f1 type internaltest.OpenPortalTasksOverrideData #txt
Os0 f1 83 355 26 26 14 0 #rect
Os0 f1 @|EndSubIcon #fIcon
Os0 f3 targetWindow NEW:card: #txt
Os0 f3 targetDisplay TOP #txt
Os0 f3 richDialogId internalPortal.PortalTasks #txt
Os0 f3 startMethod useTaskViewWithMenuState(ch.ivy.addon.portal.generic.view.TaskView,String) #txt
Os0 f3 type internaltest.OpenPortalTasksOverrideData #txt
Os0 f3 requestActionDecl '<ch.ivy.addon.portal.generic.view.TaskView taskView, String menuState> param;' #txt
Os0 f3 requestMappingAction 'param.taskView=in.taskView;
param.menuState=ch.ivy.addon.portalkit.util.MenuUtils.getMenuState();
' #txt
Os0 f3 responseActionDecl 'internaltest.OpenPortalTasksOverrideData out;
' #txt
Os0 f3 responseMappingAction 'out=in;
' #txt
Os0 f3 windowConfiguration '* ' #txt
Os0 f3 isAsynch false #txt
Os0 f3 isInnerRd false #txt
Os0 f3 userContext '* ' #txt
Os0 f3 78 274 36 24 20 -2 #rect
Os0 f3 @|RichDialogIcon #fIcon
Os0 f2 expr out #txt
Os0 f2 96 298 96 355 #arcP
Os0 f5 actionDecl 'internaltest.OpenPortalTasksOverrideData out;
' #txt
Os0 f5 actionTable 'out=in;
' #txt
Os0 f5 actionCode 'import ch.internalsupport.CustomizedTaskLazyDataModel;
import ch.ivy.addon.portalkit.datamodel.TaskLazyDataModel;

in.taskView.setDataModel(TaskLazyDataModel.newInstance(in.taskView.getDataModel(), CustomizedTaskLazyDataModel.class));' #txt
Os0 f5 type internaltest.OpenPortalTasksOverrideData #txt
Os0 f5 78 180 36 24 20 -2 #rect
Os0 f5 @|StepIcon #fIcon
Os0 f6 expr out #txt
Os0 f6 96 125 96 180 #arcP
Os0 f4 expr out #txt
Os0 f4 96 204 96 274 #arcP
Os0 f7 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>NOTE: IF THERE IS A CUSTOMIZED DATA MODEL, PLEASE USE THIS SCRIPT.

import ch.ivy.addon.portalkit.datamodel.TaskLazyDataModel;
import &lt;CUSTOMIZED_DATA_MODEL&gt;;

in.taskView.setDataModel(TaskLazyDataModel.newInstance(in.taskView.getDataModel(), &lt;CUSTOMIZED_DATA_MODEL&gt;.class));</name>
        <nameStyle>275
</nameStyle>
    </language>
</elementInfo>
' #txt
Os0 f7 184 162 720 108 -354 -48 #rect
Os0 f7 @|IBIcon #fIcon
Os0 f8 184 216 114 192 #arcP
>Proto Os0 .type internaltest.OpenPortalTasksOverrideData #txt
>Proto Os0 .processKind CALLABLE_SUB #txt
>Proto Os0 0 0 32 24 18 0 #rect
>Proto Os0 @|BIcon #fIcon
Os0 f3 mainOut f2 tail #connect
Os0 f2 head f1 mainIn #connect
Os0 f0 mainOut f6 tail #connect
Os0 f6 head f5 mainIn #connect
Os0 f5 mainOut f4 tail #connect
Os0 f4 head f3 mainIn #connect
Os0 f7 ao f8 tail #connect
Os0 f8 head f5 @CG|ai #connect
