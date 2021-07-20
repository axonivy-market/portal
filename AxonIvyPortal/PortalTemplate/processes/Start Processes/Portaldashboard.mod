[Ivy]
17065D04AF6FF0C0 9.3.0 #module
>Proto >Proto Collection #zClass
Pd0 PortalDashboard Big #zClass
Pd0 B #cInfo
Pd0 #process
Pd0 @TextInP .type .type #zField
Pd0 @TextInP .processKind .processKind #zField
Pd0 @TextInP .xml .xml #zField
Pd0 @TextInP .responsibility .responsibility #zField
Pd0 @StartRequest f0 '' #zField
Pd0 @UserDialog f3 '' #zField
Pd0 @PushWFArc f4 '' #zField
Pd0 @StartRequest f2 '' #zField
Pd0 @EndTask f5 '' #zField
Pd0 @UserDialog f7 '' #zField
Pd0 @PushWFArc f8 '' #zField
Pd0 @PushWFArc f9 '' #zField
Pd0 @EndTask f6 '' #zField
Pd0 @PushWFArc f1 '' #zField
>Proto Pd0 Pd0 PortalDashboard #zField
Pd0 f0 outLink Dashboard.ivp #txt
Pd0 f0 inParamDecl '<> param;' #txt
Pd0 f0 requestEnabled true #txt
Pd0 f0 triggerEnabled false #txt
Pd0 f0 callSignature Dashboard() #txt
Pd0 f0 startName Dashboard #txt
Pd0 f0 caseData businessCase.attach=true #txt
Pd0 f0 showInStartList 1 #txt
Pd0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Dashboard.ivp</name>
    </language>
</elementInfo>
' #txt
Pd0 f0 @C|.responsibility Everybody #txt
Pd0 f0 113 81 30 30 -37 20 #rect
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
Pd0 f3 224 74 112 44 -44 -8 #rect
Pd0 f4 143 96 224 96 #arcP
Pd0 f2 outLink DashboardConfiguration.ivp #txt
Pd0 f2 inParamDecl '<> param;' #txt
Pd0 f2 requestEnabled true #txt
Pd0 f2 triggerEnabled false #txt
Pd0 f2 callSignature DashboardConfiguration() #txt
Pd0 f2 caseData businessCase.attach=true #txt
Pd0 f2 showInStartList 0 #txt
Pd0 f2 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>DashboardConfiguration.ivp</name>
    </language>
</elementInfo>
' #txt
Pd0 f2 @C|.responsibility Everybody #txt
Pd0 f2 113 209 30 30 -67 21 #rect
Pd0 f5 465 209 30 30 0 15 #rect
Pd0 f7 dialogId ch.ivy.addon.portal.generic.dashboard.PortalDashboardConfiguration #txt
Pd0 f7 startMethod start() #txt
Pd0 f7 requestActionDecl '<> param;' #txt
Pd0 f7 responseMappingAction 'out=in;
' #txt
Pd0 f7 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>PortalDashboardConfiguration</name>
    </language>
</elementInfo>
' #txt
Pd0 f7 224 202 176 44 -84 -8 #rect
Pd0 f8 143 224 224 224 #arcP
Pd0 f9 400 224 465 224 #arcP
Pd0 f6 465 81 30 30 0 15 #rect
Pd0 f1 336 96 465 96 #arcP
>Proto Pd0 .type ch.ivy.addon.portal.generic.PortalDashboardData #txt
>Proto Pd0 .processKind NORMAL #txt
>Proto Pd0 0 0 32 24 18 0 #rect
>Proto Pd0 @|BIcon #fIcon
Pd0 f0 mainOut f4 tail #connect
Pd0 f4 head f3 mainIn #connect
Pd0 f2 mainOut f8 tail #connect
Pd0 f8 head f7 mainIn #connect
Pd0 f7 mainOut f9 tail #connect
Pd0 f9 head f5 mainIn #connect
Pd0 f3 mainOut f1 tail #connect
Pd0 f1 head f6 mainIn #connect
