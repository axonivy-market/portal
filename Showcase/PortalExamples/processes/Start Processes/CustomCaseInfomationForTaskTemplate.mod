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
Ce0 @UserDialog f3 '' #zField
Ce0 @StartRequest f0 '' #zField
Ce0 @EndTask f1 '' #zField
Ce0 @PushWFArc f4 '' #zField
Ce0 @StartRequest f20 '' #zField
Ce0 @EndTask f21 '' #zField
Ce0 @UserDialog f22 '' #zField
Ce0 @PushWFArc f23 '' #zField
Ce0 @PushWFArc f24 '' #zField
Ce0 @PushWFArc f2 '' #zField
Ce0 @StartRequest f5 '' #zField
Ce0 @EndTask f6 '' #zField
Ce0 @UserDialog f7 '' #zField
Ce0 @PushWFArc f8 '' #zField
Ce0 @PushWFArc f9 '' #zField
Ce0 @StartRequest f25 '' #zField
Ce0 @UserDialog f26 '' #zField
Ce0 @EndTask f27 '' #zField
Ce0 @PushWFArc f28 '' #zField
Ce0 @PushWFArc f29 '' #zField
Ce0 @TaskSwitch f10 '' #zField
Ce0 @TaskSwitch f11 '' #zField
Ce0 @StartRequest f12 '' #zField
Ce0 @StartRequest f13 '' #zField
Ce0 @EndTask f14 '' #zField
Ce0 @EndTask f30 '' #zField
Ce0 @TkArc f31 '' #zField
Ce0 @TkArc f32 '' #zField
Ce0 @PushWFArc f33 '' #zField
Ce0 @PushWFArc f34 '' #zField
Ce0 @InfoButton f15 '' #zField
Ce0 @InfoButton f16 '' #zField
Ce0 @InfoButton f17 '' #zField
>Proto Ce0 Ce0 CustomCaseInfomationForTaskTemplate #zField
Ce0 f3 dialogId ch.ivyteam.ivy.project.portal.examples.testdata.EmployeeSearchInFrame #txt
Ce0 f3 startMethod start() #txt
Ce0 f3 requestActionDecl '<> param;' #txt
Ce0 f3 responseMappingAction 'out=in;
' #txt
Ce0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>EmployeeSearchInFrame</name>
    </language>
</elementInfo>
' #txt
Ce0 f3 192 234 144 44 -69 -8 #rect
Ce0 f3 @|UserDialogIcon #fIcon
Ce0 f0 outLink employeeSearchInFrame.ivp #txt
Ce0 f0 inParamDecl '<> param;' #txt
Ce0 f0 requestEnabled true #txt
Ce0 f0 triggerEnabled false #txt
Ce0 f0 callSignature employeeSearchInFrame() #txt
Ce0 f0 startName 'Employee Search In Iframe' #txt
Ce0 f0 caseData 'businessCase.attach=true
case.name=Case of Search list' #txt
Ce0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>employeeSearchInFrame.ivp</name>
    </language>
</elementInfo>
' #txt
Ce0 f0 @C|.responsibility Everybody #txt
Ce0 f0 81 241 30 30 -47 24 #rect
Ce0 f0 @|StartRequestIcon #fIcon
Ce0 f1 401 241 30 30 0 15 #rect
Ce0 f1 @|EndIcon #fIcon
Ce0 f4 111 256 192 256 #arcP
Ce0 f20 outLink employeeDetailsInFrame.ivp #txt
Ce0 f20 inParamDecl '<Integer employeeId> param;' #txt
Ce0 f20 inParamTable 'out.employeeInfo.id=param.employeeId;
' #txt
Ce0 f20 requestEnabled true #txt
Ce0 f20 triggerEnabled false #txt
Ce0 f20 callSignature employeeDetailsInFrame(Integer) #txt
Ce0 f20 startName 'Employee Details in frame' #txt
Ce0 f20 caseData 'businessCase.attach=true
case.name=Case for Employee details in frame' #txt
Ce0 f20 showInStartList 0 #txt
Ce0 f20 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>employeeDetailsInFrame.ivp</name>
    </language>
</elementInfo>
' #txt
Ce0 f20 @C|.responsibility Everybody #txt
Ce0 f20 81 337 30 30 -49 26 #rect
Ce0 f20 @|StartRequestIcon #fIcon
Ce0 f21 401 337 30 30 0 15 #rect
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
Ce0 f22 192 330 144 44 -69 -8 #rect
Ce0 f22 @|UserDialogIcon #fIcon
Ce0 f23 111 352 192 352 #arcP
Ce0 f24 336 352 401 352 #arcP
Ce0 f2 336 256 401 256 #arcP
Ce0 f5 outLink employeeSearch.ivp #txt
Ce0 f5 inParamDecl '<> param;' #txt
Ce0 f5 requestEnabled true #txt
Ce0 f5 triggerEnabled false #txt
Ce0 f5 callSignature employeeSearch() #txt
Ce0 f5 startName 'Employee Search' #txt
Ce0 f5 caseData 'businessCase.attach=true
customFields.STRING.embedInFrame="false"' #txt
Ce0 f5 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>employeeSearch.ivp</name>
    </language>
</elementInfo>
' #txt
Ce0 f5 @C|.responsibility Everybody #txt
Ce0 f5 81 497 30 30 -41 18 #rect
Ce0 f5 @|StartRequestIcon #fIcon
Ce0 f6 401 497 30 30 0 15 #rect
Ce0 f6 @|EndIcon #fIcon
Ce0 f7 dialogId ch.ivyteam.ivy.project.portal.examples.testdata.EmployeeSearch #txt
Ce0 f7 startMethod start() #txt
Ce0 f7 requestActionDecl '<> param;' #txt
Ce0 f7 responseMappingAction 'out=in;
' #txt
Ce0 f7 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>EmployeeSearch</name>
    </language>
</elementInfo>
' #txt
Ce0 f7 192 490 112 44 -47 -8 #rect
Ce0 f7 @|UserDialogIcon #fIcon
Ce0 f8 111 512 192 512 #arcP
Ce0 f9 304 512 401 512 #arcP
Ce0 f25 outLink employeeDetails.ivp #txt
Ce0 f25 inParamDecl '<Integer employeeId> param;' #txt
Ce0 f25 inParamTable 'out.employeeInfo.id=param.employeeId;
' #txt
Ce0 f25 requestEnabled true #txt
Ce0 f25 triggerEnabled false #txt
Ce0 f25 callSignature employeeDetails(Integer) #txt
Ce0 f25 startName 'Employee Details' #txt
Ce0 f25 caseData 'businessCase.attach=true
case.name=Case for Employee details
customFields.STRING.embedInFrame="false"' #txt
Ce0 f25 showInStartList 0 #txt
Ce0 f25 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>employeeDetails.ivp</name>
    </language>
</elementInfo>
' #txt
Ce0 f25 @C|.responsibility Everybody #txt
Ce0 f25 81 593 30 30 -49 26 #rect
Ce0 f25 @|StartRequestIcon #fIcon
Ce0 f26 dialogId ch.ivyteam.ivy.project.portal.examples.testdata.EmployeeDetails #txt
Ce0 f26 startMethod start(Integer) #txt
Ce0 f26 requestActionDecl '<Integer employeeId> param;' #txt
Ce0 f26 requestMappingAction 'param.employeeId=in.employeeInfo.id;
' #txt
Ce0 f26 responseMappingAction 'out=in;
' #txt
Ce0 f26 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>EmployeeDetails</name>
    </language>
</elementInfo>
' #txt
Ce0 f26 192 586 112 44 -47 -8 #rect
Ce0 f26 @|UserDialogIcon #fIcon
Ce0 f27 401 593 30 30 0 15 #rect
Ce0 f27 @|EndIcon #fIcon
Ce0 f28 304 608 401 608 #arcP
Ce0 f29 111 608 192 608 #arcP
Ce0 f10 actionTable 'out=in1;
' #txt
Ce0 f10 outLinks "TaskA.ivp" #txt
Ce0 f10 caseData 'case.name=Case for employee Carol Danver
customFields.NUMBER.employeeId=2' #txt
Ce0 f10 taskData 'TaskA.NAM=Task for employee Carol Danver' #txt
Ce0 f10 240 112 32 32 0 16 #rect
Ce0 f10 @|TaskSwitchIcon #fIcon
Ce0 f11 actionTable 'out=in1;
' #txt
Ce0 f11 outLinks "TaskA.ivp" #txt
Ce0 f11 caseData 'case.name=Case for Employee Peter Parker
customFields.NUMBER.employeeId=1' #txt
Ce0 f11 taskData 'TaskA.NAM=Task for Employee Peter Parker' #txt
Ce0 f11 240 48 32 32 0 16 #rect
Ce0 f11 @|TaskSwitchIcon #fIcon
Ce0 f12 outLink createTaskForEmployeeB.ivp #txt
Ce0 f12 inParamDecl '<> param;' #txt
Ce0 f12 requestEnabled true #txt
Ce0 f12 triggerEnabled false #txt
Ce0 f12 callSignature createTaskForEmployeeB() #txt
Ce0 f12 startName 'Create Task For Employee B' #txt
Ce0 f12 caseData businessCase.attach=true #txt
Ce0 f12 showInStartList 1 #txt
Ce0 f12 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>createTaskForEmployeeB.ivp</name>
    </language>
</elementInfo>
' #txt
Ce0 f12 @C|.responsibility Everybody #txt
Ce0 f12 81 113 30 30 -21 17 #rect
Ce0 f12 @|StartRequestIcon #fIcon
Ce0 f13 outLink createTaskForEmployeeA.ivp #txt
Ce0 f13 inParamDecl '<> param;' #txt
Ce0 f13 requestEnabled true #txt
Ce0 f13 triggerEnabled false #txt
Ce0 f13 callSignature createTaskForEmployeeA() #txt
Ce0 f13 startName 'Create Task For Employee A' #txt
Ce0 f13 caseData businessCase.attach=true #txt
Ce0 f13 showInStartList 1 #txt
Ce0 f13 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>createTaskForEmployeeA.ivp</name>
    </language>
</elementInfo>
' #txt
Ce0 f13 @C|.responsibility Everybody #txt
Ce0 f13 81 49 30 30 -21 17 #rect
Ce0 f13 @|StartRequestIcon #fIcon
Ce0 f14 401 49 30 30 0 15 #rect
Ce0 f14 @|EndIcon #fIcon
Ce0 f30 401 113 30 30 0 15 #rect
Ce0 f30 @|EndIcon #fIcon
Ce0 f31 var in1 #txt
Ce0 f31 111 128 240 128 #arcP
Ce0 f32 var in1 #txt
Ce0 f32 111 64 240 64 #arcP
Ce0 f33 expr data #txt
Ce0 f33 outCond ivp=="TaskA.ivp" #txt
Ce0 f33 272 64 401 64 #arcP
Ce0 f34 expr data #txt
Ce0 f34 outCond ivp=="TaskA.ivp" #txt
Ce0 f34 272 128 401 128 #arcP
Ce0 f15 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>CREATE TEST DATA:

Run createTaskForEmployeeA.ivp and createTaskForEmployeeB.ivp processes to create test data.&#13;
These processes will create cases which has customField "employeeId" = 1 and "employeeId" =2.</name>
        <nameStyle>16,5
190,5
</nameStyle>
    </language>
</elementInfo>
' #txt
Ce0 f15 480 58 544 76 -268 -32 #rect
Ce0 f15 @|IBIcon #fIcon
Ce0 f16 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>HOW TO RUN THIS EXAMPLE

   - Run employeeSearchInFrame.ivp to open a list of employees.&#13;
   - Click link "Details in frame" on any row of employee.&#13;
   - It will navigate to the employee details page.&#13;
   - Click "Case Information" button on the top right of the employee details page.&#13;
   - Now you will see that it will show the case details of the case you created  above instead of case details of working task</name>
        <nameStyle>415,5
</nameStyle>
    </language>
</elementInfo>
' #txt
Ce0 f16 480 226 672 124 -332 -56 #rect
Ce0 f16 @|IBIcon #fIcon
Ce0 f17 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>HOW TO RUN THIS EXAMPLE&#13;
&#13;
   - Run employeeSearch.ivp to open a list of employees.&#13;
   - Click link "Details" on any row of employee.&#13;
   - It will navigate to the employee details page.&#13;
   - Click "Case Information" button on the top right of the employee details page.&#13;
   - Now you will see that it will show the case details of the case you created  above instead of case details of working task</name>
    </language>
</elementInfo>
' #txt
Ce0 f17 480 498 672 124 -332 -56 #rect
Ce0 f17 @|IBIcon #fIcon
>Proto Ce0 .type ch.ivyteam.ivy.project.portal.examples.Data #txt
>Proto Ce0 .processKind NORMAL #txt
>Proto Ce0 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <swimlaneLabel>Create test data</swimlaneLabel>
        <swimlaneLabel>Example for IFrameTaskTemplate</swimlaneLabel>
        <swimlaneLabel>Example for TaskTemplate-8</swimlaneLabel>
    </language>
    <swimlaneOrientation>false</swimlaneOrientation>
    <swimlaneSize>192</swimlaneSize>
    <swimlaneSize>256</swimlaneSize>
    <swimlaneSize>224</swimlaneSize>
    <swimlaneColor gradient="false">-6684775</swimlaneColor>
    <swimlaneColor gradient="false">-103</swimlaneColor>
    <swimlaneColor gradient="false">-6684673</swimlaneColor>
    <swimlaneType>LANE</swimlaneType>
    <swimlaneType>LANE</swimlaneType>
    <swimlaneType>LANE</swimlaneType>
    <swimlaneSpaceBefore>0</swimlaneSpaceBefore>
    <swimlaneSpaceBefore>0</swimlaneSpaceBefore>
    <swimlaneSpaceBefore>0</swimlaneSpaceBefore>
</elementInfo>
' #txt
>Proto Ce0 0 0 32 24 18 0 #rect
>Proto Ce0 @|BIcon #fIcon
Ce0 f0 mainOut f4 tail #connect
Ce0 f4 head f3 mainIn #connect
Ce0 f3 mainOut f2 tail #connect
Ce0 f2 head f1 mainIn #connect
Ce0 f20 mainOut f23 tail #connect
Ce0 f23 head f22 mainIn #connect
Ce0 f22 mainOut f24 tail #connect
Ce0 f24 head f21 mainIn #connect
Ce0 f5 mainOut f8 tail #connect
Ce0 f8 head f7 mainIn #connect
Ce0 f7 mainOut f9 tail #connect
Ce0 f9 head f6 mainIn #connect
Ce0 f25 mainOut f29 tail #connect
Ce0 f29 head f26 mainIn #connect
Ce0 f26 mainOut f28 tail #connect
Ce0 f28 head f27 mainIn #connect
Ce0 f13 mainOut f32 tail #connect
Ce0 f32 head f11 in #connect
Ce0 f11 out f33 tail #connect
Ce0 f33 head f14 mainIn #connect
Ce0 f12 mainOut f31 tail #connect
Ce0 f31 head f10 in #connect
Ce0 f10 out f34 tail #connect
Ce0 f34 head f30 mainIn #connect
