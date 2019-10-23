[Ivy]
1635D8A3ACBA233D 7.5.0 #module
>Proto >Proto Collection #zClass
sl0 sendEmail Big #zClass
sl0 B #cInfo
sl0 #process
sl0 @TextInP .type .type #zField
sl0 @TextInP .processKind .processKind #zField
sl0 @AnnotationInP-0n ai ai #zField
sl0 @MessageFlowInP-0n messageIn messageIn #zField
sl0 @MessageFlowOutP-0n messageOut messageOut #zField
sl0 @TextInP .xml .xml #zField
sl0 @TextInP .responsibility .responsibility #zField
sl0 @StartSub f0 '' #zField
sl0 @EndSub f1 '' #zField
sl0 @EMail f3 '' #zField
sl0 @PushWFArc f2 '' #zField
sl0 @PushWFArc f4 '' #zField
>Proto sl0 sl0 sendEmail #zField
sl0 f0 inParamDecl '<ch.ivyteam.ivy.components.config.EmailConfiguration emailConfig,java.util.List<File> attachmentFiles> param;' #txt
sl0 f0 inParamTable 'out.attachmentFiles=param.attachmentFiles;
out.emailConfig=param.emailConfig;
' #txt
sl0 f0 outParamDecl '<> result;' #txt
sl0 f0 callSignature call(ch.ivyteam.ivy.components.config.EmailConfiguration,List<File>) #txt
sl0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>call(EmailConfiguration,List&lt;File&gt;)</name>
        <nameStyle>35,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
sl0 f0 81 49 30 30 -95 17 #rect
sl0 f0 @|StartSubIcon #fIcon
sl0 f1 337 49 30 30 0 15 #rect
sl0 f1 @|EndSubIcon #fIcon
sl0 f3 beanConfig '"{/emailSubject ""<%=in.emailConfig.subject%>""/emailFrom """"/emailReplyTo ""<%=in.emailConfig.replyTo%>""/emailTo ""<%=in.emailConfig.to%>""/emailCC """"/emailBCC """"/exceptionMissingEmailAttachments ""false""/emailMessage ""<%=in.emailConfig.messageContent%>""/emailAttachments {""<%=in.attachmentFiles.size() > 0 ? in.attachmentFiles.get(0) : \\""\\""%>""""<%=in.attachmentFiles.size() > 1 ? in.attachmentFiles.get(1) : \\""\\""%>""""<%=in.attachmentFiles.size() > 2 ? in.attachmentFiles.get(2) : \\""\\""%>""""<%=in.attachmentFiles.size() > 3 ? in.attachmentFiles.get(3) : \\""\\""%>""""<%=in.attachmentFiles.size() > 4 ? in.attachmentFiles.get(4) : \\""\\""%>""""<%=in.attachmentFiles.size() > 5 ? in.attachmentFiles.get(5) : \\""\\""%>""""<%=in.attachmentFiles.size() > 6 ? in.attachmentFiles.get(6) : \\""\\""%>""""<%=in.attachmentFiles.size() > 7 ? in.attachmentFiles.get(7) : \\""\\""%>""""<%=in.attachmentFiles.size() > 8 ? in.attachmentFiles.get(8) : \\""\\""%>""""<%=in.attachmentFiles.size() > 9 ? in.attachmentFiles.get(9) : \\""\\""%>""""<%=in.attachmentFiles.size() > 10 ? in.attachmentFiles.get(10) : \\""\\""%>""""<%=in.attachmentFiles.size() > 11 ? in.attachmentFiles.get(11) : \\""\\""%>""""<%=in.attachmentFiles.size() > 12 ? in.attachmentFiles.get(12) : \\""\\""%>""""<%=in.attachmentFiles.size() > 13 ? in.attachmentFiles.get(13) : \\""\\""%>""""<%=in.attachmentFiles.size() > 14 ? in.attachmentFiles.get(14) : \\""\\""%>""""<%=in.attachmentFiles.size() > 15 ? in.attachmentFiles.get(15) : \\""\\""%>""""<%=in.attachmentFiles.size() > 16 ? in.attachmentFiles.get(16) : \\""\\""%>""""<%=in.attachmentFiles.size() > 17 ? in.attachmentFiles.get(17) : \\""\\""%>""""<%=in.attachmentFiles.size() > 18 ? in.attachmentFiles.get(18) : \\""\\""%>""""<%=in.attachmentFiles.size() > 19 ? in.attachmentFiles.get(19) : \\""\\""%>""""<%=in.attachmentFiles.size() > 20 ? in.attachmentFiles.get(20) : \\""\\""%>""""<%=in.attachmentFiles.size() > 21 ? in.attachmentFiles.get(21) : \\""\\""%>""""<%=in.attachmentFiles.size() > 22 ? in.attachmentFiles.get(22) : \\""\\""%>""""<%=in.attachmentFiles.size() > 23 ? in.attachmentFiles.get(23) : \\""\\""%>""""<%=in.attachmentFiles.size() > 24 ? in.attachmentFiles.get(24) : \\""\\""%>""""<%=in.attachmentFiles.size() > 25 ? in.attachmentFiles.get(25) : \\""\\""%>""""<%=in.attachmentFiles.size() > 26 ? in.attachmentFiles.get(26) : \\""\\""%>""""<%=in.attachmentFiles.size() > 27 ? in.attachmentFiles.get(27) : \\""\\""%>""""<%=in.attachmentFiles.size() > 28 ? in.attachmentFiles.get(28) : \\""\\""%>""""<%=in.attachmentFiles.size() > 29 ? in.attachmentFiles.get(29) : \\""\\""%>""""<%=in.attachmentFiles.size() > 30 ? in.attachmentFiles.get(30) : \\""\\""%>""""<%=in.attachmentFiles.size() > 31 ? in.attachmentFiles.get(31) : \\""\\""%>""""<%=in.attachmentFiles.size() > 32 ? in.attachmentFiles.get(32) : \\""\\""%>""""<%=in.attachmentFiles.size() > 33 ? in.attachmentFiles.get(33) : \\""\\""%>""""<%=in.attachmentFiles.size() > 34 ? in.attachmentFiles.get(34) : \\""\\""%>""""<%=in.attachmentFiles.size() > 35 ? in.attachmentFiles.get(35) : \\""\\""%>""""<%=in.attachmentFiles.size() > 36 ? in.attachmentFiles.get(36) : \\""\\""%>""""<%=in.attachmentFiles.size() > 37 ? in.attachmentFiles.get(37) : \\""\\""%>""""<%=in.attachmentFiles.size() > 38 ? in.attachmentFiles.get(38) : \\""\\""%>""""<%=in.attachmentFiles.size() > 39 ? in.attachmentFiles.get(39) : \\""\\""%>""""<%=in.attachmentFiles.size() > 40 ? in.attachmentFiles.get(40) : \\""\\""%>""""<%=in.attachmentFiles.size() > 41 ? in.attachmentFiles.get(41) : \\""\\""%>""""<%=in.attachmentFiles.size() > 42 ? in.attachmentFiles.get(42) : \\""\\""%>""""<%=in.attachmentFiles.size() > 43 ? in.attachmentFiles.get(43) : \\""\\""%>""""<%=in.attachmentFiles.size() > 44 ? in.attachmentFiles.get(44) : \\""\\""%>""""<%=in.attachmentFiles.size() > 45 ? in.attachmentFiles.get(45) : \\""\\""%>""""<%=in.attachmentFiles.size() > 46 ? in.attachmentFiles.get(46) : \\""\\""%>""""<%=in.attachmentFiles.size() > 47 ? in.attachmentFiles.get(47) : \\""\\""%>""""<%=in.attachmentFiles.size() > 48 ? in.attachmentFiles.get(48) : \\""\\""%>""""<%=in.attachmentFiles.size() > 49 ? in.attachmentFiles.get(49) : \\""\\""%>""}}"' #txt
sl0 f3 type gawfs.SendEmailData #txt
sl0 f3 timeout 0 #txt
sl0 f3 168 42 112 44 0 -8 #rect
sl0 f3 @|EMailIcon #fIcon
sl0 f2 expr out #txt
sl0 f2 280 64 337 64 #arcP
sl0 f4 expr out #txt
sl0 f4 111 64 168 64 #arcP
>Proto sl0 .type gawfs.SendEmailData #txt
>Proto sl0 .processKind CALLABLE_SUB #txt
>Proto sl0 0 0 32 24 18 0 #rect
>Proto sl0 @|BIcon #fIcon
sl0 f0 mainOut f4 tail #connect
sl0 f4 head f3 mainIn #connect
sl0 f3 mainOut f2 tail #connect
sl0 f2 head f1 mainIn #connect
