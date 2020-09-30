[Ivy]
16FA8B451814E32A 9.2.0 #module
>Proto >Proto Collection #zClass
Gt0 GlobalGrowlSkipTaskList Big #zClass
Gt0 B #cInfo
Gt0 #process
Gt0 @TextInP .type .type #zField
Gt0 @TextInP .processKind .processKind #zField
Gt0 @TextInP .xml .xml #zField
Gt0 @TextInP .responsibility .responsibility #zField
Gt0 @StartRequest f0 '' #zField
Gt0 @EndTask f1 '' #zField
Gt0 @TaskSwitch f3 '' #zField
Gt0 @TkArc f4 '' #zField
Gt0 @UserDialog f5 '' #zField
Gt0 @PushWFArc f6 '' #zField
Gt0 @PushWFArc f2 '' #zField
>Proto Gt0 Gt0 GlobalGrowlSkipTaskList #zField
Gt0 f0 outLink start.ivp #txt
Gt0 f0 inParamDecl '<> param;' #txt
Gt0 f0 requestEnabled true #txt
Gt0 f0 triggerEnabled false #txt
Gt0 f0 callSignature start() #txt
Gt0 f0 startName 'Global Growl Skip Task List' #txt
Gt0 f0 taskData 'TaskTriggered.NAM=Start\: Global Growl Skip Task List' #txt
Gt0 f0 caseData businessCase.attach=true #txt
Gt0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start.ivp</name>
    </language>
</elementInfo>
' #txt
Gt0 f0 @C|.responsibility Everybody #txt
Gt0 f0 81 49 30 30 -21 17 #rect
Gt0 f0 @|StartRequestIcon #fIcon
Gt0 f1 433 49 30 30 0 15 #rect
Gt0 f1 @|EndIcon #fIcon
Gt0 f3 actionTable 'out=in1;
' #txt
Gt0 f3 outLinks "TaskA.ivp" #txt
Gt0 f3 taskData 'TaskA.NAM=Global Growl Skip Task List
TaskA.SKIP_TASK_LIST=true
TaskA.customFields.STRING.embedInFrame="false"' #txt
Gt0 f3 176 48 32 32 0 16 #rect
Gt0 f3 @|TaskSwitchIcon #fIcon
Gt0 f4 var in1 #txt
Gt0 f4 111 64 176 64 #arcP
Gt0 f5 dialogId com.axonivy.portal.developerexamples.testdata.LeaveRequestForm #txt
Gt0 f5 startMethod start() #txt
Gt0 f5 requestActionDecl '<> param;' #txt
Gt0 f5 responseMappingAction 'out=in;
' #txt
Gt0 f5 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>LeaveRequestForm</name>
    </language>
</elementInfo>
' #txt
Gt0 f5 256 42 128 44 -54 -8 #rect
Gt0 f5 @|UserDialogIcon #fIcon
Gt0 f6 expr data #txt
Gt0 f6 outCond ivp=="TaskA.ivp" #txt
Gt0 f6 208 64 256 64 #arcP
Gt0 f2 384 64 433 64 #arcP
>Proto Gt0 .type com.axonivy.portal.developerexamples.GlobalGrowlSkipTaskListData #txt
>Proto Gt0 .processKind NORMAL #txt
>Proto Gt0 0 0 32 24 18 0 #rect
>Proto Gt0 @|BIcon #fIcon
Gt0 f0 mainOut f4 tail #connect
Gt0 f4 head f3 in #connect
Gt0 f3 out f6 tail #connect
Gt0 f6 head f5 mainIn #connect
Gt0 f5 mainOut f2 tail #connect
Gt0 f2 head f1 mainIn #connect
