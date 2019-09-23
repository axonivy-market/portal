[Ivy]
16C420AAA2B6B6B6 3.26 #module
>Proto >Proto Collection #zClass
Ps0 TaskItemDetailsProcess Big #zClass
Ps0 RD #cInfo
Ps0 #process
Ps0 @TextInP .ui2RdDataAction .ui2RdDataAction #zField
Ps0 @TextInP .rdData2UIAction .rdData2UIAction #zField
Ps0 @TextInP .resExport .resExport #zField
Ps0 @TextInP .type .type #zField
Ps0 @TextInP .processKind .processKind #zField
Ps0 @AnnotationInP-0n ai ai #zField
Ps0 @MessageFlowInP-0n messageIn messageIn #zField
Ps0 @MessageFlowOutP-0n messageOut messageOut #zField
Ps0 @TextInP .xml .xml #zField
Ps0 @TextInP .responsibility .responsibility #zField
Ps0 @RichDialogInitStart f0 '' #zField
Ps0 @RichDialogProcessEnd f4 '' #zField
Ps0 @PushWFArc f5 '' #zField
Ps0 @RichDialogProcessStart f3 '' #zField
Ps0 @RichDialogEnd f1 '' #zField
Ps0 @PushWFArc f2 '' #zField
>Proto Ps0 Ps0 TaskItemDetailsProcess #zField
Ps0 f0 guid 16C421FBB76376F6 #txt
Ps0 f0 type ch.ivyteam.ivy.project.portal.examples.component.customize.TaskItemDetails.TaskItemDetailsData #txt
Ps0 f0 method start(ch.ivyteam.ivy.workflow.ITask,String,ch.ivy.addon.portalkit.datamodel.TaskLazyDataModel,String,Boolean) #txt
Ps0 f0 disableUIEvents true #txt
Ps0 f0 inParameterDecl 'ch.ivyteam.ivy.project.portal.examples.component.customize.TaskItemDetails.TaskItemDetailsData out;
' #txt
Ps0 f0 inParameterMapAction 'out.dataModel=param.dataModel;
out.isFromTaskList=param.isFromTaskList;
out.menuState=param.menuState;
out.portalPage=param.portalPage;
out.task=param.task;
' #txt
Ps0 f0 outParameterDecl '<> result;
' #txt
Ps0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start(ITask,String,TaskLazyDataModel,String,Boolean)</name>
    </language>
</elementInfo>
' #txt
Ps0 f0 83 83 26 26 -16 15 #rect
Ps0 f0 @|RichDialogInitStartIcon #fIcon
Ps0 f4 type ch.ivyteam.ivy.project.portal.examples.component.customize.TaskItemDetails.TaskItemDetailsData #txt
Ps0 f4 211 83 26 26 0 12 #rect
Ps0 f4 @|RichDialogProcessEndIcon #fIcon
Ps0 f5 expr out #txt
Ps0 f5 109 96 211 96 #arcP
Ps0 f3 guid 16C45AE394A575BF #txt
Ps0 f3 type ch.ivyteam.ivy.project.portal.examples.component.customize.TaskItemDetails.TaskItemDetailsData #txt
Ps0 f3 actionDecl 'ch.ivyteam.ivy.project.portal.examples.component.customize.TaskItemDetails.TaskItemDetailsData out;
' #txt
Ps0 f3 actionTable 'out=in;
' #txt
Ps0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>close</name>
    </language>
</elementInfo>
' #txt
Ps0 f3 83 179 26 26 -15 12 #rect
Ps0 f3 @|RichDialogProcessStartIcon #fIcon
Ps0 f1 type ch.ivyteam.ivy.project.portal.examples.component.customize.TaskItemDetails.TaskItemDetailsData #txt
Ps0 f1 guid 16BD5C61E82555D2 #txt
Ps0 f1 211 179 26 26 0 12 #rect
Ps0 f1 @|RichDialogEndIcon #fIcon
Ps0 f2 expr out #txt
Ps0 f2 109 192 211 192 #arcP
>Proto Ps0 .type ch.ivyteam.ivy.project.portal.examples.component.customize.TaskItemDetails.TaskItemDetailsData #txt
>Proto Ps0 .processKind HTML_DIALOG #txt
>Proto Ps0 -8 -8 16 16 16 26 #rect
>Proto Ps0 '' #fIcon
Ps0 f0 mainOut f5 tail #connect
Ps0 f5 head f4 mainIn #connect
Ps0 f3 mainOut f2 tail #connect
Ps0 f2 head f1 mainIn #connect
