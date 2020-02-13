[Ivy]
170322ABC60F09B6 7.5.0 #module
>Proto >Proto Collection #zClass
Pt0 ProcurementRequestParallel Big #zClass
Pt0 B #cInfo
Pt0 #process
Pt0 @TextInP .type .type #zField
Pt0 @TextInP .processKind .processKind #zField
Pt0 @AnnotationInP-0n ai ai #zField
Pt0 @MessageFlowInP-0n messageIn messageIn #zField
Pt0 @MessageFlowOutP-0n messageOut messageOut #zField
Pt0 @TextInP .xml .xml #zField
Pt0 @TextInP .responsibility .responsibility #zField
Pt0 @UserDialog f6 '' #zField
Pt0 @StartRequest f0 '' #zField
Pt0 @EndTask f1 '' #zField
Pt0 @TaskSwitch f7 '' #zField
Pt0 @UserDialog f3 '' #zField
Pt0 @TaskSwitch f2 '' #zField
Pt0 @UserDialog f5 '' #zField
Pt0 @EMail f8 '' #zField
Pt0 @PushWFArc f4 '' #zField
Pt0 @TkArc f9 '' #zField
Pt0 @PushWFArc f10 '' #zField
Pt0 @PushWFArc f11 '' #zField
Pt0 @PushWFArc f15 '' #zField
Pt0 @UserDialog f16 '' #zField
Pt0 @PushWFArc f17 '' #zField
Pt0 @Alternative f19 '' #zField
Pt0 @PushWFArc f20 '' #zField
Pt0 @PushWFArc f21 '' #zField
Pt0 @TkArc f12 '' #zField
Pt0 @TkArc f13 '' #zField
Pt0 @InfoButton f14 '' #zField
Pt0 @InfoButton f24 '' #zField
Pt0 @AnnotationArc f25 '' #zField
Pt0 @TaskSwitchSimple f26 '' #zField
Pt0 @TkArc f27 '' #zField
Pt0 @PushWFArc f18 '' #zField
>Proto Pt0 Pt0 ProcurementRequestParallel #zField
Pt0 f6 dialogId com.axonivy.portal.businessuserexamples.humantask.VerifyRequest #txt
Pt0 f6 startMethod start(com.axonivy.portal.businessuserexamples.humantask.ProcurementRequest) #txt
Pt0 f6 requestActionDecl '<com.axonivy.portal.businessuserexamples.humantask.ProcurementRequest procurementRequest> param;' #txt
Pt0 f6 requestMappingAction 'param.procurementRequest=in;
' #txt
Pt0 f6 responseActionDecl 'workflow.humantask.ProcurementRequest out;
' #txt
Pt0 f6 responseMappingAction 'out=in;
out.activityLog=in.activityLog.add(result.logEntry);
out.dataOkManager=result.dataOk;
' #txt
Pt0 f6 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Verify Request</name>
        <nameStyle>14,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Pt0 f6 360 402 112 44 -39 -8 #rect
Pt0 f6 @|UserDialogIcon #fIcon
Pt0 f0 outLink start.ivp #txt
Pt0 f0 inParamDecl '<> param;' #txt
Pt0 f0 requestEnabled true #txt
Pt0 f0 triggerEnabled false #txt
Pt0 f0 callSignature start() #txt
Pt0 f0 persist false #txt
Pt0 f0 startName '2.2: <%=ivy.cms.co("/ProcessDescriptions/procurementRequest")%> (<%=ivy.cms.co("/ProcessDescriptions/parallelTaskExample")%>)' #txt
Pt0 f0 startDescription <%=ivy.cms.co("/ProcessDescriptions/procurementRequestParallelDescription")%> #txt
Pt0 f0 taskData 'TaskTriggered.CATEGORY=Input/Procurement
TaskTriggered.EXPRI=2
TaskTriggered.EXROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.PRI=2
TaskTriggered.ROL=Everybody
TaskTriggered.TYPE=0' #txt
Pt0 f0 caseData case.category=Procurement/Request #txt
Pt0 f0 showInStartList 1 #txt
Pt0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start.ivp</name>
        <nameStyle>9,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Pt0 f0 @C|.responsibility Employee #txt
Pt0 f0 65 169 30 30 -21 17 #rect
Pt0 f0 @|StartRequestIcon #fIcon
Pt0 f1 857 169 30 30 0 15 #rect
Pt0 f1 @|EndIcon #fIcon
Pt0 f7 actionTable 'out=in1;
out.activityLog=in1.activityLog.removeAll(in2.activityLog).addAll(in2.activityLog);
out.dataOkManager=in2.dataOkManager;
out.dataOkTeamLeader=in1.dataOkTeamLeader;
' #txt
Pt0 f7 outLinks "TaskB.ivp" #txt
Pt0 f7 caseData case.category=System #txt
Pt0 f7 taskData 'TaskA.EXPRI=2
TaskA.EXROL=Everybody
TaskA.EXTYPE=0
TaskA.PRI=2
TaskA.ROL=SYSTEM
TaskA.SKIP_TASK_LIST=true
TaskA.TYPE=0
TaskB.EXPRI=2
TaskB.EXROL=Everybody
TaskB.EXTYPE=0
TaskB.PRI=2
TaskB.ROL=SYSTEM
TaskB.SKIP_TASK_LIST=true
TaskB.TYPE=0' #txt
Pt0 f7 template "" #txt
Pt0 f7 512 288 32 32 0 16 #rect
Pt0 f7 @|TaskSwitchIcon #fIcon
Pt0 f3 dialogId com.axonivy.portal.businessuserexamples.humantask.EnterRequest #txt
Pt0 f3 startMethod start() #txt
Pt0 f3 requestActionDecl '<> param;' #txt
Pt0 f3 responseActionDecl 'workflow.humantask.ProcurementRequest out;
' #txt
Pt0 f3 responseMappingAction 'out=result.procurementRequestData;
out.activityLog=in.activityLog.add(result.logEntry);
out.totalPrice=result.procurementRequestData.amount * result.procurementRequestData.pricePerUnit;
' #txt
Pt0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Enter Request</name>
        <nameStyle>13,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Pt0 f3 152 162 112 44 -39 -8 #rect
Pt0 f3 @|UserDialogIcon #fIcon
Pt0 f2 actionTable 'out=in1;
' #txt
Pt0 f2 outLinks "TaskA.ivp","TaskB.ivp" #txt
Pt0 f2 taskData 'TaskA.CATEGORY=Review/Procurement
TaskA.EXPRI=2
TaskA.EXROL=Everybody
TaskA.EXTYPE=0
TaskA.NAM=<%\=ivy.cms.co("/TaskDescriptions/verifyRequest")%>\: <%\=in1.amount%> <%\=ivy.cms.co("/Dialogs/procurementRequest/piecesOf")%>  ''<%\=in1.description%>'' <%\=ivy.cms.co("/Dialogs/procurementRequest/forTotal")%> <%\=in1.totalPrice%><%\=ivy.cms.co("/TaskDescriptions/currencySymbol")%>
TaskA.PRI=2
TaskA.ROL=Teamleader
TaskA.SKIP_TASK_LIST=false
TaskA.TYPE=0
TaskB.CATEGORY=Review/Procurement
TaskB.EXPRI=2
TaskB.EXROL=Everybody
TaskB.EXTYPE=0
TaskB.NAM=<%\=ivy.cms.co("/TaskDescriptions/verifyRequest")%>\: <%\=in1.amount%> <%\=ivy.cms.co("/Dialogs/procurementRequest/piecesOf")%> ''<%\=in1.description%>'' <%\=ivy.cms.co("/Dialogs/procurementRequest/forTotal")%> <%\=in1.totalPrice%><%\=ivy.cms.co("/TaskDescriptions/currencySymbol")%>
TaskB.PRI=2
TaskB.ROL=Manager
TaskB.SKIP_TASK_LIST=false
TaskB.TYPE=0' #txt
Pt0 f2 template "" #txt
Pt0 f2 296 288 32 32 0 16 #rect
Pt0 f2 @|TaskSwitchIcon #fIcon
Pt0 f5 dialogId com.axonivy.portal.businessuserexamples.humantask.VerifyRequest #txt
Pt0 f5 startMethod start(com.axonivy.portal.businessuserexamples.humantask.ProcurementRequest) #txt
Pt0 f5 requestActionDecl '<com.axonivy.portal.businessuserexamples.humantask.ProcurementRequest procurementRequest> param;' #txt
Pt0 f5 requestMappingAction 'param.procurementRequest=in;
' #txt
Pt0 f5 responseActionDecl 'workflow.humantask.ProcurementRequest out;
' #txt
Pt0 f5 responseMappingAction 'out=in;
out.activityLog=in.activityLog.add(result.logEntry);
out.dataOkTeamLeader=result.dataOk;
' #txt
Pt0 f5 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Verify Request</name>
        <nameStyle>14,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Pt0 f5 360 282 112 44 -39 -8 #rect
Pt0 f5 @|UserDialogIcon #fIcon
Pt0 f8 beanConfig '"{/emailSubject ""<%=ivy.cms.co(\\""/Emails/yourRequestHasBeen\\"")%> <%=(in.accepted ? ivy.cms.co(\\""/Emails/accepted\\"") : ivy.cms.co(\\""/Emails/declined\\""))%>: <%=in.amount%> <%=ivy.cms.co(\\""/Dialogs/procurementRequest/piecesOf\\"")%>  \\''<%=in.description%>\\'' <%=ivy.cms.co(\\""/Dialogs/procurementRequest/forTotal\\"")%> <%=in.totalPrice%> <%=ivy.cms.co(\\""/Dialogs/procurementRequest/currencySymbol\\"")%>""/emailFrom ""<%=ivy.cms.co(\\""/Emails/senderMail\\"")%>""/emailReplyTo """"/emailTo ""<%=in.requester.email%>""/emailCC """"/emailBCC """"/exceptionMissingEmailAttachments ""false""/emailMessage "" <html> \\n\\t<style type=\\""text/css\\"">\\n     \\t\\t<%=ivy.cms.co(\\""/Styles/Classic\\"")%>\\n\\t</style>\\n\\t\\n\\t<%=ivy.cms.co(\\""/Images/Logo\\"")%>\\n\\n\\t<%=ivy.cms.co(\\""/Emails/procurementRequestNotification\\"")%>\\n</html>""/emailAttachments * }"' #txt
Pt0 f8 type com.axonivy.portal.businessuserexamples.humantask.ProcurementRequest #txt
Pt0 f8 timeout 0 #txt
Pt0 f8 exceptionHandler '>> Ignore Exception' #txt
Pt0 f8 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Notify Requester</name>
        <nameStyle>16,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Pt0 f8 712 162 112 44 -45 -8 #rect
Pt0 f8 @|EMailIcon #fIcon
Pt0 f4 expr out #txt
Pt0 f4 95 184 152 184 #arcP
Pt0 f9 expr out #txt
Pt0 f9 type workflow.humantask.ProcurementRequest #txt
Pt0 f9 var in1 #txt
Pt0 f9 264 184 312 288 #arcP
Pt0 f9 1 312 184 #addKink
Pt0 f9 1 0.06965079247016907 0 0 #arcLabel
Pt0 f10 expr data #txt
Pt0 f10 outCond ivp=="TaskA.ivp" #txt
Pt0 f10 328 304 360 304 #arcP
Pt0 f10 0 0.5752865296136699 0 0 #arcLabel
Pt0 f11 expr data #txt
Pt0 f11 outCond ivp=="TaskB.ivp" #txt
Pt0 f11 312 320 360 424 #arcP
Pt0 f11 1 312 424 #addKink
Pt0 f11 1 0.13308345915844264 0 0 #arcLabel
Pt0 f15 expr out #txt
Pt0 f15 824 184 857 184 #arcP
Pt0 f16 dialogId com.axonivy.portal.businessuserexamples.humantask.AcceptRequest #txt
Pt0 f16 startMethod start(com.axonivy.portal.businessuserexamples.humantask.ProcurementRequest) #txt
Pt0 f16 requestActionDecl '<com.axonivy.portal.businessuserexamples.humantask.ProcurementRequest procurementRequest> param;' #txt
Pt0 f16 requestMappingAction 'param.procurementRequest=in;
' #txt
Pt0 f16 responseActionDecl 'workflow.humantask.ProcurementRequest out;
' #txt
Pt0 f16 responseMappingAction 'out=in;
out.accepted=result.accepted;
out.activityLog=in.activityLog.add(result.logEntry);
' #txt
Pt0 f16 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Accept Request</name>
        <nameStyle>14,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Pt0 f16 632 514 112 44 -43 -8 #rect
Pt0 f16 @|UserDialogIcon #fIcon
Pt0 f17 expr out #txt
Pt0 f17 744 536 768 206 #arcP
Pt0 f17 1 768 536 #addKink
Pt0 f17 1 0.4192986443384443 0 0 #arcLabel
Pt0 f19 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Verified?</name>
        <nameStyle>9,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Pt0 f19 576 288 32 32 23 -4 #rect
Pt0 f19 @|AlternativeIcon #fIcon
Pt0 f20 expr data #txt
Pt0 f20 outCond ivp=="TaskB.ivp" #txt
Pt0 f20 544 304 576 304 #arcP
Pt0 f20 0 0.7298707237003984 0 0 #arcLabel
Pt0 f21 expr in #txt
Pt0 f21 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>No</name>
        <nameStyle>2,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Pt0 f21 592 288 712 184 #arcP
Pt0 f21 1 592 184 #addKink
Pt0 f21 0 0.7380952380952381 9 0 #arcLabel
Pt0 f12 expr out #txt
Pt0 f12 type workflow.humantask.ProcurementRequest #txt
Pt0 f12 var in1 #txt
Pt0 f12 472 304 512 304 #arcP
Pt0 f13 expr out #txt
Pt0 f13 type workflow.humantask.ProcurementRequest #txt
Pt0 f13 var in2 #txt
Pt0 f13 472 424 528 320 #arcP
Pt0 f13 1 528 424 #addKink
Pt0 f13 1 0.24183017286938835 0 0 #arcLabel
Pt0 f14 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>This example shows the use of a Parallel Task Switch Gateway.
A procurement request is made by an employee, verified by the team leader and by a manager 
and accepted by an executive.</name>
        <nameStyle>183,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Pt0 f14 64 18 528 60 -258 -24 #rect
Pt0 f14 @|IBIcon #fIcon
Pt0 f24 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>This is a gateway with a task switch.
You can use it to delegate parallel tasks to different users.
Don''t use the Split-Gateway to implement this, because there 
will be problems with the session handling.</name>
        <nameStyle>38,7
167,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Pt0 f24 96 506 352 76 -168 -32 #rect
Pt0 f24 @|IBIcon #fIcon
Pt0 f25 272 506 304 312 #arcP
Pt0 f26 actionTable 'out=in1;
' #txt
Pt0 f26 outLinks "TaskA.ivp" #txt
Pt0 f26 taskData 'TaskA.CATEGORY=Accept/Procurement
TaskA.EXPRI=2
TaskA.EXROL=Executive Manager
TaskA.EXTYPE=0
TaskA.NAM=<%\=ivy.cms.co("/TaskDescriptions/acceptRequest")%>\: <%\=in1.amount%> <%\=ivy.cms.co("/Dialogs/procurementRequest/piecesOf")%> ''<%\=in1.description%>'' <%\=ivy.cms.co("/Dialogs/procurementRequest/forTotal")%> <%\=in1.totalPrice%><%\=ivy.cms.co("/TaskDescriptions/currencySymbol")%>
TaskA.PRI=2
TaskA.ROL=Executive Manager
TaskA.SKIP_TASK_LIST=false
TaskA.TYPE=0' #txt
Pt0 f26 template "" #txt
Pt0 f26 577 521 30 30 0 16 #rect
Pt0 f26 @|TaskSwitchSimpleIcon #fIcon
Pt0 f27 expr in #txt
Pt0 f27 outCond 'in.dataOkManager && in.dataOkTeamLeader' #txt
Pt0 f27 type workflow.humantask.ProcurementRequest #txt
Pt0 f27 var in1 #txt
Pt0 f27 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Yes</name>
        <nameStyle>3,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Pt0 f27 592 320 592 521 #arcP
Pt0 f27 0 0.41911764705882354 14 0 #arcLabel
Pt0 f18 expr data #txt
Pt0 f18 outCond ivp=="TaskA.ivp" #txt
Pt0 f18 607 536 632 536 #arcP
>Proto Pt0 .type com.axonivy.portal.businessuserexamples.humantask.ProcurementRequest #txt
>Proto Pt0 .processKind NORMAL #txt
>Proto Pt0 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <swimlaneLabel>Procurement Request</swimlaneLabel>
        <swimlaneLabel>Employee</swimlaneLabel>
        <swimlaneLabel>Team Leader</swimlaneLabel>
        <swimlaneLabel>Manager</swimlaneLabel>
        <swimlaneLabel>Executive</swimlaneLabel>
    </language>
    <swimlaneOrientation>false</swimlaneOrientation>
    <swimlaneSize>472</swimlaneSize>
    <swimlaneSize>120</swimlaneSize>
    <swimlaneSize>120</swimlaneSize>
    <swimlaneSize>112</swimlaneSize>
    <swimlaneSize>120</swimlaneSize>
    <swimlaneColor gradient="false">-1</swimlaneColor>
    <swimlaneColor gradient="false">684510412</swimlaneColor>
    <swimlaneColor gradient="false">681166591</swimlaneColor>
    <swimlaneColor gradient="false">687865804</swimlaneColor>
    <swimlaneColor gradient="false">687852748</swimlaneColor>
    <swimlaneType>POOL</swimlaneType>
    <swimlaneType>LANE_IN_POOL</swimlaneType>
    <swimlaneType>LANE_IN_POOL</swimlaneType>
    <swimlaneType>LANE_IN_POOL</swimlaneType>
    <swimlaneType>LANE_IN_POOL</swimlaneType>
    <swimlaneSpaceBefore>128</swimlaneSpaceBefore>
    <swimlaneSpaceBefore>0</swimlaneSpaceBefore>
    <swimlaneSpaceBefore>0</swimlaneSpaceBefore>
    <swimlaneSpaceBefore>0</swimlaneSpaceBefore>
    <swimlaneSpaceBefore>0</swimlaneSpaceBefore>
</elementInfo>
' #txt
>Proto Pt0 0 0 32 24 18 0 #rect
>Proto Pt0 @|BIcon #fIcon
Pt0 f0 mainOut f4 tail #connect
Pt0 f4 head f3 mainIn #connect
Pt0 f3 mainOut f9 tail #connect
Pt0 f9 head f2 in #connect
Pt0 f2 out f10 tail #connect
Pt0 f10 head f5 mainIn #connect
Pt0 f2 out f11 tail #connect
Pt0 f11 head f6 mainIn #connect
Pt0 f8 mainOut f15 tail #connect
Pt0 f15 head f1 mainIn #connect
Pt0 f16 mainOut f17 tail #connect
Pt0 f17 head f8 mainIn #connect
Pt0 f7 out f20 tail #connect
Pt0 f20 head f19 in #connect
Pt0 f21 head f8 mainIn #connect
Pt0 f5 mainOut f12 tail #connect
Pt0 f12 head f7 in #connect
Pt0 f6 mainOut f13 tail #connect
Pt0 f13 head f7 in #connect
Pt0 f24 ao f25 tail #connect
Pt0 f25 head f2 @CG|ai #connect
Pt0 f19 out f27 tail #connect
Pt0 f27 head f26 in #connect
Pt0 f19 out f21 tail #connect
Pt0 f26 out f18 tail #connect
Pt0 f18 head f16 mainIn #connect
