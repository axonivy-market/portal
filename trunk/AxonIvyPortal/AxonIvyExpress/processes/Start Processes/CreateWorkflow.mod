[Ivy]
[>Created: Wed May 31 09:20:21 ICT 2017]
15798655494F25E1 3.20 #module
>Proto >Proto Collection #zClass
cw0 CreateWorkflow Big #zClass
cw0 B #cInfo
cw0 #process
cw0 @TextInP .resExport .resExport #zField
cw0 @TextInP .type .type #zField
cw0 @TextInP .processKind .processKind #zField
cw0 @AnnotationInP-0n ai ai #zField
cw0 @MessageFlowInP-0n messageIn messageIn #zField
cw0 @MessageFlowOutP-0n messageOut messageOut #zField
cw0 @TextInP .xml .xml #zField
cw0 @TextInP .responsibility .responsibility #zField
cw0 @StartRequest f0 '' #zField
cw0 @EndTask f1 '' #zField
cw0 @CallSub f3 '' #zField
cw0 @PushWFArc f4 '' #zField
cw0 @TaskSwitchSimple f5 '' #zField
cw0 @TkArc f6 '' #zField
cw0 @GridStep f7 '' #zField
cw0 @PushWFArc f8 '' #zField
cw0 @PushWFArc f2 '' #zField
>Proto cw0 cw0 CreateWorkflow #zField
cw0 f0 outLink AxonIvyExpressWF.ivp #txt
cw0 f0 type gawfs.createWorkflowData #txt
cw0 f0 inParamDecl '<> param;' #txt
cw0 f0 actionDecl 'gawfs.createWorkflowData out;
' #txt
cw0 f0 guid 15798655499443C8 #txt
cw0 f0 requestEnabled true #txt
cw0 f0 triggerEnabled false #txt
cw0 f0 callSignature AxonIvyExpressWF() #txt
cw0 f0 persist false #txt
cw0 f0 startName 'Create Axon.ivy Express Workflow' #txt
cw0 f0 taskData 'TaskTriggered.ROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.EXPRI=2
TaskTriggered.TYPE=0
TaskTriggered.PRI=2
TaskTriggered.DESC=Bitte definieren Sie die Workfloweigenschaften\!
TaskTriggered.NAM=Workflow Eigenschaften
TaskTriggered.EXROL=Everybody' #txt
cw0 f0 caseData 'case.name=Neuen Prozess erstellen
case.description=Erstellen eines neuen Prozesses\!
customFields.varchar.1="CREATE_WF"
businessCase.attach=false' #txt
cw0 f0 showInStartList 0 #txt
cw0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>AxonIvyExpressWF.ivp</name>
        <nameStyle>20,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
cw0 f0 @C|.responsibility Everybody #txt
cw0 f0 81 49 30 30 -60 17 #rect
cw0 f0 @|StartRequestIcon #fIcon
cw0 f1 type gawfs.createWorkflowData #txt
cw0 f1 657 49 30 30 0 15 #rect
cw0 f1 @|EndIcon #fIcon
cw0 f3 type gawfs.createWorkflowData #txt
cw0 f3 processCall 'Functional Processes/editWorkflow:newWorkflow()' #txt
cw0 f3 doCall true #txt
cw0 f3 requestActionDecl '<> param;
' #txt
cw0 f3 responseActionDecl 'gawfs.createWorkflowData out;
' #txt
cw0 f3 responseMappingAction 'out=in;
' #txt
cw0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>editWorkflow</name>
        <nameStyle>12,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
cw0 f3 216 42 112 44 -35 -8 #rect
cw0 f3 @|CallSubIcon #fIcon
cw0 f4 expr out #txt
cw0 f4 111 64 216 64 #arcP
cw0 f5 actionDecl 'gawfs.createWorkflowData out;
' #txt
cw0 f5 actionTable 'out=in1;
' #txt
cw0 f5 outTypes "gawfs.createWorkflowData" #txt
cw0 f5 outLinks "TaskA.ivp" #txt
cw0 f5 taskData 'TaskA.EXPRI=2
TaskA.EXROL=Everybody
TaskA.EXTYPE=0
TaskA.NAM=New Process saved\!
TaskA.PRI=2
TaskA.ROL=SYSTEM
TaskA.SKIP_TASK_LIST=true
TaskA.TYPE=0' #txt
cw0 f5 type gawfs.createWorkflowData #txt
cw0 f5 template "" #txt
cw0 f5 401 49 30 30 0 16 #rect
cw0 f5 @|TaskSwitchSimpleIcon #fIcon
cw0 f6 expr out #txt
cw0 f6 type gawfs.createWorkflowData #txt
cw0 f6 var in1 #txt
cw0 f6 328 64 401 64 #arcP
cw0 f7 actionDecl 'gawfs.createWorkflowData out;
' #txt
cw0 f7 actionTable 'out=in;
' #txt
cw0 f7 actionCode Thread.sleep(5000); #txt
cw0 f7 type gawfs.createWorkflowData #txt
cw0 f7 488 42 112 44 0 -8 #rect
cw0 f7 @|StepIcon #fIcon
cw0 f8 expr data #txt
cw0 f8 outCond ivp=="TaskA.ivp" #txt
cw0 f8 431 64 488 64 #arcP
cw0 f2 expr out #txt
cw0 f2 600 64 657 64 #arcP
>Proto cw0 .type gawfs.createWorkflowData #txt
>Proto cw0 .processKind NORMAL #txt
>Proto cw0 0 0 32 24 18 0 #rect
>Proto cw0 @|BIcon #fIcon
cw0 f0 mainOut f4 tail #connect
cw0 f4 head f3 mainIn #connect
cw0 f3 mainOut f6 tail #connect
cw0 f6 head f5 in #connect
cw0 f5 out f8 tail #connect
cw0 f8 head f7 mainIn #connect
cw0 f7 mainOut f2 tail #connect
cw0 f2 head f1 mainIn #connect
