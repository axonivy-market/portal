[Ivy]
[>Created: Tue Jul 18 17:44:35 ICT 2017]
1574EC0ADCD99718 3.20 #module
>Proto >Proto Collection #zClass
Dt0 DevBuildWorkflow Big #zClass
Dt0 B #cInfo
Dt0 #process
Dt0 @TextInP .resExport .resExport #zField
Dt0 @TextInP .type .type #zField
Dt0 @TextInP .processKind .processKind #zField
Dt0 @AnnotationInP-0n ai ai #zField
Dt0 @MessageFlowInP-0n messageIn messageIn #zField
Dt0 @MessageFlowOutP-0n messageOut messageOut #zField
Dt0 @TextInP .xml .xml #zField
Dt0 @TextInP .responsibility .responsibility #zField
Dt0 @EndTask f1 '' #zField
Dt0 @StartRequest f0 '' #zField
Dt0 @CallSub f2 '' #zField
Dt0 @PushWFArc f3 '' #zField
Dt0 @PushWFArc f4 '' #zField
>Proto Dt0 Dt0 DevBuildWorkflow #zField
Dt0 f1 type gawfs.Data #txt
Dt0 f1 369 49 30 30 0 15 #rect
Dt0 f1 @|EndIcon #fIcon
Dt0 f0 outLink start.ivp #txt
Dt0 f0 type gawfs.Data #txt
Dt0 f0 inParamDecl '<> param;' #txt
Dt0 f0 actionDecl 'gawfs.Data out;
' #txt
Dt0 f0 guid 1578123DBC99E740 #txt
Dt0 f0 requestEnabled true #txt
Dt0 f0 triggerEnabled false #txt
Dt0 f0 callSignature start() #txt
Dt0 f0 persist false #txt
Dt0 f0 taskData 'TaskTriggered.ROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.EXPRI=2
TaskTriggered.TYPE=0
TaskTriggered.PRI=2
TaskTriggered.EXROL=Everybody' #txt
Dt0 f0 caseData businessCase.attach=false #txt
Dt0 f0 wfuser 1 #txt
Dt0 f0 showInStartList 0 #txt
Dt0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start.ivp</name>
        <nameStyle>9,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Dt0 f0 @C|.responsibility Everybody #txt
Dt0 f0 89 49 30 30 -21 17 #rect
Dt0 f0 @|StartRequestIcon #fIcon
Dt0 f2 type gawfs.Data #txt
Dt0 f2 processCall 'Functional Processes/editWorkflow:newWorkflow()' #txt
Dt0 f2 doCall true #txt
Dt0 f2 requestActionDecl '<> param;
' #txt
Dt0 f2 responseActionDecl 'gawfs.Data out;
' #txt
Dt0 f2 responseMappingAction 'out=in;
' #txt
Dt0 f2 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>editWorkflow</name>
        <nameStyle>12,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Dt0 f2 184 42 112 44 -35 -8 #rect
Dt0 f2 @|CallSubIcon #fIcon
Dt0 f3 expr out #txt
Dt0 f3 119 64 184 64 #arcP
Dt0 f4 expr out #txt
Dt0 f4 296 64 369 64 #arcP
>Proto Dt0 .type gawfs.Data #txt
>Proto Dt0 .processKind NORMAL #txt
>Proto Dt0 0 0 32 24 18 0 #rect
>Proto Dt0 @|BIcon #fIcon
Dt0 f0 mainOut f3 tail #connect
Dt0 f3 head f2 mainIn #connect
Dt0 f2 mainOut f4 tail #connect
Dt0 f4 head f1 mainIn #connect
