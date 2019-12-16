[Ivy]
16EEF13CDA450E67 7.5.0 #module
>Proto >Proto Collection #zClass
Ps0 PortalPages Big #zClass
Ps0 B #cInfo
Ps0 #process
Ps0 @TextInP .type .type #zField
Ps0 @TextInP .processKind .processKind #zField
Ps0 @TextInP .xml .xml #zField
Ps0 @TextInP .responsibility .responsibility #zField
Ps0 @UserDialog f24 '' #zField
Ps0 @StartRequest f76 '' #zField
Ps0 @PushWFArc f33 '' #zField
Ps0 @StartRequest f2 '' #zField
Ps0 @UserDialog f3 '' #zField
Ps0 @PushWFArc f4 '' #zField
Ps0 @UserDialog f5 '' #zField
Ps0 @StartRequest f6 '' #zField
Ps0 @PushWFArc f7 '' #zField
>Proto Ps0 Ps0 PortalPages #zField
Ps0 f24 dialogId ch.ivy.addon.portal.generic.Processes #txt
Ps0 f24 startMethod start() #txt
Ps0 f24 requestActionDecl '<> param;' #txt
Ps0 f24 responseMappingAction 'out=in;
' #txt
Ps0 f24 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>processes</name>
    </language>
</elementInfo>
' #txt
Ps0 f24 192 74 112 44 -29 -8 #rect
Ps0 f24 @|UserDialogIcon #fIcon
Ps0 f76 outLink DefaultProcessStartListPage.ivp #txt
Ps0 f76 inParamDecl '<> param;' #txt
Ps0 f76 requestEnabled true #txt
Ps0 f76 triggerEnabled false #txt
Ps0 f76 callSignature DefaultProcessStartListPage() #txt
Ps0 f76 persist false #txt
Ps0 f76 taskData 'TaskTriggered.EXPRI=2
TaskTriggered.EXROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.PRI=2
TaskTriggered.ROL=Everybody
TaskTriggered.TYPE=0' #txt
Ps0 f76 caseData businessCase.attach=true #txt
Ps0 f76 showInStartList 0 #txt
Ps0 f76 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>DefaultProcessStartListPage.ivp</name>
    </language>
</elementInfo>
' #txt
Ps0 f76 @C|.responsibility Everybody #txt
Ps0 f76 81 81 30 30 -60 17 #rect
Ps0 f76 @|StartRequestIcon #fIcon
Ps0 f33 111 96 192 96 #arcP
Ps0 f2 outLink Error500Page.ivp #txt
Ps0 f2 inParamDecl '<> param;' #txt
Ps0 f2 requestEnabled true #txt
Ps0 f2 triggerEnabled false #txt
Ps0 f2 callSignature Error500Page() #txt
Ps0 f2 caseData businessCase.attach=true #txt
Ps0 f2 showInStartList 0 #txt
Ps0 f2 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Error500Page.ivp</name>
    </language>
</elementInfo>
' #txt
Ps0 f2 @C|.responsibility Everybody #txt
Ps0 f2 81 177 30 30 -44 17 #rect
Ps0 f2 @|StartRequestIcon #fIcon
Ps0 f3 dialogId ch.ivy.addon.portal.error.ErrorPage #txt
Ps0 f3 startMethod start(String) #txt
Ps0 f3 requestActionDecl '<String errorCode> param;' #txt
Ps0 f3 requestMappingAction 'param.errorCode="500";
' #txt
Ps0 f3 responseMappingAction 'out=in;
' #txt
Ps0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>ErrorPage</name>
    </language>
</elementInfo>
' #txt
Ps0 f3 192 170 112 44 -27 -8 #rect
Ps0 f3 @|UserDialogIcon #fIcon
Ps0 f4 111 192 192 192 #arcP
Ps0 f5 dialogId ch.ivy.addon.portal.error.ErrorPage #txt
Ps0 f5 startMethod start(String) #txt
Ps0 f5 requestActionDecl '<String errorCode> param;' #txt
Ps0 f5 requestMappingAction 'param.errorCode="404";
' #txt
Ps0 f5 responseMappingAction 'out=in;
' #txt
Ps0 f5 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>ErrorPage</name>
    </language>
</elementInfo>
' #txt
Ps0 f5 192 266 112 44 -27 -8 #rect
Ps0 f5 @|UserDialogIcon #fIcon
Ps0 f6 outLink Error404Page.ivp #txt
Ps0 f6 inParamDecl '<> param;' #txt
Ps0 f6 requestEnabled true #txt
Ps0 f6 triggerEnabled false #txt
Ps0 f6 callSignature Error404Page() #txt
Ps0 f6 caseData businessCase.attach=true #txt
Ps0 f6 showInStartList 0 #txt
Ps0 f6 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Error404Page.ivp</name>
    </language>
</elementInfo>
' #txt
Ps0 f6 @C|.responsibility Everybody #txt
Ps0 f6 81 273 30 30 -39 17 #rect
Ps0 f6 @|StartRequestIcon #fIcon
Ps0 f7 111 288 192 288 #arcP
>Proto Ps0 .type ch.ivy.addon.portal.generic.PortalPagesData #txt
>Proto Ps0 .processKind NORMAL #txt
>Proto Ps0 0 0 32 24 18 0 #rect
>Proto Ps0 @|BIcon #fIcon
Ps0 f76 mainOut f33 tail #connect
Ps0 f33 head f24 mainIn #connect
Ps0 f2 mainOut f4 tail #connect
Ps0 f4 head f3 mainIn #connect
Ps0 f6 mainOut f7 tail #connect
Ps0 f7 head f5 mainIn #connect
