[Ivy]
179D696BF6492C34 9.3.1 #module
>Proto >Proto Collection #zClass
Gd0 GetGroupId Big #zClass
Gd0 B #cInfo
Gd0 #process
Gd0 @AnnotationInP-0n ai ai #zField
Gd0 @TextInP .type .type #zField
Gd0 @TextInP .processKind .processKind #zField
Gd0 @TextInP .xml .xml #zField
Gd0 @TextInP .responsibility .responsibility #zField
Gd0 @StartSub f0 '' #zField
Gd0 @EndSub f1 '' #zField
Gd0 @GridStep f3 '' #zField
Gd0 @PushWFArc f4 '' #zField
Gd0 @PushWFArc f2 '' #zField
>Proto Gd0 Gd0 GetGroupId #zField
Gd0 f0 inParamDecl '<> param;' #txt
Gd0 f0 outParamDecl '<String groupId> result;' #txt
Gd0 f0 outParamTable 'result.groupId=in.groupId;
' #txt
Gd0 f0 callSignature getGroupId() #txt
Gd0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>getGroupId()</name>
    </language>
</elementInfo>
' #txt
Gd0 f0 81 49 30 30 -13 17 #rect
Gd0 f1 401 49 30 30 0 15 #rect
Gd0 f3 actionTable 'out=in;
' #txt
Gd0 f3 actionCode 'import ch.ivyteam.ivy.project.IvyProjectNavigationUtil;
import ch.ivyteam.ivy.project.IIvyProject;
import ch.ivyteam.ivy.workflow.IWorkflowProcessModelVersion;

IIvyProject ivyProject = IvyProjectNavigationUtil.getIvyProject(ivy.request.getProcessModelVersion());

if (ivyProject.getLibrary() != null && ivyProject.getLibrary().getId() != null){
	in.groupId = ivyProject.getLibrary().getId().groupId();
}
' #txt
Gd0 f3 security system #txt
Gd0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>get group id&#13;
in portal kit</name>
    </language>
</elementInfo>
' #txt
Gd0 f3 232 42 112 44 -32 -16 #rect
Gd0 f4 111 64 232 64 #arcP
Gd0 f2 344 64 401 64 #arcP
>Proto Gd0 .type ch.ivy.add.portalkit.GetGroupIdData #txt
>Proto Gd0 .processKind CALLABLE_SUB #txt
>Proto Gd0 0 0 32 24 18 0 #rect
>Proto Gd0 @|BIcon #fIcon
Gd0 f0 mainOut f4 tail #connect
Gd0 f4 head f3 mainIn #connect
Gd0 f3 mainOut f2 tail #connect
Gd0 f2 head f1 mainIn #connect
