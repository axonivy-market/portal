[Ivy]
16A7C7CEF44FB794 7.5.0 #module
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
Gt0 @InfoButton f5 '' #zField
Gt0 @AnnotationArc f6 '' #zField
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
Gt0 f0 @|StartSubIcon #fIcon
Gt0 f1 337 49 30 30 0 15 #rect
Gt0 f1 @|EndSubIcon #fIcon
Gt0 f3 actionTable 'out=in;
' #txt
Gt0 f3 actionCode 'import ch.ivy.addon.portalkit.document.DocumentCustomField;
import org.apache.commons.lang3.StringUtils;
import ch.ivy.addon.portalkit.enums.BasicDocumentType;
import ch.ivy.addon.portalkit.ivydata.bo.IvyDocument;
import ch.ivy.addon.portalkit.document.IvyDocumentTransformer;
import ch.ivyteam.ivy.workflow.document.IDocument;
import ch.ivyteam.ivy.workflow.ICase;
import ch.ivy.addon.portalkit.constant.PortalConstants;
import ch.ivy.addon.portalkit.service.CaseDocumentService;

if(in.#businessCase is initialized) {
	List<IDocument> iDocuments = CaseDocumentService.newInstance(in.businessCase).getAll();
	IvyDocumentTransformer transformer = new IvyDocumentTransformer();
	in.documents = transformer.transform(iDocuments);
	for (IvyDocument ivyDocument : in.documents) {
		String stringField = DocumentCustomField.TYPE_PREFIX + ivyDocument.id;
		String documentTypeString = in.businessCase.customFields().stringField(stringField).getOrNull();
		if (StringUtils.isNotBlank(documentTypeString)) {
			ivyDocument.setType(BasicDocumentType.valueOf(documentTypeString.toUpperCase()));
		}
	}
}' #txt
Gt0 f3 security system #txt
Gt0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>get document list</name>
    </language>
</elementInfo>
' #txt
Gt0 f3 168 42 112 44 -47 -8 #rect
Gt0 f3 @|StepIcon #fIcon
Gt0 f4 expr out #txt
Gt0 f4 111 64 168 64 #arcP
Gt0 f2 expr out #txt
Gt0 f2 280 64 337 64 #arcP
Gt0 f5 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>After get document list from DMS, convert them into List&lt;ch.ivy.addon.portalkit.ivydata.bo.IvyDocument&gt;&#xD;
Some mandatory fields when mapping:&#xD;
- id&#xD;
- name&#xD;
- contentType&#xD;
</name>
    </language>
</elementInfo>
' #txt
Gt0 f5 56 186 576 108 -278 -48 #rect
Gt0 f5 @|IBIcon #fIcon
Gt0 f6 344 186 109 71 #arcP
>Proto Gt0 .type ch.ivy.add.portalkit.GetDocumentListData #txt
>Proto Gt0 .processKind CALLABLE_SUB #txt
>Proto Gt0 0 0 32 24 18 0 #rect
>Proto Gt0 @|BIcon #fIcon
Gt0 f0 mainOut f4 tail #connect
Gt0 f4 head f3 mainIn #connect
Gt0 f3 mainOut f2 tail #connect
Gt0 f2 head f1 mainIn #connect
Gt0 f5 ao f6 tail #connect
Gt0 f6 head f0 @CG|ai #connect
