[Ivy]
15798655494F25E1 9.3.1 #module
>Proto >Proto Collection #zClass
cw0 CreateWorkflow Big #zClass
cw0 B #cInfo
cw0 #process
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
cw0 @PushWFArc f2 '' #zField
cw0 @StartRequest f5 '' #zField
cw0 @CallSub f6 '' #zField
cw0 @EndTask f8 '' #zField
cw0 @PushWFArc f9 '' #zField
cw0 @GridStep f10 '' #zField
cw0 @PushWFArc f11 '' #zField
cw0 @PushWFArc f4 '' #zField
cw0 @GridStep f12 '' #zField
cw0 @PushWFArc f13 '' #zField
cw0 @PushWFArc f7 '' #zField
>Proto cw0 cw0 CreateWorkflow #zField
cw0 f0 outLink AxonIvyExpressWF.ivp #txt
cw0 f0 inParamDecl '<> param;' #txt
cw0 f0 requestEnabled true #txt
cw0 f0 triggerEnabled false #txt
cw0 f0 callSignature AxonIvyExpressWF() #txt
cw0 f0 persist false #txt
cw0 f0 startName <%=ivy.cms.co("/Dialogs/workflowCreation/ProcessName")%> #txt
cw0 f0 taskData 'TaskTriggered.DESC=<%\=ivy.cms.co("/Dialogs/Tasks/WorkflowProperties/TaskDescription")%>
TaskTriggered.EXPRI=2
TaskTriggered.EXROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.NAM=<%\=ivy.cms.co("/Dialogs/Tasks/WorkflowProperties/TaskName")%>
TaskTriggered.PRI=2
TaskTriggered.ROL=Everybody
TaskTriggered.TYPE=0' #txt
cw0 f0 caseData 'businessCase.attach=false
case.description=<%\=ivy.cms.co("/Dialogs/workflowCreation/ProcessName")%>
case.name=<%\=ivy.cms.co("/Dialogs/workflowCreation/ProcessName")%>' #txt
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
cw0 f1 569 49 30 30 0 15 #rect
cw0 f3 processCall 'Functional Processes/editWorkflow:newWorkflow()' #txt
cw0 f3 requestActionDecl '<> param;' #txt
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
cw0 f3 384 42 112 44 -35 -8 #rect
cw0 f2 expr out #txt
cw0 f2 496 64 569 64 #arcP
cw0 f5 outLink AxonIvyExpressAdhocWF.ivp #txt
cw0 f5 inParamDecl '<Long originalTaskId> param;' #txt
cw0 f5 inParamTable 'out.originalTaskId=param.originalTaskId;
' #txt
cw0 f5 requestEnabled true #txt
cw0 f5 triggerEnabled false #txt
cw0 f5 callSignature AxonIvyExpressAdhocWF(Long) #txt
cw0 f5 persist false #txt
cw0 f5 caseData businessCase.attach=true #txt
cw0 f5 showInStartList 0 #txt
cw0 f5 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>AxonIvyExpressAdhocWF.ivp</name>
    </language>
</elementInfo>
' #txt
cw0 f5 @C|.responsibility Everybody #txt
cw0 f5 81 177 30 30 -73 24 #rect
cw0 f6 processCall 'Functional Processes/editWorkflow:newAdhocWorkflow(Long)' #txt
cw0 f6 requestActionDecl '<Long originalTaskId> param;' #txt
cw0 f6 requestMappingAction 'param.originalTaskId=in.originalTaskId;
' #txt
cw0 f6 responseActionDecl 'gawfs.createWorkflowData out;
' #txt
cw0 f6 responseMappingAction 'out=in;
' #txt
cw0 f6 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>create Adhoc WF</name>
    </language>
</elementInfo>
' #txt
cw0 f6 392 170 112 44 -46 -8 #rect
cw0 f8 577 177 30 30 0 15 #rect
cw0 f9 expr out #txt
cw0 f9 504 192 577 192 #arcP
cw0 f10 actionTable 'out=in;
' #txt
cw0 f10 actionCode 'import ch.ivy.addon.portalkit.constant.CustomFields;
ivy.case.customFields().stringField(CustomFields.IS_EXPRESS_PROCESS).set("true");' #txt
cw0 f10 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Set Task/Case field</name>
    </language>
</elementInfo>
' #txt
cw0 f10 224 42 112 44 -53 -8 #rect
cw0 f11 expr out #txt
cw0 f11 111 64 224 64 #arcP
cw0 f4 336 64 384 64 #arcP
cw0 f12 actionTable 'out=in;
' #txt
cw0 f12 actionCode 'import ch.ivy.addon.portalkit.constant.CustomFields;
ivy.case.customFields().stringField(CustomFields.IS_EXPRESS_PROCESS).set("true");' #txt
cw0 f12 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Set Task/Case field</name>
    </language>
</elementInfo>
' #txt
cw0 f12 224 170 112 44 -53 -8 #rect
cw0 f13 expr out #txt
cw0 f13 111 192 224 192 #arcP
cw0 f7 336 192 392 192 #arcP
>Proto cw0 .type gawfs.createWorkflowData #txt
>Proto cw0 .processKind NORMAL #txt
>Proto cw0 0 0 32 24 18 0 #rect
>Proto cw0 @|BIcon #fIcon
cw0 f3 mainOut f2 tail #connect
cw0 f2 head f1 mainIn #connect
cw0 f6 mainOut f9 tail #connect
cw0 f9 head f8 mainIn #connect
cw0 f0 mainOut f11 tail #connect
cw0 f11 head f10 mainIn #connect
cw0 f10 mainOut f4 tail #connect
cw0 f4 head f3 mainIn #connect
cw0 f5 mainOut f13 tail #connect
cw0 f13 head f12 mainIn #connect
cw0 f12 mainOut f7 tail #connect
cw0 f7 head f6 mainIn #connect
