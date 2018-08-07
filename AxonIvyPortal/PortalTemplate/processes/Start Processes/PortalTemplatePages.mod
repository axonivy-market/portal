[Ivy]
[>Created: Thu Mar 03 19:19:41 ICT 2016]
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
Tt0 @RichDialog f14 '' #zField
Tt0 @StartRequest f15 '' #zField
Tt0 @PushWFArc f16 '' #zField
Tt0 @PushWFArc f4 '' #zField
Tt0 @StartRequest f3 '' #zField
Tt0 @RichDialog f7 '' #zField
Tt0 @PushWFArc f9 '' #zField
Tt0 @StartRequest f11 '' #zField
Tt0 @RichDialog f17 '' #zField
Tt0 @PushWFArc f18 '' #zField
Tt0 @StartRequest f19 '' #zField
Tt0 @RichDialog f20 '' #zField
Tt0 @PushWFArc f21 '' #zField
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
Tt0 f6 startName 'Portal Home' #txt
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
Tt0 f14 targetWindow NEW:card: #txt
Tt0 f14 targetDisplay TOP #txt
Tt0 f14 richDialogId ch.ivy.addon.portal.generic.PortalTasks #txt
Tt0 f14 startMethod start(String,java.lang.Long,java.lang.Long) #txt
Tt0 f14 type ch.ivy.addon.portal.generic.Data #txt
Tt0 f14 requestActionDecl '<String keyword, java.lang.Long remoteTaskId, java.lang.Long serverId> param;' #txt
Tt0 f14 requestMappingAction 'param.keyword=in.keyword;
param.remoteTaskId=in.taskId;
param.serverId=in.serverId;
' #txt
Tt0 f14 responseActionDecl 'ch.ivy.addon.portal.generic.Data out;
' #txt
Tt0 f14 responseMappingAction 'out=in;
' #txt
Tt0 f14 windowConfiguration '* ' #txt
Tt0 f14 isAsynch false #txt
Tt0 f14 isInnerRd false #txt
Tt0 f14 userContext '* ' #txt
Tt0 f14 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>show task 
detail</name>
        <nameStyle>17
</nameStyle>
    </language>
</elementInfo>
' #txt
Tt0 f14 380 332 88 40 -22 -16 #rect
Tt0 f14 @|RichDialogIcon #fIcon
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
Tt0 f16 expr out #txt
Tt0 f16 109 352 380 352 #arcP
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
Tt0 f7 384 424 96 48 -22 -8 #rect
Tt0 f7 @|RichDialogIcon #fIcon
Tt0 f9 expr out #txt
Tt0 f9 109 448 384 448 #arcP
Tt0 f11 outLink PortalCaseList.ivp #txt
Tt0 f11 type ch.ivy.addon.portal.generic.Data #txt
Tt0 f11 inParamDecl '<java.lang.Long serverId,java.lang.String keyword,java.lang.Long caseId> param;' #txt
Tt0 f11 inParamTable 'out.caseId=param.caseId;
out.keyword=param.keyword;
out.serverId=param.serverId;
' #txt
Tt0 f11 actionDecl 'ch.ivy.addon.portal.generic.Data out;
' #txt
Tt0 f11 guid 153161861E02950D #txt
Tt0 f11 requestEnabled true #txt
Tt0 f11 triggerEnabled false #txt
Tt0 f11 callSignature PortalCaseList(Long,String,Long) #txt
Tt0 f11 persist false #txt
Tt0 f11 taskData 'TaskTriggered.ROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.EXPRI=2
TaskTriggered.TYPE=0
TaskTriggered.PRI=2
TaskTriggered.EXROL=Everybody' #txt
Tt0 f11 showInStartList 0 #txt
Tt0 f11 taskAndCaseSetupAction 'import ch.ivyteam.ivy.workflow.TaskUpdateDefinition;
ch.ivyteam.ivy.workflow.TaskUpdateDefinition taskUpdDef = new ch.ivyteam.ivy.workflow.TaskUpdateDefinition();
import ch.ivyteam.ivy.request.impl.DefaultCalendarProxy;
DefaultCalendarProxy calendarProxy = ivy.cal as DefaultCalendarProxy;
taskUpdDef.setPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
taskUpdDef.setExpiryActivator("Everybody");
taskUpdDef.setExpiryPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
engine.updateCurrentTask(taskUpdDef);
' #txt
Tt0 f11 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>PortalCaseList.ivp</name>
        <nameStyle>18,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Tt0 f11 @C|.responsibility Everybody #txt
Tt0 f11 83 531 26 26 14 0 #rect
Tt0 f11 @|StartRequestIcon #fIcon
Tt0 f17 targetWindow NEW:card: #txt
Tt0 f17 targetDisplay TOP #txt
Tt0 f17 richDialogId ch.ivy.addon.portal.generic.PortalCaseList #txt
Tt0 f17 startMethod start(String,java.lang.Long,java.lang.Long) #txt
Tt0 f17 type ch.ivy.addon.portal.generic.Data #txt
Tt0 f17 requestActionDecl '<String keyword, java.lang.Long selectedCaseId, java.lang.Long serverId> param;' #txt
Tt0 f17 requestMappingAction 'param.keyword=in.keyword;
param.selectedCaseId=in.caseId;
param.serverId=in.serverId;
' #txt
Tt0 f17 responseActionDecl 'ch.ivy.addon.portal.generic.Data out;
' #txt
Tt0 f17 responseMappingAction 'out=in;
' #txt
Tt0 f17 windowConfiguration '* ' #txt
Tt0 f17 isAsynch false #txt
Tt0 f17 isInnerRd false #txt
Tt0 f17 userContext '* ' #txt
Tt0 f17 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Case List</name>
        <nameStyle>9
</nameStyle>
    </language>
</elementInfo>
' #txt
Tt0 f17 398 532 36 24 20 -2 #rect
Tt0 f17 @|RichDialogIcon #fIcon
Tt0 f18 expr out #txt
Tt0 f18 109 544 398 544 #arcP
Tt0 f19 outLink PortalProcessList.ivp #txt
Tt0 f19 type ch.ivy.addon.portal.generic.Data #txt
Tt0 f19 inParamDecl '<java.lang.String processFullRequestPath,java.lang.String keyword> param;' #txt
Tt0 f19 inParamTable 'out.keyword=param.keyword;
out.processFullRequestPath=param.processFullRequestPath;
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
Tt0 f19 83 627 26 26 14 0 #rect
Tt0 f19 @|StartRequestIcon #fIcon
Tt0 f20 targetWindow NEW:card: #txt
Tt0 f20 targetDisplay TOP #txt
Tt0 f20 richDialogId ch.ivy.addon.portal.generic.Processes #txt
Tt0 f20 startMethod start(String,String) #txt
Tt0 f20 type ch.ivy.addon.portal.generic.Data #txt
Tt0 f20 requestActionDecl '<String keyword, String processFullRequestPath> param;' #txt
Tt0 f20 requestMappingAction 'param.keyword=in.keyword;
param.processFullRequestPath=in.processFullRequestPath;
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
Tt0 f20 398 628 36 24 20 -2 #rect
Tt0 f20 @|RichDialogIcon #fIcon
Tt0 f21 expr out #txt
Tt0 f21 109 640 398 640 #arcP
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
Tt0 f15 mainOut f16 tail #connect
Tt0 f16 head f14 mainIn #connect
Tt0 f6 mainOut f4 tail #connect
Tt0 f4 head f1 in #connect
Tt0 f3 mainOut f9 tail #connect
Tt0 f9 head f7 mainIn #connect
Tt0 f11 mainOut f18 tail #connect
Tt0 f18 head f17 mainIn #connect
Tt0 f19 mainOut f21 tail #connect
Tt0 f21 head f20 mainIn #connect
