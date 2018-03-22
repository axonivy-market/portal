[Ivy]
1624C7FF93CE4B4C 3.20 #module
>Proto >Proto Collection #zClass
Pe0 PortalHome Big #zClass
Pe0 B #cInfo
Pe0 #process
Pe0 @TextInP .resExport .resExport #zField
Pe0 @TextInP .type .type #zField
Pe0 @TextInP .processKind .processKind #zField
Pe0 @AnnotationInP-0n ai ai #zField
Pe0 @MessageFlowInP-0n messageIn messageIn #zField
Pe0 @MessageFlowOutP-0n messageOut messageOut #zField
Pe0 @TextInP .xml .xml #zField
Pe0 @TextInP .responsibility .responsibility #zField
Pe0 @StartRequest f0 '' #zField
Pe0 @EndTask f1 '' #zField
Pe0 @RichDialog f3 '' #zField
Pe0 @PushWFArc f4 '' #zField
Pe0 @PushWFArc f2 '' #zField
>Proto Pe0 Pe0 PortalHome #zField
Pe0 f0 outLink basic.ivp #txt
Pe0 f0 type ch.ivyteam.ivy.project.portal.examples.Data #txt
Pe0 f0 inParamDecl '<> param;' #txt
Pe0 f0 actionDecl 'ch.ivyteam.ivy.project.portal.examples.Data out;
' #txt
Pe0 f0 guid 1624C7FF9B2D850D #txt
Pe0 f0 requestEnabled true #txt
Pe0 f0 triggerEnabled false #txt
Pe0 f0 callSignature basic() #txt
Pe0 f0 persist false #txt
Pe0 f0 startName 'Basic customized PortalHome uses DefaultHomePageTemplate.xhtml' #txt
Pe0 f0 taskData 'TaskTriggered.ROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.EXPRI=2
TaskTriggered.TYPE=0
TaskTriggered.PRI=2
TaskTriggered.EXROL=Everybody' #txt
Pe0 f0 caseData businessCase.attach=true #txt
Pe0 f0 showInStartList 1 #txt
Pe0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>basic.ivp</name>
        <nameStyle>9,5,7
</nameStyle>
        <desc>Basic customized PortalHome uses DefaultHomePageTemplate.xhtml</desc>
    </language>
</elementInfo>
' #txt
Pe0 f0 @C|.responsibility Everybody #txt
Pe0 f0 81 49 30 30 -24 17 #rect
Pe0 f0 @|StartRequestIcon #fIcon
Pe0 f1 type ch.ivyteam.ivy.project.portal.examples.Data #txt
Pe0 f1 337 49 30 30 0 15 #rect
Pe0 f1 @|EndIcon #fIcon
Pe0 f3 targetWindow NEW #txt
Pe0 f3 targetDisplay TOP #txt
Pe0 f3 richDialogId ch.ivyteam.ivy.project.portal.examples.PortalHome #txt
Pe0 f3 startMethod start() #txt
Pe0 f3 type ch.ivyteam.ivy.project.portal.examples.Data #txt
Pe0 f3 requestActionDecl '<> param;' #txt
Pe0 f3 responseActionDecl 'ch.ivyteam.ivy.project.portal.examples.Data out;
' #txt
Pe0 f3 responseMappingAction 'out=in;
' #txt
Pe0 f3 isAsynch false #txt
Pe0 f3 isInnerRd false #txt
Pe0 f3 userContext '* ' #txt
Pe0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Open customized 
home page</name>
        <nameStyle>26
</nameStyle>
    </language>
</elementInfo>
' #txt
Pe0 f3 160 42 128 44 -44 -16 #rect
Pe0 f3 @|RichDialogIcon #fIcon
Pe0 f4 expr out #txt
Pe0 f4 111 64 160 64 #arcP
Pe0 f2 expr out #txt
Pe0 f2 288 64 337 64 #arcP
>Proto Pe0 .type ch.ivyteam.ivy.project.portal.examples.Data #txt
>Proto Pe0 .processKind NORMAL #txt
>Proto Pe0 0 0 32 24 18 0 #rect
>Proto Pe0 @|BIcon #fIcon
Pe0 f0 mainOut f4 tail #connect
Pe0 f4 head f3 mainIn #connect
Pe0 f3 mainOut f2 tail #connect
Pe0 f2 head f1 mainIn #connect
