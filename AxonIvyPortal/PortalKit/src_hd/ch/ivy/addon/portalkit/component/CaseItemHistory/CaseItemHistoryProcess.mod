[Ivy]
153362B0AC312EFB 9.4.3 #module
>Proto >Proto Collection #zClass
Cs0 CaseItemHistoryProcess Big #zClass
Cs0 RD #cInfo
Cs0 #process
Cs0 @TextInP .colors .colors #zField
Cs0 @TextInP color color #zField
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
Cs0 @PushWFArc f10 '' #zField
Cs0 @PushWFArc f9 '' #zField
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
Cs0 f1 275 83 26 26 0 12 #rect
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
Cs0 f26 actionTable 'out=in;
' #txt
Cs0 f26 actionCode 'import ch.ivy.addon.portalkit.util.CaseUtils;
import ch.ivy.addon.portalkit.datamodel.internal.RelatedCaseLazyDataModel;
import ch.ivy.addon.portalkit.ivydata.dto.IvyCaseResultDTO;
import ch.ivy.addon.portalkit.ivydata.searchcriteria.CaseSearchCriteria;
import ch.ivy.addon.portalkit.ivydata.service.impl.CaseService;
import ch.ivy.addon.portalkit.service.HistoryService;
import ch.ivyteam.ivy.workflow.CaseState;
import ch.ivyteam.ivy.workflow.ICase;
import ch.ivyteam.ivy.workflow.INote;
import ch.ivyteam.ivy.workflow.ITask;
import ch.ivyteam.ivy.workflow.TaskState;
import java.util.ArrayList;
import java.util.Arrays;
import org.apache.commons.collections4.CollectionUtils;

List<ITask> finishedTasks = new ArrayList();
for (ITask task : in.iCase.tasks().all()) {
	if (task.getState() == TaskState.DONE
	|| task.getState() == TaskState.DESTROYED) {
		finishedTasks.add(task);
	}
}

long selectedCaseId = in.iCase.getId();
List<ICase> cases = new ArrayList();
cases.add(in.iCase);
cases.addAll(CaseUtils.findSubCasesByBusinessCaseId(selectedCaseId));

HistoryService historyService = new HistoryService();
in.histories = historyService.getCaseHistories(selectedCaseId, finishedTasks, cases, !in.showSystemTasks, !in.showSystemNotes);' #txt
Cs0 f26 security system #txt
Cs0 f26 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Create histories&#xD;
from tasks and notes</name>
    </language>
</elementInfo>
' #txt
Cs0 f26 344 170 144 44 -53 -16 #rect
Cs0 f28 523 179 26 26 0 12 #rect
Cs0 f29 expr out #txt
Cs0 f29 488 192 523 192 #arcP
Cs0 f3 actionTable 'out=in;
' #txt
Cs0 f3 actionCode 'in.iCase.createNote(ivy.session, in.noteContent);
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
Cs0 f25 expr out #txt
Cs0 f25 109 288 168 288 #arcP
Cs0 f4 expr out #txt
Cs0 f4 280 288 416 214 #arcP
Cs0 f4 1 416 288 #addKink
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
Cs0 f6 109 384 416 214 #arcP
Cs0 f6 1 416 384 #addKink
Cs0 f6 0 0.6831393451508 0 0 #arcLabel
Cs0 f8 actionTable 'out=in;
' #txt
Cs0 f8 actionCode 'import ch.ivy.addon.portalkit.util.PermissionUtils;
import ch.ivy.addon.portalkit.service.GlobalSettingService;
GlobalSettingService service = new GlobalSettingService();
out.showSystemNotesChkbox = !service.findHideSystemNotesFromHistorySettingValue();
out.showSystemTasksChkbox = !service.findHideSystemTasksFromHistorySettingValue();
boolean isAdmin = PermissionUtils.isSessionUserHasAdminRole();
out.showSystemNotes = isAdmin && out.showSystemNotesChkbox;
out.showSystemTasks = isAdmin && out.showSystemTasksChkbox;' #txt
Cs0 f8 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Read Portal settings&#13;
and default values of&#13;
system entries</name>
    </language>
</elementInfo>
' #txt
Cs0 f8 160 162 128 60 -46 -24 #rect
Cs0 f10 288 192 344 192 #arcP
Cs0 f9 109 192 160 192 #arcP
>Proto Cs0 .type ch.ivy.addon.portalkit.component.CaseItemHistory.CaseItemHistoryData #txt
>Proto Cs0 .processKind HTML_DIALOG #txt
>Proto Cs0 -8 -8 16 16 16 26 #rect
Cs0 f0 mainOut f2 tail #connect
Cs0 f2 head f1 mainIn #connect
Cs0 f26 mainOut f29 tail #connect
Cs0 f29 head f28 mainIn #connect
Cs0 f7 mainOut f25 tail #connect
Cs0 f25 head f3 mainIn #connect
Cs0 f3 mainOut f4 tail #connect
Cs0 f4 head f26 mainIn #connect
Cs0 f5 mainOut f6 tail #connect
Cs0 f6 head f26 mainIn #connect
Cs0 f8 mainOut f10 tail #connect
Cs0 f10 head f26 mainIn #connect
Cs0 f18 mainOut f9 tail #connect
Cs0 f9 head f8 mainIn #connect
