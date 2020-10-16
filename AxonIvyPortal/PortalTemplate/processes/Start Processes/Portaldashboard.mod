[Ivy]
17065D04AF6FF0C0 7.5.0 #module
>Proto >Proto Collection #zClass
Pd0 Portaldashboard Big #zClass
Pd0 B #cInfo
Pd0 #process
Pd0 @TextInP .type .type #zField
Pd0 @TextInP .processKind .processKind #zField
Pd0 @TextInP .xml .xml #zField
Pd0 @TextInP .responsibility .responsibility #zField
Pd0 @StartRequest f0 '' #zField
Pd0 @EndTask f1 '' #zField
Pd0 @UserDialog f3 '' #zField
Pd0 @PushWFArc f4 '' #zField
Pd0 @PushWFArc f2 '' #zField
>Proto Pd0 Pd0 Portaldashboard #zField
Pd0 f0 outLink start.ivp #txt
Pd0 f0 inParamDecl '<> param;' #txt
Pd0 f0 requestEnabled true #txt
Pd0 f0 triggerEnabled false #txt
Pd0 f0 callSignature start() #txt
Pd0 f0 startName Dashboard #txt
Pd0 f0 caseData businessCase.attach=true #txt
Pd0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start.ivp</name>
    </language>
</elementInfo>
' #txt
Pd0 f0 @C|.responsibility Everybody #txt
Pd0 f0 81 49 30 30 -20 17 #rect
Pd0 f0 @|StartRequestIcon #fIcon
Pd0 f1 337 49 30 30 0 15 #rect
Pd0 f1 @|EndIcon #fIcon
Pd0 f3 dialogId ch.ivy.addon.portal.generic.dashboard.PortalDashboard #txt
Pd0 f3 startMethod start() #txt
Pd0 f3 requestActionDecl '<> param;' #txt
Pd0 f3 responseMappingAction 'out=in;
' #txt
Pd0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>PortalDashboard</name>
    </language>
</elementInfo>
' #txt
Pd0 f3 168 42 112 44 -44 -8 #rect
Pd0 f3 @|UserDialogIcon #fIcon
Pd0 f4 111 64 168 64 #arcP
Pd0 f2 280 64 337 64 #arcP
>Proto Pd0 .type ch.ivy.addon.portal.generic.Data #txt
>Proto Pd0 .processKind NORMAL #txt
>Proto Pd0 0 0 32 24 18 0 #rect
>Proto Pd0 @|BIcon #fIcon
Pd0 f0 mainOut f4 tail #connect
Pd0 f4 head f3 mainIn #connect
Pd0 f3 mainOut f2 tail #connect
Pd0 f2 head f1 mainIn #connect
