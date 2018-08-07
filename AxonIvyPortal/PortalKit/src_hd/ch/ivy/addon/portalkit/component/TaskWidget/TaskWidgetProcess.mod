[Ivy]
[>Created: Fri Aug 04 10:34:51 ICT 2017]
15035F535EFB1618 3.20 #module
>Proto >Proto Collection #zClass
Ts0 TaskWidgetProcess Big #zClass
Ts0 RD #cInfo
Ts0 #process
Ts0 @TextInP .ui2RdDataAction .ui2RdDataAction #zField
Ts0 @TextInP .rdData2UIAction .rdData2UIAction #zField
Ts0 @TextInP .resExport .resExport #zField
Ts0 @TextInP .type .type #zField
Ts0 @TextInP .processKind .processKind #zField
Ts0 @AnnotationInP-0n ai ai #zField
Ts0 @MessageFlowInP-0n messageIn messageIn #zField
Ts0 @MessageFlowOutP-0n messageOut messageOut #zField
Ts0 @TextInP .xml .xml #zField
Ts0 @TextInP .responsibility .responsibility #zField
Ts0 @RichDialogInitStart f0 '' #zField
Ts0 @RichDialogProcessEnd f1 '' #zField
Ts0 @RichDialogMethodStart f37 '' #zField
Ts0 @RichDialogProcessEnd f38 '' #zField
Ts0 @GridStep f60 '' #zField
Ts0 @PushWFArc f61 '' #zField
Ts0 @PushWFArc f44 '' #zField
Ts0 @RichDialogProcessStart f82 '' #zField
Ts0 @RichDialogProcessEnd f83 '' #zField
Ts0 @GridStep f91 '' #zField
Ts0 @PushWFArc f90 '' #zField
Ts0 @GridStep f93 '' #zField
Ts0 @PushWFArc f89 '' #zField
Ts0 @RichDialogMethodStart f95 '' #zField
Ts0 @RichDialogMethodStart f96 '' #zField
Ts0 @RichDialogMethodStart f94 '' #zField
Ts0 @CallSub f98 '' #zField
Ts0 @RichDialogProcessEnd f100 '' #zField
Ts0 @PushWFArc f101 '' #zField
Ts0 @PushWFArc f88 '' #zField
Ts0 @PushWFArc f107 '' #zField
Ts0 @RichDialogProcessEnd f67 '' #zField
Ts0 @RichDialogMethodStart f68 '' #zField
Ts0 @CallSub f69 '' #zField
Ts0 @PushWFArc f9 '' #zField
Ts0 @PushWFArc f2 '' #zField
Ts0 @PushWFArc f7 '' #zField
Ts0 @RichDialogMethodStart f8 '' #zField
Ts0 @GridStep f10 '' #zField
Ts0 @PushWFArc f11 '' #zField
Ts0 @PushWFArc f12 '' #zField
Ts0 @PushWFArc f3 '' #zField
Ts0 @GridStep f4 '' #zField
Ts0 @PushWFArc f5 '' #zField
Ts0 @PushWFArc f6 '' #zField
>Proto Ts0 Ts0 TaskWidgetProcess #zField
Ts0 f0 guid 14FDF92006C61D35 #txt
Ts0 f0 type ch.ivy.addon.portalkit.component.TaskWidget.TaskWidgetData #txt
Ts0 f0 method start(String,Boolean,ch.ivy.addon.portalkit.datamodel.TaskLazyDataModel) #txt
Ts0 f0 disableUIEvents true #txt
Ts0 f0 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<java.lang.String keyword,java.lang.Boolean compactMode,ch.ivy.addon.portalkit.datamodel.TaskLazyDataModel dataModel> param = methodEvent.getInputArguments();
' #txt
Ts0 f0 inParameterMapAction 'out.keyword=param.#keyword;
' #txt
Ts0 f0 inActionCode 'import ch.ivy.addon.portalkit.bean.TaskWidgetBean;
import javax.faces.context.FacesContext;

if (!param.#dataModel is initialized) {
	FacesContext context = FacesContext.getCurrentInstance();
	TaskWidgetBean taskWidgetBean = context.getApplication().evaluateExpressionGet(context, "#{taskWidgetBean}", TaskWidgetBean.class) as TaskWidgetBean;
	out.dataModel = taskWidgetBean.dataModel;
} else {
	out.dataModel = param.dataModel;
}' #txt
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
Ts0 f0 61 53 22 22 14 0 #rect
Ts0 f0 @|RichDialogInitStartIcon #fIcon
Ts0 f1 type ch.ivy.addon.portalkit.component.TaskWidget.TaskWidgetData #txt
Ts0 f1 61 181 22 22 14 0 #rect
Ts0 f1 @|RichDialogProcessEndIcon #fIcon
Ts0 f37 guid 152ED6485874C117 #txt
Ts0 f37 type ch.ivy.addon.portalkit.component.TaskWidget.TaskWidgetData #txt
Ts0 f37 method filter() #txt
Ts0 f37 disableUIEvents false #txt
Ts0 f37 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<> param = methodEvent.getInputArguments();
' #txt
Ts0 f37 outParameterDecl '<> result;
' #txt
Ts0 f37 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>filter()</name>
        <nameStyle>8,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ts0 f37 365 53 22 22 14 0 #rect
Ts0 f37 @|RichDialogMethodStartIcon #fIcon
Ts0 f38 type ch.ivy.addon.portalkit.component.TaskWidget.TaskWidgetData #txt
Ts0 f38 365 245 22 22 14 0 #rect
Ts0 f38 @|RichDialogProcessEndIcon #fIcon
Ts0 f60 actionDecl 'ch.ivy.addon.portalkit.component.TaskWidget.TaskWidgetData out;
' #txt
Ts0 f60 actionTable 'out=in;
' #txt
Ts0 f60 actionCode in.dataModel.setKeyword(in.keyword); #txt
Ts0 f60 type ch.ivy.addon.portalkit.component.TaskWidget.TaskWidgetData #txt
Ts0 f60 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>filter tasks 
based on keyword</name>
        <nameStyle>30,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ts0 f60 358 148 36 24 20 -2 #rect
Ts0 f60 @|StepIcon #fIcon
Ts0 f61 expr out #txt
Ts0 f61 376 75 376 148 #arcP
Ts0 f44 expr out #txt
Ts0 f44 376 172 376 245 #arcP
Ts0 f82 guid 153E0DE6762B6C64 #txt
Ts0 f82 type ch.ivy.addon.portalkit.component.TaskWidget.TaskWidgetData #txt
Ts0 f82 actionDecl 'ch.ivy.addon.portalkit.component.TaskWidget.TaskWidgetData out;
' #txt
Ts0 f82 actionTable 'out=in;
' #txt
Ts0 f82 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>switchMode</name>
        <nameStyle>10,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ts0 f82 61 341 22 22 14 0 #rect
Ts0 f82 @|RichDialogProcessStartIcon #fIcon
Ts0 f83 type ch.ivy.addon.portalkit.component.TaskWidget.TaskWidgetData #txt
Ts0 f83 61 437 22 22 14 0 #rect
Ts0 f83 @|RichDialogProcessEndIcon #fIcon
Ts0 f91 actionDecl 'ch.ivy.addon.portalkit.component.TaskWidget.TaskWidgetData out;
' #txt
Ts0 f91 actionTable 'out=in;
' #txt
Ts0 f91 actionCode 'import ch.ivy.addon.portalkit.enums.TaskSortField;

in.dataModel.setSortField(TaskSortField.PRIORITY.toString(), false);
' #txt
Ts0 f91 type ch.ivy.addon.portalkit.component.TaskWidget.TaskWidgetData #txt
Ts0 f91 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>sort tasks
filtered tasks</name>
        <nameStyle>25,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ts0 f91 446 396 36 24 20 -7 #rect
Ts0 f91 @|StepIcon #fIcon
Ts0 f90 expr out #txt
Ts0 f90 464 420 83 448 #arcP
Ts0 f90 1 464 448 #addKink
Ts0 f90 1 0.38811073544256947 0 0 #arcLabel
Ts0 f93 actionDecl 'ch.ivy.addon.portalkit.component.TaskWidget.TaskWidgetData out;
' #txt
Ts0 f93 actionTable 'out=in;
' #txt
Ts0 f93 actionCode 'import ch.ivy.addon.portalkit.enums.TaskSortField;

in.dataModel.setSortField(TaskSortField.EXPIRY_TIME.toString(), false);
' #txt
Ts0 f93 type ch.ivy.addon.portalkit.component.TaskWidget.TaskWidgetData #txt
Ts0 f93 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>sort tasks
filtered tasks</name>
        <nameStyle>25,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ts0 f93 302 396 36 24 20 -7 #rect
Ts0 f93 @|StepIcon #fIcon
Ts0 f89 expr out #txt
Ts0 f89 320 420 83 448 #arcP
Ts0 f89 1 320 448 #addKink
Ts0 f89 1 0.33600930498506193 0 0 #arcLabel
Ts0 f95 guid 153E111F9FCD9D31 #txt
Ts0 f95 type ch.ivy.addon.portalkit.component.TaskWidget.TaskWidgetData #txt
Ts0 f95 method sortByPriority() #txt
Ts0 f95 disableUIEvents false #txt
Ts0 f95 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<> param = methodEvent.getInputArguments();
' #txt
Ts0 f95 outParameterDecl '<> result;
' #txt
Ts0 f95 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>sortByPriority()</name>
    </language>
</elementInfo>
' #txt
Ts0 f95 453 341 22 22 14 0 #rect
Ts0 f95 @|RichDialogMethodStartIcon #fIcon
Ts0 f96 guid 153E112133CF9A21 #txt
Ts0 f96 type ch.ivy.addon.portalkit.component.TaskWidget.TaskWidgetData #txt
Ts0 f96 method sortByExpiry() #txt
Ts0 f96 disableUIEvents false #txt
Ts0 f96 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<> param = methodEvent.getInputArguments();
' #txt
Ts0 f96 outParameterDecl '<> result;
' #txt
Ts0 f96 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>sortByExpiry()</name>
    </language>
</elementInfo>
' #txt
Ts0 f96 309 341 22 22 14 0 #rect
Ts0 f96 @|RichDialogMethodStartIcon #fIcon
Ts0 f94 guid 153EF14248ECB35E #txt
Ts0 f94 type ch.ivy.addon.portalkit.component.TaskWidget.TaskWidgetData #txt
Ts0 f94 method findTasks(Integer,Integer,ch.ivy.ws.addon.TaskSearchCriteria,java.lang.Long) #txt
Ts0 f94 disableUIEvents false #txt
Ts0 f94 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<java.lang.Integer startIndex,java.lang.Integer count,ch.ivy.ws.addon.TaskSearchCriteria criteria,java.lang.Long serverId> param = methodEvent.getInputArguments();
' #txt
Ts0 f94 inParameterMapAction 'out.count=param.count;
out.serverId=param.#serverId is initialized ? param.serverId : null;
out.startIndex=param.startIndex;
out.taskSearchCriteria=param.criteria;
out.taskSearchCriteria.involvedApplications=param.criteria.involvedApplications;
' #txt
Ts0 f94 outParameterDecl '<java.util.List<ch.ivy.addon.portalkit.bo.RemoteTask> tasks> result;
' #txt
Ts0 f94 outParameterMapAction 'result.tasks=in.tasks;
' #txt
Ts0 f94 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>findTasks(Integer,
Integer,TaskSearchCriteria,Long)</name>
        <nameStyle>51,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ts0 f94 541 53 22 22 -44 -46 #rect
Ts0 f94 @|RichDialogMethodStartIcon #fIcon
Ts0 f98 type ch.ivy.addon.portalkit.component.TaskWidget.TaskWidgetData #txt
Ts0 f98 processCall MultiPortal/TaskService:findTasksByCriteria(Long,ch.ivy.ws.addon.TaskSearchCriteria,Integer,Integer) #txt
Ts0 f98 doCall true #txt
Ts0 f98 requestActionDecl '<java.lang.Long serverId,ch.ivy.ws.addon.TaskSearchCriteria taskSearchCriteria,java.lang.Integer startIndex,java.lang.Integer count> param;
' #txt
Ts0 f98 requestMappingAction 'param.serverId=in.#serverId;
param.taskSearchCriteria=in.taskSearchCriteria;
param.startIndex=in.startIndex;
param.count=in.count;
' #txt
Ts0 f98 responseActionDecl 'ch.ivy.addon.portalkit.component.TaskWidget.TaskWidgetData out;
' #txt
Ts0 f98 responseMappingAction 'out=in;
out.errors=result.errors;
out.tasks=in.taskSearchCriteria.ignoreInvolvedUser ? result.allTasks : result.tasks;
' #txt
Ts0 f98 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>TaskService</name>
        <nameStyle>11,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ts0 f98 534 148 36 24 20 -2 #rect
Ts0 f98 @|CallSubIcon #fIcon
Ts0 f100 type ch.ivy.addon.portalkit.component.TaskWidget.TaskWidgetData #txt
Ts0 f100 541 245 22 22 14 0 #rect
Ts0 f100 @|RichDialogProcessEndIcon #fIcon
Ts0 f101 expr out #txt
Ts0 f101 552 172 552 245 #arcP
Ts0 f88 expr out #txt
Ts0 f88 464 363 464 396 #arcP
Ts0 f107 expr out #txt
Ts0 f107 320 363 320 396 #arcP
Ts0 f107 0 0.4908436886854152 0 0 #arcLabel
Ts0 f67 type ch.ivy.addon.portalkit.component.TaskWidget.TaskWidgetData #txt
Ts0 f67 741 245 22 22 14 0 #rect
Ts0 f67 @|RichDialogProcessEndIcon #fIcon
Ts0 f68 guid 15403E7DE16C8732 #txt
Ts0 f68 type ch.ivy.addon.portalkit.component.TaskWidget.TaskWidgetData #txt
Ts0 f68 method countTasks(ch.ivy.ws.addon.TaskSearchCriteria,java.lang.Long) #txt
Ts0 f68 disableUIEvents false #txt
Ts0 f68 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<ch.ivy.ws.addon.TaskSearchCriteria criteria,java.lang.Long serverId> param = methodEvent.getInputArguments();
' #txt
Ts0 f68 inParameterMapAction 'out.serverId=param.#serverId is initialized ? param.serverId : null;
out.taskSearchCriteria=param.criteria;
' #txt
Ts0 f68 outParameterDecl '<java.lang.Long taskCount> result;
' #txt
Ts0 f68 outParameterMapAction 'result.taskCount=in.taskCount;
' #txt
Ts0 f68 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>countTasks(TaskSearchCriteria,Long)</name>
        <nameStyle>35,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ts0 f68 741 53 22 22 -32 -35 #rect
Ts0 f68 @|RichDialogMethodStartIcon #fIcon
Ts0 f69 type ch.ivy.addon.portalkit.component.TaskWidget.TaskWidgetData #txt
Ts0 f69 processCall MultiPortal/TaskService:countTasksByCriteria(Long,ch.ivy.ws.addon.TaskSearchCriteria) #txt
Ts0 f69 doCall true #txt
Ts0 f69 requestActionDecl '<java.lang.Long serverId,ch.ivy.ws.addon.TaskSearchCriteria taskSearchCriteria> param;
' #txt
Ts0 f69 requestMappingAction 'param.serverId=in.#serverId;
param.taskSearchCriteria=in.taskSearchCriteria;
' #txt
Ts0 f69 responseActionDecl 'ch.ivy.addon.portalkit.component.TaskWidget.TaskWidgetData out;
' #txt
Ts0 f69 responseMappingAction 'out=in;
out.errors=result.errors;
out.taskCount=result.taskCount;
' #txt
Ts0 f69 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>TaskService</name>
        <nameStyle>11,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ts0 f69 734 148 36 24 20 -2 #rect
Ts0 f69 @|CallSubIcon #fIcon
Ts0 f9 expr out #txt
Ts0 f9 552 75 552 148 #arcP
Ts0 f2 expr out #txt
Ts0 f2 752 172 752 245 #arcP
Ts0 f7 expr out #txt
Ts0 f7 752 75 752 148 #arcP
Ts0 f8 guid 15B8AD1D7AD576DC #txt
Ts0 f8 type ch.ivy.addon.portalkit.component.TaskWidget.TaskWidgetData #txt
Ts0 f8 method storeConfiguration() #txt
Ts0 f8 disableUIEvents false #txt
Ts0 f8 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<> param = methodEvent.getInputArguments();
' #txt
Ts0 f8 outParameterDecl '<> result;
' #txt
Ts0 f8 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>storeConfiguration()</name>
    </language>
</elementInfo>
' #txt
Ts0 f8 629 341 22 22 14 0 #rect
Ts0 f8 @|RichDialogMethodStartIcon #fIcon
Ts0 f10 actionDecl 'ch.ivy.addon.portalkit.component.TaskWidget.TaskWidgetData out;
' #txt
Ts0 f10 actionTable 'out=in;
' #txt
Ts0 f10 actionCode 'import ch.ivy.addon.portalkit.enums.SessionAttribute;
import ch.ivy.addon.portalkit.util.SecurityServiceUtils;

SecurityServiceUtils.setSessionAttribute(SessionAttribute.TASK_DATA_MODEL.toString(), in.dataModel);' #txt
Ts0 f10 type ch.ivy.addon.portalkit.component.TaskWidget.TaskWidgetData #txt
Ts0 f10 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>store task
list''s configuration</name>
        <nameStyle>31
</nameStyle>
    </language>
</elementInfo>
' #txt
Ts0 f10 622 396 36 24 20 -2 #rect
Ts0 f10 @|StepIcon #fIcon
Ts0 f11 expr out #txt
Ts0 f11 640 363 640 396 #arcP
Ts0 f12 expr out #txt
Ts0 f12 640 420 83 448 #arcP
Ts0 f12 1 640 448 #addKink
Ts0 f12 1 0.4606959193892842 0 0 #arcLabel
Ts0 f3 expr out #txt
Ts0 f3 72 75 72 181 #arcP
Ts0 f4 actionDecl 'ch.ivy.addon.portalkit.component.TaskWidget.TaskWidgetData out;
' #txt
Ts0 f4 actionTable 'out=in;
' #txt
Ts0 f4 actionCode 'import ch.ivy.addon.portalkit.taskfilter.TaskFilter;
import ch.ivy.addon.portalkit.util.TaskUtils;

in.dataModel.compactMode = !in.dataModel.compactMode;
if (!in.dataModel.compactMode) {
	for (TaskFilter filter : in.dataModel.filterContainer.filters) {
		filter.resetValues();
	}
}

in.dataModel.setIgnoreInvolvedUser(TaskUtils.checkReadAllTasksPermission() && !in.dataModel.compactMode);
' #txt
Ts0 f4 type ch.ivy.addon.portalkit.component.TaskWidget.TaskWidgetData #txt
Ts0 f4 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>switch mode</name>
        <nameStyle>11,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ts0 f4 54 397 36 24 20 -7 #rect
Ts0 f4 @|StepIcon #fIcon
Ts0 f5 expr out #txt
Ts0 f5 72 363 72 397 #arcP
Ts0 f6 expr out #txt
Ts0 f6 72 421 72 437 #arcP
>Proto Ts0 .type ch.ivy.addon.portalkit.component.TaskWidget.TaskWidgetData #txt
>Proto Ts0 .processKind HTML_DIALOG #txt
>Proto Ts0 -8 -8 16 16 16 26 #rect
>Proto Ts0 '' #fIcon
Ts0 f37 mainOut f61 tail #connect
Ts0 f61 head f60 mainIn #connect
Ts0 f60 mainOut f44 tail #connect
Ts0 f44 head f38 mainIn #connect
Ts0 f91 mainOut f90 tail #connect
Ts0 f90 head f83 mainIn #connect
Ts0 f93 mainOut f89 tail #connect
Ts0 f89 head f83 mainIn #connect
Ts0 f98 mainOut f101 tail #connect
Ts0 f101 head f100 mainIn #connect
Ts0 f95 mainOut f88 tail #connect
Ts0 f88 head f91 mainIn #connect
Ts0 f96 mainOut f107 tail #connect
Ts0 f107 head f93 mainIn #connect
Ts0 f94 mainOut f9 tail #connect
Ts0 f9 head f98 mainIn #connect
Ts0 f69 mainOut f2 tail #connect
Ts0 f2 head f67 mainIn #connect
Ts0 f68 mainOut f7 tail #connect
Ts0 f7 head f69 mainIn #connect
Ts0 f8 mainOut f11 tail #connect
Ts0 f11 head f10 mainIn #connect
Ts0 f10 mainOut f12 tail #connect
Ts0 f12 head f83 mainIn #connect
Ts0 f0 mainOut f3 tail #connect
Ts0 f3 head f1 mainIn #connect
Ts0 f82 mainOut f5 tail #connect
Ts0 f5 head f4 mainIn #connect
Ts0 f4 mainOut f6 tail #connect
Ts0 f6 head f83 mainIn #connect
