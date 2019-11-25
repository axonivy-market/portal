[Ivy]
1657A360018BBA71 7.5.0 #module
>Proto >Proto Collection #zClass
Te0 ToCustomizedHomePage Big #zClass
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
>Proto Te0 Te0 ToCustomizedHomePage #zField
Te0 f0 outLink openCustomizedPortalHome.ivp #txt
Te0 f0 inParamDecl '<> param;' #txt
Te0 f0 requestEnabled true #txt
Te0 f0 triggerEnabled false #txt
Te0 f0 callSignature openCustomizedPortalHome() #txt
Te0 f0 persist false #txt
Te0 f0 startName 'Open customized portal home' #txt
Te0 f0 startDescription 'Open customized portal home' #txt
Te0 f0 taskData 'TaskTriggered.EXPRI=2
TaskTriggered.EXROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.PRI=2
TaskTriggered.ROL=Everybody
TaskTriggered.TYPE=0
TaskTriggered.customFields.STRING.embedInFrame="false"' #txt
Te0 f0 caseData 'businessCase.attach=true
customFields.STRING.embedInFrame="false"' #txt
Te0 f0 showInStartList 1 #txt
Te0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>openCustomizedPortalHome.ivp</name>
    </language>
</elementInfo>
' #txt
Te0 f0 @C|.responsibility Everybody #txt
Te0 f0 81 49 30 30 -89 17 #rect
Te0 f0 @|StartRequestIcon #fIcon
Te0 f1 417 49 30 30 0 15 #rect
Te0 f1 @|EndIcon #fIcon
Te0 f3 dialogId ch.ivyteam.ivy.project.portal.examples.customization.CustomizedHomePage #txt
Te0 f3 startMethod start() #txt
Te0 f3 requestActionDecl '<> param;' #txt
Te0 f3 responseActionDecl 'ch.ivyteam.ivy.project.portal.examples.Data out;
' #txt
Te0 f3 responseMappingAction 'out=in;
' #txt
Te0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Customized Home Page</name>
    </language>
</elementInfo>
' #txt
Te0 f3 232 42 144 44 -67 -8 #rect
Te0 f3 @|UserDialogIcon #fIcon
Te0 f4 expr out #txt
Te0 f4 111 64 232 64 #arcP
Te0 f2 expr out #txt
Te0 f2 376 64 417 64 #arcP
Te0 f5 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>This process demonstrates the home page customization of Portal. It opens a customized Portal dashboard page with:
1. Statistic widget is not rendered.
2. Task widget take the remaining space.
3. Render a custom widget

HOW TO RUN THIS EXAMPLE:
Open openCustomizedPortalHome.ivp in PortalExamples to access to the customized home page.

</name>
        <nameStyle>337,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Te0 f5 464 18 656 156 -324 -72 #rect
Te0 f5 @|IBIcon #fIcon
>Proto Te0 .type ch.ivyteam.ivy.project.portal.examples.Data #txt
>Proto Te0 .processKind NORMAL #txt
>Proto Te0 0 0 32 24 18 0 #rect
>Proto Te0 @|BIcon #fIcon
Te0 f0 mainOut f4 tail #connect
Te0 f4 head f3 mainIn #connect
Te0 f3 mainOut f2 tail #connect
Te0 f2 head f1 mainIn #connect
