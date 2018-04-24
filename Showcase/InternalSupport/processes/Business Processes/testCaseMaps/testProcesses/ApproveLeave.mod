[Ivy]
15C7B30FB93C827E 3.20 #module
>Proto >Proto Collection #zClass
Ae0 ApproveLeave Big #zClass
Ae0 B #cInfo
Ae0 #process
Ae0 @TextInP .resExport .resExport #zField
Ae0 @TextInP .type .type #zField
Ae0 @TextInP .processKind .processKind #zField
Ae0 @AnnotationInP-0n ai ai #zField
Ae0 @MessageFlowInP-0n messageIn messageIn #zField
Ae0 @MessageFlowOutP-0n messageOut messageOut #zField
Ae0 @TextInP .xml .xml #zField
Ae0 @TextInP .responsibility .responsibility #zField
Ae0 @StartRequest f0 '' #zField
Ae0 @EndTask f1 '' #zField
Ae0 @RichDialog f3 '' #zField
Ae0 @PushWFArc f4 '' #zField
Ae0 @PushWFArc f2 '' #zField
Ae0 @StartRequest f5 '' #zField
Ae0 @Trigger f6 '' #zField
Ae0 @PushWFArc f7 '' #zField
Ae0 @StartRequest f8 '' #zField
Ae0 @EndTask f9 '' #zField
Ae0 @EndTask f11 '' #zField
Ae0 @StartRequest f13 '' #zField
Ae0 @EndTask f14 '' #zField
Ae0 @Trigger f16 '' #zField
Ae0 @PushWFArc f12 '' #zField
Ae0 @TaskSwitchSimple f15 '' #zField
Ae0 @PushWFArc f19 '' #zField
Ae0 @TaskSwitch f21 '' #zField
Ae0 @PushWFArc f23 '' #zField
Ae0 @PushWFArc f20 '' #zField
Ae0 @PushWFArc f10 '' #zField
Ae0 @CallSub f22 '' #zField
Ae0 @TkArc f24 '' #zField
Ae0 @CallSub f25 '' #zField
Ae0 @PushWFArc f26 '' #zField
Ae0 @PushWFArc f17 '' #zField
Ae0 @StartRequest f27 '' #zField
Ae0 @Trigger f29 '' #zField
Ae0 @EndTask f30 '' #zField
Ae0 @PushWFArc f31 '' #zField
Ae0 @PushWFArc f28 '' #zField
Ae0 @StartRequest f32 '' #zField
Ae0 @TaskSwitchSimple f33 '' #zField
Ae0 @EndTask f35 '' #zField
Ae0 @PushWFArc f36 '' #zField
Ae0 @CallSub f37 '' #zField
Ae0 @PushWFArc f38 '' #zField
Ae0 @TkArc f34 '' #zField
Ae0 @CallSub f39 '' #zField
Ae0 @PushWFArc f40 '' #zField
Ae0 @TkArc f18 '' #zField
>Proto Ae0 Ae0 ApproveLeave #zField
Ae0 f0 outLink start.ivp #txt
Ae0 f0 type internaltest.Data #txt
Ae0 f0 inParamDecl '<> param;' #txt
Ae0 f0 actionDecl 'internaltest.Data out;
' #txt
Ae0 f0 guid 15C7B30FB98ED618 #txt
Ae0 f0 requestEnabled true #txt
Ae0 f0 triggerEnabled false #txt
Ae0 f0 callSignature start() #txt
Ae0 f0 persist false #txt
Ae0 f0 taskData 'TaskTriggered.ROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.EXPRI=2
TaskTriggered.TYPE=0
TaskTriggered.PRI=2
TaskTriggered.EXROL=Everybody
TaskTriggered.NAM=Approve Leave Request' #txt
Ae0 f0 caseData businessCase.attach=true #txt
Ae0 f0 showInStartList 1 #txt
Ae0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start.ivp</name>
        <nameStyle>9,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ae0 f0 @C|.responsibility Everybody #txt
Ae0 f0 81 49 30 30 -21 17 #rect
Ae0 f0 @|StartRequestIcon #fIcon
Ae0 f1 type internaltest.Data #txt
Ae0 f1 337 49 30 30 0 15 #rect
Ae0 f1 @|EndIcon #fIcon
Ae0 f3 targetWindow NEW #txt
Ae0 f3 targetDisplay TOP #txt
Ae0 f3 richDialogId internaltest.ui.approveLeave #txt
Ae0 f3 startMethod start(String,Date,Date,Boolean,String,internalPortal.ProcessStatus) #txt
Ae0 f3 type internaltest.Data #txt
Ae0 f3 requestActionDecl '<String Mitarbeiter, Date Von, Date Bis, Boolean beantragt, String Vertretung, internalPortal.ProcessStatus processStatus> param;' #txt
Ae0 f3 responseActionDecl 'internaltest.Data out;
' #txt
Ae0 f3 responseMappingAction 'out=in;
' #txt
Ae0 f3 isAsynch false #txt
Ae0 f3 isInnerRd false #txt
Ae0 f3 userContext '* ' #txt
Ae0 f3 168 42 112 44 0 -8 #rect
Ae0 f3 @|RichDialogIcon #fIcon
Ae0 f4 expr out #txt
Ae0 f4 111 64 168 64 #arcP
Ae0 f2 expr out #txt
Ae0 f2 280 64 337 64 #arcP
Ae0 f5 outLink repairComputer.ivp #txt
Ae0 f5 type internaltest.Data #txt
Ae0 f5 inParamDecl '<> param;' #txt
Ae0 f5 actionDecl 'internaltest.Data out;
' #txt
Ae0 f5 guid 015FA406DB74BB32 #txt
Ae0 f5 requestEnabled true #txt
Ae0 f5 triggerEnabled false #txt
Ae0 f5 callSignature repairComputer() #txt
Ae0 f5 persist false #txt
Ae0 f5 startName 'Repair Computer' #txt
Ae0 f5 taskData 'TaskTriggered.ROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.EXPRI=2
TaskTriggered.TYPE=0
TaskTriggered.PRI=2
TaskTriggered.EXROL=Everybody' #txt
Ae0 f5 caseData 'case.name=Repair Computer
businessCase.attach=true' #txt
Ae0 f5 showInStartList 1 #txt
Ae0 f5 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>repairComputer.ivp</name>
        <nameStyle>18,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ae0 f5 @C|.responsibility Everybody #txt
Ae0 f5 81 177 30 30 -52 17 #rect
Ae0 f5 @|StartRequestIcon #fIcon
Ae0 f6 type internaltest.Data #txt
Ae0 f6 processCall 'Business Processes/testCaseMaps/testProcesses/ApproveLeave:fixHardware()' #txt
Ae0 f6 doCall true #txt
Ae0 f6 requestActionDecl '<> param;
' #txt
Ae0 f6 responseActionDecl 'internaltest.Data out;
' #txt
Ae0 f6 responseMappingAction 'out=in;
' #txt
Ae0 f6 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>ApproveLeave</name>
    </language>
</elementInfo>
' #txt
Ae0 f6 192 170 112 44 -38 -8 #rect
Ae0 f6 @|TriggerIcon #fIcon
Ae0 f7 expr out #txt
Ae0 f7 111 192 192 192 #arcP
Ae0 f8 outLink fixHardware.ivp #txt
Ae0 f8 type internaltest.Data #txt
Ae0 f8 inParamDecl '<> param;' #txt
Ae0 f8 actionDecl 'internaltest.Data out;
' #txt
Ae0 f8 guid 15FA4081B8873731 #txt
Ae0 f8 requestEnabled false #txt
Ae0 f8 triggerEnabled true #txt
Ae0 f8 callSignature fixHardware() #txt
Ae0 f8 persist false #txt
Ae0 f8 startName 'Fix Hardware' #txt
Ae0 f8 taskData 'TaskTriggered.ROL=SYSTEM
TaskTriggered.EXTYPE=0
TaskTriggered.EXPRI=2
TaskTriggered.TYPE=0
TaskTriggered.PRI=2
TaskTriggered.EXROL=Everybody
TaskTriggered.NAM=Fix Hardware' #txt
Ae0 f8 caseData 'case.name=Fix Hardware
businessCase.attach=true' #txt
Ae0 f8 showInStartList 1 #txt
Ae0 f8 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Fix Hardware</name>
    </language>
</elementInfo>
' #txt
Ae0 f8 @C|.responsibility Everybody #txt
Ae0 f8 81 337 30 30 -36 17 #rect
Ae0 f8 @|StartRequestIcon #fIcon
Ae0 f9 type internaltest.Data #txt
Ae0 f9 673 177 30 30 0 15 #rect
Ae0 f9 @|EndIcon #fIcon
Ae0 f11 type internaltest.Data #txt
Ae0 f11 537 337 30 30 0 15 #rect
Ae0 f11 @|EndIcon #fIcon
Ae0 f13 outLink installOperationSystem.ivp #txt
Ae0 f13 type internaltest.Data #txt
Ae0 f13 inParamDecl '<> param;' #txt
Ae0 f13 actionDecl 'internaltest.Data out;
' #txt
Ae0 f13 guid 15FA4121BA547739 #txt
Ae0 f13 requestEnabled false #txt
Ae0 f13 triggerEnabled true #txt
Ae0 f13 callSignature installOperationSystem() #txt
Ae0 f13 persist false #txt
Ae0 f13 startName 'Install Operation System' #txt
Ae0 f13 taskData 'TaskTriggered.ROL=SYSTEM
TaskTriggered.EXTYPE=0
TaskTriggered.EXPRI=2
TaskTriggered.TYPE=0
TaskTriggered.PRI=2
TaskTriggered.EXROL=Everybody' #txt
Ae0 f13 caseData 'case.name=Install Operation System
businessCase.attach=true' #txt
Ae0 f13 showInStartList 1 #txt
Ae0 f13 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Install Operation System</name>
        <nameStyle>24
</nameStyle>
    </language>
</elementInfo>
' #txt
Ae0 f13 @C|.responsibility Everybody #txt
Ae0 f13 73 449 30 30 -67 17 #rect
Ae0 f13 @|StartRequestIcon #fIcon
Ae0 f14 type internaltest.Data #txt
Ae0 f14 521 449 30 30 0 15 #rect
Ae0 f14 @|EndIcon #fIcon
Ae0 f16 type internaltest.Data #txt
Ae0 f16 processCall 'Business Processes/testCaseMaps/testProcesses/ApproveLeave:installOperationSystem()' #txt
Ae0 f16 doCall true #txt
Ae0 f16 requestActionDecl '<> param;
' #txt
Ae0 f16 responseActionDecl 'internaltest.Data out;
' #txt
Ae0 f16 responseMappingAction 'out=in;
' #txt
Ae0 f16 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>ApproveLeave</name>
    </language>
</elementInfo>
' #txt
Ae0 f16 328 330 112 44 -38 -8 #rect
Ae0 f16 @|TriggerIcon #fIcon
Ae0 f12 expr out #txt
Ae0 f12 440 352 537 352 #arcP
Ae0 f15 actionDecl 'internaltest.Data out;
' #txt
Ae0 f15 actionTable 'out=in1;
' #txt
Ae0 f15 outTypes "internaltest.Data" #txt
Ae0 f15 outLinks "TaskA.ivp" #txt
Ae0 f15 taskData 'TaskA.EXPRI=2
TaskA.EXROL=Everybody
TaskA.EXTYPE=0
TaskA.NAM=Install OperationSystem
TaskA.PRI=2
TaskA.ROL=Everybody
TaskA.SKIP_TASK_LIST=false
TaskA.TYPE=0' #txt
Ae0 f15 type internaltest.Data #txt
Ae0 f15 template "" #txt
Ae0 f15 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Install</name>
        <nameStyle>7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ae0 f15 353 449 30 30 -16 17 #rect
Ae0 f15 @|TaskSwitchSimpleIcon #fIcon
Ae0 f19 expr data #txt
Ae0 f19 outCond ivp=="TaskA.ivp" #txt
Ae0 f19 383 464 521 464 #arcP
Ae0 f21 actionDecl 'internaltest.Data out;
' #txt
Ae0 f21 actionTable 'out=in1;
' #txt
Ae0 f21 outTypes "internaltest.Data","internaltest.Data" #txt
Ae0 f21 outLinks "TaskA.ivp","TaskB.ivp" #txt
Ae0 f21 taskData 'TaskA.DESC=Report and hide case
TaskA.EXPRI=2
TaskA.EXROL=Everybody
TaskA.EXTYPE=0
TaskA.NAM=Report and hide case
TaskA.PRI=2
TaskA.ROL=AXONIVY_PORTAL_ADMIN
TaskA.SKIP_TASK_LIST=false
TaskA.TYPE=0
TaskB.DESC=Report but don''t hide case
TaskB.EXPRI=2
TaskB.EXROL=Everybody
TaskB.EXTYPE=0
TaskB.NAM=Report
TaskB.PRI=2
TaskB.ROL=CREATOR
TaskB.SKIP_TASK_LIST=false
TaskB.TYPE=0' #txt
Ae0 f21 type internaltest.Data #txt
Ae0 f21 template "" #txt
Ae0 f21 416 176 32 32 0 16 #rect
Ae0 f21 @|TaskSwitchIcon #fIcon
Ae0 f23 expr data #txt
Ae0 f23 outCond ivp=="TaskB.ivp" #txt
Ae0 f23 432 208 688 207 #arcP
Ae0 f23 1 432 256 #addKink
Ae0 f23 2 688 256 #addKink
Ae0 f23 1 0.5013020833333334 0 0 #arcLabel
Ae0 f20 expr data #txt
Ae0 f20 outCond ivp=="TaskA.ivp" #txt
Ae0 f20 448 192 496 192 #arcP
Ae0 f10 expr out #txt
Ae0 f10 608 192 673 192 #arcP
Ae0 f22 type internaltest.Data #txt
Ae0 f22 processCall 'Functional Processes/HideSystemCase:call(Boolean)' #txt
Ae0 f22 doCall true #txt
Ae0 f22 requestActionDecl '<java.lang.Boolean hideBusinessCase> param;
' #txt
Ae0 f22 requestMappingAction 'param.hideBusinessCase=true;
' #txt
Ae0 f22 responseActionDecl 'internaltest.Data out;
' #txt
Ae0 f22 responseMappingAction 'out=in;
' #txt
Ae0 f22 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>HideSystemCase</name>
        <nameStyle>14,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ae0 f22 496 170 112 44 -48 -8 #rect
Ae0 f22 @|CallSubIcon #fIcon
Ae0 f24 expr out #txt
Ae0 f24 type internaltest.Data #txt
Ae0 f24 var in1 #txt
Ae0 f24 304 192 416 192 #arcP
Ae0 f25 type internaltest.Data #txt
Ae0 f25 processCall 'Functional Processes/HideSystemCase:call(Boolean)' #txt
Ae0 f25 doCall true #txt
Ae0 f25 requestActionDecl '<java.lang.Boolean hideBusinessCase> param;
' #txt
Ae0 f25 requestMappingAction 'param.hideBusinessCase=false;
' #txt
Ae0 f25 responseActionDecl 'internaltest.Data out;
' #txt
Ae0 f25 responseMappingAction 'out=in;
' #txt
Ae0 f25 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>HideSystemCase</name>
        <nameStyle>14,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ae0 f25 168 330 112 44 -48 -8 #rect
Ae0 f25 @|CallSubIcon #fIcon
Ae0 f26 expr out #txt
Ae0 f26 111 352 168 352 #arcP
Ae0 f17 expr out #txt
Ae0 f17 280 352 328 352 #arcP
Ae0 f27 outLink Appraisal.ivp #txt
Ae0 f27 type internaltest.Data #txt
Ae0 f27 inParamDecl '<> param;' #txt
Ae0 f27 actionDecl 'internaltest.Data out;
' #txt
Ae0 f27 guid 161735CC07C2F433 #txt
Ae0 f27 requestEnabled true #txt
Ae0 f27 triggerEnabled false #txt
Ae0 f27 callSignature Appraisal() #txt
Ae0 f27 persist false #txt
Ae0 f27 startName Appraisal #txt
Ae0 f27 taskData 'TaskTriggered.ROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.EXPRI=2
TaskTriggered.TYPE=0
TaskTriggered.PRI=2
TaskTriggered.EXROL=Everybody' #txt
Ae0 f27 caseData businessCase.attach=true #txt
Ae0 f27 showInStartList 1 #txt
Ae0 f27 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Appraisal.ivp</name>
    </language>
</elementInfo>
' #txt
Ae0 f27 @C|.responsibility Everybody #txt
Ae0 f27 81 545 30 30 -35 17 #rect
Ae0 f27 @|StartRequestIcon #fIcon
Ae0 f29 type internaltest.Data #txt
Ae0 f29 processCall 'Business Processes/testCaseMaps/testProcesses/ApproveLeave:RequestForm()' #txt
Ae0 f29 doCall true #txt
Ae0 f29 requestActionDecl '<> param;
' #txt
Ae0 f29 responseActionDecl 'internaltest.Data out;
' #txt
Ae0 f29 responseMappingAction 'out=in;
' #txt
Ae0 f29 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Request form</name>
        <nameStyle>12,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ae0 f29 288 538 112 44 -37 -8 #rect
Ae0 f29 @|TriggerIcon #fIcon
Ae0 f30 type internaltest.Data #txt
Ae0 f30 561 545 30 30 0 15 #rect
Ae0 f30 @|EndIcon #fIcon
Ae0 f31 expr out #txt
Ae0 f31 400 560 561 560 #arcP
Ae0 f28 expr out #txt
Ae0 f28 111 560 288 560 #arcP
Ae0 f32 outLink RequestForm.ivp #txt
Ae0 f32 type internaltest.Data #txt
Ae0 f32 inParamDecl '<> param;' #txt
Ae0 f32 actionDecl 'internaltest.Data out;
' #txt
Ae0 f32 guid 161735EA6AA1E874 #txt
Ae0 f32 requestEnabled false #txt
Ae0 f32 triggerEnabled true #txt
Ae0 f32 callSignature RequestForm() #txt
Ae0 f32 persist false #txt
Ae0 f32 taskData 'TaskTriggered.ROL=SYSTEM
TaskTriggered.EXTYPE=0
TaskTriggered.EXPRI=2
TaskTriggered.TYPE=0
TaskTriggered.PRI=2
TaskTriggered.EXROL=Everybody' #txt
Ae0 f32 caseData businessCase.attach=true #txt
Ae0 f32 showInStartList 1 #txt
Ae0 f32 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>RequestForm()</name>
    </language>
</elementInfo>
' #txt
Ae0 f32 @C|.responsibility Everybody #txt
Ae0 f32 81 625 30 30 -42 17 #rect
Ae0 f32 @|StartRequestIcon #fIcon
Ae0 f33 actionDecl 'internaltest.Data out;
' #txt
Ae0 f33 actionTable 'out=in1;
' #txt
Ae0 f33 outTypes "internaltest.Data" #txt
Ae0 f33 outLinks "TaskA.ivp" #txt
Ae0 f33 taskData 'TaskA.CATEGORY=RequestForm
TaskA.EXPRI=2
TaskA.EXROL=Everybody
TaskA.EXTYPE=0
TaskA.NAM=Request form
TaskA.PRI=2
TaskA.ROL=Everybody
TaskA.SKIP_TASK_LIST=false
TaskA.TYPE=0' #txt
Ae0 f33 type internaltest.Data #txt
Ae0 f33 template "" #txt
Ae0 f33 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Request form</name>
        <nameStyle>12,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ae0 f33 337 625 30 30 -37 17 #rect
Ae0 f33 @|TaskSwitchSimpleIcon #fIcon
Ae0 f35 type internaltest.Data #txt
Ae0 f35 449 625 30 30 0 15 #rect
Ae0 f35 @|EndIcon #fIcon
Ae0 f36 expr data #txt
Ae0 f36 outCond ivp=="TaskA.ivp" #txt
Ae0 f36 367 640 449 640 #arcP
Ae0 f37 type internaltest.Data #txt
Ae0 f37 processCall 'Functional Processes/HideSystemCase:call(Boolean)' #txt
Ae0 f37 doCall true #txt
Ae0 f37 requestActionDecl '<java.lang.Boolean hideBusinessCase> param;
' #txt
Ae0 f37 requestMappingAction 'param.hideBusinessCase=true;
' #txt
Ae0 f37 responseActionDecl 'internaltest.Data out;
' #txt
Ae0 f37 responseMappingAction 'out=in;
' #txt
Ae0 f37 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>HideSystemCase</name>
    </language>
</elementInfo>
' #txt
Ae0 f37 168 618 112 44 -48 -8 #rect
Ae0 f37 @|CallSubIcon #fIcon
Ae0 f38 expr out #txt
Ae0 f38 111 640 168 640 #arcP
Ae0 f34 expr out #txt
Ae0 f34 type internaltest.Data #txt
Ae0 f34 var in1 #txt
Ae0 f34 280 640 337 640 #arcP
Ae0 f39 type internaltest.Data #txt
Ae0 f39 processCall 'Functional Processes/HideSystemCase:call(Boolean)' #txt
Ae0 f39 doCall true #txt
Ae0 f39 requestActionDecl '<java.lang.Boolean hideBusinessCase> param;
' #txt
Ae0 f39 requestMappingAction 'param.hideBusinessCase=false;
' #txt
Ae0 f39 responseActionDecl 'internaltest.Data out;
' #txt
Ae0 f39 responseMappingAction 'out=in;
' #txt
Ae0 f39 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>HideSystemCase</name>
    </language>
</elementInfo>
' #txt
Ae0 f39 184 442 112 44 -48 -8 #rect
Ae0 f39 @|CallSubIcon #fIcon
Ae0 f40 expr out #txt
Ae0 f40 103 464 184 464 #arcP
Ae0 f18 expr out #txt
Ae0 f18 type internaltest.Data #txt
Ae0 f18 var in1 #txt
Ae0 f18 296 464 353 464 #arcP
>Proto Ae0 .type internaltest.Data #txt
>Proto Ae0 .processKind NORMAL #txt
>Proto Ae0 0 0 32 24 18 0 #rect
>Proto Ae0 @|BIcon #fIcon
Ae0 f0 mainOut f4 tail #connect
Ae0 f4 head f3 mainIn #connect
Ae0 f3 mainOut f2 tail #connect
Ae0 f2 head f1 mainIn #connect
Ae0 f5 mainOut f7 tail #connect
Ae0 f7 head f6 mainIn #connect
Ae0 f16 mainOut f12 tail #connect
Ae0 f12 head f11 mainIn #connect
Ae0 f15 out f19 tail #connect
Ae0 f19 head f14 mainIn #connect
Ae0 f22 mainOut f10 tail #connect
Ae0 f10 head f9 mainIn #connect
Ae0 f21 out f20 tail #connect
Ae0 f20 head f22 mainIn #connect
Ae0 f21 out f23 tail #connect
Ae0 f23 head f9 mainIn #connect
Ae0 f6 mainOut f24 tail #connect
Ae0 f24 head f21 in #connect
Ae0 f8 mainOut f26 tail #connect
Ae0 f26 head f25 mainIn #connect
Ae0 f25 mainOut f17 tail #connect
Ae0 f17 head f16 mainIn #connect
Ae0 f29 mainOut f31 tail #connect
Ae0 f31 head f30 mainIn #connect
Ae0 f27 mainOut f28 tail #connect
Ae0 f28 head f29 mainIn #connect
Ae0 f33 out f36 tail #connect
Ae0 f36 head f35 mainIn #connect
Ae0 f32 mainOut f38 tail #connect
Ae0 f38 head f37 mainIn #connect
Ae0 f37 mainOut f34 tail #connect
Ae0 f34 head f33 in #connect
Ae0 f13 mainOut f40 tail #connect
Ae0 f40 head f39 mainIn #connect
Ae0 f39 mainOut f18 tail #connect
Ae0 f18 head f15 in #connect
