[Ivy]
17236DB1D3DA14C0 9.4.8 #module
>Proto >Proto Collection #zClass
Ue0 UserExampleGuide Big #zClass
Ue0 B #cInfo
Ue0 #process
Bk1 BpmnUserTask Big #zClass
Bk1 BpmnUserTask #cInfo
Bk3 BpmnUserTask Big #zClass
Bk3 BpmnUserTask #cInfo
Ue0 @TextInP .colors .colors #zField
Ue0 @TextInP color color #zField
Ue0 @TextInP .type .type #zField
Ue0 @TextInP .processKind .processKind #zField
Ue0 @TextInP .xml .xml #zField
Ue0 @TextInP .responsibility .responsibility #zField
Ue0 @StartRequest f0 '' #zField
Ue0 @EndTask f1 '' #zField
Ue0 Bk1 S20 'Sub 2' #zField
Ue0 @StartRequest f4 '' #zField
Ue0 @PushWFArc f19 '' #zField
Ue0 @EndTask f5 '' #zField
Ue0 @PushWFArc f7 '' #zField
Ue0 @PushWFArc f8 '' #zField
Ue0 Bk3 S31 'Sub 3' #zField
Ue0 @PushWFArc f2 '' #zField
Ue0 @InfoButton f3 '' #zField
Ue0 @InfoButton f6 '' #zField
Ue0 @AnnotationArc f9 '' #zField
Ue0 @AnnotationArc f10 '' #zField
>Proto Ue0 Ue0 UserExampleGuide #zField
Bk1 @TextInP .colors .colors #zField
Bk1 @TextInP color color #zField
Bk1 @AnnotationInP-0n ai ai #zField
Bk1 @TextInP tags tags #zField
Bk1 @MessageFlowInP-0n messageIn messageIn #zField
Bk1 @MessageFlowOutP-0n messageOut messageOut #zField
Bk1 @TextInP .xml .xml #zField
Bk1 @TextInP .responsibility .responsibility #zField
Bk1 @PushTrueWFInG-01 g0 '' #zField
Bk1 @PushTrueWFOutG-01 g1 '' #zField
Bk1 @GridStep f1 '' #zField
Bk1 @PushWFArc f2 '' #zField
Bk1 @TaskSwitchSimple f4 '' #zField
Bk1 @PushWFArc f0 '' #zField
Bk1 @TkArc f3 '' #zField
>Proto Bk1 Bk0 BpmnUserTask #zField
Bk3 @TextInP .colors .colors #zField
Bk3 @TextInP color color #zField
Bk3 @AnnotationInP-0n ai ai #zField
Bk3 @TextInP .type .type #zField
Bk3 @TextInP .processKind .processKind #zField
Bk3 @TextInP .xml .xml #zField
Bk3 @TextInP .responsibility .responsibility #zField
Bk3 @UserDialog f3 '' #zField
Bk3 @PushTrueWFInG-01 g0 '' #zField
Bk3 @PushWFArc f0 '' #zField
Bk3 @PushTrueWFOutG-01 g1 '' #zField
Bk3 @PushWFArc f1 '' #zField
>Proto Bk3 Bk2 BpmnUserTask #zField
Ue0 f0 outLink userExampleGuide.ivp #txt
Ue0 f0 inParamDecl '<> param;' #txt
Ue0 f0 requestEnabled true #txt
Ue0 f0 triggerEnabled false #txt
Ue0 f0 callSignature userExampleGuide() #txt
Ue0 f0 startName <%=ivy.cms.co("/Processes/UserExampleGuide/name")%> #txt
Ue0 f0 startDescription <%=ivy.cms.co("/Processes/UserExampleGuide/processDescription")%> #txt
Ue0 f0 caseData 'businessCase.attach=true
customFields.STRING.embedInFrame="false"' #txt
Ue0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>userExampleGuide.ivp</name>
    </language>
</elementInfo>
' #txt
Ue0 f0 @C|.responsibility Everybody #txt
Ue0 f0 177 465 30 30 -31 18 #rect
Ue0 f1 593 465 30 30 0 15 #rect
Ue0 S20 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Open Login page</name>
    </language>
</elementInfo>
' #txt
Ue0 S20 344 202 112 44 -47 -8 #rect
Ue0 f4 outLink startPortalLoginPage.ivp #txt
Ue0 f4 inParamDecl '<> param;' #txt
Ue0 f4 requestEnabled true #txt
Ue0 f4 triggerEnabled false #txt
Ue0 f4 callSignature startPortalLoginPage() #txt
Ue0 f4 startName <%=ivy.cms.co("/Processes/LeaveRequest/leaveRequest")%> #txt
Ue0 f4 startDescription 'This process allow employee to submit their leave request, then the request will be sent to selected approver.
After approver finish his task, the approval result will be sent back to requester' #txt
Ue0 f4 startCustomFields 'cssIcon=si si-advertising-megaphone-2' #txt
Ue0 f4 taskData TaskTriggered.NAM=<%\=ivy.cms.co("/Processes/LeaveRequest/createNewLeaveRequest")%> #txt
Ue0 f4 caseData 'businessCase.attach=true
case.name=<%\=ivy.cms.co("/Processes/LeaveRequest/leaveRequest")%>
customFields.STRING.embedInFrame="false"' #txt
Ue0 f4 wfuser 1 #txt
Ue0 f4 roleExceptionId '>> Ignore Exception' #txt
Ue0 f4 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>startPortalLoginPage.ivp</name>
    </language>
</elementInfo>
' #txt
Ue0 f4 @C|.responsibility Everybody #txt
Ue0 f4 177 209 30 30 -32 18 #rect
Ue0 f19 207 224 344 224 #arcP
Ue0 f5 593 209 30 30 0 15 #rect
Ue0 f7 207 480 344 480 #arcP
Ue0 f8 472 480 593 480 #arcP
Ue0 S31 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language lang="en">
        <name>Start UserGuide page</name>
    </language>
</elementInfo>
' #txt
Ue0 S31 344 458 128 44 -59 -8 #rect
Ue0 f2 456 224 593 224 #arcP
Ue0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>**To start your Axon Ivy Portal experience. This link redirects you to the Portal login page**&#13;
Please use the below credential to login Portal:&#13;
- account: Developer&#13;
- password: Developer</name>
    </language>
</elementInfo>
' #txt
Ue0 f3 96 66 672 76 -330 -34 #rect
Ue0 f6 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>**Portal user example guide**&#13;
In this User Example Guide, you will find some demo processes available in the Axon Ivy Portal.&#13;
Just click on the corresponding link below to get more information.</name>
    </language>
</elementInfo>
' #txt
Ue0 f6 96 338 528 60 -259 -26 #rect
Ue0 f9 432 142 207 224 #arcP
Ue0 f10 360 398 204 472 #arcP
>Proto Ue0 .type com.axonivy.portal.userexamples.UserExampleGuideData #txt
>Proto Ue0 .processKind NORMAL #txt
>Proto Ue0 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <swimlaneLabel>User example guide</swimlaneLabel>
    </language>
    <swimlaneOrientation>false</swimlaneOrientation>
    <swimlaneSize>544</swimlaneSize>
    <swimlaneColor gradient="false">-1</swimlaneColor>
    <swimlaneType>POOL</swimlaneType>
    <swimlaneSpaceBefore>32</swimlaneSpaceBefore>
</elementInfo>
' #txt
>Proto Ue0 0 0 32 24 18 0 #rect
>Proto Ue0 @|BIcon #fIcon
Bk1 g0 51 243 26 26 0 5 #rect
Bk1 g1 691 243 26 26 0 5 #rect
Bk1 f1 actionTable 'out=in;
' #txt
Bk1 f1 actionCode ivy.session.logoutSessionUser(); #txt
Bk1 f1 security system #txt
Bk1 f1 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Logout SessionUser</name>
    </language>
</elementInfo>
' #txt
Bk1 f1 168 234 128 44 -57 -8 #rect
Bk1 f2 77 256 168 256 #arcP
Bk1 f4 actionTable 'out=in1;
' #txt
Bk1 f4 caseData 'case.name=Welcome to Portal' #txt
Bk1 f4 taskData 'TaskA.NAM=Welcome to Portal
TaskA.SKIP_TASK_LIST=true' #txt
Bk1 f4 585 241 30 30 0 16 #rect
Bk1 f0 615 256 691 256 #arcP
Bk1 f3 296 256 585 256 #arcP
>Proto Bk0 -8 -8 16 16 16 26 #rect
Bk3 f3 dialogId com.axonivy.portal.userexamples.ExampleHomePage #txt
Bk3 f3 startMethod start() #txt
Bk3 f3 requestActionDecl '<> param;' #txt
Bk3 f3 responseMappingAction 'out=in;
' #txt
Bk3 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>ExampleHomePage</name>
    </language>
</elementInfo>
' #txt
Bk3 f3 208 138 128 44 -55 -8 #rect
Bk3 g0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language lang="en">
        <name>in 1</name>
    </language>
</elementInfo>
' #txt
Bk3 g0 51 147 26 26 0 5 #rect
Bk3 f0 77 160 208 160 #arcP
Bk3 g1 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language lang="en">
        <name>out 1</name>
    </language>
</elementInfo>
' #txt
Bk3 g1 451 147 26 26 0 5 #rect
Bk3 f1 336 160 451 160 #arcP
>Proto Bk2 0 0 32 24 18 0 #rect
>Proto Bk2 @|BpmnUserTaskIcon #fIcon
Ue0 f4 mainOut f19 tail #connect
Ue0 f19 head S20 g0 #connect
Ue0 f8 head f1 mainIn #connect
Ue0 f0 mainOut f7 tail #connect
Ue0 f7 head S31 g0 #connect
Ue0 S31 g1 f8 tail #connect
Ue0 S20 g1 f2 tail #connect
Ue0 f2 head f5 mainIn #connect
Ue0 f3 ao f9 tail #connect
Ue0 f9 head f4 @CG|ai #connect
Ue0 f6 ao f10 tail #connect
Ue0 f10 head f0 @CG|ai #connect
Bk1 g0 m f2 tail #connect
Bk1 f2 head f1 mainIn #connect
Bk1 f4 out f0 tail #connect
Bk1 f0 head g1 m #connect
Bk1 f1 mainOut f3 tail #connect
Bk1 f3 head f4 in #connect
Bk1 0 0 784 504 0 #ivRect
Bk3 g0 m f0 tail #connect
Bk3 f0 head f3 mainIn #connect
Bk3 f1 head g1 m #connect
Bk3 f3 mainOut f1 tail #connect
Bk3 0 0 528 336 0 #ivRect
