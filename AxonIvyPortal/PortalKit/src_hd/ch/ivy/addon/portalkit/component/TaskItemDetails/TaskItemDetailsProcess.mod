[Ivy]
16BFA568D95D2EA0 9.4.6 #module
>Proto >Proto Collection #zClass
Cs0 TaskItemDetailsProcess Big #zClass
Cs0 RD #cInfo
Cs0 #process
Cs0 @TextInP .colors .colors #zField
Cs0 @TextInP color color #zField
Cs0 @TextInP .type .type #zField
Cs0 @TextInP .processKind .processKind #zField
Cs0 @AnnotationInP-0n ai ai #zField
Cs0 @MessageFlowInP-0n messageIn messageIn #zField
Cs0 @MessageFlowOutP-0n messageOut messageOut #zField
Cs0 @TextInP .xml .xml #zField
Cs0 @TextInP .responsibility .responsibility #zField
Cs0 @UdInit f0 '' #zField
Cs0 @UdProcessEnd f1 '' #zField
Cs0 @PushWFArc f2 '' #zField
Cs0 @UdMethod f6 '' #zField
Cs0 @UdProcessEnd f7 '' #zField
Cs0 @GridStep f8 '' #zField
Cs0 @UdMethod f9 '' #zField
Cs0 @UdProcessEnd f10 '' #zField
Cs0 @PushWFArc f11 '' #zField
Cs0 @PushWFArc f12 '' #zField
Cs0 @PushWFArc f13 '' #zField
Cs0 @UdMethod f14 '' #zField
Cs0 @GridStep f45 '' #zField
Cs0 @UdProcessEnd f15 '' #zField
Cs0 @PushWFArc f16 '' #zField
Cs0 @Alternative f77 '' #zField
Cs0 @UdProcessEnd f68 '' #zField
Cs0 @GridStep f66 '' #zField
Cs0 @GridStep f49 '' #zField
Cs0 @Alternative f99 '' #zField
Cs0 @PushWFArc f71 '' #zField
Cs0 @PushWFArc f67 '' #zField
Cs0 @PushWFArc f100 '' #zField
Cs0 @PushWFArc f20 '' #zField
Cs0 @UdMethod f30 '' #zField
Cs0 @GridStep f35 '' #zField
Cs0 @PushWFArc f64 '' #zField
Cs0 @Alternative f79 '' #zField
Cs0 @UdProcessEnd f98 '' #zField
Cs0 @UdMethod f97 '' #zField
Cs0 @GridStep f80 '' #zField
Cs0 @PushWFArc f92 '' #zField
Cs0 @PushWFArc f87 '' #zField
Cs0 @PushWFArc f81 '' #zField
Cs0 @PushWFArc f4 '' #zField
Cs0 @PushWFArc f5 '' #zField
Cs0 @PushWFArc f17 '' #zField
Cs0 @PushWFArc f3 '' #zField
Cs0 @UdMethod f18 '' #zField
Cs0 @UdProcessEnd f19 '' #zField
Cs0 @GridStep f22 '' #zField
Cs0 @PushWFArc f23 '' #zField
Cs0 @PushWFArc f21 '' #zField
Cs0 @UdMethod f24 '' #zField
Cs0 @UdProcessEnd f25 '' #zField
Cs0 @GridStep f27 '' #zField
Cs0 @PushWFArc f28 '' #zField
Cs0 @PushWFArc f26 '' #zField
Cs0 @UdInit f31 '' #zField
Cs0 @UdProcessEnd f29 '' #zField
Cs0 @PushWFArc f32 '' #zField
Cs0 @UdMethod f33 '' #zField
Cs0 @GridStep f34 '' #zField
Cs0 @PushWFArc f36 '' #zField
Cs0 @UdProcessEnd f37 '' #zField
Cs0 @PushWFArc f38 '' #zField
>Proto Cs0 Cs0 TaskItemDetailsProcess #zField
Cs0 f0 guid 16BBB5787F4A8092 #txt
Cs0 f0 method start(ch.ivyteam.ivy.workflow.ITask,ch.ivy.addon.portalkit.datamodel.TaskLazyDataModel,ch.ivy.addon.portalkit.enums.PortalPage) #txt
Cs0 f0 inParameterDecl '<ch.ivyteam.ivy.workflow.ITask task,ch.ivy.addon.portalkit.datamodel.TaskLazyDataModel dataModel,ch.ivy.addon.portalkit.enums.PortalPage portalPage> param;' #txt
Cs0 f0 inParameterMapAction 'out.dataModel=param.dataModel;
out.portalPage=param.portalPage;
out.task=param.task;
' #txt
Cs0 f0 outParameterDecl '<> result;' #txt
Cs0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start(ITask,TaskLazyDataModel,PortalPage)</name>
    </language>
</elementInfo>
' #txt
Cs0 f0 83 51 26 26 -16 15 #rect
Cs0 f1 211 51 26 26 0 12 #rect
Cs0 f2 expr out #txt
Cs0 f2 109 64 211 64 #arcP
Cs0 f6 guid 16C0845B607C6BC7 #txt
Cs0 f6 method keepOldNameValue(javax.faces.event.ValueChangeEvent) #txt
Cs0 f6 inParameterDecl '<javax.faces.event.ValueChangeEvent valueChangeEvent> param;' #txt
Cs0 f6 inParameterMapAction 'out.oldName=param.valueChangeEvent.getOldValue() as String;
' #txt
Cs0 f6 outParameterDecl '<> result;' #txt
Cs0 f6 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>keepOldNameValue(valueChangeEvent)</name>
    </language>
</elementInfo>
' #txt
Cs0 f6 83 147 26 26 -86 15 #rect
Cs0 f7 371 147 26 26 0 12 #rect
Cs0 f8 actionTable 'out=in;
' #txt
Cs0 f8 actionCode 'import java.util.Arrays;

out.task.getCase().createNote(ivy.session, ivy.cms.co("/ch.ivy.addon.portalkit.ui.jsf/taskList/setNameNote", Arrays.asList(ivy.session.getSessionUser().getDisplayName(), in.oldName, in.task.getName())));' #txt
Cs0 f8 security system #txt
Cs0 f8 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Add note</name>
    </language>
</elementInfo>
' #txt
Cs0 f8 200 234 112 44 -24 -8 #rect
Cs0 f9 guid 16C0845B60E83C7D #txt
Cs0 f9 method updateTask() #txt
Cs0 f9 inParameterDecl '<> param;' #txt
Cs0 f9 outParameterDecl '<> result;' #txt
Cs0 f9 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>updateTask()</name>
    </language>
</elementInfo>
' #txt
Cs0 f9 83 243 26 26 -51 15 #rect
Cs0 f10 371 243 26 26 0 12 #rect
Cs0 f11 expr out #txt
Cs0 f11 109 256 200 256 #arcP
Cs0 f12 expr out #txt
Cs0 f12 312 256 371 256 #arcP
Cs0 f13 expr out #txt
Cs0 f13 109 160 371 160 #arcP
Cs0 f14 guid 16C23097336B39C2 #txt
Cs0 f14 method openTask() #txt
Cs0 f14 inParameterDecl '<> param;' #txt
Cs0 f14 outParameterDecl '<> result;' #txt
Cs0 f14 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>openTask()</name>
    </language>
</elementInfo>
' #txt
Cs0 f14 83 467 26 26 -46 17 #rect
Cs0 f45 actionTable 'out=in;
' #txt
Cs0 f45 actionCode 'import ch.ivy.addon.portalkit.util.TaskUtils;
import ch.ivy.addon.portalkit.jsf.ManagedBeans;
import ch.ivy.addon.portalkit.bean.TaskActionBean;

TaskActionBean taskActionBean = ManagedBeans.get("taskActionBean") as TaskActionBean;
in.task = TaskUtils.findTaskById(in.task.getId());
out.canUserResumeTask = taskActionBean.canResume(in.task);
' #txt
Cs0 f45 security system #txt
Cs0 f45 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Check if can resume</name>
    </language>
</elementInfo>
' #txt
Cs0 f45 160 458 128 44 -56 -8 #rect
Cs0 f15 579 467 26 26 0 12 #rect
Cs0 f16 expr out #txt
Cs0 f16 109 480 160 480 #arcP
Cs0 f77 338 466 28 28 14 0 #rect
Cs0 f68 595 563 26 26 0 12 #rect
Cs0 f66 actionTable 'out=in;
' #txt
Cs0 f66 actionCode 'import org.primefaces.PrimeFaces;
import ch.ivyteam.ivy.security.IUser;
import org.apache.commons.lang3.StringUtils;
import ch.ivyteam.ivy.workflow.TaskState;
import javax.faces.context.FacesContext;
import javax.faces.application.FacesMessage;

FacesContext facesContext = FacesContext.getCurrentInstance();
String notification;
if(in.task.getState() == TaskState.DONE){
	notification = ivy.cms.co("/ch.ivy.addon.portalkit.ui.jsf/components/taskStart/cannotStartMessages/taskDone", [in.task.getName()]);
} else {
	IUser worker = in.task.getWorkerUser();
	if (worker != null){
		String fullName = worker.getFullName();
		String workerName = StringUtils.isBlank(fullName) ? worker.getName() : worker.getFullName() + " (" + worker.getName() + ")";
		notification = ivy.cms.co("/ch.ivy.addon.portalkit.ui.jsf/components/taskStart/cannotStartMessages/isAnotherUserWorking", [in.task.name, in.task.getId(), workerName]);
	} else {
		notification = ivy.cms.co("/ch.ivy.addon.portalkit.ui.jsf/components/taskStart/cannotStartMessages/noPermission", [in.task.getName()]);
	}
}

facesContext.validationFailed();
facesContext.addMessage("portal-global-growl-message", new FacesMessage(FacesMessage.SEVERITY_INFO, notification, null));
PrimeFaces.current().ajax().update("portal-global-growl");  
' #txt
Cs0 f66 security system #txt
Cs0 f66 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Display error</name>
    </language>
</elementInfo>
' #txt
Cs0 f66 424 554 112 44 -35 -8 #rect
Cs0 f49 actionTable 'out=in;
' #txt
Cs0 f49 actionCode 'import ch.ivy.addon.portalkit.enums.PortalPage;
import ch.ivy.addon.portalkit.enums.NavigationHistory;
import ch.ivy.addon.portalkit.dto.TaskEndInfo;
import ch.ivy.addon.portalkit.service.StickyTaskListService;
import ch.ivy.addon.portalkit.util.SecurityServiceUtils;
import javax.faces.context.FacesContext;

TaskEndInfo taskEndInfo = new TaskEndInfo();
taskEndInfo.setDataModel(in.dataModel);
taskEndInfo.setPortalPage(in.portalPage);
taskEndInfo.setIsStartedInTaskDetails(true);

String taskEndInfoSessionAttributeKey = StickyTaskListService.service().getTaskEndInfoSessionAttributeKey(in.task.getId());
SecurityServiceUtils.setSessionAttribute(taskEndInfoSessionAttributeKey, taskEndInfo);

// Put the "embedInIFrame" param to the task start link to open it in the DefaultFramePage process
// Then this process will open task in IFrame or not based on its "embedInIFrame" String custom field
FacesContext.getCurrentInstance().getExternalContext().redirect(in.task.getStartLinkEmbedded().getRelative());' #txt
Cs0 f49 security system #txt
Cs0 f49 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Store task end info&#13;
Start task</name>
    </language>
</elementInfo>
' #txt
Cs0 f49 416 458 128 44 -44 -16 #rect
Cs0 f99 336 560 32 32 0 16 #rect
Cs0 f71 expr out #txt
Cs0 f71 536 576 595 576 #arcP
Cs0 f67 expr in #txt
Cs0 f67 368 576 424 576 #arcP
Cs0 f67 0 0.1527488616366867 0 0 #arcLabel
Cs0 f100 expr in #txt
Cs0 f100 352 494 352 560 #arcP
Cs0 f100 0 0.6038825846613081 0 0 #arcLabel
Cs0 f20 expr out #txt
Cs0 f20 288 480 338 480 #arcP
Cs0 f30 guid 16D4E180956E8D83 #txt
Cs0 f30 method resetAndOpenTask() #txt
Cs0 f30 inParameterDecl '<> param;' #txt
Cs0 f30 outParameterDecl '<> result;' #txt
Cs0 f30 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>resetAndOpenTask()</name>
    </language>
</elementInfo>
' #txt
Cs0 f30 83 355 26 26 -57 15 #rect
Cs0 f35 actionTable 'out=in;
' #txt
Cs0 f35 actionCode 'import ch.ivy.addon.portalkit.util.TaskUtils;

TaskUtils.resetTask(in.task);' #txt
Cs0 f35 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Reset task</name>
    </language>
</elementInfo>
' #txt
Cs0 f35 168 346 112 44 -29 -8 #rect
Cs0 f64 expr out #txt
Cs0 f64 109 368 168 368 #arcP
Cs0 f79 336 656 32 32 0 16 #rect
Cs0 f98 467 659 26 26 0 12 #rect
Cs0 f97 guid 16D4E1A305331132 #txt
Cs0 f97 method validate(ch.ivyteam.ivy.workflow.ITask) #txt
Cs0 f97 inParameterDecl '<ch.ivyteam.ivy.workflow.ITask task> param;' #txt
Cs0 f97 inParameterMapAction 'out.task=param.task;
' #txt
Cs0 f97 outParameterDecl '<> result;' #txt
Cs0 f97 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>validate(ITask)</name>
    </language>
</elementInfo>
' #txt
Cs0 f97 83 659 26 26 -60 16 #rect
Cs0 f80 actionTable 'out=in;
' #txt
Cs0 f80 actionCode 'import ch.ivy.addon.portalkit.jsf.ManagedBeans;
import ch.ivy.addon.portalkit.bean.TaskActionBean;

TaskActionBean taskActionBean = ManagedBeans.get("taskActionBean") as TaskActionBean;
out.canUserResumeTask = taskActionBean.canResume(in.task);
' #txt
Cs0 f80 security system #txt
Cs0 f80 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Check if can resume</name>
    </language>
</elementInfo>
' #txt
Cs0 f80 160 650 128 44 -56 -8 #rect
Cs0 f92 expr in #txt
Cs0 f92 outCond in.canUserResumeTask #txt
Cs0 f92 368 672 467 672 #arcP
Cs0 f87 expr out #txt
Cs0 f87 288 672 336 672 #arcP
Cs0 f81 expr out #txt
Cs0 f81 109 672 160 672 #arcP
Cs0 f4 expr in #txt
Cs0 f4 352 656 352 592 #arcP
Cs0 f5 544 480 579 480 #arcP
Cs0 f17 expr out #txt
Cs0 f17 280 368 480 458 #arcP
Cs0 f17 1 480 368 #addKink
Cs0 f17 0 0.7613470524461249 0 0 #arcLabel
Cs0 f3 expr in #txt
Cs0 f3 outCond in.canUserResumeTask #txt
Cs0 f3 366 480 416 480 #arcP
Cs0 f18 guid 171F37E3B7068238 #txt
Cs0 f18 method getHideTaskDocumentConfiguration() #txt
Cs0 f18 inParameterDecl '<> param;' #txt
Cs0 f18 outParameterDecl '<> result;' #txt
Cs0 f18 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>getHideTaskDocumentConfiguration()</name>
    </language>
</elementInfo>
' #txt
Cs0 f18 83 787 26 26 -84 23 #rect
Cs0 f19 499 787 26 26 0 12 #rect
Cs0 f22 actionTable 'out=in;
' #txt
Cs0 f22 actionCode 'import ch.ivy.addon.portalkit.enums.GlobalVariable;
import ch.ivy.addon.portalkit.service.GlobalSettingService;

// Get the latest configuration of HIDE_TASK_DOCUMENT in GlobalSettingService
// If null or empty, will return false
in.isHideTaskDocument = new GlobalSettingService().findGlobalSettingValueAsBoolean(GlobalVariable.HIDE_TASK_DOCUMENT);' #txt
Cs0 f22 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Find HIDE_TASK_DOCUMENT configuration</name>
    </language>
</elementInfo>
' #txt
Cs0 f22 192 778 256 44 -121 -8 #rect
Cs0 f23 109 800 192 800 #arcP
Cs0 f21 448 800 499 800 #arcP
Cs0 f24 guid 172BB67713C7C1B0 #txt
Cs0 f24 method destroyTask(ch.ivyteam.ivy.workflow.ITask) #txt
Cs0 f24 inParameterDecl '<ch.ivyteam.ivy.workflow.ITask selectedTask> param;' #txt
Cs0 f24 inParameterMapAction 'out.task=param.selectedTask;
' #txt
Cs0 f24 outParameterDecl '<> result;' #txt
Cs0 f24 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>destroyTask(ITask)</name>
    </language>
</elementInfo>
' #txt
Cs0 f24 83 915 26 26 -25 15 #rect
Cs0 f25 355 915 26 26 0 12 #rect
Cs0 f27 actionTable 'out=in;
' #txt
Cs0 f27 actionCode 'import ch.ivy.addon.portalkit.util.TaskUtils;

Long destroyedTaskId = in.task.getId();
TaskUtils.destroyTaskById(destroyedTaskId);
in.task = TaskUtils.findTaskById(destroyedTaskId);' #txt
Cs0 f27 security system #txt
Cs0 f27 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Destroy task</name>
    </language>
</elementInfo>
' #txt
Cs0 f27 184 906 112 44 -34 -8 #rect
Cs0 f28 109 928 184 928 #arcP
Cs0 f26 296 928 355 928 #arcP
Cs0 f31 guid 1773CE142B3C51EE #txt
Cs0 f31 method startFromDashboard(ch.ivyteam.ivy.workflow.ITask,ch.ivy.addon.portalkit.enums.PortalPage) #txt
Cs0 f31 inParameterDecl '<ch.ivyteam.ivy.workflow.ITask task,ch.ivy.addon.portalkit.enums.PortalPage portalPage> param;' #txt
Cs0 f31 inParameterMapAction 'out.portalPage=param.portalPage;
out.task=param.task;
' #txt
Cs0 f31 outParameterDecl '<> result;' #txt
Cs0 f31 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>startFromDashboard(ITask,PortalPage)</name>
    </language>
</elementInfo>
' #txt
Cs0 f31 627 51 26 26 -16 15 #rect
Cs0 f29 915 51 26 26 0 12 #rect
Cs0 f32 653 64 915 64 #arcP
Cs0 f33 guid 1803BC11F3D7DFB0 #txt
Cs0 f33 method expiryTask(ch.ivyteam.ivy.workflow.ITask) #txt
Cs0 f33 inParameterDecl '<ch.ivyteam.ivy.workflow.ITask selectedTask> param;' #txt
Cs0 f33 inParameterMapAction 'out.task=param.selectedTask;
' #txt
Cs0 f33 outParameterDecl '<> result;' #txt
Cs0 f33 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>expiryTask(ITask)</name>
    </language>
</elementInfo>
' #txt
Cs0 f33 75 1027 26 26 -25 15 #rect
Cs0 f34 actionTable 'out=in;
' #txt
Cs0 f34 actionCode 'import ch.ivy.addon.portalkit.util.TaskUtils;

Long taskId = in.task.getId();
TaskUtils.expiryTaskById(taskId);
in.task = TaskUtils.findTaskById(taskId);' #txt
Cs0 f34 security system #txt
Cs0 f34 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Expiry task </name>
    </language>
</elementInfo>
' #txt
Cs0 f34 184 1018 112 44 -30 -8 #rect
Cs0 f36 101 1040 184 1040 #arcP
Cs0 f37 355 1027 26 26 0 12 #rect
Cs0 f38 296 1040 355 1040 #arcP
>Proto Cs0 .type ch.ivy.addon.portalkit.component.TaskItemDetails.TaskItemDetailsData #txt
>Proto Cs0 .processKind HTML_DIALOG #txt
>Proto Cs0 -8 -8 16 16 16 26 #rect
Cs0 f0 mainOut f2 tail #connect
Cs0 f2 head f1 mainIn #connect
Cs0 f6 mainOut f13 tail #connect
Cs0 f13 head f7 mainIn #connect
Cs0 f9 mainOut f11 tail #connect
Cs0 f11 head f8 mainIn #connect
Cs0 f8 mainOut f12 tail #connect
Cs0 f12 head f10 mainIn #connect
Cs0 f14 mainOut f16 tail #connect
Cs0 f16 head f45 mainIn #connect
Cs0 f66 mainOut f71 tail #connect
Cs0 f71 head f68 mainIn #connect
Cs0 f100 head f99 in #connect
Cs0 f99 out f67 tail #connect
Cs0 f67 head f66 mainIn #connect
Cs0 f45 mainOut f20 tail #connect
Cs0 f20 head f77 in #connect
Cs0 f30 mainOut f64 tail #connect
Cs0 f64 head f35 mainIn #connect
Cs0 f97 mainOut f81 tail #connect
Cs0 f81 head f80 mainIn #connect
Cs0 f80 mainOut f87 tail #connect
Cs0 f87 head f79 in #connect
Cs0 f79 out f92 tail #connect
Cs0 f92 head f98 mainIn #connect
Cs0 f79 out f4 tail #connect
Cs0 f4 head f99 in #connect
Cs0 f49 mainOut f5 tail #connect
Cs0 f5 head f15 mainIn #connect
Cs0 f35 mainOut f17 tail #connect
Cs0 f17 head f49 mainIn #connect
Cs0 f77 out f3 tail #connect
Cs0 f3 head f49 mainIn #connect
Cs0 f77 out f100 tail #connect
Cs0 f18 mainOut f23 tail #connect
Cs0 f23 head f22 mainIn #connect
Cs0 f22 mainOut f21 tail #connect
Cs0 f21 head f19 mainIn #connect
Cs0 f24 mainOut f28 tail #connect
Cs0 f28 head f27 mainIn #connect
Cs0 f27 mainOut f26 tail #connect
Cs0 f26 head f25 mainIn #connect
Cs0 f31 mainOut f32 tail #connect
Cs0 f32 head f29 mainIn #connect
Cs0 f33 mainOut f36 tail #connect
Cs0 f36 head f34 mainIn #connect
Cs0 f34 mainOut f38 tail #connect
Cs0 f38 head f37 mainIn #connect
