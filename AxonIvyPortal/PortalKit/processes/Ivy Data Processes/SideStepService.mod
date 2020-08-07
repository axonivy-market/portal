[Ivy]
167F3CD1442A57A4 7.5.0 #module
>Proto >Proto Collection #zClass
Se0 SideStepService Big #zClass
Se0 B #cInfo
Se0 #process
Se0 @TextInP .type .type #zField
Se0 @TextInP .processKind .processKind #zField
Se0 @AnnotationInP-0n ai ai #zField
Se0 @MessageFlowInP-0n messageIn messageIn #zField
Se0 @MessageFlowOutP-0n messageOut messageOut #zField
Se0 @TextInP .xml .xml #zField
Se0 @TextInP .responsibility .responsibility #zField
Se0 @StartSub f0 '' #zField
Se0 @EndSub f1 '' #zField
Se0 @GridStep f3 '' #zField
Se0 @PushWFArc f4 '' #zField
Se0 @PushWFArc f6 '' #zField
>Proto Se0 Se0 SideStepService #zField
Se0 f0 inParamDecl '<ch.ivy.addon.portalkit.ivydata.searchcriteria.SideStepSearchCriteria sideStepSearchCriteria> param;' #txt
Se0 f0 inParamTable 'out.sideStepSearchCriteria=param.sideStepSearchCriteria;
' #txt
Se0 f0 outParamDecl '<java.util.List<ch.ivy.addon.portalkit.ivydata.bo.IvySideStep> sideSteps> result;' #txt
Se0 f0 outParamTable 'result.sideSteps=in.sideSteps;
' #txt
Se0 f0 callSignature findSideStepsByCriteria(ch.ivy.addon.portalkit.ivydata.searchcriteria.SideStepSearchCriteria) #txt
Se0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>findSideStepsByCriteria(SideStepSearchCriteria)</name>
    </language>
</elementInfo>
' #txt
Se0 f0 81 49 30 30 -92 32 #rect
Se0 f0 @|StartSubIcon #fIcon
Se0 f1 433 49 30 30 0 15 #rect
Se0 f1 @|EndSubIcon #fIcon
Se0 f3 actionTable 'out=in;
' #txt
Se0 f3 actionCode 'import ch.ivy.addon.portalkit.ivydata.dto.IvySideStepResultDTO;
import ch.ivy.addon.portalkit.ivydata.service.impl.SideStepService;

IvySideStepResultDTO dto = SideStepService.newInstance().findSideStepsByCriteria(in.sideStepSearchCriteria);
out.sideSteps = dto.getSideSteps();' #txt
Se0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Find side steps</name>
    </language>
</elementInfo>
' #txt
Se0 f3 248 42 112 44 -42 -8 #rect
Se0 f3 @|StepIcon #fIcon
Se0 f4 expr out #txt
Se0 f4 111 64 248 64 #arcP
Se0 f6 expr out #txt
Se0 f6 360 64 433 64 #arcP
>Proto Se0 .type ch.ivyteam.wf.processes.SideStepServiceData #txt
>Proto Se0 .processKind CALLABLE_SUB #txt
>Proto Se0 0 0 32 24 18 0 #rect
>Proto Se0 @|BIcon #fIcon
Se0 f0 mainOut f4 tail #connect
Se0 f4 head f3 mainIn #connect
Se0 f3 mainOut f6 tail #connect
Se0 f6 head f1 mainIn #connect
