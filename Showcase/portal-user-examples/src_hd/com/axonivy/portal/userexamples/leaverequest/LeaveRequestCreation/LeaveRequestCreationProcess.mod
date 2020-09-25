[Ivy]
1703359FD55C9418 9.2.0 #module
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
Ls0 @PushWFArc f5 '' #zField
Ls0 @GridStep f10 '' #zField
Ls0 @PushWFArc f11 '' #zField
Ls0 @Alternative f12 '' #zField
Ls0 @PushWFArc f13 '' #zField
Ls0 @PushWFArc f9 '' #zField
Ls0 @UdProcessEnd f14 '' #zField
Ls0 @PushWFArc f15 '' #zField
>Proto Ls0 Ls0 LeaveRequestCreationProcess #zField
Ls0 f0 guid 1703359FD639EAC8 #txt
Ls0 f0 method start() #txt
Ls0 f0 inParameterDecl '<> param;' #txt
Ls0 f0 outParameterDecl '<String approverName,String requesterName> result;' #txt
Ls0 f0 outParameterMapAction 'result.approverName=in.leaveRequestData.approverUsername;
result.requesterName=in.leaveRequestData.requesterUsername;
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
Ls0 f4 595 147 26 26 0 12 #rect
Ls0 f4 @|UdExitEndIcon #fIcon
Ls0 f6 actionTable 'out=in;
' #txt
Ls0 f6 actionCode 'import java.util.Arrays;

import ch.ivy.addon.portalkit.dto.UserDTO;
import ch.ivy.addon.portalkit.util.UserUtils;
import com.axonivy.portal.userexamples.enums.LeaveType;
import java.util.Arrays;
import org.apache.commons.lang3.StringUtils;
import com.axonivy.portal.userexamples.leaverequest.LeaveRequest;

in.leaveRequestData = ivy.repo.get(LeaveRequest.class) as LeaveRequest;
if(StringUtils.isEmpty(in.leaveRequestData.requesterUsername)) {
	in.leaveRequestData.setRequesterUsername(ivy.session.getSessionUserName());
	ivy.repo.save(in.leaveRequestData);
}


in.requester = new UserDTO(ivy.session.getSecurityContext().findUser(in.leaveRequestData.getRequesterUsername()));
in.leaveTypes = LeaveType.class.getEnumConstants();
in.approvers = UserUtils.findUsers("", 0, -1, [], []);' #txt
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
Ls0 f8 424 138 112 44 -26 -8 #rect
Ls0 f8 @|StepIcon #fIcon
Ls0 f5 536 160 595 160 #arcP
Ls0 f10 actionTable 'out=in;
' #txt
Ls0 f10 actionCode 'import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
in.isValid = in.leaveRequestData.from.compareTo(in.leaveRequestData.to) > 0? false : true;
if(!in.isValid) {
FacesContext.getCurrentInstance().validationFailed();
FacesContext.getCurrentInstance().addMessage(
          null,
          new FacesMessage(FacesMessage.SEVERITY_ERROR, ivy.cms.co(
              "/ch.ivy.addon.portalkit.ui.jsf/common/dateFromBiggerThanTo"), null));
}' #txt
Ls0 f10 security system #txt
Ls0 f10 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>validation</name>
    </language>
</elementInfo>
' #txt
Ls0 f10 168 138 112 44 -26 -8 #rect
Ls0 f10 @|StepIcon #fIcon
Ls0 f11 109 160 168 160 #arcP
Ls0 f12 336 144 32 32 0 16 #rect
Ls0 f12 @|AlternativeIcon #fIcon
Ls0 f13 280 160 336 160 #arcP
Ls0 f9 expr in #txt
Ls0 f9 outCond in.isValid #txt
Ls0 f9 368 160 424 160 #arcP
Ls0 f14 339 243 26 26 0 12 #rect
Ls0 f14 @|UdProcessEndIcon #fIcon
Ls0 f15 352 176 352 243 #arcP
>Proto Ls0 .type com.axonivy.portal.userexamples.leaverequest.LeaveRequestCreation.LeaveRequestCreationData #txt
>Proto Ls0 .processKind HTML_DIALOG #txt
>Proto Ls0 -8 -8 16 16 16 26 #rect
>Proto Ls0 '' #fIcon
Ls0 f0 mainOut f7 tail #connect
Ls0 f7 head f6 mainIn #connect
Ls0 f6 mainOut f2 tail #connect
Ls0 f2 head f1 mainIn #connect
Ls0 f8 mainOut f5 tail #connect
Ls0 f5 head f4 mainIn #connect
Ls0 f3 mainOut f11 tail #connect
Ls0 f11 head f10 mainIn #connect
Ls0 f10 mainOut f13 tail #connect
Ls0 f13 head f12 in #connect
Ls0 f12 out f9 tail #connect
Ls0 f9 head f8 mainIn #connect
Ls0 f12 out f15 tail #connect
Ls0 f15 head f14 mainIn #connect
