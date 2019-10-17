[Ivy]
16343BD885A12720 7.5.0 #module
>Proto >Proto Collection #zClass
Us0 UserTaskWithMailFormProcess Big #zClass
Us0 RD #cInfo
Us0 #process
Us0 @TextInP .type .type #zField
Us0 @TextInP .processKind .processKind #zField
Us0 @AnnotationInP-0n ai ai #zField
Us0 @MessageFlowInP-0n messageIn messageIn #zField
Us0 @MessageFlowOutP-0n messageOut messageOut #zField
Us0 @TextInP .xml .xml #zField
Us0 @TextInP .responsibility .responsibility #zField
Us0 @UdInit f0 '' #zField
Us0 @UdProcessEnd f1 '' #zField
Us0 @UdEvent f3 '' #zField
Us0 @UdExitEnd f4 '' #zField
Us0 @UdMethod f8 '' #zField
Us0 @GridStep f9 '' #zField
Us0 @PushWFArc f10 '' #zField
Us0 @GridStep f12 '' #zField
Us0 @PushWFArc f13 '' #zField
Us0 @UdProcessEnd f11 '' #zField
Us0 @PushWFArc f14 '' #zField
Us0 @GridStep f15 '' #zField
Us0 @PushWFArc f2 '' #zField
Us0 @PushWFArc f6 '' #zField
Us0 @GridStep f7 '' #zField
Us0 @PushWFArc f16 '' #zField
Us0 @PushWFArc f5 '' #zField
>Proto Us0 Us0 UserTaskWithMailFormProcess #zField
Us0 f0 guid 162F0A4FA20C17F1 #txt
Us0 f0 method start(gawfs.ExecutePredefinedWorkflowData) #txt
Us0 f0 inParameterDecl '<gawfs.ExecutePredefinedWorkflowData executePredefinedWorkflowData> param;' #txt
Us0 f0 inParameterMapAction 'out.executePredefinedWorkflowData=param.executePredefinedWorkflowData;
' #txt
Us0 f0 outParameterDecl '<gawfs.ExecutePredefinedWorkflowData executePredefinedWorkflowData> result;' #txt
Us0 f0 outParameterMapAction 'result.executePredefinedWorkflowData=in.executePredefinedWorkflowData;
' #txt
Us0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start()</name>
        <nameStyle>7,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Us0 f0 83 51 26 26 -16 15 #rect
Us0 f0 @|UdInitIcon #fIcon
Us0 f1 595 51 26 26 0 12 #rect
Us0 f1 @|UdProcessEndIcon #fIcon
Us0 f3 guid 162F0A4FA24F861A #txt
Us0 f3 actionTable 'out=in;
' #txt
Us0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>close</name>
    </language>
</elementInfo>
' #txt
Us0 f3 83 147 26 26 -15 12 #rect
Us0 f3 @|UdEventIcon #fIcon
Us0 f4 595 147 26 26 0 12 #rect
Us0 f4 @|UdExitEndIcon #fIcon
Us0 f8 guid 162F0A6B9E82BE32 #txt
Us0 f8 method cancel() #txt
Us0 f8 inParameterDecl '<> param;' #txt
Us0 f8 outParameterDecl '<> result;' #txt
Us0 f8 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>cancel()</name>
    </language>
</elementInfo>
' #txt
Us0 f8 83 243 26 26 -22 15 #rect
Us0 f8 @|UdMethodIcon #fIcon
Us0 f9 actionTable 'out=in;
' #txt
Us0 f9 actionCode 'import ch.ivy.addon.portal.generic.navigation.PortalNavigator;
ivy.task.reset();
PortalNavigator navigator = new PortalNavigator();
navigator.navigateToPortalEndPage();' #txt
Us0 f9 security system #txt
Us0 f9 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Reset task</name>
    </language>
</elementInfo>
' #txt
Us0 f9 192 234 112 44 -29 -8 #rect
Us0 f9 @|StepIcon #fIcon
Us0 f10 expr out #txt
Us0 f10 109 256 192 256 #arcP
Us0 f12 actionTable 'out=in;
' #txt
Us0 f12 actionCode 'import ch.ivy.gawfs.ExpressProcessUtils;
import ch.ivy.gawfs.mail.InformationMailSender;
import ch.ivy.gawfs.mail.InformationMailSender;

InformationMailSender mailSender = new InformationMailSender();
ExpressProcessUtils expressProcessUtils = new ExpressProcessUtils();
String taskFolder = expressProcessUtils.generateProcessFolder();
String folderPath = "/Express/Task/" + taskFolder + "/Attachment/";
expressProcessUtils.saveAttachmentsForEmail(folderPath, in.email);
mailSender.send(in.email);' #txt
Us0 f12 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Update working&#xD;
task definition</name>
    </language>
</elementInfo>
' #txt
Us0 f12 192 138 128 44 -41 -16 #rect
Us0 f12 @|StepIcon #fIcon
Us0 f13 expr out #txt
Us0 f13 109 160 192 160 #arcP
Us0 f11 595 243 26 26 0 12 #rect
Us0 f11 @|UdProcessEndIcon #fIcon
Us0 f14 expr out #txt
Us0 f14 304 256 595 256 #arcP
Us0 f15 actionTable 'out=in;
' #txt
Us0 f15 actionCode 'import ch.ivy.addon.portalkit.bo.ExpressUserEmail;
import ch.ivyteam.ivy.security.IUser;
import gawfs.TaskDef;

in.executePredefinedWorkflowData.currentTask.dynaFormController.createForm();
in.email = new ExpressUserEmail();

for (TaskDef task : in.executePredefinedWorkflowData.finishedTasks) {
	task.actualApplicant = ivy.wf.getSecurityContext().findUser(task.actualApplicantName);
}' #txt
Us0 f15 security system #txt
Us0 f15 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Initialize</name>
    </language>
</elementInfo>
' #txt
Us0 f15 194 42 112 44 -22 -8 #rect
Us0 f15 @|StepIcon #fIcon
Us0 f2 expr out #txt
Us0 f2 109 64 194 64 #arcP
Us0 f6 expr out #txt
Us0 f6 306 64 595 64 #arcP
Us0 f7 actionTable 'out=in;
' #txt
Us0 f7 actionCode 'in.executePredefinedWorkflowData.currentTask.actualApplicantName = ivy.session.getSessionUser().getName();' #txt
Us0 f7 security system #txt
Us0 f7 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Update applicant&#xD;
information</name>
    </language>
</elementInfo>
' #txt
Us0 f7 384 138 128 44 -43 -16 #rect
Us0 f7 @|StepIcon #fIcon
Us0 f16 expr out #txt
Us0 f16 320 160 384 160 #arcP
Us0 f5 expr out #txt
Us0 f5 512 160 595 160 #arcP
>Proto Us0 .type ch.ivy.gawfs.workflowExecution.UserTaskWithMailForm.UserTaskWithMailFormData #txt
>Proto Us0 .processKind HTML_DIALOG #txt
>Proto Us0 -8 -8 16 16 16 26 #rect
>Proto Us0 '' #fIcon
Us0 f8 mainOut f10 tail #connect
Us0 f10 head f9 mainIn #connect
Us0 f3 mainOut f13 tail #connect
Us0 f13 head f12 mainIn #connect
Us0 f9 mainOut f14 tail #connect
Us0 f14 head f11 mainIn #connect
Us0 f0 mainOut f2 tail #connect
Us0 f2 head f15 mainIn #connect
Us0 f15 mainOut f6 tail #connect
Us0 f6 head f1 mainIn #connect
Us0 f12 mainOut f16 tail #connect
Us0 f16 head f7 mainIn #connect
Us0 f7 mainOut f5 tail #connect
Us0 f5 head f4 mainIn #connect
