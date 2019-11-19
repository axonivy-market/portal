[Ivy]
16E3FC0B457649AF 7.5.0 #module
>Proto >Proto Collection #zClass
Dt0 DailyTaskSummaryMailContent Big #zClass
Dt0 B #cInfo
Dt0 #process
Dt0 @TextInP .type .type #zField
Dt0 @TextInP .processKind .processKind #zField
Dt0 @TextInP .xml .xml #zField
Dt0 @TextInP .responsibility .responsibility #zField
Dt0 @StartRequest f0 '' #zField
Dt0 @UserDialog f1 '' #zField
Dt0 @GridStep f3 '' #zField
Dt0 @PushWFArc f2 '' #zField
Dt0 @PushWFArc f4 '' #zField
>Proto Dt0 Dt0 DailyTaskSummaryMailContent #zField
Dt0 f0 outLink MailNotification_DailyTaskSummary.ivp #txt
Dt0 f0 inParamDecl '<Number notificationUserId> param;' #txt
Dt0 f0 inParamTable 'out.tasks=ivy.wf.findWorkTasks(ivy.wf.getSecurityContext().findUser(param.notificationUserId), 0, -1).getResultList();
out.user=ivy.wf.getSecurityContext().findUser(param.notificationUserId);
' #txt
Dt0 f0 requestEnabled true #txt
Dt0 f0 triggerEnabled false #txt
Dt0 f0 callSignature MailNotification_DailyTaskSummary(Number) #txt
Dt0 f0 caseData businessCase.attach=true #txt
Dt0 f0 showInStartList 0 #txt
Dt0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>MailNotification_DailyTaskSummary.ivp</name>
    </language>
</elementInfo>
' #txt
Dt0 f0 @C|.responsibility Everybody #txt
Dt0 f0 81 49 30 30 -89 17 #rect
Dt0 f0 @|StartRequestIcon #fIcon
Dt0 f1 dialogId ch.ivy.addon.portal.generic.mail.DailyTaskSummaryMailContent #txt
Dt0 f1 startMethod start(List<ITask>) #txt
Dt0 f1 requestActionDecl '<java.util.List<ch.ivyteam.ivy.workflow.ITask> tasks> param;' #txt
Dt0 f1 requestMappingAction 'param.tasks=in.tasks;
' #txt
Dt0 f1 responseMappingAction 'out=in;
' #txt
Dt0 f1 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>DailyTaskSummaryMailContent</name>
    </language>
</elementInfo>
' #txt
Dt0 f1 400 42 176 44 -81 -8 #rect
Dt0 f1 @|UserDialogIcon #fIcon
Dt0 f3 actionTable 'out=in;
' #txt
Dt0 f3 actionCode 'ivy.session.contentLocale = in.user.#eMailLanguage;
ivy.session.formattingLocale = in.user.#eMailLanguage;' #txt
Dt0 f3 security system #txt
Dt0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Set language</name>
    </language>
</elementInfo>
' #txt
Dt0 f3 232 42 112 44 -34 -8 #rect
Dt0 f3 @|StepIcon #fIcon
Dt0 f2 344 64 400 64 #arcP
Dt0 f4 111 64 232 64 #arcP
>Proto Dt0 .type ch.ivy.addon.portal.generic.DailyTaskSummaryMailContentData #txt
>Proto Dt0 .processKind NORMAL #txt
>Proto Dt0 0 0 32 24 18 0 #rect
>Proto Dt0 @|BIcon #fIcon
Dt0 f3 mainOut f2 tail #connect
Dt0 f2 head f1 mainIn #connect
Dt0 f0 mainOut f4 tail #connect
Dt0 f4 head f3 mainIn #connect
