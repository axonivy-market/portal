[Ivy]
163DD68942416255 7.5.0 #module
>Proto >Proto Collection #zClass
Ts0 TaskAnalysisWidgetProcess Big #zClass
Ts0 RD #cInfo
Ts0 #process
Ts0 @TextInP .type .type #zField
Ts0 @TextInP .processKind .processKind #zField
Ts0 @AnnotationInP-0n ai ai #zField
Ts0 @MessageFlowInP-0n messageIn messageIn #zField
Ts0 @MessageFlowOutP-0n messageOut messageOut #zField
Ts0 @TextInP .xml .xml #zField
Ts0 @TextInP .responsibility .responsibility #zField
Ts0 @UdInit f0 '' #zField
Ts0 @UdMethod f94 '' #zField
Ts0 @UdEvent f23 '' #zField
Ts0 @GridStep f24 '' #zField
Ts0 @UdEvent f28 '' #zField
Ts0 @UdProcessEnd f29 '' #zField
Ts0 @GridStep f31 '' #zField
Ts0 @PushWFArc f32 '' #zField
Ts0 @PushWFArc f30 '' #zField
Ts0 @PushWFArc f26 '' #zField
Ts0 @GridStep f27 '' #zField
Ts0 @UdProcessEnd f3 '' #zField
Ts0 @UdMethod f34 '' #zField
Ts0 @GridStep f35 '' #zField
Ts0 @UdProcessEnd f36 '' #zField
Ts0 @PushWFArc f39 '' #zField
Ts0 @PushWFArc f40 '' #zField
Ts0 @UdMethod f41 '' #zField
Ts0 @UdProcessEnd f42 '' #zField
Ts0 @PushWFArc f43 '' #zField
Ts0 @GridStep f45 '' #zField
Ts0 @PushWFArc f46 '' #zField
Ts0 @Alternative f47 '' #zField
Ts0 @PushWFArc f48 '' #zField
Ts0 @PushWFArc f25 '' #zField
Ts0 @UdProcessEnd f50 '' #zField
Ts0 @PushWFArc f51 '' #zField
Ts0 @UdMethod f16 '' #zField
Ts0 @UdProcessEnd f17 '' #zField
Ts0 @PushWFArc f20 '' #zField
Ts0 @PushWFArc f57 '' #zField
Ts0 @GridStep f8 '' #zField
Ts0 @PushWFArc f10 '' #zField
Ts0 @PushWFArc f1 '' #zField
Ts0 @UdEvent f11 '' #zField
Ts0 @UdProcessEnd f4 '' #zField
Ts0 @GridStep f5 '' #zField
Ts0 @PushWFArc f6 '' #zField
Ts0 @PushWFArc f12 '' #zField
Ts0 @UdMethod f13 '' #zField
Ts0 @UdProcessEnd f14 '' #zField
Ts0 @GridStep f37 '' #zField
Ts0 @PushWFArc f56 '' #zField
Ts0 @CallSub f15 '' #zField
Ts0 @PushWFArc f18 '' #zField
Ts0 @PushWFArc f21 '' #zField
Ts0 @CallSub f9 '' #zField
Ts0 @PushWFArc f19 '' #zField
Ts0 @UdProcessEnd f22 '' #zField
Ts0 @PushWFArc f33 '' #zField
Ts0 @UdMethod f2 '' #zField
Ts0 @CallSub f7 '' #zField
Ts0 @UdProcessEnd f38 '' #zField
Ts0 @PushWFArc f44 '' #zField
Ts0 @PushWFArc f49 '' #zField
>Proto Ts0 Ts0 TaskAnalysisWidgetProcess #zField
Ts0 f0 guid 14FDF92006C61D35 #txt
Ts0 f0 method start(ch.ivy.addon.portalkit.datamodel.TaskAnalysisLazyDataModel,java.lang.Long) #txt
Ts0 f0 inParameterDecl '<ch.ivy.addon.portalkit.datamodel.TaskAnalysisLazyDataModel dataModel,Long filterGroupId> param;' #txt
Ts0 f0 inParameterMapAction 'out.taskFilterGroupId=param.filterGroupId;
' #txt
Ts0 f0 inActionCode 'if (param.#dataModel is initialized){
	out.dataModel = param.dataModel;
}' #txt
Ts0 f0 outParameterDecl '<> result;' #txt
Ts0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start(TaskAnalysisLazyDataModel,Long)</name>
        <nameStyle>37,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ts0 f0 85 53 22 22 -83 20 #rect
Ts0 f0 @|UdInitIcon #fIcon
Ts0 f94 guid 153EF14248ECB35E #txt
Ts0 f94 method findTasks(ch.ivy.addon.portalkit.ivydata.searchcriteria.TaskSearchCriteria,Integer,Integer) #txt
Ts0 f94 inParameterDecl '<ch.ivy.addon.portalkit.ivydata.searchcriteria.TaskSearchCriteria criteria,Integer startIndex,Integer count> param;' #txt
Ts0 f94 inParameterMapAction 'out.count=param.count;
out.startIndex=param.startIndex;
out.taskSearchCriteria=param.criteria;
' #txt
Ts0 f94 outParameterDecl '<java.util.List<ch.ivyteam.ivy.workflow.ITask> tasks> result;' #txt
Ts0 f94 outParameterMapAction 'result.tasks=in.tasks;
' #txt
Ts0 f94 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>findTasks(TaskSearchCriteria,Integer,Integer)</name>
    </language>
</elementInfo>
' #txt
Ts0 f94 85 149 22 22 -76 10 #rect
Ts0 f94 @|UdMethodIcon #fIcon
Ts0 f23 guid 15F5844AD96F4A4C #txt
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
Ts0 f23 @|UdEventIcon #fIcon
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
Ts0 f28 @|UdEventIcon #fIcon
Ts0 f29 587 467 26 26 0 12 #rect
Ts0 f29 @|UdProcessEndIcon #fIcon
Ts0 f31 actionTable 'out=in;
' #txt
Ts0 f31 actionCode 'import ch.ivy.addon.portalkit.enums.FilterType;
in.filterType = FilterType.ONLY_ME;
in.filterSetName = "";' #txt
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
Ts0 f27 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>load filter set</name>
        <nameStyle>15,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ts0 f27 366 52 36 24 20 -2 #rect
Ts0 f27 @|StepIcon #fIcon
Ts0 f3 467 51 26 26 0 12 #rect
Ts0 f3 @|UdProcessEndIcon #fIcon
Ts0 f34 guid 15F5C0F9B77406B4 #txt
Ts0 f34 method deleteFilter() #txt
Ts0 f34 inParameterDecl '<> param;' #txt
Ts0 f34 outParameterDecl '<> result;' #txt
Ts0 f34 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>deleteFilter()</name>
    </language>
</elementInfo>
' #txt
Ts0 f34 67 563 26 26 -34 15 #rect
Ts0 f34 @|UdMethodIcon #fIcon
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
Ts0 f36 387 563 26 26 0 12 #rect
Ts0 f36 @|UdProcessEndIcon #fIcon
Ts0 f39 expr out #txt
Ts0 f39 93 576 176 576 #arcP
Ts0 f40 expr out #txt
Ts0 f40 288 576 387 576 #arcP
Ts0 f41 guid 15F5C9F2AA5C9BA1 #txt
Ts0 f41 method setFilterToBeDeleted(ch.ivy.addon.portalkit.taskfilter.TaskAnalysisFilterData) #txt
Ts0 f41 inParameterDecl '<ch.ivy.addon.portalkit.taskfilter.TaskAnalysisFilterData taskFilterData> param;' #txt
Ts0 f41 inParameterMapAction 'out.taskFilterDataToBeRemoved=param.taskFilterData;
' #txt
Ts0 f41 outParameterDecl '<> result;' #txt
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
Ts0 f41 @|UdMethodIcon #fIcon
Ts0 f42 387 659 26 26 0 12 #rect
Ts0 f42 @|UdProcessEndIcon #fIcon
Ts0 f43 93 672 387 672 #arcP
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
Ts0 f50 579 323 26 26 0 12 #rect
Ts0 f50 @|UdProcessEndIcon #fIcon
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
Ts0 f16 method applyFilter(ch.ivy.addon.portalkit.taskfilter.TaskAnalysisFilterData) #txt
Ts0 f16 inParameterDecl '<ch.ivy.addon.portalkit.taskfilter.TaskAnalysisFilterData taskFilterData> param;' #txt
Ts0 f16 inActionCode out.dataModel.applyFilter(param.taskFilterData); #txt
Ts0 f16 outParameterDecl '<> result;' #txt
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
Ts0 f16 @|UdMethodIcon #fIcon
Ts0 f17 387 755 26 26 0 12 #rect
Ts0 f17 @|UdProcessEndIcon #fIcon
Ts0 f20 93 768 387 768 #arcP
Ts0 f57 expr out #txt
Ts0 f57 402 64 467 64 #arcP
Ts0 f8 actionTable 'out=in;
' #txt
Ts0 f8 actionCode 'import ch.ivy.addon.portalkit.datamodel.TaskAnalysisLazyDataModel;
import ch.ivy.addon.portalkit.bean.TaskAnalysisWidgetBean;
import javax.faces.context.FacesContext;

FacesContext context = FacesContext.getCurrentInstance();
TaskAnalysisWidgetBean taskAnalysisWidgetBean = context.getApplication().evaluateExpressionGet(context, "#{taskAnalysisWidgetBean}", TaskAnalysisWidgetBean.class) as TaskAnalysisWidgetBean;
if (!in.#dataModel is initialized) {
	TaskAnalysisLazyDataModel dataModel = new TaskAnalysisLazyDataModel();
	taskAnalysisWidgetBean.setDataModel(dataModel);
	in.dataModel = taskAnalysisWidgetBean.dataModel;
} else {
	taskAnalysisWidgetBean.setDataModel(in.dataModel);
}' #txt
Ts0 f8 security system #txt
Ts0 f8 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>init data model</name>
    </language>
</elementInfo>
' #txt
Ts0 f8 184 42 112 44 -40 -8 #rect
Ts0 f8 @|StepIcon #fIcon
Ts0 f10 expr out #txt
Ts0 f10 107 64 184 64 #arcP
Ts0 f1 expr out #txt
Ts0 f1 296 64 366 64 #arcP
Ts0 f11 guid 1645924828285975 #txt
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
Ts0 f11 @|UdEventIcon #fIcon
Ts0 f4 387 867 26 26 0 12 #rect
Ts0 f4 @|UdProcessEndIcon #fIcon
Ts0 f5 actionTable 'out=in;
' #txt
Ts0 f5 actionCode 'import javax.faces.context.FacesContext;

String statisticPageUrl = ivy.html.startref("Start Processes/PortalStart/startPortalStatistic.ivp");
FacesContext.getCurrentInstance().getExternalContext().redirect(statisticPageUrl);' #txt
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
Ts0 f13 method getExportedFile(java.util.Map) #txt
Ts0 f13 inParameterDecl '<java.util.Map columnsVisibility> param;' #txt
Ts0 f13 inParameterMapAction 'out.columnsVisibility=param.columnsVisibility;
' #txt
Ts0 f13 outParameterDecl '<org.primefaces.model.StreamedContent exportedFile> result;' #txt
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
Ts0 f13 @|UdMethodIcon #fIcon
Ts0 f14 483 979 26 26 0 12 #rect
Ts0 f14 @|UdProcessEndIcon #fIcon
Ts0 f37 actionTable 'out=in;
' #txt
Ts0 f37 actionCode 'import ch.ivy.addon.portalkit.util.TaskAnalysisExporter;
TaskAnalysisExporter exporter = new TaskAnalysisExporter(in.columnsVisibility);
in.exportedFile = exporter.getStreamedContent(in.collectedTasksForExporting);' #txt
Ts0 f37 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>export excel</name>
    </language>
</elementInfo>
' #txt
Ts0 f37 328 970 112 44 -32 -8 #rect
Ts0 f37 @|StepIcon #fIcon
Ts0 f56 expr out #txt
Ts0 f56 440 992 483 992 #arcP
Ts0 f56 0 0.9184538480715879 0 0 #arcLabel
Ts0 f15 processCall 'Ivy Data Processes/TaskService:findTasksByCriteria(ch.ivy.addon.portalkit.ivydata.searchcriteria.TaskSearchCriteria,Integer,Integer)' #txt
Ts0 f15 requestActionDecl '<ch.ivy.addon.portalkit.ivydata.searchcriteria.TaskSearchCriteria taskSearchCriteria,Integer startIndex,Integer count> param;' #txt
Ts0 f15 requestMappingAction 'param.taskSearchCriteria=in.taskSearchCriteria;
param.startIndex=0;
param.count=-1;
' #txt
Ts0 f15 responseActionDecl 'ch.ivy.addon.portalkit.component.TaskAnalysisWidget.TaskAnalysisWidgetData out;
' #txt
Ts0 f15 responseMappingAction 'out=in;
out.collectedTasksForExporting=result.tasks;
out.errors=result.errors;
' #txt
Ts0 f15 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>TaskService</name>
    </language>
</elementInfo>
' #txt
Ts0 f15 168 970 112 44 -33 -8 #rect
Ts0 f15 @|CallSubIcon #fIcon
Ts0 f18 expr out #txt
Ts0 f18 93 992 168 992 #arcP
Ts0 f21 expr out #txt
Ts0 f21 280 992 328 992 #arcP
Ts0 f9 processCall 'Ivy Data Processes/TaskService:findTasksByCriteria(ch.ivy.addon.portalkit.ivydata.searchcriteria.TaskSearchCriteria,Integer,Integer)' #txt
Ts0 f9 requestActionDecl '<ch.ivy.addon.portalkit.ivydata.searchcriteria.TaskSearchCriteria taskSearchCriteria,Integer startIndex,Integer count> param;' #txt
Ts0 f9 requestMappingAction 'param.taskSearchCriteria=in.taskSearchCriteria;
param.startIndex=in.startIndex;
param.count=in.count;
' #txt
Ts0 f9 responseActionDecl 'ch.ivy.addon.portalkit.component.TaskAnalysisWidget.TaskAnalysisWidgetData out;
' #txt
Ts0 f9 responseMappingAction 'out=in;
out.errors=result.errors;
out.tasks=result.tasks;
' #txt
Ts0 f9 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>TaskService</name>
    </language>
</elementInfo>
' #txt
Ts0 f9 216 138 112 44 -33 -8 #rect
Ts0 f9 @|CallSubIcon #fIcon
Ts0 f19 expr out #txt
Ts0 f19 107 160 216 160 #arcP
Ts0 f22 403 147 26 26 0 12 #rect
Ts0 f22 @|UdProcessEndIcon #fIcon
Ts0 f33 expr out #txt
Ts0 f33 328 160 403 160 #arcP
Ts0 f2 guid 1682C611F21483D7 #txt
Ts0 f2 method countTasks(ch.ivy.addon.portalkit.ivydata.searchcriteria.TaskSearchCriteria) #txt
Ts0 f2 inParameterDecl '<ch.ivy.addon.portalkit.ivydata.searchcriteria.TaskSearchCriteria criteria> param;' #txt
Ts0 f2 inParameterMapAction 'out.taskSearchCriteria=param.criteria;
' #txt
Ts0 f2 outParameterDecl '<Long totalTasks> result;' #txt
Ts0 f2 outParameterMapAction 'result.totalTasks=in.totalTasks;
' #txt
Ts0 f2 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>countTasks(TaskSearchCriteria)</name>
    </language>
</elementInfo>
' #txt
Ts0 f2 83 243 26 26 -89 15 #rect
Ts0 f2 @|UdMethodIcon #fIcon
Ts0 f7 processCall 'Ivy Data Processes/TaskService:countTasksByCriteria(ch.ivy.addon.portalkit.ivydata.searchcriteria.TaskSearchCriteria)' #txt
Ts0 f7 requestActionDecl '<ch.ivy.addon.portalkit.ivydata.searchcriteria.TaskSearchCriteria taskSearchCriteria> param;' #txt
Ts0 f7 requestMappingAction 'param.taskSearchCriteria=in.taskSearchCriteria;
' #txt
Ts0 f7 responseActionDecl 'ch.ivy.addon.portalkit.component.TaskAnalysisWidget.TaskAnalysisWidgetData out;
' #txt
Ts0 f7 responseMappingAction 'out=in;
out.errors=result.errors;
out.totalTasks=result.totalTasks;
' #txt
Ts0 f7 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>TaskService</name>
    </language>
</elementInfo>
' #txt
Ts0 f7 216 234 112 44 -33 -8 #rect
Ts0 f7 @|CallSubIcon #fIcon
Ts0 f38 403 243 26 26 0 12 #rect
Ts0 f38 @|UdProcessEndIcon #fIcon
Ts0 f44 expr out #txt
Ts0 f44 109 256 216 256 #arcP
Ts0 f49 expr out #txt
Ts0 f49 328 256 403 256 #arcP
>Proto Ts0 .type ch.ivy.addon.portalkit.component.TaskAnalysisWidget.TaskAnalysisWidgetData #txt
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
Ts0 f27 mainOut f57 tail #connect
Ts0 f57 head f3 mainIn #connect
Ts0 f0 mainOut f10 tail #connect
Ts0 f10 head f8 mainIn #connect
Ts0 f8 mainOut f1 tail #connect
Ts0 f1 head f27 mainIn #connect
Ts0 f11 mainOut f6 tail #connect
Ts0 f6 head f5 mainIn #connect
Ts0 f5 mainOut f12 tail #connect
Ts0 f12 head f4 mainIn #connect
Ts0 f37 mainOut f56 tail #connect
Ts0 f56 head f14 mainIn #connect
Ts0 f13 mainOut f18 tail #connect
Ts0 f18 head f15 mainIn #connect
Ts0 f15 mainOut f21 tail #connect
Ts0 f21 head f37 mainIn #connect
Ts0 f94 mainOut f19 tail #connect
Ts0 f19 head f9 mainIn #connect
Ts0 f9 mainOut f33 tail #connect
Ts0 f33 head f22 mainIn #connect
Ts0 f2 mainOut f44 tail #connect
Ts0 f44 head f7 mainIn #connect
Ts0 f7 mainOut f49 tail #connect
Ts0 f49 head f38 mainIn #connect
