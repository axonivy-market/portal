[Ivy]
16385B084D349718 3.23 #module
>Proto >Proto Collection #zClass
Ic0 InternalSupportStatistic Big #zClass
Ic0 B #cInfo
Ic0 #process
Ic0 @TextInP .resExport .resExport #zField
Ic0 @TextInP .type .type #zField
Ic0 @TextInP .processKind .processKind #zField
Ic0 @AnnotationInP-0n ai ai #zField
Ic0 @MessageFlowInP-0n messageIn messageIn #zField
Ic0 @MessageFlowOutP-0n messageOut messageOut #zField
Ic0 @TextInP .xml .xml #zField
Ic0 @TextInP .responsibility .responsibility #zField
Ic0 @StartRequest f0 '' #zField
Ic0 @EndTask f1 '' #zField
Ic0 @RichDialog f3 '' #zField
Ic0 @PushWFArc f4 '' #zField
Ic0 @PushWFArc f2 '' #zField
>Proto Ic0 Ic0 InternalSupportStatistic #zField
Ic0 f0 outLink start.ivp #txt
Ic0 f0 type internaltest.InternalSupportStatisticData #txt
Ic0 f0 inParamDecl '<> param;' #txt
Ic0 f0 actionDecl 'internaltest.InternalSupportStatisticData out;
' #txt
Ic0 f0 guid 16385B084D8282B8 #txt
Ic0 f0 requestEnabled true #txt
Ic0 f0 triggerEnabled false #txt
Ic0 f0 callSignature start() #txt
Ic0 f0 caseData businessCase.attach=true #txt
Ic0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start.ivp</name>
    </language>
</elementInfo>
' #txt
Ic0 f0 @C|.responsibility Everybody #txt
Ic0 f0 81 49 30 30 -21 17 #rect
Ic0 f0 @|StartRequestIcon #fIcon
Ic0 f1 type internaltest.InternalSupportStatisticData #txt
Ic0 f1 337 49 30 30 0 15 #rect
Ic0 f1 @|EndIcon #fIcon
Ic0 f3 targetWindow NEW #txt
Ic0 f3 targetDisplay TOP #txt
Ic0 f3 richDialogId ch.ivy.addon.portal.generic.admin.PortalDashBoard #txt
Ic0 f3 startMethod start() #txt
Ic0 f3 type internaltest.InternalSupportStatisticData #txt
Ic0 f3 requestActionDecl '<> param;' #txt
Ic0 f3 responseActionDecl 'internaltest.InternalSupportStatisticData out;
' #txt
Ic0 f3 responseMappingAction 'out=in;
' #txt
Ic0 f3 isAsynch false #txt
Ic0 f3 isInnerRd false #txt
Ic0 f3 userContext '* ' #txt
Ic0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Open statistic page</name>
        <nameStyle>19,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ic0 f3 168 42 112 44 -53 -8 #rect
Ic0 f3 @|RichDialogIcon #fIcon
Ic0 f4 expr out #txt
Ic0 f4 111 64 168 64 #arcP
Ic0 f2 expr out #txt
Ic0 f2 280 64 337 64 #arcP
>Proto Ic0 .type internaltest.InternalSupportStatisticData #txt
>Proto Ic0 .processKind NORMAL #txt
>Proto Ic0 0 0 32 24 18 0 #rect
>Proto Ic0 @|BIcon #fIcon
Ic0 f0 mainOut f4 tail #connect
Ic0 f4 head f3 mainIn #connect
Ic0 f3 mainOut f2 tail #connect
Ic0 f2 head f1 mainIn #connect
