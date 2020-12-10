[Ivy]
167CFB0D927CF955 7.5.0 #module
>Proto >Proto Collection #zClass
Ps0 CompactProcessWidgetProcess Big #zClass
Ps0 RD #cInfo
Ps0 #process
Ps0 @TextInP .type .type #zField
Ps0 @TextInP .processKind .processKind #zField
Ps0 @AnnotationInP-0n ai ai #zField
Ps0 @MessageFlowInP-0n messageIn messageIn #zField
Ps0 @MessageFlowOutP-0n messageOut messageOut #zField
Ps0 @TextInP .xml .xml #zField
Ps0 @TextInP .responsibility .responsibility #zField
Ps0 @UdInit f1 '' #zField
Ps0 @UdProcessEnd f3 '' #zField
Ps0 @PushWFArc f4 '' #zField
Ps0 @UdMethod f0 '' #zField
Ps0 @GridStep f2 '' #zField
Ps0 @PushWFArc f5 '' #zField
Ps0 @CallSub f6 '' #zField
Ps0 @PushWFArc f7 '' #zField
Ps0 @UdProcessEnd f8 '' #zField
Ps0 @GridStep f10 '' #zField
Ps0 @PushWFArc f11 '' #zField
Ps0 @GridStep f12 '' #zField
Ps0 @PushWFArc f9 '' #zField
Ps0 @PushWFArc f13 '' #zField
Ps0 @UdMethod f14 '' #zField
Ps0 @UdMethod f17 '' #zField
Ps0 @UdProcessEnd f18 '' #zField
Ps0 @Alternative f178 '' #zField
Ps0 @UdProcessEnd f16 '' #zField
Ps0 @GridStep f23 '' #zField
Ps0 @PushWFArc f24 '' #zField
Ps0 @PushWFArc f15 '' #zField
Ps0 @GridStep f20 '' #zField
Ps0 @PushWFArc f28 '' #zField
Ps0 @GridStep f29 '' #zField
Ps0 @PushWFArc f30 '' #zField
Ps0 @PushWFArc f19 '' #zField
Ps0 @PushWFArc f31 '' #zField
Ps0 @PushWFArc f22 '' #zField
>Proto Ps0 Ps0 CompactProcessWidgetProcess #zField
Ps0 f1 guid 167CEF3C0BEA1F15 #txt
Ps0 f1 method start() #txt
Ps0 f1 inParameterDecl '<> param;' #txt
Ps0 f1 outParameterDecl '<> result;' #txt
Ps0 f1 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start(String)</name>
    </language>
</elementInfo>
' #txt
Ps0 f1 83 83 26 26 -32 15 #rect
Ps0 f1 @|UdInitIcon #fIcon
Ps0 f3 307 83 26 26 0 12 #rect
Ps0 f3 @|UdProcessEndIcon #fIcon
Ps0 f4 expr out #txt
Ps0 f4 109 96 307 96 #arcP
Ps0 f0 guid 167CF9865BEC5C1B #txt
Ps0 f0 method collectProcesses() #txt
Ps0 f0 inParameterDecl '<> param;' #txt
Ps0 f0 outParameterDecl '<java.util.List<ch.ivy.addon.portalkit.persistence.domain.UserProcess> userProcesses> result;' #txt
Ps0 f0 outParameterMapAction 'result.userProcesses=in.userProcesses;
' #txt
Ps0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>collectProcesses()</name>
    </language>
</elementInfo>
' #txt
Ps0 f0 83 179 26 26 -48 16 #rect
Ps0 f0 @|UdMethodIcon #fIcon
Ps0 f2 actionTable 'out=in;
' #txt
Ps0 f2 actionCode 'import ch.ivy.addon.portalkit.service.RegisteredApplicationService;

RegisteredApplicationService service = new RegisteredApplicationService();
java.util.List apps = service.findActiveIvyAppsBasedOnConfiguration(ivy.session.getSessionUserName());
out.processSearchCriteria.apps = apps;
out.processSearchCriteria.username = ivy.session.getSessionUserName();
' #txt
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
Ps0 f6 processCall 'Ivy Data Processes/ProcessService:findProcesses(ch.ivy.addon.portalkit.ivydata.searchcriteria.ProcessSearchCriteria)' #txt
Ps0 f6 requestActionDecl '<ch.ivy.addon.portalkit.ivydata.searchcriteria.ProcessSearchCriteria processSearchCriteria> param;' #txt
Ps0 f6 requestMappingAction 'param.processSearchCriteria=in.processSearchCriteria;
' #txt
Ps0 f6 responseActionDecl 'ch.ivy.addon.portalkit.component.CompactProcessWidget.CompactProcessWidgetData out;
' #txt
Ps0 f6 responseMappingAction 'out=in;
out.errors=result.errors;
out.userProcesses=ch.ivy.addon.portalkit.mapper.UserProcessMapper.toUserProcesses(result.processes);
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
Ps0 f8 811 179 26 26 0 12 #rect
Ps0 f8 @|UdProcessEndIcon #fIcon
Ps0 f10 actionTable 'out=in;
' #txt
Ps0 f10 actionCode 'import ch.ivy.addon.portalkit.mapper.UserProcessMapper;
import ch.ivy.addon.portalkit.util.ProcessStartsUtil;
import ch.ivy.addon.portalkit.util.PermissionUtils;
import org.apache.commons.lang3.StringUtils;
import ch.ivy.addon.portalkit.service.ExpressServiceRegistry;
import ch.ivy.addon.portalkit.bo.ExpressProcess;
import ch.ivy.addon.portalkit.service.ProcessStartCollector;

ProcessStartCollector processStartCollector = new ProcessStartCollector(ivy.request.getApplication());
String expressStartLink = processStartCollector.findExpressWorkflowStartLink();
if (StringUtils.isNotBlank(expressStartLink)) {
	List<ExpressProcess> workflows = ExpressServiceRegistry.getProcessService().findReadyToExecuteProcessOrderByName();
	for(ExpressProcess wf : workflows) {
		if (PermissionUtils.checkAbleToStartAndAbleToEditExpressWorkflow(wf) && !ProcessStartsUtil.isExpressProcessAdded(wf, in.userProcesses)) {
		  in.userProcesses.add(UserProcessMapper.toUserProcess(wf, expressStartLink));
		}
	}
}' #txt
Ps0 f10 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Collect express&#xD;
workflow</name>
    </language>
</elementInfo>
' #txt
Ps0 f10 472 170 128 44 -41 -16 #rect
Ps0 f10 @|StepIcon #fIcon
Ps0 f11 expr out #txt
Ps0 f11 432 192 472 192 #arcP
Ps0 f12 actionTable 'out=in;
' #txt
Ps0 f12 actionCode 'import ch.ivy.addon.portalkit.mapper.UserProcessMapper;
import ch.ivy.addon.portalkit.service.ExternalLinkService;
import ch.ivy.addon.portalkit.persistence.domain.UserProcess;
import java.util.ArrayList;
import ch.ivy.addon.portalkit.bo.ExternalLink;

in.userProcesses.addAll(UserProcessMapper.externalLinksToUserProcesses(ExternalLinkService.getInstance().findStartableLink(ivy.session.getSessionUserName())));' #txt
Ps0 f12 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Collect external links</name>
    </language>
</elementInfo>
' #txt
Ps0 f12 648 170 128 44 -56 -8 #rect
Ps0 f12 @|StepIcon #fIcon
Ps0 f9 expr out #txt
Ps0 f9 600 192 648 192 #arcP
Ps0 f13 776 192 811 192 #arcP
Ps0 f14 guid 1762308002888AA1 #txt
Ps0 f14 method generateProcessDisplayName() #txt
Ps0 f14 inParameterDecl '<> param;' #txt
Ps0 f14 outParameterDecl '<> result;' #txt
Ps0 f14 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>generateProcessDisplayName()</name>
    </language>
</elementInfo>
' #txt
Ps0 f14 83 307 26 26 -64 20 #rect
Ps0 f14 @|UdMethodIcon #fIcon
Ps0 f17 guid 176230A4F0EDD743 #txt
Ps0 f17 method processDisplayNames() #txt
Ps0 f17 inParameterDecl '<> param;' #txt
Ps0 f17 outParameterDecl '<> result;' #txt
Ps0 f17 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>processDisplayNames()</name>
    </language>
</elementInfo>
' #txt
Ps0 f17 83 467 26 26 -57 17 #rect
Ps0 f17 @|UdMethodIcon #fIcon
Ps0 f18 443 467 26 26 0 12 #rect
Ps0 f18 @|UdProcessEndIcon #fIcon
Ps0 f178 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>is provided process name?</name>
    </language>
</elementInfo>
' #txt
Ps0 f178 402 306 28 28 -68 -36 #rect
Ps0 f178 @|AlternativeIcon #fIcon
Ps0 f16 731 307 26 26 0 12 #rect
Ps0 f16 @|UdProcessEndIcon #fIcon
Ps0 f23 actionTable 'out=in;
' #txt
Ps0 f23 actionCode 'import org.apache.commons.lang3.StringUtils;
import ch.ivy.addon.portalkit.bean.CompactProcessWidgetBean;
import ch.ivy.addon.portalkit.jsf.ManagedBeans;

CompactProcessWidgetBean compactProcessWidgetBean = ManagedBeans.get("compactProcessWidgetBean") as CompactProcessWidgetBean;
in.canAddLanguages = StringUtils.isNotEmpty(compactProcessWidgetBean.getEditingProcess().getProcessName());' #txt
Ps0 f23 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Can add languages</name>
    </language>
</elementInfo>
' #txt
Ps0 f23 168 298 128 44 -54 -8 #rect
Ps0 f23 @|StepIcon #fIcon
Ps0 f24 109 320 168 320 #arcP
Ps0 f15 296 320 402 320 #arcP
Ps0 f20 actionTable 'out=in;
' #txt
Ps0 f20 actionCode 'import ch.ivy.addon.portalkit.bean.CompactProcessWidgetBean;
import ch.ivy.addon.portalkit.jsf.ManagedBeans;

CompactProcessWidgetBean compactProcessWidgetBean = ManagedBeans.get("compactProcessWidgetBean") as CompactProcessWidgetBean;
in.displayNames = compactProcessWidgetBean.generateProcessDisplayNames();

' #txt
Ps0 f20 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Get supported languages</name>
    </language>
</elementInfo>
' #txt
Ps0 f20 512 298 144 44 -69 -8 #rect
Ps0 f20 @|StepIcon #fIcon
Ps0 f28 656 320 731 320 #arcP
Ps0 f29 actionTable 'out=in;
' #txt
Ps0 f29 actionCode 'import org.apache.commons.lang3.StringUtils;
import ch.ivy.addon.portalkit.dto.DisplayName;
import ch.ivy.addon.portalkit.bean.CompactProcessWidgetBean;
import ch.ivy.addon.portalkit.jsf.ManagedBeans;

CompactProcessWidgetBean compactProcessWidgetBean = ManagedBeans.get("compactProcessWidgetBean") as CompactProcessWidgetBean;
String defaultProcessName = compactProcessWidgetBean.getEditingProcess().getProcessName();
List<DisplayName> processDisplayNames = new List();
processDisplayNames.addAll(in.displayNames);
for (DisplayName name : processDisplayNames) {
		if (StringUtils.isBlank(name.getValue())) {
				name.setValue(defaultProcessName);
		}
}
compactProcessWidgetBean.getEditingProcess().setNames(processDisplayNames);

' #txt
Ps0 f29 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Update process display name</name>
    </language>
</elementInfo>
' #txt
Ps0 f29 168 458 176 44 -82 -8 #rect
Ps0 f29 @|StepIcon #fIcon
Ps0 f30 109 480 168 480 #arcP
Ps0 f19 344 480 443 480 #arcP
Ps0 f31 expr in #txt
Ps0 f31 416 334 744 333 #arcP
Ps0 f31 1 416 384 #addKink
Ps0 f31 2 744 384 #addKink
Ps0 f31 1 0.48970462821318367 0 0 #arcLabel
Ps0 f22 expr in #txt
Ps0 f22 outCond 'in.#canAddLanguages' #txt
Ps0 f22 430 320 512 320 #arcP
>Proto Ps0 .type ch.ivy.addon.portalkit.component.CompactProcessWidget.CompactProcessWidgetData #txt
>Proto Ps0 .processKind HTML_DIALOG #txt
>Proto Ps0 -8 -8 16 16 16 26 #rect
>Proto Ps0 '' #fIcon
Ps0 f1 mainOut f4 tail #connect
Ps0 f4 head f3 mainIn #connect
Ps0 f0 mainOut f5 tail #connect
Ps0 f5 head f2 mainIn #connect
Ps0 f2 mainOut f7 tail #connect
Ps0 f7 head f6 mainIn #connect
Ps0 f6 mainOut f11 tail #connect
Ps0 f11 head f10 mainIn #connect
Ps0 f10 mainOut f9 tail #connect
Ps0 f9 head f12 mainIn #connect
Ps0 f12 mainOut f13 tail #connect
Ps0 f13 head f8 mainIn #connect
Ps0 f14 mainOut f24 tail #connect
Ps0 f24 head f23 mainIn #connect
Ps0 f23 mainOut f15 tail #connect
Ps0 f15 head f178 in #connect
Ps0 f20 mainOut f28 tail #connect
Ps0 f28 head f16 mainIn #connect
Ps0 f17 mainOut f30 tail #connect
Ps0 f30 head f29 mainIn #connect
Ps0 f29 mainOut f19 tail #connect
Ps0 f19 head f18 mainIn #connect
Ps0 f31 head f16 mainIn #connect
Ps0 f178 out f22 tail #connect
Ps0 f22 head f20 mainIn #connect
Ps0 f178 out f31 tail #connect
