[Ivy]
152E8EDB33C1BDC1 3.20 #module
>Proto >Proto Collection #zClass
Cs0 CaseWidgetProcess Big #zClass
Cs0 RD #cInfo
Cs0 #process
Cs0 @TextInP .ui2RdDataAction .ui2RdDataAction #zField
Cs0 @TextInP .rdData2UIAction .rdData2UIAction #zField
Cs0 @TextInP .resExport .resExport #zField
Cs0 @TextInP .type .type #zField
Cs0 @TextInP .processKind .processKind #zField
Cs0 @AnnotationInP-0n ai ai #zField
Cs0 @MessageFlowInP-0n messageIn messageIn #zField
Cs0 @MessageFlowOutP-0n messageOut messageOut #zField
Cs0 @TextInP .xml .xml #zField
Cs0 @TextInP .responsibility .responsibility #zField
Cs0 @RichDialogInitStart f0 '' #zField
Cs0 @RichDialogProcessEnd f1 '' #zField
Cs0 @RichDialogMethodStart f11 '' #zField
Cs0 @RichDialogProcessEnd f12 '' #zField
Cs0 @GridStep f3 '' #zField
Cs0 @PushWFArc f4 '' #zField
Cs0 @PushWFArc f5 '' #zField
Cs0 @RichDialogProcessEnd f23 '' #zField
Cs0 @RichDialogMethodStart f14 '' #zField
Cs0 @CallSub f25 '' #zField
Cs0 @PushWFArc f24 '' #zField
Cs0 @RichDialogMethodStart f18 '' #zField
Cs0 @RichDialogMethodStart f19 '' #zField
Cs0 @CallSub f20 '' #zField
Cs0 @CallSub f21 '' #zField
Cs0 @PushWFArc f22 '' #zField
Cs0 @RichDialogProcessEnd f26 '' #zField
Cs0 @RichDialogProcessEnd f27 '' #zField
Cs0 @PushWFArc f29 '' #zField
Cs0 @PushWFArc f30 '' #zField
Cs0 @GridStep f31 '' #zField
Cs0 @PushWFArc f17 '' #zField
Cs0 @CallSub f13 '' #zField
Cs0 @PushWFArc f16 '' #zField
Cs0 @Alternative f32 '' #zField
Cs0 @PushWFArc f33 '' #zField
Cs0 @PushWFArc f34 '' #zField
Cs0 @PushWFArc f37 '' #zField
Cs0 @RichDialogMethodStart f15 '' #zField
Cs0 @GridStep f35 '' #zField
Cs0 @RichDialogProcessEnd f36 '' #zField
Cs0 @PushWFArc f38 '' #zField
Cs0 @PushWFArc f39 '' #zField
Cs0 @PushWFArc f28 '' #zField
Cs0 @RichDialogProcessStart f46 '' #zField
Cs0 @GridStep f47 '' #zField
Cs0 @RichDialogProcessEnd f50 '' #zField
Cs0 @RichDialogProcessStart f48 '' #zField
Cs0 @RichDialogProcessEnd f49 '' #zField
Cs0 @GridStep f51 '' #zField
Cs0 @Alternative f52 '' #zField
Cs0 @GridStep f53 '' #zField
Cs0 @PushWFArc f54 '' #zField
Cs0 @PushWFArc f55 '' #zField
Cs0 @PushWFArc f56 '' #zField
Cs0 @PushWFArc f57 '' #zField
Cs0 @PushWFArc f58 '' #zField
Cs0 @PushWFArc f59 '' #zField
Cs0 @PushWFArc f60 '' #zField
Cs0 @RichDialogMethodStart f43 '' #zField
Cs0 @PushWFArc f44 '' #zField
Cs0 @RichDialogProcessEnd f41 '' #zField
Cs0 @RichDialogProcessEnd f42 '' #zField
Cs0 @RichDialogMethodStart f40 '' #zField
Cs0 @PushWFArc f45 '' #zField
Cs0 @GridStep f61 '' #zField
Cs0 @PushWFArc f62 '' #zField
Cs0 @PushWFArc f2 '' #zField
Cs0 @RichDialogProcessEnd f66 '' #zField
Cs0 @RichDialogMethodStart f67 '' #zField
Cs0 @GridStep f68 '' #zField
Cs0 @PushWFArc f69 '' #zField
Cs0 @PushWFArc f70 '' #zField
>Proto Cs0 Cs0 CaseWidgetProcess #zField
Cs0 f0 guid 152E8EDB3E3A6957 #txt
Cs0 f0 type ch.ivy.addon.portalkit.component.CaseWidget.CaseWidgetData #txt
Cs0 f0 method start(ch.ivy.addon.portalkit.datamodel.CaseLazyDataModel,java.lang.Long) #txt
Cs0 f0 disableUIEvents true #txt
Cs0 f0 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<ch.ivy.addon.portalkit.datamodel.CaseLazyDataModel dataModel,java.lang.Long filterGroupId> param = methodEvent.getInputArguments();
' #txt
Cs0 f0 inParameterMapAction 'out.dataModel=param.dataModel;
out.filterGroupId=param.filterGroupId;
' #txt
Cs0 f0 outParameterDecl '<> result;
' #txt
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
Cs0 f0 @|RichDialogInitStartIcon #fIcon
Cs0 f1 type ch.ivy.addon.portalkit.component.CaseWidget.CaseWidgetData #txt
Cs0 f1 525 85 22 22 14 0 #rect
Cs0 f1 @|RichDialogProcessEndIcon #fIcon
Cs0 f11 guid 1530D5DA30DFC025 #txt
Cs0 f11 type ch.ivy.addon.portalkit.component.CaseWidget.CaseWidgetData #txt
Cs0 f11 method setCases(java.util.List<ch.ivy.addon.portalkit.bo.RemoteCase>) #txt
Cs0 f11 disableUIEvents false #txt
Cs0 f11 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<java.util.List<ch.ivy.addon.portalkit.bo.RemoteCase> cases> param = methodEvent.getInputArguments();
' #txt
Cs0 f11 inParameterMapAction 'out.cases=param.cases;
' #txt
Cs0 f11 outParameterDecl '<> result;
' #txt
Cs0 f11 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>setCases(List&lt;RemoteCase&gt;)</name>
        <nameStyle>26,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f11 53 245 22 22 14 0 #rect
Cs0 f11 @|RichDialogMethodStartIcon #fIcon
Cs0 f12 type ch.ivy.addon.portalkit.component.CaseWidget.CaseWidgetData #txt
Cs0 f12 525 245 22 22 14 0 #rect
Cs0 f12 @|RichDialogProcessEndIcon #fIcon
Cs0 f3 actionDecl 'ch.ivy.addon.portalkit.component.CaseWidget.CaseWidgetData out;
' #txt
Cs0 f3 actionTable 'out=in;
' #txt
Cs0 f3 actionCode 'import ch.ivy.addon.portalkit.service.FilterService;

if ("".equals(in.filteringKeyword)) {
	in.filteredCases = new List();
	in.filteredCases.addAll(in.cases);
} else {
	FilterService service = new FilterService(in.filteringKeyword);
	in.filteredCases = service.filterCases(in.cases);
}
' #txt
Cs0 f3 type ch.ivy.addon.portalkit.component.CaseWidget.CaseWidgetData #txt
Cs0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>filter cases 
based on keyword (if any)</name>
        <nameStyle>39,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f3 270 244 36 24 19 15 #rect
Cs0 f3 @|StepIcon #fIcon
Cs0 f4 expr out #txt
Cs0 f4 75 256 270 256 #arcP
Cs0 f5 expr out #txt
Cs0 f5 306 256 525 256 #arcP
Cs0 f23 type ch.ivy.addon.portalkit.component.CaseWidget.CaseWidgetData #txt
Cs0 f23 629 501 22 22 14 0 #rect
Cs0 f23 @|RichDialogProcessEndIcon #fIcon
Cs0 f14 guid 15432E26FC2E6FAC #txt
Cs0 f14 type ch.ivy.addon.portalkit.component.CaseWidget.CaseWidgetData #txt
Cs0 f14 method destroyCase(ch.ivy.addon.portalkit.bo.RemoteCase) #txt
Cs0 f14 disableUIEvents false #txt
Cs0 f14 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<ch.ivy.addon.portalkit.bo.RemoteCase case> param = methodEvent.getInputArguments();
' #txt
Cs0 f14 inParameterMapAction 'out.selectedCase=param.case;
' #txt
Cs0 f14 outParameterDecl '<> result;
' #txt
Cs0 f14 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>destroyCase()</name>
        <nameStyle>13,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f14 53 501 22 22 14 0 #rect
Cs0 f14 @|RichDialogMethodStartIcon #fIcon
Cs0 f25 type ch.ivy.addon.portalkit.component.CaseWidget.CaseWidgetData #txt
Cs0 f25 processCall MultiPortal/CaseService:destroyCase(ch.ivy.addon.portalkit.bo.RemoteCase) #txt
Cs0 f25 doCall true #txt
Cs0 f25 requestActionDecl '<ch.ivy.addon.portalkit.bo.RemoteCase remoteCase> param;
' #txt
Cs0 f25 requestMappingAction 'param.remoteCase=in.selectedCase;
' #txt
Cs0 f25 responseActionDecl 'ch.ivy.addon.portalkit.component.CaseWidget.CaseWidgetData out;
' #txt
Cs0 f25 responseMappingAction 'out=in;
out.errors=result.errors;
' #txt
Cs0 f25 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>CaseService</name>
        <nameStyle>11,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f25 182 500 36 24 -34 -30 #rect
Cs0 f25 @|CallSubIcon #fIcon
Cs0 f24 expr out #txt
Cs0 f24 75 512 182 512 #arcP
Cs0 f18 guid 1545FDADD6AAA70C #txt
Cs0 f18 type ch.ivy.addon.portalkit.component.CaseWidget.CaseWidgetData #txt
Cs0 f18 method findCases(java.lang.Integer,java.lang.Integer,ch.ivy.ws.addon.CaseSearchCriteria,java.lang.Long) #txt
Cs0 f18 disableUIEvents false #txt
Cs0 f18 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<java.lang.Integer startIndex,java.lang.Integer count,ch.ivy.ws.addon.CaseSearchCriteria caseSearchCriteria,java.lang.Long serverId> param = methodEvent.getInputArguments();
' #txt
Cs0 f18 inParameterMapAction 'out.caseSearchCriteria=param.caseSearchCriteria;
out.count=param.count;
out.serverId=param.serverId;
out.startIndex=param.startIndex;
' #txt
Cs0 f18 outParameterDecl '<List<ch.ivy.addon.portalkit.bo.RemoteCase> cases> result;
' #txt
Cs0 f18 outParameterMapAction 'result.cases=in.cases;
' #txt
Cs0 f18 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>findCases(Integer,Integer,CaseSearchCriteria)</name>
        <nameStyle>45,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f18 53 597 22 22 14 0 #rect
Cs0 f18 @|RichDialogMethodStartIcon #fIcon
Cs0 f19 guid 1545FDAE8024C8DD #txt
Cs0 f19 type ch.ivy.addon.portalkit.component.CaseWidget.CaseWidgetData #txt
Cs0 f19 method countCases(ch.ivy.ws.addon.CaseSearchCriteria,java.lang.Long) #txt
Cs0 f19 disableUIEvents false #txt
Cs0 f19 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<ch.ivy.ws.addon.CaseSearchCriteria caseSearchCriteria,java.lang.Long serverId> param = methodEvent.getInputArguments();
' #txt
Cs0 f19 inParameterMapAction 'out.caseSearchCriteria=param.caseSearchCriteria;
out.serverId=param.serverId;
' #txt
Cs0 f19 outParameterDecl '<java.lang.Long caseCount> result;
' #txt
Cs0 f19 outParameterMapAction 'result.caseCount=in.caseCount;
' #txt
Cs0 f19 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>countCases(CaseSearchCriteria)</name>
        <nameStyle>30,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f19 53 693 22 22 14 0 #rect
Cs0 f19 @|RichDialogMethodStartIcon #fIcon
Cs0 f20 type ch.ivy.addon.portalkit.component.CaseWidget.CaseWidgetData #txt
Cs0 f20 processCall MultiPortal/CaseService:findCasesByCriteria(Long,Integer,Integer,ch.ivy.ws.addon.CaseSearchCriteria) #txt
Cs0 f20 doCall true #txt
Cs0 f20 requestActionDecl '<java.lang.Long serverId,java.lang.Integer startIndex,java.lang.Integer count,ch.ivy.ws.addon.CaseSearchCriteria caseSearchCriteria> param;
' #txt
Cs0 f20 requestMappingAction 'param.serverId=in.#serverId;
param.startIndex=in.startIndex;
param.count=in.count;
param.caseSearchCriteria=in.caseSearchCriteria;
' #txt
Cs0 f20 responseActionDecl 'ch.ivy.addon.portalkit.component.CaseWidget.CaseWidgetData out;
' #txt
Cs0 f20 responseMappingAction 'out=in;
out.cases=result.cases;
out.errors=result.errors;
' #txt
Cs0 f20 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>CaseService</name>
        <nameStyle>11,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f20 334 596 36 24 20 -2 #rect
Cs0 f20 @|CallSubIcon #fIcon
Cs0 f21 type ch.ivy.addon.portalkit.component.CaseWidget.CaseWidgetData #txt
Cs0 f21 processCall MultiPortal/CaseService:countCasesByCriteria(Long,ch.ivy.ws.addon.CaseSearchCriteria) #txt
Cs0 f21 doCall true #txt
Cs0 f21 requestActionDecl '<java.lang.Long serverId,ch.ivy.ws.addon.CaseSearchCriteria caseSearchCriteria> param;
' #txt
Cs0 f21 requestMappingAction 'param.serverId=in.#serverId;
param.caseSearchCriteria=in.caseSearchCriteria;
' #txt
Cs0 f21 responseActionDecl 'ch.ivy.addon.portalkit.component.CaseWidget.CaseWidgetData out;
' #txt
Cs0 f21 responseMappingAction 'out=in;
out.caseCount=result.caseCount;
out.errors=result.errors;
' #txt
Cs0 f21 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>CaseService</name>
        <nameStyle>11,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f21 334 692 36 24 20 -2 #rect
Cs0 f21 @|CallSubIcon #fIcon
Cs0 f22 expr out #txt
Cs0 f22 75 608 334 608 #arcP
Cs0 f26 type ch.ivy.addon.portalkit.component.CaseWidget.CaseWidgetData #txt
Cs0 f26 469 597 22 22 14 0 #rect
Cs0 f26 @|RichDialogProcessEndIcon #fIcon
Cs0 f27 type ch.ivy.addon.portalkit.component.CaseWidget.CaseWidgetData #txt
Cs0 f27 469 693 22 22 14 0 #rect
Cs0 f27 @|RichDialogProcessEndIcon #fIcon
Cs0 f29 expr out #txt
Cs0 f29 75 704 334 704 #arcP
Cs0 f30 expr out #txt
Cs0 f30 370 704 469 704 #arcP
Cs0 f31 actionDecl 'ch.ivy.addon.portalkit.component.CaseWidget.CaseWidgetData out;
' #txt
Cs0 f31 actionTable 'out=in;
' #txt
Cs0 f31 actionCode 'if (in.errors.isEmpty()) {
	in.dataModel.rowCount--;
}' #txt
Cs0 f31 type ch.ivy.addon.portalkit.component.CaseWidget.CaseWidgetData #txt
Cs0 f31 518 500 36 24 20 -2 #rect
Cs0 f31 @|StepIcon #fIcon
Cs0 f17 expr out #txt
Cs0 f17 554 512 629 512 #arcP
Cs0 f13 type ch.ivy.addon.portalkit.component.CaseWidget.CaseWidgetData #txt
Cs0 f13 processCall MultiPortal/CaseService:createNote(ch.ivy.addon.portalkit.persistence.domain.Server,Long,String,String) #txt
Cs0 f13 doCall true #txt
Cs0 f13 requestActionDecl '<ch.ivy.addon.portalkit.persistence.domain.Server server,java.lang.Long caseId,java.lang.String username,java.lang.String content> param;
' #txt
Cs0 f13 requestMappingAction 'param.server=in.selectedCase.server;
param.caseId=in.selectedCase.id;
param.username=ivy.session.getSessionUserName();
param.content=ivy.cms.co("/ch.ivy.addon.portalkit.ui.jsf/caseList/deletionNoteContent",[ivy.session.getSessionUser().getDisplayName()]);
' #txt
Cs0 f13 responseActionDecl 'ch.ivy.addon.portalkit.component.CaseWidget.CaseWidgetData out;
' #txt
Cs0 f13 responseMappingAction 'out=in;
out.errors=result.errors;
' #txt
Cs0 f13 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Add deletion note</name>
        <nameStyle>17,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f13 382 500 36 24 -44 -32 #rect
Cs0 f13 @|CallSubIcon #fIcon
Cs0 f16 expr out #txt
Cs0 f16 418 512 518 512 #arcP
Cs0 f32 type ch.ivy.addon.portalkit.component.CaseWidget.CaseWidgetData #txt
Cs0 f32 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Any errors?</name>
        <nameStyle>11,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f32 274 498 28 28 -24 -32 #rect
Cs0 f32 @|AlternativeIcon #fIcon
Cs0 f33 expr out #txt
Cs0 f33 218 512 274 512 #arcP
Cs0 f34 expr in #txt
Cs0 f34 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>yes</name>
        <nameStyle>3,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f34 288 526 640 523 #arcP
Cs0 f34 1 288 560 #addKink
Cs0 f34 2 640 560 #addKink
Cs0 f34 1 0.5056818181818182 0 5 #arcLabel
Cs0 f37 expr in #txt
Cs0 f37 outCond in.errors.isEmpty() #txt
Cs0 f37 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>no</name>
        <nameStyle>2,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f37 302 512 382 512 #arcP
Cs0 f37 0 0.5 0 8 #arcLabel
Cs0 f15 guid 157B274E4D4ACF6E #txt
Cs0 f15 type ch.ivy.addon.portalkit.component.CaseWidget.CaseWidgetData #txt
Cs0 f15 method sort(String,Boolean) #txt
Cs0 f15 disableUIEvents false #txt
Cs0 f15 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<java.lang.String sortedField,java.lang.Boolean isSortingDescending> param = methodEvent.getInputArguments();
' #txt
Cs0 f15 inParameterMapAction 'out.isSortingDescending=param.isSortingDescending;
out.sortedField=param.sortedField;
' #txt
Cs0 f15 outParameterDecl '<> result;
' #txt
Cs0 f15 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>sort(CaseSortedField,Boolean)</name>
        <nameStyle>29,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f15 53 781 22 22 14 0 #rect
Cs0 f15 @|RichDialogMethodStartIcon #fIcon
Cs0 f35 actionDecl 'ch.ivy.addon.portalkit.component.CaseWidget.CaseWidgetData out;
' #txt
Cs0 f35 actionTable 'out=in;
' #txt
Cs0 f35 actionCode in.dataModel.setSorting(in.sortedField,in.isSortingDescending); #txt
Cs0 f35 type ch.ivy.addon.portalkit.component.CaseWidget.CaseWidgetData #txt
Cs0 f35 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>set sorting
to lazy model</name>
        <nameStyle>25,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f35 270 780 36 24 20 14 #rect
Cs0 f35 @|StepIcon #fIcon
Cs0 f36 type ch.ivy.addon.portalkit.component.CaseWidget.CaseWidgetData #txt
Cs0 f36 469 781 22 22 14 0 #rect
Cs0 f36 @|RichDialogProcessEndIcon #fIcon
Cs0 f38 expr out #txt
Cs0 f38 75 792 270 792 #arcP
Cs0 f39 expr out #txt
Cs0 f39 306 792 469 792 #arcP
Cs0 f28 expr out #txt
Cs0 f28 370 608 469 608 #arcP
Cs0 f46 guid 15FFBC1713CF0D50 #txt
Cs0 f46 type ch.ivy.addon.portalkit.component.CaseWidget.CaseWidgetData #txt
Cs0 f46 actionDecl 'ch.ivy.addon.portalkit.component.CaseWidget.CaseWidgetData out;
' #txt
Cs0 f46 actionTable 'out=in;
' #txt
Cs0 f46 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>saveFilter</name>
        <nameStyle>10,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f46 54 890 26 26 -26 15 #rect
Cs0 f46 @|RichDialogProcessStartIcon #fIcon
Cs0 f47 actionDecl 'ch.ivy.addon.portalkit.component.CaseWidget.CaseWidgetData out;
' #txt
Cs0 f47 actionTable 'out=in;
' #txt
Cs0 f47 actionCode 'import ch.ivy.addon.portalkit.service.CaseFilterService;
import ch.ivy.addon.portalkit.casefilter.CaseFilterData;
import ch.ivy.addon.portalkit.enums.FilterType;
CaseFilterService caseFilterService = new CaseFilterService();
CaseFilterData caseFilterData = in.dataModel.saveFilter(in.filterSetName, in.filterType, in.filterGroupId);
if(FilterType.ONLY_ME == caseFilterData.type) {
	in.privateFilters.add(caseFilterData);
	in.privateFilters = caseFilterService.sortFilters(in.privateFilters) as java.util.List<CaseFilterData>;
} else {
	in.publicFilters.add(caseFilterData);
	in.publicFilters = caseFilterService.sortFilters(in.publicFilters) as java.util.List<CaseFilterData>;
}
in.dataModel.selectedFilterData = caseFilterData;' #txt
Cs0 f47 type ch.ivy.addon.portalkit.component.CaseWidget.CaseWidgetData #txt
Cs0 f47 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>save filter set</name>
        <nameStyle>15,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f47 395 881 112 44 -36 -8 #rect
Cs0 f47 @|StepIcon #fIcon
Cs0 f50 type ch.ivy.addon.portalkit.component.CaseWidget.CaseWidgetData #txt
Cs0 f50 566 842 26 26 0 12 #rect
Cs0 f50 @|RichDialogProcessEndIcon #fIcon
Cs0 f48 guid 15FFBC1713E2CCD1 #txt
Cs0 f48 type ch.ivy.addon.portalkit.component.CaseWidget.CaseWidgetData #txt
Cs0 f48 actionDecl 'ch.ivy.addon.portalkit.component.CaseWidget.CaseWidgetData out;
' #txt
Cs0 f48 actionTable 'out=in;
' #txt
Cs0 f48 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>clearSaveFilterDialog</name>
        <nameStyle>21,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f48 54 986 26 26 -58 15 #rect
Cs0 f48 @|RichDialogProcessStartIcon #fIcon
Cs0 f49 type ch.ivy.addon.portalkit.component.CaseWidget.CaseWidgetData #txt
Cs0 f49 574 986 26 26 0 12 #rect
Cs0 f49 @|RichDialogProcessEndIcon #fIcon
Cs0 f51 actionDecl 'ch.ivy.addon.portalkit.component.CaseWidget.CaseWidgetData out;
' #txt
Cs0 f51 actionTable 'out=in;
' #txt
Cs0 f51 actionCode 'import ch.ivy.addon.portalkit.enums.FilterType;
in.filterType = FilterType.ONLY_ME;
in.filterSetName = "";' #txt
Cs0 f51 type ch.ivy.addon.portalkit.component.CaseWidget.CaseWidgetData #txt
Cs0 f51 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Clear filter</name>
        <nameStyle>12,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f51 395 977 112 44 -28 -8 #rect
Cs0 f51 @|StepIcon #fIcon
Cs0 f52 type ch.ivy.addon.portalkit.component.CaseWidget.CaseWidgetData #txt
Cs0 f52 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>is filter
existed?</name>
        <nameStyle>18,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f52 307 887 32 32 -23 18 #rect
Cs0 f52 @|AlternativeIcon #fIcon
Cs0 f53 actionDecl 'ch.ivy.addon.portalkit.component.CaseWidget.CaseWidgetData out;
' #txt
Cs0 f53 actionTable 'out=in;
' #txt
Cs0 f53 actionCode 'import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import ch.ivy.addon.portalkit.service.CaseFilterService;
CaseFilterService service = new CaseFilterService();
in.isFilterExisted = false;
if (service.isFilterExisted(in.filterSetName, in.filterType, in.filterGroupId)) {
	FacesMessage message = new FacesMessage( FacesMessage.SEVERITY_ERROR, ivy.cms.co("/ch.ivy.addon.portalkit.ui.jsf/components/taskView/filterExistedValidationError"), "");
	FacesContext.getCurrentInstance().addMessage("", message);
	FacesContext.getCurrentInstance().validationFailed();
	in.isFilterExisted = true;
}' #txt
Cs0 f53 type ch.ivy.addon.portalkit.component.CaseWidget.CaseWidgetData #txt
Cs0 f53 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>validate</name>
        <nameStyle>8,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f53 139 881 112 44 -21 -8 #rect
Cs0 f53 @|StepIcon #fIcon
Cs0 f54 expr out #txt
Cs0 f54 507 999 574 999 #arcP
Cs0 f55 expr out #txt
Cs0 f55 80 903 139 903 #arcP
Cs0 f56 expr out #txt
Cs0 f56 251 903 307 903 #arcP
Cs0 f57 expr out #txt
Cs0 f57 451 925 451 977 #arcP
Cs0 f58 expr in #txt
Cs0 f58 outCond 'in.isFilterExisted == false' #txt
Cs0 f58 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>no</name>
        <nameStyle>2,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f58 339 903 395 903 #arcP
Cs0 f59 expr in #txt
Cs0 f59 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>yes</name>
        <nameStyle>3,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f59 323 887 566 855 #arcP
Cs0 f59 1 323 855 #addKink
Cs0 f59 1 0.3018284624086855 0 0 #arcLabel
Cs0 f60 expr out #txt
Cs0 f60 80 999 395 999 #arcP
Cs0 f43 guid 15FFCD4B4A52EC54 #txt
Cs0 f43 type ch.ivy.addon.portalkit.component.CaseWidget.CaseWidgetData #txt
Cs0 f43 method applyFilter(ch.ivy.addon.portalkit.casefilter.CaseFilterData) #txt
Cs0 f43 disableUIEvents false #txt
Cs0 f43 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<ch.ivy.addon.portalkit.casefilter.CaseFilterData filterData> param = methodEvent.getInputArguments();
' #txt
Cs0 f43 inParameterMapAction 'out.filteringKeyword=param.filterData.keyword;
' #txt
Cs0 f43 inActionCode out.dataModel.applyFilter(param.filterData); #txt
Cs0 f43 outParameterDecl '<> result;
' #txt
Cs0 f43 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>applyFilter(TaskFilterData)</name>
        <nameStyle>27,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f43 50 1077 26 26 -48 18 #rect
Cs0 f43 @|RichDialogMethodStartIcon #fIcon
Cs0 f44 expr out #txt
Cs0 f44 76 1090 211 1090 #arcP
Cs0 f41 type ch.ivy.addon.portalkit.component.CaseWidget.CaseWidgetData #txt
Cs0 f41 211 1077 26 26 0 12 #rect
Cs0 f41 @|RichDialogProcessEndIcon #fIcon
Cs0 f42 type ch.ivy.addon.portalkit.component.CaseWidget.CaseWidgetData #txt
Cs0 f42 347 1169 26 26 0 12 #rect
Cs0 f42 @|RichDialogProcessEndIcon #fIcon
Cs0 f40 guid 15FFCDB06969BA5E #txt
Cs0 f40 type ch.ivy.addon.portalkit.component.CaseWidget.CaseWidgetData #txt
Cs0 f40 method setFilterToBeDeleted(ch.ivy.addon.portalkit.casefilter.CaseFilterData) #txt
Cs0 f40 disableUIEvents false #txt
Cs0 f40 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<ch.ivy.addon.portalkit.casefilter.CaseFilterData filterData> param = methodEvent.getInputArguments();
' #txt
Cs0 f40 inParameterMapAction 'out.filterDataToBeDeleted=param.filterData;
' #txt
Cs0 f40 outParameterDecl '<> result;
' #txt
Cs0 f40 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>setFilterToBeDeleted(String)</name>
        <nameStyle>28,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f40 50 1169 26 26 -51 16 #rect
Cs0 f40 @|RichDialogMethodStartIcon #fIcon
Cs0 f45 expr out #txt
Cs0 f45 76 1182 347 1182 #arcP
Cs0 f61 actionDecl 'ch.ivy.addon.portalkit.component.CaseWidget.CaseWidgetData out;
' #txt
Cs0 f61 actionTable 'out=in;
' #txt
Cs0 f61 actionCode 'import ch.ivy.addon.portalkit.service.CaseFilterService;
import ch.ivy.addon.portalkit.bean.PermissionBean;
import ch.ivy.addon.portalkit.enums.FilterType;
CaseFilterService filterService = new CaseFilterService();
in.privateFilters = filterService.getPrivateFilterForCurrentUser(in.filterGroupId) as List;
in.publicFilters = filterService.getPublicFilter(in.filterGroupId) as List;
in.filterType = FilterType.ONLY_ME;' #txt
Cs0 f61 type ch.ivy.addon.portalkit.component.CaseWidget.CaseWidgetData #txt
Cs0 f61 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>load filter set</name>
        <nameStyle>15,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f61 238 84 36 24 20 -2 #rect
Cs0 f61 @|StepIcon #fIcon
Cs0 f62 expr out #txt
Cs0 f62 75 96 238 96 #arcP
Cs0 f2 expr out #txt
Cs0 f2 274 96 525 96 #arcP
Cs0 f66 type ch.ivy.addon.portalkit.component.CaseWidget.CaseWidgetData #txt
Cs0 f66 337 1267 26 26 0 12 #rect
Cs0 f66 @|RichDialogProcessEndIcon #fIcon
Cs0 f67 guid 1600186A4F085B89 #txt
Cs0 f67 type ch.ivy.addon.portalkit.component.CaseWidget.CaseWidgetData #txt
Cs0 f67 method deleteFilter() #txt
Cs0 f67 disableUIEvents false #txt
Cs0 f67 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<> param = methodEvent.getInputArguments();
' #txt
Cs0 f67 outParameterDecl '<> result;
' #txt
Cs0 f67 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>deleteFilter()</name>
    </language>
</elementInfo>
' #txt
Cs0 f67 49 1267 26 26 -34 15 #rect
Cs0 f67 @|RichDialogMethodStartIcon #fIcon
Cs0 f68 actionDecl 'ch.ivy.addon.portalkit.component.CaseWidget.CaseWidgetData out;
' #txt
Cs0 f68 actionTable 'out=in;
' #txt
Cs0 f68 actionCode 'import ch.ivy.addon.portalkit.enums.FilterType;
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
Cs0 f68 type ch.ivy.addon.portalkit.component.CaseWidget.CaseWidgetData #txt
Cs0 f68 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>remove filter</name>
        <nameStyle>13,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f68 158 1258 112 44 -33 -8 #rect
Cs0 f68 @|StepIcon #fIcon
Cs0 f69 expr out #txt
Cs0 f69 75 1280 158 1280 #arcP
Cs0 f70 expr out #txt
Cs0 f70 270 1280 337 1280 #arcP
>Proto Cs0 .type ch.ivy.addon.portalkit.component.CaseWidget.CaseWidgetData #txt
>Proto Cs0 .processKind HTML_DIALOG #txt
>Proto Cs0 -8 -8 16 16 16 26 #rect
>Proto Cs0 '' #fIcon
Cs0 f11 mainOut f4 tail #connect
Cs0 f4 head f3 mainIn #connect
Cs0 f3 mainOut f5 tail #connect
Cs0 f5 head f12 mainIn #connect
Cs0 f14 mainOut f24 tail #connect
Cs0 f24 head f25 mainIn #connect
Cs0 f18 mainOut f22 tail #connect
Cs0 f22 head f20 mainIn #connect
Cs0 f20 mainOut f28 tail #connect
Cs0 f28 head f26 mainIn #connect
Cs0 f19 mainOut f29 tail #connect
Cs0 f29 head f21 mainIn #connect
Cs0 f21 mainOut f30 tail #connect
Cs0 f30 head f27 mainIn #connect
Cs0 f31 mainOut f17 tail #connect
Cs0 f17 head f23 mainIn #connect
Cs0 f13 mainOut f16 tail #connect
Cs0 f16 head f31 mainIn #connect
Cs0 f25 mainOut f33 tail #connect
Cs0 f33 head f32 in #connect
Cs0 f34 head f23 mainIn #connect
Cs0 f32 out f37 tail #connect
Cs0 f37 head f13 mainIn #connect
Cs0 f32 out f34 tail #connect
Cs0 f15 mainOut f38 tail #connect
Cs0 f38 head f35 mainIn #connect
Cs0 f35 mainOut f39 tail #connect
Cs0 f39 head f36 mainIn #connect
Cs0 f48 mainOut f60 tail #connect
Cs0 f60 head f51 mainIn #connect
Cs0 f51 mainOut f54 tail #connect
Cs0 f54 head f49 mainIn #connect
Cs0 f47 mainOut f57 tail #connect
Cs0 f57 head f51 mainIn #connect
Cs0 f46 mainOut f55 tail #connect
Cs0 f55 head f53 mainIn #connect
Cs0 f53 mainOut f56 tail #connect
Cs0 f56 head f52 in #connect
Cs0 f52 out f58 tail #connect
Cs0 f58 head f47 mainIn #connect
Cs0 f52 out f59 tail #connect
Cs0 f59 head f50 mainIn #connect
Cs0 f43 mainOut f44 tail #connect
Cs0 f44 head f41 mainIn #connect
Cs0 f40 mainOut f45 tail #connect
Cs0 f45 head f42 mainIn #connect
Cs0 f0 mainOut f62 tail #connect
Cs0 f62 head f61 mainIn #connect
Cs0 f61 mainOut f2 tail #connect
Cs0 f2 head f1 mainIn #connect
Cs0 f67 mainOut f69 tail #connect
Cs0 f69 head f68 mainIn #connect
Cs0 f68 mainOut f70 tail #connect
Cs0 f70 head f66 mainIn #connect
