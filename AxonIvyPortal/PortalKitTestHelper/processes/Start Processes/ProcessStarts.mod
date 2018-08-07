[Ivy]
[>Created: Fri Apr 24 16:45:30 ICT 2015]
14B3878D8CD7D237 3.17 #module
>Proto >Proto Collection #zClass
Ps0 ProcessStarts Big #zClass
Ps0 B #cInfo
Ps0 #process
Ps0 @TextInP .resExport .resExport #zField
Ps0 @TextInP .type .type #zField
Ps0 @TextInP .processKind .processKind #zField
Ps0 @AnnotationInP-0n ai ai #zField
Ps0 @TextInP .xml .xml #zField
Ps0 @TextInP .responsibility .responsibility #zField
Ps0 @StartRequest f5 '' #zField
Ps0 @RichDialog f6 '' #zField
Ps0 @EndTask f7 '' #zField
Ps0 @PushWFArc f8 '' #zField
Ps0 @PushWFArc f9 '' #zField
>Proto Ps0 Ps0 ProcessStarts #zField
Ps0 f5 outLink ProcessStartsStart.ivp #txt
Ps0 f5 type portalKit_test.Data #txt
Ps0 f5 inParamDecl '<> param;' #txt
Ps0 f5 actionDecl 'portalKit_test.Data out;
' #txt
Ps0 f5 guid 14C26735985A626E #txt
Ps0 f5 requestEnabled true #txt
Ps0 f5 triggerEnabled false #txt
Ps0 f5 callSignature ProcessStartsStart() #txt
Ps0 f5 persist false #txt
Ps0 f5 startName 'Internal Process Start Show Room' #txt
Ps0 f5 taskData '#
#Fri Apr 24 16:45:30 ICT 2015
TaskTriggered.ROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.EXPRI=2
TaskTriggered.TYPE=0
TaskTriggered.PRI=2
TaskTriggered.EXROL=Everybody
' #txt
Ps0 f5 caseData '#
#Fri Apr 24 16:45:30 ICT 2015
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
Ps0 f5 showInStartList 1 #txt
Ps0 f5 taskAndCaseSetupAction 'import ch.ivyteam.ivy.workflow.TaskUpdateDefinition;
ch.ivyteam.ivy.workflow.TaskUpdateDefinition taskUpdDef = new ch.ivyteam.ivy.workflow.TaskUpdateDefinition();
import ch.ivyteam.ivy.request.impl.DefaultCalendarProxy;
DefaultCalendarProxy calendarProxy = ivy.cal as DefaultCalendarProxy;
taskUpdDef.setPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
taskUpdDef.setExpiryActivator("Everybody");
taskUpdDef.setExpiryPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
engine.updateCurrentTask(taskUpdDef);
' #txt
Ps0 f5 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>ProcessStartsShowRoom.ivp</name>
        <nameStyle>25,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ps0 f5 @C|.responsibility Everybody #txt
Ps0 f5 75 27 26 26 20 -7 #rect
Ps0 f5 @|StartRequestIcon #fIcon
Ps0 f6 targetWindow NEW:card: #txt
Ps0 f6 targetDisplay TOP #txt
Ps0 f6 richDialogId ch.ivy.addon.portalkit.showroom.singleapp.general.ProcessStartsHome #txt
Ps0 f6 startMethod start() #txt
Ps0 f6 type portalKit_test.Data #txt
Ps0 f6 requestActionDecl '<> param;' #txt
Ps0 f6 responseActionDecl 'portalKit_test.Data out;
' #txt
Ps0 f6 responseMappingAction 'out=in;
' #txt
Ps0 f6 windowConfiguration '* ' #txt
Ps0 f6 isAsynch false #txt
Ps0 f6 isInnerRd false #txt
Ps0 f6 userContext '* ' #txt
Ps0 f6 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Call process starts</name>
        <nameStyle>19,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ps0 f6 70 108 36 24 20 -10 #rect
Ps0 f6 @|RichDialogIcon #fIcon
Ps0 f7 type portalKit_test.Data #txt
Ps0 f7 75 187 26 26 14 0 #rect
Ps0 f7 @|EndIcon #fIcon
Ps0 f8 expr out #txt
Ps0 f8 88 53 88 108 #arcP
Ps0 f9 expr out #txt
Ps0 f9 88 132 88 187 #arcP
>Proto Ps0 .type portalKit_test.Data #txt
>Proto Ps0 .processKind NORMAL #txt
>Proto Ps0 0 0 32 24 18 0 #rect
>Proto Ps0 @|BIcon #fIcon
Ps0 f5 mainOut f8 tail #connect
Ps0 f8 head f6 mainIn #connect
Ps0 f6 mainOut f9 tail #connect
Ps0 f9 head f7 mainIn #connect
