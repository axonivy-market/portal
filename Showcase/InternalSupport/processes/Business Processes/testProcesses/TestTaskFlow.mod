[Ivy]
[>Created: Thu Apr 20 11:26:39 ICT 2017]
14B2FC03D2E87141 3.20 #module
>Proto >Proto Collection #zClass
Tt0 TestTaskFlow Big #zClass
Tt0 B #cInfo
Tt0 #process
Tt0 @TextInP .resExport .resExport #zField
Tt0 @TextInP .type .type #zField
Tt0 @TextInP .processKind .processKind #zField
Tt0 @AnnotationInP-0n ai ai #zField
Tt0 @TextInP .xml .xml #zField
Tt0 @TextInP .responsibility .responsibility #zField
Tt0 @StartRequest f5 '' #zField
Tt0 @TaskSwitch f7 '' #zField
Tt0 @RichDialog f9 '' #zField
Tt0 @RichDialog f11 '' #zField
Tt0 @RichDialog f12 '' #zField
Tt0 @PushWFArc f13 '' #zField
Tt0 @PushWFArc f14 '' #zField
Tt0 @PushWFArc f15 '' #zField
Tt0 @StartRequest f0 '' #zField
Tt0 @EndTask f22 '' #zField
Tt0 @TaskSwitch f23 '' #zField
Tt0 @PushWFArc f25 '' #zField
Tt0 @GridStep f1 '' #zField
Tt0 @PushWFArc f2 '' #zField
Tt0 @EndRequest f20 '' #zField
Tt0 @PushWFArc f18 '' #zField
Tt0 @PushWFArc f17 '' #zField
Tt0 @PushWFArc f19 '' #zField
Tt0 @TkArc f4 '' #zField
Tt0 @TaskSwitchSimple f8 '' #zField
Tt0 @EndTask f10 '' #zField
Tt0 @PushWFArc f21 '' #zField
Tt0 @StartRequest f6 '' #zField
Tt0 @TkArc f16 '' #zField
Tt0 @RichDialog f24 '' #zField
Tt0 @RichDialog f26 '' #zField
Tt0 @TaskSwitch f27 '' #zField
Tt0 @RichDialog f28 '' #zField
Tt0 @StartRequest f29 '' #zField
Tt0 @EndRequest f30 '' #zField
Tt0 @PushWFArc f31 '' #zField
Tt0 @PushWFArc f32 '' #zField
Tt0 @PushWFArc f33 '' #zField
Tt0 @TkArc f34 '' #zField
Tt0 @PushWFArc f35 '' #zField
Tt0 @PushWFArc f36 '' #zField
Tt0 @PushWFArc f37 '' #zField
Tt0 @ProcessException f38 '' #zField
Tt0 @GridStep f39 '' #zField
Tt0 @PushWFArc f40 '' #zField
Tt0 @TaskSwitchSimple f41 '' #zField
Tt0 @EndTask f42 '' #zField
Tt0 @TkArc f43 '' #zField
Tt0 @PushWFArc f44 '' #zField
Tt0 @TkArc f3 '' #zField
>Proto Tt0 Tt0 TestTaskFlow #zField
Tt0 f5 outLink CategoriedLeaveRequest.ivp #txt
Tt0 f5 type internaltest.Data #txt
Tt0 f5 inParamDecl '<> param;' #txt
Tt0 f5 actionDecl 'internaltest.Data out;
' #txt
Tt0 f5 guid 14B3A6DCB767D79A #txt
Tt0 f5 requestEnabled true #txt
Tt0 f5 triggerEnabled false #txt
Tt0 f5 callSignature CategoriedLeaveRequest() #txt
Tt0 f5 persist false #txt
Tt0 f5 startName 'Categoried Leave Request' #txt
Tt0 f5 taskData 'TaskTriggered.ROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.EXPRI=2
TaskTriggered.TYPE=0
TaskTriggered.PRI=2
TaskTriggered.EXROL=Everybody' #txt
Tt0 f5 caseData 'case.description=Leave Request Description
case.name=Leave Request
customFields.varchar.1="Leave Request CustomVarCharField1"
customFields.varchar.2="Leave Request CustomVarCharField2"
customFields.varchar.3="Leave Request CustomVarCharField3"
customFields.varchar.4="Leave Request CustomVarCharField4"
customFields.varchar.5="Leave Request CustomVarCharField5"
process.code=pubRequested
process.name=Publication Requested
processCategory.code=pubRequested
processCategory.name=Publication Requested' #txt
Tt0 f5 showInStartList 1 #txt
Tt0 f5 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>CategoriedLeaveRequest.ivp</name>
        <nameStyle>26,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Tt0 f5 @C|.responsibility Everybody #txt
Tt0 f5 187 51 26 26 14 0 #rect
Tt0 f5 @|StartRequestIcon #fIcon
Tt0 f7 actionDecl 'internaltest.Data out;
' #txt
Tt0 f7 actionTable 'out=in1;
' #txt
Tt0 f7 outTypes "internaltest.Data","internaltest.Data","internaltest.Data" #txt
Tt0 f7 outLinks "TaskA.ivp","TaskB.ivp","TaskC.ivp" #txt
Tt0 f7 taskData 'TaskA.CATEGORY=AnnualLeave
TaskA.DESC=Annual Leave Request Description
TaskA.EXP=new Duration("3H")
TaskA.EXPRI=2
TaskA.EXROL=Everybody
TaskA.EXTYPE=0
TaskA.NAM=Annual Leave Request
TaskA.PRI=2
TaskA.ROL="demo"
TaskA.SKIP_TASK_LIST=false
TaskA.TYPE=3
TaskA.customFields.varchar.1="Annual CustomVarCharField1"
TaskA.customFields.varchar.2="Annual CustomVarCharField2"
TaskA.customFields.varchar.3="Annual CustomVarCharField3"
TaskA.customFields.varchar.4="Annual CustomVarCharField4"
TaskA.customFields.varchar.5="Annual Leave"
TaskB.CATEGORY=OtherLeave/SickLeave/Long
TaskB.DESC=Sick Leave Request Description
TaskB.EXP=new Duration("1D")
TaskB.EXPRI=2
TaskB.EXROL=Everybody
TaskB.EXTYPE=0
TaskB.NAM=Sick Leave Request
TaskB.PRI=1
TaskB.ROL=Everybody
TaskB.SKIP_TASK_LIST=false
TaskB.TYPE=0
TaskB.customFields.varchar.1="Sick CustomVarCharField1"
TaskB.customFields.varchar.2="Sick CustomVarCharField2"
TaskB.customFields.varchar.3="Sick CustomVarCharField3"
TaskB.customFields.varchar.4="Sick CustomVarCharField4"
TaskB.customFields.varchar.5="Other Leave/Sick/Long"
TaskC.CATEGORY=OtherLeave/Maternity
TaskC.DESC=Maternity Leave Request Description
TaskC.EXP=new Duration("2D")
TaskC.EXPRI=2
TaskC.EXROL=Everybody
TaskC.EXTYPE=0
TaskC.NAM=Maternity Leave Request
TaskC.PRI=3
TaskC.ROL=Everybody
TaskC.SKIP_TASK_LIST=false
TaskC.TYPE=0
TaskC.customFields.varchar.1="Maternity CustomVarCharField1"
TaskC.customFields.varchar.2="Maternity CustomVarCharField2"
TaskC.customFields.varchar.3="Maternity CustomVarCharField3"
TaskC.customFields.varchar.4="Maternity CustomVarCharField4"
TaskC.customFields.varchar.5="Other Leave/Maternity"' #txt
Tt0 f7 type internaltest.Data #txt
Tt0 f7 template "/ProcessPages/portalHome.ivc" #txt
Tt0 f7 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Create tasks</name>
        <nameStyle>12,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Tt0 f7 186 178 28 28 9 -21 #rect
Tt0 f7 @|TaskSwitchIcon #fIcon
Tt0 f9 targetWindow NEW:card: #txt
Tt0 f9 targetDisplay TOP #txt
Tt0 f9 richDialogId internaltest.TaskForm #txt
Tt0 f9 startMethod start() #txt
Tt0 f9 type internaltest.Data #txt
Tt0 f9 requestActionDecl '<> param;' #txt
Tt0 f9 responseActionDecl 'internaltest.Data out;
' #txt
Tt0 f9 responseMappingAction 'out=in;
' #txt
Tt0 f9 windowConfiguration '* ' #txt
Tt0 f9 isAsynch false #txt
Tt0 f9 isInnerRd false #txt
Tt0 f9 userContext '* ' #txt
Tt0 f9 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Show 
Task Form</name>
        <nameStyle>6,7
9,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Tt0 f9 54 284 36 24 20 -2 #rect
Tt0 f9 @|RichDialogIcon #fIcon
Tt0 f11 targetWindow NEW:card: #txt
Tt0 f11 targetDisplay TOP #txt
Tt0 f11 richDialogId internaltest.TaskForm #txt
Tt0 f11 startMethod start() #txt
Tt0 f11 type internaltest.Data #txt
Tt0 f11 requestActionDecl '<> param;' #txt
Tt0 f11 responseActionDecl 'internaltest.Data out;
' #txt
Tt0 f11 responseMappingAction 'out=in;
' #txt
Tt0 f11 windowConfiguration '* ' #txt
Tt0 f11 isAsynch false #txt
Tt0 f11 isInnerRd false #txt
Tt0 f11 userContext '* ' #txt
Tt0 f11 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Show 
Task Form</name>
        <nameStyle>15,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Tt0 f11 182 284 36 24 20 -2 #rect
Tt0 f11 @|RichDialogIcon #fIcon
Tt0 f12 targetWindow NEW:card: #txt
Tt0 f12 targetDisplay TOP #txt
Tt0 f12 richDialogId internaltest.TaskForm #txt
Tt0 f12 startMethod start() #txt
Tt0 f12 type internaltest.Data #txt
Tt0 f12 requestActionDecl '<> param;' #txt
Tt0 f12 responseActionDecl 'internaltest.Data out;
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
        <name>Show 
Task Form</name>
        <nameStyle>15,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Tt0 f12 318 284 36 24 20 -2 #rect
Tt0 f12 @|RichDialogIcon #fIcon
Tt0 f13 expr data #txt
Tt0 f13 outCond ivp=="TaskA.ivp" #txt
Tt0 f13 186 192 72 284 #arcP
Tt0 f13 1 72 192 #addKink
Tt0 f13 0 0.8879104383411323 0 0 #arcLabel
Tt0 f14 expr data #txt
Tt0 f14 outCond ivp=="TaskB.ivp" #txt
Tt0 f14 200 206 200 284 #arcP
Tt0 f15 expr data #txt
Tt0 f15 outCond ivp=="TaskC.ivp" #txt
Tt0 f15 214 192 336 284 #arcP
Tt0 f15 1 336 192 #addKink
Tt0 f15 0 0.8643692094953389 0 0 #arcLabel
Tt0 f0 outLink CreateSupportTicket.ivp #txt
Tt0 f0 type internaltest.Data #txt
Tt0 f0 inParamDecl '<> param;' #txt
Tt0 f0 actionDecl 'internaltest.Data out;
' #txt
Tt0 f0 guid 14D41201484D00B4 #txt
Tt0 f0 requestEnabled true #txt
Tt0 f0 triggerEnabled false #txt
Tt0 f0 callSignature CreateSupportTicket() #txt
Tt0 f0 persist false #txt
Tt0 f0 startName 'Create Support Ticket' #txt
Tt0 f0 taskData '#
#Mon May 11 11:02:05 ICT 2015
TaskTriggered.ROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.EXPRI=2
TaskTriggered.TYPE=0
TaskTriggered.PRI=2
TaskTriggered.EXROL=Everybody
' #txt
Tt0 f0 caseData '#
#Mon May 11 11:02:05 ICT 2015
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
Tt0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Support Ticket</name>
        <nameStyle>14,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Tt0 f0 @C|.responsibility Everybody #txt
Tt0 f0 531 51 26 26 25 -2 #rect
Tt0 f0 @|StartRequestIcon #fIcon
Tt0 f22 type internaltest.Data #txt
Tt0 f22 531 403 26 26 14 0 #rect
Tt0 f22 @|EndIcon #fIcon
Tt0 f23 actionDecl 'internaltest.Data out;
' #txt
Tt0 f23 actionTable 'out=in1;
' #txt
Tt0 f23 outTypes "internaltest.Data" #txt
Tt0 f23 outLinks "TaskA.ivp" #txt
Tt0 f23 caseData 'case.name=SupportTicket
process.code=Ticket Process Code
process.name=Ticket Process Name
processCategory.code=Ticket Category Code
processCategory.name=Ticket Category Name' #txt
Tt0 f23 taskData 'TaskA.EXC=14B2FC03D2E87141-f38-buffer
TaskA.EXP=new Duration("3H")
TaskA.EXPRI=2
TaskA.EXTYPE=-1
TaskA.NAM=SupportTicket
TaskA.PRI=in1.taskPriority.intValue()
TaskA.ROL=ivy.session.getSessionUserName()
TaskA.SKIP_TASK_LIST=false
TaskA.TYPE=3' #txt
Tt0 f23 type internaltest.Data #txt
Tt0 f23 template "/ProcessPages/portalHome.ivc" #txt
Tt0 f23 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>SupportTicket</name>
        <nameStyle>13,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Tt0 f23 530 274 28 28 21 -3 #rect
Tt0 f23 @|TaskSwitchIcon #fIcon
Tt0 f25 expr data #txt
Tt0 f25 outCond ivp=="TaskA.ivp" #txt
Tt0 f25 544 302 544 403 #arcP
Tt0 f1 actionDecl 'internaltest.Data out;
' #txt
Tt0 f1 actionTable 'out=in;
' #txt
Tt0 f1 actionCode 'import ch.ivyteam.ivy.workflow.WorkflowPriority;
import ch.ivy.addon.portalkit.util.CaseUtils;

CaseUtils.setCaseDetailsProcess(ivy.case,ivy.html.startref("Start Processes/InternalSupportPortalHome/caseDetails.ivp"));
CaseUtils.setCaseMainContactFolderId(ivy.case,"http://www.axonactive.vn/");

in.expiredDate = new Duration(1,0,0,0,0,0);
in.taskPriority = WorkflowPriority.NORMAL;' #txt
Tt0 f1 type internaltest.Data #txt
Tt0 f1 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>add case details link
add contactfolder id link</name>
        <nameStyle>47,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Tt0 f1 526 180 36 24 20 -2 #rect
Tt0 f1 @|StepIcon #fIcon
Tt0 f2 expr out #txt
Tt0 f2 544 77 544 180 #arcP
Tt0 f20 type internaltest.Data #txt
Tt0 f20 template "/ProcessPages/portalHome.ivc" #txt
Tt0 f20 187 451 26 26 14 0 #rect
Tt0 f20 @|EndRequestIcon #fIcon
Tt0 f18 expr out #txt
Tt0 f18 72 308 187 464 #arcP
Tt0 f18 1 72 464 #addKink
Tt0 f18 1 0.032798207405834785 0 0 #arcLabel
Tt0 f17 expr out #txt
Tt0 f17 336 308 213 464 #arcP
Tt0 f17 1 336 464 #addKink
Tt0 f17 1 0.03785109119051796 0 0 #arcLabel
Tt0 f19 expr out #txt
Tt0 f19 200 308 200 451 #arcP
Tt0 f4 expr out #txt
Tt0 f4 type internaltest.Data #txt
Tt0 f4 var in1 #txt
Tt0 f4 template /ProcessPages/portalHome.ivc #txt
Tt0 f4 200 77 200 178 #arcP
Tt0 f8 actionDecl 'internaltest.Data out;
' #txt
Tt0 f8 actionTable 'out=in1;
' #txt
Tt0 f8 outTypes "internaltest.Data" #txt
Tt0 f8 outLinks "TaskA.ivp" #txt
Tt0 f8 caseData 'case.name=\u00D6sterreich Resource with ID 1212' #txt
Tt0 f8 taskData 'TaskA.EXP=new Duration("1D")
TaskA.EXPRI=2
TaskA.EXTYPE=-1
TaskA.NAM=\u00D6sterreich Resource with ID 1212
TaskA.PRI=2
TaskA.ROL=Everybody
TaskA.SKIP_TASK_LIST=false
TaskA.TYPE=0' #txt
Tt0 f8 type internaltest.Data #txt
Tt0 f8 template "" #txt
Tt0 f8 1059 155 26 26 13 0 #rect
Tt0 f8 @|TaskSwitchSimpleIcon #fIcon
Tt0 f10 type internaltest.Data #txt
Tt0 f10 1059 243 26 26 14 0 #rect
Tt0 f10 @|EndIcon #fIcon
Tt0 f21 expr data #txt
Tt0 f21 outCond ivp=="TaskA.ivp" #txt
Tt0 f21 1072 181 1072 243 #arcP
Tt0 f6 outLink CreateTaskWithSpecialCharacter.ivp #txt
Tt0 f6 type internaltest.Data #txt
Tt0 f6 inParamDecl '<> param;' #txt
Tt0 f6 actionDecl 'internaltest.Data out;
' #txt
Tt0 f6 guid 154EC137231EA5FE #txt
Tt0 f6 requestEnabled true #txt
Tt0 f6 triggerEnabled false #txt
Tt0 f6 callSignature CreateTaskWithSpecialCharacter() #txt
Tt0 f6 persist false #txt
Tt0 f6 taskData 'TaskTriggered.ROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.EXPRI=2
TaskTriggered.TYPE=0
TaskTriggered.PRI=2
TaskTriggered.EXROL=Everybody' #txt
Tt0 f6 showInStartList 1 #txt
Tt0 f6 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>CreateTaskWithSpecialCharacter.ivp</name>
    </language>
</elementInfo>
' #txt
Tt0 f6 @C|.responsibility Everybody #txt
Tt0 f6 1059 51 26 26 14 0 #rect
Tt0 f6 @|StartRequestIcon #fIcon
Tt0 f16 expr out #txt
Tt0 f16 type internaltest.Data #txt
Tt0 f16 var in1 #txt
Tt0 f16 1072 77 1072 155 #arcP
Tt0 f24 targetWindow NEW:card: #txt
Tt0 f24 targetDisplay TOP #txt
Tt0 f24 richDialogId internaltest.TaskForm #txt
Tt0 f24 startMethod start() #txt
Tt0 f24 type internaltest.Data #txt
Tt0 f24 requestActionDecl '<> param;' #txt
Tt0 f24 responseActionDecl 'internaltest.Data out;
' #txt
Tt0 f24 responseMappingAction 'out=in;
' #txt
Tt0 f24 windowConfiguration '* ' #txt
Tt0 f24 isAsynch false #txt
Tt0 f24 isInnerRd false #txt
Tt0 f24 userContext '* ' #txt
Tt0 f24 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Show 
Task Form</name>
        <nameStyle>15,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Tt0 f24 1628 272 36 24 20 -2 #rect
Tt0 f24 @|RichDialogIcon #fIcon
Tt0 f26 targetWindow NEW:card: #txt
Tt0 f26 targetDisplay TOP #txt
Tt0 f26 richDialogId internaltest.TaskForm #txt
Tt0 f26 startMethod start() #txt
Tt0 f26 type internaltest.Data #txt
Tt0 f26 requestActionDecl '<> param;' #txt
Tt0 f26 responseActionDecl 'internaltest.Data out;
' #txt
Tt0 f26 responseMappingAction 'out=in;
' #txt
Tt0 f26 windowConfiguration '* ' #txt
Tt0 f26 isAsynch false #txt
Tt0 f26 isInnerRd false #txt
Tt0 f26 userContext '* ' #txt
Tt0 f26 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Show 
Task Form</name>
        <nameStyle>6,7
9,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Tt0 f26 1364 272 36 24 20 -2 #rect
Tt0 f26 @|RichDialogIcon #fIcon
Tt0 f27 actionDecl 'internaltest.Data out;
' #txt
Tt0 f27 actionTable 'out=in1;
' #txt
Tt0 f27 outTypes "internaltest.Data","internaltest.Data","internaltest.Data" #txt
Tt0 f27 outLinks "TaskA.ivp","TaskB.ivp","TaskC.ivp" #txt
Tt0 f27 taskData 'TaskA.DESC=Annual Leave Request Description
TaskA.EXP=new Duration("3H")
TaskA.EXPRI=2
TaskA.EXROL=Everybody
TaskA.EXTYPE=0
TaskA.NAM=Annual Leave Request
TaskA.PRI=2
TaskA.ROL="demoNotExisted"
TaskA.SKIP_TASK_LIST=false
TaskA.TYPE=3
TaskA.customFields.varchar.1="Annual CustomVarCharField1"
TaskA.customFields.varchar.2="Annual CustomVarCharField2"
TaskA.customFields.varchar.3="Annual CustomVarCharField3"
TaskA.customFields.varchar.4="Annual CustomVarCharField4"
TaskA.customFields.varchar.5="Annual Leave"
TaskB.DESC=Sick Leave Request Description
TaskB.EXP=new Duration("1D")
TaskB.EXPRI=2
TaskB.EXROL=Everybody
TaskB.EXTYPE=0
TaskB.NAM=Sick Leave Request
TaskB.PRI=1
TaskB.ROL=Everybody
TaskB.SKIP_TASK_LIST=false
TaskB.TYPE=0
TaskB.customFields.varchar.1="Sick CustomVarCharField1"
TaskB.customFields.varchar.2="Sick CustomVarCharField2"
TaskB.customFields.varchar.3="Sick CustomVarCharField3"
TaskB.customFields.varchar.4="Sick CustomVarCharField4"
TaskB.customFields.varchar.5="Other Leave/Sick/Long"
TaskC.DESC=Maternity Leave Request Description
TaskC.EXP=new Duration("2D")
TaskC.EXPRI=2
TaskC.EXROL=Everybody
TaskC.EXTYPE=0
TaskC.NAM=Maternity Leave Request
TaskC.PRI=3
TaskC.ROL=Everybody
TaskC.SKIP_TASK_LIST=false
TaskC.TYPE=0
TaskC.customFields.varchar.1="Maternity CustomVarCharField1"
TaskC.customFields.varchar.2="Maternity CustomVarCharField2"
TaskC.customFields.varchar.3="Maternity CustomVarCharField3"
TaskC.customFields.varchar.4="Maternity CustomVarCharField4"
TaskC.customFields.varchar.5="Other Leave/Maternity"' #txt
Tt0 f27 type internaltest.Data #txt
Tt0 f27 template "/ProcessPages/portalHome.ivc" #txt
Tt0 f27 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Create tasks</name>
        <nameStyle>12,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Tt0 f27 1496 166 28 28 9 -21 #rect
Tt0 f27 @|TaskSwitchIcon #fIcon
Tt0 f28 targetWindow NEW:card: #txt
Tt0 f28 targetDisplay TOP #txt
Tt0 f28 richDialogId internaltest.TaskForm #txt
Tt0 f28 startMethod start() #txt
Tt0 f28 type internaltest.Data #txt
Tt0 f28 requestActionDecl '<> param;' #txt
Tt0 f28 responseActionDecl 'internaltest.Data out;
' #txt
Tt0 f28 responseMappingAction 'out=in;
' #txt
Tt0 f28 windowConfiguration '* ' #txt
Tt0 f28 isAsynch false #txt
Tt0 f28 isInnerRd false #txt
Tt0 f28 userContext '* ' #txt
Tt0 f28 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Show 
Task Form</name>
        <nameStyle>15,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Tt0 f28 1492 272 36 24 20 -2 #rect
Tt0 f28 @|RichDialogIcon #fIcon
Tt0 f29 outLink TestRelatedTaskWithNoResponsible.ivp #txt
Tt0 f29 type internaltest.Data #txt
Tt0 f29 inParamDecl '<> param;' #txt
Tt0 f29 actionDecl 'internaltest.Data out;
' #txt
Tt0 f29 guid 158E17E6177AFF46 #txt
Tt0 f29 requestEnabled true #txt
Tt0 f29 triggerEnabled false #txt
Tt0 f29 callSignature TestRelatedTaskWithNoResponsible() #txt
Tt0 f29 persist false #txt
Tt0 f29 startName 'Categoried Leave Request' #txt
Tt0 f29 startDescription 'Test for IVYPORTAL-3369' #txt
Tt0 f29 taskData 'TaskTriggered.ROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.EXPRI=2
TaskTriggered.TYPE=0
TaskTriggered.PRI=2
TaskTriggered.EXROL=Everybody' #txt
Tt0 f29 caseData 'case.description=Leave Request Description 
case.name=Leave Request Test For IVYPORTAL-3369
customFields.varchar.1="Leave Request CustomVarCharField1"
customFields.varchar.2="Leave Request CustomVarCharField2"
customFields.varchar.3="Leave Request CustomVarCharField3"
customFields.varchar.4="Leave Request CustomVarCharField4"
customFields.varchar.5="Leave Request CustomVarCharField5"
process.code=pubRequested
process.name=Publication Requested
processCategory.code=pubRequested
processCategory.name=Publication Requested' #txt
Tt0 f29 showInStartList 1 #txt
Tt0 f29 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>TestRelatedTaskWithNoResponsible.ivp</name>
        <nameStyle>36,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Tt0 f29 @C|.responsibility Everybody #txt
Tt0 f29 1497 39 26 26 14 0 #rect
Tt0 f29 @|StartRequestIcon #fIcon
Tt0 f30 type internaltest.Data #txt
Tt0 f30 template "/ProcessPages/portalHome.ivc" #txt
Tt0 f30 1497 439 26 26 14 0 #rect
Tt0 f30 @|EndRequestIcon #fIcon
Tt0 f31 expr data #txt
Tt0 f31 outCond ivp=="TaskC.ivp" #txt
Tt0 f31 1524 180 1646 272 #arcP
Tt0 f31 1 1646 180 #addKink
Tt0 f31 0 0.8643692094953389 0 0 #arcLabel
Tt0 f32 expr out #txt
Tt0 f32 1646 296 1523 452 #arcP
Tt0 f32 1 1646 452 #addKink
Tt0 f32 1 0.03785109119051796 0 0 #arcLabel
Tt0 f33 expr out #txt
Tt0 f33 1382 296 1497 452 #arcP
Tt0 f33 1 1382 452 #addKink
Tt0 f33 1 0.032798207405834785 0 0 #arcLabel
Tt0 f34 expr out #txt
Tt0 f34 type internaltest.Data #txt
Tt0 f34 var in1 #txt
Tt0 f34 template /ProcessPages/portalHome.ivc #txt
Tt0 f34 1510 65 1510 166 #arcP
Tt0 f35 expr data #txt
Tt0 f35 outCond ivp=="TaskB.ivp" #txt
Tt0 f35 1510 194 1510 272 #arcP
Tt0 f36 expr out #txt
Tt0 f36 1510 296 1510 439 #arcP
Tt0 f37 expr data #txt
Tt0 f37 outCond ivp=="TaskA.ivp" #txt
Tt0 f37 1496 180 1382 272 #arcP
Tt0 f37 1 1382 180 #addKink
Tt0 f37 0 0.8879104383411323 0 0 #arcLabel
Tt0 f38 .resExport export #txt
Tt0 f38 actionDecl 'internaltest.Data out;
' #txt
Tt0 f38 actionTable 'out=in;
' #txt
Tt0 f38 type internaltest.Data #txt
Tt0 f38 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>ExpirationHandler</name>
        <nameStyle>17,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Tt0 f38 787 59 26 26 14 0 #rect
Tt0 f38 @|ExceptionIcon #fIcon
Tt0 f39 actionDecl 'internaltest.Data out;
' #txt
Tt0 f39 actionTable 'out=in;
' #txt
Tt0 f39 actionCode 'import ch.ivyteam.ivy.workflow.WorkflowPriority;
in.taskPriority = WorkflowPriority.HIGH;' #txt
Tt0 f39 type internaltest.Data #txt
Tt0 f39 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>increase task priority</name>
        <nameStyle>22,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Tt0 f39 782 188 36 24 20 -2 #rect
Tt0 f39 @|StepIcon #fIcon
Tt0 f40 expr out #txt
Tt0 f40 800 85 800 188 #arcP
Tt0 f41 actionDecl 'internaltest.Data out;
' #txt
Tt0 f41 actionTable 'out=in1;
' #txt
Tt0 f41 outTypes "internaltest.Data" #txt
Tt0 f41 outLinks "TaskA.ivp" #txt
Tt0 f41 caseData 'case.name=SupportTicket
process.code=Ticket Process Code
process.name=Ticket Process Name
processCategory.code=Ticket Category Code
processCategory.name=Ticket Category Name' #txt
Tt0 f41 taskData 'TaskA.EXC=14B2FC03D2E87141-f38-buffer
TaskA.EXP=new Duration("1D")
TaskA.EXPRI=2
TaskA.EXROL=Everybody
TaskA.EXTYPE=0
TaskA.NAM=SupportTicket
TaskA.PRI=in1.taskPriority.intValue()
TaskA.ROL=Everybody
TaskA.SKIP_TASK_LIST=false
TaskA.TYPE=0' #txt
Tt0 f41 type internaltest.Data #txt
Tt0 f41 template "/ProcessPages/portalHome.ivc" #txt
Tt0 f41 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>support ticket</name>
        <nameStyle>14,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Tt0 f41 787 275 26 26 13 0 #rect
Tt0 f41 @|TaskSwitchSimpleIcon #fIcon
Tt0 f42 type internaltest.Data #txt
Tt0 f42 787 403 26 26 14 0 #rect
Tt0 f42 @|EndIcon #fIcon
Tt0 f43 expr out #txt
Tt0 f43 type internaltest.Data #txt
Tt0 f43 var in1 #txt
Tt0 f43 template /ProcessPages/portalHome.ivc #txt
Tt0 f43 800 212 800 275 #arcP
Tt0 f44 expr data #txt
Tt0 f44 outCond ivp=="TaskA.ivp" #txt
Tt0 f44 800 301 800 403 #arcP
Tt0 f3 expr out #txt
Tt0 f3 type internaltest.Data #txt
Tt0 f3 var in1 #txt
Tt0 f3 template /ProcessPages/portalHome.ivc #txt
Tt0 f3 544 204 544 274 #arcP
>Proto Tt0 .type internaltest.Data #txt
>Proto Tt0 .processKind NORMAL #txt
>Proto Tt0 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <swimlaneLabel>Create Tasks</swimlaneLabel>
        <swimlaneLabel></swimlaneLabel>
    </language>
    <swimlaneSize>424</swimlaneSize>
    <swimlaneColor>-1</swimlaneColor>
    <swimlaneSpaceBefore>0</swimlaneSpaceBefore>
</elementInfo>
' #txt
>Proto Tt0 0 0 32 24 18 0 #rect
>Proto Tt0 @|BIcon #fIcon
Tt0 f7 out f13 tail #connect
Tt0 f13 head f9 mainIn #connect
Tt0 f7 out f14 tail #connect
Tt0 f14 head f11 mainIn #connect
Tt0 f7 out f15 tail #connect
Tt0 f15 head f12 mainIn #connect
Tt0 f12 mainOut f17 tail #connect
Tt0 f17 head f20 mainIn #connect
Tt0 f11 mainOut f19 tail #connect
Tt0 f19 head f20 mainIn #connect
Tt0 f9 mainOut f18 tail #connect
Tt0 f18 head f20 mainIn #connect
Tt0 f23 out f25 tail #connect
Tt0 f25 head f22 mainIn #connect
Tt0 f0 mainOut f2 tail #connect
Tt0 f2 head f1 mainIn #connect
Tt0 f1 mainOut f3 tail #connect
Tt0 f3 head f23 in #connect
Tt0 f5 mainOut f4 tail #connect
Tt0 f4 head f7 in #connect
Tt0 f8 out f21 tail #connect
Tt0 f21 head f10 mainIn #connect
Tt0 f6 mainOut f16 tail #connect
Tt0 f16 head f8 in #connect
Tt0 f27 out f37 tail #connect
Tt0 f37 head f26 mainIn #connect
Tt0 f27 out f35 tail #connect
Tt0 f35 head f28 mainIn #connect
Tt0 f27 out f31 tail #connect
Tt0 f31 head f24 mainIn #connect
Tt0 f24 mainOut f32 tail #connect
Tt0 f32 head f30 mainIn #connect
Tt0 f28 mainOut f36 tail #connect
Tt0 f36 head f30 mainIn #connect
Tt0 f26 mainOut f33 tail #connect
Tt0 f33 head f30 mainIn #connect
Tt0 f29 mainOut f34 tail #connect
Tt0 f34 head f27 in #connect
Tt0 f38 mainOut f40 tail #connect
Tt0 f40 head f39 mainIn #connect
Tt0 f39 mainOut f43 tail #connect
Tt0 f43 head f41 in #connect
Tt0 f41 out f44 tail #connect
Tt0 f44 head f42 mainIn #connect
