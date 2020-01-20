[Ivy]
15788E435FBCCB49 3.20 #module
>Proto >Proto Collection #zClass
Ws0 WfSecondReviewFormProcess Big #zClass
Ws0 RD #cInfo
Ws0 #process
Ws0 @TextInP .ui2RdDataAction .ui2RdDataAction #zField
Ws0 @TextInP .rdData2UIAction .rdData2UIAction #zField
Ws0 @TextInP .resExport .resExport #zField
Ws0 @TextInP .type .type #zField
Ws0 @TextInP .processKind .processKind #zField
Ws0 @AnnotationInP-0n ai ai #zField
Ws0 @MessageFlowInP-0n messageIn messageIn #zField
Ws0 @MessageFlowOutP-0n messageOut messageOut #zField
Ws0 @TextInP .xml .xml #zField
Ws0 @TextInP .responsibility .responsibility #zField
Ws0 @RichDialogInitStart f0 '' #zField
Ws0 @RichDialogProcessEnd f1 '' #zField
Ws0 @PushWFArc f2 '' #zField
Ws0 @RichDialogProcessStart f3 '' #zField
Ws0 @RichDialogEnd f4 '' #zField
Ws0 @RichDialogProcessStart f6 '' #zField
Ws0 @GridStep f8 '' #zField
Ws0 @PushWFArc f9 '' #zField
Ws0 @GridStep f10 '' #zField
Ws0 @PushWFArc f11 '' #zField
Ws0 @PushWFArc f5 '' #zField
Ws0 @GridStep f12 '' #zField
Ws0 @PushWFArc f13 '' #zField
Ws0 @PushWFArc f7 '' #zField
Ws0 @RichDialogProcessStart f51 '' #zField
Ws0 @GridStep f24 '' #zField
Ws0 @RichDialogProcessEnd f21 '' #zField
Ws0 @PushWFArc f23 '' #zField
Ws0 @PushWFArc f14 '' #zField
>Proto Ws0 Ws0 WfSecondReviewFormProcess #zField
Ws0 f0 guid 15788E4362776D01 #txt
Ws0 f0 type ch.ivy.gawfs.workflowExecution.WfSecondReviewForm.WfSecondReviewFormData #txt
Ws0 f0 method start(gawfs.ExecutePredefinedWorkflowData) #txt
Ws0 f0 disableUIEvents true #txt
Ws0 f0 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<gawfs.ExecutePredefinedWorkflowData executePredefinedWorkflowData> param = methodEvent.getInputArguments();
' #txt
Ws0 f0 inParameterMapAction 'out.executePredefinedWorkflowData=param.executePredefinedWorkflowData;
' #txt
Ws0 f0 outParameterDecl '<gawfs.ExecutePredefinedWorkflowData executePredefinedWorkflowData> result;
' #txt
Ws0 f0 outParameterMapAction 'result.executePredefinedWorkflowData=in.executePredefinedWorkflowData;
' #txt
Ws0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start(executePredefinedWorkflowData)</name>
    </language>
</elementInfo>
' #txt
Ws0 f0 83 51 26 26 -105 15 #rect
Ws0 f0 @|RichDialogInitStartIcon #fIcon
Ws0 f1 type ch.ivy.gawfs.workflowExecution.WfSecondReviewForm.WfSecondReviewFormData #txt
Ws0 f1 211 51 26 26 0 12 #rect
Ws0 f1 @|RichDialogProcessEndIcon #fIcon
Ws0 f2 expr out #txt
Ws0 f2 109 64 211 64 #arcP
Ws0 f3 guid 15788E4367EB596F #txt
Ws0 f3 type ch.ivy.gawfs.workflowExecution.WfSecondReviewForm.WfSecondReviewFormData #txt
Ws0 f3 actionDecl 'ch.ivy.gawfs.workflowExecution.WfSecondReviewForm.WfSecondReviewFormData out;
' #txt
Ws0 f3 actionTable 'out=in;
' #txt
Ws0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>closeApprove</name>
        <nameStyle>12,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ws0 f3 83 147 26 26 -37 15 #rect
Ws0 f3 @|RichDialogProcessStartIcon #fIcon
Ws0 f4 type ch.ivy.gawfs.workflowExecution.WfSecondReviewForm.WfSecondReviewFormData #txt
Ws0 f4 guid 15788E4367E40EAD #txt
Ws0 f4 563 147 26 26 0 12 #rect
Ws0 f4 @|RichDialogEndIcon #fIcon
Ws0 f6 guid 157890904ACBAD13 #txt
Ws0 f6 type ch.ivy.gawfs.workflowExecution.WfSecondReviewForm.WfSecondReviewFormData #txt
Ws0 f6 actionDecl 'ch.ivy.gawfs.workflowExecution.WfSecondReviewForm.WfSecondReviewFormData out;
' #txt
Ws0 f6 actionTable 'out=in;
' #txt
Ws0 f6 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>closeDeny</name>
        <nameStyle>9,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ws0 f6 83 211 26 26 -29 15 #rect
Ws0 f6 @|RichDialogProcessStartIcon #fIcon
Ws0 f8 actionDecl 'ch.ivy.gawfs.workflowExecution.WfSecondReviewForm.WfSecondReviewFormData out;
' #txt
Ws0 f8 actionTable 'out=in;
' #txt
Ws0 f8 actionCode 'in.executePredefinedWorkflowData.denied = true;' #txt
Ws0 f8 security system #txt
Ws0 f8 type ch.ivy.gawfs.workflowExecution.WfSecondReviewForm.WfSecondReviewFormData #txt
Ws0 f8 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>denied = true</name>
        <nameStyle>13,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ws0 f8 192 202 112 44 -36 -8 #rect
Ws0 f8 @|StepIcon #fIcon
Ws0 f9 expr out #txt
Ws0 f9 109 224 192 224 #arcP
Ws0 f9 0 0.6055751278069843 0 0 #arcLabel
Ws0 f10 actionDecl 'ch.ivy.gawfs.workflowExecution.WfSecondReviewForm.WfSecondReviewFormData out;
' #txt
Ws0 f10 actionTable 'out=in;
' #txt
Ws0 f10 actionCode 'String note = ivy.session.getSessionUserName() + ":" + ivy.cms.co("/Dialogs/Tasks/Approved/TaskName"); 

ivy.case.createNote(ivy.session, note);
' #txt
Ws0 f10 security system #txt
Ws0 f10 type ch.ivy.gawfs.workflowExecution.WfSecondReviewForm.WfSecondReviewFormData #txt
Ws0 f10 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Update History</name>
        <nameStyle>14,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ws0 f10 352 138 112 44 -40 -8 #rect
Ws0 f10 @|StepIcon #fIcon
Ws0 f11 expr out #txt
Ws0 f11 109 160 352 160 #arcP
Ws0 f5 expr out #txt
Ws0 f5 464 160 563 160 #arcP
Ws0 f12 actionDecl 'ch.ivy.gawfs.workflowExecution.WfSecondReviewForm.WfSecondReviewFormData out;
' #txt
Ws0 f12 actionTable 'out=in;
' #txt
Ws0 f12 actionCode 'String note = ivy.session.getSessionUserName() + ":" + ivy.cms.co("/Dialogs/Tasks/Declined/TaskName"); 

ivy.case.createNote(ivy.session, note);
' #txt
Ws0 f12 security system #txt
Ws0 f12 type ch.ivy.gawfs.workflowExecution.WfSecondReviewForm.WfSecondReviewFormData #txt
Ws0 f12 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Update History</name>
        <nameStyle>14,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ws0 f12 352 202 112 44 -40 -8 #rect
Ws0 f12 @|StepIcon #fIcon
Ws0 f13 expr out #txt
Ws0 f13 304 224 352 224 #arcP
Ws0 f13 0 0.451049060711409 0 0 #arcLabel
Ws0 f7 expr out #txt
Ws0 f7 464 224 576 173 #arcP
Ws0 f7 1 576 224 #addKink
Ws0 f7 0 0.8118286780646053 0 0 #arcLabel
Ws0 f51 guid 158944905A6EF4D7 #txt
Ws0 f51 type ch.ivy.gawfs.workflowExecution.WfSecondReviewForm.WfSecondReviewFormData #txt
Ws0 f51 actionDecl 'ch.ivy.gawfs.workflowExecution.WfSecondReviewForm.WfSecondReviewFormData out;
' #txt
Ws0 f51 actionTable 'out=in;
' #txt
Ws0 f51 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>cancel</name>
        <nameStyle>6,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ws0 f51 83 291 26 26 -18 15 #rect
Ws0 f51 @|RichDialogProcessStartIcon #fIcon
Ws0 f24 actionDecl 'ch.ivy.gawfs.workflowExecution.WfSecondReviewForm.WfSecondReviewFormData out;
' #txt
Ws0 f24 actionTable 'out=in;
' #txt
Ws0 f24 actionCode 'import ch.ivy.addon.portalkit.enums.SessionAttribute;
import ch.ivy.addon.portalkit.service.ProcessStartCollector;
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
ivy.session.setAttribute(SessionAttribute.IS_TASK_FINISHED.toString(), false);
FacesContext.getCurrentInstance().getExternalContext().redirect(link);' #txt
Ws0 f24 security system #txt
Ws0 f24 type ch.ivy.gawfs.workflowExecution.WfSecondReviewForm.WfSecondReviewFormData #txt
Ws0 f24 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>reset task</name>
        <nameStyle>10,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ws0 f24 180 284 104 40 -28 -6 #rect
Ws0 f24 @|StepIcon #fIcon
Ws0 f21 type ch.ivy.gawfs.workflowExecution.WfSecondReviewForm.WfSecondReviewFormData #txt
Ws0 f21 355 291 26 26 0 12 #rect
Ws0 f21 @|RichDialogProcessEndIcon #fIcon
Ws0 f23 expr out #txt
Ws0 f23 284 304 355 304 #arcP
Ws0 f14 expr out #txt
Ws0 f14 109 304 180 304 #arcP
>Proto Ws0 .type ch.ivy.gawfs.workflowExecution.WfSecondReviewForm.WfSecondReviewFormData #txt
>Proto Ws0 .processKind HTML_DIALOG #txt
>Proto Ws0 -8 -8 16 16 16 26 #rect
>Proto Ws0 '' #fIcon
Ws0 f0 mainOut f2 tail #connect
Ws0 f2 head f1 mainIn #connect
Ws0 f6 mainOut f9 tail #connect
Ws0 f9 head f8 mainIn #connect
Ws0 f3 mainOut f11 tail #connect
Ws0 f11 head f10 mainIn #connect
Ws0 f10 mainOut f5 tail #connect
Ws0 f5 head f4 mainIn #connect
Ws0 f8 mainOut f13 tail #connect
Ws0 f13 head f12 mainIn #connect
Ws0 f12 mainOut f7 tail #connect
Ws0 f7 head f4 mainIn #connect
Ws0 f24 mainOut f23 tail #connect
Ws0 f23 head f21 mainIn #connect
Ws0 f51 mainOut f14 tail #connect
Ws0 f14 head f24 mainIn #connect
