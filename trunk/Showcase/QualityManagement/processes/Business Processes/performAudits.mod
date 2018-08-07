[Ivy]
[>Created: Tue May 05 16:38:32 ICT 2015]
14763D487EBEE773 3.17 #module
>Proto >Proto Collection #zClass
ps0 performAudits Big #zClass
ps0 B #cInfo
ps0 #process
Ct0 Component Big #zClass
Ct0 B #cInfo
Ct1 Component Big #zClass
Ct1 B #cInfo
Ct2 Component Big #zClass
Ct2 B #cInfo
ps0 @TextInP .resExport .resExport #zField
ps0 @TextInP .type .type #zField
ps0 @TextInP .processKind .processKind #zField
ps0 @AnnotationInP-0n ai ai #zField
ps0 @MessageFlowInP-0n messageIn messageIn #zField
ps0 @MessageFlowOutP-0n messageOut messageOut #zField
ps0 @TextInP .xml .xml #zField
ps0 @TextInP .responsibility .responsibility #zField
ps0 @EndTask f4 '' #zField
ps0 Ct0 1b0 '1 Sub' #zField
ps0 Ct1 2b0 '2 Sub' #zField
ps0 Ct2 3b0 '3 Sub' #zField
ps0 @PushWFArc f1 '' #zField
ps0 @PushWFArc f2 '' #zField
ps0 @PushWFArc f3 '' #zField
ps0 @StartRequest f5 '' #zField
ps0 @GridStep f6 '' #zField
ps0 @PushWFArc f7 '' #zField
ps0 @PushWFArc f0 '' #zField
>Proto ps0 ps0 performAudits #zField
Ct0 @TextInP .resExport .resExport #zField
Ct0 @TextInP .type .type #zField
Ct0 @TextInP .processKind .processKind #zField
Ct0 @AnnotationInP-0n ai ai #zField
Ct0 @TextInP .xml .xml #zField
Ct0 @TextInP .responsibility .responsibility #zField
Ct0 @PushTrueWFInG-01 g0 '' #zField
Ct0 @PushTrueWFOutG-01 g1 '' #zField
Ct0 @TaskSwitchSimple f0 '' #zField
Ct0 @RichDialog f1 '' #zField
Ct0 @PushWFArc f3 '' #zField
Ct0 @TkArc f2 '' #zField
Ct0 @PushWFArc f4 '' #zField
>Proto Ct0 Ct0 Component #zField
Ct1 @TextInP .resExport .resExport #zField
Ct1 @TextInP .type .type #zField
Ct1 @TextInP .processKind .processKind #zField
Ct1 @AnnotationInP-0n ai ai #zField
Ct1 @TextInP .xml .xml #zField
Ct1 @TextInP .responsibility .responsibility #zField
Ct1 @PushTrueWFInG-01 g0 '' #zField
Ct1 @PushTrueWFOutG-01 g1 '' #zField
Ct1 @RichDialog f3 '' #zField
Ct1 @TaskSwitchSimple f1 '' #zField
Ct1 @PushWFArc f4 '' #zField
Ct1 @TkArc f0 '' #zField
Ct1 @PushWFArc f2 '' #zField
Ct1 @PushTrueWFInG-01 g2 '' #zField
>Proto Ct1 Ct1 Component #zField
Ct2 @TextInP .resExport .resExport #zField
Ct2 @TextInP .type .type #zField
Ct2 @TextInP .processKind .processKind #zField
Ct2 @AnnotationInP-0n ai ai #zField
Ct2 @TextInP .xml .xml #zField
Ct2 @TextInP .responsibility .responsibility #zField
Ct2 @PushTrueWFInG-01 g0 '' #zField
Ct2 @PushTrueWFOutG-01 g1 '' #zField
Ct2 @TaskSwitchSimple f1 '' #zField
Ct2 @TkArc f0 '' #zField
Ct2 @PushWFArc f2 '' #zField
>Proto Ct2 Ct2 Component #zField
ps0 f4 type test004QualityManagement.PerformAudits #txt
ps0 f4 321 545 30 30 16 0 #rect
ps0 f4 @|EndIcon #fIcon
ps0 f4 -1|-1|-9671572 #nodeStyle
ps0 1b0 .resExport export #txt
ps0 1b0 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language lang="en">
        <name>PerformQualityAudit</name>
        <nameStyle>19,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
ps0 1b0 31 328 179 32 -57 -8 #rect
ps0 1b0 @|BIcon #fIcon
ps0 1b0 g0 0 -16 #fFoot
ps0 1b0 g1 89 8 #fFoot
ps0 2b0 .resExport export #txt
ps0 2b0 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language lang="en">
        <name>Prepare Quality Audit</name>
        <nameStyle>21,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
ps0 2b0 26 168 189 32 -60 -8 #rect
ps0 2b0 @|BIcon #fIcon
ps0 2b0 g0 1 -16 #fFoot
ps0 2b0 g1 0 16 #fFoot
ps0 2b0 g2 94 -8 #fFoot
ps0 3b0 .resExport export #txt
ps0 3b0 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language lang="en">
        <name>SetQualityActions</name>
        <nameStyle>17,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
ps0 3b0 256 464 160 32 -51 -8 #rect
ps0 3b0 @|BIcon #fIcon
ps0 3b0 g0 0 -16 #fFoot
ps0 3b0 g1 0 16 #fFoot
ps0 f1 120 200 120 328 #arcP
ps0 f2 209 352 336 464 #arcP
ps0 f2 1 336 352 #addKink
ps0 f2 1 0.05932494476049045 0 0 #arcLabel
ps0 f3 336 496 336 545 #arcP
ps0 f5 outLink Audit.ivp #txt
ps0 f5 type test004QualityManagement.PerformAudits #txt
ps0 f5 inParamDecl '<> param;' #txt
ps0 f5 actionDecl 'test004QualityManagement.PerformAudits out;
' #txt
ps0 f5 guid 14B00FFC0EE43D97 #txt
ps0 f5 requestEnabled true #txt
ps0 f5 triggerEnabled false #txt
ps0 f5 callSignature Audit() #txt
ps0 f5 persist false #txt
ps0 f5 startName 'Perform Quality Audit' #txt
ps0 f5 startDescription 'Preparation, performing and reporting internal quality audit' #txt
ps0 f5 taskData '#
#Fri Apr 24 15:33:43 ICT 2015
TaskTriggered.ROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.EXPRI=2
TaskTriggered.TYPE=0
TaskTriggered.PRI=2
TaskTriggered.EXROL=Qualitymanager
' #txt
ps0 f5 caseData '#
#Fri Apr 24 15:33:43 ICT 2015
businessCalendarName=
businessCreator.user=
businessMilestone.timestamp=
businessObject.code=
businessObject.docDb.code=
businessObject.folder.id=
businessObject.name=
businessPriority=
businessStart.timestamp=
case.description=QUALITY AUDIT test desc. 
case.name=QUALITY AUDIT
correspondent.id=
mainContact.docDb.code=
mainContact.folder.id=
mainContact.id=
mainContact.name=
mainContact.type=
process.code=
process.name=
processCategory.code=Audit
processCategory.name=
subType.code=
subType.name=
type.code=
type.name=
' #txt
ps0 f5 showInStartList 1 #txt
ps0 f5 taskAndCaseSetupAction 'ivy.case.setName(engine.expandMacros("QUALITY AUDIT"));
ivy.case.setDescription(engine.expandMacros("QUALITY AUDIT test desc."));
ivy.case.setProcessCategory(engine.expandMacros("Audit"), engine.expandMacros(""));
import ch.ivyteam.ivy.workflow.TaskUpdateDefinition;
ch.ivyteam.ivy.workflow.TaskUpdateDefinition taskUpdDef = new ch.ivyteam.ivy.workflow.TaskUpdateDefinition();
import ch.ivyteam.ivy.request.impl.DefaultCalendarProxy;
DefaultCalendarProxy calendarProxy = ivy.cal as DefaultCalendarProxy;
taskUpdDef.setPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
taskUpdDef.setExpiryActivator("Qualitymanager");
taskUpdDef.setExpiryPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
engine.updateCurrentTask(taskUpdDef);
' #txt
ps0 f5 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Audit</name>
        <nameStyle>5,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
ps0 f5 @C|.responsibility Qualitymanager #txt
ps0 f5 107 67 26 26 14 0 #rect
ps0 f5 @|StartRequestIcon #fIcon
ps0 f6 actionDecl 'test004QualityManagement.PerformAudits out;
' #txt
ps0 f6 actionTable 'out=in;
' #txt
ps0 f6 actionCode 'import ch.ivy.addon.portalkit.util.CaseUtils;
CaseUtils.setCaseDetailsProcess(ivy.case,ivy.html.startref("Start Processes/InternalPortalHome/caseDetails.ivp"));' #txt
ps0 f6 type test004QualityManagement.PerformAudits #txt
ps0 f6 102 116 36 24 20 -2 #rect
ps0 f6 @|StepIcon #fIcon
ps0 f7 expr out #txt
ps0 f7 120 93 120 116 #arcP
ps0 f0 expr out #txt
ps0 f0 120 140 121 168 #arcP
>Proto ps0 .type test004QualityManagement.PerformAudits #txt
>Proto ps0 .processKind NORMAL #txt
>Proto ps0 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <swimlaneLabel>Quality Manager</swimlaneLabel>
        <swimlaneLabel>Mitarbeiter</swimlaneLabel>
    </language>
    <swimlaneOrientation>true</swimlaneOrientation>
    <swimlaneSize>240</swimlaneSize>
    <swimlaneSize>192</swimlaneSize>
    <swimlaneColor>-6710785</swimlaneColor>
    <swimlaneColor>-13108</swimlaneColor>
</elementInfo>
' #txt
>Proto ps0 0 0 32 24 18 0 #rect
>Proto ps0 @|BIcon #fIcon
Ct0 g0 301 -6 6 6 10 16 #rect
Ct0 g0 @|MIGIcon #fIcon
Ct0 g1 601 381 6 6 10 16 #rect
Ct0 g1 @|MOGIcon #fIcon
Ct0 f0 actionDecl 'test004QualityManagement.PerformAudits out;
' #txt
Ct0 f0 actionTable 'out=in1;
' #txt
Ct0 f0 outTypes "test004QualityManagement.PerformAudits" #txt
Ct0 f0 outLinks "TaskA.ivp" #txt
Ct0 f0 caseData '#
#Fri Jul 25 08:48:04 CEST 2014
businessCalendarName=
businessCreator.user=
businessMilestone.timestamp=
businessObject.code=
businessObject.docDb.code=
businessObject.folder.id=
businessObject.name=
businessPriority=
businessStart.timestamp=
case.description=Appraisal/Report
case.name=Audit Stage Perform
correspondent.id=
mainContact.docDb.code=
mainContact.folder.id=
mainContact.id=
mainContact.name=
mainContact.type=
process.code=
process.name=
processCategory.code=Q002
processCategory.name=
subType.code=
subType.name=
type.code=
type.name=
' #txt
Ct0 f0 taskData '#
#Mon Jan 19 10:41:24 ICT 2015
TaskA.DEL=new Duration(1)
TaskA.DESC=Interview (see Checklist)\nAppraisal\nDocument Check\nPrepare Report
TaskA.EXPRI=2
TaskA.EXROL=Qualitymanager
TaskA.EXTYPE=0
TaskA.KINDC=Q002
TaskA.KINDN=Audit Interview, Appraisal,  Report
TaskA.NAM=Perform Quality Audit
TaskA.PRI=2
TaskA.ROL=Qualitymanager
TaskA.SKIP_TASK_LIST=false
TaskA.TYPE=0
TaskA.customFields.decimal.1=in1.ReportID
TaskA.customFields.timestamp.2=new DateTime(in1.Termin)
TaskA.customFields.varchar.1=in1.Auditor
TaskA.customFields.varchar.2=in1.Mitarbeiter
' #txt
Ct0 f0 taskAction 'ivy.case.setName(engine.expandMacros("Audit Stage Perform"));
ivy.case.setDescription(engine.expandMacros("Appraisal/Report"));
ivy.case.setProcessCategory(engine.expandMacros("Q002"), engine.expandMacros(""));
import ch.ivyteam.ivy.workflow.TaskDefinition;
List<TaskDefinition> taskDefinitions;
TaskDefinition taskDef;import ch.ivyteam.ivy.request.impl.DefaultCalendarProxy;
DefaultCalendarProxy calendarProxy = ivy.cal as DefaultCalendarProxy;
taskDef = new TaskDefinition();
taskDef.setStartRequestPath("TaskA.ivp");
taskDef.setName(engine.expandMacros("Perform Quality Audit"));
taskDef.setDescription(engine.expandMacros("Interview (see Checklist)
Appraisal
Document Check
Prepare Report"));
taskDef.setKindCode(engine.expandMacros("Q002"));
taskDef.setKindName(engine.expandMacros("Audit Interview, Appraisal,  Report"));
taskDef.setAutoStartTask(false);
taskDef.setActivator("Qualitymanager");
taskDef.setDelayPeriod(1000 * (new Duration(1)).toNumber());
taskDef.setPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
taskDef.setExpiryActivator("Qualitymanager");
taskDef.setExpiryPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
taskDef.setCustomVarCharField1(in1.Auditor);
taskDef.setCustomDecimalField1(in1.ReportID);
taskDef.setCustomVarCharField2(in1.Mitarbeiter);
taskDef.setCustomTimestampField2(new DateTime(in1.Termin));
taskDefinitions.add(taskDef);
' #txt
Ct0 f0 type test004QualityManagement.PerformAudits #txt
Ct0 f0 template "" #txt
Ct0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>PerformAudit</name>
        <nameStyle>12,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ct0 f0 289 145 30 30 -35 17 #rect
Ct0 f0 @|TaskSwitchSimpleIcon #fIcon
Ct0 f0 -1|-1|-9671572 #nodeStyle
Ct0 f1 targetWindow NEW:card: #txt
Ct0 f1 targetDisplay TOP #txt
Ct0 f1 richDialogId test.ui.AuditReportGeneration #txt
Ct0 f1 startMethod start(String,String,Date) #txt
Ct0 f1 type test004QualityManagement.PerformAudits #txt
Ct0 f1 requestActionDecl '<String Mitarbeiter, String Auditor, Date Termin> param;' #txt
Ct0 f1 requestMappingAction 'param.Mitarbeiter=in.Mitarbeiter;
param.Auditor=in.Auditor;
param.Termin=in.Termin;
' #txt
Ct0 f1 responseActionDecl 'test004QualityManagement.PerformAudits out;
' #txt
Ct0 f1 responseMappingAction 'out=in;
out.compliant=result.compliant;
out.ReportID=result.ReportID;
' #txt
Ct0 f1 windowConfiguration '* ' #txt
Ct0 f1 isAsynch false #txt
Ct0 f1 isInnerRd false #txt
Ct0 f1 userContext '* ' #txt
Ct0 f1 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Generate Audit Report</name>
        <nameStyle>21,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ct0 f1 240 290 128 44 -60 -8 #rect
Ct0 f1 @|RichDialogIcon #fIcon
Ct0 f1 -1|-1|-9671572 #nodeStyle
Ct0 f3 expr data #txt
Ct0 f3 outCond ivp=="TaskA.ivp" #txt
Ct0 f3 304 175 304 290 #arcP
Ct0 f2 type test004QualityManagement.PerformAudits #txt
Ct0 f2 var in1 #txt
Ct0 f2 304 0 304 145 #arcP
Ct0 f4 expr out #txt
Ct0 f4 304 334 601 384 #arcP
Ct0 f4 1 304 384 #addKink
Ct0 f4 1 0.3557197143881604 0 0 #arcLabel
>Proto Ct0 0 0 32 24 18 0 #rect
>Proto Ct0 @|BIcon #fIcon
Ct1 g0 221 -6 6 6 10 16 #rect
Ct1 g0 @|MIGIcon #fIcon
Ct1 g1 301 501 6 6 10 16 #rect
Ct1 g1 @|MOGIcon #fIcon
Ct1 f3 targetWindow NEW:card: #txt
Ct1 f3 targetDisplay TOP #txt
Ct1 f3 richDialogId test.ui.AuditDetailsForm #txt
Ct1 f3 startMethod start() #txt
Ct1 f3 type test004QualityManagement.PerformAudits #txt
Ct1 f3 requestActionDecl '<> param;' #txt
Ct1 f3 responseActionDecl 'test004QualityManagement.PerformAudits out;
' #txt
Ct1 f3 responseMappingAction 'out=in;
out.Auditor=result.Auditor;
out.compliant=result.compliant;
out.Mitarbeiter=result.Mitarbeiter;
out.Termin=result.Termin;
' #txt
Ct1 f3 windowConfiguration '* ' #txt
Ct1 f3 isAsynch false #txt
Ct1 f3 isInnerRd false #txt
Ct1 f3 userContext '* ' #txt
Ct1 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>prepareAuditDetails</name>
        <nameStyle>19,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ct1 f3 208 346 128 44 -54 -8 #rect
Ct1 f3 @|RichDialogIcon #fIcon
Ct1 f3 -1|-1|-9671572 #nodeStyle
Ct1 f1 actionDecl 'test004QualityManagement.PerformAudits out;
' #txt
Ct1 f1 actionTable 'out=in1;
' #txt
Ct1 f1 outTypes "test004QualityManagement.PerformAudits" #txt
Ct1 f1 outLinks "TaskA.ivp" #txt
Ct1 f1 caseData '#
#Thu Apr 23 15:28:15 ICT 2015
businessCalendarName=
businessCreator.user=
businessMilestone.timestamp=
businessObject.code=
businessObject.docDb.code=
businessObject.folder.id=
businessObject.name=
businessPriority=
businessStart.timestamp=
case.description=ISO9001 Audit
case.name=Audit Status Send Programme
correspondent.id=
mainContact.docDb.code=
mainContact.folder.id=
mainContact.id=
mainContact.name=
mainContact.type=
process.code=
process.name=
processCategory.code=Q001
processCategory.name=Quality Audit
subType.code=
subType.name=
type.code=
type.name=
' #txt
Ct1 f1 taskData '#
#Thu Apr 23 15:28:15 ICT 2015
TaskA.DESC=Invite participants, define audit topics, \nset audit time slots, prepare checklist
TaskA.EXPRI=2
TaskA.EXROL=Everybody
TaskA.EXTYPE=0
TaskA.KINDC=Q1
TaskA.KINDN=Quality Audit
TaskA.NAM=Send Audit Programme
TaskA.PRI=2
TaskA.ROL=Qualitymanager
TaskA.SKIP_TASK_LIST=false
TaskA.TYPE=0
TaskA.customFields.varchar.1="ISO 9001 Audit"
' #txt
Ct1 f1 taskAction 'ivy.case.setName(engine.expandMacros("Audit Status Send Programme"));
ivy.case.setDescription(engine.expandMacros("ISO9001 Audit"));
ivy.case.setProcessCategory(engine.expandMacros("Q001"), engine.expandMacros("Quality Audit"));
import ch.ivyteam.ivy.workflow.TaskDefinition;
List<TaskDefinition> taskDefinitions;
TaskDefinition taskDef;import ch.ivyteam.ivy.request.impl.DefaultCalendarProxy;
DefaultCalendarProxy calendarProxy = ivy.cal as DefaultCalendarProxy;
taskDef = new TaskDefinition();
taskDef.setStartRequestPath("TaskA.ivp");
taskDef.setName(engine.expandMacros("Send Audit Programme"));
taskDef.setDescription(engine.expandMacros("Invite participants, define audit topics, 
set audit time slots, prepare checklist"));
taskDef.setKindCode(engine.expandMacros("Q1"));
taskDef.setKindName(engine.expandMacros("Quality Audit"));
taskDef.setAutoStartTask(false);
taskDef.setActivator("Qualitymanager");
taskDef.setPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
taskDef.setExpiryActivator("Everybody");
taskDef.setExpiryPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
taskDef.setCustomVarCharField1("ISO 9001 Audit");
taskDefinitions.add(taskDef);
' #txt
Ct1 f1 type test004QualityManagement.PerformAudits #txt
Ct1 f1 template "traffic-control.jsp" #txt
Ct1 f1 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>sendAuditProgramme</name>
        <nameStyle>18,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ct1 f1 257 217 30 30 -60 17 #rect
Ct1 f1 @|TaskSwitchSimpleIcon #fIcon
Ct1 f1 -1|-1|-9671572 #nodeStyle
Ct1 f4 expr data #txt
Ct1 f4 outCond ivp=="TaskA.ivp" #txt
Ct1 f4 272 247 272 346 #arcP
Ct1 f0 type test004QualityManagement.PerformAudits #txt
Ct1 f0 var in1 #txt
Ct1 f0 template traffic-control.jsp #txt
Ct1 f0 225 0 268 217 #arcP
Ct1 f2 expr out #txt
Ct1 f2 277 390 303 501 #arcP
Ct1 g2 601 149 6 6 10 16 #rect
Ct1 g2 @|MIGIcon #fIcon
>Proto Ct1 0 0 32 24 18 0 #rect
>Proto Ct1 @|BIcon #fIcon
Ct2 g0 -6 109 6 6 10 16 #rect
Ct2 g0 @|MIGIcon #fIcon
Ct2 g1 309 501 6 6 10 16 #rect
Ct2 g1 @|MOGIcon #fIcon
Ct2 f1 actionDecl 'test004QualityManagement.PerformAudits out;
' #txt
Ct2 f1 actionTable 'out=in1;
' #txt
Ct2 f1 outTypes "test004QualityManagement.PerformAudits" #txt
Ct2 f1 outLinks "TaskA.ivp" #txt
Ct2 f1 caseData '#
#Thu Apr 23 15:28:28 ICT 2015
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
Ct2 f1 taskData '#
#Thu Apr 23 15:28:28 ICT 2015
TaskA.DEL=new Duration(5)
TaskA.DESC=Walk through action list\nClose action points
TaskA.EXP=new Duration (7)
TaskA.EXPRI=2
TaskA.EXROL=Employee
TaskA.EXTYPE=0
TaskA.KINDC=Q003
TaskA.KINDN=Action Closing
TaskA.NAM=Close action Points
TaskA.PRI=2
TaskA.ROL=Employee
TaskA.SKIP_TASK_LIST=false
TaskA.TYPE=0
TaskA.customFields.decimal.3=in1.ReportID
TaskA.customFields.varchar.1=in1.Mitarbeiter
' #txt
Ct2 f1 taskAction 'import ch.ivyteam.ivy.workflow.TaskDefinition;
List<TaskDefinition> taskDefinitions;
TaskDefinition taskDef;import ch.ivyteam.ivy.request.impl.DefaultCalendarProxy;
DefaultCalendarProxy calendarProxy = ivy.cal as DefaultCalendarProxy;
taskDef = new TaskDefinition();
taskDef.setStartRequestPath("TaskA.ivp");
taskDef.setName(engine.expandMacros("Close action Points"));
taskDef.setDescription(engine.expandMacros("Walk through action list
Close action points"));
taskDef.setKindCode(engine.expandMacros("Q003"));
taskDef.setKindName(engine.expandMacros("Action Closing"));
taskDef.setAutoStartTask(false);
taskDef.setActivator("Employee");
taskDef.setDelayPeriod(1000 * (new Duration(5)).toNumber());
taskDef.setPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
taskDef.setExpiryPeriod(1000 * (new Duration (7)).toNumber());
taskDef.setExpiryActivator("Employee");
taskDef.setExpiryPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
taskDef.setCustomVarCharField1(in1.Mitarbeiter);
taskDef.setCustomDecimalField3(in1.ReportID);
taskDefinitions.add(taskDef);
' #txt
Ct2 f1 type test004QualityManagement.PerformAudits #txt
Ct2 f1 template "traffic-control.jsp" #txt
Ct2 f1 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>ActionList</name>
        <nameStyle>10,7
</nameStyle>
        <desc>Close action points from audit report</desc>
    </language>
</elementInfo>
' #txt
Ct2 f1 297 241 30 30 -26 17 #rect
Ct2 f1 @|TaskSwitchSimpleIcon #fIcon
Ct2 f1 -1|-1|-9671572 #nodeStyle
Ct2 f0 type test004QualityManagement.PerformAudits #txt
Ct2 f0 var in1 #txt
Ct2 f0 template traffic-control.jsp #txt
Ct2 f0 0 112 312 241 #arcP
Ct2 f0 1 312 112 #addKink
Ct2 f0 0 0.8027211516489057 0 0 #arcLabel
Ct2 f2 expr data #txt
Ct2 f2 outCond ivp=="TaskA.ivp" #txt
Ct2 f2 312 271 312 501 #arcP
>Proto Ct2 0 0 32 24 18 0 #rect
>Proto Ct2 @|BIcon #fIcon
ps0 2b0 g1 f1 tail #connect
ps0 f1 head 1b0 g0 #connect
ps0 1b0 g1 f2 tail #connect
ps0 f2 head 3b0 g0 #connect
ps0 3b0 g1 f3 tail #connect
ps0 f3 head f4 mainIn #connect
ps0 f5 mainOut f7 tail #connect
ps0 f7 head f6 mainIn #connect
ps0 f6 mainOut f0 tail #connect
ps0 f0 head 2b0 g0 #connect
Ct0 f0 out f3 tail #connect
Ct0 f3 head f1 mainIn #connect
Ct0 g0 m f2 tail #connect
Ct0 f2 head f0 in #connect
Ct0 f1 mainOut f4 tail #connect
Ct0 f4 head g1 m #connect
Ct0 0 0 600 500 0 #ivRect
Ct1 f1 out f4 tail #connect
Ct1 f4 head f3 mainIn #connect
Ct1 g0 m f0 tail #connect
Ct1 f0 head f1 in #connect
Ct1 f3 mainOut f2 tail #connect
Ct1 f2 head g1 m #connect
Ct1 0 0 600 500 0 #ivRect
Ct2 g0 m f0 tail #connect
Ct2 f0 head f1 in #connect
Ct2 f1 out f2 tail #connect
Ct2 f2 head g1 m #connect
Ct2 0 0 600 500 0 #ivRect
