[Ivy]
164DB506D12B25CF 7.5.0 #module
>Proto >Proto Collection #zClass
Pt0 ProcessChainTest Big #zClass
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
Pt0 @UserDialog f3 '' #zField
Pt0 @PushWFArc f4 '' #zField
Pt0 @PushWFArc f2 '' #zField
Pt0 @InfoButton f5 '' #zField
>Proto Pt0 Pt0 ProcessChainTest #zField
Pt0 f0 outLink showSampleProcessChain.ivp #txt
Pt0 f0 inParamDecl '<> param;' #txt
Pt0 f0 requestEnabled true #txt
Pt0 f0 triggerEnabled false #txt
Pt0 f0 callSignature showSampleProcessChain() #txt
Pt0 f0 persist false #txt
Pt0 f0 startName 'Show All Sample Process Chains' #txt
Pt0 f0 startDescription 'Show All Sample Process Chains' #txt
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
        <name>showSampleProcessChain.ivp</name>
        <nameStyle>26,5,7
</nameStyle>
        <desc>This process will open a page, which contains 4 process chains.</desc>
    </language>
</elementInfo>
' #txt
Pt0 f0 @C|.responsibility Everybody #txt
Pt0 f0 81 49 30 30 -85 17 #rect
Pt0 f0 @|StartRequestIcon #fIcon
Pt0 f1 481 49 30 30 0 15 #rect
Pt0 f1 @|EndIcon #fIcon
Pt0 f3 dialogId ch.ivyteam.ivy.project.portal.examples.customization.ProcessChainTest #txt
Pt0 f3 startMethod start() #txt
Pt0 f3 requestActionDecl '<> param;' #txt
Pt0 f3 responseActionDecl 'ch.ivyteam.ivy.project.portal.examples.Data out;
' #txt
Pt0 f3 responseMappingAction 'out=in;
' #txt
Pt0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Show All Sample Process Chains</name>
        <nameStyle>30,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Pt0 f3 208 42 192 44 -92 -8 #rect
Pt0 f3 @|UserDialogIcon #fIcon
Pt0 f4 expr out #txt
Pt0 f4 111 64 208 64 #arcP
Pt0 f2 expr out #txt
Pt0 f2 400 64 481 64 #arcP
Pt0 f5 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>This process open a page, which contains all process chain types:
1. shape is circle, direction is horizontal
2. shape is circle, direction is vertical
3. shape is line, direction is horizontal
4. shape is line, direction is vertical</name>
        <nameStyle>66,7
167,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Pt0 f5 160 130 368 92 -181 -40 #rect
Pt0 f5 @|IBIcon #fIcon
>Proto Pt0 .type ch.ivyteam.ivy.project.portal.examples.Data #txt
>Proto Pt0 .processKind NORMAL #txt
>Proto Pt0 0 0 32 24 18 0 #rect
>Proto Pt0 @|BIcon #fIcon
Pt0 f0 mainOut f4 tail #connect
Pt0 f4 head f3 mainIn #connect
Pt0 f3 mainOut f2 tail #connect
Pt0 f2 head f1 mainIn #connect
