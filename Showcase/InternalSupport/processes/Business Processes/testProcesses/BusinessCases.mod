[Ivy]
15B1EA24CCF377E8 9.3.1 #module
>Proto >Proto Collection #zClass
Be0 BusinessCases Big #zClass
Be0 B #cInfo
Be0 #process
Be0 @TextInP .type .type #zField
Be0 @TextInP .processKind .processKind #zField
Be0 @AnnotationInP-0n ai ai #zField
Be0 @MessageFlowInP-0n messageIn messageIn #zField
Be0 @MessageFlowOutP-0n messageOut messageOut #zField
Be0 @TextInP .xml .xml #zField
Be0 @TextInP .responsibility .responsibility #zField
Be0 @StartRequest f0 '' #zField
Be0 @EndTask f1 '' #zField
Be0 @SignalStartEvent f3 '' #zField
Be0 @EndTask f4 '' #zField
Be0 @TaskSwitch f5 '' #zField
Be0 @PushWFArc f7 '' #zField
Be0 @GridStep f8 '' #zField
Be0 @PushWFArc f9 '' #zField
Be0 @PushWFArc f12 '' #zField
Be0 @GridStep f2 '' #zField
Be0 @PushWFArc f10 '' #zField
Be0 @TkArc f6 '' #zField
Be0 @StartRequest f11 '' #zField
Be0 @Trigger f13 '' #zField
Be0 @PushWFArc f14 '' #zField
Be0 @EndTask f17 '' #zField
Be0 @StartRequest f19 '' #zField
Be0 @TaskSwitch f20 '' #zField
Be0 @TkArc f21 '' #zField
Be0 @EndTask f22 '' #zField
Be0 @SignalStartEvent f24 '' #zField
Be0 @EndTask f25 '' #zField
Be0 @TaskSwitch f27 '' #zField
Be0 @PushWFArc f18 '' #zField
Be0 @TkArc f15 '' #zField
Be0 @Trigger f16 '' #zField
Be0 @PushWFArc f28 '' #zField
Be0 @PushWFArc f23 '' #zField
Be0 @StartRequest f29 '' #zField
Be0 @EndTask f30 '' #zField
Be0 @GridStep f32 '' #zField
Be0 @PushWFArc f33 '' #zField
Be0 @PushWFArc f31 '' #zField
Be0 @TaskSwitch f34 '' #zField
Be0 @TkArc f35 '' #zField
Be0 @PushWFArc f26 '' #zField
Be0 @StartRequest f36 '' #zField
Be0 @UserTask f37 '' #zField
Be0 @EndTask f39 '' #zField
Be0 @PushWFArc f40 '' #zField
Be0 @GridStep f41 '' #zField
Be0 @TkArc f38 '' #zField
Be0 @SignalStartEvent f43 '' #zField
Be0 @TaskSwitchSimple f44 '' #zField
Be0 @EndTask f45 '' #zField
Be0 @TkArc f46 '' #zField
Be0 @PushWFArc f47 '' #zField
Be0 @TaskSwitchSimple f48 '' #zField
Be0 @TkArc f49 '' #zField
Be0 @StartRequest f50 '' #zField
Be0 @EndTask f51 '' #zField
Be0 @TaskSwitchSimple f53 '' #zField
Be0 @TkArc f54 '' #zField
Be0 @PushWFArc f52 '' #zField
Be0 @Trigger f55 '' #zField
Be0 @PushWFArc f56 '' #zField
Be0 @PushWFArc f42 '' #zField
>Proto Be0 Be0 BusinessCases #zField
Be0 f0 outLink updateCheckInTime.ivp #txt
Be0 f0 inParamDecl '<> param;' #txt
Be0 f0 requestEnabled true #txt
Be0 f0 triggerEnabled false #txt
Be0 f0 callSignature updateCheckInTime() #txt
Be0 f0 persist false #txt
Be0 f0 startName 'Update checkin time' #txt
Be0 f0 taskData 'TaskTriggered.EXPRI=2
TaskTriggered.EXROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.NAM=Update checkin time
TaskTriggered.PRI=2
TaskTriggered.ROL=Everybody
TaskTriggered.TYPE=0' #txt
Be0 f0 caseData 'businessCase.attach=true
case.name=Update checkin time' #txt
Be0 f0 showInStartList 1 #txt
Be0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>updateCheckInTime.ivp</name>
        <nameStyle>21,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Be0 f0 @C|.responsibility Everybody #txt
Be0 f0 51 83 26 26 14 0 #rect
Be0 f1 51 339 26 26 14 0 #rect
Be0 f3 signalCode ch:axonivy:portal:internalsupport:updatecheckintime #txt
Be0 f3 attachToBusinessCase true #txt
Be0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>signal</name>
        <nameStyle>6,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Be0 f3 275 83 26 26 14 0 #rect
Be0 f4 275 339 26 26 14 0 #rect
Be0 f5 actionTable 'out=in1;
' #txt
Be0 f5 outLinks "TaskA.ivp" #txt
Be0 f5 caseData case.name=<%\=in1.caseName%> #txt
Be0 f5 taskData 'TaskA.EXPRI=2
TaskA.EXROL=Everybody
TaskA.EXTYPE=0
TaskA.NAM=<%\=in1.taskName%>
TaskA.PRI=2
TaskA.ROL=Everybody
TaskA.SKIP_TASK_LIST=false
TaskA.TYPE=0' #txt
Be0 f5 template "" #txt
Be0 f5 274 210 28 28 14 0 #rect
Be0 f7 expr data #txt
Be0 f7 outCond ivp=="TaskA.ivp" #txt
Be0 f7 288 238 288 339 #arcP
Be0 f8 actionTable 'out=in;
' #txt
Be0 f8 actionCode 'import ch.ivyteam.ivy.process.model.value.SignalCode;

SignalCode code = new SignalCode("ch:axonivy:portal:internalsupport:updatecheckintime");
ivy.wf.signals().send(code);' #txt
Be0 f8 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>send signal</name>
        <nameStyle>11
</nameStyle>
    </language>
</elementInfo>
' #txt
Be0 f8 46 180 36 24 20 -2 #rect
Be0 f9 expr out #txt
Be0 f9 64 109 64 180 #arcP
Be0 f12 expr out #txt
Be0 f12 64 204 64 339 #arcP
Be0 f2 actionTable 'out=in;
' #txt
Be0 f2 actionCode 'out.caseName = "TECH: Update checkin time";
out.taskName = "Update checkin time of case " +  ivy.case.getBusinessCase().getId();' #txt
Be0 f2 270 148 36 24 20 -2 #rect
Be0 f10 288 109 288 148 #arcP
Be0 f6 expr out #txt
Be0 f6 type internaltest.Data #txt
Be0 f6 var in1 #txt
Be0 f6 288 172 288 210 #arcP
Be0 f11 outLink OrderPizza.ivp #txt
Be0 f11 inParamDecl '<> param;' #txt
Be0 f11 requestEnabled true #txt
Be0 f11 triggerEnabled false #txt
Be0 f11 callSignature OrderPizza() #txt
Be0 f11 persist false #txt
Be0 f11 taskData 'TaskTriggered.EXPRI=2
TaskTriggered.EXROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.NAM=Order pizza
TaskTriggered.PRI=2
TaskTriggered.ROL=Everybody
TaskTriggered.TYPE=0' #txt
Be0 f11 caseData 'businessCase.attach=true
case.name=Order Pizza' #txt
Be0 f11 showInStartList 1 #txt
Be0 f11 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>OrderPizza.ivp</name>
        <nameStyle>14,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Be0 f11 @C|.responsibility Everybody #txt
Be0 f11 435 83 26 26 14 0 #rect
Be0 f13 processCall 'Business Processes/testProcesses/BusinessCases:takeOrder()' #txt
Be0 f13 requestActionDecl '<> param;' #txt
Be0 f13 responseActionDecl 'internaltest.Data out;
' #txt
Be0 f13 responseMappingAction 'out=in;
' #txt
Be0 f13 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>order</name>
        <nameStyle>5
</nameStyle>
    </language>
</elementInfo>
' #txt
Be0 f13 430 180 36 24 20 -2 #rect
Be0 f14 expr out #txt
Be0 f14 448 109 448 180 #arcP
Be0 f17 435 339 26 26 14 0 #rect
Be0 f19 outLink takeOrder.ivp #txt
Be0 f19 inParamDecl '<> param;' #txt
Be0 f19 requestEnabled false #txt
Be0 f19 triggerEnabled true #txt
Be0 f19 callSignature takeOrder() #txt
Be0 f19 persist false #txt
Be0 f19 taskData 'TaskTriggered.EXPRI=2
TaskTriggered.EXROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.NAM=Take Order
TaskTriggered.PRI=2
TaskTriggered.ROL=Everybody
TaskTriggered.TYPE=0' #txt
Be0 f19 caseData 'businessCase.attach=true
case.name=Take Order and Make Pizza' #txt
Be0 f19 showInStartList 1 #txt
Be0 f19 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>takeOrder()</name>
    </language>
</elementInfo>
' #txt
Be0 f19 @C|.responsibility Everybody #txt
Be0 f19 599 83 26 26 14 0 #rect
Be0 f20 actionTable 'out=in1;
' #txt
Be0 f20 outLinks "TaskA.ivp" #txt
Be0 f20 taskData 'TaskA.EXPRI=2
TaskA.EXROL=Everybody
TaskA.EXTYPE=0
TaskA.NAM=Make Pizza
TaskA.PRI=2
TaskA.ROL=Everybody
TaskA.SKIP_TASK_LIST=false
TaskA.TYPE=0' #txt
Be0 f20 template "" #txt
Be0 f20 598 178 28 28 14 0 #rect
Be0 f21 expr out #txt
Be0 f21 type internaltest.Data #txt
Be0 f21 var in1 #txt
Be0 f21 612 109 612 178 #arcP
Be0 f22 599 339 26 26 14 0 #rect
Be0 f24 signalCode ivy:portal:pizza:delivery #txt
Be0 f24 attachToBusinessCase true #txt
Be0 f24 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>delivery</name>
        <nameStyle>8
</nameStyle>
    </language>
</elementInfo>
' #txt
Be0 f24 883 83 26 26 14 0 #rect
Be0 f25 883 339 26 26 14 0 #rect
Be0 f27 actionTable 'out=in1;
' #txt
Be0 f27 outLinks "TaskA.ivp" #txt
Be0 f27 taskData 'TaskA.EXPRI=2
TaskA.EXROL=Everybody
TaskA.EXTYPE=0
TaskA.NAM=Eat Pizza
TaskA.PRI=2
TaskA.ROL=Everybody
TaskA.SKIP_TASK_LIST=false
TaskA.TYPE=0' #txt
Be0 f27 template "" #txt
Be0 f27 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Eat</name>
        <nameStyle>3
</nameStyle>
    </language>
</elementInfo>
' #txt
Be0 f27 434 274 28 28 14 0 #rect
Be0 f18 expr data #txt
Be0 f18 outCond ivp=="TaskA.ivp" #txt
Be0 f18 448 302 448 339 #arcP
Be0 f15 expr out #txt
Be0 f15 type internaltest.Data #txt
Be0 f15 var in1 #txt
Be0 f15 448 204 448 274 #arcP
Be0 f16 processCall 'Business Processes/testProcesses/BusinessCases:pack()' #txt
Be0 f16 requestActionDecl '<> param;' #txt
Be0 f16 responseActionDecl 'internaltest.Data out;
' #txt
Be0 f16 responseMappingAction 'out=in;
' #txt
Be0 f16 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>BusinessCases</name>
    </language>
</elementInfo>
' #txt
Be0 f16 594 276 36 24 20 -2 #rect
Be0 f28 expr data #txt
Be0 f28 outCond ivp=="TaskA.ivp" #txt
Be0 f28 612 206 612 276 #arcP
Be0 f23 expr out #txt
Be0 f23 612 300 612 339 #arcP
Be0 f29 outLink pack.ivp #txt
Be0 f29 inParamDecl '<> param;' #txt
Be0 f29 requestEnabled false #txt
Be0 f29 triggerEnabled true #txt
Be0 f29 callSignature pack() #txt
Be0 f29 persist false #txt
Be0 f29 taskData 'TaskTriggered.EXPRI=2
TaskTriggered.EXROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.NAM=Pack Pizza
TaskTriggered.PRI=2
TaskTriggered.ROL=Everybody
TaskTriggered.TYPE=0' #txt
Be0 f29 caseData 'businessCase.attach=true
case.name=Pack Pizza' #txt
Be0 f29 showInStartList 1 #txt
Be0 f29 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>pack()</name>
    </language>
</elementInfo>
' #txt
Be0 f29 @C|.responsibility Everybody #txt
Be0 f29 755 83 26 26 14 0 #rect
Be0 f30 755 339 26 26 14 0 #rect
Be0 f32 actionTable 'out=in;
' #txt
Be0 f32 actionCode 'import ch.ivyteam.ivy.process.model.value.SignalCode;

SignalCode code = new SignalCode("ivy:portal:pizza:delivery");
ivy.wf.signals().send(code);' #txt
Be0 f32 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>delivery</name>
        <nameStyle>8
</nameStyle>
    </language>
</elementInfo>
' #txt
Be0 f32 750 212 36 24 20 -2 #rect
Be0 f33 expr out #txt
Be0 f33 768 109 768 212 #arcP
Be0 f31 expr out #txt
Be0 f31 768 236 768 339 #arcP
Be0 f34 actionTable 'out=in1;
' #txt
Be0 f34 outLinks "TaskA.ivp" #txt
Be0 f34 caseData 'case.name=Delivery Pizza' #txt
Be0 f34 taskData 'TaskA.EXPRI=2
TaskA.EXROL=Everybody
TaskA.EXTYPE=0
TaskA.NAM=Delivery Pizza
TaskA.PRI=2
TaskA.ROL=Everybody
TaskA.SKIP_TASK_LIST=false
TaskA.TYPE=0' #txt
Be0 f34 template "" #txt
Be0 f34 882 210 28 28 14 0 #rect
Be0 f35 type internaltest.Data #txt
Be0 f35 var in1 #txt
Be0 f35 896 109 896 210 #arcP
Be0 f26 expr data #txt
Be0 f26 outCond ivp=="TaskA.ivp" #txt
Be0 f26 896 238 896 339 #arcP
Be0 f36 outLink saleAndInform.ivp #txt
Be0 f36 inParamDecl '<> param;' #txt
Be0 f36 requestEnabled true #txt
Be0 f36 triggerEnabled false #txt
Be0 f36 callSignature saleAndInform() #txt
Be0 f36 startName 'Sale and inform' #txt
Be0 f36 startDescription 'Task with UI and trigger signal' #txt
Be0 f36 caseData 'businessCase.attach=true
case.name=Sale and inform
customFields.STRING.embedInFrame="false"' #txt
Be0 f36 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>saleAndInform.ivp</name>
    </language>
</elementInfo>
' #txt
Be0 f36 @C|.responsibility Everybody #txt
Be0 f36 1105 81 30 30 16 0 #rect
Be0 f37 dialogId internaltest.ui.SaleDepartment #txt
Be0 f37 startMethod start() #txt
Be0 f37 requestActionDecl '<> param;' #txt
Be0 f37 responseMappingAction 'out=in;
' #txt
Be0 f37 caseData customFields.STRING.embedInFrame="false" #txt
Be0 f37 taskData 'TaskA.CATEGORY=SALE/SIGNCONTRACT
TaskA.NAM=Work with sale department' #txt
Be0 f37 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>SaleDepartment</name>
    </language>
</elementInfo>
' #txt
Be0 f37 1064 458 112 44 -45 -8 #rect
Be0 f39 1105 593 30 30 16 0 #rect
Be0 f40 1120 502 1120 593 #arcP
Be0 f41 actionTable 'out=in;
' #txt
Be0 f41 actionCode 'import ch.ivyteam.ivy.process.model.value.SignalCode;

SignalCode code = new SignalCode("sale:inform");
ivy.wf.signals().send(code);' #txt
Be0 f41 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>trigger signal</name>
    </language>
</elementInfo>
' #txt
Be0 f41 1064 362 112 44 -36 -8 #rect
Be0 f38 1120 406 1120 458 #arcP
Be0 f43 signalCode sale:inform #txt
Be0 f43 attachToBusinessCase true #txt
Be0 f43 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Send mail</name>
    </language>
</elementInfo>
' #txt
Be0 f43 1281 193 30 30 16 0 #rect
Be0 f44 actionTable 'out=in1;
' #txt
Be0 f44 caseData 'case.category=SALE/SENDMAIL
case.name=Send mail inform' #txt
Be0 f44 taskData 'TaskA.NAM=Send email inform ' #txt
Be0 f44 1281 289 30 30 15 0 #rect
Be0 f45 1281 401 30 30 16 0 #rect
Be0 f46 1296 223 1296 289 #arcP
Be0 f47 1296 319 1296 401 #arcP
Be0 f48 actionTable 'out=in1;
' #txt
Be0 f48 taskData 'TaskA.NAM=Submit request
TaskA.SKIP_TASK_LIST=true' #txt
Be0 f48 1105 177 30 30 15 0 #rect
Be0 f49 1120 111 1120 177 #arcP
Be0 f50 outLink startTriggerMail.ivp #txt
Be0 f50 inParamDecl '<> param;' #txt
Be0 f50 requestEnabled true #txt
Be0 f50 triggerEnabled true #txt
Be0 f50 callSignature startTriggerMail() #txt
Be0 f50 taskData 'TaskTriggered.ROL=SYSTEM
TaskTriggered.TYPE=0' #txt
Be0 f50 caseData businessCase.attach=true #txt
Be0 f50 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>startTriggerMail.ivp</name>
    </language>
</elementInfo>
' #txt
Be0 f50 @C|.responsibility Everybody #txt
Be0 f50 1425 193 30 30 16 0 #rect
Be0 f51 1425 401 30 30 16 0 #rect
Be0 f53 actionTable 'out=in1;
' #txt
Be0 f53 caseData 'case.category=SALE/SENDMAIL
case.name=Trigger mail' #txt
Be0 f53 taskData 'TaskA.CATEGORY=SALE/SENDMAIL
TaskA.NAM=Trigger mail
TaskA.SKIP_TASK_LIST=true' #txt
Be0 f53 1425 289 30 30 15 0 #rect
Be0 f54 1440 223 1440 289 #arcP
Be0 f52 1440 319 1440 401 #arcP
Be0 f55 processCall 'Business Processes/testProcesses/BusinessCases:startTriggerMail()' #txt
Be0 f55 requestActionDecl '<> param;' #txt
Be0 f55 responseMappingAction 'out=in;
' #txt
Be0 f55 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Trigger mail</name>
    </language>
</elementInfo>
' #txt
Be0 f55 1064 266 112 44 -33 -8 #rect
Be0 f56 1120 207 1120 266 #arcP
Be0 f42 1120 310 1120 362 #arcP
>Proto Be0 .type internaltest.Data #txt
>Proto Be0 .processKind NORMAL #txt
>Proto Be0 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <swimlaneLabel></swimlaneLabel>
        <swimlaneLabel></swimlaneLabel>
    </language>
    <swimlaneOrientation>true</swimlaneOrientation>
    <swimlaneSize>384</swimlaneSize>
    <swimlaneSize>608</swimlaneSize>
    <swimlaneColor gradient="false">-1</swimlaneColor>
    <swimlaneColor gradient="false">-13312</swimlaneColor>
    <swimlaneType>LANE</swimlaneType>
    <swimlaneType>LANE</swimlaneType>
    <swimlaneSpaceBefore>0</swimlaneSpaceBefore>
    <swimlaneSpaceBefore>0</swimlaneSpaceBefore>
</elementInfo>
' #txt
>Proto Be0 0 0 32 24 18 0 #rect
>Proto Be0 @|BIcon #fIcon
Be0 f5 out f7 tail #connect
Be0 f7 head f4 mainIn #connect
Be0 f0 mainOut f9 tail #connect
Be0 f9 head f8 mainIn #connect
Be0 f8 mainOut f12 tail #connect
Be0 f12 head f1 mainIn #connect
Be0 f3 mainOut f10 tail #connect
Be0 f10 head f2 mainIn #connect
Be0 f2 mainOut f6 tail #connect
Be0 f6 head f5 in #connect
Be0 f11 mainOut f14 tail #connect
Be0 f14 head f13 mainIn #connect
Be0 f19 mainOut f21 tail #connect
Be0 f21 head f20 in #connect
Be0 f27 out f18 tail #connect
Be0 f18 head f17 mainIn #connect
Be0 f13 mainOut f15 tail #connect
Be0 f15 head f27 in #connect
Be0 f20 out f28 tail #connect
Be0 f28 head f16 mainIn #connect
Be0 f16 mainOut f23 tail #connect
Be0 f23 head f22 mainIn #connect
Be0 f29 mainOut f33 tail #connect
Be0 f33 head f32 mainIn #connect
Be0 f32 mainOut f31 tail #connect
Be0 f31 head f30 mainIn #connect
Be0 f24 mainOut f35 tail #connect
Be0 f35 head f34 in #connect
Be0 f34 out f26 tail #connect
Be0 f26 head f25 mainIn #connect
Be0 f37 out f40 tail #connect
Be0 f40 head f39 mainIn #connect
Be0 f41 mainOut f38 tail #connect
Be0 f38 head f37 in #connect
Be0 f43 mainOut f46 tail #connect
Be0 f46 head f44 in #connect
Be0 f44 out f47 tail #connect
Be0 f47 head f45 mainIn #connect
Be0 f36 mainOut f49 tail #connect
Be0 f49 head f48 in #connect
Be0 f50 mainOut f54 tail #connect
Be0 f54 head f53 in #connect
Be0 f53 out f52 tail #connect
Be0 f52 head f51 mainIn #connect
Be0 f48 out f56 tail #connect
Be0 f56 head f55 mainIn #connect
Be0 f55 mainOut f42 tail #connect
Be0 f42 head f41 mainIn #connect
