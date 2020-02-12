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
Lt0 @TkArc f4 '' #zField
Lt0 @UserDialog f2 '' #zField
Lt0 @PushWFArc f5 '' #zField
Lt0 @TaskSwitchSimple f7 '' #zField
Lt0 @TkArc f8 '' #zField
Lt0 @UserDialog f9 '' #zField
Lt0 @PushWFArc f10 '' #zField
Lt0 @PushWFArc f6 '' #zField
>Proto Lt0 Lt0 LeaveRequest #zField
Lt0 f0 outLink start.ivp #txt
Lt0 f0 inParamDecl '<> param;' #txt
Lt0 f0 requestEnabled true #txt
Lt0 f0 triggerEnabled false #txt
Lt0 f0 callSignature start() #txt
Lt0 f0 startName <%=ivy.cms.co("/Processes/LeaveRequest")%> #txt
Lt0 f0 startDescription <%=ivy.cms.co("/Processes/LeaveRequest")%> #txt
Lt0 f0 taskData 'TaskTriggered.NAM=Start create new leave request' #txt
Lt0 f0 caseData 'businessCase.attach=true
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
Lt0 f1 817 49 30 30 0 15 #rect
Lt0 f1 @|EndIcon #fIcon
Lt0 f3 actionTable 'out=in1;
' #txt
Lt0 f3 taskData 'TaskA.NAM=Leave Request
TaskA.SKIP_TASK_LIST=true' #txt
Lt0 f3 177 49 30 30 0 16 #rect
Lt0 f3 @|TaskSwitchSimpleIcon #fIcon
Lt0 f4 111 64 177 64 #arcP
Lt0 f2 dialogId com.axonivy.portal.businessuserexamples.leaverequest.LeaveRequestCreation #txt
Lt0 f2 startMethod start() #txt
Lt0 f2 requestActionDecl '<> param;' #txt
Lt0 f2 responseMappingAction 'out=in;
' #txt
Lt0 f2 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>LeaveRequestCreation</name>
    </language>
</elementInfo>
' #txt
Lt0 f2 272 42 144 44 -63 -8 #rect
Lt0 f2 @|UserDialogIcon #fIcon
Lt0 f5 207 64 272 64 #arcP
Lt0 f7 actionTable 'out=in1;
' #txt
Lt0 f7 taskData 'TaskA.NAM=Leave Request Approval' #txt
Lt0 f7 473 49 30 30 0 16 #rect
Lt0 f7 @|TaskSwitchSimpleIcon #fIcon
Lt0 f8 416 64 473 64 #arcP
Lt0 f9 dialogId com.axonivy.portal.businessuserexamples.leaverequest.LeaveRequestApproval #txt
Lt0 f9 startMethod start() #txt
Lt0 f9 requestActionDecl '<> param;' #txt
Lt0 f9 responseMappingAction 'out=in;
' #txt
Lt0 f9 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>LeaveRequestApproval</name>
    </language>
</elementInfo>
' #txt
Lt0 f9 584 42 144 44 -63 -8 #rect
Lt0 f9 @|UserDialogIcon #fIcon
Lt0 f10 503 64 584 64 #arcP
Lt0 f6 728 64 817 64 #arcP
>Proto Lt0 .type com.axonivy.portal.businessuserexamples.LeaveRequestData #txt
>Proto Lt0 .processKind NORMAL #txt
>Proto Lt0 0 0 32 24 18 0 #rect
>Proto Lt0 @|BIcon #fIcon
Lt0 f0 mainOut f4 tail #connect
Lt0 f4 head f3 in #connect
Lt0 f3 out f5 tail #connect
Lt0 f5 head f2 mainIn #connect
Lt0 f2 mainOut f8 tail #connect
Lt0 f8 head f7 in #connect
Lt0 f7 out f10 tail #connect
Lt0 f10 head f9 mainIn #connect
Lt0 f9 mainOut f6 tail #connect
Lt0 f6 head f1 mainIn #connect
