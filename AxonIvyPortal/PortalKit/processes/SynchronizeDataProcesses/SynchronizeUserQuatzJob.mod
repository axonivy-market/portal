[Ivy]
[>Created: Tue Apr 25 17:51:48 ICT 2017]
150FFE7ECE139751 3.20 #module
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
Sb0 @Alternative f1 '' #zField
Sb0 @PushWFArc f2 '' #zField
Sb0 @PushWFArc f0 '' #zField
Sb0 @GridStep f4 '' #zField
Sb0 @PushWFArc f9 '' #zField
Sb0 @EndTask f10 '' #zField
Sb0 @PushWFArc f12 '' #zField
>Proto Sb0 Sb0 SynchronizeUserQuatzJob #zField
Sb0 f7 actionDecl 'ch.ivy.add.portalkit.Data out;
' #txt
Sb0 f7 actionTable 'out=in;
' #txt
Sb0 f7 actionCode 'ivy.log.info("Start to synchronize Application User Cache by user " + ivy.session.getSessionUserName());

' #txt
Sb0 f7 type ch.ivy.add.portalkit.Data #txt
Sb0 f7 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>log start
synch users</name>
        <nameStyle>21,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Sb0 f7 70 196 36 24 20 -2 #rect
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
Sb0 f3 70 276 36 24 25 -26 #rect
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
        <desc>This is a scheduled process of Portal</desc>
    </language>
</elementInfo>
' #txt
Sb0 f5 @C|.responsibility Everybody #txt
Sb0 f5 75 43 26 26 14 0 #rect
Sb0 f5 @|StartEventIcon #fIcon
Sb0 f6 type ch.ivy.add.portalkit.Data #txt
Sb0 f6 75 347 26 26 14 0 #rect
Sb0 f6 @|EndIcon #fIcon
Sb0 f8 expr out #txt
Sb0 f8 88 300 88 347 #arcP
Sb0 f11 expr out #txt
Sb0 f11 88 220 88 276 #arcP
Sb0 f1 type ch.ivy.add.portalkit.Data #txt
Sb0 f1 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>is app allowed
to synch users?</name>
        <nameStyle>15,7
15,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Sb0 f1 74 114 28 28 11 -35 #rect
Sb0 f1 @|AlternativeIcon #fIcon
Sb0 f2 expr out #txt
Sb0 f2 88 69 88 114 #arcP
Sb0 f2 0 0.4402676521414573 0 0 #arcLabel
Sb0 f0 expr in #txt
Sb0 f0 outCond ch.ivy.addon.portalkit.service.UserSynchronizationService.isCurrentApplicationAllowedToSynchUser() #txt
Sb0 f0 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>yes</name>
        <nameStyle>3,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Sb0 f0 88 142 88 196 #arcP
Sb0 f0 0 0.4402676521414573 0 0 #arcLabel
Sb0 f4 actionDecl 'ch.ivy.add.portalkit.Data out;
' #txt
Sb0 f4 actionTable 'out=in;
' #txt
Sb0 f4 actionCode 'ivy.log.info("Current application is not allowed to synchronize users because it is not the first configured application.");' #txt
Sb0 f4 type ch.ivy.add.portalkit.Data #txt
Sb0 f4 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>log not allow
to synch users</name>
        <nameStyle>28,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Sb0 f4 214 196 36 24 20 -2 #rect
Sb0 f4 @|StepIcon #fIcon
Sb0 f9 expr in #txt
Sb0 f9 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>no</name>
        <nameStyle>2,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Sb0 f9 102 128 232 196 #arcP
Sb0 f9 1 232 128 #addKink
Sb0 f9 0 0.774685374191129 0 0 #arcLabel
Sb0 f10 type ch.ivy.add.portalkit.Data #txt
Sb0 f10 219 347 26 26 14 0 #rect
Sb0 f10 @|EndIcon #fIcon
Sb0 f12 expr out #txt
Sb0 f12 232 220 232 347 #arcP
>Proto Sb0 .type ch.ivy.add.portalkit.Data #txt
>Proto Sb0 .processKind NORMAL #txt
>Proto Sb0 0 0 32 24 18 0 #rect
>Proto Sb0 @|BIcon #fIcon
Sb0 f3 mainOut f8 tail #connect
Sb0 f8 head f6 mainIn #connect
Sb0 f7 mainOut f11 tail #connect
Sb0 f11 head f3 mainIn #connect
Sb0 f5 mainOut f2 tail #connect
Sb0 f2 head f1 in #connect
Sb0 f1 out f0 tail #connect
Sb0 f0 head f7 mainIn #connect
Sb0 f1 out f9 tail #connect
Sb0 f9 head f4 mainIn #connect
Sb0 f4 mainOut f12 tail #connect
Sb0 f12 head f10 mainIn #connect
