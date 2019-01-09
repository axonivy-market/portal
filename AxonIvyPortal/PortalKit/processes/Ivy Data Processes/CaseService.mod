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
Ce0 f4 176 202 176 44 -81 -8 #rect
Ce0 f4 @|StepIcon #fIcon
Ce0 f9 type ch.ivyteam.wf.processes.CaseServiceData #txt
Ce0 f9 785 209 30 30 0 15 #rect
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
Ce0 f11 416 202 112 44 -35 -8 #rect
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
Ce0 f12 81 209 30 30 -89 26 #rect
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
Ce0 f13 600 202 112 44 -35 -8 #rect
Ce0 f13 @|CallSubIcon #fIcon
Ce0 f14 expr out #txt
Ce0 f14 352 224 416 224 #arcP
Ce0 f15 expr out #txt
Ce0 f15 712 224 785 224 #arcP
Ce0 f16 expr out #txt
Ce0 f16 528 224 600 224 #arcP
Ce0 f17 expr out #txt
Ce0 f17 111 224 176 224 #arcP
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
Ce0 f18 81 337 30 30 -89 28 #rect
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
Ce0 f19 600 330 112 44 -35 -8 #rect
Ce0 f19 @|CallSubIcon #fIcon
Ce0 f20 actionDecl 'ch.ivyteam.wf.processes.CaseServiceData out;
' #txt
Ce0 f20 actionTable 'out=in;
' #txt
Ce0 f20 actionCode 'import ch.ivy.addon.portalkit.service.ApplicationService;

ApplicationService service = new ApplicationService();
out.caseCategorySearchCriteria.apps = service.findActiveIvyAppsBy(in.caseCategorySearchCriteria.apps);
' #txt
Ce0 f20 type ch.ivyteam.wf.processes.CaseServiceData #txt
Ce0 f20 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Find applications can work on</name>
    </language>
</elementInfo>
' #txt
Ce0 f20 176 330 176 44 -81 -8 #rect
Ce0 f20 @|StepIcon #fIcon
Ce0 f21 type ch.ivyteam.wf.processes.CaseServiceData #txt
Ce0 f21 785 337 30 30 0 15 #rect
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
Ce0 f22 416 330 112 44 -42 -8 #rect
Ce0 f22 @|StepIcon #fIcon
Ce0 f23 expr out #txt
Ce0 f23 712 352 785 352 #arcP
Ce0 f24 expr out #txt
Ce0 f24 528 352 600 352 #arcP
Ce0 f25 expr out #txt
Ce0 f25 352 352 416 352 #arcP
Ce0 f26 expr out #txt
Ce0 f26 111 352 176 352 #arcP
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
