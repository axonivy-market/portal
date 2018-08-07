[Ivy]
[>Created: Fri Jul 14 16:31:50 ICT 2017]
15797F0F040DE913 3.20 #module
>Proto >Proto Collection #zClass
dw0 deletePredefinedWorkflow Big #zClass
dw0 B #cInfo
dw0 #process
dw0 @TextInP .resExport .resExport #zField
dw0 @TextInP .type .type #zField
dw0 @TextInP .processKind .processKind #zField
dw0 @AnnotationInP-0n ai ai #zField
dw0 @MessageFlowInP-0n messageIn messageIn #zField
dw0 @MessageFlowOutP-0n messageOut messageOut #zField
dw0 @TextInP .xml .xml #zField
dw0 @TextInP .responsibility .responsibility #zField
dw0 @StartSub f0 '' #zField
dw0 @EndSub f1 '' #zField
dw0 @GridStep f3 '' #zField
dw0 @PushWFArc f2 '' #zField
dw0 @RichDialog f5 '' #zField
dw0 @PushWFArc f6 '' #zField
dw0 @PushWFArc f4 '' #zField
>Proto dw0 dw0 deletePredefinedWorkflow #zField
dw0 f0 inParamDecl '<java.lang.String workflowID> param;' #txt
dw0 f0 inParamTable 'out.workflowID=param.workflowID;
' #txt
dw0 f0 outParamDecl '<java.lang.String workflowID> result;
' #txt
dw0 f0 outParamTable 'result.workflowID=in.workflowID;
' #txt
dw0 f0 actionDecl 'gawfs.deletePredefinedWorkflowData out;
' #txt
dw0 f0 callSignature call(String) #txt
dw0 f0 type gawfs.deletePredefinedWorkflowData #txt
dw0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>call(String)</name>
        <nameStyle>12,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
dw0 f0 81 49 30 30 -29 17 #rect
dw0 f0 @|StartSubIcon #fIcon
dw0 f1 type gawfs.deletePredefinedWorkflowData #txt
dw0 f1 665 49 30 30 0 15 #rect
dw0 f1 @|EndSubIcon #fIcon
dw0 f3 actionDecl 'gawfs.deletePredefinedWorkflowData out;
' #txt
dw0 f3 actionTable 'out=in;
' #txt
dw0 f3 actionCode 'import ch.ivy.addon.portalkit.service.ExpressServiceRegistry;

ExpressServiceRegistry.getProcessService().delete(in.workflowID);
ExpressServiceRegistry.getTaskDefinitionService().deleteByProcessId(in.workflowID);
ExpressServiceRegistry.getFormElementService().deleteByProcessId(in.workflowID);

ivy.log.debug("WORKFLOW has Id {0} is DELETED by  TaskId {1}, CaseId {2}", in.workflowID, ivy.task.getId(),  ivy.case.getId());
ivy.log.debug(ivy.case.getCustomVarCharField1());
' #txt
dw0 f3 type gawfs.deletePredefinedWorkflowData #txt
dw0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>delete Workflow 
and related TaksSteps and Formelements</name>
        <nameStyle>55,7
</nameStyle>
    </language>
</elementInfo>
' #txt
dw0 f3 336 42 256 44 -110 -16 #rect
dw0 f3 @|StepIcon #fIcon
dw0 f2 expr out #txt
dw0 f2 592 64 665 64 #arcP
dw0 f5 targetWindow NEW:card: #txt
dw0 f5 targetDisplay TOP #txt
dw0 f5 richDialogId ch.ivy.gawfs.portal.DeleteConfirmation #txt
dw0 f5 startMethod start(gawfs.DevLoadWorkflowsData) #txt
dw0 f5 type gawfs.deletePredefinedWorkflowData #txt
dw0 f5 requestActionDecl '<gawfs.DevLoadWorkflowsData devLoadWorkflowsData> param;' #txt
dw0 f5 requestMappingAction 'param.devLoadWorkflowsData.workflowID=in.workflowID;
' #txt
dw0 f5 responseActionDecl 'gawfs.deletePredefinedWorkflowData out;
' #txt
dw0 f5 responseMappingAction 'out=in;
out.workflowID=result.devLoadWorkflowsData.workflowID;
' #txt
dw0 f5 windowConfiguration '* ' #txt
dw0 f5 isAsynch false #txt
dw0 f5 isInnerRd false #txt
dw0 f5 userContext '* ' #txt
dw0 f5 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>delete confirmation</name>
        <nameStyle>19,7
</nameStyle>
    </language>
</elementInfo>
' #txt
dw0 f5 160 42 112 44 -52 -8 #rect
dw0 f5 @|RichDialogIcon #fIcon
dw0 f6 expr out #txt
dw0 f6 111 64 160 64 #arcP
dw0 f4 expr out #txt
dw0 f4 272 64 336 64 #arcP
>Proto dw0 .type gawfs.deletePredefinedWorkflowData #txt
>Proto dw0 .processKind CALLABLE_SUB #txt
>Proto dw0 0 0 32 24 18 0 #rect
>Proto dw0 @|BIcon #fIcon
dw0 f3 mainOut f2 tail #connect
dw0 f2 head f1 mainIn #connect
dw0 f0 mainOut f6 tail #connect
dw0 f6 head f5 mainIn #connect
dw0 f5 mainOut f4 tail #connect
dw0 f4 head f3 mainIn #connect
