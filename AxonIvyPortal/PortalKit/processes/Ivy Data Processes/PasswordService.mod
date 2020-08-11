[Ivy]
15E0CEDE89CC1D0E 7.5.0 #module
>Proto >Proto Collection #zClass
Pe0 PasswordService Big #zClass
Pe0 B #cInfo
Pe0 #process
Pe0 @TextInP .type .type #zField
Pe0 @TextInP .processKind .processKind #zField
Pe0 @AnnotationInP-0n ai ai #zField
Pe0 @MessageFlowInP-0n messageIn messageIn #zField
Pe0 @MessageFlowOutP-0n messageOut messageOut #zField
Pe0 @TextInP .xml .xml #zField
Pe0 @TextInP .responsibility .responsibility #zField
Pe0 @StartSub f0 '' #zField
Pe0 @EndSub f1 '' #zField
Pe0 @GridStep f6 '' #zField
Pe0 @PushWFArc f4 '' #zField
Pe0 @PushWFArc f2 '' #zField
>Proto Pe0 Pe0 PasswordService #zField
Pe0 f0 inParamDecl '<String newPassword> param;' #txt
Pe0 f0 inParamTable 'out.newPassword=param.newPassword;
' #txt
Pe0 f0 outParamDecl '<Boolean result> result;' #txt
Pe0 f0 outParamTable 'result.result=in.result;
' #txt
Pe0 f0 callSignature updatePassword(String) #txt
Pe0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>updatePassword(String)</name>
    </language>
</elementInfo>
' #txt
Pe0 f0 81 81 30 30 -86 17 #rect
Pe0 f0 @|StartSubIcon #fIcon
Pe0 f1 425 81 30 30 0 15 #rect
Pe0 f1 @|EndSubIcon #fIcon
Pe0 f6 actionTable 'out=in;
' #txt
Pe0 f6 actionCode 'import ch.ivy.addon.portalkit.ivydata.service.impl.PasswordService;

in.result = PasswordService.newInstance().changePassword(in.newPassword);
' #txt
Pe0 f6 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Change password</name>
    </language>
</elementInfo>
' #txt
Pe0 f6 232 74 112 44 -51 -8 #rect
Pe0 f6 @|StepIcon #fIcon
Pe0 f4 111 96 232 96 #arcP
Pe0 f2 344 96 425 96 #arcP
>Proto Pe0 .type ch.ivyteam.wf.processes.PasswordServiceData #txt
>Proto Pe0 .processKind CALLABLE_SUB #txt
>Proto Pe0 0 0 32 24 18 0 #rect
>Proto Pe0 @|BIcon #fIcon
Pe0 f0 mainOut f4 tail #connect
Pe0 f4 head f6 mainIn #connect
Pe0 f6 mainOut f2 tail #connect
Pe0 f2 head f1 mainIn #connect
