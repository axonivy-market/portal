[Ivy]
14FEEC13F8B8E7D2 3.23 #module
>Proto >Proto Collection #zClass
Ps0 ProcessWidgetProcess Big #zClass
Ps0 RD #cInfo
Ps0 #process
Ps0 @TextInP .ui2RdDataAction .ui2RdDataAction #zField
Ps0 @TextInP .rdData2UIAction .rdData2UIAction #zField
Ps0 @TextInP .resExport .resExport #zField
Ps0 @TextInP .type .type #zField
Ps0 @TextInP .processKind .processKind #zField
Ps0 @AnnotationInP-0n ai ai #zField
Ps0 @MessageFlowInP-0n messageIn messageIn #zField
Ps0 @MessageFlowOutP-0n messageOut messageOut #zField
Ps0 @TextInP .xml .xml #zField
Ps0 @TextInP .responsibility .responsibility #zField
Ps0 @RichDialogInitStart f1 '' #zField
Ps0 @RichDialogProcessEnd f3 '' #zField
Ps0 @PushWFArc f4 '' #zField
Ps0 @RichDialogMethodStart f0 '' #zField
Ps0 @GridStep f2 '' #zField
Ps0 @PushWFArc f5 '' #zField
Ps0 @CallSub f6 '' #zField
Ps0 @PushWFArc f7 '' #zField
Ps0 @RichDialogProcessEnd f8 '' #zField
Ps0 @GridStep f10 '' #zField
Ps0 @RichDialogMethodStart f12 '' #zField
Ps0 @RichDialogProcessEnd f13 '' #zField
Ps0 @PushWFArc f14 '' #zField
Ps0 @PushWFArc f11 '' #zField
Ps0 @PushWFArc f9 '' #zField
>Proto Ps0 Ps0 ProcessWidgetProcess #zField
Ps0 f1 guid 167CEF3C0BEA1F15 #txt
Ps0 f1 type ch.ivy.addon.portalkit.component.ProcessWidget.ProcessWidgetData #txt
Ps0 f1 method start() #txt
Ps0 f1 disableUIEvents true #txt
Ps0 f1 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<> param = methodEvent.getInputArguments();
' #txt
Ps0 f1 outParameterDecl '<> result;
' #txt
Ps0 f1 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start()</name>
    </language>
</elementInfo>
' #txt
Ps0 f1 83 83 26 26 -12 16 #rect
Ps0 f1 @|RichDialogInitStartIcon #fIcon
Ps0 f3 type ch.ivy.addon.portalkit.component.ProcessWidget.ProcessWidgetData #txt
Ps0 f3 307 83 26 26 0 12 #rect
Ps0 f3 @|RichDialogProcessEndIcon #fIcon
Ps0 f4 expr out #txt
Ps0 f4 109 96 307 96 #arcP
Ps0 f0 guid 167CF9865BEC5C1B #txt
Ps0 f0 type ch.ivy.addon.portalkit.component.ProcessWidget.ProcessWidgetData #txt
Ps0 f0 method collectProcesses() #txt
Ps0 f0 disableUIEvents false #txt
Ps0 f0 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<> param = methodEvent.getInputArguments();
' #txt
Ps0 f0 outParameterDecl '<java.util.List<ch.ivyteam.ivy.workflow.start.IWebStartable> processes> result;
' #txt
Ps0 f0 outParameterMapAction 'result.processes=in.processes;
' #txt
Ps0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>collectProcesses()</name>
    </language>
</elementInfo>
' #txt
Ps0 f0 83 179 26 26 -42 19 #rect
Ps0 f0 @|RichDialogMethodStartIcon #fIcon
Ps0 f2 actionDecl 'ch.ivy.addon.portalkit.component.ProcessWidget.ProcessWidgetData out;
' #txt
Ps0 f2 actionTable 'out=in;
' #txt
Ps0 f2 actionCode 'import org.apache.commons.lang3.StringUtils;
import ch.ivy.addon.portalkit.util.SecurityServiceUtils;

out.processSearchCriteria.username = ivy.session.getSessionUserName();

String applicationName = SecurityServiceUtils.getApplicationNameFromSession();
if (StringUtils.isNotBlank(applicationName)) {
	out.processSearchCriteria.apps = [applicationName];
}
' #txt
Ps0 f2 type ch.ivy.addon.portalkit.component.ProcessWidget.ProcessWidgetData #txt
Ps0 f2 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Prepare criteria</name>
    </language>
</elementInfo>
' #txt
Ps0 f2 168 170 112 44 -42 -8 #rect
Ps0 f2 @|StepIcon #fIcon
Ps0 f5 expr out #txt
Ps0 f5 109 192 168 192 #arcP
Ps0 f6 type ch.ivy.addon.portalkit.component.ProcessWidget.ProcessWidgetData #txt
Ps0 f6 processCall 'Ivy Data Processes/ProcessService:findProcesses(ch.ivy.addon.portalkit.ivydata.searchcriteria.ProcessSearchCriteria)' #txt
Ps0 f6 doCall true #txt
Ps0 f6 requestActionDecl '<ch.ivy.addon.portalkit.ivydata.searchcriteria.ProcessSearchCriteria processSearchCriteria> param;
' #txt
Ps0 f6 requestMappingAction 'param.processSearchCriteria=in.processSearchCriteria;
' #txt
Ps0 f6 responseActionDecl 'ch.ivy.addon.portalkit.component.ProcessWidget.ProcessWidgetData out;
' #txt
Ps0 f6 responseMappingAction 'out=in;
out.errors=result.errors;
out.processes=result.processes;
' #txt
Ps0 f6 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>ProcessService</name>
    </language>
</elementInfo>
' #txt
Ps0 f6 320 170 112 44 -43 -8 #rect
Ps0 f6 @|CallSubIcon #fIcon
Ps0 f7 expr out #txt
Ps0 f7 280 192 320 192 #arcP
Ps0 f8 type ch.ivy.addon.portalkit.component.ProcessWidget.ProcessWidgetData #txt
Ps0 f8 499 179 26 26 0 12 #rect
Ps0 f8 @|RichDialogProcessEndIcon #fIcon
Ps0 f10 actionDecl 'ch.ivy.addon.portalkit.component.ProcessWidget.ProcessWidgetData out;
' #txt
Ps0 f10 actionTable 'out=in;
' #txt
Ps0 f10 actionCode 'import ch.ivy.addon.portalkit.util.PermissionUtils;
import org.apache.commons.lang3.StringUtils;
import ch.ivy.addon.portalkit.service.ExpressServiceRegistry;
import ch.ivy.addon.portalkit.bo.ExpressProcess;
import ch.ivy.addon.portalkit.service.ProcessStartCollector;

ProcessStartCollector processStartCollector = new ProcessStartCollector(ivy.request.getApplication());
String expressStartLink = processStartCollector.findExpressWorkflowStartLink();
if (StringUtils.isNotBlank(expressStartLink)) {
	List<ExpressProcess> workflows = ExpressServiceRegistry.getProcessService().findReadyToExecuteProcessOrderByName();
	for(ExpressProcess wf : workflows) {
		if (PermissionUtils.canStartExpressWorkflow(wf)) {
		  in.expressProcesses.add(wf);
		}
	}	
}

' #txt
Ps0 f10 type ch.ivy.addon.portalkit.component.ProcessWidget.ProcessWidgetData #txt
Ps0 f10 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Collect express&#xD;
workflow</name>
    </language>
</elementInfo>
' #txt
Ps0 f10 184 266 128 44 -41 -16 #rect
Ps0 f10 @|StepIcon #fIcon
Ps0 f12 guid 167DF8EF85131C60 #txt
Ps0 f12 type ch.ivy.addon.portalkit.component.ProcessWidget.ProcessWidgetData #txt
Ps0 f12 method collectExpressProcesses() #txt
Ps0 f12 disableUIEvents false #txt
Ps0 f12 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<> param = methodEvent.getInputArguments();
' #txt
Ps0 f12 outParameterDecl '<java.util.List<ch.ivy.addon.portalkit.bo.ExpressProcess> expressProcesses> result;
' #txt
Ps0 f12 outParameterMapAction 'result.expressProcesses=in.expressProcesses;
' #txt
Ps0 f12 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>collectExpressProcesses()</name>
    </language>
</elementInfo>
' #txt
Ps0 f12 83 275 26 26 -74 15 #rect
Ps0 f12 @|RichDialogMethodStartIcon #fIcon
Ps0 f13 type ch.ivy.addon.portalkit.component.ProcessWidget.ProcessWidgetData #txt
Ps0 f13 403 275 26 26 0 12 #rect
Ps0 f13 @|RichDialogProcessEndIcon #fIcon
Ps0 f14 expr out #txt
Ps0 f14 432 192 499 192 #arcP
Ps0 f11 expr out #txt
Ps0 f11 312 288 403 288 #arcP
Ps0 f9 expr out #txt
Ps0 f9 109 288 184 288 #arcP
>Proto Ps0 .type ch.ivy.addon.portalkit.component.ProcessWidget.ProcessWidgetData #txt
>Proto Ps0 .processKind HTML_DIALOG #txt
>Proto Ps0 -8 -8 16 16 16 26 #rect
>Proto Ps0 '' #fIcon
Ps0 f1 mainOut f4 tail #connect
Ps0 f4 head f3 mainIn #connect
Ps0 f0 mainOut f5 tail #connect
Ps0 f5 head f2 mainIn #connect
Ps0 f2 mainOut f7 tail #connect
Ps0 f7 head f6 mainIn #connect
Ps0 f6 mainOut f14 tail #connect
Ps0 f14 head f8 mainIn #connect
Ps0 f10 mainOut f11 tail #connect
Ps0 f11 head f13 mainIn #connect
Ps0 f12 mainOut f9 tail #connect
Ps0 f9 head f10 mainIn #connect
