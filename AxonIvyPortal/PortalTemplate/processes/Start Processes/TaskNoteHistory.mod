[Ivy]
16044EDBC0E23859 7.5.0 #module
>Proto >Proto Collection #zClass
Ty0 TaskNoteHistory Big #zClass
Ty0 B #cInfo
Ty0 #process
Ty0 @TextInP .type .type #zField
Ty0 @TextInP .processKind .processKind #zField
Ty0 @AnnotationInP-0n ai ai #zField
Ty0 @MessageFlowInP-0n messageIn messageIn #zField
Ty0 @MessageFlowOutP-0n messageOut messageOut #zField
Ty0 @TextInP .xml .xml #zField
Ty0 @TextInP .responsibility .responsibility #zField
Ty0 @StartRequest f0 '' #zField
Ty0 @EndTask f1 '' #zField
Ty0 @GridStep f9 '' #zField
Ty0 @UserDialog f11 '' #zField
Ty0 @PushWFArc f12 '' #zField
Ty0 @PushWFArc f2 '' #zField
Ty0 @GridStep f17 '' #zField
Ty0 @PushWFArc f3 '' #zField
Ty0 @PushWFArc f4 '' #zField
>Proto Ty0 Ty0 TaskNoteHistory #zField
Ty0 f0 outLink showTaskNoteHistory.ivp #txt
Ty0 f0 inParamDecl '<Long selectedTaskId> param;' #txt
Ty0 f0 inParamTable 'out.taskId=param.selectedTaskId;
' #txt
Ty0 f0 requestEnabled true #txt
Ty0 f0 triggerEnabled false #txt
Ty0 f0 callSignature showTaskNoteHistory(Long) #txt
Ty0 f0 persist false #txt
Ty0 f0 taskData 'TaskTriggered.EXPRI=2
TaskTriggered.EXROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.PRI=2
TaskTriggered.ROL=Everybody
TaskTriggered.TYPE=0' #txt
Ty0 f0 caseData businessCase.attach=true #txt
Ty0 f0 showInStartList 0 #txt
Ty0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>showTaskNoteHistory.ivp</name>
        <nameStyle>23,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ty0 f0 @C|.responsibility Everybody #txt
Ty0 f0 81 49 30 30 -69 17 #rect
Ty0 f0 @|StartRequestIcon #fIcon
Ty0 f1 705 49 30 30 0 15 #rect
Ty0 f1 @|EndIcon #fIcon
Ty0 f9 actionTable 'out=in;
' #txt
Ty0 f9 actionCode 'import ch.ivy.addon.portalkit.comparator.NoteComparator;

in.notes.sort(new NoteComparator());' #txt
Ty0 f9 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>sort notes</name>
        <nameStyle>10,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ty0 f9 376 42 112 44 -27 -8 #rect
Ty0 f9 @|StepIcon #fIcon
Ty0 f11 dialogId ch.ivy.addon.portal.generic.TaskNoteHistory #txt
Ty0 f11 startMethod start(java.util.List<ch.ivyteam.ivy.workflow.INote>,String) #txt
Ty0 f11 requestActionDecl '<java.util.List<ch.ivyteam.ivy.workflow.INote> notes,String exportedFileName> param;' #txt
Ty0 f11 requestMappingAction 'param.notes=in.notes;
param.exportedFileName=ivy.cms.co("/ch.ivy.addon.portalkit.ui.jsf/noteHistory/taskExportedFileNamePrefix", java.util.Arrays.asList(ch.ivy.addon.portalkit.util.PermissionUtils.getTaskName(in.task)));
' #txt
Ty0 f11 responseActionDecl 'ch.ivy.addon.portal.generic.TaskNoteHistoryData out;
' #txt
Ty0 f11 responseMappingAction 'out=in;
' #txt
Ty0 f11 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Task Note History</name>
        <nameStyle>17,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ty0 f11 544 42 112 44 -48 -8 #rect
Ty0 f11 @|UserDialogIcon #fIcon
Ty0 f12 expr out #txt
Ty0 f12 488 64 544 64 #arcP
Ty0 f2 expr out #txt
Ty0 f2 656 64 705 64 #arcP
Ty0 f17 actionTable 'out=in;
' #txt
Ty0 f17 actionCode 'import ch.ivyteam.ivy.workflow.query.TaskQuery;
import ch.ivyteam.ivy.workflow.ITask;
import ch.ivy.addon.portalkit.util.CaseUtils;
import ch.ivyteam.ivy.workflow.ICase;
import ch.ivy.addon.portalkit.service.GlobalSettingService;

out.task = ivy.wf.getGlobalContext().getTaskQueryExecutor().getFirstResult(TaskQuery.create().where().taskId().isEqual(in.taskId)) as ITask;
ICase iCase = out.task.getCase().getBusinessCase();
GlobalSettingService globalSettingService = new GlobalSettingService();
boolean excludeTechnicalHistory = globalSettingService.findHideSystemTasksFromHistorySettingValue();
in.notes = CaseUtils.findNotes(iCase, excludeTechnicalHistory);' #txt
Ty0 f17 security system #txt
Ty0 f17 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>find notes</name>
    </language>
</elementInfo>
' #txt
Ty0 f17 200 42 112 44 -27 -8 #rect
Ty0 f17 @|StepIcon #fIcon
Ty0 f3 expr out #txt
Ty0 f3 111 64 200 64 #arcP
Ty0 f4 expr out #txt
Ty0 f4 312 64 376 64 #arcP
>Proto Ty0 .type ch.ivy.addon.portal.generic.TaskNoteHistoryData #txt
>Proto Ty0 .processKind NORMAL #txt
>Proto Ty0 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language/>
</elementInfo>
' #txt
>Proto Ty0 0 0 32 24 18 0 #rect
>Proto Ty0 @|BIcon #fIcon
Ty0 f9 mainOut f12 tail #connect
Ty0 f12 head f11 mainIn #connect
Ty0 f11 mainOut f2 tail #connect
Ty0 f2 head f1 mainIn #connect
Ty0 f0 mainOut f3 tail #connect
Ty0 f3 head f17 mainIn #connect
Ty0 f17 mainOut f4 tail #connect
Ty0 f4 head f9 mainIn #connect
