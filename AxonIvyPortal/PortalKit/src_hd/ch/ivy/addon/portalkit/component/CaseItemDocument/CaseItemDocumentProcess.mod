[Ivy]
153361016FE4213A 3.20 #module
>Proto >Proto Collection #zClass
Cs0 CaseItemDocumentProcess Big #zClass
Cs0 RD #cInfo
Cs0 #process
Cs0 @TextInP .ui2RdDataAction .ui2RdDataAction #zField
Cs0 @TextInP .rdData2UIAction .rdData2UIAction #zField
Cs0 @TextInP .resExport .resExport #zField
Cs0 @TextInP .type .type #zField
Cs0 @TextInP .processKind .processKind #zField
Cs0 @AnnotationInP-0n ai ai #zField
Cs0 @MessageFlowInP-0n messageIn messageIn #zField
Cs0 @MessageFlowOutP-0n messageOut messageOut #zField
Cs0 @TextInP .xml .xml #zField
Cs0 @TextInP .responsibility .responsibility #zField
Cs0 @RichDialogInitStart f0 '' #zField
Cs0 @RichDialogProcessEnd f1 '' #zField
Cs0 @RichDialogMethodStart f7 '' #zField
Cs0 @Alternative f35 '' #zField
Cs0 @CallSub f58 '' #zField
Cs0 @Alternative f28 '' #zField
Cs0 @GridStep f30 '' #zField
Cs0 @GridStep f56 '' #zField
Cs0 @RichDialogProcessEnd f11 '' #zField
Cs0 @CallSub f43 '' #zField
Cs0 @PushWFArc f29 '' #zField
Cs0 @PushWFArc f9 '' #zField
Cs0 @PushWFArc f17 '' #zField
Cs0 @PushWFArc f44 '' #zField
Cs0 @RichDialogProcessEnd f20 '' #zField
Cs0 @RichDialogMethodStart f8 '' #zField
Cs0 @GridStep f26 '' #zField
Cs0 @CallSub f24 '' #zField
Cs0 @PushWFArc f27 '' #zField
Cs0 @PushWFArc f25 '' #zField
Cs0 @PushWFArc f22 '' #zField
Cs0 @GridStep f49 '' #zField
Cs0 @RichDialogMethodStart f31 '' #zField
Cs0 @RichDialogProcessEnd f33 '' #zField
Cs0 @CallSub f42 '' #zField
Cs0 @GridStep f47 '' #zField
Cs0 @Alternative f48 '' #zField
Cs0 @PushWFArc f52 '' #zField
Cs0 @PushWFArc f32 '' #zField
Cs0 @PushWFArc f36 '' #zField
Cs0 @PushWFArc f10 '' #zField
Cs0 @PushWFArc f16 '' #zField
Cs0 @PushWFArc f38 '' #zField
Cs0 @RichDialogMethodStart f12 '' #zField
Cs0 @RichDialogProcessEnd f18 '' #zField
Cs0 @PushWFArc f21 '' #zField
Cs0 @RichDialogMethodStart f3 '' #zField
Cs0 @RichDialogProcessEnd f4 '' #zField
Cs0 @GridStep f6 '' #zField
Cs0 @PushWFArc f23 '' #zField
Cs0 @PushWFArc f5 '' #zField
Cs0 @RichDialogMethodStart f34 '' #zField
Cs0 @RichDialogProcessEnd f37 '' #zField
Cs0 @CallSub f80 '' #zField
Cs0 @PushWFArc f39 '' #zField
Cs0 @GridStep f41 '' #zField
Cs0 @PushWFArc f19 '' #zField
Cs0 @GridStep f46 '' #zField
Cs0 @PushWFArc f50 '' #zField
Cs0 @PushWFArc f40 '' #zField
Cs0 @GridStep f51 '' #zField
Cs0 @PushWFArc f53 '' #zField
Cs0 @PushWFArc f15 '' #zField
Cs0 @CallSub f54 '' #zField
Cs0 @PushWFArc f57 '' #zField
Cs0 @PushWFArc f13 '' #zField
Cs0 @PushWFArc f55 '' #zField
Cs0 @Alternative f59 '' #zField
Cs0 @PushWFArc f60 '' #zField
Cs0 @PushWFArc f14 '' #zField
Cs0 @PushWFArc f61 '' #zField
Cs0 @GridStep f62 '' #zField
Cs0 @PushWFArc f63 '' #zField
Cs0 @PushWFArc f45 '' #zField
Cs0 @GridStep f64 '' #zField
Cs0 @PushWFArc f65 '' #zField
Cs0 @PushWFArc f2 '' #zField
>Proto Cs0 Cs0 CaseItemDocumentProcess #zField
Cs0 f0 guid 1533610171B77F44 #txt
Cs0 f0 type ch.ivy.addon.portalkit.component.CaseItemDocument.CaseItemDocumentData #txt
Cs0 f0 method start() #txt
Cs0 f0 disableUIEvents true #txt
Cs0 f0 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<> param = methodEvent.getInputArguments();
' #txt
Cs0 f0 outParameterDecl '<> result;
' #txt
Cs0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start()</name>
    </language>
</elementInfo>
' #txt
Cs0 f0 53 85 22 22 14 0 #rect
Cs0 f0 @|RichDialogInitStartIcon #fIcon
Cs0 f1 type ch.ivy.addon.portalkit.component.CaseItemDocument.CaseItemDocumentData #txt
Cs0 f1 53 277 22 22 14 0 #rect
Cs0 f1 @|RichDialogProcessEndIcon #fIcon
Cs0 f7 guid 153361C617411F60 #txt
Cs0 f7 type ch.ivy.addon.portalkit.component.CaseItemDocument.CaseItemDocumentData #txt
Cs0 f7 method uploadDocument(org.primefaces.event.FileUploadEvent) #txt
Cs0 f7 disableUIEvents false #txt
Cs0 f7 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<org.primefaces.event.FileUploadEvent event> param = methodEvent.getInputArguments();
' #txt
Cs0 f7 inParameterMapAction 'out.documentUploadEvent=param.event;
' #txt
Cs0 f7 outParameterDecl '<> result;
' #txt
Cs0 f7 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>uploadDocument(FileUploadEvent)</name>
        <nameStyle>31,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f7 525 77 22 22 -57 16 #rect
Cs0 f7 @|RichDialogMethodStartIcon #fIcon
Cs0 f35 type ch.ivy.addon.portalkit.component.CaseItemDocument.CaseItemDocumentData #txt
Cs0 f35 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>valid file?</name>
        <nameStyle>11,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f35 522 194 28 28 -34 15 #rect
Cs0 f35 @|AlternativeIcon #fIcon
Cs0 f58 type ch.ivy.addon.portalkit.component.CaseItemDocument.CaseItemDocumentData #txt
Cs0 f58 processCall MultiPortal/CaseService:uploadDocument(String,String,javax.activation.DataHandler,ch.ivy.addon.portalkit.persistence.domain.Server,Long) #txt
Cs0 f58 doCall true #txt
Cs0 f58 requestActionDecl '<java.lang.String userName,java.lang.String documentName,javax.activation.DataHandler documentContent,ch.ivy.addon.portalkit.persistence.domain.Server server,java.lang.Long caseId> param;
' #txt
Cs0 f58 requestMappingAction 'param.userName=ivy.session.getSessionUserName();
param.documentName=in.documentContent.getName();
param.documentContent=in.documentContent;
param.server=in.remoteCase.server;
param.caseId=in.remoteCase.getId();
' #txt
Cs0 f58 responseActionDecl 'ch.ivy.addon.portalkit.component.CaseItemDocument.CaseItemDocumentData out;
' #txt
Cs0 f58 responseMappingAction 'out=in;
out.document=result.document;
' #txt
Cs0 f58 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>CaseService</name>
        <nameStyle>11,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f58 414 572 36 24 -32 17 #rect
Cs0 f58 @|CallSubIcon #fIcon
Cs0 f28 type ch.ivy.addon.portalkit.component.CaseItemDocument.CaseItemDocumentData #txt
Cs0 f28 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>upload successfully?</name>
        <nameStyle>20,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f28 418 658 28 28 -46 16 #rect
Cs0 f28 @|AlternativeIcon #fIcon
Cs0 f30 actionDecl 'ch.ivy.addon.portalkit.component.CaseItemDocument.CaseItemDocumentData out;
' #txt
Cs0 f30 actionTable 'out=in;
' #txt
Cs0 f30 actionCode 'import ch.ivy.ws.addon.WsException;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

for (WsException error : in.errors) {
	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, error.userText, null));
}' #txt
Cs0 f30 type ch.ivy.addon.portalkit.component.CaseItemDocument.CaseItemDocumentData #txt
Cs0 f30 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>show error message</name>
        <nameStyle>18,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f30 414 740 36 24 -50 23 #rect
Cs0 f30 @|StepIcon #fIcon
Cs0 f56 actionDecl 'ch.ivy.addon.portalkit.component.CaseItemDocument.CaseItemDocumentData out;
' #txt
Cs0 f56 actionTable 'out=in;
' #txt
Cs0 f56 actionCode 'import ch.ivy.addon.portalkit.webservice.InputStreamDataSource;
import javax.activation.DataHandler;
import org.primefaces.model.UploadedFile;



UploadedFile uploadedFile = in.documentUploadEvent.getFile();

InputStreamDataSource source = new InputStreamDataSource(uploadedFile.getInputstream(), uploadedFile.getContentType(), uploadedFile.getFileName());
in.documentContent = new DataHandler(source);' #txt
Cs0 f56 type ch.ivy.addon.portalkit.component.CaseItemDocument.CaseItemDocumentData #txt
Cs0 f56 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>get document information</name>
        <nameStyle>24,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f56 414 468 36 24 -69 16 #rect
Cs0 f56 @|StepIcon #fIcon
Cs0 f11 type ch.ivy.addon.portalkit.component.CaseItemDocument.CaseItemDocumentData #txt
Cs0 f11 517 901 22 22 14 0 #rect
Cs0 f11 @|RichDialogProcessEndIcon #fIcon
Cs0 f43 type ch.ivy.addon.portalkit.component.CaseItemDocument.CaseItemDocumentData #txt
Cs0 f43 processCall MultiPortal/CaseService:findDocuments(ch.ivy.addon.portalkit.persistence.domain.Server,Long) #txt
Cs0 f43 doCall true #txt
Cs0 f43 requestActionDecl '<ch.ivy.addon.portalkit.persistence.domain.Server server,java.lang.Long caseId> param;
' #txt
Cs0 f43 requestMappingAction 'param.server=in.remoteCase.server;
param.caseId=in.remoteCase.id;
' #txt
Cs0 f43 responseActionDecl 'ch.ivy.addon.portalkit.component.CaseItemDocument.CaseItemDocumentData out;
' #txt
Cs0 f43 responseMappingAction 'out=in;
out.documents=result.documents;
' #txt
Cs0 f43 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>CaseService
find documents</name>
        <nameStyle>26,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f43 510 660 36 24 -28 14 #rect
Cs0 f43 @|CallSubIcon #fIcon
Cs0 f29 expr out #txt
Cs0 f29 432 596 432 658 #arcP
Cs0 f9 expr in #txt
Cs0 f9 432 686 432 740 #arcP
Cs0 f9 0 0.22905215465364628 0 0 #arcLabel
Cs0 f17 expr out #txt
Cs0 f17 432 764 517 912 #arcP
Cs0 f17 1 432 912 #addKink
Cs0 f17 0 0.7224816120006017 0 0 #arcLabel
Cs0 f44 expr in #txt
Cs0 f44 outCond in.errors.isEmpty() #txt
Cs0 f44 446 672 510 672 #arcP
Cs0 f20 type ch.ivy.addon.portalkit.component.CaseItemDocument.CaseItemDocumentData #txt
Cs0 f20 733 365 22 22 14 0 #rect
Cs0 f20 @|RichDialogProcessEndIcon #fIcon
Cs0 f8 guid 153362370F7BF4BA #txt
Cs0 f8 type ch.ivy.addon.portalkit.component.CaseItemDocument.CaseItemDocumentData #txt
Cs0 f8 method downloadDocument(ch.ivy.ws.addon.IvyDocument,ch.ivy.addon.portalkit.bo.RemoteCase) #txt
Cs0 f8 disableUIEvents false #txt
Cs0 f8 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<ch.ivy.ws.addon.IvyDocument ivyDocument,ch.ivy.addon.portalkit.bo.RemoteCase remoteCase> param = methodEvent.getInputArguments();
' #txt
Cs0 f8 inParameterMapAction 'out.document=param.ivyDocument;
out.remoteCase=param.remoteCase;
' #txt
Cs0 f8 outParameterDecl '<org.primefaces.model.StreamedContent document> result;
' #txt
Cs0 f8 outParameterMapAction 'result.document=in.documentFile;
' #txt
Cs0 f8 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>downloadDocument(IvyDocument,RemoteCase)</name>
        <nameStyle>40,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f8 733 77 22 22 6 10 #rect
Cs0 f8 @|RichDialogMethodStartIcon #fIcon
Cs0 f26 actionDecl 'ch.ivy.addon.portalkit.component.CaseItemDocument.CaseItemDocumentData out;
' #txt
Cs0 f26 actionTable 'out=in;
' #txt
Cs0 f26 actionCode 'import org.primefaces.model.DefaultStreamedContent;
import java.io.InputStream;

InputStream inputStream = in.documentContent.getInputStream();
in.documentFile = new DefaultStreamedContent(inputStream, in.document.getContentType(), in.document.getName());

' #txt
Cs0 f26 type ch.ivy.addon.portalkit.component.CaseItemDocument.CaseItemDocumentData #txt
Cs0 f26 726 284 36 24 20 -2 #rect
Cs0 f26 @|StepIcon #fIcon
Cs0 f24 type ch.ivy.addon.portalkit.component.CaseItemDocument.CaseItemDocumentData #txt
Cs0 f24 processCall MultiPortal/CaseService:downloadDocument(ch.ivy.addon.portalkit.persistence.domain.Server,Long,Long) #txt
Cs0 f24 doCall true #txt
Cs0 f24 requestActionDecl '<ch.ivy.addon.portalkit.persistence.domain.Server server,java.lang.Long documentId,java.lang.Long caseId> param;
' #txt
Cs0 f24 requestMappingAction 'param.server=in.remoteCase.server;
param.documentId=in.document.id;
param.caseId=in.remoteCase.getId();
' #txt
Cs0 f24 responseActionDecl 'ch.ivy.addon.portalkit.component.CaseItemDocument.CaseItemDocumentData out;
' #txt
Cs0 f24 responseMappingAction 'out=in;
out.documentContent=result.documentContent;
out.errors=result.errors;
' #txt
Cs0 f24 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>CaseService</name>
        <nameStyle>11,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f24 726 180 36 24 -30 17 #rect
Cs0 f24 @|CallSubIcon #fIcon
Cs0 f27 expr out #txt
Cs0 f27 744 204 744 284 #arcP
Cs0 f25 expr out #txt
Cs0 f25 744 99 744 180 #arcP
Cs0 f22 expr out #txt
Cs0 f22 744 308 744 365 #arcP
Cs0 f49 actionDecl 'ch.ivy.addon.portalkit.component.CaseItemDocument.CaseItemDocumentData out;
' #txt
Cs0 f49 actionTable 'out=in;
' #txt
Cs0 f49 actionCode 'import ch.ivy.ws.addon.WsException;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

for (WsException error : in.errors) {
	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, error.userText, null));
}' #txt
Cs0 f49 type ch.ivy.addon.portalkit.component.CaseItemDocument.CaseItemDocumentData #txt
Cs0 f49 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>show error message</name>
        <nameStyle>18,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f49 894 260 36 24 -62 13 #rect
Cs0 f49 @|StepIcon #fIcon
Cs0 f31 guid 153362794021D4F5 #txt
Cs0 f31 type ch.ivy.addon.portalkit.component.CaseItemDocument.CaseItemDocumentData #txt
Cs0 f31 method deleteDocument() #txt
Cs0 f31 disableUIEvents false #txt
Cs0 f31 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<> param = methodEvent.getInputArguments();
' #txt
Cs0 f31 outParameterDecl '<> result;
' #txt
Cs0 f31 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>deleteDocument()</name>
    </language>
</elementInfo>
' #txt
Cs0 f31 1013 77 22 22 12 -3 #rect
Cs0 f31 @|RichDialogMethodStartIcon #fIcon
Cs0 f33 type ch.ivy.addon.portalkit.component.CaseItemDocument.CaseItemDocumentData #txt
Cs0 f33 1013 389 22 22 14 0 #rect
Cs0 f33 @|RichDialogProcessEndIcon #fIcon
Cs0 f42 type ch.ivy.addon.portalkit.component.CaseItemDocument.CaseItemDocumentData #txt
Cs0 f42 processCall MultiPortal/CaseService:deleteDocument(String,ch.ivy.addon.portalkit.persistence.domain.Server,Long,Long) #txt
Cs0 f42 doCall true #txt
Cs0 f42 requestActionDecl '<java.lang.String userName,ch.ivy.addon.portalkit.persistence.domain.Server server,java.lang.Long documentId,java.lang.Long caseId> param;
' #txt
Cs0 f42 requestMappingAction 'param.userName=ivy.session.getSessionUserName();
param.server=in.remoteCase.server;
param.documentId=in.document.id;
param.caseId=in.remoteCase.getId();
' #txt
Cs0 f42 responseActionDecl 'ch.ivy.addon.portalkit.component.CaseItemDocument.CaseItemDocumentData out;
' #txt
Cs0 f42 responseMappingAction 'out=in;
out.errors=result.errors;
' #txt
Cs0 f42 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>CaseService
deleteDocument</name>
        <nameStyle>26,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f42 1006 148 36 24 -40 23 #rect
Cs0 f42 @|CallSubIcon #fIcon
Cs0 f47 actionDecl 'ch.ivy.addon.portalkit.component.CaseItemDocument.CaseItemDocumentData out;
' #txt
Cs0 f47 actionTable 'out=in;
' #txt
Cs0 f47 actionCode in.documents.remove(in.document); #txt
Cs0 f47 type ch.ivy.addon.portalkit.component.CaseItemDocument.CaseItemDocumentData #txt
Cs0 f47 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>remove document from list
if no errors</name>
        <nameStyle>26,7
12,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f47 1006 316 36 24 -46 11 #rect
Cs0 f47 @|StepIcon #fIcon
Cs0 f48 type ch.ivy.addon.portalkit.component.CaseItemDocument.CaseItemDocumentData #txt
Cs0 f48 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>remove successfully?</name>
        <nameStyle>20,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f48 1010 258 28 28 -39 -31 #rect
Cs0 f48 @|AlternativeIcon #fIcon
Cs0 f52 expr out #txt
Cs0 f52 1024 172 1024 258 #arcP
Cs0 f32 expr in #txt
Cs0 f32 1010 272 930 272 #arcP
Cs0 f32 0 0.5778198756200936 0 0 #arcLabel
Cs0 f36 expr out #txt
Cs0 f36 1024 340 1024 389 #arcP
Cs0 f10 expr out #txt
Cs0 f10 912 284 1013 400 #arcP
Cs0 f10 1 912 400 #addKink
Cs0 f10 0 0.7661028618229209 0 0 #arcLabel
Cs0 f16 expr in #txt
Cs0 f16 outCond in.errors.isEmpty() #txt
Cs0 f16 1024 286 1024 316 #arcP
Cs0 f38 expr out #txt
Cs0 f38 1024 99 1024 148 #arcP
Cs0 f12 guid 1533A91996E2DD65 #txt
Cs0 f12 type ch.ivy.addon.portalkit.component.CaseItemDocument.CaseItemDocumentData #txt
Cs0 f12 method setDataToDelete(ch.ivy.ws.addon.IvyDocument,ch.ivy.addon.portalkit.bo.RemoteCase) #txt
Cs0 f12 disableUIEvents false #txt
Cs0 f12 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<ch.ivy.ws.addon.IvyDocument document,ch.ivy.addon.portalkit.bo.RemoteCase remoteCase> param = methodEvent.getInputArguments();
' #txt
Cs0 f12 inParameterMapAction 'out.document=param.document;
out.remoteCase=param.remoteCase;
' #txt
Cs0 f12 outParameterDecl '<> result;
' #txt
Cs0 f12 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>deleteData(IvyDocument,RemoteCase)</name>
        <nameStyle>34,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f12 1173 77 22 22 14 0 #rect
Cs0 f12 @|RichDialogMethodStartIcon #fIcon
Cs0 f18 type ch.ivy.addon.portalkit.component.CaseItemDocument.CaseItemDocumentData #txt
Cs0 f18 1173 149 22 22 14 0 #rect
Cs0 f18 @|RichDialogProcessEndIcon #fIcon
Cs0 f21 expr out #txt
Cs0 f21 1184 99 1184 149 #arcP
Cs0 f3 guid 1534FDA4FDC9B40C #txt
Cs0 f3 type ch.ivy.addon.portalkit.component.CaseItemDocument.CaseItemDocumentData #txt
Cs0 f3 method resetDataUploadDialog() #txt
Cs0 f3 disableUIEvents false #txt
Cs0 f3 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<> param = methodEvent.getInputArguments();
' #txt
Cs0 f3 outParameterDecl '<> result;
' #txt
Cs0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>resetDataUploadDialog()</name>
    </language>
</elementInfo>
' #txt
Cs0 f3 1165 213 22 22 14 0 #rect
Cs0 f3 @|RichDialogMethodStartIcon #fIcon
Cs0 f4 type ch.ivy.addon.portalkit.component.CaseItemDocument.CaseItemDocumentData #txt
Cs0 f4 1165 341 22 22 14 0 #rect
Cs0 f4 @|RichDialogProcessEndIcon #fIcon
Cs0 f6 actionDecl 'ch.ivy.addon.portalkit.component.CaseItemDocument.CaseItemDocumentData out;
' #txt
Cs0 f6 actionTable 'out=in;
' #txt
Cs0 f6 actionCode 'import java.util.Iterator;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
Iterator messages = FacesContext.getCurrentInstance().getMessages();

while(messages.hasNext()) {
	messages.next();
	messages.remove();
}
' #txt
Cs0 f6 type ch.ivy.addon.portalkit.component.CaseItemDocument.CaseItemDocumentData #txt
Cs0 f6 1158 276 36 24 20 -2 #rect
Cs0 f6 @|StepIcon #fIcon
Cs0 f23 expr out #txt
Cs0 f23 1176 235 1176 276 #arcP
Cs0 f5 expr out #txt
Cs0 f5 1176 300 1176 341 #arcP
Cs0 f34 guid 1539CA0631099354 #txt
Cs0 f34 type ch.ivy.addon.portalkit.component.CaseItemDocument.CaseItemDocumentData #txt
Cs0 f34 method initData(ch.ivy.addon.portalkit.bo.RemoteCase) #txt
Cs0 f34 disableUIEvents false #txt
Cs0 f34 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<ch.ivy.addon.portalkit.bo.RemoteCase remoteCase> param = methodEvent.getInputArguments();
' #txt
Cs0 f34 inParameterMapAction 'out.remoteCase=param.remoteCase;
' #txt
Cs0 f34 outParameterDecl '<> result;
' #txt
Cs0 f34 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>initData(RemoteCase)</name>
        <nameStyle>20,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f34 197 85 22 22 14 0 #rect
Cs0 f34 @|RichDialogMethodStartIcon #fIcon
Cs0 f37 type ch.ivy.addon.portalkit.component.CaseItemDocument.CaseItemDocumentData #txt
Cs0 f37 197 373 22 22 14 0 #rect
Cs0 f37 @|RichDialogProcessEndIcon #fIcon
Cs0 f80 type ch.ivy.addon.portalkit.component.CaseItemDocument.CaseItemDocumentData #txt
Cs0 f80 processCall MultiPortal/CaseService:findDocuments(ch.ivy.addon.portalkit.persistence.domain.Server,Long) #txt
Cs0 f80 doCall true #txt
Cs0 f80 requestActionDecl '<ch.ivy.addon.portalkit.persistence.domain.Server server,java.lang.Long caseId> param;
' #txt
Cs0 f80 requestMappingAction 'param.server=in.remoteCase.server;
param.caseId=in.remoteCase.id;
' #txt
Cs0 f80 responseActionDecl 'ch.ivy.addon.portalkit.component.CaseItemDocument.CaseItemDocumentData out;
' #txt
Cs0 f80 responseMappingAction 'out=in;
out.documents=result.documents;
' #txt
Cs0 f80 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>CaseService
find documents</name>
        <nameStyle>26,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f80 190 156 36 24 -28 14 #rect
Cs0 f80 @|CallSubIcon #fIcon
Cs0 f39 expr out #txt
Cs0 f39 208 107 208 156 #arcP
Cs0 f41 actionDecl 'ch.ivy.addon.portalkit.component.CaseItemDocument.CaseItemDocumentData out;
' #txt
Cs0 f41 actionTable 'out=in;
' #txt
Cs0 f41 actionCode 'import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, in.uploadDocumentCheckMessage, null));' #txt
Cs0 f41 type ch.ivy.addon.portalkit.component.CaseItemDocument.CaseItemDocumentData #txt
Cs0 f41 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>show error message</name>
    </language>
</elementInfo>
' #txt
Cs0 f41 608 428 32 24 -40 22 #rect
Cs0 f41 @|StepIcon #fIcon
Cs0 f19 expr out #txt
Cs0 f19 624 452 539 912 #arcP
Cs0 f19 1 624 912 #addKink
Cs0 f19 1 0.8365402140487286 0 0 #arcLabel
Cs0 f46 actionDecl 'ch.ivy.addon.portalkit.component.CaseItemDocument.CaseItemDocumentData out;
' #txt
Cs0 f46 actionTable 'out=in;
' #txt
Cs0 f46 actionCode 'import ch.ivy.ws.addon.IvyDocument;
import org.apache.commons.collections.CollectionUtils;
import java.util.ArrayList;
import ch.ivy.addon.portalkit.service.CaseDocumentService;
import ch.ivy.addon.portalkit.util.CaseUtils;
import ch.ivyteam.ivy.workflow.ICase;

if (!CollectionUtils.isEmpty(in.documents)) {
	ICase iCase = CaseUtils.findcase(in.remoteCase.id);
	List expressDocs = new ArrayList();
	for (IvyDocument doc : in.documents) {
		if (iCase.documents().get(doc.getId()).getPath().asString().contains(CaseDocumentService.EXPRESS_UPLOAD_FOLDER)) {
			expressDocs.add(doc);
		}
	}
	in.documents.removeAll(expressDocs);
}' #txt
Cs0 f46 type ch.ivy.addon.portalkit.component.CaseItemDocument.CaseItemDocumentData #txt
Cs0 f46 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>filter</name>
    </language>
</elementInfo>
' #txt
Cs0 f46 152 266 112 44 -11 -8 #rect
Cs0 f46 @|StepIcon #fIcon
Cs0 f50 expr out #txt
Cs0 f50 208 180 208 266 #arcP
Cs0 f40 expr out #txt
Cs0 f40 208 310 208 373 #arcP
Cs0 f51 actionDecl 'ch.ivy.addon.portalkit.component.CaseItemDocument.CaseItemDocumentData out;
' #txt
Cs0 f51 actionTable 'out=in;
' #txt
Cs0 f51 actionCode 'import ch.ivy.ws.addon.IvyDocument;
import org.apache.commons.collections.CollectionUtils;
import java.util.ArrayList;
import ch.ivy.addon.portalkit.service.CaseDocumentService;
import ch.ivy.addon.portalkit.util.CaseUtils;
import ch.ivyteam.ivy.workflow.ICase;

if (!CollectionUtils.isEmpty(in.documents)) {
	ICase iCase = CaseUtils.findcase(in.remoteCase.id);
	List expressDocs = new ArrayList();
	for (IvyDocument doc : in.documents) {
		if (iCase.documents().get(doc.getId()).getPath().asString().contains(CaseDocumentService.EXPRESS_UPLOAD_FOLDER)) {
			expressDocs.add(doc);
		}
	}
	in.documents.removeAll(expressDocs);
}' #txt
Cs0 f51 type ch.ivy.addon.portalkit.component.CaseItemDocument.CaseItemDocumentData #txt
Cs0 f51 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>filter</name>
    </language>
</elementInfo>
' #txt
Cs0 f51 472 810 112 44 -11 -8 #rect
Cs0 f51 @|StepIcon #fIcon
Cs0 f53 expr out #txt
Cs0 f53 528 684 528 810 #arcP
Cs0 f15 expr out #txt
Cs0 f15 528 854 528 901 #arcP
Cs0 f54 type ch.ivy.addon.portalkit.component.CaseItemDocument.CaseItemDocumentData #txt
Cs0 f54 processCall 'Functional Processes/UploadDocumentChecker:call(org.primefaces.model.UploadedFile)' #txt
Cs0 f54 doCall true #txt
Cs0 f54 requestActionDecl '<org.primefaces.model.UploadedFile uploadFile> param;
' #txt
Cs0 f54 requestMappingAction 'param.uploadFile=in.documentUploadEvent.getFile();
' #txt
Cs0 f54 responseActionDecl 'ch.ivy.addon.portalkit.component.CaseItemDocument.CaseItemDocumentData out;
' #txt
Cs0 f54 responseMappingAction 'out=in;
out.uploadDocumentCheckMessage=result.message;
out.uploadDocumentCheckStatus=result.uploadDocumentCheckStatus;
' #txt
Cs0 f54 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>UploadDocumentChecker</name>
        <nameStyle>21,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f54 352 266 160 44 -71 -8 #rect
Cs0 f54 @|CallSubIcon #fIcon
Cs0 f57 expr out #txt
Cs0 f57 432 492 432 572 #arcP
Cs0 f13 expr in #txt
Cs0 f13 outCond 'in.documentUploadEvent.getFile() != null && in.documentUploadEvent.getFile().getSize() > 0' #txt
Cs0 f13 522 208 432 266 #arcP
Cs0 f13 1 432 208 #addKink
Cs0 f13 0 0.9770034044244577 0 0 #arcLabel
Cs0 f55 expr out #txt
Cs0 f55 536 99 536 194 #arcP
Cs0 f59 type ch.ivy.addon.portalkit.component.CaseItemDocument.CaseItemDocumentData #txt
Cs0 f59 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Document check</name>
        <nameStyle>14,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f59 416 368 32 32 -46 18 #rect
Cs0 f59 @|AlternativeIcon #fIcon
Cs0 f60 expr out #txt
Cs0 f60 432 310 432 368 #arcP
Cs0 f60 0 0.9770034044244577 0 0 #arcLabel
Cs0 f14 expr in #txt
Cs0 f14 outCond 'in.uploadDocumentCheckStatus == ch.ivy.addon.portalkit.enums.UploadDocumentCheckStatus.OK' #txt
Cs0 f14 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>OK</name>
        <nameStyle>2,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f14 432 400 432 468 #arcP
Cs0 f14 0 0.5588235294117647 19 0 #arcLabel
Cs0 f61 expr in #txt
Cs0 f61 448 384 624 428 #arcP
Cs0 f61 1 624 384 #addKink
Cs0 f61 0 0.6617402888171451 0 0 #arcLabel
Cs0 f62 actionDecl 'ch.ivy.addon.portalkit.component.CaseItemDocument.CaseItemDocumentData out;
' #txt
Cs0 f62 actionTable 'out=in;
' #txt
Cs0 f62 actionCode 'in.uploadDocumentCheckMessage = ivy.cms.co("/Dialogs/components/CaseDocument/invalidFileMessage");' #txt
Cs0 f62 type ch.ivy.addon.portalkit.component.CaseItemDocument.CaseItemDocumentData #txt
Cs0 f62 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Create error message</name>
    </language>
</elementInfo>
' #txt
Cs0 f62 560 266 128 44 -61 -8 #rect
Cs0 f62 @|StepIcon #fIcon
Cs0 f63 expr in #txt
Cs0 f63 550 208 624 266 #arcP
Cs0 f63 1 624 208 #addKink
Cs0 f63 1 0.8365402140487286 0 0 #arcLabel
Cs0 f45 expr out #txt
Cs0 f45 624 310 624 428 #arcP
Cs0 f45 0 0.8365402140487286 0 0 #arcLabel
Cs0 f64 actionDecl 'ch.ivy.addon.portalkit.component.CaseItemDocument.CaseItemDocumentData out;
' #txt
Cs0 f64 actionTable 'out=in;
' #txt
Cs0 f64 actionCode 'import ch.ivy.addon.portalkit.persistence.variable.GlobalVariable;
import ch.ivy.addon.portalkit.service.GlobalSettingService;

GlobalSettingService globalSettingSerive = new GlobalSettingService();
in.isHideUploadDocumentForDoneCase = globalSettingSerive.findGlobalSettingValueAsBoolean(GlobalVariable.HIDE_UPLOAD_DOCUMENT_FOR_DONE_CASE);' #txt
Cs0 f64 type ch.ivy.addon.portalkit.component.CaseItemDocument.CaseItemDocumentData #txt
Cs0 f64 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Init hide&#xD;
add document</name>
        <nameStyle>23,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f64 8 170 112 44 -39 -16 #rect
Cs0 f64 @|StepIcon #fIcon
Cs0 f65 expr out #txt
Cs0 f65 64 107 64 170 #arcP
Cs0 f2 expr out #txt
Cs0 f2 64 214 64 277 #arcP
>Proto Cs0 .type ch.ivy.addon.portalkit.component.CaseItemDocument.CaseItemDocumentData #txt
>Proto Cs0 .processKind HTML_DIALOG #txt
>Proto Cs0 -8 -8 16 16 16 26 #rect
>Proto Cs0 '' #fIcon
Cs0 f58 mainOut f29 tail #connect
Cs0 f29 head f28 in #connect
Cs0 f9 head f30 mainIn #connect
Cs0 f30 mainOut f17 tail #connect
Cs0 f17 head f11 mainIn #connect
Cs0 f28 out f44 tail #connect
Cs0 f44 head f43 mainIn #connect
Cs0 f28 out f9 tail #connect
Cs0 f24 mainOut f27 tail #connect
Cs0 f27 head f26 mainIn #connect
Cs0 f8 mainOut f25 tail #connect
Cs0 f25 head f24 mainIn #connect
Cs0 f26 mainOut f22 tail #connect
Cs0 f22 head f20 mainIn #connect
Cs0 f42 mainOut f52 tail #connect
Cs0 f52 head f48 in #connect
Cs0 f32 head f49 mainIn #connect
Cs0 f47 mainOut f36 tail #connect
Cs0 f36 head f33 mainIn #connect
Cs0 f49 mainOut f10 tail #connect
Cs0 f10 head f33 mainIn #connect
Cs0 f48 out f16 tail #connect
Cs0 f16 head f47 mainIn #connect
Cs0 f48 out f32 tail #connect
Cs0 f31 mainOut f38 tail #connect
Cs0 f38 head f42 mainIn #connect
Cs0 f12 mainOut f21 tail #connect
Cs0 f21 head f18 mainIn #connect
Cs0 f3 mainOut f23 tail #connect
Cs0 f23 head f6 mainIn #connect
Cs0 f6 mainOut f5 tail #connect
Cs0 f5 head f4 mainIn #connect
Cs0 f34 mainOut f39 tail #connect
Cs0 f39 head f80 mainIn #connect
Cs0 f41 mainOut f19 tail #connect
Cs0 f19 head f11 mainIn #connect
Cs0 f80 mainOut f50 tail #connect
Cs0 f50 head f46 mainIn #connect
Cs0 f46 mainOut f40 tail #connect
Cs0 f40 head f37 mainIn #connect
Cs0 f43 mainOut f53 tail #connect
Cs0 f53 head f51 mainIn #connect
Cs0 f51 mainOut f15 tail #connect
Cs0 f15 head f11 mainIn #connect
Cs0 f56 mainOut f57 tail #connect
Cs0 f57 head f58 mainIn #connect
Cs0 f35 out f13 tail #connect
Cs0 f13 head f54 mainIn #connect
Cs0 f7 mainOut f55 tail #connect
Cs0 f55 head f35 in #connect
Cs0 f54 mainOut f60 tail #connect
Cs0 f60 head f59 in #connect
Cs0 f59 out f14 tail #connect
Cs0 f14 head f56 mainIn #connect
Cs0 f59 out f61 tail #connect
Cs0 f61 head f41 mainIn #connect
Cs0 f35 out f63 tail #connect
Cs0 f63 head f62 mainIn #connect
Cs0 f62 mainOut f45 tail #connect
Cs0 f45 head f41 mainIn #connect
Cs0 f0 mainOut f65 tail #connect
Cs0 f65 head f64 mainIn #connect
Cs0 f64 mainOut f2 tail #connect
Cs0 f2 head f1 mainIn #connect
