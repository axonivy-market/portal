[Ivy]
16938C058206E397 3.20 #module
>Proto >Proto Collection #zClass
Gn0 GroupChatActivation Big #zClass
Gn0 B #cInfo
Gn0 #process
Gn0 @TextInP .resExport .resExport #zField
Gn0 @TextInP .type .type #zField
Gn0 @TextInP .processKind .processKind #zField
Gn0 @AnnotationInP-0n ai ai #zField
Gn0 @MessageFlowInP-0n messageIn messageIn #zField
Gn0 @MessageFlowOutP-0n messageOut messageOut #zField
Gn0 @TextInP .xml .xml #zField
Gn0 @TextInP .responsibility .responsibility #zField
Gn0 @StartSub f0 '' #zField
Gn0 @EndSub f1 '' #zField
Gn0 @GridStep f3 '' #zField
Gn0 @PushWFArc f4 '' #zField
Gn0 @PushWFArc f2 '' #zField
>Proto Gn0 Gn0 GroupChatActivation #zField
Gn0 f0 inParamDecl '<> param;' #txt
Gn0 f0 outParamDecl '<java.lang.Boolean isActivated> result;
' #txt
Gn0 f0 outParamTable 'result.isActivated=in.isActivated;
' #txt
Gn0 f0 actionDecl 'ch.ivy.add.portalkit.GroupChatActivationData out;
' #txt
Gn0 f0 callSignature activateGroupChat() #txt
Gn0 f0 type ch.ivy.add.portalkit.GroupChatActivationData #txt
Gn0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>activateGroupChat()</name>
    </language>
</elementInfo>
' #txt
Gn0 f0 81 49 30 30 -54 17 #rect
Gn0 f0 @|StartSubIcon #fIcon
Gn0 f1 type ch.ivy.add.portalkit.GroupChatActivationData #txt
Gn0 f1 353 49 30 30 0 15 #rect
Gn0 f1 @|EndSubIcon #fIcon
Gn0 f3 actionDecl 'ch.ivy.add.portalkit.GroupChatActivationData out;
' #txt
Gn0 f3 actionTable 'out=in;
out.isActivated=false;
' #txt
Gn0 f3 type ch.ivy.add.portalkit.GroupChatActivationData #txt
Gn0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Activate/Deactivate group chat</name>
        <nameStyle>30,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Gn0 f3 152 42 176 44 -81 -8 #rect
Gn0 f3 @|StepIcon #fIcon
Gn0 f4 expr out #txt
Gn0 f4 111 64 152 64 #arcP
Gn0 f2 expr out #txt
Gn0 f2 328 64 353 64 #arcP
>Proto Gn0 .type ch.ivy.add.portalkit.GroupChatActivationData #txt
>Proto Gn0 .processKind CALLABLE_SUB #txt
>Proto Gn0 0 0 32 24 18 0 #rect
>Proto Gn0 @|BIcon #fIcon
Gn0 f0 mainOut f4 tail #connect
Gn0 f4 head f3 mainIn #connect
Gn0 f3 mainOut f2 tail #connect
Gn0 f2 head f1 mainIn #connect
