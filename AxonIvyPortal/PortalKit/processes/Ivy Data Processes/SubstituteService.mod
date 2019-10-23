[Ivy]
14BECA923C1F4A4B 7.5.0 #module
>Proto >Proto Collection #zClass
Se0 SubstituteService Big #zClass
Se0 B #cInfo
Se0 #process
Se0 @TextInP .type .type #zField
Se0 @TextInP .processKind .processKind #zField
Se0 @AnnotationInP-0n ai ai #zField
Se0 @TextInP .xml .xml #zField
Se0 @TextInP .responsibility .responsibility #zField
Se0 @StartSub f0 '' #zField
Se0 @EndSub f1 '' #zField
Se0 @CallSub f88 '' #zField
Se0 @GridStep f84 '' #zField
Se0 @GridStep f86 '' #zField
Se0 @PushWFArc f89 '' #zField
Se0 @PushWFArc f87 '' #zField
Se0 @PushWFArc f2 '' #zField
Se0 @PushWFArc f3 '' #zField
Se0 @StartSub f4 '' #zField
Se0 @EndSub f5 '' #zField
Se0 @GridStep f8 '' #zField
Se0 @CallSub f9 '' #zField
Se0 @PushWFArc f11 '' #zField
Se0 @PushWFArc f6 '' #zField
Se0 @PushWFArc f7 '' #zField
>Proto Se0 Se0 SubstituteService #zField
Se0 f0 inParamDecl '<String username> param;' #txt
Se0 f0 inParamTable 'out.username=param.username;
' #txt
Se0 f0 outParamDecl '<java.util.List<ch.ivy.addon.portalkit.ivydata.exception.PortalIvyDataException> errors,java.util.Map substitutesByApp> result;' #txt
Se0 f0 outParamTable 'result.errors=in.errors;
result.substitutesByApp=in.substitutesByApp;
' #txt
Se0 f0 callSignature findSubstitutes(String) #txt
Se0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>findSubstitutes(String)</name>
    </language>
</elementInfo>
' #txt
Se0 f0 81 49 30 30 -61 17 #rect
Se0 f0 @|StartSubIcon #fIcon
Se0 f1 721 49 30 30 0 15 #rect
Se0 f1 @|EndSubIcon #fIcon
Se0 f88 processCall 'Functional Processes/ErrorHandler:handle(List<ch.ivy.addon.portalkit.ivydata.exception.PortalIvyDataException>)' #txt
Se0 f88 requestActionDecl '<java.util.List<ch.ivy.addon.portalkit.ivydata.exception.PortalIvyDataException> exceptions> param;' #txt
Se0 f88 requestMappingAction 'param.exceptions=in.errors;
' #txt
Se0 f88 responseActionDecl 'ch.ivyteam.wf.processes.SubstituteServiceData out;
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
Se0 f88 530 42 112 44 -35 -8 #rect
Se0 f88 @|CallSubIcon #fIcon
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
Se0 f84 160 42 176 44 -81 -8 #rect
Se0 f84 @|StepIcon #fIcon
Se0 f86 actionTable 'out=in;
' #txt
Se0 f86 actionCode 'import ch.ivy.addon.portalkit.ivydata.dto.IvySubstituteResultDTO;
import ch.ivy.addon.portalkit.ivydata.service.impl.SubstituteService;

IvySubstituteResultDTO dto = SubstituteService.newInstance().findSubstitutes(in.username, in.apps);
out.substitutesByApp = dto.getIvySubstitutesByApp();
out.errors = dto.errors;' #txt
Se0 f86 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Find substitutes</name>
    </language>
</elementInfo>
' #txt
Se0 f86 370 42 112 44 -44 -8 #rect
Se0 f86 @|StepIcon #fIcon
Se0 f89 expr out #txt
Se0 f89 482 64 530 64 #arcP
Se0 f87 expr out #txt
Se0 f87 336 64 370 64 #arcP
Se0 f2 expr out #txt
Se0 f2 111 64 160 64 #arcP
Se0 f3 expr out #txt
Se0 f3 642 64 721 64 #arcP
Se0 f4 inParamDecl '<String username,java.util.Map substitutesByApp> param;' #txt
Se0 f4 inParamTable 'out.substitutesByApp=param.substitutesByApp;
out.username=param.username;
' #txt
Se0 f4 outParamDecl '<java.util.List<ch.ivy.addon.portalkit.ivydata.exception.PortalIvyDataException> errors> result;' #txt
Se0 f4 outParamTable 'result.errors=in.errors;
' #txt
Se0 f4 callSignature saveSubstitutes(String,java.util.Map) #txt
Se0 f4 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>saveSubstitutes(String,Map)</name>
    </language>
</elementInfo>
' #txt
Se0 f4 81 177 30 30 -74 20 #rect
Se0 f4 @|StartSubIcon #fIcon
Se0 f5 721 177 30 30 0 15 #rect
Se0 f5 @|EndSubIcon #fIcon
Se0 f8 actionTable 'out=in;
' #txt
Se0 f8 actionCode 'import ch.ivy.addon.portalkit.ivydata.dto.IvySubstituteResultDTO;
import ch.ivy.addon.portalkit.ivydata.service.impl.SubstituteService;

IvySubstituteResultDTO dto = SubstituteService.newInstance().saveSubstitutes(in.username, in.substitutesByApp);
out.errors = dto.errors;' #txt
Se0 f8 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Save substitutes</name>
    </language>
</elementInfo>
' #txt
Se0 f8 232 170 112 44 -45 -8 #rect
Se0 f8 @|StepIcon #fIcon
Se0 f9 processCall 'Functional Processes/ErrorHandler:handle(List<ch.ivy.addon.portalkit.ivydata.exception.PortalIvyDataException>)' #txt
Se0 f9 requestActionDecl '<java.util.List<ch.ivy.addon.portalkit.ivydata.exception.PortalIvyDataException> exceptions> param;' #txt
Se0 f9 requestMappingAction 'param.exceptions=in.errors;
' #txt
Se0 f9 responseActionDecl 'ch.ivyteam.wf.processes.SubstituteServiceData out;
' #txt
Se0 f9 responseMappingAction 'out=in;
' #txt
Se0 f9 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>ErrorHandler</name>
    </language>
</elementInfo>
' #txt
Se0 f9 458 170 112 44 -35 -8 #rect
Se0 f9 @|CallSubIcon #fIcon
Se0 f11 expr out #txt
Se0 f11 344 192 458 192 #arcP
Se0 f6 expr out #txt
Se0 f6 570 192 721 192 #arcP
Se0 f7 expr out #txt
Se0 f7 111 192 232 192 #arcP
>Proto Se0 .type ch.ivyteam.wf.processes.SubstituteServiceData #txt
>Proto Se0 .processKind CALLABLE_SUB #txt
>Proto Se0 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <swimlaneLabel>Add substitutes</swimlaneLabel>
        <swimlaneLabel>Set substitutes</swimlaneLabel>
        <swimlaneLabel></swimlaneLabel>
        <swimlaneLabel></swimlaneLabel>
    </language>
    <swimlaneSize>272</swimlaneSize>
    <swimlaneSize>496</swimlaneSize>
    <swimlaneSize>840</swimlaneSize>
    <swimlaneColor>-1</swimlaneColor>
    <swimlaneColor>-1</swimlaneColor>
    <swimlaneColor>-1</swimlaneColor>
</elementInfo>
' #txt
>Proto Se0 0 0 32 24 18 0 #rect
>Proto Se0 @|BIcon #fIcon
Se0 f84 mainOut f87 tail #connect
Se0 f87 head f86 mainIn #connect
Se0 f86 mainOut f89 tail #connect
Se0 f89 head f88 mainIn #connect
Se0 f0 mainOut f2 tail #connect
Se0 f2 head f84 mainIn #connect
Se0 f88 mainOut f3 tail #connect
Se0 f3 head f1 mainIn #connect
Se0 f8 mainOut f11 tail #connect
Se0 f11 head f9 mainIn #connect
Se0 f9 mainOut f6 tail #connect
Se0 f6 head f5 mainIn #connect
Se0 f4 mainOut f7 tail #connect
Se0 f7 head f8 mainIn #connect
