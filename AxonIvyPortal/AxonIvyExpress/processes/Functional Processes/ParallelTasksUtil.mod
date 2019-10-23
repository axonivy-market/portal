[Ivy]
1549EAD7E9C3125A 7.5.0 #module
>Proto >Proto Collection #zClass
Pl0 ParallelTasksUtil Big #zClass
Pl0 B #cInfo
Pl0 #process
Pl0 @TextInP .type .type #zField
Pl0 @TextInP .processKind .processKind #zField
Pl0 @AnnotationInP-0n ai ai #zField
Pl0 @MessageFlowInP-0n messageIn messageIn #zField
Pl0 @MessageFlowOutP-0n messageOut messageOut #zField
Pl0 @TextInP .xml .xml #zField
Pl0 @TextInP .responsibility .responsibility #zField
Pl0 @EndSub f1 '' #zField
Pl0 @GridStep f17 '' #zField
Pl0 @Alternative f12 '' #zField
Pl0 @GridStep f15 '' #zField
Pl0 @TaskSwitch f18 '' #zField
Pl0 @StartSub f23 '' #zField
Pl0 @TkArc f19 '' #zField
Pl0 @PushWFArc f2 '' #zField
Pl0 @PushWFArc f20 '' #zField
Pl0 @PushWFArc f21 '' #zField
Pl0 @PushWFArc f16 '' #zField
Pl0 @PushWFArc f0 '' #zField
Pl0 @EndTask f10 '' #zField
Pl0 @PushWFArc f11 '' #zField
Pl0 @EndSub f32 '' #zField
Pl0 @Alternative f34 '' #zField
Pl0 @StartSub f35 '' #zField
Pl0 @EndTask f36 '' #zField
Pl0 @PushWFArc f37 '' #zField
Pl0 @PushWFArc f39 '' #zField
Pl0 @PushWFArc f3 '' #zField
>Proto Pl0 Pl0 ParallelTasksUtil #zField
Pl0 f1 129 321 30 30 0 15 #rect
Pl0 f1 @|EndSubIcon #fIcon
Pl0 f17 actionTable 'out=in;
' #txt
Pl0 f17 actionCode in.counter++; #txt
Pl0 f17 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>counter++</name>
        <nameStyle>9,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Pl0 f17 254 188 36 24 20 -2 #rect
Pl0 f17 @|StepIcon #fIcon
Pl0 f12 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>for loop</name>
        <nameStyle>8,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Pl0 f12 130 130 28 28 14 0 #rect
Pl0 f12 @|AlternativeIcon #fIcon
Pl0 f15 actionTable 'out=in;
' #txt
Pl0 f15 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>actual
task</name>
        <nameStyle>11
</nameStyle>
    </language>
</elementInfo>
' #txt
Pl0 f15 126 196 36 24 -68 -8 #rect
Pl0 f15 @|StepIcon #fIcon
Pl0 f18 actionTable 'out=in1;
' #txt
Pl0 f18 outLinks "TaskA.ivp","TaskB.ivp" #txt
Pl0 f18 taskData 'TaskA.EXPRI=2
TaskA.EXROL=Everybody
TaskA.EXTYPE=0
TaskA.NAM=System Task\: Create Task
TaskA.PRI=2
TaskA.ROL=SYSTEM
TaskA.SKIP_TASK_LIST=false
TaskA.TYPE=0
TaskB.EXPRI=2
TaskB.EXROL=Everybody
TaskB.EXTYPE=0
TaskB.NAM=System Task\: Check for next Task
TaskB.PRI=2
TaskB.ROL=SYSTEM
TaskB.SKIP_TASK_LIST=false
TaskB.TYPE=0' #txt
Pl0 f18 template "" #txt
Pl0 f18 130 258 28 28 14 0 #rect
Pl0 f18 @|TaskSwitchIcon #fIcon
Pl0 f23 inParamDecl '<Number numberOfTasks> param;' #txt
Pl0 f23 inParamTable 'out.numberOfTasks=param.numberOfTasks;
' #txt
Pl0 f23 outParamDecl '<Number counter> result;' #txt
Pl0 f23 outParamTable 'result.counter=in.counter;
' #txt
Pl0 f23 callSignature splitTasks(Number) #txt
Pl0 f23 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>splitTasks</name>
        <nameStyle>10,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Pl0 f23 129 49 30 30 16 0 #rect
Pl0 f23 @|StartSubIcon #fIcon
Pl0 f19 expr out #txt
Pl0 f19 type gawfs.ParallelTasksUtilData #txt
Pl0 f19 var in1 #txt
Pl0 f19 144 220 144 258 #arcP
Pl0 f2 expr data #txt
Pl0 f2 outCond ivp=="TaskA.ivp" #txt
Pl0 f2 144 286 144 321 #arcP
Pl0 f20 expr data #txt
Pl0 f20 outCond ivp=="TaskB.ivp" #txt
Pl0 f20 158 272 272 212 #arcP
Pl0 f20 1 272 272 #addKink
Pl0 f20 0 0.9527391199507482 0 0 #arcLabel
Pl0 f21 expr out #txt
Pl0 f21 272 188 158 144 #arcP
Pl0 f21 1 272 144 #addKink
Pl0 f21 1 0.04726088004925168 0 0 #arcLabel
Pl0 f16 expr in #txt
Pl0 f16 144 158 144 196 #arcP
Pl0 f0 expr out #txt
Pl0 f0 144 79 144 130 #arcP
Pl0 f10 33 129 30 30 16 0 #rect
Pl0 f10 @|EndIcon #fIcon
Pl0 f11 expr in #txt
Pl0 f11 outCond 'in.counter >= in.numberOfTasks' #txt
Pl0 f11 130 144 63 144 #arcP
Pl0 f32 516 231 26 26 14 0 #rect
Pl0 f32 @|EndSubIcon #fIcon
Pl0 f34 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>has open tasks?</name>
        <nameStyle>15,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Pl0 f34 515 166 28 28 -45 -42 #rect
Pl0 f34 @|AlternativeIcon #fIcon
Pl0 f35 inParamDecl '<> param;' #txt
Pl0 f35 outParamDecl '<> result;' #txt
Pl0 f35 callSignature join() #txt
Pl0 f35 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>join()</name>
    </language>
</elementInfo>
' #txt
Pl0 f35 516 55 26 26 14 0 #rect
Pl0 f35 @|StartSubIcon #fIcon
Pl0 f36 626 165 30 30 16 0 #rect
Pl0 f36 @|EndIcon #fIcon
Pl0 f37 expr in #txt
Pl0 f37 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>no</name>
        <nameStyle>2,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Pl0 f37 529 194 529 231 #arcP
Pl0 f37 0 0.5135135135135135 -11 0 #arcLabel
Pl0 f39 expr in #txt
Pl0 f39 outCond 'ivy.case.getActiveTasks().size() > 1' #txt
Pl0 f39 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>yes</name>
        <nameStyle>3,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Pl0 f39 543 180 626 180 #arcP
Pl0 f39 0 0.43373493975903615 0 9 #arcLabel
Pl0 f3 expr out #txt
Pl0 f3 529 81 529 166 #arcP
>Proto Pl0 .type gawfs.ParallelTasksUtilData #txt
>Proto Pl0 .processKind CALLABLE_SUB #txt
>Proto Pl0 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <swimlaneLabel>Split</swimlaneLabel>
        <swimlaneLabel>Join</swimlaneLabel>
    </language>
    <swimlaneOrientation>true</swimlaneOrientation>
    <swimlaneSize>384</swimlaneSize>
    <swimlaneSize>320</swimlaneSize>
    <swimlaneColor gradient="false">-1</swimlaneColor>
    <swimlaneColor gradient="false">-1</swimlaneColor>
    <swimlaneType>LANE</swimlaneType>
    <swimlaneType>LANE</swimlaneType>
    <swimlaneSpaceBefore>0</swimlaneSpaceBefore>
    <swimlaneSpaceBefore>0</swimlaneSpaceBefore>
</elementInfo>
' #txt
>Proto Pl0 0 0 32 24 18 0 #rect
>Proto Pl0 @|BIcon #fIcon
Pl0 f15 mainOut f19 tail #connect
Pl0 f19 head f18 in #connect
Pl0 f18 out f2 tail #connect
Pl0 f2 head f1 mainIn #connect
Pl0 f18 out f20 tail #connect
Pl0 f20 head f17 mainIn #connect
Pl0 f17 mainOut f21 tail #connect
Pl0 f21 head f12 in #connect
Pl0 f16 head f15 mainIn #connect
Pl0 f23 mainOut f0 tail #connect
Pl0 f0 head f12 in #connect
Pl0 f12 out f11 tail #connect
Pl0 f11 head f10 mainIn #connect
Pl0 f12 out f16 tail #connect
Pl0 f37 head f32 mainIn #connect
Pl0 f34 out f39 tail #connect
Pl0 f39 head f36 mainIn #connect
Pl0 f34 out f37 tail #connect
Pl0 f35 mainOut f3 tail #connect
Pl0 f3 head f34 in #connect
