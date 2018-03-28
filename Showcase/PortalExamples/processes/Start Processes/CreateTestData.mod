[Ivy]
162511D2577DBA88 3.23 #module
>Proto >Proto Collection #zClass
Cs0 CreateTestData Big #zClass
Cs0 B #cInfo
Cs0 #process
Cs0 @TextInP .resExport .resExport #zField
Cs0 @TextInP .type .type #zField
Cs0 @TextInP .processKind .processKind #zField
Cs0 @AnnotationInP-0n ai ai #zField
Cs0 @MessageFlowInP-0n messageIn messageIn #zField
Cs0 @MessageFlowOutP-0n messageOut messageOut #zField
Cs0 @TextInP .xml .xml #zField
Cs0 @TextInP .responsibility .responsibility #zField
Cs0 @StartRequest f0 '' #zField
Cs0 @EndTask f1 '' #zField
Cs0 @EndTask f4 '' #zField
Cs0 @EndTask f5 '' #zField
Cs0 @TaskSwitch f7 '' #zField
Cs0 @TkArc f2 '' #zField
Cs0 @PushWFArc f3 '' #zField
Cs0 @PushWFArc f6 '' #zField
Cs0 @PushWFArc f8 '' #zField
Cs0 @InfoButton f13 '' #zField
>Proto Cs0 Cs0 CreateTestData #zField
Cs0 f0 outLink createTasksForTaskListCustomization.ivp #txt
Cs0 f0 type ch.ivyteam.ivy.project.portal.examples.Data #txt
Cs0 f0 inParamDecl '<> param;' #txt
Cs0 f0 actionDecl 'ch.ivyteam.ivy.project.portal.examples.Data out;
' #txt
Cs0 f0 guid 162511D258ADC37F #txt
Cs0 f0 requestEnabled true #txt
Cs0 f0 triggerEnabled false #txt
Cs0 f0 callSignature createTasksForTaskListCustomization() #txt
Cs0 f0 persist false #txt
Cs0 f0 startName 'Create Tasks For Task List Customization' #txt
Cs0 f0 startDescription 'Create 3 tasks, each task has customVarcharField5 and customTimestampField1' #txt
Cs0 f0 taskData 'TaskTriggered.ROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.EXPRI=2
TaskTriggered.TYPE=0
TaskTriggered.PRI=2
TaskTriggered.EXROL=Everybody' #txt
Cs0 f0 caseData businessCase.attach=true #txt
Cs0 f0 showInStartList 1 #txt
Cs0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>createTasksForTaskListCustomization.ivp</name>
        <nameStyle>39,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f0 @C|.responsibility Everybody #txt
Cs0 f0 81 81 30 30 -80 20 #rect
Cs0 f0 @|StartRequestIcon #fIcon
Cs0 f1 type ch.ivyteam.ivy.project.portal.examples.Data #txt
Cs0 f1 403 83 30 30 0 15 #rect
Cs0 f1 @|EndIcon #fIcon
Cs0 f4 type ch.ivyteam.ivy.project.portal.examples.Data #txt
Cs0 f4 403 19 30 30 0 15 #rect
Cs0 f4 @|EndIcon #fIcon
Cs0 f5 type ch.ivyteam.ivy.project.portal.examples.Data #txt
Cs0 f5 403 147 30 30 0 15 #rect
Cs0 f5 @|EndIcon #fIcon
Cs0 f7 actionDecl 'ch.ivyteam.ivy.project.portal.examples.Data out;
' #txt
Cs0 f7 actionTable 'out=in1;
' #txt
Cs0 f7 outTypes "ch.ivyteam.ivy.project.portal.examples.Data","ch.ivyteam.ivy.project.portal.examples.Data","ch.ivyteam.ivy.project.portal.examples.Data" #txt
Cs0 f7 outLinks "TaskA.ivp","TaskB.ivp","TaskC.ivp" #txt
Cs0 f7 caseData 'case.name=Leave Request
case.category=LeaveRequest' #txt
Cs0 f7 taskData 'TaskA.CATEGORY=Drink
TaskA.DESC=Milk tea for 4 people
TaskA.EXP=new Duration("3H")
TaskA.EXPRI=2
TaskA.EXROL=Everybody
TaskA.EXTYPE=0
TaskA.NAM=Milk Tea Order
TaskA.PRI=2
TaskA.ROL="demo"
TaskA.SKIP_TASK_LIST=false
TaskA.TYPE=3
TaskA.customFields.timestamp.1=new DateTime()
TaskA.customFields.varchar.1="Exterior"
TaskA.customFields.varchar.5="Sarah"
TaskB.CATEGORY=Drink
TaskB.DESC=Beer for a big company with 10.000 people
TaskB.EXP=new Duration("1D")
TaskB.EXPRI=2
TaskB.EXROL=Everybody
TaskB.EXTYPE=0
TaskB.NAM=Beer Order
TaskB.PRI=1
TaskB.ROL=Everybody
TaskB.SKIP_TASK_LIST=false
TaskB.TYPE=0
TaskB.customFields.timestamp.1=new DateTime("2080-03-23 11\:11\:11")
TaskB.customFields.varchar.1="Interior"
TaskB.customFields.varchar.5="Julie"
TaskC.CATEGORY=Food
TaskC.DESC=Pizza for 10 people
TaskC.EXP=new Duration("2D")
TaskC.EXPRI=2
TaskC.EXROL=Everybody
TaskC.EXTYPE=0
TaskC.NAM=Pizza Order
TaskC.PRI=3
TaskC.ROL=Everybody
TaskC.SKIP_TASK_LIST=false
TaskC.TYPE=0
TaskC.customFields.timestamp.1=new DateTime()
TaskC.customFields.varchar.1="Interior"
TaskC.customFields.varchar.5="Florian"' #txt
Cs0 f7 type ch.ivyteam.ivy.project.portal.examples.Data #txt
Cs0 f7 template "" #txt
Cs0 f7 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Create tasks</name>
        <nameStyle>12,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f7 243 82 28 28 9 -21 #rect
Cs0 f7 @|TaskSwitchIcon #fIcon
Cs0 f2 expr out #txt
Cs0 f2 type ch.ivyteam.ivy.project.portal.examples.Data #txt
Cs0 f2 var in1 #txt
Cs0 f2 111 96 243 96 #arcP
Cs0 f3 expr data #txt
Cs0 f3 outCond ivp=="TaskA.ivp" #txt
Cs0 f3 271 96 403 97 #arcP
Cs0 f6 expr data #txt
Cs0 f6 outCond ivp=="TaskB.ivp" #txt
Cs0 f6 257 82 403 34 #arcP
Cs0 f6 1 257 34 #addKink
Cs0 f6 1 0.2820126980511695 0 0 #arcLabel
Cs0 f8 expr data #txt
Cs0 f8 outCond ivp=="TaskC.ivp" #txt
Cs0 f8 257 110 403 162 #arcP
Cs0 f8 1 257 162 #addKink
Cs0 f8 1 0.2517837545571593 0 0 #arcLabel
Cs0 f13 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>HOW TO RUN THIS EXAMPLE:

Run createTasksForTaskListCustomization.ivp process
This process will create 3 tasks, each task has filled customVarcharField5 and customTimestampField1,
 we will use these fields as custom fields in Task Widget.</name>
        <nameStyle>238,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f13 632 66 592 92 -286 -40 #rect
Cs0 f13 @|IBIcon #fIcon
>Proto Cs0 .type ch.ivyteam.ivy.project.portal.examples.Data #txt
>Proto Cs0 .processKind NORMAL #txt
>Proto Cs0 0 0 32 24 18 0 #rect
>Proto Cs0 @|BIcon #fIcon
Cs0 f0 mainOut f2 tail #connect
Cs0 f2 head f7 in #connect
Cs0 f7 out f3 tail #connect
Cs0 f3 head f1 mainIn #connect
Cs0 f7 out f6 tail #connect
Cs0 f6 head f4 mainIn #connect
Cs0 f7 out f8 tail #connect
Cs0 f8 head f5 mainIn #connect
