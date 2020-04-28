[Ivy]
1473A12DE00609FB 7.5.0 #module
>Proto >Proto Collection #zClass
Pt0 ProcessService Big #zClass
Pt0 B #cInfo
Pt0 #process
Pt0 @TextInP .type .type #zField
Pt0 @TextInP .processKind .processKind #zField
Pt0 @AnnotationInP-0n ai ai #zField
Pt0 @TextInP .xml .xml #zField
Pt0 @TextInP .responsibility .responsibility #zField
Pt0 @StartSub f15 '' #zField
Pt0 @EndSub f19 '' #zField
Pt0 @GridStep f24 '' #zField
Pt0 @CallSub f26 '' #zField
Pt0 @PushWFArc f27 '' #zField
Pt0 @PushWFArc f23 '' #zField
Pt0 @PushWFArc f0 '' #zField
>Proto Pt0 Pt0 ProcessService #zField
Pt0 f15 inParamDecl '<ch.ivy.addon.portalkit.ivydata.searchcriteria.ProcessSearchCriteria processSearchCriteria> param;' #txt
Pt0 f15 inParamTable 'out.processSearchCriteria=param.processSearchCriteria;
' #txt
Pt0 f15 outParamDecl '<java.util.List<ch.ivyteam.ivy.workflow.start.IWebStartable> processes,java.util.List<ch.ivy.addon.portalkit.ivydata.exception.PortalIvyDataException> errors> result;' #txt
Pt0 f15 outParamTable 'result.processes=in.processes;
result.errors=in.errors;
' #txt
Pt0 f15 callSignature findProcesses(ch.ivy.addon.portalkit.ivydata.searchcriteria.ProcessSearchCriteria) #txt
Pt0 f15 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>findProcesses(ProcessSearchCriteria)</name>
    </language>
</elementInfo>
' #txt
Pt0 f15 81 81 30 30 -80 19 #rect
Pt0 f15 @|StartSubIcon #fIcon
Pt0 f19 513 81 30 30 0 15 #rect
Pt0 f19 @|EndSubIcon #fIcon
Pt0 f24 actionTable 'out=in;
' #txt
Pt0 f24 actionCode 'import ch.ivy.addon.portalkit.ivydata.dto.IvyProcessResultDTO;
import ch.ivy.addon.portalkit.ivydata.service.impl.ProcessService;

IvyProcessResultDTO dto = ProcessService.newInstance().findProcesses(in.processSearchCriteria);
out.processes = dto.processes;
out.errors = dto.errors;' #txt
Pt0 f24 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Find processes</name>
    </language>
</elementInfo>
' #txt
Pt0 f24 176 74 112 44 -43 -8 #rect
Pt0 f24 @|StepIcon #fIcon
Pt0 f26 processCall 'Functional Processes/ErrorHandler:handle(List<ch.ivy.addon.portalkit.ivydata.exception.PortalIvyDataException>)' #txt
Pt0 f26 requestActionDecl '<java.util.List<ch.ivy.addon.portalkit.ivydata.exception.PortalIvyDataException> exceptions> param;' #txt
Pt0 f26 requestMappingAction 'param.exceptions=in.errors;
' #txt
Pt0 f26 responseActionDecl 'ch.ivyteam.wf.processes.ProcessServiceData out;
' #txt
Pt0 f26 responseMappingAction 'out=in;
' #txt
Pt0 f26 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>ErrorHandler</name>
    </language>
</elementInfo>
' #txt
Pt0 f26 352 74 112 44 -35 -8 #rect
Pt0 f26 @|CallSubIcon #fIcon
Pt0 f27 expr out #txt
Pt0 f27 288 96 352 96 #arcP
Pt0 f23 expr out #txt
Pt0 f23 464 96 513 96 #arcP
Pt0 f0 expr out #txt
Pt0 f0 111 96 176 96 #arcP
>Proto Pt0 .type ch.ivyteam.wf.processes.ProcessServiceData #txt
>Proto Pt0 .processKind CALLABLE_SUB #txt
>Proto Pt0 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <swimlaneLabel></swimlaneLabel>
        <swimlaneLabel></swimlaneLabel>
    </language>
    <swimlaneSize>1024</swimlaneSize>
    <swimlaneColor>-6697729</swimlaneColor>
</elementInfo>
' #txt
>Proto Pt0 0 0 32 24 18 0 #rect
>Proto Pt0 @|BIcon #fIcon
Pt0 f24 mainOut f27 tail #connect
Pt0 f27 head f26 mainIn #connect
Pt0 f26 mainOut f23 tail #connect
Pt0 f23 head f19 mainIn #connect
Pt0 f15 mainOut f0 tail #connect
Pt0 f0 head f24 mainIn #connect
