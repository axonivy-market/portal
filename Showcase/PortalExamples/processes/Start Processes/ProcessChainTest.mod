[Ivy]
164DB506D12B25CF 3.23 #module
>Proto >Proto Collection #zClass
Pt0 ProcessChainTest Big #zClass
Pt0 B #cInfo
Pt0 #process
Pt0 @TextInP .resExport .resExport #zField
Pt0 @TextInP .type .type #zField
Pt0 @TextInP .processKind .processKind #zField
Pt0 @AnnotationInP-0n ai ai #zField
Pt0 @MessageFlowInP-0n messageIn messageIn #zField
Pt0 @MessageFlowOutP-0n messageOut messageOut #zField
Pt0 @TextInP .xml .xml #zField
Pt0 @TextInP .responsibility .responsibility #zField
Pt0 @StartRequest f0 '' #zField
Pt0 @EndTask f1 '' #zField
Pt0 @RichDialog f3 '' #zField
Pt0 @PushWFArc f4 '' #zField
Pt0 @PushWFArc f2 '' #zField
>Proto Pt0 Pt0 ProcessChainTest #zField
Pt0 f0 outLink start.ivp #txt
Pt0 f0 type ch.ivyteam.ivy.project.portal.examples.Data #txt
Pt0 f0 inParamDecl '<> param;' #txt
Pt0 f0 actionDecl 'ch.ivyteam.ivy.project.portal.examples.Data out;
' #txt
Pt0 f0 guid 164DB506D426209F #txt
Pt0 f0 requestEnabled true #txt
Pt0 f0 triggerEnabled false #txt
Pt0 f0 callSignature start() #txt
Pt0 f0 caseData businessCase.attach=true #txt
Pt0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start.ivp</name>
    </language>
</elementInfo>
' #txt
Pt0 f0 @C|.responsibility Everybody #txt
Pt0 f0 81 49 30 30 -21 17 #rect
Pt0 f0 @|StartRequestIcon #fIcon
Pt0 f1 type ch.ivyteam.ivy.project.portal.examples.Data #txt
Pt0 f1 337 49 30 30 0 15 #rect
Pt0 f1 @|EndIcon #fIcon
Pt0 f3 targetWindow NEW #txt
Pt0 f3 targetDisplay TOP #txt
Pt0 f3 richDialogId ch.ivyteam.ivy.project.portal.examples.customization.ProcessChainTest #txt
Pt0 f3 startMethod start() #txt
Pt0 f3 type ch.ivyteam.ivy.project.portal.examples.Data #txt
Pt0 f3 requestActionDecl '<> param;' #txt
Pt0 f3 responseActionDecl 'ch.ivyteam.ivy.project.portal.examples.Data out;
' #txt
Pt0 f3 responseMappingAction 'out=in;
' #txt
Pt0 f3 isAsynch false #txt
Pt0 f3 isInnerRd false #txt
Pt0 f3 userContext '* ' #txt
Pt0 f3 168 42 112 44 0 -8 #rect
Pt0 f3 @|RichDialogIcon #fIcon
Pt0 f4 expr out #txt
Pt0 f4 111 64 168 64 #arcP
Pt0 f2 expr out #txt
Pt0 f2 280 64 337 64 #arcP
>Proto Pt0 .type ch.ivyteam.ivy.project.portal.examples.Data #txt
>Proto Pt0 .processKind NORMAL #txt
>Proto Pt0 0 0 32 24 18 0 #rect
>Proto Pt0 @|BIcon #fIcon
Pt0 f0 mainOut f4 tail #connect
Pt0 f4 head f3 mainIn #connect
Pt0 f3 mainOut f2 tail #connect
Pt0 f2 head f1 mainIn #connect
