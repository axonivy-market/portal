[Ivy]
[>Created: Tue Jan 19 10:02:34 ICT 2016]
14CE57D427DB4684 3.18 #module
>Proto >Proto Collection #zClass
Ae0 InternalPortalHome Big #zClass
Ae0 B #cInfo
Ae0 #process
Ae0 @TextInP .resExport .resExport #zField
Ae0 @TextInP .type .type #zField
Ae0 @TextInP .processKind .processKind #zField
Ae0 @AnnotationInP-0n ai ai #zField
Ae0 @MessageFlowInP-0n messageIn messageIn #zField
Ae0 @MessageFlowOutP-0n messageOut messageOut #zField
Ae0 @TextInP .xml .xml #zField
Ae0 @TextInP .responsibility .responsibility #zField
Ae0 @StartRequest f0 '' #zField
Ae0 @RichDialog f6 '' #zField
Ae0 @StartRequest f3 '' #zField
Ae0 @RichDialog f8 '' #zField
Ae0 @PushWFArc f9 '' #zField
Ae0 @RichDialog f11 '' #zField
Ae0 @StartRequest f10 '' #zField
Ae0 @PushWFArc f12 '' #zField
Ae0 @StartRequest f13 '' #zField
Ae0 @RichDialog f14 '' #zField
Ae0 @PushWFArc f15 '' #zField
Ae0 @PushWFArc f16 '' #zField
>Proto Ae0 Ae0 InternalPortalHome #zField
Ae0 f0 outLink start.ivp #txt
Ae0 f0 type test004QualityManagement.PortalData #txt
Ae0 f0 inParamDecl '<java.lang.Number taskIdentifier> param;' #txt
Ae0 f0 inParamTable 'out.taskId=param.taskIdentifier;
' #txt
Ae0 f0 actionDecl 'test004QualityManagement.PortalData out;
' #txt
Ae0 f0 guid 14BEE532267D336E #txt
Ae0 f0 requestEnabled true #txt
Ae0 f0 triggerEnabled false #txt
Ae0 f0 callSignature start(Number) #txt
Ae0 f0 persist false #txt
Ae0 f0 taskData '#
#Fri Apr 03 09:35:22 ICT 2015
TaskTriggered.ROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.EXPRI=2
TaskTriggered.TYPE=0
TaskTriggered.PRI=2
TaskTriggered.EXROL=Everybody
' #txt
Ae0 f0 caseData '#
#Fri Apr 03 09:35:22 ICT 2015
businessCalendarName=
businessCreator.user=
businessMilestone.timestamp=
businessObject.code=
businessObject.docDb.code=
businessObject.folder.id=
businessObject.name=
businessPriority=
businessStart.timestamp=
case.description=
case.name=
correspondent.id=
mainContact.docDb.code=
mainContact.folder.id=
mainContact.id=
mainContact.name=
mainContact.type=
process.code=
process.name=
processCategory.code=
processCategory.name=
subType.code=
subType.name=
type.code=
type.name=
' #txt
Ae0 f0 showInStartList 1 #txt
Ae0 f0 taskAndCaseSetupAction 'import ch.ivyteam.ivy.workflow.TaskUpdateDefinition;
ch.ivyteam.ivy.workflow.TaskUpdateDefinition taskUpdDef = new ch.ivyteam.ivy.workflow.TaskUpdateDefinition();
import ch.ivyteam.ivy.request.impl.DefaultCalendarProxy;
DefaultCalendarProxy calendarProxy = ivy.cal as DefaultCalendarProxy;
taskUpdDef.setPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
taskUpdDef.setExpiryActivator("Everybody");
taskUpdDef.setExpiryPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
engine.updateCurrentTask(taskUpdDef);
' #txt
Ae0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start.ivp</name>
        <nameStyle>9,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ae0 f0 @C|.responsibility Everybody #txt
Ae0 f0 81 49 30 30 -21 17 #rect
Ae0 f0 @|StartRequestIcon #fIcon
Ae0 f6 targetWindow NEW:card: #txt
Ae0 f6 targetDisplay TOP #txt
Ae0 f6 richDialogId portal.InternalPortalHome #txt
Ae0 f6 startMethod start() #txt
Ae0 f6 type test004QualityManagement.PortalData #txt
Ae0 f6 requestActionDecl '<> param;' #txt
Ae0 f6 responseActionDecl 'test004QualityManagement.PortalData out;
' #txt
Ae0 f6 responseMappingAction 'out=in;
' #txt
Ae0 f6 windowConfiguration '* ' #txt
Ae0 f6 isAsynch false #txt
Ae0 f6 isInnerRd false #txt
Ae0 f6 userContext '* ' #txt
Ae0 f6 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>homepage</name>
        <nameStyle>8,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ae0 f6 254 52 36 24 23 4 #rect
Ae0 f6 @|RichDialogIcon #fIcon
Ae0 f3 outLink caseDetails.ivp #txt
Ae0 f3 type test004QualityManagement.PortalData #txt
Ae0 f3 inParamDecl '<java.lang.Number caseId> param;' #txt
Ae0 f3 inParamTable 'out.caseId=param.caseId;
' #txt
Ae0 f3 actionDecl 'test004QualityManagement.PortalData out;
' #txt
Ae0 f3 guid 14C9DEEEB08F6031 #txt
Ae0 f3 requestEnabled true #txt
Ae0 f3 triggerEnabled false #txt
Ae0 f3 callSignature caseDetails(Number) #txt
Ae0 f3 persist false #txt
Ae0 f3 taskData '#
#Wed Apr 15 14:09:38 ICT 2015
TaskTriggered.ROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.EXPRI=2
TaskTriggered.TYPE=0
TaskTriggered.PRI=2
TaskTriggered.EXROL=Everybody
' #txt
Ae0 f3 caseData '#
#Wed Apr 15 14:09:38 ICT 2015
businessCalendarName=
businessCreator.user=
businessMilestone.timestamp=
businessObject.code=
businessObject.docDb.code=
businessObject.folder.id=
businessObject.name=
businessPriority=
businessStart.timestamp=
case.description=
case.name=
correspondent.id=
mainContact.docDb.code=
mainContact.folder.id=
mainContact.id=
mainContact.name=
mainContact.type=
process.code=
process.name=
processCategory.code=
processCategory.name=
subType.code=
subType.name=
type.code=
type.name=
' #txt
Ae0 f3 showInStartList 0 #txt
Ae0 f3 taskAndCaseSetupAction 'import ch.ivyteam.ivy.workflow.TaskUpdateDefinition;
ch.ivyteam.ivy.workflow.TaskUpdateDefinition taskUpdDef = new ch.ivyteam.ivy.workflow.TaskUpdateDefinition();
import ch.ivyteam.ivy.request.impl.DefaultCalendarProxy;
DefaultCalendarProxy calendarProxy = ivy.cal as DefaultCalendarProxy;
taskUpdDef.setPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
taskUpdDef.setExpiryActivator("Everybody");
taskUpdDef.setExpiryPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
engine.updateCurrentTask(taskUpdDef);
' #txt
Ae0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>caseDetails</name>
        <nameStyle>11,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ae0 f3 @C|.responsibility Everybody #txt
Ae0 f3 75 211 26 26 14 0 #rect
Ae0 f3 @|StartRequestIcon #fIcon
Ae0 f8 targetWindow NEW:card: #txt
Ae0 f8 targetDisplay TOP #txt
Ae0 f8 richDialogId portal.casedetails.PortalCaseDetails #txt
Ae0 f8 startMethod start(Number) #txt
Ae0 f8 type test004QualityManagement.PortalData #txt
Ae0 f8 requestActionDecl '<Number caseId> param;' #txt
Ae0 f8 requestMappingAction 'param.caseId=in.caseId;
' #txt
Ae0 f8 responseActionDecl 'test004QualityManagement.PortalData out;
' #txt
Ae0 f8 responseMappingAction 'out=in;
' #txt
Ae0 f8 windowConfiguration '* ' #txt
Ae0 f8 isAsynch false #txt
Ae0 f8 isInnerRd false #txt
Ae0 f8 userContext '* ' #txt
Ae0 f8 254 212 36 24 20 -2 #rect
Ae0 f8 @|RichDialogIcon #fIcon
Ae0 f9 expr out #txt
Ae0 f9 101 224 254 224 #arcP
Ae0 f11 targetWindow NEW:card: #txt
Ae0 f11 targetDisplay TOP #txt
Ae0 f11 richDialogId portal.caselist.AuditStagePrepareCaseList #txt
Ae0 f11 startMethod start() #txt
Ae0 f11 type test004QualityManagement.PortalData #txt
Ae0 f11 requestActionDecl '<> param;' #txt
Ae0 f11 responseActionDecl 'test004QualityManagement.PortalData out;
' #txt
Ae0 f11 responseMappingAction 'out=in;
' #txt
Ae0 f11 windowConfiguration '* ' #txt
Ae0 f11 isAsynch false #txt
Ae0 f11 isInnerRd false #txt
Ae0 f11 userContext '* ' #txt
Ae0 f11 254 285 36 22 20 -2 #rect
Ae0 f11 @|RichDialogIcon #fIcon
Ae0 f10 outLink InternalAuditPrepareCaseList.ivp #txt
Ae0 f10 type test004QualityManagement.PortalData #txt
Ae0 f10 inParamDecl '<> param;' #txt
Ae0 f10 actionDecl 'test004QualityManagement.PortalData out;
' #txt
Ae0 f10 guid 14CEA95FA02860BA #txt
Ae0 f10 requestEnabled true #txt
Ae0 f10 triggerEnabled false #txt
Ae0 f10 callSignature InternalAuditPrepareCaseList() #txt
Ae0 f10 persist false #txt
Ae0 f10 taskData '#
#Fri Apr 24 15:56:38 ICT 2015
TaskTriggered.ROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.EXPRI=2
TaskTriggered.TYPE=0
TaskTriggered.PRI=2
TaskTriggered.EXROL=Everybody
' #txt
Ae0 f10 caseData '#
#Fri Apr 24 15:56:38 ICT 2015
businessCalendarName=
businessCreator.user=
businessMilestone.timestamp=
businessObject.code=
businessObject.docDb.code=
businessObject.folder.id=
businessObject.name=
businessPriority=
businessStart.timestamp=
case.description=
case.name=
correspondent.id=
mainContact.docDb.code=
mainContact.folder.id=
mainContact.id=
mainContact.name=
mainContact.type=
process.code=
process.name=
processCategory.code=
processCategory.name=
subType.code=
subType.name=
type.code=
type.name=
' #txt
Ae0 f10 showInStartList 0 #txt
Ae0 f10 taskAndCaseSetupAction 'import ch.ivyteam.ivy.workflow.TaskUpdateDefinition;
ch.ivyteam.ivy.workflow.TaskUpdateDefinition taskUpdDef = new ch.ivyteam.ivy.workflow.TaskUpdateDefinition();
import ch.ivyteam.ivy.request.impl.DefaultCalendarProxy;
DefaultCalendarProxy calendarProxy = ivy.cal as DefaultCalendarProxy;
taskUpdDef.setPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
taskUpdDef.setExpiryActivator("Everybody");
taskUpdDef.setExpiryPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
engine.updateCurrentTask(taskUpdDef);
' #txt
Ae0 f10 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>InternalAuditPrepareCaseList.ivp</name>
    </language>
</elementInfo>
' #txt
Ae0 f10 @C|.responsibility Everybody #txt
Ae0 f10 76 283 25 26 -16 19 #rect
Ae0 f10 @|StartRequestIcon #fIcon
Ae0 f12 expr out #txt
Ae0 f12 100 296 254 296 #arcP
Ae0 f13 outLink InternalAuditStagePerformCaseList.ivp #txt
Ae0 f13 type test004QualityManagement.PortalData #txt
Ae0 f13 inParamDecl '<> param;' #txt
Ae0 f13 actionDecl 'test004QualityManagement.PortalData out;
' #txt
Ae0 f13 guid 14CEAA38220F3C7A #txt
Ae0 f13 requestEnabled true #txt
Ae0 f13 triggerEnabled false #txt
Ae0 f13 callSignature InternalAuditStagePerformCaseList() #txt
Ae0 f13 persist false #txt
Ae0 f13 taskData '#
#Fri Apr 24 15:56:20 ICT 2015
TaskTriggered.ROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.EXPRI=2
TaskTriggered.TYPE=0
TaskTriggered.PRI=2
TaskTriggered.EXROL=Everybody
' #txt
Ae0 f13 caseData '#
#Fri Apr 24 15:56:20 ICT 2015
businessCalendarName=
businessCreator.user=
businessMilestone.timestamp=
businessObject.code=
businessObject.docDb.code=
businessObject.folder.id=
businessObject.name=
businessPriority=
businessStart.timestamp=
case.description=
case.name=
correspondent.id=
mainContact.docDb.code=
mainContact.folder.id=
mainContact.id=
mainContact.name=
mainContact.type=
process.code=
process.name=
processCategory.code=
processCategory.name=
subType.code=
subType.name=
type.code=
type.name=
' #txt
Ae0 f13 showInStartList 0 #txt
Ae0 f13 taskAndCaseSetupAction 'import ch.ivyteam.ivy.workflow.TaskUpdateDefinition;
ch.ivyteam.ivy.workflow.TaskUpdateDefinition taskUpdDef = new ch.ivyteam.ivy.workflow.TaskUpdateDefinition();
import ch.ivyteam.ivy.request.impl.DefaultCalendarProxy;
DefaultCalendarProxy calendarProxy = ivy.cal as DefaultCalendarProxy;
taskUpdDef.setPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
taskUpdDef.setExpiryActivator("Everybody");
taskUpdDef.setExpiryPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
engine.updateCurrentTask(taskUpdDef);
' #txt
Ae0 f13 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>InternalAuditStagePerformCaseList.ivp</name>
    </language>
</elementInfo>
' #txt
Ae0 f13 @C|.responsibility Everybody #txt
Ae0 f13 76 363 25 26 -16 19 #rect
Ae0 f13 @|StartRequestIcon #fIcon
Ae0 f14 targetWindow NEW:card: #txt
Ae0 f14 targetDisplay TOP #txt
Ae0 f14 richDialogId portal.caselist.AuditStagePerformCaseList #txt
Ae0 f14 startMethod start() #txt
Ae0 f14 type test004QualityManagement.PortalData #txt
Ae0 f14 requestActionDecl '<> param;' #txt
Ae0 f14 responseActionDecl 'test004QualityManagement.PortalData out;
' #txt
Ae0 f14 responseMappingAction 'out=in;
' #txt
Ae0 f14 windowConfiguration '* ' #txt
Ae0 f14 isAsynch false #txt
Ae0 f14 isInnerRd false #txt
Ae0 f14 userContext '* ' #txt
Ae0 f14 254 365 36 22 20 -2 #rect
Ae0 f14 @|RichDialogIcon #fIcon
Ae0 f15 expr out #txt
Ae0 f15 100 376 254 376 #arcP
Ae0 f16 expr out #txt
Ae0 f16 111 64 254 64 #arcP
>Proto Ae0 .type test004QualityManagement.PortalData #txt
>Proto Ae0 .processKind NORMAL #txt
>Proto Ae0 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language/>
</elementInfo>
' #txt
>Proto Ae0 0 0 32 24 18 0 #rect
>Proto Ae0 @|BIcon #fIcon
Ae0 f3 mainOut f9 tail #connect
Ae0 f9 head f8 mainIn #connect
Ae0 f10 mainOut f12 tail #connect
Ae0 f12 head f11 mainIn #connect
Ae0 f13 mainOut f15 tail #connect
Ae0 f15 head f14 mainIn #connect
Ae0 f0 mainOut f16 tail #connect
Ae0 f16 head f6 mainIn #connect
