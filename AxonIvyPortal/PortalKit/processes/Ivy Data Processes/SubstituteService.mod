[Ivy]
14BECA923C1F4A4B 9.2.0 #module
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
Se0 @GridStep f10 '' #zField
Se0 @StartSub f12 '' #zField
Se0 @CallSub f13 '' #zField
Se0 @GridStep f14 '' #zField
Se0 @EndSub f15 '' #zField
Se0 @PushWFArc f16 '' #zField
Se0 @PushWFArc f17 '' #zField
Se0 @PushWFArc f18 '' #zField
Se0 @PushWFArc f19 '' #zField
Se0 @StartSub f20 '' #zField
Se0 @GridStep f21 '' #zField
Se0 @CallSub f22 '' #zField
Se0 @EndSub f23 '' #zField
Se0 @PushWFArc f24 '' #zField
Se0 @PushWFArc f25 '' #zField
Se0 @PushWFArc f26 '' #zField
Se0 @StartSub f27 '' #zField
Se0 @CallSub f28 '' #zField
Se0 @EndSub f29 '' #zField
Se0 @GridStep f30 '' #zField
Se0 @PushWFArc f32 '' #zField
Se0 @PushWFArc f34 '' #zField
Se0 @PushWFArc f31 '' #zField
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
Se0 f10 actionTable 'out=in;
' #txt
Se0 f10 actionCode 'import ch.ivy.addon.portalkit.service.RegisteredApplicationService;

RegisteredApplicationService service = new RegisteredApplicationService();
out.apps = service.findActiveIvyAppsUserCanWorkOn(in.username);

' #txt
Se0 f10 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Find applications can work on</name>
    </language>
</elementInfo>
' #txt
Se0 f10 192 146 176 44 -81 -8 #rect
Se0 f10 @|StepIcon #fIcon
Se0 f12 inParamDecl '<String username> param;' #txt
Se0 f12 inParamTable 'out.username=param.username;
' #txt
Se0 f12 outParamDecl '<java.util.List<ch.ivy.addon.portalkit.ivydata.exception.PortalIvyDataException> errors,java.util.Map substitutesByApp> result;' #txt
Se0 f12 outParamTable 'result.errors=in.errors;
result.substitutesByApp=in.substitutesByApp;
' #txt
Se0 f12 callSignature findSubstitutions(String) #txt
Se0 f12 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>findSubstitutions(String)</name>
    </language>
</elementInfo>
' #txt
Se0 f12 81 153 30 30 -61 17 #rect
Se0 f12 @|StartSubIcon #fIcon
Se0 f13 processCall 'Functional Processes/ErrorHandler:handle(List<ch.ivy.addon.portalkit.ivydata.exception.PortalIvyDataException>)' #txt
Se0 f13 requestActionDecl '<java.util.List<ch.ivy.addon.portalkit.ivydata.exception.PortalIvyDataException> exceptions> param;' #txt
Se0 f13 requestMappingAction 'param.exceptions=in.errors;
' #txt
Se0 f13 responseActionDecl 'ch.ivyteam.wf.processes.SubstituteServiceData out;
' #txt
Se0 f13 responseMappingAction 'out=in;
' #txt
Se0 f13 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>ErrorHandler</name>
    </language>
</elementInfo>
' #txt
Se0 f13 562 146 112 44 -35 -8 #rect
Se0 f13 @|CallSubIcon #fIcon
Se0 f14 actionTable 'out=in;
' #txt
Se0 f14 actionCode 'import ch.ivy.addon.portalkit.ivydata.dto.IvySubstituteResultDTO;
import ch.ivy.addon.portalkit.ivydata.service.impl.SubstituteService;

IvySubstituteResultDTO dto = SubstituteService.newInstance().findSubstitutions(in.username, in.apps);
out.substitutesByApp = dto.getIvySubstitutesByApp();
out.errors = dto.errors;' #txt
Se0 f14 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Find substitutions</name>
    </language>
</elementInfo>
' #txt
Se0 f14 402 146 112 44 -49 -8 #rect
Se0 f14 @|StepIcon #fIcon
Se0 f15 753 153 30 30 0 15 #rect
Se0 f15 @|EndSubIcon #fIcon
Se0 f16 expr out #txt
Se0 f16 368 168 402 168 #arcP
Se0 f17 expr out #txt
Se0 f17 514 168 562 168 #arcP
Se0 f18 expr out #txt
Se0 f18 674 168 753 168 #arcP
Se0 f19 expr out #txt
Se0 f19 111 168 192 168 #arcP
Se0 f20 inParamDecl '<java.util.Map userPerApplication,java.util.Map substitutesByApp> param;' #txt
Se0 f20 inParamTable 'out.substitutesByApp=param.substitutesByApp;
out.userPerApp=param.userPerApplication;
' #txt
Se0 f20 outParamDecl '<java.util.List<ch.ivy.addon.portalkit.ivydata.exception.PortalIvyDataException> errors> result;' #txt
Se0 f20 outParamTable 'result.errors=in.errors;
' #txt
Se0 f20 callSignature saveSubstitutes(java.util.Map,java.util.Map) #txt
Se0 f20 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>saveSubstitutes(Map,Map)</name>
    </language>
</elementInfo>
' #txt
Se0 f20 89 289 30 30 -74 20 #rect
Se0 f20 @|StartSubIcon #fIcon
Se0 f21 actionTable 'out=in;
' #txt
Se0 f21 actionCode 'import ch.ivy.addon.portalkit.ivydata.dto.IvySubstituteResultDTO;
import ch.ivy.addon.portalkit.ivydata.service.impl.SubstituteService;

IvySubstituteResultDTO dto = SubstituteService.newInstance().saveSubstitutes(in.userPerApp, in.substitutesByApp);
out.errors = dto.errors;' #txt
Se0 f21 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Save substitutes</name>
    </language>
</elementInfo>
' #txt
Se0 f21 240 282 112 44 -45 -8 #rect
Se0 f21 @|StepIcon #fIcon
Se0 f22 processCall 'Functional Processes/ErrorHandler:handle(List<ch.ivy.addon.portalkit.ivydata.exception.PortalIvyDataException>)' #txt
Se0 f22 requestActionDecl '<java.util.List<ch.ivy.addon.portalkit.ivydata.exception.PortalIvyDataException> exceptions> param;' #txt
Se0 f22 requestMappingAction 'param.exceptions=in.errors;
' #txt
Se0 f22 responseActionDecl 'ch.ivyteam.wf.processes.SubstituteServiceData out;
' #txt
Se0 f22 responseMappingAction 'out=in;
' #txt
Se0 f22 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>ErrorHandler</name>
    </language>
</elementInfo>
' #txt
Se0 f22 466 282 112 44 -35 -8 #rect
Se0 f22 @|CallSubIcon #fIcon
Se0 f23 729 289 30 30 0 15 #rect
Se0 f23 @|EndSubIcon #fIcon
Se0 f24 expr out #txt
Se0 f24 119 304 240 304 #arcP
Se0 f25 expr out #txt
Se0 f25 352 304 466 304 #arcP
Se0 f26 expr out #txt
Se0 f26 578 304 729 304 #arcP
Se0 f27 inParamDecl '<String username,String appName> param;' #txt
Se0 f27 inParamTable 'out.appName=param.appName;
out.username=param.username;
' #txt
Se0 f27 outParamDecl '<java.util.List<ch.ivy.addon.portalkit.ivydata.exception.PortalIvyDataException> errors,java.util.Map substitutesByApp> result;' #txt
Se0 f27 outParamTable 'result.errors=in.errors;
result.substitutesByApp=in.substitutesByApp;
' #txt
Se0 f27 callSignature findSubstitutesOnApp(String,String) #txt
Se0 f27 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>findSubstitutesOnApp(String,String)</name>
    </language>
</elementInfo>
' #txt
Se0 f27 81 417 30 30 -13 17 #rect
Se0 f27 @|StartSubIcon #fIcon
Se0 f28 processCall 'Functional Processes/ErrorHandler:handle(List<ch.ivy.addon.portalkit.ivydata.exception.PortalIvyDataException>)' #txt
Se0 f28 requestActionDecl '<java.util.List<ch.ivy.addon.portalkit.ivydata.exception.PortalIvyDataException> exceptions> param;' #txt
Se0 f28 requestMappingAction 'param.exceptions=in.errors;
' #txt
Se0 f28 responseActionDecl 'ch.ivyteam.wf.processes.SubstituteServiceData out;
' #txt
Se0 f28 responseMappingAction 'out=in;
' #txt
Se0 f28 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>ErrorHandler</name>
    </language>
</elementInfo>
' #txt
Se0 f28 546 410 112 44 -35 -8 #rect
Se0 f28 @|CallSubIcon #fIcon
Se0 f29 737 417 30 30 0 15 #rect
Se0 f29 @|EndSubIcon #fIcon
Se0 f30 actionTable 'out=in;
' #txt
Se0 f30 actionCode 'import ch.ivy.addon.portalkit.ivydata.dto.IvySubstituteResultDTO;
import ch.ivy.addon.portalkit.ivydata.service.impl.SubstituteService;

IvySubstituteResultDTO dto = SubstituteService.newInstance().findSubstitutesOnApp(in.username, in.appName);
out.substitutesByApp = dto.getIvySubstitutesByApp();
out.errors = dto.errors;' #txt
Se0 f30 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Find substitutes</name>
    </language>
</elementInfo>
' #txt
Se0 f30 386 410 112 44 -44 -8 #rect
Se0 f30 @|StepIcon #fIcon
Se0 f32 expr out #txt
Se0 f32 498 432 546 432 #arcP
Se0 f34 expr out #txt
Se0 f34 658 432 737 432 #arcP
Se0 f31 111 432 386 432 #arcP
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
Se0 f10 mainOut f16 tail #connect
Se0 f16 head f14 mainIn #connect
Se0 f14 mainOut f17 tail #connect
Se0 f17 head f13 mainIn #connect
Se0 f12 mainOut f19 tail #connect
Se0 f19 head f10 mainIn #connect
Se0 f13 mainOut f18 tail #connect
Se0 f18 head f15 mainIn #connect
Se0 f21 mainOut f25 tail #connect
Se0 f25 head f22 mainIn #connect
Se0 f22 mainOut f26 tail #connect
Se0 f26 head f23 mainIn #connect
Se0 f20 mainOut f24 tail #connect
Se0 f24 head f21 mainIn #connect
Se0 f30 mainOut f32 tail #connect
Se0 f32 head f28 mainIn #connect
Se0 f28 mainOut f34 tail #connect
Se0 f34 head f29 mainIn #connect
Se0 f27 mainOut f31 tail #connect
Se0 f31 head f30 mainIn #connect
