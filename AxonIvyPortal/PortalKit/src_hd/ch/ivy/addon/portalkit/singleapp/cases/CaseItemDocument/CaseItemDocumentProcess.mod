[Ivy]
[>Created: Fri Mar 25 14:09:07 ICT 2016]
153A79700C35BE80 3.18 #module
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
Cs0 @PushWFArc f20 '' #zField
Cs0 @GridStep f21 '' #zField
Cs0 @Alternative f29 '' #zField
Cs0 @Alternative f22 '' #zField
Cs0 @PushWFArc f32 '' #zField
Cs0 @PushWFArc f26 '' #zField
Cs0 @PushWFArc f27 '' #zField
Cs0 @PushWFArc f35 '' #zField
Cs0 @GridStep f24 '' #zField
Cs0 @PushWFArc f25 '' #zField
Cs0 @PushWFArc f36 '' #zField
Cs0 @GridStep f38 '' #zField
Cs0 @PushWFArc f42 '' #zField
Cs0 @PushWFArc f28 '' #zField
Cs0 @GridStep f11 '' #zField
Cs0 @PushWFArc f30 '' #zField
Cs0 @PushWFArc f17 '' #zField
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
Cs0 f0 85 85 22 22 14 0 #rect
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
Cs0 f7 469 85 22 22 -57 16 #rect
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
Cs0 f31 221 85 22 22 12 -3 #rect
Cs0 f31 @|RichDialogMethodStartIcon #fIcon
Cs0 f33 type ch.ivy.addon.portalkit.singleapp.cases.CaseItemDocument.CaseItemDocumentData #txt
Cs0 f33 469 357 22 22 14 0 #rect
Cs0 f33 @|RichDialogProcessEndIcon #fIcon
Cs0 f3 guid 1534FDA4FDC9B40C #txt
Cs0 f3 type ch.ivy.addon.portalkit.singleapp.cases.CaseItemDocument.CaseItemDocumentData #txt
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
Cs0 f3 1149 85 22 22 14 0 #rect
Cs0 f3 @|RichDialogMethodStartIcon #fIcon
Cs0 f4 type ch.ivy.addon.portalkit.singleapp.cases.CaseItemDocument.CaseItemDocumentData #txt
Cs0 f4 1149 213 22 22 14 0 #rect
Cs0 f4 @|RichDialogProcessEndIcon #fIcon
Cs0 f6 actionDecl 'ch.ivy.addon.portalkit.singleapp.cases.CaseItemDocument.CaseItemDocumentData out;
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
Cs0 f6 type ch.ivy.addon.portalkit.singleapp.cases.CaseItemDocument.CaseItemDocumentData #txt
Cs0 f6 1142 180 36 24 20 -2 #rect
Cs0 f6 @|StepIcon #fIcon
Cs0 f23 expr out #txt
Cs0 f23 1160 107 1160 180 #arcP
Cs0 f5 expr out #txt
Cs0 f5 1160 204 1160 213 #arcP
Cs0 f37 type ch.ivy.addon.portalkit.singleapp.cases.CaseItemDocument.CaseItemDocumentData #txt
Cs0 f37 85 301 22 22 14 0 #rect
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
Cs0 f39 78 180 36 24 4 18 #rect
Cs0 f39 @|StepIcon #fIcon
Cs0 f41 expr out #txt
Cs0 f41 96 204 96 301 #arcP
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
Cs0 f8 933 85 22 22 14 0 #rect
Cs0 f8 @|RichDialogMethodStartIcon #fIcon
Cs0 f9 type ch.ivy.addon.portalkit.singleapp.cases.CaseItemDocument.CaseItemDocumentData #txt
Cs0 f9 933 285 22 22 14 0 #rect
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
Cs0 f13 926 172 36 24 20 -2 #rect
Cs0 f13 @|StepIcon #fIcon
Cs0 f14 expr out #txt
Cs0 f14 944 107 944 172 #arcP
Cs0 f15 expr out #txt
Cs0 f15 944 196 944 285 #arcP
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
Cs0 f12 701 85 22 22 14 0 #rect
Cs0 f12 @|RichDialogMethodStartIcon #fIcon
Cs0 f18 type ch.ivy.addon.portalkit.singleapp.cases.CaseItemDocument.CaseItemDocumentData #txt
Cs0 f18 701 277 22 22 14 0 #rect
Cs0 f18 @|RichDialogProcessEndIcon #fIcon
Cs0 f19 expr out #txt
Cs0 f19 712 107 712 277 #arcP
Cs0 f10 actionDecl 'ch.ivy.addon.portalkit.singleapp.cases.CaseItemDocument.CaseItemDocumentData out;
' #txt
Cs0 f10 actionTable 'out=in;
' #txt
Cs0 f10 actionCode 'import ch.ivy.addon.portalkit.service.CaseDocumentService;

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
Cs0 f10 214 180 36 24 20 -2 #rect
Cs0 f10 @|StepIcon #fIcon
Cs0 f16 expr out #txt
Cs0 f16 232 107 232 180 #arcP
Cs0 f20 expr out #txt
Cs0 f20 214 192 114 192 #arcP
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
Cs0 f21 462 204 36 24 23 -10 #rect
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
Cs0 f29 466 146 28 28 -66 -9 #rect
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
Cs0 f22 466 258 28 28 20 -8 #rect
Cs0 f22 @|AlternativeIcon #fIcon
Cs0 f32 expr out #txt
Cs0 f32 480 228 480 258 #arcP
Cs0 f26 expr in #txt
Cs0 f26 outCond 'in.documentUploadEvent.getFile() != null && in.documentUploadEvent.getFile().getSize() > 0' #txt
Cs0 f26 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>yes</name>
        <nameStyle>3,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f26 480 174 480 204 #arcP
Cs0 f26 0 0.36666666666666664 13 0 #arcLabel
Cs0 f27 expr out #txt
Cs0 f27 480 107 480 146 #arcP
Cs0 f35 expr in #txt
Cs0 f35 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>no</name>
        <nameStyle>2,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f35 494 160 491 368 #arcP
Cs0 f35 1 624 160 #addKink
Cs0 f35 2 624 368 #addKink
Cs0 f35 0 0.5629581565438773 0 -12 #arcLabel
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
Cs0 f24 358 260 36 24 20 -2 #rect
Cs0 f24 @|StepIcon #fIcon
Cs0 f25 expr in #txt
Cs0 f25 466 272 394 272 #arcP
Cs0 f36 expr out #txt
Cs0 f36 358 272 114 192 #arcP
Cs0 f36 0 0.6065414273012237 0 0 #arcLabel
Cs0 f38 actionDecl 'ch.ivy.addon.portalkit.singleapp.cases.CaseItemDocument.CaseItemDocumentData out;
' #txt
Cs0 f38 actionTable 'out=in;
' #txt
Cs0 f38 actionCode 'import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

FacesContext context = FacesContext.getCurrentInstance();
context.addMessage("upload-messages", new FacesMessage(FacesMessage.SEVERITY_ERROR, ivy.cms.co("/ch.ivy.addon.portalkit.ui.jsf/documentFiles/uploadFileExists", [in.documentUploadEvent.getFile().getFileName()]),null));' #txt
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
Cs0 f38 464 308 32 24 24 -7 #rect
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
Cs0 f42 480 286 480 308 #arcP
Cs0 f28 expr out #txt
Cs0 f28 480 332 480 357 #arcP
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
Cs0 f11 78 132 36 24 20 -2 #rect
Cs0 f11 @|StepIcon #fIcon
Cs0 f30 expr out #txt
Cs0 f30 96 156 96 180 #arcP
Cs0 f17 expr out #txt
Cs0 f17 96 107 96 132 #arcP
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
Cs0 f10 mainOut f20 tail #connect
Cs0 f20 head f39 mainIn #connect
Cs0 f29 out f26 tail #connect
Cs0 f26 head f21 mainIn #connect
Cs0 f21 mainOut f32 tail #connect
Cs0 f32 head f22 in #connect
Cs0 f7 mainOut f27 tail #connect
Cs0 f27 head f29 in #connect
Cs0 f29 out f35 tail #connect
Cs0 f35 head f33 mainIn #connect
Cs0 f25 head f24 mainIn #connect
Cs0 f24 mainOut f36 tail #connect
Cs0 f36 head f39 mainIn #connect
Cs0 f22 out f42 tail #connect
Cs0 f42 head f38 mainIn #connect
Cs0 f22 out f25 tail #connect
Cs0 f38 mainOut f28 tail #connect
Cs0 f28 head f33 mainIn #connect
Cs0 f11 mainOut f30 tail #connect
Cs0 f30 head f39 mainIn #connect
Cs0 f0 mainOut f17 tail #connect
Cs0 f17 head f11 mainIn #connect
