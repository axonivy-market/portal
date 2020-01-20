[Ivy]
16F6A8B4F0A715CB 3.20 #module
>Proto >Proto Collection #zClass
Gl0 GlobalGrowlSkipTaskList Big #zClass
Gl0 B #cInfo
Gl0 #process
Gl0 @TextInP .resExport .resExport #zField
Gl0 @TextInP .type .type #zField
Gl0 @TextInP .processKind .processKind #zField
Gl0 @AnnotationInP-0n ai ai #zField
Gl0 @MessageFlowInP-0n messageIn messageIn #zField
Gl0 @MessageFlowOutP-0n messageOut messageOut #zField
Gl0 @TextInP .xml .xml #zField
Gl0 @TextInP .responsibility .responsibility #zField
Gl0 @StartRequest f0 '' #zField
Gl0 @EndTask f1 '' #zField
Gl0 @TaskSwitch f3 '' #zField
Gl0 @TkArc f4 '' #zField
Gl0 @PushWFArc f2 '' #zField
Gl0 @PushWFArc f6 '' #zField
Gl0 @RichDialog f5 '' #zField
>Proto Gl0 Gl0 GlobalGrowlSkipTaskList #zField
Gl0 f0 outLink start.ivp #txt
Gl0 f0 type ch.ivy.addon.portal.generic.Data #txt
Gl0 f0 inParamDecl '<> param;' #txt
Gl0 f0 actionDecl 'ch.ivy.addon.portal.generic.Data out;
' #txt
Gl0 f0 guid 16A8BFE4316B13AC #txt
Gl0 f0 requestEnabled true #txt
Gl0 f0 triggerEnabled false #txt
Gl0 f0 callSignature start() #txt
Gl0 f0 persist false #txt
Gl0 f0 startName 'Global Growl Skip Task List' #txt
Gl0 f0 taskData 'TaskTriggered.ROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.EXPRI=2
TaskTriggered.TYPE=0
TaskTriggered.PRI=2
TaskTriggered.NAM=Start\: Global Growl Skip Task List
TaskTriggered.EXROL=Everybody' #txt
Gl0 f0 caseData 'case.name=Global Growl
businessCase.attach=true' #txt
Gl0 f0 showInStartList 1 #txt
Gl0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start.ivp</name>
        <nameStyle>9,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Gl0 f0 @C|.responsibility Everybody #txt
Gl0 f0 81 49 30 30 -21 17 #rect
Gl0 f0 @|StartRequestIcon #fIcon
Gl0 f1 type ch.ivy.addon.portal.generic.Data #txt
Gl0 f1 433 49 30 30 0 15 #rect
Gl0 f1 @|EndIcon #fIcon
Gl0 f3 actionDecl 'ch.ivy.addon.portal.generic.Data out;
' #txt
Gl0 f3 actionTable 'out=in1;
' #txt
Gl0 f3 outTypes "ch.ivy.addon.portal.generic.Data" #txt
Gl0 f3 outLinks "TaskA.ivp" #txt
Gl0 f3 taskData 'TaskA.EXPRI=2
TaskA.EXROL=Everybody
TaskA.EXTYPE=0
TaskA.NAM=Global Growl Skip Task List
TaskA.PRI=2
TaskA.ROL=Everybody
TaskA.SKIP_TASK_LIST=true
TaskA.TYPE=0' #txt
Gl0 f3 type ch.ivy.addon.portal.generic.Data #txt
Gl0 f3 template "" #txt
Gl0 f3 176 48 32 32 0 16 #rect
Gl0 f3 @|TaskSwitchIcon #fIcon
Gl0 f4 expr out #txt
Gl0 f4 type ch.ivy.addon.portal.generic.Data #txt
Gl0 f4 var in1 #txt
Gl0 f4 111 64 176 64 #arcP
Gl0 f2 expr out #txt
Gl0 f2 376 64 433 64 #arcP
Gl0 f6 expr data #txt
Gl0 f6 outCond ivp=="TaskA.ivp" #txt
Gl0 f6 208 64 264 64 #arcP
Gl0 f5 targetWindow NEW #txt
Gl0 f5 targetDisplay TOP #txt
Gl0 f5 richDialogId internaltest.TaskForm #txt
Gl0 f5 startMethod start() #txt
Gl0 f5 type ch.ivy.addon.portal.generic.Data #txt
Gl0 f5 requestActionDecl '<> param;' #txt
Gl0 f5 responseActionDecl 'ch.ivy.addon.portal.generic.Data out;
' #txt
Gl0 f5 responseMappingAction 'out=in;
' #txt
Gl0 f5 isAsynch false #txt
Gl0 f5 isInnerRd false #txt
Gl0 f5 userContext '* ' #txt
Gl0 f5 264 42 112 44 0 -8 #rect
Gl0 f5 @|RichDialogIcon #fIcon
>Proto Gl0 .type ch.ivy.addon.portal.generic.Data #txt
>Proto Gl0 .processKind NORMAL #txt
>Proto Gl0 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language/>
</elementInfo>
' #txt
>Proto Gl0 0 0 32 24 18 0 #rect
>Proto Gl0 @|BIcon #fIcon
Gl0 f0 mainOut f4 tail #connect
Gl0 f4 head f3 in #connect
Gl0 f3 out f6 tail #connect
Gl0 f6 head f5 mainIn #connect
Gl0 f5 mainOut f2 tail #connect
Gl0 f2 head f1 mainIn #connect
