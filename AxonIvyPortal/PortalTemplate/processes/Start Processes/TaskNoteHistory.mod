[Ivy]
16044EDBC0E23859 3.20 #module
>Proto >Proto Collection #zClass
Ty0 TaskNoteHistory Big #zClass
Ty0 B #cInfo
Ty0 #process
Ty0 @TextInP .resExport .resExport #zField
Ty0 @TextInP .type .type #zField
Ty0 @TextInP .processKind .processKind #zField
Ty0 @AnnotationInP-0n ai ai #zField
Ty0 @MessageFlowInP-0n messageIn messageIn #zField
Ty0 @MessageFlowOutP-0n messageOut messageOut #zField
Ty0 @TextInP .xml .xml #zField
Ty0 @TextInP .responsibility .responsibility #zField
Ty0 @StartRequest f0 '' #zField
Ty0 @EndTask f1 '' #zField
Ty0 @GridStep f3 '' #zField
Ty0 @PushWFArc f4 '' #zField
Ty0 @CallSub f5 '' #zField
Ty0 @PushWFArc f6 '' #zField
Ty0 @CallSub f7 '' #zField
Ty0 @GridStep f9 '' #zField
Ty0 @PushWFArc f10 '' #zField
Ty0 @RichDialog f11 '' #zField
Ty0 @PushWFArc f12 '' #zField
Ty0 @PushWFArc f2 '' #zField
Ty0 @GridStep f13 '' #zField
Ty0 @PushWFArc f14 '' #zField
Ty0 @PushWFArc f8 '' #zField
>Proto Ty0 Ty0 TaskNoteHistory #zField
Ty0 f0 outLink showTaskNoteHistory.ivp #txt
Ty0 f0 type ch.ivy.addon.portal.generic.TaskNoteHistoryData #txt
Ty0 f0 inParamDecl '<java.lang.Long remoteTaskId,java.lang.Long serverId> param;' #txt
Ty0 f0 inParamTable 'out.remoteTaskId=param.remoteTaskId;
out.serverId=param.serverId;
' #txt
Ty0 f0 actionDecl 'ch.ivy.addon.portal.generic.TaskNoteHistoryData out;
' #txt
Ty0 f0 guid 16044EDBC1AF596D #txt
Ty0 f0 requestEnabled true #txt
Ty0 f0 triggerEnabled false #txt
Ty0 f0 callSignature showTaskNoteHistory(Long,Long) #txt
Ty0 f0 persist false #txt
Ty0 f0 taskData 'TaskTriggered.ROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.EXPRI=2
TaskTriggered.TYPE=0
TaskTriggered.PRI=2
TaskTriggered.EXROL=Everybody' #txt
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
Ty0 f1 type ch.ivy.addon.portal.generic.TaskNoteHistoryData #txt
Ty0 f1 1241 49 30 30 0 15 #rect
Ty0 f1 @|EndIcon #fIcon
Ty0 f3 actionDecl 'ch.ivy.addon.portal.generic.TaskNoteHistoryData out;
' #txt
Ty0 f3 actionTable 'out=in;
' #txt
Ty0 f3 actionCode 'import ch.ivy.addon.portalkit.service.TaskQueryService;
import ch.ivy.addon.portalkit.support.TaskQueryCriteria;

TaskQueryCriteria queryCriteria = new TaskQueryCriteria();
queryCriteria.taskId = in.remoteTaskId;
queryCriteria.newQueryCreated = true;
out.taskSearchCriteria.jsonQuery = TaskQueryService.service().createQuery(queryCriteria).asJson();' #txt
Ty0 f3 type ch.ivy.addon.portal.generic.TaskNoteHistoryData #txt
Ty0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>prepare task search criteria</name>
        <nameStyle>28,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ty0 f3 216 42 160 44 -75 -8 #rect
Ty0 f3 @|StepIcon #fIcon
Ty0 f4 expr out #txt
Ty0 f4 111 64 216 64 #arcP
Ty0 f5 type ch.ivy.addon.portal.generic.TaskNoteHistoryData #txt
Ty0 f5 processCall MultiPortal/TaskService:findTasksByCriteria(Long,ch.ivy.ws.addon.TaskSearchCriteria,Integer,Integer) #txt
Ty0 f5 doCall true #txt
Ty0 f5 requestActionDecl '<java.lang.Long serverId,ch.ivy.ws.addon.TaskSearchCriteria taskSearchCriteria,java.lang.Integer startIndex,java.lang.Integer count> param;
' #txt
Ty0 f5 requestMappingAction 'param.serverId=in.serverId;
param.taskSearchCriteria=in.taskSearchCriteria;
' #txt
Ty0 f5 responseActionDecl 'ch.ivy.addon.portal.generic.TaskNoteHistoryData out;
' #txt
Ty0 f5 responseMappingAction 'out=in;
out.remoteTask=org.apache.commons.collections4.CollectionUtils.isEmpty(result.tasks) ? null :  result.tasks.get(0);
' #txt
Ty0 f5 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>get remote task</name>
        <nameStyle>15,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ty0 f5 432 42 112 44 -42 -8 #rect
Ty0 f5 @|CallSubIcon #fIcon
Ty0 f6 expr out #txt
Ty0 f6 376 64 432 64 #arcP
Ty0 f7 type ch.ivy.addon.portal.generic.TaskNoteHistoryData #txt
Ty0 f7 processCall MultiPortal/CaseService:findNotes(Boolean,ch.ivy.addon.portalkit.persistence.domain.Server,Long) #txt
Ty0 f7 doCall true #txt
Ty0 f7 requestActionDecl '<java.lang.Boolean excludeSystemNotes,ch.ivy.addon.portalkit.persistence.domain.Server server,java.lang.Long caseId> param;
' #txt
Ty0 f7 requestMappingAction 'param.excludeSystemNotes=in.excludeSystemNotes;
param.server=in.remoteTask.applicationRegister.server;
param.caseId=in.remoteTask.case.getId();
' #txt
Ty0 f7 responseActionDecl 'ch.ivy.addon.portal.generic.TaskNoteHistoryData out;
' #txt
Ty0 f7 responseMappingAction 'out=in;
out.notes=result.notes;
' #txt
Ty0 f7 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>find task notes</name>
        <nameStyle>15,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ty0 f7 752 42 112 44 -40 -8 #rect
Ty0 f7 @|CallSubIcon #fIcon
Ty0 f9 actionDecl 'ch.ivy.addon.portal.generic.TaskNoteHistoryData out;
' #txt
Ty0 f9 actionTable 'out=in;
' #txt
Ty0 f9 actionCode 'import ch.ivy.addon.portalkit.comparator.IvyNoteComparator;

in.notes.sort(new IvyNoteComparator());' #txt
Ty0 f9 type ch.ivy.addon.portal.generic.TaskNoteHistoryData #txt
Ty0 f9 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>sort notes</name>
        <nameStyle>10,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ty0 f9 912 42 112 44 -27 -8 #rect
Ty0 f9 @|StepIcon #fIcon
Ty0 f10 expr out #txt
Ty0 f10 864 64 912 64 #arcP
Ty0 f11 targetWindow NEW #txt
Ty0 f11 targetDisplay TOP #txt
Ty0 f11 richDialogId ch.ivy.addon.portal.generic.TaskNoteHistory #txt
Ty0 f11 startMethod start(List<ch.ivy.ws.addon.IvyNote>,String) #txt
Ty0 f11 type ch.ivy.addon.portal.generic.TaskNoteHistoryData #txt
Ty0 f11 requestActionDecl '<List<ch.ivy.ws.addon.IvyNote> notes, String exportedFileName> param;' #txt
Ty0 f11 requestMappingAction 'param.notes=in.notes;
param.exportedFileName=ivy.cms.co("/ch.ivy.addon.portalkit.ui.jsf/noteHistory/taskExportedFileNamePrefix", java.util.Arrays.asList(in.remoteTask.name));
' #txt
Ty0 f11 responseActionDecl 'ch.ivy.addon.portal.generic.TaskNoteHistoryData out;
' #txt
Ty0 f11 responseMappingAction 'out=in;
' #txt
Ty0 f11 isAsynch false #txt
Ty0 f11 isInnerRd false #txt
Ty0 f11 userContext '* ' #txt
Ty0 f11 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Task Note History</name>
        <nameStyle>17,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ty0 f11 1080 42 112 44 -48 -8 #rect
Ty0 f11 @|RichDialogIcon #fIcon
Ty0 f12 expr out #txt
Ty0 f12 1024 64 1080 64 #arcP
Ty0 f2 expr out #txt
Ty0 f2 1192 64 1241 64 #arcP
Ty0 f13 actionDecl 'ch.ivy.addon.portal.generic.TaskNoteHistoryData out;
' #txt
Ty0 f13 actionTable 'out=in;
' #txt
Ty0 f13 actionCode 'import ch.ivy.addon.portalkit.service.GlobalSettingService;
GlobalSettingService globalSettingSerive = new GlobalSettingService();
in.excludeSystemNotes = globalSettingSerive.findHideSystemTasksFromHistorySettingValue();' #txt
Ty0 f13 type ch.ivy.addon.portal.generic.TaskNoteHistoryData #txt
Ty0 f13 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Check if should exclude
system notes</name>
        <nameStyle>36,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ty0 f13 568 42 160 44 -60 -16 #rect
Ty0 f13 @|StepIcon #fIcon
Ty0 f14 expr out #txt
Ty0 f14 544 64 568 64 #arcP
Ty0 f8 expr out #txt
Ty0 f8 728 64 752 64 #arcP
>Proto Ty0 .type ch.ivy.addon.portal.generic.TaskNoteHistoryData #txt
>Proto Ty0 .processKind NORMAL #txt
>Proto Ty0 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language/>
</elementInfo>
' #txt
>Proto Ty0 0 0 32 24 18 0 #rect
>Proto Ty0 @|BIcon #fIcon
Ty0 f0 mainOut f4 tail #connect
Ty0 f4 head f3 mainIn #connect
Ty0 f3 mainOut f6 tail #connect
Ty0 f6 head f5 mainIn #connect
Ty0 f7 mainOut f10 tail #connect
Ty0 f10 head f9 mainIn #connect
Ty0 f9 mainOut f12 tail #connect
Ty0 f12 head f11 mainIn #connect
Ty0 f11 mainOut f2 tail #connect
Ty0 f2 head f1 mainIn #connect
Ty0 f5 mainOut f14 tail #connect
Ty0 f14 head f13 mainIn #connect
Ty0 f13 mainOut f8 tail #connect
Ty0 f8 head f7 mainIn #connect
