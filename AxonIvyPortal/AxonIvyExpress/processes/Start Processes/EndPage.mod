[Ivy]
1600BDF7CC0AE52F 7.5.0 #module
>Proto >Proto Collection #zClass
Ee0 EndPage Big #zClass
Ee0 B #cInfo
Ee0 #process
Ee0 @TextInP .type .type #zField
Ee0 @TextInP .processKind .processKind #zField
Ee0 @AnnotationInP-0n ai ai #zField
Ee0 @MessageFlowInP-0n messageIn messageIn #zField
Ee0 @MessageFlowOutP-0n messageOut messageOut #zField
Ee0 @TextInP .xml .xml #zField
Ee0 @TextInP .responsibility .responsibility #zField
Ee0 @StartRequest f0 '' #zField
Ee0 @EndTask f1 '' #zField
Ee0 @UserDialog f3 '' #zField
Ee0 @PushWFArc f4 '' #zField
Ee0 @PushWFArc f2 '' #zField
>Proto Ee0 Ee0 EndPage #zField
Ee0 f0 outLink start.ivp #txt
Ee0 f0 inParamDecl '<> param;' #txt
Ee0 f0 requestEnabled true #txt
Ee0 f0 triggerEnabled false #txt
Ee0 f0 callSignature start() #txt
Ee0 f0 persist false #txt
Ee0 f0 taskData 'TaskTriggered.EXPRI=2
TaskTriggered.EXROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.PRI=2
TaskTriggered.ROL=Everybody
TaskTriggered.TYPE=0' #txt
Ee0 f0 caseData businessCase.attach=true #txt
Ee0 f0 showInStartList 0 #txt
Ee0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start.ivp</name>
        <nameStyle>9,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ee0 f0 @C|.responsibility Everybody #txt
Ee0 f0 81 49 30 30 -21 17 #rect
Ee0 f0 @|StartRequestIcon #fIcon
Ee0 f1 337 49 30 30 0 15 #rect
Ee0 f1 @|EndIcon #fIcon
Ee0 f3 dialogId ch.ivy.addon.express.generic.EndPage #txt
Ee0 f3 startMethod start() #txt
Ee0 f3 requestActionDecl '<> param;' #txt
Ee0 f3 responseActionDecl 'ch.ivy.addon.express.generic.HandleEndPageData out;
' #txt
Ee0 f3 responseMappingAction 'out=in;
' #txt
Ee0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>express endpage</name>
        <nameStyle>15
</nameStyle>
    </language>
</elementInfo>
' #txt
Ee0 f3 168 42 112 44 -48 -8 #rect
Ee0 f3 @|UserDialogIcon #fIcon
Ee0 f4 expr out #txt
Ee0 f4 111 64 168 64 #arcP
Ee0 f2 expr out #txt
Ee0 f2 280 64 337 64 #arcP
>Proto Ee0 .type ch.ivy.addon.express.generic.HandleEndPageData #txt
>Proto Ee0 .processKind NORMAL #txt
>Proto Ee0 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language/>
</elementInfo>
' #txt
>Proto Ee0 0 0 32 24 18 0 #rect
>Proto Ee0 @|BIcon #fIcon
Ee0 f0 mainOut f4 tail #connect
Ee0 f4 head f3 mainIn #connect
Ee0 f3 mainOut f2 tail #connect
Ee0 f2 head f1 mainIn #connect
