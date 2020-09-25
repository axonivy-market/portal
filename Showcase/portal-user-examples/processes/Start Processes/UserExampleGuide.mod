[Ivy]
17236DB1D3DA14C0 9.2.0 #module
>Proto >Proto Collection #zClass
Ue0 UserExampleGuide Big #zClass
Ue0 B #cInfo
Ue0 #process
Ue0 @TextInP .type .type #zField
Ue0 @TextInP .processKind .processKind #zField
Ue0 @TextInP .xml .xml #zField
Ue0 @TextInP .responsibility .responsibility #zField
Ue0 @StartRequest f0 '' #zField
Ue0 @EndTask f1 '' #zField
Ue0 @UserDialog f3 '' #zField
Ue0 @PushWFArc f2 '' #zField
Ue0 @PushWFArc f11 '' #zField
>Proto Ue0 Ue0 UserExampleGuide #zField
Ue0 f0 outLink userExampleGuide.ivp #txt
Ue0 f0 inParamDecl '<> param;' #txt
Ue0 f0 requestEnabled true #txt
Ue0 f0 triggerEnabled false #txt
Ue0 f0 callSignature userExampleGuide() #txt
Ue0 f0 startName <%=ivy.cms.co("/Processes/UserExampleGuide/name")%> #txt
Ue0 f0 startDescription <%=ivy.cms.co("/Processes/UserExampleGuide/processDescription")%> #txt
Ue0 f0 caseData 'businessCase.attach=true
customFields.STRING.embedInFrame="false"' #txt
Ue0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>userExampleGuide.ivp</name>
    </language>
</elementInfo>
' #txt
Ue0 f0 @C|.responsibility Everybody #txt
Ue0 f0 81 49 30 30 -21 17 #rect
Ue0 f0 @|StartRequestIcon #fIcon
Ue0 f1 721 49 30 30 0 15 #rect
Ue0 f1 @|EndIcon #fIcon
Ue0 f3 dialogId com.axonivy.portal.userexamples.ExampleHomePage #txt
Ue0 f3 startMethod start() #txt
Ue0 f3 requestActionDecl '<> param;' #txt
Ue0 f3 responseMappingAction 'out=in;
' #txt
Ue0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>ExampleHomePage</name>
    </language>
</elementInfo>
' #txt
Ue0 f3 296 42 128 44 -55 -8 #rect
Ue0 f3 @|UserDialogIcon #fIcon
Ue0 f2 424 64 721 64 #arcP
Ue0 f11 111 64 296 64 #arcP
>Proto Ue0 .type com.axonivy.portal.userexamples.UserExampleGuideData #txt
>Proto Ue0 .processKind NORMAL #txt
>Proto Ue0 0 0 32 24 18 0 #rect
>Proto Ue0 @|BIcon #fIcon
Ue0 f3 mainOut f2 tail #connect
Ue0 f2 head f1 mainIn #connect
Ue0 f0 mainOut f11 tail #connect
Ue0 f11 head f3 mainIn #connect
