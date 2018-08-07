[Ivy]
[>Created: Tue May 05 17:16:39 ICT 2015]
14763D58C921B571 3.17 #module
>Proto >Proto Collection #zClass
st0 startProject Big #zClass
st0 B #cInfo
st0 #process
Ct0 Component Big #zClass
Ct0 B #cInfo
Ct1 Component Big #zClass
Ct1 B #cInfo
st0 @TextInP .resExport .resExport #zField
st0 @TextInP .type .type #zField
st0 @TextInP .processKind .processKind #zField
st0 @AnnotationInP-0n ai ai #zField
st0 @MessageFlowInP-0n messageIn messageIn #zField
st0 @MessageFlowOutP-0n messageOut messageOut #zField
st0 @TextInP .xml .xml #zField
st0 @TextInP .responsibility .responsibility #zField
st0 @StartRequest f0 '' #zField
st0 @EndTask f3 '' #zField
st0 Ct0 1b0 '1 Sub' #zField
st0 Ct1 2b0 '2 Sub' #zField
st0 @PushWFArc f2 '' #zField
st0 @PushWFArc f4 '' #zField
st0 @GridStep f5 '' #zField
st0 @PushWFArc f6 '' #zField
st0 @PushWFArc f1 '' #zField
>Proto st0 st0 startProject #zField
Ct0 @TextInP .resExport .resExport #zField
Ct0 @TextInP .type .type #zField
Ct0 @TextInP .processKind .processKind #zField
Ct0 @AnnotationInP-0n ai ai #zField
Ct0 @TextInP .xml .xml #zField
Ct0 @TextInP .responsibility .responsibility #zField
Ct0 @PushTrueWFInG-01 g0 '' #zField
Ct0 @PushTrueWFInG-01 g1 '' #zField
Ct0 @PushTrueWFOutG-01 g2 '' #zField
Ct0 @RichDialog f3 '' #zField
Ct0 @TaskSwitchSimple f1 '' #zField
Ct0 @PushWFArc f4 '' #zField
Ct0 @TkArc f0 '' #zField
Ct0 @PushWFArc f2 '' #zField
>Proto Ct0 Ct0 Component #zField
Ct1 @TextInP .resExport .resExport #zField
Ct1 @TextInP .type .type #zField
Ct1 @TextInP .processKind .processKind #zField
Ct1 @AnnotationInP-0n ai ai #zField
Ct1 @TextInP .xml .xml #zField
Ct1 @TextInP .responsibility .responsibility #zField
Ct1 @PushTrueWFInG-01 g0 '' #zField
Ct1 @PushTrueWFOutG-01 g1 '' #zField
Ct1 @TaskSwitchSimple f1 '' #zField
Ct1 @TkArc f0 '' #zField
Ct1 @PushWFArc f2 '' #zField
>Proto Ct1 Ct1 Component #zField
st0 f0 outLink startProject.ivp #txt
st0 f0 type test005ProjectManagement.startProject #txt
st0 f0 inParamDecl '<> param;' #txt
st0 f0 actionDecl 'test005ProjectManagement.startProject out;
' #txt
st0 f0 guid 1476C9779ED9F70D #txt
st0 f0 requestEnabled true #txt
st0 f0 triggerEnabled false #txt
st0 f0 callSignature startProject() #txt
st0 f0 persist false #txt
st0 f0 startName 'Start Project' #txt
st0 f0 startDescription 'Projektleiter erhält Auftrag und startet Projekt' #txt
st0 f0 taskData '#
#Mon Jan 19 17:46:41 ICT 2015
TaskTriggered.ROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.EXPRI=2
TaskTriggered.TYPE=0
TaskTriggered.PRI=2
TaskTriggered.EXROL=Projectmanager
' #txt
st0 f0 caseData '#
#Mon Jan 19 17:46:41 ICT 2015
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
st0 f0 showInStartList 1 #txt
st0 f0 taskAndCaseSetupAction 'import ch.ivyteam.ivy.workflow.TaskUpdateDefinition;
ch.ivyteam.ivy.workflow.TaskUpdateDefinition taskUpdDef = new ch.ivyteam.ivy.workflow.TaskUpdateDefinition();
import ch.ivyteam.ivy.request.impl.DefaultCalendarProxy;
DefaultCalendarProxy calendarProxy = ivy.cal as DefaultCalendarProxy;
taskUpdDef.setPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
taskUpdDef.setExpiryActivator("Projectmanager");
taskUpdDef.setExpiryPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
engine.updateCurrentTask(taskUpdDef);
' #txt
st0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>startProject</name>
        <nameStyle>12,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
st0 f0 @C|.responsibility Projectmanager #txt
st0 f0 113 89 30 30 16 0 #rect
st0 f0 @|StartRequestIcon #fIcon
st0 f0 -1|-1|-9671572 #nodeStyle
st0 f3 type test005ProjectManagement.startProject #txt
st0 f3 113 465 30 30 16 0 #rect
st0 f3 @|EndIcon #fIcon
st0 f3 -1|-1|-9671572 #nodeStyle
st0 1b0 .resExport export #txt
st0 1b0 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language lang="en">
        <name>PrepareDocuments</name>
        <nameStyle>16,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
st0 1b0 49 184 159 32 -50 -8 #rect
st0 1b0 @|BIcon #fIcon
st0 1b0 g0 0 -16 #fFoot
st0 1b0 g1 0 -16 #fFoot
st0 1b0 g2 0 16 #fFoot
st0 2b0 .resExport export #txt
st0 2b0 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language lang="en">
        <name>DoKickOff</name>
        <nameStyle>9,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
st0 2b0 79 344 99 32 -30 -8 #rect
st0 2b0 @|BIcon #fIcon
st0 2b0 g0 0 -16 #fFoot
st0 2b0 g1 0 16 #fFoot
st0 f2 128 216 128 344 #arcP
st0 f4 128 376 128 465 #arcP
st0 f5 actionDecl 'test005ProjectManagement.startProject out;
' #txt
st0 f5 actionTable 'out=in;
' #txt
st0 f5 actionCode 'import ch.ivy.addon.portalkit.util.CaseUtils;
CaseUtils.setCaseDetailsProcess(ivy.case,ivy.html.startref("Start Processes/InternalPortalHome/caseDetails.ivp"));' #txt
st0 f5 type test005ProjectManagement.startProject #txt
st0 f5 110 132 36 24 20 -2 #rect
st0 f5 @|StepIcon #fIcon
st0 f6 expr out #txt
st0 f6 128 119 128 132 #arcP
st0 f1 expr out #txt
st0 f1 128 156 128 184 #arcP
>Proto st0 .type test005ProjectManagement.startProject #txt
>Proto st0 .processKind NORMAL #txt
>Proto st0 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <swimlaneLabel>Project Manager</swimlaneLabel>
        <swimlaneLabel></swimlaneLabel>
    </language>
    <swimlaneOrientation>true</swimlaneOrientation>
    <swimlaneSize>248</swimlaneSize>
    <swimlaneSize>192</swimlaneSize>
    <swimlaneColor>-39322</swimlaneColor>
    <swimlaneColor>-1</swimlaneColor>
</elementInfo>
' #txt
>Proto st0 0 0 32 24 18 0 #rect
>Proto st0 @|BIcon #fIcon
Ct0 g0 301 -6 6 6 10 16 #rect
Ct0 g0 @|MIGIcon #fIcon
Ct0 g1 301 -6 6 6 7 -3 #rect
Ct0 g1 @|MIGIcon #fIcon
Ct0 g2 301 501 6 6 10 16 #rect
Ct0 g2 @|MOGIcon #fIcon
Ct0 f3 targetWindow NEW:card: #txt
Ct0 f3 targetDisplay TOP #txt
Ct0 f3 richDialogId test.ui.createDocument #txt
Ct0 f3 startMethod start() #txt
Ct0 f3 type test005ProjectManagement.startProject #txt
Ct0 f3 requestActionDecl '<> param;' #txt
Ct0 f3 responseActionDecl 'test005ProjectManagement.startProject out;
' #txt
Ct0 f3 responseMappingAction 'out=in;
out.ProjectEnd=result.ProjectEnd;
out.ProjectID=result.ProjectID;
out.ProjectManager=result.ProjectManager;
out.ProjectName=result.ProjectName;
out.ProjectStart=result.ProjectStart;
' #txt
Ct0 f3 windowConfiguration '* ' #txt
Ct0 f3 isAsynch false #txt
Ct0 f3 isInnerRd false #txt
Ct0 f3 userContext '* ' #txt
Ct0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>CreateDocuments</name>
        <nameStyle>15,7
</nameStyle>
        <desc>Anlage in den Tools </desc>
    </language>
</elementInfo>
' #txt
Ct0 f3 248 290 112 44 -50 -8 #rect
Ct0 f3 @|RichDialogIcon #fIcon
Ct0 f3 -1|-1|-9671572 #nodeStyle
Ct0 f1 actionDecl 'test005ProjectManagement.startProject out;
' #txt
Ct0 f1 actionTable 'out=in1;
' #txt
Ct0 f1 outTypes "test005ProjectManagement.startProject" #txt
Ct0 f1 outLinks "TaskA.ivp" #txt
Ct0 f1 caseData '#
#Mon Jan 19 10:46:38 ICT 2015
businessCalendarName=
businessCreator.user=
businessMilestone.timestamp=
businessObject.code=
businessObject.docDb.code=
businessObject.folder.id=
businessObject.name=
businessPriority=
businessStart.timestamp=
case.description=PHP
case.name=Start Project
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
Ct0 f1 taskData '#
#Mon Jan 19 10:46:38 ICT 2015
TaskA.DESC=Projektmanager bef\u00FCllt Vorlage f\u00FCr Projekthandbuch und legt das Projekt in den Tools an. 
TaskA.EXPRI=2
TaskA.EXROL=Everybody
TaskA.EXTYPE=0
TaskA.KINDC=P001
TaskA.KINDN=Projekthandbuch nach PMA
TaskA.NAM=Prepare Documentation
TaskA.PRI=2
TaskA.ROL=Projectmanager
TaskA.SKIP_TASK_LIST=false
TaskA.TYPE=0
' #txt
Ct0 f1 taskAction 'ivy.case.setName(engine.expandMacros("Start Project"));
ivy.case.setDescription(engine.expandMacros("PHP"));
import ch.ivyteam.ivy.workflow.TaskDefinition;
List<TaskDefinition> taskDefinitions;
TaskDefinition taskDef;import ch.ivyteam.ivy.request.impl.DefaultCalendarProxy;
DefaultCalendarProxy calendarProxy = ivy.cal as DefaultCalendarProxy;
taskDef = new TaskDefinition();
taskDef.setStartRequestPath("TaskA.ivp");
taskDef.setName(engine.expandMacros("Prepare Documentation"));
taskDef.setDescription(engine.expandMacros("Projektmanager befüllt Vorlage für Projekthandbuch und legt das Projekt in den Tools an."));
taskDef.setKindCode(engine.expandMacros("P001"));
taskDef.setKindName(engine.expandMacros("Projekthandbuch nach PMA"));
taskDef.setAutoStartTask(false);
taskDef.setActivator("Projectmanager");
taskDef.setPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
taskDef.setExpiryActivator("Everybody");
taskDef.setExpiryPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
taskDefinitions.add(taskDef);
' #txt
Ct0 f1 type test005ProjectManagement.startProject #txt
Ct0 f1 template "" #txt
Ct0 f1 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>PrepareDocuments</name>
        <nameStyle>16,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ct0 f1 289 129 30 30 20 -3 #rect
Ct0 f1 @|TaskSwitchSimpleIcon #fIcon
Ct0 f1 -1|-1|-9671572 #nodeStyle
Ct0 f4 expr data #txt
Ct0 f4 outCond ivp=="TaskA.ivp" #txt
Ct0 f4 304 159 304 290 #arcP
Ct0 f0 type test005ProjectManagement.startProject #txt
Ct0 f0 var in1 #txt
Ct0 f0 304 0 304 129 #arcP
Ct0 f2 expr out #txt
Ct0 f2 304 334 304 501 #arcP
>Proto Ct0 0 0 32 24 18 0 #rect
>Proto Ct0 @|BIcon #fIcon
Ct1 g0 285 -6 6 6 11 -5 #rect
Ct1 g0 @|MIGIcon #fIcon
Ct1 g1 285 501 6 6 10 16 #rect
Ct1 g1 @|MOGIcon #fIcon
Ct1 f1 actionDecl 'test005ProjectManagement.startProject out;
' #txt
Ct1 f1 actionTable 'out=in1;
' #txt
Ct1 f1 outTypes "test005ProjectManagement.startProject" #txt
Ct1 f1 outLinks "TaskA.ivp" #txt
Ct1 f1 caseData '#
#Mon Jan 19 10:45:53 ICT 2015
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
Ct1 f1 taskData '#
#Mon Jan 19 10:45:53 ICT 2015
TaskA.DEL=new Duration(1)
TaskA.DESC=Projektmanager l\u00E4dt Team zum Kick-Off Meeting ein, stellt Projektinhalte und Strukturen vor und \nverteilt Arbeitspakete. 
TaskA.EXP=new Duration(8)
TaskA.EXPRI=2
TaskA.EXROL=Projectmanager
TaskA.EXTYPE=0
TaskA.KINDC=P002
TaskA.KINDN=Kick off
TaskA.NAM=Kick Off Meeting
TaskA.PRI=2
TaskA.ROL=Projectmanager
TaskA.SKIP_TASK_LIST=false
TaskA.TYPE=0
TaskA.customFields.decimal.1=in1.ProjectID
TaskA.customFields.timestamp.1=new DateTime(in1.ProjectStart)
TaskA.customFields.timestamp.2=new DateTime(in1.ProjectEnd)
TaskA.customFields.varchar.1=in1.ProjectManager
TaskA.customFields.varchar.2=in1.ProjectName
' #txt
Ct1 f1 taskAction 'import ch.ivyteam.ivy.workflow.TaskDefinition;
List<TaskDefinition> taskDefinitions;
TaskDefinition taskDef;import ch.ivyteam.ivy.request.impl.DefaultCalendarProxy;
DefaultCalendarProxy calendarProxy = ivy.cal as DefaultCalendarProxy;
taskDef = new TaskDefinition();
taskDef.setStartRequestPath("TaskA.ivp");
taskDef.setName(engine.expandMacros("Kick Off Meeting"));
taskDef.setDescription(engine.expandMacros("Projektmanager lädt Team zum Kick-Off Meeting ein, stellt Projektinhalte und Strukturen vor und 
verteilt Arbeitspakete."));
taskDef.setKindCode(engine.expandMacros("P002"));
taskDef.setKindName(engine.expandMacros("Kick off"));
taskDef.setAutoStartTask(false);
taskDef.setActivator("Projectmanager");
taskDef.setDelayPeriod(1000 * (new Duration(1)).toNumber());
taskDef.setPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
taskDef.setExpiryPeriod(1000 * (new Duration(8)).toNumber());
taskDef.setExpiryActivator("Projectmanager");
taskDef.setExpiryPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
taskDef.setCustomVarCharField1(in1.ProjectManager);
taskDef.setCustomDecimalField1(in1.ProjectID);
taskDef.setCustomTimestampField1(new DateTime(in1.ProjectStart));
taskDef.setCustomVarCharField2(in1.ProjectName);
taskDef.setCustomTimestampField2(new DateTime(in1.ProjectEnd));
taskDefinitions.add(taskDef);
' #txt
Ct1 f1 type test005ProjectManagement.startProject #txt
Ct1 f1 template "" #txt
Ct1 f1 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>PerformKickOffMeeting</name>
        <nameStyle>21,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ct1 f1 273 169 30 30 21 -5 #rect
Ct1 f1 @|TaskSwitchSimpleIcon #fIcon
Ct1 f1 -1|-1|-9671572 #nodeStyle
Ct1 f0 type test005ProjectManagement.startProject #txt
Ct1 f0 var in1 #txt
Ct1 f0 288 0 288 169 #arcP
Ct1 f2 expr data #txt
Ct1 f2 outCond ivp=="TaskA.ivp" #txt
Ct1 f2 288 199 288 501 #arcP
>Proto Ct1 0 0 32 24 18 0 #rect
>Proto Ct1 @|BIcon #fIcon
st0 1b0 g2 f2 tail #connect
st0 f2 head 2b0 g0 #connect
st0 2b0 g1 f4 tail #connect
st0 f4 head f3 mainIn #connect
st0 f0 mainOut f6 tail #connect
st0 f6 head f5 mainIn #connect
st0 f5 mainOut f1 tail #connect
st0 f1 head 1b0 g1 #connect
Ct0 f1 out f4 tail #connect
Ct0 f4 head f3 mainIn #connect
Ct0 g1 m f0 tail #connect
Ct0 f0 head f1 in #connect
Ct0 f3 mainOut f2 tail #connect
Ct0 f2 head g2 m #connect
Ct0 0 0 600 500 0 #ivRect
Ct1 g0 m f0 tail #connect
Ct1 f0 head f1 in #connect
Ct1 f1 out f2 tail #connect
Ct1 f2 head g1 m #connect
Ct1 0 0 600 500 0 #ivRect
