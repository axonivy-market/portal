[Ivy]
16AD35D75A4DFC51 7.5.0 #module
>Proto >Proto Collection #zClass
Dt0 DownloadDocument Big #zClass
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
Dt0 @InfoButton f5 '' #zField
Dt0 @AnnotationArc f6 '' #zField
>Proto Dt0 Dt0 DownloadDocument #zField
Dt0 f0 inParamDecl '<ch.ivyteam.ivy.workflow.ICase bussinessCase,ch.ivy.addon.portalkit.ivydata.bo.IvyDocument document> param;' #txt
Dt0 f0 inParamTable 'out.businessCase=param.bussinessCase;
out.document=param.document;
' #txt
Dt0 f0 outParamDecl '<org.primefaces.model.StreamedContent streamedContent> result;' #txt
Dt0 f0 outParamTable 'result.streamedContent=in.streamedContent;
' #txt
Dt0 f0 callSignature call(ch.ivyteam.ivy.workflow.ICase,ch.ivy.addon.portalkit.ivydata.bo.IvyDocument) #txt
Dt0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>call(ICase,IvyDocument)</name>
    </language>
</elementInfo>
' #txt
Dt0 f0 81 49 30 30 -61 28 #rect
Dt0 f0 @|StartSubIcon #fIcon
Dt0 f1 337 49 30 30 0 15 #rect
Dt0 f1 @|EndSubIcon #fIcon
Dt0 f3 actionTable 'out=in;
' #txt
Dt0 f3 actionCode 'import ch.ivy.addon.portalkit.service.CaseDocumentService;

ivy.log.warn("Call to override process download document");
out.streamedContent = CaseDocumentService.newInstance(in.businessCase).download(in.document);' #txt
Dt0 f3 security system #txt
Dt0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Download document</name>
    </language>
</elementInfo>
' #txt
Dt0 f3 160 42 128 44 -57 -8 #rect
Dt0 f3 @|StepIcon #fIcon
Dt0 f4 expr out #txt
Dt0 f4 111 64 160 64 #arcP
Dt0 f2 expr out #txt
Dt0 f2 288 64 337 64 #arcP
Dt0 f5 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Override this subprocess to download file from DMS</name>
    </language>
</elementInfo>
' #txt
Dt0 f5 120 177 288 30 -141 -8 #rect
Dt0 f5 @|IBIcon #fIcon
Dt0 f6 120 192 98 78 #arcP
>Proto Dt0 .type _ch.ivyteam.ivy.project.portal.examples.DownloadDocumentOverrideData #txt
>Proto Dt0 .processKind CALLABLE_SUB #txt
>Proto Dt0 0 0 32 24 18 0 #rect
>Proto Dt0 @|BIcon #fIcon
Dt0 f0 mainOut f4 tail #connect
Dt0 f4 head f3 mainIn #connect
Dt0 f3 mainOut f2 tail #connect
Dt0 f2 head f1 mainIn #connect
Dt0 f5 ao f6 tail #connect
Dt0 f6 head f0 @CG|ai #connect
