[Ivy]
14232C3D829C4D71 3.26 #module
>Proto >Proto Collection #zClass
AF0 AdHocWF Big #zClass
AF0 B #cInfo
AF0 #process
1b1 '1 Sub' Big #zClass
1b1 B #cInfo
Ct0 Component Big #zClass
Ct0 B #cInfo
AF0 1b1 1b0 '1 Sub' #zField
AF0 @TextInP .resExport .resExport #zField
AF0 @TextInP .type .type #zField
AF0 @TextInP .processKind .processKind #zField
AF0 @AnnotationInP-0n ai ai #zField
AF0 @MessageFlowInP-0n messageIn messageIn #zField
AF0 @MessageFlowOutP-0n messageOut messageOut #zField
AF0 @TextInP .xml .xml #zField
AF0 @TextInP .responsibility .responsibility #zField
AF0 @StartRequest f0 '' #zField
AF0 @RichDialog f1 '' #zField
AF0 @Alternative f5 '' #zField
AF0 @PushWFArc f6 '' #zField
AF0 @EndRequest f10 '' #zField
AF0 @GridStep f7 '' #zField
AF0 @PushWFArc f8 '' #zField
AF0 @PushWFArc f2 '' #zField
AF0 @GridStep f9 '' #zField
AF0 @GridStep f3 '' #zField
AF0 @PushWFArc f16 '' #zField
AF0 @PushWFArc f4 '' #zField
AF0 @PushWFArc f12 '' #zField
AF0 @Alternative f13 '' #zField
AF0 @PushWFArc f14 '' #zField
AF0 @PushWFArc f11 '' #zField
AF0 @PushWFArc f15 '' #zField
>Proto AF0 AF0 AdHocWF #zField
1b1 Ct0 S10 'Sub 1' #zField
1b1 @TextInP .resExport .resExport #zField
1b1 @TextInP .type .type #zField
1b1 @TextInP .processKind .processKind #zField
1b1 @AnnotationInP-0n ai ai #zField
1b1 @MessageFlowInP-0n messageIn messageIn #zField
1b1 @MessageFlowOutP-0n messageOut messageOut #zField
1b1 @TextInP .xml .xml #zField
1b1 @TextInP .responsibility .responsibility #zField
1b1 @Alternative f6 '' #zField
1b1 @PushTrueWFInG-01 g0 '' #zField
1b1 @PushWFArc f0 '' #zField
1b1 @PushTrueWFOutG-01 g1 '' #zField
1b1 @PushWFArc f1 '' #zField
1b1 @GridStep f5 '' #zField
1b1 @PushWFArc f4 '' #zField
1b1 @EMail f3 '' #zField
1b1 @Alternative f8 '' #zField
1b1 @PushWFArc f10 '' #zField
1b1 @TaskSwitchSimple f13 '' #zField
1b1 @TkArc f14 '' #zField
1b1 @PushWFArc f16 '' #zField
1b1 @GridStep f17 '' #zField
1b1 @PushWFArc f18 '' #zField
1b1 @PushWFArc f15 '' #zField
1b1 @PushWFArc f19 '' #zField
1b1 @PushWFArc f20 '' #zField
>Proto 1b1 1b0 '1 Sub' #zField
Ct0 @TextInP .resExport .resExport #zField
Ct0 @TextInP .type .type #zField
Ct0 @TextInP .processKind .processKind #zField
Ct0 @AnnotationInP-0n ai ai #zField
Ct0 @MessageFlowInP-0n messageIn messageIn #zField
Ct0 @MessageFlowOutP-0n messageOut messageOut #zField
Ct0 @TextInP .xml .xml #zField
Ct0 @TextInP .responsibility .responsibility #zField
Ct0 @TaskSwitchSimple f9 '' #zField
Ct0 @RichDialog f11 '' #zField
Ct0 @PushWFArc f12 '' #zField
Ct0 @PushTrueWFInG-01 g0 '' #zField
Ct0 @PushTrueWFOutG-01 g1 '' #zField
Ct0 @TaskSwitch f2 '' #zField
Ct0 @TaskSwitch f4 '' #zField
Ct0 @Alternative f6 '' #zField
Ct0 @TkArc f3 '' #zField
Ct0 @Alternative f8 '' #zField
Ct0 @PushWFArc f10 '' #zField
Ct0 @TkArc f0 '' #zField
Ct0 @Alternative f14 '' #zField
Ct0 @PushWFArc f15 '' #zField
Ct0 @Alternative f17 '' #zField
Ct0 @PushWFArc f18 '' #zField
Ct0 @GridStep f20 '' #zField
Ct0 @GridStep f22 '' #zField
Ct0 @PushWFArc f23 '' #zField
Ct0 @PushWFArc f13 '' #zField
Ct0 @GridStep f24 '' #zField
Ct0 @PushWFArc f25 '' #zField
Ct0 @PushWFArc f19 '' #zField
Ct0 @PushWFArc f21 '' #zField
Ct0 @TkArc f5 '' #zField
Ct0 @TkArc f16 '' #zField
Ct0 @Alternative f30 '' #zField
Ct0 @PushWFArc f31 '' #zField
Ct0 @PushWFArc f1 '' #zField
Ct0 @PushWFArc f7 '' #zField
Ct0 @PushWFArc f27 '' #zField
Ct0 @Alternative f26 '' #zField
Ct0 @PushWFArc f28 '' #zField
>Proto Ct0 Ct0 Component #zField
AF0 1b0 .resExport export #txt
AF0 1b0 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language lang="en">
        <name>Execute Tasks</name>
        <nameStyle>13,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
AF0 1b0 704 90 112 44 -40 -8 #rect
AF0 1b0 @|BIcon #fIcon
AF0 f0 outLink start.ivp #txt
AF0 f0 type selfServiceBPM.Data #txt
AF0 f0 inParamDecl '<java.lang.Long originalTaskId> param;' #txt
AF0 f0 inParamTable 'out.isAddingAdhocToOtherTask=false;
out.originalTaskId=param.originalTaskId;
' #txt
AF0 f0 actionDecl 'selfServiceBPM.Data out;
' #txt
AF0 f0 guid 14232C40032FD3B8 #txt
AF0 f0 requestEnabled true #txt
AF0 f0 triggerEnabled false #txt
AF0 f0 callSignature start(Long) #txt
AF0 f0 persist false #txt
AF0 f0 startName <%=ivy.cms.co("/ProcessPages/selfServiceAdhocProcess")%> #txt
AF0 f0 startDescription <%=ivy.cms.co("/ProcessPages/selfServiceDescription")%> #txt
AF0 f0 taskData 'TaskTriggered.EXPRI=2
TaskTriggered.EXROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.NAM=Self Service Workflow
TaskTriggered.PRI=2
TaskTriggered.ROL=Everybody
TaskTriggered.TYPE=0
TaskTriggered.customFields.STRING.KindCode="CREATOR"' #txt
AF0 f0 caseData 'businessCase.attach=true
customFields.STRING.ProcessCategoryCode="Self Service WF"
customFields.STRING.ProcessCategoryName="Self Service WF"
customFields.STRING.ProcessCode="Self Service WF"
customFields.STRING.ProcessName="Self Service WF"' #txt
AF0 f0 wfuser 1 #txt
AF0 f0 showInStartList 1 #txt
AF0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start.ivp</name>
        <nameStyle>9,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
AF0 f0 @C|.responsibility Everybody #txt
AF0 f0 83 99 26 26 -21 15 #rect
AF0 f0 @|StartRequestIcon #fIcon
AF0 f1 targetWindow NEW:card: #txt
AF0 f1 targetDisplay TOP #txt
AF0 f1 richDialogId agileBPM.define_WF #txt
AF0 f1 startMethod start(java.lang.Long,Boolean) #txt
AF0 f1 type selfServiceBPM.Data #txt
AF0 f1 requestActionDecl '<java.lang.Long originalTaskId,Boolean isAddingAdhocTaskToOtherTask> param;' #txt
AF0 f1 requestMappingAction 'param.originalTaskId=in.originalTaskId;
param.isAddingAdhocTaskToOtherTask=in.isAddingAdhocToOtherTask;
' #txt
AF0 f1 responseActionDecl 'selfServiceBPM.Data out;
' #txt
AF0 f1 responseMappingAction 'out.caseInfo=result.caseInfo;
out.definedTasks=result.definedTasks;
out.isAddingAdhocToOtherTask=in.isAddingAdhocToOtherTask;
out.originalTaskId=in.originalTaskId;
' #txt
AF0 f1 windowConfiguration '* ' #txt
AF0 f1 isAsynch false #txt
AF0 f1 isInnerRd false #txt
AF0 f1 userContext '* ' #txt
AF0 f1 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>define ad hoc WF</name>
        <nameStyle>16,7
</nameStyle>
    </language>
</elementInfo>
' #txt
AF0 f1 328 90 112 44 -47 -8 #rect
AF0 f1 @|RichDialogIcon #fIcon
AF0 f5 type selfServiceBPM.Data #txt
AF0 f5 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Abort ?</name>
        <nameStyle>7
</nameStyle>
    </language>
</elementInfo>
' #txt
AF0 f5 482 98 28 28 -14 -34 #rect
AF0 f5 @|AlternativeIcon #fIcon
AF0 f6 expr out #txt
AF0 f6 440 112 482 112 #arcP
AF0 f10 type selfServiceBPM.Data #txt
AF0 f10 template "/ProcessPages/AdHocWF/portalHome.ivc" #txt
AF0 f10 1097 97 30 30 0 15 #rect
AF0 f10 @|EndRequestIcon #fIcon
AF0 f7 actionDecl 'selfServiceBPM.Data out;
' #txt
AF0 f7 actionTable 'out=in;
' #txt
AF0 f7 actionCode 'import ch.ivyteam.ivy.security.IPermission;
import ch.ivy.addon.portalkit.service.PermissionCheckerService;
import ch.ivy.addon.portalkit.util.TaskUtils;
import ch.ivyteam.ivy.workflow.ITask;

if(in.#originalTaskId is initialized){
	ITask originalTask = TaskUtils.findTaskUserHasPermissionToSee(in.originalTaskId);
	PermissionCheckerService permissionService = new PermissionCheckerService();
	if(originalTask == null && permissionService.hasPermission(IPermission.TASK_READ_ALL)){
		originalTask = ivy.wf.findTask(in.originalTaskId);
	}
	if(originalTask != null){
		in.isAddingAdhocToOtherTask = true;
	}
}' #txt
AF0 f7 security system #txt
AF0 f7 type selfServiceBPM.Data #txt
AF0 f7 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Checking if create 
ad-hoc task for other task</name>
        <nameStyle>46,7
</nameStyle>
    </language>
</elementInfo>
' #txt
AF0 f7 144 90 160 44 -62 -16 #rect
AF0 f7 @|StepIcon #fIcon
AF0 f8 expr out #txt
AF0 f8 109 112 144 112 #arcP
AF0 f2 expr out #txt
AF0 f2 304 112 328 112 #arcP
AF0 f9 actionDecl 'selfServiceBPM.Data out;
' #txt
AF0 f9 actionTable 'out=in;
' #txt
AF0 f9 actionCode 'import ch.ivy.addon.portalkit.util.TaskUtils;
import ch.ivyteam.ivy.workflow.ITask;

if(in.isAddingAdhocToOtherTask){
	ITask originalTask = ivy.wf.findTask(in.originalTaskId);
	if(originalTask != null){
		TaskUtils.removeHidePropertyToDisplayInPortal(originalTask);
	}
}' #txt
AF0 f9 security system #txt
AF0 f9 type selfServiceBPM.Data #txt
AF0 f9 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Show original task</name>
        <nameStyle>18
</nameStyle>
    </language>
</elementInfo>
' #txt
AF0 f9 864 90 112 44 -50 -8 #rect
AF0 f9 @|StepIcon #fIcon
AF0 f3 actionDecl 'selfServiceBPM.Data out;
' #txt
AF0 f3 actionTable 'out=in;
' #txt
AF0 f3 actionCode 'import ch.ivy.addon.portalkit.util.TaskUtils;
import ch.ivyteam.ivy.workflow.ITask;
import ch.ivyteam.ivy.workflow.ICase;

if(in.isAddingAdhocToOtherTask){
	ITask originalTask = ivy.wf.findTask(in.originalTaskId);
	if(originalTask != null){
		TaskUtils.setHidePropertyToHideInPortal(originalTask);
	}
}' #txt
AF0 f3 security system #txt
AF0 f3 type selfServiceBPM.Data #txt
AF0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Hide original task</name>
        <nameStyle>18
</nameStyle>
    </language>
</elementInfo>
' #txt
AF0 f3 544 90 112 44 -48 -8 #rect
AF0 f3 @|StepIcon #fIcon
AF0 f16 expr in #txt
AF0 f16 outCond 'in.definedTasks.size() > 0' #txt
AF0 f16 510 112 544 112 #arcP
AF0 f4 expr out #txt
AF0 f4 656 112 704 112 #arcP
AF0 f12 816 112 864 112 #arcP
AF0 f13 type selfServiceBPM.Data #txt
AF0 f13 1024 96 32 32 0 16 #rect
AF0 f13 @|AlternativeIcon #fIcon
AF0 f14 expr out #txt
AF0 f14 976 112 1024 112 #arcP
AF0 f11 expr in #txt
AF0 f11 1056 112 1097 112 #arcP
AF0 f15 expr in #txt
AF0 f15 496 126 1040 128 #arcP
AF0 f15 1 496 176 #addKink
AF0 f15 2 1040 176 #addKink
AF0 f15 1 0.49818840579710144 0 0 #arcLabel
>Proto AF0 .type selfServiceBPM.Data #txt
>Proto AF0 .processKind NORMAL #txt
>Proto AF0 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <swimlaneLabel>Ad hoc WF</swimlaneLabel>
    </language>
    <swimlaneOrientation>false</swimlaneOrientation>
    <swimlaneSize>225</swimlaneSize>
    <swimlaneColor>-1</swimlaneColor>
    <swimlaneType>POOL</swimlaneType>
    <swimlaneSpaceBefore>16</swimlaneSpaceBefore>
</elementInfo>
' #txt
>Proto AF0 0 0 32 24 18 0 #rect
>Proto AF0 @|BIcon #fIcon
1b1 S10 .resExport export #txt
1b1 S10 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language lang="en">
        <name>User Task</name>
        <nameStyle>9,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
1b1 S10 520 202 112 44 -28 -8 #rect
1b1 S10 @|BIcon #fIcon
1b1 f6 type selfServiceBPM.Data #txt
1b1 f6 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>more Tasks?</name>
        <nameStyle>11
</nameStyle>
    </language>
</elementInfo>
' #txt
1b1 f6 114 210 28 28 2 19 #rect
1b1 f6 @|AlternativeIcon #fIcon
1b1 g0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language lang="en">
        <name>in 1</name>
    </language>
</elementInfo>
' #txt
1b1 g0 35 211 26 26 -13 18 #rect
1b1 g0 @|MIGIcon #fIcon
1b1 f0 61 224 114 224 #arcP
1b1 g1 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language lang="en">
        <name>out 1</name>
    </language>
</elementInfo>
' #txt
1b1 g1 243 403 26 26 -19 15 #rect
1b1 g1 @|MOGIcon #fIcon
1b1 f1 expr in #txt
1b1 f1 outCond in.definedTasks.size()==0 #txt
1b1 f1 128 238 243 416 #arcP
1b1 f1 1 128 416 #addKink
1b1 f1 1 0.38393890385103746 0 0 #arcLabel
1b1 f5 actionDecl 'selfServiceBPM.Data out;
' #txt
1b1 f5 actionTable 'out=in;
' #txt
1b1 f5 actionCode 'out.nextTask = in.definedTasks.removeGet(0);

out.nextTask.setSubject(in.caseInfo.subject);


' #txt
1b1 f5 type selfServiceBPM.Data #txt
1b1 f5 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>next Task</name>
        <nameStyle>9
</nameStyle>
    </language>
</elementInfo>
' #txt
1b1 f5 220 204 104 40 -24 -8 #rect
1b1 f5 @|StepIcon #fIcon
1b1 f4 expr in #txt
1b1 f4 142 224 220 224 #arcP
1b1 f3 beanConfig '"{/emailSubject ""<%=in.caseInfo.kind%> <%=in.caseInfo.subject%>""/emailFrom ""noreply@ivyserver.local""/emailReplyTo """"/emailTo ""<%=ivy.case.getCreatorUser().getEMailAddress()%>""/emailCC """"/emailBCC """"/exceptionMissingEmailAttachments ""false""/emailMessage ""<html>\\n<head>\\n<meta http-equiv=\\""Content-Type\\"" content=\\""text/html; charset=iso-8859-1\\""></head>\\n<body \\tstyle=\\""font-family: \\''Segoe UI\\'', \\''Helvetica Neue\\'', Helvetica, Arial, sans-serif; font-size:10pt;\\"">\\n<div style=\\""background-color:#575656; height:100px\\"">\\n<%=ivy.cms.co(\\""/Project/Banner\\"")%>\\n</div>\\n<div style=\\""background-color:#eaeaea; height:30px; padding-top:10px\\"">AXON IVY Self Service Ad-hoc Workflow</div>\\n<div>\\n<p>\\n<b><%=in.caseInfo.kind%> <%=in.caseInfo.subject%></b>\\n</p>\\n<%=in.caseInfo.description%>\\n<hr>\\n<p>\\n<%=in.answer%>\\n</p>\\n<p> Autogenerated Mail <br>\\nPowered by Axon.ivy Workflow </p>\\n</div>\\n</body>\\n</html>\\n""/emailAttachments * }"' #txt
1b1 f3 type selfServiceBPM.Data #txt
1b1 f3 timeout 0 #txt
1b1 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>send Mail</name>
        <nameStyle>9
</nameStyle>
    </language>
</elementInfo>
' #txt
1b1 f3 680 298 112 44 -26 -8 #rect
1b1 f3 @|EMailIcon #fIcon
1b1 f8 type selfServiceBPM.Data #txt
1b1 f8 368 208 32 32 0 16 #rect
1b1 f8 @|AlternativeIcon #fIcon
1b1 f10 expr out #txt
1b1 f10 324 224 368 224 #arcP
1b1 f13 actionDecl 'selfServiceBPM.Data out;
' #txt
1b1 f13 actionTable 'out=in1;
' #txt
1b1 f13 outTypes "selfServiceBPM.Data" #txt
1b1 f13 outLinks "TaskA.ivp" #txt
1b1 f13 taskData 'TaskA.EXPRI=2
TaskA.EXROL=Everybody
TaskA.EXTYPE=0
TaskA.NAM=SYSTEM Send <%\=in1.caseInfo.kind%> Mail 
TaskA.PRI=2
TaskA.ROL=SYSTEM
TaskA.SKIP_TASK_LIST=false
TaskA.TYPE=0' #txt
1b1 f13 type selfServiceBPM.Data #txt
1b1 f13 template "/ProcessPages/AdHocWF/portalHome.ivc" #txt
1b1 f13 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>SYSTEM</name>
        <nameStyle>6
</nameStyle>
    </language>
</elementInfo>
' #txt
1b1 f13 417 305 30 30 -23 17 #rect
1b1 f13 @|TaskSwitchSimpleIcon #fIcon
1b1 f14 expr in #txt
1b1 f14 type selfServiceBPM.Data #txt
1b1 f14 var in1 #txt
1b1 f14 template /ProcessPages/AdHocWF/portalHome.ivc #txt
1b1 f14 384 240 417 320 #arcP
1b1 f14 1 384 320 #addKink
1b1 f14 1 0.20334162686118912 0 0 #arcLabel
1b1 f16 expr out #txt
1b1 f16 792 320 128 210 #arcP
1b1 f16 1 840 320 #addKink
1b1 f16 2 840 144 #addKink
1b1 f16 3 128 144 #addKink
1b1 f16 2 0.00843115672759725 0 0 #arcLabel
1b1 f17 actionDecl 'selfServiceBPM.Data out;
' #txt
1b1 f17 actionTable 'out=in;
' #txt
1b1 f17 actionCode 'import ch.ivyteam.ivy.workflow.INote;
StringBuffer sb = new StringBuffer();
for(INote note: ivy.case.getNotes())
{
	sb.append(note.getCreationTimestamp());
	sb.append(" ");
	sb.append(note.getWritterName());
	sb.append(": ");
	sb.append(note.getMessage());
	sb.append("<br>");
}	

out.answer = sb.toString();

ivy.log.debug("Email: {0}", ivy.case.getCreatorUser().getEMailAddress());' #txt
1b1 f17 type selfServiceBPM.Data #txt
1b1 f17 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>setup Mail</name>
        <nameStyle>10
</nameStyle>
    </language>
</elementInfo>
' #txt
1b1 f17 520 298 112 44 -28 -8 #rect
1b1 f17 @|StepIcon #fIcon
1b1 f18 expr data #txt
1b1 f18 outCond ivp=="TaskA.ivp" #txt
1b1 f18 447 320 520 320 #arcP
1b1 f15 expr out #txt
1b1 f15 632 320 680 320 #arcP
1b1 f19 expr in #txt
1b1 f19 outCond 'in.nextTask.actor !="SYSTEM"' #txt
1b1 f19 400 224 520 224 #arcP
1b1 f20 632 224 128 210 #arcP
1b1 f20 1 840 224 #addKink
1b1 f20 2 840 144 #addKink
1b1 f20 3 128 144 #addKink
1b1 f20 2 0.3478475756443725 0 0 #arcLabel
>Proto 1b0 0 0 32 24 18 0 #rect
>Proto 1b0 @|BIcon #fIcon
Ct0 f9 actionDecl 'selfServiceBPM.Data out;
' #txt
Ct0 f9 actionTable 'out=in1;
' #txt
Ct0 f9 outTypes "selfServiceBPM.Data" #txt
Ct0 f9 outLinks "TaskA.ivp" #txt
Ct0 f9 taskData 'TaskA.DESC=<%\=in1.nextTask.description%>
TaskA.EXP=in1.nextTask.until\!\=null ? in1.nextTask.until - new DateTime() \: null
TaskA.EXPRI=0
TaskA.EXROL=in1.nextTask.actoruser
TaskA.EXTYPE=3
TaskA.NAM=<%\=in1.nextTask.kind%> <%\=in1.nextTask.subject%>
TaskA.PRI=2
TaskA.ROL=in1.nextTask.actoruser
TaskA.SKIP_TASK_LIST=false
TaskA.TYPE=3
TaskA.customFields.STRING.KindCode=in1.nextTask.kind' #txt
Ct0 f9 type selfServiceBPM.Data #txt
Ct0 f9 template "/ProcessPages/AdHocWF/portalHome.ivc" #txt
Ct0 f9 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>task list</name>
        <nameStyle>9
</nameStyle>
    </language>
</elementInfo>
' #txt
Ct0 f9 562 250 28 28 -17 20 #rect
Ct0 f9 @|TaskSwitchSimpleIcon #fIcon
Ct0 f11 targetWindow NEW:card: #txt
Ct0 f11 targetDisplay TOP #txt
Ct0 f11 richDialogId agileBPM.task_Form #txt
Ct0 f11 startMethod start(selfServiceBPM.CaseDef,selfServiceBPM.TaskDef,List<selfServiceBPM.TaskDef>) #txt
Ct0 f11 type selfServiceBPM.Data #txt
Ct0 f11 requestActionDecl '<selfServiceBPM.CaseDef caseInfo, selfServiceBPM.TaskDef nextTask, List<selfServiceBPM.TaskDef> definedTasks> param;' #txt
Ct0 f11 requestMappingAction 'param.caseInfo=in.caseInfo;
param.nextTask=in.nextTask;
param.definedTasks=in.definedTasks;
' #txt
Ct0 f11 responseActionDecl 'selfServiceBPM.Data out;
' #txt
Ct0 f11 responseMappingAction 'out=in;
out.definedTasks=result.definedTasks;
' #txt
Ct0 f11 windowConfiguration '* ' #txt
Ct0 f11 isAsynch false #txt
Ct0 f11 isInnerRd false #txt
Ct0 f11 userContext '* ' #txt
Ct0 f11 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>taskForm</name>
        <nameStyle>8
</nameStyle>
    </language>
</elementInfo>
' #txt
Ct0 f11 636 244 104 40 -24 -8 #rect
Ct0 f11 @|RichDialogIcon #fIcon
Ct0 f12 expr data #txt
Ct0 f12 outCond ivp=="TaskA.ivp" #txt
Ct0 f12 590 264 636 264 #arcP
Ct0 g0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language lang="en">
        <name>in 1</name>
    </language>
</elementInfo>
' #txt
Ct0 g0 51 147 26 26 2 14 #rect
Ct0 g0 @|MIGIcon #fIcon
Ct0 g1 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language lang="en">
        <name>out 1</name>
    </language>
</elementInfo>
' #txt
Ct0 g1 1195 147 26 26 -16 13 #rect
Ct0 g1 @|MOGIcon #fIcon
Ct0 f2 actionDecl 'selfServiceBPM.Data out;
' #txt
Ct0 f2 actionTable 'out=in1;
' #txt
Ct0 f2 outTypes "selfServiceBPM.Data","selfServiceBPM.Data" #txt
Ct0 f2 outLinks "TaskA.ivp","TaskB.ivp" #txt
Ct0 f2 taskData 'TaskA.EXPRI=2
TaskA.EXROL=Everybody
TaskA.EXTYPE=0
TaskA.NAM=Split SYSTEM
TaskA.PRI=2
TaskA.ROL=SYSTEM
TaskA.SKIP_TASK_LIST=true
TaskA.TYPE=0
TaskB.EXPRI=2
TaskB.EXROL=Everybody
TaskB.EXTYPE=0
TaskB.NAM=Split Usertask
TaskB.PRI=2
TaskB.ROL=SYSTEM
TaskB.SKIP_TASK_LIST=false
TaskB.TYPE=0' #txt
Ct0 f2 type selfServiceBPM.Data #txt
Ct0 f2 template "/ProcessPages/AdHocWF/portalHome.ivc" #txt
Ct0 f2 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>SYSTEM spilt</name>
        <nameStyle>12
</nameStyle>
    </language>
</elementInfo>
' #txt
Ct0 f2 424 144 32 32 9 -32 #rect
Ct0 f2 @|TaskSwitchIcon #fIcon
Ct0 f4 actionDecl 'selfServiceBPM.Data out;
' #txt
Ct0 f4 actionTable 'out=in1;
out.definedTasks=in2.definedTasks;
' #txt
Ct0 f4 outTypes "selfServiceBPM.Data" #txt
Ct0 f4 outLinks "TaskA.ivp" #txt
Ct0 f4 taskData 'TaskA.EXPRI=2
TaskA.EXROL=Everybody
TaskA.EXTYPE=0
TaskA.NAM=SYSTEM join
TaskA.PRI=2
TaskA.ROL=SYSTEM
TaskA.SKIP_TASK_LIST=false
TaskA.TYPE=0' #txt
Ct0 f4 type selfServiceBPM.Data #txt
Ct0 f4 template "/ProcessPages/AdHocWF/portalHome.ivc" #txt
Ct0 f4 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>SYSTEM join</name>
        <nameStyle>11
</nameStyle>
    </language>
</elementInfo>
' #txt
Ct0 f4 808 144 32 32 12 -29 #rect
Ct0 f4 @|TaskSwitchIcon #fIcon
Ct0 f6 type selfServiceBPM.Data #txt
Ct0 f6 376 144 32 32 0 16 #rect
Ct0 f6 @|AlternativeIcon #fIcon
Ct0 f3 expr in #txt
Ct0 f3 type selfServiceBPM.Data #txt
Ct0 f3 var in1 #txt
Ct0 f3 template /ProcessPages/AdHocWF/portalHome.ivc #txt
Ct0 f3 408 160 424 160 #arcP
Ct0 f8 type selfServiceBPM.Data #txt
Ct0 f8 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>more?</name>
        <nameStyle>5
</nameStyle>
    </language>
</elementInfo>
' #txt
Ct0 f8 560 144 32 32 -18 18 #rect
Ct0 f8 @|AlternativeIcon #fIcon
Ct0 f10 expr data #txt
Ct0 f10 outCond ivp=="TaskA.ivp" #txt
Ct0 f10 456 160 560 160 #arcP
Ct0 f0 expr data #txt
Ct0 f0 outCond ivp=="TaskB.ivp" #txt
Ct0 f0 type selfServiceBPM.Data #txt
Ct0 f0 var in1 #txt
Ct0 f0 template /ProcessPages/AdHocWF/portalHome.ivc #txt
Ct0 f0 440 176 562 264 #arcP
Ct0 f0 1 440 264 #addKink
Ct0 f0 1 0.19818879887184085 0 0 #arcLabel
Ct0 f14 type selfServiceBPM.Data #txt
Ct0 f14 752 144 32 32 0 16 #rect
Ct0 f14 @|AlternativeIcon #fIcon
Ct0 f15 expr in #txt
Ct0 f15 592 160 752 160 #arcP
Ct0 f17 type selfServiceBPM.Data #txt
Ct0 f17 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>more?</name>
        <nameStyle>5
</nameStyle>
    </language>
</elementInfo>
' #txt
Ct0 f17 936 144 32 32 -18 18 #rect
Ct0 f17 @|AlternativeIcon #fIcon
Ct0 f18 expr data #txt
Ct0 f18 outCond ivp=="TaskA.ivp" #txt
Ct0 f18 840 160 936 160 #arcP
Ct0 f18 0 0.06855791962174941 0 0 #arcLabel
Ct0 f20 actionDecl 'selfServiceBPM.Data out;
' #txt
Ct0 f20 actionTable 'out=in;
' #txt
Ct0 f20 actionCode 'out.nextTask.actorusers.clear();

List<String> names = in.nextTask.actor.split(",");
for(String name: names)
{
	if(name.trim().length()>0)
	{
		out.nextTask.actorusers.add(name.trim());
	}	
}	

out.nextTask.n = out.nextTask.actorusers.size();
out.nextTask.count = out.nextTask.actorusers.size();

out.nextTask.actoruser = in.nextTask.actorusers.get(out.nextTask.n-1);

' #txt
Ct0 f20 type selfServiceBPM.Data #txt
Ct0 f20 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>init</name>
        <nameStyle>4
</nameStyle>
    </language>
</elementInfo>
' #txt
Ct0 f20 112 138 112 44 -8 -8 #rect
Ct0 f20 @|StepIcon #fIcon
Ct0 f22 actionDecl 'selfServiceBPM.Data out;
' #txt
Ct0 f22 actionTable 'out=in;
out.nextTask.n=in.nextTask.n-1;
' #txt
Ct0 f22 actionCode 'out.nextTask.actoruser = in.nextTask.actorusers.get(out.nextTask.n-1);' #txt
Ct0 f22 type selfServiceBPM.Data #txt
Ct0 f22 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>n-1</name>
        <nameStyle>3
</nameStyle>
    </language>
</elementInfo>
' #txt
Ct0 f22 424 66 112 44 -9 -8 #rect
Ct0 f22 @|StepIcon #fIcon
Ct0 f23 expr in #txt
Ct0 f23 outCond in.nextTask.n>1 #txt
Ct0 f23 576 144 536 88 #arcP
Ct0 f23 1 576 88 #addKink
Ct0 f23 1 0.5969001398703495 0 0 #arcLabel
Ct0 f13 expr out #txt
Ct0 f13 424 88 392 144 #arcP
Ct0 f13 1 392 88 #addKink
Ct0 f13 1 0.5969001398703495 0 0 #arcLabel
Ct0 f24 actionDecl 'selfServiceBPM.Data out;
' #txt
Ct0 f24 actionTable 'out=in;
out.nextTask.n=in.nextTask.n+1;
' #txt
Ct0 f24 type selfServiceBPM.Data #txt
Ct0 f24 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>n+1</name>
        <nameStyle>3
</nameStyle>
    </language>
</elementInfo>
' #txt
Ct0 f24 800 66 112 44 -10 -8 #rect
Ct0 f24 @|StepIcon #fIcon
Ct0 f25 expr in #txt
Ct0 f25 outCond 'in.nextTask.n <in.nextTask.count' #txt
Ct0 f25 952 144 912 88 #arcP
Ct0 f25 1 952 88 #addKink
Ct0 f25 1 0.6083441154037813 0 0 #arcLabel
Ct0 f19 expr out #txt
Ct0 f19 800 88 768 144 #arcP
Ct0 f19 1 768 88 #addKink
Ct0 f19 1 0.6083441154037813 0 0 #arcLabel
Ct0 f21 77 160 112 160 #arcP
Ct0 f5 expr in #txt
Ct0 f5 type selfServiceBPM.Data #txt
Ct0 f5 var in1 #txt
Ct0 f5 template /ProcessPages/AdHocWF/portalHome.ivc #txt
Ct0 f5 784 160 808 160 #arcP
Ct0 f16 expr out #txt
Ct0 f16 type selfServiceBPM.Data #txt
Ct0 f16 var in2 #txt
Ct0 f16 template /ProcessPages/AdHocWF/portalHome.ivc #txt
Ct0 f16 740 264 824 176 #arcP
Ct0 f16 1 824 264 #addKink
Ct0 f16 0 0.9183817963969344 0 0 #arcLabel
Ct0 f30 type selfServiceBPM.Data #txt
Ct0 f30 1056 144 32 32 0 16 #rect
Ct0 f30 @|AlternativeIcon #fIcon
Ct0 f31 expr in #txt
Ct0 f31 968 160 1056 160 #arcP
Ct0 f1 expr in #txt
Ct0 f1 1088 160 1195 160 #arcP
Ct0 f7 expr in #txt
Ct0 f7 outCond !in.nextTask.isOriginalTask #txt
Ct0 f7 312 160 376 160 #arcP
Ct0 f27 expr out #txt
Ct0 f27 224 160 280 160 #arcP
Ct0 f26 type selfServiceBPM.Data #txt
Ct0 f26 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Is Not Original Task</name>
        <nameStyle>20,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ct0 f26 280 144 32 32 -54 18 #rect
Ct0 f26 @|AlternativeIcon #fIcon
Ct0 f28 expr in #txt
Ct0 f28 296 176 1072 176 #arcP
Ct0 f28 1 296 352 #addKink
Ct0 f28 2 1072 352 #addKink
Ct0 f28 1 0.5032864930698892 0 0 #arcLabel
>Proto Ct0 0 0 32 24 18 0 #rect
>Proto Ct0 @|BIcon #fIcon
AF0 f1 mainOut f6 tail #connect
AF0 f6 head f5 in #connect
AF0 f0 mainOut f8 tail #connect
AF0 f8 head f7 mainIn #connect
AF0 f7 mainOut f2 tail #connect
AF0 f2 head f1 mainIn #connect
AF0 f5 out f16 tail #connect
AF0 f16 head f3 mainIn #connect
AF0 f3 mainOut f4 tail #connect
AF0 f4 head 1b0 g0 #connect
AF0 1b0 g1 f12 tail #connect
AF0 f12 head f9 mainIn #connect
AF0 f9 mainOut f14 tail #connect
AF0 f14 head f13 in #connect
AF0 f13 out f11 tail #connect
AF0 f11 head f10 mainIn #connect
AF0 f5 out f15 tail #connect
AF0 f15 head f13 in #connect
1b1 g0 m f0 tail #connect
1b1 f0 head f6 in #connect
1b1 f1 head g1 m #connect
1b1 f6 out f1 tail #connect
1b1 f6 out f4 tail #connect
1b1 f4 head f5 mainIn #connect
1b1 f5 mainOut f10 tail #connect
1b1 f10 head f8 in #connect
1b1 f14 head f13 in #connect
1b1 f3 mainOut f16 tail #connect
1b1 f16 head f6 in #connect
1b1 f13 out f18 tail #connect
1b1 f18 head f17 mainIn #connect
1b1 f17 mainOut f15 tail #connect
1b1 f15 head f3 mainIn #connect
1b1 f19 head S10 g0 #connect
1b1 S10 g1 f20 tail #connect
1b1 f20 head f6 in #connect
1b1 f8 out f19 tail #connect
1b1 f8 out f14 tail #connect
1b1 0 0 944 488 0 #ivRect
Ct0 f9 out f12 tail #connect
Ct0 f12 head f11 mainIn #connect
Ct0 f6 out f3 tail #connect
Ct0 f3 head f2 in #connect
Ct0 f2 out f10 tail #connect
Ct0 f10 head f8 in #connect
Ct0 f2 out f0 tail #connect
Ct0 f0 head f9 in #connect
Ct0 f15 head f14 in #connect
Ct0 f4 out f18 tail #connect
Ct0 f18 head f17 in #connect
Ct0 f8 out f23 tail #connect
Ct0 f23 head f22 mainIn #connect
Ct0 f8 out f15 tail #connect
Ct0 f22 mainOut f13 tail #connect
Ct0 f13 head f6 in #connect
Ct0 f17 out f25 tail #connect
Ct0 f25 head f24 mainIn #connect
Ct0 f24 mainOut f19 tail #connect
Ct0 f19 head f14 in #connect
Ct0 g0 m f21 tail #connect
Ct0 f21 head f20 mainIn #connect
Ct0 f14 out f5 tail #connect
Ct0 f5 head f4 in #connect
Ct0 f11 mainOut f16 tail #connect
Ct0 f16 head f4 in #connect
Ct0 f20 mainOut f27 tail #connect
Ct0 f27 head f26 in #connect
Ct0 f26 out f7 tail #connect
Ct0 f7 head f6 in #connect
Ct0 f17 out f31 tail #connect
Ct0 f31 head f30 in #connect
Ct0 f30 out f1 tail #connect
Ct0 f1 head g1 m #connect
Ct0 f26 out f28 tail #connect
Ct0 f28 head f30 in #connect
Ct0 0 0 1352 408 0 #ivRect
