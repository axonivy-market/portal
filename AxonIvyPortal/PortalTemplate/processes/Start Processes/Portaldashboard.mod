[Ivy]
17065D04AF6FF0C0 9.2.0 #module
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
Pd0 @UserDialog f5 '' #zField
Pd0 @PushWFArc f2 '' #zField
Pd0 @PushWFArc f1 '' #zField
>Proto Pd0 Pd0 PortalDashboard #zField
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
Pd0 f0 113 177 30 30 -20 17 #rect
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
Pd0 f3 200 170 112 44 -44 -8 #rect
Pd0 f4 143 192 200 192 #arcP
Pd0 f5 dialogId ch.ivy.addon.portal.generic.dashboard.PortalDashboardConfiguration #txt
Pd0 f5 startMethod start() #txt
Pd0 f5 requestActionDecl '<> param;' #txt
Pd0 f5 responseMappingAction 'out=in;
' #txt
Pd0 f5 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>PortalDashboardConfiguration</name>
    </language>
</elementInfo>
' #txt
Pd0 f5 456 170 176 44 -84 -8 #rect
Pd0 f2 544 170 256 170 #arcP
Pd0 f2 1 544 112 #addKink
Pd0 f2 2 256 112 #addKink
Pd0 f2 1 0.5 0 0 #arcLabel
Pd0 f1 312 192 456 192 #arcP
Pd0 f1 0 0.462962962962963 0 0 #arcLabel
>Proto Pd0 .type ch.ivy.addon.portal.generic.PortalDashboardData #txt
>Proto Pd0 .processKind NORMAL #txt
>Proto Pd0 0 0 32 24 18 0 #rect
>Proto Pd0 @|BIcon #fIcon
Pd0 f0 mainOut f4 tail #connect
Pd0 f4 head f3 mainIn #connect
Pd0 f5 mainOut f2 tail #connect
Pd0 f2 head f3 mainIn #connect
Pd0 f3 mainOut f1 tail #connect
Pd0 f1 head f5 mainIn #connect
