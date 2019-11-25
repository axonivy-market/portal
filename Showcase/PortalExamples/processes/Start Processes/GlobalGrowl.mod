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
Gl0 f1 465 49 30 30 0 15 #rect
Gl0 f1 @|EndIcon #fIcon
Gl0 f3 actionTable 'out=in1;
' #txt
Gl0 f3 outLinks "TaskA.ivp" #txt
Gl0 f3 taskData 'TaskA.NAM=Global Growl
TaskA.customFields.STRING.embedInFrame="false"' #txt
Gl0 f3 template "" #txt
Gl0 f3 208 48 32 32 0 16 #rect
Gl0 f3 @|TaskSwitchIcon #fIcon
Gl0 f4 expr out #txt
Gl0 f4 var in1 #txt
Gl0 f4 111 64 208 64 #arcP
Gl0 f5 dialogId ch.ivyteam.ivy.project.portal.examples.GlobalGrowl #txt
Gl0 f5 startMethod start() #txt
Gl0 f5 requestActionDecl '<> param;' #txt
Gl0 f5 responseActionDecl 'ch.ivyteam.ivy.project.portal.examples.GlobalGrowlData out;
' #txt
Gl0 f5 responseMappingAction 'out=in;
' #txt
Gl0 f5 296 42 112 44 0 -8 #rect
Gl0 f5 @|UserDialogIcon #fIcon
Gl0 f6 expr data #txt
Gl0 f6 outCond ivp=="TaskA.ivp" #txt
Gl0 f6 240 64 296 64 #arcP
Gl0 f2 expr out #txt
Gl0 f2 408 64 465 64 #arcP
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
