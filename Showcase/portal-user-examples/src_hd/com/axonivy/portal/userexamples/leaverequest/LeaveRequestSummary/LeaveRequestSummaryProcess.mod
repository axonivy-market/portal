[Ivy]
17038C52ECDC08C6 9.2.0 #module
>Proto >Proto Collection #zClass
Ls0 LeaveRequestSummaryProcess Big #zClass
Ls0 RD #cInfo
Ls0 #process
Ls0 @TextInP .type .type #zField
Ls0 @TextInP .processKind .processKind #zField
Ls0 @TextInP .xml .xml #zField
Ls0 @TextInP .responsibility .responsibility #zField
Ls0 @UdInit f0 '' #zField
Ls0 @UdProcessEnd f1 '' #zField
Ls0 @UdEvent f3 '' #zField
Ls0 @UdExitEnd f4 '' #zField
Ls0 @PushWFArc f5 '' #zField
Ls0 @GridStep f6 '' #zField
Ls0 @PushWFArc f7 '' #zField
Ls0 @PushWFArc f2 '' #zField
>Proto Ls0 Ls0 LeaveRequestSummaryProcess #zField
Ls0 f0 guid 17038C52EEE78020 #txt
Ls0 f0 method start() #txt
Ls0 f0 inParameterDecl '<> param;' #txt
Ls0 f0 outParameterDecl '<> result;' #txt
Ls0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start()</name>
    </language>
</elementInfo>
' #txt
Ls0 f0 83 51 26 26 -16 15 #rect
Ls0 f0 @|UdInitIcon #fIcon
Ls0 f1 339 51 26 26 0 12 #rect
Ls0 f1 @|UdProcessEndIcon #fIcon
Ls0 f3 guid 17038C52F10CC4E1 #txt
Ls0 f3 actionTable 'out=in;
' #txt
Ls0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>close</name>
    </language>
</elementInfo>
' #txt
Ls0 f3 83 147 26 26 -15 15 #rect
Ls0 f3 @|UdEventIcon #fIcon
Ls0 f4 211 147 26 26 0 12 #rect
Ls0 f4 @|UdExitEndIcon #fIcon
Ls0 f5 109 160 211 160 #arcP
Ls0 f6 actionTable 'out=in;
' #txt
Ls0 f6 actionCode 'import ch.ivy.addon.portalkit.dto.UserDTO;
import com.axonivy.portal.userexamples.leaverequest.LeaveRequest;
in.leaveRequestData = ivy.repo.get(LeaveRequest.class) as LeaveRequest;

in.requester = new UserDTO(ivy.session.getSecurityContext().findUser(in.leaveRequestData.requesterUsername));
in.approver = new UserDTO(ivy.session.getSecurityContext().findUser(in.leaveRequestData.approverUsername));' #txt
Ls0 f6 security system #txt
Ls0 f6 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>load data</name>
    </language>
</elementInfo>
' #txt
Ls0 f6 168 42 112 44 -25 -8 #rect
Ls0 f6 @|StepIcon #fIcon
Ls0 f7 109 64 168 64 #arcP
Ls0 f2 280 64 339 64 #arcP
>Proto Ls0 .type com.axonivy.portal.userexamples.leaverequest.LeaveRequestSummary.LeaveRequestSummaryData #txt
>Proto Ls0 .processKind HTML_DIALOG #txt
>Proto Ls0 -8 -8 16 16 16 26 #rect
>Proto Ls0 '' #fIcon
Ls0 f3 mainOut f5 tail #connect
Ls0 f5 head f4 mainIn #connect
Ls0 f0 mainOut f7 tail #connect
Ls0 f7 head f6 mainIn #connect
Ls0 f6 mainOut f2 tail #connect
Ls0 f2 head f1 mainIn #connect
