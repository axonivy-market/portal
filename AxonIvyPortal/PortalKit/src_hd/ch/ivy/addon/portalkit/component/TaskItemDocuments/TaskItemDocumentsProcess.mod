[Ivy]
1549452C001D8A69 3.23 #module
>Proto >Proto Collection #zClass
Ts0 TaskItemDocumentsProcess Big #zClass
Ts0 RD #cInfo
Ts0 #process
Ts0 @TextInP .ui2RdDataAction .ui2RdDataAction #zField
Ts0 @TextInP .rdData2UIAction .rdData2UIAction #zField
Ts0 @TextInP .resExport .resExport #zField
Ts0 @TextInP .type .type #zField
Ts0 @TextInP .processKind .processKind #zField
Ts0 @AnnotationInP-0n ai ai #zField
Ts0 @MessageFlowInP-0n messageIn messageIn #zField
Ts0 @MessageFlowOutP-0n messageOut messageOut #zField
Ts0 @TextInP .xml .xml #zField
Ts0 @TextInP .responsibility .responsibility #zField
Ts0 @RichDialogInitStart f0 '' #zField
Ts0 @RichDialogProcessEnd f1 '' #zField
Ts0 @RichDialogMethodStart f6 '' #zField
Ts0 @GridStep f9 '' #zField
Ts0 @PushWFArc f16 '' #zField
Ts0 @GridStep f64 '' #zField
Ts0 @PushWFArc f17 '' #zField
Ts0 @PushWFArc f2 '' #zField
Ts0 @RichDialogProcessEnd f8 '' #zField
Ts0 @PushWFArc f37 '' #zField
Ts0 @RichDialogMethodStart f10 '' #zField
Ts0 @RichDialogProcessEnd f18 '' #zField
Ts0 @GridStep f36 '' #zField
Ts0 @GridStep f38 '' #zField
Ts0 @Alternative f41 '' #zField
Ts0 @Alternative f53 '' #zField
Ts0 @Alternative f67 '' #zField
Ts0 @RichDialogProcessEnd f68 '' #zField
Ts0 @GridStep f69 '' #zField
Ts0 @Alternative f70 '' #zField
Ts0 @GridStep f71 '' #zField
Ts0 @Alternative f72 '' #zField
Ts0 @Alternative f73 '' #zField
Ts0 @Alternative f74 '' #zField
Ts0 @CallSub f75 '' #zField
Ts0 @GridStep f76 '' #zField
Ts0 @PushWFArc f77 '' #zField
Ts0 @PushWFArc f78 '' #zField
Ts0 @PushWFArc f79 '' #zField
Ts0 @PushWFArc f80 '' #zField
Ts0 @PushWFArc f81 '' #zField
Ts0 @PushWFArc f82 '' #zField
Ts0 @PushWFArc f83 '' #zField
Ts0 @PushWFArc f84 '' #zField
Ts0 @PushWFArc f85 '' #zField
Ts0 @PushWFArc f86 '' #zField
Ts0 @PushWFArc f87 '' #zField
Ts0 @PushWFArc f88 '' #zField
Ts0 @PushWFArc f89 '' #zField
Ts0 @PushWFArc f90 '' #zField
Ts0 @PushWFArc f91 '' #zField
Ts0 @PushWFArc f92 '' #zField
Ts0 @PushWFArc f93 '' #zField
Ts0 @PushWFArc f94 '' #zField
Ts0 @GridStep f20 '' #zField
Ts0 @RichDialogProcessEnd f4 '' #zField
Ts0 @RichDialogMethodStart f5 '' #zField
Ts0 @PushWFArc f7 '' #zField
Ts0 @PushWFArc f11 '' #zField
Ts0 @RichDialogMethodStart f12 '' #zField
Ts0 @RichDialogProcessEnd f21 '' #zField
Ts0 @GridStep f13 '' #zField
Ts0 @PushWFArc f23 '' #zField
Ts0 @PushWFArc f14 '' #zField
Ts0 @RichDialogMethodStart f3 '' #zField
Ts0 @GridStep f15 '' #zField
Ts0 @GridStep f19 '' #zField
Ts0 @RichDialogProcessEnd f22 '' #zField
Ts0 @PushWFArc f24 '' #zField
Ts0 @PushWFArc f25 '' #zField
Ts0 @PushWFArc f26 '' #zField
>Proto Ts0 Ts0 TaskItemDocumentsProcess #zField
Ts0 f0 guid 1682717B951993D3 #txt
Ts0 f0 type ch.ivy.addon.portalkit.component.TaskItemDocuments.TaskItemDocumentsData #txt
Ts0 f0 method start() #txt
Ts0 f0 disableUIEvents true #txt
Ts0 f0 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<> param = methodEvent.getInputArguments();
' #txt
Ts0 f0 outParameterDecl '<> result;
' #txt
Ts0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start()</name>
    </language>
</elementInfo>
' #txt
Ts0 f0 83 83 26 26 -16 15 #rect
Ts0 f0 @|RichDialogInitStartIcon #fIcon
Ts0 f1 type ch.ivy.addon.portalkit.component.TaskItemDocuments.TaskItemDocumentsData #txt
Ts0 f1 371 83 26 26 0 12 #rect
Ts0 f1 @|RichDialogProcessEndIcon #fIcon
Ts0 f6 guid 1682717F2DAE7E7A #txt
Ts0 f6 type ch.ivy.addon.portalkit.component.TaskItemDocuments.TaskItemDocumentsData #txt
Ts0 f6 method initData(ch.ivyteam.ivy.workflow.ITask) #txt
Ts0 f6 disableUIEvents false #txt
Ts0 f6 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<ch.ivyteam.ivy.workflow.ITask task> param = methodEvent.getInputArguments();
' #txt
Ts0 f6 inParameterMapAction 'out.task=param.task;
' #txt
Ts0 f6 outParameterDecl '<> result;
' #txt
Ts0 f6 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>initData(ITask)</name>
    </language>
</elementInfo>
' #txt
Ts0 f6 83 179 26 26 -40 15 #rect
Ts0 f6 @|RichDialogMethodStartIcon #fIcon
Ts0 f9 actionDecl 'ch.ivy.addon.portalkit.component.TaskItemDocuments.TaskItemDocumentsData out;
' #txt
Ts0 f9 actionTable 'out=in;
' #txt
Ts0 f9 actionCode 'import ch.ivy.addon.portalkit.service.CaseDocumentService;

out.businessCase = in.task.getCase().getBusinessCase();
out.documents = CaseDocumentService.newInstance(out.businessCase).getAll();' #txt
Ts0 f9 type ch.ivy.addon.portalkit.component.TaskItemDocuments.TaskItemDocumentsData #txt
Ts0 f9 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Find documents</name>
    </language>
</elementInfo>
' #txt
Ts0 f9 168 170 112 44 -44 -8 #rect
Ts0 f9 @|StepIcon #fIcon
Ts0 f16 expr out #txt
Ts0 f16 109 192 168 192 #arcP
Ts0 f64 actionDecl 'ch.ivy.addon.portalkit.component.TaskItemDocuments.TaskItemDocumentsData out;
' #txt
Ts0 f64 actionTable 'out=in;
' #txt
Ts0 f64 actionCode 'import ch.ivy.addon.portalkit.enums.GlobalVariable;
import ch.ivy.addon.portalkit.service.GlobalSettingService;

GlobalSettingService globalSettingService = new GlobalSettingService();
String isHideUploadDocumentForDoneCaseString =
        globalSettingService.findGlobalSettingValue(GlobalVariable.HIDE_UPLOAD_DOCUMENT_FOR_DONE_CASE.toString());
boolean isHideUploadDocumentForDoneCase = Boolean.parseBoolean(isHideUploadDocumentForDoneCaseString);
in.isShowUploadDocumentButton =  !isHideUploadDocumentForDoneCase;' #txt
Ts0 f64 type ch.ivy.addon.portalkit.component.TaskItemDocuments.TaskItemDocumentsData #txt
Ts0 f64 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Init hide&#xD;
add document</name>
    </language>
</elementInfo>
' #txt
Ts0 f64 336 170 112 44 -39 -16 #rect
Ts0 f64 @|StepIcon #fIcon
Ts0 f17 expr out #txt
Ts0 f17 109 96 371 96 #arcP
Ts0 f2 expr out #txt
Ts0 f2 280 192 336 192 #arcP
Ts0 f8 type ch.ivy.addon.portalkit.component.TaskItemDocuments.TaskItemDocumentsData #txt
Ts0 f8 499 179 26 26 0 12 #rect
Ts0 f8 @|RichDialogProcessEndIcon #fIcon
Ts0 f37 expr out #txt
Ts0 f37 448 192 499 192 #arcP
Ts0 f10 guid 16827249E7B1FA94 #txt
Ts0 f10 type ch.ivy.addon.portalkit.component.TaskItemDocuments.TaskItemDocumentsData #txt
Ts0 f10 method uploadDocument(org.primefaces.event.FileUploadEvent) #txt
Ts0 f10 disableUIEvents false #txt
Ts0 f10 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<org.primefaces.event.FileUploadEvent uploadEvent> param = methodEvent.getInputArguments();
' #txt
Ts0 f10 inParameterMapAction 'out.documentUploadEvent=param.uploadEvent;
' #txt
Ts0 f10 outParameterDecl '<> result;
' #txt
Ts0 f10 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>uploadDocument(FileUploadEvent)</name>
    </language>
</elementInfo>
' #txt
Ts0 f10 83 275 26 26 -86 17 #rect
Ts0 f10 @|RichDialogMethodStartIcon #fIcon
Ts0 f18 type ch.ivy.addon.portalkit.component.TaskItemDocuments.TaskItemDocumentsData #txt
Ts0 f18 1395 371 26 26 0 12 #rect
Ts0 f18 @|RichDialogProcessEndIcon #fIcon
Ts0 f36 actionDecl 'ch.ivy.addon.portalkit.component.TaskItemDocuments.TaskItemDocumentsData out;
' #txt
Ts0 f36 actionTable 'out=in;
' #txt
Ts0 f36 actionCode 'import ch.ivy.addon.portalkit.enums.UploadDocumentCheckStatus;
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
Ts0 f36 type ch.ivy.addon.portalkit.component.TaskItemDocuments.TaskItemDocumentsData #txt
Ts0 f36 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Validate</name>
    </language>
</elementInfo>
' #txt
Ts0 f36 176 266 112 44 -22 -8 #rect
Ts0 f36 @|StepIcon #fIcon
Ts0 f38 actionDecl 'ch.ivy.addon.portalkit.component.TaskItemDocuments.TaskItemDocumentsData out;
' #txt
Ts0 f38 actionTable 'out=in;
' #txt
Ts0 f38 actionCode 'import ch.ivy.addon.portalkit.enums.UploadDocumentCheckStatus;
import org.primefaces.model.UploadedFile;
import ch.ivy.addon.portalkit.service.CaseDocumentService;

UploadedFile uploadedFile = in.documentUploadEvent.getFile();
out.name = uploadedFile.getFileName();
boolean doesDocumentExist = CaseDocumentService.newInstance(in.businessCase).doesDocumentExist(out.name);

if (doesDocumentExist) {
	out.uploadDocumentCheckMessage = ivy.cms.co("/ch.ivy.addon.portalkit.ui.jsf/documentFiles/uploadFileExists", [in.name]);
	out.uploadDocumentCheckStatus = UploadDocumentCheckStatus.FAIL;
}' #txt
Ts0 f38 type ch.ivy.addon.portalkit.component.TaskItemDocuments.TaskItemDocumentsData #txt
Ts0 f38 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Check exist</name>
    </language>
</elementInfo>
' #txt
Ts0 f38 712 266 112 44 -31 -8 #rect
Ts0 f38 @|StepIcon #fIcon
Ts0 f41 type ch.ivy.addon.portalkit.component.TaskItemDocuments.TaskItemDocumentsData #txt
Ts0 f41 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Upload successfully?</name>
    </language>
</elementInfo>
' #txt
Ts0 f41 1152 272 32 32 -54 -39 #rect
Ts0 f41 @|AlternativeIcon #fIcon
Ts0 f53 type ch.ivy.addon.portalkit.component.TaskItemDocuments.TaskItemDocumentsData #txt
Ts0 f53 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Document check</name>
        <nameStyle>14,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ts0 f53 624 272 32 32 -54 -37 #rect
Ts0 f53 @|AlternativeIcon #fIcon
Ts0 f67 type ch.ivy.addon.portalkit.component.TaskItemDocuments.TaskItemDocumentsData #txt
Ts0 f67 1152 368 32 32 0 16 #rect
Ts0 f67 @|AlternativeIcon #fIcon
Ts0 f68 type ch.ivy.addon.portalkit.component.TaskItemDocuments.TaskItemDocumentsData #txt
Ts0 f68 1395 275 26 26 0 12 #rect
Ts0 f68 @|RichDialogProcessEndIcon #fIcon
Ts0 f69 actionDecl 'ch.ivy.addon.portalkit.component.TaskItemDocuments.TaskItemDocumentsData out;
' #txt
Ts0 f69 actionTable 'out=in;
' #txt
Ts0 f69 actionCode 'import ch.ivy.addon.portalkit.service.CaseDocumentService;

out.documents = CaseDocumentService.newInstance(in.businessCase).getAll();' #txt
Ts0 f69 type ch.ivy.addon.portalkit.component.TaskItemDocuments.TaskItemDocumentsData #txt
Ts0 f69 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Find all documents</name>
    </language>
</elementInfo>
' #txt
Ts0 f69 1240 266 112 44 -52 -8 #rect
Ts0 f69 @|StepIcon #fIcon
Ts0 f70 type ch.ivy.addon.portalkit.component.TaskItemDocuments.TaskItemDocumentsData #txt
Ts0 f70 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>valid file?</name>
        <nameStyle>11,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ts0 f70 338 274 28 28 -30 -34 #rect
Ts0 f70 @|AlternativeIcon #fIcon
Ts0 f71 actionDecl 'ch.ivy.addon.portalkit.component.TaskItemDocuments.TaskItemDocumentsData out;
' #txt
Ts0 f71 actionTable 'out=in;
' #txt
Ts0 f71 actionCode 'import ch.ivy.addon.portalkit.service.CaseDocumentService;
import ch.ivy.addon.portalkit.enums.UploadDocumentCheckStatus;
import java.util.Arrays;
import org.primefaces.model.UploadedFile;

out.uploadDocumentCheckMessage = "";
out.uploadDocumentCheckStatus = UploadDocumentCheckStatus.OK;
UploadedFile uploadedFile = in.documentUploadEvent.getFile();
boolean isUploaded = CaseDocumentService.newInstance(in.businessCase).upload(in.name, uploadedFile.getInputstream());

if (isUploaded) {	
	String note = ivy.cms.co("/ch.ivy.addon.portalkit.ui.jsf/documentFiles/uploadDocumentNote", Arrays.asList(ivy.session.getSessionUserName(), in.name));
	in.businessCase.createNote(ivy.session, note);
} else {
	out.uploadDocumentCheckMessage = ivy.cms.co("/ch.ivy.addon.portalkit.ui.jsf/documentFiles/uploadFailed");
	out.uploadDocumentCheckStatus = UploadDocumentCheckStatus.FAIL;
}' #txt
Ts0 f71 security system #txt
Ts0 f71 type ch.ivy.addon.portalkit.component.TaskItemDocuments.TaskItemDocumentsData #txt
Ts0 f71 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Upload document&#xD;
and create note</name>
    </language>
</elementInfo>
' #txt
Ts0 f71 976 266 128 44 -44 -16 #rect
Ts0 f71 @|StepIcon #fIcon
Ts0 f72 type ch.ivy.addon.portalkit.component.TaskItemDocuments.TaskItemDocumentsData #txt
Ts0 f72 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Not exist?</name>
    </language>
</elementInfo>
' #txt
Ts0 f72 880 272 32 32 -27 -34 #rect
Ts0 f72 @|AlternativeIcon #fIcon
Ts0 f73 type ch.ivy.addon.portalkit.component.TaskItemDocuments.TaskItemDocumentsData #txt
Ts0 f73 880 368 32 32 0 16 #rect
Ts0 f73 @|AlternativeIcon #fIcon
Ts0 f74 type ch.ivy.addon.portalkit.component.TaskItemDocuments.TaskItemDocumentsData #txt
Ts0 f74 624 368 32 32 0 16 #rect
Ts0 f74 @|AlternativeIcon #fIcon
Ts0 f75 type ch.ivy.addon.portalkit.component.TaskItemDocuments.TaskItemDocumentsData #txt
Ts0 f75 processCall 'Functional Processes/UploadDocumentChecker:call(org.primefaces.model.UploadedFile)' #txt
Ts0 f75 doCall true #txt
Ts0 f75 requestActionDecl '<org.primefaces.model.UploadedFile uploadFile> param;
' #txt
Ts0 f75 requestMappingAction 'param.uploadFile=in.documentUploadEvent.getFile();
' #txt
Ts0 f75 responseActionDecl 'ch.ivy.addon.portalkit.component.TaskItemDocuments.TaskItemDocumentsData out;
' #txt
Ts0 f75 responseMappingAction 'out=in;
out.uploadDocumentCheckMessage=result.message;
out.uploadDocumentCheckStatus=result.uploadDocumentCheckStatus;
' #txt
Ts0 f75 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>UploadDocumentChecker</name>
        <nameStyle>21,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ts0 f75 416 266 160 44 -71 -8 #rect
Ts0 f75 @|CallSubIcon #fIcon
Ts0 f76 actionDecl 'ch.ivy.addon.portalkit.component.TaskItemDocuments.TaskItemDocumentsData out;
' #txt
Ts0 f76 actionTable 'out=in;
' #txt
Ts0 f76 actionCode 'import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, in.uploadDocumentCheckMessage, null));' #txt
Ts0 f76 type ch.ivy.addon.portalkit.component.TaskItemDocuments.TaskItemDocumentsData #txt
Ts0 f76 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Show error message</name>
    </language>
</elementInfo>
' #txt
Ts0 f76 1232 362 128 44 -58 -8 #rect
Ts0 f76 @|StepIcon #fIcon
Ts0 f77 expr in #txt
Ts0 f77 1184 384 1232 384 #arcP
Ts0 f77 0 0.3194444444444444 0 -10 #arcLabel
Ts0 f78 expr in #txt
Ts0 f78 outCond org.apache.commons.lang.StringUtils.isBlank(in.uploadDocumentCheckMessage) #txt
Ts0 f78 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>YES</name>
        <nameStyle>3
</nameStyle>
    </language>
</elementInfo>
' #txt
Ts0 f78 366 288 416 288 #arcP
Ts0 f78 0 0.26 0 -11 #arcLabel
Ts0 f79 expr out #txt
Ts0 f79 288 288 338 288 #arcP
Ts0 f80 expr out #txt
Ts0 f80 576 288 624 288 #arcP
Ts0 f80 0 0.9770034044244577 0 0 #arcLabel
Ts0 f81 expr in #txt
Ts0 f81 912 384 1152 384 #arcP
Ts0 f81 0 0.3194444444444444 0 -10 #arcLabel
Ts0 f82 expr out #txt
Ts0 f82 1352 288 1395 288 #arcP
Ts0 f83 expr in #txt
Ts0 f83 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>NO</name>
        <nameStyle>2
</nameStyle>
    </language>
</elementInfo>
' #txt
Ts0 f83 352 302 624 384 #arcP
Ts0 f83 1 352 384 #addKink
Ts0 f83 1 0.11556836310247781 -1 -9 #arcLabel
Ts0 f84 expr out #txt
Ts0 f84 1104 288 1152 288 #arcP
Ts0 f85 expr in #txt
Ts0 f85 outCond 'in.uploadDocumentCheckStatus == ch.ivy.addon.portalkit.enums.UploadDocumentCheckStatus.OK' #txt
Ts0 f85 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>YES</name>
        <nameStyle>3
</nameStyle>
    </language>
</elementInfo>
' #txt
Ts0 f85 1184 288 1240 288 #arcP
Ts0 f85 0 0.2857142857142857 -1 -7 #arcLabel
Ts0 f86 expr in #txt
Ts0 f86 1168 304 1168 368 #arcP
Ts0 f87 expr in #txt
Ts0 f87 outCond 'in.uploadDocumentCheckStatus == ch.ivy.addon.portalkit.enums.UploadDocumentCheckStatus.OK' #txt
Ts0 f87 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>OK</name>
        <nameStyle>2,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ts0 f87 656 288 712 288 #arcP
Ts0 f87 0 0.3333333333333333 0 -9 #arcLabel
Ts0 f88 expr in #txt
Ts0 f88 896 304 896 368 #arcP
Ts0 f88 0 0.6247201550720135 0 0 #arcLabel
Ts0 f89 expr out #txt
Ts0 f89 1360 384 1395 384 #arcP
Ts0 f90 expr in #txt
Ts0 f90 640 304 640 368 #arcP
Ts0 f91 expr out #txt
Ts0 f91 824 288 880 288 #arcP
Ts0 f91 0 0.3333333333333333 0 -9 #arcLabel
Ts0 f92 expr in #txt
Ts0 f92 656 384 880 384 #arcP
Ts0 f92 0 0.3194444444444444 0 -10 #arcLabel
Ts0 f93 expr in #txt
Ts0 f93 outCond 'in.uploadDocumentCheckStatus == ch.ivy.addon.portalkit.enums.UploadDocumentCheckStatus.OK' #txt
Ts0 f93 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>YES</name>
        <nameStyle>3
</nameStyle>
    </language>
</elementInfo>
' #txt
Ts0 f93 912 288 976 288 #arcP
Ts0 f93 0 0.3333333333333333 -1 -9 #arcLabel
Ts0 f94 expr out #txt
Ts0 f94 109 288 176 288 #arcP
Ts0 f20 actionDecl 'ch.ivy.addon.portalkit.component.TaskItemDocuments.TaskItemDocumentsData out;
' #txt
Ts0 f20 actionTable 'out=in;
' #txt
Ts0 f20 actionCode 'import ch.ivy.addon.portalkit.service.CaseDocumentService;

out.streamedContent = CaseDocumentService.newInstance(in.businessCase).download(in.document);' #txt
Ts0 f20 type ch.ivy.addon.portalkit.component.TaskItemDocuments.TaskItemDocumentsData #txt
Ts0 f20 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Download document</name>
    </language>
</elementInfo>
' #txt
Ts0 f20 192 426 128 44 -57 -8 #rect
Ts0 f20 @|StepIcon #fIcon
Ts0 f4 type ch.ivy.addon.portalkit.component.TaskItemDocuments.TaskItemDocumentsData #txt
Ts0 f4 403 435 26 26 0 12 #rect
Ts0 f4 @|RichDialogProcessEndIcon #fIcon
Ts0 f5 guid 16827286AC6AA280 #txt
Ts0 f5 type ch.ivy.addon.portalkit.component.TaskItemDocuments.TaskItemDocumentsData #txt
Ts0 f5 method downloadDocument(ch.ivyteam.ivy.workflow.document.IDocument) #txt
Ts0 f5 disableUIEvents false #txt
Ts0 f5 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<ch.ivyteam.ivy.workflow.document.IDocument document> param = methodEvent.getInputArguments();
' #txt
Ts0 f5 inParameterMapAction 'out.document=param.document;
' #txt
Ts0 f5 outParameterDecl '<org.primefaces.model.StreamedContent streamedContent> result;
' #txt
Ts0 f5 outParameterMapAction 'result.streamedContent=in.streamedContent;
' #txt
Ts0 f5 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>downloadDocument(IDocument)</name>
    </language>
</elementInfo>
' #txt
Ts0 f5 83 435 26 26 -89 15 #rect
Ts0 f5 @|RichDialogMethodStartIcon #fIcon
Ts0 f7 expr out #txt
Ts0 f7 320 448 403 448 #arcP
Ts0 f11 expr out #txt
Ts0 f11 109 448 192 448 #arcP
Ts0 f12 guid 168272AA0A252382 #txt
Ts0 f12 type ch.ivy.addon.portalkit.component.TaskItemDocuments.TaskItemDocumentsData #txt
Ts0 f12 method resetMessageUploadDocument() #txt
Ts0 f12 disableUIEvents false #txt
Ts0 f12 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<> param = methodEvent.getInputArguments();
' #txt
Ts0 f12 outParameterDecl '<> result;
' #txt
Ts0 f12 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>resetMessageUploadDocument()</name>
    </language>
</elementInfo>
' #txt
Ts0 f12 83 531 26 26 -79 21 #rect
Ts0 f12 @|RichDialogMethodStartIcon #fIcon
Ts0 f21 type ch.ivy.addon.portalkit.component.TaskItemDocuments.TaskItemDocumentsData #txt
Ts0 f21 403 531 26 26 0 12 #rect
Ts0 f21 @|RichDialogProcessEndIcon #fIcon
Ts0 f13 actionDecl 'ch.ivy.addon.portalkit.component.TaskItemDocuments.TaskItemDocumentsData out;
' #txt
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
Ts0 f13 type ch.ivy.addon.portalkit.component.TaskItemDocuments.TaskItemDocumentsData #txt
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
Ts0 f3 type ch.ivy.addon.portalkit.component.TaskItemDocuments.TaskItemDocumentsData #txt
Ts0 f3 method deleteDocument() #txt
Ts0 f3 disableUIEvents false #txt
Ts0 f3 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<> param = methodEvent.getInputArguments();
' #txt
Ts0 f3 outParameterDecl '<> result;
' #txt
Ts0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>deleteDocument()</name>
    </language>
</elementInfo>
' #txt
Ts0 f3 83 627 26 26 -49 15 #rect
Ts0 f3 @|RichDialogMethodStartIcon #fIcon
Ts0 f15 actionDecl 'ch.ivy.addon.portalkit.component.TaskItemDocuments.TaskItemDocumentsData out;
' #txt
Ts0 f15 actionTable 'out=in;
' #txt
Ts0 f15 actionCode 'import java.util.Arrays;

in.businessCase.documents().delete(in.document);

String note = ivy.cms.co("/ch.ivy.addon.portalkit.ui.jsf/documentFiles/deleteDocumentNote", Arrays.asList(ivy.session.getSessionUserName(), in.document.getName()));
in.businessCase.createNote(ivy.session, note);' #txt
Ts0 f15 security system #txt
Ts0 f15 type ch.ivy.addon.portalkit.component.TaskItemDocuments.TaskItemDocumentsData #txt
Ts0 f15 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Delete document&#xD;
and create note</name>
    </language>
</elementInfo>
' #txt
Ts0 f15 192 618 128 44 -43 -16 #rect
Ts0 f15 @|StepIcon #fIcon
Ts0 f19 actionDecl 'ch.ivy.addon.portalkit.component.TaskItemDocuments.TaskItemDocumentsData out;
' #txt
Ts0 f19 actionTable 'out=in;
' #txt
Ts0 f19 actionCode 'import ch.ivy.addon.portalkit.service.CaseDocumentService;

out.documents = CaseDocumentService.newInstance(in.businessCase).getAll();' #txt
Ts0 f19 type ch.ivy.addon.portalkit.component.TaskItemDocuments.TaskItemDocumentsData #txt
Ts0 f19 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Find all documents</name>
    </language>
</elementInfo>
' #txt
Ts0 f19 368 618 112 44 -52 -8 #rect
Ts0 f19 @|StepIcon #fIcon
Ts0 f22 type ch.ivy.addon.portalkit.component.TaskItemDocuments.TaskItemDocumentsData #txt
Ts0 f22 531 627 26 26 0 12 #rect
Ts0 f22 @|RichDialogProcessEndIcon #fIcon
Ts0 f24 expr out #txt
Ts0 f24 320 640 368 640 #arcP
Ts0 f25 expr out #txt
Ts0 f25 480 640 531 640 #arcP
Ts0 f26 expr out #txt
Ts0 f26 109 640 192 640 #arcP
>Proto Ts0 .type ch.ivy.addon.portalkit.component.TaskItemDocuments.TaskItemDocumentsData #txt
>Proto Ts0 .processKind HTML_DIALOG #txt
>Proto Ts0 -8 -8 16 16 16 26 #rect
>Proto Ts0 '' #fIcon
Ts0 f6 mainOut f16 tail #connect
Ts0 f16 head f9 mainIn #connect
Ts0 f0 mainOut f17 tail #connect
Ts0 f17 head f1 mainIn #connect
Ts0 f9 mainOut f2 tail #connect
Ts0 f2 head f64 mainIn #connect
Ts0 f64 mainOut f37 tail #connect
Ts0 f37 head f8 mainIn #connect
Ts0 f70 out f78 tail #connect
Ts0 f78 head f75 mainIn #connect
Ts0 f75 mainOut f80 tail #connect
Ts0 f80 head f53 in #connect
Ts0 f36 mainOut f79 tail #connect
Ts0 f79 head f70 in #connect
Ts0 f76 mainOut f89 tail #connect
Ts0 f89 head f18 mainIn #connect
Ts0 f70 out f83 tail #connect
Ts0 f83 head f74 in #connect
Ts0 f90 head f74 in #connect
Ts0 f71 mainOut f84 tail #connect
Ts0 f84 head f41 in #connect
Ts0 f41 out f85 tail #connect
Ts0 f85 head f69 mainIn #connect
Ts0 f67 out f77 tail #connect
Ts0 f77 head f76 mainIn #connect
Ts0 f41 out f86 tail #connect
Ts0 f86 head f67 in #connect
Ts0 f53 out f87 tail #connect
Ts0 f87 head f38 mainIn #connect
Ts0 f53 out f90 tail #connect
Ts0 f38 mainOut f91 tail #connect
Ts0 f91 head f72 in #connect
Ts0 f72 out f93 tail #connect
Ts0 f93 head f71 mainIn #connect
Ts0 f74 out f92 tail #connect
Ts0 f92 head f73 in #connect
Ts0 f73 out f81 tail #connect
Ts0 f81 head f67 in #connect
Ts0 f72 out f88 tail #connect
Ts0 f88 head f73 in #connect
Ts0 f69 mainOut f82 tail #connect
Ts0 f82 head f68 mainIn #connect
Ts0 f10 mainOut f94 tail #connect
Ts0 f94 head f36 mainIn #connect
Ts0 f5 mainOut f11 tail #connect
Ts0 f11 head f20 mainIn #connect
Ts0 f20 mainOut f7 tail #connect
Ts0 f7 head f4 mainIn #connect
Ts0 f13 mainOut f23 tail #connect
Ts0 f23 head f21 mainIn #connect
Ts0 f12 mainOut f14 tail #connect
Ts0 f14 head f13 mainIn #connect
Ts0 f15 mainOut f24 tail #connect
Ts0 f24 head f19 mainIn #connect
Ts0 f19 mainOut f25 tail #connect
Ts0 f25 head f22 mainIn #connect
Ts0 f3 mainOut f26 tail #connect
Ts0 f26 head f15 mainIn #connect
