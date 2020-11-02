[Ivy]
170321BD7F5539D6 9.2.0 #module
>Proto >Proto Collection #zClass
Lt0 LeaveRequest Big #zClass
Lt0 B #cInfo
Lt0 #process
Lt0 @TextInP .type .type #zField
Lt0 @TextInP .processKind .processKind #zField
Lt0 @TextInP .xml .xml #zField
Lt0 @TextInP .responsibility .responsibility #zField
Lt0 @StartRequest f0 '' #zField
Lt0 @UserTask f18 '' #zField
Lt0 @TkArc f20 '' #zField
Lt0 @UserTask f4 '' #zField
Lt0 @UserTask f16 '' #zField
Lt0 @TkArc f2 '' #zField
Lt0 @TkArc f3 '' #zField
Lt0 @InfoButton f5 '' #zField
Lt0 @AnnotationArc f6 '' #zField
Lt0 @InfoButton f7 '' #zField
Lt0 @AnnotationArc f8 '' #zField
Lt0 @InfoButton f9 '' #zField
Lt0 @AnnotationArc f10 '' #zField
Lt0 @InfoButton f11 '' #zField
Lt0 @AnnotationArc f12 '' #zField
Lt0 @GridStep f28 '' #zField
Lt0 @PushWFArc f1 '' #zField
Lt0 @EndTask f13 '' #zField
Lt0 @PushWFArc f14 '' #zField
>Proto Lt0 Lt0 LeaveRequest #zField
Lt0 f0 outLink start.ivp #txt
Lt0 f0 inParamDecl '<> param;' #txt
Lt0 f0 requestEnabled true #txt
Lt0 f0 triggerEnabled false #txt
Lt0 f0 callSignature start() #txt
Lt0 f0 startName <%=ivy.cms.co("/Processes/LeaveRequest/leaveRequest")%> #txt
Lt0 f0 startDescription 'This process allow employee to submit their leave request, then the request will be sent to selected approver.
After approver finish his task, the approval result will be sent back to requester' #txt
Lt0 f0 taskData TaskTriggered.NAM=<%\=ivy.cms.co("/Processes/LeaveRequest/createNewLeaveRequest")%> #txt
Lt0 f0 caseData 'businessCase.attach=true
case.name=<%\=ivy.cms.co("/Processes/LeaveRequest/leaveRequest")%>
customFields.STRING.embedInFrame="false"' #txt
Lt0 f0 wfuser 1 #txt
Lt0 f0 roleExceptionId '>> Ignore Exception' #txt
Lt0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start.ivp</name>
    </language>
</elementInfo>
' #txt
Lt0 f0 @C|.responsibility Everybody #txt
Lt0 f0 73 233 30 30 -21 17 #rect
Lt0 f0 @|StartRequestIcon #fIcon
Lt0 f18 dialogId com.axonivy.portal.userexamples.leaverequest.LeaveRequestCreation #txt
Lt0 f18 startMethod start() #txt
Lt0 f18 requestActionDecl '<> param;' #txt
Lt0 f18 responseMappingAction 'out=in;
out.approver=result.approverName;
out.requester=result.requesterName;
' #txt
Lt0 f18 taskData 'TaskA.NAM=<%\=ivy.cms.co("/Processes/LeaveRequest/createLeaveRequest", [ivy.session.getSessionUser().getDisplayName()])%>
TaskA.ROL=SELF
TaskA.SKIP_TASK_LIST=true
TaskA.TYPE=0' #txt
Lt0 f18 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>LeaveRequestCreation</name>
    </language>
</elementInfo>
' #txt
Lt0 f18 208 226 144 44 -63 -8 #rect
Lt0 f18 @|UserTaskIcon #fIcon
Lt0 f20 103 248 208 248 #arcP
Lt0 f4 dialogId com.axonivy.portal.userexamples.leaverequest.LeaveRequestApproval #txt
Lt0 f4 startMethod start() #txt
Lt0 f4 requestActionDecl '<> param;' #txt
Lt0 f4 responseMappingAction 'out=in;
out.isApproved=result.isApproved;
' #txt
Lt0 f4 taskData 'TaskA.NAM=<%\=ivy.cms.co("/Processes/LeaveRequest/approvalTaskName", [ivy.session.getSecurityContext().findUser(in.requester).getDisplayName()])%>
TaskA.ROL=in.approver
TaskA.TYPE=3' #txt
Lt0 f4 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>LeaveRequestApproval</name>
    </language>
</elementInfo>
' #txt
Lt0 f4 424 226 144 44 -63 -8 #rect
Lt0 f4 @|UserTaskIcon #fIcon
Lt0 f16 dialogId com.axonivy.portal.userexamples.leaverequest.LeaveRequestSummary #txt
Lt0 f16 startMethod start() #txt
Lt0 f16 requestActionDecl '<> param;' #txt
Lt0 f16 responseMappingAction 'out=in;
' #txt
Lt0 f16 taskData 'TaskA.NAM=<%\=in.isApproved? ivy.cms.co("/Processes/LeaveRequest/approvalTask") \: ivy.cms.co("/Processes/LeaveRequest/rejectedTask")%>
TaskA.ROL=in.requester
TaskA.TYPE=3' #txt
Lt0 f16 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>LeaveRequestSummary</name>
    </language>
</elementInfo>
' #txt
Lt0 f16 648 226 144 44 -66 -8 #rect
Lt0 f16 @|UserTaskIcon #fIcon
Lt0 f2 568 248 648 248 #arcP
Lt0 f2 0 0.6124600781942752 0 0 #arcLabel
Lt0 f3 352 248 424 248 #arcP
Lt0 f3 0 0.8163762171638494 0 0 #arcLabel
Lt0 f5 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>User goes directly to this task after start the&#13;
process because we enable skipTaskList&#13;
&#13;
In this task, user able to submit his/her  leave request, and choose the approval for the request</name>
    </language>
</elementInfo>
' #txt
Lt0 f5 96 26 528 76 -257 -32 #rect
Lt0 f5 @|IBIcon #fIcon
Lt0 f6 360 102 280 226 #arcP
Lt0 f7 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Approval task for leave request, which is assigned to the selected approver in first task</name>
    </language>
</elementInfo>
' #txt
Lt0 f7 344 393 480 30 -234 -8 #rect
Lt0 f7 @|IBIcon #fIcon
Lt0 f8 584 393 496 270 #arcP
Lt0 f9 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Base on descision in approval task, the task name will be &#13;
display as approved or rejected. It''s assigned back to &#13;
the person who submit the leave request</name>
    </language>
</elementInfo>
' #txt
Lt0 f9 816 34 336 60 -158 -24 #rect
Lt0 f9 @|IBIcon #fIcon
Lt0 f10 816 64 720 226 #arcP
Lt0 f11 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>This process allow employee to submit their leave request, then the request will be sent to selected approver.&#13;
After approver finish his task, the approval result will be sent back to requester</name>
    </language>
</elementInfo>
' #txt
Lt0 f11 0 530 608 44 -298 -16 #rect
Lt0 f11 @|IBIcon #fIcon
Lt0 f12 304 530 83 262 #arcP
Lt0 f28 actionTable 'out=in;
' #txt
Lt0 f28 actionCode 'import ch.ivy.addon.portalkit.constant.CustomFields;
import java.util.Map;
import ch.ivy.addon.portalkit.enums.PortalLibrary;
import java.util.Arrays;
import ch.ivy.addon.portalkit.service.IvyAdapterService;

Map x = IvyAdapterService.startSubProcess("handleUserExamplesEndPage()", null, Arrays.asList(PortalLibrary.AXON_EXPRESS.getValue()));
String callbackUrl = x.get("callbackUrl") as String;
ivy.task.customFields().stringField(CustomFields.EXPRESS_END_PAGE_URL.toString()).set(callbackUrl);

' #txt
Lt0 f28 security system #txt
Lt0 f28 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Set end page</name>
    </language>
</elementInfo>
' #txt
Lt0 f28 864 226 112 44 -34 -8 #rect
Lt0 f28 @|StepIcon #fIcon
Lt0 f1 792 248 864 248 #arcP
Lt0 f13 1041 233 30 30 0 15 #rect
Lt0 f13 @|EndIcon #fIcon
Lt0 f14 976 248 1041 248 #arcP
>Proto Lt0 .type com.axonivy.portal.userexamples.leaverequest.LeaveRequestProcessData #txt
>Proto Lt0 .processKind NORMAL #txt
>Proto Lt0 0 0 32 24 18 0 #rect
>Proto Lt0 @|BIcon #fIcon
Lt0 f0 mainOut f20 tail #connect
Lt0 f20 head f18 in #connect
Lt0 f4 out f2 tail #connect
Lt0 f2 head f16 in #connect
Lt0 f18 out f3 tail #connect
Lt0 f3 head f4 in #connect
Lt0 f5 ao f6 tail #connect
Lt0 f6 head f18 @CG|ai #connect
Lt0 f7 ao f8 tail #connect
Lt0 f8 head f4 @CG|ai #connect
Lt0 f9 ao f10 tail #connect
Lt0 f10 head f16 @CG|ai #connect
Lt0 f11 ao f12 tail #connect
Lt0 f12 head f0 @CG|ai #connect
Lt0 f16 out f1 tail #connect
Lt0 f1 head f28 mainIn #connect
Lt0 f28 mainOut f14 tail #connect
Lt0 f14 head f13 mainIn #connect
