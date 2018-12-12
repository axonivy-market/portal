[Ivy]
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
Ts0 @PushWFArc f88 '' #zField
Ts0 @PushWFArc f107 '' #zField
Ts0 @RichDialogProcessEnd f67 '' #zField
Ts0 @RichDialogMethodStart f68 '' #zField
Ts0 @CallSub f69 '' #zField
Ts0 @PushWFArc f9 '' #zField
Ts0 @PushWFArc f2 '' #zField
Ts0 @PushWFArc f7 '' #zField
Ts0 @GridStep f4 '' #zField
Ts0 @PushWFArc f5 '' #zField
Ts0 @PushWFArc f6 '' #zField
Ts0 @RichDialogProcessStart f23 '' #zField
Ts0 @GridStep f24 '' #zField
Ts0 @RichDialogProcessStart f28 '' #zField
Ts0 @RichDialogProcessEnd f29 '' #zField
Ts0 @GridStep f31 '' #zField
Ts0 @PushWFArc f32 '' #zField
Ts0 @PushWFArc f30 '' #zField
Ts0 @PushWFArc f26 '' #zField
Ts0 @GridStep f27 '' #zField
Ts0 @PushWFArc f1 '' #zField
Ts0 @RichDialogProcessEnd f3 '' #zField
Ts0 @RichDialogMethodStart f34 '' #zField
Ts0 @GridStep f35 '' #zField
Ts0 @RichDialogProcessEnd f36 '' #zField
Ts0 @PushWFArc f39 '' #zField
Ts0 @PushWFArc f40 '' #zField
Ts0 @RichDialogMethodStart f41 '' #zField
Ts0 @RichDialogProcessEnd f42 '' #zField
Ts0 @PushWFArc f43 '' #zField
Ts0 @GridStep f45 '' #zField
Ts0 @PushWFArc f46 '' #zField
Ts0 @Alternative f47 '' #zField
Ts0 @PushWFArc f48 '' #zField
Ts0 @PushWFArc f25 '' #zField
Ts0 @RichDialogProcessEnd f50 '' #zField
Ts0 @PushWFArc f51 '' #zField
Ts0 @RichDialogMethodStart f16 '' #zField
Ts0 @RichDialogProcessEnd f17 '' #zField
Ts0 @PushWFArc f20 '' #zField
Ts0 @GridStep f65 '' #zField
Ts0 @CallSub f70 '' #zField
Ts0 @GridStep f56 '' #zField
Ts0 @GridStep f59 '' #zField
Ts0 @RichDialogProcessEnd f22 '' #zField
Ts0 @Alternative f54 '' #zField
Ts0 @PushWFArc f49 '' #zField
Ts0 @PushWFArc f72 '' #zField
Ts0 @RichDialogMethodStart f13 '' #zField
Ts0 @PushWFArc f15 '' #zField
Ts0 @PushWFArc f21 '' #zField
Ts0 @PushWFArc f57 '' #zField
Ts0 @PushWFArc f18 '' #zField
Ts0 @PushWFArc f63 '' #zField
Ts0 @PushWFArc f58 '' #zField
Ts0 @PushWFArc f19 '' #zField
>Proto Ts0 Ts0 TaskWidgetProcess #zField
Ts0 f0 guid 14FDF92006C61D35 #txt
Ts0 f0 type ch.ivy.addon.portalkit.component.TaskWidget.TaskWidgetData #txt
Ts0 f0 method start(String,Boolean,ch.ivy.addon.portalkit.datamodel.TaskLazyDataModel,java.lang.Long) #txt
Ts0 f0 disableUIEvents true #txt
Ts0 f0 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<java.lang.String keyword,java.lang.Boolean compactMode,ch.ivy.addon.portalkit.datamodel.TaskLazyDataModel dataModel,java.lang.Long filterGroupId> param = methodEvent.getInputArguments();
' #txt
Ts0 f0 inParameterMapAction 'out.keyword=param.#keyword;
out.taskFilterGroupId=param.filterGroupId;
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
Ts0 f0 61 61 22 22 14 0 #rect
Ts0 f0 @|RichDialogInitStartIcon #fIcon
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
out.latestTaskDate=new java.util.Date();
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
Ts0 f94 541 61 22 22 -44 -46 #rect
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
out.latestTaskDate=new java.util.Date();
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
Ts0 f98 534 160 36 24 20 -2 #rect
Ts0 f98 @|CallSubIcon #fIcon
Ts0 f100 type ch.ivy.addon.portalkit.component.TaskWidget.TaskWidgetData #txt
Ts0 f100 541 237 22 22 14 0 #rect
Ts0 f100 @|RichDialogProcessEndIcon #fIcon
Ts0 f88 expr out #txt
Ts0 f88 464 363 464 396 #arcP
Ts0 f107 expr out #txt
Ts0 f107 320 363 320 396 #arcP
Ts0 f107 0 0.4908436886854152 0 0 #arcLabel
Ts0 f67 type ch.ivy.addon.portalkit.component.TaskWidget.TaskWidgetData #txt
Ts0 f67 741 237 22 22 14 0 #rect
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
Ts0 f68 741 61 22 22 -32 -35 #rect
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
Ts0 f69 734 160 36 24 20 -2 #rect
Ts0 f69 @|CallSubIcon #fIcon
Ts0 f9 expr out #txt
Ts0 f9 552 83 552 160 #arcP
Ts0 f2 expr out #txt
Ts0 f2 752 184 752 237 #arcP
Ts0 f7 expr out #txt
Ts0 f7 752 83 752 160 #arcP
Ts0 f4 actionDecl 'ch.ivy.addon.portalkit.component.TaskWidget.TaskWidgetData out;
' #txt
Ts0 f4 actionTable 'out=in;
' #txt
Ts0 f4 actionCode 'import ch.ivy.addon.portalkit.taskfilter.TaskFilter;
import ch.ivy.addon.portalkit.util.PermissionUtils;

in.dataModel.compactMode = !in.dataModel.compactMode;
if (!in.dataModel.compactMode && in.dataModel.#filterContainer is initialized) {
	for (TaskFilter filter : in.dataModel.filterContainer.filters) {
		filter.resetValues();
	}
}

in.dataModel.setIgnoreInvolvedUser(PermissionUtils.checkReadAllTasksPermission() && !in.dataModel.compactMode);
in.dataModel.initFilters();
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
Ts0 f23 guid 15F5844AD96F4A4C #txt
Ts0 f23 type ch.ivy.addon.portalkit.component.TaskWidget.TaskWidgetData #txt
Ts0 f23 actionDecl 'ch.ivy.addon.portalkit.component.TaskWidget.TaskWidgetData out;
' #txt
Ts0 f23 actionTable 'out=in;
' #txt
Ts0 f23 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>saveFilter</name>
        <nameStyle>10,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ts0 f23 67 539 26 26 -26 15 #rect
Ts0 f23 @|RichDialogProcessStartIcon #fIcon
Ts0 f24 actionDecl 'ch.ivy.addon.portalkit.component.TaskWidget.TaskWidgetData out;
' #txt
Ts0 f24 actionTable 'out=in;
' #txt
Ts0 f24 actionCode 'import ch.ivy.addon.portalkit.service.TaskFilterService;
import ch.ivy.addon.portalkit.taskfilter.TaskFilterData;
import ch.ivy.addon.portalkit.enums.FilterType;
TaskFilterService taskFilterService = new TaskFilterService();
TaskFilterData taskFilterData = in.dataModel.saveFilter(in.filterSetName, in.filterType, in.taskFilterGroupId);
if(FilterType.ONLY_ME == taskFilterData.type) {
	in.taskPrivateFilters.add(taskFilterData);
	in.taskPrivateFilters = taskFilterService.sortFilters(in.taskPrivateFilters) as List;
} else {
	in.taskPublicFilters.add(taskFilterData);
	in.taskPublicFilters = taskFilterService.sortFilters(in.taskPublicFilters) as List;
}
in.dataModel.selectedTaskFilterData = taskFilterData;' #txt
Ts0 f24 type ch.ivy.addon.portalkit.component.TaskWidget.TaskWidgetData #txt
Ts0 f24 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>save filter set</name>
        <nameStyle>15,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ts0 f24 408 530 112 44 -36 -8 #rect
Ts0 f24 @|StepIcon #fIcon
Ts0 f28 guid 15F5BFFC0DEF027B #txt
Ts0 f28 type ch.ivy.addon.portalkit.component.TaskWidget.TaskWidgetData #txt
Ts0 f28 actionDecl 'ch.ivy.addon.portalkit.component.TaskWidget.TaskWidgetData out;
' #txt
Ts0 f28 actionTable 'out=in;
' #txt
Ts0 f28 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>clearSaveFilterDialog</name>
        <nameStyle>21,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ts0 f28 67 635 26 26 -58 15 #rect
Ts0 f28 @|RichDialogProcessStartIcon #fIcon
Ts0 f29 type ch.ivy.addon.portalkit.component.TaskWidget.TaskWidgetData #txt
Ts0 f29 587 635 26 26 0 12 #rect
Ts0 f29 @|RichDialogProcessEndIcon #fIcon
Ts0 f31 actionDecl 'ch.ivy.addon.portalkit.component.TaskWidget.TaskWidgetData out;
' #txt
Ts0 f31 actionTable 'out=in;
' #txt
Ts0 f31 actionCode 'import ch.ivy.addon.portalkit.enums.FilterType;
in.filterType = FilterType.ONLY_ME;
in.filterSetName = "";' #txt
Ts0 f31 type ch.ivy.addon.portalkit.component.TaskWidget.TaskWidgetData #txt
Ts0 f31 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Clear filter</name>
        <nameStyle>12,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ts0 f31 408 626 112 44 -28 -8 #rect
Ts0 f31 @|StepIcon #fIcon
Ts0 f32 expr out #txt
Ts0 f32 93 648 408 648 #arcP
Ts0 f30 expr out #txt
Ts0 f30 520 648 587 648 #arcP
Ts0 f26 expr out #txt
Ts0 f26 464 574 464 626 #arcP
Ts0 f27 actionDecl 'ch.ivy.addon.portalkit.component.TaskWidget.TaskWidgetData out;
' #txt
Ts0 f27 actionTable 'out=in;
' #txt
Ts0 f27 actionCode 'import ch.ivy.addon.portalkit.bean.PermissionBean;
import ch.ivy.addon.portalkit.enums.FilterType;
import ch.ivy.addon.portalkit.service.TaskFilterService;

if(!in.dataModel.compactMode) {
	TaskFilterService taskFilterService = new TaskFilterService();
	in.taskPrivateFilters = taskFilterService.getPrivateFilterForCurrentUser(in.taskFilterGroupId) as List;
	in.taskPublicFilters = taskFilterService.getPublicFilter(in.taskFilterGroupId) as List;
	in.filterType = FilterType.ONLY_ME;
	in.dataModel.initColumnsConfiguration();
}
' #txt
Ts0 f27 type ch.ivy.addon.portalkit.component.TaskWidget.TaskWidgetData #txt
Ts0 f27 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>load filter set
and init column configuration</name>
        <nameStyle>45,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ts0 f27 54 160 36 24 28 -14 #rect
Ts0 f27 @|StepIcon #fIcon
Ts0 f1 expr out #txt
Ts0 f1 72 83 72 160 #arcP
Ts0 f3 type ch.ivy.addon.portalkit.component.TaskWidget.TaskWidgetData #txt
Ts0 f3 59 235 26 26 0 12 #rect
Ts0 f3 @|RichDialogProcessEndIcon #fIcon
Ts0 f34 guid 15F5C0F9B77406B4 #txt
Ts0 f34 type ch.ivy.addon.portalkit.component.TaskWidget.TaskWidgetData #txt
Ts0 f34 method deleteFilter() #txt
Ts0 f34 disableUIEvents false #txt
Ts0 f34 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<> param = methodEvent.getInputArguments();
' #txt
Ts0 f34 outParameterDecl '<> result;
' #txt
Ts0 f34 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>deleteFilter()</name>
    </language>
</elementInfo>
' #txt
Ts0 f34 67 731 26 26 -34 15 #rect
Ts0 f34 @|RichDialogMethodStartIcon #fIcon
Ts0 f35 actionDecl 'ch.ivy.addon.portalkit.component.TaskWidget.TaskWidgetData out;
' #txt
Ts0 f35 actionTable 'out=in;
' #txt
Ts0 f35 actionCode 'import ch.ivy.addon.portalkit.enums.FilterType;
import ch.ivy.addon.portalkit.taskfilter.TaskFilterData;
import ch.ivy.addon.portalkit.service.TaskFilterService;
TaskFilterService service = new TaskFilterService();
service.delete(in.taskFilterDataToBeRemoved.getId());
if(in.taskFilterDataToBeRemoved.type == FilterType.ONLY_ME) {
	in.taskPrivateFilters.remove(in.taskFilterDataToBeRemoved);
} else {
	in.taskPublicFilters.remove(in.taskFilterDataToBeRemoved);
}
if (in.dataModel.#selectedTaskFilterData is initialized && in.dataModel.selectedTaskFilterData.equals(in.taskFilterDataToBeRemoved)) {
	in.dataModel.resetFilters();
	in.keyword = null;
}' #txt
Ts0 f35 type ch.ivy.addon.portalkit.component.TaskWidget.TaskWidgetData #txt
Ts0 f35 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>remove filter</name>
        <nameStyle>13,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ts0 f35 176 722 112 44 -33 -8 #rect
Ts0 f35 @|StepIcon #fIcon
Ts0 f36 type ch.ivy.addon.portalkit.component.TaskWidget.TaskWidgetData #txt
Ts0 f36 355 731 26 26 0 12 #rect
Ts0 f36 @|RichDialogProcessEndIcon #fIcon
Ts0 f39 expr out #txt
Ts0 f39 93 744 176 744 #arcP
Ts0 f40 expr out #txt
Ts0 f40 288 744 355 744 #arcP
Ts0 f41 guid 15F5C9F2AA5C9BA1 #txt
Ts0 f41 type ch.ivy.addon.portalkit.component.TaskWidget.TaskWidgetData #txt
Ts0 f41 method setFilterToBeDeleted(ch.ivy.addon.portalkit.taskfilter.TaskFilterData) #txt
Ts0 f41 disableUIEvents false #txt
Ts0 f41 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<ch.ivy.addon.portalkit.taskfilter.TaskFilterData taskFilterData> param = methodEvent.getInputArguments();
' #txt
Ts0 f41 inParameterMapAction 'out.taskFilterDataToBeRemoved=param.taskFilterData;
' #txt
Ts0 f41 outParameterDecl '<> result;
' #txt
Ts0 f41 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>setFilterToBeDeleted(TaskFilterData)</name>
    </language>
</elementInfo>
' #txt
Ts0 f41 67 827 26 26 -73 15 #rect
Ts0 f41 @|RichDialogMethodStartIcon #fIcon
Ts0 f42 type ch.ivy.addon.portalkit.component.TaskWidget.TaskWidgetData #txt
Ts0 f42 355 827 26 26 0 12 #rect
Ts0 f42 @|RichDialogProcessEndIcon #fIcon
Ts0 f43 93 840 355 840 #arcP
Ts0 f45 actionDecl 'ch.ivy.addon.portalkit.component.TaskWidget.TaskWidgetData out;
' #txt
Ts0 f45 actionTable 'out=in;
' #txt
Ts0 f45 actionCode 'import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import ch.ivy.addon.portalkit.service.TaskFilterService;
TaskFilterService service = new TaskFilterService();
in.isFilterExisted = false;
if (service.isFilterExisted(in.filterSetName, in.filterType, in.taskFilterGroupId)) {
	FacesMessage message = new FacesMessage( FacesMessage.SEVERITY_ERROR, ivy.cms.co("/ch.ivy.addon.portalkit.ui.jsf/components/taskView/filterExistedValidationError"), "");
	FacesContext.getCurrentInstance().addMessage("", message);
	FacesContext.getCurrentInstance().validationFailed();
	in.isFilterExisted = true;
}' #txt
Ts0 f45 type ch.ivy.addon.portalkit.component.TaskWidget.TaskWidgetData #txt
Ts0 f45 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>validate</name>
        <nameStyle>8,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ts0 f45 152 530 112 44 -21 -8 #rect
Ts0 f45 @|StepIcon #fIcon
Ts0 f46 expr out #txt
Ts0 f46 93 552 152 552 #arcP
Ts0 f47 type ch.ivy.addon.portalkit.component.TaskWidget.TaskWidgetData #txt
Ts0 f47 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>is filter
existed?</name>
        <nameStyle>18,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ts0 f47 320 536 32 32 -23 18 #rect
Ts0 f47 @|AlternativeIcon #fIcon
Ts0 f48 expr out #txt
Ts0 f48 264 552 320 552 #arcP
Ts0 f25 expr in #txt
Ts0 f25 outCond 'in.isFilterExisted == false' #txt
Ts0 f25 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>no</name>
        <nameStyle>2,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ts0 f25 352 552 408 552 #arcP
Ts0 f50 type ch.ivy.addon.portalkit.component.TaskWidget.TaskWidgetData #txt
Ts0 f50 579 491 26 26 0 12 #rect
Ts0 f50 @|RichDialogProcessEndIcon #fIcon
Ts0 f51 expr in #txt
Ts0 f51 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>yes</name>
        <nameStyle>3,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ts0 f51 336 536 579 504 #arcP
Ts0 f51 1 336 504 #addKink
Ts0 f51 1 0.3018284624086855 0 0 #arcLabel
Ts0 f16 guid 15F772190BB92384 #txt
Ts0 f16 type ch.ivy.addon.portalkit.component.TaskWidget.TaskWidgetData #txt
Ts0 f16 method applyFilter(ch.ivy.addon.portalkit.taskfilter.TaskFilterData) #txt
Ts0 f16 disableUIEvents false #txt
Ts0 f16 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<ch.ivy.addon.portalkit.taskfilter.TaskFilterData taskFilterData> param = methodEvent.getInputArguments();
' #txt
Ts0 f16 inParameterMapAction 'out.keyword=param.taskFilterData.keyword;
' #txt
Ts0 f16 inActionCode out.dataModel.applyFilter(param.taskFilterData); #txt
Ts0 f16 outParameterDecl '<> result;
' #txt
Ts0 f16 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>applyFilter(TaskFilterData)</name>
        <nameStyle>27,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ts0 f16 67 923 26 26 -72 15 #rect
Ts0 f16 @|RichDialogMethodStartIcon #fIcon
Ts0 f17 type ch.ivy.addon.portalkit.component.TaskWidget.TaskWidgetData #txt
Ts0 f17 355 923 26 26 0 12 #rect
Ts0 f17 @|RichDialogProcessEndIcon #fIcon
Ts0 f20 93 936 355 936 #arcP
Ts0 f65 actionDecl 'ch.ivy.addon.portalkit.component.TaskWidget.TaskWidgetData out;
' #txt
Ts0 f65 actionTable 'out=in;
' #txt
Ts0 f65 actionCode 'import org.apache.commons.beanutils.BeanUtils;
import ch.ivy.ws.addon.TaskSearchCriteria;
import ch.ivy.addon.portalkit.service.TaskQueryService;
import ch.ivyteam.ivy.workflow.query.TaskQuery;

TaskQuery query = TaskQuery.fromJson(in.taskSearchCriteria.jsonQuery);
TaskQuery newQuery = TaskQueryService.service().findNewTasks(query, in.latestTaskDate, in.taskSearchCriteria.ignoreInvolvedUser);

in.newTaskSearchCriteria = BeanUtils.cloneBean(in.taskSearchCriteria) as TaskSearchCriteria;
in.newTaskSearchCriteria.setJsonQuery(newQuery.asJson());

in.latestTaskDate = new java.util.Date();

' #txt
Ts0 f65 type ch.ivy.addon.portalkit.component.TaskWidget.TaskWidgetData #txt
Ts0 f65 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Create query for new task</name>
        <nameStyle>25
</nameStyle>
    </language>
</elementInfo>
' #txt
Ts0 f65 188 1034 144 44 -69 -8 #rect
Ts0 f65 @|StepIcon #fIcon
Ts0 f70 type ch.ivy.addon.portalkit.component.TaskWidget.TaskWidgetData #txt
Ts0 f70 processCall MultiPortal/TaskService:countTasksByCriteria(Long,ch.ivy.ws.addon.TaskSearchCriteria) #txt
Ts0 f70 doCall true #txt
Ts0 f70 requestActionDecl '<java.lang.Long serverId,ch.ivy.ws.addon.TaskSearchCriteria taskSearchCriteria> param;
' #txt
Ts0 f70 requestMappingAction 'param.serverId=in.#serverId;
param.taskSearchCriteria=in.newTaskSearchCriteria;
' #txt
Ts0 f70 responseActionDecl 'ch.ivy.addon.portalkit.component.TaskWidget.TaskWidgetData out;
' #txt
Ts0 f70 responseMappingAction 'out=in;
out.errors=result.errors;
out.numberOfNewTask=result.taskCount;
' #txt
Ts0 f70 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>get number of new tasks</name>
        <nameStyle>23,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ts0 f70 388 1036 72 40 -66 -43 #rect
Ts0 f70 @|CallSubIcon #fIcon
Ts0 f56 actionDecl 'ch.ivy.addon.portalkit.component.TaskWidget.TaskWidgetData out;
' #txt
Ts0 f56 actionTable 'out=in;
' #txt
Ts0 f56 actionCode 'import ch.ivy.addon.portalkit.util.Dates;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

if (in.numberOfNewTask > 0){
	RequestContext requesContext = RequestContext.getCurrentInstance();
      FacesContext facesContext = FacesContext.getCurrentInstance();
      String notification = ivy.cms.co("/ch.ivy.addon.portalkit.ui.jsf/taskList/newTaskNotification");
      facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, notification, null));
      requesContext.update("task-widget:task-polling-form:new-task-notification");        
}' #txt
Ts0 f56 type ch.ivy.addon.portalkit.component.TaskWidget.TaskWidgetData #txt
Ts0 f56 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>show notification</name>
        <nameStyle>17
</nameStyle>
    </language>
</elementInfo>
' #txt
Ts0 f56 768 1034 112 44 -46 -8 #rect
Ts0 f56 @|StepIcon #fIcon
Ts0 f59 actionDecl 'ch.ivy.addon.portalkit.component.TaskWidget.TaskWidgetData out;
' #txt
Ts0 f59 actionTable 'out=in;
' #txt
Ts0 f59 actionCode 'import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;
	
RequestContext requesContext = RequestContext.getCurrentInstance();

if(in.scrollPosition == 0 && in.expandedTaskId < 1) {	
	requesContext.update("task-widget:task-view-container");
	requesContext.execute("taskWidget.setupScrollbar()");
}' #txt
Ts0 f59 type ch.ivy.addon.portalkit.component.TaskWidget.TaskWidgetData #txt
Ts0 f59 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>update task list</name>
        <nameStyle>16
</nameStyle>
    </language>
</elementInfo>
' #txt
Ts0 f59 1008 1034 112 44 -41 -8 #rect
Ts0 f59 @|StepIcon #fIcon
Ts0 f22 type ch.ivy.addon.portalkit.component.TaskWidget.TaskWidgetData #txt
Ts0 f22 1195 1043 26 26 0 12 #rect
Ts0 f22 @|RichDialogProcessEndIcon #fIcon
Ts0 f54 type ch.ivy.addon.portalkit.component.TaskWidget.TaskWidgetData #txt
Ts0 f54 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>number of tasks change ?</name>
        <nameStyle>24
</nameStyle>
    </language>
</elementInfo>
' #txt
Ts0 f54 576 1040 32 32 -71 18 #rect
Ts0 f54 @|AlternativeIcon #fIcon
Ts0 f49 expr out #txt
Ts0 f49 1120 1056 1195 1056 #arcP
Ts0 f72 expr in #txt
Ts0 f72 outCond 'in.numberOfNewTask > 0' #txt
Ts0 f72 608 1056 768 1056 #arcP
Ts0 f13 guid 15F99790EE1F6D5A #txt
Ts0 f13 type ch.ivy.addon.portalkit.component.TaskWidget.TaskWidgetData #txt
Ts0 f13 method refreshTaskList(java.lang.Long) #txt
Ts0 f13 disableUIEvents false #txt
Ts0 f13 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<java.lang.Long expandedTaskId> param = methodEvent.getInputArguments();
' #txt
Ts0 f13 inParameterMapAction 'out.expandedTaskId=param.expandedTaskId;
' #txt
Ts0 f13 outParameterDecl '<> result;
' #txt
Ts0 f13 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>refreshTaskList(Long)</name>
        <nameStyle>21,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ts0 f13 67 1043 26 26 -61 15 #rect
Ts0 f13 @|RichDialogMethodStartIcon #fIcon
Ts0 f15 expr out #txt
Ts0 f15 880 1056 1008 1056 #arcP
Ts0 f21 expr out #txt
Ts0 f21 332 1056 388 1056 #arcP
Ts0 f57 expr out #txt
Ts0 f57 72 184 72 235 #arcP
Ts0 f18 expr out #txt
Ts0 f18 93 1056 188 1056 #arcP
Ts0 f63 expr in #txt
Ts0 f63 592 1040 1064 1034 #arcP
Ts0 f63 1 592 968 #addKink
Ts0 f63 2 1064 968 #addKink
Ts0 f63 1 0.5028846153846154 0 0 #arcLabel
Ts0 f58 expr out #txt
Ts0 f58 460 1056 576 1056 #arcP
Ts0 f19 expr out #txt
Ts0 f19 552 184 552 237 #arcP
>Proto Ts0 .type ch.ivy.addon.portalkit.component.TaskWidget.TaskWidgetData #txt
>Proto Ts0 .processKind HTML_DIALOG #txt
>Proto Ts0 -8 -8 16 16 16 26 #rect
>Proto Ts0 '' #fIcon
Ts0 f91 mainOut f90 tail #connect
Ts0 f90 head f83 mainIn #connect
Ts0 f93 mainOut f89 tail #connect
Ts0 f89 head f83 mainIn #connect
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
Ts0 f82 mainOut f5 tail #connect
Ts0 f5 head f4 mainIn #connect
Ts0 f4 mainOut f6 tail #connect
Ts0 f6 head f83 mainIn #connect
Ts0 f28 mainOut f32 tail #connect
Ts0 f32 head f31 mainIn #connect
Ts0 f31 mainOut f30 tail #connect
Ts0 f30 head f29 mainIn #connect
Ts0 f24 mainOut f26 tail #connect
Ts0 f26 head f31 mainIn #connect
Ts0 f0 mainOut f1 tail #connect
Ts0 f1 head f27 mainIn #connect
Ts0 f34 mainOut f39 tail #connect
Ts0 f39 head f35 mainIn #connect
Ts0 f35 mainOut f40 tail #connect
Ts0 f40 head f36 mainIn #connect
Ts0 f41 mainOut f43 tail #connect
Ts0 f43 head f42 mainIn #connect
Ts0 f23 mainOut f46 tail #connect
Ts0 f46 head f45 mainIn #connect
Ts0 f45 mainOut f48 tail #connect
Ts0 f48 head f47 in #connect
Ts0 f47 out f25 tail #connect
Ts0 f25 head f24 mainIn #connect
Ts0 f47 out f51 tail #connect
Ts0 f51 head f50 mainIn #connect
Ts0 f16 mainOut f20 tail #connect
Ts0 f20 head f17 mainIn #connect
Ts0 f59 mainOut f49 tail #connect
Ts0 f49 head f22 mainIn #connect
Ts0 f54 out f72 tail #connect
Ts0 f72 head f56 mainIn #connect
Ts0 f56 mainOut f15 tail #connect
Ts0 f15 head f59 mainIn #connect
Ts0 f65 mainOut f21 tail #connect
Ts0 f21 head f70 mainIn #connect
Ts0 f27 mainOut f57 tail #connect
Ts0 f57 head f3 mainIn #connect
Ts0 f13 mainOut f18 tail #connect
Ts0 f18 head f65 mainIn #connect
Ts0 f54 out f63 tail #connect
Ts0 f63 head f59 mainIn #connect
Ts0 f70 mainOut f58 tail #connect
Ts0 f58 head f54 in #connect
Ts0 f98 mainOut f19 tail #connect
Ts0 f19 head f100 mainIn #connect
