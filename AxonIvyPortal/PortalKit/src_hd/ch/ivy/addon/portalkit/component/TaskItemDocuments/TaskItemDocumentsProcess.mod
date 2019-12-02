[Ivy]
1549452C001D8A69 7.5.0 #module
>Proto >Proto Collection #zClass
Ts0 TaskItemDocumentsProcess Big #zClass
Ts0 RD #cInfo
Ts0 #process
Ts0 @TextInP .type .type #zField
Ts0 @TextInP .processKind .processKind #zField
Ts0 @AnnotationInP-0n ai ai #zField
Ts0 @MessageFlowInP-0n messageIn messageIn #zField
Ts0 @MessageFlowOutP-0n messageOut messageOut #zField
Ts0 @TextInP .xml .xml #zField
Ts0 @TextInP .responsibility .responsibility #zField
Ts0 @UdInit f0 '' #zField
Ts0 @UdProcessEnd f1 '' #zField
Ts0 @UdMethod f6 '' #zField
Ts0 @GridStep f64 '' #zField
Ts0 @PushWFArc f17 '' #zField
Ts0 @UdProcessEnd f8 '' #zField
Ts0 @PushWFArc f37 '' #zField
Ts0 @UdMethod f10 '' #zField
Ts0 @UdProcessEnd f18 '' #zField
Ts0 @Alternative f41 '' #zField
Ts0 @Alternative f67 '' #zField
Ts0 @UdProcessEnd f68 '' #zField
Ts0 @GridStep f76 '' #zField
Ts0 @PushWFArc f77 '' #zField
Ts0 @PushWFArc f86 '' #zField
Ts0 @PushWFArc f89 '' #zField
Ts0 @UdProcessEnd f4 '' #zField
Ts0 @UdMethod f5 '' #zField
Ts0 @UdMethod f12 '' #zField
Ts0 @UdProcessEnd f21 '' #zField
Ts0 @GridStep f13 '' #zField
Ts0 @PushWFArc f23 '' #zField
Ts0 @PushWFArc f14 '' #zField
Ts0 @UdMethod f3 '' #zField
Ts0 @UdProcessEnd f22 '' #zField
Ts0 @GridStep f46 '' #zField
Ts0 @PushWFArc f27 '' #zField
Ts0 @GridStep f50 '' #zField
Ts0 @CallSub f30 '' #zField
Ts0 @PushWFArc f2 '' #zField
Ts0 @PushWFArc f31 '' #zField
Ts0 @GridStep f9 '' #zField
Ts0 @CallSub f16 '' #zField
Ts0 @PushWFArc f28 '' #zField
Ts0 @PushWFArc f33 '' #zField
Ts0 @PushWFArc f34 '' #zField
Ts0 @CallSub f32 '' #zField
Ts0 @PushWFArc f35 '' #zField
Ts0 @PushWFArc f36 '' #zField
Ts0 @CallSub f7 '' #zField
Ts0 @PushWFArc f11 '' #zField
Ts0 @PushWFArc f20 '' #zField
Ts0 @CallSub f38 '' #zField
Ts0 @PushWFArc f39 '' #zField
Ts0 @PushWFArc f24 '' #zField
Ts0 @CallSub f19 '' #zField
Ts0 @PushWFArc f25 '' #zField
Ts0 @PushWFArc f26 '' #zField
>Proto Ts0 Ts0 TaskItemDocumentsProcess #zField
Ts0 f0 guid 1682717B951993D3 #txt
Ts0 f0 method start() #txt
Ts0 f0 inParameterDecl '<> param;' #txt
Ts0 f0 outParameterDecl '<> result;' #txt
Ts0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start()</name>
    </language>
</elementInfo>
' #txt
Ts0 f0 83 83 26 26 -16 15 #rect
Ts0 f0 @|UdInitIcon #fIcon
Ts0 f1 371 83 26 26 0 12 #rect
Ts0 f1 @|UdProcessEndIcon #fIcon
Ts0 f6 guid 1682717F2DAE7E7A #txt
Ts0 f6 method initData(ch.ivyteam.ivy.workflow.ITask) #txt
Ts0 f6 inParameterDecl '<ch.ivyteam.ivy.workflow.ITask task> param;' #txt
Ts0 f6 inParameterMapAction 'out.task=param.task;
' #txt
Ts0 f6 outParameterDecl '<> result;' #txt
Ts0 f6 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>initData(ITask)</name>
    </language>
</elementInfo>
' #txt
Ts0 f6 83 179 26 26 -40 15 #rect
Ts0 f6 @|UdMethodIcon #fIcon
Ts0 f64 actionTable 'out=in;
' #txt
Ts0 f64 actionCode 'import ch.ivy.addon.portalkit.enums.GlobalVariable;
import ch.ivy.addon.portalkit.service.GlobalSettingService;

GlobalSettingService globalSettingService = new GlobalSettingService();
String isHideUploadDocumentForDoneCaseString =
        globalSettingService.findGlobalSettingValue(GlobalVariable.HIDE_UPLOAD_DOCUMENT_FOR_DONE_CASE.toString());
boolean isHideUploadDocumentForDoneCase = Boolean.parseBoolean(isHideUploadDocumentForDoneCaseString);
in.isShowUploadDocumentButton = !isHideUploadDocumentForDoneCase;' #txt
Ts0 f64 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Init hide&#xD;
add document</name>
    </language>
</elementInfo>
' #txt
Ts0 f64 544 170 112 44 -39 -16 #rect
Ts0 f64 @|StepIcon #fIcon
Ts0 f17 expr out #txt
Ts0 f17 109 96 371 96 #arcP
Ts0 f8 707 179 26 26 0 12 #rect
Ts0 f8 @|UdProcessEndIcon #fIcon
Ts0 f37 expr out #txt
Ts0 f37 656 192 707 192 #arcP
Ts0 f10 guid 16827249E7B1FA94 #txt
Ts0 f10 method uploadDocument(org.primefaces.event.FileUploadEvent) #txt
Ts0 f10 inParameterDecl '<org.primefaces.event.FileUploadEvent uploadEvent> param;' #txt
Ts0 f10 inParameterMapAction 'out.documentUploadEvent=param.uploadEvent;
' #txt
Ts0 f10 outParameterDecl '<> result;' #txt
Ts0 f10 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>uploadDocument(FileUploadEvent)</name>
    </language>
</elementInfo>
' #txt
Ts0 f10 83 275 26 26 -86 17 #rect
Ts0 f10 @|UdMethodIcon #fIcon
Ts0 f18 651 371 26 26 0 12 #rect
Ts0 f18 @|UdProcessEndIcon #fIcon
Ts0 f41 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Upload successfully?</name>
    </language>
</elementInfo>
' #txt
Ts0 f41 392 272 32 32 -54 -39 #rect
Ts0 f41 @|AlternativeIcon #fIcon
Ts0 f67 392 368 32 32 0 16 #rect
Ts0 f67 @|AlternativeIcon #fIcon
Ts0 f68 859 275 26 26 0 12 #rect
Ts0 f68 @|UdProcessEndIcon #fIcon
Ts0 f76 actionTable 'out=in;
' #txt
Ts0 f76 actionCode 'import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, in.uploadDocumentCheckMessage, null));' #txt
Ts0 f76 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Show error message</name>
    </language>
</elementInfo>
' #txt
Ts0 f76 488 362 128 44 -58 -8 #rect
Ts0 f76 @|StepIcon #fIcon
Ts0 f77 expr in #txt
Ts0 f77 424 384 488 384 #arcP
Ts0 f77 0 0.3194444444444444 0 -10 #arcLabel
Ts0 f86 expr in #txt
Ts0 f86 408 304 408 368 #arcP
Ts0 f89 expr out #txt
Ts0 f89 616 384 651 384 #arcP
Ts0 f4 403 435 26 26 0 12 #rect
Ts0 f4 @|UdProcessEndIcon #fIcon
Ts0 f5 guid 16827286AC6AA280 #txt
Ts0 f5 method downloadDocument(ch.ivy.addon.portalkit.ivydata.bo.IvyDocument) #txt
Ts0 f5 inParameterDecl '<ch.ivy.addon.portalkit.ivydata.bo.IvyDocument document> param;' #txt
Ts0 f5 inParameterMapAction 'out.document=param.document;
' #txt
Ts0 f5 outParameterDecl '<org.primefaces.model.StreamedContent streamedContent> result;' #txt
Ts0 f5 outParameterMapAction 'result.streamedContent=in.streamedContent;
' #txt
Ts0 f5 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>downloadDocument(IvyDocument)</name>
    </language>
</elementInfo>
' #txt
Ts0 f5 83 435 26 26 -89 15 #rect
Ts0 f5 @|UdMethodIcon #fIcon
Ts0 f12 guid 168272AA0A252382 #txt
Ts0 f12 method resetMessageUploadDocument() #txt
Ts0 f12 inParameterDecl '<> param;' #txt
Ts0 f12 outParameterDecl '<> result;' #txt
Ts0 f12 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>resetMessageUploadDocument()</name>
    </language>
</elementInfo>
' #txt
Ts0 f12 83 531 26 26 -79 21 #rect
Ts0 f12 @|UdMethodIcon #fIcon
Ts0 f21 403 531 26 26 0 12 #rect
Ts0 f21 @|UdProcessEndIcon #fIcon
Ts0 f13 actionTable 'out=in;
' #txt
Ts0 f13 actionCode 'import java.util.Iterator;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
Iterator messages = FacesContext.getCurrentInstance().getMessages();

while(messages.hasNext()) {
	messages.next();
	messages.remove();
}
' #txt
Ts0 f13 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Reset messages</name>
    </language>
</elementInfo>
' #txt
Ts0 f13 192 522 112 44 -48 -8 #rect
Ts0 f13 @|StepIcon #fIcon
Ts0 f23 expr out #txt
Ts0 f23 304 544 403 544 #arcP
Ts0 f14 expr out #txt
Ts0 f14 109 544 192 544 #arcP
Ts0 f3 guid 168272BBCF5B4078 #txt
Ts0 f3 method deleteDocument() #txt
Ts0 f3 inParameterDecl '<> param;' #txt
Ts0 f3 outParameterDecl '<> result;' #txt
Ts0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>deleteDocument()</name>
    </language>
</elementInfo>
' #txt
Ts0 f3 83 627 26 26 -49 15 #rect
Ts0 f3 @|UdMethodIcon #fIcon
Ts0 f22 755 627 26 26 0 12 #rect
Ts0 f22 @|UdProcessEndIcon #fIcon
Ts0 f46 actionTable 'out=in;
' #txt
Ts0 f46 actionCode 'import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

FacesContext.getCurrentInstance().addMessage("", new FacesMessage( in.uploadDocumentCheckMessage, "" ));' #txt
Ts0 f46 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Show success&#xD;
message</name>
    </language>
</elementInfo>
' #txt
Ts0 f46 488 266 128 44 -40 -16 #rect
Ts0 f46 @|StepIcon #fIcon
Ts0 f27 expr in #txt
Ts0 f27 outCond 'in.uploadDocumentCheckStatus == ch.ivy.addon.portalkit.enums.UploadDocumentCheckStatus.OK' #txt
Ts0 f27 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>YES</name>
        <nameStyle>3
</nameStyle>
    </language>
</elementInfo>
' #txt
Ts0 f27 424 288 488 288 #arcP
Ts0 f27 0 0.2857142857142857 -2 -7 #arcLabel
Ts0 f50 actionTable 'out=in;
' #txt
Ts0 f50 actionCode 'import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

FacesContext.getCurrentInstance().addMessage("task-document-global-messages", new FacesMessage(in.deleteDocumentMessage, "" ));' #txt
Ts0 f50 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Show success&#xD;
message</name>
    </language>
</elementInfo>
' #txt
Ts0 f50 384 618 128 44 -40 -16 #rect
Ts0 f50 @|StepIcon #fIcon
Ts0 f30 processCall 'Functional Processes/GetDocumentList:call(ch.ivyteam.ivy.workflow.ICase)' #txt
Ts0 f30 requestActionDecl '<ch.ivyteam.ivy.workflow.ICase businessCase> param;' #txt
Ts0 f30 requestMappingAction 'param.businessCase=in.businessCase;
' #txt
Ts0 f30 responseActionDecl 'ch.ivy.addon.portalkit.component.TaskItemDocuments.TaskItemDocumentsData out;
' #txt
Ts0 f30 responseMappingAction 'out=in;
out.documents=result.documents;
' #txt
Ts0 f30 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Find documents</name>
    </language>
</elementInfo>
' #txt
Ts0 f30 384 170 112 44 -44 -8 #rect
Ts0 f30 @|CallSubIcon #fIcon
Ts0 f2 expr out #txt
Ts0 f2 496 192 544 192 #arcP
Ts0 f31 expr out #txt
Ts0 f31 336 192 384 192 #arcP
Ts0 f9 actionTable 'out=in;
' #txt
Ts0 f9 actionCode 'import ch.ivy.addon.portalkit.service.CaseDocumentService;

out.businessCase = in.task.getCase().getBusinessCase();' #txt
Ts0 f9 security system #txt
Ts0 f9 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Find business case</name>
    </language>
</elementInfo>
' #txt
Ts0 f9 208 170 128 44 -54 -8 #rect
Ts0 f9 @|StepIcon #fIcon
Ts0 f16 processCall 'Functional Processes/GetDocumentList:call(ch.ivyteam.ivy.workflow.ICase)' #txt
Ts0 f16 requestActionDecl '<ch.ivyteam.ivy.workflow.ICase businessCase> param;' #txt
Ts0 f16 requestMappingAction 'param.businessCase=in.businessCase;
' #txt
Ts0 f16 responseActionDecl 'ch.ivy.addon.portalkit.component.TaskItemDocuments.TaskItemDocumentsData out;
' #txt
Ts0 f16 responseMappingAction 'out=in;
out.documents=result.documents;
' #txt
Ts0 f16 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Find documents</name>
    </language>
</elementInfo>
' #txt
Ts0 f16 696 266 112 44 -44 -8 #rect
Ts0 f16 @|CallSubIcon #fIcon
Ts0 f28 expr out #txt
Ts0 f28 616 288 696 288 #arcP
Ts0 f33 expr out #txt
Ts0 f33 808 288 859 288 #arcP
Ts0 f34 expr out #txt
Ts0 f34 109 192 208 192 #arcP
Ts0 f32 processCall 'Functional Processes/UploadDocument:call(ch.ivyteam.ivy.workflow.ICase,org.primefaces.model.UploadedFile)' #txt
Ts0 f32 requestActionDecl '<ch.ivyteam.ivy.workflow.ICase businessCase,org.primefaces.model.UploadedFile uploadedFile> param;' #txt
Ts0 f32 requestMappingAction 'param.businessCase=in.businessCase;
param.uploadedFile=in.documentUploadEvent.getFile();
' #txt
Ts0 f32 responseActionDecl 'ch.ivy.addon.portalkit.component.TaskItemDocuments.TaskItemDocumentsData out;
' #txt
Ts0 f32 responseMappingAction 'out=in;
out.uploadDocumentCheckMessage=result.message;
out.uploadDocumentCheckStatus=result.status;
' #txt
Ts0 f32 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>UploadDocument</name>
    </language>
</elementInfo>
' #txt
Ts0 f32 216 266 112 44 -48 -8 #rect
Ts0 f32 @|CallSubIcon #fIcon
Ts0 f35 expr out #txt
Ts0 f35 109 288 216 288 #arcP
Ts0 f36 expr out #txt
Ts0 f36 328 288 392 288 #arcP
Ts0 f7 processCall 'Functional Processes/DownloadDocument:call(ch.ivyteam.ivy.workflow.ICase,ch.ivy.addon.portalkit.ivydata.bo.IvyDocument)' #txt
Ts0 f7 requestActionDecl '<ch.ivyteam.ivy.workflow.ICase bussinessCase,ch.ivy.addon.portalkit.ivydata.bo.IvyDocument document> param;' #txt
Ts0 f7 requestMappingAction 'param.bussinessCase=in.businessCase;
param.document=in.document;
' #txt
Ts0 f7 responseActionDecl 'ch.ivy.addon.portalkit.component.TaskItemDocuments.TaskItemDocumentsData out;
' #txt
Ts0 f7 responseMappingAction 'out=in;
out.streamedContent=result.streamedContent;
' #txt
Ts0 f7 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Download Document</name>
    </language>
</elementInfo>
' #txt
Ts0 f7 192 426 128 44 -58 -8 #rect
Ts0 f7 @|CallSubIcon #fIcon
Ts0 f11 expr out #txt
Ts0 f11 109 448 192 448 #arcP
Ts0 f20 expr out #txt
Ts0 f20 320 448 403 448 #arcP
Ts0 f38 processCall 'Functional Processes/GetDocumentList:call(ch.ivyteam.ivy.workflow.ICase)' #txt
Ts0 f38 requestActionDecl '<ch.ivyteam.ivy.workflow.ICase businessCase> param;' #txt
Ts0 f38 requestMappingAction 'param.businessCase=in.businessCase;
' #txt
Ts0 f38 responseActionDecl 'ch.ivy.addon.portalkit.component.TaskItemDocuments.TaskItemDocumentsData out;
' #txt
Ts0 f38 responseMappingAction 'out=in;
out.documents=result.documents;
' #txt
Ts0 f38 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Find documents</name>
    </language>
</elementInfo>
' #txt
Ts0 f38 576 618 112 44 -44 -8 #rect
Ts0 f38 @|CallSubIcon #fIcon
Ts0 f39 expr out #txt
Ts0 f39 512 640 576 640 #arcP
Ts0 f24 expr out #txt
Ts0 f24 688 640 755 640 #arcP
Ts0 f19 processCall 'Functional Processes/DeleteDocument:call(ch.ivyteam.ivy.workflow.ICase,ch.ivy.addon.portalkit.ivydata.bo.IvyDocument)' #txt
Ts0 f19 requestActionDecl '<ch.ivyteam.ivy.workflow.ICase businessCase,ch.ivy.addon.portalkit.ivydata.bo.IvyDocument document> param;' #txt
Ts0 f19 requestMappingAction 'param.businessCase=in.businessCase;
param.document=in.document;
' #txt
Ts0 f19 responseActionDecl 'ch.ivy.addon.portalkit.component.TaskItemDocuments.TaskItemDocumentsData out;
' #txt
Ts0 f19 responseMappingAction 'out=in;
out.deleteDocumentMessage=result.message;
' #txt
Ts0 f19 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Delete Document</name>
    </language>
</elementInfo>
' #txt
Ts0 f19 192 618 112 44 -48 -8 #rect
Ts0 f19 @|CallSubIcon #fIcon
Ts0 f25 expr out #txt
Ts0 f25 109 640 192 640 #arcP
Ts0 f26 expr out #txt
Ts0 f26 304 640 384 640 #arcP
>Proto Ts0 .type ch.ivy.addon.portalkit.component.TaskItemDocuments.TaskItemDocumentsData #txt
>Proto Ts0 .processKind HTML_DIALOG #txt
>Proto Ts0 -8 -8 16 16 16 26 #rect
>Proto Ts0 '' #fIcon
Ts0 f0 mainOut f17 tail #connect
Ts0 f17 head f1 mainIn #connect
Ts0 f64 mainOut f37 tail #connect
Ts0 f37 head f8 mainIn #connect
Ts0 f76 mainOut f89 tail #connect
Ts0 f89 head f18 mainIn #connect
Ts0 f67 out f77 tail #connect
Ts0 f77 head f76 mainIn #connect
Ts0 f86 head f67 in #connect
Ts0 f13 mainOut f23 tail #connect
Ts0 f23 head f21 mainIn #connect
Ts0 f12 mainOut f14 tail #connect
Ts0 f14 head f13 mainIn #connect
Ts0 f41 out f27 tail #connect
Ts0 f27 head f46 mainIn #connect
Ts0 f41 out f86 tail #connect
Ts0 f9 mainOut f31 tail #connect
Ts0 f31 head f30 mainIn #connect
Ts0 f30 mainOut f2 tail #connect
Ts0 f2 head f64 mainIn #connect
Ts0 f46 mainOut f28 tail #connect
Ts0 f28 head f16 mainIn #connect
Ts0 f16 mainOut f33 tail #connect
Ts0 f33 head f68 mainIn #connect
Ts0 f6 mainOut f34 tail #connect
Ts0 f34 head f9 mainIn #connect
Ts0 f10 mainOut f35 tail #connect
Ts0 f35 head f32 mainIn #connect
Ts0 f32 mainOut f36 tail #connect
Ts0 f36 head f41 in #connect
Ts0 f5 mainOut f11 tail #connect
Ts0 f11 head f7 mainIn #connect
Ts0 f7 mainOut f20 tail #connect
Ts0 f20 head f4 mainIn #connect
Ts0 f50 mainOut f39 tail #connect
Ts0 f39 head f38 mainIn #connect
Ts0 f38 mainOut f24 tail #connect
Ts0 f24 head f22 mainIn #connect
Ts0 f3 mainOut f25 tail #connect
Ts0 f25 head f19 mainIn #connect
Ts0 f19 mainOut f26 tail #connect
Ts0 f26 head f50 mainIn #connect
