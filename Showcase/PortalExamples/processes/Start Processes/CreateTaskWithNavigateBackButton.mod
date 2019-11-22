[Ivy]
16583DA46E986FE1 7.5.0 #module
>Proto >Proto Collection #zClass
Cn0 CreateTaskWithNavigateBackButton Big #zClass
Cn0 B #cInfo
Cn0 #process
Cn0 @TextInP .type .type #zField
Cn0 @TextInP .processKind .processKind #zField
Cn0 @AnnotationInP-0n ai ai #zField
Cn0 @MessageFlowInP-0n messageIn messageIn #zField
Cn0 @MessageFlowOutP-0n messageOut messageOut #zField
Cn0 @TextInP .xml .xml #zField
Cn0 @TextInP .responsibility .responsibility #zField
Cn0 @StartRequest f0 '' #zField
Cn0 @EndTask f1 '' #zField
Cn0 @TaskSwitchSimple f3 '' #zField
Cn0 @TkArc f4 '' #zField
Cn0 @UserDialog f5 '' #zField
Cn0 @PushWFArc f6 '' #zField
Cn0 @PushWFArc f2 '' #zField
Cn0 @InfoButton f7 '' #zField
>Proto Cn0 Cn0 CreateTaskWithNavigateBackButton #zField
Cn0 f0 outLink CreateTaskWithNavigateBackButton.ivp #txt
Cn0 f0 inParamDecl '<> param;' #txt
Cn0 f0 requestEnabled true #txt
Cn0 f0 triggerEnabled false #txt
Cn0 f0 callSignature CreateTaskWithNavigateBackButton() #txt
Cn0 f0 persist false #txt
Cn0 f0 startName 'Create task with navigate back button' #txt
Cn0 f0 taskData 'TaskTriggered.EXPRI=2
TaskTriggered.EXROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.PRI=2
TaskTriggered.ROL=Everybody
TaskTriggered.TYPE=0' #txt
Cn0 f0 caseData businessCase.attach=true #txt
Cn0 f0 showInStartList 1 #txt
Cn0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>CreateTaskWithNavigateBackButton.ivp</name>
    </language>
</elementInfo>
' #txt
Cn0 f0 @C|.responsibility Everybody #txt
Cn0 f0 169 49 30 30 -108 17 #rect
Cn0 f0 @|StartRequestIcon #fIcon
Cn0 f1 681 49 30 30 0 15 #rect
Cn0 f1 @|EndIcon #fIcon
Cn0 f3 actionTable 'out=in1;
' #txt
Cn0 f3 outLinks "TaskA.ivp" #txt
Cn0 f3 taskData 'TaskA.DESC=This is an example of a task with a button which use the Navigate back feature of Portal
TaskA.EXPRI=2
TaskA.EXROL=Everybody
TaskA.EXTYPE=0
TaskA.NAM=Navigate back example
TaskA.PRI=2
TaskA.ROL=Everybody
TaskA.SKIP_TASK_LIST=false
TaskA.TYPE=0
TaskA.customFields.STRING.embedInFrame="false"' #txt
Cn0 f3 template "" #txt
Cn0 f3 337 49 30 30 0 16 #rect
Cn0 f3 @|TaskSwitchSimpleIcon #fIcon
Cn0 f4 expr out #txt
Cn0 f4 type ch.ivyteam.ivy.project.portal.examples.Data #txt
Cn0 f4 var in1 #txt
Cn0 f4 199 64 337 64 #arcP
Cn0 f5 dialogId ch.ivyteam.ivy.project.portal.examples.NavigateBackTaskPage #txt
Cn0 f5 startMethod start() #txt
Cn0 f5 requestActionDecl '<> param;' #txt
Cn0 f5 responseActionDecl 'ch.ivyteam.ivy.project.portal.examples.Data out;
' #txt
Cn0 f5 responseMappingAction 'out=in;
' #txt
Cn0 f5 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Page with navigate back button</name>
    </language>
</elementInfo>
' #txt
Cn0 f5 432 42 176 44 -84 -8 #rect
Cn0 f5 @|UserDialogIcon #fIcon
Cn0 f6 expr data #txt
Cn0 f6 outCond ivp=="TaskA.ivp" #txt
Cn0 f6 367 64 432 64 #arcP
Cn0 f2 expr out #txt
Cn0 f2 608 64 681 64 #arcP
Cn0 f7 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>This example demonstrate the Navigate back feature of Portal. Go to the logic of NavigateBackTaskPage HTML dialog to see how this is done.

HOW TO USE THIS EXAMPLE
Start CreateTaskWithNavigateBackButton.ivp. This process will create a "Navigate back example" task.
In the task UI, if you click on the "Back" button, you will be redirected to the task list where you start this task from.
</name>
        <nameStyle>139,7
249,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cn0 f7 64 162 784 108 -386 -48 #rect
Cn0 f7 @|IBIcon #fIcon
>Proto Cn0 .type ch.ivyteam.ivy.project.portal.examples.Data #txt
>Proto Cn0 .processKind NORMAL #txt
>Proto Cn0 0 0 32 24 18 0 #rect
>Proto Cn0 @|BIcon #fIcon
Cn0 f0 mainOut f4 tail #connect
Cn0 f4 head f3 in #connect
Cn0 f3 out f6 tail #connect
Cn0 f6 head f5 mainIn #connect
Cn0 f5 mainOut f2 tail #connect
Cn0 f2 head f1 mainIn #connect
