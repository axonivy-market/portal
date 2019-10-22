[Ivy]
16250B8F9D64A11E 7.5.0 #module
>Proto >Proto Collection #zClass
Il0 InitializeTaskDataModel Big #zClass
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
Il0 @InfoButton f16 '' #zField
Il0 @AnnotationArc f5 '' #zField
Il0 @PushWFArc f2 '' #zField
Il0 @PushWFArc f4 '' #zField
Il0 @GridStep f3 '' #zField
>Proto Il0 Il0 InitializeTaskDataModel #zField
Il0 f0 inParamDecl '<> param;' #txt
Il0 f0 outParamDecl '<ch.ivy.addon.portalkit.datamodel.TaskLazyDataModel dataModel> result;' #txt
Il0 f0 outParamTable 'result.dataModel=in.dataModel;
' #txt
Il0 f0 callSignature call() #txt
Il0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>call()</name>
        <nameStyle>6,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Il0 f0 81 49 30 30 -13 17 #rect
Il0 f0 @|StartSubIcon #fIcon
Il0 f1 337 49 30 30 0 15 #rect
Il0 f1 @|EndSubIcon #fIcon
Il0 f16 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>NOTE: IF THERE IS A CUSTOMIZED DATA MODEL, PLEASE REPLACE THE SCRIPT

in.dataModel = new TaskLazyDataModel();
BY
in.dataModel = new &lt;CUSTOMIZED_DATA_MODEL&gt;();</name>
        <nameStyle>158
</nameStyle>
    </language>
</elementInfo>
' #txt
Il0 f16 160 164 480 92 -230 -40 #rect
Il0 f16 @|IBIcon #fIcon
Il0 f5 400 164 224 86 #arcP
Il0 f2 expr out #txt
Il0 f2 288 64 337 64 #arcP
Il0 f4 expr out #txt
Il0 f4 111 64 160 64 #arcP
Il0 f3 actionTable 'out=in;
' #txt
Il0 f3 actionCode 'import ch.ivyteam.ivy.project.portal.examples.component.customize.CustomizedTaskLazyDataModel;

in.dataModel = new CustomizedTaskLazyDataModel();' #txt
Il0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Initialize task
lazy data model</name>
        <nameStyle>31
</nameStyle>
    </language>
</elementInfo>
' #txt
Il0 f3 160 42 128 44 -41 -16 #rect
Il0 f3 @|StepIcon #fIcon
>Proto Il0 .type _ch.ivyteam.ivy.project.portal.examples.InitializeTaskDataModelOverrideData #txt
>Proto Il0 .processKind CALLABLE_SUB #txt
>Proto Il0 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language/>
</elementInfo>
' #txt
>Proto Il0 0 0 32 24 18 0 #rect
>Proto Il0 @|BIcon #fIcon
Il0 f0 mainOut f4 tail #connect
Il0 f4 head f3 mainIn #connect
Il0 f3 mainOut f2 tail #connect
Il0 f2 head f1 mainIn #connect
Il0 f16 ao f5 tail #connect
Il0 f5 head f3 @CG|ai #connect
