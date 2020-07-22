[Ivy]
17326FC2F133FBEA 7.5.0 #module
>Proto >Proto Collection #zClass
Et0 ExpressStart Big #zClass
Et0 B #cInfo
Et0 #process
Et0 @TextInP .type .type #zField
Et0 @TextInP .processKind .processKind #zField
Et0 @TextInP .xml .xml #zField
Et0 @TextInP .responsibility .responsibility #zField
Et0 @StartRequest f0 '' #zField
Et0 @EndTask f1 '' #zField
Et0 @UserDialog f5 '' #zField
Et0 @PushWFArc f2 '' #zField
Et0 @PushWFArc f3 '' #zField
>Proto Et0 Et0 ExpressStart #zField
Et0 f0 outLink startExpressBusinessView.ivp #txt
Et0 f0 inParamDecl '<Long caseId> param;' #txt
Et0 f0 inParamTable 'out.caseId=param.caseId;
' #txt
Et0 f0 requestEnabled true #txt
Et0 f0 triggerEnabled false #txt
Et0 f0 callSignature startExpressBusinessView(Long) #txt
Et0 f0 caseData businessCase.attach=true #txt
Et0 f0 showInStartList 0 #txt
Et0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>startExpressBusinessView.ivp</name>
    </language>
</elementInfo>
' #txt
Et0 f0 @C|.responsibility Everybody #txt
Et0 f0 81 49 30 30 -69 22 #rect
Et0 f0 @|StartRequestIcon #fIcon
Et0 f1 625 49 30 30 0 15 #rect
Et0 f1 @|EndIcon #fIcon
Et0 f5 dialogId ch.ivy.addon.express.generic.ExpressBusinessView #txt
Et0 f5 startMethod start(Long) #txt
Et0 f5 requestActionDecl '<Long caseId> param;' #txt
Et0 f5 requestMappingAction 'param.caseId=in.caseId;
' #txt
Et0 f5 responseMappingAction 'out=in;
' #txt
Et0 f5 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Open ExpressBusinessView</name>
    </language>
</elementInfo>
' #txt
Et0 f5 264 42 176 44 -78 -8 #rect
Et0 f5 @|UserDialogIcon #fIcon
Et0 f2 440 64 625 64 #arcP
Et0 f3 111 64 264 64 #arcP
>Proto Et0 .type gawfs.ExpressStartData #txt
>Proto Et0 .processKind NORMAL #txt
>Proto Et0 0 0 32 24 18 0 #rect
>Proto Et0 @|BIcon #fIcon
Et0 f5 mainOut f2 tail #connect
Et0 f2 head f1 mainIn #connect
Et0 f0 mainOut f3 tail #connect
Et0 f3 head f5 mainIn #connect
