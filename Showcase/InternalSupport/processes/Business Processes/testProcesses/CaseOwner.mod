[Ivy]
16A68510A341BE6E 7.5.0 #module
>Proto >Proto Collection #zClass
Cr0 CaseOwner Big #zClass
Cr0 B #cInfo
Cr0 #process
Cr0 @TextInP .resExport .resExport #zField
Cr0 @TextInP .type .type #zField
Cr0 @TextInP .processKind .processKind #zField
Cr0 @AnnotationInP-0n ai ai #zField
Cr0 @MessageFlowInP-0n messageIn messageIn #zField
Cr0 @MessageFlowOutP-0n messageOut messageOut #zField
Cr0 @TextInP .xml .xml #zField
Cr0 @TextInP .responsibility .responsibility #zField
Cr0 @StartRequest f0 '' #zField
Cr0 @EndTask f1 '' #zField
Cr0 @GridStep f3 '' #zField
Cr0 @PushWFArc f4 '' #zField
Cr0 @TaskSwitch f5 '' #zField
Cr0 @TkArc f6 '' #zField
Cr0 @PushWFArc f2 '' #zField
Cr0 @StartRequest f7 '' #zField
Cr0 @TaskSwitch f8 '' #zField
Cr0 @EndTask f9 '' #zField
Cr0 @GridStep f10 '' #zField
Cr0 @PushWFArc f11 '' #zField
Cr0 @TkArc f12 '' #zField
Cr0 @PushWFArc f13 '' #zField
>Proto Cr0 Cr0 CaseOwner #zField
Cr0 f0 outLink userIsOwner.ivp #txt
Cr0 f0 inParamDecl '<> param;' #txt
Cr0 f0 requestEnabled true #txt
Cr0 f0 triggerEnabled false #txt
Cr0 f0 callSignature userIsOwner() #txt
Cr0 f0 persist false #txt
Cr0 f0 startName 'demo user is owner' #txt
Cr0 f0 taskData 'TaskTriggered.NAM=Start\: case user owner' #txt
Cr0 f0 caseData businessCase.attach=true #txt
Cr0 f0 showInStartList 1 #txt
Cr0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>userIsOwner.ivp</name>
    </language>
</elementInfo>
' #txt
Cr0 f0 @C|.responsibility Everybody #txt
Cr0 f0 81 49 30 30 -38 15 #rect
Cr0 f0 @|StartRequestIcon #fIcon
Cr0 f1 433 49 30 30 0 15 #rect
Cr0 f1 @|EndIcon #fIcon
Cr0 f3 actionTable 'out=in;
' #txt
Cr0 f3 actionCode ivy.case.setOwner(ivy.wf.getSecurityContext().findUser("demo")); #txt
Cr0 f3 security system #txt
Cr0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Set demo user&#xD;
as owner</name>
    </language>
</elementInfo>
' #txt
Cr0 f3 160 42 128 44 -40 -16 #rect
Cr0 f3 @|StepIcon #fIcon
Cr0 f4 expr out #txt
Cr0 f4 111 64 160 64 #arcP
Cr0 f5 actionTable 'out=in1;
' #txt
Cr0 f5 outLinks "TaskA.ivp" #txt
Cr0 f5 caseData 'case.name=demo user is owner' #txt
Cr0 f5 taskData 'TaskA.NAM=demo user is owner
TaskA.ROL=CREATOR
TaskA.TYPE=0' #txt
Cr0 f5 template "" #txt
Cr0 f5 336 48 32 32 0 16 #rect
Cr0 f5 @|TaskSwitchIcon #fIcon
Cr0 f6 expr out #txt
Cr0 f6 var in1 #txt
Cr0 f6 288 64 336 64 #arcP
Cr0 f2 expr data #txt
Cr0 f2 outCond ivp=="TaskA.ivp" #txt
Cr0 f2 368 64 433 64 #arcP
Cr0 f7 outLink roleIsOwner.ivp #txt
Cr0 f7 inParamDecl '<> param;' #txt
Cr0 f7 requestEnabled true #txt
Cr0 f7 triggerEnabled false #txt
Cr0 f7 callSignature roleIsOwner() #txt
Cr0 f7 persist false #txt
Cr0 f7 startName 'CaseOwner role is owner' #txt
Cr0 f7 taskData 'TaskTriggered.NAM=Start\: case role owner' #txt
Cr0 f7 caseData businessCase.attach=true #txt
Cr0 f7 showInStartList 1 #txt
Cr0 f7 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>roleIsOwner.ivp</name>
    </language>
</elementInfo>
' #txt
Cr0 f7 @C|.responsibility Everybody #txt
Cr0 f7 81 145 30 30 -45 17 #rect
Cr0 f7 @|StartRequestIcon #fIcon
Cr0 f8 actionTable 'out=in1;
' #txt
Cr0 f8 outLinks "TaskA.ivp" #txt
Cr0 f8 caseData 'case.name=Test role is owner' #txt
Cr0 f8 taskData 'TaskA.NAM=Test role is owner
TaskA.ROL=CREATOR
TaskA.TYPE=0' #txt
Cr0 f8 template "" #txt
Cr0 f8 336 144 32 32 0 16 #rect
Cr0 f8 @|TaskSwitchIcon #fIcon
Cr0 f9 433 145 30 30 0 15 #rect
Cr0 f9 @|EndIcon #fIcon
Cr0 f10 actionTable 'out=in;
' #txt
Cr0 f10 actionCode ivy.case.setOwner(ivy.wf.getSecurityContext().findRole("CaseOwner")); #txt
Cr0 f10 security system #txt
Cr0 f10 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Set CaseOwner role&#xD;
as owner</name>
    </language>
</elementInfo>
' #txt
Cr0 f10 152 138 144 44 -51 -16 #rect
Cr0 f10 @|StepIcon #fIcon
Cr0 f11 expr data #txt
Cr0 f11 outCond ivp=="TaskA.ivp" #txt
Cr0 f11 368 160 433 160 #arcP
Cr0 f12 expr out #txt
Cr0 f12 var in1 #txt
Cr0 f12 296 160 336 160 #arcP
Cr0 f13 expr out #txt
Cr0 f13 111 160 152 160 #arcP
>Proto Cr0 .type internaltest.CaseOwnerData #txt
>Proto Cr0 .processKind NORMAL #txt
>Proto Cr0 0 0 32 24 18 0 #rect
>Proto Cr0 @|BIcon #fIcon
Cr0 f0 mainOut f4 tail #connect
Cr0 f4 head f3 mainIn #connect
Cr0 f3 mainOut f6 tail #connect
Cr0 f6 head f5 in #connect
Cr0 f5 out f2 tail #connect
Cr0 f2 head f1 mainIn #connect
Cr0 f7 mainOut f13 tail #connect
Cr0 f13 head f10 mainIn #connect
Cr0 f10 mainOut f12 tail #connect
Cr0 f12 head f8 in #connect
Cr0 f8 out f11 tail #connect
Cr0 f11 head f9 mainIn #connect
