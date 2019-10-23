[Ivy]
169B8DBA8236AA9F 7.5.0 #module
>Proto >Proto Collection #zClass
Gs0 GetGroupChatParams Big #zClass
Gs0 B #cInfo
Gs0 #process
Gs0 @TextInP .type .type #zField
Gs0 @TextInP .processKind .processKind #zField
Gs0 @AnnotationInP-0n ai ai #zField
Gs0 @MessageFlowInP-0n messageIn messageIn #zField
Gs0 @MessageFlowOutP-0n messageOut messageOut #zField
Gs0 @TextInP .xml .xml #zField
Gs0 @TextInP .responsibility .responsibility #zField
Gs0 @StartSub f0 '' #zField
Gs0 @EndSub f1 '' #zField
Gs0 @GridStep f3 '' #zField
Gs0 @PushWFArc f4 '' #zField
Gs0 @PushWFArc f2 '' #zField
Gs0 @InfoButton f5 '' #zField
Gs0 @AnnotationArc f6 '' #zField
>Proto Gs0 Gs0 GetGroupChatParams #zField
Gs0 f0 inParamDecl '<> param;' #txt
Gs0 f0 inParamTable 'out.params=new java.util.HashMap();
' #txt
Gs0 f0 outParamDecl '<java.util.Map params> result;' #txt
Gs0 f0 outParamTable 'result.params=in.params;
' #txt
Gs0 f0 callSignature getGroupChatParams() #txt
Gs0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>getGroupChatParams()</name>
        <nameStyle>20,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Gs0 f0 81 49 30 30 -64 17 #rect
Gs0 f0 @|StartSubIcon #fIcon
Gs0 f1 337 49 30 30 0 15 #rect
Gs0 f1 @|EndSubIcon #fIcon
Gs0 f3 actionTable 'out=in;
' #txt
Gs0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Set params</name>
        <nameStyle>10
</nameStyle>
    </language>
</elementInfo>
' #txt
Gs0 f3 168 42 112 44 -32 -8 #rect
Gs0 f3 @|StepIcon #fIcon
Gs0 f4 expr out #txt
Gs0 f4 111 64 168 64 #arcP
Gs0 f2 expr out #txt
Gs0 f2 280 64 337 64 #arcP
Gs0 f5 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Put parameters to display in group chat name:
out.params.put("accountHolder", &lt;VALUE&gt;);</name>
        <nameStyle>87
</nameStyle>
    </language>
</elementInfo>
' #txt
Gs0 f5 176 154 256 44 -125 -16 #rect
Gs0 f5 @|IBIcon #fIcon
Gs0 f6 304 154 224 86 #arcP
>Proto Gs0 .type ch.ivy.addon.portal.generic.GetGroupChatParamsData #txt
>Proto Gs0 .processKind CALLABLE_SUB #txt
>Proto Gs0 0 0 32 24 18 0 #rect
>Proto Gs0 @|BIcon #fIcon
Gs0 f0 mainOut f4 tail #connect
Gs0 f4 head f3 mainIn #connect
Gs0 f3 mainOut f2 tail #connect
Gs0 f2 head f1 mainIn #connect
Gs0 f5 ao f6 tail #connect
Gs0 f6 head f3 @CG|ai #connect
