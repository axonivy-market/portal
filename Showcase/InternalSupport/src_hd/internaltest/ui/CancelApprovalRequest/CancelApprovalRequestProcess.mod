[Ivy]
15D4E5494B0DFC50 7.5.0 #module
>Proto >Proto Collection #zClass
Cs0 CancelApprovalRequestProcess Big #zClass
Cs0 RD #cInfo
Cs0 #process
Cs0 @TextInP .type .type #zField
Cs0 @TextInP .processKind .processKind #zField
Cs0 @AnnotationInP-0n ai ai #zField
Cs0 @MessageFlowInP-0n messageIn messageIn #zField
Cs0 @MessageFlowOutP-0n messageOut messageOut #zField
Cs0 @TextInP .xml .xml #zField
Cs0 @TextInP .responsibility .responsibility #zField
Cs0 @UdInit f0 '' #zField
Cs0 @UdProcessEnd f1 '' #zField
Cs0 @PushWFArc f2 '' #zField
Cs0 @UdExitEnd f4 '' #zField
Cs0 @GridStep f6 '' #zField
Cs0 @PushWFArc f5 '' #zField
Cs0 @UdMethod f8 '' #zField
Cs0 @PushWFArc f7 '' #zField
>Proto Cs0 Cs0 CancelApprovalRequestProcess #zField
Cs0 f0 guid 15D4E5494D1802E5 #txt
Cs0 f0 method start() #txt
Cs0 f0 inParameterDecl '<> param;' #txt
Cs0 f0 outParameterDecl '<> result;' #txt
Cs0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start()</name>
    </language>
</elementInfo>
' #txt
Cs0 f0 83 51 26 26 -16 15 #rect
Cs0 f0 @|UdInitIcon #fIcon
Cs0 f1 211 51 26 26 0 12 #rect
Cs0 f1 @|UdProcessEndIcon #fIcon
Cs0 f2 expr out #txt
Cs0 f2 109 64 211 64 #arcP
Cs0 f4 419 147 26 26 0 12 #rect
Cs0 f4 @|UdExitEndIcon #fIcon
Cs0 f6 actionTable 'out=in;
' #txt
Cs0 f6 actionCode 'import ch.ivyteam.ivy.workflow.INote;
import ch.ivyteam.ivy.workflow.internal.cases.Case;

Case wfCase = ivy.wf.findCase(in.caseId) as Case;
String cancelNote = "Cancel Reason: " +  out.cancelReason;
INote note =wfCase.createNote(ivy.session, cancelNote);


' #txt
Cs0 f6 security system #txt
Cs0 f6 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Write cancel reason 
to case note</name>
        <nameStyle>33,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f6 184 138 144 44 -52 -16 #rect
Cs0 f6 @|StepIcon #fIcon
Cs0 f5 expr out #txt
Cs0 f5 328 160 419 160 #arcP
Cs0 f8 guid 15D4E6C813AA1993 #txt
Cs0 f8 method cancelApprovalRequest(java.lang.Long) #txt
Cs0 f8 inParameterDecl '<Long caseId> param;' #txt
Cs0 f8 inParameterMapAction 'out.caseId=param.caseId;
' #txt
Cs0 f8 outParameterDecl '<> result;' #txt
Cs0 f8 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>cancelApprovalRequest(Long,String)</name>
        <nameStyle>34,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f8 83 147 26 26 -100 15 #rect
Cs0 f8 @|UdMethodIcon #fIcon
Cs0 f7 expr out #txt
Cs0 f7 109 160 184 160 #arcP
>Proto Cs0 .type internaltest.ui.CancelApprovalRequest.CancelApprovalRequestData #txt
>Proto Cs0 .processKind HTML_DIALOG #txt
>Proto Cs0 -8 -8 16 16 16 26 #rect
>Proto Cs0 '' #fIcon
Cs0 f0 mainOut f2 tail #connect
Cs0 f2 head f1 mainIn #connect
Cs0 f6 mainOut f5 tail #connect
Cs0 f5 head f4 mainIn #connect
Cs0 f8 mainOut f7 tail #connect
Cs0 f7 head f6 mainIn #connect
