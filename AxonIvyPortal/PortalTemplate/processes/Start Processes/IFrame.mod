[Ivy]
16E1AAC7F373B1B7 7.5.0 #module
>Proto >Proto Collection #zClass
Ie0 IFrame Big #zClass
Ie0 B #cInfo
Ie0 #process
Ie0 @TextInP .type .type #zField
Ie0 @TextInP .processKind .processKind #zField
Ie0 @TextInP .xml .xml #zField
Ie0 @TextInP .responsibility .responsibility #zField
Ie0 @StartRequest f0 '' #zField
Ie0 @UserDialog f3 '' #zField
Ie0 @GridStep f5 '' #zField
Ie0 @PushWFArc f6 '' #zField
Ie0 @Alternative f7 '' #zField
Ie0 @PushWFArc f8 '' #zField
Ie0 @GridStep f4 '' #zField
Ie0 @PushWFArc f10 '' #zField
Ie0 @InfoButton f1 '' #zField
Ie0 @GridStep f2 '' #zField
Ie0 @PushWFArc f11 '' #zField
Ie0 @PushWFArc f9 '' #zField
>Proto Ie0 Ie0 IFrame #zField
Ie0 f0 outLink DefaultFramePage.ivp #txt
Ie0 f0 inParamDecl '<String relativeUrl,Number runningTaskId> param;' #txt
Ie0 f0 inParamTable 'out.taskId=param.runningTaskId;
' #txt
Ie0 f0 actionCode 'import ch.ivyteam.ivy.bpm.error.BpmError;
if (param.relativeUrl.startsWith("/ivy/")) {
  out.url = param.relativeUrl;
} else {
  BpmError.create("frame:unsupported:url").withMessage("only relative urls are supported (security reasons)").throwError();
}' #txt
Ie0 f0 requestEnabled true #txt
Ie0 f0 triggerEnabled false #txt
Ie0 f0 callSignature DefaultFramePage(String,Number) #txt
Ie0 f0 caseData businessCase.attach=true #txt
Ie0 f0 showInStartList 0 #txt
Ie0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>DefaultFramePage.ivp</name>
    </language>
</elementInfo>
' #txt
Ie0 f0 @C|.responsibility Everybody #txt
Ie0 f0 81 49 30 30 -48 16 #rect
Ie0 f0 @|StartRequestIcon #fIcon
Ie0 f3 dialogId ch.ivy.addon.portal.generic.iframe.TaskTemplateIFrame #txt
Ie0 f3 startMethod start(String,Number) #txt
Ie0 f3 requestActionDecl '<String url,Number taskId> param;' #txt
Ie0 f3 requestMappingAction 'param.url=in.url;
param.taskId=in.taskId;
' #txt
Ie0 f3 responseMappingAction 'out=in;
' #txt
Ie0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>TaskTemplateIFrame</name>
    </language>
</elementInfo>
' #txt
Ie0 f3 576 42 128 44 -55 -8 #rect
Ie0 f3 @|UserDialogIcon #fIcon
Ie0 f5 actionTable 'out=in;
' #txt
Ie0 f5 actionCode 'import ch.ivy.addon.portalkit.constant.CustomFields;
import ch.ivy.addon.portalkit.enums.AdditionalProperty;
import ch.ivy.addon.portalkit.enums.GlobalVariable;
import ch.ivy.addon.portalkit.service.GlobalSettingService;
import ch.ivy.addon.portalkit.service.IvyAdapterService;
import ch.ivyteam.ivy.workflow.ITask;

// There are two levels: custom field in task, if false, check Portal global setting
GlobalSettingService service = new GlobalSettingService();
ITask task = ivy.wf.findTask(in.taskId);
if (task != null) {
	boolean isExpress = Boolean.parseBoolean(task.getCase().customFields().stringField(CustomFields.IS_EXPRESS_PROCESS).getOrDefault("false"));
	out.embedInIFrame = !isExpress && (Boolean.parseBoolean(service.findGlobalSettingValue(GlobalVariable.EMBEDED_IN_IFRAME.toString())) || IvyAdapterService.getTaskEmbedInIFrameCustomField(task));
}' #txt
Ie0 f5 security system #txt
Ie0 f5 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>get embedInIFrame setting</name>
    </language>
</elementInfo>
' #txt
Ie0 f5 152 42 144 44 -69 -8 #rect
Ie0 f5 @|StepIcon #fIcon
Ie0 f6 111 64 152 64 #arcP
Ie0 f7 336 48 32 32 0 16 #rect
Ie0 f7 @|AlternativeIcon #fIcon
Ie0 f8 296 64 336 64 #arcP
Ie0 f4 actionTable 'out=in;
' #txt
Ie0 f4 actionCode 'import ch.ivy.addon.portalkit.util.RequestUtil;
RequestUtil.redirect(in.url);' #txt
Ie0 f4 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Redirect to task start</name>
    </language>
</elementInfo>
' #txt
Ie0 f4 424 138 112 44 -53 -8 #rect
Ie0 f4 @|StepIcon #fIcon
Ie0 f10 352 80 424 160 #arcP
Ie0 f10 1 352 160 #addKink
Ie0 f10 1 0.1556203489120305 0 0 #arcLabel
Ie0 f1 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>If open a process/start in "embedInFrame" mode (add to url embedInFrame parameter), &#13;
DefaultFramePage process will run</name>
    </language>
</elementInfo>
' #txt
Ie0 f1 56 202 464 44 -228 -16 #rect
Ie0 f1 @|IBIcon #fIcon
Ie0 f2 actionTable 'out=in;
' #txt
Ie0 f2 actionCode 'import ch.ivy.addon.portalkit.util.SecurityServiceUtils;
import ch.ivy.addon.portalkit.service.StickyTaskListService;

String iframeAttr = StickyTaskListService.service().getIFrameSessionAttributeKey(in.taskId);
SecurityServiceUtils.setSessionAttribute(iframeAttr, true);' #txt
Ie0 f2 security system #txt
Ie0 f2 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Set IFrame attribute</name>
    </language>
</elementInfo>
' #txt
Ie0 f2 424 42 112 44 -51 -8 #rect
Ie0 f2 @|StepIcon #fIcon
Ie0 f11 expr in #txt
Ie0 f11 outCond in.embedInIFrame #txt
Ie0 f11 368 64 424 64 #arcP
Ie0 f9 536 64 576 64 #arcP
>Proto Ie0 .type ch.ivy.addon.portal.generic.IFrameData #txt
>Proto Ie0 .processKind NORMAL #txt
>Proto Ie0 0 0 32 24 18 0 #rect
>Proto Ie0 @|BIcon #fIcon
Ie0 f0 mainOut f6 tail #connect
Ie0 f6 head f5 mainIn #connect
Ie0 f5 mainOut f8 tail #connect
Ie0 f8 head f7 in #connect
Ie0 f10 head f4 mainIn #connect
Ie0 f7 out f11 tail #connect
Ie0 f11 head f2 mainIn #connect
Ie0 f7 out f10 tail #connect
Ie0 f2 mainOut f9 tail #connect
Ie0 f9 head f3 mainIn #connect
