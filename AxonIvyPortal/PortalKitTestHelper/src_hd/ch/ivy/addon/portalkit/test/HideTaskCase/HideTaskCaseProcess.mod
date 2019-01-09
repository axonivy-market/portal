[Ivy]
1682D02949BF4113 3.20 #module
>Proto >Proto Collection #zClass
Hs0 HideTaskCaseProcess Big #zClass
Hs0 RD #cInfo
Hs0 #process
Hs0 @TextInP .ui2RdDataAction .ui2RdDataAction #zField
Hs0 @TextInP .rdData2UIAction .rdData2UIAction #zField
Hs0 @TextInP .resExport .resExport #zField
Hs0 @TextInP .type .type #zField
Hs0 @TextInP .processKind .processKind #zField
Hs0 @AnnotationInP-0n ai ai #zField
Hs0 @MessageFlowInP-0n messageIn messageIn #zField
Hs0 @MessageFlowOutP-0n messageOut messageOut #zField
Hs0 @TextInP .xml .xml #zField
Hs0 @TextInP .responsibility .responsibility #zField
Hs0 @RichDialogInitStart f0 '' #zField
Hs0 @RichDialogProcessEnd f1 '' #zField
Hs0 @PushWFArc f2 '' #zField
Hs0 @RichDialogProcessStart f3 '' #zField
Hs0 @RichDialogEnd f4 '' #zField
Hs0 @PushWFArc f5 '' #zField
Hs0 @RichDialogProcessStart f6 '' #zField
Hs0 @GridStep f9 '' #zField
Hs0 @PushWFArc f10 '' #zField
Hs0 @RichDialogEnd f11 '' #zField
Hs0 @PushWFArc f12 '' #zField
>Proto Hs0 Hs0 HideTaskCaseProcess #zField
Hs0 f0 guid 1682D0294BC45DF1 #txt
Hs0 f0 type ch.ivy.addon.portalkit.test.HideTaskCase.HideTaskCaseData #txt
Hs0 f0 method start() #txt
Hs0 f0 disableUIEvents true #txt
Hs0 f0 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<> param = methodEvent.getInputArguments();
' #txt
Hs0 f0 outParameterDecl '<> result;
' #txt
Hs0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start()</name>
    </language>
</elementInfo>
' #txt
Hs0 f0 83 51 26 26 -16 15 #rect
Hs0 f0 @|RichDialogInitStartIcon #fIcon
Hs0 f1 type ch.ivy.addon.portalkit.test.HideTaskCase.HideTaskCaseData #txt
Hs0 f1 211 51 26 26 0 12 #rect
Hs0 f1 @|RichDialogProcessEndIcon #fIcon
Hs0 f2 expr out #txt
Hs0 f2 109 64 211 64 #arcP
Hs0 f3 guid 1682D0294D0186EB #txt
Hs0 f3 type ch.ivy.addon.portalkit.test.HideTaskCase.HideTaskCaseData #txt
Hs0 f3 actionDecl 'ch.ivy.addon.portalkit.test.HideTaskCase.HideTaskCaseData out;
' #txt
Hs0 f3 actionTable 'out=in;
' #txt
Hs0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>close</name>
    </language>
</elementInfo>
' #txt
Hs0 f3 83 147 26 26 -15 12 #rect
Hs0 f3 @|RichDialogProcessStartIcon #fIcon
Hs0 f4 type ch.ivy.addon.portalkit.test.HideTaskCase.HideTaskCaseData #txt
Hs0 f4 guid 1682D0294D157CF9 #txt
Hs0 f4 211 147 26 26 0 12 #rect
Hs0 f4 @|RichDialogEndIcon #fIcon
Hs0 f5 expr out #txt
Hs0 f5 109 160 211 160 #arcP
Hs0 f6 guid 1682D080175ED39C #txt
Hs0 f6 type ch.ivy.addon.portalkit.test.HideTaskCase.HideTaskCaseData #txt
Hs0 f6 actionDecl 'ch.ivy.addon.portalkit.test.HideTaskCase.HideTaskCaseData out;
' #txt
Hs0 f6 actionTable 'out=in;
' #txt
Hs0 f6 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>hide</name>
    </language>
</elementInfo>
' #txt
Hs0 f6 83 243 26 26 -12 12 #rect
Hs0 f6 @|RichDialogProcessStartIcon #fIcon
Hs0 f9 actionDecl 'ch.ivy.addon.portalkit.test.HideTaskCase.HideTaskCaseData out;
' #txt
Hs0 f9 actionTable 'out=in;
' #txt
Hs0 f9 actionCode 'import ch.ivyteam.ivy.workflow.ICase;
import ch.ivyteam.ivy.workflow.ITask;
import ch.ivy.addon.portalkit.util.CaseUtils;
import ch.ivy.addon.portalkit.util.TaskUtils;

ITask task = TaskUtils.findTaskById(in.taskId);
if (task != null){
	TaskUtils.setHidePropertyToHideInPortal(task);
}

ICase iCase = CaseUtils.findcase(in.caseId);
if (iCase != null){
	CaseUtils.setHidePropertyToHideInPortal(iCase);
}' #txt
Hs0 f9 type ch.ivy.addon.portalkit.test.HideTaskCase.HideTaskCaseData #txt
Hs0 f9 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>hide</name>
        <nameStyle>4,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Hs0 f9 232 234 112 44 -12 -8 #rect
Hs0 f9 @|StepIcon #fIcon
Hs0 f10 expr out #txt
Hs0 f10 109 256 232 256 #arcP
Hs0 f11 type ch.ivy.addon.portalkit.test.HideTaskCase.HideTaskCaseData #txt
Hs0 f11 guid 1682D0CF104E2BC8 #txt
Hs0 f11 403 243 26 26 0 12 #rect
Hs0 f11 @|RichDialogEndIcon #fIcon
Hs0 f12 expr out #txt
Hs0 f12 344 256 403 256 #arcP
>Proto Hs0 .type ch.ivy.addon.portalkit.test.HideTaskCase.HideTaskCaseData #txt
>Proto Hs0 .processKind HTML_DIALOG #txt
>Proto Hs0 -8 -8 16 16 16 26 #rect
>Proto Hs0 '' #fIcon
Hs0 f0 mainOut f2 tail #connect
Hs0 f2 head f1 mainIn #connect
Hs0 f3 mainOut f5 tail #connect
Hs0 f5 head f4 mainIn #connect
Hs0 f6 mainOut f10 tail #connect
Hs0 f10 head f9 mainIn #connect
Hs0 f9 mainOut f12 tail #connect
Hs0 f12 head f11 mainIn #connect
