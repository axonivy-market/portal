[Ivy]
161936E158EBC57F 7.5.0 #module
>Proto >Proto Collection #zClass
Sd0 SetCaseBusinessEntity Big #zClass
Sd0 B #cInfo
Sd0 #process
Sd0 @TextInP .type .type #zField
Sd0 @TextInP .processKind .processKind #zField
Sd0 @AnnotationInP-0n ai ai #zField
Sd0 @MessageFlowInP-0n messageIn messageIn #zField
Sd0 @MessageFlowOutP-0n messageOut messageOut #zField
Sd0 @TextInP .xml .xml #zField
Sd0 @TextInP .responsibility .responsibility #zField
Sd0 @StartSub f0 '' #zField
Sd0 @EndSub f1 '' #zField
Sd0 @GridStep f3 '' #zField
Sd0 @PushWFArc f4 '' #zField
Sd0 @PushWFArc f2 '' #zField
>Proto Sd0 Sd0 SetCaseBusinessEntity #zField
Sd0 f0 inParamDecl '<String businessEntityId> param;' #txt
Sd0 f0 inParamTable 'out.caseBusinessEntity=param.businessEntityId;
' #txt
Sd0 f0 outParamDecl '<> result;' #txt
Sd0 f0 callSignature call(String) #txt
Sd0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>call(String)</name>
    </language>
</elementInfo>
' #txt
Sd0 f0 81 49 30 30 -29 17 #rect
Sd0 f0 @|StartSubIcon #fIcon
Sd0 f1 417 49 30 30 0 15 #rect
Sd0 f1 @|EndSubIcon #fIcon
Sd0 f3 actionTable 'out=in;
' #txt
Sd0 f3 actionCode 'import com.axonivy.portal.component.enums.AdditionalProperty;
import ch.ivyteam.ivy.workflow.ICase;

ivy.case.getBusinessCase().customFields().textField(AdditionalProperty.CASE_BUSINESS_ENTITY_PROPERTY.toString()).set(in.caseBusinessEntity);' #txt
Sd0 f3 security system #txt
Sd0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Set case business entity to case</name>
    </language>
</elementInfo>
' #txt
Sd0 f3 168 42 192 44 -88 -8 #rect
Sd0 f3 @|StepIcon #fIcon
Sd0 f4 expr out #txt
Sd0 f4 111 64 168 64 #arcP
Sd0 f2 expr out #txt
Sd0 f2 360 64 417 64 #arcP
>Proto Sd0 .type com.axonivy.portal.component.SetCaseBusinessEntityData #txt
>Proto Sd0 .processKind CALLABLE_SUB #txt
>Proto Sd0 0 0 32 24 18 0 #rect
>Proto Sd0 @|BIcon #fIcon
Sd0 f0 mainOut f4 tail #connect
Sd0 f4 head f3 mainIn #connect
Sd0 f3 mainOut f2 tail #connect
Sd0 f2 head f1 mainIn #connect
