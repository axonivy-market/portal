[Ivy]
15EF0843F1DB39F2 7.5.0 #module
>Proto >Proto Collection #zClass
Lt0 Logout Big #zClass
Lt0 B #cInfo
Lt0 #process
Lt0 @TextInP .type .type #zField
Lt0 @TextInP .processKind .processKind #zField
Lt0 @AnnotationInP-0n ai ai #zField
Lt0 @MessageFlowInP-0n messageIn messageIn #zField
Lt0 @MessageFlowOutP-0n messageOut messageOut #zField
Lt0 @TextInP .xml .xml #zField
Lt0 @TextInP .responsibility .responsibility #zField
Lt0 @StartSub f0 '' #zField
Lt0 @EndSub f1 '' #zField
Lt0 @GridStep f3 '' #zField
Lt0 @PushWFArc f2 '' #zField
Lt0 @PushWFArc f4 '' #zField
>Proto Lt0 Lt0 Logout #zField
Lt0 f0 inParamDecl '<Boolean isTaskReserve,ch.ivyteam.ivy.workflow.ITask task> param;' #txt
Lt0 f0 inParamTable 'out.isTaskReserve=param.isTaskReserve;
out.task=param.task;
' #txt
Lt0 f0 outParamDecl '<> result;' #txt
Lt0 f0 callSignature call(Boolean,ITask) #txt
Lt0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>call(Boolean,ITask)</name>
    </language>
</elementInfo>
' #txt
Lt0 f0 81 49 30 30 -36 17 #rect
Lt0 f0 @|StartSubIcon #fIcon
Lt0 f1 353 49 30 30 0 15 #rect
Lt0 f1 @|EndSubIcon #fIcon
Lt0 f3 actionTable 'out=in;
' #txt
Lt0 f3 actionCode 'import ch.ivy.addon.portalkit.util.SecurityServiceUtils;
import ch.ivy.addon.portalkit.enums.SessionAttribute;
if (in.isTaskReserve) {
	ivy.session.parkTask(in.#task != null ? in.task : ivy.task);
}
ivy.session.logoutSessionUser();


for (SessionAttribute item: SessionAttribute.values()){
	SecurityServiceUtils.removeSessionAttribute(item.toString());
}' #txt
Lt0 f3 security system #txt
Lt0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Logout</name>
        <nameStyle>6
</nameStyle>
    </language>
</elementInfo>
' #txt
Lt0 f3 184 42 112 44 -19 -8 #rect
Lt0 f3 @|StepIcon #fIcon
Lt0 f2 expr out #txt
Lt0 f2 296 64 353 64 #arcP
Lt0 f4 expr out #txt
Lt0 f4 111 64 184 64 #arcP
>Proto Lt0 .type ch.ivy.add.portalkit.LogoutData #txt
>Proto Lt0 .processKind CALLABLE_SUB #txt
>Proto Lt0 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language/>
</elementInfo>
' #txt
>Proto Lt0 0 0 32 24 18 0 #rect
>Proto Lt0 @|BIcon #fIcon
Lt0 f3 mainOut f2 tail #connect
Lt0 f2 head f1 mainIn #connect
Lt0 f0 mainOut f4 tail #connect
Lt0 f4 head f3 mainIn #connect
