[Ivy]
153A79700C35BE80 3.20 #module
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
Cs0 @RichDialogMethodStart f7 '' #zField
Cs0 @RichDialogMethodStart f31 '' #zField
Cs0 @RichDialogProcessEnd f33 '' #zField
Cs0 @RichDialogMethodStart f3 '' #zField
Cs0 @RichDialogProcessEnd f4 '' #zField
Cs0 @GridStep f6 '' #zField
Cs0 @PushWFArc f23 '' #zField
Cs0 @PushWFArc f5 '' #zField
Cs0 @RichDialogProcessEnd f37 '' #zField
Cs0 @GridStep f39 '' #zField
Cs0 @PushWFArc f41 '' #zField
Cs0 @RichDialogMethodStart f8 '' #zField
Cs0 @RichDialogProcessEnd f9 '' #zField
Cs0 @GridStep f13 '' #zField
Cs0 @PushWFArc f14 '' #zField
Cs0 @PushWFArc f15 '' #zField
Cs0 @RichDialogMethodStart f12 '' #zField
Cs0 @RichDialogProcessEnd f18 '' #zField
Cs0 @PushWFArc f19 '' #zField
Cs0 @GridStep f10 '' #zField
Cs0 @PushWFArc f16 '' #zField
Cs0 @GridStep f21 '' #zField
Cs0 @Alternative f29 '' #zField
Cs0 @Alternative f22 '' #zField
Cs0 @PushWFArc f32 '' #zField
Cs0 @PushWFArc f27 '' #zField
Cs0 @GridStep f24 '' #zField
Cs0 @PushWFArc f25 '' #zField
Cs0 @GridStep f38 '' #zField
Cs0 @PushWFArc f42 '' #zField
Cs0 @PushWFArc f28 '' #zField
Cs0 @GridStep f11 '' #zField
Cs0 @PushWFArc f30 '' #zField
Cs0 @PushWFArc f17 '' #zField
Cs0 @GridStep f1 '' #zField
Cs0 @PushWFArc f34 '' #zField
Cs0 @CallSub f54 '' #zField
Cs0 @PushWFArc f35 '' #zField
Cs0 @Alternative f40 '' #zField
Cs0 @PushWFArc f43 '' #zField
Cs0 @PushWFArc f26 '' #zField
Cs0 @PushWFArc f44 '' #zField
Cs0 @GridStep f62 '' #zField
Cs0 @PushWFArc f45 '' #zField
Cs0 @PushWFArc f2 '' #zField
Cs0 @GridStep f68 '' #zField
Cs0 @PushWFArc f46 '' #zField
Cs0 @PushWFArc f20 '' #zField
Cs0 @GridStep f66 '' #zField
Cs0 @PushWFArc f47 '' #zField
Cs0 @PushWFArc f36 '' #zField
>Proto Cs0 Cs0 CaseItemDocumentProcess #zField
Cs0 f0 guid 1533610171B77F44 #txt
Cs0 f0 type ch.ivy.addon.portalkit.singleapp.cases.CaseItemDocument.CaseItemDocumentData #txt
Cs0 f0 method start(java.lang.Long) #txt
Cs0 f0 disableUIEvents true #txt
Cs0 f0 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<java.lang.Long caseId> param = methodEvent.getInputArguments();
' #txt
Cs0 f0 inParameterMapAction 'out.caseId=param.caseId;
' #txt
Cs0 f0 outParameterDecl '<> result;
' #txt
Cs0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start()</name>
        <nameStyle>7,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f0 45 85 22 22 14 0 #rect
Cs0 f0 @|RichDialogInitStartIcon #fIcon
Cs0 f7 guid 153361C617411F60 #txt
Cs0 f7 type ch.ivy.addon.portalkit.singleapp.cases.CaseItemDocument.CaseItemDocumentData #txt
Cs0 f7 method uploadDocument(org.primefaces.event.FileUploadEvent) #txt
Cs0 f7 disableUIEvents false #txt
Cs0 f7 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<org.primefaces.event.FileUploadEvent event> param = methodEvent.getInputArguments();
' #txt
Cs0 f7 inParameterMapAction 'out.documentUploadEvent=param.event;
' #txt
Cs0 f7 inActionCode 'out.clientId = param.event.getComponent().getAttributes().get("clientId") as String;
out.clientId = out.clientId.replace(":document-upload-form:document-upload-panel","");' #txt
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
Cs0 f7 549 85 22 22 -57 16 #rect
Cs0 f7 @|RichDialogMethodStartIcon #fIcon
Cs0 f31 guid 153362794021D4F5 #txt
Cs0 f31 type ch.ivy.addon.portalkit.singleapp.cases.CaseItemDocument.CaseItemDocumentData #txt
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
Cs0 f31 261 85 22 22 12 -3 #rect
Cs0 f31 @|RichDialogMethodStartIcon #fIcon
Cs0 f33 type ch.ivy.addon.portalkit.singleapp.cases.CaseItemDocument.CaseItemDocumentData #txt
Cs0 f33 549 597 22 22 14 0 #rect
Cs0 f33 @|RichDialogProcessEndIcon #fIcon
Cs0 f3 guid 1534FDA4FDC9B40C #txt
Cs0 f3 type ch.ivy.addon.portalkit.singleapp.cases.CaseItemDocument.CaseItemDocumentData #txt
Cs0 f3 method resetDataUploadDialog(String) #txt
Cs0 f3 disableUIEvents false #txt
Cs0 f3 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<java.lang.String clientId> param = methodEvent.getInputArguments();
' #txt
Cs0 f3 inParameterMapAction 'out.clientId=param.clientId;
' #txt
Cs0 f3 outParameterDecl '<> result;
' #txt
Cs0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>resetDataUploadDialog()</name>
        <nameStyle>23,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f3 1429 85 22 22 14 0 #rect
Cs0 f3 @|RichDialogMethodStartIcon #fIcon
Cs0 f4 type ch.ivy.addon.portalkit.singleapp.cases.CaseItemDocument.CaseItemDocumentData #txt
Cs0 f4 1429 213 22 22 14 0 #rect
Cs0 f4 @|RichDialogProcessEndIcon #fIcon
Cs0 f6 actionDecl 'ch.ivy.addon.portalkit.singleapp.cases.CaseItemDocument.CaseItemDocumentData out;
' #txt
Cs0 f6 actionTable 'out=in;
' #txt
Cs0 f6 actionCode 'import org.primefaces.context.RequestContext;
import java.util.Iterator;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
Iterator messages = FacesContext.getCurrentInstance().getMessages();

while(messages.hasNext()) {
	messages.next();
	messages.remove();
}
RequestContext.getCurrentInstance().update(in.clientId + ":document-upload-form:upload-messages");' #txt
Cs0 f6 type ch.ivy.addon.portalkit.singleapp.cases.CaseItemDocument.CaseItemDocumentData #txt
Cs0 f6 1422 180 36 24 20 -2 #rect
Cs0 f6 @|StepIcon #fIcon
Cs0 f23 expr out #txt
Cs0 f23 1440 107 1440 180 #arcP
Cs0 f5 expr out #txt
Cs0 f5 1440 204 1440 213 #arcP
Cs0 f37 type ch.ivy.addon.portalkit.singleapp.cases.CaseItemDocument.CaseItemDocumentData #txt
Cs0 f37 45 301 22 22 14 0 #rect
Cs0 f37 @|RichDialogProcessEndIcon #fIcon
Cs0 f39 actionDecl 'ch.ivy.addon.portalkit.singleapp.cases.CaseItemDocument.CaseItemDocumentData out;
' #txt
Cs0 f39 actionTable 'out=in;
' #txt
Cs0 f39 actionCode 'import ch.ivy.addon.portalkit.service.CaseDocumentService;
import org.primefaces.model.UploadedFile;

CaseDocumentService documentService = new CaseDocumentService(in.internalCase.getId());
in.documents = documentService.getAll();' #txt
Cs0 f39 type ch.ivy.addon.portalkit.singleapp.cases.CaseItemDocument.CaseItemDocumentData #txt
Cs0 f39 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>getDocuments</name>
        <nameStyle>12,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f39 38 180 36 24 -38 22 #rect
Cs0 f39 @|StepIcon #fIcon
Cs0 f41 expr out #txt
Cs0 f41 56 204 56 301 #arcP
Cs0 f8 guid 153A7A3758776085 #txt
Cs0 f8 type ch.ivy.addon.portalkit.singleapp.cases.CaseItemDocument.CaseItemDocumentData #txt
Cs0 f8 method downloadDocument(String) #txt
Cs0 f8 disableUIEvents false #txt
Cs0 f8 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<java.lang.String fileName> param = methodEvent.getInputArguments();
' #txt
Cs0 f8 inParameterMapAction 'out.selectedFileName=param.fileName;
' #txt
Cs0 f8 outParameterDecl '<org.primefaces.model.StreamedContent document> result;
' #txt
Cs0 f8 outParameterMapAction 'result.document=in.documentFile;
' #txt
Cs0 f8 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>downloadDocument(String)</name>
        <nameStyle>24,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f8 1213 85 22 22 14 0 #rect
Cs0 f8 @|RichDialogMethodStartIcon #fIcon
Cs0 f9 type ch.ivy.addon.portalkit.singleapp.cases.CaseItemDocument.CaseItemDocumentData #txt
Cs0 f9 1213 285 22 22 14 0 #rect
Cs0 f9 @|RichDialogProcessEndIcon #fIcon
Cs0 f13 actionDecl 'ch.ivy.addon.portalkit.singleapp.cases.CaseItemDocument.CaseItemDocumentData out;
' #txt
Cs0 f13 actionTable 'out=in;
' #txt
Cs0 f13 actionCode 'import ch.ivy.addon.portalkit.service.CaseDocumentService;

CaseDocumentService documentService = new CaseDocumentService(in.internalCase.getId());
out.documentFile = documentService.download(in.selectedFileName);' #txt
Cs0 f13 type ch.ivy.addon.portalkit.singleapp.cases.CaseItemDocument.CaseItemDocumentData #txt
Cs0 f13 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>get streamed content</name>
        <nameStyle>20,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f13 1206 172 36 24 20 -2 #rect
Cs0 f13 @|StepIcon #fIcon
Cs0 f14 expr out #txt
Cs0 f14 1224 107 1224 172 #arcP
Cs0 f15 expr out #txt
Cs0 f15 1224 196 1224 285 #arcP
Cs0 f12 guid 153A7B1032241C36 #txt
Cs0 f12 type ch.ivy.addon.portalkit.singleapp.cases.CaseItemDocument.CaseItemDocumentData #txt
Cs0 f12 method setDocumentToDelete(String) #txt
Cs0 f12 disableUIEvents false #txt
Cs0 f12 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<java.lang.String fileName> param = methodEvent.getInputArguments();
' #txt
Cs0 f12 inParameterMapAction 'out.selectedFileName=param.fileName;
' #txt
Cs0 f12 outParameterDecl '<> result;
' #txt
Cs0 f12 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>setDocumentToDelete(String)</name>
        <nameStyle>27,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f12 981 85 22 22 14 0 #rect
Cs0 f12 @|RichDialogMethodStartIcon #fIcon
Cs0 f18 type ch.ivy.addon.portalkit.singleapp.cases.CaseItemDocument.CaseItemDocumentData #txt
Cs0 f18 981 277 22 22 14 0 #rect
Cs0 f18 @|RichDialogProcessEndIcon #fIcon
Cs0 f19 expr out #txt
Cs0 f19 992 107 992 277 #arcP
Cs0 f10 actionDecl 'ch.ivy.addon.portalkit.singleapp.cases.CaseItemDocument.CaseItemDocumentData out;
' #txt
Cs0 f10 actionTable 'out=in;
' #txt
Cs0 f10 actionCode 'import ch.ivy.addon.portalkit.service.CaseDocumentService;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

CaseDocumentService documentService = new CaseDocumentService(in.internalCase.getId());
documentService.delete(in.selectedFileName);
in.selectedFileName = "";' #txt
Cs0 f10 type ch.ivy.addon.portalkit.singleapp.cases.CaseItemDocument.CaseItemDocumentData #txt
Cs0 f10 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>delete file</name>
        <nameStyle>11,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f10 254 180 36 24 20 -2 #rect
Cs0 f10 @|StepIcon #fIcon
Cs0 f16 expr out #txt
Cs0 f16 272 107 272 180 #arcP
Cs0 f21 actionDecl 'ch.ivy.addon.portalkit.singleapp.cases.CaseItemDocument.CaseItemDocumentData out;
' #txt
Cs0 f21 actionTable 'out=in;
' #txt
Cs0 f21 actionCode 'import ch.ivy.addon.portalkit.service.CaseDocumentService;

CaseDocumentService documentService = new CaseDocumentService(in.internalCase.getId());
in.fileAlreadyExists = documentService.doesDocumentExist(in.documentUploadEvent.getFile().getFileName());' #txt
Cs0 f21 type ch.ivy.addon.portalkit.singleapp.cases.CaseItemDocument.CaseItemDocumentData #txt
Cs0 f21 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>check exist</name>
        <nameStyle>11,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f21 542 436 36 24 23 -10 #rect
Cs0 f21 @|StepIcon #fIcon
Cs0 f29 type ch.ivy.addon.portalkit.singleapp.cases.CaseItemDocument.CaseItemDocumentData #txt
Cs0 f29 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>file valid?</name>
        <nameStyle>11,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f29 546 146 28 28 -66 -9 #rect
Cs0 f29 @|AlternativeIcon #fIcon
Cs0 f22 type ch.ivy.addon.portalkit.singleapp.cases.CaseItemDocument.CaseItemDocumentData #txt
Cs0 f22 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>file exists?</name>
        <nameStyle>12,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f22 546 498 28 28 20 -8 #rect
Cs0 f22 @|AlternativeIcon #fIcon
Cs0 f32 expr out #txt
Cs0 f32 560 460 560 498 #arcP
Cs0 f27 expr out #txt
Cs0 f27 560 107 560 146 #arcP
Cs0 f24 actionDecl 'ch.ivy.addon.portalkit.singleapp.cases.CaseItemDocument.CaseItemDocumentData out;
' #txt
Cs0 f24 actionTable 'out=in;
' #txt
Cs0 f24 actionCode 'import ch.ivy.addon.portalkit.service.CaseDocumentService;
import org.primefaces.context.RequestContext;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

CaseDocumentService documentService = new CaseDocumentService(in.internalCase.getId());
Boolean uploaded = documentService.upload(in.documentUploadEvent.getFile().getFileName(), in.documentUploadEvent.getFile().getInputstream());
if(!uploaded){
  FacesContext.getCurrentInstance().addMessage("upload-messages", new FacesMessage(FacesMessage.SEVERITY_ERROR, ivy.cms.co("/ch.ivy.addon.portalkit.ui.jsf/documentFiles/uploadFailed"),null));
} else {
 RequestContext.getCurrentInstance().update(in.clientId + ":case-details-documents");
 
}
' #txt
Cs0 f24 type ch.ivy.addon.portalkit.singleapp.cases.CaseItemDocument.CaseItemDocumentData #txt
Cs0 f24 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>upload file</name>
        <nameStyle>11,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f24 358 252 36 24 20 -2 #rect
Cs0 f24 @|StepIcon #fIcon
Cs0 f25 expr in #txt
Cs0 f25 546 512 376 276 #arcP
Cs0 f25 1 376 512 #addKink
Cs0 f25 1 0.02888732979171206 0 0 #arcLabel
Cs0 f38 actionDecl 'ch.ivy.addon.portalkit.singleapp.cases.CaseItemDocument.CaseItemDocumentData out;
' #txt
Cs0 f38 actionTable 'out=in;
' #txt
Cs0 f38 actionCode 'import org.primefaces.context.RequestContext;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

FacesContext context = FacesContext.getCurrentInstance();
context.addMessage("upload-messages", new FacesMessage(FacesMessage.SEVERITY_ERROR, ivy.cms.co("/ch.ivy.addon.portalkit.ui.jsf/documentFiles/uploadFileExists", [in.documentUploadEvent.getFile().getFileName()]),null));
RequestContext.getCurrentInstance().update(in.clientId + ":document-upload-form:upload-messages");' #txt
Cs0 f38 type ch.ivy.addon.portalkit.singleapp.cases.CaseItemDocument.CaseItemDocumentData #txt
Cs0 f38 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>show warning</name>
        <nameStyle>12,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f38 544 548 32 24 24 -7 #rect
Cs0 f38 @|StepIcon #fIcon
Cs0 f42 expr in #txt
Cs0 f42 outCond in.fileAlreadyExists #txt
Cs0 f42 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>yes</name>
        <nameStyle>3,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f42 560 526 560 548 #arcP
Cs0 f28 expr out #txt
Cs0 f28 560 572 560 597 #arcP
Cs0 f11 actionDecl 'ch.ivy.addon.portalkit.singleapp.cases.CaseItemDocument.CaseItemDocumentData out;
' #txt
Cs0 f11 actionTable 'out=in;
' #txt
Cs0 f11 actionCode 'in.internalCase = ivy.wf.findCase(in.caseId);' #txt
Cs0 f11 security system #txt
Cs0 f11 type ch.ivy.addon.portalkit.singleapp.cases.CaseItemDocument.CaseItemDocumentData #txt
Cs0 f11 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>get case</name>
        <nameStyle>8,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f11 38 132 36 24 20 -2 #rect
Cs0 f11 @|StepIcon #fIcon
Cs0 f30 expr out #txt
Cs0 f30 56 156 56 180 #arcP
Cs0 f17 expr out #txt
Cs0 f17 56 107 56 132 #arcP
Cs0 f1 actionDecl 'ch.ivy.addon.portalkit.singleapp.cases.CaseItemDocument.CaseItemDocumentData out;
' #txt
Cs0 f1 actionTable 'out=in;
' #txt
Cs0 f1 actionCode 'import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

FacesContext.getCurrentInstance().addMessage("upload-messages", new FacesMessage(FacesMessage.SEVERITY_ERROR, in.uploadDocumentCheckMessage, null));
' #txt
Cs0 f1 type ch.ivy.addon.portalkit.singleapp.cases.CaseItemDocument.CaseItemDocumentData #txt
Cs0 f1 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>show error msg</name>
        <nameStyle>14,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f1 700 460 40 24 -39 18 #rect
Cs0 f1 @|StepIcon #fIcon
Cs0 f34 expr out #txt
Cs0 f34 720 484 571 608 #arcP
Cs0 f34 1 720 608 #addKink
Cs0 f34 0 0.5629581565438773 0 -12 #arcLabel
Cs0 f54 type ch.ivy.addon.portalkit.singleapp.cases.CaseItemDocument.CaseItemDocumentData #txt
Cs0 f54 processCall 'Functional Processes/UploadDocumentChecker:call(org.primefaces.model.UploadedFile)' #txt
Cs0 f54 doCall true #txt
Cs0 f54 requestActionDecl '<org.primefaces.model.UploadedFile uploadFile> param;
' #txt
Cs0 f54 requestMappingAction 'param.uploadFile=in.documentUploadEvent.getFile();
' #txt
Cs0 f54 responseActionDecl 'ch.ivy.addon.portalkit.singleapp.cases.CaseItemDocument.CaseItemDocumentData out;
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
Cs0 f54 480 230 160 44 -71 -8 #rect
Cs0 f54 @|CallSubIcon #fIcon
Cs0 f35 expr in #txt
Cs0 f35 outCond 'in.documentUploadEvent.getFile() != null' #txt
Cs0 f35 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>yes</name>
        <nameStyle>3,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f35 560 174 560 230 #arcP
Cs0 f35 0 0.1 27 0 #arcLabel
Cs0 f40 type ch.ivy.addon.portalkit.singleapp.cases.CaseItemDocument.CaseItemDocumentData #txt
Cs0 f40 544 336 32 32 0 16 #rect
Cs0 f40 @|AlternativeIcon #fIcon
Cs0 f43 expr out #txt
Cs0 f43 560 274 560 336 #arcP
Cs0 f26 expr in #txt
Cs0 f26 outCond 'in.uploadDocumentCheckStatus == ch.ivy.addon.portalkit.enums.UploadDocumentCheckStatus.OK' #txt
Cs0 f26 560 368 560 436 #arcP
Cs0 f44 expr in #txt
Cs0 f44 576 352 720 460 #arcP
Cs0 f44 1 720 352 #addKink
Cs0 f44 0 0.9028056360469819 0 0 #arcLabel
Cs0 f62 actionDecl 'ch.ivy.addon.portalkit.singleapp.cases.CaseItemDocument.CaseItemDocumentData out;
' #txt
Cs0 f62 actionTable 'out=in;
' #txt
Cs0 f62 actionCode 'in.uploadDocumentCheckMessage = ivy.cms.co("/Dialogs/components/CaseDocument/invalidFileMessage");' #txt
Cs0 f62 type ch.ivy.addon.portalkit.singleapp.cases.CaseItemDocument.CaseItemDocumentData #txt
Cs0 f62 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Create error message</name>
    </language>
</elementInfo>
' #txt
Cs0 f62 656 229 128 44 -61 -8 #rect
Cs0 f62 @|StepIcon #fIcon
Cs0 f45 expr in #txt
Cs0 f45 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>no</name>
        <nameStyle>2,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f45 574 160 720 229 #arcP
Cs0 f45 1 720 160 #addKink
Cs0 f45 0 0.5629581565438773 0 -12 #arcLabel
Cs0 f2 expr out #txt
Cs0 f2 720 273 720 460 #arcP
Cs0 f68 actionDecl 'ch.ivy.addon.portalkit.singleapp.cases.CaseItemDocument.CaseItemDocumentData out;
' #txt
Cs0 f68 actionTable 'out=in;
' #txt
Cs0 f68 actionCode 'import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

FacesContext.getCurrentInstance().addMessage("", new FacesMessage( ivy.cms.co("/Dialogs/components/CaseDocument/deleteSucceed"), "" ));' #txt
Cs0 f68 type ch.ivy.addon.portalkit.singleapp.cases.CaseItemDocument.CaseItemDocumentData #txt
Cs0 f68 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Show success message</name>
        <nameStyle>20,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f68 168 180 32 24 -70 -31 #rect
Cs0 f68 @|StepIcon #fIcon
Cs0 f46 expr out #txt
Cs0 f46 254 192 200 192 #arcP
Cs0 f20 expr out #txt
Cs0 f20 168 192 74 192 #arcP
Cs0 f66 actionDecl 'ch.ivy.addon.portalkit.singleapp.cases.CaseItemDocument.CaseItemDocumentData out;
' #txt
Cs0 f66 actionTable 'out=in;
' #txt
Cs0 f66 actionCode 'import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

FacesContext.getCurrentInstance().addMessage("", new FacesMessage( ivy.cms.co("/Dialogs/components/CaseDocument/uploadSucceed"), "" ));' #txt
Cs0 f66 type ch.ivy.addon.portalkit.singleapp.cases.CaseItemDocument.CaseItemDocumentData #txt
Cs0 f66 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Show success message</name>
        <nameStyle>20,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f66 176 252 32 24 -60 17 #rect
Cs0 f66 @|StepIcon #fIcon
Cs0 f47 expr out #txt
Cs0 f47 358 264 208 264 #arcP
Cs0 f47 0 0.6065414273012237 0 0 #arcLabel
Cs0 f36 expr out #txt
Cs0 f36 176 264 74 202 #arcP
>Proto Cs0 .type ch.ivy.addon.portalkit.singleapp.cases.CaseItemDocument.CaseItemDocumentData #txt
>Proto Cs0 .processKind HTML_DIALOG #txt
>Proto Cs0 -8 -8 16 16 16 26 #rect
>Proto Cs0 '' #fIcon
Cs0 f3 mainOut f23 tail #connect
Cs0 f23 head f6 mainIn #connect
Cs0 f6 mainOut f5 tail #connect
Cs0 f5 head f4 mainIn #connect
Cs0 f39 mainOut f41 tail #connect
Cs0 f41 head f37 mainIn #connect
Cs0 f8 mainOut f14 tail #connect
Cs0 f14 head f13 mainIn #connect
Cs0 f13 mainOut f15 tail #connect
Cs0 f15 head f9 mainIn #connect
Cs0 f12 mainOut f19 tail #connect
Cs0 f19 head f18 mainIn #connect
Cs0 f31 mainOut f16 tail #connect
Cs0 f16 head f10 mainIn #connect
Cs0 f21 mainOut f32 tail #connect
Cs0 f32 head f22 in #connect
Cs0 f7 mainOut f27 tail #connect
Cs0 f27 head f29 in #connect
Cs0 f25 head f24 mainIn #connect
Cs0 f22 out f42 tail #connect
Cs0 f42 head f38 mainIn #connect
Cs0 f22 out f25 tail #connect
Cs0 f38 mainOut f28 tail #connect
Cs0 f28 head f33 mainIn #connect
Cs0 f11 mainOut f30 tail #connect
Cs0 f30 head f39 mainIn #connect
Cs0 f0 mainOut f17 tail #connect
Cs0 f17 head f11 mainIn #connect
Cs0 f1 mainOut f34 tail #connect
Cs0 f34 head f33 mainIn #connect
Cs0 f29 out f35 tail #connect
Cs0 f35 head f54 mainIn #connect
Cs0 f54 mainOut f43 tail #connect
Cs0 f43 head f40 in #connect
Cs0 f40 out f26 tail #connect
Cs0 f26 head f21 mainIn #connect
Cs0 f40 out f44 tail #connect
Cs0 f44 head f1 mainIn #connect
Cs0 f29 out f45 tail #connect
Cs0 f45 head f62 mainIn #connect
Cs0 f62 mainOut f2 tail #connect
Cs0 f2 head f1 mainIn #connect
Cs0 f10 mainOut f46 tail #connect
Cs0 f46 head f68 mainIn #connect
Cs0 f68 mainOut f20 tail #connect
Cs0 f20 head f39 mainIn #connect
Cs0 f24 mainOut f47 tail #connect
Cs0 f47 head f66 mainIn #connect
Cs0 f66 mainOut f36 tail #connect
Cs0 f36 head f39 mainIn #connect
