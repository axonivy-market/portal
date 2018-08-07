[Ivy]
[>Created: Wed Aug 23 09:35:17 ICT 2017]
1580345221FA4CE0 3.20 #module
>Proto >Proto Collection #zClass
Gs0 GAWFSPortalHomeProcess Big #zClass
Gs0 RD #cInfo
Gs0 #process
Gs0 @TextInP .ui2RdDataAction .ui2RdDataAction #zField
Gs0 @TextInP .rdData2UIAction .rdData2UIAction #zField
Gs0 @TextInP .resExport .resExport #zField
Gs0 @TextInP .type .type #zField
Gs0 @TextInP .processKind .processKind #zField
Gs0 @AnnotationInP-0n ai ai #zField
Gs0 @MessageFlowInP-0n messageIn messageIn #zField
Gs0 @MessageFlowOutP-0n messageOut messageOut #zField
Gs0 @TextInP .xml .xml #zField
Gs0 @TextInP .responsibility .responsibility #zField
Gs0 @RichDialogInitStart f0 '' #zField
Gs0 @RichDialogProcessEnd f1 '' #zField
Gs0 @RichDialogProcessStart f3 '' #zField
Gs0 @RichDialogEnd f4 '' #zField
Gs0 @PushWFArc f5 '' #zField
Gs0 @GridStep f6 '' #zField
Gs0 @PushWFArc f7 '' #zField
Gs0 @GridStep f2 '' #zField
Gs0 @PushWFArc f8 '' #zField
Gs0 @PushWFArc f9 '' #zField
Gs0 @RichDialogProcessEnd f46 '' #zField
Gs0 @GridStep f48 '' #zField
Gs0 @RichDialogMethodStart f35 '' #zField
Gs0 @PushWFArc f47 '' #zField
Gs0 @PushWFArc f49 '' #zField
Gs0 @RichDialogMethodStart f10 '' #zField
Gs0 @RichDialogProcessEnd f11 '' #zField
Gs0 @PushWFArc f12 '' #zField
Gs0 @RichDialogProcessStart f13 '' #zField
Gs0 @RichDialogProcessEnd f14 '' #zField
Gs0 @GridStep f16 '' #zField
Gs0 @PushWFArc f17 '' #zField
Gs0 @PushWFArc f15 '' #zField
>Proto Gs0 Gs0 GAWFSPortalHomeProcess #zField
Gs0 f0 guid 158034522559ACD1 #txt
Gs0 f0 type ch.ivy.gawfs.portal.GAWFSPortalHome.GAWFSPortalHomeData #txt
Gs0 f0 method start() #txt
Gs0 f0 disableUIEvents true #txt
Gs0 f0 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<> param = methodEvent.getInputArguments();
' #txt
Gs0 f0 outParameterDecl '<> result;
' #txt
Gs0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start()</name>
    </language>
</elementInfo>
' #txt
Gs0 f0 76 51 26 26 -16 15 #rect
Gs0 f0 @|RichDialogInitStartIcon #fIcon
Gs0 f1 type ch.ivy.gawfs.portal.GAWFSPortalHome.GAWFSPortalHomeData #txt
Gs0 f1 467 51 26 26 0 12 #rect
Gs0 f1 @|RichDialogProcessEndIcon #fIcon
Gs0 f3 guid 1580345226E6BE12 #txt
Gs0 f3 type ch.ivy.gawfs.portal.GAWFSPortalHome.GAWFSPortalHomeData #txt
Gs0 f3 actionDecl 'ch.ivy.gawfs.portal.GAWFSPortalHome.GAWFSPortalHomeData out;
' #txt
Gs0 f3 actionTable 'out=in;
' #txt
Gs0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>close</name>
    </language>
</elementInfo>
' #txt
Gs0 f3 76 147 26 26 -15 12 #rect
Gs0 f3 @|RichDialogProcessStartIcon #fIcon
Gs0 f4 type ch.ivy.gawfs.portal.GAWFSPortalHome.GAWFSPortalHomeData #txt
Gs0 f4 guid 158034522701D8F0 #txt
Gs0 f4 467 147 26 26 0 12 #rect
Gs0 f4 @|RichDialogEndIcon #fIcon
Gs0 f5 expr out #txt
Gs0 f5 102 160 467 160 #arcP
Gs0 f6 actionDecl 'ch.ivy.gawfs.portal.GAWFSPortalHome.GAWFSPortalHomeData out;
' #txt
Gs0 f6 actionTable 'out=in;
' #txt
Gs0 f6 actionCode 'import ch.ivy.addon.portalkit.service.ExpressServiceRegistry;
import ch.ivy.addon.portalkit.bo.ExpressProcess;
import ch.ivy.addon.portalkit.util.UrlHelper;
import java.util.Collections;
import ch.ivyteam.ivy.security.IRole;
import ch.ivyteam.ivy.security.IUser;
import gawfs.AvailWorkflow;

//get create workflow start link
in.createWorkflowStartLink = UrlHelper.findCreateExpressWorkflowStartLink();

// all workflows to display a selection
List<ExpressProcess> workflows = ExpressServiceRegistry.getProcessService().findAllOrderByName();

for(ExpressProcess wf: workflows){
	AvailWorkflow wfx = new AvailWorkflow();
	wfx.id = wf.id;
	wfx.processDescription = wf.processDescription;
	wfx.processName = wf.processName;
	wfx.processType = wf.processType;
	wfx.processOwner = wf.processOwner;

	ivy.log.debug(wfx.processOwner);
	IUser owner = ivy.request.getApplication().getSecurityContext().findUser(wfx.processOwner.substring(1));
	ivy.log.debug(owner);
	try{
		wfx.ownerDetails = "Fachlicher Administrator: " + owner.getDisplayName() + "( " + owner.getEMailAddress() + " )";
	}catch{
		wfx.ownerDetails = "Fachlicher Administrator: " + owner.getDisplayName() + "( not available )";
	}
	
	wfx.processPermission = wf.processPermission;
	
	IRole permittedRole;
	IUser permittedUser;
	if(wfx.processPermission.startsWith("#")){
		permittedUser = ivy.request.getApplication().getSecurityContext().findUser(wfx.processPermission.substring(1));
	}else{
		permittedRole = ivy.request.getApplication().getSecurityContext().findRole(wfx.processPermission);
	}
	
	//for Execution and displaying the workflow in general
	//check if user 1. has a role that is allowed to execute the process, 2.is AXONIVY_PORTAL_ADMIN or 3.is creator of the process
	if((#permittedUser is initialized &&  ivy.session.getSessionUser() != null &&  ivy.session.getSessionUser().getId() == permittedUser.getId()) 
		|| ivy.session.hasRole(permittedRole, false) || ivy.session.hasRole(ivy.request.getApplication().getSecurityContext().findRole("AXONIVY_PORTAL_ADMIN"), false) 
		|| ivy.session.canActAsUser(owner)){
		wfx.permitted = true;
	}else{
		wfx.permitted = false;
	}
	
	//is the user allowed to edit or delete the workflow
	//check if he is owner(initial creator of the workflow) or AXONIVY_PORTAL_ADMIN
	if(ivy.session.hasRole(ivy.request.getApplication().getSecurityContext().findRole("AXONIVY_PORTAL_ADMIN"), false) || ivy.session.canActAsUser(owner)){
		wfx.owner = true;
	}else{
		wfx.owner = false;
	}

	wfx.processStartLink = ivy.html.startref("Start Processes/GenericPredefinedWorkflowStart/GenericPredefinedProcessStart.ivp") + "?workflowID=" + wfx.id;
	wfx.processEditLink = ivy.html.startref("Start Processes/GenericPredefinedWorkflowStart/GenericEditProcessStart.ivp") + "?workflowID=" + wfx.id;
	
	//only show the workflow if the user is allowed to execute it!
	
	if(wfx.permitted){
		in.workflows.add(wfx);
	}
	
	ivy.log.debug(">" + wfx.id + "-" + wfx.processName + "-OWNER:" + wfx.processOwner + "-" + wfx.owner + "-PERMISSION:" + wfx.processPermission + "-" + wfx.permitted);
}













' #txt
Gs0 f6 type ch.ivy.gawfs.portal.GAWFSPortalHome.GAWFSPortalHomeData #txt
Gs0 f6 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>get avail. Workflows</name>
        <nameStyle>20,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Gs0 f6 176 42 112 44 -53 -8 #rect
Gs0 f6 @|StepIcon #fIcon
Gs0 f7 expr out #txt
Gs0 f7 102 64 176 64 #arcP
Gs0 f2 actionDecl 'ch.ivy.gawfs.portal.GAWFSPortalHome.GAWFSPortalHomeData out;
' #txt
Gs0 f2 actionTable 'out=in;
' #txt
Gs0 f2 actionCode 'import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.BarChartModel;



Integer finishedProcesses = ch.ivy.statUtils.CaseStatUtils.getFinishedProcessesCount(new Date("01.01.1900"));
Integer runningProcesses = ch.ivy.statUtils.CaseStatUtils.getRunningProcessesCount(new Date("01.01.1900"));
ivy.log.debug("STATT:" + finishedProcesses + " / " + runningProcesses );


in.barChartProcesses = new BarChartModel();
         
ChartSeries runningProcessesSeries = new ChartSeries();
runningProcessesSeries.setLabel(ivy.cms.co("/Dialogs/GAWFSPortalHome/RunningProcesses"));
runningProcessesSeries.set(ivy.cms.co("/Dialogs/GAWFSPortalHome/SinceTheIntroduction"), runningProcesses);

ChartSeries finishedProcessesSeries = new ChartSeries();
finishedProcessesSeries.setLabel(ivy.cms.co("/Dialogs/GAWFSPortalHome/CompletedProcesses"));
finishedProcessesSeries.set(ivy.cms.co("/Dialogs/GAWFSPortalHome/SinceTheIntroduction"), finishedProcesses);
in.barChartProcesses.addSeries(runningProcessesSeries);
in.barChartProcesses.addSeries(finishedProcessesSeries);
in.barChartProcesses.setLegendPosition("ne");
in.barChartProcesses.setAnimate(true);
in.barChartProcesses.setBarPadding(50);
in.barChartProcesses.setBarMargin(50);
in.barChartProcesses.setSeriesColors("F21C0A, 5D5D5D");
in.barChartProcesses.setExtender("barChartExtender");' #txt
Gs0 f2 type ch.ivy.gawfs.portal.GAWFSPortalHome.GAWFSPortalHomeData #txt
Gs0 f2 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>get statistics</name>
        <nameStyle>14,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Gs0 f2 328 42 112 44 -34 -8 #rect
Gs0 f2 @|StepIcon #fIcon
Gs0 f8 expr out #txt
Gs0 f8 288 64 328 64 #arcP
Gs0 f9 expr out #txt
Gs0 f9 440 64 467 64 #arcP
Gs0 f46 type ch.ivy.gawfs.portal.GAWFSPortalHome.GAWFSPortalHomeData #txt
Gs0 f46 470 245 22 22 14 0 #rect
Gs0 f46 @|RichDialogProcessEndIcon #fIcon
Gs0 f48 actionDecl 'ch.ivy.gawfs.portal.GAWFSPortalHome.GAWFSPortalHomeData out;
' #txt
Gs0 f48 actionTable 'out=in;
' #txt
Gs0 f48 actionCode 'import javax.faces.context.FacesContext;

FacesContext.getCurrentInstance().getExternalContext().redirect(in.createWorkflowStartLink);' #txt
Gs0 f48 type ch.ivy.gawfs.portal.GAWFSPortalHome.GAWFSPortalHomeData #txt
Gs0 f48 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Redirect</name>
        <nameStyle>8,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Gs0 f48 279 244 36 24 -18 22 #rect
Gs0 f48 @|StepIcon #fIcon
Gs0 f35 guid 15C5C572E0456266 #txt
Gs0 f35 type ch.ivy.gawfs.portal.GAWFSPortalHome.GAWFSPortalHomeData #txt
Gs0 f35 method startProcess(String) #txt
Gs0 f35 disableUIEvents false #txt
Gs0 f35 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<java.lang.String link> param = methodEvent.getInputArguments();
' #txt
Gs0 f35 inParameterMapAction 'out.createWorkflowStartLink=param.link;
' #txt
Gs0 f35 outParameterDecl '<> result;
' #txt
Gs0 f35 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>startProcess(String)</name>
        <nameStyle>20,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Gs0 f35 78 245 22 22 14 0 #rect
Gs0 f35 @|RichDialogMethodStartIcon #fIcon
Gs0 f47 expr out #txt
Gs0 f47 315 256 470 256 #arcP
Gs0 f49 expr out #txt
Gs0 f49 100 256 279 256 #arcP
Gs0 f10 guid 15E095A7CD439EE0 #txt
Gs0 f10 type ch.ivy.gawfs.portal.GAWFSPortalHome.GAWFSPortalHomeData #txt
Gs0 f10 method select(gawfs.AvailWorkflow) #txt
Gs0 f10 disableUIEvents false #txt
Gs0 f10 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<gawfs.AvailWorkflow workflow> param = methodEvent.getInputArguments();
' #txt
Gs0 f10 inParameterMapAction 'out.selectedWorkflow=param.workflow;
' #txt
Gs0 f10 outParameterDecl '<> result;
' #txt
Gs0 f10 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>select(AvailWorkflow)</name>
        <nameStyle>21,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Gs0 f10 76 339 26 26 -58 15 #rect
Gs0 f10 @|RichDialogMethodStartIcon #fIcon
Gs0 f11 type ch.ivy.gawfs.portal.GAWFSPortalHome.GAWFSPortalHomeData #txt
Gs0 f11 211 339 26 26 0 12 #rect
Gs0 f11 @|RichDialogProcessEndIcon #fIcon
Gs0 f12 102 352 211 352 #arcP
Gs0 f13 guid 15E096C4A50E61A1 #txt
Gs0 f13 type ch.ivy.gawfs.portal.GAWFSPortalHome.GAWFSPortalHomeData #txt
Gs0 f13 actionDecl 'ch.ivy.gawfs.portal.GAWFSPortalHome.GAWFSPortalHomeData out;
' #txt
Gs0 f13 actionTable 'out=in;
' #txt
Gs0 f13 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>deleteWorkflow</name>
    </language>
</elementInfo>
' #txt
Gs0 f13 76 435 26 26 -42 12 #rect
Gs0 f13 @|RichDialogProcessStartIcon #fIcon
Gs0 f14 type ch.ivy.gawfs.portal.GAWFSPortalHome.GAWFSPortalHomeData #txt
Gs0 f14 339 435 26 26 0 12 #rect
Gs0 f14 @|RichDialogProcessEndIcon #fIcon
Gs0 f16 actionDecl 'ch.ivy.gawfs.portal.GAWFSPortalHome.GAWFSPortalHomeData out;
' #txt
Gs0 f16 actionTable 'out=in;
' #txt
Gs0 f16 actionCode 'import ch.ivy.addon.portalkit.service.ExpressServiceRegistry;

in.workflows.remove(in.selectedWorkflow);

ExpressServiceRegistry.getProcessService().delete(in.selectedWorkflow.id);
ExpressServiceRegistry.getTaskDefinitionService().deleteByProcessId(in.selectedWorkflow.id);
ExpressServiceRegistry.getFormElementService().deleteByProcessId(in.selectedWorkflow.id);

ivy.log.debug("WORKFLOW has Id {0} is DELETED by  TaskId {1}, CaseId {2}", in.selectedWorkflow.id, ivy.task.getId(),  ivy.case.getId());
ivy.log.debug(ivy.case.getCustomVarCharField1());
' #txt
Gs0 f16 type ch.ivy.gawfs.portal.GAWFSPortalHome.GAWFSPortalHomeData #txt
Gs0 f16 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>delete</name>
        <nameStyle>6
</nameStyle>
    </language>
</elementInfo>
' #txt
Gs0 f16 168 426 112 44 -17 -8 #rect
Gs0 f16 @|StepIcon #fIcon
Gs0 f17 expr out #txt
Gs0 f17 102 448 168 448 #arcP
Gs0 f15 expr out #txt
Gs0 f15 280 448 339 448 #arcP
>Proto Gs0 .type ch.ivy.gawfs.portal.GAWFSPortalHome.GAWFSPortalHomeData #txt
>Proto Gs0 .processKind HTML_DIALOG #txt
>Proto Gs0 -8 -8 16 16 16 26 #rect
>Proto Gs0 '' #fIcon
Gs0 f3 mainOut f5 tail #connect
Gs0 f5 head f4 mainIn #connect
Gs0 f0 mainOut f7 tail #connect
Gs0 f7 head f6 mainIn #connect
Gs0 f6 mainOut f8 tail #connect
Gs0 f8 head f2 mainIn #connect
Gs0 f2 mainOut f9 tail #connect
Gs0 f9 head f1 mainIn #connect
Gs0 f35 mainOut f49 tail #connect
Gs0 f49 head f48 mainIn #connect
Gs0 f48 mainOut f47 tail #connect
Gs0 f47 head f46 mainIn #connect
Gs0 f10 mainOut f12 tail #connect
Gs0 f12 head f11 mainIn #connect
Gs0 f13 mainOut f17 tail #connect
Gs0 f17 head f16 mainIn #connect
Gs0 f16 mainOut f15 tail #connect
Gs0 f15 head f14 mainIn #connect
