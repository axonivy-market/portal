[Ivy]
[>Created: Tue Jan 19 10:02:47 ICT 2016]
14CE583590E40169 3.18 #module
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
Ae0 @PushWFArc f5 '' #zField
>Proto Ae0 Ae0 InternalPortalHome #zField
Ae0 f0 outLink start.ivp #txt
Ae0 f0 type test005ProjectManagement.PortalData #txt
Ae0 f0 inParamDecl '<java.lang.Number taskIdentifier> param;' #txt
Ae0 f0 inParamTable 'out.taskId=param.taskIdentifier;
' #txt
Ae0 f0 actionDecl 'test005ProjectManagement.PortalData out;
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
Ae0 f6 type test005ProjectManagement.PortalData #txt
Ae0 f6 requestActionDecl '<> param;' #txt
Ae0 f6 responseActionDecl 'test005ProjectManagement.PortalData out;
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
Ae0 f3 type test005ProjectManagement.PortalData #txt
Ae0 f3 inParamDecl '<java.lang.Number caseId> param;' #txt
Ae0 f3 inParamTable 'out.caseId=param.caseId;
' #txt
Ae0 f3 actionDecl 'test005ProjectManagement.PortalData out;
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
Ae0 f8 type test005ProjectManagement.PortalData #txt
Ae0 f8 requestActionDecl '<Number caseId> param;' #txt
Ae0 f8 requestMappingAction 'param.caseId=in.caseId;
' #txt
Ae0 f8 responseActionDecl 'test005ProjectManagement.PortalData out;
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
Ae0 f5 expr out #txt
Ae0 f5 111 64 254 64 #arcP
>Proto Ae0 .type test005ProjectManagement.PortalData #txt
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
Ae0 f0 mainOut f5 tail #connect
Ae0 f5 head f6 mainIn #connect
