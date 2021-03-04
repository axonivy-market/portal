[Ivy]
1773DD760B7F094F 9.2.0 #module
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
