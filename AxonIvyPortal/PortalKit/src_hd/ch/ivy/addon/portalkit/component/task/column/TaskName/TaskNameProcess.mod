[Ivy]
15AE9A62C6A66DD8 7.5.0 #module
>Proto >Proto Collection #zClass
Ts0 TaskNameProcess Big #zClass
Ts0 RD #cInfo
Ts0 #process
Ts0 @TextInP .type .type #zField
Ts0 @TextInP .processKind .processKind #zField
Ts0 @AnnotationInP-0n ai ai #zField
Ts0 @MessageFlowInP-0n messageIn messageIn #zField
Ts0 @MessageFlowOutP-0n messageOut messageOut #zField
Ts0 @TextInP .xml .xml #zField
Ts0 @TextInP .responsibility .responsibility #zField
Ts0 @UdInit f0 '' #zField
Ts0 @UdProcessEnd f1 '' #zField
Ts0 @PushWFArc f2 '' #zField
Ts0 @UdMethod f3 '' #zField
Ts0 @UdProcessEnd f4 '' #zField
Ts0 @PushWFArc f5 '' #zField
Ts0 @UdMethod f6 '' #zField
Ts0 @GridStep f7 '' #zField
Ts0 @PushWFArc f8 '' #zField
Ts0 @UdProcessEnd f9 '' #zField
Ts0 @PushWFArc f10 '' #zField
>Proto Ts0 Ts0 TaskNameProcess #zField
Ts0 f0 guid 1681204703950395 #txt
Ts0 f0 method start() #txt
Ts0 f0 inParameterDecl '<> param;' #txt
Ts0 f0 outParameterDecl '<> result;' #txt
Ts0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start()</name>
    </language>
</elementInfo>
' #txt
Ts0 f0 83 83 26 26 -16 15 #rect
Ts0 f0 @|UdInitIcon #fIcon
Ts0 f1 371 83 26 26 0 12 #rect
Ts0 f1 @|UdProcessEndIcon #fIcon
Ts0 f2 expr out #txt
Ts0 f2 109 96 371 96 #arcP
Ts0 f3 guid 1681204C698BF8C7 #txt
Ts0 f3 method keepOldNameValue(javax.faces.event.ValueChangeEvent) #txt
Ts0 f3 inParameterDecl '<javax.faces.event.ValueChangeEvent event> param;' #txt
Ts0 f3 inParameterMapAction 'out.oldName=param.event.getOldValue() as String;
' #txt
Ts0 f3 outParameterDecl '<> result;' #txt
Ts0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>keepOldNameValue(ValueChangeEvent)</name>
    </language>
</elementInfo>
' #txt
Ts0 f3 83 179 26 26 -86 15 #rect
Ts0 f3 @|UdMethodIcon #fIcon
Ts0 f4 371 179 26 26 0 12 #rect
Ts0 f4 @|UdProcessEndIcon #fIcon
Ts0 f5 expr out #txt
Ts0 f5 109 192 371 192 #arcP
Ts0 f6 guid 16812056797ABC4C #txt
Ts0 f6 method updateTask(ch.ivyteam.ivy.workflow.ITask) #txt
Ts0 f6 inParameterDecl '<ch.ivyteam.ivy.workflow.ITask task> param;' #txt
Ts0 f6 inParameterMapAction 'out.task=param.task;
' #txt
Ts0 f6 outParameterDecl '<> result;' #txt
Ts0 f6 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>updateTask(ITask)</name>
    </language>
</elementInfo>
' #txt
Ts0 f6 83 275 26 26 -51 15 #rect
Ts0 f6 @|UdMethodIcon #fIcon
Ts0 f7 actionTable 'out=in;
' #txt
Ts0 f7 actionCode 'import java.util.Arrays;

out.task.getCase().createNote(ivy.session, ivy.cms.co("/ch.ivy.addon.portalkit.ui.jsf/taskList/setNameNote", Arrays.asList(ivy.session.getSessionUser().getDisplayName(), in.oldName, in.task.getName())));' #txt
Ts0 f7 security system #txt
Ts0 f7 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Add note</name>
    </language>
</elementInfo>
' #txt
Ts0 f7 200 266 112 44 -24 -8 #rect
Ts0 f7 @|StepIcon #fIcon
Ts0 f8 expr out #txt
Ts0 f8 109 288 200 288 #arcP
Ts0 f9 371 275 26 26 0 12 #rect
Ts0 f9 @|UdProcessEndIcon #fIcon
Ts0 f10 expr out #txt
Ts0 f10 312 288 371 288 #arcP
>Proto Ts0 .type ch.ivy.addon.portalkit.component.task.column.TaskName.TaskNameData #txt
>Proto Ts0 .processKind HTML_DIALOG #txt
>Proto Ts0 -8 -8 16 16 16 26 #rect
>Proto Ts0 '' #fIcon
Ts0 f0 mainOut f2 tail #connect
Ts0 f2 head f1 mainIn #connect
Ts0 f3 mainOut f5 tail #connect
Ts0 f5 head f4 mainIn #connect
Ts0 f6 mainOut f8 tail #connect
Ts0 f8 head f7 mainIn #connect
Ts0 f7 mainOut f10 tail #connect
Ts0 f10 head f9 mainIn #connect
