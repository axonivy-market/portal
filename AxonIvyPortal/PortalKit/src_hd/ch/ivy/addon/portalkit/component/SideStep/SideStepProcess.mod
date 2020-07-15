[Ivy]
16BF516D50A96350 7.5.0 #module
>Proto >Proto Collection #zClass
Ts0 SideStepProcess Big #zClass
Ts0 RD #cInfo
Ts0 #process
Ts0 @TextInP .type .type #zField
Ts0 @TextInP .processKind .processKind #zField
Ts0 @AnnotationInP-0n ai ai #zField
Ts0 @MessageFlowInP-0n messageIn messageIn #zField
Ts0 @MessageFlowOutP-0n messageOut messageOut #zField
Ts0 @TextInP .xml .xml #zField
Ts0 @TextInP .responsibility .responsibility #zField
Ts0 @UdProcessEnd f17 '' #zField
Ts0 @UdProcessEnd f62 '' #zField
Ts0 @UdProcessEnd f1 '' #zField
Ts0 @UdMethod f39 '' #zField
Ts0 @PushWFArc f63 '' #zField
Ts0 @PushWFArc f61 '' #zField
Ts0 @GridStep f48 '' #zField
Ts0 @UdInit f0 '' #zField
Ts0 @UdMethod f6 '' #zField
Ts0 @UdProcessEnd f9 '' #zField
Ts0 @GridStep f16 '' #zField
Ts0 @PushWFArc f18 '' #zField
Ts0 @PushWFArc f22 '' #zField
Ts0 @PushWFArc f2 '' #zField
Ts0 @UdMethod f3 '' #zField
Ts0 @CallSub f7 '' #zField
Ts0 @PushWFArc f8 '' #zField
Ts0 @UdMethod f4 '' #zField
Ts0 @UdProcessEnd f5 '' #zField
Ts0 @GridStep f11 '' #zField
Ts0 @PushWFArc f12 '' #zField
Ts0 @PushWFArc f10 '' #zField
>Proto Ts0 Ts0 SideStepProcess #zField
Ts0 f17 -106 1334 20 20 13 0 #rect
Ts0 f17 @|UdProcessEndIcon #fIcon
Ts0 f62 339 275 26 26 0 12 #rect
Ts0 f62 @|UdProcessEndIcon #fIcon
Ts0 f1 339 83 26 26 0 12 #rect
Ts0 f1 @|UdProcessEndIcon #fIcon
Ts0 f39 guid 16816B96E5CA66CF #txt
Ts0 f39 method parkTask(ch.ivyteam.ivy.workflow.ITask) #txt
Ts0 f39 inParameterDecl '<ch.ivyteam.ivy.workflow.ITask task> param;' #txt
Ts0 f39 inParameterMapAction 'out.task=param.task;
' #txt
Ts0 f39 outParameterDecl '<> result;' #txt
Ts0 f39 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>parkTask(ITask)</name>
    </language>
</elementInfo>
' #txt
Ts0 f39 83 275 26 26 -44 15 #rect
Ts0 f39 @|UdMethodIcon #fIcon
Ts0 f63 expr out #txt
Ts0 f63 280 288 339 288 #arcP
Ts0 f61 expr out #txt
Ts0 f61 109 288 168 288 #arcP
Ts0 f48 actionTable 'out=in;
' #txt
Ts0 f48 actionCode 'import ch.ivy.addon.portalkit.util.TaskUtils;

TaskUtils.parkTask(in.task);' #txt
Ts0 f48 security system #txt
Ts0 f48 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Park task</name>
    </language>
</elementInfo>
' #txt
Ts0 f48 168 266 112 44 -25 -8 #rect
Ts0 f48 @|StepIcon #fIcon
Ts0 f0 guid 16816B3D5FE7A2CB #txt
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
Ts0 f6 guid 16BF8B07EB1F1B35 #txt
Ts0 f6 method resetTask(ch.ivyteam.ivy.workflow.ITask) #txt
Ts0 f6 inParameterDecl '<ch.ivyteam.ivy.workflow.ITask task> param;' #txt
Ts0 f6 inParameterMapAction 'out.task=param.task;
' #txt
Ts0 f6 outParameterDecl '<> result;' #txt
Ts0 f6 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>resetTask(ITask)</name>
    </language>
</elementInfo>
' #txt
Ts0 f6 83 179 26 26 -46 15 #rect
Ts0 f6 @|UdMethodIcon #fIcon
Ts0 f9 339 179 26 26 0 12 #rect
Ts0 f9 @|UdProcessEndIcon #fIcon
Ts0 f16 actionTable 'out=in;
' #txt
Ts0 f16 actionCode 'import ch.ivy.addon.portalkit.util.TaskUtils;

TaskUtils.resetTask(in.task);' #txt
Ts0 f16 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Reset Task</name>
    </language>
</elementInfo>
' #txt
Ts0 f16 168 170 112 44 -31 -8 #rect
Ts0 f16 @|StepIcon #fIcon
Ts0 f18 expr out #txt
Ts0 f18 109 192 168 192 #arcP
Ts0 f22 expr out #txt
Ts0 f22 280 192 339 192 #arcP
Ts0 f2 expr out #txt
Ts0 f2 109 96 339 96 #arcP
Ts0 f3 guid 1700E48002A4D237 #txt
Ts0 f3 method openDetails(ch.ivyteam.ivy.workflow.ITask,ch.ivy.addon.portalkit.datamodel.TaskLazyDataModel) #txt
Ts0 f3 inParameterDecl '<ch.ivyteam.ivy.workflow.ITask task,ch.ivy.addon.portalkit.datamodel.TaskLazyDataModel dataModel> param;' #txt
Ts0 f3 inParameterMapAction 'out.dataModel=param.dataModel;
out.task=param.task;
' #txt
Ts0 f3 outParameterDecl '<> result;' #txt
Ts0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>openDetails(ITask,TaskLazyDataModel)</name>
    </language>
</elementInfo>
' #txt
Ts0 f3 75 387 26 26 -70 15 #rect
Ts0 f3 @|UdMethodIcon #fIcon
Ts0 f7 processCall 'Functional Processes/OpenPortalTaskDetailsHook:call(ch.ivyteam.ivy.workflow.ITask,ch.ivy.addon.portalkit.datamodel.TaskLazyDataModel,ch.ivy.addon.portalkit.enums.PortalPage,Boolean)' #txt
Ts0 f7 requestActionDecl '<ch.ivyteam.ivy.workflow.ITask task,ch.ivy.addon.portalkit.datamodel.TaskLazyDataModel dataModel,ch.ivy.addon.portalkit.enums.PortalPage portalPage,Boolean isFromTaskList> param;' #txt
Ts0 f7 requestMappingAction 'param.task=in.task;
param.dataModel=in.dataModel;
param.portalPage=ch.ivy.addon.portalkit.enums.PortalPage.valueOf(in.currentPortalPage);
param.isFromTaskList=true;
' #txt
Ts0 f7 responseActionDecl 'ch.ivy.addon.portalkit.component.TaskItem.TaskItemData out;
' #txt
Ts0 f7 responseMappingAction 'out=in;
' #txt
Ts0 f7 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>OpenPortalTaskDetails</name>
    </language>
</elementInfo>
' #txt
Ts0 f7 256 378 144 44 -64 -8 #rect
Ts0 f7 @|CallSubIcon #fIcon
Ts0 f8 expr out #txt
Ts0 f8 101 400 256 400 #arcP
Ts0 f4 guid 17350C978AECF036 #txt
Ts0 f4 method clearDelay(ch.ivyteam.ivy.workflow.ITask) #txt
Ts0 f4 inParameterDecl '<ch.ivyteam.ivy.workflow.ITask task> param;' #txt
Ts0 f4 inParameterMapAction 'out.task=param.task;
' #txt
Ts0 f4 outParameterDecl '<> result;' #txt
Ts0 f4 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>clearDelay(ITask)</name>
    </language>
</elementInfo>
' #txt
Ts0 f4 83 499 26 26 -46 17 #rect
Ts0 f4 @|UdMethodIcon #fIcon
Ts0 f5 371 499 26 26 0 12 #rect
Ts0 f5 @|UdProcessEndIcon #fIcon
Ts0 f11 actionTable 'out=in;
' #txt
Ts0 f11 actionCode 'import ch.ivy.addon.portalkit.util.TaskUtils;
import org.apache.commons.lang3.StringUtils;
import ch.ivyteam.ivy.security.IUser;
import ch.ivy.addon.portalkit.service.TaskInforActionService;

boolean isDone = TaskUtils.removeTaskDelay(in.task);
if (isDone) {
	TaskInforActionService service = new TaskInforActionService();
	IUser user = ivy.session.getSessionUser();
	String fullName = user.getFullName();
	String userName = StringUtils.substring(user.getMemberName(), 1);
	String delayNote = service.prepareChangeDelayNoteContent(fullName, userName, new Date(), in.task.getId());
	in.task.getCase().getBusinessCase().createNote(ivy.session, delayNote);
}' #txt
Ts0 f11 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>clear the delay time</name>
    </language>
</elementInfo>
' #txt
Ts0 f11 176 490 112 44 -53 -8 #rect
Ts0 f11 @|StepIcon #fIcon
Ts0 f12 109 512 176 512 #arcP
Ts0 f10 288 512 371 512 #arcP
>Proto Ts0 .type ch.ivy.addon.portalkit.component.SideStep.SideStepData #txt
>Proto Ts0 .processKind HTML_DIALOG #txt
>Proto Ts0 -8 -8 16 16 16 26 #rect
>Proto Ts0 '' #fIcon
Ts0 f39 mainOut f61 tail #connect
Ts0 f61 head f48 mainIn #connect
Ts0 f48 mainOut f63 tail #connect
Ts0 f63 head f62 mainIn #connect
Ts0 f6 mainOut f18 tail #connect
Ts0 f18 head f16 mainIn #connect
Ts0 f16 mainOut f22 tail #connect
Ts0 f22 head f9 mainIn #connect
Ts0 f0 mainOut f2 tail #connect
Ts0 f2 head f1 mainIn #connect
Ts0 f3 mainOut f8 tail #connect
Ts0 f8 head f7 mainIn #connect
Ts0 f4 mainOut f12 tail #connect
Ts0 f12 head f11 mainIn #connect
Ts0 f11 mainOut f10 tail #connect
Ts0 f10 head f5 mainIn #connect
