[Ivy]
[>Created: Wed Nov 23 19:06:21 PST 2016]
158B287676DD65D2 3.18 #module
>Proto >Proto Collection #zClass
Ws0 CaseDetailsProcess Big #zClass
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
Ws0 @RichDialogProcessEnd f48 '' #zField
Ws0 @GridStep f47 '' #zField
Ws0 @PushWFArc f49 '' #zField
Ws0 @PushWFArc f46 '' #zField
>Proto Ws0 Ws0 CaseDetailsProcess #zField
Ws0 f0 guid 1578919576D899E8 #txt
Ws0 f0 type de.eon.gawfs.portal.CaseDetails.CaseDetailsData #txt
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
Ws0 f1 type de.eon.gawfs.portal.CaseDetails.CaseDetailsData #txt
Ws0 f1 211 51 26 26 0 12 #rect
Ws0 f1 @|RichDialogProcessEndIcon #fIcon
Ws0 f2 expr out #txt
Ws0 f2 109 64 211 64 #arcP
Ws0 f3 guid 1578919577872B2E #txt
Ws0 f3 type de.eon.gawfs.portal.CaseDetails.CaseDetailsData #txt
Ws0 f3 actionDecl 'de.eon.gawfs.portal.CaseDetails.CaseDetailsData out;
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
Ws0 f4 type de.eon.gawfs.portal.CaseDetails.CaseDetailsData #txt
Ws0 f4 guid 157891957797EA82 #txt
Ws0 f4 211 147 26 26 0 12 #rect
Ws0 f4 @|RichDialogEndIcon #fIcon
Ws0 f5 expr out #txt
Ws0 f5 109 160 211 160 #arcP
Ws0 f51 guid 158944C4926E3D04 #txt
Ws0 f51 type de.eon.gawfs.portal.CaseDetails.CaseDetailsData #txt
Ws0 f51 actionDecl 'de.eon.gawfs.portal.CaseDetails.CaseDetailsData out;
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
Ws0 f48 type de.eon.gawfs.portal.CaseDetails.CaseDetailsData #txt
Ws0 f48 564 228 24 24 13 0 #rect
Ws0 f48 @|RichDialogProcessEndIcon #fIcon
Ws0 f47 actionDecl 'de.eon.gawfs.portal.CaseDetails.CaseDetailsData out;
' #txt
Ws0 f47 actionTable 'out=in;
' #txt
Ws0 f47 actionCode 'import ch.ivyteam.ivy.workflow.CaseState;
import javax.servlet.http.HttpServletRequest;
import javax.faces.context.FacesContext;
import ch.ivyteam.ivy.workflow.IProcessStart;
import ch.ivyteam.ivy.richdialog.exec.ProcessStartConfiguration;
 
ivy.task.reset();

String link;

if (ivy.session.isSessionUserUnknown() && !ivy.wf.getApplication().getName().equalsIgnoreCase("designer")) {
		link = ivy.var.de_eon_gawfs_core_endpage;
		ivy.log.debug("DESIGNER!!!");
}
else {
	HttpServletRequest  req = FacesContext.getCurrentInstance().getExternalContext().getRequest() as HttpServletRequest;
	 
	                IProcessStart processStart;
	                for (IProcessStart start : ivy.session.getStartableProcessStarts())
	                {
	                               ivy.log.debug("Process-start Id:"+start.getName()+"/"+start.getProcessElementId());
	                               if (start.getProcessElementId().equals("1576E76B009E23DD-f0")) { //Portal
	                                               processStart = start;
	                                               break;
	                               }
	                }
	
	String context = ivy.html.applicationHomeRef().substring(0,ivy.html.applicationHomeRef().indexOf("/",1));
	//ivy.log.info("HomeRef:"+context);
	                
	link = "http://"+req.getServerName() + ":"+ req.getServerPort() + context + "/pro/";
	                if(processStart != null) {
	                               if (!ivy.case.getState().equals(CaseState.ZOMBIE) && !ivy.case.getState().equals(CaseState.CREATED)) {
	                                               link += processStart.getFullRequestPath()+"?taskIdentifier="+ivy.task.getId();
	                               }
	                               else {
	                                               link += processStart.getFullRequestPath();
	                               }
	                }

}

//redirect to portal
//ivy.log.debug("Link to Portal found:"+link);
FacesContext.getCurrentInstance().getExternalContext().redirect(link);
' #txt
Ws0 f47 type de.eon.gawfs.portal.CaseDetails.CaseDetailsData #txt
Ws0 f47 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>reset task</name>
        <nameStyle>10,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ws0 f47 260 220 104 40 -28 -6 #rect
Ws0 f47 @|StepIcon #fIcon
Ws0 f49 expr out #txt
Ws0 f49 364 240 564 240 #arcP
Ws0 f46 expr out #txt
Ws0 f46 109 240 260 240 #arcP
>Proto Ws0 .type de.eon.gawfs.portal.CaseDetails.CaseDetailsData #txt
>Proto Ws0 .processKind HTML_DIALOG #txt
>Proto Ws0 -8 -8 16 16 16 26 #rect
>Proto Ws0 '' #fIcon
Ws0 f0 mainOut f2 tail #connect
Ws0 f2 head f1 mainIn #connect
Ws0 f3 mainOut f5 tail #connect
Ws0 f5 head f4 mainIn #connect
Ws0 f47 mainOut f49 tail #connect
Ws0 f49 head f48 mainIn #connect
Ws0 f51 mainOut f46 tail #connect
Ws0 f46 head f47 mainIn #connect
