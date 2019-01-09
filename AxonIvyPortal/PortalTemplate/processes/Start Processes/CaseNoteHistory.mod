[Ivy]
1603506A872272C6 3.23 #module
>Proto >Proto Collection #zClass
Cy0 CaseNoteHistory Big #zClass
Cy0 B #cInfo
Cy0 #process
Cy0 @TextInP .resExport .resExport #zField
Cy0 @TextInP .type .type #zField
Cy0 @TextInP .processKind .processKind #zField
Cy0 @AnnotationInP-0n ai ai #zField
Cy0 @MessageFlowInP-0n messageIn messageIn #zField
Cy0 @MessageFlowOutP-0n messageOut messageOut #zField
Cy0 @TextInP .xml .xml #zField
Cy0 @TextInP .responsibility .responsibility #zField
Cy0 @StartRequest f15 '' #zField
Cy0 @GridStep f17 '' #zField
Cy0 @PushWFArc f18 '' #zField
Cy0 @EndTask f16 '' #zField
Cy0 @RichDialog f20 '' #zField
Cy0 @PushWFArc f21 '' #zField
Cy0 @PushWFArc f19 '' #zField
>Proto Cy0 Cy0 CaseNoteHistory #zField
Cy0 f15 outLink showCaseNoteHistory.ivp #txt
Cy0 f15 type ch.ivy.addon.portal.generic.CaseNoteHistoryData #txt
Cy0 f15 inParamDecl '<java.lang.Long caseId> param;' #txt
Cy0 f15 inParamTable 'out.caseId=param.caseId;
' #txt
Cy0 f15 actionDecl 'ch.ivy.addon.portal.generic.CaseNoteHistoryData out;
' #txt
Cy0 f15 guid 16048E4424ED1B60 #txt
Cy0 f15 requestEnabled true #txt
Cy0 f15 triggerEnabled false #txt
Cy0 f15 callSignature showCaseNoteHistory(Long) #txt
Cy0 f15 persist false #txt
Cy0 f15 taskData 'TaskTriggered.ROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.EXPRI=2
TaskTriggered.TYPE=0
TaskTriggered.PRI=2
TaskTriggered.EXROL=Everybody' #txt
Cy0 f15 caseData businessCase.attach=true #txt
Cy0 f15 showInStartList 0 #txt
Cy0 f15 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>showCaseNoteHistory.ivp</name>
        <nameStyle>23,5
</nameStyle>
    </language>
</elementInfo>
' #txt
Cy0 f15 @C|.responsibility Everybody #txt
Cy0 f15 81 81 30 30 -61 20 #rect
Cy0 f15 @|StartRequestIcon #fIcon
Cy0 f17 actionDecl 'ch.ivy.addon.portal.generic.CaseNoteHistoryData out;
' #txt
Cy0 f17 actionTable 'out=in;
' #txt
Cy0 f17 actionCode 'import ch.ivy.addon.portalkit.service.GlobalSettingService;
import java.util.ArrayList;
import ch.ivyteam.ivy.workflow.TaskState;
import ch.ivyteam.ivy.workflow.ITask;
import ch.ivy.addon.portalkit.service.HistoryService;

List<ITask> finishedTasks = new ArrayList();
in.internalCase = ivy.wf.findCase(in.caseId);
for(ITask task : in.internalCase.getTasks()) {
	if(task.getState() == TaskState.DONE 
	|| task.getState() == TaskState.DESTROYED 
	|| task.getState() == TaskState.ZOMBIE) {
		finishedTasks.add(task);
	}
}

HistoryService historyService = new HistoryService();
GlobalSettingService globalSettingService = new GlobalSettingService();
boolean excludeTechnicalHistory = globalSettingService.findHideSystemTasksFromHistorySettingValue();
in.histories = historyService.getHistories(finishedTasks, in.internalCase.getNotes(), excludeTechnicalHistory);' #txt
Cy0 f17 security system #txt
Cy0 f17 type ch.ivy.addon.portal.generic.CaseNoteHistoryData #txt
Cy0 f17 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>get case
create histories 
from tasks and notes</name>
        <nameStyle>47,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cy0 f17 216 66 144 60 -53 -24 #rect
Cy0 f17 @|StepIcon #fIcon
Cy0 f18 expr out #txt
Cy0 f18 111 96 216 96 #arcP
Cy0 f16 type ch.ivy.addon.portal.generic.CaseNoteHistoryData #txt
Cy0 f16 593 81 30 30 0 15 #rect
Cy0 f16 @|EndIcon #fIcon
Cy0 f20 targetWindow NEW #txt
Cy0 f20 targetDisplay TOP #txt
Cy0 f20 richDialogId ch.ivy.addon.portal.generic.CaseNoteHistory #txt
Cy0 f20 startMethod start(java.util.List<ch.ivy.addon.portalkit.bo.History>,String,ch.ivyteam.ivy.workflow.ICase) #txt
Cy0 f20 type ch.ivy.addon.portal.generic.CaseNoteHistoryData #txt
Cy0 f20 requestActionDecl '<java.util.List<ch.ivy.addon.portalkit.bo.History> histories, String exportedFileName, ch.ivyteam.ivy.workflow.ICase ivyCase> param;' #txt
Cy0 f20 requestMappingAction 'param.histories=in.histories;
param.exportedFileName=ivy.cms.co("/ch.ivy.addon.portalkit.ui.jsf/noteHistory/caseExportedFileNamePrefix", java.util.Arrays.asList(in.internalCase.name));
param.ivyCase=in.internalCase;
' #txt
Cy0 f20 responseActionDecl 'ch.ivy.addon.portal.generic.CaseNoteHistoryData out;
' #txt
Cy0 f20 responseMappingAction 'out=in;
' #txt
Cy0 f20 isAsynch false #txt
Cy0 f20 isInnerRd false #txt
Cy0 f20 userContext '* ' #txt
Cy0 f20 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Case note history</name>
        <nameStyle>17,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cy0 f20 424 74 112 44 -48 -8 #rect
Cy0 f20 @|RichDialogIcon #fIcon
Cy0 f21 expr out #txt
Cy0 f21 360 96 424 96 #arcP
Cy0 f19 expr out #txt
Cy0 f19 536 96 593 96 #arcP
>Proto Cy0 .type ch.ivy.addon.portal.generic.CaseNoteHistoryData #txt
>Proto Cy0 .processKind NORMAL #txt
>Proto Cy0 0 0 32 24 18 0 #rect
>Proto Cy0 @|BIcon #fIcon
Cy0 f15 mainOut f18 tail #connect
Cy0 f18 head f17 mainIn #connect
Cy0 f17 mainOut f21 tail #connect
Cy0 f21 head f20 mainIn #connect
Cy0 f20 mainOut f19 tail #connect
Cy0 f19 head f16 mainIn #connect
