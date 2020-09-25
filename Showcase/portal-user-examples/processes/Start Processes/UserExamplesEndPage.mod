[Ivy]
1709A97D25EAB86E 9.2.0 #module
>Proto >Proto Collection #zClass
Ce0 UserExamplesEndPage Big #zClass
Ce0 B #cInfo
Ce0 #process
Ce0 @TextInP .type .type #zField
Ce0 @TextInP .processKind .processKind #zField
Ce0 @TextInP .xml .xml #zField
Ce0 @TextInP .responsibility .responsibility #zField
Ce0 @StartRequest f0 '' #zField
Ce0 @UserDialog f3 '' #zField
Ce0 @EndTask f1 '' #zField
Ce0 @PushWFArc f2 '' #zField
Ce0 @PushWFArc f4 '' #zField
>Proto Ce0 Ce0 UserExamplesEndPage #zField
Ce0 f0 outLink start.ivp #txt
Ce0 f0 inParamDecl '<Long caseId> param;' #txt
Ce0 f0 inParamTable 'out.caseId=param.caseId;
' #txt
Ce0 f0 requestEnabled true #txt
Ce0 f0 triggerEnabled false #txt
Ce0 f0 callSignature start(Long) #txt
Ce0 f0 caseData businessCase.attach=true #txt
Ce0 f0 showInStartList 0 #txt
Ce0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start.ivp</name>
    </language>
</elementInfo>
' #txt
Ce0 f0 @C|.responsibility Everybody #txt
Ce0 f0 73 73 30 30 -21 17 #rect
Ce0 f0 @|StartRequestIcon #fIcon
Ce0 f3 dialogId com.axonivy.portal.userexamples.credit.CustomEndPage #txt
Ce0 f3 startMethod start(Long) #txt
Ce0 f3 requestActionDecl '<Long caseId> param;' #txt
Ce0 f3 requestMappingAction 'param.caseId=in.caseId;
' #txt
Ce0 f3 responseMappingAction 'out=in;
' #txt
Ce0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>CustomEndPage</name>
    </language>
</elementInfo>
' #txt
Ce0 f3 224 66 112 44 -47 -8 #rect
Ce0 f3 @|UserDialogIcon #fIcon
Ce0 f1 393 73 30 30 0 15 #rect
Ce0 f1 @|EndIcon #fIcon
Ce0 f2 336 88 393 88 #arcP
Ce0 f4 103 88 224 88 #arcP
>Proto Ce0 .type com.axonivy.portal.userexamples.credit.HandleEndPageData #txt
>Proto Ce0 .processKind NORMAL #txt
>Proto Ce0 0 0 32 24 18 0 #rect
>Proto Ce0 @|BIcon #fIcon
Ce0 f3 mainOut f2 tail #connect
Ce0 f2 head f1 mainIn #connect
Ce0 f0 mainOut f4 tail #connect
Ce0 f4 head f3 mainIn #connect
