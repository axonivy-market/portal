[Ivy]
15797DC22608DA55 7.5.0 #module
>Proto >Proto Collection #zClass
Gt0 GenericPredefinedWorkflowStart Big #zClass
Gt0 B #cInfo
Gt0 #process
Gt0 @TextInP .type .type #zField
Gt0 @TextInP .processKind .processKind #zField
Gt0 @AnnotationInP-0n ai ai #zField
Gt0 @MessageFlowInP-0n messageIn messageIn #zField
Gt0 @MessageFlowOutP-0n messageOut messageOut #zField
Gt0 @TextInP .xml .xml #zField
Gt0 @TextInP .responsibility .responsibility #zField
Gt0 @StartRequest f0 '' #zField
Gt0 @CallSub f3 '' #zField
Gt0 @StartRequest f6 '' #zField
Gt0 @CallSub f12 '' #zField
Gt0 @Alternative f10 '' #zField
Gt0 @PushWFArc f16 '' #zField
Gt0 @PushWFArc f2 '' #zField
Gt0 @EndTask f17 '' #zField
Gt0 @PushWFArc f1 '' #zField
Gt0 @GridStep f5 '' #zField
Gt0 @PushWFArc f7 '' #zField
Gt0 @PushWFArc f4 '' #zField
Gt0 @GridStep f9 '' #zField
Gt0 @PushWFArc f11 '' #zField
Gt0 @PushWFArc f8 '' #zField
>Proto Gt0 Gt0 GenericPredefinedWorkflowStart #zField
Gt0 f0 outLink GenericPredefinedProcessStart.ivp #txt
Gt0 f0 inParamDecl '<Integer actualStepIndex,String workflowID> param;' #txt
Gt0 f0 inParamTable 'out.actualStepIndex=param.actualStepIndex;
out.workflowID=param.workflowID;
' #txt
Gt0 f0 requestEnabled true #txt
Gt0 f0 triggerEnabled false #txt
Gt0 f0 callSignature GenericPredefinedProcessStart(Integer,String) #txt
Gt0 f0 persist false #txt
Gt0 f0 startName 'GenericPredefinedProcessStart (Inputparam: WorkflowID e.g. ?workflowID=24, actualStepIndex e.g. ?actualStepIndex=1)' #txt
Gt0 f0 taskData 'TaskTriggered.DESC=<%\=ivy.cms.co("/Dialogs/Tasks/CreateWorkflow/TaskDescription")%>
TaskTriggered.EXPRI=2
TaskTriggered.EXROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.NAM=<%\=ivy.cms.co("/Dialogs/Tasks/CreateWorkflow/TaskName")%>
TaskTriggered.PRI=2
TaskTriggered.ROL=Everybody
TaskTriggered.TYPE=0' #txt
Gt0 f0 caseData businessCase.attach=false #txt
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
Gt0 f3 processCall 'Functional Processes/executePredefinedWorkflow:call(Integer,String)' #txt
Gt0 f3 requestActionDecl '<Integer actualStepIndex,String workflowID> param;' #txt
Gt0 f3 requestMappingAction 'param.actualStepIndex=in.actualStepIndex;
param.workflowID=in.workflowID;
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
Gt0 f3 408 42 160 44 -76 -8 #rect
Gt0 f3 @|CallSubIcon #fIcon
Gt0 f6 outLink GenericEditProcessStart.ivp #txt
Gt0 f6 inParamDecl '<String workflowID> param;' #txt
Gt0 f6 inParamTable 'out.workflowID=param.workflowID;
' #txt
Gt0 f6 requestEnabled true #txt
Gt0 f6 triggerEnabled false #txt
Gt0 f6 callSignature GenericEditProcessStart(String) #txt
Gt0 f6 persist false #txt
Gt0 f6 startName 'GenericEditProcessStart(Inputparam: WorkflowID e.g. ?workflowID=24)' #txt
Gt0 f6 startDescription 'Add WorkflowID to the Link like ?workflowID=24' #txt
Gt0 f6 taskData 'TaskTriggered.DESC=<%\=ivy.cms.co("/Dialogs/Tasks/EditWorkflow/TaskDescription")%>
TaskTriggered.EXPRI=2
TaskTriggered.EXROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.NAM=<%\=ivy.cms.co("/Dialogs/Tasks/EditWorkflow/TaskName")%>
TaskTriggered.PRI=2
TaskTriggered.ROL=Everybody
TaskTriggered.TYPE=0' #txt
Gt0 f6 caseData 'businessCase.attach=false
case.description=<%\=ivy.cms.co("/Dialogs/Cases/EditWorkflow/CaseDescription")%>
case.name=<%\=ivy.cms.co("/Dialogs/Cases/EditWorkflow/CaseName")%>' #txt
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
Gt0 f6 81 145 30 30 -76 17 #rect
Gt0 f6 @|StartRequestIcon #fIcon
Gt0 f12 processCall 'Functional Processes/editWorkflow:editWorkflow(String)' #txt
Gt0 f12 requestActionDecl '<String workflowID> param;' #txt
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
Gt0 f12 416 136 160 48 -10 -8 #rect
Gt0 f12 @|CallSubIcon #fIcon
Gt0 f10 608 144 32 32 0 16 #rect
Gt0 f10 @|AlternativeIcon #fIcon
Gt0 f16 expr out #txt
Gt0 f16 568 64 624 144 #arcP
Gt0 f16 1 624 64 #addKink
Gt0 f16 0 0.7371524785058673 0 0 #arcLabel
Gt0 f2 expr out #txt
Gt0 f2 576 160 608 160 #arcP
Gt0 f2 0 0.477567298105683 0 0 #arcLabel
Gt0 f17 705 145 30 30 0 15 #rect
Gt0 f17 @|EndIcon #fIcon
Gt0 f1 expr in #txt
Gt0 f1 640 160 705 160 #arcP
Gt0 f5 actionTable 'out=in;
' #txt
Gt0 f5 actionCode 'import ch.ivy.addon.portalkit.constant.CustomFields;
ivy.case.customFields().stringField(CustomFields.IS_EXPRESS_PROCESS).set("true");' #txt
Gt0 f5 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Set Task/Case field</name>
    </language>
</elementInfo>
' #txt
Gt0 f5 224 42 112 44 -53 -8 #rect
Gt0 f5 @|StepIcon #fIcon
Gt0 f7 expr out #txt
Gt0 f7 111 64 224 64 #arcP
Gt0 f4 336 64 408 64 #arcP
Gt0 f4 0 0.5448213204118716 0 0 #arcLabel
Gt0 f9 actionTable 'out=in;
' #txt
Gt0 f9 actionCode 'import ch.ivy.addon.portalkit.constant.CustomFields;
ivy.case.customFields().stringField(CustomFields.IS_EXPRESS_PROCESS).set("true");' #txt
Gt0 f9 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Set Task/Case field</name>
    </language>
</elementInfo>
' #txt
Gt0 f9 224 138 112 44 -53 -8 #rect
Gt0 f9 @|StepIcon #fIcon
Gt0 f11 expr out #txt
Gt0 f11 111 160 224 160 #arcP
Gt0 f8 336 160 416 160 #arcP
>Proto Gt0 .type gawfs.GenericPredefinedWorkflowStartData #txt
>Proto Gt0 .processKind NORMAL #txt
>Proto Gt0 0 0 32 24 18 0 #rect
>Proto Gt0 @|BIcon #fIcon
Gt0 f3 mainOut f16 tail #connect
Gt0 f16 head f10 in #connect
Gt0 f12 mainOut f2 tail #connect
Gt0 f2 head f10 in #connect
Gt0 f10 out f1 tail #connect
Gt0 f1 head f17 mainIn #connect
Gt0 f0 mainOut f7 tail #connect
Gt0 f7 head f5 mainIn #connect
Gt0 f5 mainOut f4 tail #connect
Gt0 f4 head f3 mainIn #connect
Gt0 f6 mainOut f11 tail #connect
Gt0 f11 head f9 mainIn #connect
Gt0 f9 mainOut f8 tail #connect
Gt0 f8 head f12 mainIn #connect
