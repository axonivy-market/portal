[Ivy]
180F9F472CD8C95D 7.5.0 #module
>Proto >Proto Collection #zClass
Ds0 DocumentTableProcess Big #zClass
Ds0 RD #cInfo
Ds0 #process
Ds0 @TextInP .type .type #zField
Ds0 @TextInP .processKind .processKind #zField
Ds0 @AnnotationInP-0n ai ai #zField
Ds0 @MessageFlowInP-0n messageIn messageIn #zField
Ds0 @MessageFlowOutP-0n messageOut messageOut #zField
Ds0 @TextInP .xml .xml #zField
Ds0 @TextInP .responsibility .responsibility #zField
Ds0 @UdInit f0 '' #zField
Ds0 @UdProcessEnd f1 '' #zField
Ds0 @UdMethod f5 '' #zField
Ds0 @CallSub f6 '' #zField
Ds0 @UdProcessEnd f7 '' #zField
Ds0 @PushWFArc f8 '' #zField
Ds0 @PushWFArc f9 '' #zField
Ds0 @UdMethod f10 '' #zField
Ds0 @Alternative f28 '' #zField
Ds0 @GridStep f11 '' #zField
Ds0 @UdProcessEnd f51 '' #zField
Ds0 @CallSub f13 '' #zField
Ds0 @GridStep f46 '' #zField
Ds0 @PushWFArc f29 '' #zField
Ds0 @PushWFArc f2 '' #zField
Ds0 @UdMethod f3 '' #zField
Ds0 @UdProcessEnd f4 '' #zField
Ds0 @PushWFArc f18 '' #zField
Ds0 @UdMethod f19 '' #zField
Ds0 @CallSub f20 '' #zField
Ds0 @GridStep f50 '' #zField
Ds0 @PushWFArc f30 '' #zField
Ds0 @PushWFArc f21 '' #zField
Ds0 @UdProcessEnd f22 '' #zField
Ds0 @PushWFArc f23 '' #zField
Ds0 @UdMethod f24 '' #zField
Ds0 @CallSub f25 '' #zField
Ds0 @UdProcessEnd f26 '' #zField
Ds0 @PushWFArc f27 '' #zField
Ds0 @PushWFArc f31 '' #zField
Ds0 @Alternative f16 '' #zField
Ds0 @PushWFArc f32 '' #zField
Ds0 @PushWFArc f33 '' #zField
Ds0 @GridStep f34 '' #zField
Ds0 @PushWFArc f35 '' #zField
Ds0 @Alternative f36 '' #zField
Ds0 @PushWFArc f37 '' #zField
Ds0 @PushWFArc f12 '' #zField
Ds0 @PushWFArc f38 '' #zField
Ds0 @GridStep f39 '' #zField
Ds0 @PushWFArc f40 '' #zField
Ds0 @PushWFArc f41 '' #zField
Ds0 @UdMethod f42 '' #zField
Ds0 @UdProcessEnd f43 '' #zField
Ds0 @PushWFArc f44 '' #zField
Ds0 @GridStep f45 '' #zField
Ds0 @PushWFArc f47 '' #zField
Ds0 @PushWFArc f14 '' #zField
Ds0 @PushWFArc f48 '' #zField
>Proto Ds0 Ds0 DocumentTableProcess #zField
Ds0 f0 guid 016B0CFC3D7D6069 #txt
Ds0 f0 method start(ch.ivyteam.ivy.workflow.ICase) #txt
Ds0 f0 inParameterDecl '<ch.ivyteam.ivy.workflow.ICase iCase> param;' #txt
Ds0 f0 inParameterMapAction 'out.ivyCase=param.#iCase is initialized ? param.iCase : ivy.case;
' #txt
Ds0 f0 outParameterDecl '<> result;' #txt
Ds0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start(ICase)</name>
    </language>
</elementInfo>
' #txt
Ds0 f0 83 83 26 26 -35 15 #rect
Ds0 f0 @|UdInitIcon #fIcon
Ds0 f1 339 83 26 26 0 12 #rect
Ds0 f1 @|UdProcessEndIcon #fIcon
Ds0 f5 guid 16B1C835D6DF8ED1 #txt
Ds0 f5 method renderTable() #txt
Ds0 f5 inParameterDecl '<> param;' #txt
Ds0 f5 outParameterDecl '<> result;' #txt
Ds0 f5 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>renderTable()</name>
    </language>
</elementInfo>
' #txt
Ds0 f5 83 275 26 26 -25 17 #rect
Ds0 f5 @|UdMethodIcon #fIcon
Ds0 f6 processCall 'Functional Processes/GetDocumentItems:call(ch.ivyteam.ivy.workflow.ICase)' #txt
Ds0 f6 requestActionDecl '<ch.ivyteam.ivy.workflow.ICase businessCase> param;' #txt
Ds0 f6 requestMappingAction 'param.businessCase=in.ivyCase;
' #txt
Ds0 f6 responseActionDecl 'com.axonivy.portal.components.DocumentTable.DocumentTableData out;
' #txt
Ds0 f6 responseMappingAction 'out=in;
out.documents=result.documents;
out.message=result.message;
' #txt
Ds0 f6 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>GetDocumentList</name>
    </language>
</elementInfo>
' #txt
Ds0 f6 168 266 112 44 -48 -8 #rect
Ds0 f6 @|CallSubIcon #fIcon
Ds0 f7 339 275 26 26 0 12 #rect
Ds0 f7 @|UdProcessEndIcon #fIcon
Ds0 f8 expr out #txt
Ds0 f8 109 288 168 288 #arcP
Ds0 f9 expr out #txt
Ds0 f9 280 288 339 288 #arcP
Ds0 f10 guid 16B1C99C07E95EDC #txt
Ds0 f10 method upload(org.primefaces.event.FileUploadEvent) #txt
Ds0 f10 inParameterDecl '<org.primefaces.event.FileUploadEvent event> param;' #txt
Ds0 f10 inParameterMapAction 'out.event=param.event;
' #txt
Ds0 f10 outParameterDecl '<> result;' #txt
Ds0 f10 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>upload(FileUploadEvent)</name>
    </language>
</elementInfo>
' #txt
Ds0 f10 83 371 26 26 -64 21 #rect
Ds0 f10 @|UdMethodIcon #fIcon
Ds0 f28 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Upload successfully?</name>
    </language>
</elementInfo>
' #txt
Ds0 f28 496 368 32 32 -54 -39 #rect
Ds0 f28 @|AlternativeIcon #fIcon
Ds0 f11 actionTable 'out=in;
' #txt
Ds0 f11 actionCode 'import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

FacesContext.getCurrentInstance().addMessage(in.messageComponentId, new FacesMessage(FacesMessage.SEVERITY_ERROR, in.message, null));' #txt
Ds0 f11 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Show error message</name>
    </language>
</elementInfo>
' #txt
Ds0 f11 576 458 128 44 -58 -8 #rect
Ds0 f11 @|StepIcon #fIcon
Ds0 f51 1107 371 26 26 0 12 #rect
Ds0 f51 @|UdProcessEndIcon #fIcon
Ds0 f13 processCall 'Functional Processes/UploadDocumentItem:call(ch.ivyteam.ivy.workflow.ICase,org.primefaces.model.UploadedFile,Boolean,Boolean,String)' #txt
Ds0 f13 requestActionDecl '<ch.ivyteam.ivy.workflow.ICase businessCase,org.primefaces.model.UploadedFile uploadedFile,Boolean enableScriptCheckingForUploadedDocument,Boolean enableVirusScannerForUploadedDocument,String allowedUploadFileTypes> param;' #txt
Ds0 f13 requestMappingAction 'param.businessCase=in.ivyCase;
param.uploadedFile=in.event.getFile();
param.enableScriptCheckingForUploadedDocument=in.enableScriptCheckingForUploadedDocument;
param.enableVirusScannerForUploadedDocument=in.enableVirusScannerForUploadedDocument;
param.allowedUploadFileTypes=in.allowedUploadFileTypes;
' #txt
Ds0 f13 responseActionDecl 'com.axonivy.portal.components.DocumentTable.DocumentTableData out;
' #txt
Ds0 f13 responseMappingAction 'out=in;
out.message=result.message;
out.uploadDocumentCheckStatus=result.status;
out.uploadedDocument=result.#uploadedDocument;
' #txt
Ds0 f13 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>UploadDocument</name>
    </language>
</elementInfo>
' #txt
Ds0 f13 328 362 112 44 -48 -8 #rect
Ds0 f13 @|CallSubIcon #fIcon
Ds0 f46 actionTable 'out=in;
' #txt
Ds0 f46 actionCode 'import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

FacesContext.getCurrentInstance().addMessage(in.messageComponentId, new FacesMessage(in.message, ""));' #txt
Ds0 f46 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Show success&#xD;
message</name>
    </language>
</elementInfo>
' #txt
Ds0 f46 736 362 128 44 -40 -16 #rect
Ds0 f46 @|StepIcon #fIcon
Ds0 f29 expr out #txt
Ds0 f29 440 384 496 384 #arcP
Ds0 f2 expr out #txt
Ds0 f2 109 96 339 96 #arcP
Ds0 f3 guid 16B2047AE0419DBD #txt
Ds0 f3 method setFileLimit(Integer) #txt
Ds0 f3 inParameterDecl '<Integer fileLimit> param;' #txt
Ds0 f3 inParameterMapAction 'out.fileLimit=param.fileLimit;
' #txt
Ds0 f3 outParameterDecl '<> result;' #txt
Ds0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>setFileLimit(Integer)</name>
    </language>
</elementInfo>
' #txt
Ds0 f3 83 531 26 26 -55 15 #rect
Ds0 f3 @|UdMethodIcon #fIcon
Ds0 f4 339 531 26 26 0 12 #rect
Ds0 f4 @|UdProcessEndIcon #fIcon
Ds0 f18 expr out #txt
Ds0 f18 109 544 339 544 #arcP
Ds0 f19 guid 16B213CA436B66B2 #txt
Ds0 f19 method delete() #txt
Ds0 f19 inParameterDecl '<> param;' #txt
Ds0 f19 outParameterDecl '<> result;' #txt
Ds0 f19 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>delete()</name>
    </language>
</elementInfo>
' #txt
Ds0 f19 83 627 26 26 -21 15 #rect
Ds0 f19 @|UdMethodIcon #fIcon
Ds0 f20 processCall 'Functional Processes/DeleteDocumentItem:call(ch.ivyteam.ivy.workflow.ICase,com.axonivy.portal.components.ivydata.bo.IvyDocument)' #txt
Ds0 f20 requestActionDecl '<ch.ivyteam.ivy.workflow.ICase businessCase,com.axonivy.portal.components.ivydata.bo.IvyDocument document> param;' #txt
Ds0 f20 requestMappingAction 'param.businessCase=in.ivyCase;
param.document=in.selectedDocument;
' #txt
Ds0 f20 responseActionDecl 'com.axonivy.portal.components.DocumentTable.DocumentTableData out;
' #txt
Ds0 f20 responseMappingAction 'out=in;
out.message=result.message;
' #txt
Ds0 f20 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Delete Document</name>
    </language>
</elementInfo>
' #txt
Ds0 f20 160 618 112 44 -48 -8 #rect
Ds0 f20 @|CallSubIcon #fIcon
Ds0 f50 actionTable 'out=in;
' #txt
Ds0 f50 actionCode 'import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

FacesContext.getCurrentInstance().addMessage(in.messageComponentId, new FacesMessage(in.message, ""));' #txt
Ds0 f50 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Show delete&#xD;
message</name>
    </language>
</elementInfo>
' #txt
Ds0 f50 320 618 112 44 -34 -16 #rect
Ds0 f50 @|StepIcon #fIcon
Ds0 f30 expr out #txt
Ds0 f30 272 640 320 640 #arcP
Ds0 f21 expr out #txt
Ds0 f21 109 640 160 640 #arcP
Ds0 f22 499 627 26 26 0 12 #rect
Ds0 f22 @|UdProcessEndIcon #fIcon
Ds0 f23 expr out #txt
Ds0 f23 432 640 499 640 #arcP
Ds0 f24 guid 16B214644C2FBAA8 #txt
Ds0 f24 method download(com.axonivy.portal.components.ivydata.bo.IvyDocument) #txt
Ds0 f24 inParameterDecl '<com.axonivy.portal.components.ivydata.bo.IvyDocument selectedDocument> param;' #txt
Ds0 f24 inParameterMapAction 'out.selectedDocument=param.selectedDocument;
' #txt
Ds0 f24 outParameterDecl '<org.primefaces.model.StreamedContent streamedContent> result;' #txt
Ds0 f24 outParameterMapAction 'result.streamedContent=in.streamedContent;
' #txt
Ds0 f24 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>download(IvyDocument)</name>
    </language>
</elementInfo>
' #txt
Ds0 f24 83 723 26 26 -66 15 #rect
Ds0 f24 @|UdMethodIcon #fIcon
Ds0 f25 processCall 'Functional Processes/DownloadDocumentItem:call(ch.ivyteam.ivy.workflow.ICase,com.axonivy.portal.components.ivydata.bo.IvyDocument)' #txt
Ds0 f25 requestActionDecl '<ch.ivyteam.ivy.workflow.ICase bussinessCase,com.axonivy.portal.components.ivydata.bo.IvyDocument document> param;' #txt
Ds0 f25 requestMappingAction 'param.bussinessCase=in.ivyCase;
param.document=in.selectedDocument;
' #txt
Ds0 f25 responseActionDecl 'com.axonivy.portal.components.DocumentTable.DocumentTableData out;
' #txt
Ds0 f25 responseMappingAction 'out=in;
out.streamedContent=result.streamedContent;
' #txt
Ds0 f25 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>DownloadDocument</name>
    </language>
</elementInfo>
' #txt
Ds0 f25 160 714 128 44 -56 -8 #rect
Ds0 f25 @|CallSubIcon #fIcon
Ds0 f26 339 723 26 26 0 12 #rect
Ds0 f26 @|UdProcessEndIcon #fIcon
Ds0 f27 expr out #txt
Ds0 f27 288 736 339 736 #arcP
Ds0 f31 expr out #txt
Ds0 f31 109 736 160 736 #arcP
Ds0 f16 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>not exceed&#xD;
limit of documents?</name>
    </language>
</elementInfo>
' #txt
Ds0 f16 208 368 32 32 -68 -48 #rect
Ds0 f16 @|AlternativeIcon #fIcon
Ds0 f32 expr out #txt
Ds0 f32 109 384 208 384 #arcP
Ds0 f33 expr in #txt
Ds0 f33 outCond 'in.fileLimit <= 0 || in.fileLimit > in.documents.size()' #txt
Ds0 f33 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>YES</name>
    </language>
</elementInfo>
' #txt
Ds0 f33 240 384 328 384 #arcP
Ds0 f33 0 0.5 0 -11 #arcLabel
Ds0 f34 actionTable 'out=in;
' #txt
Ds0 f34 actionCode 'out.message = ivy.cms.co("/Dialogs/com/axonivy/portal/components/DocumentTable/FileLimitMessage") + " (max." + in.fileLimit + ")";' #txt
Ds0 f34 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Set error message to&#xD;
limit of documents error message</name>
    </language>
</elementInfo>
' #txt
Ds0 f34 264 458 208 44 -86 -16 #rect
Ds0 f34 @|StepIcon #fIcon
Ds0 f35 expr in #txt
Ds0 f35 224 400 264 480 #arcP
Ds0 f35 1 224 480 #addKink
Ds0 f35 1 0.5781899068506348 0 0 #arcLabel
Ds0 f36 496 464 32 32 0 16 #rect
Ds0 f36 @|AlternativeIcon #fIcon
Ds0 f37 expr in #txt
Ds0 f37 512 400 512 464 #arcP
Ds0 f37 0 0.6396370554297803 0 0 #arcLabel
Ds0 f12 expr in #txt
Ds0 f12 528 480 576 480 #arcP
Ds0 f12 0 0.18918337471700586 0 0 #arcLabel
Ds0 f38 expr out #txt
Ds0 f38 472 480 496 480 #arcP
Ds0 f39 actionTable 'out=in;
' #txt
Ds0 f39 actionCode 'import com.axonivy.portal.components.document.DocumentCustomField;

if (in.typeColumnRendered) {
	String stringField = DocumentCustomField.TYPE_PREFIX + in.uploadedDocument.getId();
	in.ivyCase.customFields().stringField(stringField).set(in.typeSelection.name());
}' #txt
Ds0 f39 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Store document type&#xD;
if it is not empty</name>
    </language>
</elementInfo>
' #txt
Ds0 f39 568 362 144 44 -52 -16 #rect
Ds0 f39 @|StepIcon #fIcon
Ds0 f40 expr in #txt
Ds0 f40 outCond 'in.uploadDocumentCheckStatus == com.axonivy.portal.components.enums.UploadDocumentCheckStatus.OK' #txt
Ds0 f40 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>YES</name>
        <nameStyle>3
</nameStyle>
    </language>
</elementInfo>
' #txt
Ds0 f40 528 384 568 384 #arcP
Ds0 f40 0 0.2857142857142857 0 -7 #arcLabel
Ds0 f41 expr out #txt
Ds0 f41 712 384 736 384 #arcP
Ds0 f41 0 0.2857142857142857 0 -7 #arcLabel
Ds0 f42 guid 16B4E86E9AEE3309 #txt
Ds0 f42 method renderComponent(String,Boolean,com.axonivy.portal.components.enums.DocumentType,Boolean,Boolean,String) #txt
Ds0 f42 inParameterDecl '<String messageComponentId,Boolean typeColumnRendered,com.axonivy.portal.components.enums.DocumentType selectedType,Boolean enableScriptCheckingForUploadedDocument,Boolean enableVirusScannerForUploadedDocument,String allowedUploadFileTypes> param;' #txt
Ds0 f42 inParameterMapAction 'out.allowedUploadFileTypes=param.allowedUploadFileTypes;
out.enableScriptCheckingForUploadedDocument=param.enableScriptCheckingForUploadedDocument;
out.enableVirusScannerForUploadedDocument=param.enableVirusScannerForUploadedDocument;
out.messageComponentId=param.messageComponentId;
out.typeColumnRendered=param.typeColumnRendered;
out.typeSelection=param.selectedType;
' #txt
Ds0 f42 outParameterDecl '<> result;' #txt
Ds0 f42 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>renderComponent(String,Boolean,DocumentType,Boolean,Boolean,String)</name>
    </language>
</elementInfo>
' #txt
Ds0 f42 83 179 26 26 -91 17 #rect
Ds0 f42 @|UdMethodIcon #fIcon
Ds0 f43 339 179 26 26 0 12 #rect
Ds0 f43 @|UdProcessEndIcon #fIcon
Ds0 f44 expr out #txt
Ds0 f44 109 192 339 192 #arcP
Ds0 f45 actionTable 'out=in;
' #txt
Ds0 f45 actionCode '// Do not store JSF Event in a Html Dialog data field
out.event = null;' #txt
Ds0 f45 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Release JSF Event</name>
    </language>
</elementInfo>
' #txt
Ds0 f45 936 362 112 44 -52 -8 #rect
Ds0 f45 @|StepIcon #fIcon
Ds0 f47 expr out #txt
Ds0 f47 864 384 936 384 #arcP
Ds0 f14 1048 384 1107 384 #arcP
Ds0 f48 expr out #txt
Ds0 f48 704 480 936 384 #arcP
>Proto Ds0 .type com.axonivy.portal.components.DocumentTable.DocumentTableData #txt
>Proto Ds0 .processKind HTML_DIALOG #txt
>Proto Ds0 -8 -8 16 16 16 26 #rect
>Proto Ds0 '' #fIcon
Ds0 f5 mainOut f8 tail #connect
Ds0 f8 head f6 mainIn #connect
Ds0 f6 mainOut f9 tail #connect
Ds0 f9 head f7 mainIn #connect
Ds0 f13 mainOut f29 tail #connect
Ds0 f29 head f28 in #connect
Ds0 f0 mainOut f2 tail #connect
Ds0 f2 head f1 mainIn #connect
Ds0 f3 mainOut f18 tail #connect
Ds0 f18 head f4 mainIn #connect
Ds0 f20 mainOut f30 tail #connect
Ds0 f30 head f50 mainIn #connect
Ds0 f19 mainOut f21 tail #connect
Ds0 f21 head f20 mainIn #connect
Ds0 f50 mainOut f23 tail #connect
Ds0 f23 head f22 mainIn #connect
Ds0 f25 mainOut f27 tail #connect
Ds0 f27 head f26 mainIn #connect
Ds0 f24 mainOut f31 tail #connect
Ds0 f31 head f25 mainIn #connect
Ds0 f10 mainOut f32 tail #connect
Ds0 f32 head f16 in #connect
Ds0 f16 out f33 tail #connect
Ds0 f33 head f13 mainIn #connect
Ds0 f16 out f35 tail #connect
Ds0 f35 head f34 mainIn #connect
Ds0 f37 head f36 in #connect
Ds0 f36 out f12 tail #connect
Ds0 f12 head f11 mainIn #connect
Ds0 f34 mainOut f38 tail #connect
Ds0 f38 head f36 in #connect
Ds0 f28 out f40 tail #connect
Ds0 f40 head f39 mainIn #connect
Ds0 f28 out f37 tail #connect
Ds0 f39 mainOut f41 tail #connect
Ds0 f41 head f46 mainIn #connect
Ds0 f42 mainOut f44 tail #connect
Ds0 f44 head f43 mainIn #connect
Ds0 f46 mainOut f47 tail #connect
Ds0 f47 head f45 mainIn #connect
Ds0 f45 mainOut f14 tail #connect
Ds0 f14 head f51 mainIn #connect
Ds0 f11 mainOut f48 tail #connect
Ds0 f48 head f45 mainIn #connect
