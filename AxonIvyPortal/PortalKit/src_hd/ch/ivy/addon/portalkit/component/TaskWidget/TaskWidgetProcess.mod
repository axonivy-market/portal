[Ivy]
15035F535EFB1618 3.26 #module
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
Ts0 @GridStep f65 '' #zField
Ts0 @GridStep f56 '' #zField
Ts0 @GridStep f59 '' #zField
Ts0 @RichDialogProcessEnd f22 '' #zField
Ts0 @Alternative f54 '' #zField
Ts0 @PushWFArc f49 '' #zField
Ts0 @PushWFArc f72 '' #zField
Ts0 @RichDialogMethodStart f13 '' #zField
Ts0 @PushWFArc f15 '' #zField
Ts0 @PushWFArc f57 '' #zField
Ts0 @PushWFArc f18 '' #zField
Ts0 @PushWFArc f63 '' #zField
Ts0 @GridStep f8 '' #zField
Ts0 @PushWFArc f10 '' #zField
Ts0 @PushWFArc f1 '' #zField
Ts0 @RichDialogMethodStart f11 '' #zField
Ts0 @GridStep f12 '' #zField
Ts0 @RichDialogProcessEnd f14 '' #zField
Ts0 @PushWFArc f33 '' #zField
Ts0 @PushWFArc f37 '' #zField
Ts0 @RichDialogMethodStart f9 '' #zField
Ts0 @CallSub f19 '' #zField
Ts0 @PushWFArc f38 '' #zField
Ts0 @RichDialogProcessEnd f44 '' #zField
Ts0 @PushWFArc f52 '' #zField
Ts0 @RichDialogMethodStart f2 '' #zField
Ts0 @CallSub f7 '' #zField
Ts0 @RichDialogProcessEnd f53 '' #zField
Ts0 @PushWFArc f55 '' #zField
Ts0 @PushWFArc f60 '' #zField
Ts0 @RichDialogMethodStart f4 '' #zField
Ts0 @GridStep f5 '' #zField
Ts0 @PushWFArc f6 '' #zField
Ts0 @RichDialogProcessEnd f61 '' #zField
Ts0 @PushWFArc f62 '' #zField
Ts0 @RichDialogMethodStart f64 '' #zField
Ts0 @RichDialogProcessEnd f66 '' #zField
Ts0 @GridStep f67 '' #zField
Ts0 @PushWFArc f68 '' #zField
Ts0 @PushWFArc f69 '' #zField
Ts0 @CallSub f21 '' #zField
Ts0 @PushWFArc f58 '' #zField
Ts0 @PushWFArc f70 '' #zField
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
Ts0 f0 inActionCode 'if (param.#dataModel is initialized){
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
Ts0 f0 85 85 22 22 -14 13 #rect
Ts0 f0 @|RichDialogInitStartIcon #fIcon
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
Ts0 f23 83 563 26 26 -26 15 #rect
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
Ts0 f24 424 554 112 44 -36 -8 #rect
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
Ts0 f28 83 659 26 26 -58 15 #rect
Ts0 f28 @|RichDialogProcessStartIcon #fIcon
Ts0 f29 type ch.ivy.addon.portalkit.component.TaskWidget.TaskWidgetData #txt
Ts0 f29 603 659 26 26 0 12 #rect
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
Ts0 f31 424 650 112 44 -28 -8 #rect
Ts0 f31 @|StepIcon #fIcon
Ts0 f32 expr out #txt
Ts0 f32 109 672 424 672 #arcP
Ts0 f30 expr out #txt
Ts0 f30 536 672 603 672 #arcP
Ts0 f26 expr out #txt
Ts0 f26 480 598 480 650 #arcP
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
}
' #txt
Ts0 f27 type ch.ivy.addon.portalkit.component.TaskWidget.TaskWidgetData #txt
Ts0 f27 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>load filter set&#xD;
and init column &#xD;
configuration</name>
        <nameStyle>48,5
</nameStyle>
    </language>
</elementInfo>
' #txt
Ts0 f27 320 66 128 60 -41 -24 #rect
Ts0 f27 @|StepIcon #fIcon
Ts0 f3 type ch.ivy.addon.portalkit.component.TaskWidget.TaskWidgetData #txt
Ts0 f3 499 83 26 26 0 12 #rect
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
Ts0 f34 83 755 26 26 -34 15 #rect
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
Ts0 f35 192 746 112 44 -33 -8 #rect
Ts0 f35 @|StepIcon #fIcon
Ts0 f36 type ch.ivy.addon.portalkit.component.TaskWidget.TaskWidgetData #txt
Ts0 f36 371 755 26 26 0 12 #rect
Ts0 f36 @|RichDialogProcessEndIcon #fIcon
Ts0 f39 expr out #txt
Ts0 f39 109 768 192 768 #arcP
Ts0 f40 expr out #txt
Ts0 f40 304 768 371 768 #arcP
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
Ts0 f41 83 851 26 26 -73 15 #rect
Ts0 f41 @|RichDialogMethodStartIcon #fIcon
Ts0 f42 type ch.ivy.addon.portalkit.component.TaskWidget.TaskWidgetData #txt
Ts0 f42 371 851 26 26 0 12 #rect
Ts0 f42 @|RichDialogProcessEndIcon #fIcon
Ts0 f43 109 864 371 864 #arcP
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
Ts0 f45 168 554 112 44 -21 -8 #rect
Ts0 f45 @|StepIcon #fIcon
Ts0 f46 expr out #txt
Ts0 f46 109 576 168 576 #arcP
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
Ts0 f47 336 560 32 32 -23 18 #rect
Ts0 f47 @|AlternativeIcon #fIcon
Ts0 f48 expr out #txt
Ts0 f48 280 576 336 576 #arcP
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
Ts0 f25 368 576 424 576 #arcP
Ts0 f50 type ch.ivy.addon.portalkit.component.TaskWidget.TaskWidgetData #txt
Ts0 f50 595 515 26 26 0 12 #rect
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
Ts0 f51 352 560 595 528 #arcP
Ts0 f51 1 352 528 #addKink
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
Ts0 f16 83 947 26 26 -72 15 #rect
Ts0 f16 @|RichDialogMethodStartIcon #fIcon
Ts0 f17 type ch.ivy.addon.portalkit.component.TaskWidget.TaskWidgetData #txt
Ts0 f17 371 947 26 26 0 12 #rect
Ts0 f17 @|RichDialogProcessEndIcon #fIcon
Ts0 f20 109 960 371 960 #arcP
Ts0 f65 actionDecl 'ch.ivy.addon.portalkit.component.TaskWidget.TaskWidgetData out;
' #txt
Ts0 f65 actionTable 'out=in;
' #txt
Ts0 f65 actionCode 'import ch.ivy.addon.portalkit.ivydata.searchcriteria.TaskSearchCriteria;
import org.apache.commons.beanutils.BeanUtils;
import ch.ivyteam.ivy.workflow.query.TaskQuery;

TaskQuery query = in.taskSearchCriteria.finalTaskQuery;
in.newTaskSearchCriteria = BeanUtils.cloneBean(in.taskSearchCriteria) as TaskSearchCriteria;
in.newTaskSearchCriteria.setFinalTaskQuery(in.newTaskSearchCriteria.createQueryToFindLatestTasks(query, in.latestTaskDate));

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
Ts0 f65 172 1034 144 44 -69 -8 #rect
Ts0 f65 @|StepIcon #fIcon
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
	facesContext.addMessage("portal-global-growl-message", new FacesMessage(FacesMessage.SEVERITY_INFO, notification, null));
	requesContext.update("portal-global-growl");        
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
Ts0 f56 584 1034 112 44 -46 -8 #rect
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
Ts0 f59 736 1034 112 44 -41 -8 #rect
Ts0 f59 @|StepIcon #fIcon
Ts0 f22 type ch.ivy.addon.portalkit.component.TaskWidget.TaskWidgetData #txt
Ts0 f22 915 1043 26 26 0 12 #rect
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
Ts0 f54 496 1040 32 32 -71 18 #rect
Ts0 f54 @|AlternativeIcon #fIcon
Ts0 f49 expr out #txt
Ts0 f49 848 1056 915 1056 #arcP
Ts0 f72 expr in #txt
Ts0 f72 outCond 'in.numberOfNewTask > 0' #txt
Ts0 f72 528 1056 584 1056 #arcP
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
Ts0 f13 83 1043 26 26 -61 15 #rect
Ts0 f13 @|RichDialogMethodStartIcon #fIcon
Ts0 f15 expr out #txt
Ts0 f15 696 1056 736 1056 #arcP
Ts0 f57 expr out #txt
Ts0 f57 448 96 499 96 #arcP
Ts0 f18 expr out #txt
Ts0 f18 109 1056 172 1056 #arcP
Ts0 f63 expr in #txt
Ts0 f63 512 1040 792 1034 #arcP
Ts0 f63 1 512 992 #addKink
Ts0 f63 2 792 992 #addKink
Ts0 f63 1 0.5028846153846154 0 0 #arcLabel
Ts0 f8 actionDecl 'ch.ivy.addon.portalkit.component.TaskWidget.TaskWidgetData out;
' #txt
Ts0 f8 actionTable 'out=in;
' #txt
Ts0 f8 actionCode 'import ch.ivy.addon.portalkit.datamodel.TaskLazyDataModel;
import ch.ivy.addon.portalkit.bean.TaskWidgetBean;
import javax.faces.context.FacesContext;

FacesContext context = FacesContext.getCurrentInstance();
TaskWidgetBean taskWidgetBean = context.getApplication().evaluateExpressionGet(context, "#{taskWidgetBean}", TaskWidgetBean.class) as TaskWidgetBean;
if (!in.#dataModel is initialized) {
	TaskLazyDataModel dataModel = new TaskLazyDataModel();
	dataModel.setCompactMode(true);
	taskWidgetBean.setDataModel(dataModel);
	in.dataModel = taskWidgetBean.dataModel;
} else {
	taskWidgetBean.setDataModel(in.dataModel);
}' #txt
Ts0 f8 security system #txt
Ts0 f8 type ch.ivy.addon.portalkit.component.TaskWidget.TaskWidgetData #txt
Ts0 f8 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>init data model</name>
    </language>
</elementInfo>
' #txt
Ts0 f8 168 74 112 44 -40 -8 #rect
Ts0 f8 @|StepIcon #fIcon
Ts0 f10 expr out #txt
Ts0 f10 107 96 168 96 #arcP
Ts0 f1 expr out #txt
Ts0 f1 280 96 320 96 #arcP
Ts0 f11 guid 1673F6EA05E1210C #txt
Ts0 f11 type ch.ivy.addon.portalkit.component.TaskWidget.TaskWidgetData #txt
Ts0 f11 method sortOnMobile() #txt
Ts0 f11 disableUIEvents false #txt
Ts0 f11 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<> param = methodEvent.getInputArguments();
' #txt
Ts0 f11 outParameterDecl '<> result;
' #txt
Ts0 f11 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>sortOnMobile()</name>
    </language>
</elementInfo>
' #txt
Ts0 f11 83 1139 26 26 -40 15 #rect
Ts0 f11 @|RichDialogMethodStartIcon #fIcon
Ts0 f12 actionDecl 'ch.ivy.addon.portalkit.component.TaskWidget.TaskWidgetData out;
' #txt
Ts0 f12 actionTable 'out=in;
' #txt
Ts0 f12 actionCode 'import org.apache.commons.lang3.StringUtils;
if (StringUtils.isNotBlank(in.mobileSelectedSort)){
	in.dataModel.sort(in.mobileSelectedSort);
}' #txt
Ts0 f12 type ch.ivy.addon.portalkit.component.TaskWidget.TaskWidgetData #txt
Ts0 f12 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>sort</name>
    </language>
</elementInfo>
' #txt
Ts0 f12 192 1130 112 44 -10 -8 #rect
Ts0 f12 @|StepIcon #fIcon
Ts0 f14 type ch.ivy.addon.portalkit.component.TaskWidget.TaskWidgetData #txt
Ts0 f14 371 1139 26 26 0 12 #rect
Ts0 f14 @|RichDialogProcessEndIcon #fIcon
Ts0 f33 expr out #txt
Ts0 f33 109 1152 192 1152 #arcP
Ts0 f37 expr out #txt
Ts0 f37 304 1152 371 1152 #arcP
Ts0 f9 guid 16811CF9CAADF848 #txt
Ts0 f9 type ch.ivy.addon.portalkit.component.TaskWidget.TaskWidgetData #txt
Ts0 f9 method findTasks(ch.ivy.addon.portalkit.ivydata.searchcriteria.TaskSearchCriteria,Integer,Integer) #txt
Ts0 f9 disableUIEvents false #txt
Ts0 f9 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<ch.ivy.addon.portalkit.ivydata.searchcriteria.TaskSearchCriteria criteria,java.lang.Integer startIndex,java.lang.Integer count> param = methodEvent.getInputArguments();
' #txt
Ts0 f9 inParameterMapAction 'out.count=param.count;
out.startIndex=param.startIndex;
out.taskSearchCriteria=param.criteria;
' #txt
Ts0 f9 outParameterDecl '<java.util.List<ch.ivyteam.ivy.workflow.ITask> tasks> result;
' #txt
Ts0 f9 outParameterMapAction 'result.tasks=in.tasks;
' #txt
Ts0 f9 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>findTasks(TaskSearchCriteria,Integer,Integer)</name>
    </language>
</elementInfo>
' #txt
Ts0 f9 83 179 26 26 -89 18 #rect
Ts0 f9 @|RichDialogMethodStartIcon #fIcon
Ts0 f19 type ch.ivy.addon.portalkit.component.TaskWidget.TaskWidgetData #txt
Ts0 f19 processCall 'Ivy Data Processes/TaskService:findTasksByCriteria(ch.ivy.addon.portalkit.ivydata.searchcriteria.TaskSearchCriteria,Integer,Integer)' #txt
Ts0 f19 doCall true #txt
Ts0 f19 requestActionDecl '<ch.ivy.addon.portalkit.ivydata.searchcriteria.TaskSearchCriteria taskSearchCriteria,java.lang.Integer startIndex,java.lang.Integer count> param;
' #txt
Ts0 f19 requestMappingAction 'param.taskSearchCriteria=in.taskSearchCriteria;
param.startIndex=in.startIndex;
param.count=in.count;
' #txt
Ts0 f19 responseActionDecl 'ch.ivy.addon.portalkit.component.TaskWidget.TaskWidgetData out;
' #txt
Ts0 f19 responseMappingAction 'out=in;
out.errors=result.errors;
out.tasks=result.tasks;
' #txt
Ts0 f19 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Find tasks</name>
    </language>
</elementInfo>
' #txt
Ts0 f19 200 170 112 44 -28 -8 #rect
Ts0 f19 @|CallSubIcon #fIcon
Ts0 f38 expr out #txt
Ts0 f38 109 192 200 192 #arcP
Ts0 f44 type ch.ivy.addon.portalkit.component.TaskWidget.TaskWidgetData #txt
Ts0 f44 403 179 26 26 0 12 #rect
Ts0 f44 @|RichDialogProcessEndIcon #fIcon
Ts0 f52 expr out #txt
Ts0 f52 312 192 403 192 #arcP
Ts0 f2 guid 16811D75407A9053 #txt
Ts0 f2 type ch.ivy.addon.portalkit.component.TaskWidget.TaskWidgetData #txt
Ts0 f2 method countTasks(ch.ivy.addon.portalkit.ivydata.searchcriteria.TaskSearchCriteria) #txt
Ts0 f2 disableUIEvents false #txt
Ts0 f2 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<ch.ivy.addon.portalkit.ivydata.searchcriteria.TaskSearchCriteria criteria> param = methodEvent.getInputArguments();
' #txt
Ts0 f2 inParameterMapAction 'out.taskSearchCriteria=param.criteria;
' #txt
Ts0 f2 outParameterDecl '<java.lang.Long totalTasks> result;
' #txt
Ts0 f2 outParameterMapAction 'result.totalTasks=in.totalTasks;
' #txt
Ts0 f2 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>countTasks(TaskSearchCriteria)</name>
    </language>
</elementInfo>
' #txt
Ts0 f2 83 275 26 26 -89 15 #rect
Ts0 f2 @|RichDialogMethodStartIcon #fIcon
Ts0 f7 type ch.ivy.addon.portalkit.component.TaskWidget.TaskWidgetData #txt
Ts0 f7 processCall 'Ivy Data Processes/TaskService:countTasksByCriteria(ch.ivy.addon.portalkit.ivydata.searchcriteria.TaskSearchCriteria)' #txt
Ts0 f7 doCall true #txt
Ts0 f7 requestActionDecl '<ch.ivy.addon.portalkit.ivydata.searchcriteria.TaskSearchCriteria taskSearchCriteria> param;
' #txt
Ts0 f7 requestMappingAction 'param.taskSearchCriteria=in.taskSearchCriteria;
' #txt
Ts0 f7 responseActionDecl 'ch.ivy.addon.portalkit.component.TaskWidget.TaskWidgetData out;
' #txt
Ts0 f7 responseMappingAction 'out=in;
out.errors=result.errors;
out.totalTasks=result.totalTasks;
' #txt
Ts0 f7 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Count tasks</name>
    </language>
</elementInfo>
' #txt
Ts0 f7 200 266 112 44 -33 -8 #rect
Ts0 f7 @|CallSubIcon #fIcon
Ts0 f53 type ch.ivy.addon.portalkit.component.TaskWidget.TaskWidgetData #txt
Ts0 f53 403 275 26 26 0 12 #rect
Ts0 f53 @|RichDialogProcessEndIcon #fIcon
Ts0 f55 expr out #txt
Ts0 f55 109 288 200 288 #arcP
Ts0 f60 expr out #txt
Ts0 f60 312 288 403 288 #arcP
Ts0 f4 guid 16811D991B9AE1CC #txt
Ts0 f4 type ch.ivy.addon.portalkit.component.TaskWidget.TaskWidgetData #txt
Ts0 f4 method sortByPriority() #txt
Ts0 f4 disableUIEvents false #txt
Ts0 f4 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<> param = methodEvent.getInputArguments();
' #txt
Ts0 f4 outParameterDecl '<> result;
' #txt
Ts0 f4 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>sortByPriority()</name>
    </language>
</elementInfo>
' #txt
Ts0 f4 83 371 26 26 -39 15 #rect
Ts0 f4 @|RichDialogMethodStartIcon #fIcon
Ts0 f5 actionDecl 'ch.ivy.addon.portalkit.component.TaskWidget.TaskWidgetData out;
' #txt
Ts0 f5 actionTable 'out=in;
' #txt
Ts0 f5 actionCode 'import ch.ivy.addon.portalkit.enums.TaskSortField;

in.dataModel.setSortField(TaskSortField.PRIORITY.toString(), false);
' #txt
Ts0 f5 type ch.ivy.addon.portalkit.component.TaskWidget.TaskWidgetData #txt
Ts0 f5 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Sort by priority</name>
    </language>
</elementInfo>
' #txt
Ts0 f5 200 362 112 44 -38 -8 #rect
Ts0 f5 @|StepIcon #fIcon
Ts0 f6 expr out #txt
Ts0 f6 109 384 200 384 #arcP
Ts0 f61 type ch.ivy.addon.portalkit.component.TaskWidget.TaskWidgetData #txt
Ts0 f61 403 371 26 26 0 12 #rect
Ts0 f61 @|RichDialogProcessEndIcon #fIcon
Ts0 f62 expr out #txt
Ts0 f62 312 384 403 384 #arcP
Ts0 f64 guid 16811DA19D0B0EF1 #txt
Ts0 f64 type ch.ivy.addon.portalkit.component.TaskWidget.TaskWidgetData #txt
Ts0 f64 method sortByExpiry() #txt
Ts0 f64 disableUIEvents false #txt
Ts0 f64 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<> param = methodEvent.getInputArguments();
' #txt
Ts0 f64 outParameterDecl '<> result;
' #txt
Ts0 f64 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>sortByExpiry()</name>
    </language>
</elementInfo>
' #txt
Ts0 f64 83 467 26 26 -37 15 #rect
Ts0 f64 @|RichDialogMethodStartIcon #fIcon
Ts0 f66 type ch.ivy.addon.portalkit.component.TaskWidget.TaskWidgetData #txt
Ts0 f66 403 467 26 26 0 12 #rect
Ts0 f66 @|RichDialogProcessEndIcon #fIcon
Ts0 f67 actionDecl 'ch.ivy.addon.portalkit.component.TaskWidget.TaskWidgetData out;
' #txt
Ts0 f67 actionTable 'out=in;
' #txt
Ts0 f67 actionCode 'import ch.ivy.addon.portalkit.enums.TaskSortField;

in.dataModel.setSortField(TaskSortField.EXPIRY_TIME.toString(), false);
' #txt
Ts0 f67 type ch.ivy.addon.portalkit.component.TaskWidget.TaskWidgetData #txt
Ts0 f67 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Sort by expiry time</name>
    </language>
</elementInfo>
' #txt
Ts0 f67 200 458 112 44 -49 -8 #rect
Ts0 f67 @|StepIcon #fIcon
Ts0 f68 expr out #txt
Ts0 f68 109 480 200 480 #arcP
Ts0 f69 expr out #txt
Ts0 f69 312 480 403 480 #arcP
Ts0 f21 type ch.ivy.addon.portalkit.component.TaskWidget.TaskWidgetData #txt
Ts0 f21 processCall 'Ivy Data Processes/TaskService:countTasksByCriteria(ch.ivy.addon.portalkit.ivydata.searchcriteria.TaskSearchCriteria)' #txt
Ts0 f21 doCall true #txt
Ts0 f21 requestActionDecl '<ch.ivy.addon.portalkit.ivydata.searchcriteria.TaskSearchCriteria taskSearchCriteria> param;
' #txt
Ts0 f21 requestMappingAction 'param.taskSearchCriteria=in.newTaskSearchCriteria;
' #txt
Ts0 f21 responseActionDecl 'ch.ivy.addon.portalkit.component.TaskWidget.TaskWidgetData out;
' #txt
Ts0 f21 responseMappingAction 'out=in;
out.errors=result.errors;
out.numberOfNewTask=result.totalTasks;
' #txt
Ts0 f21 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Count new tasks</name>
    </language>
</elementInfo>
' #txt
Ts0 f21 344 1034 112 44 -46 -8 #rect
Ts0 f21 @|CallSubIcon #fIcon
Ts0 f58 expr out #txt
Ts0 f58 316 1056 344 1056 #arcP
Ts0 f70 expr out #txt
Ts0 f70 456 1056 496 1056 #arcP
>Proto Ts0 .type ch.ivy.addon.portalkit.component.TaskWidget.TaskWidgetData #txt
>Proto Ts0 .processKind HTML_DIALOG #txt
>Proto Ts0 -8 -8 16 16 16 26 #rect
>Proto Ts0 '' #fIcon
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
Ts0 f59 mainOut f49 tail #connect
Ts0 f49 head f22 mainIn #connect
Ts0 f54 out f72 tail #connect
Ts0 f72 head f56 mainIn #connect
Ts0 f56 mainOut f15 tail #connect
Ts0 f15 head f59 mainIn #connect
Ts0 f27 mainOut f57 tail #connect
Ts0 f57 head f3 mainIn #connect
Ts0 f13 mainOut f18 tail #connect
Ts0 f18 head f65 mainIn #connect
Ts0 f54 out f63 tail #connect
Ts0 f63 head f59 mainIn #connect
Ts0 f0 mainOut f10 tail #connect
Ts0 f10 head f8 mainIn #connect
Ts0 f8 mainOut f1 tail #connect
Ts0 f1 head f27 mainIn #connect
Ts0 f11 mainOut f33 tail #connect
Ts0 f33 head f12 mainIn #connect
Ts0 f12 mainOut f37 tail #connect
Ts0 f37 head f14 mainIn #connect
Ts0 f9 mainOut f38 tail #connect
Ts0 f38 head f19 mainIn #connect
Ts0 f19 mainOut f52 tail #connect
Ts0 f52 head f44 mainIn #connect
Ts0 f2 mainOut f55 tail #connect
Ts0 f55 head f7 mainIn #connect
Ts0 f7 mainOut f60 tail #connect
Ts0 f60 head f53 mainIn #connect
Ts0 f4 mainOut f6 tail #connect
Ts0 f6 head f5 mainIn #connect
Ts0 f5 mainOut f62 tail #connect
Ts0 f62 head f61 mainIn #connect
Ts0 f64 mainOut f68 tail #connect
Ts0 f68 head f67 mainIn #connect
Ts0 f67 mainOut f69 tail #connect
Ts0 f69 head f66 mainIn #connect
Ts0 f65 mainOut f58 tail #connect
Ts0 f58 head f21 mainIn #connect
Ts0 f21 mainOut f70 tail #connect
Ts0 f70 head f54 in #connect
