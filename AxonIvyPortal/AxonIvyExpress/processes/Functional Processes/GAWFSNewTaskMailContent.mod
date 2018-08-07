[Ivy]
[>Created: Wed May 10 09:55:58 ICT 2017]
15AD7BBD0A1EBF6E 3.20 #module
>Proto >Proto Collection #zClass
Nt0 GAWFSNewTaskMailContent Big #zClass
Nt0 B #cInfo
Nt0 #process
Nt0 @TextInP .resExport .resExport #zField
Nt0 @TextInP .type .type #zField
Nt0 @TextInP .processKind .processKind #zField
Nt0 @AnnotationInP-0n ai ai #zField
Nt0 @TextInP .xml .xml #zField
Nt0 @TextInP .responsibility .responsibility #zField
Nt0 @StartRequest f0 '' #zField
Nt0 @EndRequest f1 '' #zField
Nt0 @GridStep f10 '' #zField
Nt0 @PushWFArc f2 '' #zField
Nt0 @Alternative f3 '' #zField
Nt0 @PushWFArc f4 '' #zField
Nt0 @PushWFArc f5 '' #zField
Nt0 @EndTask f6 '' #zField
Nt0 @PushWFArc f7 '' #zField
>Proto Nt0 Nt0 GAWFSNewTaskMailContent #zField
Nt0 f0 outLink MailNotification_NewTask.ivp #txt
Nt0 f0 type ch.ivy.components.NewTaskMailContent #txt
Nt0 f0 inParamDecl '<java.lang.Number notificationTaskId,java.lang.Number notificationUserId> param;' #txt
Nt0 f0 inParamTable 'out.task=ivy.wf.findTask(param.notificationTaskId.longValue());
out.user=ivy.wf.getSecurityContext().findUser(param.notificationUserId);
' #txt
Nt0 f0 actionDecl 'ch.ivy.components.NewTaskMailContent out;
' #txt
Nt0 f0 guid 129CB772CF9658F0 #txt
Nt0 f0 requestEnabled true #txt
Nt0 f0 triggerEnabled false #txt
Nt0 f0 callSignature MailNotification_NewTask(Number,Number) #txt
Nt0 f0 persist false #txt
Nt0 f0 taskData '#
#Tue Jan 20 21:52:20 CET 2015
TaskTriggered.ROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.EXPRI=2
TaskTriggered.TYPE=0
TaskTriggered.PRI=2
TaskTriggered.EXROL=Everybody
' #txt
Nt0 f0 caseData '#
#Tue Jan 20 21:52:20 CET 2015
businessCalendarName=
businessCreator.user=
businessMilestone.timestamp=
businessObject.code=
businessObject.docDb.code=
businessObject.folder.id=
businessObject.name=
businessPriority=
businessStart.timestamp=
case.description=
case.name=
correspondent.id=
mainContact.docDb.code=
mainContact.folder.id=
mainContact.id=
mainContact.name=
mainContact.type=
process.code=
process.name=
processCategory.code=
processCategory.name=
subType.code=
subType.name=
type.code=
type.name=
' #txt
Nt0 f0 showInStartList 0 #txt
Nt0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>MailNotification_NewTask.ivp</name>
        <nameStyle>28,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Nt0 f0 @C|.responsibility Everybody #txt
Nt0 f0 155 35 26 26 14 0 #rect
Nt0 f0 @|StartRequestIcon #fIcon
Nt0 f0 -256|-1|-16777216 #nodeStyle
Nt0 f1 type ch.ivy.components.NewTaskMailContent #txt
Nt0 f1 template "/ProcessPages/NewTaskMailContent/NewTaskMailContent.ivc" #txt
Nt0 f1 155 251 26 26 14 0 #rect
Nt0 f1 @|EndRequestIcon #fIcon
Nt0 f10 actionDecl 'ch.ivy.components.NewTaskMailContent out;
' #txt
Nt0 f10 actionTable 'out=in;
' #txt
Nt0 f10 actionCode 'String tmp = ivy.html.taskstartref(in.task);
tmp = tmp.substring(tmp.indexOf("/ivy")+1);

in.taskStartLink = ivy.var.servername_for_mail + tmp;' #txt
Nt0 f10 type ch.ivy.components.NewTaskMailContent #txt
Nt0 f10 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>create task
start link</name>
        <nameStyle>12,7
10,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Nt0 f10 150 188 36 24 27 -17 #rect
Nt0 f10 @|StepIcon #fIcon
Nt0 f2 expr out #txt
Nt0 f2 168 212 168 251 #arcP
Nt0 f3 type ch.ivy.components.NewTaskMailContent #txt
Nt0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>check if task is INIT_WF Task, 
then sent no email</name>
        <nameStyle>37,7
5,7
8,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Nt0 f3 152 80 32 32 12 11 #rect
Nt0 f3 @|AlternativeIcon #fIcon
Nt0 f4 expr out #txt
Nt0 f4 168 61 168 80 #arcP
Nt0 f5 expr in #txt
Nt0 f5 outCond 'in.task.getCustomVarCharField1() !="INIT_WF"' #txt
Nt0 f5 168 112 168 188 #arcP
Nt0 f6 type ch.ivy.components.NewTaskMailContent #txt
Nt0 f6 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>No eMail</name>
        <nameStyle>8,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Nt0 f6 377 81 30 30 -22 22 #rect
Nt0 f6 @|EndIcon #fIcon
Nt0 f7 expr in #txt
Nt0 f7 184 96 377 96 #arcP
>Proto Nt0 .type ch.ivy.components.NewTaskMailContent #txt
>Proto Nt0 .processKind NORMAL #txt
>Proto Nt0 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language/>
</elementInfo>
' #txt
>Proto Nt0 0 0 32 24 18 0 #rect
>Proto Nt0 @|BIcon #fIcon
Nt0 f10 mainOut f2 tail #connect
Nt0 f2 head f1 mainIn #connect
Nt0 f0 mainOut f4 tail #connect
Nt0 f4 head f3 in #connect
Nt0 f3 out f5 tail #connect
Nt0 f5 head f10 mainIn #connect
Nt0 f3 out f7 tail #connect
Nt0 f7 head f6 mainIn #connect
