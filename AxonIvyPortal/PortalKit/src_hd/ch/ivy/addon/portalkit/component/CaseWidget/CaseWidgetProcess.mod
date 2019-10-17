[Ivy]
152E8EDB33C1BDC1 7.5.0 #module
>Proto >Proto Collection #zClass
Cs0 CaseWidgetProcess Big #zClass
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
Cs0 @UdMethod f6 '' #zField
Cs0 @CallSub f7 '' #zField
Cs0 @PushWFArc f8 '' #zField
Cs0 @UdProcessEnd f9 '' #zField
Cs0 @PushWFArc f10 '' #zField
Cs0 @UdMethod f15 '' #zField
Cs0 @CallSub f18 '' #zField
Cs0 @PushWFArc f19 '' #zField
Cs0 @UdProcessEnd f20 '' #zField
Cs0 @PushWFArc f21 '' #zField
Cs0 @GridStep f2 '' #zField
Cs0 @PushWFArc f3 '' #zField
Cs0 @PushWFArc f4 '' #zField
Cs0 @UdEvent f5 '' #zField
Cs0 @Alternative f47 '' #zField
Cs0 @UdProcessEnd f29 '' #zField
Cs0 @GridStep f45 '' #zField
Cs0 @UdProcessEnd f50 '' #zField
Cs0 @GridStep f31 '' #zField
Cs0 @GridStep f24 '' #zField
Cs0 @PushWFArc f25 '' #zField
Cs0 @PushWFArc f51 '' #zField
Cs0 @PushWFArc f26 '' #zField
Cs0 @PushWFArc f48 '' #zField
Cs0 @PushWFArc f30 '' #zField
Cs0 @PushWFArc f11 '' #zField
Cs0 @UdEvent f28 '' #zField
Cs0 @PushWFArc f12 '' #zField
Cs0 @GridStep f35 '' #zField
Cs0 @UdProcessEnd f36 '' #zField
Cs0 @PushWFArc f40 '' #zField
Cs0 @UdEvent f13 '' #zField
Cs0 @PushWFArc f14 '' #zField
Cs0 @UdMethod f16 '' #zField
Cs0 @UdProcessEnd f17 '' #zField
Cs0 @PushWFArc f22 '' #zField
Cs0 @UdMethod f23 '' #zField
Cs0 @UdProcessEnd f27 '' #zField
Cs0 @PushWFArc f32 '' #zField
>Proto Cs0 Cs0 CaseWidgetProcess #zField
Cs0 f0 guid 152E8EDB3E3A6957 #txt
Cs0 f0 method start(ch.ivy.addon.portalkit.datamodel.CaseLazyDataModel,java.lang.Long) #txt
Cs0 f0 inParameterDecl '<ch.ivy.addon.portalkit.datamodel.CaseLazyDataModel dataModel,Long filterGroupId> param;' #txt
Cs0 f0 inParameterMapAction 'out.dataModel=param.dataModel;
out.filterGroupId=param.filterGroupId;
' #txt
Cs0 f0 outParameterDecl '<> result;' #txt
Cs0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start()</name>
        <nameStyle>7,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f0 53 85 22 22 14 0 #rect
Cs0 f0 @|UdInitIcon #fIcon
Cs0 f1 525 85 22 22 14 0 #rect
Cs0 f1 @|UdProcessEndIcon #fIcon
Cs0 f6 guid 167E4C8566B6B6EA #txt
Cs0 f6 method findCases(ch.ivy.addon.portalkit.ivydata.searchcriteria.CaseSearchCriteria,java.lang.Integer,java.lang.Integer) #txt
Cs0 f6 inParameterDecl '<ch.ivy.addon.portalkit.ivydata.searchcriteria.CaseSearchCriteria caseSearchCriteria,Integer startIndex,Integer count> param;' #txt
Cs0 f6 inParameterMapAction 'out.caseSearchCriteria=param.caseSearchCriteria;
out.count=param.count;
out.startIndex=param.startIndex;
' #txt
Cs0 f6 outParameterDecl '<java.util.List<ch.ivyteam.ivy.workflow.ICase> cases> result;' #txt
Cs0 f6 outParameterMapAction 'result.cases=in.cases;
' #txt
Cs0 f6 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>findCases(CaseSearchCriteria,Integer,Integer)</name>
    </language>
</elementInfo>
' #txt
Cs0 f6 51 179 26 26 -48 13 #rect
Cs0 f6 @|UdMethodIcon #fIcon
Cs0 f7 processCall 'Ivy Data Processes/CaseService:findCasesByCriteria(ch.ivy.addon.portalkit.ivydata.searchcriteria.CaseSearchCriteria,Integer,Integer)' #txt
Cs0 f7 requestActionDecl '<ch.ivy.addon.portalkit.ivydata.searchcriteria.CaseSearchCriteria caseSearchCriteria,Integer startIndex,Integer count> param;' #txt
Cs0 f7 requestMappingAction 'param.caseSearchCriteria=in.caseSearchCriteria;
param.startIndex=in.startIndex;
param.count=in.count;
' #txt
Cs0 f7 responseActionDecl 'ch.ivy.addon.portalkit.component.CaseWidget.CaseWidgetData out;
' #txt
Cs0 f7 responseMappingAction 'out=in;
out.cases=result.cases;
out.errors=result.errors;
' #txt
Cs0 f7 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>CaseService</name>
    </language>
</elementInfo>
' #txt
Cs0 f7 200 170 112 44 -35 -8 #rect
Cs0 f7 @|CallSubIcon #fIcon
Cs0 f8 expr out #txt
Cs0 f8 77 192 200 192 #arcP
Cs0 f9 435 179 26 26 0 12 #rect
Cs0 f9 @|UdProcessEndIcon #fIcon
Cs0 f10 expr out #txt
Cs0 f10 312 192 435 192 #arcP
Cs0 f15 guid 167E4DD6005674EC #txt
Cs0 f15 method countCases(ch.ivy.addon.portalkit.ivydata.searchcriteria.CaseSearchCriteria) #txt
Cs0 f15 inParameterDecl '<ch.ivy.addon.portalkit.ivydata.searchcriteria.CaseSearchCriteria caseSearchCriteria> param;' #txt
Cs0 f15 inParameterMapAction 'out.caseSearchCriteria=param.caseSearchCriteria;
' #txt
Cs0 f15 outParameterDecl '<Long totalCases> result;' #txt
Cs0 f15 outParameterMapAction 'result.totalCases=in.totalCases;
' #txt
Cs0 f15 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>countCases(CaseSearchCriteria)</name>
    </language>
</elementInfo>
' #txt
Cs0 f15 51 275 26 26 -45 14 #rect
Cs0 f15 @|UdMethodIcon #fIcon
Cs0 f18 processCall 'Ivy Data Processes/CaseService:countCasesByCriteria(ch.ivy.addon.portalkit.ivydata.searchcriteria.CaseSearchCriteria)' #txt
Cs0 f18 requestActionDecl '<ch.ivy.addon.portalkit.ivydata.searchcriteria.CaseSearchCriteria caseSearchCriteria> param;' #txt
Cs0 f18 requestMappingAction 'param.caseSearchCriteria=in.caseSearchCriteria;
' #txt
Cs0 f18 responseActionDecl 'ch.ivy.addon.portalkit.component.CaseWidget.CaseWidgetData out;
' #txt
Cs0 f18 responseMappingAction 'out=in;
out.errors=result.errors;
out.totalCases=result.totalCases;
' #txt
Cs0 f18 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>CaseService</name>
    </language>
</elementInfo>
' #txt
Cs0 f18 200 266 112 44 -35 -8 #rect
Cs0 f18 @|CallSubIcon #fIcon
Cs0 f19 expr out #txt
Cs0 f19 77 288 200 288 #arcP
Cs0 f20 435 275 26 26 0 12 #rect
Cs0 f20 @|UdProcessEndIcon #fIcon
Cs0 f21 expr out #txt
Cs0 f21 312 288 435 288 #arcP
Cs0 f2 actionTable 'out=in;
' #txt
Cs0 f2 actionCode 'import ch.ivy.addon.portalkit.service.CaseFilterService;
import ch.ivy.addon.portalkit.bean.PermissionBean;
import ch.ivy.addon.portalkit.enums.FilterType;
CaseFilterService filterService = new CaseFilterService();
in.privateFilters = filterService.getPrivateFilterForCurrentUser(in.filterGroupId) as List;
in.publicFilters = filterService.getPublicFilter(in.filterGroupId) as List;
in.filterType = FilterType.ONLY_ME;' #txt
Cs0 f2 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Load filter set</name>
    </language>
</elementInfo>
' #txt
Cs0 f2 232 74 112 44 -37 -8 #rect
Cs0 f2 @|StepIcon #fIcon
Cs0 f3 expr out #txt
Cs0 f3 75 96 232 96 #arcP
Cs0 f4 expr out #txt
Cs0 f4 344 96 525 96 #arcP
Cs0 f5 guid 1680C6A623A9C767 #txt
Cs0 f5 actionTable 'out=in;
' #txt
Cs0 f5 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>saveFilter</name>
    </language>
</elementInfo>
' #txt
Cs0 f5 51 371 26 26 -30 17 #rect
Cs0 f5 @|UdEventIcon #fIcon
Cs0 f47 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>is filter
existed?</name>
        <nameStyle>18,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f47 288 368 32 32 -23 18 #rect
Cs0 f47 @|AlternativeIcon #fIcon
Cs0 f29 555 467 26 26 0 12 #rect
Cs0 f29 @|UdProcessEndIcon #fIcon
Cs0 f45 actionTable 'out=in;
' #txt
Cs0 f45 actionCode 'import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import ch.ivy.addon.portalkit.service.CaseFilterService;

CaseFilterService service = new CaseFilterService();
in.isFilterExisted = false;
if (service.isFilterExisted(in.filterSetName, in.filterType, in.filterGroupId)) {
	FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, ivy.cms.co("/ch.ivy.addon.portalkit.ui.jsf/components/taskView/filterExistedValidationError"), "");
	FacesContext.getCurrentInstance().addMessage("", message);
	FacesContext.getCurrentInstance().validationFailed();
	in.isFilterExisted = true;
}' #txt
Cs0 f45 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>validate</name>
        <nameStyle>8,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f45 120 362 112 44 -21 -8 #rect
Cs0 f45 @|StepIcon #fIcon
Cs0 f50 547 323 26 26 0 12 #rect
Cs0 f50 @|UdProcessEndIcon #fIcon
Cs0 f31 actionTable 'out=in;
' #txt
Cs0 f31 actionCode 'import ch.ivy.addon.portalkit.enums.FilterType;
in.filterType = FilterType.ONLY_ME;
in.filterSetName = "";' #txt
Cs0 f31 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Clear filter</name>
        <nameStyle>12,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f31 376 458 112 44 -28 -8 #rect
Cs0 f31 @|StepIcon #fIcon
Cs0 f24 actionTable 'out=in;
' #txt
Cs0 f24 actionCode 'import ch.ivy.addon.portalkit.service.CaseFilterService;
import ch.ivy.addon.portalkit.casefilter.CaseFilterData;
import ch.ivy.addon.portalkit.enums.FilterType;

CaseFilterService filterService = new CaseFilterService();
CaseFilterData filterData = in.dataModel.saveFilter(in.filterSetName, in.filterType, in.filterGroupId);
if (FilterType.ONLY_ME == filterData.type) {
	in.privateFilters.add(filterData);
	in.privateFilters = filterService.sortFilters(in.privateFilters) as List;
} else {
	in.publicFilters.add(filterData);
	in.publicFilters = filterService.sortFilters(in.publicFilters) as List;
}
in.dataModel.selectedFilterData = filterData;' #txt
Cs0 f24 security system #txt
Cs0 f24 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>save filter set</name>
        <nameStyle>15,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f24 376 362 112 44 -36 -8 #rect
Cs0 f24 @|StepIcon #fIcon
Cs0 f25 expr in #txt
Cs0 f25 outCond 'in.isFilterExisted == false' #txt
Cs0 f25 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>no</name>
        <nameStyle>2,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f25 320 384 376 384 #arcP
Cs0 f51 expr in #txt
Cs0 f51 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>yes</name>
        <nameStyle>3,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f51 304 368 547 336 #arcP
Cs0 f51 1 304 336 #addKink
Cs0 f51 1 0.3018284624086855 0 0 #arcLabel
Cs0 f26 expr out #txt
Cs0 f26 432 406 432 458 #arcP
Cs0 f48 expr out #txt
Cs0 f48 232 384 288 384 #arcP
Cs0 f30 expr out #txt
Cs0 f30 488 480 555 480 #arcP
Cs0 f11 expr out #txt
Cs0 f11 77 384 120 384 #arcP
Cs0 f28 guid 1680C745298BE4BA #txt
Cs0 f28 actionTable 'out=in;
' #txt
Cs0 f28 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>clearSaveFilterDialog</name>
        <nameStyle>21,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f28 51 467 26 26 -58 15 #rect
Cs0 f28 @|UdEventIcon #fIcon
Cs0 f12 expr out #txt
Cs0 f12 77 480 376 480 #arcP
Cs0 f35 actionTable 'out=in;
' #txt
Cs0 f35 actionCode 'import ch.ivy.addon.portalkit.enums.FilterType;
import ch.ivy.addon.portalkit.casefilter.CaseFilterData;
import ch.ivy.addon.portalkit.service.CaseFilterService;

CaseFilterService service = new CaseFilterService();
service.delete(in.filterDataToBeDeleted.getId());

if(in.filterDataToBeDeleted.type == FilterType.ONLY_ME) {
	in.privateFilters.remove(in.filterDataToBeDeleted);
} else {
	in.publicFilters.remove(in.filterDataToBeDeleted);
}

if (in.dataModel.#selectedFilterData is initialized && in.dataModel.selectedFilterData.equals(in.filterDataToBeDeleted)) {
	in.dataModel.resetFilters();
	in.filteringKeyword = null;
}' #txt
Cs0 f35 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>remove filter</name>
        <nameStyle>13,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f35 160 554 112 44 -33 -8 #rect
Cs0 f35 @|StepIcon #fIcon
Cs0 f36 339 563 26 26 0 12 #rect
Cs0 f36 @|UdProcessEndIcon #fIcon
Cs0 f40 expr out #txt
Cs0 f40 272 576 339 576 #arcP
Cs0 f13 guid 1680C75019258C12 #txt
Cs0 f13 actionTable 'out=in;
' #txt
Cs0 f13 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>deleteFilter</name>
    </language>
</elementInfo>
' #txt
Cs0 f13 51 563 26 26 -21 16 #rect
Cs0 f13 @|UdEventIcon #fIcon
Cs0 f14 expr out #txt
Cs0 f14 77 576 160 576 #arcP
Cs0 f16 guid 1680C78D11AA28AB #txt
Cs0 f16 method applyFilter(ch.ivy.addon.portalkit.casefilter.CaseFilterData) #txt
Cs0 f16 inParameterDecl '<ch.ivy.addon.portalkit.casefilter.CaseFilterData filterData> param;' #txt
Cs0 f16 inParameterMapAction 'out.filteringKeyword=param.filterData.keyword;
' #txt
Cs0 f16 inActionCode out.dataModel.applyFilter(param.filterData); #txt
Cs0 f16 outParameterDecl '<> result;' #txt
Cs0 f16 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>applyFilter(CaseFilterData)</name>
    </language>
</elementInfo>
' #txt
Cs0 f16 51 659 26 26 -49 16 #rect
Cs0 f16 @|UdMethodIcon #fIcon
Cs0 f17 339 659 26 26 0 12 #rect
Cs0 f17 @|UdProcessEndIcon #fIcon
Cs0 f22 expr out #txt
Cs0 f22 77 672 339 672 #arcP
Cs0 f23 guid 168A2BE8E0F76EC9 #txt
Cs0 f23 method setFilterToBeDeleted(ch.ivy.addon.portalkit.casefilter.CaseFilterData) #txt
Cs0 f23 inParameterDecl '<ch.ivy.addon.portalkit.casefilter.CaseFilterData caseFilterData> param;' #txt
Cs0 f23 inParameterMapAction 'out.filterDataToBeDeleted=param.caseFilterData;
' #txt
Cs0 f23 outParameterDecl '<> result;' #txt
Cs0 f23 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>setFilterToBeDeleted(CaseFilterData)</name>
    </language>
</elementInfo>
' #txt
Cs0 f23 51 755 26 26 -58 17 #rect
Cs0 f23 @|UdMethodIcon #fIcon
Cs0 f27 339 755 26 26 0 12 #rect
Cs0 f27 @|UdProcessEndIcon #fIcon
Cs0 f32 expr out #txt
Cs0 f32 77 768 339 768 #arcP
>Proto Cs0 .type ch.ivy.addon.portalkit.component.CaseWidget.CaseWidgetData #txt
>Proto Cs0 .processKind HTML_DIALOG #txt
>Proto Cs0 -8 -8 16 16 16 26 #rect
>Proto Cs0 '' #fIcon
Cs0 f6 mainOut f8 tail #connect
Cs0 f8 head f7 mainIn #connect
Cs0 f7 mainOut f10 tail #connect
Cs0 f10 head f9 mainIn #connect
Cs0 f15 mainOut f19 tail #connect
Cs0 f19 head f18 mainIn #connect
Cs0 f18 mainOut f21 tail #connect
Cs0 f21 head f20 mainIn #connect
Cs0 f0 mainOut f3 tail #connect
Cs0 f3 head f2 mainIn #connect
Cs0 f2 mainOut f4 tail #connect
Cs0 f4 head f1 mainIn #connect
Cs0 f31 mainOut f30 tail #connect
Cs0 f30 head f29 mainIn #connect
Cs0 f24 mainOut f26 tail #connect
Cs0 f26 head f31 mainIn #connect
Cs0 f45 mainOut f48 tail #connect
Cs0 f48 head f47 in #connect
Cs0 f47 out f25 tail #connect
Cs0 f25 head f24 mainIn #connect
Cs0 f47 out f51 tail #connect
Cs0 f51 head f50 mainIn #connect
Cs0 f5 mainOut f11 tail #connect
Cs0 f11 head f45 mainIn #connect
Cs0 f28 mainOut f12 tail #connect
Cs0 f12 head f31 mainIn #connect
Cs0 f35 mainOut f40 tail #connect
Cs0 f40 head f36 mainIn #connect
Cs0 f13 mainOut f14 tail #connect
Cs0 f14 head f35 mainIn #connect
Cs0 f16 mainOut f22 tail #connect
Cs0 f22 head f17 mainIn #connect
Cs0 f23 mainOut f32 tail #connect
Cs0 f32 head f27 mainIn #connect
