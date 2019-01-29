[Ivy]
153361016FE4213A 3.23 #module
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
Cs0 @Alternative f35 '' #zField
Cs0 @CallSub f54 '' #zField
Cs0 @PushWFArc f13 '' #zField
Cs0 @Alternative f59 '' #zField
Cs0 @PushWFArc f60 '' #zField
Cs0 @GridStep f64 '' #zField
Cs0 @GridStep f66 '' #zField
Cs0 @PushWFArc f55 '' #zField
Cs0 @RichDialogInitStart f0 '' #zField
Cs0 @PushWFArc f1 '' #zField
Cs0 @RichDialogProcessEnd f2 '' #zField
Cs0 @PushWFArc f62 '' #zField
Cs0 @RichDialogMethodStart f63 '' #zField
Cs0 @RichDialogProcessEnd f69 '' #zField
Cs0 @RichDialogMethodStart f7 '' #zField
Cs0 @PushWFArc f39 '' #zField
Cs0 @GridStep f40 '' #zField
Cs0 @GridStep f9 '' #zField
Cs0 @RichDialogProcessEnd f15 '' #zField
Cs0 @PushWFArc f17 '' #zField
Cs0 @Alternative f19 '' #zField
Cs0 @PushWFArc f29 '' #zField
Cs0 @PushWFArc f30 '' #zField
Cs0 @RichDialogProcessEnd f51 '' #zField
Cs0 @RichDialogMethodStart f8 '' #zField
Cs0 @GridStep f20 '' #zField
Cs0 @PushWFArc f22 '' #zField
Cs0 @RichDialogProcessEnd f24 '' #zField
Cs0 @PushWFArc f25 '' #zField
Cs0 @RichDialogMethodStart f3 '' #zField
Cs0 @GridStep f12 '' #zField
Cs0 @PushWFArc f18 '' #zField
Cs0 @RichDialogProcessEnd f21 '' #zField
Cs0 @PushWFArc f23 '' #zField
Cs0 @RichDialogMethodStart f4 '' #zField
Cs0 @GridStep f5 '' #zField
Cs0 @PushWFArc f6 '' #zField
Cs0 @RichDialogProcessEnd f10 '' #zField
Cs0 @GridStep f26 '' #zField
Cs0 @PushWFArc f27 '' #zField
Cs0 @GridStep f32 '' #zField
Cs0 @Alternative f28 '' #zField
Cs0 @PushWFArc f33 '' #zField
Cs0 @PushWFArc f14 '' #zField
Cs0 @Alternative f36 '' #zField
Cs0 @PushWFArc f11 '' #zField
Cs0 @PushWFArc f42 '' #zField
Cs0 @GridStep f43 '' #zField
Cs0 @PushWFArc f44 '' #zField
Cs0 @Alternative f47 '' #zField
Cs0 @PushWFArc f48 '' #zField
Cs0 @PushWFArc f45 '' #zField
Cs0 @Alternative f57 '' #zField
Cs0 @PushWFArc f58 '' #zField
Cs0 @PushWFArc f38 '' #zField
Cs0 @PushWFArc f61 '' #zField
Cs0 @PushWFArc f31 '' #zField
Cs0 @PushWFArc f34 '' #zField
Cs0 @GridStep f37 '' #zField
Cs0 @PushWFArc f41 '' #zField
Cs0 @PushWFArc f16 '' #zField
>Proto Cs0 Cs0 CaseItemDocumentProcess #zField
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
Cs0 f35 370 274 28 28 -30 -34 #rect
Cs0 f35 @|AlternativeIcon #fIcon
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
Cs0 f54 448 266 160 44 -71 -8 #rect
Cs0 f54 @|CallSubIcon #fIcon
Cs0 f13 expr in #txt
Cs0 f13 outCond org.apache.commons.lang.StringUtils.isBlank(in.uploadDocumentCheckMessage) #txt
Cs0 f13 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>YES</name>
        <nameStyle>3
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f13 398 288 448 288 #arcP
Cs0 f13 0 0.26 0 -11 #arcLabel
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
Cs0 f59 656 272 32 32 -54 -37 #rect
Cs0 f59 @|AlternativeIcon #fIcon
Cs0 f60 expr out #txt
Cs0 f60 608 288 656 288 #arcP
Cs0 f60 0 0.9770034044244577 0 0 #arcLabel
Cs0 f64 actionDecl 'ch.ivy.addon.portalkit.component.CaseItemDocument.CaseItemDocumentData out;
' #txt
Cs0 f64 actionTable 'out=in;
' #txt
Cs0 f64 actionCode 'import ch.ivy.addon.portalkit.enums.GlobalVariable;
import ch.ivy.addon.portalkit.service.GlobalSettingService;

GlobalSettingService globalSettingService = new GlobalSettingService();
String isHideUploadDocumentForDoneCase =
        globalSettingService.findGlobalSettingValue(GlobalVariable.HIDE_UPLOAD_DOCUMENT_FOR_DONE_CASE.toString());
in.isHideUploadDocumentForDoneCase = Boolean.parseBoolean(isHideUploadDocumentForDoneCase);' #txt
Cs0 f64 type ch.ivy.addon.portalkit.component.CaseItemDocument.CaseItemDocumentData #txt
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
Cs0 f66 actionDecl 'ch.ivy.addon.portalkit.component.CaseItemDocument.CaseItemDocumentData out;
' #txt
Cs0 f66 actionTable 'out=in;
' #txt
Cs0 f66 actionCode 'import ch.ivy.addon.portalkit.enums.UploadDocumentCheckStatus;
import org.apache.commons.io.FileUtils;
import java.util.Arrays;
import ch.ivy.addon.portalkit.masterdata.MasterData;

in.uploadDocumentCheckMessage = "";
in.uploadDocumentCheckStatus = UploadDocumentCheckStatus.OK;

if (in.documentUploadEvent.getFile() == null || in.documentUploadEvent.getFile().getSize() == 0) {
	in.uploadDocumentCheckMessage = ivy.cms.co("/Dialogs/components/CaseDocument/invalidFileMessage");
} else {
	Long maxFileUploadSize = MasterData.getFileUploadSizeLimit();
	if (in.documentUploadEvent.getFile().getSize() > maxFileUploadSize) {
		in.uploadDocumentCheckMessage = ivy.cms.co("/ch.ivy.addon.portalkit.ui.jsf/common/errorFileUploadSize", Arrays.asList(FileUtils.byteCountToDisplaySize(maxFileUploadSize)));
	}
}' #txt
Cs0 f66 type ch.ivy.addon.portalkit.component.CaseItemDocument.CaseItemDocumentData #txt
Cs0 f66 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Validate</name>
    </language>
</elementInfo>
' #txt
Cs0 f66 208 266 112 44 -22 -8 #rect
Cs0 f66 @|StepIcon #fIcon
Cs0 f55 expr out #txt
Cs0 f55 320 288 370 288 #arcP
Cs0 f0 guid 167E9DEBBFA8A8ED #txt
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
Cs0 f0 83 83 26 26 -16 15 #rect
Cs0 f0 @|RichDialogInitStartIcon #fIcon
Cs0 f1 expr out #txt
Cs0 f1 109 96 176 96 #arcP
Cs0 f2 type ch.ivy.addon.portalkit.component.CaseItemDocument.CaseItemDocumentData #txt
Cs0 f2 371 83 26 26 0 12 #rect
Cs0 f2 @|RichDialogProcessEndIcon #fIcon
Cs0 f62 expr out #txt
Cs0 f62 288 96 371 96 #arcP
Cs0 f63 guid 167E9DF0DE4FEF32 #txt
Cs0 f63 type ch.ivy.addon.portalkit.component.CaseItemDocument.CaseItemDocumentData #txt
Cs0 f63 method initData(ch.ivyteam.ivy.workflow.ICase) #txt
Cs0 f63 disableUIEvents false #txt
Cs0 f63 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<ch.ivyteam.ivy.workflow.ICase iCase> param = methodEvent.getInputArguments();
' #txt
Cs0 f63 inParameterMapAction 'out.iCase=param.iCase;
' #txt
Cs0 f63 outParameterDecl '<> result;
' #txt
Cs0 f63 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>initData(ICase)</name>
    </language>
</elementInfo>
' #txt
Cs0 f63 83 179 26 26 -41 15 #rect
Cs0 f63 @|RichDialogMethodStartIcon #fIcon
Cs0 f69 type ch.ivy.addon.portalkit.component.CaseItemDocument.CaseItemDocumentData #txt
Cs0 f69 371 179 26 26 0 12 #rect
Cs0 f69 @|RichDialogProcessEndIcon #fIcon
Cs0 f7 guid 167E9E558A0D7CF1 #txt
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
    </language>
</elementInfo>
' #txt
Cs0 f7 83 275 26 26 -75 18 #rect
Cs0 f7 @|RichDialogMethodStartIcon #fIcon
Cs0 f39 expr out #txt
Cs0 f39 109 288 208 288 #arcP
Cs0 f40 actionDecl 'ch.ivy.addon.portalkit.component.CaseItemDocument.CaseItemDocumentData out;
' #txt
Cs0 f40 actionTable 'out=in;
' #txt
Cs0 f40 actionCode 'import ch.ivy.addon.portalkit.service.CaseDocumentService;
import ch.ivy.addon.portalkit.enums.UploadDocumentCheckStatus;
import java.util.Arrays;
import org.primefaces.model.UploadedFile;

out.uploadDocumentCheckMessage = "";
out.uploadDocumentCheckStatus = UploadDocumentCheckStatus.OK;
UploadedFile uploadedFile = in.documentUploadEvent.getFile();
boolean isUploaded = CaseDocumentService.newInstance(in.iCase).upload(in.name, uploadedFile.getInputstream());

if (isUploaded) {	
	String note = ivy.cms.co("/ch.ivy.addon.portalkit.ui.jsf/documentFiles/uploadDocumentNote", Arrays.asList(ivy.session.getSessionUserName(), in.name));
	in.iCase.createNote(ivy.session, note);
} else {
	out.uploadDocumentCheckMessage = ivy.cms.co("/ch.ivy.addon.portalkit.ui.jsf/documentFiles/uploadFailed");
	out.uploadDocumentCheckStatus = UploadDocumentCheckStatus.FAIL;
}' #txt
Cs0 f40 security system #txt
Cs0 f40 type ch.ivy.addon.portalkit.component.CaseItemDocument.CaseItemDocumentData #txt
Cs0 f40 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Upload document&#xD;
and create note</name>
    </language>
</elementInfo>
' #txt
Cs0 f40 1008 266 128 44 -44 -16 #rect
Cs0 f40 @|StepIcon #fIcon
Cs0 f9 actionDecl 'ch.ivy.addon.portalkit.component.CaseItemDocument.CaseItemDocumentData out;
' #txt
Cs0 f9 actionTable 'out=in;
' #txt
Cs0 f9 actionCode 'import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, in.uploadDocumentCheckMessage, null));' #txt
Cs0 f9 type ch.ivy.addon.portalkit.component.CaseItemDocument.CaseItemDocumentData #txt
Cs0 f9 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Show error message</name>
    </language>
</elementInfo>
' #txt
Cs0 f9 1264 362 128 44 -58 -8 #rect
Cs0 f9 @|StepIcon #fIcon
Cs0 f15 type ch.ivy.addon.portalkit.component.CaseItemDocument.CaseItemDocumentData #txt
Cs0 f15 1427 371 26 26 0 12 #rect
Cs0 f15 @|RichDialogProcessEndIcon #fIcon
Cs0 f17 expr out #txt
Cs0 f17 1392 384 1427 384 #arcP
Cs0 f19 type ch.ivy.addon.portalkit.component.CaseItemDocument.CaseItemDocumentData #txt
Cs0 f19 656 368 32 32 0 16 #rect
Cs0 f19 @|AlternativeIcon #fIcon
Cs0 f29 expr in #txt
Cs0 f29 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>NO</name>
        <nameStyle>2
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f29 384 302 656 384 #arcP
Cs0 f29 1 384 384 #addKink
Cs0 f29 1 0.3194444444444444 0 -10 #arcLabel
Cs0 f30 expr in #txt
Cs0 f30 672 304 672 368 #arcP
Cs0 f51 type ch.ivy.addon.portalkit.component.CaseItemDocument.CaseItemDocumentData #txt
Cs0 f51 1427 275 26 26 0 12 #rect
Cs0 f51 @|RichDialogProcessEndIcon #fIcon
Cs0 f8 guid 1680301B2BFFF8D6 #txt
Cs0 f8 type ch.ivy.addon.portalkit.component.CaseItemDocument.CaseItemDocumentData #txt
Cs0 f8 method downloadDocument(ch.ivyteam.ivy.workflow.document.IDocument) #txt
Cs0 f8 disableUIEvents false #txt
Cs0 f8 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<ch.ivyteam.ivy.workflow.document.IDocument document> param = methodEvent.getInputArguments();
' #txt
Cs0 f8 inParameterMapAction 'out.document=param.document;
' #txt
Cs0 f8 outParameterDecl '<org.primefaces.model.StreamedContent streamedContent> result;
' #txt
Cs0 f8 outParameterMapAction 'result.streamedContent=in.streamedContent;
' #txt
Cs0 f8 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>downloadDocument(IDocument)</name>
    </language>
</elementInfo>
' #txt
Cs0 f8 83 435 26 26 -91 17 #rect
Cs0 f8 @|RichDialogMethodStartIcon #fIcon
Cs0 f20 actionDecl 'ch.ivy.addon.portalkit.component.CaseItemDocument.CaseItemDocumentData out;
' #txt
Cs0 f20 actionTable 'out=in;
' #txt
Cs0 f20 actionCode 'import ch.ivy.addon.portalkit.service.CaseDocumentService;

out.streamedContent = CaseDocumentService.newInstance(in.iCase).download(in.document);' #txt
Cs0 f20 type ch.ivy.addon.portalkit.component.CaseItemDocument.CaseItemDocumentData #txt
Cs0 f20 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Download document</name>
    </language>
</elementInfo>
' #txt
Cs0 f20 192 426 128 44 -57 -8 #rect
Cs0 f20 @|StepIcon #fIcon
Cs0 f22 expr out #txt
Cs0 f22 109 448 192 448 #arcP
Cs0 f24 type ch.ivy.addon.portalkit.component.CaseItemDocument.CaseItemDocumentData #txt
Cs0 f24 403 435 26 26 0 12 #rect
Cs0 f24 @|RichDialogProcessEndIcon #fIcon
Cs0 f25 expr out #txt
Cs0 f25 320 448 403 448 #arcP
Cs0 f3 guid 168030614DDB1EA9 #txt
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
Cs0 f3 83 531 26 26 -69 15 #rect
Cs0 f3 @|RichDialogMethodStartIcon #fIcon
Cs0 f12 actionDecl 'ch.ivy.addon.portalkit.component.CaseItemDocument.CaseItemDocumentData out;
' #txt
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
Cs0 f12 type ch.ivy.addon.portalkit.component.CaseItemDocument.CaseItemDocumentData #txt
Cs0 f12 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Reset messages</name>
    </language>
</elementInfo>
' #txt
Cs0 f12 200 522 112 44 -48 -8 #rect
Cs0 f12 @|StepIcon #fIcon
Cs0 f18 expr out #txt
Cs0 f18 109 544 200 544 #arcP
Cs0 f21 type ch.ivy.addon.portalkit.component.CaseItemDocument.CaseItemDocumentData #txt
Cs0 f21 403 531 26 26 0 12 #rect
Cs0 f21 @|RichDialogProcessEndIcon #fIcon
Cs0 f23 expr out #txt
Cs0 f23 312 544 403 544 #arcP
Cs0 f4 guid 168030735CFC1630 #txt
Cs0 f4 type ch.ivy.addon.portalkit.component.CaseItemDocument.CaseItemDocumentData #txt
Cs0 f4 method deleteDocument() #txt
Cs0 f4 disableUIEvents false #txt
Cs0 f4 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<> param = methodEvent.getInputArguments();
' #txt
Cs0 f4 outParameterDecl '<> result;
' #txt
Cs0 f4 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>deleteDocument()</name>
    </language>
</elementInfo>
' #txt
Cs0 f4 83 627 26 26 -49 15 #rect
Cs0 f4 @|RichDialogMethodStartIcon #fIcon
Cs0 f5 actionDecl 'ch.ivy.addon.portalkit.component.CaseItemDocument.CaseItemDocumentData out;
' #txt
Cs0 f5 actionTable 'out=in;
' #txt
Cs0 f5 actionCode 'import java.util.Arrays;

in.iCase.documents().delete(in.document);

String note = ivy.cms.co("/ch.ivy.addon.portalkit.ui.jsf/documentFiles/deleteDocumentNote", Arrays.asList(ivy.session.getSessionUserName(), in.document.getName()));
in.iCase.createNote(ivy.session, note);' #txt
Cs0 f5 security system #txt
Cs0 f5 type ch.ivy.addon.portalkit.component.CaseItemDocument.CaseItemDocumentData #txt
Cs0 f5 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Delete document&#xD;
and create note</name>
    </language>
</elementInfo>
' #txt
Cs0 f5 192 618 128 44 -43 -16 #rect
Cs0 f5 @|StepIcon #fIcon
Cs0 f6 expr out #txt
Cs0 f6 109 640 192 640 #arcP
Cs0 f10 type ch.ivy.addon.portalkit.component.CaseItemDocument.CaseItemDocumentData #txt
Cs0 f10 531 627 26 26 0 12 #rect
Cs0 f10 @|RichDialogProcessEndIcon #fIcon
Cs0 f26 actionDecl 'ch.ivy.addon.portalkit.component.CaseItemDocument.CaseItemDocumentData out;
' #txt
Cs0 f26 actionTable 'out=in;
' #txt
Cs0 f26 actionCode 'import ch.ivy.addon.portalkit.service.CaseDocumentService;

out.documents = CaseDocumentService.newInstance(in.iCase).getAll();' #txt
Cs0 f26 type ch.ivy.addon.portalkit.component.CaseItemDocument.CaseItemDocumentData #txt
Cs0 f26 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Find all documents</name>
    </language>
</elementInfo>
' #txt
Cs0 f26 168 170 112 44 -52 -8 #rect
Cs0 f26 @|StepIcon #fIcon
Cs0 f27 expr out #txt
Cs0 f27 109 192 168 192 #arcP
Cs0 f32 actionDecl 'ch.ivy.addon.portalkit.component.CaseItemDocument.CaseItemDocumentData out;
' #txt
Cs0 f32 actionTable 'out=in;
' #txt
Cs0 f32 actionCode 'import ch.ivy.addon.portalkit.service.CaseDocumentService;

out.documents = CaseDocumentService.newInstance(in.iCase).getAll();' #txt
Cs0 f32 type ch.ivy.addon.portalkit.component.CaseItemDocument.CaseItemDocumentData #txt
Cs0 f32 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Find all documents</name>
    </language>
</elementInfo>
' #txt
Cs0 f32 1272 266 112 44 -52 -8 #rect
Cs0 f32 @|StepIcon #fIcon
Cs0 f28 type ch.ivy.addon.portalkit.component.CaseItemDocument.CaseItemDocumentData #txt
Cs0 f28 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Upload successfully?</name>
    </language>
</elementInfo>
' #txt
Cs0 f28 1184 272 32 32 -54 -39 #rect
Cs0 f28 @|AlternativeIcon #fIcon
Cs0 f33 expr out #txt
Cs0 f33 1136 288 1184 288 #arcP
Cs0 f14 expr in #txt
Cs0 f14 outCond 'in.uploadDocumentCheckStatus == ch.ivy.addon.portalkit.enums.UploadDocumentCheckStatus.OK' #txt
Cs0 f14 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>YES</name>
        <nameStyle>3
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f14 1216 288 1272 288 #arcP
Cs0 f14 0 0.2857142857142857 0 -7 #arcLabel
Cs0 f36 type ch.ivy.addon.portalkit.component.CaseItemDocument.CaseItemDocumentData #txt
Cs0 f36 1184 368 32 32 0 16 #rect
Cs0 f36 @|AlternativeIcon #fIcon
Cs0 f11 expr in #txt
Cs0 f11 1216 384 1264 384 #arcP
Cs0 f11 0 0.3194444444444444 0 -10 #arcLabel
Cs0 f42 expr in #txt
Cs0 f42 1200 304 1200 368 #arcP
Cs0 f43 actionDecl 'ch.ivy.addon.portalkit.component.CaseItemDocument.CaseItemDocumentData out;
' #txt
Cs0 f43 actionTable 'out=in;
' #txt
Cs0 f43 actionCode 'import ch.ivy.addon.portalkit.enums.UploadDocumentCheckStatus;
import org.primefaces.model.UploadedFile;
import ch.ivy.addon.portalkit.service.CaseDocumentService;

UploadedFile uploadedFile = in.documentUploadEvent.getFile();
out.name = uploadedFile.getFileName();
boolean doesDocumentExist = CaseDocumentService.newInstance(in.iCase).doesDocumentExist(out.name);

if (doesDocumentExist) {
	out.uploadDocumentCheckMessage = ivy.cms.co("/ch.ivy.addon.portalkit.ui.jsf/documentFiles/uploadFileExists", [in.name]);
	out.uploadDocumentCheckStatus = UploadDocumentCheckStatus.FAIL;
}' #txt
Cs0 f43 security system #txt
Cs0 f43 type ch.ivy.addon.portalkit.component.CaseItemDocument.CaseItemDocumentData #txt
Cs0 f43 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Check exist</name>
    </language>
</elementInfo>
' #txt
Cs0 f43 744 266 112 44 -31 -8 #rect
Cs0 f43 @|StepIcon #fIcon
Cs0 f44 expr in #txt
Cs0 f44 outCond 'in.uploadDocumentCheckStatus == ch.ivy.addon.portalkit.enums.UploadDocumentCheckStatus.OK' #txt
Cs0 f44 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>OK</name>
        <nameStyle>2,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f44 688 288 744 288 #arcP
Cs0 f44 0 0.3333333333333333 0 -9 #arcLabel
Cs0 f47 type ch.ivy.addon.portalkit.component.CaseItemDocument.CaseItemDocumentData #txt
Cs0 f47 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Not exist?</name>
    </language>
</elementInfo>
' #txt
Cs0 f47 912 272 32 32 -27 -34 #rect
Cs0 f47 @|AlternativeIcon #fIcon
Cs0 f48 expr out #txt
Cs0 f48 856 288 912 288 #arcP
Cs0 f48 0 0.3333333333333333 0 -9 #arcLabel
Cs0 f45 expr in #txt
Cs0 f45 outCond 'in.uploadDocumentCheckStatus == ch.ivy.addon.portalkit.enums.UploadDocumentCheckStatus.OK' #txt
Cs0 f45 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>YES</name>
        <nameStyle>3
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f45 944 288 1008 288 #arcP
Cs0 f45 0 0.3333333333333333 0 -9 #arcLabel
Cs0 f57 type ch.ivy.addon.portalkit.component.CaseItemDocument.CaseItemDocumentData #txt
Cs0 f57 912 368 32 32 0 16 #rect
Cs0 f57 @|AlternativeIcon #fIcon
Cs0 f58 expr in #txt
Cs0 f58 688 384 912 384 #arcP
Cs0 f58 0 0.3194444444444444 0 -10 #arcLabel
Cs0 f38 expr in #txt
Cs0 f38 944 384 1184 384 #arcP
Cs0 f38 0 0.3194444444444444 0 -10 #arcLabel
Cs0 f61 expr in #txt
Cs0 f61 928 304 928 368 #arcP
Cs0 f61 0 0.6247201550720135 0 0 #arcLabel
Cs0 f31 expr out #txt
Cs0 f31 280 192 371 192 #arcP
Cs0 f34 expr out #txt
Cs0 f34 1384 288 1427 288 #arcP
Cs0 f37 actionDecl 'ch.ivy.addon.portalkit.component.CaseItemDocument.CaseItemDocumentData out;
' #txt
Cs0 f37 actionTable 'out=in;
' #txt
Cs0 f37 actionCode 'import ch.ivy.addon.portalkit.service.CaseDocumentService;

out.documents = CaseDocumentService.newInstance(in.iCase).getAll();' #txt
Cs0 f37 type ch.ivy.addon.portalkit.component.CaseItemDocument.CaseItemDocumentData #txt
Cs0 f37 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Find all documents</name>
    </language>
</elementInfo>
' #txt
Cs0 f37 368 618 112 44 -52 -8 #rect
Cs0 f37 @|StepIcon #fIcon
Cs0 f41 expr out #txt
Cs0 f41 320 640 368 640 #arcP
Cs0 f16 expr out #txt
Cs0 f16 480 640 531 640 #arcP
>Proto Cs0 .type ch.ivy.addon.portalkit.component.CaseItemDocument.CaseItemDocumentData #txt
>Proto Cs0 .processKind HTML_DIALOG #txt
>Proto Cs0 -8 -8 16 16 16 26 #rect
>Proto Cs0 '' #fIcon
Cs0 f35 out f13 tail #connect
Cs0 f13 head f54 mainIn #connect
Cs0 f54 mainOut f60 tail #connect
Cs0 f60 head f59 in #connect
Cs0 f66 mainOut f55 tail #connect
Cs0 f55 head f35 in #connect
Cs0 f0 mainOut f1 tail #connect
Cs0 f1 head f64 mainIn #connect
Cs0 f64 mainOut f62 tail #connect
Cs0 f62 head f2 mainIn #connect
Cs0 f7 mainOut f39 tail #connect
Cs0 f39 head f66 mainIn #connect
Cs0 f9 mainOut f17 tail #connect
Cs0 f17 head f15 mainIn #connect
Cs0 f35 out f29 tail #connect
Cs0 f29 head f19 in #connect
Cs0 f30 head f19 in #connect
Cs0 f8 mainOut f22 tail #connect
Cs0 f22 head f20 mainIn #connect
Cs0 f20 mainOut f25 tail #connect
Cs0 f25 head f24 mainIn #connect
Cs0 f3 mainOut f18 tail #connect
Cs0 f18 head f12 mainIn #connect
Cs0 f12 mainOut f23 tail #connect
Cs0 f23 head f21 mainIn #connect
Cs0 f4 mainOut f6 tail #connect
Cs0 f6 head f5 mainIn #connect
Cs0 f63 mainOut f27 tail #connect
Cs0 f27 head f26 mainIn #connect
Cs0 f40 mainOut f33 tail #connect
Cs0 f33 head f28 in #connect
Cs0 f28 out f14 tail #connect
Cs0 f14 head f32 mainIn #connect
Cs0 f36 out f11 tail #connect
Cs0 f11 head f9 mainIn #connect
Cs0 f28 out f42 tail #connect
Cs0 f42 head f36 in #connect
Cs0 f59 out f44 tail #connect
Cs0 f44 head f43 mainIn #connect
Cs0 f59 out f30 tail #connect
Cs0 f43 mainOut f48 tail #connect
Cs0 f48 head f47 in #connect
Cs0 f47 out f45 tail #connect
Cs0 f45 head f40 mainIn #connect
Cs0 f19 out f58 tail #connect
Cs0 f58 head f57 in #connect
Cs0 f57 out f38 tail #connect
Cs0 f38 head f36 in #connect
Cs0 f47 out f61 tail #connect
Cs0 f61 head f57 in #connect
Cs0 f26 mainOut f31 tail #connect
Cs0 f31 head f69 mainIn #connect
Cs0 f32 mainOut f34 tail #connect
Cs0 f34 head f51 mainIn #connect
Cs0 f5 mainOut f41 tail #connect
Cs0 f41 head f37 mainIn #connect
Cs0 f37 mainOut f16 tail #connect
Cs0 f16 head f10 mainIn #connect
