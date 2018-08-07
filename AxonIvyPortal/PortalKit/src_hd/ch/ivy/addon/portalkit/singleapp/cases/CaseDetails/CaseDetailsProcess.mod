[Ivy]
[>Created: Wed Mar 02 18:13:25 ICT 2016]
15307172337F4AD0 3.18 #module
>Proto >Proto Collection #zClass
Cs0 CaseDetailsProcess Big #zClass
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
Cs0 @PushWFArc f2 '' #zField
Cs0 @RichDialogMethodStart f3 '' #zField
Cs0 @RichDialogMethodStart f5 '' #zField
Cs0 @Alternative f35 '' #zField
Cs0 @PushWFArc f13 '' #zField
Cs0 @CallSub f58 '' #zField
Cs0 @Alternative f28 '' #zField
Cs0 @GridStep f56 '' #zField
Cs0 @GridStep f30 '' #zField
Cs0 @PushWFArc f59 '' #zField
Cs0 @PushWFArc f29 '' #zField
Cs0 @PushWFArc f14 '' #zField
Cs0 @PushWFArc f9 '' #zField
Cs0 @RichDialogProcessEnd f11 '' #zField
Cs0 @PushWFArc f17 '' #zField
Cs0 @PushWFArc f19 '' #zField
Cs0 @RichDialogProcessEnd f20 '' #zField
Cs0 @GridStep f26 '' #zField
Cs0 @CallSub f24 '' #zField
Cs0 @PushWFArc f27 '' #zField
Cs0 @PushWFArc f25 '' #zField
Cs0 @PushWFArc f22 '' #zField
Cs0 @RichDialogMethodStart f31 '' #zField
Cs0 @Alternative f48 '' #zField
Cs0 @CallSub f42 '' #zField
Cs0 @GridStep f49 '' #zField
Cs0 @PushWFArc f52 '' #zField
Cs0 @PushWFArc f32 '' #zField
Cs0 @RichDialogProcessEnd f33 '' #zField
Cs0 @PushWFArc f34 '' #zField
Cs0 @PushWFArc f37 '' #zField
Cs0 @RichDialogMethodStart f63 '' #zField
Cs0 @CallSub f67 '' #zField
Cs0 @PushWFArc f68 '' #zField
Cs0 @GridStep f64 '' #zField
Cs0 @Alternative f65 '' #zField
Cs0 @GridStep f72 '' #zField
Cs0 @PushWFArc f73 '' #zField
Cs0 @PushWFArc f74 '' #zField
Cs0 @RichDialogProcessEnd f70 '' #zField
Cs0 @PushWFArc f76 '' #zField
Cs0 @RichDialogMethodStart f61 '' #zField
Cs0 @GridStep f62 '' #zField
Cs0 @CallSub f66 '' #zField
Cs0 @PushWFArc f77 '' #zField
Cs0 @GridStep f78 '' #zField
Cs0 @PushWFArc f79 '' #zField
Cs0 @CallSub f80 '' #zField
Cs0 @RichDialogProcessEnd f82 '' #zField
Cs0 @PushWFArc f4 '' #zField
Cs0 @PushWFArc f7 '' #zField
Cs0 @PushWFArc f8 '' #zField
Cs0 @CallSub f16 '' #zField
Cs0 @GridStep f39 '' #zField
Cs0 @PushWFArc f40 '' #zField
Cs0 @PushWFArc f12 '' #zField
Cs0 @PushWFArc f38 '' #zField
Cs0 @PushWFArc f41 '' #zField
Cs0 @PushWFArc f36 '' #zField
Cs0 @GridStep f47 '' #zField
Cs0 @PushWFArc f50 '' #zField
Cs0 @CallSub f43 '' #zField
Cs0 @PushWFArc f44 '' #zField
Cs0 @PushWFArc f15 '' #zField
>Proto Cs0 Cs0 CaseDetailsProcess #zField
Cs0 f0 guid 1530717248815226 #txt
Cs0 f0 type ch.ivy.addon.portalkit.singleapp.cases.CaseDetails.CaseDetailsData #txt
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
Cs0 f0 61 53 22 22 14 0 #rect
Cs0 f0 @|RichDialogInitStartIcon #fIcon
Cs0 f1 type ch.ivy.addon.portalkit.singleapp.cases.CaseDetails.CaseDetailsData #txt
Cs0 f1 285 53 22 22 14 0 #rect
Cs0 f1 @|RichDialogProcessEndIcon #fIcon
Cs0 f2 expr out #txt
Cs0 f2 83 64 285 64 #arcP
Cs0 f3 guid 153072A69852C0F1 #txt
Cs0 f3 type ch.ivy.addon.portalkit.singleapp.cases.CaseDetails.CaseDetailsData #txt
Cs0 f3 method downloadDocument(ch.ivy.ws.addon.IvyDocument,ch.ivy.addon.portalkit.bo.RemoteCase) #txt
Cs0 f3 disableUIEvents false #txt
Cs0 f3 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<ch.ivy.ws.addon.IvyDocument document,ch.ivy.addon.portalkit.bo.RemoteCase remoteCase> param = methodEvent.getInputArguments();
' #txt
Cs0 f3 inParameterMapAction 'out.document=param.document;
out.remoteCase=param.remoteCase;
' #txt
Cs0 f3 outParameterDecl '<> result;
' #txt
Cs0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>downloadDocument(IvyDocument,RemoteCase)</name>
    </language>
</elementInfo>
' #txt
Cs0 f3 61 157 22 22 -57 16 #rect
Cs0 f3 @|RichDialogMethodStartIcon #fIcon
Cs0 f5 guid 153072A9B4E5E2C5 #txt
Cs0 f5 type ch.ivy.addon.portalkit.singleapp.cases.CaseDetails.CaseDetailsData #txt
Cs0 f5 method uploadDocument(org.primefaces.event.FileUploadEvent) #txt
Cs0 f5 disableUIEvents false #txt
Cs0 f5 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<org.primefaces.event.FileUploadEvent event> param = methodEvent.getInputArguments();
' #txt
Cs0 f5 inParameterMapAction 'out.documentUploadEvent=param.event;
' #txt
Cs0 f5 outParameterDecl '<> result;
' #txt
Cs0 f5 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>uploadDocument(FileUploadEvent)</name>
        <nameStyle>31,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f5 61 285 22 22 -57 16 #rect
Cs0 f5 @|RichDialogMethodStartIcon #fIcon
Cs0 f35 type ch.ivy.addon.portalkit.singleapp.cases.CaseDetails.CaseDetailsData #txt
Cs0 f35 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>valid file?</name>
        <nameStyle>11,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f35 282 282 28 28 -34 15 #rect
Cs0 f35 @|AlternativeIcon #fIcon
Cs0 f13 expr out #txt
Cs0 f13 83 296 282 296 #arcP
Cs0 f13 0 0.29095957482186685 0 0 #arcLabel
Cs0 f58 type ch.ivy.addon.portalkit.singleapp.cases.CaseDetails.CaseDetailsData #txt
Cs0 f58 processCall MultiPortal/CaseService:uploadDocument(String,javax.activation.DataHandler,ch.ivy.addon.portalkit.persistence.domain.Server,Long) #txt
Cs0 f58 doCall true #txt
Cs0 f58 requestActionDecl '<java.lang.String documentName,javax.activation.DataHandler documentContent,ch.ivy.addon.portalkit.persistence.domain.Server server,java.lang.Long caseId> param;
' #txt
Cs0 f58 requestMappingAction 'param.documentName=in.documentContent.getName();
param.documentContent=in.documentContent;
param.server=in.remoteCase.server;
param.caseId=in.remoteCase.getId();
' #txt
Cs0 f58 responseActionDecl 'ch.ivy.addon.portalkit.singleapp.cases.CaseDetails.CaseDetailsData out;
' #txt
Cs0 f58 responseMappingAction 'out=in;
out.document=result.document;
out.errors=result.errors;
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
Cs0 f58 534 284 36 24 -32 17 #rect
Cs0 f58 @|CallSubIcon #fIcon
Cs0 f28 type ch.ivy.addon.portalkit.singleapp.cases.CaseDetails.CaseDetailsData #txt
Cs0 f28 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>upload successfully?</name>
        <nameStyle>20,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f28 634 282 28 28 -46 16 #rect
Cs0 f28 @|AlternativeIcon #fIcon
Cs0 f56 actionDecl 'ch.ivy.addon.portalkit.singleapp.cases.CaseDetails.CaseDetailsData out;
' #txt
Cs0 f56 actionTable 'out=in;
' #txt
Cs0 f56 actionCode 'import javax.activation.DataHandler;
import org.apache.cxf.jaxrs.ext.multipart.InputStreamDataSource;
import org.primefaces.model.UploadedFile;

UploadedFile uploadedFile = in.documentUploadEvent.getFile();
InputStreamDataSource source = new InputStreamDataSource(uploadedFile.getInputstream(), uploadedFile.getContentType(), uploadedFile.getFileName());
in.documentContent = new DataHandler(source);' #txt
Cs0 f56 type ch.ivy.addon.portalkit.singleapp.cases.CaseDetails.CaseDetailsData #txt
Cs0 f56 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>get document information</name>
        <nameStyle>24,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f56 406 284 36 24 -69 16 #rect
Cs0 f56 @|StepIcon #fIcon
Cs0 f30 actionDecl 'ch.ivy.addon.portalkit.singleapp.cases.CaseDetails.CaseDetailsData out;
' #txt
Cs0 f30 actionTable 'out=in;
' #txt
Cs0 f30 actionCode 'import ch.ivy.ws.addon.WsException;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

for (WsException error : in.errors) {
	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, error.userText, null));
}' #txt
Cs0 f30 type ch.ivy.addon.portalkit.singleapp.cases.CaseDetails.CaseDetailsData #txt
Cs0 f30 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>show error message</name>
        <nameStyle>18,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f30 758 220 36 24 20 -2 #rect
Cs0 f30 @|StepIcon #fIcon
Cs0 f59 expr out #txt
Cs0 f59 442 296 534 296 #arcP
Cs0 f29 expr out #txt
Cs0 f29 570 296 634 296 #arcP
Cs0 f14 expr in #txt
Cs0 f14 outCond 'in.documentUploadEvent.getFile() != null && in.documentUploadEvent.getFile().getSize() > 0' #txt
Cs0 f14 310 296 406 296 #arcP
Cs0 f9 expr in #txt
Cs0 f9 648 282 758 232 #arcP
Cs0 f9 1 648 232 #addKink
Cs0 f9 1 0.09188965456135022 0 0 #arcLabel
Cs0 f11 type ch.ivy.addon.portalkit.singleapp.cases.CaseDetails.CaseDetailsData #txt
Cs0 f11 925 285 22 22 14 0 #rect
Cs0 f11 @|RichDialogProcessEndIcon #fIcon
Cs0 f17 expr out #txt
Cs0 f17 794 232 936 285 #arcP
Cs0 f17 1 936 232 #addKink
Cs0 f17 0 0.7224816120006017 0 0 #arcLabel
Cs0 f19 expr in #txt
Cs0 f19 296 310 936 307 #arcP
Cs0 f19 1 296 480 #addKink
Cs0 f19 2 936 480 #addKink
Cs0 f19 1 0.5017361111111112 0 0 #arcLabel
Cs0 f20 type ch.ivy.addon.portalkit.singleapp.cases.CaseDetails.CaseDetailsData #txt
Cs0 f20 541 157 22 22 14 0 #rect
Cs0 f20 @|RichDialogProcessEndIcon #fIcon
Cs0 f26 actionDecl 'ch.ivy.addon.portalkit.singleapp.cases.CaseDetails.CaseDetailsData out;
' #txt
Cs0 f26 actionTable 'out=in;
' #txt
Cs0 f26 actionCode 'import org.primefaces.model.DefaultStreamedContent;
import java.io.InputStream;

InputStream inputStream = in.documentContent.getInputStream();
in.documentFile = new DefaultStreamedContent(inputStream, in.document.getContentType(), in.document.getName());

' #txt
Cs0 f26 type ch.ivy.addon.portalkit.singleapp.cases.CaseDetails.CaseDetailsData #txt
Cs0 f26 438 156 36 24 20 -2 #rect
Cs0 f26 @|StepIcon #fIcon
Cs0 f24 type ch.ivy.addon.portalkit.singleapp.cases.CaseDetails.CaseDetailsData #txt
Cs0 f24 processCall MultiPortal/CaseService:downloadDocument(ch.ivy.addon.portalkit.persistence.domain.Server,Long,Long) #txt
Cs0 f24 doCall true #txt
Cs0 f24 requestActionDecl '<ch.ivy.addon.portalkit.persistence.domain.Server server,java.lang.Long documentId,java.lang.Long caseId> param;
' #txt
Cs0 f24 requestMappingAction 'param.server=in.remoteCase.server;
param.documentId=in.document.id;
param.caseId=in.remoteCase.getId();
' #txt
Cs0 f24 responseActionDecl 'ch.ivy.addon.portalkit.singleapp.cases.CaseDetails.CaseDetailsData out;
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
Cs0 f24 310 156 36 24 -30 17 #rect
Cs0 f24 @|CallSubIcon #fIcon
Cs0 f27 expr out #txt
Cs0 f27 346 168 438 168 #arcP
Cs0 f25 expr out #txt
Cs0 f25 83 168 310 168 #arcP
Cs0 f22 expr out #txt
Cs0 f22 474 168 541 168 #arcP
Cs0 f31 guid 153084774238116D #txt
Cs0 f31 type ch.ivy.addon.portalkit.singleapp.cases.CaseDetails.CaseDetailsData #txt
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
Cs0 f31 69 413 22 22 -42 17 #rect
Cs0 f31 @|RichDialogMethodStartIcon #fIcon
Cs0 f48 type ch.ivy.addon.portalkit.singleapp.cases.CaseDetails.CaseDetailsData #txt
Cs0 f48 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>remove successfully?</name>
        <nameStyle>20,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f48 354 410 28 28 -39 -31 #rect
Cs0 f48 @|AlternativeIcon #fIcon
Cs0 f42 type ch.ivy.addon.portalkit.singleapp.cases.CaseDetails.CaseDetailsData #txt
Cs0 f42 processCall MultiPortal/CaseService:deleteDocument(ch.ivy.addon.portalkit.persistence.domain.Server,Long,Long) #txt
Cs0 f42 doCall true #txt
Cs0 f42 requestActionDecl '<ch.ivy.addon.portalkit.persistence.domain.Server server,java.lang.Long documentId,java.lang.Long caseId> param;
' #txt
Cs0 f42 requestMappingAction 'param.server=in.remoteCase.server;
param.documentId=in.document.id;
param.caseId=in.remoteCase.getId();
' #txt
Cs0 f42 responseActionDecl 'ch.ivy.addon.portalkit.singleapp.cases.CaseDetails.CaseDetailsData out;
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
Cs0 f42 190 412 36 24 -40 23 #rect
Cs0 f42 @|CallSubIcon #fIcon
Cs0 f49 actionDecl 'ch.ivy.addon.portalkit.singleapp.cases.CaseDetails.CaseDetailsData out;
' #txt
Cs0 f49 actionTable 'out=in;
' #txt
Cs0 f49 actionCode 'import ch.ivy.ws.addon.WsException;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

for (WsException error : in.errors) {
	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, error.userText, null));
}' #txt
Cs0 f49 type ch.ivy.addon.portalkit.singleapp.cases.CaseDetails.CaseDetailsData #txt
Cs0 f49 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>show error message</name>
        <nameStyle>18,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f49 350 484 36 24 -62 13 #rect
Cs0 f49 @|StepIcon #fIcon
Cs0 f52 expr out #txt
Cs0 f52 226 424 354 424 #arcP
Cs0 f32 expr in #txt
Cs0 f32 368 438 368 484 #arcP
Cs0 f32 0 0.47952939196677374 0 0 #arcLabel
Cs0 f33 type ch.ivy.addon.portalkit.singleapp.cases.CaseDetails.CaseDetailsData #txt
Cs0 f33 677 413 22 22 14 0 #rect
Cs0 f33 @|RichDialogProcessEndIcon #fIcon
Cs0 f34 expr out #txt
Cs0 f34 91 424 190 424 #arcP
Cs0 f37 expr out #txt
Cs0 f37 386 496 688 435 #arcP
Cs0 f37 1 688 496 #addKink
Cs0 f37 0 0.6967400801419017 0 0 #arcLabel
Cs0 f63 guid 153172F2EA5B0DA0 #txt
Cs0 f63 type ch.ivy.addon.portalkit.singleapp.cases.CaseDetails.CaseDetailsData #txt
Cs0 f63 method createNote() #txt
Cs0 f63 disableUIEvents false #txt
Cs0 f63 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<> param = methodEvent.getInputArguments();
' #txt
Cs0 f63 outParameterDecl '<> result;
' #txt
Cs0 f63 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>createNote()</name>
        <nameStyle>12,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f63 29 733 22 22 14 0 #rect
Cs0 f63 @|RichDialogMethodStartIcon #fIcon
Cs0 f67 type ch.ivy.addon.portalkit.singleapp.cases.CaseDetails.CaseDetailsData #txt
Cs0 f67 processCall MultiPortal/CaseService:createNote(ch.ivy.addon.portalkit.persistence.domain.Server,Long,String,String) #txt
Cs0 f67 doCall true #txt
Cs0 f67 requestActionDecl '<ch.ivy.addon.portalkit.persistence.domain.Server server,java.lang.Long caseId,java.lang.String username,java.lang.String content> param;
' #txt
Cs0 f67 requestMappingAction 'param.server=in.remoteCase.server;
param.caseId=in.remoteCase.id;
param.username=ivy.session.getSessionUserName();
param.content=in.noteContent;
' #txt
Cs0 f67 responseActionDecl 'ch.ivy.addon.portalkit.singleapp.cases.CaseDetails.CaseDetailsData out;
' #txt
Cs0 f67 responseMappingAction 'out=in;
out.errors=result.errors;
out.note=result.note;
' #txt
Cs0 f67 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>CaseService
createNote</name>
        <nameStyle>22,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f67 142 732 36 24 -31 19 #rect
Cs0 f67 @|CallSubIcon #fIcon
Cs0 f68 expr out #txt
Cs0 f68 51 744 142 744 #arcP
Cs0 f64 actionDecl 'ch.ivy.addon.portalkit.singleapp.cases.CaseDetails.CaseDetailsData out;
' #txt
Cs0 f64 actionTable 'out=in;
' #txt
Cs0 f64 actionCode 'import ch.ivy.ws.addon.WsException;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

for (WsException error : in.errors) {
	FacesContext.getCurrentInstance().addMessage("note-content", new FacesMessage(FacesMessage.SEVERITY_ERROR, error.userText, null));
}
FacesContext.getCurrentInstance().validationFailed();' #txt
Cs0 f64 type ch.ivy.addon.portalkit.singleapp.cases.CaseDetails.CaseDetailsData #txt
Cs0 f64 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Show errors</name>
        <nameStyle>11,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f64 390 812 36 24 20 -2 #rect
Cs0 f64 @|StepIcon #fIcon
Cs0 f65 type ch.ivy.addon.portalkit.singleapp.cases.CaseDetails.CaseDetailsData #txt
Cs0 f65 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>successfully?</name>
        <nameStyle>13,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f65 210 730 28 28 -36 -30 #rect
Cs0 f65 @|AlternativeIcon #fIcon
Cs0 f72 actionDecl 'ch.ivy.addon.portalkit.singleapp.cases.CaseDetails.CaseDetailsData out;
' #txt
Cs0 f72 actionTable 'out=in;
' #txt
Cs0 f72 actionCode 'import ch.ivy.addon.portalkit.bo.RemoteNote;

out.noteContent = "";
RemoteNote remoteNote = new RemoteNote(in.note);
out.remoteCase.getRemoteNotes().add(remoteNote);
' #txt
Cs0 f72 type ch.ivy.addon.portalkit.singleapp.cases.CaseDetails.CaseDetailsData #txt
Cs0 f72 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>clear note field
update case notes</name>
        <nameStyle>34,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f72 310 732 36 24 -36 18 #rect
Cs0 f72 @|StepIcon #fIcon
Cs0 f73 expr in #txt
Cs0 f73 outCond in.errors.isEmpty() #txt
Cs0 f73 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>yes</name>
        <nameStyle>3,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f73 238 744 310 744 #arcP
Cs0 f73 0 0.5925925925925926 1 -15 #arcLabel
Cs0 f74 expr in #txt
Cs0 f74 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>no</name>
        <nameStyle>2,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f74 224 758 390 824 #arcP
Cs0 f74 1 224 824 #addKink
Cs0 f74 1 0.4294012299513545 1 13 #arcLabel
Cs0 f70 type ch.ivy.addon.portalkit.singleapp.cases.CaseDetails.CaseDetailsData #txt
Cs0 f70 861 733 22 22 14 0 #rect
Cs0 f70 @|RichDialogProcessEndIcon #fIcon
Cs0 f76 expr out #txt
Cs0 f76 426 824 872 755 #arcP
Cs0 f76 1 872 824 #addKink
Cs0 f76 0 0.589419446648472 0 0 #arcLabel
Cs0 f61 guid 1531C5C0B3496103 #txt
Cs0 f61 type ch.ivy.addon.portalkit.singleapp.cases.CaseDetails.CaseDetailsData #txt
Cs0 f61 method initCaseDetailsOf(ch.ivy.addon.portalkit.bo.RemoteCase) #txt
Cs0 f61 disableUIEvents false #txt
Cs0 f61 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<ch.ivy.addon.portalkit.bo.RemoteCase case> param = methodEvent.getInputArguments();
' #txt
Cs0 f61 inParameterMapAction 'out.remoteCase=param.case;
' #txt
Cs0 f61 outParameterDecl '<> result;
' #txt
Cs0 f61 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>initCaseDetailsOf
(RemoteCase)</name>
        <nameStyle>30,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f61 29 589 22 22 14 0 #rect
Cs0 f61 @|RichDialogMethodStartIcon #fIcon
Cs0 f62 actionDecl 'ch.ivy.addon.portalkit.singleapp.cases.CaseDetails.CaseDetailsData out;
' #txt
Cs0 f62 actionTable 'out=in;
' #txt
Cs0 f62 actionCode 'import ch.ivyteam.ivy.workflow.TaskState;
import ch.ivy.addon.portalkit.bo.RemoteTask;
in.relatedTasks = new java.util.ArrayList();

for (RemoteTask task: in.tasksOfCase) {
	TaskState state = task.getState();
	if (state == TaskState.SUSPENDED || state == TaskState.RESUMED || state == TaskState.PARKED) {
		in.relatedTasks.add(task)	;
	}
}
' #txt
Cs0 f62 type ch.ivy.addon.portalkit.singleapp.cases.CaseDetails.CaseDetailsData #txt
Cs0 f62 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>find related tasks</name>
        <nameStyle>18,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f62 366 588 36 24 20 -2 #rect
Cs0 f62 @|StepIcon #fIcon
Cs0 f66 type ch.ivy.addon.portalkit.singleapp.cases.CaseDetails.CaseDetailsData #txt
Cs0 f66 processCall MultiPortal/TaskService:findTasksOfCase(ch.ivy.addon.portalkit.persistence.domain.Server,Long) #txt
Cs0 f66 doCall true #txt
Cs0 f66 requestActionDecl '<ch.ivy.addon.portalkit.persistence.domain.Server server,java.lang.Long caseId> param;
' #txt
Cs0 f66 requestMappingAction 'param.server=in.remoteCase.server;
param.caseId=in.remoteCase.id;
' #txt
Cs0 f66 responseActionDecl 'ch.ivy.addon.portalkit.singleapp.cases.CaseDetails.CaseDetailsData out;
' #txt
Cs0 f66 responseMappingAction 'out=in;
out.errors=in.errors;
out.tasksOfCase=result.tasks;
' #txt
Cs0 f66 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>find all tasks
 of case</name>
        <nameStyle>23,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f66 166 588 36 24 20 -2 #rect
Cs0 f66 @|CallSubIcon #fIcon
Cs0 f77 expr out #txt
Cs0 f77 51 600 166 600 #arcP
Cs0 f78 actionDecl 'ch.ivy.addon.portalkit.singleapp.cases.CaseDetails.CaseDetailsData out;
' #txt
Cs0 f78 actionTable 'out=in;
' #txt
Cs0 f78 actionCode 'import ch.ivyteam.ivy.workflow.TaskState;
import java.util.ArrayList;
import ch.ivy.addon.portalkit.service.HistoryService;
import ch.ivy.addon.portalkit.vo.TaskVO;
import ch.ivy.addon.portalkit.bo.RemoteTask;

ArrayList<RemoteTask> finishedTasks = new ArrayList();
for (RemoteTask task: in.tasksOfCase) {
	if (task.getState() == TaskState.DONE) {
		finishedTasks.add(task)	;
	}
}
HistoryService historyService = new HistoryService();
in.histories = historyService.createHistories(finishedTasks, in.remoteCase.remoteNotes);' #txt
Cs0 f78 type ch.ivy.addon.portalkit.singleapp.cases.CaseDetails.CaseDetailsData #txt
Cs0 f78 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>create histories 
from tasks and notes</name>
        <nameStyle>38,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f78 510 588 36 24 20 -1 #rect
Cs0 f78 @|StepIcon #fIcon
Cs0 f79 expr out #txt
Cs0 f79 402 600 510 600 #arcP
Cs0 f80 type ch.ivy.addon.portalkit.singleapp.cases.CaseDetails.CaseDetailsData #txt
Cs0 f80 processCall MultiPortal/CaseService:findDocuments(ch.ivy.addon.portalkit.persistence.domain.Server,Long) #txt
Cs0 f80 doCall true #txt
Cs0 f80 requestActionDecl '<ch.ivy.addon.portalkit.persistence.domain.Server server,java.lang.Long caseId> param;
' #txt
Cs0 f80 requestMappingAction 'param.server=in.remoteCase.server;
param.caseId=in.remoteCase.id;
' #txt
Cs0 f80 responseActionDecl 'ch.ivy.addon.portalkit.singleapp.cases.CaseDetails.CaseDetailsData out;
' #txt
Cs0 f80 responseMappingAction 'out=in;
out.documents=result.documents;
out.errors=in.errors.addAll(result.errors);
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
Cs0 f80 294 588 36 24 -28 14 #rect
Cs0 f80 @|CallSubIcon #fIcon
Cs0 f82 type ch.ivy.addon.portalkit.singleapp.cases.CaseDetails.CaseDetailsData #txt
Cs0 f82 861 589 22 22 14 0 #rect
Cs0 f82 @|RichDialogProcessEndIcon #fIcon
Cs0 f4 expr out #txt
Cs0 f4 202 600 294 600 #arcP
Cs0 f7 expr out #txt
Cs0 f7 330 600 366 600 #arcP
Cs0 f7 0 0.41339749593910025 0 0 #arcLabel
Cs0 f8 expr out #txt
Cs0 f8 546 600 861 600 #arcP
Cs0 f8 0 0.7711867924780302 0 0 #arcLabel
Cs0 f16 type ch.ivy.addon.portalkit.singleapp.cases.CaseDetails.CaseDetailsData #txt
Cs0 f16 processCall MultiPortal/TaskService:findTasksOfCase(ch.ivy.addon.portalkit.persistence.domain.Server,Long) #txt
Cs0 f16 doCall true #txt
Cs0 f16 requestActionDecl '<ch.ivy.addon.portalkit.persistence.domain.Server server,java.lang.Long caseId> param;
' #txt
Cs0 f16 requestMappingAction 'param.server=in.remoteCase.server;
param.caseId=in.remoteCase.id;
' #txt
Cs0 f16 responseActionDecl 'ch.ivy.addon.portalkit.singleapp.cases.CaseDetails.CaseDetailsData out;
' #txt
Cs0 f16 responseMappingAction 'out=in;
out.errors=in.errors;
out.tasksOfCase=result.tasks;
' #txt
Cs0 f16 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>find all tasks
 of case</name>
        <nameStyle>23,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f16 390 732 36 24 -32 -49 #rect
Cs0 f16 @|CallSubIcon #fIcon
Cs0 f39 actionDecl 'ch.ivy.addon.portalkit.singleapp.cases.CaseDetails.CaseDetailsData out;
' #txt
Cs0 f39 actionTable 'out=in;
' #txt
Cs0 f39 actionCode 'import ch.ivyteam.ivy.workflow.TaskState;
import java.util.ArrayList;
import ch.ivy.addon.portalkit.service.HistoryService;
import ch.ivy.addon.portalkit.vo.TaskVO;
import ch.ivy.addon.portalkit.bo.RemoteTask;

ArrayList<RemoteTask> finishedTasks = new ArrayList();
for (RemoteTask task: in.tasksOfCase) {
	if (task.getState() == TaskState.DONE) {
		finishedTasks.add(task)	;
	}
}
HistoryService historyService = new HistoryService();
in.histories = historyService.createHistories(finishedTasks, in.remoteCase.remoteNotes);' #txt
Cs0 f39 type ch.ivy.addon.portalkit.singleapp.cases.CaseDetails.CaseDetailsData #txt
Cs0 f39 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>create histories 
from tasks and notes</name>
        <nameStyle>38,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f39 470 732 36 24 20 -1 #rect
Cs0 f39 @|StepIcon #fIcon
Cs0 f40 expr out #txt
Cs0 f40 426 744 470 744 #arcP
Cs0 f12 expr out #txt
Cs0 f12 178 744 210 744 #arcP
Cs0 f38 expr out #txt
Cs0 f38 346 744 390 744 #arcP
Cs0 f41 expr out #txt
Cs0 f41 506 744 861 744 #arcP
Cs0 f36 expr out #txt
Cs0 f36 546 424 677 424 #arcP
Cs0 f47 actionDecl 'ch.ivy.addon.portalkit.singleapp.cases.CaseDetails.CaseDetailsData out;
' #txt
Cs0 f47 actionTable 'out=in;
' #txt
Cs0 f47 actionCode in.documents.remove(in.document); #txt
Cs0 f47 type ch.ivy.addon.portalkit.singleapp.cases.CaseDetails.CaseDetailsData #txt
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
Cs0 f47 510 412 36 24 -46 11 #rect
Cs0 f47 @|StepIcon #fIcon
Cs0 f50 expr in #txt
Cs0 f50 outCond in.errors.isEmpty() #txt
Cs0 f50 382 424 510 424 #arcP
Cs0 f43 type ch.ivy.addon.portalkit.singleapp.cases.CaseDetails.CaseDetailsData #txt
Cs0 f43 processCall MultiPortal/CaseService:findDocuments(ch.ivy.addon.portalkit.persistence.domain.Server,Long) #txt
Cs0 f43 doCall true #txt
Cs0 f43 requestActionDecl '<ch.ivy.addon.portalkit.persistence.domain.Server server,java.lang.Long caseId> param;
' #txt
Cs0 f43 requestMappingAction 'param.server=in.remoteCase.server;
param.caseId=in.remoteCase.id;
' #txt
Cs0 f43 responseActionDecl 'ch.ivy.addon.portalkit.singleapp.cases.CaseDetails.CaseDetailsData out;
' #txt
Cs0 f43 responseMappingAction 'out=in;
out.documents=result.documents;
out.errors=in.errors.addAll(result.errors);
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
Cs0 f43 758 284 36 24 -28 14 #rect
Cs0 f43 @|CallSubIcon #fIcon
Cs0 f44 expr in #txt
Cs0 f44 outCond in.errors.isEmpty() #txt
Cs0 f44 662 296 758 296 #arcP
Cs0 f15 expr out #txt
Cs0 f15 794 296 925 296 #arcP
>Proto Cs0 .type ch.ivy.addon.portalkit.singleapp.cases.CaseDetails.CaseDetailsData #txt
>Proto Cs0 .processKind HTML_DIALOG #txt
>Proto Cs0 -8 -8 16 16 16 26 #rect
>Proto Cs0 '' #fIcon
Cs0 f0 mainOut f2 tail #connect
Cs0 f2 head f1 mainIn #connect
Cs0 f5 mainOut f13 tail #connect
Cs0 f13 head f35 in #connect
Cs0 f56 mainOut f59 tail #connect
Cs0 f59 head f58 mainIn #connect
Cs0 f58 mainOut f29 tail #connect
Cs0 f29 head f28 in #connect
Cs0 f35 out f14 tail #connect
Cs0 f14 head f56 mainIn #connect
Cs0 f9 head f30 mainIn #connect
Cs0 f30 mainOut f17 tail #connect
Cs0 f17 head f11 mainIn #connect
Cs0 f35 out f19 tail #connect
Cs0 f19 head f11 mainIn #connect
Cs0 f24 mainOut f27 tail #connect
Cs0 f27 head f26 mainIn #connect
Cs0 f3 mainOut f25 tail #connect
Cs0 f25 head f24 mainIn #connect
Cs0 f26 mainOut f22 tail #connect
Cs0 f22 head f20 mainIn #connect
Cs0 f48 out f50 tail #connect
Cs0 f50 head f47 mainIn #connect
Cs0 f42 mainOut f52 tail #connect
Cs0 f52 head f48 in #connect
Cs0 f48 out f32 tail #connect
Cs0 f32 head f49 mainIn #connect
Cs0 f31 mainOut f34 tail #connect
Cs0 f34 head f42 mainIn #connect
Cs0 f47 mainOut f36 tail #connect
Cs0 f36 head f33 mainIn #connect
Cs0 f49 mainOut f37 tail #connect
Cs0 f37 head f33 mainIn #connect
Cs0 f63 mainOut f68 tail #connect
Cs0 f68 head f67 mainIn #connect
Cs0 f65 out f73 tail #connect
Cs0 f73 head f72 mainIn #connect
Cs0 f65 out f74 tail #connect
Cs0 f74 head f64 mainIn #connect
Cs0 f64 mainOut f76 tail #connect
Cs0 f76 head f70 mainIn #connect
Cs0 f61 mainOut f77 tail #connect
Cs0 f77 head f66 mainIn #connect
Cs0 f62 mainOut f79 tail #connect
Cs0 f79 head f78 mainIn #connect
Cs0 f66 mainOut f4 tail #connect
Cs0 f4 head f80 mainIn #connect
Cs0 f80 mainOut f7 tail #connect
Cs0 f7 head f62 mainIn #connect
Cs0 f78 mainOut f8 tail #connect
Cs0 f8 head f82 mainIn #connect
Cs0 f16 mainOut f40 tail #connect
Cs0 f40 head f39 mainIn #connect
Cs0 f67 mainOut f12 tail #connect
Cs0 f12 head f65 in #connect
Cs0 f72 mainOut f38 tail #connect
Cs0 f38 head f16 mainIn #connect
Cs0 f39 mainOut f41 tail #connect
Cs0 f41 head f70 mainIn #connect
Cs0 f28 out f44 tail #connect
Cs0 f44 head f43 mainIn #connect
Cs0 f28 out f9 tail #connect
Cs0 f43 mainOut f15 tail #connect
Cs0 f15 head f11 mainIn #connect
