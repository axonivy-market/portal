[Ivy]
172591B4F5B4DE03 9.2.0 #module
>Proto >Proto Collection #zClass
Ls0 LeaveRequestOverviewProcess Big #zClass
Ls0 RD #cInfo
Ls0 #process
Ls0 @TextInP .type .type #zField
Ls0 @TextInP .processKind .processKind #zField
Ls0 @TextInP .xml .xml #zField
Ls0 @TextInP .responsibility .responsibility #zField
Ls0 @UdInit f0 '' #zField
Ls0 @UdProcessEnd f1 '' #zField
Ls0 @UdEvent f3 '' #zField
Ls0 @UdExitEnd f4 '' #zField
Ls0 @PushWFArc f5 '' #zField
Ls0 @GridStep f6 '' #zField
Ls0 @PushWFArc f7 '' #zField
Ls0 @PushWFArc f2 '' #zField
Ls0 @UdEvent f8 '' #zField
Ls0 @UserDialog f9 '' #zField
Ls0 @PushWFArc f10 '' #zField
>Proto Ls0 Ls0 LeaveRequestOverviewProcess #zField
Ls0 f0 guid 172591B4F815A757 #txt
Ls0 f0 method start(ch.ivy.addon.portalkit.persistence.domain.UserProcess) #txt
Ls0 f0 inParameterDecl '<ch.ivy.addon.portalkit.persistence.domain.UserProcess userProcess> param;' #txt
Ls0 f0 inParameterMapAction 'out.userProcess=param.userProcess;
' #txt
Ls0 f0 outParameterDecl '<> result;' #txt
Ls0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start()</name>
    </language>
</elementInfo>
' #txt
Ls0 f0 83 51 26 26 -16 15 #rect
Ls0 f0 @|UdInitIcon #fIcon
Ls0 f1 371 51 26 26 0 12 #rect
Ls0 f1 @|UdProcessEndIcon #fIcon
Ls0 f3 guid 172591B4F90C05EB #txt
Ls0 f3 actionTable 'out=in;
' #txt
Ls0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>close</name>
    </language>
</elementInfo>
' #txt
Ls0 f3 83 147 26 26 -15 15 #rect
Ls0 f3 @|UdEventIcon #fIcon
Ls0 f4 211 147 26 26 0 12 #rect
Ls0 f4 @|UdExitEndIcon #fIcon
Ls0 f5 109 160 211 160 #arcP
Ls0 f6 actionTable 'out=in;
' #txt
Ls0 f6 actionCode 'import java.util.Arrays;
import ch.ivy.addon.portalkit.bo.ProcessStep;

ProcessStep creationStep = new ProcessStep();
creationStep.setName(ivy.cms.co("/Processes/LeaveRequest/Creation/name"));
creationStep.setDescriptions(Arrays.asList(ivy.cms.co("/Processes/LeaveRequest/Creation/description1"), ivy.cms.co("/Processes/LeaveRequest/Creation/description2"), ivy.cms.co("/Processes/LeaveRequest/Creation/description3")));
in.processSteps.add(creationStep);

ProcessStep approvalStep = new ProcessStep();
approvalStep.setName(ivy.cms.co("/Processes/LeaveRequest/Approval/name"));
approvalStep.setDescriptions(Arrays.asList(ivy.cms.co("/Processes/LeaveRequest/Approval/description1"), ivy.cms.co("/Processes/LeaveRequest/Approval/description2")));
in.processSteps.add(approvalStep);

ProcessStep summaryStep = new ProcessStep();
summaryStep.setName(ivy.cms.co("/Processes/LeaveRequest/Summary/name"));
summaryStep.setDescriptions(Arrays.asList(ivy.cms.co("/Processes/LeaveRequest/Summary/description1"), ivy.cms.co("/Processes/LeaveRequest/Summary/description2")));
in.processSteps.add(summaryStep);' #txt
Ls0 f6 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>init data</name>
    </language>
</elementInfo>
' #txt
Ls0 f6 168 42 112 44 -21 -8 #rect
Ls0 f6 @|StepIcon #fIcon
Ls0 f7 109 64 168 64 #arcP
Ls0 f2 280 64 371 64 #arcP
Ls0 f8 guid 17298017D25BAF7E #txt
Ls0 f8 actionTable 'out=in;
' #txt
Ls0 f8 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>cancel</name>
    </language>
</elementInfo>
' #txt
Ls0 f8 83 243 26 26 -14 15 #rect
Ls0 f8 @|UdEventIcon #fIcon
Ls0 f9 dialogId com.axonivy.portal.userexamples.ExampleHomePage #txt
Ls0 f9 startMethod start() #txt
Ls0 f9 requestActionDecl '<> param;' #txt
Ls0 f9 responseMappingAction 'out=in;
' #txt
Ls0 f9 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>ExampleHomePage</name>
    </language>
</elementInfo>
' #txt
Ls0 f9 160 234 128 44 -55 -8 #rect
Ls0 f9 @|UserDialogIcon #fIcon
Ls0 f10 109 256 160 256 #arcP
>Proto Ls0 .type com.axonivy.portal.userexamples.leaverequest.LeaveRequestOverview.LeaveRequestOverviewData #txt
>Proto Ls0 .processKind HTML_DIALOG #txt
>Proto Ls0 -8 -8 16 16 16 26 #rect
>Proto Ls0 '' #fIcon
Ls0 f3 mainOut f5 tail #connect
Ls0 f5 head f4 mainIn #connect
Ls0 f0 mainOut f7 tail #connect
Ls0 f7 head f6 mainIn #connect
Ls0 f6 mainOut f2 tail #connect
Ls0 f2 head f1 mainIn #connect
Ls0 f8 mainOut f10 tail #connect
Ls0 f10 head f9 mainIn #connect
