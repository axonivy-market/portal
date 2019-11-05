[Ivy]
15B1EA24CCF377E8 7.5.0 #module
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
Be0 f0 @|StartRequestIcon #fIcon
Be0 f1 51 339 26 26 14 0 #rect
Be0 f1 @|EndIcon #fIcon
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
Be0 f3 @|SignalStartEventIcon #fIcon
Be0 f4 275 339 26 26 14 0 #rect
Be0 f4 @|EndIcon #fIcon
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
Be0 f5 @|TaskSwitchIcon #fIcon
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
Be0 f8 @|StepIcon #fIcon
Be0 f9 expr out #txt
Be0 f9 64 109 64 180 #arcP
Be0 f12 expr out #txt
Be0 f12 64 204 64 339 #arcP
Be0 f2 actionTable 'out=in;
' #txt
Be0 f2 actionCode 'out.caseName = "TECH: Update checkin time";
out.taskName = "Update checkin time of case " +  ivy.case.getBusinessCase().getId();' #txt
Be0 f2 270 148 36 24 20 -2 #rect
Be0 f2 @|StepIcon #fIcon
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
Be0 f11 @|StartRequestIcon #fIcon
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
Be0 f13 @|TriggerIcon #fIcon
Be0 f14 expr out #txt
Be0 f14 448 109 448 180 #arcP
Be0 f17 435 339 26 26 14 0 #rect
Be0 f17 @|EndIcon #fIcon
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
Be0 f19 @|StartRequestIcon #fIcon
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
Be0 f20 @|TaskSwitchIcon #fIcon
Be0 f21 expr out #txt
Be0 f21 type internaltest.Data #txt
Be0 f21 var in1 #txt
Be0 f21 612 109 612 178 #arcP
Be0 f22 599 339 26 26 14 0 #rect
Be0 f22 @|EndIcon #fIcon
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
Be0 f24 @|SignalStartEventIcon #fIcon
Be0 f25 883 339 26 26 14 0 #rect
Be0 f25 @|EndIcon #fIcon
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
Be0 f27 @|TaskSwitchIcon #fIcon
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
Be0 f16 @|TriggerIcon #fIcon
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
Be0 f29 @|StartRequestIcon #fIcon
Be0 f30 755 339 26 26 14 0 #rect
Be0 f30 @|EndIcon #fIcon
Be0 f32 actionTable 'out=in;
' #txt
Be0 f32 actionCode ivy.wf.signals().send("ivy:portal:pizza:delivery"); #txt
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
Be0 f32 @|StepIcon #fIcon
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
Be0 f34 @|TaskSwitchIcon #fIcon
Be0 f35 type internaltest.Data #txt
Be0 f35 var in1 #txt
Be0 f35 896 109 896 210 #arcP
Be0 f26 expr data #txt
Be0 f26 outCond ivp=="TaskA.ivp" #txt
Be0 f26 896 238 896 339 #arcP
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
