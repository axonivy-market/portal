[Ivy]
15EF0843F1DB39F2 3.23 #module
>Proto >Proto Collection #zClass
Lt0 Logout Big #zClass
Lt0 B #cInfo
Lt0 #process
Lt0 @TextInP .resExport .resExport #zField
Lt0 @TextInP .type .type #zField
Lt0 @TextInP .processKind .processKind #zField
Lt0 @AnnotationInP-0n ai ai #zField
Lt0 @MessageFlowInP-0n messageIn messageIn #zField
Lt0 @MessageFlowOutP-0n messageOut messageOut #zField
Lt0 @TextInP .xml .xml #zField
Lt0 @TextInP .responsibility .responsibility #zField
Lt0 @StartSub f0 '' #zField
Lt0 @EndSub f1 '' #zField
Lt0 @GridStep f3 '' #zField
Lt0 @PushWFArc f2 '' #zField
Lt0 @CallSub f5 '' #zField
Lt0 @PushWFArc f6 '' #zField
Lt0 @PushWFArc f4 '' #zField
Lt0 @InfoButton f7 '' #zField
Lt0 @AnnotationArc f8 '' #zField
>Proto Lt0 Lt0 Logout #zField
Lt0 f0 inParamDecl '<java.lang.Boolean isTaskReserve> param;' #txt
Lt0 f0 inParamTable 'out.isTaskReserve=param.isTaskReserve;
' #txt
Lt0 f0 outParamDecl '<> result;
' #txt
Lt0 f0 actionDecl 'ch.ivy.add.portalkit.LogoutData out;
' #txt
Lt0 f0 callSignature call(Boolean) #txt
Lt0 f0 type ch.ivy.add.portalkit.LogoutData #txt
Lt0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>call(Boolean)</name>
    </language>
</elementInfo>
' #txt
Lt0 f0 81 49 30 30 -36 17 #rect
Lt0 f0 @|StartSubIcon #fIcon
Lt0 f1 type ch.ivy.add.portalkit.LogoutData #txt
Lt0 f1 497 49 30 30 0 15 #rect
Lt0 f1 @|EndSubIcon #fIcon
Lt0 f3 actionDecl 'ch.ivy.add.portalkit.LogoutData out;
' #txt
Lt0 f3 actionTable 'out=in;
' #txt
Lt0 f3 actionCode 'if (in.isTaskReserve) {
	ivy.session.parkTask(ivy.task);
}
ivy.session.logoutSessionUser();
ivy.session.getSecurityContext().destroySession(ivy.session.getIdentifier());' #txt
Lt0 f3 security system #txt
Lt0 f3 type ch.ivy.add.portalkit.LogoutData #txt
Lt0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Logout</name>
        <nameStyle>6
</nameStyle>
    </language>
</elementInfo>
' #txt
Lt0 f3 328 42 112 44 -19 -8 #rect
Lt0 f3 @|StepIcon #fIcon
Lt0 f2 expr out #txt
Lt0 f2 440 64 497 64 #arcP
Lt0 f5 type ch.ivy.add.portalkit.LogoutData #txt
Lt0 f5 processCall 'Functional Processes/HookLogout:call()' #txt
Lt0 f5 doCall true #txt
Lt0 f5 requestActionDecl '<> param;
' #txt
Lt0 f5 responseActionDecl 'ch.ivy.add.portalkit.LogoutData out;
' #txt
Lt0 f5 responseMappingAction 'out=in;
' #txt
Lt0 f5 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>HookLogout</name>
    </language>
</elementInfo>
' #txt
Lt0 f5 168 42 112 44 -33 -8 #rect
Lt0 f5 @|CallSubIcon #fIcon
Lt0 f6 expr out #txt
Lt0 f6 111 64 168 64 #arcP
Lt0 f4 expr out #txt
Lt0 f4 280 64 328 64 #arcP
Lt0 f7 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Override callable HookLogout if you want to do some extra steps before logout</name>
        <nameStyle>77
</nameStyle>
    </language>
</elementInfo>
' #txt
Lt0 f7 96 265 432 30 -212 -8 #rect
Lt0 f7 @|IBIcon #fIcon
Lt0 f8 312 265 224 86 #arcP
>Proto Lt0 .type ch.ivy.add.portalkit.LogoutData #txt
>Proto Lt0 .processKind CALLABLE_SUB #txt
>Proto Lt0 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language/>
</elementInfo>
' #txt
>Proto Lt0 0 0 32 24 18 0 #rect
>Proto Lt0 @|BIcon #fIcon
Lt0 f3 mainOut f2 tail #connect
Lt0 f2 head f1 mainIn #connect
Lt0 f0 mainOut f6 tail #connect
Lt0 f6 head f5 mainIn #connect
Lt0 f5 mainOut f4 tail #connect
Lt0 f4 head f3 mainIn #connect
Lt0 f7 ao f8 tail #connect
Lt0 f8 head f5 @CG|ai #connect
