[Ivy]
153362B0AC312EFB 9.2.0 #module
>Proto >Proto Collection #zClass
Cs0 CaseItemHistoryProcess Big #zClass
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
Cs0 @PushWFArc f2 '' #zField
Cs0 @UdMethod f18 '' #zField
Cs0 @GridStep f19 '' #zField
Cs0 @PushWFArc f20 '' #zField
Cs0 @CallSub f21 '' #zField
Cs0 @PushWFArc f22 '' #zField
Cs0 @GridStep f26 '' #zField
Cs0 @UdProcessEnd f28 '' #zField
Cs0 @PushWFArc f29 '' #zField
Cs0 @GridStep f3 '' #zField
Cs0 @UdMethod f7 '' #zField
Cs0 @PushWFArc f25 '' #zField
Cs0 @PushWFArc f4 '' #zField
Cs0 @UdMethod f5 '' #zField
Cs0 @PushWFArc f6 '' #zField
Cs0 @GridStep f8 '' #zField
Cs0 @PushWFArc f9 '' #zField
Cs0 @PushWFArc f10 '' #zField
>Proto Cs0 Cs0 CaseItemHistoryProcess #zField
Cs0 f0 guid 167E9D3052E4370B #txt
Cs0 f0 method start() #txt
Cs0 f0 inParameterDecl '<> param;' #txt
Cs0 f0 outParameterDecl '<> result;' #txt
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
Cs0 f2 expr out #txt
Cs0 f2 109 96 275 96 #arcP
Cs0 f18 guid 167E9D324F6C3538 #txt
Cs0 f18 method initData(ch.ivyteam.ivy.workflow.ICase) #txt
Cs0 f18 inParameterDecl '<ch.ivyteam.ivy.workflow.ICase iCase> param;' #txt
Cs0 f18 inParameterMapAction 'out.iCase=param.iCase;
' #txt
Cs0 f18 outParameterDecl '<> result;' #txt
Cs0 f18 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>initData(ICase)</name>
    </language>
</elementInfo>
' #txt
Cs0 f18 83 179 26 26 -41 15 #rect
Cs0 f18 @|UdMethodIcon #fIcon
Cs0 f19 actionTable 'out=in;
' #txt
Cs0 f19 actionCode 'import ch.ivy.addon.portalkit.util.PermissionUtils;
import ch.ivyteam.ivy.workflow.TaskState;

out.taskSearchCriteria.setAdminQuery(PermissionUtils.checkReadAllTasksPermission());
out.taskSearchCriteria.includedStates = [TaskState.DONE, TaskState.DESTROYED];
out.taskSearchCriteria.caseId = in.iCase.getId();
out.taskSearchCriteria.queryByBusinessCaseId = in.iCase.isBusinessCase();
out.taskSearchCriteria.newQueryCreated = true;
out.taskSearchCriteria.finalTaskQuery = out.taskSearchCriteria.createQuery();
' #txt
Cs0 f19 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Prepare task&#xD;
search criteria</name>
    </language>
</elementInfo>
' #txt
Cs0 f19 168 170 112 44 -39 -16 #rect
Cs0 f19 @|StepIcon #fIcon
Cs0 f20 expr out #txt
Cs0 f20 109 192 168 192 #arcP
Cs0 f21 processCall 'Ivy Data Processes/TaskService:findTasksByCriteria(ch.ivy.addon.portalkit.ivydata.searchcriteria.TaskSearchCriteria,Integer,Integer)' #txt
Cs0 f21 requestActionDecl '<ch.ivy.addon.portalkit.ivydata.searchcriteria.TaskSearchCriteria taskSearchCriteria,Integer startIndex,Integer count> param;' #txt
Cs0 f21 requestMappingAction 'param.taskSearchCriteria=in.taskSearchCriteria;
param.startIndex=0;
param.count=-1;
' #txt
Cs0 f21 responseActionDecl 'ch.ivy.addon.portalkit.component.CaseItemHistory.CaseItemHistoryData out;
' #txt
Cs0 f21 responseMappingAction 'out=in;
out.tasks=result.tasks;
' #txt
Cs0 f21 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>TaskService</name>
    </language>
</elementInfo>
' #txt
Cs0 f21 320 170 112 44 -33 -8 #rect
Cs0 f21 @|CallSubIcon #fIcon
Cs0 f22 expr out #txt
Cs0 f22 280 192 320 192 #arcP
Cs0 f26 actionTable 'out=in;
' #txt
Cs0 f26 actionCode 'import ch.ivy.addon.portalkit.service.HistoryService;

HistoryService historyService = new HistoryService();
in.histories = historyService.getHistories(in.tasks, in.iCase.getNotes(), !in.showSystemTasks, !in.showSystemNotes);' #txt
Cs0 f26 security system #txt
Cs0 f26 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Create histories&#xD;
from tasks and notes</name>
    </language>
</elementInfo>
' #txt
Cs0 f26 656 170 144 44 -53 -16 #rect
Cs0 f26 @|StepIcon #fIcon
Cs0 f28 835 179 26 26 0 12 #rect
Cs0 f28 @|UdProcessEndIcon #fIcon
Cs0 f29 expr out #txt
Cs0 f29 800 192 835 192 #arcP
Cs0 f3 actionTable 'out=in;
' #txt
Cs0 f3 actionCode 'in.iCase.getBusinessCase().createNote(ivy.session, in.noteContent);
in.noteContent = "";' #txt
Cs0 f3 security system #txt
Cs0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Create note</name>
    </language>
</elementInfo>
' #txt
Cs0 f3 168 266 112 44 -32 -8 #rect
Cs0 f3 @|StepIcon #fIcon
Cs0 f7 guid 1682B786CB2C9D0B #txt
Cs0 f7 method createNote() #txt
Cs0 f7 inParameterDecl '<> param;' #txt
Cs0 f7 outParameterDecl '<> result;' #txt
Cs0 f7 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>createNote()</name>
    </language>
</elementInfo>
' #txt
Cs0 f7 83 275 26 26 -34 15 #rect
Cs0 f7 @|UdMethodIcon #fIcon
Cs0 f25 expr out #txt
Cs0 f25 109 288 168 288 #arcP
Cs0 f4 expr out #txt
Cs0 f4 280 288 728 214 #arcP
Cs0 f4 1 728 288 #addKink
Cs0 f4 0 0.6911145968878323 0 0 #arcLabel
Cs0 f5 guid 177234B3CBCDC920 #txt
Cs0 f5 method loadHistories() #txt
Cs0 f5 inParameterDecl '<> param;' #txt
Cs0 f5 outParameterDecl '<> result;' #txt
Cs0 f5 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>loadHistories()</name>
    </language>
</elementInfo>
' #txt
Cs0 f5 83 371 26 26 -23 15 #rect
Cs0 f5 @|UdMethodIcon #fIcon
Cs0 f6 109 384 728 214 #arcP
Cs0 f6 1 728 384 #addKink
Cs0 f6 0 0.6831393451508 0 0 #arcLabel
Cs0 f8 actionTable 'out=in;
' #txt
Cs0 f8 actionCode 'import ch.ivy.addon.portalkit.util.PermissionUtils;
import ch.ivy.addon.portalkit.service.GlobalSettingService;
GlobalSettingService service = new GlobalSettingService();
out.showSystemNotesChkbox = !service.findHideSystemNotesFromHistorySettingValue();
out.showSystemTasksChkbox = !service.findHideSystemTasksFromHistorySettingValue();
boolean isAdmin = PermissionUtils.isSessionUserHasAdminRole();
out.showSystemNotes = isAdmin;
out.showSystemTasks = isAdmin;' #txt
Cs0 f8 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Read Portal settings&#13;
and default values of&#13;
system entries</name>
    </language>
</elementInfo>
' #txt
Cs0 f8 472 162 128 60 -46 -24 #rect
Cs0 f8 @|StepIcon #fIcon
Cs0 f9 expr out #txt
Cs0 f9 432 192 472 192 #arcP
Cs0 f10 600 192 656 192 #arcP
>Proto Cs0 .type ch.ivy.addon.portalkit.component.CaseItemHistory.CaseItemHistoryData #txt
>Proto Cs0 .processKind HTML_DIALOG #txt
>Proto Cs0 -8 -8 16 16 16 26 #rect
>Proto Cs0 '' #fIcon
Cs0 f0 mainOut f2 tail #connect
Cs0 f2 head f1 mainIn #connect
Cs0 f18 mainOut f20 tail #connect
Cs0 f20 head f19 mainIn #connect
Cs0 f19 mainOut f22 tail #connect
Cs0 f22 head f21 mainIn #connect
Cs0 f26 mainOut f29 tail #connect
Cs0 f29 head f28 mainIn #connect
Cs0 f7 mainOut f25 tail #connect
Cs0 f25 head f3 mainIn #connect
Cs0 f3 mainOut f4 tail #connect
Cs0 f4 head f26 mainIn #connect
Cs0 f5 mainOut f6 tail #connect
Cs0 f6 head f26 mainIn #connect
Cs0 f21 mainOut f9 tail #connect
Cs0 f9 head f8 mainIn #connect
Cs0 f8 mainOut f10 tail #connect
Cs0 f10 head f26 mainIn #connect
