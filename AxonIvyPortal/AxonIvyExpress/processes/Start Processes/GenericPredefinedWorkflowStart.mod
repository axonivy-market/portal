[Ivy]
[>Created: Tue Jul 18 17:46:02 ICT 2017]
15797DC22608DA55 3.20 #module
>Proto >Proto Collection #zClass
Gt0 GenericPredefinedWorkflowStart Big #zClass
Gt0 B #cInfo
Gt0 #process
Gt0 @TextInP .resExport .resExport #zField
Gt0 @TextInP .type .type #zField
Gt0 @TextInP .processKind .processKind #zField
Gt0 @AnnotationInP-0n ai ai #zField
Gt0 @MessageFlowInP-0n messageIn messageIn #zField
Gt0 @MessageFlowOutP-0n messageOut messageOut #zField
Gt0 @TextInP .xml .xml #zField
Gt0 @TextInP .responsibility .responsibility #zField
Gt0 @StartRequest f0 '' #zField
Gt0 @CallSub f3 '' #zField
Gt0 @PushWFArc f4 '' #zField
Gt0 @StartRequest f5 '' #zField
Gt0 @StartRequest f6 '' #zField
Gt0 @CallSub f12 '' #zField
Gt0 @CallSub f11 '' #zField
Gt0 @PushWFArc f7 '' #zField
Gt0 @PushWFArc f8 '' #zField
Gt0 @Alternative f10 '' #zField
Gt0 @PushWFArc f15 '' #zField
Gt0 @PushWFArc f16 '' #zField
Gt0 @PushWFArc f2 '' #zField
Gt0 @TaskSwitchSimple f13 '' #zField
Gt0 @GridStep f14 '' #zField
Gt0 @EndTask f17 '' #zField
Gt0 @PushWFArc f18 '' #zField
Gt0 @PushWFArc f19 '' #zField
Gt0 @TkArc f20 '' #zField
>Proto Gt0 Gt0 GenericPredefinedWorkflowStart #zField
Gt0 f0 outLink GenericPredefinedProcessStart.ivp #txt
Gt0 f0 type gawfs.GenericPredefinedWorkflowStartData #txt
Gt0 f0 inParamDecl '<java.lang.String workflowID> param;' #txt
Gt0 f0 inParamTable 'out.workflowID=param.workflowID;
' #txt
Gt0 f0 actionDecl 'gawfs.GenericPredefinedWorkflowStartData out;
' #txt
Gt0 f0 guid 15797DC2264C9526 #txt
Gt0 f0 requestEnabled true #txt
Gt0 f0 triggerEnabled false #txt
Gt0 f0 callSignature GenericPredefinedProcessStart(String) #txt
Gt0 f0 persist false #txt
Gt0 f0 startName 'GenericPredefinedProcessStart (Inputparam: WorkflowID e.g. ?workflowID=24)' #txt
Gt0 f0 taskData 'TaskTriggered.ROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.EXPRI=2
TaskTriggered.TYPE=0
TaskTriggered.PRI=2
TaskTriggered.EXROL=Everybody
TaskTriggered.NAM=Workflow wurde angelegt
TaskTriggered.DESC=Workflow wurde angelegt' #txt
Gt0 f0 caseData 'customFields.varchar.1="EXECUTION_WF"
businessCase.attach=false' #txt
Gt0 f0 showInStartList 0 #txt
Gt0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>GenericPredefinedProcessStart.ivp</name>
        <nameStyle>33,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Gt0 f0 @C|.responsibility Everybody #txt
Gt0 f0 81 49 30 30 -96 17 #rect
Gt0 f0 @|StartRequestIcon #fIcon
Gt0 f3 type gawfs.GenericPredefinedWorkflowStartData #txt
Gt0 f3 processCall 'Functional Processes/executePredefinedWorkflow:call(String)' #txt
Gt0 f3 doCall true #txt
Gt0 f3 requestActionDecl '<java.lang.String workflowID> param;
' #txt
Gt0 f3 requestMappingAction 'param.workflowID=in.workflowID;
' #txt
Gt0 f3 responseActionDecl 'gawfs.GenericPredefinedWorkflowStartData out;
' #txt
Gt0 f3 responseMappingAction 'out=in;
' #txt
Gt0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>executePredefinedWorkflow</name>
        <nameStyle>25,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Gt0 f3 200 42 160 44 -76 -8 #rect
Gt0 f3 @|CallSubIcon #fIcon
Gt0 f4 expr out #txt
Gt0 f4 111 64 200 64 #arcP
Gt0 f5 outLink GenericDeleteProcessStart.ivp #txt
Gt0 f5 type gawfs.GenericPredefinedWorkflowStartData #txt
Gt0 f5 inParamDecl '<java.lang.String workflowID> param;' #txt
Gt0 f5 inParamTable 'out.workflowID=param.workflowID;
' #txt
Gt0 f5 actionDecl 'gawfs.GenericPredefinedWorkflowStartData out;
' #txt
Gt0 f5 guid 1580E4D31F83146E #txt
Gt0 f5 requestEnabled true #txt
Gt0 f5 triggerEnabled false #txt
Gt0 f5 callSignature GenericDeleteProcessStart(String) #txt
Gt0 f5 persist false #txt
Gt0 f5 startName 'GenericDeleteProcessStart(Inputparam: WorkflowID e.g. ?workflowID=24)' #txt
Gt0 f5 startDescription 'Add WorkflowID to the Link like ?workflowID=24' #txt
Gt0 f5 taskData 'TaskTriggered.ROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.EXPRI=2
TaskTriggered.TYPE=0
TaskTriggered.PRI=2
TaskTriggered.EXROL=Everybody' #txt
Gt0 f5 caseData 'case.name=Prozess gel\u00F6scht
case.description=Ein Prozess wurde gel\u00F6scht
customFields.varchar.1="DELETE_WF"
businessCase.attach=false' #txt
Gt0 f5 wfuser 1 #txt
Gt0 f5 showInStartList 0 #txt
Gt0 f5 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>GenericDeleteProcessStart.ivp</name>
        <nameStyle>29,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Gt0 f5 @C|.responsibility Everybody #txt
Gt0 f5 73 145 30 30 -84 17 #rect
Gt0 f5 @|StartRequestIcon #fIcon
Gt0 f6 outLink GenericEditProcessStart.ivp #txt
Gt0 f6 type gawfs.GenericPredefinedWorkflowStartData #txt
Gt0 f6 inParamDecl '<java.lang.String workflowID> param;' #txt
Gt0 f6 inParamTable 'out.workflowID=param.workflowID;
' #txt
Gt0 f6 actionDecl 'gawfs.GenericPredefinedWorkflowStartData out;
' #txt
Gt0 f6 guid 1580E4DAE49F21B4 #txt
Gt0 f6 requestEnabled true #txt
Gt0 f6 triggerEnabled false #txt
Gt0 f6 callSignature GenericEditProcessStart(String) #txt
Gt0 f6 persist false #txt
Gt0 f6 startName 'GenericEditProcessStart(Inputparam: WorkflowID e.g. ?workflowID=24)' #txt
Gt0 f6 startDescription 'Add WorkflowID to the Link like ?workflowID=24' #txt
Gt0 f6 taskData 'TaskTriggered.ROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.EXPRI=2
TaskTriggered.TYPE=0
TaskTriggered.PRI=2
TaskTriggered.EXROL=Everybody
TaskTriggered.NAM=Prozess bearbeitet 
TaskTriggered.DESC=Ein Prozess wurde bearbeitet' #txt
Gt0 f6 caseData 'case.name=Workflow editieren
case.description=Bearbeiten eines exitierenden Workflows
customFields.varchar.1="EDITING_WF"
businessCase.attach=false' #txt
Gt0 f6 wfuser 1 #txt
Gt0 f6 showInStartList 0 #txt
Gt0 f6 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>GenericEditProcessStart.ivp</name>
        <nameStyle>27,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Gt0 f6 @C|.responsibility Everybody #txt
Gt0 f6 73 241 30 30 -76 17 #rect
Gt0 f6 @|StartRequestIcon #fIcon
Gt0 f12 type gawfs.GenericPredefinedWorkflowStartData #txt
Gt0 f12 processCall 'Functional Processes/editWorkflow:editWorkflow(String)' #txt
Gt0 f12 doCall true #txt
Gt0 f12 requestActionDecl '<java.lang.String workflowID> param;
' #txt
Gt0 f12 requestMappingAction 'param.workflowID=in.workflowID;
' #txt
Gt0 f12 responseActionDecl 'gawfs.GenericPredefinedWorkflowStartData out;
' #txt
Gt0 f12 responseMappingAction 'out=in;
' #txt
Gt0 f12 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>edit</name>
        <nameStyle>4,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Gt0 f12 200 232 160 48 -10 -8 #rect
Gt0 f12 @|CallSubIcon #fIcon
Gt0 f11 type gawfs.GenericPredefinedWorkflowStartData #txt
Gt0 f11 processCall 'Functional Processes/deletePredefinedWorkflow:call(String)' #txt
Gt0 f11 doCall true #txt
Gt0 f11 requestActionDecl '<java.lang.String workflowID> param;
' #txt
Gt0 f11 requestMappingAction 'param.workflowID=in.workflowID;
' #txt
Gt0 f11 responseActionDecl 'gawfs.GenericPredefinedWorkflowStartData out;
' #txt
Gt0 f11 responseMappingAction 'out=in;
' #txt
Gt0 f11 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>delete</name>
        <nameStyle>6,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Gt0 f11 204 136 152 48 -17 -8 #rect
Gt0 f11 @|CallSubIcon #fIcon
Gt0 f7 expr out #txt
Gt0 f7 103 160 204 160 #arcP
Gt0 f8 expr out #txt
Gt0 f8 103 256 200 256 #arcP
Gt0 f10 type gawfs.GenericPredefinedWorkflowStartData #txt
Gt0 f10 400 144 32 32 0 16 #rect
Gt0 f10 @|AlternativeIcon #fIcon
Gt0 f15 expr out #txt
Gt0 f15 356 160 400 160 #arcP
Gt0 f15 0 0.6462272645831176 0 0 #arcLabel
Gt0 f16 expr out #txt
Gt0 f16 360 64 416 144 #arcP
Gt0 f16 1 416 64 #addKink
Gt0 f16 0 0.7371524785058673 0 0 #arcLabel
Gt0 f2 expr out #txt
Gt0 f2 360 256 416 176 #arcP
Gt0 f2 1 416 256 #addKink
Gt0 f2 1 0.11186440677966109 0 0 #arcLabel
Gt0 f13 actionDecl 'gawfs.GenericPredefinedWorkflowStartData out;
' #txt
Gt0 f13 actionTable 'out=in1;
' #txt
Gt0 f13 outTypes "gawfs.GenericPredefinedWorkflowStartData" #txt
Gt0 f13 outLinks "TaskA.ivp" #txt
Gt0 f13 taskData 'TaskA.DESC=Prozess beendet.
TaskA.EXPRI=2
TaskA.EXROL=Everybody
TaskA.EXTYPE=0
TaskA.NAM=Prozess beendet.
TaskA.PRI=2
TaskA.ROL=SYSTEM
TaskA.SKIP_TASK_LIST=true
TaskA.TYPE=0' #txt
Gt0 f13 type gawfs.GenericPredefinedWorkflowStartData #txt
Gt0 f13 template "" #txt
Gt0 f13 497 145 30 30 0 16 #rect
Gt0 f13 @|TaskSwitchSimpleIcon #fIcon
Gt0 f14 actionDecl 'gawfs.GenericPredefinedWorkflowStartData out;
' #txt
Gt0 f14 actionTable 'out=in;
' #txt
Gt0 f14 actionCode Thread.sleep(5000); #txt
Gt0 f14 type gawfs.GenericPredefinedWorkflowStartData #txt
Gt0 f14 560 138 112 44 0 -8 #rect
Gt0 f14 @|StepIcon #fIcon
Gt0 f17 type gawfs.GenericPredefinedWorkflowStartData #txt
Gt0 f17 713 145 30 30 0 15 #rect
Gt0 f17 @|EndIcon #fIcon
Gt0 f18 expr data #txt
Gt0 f18 outCond ivp=="TaskA.ivp" #txt
Gt0 f18 527 160 560 160 #arcP
Gt0 f19 expr out #txt
Gt0 f19 672 160 713 160 #arcP
Gt0 f20 expr in #txt
Gt0 f20 type gawfs.GenericPredefinedWorkflowStartData #txt
Gt0 f20 var in1 #txt
Gt0 f20 432 160 497 160 #arcP
>Proto Gt0 .type gawfs.GenericPredefinedWorkflowStartData #txt
>Proto Gt0 .processKind NORMAL #txt
>Proto Gt0 0 0 32 24 18 0 #rect
>Proto Gt0 @|BIcon #fIcon
Gt0 f0 mainOut f4 tail #connect
Gt0 f4 head f3 mainIn #connect
Gt0 f5 mainOut f7 tail #connect
Gt0 f7 head f11 mainIn #connect
Gt0 f6 mainOut f8 tail #connect
Gt0 f8 head f12 mainIn #connect
Gt0 f11 mainOut f15 tail #connect
Gt0 f15 head f10 in #connect
Gt0 f3 mainOut f16 tail #connect
Gt0 f16 head f10 in #connect
Gt0 f12 mainOut f2 tail #connect
Gt0 f2 head f10 in #connect
Gt0 f13 out f18 tail #connect
Gt0 f18 head f14 mainIn #connect
Gt0 f14 mainOut f19 tail #connect
Gt0 f19 head f17 mainIn #connect
Gt0 f10 out f20 tail #connect
Gt0 f20 head f13 in #connect
