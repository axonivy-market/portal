[Ivy]
1815C3A4CE9836ED 7.5.0 #module
>Proto >Proto Collection #zClass
Pt0 ProcessHistoryExample Big #zClass
Pt0 B #cInfo
Pt0 #process
Pt0 @TextInP .type .type #zField
Pt0 @TextInP .processKind .processKind #zField
Pt0 @AnnotationInP-0n ai ai #zField
Pt0 @MessageFlowInP-0n messageIn messageIn #zField
Pt0 @MessageFlowOutP-0n messageOut messageOut #zField
Pt0 @TextInP .xml .xml #zField
Pt0 @TextInP .responsibility .responsibility #zField
Pt0 @StartRequest f0 '' #zField
Pt0 @EndTask f1 '' #zField
Pt0 @StartRequest f6 '' #zField
Pt0 @EndTask f8 '' #zField
Pt0 @TaskSwitchSimple f7 '' #zField
Pt0 @GridStep f19 '' #zField
Pt0 @PushWFArc f20 '' #zField
Pt0 @PushWFArc f11 '' #zField
Pt0 @EndTask f12 '' #zField
Pt0 @StartRequest f14 '' #zField
Pt0 @StartRequest f17 '' #zField
Pt0 @EndTask f18 '' #zField
Pt0 @GridStep f23 '' #zField
Pt0 @PushWFArc f25 '' #zField
Pt0 @TaskSwitchSimple f22 '' #zField
Pt0 @SignalStartEvent f31 '' #zField
Pt0 @GridStep f26 '' #zField
Pt0 @PushWFArc f27 '' #zField
Pt0 @PushWFArc f28 '' #zField
Pt0 @EndTask f29 '' #zField
Pt0 @PushWFArc f30 '' #zField
Pt0 @InfoButton f33 '' #zField
Pt0 @InfoButton f34 '' #zField
Pt0 @UserDialog f2 '' #zField
Pt0 @PushWFArc f3 '' #zField
Pt0 @PushWFArc f4 '' #zField
Pt0 @UserDialog f13 '' #zField
Pt0 @PushWFArc f15 '' #zField
Pt0 @PushWFArc f16 '' #zField
Pt0 @CallSub f5 '' #zField
Pt0 @PushWFArc f9 '' #zField
Pt0 @TkArc f10 '' #zField
Pt0 @CallSub f21 '' #zField
Pt0 @PushWFArc f24 '' #zField
Pt0 @TkArc f32 '' #zField
>Proto Pt0 Pt0 ProcessHistoryExample #zField
Pt0 f0 outLink viewProcessHistoryOfAlphaCompany.ivp #txt
Pt0 f0 inParamDecl '<> param;' #txt
Pt0 f0 requestEnabled true #txt
Pt0 f0 triggerEnabled false #txt
Pt0 f0 callSignature viewProcessHistoryOfAlphaCompany() #txt
Pt0 f0 persist false #txt
Pt0 f0 startName 'View Process History of Alpha Company in new page' #txt
Pt0 f0 taskData 'TaskTriggered.EXPRI=2
TaskTriggered.EXROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.PRI=2
TaskTriggered.ROL=Everybody
TaskTriggered.TYPE=0' #txt
Pt0 f0 caseData businessCase.attach=true #txt
Pt0 f0 showInStartList 1 #txt
Pt0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>viewProcessHistoryOfAlphaCompany.ivp</name>
        <nameStyle>36,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Pt0 f0 @C|.responsibility Everybody #txt
Pt0 f0 129 33 30 30 -111 17 #rect
Pt0 f0 @|StartRequestIcon #fIcon
Pt0 f1 609 33 30 30 0 15 #rect
Pt0 f1 @|EndIcon #fIcon
Pt0 f6 outLink createAlphaCompany.ivp #txt
Pt0 f6 inParamDecl '<> param;' #txt
Pt0 f6 requestEnabled true #txt
Pt0 f6 triggerEnabled false #txt
Pt0 f6 callSignature createAlphaCompany() #txt
Pt0 f6 persist false #txt
Pt0 f6 startName 'Create Alpha Company' #txt
Pt0 f6 taskData 'TaskTriggered.EXPRI=2
TaskTriggered.EXROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.PRI=2
TaskTriggered.ROL=Everybody
TaskTriggered.TYPE=0' #txt
Pt0 f6 caseData 'businessCase.attach=true
case.name=Alpha Company' #txt
Pt0 f6 showInStartList 1 #txt
Pt0 f6 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>createAlphaCompany.ivp</name>
        <nameStyle>22,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Pt0 f6 @C|.responsibility Everybody #txt
Pt0 f6 129 129 30 30 -68 17 #rect
Pt0 f6 @|StartRequestIcon #fIcon
Pt0 f8 801 129 30 30 0 15 #rect
Pt0 f8 @|EndIcon #fIcon
Pt0 f7 actionTable 'out=in1;
' #txt
Pt0 f7 outLinks "TaskA.ivp" #txt
Pt0 f7 taskData 'TaskA.EXPRI=2
TaskA.EXROL=Everybody
TaskA.EXTYPE=0
TaskA.NAM=Alpha Company
TaskA.PRI=2
TaskA.ROL=Everybody
TaskA.SKIP_TASK_LIST=false
TaskA.TYPE=0' #txt
Pt0 f7 template "" #txt
Pt0 f7 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Alpha Company</name>
        <nameStyle>13,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Pt0 f7 689 129 30 30 -43 17 #rect
Pt0 f7 @|TaskSwitchSimpleIcon #fIcon
Pt0 f19 actionTable 'out=in;
' #txt
Pt0 f19 actionCode 'ivy.case.setCategoryPath("Alpha Company");' #txt
Pt0 f19 security system #txt
Pt0 f19 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Set process&#xD;
code name</name>
    </language>
</elementInfo>
' #txt
Pt0 f19 296 122 112 44 -33 -16 #rect
Pt0 f19 @|StepIcon #fIcon
Pt0 f20 expr out #txt
Pt0 f20 159 144 296 144 #arcP
Pt0 f11 expr data #txt
Pt0 f11 outCond ivp=="TaskA.ivp" #txt
Pt0 f11 719 144 801 144 #arcP
Pt0 f12 617 321 30 30 0 15 #rect
Pt0 f12 @|EndIcon #fIcon
Pt0 f14 outLink viewProcessHistoryOfBetaCompany.ivp #txt
Pt0 f14 inParamDecl '<> param;' #txt
Pt0 f14 requestEnabled true #txt
Pt0 f14 triggerEnabled false #txt
Pt0 f14 callSignature viewProcessHistoryOfBetaCompany() #txt
Pt0 f14 persist false #txt
Pt0 f14 startName 'View Process history of beta company in dialog' #txt
Pt0 f14 taskData 'TaskTriggered.EXPRI=2
TaskTriggered.EXROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.PRI=2
TaskTriggered.ROL=Everybody
TaskTriggered.TYPE=0' #txt
Pt0 f14 caseData businessCase.attach=true #txt
Pt0 f14 showInStartList 1 #txt
Pt0 f14 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>viewProcessHistoryOfBetaCompany.ivp</name>
        <nameStyle>35,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Pt0 f14 @C|.responsibility Everybody #txt
Pt0 f14 129 321 30 30 -108 17 #rect
Pt0 f14 @|StartRequestIcon #fIcon
Pt0 f17 outLink createBetaCompany.ivp #txt
Pt0 f17 inParamDecl '<> param;' #txt
Pt0 f17 requestEnabled true #txt
Pt0 f17 triggerEnabled false #txt
Pt0 f17 callSignature createBetaCompany() #txt
Pt0 f17 persist false #txt
Pt0 f17 startName 'Create Beta Company' #txt
Pt0 f17 taskData 'TaskTriggered.EXPRI=2
TaskTriggered.EXROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.PRI=2
TaskTriggered.ROL=Everybody
TaskTriggered.TYPE=0' #txt
Pt0 f17 caseData 'businessCase.attach=true
case.name=Beta Company' #txt
Pt0 f17 showInStartList 1 #txt
Pt0 f17 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>createBetaCompany.ivp</name>
        <nameStyle>21,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Pt0 f17 @C|.responsibility Everybody #txt
Pt0 f17 129 417 30 30 -65 17 #rect
Pt0 f17 @|StartRequestIcon #fIcon
Pt0 f18 769 417 30 30 0 15 #rect
Pt0 f18 @|EndIcon #fIcon
Pt0 f23 actionTable 'out=in;
' #txt
Pt0 f23 actionCode 'ivy.case.setCategoryPath("Beta Company");' #txt
Pt0 f23 security system #txt
Pt0 f23 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Set process&#xD;
code name</name>
    </language>
</elementInfo>
' #txt
Pt0 f23 296 410 112 44 -33 -16 #rect
Pt0 f23 @|StepIcon #fIcon
Pt0 f25 expr out #txt
Pt0 f25 159 432 296 432 #arcP
Pt0 f22 actionTable 'out=in1;
' #txt
Pt0 f22 outLinks "TaskA.ivp" #txt
Pt0 f22 taskData 'TaskA.EXPRI=2
TaskA.EXROL=Everybody
TaskA.EXTYPE=0
TaskA.NAM=Beta Company
TaskA.PRI=2
TaskA.ROL=Everybody
TaskA.SKIP_TASK_LIST=false
TaskA.TYPE=0' #txt
Pt0 f22 template "" #txt
Pt0 f22 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Beta Company</name>
        <nameStyle>12,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Pt0 f22 529 513 30 30 -40 17 #rect
Pt0 f22 @|TaskSwitchSimpleIcon #fIcon
Pt0 f31 signalCode ch:axonivy:portal:example:createbetacompany #txt
Pt0 f31 attachToBusinessCase true #txt
Pt0 f31 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Signal create Beta Company</name>
        <nameStyle>26,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Pt0 f31 129 513 30 30 -78 17 #rect
Pt0 f31 @|SignalStartEventIcon #fIcon
Pt0 f26 actionTable 'out=in;
' #txt
Pt0 f26 actionCode 'import ch.ivyteam.ivy.process.model.value.SignalCode;

SignalCode code = new SignalCode("ch:axonivy:portal:example:createbetacompany");
ivy.wf.signals().send(code);' #txt
Pt0 f26 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Send signal to create company</name>
    </language>
</elementInfo>
' #txt
Pt0 f26 488 410 176 44 -84 -8 #rect
Pt0 f26 @|StepIcon #fIcon
Pt0 f27 expr out #txt
Pt0 f27 408 432 488 432 #arcP
Pt0 f28 expr out #txt
Pt0 f28 664 432 769 432 #arcP
Pt0 f29 673 513 30 30 0 15 #rect
Pt0 f29 @|EndIcon #fIcon
Pt0 f30 expr data #txt
Pt0 f30 outCond ivp=="TaskA.ivp" #txt
Pt0 f30 559 528 673 528 #arcP
Pt0 f33 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>In this example, we create case for Alpha Company, then create a new page to view the process history of Alpha Company

createAlphaCompany =&gt;  create alpha company business case
viewProcessHistoryOfAlphaCompany =&gt; view process history of alpha company in new page

HOW TO RUN THIS EXAMPLE:
1. Run createAlphaCompany.ivp process
2. Enter viewProcessHistoryOfAlphaCompany.ivp process, process history of Alpha Company will be shown</name>
        <nameStyle>429,5
</nameStyle>
    </language>
</elementInfo>
' #txt
Pt0 f33 872 26 672 140 -331 -64 #rect
Pt0 f33 @|IBIcon #fIcon
Pt0 f34 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>In this example, we create case for Beta Company by sending a signal, then create a dialog to view the process history of Beta Company

createBetaCompany =&gt;  create beta company business case (with signal)
viewProcessHistoryOfBetaCompany =&gt; view process history of beta company in a dialog

HOW TO RUN THIS EXAMPLE:
1. Run createBetaCompany.ivp process
2. Run viewProcessHistoryOfBetaCompany.ivp process, then click "Open process history dialog" button. Process history of Beta company will be shown</name>
        <nameStyle>499,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Pt0 f34 872 362 832 140 -412 -64 #rect
Pt0 f34 @|IBIcon #fIcon
Pt0 f2 dialogId com.axonivy.portal.components.examples.ProcessHistoryExample #txt
Pt0 f2 startMethod start(Boolean,Boolean) #txt
Pt0 f2 requestActionDecl '<Boolean showProcessHistoriesOfAlphaCompany,Boolean showProcessHistoriesOfBetaCompany> param;' #txt
Pt0 f2 requestMappingAction 'param.showProcessHistoriesOfAlphaCompany=true;
param.showProcessHistoriesOfBetaCompany=false;
' #txt
Pt0 f2 responseMappingAction 'out=in;
' #txt
Pt0 f2 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Alpha Company Process History</name>
    </language>
</elementInfo>
' #txt
Pt0 f2 296 26 192 44 -88 -8 #rect
Pt0 f2 @|UserDialogIcon #fIcon
Pt0 f3 159 48 296 48 #arcP
Pt0 f4 488 48 609 48 #arcP
Pt0 f13 dialogId com.axonivy.portal.components.examples.ProcessHistoryExample #txt
Pt0 f13 startMethod start(Boolean,Boolean) #txt
Pt0 f13 requestActionDecl '<Boolean showProcessHistoriesOfAlphaCompany,Boolean showProcessHistoriesOfBetaCompany> param;' #txt
Pt0 f13 requestMappingAction 'param.showProcessHistoriesOfAlphaCompany=false;
param.showProcessHistoriesOfBetaCompany=true;
' #txt
Pt0 f13 responseMappingAction 'out=in;
' #txt
Pt0 f13 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Beta Company Process History</name>
    </language>
</elementInfo>
' #txt
Pt0 f13 296 314 176 44 -85 -8 #rect
Pt0 f13 @|UserDialogIcon #fIcon
Pt0 f15 159 336 296 336 #arcP
Pt0 f16 472 336 617 336 #arcP
Pt0 f16 0 0.49999999999999994 0 0 #arcLabel
Pt0 f5 processCall 'Functional Processes/SetCaseBusinessEntity:call(String)' #txt
Pt0 f5 requestActionDecl '<String businessEntityId> param;' #txt
Pt0 f5 requestMappingAction 'param.businessEntityId="alpha";
' #txt
Pt0 f5 responseMappingAction 'out=in;
' #txt
Pt0 f5 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>SetCaseBusinessEntity</name>
    </language>
</elementInfo>
' #txt
Pt0 f5 480 122 144 44 -65 -8 #rect
Pt0 f5 @|CallSubIcon #fIcon
Pt0 f9 408 144 480 144 #arcP
Pt0 f10 624 144 689 144 #arcP
Pt0 f21 processCall 'Functional Processes/SetCaseBusinessEntity:call(String)' #txt
Pt0 f21 requestActionDecl '<String businessEntityId> param;' #txt
Pt0 f21 requestMappingAction 'param.businessEntityId="beta";
' #txt
Pt0 f21 responseMappingAction 'out=in;
' #txt
Pt0 f21 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>SetCaseBusinessEntity</name>
    </language>
</elementInfo>
' #txt
Pt0 f21 264 506 144 44 -65 -8 #rect
Pt0 f21 @|CallSubIcon #fIcon
Pt0 f24 159 528 264 528 #arcP
Pt0 f32 408 528 529 528 #arcP
>Proto Pt0 .type com.axonivy.portal.components.examples.ProcessHistoryExampleData #txt
>Proto Pt0 .processKind NORMAL #txt
>Proto Pt0 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <swimlaneLabel>Alpha
</swimlaneLabel>
        <swimlaneLabel>Beta</swimlaneLabel>
    </language>
    <swimlaneOrientation>false</swimlaneOrientation>
    <swimlaneSize>232</swimlaneSize>
    <swimlaneSize>352</swimlaneSize>
    <swimlaneColor gradient="false">-1</swimlaneColor>
    <swimlaneColor gradient="false">-1</swimlaneColor>
    <swimlaneType>POOL</swimlaneType>
    <swimlaneType>POOL</swimlaneType>
    <swimlaneSpaceBefore>0</swimlaneSpaceBefore>
    <swimlaneSpaceBefore>24</swimlaneSpaceBefore>
</elementInfo>
' #txt
>Proto Pt0 0 0 32 24 18 0 #rect
>Proto Pt0 @|BIcon #fIcon
Pt0 f7 out f11 tail #connect
Pt0 f11 head f8 mainIn #connect
Pt0 f6 mainOut f20 tail #connect
Pt0 f20 head f19 mainIn #connect
Pt0 f17 mainOut f25 tail #connect
Pt0 f25 head f23 mainIn #connect
Pt0 f23 mainOut f27 tail #connect
Pt0 f27 head f26 mainIn #connect
Pt0 f26 mainOut f28 tail #connect
Pt0 f28 head f18 mainIn #connect
Pt0 f22 out f30 tail #connect
Pt0 f30 head f29 mainIn #connect
Pt0 f0 mainOut f3 tail #connect
Pt0 f3 head f2 mainIn #connect
Pt0 f2 mainOut f4 tail #connect
Pt0 f4 head f1 mainIn #connect
Pt0 f14 mainOut f15 tail #connect
Pt0 f15 head f13 mainIn #connect
Pt0 f13 mainOut f16 tail #connect
Pt0 f16 head f12 mainIn #connect
Pt0 f19 mainOut f9 tail #connect
Pt0 f9 head f5 mainIn #connect
Pt0 f5 mainOut f10 tail #connect
Pt0 f10 head f7 in #connect
Pt0 f31 mainOut f24 tail #connect
Pt0 f24 head f21 mainIn #connect
Pt0 f21 mainOut f32 tail #connect
Pt0 f32 head f22 in #connect
