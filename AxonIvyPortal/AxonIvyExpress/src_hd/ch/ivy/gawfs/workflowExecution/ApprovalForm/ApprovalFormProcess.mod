[Ivy]
162F55163FD8DF16 7.5.0 #module
>Proto >Proto Collection #zClass
As0 ApprovalFormProcess Big #zClass
As0 RD #cInfo
As0 #process
As0 @TextInP .type .type #zField
As0 @TextInP .processKind .processKind #zField
As0 @AnnotationInP-0n ai ai #zField
As0 @MessageFlowInP-0n messageIn messageIn #zField
As0 @MessageFlowOutP-0n messageOut messageOut #zField
As0 @TextInP .xml .xml #zField
As0 @TextInP .responsibility .responsibility #zField
As0 @UdInit f0 '' #zField
As0 @UdProcessEnd f1 '' #zField
As0 @UdEvent f6 '' #zField
As0 @UdEvent f7 '' #zField
As0 @GridStep f3 '' #zField
As0 @UdExitEnd f5 '' #zField
As0 @GridStep f9 '' #zField
As0 @GridStep f10 '' #zField
As0 @PushWFArc f11 '' #zField
As0 @PushWFArc f4 '' #zField
As0 @PushWFArc f12 '' #zField
As0 @GridStep f13 '' #zField
As0 @PushWFArc f14 '' #zField
As0 @UdEvent f16 '' #zField
As0 @GridStep f17 '' #zField
As0 @PushWFArc f18 '' #zField
As0 @UdProcessEnd f19 '' #zField
As0 @PushWFArc f20 '' #zField
As0 @GridStep f21 '' #zField
As0 @PushWFArc f22 '' #zField
As0 @PushWFArc f8 '' #zField
As0 @PushWFArc f23 '' #zField
As0 @GridStep f15 '' #zField
As0 @PushWFArc f24 '' #zField
As0 @PushWFArc f2 '' #zField
>Proto As0 As0 ApprovalFormProcess #zField
As0 f0 guid 162F55164217492D #txt
As0 f0 method start(java.util.List<gawfs.TaskDef>,List<String>,Integer,Boolean,Long) #txt
As0 f0 inParameterDecl '<java.util.List<gawfs.TaskDef> finishedTasks,List<String> steps,Integer actualStepIndex,Boolean isAdhocProcess,Long originalTaskId> param;' #txt
As0 f0 inParameterMapAction 'out.actualStepIndex=param.actualStepIndex;
out.finishedTasks=param.finishedTasks;
out.isAdhocProcess=param.isAdhocProcess;
out.originalTaskId=param.originalTaskId;
out.steps=param.steps;
' #txt
As0 f0 outParameterDecl '<gawfs.ApprovalTaskResult approvalResult> result;' #txt
As0 f0 outParameterMapAction 'result.approvalResult=in.approvalResult;
' #txt
As0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start(List,List,Integer,Boolean,Long)</name>
    </language>
</elementInfo>
' #txt
As0 f0 83 51 26 26 -16 15 #rect
As0 f0 @|UdInitIcon #fIcon
As0 f1 435 51 26 26 0 12 #rect
As0 f1 @|UdProcessEndIcon #fIcon
As0 f6 guid 163066332FC888BC #txt
As0 f6 actionTable 'out=in;
' #txt
As0 f6 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>closeApprove</name>
        <nameStyle>12,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
As0 f6 83 147 26 26 -37 15 #rect
As0 f6 @|UdEventIcon #fIcon
As0 f7 guid 1630663828B44965 #txt
As0 f7 actionTable 'out=in;
' #txt
As0 f7 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>closeDeny</name>
        <nameStyle>9,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
As0 f7 83 243 26 26 -29 15 #rect
As0 f7 @|UdEventIcon #fIcon
As0 f3 actionTable 'out=in;
' #txt
As0 f3 actionCode 'import ch.ivy.addon.portalkit.util.AdhocUtils;
import org.apache.commons.lang3.StringUtils;
String note = ivy.session.getSessionUserName() + ":" + ivy.cms.co("/Dialogs/WorkflowExecution/ApprovalForm/Approve"); 
ivy.case.createNote(ivy.session, note);

if (in.originalTaskId != null && in.originalTaskId > 0) {
	String approvalComment = ivy.cms.co("/Dialogs/WorkflowExecution/ApprovalForm/ApproveComment") + (StringUtils.isEmpty(in.comment) ? "" : (" - " + in.comment));
	AdhocUtils.storeHistory(in.originalTaskId, approvalComment);
	ivy.case.getBusinessCase().createNote(ivy.session, ivy.task.getName() + ": " + approvalComment);
}' #txt
As0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Update history</name>
    </language>
</elementInfo>
' #txt
As0 f3 352 138 112 44 -39 -8 #rect
As0 f3 @|StepIcon #fIcon
As0 f5 691 147 26 26 0 12 #rect
As0 f5 @|UdExitEndIcon #fIcon
As0 f9 actionTable 'out=in;
' #txt
As0 f9 actionCode in.approvalResult.setIsApproved(false); #txt
As0 f9 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Update result</name>
    </language>
</elementInfo>
' #txt
As0 f9 160 234 112 44 -37 -8 #rect
As0 f9 @|StepIcon #fIcon
As0 f10 actionTable 'out=in;
' #txt
As0 f10 actionCode in.approvalResult.setIsApproved(true); #txt
As0 f10 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Update result</name>
    </language>
</elementInfo>
' #txt
As0 f10 160 138 112 44 -37 -8 #rect
As0 f10 @|StepIcon #fIcon
As0 f11 expr out #txt
As0 f11 109 160 160 160 #arcP
As0 f4 expr out #txt
As0 f4 272 160 352 160 #arcP
As0 f12 expr out #txt
As0 f12 109 256 160 256 #arcP
As0 f13 actionTable 'out=in;
' #txt
As0 f13 actionCode 'import org.apache.commons.lang3.StringUtils;
import ch.ivy.addon.portalkit.util.AdhocUtils;
String note = ivy.session.getSessionUserName() + ":" + ivy.cms.co("/Dialogs/WorkflowExecution/ApprovalForm/Reject"); 
ivy.case.createNote(ivy.session, note);

if (in.originalTaskId != null && in.originalTaskId > 0) {
	String approvalComment = ivy.cms.co("/Dialogs/WorkflowExecution/ApprovalForm/RejectComment") + (StringUtils.isEmpty(in.comment) ? "" : (" - " + in.comment));
	AdhocUtils.storeHistory(in.originalTaskId, approvalComment);
	ivy.case.getBusinessCase().createNote(ivy.session, ivy.task.getName() + ": " + approvalComment);
}' #txt
As0 f13 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Update history</name>
    </language>
</elementInfo>
' #txt
As0 f13 352 234 112 44 -39 -8 #rect
As0 f13 @|StepIcon #fIcon
As0 f14 expr out #txt
As0 f14 272 256 352 256 #arcP
As0 f16 guid 163066874021794C #txt
As0 f16 actionTable 'out=in;
' #txt
As0 f16 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>cancel</name>
        <nameStyle>6,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
As0 f16 83 339 26 26 -18 15 #rect
As0 f16 @|UdEventIcon #fIcon
As0 f17 actionTable 'out=in;
' #txt
As0 f17 actionCode 'import ch.ivy.addon.portalkit.util.TaskUtils;
import ch.ivy.addon.portal.generic.navigation.PortalNavigator;
TaskUtils.resetTask(ivy.task);

PortalNavigator navigator = new PortalNavigator();
navigator.navigateToPortalEndPage();' #txt
As0 f17 security system #txt
As0 f17 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Reset task</name>
    </language>
</elementInfo>
' #txt
As0 f17 256 330 112 44 -29 -8 #rect
As0 f17 @|StepIcon #fIcon
As0 f18 expr out #txt
As0 f18 109 352 256 352 #arcP
As0 f19 435 339 26 26 0 12 #rect
As0 f19 @|UdProcessEndIcon #fIcon
As0 f20 expr out #txt
As0 f20 368 352 435 352 #arcP
As0 f21 actionTable 'out=in;
' #txt
As0 f21 actionCode 'import ch.ivyteam.ivy.security.IUser;

in.approvalResult.setApproverName(ivy.session.getSessionUser().getDisplayName());
in.approvalResult.setComment(in.comment);
in.approvalResult.setTaskName(in.steps.get(in.actualStepIndex));
in.approvalResult.setIndex(in.actualStepIndex);' #txt
As0 f21 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>update approval&#xD;
information</name>
    </language>
</elementInfo>
' #txt
As0 f21 512 138 128 44 -42 -16 #rect
As0 f21 @|StepIcon #fIcon
As0 f22 expr out #txt
As0 f22 464 160 512 160 #arcP
As0 f8 expr out #txt
As0 f8 640 160 691 160 #arcP
As0 f23 expr out #txt
As0 f23 464 256 576 182 #arcP
As0 f23 1 576 256 #addKink
As0 f23 0 0.8533831173785638 0 0 #arcLabel
As0 f15 actionTable 'out=in;
' #txt
As0 f15 actionCode 'import gawfs.TaskDef;

for (TaskDef task : in.finishedTasks) {
	task.actualApplicant = ivy.wf.getSecurityContext().findUser(task.actualApplicantName);
}' #txt
As0 f15 security system #txt
As0 f15 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Initialize</name>
    </language>
</elementInfo>
' #txt
As0 f15 160 43 112 44 -22 -8 #rect
As0 f15 @|StepIcon #fIcon
As0 f24 expr out #txt
As0 f24 108 64 160 65 #arcP
As0 f2 expr out #txt
As0 f2 272 65 435 64 #arcP
>Proto As0 .type ch.ivy.gawfs.workflowExecution.ApprovalForm.ApprovalFormData #txt
>Proto As0 .processKind HTML_DIALOG #txt
>Proto As0 -8 -8 16 16 16 26 #rect
>Proto As0 '' #fIcon
As0 f6 mainOut f11 tail #connect
As0 f11 head f10 mainIn #connect
As0 f10 mainOut f4 tail #connect
As0 f4 head f3 mainIn #connect
As0 f7 mainOut f12 tail #connect
As0 f12 head f9 mainIn #connect
As0 f9 mainOut f14 tail #connect
As0 f14 head f13 mainIn #connect
As0 f16 mainOut f18 tail #connect
As0 f18 head f17 mainIn #connect
As0 f17 mainOut f20 tail #connect
As0 f20 head f19 mainIn #connect
As0 f3 mainOut f22 tail #connect
As0 f22 head f21 mainIn #connect
As0 f21 mainOut f8 tail #connect
As0 f8 head f5 mainIn #connect
As0 f13 mainOut f23 tail #connect
As0 f23 head f21 mainIn #connect
As0 f0 mainOut f24 tail #connect
As0 f24 head f15 mainIn #connect
As0 f15 mainOut f2 tail #connect
As0 f2 head f1 mainIn #connect
