[Ivy]
153358BE9219FD4C 3.28 #module
>Proto >Proto Collection #zClass
Cs0 CaseItemRelatedTaskProcess Big #zClass
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
Cs0 @PushWFArc f3 '' #zField
Cs0 @UdMethod f6 '' #zField
Cs0 @UdProcessEnd f7 '' #zField
Cs0 @Split f8 '' #zField
Cs0 @Join f9 '' #zField
Cs0 @PushWFArc f10 '' #zField
Cs0 @PushWFArc f12 '' #zField
Cs0 @GridStep f14 '' #zField
Cs0 @PushWFArc f15 '' #zField
Cs0 @SJArc f13 '' #zField
Cs0 @GridStep f17 '' #zField
Cs0 @PushWFArc f18 '' #zField
Cs0 @CallSub f19 '' #zField
Cs0 @PushWFArc f20 '' #zField
Cs0 @SJArc f21 '' #zField
Cs0 @GridStep f2 '' #zField
Cs0 @UdEvent f5 '' #zField
Cs0 @CallSub f4 '' #zField
Cs0 @PushWFArc f11 '' #zField
Cs0 @PushWFArc f16 '' #zField
Cs0 @UdEvent f22 '' #zField
Cs0 @CallSub f23 '' #zField
Cs0 @PushWFArc f24 '' #zField
Cs0 @UdEvent f25 '' #zField
Cs0 @CallSub f26 '' #zField
Cs0 @PushWFArc f27 '' #zField
>Proto Cs0 Cs0 CaseItemRelatedTaskProcess #zField
Cs0 f0 guid 167E9A75EF3D0909 #txt
Cs0 f0 method start() #txt
Cs0 f0 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<> param = methodEvent.getInputArguments();
' #txt
Cs0 f0 outParameterDecl '<> result;
' #txt
Cs0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start()</name>
    </language>
</elementInfo>
' #txt
Cs0 f0 83 83 26 26 -16 15 #rect
Cs0 f0 @|UdInitIcon #fIcon
Cs0 f1 275 83 26 26 0 12 #rect
Cs0 f1 @|UdProcessEndIcon #fIcon
Cs0 f3 expr out #txt
Cs0 f3 109 96 275 96 #arcP
Cs0 f6 guid 167E9A777AB171EA #txt
Cs0 f6 method initData(ch.ivyteam.ivy.workflow.ICase) #txt
Cs0 f6 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<ch.ivyteam.ivy.workflow.ICase iCase> param = methodEvent.getInputArguments();
' #txt
Cs0 f6 inParameterMapAction 'out.iCase=param.iCase;
' #txt
Cs0 f6 outParameterDecl '<> result;
' #txt
Cs0 f6 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>initData(ICase)</name>
    </language>
</elementInfo>
' #txt
Cs0 f6 83 179 26 26 -41 15 #rect
Cs0 f6 @|UdMethodIcon #fIcon
Cs0 f7 659 179 26 26 0 12 #rect
Cs0 f7 @|UdProcessEndIcon #fIcon
Cs0 f8 actionTable 'out1=in;
out1.iCase=in.iCase;
out2.iCase=in.iCase;
' #txt
Cs0 f8 176 176 32 32 0 16 #rect
Cs0 f8 @|ThreadIcon #fIcon
Cs0 f9 actionTable 'out=in1;
out.relatedTasks=in1.relatedTasks;
out.technicalCases=in2.technicalCases;
' #txt
Cs0 f9 560 176 32 32 0 16 #rect
Cs0 f9 @|JoinIcon #fIcon
Cs0 f10 expr out #txt
Cs0 f10 592 192 659 192 #arcP
Cs0 f12 expr out #txt
Cs0 f12 109 192 176 192 #arcP
Cs0 f14 actionTable 'out=in;
' #txt
Cs0 f14 actionCode 'import ch.ivy.addon.portalkit.util.HiddenTasksCasesConfig;
import org.apache.commons.lang3.StringUtils;
import ch.ivy.addon.portalkit.enums.AdditionalProperty;
import ch.ivyteam.ivy.workflow.TaskState;
import ch.ivyteam.ivy.workflow.ITask;

in.relatedTasks.clear();

int count = 1;
boolean excludeHiddenTasks = Boolean.parseBoolean(ivy.var.get(HiddenTasksCasesConfig.PORTAL_HIDDEN_TASK_CASE_EXCLUDED));
for (ITask task : in.iCase.getTasks()) {
	if ((task.getState() == TaskState.SUSPENDED || task.getState() == TaskState.RESUMED || task.getState() == TaskState.UNASSIGNED || task.getState() == TaskState.PARKED)
				&& (excludeHiddenTasks ? StringUtils.isEmpty(task.customFields().stringField(AdditionalProperty.HIDE.toString()).getOrNull()) : true)
				&& count <= 21) {//get only 21 tasks
		in.relatedTasks.add(task);
		count++;
	}
}' #txt
Cs0 f14 security system #txt
Cs0 f14 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Find related tasks</name>
    </language>
</elementInfo>
' #txt
Cs0 f14 336 170 112 44 -49 -8 #rect
Cs0 f14 @|StepIcon #fIcon
Cs0 f15 expr out1 #txt
Cs0 f15 208 192 336 192 #arcP
Cs0 f13 expr out #txt
Cs0 f13 type ch.ivy.addon.portalkit.component.CaseItemRelatedTask.CaseItemRelatedTaskData #txt
Cs0 f13 var in1 #txt
Cs0 f13 448 192 560 192 #arcP
Cs0 f17 actionTable 'out=in;
' #txt
Cs0 f17 actionCode 'out.caseSearchCriteria.businessCaseId = in.iCase.getId();
out.caseSearchCriteria.technicalCase = true;' #txt
Cs0 f17 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Prepare case&#xD;
search criteria</name>
    </language>
</elementInfo>
' #txt
Cs0 f17 240 266 112 44 -39 -16 #rect
Cs0 f17 @|StepIcon #fIcon
Cs0 f18 expr out2 #txt
Cs0 f18 192 208 240 288 #arcP
Cs0 f18 1 192 288 #addKink
Cs0 f18 1 0.08437593569205071 0 0 #arcLabel
Cs0 f19 processCall 'Ivy Data Processes/CaseService:findCasesByCriteria(ch.ivy.addon.portalkit.ivydata.searchcriteria.CaseSearchCriteria,Integer,Integer)' #txt
Cs0 f19 requestActionDecl '<ch.ivy.addon.portalkit.ivydata.searchcriteria.CaseSearchCriteria caseSearchCriteria,java.lang.Integer startIndex,java.lang.Integer count> param;
' #txt
Cs0 f19 requestMappingAction 'param.caseSearchCriteria=in.caseSearchCriteria;
param.startIndex=0;
param.count=21;
' #txt
Cs0 f19 responseActionDecl 'ch.ivy.addon.portalkit.component.CaseItemRelatedTask.CaseItemRelatedTaskData out;
' #txt
Cs0 f19 responseMappingAction 'out=in;
out.technicalCases=result.cases;
' #txt
Cs0 f19 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>CaseService</name>
    </language>
</elementInfo>
' #txt
Cs0 f19 408 266 112 44 -35 -8 #rect
Cs0 f19 @|CallSubIcon #fIcon
Cs0 f20 expr out #txt
Cs0 f20 352 288 408 288 #arcP
Cs0 f21 expr out #txt
Cs0 f21 type ch.ivy.addon.portalkit.component.CaseItemRelatedTask.CaseItemRelatedTaskData #txt
Cs0 f21 var in2 #txt
Cs0 f21 520 288 576 208 #arcP
Cs0 f21 1 576 288 #addKink
Cs0 f21 0 0.8623545764087797 0 0 #arcLabel
Cs0 f2 actionTable 'out=in;
' #txt
Cs0 f2 actionCode 'import ch.ivy.addon.portalkit.util.MenuUtils;

MenuUtils.clearMenuState();' #txt
Cs0 f2 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Clear menu state</name>
    </language>
</elementInfo>
' #txt
Cs0 f2 168 362 112 44 -47 -8 #rect
Cs0 f2 @|StepIcon #fIcon
Cs0 f5 guid 1682B962CA36E3CC #txt
Cs0 f5 actionTable 'out=in;
' #txt
Cs0 f5 actionCode 'import org.primefaces.component.commandlink.CommandLink;
CommandLink commandLink = event.getSource() as CommandLink;
in.taskId = commandLink.getAttributes().get("taskId") as Long;' #txt
Cs0 f5 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>openPortalTasks</name>
    </language>
</elementInfo>
' #txt
Cs0 f5 83 371 26 26 -47 15 #rect
Cs0 f5 @|UdEventIcon #fIcon
Cs0 f4 processCall 'Functional Processes/Navigator:viewTask(Long,ch.ivy.addon.portalkit.dto.GlobalCaseId,String)' #txt
Cs0 f4 requestActionDecl '<java.lang.Long taskId,ch.ivy.addon.portalkit.dto.GlobalCaseId caseId,java.lang.String caseName> param;
' #txt
Cs0 f4 requestMappingAction 'param.taskId=in.taskId;
param.caseId=ch.ivy.addon.portalkit.dto.GlobalCaseId.caseId(in.iCase.getId()).isBusinessCase(in.iCase.isBusinessCase()).build();
param.caseName=ch.ivy.addon.portalkit.util.PermissionUtils.getCaseName(in.iCase);
' #txt
Cs0 f4 responseActionDecl 'ch.ivy.addon.portalkit.component.CaseItemRelatedTask.CaseItemRelatedTaskData out;
' #txt
Cs0 f4 responseMappingAction 'out=in;
' #txt
Cs0 f4 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Navigate to task detail</name>
    </language>
</elementInfo>
' #txt
Cs0 f4 320 362 128 44 -60 -8 #rect
Cs0 f4 @|CallSubIcon #fIcon
Cs0 f11 expr out #txt
Cs0 f11 280 384 320 384 #arcP
Cs0 f16 expr out #txt
Cs0 f16 109 384 168 384 #arcP
Cs0 f22 guid 1682B96F9503687C #txt
Cs0 f22 actionTable 'out=in;
' #txt
Cs0 f22 actionCode 'import ch.ivyteam.ivy.workflow.ICase;
import org.primefaces.component.commandlink.CommandLink;

CommandLink commandLink = event.getSource() as CommandLink;
out.selectedTechnicalCase = commandLink.getAttributes().get("selectedCase") as ICase;' #txt
Cs0 f22 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>navigateToRelatedCase</name>
    </language>
</elementInfo>
' #txt
Cs0 f22 83 467 26 26 -47 15 #rect
Cs0 f22 @|UdEventIcon #fIcon
Cs0 f23 processCall 'Functional Processes/Navigator:viewCase(String,ch.ivy.addon.portalkit.dto.GlobalCaseId)' #txt
Cs0 f23 requestActionDecl '<java.lang.String caseName,ch.ivy.addon.portalkit.dto.GlobalCaseId caseId> param;
' #txt
Cs0 f23 requestMappingAction 'param.caseName=ch.ivy.addon.portalkit.util.PermissionUtils.getCaseName(in.selectedTechnicalCase);
param.caseId=ch.ivy.addon.portalkit.dto.GlobalCaseId.caseId(in.selectedTechnicalCase.getId()).isBusinessCase(in.selectedTechnicalCase.isBusinessCase()).build();
' #txt
Cs0 f23 responseActionDecl 'ch.ivy.addon.portalkit.component.CaseItemRelatedTask.CaseItemRelatedTaskData out;
' #txt
Cs0 f23 responseMappingAction 'out=in;
' #txt
Cs0 f23 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Navigate to case detail</name>
    </language>
</elementInfo>
' #txt
Cs0 f23 192 458 128 44 -61 -8 #rect
Cs0 f23 @|CallSubIcon #fIcon
Cs0 f24 expr out #txt
Cs0 f24 109 480 192 480 #arcP
Cs0 f25 guid 1690E16A5D3AD46C #txt
Cs0 f25 actionTable 'out=in;
' #txt
Cs0 f25 actionCode 'import ch.ivyteam.ivy.workflow.ICase;
import org.primefaces.component.commandlink.CommandLink;

CommandLink commandLink = event.getSource() as CommandLink;
out.selectedTechnicalCase = commandLink.getAttributes().get("selectedCase") as ICase;' #txt
Cs0 f25 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>navigateToTechnicalCasesOfBusinessCase</name>
    </language>
</elementInfo>
' #txt
Cs0 f25 83 563 26 26 -88 17 #rect
Cs0 f25 @|UdEventIcon #fIcon
Cs0 f26 processCall 'Functional Processes/Navigator:viewTechnicalCasesOfBusniessCase(String,ch.ivy.addon.portalkit.dto.GlobalCaseId)' #txt
Cs0 f26 requestActionDecl '<java.lang.String businessCaseName,ch.ivy.addon.portalkit.dto.GlobalCaseId businessCaseId> param;
' #txt
Cs0 f26 requestMappingAction 'param.businessCaseName=ch.ivy.addon.portalkit.util.PermissionUtils.getCaseName(in.selectedTechnicalCase);
param.businessCaseId=ch.ivy.addon.portalkit.dto.GlobalCaseId.caseId(in.selectedTechnicalCase.getId()).isBusinessCase(in.selectedTechnicalCase.isBusinessCase()).build();
' #txt
Cs0 f26 responseActionDecl 'ch.ivy.addon.portalkit.component.CaseItemRelatedTask.CaseItemRelatedTaskData out;
' #txt
Cs0 f26 responseMappingAction 'out=in;
' #txt
Cs0 f26 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Navigate to case detail</name>
    </language>
</elementInfo>
' #txt
Cs0 f26 320 554 128 44 -61 -8 #rect
Cs0 f26 @|CallSubIcon #fIcon
Cs0 f27 expr out #txt
Cs0 f27 109 576 320 576 #arcP
>Proto Cs0 .type ch.ivy.addon.portalkit.component.CaseItemRelatedTask.CaseItemRelatedTaskData #txt
>Proto Cs0 .processKind HTML_DIALOG #txt
>Proto Cs0 -8 -8 16 16 16 26 #rect
>Proto Cs0 '' #fIcon
Cs0 f0 mainOut f3 tail #connect
Cs0 f3 head f1 mainIn #connect
Cs0 f9 mainOut f10 tail #connect
Cs0 f10 head f7 mainIn #connect
Cs0 f6 mainOut f12 tail #connect
Cs0 f12 head f8 in #connect
Cs0 f8 out f15 tail #connect
Cs0 f15 head f14 mainIn #connect
Cs0 f14 mainOut f13 tail #connect
Cs0 f13 head f9 in #connect
Cs0 f8 out f18 tail #connect
Cs0 f18 head f17 mainIn #connect
Cs0 f17 mainOut f20 tail #connect
Cs0 f20 head f19 mainIn #connect
Cs0 f19 mainOut f21 tail #connect
Cs0 f21 head f9 in #connect
Cs0 f5 mainOut f16 tail #connect
Cs0 f16 head f2 mainIn #connect
Cs0 f2 mainOut f11 tail #connect
Cs0 f11 head f4 mainIn #connect
Cs0 f22 mainOut f24 tail #connect
Cs0 f24 head f23 mainIn #connect
Cs0 f25 mainOut f27 tail #connect
Cs0 f27 head f26 mainIn #connect
