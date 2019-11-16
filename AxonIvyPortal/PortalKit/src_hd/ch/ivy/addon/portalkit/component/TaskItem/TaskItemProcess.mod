[Ivy]
150CB86EFC2F2972 7.5.0 #module
>Proto >Proto Collection #zClass
Ts0 TaskItemProcess Big #zClass
Ts0 RD #cInfo
Ts0 #process
Ts0 @TextInP .type .type #zField
Ts0 @TextInP .processKind .processKind #zField
Ts0 @AnnotationInP-0n ai ai #zField
Ts0 @MessageFlowInP-0n messageIn messageIn #zField
Ts0 @MessageFlowOutP-0n messageOut messageOut #zField
Ts0 @TextInP .xml .xml #zField
Ts0 @TextInP .responsibility .responsibility #zField
Ts0 @UdProcessEnd f94 '' #zField
Ts0 @UdProcessEnd f46 '' #zField
Ts0 @Alternative f79 '' #zField
Ts0 @UdEvent f36 '' #zField
Ts0 @PushWFArc f92 '' #zField
Ts0 @UdProcessEnd f98 '' #zField
Ts0 @UdProcessEnd f17 '' #zField
Ts0 @UdProcessEnd f70 '' #zField
Ts0 @GridStep f56 '' #zField
Ts0 @UdMethod f33 '' #zField
Ts0 @PushWFArc f55 '' #zField
Ts0 @GridStep f54 '' #zField
Ts0 @UdProcessEnd f1 '' #zField
Ts0 @UdProcessEnd f3 '' #zField
Ts0 @UdMethod f108 '' #zField
Ts0 @UdMethod f30 '' #zField
Ts0 @PushWFArc f87 '' #zField
Ts0 @GridStep f80 '' #zField
Ts0 @GridStep f66 '' #zField
Ts0 @PushWFArc f57 '' #zField
Ts0 @Alternative f77 '' #zField
Ts0 @PushWFArc f110 '' #zField
Ts0 @UdProcessEnd f109 '' #zField
Ts0 @PushWFArc f101 '' #zField
Ts0 @PushWFArc f67 '' #zField
Ts0 @PushWFArc f100 '' #zField
Ts0 @Alternative f99 '' #zField
Ts0 @PushWFArc f95 '' #zField
Ts0 @UdMethod f76 '' #zField
Ts0 @PushWFArc f73 '' #zField
Ts0 @GridStep f49 '' #zField
Ts0 @PushWFArc f81 '' #zField
Ts0 @UdMethod f97 '' #zField
Ts0 @PushWFArc f71 '' #zField
Ts0 @UdProcessEnd f68 '' #zField
Ts0 @PushWFArc f21 '' #zField
Ts0 @PushWFArc f5 '' #zField
Ts0 @GridStep f4 '' #zField
Ts0 @PushWFArc f69 '' #zField
Ts0 @UdMethod f96 '' #zField
Ts0 @PushWFArc f65 '' #zField
Ts0 @PushWFArc f64 '' #zField
Ts0 @GridStep f35 '' #zField
Ts0 @PushWFArc f60 '' #zField
Ts0 @PushWFArc f58 '' #zField
Ts0 @GridStep f45 '' #zField
Ts0 @UdInit f0 '' #zField
Ts0 @UdMethod f6 '' #zField
Ts0 @CallSub f7 '' #zField
Ts0 @PushWFArc f8 '' #zField
Ts0 @PushWFArc f9 '' #zField
Ts0 @PushWFArc f2 '' #zField
>Proto Ts0 Ts0 TaskItemProcess #zField
Ts0 f94 339 883 26 26 0 12 #rect
Ts0 f94 @|UdProcessEndIcon #fIcon
Ts0 f46 915 299 26 26 0 12 #rect
Ts0 f46 @|UdProcessEndIcon #fIcon
Ts0 f79 336 488 32 32 0 16 #rect
Ts0 f79 @|AlternativeIcon #fIcon
Ts0 f36 guid 16816B4C738DA4B0 #txt
Ts0 f36 actionTable 'out=in;
' #txt
Ts0 f36 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>changeAssignType</name>
    </language>
</elementInfo>
' #txt
Ts0 f36 83 683 26 26 -47 15 #rect
Ts0 f36 @|UdEventIcon #fIcon
Ts0 f92 expr in #txt
Ts0 f92 outCond in.canUserResumeTask #txt
Ts0 f92 368 504 467 504 #arcP
Ts0 f98 467 491 26 26 0 12 #rect
Ts0 f98 @|UdProcessEndIcon #fIcon
Ts0 f17 -106 1334 20 20 13 0 #rect
Ts0 f17 @|UdProcessEndIcon #fIcon
Ts0 f70 339 979 26 26 0 12 #rect
Ts0 f70 @|UdProcessEndIcon #fIcon
Ts0 f56 actionTable 'out=in;
' #txt
Ts0 f56 actionCode 'import javax.faces.context.FacesContext;
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
Ts0 f56 security system #txt
Ts0 f56 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Build callbackUrl</name>
        <nameStyle>17
</nameStyle>
    </language>
</elementInfo>
' #txt
Ts0 f56 424 290 112 44 -46 -8 #rect
Ts0 f56 @|StepIcon #fIcon
Ts0 f33 guid 16816C4D9766CFA8 #txt
Ts0 f33 method openTask(ch.ivyteam.ivy.workflow.ITask,ch.ivy.addon.portalkit.datamodel.TaskLazyDataModel) #txt
Ts0 f33 inParameterDecl '<ch.ivyteam.ivy.workflow.ITask task,ch.ivy.addon.portalkit.datamodel.TaskLazyDataModel dataModel> param;' #txt
Ts0 f33 inParameterMapAction 'out.dataModel=param.dataModel;
out.task=param.task;
' #txt
Ts0 f33 outParameterDecl '<> result;' #txt
Ts0 f33 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>openTask(ITask,TaskLazyDataModel)</name>
    </language>
</elementInfo>
' #txt
Ts0 f33 83 299 26 26 -80 15 #rect
Ts0 f33 @|UdMethodIcon #fIcon
Ts0 f55 expr out #txt
Ts0 f55 536 312 576 312 #arcP
Ts0 f54 actionTable 'out=in;
' #txt
Ts0 f54 actionCode 'import ch.ivy.addon.portalkit.enums.AdditionalProperty;

in.task.customFields().stringField(AdditionalProperty.PORTAL_TASK_CALLBACK_URI.toString()).set(in.callbackUrl);' #txt
Ts0 f54 security system #txt
Ts0 f54 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Set additional property</name>
    </language>
</elementInfo>
' #txt
Ts0 f54 576 290 128 44 -61 -8 #rect
Ts0 f54 @|StepIcon #fIcon
Ts0 f1 339 83 26 26 0 12 #rect
Ts0 f1 @|UdProcessEndIcon #fIcon
Ts0 f3 339 683 26 26 0 12 #rect
Ts0 f3 @|UdProcessEndIcon #fIcon
Ts0 f108 guid 161932E544273665 #txt
Ts0 f108 method setSelectedTaskAndDataModel(ch.ivyteam.ivy.workflow.ITask,ch.ivy.addon.portalkit.datamodel.TaskLazyDataModel) #txt
Ts0 f108 inParameterDecl '<ch.ivyteam.ivy.workflow.ITask task,ch.ivy.addon.portalkit.datamodel.TaskLazyDataModel dataModel> param;' #txt
Ts0 f108 inParameterMapAction 'out.dataModel=param.dataModel;
out.task=param.task;
' #txt
Ts0 f108 outParameterDecl '<> result;' #txt
Ts0 f108 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>setSelectedTaskAndDataModel(ITask,TaskLazyDataModel)</name>
    </language>
</elementInfo>
' #txt
Ts0 f108 83 587 26 26 -85 17 #rect
Ts0 f108 @|UdMethodIcon #fIcon
Ts0 f30 guid 16816C2D98C57DA7 #txt
Ts0 f30 method resetAndOpenTask() #txt
Ts0 f30 inParameterDecl '<> param;' #txt
Ts0 f30 outParameterDecl '<> result;' #txt
Ts0 f30 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>resetAndOpenTask()</name>
    </language>
</elementInfo>
' #txt
Ts0 f30 83 203 26 26 -57 15 #rect
Ts0 f30 @|UdMethodIcon #fIcon
Ts0 f87 expr out #txt
Ts0 f87 288 504 336 504 #arcP
Ts0 f80 actionTable 'out=in;
' #txt
Ts0 f80 actionCode 'import ch.ivy.addon.portalkit.jsf.ManagedBeans;
import ch.ivy.addon.portalkit.bean.TaskActionBean;

TaskActionBean taskActionBean = ManagedBeans.get("taskActionBean") as TaskActionBean;
out.canUserResumeTask = taskActionBean.canResume(in.task);' #txt
Ts0 f80 security system #txt
Ts0 f80 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Check if can resume</name>
    </language>
</elementInfo>
' #txt
Ts0 f80 160 482 128 44 -56 -8 #rect
Ts0 f80 @|StepIcon #fIcon
Ts0 f66 actionTable 'out=in;
' #txt
Ts0 f66 actionCode 'import org.primefaces.PrimeFaces;
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
		notification = ivy.cms.co("/ch.ivy.addon.portalkit.ui.jsf/components/taskStart/cannotStartMessages/isAnotherUserWorking", [in.task.name, in.task.getId(), worker]);
	} else {
		notification = ivy.cms.co("/ch.ivy.addon.portalkit.ui.jsf/components/taskStart/cannotStartMessages/noPermission", [in.task.getName()]);
	}
}

facesContext.validationFailed();
facesContext.addMessage("portal-global-growl-message", new FacesMessage(FacesMessage.SEVERITY_INFO, notification, null));
PrimeFaces.current().ajax().update("portal-global-growl");  
' #txt
Ts0 f66 security system #txt
Ts0 f66 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Display error</name>
    </language>
</elementInfo>
' #txt
Ts0 f66 424 386 112 44 -35 -8 #rect
Ts0 f66 @|StepIcon #fIcon
Ts0 f57 expr in #txt
Ts0 f57 outCond in.canUserResumeTask #txt
Ts0 f57 366 312 424 312 #arcP
Ts0 f77 338 298 28 28 14 0 #rect
Ts0 f77 @|AlternativeIcon #fIcon
Ts0 f110 expr out #txt
Ts0 f110 109 600 339 600 #arcP
Ts0 f110 0 0.49999999999999994 0 0 #arcLabel
Ts0 f109 339 587 26 26 0 12 #rect
Ts0 f109 @|UdProcessEndIcon #fIcon
Ts0 f101 expr in #txt
Ts0 f101 352 488 352 424 #arcP
Ts0 f67 expr in #txt
Ts0 f67 368 408 424 408 #arcP
Ts0 f67 0 0.1527488616366867 0 0 #arcLabel
Ts0 f100 expr in #txt
Ts0 f100 352 326 352 392 #arcP
Ts0 f100 0 0.6038825846613081 0 0 #arcLabel
Ts0 f99 336 392 32 32 0 16 #rect
Ts0 f99 @|AlternativeIcon #fIcon
Ts0 f95 expr out #txt
Ts0 f95 109 992 339 992 #arcP
Ts0 f76 guid 15F8F5A744248DE3 #txt
Ts0 f76 method autoCompleteForUserDelegate(String) #txt
Ts0 f76 inParameterDecl '<String query> param;' #txt
Ts0 f76 inParameterMapAction 'out.queryAutoComplete=param.query;
' #txt
Ts0 f76 outParameterDecl '<java.util.List<ch.ivyteam.ivy.security.IUser> usersToDelegate> result;' #txt
Ts0 f76 outActionCode 'import ch.ivy.addon.portalkit.util.UserUtils;

result.usersToDelegate = UserUtils.filterUsers(in.usersToDelegate, in.queryAutoComplete);
' #txt
Ts0 f76 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>autoCompleteForUserDelegate(String)</name>
        <nameStyle>35,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ts0 f76 83 979 26 26 -52 12 #rect
Ts0 f76 @|UdMethodIcon #fIcon
Ts0 f73 expr out #txt
Ts0 f73 704 312 744 312 #arcP
Ts0 f49 actionTable 'out=in;
' #txt
Ts0 f49 actionCode 'import ch.ivy.addon.portalkit.enums.PortalPage;
import ch.ivy.addon.portalkit.enums.NavigationHistory;
import ch.ivy.addon.portalkit.dto.TaskEndInfo;
import ch.ivy.addon.portalkit.service.StickyTaskListService;
import ch.ivy.addon.portalkit.util.SecurityServiceUtils;
import ch.ivy.addon.portalkit.enums.GlobalVariable;
import ch.ivy.addon.portalkit.service.GlobalSettingService;
import javax.faces.context.FacesContext;

GlobalSettingService service = new GlobalSettingService();
boolean enabledStartInIFrame = Boolean.parseBoolean(service.findGlobalSettingValue(GlobalVariable.START_IN_IFRAME.toString()));

TaskEndInfo taskEndInfo = new TaskEndInfo();
taskEndInfo.setDataModel(in.dataModel);
taskEndInfo.setPortalPage(PortalPage.valueOf(in.currentPortalPage));
taskEndInfo.setIsInIFrame(enabledStartInIFrame);

String taskEndInfoSessionAttributeKey = StickyTaskListService.service().getTaskEndInfoSessionAttributeKey(in.task.getId());
SecurityServiceUtils.setSessionAttribute(taskEndInfoSessionAttributeKey, taskEndInfo);

String taskStart = ivy.html.taskStartRef(in.task);
if (enabledStartInIFrame) {
	taskStart = ivy.html.taskStartInFrameRef(in.task);
}
FacesContext.getCurrentInstance().getExternalContext().redirect(taskStart);' #txt
Ts0 f49 security system #txt
Ts0 f49 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Store task end info&#13;
Start task</name>
    </language>
</elementInfo>
' #txt
Ts0 f49 744 290 128 44 -44 -16 #rect
Ts0 f49 @|StepIcon #fIcon
Ts0 f81 expr out #txt
Ts0 f81 109 504 160 504 #arcP
Ts0 f97 guid 1602EFA80B079E1D #txt
Ts0 f97 method validate(ch.ivyteam.ivy.workflow.ITask,ch.ivy.addon.portalkit.datamodel.TaskLazyDataModel) #txt
Ts0 f97 inParameterDecl '<ch.ivyteam.ivy.workflow.ITask task,ch.ivy.addon.portalkit.datamodel.TaskLazyDataModel dataModel> param;' #txt
Ts0 f97 inParameterMapAction 'out.dataModel=param.dataModel;
out.task=param.task;
' #txt
Ts0 f97 outParameterDecl '<> result;' #txt
Ts0 f97 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>validate(ITask,TaskLazyDataModel)</name>
    </language>
</elementInfo>
' #txt
Ts0 f97 83 491 26 26 -60 16 #rect
Ts0 f97 @|UdMethodIcon #fIcon
Ts0 f71 expr out #txt
Ts0 f71 536 408 595 408 #arcP
Ts0 f68 595 395 26 26 0 12 #rect
Ts0 f68 @|UdProcessEndIcon #fIcon
Ts0 f21 expr out #txt
Ts0 f21 109 696 152 696 #arcP
Ts0 f5 expr out #txt
Ts0 f5 296 696 339 696 #arcP
Ts0 f4 actionTable 'out=in;
' #txt
Ts0 f4 actionCode 'out.delegatedSecurityMember = null;
out.disabledDelegateButton = true;
' #txt
Ts0 f4 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Change assignee type&#xD;
to delegate</name>
    </language>
</elementInfo>
' #txt
Ts0 f4 152 674 144 44 -54 -16 #rect
Ts0 f4 @|StepIcon #fIcon
Ts0 f69 expr out #txt
Ts0 f69 109 896 339 896 #arcP
Ts0 f69 0 0.61063400144005 0 0 #arcLabel
Ts0 f96 guid 15F940442EBC4F26 #txt
Ts0 f96 method autoCompleteForRoleDelegate(String) #txt
Ts0 f96 inParameterDecl '<String query> param;' #txt
Ts0 f96 inParameterMapAction 'out.queryAutoComplete=param.query;
' #txt
Ts0 f96 outParameterDecl '<java.util.List<ch.ivyteam.ivy.security.IRole> rolesToDelegate> result;' #txt
Ts0 f96 outActionCode 'import ch.ivy.addon.portalkit.util.RoleUtils;

result.rolesToDelegate = RoleUtils.filterRoles(in.rolesToDelegate, in.queryAutoComplete);' #txt
Ts0 f96 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>autoCompleteForRoleDelegate(String)</name>
        <nameStyle>35,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ts0 f96 83 883 26 26 -56 13 #rect
Ts0 f96 @|UdMethodIcon #fIcon
Ts0 f65 expr out #txt
Ts0 f65 280 216 480 290 #arcP
Ts0 f65 1 480 216 #addKink
Ts0 f65 0 0.7241149078079877 0 0 #arcLabel
Ts0 f64 expr out #txt
Ts0 f64 109 216 168 216 #arcP
Ts0 f35 actionTable 'out=in;
' #txt
Ts0 f35 actionCode 'import ch.ivy.addon.portalkit.util.TaskUtils;

TaskUtils.resetTask(in.task);' #txt
Ts0 f35 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Reset task</name>
    </language>
</elementInfo>
' #txt
Ts0 f35 168 194 112 44 -29 -8 #rect
Ts0 f35 @|StepIcon #fIcon
Ts0 f60 expr out #txt
Ts0 f60 288 312 338 312 #arcP
Ts0 f58 expr out #txt
Ts0 f58 109 312 160 312 #arcP
Ts0 f45 actionTable 'out=in;
' #txt
Ts0 f45 actionCode 'import ch.ivy.addon.portalkit.jsf.ManagedBeans;
import ch.ivy.addon.portalkit.bean.TaskActionBean;

TaskActionBean taskActionBean = ManagedBeans.get("taskActionBean") as TaskActionBean;
out.canUserResumeTask = taskActionBean.canResume(in.task);' #txt
Ts0 f45 security system #txt
Ts0 f45 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Check if can resume</name>
    </language>
</elementInfo>
' #txt
Ts0 f45 160 290 128 44 -56 -8 #rect
Ts0 f45 @|StepIcon #fIcon
Ts0 f0 guid 16816B3D5FE7A2CB #txt
Ts0 f0 method start(String) #txt
Ts0 f0 inParameterDecl '<String currentPortalPage> param;' #txt
Ts0 f0 inParameterMapAction 'out.currentPortalPage=param.currentPortalPage;
' #txt
Ts0 f0 outParameterDecl '<> result;' #txt
Ts0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start(String)</name>
    </language>
</elementInfo>
' #txt
Ts0 f0 83 83 26 26 -16 15 #rect
Ts0 f0 @|UdInitIcon #fIcon
Ts0 f6 guid 16D28868336F21A0 #txt
Ts0 f6 method openDetails(ch.ivyteam.ivy.workflow.ITask,ch.ivy.addon.portalkit.datamodel.TaskLazyDataModel) #txt
Ts0 f6 inParameterDecl '<ch.ivyteam.ivy.workflow.ITask task,ch.ivy.addon.portalkit.datamodel.TaskLazyDataModel dataModel> param;' #txt
Ts0 f6 inParameterMapAction 'out.dataModel=param.dataModel;
out.task=param.task;
' #txt
Ts0 f6 outParameterDecl '<> result;' #txt
Ts0 f6 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>openDetails(ITask,TaskLazyDataModel)</name>
    </language>
</elementInfo>
' #txt
Ts0 f6 83 787 26 26 -70 15 #rect
Ts0 f6 @|UdMethodIcon #fIcon
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
Ts0 f7 264 778 144 44 -64 -8 #rect
Ts0 f7 @|CallSubIcon #fIcon
Ts0 f8 expr out #txt
Ts0 f8 109 800 264 800 #arcP
Ts0 f9 872 312 915 312 #arcP
Ts0 f2 109 96 339 96 #arcP
>Proto Ts0 .type ch.ivy.addon.portalkit.component.TaskItem.TaskItemData #txt
>Proto Ts0 .processKind HTML_DIALOG #txt
>Proto Ts0 -8 -8 16 16 16 26 #rect
>Proto Ts0 '' #fIcon
Ts0 f77 out f57 tail #connect
Ts0 f57 head f56 mainIn #connect
Ts0 f96 mainOut f69 tail #connect
Ts0 f69 head f94 mainIn #connect
Ts0 f76 mainOut f95 tail #connect
Ts0 f95 head f70 mainIn #connect
Ts0 f108 mainOut f110 tail #connect
Ts0 f110 head f109 mainIn #connect
Ts0 f4 mainOut f5 tail #connect
Ts0 f5 head f3 mainIn #connect
Ts0 f36 mainOut f21 tail #connect
Ts0 f21 head f4 mainIn #connect
Ts0 f30 mainOut f64 tail #connect
Ts0 f64 head f35 mainIn #connect
Ts0 f33 mainOut f58 tail #connect
Ts0 f58 head f45 mainIn #connect
Ts0 f45 mainOut f60 tail #connect
Ts0 f60 head f77 in #connect
Ts0 f35 mainOut f65 tail #connect
Ts0 f65 head f56 mainIn #connect
Ts0 f66 mainOut f71 tail #connect
Ts0 f71 head f68 mainIn #connect
Ts0 f56 mainOut f55 tail #connect
Ts0 f55 head f54 mainIn #connect
Ts0 f54 mainOut f73 tail #connect
Ts0 f73 head f49 mainIn #connect
Ts0 f97 mainOut f81 tail #connect
Ts0 f81 head f80 mainIn #connect
Ts0 f80 mainOut f87 tail #connect
Ts0 f87 head f79 in #connect
Ts0 f79 out f92 tail #connect
Ts0 f92 head f98 mainIn #connect
Ts0 f77 out f100 tail #connect
Ts0 f100 head f99 in #connect
Ts0 f99 out f67 tail #connect
Ts0 f67 head f66 mainIn #connect
Ts0 f79 out f101 tail #connect
Ts0 f101 head f99 in #connect
Ts0 f6 mainOut f8 tail #connect
Ts0 f8 head f7 mainIn #connect
Ts0 f49 mainOut f9 tail #connect
Ts0 f9 head f46 mainIn #connect
Ts0 f0 mainOut f2 tail #connect
Ts0 f2 head f1 mainIn #connect
