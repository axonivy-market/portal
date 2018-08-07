[Ivy]
[>Created: Wed Feb 03 16:48:17 ICT 2016]
152A6870CBF890E3 3.18 #module
>Proto >Proto Collection #zClass
Eg0 ErrorLog Big #zClass
Eg0 B #cInfo
Eg0 #process
Eg0 @TextInP .resExport .resExport #zField
Eg0 @TextInP .type .type #zField
Eg0 @TextInP .processKind .processKind #zField
Eg0 @AnnotationInP-0n ai ai #zField
Eg0 @MessageFlowInP-0n messageIn messageIn #zField
Eg0 @MessageFlowOutP-0n messageOut messageOut #zField
Eg0 @TextInP .xml .xml #zField
Eg0 @TextInP .responsibility .responsibility #zField
Eg0 @StartSub f0 '' #zField
Eg0 @EndSub f1 '' #zField
Eg0 @GridStep f3 '' #zField
Eg0 @PushWFArc f4 '' #zField
Eg0 @PushWFArc f2 '' #zField
>Proto Eg0 Eg0 ErrorLog #zField
Eg0 f0 inParamDecl '<java.util.List<java.lang.Exception> errors> param;' #txt
Eg0 f0 inParamTable 'out.errors=param.errors;
' #txt
Eg0 f0 outParamDecl '<> result;
' #txt
Eg0 f0 actionDecl 'ch.ivy.ws.addon.ErrorLogData out;
' #txt
Eg0 f0 callSignature logError(List<java.lang.Exception>) #txt
Eg0 f0 type ch.ivy.ws.addon.ErrorLogData #txt
Eg0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>logError(List&lt;WSException&gt;)</name>
        <nameStyle>27,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Eg0 f0 51 67 26 26 14 0 #rect
Eg0 f0 @|StartSubIcon #fIcon
Eg0 f1 type ch.ivy.ws.addon.ErrorLogData #txt
Eg0 f1 51 267 26 26 14 0 #rect
Eg0 f1 @|EndSubIcon #fIcon
Eg0 f3 actionDecl 'ch.ivy.ws.addon.ErrorLogData out;
' #txt
Eg0 f3 actionTable 'out=in;
' #txt
Eg0 f3 actionCode 'for (Exception error : in.errors) {
	ivy.log.error("Error", error);
}' #txt
Eg0 f3 type ch.ivy.ws.addon.ErrorLogData #txt
Eg0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>log error</name>
        <nameStyle>9,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Eg0 f3 46 164 36 24 20 -2 #rect
Eg0 f3 @|StepIcon #fIcon
Eg0 f4 expr out #txt
Eg0 f4 64 93 64 164 #arcP
Eg0 f2 expr out #txt
Eg0 f2 64 188 64 267 #arcP
>Proto Eg0 .type ch.ivy.ws.addon.ErrorLogData #txt
>Proto Eg0 .processKind CALLABLE_SUB #txt
>Proto Eg0 0 0 32 24 18 0 #rect
>Proto Eg0 @|BIcon #fIcon
Eg0 f0 mainOut f4 tail #connect
Eg0 f4 head f3 mainIn #connect
Eg0 f3 mainOut f2 tail #connect
Eg0 f2 head f1 mainIn #connect
