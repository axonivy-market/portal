[Ivy]
16A7BB2ADC9580A8 7.5.0 #module
>Proto >Proto Collection #zClass
Gl0 GlobalGrowl Big #zClass
Gl0 B #cInfo
Gl0 #process
Gl0 @TextInP .type .type #zField
Gl0 @TextInP .processKind .processKind #zField
Gl0 @AnnotationInP-0n ai ai #zField
Gl0 @MessageFlowInP-0n messageIn messageIn #zField
Gl0 @MessageFlowOutP-0n messageOut messageOut #zField
Gl0 @TextInP .xml .xml #zField
Gl0 @TextInP .responsibility .responsibility #zField
Gl0 @StartRequest f0 '' #zField
Gl0 @EndTask f1 '' #zField
Gl0 @TaskSwitch f3 '' #zField
Gl0 @TkArc f4 '' #zField
Gl0 @UserDialog f5 '' #zField
Gl0 @PushWFArc f6 '' #zField
Gl0 @PushWFArc f2 '' #zField
Gl0 @TaskSwitchSimple f21 '' #zField
Gl0 @UserDialog f22 '' #zField
Gl0 @UserDialog f19 '' #zField
Gl0 @UserDialog f34 '' #zField
Gl0 @TaskSwitchSimple f7 '' #zField
Gl0 @TaskSwitchSimple f35 '' #zField
Gl0 @StartRequest f36 '' #zField
Gl0 @UserDialog f9 '' #zField
Gl0 @StartRequest f10 '' #zField
Gl0 @EndTask f11 '' #zField
Gl0 @TaskSwitchSimple f18 '' #zField
Gl0 @PushWFArc f38 '' #zField
Gl0 @PushWFArc f39 '' #zField
Gl0 @TkArc f14 '' #zField
Gl0 @PushWFArc f23 '' #zField
Gl0 @PushWFArc f25 '' #zField
Gl0 @TkArc f24 '' #zField
Gl0 @PushWFArc f16 '' #zField
Gl0 @TkArc f37 '' #zField
Gl0 @PushWFArc f20 '' #zField
Gl0 @TkArc f40 '' #zField
>Proto Gl0 Gl0 GlobalGrowl #zField
Gl0 f0 outLink start.ivp #txt
Gl0 f0 inParamDecl '<> param;' #txt
Gl0 f0 requestEnabled true #txt
Gl0 f0 triggerEnabled false #txt
Gl0 f0 callSignature start() #txt
Gl0 f0 persist false #txt
Gl0 f0 startName 'Global Growl' #txt
Gl0 f0 taskData 'TaskTriggered.NAM=Start\: Global Growl' #txt
Gl0 f0 caseData 'businessCase.attach=true
case.name=Global Growl
customFields.STRING.embedInFrame="false"' #txt
Gl0 f0 showInStartList 1 #txt
Gl0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start.ivp</name>
    </language>
</elementInfo>
' #txt
Gl0 f0 @C|.responsibility Everybody #txt
Gl0 f0 81 49 30 30 -21 17 #rect
Gl0 f0 @|StartRequestIcon #fIcon
Gl0 f1 433 49 30 30 0 15 #rect
Gl0 f1 @|EndIcon #fIcon
Gl0 f3 actionTable 'out=in1;
' #txt
Gl0 f3 outLinks "TaskA.ivp" #txt
Gl0 f3 taskData 'TaskA.NAM=Global Growl
TaskA.customFields.STRING.embedInFrame="false"' #txt
Gl0 f3 template "" #txt
Gl0 f3 176 48 32 32 0 16 #rect
Gl0 f3 @|TaskSwitchIcon #fIcon
Gl0 f4 expr out #txt
Gl0 f4 var in1 #txt
Gl0 f4 111 64 176 64 #arcP
Gl0 f5 dialogId ch.ivyteam.ivy.project.portal.examples.GlobalGrowl #txt
Gl0 f5 startMethod start() #txt
Gl0 f5 requestActionDecl '<> param;' #txt
Gl0 f5 responseActionDecl 'ch.ivyteam.ivy.project.portal.examples.GlobalGrowlData out;
' #txt
Gl0 f5 responseMappingAction 'out=in;
' #txt
Gl0 f5 264 42 112 44 0 -8 #rect
Gl0 f5 @|UserDialogIcon #fIcon
Gl0 f6 expr data #txt
Gl0 f6 outCond ivp=="TaskA.ivp" #txt
Gl0 f6 208 64 264 64 #arcP
Gl0 f2 expr out #txt
Gl0 f2 376 64 433 64 #arcP
Gl0 f21 actionTable 'out=in1;
' #txt
Gl0 f21 taskData 'TaskA.NAM=frame8
TaskA.SKIP_TASK_LIST=true' #txt
Gl0 f21 689 113 30 30 0 16 #rect
Gl0 f21 @|TaskSwitchSimpleIcon #fIcon
Gl0 f22 dialogId ch.ivyteam.ivy.project.portal.examples.GlobalGrowlIFrame8 #txt
Gl0 f22 startMethod start() #txt
Gl0 f22 requestActionDecl '<> param;' #txt
Gl0 f22 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>GlobalGrowlIFrame8</name>
    </language>
</elementInfo>
' #txt
Gl0 f22 768 106 128 44 -57 -8 #rect
Gl0 f22 @|UserDialogIcon #fIcon
Gl0 f19 dialogId ch.ivyteam.ivy.project.portal.examples.GlobalGrowlIFrame8 #txt
Gl0 f19 startMethod start() #txt
Gl0 f19 requestActionDecl '<> param;' #txt
Gl0 f19 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>GlobalGrowlIFrame8</name>
    </language>
</elementInfo>
' #txt
Gl0 f19 512 106 128 44 -57 -8 #rect
Gl0 f19 @|UserDialogIcon #fIcon
Gl0 f34 dialogId ch.ivyteam.ivy.project.portal.examples.GlobalGrowlIFrame8 #txt
Gl0 f34 startMethod start() #txt
Gl0 f34 requestActionDecl '<> param;' #txt
Gl0 f34 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>GlobalGrowlIFrame8</name>
    </language>
</elementInfo>
' #txt
Gl0 f34 256 170 128 44 -57 -8 #rect
Gl0 f34 @|UserDialogIcon #fIcon
Gl0 f7 actionTable 'out=in1;
' #txt
Gl0 f7 taskData 'TaskA.NAM=frame8
TaskA.SKIP_TASK_LIST=false' #txt
Gl0 f7 177 113 30 30 0 16 #rect
Gl0 f7 @|TaskSwitchSimpleIcon #fIcon
Gl0 f35 actionTable 'out=in1;
' #txt
Gl0 f35 taskData 'TaskA.NAM=frame8
TaskA.SKIP_TASK_LIST=false' #txt
Gl0 f35 177 177 30 30 0 16 #rect
Gl0 f35 @|TaskSwitchSimpleIcon #fIcon
Gl0 f36 outLink frame8.ivp #txt
Gl0 f36 inParamDecl '<> param;' #txt
Gl0 f36 requestEnabled true #txt
Gl0 f36 triggerEnabled false #txt
Gl0 f36 callSignature frame8() #txt
Gl0 f36 startName 'Display growl message for task using IFrame' #txt
Gl0 f36 caseData businessCase.attach=true #txt
Gl0 f36 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>frame8.ivp</name>
    </language>
</elementInfo>
' #txt
Gl0 f36 @C|.responsibility Everybody #txt
Gl0 f36 81 177 30 30 -21 17 #rect
Gl0 f36 @|StartRequestIcon #fIcon
Gl0 f9 dialogId ch.ivyteam.ivy.project.portal.examples.GlobalGrowlIFrame8 #txt
Gl0 f9 startMethod start() #txt
Gl0 f9 requestActionDecl '<> param;' #txt
Gl0 f9 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>GlobalGrowlIFrame8</name>
    </language>
</elementInfo>
' #txt
Gl0 f9 256 106 128 44 -57 -8 #rect
Gl0 f9 @|UserDialogIcon #fIcon
Gl0 f10 outLink fram8skiptasklist.ivp #txt
Gl0 f10 inParamDecl '<> param;' #txt
Gl0 f10 requestEnabled true #txt
Gl0 f10 triggerEnabled false #txt
Gl0 f10 callSignature fram8skiptasklist() #txt
Gl0 f10 startName 'Display growl message for task using IFrame and enable Skip Tasklist' #txt
Gl0 f10 caseData businessCase.attach=true #txt
Gl0 f10 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>fram8skiptasklist.ivp</name>
    </language>
</elementInfo>
' #txt
Gl0 f10 @C|.responsibility Everybody #txt
Gl0 f10 81 113 30 30 -21 17 #rect
Gl0 f10 @|StartRequestIcon #fIcon
Gl0 f11 953 145 30 30 0 15 #rect
Gl0 f11 @|EndIcon #fIcon
Gl0 f18 actionTable 'out=in1;
' #txt
Gl0 f18 taskData 'TaskA.NAM=frame8
TaskA.SKIP_TASK_LIST=true' #txt
Gl0 f18 433 113 30 30 0 16 #rect
Gl0 f18 @|TaskSwitchSimpleIcon #fIcon
Gl0 f38 207 192 256 192 #arcP
Gl0 f39 384 192 954 165 #arcP
Gl0 f39 1 888 192 #addKink
Gl0 f39 0 0.7086103002064744 0 0 #arcLabel
Gl0 f14 640 128 689 128 #arcP
Gl0 f23 719 128 768 128 #arcP
Gl0 f25 896 128 953 156 #arcP
Gl0 f24 384 128 433 128 #arcP
Gl0 f24 0 0.5000000000000001 0 0 #arcLabel
Gl0 f16 207 128 256 128 #arcP
Gl0 f37 111 192 177 192 #arcP
Gl0 f20 463 128 512 128 #arcP
Gl0 f40 111 128 177 128 #arcP
>Proto Gl0 .type ch.ivyteam.ivy.project.portal.examples.GlobalGrowlData #txt
>Proto Gl0 .processKind NORMAL #txt
>Proto Gl0 0 0 32 24 18 0 #rect
>Proto Gl0 @|BIcon #fIcon
Gl0 f0 mainOut f4 tail #connect
Gl0 f4 head f3 in #connect
Gl0 f3 out f6 tail #connect
Gl0 f6 head f5 mainIn #connect
Gl0 f5 mainOut f2 tail #connect
Gl0 f2 head f1 mainIn #connect
Gl0 f10 mainOut f40 tail #connect
Gl0 f40 head f7 in #connect
Gl0 f7 out f16 tail #connect
Gl0 f16 head f9 mainIn #connect
Gl0 f18 out f20 tail #connect
Gl0 f20 head f19 mainIn #connect
Gl0 f21 out f23 tail #connect
Gl0 f23 head f22 mainIn #connect
Gl0 f9 mainOut f24 tail #connect
Gl0 f24 head f18 in #connect
Gl0 f19 mainOut f14 tail #connect
Gl0 f14 head f21 in #connect
Gl0 f22 mainOut f25 tail #connect
Gl0 f25 head f11 mainIn #connect
Gl0 f36 mainOut f37 tail #connect
Gl0 f37 head f35 in #connect
Gl0 f35 out f38 tail #connect
Gl0 f38 head f34 mainIn #connect
Gl0 f34 mainOut f39 tail #connect
Gl0 f39 head f11 mainIn #connect
