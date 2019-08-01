[Ivy]
16C47279A531881D 3.20 #module
>Proto >Proto Collection #zClass
Ck0 CreateNoteHistoryHasExpiredTask Big #zClass
Ck0 B #cInfo
Ck0 #process
Ck0 @TextInP .resExport .resExport #zField
Ck0 @TextInP .type .type #zField
Ck0 @TextInP .processKind .processKind #zField
Ck0 @AnnotationInP-0n ai ai #zField
Ck0 @MessageFlowInP-0n messageIn messageIn #zField
Ck0 @MessageFlowOutP-0n messageOut messageOut #zField
Ck0 @TextInP .xml .xml #zField
Ck0 @TextInP .responsibility .responsibility #zField
Ck0 @StartRequest f0 '' #zField
Ck0 @EndTask f1 '' #zField
Ck0 @TaskSwitch f3 '' #zField
Ck0 @TkArc f4 '' #zField
Ck0 @PushWFArc f2 '' #zField
Ck0 @EndTask f5 '' #zField
Ck0 @PushWFArc f6 '' #zField
>Proto Ck0 Ck0 CreateNoteHistoryHasExpiredTask #zField
Ck0 f0 outLink createHistoryHasExpiredTask.ivp #txt
Ck0 f0 type portalKit_test.CreateNoteHistoryHasExpiredTaskData #txt
Ck0 f0 inParamDecl '<> param;' #txt
Ck0 f0 actionDecl 'portalKit_test.CreateNoteHistoryHasExpiredTaskData out;
' #txt
Ck0 f0 guid 16C47279B8E486C9 #txt
Ck0 f0 requestEnabled true #txt
Ck0 f0 triggerEnabled false #txt
Ck0 f0 callSignature createHistoryHasExpiredTask() #txt
Ck0 f0 persist false #txt
Ck0 f0 startName 'Create note history which has expired task' #txt
Ck0 f0 startDescription 'Create note history which has expired task' #txt
Ck0 f0 taskData 'TaskTriggered.ROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.EXPRI=2
TaskTriggered.TYPE=0
TaskTriggered.PRI=2
TaskTriggered.EXROL=Everybody' #txt
Ck0 f0 caseData businessCase.attach=true #txt
Ck0 f0 showInStartList 1 #txt
Ck0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>createHistoryHasExpiredTask</name>
        <nameStyle>27,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ck0 f0 @C|.responsibility Everybody #txt
Ck0 f0 81 49 30 30 -81 17 #rect
Ck0 f0 @|StartRequestIcon #fIcon
Ck0 f1 type portalKit_test.CreateNoteHistoryHasExpiredTaskData #txt
Ck0 f1 497 49 30 30 0 15 #rect
Ck0 f1 @|EndIcon #fIcon
Ck0 f3 actionDecl 'portalKit_test.CreateNoteHistoryHasExpiredTaskData out;
' #txt
Ck0 f3 actionTable 'out=in1;
' #txt
Ck0 f3 outTypes "portalKit_test.CreateNoteHistoryHasExpiredTaskData","portalKit_test.CreateNoteHistoryHasExpiredTaskData" #txt
Ck0 f3 outLinks "TaskA.ivp","TaskB.ivp" #txt
Ck0 f3 caseData 'case.name=Test note history destroys expired task ' #txt
Ck0 f3 taskData 'TaskA.EXP=new Duration(0,0,15,0,0,0)
TaskA.EXPRI=2
TaskA.EXTYPE=-1
TaskA.NAM=Task has long duration
TaskA.PRI=2
TaskA.ROL=Everybody
TaskA.SKIP_TASK_LIST=false
TaskA.TYPE=0
TaskB.EXP=new Duration(0,0,0,0,0,30)
TaskB.EXPRI=1
TaskB.EXTYPE=-1
TaskB.NAM=Task is expired in 30s
TaskB.PRI=2
TaskB.ROL=Everybody
TaskB.SKIP_TASK_LIST=false
TaskB.TYPE=0' #txt
Ck0 f3 type portalKit_test.CreateNoteHistoryHasExpiredTaskData #txt
Ck0 f3 template "" #txt
Ck0 f3 304 48 32 32 0 16 #rect
Ck0 f3 @|TaskSwitchIcon #fIcon
Ck0 f4 expr out #txt
Ck0 f4 type portalKit_test.CreateNoteHistoryHasExpiredTaskData #txt
Ck0 f4 var in1 #txt
Ck0 f4 111 64 304 64 #arcP
Ck0 f2 expr data #txt
Ck0 f2 outCond ivp=="TaskA.ivp" #txt
Ck0 f2 336 64 497 64 #arcP
Ck0 f5 type portalKit_test.CreateNoteHistoryHasExpiredTaskData #txt
Ck0 f5 497 113 30 30 0 15 #rect
Ck0 f5 @|EndIcon #fIcon
Ck0 f6 expr data #txt
Ck0 f6 outCond ivp=="TaskB.ivp" #txt
Ck0 f6 320 80 497 128 #arcP
Ck0 f6 1 320 128 #addKink
Ck0 f6 1 0.30890317290673075 0 0 #arcLabel
>Proto Ck0 .type portalKit_test.CreateNoteHistoryHasExpiredTaskData #txt
>Proto Ck0 .processKind NORMAL #txt
>Proto Ck0 0 0 32 24 18 0 #rect
>Proto Ck0 @|BIcon #fIcon
Ck0 f0 mainOut f4 tail #connect
Ck0 f4 head f3 in #connect
Ck0 f3 out f2 tail #connect
Ck0 f2 head f1 mainIn #connect
Ck0 f3 out f6 tail #connect
Ck0 f6 head f5 mainIn #connect
