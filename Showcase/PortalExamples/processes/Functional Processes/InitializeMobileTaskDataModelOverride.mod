[Ivy]
16740303918BFE61 3.28 #module
>Proto >Proto Collection #zClass
Il0 InitializeMobileTaskDataModel Big #zClass
Il0 B #cInfo
Il0 #process
Il0 @TextInP .type .type #zField
Il0 @TextInP .processKind .processKind #zField
Il0 @AnnotationInP-0n ai ai #zField
Il0 @MessageFlowInP-0n messageIn messageIn #zField
Il0 @MessageFlowOutP-0n messageOut messageOut #zField
Il0 @TextInP .xml .xml #zField
Il0 @TextInP .responsibility .responsibility #zField
Il0 @StartSub f0 '' #zField
Il0 @EndSub f1 '' #zField
Il0 @GridStep f27 '' #zField
Il0 @PushWFArc f3 '' #zField
Il0 @PushWFArc f2 '' #zField
Il0 @InfoButton f16 '' #zField
Il0 @AnnotationArc f4 '' #zField
>Proto Il0 Il0 InitializeMobileTaskDataModel #zField
Il0 f0 inParamDecl '<java.lang.Boolean isMobile> param;' #txt
Il0 f0 inParamTable 'out.isMobile=param.isMobile;
' #txt
Il0 f0 outParamDecl '<ch.ivy.addon.portalkit.datamodel.TaskLazyDataModel dataModel> result;
' #txt
Il0 f0 outParamTable 'result.dataModel=in.dataModel;
' #txt
Il0 f0 callSignature call(Boolean) #txt
Il0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>call(Boolean)</name>
    </language>
</elementInfo>
' #txt
Il0 f0 81 49 30 30 -36 17 #rect
Il0 f0 @|StartSubIcon #fIcon
Il0 f1 569 49 30 30 0 15 #rect
Il0 f1 @|EndSubIcon #fIcon
Il0 f27 actionTable 'out=in;
' #txt
Il0 f27 actionCode 'import ch.ivyteam.ivy.project.portal.examples.component.customize.CustomizedTaskLazyDataModel;
import ch.ivy.addon.portal.generic.view.TaskView;

in.dataModel = new CustomizedTaskLazyDataModel(in.isMobile);


' #txt
Il0 f27 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Initialize data model</name>
        <nameStyle>21
</nameStyle>
    </language>
</elementInfo>
' #txt
Il0 f27 256 42 128 44 -54 -8 #rect
Il0 f27 @|StepIcon #fIcon
Il0 f3 expr out #txt
Il0 f3 111 64 256 64 #arcP
Il0 f2 expr out #txt
Il0 f2 384 64 569 64 #arcP
Il0 f16 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Override TaskLazyDataModel with CustomizedTaskLazyDataModel</name>
        <nameStyle>59,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Il0 f16 248 203 368 30 -181 -8 #rect
Il0 f16 @|IBIcon #fIcon
Il0 f4 432 203 320 86 #arcP
>Proto Il0 .type _ch.ivyteam.ivy.project.portal.examples.InitializeMobileTaskDataModelOverrideData #txt
>Proto Il0 .processKind CALLABLE_SUB #txt
>Proto Il0 0 0 32 24 18 0 #rect
>Proto Il0 @|BIcon #fIcon
Il0 f0 mainOut f3 tail #connect
Il0 f3 head f27 mainIn #connect
Il0 f27 mainOut f2 tail #connect
Il0 f2 head f1 mainIn #connect
Il0 f16 ao f4 tail #connect
Il0 f4 head f27 @CG|ai #connect
