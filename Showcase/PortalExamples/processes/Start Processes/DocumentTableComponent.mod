[Ivy]
16B447235433958E 7.5.0 #module
>Proto >Proto Collection #zClass
Dt0 DocumentTableComponent Big #zClass
Dt0 B #cInfo
Dt0 #process
Dt0 @TextInP .type .type #zField
Dt0 @TextInP .processKind .processKind #zField
Dt0 @AnnotationInP-0n ai ai #zField
Dt0 @MessageFlowInP-0n messageIn messageIn #zField
Dt0 @MessageFlowOutP-0n messageOut messageOut #zField
Dt0 @TextInP .xml .xml #zField
Dt0 @TextInP .responsibility .responsibility #zField
Dt0 @StartRequest f0 '' #zField
Dt0 @EndTask f1 '' #zField
Dt0 @UserDialog f3 '' #zField
Dt0 @PushWFArc f4 '' #zField
Dt0 @PushWFArc f2 '' #zField
Dt0 @EndTask f5 '' #zField
Dt0 @UserDialog f7 '' #zField
Dt0 @PushWFArc f9 '' #zField
Dt0 @StartRequest f10 '' #zField
Dt0 @PushWFArc f6 '' #zField
>Proto Dt0 Dt0 DocumentTableComponent #zField
Dt0 f0 outLink openDocumentTable.ivp #txt
Dt0 f0 inParamDecl '<> param;' #txt
Dt0 f0 requestEnabled true #txt
Dt0 f0 triggerEnabled false #txt
Dt0 f0 callSignature openDocumentTable() #txt
Dt0 f0 persist false #txt
Dt0 f0 startName 'Document Table Usage' #txt
Dt0 f0 startDescription 'Open Document Table in Portal' #txt
Dt0 f0 taskData TaskTriggered.customFields.STRING.embedInFrame="false" #txt
Dt0 f0 caseData businessCase.attach=true #txt
Dt0 f0 showInStartList 1 #txt
Dt0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>openDocumentTable.ivp</name>
    </language>
</elementInfo>
' #txt
Dt0 f0 @C|.responsibility Everybody #txt
Dt0 f0 81 49 30 30 -54 22 #rect
Dt0 f0 @|StartRequestIcon #fIcon
Dt0 f1 337 49 30 30 0 15 #rect
Dt0 f1 @|EndIcon #fIcon
Dt0 f3 dialogId ch.ivyteam.ivy.project.portal.examples.DocumentTableUsage #txt
Dt0 f3 startMethod start() #txt
Dt0 f3 requestActionDecl '<> param;' #txt
Dt0 f3 responseActionDecl 'ch.ivyteam.ivy.project.portal.examples.DocumentTableComponentData out;
' #txt
Dt0 f3 responseMappingAction 'out=in;
' #txt
Dt0 f3 168 42 112 44 0 -8 #rect
Dt0 f3 @|UserDialogIcon #fIcon
Dt0 f4 expr out #txt
Dt0 f4 111 64 168 64 #arcP
Dt0 f2 expr out #txt
Dt0 f2 280 64 337 64 #arcP
Dt0 f5 337 177 30 30 0 15 #rect
Dt0 f5 @|EndIcon #fIcon
Dt0 f7 dialogId ch.ivyteam.ivy.project.portal.examples.DocumentTableUsageIframe #txt
Dt0 f7 startMethod start() #txt
Dt0 f7 requestActionDecl '<> param;' #txt
Dt0 f7 responseActionDecl 'ch.ivyteam.ivy.project.portal.examples.DocumentTableComponentData out;
' #txt
Dt0 f7 responseMappingAction 'out=in;
' #txt
Dt0 f7 168 170 112 44 0 -8 #rect
Dt0 f7 @|UserDialogIcon #fIcon
Dt0 f9 expr out #txt
Dt0 f9 280 192 337 192 #arcP
Dt0 f10 outLink openDocumentTableInIframe.ivp #txt
Dt0 f10 inParamDecl '<> param;' #txt
Dt0 f10 requestEnabled true #txt
Dt0 f10 triggerEnabled false #txt
Dt0 f10 callSignature openDocumentTableInIframe() #txt
Dt0 f10 persist false #txt
Dt0 f10 startName 'Document Table Usage in Iframe' #txt
Dt0 f10 startDescription 'Open Document Table in Iframe' #txt
Dt0 f10 taskData TaskTriggered.customFields.STRING.embedInFrame="false" #txt
Dt0 f10 caseData businessCase.attach=true #txt
Dt0 f10 showInStartList 1 #txt
Dt0 f10 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>openDocumentTableInIframe.ivp</name>
    </language>
</elementInfo>
' #txt
Dt0 f10 @C|.responsibility Everybody #txt
Dt0 f10 81 177 30 30 -66 28 #rect
Dt0 f10 @|StartRequestIcon #fIcon
Dt0 f6 111 192 168 192 #arcP
>Proto Dt0 .type ch.ivyteam.ivy.project.portal.examples.DocumentTableComponentData #txt
>Proto Dt0 .processKind NORMAL #txt
>Proto Dt0 0 0 32 24 18 0 #rect
>Proto Dt0 @|BIcon #fIcon
Dt0 f0 mainOut f4 tail #connect
Dt0 f4 head f3 mainIn #connect
Dt0 f3 mainOut f2 tail #connect
Dt0 f2 head f1 mainIn #connect
Dt0 f7 mainOut f9 tail #connect
Dt0 f9 head f5 mainIn #connect
Dt0 f10 mainOut f6 tail #connect
Dt0 f6 head f7 mainIn #connect
