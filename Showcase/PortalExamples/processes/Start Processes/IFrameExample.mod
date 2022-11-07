[Ivy]
16E5DB746865BCEC 7.5.0 #module
>Proto >Proto Collection #zClass
Ie0 IFrameExample Big #zClass
Ie0 B #cInfo
Ie0 #process
Ie0 @TextInP .type .type #zField
Ie0 @TextInP .processKind .processKind #zField
Ie0 @TextInP .xml .xml #zField
Ie0 @TextInP .responsibility .responsibility #zField
Ie0 @TaskSwitch f20 '' #zField
Ie0 @UserDialog f21 '' #zField
Ie0 @EndTask f13 '' #zField
Ie0 @UserDialog f15 '' #zField
Ie0 @StartRequest f12 '' #zField
Ie0 @PushWFArc f24 '' #zField
Ie0 @TkArc f22 '' #zField
Ie0 @PushWFArc f19 '' #zField
Ie0 @UserDialog f0 '' #zField
Ie0 @PushWFArc f1 '' #zField
Ie0 @PushWFArc f2 '' #zField
Ie0 @UserDialog f3 '' #zField
Ie0 @PushWFArc f4 '' #zField
Ie0 @UserDialog f5 '' #zField
Ie0 @PushWFArc f6 '' #zField
Ie0 @PushWFArc f7 '' #zField
Ie0 @PushWFArc f8 '' #zField
Ie0 @StartRequest f9 '' #zField
Ie0 @UserDialog f10 '' #zField
Ie0 @PushWFArc f11 '' #zField
Ie0 @EndRequest f18 '' #zField
Ie0 @PushWFArc f23 '' #zField
Ie0 @TaskSwitchSimple f16 '' #zField
Ie0 @TkArc f17 '' #zField
Ie0 @UserDialog f25 '' #zField
Ie0 @PushWFArc f26 '' #zField
Ie0 @PushWFArc f14 '' #zField
Ie0 @StartRequest f27 '' #zField
Ie0 @EndTask f28 '' #zField
Ie0 @TaskSwitchSimple f30 '' #zField
Ie0 @TkArc f31 '' #zField
Ie0 @UserDialog f32 '' #zField
Ie0 @PushWFArc f33 '' #zField
Ie0 @PushWFArc f29 '' #zField
Ie0 @UserDialog f36 '' #zField
Ie0 @StartRequest f37 '' #zField
Ie0 @PushWFArc f40 '' #zField
Ie0 @EndTask f41 '' #zField
Ie0 @PushWFArc f42 '' #zField
Ie0 @UserDialog f34 '' #zField
Ie0 @StartRequest f35 '' #zField
Ie0 @EndTask f38 '' #zField
Ie0 @PushWFArc f39 '' #zField
Ie0 @PushWFArc f43 '' #zField
>Proto Ie0 Ie0 IFrameExample #zField
Ie0 f20 actionTable 'out=in1;
' #txt
Ie0 f20 outLinks "TaskB.ivp","TaskA.ivp","TaskD.ivp","TaskC.ivp" #txt
Ie0 f20 taskData 'TaskB.NAM=Serenity Task Template
TaskB.customFields.STRING.embedInFrame="false"
TaskA.NAM=Approve Investment (Task in IFrame)
TaskA.customFields.STRING.embedInFrame="true"
TaskD.NAM=Deprecated Modena Task Template
TaskD.customFields.STRING.embedInFrame="true"
TaskC.NAM=Deprecated Task Template
TaskC.customFields.STRING.embedInFrame="false"' #txt
Ie0 f20 template "" #txt
Ie0 f20 368 80 32 32 0 16 #rect
Ie0 f20 @|TaskSwitchIcon #fIcon
Ie0 f21 dialogId ch.ivyteam.ivy.project.portal.examples.testdata.CreateInvestment #txt
Ie0 f21 startMethod start() #txt
Ie0 f21 requestActionDecl '<> param;' #txt
Ie0 f21 responseActionDecl 'investment.manae.Data out;
' #txt
Ie0 f21 responseMappingAction 'out=in;
out.investment=result.investment;
' #txt
Ie0 f21 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Create Investment</name>
    </language>
</elementInfo>
' #txt
Ie0 f21 192 74 112 44 -50 -8 #rect
Ie0 f21 @|UserDialogIcon #fIcon
Ie0 f13 929 81 30 30 0 15 #rect
Ie0 f13 @|EndIcon #fIcon
Ie0 f15 dialogId ch.ivyteam.ivy.project.portal.examples.testdata.Approve #txt
Ie0 f15 startMethod start(ch.ivyteam.ivy.project.portal.examples.Investment) #txt
Ie0 f15 requestActionDecl '<ch.ivyteam.ivy.project.portal.examples.Investment investment> param;' #txt
Ie0 f15 requestMappingAction 'param.investment=in.investment;
' #txt
Ie0 f15 responseActionDecl 'investment.manae.Data out;
' #txt
Ie0 f15 responseMappingAction 'out=in;
out.investment=result.investment;
out.isApproved=result.isApproved;
out.note=result.note;
' #txt
Ie0 f15 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Approve</name>
    </language>
</elementInfo>
' #txt
Ie0 f15 504 74 112 44 -22 -8 #rect
Ie0 f15 @|UserDialogIcon #fIcon
Ie0 f12 outLink CreateInvestment.ivp #txt
Ie0 f12 inParamDecl '<> param;' #txt
Ie0 f12 requestEnabled true #txt
Ie0 f12 triggerEnabled false #txt
Ie0 f12 callSignature CreateInvestment() #txt
Ie0 f12 persist false #txt
Ie0 f12 startName 'Create Investment (IFrame + Task custom fields)' #txt
Ie0 f12 taskData TaskTriggered.customFields.STRING.embedInFrame="true" #txt
Ie0 f12 caseData 'businessCase.attach=true
case.name=Investment Creation (IFrame + Task custom fields)' #txt
Ie0 f12 showInStartList 1 #txt
Ie0 f12 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>CreateInvestment.ivp</name>
    </language>
</elementInfo>
' #txt
Ie0 f12 @C|.responsibility Everybody #txt
Ie0 f12 81 81 30 30 -50 19 #rect
Ie0 f12 @|StartRequestIcon #fIcon
Ie0 f24 expr data #txt
Ie0 f24 outCond ivp=="TaskA.ivp" #txt
Ie0 f24 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Task in IFrame</name>
    </language>
</elementInfo>
' #txt
Ie0 f24 400 96 504 96 #arcP
Ie0 f24 0 0.4807692307692308 0 -13 #arcLabel
Ie0 f22 expr out #txt
Ie0 f22 var in1 #txt
Ie0 f22 304 96 368 96 #arcP
Ie0 f19 expr out #txt
Ie0 f19 111 96 192 96 #arcP
Ie0 f0 dialogId ch.ivyteam.ivy.project.portal.examples.testdata.SimpleTaskDialog #txt
Ie0 f0 startMethod start() #txt
Ie0 f0 requestActionDecl '<> param;' #txt
Ie0 f0 responseMappingAction 'out=in;
' #txt
Ie0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>SimpleTaskDialog</name>
    </language>
</elementInfo>
' #txt
Ie0 f0 560 170 112 44 -47 -8 #rect
Ie0 f0 @|UserDialogIcon #fIcon
Ie0 f1 expr data #txt
Ie0 f1 outCond ivp=="TaskB.ivp" #txt
Ie0 f1 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>TaskTemplate</name>
    </language>
</elementInfo>
' #txt
Ie0 f1 384 112 560 192 #arcP
Ie0 f1 1 384 192 #addKink
Ie0 f1 1 0.4583333333333333 0 -13 #arcLabel
Ie0 f2 672 192 944 111 #arcP
Ie0 f2 1 944 192 #addKink
Ie0 f2 0 0.8381036613865878 0 0 #arcLabel
Ie0 f3 dialogId ch.ivyteam.ivy.project.portal.examples.testdata.DeprecatedTaskTemplateDialog #txt
Ie0 f3 startMethod start() #txt
Ie0 f3 requestActionDecl '<> param;' #txt
Ie0 f3 responseMappingAction 'out=in;
' #txt
Ie0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>DeprecatedTaskTemplateDialog</name>
    </language>
</elementInfo>
' #txt
Ie0 f3 552 266 176 44 -84 -8 #rect
Ie0 f3 @|UserDialogIcon #fIcon
Ie0 f4 expr data #txt
Ie0 f4 outCond ivp=="TaskC.ivp" #txt
Ie0 f4 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>DeprecatedTaskTemplate</name>
    </language>
</elementInfo>
' #txt
Ie0 f4 384 112 552 288 #arcP
Ie0 f4 1 384 288 #addKink
Ie0 f4 1 0.5147058823529411 0 -10 #arcLabel
Ie0 f5 dialogId ch.ivyteam.ivy.project.portal.examples.testdata.ModenaElements #txt
Ie0 f5 startMethod start() #txt
Ie0 f5 requestActionDecl '<> param;' #txt
Ie0 f5 responseMappingAction 'out=in;
' #txt
Ie0 f5 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>ModenaElements</name>
    </language>
</elementInfo>
' #txt
Ie0 f5 552 362 112 44 -45 -8 #rect
Ie0 f5 @|UserDialogIcon #fIcon
Ie0 f6 expr data #txt
Ie0 f6 outCond ivp=="TaskD.ivp" #txt
Ie0 f6 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>DeprecatedModenaTemplate</name>
    </language>
</elementInfo>
' #txt
Ie0 f6 384 112 552 384 #arcP
Ie0 f6 1 384 384 #addKink
Ie0 f6 0 0.9522058823529411 80 0 #arcLabel
Ie0 f7 728 288 944 111 #arcP
Ie0 f7 1 944 288 #addKink
Ie0 f7 1 0.4084507035380983 0 0 #arcLabel
Ie0 f8 664 384 944 111 #arcP
Ie0 f8 1 944 384 #addKink
Ie0 f8 1 0.30952380952380953 0 0 #arcLabel
Ie0 f9 outLink start.ivp #txt
Ie0 f9 inParamDecl '<> param;' #txt
Ie0 f9 requestEnabled true #txt
Ie0 f9 triggerEnabled false #txt
Ie0 f9 callSignature start() #txt
Ie0 f9 startName 'Test IFrame based on app configuration' #txt
Ie0 f9 caseData businessCase.attach=true #txt
Ie0 f9 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start.ivp</name>
    </language>
</elementInfo>
' #txt
Ie0 f9 @C|.responsibility Everybody #txt
Ie0 f9 81 465 30 30 -20 17 #rect
Ie0 f9 @|StartRequestIcon #fIcon
Ie0 f10 dialogId ch.ivyteam.ivy.project.portal.examples.testdata.CreateInvestment #txt
Ie0 f10 startMethod start() #txt
Ie0 f10 requestActionDecl '<> param;' #txt
Ie0 f10 responseActionDecl 'investment.manae.Data out;
' #txt
Ie0 f10 responseMappingAction 'out=in;
out.investment=result.investment;
' #txt
Ie0 f10 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Create Investment</name>
    </language>
</elementInfo>
' #txt
Ie0 f10 192 458 112 44 -50 -8 #rect
Ie0 f10 @|UserDialogIcon #fIcon
Ie0 f11 111 480 192 480 #arcP
Ie0 f18 template "/EndPage.ivc" #txt
Ie0 f18 369 465 30 30 0 15 #rect
Ie0 f18 @|EndRequestIcon #fIcon
Ie0 f23 304 480 369 480 #arcP
Ie0 f16 actionTable 'out=in1;
' #txt
Ie0 f16 taskData 'TaskA.NAM=Review Request (Skip Tasklist In IFrame)
TaskA.SKIP_TASK_LIST=true
TaskA.customFields.STRING.embedInFrame="true"' #txt
Ie0 f16 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Review Request</name>
    </language>
</elementInfo>
' #txt
Ie0 f16 673 81 30 30 -36 19 #rect
Ie0 f16 @|TaskSwitchSimpleIcon #fIcon
Ie0 f17 expr out #txt
Ie0 f17 616 96 673 96 #arcP
Ie0 f25 dialogId ch.ivyteam.ivy.project.portal.examples.testdata.Review #txt
Ie0 f25 startMethod start(ch.ivyteam.ivy.project.portal.examples.Investment,Boolean,String) #txt
Ie0 f25 requestActionDecl '<ch.ivyteam.ivy.project.portal.examples.Investment investment,Boolean isApproved,String note> param;' #txt
Ie0 f25 requestMappingAction 'param.investment=in.investment;
param.isApproved=in.isApproved;
param.note=in.note;
' #txt
Ie0 f25 responseMappingAction 'out=in;
' #txt
Ie0 f25 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Review</name>
    </language>
</elementInfo>
' #txt
Ie0 f25 760 74 112 44 -20 -8 #rect
Ie0 f25 @|UserDialogIcon #fIcon
Ie0 f26 703 96 760 96 #arcP
Ie0 f14 872 96 929 96 #arcP
Ie0 f27 outLink NavigateInIFrameExample.ivp #txt
Ie0 f27 inParamDecl '<> param;' #txt
Ie0 f27 requestEnabled true #txt
Ie0 f27 triggerEnabled false #txt
Ie0 f27 callSignature NavigateInIFrameExample() #txt
Ie0 f27 startName 'Navigate back in the IFrame example' #txt
Ie0 f27 startDescription 'Example of navigation in IFrame' #txt
Ie0 f27 caseData businessCase.attach=true #txt
Ie0 f27 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>NavigateInIFrameExample.ivp</name>
    </language>
</elementInfo>
' #txt
Ie0 f27 @C|.responsibility Everybody #txt
Ie0 f27 81 561 30 30 -24 17 #rect
Ie0 f27 @|StartRequestIcon #fIcon
Ie0 f28 449 561 30 30 0 15 #rect
Ie0 f28 @|EndIcon #fIcon
Ie0 f30 actionTable 'out=in1;
' #txt
Ie0 f30 caseData 'case.description=In case your project has navigation button without finishing a task, e.g Cancel, navigate.\r\n- Home page\: call navigateToPortalHome() from class PortalNavigatorInFrameAPI.\r\n- Previous page\: call navigateToPortalEndPage() from class PortalNavigatorInFrameAPI.\r\n- A specific url\: call navigateToUrl(String url) from class PortalNavigatorInFrameAPI.
case.name=Navigate IFrame example
customFields.STRING.embedInFrame="true"' #txt
Ie0 f30 taskData 'TaskA.NAM=Navigate back in the IFrame example
TaskA.SKIP_TASK_LIST=true' #txt
Ie0 f30 193 561 30 30 0 16 #rect
Ie0 f30 @|TaskSwitchSimpleIcon #fIcon
Ie0 f31 111 576 193 576 #arcP
Ie0 f32 dialogId ch.ivyteam.ivy.project.portal.examples.ResetTaskIFrame #txt
Ie0 f32 startMethod start() #txt
Ie0 f32 requestActionDecl '<> param;' #txt
Ie0 f32 responseMappingAction 'out=in;
' #txt
Ie0 f32 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>ResetTaskIFrame</name>
    </language>
</elementInfo>
' #txt
Ie0 f32 280 554 112 44 -49 -8 #rect
Ie0 f32 @|UserDialogIcon #fIcon
Ie0 f33 223 576 280 576 #arcP
Ie0 f29 392 576 449 576 #arcP
Ie0 f36 dialogId ch.ivyteam.ivy.project.portal.examples.testdata.CreateInvestment #txt
Ie0 f36 startMethod start() #txt
Ie0 f36 requestActionDecl '<> param;' #txt
Ie0 f36 responseActionDecl 'investment.manae.Data out;
' #txt
Ie0 f36 responseMappingAction 'out=in;
out.investment=result.investment;
' #txt
Ie0 f36 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Create Investment</name>
    </language>
</elementInfo>
' #txt
Ie0 f36 208 658 112 44 -50 -8 #rect
Ie0 f36 @|UserDialogIcon #fIcon
Ie0 f37 outLink SampleProcessWithoutAnyCustomFields.ivp #txt
Ie0 f37 inParamDecl '<> param;' #txt
Ie0 f37 requestEnabled true #txt
Ie0 f37 triggerEnabled false #txt
Ie0 f37 callSignature SampleProcessWithoutAnyCustomFields() #txt
Ie0 f37 persist false #txt
Ie0 f37 startName 'Sample process (IFrame without any custom fields)' #txt
Ie0 f37 caseData 'businessCase.attach=true
case.name=Investment Creation (IFrame + Task custom fields)' #txt
Ie0 f37 showInStartList 1 #txt
Ie0 f37 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>SampleProcessWithoutAnyCustomFields.ivp</name>
    </language>
</elementInfo>
' #txt
Ie0 f37 @C|.responsibility Everybody #txt
Ie0 f37 81 665 30 30 -77 30 #rect
Ie0 f37 @|StartRequestIcon #fIcon
Ie0 f40 expr out #txt
Ie0 f40 111 680 208 680 #arcP
Ie0 f41 433 665 30 30 0 15 #rect
Ie0 f41 @|EndIcon #fIcon
Ie0 f42 expr out #txt
Ie0 f42 320 680 433 680 #arcP
Ie0 f34 dialogId ch.ivyteam.ivy.project.portal.examples.testdata.CreateInvestment #txt
Ie0 f34 startMethod start() #txt
Ie0 f34 requestActionDecl '<> param;' #txt
Ie0 f34 responseActionDecl 'investment.manae.Data out;
' #txt
Ie0 f34 responseMappingAction 'out=in;
out.investment=result.investment;
' #txt
Ie0 f34 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Create Investment</name>
    </language>
</elementInfo>
' #txt
Ie0 f34 208 754 112 44 -50 -8 #rect
Ie0 f34 @|UserDialogIcon #fIcon
Ie0 f35 outLink SampleProcessWithCaseCustomField.ivp #txt
Ie0 f35 inParamDecl '<> param;' #txt
Ie0 f35 requestEnabled true #txt
Ie0 f35 triggerEnabled false #txt
Ie0 f35 callSignature SampleProcessWithCaseCustomField() #txt
Ie0 f35 persist false #txt
Ie0 f35 startName 'Sample process (IFrame + Case custom field)' #txt
Ie0 f35 caseData 'businessCase.attach=true
case.name=Sample process (IFrame + Case custom field)
customFields.STRING.embedInFrame="true"' #txt
Ie0 f35 showInStartList 1 #txt
Ie0 f35 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>SampleProcessWithCaseCustomField.ivp</name>
    </language>
</elementInfo>
' #txt
Ie0 f35 @C|.responsibility Everybody #txt
Ie0 f35 81 761 30 30 -75 32 #rect
Ie0 f35 @|StartRequestIcon #fIcon
Ie0 f38 433 761 30 30 0 15 #rect
Ie0 f38 @|EndIcon #fIcon
Ie0 f39 expr out #txt
Ie0 f39 320 776 433 776 #arcP
Ie0 f43 expr out #txt
Ie0 f43 111 776 208 776 #arcP
>Proto Ie0 .type ch.ivyteam.ivy.project.portal.examples.IFrameExampleData #txt
>Proto Ie0 .processKind NORMAL #txt
>Proto Ie0 0 0 32 24 18 0 #rect
>Proto Ie0 @|BIcon #fIcon
Ie0 f12 mainOut f19 tail #connect
Ie0 f19 head f21 mainIn #connect
Ie0 f21 mainOut f22 tail #connect
Ie0 f22 head f20 in #connect
Ie0 f20 out f24 tail #connect
Ie0 f24 head f15 mainIn #connect
Ie0 f20 out f1 tail #connect
Ie0 f1 head f0 mainIn #connect
Ie0 f0 mainOut f2 tail #connect
Ie0 f2 head f13 mainIn #connect
Ie0 f20 out f4 tail #connect
Ie0 f4 head f3 mainIn #connect
Ie0 f20 out f6 tail #connect
Ie0 f6 head f5 mainIn #connect
Ie0 f3 mainOut f7 tail #connect
Ie0 f7 head f13 mainIn #connect
Ie0 f5 mainOut f8 tail #connect
Ie0 f8 head f13 mainIn #connect
Ie0 f9 mainOut f11 tail #connect
Ie0 f11 head f10 mainIn #connect
Ie0 f10 mainOut f23 tail #connect
Ie0 f23 head f18 mainIn #connect
Ie0 f15 mainOut f17 tail #connect
Ie0 f17 head f16 in #connect
Ie0 f16 out f26 tail #connect
Ie0 f26 head f25 mainIn #connect
Ie0 f25 mainOut f14 tail #connect
Ie0 f14 head f13 mainIn #connect
Ie0 f27 mainOut f31 tail #connect
Ie0 f31 head f30 in #connect
Ie0 f30 out f33 tail #connect
Ie0 f33 head f32 mainIn #connect
Ie0 f32 mainOut f29 tail #connect
Ie0 f29 head f28 mainIn #connect
Ie0 f37 mainOut f40 tail #connect
Ie0 f40 head f36 mainIn #connect
Ie0 f36 mainOut f42 tail #connect
Ie0 f42 head f41 mainIn #connect
Ie0 f35 mainOut f43 tail #connect
Ie0 f43 head f34 mainIn #connect
Ie0 f34 mainOut f39 tail #connect
Ie0 f39 head f38 mainIn #connect
