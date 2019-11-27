[Ivy]
169B29B5A9636EE8 7.5.0 #module
>Proto >Proto Collection #zClass
Se0 SetGroupChatName Big #zClass
Se0 B #cInfo
Se0 #process
Se0 @TextInP .type .type #zField
Se0 @TextInP .processKind .processKind #zField
Se0 @AnnotationInP-0n ai ai #zField
Se0 @MessageFlowInP-0n messageIn messageIn #zField
Se0 @MessageFlowOutP-0n messageOut messageOut #zField
Se0 @TextInP .xml .xml #zField
Se0 @TextInP .responsibility .responsibility #zField
Se0 @StartSub f0 '' #zField
Se0 @EndSub f1 '' #zField
Se0 @GridStep f3 '' #zField
Se0 @PushWFArc f4 '' #zField
Se0 @PushWFArc f2 '' #zField
Se0 @InfoButton f5 '' #zField
Se0 @AnnotationArc f6 '' #zField
>Proto Se0 Se0 SetGroupChatName #zField
Se0 f0 inParamDecl '<> param;' #txt
Se0 f0 outParamDecl '<String name> result;' #txt
Se0 f0 outParamTable 'result.name=in.name;
' #txt
Se0 f0 callSignature setGroupChatName() #txt
Se0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>setGroupChatName()</name>
        <nameStyle>18,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Se0 f0 81 49 30 30 -59 17 #rect
Se0 f0 @|StartSubIcon #fIcon
Se0 f1 337 49 30 30 0 15 #rect
Se0 f1 @|EndSubIcon #fIcon
Se0 f3 actionTable 'out=in;
out.name=ivy.cms.co("/ch.ivy.addon.portalkit.ui.jsf/common/case") + "-{caseId}" + " {caseName}";
' #txt
Se0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Set group chat name</name>
        <nameStyle>19
</nameStyle>
    </language>
</elementInfo>
' #txt
Se0 f3 160 42 128 44 -57 -8 #rect
Se0 f3 @|StepIcon #fIcon
Se0 f4 expr out #txt
Se0 f4 111 64 160 64 #arcP
Se0 f2 expr out #txt
Se0 f2 288 64 337 64 #arcP
Se0 f5 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Default case name is: "Case-{caseId}"

Parameters can be passed through the bracket: {parameter}
They are available in GroupChat.java and you can add more by overriding the GetGroupChatParams callable process</name>
        <nameStyle>208
</nameStyle>
    </language>
</elementInfo>
' #txt
Se0 f5 160 130 656 76 -318 -32 #rect
Se0 f5 @|IBIcon #fIcon
Se0 f6 488 130 224 86 #arcP
>Proto Se0 .type ch.ivy.addon.portal.generic.SetGroupChatNameData #txt
>Proto Se0 .processKind CALLABLE_SUB #txt
>Proto Se0 0 0 32 24 18 0 #rect
>Proto Se0 @|BIcon #fIcon
Se0 f0 mainOut f4 tail #connect
Se0 f4 head f3 mainIn #connect
Se0 f3 mainOut f2 tail #connect
Se0 f2 head f1 mainIn #connect
Se0 f5 ao f6 tail #connect
Se0 f6 head f3 @CG|ai #connect
