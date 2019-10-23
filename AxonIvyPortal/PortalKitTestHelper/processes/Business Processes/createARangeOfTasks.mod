[Ivy]
14B2A3DC7173EA26 7.5.0 #module
>Proto >Proto Collection #zClass
cs0 createARangeOfTasks Big #zClass
cs0 B #cInfo
cs0 #process
cs0 @TextInP .type .type #zField
cs0 @TextInP .processKind .processKind #zField
cs0 @AnnotationInP-0n ai ai #zField
cs0 @TextInP .xml .xml #zField
cs0 @TextInP .responsibility .responsibility #zField
cs0 @StartRequest f0 '' #zField
cs0 @Alternative f1 '' #zField
cs0 @TaskSwitch f2 '' #zField
cs0 @EndTask f3 '' #zField
cs0 @EndTask f4 '' #zField
cs0 @TkArc f6 '' #zField
cs0 @PushWFArc f7 '' #zField
cs0 @PushWFArc f8 '' #zField
cs0 @GridStep f9 '' #zField
cs0 @PushWFArc f10 '' #zField
cs0 @PushWFArc f11 '' #zField
cs0 @GridStep f12 '' #zField
cs0 @PushWFArc f13 '' #zField
cs0 @PushWFArc f5 '' #zField
cs0 @Alternative f14 '' #zField
cs0 @EndTask f15 '' #zField
cs0 @TaskSwitch f16 '' #zField
cs0 @GridStep f17 '' #zField
cs0 @EndTask f18 '' #zField
cs0 @StartRequest f19 '' #zField
cs0 @GridStep f20 '' #zField
cs0 @TkArc f21 '' #zField
cs0 @PushWFArc f23 '' #zField
cs0 @PushWFArc f24 '' #zField
cs0 @PushWFArc f25 '' #zField
cs0 @PushWFArc f26 '' #zField
cs0 @PushWFArc f27 '' #zField
cs0 @Trigger f28 '' #zField
cs0 @PushWFArc f29 '' #zField
cs0 @PushWFArc f22 '' #zField
cs0 @StartRequest f30 '' #zField
cs0 @EndTask f31 '' #zField
cs0 @TaskSwitchSimple f33 '' #zField
cs0 @TkArc f34 '' #zField
cs0 @PushWFArc f32 '' #zField
>Proto cs0 cs0 createARangeOfTasks #zField
cs0 f0 outLink generateTask.ivp #txt
cs0 f0 inParamDecl '<ch.ivy.add.portalkit.task.TaskGenerationDataRange rangeData> param;' #txt
cs0 f0 inParamTable 'out=param.rangeData;
' #txt
cs0 f0 requestEnabled false #txt
cs0 f0 triggerEnabled true #txt
cs0 f0 callSignature generateTask(ch.ivy.add.portalkit.task.TaskGenerationDataRange) #txt
cs0 f0 persist false #txt
cs0 f0 taskData 'TaskTriggered.EXPRI=2
TaskTriggered.EXROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.PRI=2
TaskTriggered.ROL=SYSTEM
TaskTriggered.TYPE=0' #txt
cs0 f0 showInStartList 1 #txt
cs0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>generateTask(TaskGenerationDataRange)</name>
        <nameStyle>37,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
cs0 f0 @C|.responsibility Everybody #txt
cs0 f0 195 211 26 26 14 0 #rect
cs0 f0 @|StartRequestIcon #fIcon
cs0 f1 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>in.taskCounter &lt; in.to</name>
        <nameStyle>22,7
</nameStyle>
    </language>
</elementInfo>
' #txt
cs0 f1 194 330 28 28 14 0 #rect
cs0 f1 @|AlternativeIcon #fIcon
cs0 f2 actionTable 'out=in1;
' #txt
cs0 f2 outLinks "TaskA.ivp","TaskB.ivp" #txt
cs0 f2 caseData 'case.name=CASE<%\=in1.taskCounter%>_<%\=in1.structuredData%>
customFields.STRING.CustomVarCharField5=in1.structuredData' #txt
cs0 f2 taskData 'TaskA.EXPRI=2
TaskA.EXROL=Everybody
TaskA.EXTYPE=0
TaskA.NAM=TASK<%\=in1.taskCounter%>_<%\=in1.structuredData%>
TaskA.PRI=2
TaskA.ROL=Everybody
TaskA.SKIP_TASK_LIST=false
TaskA.TYPE=0
TaskA.customFields.STRING.CustomVarCharField5=in1.structuredData
TaskB.EXPRI=2
TaskB.EXROL=Everybody
TaskB.EXTYPE=0
TaskB.PRI=2
TaskB.ROL=SYSTEM
TaskB.SKIP_TASK_LIST=false
TaskB.TYPE=0' #txt
cs0 f2 template "" #txt
cs0 f2 194 418 28 28 14 0 #rect
cs0 f2 @|TaskSwitchIcon #fIcon
cs0 f3 83 331 26 26 14 0 #rect
cs0 f3 @|EndIcon #fIcon
cs0 f4 195 483 26 26 14 0 #rect
cs0 f4 @|EndIcon #fIcon
cs0 f6 expr in #txt
cs0 f6 outCond 'in.taskCounter < in.to' #txt
cs0 f6 type ch.ivy.add.portalkit.task.TaskGenerationDataRange #txt
cs0 f6 var in1 #txt
cs0 f6 208 358 208 418 #arcP
cs0 f7 expr data #txt
cs0 f7 outCond ivp=="TaskA.ivp" #txt
cs0 f7 208 446 208 483 #arcP
cs0 f8 expr in #txt
cs0 f8 194 344 109 344 #arcP
cs0 f9 actionTable 'out=in;
' #txt
cs0 f9 actionCode 'in.taskCounter += 1;' #txt
cs0 f9 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>counter+1</name>
        <nameStyle>9,7
</nameStyle>
    </language>
</elementInfo>
' #txt
cs0 f9 334 420 36 24 20 -2 #rect
cs0 f9 @|StepIcon #fIcon
cs0 f10 expr data #txt
cs0 f10 outCond ivp=="TaskB.ivp" #txt
cs0 f10 222 432 334 432 #arcP
cs0 f11 expr out #txt
cs0 f11 352 420 222 344 #arcP
cs0 f11 1 352 344 #addKink
cs0 f11 1 0.058532623964912746 0 0 #arcLabel
cs0 f12 actionTable 'out=in;
' #txt
cs0 f12 actionCode 'in.taskCounter = in.from;' #txt
cs0 f12 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>in.taskCounter = in.from</name>
        <nameStyle>24,7
</nameStyle>
    </language>
</elementInfo>
' #txt
cs0 f12 190 276 36 24 20 -2 #rect
cs0 f12 @|StepIcon #fIcon
cs0 f13 expr out #txt
cs0 f13 208 237 208 276 #arcP
cs0 f5 expr out #txt
cs0 f5 208 300 208 330 #arcP
cs0 f14 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>in.taskCounter &lt;= in.to</name>
        <nameStyle>23,7
</nameStyle>
    </language>
</elementInfo>
' #txt
cs0 f14 594 338 28 28 14 0 #rect
cs0 f14 @|AlternativeIcon #fIcon
cs0 f15 595 547 26 26 14 0 #rect
cs0 f15 @|EndIcon #fIcon
cs0 f16 actionTable 'out=in1;
' #txt
cs0 f16 outLinks "TaskA.ivp","TaskB.ivp" #txt
cs0 f16 taskData 'TaskA.EXPRI=2
TaskA.EXROL=Everybody
TaskA.EXTYPE=0
TaskA.PRI=2
TaskA.ROL=SYSTEM
TaskA.SKIP_TASK_LIST=false
TaskA.TYPE=0
TaskB.EXPRI=2
TaskB.EXROL=Everybody
TaskB.EXTYPE=0
TaskB.PRI=2
TaskB.ROL=SYSTEM
TaskB.SKIP_TASK_LIST=false
TaskB.TYPE=0' #txt
cs0 f16 template "" #txt
cs0 f16 594 426 28 28 14 0 #rect
cs0 f16 @|TaskSwitchIcon #fIcon
cs0 f17 actionTable 'out=in;
' #txt
cs0 f17 actionCode 'in.from += 10;
in.taskCounter += 10;' #txt
cs0 f17 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>counter+10
from + 10</name>
        <nameStyle>20,7
</nameStyle>
    </language>
</elementInfo>
' #txt
cs0 f17 734 428 36 24 20 -2 #rect
cs0 f17 @|StepIcon #fIcon
cs0 f18 483 339 26 26 14 0 #rect
cs0 f18 @|EndIcon #fIcon
cs0 f19 outLink splitTaskGeneration.ivp #txt
cs0 f19 inParamDecl '<ch.ivy.add.portalkit.task.TaskGenerationDataRange rangeData> param;' #txt
cs0 f19 inParamTable 'out=param.rangeData;
' #txt
cs0 f19 requestEnabled false #txt
cs0 f19 triggerEnabled true #txt
cs0 f19 callSignature splitTaskGeneration(ch.ivy.add.portalkit.task.TaskGenerationDataRange) #txt
cs0 f19 persist false #txt
cs0 f19 taskData 'TaskTriggered.EXPRI=2
TaskTriggered.EXROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.PRI=2
TaskTriggered.ROL=SYSTEM
TaskTriggered.TYPE=0' #txt
cs0 f19 showInStartList 1 #txt
cs0 f19 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>splitTaskGeneration</name>
        <nameStyle>19,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
cs0 f19 @C|.responsibility Everybody #txt
cs0 f19 595 211 26 26 14 0 #rect
cs0 f19 @|StartRequestIcon #fIcon
cs0 f20 actionTable 'out=in;
' #txt
cs0 f20 actionCode 'in.taskCounter = in.from+10;' #txt
cs0 f20 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>in.taskCounter = in.from+10</name>
        <nameStyle>27,7
</nameStyle>
    </language>
</elementInfo>
' #txt
cs0 f20 590 276 36 24 20 -2 #rect
cs0 f20 @|StepIcon #fIcon
cs0 f21 expr in #txt
cs0 f21 outCond 'in.taskCounter <= in.to' #txt
cs0 f21 type ch.ivy.add.portalkit.task.TaskGenerationDataRange #txt
cs0 f21 var in1 #txt
cs0 f21 608 366 608 426 #arcP
cs0 f23 expr in #txt
cs0 f23 594 352 509 352 #arcP
cs0 f24 expr data #txt
cs0 f24 outCond ivp=="TaskB.ivp" #txt
cs0 f24 622 440 734 440 #arcP
cs0 f25 expr out #txt
cs0 f25 752 428 622 352 #arcP
cs0 f25 1 752 352 #addKink
cs0 f25 1 0.058532623964912746 0 0 #arcLabel
cs0 f26 expr out #txt
cs0 f26 608 237 608 276 #arcP
cs0 f27 expr out #txt
cs0 f27 608 300 608 338 #arcP
cs0 f28 processCall 'Business Processes/createARangeOfTasks:generateTask(ch.ivy.add.portalkit.task.TaskGenerationDataRange)' #txt
cs0 f28 requestActionDecl '<ch.ivy.add.portalkit.task.TaskGenerationDataRange rangeData> param;' #txt
cs0 f28 requestMappingAction 'param.rangeData.from=in.from;
param.rangeData.structuredData=in.structuredData;
param.rangeData.taskCounter=in.taskCounter;
param.rangeData.to=in.to;
' #txt
cs0 f28 responseActionDecl 'ch.ivy.add.portalkit.task.TaskGenerationDataRange out;
' #txt
cs0 f28 responseMappingAction 'out=in;
' #txt
cs0 f28 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>generateTask
from-&gt;counter</name>
        <nameStyle>26,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
cs0 f28 590 484 36 24 20 -2 #rect
cs0 f28 @|TriggerIcon #fIcon
cs0 f29 expr data #txt
cs0 f29 outCond ivp=="TaskA.ivp" #txt
cs0 f29 608 454 608 484 #arcP
cs0 f22 expr out #txt
cs0 f22 608 508 608 547 #arcP
cs0 f30 outLink generateTasks.ivp #txt
cs0 f30 inParamDecl '<Number counter> param;' #txt
cs0 f30 inParamTable 'out.taskCounter=param.counter;
' #txt
cs0 f30 requestEnabled false #txt
cs0 f30 triggerEnabled true #txt
cs0 f30 callSignature generateTasks(Number) #txt
cs0 f30 persist false #txt
cs0 f30 taskData 'TaskTriggered.EXPRI=2
TaskTriggered.EXROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.PRI=2
TaskTriggered.ROL=SYSTEM
TaskTriggered.TYPE=0' #txt
cs0 f30 showInStartList 1 #txt
cs0 f30 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>generateTasks</name>
        <nameStyle>13,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
cs0 f30 @C|.responsibility Everybody #txt
cs0 f30 947 211 26 26 14 0 #rect
cs0 f30 @|StartRequestIcon #fIcon
cs0 f31 947 419 26 26 14 0 #rect
cs0 f31 @|EndIcon #fIcon
cs0 f33 actionTable 'out=in1;
' #txt
cs0 f33 outLinks "TaskA.ivp" #txt
cs0 f33 taskData 'TaskA.DESC=Sample description <%\=in1.taskCounter%>
TaskA.EXP=''P2h3m''
TaskA.EXPRI=2
TaskA.EXROL=Everybody
TaskA.EXTYPE=0
TaskA.NAM=Task <%\=in1.taskCounter%>
TaskA.PRI=2
TaskA.ROL=Everybody
TaskA.SKIP_TASK_LIST=false
TaskA.TYPE=0' #txt
cs0 f33 template "" #txt
cs0 f33 947 307 26 26 13 0 #rect
cs0 f33 @|TaskSwitchSimpleIcon #fIcon
cs0 f34 expr out #txt
cs0 f34 type ch.ivy.add.portalkit.task.TaskGenerationDataRange #txt
cs0 f34 var in1 #txt
cs0 f34 960 237 960 307 #arcP
cs0 f32 expr data #txt
cs0 f32 outCond ivp=="TaskA.ivp" #txt
cs0 f32 960 333 960 419 #arcP
>Proto cs0 .type ch.ivy.add.portalkit.task.TaskGenerationDataRange #txt
>Proto cs0 .processKind NORMAL #txt
>Proto cs0 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language/>
</elementInfo>
' #txt
>Proto cs0 0 0 32 24 18 0 #rect
>Proto cs0 @|BIcon #fIcon
cs0 f1 out f6 tail #connect
cs0 f6 head f2 in #connect
cs0 f2 out f7 tail #connect
cs0 f7 head f4 mainIn #connect
cs0 f1 out f8 tail #connect
cs0 f8 head f3 mainIn #connect
cs0 f2 out f10 tail #connect
cs0 f10 head f9 mainIn #connect
cs0 f9 mainOut f11 tail #connect
cs0 f11 head f1 in #connect
cs0 f0 mainOut f13 tail #connect
cs0 f13 head f12 mainIn #connect
cs0 f12 mainOut f5 tail #connect
cs0 f5 head f1 in #connect
cs0 f14 out f21 tail #connect
cs0 f21 head f16 in #connect
cs0 f14 out f23 tail #connect
cs0 f23 head f18 mainIn #connect
cs0 f24 head f17 mainIn #connect
cs0 f17 mainOut f25 tail #connect
cs0 f25 head f14 in #connect
cs0 f19 mainOut f26 tail #connect
cs0 f26 head f20 mainIn #connect
cs0 f20 mainOut f27 tail #connect
cs0 f27 head f14 in #connect
cs0 f16 out f29 tail #connect
cs0 f29 head f28 mainIn #connect
cs0 f16 out f24 tail #connect
cs0 f28 mainOut f22 tail #connect
cs0 f22 head f15 mainIn #connect
cs0 f30 mainOut f34 tail #connect
cs0 f34 head f33 in #connect
cs0 f33 out f32 tail #connect
cs0 f32 head f31 mainIn #connect
