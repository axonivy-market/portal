[Ivy]
16BFA568D95D2EA0 3.26 #module
>Proto >Proto Collection #zClass
Cs0 TaskItemDetailsProcess Big #zClass
Cs0 RD #cInfo
Cs0 #process
Cs0 @TextInP .ui2RdDataAction .ui2RdDataAction #zField
Cs0 @TextInP .rdData2UIAction .rdData2UIAction #zField
Cs0 @TextInP .resExport .resExport #zField
Cs0 @TextInP .type .type #zField
Cs0 @TextInP .processKind .processKind #zField
Cs0 @AnnotationInP-0n ai ai #zField
Cs0 @MessageFlowInP-0n messageIn messageIn #zField
Cs0 @MessageFlowOutP-0n messageOut messageOut #zField
Cs0 @TextInP .xml .xml #zField
Cs0 @TextInP .responsibility .responsibility #zField
Cs0 @RichDialogInitStart f0 '' #zField
Cs0 @RichDialogProcessEnd f1 '' #zField
Cs0 @PushWFArc f2 '' #zField
Cs0 @RichDialogProcessStart f3 '' #zField
Cs0 @RichDialogEnd f4 '' #zField
Cs0 @PushWFArc f5 '' #zField
Cs0 @RichDialogMethodStart f6 '' #zField
Cs0 @RichDialogProcessEnd f7 '' #zField
Cs0 @GridStep f8 '' #zField
Cs0 @RichDialogMethodStart f9 '' #zField
Cs0 @RichDialogProcessEnd f10 '' #zField
Cs0 @PushWFArc f11 '' #zField
Cs0 @PushWFArc f12 '' #zField
Cs0 @PushWFArc f13 '' #zField
>Proto Cs0 Cs0 TaskItemDetailsProcess #zField
Cs0 f0 guid 16BBB5787F4A8092 #txt
Cs0 f0 type ch.ivy.addon.portalkit.component.TaskItemDetails.TaskItemDetailsData #txt
Cs0 f0 method start() #txt
Cs0 f0 disableUIEvents true #txt
Cs0 f0 inParameterDecl 'ch.ivy.add.portalkit.Data out;
' #txt
Cs0 f0 outParameterDecl '<> result;
' #txt
Cs0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start()</name>
    </language>
</elementInfo>
' #txt
Cs0 f0 83 51 26 26 -16 15 #rect
Cs0 f0 @|RichDialogInitStartIcon #fIcon
Cs0 f1 type ch.ivy.addon.portalkit.component.TaskItemDetails.TaskItemDetailsData #txt
Cs0 f1 211 51 26 26 0 12 #rect
Cs0 f1 @|RichDialogProcessEndIcon #fIcon
Cs0 f2 expr out #txt
Cs0 f2 109 64 211 64 #arcP
Cs0 f3 guid 16BBB578819930A1 #txt
Cs0 f3 type ch.ivy.addon.portalkit.component.TaskItemDetails.TaskItemDetailsData #txt
Cs0 f3 actionDecl 'ch.ivy.add.portalkit.Data out;
' #txt
Cs0 f3 actionTable 'out=in;
' #txt
Cs0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>close</name>
    </language>
</elementInfo>
' #txt
Cs0 f3 83 147 26 26 -15 12 #rect
Cs0 f3 @|RichDialogProcessStartIcon #fIcon
Cs0 f4 type ch.ivy.addon.portalkit.component.TaskItemDetails.TaskItemDetailsData #txt
Cs0 f4 guid 16BBB57881A7CF73 #txt
Cs0 f4 211 147 26 26 0 12 #rect
Cs0 f4 @|RichDialogEndIcon #fIcon
Cs0 f5 expr out #txt
Cs0 f5 109 160 211 160 #arcP
Cs0 f6 guid 16C0845B607C6BC7 #txt
Cs0 f6 type ch.ivy.addon.portalkit.component.TaskItemDetails.TaskItemDetailsData #txt
Cs0 f6 method keepOldNameValue(javax.faces.event.ValueChangeEvent) #txt
Cs0 f6 disableUIEvents false #txt
Cs0 f6 inParameterDecl 'ch.ivy.addon.portalkit.component.TaskItemDetails.TaskItemDetailsData out;
' #txt
Cs0 f6 inParameterMapAction 'out.oldName=param.valueChangeEvent.getOldValue() as String;
' #txt
Cs0 f6 outParameterDecl '<> result;
' #txt
Cs0 f6 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>keepOldNameValue(valueChangeEvent)</name>
    </language>
</elementInfo>
' #txt
Cs0 f6 83 219 26 26 -86 15 #rect
Cs0 f6 @|RichDialogMethodStartIcon #fIcon
Cs0 f7 type ch.ivy.addon.portalkit.component.TaskItemDetails.TaskItemDetailsData #txt
Cs0 f7 371 219 26 26 0 12 #rect
Cs0 f7 @|RichDialogProcessEndIcon #fIcon
Cs0 f8 actionDecl 'ch.ivy.addon.portalkit.component.TaskItemDetails.TaskItemDetailsData out;
' #txt
Cs0 f8 actionTable 'out=in;
' #txt
Cs0 f8 actionCode 'import java.util.Arrays;

out.task.getCase().createNote(ivy.session, ivy.cms.co("/ch.ivy.addon.portalkit.ui.jsf/taskList/setNameNote", Arrays.asList(ivy.session.getSessionUser().getDisplayName(), in.oldName, in.task.getName())));' #txt
Cs0 f8 security system #txt
Cs0 f8 type ch.ivy.addon.portalkit.component.TaskItemDetails.TaskItemDetailsData #txt
Cs0 f8 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Add note</name>
    </language>
</elementInfo>
' #txt
Cs0 f8 200 306 112 44 -24 -8 #rect
Cs0 f8 @|StepIcon #fIcon
Cs0 f9 guid 16C0845B60E83C7D #txt
Cs0 f9 type ch.ivy.addon.portalkit.component.TaskItemDetails.TaskItemDetailsData #txt
Cs0 f9 method updateTask(ch.ivyteam.ivy.workflow.ITask) #txt
Cs0 f9 disableUIEvents false #txt
Cs0 f9 inParameterDecl 'ch.ivy.addon.portalkit.component.TaskItemDetails.TaskItemDetailsData out;
' #txt
Cs0 f9 inParameterMapAction 'out.task=param.task;
' #txt
Cs0 f9 outParameterDecl '<> result;
' #txt
Cs0 f9 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>updateTask(ITask)</name>
    </language>
</elementInfo>
' #txt
Cs0 f9 83 315 26 26 -51 15 #rect
Cs0 f9 @|RichDialogMethodStartIcon #fIcon
Cs0 f10 type ch.ivy.addon.portalkit.component.TaskItemDetails.TaskItemDetailsData #txt
Cs0 f10 371 315 26 26 0 12 #rect
Cs0 f10 @|RichDialogProcessEndIcon #fIcon
Cs0 f11 expr out #txt
Cs0 f11 109 328 200 328 #arcP
Cs0 f12 expr out #txt
Cs0 f12 312 328 371 328 #arcP
Cs0 f13 expr out #txt
Cs0 f13 109 232 371 232 #arcP
>Proto Cs0 .type ch.ivy.addon.portalkit.component.TaskItemDetails.TaskItemDetailsData #txt
>Proto Cs0 .processKind HTML_DIALOG #txt
>Proto Cs0 -8 -8 16 16 16 26 #rect
>Proto Cs0 '' #fIcon
Cs0 f0 mainOut f2 tail #connect
Cs0 f2 head f1 mainIn #connect
Cs0 f3 mainOut f5 tail #connect
Cs0 f5 head f4 mainIn #connect
Cs0 f6 mainOut f13 tail #connect
Cs0 f13 head f7 mainIn #connect
Cs0 f9 mainOut f11 tail #connect
Cs0 f11 head f8 mainIn #connect
Cs0 f8 mainOut f12 tail #connect
Cs0 f12 head f10 mainIn #connect
