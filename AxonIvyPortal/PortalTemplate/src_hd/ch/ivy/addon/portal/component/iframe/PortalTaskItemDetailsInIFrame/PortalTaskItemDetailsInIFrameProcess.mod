[Ivy]
1754F760476EA790 9.2.0 #module
>Proto >Proto Collection #zClass
Ps0 PortalTaskItemDetailsInIFrameProcess Big #zClass
Ps0 RD #cInfo
Ps0 #process
Ps0 @TextInP .type .type #zField
Ps0 @TextInP .processKind .processKind #zField
Ps0 @TextInP .xml .xml #zField
Ps0 @TextInP .responsibility .responsibility #zField
Ps0 @UdInit f0 '' #zField
Ps0 @UdProcessEnd f1 '' #zField
Ps0 @UdEvent f3 '' #zField
Ps0 @UdExitEnd f4 '' #zField
Ps0 @PushWFArc f5 '' #zField
Ps0 @GridStep f6 '' #zField
Ps0 @PushWFArc f7 '' #zField
Ps0 @PushWFArc f2 '' #zField
>Proto Ps0 Ps0 PortalTaskItemDetailsInIFrameProcess #zField
Ps0 f0 guid 1754F7604A58AB3F #txt
Ps0 f0 method start(ch.ivyteam.ivy.workflow.ITask,ch.ivy.addon.portalkit.datamodel.TaskLazyDataModel,ch.ivy.addon.portalkit.enums.PortalPage,Boolean) #txt
Ps0 f0 inParameterDecl '<ch.ivyteam.ivy.workflow.ITask task,ch.ivy.addon.portalkit.datamodel.TaskLazyDataModel model,ch.ivy.addon.portalkit.enums.PortalPage portalPage,Boolean isFromTaskList> param;' #txt
Ps0 f0 inParameterMapAction 'out.dataModel=param.model;
out.isFromTaskList=param.isFromTaskList;
out.portalPage=param.portalPage;
out.task=param.task;
' #txt
Ps0 f0 outParameterDecl '<> result;' #txt
Ps0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start(ITask,TaskLazyDataModel,PortalPage,Boolean)</name>
    </language>
</elementInfo>
' #txt
Ps0 f0 83 51 26 26 -75 29 #rect
Ps0 f0 @|UdInitIcon #fIcon
Ps0 f1 371 51 26 26 0 12 #rect
Ps0 f1 @|UdProcessEndIcon #fIcon
Ps0 f3 guid 1754F7604DB51E56 #txt
Ps0 f3 actionTable 'out=in;
' #txt
Ps0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>close</name>
    </language>
</elementInfo>
' #txt
Ps0 f3 83 147 26 26 -15 15 #rect
Ps0 f3 @|UdEventIcon #fIcon
Ps0 f4 371 147 26 26 0 12 #rect
Ps0 f4 @|UdExitEndIcon #fIcon
Ps0 f5 109 160 371 160 #arcP
Ps0 f6 actionTable 'out=in;
' #txt
Ps0 f6 actionCode 'import ch.ivy.addon.portalkit.enums.SessionAttribute;

in.isTaskStartedInDetails = ivy.session.getAttribute(SessionAttribute.IS_TASK_STARTED_IN_DETAILS.toString()) as Boolean;' #txt
Ps0 f6 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Check task is started</name>
    </language>
</elementInfo>
' #txt
Ps0 f6 192 42 128 44 -57 -8 #rect
Ps0 f6 @|StepIcon #fIcon
Ps0 f7 109 64 192 64 #arcP
Ps0 f2 320 64 371 64 #arcP
>Proto Ps0 .type ch.ivy.addon.portal.component.iframe.PortalTaskItemDetailsInIFrame.PortalTaskItemDetailsInIFrameData #txt
>Proto Ps0 .processKind HTML_DIALOG #txt
>Proto Ps0 -8 -8 16 16 16 26 #rect
>Proto Ps0 '' #fIcon
Ps0 f3 mainOut f5 tail #connect
Ps0 f5 head f4 mainIn #connect
Ps0 f0 mainOut f7 tail #connect
Ps0 f7 head f6 mainIn #connect
Ps0 f6 mainOut f2 tail #connect
Ps0 f2 head f1 mainIn #connect
