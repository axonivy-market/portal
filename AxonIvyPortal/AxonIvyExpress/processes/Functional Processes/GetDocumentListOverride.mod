[Ivy]
16B25F2844868AE2 9.3.1 #module
>Proto >Proto Collection #zClass
Gt0 GetDocumentList Big #zClass
Gt0 B #cInfo
Gt0 #process
Gt0 @TextInP .type .type #zField
Gt0 @TextInP .processKind .processKind #zField
Gt0 @AnnotationInP-0n ai ai #zField
Gt0 @MessageFlowInP-0n messageIn messageIn #zField
Gt0 @MessageFlowOutP-0n messageOut messageOut #zField
Gt0 @TextInP .xml .xml #zField
Gt0 @TextInP .responsibility .responsibility #zField
Gt0 @StartSub f0 '' #zField
Gt0 @EndSub f1 '' #zField
Gt0 @GridStep f3 '' #zField
Gt0 @PushWFArc f4 '' #zField
Gt0 @PushWFArc f2 '' #zField
>Proto Gt0 Gt0 GetDocumentList #zField
Gt0 f0 inParamDecl '<ch.ivyteam.ivy.workflow.ICase businessCase> param;' #txt
Gt0 f0 inParamTable 'out.businessCase=param.businessCase;
' #txt
Gt0 f0 outParamDecl '<java.util.List<ch.ivy.addon.portalkit.ivydata.bo.IvyDocument> documents,String message> result;' #txt
Gt0 f0 outParamTable 'result.documents=in.documents;
result.message=in.message;
' #txt
Gt0 f0 callSignature call(ch.ivyteam.ivy.workflow.ICase) #txt
Gt0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>call(ICase)</name>
    </language>
</elementInfo>
' #txt
Gt0 f0 81 49 30 30 -32 24 #rect
Gt0 f1 337 49 30 30 0 15 #rect
Gt0 f3 actionTable 'out=in;
' #txt
Gt0 f3 actionCode 'import ch.ivy.addon.portalkit.util.DocumentFileUtils;

in.documents = DocumentFileUtils.expressDocuments(in.businessCase);

' #txt
Gt0 f3 security system #txt
Gt0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>get document list</name>
    </language>
</elementInfo>
' #txt
Gt0 f3 168 42 112 44 -47 -8 #rect
Gt0 f4 expr out #txt
Gt0 f4 111 64 168 64 #arcP
Gt0 f2 expr out #txt
Gt0 f2 280 64 337 64 #arcP
>Proto Gt0 .type gawfs.GetDocumentListOverrideData #txt
>Proto Gt0 .processKind CALLABLE_SUB #txt
>Proto Gt0 0 0 32 24 18 0 #rect
>Proto Gt0 @|BIcon #fIcon
Gt0 f0 mainOut f4 tail #connect
Gt0 f4 head f3 mainIn #connect
Gt0 f3 mainOut f2 tail #connect
Gt0 f2 head f1 mainIn #connect
