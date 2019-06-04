[Ivy]
169B2A4D199FA6EA 3.20 #module
>Proto >Proto Collection #zClass
At0 AssignRolesGroupChat Big #zClass
At0 B #cInfo
At0 #process
At0 @TextInP .resExport .resExport #zField
At0 @TextInP .type .type #zField
At0 @TextInP .processKind .processKind #zField
At0 @AnnotationInP-0n ai ai #zField
At0 @MessageFlowInP-0n messageIn messageIn #zField
At0 @MessageFlowOutP-0n messageOut messageOut #zField
At0 @TextInP .xml .xml #zField
At0 @TextInP .responsibility .responsibility #zField
At0 @StartSub f0 '' #zField
At0 @EndSub f1 '' #zField
At0 @InfoButton f3 '' #zField
At0 @PushWFArc f2 '' #zField
At0 @AnnotationArc f4 '' #zField
>Proto At0 At0 AssignRolesGroupChat #zField
At0 f0 inParamDecl '<> param;' #txt
At0 f0 outParamDecl '<java.util.List<ch.ivyteam.ivy.security.IRole> roles> result;
' #txt
At0 f0 outParamTable 'result.roles=in.roles;
' #txt
At0 f0 actionDecl 'ch.ivy.addon.portal.generic.AssignRolesGroupChatData out;
' #txt
At0 f0 callSignature configureRolesForGroupChat() #txt
At0 f0 type ch.ivy.addon.portal.generic.AssignRolesGroupChatData #txt
At0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>configureRolesForGroupChat()</name>
        <nameStyle>28,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
At0 f0 81 49 30 30 5 17 #rect
At0 f0 @|StartSubIcon #fIcon
At0 f1 type ch.ivy.addon.portal.generic.AssignRolesGroupChatData #txt
At0 f1 81 257 30 30 0 15 #rect
At0 f1 @|EndSubIcon #fIcon
At0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>This callable is introduced for configuring a list of roles of group chat feature. 
By default, this is doing nothing.
Set value of in.roles to customize this role list.</name>
        <nameStyle>169,7
</nameStyle>
    </language>
</elementInfo>
' #txt
At0 f3 152 130 432 60 -209 -24 #rect
At0 f3 @|IBIcon #fIcon
At0 f2 expr out #txt
At0 f2 96 79 96 257 #arcP
At0 f4 152 160 96 168 #arcP
>Proto At0 .type ch.ivy.addon.portal.generic.AssignRolesGroupChatData #txt
>Proto At0 .processKind CALLABLE_SUB #txt
>Proto At0 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language/>
</elementInfo>
' #txt
>Proto At0 0 0 32 24 18 0 #rect
>Proto At0 @|BIcon #fIcon
At0 f0 mainOut f2 tail #connect
At0 f2 head f1 mainIn #connect
At0 f3 ao f4 tail #connect
At0 f4 head f2 ai #connect
