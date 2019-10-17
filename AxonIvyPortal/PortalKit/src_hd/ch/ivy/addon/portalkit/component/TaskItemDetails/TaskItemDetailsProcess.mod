[Ivy]
16BFA568D95D2EA0 7.5.0 #module
>Proto >Proto Collection #zClass
Cs0 TaskItemDetailsProcess Big #zClass
Cs0 RD #cInfo
Cs0 #process
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
Cs0 @GridStep f19 '' #zField
Cs0 @UdProcessEnd f68 '' #zField
Cs0 @GridStep f56 '' #zField
Cs0 @GridStep f54 '' #zField
Cs0 @GridStep f66 '' #zField
Cs0 @GridStep f49 '' #zField
Cs0 @Alternative f99 '' #zField
Cs0 @PushWFArc f71 '' #zField
Cs0 @PushWFArc f73 '' #zField
Cs0 @PushWFArc f57 '' #zField
Cs0 @PushWFArc f67 '' #zField
Cs0 @PushWFArc f100 '' #zField
Cs0 @PushWFArc f55 '' #zField
Cs0 @PushWFArc f50 '' #zField
Cs0 @PushWFArc f20 '' #zField
Cs0 @PushWFArc f17 '' #zField
Cs0 @UdMethod f30 '' #zField
Cs0 @GridStep f35 '' #zField
Cs0 @PushWFArc f64 '' #zField
Cs0 @PushWFArc f3 '' #zField
Cs0 @Alternative f79 '' #zField
Cs0 @UdProcessEnd f98 '' #zField
Cs0 @UdMethod f97 '' #zField
Cs0 @GridStep f80 '' #zField
Cs0 @PushWFArc f92 '' #zField
Cs0 @PushWFArc f87 '' #zField
Cs0 @PushWFArc f81 '' #zField
Cs0 @PushWFArc f4 '' #zField
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
Cs0 f0 @|UdInitIcon #fIcon
Cs0 f1 211 51 26 26 0 12 #rect
Cs0 f1 @|UdProcessEndIcon #fIcon
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
Cs0 f6 @|UdMethodIcon #fIcon
Cs0 f7 371 147 26 26 0 12 #rect
Cs0 f7 @|UdProcessEndIcon #fIcon
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
Cs0 f8 @|StepIcon #fIcon
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
Cs0 f9 @|UdMethodIcon #fIcon
Cs0 f10 371 243 26 26 0 12 #rect
Cs0 f10 @|UdProcessEndIcon #fIcon
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
Cs0 f14 @|UdMethodIcon #fIcon
Cs0 f45 actionTable 'out=in;
' #txt
Cs0 f45 actionCode 'import ch.ivy.addon.portalkit.jsf.ManagedBeans;
import ch.ivy.addon.portalkit.bean.TaskActionBean;

TaskActionBean taskActionBean = ManagedBeans.get("taskActionBean") as TaskActionBean;
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
Cs0 f45 @|StepIcon #fIcon
Cs0 f15 1011 467 26 26 0 12 #rect
Cs0 f15 @|UdProcessEndIcon #fIcon
Cs0 f16 expr out #txt
Cs0 f16 109 480 160 480 #arcP
Cs0 f77 338 466 28 28 14 0 #rect
Cs0 f77 @|AlternativeIcon #fIcon
Cs0 f19 actionTable 'out=in;
' #txt
Cs0 f19 actionCode 'import ch.ivyteam.ivy.request.RequestUriFactory;
import java.net.URI;
import ch.ivyteam.ivy.server.ServerFactory;
import ch.ivyteam.ivy.application.IApplicationConfigurationManager;
import javax.faces.context.FacesContext;

IApplicationConfigurationManager appConfig = ServerFactory.getServer().getApplicationConfigurationManager();
URI taskUri = RequestUriFactory.createTaskStartUri(appConfig, in.task);
FacesContext.getCurrentInstance().getExternalContext().redirect(taskUri.toString());' #txt
Cs0 f19 security system #txt
Cs0 f19 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Open task</name>
    </language>
</elementInfo>
' #txt
Cs0 f19 872 458 112 44 -28 -8 #rect
Cs0 f19 @|StepIcon #fIcon
Cs0 f68 595 563 26 26 0 12 #rect
Cs0 f68 @|UdProcessEndIcon #fIcon
Cs0 f56 actionTable 'out=in;
' #txt
Cs0 f56 actionCode 'import javax.faces.context.FacesContext;
import ch.ivyteam.ivy.server.ServerFactory;
import ch.ivyteam.ivy.request.RequestUriFactory;
import javax.servlet.http.HttpServletRequest;
import ch.ivy.addon.portalkit.support.UrlDetector;
import ch.ivyteam.ivy.workflow.IProcessStart;
import ch.ivy.addon.portalkit.service.ProcessStartCollector;

ProcessStartCollector collector = new ProcessStartCollector(ivy.wf.getApplication());
java.util.List<IProcessStart> processStarts = collector.findProcessStartRequestPathContainsKeyword("restorePortalTaskList.ivp");
UrlDetector urlDetector = new UrlDetector();
HttpServletRequest request = FacesContext.getCurrentInstance().getExternalContext().getRequest() as HttpServletRequest;
out.callbackUrl = urlDetector.getBaseURLWithoutContextPath(request) + RequestUriFactory.createProcessStartUri(ServerFactory.getServer().getApplicationConfigurationManager(), processStarts.get(0));' #txt
Cs0 f56 security system #txt
Cs0 f56 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Build callbackUrl</name>
        <nameStyle>17
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f56 408 458 112 44 -46 -8 #rect
Cs0 f56 @|StepIcon #fIcon
Cs0 f54 actionTable 'out=in;
' #txt
Cs0 f54 actionCode 'import ch.ivy.addon.portalkit.enums.AdditionalProperty;

in.task.setAdditionalProperty(AdditionalProperty.PORTAL_TASK_CALLBACK_URI.toString(), in.callbackUrl);' #txt
Cs0 f54 security system #txt
Cs0 f54 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Set additional property</name>
    </language>
</elementInfo>
' #txt
Cs0 f54 560 458 128 44 -61 -8 #rect
Cs0 f54 @|StepIcon #fIcon
Cs0 f66 actionTable 'out=in;
' #txt
Cs0 f66 actionCode 'import ch.ivyteam.ivy.security.IUser;
import org.apache.commons.lang3.StringUtils;
import ch.ivyteam.ivy.workflow.TaskState;
import javax.faces.context.FacesContext;
import javax.faces.application.FacesMessage;
import org.primefaces.context.RequestContext;

RequestContext requesContext = RequestContext.getCurrentInstance();
FacesContext facesContext = FacesContext.getCurrentInstance();

String notification;
if(in.task.getState() == TaskState.DONE){
	notification = ivy.cms.co("/ch.ivy.addon.portalkit.ui.jsf/components/taskStart/cannotStartMessages/taskDone", [in.task.getName()]);
} else {
	IUser worker = in.task.getWorkerUser();
	if (worker != null){
		String fullName = worker.getFullName();
		String workerName = StringUtils.isBlank(fullName) ? worker.getName() : worker.getFullName() + " (" + worker.getName() + ")";
		notification = ivy.cms.co("/ch.ivy.addon.portalkit.ui.jsf/components/taskStart/cannotStartMessages/isAnotherUserWorking", [in.task.name, in.task.getId(), worker]);
	} else {
		notification = ivy.cms.co("/ch.ivy.addon.portalkit.ui.jsf/components/taskStart/cannotStartMessages/noPermission", [in.task.getName()]);
	}
}

facesContext.validationFailed();
facesContext.addMessage("portal-global-growl", new FacesMessage(FacesMessage.SEVERITY_INFO, notification, null));
requesContext.update("portal-global-growl");  
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
Cs0 f66 @|StepIcon #fIcon
Cs0 f49 actionTable 'out=in;
' #txt
Cs0 f49 actionCode 'import ch.ivy.addon.portalkit.enums.NavigationHistory;
import ch.ivy.addon.portalkit.dto.TaskEndInfo;
import ch.ivy.addon.portalkit.service.StickyTaskListService;
import ch.ivy.addon.portalkit.util.SecurityServiceUtils;

TaskEndInfo taskEndInfo = new TaskEndInfo();
taskEndInfo.setDataModel(in.dataModel);
taskEndInfo.setPortalPage(in.portalPage);
taskEndInfo.setIsStartedInTaskDetails(true);

String taskDataModelSessionAttributeKey = StickyTaskListService.service().getTaskEndInfoSessionAttributeKey(in.task.getId());
SecurityServiceUtils.setSessionAttribute(taskDataModelSessionAttributeKey, taskEndInfo);' #txt
Cs0 f49 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Store task end info</name>
    </language>
</elementInfo>
' #txt
Cs0 f49 728 458 112 44 -51 -8 #rect
Cs0 f49 @|StepIcon #fIcon
Cs0 f99 336 560 32 32 0 16 #rect
Cs0 f99 @|AlternativeIcon #fIcon
Cs0 f71 expr out #txt
Cs0 f71 536 576 595 576 #arcP
Cs0 f73 expr out #txt
Cs0 f73 688 480 728 480 #arcP
Cs0 f57 expr in #txt
Cs0 f57 outCond in.canUserResumeTask #txt
Cs0 f57 366 480 408 480 #arcP
Cs0 f67 expr in #txt
Cs0 f67 368 576 424 576 #arcP
Cs0 f67 0 0.1527488616366867 0 0 #arcLabel
Cs0 f100 expr in #txt
Cs0 f100 352 494 352 560 #arcP
Cs0 f100 0 0.6038825846613081 0 0 #arcLabel
Cs0 f55 expr out #txt
Cs0 f55 520 480 560 480 #arcP
Cs0 f50 expr out #txt
Cs0 f50 840 480 872 480 #arcP
Cs0 f20 expr out #txt
Cs0 f20 288 480 338 480 #arcP
Cs0 f17 expr out #txt
Cs0 f17 984 480 1011 480 #arcP
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
Cs0 f30 @|UdMethodIcon #fIcon
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
Cs0 f35 @|StepIcon #fIcon
Cs0 f64 expr out #txt
Cs0 f64 109 368 168 368 #arcP
Cs0 f3 expr out #txt
Cs0 f3 280 368 464 458 #arcP
Cs0 f3 1 464 368 #addKink
Cs0 f3 0 0.7613470524461249 0 0 #arcLabel
Cs0 f79 336 656 32 32 0 16 #rect
Cs0 f79 @|AlternativeIcon #fIcon
Cs0 f98 467 659 26 26 0 12 #rect
Cs0 f98 @|UdProcessEndIcon #fIcon
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
Cs0 f97 @|UdMethodIcon #fIcon
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
Cs0 f80 @|StepIcon #fIcon
Cs0 f92 expr in #txt
Cs0 f92 outCond in.canUserResumeTask #txt
Cs0 f92 368 672 467 672 #arcP
Cs0 f87 expr out #txt
Cs0 f87 288 672 336 672 #arcP
Cs0 f81 expr out #txt
Cs0 f81 109 672 160 672 #arcP
Cs0 f4 expr in #txt
Cs0 f4 352 656 352 592 #arcP
>Proto Cs0 .type ch.ivy.addon.portalkit.component.TaskItemDetails.TaskItemDetailsData #txt
>Proto Cs0 .processKind HTML_DIALOG #txt
>Proto Cs0 -8 -8 16 16 16 26 #rect
>Proto Cs0 '' #fIcon
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
Cs0 f77 out f57 tail #connect
Cs0 f57 head f56 mainIn #connect
Cs0 f66 mainOut f71 tail #connect
Cs0 f71 head f68 mainIn #connect
Cs0 f49 mainOut f50 tail #connect
Cs0 f50 head f19 mainIn #connect
Cs0 f56 mainOut f55 tail #connect
Cs0 f55 head f54 mainIn #connect
Cs0 f54 mainOut f73 tail #connect
Cs0 f73 head f49 mainIn #connect
Cs0 f77 out f100 tail #connect
Cs0 f100 head f99 in #connect
Cs0 f99 out f67 tail #connect
Cs0 f67 head f66 mainIn #connect
Cs0 f45 mainOut f20 tail #connect
Cs0 f20 head f77 in #connect
Cs0 f19 mainOut f17 tail #connect
Cs0 f17 head f15 mainIn #connect
Cs0 f30 mainOut f64 tail #connect
Cs0 f64 head f35 mainIn #connect
Cs0 f35 mainOut f3 tail #connect
Cs0 f3 head f56 mainIn #connect
Cs0 f97 mainOut f81 tail #connect
Cs0 f81 head f80 mainIn #connect
Cs0 f80 mainOut f87 tail #connect
Cs0 f87 head f79 in #connect
Cs0 f79 out f92 tail #connect
Cs0 f92 head f98 mainIn #connect
Cs0 f79 out f4 tail #connect
Cs0 f4 head f99 in #connect
