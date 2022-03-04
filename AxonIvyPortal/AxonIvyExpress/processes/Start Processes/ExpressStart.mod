[Ivy]
17F49EF501FB6EEB 7.5.0 #module
>Proto >Proto Collection #zClass
Et0 ExpressStart Big #zClass
Et0 B #cInfo
Et0 #process
Et0 @AnnotationInP-0n ai ai #zField
Et0 @TextInP .type .type #zField
Et0 @TextInP .processKind .processKind #zField
Et0 @TextInP .xml .xml #zField
Et0 @TextInP .responsibility .responsibility #zField
Et0 @StartRequest f0 '' #zField
Et0 @EndTask f1 '' #zField
Et0 @UserDialog f3 '' #zField
Et0 @PushWFArc f4 '' #zField
Et0 @PushWFArc f2 '' #zField
>Proto Et0 Et0 ExpressStart #zField
Et0 f0 outLink startExpressBusinessView.ivp #txt
Et0 f0 inParamDecl '<Long caseId> param;' #txt
Et0 f0 inParamTable 'out.caseId=param.caseId;
' #txt
Et0 f0 requestEnabled true #txt
Et0 f0 triggerEnabled false #txt
Et0 f0 callSignature startExpressBusinessView(Long) #txt
Et0 f0 caseData businessCase.attach=true #txt
Et0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>startExpressBusinessView.ivp</name>
    </language>
</elementInfo>
' #txt
Et0 f0 @C|.responsibility Everybody #txt
Et0 f0 81 49 30 30 -61 21 #rect
Et0 f0 @|StartRequestIcon #fIcon
Et0 f1 401 49 30 30 0 15 #rect
Et0 f1 @|EndIcon #fIcon
Et0 f3 dialogId ch.ivy.addon.express.generic.ExpressBusinessView #txt
Et0 f3 startMethod start(Long) #txt
Et0 f3 requestActionDecl '<Long caseId> param;' #txt
Et0 f3 requestMappingAction 'param.caseId=in.caseId;
' #txt
Et0 f3 responseMappingAction 'out=in;
' #txt
Et0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>ExpressBusinessView</name>
    </language>
</elementInfo>
' #txt
Et0 f3 216 42 128 44 -61 -8 #rect
Et0 f3 @|UserDialogIcon #fIcon
Et0 f4 111 64 216 64 #arcP
Et0 f2 344 64 401 64 #arcP
>Proto Et0 .type gawfs.ExpressStartData #txt
>Proto Et0 .processKind NORMAL #txt
>Proto Et0 0 0 32 24 18 0 #rect
>Proto Et0 @|BIcon #fIcon
Et0 f0 mainOut f4 tail #connect
Et0 f4 head f3 mainIn #connect
Et0 f3 mainOut f2 tail #connect
Et0 f2 head f1 mainIn #connect
