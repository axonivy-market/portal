[Ivy]
161504784DDFCFC7 7.5.0 #module
>Proto >Proto Collection #zClass
Es0 ElapsedTimeChartDetailsProcess Big #zClass
Es0 RD #cInfo
Es0 #process
Es0 @TextInP .type .type #zField
Es0 @TextInP .processKind .processKind #zField
Es0 @AnnotationInP-0n ai ai #zField
Es0 @MessageFlowInP-0n messageIn messageIn #zField
Es0 @MessageFlowOutP-0n messageOut messageOut #zField
Es0 @TextInP .xml .xml #zField
Es0 @TextInP .responsibility .responsibility #zField
Es0 @UdEvent f3 '' #zField
Es0 @UdExitEnd f4 '' #zField
Es0 @PushWFArc f5 '' #zField
Es0 @UdMethod f15 '' #zField
Es0 @UdProcessEnd f20 '' #zField
Es0 @UdMethod f6 '' #zField
Es0 @CallSub f7 '' #zField
Es0 @UdProcessEnd f9 '' #zField
Es0 @CallSub f18 '' #zField
Es0 @PushWFArc f8 '' #zField
Es0 @PushWFArc f19 '' #zField
Es0 @UdProcessEnd f1 '' #zField
Es0 @UdInit f0 '' #zField
Es0 @PushWFArc f2 '' #zField
Es0 @PushWFArc f11 '' #zField
Es0 @PushWFArc f10 '' #zField
Es0 @UdMethod f12 '' #zField
Es0 @UdProcessEnd f13 '' #zField
Es0 @GridStep f16 '' #zField
Es0 @PushWFArc f17 '' #zField
Es0 @PushWFArc f14 '' #zField
>Proto Es0 Es0 ElapsedTimeChartDetailsProcess #zField
Es0 f3 guid 161504785367B2A4 #txt
Es0 f3 actionTable 'out=in;
' #txt
Es0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>close</name>
    </language>
</elementInfo>
' #txt
Es0 f3 83 147 26 26 -15 12 #rect
Es0 f3 @|UdEventIcon #fIcon
Es0 f4 211 147 26 26 0 12 #rect
Es0 f4 @|UdExitEndIcon #fIcon
Es0 f5 expr out #txt
Es0 f5 109 160 211 160 #arcP
Es0 f15 guid 16AD339444C2C97E #txt
Es0 f15 method countCases(ch.ivy.addon.portalkit.ivydata.searchcriteria.CaseSearchCriteria) #txt
Es0 f15 inParameterDecl '<ch.ivy.addon.portalkit.ivydata.searchcriteria.CaseSearchCriteria caseSearchCriteria> param;' #txt
Es0 f15 inParameterMapAction 'out.caseSearchCriteria=param.caseSearchCriteria;
' #txt
Es0 f15 outParameterDecl '<Long totalCases> result;' #txt
Es0 f15 outParameterMapAction 'result.totalCases=in.totalCases;
' #txt
Es0 f15 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>countCases(CaseSearchCriteria)</name>
    </language>
</elementInfo>
' #txt
Es0 f15 83 339 26 26 -45 14 #rect
Es0 f15 @|UdMethodIcon #fIcon
Es0 f20 563 339 26 26 0 12 #rect
Es0 f20 @|UdProcessEndIcon #fIcon
Es0 f6 guid 16AD339444E6AFE5 #txt
Es0 f6 method findCases(ch.ivy.addon.portalkit.ivydata.searchcriteria.CaseSearchCriteria,Integer,Integer) #txt
Es0 f6 inParameterDecl '<ch.ivy.addon.portalkit.ivydata.searchcriteria.CaseSearchCriteria caseSearchCriteria,Integer startIndex,Integer count> param;' #txt
Es0 f6 inParameterMapAction 'out.caseSearchCriteria=param.caseSearchCriteria;
out.count=param.count;
out.startIndex=param.startIndex;
' #txt
Es0 f6 outParameterDecl '<List<ch.ivyteam.ivy.workflow.ICase> cases> result;' #txt
Es0 f6 outParameterMapAction 'result.cases=in.cases;
' #txt
Es0 f6 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>findCases(CaseSearchCriteria,Integer,Integer)</name>
    </language>
</elementInfo>
' #txt
Es0 f6 84 244 24 24 -74 14 #rect
Es0 f6 @|UdMethodIcon #fIcon
Es0 f7 processCall 'Ivy Data Processes/CaseService:findCasesByCriteria(ch.ivy.addon.portalkit.ivydata.searchcriteria.CaseSearchCriteria,Integer,Integer)' #txt
Es0 f7 requestActionDecl '<ch.ivy.addon.portalkit.ivydata.searchcriteria.CaseSearchCriteria caseSearchCriteria,Integer startIndex,Integer count> param;' #txt
Es0 f7 requestMappingAction 'param.caseSearchCriteria=in.caseSearchCriteria;
param.startIndex=in.startIndex;
param.count=in.count;
' #txt
Es0 f7 responseActionDecl 'ch.ivy.addon.portalkit.component.statistic.ElapsedTimeChartDetails.ElapsedTimeChartDetailsData out;
' #txt
Es0 f7 responseMappingAction 'out=in;
out.cases=result.cases;
' #txt
Es0 f7 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>CaseService</name>
    </language>
</elementInfo>
' #txt
Es0 f7 304 234 112 44 -35 -8 #rect
Es0 f7 @|CallSubIcon #fIcon
Es0 f9 563 243 26 26 0 12 #rect
Es0 f9 @|UdProcessEndIcon #fIcon
Es0 f18 processCall 'Ivy Data Processes/CaseService:countCasesByCriteria(ch.ivy.addon.portalkit.ivydata.searchcriteria.CaseSearchCriteria)' #txt
Es0 f18 requestActionDecl '<ch.ivy.addon.portalkit.ivydata.searchcriteria.CaseSearchCriteria caseSearchCriteria> param;' #txt
Es0 f18 requestMappingAction 'param.caseSearchCriteria=in.caseSearchCriteria;
' #txt
Es0 f18 responseActionDecl 'ch.ivy.addon.portalkit.component.statistic.ElapsedTimeChartDetails.ElapsedTimeChartDetailsData out;
' #txt
Es0 f18 responseMappingAction 'out=in;
out.totalCases=result.totalCases;
' #txt
Es0 f18 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>CaseService</name>
    </language>
</elementInfo>
' #txt
Es0 f18 304 330 112 44 -35 -8 #rect
Es0 f18 @|CallSubIcon #fIcon
Es0 f8 expr out #txt
Es0 f8 108 256 304 256 #arcP
Es0 f19 expr out #txt
Es0 f19 109 352 304 352 #arcP
Es0 f1 565 85 22 22 14 0 #rect
Es0 f1 @|UdProcessEndIcon #fIcon
Es0 f0 guid 16AD3D99ECE5523E #txt
Es0 f0 method start() #txt
Es0 f0 inParameterDecl '<> param;' #txt
Es0 f0 outParameterDecl '<> result;' #txt
Es0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start</name>
    </language>
</elementInfo>
' #txt
Es0 f0 85 85 22 22 14 0 #rect
Es0 f0 @|UdInitIcon #fIcon
Es0 f2 expr out #txt
Es0 f2 107 96 565 96 #arcP
Es0 f11 expr out #txt
Es0 f11 416 352 563 352 #arcP
Es0 f10 expr out #txt
Es0 f10 416 256 563 256 #arcP
Es0 f12 guid 16AD87286B4BA607 #txt
Es0 f12 method initialize(String) #txt
Es0 f12 inParameterDecl '<String caseCategory> param;' #txt
Es0 f12 inParameterMapAction 'out.category=param.caseCategory;
' #txt
Es0 f12 outParameterDecl '<> result;' #txt
Es0 f12 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>initialize(String)</name>
    </language>
</elementInfo>
' #txt
Es0 f12 83 435 26 26 -42 15 #rect
Es0 f12 @|UdMethodIcon #fIcon
Es0 f13 563 435 26 26 0 12 #rect
Es0 f13 @|UdProcessEndIcon #fIcon
Es0 f16 actionTable 'out=in;
' #txt
Es0 f16 actionCode 'import ch.ivy.addon.portalkit.datamodel.ElapsedTimeLazyDataModel;
import ch.ivy.addon.portalkit.statistics.StatisticChartConstants;

in.dataModel = new ElapsedTimeLazyDataModel();
if(!ivy.cms.co(StatisticChartConstants.NO_CATEGORY_CMS).equalsIgnoreCase(in.category)) {
	in.dataModel.setCategory(in.category);
}
' #txt
Es0 f16 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Set CaseCatgory</name>
    </language>
</elementInfo>
' #txt
Es0 f16 200 426 112 44 -46 -8 #rect
Es0 f16 @|StepIcon #fIcon
Es0 f17 expr out #txt
Es0 f17 109 448 200 448 #arcP
Es0 f14 expr out #txt
Es0 f14 312 448 563 448 #arcP
>Proto Es0 .type ch.ivy.addon.portalkit.component.statistic.ElapsedTimeChartDetails.ElapsedTimeChartDetailsData #txt
>Proto Es0 .processKind HTML_DIALOG #txt
>Proto Es0 -8 -8 16 16 16 26 #rect
>Proto Es0 '' #fIcon
Es0 f3 mainOut f5 tail #connect
Es0 f5 head f4 mainIn #connect
Es0 f6 mainOut f8 tail #connect
Es0 f8 head f7 mainIn #connect
Es0 f15 mainOut f19 tail #connect
Es0 f19 head f18 mainIn #connect
Es0 f0 mainOut f2 tail #connect
Es0 f2 head f1 mainIn #connect
Es0 f18 mainOut f11 tail #connect
Es0 f11 head f20 mainIn #connect
Es0 f7 mainOut f10 tail #connect
Es0 f10 head f9 mainIn #connect
Es0 f12 mainOut f17 tail #connect
Es0 f17 head f16 mainIn #connect
Es0 f16 mainOut f14 tail #connect
Es0 f14 head f13 mainIn #connect
