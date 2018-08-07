[Ivy]
[>Created: Mon Dec 28 11:43:04 ICT 2015]
14BCA4F51FE3BD84 3.18 #module
>Proto >Proto Collection #zClass
Ts0 UniversalTaskListProcess Big #zClass
Ts0 RD #cInfo
Ts0 #process
Ts0 @TextInP .ui2RdDataAction .ui2RdDataAction #zField
Ts0 @TextInP .rdData2UIAction .rdData2UIAction #zField
Ts0 @TextInP .resExport .resExport #zField
Ts0 @TextInP .type .type #zField
Ts0 @TextInP .processKind .processKind #zField
Ts0 @AnnotationInP-0n ai ai #zField
Ts0 @TextInP .xml .xml #zField
Ts0 @TextInP .responsibility .responsibility #zField
Ts0 @RichDialogInitStart f0 '' #zField
Ts0 @CallSub f3 '' #zField
Ts0 @RichDialogProcessEnd f2 '' #zField
Ts0 @GridStep f42 '' #zField
Ts0 @GridStep f5 '' #zField
Ts0 @PushWFArc f4 '' #zField
Ts0 @PushWFArc f1 '' #zField
Ts0 @PushWFArc f47 '' #zField
Ts0 @RichDialogMethodStart f6 '' #zField
Ts0 @RichDialogProcessEnd f7 '' #zField
Ts0 @GridStep f8 '' #zField
Ts0 @CallSub f11 '' #zField
Ts0 @PushWFArc f15 '' #zField
Ts0 @GridStep f12 '' #zField
Ts0 @Alternative f14 '' #zField
Ts0 @PushWFArc f16 '' #zField
Ts0 @PushWFArc f17 '' #zField
Ts0 @Alternative f9 '' #zField
Ts0 @PushWFArc f18 '' #zField
Ts0 @PushWFArc f10 '' #zField
Ts0 @PushWFArc f13 '' #zField
Ts0 @PushWFArc f19 '' #zField
Ts0 @GridStep f20 '' #zField
Ts0 @PushWFArc f21 '' #zField
Ts0 @PushWFArc f22 '' #zField
>Proto Ts0 Ts0 UniversalTaskListProcess #zField
Ts0 f0 guid 146F6322FE1697F4 #txt
Ts0 f0 type ch.ivy.addon.portalkit.multiapp.tasks.UniversalTaskList.UniversalTaskListData #txt
Ts0 f0 method start() #txt
Ts0 f0 disableUIEvents true #txt
Ts0 f0 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<> param = methodEvent.getInputArguments();
' #txt
Ts0 f0 inActionCode 'out.user = ivy.session.getSessionUserName();' #txt
Ts0 f0 outParameterDecl '<> result;
' #txt
Ts0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start()</name>
        <nameStyle>7,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ts0 f0 30 54 20 20 13 0 #rect
Ts0 f0 @|RichDialogInitStartIcon #fIcon
Ts0 f3 type ch.ivy.addon.portalkit.multiapp.tasks.UniversalTaskList.UniversalTaskListData #txt
Ts0 f3 processCall MultiPortal/CasesTasksFilter:findActiveTasks(String,java.util.Date,java.util.Date,List<String>) #txt
Ts0 f3 doCall true #txt
Ts0 f3 requestActionDecl '<java.lang.String user,java.util.Date createdFrom,java.util.Date createdTo,List<java.lang.String> priorities> param;
' #txt
Ts0 f3 requestMappingAction 'param.user=in.user;
param.createdFrom=in.createdFrom;
param.createdTo=in.createdTo;
param.priorities=in.selectedWorkflowPriorities;
' #txt
Ts0 f3 responseActionDecl 'ch.ivy.addon.portalkit.multiapp.tasks.UniversalTaskList.UniversalTaskListData out;
' #txt
Ts0 f3 responseMappingAction 'out=in;
out.errors=result.errors;
out.tasks=result.tasks;
' #txt
Ts0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>findActiveTasks</name>
        <nameStyle>15,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ts0 f3 126 52 36 24 -27 16 #rect
Ts0 f3 @|CallSubIcon #fIcon
Ts0 f2 type ch.ivy.addon.portalkit.multiapp.tasks.UniversalTaskList.UniversalTaskListData #txt
Ts0 f2 654 54 20 20 13 0 #rect
Ts0 f2 @|RichDialogProcessEndIcon #fIcon
Ts0 f42 actionDecl 'ch.ivy.addon.portalkit.multiapp.tasks.UniversalTaskList.UniversalTaskListData out;
' #txt
Ts0 f42 actionTable 'out=in;
' #txt
Ts0 f42 actionCode 'import ch.ivy.addon.portalkit.persistence.domain.Application;

import ch.ivy.addon.portalkit.bo.Contact;
import ch.ivy.addon.portalkit.util.TaskUtils;
import ch.ivy.addon.portalkit.util.UserUtils;

for(Application application : in.applications){
	in.apps.add(application.name);
}

in.taskTree = TaskUtils.generateAppTree(in.tasks);
in.contact = new Contact(ivy.session.getSessionUser().getEMailAddress());
in.contact.phone = ivy.session.getSessionUser().getProperty(UserUtils.PHONE);
in.contact.mobilePhone = ivy.session.getSessionUser().getProperty(UserUtils.MOBILE);' #txt
Ts0 f42 type ch.ivy.addon.portalkit.multiapp.tasks.UniversalTaskList.UniversalTaskListData #txt
Ts0 f42 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>set apps, task tree</name>
        <nameStyle>19,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ts0 f42 534 52 36 24 -36 20 #rect
Ts0 f42 @|StepIcon #fIcon
Ts0 f5 actionDecl 'ch.ivy.addon.portalkit.multiapp.tasks.UniversalTaskList.UniversalTaskListData out;
' #txt
Ts0 f5 actionTable 'out=in;
' #txt
Ts0 f5 actionCode 'import java.util.Calendar;
import javax.faces.context.FacesContext;
import javax.faces.application.FacesMessage;
import ch.ivy.ws.addon.WsException;

//Create error message
if(in.errors.size()>0){
	in.errorLink = ivy.cms.co("/ch.ivy.addon.portalkit.ui.jsf/common/found") + in.errors.size() + ivy.cms.co("/ch.ivy.addon.portalkit.ui.jsf/common/clickToShowDetail");
	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, ivy.cms.co("/ch.ivy.addon.portalkit.ui.jsf/common/errorNotification"), "<span style=\"cursor:pointer\"><a onclick=\"PF(''errorDialogUniversalTask'').show(); hideGrowl()\">"+in.errorLink+"</a></span>"));
}

//init workflow priorities
in.selectedWorkflowPriorities.add("HIGH").add("NORMAL").add("LOW");

//init time filter
Calendar c = Calendar.getInstance();
in.createdTo = null;// = c.getTime();
in.createdFrom = null;// = c.getTime();

' #txt
Ts0 f5 type ch.ivy.addon.portalkit.multiapp.tasks.UniversalTaskList.UniversalTaskListData #txt
Ts0 f5 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>init</name>
        <nameStyle>4,7
</nameStyle>
        <desc>init:
filters</desc>
    </language>
</elementInfo>
' #txt
Ts0 f5 270 52 36 24 -11 17 #rect
Ts0 f5 @|StepIcon #fIcon
Ts0 f4 expr out #txt
Ts0 f4 570 64 654 64 #arcP
Ts0 f1 expr out #txt
Ts0 f1 50 64 126 64 #arcP
Ts0 f47 expr out #txt
Ts0 f47 162 64 270 64 #arcP
Ts0 f6 guid 14F9677C62CB1FDC #txt
Ts0 f6 type ch.ivy.addon.portalkit.multiapp.tasks.UniversalTaskList.UniversalTaskListData #txt
Ts0 f6 method openTask(ch.ivy.addon.portalkit.bo.RemoteTask) #txt
Ts0 f6 disableUIEvents false #txt
Ts0 f6 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<ch.ivy.addon.portalkit.bo.RemoteTask remoteTask> param = methodEvent.getInputArguments();
' #txt
Ts0 f6 inParameterMapAction 'out.remoteTask=param.remoteTask;
' #txt
Ts0 f6 outParameterDecl '<> result;
' #txt
Ts0 f6 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>openTask(RemoteTask)</name>
        <nameStyle>20,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ts0 f6 30 150 20 20 9 5 #rect
Ts0 f6 @|RichDialogMethodStartIcon #fIcon
Ts0 f7 type ch.ivy.addon.portalkit.multiapp.tasks.UniversalTaskList.UniversalTaskListData #txt
Ts0 f7 726 150 20 20 13 0 #rect
Ts0 f7 @|RichDialogProcessEndIcon #fIcon
Ts0 f8 actionDecl 'ch.ivy.addon.portalkit.multiapp.tasks.UniversalTaskList.UniversalTaskListData out;
' #txt
Ts0 f8 actionTable 'out=in;
' #txt
Ts0 f8 actionCode 'import javax.faces.context.FacesContext;

FacesContext.getCurrentInstance().getExternalContext().redirect(in.remoteTask.getFullRequestPath());' #txt
Ts0 f8 type ch.ivy.addon.portalkit.multiapp.tasks.UniversalTaskList.UniversalTaskListData #txt
Ts0 f8 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start task</name>
        <nameStyle>10,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ts0 f8 558 148 36 24 -22 15 #rect
Ts0 f8 @|StepIcon #fIcon
Ts0 f11 type ch.ivy.addon.portalkit.multiapp.tasks.UniversalTaskList.UniversalTaskListData #txt
Ts0 f11 processCall MultiPortal/TaskService:resetTask(ch.ivy.addon.portalkit.persistence.domain.Server,Long) #txt
Ts0 f11 doCall true #txt
Ts0 f11 requestActionDecl '<ch.ivy.addon.portalkit.persistence.domain.Server ivyServer,java.lang.Long taskId> param;
' #txt
Ts0 f11 requestMappingAction 'param.ivyServer=in.remoteTask.applicationRegister.server;
param.taskId=in.remoteTask.getId();
' #txt
Ts0 f11 responseActionDecl 'ch.ivy.addon.portalkit.multiapp.tasks.UniversalTaskList.UniversalTaskListData out;
' #txt
Ts0 f11 responseMappingAction 'out=in;
out.errors=result.errors;
' #txt
Ts0 f11 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>resetTask(Server,Long)</name>
        <nameStyle>22,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ts0 f11 270 148 36 24 -64 14 #rect
Ts0 f11 @|CallSubIcon #fIcon
Ts0 f15 expr out #txt
Ts0 f15 50 160 270 160 #arcP
Ts0 f12 actionDecl 'ch.ivy.addon.portalkit.multiapp.tasks.UniversalTaskList.UniversalTaskListData out;
' #txt
Ts0 f12 actionTable 'out=in;
' #txt
Ts0 f12 actionCode 'import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

in.errorLink = ivy.cms.co("/ch.ivy.addon.portalkit.ui.jsf/common/found") + in.errors.size() + ivy.cms.co("/ch.ivy.addon.portalkit.ui.jsf/common/clickToShowDetail");
FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, ivy.cms.co("/ch.ivy.addon.portalkit.ui.jsf/common/errorNotification"), "<span style=\"cursor:pointer\"><a onclick=\"PF(''errorDialogUniversalTask'').show(); hideGrowl()\">"+in.errorLink+"</a></span>"));' #txt
Ts0 f12 type ch.ivy.addon.portalkit.multiapp.tasks.UniversalTaskList.UniversalTaskListData #txt
Ts0 f12 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>handle errors</name>
        <nameStyle>13,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ts0 f12 558 212 36 24 -37 14 #rect
Ts0 f12 @|StepIcon #fIcon
Ts0 f14 type ch.ivy.addon.portalkit.multiapp.tasks.UniversalTaskList.UniversalTaskListData #txt
Ts0 f14 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>no errors?</name>
        <nameStyle>10,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ts0 f14 434 146 28 28 14 0 #rect
Ts0 f14 @|AlternativeIcon #fIcon
Ts0 f16 expr out #txt
Ts0 f16 306 160 434 160 #arcP
Ts0 f17 expr in #txt
Ts0 f17 outCond in.errors.isEmpty() #txt
Ts0 f17 462 160 558 160 #arcP
Ts0 f9 type ch.ivy.addon.portalkit.multiapp.tasks.UniversalTaskList.UniversalTaskListData #txt
Ts0 f9 658 146 28 28 14 0 #rect
Ts0 f9 @|AlternativeIcon #fIcon
Ts0 f18 expr out #txt
Ts0 f18 594 160 658 160 #arcP
Ts0 f10 expr out #txt
Ts0 f10 594 224 672 174 #arcP
Ts0 f10 1 672 224 #addKink
Ts0 f10 0 0.7408944020176953 0 0 #arcLabel
Ts0 f13 expr in #txt
Ts0 f13 686 160 726 160 #arcP
Ts0 f19 expr in #txt
Ts0 f19 448 174 558 224 #arcP
Ts0 f19 1 448 224 #addKink
Ts0 f19 1 0.2296194231272273 0 0 #arcLabel
Ts0 f20 actionDecl 'ch.ivy.addon.portalkit.multiapp.tasks.UniversalTaskList.UniversalTaskListData out;
' #txt
Ts0 f20 actionTable 'out=in;
' #txt
Ts0 f20 actionCode 'import ch.ivy.addon.portalkit.service.ApplicationService;
ApplicationService applicationService = new ApplicationService();
in.applications = applicationService.findAll();' #txt
Ts0 f20 type ch.ivy.addon.portalkit.multiapp.tasks.UniversalTaskList.UniversalTaskListData #txt
Ts0 f20 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>get all applications</name>
        <nameStyle>20,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ts0 f20 390 52 36 24 -31 18 #rect
Ts0 f20 @|StepIcon #fIcon
Ts0 f21 expr out #txt
Ts0 f21 306 64 390 64 #arcP
Ts0 f22 expr out #txt
Ts0 f22 426 64 534 64 #arcP
>Proto Ts0 .type ch.ivy.addon.portalkit.multiapp.tasks.UniversalTaskList.UniversalTaskListData #txt
>Proto Ts0 .processKind HTML_DIALOG #txt
>Proto Ts0 -8 -8 16 16 16 26 #rect
>Proto Ts0 '' #fIcon
Ts0 f42 mainOut f4 tail #connect
Ts0 f4 head f2 mainIn #connect
Ts0 f0 mainOut f1 tail #connect
Ts0 f1 head f3 mainIn #connect
Ts0 f3 mainOut f47 tail #connect
Ts0 f47 head f5 mainIn #connect
Ts0 f6 mainOut f15 tail #connect
Ts0 f15 head f11 mainIn #connect
Ts0 f11 mainOut f16 tail #connect
Ts0 f16 head f14 in #connect
Ts0 f14 out f17 tail #connect
Ts0 f17 head f8 mainIn #connect
Ts0 f8 mainOut f18 tail #connect
Ts0 f18 head f9 in #connect
Ts0 f12 mainOut f10 tail #connect
Ts0 f10 head f9 in #connect
Ts0 f9 out f13 tail #connect
Ts0 f13 head f7 mainIn #connect
Ts0 f14 out f19 tail #connect
Ts0 f19 head f12 mainIn #connect
Ts0 f5 mainOut f21 tail #connect
Ts0 f21 head f20 mainIn #connect
Ts0 f20 mainOut f22 tail #connect
Ts0 f22 head f42 mainIn #connect
