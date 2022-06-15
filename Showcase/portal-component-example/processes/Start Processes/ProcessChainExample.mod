[Ivy]
181602E04C8224B8 7.5.0 #module
>Proto >Proto Collection #zClass
Pe0 ProcessChainExample Big #zClass
Pe0 B #cInfo
Pe0 #process
Pe0 @AnnotationInP-0n ai ai #zField
Pe0 @TextInP .type .type #zField
Pe0 @TextInP .processKind .processKind #zField
Pe0 @TextInP .xml .xml #zField
Pe0 @TextInP .responsibility .responsibility #zField
Pe0 @EndTask f12 '' #zField
Pe0 @StartRequest f10 '' #zField
Pe0 @UserDialog f11 '' #zField
Pe0 @PushWFArc f13 '' #zField
Pe0 @PushWFArc f14 '' #zField
>Proto Pe0 Pe0 ProcessChainExample #zField
Pe0 f12 481 65 30 30 0 15 #rect
Pe0 f12 @|EndIcon #fIcon
Pe0 f10 outLink start.ivp #txt
Pe0 f10 inParamDecl '<> param;' #txt
Pe0 f10 requestEnabled true #txt
Pe0 f10 triggerEnabled false #txt
Pe0 f10 callSignature start() #txt
Pe0 f10 startName 'Process Chain Example' #txt
Pe0 f10 caseData businessCase.attach=true #txt
Pe0 f10 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start.ivp</name>
    </language>
</elementInfo>
' #txt
Pe0 f10 @C|.responsibility Everybody #txt
Pe0 f10 65 65 30 30 -43 11 #rect
Pe0 f10 @|StartRequestIcon #fIcon
Pe0 f11 dialogId examples.ProcessChainExample #txt
Pe0 f11 startMethod start() #txt
Pe0 f11 requestActionDecl '<> param;' #txt
Pe0 f11 responseMappingAction 'out=in;
' #txt
Pe0 f11 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>ProcessChainExample</name>
    </language>
</elementInfo>
' #txt
Pe0 f11 224 58 144 44 -63 -8 #rect
Pe0 f11 @|UserDialogIcon #fIcon
Pe0 f13 368 80 481 80 #arcP
Pe0 f14 95 80 224 80 #arcP
>Proto Pe0 .type examples.ProcessChainExampleData #txt
>Proto Pe0 .processKind NORMAL #txt
>Proto Pe0 0 0 32 24 18 0 #rect
>Proto Pe0 @|BIcon #fIcon
Pe0 f10 mainOut f14 tail #connect
Pe0 f14 head f11 mainIn #connect
Pe0 f11 mainOut f13 tail #connect
Pe0 f13 head f12 mainIn #connect
