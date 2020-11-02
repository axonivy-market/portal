[Ivy]
171E2BB0DB49C362 9.2.0 #module
>Proto >Proto Collection #zClass
Rd0 RoleInvolved Big #zClass
Rd0 B #cInfo
Rd0 #process
Rd0 @TextInP .type .type #zField
Rd0 @TextInP .processKind .processKind #zField
Rd0 @TextInP .xml .xml #zField
Rd0 @TextInP .responsibility .responsibility #zField
Rd0 @StartRequest f0 '' #zField
Rd0 @EndTask f1 '' #zField
Rd0 @TaskSwitch f3 '' #zField
Rd0 @TkArc f4 '' #zField
Rd0 @PushWFArc f7 '' #zField
>Proto Rd0 Rd0 RoleInvolved #zField
Rd0 f0 outLink roleInvolved.ivp #txt
Rd0 f0 inParamDecl '<> param;' #txt
Rd0 f0 requestEnabled true #txt
Rd0 f0 triggerEnabled false #txt
Rd0 f0 callSignature roleInvolved() #txt
Rd0 f0 startName 'Test Process: role involved' #txt
Rd0 f0 taskData 'TaskTriggered.NAM=Test Process\: role involved' #txt
Rd0 f0 caseData 'businessCase.attach=true
case.name=Test Process\: role involved' #txt
Rd0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>roleInvolved.ivp</name>
    </language>
</elementInfo>
' #txt
Rd0 f0 @C|.responsibility Everybody #txt
Rd0 f0 81 49 30 30 -37 17 #rect
Rd0 f0 @|StartRequestIcon #fIcon
Rd0 f1 273 49 30 30 0 15 #rect
Rd0 f1 @|EndIcon #fIcon
Rd0 f3 actionTable 'out=in1;
' #txt
Rd0 f3 outLinks "TaskA.ivp" #txt
Rd0 f3 taskData 'TaskA.NAM=Task for role involved
TaskA.ROL=TestRoleInvolved
TaskA.TYPE=0' #txt
Rd0 f3 176 48 32 32 0 16 #rect
Rd0 f3 @|TaskSwitchIcon #fIcon
Rd0 f4 var in1 #txt
Rd0 f4 111 64 176 64 #arcP
Rd0 f7 expr data #txt
Rd0 f7 outCond ivp=="TaskA.ivp" #txt
Rd0 f7 208 64 273 64 #arcP
>Proto Rd0 .type internaltest.Data #txt
>Proto Rd0 .processKind NORMAL #txt
>Proto Rd0 0 0 32 24 18 0 #rect
>Proto Rd0 @|BIcon #fIcon
Rd0 f0 mainOut f4 tail #connect
Rd0 f4 head f3 in #connect
Rd0 f3 out f7 tail #connect
Rd0 f7 head f1 mainIn #connect
