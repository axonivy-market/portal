[Ivy]
15C7B30FB93C827E 7.5.0 #module
>Proto >Proto Collection #zClass
Ae0 ApproveLeave Big #zClass
Ae0 B #cInfo
Ae0 #process
Ae0 @TextInP .type .type #zField
Ae0 @TextInP .processKind .processKind #zField
Ae0 @AnnotationInP-0n ai ai #zField
Ae0 @MessageFlowInP-0n messageIn messageIn #zField
Ae0 @MessageFlowOutP-0n messageOut messageOut #zField
Ae0 @TextInP .xml .xml #zField
Ae0 @TextInP .responsibility .responsibility #zField
Ae0 @StartRequest f0 '' #zField
Ae0 @EndTask f1 '' #zField
Ae0 @UserDialog f3 '' #zField
Ae0 @PushWFArc f4 '' #zField
Ae0 @PushWFArc f2 '' #zField
Ae0 @StartRequest f8 '' #zField
Ae0 @EndTask f11 '' #zField
Ae0 @StartRequest f13 '' #zField
Ae0 @EndTask f14 '' #zField
Ae0 @Trigger f16 '' #zField
Ae0 @PushWFArc f12 '' #zField
Ae0 @TaskSwitchSimple f15 '' #zField
Ae0 @PushWFArc f19 '' #zField
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
Ae0 @StartRequest f41 '' #zField
Ae0 @Alternative f46 '' #zField
Ae0 @EndTask f48 '' #zField
Ae0 @PushWFArc f49 '' #zField
Ae0 @Alternative f50 '' #zField
Ae0 @PushWFArc f51 '' #zField
Ae0 @Trigger f45 '' #zField
Ae0 @PushWFArc f42 '' #zField
Ae0 @StartRequest f43 '' #zField
Ae0 @TaskSwitchSimple f47 '' #zField
Ae0 @EndTask f54 '' #zField
Ae0 @PushWFArc f55 '' #zField
Ae0 @GridStep f52 '' #zField
Ae0 @PushWFArc f56 '' #zField
Ae0 @PushWFArc f44 '' #zField
Ae0 @PushWFArc f57 '' #zField
Ae0 @GridStep f58 '' #zField
Ae0 @PushWFArc f59 '' #zField
Ae0 @TkArc f53 '' #zField
>Proto Ae0 Ae0 ApproveLeave #zField
Ae0 f0 outLink start.ivp #txt
Ae0 f0 inParamDecl '<> param;' #txt
Ae0 f0 requestEnabled true #txt
Ae0 f0 triggerEnabled false #txt
Ae0 f0 callSignature start() #txt
Ae0 f0 persist false #txt
Ae0 f0 taskData 'TaskTriggered.EXPRI=2
TaskTriggered.EXROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.NAM=Approve Leave Request
TaskTriggered.PRI=2
TaskTriggered.ROL=Everybody
TaskTriggered.TYPE=0' #txt
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
Ae0 f1 337 49 30 30 0 15 #rect
Ae0 f1 @|EndIcon #fIcon
Ae0 f3 dialogId internaltest.ui.approveLeave #txt
Ae0 f3 startMethod start(String,Date,Date,Boolean,String,internalPortal.ProcessStatus) #txt
Ae0 f3 requestActionDecl '<String Mitarbeiter,Date Von,Date Bis,Boolean beantragt,String Vertretung,internalPortal.ProcessStatus processStatus> param;' #txt
Ae0 f3 responseActionDecl 'internaltest.Data out;
' #txt
Ae0 f3 responseMappingAction 'out=in;
' #txt
Ae0 f3 168 42 112 44 0 -8 #rect
Ae0 f3 @|UserDialogIcon #fIcon
Ae0 f4 expr out #txt
Ae0 f4 111 64 168 64 #arcP
Ae0 f2 expr out #txt
Ae0 f2 280 64 337 64 #arcP
Ae0 f8 outLink fixHardware.ivp #txt
Ae0 f8 inParamDecl '<> param;' #txt
Ae0 f8 requestEnabled false #txt
Ae0 f8 triggerEnabled true #txt
Ae0 f8 callSignature fixHardware() #txt
Ae0 f8 persist false #txt
Ae0 f8 startName 'Fix Hardware' #txt
Ae0 f8 taskData 'TaskTriggered.EXPRI=2
TaskTriggered.EXROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.NAM=Fix Hardware
TaskTriggered.PRI=2
TaskTriggered.ROL=SYSTEM
TaskTriggered.TYPE=0' #txt
Ae0 f8 caseData 'businessCase.attach=true
case.name=Fix Hardware' #txt
Ae0 f8 showInStartList 1 #txt
Ae0 f8 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Fix Hardware</name>
    </language>
</elementInfo>
' #txt
Ae0 f8 @C|.responsibility Everybody #txt
Ae0 f8 73 153 30 30 -36 17 #rect
Ae0 f8 @|StartRequestIcon #fIcon
Ae0 f11 529 153 30 30 0 15 #rect
Ae0 f11 @|EndIcon #fIcon
Ae0 f13 outLink installOperationSystem.ivp #txt
Ae0 f13 inParamDecl '<> param;' #txt
Ae0 f13 requestEnabled false #txt
Ae0 f13 triggerEnabled true #txt
Ae0 f13 callSignature installOperationSystem() #txt
Ae0 f13 persist false #txt
Ae0 f13 startName 'Install Operation System' #txt
Ae0 f13 taskData 'TaskTriggered.EXPRI=2
TaskTriggered.EXROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.PRI=2
TaskTriggered.ROL=SYSTEM
TaskTriggered.TYPE=0' #txt
Ae0 f13 caseData 'businessCase.attach=true
case.name=Install Operation System' #txt
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
Ae0 f13 65 265 30 30 -67 17 #rect
Ae0 f13 @|StartRequestIcon #fIcon
Ae0 f14 513 265 30 30 0 15 #rect
Ae0 f14 @|EndIcon #fIcon
Ae0 f16 processCall 'Business Processes/testCaseMaps/testProcesses/ApproveLeave:installOperationSystem()' #txt
Ae0 f16 requestActionDecl '<> param;' #txt
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
Ae0 f16 320 146 112 44 -38 -8 #rect
Ae0 f16 @|TriggerIcon #fIcon
Ae0 f12 expr out #txt
Ae0 f12 432 168 529 168 #arcP
Ae0 f15 actionTable 'out=in1;
' #txt
Ae0 f15 outLinks "TaskA.ivp" #txt
Ae0 f15 taskData 'TaskA.EXPRI=2
TaskA.EXROL=Everybody
TaskA.EXTYPE=0
TaskA.NAM=Install OperationSystem
TaskA.PRI=2
TaskA.ROL=Everybody
TaskA.SKIP_TASK_LIST=false
TaskA.TYPE=0' #txt
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
Ae0 f15 345 265 30 30 -16 17 #rect
Ae0 f15 @|TaskSwitchSimpleIcon #fIcon
Ae0 f19 expr data #txt
Ae0 f19 outCond ivp=="TaskA.ivp" #txt
Ae0 f19 375 280 513 280 #arcP
Ae0 f25 processCall 'Functional Processes/HideSystemCase:call(Boolean)' #txt
Ae0 f25 requestActionDecl '<Boolean hideBusinessCase> param;' #txt
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
Ae0 f25 160 146 112 44 -48 -8 #rect
Ae0 f25 @|CallSubIcon #fIcon
Ae0 f26 expr out #txt
Ae0 f26 103 168 160 168 #arcP
Ae0 f17 expr out #txt
Ae0 f17 272 168 320 168 #arcP
Ae0 f27 outLink Appraisal.ivp #txt
Ae0 f27 inParamDecl '<> param;' #txt
Ae0 f27 requestEnabled true #txt
Ae0 f27 triggerEnabled false #txt
Ae0 f27 callSignature Appraisal() #txt
Ae0 f27 persist false #txt
Ae0 f27 startName Appraisal #txt
Ae0 f27 taskData 'TaskTriggered.EXPRI=2
TaskTriggered.EXROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.PRI=2
TaskTriggered.ROL=Everybody
TaskTriggered.TYPE=0' #txt
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
Ae0 f27 73 361 30 30 -35 17 #rect
Ae0 f27 @|StartRequestIcon #fIcon
Ae0 f29 processCall 'Business Processes/testCaseMaps/testProcesses/ApproveLeave:RequestForm()' #txt
Ae0 f29 requestActionDecl '<> param;' #txt
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
Ae0 f29 280 354 112 44 -37 -8 #rect
Ae0 f29 @|TriggerIcon #fIcon
Ae0 f30 553 361 30 30 0 15 #rect
Ae0 f30 @|EndIcon #fIcon
Ae0 f31 expr out #txt
Ae0 f31 392 376 553 376 #arcP
Ae0 f28 expr out #txt
Ae0 f28 103 376 280 376 #arcP
Ae0 f32 outLink RequestForm.ivp #txt
Ae0 f32 inParamDecl '<> param;' #txt
Ae0 f32 requestEnabled false #txt
Ae0 f32 triggerEnabled true #txt
Ae0 f32 callSignature RequestForm() #txt
Ae0 f32 persist false #txt
Ae0 f32 taskData 'TaskTriggered.EXPRI=2
TaskTriggered.EXROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.PRI=2
TaskTriggered.ROL=SYSTEM
TaskTriggered.TYPE=0' #txt
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
Ae0 f32 73 441 30 30 -42 17 #rect
Ae0 f32 @|StartRequestIcon #fIcon
Ae0 f33 actionTable 'out=in1;
' #txt
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
Ae0 f33 329 441 30 30 -37 17 #rect
Ae0 f33 @|TaskSwitchSimpleIcon #fIcon
Ae0 f35 441 441 30 30 0 15 #rect
Ae0 f35 @|EndIcon #fIcon
Ae0 f36 expr data #txt
Ae0 f36 outCond ivp=="TaskA.ivp" #txt
Ae0 f36 359 456 441 456 #arcP
Ae0 f37 processCall 'Functional Processes/HideSystemCase:call(Boolean)' #txt
Ae0 f37 requestActionDecl '<Boolean hideBusinessCase> param;' #txt
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
Ae0 f37 160 434 112 44 -48 -8 #rect
Ae0 f37 @|CallSubIcon #fIcon
Ae0 f38 expr out #txt
Ae0 f38 103 456 160 456 #arcP
Ae0 f34 expr out #txt
Ae0 f34 type internaltest.Data #txt
Ae0 f34 var in1 #txt
Ae0 f34 272 456 329 456 #arcP
Ae0 f39 processCall 'Functional Processes/HideSystemCase:call(Boolean)' #txt
Ae0 f39 requestActionDecl '<Boolean hideBusinessCase> param;' #txt
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
Ae0 f39 176 258 112 44 -48 -8 #rect
Ae0 f39 @|CallSubIcon #fIcon
Ae0 f40 expr out #txt
Ae0 f40 95 280 176 280 #arcP
Ae0 f18 expr out #txt
Ae0 f18 type internaltest.Data #txt
Ae0 f18 var in1 #txt
Ae0 f18 288 280 345 280 #arcP
Ae0 f41 outLink create12CasesWithCategory.ivp #txt
Ae0 f41 inParamDecl '<> param;' #txt
Ae0 f41 inParamTable 'out.caseNumber=12;
out.count=0;
' #txt
Ae0 f41 requestEnabled true #txt
Ae0 f41 triggerEnabled false #txt
Ae0 f41 callSignature create12CasesWithCategory() #txt
Ae0 f41 persist false #txt
Ae0 f41 startName 'Create 12 Cases with category' #txt
Ae0 f41 taskData 'TaskTriggered.EXPRI=2
TaskTriggered.EXROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.PRI=2
TaskTriggered.ROL=Everybody
TaskTriggered.TYPE=0' #txt
Ae0 f41 caseData businessCase.attach=true #txt
Ae0 f41 showInStartList 1 #txt
Ae0 f41 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>create12CasesWithCategory.ivp</name>
        <nameStyle>29,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ae0 f41 @C|.responsibility Everybody #txt
Ae0 f41 73 601 30 30 -88 17 #rect
Ae0 f41 @|StartRequestIcon #fIcon
Ae0 f46 672 600 32 32 0 16 #rect
Ae0 f46 @|AlternativeIcon #fIcon
Ae0 f48 801 601 30 30 0 15 #rect
Ae0 f48 @|EndIcon #fIcon
Ae0 f49 expr in #txt
Ae0 f49 outCond 'in.count == in.caseNumber' #txt
Ae0 f49 704 616 801 616 #arcP
Ae0 f50 168 600 32 32 0 16 #rect
Ae0 f50 @|AlternativeIcon #fIcon
Ae0 f51 expr out #txt
Ae0 f51 103 616 168 616 #arcP
Ae0 f45 processCall 'Business Processes/testCaseMaps/testProcesses/ApproveLeave:startACase(Integer)' #txt
Ae0 f45 requestActionDecl '<Integer count> param;' #txt
Ae0 f45 requestMappingAction 'param.count=in.count;
' #txt
Ae0 f45 responseActionDecl 'internaltest.Data out;
' #txt
Ae0 f45 responseMappingAction 'out=in;
' #txt
Ae0 f45 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>ApproveLeave</name>
        <nameStyle>12,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ae0 f45 344 594 112 44 -38 -8 #rect
Ae0 f45 @|TriggerIcon #fIcon
Ae0 f42 expr in #txt
Ae0 f42 200 616 344 616 #arcP
Ae0 f43 outLink startACase.ivp #txt
Ae0 f43 inParamDecl '<Integer count> param;' #txt
Ae0 f43 inParamTable 'out.count=param.count;
' #txt
Ae0 f43 requestEnabled false #txt
Ae0 f43 triggerEnabled true #txt
Ae0 f43 callSignature startACase(Integer) #txt
Ae0 f43 persist false #txt
Ae0 f43 taskData 'TaskTriggered.EXPRI=2
TaskTriggered.EXROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.PRI=2
TaskTriggered.ROL=SYSTEM
TaskTriggered.TYPE=0' #txt
Ae0 f43 caseData 'businessCase.attach=false
customFields.STRING.CustomVarCharField1="CustomVarcharField1" + param.count
customFields.TIMESTAMP.CustomTimestampField1=new DateTime()' #txt
Ae0 f43 showInStartList 1 #txt
Ae0 f43 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>startACase(Integer)</name>
        <nameStyle>19,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ae0 f43 @C|.responsibility Everybody #txt
Ae0 f43 57 825 30 30 -53 17 #rect
Ae0 f43 @|StartRequestIcon #fIcon
Ae0 f47 actionTable 'out=in1;
' #txt
Ae0 f47 outLinks "TaskA.ivp" #txt
Ae0 f47 caseData case.name=TestCase #txt
Ae0 f47 taskData 'TaskA.CATEGORY=<%\=in1.category%>
TaskA.EXPRI=2
TaskA.EXROL=Everybody
TaskA.EXTYPE=0
TaskA.NAM=Task number <%\=in1.count%>
TaskA.PRI=2
TaskA.ROL=Everybody
TaskA.SKIP_TASK_LIST=false
TaskA.TYPE=0' #txt
Ae0 f47 template "" #txt
Ae0 f47 313 825 30 30 0 16 #rect
Ae0 f47 @|TaskSwitchSimpleIcon #fIcon
Ae0 f54 457 825 30 30 0 15 #rect
Ae0 f54 @|EndIcon #fIcon
Ae0 f55 expr data #txt
Ae0 f55 outCond ivp=="TaskA.ivp" #txt
Ae0 f55 343 840 457 840 #arcP
Ae0 f52 actionTable 'out=in;
' #txt
Ae0 f52 actionCode in.count++; #txt
Ae0 f52 504 594 112 44 0 -8 #rect
Ae0 f52 @|StepIcon #fIcon
Ae0 f56 expr out #txt
Ae0 f56 456 616 504 616 #arcP
Ae0 f44 expr out #txt
Ae0 f44 616 616 672 616 #arcP
Ae0 f57 expr in #txt
Ae0 f57 688 632 184 632 #arcP
Ae0 f57 1 688 752 #addKink
Ae0 f57 2 184 752 #addKink
Ae0 f57 1 0.5 0 0 #arcLabel
Ae0 f58 actionTable 'out=in;
' #txt
Ae0 f58 actionCode 'String a;
if(in.count % 3 == 0){
	a = "CaseGroup/Group1/TestCase" + in.count;
	in.category = "TaskGroup/Group1/TestCase" + in.count;
} else if(in.count % 3 == 1) {
	a = "CaseGroup/Group2/TestCase" + in.count;
	in.category = "TaskGroup/Group2/TestCase" + in.count;
} else {
	a = "CaseGroup/Group3/TestCase" + in.count;
	in.category = "TaskGroup/Group3/TestCase" + in.count;
}
ivy.case.setCategoryPath(a);' #txt
Ae0 f58 144 818 112 44 0 -8 #rect
Ae0 f58 @|StepIcon #fIcon
Ae0 f59 expr out #txt
Ae0 f59 87 840 144 840 #arcP
Ae0 f53 expr out #txt
Ae0 f53 type internaltest.Data #txt
Ae0 f53 var in1 #txt
Ae0 f53 256 840 313 840 #arcP
>Proto Ae0 .type internaltest.Data #txt
>Proto Ae0 .processKind NORMAL #txt
>Proto Ae0 0 0 32 24 18 0 #rect
>Proto Ae0 @|BIcon #fIcon
Ae0 f0 mainOut f4 tail #connect
Ae0 f4 head f3 mainIn #connect
Ae0 f3 mainOut f2 tail #connect
Ae0 f2 head f1 mainIn #connect
Ae0 f16 mainOut f12 tail #connect
Ae0 f12 head f11 mainIn #connect
Ae0 f15 out f19 tail #connect
Ae0 f19 head f14 mainIn #connect
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
Ae0 f46 out f49 tail #connect
Ae0 f49 head f48 mainIn #connect
Ae0 f41 mainOut f51 tail #connect
Ae0 f51 head f50 in #connect
Ae0 f50 out f42 tail #connect
Ae0 f42 head f45 mainIn #connect
Ae0 f47 out f55 tail #connect
Ae0 f55 head f54 mainIn #connect
Ae0 f45 mainOut f56 tail #connect
Ae0 f56 head f52 mainIn #connect
Ae0 f52 mainOut f44 tail #connect
Ae0 f44 head f46 in #connect
Ae0 f46 out f57 tail #connect
Ae0 f57 head f50 in #connect
Ae0 f43 mainOut f59 tail #connect
Ae0 f59 head f58 mainIn #connect
Ae0 f58 mainOut f53 tail #connect
Ae0 f53 head f47 in #connect
