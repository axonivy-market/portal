[Ivy]
162F55163FD8DF16 3.23 #module
>Proto >Proto Collection #zClass
As0 ApprovalFormProcess Big #zClass
As0 RD #cInfo
As0 #process
As0 @TextInP .ui2RdDataAction .ui2RdDataAction #zField
As0 @TextInP .rdData2UIAction .rdData2UIAction #zField
As0 @TextInP .resExport .resExport #zField
As0 @TextInP .type .type #zField
As0 @TextInP .processKind .processKind #zField
As0 @AnnotationInP-0n ai ai #zField
As0 @MessageFlowInP-0n messageIn messageIn #zField
As0 @MessageFlowOutP-0n messageOut messageOut #zField
As0 @TextInP .xml .xml #zField
As0 @TextInP .responsibility .responsibility #zField
As0 @RichDialogInitStart f0 '' #zField
As0 @RichDialogProcessEnd f1 '' #zField
As0 @PushWFArc f2 '' #zField
As0 @RichDialogProcessStart f6 '' #zField
As0 @RichDialogProcessStart f7 '' #zField
As0 @GridStep f3 '' #zField
As0 @RichDialogEnd f5 '' #zField
As0 @PushWFArc f8 '' #zField
As0 @GridStep f9 '' #zField
As0 @GridStep f10 '' #zField
As0 @PushWFArc f11 '' #zField
As0 @PushWFArc f4 '' #zField
As0 @PushWFArc f12 '' #zField
As0 @GridStep f13 '' #zField
As0 @PushWFArc f14 '' #zField
As0 @PushWFArc f15 '' #zField
As0 @RichDialogProcessStart f16 '' #zField
As0 @GridStep f17 '' #zField
As0 @PushWFArc f18 '' #zField
As0 @PushWFArc f19 '' #zField
>Proto As0 As0 ApprovalFormProcess #zField
As0 f0 guid 162F55164217492D #txt
As0 f0 type ch.ivy.gawfs.workflowExecution.ApprovalForm.ApprovalFormData #txt
As0 f0 method start(gawfs.TaskDef,String,java.util.List<String>,java.lang.Integer) #txt
As0 f0 disableUIEvents true #txt
As0 f0 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<gawfs.TaskDef userTask,java.lang.String applicantName,java.util.List<java.lang.String> steps,java.lang.Integer actualStepIndex> param = methodEvent.getInputArguments();
' #txt
As0 f0 inParameterMapAction 'out.actualStepIndex=param.actualStepIndex;
out.steps=param.steps;
out.userTask=param.userTask;
' #txt
As0 f0 inActionCode 'out.applicant = ivy.session.getSecurityContext().findSecurityMember(param.applicantName);' #txt
As0 f0 outParameterDecl '<gawfs.ApprovalTaskResult approvalResult> result;
' #txt
As0 f0 outParameterMapAction 'result.approvalResult=in.approvalResult;
' #txt
As0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start()</name>
        <nameStyle>7,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
As0 f0 83 51 26 26 -16 15 #rect
As0 f0 @|RichDialogInitStartIcon #fIcon
As0 f1 type ch.ivy.gawfs.workflowExecution.ApprovalForm.ApprovalFormData #txt
As0 f1 211 51 26 26 0 12 #rect
As0 f1 @|RichDialogProcessEndIcon #fIcon
As0 f2 expr out #txt
As0 f2 109 64 211 64 #arcP
As0 f6 guid 163066332FC888BC #txt
As0 f6 type ch.ivy.gawfs.workflowExecution.ApprovalForm.ApprovalFormData #txt
As0 f6 actionDecl 'ch.ivy.gawfs.workflowExecution.ApprovalForm.ApprovalFormData out;
' #txt
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
As0 f6 @|RichDialogProcessStartIcon #fIcon
As0 f7 guid 1630663828B44965 #txt
As0 f7 type ch.ivy.gawfs.workflowExecution.ApprovalForm.ApprovalFormData #txt
As0 f7 actionDecl 'ch.ivy.gawfs.workflowExecution.ApprovalForm.ApprovalFormData out;
' #txt
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
As0 f7 83 259 26 26 -29 15 #rect
As0 f7 @|RichDialogProcessStartIcon #fIcon
As0 f3 actionDecl 'ch.ivy.gawfs.workflowExecution.ApprovalForm.ApprovalFormData out;
' #txt
As0 f3 actionTable 'out=in;
' #txt
As0 f3 actionCode 'String note = ivy.session.getSessionUserName() + ":" + ivy.cms.co("/Dialogs/Tasks/Approved/TaskName"); 
ivy.case.createNote(ivy.session, note);
' #txt
As0 f3 type ch.ivy.gawfs.workflowExecution.ApprovalForm.ApprovalFormData #txt
As0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Update history</name>
    </language>
</elementInfo>
' #txt
As0 f3 352 138 112 44 -39 -8 #rect
As0 f3 @|StepIcon #fIcon
As0 f5 type ch.ivy.gawfs.workflowExecution.ApprovalForm.ApprovalFormData #txt
As0 f5 guid 1630664477BFCAF5 #txt
As0 f5 531 147 26 26 0 12 #rect
As0 f5 @|RichDialogEndIcon #fIcon
As0 f8 expr out #txt
As0 f8 464 160 531 160 #arcP
As0 f9 actionDecl 'ch.ivy.gawfs.workflowExecution.ApprovalForm.ApprovalFormData out;
' #txt
As0 f9 actionTable 'out=in;
' #txt
As0 f9 actionCode 'in.approvalResult.setApproverName(ivy.session.getSessionUserName());
in.approvalResult.setApplicantName(in.applicant.getMemberName());
in.approvalResult.setIsApproved(false);
in.approvalResult.setRejectedReason(in.rejectedReason);
in.approvalResult.setTaskName(in.steps.get(in.actualStepIndex));' #txt
As0 f9 type ch.ivy.gawfs.workflowExecution.ApprovalForm.ApprovalFormData #txt
As0 f9 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Update result</name>
    </language>
</elementInfo>
' #txt
As0 f9 160 250 112 44 -37 -8 #rect
As0 f9 @|StepIcon #fIcon
As0 f10 actionDecl 'ch.ivy.gawfs.workflowExecution.ApprovalForm.ApprovalFormData out;
' #txt
As0 f10 actionTable 'out=in;
' #txt
As0 f10 actionCode 'in.approvalResult.setApproverName(ivy.session.getSessionUserName());
in.approvalResult.setApplicantName(in.applicant.getMemberName());
in.approvalResult.setIsApproved(true);
in.approvalResult.setRejectedReason("");
in.approvalResult.setTaskName(in.steps.get(in.actualStepIndex));' #txt
As0 f10 type ch.ivy.gawfs.workflowExecution.ApprovalForm.ApprovalFormData #txt
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
As0 f12 109 272 160 272 #arcP
As0 f13 actionDecl 'ch.ivy.gawfs.workflowExecution.ApprovalForm.ApprovalFormData out;
' #txt
As0 f13 actionTable 'out=in;
' #txt
As0 f13 actionCode 'String note = ivy.session.getSessionUserName() + ":" + ivy.cms.co("/Dialogs/Tasks/Declined/TaskName"); 
ivy.case.createNote(ivy.session, note);' #txt
As0 f13 type ch.ivy.gawfs.workflowExecution.ApprovalForm.ApprovalFormData #txt
As0 f13 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Update history</name>
    </language>
</elementInfo>
' #txt
As0 f13 352 250 112 44 -39 -8 #rect
As0 f13 @|StepIcon #fIcon
As0 f14 expr out #txt
As0 f14 272 272 352 272 #arcP
As0 f15 expr out #txt
As0 f15 464 272 544 173 #arcP
As0 f15 1 544 272 #addKink
As0 f15 0 0.8533831173785638 0 0 #arcLabel
As0 f16 guid 163066874021794C #txt
As0 f16 type ch.ivy.gawfs.workflowExecution.ApprovalForm.ApprovalFormData #txt
As0 f16 actionDecl 'ch.ivy.gawfs.workflowExecution.ApprovalForm.ApprovalFormData out;
' #txt
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
As0 f16 83 371 26 26 -18 15 #rect
As0 f16 @|RichDialogProcessStartIcon #fIcon
As0 f17 actionDecl 'ch.ivy.gawfs.workflowExecution.ApprovalForm.ApprovalFormData out;
' #txt
As0 f17 actionTable 'out=in;
' #txt
As0 f17 actionCode 'import ch.ivy.addon.portalkit.service.ProcessStartCollector;
import ch.ivyteam.ivy.server.ServerFactory;
import ch.ivyteam.ivy.request.RequestUriFactory;
import ch.ivyteam.ivy.workflow.CaseState;
import javax.servlet.http.HttpServletRequest;
import javax.faces.context.FacesContext;
import ch.ivyteam.ivy.workflow.IProcessStart;
import ch.ivyteam.ivy.richdialog.exec.ProcessStartConfiguration;
 
ivy.task.reset();

String processStartLink = "Start Processes/PortalStart/DefaultEndPage.ivp";
ProcessStartCollector collector = new ProcessStartCollector(ivy.request.getApplication());
IProcessStart processStart = collector.findProcessStartByUserFriendlyRequestPath(processStartLink);
String link = RequestUriFactory.createProcessStartUri(ServerFactory.getServer().getApplicationConfigurationManager(), processStart).toString();

if(processStart != null) {
	if (!ivy.case.getState().equals(CaseState.ZOMBIE) && !ivy.case.getState().equals(CaseState.CREATED)) {
  	link += "?taskIdentifier="+ivy.task.getId();
  }
}

if (ivy.case.getState().equals(CaseState.ZOMBIE)) {
	ivy.wf.deleteCompletedCase(ivy.case);
}

//redirect to portal
FacesContext.getCurrentInstance().getExternalContext().redirect(link);' #txt
As0 f17 type ch.ivy.gawfs.workflowExecution.ApprovalForm.ApprovalFormData #txt
As0 f17 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Reset task</name>
    </language>
</elementInfo>
' #txt
As0 f17 160 362 112 44 -29 -8 #rect
As0 f17 @|StepIcon #fIcon
As0 f18 expr out #txt
As0 f18 109 384 160 384 #arcP
As0 f19 expr out #txt
As0 f19 272 384 544 173 #arcP
As0 f19 1 544 384 #addKink
As0 f19 0 0.8996212121212122 0 0 #arcLabel
>Proto As0 .type ch.ivy.gawfs.workflowExecution.ApprovalForm.ApprovalFormData #txt
>Proto As0 .processKind HTML_DIALOG #txt
>Proto As0 -8 -8 16 16 16 26 #rect
>Proto As0 '' #fIcon
As0 f0 mainOut f2 tail #connect
As0 f2 head f1 mainIn #connect
As0 f3 mainOut f8 tail #connect
As0 f8 head f5 mainIn #connect
As0 f6 mainOut f11 tail #connect
As0 f11 head f10 mainIn #connect
As0 f10 mainOut f4 tail #connect
As0 f4 head f3 mainIn #connect
As0 f7 mainOut f12 tail #connect
As0 f12 head f9 mainIn #connect
As0 f9 mainOut f14 tail #connect
As0 f14 head f13 mainIn #connect
As0 f13 mainOut f15 tail #connect
As0 f15 head f5 mainIn #connect
As0 f16 mainOut f18 tail #connect
As0 f18 head f17 mainIn #connect
As0 f17 mainOut f19 tail #connect
As0 f19 head f5 mainIn #connect
