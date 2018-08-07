[Ivy]
[>Created: Thu Dec 15 11:12:30 ICT 2016]
153A880B20F8280D 3.18 #module
>Proto >Proto Collection #zClass
Cs0 CaseItemRelatedTasksProcess Big #zClass
Cs0 RD #cInfo
Cs0 #process
Cs0 @TextInP .ui2RdDataAction .ui2RdDataAction #zField
Cs0 @TextInP .rdData2UIAction .rdData2UIAction #zField
Cs0 @TextInP .resExport .resExport #zField
Cs0 @TextInP .type .type #zField
Cs0 @TextInP .processKind .processKind #zField
Cs0 @AnnotationInP-0n ai ai #zField
Cs0 @MessageFlowInP-0n messageIn messageIn #zField
Cs0 @MessageFlowOutP-0n messageOut messageOut #zField
Cs0 @TextInP .xml .xml #zField
Cs0 @TextInP .responsibility .responsibility #zField
Cs0 @RichDialogInitStart f0 '' #zField
Cs0 @RichDialogProcessEnd f1 '' #zField
Cs0 @GridStep f9 '' #zField
Cs0 @GridStep f78 '' #zField
Cs0 @PushWFArc f10 '' #zField
Cs0 @PushWFArc f3 '' #zField
Cs0 @PushWFArc f2 '' #zField
Cs0 @RichDialogProcessStart f4 '' #zField
Cs0 @CallSub f5 '' #zField
Cs0 @GridStep f23 '' #zField
Cs0 @PushWFArc f7 '' #zField
Cs0 @PushWFArc f6 '' #zField
>Proto Cs0 Cs0 CaseItemRelatedTasksProcess #zField
Cs0 f0 guid 153358BE9322E3ED #txt
Cs0 f0 type ch.ivy.addon.portalkit.singleapp.cases.CaseItemRelatedTasks.CaseItemRelatedTasksData #txt
Cs0 f0 method start(Long) #txt
Cs0 f0 disableUIEvents true #txt
Cs0 f0 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<java.lang.Long caseId> param = methodEvent.getInputArguments();
' #txt
Cs0 f0 inParameterMapAction 'out.caseId=param.caseId;
' #txt
Cs0 f0 outParameterDecl '<> result;
' #txt
Cs0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start(Long)</name>
        <nameStyle>11,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f0 53 85 22 22 14 0 #rect
Cs0 f0 @|RichDialogInitStartIcon #fIcon
Cs0 f1 type ch.ivy.addon.portalkit.singleapp.cases.CaseItemRelatedTasks.CaseItemRelatedTasksData #txt
Cs0 f1 53 293 22 22 14 0 #rect
Cs0 f1 @|RichDialogProcessEndIcon #fIcon
Cs0 f9 actionDecl 'ch.ivy.addon.portalkit.singleapp.cases.CaseItemRelatedTasks.CaseItemRelatedTasksData out;
' #txt
Cs0 f9 actionTable 'out=in;
' #txt
Cs0 f9 actionCode 'import ch.ivy.addon.portalkit.service.ServerWorkingOnDetector;

ServerWorkingOnDetector detector = new ServerWorkingOnDetector();
in.currentServer = detector.getServerWorkingOn();' #txt
Cs0 f9 security system #txt
Cs0 f9 type ch.ivy.addon.portalkit.singleapp.cases.CaseItemRelatedTasks.CaseItemRelatedTasksData #txt
Cs0 f9 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>find current server</name>
        <nameStyle>19,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f9 46 212 36 24 20 -9 #rect
Cs0 f9 @|StepIcon #fIcon
Cs0 f78 actionDecl 'ch.ivy.addon.portalkit.singleapp.cases.CaseItemRelatedTasks.CaseItemRelatedTasksData out;
' #txt
Cs0 f78 actionTable 'out=in;
' #txt
Cs0 f78 actionCode 'import ch.ivyteam.ivy.workflow.ICase;
import java.util.ArrayList;
import ch.ivyteam.ivy.workflow.TaskState;
import ch.ivyteam.ivy.workflow.ITask;
import ch.ivy.addon.portalkit.service.HistoryService;

in.internalCase = ivy.wf.findCase(in.caseId);
List<ITask> relatedTasks = new ArrayList();
for(ITask task : in.internalCase.getTasks()) {
	if (task.getState() == TaskState.SUSPENDED 
		|| task.getState() == TaskState.RESUMED 
		|| task.getState() == TaskState.UNASSIGNED
		|| task.getState() == TaskState.PARKED) {
		relatedTasks.add(task);
	}
}
in.relatedTasks.clear();
in.relatedTasks.addAll(relatedTasks);' #txt
Cs0 f78 security system #txt
Cs0 f78 type ch.ivy.addon.portalkit.singleapp.cases.CaseItemRelatedTasks.CaseItemRelatedTasksData #txt
Cs0 f78 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>create histories 
from tasks and notes</name>
        <nameStyle>38,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f78 46 148 36 24 22 -16 #rect
Cs0 f78 @|StepIcon #fIcon
Cs0 f10 expr out #txt
Cs0 f10 64 172 64 212 #arcP
Cs0 f3 expr out #txt
Cs0 f3 64 236 64 293 #arcP
Cs0 f3 0 0.4728540288115568 0 0 #arcLabel
Cs0 f2 expr out #txt
Cs0 f2 64 107 64 148 #arcP
Cs0 f4 guid 154C1D46795C49B5 #txt
Cs0 f4 type ch.ivy.addon.portalkit.singleapp.cases.CaseItemRelatedTasks.CaseItemRelatedTasksData #txt
Cs0 f4 actionDecl 'ch.ivy.addon.portalkit.singleapp.cases.CaseItemRelatedTasks.CaseItemRelatedTasksData out;
' #txt
Cs0 f4 actionTable 'out=in;
' #txt
Cs0 f4 actionCode 'import org.primefaces.component.commandlink.CommandLink;
CommandLink commandLink = event.getSource() as CommandLink;

in.taskId = commandLink.getAttributes().get("taskId").toNumber();' #txt
Cs0 f4 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>openPortalTask</name>
        <nameStyle>14,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f4 325 85 22 22 14 0 #rect
Cs0 f4 @|RichDialogProcessStartIcon #fIcon
Cs0 f5 type ch.ivy.addon.portalkit.singleapp.cases.CaseItemRelatedTasks.CaseItemRelatedTasksData #txt
Cs0 f5 processCall 'Functional Processes/Navigator:viewTask(Long,ch.ivy.addon.portalkit.dto.GlobalCaseId,String)' #txt
Cs0 f5 doCall true #txt
Cs0 f5 requestActionDecl '<java.lang.Long taskId,ch.ivy.addon.portalkit.dto.GlobalCaseId caseId,java.lang.String caseName> param;
' #txt
Cs0 f5 requestMappingAction 'param.taskId=in.taskId;
param.caseId=ch.ivy.addon.portalkit.dto.GlobalCaseId.inServer(in.currentServer.id).caseId(in.internalCase.getId()).build();
param.caseName=in.internalCase.name;
' #txt
Cs0 f5 responseActionDecl 'ch.ivy.addon.portalkit.singleapp.cases.CaseItemRelatedTasks.CaseItemRelatedTasksData out;
' #txt
Cs0 f5 responseMappingAction 'out=in;
' #txt
Cs0 f5 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Navigator</name>
        <nameStyle>9,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f5 318 212 36 24 20 -2 #rect
Cs0 f5 @|CallSubIcon #fIcon
Cs0 f23 actionDecl 'ch.ivy.addon.portalkit.singleapp.cases.CaseItemRelatedTasks.CaseItemRelatedTasksData out;
' #txt
Cs0 f23 actionTable 'out=in;
' #txt
Cs0 f23 actionCode 'import ch.ivy.addon.portalkit.util.MenuUtils;
MenuUtils.clearMenuState();' #txt
Cs0 f23 type ch.ivy.addon.portalkit.singleapp.cases.CaseItemRelatedTasks.CaseItemRelatedTasksData #txt
Cs0 f23 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>clear menu state</name>
        <nameStyle>16,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f23 318 148 36 24 20 -2 #rect
Cs0 f23 @|StepIcon #fIcon
Cs0 f7 expr out #txt
Cs0 f7 336 107 336 148 #arcP
Cs0 f6 expr out #txt
Cs0 f6 336 172 336 212 #arcP
>Proto Cs0 .type ch.ivy.addon.portalkit.singleapp.cases.CaseItemRelatedTasks.CaseItemRelatedTasksData #txt
>Proto Cs0 .processKind HTML_DIALOG #txt
>Proto Cs0 -8 -8 16 16 16 26 #rect
>Proto Cs0 '' #fIcon
Cs0 f78 mainOut f10 tail #connect
Cs0 f10 head f9 mainIn #connect
Cs0 f9 mainOut f3 tail #connect
Cs0 f3 head f1 mainIn #connect
Cs0 f0 mainOut f2 tail #connect
Cs0 f2 head f78 mainIn #connect
Cs0 f4 mainOut f7 tail #connect
Cs0 f7 head f23 mainIn #connect
Cs0 f23 mainOut f6 tail #connect
Cs0 f6 head f5 mainIn #connect
