[Ivy]
[>Created: Thu May 11 14:51:33 ICT 2017]
157891957447B0FC 3.20 #module
>Proto >Proto Collection #zClass
Ws0 WfFinalReviewProcess Big #zClass
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
Ws0 @PushWFArc f5 '' #zField
Ws0 @RichDialogProcessStart f51 '' #zField
Ws0 @GridStep f24 '' #zField
Ws0 @RichDialogProcessEnd f21 '' #zField
Ws0 @PushWFArc f23 '' #zField
Ws0 @PushWFArc f6 '' #zField
>Proto Ws0 Ws0 WfFinalReviewProcess #zField
Ws0 f0 guid 1578919576D899E8 #txt
Ws0 f0 type ch.ivy.gawfs.workflowExecution.WfFinalReview.WfFinalReviewData #txt
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
Ws0 f1 type ch.ivy.gawfs.workflowExecution.WfFinalReview.WfFinalReviewData #txt
Ws0 f1 211 51 26 26 0 12 #rect
Ws0 f1 @|RichDialogProcessEndIcon #fIcon
Ws0 f2 expr out #txt
Ws0 f2 109 64 211 64 #arcP
Ws0 f3 guid 1578919577872B2E #txt
Ws0 f3 type ch.ivy.gawfs.workflowExecution.WfFinalReview.WfFinalReviewData #txt
Ws0 f3 actionDecl 'ch.ivy.gawfs.workflowExecution.WfFinalReview.WfFinalReviewData out;
' #txt
Ws0 f3 actionTable 'out=in;
' #txt
Ws0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>close</name>
    </language>
</elementInfo>
' #txt
Ws0 f3 83 147 26 26 -15 12 #rect
Ws0 f3 @|RichDialogProcessStartIcon #fIcon
Ws0 f4 type ch.ivy.gawfs.workflowExecution.WfFinalReview.WfFinalReviewData #txt
Ws0 f4 guid 157891957797EA82 #txt
Ws0 f4 211 147 26 26 0 12 #rect
Ws0 f4 @|RichDialogEndIcon #fIcon
Ws0 f5 expr out #txt
Ws0 f5 109 160 211 160 #arcP
Ws0 f51 guid 158944C4926E3D04 #txt
Ws0 f51 type ch.ivy.gawfs.workflowExecution.WfFinalReview.WfFinalReviewData #txt
Ws0 f51 actionDecl 'ch.ivy.gawfs.workflowExecution.WfFinalReview.WfFinalReviewData out;
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
Ws0 f51 83 227 26 26 -18 15 #rect
Ws0 f51 @|RichDialogProcessStartIcon #fIcon
Ws0 f24 actionDecl 'ch.ivy.gawfs.workflowExecution.WfFinalReview.WfFinalReviewData out;
' #txt
Ws0 f24 actionTable 'out=in;
' #txt
Ws0 f24 actionCode 'import ch.ivyteam.ivy.server.ServerFactory;
import ch.ivyteam.ivy.request.RequestUriFactory;
import ch.ivyteam.ivy.workflow.CaseState;
import javax.servlet.http.HttpServletRequest;
import javax.faces.context.FacesContext;
import ch.ivyteam.ivy.workflow.IProcessStart;
import ch.ivyteam.ivy.richdialog.exec.ProcessStartConfiguration;
 
//ivy.task.destroy();
ivy.task.reset();

IProcessStart processStart;
for (IProcessStart start : ivy.session.getStartableProcessStarts()) {
	if (start.getProcessElementId().equals("1576E76B009E23DD-f0")) {
  	processStart = start;
		break;
	}
}

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
//ivy.log.debug("Link to Portal found:"+link);
FacesContext.getCurrentInstance().getExternalContext().redirect(link);
' #txt
Ws0 f24 security system #txt
Ws0 f24 type ch.ivy.gawfs.workflowExecution.WfFinalReview.WfFinalReviewData #txt
Ws0 f24 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>reset task</name>
        <nameStyle>10,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ws0 f24 172 220 104 40 -28 -6 #rect
Ws0 f24 @|StepIcon #fIcon
Ws0 f21 type ch.ivy.gawfs.workflowExecution.WfFinalReview.WfFinalReviewData #txt
Ws0 f21 347 227 26 26 0 12 #rect
Ws0 f21 @|RichDialogProcessEndIcon #fIcon
Ws0 f23 expr out #txt
Ws0 f23 276 240 347 240 #arcP
Ws0 f6 expr out #txt
Ws0 f6 109 240 172 240 #arcP
>Proto Ws0 .type ch.ivy.gawfs.workflowExecution.WfFinalReview.WfFinalReviewData #txt
>Proto Ws0 .processKind HTML_DIALOG #txt
>Proto Ws0 -8 -8 16 16 16 26 #rect
>Proto Ws0 '' #fIcon
Ws0 f0 mainOut f2 tail #connect
Ws0 f2 head f1 mainIn #connect
Ws0 f3 mainOut f5 tail #connect
Ws0 f5 head f4 mainIn #connect
Ws0 f24 mainOut f23 tail #connect
Ws0 f23 head f21 mainIn #connect
Ws0 f51 mainOut f6 tail #connect
Ws0 f6 head f24 mainIn #connect
