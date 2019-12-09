[Ivy]
16ECF1943532D925 7.5.0 #module
>Proto >Proto Collection #zClass
Dm0 Diagram Big #zClass
Dm0 B #cInfo
Dm0 #process
Dm0 @TextInP .type .type #zField
Dm0 @TextInP .processKind .processKind #zField
Dm0 @TextInP .xml .xml #zField
Dm0 @TextInP .responsibility .responsibility #zField
Dm0 @StartRequest f0 '' #zField
Dm0 @EndTask f1 '' #zField
Dm0 @InfoButton f3 '' #zField
Dm0 @UserDialog f4 '' #zField
Dm0 @PushWFArc f5 '' #zField
Dm0 @PushWFArc f2 '' #zField
>Proto Dm0 Dm0 Diagram #zField
Dm0 f0 outLink start.ivp #txt
Dm0 f0 inParamDecl '<> param;' #txt
Dm0 f0 requestEnabled true #txt
Dm0 f0 triggerEnabled false #txt
Dm0 f0 callSignature start() #txt
Dm0 f0 startName Diagram #txt
Dm0 f0 caseData 'businessCase.attach=true
case.name=Diagram' #txt
Dm0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start.ivp</name>
    </language>
</elementInfo>
' #txt
Dm0 f0 @C|.responsibility Everybody #txt
Dm0 f0 81 49 30 30 -20 17 #rect
Dm0 f0 @|StartRequestIcon #fIcon
Dm0 f1 337 49 30 30 0 15 #rect
Dm0 f1 @|EndIcon #fIcon
Dm0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Test: Diagram in TaskTemplate + IFrame + Firefox</name>
    </language>
</elementInfo>
' #txt
Dm0 f3 96 129 272 30 -131 -8 #rect
Dm0 f3 @|IBIcon #fIcon
Dm0 f4 dialogId internaltest.ui.Diagram #txt
Dm0 f4 startMethod start() #txt
Dm0 f4 requestActionDecl '<> param;' #txt
Dm0 f4 responseMappingAction 'out=in;
' #txt
Dm0 f4 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Diagram</name>
    </language>
</elementInfo>
' #txt
Dm0 f4 168 42 112 44 -22 -8 #rect
Dm0 f4 @|UserDialogIcon #fIcon
Dm0 f5 111 64 168 64 #arcP
Dm0 f2 280 64 337 64 #arcP
>Proto Dm0 .type internaltest.Data #txt
>Proto Dm0 .processKind NORMAL #txt
>Proto Dm0 0 0 32 24 18 0 #rect
>Proto Dm0 @|BIcon #fIcon
Dm0 f0 mainOut f5 tail #connect
Dm0 f5 head f4 mainIn #connect
Dm0 f4 mainOut f2 tail #connect
Dm0 f2 head f1 mainIn #connect
