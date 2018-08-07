[Ivy]
[>Created: Fri Apr 24 16:43:40 ICT 2015]
14BCA9EAC2C1C0CF 3.17 #module
>Proto >Proto Collection #zClass
Tt0 MultiappStarts Big #zClass
Tt0 B #cInfo
Tt0 #process
Tt0 @TextInP .resExport .resExport #zField
Tt0 @TextInP .type .type #zField
Tt0 @TextInP .processKind .processKind #zField
Tt0 @AnnotationInP-0n ai ai #zField
Tt0 @TextInP .xml .xml #zField
Tt0 @TextInP .responsibility .responsibility #zField
Tt0 @StartRequest f0 '' #zField
Tt0 @RichDialog f1 '' #zField
Tt0 @EndTask f2 '' #zField
Tt0 @PushWFArc f3 '' #zField
Tt0 @PushWFArc f4 '' #zField
Tt0 @StartRequest f5 '' #zField
Tt0 @EndTask f6 '' #zField
Tt0 @RichDialog f7 '' #zField
Tt0 @PushWFArc f8 '' #zField
Tt0 @PushWFArc f9 '' #zField
Tt0 @StartRequest f10 '' #zField
Tt0 @EndTask f11 '' #zField
Tt0 @RichDialog f12 '' #zField
Tt0 @PushWFArc f13 '' #zField
Tt0 @PushWFArc f14 '' #zField
>Proto Tt0 Tt0 MultiappStarts #zField
Tt0 f0 outLink UniversalTaskList.ivp #txt
Tt0 f0 type ch.ivy.add.portalkit.Data #txt
Tt0 f0 inParamDecl '<> param;' #txt
Tt0 f0 actionDecl 'ch.ivy.add.portalkit.Data out;
' #txt
Tt0 f0 guid 1473F00EA91A4BCA #txt
Tt0 f0 requestEnabled true #txt
Tt0 f0 triggerEnabled false #txt
Tt0 f0 callSignature UniversalTaskList() #txt
Tt0 f0 persist false #txt
Tt0 f0 startName 'Internal Universal Task List Show Room' #txt
Tt0 f0 taskData '#
#Fri Apr 24 16:43:25 ICT 2015
TaskTriggered.ROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.EXPRI=2
TaskTriggered.TYPE=0
TaskTriggered.PRI=2
TaskTriggered.EXROL=Everybody
' #txt
Tt0 f0 caseData '#
#Fri Apr 24 16:43:25 ICT 2015
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
Tt0 f0 showInStartList 1 #txt
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
        <name>Univeral Task List Show Room</name>
        <nameStyle>28,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Tt0 f0 @C|.responsibility Everybody #txt
Tt0 f0 51 51 26 26 16 -9 #rect
Tt0 f0 @|StartRequestIcon #fIcon
Tt0 f1 targetWindow NEW:card: #txt
Tt0 f1 targetDisplay TOP #txt
Tt0 f1 richDialogId ch.ivy.addon.portalkit.showroom.multiapp.tasks.UniveralTaskListHome #txt
Tt0 f1 startMethod start() #txt
Tt0 f1 type ch.ivy.add.portalkit.Data #txt
Tt0 f1 requestActionDecl '<> param;' #txt
Tt0 f1 responseActionDecl 'ch.ivy.add.portalkit.Data out;
' #txt
Tt0 f1 responseMappingAction 'out=in;
' #txt
Tt0 f1 windowConfiguration '* ' #txt
Tt0 f1 isAsynch false #txt
Tt0 f1 isInnerRd false #txt
Tt0 f1 userContext '* ' #txt
Tt0 f1 46 116 36 24 20 -2 #rect
Tt0 f1 @|RichDialogIcon #fIcon
Tt0 f2 type ch.ivy.add.portalkit.Data #txt
Tt0 f2 51 179 26 26 14 0 #rect
Tt0 f2 @|EndIcon #fIcon
Tt0 f3 expr out #txt
Tt0 f3 64 77 64 116 #arcP
Tt0 f4 expr out #txt
Tt0 f4 64 140 64 179 #arcP
Tt0 f5 outLink UniversalCaseList.ivp #txt
Tt0 f5 type ch.ivy.add.portalkit.Data #txt
Tt0 f5 inParamDecl '<> param;' #txt
Tt0 f5 actionDecl 'ch.ivy.add.portalkit.Data out;
' #txt
Tt0 f5 guid 14758F92887111CB #txt
Tt0 f5 requestEnabled true #txt
Tt0 f5 triggerEnabled false #txt
Tt0 f5 callSignature UniversalCaseList() #txt
Tt0 f5 persist false #txt
Tt0 f5 startName 'Internal UniversalCaseList' #txt
Tt0 f5 taskData '#
#Fri Apr 24 16:43:32 ICT 2015
TaskTriggered.ROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.EXPRI=2
TaskTriggered.TYPE=0
TaskTriggered.PRI=2
TaskTriggered.EXROL=Everybody
' #txt
Tt0 f5 caseData '#
#Fri Apr 24 16:43:32 ICT 2015
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
Tt0 f5 showInStartList 1 #txt
Tt0 f5 taskAndCaseSetupAction 'import ch.ivyteam.ivy.workflow.TaskUpdateDefinition;
ch.ivyteam.ivy.workflow.TaskUpdateDefinition taskUpdDef = new ch.ivyteam.ivy.workflow.TaskUpdateDefinition();
import ch.ivyteam.ivy.request.impl.DefaultCalendarProxy;
DefaultCalendarProxy calendarProxy = ivy.cal as DefaultCalendarProxy;
taskUpdDef.setPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
taskUpdDef.setExpiryActivator("Everybody");
taskUpdDef.setExpiryPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
engine.updateCurrentTask(taskUpdDef);
' #txt
Tt0 f5 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Universal Case List Show Room</name>
        <nameStyle>29,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Tt0 f5 @C|.responsibility Everybody #txt
Tt0 f5 355 51 26 26 17 -5 #rect
Tt0 f5 @|StartRequestIcon #fIcon
Tt0 f6 type ch.ivy.add.portalkit.Data #txt
Tt0 f6 355 179 26 26 14 0 #rect
Tt0 f6 @|EndIcon #fIcon
Tt0 f7 targetWindow NEW:card: #txt
Tt0 f7 targetDisplay TOP #txt
Tt0 f7 richDialogId ch.ivy.addon.portalkit.showroom.multiapp.cases.UniversalCaseListHome #txt
Tt0 f7 startMethod start() #txt
Tt0 f7 type ch.ivy.add.portalkit.Data #txt
Tt0 f7 requestActionDecl '<> param;' #txt
Tt0 f7 responseActionDecl 'ch.ivy.add.portalkit.Data out;
' #txt
Tt0 f7 responseMappingAction 'out=in;
' #txt
Tt0 f7 windowConfiguration '* ' #txt
Tt0 f7 isAsynch false #txt
Tt0 f7 isInnerRd false #txt
Tt0 f7 userContext '* ' #txt
Tt0 f7 350 116 36 24 20 -2 #rect
Tt0 f7 @|RichDialogIcon #fIcon
Tt0 f8 expr out #txt
Tt0 f8 368 77 368 116 #arcP
Tt0 f9 expr out #txt
Tt0 f9 368 140 368 179 #arcP
Tt0 f10 outLink UniversalProcessStartList.ivp #txt
Tt0 f10 type ch.ivy.add.portalkit.Data #txt
Tt0 f10 inParamDecl '<> param;' #txt
Tt0 f10 actionDecl 'ch.ivy.add.portalkit.Data out;
' #txt
Tt0 f10 guid 14790CF05E238398 #txt
Tt0 f10 requestEnabled true #txt
Tt0 f10 triggerEnabled false #txt
Tt0 f10 callSignature UniversalProcessStartList() #txt
Tt0 f10 persist false #txt
Tt0 f10 startName 'Internal UniversalProcessStartList' #txt
Tt0 f10 taskData '#
#Fri Apr 24 16:43:39 ICT 2015
TaskTriggered.ROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.EXPRI=2
TaskTriggered.TYPE=0
TaskTriggered.PRI=2
TaskTriggered.NAM=Universal ProcessStart List
TaskTriggered.EXROL=Everybody
' #txt
Tt0 f10 caseData '#
#Fri Apr 24 16:43:39 ICT 2015
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
Tt0 f10 showInStartList 1 #txt
Tt0 f10 taskAndCaseSetupAction 'import ch.ivyteam.ivy.workflow.TaskUpdateDefinition;
ch.ivyteam.ivy.workflow.TaskUpdateDefinition taskUpdDef = new ch.ivyteam.ivy.workflow.TaskUpdateDefinition();
import ch.ivyteam.ivy.request.impl.DefaultCalendarProxy;
DefaultCalendarProxy calendarProxy = ivy.cal as DefaultCalendarProxy;
taskUpdDef.setName(engine.expandMacros("Universal ProcessStart List"));
taskUpdDef.setPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
taskUpdDef.setExpiryActivator("Everybody");
taskUpdDef.setExpiryPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
engine.updateCurrentTask(taskUpdDef);
' #txt
Tt0 f10 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Universal ProcessStart List Show Room</name>
        <nameStyle>37,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Tt0 f10 @C|.responsibility Everybody #txt
Tt0 f10 51 243 26 26 19 -8 #rect
Tt0 f10 @|StartRequestIcon #fIcon
Tt0 f11 type ch.ivy.add.portalkit.Data #txt
Tt0 f11 51 371 26 26 14 0 #rect
Tt0 f11 @|EndIcon #fIcon
Tt0 f12 targetWindow NEW:card: #txt
Tt0 f12 targetDisplay TOP #txt
Tt0 f12 richDialogId ch.ivy.addon.portalkit.showroom.multiapp.general.UniversalProcessStartListHome #txt
Tt0 f12 startMethod start() #txt
Tt0 f12 type ch.ivy.add.portalkit.Data #txt
Tt0 f12 requestActionDecl '<> param;' #txt
Tt0 f12 responseActionDecl 'ch.ivy.add.portalkit.Data out;
' #txt
Tt0 f12 responseMappingAction 'out=in;
' #txt
Tt0 f12 windowConfiguration '* ' #txt
Tt0 f12 isAsynch false #txt
Tt0 f12 isInnerRd false #txt
Tt0 f12 userContext '* ' #txt
Tt0 f12 46 308 36 24 20 -2 #rect
Tt0 f12 @|RichDialogIcon #fIcon
Tt0 f13 expr out #txt
Tt0 f13 64 269 64 308 #arcP
Tt0 f14 expr out #txt
Tt0 f14 64 332 64 371 #arcP
>Proto Tt0 .type ch.ivy.add.portalkit.Data #txt
>Proto Tt0 .processKind NORMAL #txt
>Proto Tt0 0 0 32 24 18 0 #rect
>Proto Tt0 @|BIcon #fIcon
Tt0 f0 mainOut f3 tail #connect
Tt0 f3 head f1 mainIn #connect
Tt0 f1 mainOut f4 tail #connect
Tt0 f4 head f2 mainIn #connect
Tt0 f5 mainOut f8 tail #connect
Tt0 f8 head f7 mainIn #connect
Tt0 f7 mainOut f9 tail #connect
Tt0 f9 head f6 mainIn #connect
Tt0 f10 mainOut f13 tail #connect
Tt0 f13 head f12 mainIn #connect
Tt0 f12 mainOut f14 tail #connect
Tt0 f14 head f11 mainIn #connect
