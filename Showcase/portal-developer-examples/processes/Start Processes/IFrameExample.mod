[Ivy]
16E5DB746865BCEC 9.2.0 #module
>Proto >Proto Collection #zClass
Ie0 IFrameExample Big #zClass
Ie0 B #cInfo
Ie0 #process
Ie0 @TextInP .type .type #zField
Ie0 @TextInP .processKind .processKind #zField
Ie0 @TextInP .xml .xml #zField
Ie0 @TextInP .responsibility .responsibility #zField
Ie0 @TaskSwitch f20 '' #zField
Ie0 @EndTask f13 '' #zField
Ie0 @UserDialog f15 '' #zField
Ie0 @StartRequest f12 '' #zField
Ie0 @PushWFArc f24 '' #zField
Ie0 @UserDialog f0 '' #zField
Ie0 @PushWFArc f1 '' #zField
Ie0 @PushWFArc f2 '' #zField
Ie0 @TaskSwitchSimple f3 '' #zField
Ie0 @TkArc f4 '' #zField
Ie0 @UserDialog f6 '' #zField
Ie0 @PushWFArc f7 '' #zField
Ie0 @PushWFArc f5 '' #zField
Ie0 @PushWFArc f19 '' #zField
Ie0 @TkArc f22 '' #zField
Ie0 @UserDialog f21 '' #zField
>Proto Ie0 Ie0 IFrameExample #zField
Ie0 f20 actionTable 'out=in1;
' #txt
Ie0 f20 outLinks "TaskA.ivp","TaskB.ivp" #txt
Ie0 f20 taskData 'TaskB.NAM=Serenity Task Template
TaskB.customFields.STRING.embedInFrame="false"
TaskA.NAM=Approve Investment (Task in IFrame)
TaskA.customFields.STRING.embedInFrame="true"
TaskD.NAM=Deprecated Modena Task Template
TaskD.customFields.STRING.embedInFrame="true"
TaskC.NAM=Deprecated Task Template
TaskC.customFields.STRING.embedInFrame="false"' #txt
Ie0 f20 template "" #txt
Ie0 f20 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name></name>
    </language>
</elementInfo>
' #txt
Ie0 f20 368 80 32 32 -77 276 #rect
Ie0 f20 @|TaskSwitchIcon #fIcon
Ie0 f13 873 177 30 30 0 15 #rect
Ie0 f13 @|EndIcon #fIcon
Ie0 f15 dialogId com.axonivy.portal.developerexamples.testdata.Approve #txt
Ie0 f15 startMethod start(com.axonivy.portal.developerexamples.ExampleIFrameData) #txt
Ie0 f15 requestActionDecl '<com.axonivy.portal.developerexamples.ExampleIFrameData investmentRequest> param;' #txt
Ie0 f15 requestMappingAction 'param.investmentRequest=in;
' #txt
Ie0 f15 responseActionDecl 'investment.manae.Data out;
' #txt
Ie0 f15 responseMappingAction 'out=result.investmentRequest;
' #txt
Ie0 f15 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Approve</name>
    </language>
</elementInfo>
' #txt
Ie0 f15 560 74 112 44 -22 -8 #rect
Ie0 f15 @|UserDialogIcon #fIcon
Ie0 f12 outLink CreateInvestment.ivp #txt
Ie0 f12 inParamDecl '<> param;' #txt
Ie0 f12 requestEnabled true #txt
Ie0 f12 triggerEnabled false #txt
Ie0 f12 callSignature CreateInvestment() #txt
Ie0 f12 persist false #txt
Ie0 f12 startName 'Create Investment (IFrame + Task custom fields)' #txt
Ie0 f12 taskData 'TaskTriggered.NAM=Create Investment
TaskTriggered.customFields.STRING.embedInFrame="true"' #txt
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
Ie0 f24 400 96 560 96 #arcP
Ie0 f24 0 0.421875 0 -11 #arcLabel
Ie0 f0 dialogId com.axonivy.portal.developerexamples.testdata.SimpleTaskDialog #txt
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
Ie0 f2 672 192 873 192 #arcP
Ie0 f2 0 0.45456469770120017 0 0 #arcLabel
Ie0 f3 actionTable 'out=in1;
' #txt
Ie0 f3 taskData 'TaskA.NAM=Review Request (Skip Tasklist in IFrame)
TaskA.SKIP_TASK_LIST=true
TaskA.customFields.STRING.embedInFrame="true"' #txt
Ie0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Review Request</name>
    </language>
</elementInfo>
' #txt
Ie0 f3 737 81 30 30 -44 22 #rect
Ie0 f3 @|TaskSwitchSimpleIcon #fIcon
Ie0 f4 expr out #txt
Ie0 f4 672 96 737 96 #arcP
Ie0 f6 dialogId com.axonivy.portal.developerexamples.testdata.Review #txt
Ie0 f6 startMethod start(com.axonivy.portal.developerexamples.ExampleIFrameData) #txt
Ie0 f6 requestActionDecl '<com.axonivy.portal.developerexamples.ExampleIFrameData investmentRequest> param;' #txt
Ie0 f6 requestMappingAction 'param.investmentRequest=in;
' #txt
Ie0 f6 responseMappingAction 'out=in;
' #txt
Ie0 f6 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Review</name>
    </language>
</elementInfo>
' #txt
Ie0 f6 832 74 112 44 -20 -8 #rect
Ie0 f6 @|UserDialogIcon #fIcon
Ie0 f7 767 96 832 96 #arcP
Ie0 f5 888 118 888 177 #arcP
Ie0 f19 expr out #txt
Ie0 f19 111 96 192 96 #arcP
Ie0 f22 expr out #txt
Ie0 f22 var in1 #txt
Ie0 f22 304 96 368 96 #arcP
Ie0 f21 dialogId com.axonivy.portal.developerexamples.testdata.CreateInvestment #txt
Ie0 f21 startMethod start() #txt
Ie0 f21 requestActionDecl '<> param;' #txt
Ie0 f21 responseActionDecl 'investment.manae.Data out;
' #txt
Ie0 f21 responseMappingAction 'out=result.requestData;
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
>Proto Ie0 .type com.axonivy.portal.developerexamples.ExampleIFrameData #txt
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
Ie0 f15 mainOut f4 tail #connect
Ie0 f4 head f3 in #connect
Ie0 f3 out f7 tail #connect
Ie0 f7 head f6 mainIn #connect
Ie0 f6 mainOut f5 tail #connect
Ie0 f5 head f13 mainIn #connect
