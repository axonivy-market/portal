[Ivy]
163DD68942416255 3.23 #module
>Proto >Proto Collection #zClass
Ts0 TaskAnalysisWidgetProcess Big #zClass
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
Ts0 @RichDialogMethodStart f94 '' #zField
Ts0 @CallSub f98 '' #zField
Ts0 @RichDialogProcessEnd f100 '' #zField
Ts0 @RichDialogProcessEnd f67 '' #zField
Ts0 @RichDialogMethodStart f68 '' #zField
Ts0 @CallSub f69 '' #zField
Ts0 @PushWFArc f9 '' #zField
Ts0 @PushWFArc f2 '' #zField
Ts0 @PushWFArc f7 '' #zField
Ts0 @RichDialogProcessStart f23 '' #zField
Ts0 @GridStep f24 '' #zField
Ts0 @RichDialogProcessStart f28 '' #zField
Ts0 @RichDialogProcessEnd f29 '' #zField
Ts0 @GridStep f31 '' #zField
Ts0 @PushWFArc f32 '' #zField
Ts0 @PushWFArc f30 '' #zField
Ts0 @PushWFArc f26 '' #zField
Ts0 @GridStep f27 '' #zField
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
Ts0 @PushWFArc f57 '' #zField
Ts0 @PushWFArc f19 '' #zField
Ts0 @GridStep f8 '' #zField
Ts0 @PushWFArc f10 '' #zField
Ts0 @PushWFArc f1 '' #zField
Ts0 @RichDialogProcessStart f11 '' #zField
Ts0 @RichDialogProcessEnd f4 '' #zField
Ts0 @GridStep f5 '' #zField
Ts0 @PushWFArc f6 '' #zField
Ts0 @PushWFArc f12 '' #zField
Ts0 @RichDialogMethodStart f13 '' #zField
Ts0 @RichDialogProcessEnd f14 '' #zField
Ts0 @CallSub f22 '' #zField
Ts0 @GridStep f37 '' #zField
Ts0 @CallSub f44 '' #zField
Ts0 @PushWFArc f49 '' #zField
Ts0 @GridStep f21 '' #zField
Ts0 @Alternative f52 '' #zField
Ts0 @PushWFArc f18 '' #zField
Ts0 @PushWFArc f33 '' #zField
Ts0 @GridStep f61 '' #zField
Ts0 @PushWFArc f62 '' #zField
Ts0 @PushWFArc f38 '' #zField
Ts0 @PushWFArc f54 '' #zField
Ts0 @GridStep f53 '' #zField
Ts0 @PushWFArc f55 '' #zField
Ts0 @PushWFArc f15 '' #zField
Ts0 @GridStep f56 '' #zField
Ts0 @PushWFArc f58 '' #zField
Ts0 @PushWFArc f59 '' #zField
>Proto Ts0 Ts0 TaskAnalysisWidgetProcess #zField
Ts0 f0 guid 14FDF92006C61D35 #txt
Ts0 f0 type ch.ivy.addon.portalkit.component.TaskAnalysisWidget.TaskAnalysisWidgetData #txt
Ts0 f0 method start(ch.ivy.addon.portalkit.datamodel.TaskAnalysisLazyDataModel,java.lang.Long) #txt
Ts0 f0 disableUIEvents true #txt
Ts0 f0 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<ch.ivy.addon.portalkit.datamodel.TaskAnalysisLazyDataModel dataModel,java.lang.Long filterGroupId> param = methodEvent.getInputArguments();
' #txt
Ts0 f0 inParameterMapAction 'out.taskFilterGroupId=param.filterGroupId;
' #txt
Ts0 f0 inActionCode 'if (param.#dataModel is initialized){
	out.dataModel = param.dataModel;
}' #txt
Ts0 f0 outParameterDecl '<> result;
' #txt
Ts0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start(TaskAnalysisLazyDataModel,Long)</name>
        <nameStyle>37,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ts0 f0 61 61 22 22 14 0 #rect
Ts0 f0 @|RichDialogInitStartIcon #fIcon
Ts0 f94 guid 153EF14248ECB35E #txt
Ts0 f94 type ch.ivy.addon.portalkit.component.TaskAnalysisWidget.TaskAnalysisWidgetData #txt
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
Ts0 f98 type ch.ivy.addon.portalkit.component.TaskAnalysisWidget.TaskAnalysisWidgetData #txt
Ts0 f98 processCall MultiPortal/TaskService:findTasksByCriteria(Long,ch.ivy.ws.addon.TaskSearchCriteria,Integer,Integer) #txt
Ts0 f98 doCall true #txt
Ts0 f98 requestActionDecl '<java.lang.Long serverId,ch.ivy.ws.addon.TaskSearchCriteria taskSearchCriteria,java.lang.Integer startIndex,java.lang.Integer count> param;
' #txt
Ts0 f98 requestMappingAction 'param.serverId=in.#serverId;
param.taskSearchCriteria=in.taskSearchCriteria;
param.startIndex=in.startIndex;
param.count=in.count;
' #txt
Ts0 f98 responseActionDecl 'ch.ivy.addon.portalkit.component.TaskAnalysisWidget.TaskAnalysisWidgetData out;
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
Ts0 f100 type ch.ivy.addon.portalkit.component.TaskAnalysisWidget.TaskAnalysisWidgetData #txt
Ts0 f100 541 237 22 22 14 0 #rect
Ts0 f100 @|RichDialogProcessEndIcon #fIcon
Ts0 f67 type ch.ivy.addon.portalkit.component.TaskAnalysisWidget.TaskAnalysisWidgetData #txt
Ts0 f67 741 237 22 22 14 0 #rect
Ts0 f67 @|RichDialogProcessEndIcon #fIcon
Ts0 f68 guid 15403E7DE16C8732 #txt
Ts0 f68 type ch.ivy.addon.portalkit.component.TaskAnalysisWidget.TaskAnalysisWidgetData #txt
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
Ts0 f69 type ch.ivy.addon.portalkit.component.TaskAnalysisWidget.TaskAnalysisWidgetData #txt
Ts0 f69 processCall MultiPortal/TaskService:countTasksByCriteria(Long,ch.ivy.ws.addon.TaskSearchCriteria) #txt
Ts0 f69 doCall true #txt
Ts0 f69 requestActionDecl '<java.lang.Long serverId,ch.ivy.ws.addon.TaskSearchCriteria taskSearchCriteria> param;
' #txt
Ts0 f69 requestMappingAction 'param.serverId=in.#serverId;
param.taskSearchCriteria=in.taskSearchCriteria;
' #txt
Ts0 f69 responseActionDecl 'ch.ivy.addon.portalkit.component.TaskAnalysisWidget.TaskAnalysisWidgetData out;
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
Ts0 f23 guid 15F5844AD96F4A4C #txt
Ts0 f23 type ch.ivy.addon.portalkit.component.TaskAnalysisWidget.TaskAnalysisWidgetData #txt
Ts0 f23 actionDecl 'ch.ivy.addon.portalkit.component.TaskAnalysisWidget.TaskAnalysisWidgetData out;
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
Ts0 f23 67 371 26 26 -26 15 #rect
Ts0 f23 @|RichDialogProcessStartIcon #fIcon
Ts0 f24 actionDecl 'ch.ivy.addon.portalkit.component.TaskAnalysisWidget.TaskAnalysisWidgetData out;
' #txt
Ts0 f24 actionTable 'out=in;
' #txt
Ts0 f24 actionCode 'import ch.ivy.addon.portalkit.service.TaskAnalysisFilterService;
import ch.ivy.addon.portalkit.taskfilter.TaskAnalysisFilterData;
import ch.ivy.addon.portalkit.enums.FilterType;
TaskAnalysisFilterService taskFilterService = new TaskAnalysisFilterService();
TaskAnalysisFilterData taskFilterData = in.dataModel.saveTaskAnalysisFilter(in.filterSetName, in.filterType, in.taskFilterGroupId);
if(FilterType.ONLY_ME == taskFilterData.type) {
	in.taskPrivateFilters.add(taskFilterData);
	in.taskPrivateFilters = taskFilterService.sortFilters(in.taskPrivateFilters) as List;
} else {
	in.taskPublicFilters.add(taskFilterData);
	in.taskPublicFilters = taskFilterService.sortFilters(in.taskPublicFilters) as List;
}
in.dataModel.selectedTaskAnalysisFilterData = taskFilterData;' #txt
Ts0 f24 security system #txt
Ts0 f24 type ch.ivy.addon.portalkit.component.TaskAnalysisWidget.TaskAnalysisWidgetData #txt
Ts0 f24 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>save filter set</name>
        <nameStyle>15,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ts0 f24 408 362 112 44 -36 -8 #rect
Ts0 f24 @|StepIcon #fIcon
Ts0 f28 guid 15F5BFFC0DEF027B #txt
Ts0 f28 type ch.ivy.addon.portalkit.component.TaskAnalysisWidget.TaskAnalysisWidgetData #txt
Ts0 f28 actionDecl 'ch.ivy.addon.portalkit.component.TaskAnalysisWidget.TaskAnalysisWidgetData out;
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
Ts0 f28 67 467 26 26 -58 15 #rect
Ts0 f28 @|RichDialogProcessStartIcon #fIcon
Ts0 f29 type ch.ivy.addon.portalkit.component.TaskAnalysisWidget.TaskAnalysisWidgetData #txt
Ts0 f29 587 467 26 26 0 12 #rect
Ts0 f29 @|RichDialogProcessEndIcon #fIcon
Ts0 f31 actionDecl 'ch.ivy.addon.portalkit.component.TaskAnalysisWidget.TaskAnalysisWidgetData out;
' #txt
Ts0 f31 actionTable 'out=in;
' #txt
Ts0 f31 actionCode 'import ch.ivy.addon.portalkit.enums.FilterType;
in.filterType = FilterType.ONLY_ME;
in.filterSetName = "";' #txt
Ts0 f31 type ch.ivy.addon.portalkit.component.TaskAnalysisWidget.TaskAnalysisWidgetData #txt
Ts0 f31 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Clear filter</name>
        <nameStyle>12,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ts0 f31 408 458 112 44 -28 -8 #rect
Ts0 f31 @|StepIcon #fIcon
Ts0 f32 expr out #txt
Ts0 f32 93 480 408 480 #arcP
Ts0 f30 expr out #txt
Ts0 f30 520 480 587 480 #arcP
Ts0 f26 expr out #txt
Ts0 f26 464 406 464 458 #arcP
Ts0 f27 actionDecl 'ch.ivy.addon.portalkit.component.TaskAnalysisWidget.TaskAnalysisWidgetData out;
' #txt
Ts0 f27 actionTable 'out=in;
' #txt
Ts0 f27 actionCode 'import ch.ivy.addon.portalkit.bean.PermissionBean;
import ch.ivy.addon.portalkit.enums.FilterType;
import ch.ivy.addon.portalkit.service.TaskAnalysisFilterService;
TaskAnalysisFilterService taskFilterService = new TaskAnalysisFilterService();
in.taskPrivateFilters = taskFilterService.getPrivateFilterForCurrentUser(in.taskFilterGroupId) as List;
in.taskPublicFilters = taskFilterService.getPublicFilter(in.taskFilterGroupId) as List;
in.filterType = FilterType.ONLY_ME;' #txt
Ts0 f27 security system #txt
Ts0 f27 type ch.ivy.addon.portalkit.component.TaskAnalysisWidget.TaskAnalysisWidgetData #txt
Ts0 f27 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>load filter set</name>
        <nameStyle>15,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ts0 f27 54 220 36 24 20 -2 #rect
Ts0 f27 @|StepIcon #fIcon
Ts0 f3 type ch.ivy.addon.portalkit.component.TaskAnalysisWidget.TaskAnalysisWidgetData #txt
Ts0 f3 59 283 26 26 0 12 #rect
Ts0 f3 @|RichDialogProcessEndIcon #fIcon
Ts0 f34 guid 15F5C0F9B77406B4 #txt
Ts0 f34 type ch.ivy.addon.portalkit.component.TaskAnalysisWidget.TaskAnalysisWidgetData #txt
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
Ts0 f34 67 563 26 26 -34 15 #rect
Ts0 f34 @|RichDialogMethodStartIcon #fIcon
Ts0 f35 actionDecl 'ch.ivy.addon.portalkit.component.TaskAnalysisWidget.TaskAnalysisWidgetData out;
' #txt
Ts0 f35 actionTable 'out=in;
' #txt
Ts0 f35 actionCode 'import ch.ivy.addon.portalkit.enums.FilterType;
import ch.ivy.addon.portalkit.taskfilter.TaskAnalysisFilterData;
import ch.ivy.addon.portalkit.service.TaskAnalysisFilterService;
TaskAnalysisFilterService service = new TaskAnalysisFilterService();
service.delete(in.taskFilterDataToBeRemoved.getId());
if(in.taskFilterDataToBeRemoved.type == FilterType.ONLY_ME) {
	in.taskPrivateFilters.remove(in.taskFilterDataToBeRemoved);
} else {
	in.taskPublicFilters.remove(in.taskFilterDataToBeRemoved);
}
if (in.dataModel.#selectedTaskAnalysisFilterData is initialized && in.dataModel.selectedTaskAnalysisFilterData.equals(in.taskFilterDataToBeRemoved)) {
	in.dataModel.resetFilters();
}' #txt
Ts0 f35 security system #txt
Ts0 f35 type ch.ivy.addon.portalkit.component.TaskAnalysisWidget.TaskAnalysisWidgetData #txt
Ts0 f35 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>remove filter</name>
        <nameStyle>13,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ts0 f35 176 554 112 44 -33 -8 #rect
Ts0 f35 @|StepIcon #fIcon
Ts0 f36 type ch.ivy.addon.portalkit.component.TaskAnalysisWidget.TaskAnalysisWidgetData #txt
Ts0 f36 387 563 26 26 0 12 #rect
Ts0 f36 @|RichDialogProcessEndIcon #fIcon
Ts0 f39 expr out #txt
Ts0 f39 93 576 176 576 #arcP
Ts0 f40 expr out #txt
Ts0 f40 288 576 387 576 #arcP
Ts0 f41 guid 15F5C9F2AA5C9BA1 #txt
Ts0 f41 type ch.ivy.addon.portalkit.component.TaskAnalysisWidget.TaskAnalysisWidgetData #txt
Ts0 f41 method setFilterToBeDeleted(ch.ivy.addon.portalkit.taskfilter.TaskAnalysisFilterData) #txt
Ts0 f41 disableUIEvents false #txt
Ts0 f41 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<ch.ivy.addon.portalkit.taskfilter.TaskAnalysisFilterData taskFilterData> param = methodEvent.getInputArguments();
' #txt
Ts0 f41 inParameterMapAction 'out.taskFilterDataToBeRemoved=param.taskFilterData;
' #txt
Ts0 f41 outParameterDecl '<> result;
' #txt
Ts0 f41 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>setFilterToBeDeleted</name>
        <nameStyle>20,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ts0 f41 67 659 26 26 -51 14 #rect
Ts0 f41 @|RichDialogMethodStartIcon #fIcon
Ts0 f42 type ch.ivy.addon.portalkit.component.TaskAnalysisWidget.TaskAnalysisWidgetData #txt
Ts0 f42 387 659 26 26 0 12 #rect
Ts0 f42 @|RichDialogProcessEndIcon #fIcon
Ts0 f43 93 672 387 672 #arcP
Ts0 f45 actionDecl 'ch.ivy.addon.portalkit.component.TaskAnalysisWidget.TaskAnalysisWidgetData out;
' #txt
Ts0 f45 actionTable 'out=in;
' #txt
Ts0 f45 actionCode 'import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import ch.ivy.addon.portalkit.service.TaskAnalysisFilterService;
TaskAnalysisFilterService service = new TaskAnalysisFilterService();
in.isFilterExisted = false;
if (service.isFilterExisted(in.filterSetName, in.filterType, in.taskFilterGroupId)) {
	FacesMessage message = new FacesMessage( FacesMessage.SEVERITY_ERROR, ivy.cms.co("/ch.ivy.addon.portalkit.ui.jsf/components/taskView/filterExistedValidationError"), "");
	FacesContext.getCurrentInstance().addMessage("", message);
	FacesContext.getCurrentInstance().validationFailed();
	in.isFilterExisted = true;
}' #txt
Ts0 f45 type ch.ivy.addon.portalkit.component.TaskAnalysisWidget.TaskAnalysisWidgetData #txt
Ts0 f45 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>validate</name>
        <nameStyle>8,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ts0 f45 152 362 112 44 -21 -8 #rect
Ts0 f45 @|StepIcon #fIcon
Ts0 f46 expr out #txt
Ts0 f46 93 384 152 384 #arcP
Ts0 f47 type ch.ivy.addon.portalkit.component.TaskAnalysisWidget.TaskAnalysisWidgetData #txt
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
Ts0 f47 320 368 32 32 -23 18 #rect
Ts0 f47 @|AlternativeIcon #fIcon
Ts0 f48 expr out #txt
Ts0 f48 264 384 320 384 #arcP
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
Ts0 f25 352 384 408 384 #arcP
Ts0 f50 type ch.ivy.addon.portalkit.component.TaskAnalysisWidget.TaskAnalysisWidgetData #txt
Ts0 f50 579 323 26 26 0 12 #rect
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
Ts0 f51 336 368 579 336 #arcP
Ts0 f51 1 336 336 #addKink
Ts0 f51 1 0.3018284624086855 0 0 #arcLabel
Ts0 f16 guid 15F772190BB92384 #txt
Ts0 f16 type ch.ivy.addon.portalkit.component.TaskAnalysisWidget.TaskAnalysisWidgetData #txt
Ts0 f16 method applyFilter(ch.ivy.addon.portalkit.taskfilter.TaskAnalysisFilterData) #txt
Ts0 f16 disableUIEvents false #txt
Ts0 f16 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<ch.ivy.addon.portalkit.taskfilter.TaskAnalysisFilterData taskFilterData> param = methodEvent.getInputArguments();
' #txt
Ts0 f16 inActionCode out.dataModel.applyFilter(param.taskFilterData); #txt
Ts0 f16 outParameterDecl '<> result;
' #txt
Ts0 f16 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>applyFilter</name>
        <nameStyle>11,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ts0 f16 67 755 26 26 -28 15 #rect
Ts0 f16 @|RichDialogMethodStartIcon #fIcon
Ts0 f17 type ch.ivy.addon.portalkit.component.TaskAnalysisWidget.TaskAnalysisWidgetData #txt
Ts0 f17 387 755 26 26 0 12 #rect
Ts0 f17 @|RichDialogProcessEndIcon #fIcon
Ts0 f20 93 768 387 768 #arcP
Ts0 f57 expr out #txt
Ts0 f57 72 244 72 283 #arcP
Ts0 f19 expr out #txt
Ts0 f19 552 184 552 237 #arcP
Ts0 f8 actionDecl 'ch.ivy.addon.portalkit.component.TaskAnalysisWidget.TaskAnalysisWidgetData out;
' #txt
Ts0 f8 actionTable 'out=in;
' #txt
Ts0 f8 actionCode 'import ch.ivy.addon.portalkit.bean.TaskAnalysisWidgetBean;
import javax.faces.context.FacesContext;

if (!in.#dataModel is initialized) {
	FacesContext context = FacesContext.getCurrentInstance();
	TaskAnalysisWidgetBean taskAnalysisWidgetBean = context.getApplication().evaluateExpressionGet(context, "#{taskAnalysisWidgetBean}", TaskAnalysisWidgetBean.class) as TaskAnalysisWidgetBean;
	in.dataModel = taskAnalysisWidgetBean.dataModel;
} ' #txt
Ts0 f8 security system #txt
Ts0 f8 type ch.ivy.addon.portalkit.component.TaskAnalysisWidget.TaskAnalysisWidgetData #txt
Ts0 f8 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>init data model</name>
    </language>
</elementInfo>
' #txt
Ts0 f8 16 122 112 44 -40 -8 #rect
Ts0 f8 @|StepIcon #fIcon
Ts0 f10 expr out #txt
Ts0 f10 72 83 72 122 #arcP
Ts0 f1 expr out #txt
Ts0 f1 72 166 72 220 #arcP
Ts0 f11 guid 1645924828285975 #txt
Ts0 f11 type ch.ivy.addon.portalkit.component.TaskAnalysisWidget.TaskAnalysisWidgetData #txt
Ts0 f11 actionDecl 'ch.ivy.addon.portalkit.component.TaskAnalysisWidget.TaskAnalysisWidgetData out;
' #txt
Ts0 f11 actionTable 'out=in;
' #txt
Ts0 f11 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>redirectToStatisticPage</name>
        <nameStyle>23,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ts0 f11 67 867 26 26 -63 15 #rect
Ts0 f11 @|RichDialogProcessStartIcon #fIcon
Ts0 f4 type ch.ivy.addon.portalkit.component.TaskAnalysisWidget.TaskAnalysisWidgetData #txt
Ts0 f4 387 867 26 26 0 12 #rect
Ts0 f4 @|RichDialogProcessEndIcon #fIcon
Ts0 f5 actionDecl 'ch.ivy.addon.portalkit.component.TaskAnalysisWidget.TaskAnalysisWidgetData out;
' #txt
Ts0 f5 actionTable 'out=in;
' #txt
Ts0 f5 actionCode 'import javax.faces.context.FacesContext;

String statisticPageUrl = ivy.html.startref("Start Processes/PortalStart/startPortalStatistic.ivp");
FacesContext.getCurrentInstance().getExternalContext().redirect(statisticPageUrl);' #txt
Ts0 f5 type ch.ivy.addon.portalkit.component.TaskAnalysisWidget.TaskAnalysisWidgetData #txt
Ts0 f5 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Redirect to Statistic page</name>
    </language>
</elementInfo>
' #txt
Ts0 f5 160 858 144 44 -68 -8 #rect
Ts0 f5 @|StepIcon #fIcon
Ts0 f6 expr out #txt
Ts0 f6 93 880 160 880 #arcP
Ts0 f12 expr out #txt
Ts0 f12 304 880 387 880 #arcP
Ts0 f13 guid 1646EC49808E6672 #txt
Ts0 f13 type ch.ivy.addon.portalkit.component.TaskAnalysisWidget.TaskAnalysisWidgetData #txt
Ts0 f13 method getExportedFile(java.util.Map) #txt
Ts0 f13 disableUIEvents false #txt
Ts0 f13 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<java.util.Map columnsVisibility> param = methodEvent.getInputArguments();
' #txt
Ts0 f13 inParameterMapAction 'out.columnsVisibility=param.columnsVisibility;
' #txt
Ts0 f13 outParameterDecl '<org.primefaces.model.StreamedContent exportedFile> result;
' #txt
Ts0 f13 outParameterMapAction 'result.exportedFile=in.exportedFile;
' #txt
Ts0 f13 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>getExportedFile()</name>
        <nameStyle>17,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ts0 f13 67 979 26 26 -46 15 #rect
Ts0 f13 @|RichDialogMethodStartIcon #fIcon
Ts0 f14 type ch.ivy.addon.portalkit.component.TaskAnalysisWidget.TaskAnalysisWidgetData #txt
Ts0 f14 1107 1083 26 26 0 12 #rect
Ts0 f14 @|RichDialogProcessEndIcon #fIcon
Ts0 f22 type ch.ivy.addon.portalkit.component.TaskAnalysisWidget.TaskAnalysisWidgetData #txt
Ts0 f22 processCall MultiPortal/TaskService:findTasksByCriteria(Long,ch.ivy.ws.addon.TaskSearchCriteria,Integer,Integer) #txt
Ts0 f22 doCall true #txt
Ts0 f22 requestActionDecl '<java.lang.Long serverId,ch.ivy.ws.addon.TaskSearchCriteria taskSearchCriteria,java.lang.Integer startIndex,java.lang.Integer count> param;
' #txt
Ts0 f22 requestMappingAction 'param.serverId=in.#serverId;
param.taskSearchCriteria=in.taskSearchCriteria;
param.startIndex=in.wsCallCount*in.numOfTasksPerWSCall;
param.count=in.numOfTasksPerWSCall;
' #txt
Ts0 f22 responseActionDecl 'ch.ivy.addon.portalkit.component.TaskAnalysisWidget.TaskAnalysisWidgetData out;
' #txt
Ts0 f22 responseMappingAction 'out=in;
out.errors=result.errors;
out.latestTaskDate=new java.util.Date();
out.tasks=in.taskSearchCriteria.ignoreInvolvedUser ? result.allTasks : result.tasks;
' #txt
Ts0 f22 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>get tasks</name>
        <nameStyle>9,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ts0 f22 686 980 36 24 -25 21 #rect
Ts0 f22 @|CallSubIcon #fIcon
Ts0 f37 actionDecl 'ch.ivy.addon.portalkit.component.TaskAnalysisWidget.TaskAnalysisWidgetData out;
' #txt
Ts0 f37 actionTable 'out=in;
' #txt
Ts0 f37 actionCode 'import ch.ivy.addon.portalkit.util.TaskAnalysisExporter;
TaskAnalysisExporter exporter = new TaskAnalysisExporter(in.columnsVisibility);
in.exportedFile = exporter.getStreamedContent(in.collectedTasksForExporting);' #txt
Ts0 f37 type ch.ivy.addon.portalkit.component.TaskAnalysisWidget.TaskAnalysisWidgetData #txt
Ts0 f37 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>export excel</name>
    </language>
</elementInfo>
' #txt
Ts0 f37 776 1074 112 44 -32 -8 #rect
Ts0 f37 @|StepIcon #fIcon
Ts0 f44 type ch.ivy.addon.portalkit.component.TaskAnalysisWidget.TaskAnalysisWidgetData #txt
Ts0 f44 processCall MultiPortal/TaskService:countTasksByCriteria(Long,ch.ivy.ws.addon.TaskSearchCriteria) #txt
Ts0 f44 doCall true #txt
Ts0 f44 requestActionDecl '<java.lang.Long serverId,ch.ivy.ws.addon.TaskSearchCriteria taskSearchCriteria> param;
' #txt
Ts0 f44 requestMappingAction 'param.serverId=in.#serverId;
param.taskSearchCriteria=in.taskSearchCriteria;
' #txt
Ts0 f44 responseActionDecl 'ch.ivy.addon.portalkit.component.TaskAnalysisWidget.TaskAnalysisWidgetData out;
' #txt
Ts0 f44 responseMappingAction 'out=in;
out.errors=result.errors;
out.taskCount=result.taskCount;
' #txt
Ts0 f44 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>count tasks</name>
        <nameStyle>11,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ts0 f44 149 980 36 24 -37 22 #rect
Ts0 f44 @|CallSubIcon #fIcon
Ts0 f49 expr out #txt
Ts0 f49 93 992 149 992 #arcP
Ts0 f21 actionDecl 'ch.ivy.addon.portalkit.component.TaskAnalysisWidget.TaskAnalysisWidgetData out;
' #txt
Ts0 f21 actionTable 'out=in;
' #txt
Ts0 f21 actionCode 'import java.util.ArrayList;
in.wsCallCount = 0;
in.numOfTasksPerWSCall = 10;
in.collectedTasksForExporting = new ArrayList();
int temp = (in.taskCount - 1)/in.numOfTasksPerWSCall;
in.numOfWSCalls = temp + 1;
' #txt
Ts0 f21 type ch.ivy.addon.portalkit.component.TaskAnalysisWidget.TaskAnalysisWidgetData #txt
Ts0 f21 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>init</name>
    </language>
</elementInfo>
' #txt
Ts0 f21 248 970 112 44 -8 -8 #rect
Ts0 f21 @|StepIcon #fIcon
Ts0 f52 type ch.ivy.addon.portalkit.component.TaskAnalysisWidget.TaskAnalysisWidgetData #txt
Ts0 f52 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>continue collecting
tasks?</name>
        <nameStyle>26,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ts0 f52 576 976 32 32 -51 18 #rect
Ts0 f52 @|AlternativeIcon #fIcon
Ts0 f18 expr in #txt
Ts0 f18 outCond 'in.wsCallCount < in.numOfWSCalls' #txt
Ts0 f18 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>yes</name>
        <nameStyle>3,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ts0 f18 608 992 686 992 #arcP
Ts0 f18 0 0.41025641025641024 0 -9 #arcLabel
Ts0 f33 expr out #txt
Ts0 f33 185 992 248 992 #arcP
Ts0 f33 0 0.25870596323124495 0 0 #arcLabel
Ts0 f61 actionDecl 'ch.ivy.addon.portalkit.component.TaskAnalysisWidget.TaskAnalysisWidgetData out;
' #txt
Ts0 f61 actionTable 'out=in;
' #txt
Ts0 f61 actionCode 'import java.util.ArrayList;
int collectedTaskSize = in.collectedTasksForExporting.size();
int numOfDuplicatedTasksToCheck = 100;
List removedTasks = new ArrayList();
//To prevent duplicated tasks due to multiple WS call
if (collectedTaskSize > numOfDuplicatedTasksToCheck) {
	for (int i = 0; i < numOfDuplicatedTasksToCheck; i++) {
		int j = i;
		int taskCount = numOfDuplicatedTasksToCheck < in.tasks.size() ? numOfDuplicatedTasksToCheck : in.tasks.size();
		for (j; j < taskCount; j++) {
			if(in.tasks.get(j).getId() == in.collectedTasksForExporting.get(collectedTaskSize - i - 1).getId()) {
				removedTasks.add(in.collectedTasksForExporting.get(collectedTaskSize - i - 1));
				break;
			}
		}
		if (j == taskCount) {
			break;
		}
	}
}
in.collectedTasksForExporting.removeAll(removedTasks);
//end preventing duplicated tasks
in.collectedTasksForExporting.addAll(in.tasks);
in.wsCallCount++;' #txt
Ts0 f61 type ch.ivy.addon.portalkit.component.TaskAnalysisWidget.TaskAnalysisWidgetData #txt
Ts0 f61 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>add to collected tasks</name>
    </language>
</elementInfo>
' #txt
Ts0 f61 768 970 128 44 -59 -8 #rect
Ts0 f61 @|StepIcon #fIcon
Ts0 f62 expr out #txt
Ts0 f62 722 992 768 992 #arcP
Ts0 f38 expr in #txt
Ts0 f38 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>no</name>
        <nameStyle>2,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ts0 f38 592 1008 776 1096 #arcP
Ts0 f38 1 592 1096 #addKink
Ts0 f38 1 0.2391304347826087 0 -12 #arcLabel
Ts0 f54 expr out #txt
Ts0 f54 832 970 592 976 #arcP
Ts0 f54 1 832 920 #addKink
Ts0 f54 2 592 920 #addKink
Ts0 f54 0 0.7270590020826535 0 0 #arcLabel
Ts0 f53 actionDecl 'ch.ivy.addon.portalkit.component.TaskAnalysisWidget.TaskAnalysisWidgetData out;
' #txt
Ts0 f53 actionTable 'out=in;
' #txt
Ts0 f53 actionCode 'in.dataModel.queryCriteria.sortField = in.sortField;
in.dataModel.queryCriteria.sortDescending = in.sortDescending;' #txt
Ts0 f53 type ch.ivy.addon.portalkit.component.TaskAnalysisWidget.TaskAnalysisWidgetData #txt
Ts0 f53 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>restore sorting&#xD;
in data model</name>
    </language>
</elementInfo>
' #txt
Ts0 f53 928 1074 128 44 -40 -16 #rect
Ts0 f53 @|StepIcon #fIcon
Ts0 f55 expr out #txt
Ts0 f55 888 1096 928 1096 #arcP
Ts0 f55 0 0.9184538480715879 0 0 #arcLabel
Ts0 f15 expr out #txt
Ts0 f15 1056 1096 1107 1096 #arcP
Ts0 f15 0 0.9184538480715879 0 0 #arcLabel
Ts0 f56 actionDecl 'ch.ivy.addon.portalkit.component.TaskAnalysisWidget.TaskAnalysisWidgetData out;
' #txt
Ts0 f56 actionTable 'out=in;
' #txt
Ts0 f56 actionCode 'in.sortField = in.dataModel.queryCriteria.sortField;
in.sortDescending = in.dataModel.queryCriteria.sortDescending;
in.dataModel.queryCriteria.sortField = "TASK_ID";
in.dataModel.queryCriteria.sortDescending = true;' #txt
Ts0 f56 type ch.ivy.addon.portalkit.component.TaskAnalysisWidget.TaskAnalysisWidgetData #txt
Ts0 f56 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>sort by&#xD;
task ID</name>
    </language>
</elementInfo>
' #txt
Ts0 f56 408 970 112 44 -19 -16 #rect
Ts0 f56 @|StepIcon #fIcon
Ts0 f58 expr out #txt
Ts0 f58 360 992 408 992 #arcP
Ts0 f59 expr out #txt
Ts0 f59 520 992 576 992 #arcP
>Proto Ts0 .type ch.ivy.addon.portalkit.component.TaskAnalysisWidget.TaskAnalysisWidgetData #txt
>Proto Ts0 .processKind HTML_DIALOG #txt
>Proto Ts0 -8 -8 16 16 16 26 #rect
>Proto Ts0 '' #fIcon
Ts0 f94 mainOut f9 tail #connect
Ts0 f9 head f98 mainIn #connect
Ts0 f69 mainOut f2 tail #connect
Ts0 f2 head f67 mainIn #connect
Ts0 f68 mainOut f7 tail #connect
Ts0 f7 head f69 mainIn #connect
Ts0 f28 mainOut f32 tail #connect
Ts0 f32 head f31 mainIn #connect
Ts0 f31 mainOut f30 tail #connect
Ts0 f30 head f29 mainIn #connect
Ts0 f24 mainOut f26 tail #connect
Ts0 f26 head f31 mainIn #connect
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
Ts0 f27 mainOut f57 tail #connect
Ts0 f57 head f3 mainIn #connect
Ts0 f98 mainOut f19 tail #connect
Ts0 f19 head f100 mainIn #connect
Ts0 f0 mainOut f10 tail #connect
Ts0 f10 head f8 mainIn #connect
Ts0 f8 mainOut f1 tail #connect
Ts0 f1 head f27 mainIn #connect
Ts0 f11 mainOut f6 tail #connect
Ts0 f6 head f5 mainIn #connect
Ts0 f5 mainOut f12 tail #connect
Ts0 f12 head f4 mainIn #connect
Ts0 f13 mainOut f49 tail #connect
Ts0 f49 head f44 mainIn #connect
Ts0 f52 out f18 tail #connect
Ts0 f18 head f22 mainIn #connect
Ts0 f44 mainOut f33 tail #connect
Ts0 f33 head f21 mainIn #connect
Ts0 f22 mainOut f62 tail #connect
Ts0 f62 head f61 mainIn #connect
Ts0 f52 out f38 tail #connect
Ts0 f38 head f37 mainIn #connect
Ts0 f61 mainOut f54 tail #connect
Ts0 f54 head f52 in #connect
Ts0 f37 mainOut f55 tail #connect
Ts0 f55 head f53 mainIn #connect
Ts0 f53 mainOut f15 tail #connect
Ts0 f15 head f14 mainIn #connect
Ts0 f21 mainOut f58 tail #connect
Ts0 f58 head f56 mainIn #connect
Ts0 f56 mainOut f59 tail #connect
Ts0 f59 head f52 in #connect
