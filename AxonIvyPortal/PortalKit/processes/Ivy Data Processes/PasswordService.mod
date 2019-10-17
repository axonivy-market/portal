[Ivy]
15E0CEDE89CC1D0E 7.5.0 #module
>Proto >Proto Collection #zClass
Pe0 PasswordService Big #zClass
Pe0 B #cInfo
Pe0 #process
Pe0 @TextInP .type .type #zField
Pe0 @TextInP .processKind .processKind #zField
Pe0 @AnnotationInP-0n ai ai #zField
Pe0 @MessageFlowInP-0n messageIn messageIn #zField
Pe0 @MessageFlowOutP-0n messageOut messageOut #zField
Pe0 @TextInP .xml .xml #zField
Pe0 @TextInP .responsibility .responsibility #zField
Pe0 @StartSub f0 '' #zField
Pe0 @EndSub f1 '' #zField
Pe0 @GridStep f10 '' #zField
Pe0 @GridStep f6 '' #zField
Pe0 @CallSub f2 '' #zField
Pe0 @PushWFArc f7 '' #zField
Pe0 @PushWFArc f3 '' #zField
Pe0 @PushWFArc f4 '' #zField
Pe0 @PushWFArc f5 '' #zField
>Proto Pe0 Pe0 PasswordService #zField
Pe0 f0 inParamDecl '<String username,String newPassword> param;' #txt
Pe0 f0 inParamTable 'out.newPassword=param.newPassword;
out.username=param.username;
' #txt
Pe0 f0 outParamDecl '<java.util.List<ch.ivy.addon.portalkit.ivydata.exception.PortalIvyDataException> errors> result;' #txt
Pe0 f0 outParamTable 'result.errors=in.errors;
' #txt
Pe0 f0 callSignature updatePassword(String,String) #txt
Pe0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>updatePassword(String,String)</name>
    </language>
</elementInfo>
' #txt
Pe0 f0 81 81 30 30 -86 17 #rect
Pe0 f0 @|StartSubIcon #fIcon
Pe0 f1 777 81 30 30 0 15 #rect
Pe0 f1 @|EndSubIcon #fIcon
Pe0 f10 actionTable 'out=in;
' #txt
Pe0 f10 actionCode 'import ch.ivy.addon.portalkit.service.RegisteredApplicationService;

RegisteredApplicationService service = new RegisteredApplicationService();
out.apps = service.findActiveIvyAppsUserCanWorkOn(in.username);
' #txt
Pe0 f10 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Find applications can work on</name>
    </language>
</elementInfo>
' #txt
Pe0 f10 208 74 176 44 -81 -8 #rect
Pe0 f10 @|StepIcon #fIcon
Pe0 f6 actionTable 'out=in;
' #txt
Pe0 f6 actionCode 'import ch.ivy.addon.portalkit.ivydata.dto.IvyPasswordResultDTO;
import ch.ivy.addon.portalkit.ivydata.service.impl.PasswordService;

IvyPasswordResultDTO dto = PasswordService.newInstance().changePassword(in.username, in.newPassword, in.apps);
out.errors = dto.errors;' #txt
Pe0 f6 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Change password</name>
    </language>
</elementInfo>
' #txt
Pe0 f6 440 74 112 44 -51 -8 #rect
Pe0 f6 @|StepIcon #fIcon
Pe0 f2 processCall 'Functional Processes/ErrorHandler:handle(List<ch.ivy.addon.portalkit.ivydata.exception.PortalIvyDataException>)' #txt
Pe0 f2 requestActionDecl '<java.util.List<ch.ivy.addon.portalkit.ivydata.exception.PortalIvyDataException> exceptions> param;' #txt
Pe0 f2 requestMappingAction 'param.exceptions=in.errors;
' #txt
Pe0 f2 responseActionDecl 'ch.ivyteam.wf.processes.PasswordServiceData out;
' #txt
Pe0 f2 responseMappingAction 'out=in;
' #txt
Pe0 f2 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>ErrorHandler</name>
    </language>
</elementInfo>
' #txt
Pe0 f2 624 74 112 44 -35 -8 #rect
Pe0 f2 @|CallSubIcon #fIcon
Pe0 f7 expr out #txt
Pe0 f7 384 96 440 96 #arcP
Pe0 f3 expr out #txt
Pe0 f3 552 96 624 96 #arcP
Pe0 f4 expr out #txt
Pe0 f4 111 96 208 96 #arcP
Pe0 f5 expr out #txt
Pe0 f5 736 96 777 96 #arcP
>Proto Pe0 .type ch.ivyteam.wf.processes.PasswordServiceData #txt
>Proto Pe0 .processKind CALLABLE_SUB #txt
>Proto Pe0 0 0 32 24 18 0 #rect
>Proto Pe0 @|BIcon #fIcon
Pe0 f10 mainOut f7 tail #connect
Pe0 f7 head f6 mainIn #connect
Pe0 f6 mainOut f3 tail #connect
Pe0 f3 head f2 mainIn #connect
Pe0 f0 mainOut f4 tail #connect
Pe0 f4 head f10 mainIn #connect
Pe0 f2 mainOut f5 tail #connect
Pe0 f5 head f1 mainIn #connect
