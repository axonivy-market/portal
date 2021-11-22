[Ivy]
1773DD760B7F094F 9.3.1 #module
>Proto >Proto Collection #zClass
Cs0 CaseItemRelatedCasesProcess Big #zClass
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
Cs0 @CallSub f18 '' #zField
Cs0 @UdProcessEnd f20 '' #zField
Cs0 @UdProcessEnd f9 '' #zField
Cs0 @UdMethod f6 '' #zField
Cs0 @CallSub f7 '' #zField
Cs0 @UdMethod f15 '' #zField
Cs0 @PushWFArc f8 '' #zField
Cs0 @PushWFArc f10 '' #zField
Cs0 @PushWFArc f19 '' #zField
Cs0 @PushWFArc f21 '' #zField
Cs0 @GridStep f2 '' #zField
Cs0 @UdProcessEnd f5 '' #zField
Cs0 @UdMethod f11 '' #zField
Cs0 @PushWFArc f13 '' #zField
Cs0 @PushWFArc f4 '' #zField
Cs0 @PushWFArc f3 '' #zField
Cs0 @CallSub f81 '' #zField
Cs0 @Alternative f78 '' #zField
Cs0 @GridStep f82 '' #zField
Cs0 @CallSub f75 '' #zField
Cs0 @UdMethod f77 '' #zField
Cs0 @UdProcessEnd f79 '' #zField
Cs0 @GridStep f76 '' #zField
Cs0 @CallSub f80 '' #zField
Cs0 @PushWFArc f83 '' #zField
Cs0 @PushWFArc f89 '' #zField
Cs0 @PushWFArc f87 '' #zField
Cs0 @PushWFArc f86 '' #zField
Cs0 @PushWFArc f91 '' #zField
Cs0 @PushWFArc f88 '' #zField
Cs0 @PushWFArc f92 '' #zField
Cs0 @PushWFArc f90 '' #zField
>Proto Cs0 Cs0 CaseItemRelatedCasesProcess #zField
Cs0 f0 guid 167E9A75EF3D0909 #txt
Cs0 f0 method start() #txt
Cs0 f0 inParameterDecl '<> param;' #txt
Cs0 f0 outParameterDecl '<> result;' #txt
Cs0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start()</name>
    </language>
</elementInfo>
' #txt
Cs0 f0 83 83 26 26 -16 15 #rect
Cs0 f1 339 83 26 26 0 12 #rect
Cs0 f18 processCall 'Ivy Data Processes/CaseService:countCasesByCriteria(ch.ivy.addon.portalkit.ivydata.searchcriteria.CaseSearchCriteria)' #txt
Cs0 f18 requestActionDecl '<ch.ivy.addon.portalkit.ivydata.searchcriteria.CaseSearchCriteria caseSearchCriteria> param;' #txt
Cs0 f18 requestMappingAction 'param.caseSearchCriteria=in.caseSearchCriteria;
' #txt
Cs0 f18 responseActionDecl 'ch.ivy.addon.portalkit.component.CaseWidget.CaseWidgetData out;
' #txt
Cs0 f18 responseMappingAction 'out=in;
out.totalCases=result.totalCases;
' #txt
Cs0 f18 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>CaseService</name>
    </language>
</elementInfo>
' #txt
Cs0 f18 320 266 112 44 -35 -8 #rect
Cs0 f20 499 275 26 26 0 12 #rect
Cs0 f9 499 179 26 26 0 12 #rect
Cs0 f6 guid 177470EE84B57948 #txt
Cs0 f6 method findCases(ch.ivy.addon.portalkit.ivydata.searchcriteria.CaseSearchCriteria,java.lang.Integer,java.lang.Integer) #txt
Cs0 f6 inParameterDecl '<ch.ivy.addon.portalkit.ivydata.searchcriteria.CaseSearchCriteria caseSearchCriteria,Integer startIndex,Integer count> param;' #txt
Cs0 f6 inParameterMapAction 'out.caseSearchCriteria=param.caseSearchCriteria;
out.count=param.count;
out.startIndex=param.startIndex;
' #txt
Cs0 f6 outParameterDecl '<java.util.List<ch.ivyteam.ivy.workflow.ICase> cases> result;' #txt
Cs0 f6 outParameterMapAction 'result.cases=in.relatedCases;
' #txt
Cs0 f6 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>findCases(CaseSearchCriteria,Integer,Integer)</name>
    </language>
</elementInfo>
' #txt
Cs0 f6 83 179 26 26 -48 13 #rect
Cs0 f7 processCall 'Ivy Data Processes/CaseService:findCasesByCriteria(ch.ivy.addon.portalkit.ivydata.searchcriteria.CaseSearchCriteria,Integer,Integer)' #txt
Cs0 f7 requestActionDecl '<ch.ivy.addon.portalkit.ivydata.searchcriteria.CaseSearchCriteria caseSearchCriteria,Integer startIndex,Integer count> param;' #txt
Cs0 f7 requestMappingAction 'param.caseSearchCriteria=in.caseSearchCriteria;
param.startIndex=in.startIndex;
param.count=in.count;
' #txt
Cs0 f7 responseActionDecl 'ch.ivy.addon.portalkit.component.CaseWidget.CaseWidgetData out;
' #txt
Cs0 f7 responseMappingAction 'out=in;
out.relatedCases=result.cases;
' #txt
Cs0 f7 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>CaseService</name>
    </language>
</elementInfo>
' #txt
Cs0 f7 320 170 112 44 -35 -8 #rect
Cs0 f15 guid 177470EE84B21F55 #txt
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
Cs0 f15 83 275 26 26 -45 14 #rect
Cs0 f8 expr out #txt
Cs0 f8 109 192 320 192 #arcP
Cs0 f10 expr out #txt
Cs0 f10 432 192 499 192 #arcP
Cs0 f19 expr out #txt
Cs0 f19 109 288 320 288 #arcP
Cs0 f21 expr out #txt
Cs0 f21 432 288 499 288 #arcP
Cs0 f2 actionTable 'out=in;
' #txt
Cs0 f2 actionCode 'import ch.ivy.addon.portalkit.datamodel.internal.RelatedCaseLazyDataModel;

in.caseLazyDataModel = new RelatedCaseLazyDataModel(in.iCase.getId());' #txt
Cs0 f2 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>init related case lazy data model</name>
    </language>
</elementInfo>
' #txt
Cs0 f2 184 362 192 44 -87 -8 #rect
Cs0 f5 435 371 26 26 0 12 #rect
Cs0 f11 guid 177F659A0ABC7738 #txt
Cs0 f11 method initData(ch.ivyteam.ivy.workflow.ICase) #txt
Cs0 f11 inParameterDecl '<ch.ivyteam.ivy.workflow.ICase iCase> param;' #txt
Cs0 f11 inParameterMapAction 'out.iCase=param.iCase;
' #txt
Cs0 f11 outParameterDecl '<> result;' #txt
Cs0 f11 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>initData(ICase)</name>
    </language>
</elementInfo>
' #txt
Cs0 f11 83 371 26 26 -41 15 #rect
Cs0 f13 expr out #txt
Cs0 f13 109 96 339 96 #arcP
Cs0 f4 376 384 435 384 #arcP
Cs0 f3 109 384 184 384 #arcP
Cs0 f81 processCall 'Functional Processes/ExportCaseToExcel:exportToExcel(java.util.List<ch.ivyteam.ivy.workflow.ICase>,java.util.List<String>)' #txt
Cs0 f81 requestActionDecl '<java.util.List<ch.ivyteam.ivy.workflow.ICase> collectedCasesForExporting,java.util.List<String> columnsVisibility> param;' #txt
Cs0 f81 requestMappingAction 'param.collectedCasesForExporting=in.collectedCasesForExporting;
param.columnsVisibility=in.columnsVisibility;
' #txt
Cs0 f81 responseMappingAction 'out=in;
out.exportedFile=result.exportedFile;
' #txt
Cs0 f81 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>export to excel</name>
    </language>
</elementInfo>
' #txt
Cs0 f81 664 458 112 44 -38 -8 #rect
Cs0 f78 512 464 32 32 0 16 #rect
Cs0 f82 actionTable 'out=in;
' #txt
Cs0 f82 actionCode in.loopCounter++; #txt
Cs0 f82 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>counter++</name>
    </language>
</elementInfo>
' #txt
Cs0 f82 624 586 112 44 -27 -8 #rect
Cs0 f75 processCall 'Ivy Data Processes/CaseService:findCasesByCriteria(ch.ivy.addon.portalkit.ivydata.searchcriteria.CaseSearchCriteria,Integer,Integer)' #txt
Cs0 f75 requestActionDecl '<ch.ivy.addon.portalkit.ivydata.searchcriteria.CaseSearchCriteria caseSearchCriteria,Integer startIndex,Integer count> param;' #txt
Cs0 f75 requestMappingAction 'param.caseSearchCriteria=in.caseSearchCriteria;
param.startIndex=in.loopCounter * in.maxResultNumberPerQuery;
param.count=in.maxResultNumberPerQuery;
' #txt
Cs0 f75 responseActionDecl 'ch.ivy.addon.portalkit.component.TaskAnalysisWidget.TaskAnalysisWidgetData out;
' #txt
Cs0 f75 responseMappingAction 'out=in;
' #txt
Cs0 f75 responseActionCode in.collectedCasesForExporting.addAll(result.cases); #txt
Cs0 f75 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>find cases</name>
    </language>
</elementInfo>
' #txt
Cs0 f75 472 586 112 44 -28 -8 #rect
Cs0 f77 guid 177FB4D6226CF656 #txt
Cs0 f77 method getExportedFile(java.util.List<String>) #txt
Cs0 f77 inParameterDecl '<java.util.List<String> columnsVisibility> param;' #txt
Cs0 f77 inParameterMapAction 'out.columnsVisibility=param.columnsVisibility;
' #txt
Cs0 f77 outParameterDecl '<org.primefaces.model.StreamedContent exportedFile> result;' #txt
Cs0 f77 outParameterMapAction 'result.exportedFile=in.exportedFile;
' #txt
Cs0 f77 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>getExportedFile(List&lt;String&gt;)</name>
    </language>
</elementInfo>
' #txt
Cs0 f77 83 467 26 26 -46 15 #rect
Cs0 f79 899 467 26 26 0 12 #rect
Cs0 f76 actionTable 'out=in;
' #txt
Cs0 f76 actionCode 'import java.util.ArrayList;
in.loopCounter = 0;
in.maxResultNumberPerQuery = 100000;
in.collectedCasesForExporting = new ArrayList();' #txt
Cs0 f76 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>init data</name>
    </language>
</elementInfo>
' #txt
Cs0 f76 368 458 112 44 -21 -8 #rect
Cs0 f80 processCall 'Ivy Data Processes/CaseService:countCasesByCriteria(ch.ivy.addon.portalkit.ivydata.searchcriteria.CaseSearchCriteria)' #txt
Cs0 f80 requestActionDecl '<ch.ivy.addon.portalkit.ivydata.searchcriteria.CaseSearchCriteria caseSearchCriteria> param;' #txt
Cs0 f80 requestMappingAction 'param.caseSearchCriteria=in.caseSearchCriteria;
' #txt
Cs0 f80 responseActionDecl 'ch.ivy.addon.portalkit.component.TaskAnalysisWidget.TaskAnalysisWidgetData out;
' #txt
Cs0 f80 responseMappingAction 'out=in;
out.totalCases=result.totalCases;
' #txt
Cs0 f80 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>count cases</name>
    </language>
</elementInfo>
' #txt
Cs0 f80 224 458 112 44 -33 -8 #rect
Cs0 f83 336 480 368 480 #arcP
Cs0 f89 expr in #txt
Cs0 f89 outCond 'in.loopCounter <= in.totalCases / in.maxResultNumberPerQuery' #txt
Cs0 f89 528 496 528 586 #arcP
Cs0 f87 expr out #txt
Cs0 f87 109 480 224 480 #arcP
Cs0 f86 expr in #txt
Cs0 f86 544 480 664 480 #arcP
Cs0 f91 680 586 537 487 #arcP
Cs0 f88 776 480 899 480 #arcP
Cs0 f92 480 480 512 480 #arcP
Cs0 f90 expr out #txt
Cs0 f90 584 608 624 608 #arcP
>Proto Cs0 .type ch.ivy.addon.portalkit.component.CaseItemRelatedCases.CaseItemRelatedCasesData #txt
>Proto Cs0 .processKind HTML_DIALOG #txt
>Proto Cs0 -8 -8 16 16 16 26 #rect
Cs0 f6 mainOut f8 tail #connect
Cs0 f8 head f7 mainIn #connect
Cs0 f7 mainOut f10 tail #connect
Cs0 f10 head f9 mainIn #connect
Cs0 f15 mainOut f19 tail #connect
Cs0 f19 head f18 mainIn #connect
Cs0 f18 mainOut f21 tail #connect
Cs0 f21 head f20 mainIn #connect
Cs0 f0 mainOut f13 tail #connect
Cs0 f13 head f1 mainIn #connect
Cs0 f2 mainOut f4 tail #connect
Cs0 f4 head f5 mainIn #connect
Cs0 f11 mainOut f3 tail #connect
Cs0 f3 head f2 mainIn #connect
Cs0 f80 mainOut f83 tail #connect
Cs0 f83 head f76 mainIn #connect
Cs0 f76 mainOut f92 tail #connect
Cs0 f92 head f78 in #connect
Cs0 f89 head f75 mainIn #connect
Cs0 f75 mainOut f90 tail #connect
Cs0 f90 head f82 mainIn #connect
Cs0 f82 mainOut f91 tail #connect
Cs0 f91 head f78 in #connect
Cs0 f77 mainOut f87 tail #connect
Cs0 f87 head f80 mainIn #connect
Cs0 f78 out f89 tail #connect
Cs0 f78 out f86 tail #connect
Cs0 f86 head f81 mainIn #connect
Cs0 f81 mainOut f88 tail #connect
Cs0 f88 head f79 mainIn #connect
