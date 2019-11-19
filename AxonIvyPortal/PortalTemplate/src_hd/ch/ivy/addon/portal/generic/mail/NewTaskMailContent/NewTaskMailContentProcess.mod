[Ivy]
16E3FC8ADA13E5B5 7.5.0 #module
>Proto >Proto Collection #zClass
Ns0 NewTaskMailContentProcess Big #zClass
Ns0 RD #cInfo
Ns0 #process
Ns0 @TextInP .type .type #zField
Ns0 @TextInP .processKind .processKind #zField
Ns0 @AnnotationInP-0n ai ai #zField
Ns0 @MessageFlowInP-0n messageIn messageIn #zField
Ns0 @MessageFlowOutP-0n messageOut messageOut #zField
Ns0 @TextInP .xml .xml #zField
Ns0 @TextInP .responsibility .responsibility #zField
Ns0 @UdInit f0 '' #zField
Ns0 @UdProcessEnd f1 '' #zField
Ns0 @GridStep f3 '' #zField
Ns0 @PushWFArc f4 '' #zField
Ns0 @PushWFArc f2 '' #zField
>Proto Ns0 Ns0 NewTaskMailContentProcess #zField
Ns0 f0 guid 147A9FEEB7B3C1B6 #txt
Ns0 f0 method start(ITask) #txt
Ns0 f0 inParameterDecl '<ch.ivyteam.ivy.workflow.ITask task> param;' #txt
Ns0 f0 inParameterMapAction 'out.task=param.task;
' #txt
Ns0 f0 outParameterDecl '<> result;' #txt
Ns0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start(ITask)</name>
    </language>
</elementInfo>
' #txt
Ns0 f0 83 51 26 26 -29 16 #rect
Ns0 f0 @|UdInitIcon #fIcon
Ns0 f1 339 51 26 26 0 12 #rect
Ns0 f1 @|UdProcessEndIcon #fIcon
Ns0 f3 actionTable 'out=in;
' #txt
Ns0 f3 actionCode 'import ch.ivy.addon.portalkit.service.IvyAdapterService;
import ch.ivyteam.ivy.request.RequestUriFactory;
import org.primefaces.model.DefaultStreamedContent;
import org.apache.commons.lang3.StringUtils;

out.taskPriority = StringUtils.capitalize(ivy.cms.co("/ch.ivy.addon.portalkit.ui.jsf/taskPriority/" + in.task.getPriority()).toLowerCase());
out.taskState = ivy.cms.co("/ch.ivy.addon.portalkit.ui.jsf/taskState/" + in.task.getState());

boolean enabledStartInIFrame = IvyAdapterService.getTaskEmbedInIFrameCustomField(in.task);
out.taskStart = RequestUriFactory.createExternalServerUri().resolve(RequestUriFactory.createTaskStartUri(in.task)).toASCIIString();
if (enabledStartInIFrame) {
	out.taskStart += "&embedInFrame";
}

out.logo = new DefaultStreamedContent(ivy.cms.findContentObjectValue("/images/logo/CorporateLogo", ivy.cms.defaultLanguage).getContentAsBinaryStream(), "image/png");
' #txt
Ns0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Init data</name>
    </language>
</elementInfo>
' #txt
Ns0 f3 168 42 112 44 -20 -8 #rect
Ns0 f3 @|StepIcon #fIcon
Ns0 f4 expr out #txt
Ns0 f4 109 64 168 64 #arcP
Ns0 f2 280 64 339 64 #arcP
>Proto Ns0 .type ch.ivy.addon.portal.generic.mail.NewTaskMailContent.NewTaskMailContentData #txt
>Proto Ns0 .processKind HTML_DIALOG #txt
>Proto Ns0 -8 -8 16 16 16 26 #rect
>Proto Ns0 '' #fIcon
Ns0 f0 mainOut f4 tail #connect
Ns0 f4 head f3 mainIn #connect
Ns0 f3 mainOut f2 tail #connect
Ns0 f2 head f1 mainIn #connect
