[Ivy]
14715F955CC5A35F 3.23 #module
>Proto >Proto Collection #zClass
Ce0 CaseService Big #zClass
Ce0 B #cInfo
Ce0 #process
Ce0 @TextInP .resExport .resExport #zField
Ce0 @TextInP .type .type #zField
Ce0 @TextInP .processKind .processKind #zField
Ce0 @AnnotationInP-0n ai ai #zField
Ce0 @TextInP .xml .xml #zField
Ce0 @TextInP .responsibility .responsibility #zField
Ce0 @StartSub f0 '' #zField
Ce0 @EndSub f1 '' #zField
Ce0 @GridStep f6 '' #zField
Ce0 @CallSub f5 '' #zField
Ce0 @GridStep f10 '' #zField
Ce0 @PushWFArc f7 '' #zField
Ce0 @PushWFArc f8 '' #zField
Ce0 @PushWFArc f2 '' #zField
Ce0 @PushWFArc f3 '' #zField
Ce0 @GridStep f4 '' #zField
Ce0 @EndSub f9 '' #zField
Ce0 @GridStep f11 '' #zField
Ce0 @StartSub f12 '' #zField
Ce0 @CallSub f13 '' #zField
Ce0 @PushWFArc f14 '' #zField
Ce0 @PushWFArc f15 '' #zField
Ce0 @PushWFArc f16 '' #zField
Ce0 @PushWFArc f17 '' #zField
Ce0 @StartSub f18 '' #zField
Ce0 @CallSub f19 '' #zField
Ce0 @GridStep f20 '' #zField
Ce0 @EndSub f21 '' #zField
Ce0 @GridStep f22 '' #zField
Ce0 @PushWFArc f23 '' #zField
Ce0 @PushWFArc f24 '' #zField
Ce0 @PushWFArc f25 '' #zField
Ce0 @PushWFArc f26 '' #zField
Ce0 @EndSub f40 '' #zField
Ce0 @CallSub f27 '' #zField
Ce0 @EndSub f28 '' #zField
Ce0 @GridStep f45 '' #zField
Ce0 @StartSub f29 '' #zField
Ce0 @StartSub f39 '' #zField
Ce0 @StartSub f48 '' #zField
Ce0 @CallSub f30 '' #zField
Ce0 @GridStep f31 '' #zField
Ce0 @GridStep f32 '' #zField
Ce0 @GridStep f46 '' #zField
Ce0 @GridStep f33 '' #zField
Ce0 @EndSub f47 '' #zField
Ce0 @GridStep f34 '' #zField
Ce0 @CallSub f49 '' #zField
Ce0 @PushWFArc f53 '' #zField
Ce0 @PushWFArc f50 '' #zField
Ce0 @PushWFArc f35 '' #zField
Ce0 @PushWFArc f36 '' #zField
Ce0 @PushWFArc f52 '' #zField
Ce0 @PushWFArc f37 '' #zField
Ce0 @PushWFArc f38 '' #zField
Ce0 @PushWFArc f51 '' #zField
Ce0 @PushWFArc f43 '' #zField
Ce0 @PushWFArc f44 '' #zField
Ce0 @PushWFArc f41 '' #zField
Ce0 @PushWFArc f42 '' #zField
>Proto Ce0 Ce0 CaseService #zField
Ce0 f0 inParamDecl '<ch.ivy.addon.portalkit.ivydata.searchcriteria.CaseSearchCriteria caseSearchCriteria,java.lang.Integer startIndex,java.lang.Integer count> param;' #txt
Ce0 f0 inParamTable 'out.caseSearchCriteria=param.caseSearchCriteria;
out.count=param.count;
out.startIndex=param.startIndex;
' #txt
Ce0 f0 outParamDecl '<java.util.List<ch.ivy.addon.portalkit.ivydata.exception.PortalIvyDataException> errors,java.util.List<ch.ivyteam.ivy.workflow.ICase> cases> result;
' #txt
Ce0 f0 outParamTable 'result.errors=in.errors;
result.cases=in.cases;
' #txt
Ce0 f0 actionDecl 'ch.ivyteam.wf.processes.CaseServiceData out;
' #txt
Ce0 f0 callSignature findCasesByCriteria(ch.ivy.addon.portalkit.ivydata.searchcriteria.CaseSearchCriteria,Integer,Integer) #txt
Ce0 f0 type ch.ivyteam.wf.processes.CaseServiceData #txt
Ce0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>findCasesByCriteria(CaseSearchCriteria,Integer,Integer)</name>
    </language>
</elementInfo>
' #txt
Ce0 f0 81 81 30 30 -89 26 #rect
Ce0 f0 @|StartSubIcon #fIcon
Ce0 f1 type ch.ivyteam.wf.processes.CaseServiceData #txt
Ce0 f1 785 81 30 30 0 15 #rect
Ce0 f1 @|EndSubIcon #fIcon
Ce0 f6 actionDecl 'ch.ivyteam.wf.processes.CaseServiceData out;
' #txt
Ce0 f6 actionTable 'out=in;
' #txt
Ce0 f6 actionCode 'import ch.ivy.addon.portalkit.ivydata.dto.IvyCaseResultDTO;
import ch.ivy.addon.portalkit.ivydata.service.impl.CaseService;

IvyCaseResultDTO dto = CaseService.newInstance().findCasesByCriteria(in.caseSearchCriteria, in.startIndex, in.count);
out.cases = dto.cases;
out.errors = dto.errors;' #txt
Ce0 f6 type ch.ivyteam.wf.processes.CaseServiceData #txt
Ce0 f6 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Find cases</name>
    </language>
</elementInfo>
' #txt
Ce0 f6 416 74 112 44 -30 -8 #rect
Ce0 f6 @|StepIcon #fIcon
Ce0 f5 type ch.ivyteam.wf.processes.CaseServiceData #txt
Ce0 f5 processCall 'Functional Processes/ErrorHandler:handle(List<ch.ivy.addon.portalkit.ivydata.exception.PortalIvyDataException>)' #txt
Ce0 f5 doCall true #txt
Ce0 f5 requestActionDecl '<java.util.List<ch.ivy.addon.portalkit.ivydata.exception.PortalIvyDataException> exceptions> param;
' #txt
Ce0 f5 requestMappingAction 'param.exceptions=in.errors;
' #txt
Ce0 f5 responseActionDecl 'ch.ivyteam.wf.processes.CaseServiceData out;
' #txt
Ce0 f5 responseMappingAction 'out=in;
' #txt
Ce0 f5 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>ErrorHandler</name>
    </language>
</elementInfo>
' #txt
Ce0 f5 600 74 112 44 -35 -8 #rect
Ce0 f5 @|CallSubIcon #fIcon
Ce0 f10 actionDecl 'ch.ivyteam.wf.processes.CaseServiceData out;
' #txt
Ce0 f10 actionTable 'out=in;
' #txt
Ce0 f10 actionCode 'import ch.ivy.addon.portalkit.service.ApplicationService;

ApplicationService service = new ApplicationService();
out.caseSearchCriteria.apps = service.findActiveIvyAppsBy(in.caseSearchCriteria.apps);
' #txt
Ce0 f10 type ch.ivyteam.wf.processes.CaseServiceData #txt
Ce0 f10 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Find applications can work on</name>
    </language>
</elementInfo>
' #txt
Ce0 f10 176 74 176 44 -81 -8 #rect
Ce0 f10 @|StepIcon #fIcon
Ce0 f7 expr out #txt
Ce0 f7 352 96 416 96 #arcP
Ce0 f8 expr out #txt
Ce0 f8 528 96 600 96 #arcP
Ce0 f2 expr out #txt
Ce0 f2 111 96 176 96 #arcP
Ce0 f3 expr out #txt
Ce0 f3 712 96 785 96 #arcP
Ce0 f4 actionDecl 'ch.ivyteam.wf.processes.CaseServiceData out;
' #txt
Ce0 f4 actionTable 'out=in;
' #txt
Ce0 f4 actionCode 'import ch.ivy.addon.portalkit.service.ApplicationService;

ApplicationService service = new ApplicationService();
out.caseSearchCriteria.apps = service.findActiveIvyAppsBy(in.caseSearchCriteria.apps);
' #txt
Ce0 f4 type ch.ivyteam.wf.processes.CaseServiceData #txt
Ce0 f4 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Find applications can work on</name>
    </language>
</elementInfo>
' #txt
Ce0 f4 176 170 176 44 -81 -8 #rect
Ce0 f4 @|StepIcon #fIcon
Ce0 f9 type ch.ivyteam.wf.processes.CaseServiceData #txt
Ce0 f9 785 177 30 30 0 15 #rect
Ce0 f9 @|EndSubIcon #fIcon
Ce0 f11 actionDecl 'ch.ivyteam.wf.processes.CaseServiceData out;
' #txt
Ce0 f11 actionTable 'out=in;
' #txt
Ce0 f11 actionCode 'import ch.ivy.addon.portalkit.ivydata.dto.IvyCaseResultDTO;
import ch.ivy.addon.portalkit.ivydata.service.impl.CaseService;

IvyCaseResultDTO dto = CaseService.newInstance().countCasesByCriteria(in.caseSearchCriteria);
out.totalCases = dto.totalCases;
out.errors = dto.errors;' #txt
Ce0 f11 type ch.ivyteam.wf.processes.CaseServiceData #txt
Ce0 f11 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Count cases</name>
    </language>
</elementInfo>
' #txt
Ce0 f11 416 170 112 44 -35 -8 #rect
Ce0 f11 @|StepIcon #fIcon
Ce0 f12 inParamDecl '<ch.ivy.addon.portalkit.ivydata.searchcriteria.CaseSearchCriteria caseSearchCriteria> param;' #txt
Ce0 f12 inParamTable 'out.caseSearchCriteria=param.caseSearchCriteria;
' #txt
Ce0 f12 outParamDecl '<java.util.List<ch.ivy.addon.portalkit.ivydata.exception.PortalIvyDataException> errors,java.lang.Long totalCases> result;
' #txt
Ce0 f12 outParamTable 'result.errors=in.errors;
result.totalCases=in.totalCases;
' #txt
Ce0 f12 actionDecl 'ch.ivyteam.wf.processes.CaseServiceData out;
' #txt
Ce0 f12 callSignature countCasesByCriteria(ch.ivy.addon.portalkit.ivydata.searchcriteria.CaseSearchCriteria) #txt
Ce0 f12 type ch.ivyteam.wf.processes.CaseServiceData #txt
Ce0 f12 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>countCasesByCriteria(CaseSearchCriteria)</name>
    </language>
</elementInfo>
' #txt
Ce0 f12 81 177 30 30 -89 26 #rect
Ce0 f12 @|StartSubIcon #fIcon
Ce0 f13 type ch.ivyteam.wf.processes.CaseServiceData #txt
Ce0 f13 processCall 'Functional Processes/ErrorHandler:handle(List<ch.ivy.addon.portalkit.ivydata.exception.PortalIvyDataException>)' #txt
Ce0 f13 doCall true #txt
Ce0 f13 requestActionDecl '<java.util.List<ch.ivy.addon.portalkit.ivydata.exception.PortalIvyDataException> exceptions> param;
' #txt
Ce0 f13 requestMappingAction 'param.exceptions=in.errors;
' #txt
Ce0 f13 responseActionDecl 'ch.ivyteam.wf.processes.CaseServiceData out;
' #txt
Ce0 f13 responseMappingAction 'out=in;
' #txt
Ce0 f13 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>ErrorHandler</name>
    </language>
</elementInfo>
' #txt
Ce0 f13 600 170 112 44 -35 -8 #rect
Ce0 f13 @|CallSubIcon #fIcon
Ce0 f14 expr out #txt
Ce0 f14 352 192 416 192 #arcP
Ce0 f15 expr out #txt
Ce0 f15 712 192 785 192 #arcP
Ce0 f16 expr out #txt
Ce0 f16 528 192 600 192 #arcP
Ce0 f17 expr out #txt
Ce0 f17 111 192 176 192 #arcP
Ce0 f18 inParamDecl '<ch.ivy.addon.portalkit.ivydata.searchcriteria.CaseCategorySearchCriteria caseCategorySearchCriteria> param;' #txt
Ce0 f18 inParamTable 'out.caseCategorySearchCriteria=param.caseCategorySearchCriteria;
' #txt
Ce0 f18 outParamDecl '<ch.ivyteam.ivy.workflow.category.CategoryTree categoryTree> result;
' #txt
Ce0 f18 outParamTable 'result.categoryTree=in.#categoryTree;
' #txt
Ce0 f18 actionDecl 'ch.ivyteam.wf.processes.CaseServiceData out;
' #txt
Ce0 f18 callSignature findCategoriesByCriteria(ch.ivy.addon.portalkit.ivydata.searchcriteria.CaseCategorySearchCriteria) #txt
Ce0 f18 type ch.ivyteam.wf.processes.CaseServiceData #txt
Ce0 f18 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>findCategoriesByCriteria(CaseCategorySearchCriteria)</name>
    </language>
</elementInfo>
' #txt
Ce0 f18 81 273 30 30 -89 28 #rect
Ce0 f18 @|StartSubIcon #fIcon
Ce0 f19 type ch.ivyteam.wf.processes.CaseServiceData #txt
Ce0 f19 processCall 'Functional Processes/ErrorHandler:handle(List<ch.ivy.addon.portalkit.ivydata.exception.PortalIvyDataException>)' #txt
Ce0 f19 doCall true #txt
Ce0 f19 requestActionDecl '<java.util.List<ch.ivy.addon.portalkit.ivydata.exception.PortalIvyDataException> exceptions> param;
' #txt
Ce0 f19 requestMappingAction 'param.exceptions=in.errors;
' #txt
Ce0 f19 responseActionDecl 'ch.ivyteam.wf.processes.CaseServiceData out;
' #txt
Ce0 f19 responseMappingAction 'out=in;
' #txt
Ce0 f19 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>ErrorHandler</name>
    </language>
</elementInfo>
' #txt
Ce0 f19 600 266 112 44 -35 -8 #rect
Ce0 f19 @|CallSubIcon #fIcon
Ce0 f20 actionDecl 'ch.ivyteam.wf.processes.CaseServiceData out;
' #txt
Ce0 f20 actionTable 'out=in;
' #txt
Ce0 f20 actionCode 'import ch.ivy.addon.portalkit.service.ApplicationService;

ApplicationService service = new ApplicationService();
out.caseCategorySearchCriteria.apps = service.findActiveIvyAppsBy(in.caseCategorySearchCriteria.apps);' #txt
Ce0 f20 type ch.ivyteam.wf.processes.CaseServiceData #txt
Ce0 f20 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Find applications can work on</name>
    </language>
</elementInfo>
' #txt
Ce0 f20 176 266 176 44 -81 -8 #rect
Ce0 f20 @|StepIcon #fIcon
Ce0 f21 type ch.ivyteam.wf.processes.CaseServiceData #txt
Ce0 f21 785 273 30 30 0 15 #rect
Ce0 f21 @|EndSubIcon #fIcon
Ce0 f22 actionDecl 'ch.ivyteam.wf.processes.CaseServiceData out;
' #txt
Ce0 f22 actionTable 'out=in;
' #txt
Ce0 f22 actionCode 'import ch.ivy.addon.portalkit.ivydata.dto.IvyCaseResultDTO;
import ch.ivy.addon.portalkit.ivydata.service.impl.CaseService;

IvyCaseResultDTO dto = CaseService.newInstance().findCategoriesByCriteria(in.caseCategorySearchCriteria);
out.categoryTree = dto.#categoryTree;
out.errors = dto.errors;' #txt
Ce0 f22 type ch.ivyteam.wf.processes.CaseServiceData #txt
Ce0 f22 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Find categories</name>
    </language>
</elementInfo>
' #txt
Ce0 f22 416 266 112 44 -42 -8 #rect
Ce0 f22 @|StepIcon #fIcon
Ce0 f23 expr out #txt
Ce0 f23 712 288 785 288 #arcP
Ce0 f24 expr out #txt
Ce0 f24 528 288 600 288 #arcP
Ce0 f25 expr out #txt
Ce0 f25 352 288 416 288 #arcP
Ce0 f26 expr out #txt
Ce0 f26 111 288 176 288 #arcP
Ce0 f40 type ch.ivyteam.wf.processes.CaseServiceData #txt
Ce0 f40 777 465 30 30 0 15 #rect
Ce0 f40 @|EndSubIcon #fIcon
Ce0 f27 type ch.ivyteam.wf.processes.CaseServiceData #txt
Ce0 f27 processCall 'Functional Processes/ErrorHandler:handle(List<ch.ivy.addon.portalkit.ivydata.exception.PortalIvyDataException>)' #txt
Ce0 f27 doCall true #txt
Ce0 f27 requestActionDecl '<java.util.List<ch.ivy.addon.portalkit.ivydata.exception.PortalIvyDataException> exceptions> param;
' #txt
Ce0 f27 requestMappingAction 'param.exceptions=in.errors;
' #txt
Ce0 f27 responseActionDecl 'ch.ivyteam.wf.processes.CaseServiceData out;
' #txt
Ce0 f27 responseMappingAction 'out=in;
' #txt
Ce0 f27 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>ErrorHandler</name>
    </language>
</elementInfo>
' #txt
Ce0 f27 592 362 112 44 -35 -8 #rect
Ce0 f27 @|CallSubIcon #fIcon
Ce0 f28 type ch.ivyteam.wf.processes.CaseServiceData #txt
Ce0 f28 777 369 30 30 0 15 #rect
Ce0 f28 @|EndSubIcon #fIcon
Ce0 f45 actionDecl 'ch.ivyteam.wf.processes.CaseServiceData out;
' #txt
Ce0 f45 actionTable 'out=in;
' #txt
Ce0 f45 actionCode 'import ch.ivy.addon.portalkit.service.ApplicationService;

ApplicationService service = new ApplicationService();
out.caseCustomVarCharSearchCriteria.apps = service.findActiveIvyAppsBy(in.caseCustomVarCharSearchCriteria.apps);
' #txt
Ce0 f45 type ch.ivyteam.wf.processes.CaseServiceData #txt
Ce0 f45 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Find applications can work on</name>
    </language>
</elementInfo>
' #txt
Ce0 f45 168 554 176 44 -81 -8 #rect
Ce0 f45 @|StepIcon #fIcon
Ce0 f29 inParamDecl '<ch.ivy.addon.portalkit.ivydata.searchcriteria.CaseSearchCriteria caseSearchCriteria> param;' #txt
Ce0 f29 inParamTable 'out.caseSearchCriteria=param.caseSearchCriteria;
' #txt
Ce0 f29 outParamDecl '<java.util.List<ch.ivy.addon.portalkit.ivydata.exception.PortalIvyDataException> errors,ch.ivy.addon.portalkit.bo.CaseStateStatistic caseStateStatistic> result;
' #txt
Ce0 f29 outParamTable 'result.errors=in.errors;
result.caseStateStatistic=in.caseStateStatistic;
' #txt
Ce0 f29 actionDecl 'ch.ivyteam.wf.processes.CaseServiceData out;
' #txt
Ce0 f29 callSignature analyzeCaseStateStatistic(ch.ivy.addon.portalkit.ivydata.searchcriteria.CaseSearchCriteria) #txt
Ce0 f29 type ch.ivyteam.wf.processes.CaseServiceData #txt
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
Ce0 f39 outParamDecl '<java.util.List<ch.ivy.addon.portalkit.ivydata.exception.PortalIvyDataException> errors,ch.ivy.addon.portalkit.bo.ElapsedTimeStatistic elapsedTimeStatistic> result;
' #txt
Ce0 f39 outParamTable 'result.errors=in.errors;
result.elapsedTimeStatistic=in.elapsedTimeStatistic;
' #txt
Ce0 f39 actionDecl 'ch.ivyteam.wf.processes.CaseServiceData out;
' #txt
Ce0 f39 callSignature analyzeElapsedTimeByCaseCategory(ch.ivy.addon.portalkit.ivydata.searchcriteria.CaseSearchCriteria) #txt
Ce0 f39 type ch.ivyteam.wf.processes.CaseServiceData #txt
Ce0 f39 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>analyzeElapsedTimeByCaseCategory(CaseSearchCriteria)</name>
    </language>
</elementInfo>
' #txt
Ce0 f39 81 465 30 30 -68 21 #rect
Ce0 f39 @|StartSubIcon #fIcon
Ce0 f48 inParamDecl '<ch.ivy.addon.portalkit.ivydata.searchcriteria.CaseCustomVarCharSearchCriteria caseCustomVarCharSearchCriteria> param;' #txt
Ce0 f48 inParamTable 'out.caseCustomVarCharSearchCriteria=param.caseCustomVarCharSearchCriteria;
' #txt
Ce0 f48 outParamDecl '<java.util.List<ch.ivy.addon.portalkit.ivydata.exception.PortalIvyDataException> errors,java.util.List<java.lang.String> customVarChars> result;
' #txt
Ce0 f48 outParamTable 'result.errors=in.errors;
result.customVarChars=in.customVarChars;
' #txt
Ce0 f48 actionDecl 'ch.ivyteam.wf.processes.CaseServiceData out;
' #txt
Ce0 f48 callSignature findValuesOfCustomVarChar(ch.ivy.addon.portalkit.ivydata.searchcriteria.CaseCustomVarCharSearchCriteria) #txt
Ce0 f48 type ch.ivyteam.wf.processes.CaseServiceData #txt
Ce0 f48 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>findValuesOfCustomVarChar(CaseCustomVarCharSearchCriteria)</name>
    </language>
</elementInfo>
' #txt
Ce0 f48 81 561 30 30 -68 21 #rect
Ce0 f48 @|StartSubIcon #fIcon
Ce0 f30 type ch.ivyteam.wf.processes.CaseServiceData #txt
Ce0 f30 processCall 'Functional Processes/ErrorHandler:handle(List<ch.ivy.addon.portalkit.ivydata.exception.PortalIvyDataException>)' #txt
Ce0 f30 doCall true #txt
Ce0 f30 requestActionDecl '<java.util.List<ch.ivy.addon.portalkit.ivydata.exception.PortalIvyDataException> exceptions> param;
' #txt
Ce0 f30 requestMappingAction 'param.exceptions=in.errors;
' #txt
Ce0 f30 responseActionDecl 'ch.ivyteam.wf.processes.CaseServiceData out;
' #txt
Ce0 f30 responseMappingAction 'out=in;
' #txt
Ce0 f30 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>ErrorHandler</name>
    </language>
</elementInfo>
' #txt
Ce0 f30 592 458 112 44 -35 -8 #rect
Ce0 f30 @|CallSubIcon #fIcon
Ce0 f31 actionDecl 'ch.ivyteam.wf.processes.CaseServiceData out;
' #txt
Ce0 f31 actionTable 'out=in;
' #txt
Ce0 f31 actionCode 'import ch.ivy.addon.portalkit.service.ApplicationService;

ApplicationService service = new ApplicationService();
out.caseSearchCriteria.apps = service.findActiveIvyAppsBy(in.caseCategorySearchCriteria.apps);
' #txt
Ce0 f31 type ch.ivyteam.wf.processes.CaseServiceData #txt
Ce0 f31 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Find applications can work on</name>
    </language>
</elementInfo>
' #txt
Ce0 f31 168 362 176 44 -81 -8 #rect
Ce0 f31 @|StepIcon #fIcon
Ce0 f32 actionDecl 'ch.ivyteam.wf.processes.CaseServiceData out;
' #txt
Ce0 f32 actionTable 'out=in;
' #txt
Ce0 f32 actionCode 'import ch.ivy.addon.portalkit.service.ApplicationService;

ApplicationService service = new ApplicationService();
out.caseSearchCriteria.apps = service.findActiveIvyAppsBy(in.caseSearchCriteria.apps);
' #txt
Ce0 f32 type ch.ivyteam.wf.processes.CaseServiceData #txt
Ce0 f32 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Find applications can work on</name>
    </language>
</elementInfo>
' #txt
Ce0 f32 168 458 176 44 -81 -8 #rect
Ce0 f32 @|StepIcon #fIcon
Ce0 f46 actionDecl 'ch.ivyteam.wf.processes.CaseServiceData out;
' #txt
Ce0 f46 actionTable 'out=in;
' #txt
Ce0 f46 actionCode 'import ch.ivy.addon.portalkit.ivydata.dto.IvyCaseResultDTO;
import ch.ivy.addon.portalkit.ivydata.service.impl.CaseService;

IvyCaseResultDTO dto = CaseService.newInstance().findValuesOfCustomVarChar(in.caseCustomVarCharSearchCriteria);
out.customVarChars = dto.customVarChars;
out.errors = dto.errors;' #txt
Ce0 f46 type ch.ivyteam.wf.processes.CaseServiceData #txt
Ce0 f46 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Find custom varchars</name>
    </language>
</elementInfo>
' #txt
Ce0 f46 400 554 128 44 -59 -8 #rect
Ce0 f46 @|StepIcon #fIcon
Ce0 f33 actionDecl 'ch.ivyteam.wf.processes.CaseServiceData out;
' #txt
Ce0 f33 actionTable 'out=in;
' #txt
Ce0 f33 actionCode 'import ch.ivy.addon.portalkit.ivydata.dto.IvyCaseResultDTO;
import ch.ivy.addon.portalkit.ivydata.service.impl.CaseService;

IvyCaseResultDTO dto = CaseService.newInstance().analyzeElapsedTimeByCaseCategory(in.caseSearchCriteria);
out.elapsedTimeStatistic = dto.elapsedTimeStatistic;
out.errors = dto.errors;' #txt
Ce0 f33 type ch.ivyteam.wf.processes.CaseServiceData #txt
Ce0 f33 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Analyze elapsed time statistic</name>
    </language>
</elementInfo>
' #txt
Ce0 f33 376 458 176 44 -80 -8 #rect
Ce0 f33 @|StepIcon #fIcon
Ce0 f47 type ch.ivyteam.wf.processes.CaseServiceData #txt
Ce0 f47 777 561 30 30 0 15 #rect
Ce0 f47 @|EndSubIcon #fIcon
Ce0 f34 actionDecl 'ch.ivyteam.wf.processes.CaseServiceData out;
' #txt
Ce0 f34 actionTable 'out=in;
' #txt
Ce0 f34 actionCode 'import ch.ivy.addon.portalkit.ivydata.dto.IvyCaseResultDTO;
import ch.ivy.addon.portalkit.ivydata.service.impl.CaseService;

IvyCaseResultDTO dto = CaseService.newInstance().analyzeCaseStateStatistic(in.caseSearchCriteria);
out.caseStateStatistic = dto.caseStateStatistic;
out.errors = dto.errors;' #txt
Ce0 f34 type ch.ivyteam.wf.processes.CaseServiceData #txt
Ce0 f34 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Analyze case state statistic</name>
    </language>
</elementInfo>
' #txt
Ce0 f34 384 362 160 44 -73 -8 #rect
Ce0 f34 @|StepIcon #fIcon
Ce0 f49 type ch.ivyteam.wf.processes.CaseServiceData #txt
Ce0 f49 processCall 'Functional Processes/ErrorHandler:handle(List<ch.ivy.addon.portalkit.ivydata.exception.PortalIvyDataException>)' #txt
Ce0 f49 doCall true #txt
Ce0 f49 requestActionDecl '<java.util.List<ch.ivy.addon.portalkit.ivydata.exception.PortalIvyDataException> exceptions> param;
' #txt
Ce0 f49 requestMappingAction 'param.exceptions=in.errors;
' #txt
Ce0 f49 responseActionDecl 'ch.ivyteam.wf.processes.CaseServiceData out;
' #txt
Ce0 f49 responseMappingAction 'out=in;
' #txt
Ce0 f49 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>ErrorHandler</name>
    </language>
</elementInfo>
' #txt
Ce0 f49 592 554 112 44 -35 -8 #rect
Ce0 f49 @|CallSubIcon #fIcon
Ce0 f53 expr out #txt
Ce0 f53 704 576 777 576 #arcP
Ce0 f50 expr out #txt
Ce0 f50 111 576 168 576 #arcP
Ce0 f35 expr out #txt
Ce0 f35 344 384 384 384 #arcP
Ce0 f36 expr out #txt
Ce0 f36 704 384 777 384 #arcP
Ce0 f52 expr out #txt
Ce0 f52 528 576 592 576 #arcP
Ce0 f37 expr out #txt
Ce0 f37 111 384 168 384 #arcP
Ce0 f38 expr out #txt
Ce0 f38 544 384 592 384 #arcP
Ce0 f51 expr out #txt
Ce0 f51 344 576 400 576 #arcP
Ce0 f43 expr out #txt
Ce0 f43 111 480 168 480 #arcP
Ce0 f44 expr out #txt
Ce0 f44 552 480 592 480 #arcP
Ce0 f41 expr out #txt
Ce0 f41 344 480 376 480 #arcP
Ce0 f42 expr out #txt
Ce0 f42 704 480 777 480 #arcP
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
Ce0 f10 mainOut f7 tail #connect
Ce0 f7 head f6 mainIn #connect
Ce0 f6 mainOut f8 tail #connect
Ce0 f8 head f5 mainIn #connect
Ce0 f0 mainOut f2 tail #connect
Ce0 f2 head f10 mainIn #connect
Ce0 f5 mainOut f3 tail #connect
Ce0 f3 head f1 mainIn #connect
Ce0 f4 mainOut f14 tail #connect
Ce0 f14 head f11 mainIn #connect
Ce0 f11 mainOut f16 tail #connect
Ce0 f16 head f13 mainIn #connect
Ce0 f12 mainOut f17 tail #connect
Ce0 f17 head f4 mainIn #connect
Ce0 f13 mainOut f15 tail #connect
Ce0 f15 head f9 mainIn #connect
Ce0 f20 mainOut f25 tail #connect
Ce0 f25 head f22 mainIn #connect
Ce0 f22 mainOut f24 tail #connect
Ce0 f24 head f19 mainIn #connect
Ce0 f19 mainOut f23 tail #connect
Ce0 f23 head f21 mainIn #connect
Ce0 f18 mainOut f26 tail #connect
Ce0 f26 head f20 mainIn #connect
Ce0 f31 mainOut f35 tail #connect
Ce0 f35 head f34 mainIn #connect
Ce0 f34 mainOut f38 tail #connect
Ce0 f38 head f27 mainIn #connect
Ce0 f27 mainOut f36 tail #connect
Ce0 f36 head f28 mainIn #connect
Ce0 f29 mainOut f37 tail #connect
Ce0 f37 head f31 mainIn #connect
Ce0 f32 mainOut f41 tail #connect
Ce0 f41 head f33 mainIn #connect
Ce0 f33 mainOut f44 tail #connect
Ce0 f44 head f30 mainIn #connect
Ce0 f30 mainOut f42 tail #connect
Ce0 f42 head f40 mainIn #connect
Ce0 f39 mainOut f43 tail #connect
Ce0 f43 head f32 mainIn #connect
Ce0 f45 mainOut f51 tail #connect
Ce0 f51 head f46 mainIn #connect
Ce0 f46 mainOut f52 tail #connect
Ce0 f52 head f49 mainIn #connect
Ce0 f49 mainOut f53 tail #connect
Ce0 f53 head f47 mainIn #connect
Ce0 f48 mainOut f50 tail #connect
Ce0 f50 head f45 mainIn #connect
