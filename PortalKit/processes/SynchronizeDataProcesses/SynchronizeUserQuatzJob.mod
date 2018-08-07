[Ivy]
[>Created: Fri Nov 13 16:27:10 ICT 2015]
150FFE7ECE139751 3.18 #module
>Proto >Proto Collection #zClass
Sb0 SynchronizeUserQuatzJob Big #zClass
Sb0 B #cInfo
Sb0 #process
Sb0 @TextInP .resExport .resExport #zField
Sb0 @TextInP .type .type #zField
Sb0 @TextInP .processKind .processKind #zField
Sb0 @AnnotationInP-0n ai ai #zField
Sb0 @MessageFlowInP-0n messageIn messageIn #zField
Sb0 @MessageFlowOutP-0n messageOut messageOut #zField
Sb0 @TextInP .xml .xml #zField
Sb0 @TextInP .responsibility .responsibility #zField
Sb0 @GridStep f7 '' #zField
Sb0 @CallSub f3 '' #zField
Sb0 @StartEvent f5 '' #zField
Sb0 @EndTask f6 '' #zField
Sb0 @PushWFArc f8 '' #zField
Sb0 @PushWFArc f11 '' #zField
Sb0 @PushWFArc f0 '' #zField
>Proto Sb0 Sb0 SynchronizeUserQuatzJob #zField
Sb0 f7 actionDecl 'ch.ivy.add.portalkit.Data out;
' #txt
Sb0 f7 actionTable 'out=in;
' #txt
Sb0 f7 actionCode 'ivy.log.info("Start to synchronize Application User Cache by user " + ivy.session.getSessionUserName());' #txt
Sb0 f7 type ch.ivy.add.portalkit.Data #txt
Sb0 f7 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start log</name>
        <nameStyle>9,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Sb0 f7 70 108 36 24 20 -2 #rect
Sb0 f7 @|StepIcon #fIcon
Sb0 f3 type ch.ivy.add.portalkit.Data #txt
Sb0 f3 processCall 'Business Processes/SynchronizeApplicationUser:synchronizeApplicationUsers()' #txt
Sb0 f3 doCall true #txt
Sb0 f3 requestActionDecl '<> param;
' #txt
Sb0 f3 responseActionDecl 'ch.ivy.add.portalkit.Data out;
' #txt
Sb0 f3 responseMappingAction 'out=in;
' #txt
Sb0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>synchronize users</name>
        <nameStyle>17,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Sb0 f3 70 180 36 24 25 -26 #rect
Sb0 f3 @|CallSubIcon #fIcon
Sb0 f5 outerBean "ch.ivy.addon.portalkit.util.CronByGlobalVariableTriggerStartEventBean" #txt
Sb0 f5 beanConfig "PortalStartTimeSynchUserExpression" #txt
Sb0 f5 outLink eventLink.ivp #txt
Sb0 f5 type ch.ivy.add.portalkit.Data #txt
Sb0 f5 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>eventStarterbyQuatzScheduler</name>
        <nameStyle>28,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Sb0 f5 @C|.responsibility Everybody #txt
Sb0 f5 75 43 26 26 14 0 #rect
Sb0 f5 @|StartEventIcon #fIcon
Sb0 f6 type ch.ivy.add.portalkit.Data #txt
Sb0 f6 75 251 26 26 14 0 #rect
Sb0 f6 @|EndIcon #fIcon
Sb0 f8 expr out #txt
Sb0 f8 88 204 88 251 #arcP
Sb0 f11 expr out #txt
Sb0 f11 88 132 88 180 #arcP
Sb0 f0 expr out #txt
Sb0 f0 88 69 88 108 #arcP
Sb0 f0 0 0.4402676521414573 0 0 #arcLabel
>Proto Sb0 .type ch.ivy.add.portalkit.Data #txt
>Proto Sb0 .processKind NORMAL #txt
>Proto Sb0 0 0 32 24 18 0 #rect
>Proto Sb0 @|BIcon #fIcon
Sb0 f3 mainOut f8 tail #connect
Sb0 f8 head f6 mainIn #connect
Sb0 f7 mainOut f11 tail #connect
Sb0 f11 head f3 mainIn #connect
Sb0 f5 mainOut f0 tail #connect
Sb0 f0 head f7 mainIn #connect
