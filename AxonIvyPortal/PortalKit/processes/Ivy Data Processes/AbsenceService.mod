[Ivy]
14BDDCD00C5EA267 9.2.0 #module
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
Se0 @GridStep f86 '' #zField
Se0 @StartSub f0 '' #zField
Se0 @GridStep f3 '' #zField
Se0 @EndSub f7 '' #zField
Se0 @StartSub f9 '' #zField
Se0 @EndSub f10 '' #zField
Se0 @GridStep f13 '' #zField
Se0 @EndSub f18 '' #zField
Se0 @GridStep f20 '' #zField
Se0 @StartSub f21 '' #zField
Se0 @PushWFArc f27 '' #zField
Se0 @PushWFArc f22 '' #zField
Se0 @PushWFArc f1 '' #zField
Se0 @PushWFArc f2 '' #zField
Se0 @PushWFArc f4 '' #zField
Se0 @PushWFArc f11 '' #zField
Se0 @PushWFArc f12 '' #zField
Se0 @PushWFArc f6 '' #zField
>Proto Se0 Se0 AbsenceService #zField
Se0 f81 inParamDecl '<String username> param;' #txt
Se0 f81 inParamTable 'out.username=param.username;
' #txt
Se0 f81 outParamDecl '<java.util.Map absencesByUser> result;' #txt
Se0 f81 outParamTable 'result.absencesByUser=in.absencesByUser;
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
Se0 f82 419 49 30 30 0 15 #rect
Se0 f82 @|EndSubIcon #fIcon
Se0 f86 actionTable 'out=in;
' #txt
Se0 f86 actionCode 'import ch.ivy.addon.portalkit.ivydata.dto.IvyAbsenceResultDTO;
import ch.ivy.addon.portalkit.ivydata.service.impl.AbsenceService;

IvyAbsenceResultDTO dto = AbsenceService.newInstance().findAbsences(in.username);
out.absencesByUser = dto.getIvyAbsencesByUser();
' #txt
Se0 f86 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Find absences</name>
    </language>
</elementInfo>
' #txt
Se0 f86 210 42 112 44 -41 -8 #rect
Se0 f86 @|StepIcon #fIcon
Se0 f0 inParamDecl '<String username,java.util.Set ivyAbsences> param;' #txt
Se0 f0 inParamTable 'out.absences=param.ivyAbsences;
out.username=param.username;
' #txt
Se0 f0 outParamDecl '<> result;' #txt
Se0 f0 callSignature updateAbsences(String,java.util.Set) #txt
Se0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>updateAbsences(String,Set)</name>
    </language>
</elementInfo>
' #txt
Se0 f0 81 241 30 30 -70 18 #rect
Se0 f0 @|StartSubIcon #fIcon
Se0 f3 actionTable 'out=in;
' #txt
Se0 f3 actionCode 'import ch.ivy.addon.portalkit.ivydata.service.impl.AbsenceService;

AbsenceService.newInstance().updateAbsences(in.username, in.absences);
' #txt
Se0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Save absences</name>
    </language>
</elementInfo>
' #txt
Se0 f3 210 234 112 44 -42 -8 #rect
Se0 f3 @|StepIcon #fIcon
Se0 f7 419 241 30 30 0 15 #rect
Se0 f7 @|EndSubIcon #fIcon
Se0 f9 inParamDecl '<ch.ivy.addon.portalkit.ivydata.bo.IvyAbsence ivyAbsence> param;' #txt
Se0 f9 inParamTable 'out.absence=param.ivyAbsence;
' #txt
Se0 f9 outParamDecl '<> result;' #txt
Se0 f9 callSignature deleteAbsence(ch.ivy.addon.portalkit.ivydata.bo.IvyAbsence) #txt
Se0 f9 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>deleteAbsence(IvyAbsence)</name>
    </language>
</elementInfo>
' #txt
Se0 f9 81 337 30 30 -75 17 #rect
Se0 f9 @|StartSubIcon #fIcon
Se0 f10 419 337 30 30 0 15 #rect
Se0 f10 @|EndSubIcon #fIcon
Se0 f13 actionTable 'out=in;
' #txt
Se0 f13 actionCode 'import ch.ivy.addon.portalkit.ivydata.service.impl.AbsenceService;

AbsenceService.newInstance().deleteAbsence(in.absence);
' #txt
Se0 f13 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Delete absence</name>
    </language>
</elementInfo>
' #txt
Se0 f13 210 330 112 44 -43 -8 #rect
Se0 f13 @|StepIcon #fIcon
Se0 f18 419 145 30 30 0 15 #rect
Se0 f18 @|EndSubIcon #fIcon
Se0 f20 actionTable 'out=in;
' #txt
Se0 f20 actionCode 'import ch.ivy.addon.portalkit.ivydata.service.impl.AbsenceService;

AbsenceService.newInstance().createAbsence(in.absence);
' #txt
Se0 f20 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Create absence</name>
    </language>
</elementInfo>
' #txt
Se0 f20 210 138 112 44 -44 -8 #rect
Se0 f20 @|StepIcon #fIcon
Se0 f21 inParamDecl '<ch.ivy.addon.portalkit.ivydata.bo.IvyAbsence absence> param;' #txt
Se0 f21 inParamTable 'out.absence=param.absence;
' #txt
Se0 f21 outParamDecl '<> result;' #txt
Se0 f21 callSignature createAbsence(ch.ivy.addon.portalkit.ivydata.bo.IvyAbsence) #txt
Se0 f21 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>createAbsence(IvyAbsence)</name>
    </language>
</elementInfo>
' #txt
Se0 f21 81 145 30 30 -75 19 #rect
Se0 f21 @|StartSubIcon #fIcon
Se0 f27 111 64 210 64 #arcP
Se0 f22 111 160 210 160 #arcP
Se0 f1 111 256 210 256 #arcP
Se0 f2 111 352 210 352 #arcP
Se0 f4 expr out #txt
Se0 f4 322 64 419 64 #arcP
Se0 f11 expr out #txt
Se0 f11 322 160 419 160 #arcP
Se0 f12 expr out #txt
Se0 f12 322 256 419 256 #arcP
Se0 f6 expr out #txt
Se0 f6 322 352 419 352 #arcP
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
Se0 f81 mainOut f27 tail #connect
Se0 f27 head f86 mainIn #connect
Se0 f21 mainOut f22 tail #connect
Se0 f22 head f20 mainIn #connect
Se0 f0 mainOut f1 tail #connect
Se0 f1 head f3 mainIn #connect
Se0 f9 mainOut f2 tail #connect
Se0 f2 head f13 mainIn #connect
Se0 f86 mainOut f4 tail #connect
Se0 f4 head f82 mainIn #connect
Se0 f20 mainOut f11 tail #connect
Se0 f11 head f18 mainIn #connect
Se0 f3 mainOut f12 tail #connect
Se0 f12 head f7 mainIn #connect
Se0 f13 mainOut f6 tail #connect
Se0 f6 head f10 mainIn #connect
