[Ivy]
16B1CEA6AF5F7E2C 7.5.0 #module
>Proto >Proto Collection #zClass
Ln0 Login Big #zClass
Ln0 B #cInfo
Ln0 #process
Ln0 @TextInP .type .type #zField
Ln0 @TextInP .processKind .processKind #zField
Ln0 @AnnotationInP-0n ai ai #zField
Ln0 @MessageFlowInP-0n messageIn messageIn #zField
Ln0 @MessageFlowOutP-0n messageOut messageOut #zField
Ln0 @TextInP .xml .xml #zField
Ln0 @TextInP .responsibility .responsibility #zField
Ln0 @StartSub f0 '' #zField
Ln0 @EndSub f1 '' #zField
Ln0 @GridStep f3 '' #zField
Ln0 @PushWFArc f4 '' #zField
Ln0 @PushWFArc f2 '' #zField
>Proto Ln0 Ln0 Login #zField
Ln0 f0 inParamDecl '<> param;' #txt
Ln0 f0 outParamDecl '<> result;' #txt
Ln0 f0 callSignature call() #txt
Ln0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>call()</name>
    </language>
</elementInfo>
' #txt
Ln0 f0 81 49 30 30 -13 17 #rect
Ln0 f0 @|StartSubIcon #fIcon
Ln0 f1 337 49 30 30 0 15 #rect
Ln0 f1 @|EndSubIcon #fIcon
Ln0 f3 actionTable 'out=in;
' #txt
Ln0 f3 actionCode 'import ch.ivy.addon.portalkit.enums.GlobalVariable;
import ch.ivy.addon.portal.chat.ChatServiceContainer;
import org.apache.commons.lang3.StringUtils;
import ch.ivy.addon.portalkit.service.GlobalSettingService;

String isEnableChatGlobalVariable = new GlobalSettingService().findGlobalSettingValue(GlobalVariable.ENABLE_PRIVATE_CHAT.toString());
boolean isEnableChat = StringUtils.isNotBlank(isEnableChatGlobalVariable) ? Boolean.parseBoolean(isEnableChatGlobalVariable) : true;
if (isEnableChat && ChatServiceContainer.getChatService() != null) {
  ChatServiceContainer.getChatService().handleUserOnline(ivy.session.getSessionUserName());
}' #txt
Ln0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>nofify others &#xD;
if private chat enabled</name>
    </language>
</elementInfo>
' #txt
Ln0 f3 152 42 144 44 -53 -16 #rect
Ln0 f3 @|StepIcon #fIcon
Ln0 f4 expr out #txt
Ln0 f4 111 64 152 64 #arcP
Ln0 f2 expr out #txt
Ln0 f2 296 64 337 64 #arcP
>Proto Ln0 .type portalTemplate.LoginOverrideData #txt
>Proto Ln0 .processKind CALLABLE_SUB #txt
>Proto Ln0 0 0 32 24 18 0 #rect
>Proto Ln0 @|BIcon #fIcon
Ln0 f0 mainOut f4 tail #connect
Ln0 f4 head f3 mainIn #connect
Ln0 f3 mainOut f2 tail #connect
Ln0 f2 head f1 mainIn #connect
