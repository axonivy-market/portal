[Ivy]
170321BD7F5539D6 7.5.0 #module
>Proto >Proto Collection #zClass
Lt0 LeaveRequest Big #zClass
Lt0 B #cInfo
Lt0 #process
Lt0 @TextInP .type .type #zField
Lt0 @TextInP .processKind .processKind #zField
Lt0 @TextInP .xml .xml #zField
Lt0 @TextInP .responsibility .responsibility #zField
Lt0 @StartRequest f0 '' #zField
Lt0 @EndTask f1 '' #zField
Lt0 @TaskSwitchSimple f3 '' #zField
Lt0 @UserDialog f2 '' #zField
Lt0 @PushWFArc f5 '' #zField
Lt0 @TaskSwitchSimple f7 '' #zField
Lt0 @UserDialog f9 '' #zField
Lt0 @TaskSwitchSimple f11 '' #zField
Lt0 @UserDialog f13 '' #zField
Lt0 @PushWFArc f14 '' #zField
Lt0 @PushWFArc f6 '' #zField
Lt0 @GridStep f15 '' #zField
Lt0 @PushWFArc f16 '' #zField
Lt0 @TkArc f12 '' #zField
Lt0 @GridStep f17 '' #zField
Lt0 @PushWFArc f19 '' #zField
Lt0 @TkArc f8 '' #zField
Lt0 @PushWFArc f10 '' #zField
Lt0 @GridStep f18 '' #zField
Lt0 @PushWFArc f20 '' #zField
Lt0 @TkArc f4 '' #zField
>Proto Lt0 Lt0 LeaveRequest #zField
Lt0 f0 outLink start.ivp #txt
Lt0 f0 inParamDecl '<> param;' #txt
Lt0 f0 requestEnabled true #txt
Lt0 f0 triggerEnabled false #txt
Lt0 f0 callSignature start() #txt
Lt0 f0 startName <%=ivy.cms.co("/Processes/LeaveRequest/leaveRequest")%> #txt
Lt0 f0 startDescription <%=ivy.cms.co("/Processes/LeaveRequest/leaveRequest")%> #txt
Lt0 f0 taskData TaskTriggered.NAM=<%\=ivy.cms.co("/Processes/LeaveRequest/createNewLeaveRequest")%> #txt
Lt0 f0 caseData 'businessCase.attach=true
case.name=<%\=ivy.cms.co("/Processes/LeaveRequest/leaveRequest")%>
customFields.STRING.embedInFrame="false"' #txt
Lt0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start.ivp</name>
    </language>
</elementInfo>
' #txt
Lt0 f0 @C|.responsibility Everybody #txt
Lt0 f0 81 49 30 30 -21 17 #rect
Lt0 f0 @|StartRequestIcon #fIcon
Lt0 f1 1529 49 30 30 0 15 #rect
Lt0 f1 @|EndIcon #fIcon
Lt0 f3 actionTable 'out=in1;
' #txt
Lt0 f3 taskData 'TaskA.NAM=<%\=in1.taskName%>
TaskA.ROL=SELF
TaskA.SKIP_TASK_LIST=true
TaskA.TYPE=0' #txt
Lt0 f3 337 49 30 30 0 16 #rect
Lt0 f3 @|TaskSwitchSimpleIcon #fIcon
Lt0 f2 dialogId com.axonivy.portal.businessuserexamples.leaverequest.LeaveRequestCreation #txt
Lt0 f2 startMethod start() #txt
Lt0 f2 requestActionDecl '<> param;' #txt
Lt0 f2 responseMappingAction 'out=in;
out.approver=result.approverName;
out.requester=result.requesterName;
' #txt
Lt0 f2 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>LeaveRequestCreation</name>
    </language>
</elementInfo>
' #txt
Lt0 f2 424 42 144 44 -63 -8 #rect
Lt0 f2 @|UserDialogIcon #fIcon
Lt0 f5 367 64 424 64 #arcP
Lt0 f7 actionTable 'out=in1;
' #txt
Lt0 f7 taskData 'TaskA.NAM=<%\=in1.taskName%>
TaskA.ROL=in1.approver
TaskA.TYPE=3' #txt
Lt0 f7 809 49 30 30 0 16 #rect
Lt0 f7 @|TaskSwitchSimpleIcon #fIcon
Lt0 f9 dialogId com.axonivy.portal.businessuserexamples.leaverequest.LeaveRequestApproval #txt
Lt0 f9 startMethod start() #txt
Lt0 f9 requestActionDecl '<> param;' #txt
Lt0 f9 responseMappingAction 'out=in;
out.isApproved=result.isApproved;
' #txt
Lt0 f9 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Leave Request Approval</name>
    </language>
</elementInfo>
' #txt
Lt0 f9 896 42 144 44 -66 -8 #rect
Lt0 f9 @|UserDialogIcon #fIcon
Lt0 f11 actionTable 'out=in1;
' #txt
Lt0 f11 taskData 'TaskA.DESC=Approval decision for your leave request.
TaskA.NAM=<%\=in1.taskName%>
TaskA.ROL=in1.requester
TaskA.TYPE=3' #txt
Lt0 f11 1273 49 30 30 0 16 #rect
Lt0 f11 @|TaskSwitchSimpleIcon #fIcon
Lt0 f13 dialogId com.axonivy.portal.businessuserexamples.leaverequest.LeaveRequestSummary #txt
Lt0 f13 startMethod start() #txt
Lt0 f13 requestActionDecl '<> param;' #txt
Lt0 f13 responseMappingAction 'out=in;
' #txt
Lt0 f13 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Leave Request Summary</name>
    </language>
</elementInfo>
' #txt
Lt0 f13 1344 42 144 44 -69 -8 #rect
Lt0 f13 @|UserDialogIcon #fIcon
Lt0 f14 1303 64 1344 64 #arcP
Lt0 f6 1488 64 1529 64 #arcP
Lt0 f15 actionTable 'out=in;
' #txt
Lt0 f15 actionCode 'if(in.isApproved) {
	in.taskName = ivy.cms.co("/Processes/LeaveRequest/approvalTask");
}
else {
	in.taskName = ivy.cms.co("/Processes/LeaveRequest/rejectedTask");
}' #txt
Lt0 f15 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Define name for &#13;
summary task</name>
    </language>
</elementInfo>
' #txt
Lt0 f15 1096 42 128 44 -42 -16 #rect
Lt0 f15 @|StepIcon #fIcon
Lt0 f16 1040 64 1096 64 #arcP
Lt0 f12 1224 64 1273 64 #arcP
Lt0 f17 actionTable 'out=in;
' #txt
Lt0 f17 actionCode 'in.taskName = ivy.cms.co("/Processes/LeaveRequest/approvalTaskName", [ivy.session.getSessionUser().getDisplayName()]);' #txt
Lt0 f17 security system #txt
Lt0 f17 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Define name for approval task</name>
    </language>
</elementInfo>
' #txt
Lt0 f17 608 42 176 44 -82 -8 #rect
Lt0 f17 @|StepIcon #fIcon
Lt0 f19 568 64 608 64 #arcP
Lt0 f8 784 64 809 64 #arcP
Lt0 f10 839 64 896 64 #arcP
Lt0 f18 actionTable 'out=in;
' #txt
Lt0 f18 actionCode 'in.taskName = ivy.cms.co("/Processes/LeaveRequest/createLeaveRequest", [ivy.session.getSessionUser().getDisplayName()]);' #txt
Lt0 f18 security system #txt
Lt0 f18 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Define name for &#13;
creation task</name>
    </language>
</elementInfo>
' #txt
Lt0 f18 160 42 128 44 -42 -16 #rect
Lt0 f18 @|StepIcon #fIcon
Lt0 f20 111 64 160 64 #arcP
Lt0 f4 288 64 337 64 #arcP
>Proto Lt0 .type com.axonivy.portal.businessuserexamples.leaverequest.LeaveRequestProcessData #txt
>Proto Lt0 .processKind NORMAL #txt
>Proto Lt0 0 0 32 24 18 0 #rect
>Proto Lt0 @|BIcon #fIcon
Lt0 f3 out f5 tail #connect
Lt0 f5 head f2 mainIn #connect
Lt0 f11 out f14 tail #connect
Lt0 f14 head f13 mainIn #connect
Lt0 f13 mainOut f6 tail #connect
Lt0 f6 head f1 mainIn #connect
Lt0 f9 mainOut f16 tail #connect
Lt0 f16 head f15 mainIn #connect
Lt0 f15 mainOut f12 tail #connect
Lt0 f12 head f11 in #connect
Lt0 f2 mainOut f19 tail #connect
Lt0 f19 head f17 mainIn #connect
Lt0 f17 mainOut f8 tail #connect
Lt0 f8 head f7 in #connect
Lt0 f7 out f10 tail #connect
Lt0 f10 head f9 mainIn #connect
Lt0 f0 mainOut f20 tail #connect
Lt0 f20 head f18 mainIn #connect
Lt0 f18 mainOut f4 tail #connect
Lt0 f4 head f3 in #connect
