[Ivy]
[>Created: Tue Oct 11 17:07:48 ICT 2016]
152E8EDB33C1BDC1 3.18 #module
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
Cs0 @PushWFArc f2 '' #zField
Cs0 @RichDialogMethodStart f6 '' #zField
Cs0 @RichDialogProcessEnd f7 '' #zField
Cs0 @GridStep f9 '' #zField
Cs0 @PushWFArc f10 '' #zField
Cs0 @PushWFArc f8 '' #zField
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
Cs0 @PushWFArc f28 '' #zField
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
>Proto Cs0 Cs0 CaseWidgetProcess #zField
Cs0 f0 guid 152E8EDB3E3A6957 #txt
Cs0 f0 type ch.ivy.addon.portalkit.component.CaseWidget.CaseWidgetData #txt
Cs0 f0 method start(ch.ivy.addon.portalkit.datamodel.CaseLazyDataModel) #txt
Cs0 f0 disableUIEvents true #txt
Cs0 f0 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<ch.ivy.addon.portalkit.datamodel.CaseLazyDataModel dataModel> param = methodEvent.getInputArguments();
' #txt
Cs0 f0 inParameterMapAction 'out.dataModel=param.dataModel;
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
Cs0 f2 expr out #txt
Cs0 f2 75 96 525 96 #arcP
Cs0 f6 guid 1530C78D050B0AA4 #txt
Cs0 f6 type ch.ivy.addon.portalkit.component.CaseWidget.CaseWidgetData #txt
Cs0 f6 method filter() #txt
Cs0 f6 disableUIEvents false #txt
Cs0 f6 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<> param = methodEvent.getInputArguments();
' #txt
Cs0 f6 outParameterDecl '<> result;
' #txt
Cs0 f6 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>filter()</name>
    </language>
</elementInfo>
' #txt
Cs0 f6 53 373 22 22 14 0 #rect
Cs0 f6 @|RichDialogMethodStartIcon #fIcon
Cs0 f7 type ch.ivy.addon.portalkit.component.CaseWidget.CaseWidgetData #txt
Cs0 f7 525 373 22 22 14 0 #rect
Cs0 f7 @|RichDialogProcessEndIcon #fIcon
Cs0 f9 actionDecl 'ch.ivy.addon.portalkit.component.CaseWidget.CaseWidgetData out;
' #txt
Cs0 f9 actionTable 'out=in;
' #txt
Cs0 f9 actionCode in.dataModel.setKeyword(in.filteringKeyword); #txt
Cs0 f9 type ch.ivy.addon.portalkit.component.CaseWidget.CaseWidgetData #txt
Cs0 f9 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>filter cases 
based on keyword</name>
        <nameStyle>30,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f9 270 372 36 24 20 14 #rect
Cs0 f9 @|StepIcon #fIcon
Cs0 f10 expr out #txt
Cs0 f10 75 384 270 384 #arcP
Cs0 f8 expr out #txt
Cs0 f8 306 384 525 384 #arcP
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
Cs0 f18 method findCases(java.lang.Integer,java.lang.Integer,ch.ivy.ws.addon.CaseSearchCriteria) #txt
Cs0 f18 disableUIEvents false #txt
Cs0 f18 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<java.lang.Integer startIndex,java.lang.Integer count,ch.ivy.ws.addon.CaseSearchCriteria caseSearchCriteria> param = methodEvent.getInputArguments();
' #txt
Cs0 f18 inParameterMapAction 'out.caseSearchCriteria=param.caseSearchCriteria;
out.count=param.count;
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
Cs0 f19 method countCases(ch.ivy.ws.addon.CaseSearchCriteria) #txt
Cs0 f19 disableUIEvents false #txt
Cs0 f19 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<ch.ivy.ws.addon.CaseSearchCriteria caseSearchCriteria> param = methodEvent.getInputArguments();
' #txt
Cs0 f19 inParameterMapAction 'out.caseSearchCriteria=param.caseSearchCriteria;
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
Cs0 f20 processCall MultiPortal/CaseService:findCasesByCriteria(Integer,Integer,ch.ivy.ws.addon.CaseSearchCriteria) #txt
Cs0 f20 doCall true #txt
Cs0 f20 requestActionDecl '<java.lang.Integer startIndex,java.lang.Integer count,ch.ivy.ws.addon.CaseSearchCriteria caseSearchCriteria> param;
' #txt
Cs0 f20 requestMappingAction 'param.startIndex=in.startIndex;
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
Cs0 f21 processCall MultiPortal/CaseService:countCasesByCriteria(ch.ivy.ws.addon.CaseSearchCriteria) #txt
Cs0 f21 doCall true #txt
Cs0 f21 requestActionDecl '<ch.ivy.ws.addon.CaseSearchCriteria caseSearchCriteria> param;
' #txt
Cs0 f21 requestMappingAction 'param.caseSearchCriteria=in.caseSearchCriteria;
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
Cs0 f28 expr out #txt
Cs0 f28 370 608 469 608 #arcP
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
>Proto Cs0 .type ch.ivy.addon.portalkit.component.CaseWidget.CaseWidgetData #txt
>Proto Cs0 .processKind HTML_DIALOG #txt
>Proto Cs0 -8 -8 16 16 16 26 #rect
>Proto Cs0 '' #fIcon
Cs0 f0 mainOut f2 tail #connect
Cs0 f2 head f1 mainIn #connect
Cs0 f6 mainOut f10 tail #connect
Cs0 f10 head f9 mainIn #connect
Cs0 f9 mainOut f8 tail #connect
Cs0 f8 head f7 mainIn #connect
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
