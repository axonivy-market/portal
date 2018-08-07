[Ivy]
[>Created: Mon May 09 14:30:17 ICT 2016]
14BEE67A1CA77C16 3.18 #module
>Proto >Proto Collection #zClass
Tt0 PortalTemplatePages Big #zClass
Tt0 B #cInfo
Tt0 #process
Tt0 @TextInP .resExport .resExport #zField
Tt0 @TextInP .type .type #zField
Tt0 @TextInP .processKind .processKind #zField
Tt0 @AnnotationInP-0n ai ai #zField
Tt0 @MessageFlowInP-0n messageIn messageIn #zField
Tt0 @MessageFlowOutP-0n messageOut messageOut #zField
Tt0 @TextInP .xml .xml #zField
Tt0 @TextInP .responsibility .responsibility #zField
Tt0 @StartRequest f6 '' #zField
Tt0 @RichDialog f5 '' #zField
Tt0 @StartRequest f0 '' #zField
Tt0 @Alternative f1 '' #zField
Tt0 @PushWFArc f8 '' #zField
Tt0 @PushWFArc f10 '' #zField
Tt0 @StartRequest f2 '' #zField
Tt0 @RichDialog f12 '' #zField
Tt0 @PushWFArc f13 '' #zField
Tt0 @StartRequest f15 '' #zField
Tt0 @PushWFArc f4 '' #zField
Tt0 @StartRequest f3 '' #zField
Tt0 @RichDialog f7 '' #zField
Tt0 @StartRequest f19 '' #zField
Tt0 @RichDialog f20 '' #zField
Tt0 @PushWFArc f21 '' #zField
Tt0 @StartRequest f25 '' #zField
Tt0 @StartRequest f28 '' #zField
Tt0 @RichDialog f29 '' #zField
Tt0 @PushWFArc f30 '' #zField
Tt0 @StartRequest f22 '' #zField
Tt0 @CallSub f17 '' #zField
Tt0 @GridStep f11 '' #zField
Tt0 @PushWFArc f23 '' #zField
Tt0 @PushWFArc f18 '' #zField
Tt0 @PushWFArc f9 '' #zField
Tt0 @CallSub f24 '' #zField
Tt0 @GridStep f16 '' #zField
Tt0 @PushWFArc f32 '' #zField
Tt0 @PushWFArc f31 '' #zField
Tt0 @GridStep f14 '' #zField
Tt0 @PushWFArc f33 '' #zField
Tt0 @PushWFArc f27 '' #zField
Tt0 @CallSub f26 '' #zField
>Proto Tt0 Tt0 PortalTemplatePages #zField
Tt0 f6 outLink PortalHome.ivp #txt
Tt0 f6 type ch.ivy.addon.portal.generic.Data #txt
Tt0 f6 inParamDecl '<> param;' #txt
Tt0 f6 actionDecl 'ch.ivy.addon.portal.generic.Data out;
' #txt
Tt0 f6 guid 14BEF1531A32C057 #txt
Tt0 f6 requestEnabled true #txt
Tt0 f6 triggerEnabled false #txt
Tt0 f6 callSignature PortalHome() #txt
Tt0 f6 persist false #txt
Tt0 f6 startName <%=ivy.cms.co("/Processes/portalHome")%> #txt
Tt0 f6 taskData 'TaskTriggered.ROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.EXPRI=2
TaskTriggered.TYPE=0
TaskTriggered.PRI=2
TaskTriggered.EXROL=Everybody' #txt
Tt0 f6 showInStartList 1 #txt
Tt0 f6 taskAndCaseSetupAction 'import ch.ivyteam.ivy.workflow.TaskUpdateDefinition;
ch.ivyteam.ivy.workflow.TaskUpdateDefinition taskUpdDef = new ch.ivyteam.ivy.workflow.TaskUpdateDefinition();
import ch.ivyteam.ivy.request.impl.DefaultCalendarProxy;
DefaultCalendarProxy calendarProxy = ivy.cal as DefaultCalendarProxy;
taskUpdDef.setPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
taskUpdDef.setExpiryActivator("Everybody");
taskUpdDef.setExpiryPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
engine.updateCurrentTask(taskUpdDef);
' #txt
Tt0 f6 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>PortalHome.ivp</name>
        <nameStyle>14,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Tt0 f6 @C|.responsibility Everybody #txt
Tt0 f6 81 145 30 30 -42 17 #rect
Tt0 f6 @|StartRequestIcon #fIcon
Tt0 f5 targetWindow NEW:card: #txt
Tt0 f5 targetDisplay TOP #txt
Tt0 f5 richDialogId ch.ivy.addon.portal.generic.PortalHome #txt
Tt0 f5 startMethod start() #txt
Tt0 f5 type ch.ivy.addon.portal.generic.Data #txt
Tt0 f5 requestActionDecl '<> param;' #txt
Tt0 f5 responseActionDecl 'ch.ivy.addon.portal.generic.Data out;
' #txt
Tt0 f5 responseMappingAction 'out=in;
' #txt
Tt0 f5 windowConfiguration '* ' #txt
Tt0 f5 isAsynch false #txt
Tt0 f5 isInnerRd false #txt
Tt0 f5 userContext '* ' #txt
Tt0 f5 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Portal Home</name>
        <nameStyle>11,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Tt0 f5 380 136 88 48 -29 -8 #rect
Tt0 f5 @|RichDialogIcon #fIcon
Tt0 f0 outLink DefaultEndPage.ivp #txt
Tt0 f0 type ch.ivy.addon.portal.generic.Data #txt
Tt0 f0 inParamDecl '<java.lang.Number endedTaskID> param;' #txt
Tt0 f0 actionDecl 'ch.ivy.addon.portal.generic.Data out;
' #txt
Tt0 f0 guid 15122F3CBB012A4D #txt
Tt0 f0 requestEnabled true #txt
Tt0 f0 triggerEnabled false #txt
Tt0 f0 callSignature DefaultEndPage(Number) #txt
Tt0 f0 persist false #txt
Tt0 f0 taskData 'TaskTriggered.ROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.EXPRI=2
TaskTriggered.TYPE=0
TaskTriggered.PRI=2
TaskTriggered.EXROL=Everybody' #txt
Tt0 f0 showInStartList 0 #txt
Tt0 f0 taskAndCaseSetupAction 'import ch.ivyteam.ivy.workflow.TaskUpdateDefinition;
ch.ivyteam.ivy.workflow.TaskUpdateDefinition taskUpdDef = new ch.ivyteam.ivy.workflow.TaskUpdateDefinition();
import ch.ivyteam.ivy.request.impl.DefaultCalendarProxy;
DefaultCalendarProxy calendarProxy = ivy.cal as DefaultCalendarProxy;
taskUpdDef.setPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
taskUpdDef.setExpiryActivator("Everybody");
taskUpdDef.setExpiryPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
engine.updateCurrentTask(taskUpdDef);
' #txt
Tt0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>DefaultEndPage.ivp</name>
    </language>
</elementInfo>
' #txt
Tt0 f0 @C|.responsibility Everybody #txt
Tt0 f0 83 51 26 26 -46 17 #rect
Tt0 f0 @|StartRequestIcon #fIcon
Tt0 f1 type ch.ivy.addon.portal.generic.Data #txt
Tt0 f1 290 146 28 28 14 0 #rect
Tt0 f1 @|AlternativeIcon #fIcon
Tt0 f8 expr in #txt
Tt0 f8 318 160 380 160 #arcP
Tt0 f8 0 0.4934210526315789 1 12 #arcLabel
Tt0 f10 expr out #txt
Tt0 f10 109 64 304 146 #arcP
Tt0 f10 1 304 64 #addKink
Tt0 f10 0 0.6561818258411226 0 0 #arcLabel
Tt0 f2 outLink PortalCaseDetails.ivp #txt
Tt0 f2 type ch.ivy.addon.portal.generic.Data #txt
Tt0 f2 inParamDecl '<java.lang.Number caseId> param;' #txt
Tt0 f2 inParamTable 'out.caseId=param.caseId;
' #txt
Tt0 f2 actionDecl 'ch.ivy.addon.portal.generic.Data out;
' #txt
Tt0 f2 guid 1522FBA0AB369F5E #txt
Tt0 f2 requestEnabled true #txt
Tt0 f2 triggerEnabled false #txt
Tt0 f2 callSignature PortalCaseDetails(Number) #txt
Tt0 f2 persist false #txt
Tt0 f2 taskData 'TaskTriggered.ROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.EXPRI=2
TaskTriggered.TYPE=0
TaskTriggered.PRI=2
TaskTriggered.EXROL=Everybody' #txt
Tt0 f2 showInStartList 0 #txt
Tt0 f2 taskAndCaseSetupAction 'import ch.ivyteam.ivy.workflow.TaskUpdateDefinition;
ch.ivyteam.ivy.workflow.TaskUpdateDefinition taskUpdDef = new ch.ivyteam.ivy.workflow.TaskUpdateDefinition();
import ch.ivyteam.ivy.request.impl.DefaultCalendarProxy;
DefaultCalendarProxy calendarProxy = ivy.cal as DefaultCalendarProxy;
taskUpdDef.setPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
taskUpdDef.setExpiryActivator("Everybody");
taskUpdDef.setExpiryPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
engine.updateCurrentTask(taskUpdDef);
' #txt
Tt0 f2 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>PortalCaseDetails.ivp</name>
        <nameStyle>21,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Tt0 f2 @C|.responsibility Everybody #txt
Tt0 f2 83 243 26 26 -46 20 #rect
Tt0 f2 @|StartRequestIcon #fIcon
Tt0 f12 targetWindow NEW:card: #txt
Tt0 f12 targetDisplay TOP #txt
Tt0 f12 richDialogId ch.ivy.addon.portal.generic.CaseDetails #txt
Tt0 f12 startMethod start(Number) #txt
Tt0 f12 type ch.ivy.addon.portal.generic.Data #txt
Tt0 f12 requestActionDecl '<Number caseId> param;' #txt
Tt0 f12 requestMappingAction 'param.caseId=in.caseId;
' #txt
Tt0 f12 responseActionDecl 'ch.ivy.addon.portal.generic.Data out;
' #txt
Tt0 f12 responseMappingAction 'out=in;
' #txt
Tt0 f12 windowConfiguration '* ' #txt
Tt0 f12 isAsynch false #txt
Tt0 f12 isInnerRd false #txt
Tt0 f12 userContext '* ' #txt
Tt0 f12 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Case Details</name>
        <nameStyle>12,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Tt0 f12 380 236 88 40 -31 -9 #rect
Tt0 f12 @|RichDialogIcon #fIcon
Tt0 f13 expr out #txt
Tt0 f13 109 256 380 256 #arcP
Tt0 f15 outLink PortalTaskList.ivp #txt
Tt0 f15 type ch.ivy.addon.portal.generic.Data #txt
Tt0 f15 inParamDecl '<java.lang.Long serverId,java.lang.Long remoteTaskId,java.lang.String keyword> param;' #txt
Tt0 f15 inParamTable 'out.keyword=param.keyword;
out.serverId=param.serverId;
out.taskId=param.remoteTaskId;
' #txt
Tt0 f15 actionDecl 'ch.ivy.addon.portal.generic.Data out;
' #txt
Tt0 f15 guid 15233B1EF3FCFB04 #txt
Tt0 f15 requestEnabled true #txt
Tt0 f15 triggerEnabled false #txt
Tt0 f15 callSignature PortalTaskList(Long,Long,String) #txt
Tt0 f15 persist false #txt
Tt0 f15 taskData 'TaskTriggered.ROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.EXPRI=2
TaskTriggered.TYPE=0
TaskTriggered.PRI=2
TaskTriggered.EXROL=Everybody' #txt
Tt0 f15 showInStartList 0 #txt
Tt0 f15 taskAndCaseSetupAction 'import ch.ivyteam.ivy.workflow.TaskUpdateDefinition;
ch.ivyteam.ivy.workflow.TaskUpdateDefinition taskUpdDef = new ch.ivyteam.ivy.workflow.TaskUpdateDefinition();
import ch.ivyteam.ivy.request.impl.DefaultCalendarProxy;
DefaultCalendarProxy calendarProxy = ivy.cal as DefaultCalendarProxy;
taskUpdDef.setPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
taskUpdDef.setExpiryActivator("Everybody");
taskUpdDef.setExpiryPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
engine.updateCurrentTask(taskUpdDef);
' #txt
Tt0 f15 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>PortalTaskList.ivp</name>
        <nameStyle>18,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Tt0 f15 @C|.responsibility Everybody #txt
Tt0 f15 83 339 26 26 -48 15 #rect
Tt0 f15 @|StartRequestIcon #fIcon
Tt0 f4 expr out #txt
Tt0 f4 111 160 290 160 #arcP
Tt0 f4 0 0.52046783625731 0 0 #arcLabel
Tt0 f3 outLink PortalErrorPages.ivp #txt
Tt0 f3 type ch.ivy.addon.portal.generic.Data #txt
Tt0 f3 inParamDecl '<java.lang.String errorCode> param;' #txt
Tt0 f3 inParamTable 'out.errorCode=param.errorCode;
' #txt
Tt0 f3 actionDecl 'ch.ivy.addon.portal.generic.Data out;
' #txt
Tt0 f3 guid 15268527EFBD166E #txt
Tt0 f3 requestEnabled true #txt
Tt0 f3 triggerEnabled false #txt
Tt0 f3 callSignature PortalErrorPages(String) #txt
Tt0 f3 persist false #txt
Tt0 f3 taskData 'TaskTriggered.ROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.EXPRI=2
TaskTriggered.TYPE=0
TaskTriggered.PRI=2
TaskTriggered.EXROL=Everybody' #txt
Tt0 f3 showInStartList 0 #txt
Tt0 f3 taskAndCaseSetupAction 'import ch.ivyteam.ivy.workflow.TaskUpdateDefinition;
ch.ivyteam.ivy.workflow.TaskUpdateDefinition taskUpdDef = new ch.ivyteam.ivy.workflow.TaskUpdateDefinition();
import ch.ivyteam.ivy.request.impl.DefaultCalendarProxy;
DefaultCalendarProxy calendarProxy = ivy.cal as DefaultCalendarProxy;
taskUpdDef.setPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
taskUpdDef.setExpiryActivator("Everybody");
taskUpdDef.setExpiryPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
engine.updateCurrentTask(taskUpdDef);
' #txt
Tt0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>PortalErrorPages.ivp</name>
        <nameStyle>20,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Tt0 f3 @C|.responsibility Everybody #txt
Tt0 f3 83 435 26 26 -42 16 #rect
Tt0 f3 @|StartRequestIcon #fIcon
Tt0 f7 targetWindow NEW:card: #txt
Tt0 f7 targetDisplay TOP #txt
Tt0 f7 richDialogId ch.ivy.addon.portal.error.ErrorPage #txt
Tt0 f7 startMethod start(String) #txt
Tt0 f7 type ch.ivy.addon.portal.generic.Data #txt
Tt0 f7 requestActionDecl '<String errorCode> param;' #txt
Tt0 f7 requestMappingAction 'param.errorCode=in.errorCode;
' #txt
Tt0 f7 responseActionDecl 'ch.ivy.addon.portal.generic.Data out;
' #txt
Tt0 f7 responseMappingAction 'out=in;
' #txt
Tt0 f7 windowConfiguration '* ' #txt
Tt0 f7 isAsynch false #txt
Tt0 f7 isInnerRd false #txt
Tt0 f7 userContext '* ' #txt
Tt0 f7 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Error Page</name>
        <nameStyle>10
</nameStyle>
    </language>
</elementInfo>
' #txt
Tt0 f7 384 424 80 48 -22 -8 #rect
Tt0 f7 @|RichDialogIcon #fIcon
Tt0 f19 outLink PortalProcessList.ivp #txt
Tt0 f19 type ch.ivy.addon.portal.generic.Data #txt
Tt0 f19 inParamDecl '<java.lang.String processStartLink,java.lang.String keyword> param;' #txt
Tt0 f19 inParamTable 'out.keyword=param.keyword;
out.processStartLink=param.processStartLink;
' #txt
Tt0 f19 actionDecl 'ch.ivy.addon.portal.generic.Data out;
' #txt
Tt0 f19 guid 1531CA6B7A89C7AA #txt
Tt0 f19 requestEnabled true #txt
Tt0 f19 triggerEnabled false #txt
Tt0 f19 callSignature PortalProcessList(String,String) #txt
Tt0 f19 persist false #txt
Tt0 f19 taskData 'TaskTriggered.ROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.EXPRI=2
TaskTriggered.TYPE=0
TaskTriggered.PRI=2
TaskTriggered.EXROL=Everybody' #txt
Tt0 f19 showInStartList 0 #txt
Tt0 f19 taskAndCaseSetupAction 'import ch.ivyteam.ivy.workflow.TaskUpdateDefinition;
ch.ivyteam.ivy.workflow.TaskUpdateDefinition taskUpdDef = new ch.ivyteam.ivy.workflow.TaskUpdateDefinition();
import ch.ivyteam.ivy.request.impl.DefaultCalendarProxy;
DefaultCalendarProxy calendarProxy = ivy.cal as DefaultCalendarProxy;
taskUpdDef.setPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
taskUpdDef.setExpiryActivator("Everybody");
taskUpdDef.setExpiryPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
engine.updateCurrentTask(taskUpdDef);
' #txt
Tt0 f19 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>PortalProcessList.ivp</name>
        <nameStyle>21,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Tt0 f19 @C|.responsibility Everybody #txt
Tt0 f19 83 531 26 26 -48 17 #rect
Tt0 f19 @|StartRequestIcon #fIcon
Tt0 f20 targetWindow NEW:card: #txt
Tt0 f20 targetDisplay TOP #txt
Tt0 f20 richDialogId ch.ivy.addon.portal.generic.Processes #txt
Tt0 f20 startMethod start(String,String) #txt
Tt0 f20 type ch.ivy.addon.portal.generic.Data #txt
Tt0 f20 requestActionDecl '<String keyword, String processStartLink> param;' #txt
Tt0 f20 requestMappingAction 'param.keyword=in.keyword;
param.processStartLink=in.processStartLink;
' #txt
Tt0 f20 responseActionDecl 'ch.ivy.addon.portal.generic.Data out;
' #txt
Tt0 f20 responseMappingAction 'out=in;
' #txt
Tt0 f20 windowConfiguration '* ' #txt
Tt0 f20 isAsynch false #txt
Tt0 f20 isInnerRd false #txt
Tt0 f20 userContext '* ' #txt
Tt0 f20 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Process List</name>
        <nameStyle>12,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Tt0 f20 384 524 80 40 -29 -5 #rect
Tt0 f20 @|RichDialogIcon #fIcon
Tt0 f21 expr out #txt
Tt0 f21 109 544 384 544 #arcP
Tt0 f25 outLink PortalTaskListOfCase.ivp #txt
Tt0 f25 type ch.ivy.addon.portal.generic.Data #txt
Tt0 f25 inParamDecl '<java.lang.String caseName,java.lang.Long serverId,java.lang.Long selectedTaskId,java.lang.Long caseId> param;' #txt
Tt0 f25 inParamTable 'out.caseId=param.caseId;
out.caseName=param.caseName;
out.serverId=param.serverId;
out.taskId=param.selectedTaskId;
' #txt
Tt0 f25 actionDecl 'ch.ivy.addon.portal.generic.Data out;
' #txt
Tt0 f25 guid 15355AC272E53A5C #txt
Tt0 f25 requestEnabled true #txt
Tt0 f25 triggerEnabled false #txt
Tt0 f25 callSignature PortalTaskListOfCase(String,Long,Long,Long) #txt
Tt0 f25 persist false #txt
Tt0 f25 taskData 'TaskTriggered.ROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.EXPRI=2
TaskTriggered.TYPE=0
TaskTriggered.PRI=2
TaskTriggered.EXROL=Everybody' #txt
Tt0 f25 showInStartList 0 #txt
Tt0 f25 taskAndCaseSetupAction 'import ch.ivyteam.ivy.workflow.TaskUpdateDefinition;
ch.ivyteam.ivy.workflow.TaskUpdateDefinition taskUpdDef = new ch.ivyteam.ivy.workflow.TaskUpdateDefinition();
import ch.ivyteam.ivy.request.impl.DefaultCalendarProxy;
DefaultCalendarProxy calendarProxy = ivy.cal as DefaultCalendarProxy;
taskUpdDef.setPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
taskUpdDef.setExpiryActivator("Everybody");
taskUpdDef.setExpiryPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
engine.updateCurrentTask(taskUpdDef);
' #txt
Tt0 f25 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>PortalTaskListOfCase.ivp</name>
        <nameStyle>24,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Tt0 f25 @C|.responsibility Everybody #txt
Tt0 f25 83 619 26 26 -45 19 #rect
Tt0 f25 @|StartRequestIcon #fIcon
Tt0 f28 outLink PortalSingleTask.ivp #txt
Tt0 f28 type ch.ivy.addon.portal.generic.Data #txt
Tt0 f28 inParamDecl '<java.lang.Number remoteTaskId> param;' #txt
Tt0 f28 inParamTable 'out.taskId=param.remoteTaskId;
' #txt
Tt0 f28 actionDecl 'ch.ivy.addon.portal.generic.Data out;
' #txt
Tt0 f28 guid 153893DD6808D13D #txt
Tt0 f28 requestEnabled true #txt
Tt0 f28 triggerEnabled false #txt
Tt0 f28 callSignature PortalSingleTask(Number) #txt
Tt0 f28 persist false #txt
Tt0 f28 taskData 'TaskTriggered.ROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.EXPRI=2
TaskTriggered.TYPE=0
TaskTriggered.PRI=2
TaskTriggered.EXROL=Everybody' #txt
Tt0 f28 showInStartList 0 #txt
Tt0 f28 taskAndCaseSetupAction 'import ch.ivyteam.ivy.workflow.TaskUpdateDefinition;
ch.ivyteam.ivy.workflow.TaskUpdateDefinition taskUpdDef = new ch.ivyteam.ivy.workflow.TaskUpdateDefinition();
import ch.ivyteam.ivy.request.impl.DefaultCalendarProxy;
DefaultCalendarProxy calendarProxy = ivy.cal as DefaultCalendarProxy;
taskUpdDef.setPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
taskUpdDef.setExpiryActivator("Everybody");
taskUpdDef.setExpiryPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
engine.updateCurrentTask(taskUpdDef);
' #txt
Tt0 f28 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>PortalSingleTask.ivp</name>
        <nameStyle>20,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Tt0 f28 @C|.responsibility Everybody #txt
Tt0 f28 83 699 26 26 -40 22 #rect
Tt0 f28 @|StartRequestIcon #fIcon
Tt0 f29 targetWindow NEW:card: #txt
Tt0 f29 targetDisplay TOP #txt
Tt0 f29 richDialogId ch.ivy.addon.portal.generic.PortalSingleTask #txt
Tt0 f29 startMethod start(Long) #txt
Tt0 f29 type ch.ivy.addon.portal.generic.Data #txt
Tt0 f29 requestActionDecl '<Long taskId> param;' #txt
Tt0 f29 requestMappingAction 'param.taskId=in.taskId;
' #txt
Tt0 f29 responseActionDecl 'ch.ivy.addon.portal.generic.Data out;
' #txt
Tt0 f29 responseMappingAction 'out=in;
' #txt
Tt0 f29 windowConfiguration '* ' #txt
Tt0 f29 isAsynch false #txt
Tt0 f29 isInnerRd false #txt
Tt0 f29 userContext '* ' #txt
Tt0 f29 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>show single
task</name>
        <nameStyle>16,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Tt0 f29 376 690 96 44 -28 -16 #rect
Tt0 f29 @|RichDialogIcon #fIcon
Tt0 f30 expr out #txt
Tt0 f30 109 712 376 712 #arcP
Tt0 f22 outLink PortalCaseListLinkedFromTask.ivp #txt
Tt0 f22 type ch.ivy.addon.portal.generic.Data #txt
Tt0 f22 inParamDecl '<java.lang.String caseName,java.lang.Long caseId,java.lang.Long serverId> param;' #txt
Tt0 f22 inParamTable 'out.caseId=param.caseId;
out.caseName=param.caseName;
out.serverId=param.serverId;
' #txt
Tt0 f22 actionDecl 'ch.ivy.addon.portal.generic.Data out;
' #txt
Tt0 f22 guid 153C00C960B42AF0 #txt
Tt0 f22 requestEnabled true #txt
Tt0 f22 triggerEnabled false #txt
Tt0 f22 callSignature PortalCaseListLinkedFromTask(String,Long,Long) #txt
Tt0 f22 persist false #txt
Tt0 f22 taskData 'TaskTriggered.ROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.EXPRI=2
TaskTriggered.TYPE=0
TaskTriggered.PRI=2
TaskTriggered.EXROL=Everybody' #txt
Tt0 f22 showInStartList 0 #txt
Tt0 f22 taskAndCaseSetupAction 'import ch.ivyteam.ivy.workflow.TaskUpdateDefinition;
ch.ivyteam.ivy.workflow.TaskUpdateDefinition taskUpdDef = new ch.ivyteam.ivy.workflow.TaskUpdateDefinition();
import ch.ivyteam.ivy.request.impl.DefaultCalendarProxy;
DefaultCalendarProxy calendarProxy = ivy.cal as DefaultCalendarProxy;
taskUpdDef.setPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
taskUpdDef.setExpiryActivator("Everybody");
taskUpdDef.setExpiryPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
engine.updateCurrentTask(taskUpdDef);
' #txt
Tt0 f22 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>PortalCaseListLinkedFromTask.ivp</name>
        <nameStyle>32,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Tt0 f22 @C|.responsibility Everybody #txt
Tt0 f22 83 787 26 26 -44 16 #rect
Tt0 f22 @|StartRequestIcon #fIcon
Tt0 f17 type ch.ivy.addon.portal.generic.Data #txt
Tt0 f17 processCall 'Functional Processes/OpenPortalCases:useView(ch.ivy.addon.portal.generic.view.CaseView)' #txt
Tt0 f17 doCall true #txt
Tt0 f17 requestActionDecl '<ch.ivy.addon.portal.generic.view.CaseView view> param;
' #txt
Tt0 f17 requestMappingAction 'param.view=in.caseView;
' #txt
Tt0 f17 responseActionDecl 'ch.ivy.addon.portal.generic.Data out;
' #txt
Tt0 f17 responseMappingAction 'out=in;
' #txt
Tt0 f17 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>OpenPortalCases</name>
        <nameStyle>15,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Tt0 f17 368 776 112 48 -49 -12 #rect
Tt0 f17 @|CallSubIcon #fIcon
Tt0 f11 actionDecl 'ch.ivy.addon.portal.generic.Data out;
' #txt
Tt0 f11 actionTable 'out=in;
' #txt
Tt0 f11 actionCode 'import ch.ivy.addon.portalkit.dto.GlobalCaseId;
import java.util.Arrays;
import ch.ivy.addon.portalkit.datamodel.CaseLazyDataModel;
import ch.ivy.addon.portal.generic.view.CaseView;

String title = ivy.cms.co("/ch.ivy.addon.portalkit.ui.jsf/caseList/relatedCaseHeader", Arrays.asList(in.caseId.toString(), in.caseName));
CaseLazyDataModel dataModel = new CaseLazyDataModel();
dataModel.setCaseId(in.caseId);
in.caseView = CaseView.create().dataModel(dataModel).withTitle(title).autoSelectIfExists(GlobalCaseId.inServer(in.serverId).caseId(in.caseId).build()).buildNewView();' #txt
Tt0 f11 type ch.ivy.addon.portal.generic.Data #txt
Tt0 f11 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>prepare case view</name>
        <nameStyle>17,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Tt0 f11 278 788 36 24 -43 15 #rect
Tt0 f11 @|StepIcon #fIcon
Tt0 f23 expr out #txt
Tt0 f23 109 800 278 800 #arcP
Tt0 f18 expr out #txt
Tt0 f18 314 800 368 800 #arcP
Tt0 f9 expr out #txt
Tt0 f9 109 448 384 448 #arcP
Tt0 f24 type ch.ivy.addon.portal.generic.Data #txt
Tt0 f24 processCall 'Functional Processes/OpenPortalTasks:useView(ch.ivy.addon.portal.generic.view.TaskView)' #txt
Tt0 f24 doCall true #txt
Tt0 f24 requestActionDecl '<ch.ivy.addon.portal.generic.view.TaskView taskView> param;
' #txt
Tt0 f24 requestMappingAction 'param.taskView=in.taskView;
' #txt
Tt0 f24 responseActionDecl 'ch.ivy.addon.portal.generic.Data out;
' #txt
Tt0 f24 responseMappingAction 'out=in;
' #txt
Tt0 f24 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>OpenPortalTasks</name>
        <nameStyle>15,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Tt0 f24 392 328 112 48 -48 -12 #rect
Tt0 f24 @|CallSubIcon #fIcon
Tt0 f16 actionDecl 'ch.ivy.addon.portal.generic.Data out;
' #txt
Tt0 f16 actionTable 'out=in;
' #txt
Tt0 f16 actionCode 'import ch.ivy.addon.portalkit.datamodel.TaskLazyDataModel;
import java.util.Arrays;
import ch.ivy.addon.portalkit.bo.MainMenuNode;
import ch.ivy.addon.portal.generic.view.TaskView;
MainMenuNode category = new MainMenuNode();
category.value = ivy.cms.co("/ch.ivy.addon.portalkit.ui.jsf/searchResult/searchResultsFor", Arrays.asList(ivy.cms.co("/ch.ivy.addon.portalkit.ui.jsf/searchResult/task"), in.keyword));
String pageTitle = category.value;
TaskLazyDataModel dataModel = new TaskLazyDataModel();
dataModel.setSortType(ch.ivy.addon.portalkit.enums.SortType.BY_PRIORITY);
in.taskView = TaskView.create().category(category).pageTitle(pageTitle).keyword(in.keyword).remoteTaskId(in.taskId).serverId(in.serverId).dataModel(dataModel).createNewTaskView();
' #txt
Tt0 f16 type ch.ivy.addon.portal.generic.Data #txt
Tt0 f16 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>build data model,
 task </name>
        <nameStyle>24
</nameStyle>
    </language>
</elementInfo>
' #txt
Tt0 f16 184 328 128 48 -43 -16 #rect
Tt0 f16 @|StepIcon #fIcon
Tt0 f32 expr out #txt
Tt0 f32 109 352 184 352 #arcP
Tt0 f31 expr out #txt
Tt0 f31 312 352 392 352 #arcP
Tt0 f14 actionDecl 'ch.ivy.addon.portal.generic.Data out;
' #txt
Tt0 f14 actionTable 'out=in;
' #txt
Tt0 f14 actionCode 'import ch.ivy.addon.portalkit.datamodel.TaskLazyDataModel;
import ch.ivy.addon.portalkit.bo.MainMenuNode;
import ch.ivy.addon.portal.generic.view.TaskView;

import ch.ivyteam.ivy.server.ServerFactory;
import ch.ivyteam.ivy.request.RequestUriFactory;
import ch.ivy.addon.portalkit.util.SecurityServiceUtils;
import java.util.Arrays;

StringBuilder sb = new StringBuilder();
sb.append("<a class=''ui-link ui-widget'' href=''/");
sb.append(RequestUriFactory.getIvyContextName(ServerFactory.getServer().getApplicationConfigurationManager()));
sb.append(SecurityServiceUtils.findProcessByUserFriendlyRequestPath("Start Processes/PortalTemplatePages/PortalCaseListLinkedFromTask.ivp"));
sb.append("?caseId=");
sb.append(in.caseId);
sb.append("&amp;caseName=");
sb.append(in.caseName);
sb.append("&amp;serverId=");
sb.append(in.serverId);
sb.append("''>#");
sb.append(in.caseId);
sb.append("</a>");

MainMenuNode category = new MainMenuNode();
category.value = ivy.cms.co("/ch.ivy.addon.portalkit.ui.jsf/taskList/headerTitle/relatedTasksHeader", Arrays.asList(sb.toString(), in.caseName));
String pageTitle = ivy.cms.co("/ch.ivy.addon.portalkit.ui.jsf/taskList/headerTitle/relatedTasksHeader", Arrays.asList("#" + in.caseId.toString(), in.caseName));

TaskLazyDataModel dataModel = new TaskLazyDataModel();
dataModel.setCaseId(in.caseId);
dataModel.setIgnoreInvolvedUser(true);
dataModel.setSortType(ch.ivy.addon.portalkit.enums.SortType.BY_PRIORITY);

in.taskView = TaskView.create().category(category).pageTitle(pageTitle).dataModel(dataModel).createNewTaskView();' #txt
Tt0 f14 security system #txt
Tt0 f14 type ch.ivy.addon.portal.generic.Data #txt
Tt0 f14 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>build data model,
 task </name>
        <nameStyle>24
</nameStyle>
    </language>
</elementInfo>
' #txt
Tt0 f14 192 612 112 40 -43 -16 #rect
Tt0 f14 @|StepIcon #fIcon
Tt0 f33 expr out #txt
Tt0 f33 109 632 192 632 #arcP
Tt0 f27 expr out #txt
Tt0 f27 304 632 368 632 #arcP
Tt0 f26 type ch.ivy.addon.portal.generic.Data #txt
Tt0 f26 processCall 'Functional Processes/OpenPortalTasks:useView(ch.ivy.addon.portal.generic.view.TaskView)' #txt
Tt0 f26 doCall true #txt
Tt0 f26 requestActionDecl '<ch.ivy.addon.portal.generic.view.TaskView taskView> param;
' #txt
Tt0 f26 requestMappingAction 'param.taskView=in.taskView;
' #txt
Tt0 f26 responseActionDecl 'ch.ivy.addon.portal.generic.Data out;
' #txt
Tt0 f26 responseMappingAction 'out=in;
' #txt
Tt0 f26 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>OpenPortalTasks</name>
        <nameStyle>15,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Tt0 f26 368 608 112 48 -48 -12 #rect
Tt0 f26 @|CallSubIcon #fIcon
>Proto Tt0 .type ch.ivy.addon.portal.generic.Data #txt
>Proto Tt0 .processKind NORMAL #txt
>Proto Tt0 0 0 32 24 18 0 #rect
>Proto Tt0 @|BIcon #fIcon
Tt0 f1 out f8 tail #connect
Tt0 f8 head f5 mainIn #connect
Tt0 f0 mainOut f10 tail #connect
Tt0 f10 head f1 in #connect
Tt0 f2 mainOut f13 tail #connect
Tt0 f13 head f12 mainIn #connect
Tt0 f6 mainOut f4 tail #connect
Tt0 f4 head f1 in #connect
Tt0 f19 mainOut f21 tail #connect
Tt0 f21 head f20 mainIn #connect
Tt0 f28 mainOut f30 tail #connect
Tt0 f30 head f29 mainIn #connect
Tt0 f22 mainOut f23 tail #connect
Tt0 f23 head f11 mainIn #connect
Tt0 f11 mainOut f18 tail #connect
Tt0 f18 head f17 mainIn #connect
Tt0 f3 mainOut f9 tail #connect
Tt0 f9 head f7 mainIn #connect
Tt0 f15 mainOut f32 tail #connect
Tt0 f32 head f16 mainIn #connect
Tt0 f16 mainOut f31 tail #connect
Tt0 f31 head f24 mainIn #connect
Tt0 f25 mainOut f33 tail #connect
Tt0 f33 head f14 mainIn #connect
Tt0 f14 mainOut f27 tail #connect
Tt0 f27 head f26 mainIn #connect
