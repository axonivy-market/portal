[Ivy]
181611E135B734EB 7.5.0 #module
>Proto >Proto Collection #zClass
Gt0 GetDocumentItems Big #zClass
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
>Proto Gt0 Gt0 GetDocumentItems #zField
Gt0 f0 inParamDecl '<ch.ivyteam.ivy.workflow.ICase businessCase> param;' #txt
Gt0 f0 inParamTable 'out.businessCase=param.businessCase;
' #txt
Gt0 f0 outParamDecl '<java.util.List<com.axonivy.portal.components.ivydata.bo.IvyDocument> documents,String message> result;' #txt
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
Gt0 f3 actionCode 'import com.axonivy.portal.components.examples.enums.ExtendedDocumentType;
import com.axonivy.portal.components.examples.document.CustomizedIvyDocument;
import com.axonivy.portal.components.examples.document.CustomizedIvyDocumentTransformer;
import com.axonivy.portal.components.document.DocumentCustomField;
import com.axonivy.portal.components.service.CaseDocumentService;

import org.apache.commons.lang3.StringUtils;
import ch.ivyteam.ivy.workflow.document.IDocument;

if (in.#businessCase is initialized) {
	List<IDocument> iDocuments = CaseDocumentService.newInstance(in.businessCase).getAll();
	CustomizedIvyDocumentTransformer transformer = new CustomizedIvyDocumentTransformer();
	in.documents = transformer.transform(iDocuments);
	for(CustomizedIvyDocument doc : in.documents) {
		doc.setName("Overridden: " + doc.getName());
		String typeStringField = DocumentCustomField.TYPE_PREFIX + doc.id;
		String typeString = in.businessCase.customFields().stringField(typeStringField).getOrNull();
		if (StringUtils.isNotBlank(typeString)) {
			doc.setType(ExtendedDocumentType.valueOf(typeString.toUpperCase()));
		}
		
		String customerStringField = "IvyDocumentCustomer-" + doc.id;
		String customer = in.businessCase.customFields().stringField(customerStringField).getOrNull();
		if (StringUtils.isNotBlank(customer)) {
			doc.setCustomer(customer);
		}
	}
}

' #txt
Gt0 f3 security system #txt
Gt0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>get document items</name>
    </language>
</elementInfo>
' #txt
Gt0 f3 160 42 128 44 -54 -8 #rect
Gt0 f3 @|StepIcon #fIcon
Gt0 f4 expr out #txt
Gt0 f4 111 64 160 64 #arcP
Gt0 f2 expr out #txt
Gt0 f2 288 64 337 64 #arcP
Gt0 f5 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>After get document list from DMS, convert them into List&lt;ch.ivy.addon.portal.component.ivydata.bo.IvyDocument&gt;&#13;
Some mandatory fields when mapping:&#13;
- id&#13;
- name&#13;
- contentType&#13;
</name>
    </language>
</elementInfo>
' #txt
Gt0 f5 32 186 624 108 -305 -48 #rect
Gt0 f5 @|IBIcon #fIcon
Gt0 f6 344 186 90 78 #arcP
>Proto Gt0 .type com.axonivy.portal.components.examples.override.GetDocumentItemsOverrideData #txt
>Proto Gt0 .processKind CALLABLE_SUB #txt
>Proto Gt0 0 0 32 24 18 0 #rect
>Proto Gt0 @|BIcon #fIcon
Gt0 f0 mainOut f4 tail #connect
Gt0 f4 head f3 mainIn #connect
Gt0 f3 mainOut f2 tail #connect
Gt0 f2 head f1 mainIn #connect
Gt0 f5 ao f6 tail #connect
Gt0 f6 head f0 @CG|ai #connect
