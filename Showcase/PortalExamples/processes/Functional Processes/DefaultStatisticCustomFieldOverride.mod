[Ivy]
16A914CEF46EA5B8 7.5.0 #module
>Proto >Proto Collection #zClass
Dd0 DefaultStatisticCustomField Big #zClass
Dd0 B #cInfo
Dd0 #process
Dd0 @TextInP .type .type #zField
Dd0 @TextInP .processKind .processKind #zField
Dd0 @AnnotationInP-0n ai ai #zField
Dd0 @MessageFlowInP-0n messageIn messageIn #zField
Dd0 @MessageFlowOutP-0n messageOut messageOut #zField
Dd0 @TextInP .xml .xml #zField
Dd0 @TextInP .responsibility .responsibility #zField
Dd0 @StartSub f0 '' #zField
Dd0 @EndSub f1 '' #zField
Dd0 @GridStep f3 '' #zField
Dd0 @PushWFArc f4 '' #zField
Dd0 @PushWFArc f2 '' #zField
Dd0 @InfoButton f5 '' #zField
>Proto Dd0 Dd0 DefaultStatisticCustomField #zField
Dd0 f0 inParamDecl '<> param;' #txt
Dd0 f0 outParamDecl '<java.util.List<String> customFields> result;' #txt
Dd0 f0 outParamTable 'result.customFields=in.customFields;
' #txt
Dd0 f0 callSignature createDefaultStatisticCustomFields() #txt
Dd0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>createDefaultStatisticCustomFields()</name>
    </language>
</elementInfo>
' #txt
Dd0 f0 81 49 30 30 34 -10 #rect
Dd0 f0 @|StartSubIcon #fIcon
Dd0 f1 81 305 30 30 0 15 #rect
Dd0 f1 @|EndSubIcon #fIcon
Dd0 f3 actionTable 'out=in;
' #txt
Dd0 f3 actionCode 'import java.util.Arrays;

in.customFields = Arrays.asList("CustomVarCharField1", "CompanyName");' #txt
Dd0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>get custom field name</name>
    </language>
</elementInfo>
' #txt
Dd0 f3 32 170 128 44 -61 -8 #rect
Dd0 f3 @|StepIcon #fIcon
Dd0 f4 expr out #txt
Dd0 f4 96 79 96 170 #arcP
Dd0 f2 expr out #txt
Dd0 f2 96 214 96 305 #arcP
Dd0 f5 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>// FOLLOW THIS INSTRUCTION TO CREATE CUSTOM STRING FIELD&#xD;
// CREATE LIST OF YOUR CUSTOM STRING FIELD NAME&#xD;
// DEFAULT IS "CustomVarCharField1", "CustomVarCharField2", "CustomVarCharField3", "CustomVarCharField4", "CustomVarCharField5"&#xD;
// YOU CAN CREATE YOUR OWN CUSTOM STRING FIELD NAME&#xD;
import java.util.Arrays;&#xD;
in.customFields = Arrays.asList("MyCustomStringField1", "MyCustomStringField2");</name>
    </language>
</elementInfo>
' #txt
Dd0 f5 320 130 768 108 -379 -48 #rect
Dd0 f5 @|IBIcon #fIcon
>Proto Dd0 .type _ch.ivyteam.ivy.project.portal.examples.DefaultStatisticCustomFieldOverrideData #txt
>Proto Dd0 .processKind CALLABLE_SUB #txt
>Proto Dd0 0 0 32 24 18 0 #rect
>Proto Dd0 @|BIcon #fIcon
Dd0 f0 mainOut f4 tail #connect
Dd0 f4 head f3 mainIn #connect
Dd0 f3 mainOut f2 tail #connect
Dd0 f2 head f1 mainIn #connect
