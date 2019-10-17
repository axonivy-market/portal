[Ivy]
14BDDCD00C5EA267 7.5.0 #module
>Proto >Proto Collection #zClass
Se0 AbsenceService Big #zClass
Se0 B #cInfo
Se0 #process
Se0 @TextInP .type .type #zField
Se0 @TextInP .processKind .processKind #zField
Se0 @AnnotationInP-0n ai ai #zField
Se0 @TextInP .xml .xml #zField
Se0 @TextInP .responsibility .responsibility #zField
Se0 @StartSub f81 '' #zField
Se0 @EndSub f82 '' #zField
Se0 @GridStep f84 '' #zField
Se0 @PushWFArc f85 '' #zField
Se0 @GridStep f86 '' #zField
Se0 @PushWFArc f87 '' #zField
Se0 @CallSub f88 '' #zField
Se0 @PushWFArc f89 '' #zField
Se0 @PushWFArc f83 '' #zField
Se0 @StartSub f90 '' #zField
Se0 @StartSub f0 '' #zField
Se0 @GridStep f1 '' #zField
Se0 @PushWFArc f2 '' #zField
Se0 @GridStep f3 '' #zField
Se0 @PushWFArc f4 '' #zField
Se0 @CallSub f5 '' #zField
Se0 @PushWFArc f6 '' #zField
Se0 @EndSub f7 '' #zField
Se0 @PushWFArc f8 '' #zField
Se0 @StartSub f9 '' #zField
Se0 @EndSub f10 '' #zField
Se0 @GridStep f13 '' #zField
Se0 @CallSub f15 '' #zField
Se0 @PushWFArc f16 '' #zField
Se0 @PushWFArc f17 '' #zField
Se0 @EndSub f18 '' #zField
Se0 @CallSub f19 '' #zField
Se0 @GridStep f20 '' #zField
Se0 @StartSub f21 '' #zField
Se0 @GridStep f22 '' #zField
Se0 @PushWFArc f23 '' #zField
Se0 @PushWFArc f24 '' #zField
Se0 @PushWFArc f25 '' #zField
Se0 @PushWFArc f26 '' #zField
Se0 @GridStep f27 '' #zField
Se0 @GridStep f28 '' #zField
Se0 @CallSub f29 '' #zField
Se0 @PushWFArc f30 '' #zField
Se0 @PushWFArc f31 '' #zField
Se0 @PushWFArc f32 '' #zField
Se0 @EndSub f33 '' #zField
Se0 @PushWFArc f34 '' #zField
Se0 @PushWFArc f14 '' #zField
Se0 @PushWFArc f12 '' #zField
Se0 @GridStep f11 '' #zField
>Proto Se0 Se0 AbsenceService #zField
Se0 f81 inParamDecl '<String username> param;' #txt
Se0 f81 inParamTable 'out.username=param.username;
' #txt
Se0 f81 outParamDecl '<java.util.Map absencesByUser,java.util.List<ch.ivy.addon.portalkit.ivydata.exception.PortalIvyDataException> errors> result;' #txt
Se0 f81 outParamTable 'result.absencesByUser=in.absencesByUser;
result.errors=in.errors;
' #txt
Se0 f81 callSignature findAbsences(String) #txt
Se0 f81 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>findAbsences(String)</name>
    </language>
</elementInfo>
' #txt
Se0 f81 81 49 30 30 -57 17 #rect
Se0 f81 @|StartSubIcon #fIcon
Se0 f82 683 49 30 30 0 15 #rect
Se0 f82 @|EndSubIcon #fIcon
Se0 f84 actionTable 'out=in;
' #txt
Se0 f84 actionCode 'import ch.ivy.addon.portalkit.service.RegisteredApplicationService;

RegisteredApplicationService service = new RegisteredApplicationService();
out.apps = service.findActiveIvyAppsUserCanWorkOn(in.username);
' #txt
Se0 f84 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Find applications can work on</name>
    </language>
</elementInfo>
' #txt
Se0 f84 144 42 176 44 -81 -8 #rect
Se0 f84 @|StepIcon #fIcon
Se0 f85 expr out #txt
Se0 f85 111 64 144 64 #arcP
Se0 f86 actionTable 'out=in;
' #txt
Se0 f86 actionCode 'import ch.ivy.addon.portalkit.ivydata.dto.IvyAbsenceResultDTO;
import ch.ivy.addon.portalkit.ivydata.service.impl.AbsenceService;

IvyAbsenceResultDTO dto = AbsenceService.newInstance().findAbsences(in.username, in.apps);
out.absencesByUser = dto.getIvyAbsencesByUser();
out.errors = dto.errors;' #txt
Se0 f86 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Find absences</name>
    </language>
</elementInfo>
' #txt
Se0 f86 354 42 112 44 -41 -8 #rect
Se0 f86 @|StepIcon #fIcon
Se0 f87 expr out #txt
Se0 f87 320 64 354 64 #arcP
Se0 f88 processCall 'Functional Processes/ErrorHandler:handle(List<ch.ivy.addon.portalkit.ivydata.exception.PortalIvyDataException>)' #txt
Se0 f88 requestActionDecl '<java.util.List<ch.ivy.addon.portalkit.ivydata.exception.PortalIvyDataException> exceptions> param;' #txt
Se0 f88 requestMappingAction 'param.exceptions=in.errors;
' #txt
Se0 f88 responseActionDecl 'ch.ivyteam.wf.processes.AbsenceServiceData out;
' #txt
Se0 f88 responseMappingAction 'out=in;
' #txt
Se0 f88 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>ErrorHandler</name>
    </language>
</elementInfo>
' #txt
Se0 f88 514 42 112 44 -35 -8 #rect
Se0 f88 @|CallSubIcon #fIcon
Se0 f89 expr out #txt
Se0 f89 466 64 514 64 #arcP
Se0 f83 expr out #txt
Se0 f83 626 64 683 64 #arcP
Se0 f90 inParamDecl '<String username> param;' #txt
Se0 f90 inParamTable 'out.username=param.username;
' #txt
Se0 f90 outParamDecl '<java.util.Map absencesByUser,java.util.List<ch.ivy.addon.portalkit.ivydata.exception.PortalIvyDataException> errors> result;' #txt
Se0 f90 outParamTable 'result.absencesByUser=in.absencesByUser;
result.errors=in.errors;
' #txt
Se0 f90 callSignature findAbsencesOfAllUsers(String) #txt
Se0 f90 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>findAbsencesOfAllUsers(String)</name>
    </language>
</elementInfo>
' #txt
Se0 f90 81 145 30 30 -87 17 #rect
Se0 f90 @|StartSubIcon #fIcon
Se0 f0 inParamDecl '<String username,java.util.Set ivyAbsences> param;' #txt
Se0 f0 inParamTable 'out.absences=param.ivyAbsences;
out.username=param.username;
' #txt
Se0 f0 outParamDecl '<java.util.List<ch.ivy.addon.portalkit.ivydata.exception.PortalIvyDataException> errors> result;' #txt
Se0 f0 outParamTable 'result.errors=in.errors;
' #txt
Se0 f0 callSignature updateAbsences(String,java.util.Set) #txt
Se0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>updateAbsences(String,Set)</name>
    </language>
</elementInfo>
' #txt
Se0 f0 81 337 30 30 -70 18 #rect
Se0 f0 @|StartSubIcon #fIcon
Se0 f1 actionTable 'out=in;
' #txt
Se0 f1 actionCode 'import ch.ivy.addon.portalkit.service.RegisteredApplicationService;

RegisteredApplicationService service = new RegisteredApplicationService();
out.apps = service.findActiveIvyAppsUserCanWorkOn(in.username);
' #txt
Se0 f1 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Find applications can work on</name>
    </language>
</elementInfo>
' #txt
Se0 f1 160 330 176 44 -81 -8 #rect
Se0 f1 @|StepIcon #fIcon
Se0 f2 expr out #txt
Se0 f2 111 352 160 352 #arcP
Se0 f3 actionTable 'out=in;
' #txt
Se0 f3 actionCode 'import ch.ivy.addon.portalkit.ivydata.dto.IvyAbsenceResultDTO;
import ch.ivy.addon.portalkit.ivydata.service.impl.AbsenceService;

IvyAbsenceResultDTO dto = AbsenceService.newInstance().updateAbsences(in.username, in.absences, in.apps);
out.errors = dto.errors;' #txt
Se0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Save absences</name>
    </language>
</elementInfo>
' #txt
Se0 f3 376 330 112 44 -42 -8 #rect
Se0 f3 @|StepIcon #fIcon
Se0 f4 expr out #txt
Se0 f4 336 352 376 352 #arcP
Se0 f5 processCall 'Functional Processes/ErrorHandler:handle(List<ch.ivy.addon.portalkit.ivydata.exception.PortalIvyDataException>)' #txt
Se0 f5 requestActionDecl '<java.util.List<ch.ivy.addon.portalkit.ivydata.exception.PortalIvyDataException> exceptions> param;' #txt
Se0 f5 requestMappingAction 'param.exceptions=in.errors;
' #txt
Se0 f5 responseActionDecl 'ch.ivyteam.wf.processes.AbsenceServiceData out;
' #txt
Se0 f5 responseMappingAction 'out=in;
' #txt
Se0 f5 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>ErrorHandler</name>
    </language>
</elementInfo>
' #txt
Se0 f5 538 330 112 44 -35 -8 #rect
Se0 f5 @|CallSubIcon #fIcon
Se0 f6 expr out #txt
Se0 f6 488 352 538 352 #arcP
Se0 f7 689 337 30 30 0 15 #rect
Se0 f7 @|EndSubIcon #fIcon
Se0 f8 expr out #txt
Se0 f8 650 352 689 352 #arcP
Se0 f9 inParamDecl '<ch.ivy.addon.portalkit.ivydata.bo.IvyAbsence ivyAbsence> param;' #txt
Se0 f9 inParamTable 'out.absence=param.ivyAbsence;
' #txt
Se0 f9 outParamDecl '<java.util.List<ch.ivy.addon.portalkit.ivydata.exception.PortalIvyDataException> errors> result;' #txt
Se0 f9 outParamTable 'result.errors=in.errors;
' #txt
Se0 f9 callSignature deleteAbsence(ch.ivy.addon.portalkit.ivydata.bo.IvyAbsence) #txt
Se0 f9 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>deleteAbsence(IvyAbsence)</name>
    </language>
</elementInfo>
' #txt
Se0 f9 81 433 30 30 -75 17 #rect
Se0 f9 @|StartSubIcon #fIcon
Se0 f10 689 433 30 30 0 15 #rect
Se0 f10 @|EndSubIcon #fIcon
Se0 f13 actionTable 'out=in;
' #txt
Se0 f13 actionCode 'import ch.ivy.addon.portalkit.ivydata.dto.IvyAbsenceResultDTO;
import ch.ivy.addon.portalkit.ivydata.service.impl.AbsenceService;

IvyAbsenceResultDTO dto = AbsenceService.newInstance().deleteAbsence(in.absence, in.apps);
out.errors = dto.errors;' #txt
Se0 f13 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Delete absence</name>
    </language>
</elementInfo>
' #txt
Se0 f13 376 426 112 44 -43 -8 #rect
Se0 f13 @|StepIcon #fIcon
Se0 f15 processCall 'Functional Processes/ErrorHandler:handle(List<ch.ivy.addon.portalkit.ivydata.exception.PortalIvyDataException>)' #txt
Se0 f15 requestActionDecl '<java.util.List<ch.ivy.addon.portalkit.ivydata.exception.PortalIvyDataException> exceptions> param;' #txt
Se0 f15 requestMappingAction 'param.exceptions=in.errors;
' #txt
Se0 f15 responseActionDecl 'ch.ivyteam.wf.processes.AbsenceServiceData out;
' #txt
Se0 f15 responseMappingAction 'out=in;
' #txt
Se0 f15 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>ErrorHandler</name>
    </language>
</elementInfo>
' #txt
Se0 f15 522 426 112 44 -35 -8 #rect
Se0 f15 @|CallSubIcon #fIcon
Se0 f16 expr out #txt
Se0 f16 488 448 522 448 #arcP
Se0 f17 expr out #txt
Se0 f17 634 448 689 448 #arcP
Se0 f18 689 241 30 30 0 15 #rect
Se0 f18 @|EndSubIcon #fIcon
Se0 f19 processCall 'Functional Processes/ErrorHandler:handle(List<ch.ivy.addon.portalkit.ivydata.exception.PortalIvyDataException>)' #txt
Se0 f19 requestActionDecl '<java.util.List<ch.ivy.addon.portalkit.ivydata.exception.PortalIvyDataException> exceptions> param;' #txt
Se0 f19 requestMappingAction 'param.exceptions=in.errors;
' #txt
Se0 f19 responseActionDecl 'ch.ivyteam.wf.processes.AbsenceServiceData out;
' #txt
Se0 f19 responseMappingAction 'out=in;
' #txt
Se0 f19 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>ErrorHandler</name>
    </language>
</elementInfo>
' #txt
Se0 f19 538 234 112 44 -35 -8 #rect
Se0 f19 @|CallSubIcon #fIcon
Se0 f20 actionTable 'out=in;
' #txt
Se0 f20 actionCode 'import ch.ivy.addon.portalkit.ivydata.dto.IvyAbsenceResultDTO;
import ch.ivy.addon.portalkit.ivydata.service.impl.AbsenceService;

IvyAbsenceResultDTO dto = AbsenceService.newInstance().createAbsence(in.absence, in.apps);
out.errors = dto.errors;' #txt
Se0 f20 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Create absence</name>
    </language>
</elementInfo>
' #txt
Se0 f20 376 234 112 44 -44 -8 #rect
Se0 f20 @|StepIcon #fIcon
Se0 f21 inParamDecl '<ch.ivy.addon.portalkit.ivydata.bo.IvyAbsence absence> param;' #txt
Se0 f21 inParamTable 'out.absence=param.absence;
' #txt
Se0 f21 outParamDecl '<java.util.List<ch.ivy.addon.portalkit.ivydata.exception.PortalIvyDataException> errors> result;' #txt
Se0 f21 outParamTable 'result.errors=in.errors;
' #txt
Se0 f21 callSignature createAbsence(ch.ivy.addon.portalkit.ivydata.bo.IvyAbsence) #txt
Se0 f21 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>createAbsence(IvyAbsence)</name>
    </language>
</elementInfo>
' #txt
Se0 f21 81 241 30 30 -75 19 #rect
Se0 f21 @|StartSubIcon #fIcon
Se0 f22 actionTable 'out=in;
' #txt
Se0 f22 actionCode 'import ch.ivy.addon.portalkit.service.RegisteredApplicationService;

RegisteredApplicationService service = new RegisteredApplicationService();
out.apps = service.findActiveIvyAppsUserCanWorkOn(in.absence.getUsername());
' #txt
Se0 f22 security system #txt
Se0 f22 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Find applications can work on</name>
    </language>
</elementInfo>
' #txt
Se0 f22 160 234 176 44 -81 -8 #rect
Se0 f22 @|StepIcon #fIcon
Se0 f23 expr out #txt
Se0 f23 111 256 160 256 #arcP
Se0 f24 expr out #txt
Se0 f24 336 256 376 256 #arcP
Se0 f25 expr out #txt
Se0 f25 650 256 689 256 #arcP
Se0 f26 expr out #txt
Se0 f26 488 256 538 256 #arcP
Se0 f27 actionTable 'out=in;
' #txt
Se0 f27 actionCode 'import ch.ivy.addon.portalkit.service.RegisteredApplicationService;

RegisteredApplicationService service = new RegisteredApplicationService();
out.apps = service.findActiveIvyAppsUserCanWorkOn(in.username);
' #txt
Se0 f27 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Find applications can work on</name>
    </language>
</elementInfo>
' #txt
Se0 f27 152 138 176 44 -81 -8 #rect
Se0 f27 @|StepIcon #fIcon
Se0 f28 actionTable 'out=in;
' #txt
Se0 f28 actionCode 'import ch.ivy.addon.portalkit.ivydata.dto.IvyAbsenceResultDTO;
import ch.ivy.addon.portalkit.ivydata.service.impl.AbsenceService;

IvyAbsenceResultDTO dto = AbsenceService.newInstance().findAbsences(null, in.apps);
out.absencesByUser = dto.getIvyAbsencesByUser();
out.errors = dto.errors;' #txt
Se0 f28 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Find absences</name>
    </language>
</elementInfo>
' #txt
Se0 f28 362 138 112 44 -41 -8 #rect
Se0 f28 @|StepIcon #fIcon
Se0 f29 processCall 'Functional Processes/ErrorHandler:handle(List<ch.ivy.addon.portalkit.ivydata.exception.PortalIvyDataException>)' #txt
Se0 f29 requestActionDecl '<java.util.List<ch.ivy.addon.portalkit.ivydata.exception.PortalIvyDataException> exceptions> param;' #txt
Se0 f29 requestMappingAction 'param.exceptions=in.errors;
' #txt
Se0 f29 responseActionDecl 'ch.ivyteam.wf.processes.AbsenceServiceData out;
' #txt
Se0 f29 responseMappingAction 'out=in;
' #txt
Se0 f29 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>ErrorHandler</name>
    </language>
</elementInfo>
' #txt
Se0 f29 522 138 112 44 -35 -8 #rect
Se0 f29 @|CallSubIcon #fIcon
Se0 f30 expr out #txt
Se0 f30 474 160 522 160 #arcP
Se0 f31 expr out #txt
Se0 f31 328 160 362 160 #arcP
Se0 f32 expr out #txt
Se0 f32 111 160 152 160 #arcP
Se0 f32 0 0.49492365927107135 0 0 #arcLabel
Se0 f33 691 145 30 30 0 15 #rect
Se0 f33 @|EndSubIcon #fIcon
Se0 f34 expr out #txt
Se0 f34 634 160 691 160 #arcP
Se0 f14 expr out #txt
Se0 f14 344 448 376 448 #arcP
Se0 f12 expr out #txt
Se0 f12 111 448 168 448 #arcP
Se0 f11 actionTable 'out=in;
' #txt
Se0 f11 actionCode 'import ch.ivy.addon.portalkit.service.RegisteredApplicationService;

RegisteredApplicationService service = new RegisteredApplicationService();
out.apps = service.findActiveIvyAppsUserCanWorkOn(in.absence.getUsername());
' #txt
Se0 f11 security system #txt
Se0 f11 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Find applications can work on</name>
    </language>
</elementInfo>
' #txt
Se0 f11 168 426 176 44 -81 -8 #rect
Se0 f11 @|StepIcon #fIcon
>Proto Se0 .type ch.ivyteam.wf.processes.AbsenceServiceData #txt
>Proto Se0 .processKind CALLABLE_SUB #txt
>Proto Se0 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <swimlaneLabel>Absence</swimlaneLabel>
        <swimlaneLabel></swimlaneLabel>
    </language>
    <swimlaneSize>1720</swimlaneSize>
    <swimlaneColor>-6684775</swimlaneColor>
</elementInfo>
' #txt
>Proto Se0 0 0 32 24 18 0 #rect
>Proto Se0 @|BIcon #fIcon
Se0 f81 mainOut f85 tail #connect
Se0 f85 head f84 mainIn #connect
Se0 f84 mainOut f87 tail #connect
Se0 f87 head f86 mainIn #connect
Se0 f86 mainOut f89 tail #connect
Se0 f89 head f88 mainIn #connect
Se0 f88 mainOut f83 tail #connect
Se0 f83 head f82 mainIn #connect
Se0 f0 mainOut f2 tail #connect
Se0 f2 head f1 mainIn #connect
Se0 f1 mainOut f4 tail #connect
Se0 f4 head f3 mainIn #connect
Se0 f3 mainOut f6 tail #connect
Se0 f6 head f5 mainIn #connect
Se0 f5 mainOut f8 tail #connect
Se0 f8 head f7 mainIn #connect
Se0 f9 mainOut f12 tail #connect
Se0 f12 head f11 mainIn #connect
Se0 f11 mainOut f14 tail #connect
Se0 f14 head f13 mainIn #connect
Se0 f13 mainOut f16 tail #connect
Se0 f16 head f15 mainIn #connect
Se0 f15 mainOut f17 tail #connect
Se0 f17 head f10 mainIn #connect
Se0 f21 mainOut f23 tail #connect
Se0 f23 head f22 mainIn #connect
Se0 f22 mainOut f24 tail #connect
Se0 f24 head f20 mainIn #connect
Se0 f20 mainOut f26 tail #connect
Se0 f26 head f19 mainIn #connect
Se0 f19 mainOut f25 tail #connect
Se0 f25 head f18 mainIn #connect
Se0 f27 mainOut f31 tail #connect
Se0 f31 head f28 mainIn #connect
Se0 f28 mainOut f30 tail #connect
Se0 f30 head f29 mainIn #connect
Se0 f90 mainOut f32 tail #connect
Se0 f32 head f27 mainIn #connect
Se0 f29 mainOut f34 tail #connect
Se0 f34 head f33 mainIn #connect
