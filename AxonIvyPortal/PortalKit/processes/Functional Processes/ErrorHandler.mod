[Ivy]
152AA5CA98B525F2 7.5.0 #module
>Proto >Proto Collection #zClass
Er0 ErrorHandler Big #zClass
Er0 B #cInfo
Er0 #process
Er0 @TextInP .type .type #zField
Er0 @TextInP .processKind .processKind #zField
Er0 @AnnotationInP-0n ai ai #zField
Er0 @MessageFlowInP-0n messageIn messageIn #zField
Er0 @MessageFlowOutP-0n messageOut messageOut #zField
Er0 @TextInP .xml .xml #zField
Er0 @TextInP .responsibility .responsibility #zField
Er0 @StartSub f0 '' #zField
Er0 @EndSub f1 '' #zField
Er0 @GridStep f3 '' #zField
Er0 @PushWFArc f4 '' #zField
Er0 @PushWFArc f2 '' #zField
>Proto Er0 Er0 ErrorHandler #zField
Er0 f0 inParamDecl '<java.util.List<ch.ivy.addon.portalkit.ivydata.exception.PortalIvyDataException> exceptions> param;' #txt
Er0 f0 inParamTable 'out.exceptions=param.exceptions;
' #txt
Er0 f0 outParamDecl '<> result;' #txt
Er0 f0 callSignature handle(List<ch.ivy.addon.portalkit.ivydata.exception.PortalIvyDataException>) #txt
Er0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>handle(List&lt;PortalIvyDataException&gt;)</name>
    </language>
</elementInfo>
' #txt
Er0 f0 51 35 26 26 14 0 #rect
Er0 f0 @|StartSubIcon #fIcon
Er0 f1 51 227 26 26 14 0 #rect
Er0 f1 @|EndSubIcon #fIcon
Er0 f3 actionTable 'out=in;
' #txt
Er0 f3 actionCode 'import ch.ivy.addon.portalkit.ivydata.exception.PortalIvyDataException;
import ch.ivy.addon.portalkit.util.ErrorHandler;

for (PortalIvyDataException exception : in.exceptions) {
	ErrorHandler.addError(org.apache.log4j.Priority.ERROR, exception);
}' #txt
Er0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>add errors to
error handler</name>
        <nameStyle>14,7
5,7
8,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Er0 f3 46 124 36 24 20 -2 #rect
Er0 f3 @|StepIcon #fIcon
Er0 f4 expr out #txt
Er0 f4 64 61 64 124 #arcP
Er0 f2 expr out #txt
Er0 f2 64 148 64 227 #arcP
>Proto Er0 .type ch.ivy.add.portalkit.ErrorHandlerData #txt
>Proto Er0 .processKind CALLABLE_SUB #txt
>Proto Er0 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language/>
</elementInfo>
' #txt
>Proto Er0 0 0 32 24 18 0 #rect
>Proto Er0 @|BIcon #fIcon
Er0 f0 mainOut f4 tail #connect
Er0 f4 head f3 mainIn #connect
Er0 f3 mainOut f2 tail #connect
Er0 f2 head f1 mainIn #connect
