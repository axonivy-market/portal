[Ivy]
18104AE3EB4807A1 7.5.0 #module
>Proto >Proto Collection #zClass
Ce0 PortalComponentCaseService Big #zClass
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
Ce0 @CallSub f5 '' #zField
Ce0 @PushWFArc f8 '' #zField
Ce0 @PushWFArc f3 '' #zField
Ce0 @PushWFArc f54 '' #zField
Ce0 @EndSub f9 '' #zField
Ce0 @GridStep f11 '' #zField
Ce0 @StartSub f12 '' #zField
Ce0 @CallSub f13 '' #zField
Ce0 @PushWFArc f15 '' #zField
Ce0 @PushWFArc f2 '' #zField
Ce0 @PushWFArc f16 '' #zField
>Proto Ce0 Ce0 PortalComponentCaseService #zField
Ce0 f0 inParamDecl '<com.axonivy.portal.components.ivydata.searchcriteria.CaseSearchCriteria caseSearchCriteria,Integer startIndex,Integer count> param;' #txt
Ce0 f0 inParamTable 'out.caseSearchCriteria=param.caseSearchCriteria;
out.count=param.count;
out.startIndex=param.startIndex;
' #txt
Ce0 f0 outParamDecl '<java.util.List<com.axonivy.portal.components.ivydata.exception.PortalIvyDataException> errors,java.util.List<ch.ivyteam.ivy.workflow.ICase> cases> result;' #txt
Ce0 f0 outParamTable 'result.errors=in.errors;
result.cases=in.cases;
' #txt
Ce0 f0 callSignature findCasesByCriteria(com.axonivy.portal.components.ivydata.searchcriteria.CaseSearchCriteria,Integer,Integer) #txt
Ce0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>findCasesByCriteria(CaseSearchCriteria,Integer,Integer)</name>
    </language>
</elementInfo>
' #txt
Ce0 f0 81 81 30 30 -89 26 #rect
Ce0 f0 @|StartSubIcon #fIcon
Ce0 f1 561 81 30 30 0 15 #rect
Ce0 f1 @|EndSubIcon #fIcon
Ce0 f6 actionTable 'out=in;
' #txt
Ce0 f6 actionCode 'import com.axonivy.portal.components.ivydata.service.impl.CaseService;
import com.axonivy.portal.components.ivydata.dto.IvyCaseResultDTO;

IvyCaseResultDTO dto = CaseService.newInstance().findCasesByCriteria(in.caseSearchCriteria, in.startIndex, in.count);
out.cases = dto.cases;
out.errors = dto.errors;' #txt
Ce0 f6 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Find cases</name>
    </language>
</elementInfo>
' #txt
Ce0 f6 192 74 112 44 -30 -8 #rect
Ce0 f6 @|StepIcon #fIcon
Ce0 f5 processCall 'Functional Processes/PortalComponentErrorHandler:handle(java.util.List<com.axonivy.portal.components.ivydata.exception.PortalIvyDataException>)' #txt
Ce0 f5 requestActionDecl '<java.util.List<com.axonivy.portal.components.ivydata.exception.PortalIvyDataException> exceptions> param;' #txt
Ce0 f5 requestMappingAction 'param.exceptions=in.errors;
' #txt
Ce0 f5 responseActionDecl 'com.axonivy.portal.components.service.PortalConponentCaseServiceData out;
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
Ce0 f5 376 74 112 44 -35 -8 #rect
Ce0 f5 @|CallSubIcon #fIcon
Ce0 f8 expr out #txt
Ce0 f8 304 96 376 96 #arcP
Ce0 f3 expr out #txt
Ce0 f3 488 96 561 96 #arcP
Ce0 f54 expr out #txt
Ce0 f54 111 96 192 96 #arcP
Ce0 f9 561 185 30 30 0 15 #rect
Ce0 f9 @|EndSubIcon #fIcon
Ce0 f11 actionTable 'out=in;
' #txt
Ce0 f11 actionCode 'import com.axonivy.portal.components.ivydata.service.impl.CaseService;
import com.axonivy.portal.components.ivydata.dto.IvyCaseResultDTO;

IvyCaseResultDTO dto = CaseService.newInstance().countCasesByCriteria(in.caseSearchCriteria);
out.totalCases = dto.totalCases;
out.errors = dto.errors;' #txt
Ce0 f11 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Count cases</name>
    </language>
</elementInfo>
' #txt
Ce0 f11 192 178 112 44 -35 -8 #rect
Ce0 f11 @|StepIcon #fIcon
Ce0 f12 inParamDecl '<com.axonivy.portal.components.ivydata.searchcriteria.CaseSearchCriteria caseSearchCriteria> param;' #txt
Ce0 f12 inParamTable 'out.caseSearchCriteria=param.caseSearchCriteria;
' #txt
Ce0 f12 outParamDecl '<java.util.List<com.axonivy.portal.components.ivydata.exception.PortalIvyDataException> errors,Long totalCases> result;' #txt
Ce0 f12 outParamTable 'result.errors=in.errors;
result.totalCases=in.totalCases;
' #txt
Ce0 f12 callSignature countCasesByCriteria(com.axonivy.portal.components.ivydata.searchcriteria.CaseSearchCriteria) #txt
Ce0 f12 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>countCasesByCriteria(CaseSearchCriteria)</name>
    </language>
</elementInfo>
' #txt
Ce0 f12 81 185 30 30 -89 26 #rect
Ce0 f12 @|StartSubIcon #fIcon
Ce0 f13 processCall 'Functional Processes/PortalComponentErrorHandler:handle(java.util.List<com.axonivy.portal.components.ivydata.exception.PortalIvyDataException>)' #txt
Ce0 f13 requestActionDecl '<java.util.List<com.axonivy.portal.components.ivydata.exception.PortalIvyDataException> exceptions> param;' #txt
Ce0 f13 requestMappingAction 'param.exceptions=in.errors;
' #txt
Ce0 f13 responseActionDecl 'com.axonivy.portal.components.service.PortalConponentCaseServiceData out;
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
Ce0 f13 376 178 112 44 -35 -8 #rect
Ce0 f13 @|CallSubIcon #fIcon
Ce0 f15 expr out #txt
Ce0 f15 488 200 561 200 #arcP
Ce0 f2 expr out #txt
Ce0 f2 111 200 192 200 #arcP
Ce0 f16 expr out #txt
Ce0 f16 304 200 376 200 #arcP
>Proto Ce0 .type com.axonivy.portal.components.service.PortalConponentCaseServiceData #txt
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
Ce0 f6 mainOut f8 tail #connect
Ce0 f8 head f5 mainIn #connect
Ce0 f5 mainOut f3 tail #connect
Ce0 f3 head f1 mainIn #connect
Ce0 f0 mainOut f54 tail #connect
Ce0 f54 head f6 mainIn #connect
Ce0 f11 mainOut f16 tail #connect
Ce0 f16 head f13 mainIn #connect
Ce0 f13 mainOut f15 tail #connect
Ce0 f15 head f9 mainIn #connect
Ce0 f12 mainOut f2 tail #connect
Ce0 f2 head f11 mainIn #connect
