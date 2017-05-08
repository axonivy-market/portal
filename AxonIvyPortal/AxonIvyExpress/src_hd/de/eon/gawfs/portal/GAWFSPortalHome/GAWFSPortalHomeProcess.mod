[Ivy]
[>Created: Mon Jan 16 11:52:44 CET 2017]
1580345221FA4CE0 3.18 #module
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
>Proto Gs0 Gs0 GAWFSPortalHomeProcess #zField
Gs0 f0 guid 158034522559ACD1 #txt
Gs0 f0 type de.eon.gawfs.portal.GAWFSPortalHome.GAWFSPortalHomeData #txt
Gs0 f0 method start(gawfs.DevLoadWorkflowsData) #txt
Gs0 f0 disableUIEvents true #txt
Gs0 f0 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<gawfs.DevLoadWorkflowsData devLoadWorkflowsData> param = methodEvent.getInputArguments();
' #txt
Gs0 f0 inParameterMapAction 'out.devLoadWorkflowsData=param.devLoadWorkflowsData;
' #txt
Gs0 f0 outParameterDecl '<gawfs.DevLoadWorkflowsData devLoadWorkflowsData> result;
' #txt
Gs0 f0 outParameterMapAction 'result.devLoadWorkflowsData=in.devLoadWorkflowsData;
' #txt
Gs0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start(DevLoadWorkflowsData)</name>
        <nameStyle>27,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Gs0 f0 83 51 26 26 -80 23 #rect
Gs0 f0 @|RichDialogInitStartIcon #fIcon
Gs0 f1 type de.eon.gawfs.portal.GAWFSPortalHome.GAWFSPortalHomeData #txt
Gs0 f1 467 51 26 26 0 12 #rect
Gs0 f1 @|RichDialogProcessEndIcon #fIcon
Gs0 f3 guid 1580345226E6BE12 #txt
Gs0 f3 type de.eon.gawfs.portal.GAWFSPortalHome.GAWFSPortalHomeData #txt
Gs0 f3 actionDecl 'de.eon.gawfs.portal.GAWFSPortalHome.GAWFSPortalHomeData out;
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
Gs0 f3 83 147 26 26 -15 12 #rect
Gs0 f3 @|RichDialogProcessStartIcon #fIcon
Gs0 f4 type de.eon.gawfs.portal.GAWFSPortalHome.GAWFSPortalHomeData #txt
Gs0 f4 guid 158034522701D8F0 #txt
Gs0 f4 467 147 26 26 0 12 #rect
Gs0 f4 @|RichDialogEndIcon #fIcon
Gs0 f5 expr out #txt
Gs0 f5 109 160 467 160 #arcP
Gs0 f6 actionDecl 'de.eon.gawfs.portal.GAWFSPortalHome.GAWFSPortalHomeData out;
' #txt
Gs0 f6 actionTable 'out=in;
' #txt
Gs0 f6 actionCode 'import java.util.Collections;
import ch.ivyteam.ivy.security.IRole;
import ch.ivyteam.ivy.security.IUser;
import gawfs.AvailWorkflow;
import gawfs.Workflow;



// all workflows to display a selection
List<Workflow> workflows = ivy.persistence.eonGAWFS
.createQuery("select p from Workflow p order by p.processName")
.getResultList();

for(Workflow wf: workflows){
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
	IRole permittedRole = ivy.request.getApplication().getSecurityContext().findRole(wfx.processPermission);
	
	//for Execution and displaying the workflow in general
	//check if user 1. has a role that is allowed to execute the process, 2.is GAWFS_ADMIN or 3.is creator of the process
	if(ivy.session.hasRole(permittedRole, false) || ivy.session.hasRole(ivy.request.getApplication().getSecurityContext().findRole("GAWFS_ADMIN"), false) || ivy.session.canActAsUser(owner)){
		wfx.permitted = true;
	}else{
		wfx.permitted = false;
	}
	
	//is the user allowed to edit or delete the workflow
	//check if he is owner(initial creator of the workflow) or GAWFS_ADMIN
	if(ivy.session.hasRole(ivy.request.getApplication().getSecurityContext().findRole("GAWFS_ADMIN"), false) || ivy.session.canActAsUser(owner)){
		wfx.owner = true;
	}else{
		wfx.owner = false;
	}
	
	//ivy.log.debug("1:" + ivy.session.hasRole(permittedRole, false));
	//ivy.log.debug("2:" + ivy.session.hasRole(ivy.request.getApplication().getSecurityContext().findRole("GAWFS_ADMIN"), false));
	//ivy.log.debug("3:" + ivy.session.canActAsUser(owner));
	
	
	
	wfx.processStartLink = ivy.html.startref("Start Processes/GenericPredefinedWorkflowStart/GenericPredefinedProcessStart.ivp") + "?workflowID=" + wfx.id;
	wfx.processDeleteLink = ivy.html.startref("Start Processes/GenericPredefinedWorkflowStart/GenericDeleteProcessStart.ivp") + "?workflowID=" + wfx.id;
	wfx.processEditLink = ivy.html.startref("Start Processes/GenericPredefinedWorkflowStart/GenericEditProcessStart.ivp") + "?workflowID=" + wfx.id;
	
	//only show the workflow if the user is allowed to execute it!
	
	if(wfx.permitted){
		in.workflows.add(wfx);
	}
	
	ivy.log.debug(">" + wfx.id + "-" + wfx.processName + "-OWNER:" + wfx.processOwner + "-" + wfx.owner + "-PERMISSION:" + wfx.processPermission + "-" + wfx.permitted);
}


//int workflowID = 17;
//Workflow workflow = ivy.persistence.eonGAWFS
//.find(Workflow.class, workflowID) as Workflow;

//in.workflowDescription = workflow.processDescription;
//in.workflowID = workflow.id;
//in.workflowName = workflow.processName;
//in.workflowType = workflow.processType;











' #txt
Gs0 f6 type de.eon.gawfs.portal.GAWFSPortalHome.GAWFSPortalHomeData #txt
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
Gs0 f7 109 64 176 64 #arcP
Gs0 f2 actionDecl 'de.eon.gawfs.portal.GAWFSPortalHome.GAWFSPortalHomeData out;
' #txt
Gs0 f2 actionTable 'out=in;
' #txt
Gs0 f2 actionCode 'import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.BarChartModel;



Integer finishedProcesses = de.eon.statUtils.CaseStatUtils.getFinishedProcessesCount(new Date("01.01.1900"));
Integer runningProcesses = de.eon.statUtils.CaseStatUtils.getRunningProcessesCount(new Date("01.01.1900"));
ivy.log.debug("STATT:" + finishedProcesses + " / " + runningProcesses );


in.barChartProcesses = new BarChartModel();
         
ChartSeries runningProcessesSeries = new ChartSeries();
runningProcessesSeries.setLabel("Laufende Prozesse");
runningProcessesSeries.set("Seit Einführung", runningProcesses);

ChartSeries finishedProcessesSeries = new ChartSeries();
finishedProcessesSeries.setLabel("Abgeschlossene Prozesse");
finishedProcessesSeries.set("Seit Einführung", finishedProcesses);
in.barChartProcesses.addSeries(runningProcessesSeries);
in.barChartProcesses.addSeries(finishedProcessesSeries);
in.barChartProcesses.setLegendPosition("ne");
in.barChartProcesses.setAnimate(true);
in.barChartProcesses.setBarPadding(50);
in.barChartProcesses.setBarMargin(50);
in.barChartProcesses.setSeriesColors("F21C0A, 5D5D5D");
in.barChartProcesses.setExtender("barChartExtender");' #txt
Gs0 f2 type de.eon.gawfs.portal.GAWFSPortalHome.GAWFSPortalHomeData #txt
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
>Proto Gs0 .type de.eon.gawfs.portal.GAWFSPortalHome.GAWFSPortalHomeData #txt
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
