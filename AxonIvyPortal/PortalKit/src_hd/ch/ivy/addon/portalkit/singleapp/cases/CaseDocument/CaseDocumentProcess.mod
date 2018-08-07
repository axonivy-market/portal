[Ivy]
[>Created: Thu Nov 19 14:42:10 ICT 2015]
1511EB944BC67A1A 3.18 #module
>Proto >Proto Collection #zClass
Ds0 CaseDocumentProcess Big #zClass
Ds0 RD #cInfo
Ds0 #process
Ds0 @TextInP .ui2RdDataAction .ui2RdDataAction #zField
Ds0 @TextInP .rdData2UIAction .rdData2UIAction #zField
Ds0 @TextInP .resExport .resExport #zField
Ds0 @TextInP .type .type #zField
Ds0 @TextInP .processKind .processKind #zField
Ds0 @AnnotationInP-0n ai ai #zField
Ds0 @MessageFlowInP-0n messageIn messageIn #zField
Ds0 @MessageFlowOutP-0n messageOut messageOut #zField
Ds0 @TextInP .xml .xml #zField
Ds0 @TextInP .responsibility .responsibility #zField
Ds0 @RichDialogInitStart f0 '' #zField
Ds0 @RichDialogProcessEnd f1 '' #zField
Ds0 @RichDialogProcessEnd f4 '' #zField
Ds0 @GridStep f6 '' #zField
Ds0 @PushWFArc f7 '' #zField
Ds0 @PushWFArc f2 '' #zField
Ds0 @GridStep f8 '' #zField
Ds0 @RichDialogMethodStart f5 '' #zField
Ds0 @PushWFArc f11 '' #zField
Ds0 @RichDialogProcessEnd f13 '' #zField
Ds0 @RichDialogMethodStart f15 '' #zField
Ds0 @GridStep f16 '' #zField
Ds0 @PushWFArc f17 '' #zField
Ds0 @PushWFArc f19 '' #zField
Ds0 @GridStep f21 '' #zField
Ds0 @PushWFArc f23 '' #zField
Ds0 @RichDialogMethodStart f24 '' #zField
Ds0 @GridStep f26 '' #zField
Ds0 @PushWFArc f10 '' #zField
Ds0 @PushWFArc f28 '' #zField
Ds0 @RichDialogProcessEnd f25 '' #zField
Ds0 @RichDialogMethodStart f3 '' #zField
Ds0 @Alternative f31 '' #zField
Ds0 @PushWFArc f32 '' #zField
Ds0 @PushWFArc f20 '' #zField
Ds0 @PushWFArc f33 '' #zField
Ds0 @PushWFArc f30 '' #zField
Ds0 @PushWFArc f34 '' #zField
Ds0 @PushWFArc f9 '' #zField
Ds0 @Alternative f29 '' #zField
Ds0 @PushWFArc f14 '' #zField
Ds0 @RichDialogMethodStart f12 '' #zField
>Proto Ds0 Ds0 CaseDocumentProcess #zField
Ds0 f0 guid 14DD6789B76C9299 #txt
Ds0 f0 type ch.ivy.addon.portalkit.singleapp.cases.CaseDocument.CaseDocumentData #txt
Ds0 f0 method start(Number) #txt
Ds0 f0 disableUIEvents true #txt
Ds0 f0 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<java.lang.Number caseId> param = methodEvent.getInputArguments();
' #txt
Ds0 f0 inParameterMapAction 'out.caseId=param.caseId;
' #txt
Ds0 f0 outParameterDecl '<> result;
' #txt
Ds0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start(String)</name>
        <nameStyle>13,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ds0 f0 37 61 22 22 14 0 #rect
Ds0 f0 @|RichDialogInitStartIcon #fIcon
Ds0 f1 type ch.ivy.addon.portalkit.singleapp.cases.CaseDocument.CaseDocumentData #txt
Ds0 f1 37 309 22 22 14 0 #rect
Ds0 f1 @|RichDialogProcessEndIcon #fIcon
Ds0 f4 type ch.ivy.addon.portalkit.singleapp.cases.CaseDocument.CaseDocumentData #txt
Ds0 f4 821 221 22 22 14 0 #rect
Ds0 f4 @|RichDialogProcessEndIcon #fIcon
Ds0 f6 actionDecl 'ch.ivy.addon.portalkit.singleapp.cases.CaseDocument.CaseDocumentData out;
' #txt
Ds0 f6 actionTable 'out=in;
' #txt
Ds0 f6 actionCode 'import ch.ivy.addon.portalkit.service.CaseDocumentService;
import org.primefaces.model.UploadedFile;

CaseDocumentService documentService = new CaseDocumentService(in.caseId);
in.caseDocuments = documentService.getAll();
in.selectedFile = null;' #txt
Ds0 f6 type ch.ivy.addon.portalkit.singleapp.cases.CaseDocument.CaseDocumentData #txt
Ds0 f6 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>display files</name>
        <nameStyle>13,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ds0 f6 30 220 36 24 -37 -35 #rect
Ds0 f6 @|StepIcon #fIcon
Ds0 f7 expr out #txt
Ds0 f7 48 83 48 220 #arcP
Ds0 f2 expr out #txt
Ds0 f2 48 244 48 309 #arcP
Ds0 f8 actionDecl 'ch.ivy.addon.portalkit.singleapp.cases.CaseDocument.CaseDocumentData out;
' #txt
Ds0 f8 actionTable 'out=in;
' #txt
Ds0 f8 actionCode 'import ch.ivy.addon.portalkit.service.CaseDocumentService;

CaseDocumentService documentService = new CaseDocumentService(in.caseId);
in.uploadingFileExists = documentService.doesDocumentExist(in.selectedFile.getFileName());' #txt
Ds0 f8 type ch.ivy.addon.portalkit.singleapp.cases.CaseDocument.CaseDocumentData #txt
Ds0 f8 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>check exist</name>
        <nameStyle>11,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ds0 f8 406 164 36 24 23 -10 #rect
Ds0 f8 @|StepIcon #fIcon
Ds0 f5 guid 14DD74D612721654 #txt
Ds0 f5 type ch.ivy.addon.portalkit.singleapp.cases.CaseDocument.CaseDocumentData #txt
Ds0 f5 method downloadFile(String) #txt
Ds0 f5 disableUIEvents false #txt
Ds0 f5 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<java.lang.String fileName> param = methodEvent.getInputArguments();
' #txt
Ds0 f5 inActionCode 'import ch.ivy.addon.portalkit.service.CaseDocumentService;

CaseDocumentService documentService = new CaseDocumentService(out.caseId);
out.downloadFile = documentService.download(param.fileName);' #txt
Ds0 f5 outParameterDecl '<> result;
' #txt
Ds0 f5 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>downloadFile(String,String)</name>
        <nameStyle>27,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ds0 f5 821 61 22 22 14 0 #rect
Ds0 f5 @|RichDialogMethodStartIcon #fIcon
Ds0 f11 expr out #txt
Ds0 f11 832 83 832 221 #arcP
Ds0 f13 type ch.ivy.addon.portalkit.singleapp.cases.CaseDocument.CaseDocumentData #txt
Ds0 f13 661 221 22 22 14 0 #rect
Ds0 f13 @|RichDialogProcessEndIcon #fIcon
Ds0 f15 guid 14DD764191081C0E #txt
Ds0 f15 type ch.ivy.addon.portalkit.singleapp.cases.CaseDocument.CaseDocumentData #txt
Ds0 f15 method submitDelete() #txt
Ds0 f15 disableUIEvents false #txt
Ds0 f15 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<> param = methodEvent.getInputArguments();
' #txt
Ds0 f15 outParameterDecl '<> result;
' #txt
Ds0 f15 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>submitDelete()</name>
    </language>
</elementInfo>
' #txt
Ds0 f15 173 61 22 22 14 0 #rect
Ds0 f15 @|RichDialogMethodStartIcon #fIcon
Ds0 f16 actionDecl 'ch.ivy.addon.portalkit.singleapp.cases.CaseDocument.CaseDocumentData out;
' #txt
Ds0 f16 actionTable 'out=in;
' #txt
Ds0 f16 actionCode 'import ch.ivy.addon.portalkit.service.CaseDocumentService;
import org.primefaces.context.RequestContext;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

CaseDocumentService documentService = new CaseDocumentService(in.caseId);
documentService.delete(in.selectedFilePath);
in.selectedFilePath = "";' #txt
Ds0 f16 type ch.ivy.addon.portalkit.singleapp.cases.CaseDocument.CaseDocumentData #txt
Ds0 f16 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>delete file</name>
        <nameStyle>11,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ds0 f16 166 164 36 24 20 -2 #rect
Ds0 f16 @|StepIcon #fIcon
Ds0 f17 expr out #txt
Ds0 f17 184 83 184 164 #arcP
Ds0 f19 expr out #txt
Ds0 f19 166 176 66 232 #arcP
Ds0 f19 0 0.28645635858785107 0 0 #arcLabel
Ds0 f21 actionDecl 'ch.ivy.addon.portalkit.singleapp.cases.CaseDocument.CaseDocumentData out;
' #txt
Ds0 f21 actionTable 'out=in;
' #txt
Ds0 f21 actionCode 'import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

FacesContext context = FacesContext.getCurrentInstance();
context.addMessage("choose-uploading-file", new FacesMessage(FacesMessage.SEVERITY_ERROR, ivy.cms.co("/ch.ivy.addon.portalkit.ui.jsf/documentFiles/uploadFileExists", [in.selectedFile.getFileName()]),null));' #txt
Ds0 f21 type ch.ivy.addon.portalkit.singleapp.cases.CaseDocument.CaseDocumentData #txt
Ds0 f21 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>show warning</name>
        <nameStyle>12,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ds0 f21 406 308 36 24 24 -7 #rect
Ds0 f21 @|StepIcon #fIcon
Ds0 f23 expr out #txt
Ds0 f23 406 320 59 320 #arcP
Ds0 f23 0 0.2466712619605421 0 0 #arcLabel
Ds0 f24 guid 14DD7D57754BAFCF #txt
Ds0 f24 type ch.ivy.addon.portalkit.singleapp.cases.CaseDocument.CaseDocumentData #txt
Ds0 f24 method overrideFile() #txt
Ds0 f24 disableUIEvents false #txt
Ds0 f24 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<> param = methodEvent.getInputArguments();
' #txt
Ds0 f24 outParameterDecl '<> result;
' #txt
Ds0 f24 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>overrideFile()</name>
    </language>
</elementInfo>
' #txt
Ds0 f24 301 61 22 22 14 0 #rect
Ds0 f24 @|RichDialogMethodStartIcon #fIcon
Ds0 f26 actionDecl 'ch.ivy.addon.portalkit.singleapp.cases.CaseDocument.CaseDocumentData out;
' #txt
Ds0 f26 actionTable 'out=in;
' #txt
Ds0 f26 actionCode 'import ch.ivy.addon.portalkit.service.CaseDocumentService;
import org.primefaces.context.RequestContext;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

CaseDocumentService documentService = new CaseDocumentService(in.caseId);
Boolean uploaded = documentService.upload(in.selectedFile.getFileName(), in.selectedFile.getInputstream());
if(!uploaded){
  FacesContext.getCurrentInstance().addMessage("choose-uploading-file", new FacesMessage(FacesMessage.SEVERITY_ERROR, ivy.cms.co("/ch.ivy.addon.portalkit.ui.jsf/documentFiles/uploadFailed"),null));
}
' #txt
Ds0 f26 type ch.ivy.addon.portalkit.singleapp.cases.CaseDocument.CaseDocumentData #txt
Ds0 f26 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>upload file to folder</name>
        <nameStyle>21,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ds0 f26 294 220 36 24 -54 14 #rect
Ds0 f26 @|StepIcon #fIcon
Ds0 f10 expr out #txt
Ds0 f10 294 232 66 232 #arcP
Ds0 f28 expr out #txt
Ds0 f28 312 83 312 220 #arcP
Ds0 f28 0 0.7392162041749724 0 0 #arcLabel
Ds0 f25 type ch.ivy.addon.portalkit.singleapp.cases.CaseDocument.CaseDocumentData #txt
Ds0 f25 541 149 22 22 14 0 #rect
Ds0 f25 @|RichDialogProcessEndIcon #fIcon
Ds0 f3 guid 1511A1DC42BEBDB1 #txt
Ds0 f3 type ch.ivy.addon.portalkit.singleapp.cases.CaseDocument.CaseDocumentData #txt
Ds0 f3 method submitUpload(org.primefaces.event.FileUploadEvent) #txt
Ds0 f3 disableUIEvents false #txt
Ds0 f3 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<org.primefaces.event.FileUploadEvent event> param = methodEvent.getInputArguments();
' #txt
Ds0 f3 inParameterMapAction 'out.selectedFile=param.event.getFile();
' #txt
Ds0 f3 outParameterDecl '<> result;
' #txt
Ds0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>submitUpload(FileUploadEvent)</name>
        <nameStyle>29,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ds0 f3 413 61 22 22 14 0 #rect
Ds0 f3 @|RichDialogMethodStartIcon #fIcon
Ds0 f31 type ch.ivy.addon.portalkit.singleapp.cases.CaseDocument.CaseDocumentData #txt
Ds0 f31 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>file exists?</name>
        <nameStyle>12,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ds0 f31 410 218 28 28 20 -8 #rect
Ds0 f31 @|AlternativeIcon #fIcon
Ds0 f32 expr out #txt
Ds0 f32 424 188 424 218 #arcP
Ds0 f20 expr in #txt
Ds0 f20 outCond in.uploadingFileExists #txt
Ds0 f20 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>yes</name>
        <nameStyle>3,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ds0 f20 424 246 424 308 #arcP
Ds0 f20 0 0.5 12 0 #arcLabel
Ds0 f33 expr in #txt
Ds0 f33 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>no</name>
        <nameStyle>2,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ds0 f33 410 232 330 232 #arcP
Ds0 f33 0 0.4375 0 -9 #arcLabel
Ds0 f30 expr out #txt
Ds0 f30 424 83 424 106 #arcP
Ds0 f34 expr in #txt
Ds0 f34 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>no</name>
        <nameStyle>2,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ds0 f34 438 120 552 149 #arcP
Ds0 f34 1 552 120 #addKink
Ds0 f34 0 0.42334384858044166 1 -12 #arcLabel
Ds0 f9 expr in #txt
Ds0 f9 outCond 'in.selectedFile != null && in.selectedFile.getSize() > 0' #txt
Ds0 f9 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>yes</name>
        <nameStyle>3,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ds0 f9 424 134 424 164 #arcP
Ds0 f9 0 0.36666666666666664 13 0 #arcLabel
Ds0 f29 type ch.ivy.addon.portalkit.singleapp.cases.CaseDocument.CaseDocumentData #txt
Ds0 f29 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>file valid?</name>
        <nameStyle>11,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ds0 f29 410 106 28 28 -66 -9 #rect
Ds0 f29 @|AlternativeIcon #fIcon
Ds0 f14 expr out #txt
Ds0 f14 672 83 672 221 #arcP
Ds0 f12 guid 14DD7586B15ADC83 #txt
Ds0 f12 type ch.ivy.addon.portalkit.singleapp.cases.CaseDocument.CaseDocumentData #txt
Ds0 f12 method deleteFile(String) #txt
Ds0 f12 disableUIEvents false #txt
Ds0 f12 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<java.lang.String filePath> param = methodEvent.getInputArguments();
' #txt
Ds0 f12 inParameterMapAction 'out.selectedFilePath=param.filePath;
' #txt
Ds0 f12 inActionCode 'import org.primefaces.context.RequestContext;

RequestContext.getCurrentInstance().execute("PF(''deleteFileConfirmation'').show()");' #txt
Ds0 f12 outParameterDecl '<> result;
' #txt
Ds0 f12 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>deleteFile(String)</name>
        <nameStyle>18,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ds0 f12 661 61 22 22 14 0 #rect
Ds0 f12 @|RichDialogMethodStartIcon #fIcon
>Proto Ds0 .type ch.ivy.addon.portalkit.singleapp.cases.CaseDocument.CaseDocumentData #txt
>Proto Ds0 .processKind HTML_DIALOG #txt
>Proto Ds0 -8 -8 16 16 16 26 #rect
>Proto Ds0 '' #fIcon
Ds0 f0 mainOut f7 tail #connect
Ds0 f7 head f6 mainIn #connect
Ds0 f6 mainOut f2 tail #connect
Ds0 f2 head f1 mainIn #connect
Ds0 f5 mainOut f11 tail #connect
Ds0 f11 head f4 mainIn #connect
Ds0 f12 mainOut f14 tail #connect
Ds0 f14 head f13 mainIn #connect
Ds0 f15 mainOut f17 tail #connect
Ds0 f17 head f16 mainIn #connect
Ds0 f16 mainOut f19 tail #connect
Ds0 f19 head f6 mainIn #connect
Ds0 f21 mainOut f23 tail #connect
Ds0 f23 head f1 mainIn #connect
Ds0 f26 mainOut f10 tail #connect
Ds0 f10 head f6 mainIn #connect
Ds0 f24 mainOut f28 tail #connect
Ds0 f28 head f26 mainIn #connect
Ds0 f29 out f9 tail #connect
Ds0 f9 head f8 mainIn #connect
Ds0 f29 out f34 tail #connect
Ds0 f34 head f25 mainIn #connect
Ds0 f3 mainOut f30 tail #connect
Ds0 f30 head f29 in #connect
Ds0 f8 mainOut f32 tail #connect
Ds0 f32 head f31 in #connect
Ds0 f31 out f20 tail #connect
Ds0 f20 head f21 mainIn #connect
Ds0 f31 out f33 tail #connect
Ds0 f33 head f26 mainIn #connect
