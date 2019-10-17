[Ivy]
163051859F226598 7.5.0 #module
>Proto >Proto Collection #zClass
Fs0 FinalReviewFormProcess Big #zClass
Fs0 RD #cInfo
Fs0 #process
Fs0 @TextInP .type .type #zField
Fs0 @TextInP .processKind .processKind #zField
Fs0 @AnnotationInP-0n ai ai #zField
Fs0 @MessageFlowInP-0n messageIn messageIn #zField
Fs0 @MessageFlowOutP-0n messageOut messageOut #zField
Fs0 @TextInP .xml .xml #zField
Fs0 @TextInP .responsibility .responsibility #zField
Fs0 @UdInit f0 '' #zField
Fs0 @UdProcessEnd f1 '' #zField
Fs0 @UdEvent f3 '' #zField
Fs0 @UdExitEnd f4 '' #zField
Fs0 @PushWFArc f5 '' #zField
Fs0 @UdEvent f16 '' #zField
Fs0 @UdProcessEnd f19 '' #zField
Fs0 @GridStep f17 '' #zField
Fs0 @PushWFArc f20 '' #zField
Fs0 @PushWFArc f18 '' #zField
Fs0 @GridStep f6 '' #zField
Fs0 @PushWFArc f7 '' #zField
Fs0 @PushWFArc f2 '' #zField
>Proto Fs0 Fs0 FinalReviewFormProcess #zField
Fs0 f0 guid 16305185A13E9F88 #txt
Fs0 f0 method start(java.util.List<gawfs.TaskDef>,java.util.List<String>,java.lang.Integer) #txt
Fs0 f0 inParameterDecl '<java.util.List<gawfs.TaskDef> finishedTasks,java.util.List<String> steps,Integer actualStepIndex> param;' #txt
Fs0 f0 inParameterMapAction 'out.actualStepIndex=param.actualStepIndex;
out.finishedTasks=param.finishedTasks;
out.steps=param.steps;
' #txt
Fs0 f0 outParameterDecl '<> result;' #txt
Fs0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start()</name>
        <nameStyle>7,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Fs0 f0 83 51 26 26 -16 15 #rect
Fs0 f0 @|UdInitIcon #fIcon
Fs0 f1 531 51 26 26 0 12 #rect
Fs0 f1 @|UdProcessEndIcon #fIcon
Fs0 f3 guid 16305185A2B30A9B #txt
Fs0 f3 actionTable 'out=in;
' #txt
Fs0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>close</name>
    </language>
</elementInfo>
' #txt
Fs0 f3 83 147 26 26 -15 12 #rect
Fs0 f3 @|UdEventIcon #fIcon
Fs0 f4 211 147 26 26 0 12 #rect
Fs0 f4 @|UdExitEndIcon #fIcon
Fs0 f5 expr out #txt
Fs0 f5 109 160 211 160 #arcP
Fs0 f16 guid 16348C318DEC020D #txt
Fs0 f16 actionTable 'out=in;
' #txt
Fs0 f16 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>cancel</name>
        <nameStyle>6,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Fs0 f16 77 251 26 26 -18 15 #rect
Fs0 f16 @|UdEventIcon #fIcon
Fs0 f19 525 251 26 26 0 12 #rect
Fs0 f19 @|UdProcessEndIcon #fIcon
Fs0 f17 actionTable 'out=in;
' #txt
Fs0 f17 actionCode 'import org.apache.commons.lang3.StringUtils;
import ch.ivy.addon.portalkit.enums.AdditionalProperty;
import ch.ivy.addon.portal.generic.navigation.PortalNavigator;

ivy.task.reset();
ivy.task.customFields().textField(AdditionalProperty.PORTAL_TASK_CALLBACK_URI.toString()).set(StringUtils.EMPTY);
PortalNavigator navigator = new PortalNavigator();
navigator.navigateToPortalEndPage();' #txt
Fs0 f17 security system #txt
Fs0 f17 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Reset task</name>
    </language>
</elementInfo>
' #txt
Fs0 f17 250 242 112 44 -29 -8 #rect
Fs0 f17 @|StepIcon #fIcon
Fs0 f20 expr out #txt
Fs0 f20 362 264 525 264 #arcP
Fs0 f18 expr out #txt
Fs0 f18 103 264 250 264 #arcP
Fs0 f6 actionTable 'out=in;
' #txt
Fs0 f6 actionCode 'import gawfs.TaskDef;

for (TaskDef task : in.finishedTasks) {
	task.actualApplicant = ivy.wf.getSecurityContext().findUser(task.actualApplicantName);
}' #txt
Fs0 f6 security system #txt
Fs0 f6 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Initialize</name>
    </language>
</elementInfo>
' #txt
Fs0 f6 229 41 112 44 -22 -8 #rect
Fs0 f6 @|StepIcon #fIcon
Fs0 f7 expr out #txt
Fs0 f7 108 63 229 63 #arcP
Fs0 f2 expr out #txt
Fs0 f2 341 63 531 63 #arcP
>Proto Fs0 .type ch.ivy.gawfs.workflowExecution.FinalReviewForm.FinalReviewFormData #txt
>Proto Fs0 .processKind HTML_DIALOG #txt
>Proto Fs0 -8 -8 16 16 16 26 #rect
>Proto Fs0 '' #fIcon
Fs0 f3 mainOut f5 tail #connect
Fs0 f5 head f4 mainIn #connect
Fs0 f16 mainOut f18 tail #connect
Fs0 f18 head f17 mainIn #connect
Fs0 f17 mainOut f20 tail #connect
Fs0 f20 head f19 mainIn #connect
Fs0 f0 mainOut f7 tail #connect
Fs0 f7 head f6 mainIn #connect
Fs0 f6 mainOut f2 tail #connect
Fs0 f2 head f1 mainIn #connect
