[Ivy]
15C7B30FB93C827E 3.20 #module
>Proto >Proto Collection #zClass
Ae0 ApproveLeave Big #zClass
Ae0 B #cInfo
Ae0 #process
Ae0 @TextInP .resExport .resExport #zField
Ae0 @TextInP .type .type #zField
Ae0 @TextInP .processKind .processKind #zField
Ae0 @AnnotationInP-0n ai ai #zField
Ae0 @MessageFlowInP-0n messageIn messageIn #zField
Ae0 @MessageFlowOutP-0n messageOut messageOut #zField
Ae0 @TextInP .xml .xml #zField
Ae0 @TextInP .responsibility .responsibility #zField
Ae0 @StartRequest f0 '' #zField
Ae0 @EndTask f1 '' #zField
Ae0 @RichDialog f3 '' #zField
Ae0 @PushWFArc f4 '' #zField
Ae0 @PushWFArc f2 '' #zField
Ae0 @StartRequest f5 '' #zField
Ae0 @Trigger f6 '' #zField
Ae0 @PushWFArc f7 '' #zField
Ae0 @StartRequest f8 '' #zField
Ae0 @EndTask f9 '' #zField
Ae0 @EndTask f11 '' #zField
Ae0 @StartRequest f13 '' #zField
Ae0 @EndTask f14 '' #zField
Ae0 @Trigger f16 '' #zField
Ae0 @PushWFArc f17 '' #zField
Ae0 @PushWFArc f12 '' #zField
Ae0 @TaskSwitchSimple f15 '' #zField
Ae0 @TkArc f18 '' #zField
Ae0 @PushWFArc f19 '' #zField
Ae0 @PushWFArc f10 '' #zField
Ae0 @CallSub f22 '' #zField
Ae0 @TaskSwitch f21 '' #zField
Ae0 @PushWFArc f20 '' #zField
Ae0 @PushWFArc f23 '' #zField
Ae0 @TkArc f24 '' #zField
>Proto Ae0 Ae0 ApproveLeave #zField
Ae0 f0 outLink start.ivp #txt
Ae0 f0 type internaltest.Data #txt
Ae0 f0 inParamDecl '<> param;' #txt
Ae0 f0 actionDecl 'internaltest.Data out;
' #txt
Ae0 f0 guid 15C7B30FB98ED618 #txt
Ae0 f0 requestEnabled true #txt
Ae0 f0 triggerEnabled false #txt
Ae0 f0 callSignature start() #txt
Ae0 f0 persist false #txt
Ae0 f0 taskData 'TaskTriggered.ROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.EXPRI=2
TaskTriggered.TYPE=0
TaskTriggered.PRI=2
TaskTriggered.EXROL=Everybody
TaskTriggered.NAM=Approve Leave Request' #txt
Ae0 f0 caseData businessCase.attach=true #txt
Ae0 f0 showInStartList 1 #txt
Ae0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start.ivp</name>
        <nameStyle>9,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ae0 f0 @C|.responsibility Everybody #txt
Ae0 f0 81 49 30 30 -21 17 #rect
Ae0 f0 @|StartRequestIcon #fIcon
Ae0 f1 type internaltest.Data #txt
Ae0 f1 337 49 30 30 0 15 #rect
Ae0 f1 @|EndIcon #fIcon
Ae0 f3 targetWindow NEW #txt
Ae0 f3 targetDisplay TOP #txt
Ae0 f3 richDialogId internaltest.ui.approveLeave #txt
Ae0 f3 startMethod start(String,Date,Date,Boolean,String,internalPortal.ProcessStatus) #txt
Ae0 f3 type internaltest.Data #txt
Ae0 f3 requestActionDecl '<String Mitarbeiter, Date Von, Date Bis, Boolean beantragt, String Vertretung, internalPortal.ProcessStatus processStatus> param;' #txt
Ae0 f3 responseActionDecl 'internaltest.Data out;
' #txt
Ae0 f3 responseMappingAction 'out=in;
' #txt
Ae0 f3 isAsynch false #txt
Ae0 f3 isInnerRd false #txt
Ae0 f3 userContext '* ' #txt
Ae0 f3 168 42 112 44 0 -8 #rect
Ae0 f3 @|RichDialogIcon #fIcon
Ae0 f4 expr out #txt
Ae0 f4 111 64 168 64 #arcP
Ae0 f2 expr out #txt
Ae0 f2 280 64 337 64 #arcP
Ae0 f5 outLink repairComputer.ivp #txt
Ae0 f5 type internaltest.Data #txt
Ae0 f5 inParamDecl '<> param;' #txt
Ae0 f5 actionDecl 'internaltest.Data out;
' #txt
Ae0 f5 guid 015FA406DB74BB32 #txt
Ae0 f5 requestEnabled true #txt
Ae0 f5 triggerEnabled false #txt
Ae0 f5 callSignature repairComputer() #txt
Ae0 f5 persist false #txt
Ae0 f5 startName 'Repair Computer' #txt
Ae0 f5 taskData 'TaskTriggered.ROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.EXPRI=2
TaskTriggered.TYPE=0
TaskTriggered.PRI=2
TaskTriggered.EXROL=Everybody' #txt
Ae0 f5 caseData 'case.name=Repair Computer
businessCase.attach=true' #txt
Ae0 f5 showInStartList 1 #txt
Ae0 f5 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>repairComputer.ivp</name>
        <nameStyle>18,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ae0 f5 @C|.responsibility Everybody #txt
Ae0 f5 81 177 30 30 -52 17 #rect
Ae0 f5 @|StartRequestIcon #fIcon
Ae0 f6 type internaltest.Data #txt
Ae0 f6 processCall 'Business Processes/testCaseMaps/testProcesses/ApproveLeave:fixHardware()' #txt
Ae0 f6 doCall true #txt
Ae0 f6 requestActionDecl '<> param;
' #txt
Ae0 f6 responseActionDecl 'internaltest.Data out;
' #txt
Ae0 f6 responseMappingAction 'out=in;
' #txt
Ae0 f6 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>ApproveLeave</name>
    </language>
</elementInfo>
' #txt
Ae0 f6 192 170 112 44 -38 -8 #rect
Ae0 f6 @|TriggerIcon #fIcon
Ae0 f7 expr out #txt
Ae0 f7 111 192 192 192 #arcP
Ae0 f8 outLink fixHardware.ivp #txt
Ae0 f8 type internaltest.Data #txt
Ae0 f8 inParamDecl '<> param;' #txt
Ae0 f8 actionDecl 'internaltest.Data out;
' #txt
Ae0 f8 guid 15FA4081B8873731 #txt
Ae0 f8 requestEnabled false #txt
Ae0 f8 triggerEnabled true #txt
Ae0 f8 callSignature fixHardware() #txt
Ae0 f8 persist false #txt
Ae0 f8 startName 'Fix Hardware' #txt
Ae0 f8 taskData 'TaskTriggered.ROL=SYSTEM
TaskTriggered.EXTYPE=0
TaskTriggered.EXPRI=2
TaskTriggered.TYPE=0
TaskTriggered.PRI=2
TaskTriggered.EXROL=Everybody
TaskTriggered.NAM=Fix Hardware' #txt
Ae0 f8 caseData 'case.name=Fix Hardware
businessCase.attach=true' #txt
Ae0 f8 showInStartList 1 #txt
Ae0 f8 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Fix Hardware</name>
        <nameStyle>12
</nameStyle>
    </language>
</elementInfo>
' #txt
Ae0 f8 @C|.responsibility Everybody #txt
Ae0 f8 81 337 30 30 -36 17 #rect
Ae0 f8 @|StartRequestIcon #fIcon
Ae0 f9 type internaltest.Data #txt
Ae0 f9 625 177 30 30 0 15 #rect
Ae0 f9 @|EndIcon #fIcon
Ae0 f11 type internaltest.Data #txt
Ae0 f11 401 337 30 30 0 15 #rect
Ae0 f11 @|EndIcon #fIcon
Ae0 f13 outLink installOperationSystem.ivp #txt
Ae0 f13 type internaltest.Data #txt
Ae0 f13 inParamDecl '<> param;' #txt
Ae0 f13 actionDecl 'internaltest.Data out;
' #txt
Ae0 f13 guid 15FA4121BA547739 #txt
Ae0 f13 requestEnabled false #txt
Ae0 f13 triggerEnabled true #txt
Ae0 f13 callSignature installOperationSystem() #txt
Ae0 f13 persist false #txt
Ae0 f13 startName 'Install Operation System' #txt
Ae0 f13 taskData 'TaskTriggered.ROL=SYSTEM
TaskTriggered.EXTYPE=0
TaskTriggered.EXPRI=2
TaskTriggered.TYPE=0
TaskTriggered.PRI=2
TaskTriggered.EXROL=Everybody' #txt
Ae0 f13 caseData 'case.name=Install Operation System
businessCase.attach=true' #txt
Ae0 f13 showInStartList 1 #txt
Ae0 f13 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Install Operation System</name>
        <nameStyle>24
</nameStyle>
    </language>
</elementInfo>
' #txt
Ae0 f13 @C|.responsibility Everybody #txt
Ae0 f13 81 433 30 30 -67 17 #rect
Ae0 f13 @|StartRequestIcon #fIcon
Ae0 f14 type internaltest.Data #txt
Ae0 f14 401 433 30 30 0 15 #rect
Ae0 f14 @|EndIcon #fIcon
Ae0 f16 type internaltest.Data #txt
Ae0 f16 processCall 'Business Processes/testCaseMaps/testProcesses/ApproveLeave:installOperationSystem()' #txt
Ae0 f16 doCall true #txt
Ae0 f16 requestActionDecl '<> param;
' #txt
Ae0 f16 responseActionDecl 'internaltest.Data out;
' #txt
Ae0 f16 responseMappingAction 'out=in;
' #txt
Ae0 f16 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>ApproveLeave</name>
    </language>
</elementInfo>
' #txt
Ae0 f16 192 330 112 44 -38 -8 #rect
Ae0 f16 @|TriggerIcon #fIcon
Ae0 f17 expr out #txt
Ae0 f17 111 352 192 352 #arcP
Ae0 f12 expr out #txt
Ae0 f12 304 352 401 352 #arcP
Ae0 f15 actionDecl 'internaltest.Data out;
' #txt
Ae0 f15 actionTable 'out=in1;
' #txt
Ae0 f15 outTypes "internaltest.Data" #txt
Ae0 f15 outLinks "TaskA.ivp" #txt
Ae0 f15 taskData 'TaskA.EXPRI=2
TaskA.EXROL=Everybody
TaskA.EXTYPE=0
TaskA.NAM=Install OperationSystem
TaskA.PRI=2
TaskA.ROL=Everybody
TaskA.SKIP_TASK_LIST=false
TaskA.TYPE=0' #txt
Ae0 f15 type internaltest.Data #txt
Ae0 f15 template "" #txt
Ae0 f15 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Install</name>
        <nameStyle>7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ae0 f15 209 433 30 30 -16 17 #rect
Ae0 f15 @|TaskSwitchSimpleIcon #fIcon
Ae0 f18 expr out #txt
Ae0 f18 type internaltest.Data #txt
Ae0 f18 var in1 #txt
Ae0 f18 111 448 209 448 #arcP
Ae0 f19 expr data #txt
Ae0 f19 outCond ivp=="TaskA.ivp" #txt
Ae0 f19 239 448 401 448 #arcP
Ae0 f10 expr out #txt
Ae0 f10 560 192 625 192 #arcP
Ae0 f22 type internaltest.Data #txt
Ae0 f22 processCall 'Functional Processes/HideSystemCase:call(Long,Boolean)' #txt
Ae0 f22 doCall true #txt
Ae0 f22 requestActionDecl '<java.lang.Long serverId,java.lang.Boolean hideBusinessCase> param;
' #txt
Ae0 f22 requestMappingAction 'param.serverId=ch.ivy.addon.portalkit.util.SecurityServiceUtils.getServerIdFromSession();
param.hideBusinessCase=true;
' #txt
Ae0 f22 responseActionDecl 'internaltest.Data out;
' #txt
Ae0 f22 responseMappingAction 'out=in;
' #txt
Ae0 f22 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>HideSystemCase</name>
        <nameStyle>14,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ae0 f22 448 170 112 44 -48 -8 #rect
Ae0 f22 @|CallSubIcon #fIcon
Ae0 f21 actionDecl 'internaltest.Data out;
' #txt
Ae0 f21 actionTable 'out=in1;
' #txt
Ae0 f21 outTypes "internaltest.Data","internaltest.Data" #txt
Ae0 f21 outLinks "TaskA.ivp","TaskB.ivp" #txt
Ae0 f21 taskData 'TaskA.DESC=Report and hide case
TaskA.EXPRI=2
TaskA.EXROL=Everybody
TaskA.EXTYPE=0
TaskA.NAM=Report and hide case
TaskA.PRI=2
TaskA.ROL=AXONIVY_PORTAL_ADMIN
TaskA.SKIP_TASK_LIST=false
TaskA.TYPE=0
TaskB.DESC=Report but don''t hide case
TaskB.EXPRI=2
TaskB.EXROL=Everybody
TaskB.EXTYPE=0
TaskB.NAM=Report
TaskB.PRI=2
TaskB.ROL=CREATOR
TaskB.SKIP_TASK_LIST=false
TaskB.TYPE=0' #txt
Ae0 f21 type internaltest.Data #txt
Ae0 f21 template "" #txt
Ae0 f21 368 176 32 32 0 16 #rect
Ae0 f21 @|TaskSwitchIcon #fIcon
Ae0 f20 expr data #txt
Ae0 f20 outCond ivp=="TaskA.ivp" #txt
Ae0 f20 400 192 448 192 #arcP
Ae0 f23 expr data #txt
Ae0 f23 outCond ivp=="TaskB.ivp" #txt
Ae0 f23 384 208 640 207 #arcP
Ae0 f23 1 384 256 #addKink
Ae0 f23 2 640 256 #addKink
Ae0 f23 1 0.5013020833333334 0 0 #arcLabel
Ae0 f24 expr out #txt
Ae0 f24 type internaltest.Data #txt
Ae0 f24 var in1 #txt
Ae0 f24 304 192 368 192 #arcP
>Proto Ae0 .type internaltest.Data #txt
>Proto Ae0 .processKind NORMAL #txt
>Proto Ae0 0 0 32 24 18 0 #rect
>Proto Ae0 @|BIcon #fIcon
Ae0 f0 mainOut f4 tail #connect
Ae0 f4 head f3 mainIn #connect
Ae0 f3 mainOut f2 tail #connect
Ae0 f2 head f1 mainIn #connect
Ae0 f5 mainOut f7 tail #connect
Ae0 f7 head f6 mainIn #connect
Ae0 f8 mainOut f17 tail #connect
Ae0 f17 head f16 mainIn #connect
Ae0 f16 mainOut f12 tail #connect
Ae0 f12 head f11 mainIn #connect
Ae0 f13 mainOut f18 tail #connect
Ae0 f18 head f15 in #connect
Ae0 f15 out f19 tail #connect
Ae0 f19 head f14 mainIn #connect
Ae0 f22 mainOut f10 tail #connect
Ae0 f10 head f9 mainIn #connect
Ae0 f21 out f20 tail #connect
Ae0 f20 head f22 mainIn #connect
Ae0 f21 out f23 tail #connect
Ae0 f23 head f9 mainIn #connect
Ae0 f6 mainOut f24 tail #connect
Ae0 f24 head f21 in #connect
