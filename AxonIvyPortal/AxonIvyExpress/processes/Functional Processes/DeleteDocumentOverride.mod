[Ivy]
16B266ED2EA90665 7.5.0 #module
>Proto >Proto Collection #zClass
Dt0 DeleteDocument Big #zClass
Dt0 B #cInfo
Dt0 #process
Dt0 @TextInP .type .type #zField
Dt0 @TextInP .processKind .processKind #zField
Dt0 @AnnotationInP-0n ai ai #zField
Dt0 @MessageFlowInP-0n messageIn messageIn #zField
Dt0 @MessageFlowOutP-0n messageOut messageOut #zField
Dt0 @TextInP .xml .xml #zField
Dt0 @TextInP .responsibility .responsibility #zField
Dt0 @StartSub f0 '' #zField
Dt0 @EndSub f1 '' #zField
Dt0 @GridStep f3 '' #zField
Dt0 @PushWFArc f4 '' #zField
Dt0 @PushWFArc f2 '' #zField
>Proto Dt0 Dt0 DeleteDocument #zField
Dt0 f0 inParamDecl '<ch.ivyteam.ivy.workflow.ICase businessCase,ch.ivy.addon.portalkit.ivydata.bo.IvyDocument document> param;' #txt
Dt0 f0 inParamTable 'out.businessCase=param.businessCase;
out.document=param.document;
' #txt
Dt0 f0 outParamDecl '<String message> result;' #txt
Dt0 f0 outParamTable 'result.message=in.message;
' #txt
Dt0 f0 callSignature call(ch.ivyteam.ivy.workflow.ICase,ch.ivy.addon.portalkit.ivydata.bo.IvyDocument) #txt
Dt0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>call(ICase,IvyDocument)</name>
    </language>
</elementInfo>
' #txt
Dt0 f0 81 49 30 30 -81 26 #rect
Dt0 f0 @|StartSubIcon #fIcon
Dt0 f1 337 49 30 30 0 15 #rect
Dt0 f1 @|EndSubIcon #fIcon
Dt0 f3 actionTable 'out=in;
' #txt
Dt0 f3 actionCode 'import java.util.Arrays;

in.businessCase.documents().delete(Long.valueOf(in.document.id));
in.message = ivy.cms.co("/Dialogs/components/CaseDocument/deleteSucceed");' #txt
Dt0 f3 security system #txt
Dt0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Delete document</name>
    </language>
</elementInfo>
' #txt
Dt0 f3 168 42 112 44 -47 -8 #rect
Dt0 f3 @|StepIcon #fIcon
Dt0 f4 expr out #txt
Dt0 f4 111 64 168 64 #arcP
Dt0 f2 expr out #txt
Dt0 f2 280 64 337 64 #arcP
>Proto Dt0 .type gawfs.DeleteDocumentOverrideData #txt
>Proto Dt0 .processKind CALLABLE_SUB #txt
>Proto Dt0 0 0 32 24 18 0 #rect
>Proto Dt0 @|BIcon #fIcon
Dt0 f0 mainOut f4 tail #connect
Dt0 f4 head f3 mainIn #connect
Dt0 f3 mainOut f2 tail #connect
Dt0 f2 head f1 mainIn #connect
