[Ivy]
1703359FD55C9418 7.5.0 #module
>Proto >Proto Collection #zClass
Ls0 LeaveRequestCreationProcess Big #zClass
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
Ls0 @GridStep f6 '' #zField
Ls0 @PushWFArc f7 '' #zField
Ls0 @PushWFArc f2 '' #zField
Ls0 @GridStep f8 '' #zField
Ls0 @PushWFArc f9 '' #zField
Ls0 @PushWFArc f5 '' #zField
>Proto Ls0 Ls0 LeaveRequestCreationProcess #zField
Ls0 f0 guid 1703359FD639EAC8 #txt
Ls0 f0 method start() #txt
Ls0 f0 inParameterDecl '<> param;' #txt
Ls0 f0 outParameterDecl '<String approverName> result;' #txt
Ls0 f0 outParameterMapAction 'result.approverName=in.leaveRequestData.approverUsername;
' #txt
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
Ls0 f3 guid 1703359FD7041031 #txt
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
Ls0 f4 339 147 26 26 0 12 #rect
Ls0 f4 @|UdExitEndIcon #fIcon
Ls0 f6 actionTable 'out=in;
' #txt
Ls0 f6 actionCode 'import ch.ivy.addon.portalkit.dto.UserDTO;
import ch.ivy.addon.portalkit.util.UserUtils;
import com.axonivy.portal.businessuserexamples.enums.LeaveType;
import java.util.Arrays;
import org.apache.commons.lang3.StringUtils;
import com.axonivy.portal.businessuserexamples.leaverequest.LeaveRequest;

in.leaveRequestData = ivy.repo.get(LeaveRequest.class) as LeaveRequest;
if(StringUtils.isEmpty(in.leaveRequestData.requesterUsername)) {
	in.leaveRequestData.setRequesterUsername(ivy.session.getSessionUserName());
}


in.requester = new UserDTO(ivy.session.getSecurityContext().findUser(in.leaveRequestData.getRequesterUsername()));
in.leaveTypes = LeaveType.class.getEnumConstants();
in.approvers = UserUtils.findUsers("", 0, -1);' #txt
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
Ls0 f8 actionTable 'out=in;
' #txt
Ls0 f8 actionCode 'in.leaveRequestData.setApproverUsername(in.approver.name);
ivy.repo.save(in.leaveRequestData);' #txt
Ls0 f8 security system #txt
Ls0 f8 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>save data</name>
    </language>
</elementInfo>
' #txt
Ls0 f8 168 138 112 44 -26 -8 #rect
Ls0 f8 @|StepIcon #fIcon
Ls0 f9 109 160 168 160 #arcP
Ls0 f5 280 160 339 160 #arcP
>Proto Ls0 .type com.axonivy.portal.businessuserexamples.leaverequest.LeaveRequestCreation.LeaveRequestCreationData #txt
>Proto Ls0 .processKind HTML_DIALOG #txt
>Proto Ls0 -8 -8 16 16 16 26 #rect
>Proto Ls0 '' #fIcon
Ls0 f0 mainOut f7 tail #connect
Ls0 f7 head f6 mainIn #connect
Ls0 f6 mainOut f2 tail #connect
Ls0 f2 head f1 mainIn #connect
Ls0 f3 mainOut f9 tail #connect
Ls0 f9 head f8 mainIn #connect
Ls0 f8 mainOut f5 tail #connect
Ls0 f5 head f4 mainIn #connect
