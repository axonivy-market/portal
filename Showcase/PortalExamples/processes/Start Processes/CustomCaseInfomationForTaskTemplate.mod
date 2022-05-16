[Ivy]
180B2248A88E1510 7.5.0 #module
>Proto >Proto Collection #zClass
Ce0 CustomCaseInfomationForTaskTemplate Big #zClass
Ce0 B #cInfo
Ce0 #process
Ce0 @AnnotationInP-0n ai ai #zField
Ce0 @TextInP .type .type #zField
Ce0 @TextInP .processKind .processKind #zField
Ce0 @TextInP .xml .xml #zField
Ce0 @TextInP .responsibility .responsibility #zField
Ce0 @EndTask f17 '' #zField
Ce0 @StartRequest f15 '' #zField
Ce0 @UserDialog f3 '' #zField
Ce0 @StartRequest f0 '' #zField
Ce0 @EndTask f1 '' #zField
Ce0 @TaskSwitch f16 '' #zField
Ce0 @UserDialog f7 '' #zField
Ce0 @EndTask f13 '' #zField
Ce0 @StartRequest f10 '' #zField
Ce0 @StartRequest f5 '' #zField
Ce0 @TaskSwitch f11 '' #zField
Ce0 @EndTask f6 '' #zField
Ce0 @PushWFArc f4 '' #zField
Ce0 @PushWFArc f2 '' #zField
Ce0 @TkArc f19 '' #zField
Ce0 @TkArc f12 '' #zField
Ce0 @PushWFArc f18 '' #zField
Ce0 @PushWFArc f8 '' #zField
Ce0 @PushWFArc f14 '' #zField
Ce0 @PushWFArc f9 '' #zField
Ce0 @StartRequest f20 '' #zField
Ce0 @EndTask f21 '' #zField
Ce0 @UserDialog f22 '' #zField
Ce0 @PushWFArc f23 '' #zField
Ce0 @PushWFArc f24 '' #zField
>Proto Ce0 Ce0 CustomCaseInfomationForTaskTemplate #zField
Ce0 f17 433 465 30 30 0 15 #rect
Ce0 f17 @|EndIcon #fIcon
Ce0 f15 outLink createTaskForEmployeeB.ivp #txt
Ce0 f15 inParamDecl '<> param;' #txt
Ce0 f15 requestEnabled true #txt
Ce0 f15 triggerEnabled false #txt
Ce0 f15 callSignature createTaskForEmployeeB() #txt
Ce0 f15 caseData businessCase.attach=true #txt
Ce0 f15 showInStartList 0 #txt
Ce0 f15 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>createTaskForEmployeeB.ivp</name>
    </language>
</elementInfo>
' #txt
Ce0 f15 @C|.responsibility Everybody #txt
Ce0 f15 81 465 30 30 -21 17 #rect
Ce0 f15 @|StartRequestIcon #fIcon
Ce0 f3 dialogId ch.ivyteam.ivy.project.portal.examples.testdata.EmployeeSearch #txt
Ce0 f3 startMethod start() #txt
Ce0 f3 requestActionDecl '<> param;' #txt
Ce0 f3 responseMappingAction 'out=in;
' #txt
Ce0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>EmployeeSearch</name>
    </language>
</elementInfo>
' #txt
Ce0 f3 168 42 112 44 -47 -8 #rect
Ce0 f3 @|UserDialogIcon #fIcon
Ce0 f0 outLink employeeSearch.ivp #txt
Ce0 f0 inParamDecl '<> param;' #txt
Ce0 f0 requestEnabled true #txt
Ce0 f0 triggerEnabled false #txt
Ce0 f0 callSignature employeeSearch() #txt
Ce0 f0 startName 'Employee Search' #txt
Ce0 f0 caseData 'businessCase.attach=true
case.name=Case of Search list' #txt
Ce0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>employeeSearch.ivp</name>
    </language>
</elementInfo>
' #txt
Ce0 f0 @C|.responsibility Everybody #txt
Ce0 f0 81 49 30 30 -21 17 #rect
Ce0 f0 @|StartRequestIcon #fIcon
Ce0 f1 337 49 30 30 0 15 #rect
Ce0 f1 @|EndIcon #fIcon
Ce0 f16 actionTable 'out=in1;
' #txt
Ce0 f16 outLinks "TaskA.ivp" #txt
Ce0 f16 caseData 'case.name=Customer B (002)
customFields.STRING.customerId="002"' #txt
Ce0 f16 taskData 'TaskA.NAM=Customer B (002)
TaskA.customFields.STRING.customerId="002"' #txt
Ce0 f16 240 464 32 32 0 16 #rect
Ce0 f16 @|TaskSwitchIcon #fIcon
Ce0 f7 dialogId ch.ivyteam.ivy.project.portal.examples.testdata.EmployeeDetails #txt
Ce0 f7 startMethod start(Integer) #txt
Ce0 f7 requestActionDecl '<Integer employeeId> param;' #txt
Ce0 f7 requestMappingAction 'param.employeeId=in.employeeInfo.id;
' #txt
Ce0 f7 responseMappingAction 'out=in;
' #txt
Ce0 f7 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>EmployeeDetails</name>
    </language>
</elementInfo>
' #txt
Ce0 f7 168 138 112 44 -47 -8 #rect
Ce0 f7 @|UserDialogIcon #fIcon
Ce0 f13 433 345 30 30 0 15 #rect
Ce0 f13 @|EndIcon #fIcon
Ce0 f10 outLink createTaskForEmployeeA.ivp #txt
Ce0 f10 inParamDecl '<> param;' #txt
Ce0 f10 requestEnabled true #txt
Ce0 f10 triggerEnabled false #txt
Ce0 f10 callSignature createTaskForEmployeeA() #txt
Ce0 f10 caseData businessCase.attach=true #txt
Ce0 f10 showInStartList 0 #txt
Ce0 f10 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>createTaskForEmployeeA.ivp</name>
    </language>
</elementInfo>
' #txt
Ce0 f10 @C|.responsibility Everybody #txt
Ce0 f10 81 345 30 30 -21 17 #rect
Ce0 f10 @|StartRequestIcon #fIcon
Ce0 f5 outLink employeeDetails.ivp #txt
Ce0 f5 inParamDecl '<Integer employeeId> param;' #txt
Ce0 f5 inParamTable 'out.employeeInfo.id=param.employeeId;
' #txt
Ce0 f5 requestEnabled true #txt
Ce0 f5 triggerEnabled false #txt
Ce0 f5 callSignature employeeDetails(Integer) #txt
Ce0 f5 caseData 'businessCase.attach=true
case.name=Case for Employee details' #txt
Ce0 f5 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>employeeDetails.ivp</name>
    </language>
</elementInfo>
' #txt
Ce0 f5 @C|.responsibility Everybody #txt
Ce0 f5 81 145 30 30 -24 17 #rect
Ce0 f5 @|StartRequestIcon #fIcon
Ce0 f11 actionTable 'out=in1;
' #txt
Ce0 f11 outLinks "TaskA.ivp" #txt
Ce0 f11 caseData 'case.name=Customer A (001)
customFields.STRING.customerId="001"' #txt
Ce0 f11 taskData 'TaskA.NAM=Customer A (001)
TaskA.customFields.STRING.customerId="001"' #txt
Ce0 f11 240 344 32 32 0 16 #rect
Ce0 f11 @|TaskSwitchIcon #fIcon
Ce0 f6 353 145 30 30 0 15 #rect
Ce0 f6 @|EndIcon #fIcon
Ce0 f4 111 64 168 64 #arcP
Ce0 f2 280 64 337 64 #arcP
Ce0 f19 var in1 #txt
Ce0 f19 111 480 240 480 #arcP
Ce0 f12 var in1 #txt
Ce0 f12 111 360 240 360 #arcP
Ce0 f18 expr data #txt
Ce0 f18 outCond ivp=="TaskA.ivp" #txt
Ce0 f18 272 480 433 480 #arcP
Ce0 f8 111 160 168 160 #arcP
Ce0 f14 expr data #txt
Ce0 f14 outCond ivp=="TaskA.ivp" #txt
Ce0 f14 272 360 433 360 #arcP
Ce0 f9 280 160 353 160 #arcP
Ce0 f20 outLink employeeDetailsInFrame.ivp #txt
Ce0 f20 inParamDecl '<Integer employeeId> param;' #txt
Ce0 f20 inParamTable 'out.employeeInfo.id=param.employeeId;
' #txt
Ce0 f20 requestEnabled true #txt
Ce0 f20 triggerEnabled false #txt
Ce0 f20 callSignature employeeDetailsInFrame(Integer) #txt
Ce0 f20 caseData 'businessCase.attach=true
case.name=Case for Employee details in frame' #txt
Ce0 f20 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>employeeDetailsInFrame.ivp</name>
    </language>
</elementInfo>
' #txt
Ce0 f20 @C|.responsibility Everybody #txt
Ce0 f20 81 241 30 30 -49 26 #rect
Ce0 f20 @|StartRequestIcon #fIcon
Ce0 f21 353 241 30 30 0 15 #rect
Ce0 f21 @|EndIcon #fIcon
Ce0 f22 dialogId ch.ivyteam.ivy.project.portal.examples.testdata.EmployeeDetailsInFrame #txt
Ce0 f22 startMethod start(Integer) #txt
Ce0 f22 requestActionDecl '<Integer employeeId> param;' #txt
Ce0 f22 requestMappingAction 'param.employeeId=in.employeeInfo.id;
' #txt
Ce0 f22 responseMappingAction 'out=in;
' #txt
Ce0 f22 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>EmployeeDetailsInFrame</name>
    </language>
</elementInfo>
' #txt
Ce0 f22 168 234 144 44 -69 -8 #rect
Ce0 f22 @|UserDialogIcon #fIcon
Ce0 f23 111 256 168 256 #arcP
Ce0 f24 312 256 353 256 #arcP
>Proto Ce0 .type ch.ivyteam.ivy.project.portal.examples.Data #txt
>Proto Ce0 .processKind NORMAL #txt
>Proto Ce0 0 0 32 24 18 0 #rect
>Proto Ce0 @|BIcon #fIcon
Ce0 f0 mainOut f4 tail #connect
Ce0 f4 head f3 mainIn #connect
Ce0 f3 mainOut f2 tail #connect
Ce0 f2 head f1 mainIn #connect
Ce0 f5 mainOut f8 tail #connect
Ce0 f8 head f7 mainIn #connect
Ce0 f7 mainOut f9 tail #connect
Ce0 f9 head f6 mainIn #connect
Ce0 f10 mainOut f12 tail #connect
Ce0 f12 head f11 in #connect
Ce0 f11 out f14 tail #connect
Ce0 f14 head f13 mainIn #connect
Ce0 f15 mainOut f19 tail #connect
Ce0 f19 head f16 in #connect
Ce0 f16 out f18 tail #connect
Ce0 f18 head f17 mainIn #connect
Ce0 f20 mainOut f23 tail #connect
Ce0 f23 head f22 mainIn #connect
Ce0 f22 mainOut f24 tail #connect
Ce0 f24 head f21 mainIn #connect
