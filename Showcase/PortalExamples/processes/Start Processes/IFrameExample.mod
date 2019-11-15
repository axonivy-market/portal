[Ivy]
16E5DB746865BCEC 7.5.0 #module
>Proto >Proto Collection #zClass
Ie0 IFrameExample Big #zClass
Ie0 B #cInfo
Ie0 #process
Ie0 @TextInP .type .type #zField
Ie0 @TextInP .processKind .processKind #zField
Ie0 @TextInP .xml .xml #zField
Ie0 @TextInP .responsibility .responsibility #zField
Ie0 @TaskSwitch f20 '' #zField
Ie0 @UserDialog f21 '' #zField
Ie0 @EndTask f13 '' #zField
Ie0 @UserDialog f15 '' #zField
Ie0 @StartRequest f12 '' #zField
Ie0 @PushWFArc f24 '' #zField
Ie0 @TkArc f22 '' #zField
Ie0 @PushWFArc f19 '' #zField
Ie0 @PushWFArc f14 '' #zField
>Proto Ie0 Ie0 IFrameExample #zField
Ie0 f20 actionTable 'out=in1;
' #txt
Ie0 f20 outLinks "TaskA.ivp" #txt
Ie0 f20 taskData 'TaskA.NAM=Approve Investment' #txt
Ie0 f20 template "" #txt
Ie0 f20 368 80 32 32 0 16 #rect
Ie0 f20 @|TaskSwitchIcon #fIcon
Ie0 f21 dialogId ch.ivyteam.ivy.project.portal.examples.testdata.CreateInvestment #txt
Ie0 f21 startMethod start() #txt
Ie0 f21 requestActionDecl '<> param;' #txt
Ie0 f21 responseActionDecl 'investment.manae.Data out;
' #txt
Ie0 f21 responseMappingAction 'out=in;
out.investment=result.investment;
' #txt
Ie0 f21 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Create Investment</name>
    </language>
</elementInfo>
' #txt
Ie0 f21 192 74 112 44 -50 -8 #rect
Ie0 f21 @|UserDialogIcon #fIcon
Ie0 f13 657 81 30 30 0 15 #rect
Ie0 f13 @|EndIcon #fIcon
Ie0 f15 dialogId ch.ivyteam.ivy.project.portal.examples.testdata.Approve #txt
Ie0 f15 startMethod start(ch.ivyteam.ivy.project.portal.examples.Investment) #txt
Ie0 f15 requestActionDecl '<ch.ivyteam.ivy.project.portal.examples.Investment investment> param;' #txt
Ie0 f15 requestMappingAction 'param.investment=in.investment;
' #txt
Ie0 f15 responseActionDecl 'investment.manae.Data out;
' #txt
Ie0 f15 responseMappingAction 'out=in;
' #txt
Ie0 f15 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Approve</name>
    </language>
</elementInfo>
' #txt
Ie0 f15 472 74 112 44 -22 -8 #rect
Ie0 f15 @|UserDialogIcon #fIcon
Ie0 f12 outLink CreateInvestment.ivp #txt
Ie0 f12 inParamDecl '<> param;' #txt
Ie0 f12 requestEnabled true #txt
Ie0 f12 triggerEnabled false #txt
Ie0 f12 callSignature CreateInvestment() #txt
Ie0 f12 persist false #txt
Ie0 f12 startName 'Create Investment (used for IFrameTaskTemplate)' #txt
Ie0 f12 caseData 'businessCase.attach=true
case.name=Investment Creation' #txt
Ie0 f12 showInStartList 1 #txt
Ie0 f12 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>CreateInvestment.ivp</name>
    </language>
</elementInfo>
' #txt
Ie0 f12 @C|.responsibility Everybody #txt
Ie0 f12 81 81 30 30 -50 19 #rect
Ie0 f12 @|StartRequestIcon #fIcon
Ie0 f24 expr data #txt
Ie0 f24 outCond ivp=="TaskA.ivp" #txt
Ie0 f24 400 96 472 96 #arcP
Ie0 f22 expr out #txt
Ie0 f22 var in1 #txt
Ie0 f22 304 96 368 96 #arcP
Ie0 f19 expr out #txt
Ie0 f19 111 96 192 96 #arcP
Ie0 f14 expr out #txt
Ie0 f14 584 96 657 96 #arcP
>Proto Ie0 .type ch.ivyteam.ivy.project.portal.examples.IFrameExampleData #txt
>Proto Ie0 .processKind NORMAL #txt
>Proto Ie0 0 0 32 24 18 0 #rect
>Proto Ie0 @|BIcon #fIcon
Ie0 f12 mainOut f19 tail #connect
Ie0 f19 head f21 mainIn #connect
Ie0 f21 mainOut f22 tail #connect
Ie0 f22 head f20 in #connect
Ie0 f20 out f24 tail #connect
Ie0 f24 head f15 mainIn #connect
Ie0 f15 mainOut f14 tail #connect
Ie0 f14 head f13 mainIn #connect
