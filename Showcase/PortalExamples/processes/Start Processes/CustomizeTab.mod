[Ivy]
1628525DF58D688F 7.5.0 #module
>Proto >Proto Collection #zClass
Cb0 CustomizeTab Big #zClass
Cb0 B #cInfo
Cb0 #process
Cb0 @TextInP .type .type #zField
Cb0 @TextInP .processKind .processKind #zField
Cb0 @AnnotationInP-0n ai ai #zField
Cb0 @MessageFlowInP-0n messageIn messageIn #zField
Cb0 @MessageFlowOutP-0n messageOut messageOut #zField
Cb0 @TextInP .xml .xml #zField
Cb0 @TextInP .responsibility .responsibility #zField
Cb0 @StartRequest f0 '' #zField
Cb0 @EndTask f1 '' #zField
Cb0 @UserDialog f3 '' #zField
Cb0 @PushWFArc f4 '' #zField
Cb0 @TaskSwitchSimple f5 '' #zField
Cb0 @UserDialog f7 '' #zField
Cb0 @PushWFArc f2 '' #zField
Cb0 @PushWFArc f10 '' #zField
Cb0 @Alternative f9 '' #zField
Cb0 @PushWFArc f11 '' #zField
Cb0 @TkArc f6 '' #zField
Cb0 @GridStep f12 '' #zField
Cb0 @PushWFArc f8 '' #zField
Cb0 @PushWFArc f13 '' #zField
Cb0 @InfoButton f14 '' #zField
>Proto Cb0 Cb0 CustomizeTab #zField
Cb0 f0 outLink CustomizeTab.ivp #txt
Cb0 f0 inParamDecl '<> param;' #txt
Cb0 f0 requestEnabled true #txt
Cb0 f0 triggerEnabled false #txt
Cb0 f0 callSignature CustomizeTab() #txt
Cb0 f0 persist false #txt
Cb0 f0 startName 'Customize tab on same level. Check process for more detail' #txt
Cb0 f0 startDescription 'In designer, please create database before run this process, go to start process CustomizeTab for more detail' #txt
Cb0 f0 taskData 'TaskTriggered.EXPRI=2
TaskTriggered.EXROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.PRI=2
TaskTriggered.ROL=Everybody
TaskTriggered.TYPE=0' #txt
Cb0 f0 caseData businessCase.attach=true #txt
Cb0 f0 showInStartList 1 #txt
Cb0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>CustomizeTab.ivp</name>
        <nameStyle>16,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cb0 f0 @C|.responsibility Everybody #txt
Cb0 f0 81 97 30 30 -49 17 #rect
Cb0 f0 @|StartRequestIcon #fIcon
Cb0 f1 937 97 30 30 0 15 #rect
Cb0 f1 @|EndIcon #fIcon
Cb0 f3 dialogId ch.ivyteam.ivy.project.portal.examples.customization.EmployeeInfoPage #txt
Cb0 f3 startMethod start() #txt
Cb0 f3 requestActionDecl '<> param;' #txt
Cb0 f3 responseActionDecl 'ch.ivyteam.ivy.project.portal.examples.Data out;
' #txt
Cb0 f3 responseMappingAction 'out=in;
out.employeeInfo=result.employeeInfo;
out.userAction=result.userAction;
' #txt
Cb0 f3 responseActionCode '	' #txt
Cb0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Employee info</name>
        <nameStyle>13
</nameStyle>
    </language>
</elementInfo>
' #txt
Cb0 f3 192 90 112 44 -39 -8 #rect
Cb0 f3 @|UserDialogIcon #fIcon
Cb0 f4 expr out #txt
Cb0 f4 111 112 192 112 #arcP
Cb0 f5 actionTable 'out=in1;
' #txt
Cb0 f5 outLinks "TaskA.ivp" #txt
Cb0 f5 caseData 'case.name=Employee Information' #txt
Cb0 f5 taskData 'TaskA.EXPRI=2
TaskA.EXROL=Everybody
TaskA.EXTYPE=0
TaskA.NAM=Employee Info
TaskA.PRI=2
TaskA.ROL=Everybody
TaskA.SKIP_TASK_LIST=false
TaskA.TYPE=0' #txt
Cb0 f5 template "" #txt
Cb0 f5 497 97 30 30 0 16 #rect
Cb0 f5 @|TaskSwitchSimpleIcon #fIcon
Cb0 f7 dialogId ch.ivyteam.ivy.project.portal.examples.customization.EmployeeInfoPage #txt
Cb0 f7 startMethod start(ch.ivyteam.ivy.project.portal.examples.Employee) #txt
Cb0 f7 requestActionDecl '<ch.ivyteam.ivy.project.portal.examples.Employee employeeInfo> param;' #txt
Cb0 f7 requestMappingAction 'param.employeeInfo=in.employeeInfo;
' #txt
Cb0 f7 responseActionDecl 'ch.ivyteam.ivy.project.portal.examples.Data out;
' #txt
Cb0 f7 responseMappingAction 'out=in;
out.employeeInfo=result.employeeInfo;
out.userAction=result.userAction;
' #txt
Cb0 f7 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Employee info</name>
        <nameStyle>13
</nameStyle>
    </language>
</elementInfo>
' #txt
Cb0 f7 760 90 112 44 -39 -8 #rect
Cb0 f7 @|UserDialogIcon #fIcon
Cb0 f2 expr out #txt
Cb0 f2 872 112 937 112 #arcP
Cb0 f10 expr out #txt
Cb0 f10 304 112 344 112 #arcP
Cb0 f9 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>action ?</name>
        <nameStyle>8
</nameStyle>
    </language>
</elementInfo>
' #txt
Cb0 f9 344 96 32 32 -21 -35 #rect
Cb0 f9 @|AlternativeIcon #fIcon
Cb0 f11 expr in #txt
Cb0 f11 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>ok</name>
        <nameStyle>2
</nameStyle>
    </language>
</elementInfo>
' #txt
Cb0 f11 360 128 952 127 #arcP
Cb0 f11 1 360 224 #addKink
Cb0 f11 2 952 224 #addKink
Cb0 f11 1 0.45973722150086677 0 13 #arcLabel
Cb0 f6 expr in #txt
Cb0 f6 outCond 'in.userAction == ch.ivyteam.ivy.project.portal.examples.enums.UserAction.SAVE' #txt
Cb0 f6 type ch.ivyteam.ivy.project.portal.examples.Data #txt
Cb0 f6 var in1 #txt
Cb0 f6 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>save</name>
        <nameStyle>4
</nameStyle>
    </language>
</elementInfo>
' #txt
Cb0 f6 376 112 497 112 #arcP
Cb0 f6 0 0.5076923076923077 0 -11 #arcLabel
Cb0 f12 actionTable 'out=in;
' #txt
Cb0 f12 actionCode 'import ch.ivyteam.ivy.project.portal.examples.Employee;
in.employeeInfo = ivy.persistence.PersistenceSample.find(Employee.class, in.employeeInfo.getId()) as Employee;' #txt
Cb0 f12 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Reload employee</name>
    </language>
</elementInfo>
' #txt
Cb0 f12 576 90 112 44 -48 -8 #rect
Cb0 f12 @|StepIcon #fIcon
Cb0 f8 expr out #txt
Cb0 f8 688 112 760 112 #arcP
Cb0 f13 expr data #txt
Cb0 f13 outCond ivp=="TaskA.ivp" #txt
Cb0 f13 527 112 576 112 #arcP
Cb0 f14 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>This example demonstrate
- Using task template with multiple tabs
- Implement Save, OK, Cancel buttons
	OK: finish task
	Save: store information and close
	Cancel: back to home
- Important: go to persistence to create database first
	Select employee in persistence unit, then generate schema</name>
        <nameStyle>291
</nameStyle>
    </language>
</elementInfo>
' #txt
Cb0 f14 32 258 416 156 -198 -72 #rect
Cb0 f14 @|IBIcon #fIcon
>Proto Cb0 .type ch.ivyteam.ivy.project.portal.examples.Data #txt
>Proto Cb0 .processKind NORMAL #txt
>Proto Cb0 0 0 32 24 18 0 #rect
>Proto Cb0 @|BIcon #fIcon
Cb0 f0 mainOut f4 tail #connect
Cb0 f4 head f3 mainIn #connect
Cb0 f3 mainOut f10 tail #connect
Cb0 f10 head f9 in #connect
Cb0 f9 out f6 tail #connect
Cb0 f6 head f5 in #connect
Cb0 f9 out f11 tail #connect
Cb0 f11 head f1 mainIn #connect
Cb0 f7 mainOut f2 tail #connect
Cb0 f2 head f1 mainIn #connect
Cb0 f12 mainOut f8 tail #connect
Cb0 f8 head f7 mainIn #connect
Cb0 f5 out f13 tail #connect
Cb0 f13 head f12 mainIn #connect
