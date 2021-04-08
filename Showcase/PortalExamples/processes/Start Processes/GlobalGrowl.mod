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
Gl0 @TaskSwitchSimple f8 '' #zField
Gl0 @UserDialog f12 '' #zField
Gl0 @UserDialog f13 '' #zField
Gl0 @UserDialog f15 '' #zField
Gl0 @StartRequest f17 '' #zField
Gl0 @TaskSwitchSimple f26 '' #zField
Gl0 @StartRequest f27 '' #zField
Gl0 @TaskSwitchSimple f28 '' #zField
Gl0 @TaskSwitchSimple f29 '' #zField
Gl0 @UserDialog f30 '' #zField
Gl0 @TkArc f31 '' #zField
Gl0 @TkArc f32 '' #zField
Gl0 @PushWFArc f33 '' #zField
Gl0 @PushWFArc f41 '' #zField
Gl0 @TkArc f42 '' #zField
Gl0 @PushWFArc f43 '' #zField
Gl0 @TkArc f44 '' #zField
Gl0 @PushWFArc f45 '' #zField
Gl0 @PushWFArc f46 '' #zField
Gl0 @PushWFArc f47 '' #zField
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
Gl0 f21 taskData 'TaskA.NAM=frame8CustomizedMessageAndSkipTasklist 
TaskA.SKIP_TASK_LIST=true' #txt
Gl0 f21 689 113 30 30 0 16 #rect
Gl0 f21 @|TaskSwitchSimpleIcon #fIcon
Gl0 f22 dialogId ch.ivyteam.ivy.project.portal.examples.GlobalGrowlIFrame8 #txt
Gl0 f22 startMethod start(Boolean) #txt
Gl0 f22 requestActionDecl '<Boolean isCustomized> param;' #txt
Gl0 f22 requestMappingAction 'param.isCustomized=true;
' #txt
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
Gl0 f19 startMethod start(Boolean) #txt
Gl0 f19 requestActionDecl '<Boolean isCustomized> param;' #txt
Gl0 f19 requestMappingAction 'param.isCustomized=true;
' #txt
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
Gl0 f34 startMethod start(Boolean) #txt
Gl0 f34 requestActionDecl '<Boolean isCustomized> param;' #txt
Gl0 f34 requestMappingAction 'param.isCustomized=true;
' #txt
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
Gl0 f7 taskData 'TaskA.NAM=frame8CustomizedMessageAndSkipTasklist 
TaskA.SKIP_TASK_LIST=false' #txt
Gl0 f7 177 113 30 30 0 16 #rect
Gl0 f7 @|TaskSwitchSimpleIcon #fIcon
Gl0 f35 actionTable 'out=in1;
' #txt
Gl0 f35 taskData 'TaskA.NAM=frame8CustomizedMessage
TaskA.SKIP_TASK_LIST=false' #txt
Gl0 f35 177 177 30 30 0 16 #rect
Gl0 f35 @|TaskSwitchSimpleIcon #fIcon
Gl0 f36 outLink frame8CustomizedMessage.ivp #txt
Gl0 f36 inParamDecl '<> param;' #txt
Gl0 f36 requestEnabled true #txt
Gl0 f36 triggerEnabled false #txt
Gl0 f36 callSignature frame8CustomizedMessage() #txt
Gl0 f36 startName 'Display growl customized message for task using IFrame' #txt
Gl0 f36 taskData TaskTriggered.NAM=frame8CustomizedMessage #txt
Gl0 f36 caseData businessCase.attach=true #txt
Gl0 f36 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>frame8CustomizedMessage.ivp</name>
    </language>
</elementInfo>
' #txt
Gl0 f36 @C|.responsibility Everybody #txt
Gl0 f36 81 177 30 30 -21 17 #rect
Gl0 f36 @|StartRequestIcon #fIcon
Gl0 f9 dialogId ch.ivyteam.ivy.project.portal.examples.GlobalGrowlIFrame8 #txt
Gl0 f9 startMethod start(Boolean) #txt
Gl0 f9 requestActionDecl '<Boolean isCustomized> param;' #txt
Gl0 f9 requestMappingAction 'param.isCustomized=true;
' #txt
Gl0 f9 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>GlobalGrowlIFrame8</name>
    </language>
</elementInfo>
' #txt
Gl0 f9 256 106 128 44 -57 -8 #rect
Gl0 f9 @|UserDialogIcon #fIcon
Gl0 f10 outLink frame8CustomizedMessageAndSkipTasklist.ivp #txt
Gl0 f10 inParamDecl '<> param;' #txt
Gl0 f10 requestEnabled true #txt
Gl0 f10 triggerEnabled false #txt
Gl0 f10 callSignature frame8CustomizedMessageAndSkipTasklist() #txt
Gl0 f10 startName 'Display growl customized message for task using IFrame and enable Skip Tasklist' #txt
Gl0 f10 taskData 'TaskTriggered.NAM=frame8CustomizedMessageAndSkipTasklist ' #txt
Gl0 f10 caseData businessCase.attach=true #txt
Gl0 f10 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>frame8CustomizedMessageAndSkipTasklist.ivp</name>
    </language>
</elementInfo>
' #txt
Gl0 f10 @C|.responsibility Everybody #txt
Gl0 f10 81 113 30 30 -21 17 #rect
Gl0 f10 @|StartRequestIcon #fIcon
Gl0 f11 977 209 30 30 0 15 #rect
Gl0 f11 @|EndIcon #fIcon
Gl0 f18 actionTable 'out=in1;
' #txt
Gl0 f18 taskData 'TaskA.NAM=frame8CustomizedMessageAndSkipTasklist 
TaskA.SKIP_TASK_LIST=true' #txt
Gl0 f18 433 113 30 30 0 16 #rect
Gl0 f18 @|TaskSwitchSimpleIcon #fIcon
Gl0 f38 207 192 256 192 #arcP
Gl0 f39 384 192 977 219 #arcP
Gl0 f39 1 896 192 #addKink
Gl0 f39 0 0.7086103002064744 0 0 #arcLabel
Gl0 f14 640 128 689 128 #arcP
Gl0 f23 719 128 768 128 #arcP
Gl0 f25 896 128 981 213 #arcP
Gl0 f24 384 128 433 128 #arcP
Gl0 f24 0 0.5000000000000001 0 0 #arcLabel
Gl0 f16 207 128 256 128 #arcP
Gl0 f37 111 192 177 192 #arcP
Gl0 f20 463 128 512 128 #arcP
Gl0 f40 111 128 177 128 #arcP
Gl0 f8 actionTable 'out=in1;
' #txt
Gl0 f8 taskData 'TaskA.NAM=frame8StandardMessageAndSkipTasklist
TaskA.SKIP_TASK_LIST=true' #txt
Gl0 f8 433 241 30 30 0 16 #rect
Gl0 f8 @|TaskSwitchSimpleIcon #fIcon
Gl0 f12 dialogId ch.ivyteam.ivy.project.portal.examples.GlobalGrowlIFrame8 #txt
Gl0 f12 startMethod start(Boolean) #txt
Gl0 f12 requestActionDecl '<Boolean isCustomized> param;' #txt
Gl0 f12 requestMappingAction 'param.isCustomized=false;
' #txt
Gl0 f12 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>GlobalGrowlIFrame8</name>
    </language>
</elementInfo>
' #txt
Gl0 f12 512 234 128 44 -57 -8 #rect
Gl0 f12 @|UserDialogIcon #fIcon
Gl0 f13 dialogId ch.ivyteam.ivy.project.portal.examples.GlobalGrowlIFrame8 #txt
Gl0 f13 startMethod start(Boolean) #txt
Gl0 f13 requestActionDecl '<Boolean isCustomized> param;' #txt
Gl0 f13 requestMappingAction 'param.isCustomized=false;
' #txt
Gl0 f13 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>GlobalGrowlIFrame8</name>
    </language>
</elementInfo>
' #txt
Gl0 f13 768 234 128 44 -57 -8 #rect
Gl0 f13 @|UserDialogIcon #fIcon
Gl0 f15 dialogId ch.ivyteam.ivy.project.portal.examples.GlobalGrowlIFrame8 #txt
Gl0 f15 startMethod start(Boolean) #txt
Gl0 f15 requestActionDecl '<Boolean isCustomized> param;' #txt
Gl0 f15 requestMappingAction 'param.isCustomized=false;
' #txt
Gl0 f15 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>GlobalGrowlIFrame8</name>
    </language>
</elementInfo>
' #txt
Gl0 f15 256 298 128 44 -57 -8 #rect
Gl0 f15 @|UserDialogIcon #fIcon
Gl0 f17 outLink frame8StandardMessage.ivp #txt
Gl0 f17 inParamDecl '<> param;' #txt
Gl0 f17 requestEnabled true #txt
Gl0 f17 triggerEnabled false #txt
Gl0 f17 callSignature frame8StandardMessage() #txt
Gl0 f17 startName 'Display growl standard message for task using IFrame' #txt
Gl0 f17 taskData TaskTriggered.NAM=frame8StandardMessage #txt
Gl0 f17 caseData businessCase.attach=true #txt
Gl0 f17 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>frame8StandardMessage.ivp</name>
    </language>
</elementInfo>
' #txt
Gl0 f17 @C|.responsibility Everybody #txt
Gl0 f17 81 305 30 30 -21 17 #rect
Gl0 f17 @|StartRequestIcon #fIcon
Gl0 f26 actionTable 'out=in1;
' #txt
Gl0 f26 taskData 'TaskA.NAM=frame8StandardMessageAndSkipTasklist
TaskA.SKIP_TASK_LIST=false' #txt
Gl0 f26 177 241 30 30 0 16 #rect
Gl0 f26 @|TaskSwitchSimpleIcon #fIcon
Gl0 f27 outLink frame8StandardMessageAndSkipTasklist.ivp #txt
Gl0 f27 inParamDecl '<> param;' #txt
Gl0 f27 requestEnabled true #txt
Gl0 f27 triggerEnabled false #txt
Gl0 f27 callSignature frame8StandardMessageAndSkipTasklist() #txt
Gl0 f27 startName 'Display growl standard message for task using IFrame and enable Skip Tasklist' #txt
Gl0 f27 taskData TaskTriggered.NAM=frame8StandardMessageAndSkipTasklist #txt
Gl0 f27 caseData businessCase.attach=true #txt
Gl0 f27 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>frame8StandardMessageAndSkipTasklist.ivp</name>
    </language>
</elementInfo>
' #txt
Gl0 f27 @C|.responsibility Everybody #txt
Gl0 f27 81 241 30 30 -21 17 #rect
Gl0 f27 @|StartRequestIcon #fIcon
Gl0 f28 actionTable 'out=in1;
' #txt
Gl0 f28 taskData 'TaskA.NAM=frame8StandardMessageAndSkipTasklist
TaskA.SKIP_TASK_LIST=true' #txt
Gl0 f28 689 241 30 30 0 16 #rect
Gl0 f28 @|TaskSwitchSimpleIcon #fIcon
Gl0 f29 actionTable 'out=in1;
' #txt
Gl0 f29 taskData 'TaskA.NAM=frame8StandardMessage
TaskA.SKIP_TASK_LIST=false' #txt
Gl0 f29 177 305 30 30 0 16 #rect
Gl0 f29 @|TaskSwitchSimpleIcon #fIcon
Gl0 f30 dialogId ch.ivyteam.ivy.project.portal.examples.GlobalGrowlIFrame8 #txt
Gl0 f30 startMethod start(Boolean) #txt
Gl0 f30 requestActionDecl '<Boolean isCustomized> param;' #txt
Gl0 f30 requestMappingAction 'param.isCustomized=false;
' #txt
Gl0 f30 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>GlobalGrowlIFrame8</name>
    </language>
</elementInfo>
' #txt
Gl0 f30 256 234 128 44 -57 -8 #rect
Gl0 f30 @|UserDialogIcon #fIcon
Gl0 f31 384 256 433 256 #arcP
Gl0 f31 0 0.5000000000000001 0 0 #arcLabel
Gl0 f32 111 256 177 256 #arcP
Gl0 f33 207 256 256 256 #arcP
Gl0 f41 207 320 256 320 #arcP
Gl0 f42 111 320 177 320 #arcP
Gl0 f43 719 256 768 256 #arcP
Gl0 f44 640 256 689 256 #arcP
Gl0 f45 463 256 512 256 #arcP
Gl0 f46 896 256 977 226 #arcP
Gl0 f47 384 320 981 234 #arcP
Gl0 f47 1 896 320 #addKink
Gl0 f47 0 0.7595096117729628 0 0 #arcLabel
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
Gl0 f27 mainOut f32 tail #connect
Gl0 f32 head f26 in #connect
Gl0 f26 out f33 tail #connect
Gl0 f33 head f30 mainIn #connect
Gl0 f8 out f45 tail #connect
Gl0 f45 head f12 mainIn #connect
Gl0 f28 out f43 tail #connect
Gl0 f43 head f13 mainIn #connect
Gl0 f30 mainOut f31 tail #connect
Gl0 f31 head f8 in #connect
Gl0 f12 mainOut f44 tail #connect
Gl0 f44 head f28 in #connect
Gl0 f17 mainOut f42 tail #connect
Gl0 f42 head f29 in #connect
Gl0 f29 out f41 tail #connect
Gl0 f41 head f15 mainIn #connect
Gl0 f13 mainOut f46 tail #connect
Gl0 f46 head f11 mainIn #connect
Gl0 f15 mainOut f47 tail #connect
Gl0 f47 head f11 mainIn #connect
