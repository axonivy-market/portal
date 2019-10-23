[Ivy]
1657B2041502A14A 7.5.0 #module
>Proto >Proto Collection #zClass
Te0 ToCustomizedLoginPage Big #zClass
Te0 B #cInfo
Te0 #process
Te0 @TextInP .type .type #zField
Te0 @TextInP .processKind .processKind #zField
Te0 @AnnotationInP-0n ai ai #zField
Te0 @MessageFlowInP-0n messageIn messageIn #zField
Te0 @MessageFlowOutP-0n messageOut messageOut #zField
Te0 @TextInP .xml .xml #zField
Te0 @TextInP .responsibility .responsibility #zField
Te0 @StartRequest f0 '' #zField
Te0 @EndTask f1 '' #zField
Te0 @UserDialog f3 '' #zField
Te0 @PushWFArc f4 '' #zField
Te0 @PushWFArc f2 '' #zField
Te0 @InfoButton f5 '' #zField
>Proto Te0 Te0 ToCustomizedLoginPage #zField
Te0 f0 outLink toPageWithCustomLoginPage.ivp #txt
Te0 f0 inParamDecl '<> param;' #txt
Te0 f0 requestEnabled true #txt
Te0 f0 triggerEnabled false #txt
Te0 f0 callSignature toPageWithCustomLoginPage() #txt
Te0 f0 persist false #txt
Te0 f0 startName 'Open page with custom login' #txt
Te0 f0 taskData 'TaskTriggered.EXPRI=2
TaskTriggered.EXROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.PRI=2
TaskTriggered.ROL=Everybody
TaskTriggered.TYPE=0' #txt
Te0 f0 caseData businessCase.attach=true #txt
Te0 f0 showInStartList 1 #txt
Te0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>toPageWithCustomLoginPage.ivp</name>
    </language>
</elementInfo>
' #txt
Te0 f0 @C|.responsibility Everybody #txt
Te0 f0 81 49 30 30 -92 17 #rect
Te0 f0 @|StartRequestIcon #fIcon
Te0 f1 433 49 30 30 0 15 #rect
Te0 f1 @|EndIcon #fIcon
Te0 f3 dialogId ch.ivyteam.ivy.project.portal.examples.WelcomePage #txt
Te0 f3 startMethod start() #txt
Te0 f3 requestActionDecl '<> param;' #txt
Te0 f3 responseActionDecl 'ch.ivyteam.ivy.project.portal.examples.Data out;
' #txt
Te0 f3 responseMappingAction 'out=in;
' #txt
Te0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>To Page With Customized Login</name>
    </language>
</elementInfo>
' #txt
Te0 f3 208 42 192 44 -88 -8 #rect
Te0 f3 @|UserDialogIcon #fIcon
Te0 f4 expr out #txt
Te0 f4 111 64 208 64 #arcP
Te0 f2 expr out #txt
Te0 f2 400 64 433 64 #arcP
Te0 f5 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>This process demonstrates the login page customization of Portal. It simply open a welcome page which when required to login will open a customized login page

HOW TO RUN THIS EXAMPLE:
Open toPageWithCustomLoginPage.ivp in PortalExamples without a login user in session, the cusomized login page will be opened.</name>
        <nameStyle>311,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Te0 f5 522 29 896 76 -442 -32 #rect
Te0 f5 @|IBIcon #fIcon
>Proto Te0 .type ch.ivyteam.ivy.project.portal.examples.Data #txt
>Proto Te0 .processKind NORMAL #txt
>Proto Te0 0 0 32 24 18 0 #rect
>Proto Te0 @|BIcon #fIcon
Te0 f0 mainOut f4 tail #connect
Te0 f4 head f3 mainIn #connect
Te0 f3 mainOut f2 tail #connect
Te0 f2 head f1 mainIn #connect
