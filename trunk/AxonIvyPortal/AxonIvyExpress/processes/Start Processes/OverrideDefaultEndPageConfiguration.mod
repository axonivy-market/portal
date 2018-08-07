[Ivy]
[>Created: Wed May 10 09:54:31 ICT 2017]
158251DEA92CD8C2 3.20 #module
>Proto >Proto Collection #zClass
On0 OverrideDefaultEndPageConfiguration Big #zClass
On0 B #cInfo
On0 #process
On0 @TextInP .resExport .resExport #zField
On0 @TextInP .type .type #zField
On0 @TextInP .processKind .processKind #zField
On0 @AnnotationInP-0n ai ai #zField
On0 @MessageFlowInP-0n messageIn messageIn #zField
On0 @MessageFlowOutP-0n messageOut messageOut #zField
On0 @TextInP .xml .xml #zField
On0 @TextInP .responsibility .responsibility #zField
On0 @StartRequest f0 '' #zField
On0 @GridStep f1 '' #zField
On0 @PushWFArc f3 '' #zField
On0 @EndRequest f5 '' #zField
On0 @PushWFArc f6 '' #zField
>Proto On0 On0 OverrideDefaultEndPageConfiguration #zField
On0 f0 outLink DefaultEndPage.ivp #txt
On0 f0 type gawfs.Data #txt
On0 f0 inParamDecl '<java.lang.Number endedTaskId> param;' #txt
On0 f0 inParamTable 'out.taskId=param.endedTaskId;
' #txt
On0 f0 actionDecl 'gawfs.Data out;
' #txt
On0 f0 guid 158251F5B99C2079 #txt
On0 f0 requestEnabled true #txt
On0 f0 triggerEnabled false #txt
On0 f0 callSignature DefaultEndPage(Number) #txt
On0 f0 persist false #txt
On0 f0 startName OverrideDefaultPageGAWFS #txt
On0 f0 taskData 'TaskTriggered.ROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.EXPRI=2
TaskTriggered.TYPE=0
TaskTriggered.PRI=2
TaskTriggered.EXROL=Everybody' #txt
On0 f0 showInStartList 0 #txt
On0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>DefaultEndPage.ivp</name>
        <nameStyle>18,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
On0 f0 @C|.responsibility Everybody #txt
On0 f0 113 65 30 30 -54 17 #rect
On0 f0 @|StartRequestIcon #fIcon
On0 f1 actionDecl 'gawfs.Data out;
' #txt
On0 f1 actionTable 'out=in;
' #txt
On0 f1 actionCode 'import ch.ivyteam.ivy.workflow.ITask;

//set taskid to 0 to go to default tasklist without selecting a task!
String corporateHome = ivy.html.startref(ivy.var.gawfs_core_endpage)+ "?taskIdentifier=" + in.taskId;

ivy.log.info("Homepage URL:"+corporateHome);
ivy.session.setAttribute("taskListURL", corporateHome);

' #txt
On0 f1 type gawfs.Data #txt
On0 f1 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>set url</name>
        <nameStyle>7,7
</nameStyle>
    </language>
</elementInfo>
' #txt
On0 f1 72 186 112 44 -17 -8 #rect
On0 f1 @|StepIcon #fIcon
On0 f3 expr out #txt
On0 f3 128 95 128 186 #arcP
On0 f5 type gawfs.Data #txt
On0 f5 template "RedirectToTaskList.jsp" #txt
On0 f5 113 321 30 30 0 15 #rect
On0 f5 @|EndRequestIcon #fIcon
On0 f6 expr out #txt
On0 f6 128 230 128 321 #arcP
On0 f6 0 0.5001473275680022 0 0 #arcLabel
>Proto On0 .type gawfs.Data #txt
>Proto On0 .processKind NORMAL #txt
>Proto On0 0 0 32 24 18 0 #rect
>Proto On0 @|BIcon #fIcon
On0 f0 mainOut f3 tail #connect
On0 f3 head f1 mainIn #connect
On0 f1 mainOut f6 tail #connect
On0 f6 head f5 mainIn #connect
