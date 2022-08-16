[Ivy]
18160447D1373538 7.5.0 #module
>Proto >Proto Collection #zClass
De0 DocumentTableExample Big #zClass
De0 B #cInfo
De0 #process
De0 @AnnotationInP-0n ai ai #zField
De0 @TextInP .type .type #zField
De0 @TextInP .processKind .processKind #zField
De0 @TextInP .xml .xml #zField
De0 @TextInP .responsibility .responsibility #zField
De0 @StartRequest f3 '' #zField
De0 @EndTask f4 '' #zField
De0 @StartRequest f0 '' #zField
De0 @UserDialog f6 '' #zField
De0 @EndTask f1 '' #zField
De0 @PushWFArc f2 '' #zField
De0 @PushWFArc f7 '' #zField
De0 @UserDialog f8 '' #zField
De0 @PushWFArc f9 '' #zField
De0 @PushWFArc f5 '' #zField
>Proto De0 De0 DocumentTableExample #zField
De0 f3 outLink showDefaultDocumentTableExample.ivp #txt
De0 f3 inParamDecl '<> param;' #txt
De0 f3 requestEnabled true #txt
De0 f3 triggerEnabled false #txt
De0 f3 callSignature showDefaultDocumentTableExample() #txt
De0 f3 startName 'Show Default Document Table Example' #txt
De0 f3 caseData businessCase.attach=true #txt
De0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>showDefaultDocumentTableExample.ivp</name>
    </language>
</elementInfo>
' #txt
De0 f3 @C|.responsibility Everybody #txt
De0 f3 97 65 30 30 -98 18 #rect
De0 f3 @|StartRequestIcon #fIcon
De0 f4 545 65 30 30 0 15 #rect
De0 f4 @|EndIcon #fIcon
De0 f0 outLink showCustomizedDocumentTableExample.ivp #txt
De0 f0 inParamDecl '<> param;' #txt
De0 f0 requestEnabled true #txt
De0 f0 triggerEnabled false #txt
De0 f0 callSignature showCustomizedDocumentTableExample() #txt
De0 f0 startName 'Show Customized Document Table Example' #txt
De0 f0 caseData businessCase.attach=true #txt
De0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>showCustomizedDocumentTableExample.ivp</name>
    </language>
</elementInfo>
' #txt
De0 f0 @C|.responsibility Everybody #txt
De0 f0 97 193 30 30 -99 19 #rect
De0 f0 @|StartRequestIcon #fIcon
De0 f6 dialogId com.axonivy.portal.components.examples.CustomizedDocumentTableExample #txt
De0 f6 startMethod start() #txt
De0 f6 requestActionDecl '<> param;' #txt
De0 f6 responseMappingAction 'out=in;
' #txt
De0 f6 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>CustomizedDocumentTableExample</name>
    </language>
</elementInfo>
' #txt
De0 f6 232 186 208 44 -101 -8 #rect
De0 f6 @|UserDialogIcon #fIcon
De0 f1 545 193 30 30 0 15 #rect
De0 f1 @|EndIcon #fIcon
De0 f2 440 208 545 208 #arcP
De0 f7 127 208 232 208 #arcP
De0 f8 dialogId com.axonivy.portal.components.examples.DocumentTableExample #txt
De0 f8 startMethod start() #txt
De0 f8 requestActionDecl '<> param;' #txt
De0 f8 responseMappingAction 'out=in;
' #txt
De0 f8 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>DocumentTableExample</name>
    </language>
</elementInfo>
' #txt
De0 f8 228 56 216 48 -68 -8 #rect
De0 f8 @|UserDialogIcon #fIcon
De0 f9 127 80 228 80 #arcP
De0 f5 444 80 545 80 #arcP
>Proto De0 .type com.axonivy.portal.components.examples.DocumentTableExampleData #txt
>Proto De0 .processKind NORMAL #txt
>Proto De0 0 0 32 24 18 0 #rect
>Proto De0 @|BIcon #fIcon
De0 f0 mainOut f7 tail #connect
De0 f7 head f6 mainIn #connect
De0 f6 mainOut f2 tail #connect
De0 f2 head f1 mainIn #connect
De0 f3 mainOut f9 tail #connect
De0 f9 head f8 mainIn #connect
De0 f8 mainOut f5 tail #connect
De0 f5 head f4 mainIn #connect
