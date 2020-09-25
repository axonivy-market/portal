[Ivy]
1725474D1C567198 9.2.0 #module
>Proto >Proto Collection #zClass
Ue0 UserProfile Big #zClass
Ue0 B #cInfo
Ue0 #process
Ue0 @TextInP .type .type #zField
Ue0 @TextInP .processKind .processKind #zField
Ue0 @TextInP .xml .xml #zField
Ue0 @TextInP .responsibility .responsibility #zField
Ue0 @StartRequest f0 '' #zField
Ue0 @EndTask f1 '' #zField
Ue0 @UserDialog f3 '' #zField
Ue0 @PushWFArc f4 '' #zField
Ue0 @PushWFArc f2 '' #zField
>Proto Ue0 Ue0 UserProfile #zField
Ue0 f0 outLink UserProfile.ivp #txt
Ue0 f0 inParamDecl '<> param;' #txt
Ue0 f0 requestEnabled true #txt
Ue0 f0 triggerEnabled false #txt
Ue0 f0 callSignature UserProfile() #txt
Ue0 f0 caseData 'businessCase.attach=true
customFields.STRING.embedInFrame="false"' #txt
Ue0 f0 wfuser 1 #txt
Ue0 f0 showInStartList 0 #txt
Ue0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>UserProfile.ivp</name>
    </language>
</elementInfo>
' #txt
Ue0 f0 @C|.responsibility Everybody #txt
Ue0 f0 81 49 30 30 -21 17 #rect
Ue0 f0 @|StartRequestIcon #fIcon
Ue0 f1 337 49 30 30 0 15 #rect
Ue0 f1 @|EndIcon #fIcon
Ue0 f3 dialogId ch.ivy.addon.portalkit.settings.UserProfile #txt
Ue0 f3 startMethod start() #txt
Ue0 f3 requestActionDecl '<> param;' #txt
Ue0 f3 responseMappingAction 'out=in;
' #txt
Ue0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>UserProfile</name>
    </language>
</elementInfo>
' #txt
Ue0 f3 176 42 112 44 -31 -8 #rect
Ue0 f3 @|UserDialogIcon #fIcon
Ue0 f4 111 64 176 64 #arcP
Ue0 f2 288 64 337 64 #arcP
>Proto Ue0 .type ch.ivy.add.portalkit.Data #txt
>Proto Ue0 .processKind NORMAL #txt
>Proto Ue0 0 0 32 24 18 0 #rect
>Proto Ue0 @|BIcon #fIcon
Ue0 f0 mainOut f4 tail #connect
Ue0 f4 head f3 mainIn #connect
Ue0 f3 mainOut f2 tail #connect
Ue0 f2 head f1 mainIn #connect
