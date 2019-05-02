[Ivy]
16A684441A0EF157 3.26 #module
>Proto >Proto Collection #zClass
Dd0 DefaultStatisticCustomField Big #zClass
Dd0 B #cInfo
Dd0 #process
Dd0 @TextInP .resExport .resExport #zField
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
>Proto Dd0 Dd0 DefaultStatisticCustomField #zField
Dd0 f0 inParamDecl '<> param;' #txt
Dd0 f0 outParamDecl '<java.util.List<java.lang.String> customFields> result;
' #txt
Dd0 f0 outParamTable 'result.customFields=in.customFields;
' #txt
Dd0 f0 actionDecl 'ch.ivy.addon.portalkit.DefaultStatisticCustomFieldData out;
' #txt
Dd0 f0 callSignature createDefaultStatisticCustomFields() #txt
Dd0 f0 type ch.ivy.addon.portalkit.DefaultStatisticCustomFieldData #txt
Dd0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>createDefaultStatisticCustomFields()</name>
    </language>
</elementInfo>
' #txt
Dd0 f0 81 49 30 30 0 15 #rect
Dd0 f0 @|StartSubIcon #fIcon
Dd0 f1 type ch.ivy.addon.portalkit.DefaultStatisticCustomFieldData #txt
Dd0 f1 585 49 30 30 0 15 #rect
Dd0 f1 @|EndSubIcon #fIcon
Dd0 f3 actionDecl 'ch.ivy.addon.portalkit.DefaultStatisticCustomFieldData out;
' #txt
Dd0 f3 actionTable 'out=in;
' #txt
Dd0 f3 actionCode 'import java.util.Arrays;

in.customFields = Arrays.asList("CustomVarCharField1", "CustomVarCharField2", "CustomVarCharField3", "CustomVarCharField4", "MyCompany");' #txt
Dd0 f3 type ch.ivy.addon.portalkit.DefaultStatisticCustomFieldData #txt
Dd0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>get custom field name</name>
    </language>
</elementInfo>
' #txt
Dd0 f3 352 42 128 44 -61 -8 #rect
Dd0 f3 @|StepIcon #fIcon
Dd0 f4 expr out #txt
Dd0 f4 111 64 352 64 #arcP
Dd0 f2 expr out #txt
Dd0 f2 480 64 585 64 #arcP
>Proto Dd0 .type ch.ivy.addon.portalkit.DefaultStatisticCustomFieldData #txt
>Proto Dd0 .processKind CALLABLE_SUB #txt
>Proto Dd0 0 0 32 24 18 0 #rect
>Proto Dd0 @|BIcon #fIcon
Dd0 f0 mainOut f4 tail #connect
Dd0 f4 head f3 mainIn #connect
Dd0 f3 mainOut f2 tail #connect
Dd0 f2 head f1 mainIn #connect
