[Ivy]
16E3FC031CE19AD3 7.5.0 #module
>Proto >Proto Collection #zClass
Nt0 NewTaskMailContent Big #zClass
Nt0 B #cInfo
Nt0 #process
Nt0 @TextInP .type .type #zField
Nt0 @TextInP .processKind .processKind #zField
Nt0 @TextInP .xml .xml #zField
Nt0 @TextInP .responsibility .responsibility #zField
Nt0 @StartRequest f0 '' #zField
Nt0 @UserDialog f1 '' #zField
Nt0 @GridStep f3 '' #zField
Nt0 @PushWFArc f4 '' #zField
Nt0 @PushWFArc f2 '' #zField
>Proto Nt0 Nt0 NewTaskMailContent #zField
Nt0 f0 outLink MailNotification_NewTask.ivp #txt
Nt0 f0 inParamDecl '<Number notificationTaskId,Number notificationUserId> param;' #txt
Nt0 f0 inParamTable 'out.task=ivy.wf.findTask(param.notificationTaskId);
out.user=ivy.wf.getSecurityContext().findUser(param.notificationUserId);
' #txt
Nt0 f0 requestEnabled true #txt
Nt0 f0 triggerEnabled false #txt
Nt0 f0 callSignature MailNotification_NewTask(Number,Number) #txt
Nt0 f0 caseData businessCase.attach=true #txt
Nt0 f0 showInStartList 0 #txt
Nt0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>MailNotification_NewTask.ivp</name>
    </language>
</elementInfo>
' #txt
Nt0 f0 @C|.responsibility Everybody #txt
Nt0 f0 81 49 30 30 -61 15 #rect
Nt0 f0 @|StartRequestIcon #fIcon
Nt0 f1 dialogId ch.ivy.addon.portal.generic.mail.NewTaskMailContent #txt
Nt0 f1 startMethod start(ITask) #txt
Nt0 f1 requestActionDecl '<ch.ivyteam.ivy.workflow.ITask task> param;' #txt
Nt0 f1 requestMappingAction 'param.task=in.task;
' #txt
Nt0 f1 responseMappingAction 'out=in;
' #txt
Nt0 f1 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>NewTaskMailContent</name>
    </language>
</elementInfo>
' #txt
Nt0 f1 360 42 128 44 -55 -8 #rect
Nt0 f1 @|UserDialogIcon #fIcon
Nt0 f3 actionTable 'out=in;
' #txt
Nt0 f3 actionCode 'ivy.session.contentLocale = in.user.#eMailLanguage;
ivy.session.formattingLocale = in.user.#eMailLanguage;' #txt
Nt0 f3 security system #txt
Nt0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Set language</name>
    </language>
</elementInfo>
' #txt
Nt0 f3 208 42 112 44 -34 -8 #rect
Nt0 f3 @|StepIcon #fIcon
Nt0 f4 111 64 208 64 #arcP
Nt0 f2 320 64 360 64 #arcP
>Proto Nt0 .type ch.ivy.addon.portal.generic.NewTaskMailContentData #txt
>Proto Nt0 .processKind NORMAL #txt
>Proto Nt0 0 0 32 24 18 0 #rect
>Proto Nt0 @|BIcon #fIcon
Nt0 f0 mainOut f4 tail #connect
Nt0 f4 head f3 mainIn #connect
Nt0 f3 mainOut f2 tail #connect
Nt0 f2 head f1 mainIn #connect
