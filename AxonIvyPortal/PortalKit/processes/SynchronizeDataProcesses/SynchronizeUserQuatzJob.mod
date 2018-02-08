[Ivy]
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
Sb0 @PushWFArc f11 '' #zField
Sb0 @Alternative f0 '' #zField
Sb0 @PushWFArc f1 '' #zField
Sb0 @PushWFArc f2 '' #zField
Sb0 @EndTask f4 '' #zField
Sb0 @PushWFArc f9 '' #zField
Sb0 @CallSub f10 '' #zField
Sb0 @PushWFArc f12 '' #zField
Sb0 @PushWFArc f8 '' #zField
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
Sb0 f7 174 236 36 24 20 -2 #rect
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
Sb0 f3 174 308 36 24 25 -9 #rect
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
Sb0 f5 179 43 26 26 14 0 #rect
Sb0 f5 @|StartEventIcon #fIcon
Sb0 f6 type ch.ivy.add.portalkit.Data #txt
Sb0 f6 179 435 26 26 14 0 #rect
Sb0 f6 @|EndIcon #fIcon
Sb0 f11 expr out #txt
Sb0 f11 192 260 192 308 #arcP
Sb0 f0 type ch.ivy.add.portalkit.Data #txt
Sb0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Can Synchronize ?</name>
        <nameStyle>17,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Sb0 f0 176 120 32 32 -124 -8 #rect
Sb0 f0 @|AlternativeIcon #fIcon
Sb0 f1 expr out #txt
Sb0 f1 192 69 192 120 #arcP
Sb0 f2 expr in #txt
Sb0 f2 outCond ch.ivy.addon.portalkit.service.UserSynchronizationService.isCurrentApplicationAllowedToSynchUser() #txt
Sb0 f2 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>yes</name>
        <nameStyle>3,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Sb0 f2 192 152 192 236 #arcP
Sb0 f2 0 0.5238095238095238 -12 0 #arcLabel
Sb0 f4 type ch.ivy.add.portalkit.Data #txt
Sb0 f4 329 121 30 30 0 15 #rect
Sb0 f4 @|EndIcon #fIcon
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
Sb0 f9 208 136 329 136 #arcP
Sb0 f9 0 0.5702479338842975 0 -10 #arcLabel
Sb0 f10 type ch.ivy.add.portalkit.Data #txt
Sb0 f10 processCall 'Functional Processes/HideSystemCase:call(Boolean)' #txt
Sb0 f10 doCall true #txt
Sb0 f10 requestActionDecl '<java.lang.Boolean hideBusinessCase> param;
' #txt
Sb0 f10 requestMappingAction 'param.hideBusinessCase=false;
' #txt
Sb0 f10 responseActionDecl 'ch.ivy.add.portalkit.Data out;
' #txt
Sb0 f10 responseMappingAction 'out=in;
' #txt
Sb0 f10 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>HideSystemCase</name>
        <nameStyle>14,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Sb0 f10 136 362 112 44 -48 -8 #rect
Sb0 f10 @|CallSubIcon #fIcon
Sb0 f12 expr out #txt
Sb0 f12 192 332 192 362 #arcP
Sb0 f8 expr out #txt
Sb0 f8 192 406 192 435 #arcP
>Proto Sb0 .type ch.ivy.add.portalkit.Data #txt
>Proto Sb0 .processKind NORMAL #txt
>Proto Sb0 0 0 32 24 18 0 #rect
>Proto Sb0 @|BIcon #fIcon
Sb0 f7 mainOut f11 tail #connect
Sb0 f11 head f3 mainIn #connect
Sb0 f5 mainOut f1 tail #connect
Sb0 f1 head f0 in #connect
Sb0 f0 out f2 tail #connect
Sb0 f2 head f7 mainIn #connect
Sb0 f0 out f9 tail #connect
Sb0 f9 head f4 mainIn #connect
Sb0 f3 mainOut f12 tail #connect
Sb0 f12 head f10 mainIn #connect
Sb0 f10 mainOut f8 tail #connect
Sb0 f8 head f6 mainIn #connect
