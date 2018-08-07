[Ivy]
[>Created: Mon Apr 11 10:43:37 ICT 2016]
153892C76D2CD877 3.18 #module
>Proto >Proto Collection #zClass
Ps0 PortalSingleTaskProcess Big #zClass
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
Ps0 @RichDialogProcessEnd f1 '' #zField
Ps0 @RichDialogProcessStart f3 '' #zField
Ps0 @RichDialogEnd f4 '' #zField
Ps0 @PushWFArc f5 '' #zField
Ps0 @RichDialogMethodStart f6 '' #zField
Ps0 @RichDialogProcessEnd f7 '' #zField
Ps0 @PushWFArc f12 '' #zField
Ps0 @PushWFArc f2 '' #zField
Ps0 @PushWFArc f15 '' #zField
Ps0 @CallSub f11 '' #zField
Ps0 @GridStep f9 '' #zField
Ps0 @PushWFArc f8 '' #zField
>Proto Ps0 Ps0 PortalSingleTaskProcess #zField
Ps0 f0 guid 153892C76F25DA31 #txt
Ps0 f0 type ch.ivy.addon.portal.generic.PortalSingleTask.PortalSingleTaskData #txt
Ps0 f0 method start(Long) #txt
Ps0 f0 disableUIEvents true #txt
Ps0 f0 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<java.lang.Long taskId> param = methodEvent.getInputArguments();
' #txt
Ps0 f0 inParameterMapAction 'out.remoteTaskId=param.taskId;
' #txt
Ps0 f0 outParameterDecl '<> result;
' #txt
Ps0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start(taskId)</name>
        <nameStyle>13,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ps0 f0 53 85 22 22 14 0 #rect
Ps0 f0 @|RichDialogInitStartIcon #fIcon
Ps0 f1 type ch.ivy.addon.portal.generic.PortalSingleTask.PortalSingleTaskData #txt
Ps0 f1 365 85 22 22 14 0 #rect
Ps0 f1 @|RichDialogProcessEndIcon #fIcon
Ps0 f3 guid 153892C76FD245E6 #txt
Ps0 f3 type ch.ivy.addon.portal.generic.PortalSingleTask.PortalSingleTaskData #txt
Ps0 f3 actionDecl 'ch.ivy.addon.portal.generic.PortalSingleTask.PortalSingleTaskData out;
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
Ps0 f3 61 157 22 22 14 0 #rect
Ps0 f3 @|RichDialogProcessStartIcon #fIcon
Ps0 f4 type ch.ivy.addon.portal.generic.PortalSingleTask.PortalSingleTaskData #txt
Ps0 f4 guid 153892C76FDB4942 #txt
Ps0 f4 365 157 22 22 14 0 #rect
Ps0 f4 @|RichDialogEndIcon #fIcon
Ps0 f5 expr out #txt
Ps0 f5 83 168 365 168 #arcP
Ps0 f6 guid 153895105657A06E #txt
Ps0 f6 type ch.ivy.addon.portal.generic.PortalSingleTask.PortalSingleTaskData #txt
Ps0 f6 method getTask() #txt
Ps0 f6 disableUIEvents false #txt
Ps0 f6 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<> param = methodEvent.getInputArguments();
' #txt
Ps0 f6 outParameterDecl '<java.util.List<ch.ivy.addon.portalkit.bo.RemoteTask> tasks> result;
' #txt
Ps0 f6 outParameterMapAction 'result.tasks=in.remoteTasks;
' #txt
Ps0 f6 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>getTask()</name>
        <nameStyle>9,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ps0 f6 61 229 22 22 14 0 #rect
Ps0 f6 @|RichDialogMethodStartIcon #fIcon
Ps0 f7 type ch.ivy.addon.portal.generic.PortalSingleTask.PortalSingleTaskData #txt
Ps0 f7 493 229 22 22 14 0 #rect
Ps0 f7 @|RichDialogProcessEndIcon #fIcon
Ps0 f12 expr out #txt
Ps0 f12 83 240 206 240 #arcP
Ps0 f2 expr out #txt
Ps0 f2 242 96 365 96 #arcP
Ps0 f15 expr out #txt
Ps0 f15 75 96 206 96 #arcP
Ps0 f11 type ch.ivy.addon.portal.generic.PortalSingleTask.PortalSingleTaskData #txt
Ps0 f11 processCall MultiPortal/TaskService:findTasksByCriteriaAndServers(List<ch.ivy.addon.portalkit.persistence.domain.Server>,ch.ivy.ws.addon.TaskSearchCriteria,Integer,Integer) #txt
Ps0 f11 doCall true #txt
Ps0 f11 requestActionDecl '<java.util.List<ch.ivy.addon.portalkit.persistence.domain.Server> servers,ch.ivy.ws.addon.TaskSearchCriteria taskSearchCriteria,java.lang.Integer startIndex,java.lang.Integer count> param;
' #txt
Ps0 f11 requestMappingAction 'param.servers=java.util.Arrays.asList(in.server);
param.taskSearchCriteria=in.taskSearchCriteria;
' #txt
Ps0 f11 responseActionDecl 'ch.ivy.addon.portal.generic.PortalSingleTask.PortalSingleTaskData out;
' #txt
Ps0 f11 responseMappingAction 'out=in;
out.remoteTasks=result.tasks;
' #txt
Ps0 f11 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>TaskService</name>
        <nameStyle>11,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ps0 f11 206 228 36 24 -26 14 #rect
Ps0 f11 @|CallSubIcon #fIcon
Ps0 f9 actionDecl 'ch.ivy.addon.portal.generic.PortalSingleTask.PortalSingleTaskData out;
' #txt
Ps0 f9 actionTable 'out=in;
' #txt
Ps0 f9 actionCode 'import ch.ivy.ws.addon.TaskSearchCriteria;
import ch.ivy.addon.portalkit.service.ServerWorkingOnDetector;
import ch.ivy.addon.portalkit.persistence.domain.Server;
import ch.ivy.addon.portalkit.service.ServerService;

ServerWorkingOnDetector detector = new ServerWorkingOnDetector();
in.server = detector.getServerWorkingOn();

out.dataModel.setTaskId(in.remoteTaskId);' #txt
Ps0 f9 type ch.ivy.addon.portal.generic.PortalSingleTask.PortalSingleTaskData #txt
Ps0 f9 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>detect working server
and create criteria search</name>
        <nameStyle>48,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ps0 f9 206 84 36 24 -68 -50 #rect
Ps0 f9 @|StepIcon #fIcon
Ps0 f8 expr out #txt
Ps0 f8 242 240 493 240 #arcP
>Proto Ps0 .type ch.ivy.addon.portal.generic.PortalSingleTask.PortalSingleTaskData #txt
>Proto Ps0 .processKind HTML_DIALOG #txt
>Proto Ps0 -8 -8 16 16 16 26 #rect
>Proto Ps0 '' #fIcon
Ps0 f3 mainOut f5 tail #connect
Ps0 f5 head f4 mainIn #connect
Ps0 f0 mainOut f15 tail #connect
Ps0 f15 head f9 mainIn #connect
Ps0 f9 mainOut f2 tail #connect
Ps0 f2 head f1 mainIn #connect
Ps0 f6 mainOut f12 tail #connect
Ps0 f12 head f11 mainIn #connect
Ps0 f11 mainOut f8 tail #connect
Ps0 f8 head f7 mainIn #connect
