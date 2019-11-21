[Ivy]
162511D2577DBA88 7.5.0 #module
>Proto >Proto Collection #zClass
Cs0 CreateTestData Big #zClass
Cs0 B #cInfo
Cs0 #process
Cs0 @TextInP .type .type #zField
Cs0 @TextInP .processKind .processKind #zField
Cs0 @AnnotationInP-0n ai ai #zField
Cs0 @MessageFlowInP-0n messageIn messageIn #zField
Cs0 @MessageFlowOutP-0n messageOut messageOut #zField
Cs0 @TextInP .xml .xml #zField
Cs0 @TextInP .responsibility .responsibility #zField
Cs0 @StartRequest f0 '' #zField
Cs0 @EndTask f1 '' #zField
Cs0 @EndTask f4 '' #zField
Cs0 @EndTask f5 '' #zField
Cs0 @TaskSwitch f7 '' #zField
Cs0 @TkArc f2 '' #zField
Cs0 @PushWFArc f3 '' #zField
Cs0 @PushWFArc f6 '' #zField
Cs0 @PushWFArc f8 '' #zField
Cs0 @InfoButton f13 '' #zField
Cs0 @StartRequest f9 '' #zField
Cs0 @Alternative f46 '' #zField
Cs0 @Trigger f45 '' #zField
Cs0 @EndTask f48 '' #zField
Cs0 @GridStep f52 '' #zField
Cs0 @Alternative f50 '' #zField
Cs0 @PushWFArc f57 '' #zField
Cs0 @PushWFArc f56 '' #zField
Cs0 @PushWFArc f44 '' #zField
Cs0 @PushWFArc f49 '' #zField
Cs0 @PushWFArc f42 '' #zField
Cs0 @PushWFArc f10 '' #zField
Cs0 @StartRequest f43 '' #zField
Cs0 @EndTask f54 '' #zField
Cs0 @GridStep f58 '' #zField
Cs0 @TaskSwitchSimple f47 '' #zField
Cs0 @PushWFArc f59 '' #zField
Cs0 @TkArc f53 '' #zField
Cs0 @PushWFArc f55 '' #zField
Cs0 @UserDialog f11 '' #zField
Cs0 @TaskSwitch f12 '' #zField
Cs0 @StartRequest f14 '' #zField
Cs0 @UserDialog f15 '' #zField
Cs0 @EndTask f16 '' #zField
Cs0 @UserDialog f17 '' #zField
Cs0 @TkArc f18 '' #zField
Cs0 @PushWFArc f19 '' #zField
Cs0 @PushWFArc f20 '' #zField
Cs0 @PushWFArc f21 '' #zField
Cs0 @PushWFArc f22 '' #zField
Cs0 @PushWFArc f23 '' #zField
Cs0 @PushWFArc f24 '' #zField
>Proto Cs0 Cs0 CreateTestData #zField
Cs0 f0 outLink createTasksForTaskListCustomization.ivp #txt
Cs0 f0 inParamDecl '<> param;' #txt
Cs0 f0 requestEnabled true #txt
Cs0 f0 triggerEnabled false #txt
Cs0 f0 callSignature createTasksForTaskListCustomization() #txt
Cs0 f0 persist false #txt
Cs0 f0 startName 'Create Tasks For Task List Customization' #txt
Cs0 f0 startDescription 'Create 3 tasks, each task has customVarCharField5 and customTimestampField1' #txt
Cs0 f0 taskData 'TaskTriggered.EXPRI=2
TaskTriggered.EXROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.PRI=2
TaskTriggered.ROL=Everybody
TaskTriggered.TYPE=0' #txt
Cs0 f0 caseData businessCase.attach=true #txt
Cs0 f0 showInStartList 1 #txt
Cs0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>createTasksForTaskListCustomization.ivp</name>
    </language>
</elementInfo>
' #txt
Cs0 f0 @C|.responsibility Everybody #txt
Cs0 f0 81 81 30 30 -80 20 #rect
Cs0 f0 @|StartRequestIcon #fIcon
Cs0 f1 403 83 30 30 0 15 #rect
Cs0 f1 @|EndIcon #fIcon
Cs0 f4 403 19 30 30 0 15 #rect
Cs0 f4 @|EndIcon #fIcon
Cs0 f5 403 147 30 30 0 15 #rect
Cs0 f5 @|EndIcon #fIcon
Cs0 f7 actionTable 'out=in1;
' #txt
Cs0 f7 outLinks "TaskA.ivp","TaskB.ivp","TaskC.ivp" #txt
Cs0 f7 caseData 'case.category=LeaveRequest
case.name=Leave Request
customFields.STRING.CompanyName="AxonIvy"' #txt
Cs0 f7 taskData 'TaskA.CATEGORY=Drink
TaskA.DESC=Milk tea for 4 people
TaskA.EXP=new Duration("3H")
TaskA.EXPRI=2
TaskA.EXROL=Everybody
TaskA.EXTYPE=0
TaskA.NAM=Milk Tea Order
TaskA.PRI=2
TaskA.ROL="demo"
TaskA.SKIP_TASK_LIST=false
TaskA.TYPE=3
TaskA.customFields.STRING.CustomVarCharField1="Exterior"
TaskA.customFields.STRING.CustomVarCharField5="Sarah"
TaskA.customFields.TIMESTAMP.CustomTimestampField1=new DateTime()
TaskB.CATEGORY=Drink
TaskB.DESC=Beer for a big company with 10.000 people
TaskB.EXP=new Duration("1D")
TaskB.EXPRI=2
TaskB.EXROL=Everybody
TaskB.EXTYPE=0
TaskB.NAM=Beer Order
TaskB.PRI=1
TaskB.ROL=Everybody
TaskB.SKIP_TASK_LIST=false
TaskB.TYPE=0
TaskB.customFields.STRING.CustomVarCharField1="Interior"
TaskB.customFields.STRING.CustomVarCharField5="Julie"
TaskB.customFields.TIMESTAMP.CustomTimestampField1=new DateTime("2080-03-23 11\:11\:11")
TaskC.CATEGORY=Food
TaskC.DESC=Pizza for 10 people
TaskC.EXP=new Duration("2D")
TaskC.EXPRI=2
TaskC.EXROL=Everybody
TaskC.EXTYPE=0
TaskC.NAM=Pizza Order
TaskC.PRI=3
TaskC.ROL=Everybody
TaskC.SKIP_TASK_LIST=false
TaskC.TYPE=0
TaskC.customFields.STRING.CustomVarCharField1="Interior"
TaskC.customFields.STRING.CustomVarCharField5="Florian"
TaskC.customFields.TIMESTAMP.CustomTimestampField1=new DateTime()' #txt
Cs0 f7 template "" #txt
Cs0 f7 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Create tasks</name>
        <nameStyle>12,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f7 243 82 28 28 9 -21 #rect
Cs0 f7 @|TaskSwitchIcon #fIcon
Cs0 f2 expr out #txt
Cs0 f2 type ch.ivyteam.ivy.project.portal.examples.Data #txt
Cs0 f2 var in1 #txt
Cs0 f2 111 96 243 96 #arcP
Cs0 f3 expr data #txt
Cs0 f3 outCond ivp=="TaskA.ivp" #txt
Cs0 f3 271 96 403 97 #arcP
Cs0 f6 expr data #txt
Cs0 f6 outCond ivp=="TaskB.ivp" #txt
Cs0 f6 257 82 403 34 #arcP
Cs0 f6 1 257 34 #addKink
Cs0 f6 1 0.2820126980511695 0 0 #arcLabel
Cs0 f8 expr data #txt
Cs0 f8 outCond ivp=="TaskC.ivp" #txt
Cs0 f8 257 110 403 162 #arcP
Cs0 f8 1 257 162 #addKink
Cs0 f8 1 0.2517837545571593 0 0 #arcLabel
Cs0 f13 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>HOW TO RUN THIS EXAMPLE:

Run createTasksForTaskListCustomization.ivp process
This process will create 3 tasks, each task has filled customVarCharField5 and customTimestampField1,
 we will use these fields as custom fields in Task Widget.</name>
        <nameStyle>238,5
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f13 632 66 592 92 -288 -40 #rect
Cs0 f13 @|IBIcon #fIcon
Cs0 f9 outLink createCasesForCaseListCustomization.ivp #txt
Cs0 f9 inParamDecl '<> param;' #txt
Cs0 f9 inParamTable 'out.caseNumber=12;
out.count=0;
' #txt
Cs0 f9 requestEnabled true #txt
Cs0 f9 triggerEnabled false #txt
Cs0 f9 callSignature createCasesForCaseListCustomization() #txt
Cs0 f9 persist false #txt
Cs0 f9 taskData 'TaskTriggered.EXPRI=2
TaskTriggered.EXROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.PRI=2
TaskTriggered.ROL=Everybody
TaskTriggered.TYPE=0' #txt
Cs0 f9 caseData 'businessCase.attach=true
case.description=CreateCasesForCaseListCustomization
case.name=CreateCasesForCaseListCustomization
customFields.STRING.CustomVarCharField1="Parent Case"
customFields.TIMESTAMP.CustomTimestampField1=new DateTime()' #txt
Cs0 f9 showInStartList 1 #txt
Cs0 f9 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>createCasesForCaseListCustomization.ivp</name>
        <nameStyle>39,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f9 @C|.responsibility Everybody #txt
Cs0 f9 81 241 30 30 -72 18 #rect
Cs0 f9 @|StartRequestIcon #fIcon
Cs0 f46 700 239 32 32 0 16 #rect
Cs0 f46 @|AlternativeIcon #fIcon
Cs0 f45 processCall 'Start Processes/CreateTestData:startACase(Integer)' #txt
Cs0 f45 requestActionDecl '<Integer count> param;' #txt
Cs0 f45 requestMappingAction 'param.count=in.count;
' #txt
Cs0 f45 responseActionDecl 'ch.ivyteam.ivy.project.portal.examples.Data out;
' #txt
Cs0 f45 responseMappingAction 'out=in;
' #txt
Cs0 f45 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>CreateTestData</name>
        <nameStyle>14,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f45 372 233 112 44 -43 -8 #rect
Cs0 f45 @|TriggerIcon #fIcon
Cs0 f48 829 240 30 30 0 15 #rect
Cs0 f48 @|EndIcon #fIcon
Cs0 f52 actionTable 'out=in;
' #txt
Cs0 f52 actionCode in.count++; #txt
Cs0 f52 532 233 112 44 0 -8 #rect
Cs0 f52 @|StepIcon #fIcon
Cs0 f50 196 239 32 32 0 16 #rect
Cs0 f50 @|AlternativeIcon #fIcon
Cs0 f57 expr in #txt
Cs0 f57 716 239 212 239 #arcP
Cs0 f57 1 716 192 #addKink
Cs0 f57 2 212 192 #addKink
Cs0 f57 1 0.5 0 0 #arcLabel
Cs0 f56 expr out #txt
Cs0 f56 484 255 532 255 #arcP
Cs0 f44 expr out #txt
Cs0 f44 644 255 700 255 #arcP
Cs0 f49 expr in #txt
Cs0 f49 outCond 'in.count == in.caseNumber' #txt
Cs0 f49 732 255 829 255 #arcP
Cs0 f42 expr in #txt
Cs0 f42 228 255 372 255 #arcP
Cs0 f10 expr out #txt
Cs0 f10 110 255 196 255 #arcP
Cs0 f43 outLink startACase.ivp #txt
Cs0 f43 inParamDecl '<Integer count> param;' #txt
Cs0 f43 inParamTable 'out.count=param.count;
' #txt
Cs0 f43 requestEnabled false #txt
Cs0 f43 triggerEnabled true #txt
Cs0 f43 callSignature startACase(Integer) #txt
Cs0 f43 persist false #txt
Cs0 f43 taskData 'TaskTriggered.EXPRI=2
TaskTriggered.EXROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.PRI=2
TaskTriggered.ROL=SYSTEM
TaskTriggered.TYPE=0' #txt
Cs0 f43 caseData 'businessCase.attach=false
case.description=TestCase Description <%\=param.count%>
case.name=TestCase Name <%\=param.count%>
customFields.STRING.CustomVarCharField1="CustomVarCharField1" + param.count
customFields.TIMESTAMP.CustomTimestampField1=new DateTime()' #txt
Cs0 f43 showInStartList 1 #txt
Cs0 f43 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>startACase(Integer)</name>
        <nameStyle>19,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f43 @C|.responsibility Everybody #txt
Cs0 f43 77 369 30 30 -53 17 #rect
Cs0 f43 @|StartRequestIcon #fIcon
Cs0 f54 477 369 30 30 0 15 #rect
Cs0 f54 @|EndIcon #fIcon
Cs0 f58 actionTable 'out=in;
' #txt
Cs0 f58 actionCode 'String a;
if(in.count % 3 == 0){
	a = "CaseGroup/Group1/TestCase" + in.count;
	in.category = "TaskGroup/Group1/TestCase" + in.count;
} else if(in.count % 3 == 1) {
	a = "CaseGroup/Group2/TestCase" + in.count;
	in.category = "TaskGroup/Group2/TestCase" + in.count;
} else {
	a = "CaseGroup/Group3/TestCase" + in.count;
	in.category = "TaskGroup/Group3/TestCase" + in.count;
}
ivy.case.setCategoryPath(a);
' #txt
Cs0 f58 164 362 112 44 0 -8 #rect
Cs0 f58 @|StepIcon #fIcon
Cs0 f47 actionTable 'out=in1;
' #txt
Cs0 f47 outLinks "TaskA.ivp" #txt
Cs0 f47 caseData 'case.name=TestCase <%\=in1.count%>' #txt
Cs0 f47 taskData 'TaskA.CATEGORY=<%\=in1.category%>
TaskA.EXPRI=2
TaskA.EXROL=Everybody
TaskA.EXTYPE=0
TaskA.NAM=Task number <%\=in1.count%>
TaskA.PRI=2
TaskA.ROL=Everybody
TaskA.SKIP_TASK_LIST=false
TaskA.TYPE=0' #txt
Cs0 f47 template "" #txt
Cs0 f47 333 369 30 30 0 16 #rect
Cs0 f47 @|TaskSwitchSimpleIcon #fIcon
Cs0 f59 expr out #txt
Cs0 f59 107 384 164 384 #arcP
Cs0 f53 expr out #txt
Cs0 f53 type ch.ivyteam.ivy.project.portal.examples.Data #txt
Cs0 f53 var in1 #txt
Cs0 f53 276 384 333 384 #arcP
Cs0 f55 expr data #txt
Cs0 f55 outCond ivp=="TaskA.ivp" #txt
Cs0 f55 363 384 477 384 #arcP
Cs0 f11 dialogId ch.ivyteam.ivy.project.portal.examples.testdata.LeaveRequestForm #txt
Cs0 f11 startMethod start() #txt
Cs0 f11 requestActionDecl '<> param;' #txt
Cs0 f11 responseActionDecl 'internaltest.Data out;
' #txt
Cs0 f11 responseMappingAction 'out=in;
' #txt
Cs0 f11 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Show 
Task Form</name>
        <nameStyle>15,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f11 326 508 36 24 20 -2 #rect
Cs0 f11 @|UserDialogIcon #fIcon
Cs0 f12 actionTable 'out=in1;
' #txt
Cs0 f12 outLinks "TaskB.ivp","TaskA.ivp","TaskC.ivp" #txt
Cs0 f12 caseData 'case.category=LeaveRequest
case.name=Leave Request
customFields.NUMBER.CustomDecimalField2=36' #txt
Cs0 f12 taskData 'TaskB.CATEGORY=OtherLeave/SickLeave/Long
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
TaskB.customFields.NUMBER.CustomDecimalField2=14
TaskB.customFields.STRING.CustomVarCharField1="Interior"
TaskB.customFields.STRING.CustomVarCharField5="Tung Le"
TaskB.customFields.STRING.embedInFrame="false"
TaskB.customFields.TIMESTAMP.CustomTimestampField1=new DateTime()
TaskA.CATEGORY=AnnualLeave
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
TaskA.customFields.NUMBER.CustomDecimalField2=7
TaskA.customFields.STRING.CustomVarCharField1="Exterior"
TaskA.customFields.STRING.CustomVarCharField5="Long Do"
TaskA.customFields.STRING.embedInFrame="false"
TaskA.customFields.TIMESTAMP.CustomTimestampField1=new DateTime()
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
TaskC.customFields.NUMBER.CustomDecimalField2=13
TaskC.customFields.STRING.CustomVarCharField1="Interior"
TaskC.customFields.STRING.CustomVarCharField5="Anh Nguyen"
TaskC.customFields.STRING.embedInFrame="false"
TaskC.customFields.TIMESTAMP.CustomTimestampField1=new DateTime()' #txt
Cs0 f12 template "" #txt
Cs0 f12 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Create tasks</name>
        <nameStyle>12,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f12 202 506 28 28 -39 -35 #rect
Cs0 f12 @|TaskSwitchIcon #fIcon
Cs0 f14 outLink CategoriedLeaveRequest.ivp #txt
Cs0 f14 inParamDecl '<> param;' #txt
Cs0 f14 requestEnabled true #txt
Cs0 f14 triggerEnabled false #txt
Cs0 f14 callSignature CategoriedLeaveRequest() #txt
Cs0 f14 persist false #txt
Cs0 f14 startName 'Categoried Leave Request' #txt
Cs0 f14 taskData 'TaskTriggered.EXPRI=2
TaskTriggered.EXROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.PRI=2
TaskTriggered.ROL=Everybody
TaskTriggered.TYPE=0' #txt
Cs0 f14 caseData 'case.description=Leave Request Description
case.name=Leave Request
customFields.STRING.CustomVarCharField1="Leave Request CustomVarCharField1"
customFields.STRING.CustomVarCharField2="Leave Request CustomVarCharField2"
customFields.STRING.CustomVarCharField3="Leave Request CustomVarCharField3"
customFields.STRING.CustomVarCharField4="Leave Request CustomVarCharField4"
customFields.STRING.ProcessCategoryCode="pubRequested"
customFields.STRING.ProcessCategoryName="Publication Requested"
customFields.STRING.ProcessCode="pubRequested"
customFields.STRING.ProcessName="Publication Requested"' #txt
Cs0 f14 showInStartList 1 #txt
Cs0 f14 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>CategoriedLeaveRequest.ivp</name>
    </language>
</elementInfo>
' #txt
Cs0 f14 @C|.responsibility Everybody #txt
Cs0 f14 83 507 26 26 -73 16 #rect
Cs0 f14 @|StartRequestIcon #fIcon
Cs0 f15 dialogId ch.ivyteam.ivy.project.portal.examples.testdata.LeaveRequestForm #txt
Cs0 f15 startMethod start() #txt
Cs0 f15 requestActionDecl '<> param;' #txt
Cs0 f15 responseActionDecl 'internaltest.Data out;
' #txt
Cs0 f15 responseMappingAction 'out=in;
' #txt
Cs0 f15 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Show 
Task Form</name>
        <nameStyle>15,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f15 326 564 36 24 20 -2 #rect
Cs0 f15 @|UserDialogIcon #fIcon
Cs0 f16 595 507 26 26 14 0 #rect
Cs0 f16 @|EndIcon #fIcon
Cs0 f17 dialogId ch.ivyteam.ivy.project.portal.examples.testdata.LeaveRequestForm #txt
Cs0 f17 startMethod start() #txt
Cs0 f17 requestActionDecl '<> param;' #txt
Cs0 f17 responseActionDecl 'internaltest.Data out;
' #txt
Cs0 f17 responseMappingAction 'out=in;
' #txt
Cs0 f17 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
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
Cs0 f17 326 444 36 24 20 -2 #rect
Cs0 f17 @|UserDialogIcon #fIcon
Cs0 f18 expr out #txt
Cs0 f18 type internaltest.Data #txt
Cs0 f18 var in1 #txt
Cs0 f18 109 520 202 520 #arcP
Cs0 f19 expr data #txt
Cs0 f19 outCond ivp=="TaskA.ivp" #txt
Cs0 f19 216 506 326 456 #arcP
Cs0 f19 1 216 456 #addKink
Cs0 f19 1 0.21540035677224453 0 0 #arcLabel
Cs0 f20 expr out #txt
Cs0 f20 362 576 608 533 #arcP
Cs0 f20 1 608 576 #addKink
Cs0 f20 0 0.4489837825296078 0 0 #arcLabel
Cs0 f21 expr out #txt
Cs0 f21 362 520 595 520 #arcP
Cs0 f22 expr data #txt
Cs0 f22 outCond ivp=="TaskB.ivp" #txt
Cs0 f22 230 520 326 520 #arcP
Cs0 f23 expr data #txt
Cs0 f23 outCond ivp=="TaskC.ivp" #txt
Cs0 f23 216 534 326 576 #arcP
Cs0 f23 1 216 576 #addKink
Cs0 f23 1 0.24859679354129646 0 0 #arcLabel
Cs0 f24 expr out #txt
Cs0 f24 362 456 608 507 #arcP
Cs0 f24 1 608 456 #addKink
Cs0 f24 0 0.936929200171276 0 0 #arcLabel
>Proto Cs0 .type ch.ivyteam.ivy.project.portal.examples.Data #txt
>Proto Cs0 .processKind NORMAL #txt
>Proto Cs0 0 0 32 24 18 0 #rect
>Proto Cs0 @|BIcon #fIcon
Cs0 f0 mainOut f2 tail #connect
Cs0 f2 head f7 in #connect
Cs0 f7 out f3 tail #connect
Cs0 f3 head f1 mainIn #connect
Cs0 f7 out f6 tail #connect
Cs0 f6 head f4 mainIn #connect
Cs0 f7 out f8 tail #connect
Cs0 f8 head f5 mainIn #connect
Cs0 f46 out f49 tail #connect
Cs0 f49 head f48 mainIn #connect
Cs0 f50 out f42 tail #connect
Cs0 f42 head f45 mainIn #connect
Cs0 f45 mainOut f56 tail #connect
Cs0 f56 head f52 mainIn #connect
Cs0 f52 mainOut f44 tail #connect
Cs0 f44 head f46 in #connect
Cs0 f46 out f57 tail #connect
Cs0 f57 head f50 in #connect
Cs0 f9 mainOut f10 tail #connect
Cs0 f10 head f50 in #connect
Cs0 f47 out f55 tail #connect
Cs0 f55 head f54 mainIn #connect
Cs0 f43 mainOut f59 tail #connect
Cs0 f59 head f58 mainIn #connect
Cs0 f58 mainOut f53 tail #connect
Cs0 f53 head f47 in #connect
Cs0 f12 out f19 tail #connect
Cs0 f19 head f17 mainIn #connect
Cs0 f12 out f22 tail #connect
Cs0 f22 head f11 mainIn #connect
Cs0 f12 out f23 tail #connect
Cs0 f23 head f15 mainIn #connect
Cs0 f14 mainOut f18 tail #connect
Cs0 f18 head f12 in #connect
Cs0 f11 mainOut f21 tail #connect
Cs0 f21 head f16 mainIn #connect
Cs0 f17 mainOut f24 tail #connect
Cs0 f24 head f16 mainIn #connect
Cs0 f15 mainOut f20 tail #connect
Cs0 f20 head f16 mainIn #connect
