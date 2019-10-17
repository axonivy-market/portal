[Ivy]
153361016FE4213A 7.5.0 #module
>Proto >Proto Collection #zClass
Cs0 CaseItemDocumentProcess Big #zClass
Cs0 RD #cInfo
Cs0 #process
Cs0 @TextInP .type .type #zField
Cs0 @TextInP .processKind .processKind #zField
Cs0 @AnnotationInP-0n ai ai #zField
Cs0 @MessageFlowInP-0n messageIn messageIn #zField
Cs0 @MessageFlowOutP-0n messageOut messageOut #zField
Cs0 @TextInP .xml .xml #zField
Cs0 @TextInP .responsibility .responsibility #zField
Cs0 @GridStep f64 '' #zField
Cs0 @UdInit f0 '' #zField
Cs0 @PushWFArc f1 '' #zField
Cs0 @UdProcessEnd f2 '' #zField
Cs0 @PushWFArc f62 '' #zField
Cs0 @UdMethod f63 '' #zField
Cs0 @UdProcessEnd f69 '' #zField
Cs0 @UdMethod f7 '' #zField
Cs0 @GridStep f9 '' #zField
Cs0 @UdProcessEnd f15 '' #zField
Cs0 @PushWFArc f17 '' #zField
Cs0 @UdProcessEnd f51 '' #zField
Cs0 @UdMethod f8 '' #zField
Cs0 @UdProcessEnd f24 '' #zField
Cs0 @UdMethod f3 '' #zField
Cs0 @GridStep f12 '' #zField
Cs0 @PushWFArc f18 '' #zField
Cs0 @UdProcessEnd f21 '' #zField
Cs0 @PushWFArc f23 '' #zField
Cs0 @UdMethod f4 '' #zField
Cs0 @UdProcessEnd f10 '' #zField
Cs0 @Alternative f28 '' #zField
Cs0 @Alternative f36 '' #zField
Cs0 @PushWFArc f11 '' #zField
Cs0 @PushWFArc f42 '' #zField
Cs0 @GridStep f46 '' #zField
Cs0 @PushWFArc f49 '' #zField
Cs0 @GridStep f50 '' #zField
Cs0 @CallSub f53 '' #zField
Cs0 @PushWFArc f26 '' #zField
Cs0 @PushWFArc f27 '' #zField
Cs0 @CallSub f31 '' #zField
Cs0 @PushWFArc f56 '' #zField
Cs0 @PushWFArc f16 '' #zField
Cs0 @CallSub f14 '' #zField
Cs0 @PushWFArc f32 '' #zField
Cs0 @PushWFArc f34 '' #zField
Cs0 @CallSub f13 '' #zField
Cs0 @PushWFArc f19 '' #zField
Cs0 @PushWFArc f29 '' #zField
Cs0 @CallSub f20 '' #zField
Cs0 @PushWFArc f22 '' #zField
Cs0 @PushWFArc f25 '' #zField
Cs0 @CallSub f5 '' #zField
Cs0 @PushWFArc f6 '' #zField
Cs0 @PushWFArc f30 '' #zField
>Proto Cs0 Cs0 CaseItemDocumentProcess #zField
Cs0 f64 actionTable 'out=in;
' #txt
Cs0 f64 actionCode 'import ch.ivy.addon.portalkit.enums.GlobalVariable;
import ch.ivy.addon.portalkit.service.GlobalSettingService;

GlobalSettingService globalSettingService = new GlobalSettingService();
String isHideUploadDocumentForDoneCase =
        globalSettingService.findGlobalSettingValue(GlobalVariable.HIDE_UPLOAD_DOCUMENT_FOR_DONE_CASE.toString());
in.isHideUploadDocumentForDoneCase = Boolean.parseBoolean(isHideUploadDocumentForDoneCase);' #txt
Cs0 f64 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Init hide&#xD;
add document</name>
    </language>
</elementInfo>
' #txt
Cs0 f64 176 74 112 44 -39 -16 #rect
Cs0 f64 @|StepIcon #fIcon
Cs0 f0 guid 167E9DEBBFA8A8ED #txt
Cs0 f0 method start() #txt
Cs0 f0 inParameterDecl '<> param;' #txt
Cs0 f0 outParameterDecl '<> result;' #txt
Cs0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start()</name>
    </language>
</elementInfo>
' #txt
Cs0 f0 83 83 26 26 -16 15 #rect
Cs0 f0 @|UdInitIcon #fIcon
Cs0 f1 expr out #txt
Cs0 f1 109 96 176 96 #arcP
Cs0 f2 371 83 26 26 0 12 #rect
Cs0 f2 @|UdProcessEndIcon #fIcon
Cs0 f62 expr out #txt
Cs0 f62 288 96 371 96 #arcP
Cs0 f63 guid 167E9DF0DE4FEF32 #txt
Cs0 f63 method initData(ch.ivyteam.ivy.workflow.ICase) #txt
Cs0 f63 inParameterDecl '<ch.ivyteam.ivy.workflow.ICase iCase> param;' #txt
Cs0 f63 inParameterMapAction 'out.iCase=param.iCase;
' #txt
Cs0 f63 outParameterDecl '<> result;' #txt
Cs0 f63 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>initData(ICase)</name>
    </language>
</elementInfo>
' #txt
Cs0 f63 83 179 26 26 -41 15 #rect
Cs0 f63 @|UdMethodIcon #fIcon
Cs0 f69 371 179 26 26 0 12 #rect
Cs0 f69 @|UdProcessEndIcon #fIcon
Cs0 f7 guid 167E9E558A0D7CF1 #txt
Cs0 f7 method uploadDocument(org.primefaces.event.FileUploadEvent) #txt
Cs0 f7 inParameterDecl '<org.primefaces.event.FileUploadEvent event> param;' #txt
Cs0 f7 inParameterMapAction 'out.documentUploadEvent=param.event;
' #txt
Cs0 f7 outParameterDecl '<> result;' #txt
Cs0 f7 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>uploadDocument(FileUploadEvent)</name>
    </language>
</elementInfo>
' #txt
Cs0 f7 83 275 26 26 -75 18 #rect
Cs0 f7 @|UdMethodIcon #fIcon
Cs0 f9 actionTable 'out=in;
' #txt
Cs0 f9 actionCode 'import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, in.uploadDocumentCheckMessage, null));' #txt
Cs0 f9 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Show error message</name>
    </language>
</elementInfo>
' #txt
Cs0 f9 568 362 128 44 -58 -8 #rect
Cs0 f9 @|StepIcon #fIcon
Cs0 f15 747 371 26 26 0 12 #rect
Cs0 f15 @|UdProcessEndIcon #fIcon
Cs0 f17 expr out #txt
Cs0 f17 696 384 747 384 #arcP
Cs0 f51 939 275 26 26 0 12 #rect
Cs0 f51 @|UdProcessEndIcon #fIcon
Cs0 f8 guid 1680301B2BFFF8D6 #txt
Cs0 f8 method downloadDocument(ch.ivy.addon.portalkit.ivydata.bo.IvyDocument) #txt
Cs0 f8 inParameterDecl '<ch.ivy.addon.portalkit.ivydata.bo.IvyDocument document> param;' #txt
Cs0 f8 inParameterMapAction 'out.document=param.document;
' #txt
Cs0 f8 outParameterDecl '<org.primefaces.model.StreamedContent streamedContent> result;' #txt
Cs0 f8 outParameterMapAction 'result.streamedContent=in.streamedContent;
' #txt
Cs0 f8 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>downloadDocument(IvyDocument)</name>
    </language>
</elementInfo>
' #txt
Cs0 f8 83 435 26 26 -91 17 #rect
Cs0 f8 @|UdMethodIcon #fIcon
Cs0 f24 403 435 26 26 0 12 #rect
Cs0 f24 @|UdProcessEndIcon #fIcon
Cs0 f3 guid 168030614DDB1EA9 #txt
Cs0 f3 method resetDataUploadDialog() #txt
Cs0 f3 inParameterDecl '<> param;' #txt
Cs0 f3 outParameterDecl '<> result;' #txt
Cs0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>resetDataUploadDialog()</name>
    </language>
</elementInfo>
' #txt
Cs0 f3 83 531 26 26 -69 15 #rect
Cs0 f3 @|UdMethodIcon #fIcon
Cs0 f12 actionTable 'out=in;
' #txt
Cs0 f12 actionCode 'import java.util.Iterator;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
Iterator messages = FacesContext.getCurrentInstance().getMessages();

while(messages.hasNext()) {
	messages.next();
	messages.remove();
}
' #txt
Cs0 f12 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Reset messages</name>
    </language>
</elementInfo>
' #txt
Cs0 f12 192 522 112 44 -48 -8 #rect
Cs0 f12 @|StepIcon #fIcon
Cs0 f18 expr out #txt
Cs0 f18 109 544 192 544 #arcP
Cs0 f21 403 531 26 26 0 12 #rect
Cs0 f21 @|UdProcessEndIcon #fIcon
Cs0 f23 expr out #txt
Cs0 f23 304 544 403 544 #arcP
Cs0 f4 guid 168030735CFC1630 #txt
Cs0 f4 method deleteDocument() #txt
Cs0 f4 inParameterDecl '<> param;' #txt
Cs0 f4 outParameterDecl '<> result;' #txt
Cs0 f4 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>deleteDocument()</name>
    </language>
</elementInfo>
' #txt
Cs0 f4 83 627 26 26 -49 15 #rect
Cs0 f4 @|UdMethodIcon #fIcon
Cs0 f10 739 627 26 26 0 12 #rect
Cs0 f10 @|UdProcessEndIcon #fIcon
Cs0 f28 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Upload successfully?</name>
    </language>
</elementInfo>
' #txt
Cs0 f28 472 272 32 32 -54 -39 #rect
Cs0 f28 @|AlternativeIcon #fIcon
Cs0 f36 472 368 32 32 0 16 #rect
Cs0 f36 @|AlternativeIcon #fIcon
Cs0 f11 expr in #txt
Cs0 f11 504 384 568 384 #arcP
Cs0 f11 0 0.3194444444444444 0 -10 #arcLabel
Cs0 f42 expr in #txt
Cs0 f42 488 304 488 368 #arcP
Cs0 f46 actionTable 'out=in;
' #txt
Cs0 f46 actionCode 'import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

FacesContext.getCurrentInstance().addMessage("", new FacesMessage( in.uploadDocumentCheckMessage, "" ));' #txt
Cs0 f46 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Show success&#xD;
message</name>
    </language>
</elementInfo>
' #txt
Cs0 f46 568 266 128 44 -40 -16 #rect
Cs0 f46 @|StepIcon #fIcon
Cs0 f49 expr in #txt
Cs0 f49 outCond 'in.uploadDocumentCheckStatus == ch.ivy.addon.portalkit.enums.UploadDocumentCheckStatus.OK' #txt
Cs0 f49 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>YES</name>
        <nameStyle>3
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f49 504 288 568 288 #arcP
Cs0 f49 0 0.2857142857142857 0 -7 #arcLabel
Cs0 f50 actionTable 'out=in;
' #txt
Cs0 f50 actionCode 'import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

FacesContext.getCurrentInstance().addMessage("", new FacesMessage(in.deleteDocumentMessage, "" ));' #txt
Cs0 f50 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Show delete&#xD;
message</name>
    </language>
</elementInfo>
' #txt
Cs0 f50 392 618 112 44 -34 -16 #rect
Cs0 f50 @|StepIcon #fIcon
Cs0 f53 processCall 'Functional Processes/GetDocumentList:call(ch.ivyteam.ivy.workflow.ICase)' #txt
Cs0 f53 requestActionDecl '<ch.ivyteam.ivy.workflow.ICase businessCase> param;' #txt
Cs0 f53 requestMappingAction 'param.businessCase=in.iCase;
' #txt
Cs0 f53 responseActionDecl 'ch.ivy.addon.portalkit.component.CaseItemDocument.CaseItemDocumentData out;
' #txt
Cs0 f53 responseMappingAction 'out=in;
out.documents=result.documents;
' #txt
Cs0 f53 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Find documents</name>
    </language>
</elementInfo>
' #txt
Cs0 f53 168 170 112 44 -44 -8 #rect
Cs0 f53 @|CallSubIcon #fIcon
Cs0 f26 expr out #txt
Cs0 f26 109 192 168 192 #arcP
Cs0 f27 expr out #txt
Cs0 f27 280 192 371 192 #arcP
Cs0 f31 processCall 'Functional Processes/GetDocumentList:call(ch.ivyteam.ivy.workflow.ICase)' #txt
Cs0 f31 requestActionDecl '<ch.ivyteam.ivy.workflow.ICase businessCase> param;' #txt
Cs0 f31 requestMappingAction 'param.businessCase=in.iCase;
' #txt
Cs0 f31 responseActionDecl 'ch.ivy.addon.portalkit.component.CaseItemDocument.CaseItemDocumentData out;
' #txt
Cs0 f31 responseMappingAction 'out=in;
out.documents=result.documents;
' #txt
Cs0 f31 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Find documents</name>
    </language>
</elementInfo>
' #txt
Cs0 f31 576 618 112 44 -44 -8 #rect
Cs0 f31 @|CallSubIcon #fIcon
Cs0 f56 expr out #txt
Cs0 f56 504 640 576 640 #arcP
Cs0 f16 expr out #txt
Cs0 f16 688 640 739 640 #arcP
Cs0 f14 processCall 'Functional Processes/GetDocumentList:call(ch.ivyteam.ivy.workflow.ICase)' #txt
Cs0 f14 requestActionDecl '<ch.ivyteam.ivy.workflow.ICase businessCase> param;' #txt
Cs0 f14 requestMappingAction 'param.businessCase=in.iCase;
' #txt
Cs0 f14 responseActionDecl 'ch.ivy.addon.portalkit.component.CaseItemDocument.CaseItemDocumentData out;
' #txt
Cs0 f14 responseMappingAction 'out=in;
out.documents=result.documents;
' #txt
Cs0 f14 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Find documents</name>
    </language>
</elementInfo>
' #txt
Cs0 f14 752 266 112 44 -44 -8 #rect
Cs0 f14 @|CallSubIcon #fIcon
Cs0 f32 expr out #txt
Cs0 f32 696 288 752 288 #arcP
Cs0 f34 expr out #txt
Cs0 f34 864 288 939 288 #arcP
Cs0 f13 processCall 'Functional Processes/UploadDocument:call(ch.ivyteam.ivy.workflow.ICase,org.primefaces.model.UploadedFile)' #txt
Cs0 f13 requestActionDecl '<ch.ivyteam.ivy.workflow.ICase businessCase,org.primefaces.model.UploadedFile uploadedFile> param;' #txt
Cs0 f13 requestMappingAction 'param.businessCase=in.iCase;
param.uploadedFile=in.documentUploadEvent.getFile();
' #txt
Cs0 f13 responseActionDecl 'ch.ivy.addon.portalkit.component.CaseItemDocument.CaseItemDocumentData out;
' #txt
Cs0 f13 responseMappingAction 'out=in;
out.uploadDocumentCheckMessage=result.message;
out.uploadDocumentCheckStatus=result.status;
' #txt
Cs0 f13 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>UploadDocument</name>
    </language>
</elementInfo>
' #txt
Cs0 f13 256 266 112 44 -48 -8 #rect
Cs0 f13 @|CallSubIcon #fIcon
Cs0 f19 expr out #txt
Cs0 f19 109 288 256 288 #arcP
Cs0 f29 expr out #txt
Cs0 f29 368 288 472 288 #arcP
Cs0 f20 processCall 'Functional Processes/DownloadDocument:call(ch.ivyteam.ivy.workflow.ICase,ch.ivy.addon.portalkit.ivydata.bo.IvyDocument)' #txt
Cs0 f20 requestActionDecl '<ch.ivyteam.ivy.workflow.ICase bussinessCase,ch.ivy.addon.portalkit.ivydata.bo.IvyDocument document> param;' #txt
Cs0 f20 requestMappingAction 'param.bussinessCase=in.iCase;
param.document=in.document;
' #txt
Cs0 f20 responseActionDecl 'ch.ivy.addon.portalkit.component.CaseItemDocument.CaseItemDocumentData out;
' #txt
Cs0 f20 responseMappingAction 'out=in;
out.streamedContent=result.streamedContent;
' #txt
Cs0 f20 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Download Document</name>
    </language>
</elementInfo>
' #txt
Cs0 f20 192 426 128 44 -58 -8 #rect
Cs0 f20 @|CallSubIcon #fIcon
Cs0 f22 expr out #txt
Cs0 f22 109 448 192 448 #arcP
Cs0 f25 expr out #txt
Cs0 f25 320 448 403 448 #arcP
Cs0 f5 processCall 'Functional Processes/DeleteDocument:call(ch.ivyteam.ivy.workflow.ICase,ch.ivy.addon.portalkit.ivydata.bo.IvyDocument)' #txt
Cs0 f5 requestActionDecl '<ch.ivyteam.ivy.workflow.ICase businessCase,ch.ivy.addon.portalkit.ivydata.bo.IvyDocument document> param;' #txt
Cs0 f5 requestMappingAction 'param.businessCase=in.iCase;
param.document=in.document;
' #txt
Cs0 f5 responseActionDecl 'ch.ivy.addon.portalkit.component.CaseItemDocument.CaseItemDocumentData out;
' #txt
Cs0 f5 responseMappingAction 'out=in;
out.deleteDocumentMessage=result.message;
' #txt
Cs0 f5 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Delete Document</name>
    </language>
</elementInfo>
' #txt
Cs0 f5 192 618 112 44 -48 -8 #rect
Cs0 f5 @|CallSubIcon #fIcon
Cs0 f6 expr out #txt
Cs0 f6 109 640 192 640 #arcP
Cs0 f30 expr out #txt
Cs0 f30 304 640 392 640 #arcP
>Proto Cs0 .type ch.ivy.addon.portalkit.component.CaseItemDocument.CaseItemDocumentData #txt
>Proto Cs0 .processKind HTML_DIALOG #txt
>Proto Cs0 -8 -8 16 16 16 26 #rect
>Proto Cs0 '' #fIcon
Cs0 f0 mainOut f1 tail #connect
Cs0 f1 head f64 mainIn #connect
Cs0 f64 mainOut f62 tail #connect
Cs0 f62 head f2 mainIn #connect
Cs0 f9 mainOut f17 tail #connect
Cs0 f17 head f15 mainIn #connect
Cs0 f3 mainOut f18 tail #connect
Cs0 f18 head f12 mainIn #connect
Cs0 f12 mainOut f23 tail #connect
Cs0 f23 head f21 mainIn #connect
Cs0 f36 out f11 tail #connect
Cs0 f11 head f9 mainIn #connect
Cs0 f42 head f36 in #connect
Cs0 f28 out f49 tail #connect
Cs0 f49 head f46 mainIn #connect
Cs0 f28 out f42 tail #connect
Cs0 f63 mainOut f26 tail #connect
Cs0 f26 head f53 mainIn #connect
Cs0 f53 mainOut f27 tail #connect
Cs0 f27 head f69 mainIn #connect
Cs0 f50 mainOut f56 tail #connect
Cs0 f56 head f31 mainIn #connect
Cs0 f31 mainOut f16 tail #connect
Cs0 f16 head f10 mainIn #connect
Cs0 f46 mainOut f32 tail #connect
Cs0 f32 head f14 mainIn #connect
Cs0 f14 mainOut f34 tail #connect
Cs0 f34 head f51 mainIn #connect
Cs0 f7 mainOut f19 tail #connect
Cs0 f19 head f13 mainIn #connect
Cs0 f13 mainOut f29 tail #connect
Cs0 f29 head f28 in #connect
Cs0 f8 mainOut f22 tail #connect
Cs0 f22 head f20 mainIn #connect
Cs0 f20 mainOut f25 tail #connect
Cs0 f25 head f24 mainIn #connect
Cs0 f4 mainOut f6 tail #connect
Cs0 f6 head f5 mainIn #connect
Cs0 f5 mainOut f30 tail #connect
Cs0 f30 head f50 mainIn #connect
