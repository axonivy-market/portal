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
Se0 @GridStep f86 '' #zField
Se0 @StartSub f12 '' #zField
Se0 @GridStep f14 '' #zField
Se0 @EndSub f15 '' #zField
Se0 @StartSub f20 '' #zField
Se0 @GridStep f21 '' #zField
Se0 @EndSub f23 '' #zField
Se0 @PushWFArc f24 '' #zField
Se0 @PushWFArc f4 '' #zField
Se0 @PushWFArc f2 '' #zField
Se0 @PushWFArc f3 '' #zField
Se0 @PushWFArc f5 '' #zField
Se0 @PushWFArc f6 '' #zField
>Proto Se0 Se0 SubstituteService #zField
Se0 f0 inParamDecl '<String username> param;' #txt
Se0 f0 inParamTable 'out.username=param.username;
' #txt
Se0 f0 outParamDecl '<java.util.List<ch.ivy.addon.portalkit.ivydata.bo.IvySubstitute> substitutes> result;' #txt
Se0 f0 outParamTable 'result.substitutes=in.substitutes;
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
Se0 f1 457 49 30 30 0 15 #rect
Se0 f1 @|EndSubIcon #fIcon
Se0 f86 actionTable 'out=in;
' #txt
Se0 f86 actionCode 'import ch.ivy.addon.portalkit.ivydata.dto.IvySubstituteResultDTO;
import ch.ivy.addon.portalkit.ivydata.service.impl.SubstituteService;

IvySubstituteResultDTO dto = SubstituteService.newInstance().findSubstitutes(in.username);
out.substitutes = dto.getIvySubstitutes();
' #txt
Se0 f86 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Find substitutes</name>
    </language>
</elementInfo>
' #txt
Se0 f86 232 42 112 44 -44 -8 #rect
Se0 f86 @|StepIcon #fIcon
Se0 f12 inParamDecl '<String username> param;' #txt
Se0 f12 inParamTable 'out.username=param.username;
' #txt
Se0 f12 outParamDecl '<java.util.List<ch.ivy.addon.portalkit.ivydata.bo.IvySubstitute> substitutes> result;' #txt
Se0 f12 outParamTable 'result.substitutes=in.substitutes;
' #txt
Se0 f12 callSignature findSubstitutions(String) #txt
Se0 f12 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>findSubstitutions(String)</name>
    </language>
</elementInfo>
' #txt
Se0 f12 81 145 30 30 -61 17 #rect
Se0 f12 @|StartSubIcon #fIcon
Se0 f14 actionTable 'out=in;
' #txt
Se0 f14 actionCode 'import ch.ivy.addon.portalkit.ivydata.dto.IvySubstituteResultDTO;
import ch.ivy.addon.portalkit.ivydata.service.impl.SubstituteService;

IvySubstituteResultDTO dto = SubstituteService.newInstance().findSubstitutions(in.username);
out.substitutes = dto.ivySubstitutes;
' #txt
Se0 f14 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Find substitutions</name>
    </language>
</elementInfo>
' #txt
Se0 f14 232 138 112 44 -49 -8 #rect
Se0 f14 @|StepIcon #fIcon
Se0 f15 457 145 30 30 0 15 #rect
Se0 f15 @|EndSubIcon #fIcon
Se0 f20 inParamDecl '<ch.ivy.addon.portalkit.dto.UserDTO userDTO,java.util.List<ch.ivy.addon.portalkit.ivydata.bo.IvySubstitute> substitutes> param;' #txt
Se0 f20 inParamTable 'out.substitutes=param.substitutes;
out.userDTO=param.userDTO;
' #txt
Se0 f20 outParamDecl '<> result;' #txt
Se0 f20 callSignature saveSubstitutes(ch.ivy.addon.portalkit.dto.UserDTO,java.util.List<ch.ivy.addon.portalkit.ivydata.bo.IvySubstitute>) #txt
Se0 f20 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>saveSubstitutes(UserDTO,List&lt;IvySubstitute&gt;)</name>
    </language>
</elementInfo>
' #txt
Se0 f20 81 241 30 30 -74 20 #rect
Se0 f20 @|StartSubIcon #fIcon
Se0 f21 actionTable 'out=in;
' #txt
Se0 f21 actionCode 'import ch.ivy.addon.portalkit.ivydata.dto.IvySubstituteResultDTO;
import ch.ivy.addon.portalkit.ivydata.service.impl.SubstituteService;

SubstituteService.newInstance().saveSubstitutes(in.userDTO, in.substitutes);
' #txt
Se0 f21 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Save substitutes</name>
    </language>
</elementInfo>
' #txt
Se0 f21 232 234 112 44 -45 -8 #rect
Se0 f21 @|StepIcon #fIcon
Se0 f23 457 241 30 30 0 15 #rect
Se0 f23 @|EndSubIcon #fIcon
Se0 f24 expr out #txt
Se0 f24 111 256 232 256 #arcP
Se0 f4 expr out #txt
Se0 f4 111 64 232 64 #arcP
Se0 f2 111 160 232 160 #arcP
Se0 f3 344 64 457 64 #arcP
Se0 f5 344 160 457 160 #arcP
Se0 f6 344 256 457 256 #arcP
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
    <swimlaneSize>280</swimlaneSize>
    <swimlaneSize>496</swimlaneSize>
    <swimlaneSize>840</swimlaneSize>
    <swimlaneColor>-1</swimlaneColor>
    <swimlaneColor>-1</swimlaneColor>
    <swimlaneColor>-1</swimlaneColor>
</elementInfo>
' #txt
>Proto Se0 0 0 32 24 18 0 #rect
>Proto Se0 @|BIcon #fIcon
Se0 f20 mainOut f24 tail #connect
Se0 f24 head f21 mainIn #connect
Se0 f0 mainOut f4 tail #connect
Se0 f4 head f86 mainIn #connect
Se0 f12 mainOut f2 tail #connect
Se0 f2 head f14 mainIn #connect
Se0 f86 mainOut f3 tail #connect
Se0 f3 head f1 mainIn #connect
Se0 f14 mainOut f5 tail #connect
Se0 f5 head f15 mainIn #connect
Se0 f21 mainOut f6 tail #connect
Se0 f6 head f23 mainIn #connect
