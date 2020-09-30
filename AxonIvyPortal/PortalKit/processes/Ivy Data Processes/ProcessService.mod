[Ivy]
1473A12DE00609FB 9.2.0 #module
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
Pt0 @PushWFArc f0 '' #zField
Pt0 @PushWFArc f1 '' #zField
>Proto Pt0 Pt0 ProcessService #zField
Pt0 f15 inParamDecl '<> param;' #txt
Pt0 f15 outParamDecl '<java.util.List<ch.ivyteam.ivy.workflow.start.IWebStartable> processes> result;' #txt
Pt0 f15 outParamTable 'result.processes=in.processes;
' #txt
Pt0 f15 callSignature findProcesses() #txt
Pt0 f15 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>findProcesses()</name>
    </language>
</elementInfo>
' #txt
Pt0 f15 81 81 30 30 -53 17 #rect
Pt0 f15 @|StartSubIcon #fIcon
Pt0 f19 377 81 30 30 0 15 #rect
Pt0 f19 @|EndSubIcon #fIcon
Pt0 f24 actionTable 'out=in;
' #txt
Pt0 f24 actionCode 'import ch.ivy.addon.portalkit.ivydata.dto.IvyProcessResultDTO;
import ch.ivy.addon.portalkit.ivydata.service.impl.ProcessService;

IvyProcessResultDTO dto = ProcessService.newInstance().findProcesses();
out.processes = dto.processes;' #txt
Pt0 f24 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Find processes</name>
    </language>
</elementInfo>
' #txt
Pt0 f24 176 74 112 44 -43 -8 #rect
Pt0 f24 @|StepIcon #fIcon
Pt0 f0 expr out #txt
Pt0 f0 111 96 176 96 #arcP
Pt0 f1 expr out #txt
Pt0 f1 288 96 377 96 #arcP
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
Pt0 f15 mainOut f0 tail #connect
Pt0 f0 head f24 mainIn #connect
Pt0 f24 mainOut f1 tail #connect
Pt0 f1 head f19 mainIn #connect
