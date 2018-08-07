[Ivy]
[>Created: Wed Oct 07 15:46:15 ICT 2015]
14B71B1F2C58A347 3.17 #module
>Proto >Proto Collection #zClass
Pn0 ProcessChain Big #zClass
Pn0 B #cInfo
Pn0 #process
Pn0 @TextInP .resExport .resExport #zField
Pn0 @TextInP .type .type #zField
Pn0 @TextInP .processKind .processKind #zField
Pn0 @AnnotationInP-0n ai ai #zField
Pn0 @TextInP .xml .xml #zField
Pn0 @TextInP .responsibility .responsibility #zField
Pn0 @StartRequest f0 '' #zField
Pn0 @EndTask f1 '' #zField
Pn0 @RichDialog f2 '' #zField
Pn0 @PushWFArc f3 '' #zField
Pn0 @PushWFArc f4 '' #zField
>Proto Pn0 Pn0 ProcessChain #zField
Pn0 f0 outLink start.ivp #txt
Pn0 f0 type portalKit_test.Data #txt
Pn0 f0 inParamDecl '<> param;' #txt
Pn0 f0 actionDecl 'portalKit_test.Data out;
' #txt
Pn0 f0 guid 14B71B20065B2E41 #txt
Pn0 f0 requestEnabled true #txt
Pn0 f0 triggerEnabled false #txt
Pn0 f0 callSignature start() #txt
Pn0 f0 persist false #txt
Pn0 f0 startName 'Internal using process chain' #txt
Pn0 f0 taskData '#
#Fri Apr 24 16:44:27 ICT 2015
TaskTriggered.ROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.EXPRI=2
TaskTriggered.TYPE=0
TaskTriggered.PRI=2
TaskTriggered.EXROL=Everybody
' #txt
Pn0 f0 caseData '#
#Fri Apr 24 16:44:27 ICT 2015
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
Pn0 f0 showInStartList 1 #txt
Pn0 f0 taskAndCaseSetupAction 'import ch.ivyteam.ivy.workflow.TaskUpdateDefinition;
ch.ivyteam.ivy.workflow.TaskUpdateDefinition taskUpdDef = new ch.ivyteam.ivy.workflow.TaskUpdateDefinition();
import ch.ivyteam.ivy.request.impl.DefaultCalendarProxy;
DefaultCalendarProxy calendarProxy = ivy.cal as DefaultCalendarProxy;
taskUpdDef.setPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
taskUpdDef.setExpiryActivator("Everybody");
taskUpdDef.setExpiryPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
engine.updateCurrentTask(taskUpdDef);
' #txt
Pn0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start.ivp</name>
        <nameStyle>9,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Pn0 f0 @C|.responsibility Everybody #txt
Pn0 f0 147 19 26 26 14 0 #rect
Pn0 f0 @|StartRequestIcon #fIcon
Pn0 f1 type portalKit_test.Data #txt
Pn0 f1 147 275 26 26 14 0 #rect
Pn0 f1 @|EndIcon #fIcon
Pn0 f2 targetWindow NEW:card: #txt
Pn0 f2 targetDisplay TOP #txt
Pn0 f2 richDialogId ch.ivy.addon.portalkit.showroom.singleapp.process.SampleProcessChain #txt
Pn0 f2 startMethod start() #txt
Pn0 f2 type portalKit_test.Data #txt
Pn0 f2 requestActionDecl '<> param;' #txt
Pn0 f2 responseActionDecl 'portalKit_test.Data out;
' #txt
Pn0 f2 responseMappingAction 'out=in;
' #txt
Pn0 f2 windowConfiguration '* ' #txt
Pn0 f2 isAsynch false #txt
Pn0 f2 isInnerRd false #txt
Pn0 f2 userContext '* ' #txt
Pn0 f2 142 148 36 24 20 -2 #rect
Pn0 f2 @|RichDialogIcon #fIcon
Pn0 f3 expr out #txt
Pn0 f3 160 45 160 148 #arcP
Pn0 f4 expr out #txt
Pn0 f4 160 172 160 275 #arcP
>Proto Pn0 .type portalKit_test.Data #txt
>Proto Pn0 .processKind NORMAL #txt
>Proto Pn0 0 0 32 24 18 0 #rect
>Proto Pn0 @|BIcon #fIcon
Pn0 f0 mainOut f3 tail #connect
Pn0 f3 head f2 mainIn #connect
Pn0 f2 mainOut f4 tail #connect
Pn0 f4 head f1 mainIn #connect
