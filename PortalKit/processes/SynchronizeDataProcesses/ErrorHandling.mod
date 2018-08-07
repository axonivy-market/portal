[Ivy]
[>Created: Wed Oct 28 11:58:25 ICT 2015]
15059FD9F5567E43 3.17 #module
>Proto >Proto Collection #zClass
Eg0 ErrorHandling Big #zClass
Eg0 B #cInfo
Eg0 #process
Eg0 @TextInP .resExport .resExport #zField
Eg0 @TextInP .type .type #zField
Eg0 @TextInP .processKind .processKind #zField
Eg0 @AnnotationInP-0n ai ai #zField
Eg0 @MessageFlowInP-0n messageIn messageIn #zField
Eg0 @MessageFlowOutP-0n messageOut messageOut #zField
Eg0 @TextInP .xml .xml #zField
Eg0 @TextInP .responsibility .responsibility #zField
Eg0 @StartSub f0 '' #zField
Eg0 @EndSub f1 '' #zField
Eg0 @Alternative f19 '' #zField
Eg0 @TaskSwitch f20 '' #zField
Eg0 @GridStep f22 '' #zField
Eg0 @PushWFArc f23 '' #zField
Eg0 @PushWFArc f3 '' #zField
Eg0 @PushWFArc f2 '' #zField
Eg0 @PushWFArc f4 '' #zField
Eg0 @TkArc f21 '' #zField
>Proto Eg0 Eg0 ErrorHandling #zField
Eg0 f0 inParamDecl '<java.lang.String action,java.lang.Integer retryTime,java.lang.String message> param;' #txt
Eg0 f0 inParamTable 'out.action=param.action;
out.message=param.message;
out.retryTime=param.retryTime;
' #txt
Eg0 f0 outParamDecl '<> result;
' #txt
Eg0 f0 actionDecl 'ch.ivy.add.portalkit.synchronization.ErrorHandlingData out;
' #txt
Eg0 f0 callSignature handleWebServiceException(String,Integer,String) #txt
Eg0 f0 type ch.ivy.add.portalkit.synchronization.ErrorHandlingData #txt
Eg0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>handleWebServiceException(String,Integer,String)</name>
        <nameStyle>48,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Eg0 f0 123 83 26 26 14 0 #rect
Eg0 f0 @|StartSubIcon #fIcon
Eg0 f1 type ch.ivy.add.portalkit.synchronization.ErrorHandlingData #txt
Eg0 f1 123 339 26 26 14 0 #rect
Eg0 f1 @|EndSubIcon #fIcon
Eg0 f19 type ch.ivy.add.portalkit.synchronization.ErrorHandlingData #txt
Eg0 f19 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>reach maximum 
retry times?</name>
        <nameStyle>27,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Eg0 f19 122 234 28 28 -87 23 #rect
Eg0 f19 @|AlternativeIcon #fIcon
Eg0 f20 actionDecl 'ch.ivy.add.portalkit.synchronization.ErrorHandlingData out;
' #txt
Eg0 f20 actionTable 'out=in1;
' #txt
Eg0 f20 outTypes "ch.ivy.add.portalkit.synchronization.ErrorHandlingData" #txt
Eg0 f20 outLinks "TaskA.ivp" #txt
Eg0 f20 taskData 'TaskA.DESC=Exception occur in progressing of synchronization portal data at time around "<%\=new DateTime().toString();%>\r"\\n\n<%\=in1.message%>
TaskA.EXPRI=2
TaskA.EXROL=Everybody
TaskA.EXTYPE=0
TaskA.NAM=Synchronize Portal Data Error\: <%\=in1.action%>
TaskA.PRI=0
TaskA.ROL=AXONIVY_PORTAL_ADMIN
TaskA.SKIP_TASK_LIST=false
TaskA.TYPE=0' #txt
Eg0 f20 taskAction 'import ch.ivyteam.ivy.workflow.TaskDefinition;
List<TaskDefinition> taskDefinitions;
TaskDefinition taskDef;import ch.ivyteam.ivy.request.impl.DefaultCalendarProxy;
DefaultCalendarProxy calendarProxy = ivy.cal as DefaultCalendarProxy;
taskDef = new TaskDefinition();
taskDef.setStartRequestPath("TaskA.ivp");
taskDef.setName(engine.expandMacros("Synchronize Portal Data Error: <%=in1.action%>"));
taskDef.setDescription(engine.expandMacros("Exception occur in progressing of synchronization portal data at time around \"<%=new DateTime().toString();%>\"\n
<%=in1.message%>"));
taskDef.setAutoStartTask(false);
taskDef.setActivator("AXONIVY_PORTAL_ADMIN");
taskDef.setPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(0));
taskDef.setExpiryActivator("Everybody");
taskDef.setExpiryPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
taskDefinitions.add(taskDef);
' #txt
Eg0 f20 type ch.ivy.add.portalkit.synchronization.ErrorHandlingData #txt
Eg0 f20 template "" #txt
Eg0 f20 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Create task for Admin 
to handle this case</name>
        <nameStyle>42,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Eg0 f20 242 234 28 28 6 18 #rect
Eg0 f20 @|TaskSwitchIcon #fIcon
Eg0 f22 actionDecl 'ch.ivy.add.portalkit.synchronization.ErrorHandlingData out;
' #txt
Eg0 f22 actionTable 'out=in;
' #txt
Eg0 f22 actionCode 'import ch.ivy.addon.portalkit.service.IvyAdapterService;
IvyAdapterService service = new IvyAdapterService();
in.maximumRetryTimes = service.getMaximumRetryPortalDataSynchonizationTimes();
' #txt
Eg0 f22 type ch.ivy.add.portalkit.synchronization.ErrorHandlingData #txt
Eg0 f22 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>get maximum retry time</name>
        <nameStyle>22,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Eg0 f22 118 164 36 24 23 -10 #rect
Eg0 f22 @|StepIcon #fIcon
Eg0 f23 expr out #txt
Eg0 f23 136 188 136 234 #arcP
Eg0 f23 0 0.4895833333333333 -8 0 #arcLabel
Eg0 f3 expr out #txt
Eg0 f3 136 109 136 164 #arcP
Eg0 f2 expr in #txt
Eg0 f2 136 262 136 339 #arcP
Eg0 f4 expr data #txt
Eg0 f4 outCond ivp=="TaskA.ivp" #txt
Eg0 f4 256 262 136 339 #arcP
Eg0 f4 1 256 304 #addKink
Eg0 f4 2 136 304 #addKink
Eg0 f4 1 0.4715909090909091 0 0 #arcLabel
Eg0 f21 expr in #txt
Eg0 f21 outCond 'in.retryTime  > in.maximumRetryTimes' #txt
Eg0 f21 type ch.ivy.add.portalkit.synchronization.ErrorHandlingData #txt
Eg0 f21 var in1 #txt
Eg0 f21 150 248 242 248 #arcP
Eg0 f21 0 0.4895833333333333 0 9 #arcLabel
>Proto Eg0 .type ch.ivy.add.portalkit.synchronization.ErrorHandlingData #txt
>Proto Eg0 .processKind CALLABLE_SUB #txt
>Proto Eg0 0 0 32 24 18 0 #rect
>Proto Eg0 @|BIcon #fIcon
Eg0 f21 head f20 in #connect
Eg0 f22 mainOut f23 tail #connect
Eg0 f23 head f19 in #connect
Eg0 f19 out f21 tail #connect
Eg0 f0 mainOut f3 tail #connect
Eg0 f3 head f22 mainIn #connect
Eg0 f19 out f2 tail #connect
Eg0 f2 head f1 mainIn #connect
Eg0 f20 out f4 tail #connect
Eg0 f4 head f1 mainIn #connect
