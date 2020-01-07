[Ivy]
157893EF862D1265 3.20 #module
>Proto >Proto Collection #zClass
Ws0 WfFirstInputFormProcess Big #zClass
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
Ws0 @RichDialogProcessStart f3 '' #zField
Ws0 @GridStep f6 '' #zField
Ws0 @PushWFArc f7 '' #zField
Ws0 @RichDialogMethodStart f8 '' #zField
Ws0 @GridStep f10 '' #zField
Ws0 @PushWFArc f11 '' #zField
Ws0 @PushWFArc f2 '' #zField
Ws0 @RichDialogMethodStart f12 '' #zField
Ws0 @GridStep f14 '' #zField
Ws0 @PushWFArc f15 '' #zField
Ws0 @PushWFArc f9 '' #zField
Ws0 @GridStep f16 '' #zField
Ws0 @PushWFArc f17 '' #zField
Ws0 @PushWFArc f13 '' #zField
Ws0 @RichDialogMethodStart f19 '' #zField
Ws0 @GridStep f24 '' #zField
Ws0 @PushWFArc f22 '' #zField
Ws0 @RichDialogEnd f30 '' #zField
Ws0 @PushWFArc f4 '' #zField
Ws0 @RichDialogEnd f5 '' #zField
Ws0 @PushWFArc f18 '' #zField
Ws0 @RichDialogMethodStart f20 '' #zField
Ws0 @RichDialogProcessEnd f21 '' #zField
Ws0 @PushWFArc f23 '' #zField
>Proto Ws0 Ws0 WfFirstInputFormProcess #zField
Ws0 f0 guid 157884982E527C49 #txt
Ws0 f0 type ch.ivy.gawfs.workflowExecution.WfFirstInputForm.WfFirstInputFormData #txt
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
Ws0 f1 type ch.ivy.gawfs.workflowExecution.WfFirstInputForm.WfFirstInputFormData #txt
Ws0 f1 563 51 26 26 0 12 #rect
Ws0 f1 @|RichDialogProcessEndIcon #fIcon
Ws0 f3 guid 1578849833211911 #txt
Ws0 f3 type ch.ivy.gawfs.workflowExecution.WfFirstInputForm.WfFirstInputFormData #txt
Ws0 f3 actionDecl 'ch.ivy.gawfs.workflowExecution.WfFirstInputForm.WfFirstInputFormData out;
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
Ws0 f3 83 467 26 26 -15 12 #rect
Ws0 f3 @|RichDialogProcessStartIcon #fIcon
Ws0 f6 actionDecl 'ch.ivy.gawfs.workflowExecution.WfFirstInputForm.WfFirstInputFormData out;
' #txt
Ws0 f6 actionTable 'out=in;
' #txt
Ws0 f6 actionCode 'in.executePredefinedWorkflowData.dynaFormController.createForm();
' #txt
Ws0 f6 security system #txt
Ws0 f6 type ch.ivy.gawfs.workflowExecution.WfFirstInputForm.WfFirstInputFormData #txt
Ws0 f6 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>init dynaFormModel</name>
        <nameStyle>18,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ws0 f6 192 42 112 44 -53 -8 #rect
Ws0 f6 @|StepIcon #fIcon
Ws0 f7 expr out #txt
Ws0 f7 109 64 192 64 #arcP
Ws0 f8 guid 15789432456F5CA9 #txt
Ws0 f8 type ch.ivy.gawfs.workflowExecution.WfFirstInputForm.WfFirstInputFormData #txt
Ws0 f8 method setApplicant() #txt
Ws0 f8 disableUIEvents false #txt
Ws0 f8 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<> param = methodEvent.getInputArguments();
' #txt
Ws0 f8 outParameterDecl '<> result;
' #txt
Ws0 f8 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>setApplicant</name>
        <nameStyle>12,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ws0 f8 83 179 26 26 -33 15 #rect
Ws0 f8 @|RichDialogMethodStartIcon #fIcon
Ws0 f10 actionDecl 'ch.ivy.gawfs.workflowExecution.WfFirstInputForm.WfFirstInputFormData out;
' #txt
Ws0 f10 actionTable 'out=in;
' #txt
Ws0 f10 actionCode 'import ch.ivy.gawfs.Helper;
import ch.ivyteam.ivy.security.IUser;

//inital soll der Antragssteller "im eigenen Namen" sein.
in.assignementType = 0;

//Get Users
List<IUser> users = ivy.wf.getSecurityContext().getUsers();
in.availUsers.clear();

for(IUser user : users)
{
	if(user.getName() != "SYSTEM")
	{
			out.availUsers.add(user);
	}
}

out.availUsers = Helper.sortUsers(out.availUsers);

in.executePredefinedWorkflowData.applicant.user = ivy.case.getCreatorUser();

in.executePredefinedWorkflowData.applicant.fullname = in.executePredefinedWorkflowData.applicant.user.getFullName();
in.executePredefinedWorkflowData.applicant.username = in.executePredefinedWorkflowData.applicant.user.getName();
in.executePredefinedWorkflowData.applicant.email = in.executePredefinedWorkflowData.applicant.user.getEMailAddress();
' #txt
Ws0 f10 security system #txt
Ws0 f10 type ch.ivy.gawfs.workflowExecution.WfFirstInputForm.WfFirstInputFormData #txt
Ws0 f10 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>init applicant selection</name>
        <nameStyle>24,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ws0 f10 352 42 128 44 -61 -8 #rect
Ws0 f10 @|StepIcon #fIcon
Ws0 f11 expr out #txt
Ws0 f11 304 64 352 64 #arcP
Ws0 f2 expr out #txt
Ws0 f2 480 64 563 64 #arcP
Ws0 f12 guid 1579C6865DF237DB #txt
Ws0 f12 type ch.ivy.gawfs.workflowExecution.WfFirstInputForm.WfFirstInputFormData #txt
Ws0 f12 method updateApplicant() #txt
Ws0 f12 disableUIEvents false #txt
Ws0 f12 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<> param = methodEvent.getInputArguments();
' #txt
Ws0 f12 outParameterDecl '<> result;
' #txt
Ws0 f12 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>updateApplicant()</name>
    </language>
</elementInfo>
' #txt
Ws0 f12 83 275 26 26 -48 15 #rect
Ws0 f12 @|RichDialogMethodStartIcon #fIcon
Ws0 f14 actionDecl 'ch.ivy.gawfs.workflowExecution.WfFirstInputForm.WfFirstInputFormData out;
' #txt
Ws0 f14 actionTable 'out=in;
' #txt
Ws0 f14 actionCode 'import org.primefaces.context.RequestContext;

if(in.assignementType == 0){
	in.executePredefinedWorkflowData.applicant.user = ivy.case.getCreatorUser();
	in.executePredefinedWorkflowData.applicant.fullname = in.executePredefinedWorkflowData.applicant.user.getFullName();
	in.executePredefinedWorkflowData.applicant.username = in.executePredefinedWorkflowData.applicant.user.getName();
	in.executePredefinedWorkflowData.applicant.email = in.executePredefinedWorkflowData.applicant.user.getEMailAddress();
}else{
	RequestContext context = RequestContext.getCurrentInstance();
	context.execute("PF(''chooseApplicant'').show();");
};
' #txt
Ws0 f14 security system #txt
Ws0 f14 type ch.ivy.gawfs.workflowExecution.WfFirstInputForm.WfFirstInputFormData #txt
Ws0 f14 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>set applicant self assignment
or open user selection</name>
        <nameStyle>52,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ws0 f14 224 170 192 44 -76 -16 #rect
Ws0 f14 @|StepIcon #fIcon
Ws0 f15 expr out #txt
Ws0 f15 109 192 224 192 #arcP
Ws0 f15 0 0.6296828726347244 0 0 #arcLabel
Ws0 f9 expr out #txt
Ws0 f9 416 192 576 77 #arcP
Ws0 f9 1 576 192 #addKink
Ws0 f9 0 0.8143467175606731 0 0 #arcLabel
Ws0 f16 actionDecl 'ch.ivy.gawfs.workflowExecution.WfFirstInputForm.WfFirstInputFormData out;
' #txt
Ws0 f16 actionTable 'out=in;
' #txt
Ws0 f16 actionCode 'import org.primefaces.context.RequestContext;
import ch.ivyteam.ivy.security.IUser;

in.assignementType=1;

in.executePredefinedWorkflowData.applicant.fullname = in.executePredefinedWorkflowData.applicant.user.getFullName();
in.executePredefinedWorkflowData.applicant.username = in.executePredefinedWorkflowData.applicant.user.getName();
in.executePredefinedWorkflowData.applicant.email = in.executePredefinedWorkflowData.applicant.user.getEMailAddress();' #txt
Ws0 f16 security system #txt
Ws0 f16 type ch.ivy.gawfs.workflowExecution.WfFirstInputForm.WfFirstInputFormData #txt
Ws0 f16 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>set applicant selection</name>
        <nameStyle>23,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ws0 f16 256 266 128 44 -61 -8 #rect
Ws0 f16 @|StepIcon #fIcon
Ws0 f17 expr out #txt
Ws0 f17 109 288 256 288 #arcP
Ws0 f17 0 0.7318949147687047 0 0 #arcLabel
Ws0 f13 expr out #txt
Ws0 f13 384 288 576 77 #arcP
Ws0 f13 1 576 288 #addKink
Ws0 f13 0 0.9207759598184083 0 0 #arcLabel
Ws0 f19 guid 1589407341335CE8 #txt
Ws0 f19 type ch.ivy.gawfs.workflowExecution.WfFirstInputForm.WfFirstInputFormData #txt
Ws0 f19 method cancel() #txt
Ws0 f19 disableUIEvents false #txt
Ws0 f19 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<> param = methodEvent.getInputArguments();
' #txt
Ws0 f19 outParameterDecl '<> result;
' #txt
Ws0 f19 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>cancel</name>
        <nameStyle>6,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ws0 f19 83 371 26 26 -18 15 #rect
Ws0 f19 @|RichDialogMethodStartIcon #fIcon
Ws0 f24 actionDecl 'ch.ivy.gawfs.workflowExecution.WfFirstInputForm.WfFirstInputFormData out;
' #txt
Ws0 f24 actionTable 'out=in;
' #txt
Ws0 f24 actionCode 'import ch.ivy.addon.portalkit.enums.SessionAttribute;
import ch.ivy.addon.portalkit.util.SecurityServiceUtils;
import ch.ivy.addon.portalkit.service.ProcessStartCollector;
import ch.ivyteam.ivy.server.ServerFactory;
import ch.ivyteam.ivy.request.RequestUriFactory;
import ch.ivyteam.ivy.workflow.CaseState;
import javax.servlet.http.HttpServletRequest;
import javax.faces.context.FacesContext;
import ch.ivyteam.ivy.workflow.IProcessStart;
import ch.ivyteam.ivy.richdialog.exec.ProcessStartConfiguration;


ivy.task.destroy();

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
//ivy.log.debug("Link to Portal found:"+link);

ivy.session.setAttribute(SessionAttribute.IS_TASK_FINISHED.toString(), false);

FacesContext.getCurrentInstance().getExternalContext().redirect(link);' #txt
Ws0 f24 security system #txt
Ws0 f24 type ch.ivy.gawfs.workflowExecution.WfFirstInputForm.WfFirstInputFormData #txt
Ws0 f24 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>reset task</name>
        <nameStyle>10,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ws0 f24 256 364 104 40 -28 -6 #rect
Ws0 f24 @|StepIcon #fIcon
Ws0 f22 expr out #txt
Ws0 f22 109 384 256 384 #arcP
Ws0 f22 0 0.5145499197722859 0 0 #arcLabel
Ws0 f30 type ch.ivy.gawfs.workflowExecution.WfFirstInputForm.WfFirstInputFormData #txt
Ws0 f30 guid 15AF77AA5D3C3910 #txt
Ws0 f30 563 467 26 26 0 12 #rect
Ws0 f30 @|RichDialogEndIcon #fIcon
Ws0 f4 expr out #txt
Ws0 f4 109 480 563 480 #arcP
Ws0 f5 type ch.ivy.gawfs.workflowExecution.WfFirstInputForm.WfFirstInputFormData #txt
Ws0 f5 guid 15F03F02989D33A9 #txt
Ws0 f5 563 371 26 26 0 12 #rect
Ws0 f5 @|RichDialogEndIcon #fIcon
Ws0 f18 expr out #txt
Ws0 f18 360 384 563 384 #arcP
Ws0 f20 guid 15F95CCE408656C6 #txt
Ws0 f20 type ch.ivy.gawfs.workflowExecution.WfFirstInputForm.WfFirstInputFormData #txt
Ws0 f20 method autoCompleteFilter(String) #txt
Ws0 f20 disableUIEvents false #txt
Ws0 f20 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<java.lang.String query> param = methodEvent.getInputArguments();
' #txt
Ws0 f20 inParameterMapAction 'out.autoCompleteQuery=param.query;
' #txt
Ws0 f20 outParameterDecl '<java.util.List<ch.ivyteam.ivy.security.IUser> availUsers> result;
' #txt
Ws0 f20 outActionCode 'import ch.ivy.gawfs.Helper;

result.availUsers = Helper.filterUsers(in.availUsers, in.autoCompleteQuery);' #txt
Ws0 f20 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>autoCompleteFilter(String)</name>
        <nameStyle>26,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ws0 f20 83 563 26 26 -72 15 #rect
Ws0 f20 @|RichDialogMethodStartIcon #fIcon
Ws0 f21 type ch.ivy.gawfs.workflowExecution.WfFirstInputForm.WfFirstInputFormData #txt
Ws0 f21 563 563 26 26 0 12 #rect
Ws0 f21 @|RichDialogProcessEndIcon #fIcon
Ws0 f23 expr out #txt
Ws0 f23 109 576 563 576 #arcP
>Proto Ws0 .type ch.ivy.gawfs.workflowExecution.WfFirstInputForm.WfFirstInputFormData #txt
>Proto Ws0 .processKind HTML_DIALOG #txt
>Proto Ws0 -8 -8 16 16 16 26 #rect
>Proto Ws0 '' #fIcon
Ws0 f0 mainOut f7 tail #connect
Ws0 f7 head f6 mainIn #connect
Ws0 f6 mainOut f11 tail #connect
Ws0 f11 head f10 mainIn #connect
Ws0 f10 mainOut f2 tail #connect
Ws0 f2 head f1 mainIn #connect
Ws0 f8 mainOut f15 tail #connect
Ws0 f15 head f14 mainIn #connect
Ws0 f14 mainOut f9 tail #connect
Ws0 f9 head f1 mainIn #connect
Ws0 f12 mainOut f17 tail #connect
Ws0 f17 head f16 mainIn #connect
Ws0 f16 mainOut f13 tail #connect
Ws0 f13 head f1 mainIn #connect
Ws0 f19 mainOut f22 tail #connect
Ws0 f22 head f24 mainIn #connect
Ws0 f3 mainOut f4 tail #connect
Ws0 f4 head f30 mainIn #connect
Ws0 f24 mainOut f18 tail #connect
Ws0 f18 head f5 mainIn #connect
Ws0 f20 mainOut f23 tail #connect
Ws0 f23 head f21 mainIn #connect
