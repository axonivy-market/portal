[Ivy]
170322ADBEC8D3D9 7.5.0 #module
>Proto >Proto Collection #zClass
Pt0 ProcurementRequestUserTask Big #zClass
Pt0 B #cInfo
Pt0 #process
Pt0 @TextInP .type .type #zField
Pt0 @TextInP .processKind .processKind #zField
Pt0 @AnnotationInP-0n ai ai #zField
Pt0 @MessageFlowInP-0n messageIn messageIn #zField
Pt0 @MessageFlowOutP-0n messageOut messageOut #zField
Pt0 @TextInP .xml .xml #zField
Pt0 @TextInP .responsibility .responsibility #zField
Pt0 @StartRequest f0 '' #zField
Pt0 @EndTask f1 '' #zField
Pt0 @UserDialog f3 '' #zField
Pt0 @PushWFArc f4 '' #zField
Pt0 @UserTask f5 '' #zField
Pt0 @TkArc f6 '' #zField
Pt0 @UserTask f7 '' #zField
Pt0 @EMail f9 '' #zField
Pt0 @PushWFArc f10 '' #zField
Pt0 @PushWFArc f2 '' #zField
Pt0 @Alternative f11 '' #zField
Pt0 @PushWFArc f12 '' #zField
Pt0 @TkArc f8 '' #zField
Pt0 @PushWFArc f13 '' #zField
Pt0 @InfoButton f14 '' #zField
Pt0 @AnnotationArc f15 '' #zField
Pt0 @InfoButton f16 '' #zField
>Proto Pt0 Pt0 ProcurementRequestUserTask #zField
Pt0 f0 outLink start.ivp #txt
Pt0 f0 inParamDecl '<> param;' #txt
Pt0 f0 requestEnabled true #txt
Pt0 f0 triggerEnabled false #txt
Pt0 f0 callSignature start() #txt
Pt0 f0 persist false #txt
Pt0 f0 startName '2.1: <%=ivy.cms.co("/ProcessDescriptions/procurementRequest")%> (<%=ivy.cms.co("/ProcessDescriptions/userTaskExample")%>)' #txt
Pt0 f0 startDescription <%=ivy.cms.co("/ProcessDescriptions/procurementRequestUserTaskDescription")%> #txt
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
Pt0 f0 81 177 30 30 -21 17 #rect
Pt0 f0 @|StartRequestIcon #fIcon
Pt0 f1 913 177 30 30 0 15 #rect
Pt0 f1 @|EndIcon #fIcon
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
Pt0 f3 168 170 112 44 -39 -8 #rect
Pt0 f3 @|UserDialogIcon #fIcon
Pt0 f4 expr out #txt
Pt0 f4 111 192 168 192 #arcP
Pt0 f5 dialogId com.axonivy.portal.businessuserexamples.humantask.VerifyRequest #txt
Pt0 f5 startMethod start(com.axonivy.portal.businessuserexamples.humantask.ProcurementRequest) #txt
Pt0 f5 requestActionDecl '<com.axonivy.portal.businessuserexamples.humantask.ProcurementRequest procurementRequest> param;' #txt
Pt0 f5 requestMappingAction 'param.procurementRequest=in;
' #txt
Pt0 f5 responseActionDecl 'workflow.humantask.ProcurementRequest out;
' #txt
Pt0 f5 responseMappingAction 'out=in;
out.activityLog=in.activityLog.add(result.logEntry);
out.dataOkManager=result.dataOk;
' #txt
Pt0 f5 taskData 'TaskA.CATEGORY=Review/Procurement
TaskA.EXPRI=2
TaskA.EXROL=Everybody
TaskA.EXTYPE=0
TaskA.NAM=<%\=ivy.cms.co("/TaskDescriptions/verifyRequest")%>\: <%\=in.amount%> <%\=ivy.cms.co("/Dialogs/procurementRequest/piecesOf")%> ''<%\=in.description%>'' <%\=ivy.cms.co("/Dialogs/procurementRequest/forTotal")%> <%\=in.totalPrice%><%\=ivy.cms.co("/TaskDescriptions/currencySymbol")%>
TaskA.PRI=2
TaskA.ROL=Manager
TaskA.SKIP_TASK_LIST=false
TaskA.TYPE=0' #txt
Pt0 f5 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Verify Request</name>
        <nameStyle>14,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Pt0 f5 328 298 112 44 -39 -8 #rect
Pt0 f5 @|UserTaskIcon #fIcon
Pt0 f6 expr out #txt
Pt0 f6 type workflow.humantask.ProcurementRequest #txt
Pt0 f6 var in1 #txt
Pt0 f6 224 214 328 320 #arcP
Pt0 f6 1 224 320 #addKink
Pt0 f6 0 0.9750159348993512 0 0 #arcLabel
Pt0 f7 dialogId com.axonivy.portal.businessuserexamples.humantask.AcceptRequest #txt
Pt0 f7 startMethod start(com.axonivy.portal.businessuserexamples.humantask.ProcurementRequest) #txt
Pt0 f7 requestActionDecl '<com.axonivy.portal.businessuserexamples.humantask.ProcurementRequest procurementRequest> param;' #txt
Pt0 f7 requestMappingAction 'param.procurementRequest=in;
' #txt
Pt0 f7 responseActionDecl 'workflow.humantask.ProcurementRequest out;
' #txt
Pt0 f7 responseMappingAction 'out=in;
out.accepted=result.accepted;
out.activityLog=in.activityLog.add(result.logEntry);
' #txt
Pt0 f7 taskData 'TaskA.CATEGORY=Accept/Procurement
TaskA.EXPRI=2
TaskA.EXROL=Everybody
TaskA.EXTYPE=0
TaskA.NAM=<%\=ivy.cms.co("/TaskDescriptions/acceptRequest")%>\: <%\=in.amount%> <%\=ivy.cms.co("/Dialogs/procurementRequest/piecesOf")%> ''<%\=in.description%>'' <%\=ivy.cms.co("/Dialogs/procurementRequest/forTotal")%> <%\=in.totalPrice%><%\=ivy.cms.co("/TaskDescriptions/currencySymbol")%>
TaskA.PRI=2
TaskA.ROL=Executive Manager
TaskA.SKIP_TASK_LIST=false
TaskA.TYPE=0' #txt
Pt0 f7 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Accept Request</name>
        <nameStyle>14,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Pt0 f7 584 418 112 44 -43 -8 #rect
Pt0 f7 @|UserTaskIcon #fIcon
Pt0 f9 beanConfig '"{/emailSubject ""<%=ivy.cms.co(\\""/Emails/yourRequestHasBeen\\"")%> <%=(in.accepted ? ivy.cms.co(\\""/Emails/accepted\\"") : ivy.cms.co(\\""/Emails/declined\\""))%>: <%=in.amount%> <%=ivy.cms.co(\\""/Dialogs/procurementRequest/piecesOf\\"")%> \\''<%=in.description%>\\'' <%=ivy.cms.co(\\""/Dialogs/procurementRequest/forTotal\\"")%> <%=in.totalPrice%> <%=ivy.cms.co(\\""/Dialogs/procurementRequest/currencySymbol\\"")%>""/emailFrom ""<%=ivy.cms.co(\\""/Emails/senderMail\\"")%>""/emailReplyTo """"/emailTo ""<%=in.requester.email%>""/emailCC """"/emailBCC """"/exceptionMissingEmailAttachments ""false""/emailMessage "" <html> \\n\\t<style type=\\""text/css\\"">\\n     \\t\\t<%=ivy.cms.co(\\""/Styles/Classic\\"")%>\\n\\t</style>\\n\\t\\n\\t<%=ivy.cms.co(\\""/Images/Logo\\"")%>\\n\\n\\t<%=ivy.cms.co(\\""/Emails/procurementRequestNotification\\"")%>\\n</html>""/emailAttachments * }"' #txt
Pt0 f9 type com.axonivy.portal.businessuserexamples.humantask.ProcurementRequest #txt
Pt0 f9 timeout 0 #txt
Pt0 f9 exceptionHandler '>> Ignore Exception' #txt
Pt0 f9 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Notify Requester</name>
        <nameStyle>16,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Pt0 f9 744 168 112 48 -45 -8 #rect
Pt0 f9 @|EMailIcon #fIcon
Pt0 f10 expr data #txt
Pt0 f10 outCond ivp=="TaskA.ivp" #txt
Pt0 f10 696 440 800 216 #arcP
Pt0 f10 1 800 440 #addKink
Pt0 f10 1 0.2769262184174318 0 0 #arcLabel
Pt0 f2 expr out #txt
Pt0 f2 856 192 913 192 #arcP
Pt0 f11 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Verified?</name>
        <nameStyle>9,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Pt0 f11 496 304 32 32 27 -7 #rect
Pt0 f11 @|AlternativeIcon #fIcon
Pt0 f12 expr data #txt
Pt0 f12 outCond ivp=="TaskA.ivp" #txt
Pt0 f12 440 320 496 320 #arcP
Pt0 f8 expr in #txt
Pt0 f8 outCond in.dataOkManager #txt
Pt0 f8 type workflow.humantask.ProcurementRequest #txt
Pt0 f8 var in1 #txt
Pt0 f8 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Yes</name>
        <nameStyle>3,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Pt0 f8 512 336 584 440 #arcP
Pt0 f8 1 512 440 #addKink
Pt0 f8 0 0.8465909090909091 15 0 #arcLabel
Pt0 f13 expr in #txt
Pt0 f13 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>No</name>
        <nameStyle>2,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Pt0 f13 512 304 744 192 #arcP
Pt0 f13 1 512 192 #addKink
Pt0 f13 1 0.0603448275862069 0 13 #arcLabel
Pt0 f14 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Combines a Task Switch Event
and a User Dialog Step.</name>
        <nameStyle>52,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Pt0 f14 224 370 176 44 -85 -16 #rect
Pt0 f14 @|IBIcon #fIcon
Pt0 f15 312 370 335 342 #arcP
Pt0 f16 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>This example shows the usage of a User Task Activity.
A procurement request is made by an employee, verified by the manager and accepted by an executive.
The User Task step is used instead of a Task Switch and a User Dialog step.</name>
        <nameStyle>229,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Pt0 f16 64 18 576 60 -280 -24 #rect
Pt0 f16 @|IBIcon #fIcon
>Proto Pt0 .type com.axonivy.portal.businessuserexamples.humantask.ProcurementRequest #txt
>Proto Pt0 .processKind NORMAL #txt
>Proto Pt0 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <swimlaneLabel>Procurement Request</swimlaneLabel>
        <swimlaneLabel>Employee</swimlaneLabel>
        <swimlaneLabel>Manager</swimlaneLabel>
        <swimlaneLabel>Executive</swimlaneLabel>
    </language>
    <swimlaneOrientation>false</swimlaneOrientation>
    <swimlaneSize>360</swimlaneSize>
    <swimlaneSize>128</swimlaneSize>
    <swimlaneSize>120</swimlaneSize>
    <swimlaneSize>112</swimlaneSize>
    <swimlaneColor gradient="false">-1</swimlaneColor>
    <swimlaneColor gradient="false">684510412</swimlaneColor>
    <swimlaneColor gradient="false">687865804</swimlaneColor>
    <swimlaneColor gradient="false">687852748</swimlaneColor>
    <swimlaneType>POOL</swimlaneType>
    <swimlaneType>LANE_IN_POOL</swimlaneType>
    <swimlaneType>LANE_IN_POOL</swimlaneType>
    <swimlaneType>LANE_IN_POOL</swimlaneType>
    <swimlaneSpaceBefore>128</swimlaneSpaceBefore>
    <swimlaneSpaceBefore>0</swimlaneSpaceBefore>
    <swimlaneSpaceBefore>0</swimlaneSpaceBefore>
    <swimlaneSpaceBefore>0</swimlaneSpaceBefore>
</elementInfo>
' #txt
>Proto Pt0 0 0 32 24 18 0 #rect
>Proto Pt0 @|BIcon #fIcon
Pt0 f0 mainOut f4 tail #connect
Pt0 f4 head f3 mainIn #connect
Pt0 f3 mainOut f6 tail #connect
Pt0 f6 head f5 in #connect
Pt0 f7 out f10 tail #connect
Pt0 f10 head f9 mainIn #connect
Pt0 f9 mainOut f2 tail #connect
Pt0 f2 head f1 mainIn #connect
Pt0 f5 out f12 tail #connect
Pt0 f12 head f11 in #connect
Pt0 f11 out f8 tail #connect
Pt0 f8 head f7 in #connect
Pt0 f11 out f13 tail #connect
Pt0 f13 head f9 mainIn #connect
Pt0 f14 ao f15 tail #connect
Pt0 f15 head f5 @CG|ai #connect
