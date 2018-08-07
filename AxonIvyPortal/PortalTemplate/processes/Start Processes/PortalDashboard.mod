[Ivy]
[>Created: Fri Nov 20 10:34:36 ICT 2015]
14EB90ED21549D9F 3.18 #module
>Proto >Proto Collection #zClass
Pd0 PortalDashboard Big #zClass
Pd0 B #cInfo
Pd0 #process
Pd0 @TextInP .resExport .resExport #zField
Pd0 @TextInP .type .type #zField
Pd0 @TextInP .processKind .processKind #zField
Pd0 @AnnotationInP-0n ai ai #zField
Pd0 @MessageFlowInP-0n messageIn messageIn #zField
Pd0 @MessageFlowOutP-0n messageOut messageOut #zField
Pd0 @TextInP .xml .xml #zField
Pd0 @TextInP .responsibility .responsibility #zField
Pd0 @StartRequest f0 '' #zField
Pd0 @RichDialog f1 '' #zField
Pd0 @PushWFArc f2 '' #zField
>Proto Pd0 Pd0 PortalDashboard #zField
Pd0 f0 outLink start.ivp #txt
Pd0 f0 type ch.ivy.addon.portal.generic.Data #txt
Pd0 f0 inParamDecl '<> param;' #txt
Pd0 f0 actionDecl 'ch.ivy.addon.portal.generic.Data out;
' #txt
Pd0 f0 guid 14EB90FF98CC210D #txt
Pd0 f0 requestEnabled true #txt
Pd0 f0 triggerEnabled false #txt
Pd0 f0 callSignature start() #txt
Pd0 f0 persist false #txt
Pd0 f0 taskData '#
#Thu Jul 23 16:02:58 ICT 2015
TaskTriggered.ROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.EXPRI=2
TaskTriggered.TYPE=0
TaskTriggered.PRI=2
TaskTriggered.EXROL=Everybody
' #txt
Pd0 f0 caseData '#
#Thu Jul 23 16:02:58 ICT 2015
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
Pd0 f0 showInStartList 0 #txt
Pd0 f0 taskAndCaseSetupAction 'import ch.ivyteam.ivy.workflow.TaskUpdateDefinition;
ch.ivyteam.ivy.workflow.TaskUpdateDefinition taskUpdDef = new ch.ivyteam.ivy.workflow.TaskUpdateDefinition();
import ch.ivyteam.ivy.request.impl.DefaultCalendarProxy;
DefaultCalendarProxy calendarProxy = ivy.cal as DefaultCalendarProxy;
taskUpdDef.setPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
taskUpdDef.setExpiryActivator("Everybody");
taskUpdDef.setExpiryPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
engine.updateCurrentTask(taskUpdDef);
' #txt
Pd0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start.ivp</name>
        <nameStyle>9,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Pd0 f0 @C|.responsibility Everybody #txt
Pd0 f0 50 82 28 28 -21 16 #rect
Pd0 f0 @|StartRequestIcon #fIcon
Pd0 f1 targetWindow NEW:card: #txt
Pd0 f1 targetDisplay TOP #txt
Pd0 f1 richDialogId ch.ivy.addon.portal.generic.admin.PortalDashBoard #txt
Pd0 f1 startMethod start() #txt
Pd0 f1 type ch.ivy.addon.portal.generic.Data #txt
Pd0 f1 requestActionDecl '<> param;' #txt
Pd0 f1 responseActionDecl 'ch.ivy.addon.portal.generic.Data out;
' #txt
Pd0 f1 responseMappingAction 'out=in;
' #txt
Pd0 f1 windowConfiguration '* ' #txt
Pd0 f1 isAsynch false #txt
Pd0 f1 isInnerRd false #txt
Pd0 f1 userContext '* ' #txt
Pd0 f1 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Open dashboard</name>
        <nameStyle>14,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Pd0 f1 144 72 144 48 -43 -8 #rect
Pd0 f1 @|RichDialogIcon #fIcon
Pd0 f2 expr out #txt
Pd0 f2 78 96 144 96 #arcP
>Proto Pd0 .type ch.ivy.addon.portal.generic.Data #txt
>Proto Pd0 .processKind NORMAL #txt
>Proto Pd0 0 0 32 24 18 0 #rect
>Proto Pd0 @|BIcon #fIcon
Pd0 f0 mainOut f2 tail #connect
Pd0 f2 head f1 mainIn #connect
