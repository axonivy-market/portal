[Ivy]
169137A20654C01B 7.5.0 #module
>Proto >Proto Collection #zClass
Hs0 HideOrUnHideTaskCaseProcess Big #zClass
Hs0 RD #cInfo
Hs0 #process
Hs0 @TextInP .type .type #zField
Hs0 @TextInP .processKind .processKind #zField
Hs0 @AnnotationInP-0n ai ai #zField
Hs0 @MessageFlowInP-0n messageIn messageIn #zField
Hs0 @MessageFlowOutP-0n messageOut messageOut #zField
Hs0 @TextInP .xml .xml #zField
Hs0 @TextInP .responsibility .responsibility #zField
Hs0 @UdInit f0 '' #zField
Hs0 @UdProcessEnd f1 '' #zField
Hs0 @PushWFArc f2 '' #zField
Hs0 @UdEvent f3 '' #zField
Hs0 @UdExitEnd f4 '' #zField
Hs0 @PushWFArc f5 '' #zField
Hs0 @UdEvent f6 '' #zField
Hs0 @UdExitEnd f7 '' #zField
Hs0 @GridStep f8 '' #zField
Hs0 @PushWFArc f9 '' #zField
Hs0 @PushWFArc f10 '' #zField
Hs0 @UdEvent f11 '' #zField
Hs0 @UdExitEnd f12 '' #zField
Hs0 @GridStep f13 '' #zField
Hs0 @PushWFArc f14 '' #zField
Hs0 @PushWFArc f15 '' #zField
>Proto Hs0 Hs0 HideOrUnHideTaskCaseProcess #zField
Hs0 f0 guid 169137A2086B150F #txt
Hs0 f0 method start() #txt
Hs0 f0 inParameterDecl '<> param;' #txt
Hs0 f0 outParameterDecl '<> result;' #txt
Hs0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start()</name>
    </language>
</elementInfo>
' #txt
Hs0 f0 83 51 26 26 -16 15 #rect
Hs0 f0 @|UdInitIcon #fIcon
Hs0 f1 211 51 26 26 0 12 #rect
Hs0 f1 @|UdProcessEndIcon #fIcon
Hs0 f2 expr out #txt
Hs0 f2 109 64 211 64 #arcP
Hs0 f3 guid 169137A20B7C72B8 #txt
Hs0 f3 actionTable 'out=in;
' #txt
Hs0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>close</name>
    </language>
</elementInfo>
' #txt
Hs0 f3 83 147 26 26 -15 12 #rect
Hs0 f3 @|UdEventIcon #fIcon
Hs0 f4 211 147 26 26 0 12 #rect
Hs0 f4 @|UdExitEndIcon #fIcon
Hs0 f5 expr out #txt
Hs0 f5 109 160 211 160 #arcP
Hs0 f6 guid 169137C690A4E7BD #txt
Hs0 f6 actionTable 'out=in;
' #txt
Hs0 f6 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>hide</name>
    </language>
</elementInfo>
' #txt
Hs0 f6 83 243 26 26 -9 20 #rect
Hs0 f6 @|UdEventIcon #fIcon
Hs0 f7 371 243 26 26 0 12 #rect
Hs0 f7 @|UdExitEndIcon #fIcon
Hs0 f8 actionTable 'out=in;
' #txt
Hs0 f8 actionCode 'import ch.ivy.addon.portalkit.util.CaseUtils;
import ch.ivyteam.ivy.workflow.ICase;
import ch.ivyteam.ivy.workflow.query.CaseQuery;
import ch.ivy.addon.portalkit.util.TaskUtils;
import ch.ivyteam.ivy.workflow.ITask;
import ch.ivyteam.ivy.workflow.query.TaskQuery;

TaskQuery taskQuery = TaskQuery.create().where().taskId().isEqual(in.taskId);
ITask task = ivy.wf.getGlobalContext().getTaskQueryExecutor().getResults(taskQuery).get(0) as ITask;
if (task != null){
	TaskUtils.setHidePropertyToHideInPortal(task);
}

CaseQuery caseQuery = CaseQuery.create().where().caseId().isEqual(in.caseId);
ICase case = ivy.wf.getGlobalContext().getCaseQueryExecutor().getResults(caseQuery).get(0) as ICase;
if (case != null){
	CaseUtils.setHidePropertyToHideInPortal(case);
}' #txt
Hs0 f8 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>hide</name>
    </language>
</elementInfo>
' #txt
Hs0 f8 184 234 112 44 -12 -8 #rect
Hs0 f8 @|StepIcon #fIcon
Hs0 f9 expr out #txt
Hs0 f9 109 256 184 256 #arcP
Hs0 f10 expr out #txt
Hs0 f10 296 256 371 256 #arcP
Hs0 f11 guid 169138F1D5AEA255 #txt
Hs0 f11 actionTable 'out=in;
' #txt
Hs0 f11 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>unhide</name>
    </language>
</elementInfo>
' #txt
Hs0 f11 83 339 26 26 -20 16 #rect
Hs0 f11 @|UdEventIcon #fIcon
Hs0 f12 371 339 26 26 0 12 #rect
Hs0 f12 @|UdExitEndIcon #fIcon
Hs0 f13 actionTable 'out=in;
' #txt
Hs0 f13 actionCode 'import ch.ivy.addon.portalkit.util.CaseUtils;
import ch.ivyteam.ivy.workflow.ICase;
import ch.ivyteam.ivy.workflow.query.CaseQuery;
import ch.ivy.addon.portalkit.util.TaskUtils;
import ch.ivyteam.ivy.workflow.ITask;
import ch.ivyteam.ivy.workflow.query.TaskQuery;

TaskQuery taskQuery = TaskQuery.create().where().taskId().isEqual(in.taskId);
ITask task = ivy.wf.getGlobalContext().getTaskQueryExecutor().getResults(taskQuery).get(0) as ITask;
if (task != null){
	TaskUtils.removeHidePropertyToDisplayInPortal(task);
}

CaseQuery caseQuery = CaseQuery.create().where().caseId().isEqual(in.caseId);
ICase case = ivy.wf.getGlobalContext().getCaseQueryExecutor().getResults(caseQuery).get(0) as ICase;
if (case != null){
	CaseUtils.removeHidePropertyToDisplayInPortal(case);
}' #txt
Hs0 f13 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>unhide</name>
    </language>
</elementInfo>
' #txt
Hs0 f13 184 330 112 44 -19 -8 #rect
Hs0 f13 @|StepIcon #fIcon
Hs0 f14 expr out #txt
Hs0 f14 109 352 184 352 #arcP
Hs0 f15 expr out #txt
Hs0 f15 296 352 371 352 #arcP
>Proto Hs0 .type ch.ivy.addon.portalkit.test.HideOrUnHideTaskCase.HideOrUnHideTaskCaseData #txt
>Proto Hs0 .processKind HTML_DIALOG #txt
>Proto Hs0 -8 -8 16 16 16 26 #rect
>Proto Hs0 '' #fIcon
Hs0 f0 mainOut f2 tail #connect
Hs0 f2 head f1 mainIn #connect
Hs0 f3 mainOut f5 tail #connect
Hs0 f5 head f4 mainIn #connect
Hs0 f6 mainOut f9 tail #connect
Hs0 f9 head f8 mainIn #connect
Hs0 f8 mainOut f10 tail #connect
Hs0 f10 head f7 mainIn #connect
Hs0 f11 mainOut f14 tail #connect
Hs0 f14 head f13 mainIn #connect
Hs0 f13 mainOut f15 tail #connect
Hs0 f15 head f12 mainIn #connect
