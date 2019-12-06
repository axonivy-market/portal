[Ivy]
16EDA328C4D5F6DB 3.20 #module
>Proto >Proto Collection #zClass
He0 HiddenCase Big #zClass
He0 B #cInfo
He0 #process
He0 @TextInP .resExport .resExport #zField
He0 @TextInP .type .type #zField
He0 @TextInP .processKind .processKind #zField
He0 @AnnotationInP-0n ai ai #zField
He0 @MessageFlowInP-0n messageIn messageIn #zField
He0 @MessageFlowOutP-0n messageOut messageOut #zField
He0 @TextInP .xml .xml #zField
He0 @TextInP .responsibility .responsibility #zField
He0 @StartRequest f0 '' #zField
He0 @EndTask f1 '' #zField
He0 @TaskSwitch f3 '' #zField
He0 @TkArc f4 '' #zField
He0 @GridStep f5 '' #zField
He0 @PushWFArc f6 '' #zField
He0 @PushWFArc f2 '' #zField
>Proto He0 He0 HiddenCase #zField
He0 f0 outLink start.ivp #txt
He0 f0 type internaltest.Data #txt
He0 f0 inParamDecl '<> param;' #txt
He0 f0 actionDecl 'internaltest.Data out;
' #txt
He0 f0 guid 16EDA328C50C16E4 #txt
He0 f0 requestEnabled true #txt
He0 f0 triggerEnabled false #txt
He0 f0 callSignature start() #txt
He0 f0 persist false #txt
He0 f0 startName 'Hidden case' #txt
He0 f0 taskData 'TaskTriggered.ROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.EXPRI=2
TaskTriggered.TYPE=0
TaskTriggered.PRI=2
TaskTriggered.EXROL=Everybody' #txt
He0 f0 caseData 'case.name=Hidden case
businessCase.attach=true' #txt
He0 f0 showInStartList 1 #txt
He0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start.ivp</name>
        <nameStyle>9,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
He0 f0 @C|.responsibility Everybody #txt
He0 f0 81 49 30 30 -21 17 #rect
He0 f0 @|StartRequestIcon #fIcon
He0 f1 type internaltest.Data #txt
He0 f1 497 49 30 30 0 15 #rect
He0 f1 @|EndIcon #fIcon
He0 f3 actionDecl 'internaltest.Data out;
' #txt
He0 f3 actionTable 'out=in1;
' #txt
He0 f3 outTypes "internaltest.Data" #txt
He0 f3 outLinks "TaskA.ivp" #txt
He0 f3 taskData 'TaskA.EXPRI=2
TaskA.EXROL=Everybody
TaskA.EXTYPE=0
TaskA.NAM=Task in Hidden Case
TaskA.PRI=2
TaskA.ROL=Everybody
TaskA.SKIP_TASK_LIST=true
TaskA.TYPE=0' #txt
He0 f3 type internaltest.Data #txt
He0 f3 template "" #txt
He0 f3 240 48 32 32 0 16 #rect
He0 f3 @|TaskSwitchIcon #fIcon
He0 f4 expr out #txt
He0 f4 type internaltest.Data #txt
He0 f4 var in1 #txt
He0 f4 111 64 240 64 #arcP
He0 f5 actionDecl 'internaltest.Data out;
' #txt
He0 f5 actionTable 'out=in;
' #txt
He0 f5 actionCode 'import ch.ivy.addon.portalkit.util.CaseUtils;
CaseUtils.setHidePropertyToHideInPortal(ivy.case);' #txt
He0 f5 type internaltest.Data #txt
He0 f5 328 42 112 44 0 -8 #rect
He0 f5 @|StepIcon #fIcon
He0 f6 expr data #txt
He0 f6 outCond ivp=="TaskA.ivp" #txt
He0 f6 272 64 328 64 #arcP
He0 f2 expr out #txt
He0 f2 440 64 497 64 #arcP
>Proto He0 .type internaltest.Data #txt
>Proto He0 .processKind NORMAL #txt
>Proto He0 0 0 32 24 18 0 #rect
>Proto He0 @|BIcon #fIcon
He0 f0 mainOut f4 tail #connect
He0 f4 head f3 in #connect
He0 f3 out f6 tail #connect
He0 f6 head f5 mainIn #connect
He0 f5 mainOut f2 tail #connect
He0 f2 head f1 mainIn #connect
