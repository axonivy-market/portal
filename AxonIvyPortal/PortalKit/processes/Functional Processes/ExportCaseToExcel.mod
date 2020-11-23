[Ivy]
17554BEDC7CB0381 9.2.0 #module
>Proto >Proto Collection #zClass
El0 ExportCaseToExcel Big #zClass
El0 B #cInfo
El0 #process
El0 @TextInP .type .type #zField
El0 @TextInP .processKind .processKind #zField
El0 @TextInP .xml .xml #zField
El0 @TextInP .responsibility .responsibility #zField
El0 @StartSub f0 '' #zField
El0 @EndSub f1 '' #zField
El0 @GridStep f81 '' #zField
El0 @PushWFArc f3 '' #zField
El0 @PushWFArc f2 '' #zField
>Proto El0 El0 ExportCaseToExcel #zField
El0 f0 inParamDecl '<java.util.List<ch.ivyteam.ivy.workflow.ICase> collectedCasesForExporting,java.util.List<String> columnsVisibility> param;' #txt
El0 f0 inParamTable 'out.collectedCasesForExporting=param.collectedCasesForExporting;
out.columnsVisibility=param.columnsVisibility;
' #txt
El0 f0 outParamDecl '<org.primefaces.model.StreamedContent exportedFile> result;' #txt
El0 f0 outParamTable 'result.exportedFile=in.exportedFile;
' #txt
El0 f0 callSignature exportToExcel(java.util.List<ch.ivyteam.ivy.workflow.ICase>,java.util.List<String>) #txt
El0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>exportToExcel(List&lt;ICase&gt;,List&lt;String&gt;)</name>
    </language>
</elementInfo>
' #txt
El0 f0 81 49 30 30 -58 17 #rect
El0 f0 @|StartSubIcon #fIcon
El0 f1 433 49 30 30 0 15 #rect
El0 f1 @|EndSubIcon #fIcon
El0 f81 actionTable 'out=in;
' #txt
El0 f81 actionCode 'import ch.ivy.addon.portalkit.exporter.CaseExporter;
CaseExporter exporter = new CaseExporter(in.columnsVisibility);
in.exportedFile = exporter.getStreamedContent(in.collectedCasesForExporting);
' #txt
El0 f81 security system #txt
El0 f81 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>export excel</name>
    </language>
</elementInfo>
' #txt
El0 f81 224 42 112 44 -32 -8 #rect
El0 f81 @|StepIcon #fIcon
El0 f3 111 64 224 64 #arcP
El0 f2 336 64 433 64 #arcP
>Proto El0 .type portalkit.ExportCaseToExcelData #txt
>Proto El0 .processKind CALLABLE_SUB #txt
>Proto El0 0 0 32 24 18 0 #rect
>Proto El0 @|BIcon #fIcon
El0 f0 mainOut f3 tail #connect
El0 f3 head f81 mainIn #connect
El0 f81 mainOut f2 tail #connect
El0 f2 head f1 mainIn #connect
