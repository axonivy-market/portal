[Ivy]
1470062B2127AF92 7.5.0 #module
>Proto >Proto Collection #zClass
Ps0 ProcessLeaves Big #zClass
Ps0 B #cInfo
Ps0 #process
Ct1 Component Big #zClass
Ct1 B #cInfo
Ct2 Component Big #zClass
Ct2 B #cInfo
Ct3 Component Big #zClass
Ct3 B #cInfo
Ps0 @TextInP .type .type #zField
Ps0 @TextInP .processKind .processKind #zField
Ps0 @AnnotationInP-0n ai ai #zField
Ps0 @MessageFlowInP-0n messageIn messageIn #zField
Ps0 @MessageFlowOutP-0n messageOut messageOut #zField
Ps0 @TextInP .xml .xml #zField
Ps0 @TextInP .responsibility .responsibility #zField
Ps0 @StartRequest f0 '' #zField
Ps0 @Alternative f9 '' #zField
Ps0 Ct1 1b0 '1 Sub' #zField
Ps0 Ct2 2b0 '2 Sub' #zField
Ps0 @PushWFArc f6 '' #zField
Ps0 Ct3 3b0 '3 Sub' #zField
Ps0 @GridStep f3 '' #zField
Ps0 @PushWFArc f7 '' #zField
Ps0 @GridStep f10 '' #zField
Ps0 @PushWFArc f11 '' #zField
Ps0 @PushWFArc f1 '' #zField
Ps0 @GridStep f12 '' #zField
Ps0 @PushWFArc f13 '' #zField
Ps0 @PushWFArc f8 '' #zField
Ps0 @GridStep f15 '' #zField
Ps0 @PushWFArc f16 '' #zField
Ps0 @PushWFArc f2 '' #zField
Ps0 @GridStep f34 '' #zField
Ps0 @PushWFArc f4 '' #zField
Ps0 @PushWFArc f5 '' #zField
Ps0 @TaskSwitchSimple f14 '' #zField
Ps0 @TkArc f19 '' #zField
>Proto Ps0 Ps0 ProcessLeaves #zField
Ct1 @TextInP .type .type #zField
Ct1 @TextInP .processKind .processKind #zField
Ct1 @AnnotationInP-0n ai ai #zField
Ct1 @TextInP .xml .xml #zField
Ct1 @TextInP .responsibility .responsibility #zField
Ct1 @UserDialog f1 '' #zField
Ct1 @PushTrueWFInG-01 g0 '' #zField
Ct1 @PushTrueWFOutG-01 g1 '' #zField
Ct1 @PushTrueWFInG-01 g2 '' #zField
Ct1 @PushWFArc f0 '' #zField
Ct1 @PushWFArc f2 '' #zField
Ct1 @PushWFArc f3 '' #zField
>Proto Ct1 Ct0 Component #zField
Ct2 @TextInP .type .type #zField
Ct2 @TextInP .processKind .processKind #zField
Ct2 @AnnotationInP-0n ai ai #zField
Ct2 @TextInP .xml .xml #zField
Ct2 @TextInP .responsibility .responsibility #zField
Ct2 @PushTrueWFInG-01 g0 '' #zField
Ct2 @PushTrueWFOutG-01 g1 '' #zField
Ct2 @UserDialog f1 '' #zField
Ct2 @TaskSwitch f5 '' #zField
Ct2 @TkArc f6 '' #zField
Ct2 @UserDialog f2 '' #zField
Ct2 @PushWFArc f8 '' #zField
Ct2 @PushWFArc f12 '' #zField
Ct2 @Alternative f0 '' #zField
Ct2 @PushWFArc f3 '' #zField
Ct2 @PushWFArc f7 '' #zField
Ct2 @PushWFArc f4 '' #zField
>Proto Ct2 Ct1 Component #zField
Ct3 @TextInP .type .type #zField
Ct3 @TextInP .processKind .processKind #zField
Ct3 @AnnotationInP-0n ai ai #zField
Ct3 @TextInP .xml .xml #zField
Ct3 @TextInP .responsibility .responsibility #zField
Ct3 @PushTrueWFInG-01 g0 '' #zField
Ct3 @PushTrueWFOutG-01 g1 '' #zField
Ct3 @UserDialog f3 '' #zField
Ct3 @TaskSwitchSimple f12 '' #zField
Ct3 @PushWFArc f1 '' #zField
Ct3 @TkArc f0 '' #zField
Ct3 @PushWFArc f2 '' #zField
>Proto Ct3 Ct2 Component #zField
Ps0 f0 outLink LeaveRequest.ivp #txt
Ps0 f0 inParamDecl '<> param;' #txt
Ps0 f0 requestEnabled true #txt
Ps0 f0 triggerEnabled false #txt
Ps0 f0 callSignature LeaveRequest() #txt
Ps0 f0 persist false #txt
Ps0 f0 startName 'Employee Leave Request' #txt
Ps0 f0 startDescription '<u><i>Urlaubsantrag erstellen und genehmigen</i></u>
' #txt
Ps0 f0 taskData 'TaskTriggered.DESC=Employees Leave Request. 
TaskTriggered.EXPRI=2
TaskTriggered.EXROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.NAM=Leave Request 
TaskTriggered.PRI=2
TaskTriggered.ROL=Everybody
TaskTriggered.TYPE=0' #txt
Ps0 f0 caseData 'case.name=Process Leave Management Case
customFields.STRING.ProcessCategoryCode="HolidayRequested"' #txt
Ps0 f0 showInStartList 1 #txt
Ps0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>LeaveRequest.ivp</name>
        <nameStyle>16,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ps0 f0 @C|.responsibility Everybody #txt
Ps0 f0 89 57 30 30 16 0 #rect
Ps0 f0 @|StartRequestIcon #fIcon
Ps0 f0 -1|-1|-9671572 #nodeStyle
Ps0 f9 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Approved?</name>
        <nameStyle>9,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ps0 f9 528 464 32 32 16 0 #rect
Ps0 f9 @|AlternativeIcon #fIcon
Ps0 f9 -1|-1|-9671572 #nodeStyle
Ps0 1b0 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language lang="en">
        <name>LeaveRequest</name>
        <nameStyle>12,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ps0 1b0 40 296 128 32 -40 -8 #rect
Ps0 1b0 @|BIcon #fIcon
Ps0 1b0 g0 0 -16 #fFoot
Ps0 1b0 g1 -2 16 #fFoot
Ps0 1b0 g2 64 8 #fFoot
Ps0 2b0 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language lang="en">
        <name>Approve</name>
        <nameStyle>7,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ps0 2b0 507 384 74 32 -22 -8 #rect
Ps0 2b0 @|BIcon #fIcon
Ps0 2b0 g0 -37 -8 #fFoot
Ps0 2b0 g1 0 16 #fFoot
Ps0 f6 544 416 544 464 #arcP
Ps0 3b0 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language lang="en">
        <name>Book</name>
        <nameStyle>4,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ps0 3b0 284 536 89 32 -14 -8 #rect
Ps0 3b0 @|BIcon #fIcon
Ps0 3b0 g0 0 -16 #fFoot
Ps0 3b0 g1 -1 16 #fFoot
Ps0 f3 actionTable 'out=in;
' #txt
Ps0 f3 actionCode 'in.processState.steps.add("Create Leave");
in.processState.steps.add("Approve by PM");
in.processState.steps.add("Book");
in.processState.steps.add("Finished");

in.processState.actualStepIndex = 0;' #txt
Ps0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>set
status</name>
        <nameStyle>4,7
6,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ps0 f3 86 228 36 24 26 -10 #rect
Ps0 f3 @|StepIcon #fIcon
Ps0 f7 expr out #txt
Ps0 f7 104 252 104 296 #arcP
Ps0 f10 actionTable 'out=in;
' #txt
Ps0 f10 actionCode 'in.processState.actualStepIndex = 1;' #txt
Ps0 f10 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>set
status</name>
        <nameStyle>4,7
6,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ps0 f10 446 380 36 24 -16 14 #rect
Ps0 f10 @|StepIcon #fIcon
Ps0 f11 102 328 446 392 #arcP
Ps0 f11 1 102 392 #addKink
Ps0 f11 1 0.39756061714119 0 0 #arcLabel
Ps0 f1 expr out #txt
Ps0 f1 482 392 507 392 #arcP
Ps0 f12 actionTable 'out=in;
' #txt
Ps0 f12 actionCode 'in.processState.actualStepIndex = 2;' #txt
Ps0 f12 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>set
status</name>
        <nameStyle>4,7
6,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ps0 f12 310 492 36 24 23 -11 #rect
Ps0 f12 @|StepIcon #fIcon
Ps0 f13 expr in #txt
Ps0 f13 outCond in.genehmigt #txt
Ps0 f13 528 480 328 492 #arcP
Ps0 f13 1 328 480 #addKink
Ps0 f13 0 0.6141304347826088 0 0 #arcLabel
Ps0 f8 expr out #txt
Ps0 f8 328 516 328 536 #arcP
Ps0 f15 actionTable 'out=in;
' #txt
Ps0 f15 actionCode 'in.processState.actualStepIndex = 0;' #txt
Ps0 f15 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>set
status</name>
        <nameStyle>4,7
6,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ps0 f15 206 308 36 24 -10 16 #rect
Ps0 f15 @|StepIcon #fIcon
Ps0 f16 expr in #txt
Ps0 f16 560 480 242 320 #arcP
Ps0 f16 1 592 480 #addKink
Ps0 f16 2 592 320 #addKink
Ps0 f16 0 0.8676470588235295 0 0 #arcLabel
Ps0 f2 expr out #txt
Ps0 f2 206 320 168 320 #arcP
Ps0 f2 0 0.5098157051282051 0 0 #arcLabel
Ps0 f34 actionTable 'out=in;
' #txt
Ps0 f34 actionCode 'ivy.case.customFields().stringField("CASE_DETAIL_PROCESS").set(ivy.html.startref("Start Processes/InternalSupportPortalHome/caseDetails.ivp"));



' #txt
Ps0 f34 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>set case details link</name>
        <nameStyle>21,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ps0 f34 86 156 36 24 20 -2 #rect
Ps0 f34 @|StepIcon #fIcon
Ps0 f4 expr out #txt
Ps0 f4 104 87 104 156 #arcP
Ps0 f5 expr out #txt
Ps0 f5 104 180 104 228 #arcP
Ps0 f14 actionTable 'out=in1;
' #txt
Ps0 f14 template "" #txt
Ps0 f14 330 626 28 28 14 0 #rect
Ps0 f14 @|TaskSwitchSimpleIcon #fIcon
Ps0 f19 type internaltest.ProcessLeaves #txt
Ps0 f19 var in1 #txt
Ps0 f19 327 568 330 640 #arcP
Ps0 f19 1 327 640 #addKink
Ps0 f19 1 0.9581297872967439 0 0 #arcLabel
>Proto Ps0 .type internaltest.ProcessLeaves #txt
>Proto Ps0 .processKind NORMAL #txt
>Proto Ps0 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <swimlaneLabel>Employee</swimlaneLabel>
        <swimlaneLabel>HR</swimlaneLabel>
        <swimlaneLabel>Manager</swimlaneLabel>
    </language>
    <swimlaneOrientation>true</swimlaneOrientation>
    <swimlaneSize>264</swimlaneSize>
    <swimlaneSize>168</swimlaneSize>
    <swimlaneSize>192</swimlaneSize>
    <swimlaneColor gradient="true">-2621541</swimlaneColor>
    <swimlaneColor gradient="true">-103</swimlaneColor>
    <swimlaneColor gradient="true">-26215</swimlaneColor>
    <swimlaneType>LANE</swimlaneType>
    <swimlaneType>LANE</swimlaneType>
    <swimlaneType>LANE</swimlaneType>
    <swimlaneSpaceBefore>0</swimlaneSpaceBefore>
    <swimlaneSpaceBefore>0</swimlaneSpaceBefore>
    <swimlaneSpaceBefore>0</swimlaneSpaceBefore>
</elementInfo>
' #txt
>Proto Ps0 0 0 32 24 18 0 #rect
>Proto Ps0 @|BIcon #fIcon
Ct1 f1 dialogId internaltest.ui.ProcessLeaves2 #txt
Ct1 f1 startMethod start(internalPortal.ProcessStatus) #txt
Ct1 f1 requestActionDecl '<internalPortal.ProcessStatus processStatus> param;' #txt
Ct1 f1 requestMappingAction 'param.processStatus=in.processState;
' #txt
Ct1 f1 responseActionDecl 'internaltest.ProcessLeaves out;
' #txt
Ct1 f1 responseMappingAction 'out.Art=result.Art;
out.beantragt=result.beantragt;
out.Bis=result.Bis;
out.Mitarbeiter=result.Mitarbeiter;
out.processState=result.processStatus;
out.Vertretung=result.Vertretung;
out.Von=result.Von;
' #txt
Ct1 f1 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Leave Request</name>
        <nameStyle>13,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ct1 f1 328 290 112 44 -41 -8 #rect
Ct1 f1 @|UserDialogIcon #fIcon
Ct1 f1 -1|-1|-9671572 #nodeStyle
Ct1 g0 325 85 6 6 -135 -21 #rect
Ct1 g0 @|MIGIcon #fIcon
Ct1 g1 381 421 6 6 120 -45 #rect
Ct1 g1 @|MOGIcon #fIcon
Ct1 g2 381 21 6 6 -202 -404 #rect
Ct1 g2 @|MIGIcon #fIcon
Ct1 f0 329 91 379 290 #arcP
Ct1 f2 384 27 384 290 #arcP
Ct1 f3 expr out #txt
Ct1 f3 384 334 384 421 #arcP
>Proto Ct0 0 0 32 24 18 0 #rect
>Proto Ct0 @|BIcon #fIcon
Ct2 g0 221 21 6 6 10 12 #rect
Ct2 g0 @|MIGIcon #fIcon
Ct2 g1 221 365 6 6 5 -3 #rect
Ct2 g1 @|MOGIcon #fIcon
Ct2 f1 dialogId internaltest.ui.approveLeave #txt
Ct2 f1 startMethod start(String,Date,Date,Boolean,String,internalPortal.ProcessStatus) #txt
Ct2 f1 requestActionDecl '<String Mitarbeiter,Date Von,Date Bis,Boolean beantragt,String Vertretung,internalPortal.ProcessStatus processStatus> param;' #txt
Ct2 f1 requestMappingAction 'param.Mitarbeiter=in.Mitarbeiter;
param.Von=in.Von;
param.Bis=in.Bis;
param.beantragt=in.beantragt;
param.Vertretung=in.Vertretung;
param.processStatus=in.processState;
' #txt
Ct2 f1 responseActionDecl 'internaltest.ProcessLeaves out;
' #txt
Ct2 f1 responseMappingAction 'out=in;
out.Ablehnungsgrund=result.Ablehnungsgrund;
out.genehmigt=result.genehmigt;
out.Mitarbeiter=result.Mitarbeiter;
out.processState=result.processStatus;
' #txt
Ct2 f1 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Approve Leaver Request for creator</name>
        <nameStyle>34,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ct2 f1 18 202 140 44 -67 -8 #rect
Ct2 f1 @|UserDialogIcon #fIcon
Ct2 f1 -1|-1|-9671572 #nodeStyle
Ct2 f5 actionTable 'out=in1;
' #txt
Ct2 f5 outLinks "TaskA.ivp","TaskB.ivp" #txt
Ct2 f5 taskData 'TaskA.CATEGORY=CategoryDemo/ApprovedByCREATOR
TaskA.DESC=<u><i>Manager approves Request by checking application and signing approve checkbox</i></u>
TaskA.EXP=new Duration("1D")
TaskA.EXPRI=2
TaskA.EXROL=Everybody
TaskA.EXTYPE=0
TaskA.NAM=Approve Leave Request for creator
TaskA.PRI=2
TaskA.ROL=CREATOR
TaskA.SKIP_TASK_LIST=false
TaskA.TYPE=0
TaskA.customFields.STRING.CustomVarCharField1=in1.Mitarbeiter
TaskA.customFields.STRING.CustomVarCharField2=in1.Von.toString()
TaskA.customFields.STRING.CustomVarCharField3=in1.Bis.toString()
TaskB.CATEGORY=CategoryDemo/ApprovedByManager
TaskB.DESC=Manager approves Request by checking application and signing approve checkbox
TaskB.EXP=new Duration("3D")
TaskB.EXPRI=2
TaskB.EXROL=Everybody
TaskB.EXTYPE=0
TaskB.NAM=Approve Leave Request for Manager
TaskB.PRI=2
TaskB.ROL=Manager
TaskB.SKIP_TASK_LIST=false
TaskB.TYPE=0' #txt
Ct2 f5 template "/ProcessPages/portalHome.ivc" #txt
Ct2 f5 210 98 28 28 14 0 #rect
Ct2 f5 @|TaskSwitchIcon #fIcon
Ct2 f6 type internaltest.ProcessLeaves #txt
Ct2 f6 var in1 #txt
Ct2 f6 template /ProcessPages/portalHome.ivc #txt
Ct2 f6 224 27 224 98 #arcP
Ct2 f6 0 0.4913622158543621 0 0 #arcLabel
Ct2 f2 dialogId internaltest.ui.approveLeave #txt
Ct2 f2 startMethod start(String,Date,Date,Boolean,String,internalPortal.ProcessStatus) #txt
Ct2 f2 requestActionDecl '<String Mitarbeiter,Date Von,Date Bis,Boolean beantragt,String Vertretung,internalPortal.ProcessStatus processStatus> param;' #txt
Ct2 f2 requestMappingAction 'param.Mitarbeiter=in.Mitarbeiter;
param.Von=in.Von;
param.Bis=in.Bis;
param.beantragt=in.beantragt;
param.Vertretung=in.Vertretung;
param.processStatus=in.processState;
' #txt
Ct2 f2 responseActionDecl 'internaltest.ProcessLeaves out;
' #txt
Ct2 f2 responseMappingAction 'out=in;
out.Ablehnungsgrund=result.Ablehnungsgrund;
out.genehmigt=result.genehmigt;
out.Mitarbeiter=result.Mitarbeiter;
out.processState=result.processStatus;
' #txt
Ct2 f2 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Approve Leaver Request for Manager</name>
        <nameStyle>34,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ct2 f2 272 202 144 44 -67 -8 #rect
Ct2 f2 @|UserDialogIcon #fIcon
Ct2 f2 -1|-1|-9671572 #nodeStyle
Ct2 f8 expr data #txt
Ct2 f8 outCond ivp=="TaskB.ivp" #txt
Ct2 f8 238 112 344 202 #arcP
Ct2 f8 1 344 112 #addKink
Ct2 f8 0 0.8248149772953176 0 0 #arcLabel
Ct2 f12 expr data #txt
Ct2 f12 outCond ivp=="TaskA.ivp" #txt
Ct2 f12 210 112 88 202 #arcP
Ct2 f12 1 88 112 #addKink
Ct2 f12 0 0.8364472043448651 0 0 #arcLabel
Ct2 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>JOIN</name>
        <nameStyle>4,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ct2 f0 210 282 28 28 14 0 #rect
Ct2 f0 @|AlternativeIcon #fIcon
Ct2 f3 expr out #txt
Ct2 f3 88 246 210 296 #arcP
Ct2 f3 1 88 296 #addKink
Ct2 f3 1 0.23877297458856933 0 0 #arcLabel
Ct2 f7 expr out #txt
Ct2 f7 344 246 238 296 #arcP
Ct2 f7 1 344 296 #addKink
Ct2 f7 1 0.08580556748987404 0 0 #arcLabel
Ct2 f4 expr in #txt
Ct2 f4 224 310 224 365 #arcP
>Proto Ct1 0 0 32 24 18 0 #rect
>Proto Ct1 @|BIcon #fIcon
Ct3 g0 245 53 6 6 10 16 #rect
Ct3 g0 @|MIGIcon #fIcon
Ct3 g1 245 341 6 6 10 16 #rect
Ct3 g1 @|MOGIcon #fIcon
Ct3 f3 dialogId internaltest.ui.bookRequest #txt
Ct3 f3 startMethod start(String,Date,Date,String,Boolean,Boolean,String,String,internalPortal.ProcessStatus) #txt
Ct3 f3 requestActionDecl '<String Mitarbeiter,Date Von,Date Bis,String Art,Boolean beantragt,Boolean genehmigt,String Ablehnungsgrund,String Vertretung,internalPortal.ProcessStatus processStatus> param;' #txt
Ct3 f3 requestMappingAction 'param.Mitarbeiter=in.Mitarbeiter;
param.Von=in.Von;
param.Bis=in.Bis;
param.Art=in.Art;
param.beantragt=in.beantragt;
param.genehmigt=in.genehmigt;
param.Ablehnungsgrund=in.Ablehnungsgrund;
param.Vertretung=in.Vertretung;
param.processStatus=in.processState;
' #txt
Ct3 f3 responseActionDecl 'internaltest.ProcessLeaves out;
' #txt
Ct3 f3 responseMappingAction 'out=in;
out.AnzahlTage=result.AnzahlTage;
out.processState=result.processStatus;
out.Resturlaubstage=result.Resturlaubstage;
out.storniert=result.storniert;
' #txt
Ct3 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Book Request</name>
        <nameStyle>12,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ct3 f3 192 218 112 44 -39 -8 #rect
Ct3 f3 @|UserDialogIcon #fIcon
Ct3 f3 -1|-1|-9671572 #nodeStyle
Ct3 f12 actionTable 'out=in1;
' #txt
Ct3 f12 outLinks "TaskA.ivp" #txt
Ct3 f12 caseData 'case.name=Leavers Request' #txt
Ct3 f12 taskData 'TaskA.EXPRI=2
TaskA.EXROL=Everybody
TaskA.EXTYPE=0
TaskA.NAM=Book Remaining Leave Entitlement
TaskA.PRI=2
TaskA.ROL=Human Ressources
TaskA.SKIP_TASK_LIST=false
TaskA.TYPE=0
TaskA.customFields.NUMBER.CustomDecimalField1=in1.Von.toNumber()
TaskA.customFields.NUMBER.CustomDecimalField2=in1.Bis.toNumber()
TaskA.customFields.NUMBER.CustomDecimalField3=in1.Bis.toNumber()-in1.Von.toNumber()
TaskA.customFields.STRING.CustomVarCharField1=in1.Mitarbeiter
TaskA.customFields.STRING.CustomVarCharField5="CategoryDemo/BookByHR"' #txt
Ct3 f12 template "/ProcessPages/portalHome.ivc" #txt
Ct3 f12 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Book Remaining Leave Entitlement</name>
        <nameStyle>32,7
</nameStyle>
        <desc>Bucht Resturlaubsanspruch / Resturlaubstage. </desc>
    </language>
</elementInfo>
' #txt
Ct3 f12 233 105 30 30 15 0 #rect
Ct3 f12 @|TaskSwitchSimpleIcon #fIcon
Ct3 f12 -1|-1|-9671572 #nodeStyle
Ct3 f1 expr data #txt
Ct3 f1 outCond ivp=="TaskA.ivp" #txt
Ct3 f1 248 135 248 218 #arcP
Ct3 f0 type internaltest.ProcessLeaves #txt
Ct3 f0 var in1 #txt
Ct3 f0 template /ProcessPages/portalHome.ivc #txt
Ct3 f0 248 59 248 105 #arcP
Ct3 f2 expr out #txt
Ct3 f2 248 262 248 341 #arcP
>Proto Ct2 0 0 32 24 18 0 #rect
>Proto Ct2 @|BIcon #fIcon
Ps0 2b0 g1 f6 tail #connect
Ps0 f6 head f9 in #connect
Ps0 f3 mainOut f7 tail #connect
Ps0 f7 head 1b0 g0 #connect
Ps0 1b0 g1 f11 tail #connect
Ps0 f11 head f10 mainIn #connect
Ps0 f10 mainOut f1 tail #connect
Ps0 f1 head 2b0 g0 #connect
Ps0 f9 out f13 tail #connect
Ps0 f13 head f12 mainIn #connect
Ps0 f12 mainOut f8 tail #connect
Ps0 f8 head 3b0 g0 #connect
Ps0 f9 out f16 tail #connect
Ps0 f16 head f15 mainIn #connect
Ps0 f15 mainOut f2 tail #connect
Ps0 f2 head 1b0 g2 #connect
Ps0 f0 mainOut f4 tail #connect
Ps0 f4 head f34 mainIn #connect
Ps0 f34 mainOut f5 tail #connect
Ps0 f5 head f3 mainIn #connect
Ps0 3b0 g1 f19 tail #connect
Ps0 f19 head f14 in #connect
Ct1 g0 m f0 tail #connect
Ct1 f0 head f1 mainIn #connect
Ct1 g2 m f2 tail #connect
Ct1 f2 head f1 mainIn #connect
Ct1 f1 mainOut f3 tail #connect
Ct1 f3 head g1 m #connect
Ct1 0 0 600 500 0 #ivRect
Ct2 g0 m f6 tail #connect
Ct2 f6 head f5 in #connect
Ct2 f8 head f2 mainIn #connect
Ct2 f5 out f12 tail #connect
Ct2 f12 head f1 mainIn #connect
Ct2 f5 out f8 tail #connect
Ct2 f1 mainOut f3 tail #connect
Ct2 f3 head f0 in #connect
Ct2 f2 mainOut f7 tail #connect
Ct2 f7 head f0 in #connect
Ct2 f0 out f4 tail #connect
Ct2 f4 head g1 m #connect
Ct2 0 0 568 419 0 #ivRect
Ct3 f12 out f1 tail #connect
Ct3 f1 head f3 mainIn #connect
Ct3 g0 m f0 tail #connect
Ct3 f0 head f12 in #connect
Ct3 f3 mainOut f2 tail #connect
Ct3 f2 head g1 m #connect
Ct3 0 0 600 500 0 #ivRect
