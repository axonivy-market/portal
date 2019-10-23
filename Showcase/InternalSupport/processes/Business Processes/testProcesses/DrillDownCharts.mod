[Ivy]
15C1FA2C887C250A 7.5.0 #module
>Proto >Proto Collection #zClass
Ds0 DrillDownCharts Big #zClass
Ds0 B #cInfo
Ds0 #process
Ds0 @TextInP .type .type #zField
Ds0 @TextInP .processKind .processKind #zField
Ds0 @AnnotationInP-0n ai ai #zField
Ds0 @MessageFlowInP-0n messageIn messageIn #zField
Ds0 @MessageFlowOutP-0n messageOut messageOut #zField
Ds0 @TextInP .xml .xml #zField
Ds0 @TextInP .responsibility .responsibility #zField
Ds0 @StartRequest f0 '' #zField
Ds0 @EndTask f1 '' #zField
Ds0 @UserDialog f3 '' #zField
Ds0 @PushWFArc f4 '' #zField
Ds0 @CallSub f5 '' #zField
Ds0 @PushWFArc f6 '' #zField
Ds0 @PushWFArc f2 '' #zField
>Proto Ds0 Ds0 DrillDownCharts #zField
Ds0 f0 outLink openDrillDownCharts.ivp #txt
Ds0 f0 inParamDecl '<> param;' #txt
Ds0 f0 requestEnabled true #txt
Ds0 f0 triggerEnabled false #txt
Ds0 f0 callSignature openDrillDownCharts() #txt
Ds0 f0 persist false #txt
Ds0 f0 startName 'Drill down Charts' #txt
Ds0 f0 taskData 'TaskTriggered.EXPRI=2
TaskTriggered.EXROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.PRI=2
TaskTriggered.ROL=Everybody
TaskTriggered.TYPE=0' #txt
Ds0 f0 caseData businessCase.attach=true #txt
Ds0 f0 showInStartList 1 #txt
Ds0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>openDrillDownCharts.ivp</name>
    </language>
</elementInfo>
' #txt
Ds0 f0 @C|.responsibility Everybody #txt
Ds0 f0 81 49 30 30 -68 17 #rect
Ds0 f0 @|StartRequestIcon #fIcon
Ds0 f1 497 49 30 30 0 15 #rect
Ds0 f1 @|EndIcon #fIcon
Ds0 f3 dialogId internaltest.DrillDownCharts #txt
Ds0 f3 startMethod start() #txt
Ds0 f3 requestActionDecl '<> param;' #txt
Ds0 f3 responseActionDecl 'internaltest.DrillDownChartsData out;
' #txt
Ds0 f3 responseMappingAction 'out=in;
out.taskView=result.taskView;
' #txt
Ds0 f3 168 42 112 44 0 -8 #rect
Ds0 f3 @|UserDialogIcon #fIcon
Ds0 f4 expr out #txt
Ds0 f4 111 64 168 64 #arcP
Ds0 f5 processCall 'Functional Processes/OpenPortalTasks:useView(ch.ivy.addon.portal.generic.view.TaskView)' #txt
Ds0 f5 requestActionDecl '<ch.ivy.addon.portal.generic.view.TaskView taskView> param;' #txt
Ds0 f5 requestMappingAction 'param.taskView=in.taskView;
' #txt
Ds0 f5 responseActionDecl 'internaltest.DrillDownChartsData out;
' #txt
Ds0 f5 responseMappingAction 'out=in;
' #txt
Ds0 f5 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>OpenPortalTasks</name>
        <nameStyle>15,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ds0 f5 328 42 112 44 -48 -8 #rect
Ds0 f5 @|CallSubIcon #fIcon
Ds0 f6 expr out #txt
Ds0 f6 280 64 328 64 #arcP
Ds0 f2 expr out #txt
Ds0 f2 440 64 497 64 #arcP
>Proto Ds0 .type internaltest.DrillDownChartsData #txt
>Proto Ds0 .processKind NORMAL #txt
>Proto Ds0 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language/>
</elementInfo>
' #txt
>Proto Ds0 0 0 32 24 18 0 #rect
>Proto Ds0 @|BIcon #fIcon
Ds0 f0 mainOut f4 tail #connect
Ds0 f4 head f3 mainIn #connect
Ds0 f3 mainOut f6 tail #connect
Ds0 f6 head f5 mainIn #connect
Ds0 f5 mainOut f2 tail #connect
Ds0 f2 head f1 mainIn #connect
