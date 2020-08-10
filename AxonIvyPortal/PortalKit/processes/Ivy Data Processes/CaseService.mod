[Ivy]
14715F955CC5A35F 7.5.0 #module
>Proto >Proto Collection #zClass
Ce0 CaseService Big #zClass
Ce0 B #cInfo
Ce0 #process
Ce0 @TextInP .type .type #zField
Ce0 @TextInP .processKind .processKind #zField
Ce0 @AnnotationInP-0n ai ai #zField
Ce0 @TextInP .xml .xml #zField
Ce0 @TextInP .responsibility .responsibility #zField
Ce0 @StartSub f0 '' #zField
Ce0 @EndSub f1 '' #zField
Ce0 @GridStep f6 '' #zField
Ce0 @EndSub f9 '' #zField
Ce0 @GridStep f11 '' #zField
Ce0 @StartSub f12 '' #zField
Ce0 @StartSub f18 '' #zField
Ce0 @EndSub f21 '' #zField
Ce0 @GridStep f22 '' #zField
Ce0 @EndSub f40 '' #zField
Ce0 @EndSub f28 '' #zField
Ce0 @StartSub f29 '' #zField
Ce0 @StartSub f39 '' #zField
Ce0 @GridStep f33 '' #zField
Ce0 @GridStep f34 '' #zField
Ce0 @PushWFArc f54 '' #zField
Ce0 @PushWFArc f2 '' #zField
Ce0 @PushWFArc f17 '' #zField
Ce0 @PushWFArc f26 '' #zField
Ce0 @PushWFArc f37 '' #zField
Ce0 @StartSub f4 '' #zField
Ce0 @EndSub f7 '' #zField
Ce0 @GridStep f10 '' #zField
Ce0 @PushWFArc f31 '' #zField
Ce0 @PushWFArc f32 '' #zField
Ce0 @PushWFArc f3 '' #zField
Ce0 @PushWFArc f5 '' #zField
Ce0 @PushWFArc f8 '' #zField
Ce0 @PushWFArc f13 '' #zField
Ce0 @PushWFArc f15 '' #zField
>Proto Ce0 Ce0 CaseService #zField
Ce0 f0 inParamDecl '<ch.ivy.addon.portalkit.ivydata.searchcriteria.CaseSearchCriteria caseSearchCriteria,Integer startIndex,Integer count> param;' #txt
Ce0 f0 inParamTable 'out.caseSearchCriteria=param.caseSearchCriteria;
out.count=param.count;
out.startIndex=param.startIndex;
' #txt
Ce0 f0 outParamDecl '<java.util.List<ch.ivyteam.ivy.workflow.ICase> cases> result;' #txt
Ce0 f0 outParamTable 'result.cases=in.cases;
' #txt
Ce0 f0 callSignature findCasesByCriteria(ch.ivy.addon.portalkit.ivydata.searchcriteria.CaseSearchCriteria,Integer,Integer) #txt
Ce0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>findCasesByCriteria(CaseSearchCriteria,Integer,Integer)</name>
    </language>
</elementInfo>
' #txt
Ce0 f0 81 81 30 30 -89 26 #rect
Ce0 f0 @|StartSubIcon #fIcon
Ce0 f1 417 81 30 30 0 15 #rect
Ce0 f1 @|EndSubIcon #fIcon
Ce0 f6 actionTable 'out=in;
' #txt
Ce0 f6 actionCode 'import ch.ivy.addon.portalkit.ivydata.dto.IvyCaseResultDTO;
import ch.ivy.addon.portalkit.ivydata.service.impl.CaseService;

IvyCaseResultDTO dto = CaseService.newInstance().findCasesByCriteria(in.caseSearchCriteria, in.startIndex, in.count);
out.cases = dto.cases;' #txt
Ce0 f6 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Find cases</name>
    </language>
</elementInfo>
' #txt
Ce0 f6 184 74 112 44 -30 -8 #rect
Ce0 f6 @|StepIcon #fIcon
Ce0 f9 417 177 30 30 0 15 #rect
Ce0 f9 @|EndSubIcon #fIcon
Ce0 f11 actionTable 'out=in;
' #txt
Ce0 f11 actionCode 'import ch.ivy.addon.portalkit.ivydata.dto.IvyCaseResultDTO;
import ch.ivy.addon.portalkit.ivydata.service.impl.CaseService;

IvyCaseResultDTO dto = CaseService.newInstance().countCasesByCriteria(in.caseSearchCriteria);
out.totalCases = dto.totalCases;' #txt
Ce0 f11 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Count cases</name>
    </language>
</elementInfo>
' #txt
Ce0 f11 184 170 112 44 -35 -8 #rect
Ce0 f11 @|StepIcon #fIcon
Ce0 f12 inParamDecl '<ch.ivy.addon.portalkit.ivydata.searchcriteria.CaseSearchCriteria caseSearchCriteria> param;' #txt
Ce0 f12 inParamTable 'out.caseSearchCriteria=param.caseSearchCriteria;
' #txt
Ce0 f12 outParamDecl '<Long totalCases> result;' #txt
Ce0 f12 outParamTable 'result.totalCases=in.totalCases;
' #txt
Ce0 f12 callSignature countCasesByCriteria(ch.ivy.addon.portalkit.ivydata.searchcriteria.CaseSearchCriteria) #txt
Ce0 f12 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>countCasesByCriteria(CaseSearchCriteria)</name>
    </language>
</elementInfo>
' #txt
Ce0 f12 81 177 30 30 -89 26 #rect
Ce0 f12 @|StartSubIcon #fIcon
Ce0 f18 inParamDecl '<ch.ivy.addon.portalkit.ivydata.searchcriteria.CaseCategorySearchCriteria caseCategorySearchCriteria> param;' #txt
Ce0 f18 inParamTable 'out.caseCategorySearchCriteria=param.caseCategorySearchCriteria;
' #txt
Ce0 f18 outParamDecl '<ch.ivyteam.ivy.workflow.category.CategoryTree categoryTree> result;' #txt
Ce0 f18 outParamTable 'result.categoryTree=in.#categoryTree;
' #txt
Ce0 f18 callSignature findCategoriesByCriteria(ch.ivy.addon.portalkit.ivydata.searchcriteria.CaseCategorySearchCriteria) #txt
Ce0 f18 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>findCategoriesByCriteria(CaseCategorySearchCriteria)</name>
    </language>
</elementInfo>
' #txt
Ce0 f18 81 273 30 30 -89 28 #rect
Ce0 f18 @|StartSubIcon #fIcon
Ce0 f21 417 273 30 30 0 15 #rect
Ce0 f21 @|EndSubIcon #fIcon
Ce0 f22 actionTable 'out=in;
' #txt
Ce0 f22 actionCode 'import ch.ivy.addon.portalkit.ivydata.dto.IvyCaseResultDTO;
import ch.ivy.addon.portalkit.ivydata.service.impl.CaseService;

IvyCaseResultDTO dto = CaseService.newInstance().findCategoriesByCriteria(in.caseCategorySearchCriteria);
out.categoryTree = dto.#categoryTree;' #txt
Ce0 f22 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Find categories</name>
    </language>
</elementInfo>
' #txt
Ce0 f22 184 266 112 44 -42 -8 #rect
Ce0 f22 @|StepIcon #fIcon
Ce0 f40 417 465 30 30 0 15 #rect
Ce0 f40 @|EndSubIcon #fIcon
Ce0 f28 417 369 30 30 0 15 #rect
Ce0 f28 @|EndSubIcon #fIcon
Ce0 f29 inParamDecl '<ch.ivy.addon.portalkit.ivydata.searchcriteria.CaseSearchCriteria caseSearchCriteria> param;' #txt
Ce0 f29 inParamTable 'out.caseSearchCriteria=param.caseSearchCriteria;
' #txt
Ce0 f29 outParamDecl '<ch.ivy.addon.portalkit.bo.CaseStateStatistic caseStateStatistic> result;' #txt
Ce0 f29 outParamTable 'result.caseStateStatistic=in.caseStateStatistic;
' #txt
Ce0 f29 callSignature analyzeCaseStateStatistic(ch.ivy.addon.portalkit.ivydata.searchcriteria.CaseSearchCriteria) #txt
Ce0 f29 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>analyzeCaseStateStatistic(CaseSearchCriteria)</name>
    </language>
</elementInfo>
' #txt
Ce0 f29 81 369 30 30 -68 21 #rect
Ce0 f29 @|StartSubIcon #fIcon
Ce0 f39 inParamDecl '<ch.ivy.addon.portalkit.ivydata.searchcriteria.CaseSearchCriteria caseSearchCriteria> param;' #txt
Ce0 f39 inParamTable 'out.caseSearchCriteria=param.caseSearchCriteria;
' #txt
Ce0 f39 outParamDecl '<ch.ivy.addon.portalkit.bo.ElapsedTimeStatistic elapsedTimeStatistic> result;' #txt
Ce0 f39 outParamTable 'result.elapsedTimeStatistic=in.elapsedTimeStatistic;
' #txt
Ce0 f39 callSignature analyzeElapsedTimeByCaseCategory(ch.ivy.addon.portalkit.ivydata.searchcriteria.CaseSearchCriteria) #txt
Ce0 f39 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>analyzeElapsedTimeByCaseCategory(CaseSearchCriteria)</name>
    </language>
</elementInfo>
' #txt
Ce0 f39 81 465 30 30 -68 21 #rect
Ce0 f39 @|StartSubIcon #fIcon
Ce0 f33 actionTable 'out=in;
' #txt
Ce0 f33 actionCode 'import ch.ivy.addon.portalkit.ivydata.dto.IvyCaseResultDTO;
import ch.ivy.addon.portalkit.ivydata.service.impl.CaseService;

IvyCaseResultDTO dto = CaseService.newInstance().analyzeElapsedTimeByCaseCategory(in.caseSearchCriteria);
out.elapsedTimeStatistic = dto.elapsedTimeStatistic;' #txt
Ce0 f33 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Analyze elapsed time statistic</name>
    </language>
</elementInfo>
' #txt
Ce0 f33 152 458 176 44 -80 -8 #rect
Ce0 f33 @|StepIcon #fIcon
Ce0 f34 actionTable 'out=in;
' #txt
Ce0 f34 actionCode 'import ch.ivy.addon.portalkit.ivydata.dto.IvyCaseResultDTO;
import ch.ivy.addon.portalkit.ivydata.service.impl.CaseService;

IvyCaseResultDTO dto = CaseService.newInstance().analyzeCaseStateStatistic(in.caseSearchCriteria);
out.caseStateStatistic = dto.caseStateStatistic;' #txt
Ce0 f34 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Analyze case state statistic</name>
    </language>
</elementInfo>
' #txt
Ce0 f34 160 362 160 44 -73 -8 #rect
Ce0 f34 @|StepIcon #fIcon
Ce0 f54 expr out #txt
Ce0 f54 111 96 184 96 #arcP
Ce0 f2 expr out #txt
Ce0 f2 111 192 184 192 #arcP
Ce0 f17 expr out #txt
Ce0 f17 111 288 184 288 #arcP
Ce0 f26 expr out #txt
Ce0 f26 111 384 160 384 #arcP
Ce0 f37 expr out #txt
Ce0 f37 111 480 152 480 #arcP
Ce0 f4 inParamDecl '<ch.ivy.addon.portalkit.ivydata.searchcriteria.CaseCustomFieldSearchCriteria caseCustomFieldSearchCriteria> param;' #txt
Ce0 f4 inParamTable 'out.caseCustomFieldSearchCriteria=param.caseCustomFieldSearchCriteria;
' #txt
Ce0 f4 outParamDecl '<java.util.List<String> customFields> result;' #txt
Ce0 f4 outParamTable 'result.customFields=in.customFields;
' #txt
Ce0 f4 callSignature findValuesOfCustomField(ch.ivy.addon.portalkit.ivydata.searchcriteria.CaseCustomFieldSearchCriteria) #txt
Ce0 f4 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>findValuesOfCustomField(CaseCustomFieldSearchCriteria)</name>
    </language>
</elementInfo>
' #txt
Ce0 f4 81 561 30 30 -87 34 #rect
Ce0 f4 @|StartSubIcon #fIcon
Ce0 f7 417 561 30 30 0 15 #rect
Ce0 f7 @|EndSubIcon #fIcon
Ce0 f10 actionTable 'out=in;
' #txt
Ce0 f10 actionCode 'import ch.ivy.addon.portalkit.ivydata.dto.IvyCaseResultDTO;
import ch.ivy.addon.portalkit.ivydata.service.impl.CaseService;

IvyCaseResultDTO dto = CaseService.newInstance().findValuesOfCustomString(in.caseCustomFieldSearchCriteria);
out.customFields = dto.customFields;' #txt
Ce0 f10 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Find custom fields value</name>
    </language>
</elementInfo>
' #txt
Ce0 f10 168 554 144 44 -66 -8 #rect
Ce0 f10 @|StepIcon #fIcon
Ce0 f31 expr out #txt
Ce0 f31 111 576 168 576 #arcP
Ce0 f32 expr out #txt
Ce0 f32 296 96 417 96 #arcP
Ce0 f3 expr out #txt
Ce0 f3 296 192 417 192 #arcP
Ce0 f5 expr out #txt
Ce0 f5 296 288 417 288 #arcP
Ce0 f8 expr out #txt
Ce0 f8 320 384 417 384 #arcP
Ce0 f13 expr out #txt
Ce0 f13 328 480 417 480 #arcP
Ce0 f15 expr out #txt
Ce0 f15 312 576 417 576 #arcP
>Proto Ce0 .type ch.ivyteam.wf.processes.CaseServiceData #txt
>Proto Ce0 .processKind CALLABLE_SUB #txt
>Proto Ce0 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <swimlaneLabel>findCases</swimlaneLabel>
        <swimlaneLabel>addtionalProperties</swimlaneLabel>
        <swimlaneLabel>Documents</swimlaneLabel>
        <swimlaneLabel>Destroy Case</swimlaneLabel>
        <swimlaneLabel></swimlaneLabel>
    </language>
    <swimlaneSize>768</swimlaneSize>
    <swimlaneSize>432</swimlaneSize>
    <swimlaneSize>968</swimlaneSize>
    <swimlaneSize>320</swimlaneSize>
    <swimlaneColor gradient="true">-3342388</swimlaneColor>
    <swimlaneColor gradient="true">-1</swimlaneColor>
    <swimlaneColor gradient="true">-1</swimlaneColor>
    <swimlaneColor gradient="true">-1</swimlaneColor>
    <swimlaneType>LANE</swimlaneType>
    <swimlaneType>LANE</swimlaneType>
    <swimlaneType>LANE</swimlaneType>
    <swimlaneType>LANE</swimlaneType>
    <swimlaneSpaceBefore>0</swimlaneSpaceBefore>
    <swimlaneSpaceBefore>0</swimlaneSpaceBefore>
    <swimlaneSpaceBefore>0</swimlaneSpaceBefore>
    <swimlaneSpaceBefore>0</swimlaneSpaceBefore>
</elementInfo>
' #txt
>Proto Ce0 0 0 32 24 18 0 #rect
>Proto Ce0 @|BIcon #fIcon
Ce0 f0 mainOut f54 tail #connect
Ce0 f54 head f6 mainIn #connect
Ce0 f12 mainOut f2 tail #connect
Ce0 f2 head f11 mainIn #connect
Ce0 f18 mainOut f17 tail #connect
Ce0 f17 head f22 mainIn #connect
Ce0 f29 mainOut f26 tail #connect
Ce0 f26 head f34 mainIn #connect
Ce0 f39 mainOut f37 tail #connect
Ce0 f37 head f33 mainIn #connect
Ce0 f4 mainOut f31 tail #connect
Ce0 f31 head f10 mainIn #connect
Ce0 f6 mainOut f32 tail #connect
Ce0 f32 head f1 mainIn #connect
Ce0 f11 mainOut f3 tail #connect
Ce0 f3 head f9 mainIn #connect
Ce0 f22 mainOut f5 tail #connect
Ce0 f5 head f21 mainIn #connect
Ce0 f34 mainOut f8 tail #connect
Ce0 f8 head f28 mainIn #connect
Ce0 f33 mainOut f13 tail #connect
Ce0 f13 head f40 mainIn #connect
Ce0 f10 mainOut f15 tail #connect
Ce0 f15 head f7 mainIn #connect
