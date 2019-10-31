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
Ie0 @EndTask f1 '' #zField
Ie0 @UserDialog f3 '' #zField
Ie0 @PushWFArc f4 '' #zField
Ie0 @PushWFArc f2 '' #zField
>Proto Ie0 Ie0 IFrame #zField
Ie0 f0 outLink DefaultFramePage.ivp #txt
Ie0 f0 inParamDecl '<String relativeUrl,Number runningTaskId> param;' #txt
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
Ie0 f1 369 49 30 30 0 15 #rect
Ie0 f1 @|EndIcon #fIcon
Ie0 f3 dialogId ch.ivy.addon.portal.generic.iframe.TaskTemplateIFrame #txt
Ie0 f3 startMethod start(String) #txt
Ie0 f3 requestActionDecl '<String url> param;' #txt
Ie0 f3 requestMappingAction 'param.url=in.url;
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
Ie0 f3 192 42 128 44 -55 -8 #rect
Ie0 f3 @|UserDialogIcon #fIcon
Ie0 f4 111 64 192 64 #arcP
Ie0 f2 320 64 369 64 #arcP
>Proto Ie0 .type ch.ivy.addon.portal.generic.IFrameData #txt
>Proto Ie0 .processKind NORMAL #txt
>Proto Ie0 0 0 32 24 18 0 #rect
>Proto Ie0 @|BIcon #fIcon
Ie0 f0 mainOut f4 tail #connect
Ie0 f4 head f3 mainIn #connect
Ie0 f3 mainOut f2 tail #connect
Ie0 f2 head f1 mainIn #connect
