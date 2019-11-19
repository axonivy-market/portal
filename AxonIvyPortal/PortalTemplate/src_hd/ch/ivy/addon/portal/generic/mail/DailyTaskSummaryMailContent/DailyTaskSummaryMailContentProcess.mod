[Ivy]
16E441174A19DCB5 7.5.0 #module
>Proto >Proto Collection #zClass
Ds0 DailyTaskSummaryMailContentProcess Big #zClass
Ds0 RD #cInfo
Ds0 #process
Ds0 @TextInP .type .type #zField
Ds0 @TextInP .processKind .processKind #zField
Ds0 @TextInP .xml .xml #zField
Ds0 @TextInP .responsibility .responsibility #zField
Ds0 @UdInit f0 '' #zField
Ds0 @UdProcessEnd f1 '' #zField
Ds0 @UdMethod f3 '' #zField
Ds0 @UdProcessEnd f4 '' #zField
Ds0 @PushWFArc f5 '' #zField
Ds0 @UdMethod f6 '' #zField
Ds0 @UdProcessEnd f7 '' #zField
Ds0 @PushWFArc f8 '' #zField
Ds0 @GridStep f9 '' #zField
Ds0 @PushWFArc f10 '' #zField
Ds0 @PushWFArc f2 '' #zField
Ds0 @UdMethod f11 '' #zField
Ds0 @UdProcessEnd f12 '' #zField
Ds0 @GridStep f14 '' #zField
Ds0 @PushWFArc f15 '' #zField
Ds0 @PushWFArc f13 '' #zField
>Proto Ds0 Ds0 DailyTaskSummaryMailContentProcess #zField
Ds0 f0 guid 16E441174A545BB2 #txt
Ds0 f0 method start(List<ITask>) #txt
Ds0 f0 inParameterDecl '<java.util.List<ch.ivyteam.ivy.workflow.ITask> tasks> param;' #txt
Ds0 f0 inParameterMapAction 'out.tasks=param.tasks;
' #txt
Ds0 f0 outParameterDecl '<> result;' #txt
Ds0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start(List&lt;ITask&gt;)</name>
    </language>
</elementInfo>
' #txt
Ds0 f0 83 51 26 26 -41 12 #rect
Ds0 f0 @|UdInitIcon #fIcon
Ds0 f1 339 51 26 26 0 12 #rect
Ds0 f1 @|UdProcessEndIcon #fIcon
Ds0 f3 guid 16E44B4BB11A0F0F #txt
Ds0 f3 method taskPriority(ITask) #txt
Ds0 f3 inParameterDecl '<ch.ivyteam.ivy.workflow.ITask task> param;' #txt
Ds0 f3 inParameterMapAction 'out.task=param.task;
' #txt
Ds0 f3 outParameterDecl '<String taskPriority> result;' #txt
Ds0 f3 outParameterMapAction 'result.taskPriority=org.apache.commons.lang3.StringUtils.capitalize(ivy.cms.co("/ch.ivy.addon.portalkit.ui.jsf/taskPriority/" + in.task.getPriority()).toLowerCase());
' #txt
Ds0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>taskPriority(ITask)</name>
    </language>
</elementInfo>
' #txt
Ds0 f3 83 147 26 26 -41 15 #rect
Ds0 f3 @|UdMethodIcon #fIcon
Ds0 f4 211 147 26 26 0 12 #rect
Ds0 f4 @|UdProcessEndIcon #fIcon
Ds0 f5 109 160 211 160 #arcP
Ds0 f6 guid 16E44B7B63B5ECC8 #txt
Ds0 f6 method taskState(ITask) #txt
Ds0 f6 inParameterDecl '<ch.ivyteam.ivy.workflow.ITask task> param;' #txt
Ds0 f6 inParameterMapAction 'out.task=param.task;
' #txt
Ds0 f6 outParameterDecl '<String taskState> result;' #txt
Ds0 f6 outParameterMapAction 'result.taskState=ivy.cms.co("/ch.ivy.addon.portalkit.ui.jsf/taskState/" + in.task.getState());
' #txt
Ds0 f6 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>taskState(ITask)</name>
    </language>
</elementInfo>
' #txt
Ds0 f6 83 243 26 26 -38 16 #rect
Ds0 f6 @|UdMethodIcon #fIcon
Ds0 f7 211 243 26 26 0 12 #rect
Ds0 f7 @|UdProcessEndIcon #fIcon
Ds0 f8 109 256 211 256 #arcP
Ds0 f9 actionTable 'out=in;
' #txt
Ds0 f9 actionCode 'import org.primefaces.model.DefaultStreamedContent;

out.logo = new DefaultStreamedContent(ivy.cms.findContentObjectValue("/images/logo/CorporateLogo", ivy.cms.defaultLanguage).getContentAsBinaryStream(), "image/png");' #txt
Ds0 f9 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Init data</name>
    </language>
</elementInfo>
' #txt
Ds0 f9 168 42 112 44 -20 -8 #rect
Ds0 f9 @|StepIcon #fIcon
Ds0 f10 109 64 168 64 #arcP
Ds0 f2 280 64 339 64 #arcP
Ds0 f11 guid 16E4A49C4D24B8BE #txt
Ds0 f11 method taskStart(ITask) #txt
Ds0 f11 inParameterDecl '<ch.ivyteam.ivy.workflow.ITask task> param;' #txt
Ds0 f11 inParameterMapAction 'out.task=param.task;
' #txt
Ds0 f11 outParameterDecl '<String taskStart> result;' #txt
Ds0 f11 outParameterMapAction 'result.taskStart=in.taskStart;
' #txt
Ds0 f11 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>taskStart(ITask)</name>
    </language>
</elementInfo>
' #txt
Ds0 f11 83 339 26 26 -23 15 #rect
Ds0 f11 @|UdMethodIcon #fIcon
Ds0 f12 339 339 26 26 0 12 #rect
Ds0 f12 @|UdProcessEndIcon #fIcon
Ds0 f14 actionTable 'out=in;
' #txt
Ds0 f14 actionCode 'import ch.ivy.addon.portalkit.service.IvyAdapterService;
import ch.ivyteam.ivy.request.RequestUriFactory;

out.taskStart = RequestUriFactory.createExternalServerUri().resolve(RequestUriFactory.createTaskStartUri(in.task)).toASCIIString();
boolean enabledStartInIFrame = IvyAdapterService.getTaskEmbedInIFrameCustomField(in.task);
if (enabledStartInIFrame) {
	out.taskStart += "&embedInFrame";
}' #txt
Ds0 f14 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Build task start</name>
    </language>
</elementInfo>
' #txt
Ds0 f14 168 330 112 44 -38 -8 #rect
Ds0 f14 @|StepIcon #fIcon
Ds0 f15 109 352 168 352 #arcP
Ds0 f13 280 352 339 352 #arcP
>Proto Ds0 .type ch.ivy.addon.portal.generic.mail.DailyTaskSummaryMailContent.DailyTaskSummaryMailContentData #txt
>Proto Ds0 .processKind HTML_DIALOG #txt
>Proto Ds0 -8 -8 16 16 16 26 #rect
>Proto Ds0 '' #fIcon
Ds0 f3 mainOut f5 tail #connect
Ds0 f5 head f4 mainIn #connect
Ds0 f6 mainOut f8 tail #connect
Ds0 f8 head f7 mainIn #connect
Ds0 f0 mainOut f10 tail #connect
Ds0 f10 head f9 mainIn #connect
Ds0 f9 mainOut f2 tail #connect
Ds0 f2 head f1 mainIn #connect
Ds0 f11 mainOut f15 tail #connect
Ds0 f15 head f14 mainIn #connect
Ds0 f14 mainOut f13 tail #connect
Ds0 f13 head f12 mainIn #connect
