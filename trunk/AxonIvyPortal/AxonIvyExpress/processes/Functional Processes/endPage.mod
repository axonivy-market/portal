[Ivy]
[>Created: Mon Mar 20 16:06:10 CET 2017]
158AC7867D5D538B 3.18 #module
>Proto >Proto Collection #zClass
ee0 endPage Big #zClass
ee0 B #cInfo
ee0 #process
ee0 @TextInP .resExport .resExport #zField
ee0 @TextInP .type .type #zField
ee0 @TextInP .processKind .processKind #zField
ee0 @AnnotationInP-0n ai ai #zField
ee0 @MessageFlowInP-0n messageIn messageIn #zField
ee0 @MessageFlowOutP-0n messageOut messageOut #zField
ee0 @TextInP .xml .xml #zField
ee0 @TextInP .responsibility .responsibility #zField
ee0 @StartRequest f0 '' #zField
ee0 @EndTask f1 '' #zField
ee0 @RichDialog f5 '' #zField
ee0 @PushWFArc f2 '' #zField
ee0 @PushWFArc f6 '' #zField
ee0 @PushWFArc f4 '' #zField
ee0 @RichDialog f3 '' #zField
>Proto ee0 ee0 endPage #zField
ee0 f0 outLink start.ivp #txt
ee0 f0 type gawfs.EndPageProcessData #txt
ee0 f0 inParamDecl '<java.lang.String caseType,java.lang.Integer taskIdentifier,java.lang.Integer caseId> param;' #txt
ee0 f0 inParamTable 'out.caseId=param.caseId;
out.caseType=param.caseType;
out.taskId=param.taskIdentifier;
' #txt
ee0 f0 actionDecl 'gawfs.EndPageProcessData out;
' #txt
ee0 f0 guid 158AC7867DA9A3ED #txt
ee0 f0 requestEnabled true #txt
ee0 f0 triggerEnabled false #txt
ee0 f0 callSignature start(String,Integer,Integer) #txt
ee0 f0 persist false #txt
ee0 f0 taskData 'TaskTriggered.ROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.EXPRI=2
TaskTriggered.TYPE=0
TaskTriggered.PRI=2
TaskTriggered.EXROL=Everybody' #txt
ee0 f0 showInStartList 1 #txt
ee0 f0 taskAndCaseSetupAction 'import ch.ivyteam.ivy.workflow.TaskUpdateDefinition;
ch.ivyteam.ivy.workflow.TaskUpdateDefinition taskUpdDef = new ch.ivyteam.ivy.workflow.TaskUpdateDefinition();
import ch.ivyteam.ivy.request.impl.DefaultCalendarProxy;
DefaultCalendarProxy calendarProxy = ivy.cal as DefaultCalendarProxy;
taskUpdDef.setPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
taskUpdDef.setExpiryActivator("Everybody");
taskUpdDef.setExpiryPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
engine.updateCurrentTask(taskUpdDef);
' #txt
ee0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start.ivp</name>
        <nameStyle>9,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
ee0 f0 @C|.responsibility Everybody #txt
ee0 f0 81 49 30 30 -21 17 #rect
ee0 f0 @|StartRequestIcon #fIcon
ee0 f1 type gawfs.EndPageProcessData #txt
ee0 f1 649 49 30 30 0 15 #rect
ee0 f1 @|EndIcon #fIcon
ee0 f5 targetWindow NEW:card: #txt
ee0 f5 targetDisplay TOP #txt
ee0 f5 richDialogId ch.ivy.gawfs.portal.GAWFSPortalHome #txt
ee0 f5 startMethod start(gawfs.DevLoadWorkflowsData) #txt
ee0 f5 type gawfs.EndPageProcessData #txt
ee0 f5 requestActionDecl '<gawfs.DevLoadWorkflowsData devLoadWorkflowsData> param;' #txt
ee0 f5 responseActionDecl 'gawfs.EndPageProcessData out;
' #txt
ee0 f5 responseMappingAction 'out=in;
' #txt
ee0 f5 windowConfiguration '* ' #txt
ee0 f5 isAsynch false #txt
ee0 f5 isInnerRd false #txt
ee0 f5 userContext '* ' #txt
ee0 f5 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Portal Home Page</name>
        <nameStyle>16,7
</nameStyle>
    </language>
</elementInfo>
' #txt
ee0 f5 408 42 112 44 -50 -8 #rect
ee0 f5 @|RichDialogIcon #fIcon
ee0 f2 expr out #txt
ee0 f2 520 64 649 64 #arcP
ee0 f6 expr out #txt
ee0 f6 328 64 408 64 #arcP
ee0 f4 expr out #txt
ee0 f4 111 64 216 64 #arcP
ee0 f3 targetWindow NEW:card: #txt
ee0 f3 targetDisplay TOP #txt
ee0 f3 richDialogId ch.ivy.gawfs.portal.GAWFSEndPage #txt
ee0 f3 startMethod start(gawfs.EndPageProcessData) #txt
ee0 f3 type gawfs.EndPageProcessData #txt
ee0 f3 requestActionDecl '<gawfs.EndPageProcessData endPageProcessData> param;' #txt
ee0 f3 requestMappingAction 'param.endPageProcessData=in;
' #txt
ee0 f3 responseActionDecl 'gawfs.EndPageProcessData out;
' #txt
ee0 f3 responseMappingAction 'out=result.endPageProcessData;
' #txt
ee0 f3 windowConfiguration '* ' #txt
ee0 f3 isAsynch false #txt
ee0 f3 isInnerRd false #txt
ee0 f3 userContext '* ' #txt
ee0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>endPage</name>
        <nameStyle>7,7
</nameStyle>
    </language>
</elementInfo>
' #txt
ee0 f3 216 42 112 44 -25 -8 #rect
ee0 f3 @|RichDialogIcon #fIcon
>Proto ee0 .type gawfs.EndPageProcessData #txt
>Proto ee0 .processKind NORMAL #txt
>Proto ee0 0 0 32 24 18 0 #rect
>Proto ee0 @|BIcon #fIcon
ee0 f0 mainOut f4 tail #connect
ee0 f4 head f3 mainIn #connect
ee0 f3 mainOut f6 tail #connect
ee0 f6 head f5 mainIn #connect
ee0 f5 mainOut f2 tail #connect
ee0 f2 head f1 mainIn #connect
