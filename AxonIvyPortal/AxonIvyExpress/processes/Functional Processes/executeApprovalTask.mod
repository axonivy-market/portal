[Ivy]
162FF9CBEADCEBED 3.23 #module
>Proto >Proto Collection #zClass
ek0 executeApprovalTask Big #zClass
ek0 B #cInfo
ek0 #process
ek0 @TextInP .resExport .resExport #zField
ek0 @TextInP .type .type #zField
ek0 @TextInP .processKind .processKind #zField
ek0 @AnnotationInP-0n ai ai #zField
ek0 @MessageFlowInP-0n messageIn messageIn #zField
ek0 @MessageFlowOutP-0n messageOut messageOut #zField
ek0 @TextInP .xml .xml #zField
ek0 @TextInP .responsibility .responsibility #zField
ek0 @StartSub f0 '' #zField
ek0 @EndSub f1 '' #zField
ek0 @GridStep f3 '' #zField
ek0 @PushWFArc f4 '' #zField
ek0 @GridStep f5 '' #zField
ek0 @PushWFArc f6 '' #zField
ek0 @TaskSwitch f7 '' #zField
ek0 @TkArc f8 '' #zField
ek0 @GridStep f9 '' #zField
ek0 @PushWFArc f10 '' #zField
ek0 @Alternative f11 '' #zField
ek0 @PushWFArc f12 '' #zField
ek0 @PushWFArc f13 '' #zField
ek0 @RichDialog f14 '' #zField
ek0 @Alternative f16 '' #zField
ek0 @PushWFArc f17 '' #zField
ek0 @TaskSwitchSimple f18 '' #zField
ek0 @TkArc f19 '' #zField
ek0 @Alternative f22 '' #zField
ek0 @PushWFArc f23 '' #zField
ek0 @PushWFArc f15 '' #zField
ek0 @TaskSwitchSimple f20 '' #zField
ek0 @TkArc f21 '' #zField
ek0 @PushWFArc f24 '' #zField
ek0 @TaskSwitch f25 '' #zField
ek0 @Alternative f29 '' #zField
ek0 @GridStep f37 '' #zField
ek0 @Alternative f31 '' #zField
ek0 @PushWFArc f34 '' #zField
ek0 @PushWFArc f33 '' #zField
ek0 @PushWFArc f27 '' #zField
ek0 @TkArc f30 '' #zField
ek0 @GridStep f32 '' #zField
ek0 @PushWFArc f38 '' #zField
ek0 @PushWFArc f28 '' #zField
ek0 @PushWFArc f2 '' #zField
ek0 @TkArc f26 '' #zField
>Proto ek0 ek0 executeApprovalTask #zField
ek0 f0 inParamDecl '<java.util.List<java.lang.String> steps,java.lang.Integer actualStepIndex,java.lang.String userTaskResponsibleName,gawfs.TaskDef approvalTask,gawfs.TaskDef userTask> param;' #txt
ek0 f0 inParamTable 'out.actualStepIndex=param.actualStepIndex;
out.approvalTask=param.approvalTask;
out.steps=param.steps;
out.userTask=param.userTask;
out.userTaskResponsibleName=param.userTaskResponsibleName;
' #txt
ek0 f0 outParamDecl '<java.lang.Boolean rejected,java.util.List<gawfs.ApprovalTaskResult> approvalResult> result;
' #txt
ek0 f0 outParamTable 'result.rejected=in.hasRejectedTask;
result.approvalResult=in.approvalResultList;
' #txt
ek0 f0 actionDecl 'gawfs.executeApprovalTaskData out;
' #txt
ek0 f0 callSignature call(List<String>,Integer,String,gawfs.TaskDef,gawfs.TaskDef) #txt
ek0 f0 type gawfs.executeApprovalTaskData #txt
ek0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>call(TaskDef,TaskDef)</name>
        <nameStyle>21,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
ek0 f0 337 49 30 30 17 9 #rect
ek0 f0 @|StartSubIcon #fIcon
ek0 f1 type gawfs.executeApprovalTaskData #txt
ek0 f1 337 1193 30 30 0 15 #rect
ek0 f1 @|EndSubIcon #fIcon
ek0 f3 actionDecl 'gawfs.executeApprovalTaskData out;
' #txt
ek0 f3 actionTable 'out=in;
' #txt
ek0 f3 actionCode 'in.approvalTask.counter = in.approvalTask.responsibles.size();
in.hasRejectedTask = false;' #txt
ek0 f3 type gawfs.executeApprovalTaskData #txt
ek0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Initialize</name>
    </language>
</elementInfo>
' #txt
ek0 f3 296 128 112 44 -22 -8 #rect
ek0 f3 @|StepIcon #fIcon
ek0 f4 expr out #txt
ek0 f4 352 79 352 128 #arcP
ek0 f5 actionDecl 'gawfs.executeApprovalTaskData out;
' #txt
ek0 f5 actionTable 'out=in;
' #txt
ek0 f5 actionCode 'if (!in.approvalTask.responsibles.isEmpty()) {
	in.approverName = in.approvalTask.responsibles.removeGet(0);
}' #txt
ek0 f5 type gawfs.executeApprovalTaskData #txt
ek0 f5 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Get next approver name</name>
    </language>
</elementInfo>
' #txt
ek0 f5 280 224 144 44 -65 -8 #rect
ek0 f5 @|StepIcon #fIcon
ek0 f6 expr out #txt
ek0 f6 352 172 352 224 #arcP
ek0 f7 actionDecl 'gawfs.executeApprovalTaskData out;
' #txt
ek0 f7 actionTable 'out=in1;
' #txt
ek0 f7 outTypes "gawfs.executeApprovalTaskData","gawfs.executeApprovalTaskData" #txt
ek0 f7 outLinks "TaskA.ivp","TaskB.ivp" #txt
ek0 f7 taskData 'TaskA.DESC=SYSTEM\: Create task
TaskA.EXPRI=2
TaskA.EXROL=Everybody
TaskA.EXTYPE=0
TaskA.NAM=SYSTEM\: Create task
TaskA.PRI=2
TaskA.ROL=SYSTEM
TaskA.SKIP_TASK_LIST=false
TaskA.TYPE=0
TaskB.DESC=SYSTEM\: Split
TaskB.EXPRI=2
TaskB.EXROL=Everybody
TaskB.EXTYPE=0
TaskB.NAM=SYSTEM\: Split
TaskB.PRI=2
TaskB.ROL=SYSTEM
TaskB.SKIP_TASK_LIST=true
TaskB.TYPE=0' #txt
ek0 f7 type gawfs.executeApprovalTaskData #txt
ek0 f7 template "" #txt
ek0 f7 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>SYSTEM Split</name>
        <nameStyle>12
</nameStyle>
    </language>
</elementInfo>
' #txt
ek0 f7 336 320 32 32 19 -1 #rect
ek0 f7 @|TaskSwitchIcon #fIcon
ek0 f8 expr out #txt
ek0 f8 type gawfs.executeApprovalTaskData #txt
ek0 f8 var in1 #txt
ek0 f8 352 268 352 320 #arcP
ek0 f9 actionDecl 'gawfs.executeApprovalTaskData out;
' #txt
ek0 f9 actionTable 'out=in;
' #txt
ek0 f9 actionCode 'in.approver = ivy.session.getSecurityContext().findSecurityMember(in.approverName);' #txt
ek0 f9 type gawfs.executeApprovalTaskData #txt
ek0 f9 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Get approver</name>
    </language>
</elementInfo>
' #txt
ek0 f9 296 416 112 44 -35 -8 #rect
ek0 f9 @|StepIcon #fIcon
ek0 f10 expr data #txt
ek0 f10 outCond ivp=="TaskA.ivp" #txt
ek0 f10 352 352 352 416 #arcP
ek0 f11 type gawfs.executeApprovalTaskData #txt
ek0 f11 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Has more approver?</name>
        <nameStyle>18
</nameStyle>
    </language>
</elementInfo>
' #txt
ek0 f11 48 320 32 32 9 -29 #rect
ek0 f11 @|AlternativeIcon #fIcon
ek0 f12 expr data #txt
ek0 f12 outCond ivp=="TaskB.ivp" #txt
ek0 f12 336 336 80 336 #arcP
ek0 f13 expr in #txt
ek0 f13 outCond !in.approvalTask.responsibles.isEmpty() #txt
ek0 f13 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>yes</name>
        <nameStyle>3
</nameStyle>
    </language>
</elementInfo>
' #txt
ek0 f13 64 320 280 246 #arcP
ek0 f13 1 64 246 #addKink
ek0 f13 0 0.581081081081081 15 -1 #arcLabel
ek0 f14 targetWindow NEW #txt
ek0 f14 targetDisplay TOP #txt
ek0 f14 richDialogId ch.ivy.gawfs.workflowExecution.ApprovalForm #txt
ek0 f14 startMethod start(gawfs.TaskDef,String,java.util.List<String>,java.lang.Integer) #txt
ek0 f14 type gawfs.executeApprovalTaskData #txt
ek0 f14 requestActionDecl '<gawfs.TaskDef userTask, String applicantName, java.util.List<String> steps, java.lang.Integer actualStepIndex> param;' #txt
ek0 f14 requestMappingAction 'param.userTask=in.userTask;
param.applicantName=in.userTaskResponsibleName;
param.steps=in.steps;
param.actualStepIndex=in.actualStepIndex;
' #txt
ek0 f14 responseActionDecl 'gawfs.executeApprovalTaskData out;
' #txt
ek0 f14 responseMappingAction 'out=in;
out.approvalResult=result.approvalResult;
' #txt
ek0 f14 isAsynch false #txt
ek0 f14 isInnerRd false #txt
ek0 f14 userContext '* ' #txt
ek0 f14 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Approval form</name>
        <nameStyle>13
</nameStyle>
    </language>
</elementInfo>
' #txt
ek0 f14 296 736 112 44 -37 -8 #rect
ek0 f14 @|RichDialogIcon #fIcon
ek0 f16 type gawfs.executeApprovalTaskData #txt
ek0 f16 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Assigned to user?</name>
        <nameStyle>17
</nameStyle>
    </language>
</elementInfo>
' #txt
ek0 f16 336 496 32 32 -50 18 #rect
ek0 f16 @|AlternativeIcon #fIcon
ek0 f17 expr out #txt
ek0 f17 352 460 352 496 #arcP
ek0 f18 actionDecl 'gawfs.executeApprovalTaskData out;
' #txt
ek0 f18 actionTable 'out=in1;
' #txt
ek0 f18 outTypes "gawfs.executeApprovalTaskData" #txt
ek0 f18 outLinks "TaskA.ivp" #txt
ek0 f18 taskData 'TaskA.DESC=<%\=in1.approvalTask.description%>
TaskA.EXP=new Duration(0,0,in1.approvalTask.untilDays,0,0,0)
TaskA.EXPRI=1
TaskA.EXROL=Everybody
TaskA.EXTYPE=0
TaskA.NAM=<%\=in1.approvalTask.subject%>
TaskA.PRI=2
TaskA.ROL=in1.approver.getMemberName()
TaskA.SKIP_TASK_LIST=false
TaskA.TYPE=2' #txt
ek0 f18 type gawfs.executeApprovalTaskData #txt
ek0 f18 template "" #txt
ek0 f18 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>create  task for group</name>
        <nameStyle>22
</nameStyle>
    </language>
</elementInfo>
' #txt
ek0 f18 241 577 30 30 -135 -9 #rect
ek0 f18 @|TaskSwitchSimpleIcon #fIcon
ek0 f19 expr in #txt
ek0 f19 outCond !in.approver.isUser() #txt
ek0 f19 type gawfs.executeApprovalTaskData #txt
ek0 f19 var in1 #txt
ek0 f19 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>no</name>
        <nameStyle>2
</nameStyle>
    </language>
</elementInfo>
' #txt
ek0 f19 336 512 256 577 #arcP
ek0 f19 1 256 512 #addKink
ek0 f19 0 0.4375 0 -11 #arcLabel
ek0 f22 type gawfs.executeApprovalTaskData #txt
ek0 f22 336 656 32 32 0 16 #rect
ek0 f22 @|AlternativeIcon #fIcon
ek0 f23 expr data #txt
ek0 f23 outCond ivp=="TaskA.ivp" #txt
ek0 f23 256 607 336 672 #arcP
ek0 f23 1 256 672 #addKink
ek0 f23 1 0.13217932650050215 0 0 #arcLabel
ek0 f15 expr in #txt
ek0 f15 352 688 352 736 #arcP
ek0 f20 actionDecl 'gawfs.executeApprovalTaskData out;
' #txt
ek0 f20 actionTable 'out=in1;
' #txt
ek0 f20 outTypes "gawfs.executeApprovalTaskData" #txt
ek0 f20 outLinks "TaskA.ivp" #txt
ek0 f20 taskData 'TaskA.DESC=<%\=in1.approvalTask.description%>
TaskA.EXP=new Duration(0,0,in1.approvalTask.untilDays,0,0,0)
TaskA.EXPRI=1
TaskA.EXROL=Everybody
TaskA.EXTYPE=0
TaskA.NAM=<%\=in1.approvalTask.subject%>
TaskA.PRI=2
TaskA.ROL=in1.approver.getMemberName().substring(1)
TaskA.SKIP_TASK_LIST=false
TaskA.TYPE=3' #txt
ek0 f20 type gawfs.executeApprovalTaskData #txt
ek0 f20 template "" #txt
ek0 f20 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>create task for user</name>
        <nameStyle>20
</nameStyle>
    </language>
</elementInfo>
' #txt
ek0 f20 433 577 30 30 20 -10 #rect
ek0 f20 @|TaskSwitchSimpleIcon #fIcon
ek0 f21 expr in #txt
ek0 f21 type gawfs.executeApprovalTaskData #txt
ek0 f21 var in1 #txt
ek0 f21 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>yes</name>
        <nameStyle>3
</nameStyle>
    </language>
</elementInfo>
' #txt
ek0 f21 368 512 448 577 #arcP
ek0 f21 1 448 512 #addKink
ek0 f21 0 0.55 0 -9 #arcLabel
ek0 f24 expr data #txt
ek0 f24 outCond ivp=="TaskA.ivp" #txt
ek0 f24 448 607 368 672 #arcP
ek0 f24 1 448 672 #addKink
ek0 f24 0 0.9138369937494684 0 0 #arcLabel
ek0 f25 actionDecl 'gawfs.executeApprovalTaskData out;
' #txt
ek0 f25 actionTable 'out=in2;
out.approvalResult=in1.approvalResult;
' #txt
ek0 f25 outTypes "gawfs.executeApprovalTaskData" #txt
ek0 f25 outLinks "TaskA.ivp" #txt
ek0 f25 taskData 'TaskA.DESC=SYSTEM\: Join
TaskA.EXPRI=2
TaskA.EXROL=Everybody
TaskA.EXTYPE=0
TaskA.NAM=SYSTEM\: Join
TaskA.PRI=2
TaskA.ROL=SYSTEM
TaskA.SKIP_TASK_LIST=false
TaskA.TYPE=0' #txt
ek0 f25 type gawfs.executeApprovalTaskData #txt
ek0 f25 template "" #txt
ek0 f25 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>SYSTEM Join</name>
        <nameStyle>11
</nameStyle>
    </language>
</elementInfo>
' #txt
ek0 f25 336 832 32 32 15 2 #rect
ek0 f25 @|TaskSwitchIcon #fIcon
ek0 f29 type gawfs.executeApprovalTaskData #txt
ek0 f29 336 1120 32 32 0 16 #rect
ek0 f29 @|AlternativeIcon #fIcon
ek0 f37 actionDecl 'gawfs.executeApprovalTaskData out;
' #txt
ek0 f37 actionTable 'out=in;
out.approvalTask.counter=in.approvalTask.counter - 1;
' #txt
ek0 f37 type gawfs.executeApprovalTaskData #txt
ek0 f37 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>counter --</name>
    </language>
</elementInfo>
' #txt
ek0 f37 296 1024 112 44 -26 -8 #rect
ek0 f37 @|StepIcon #fIcon
ek0 f31 type gawfs.executeApprovalTaskData #txt
ek0 f31 176 832 32 32 0 16 #rect
ek0 f31 @|AlternativeIcon #fIcon
ek0 f34 expr in #txt
ek0 f34 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>no</name>
        <nameStyle>2
</nameStyle>
    </language>
</elementInfo>
' #txt
ek0 f34 64 352 176 848 #arcP
ek0 f34 1 64 848 #addKink
ek0 f34 0 0.07056451612903226 13 0 #arcLabel
ek0 f33 expr in #txt
ek0 f33 outCond 'in.approvalTask.counter > 0' #txt
ek0 f33 336 1136 192 864 #arcP
ek0 f33 1 192 1136 #addKink
ek0 f33 1 0.04750565614394758 0 0 #arcLabel
ek0 f27 expr out #txt
ek0 f27 352 1068 352 1120 #arcP
ek0 f30 expr in #txt
ek0 f30 type gawfs.executeApprovalTaskData #txt
ek0 f30 var in2 #txt
ek0 f30 208 848 336 848 #arcP
ek0 f32 actionDecl 'gawfs.executeApprovalTaskData out;
' #txt
ek0 f32 actionTable 'out=in;
' #txt
ek0 f32 actionCode 'in.approvalResultList.add(in.approvalResult);

if (!in.approvalResult.isApproved) {
	in.hasRejectedTask = true;
}' #txt
ek0 f32 type gawfs.executeApprovalTaskData #txt
ek0 f32 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Get approval result</name>
    </language>
</elementInfo>
' #txt
ek0 f32 297 930 112 44 -51 -8 #rect
ek0 f32 @|StepIcon #fIcon
ek0 f38 expr data #txt
ek0 f38 outCond ivp=="TaskA.ivp" #txt
ek0 f38 352 864 353 930 #arcP
ek0 f28 expr out #txt
ek0 f28 353 974 352 1024 #arcP
ek0 f2 expr in #txt
ek0 f2 352 1152 352 1193 #arcP
ek0 f26 expr out #txt
ek0 f26 type gawfs.executeApprovalTaskData #txt
ek0 f26 var in1 #txt
ek0 f26 352 780 352 832 #arcP
>Proto ek0 .type gawfs.executeApprovalTaskData #txt
>Proto ek0 .processKind CALLABLE_SUB #txt
>Proto ek0 0 0 32 24 18 0 #rect
>Proto ek0 @|BIcon #fIcon
ek0 f0 mainOut f4 tail #connect
ek0 f4 head f3 mainIn #connect
ek0 f3 mainOut f6 tail #connect
ek0 f6 head f5 mainIn #connect
ek0 f5 mainOut f8 tail #connect
ek0 f8 head f7 in #connect
ek0 f7 out f10 tail #connect
ek0 f10 head f9 mainIn #connect
ek0 f7 out f12 tail #connect
ek0 f12 head f11 in #connect
ek0 f11 out f13 tail #connect
ek0 f13 head f5 mainIn #connect
ek0 f9 mainOut f17 tail #connect
ek0 f17 head f16 in #connect
ek0 f16 out f19 tail #connect
ek0 f19 head f18 in #connect
ek0 f18 out f23 tail #connect
ek0 f23 head f22 in #connect
ek0 f22 out f15 tail #connect
ek0 f15 head f14 mainIn #connect
ek0 f16 out f21 tail #connect
ek0 f21 head f20 in #connect
ek0 f20 out f24 tail #connect
ek0 f24 head f22 in #connect
ek0 f11 out f34 tail #connect
ek0 f34 head f31 in #connect
ek0 f29 out f33 tail #connect
ek0 f33 head f31 in #connect
ek0 f37 mainOut f27 tail #connect
ek0 f27 head f29 in #connect
ek0 f31 out f30 tail #connect
ek0 f30 head f25 in #connect
ek0 f25 out f38 tail #connect
ek0 f38 head f32 mainIn #connect
ek0 f32 mainOut f28 tail #connect
ek0 f28 head f37 mainIn #connect
ek0 f29 out f2 tail #connect
ek0 f2 head f1 mainIn #connect
ek0 f14 mainOut f26 tail #connect
ek0 f26 head f25 in #connect
