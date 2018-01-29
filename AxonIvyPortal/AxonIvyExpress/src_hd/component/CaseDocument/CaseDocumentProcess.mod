[Ivy]
15795F163B7492E2 3.20 #module
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
Ds0 @RichDialogProcessEnd f1 '' #zField
Ds0 @RichDialogProcessEnd f4 '' #zField
Ds0 @GridStep f6 '' #zField
Ds0 @PushWFArc f2 '' #zField
Ds0 @GridStep f8 '' #zField
Ds0 @RichDialogMethodStart f5 '' #zField
Ds0 @PushWFArc f11 '' #zField
Ds0 @GridStep f16 '' #zField
Ds0 @PushWFArc f19 '' #zField
Ds0 @GridStep f21 '' #zField
Ds0 @RichDialogMethodStart f24 '' #zField
Ds0 @GridStep f26 '' #zField
Ds0 @PushWFArc f10 '' #zField
Ds0 @PushWFArc f28 '' #zField
Ds0 @RichDialogProcessEnd f25 '' #zField
Ds0 @RichDialogMethodStart f3 '' #zField
Ds0 @Alternative f31 '' #zField
Ds0 @PushWFArc f20 '' #zField
Ds0 @PushWFArc f30 '' #zField
Ds0 @PushWFArc f34 '' #zField
Ds0 @PushWFArc f9 '' #zField
Ds0 @Alternative f29 '' #zField
Ds0 @RichDialogMethodStart f12 '' #zField
Ds0 @PushWFArc f18 '' #zField
Ds0 @PushWFArc f7 '' #zField
Ds0 @RichDialogInitStart f0 '' #zField
Ds0 @RichDialogMethodStart f13 '' #zField
Ds0 @RichDialogProcessEnd f14 '' #zField
Ds0 @PushWFArc f15 '' #zField
Ds0 @GridStep f17 '' #zField
Ds0 @GridStep f32 '' #zField
Ds0 @Alternative f35 '' #zField
Ds0 @PushWFArc f36 '' #zField
Ds0 @PushWFArc f37 '' #zField
Ds0 @PushWFArc f22 '' #zField
Ds0 @RichDialogProcessEnd f27 '' #zField
Ds0 @PushWFArc f38 '' #zField
Ds0 @PushWFArc f39 '' #zField
Ds0 @PushWFArc f23 '' #zField
Ds0 @PushWFArc f33 '' #zField
>Proto Ds0 Ds0 CaseDocumentProcess #zField
Ds0 f1 type component.CaseDocument.CaseDocumentData #txt
Ds0 f1 85 429 22 22 14 0 #rect
Ds0 f1 @|RichDialogProcessEndIcon #fIcon
Ds0 f4 type component.CaseDocument.CaseDocumentData #txt
Ds0 f4 741 181 22 22 14 0 #rect
Ds0 f4 @|RichDialogProcessEndIcon #fIcon
Ds0 f6 actionDecl 'component.CaseDocument.CaseDocumentData out;
' #txt
Ds0 f6 actionTable 'out=in;
' #txt
Ds0 f6 actionCode 'import org.primefaces.model.UploadedFile;
import ch.ivy.addon.portalkit.util.DocumentFileUtils;
import javax.faces.context.FacesContext;

in.caseId = ivy.case.getId();

in.caseDocuments = DocumentFileUtils.listFileInDirectory(ivy.case);

in.selectedFile = null;

FacesContext fc = FacesContext.getCurrentInstance();
Integer maxFiles = fc.getApplication().evaluateExpressionGet(fc, "#{cc.attrs.fileLimit}",Integer.class) as Integer;


in.sizeLimitBytes = 10485760;


//allowed file types not supported yet
in.allowedFileTypes = "/(\\.|\\/)(";

for(String type: in.inputFileTypes){
	if(type.startsWith("PDF")){
		in.allowedFileTypes += "pdf|";
	}else if(type.startsWith("Word")){
		in.allowedFileTypes += "doc|docx|";
	}else if(type.startsWith("Excel")){
		in.allowedFileTypes += "xls|xlsx|";
	}else if(type.startsWith("Andere")){
		in.allowedFileTypes += "*|";
	}
}
if(in.inputFileTypes != null && in.inputFileTypes.size() > 0){
	 in.allowedFileTypes = in.allowedFileTypes.substring(0, in.allowedFileTypes.length()-1);
}
in.allowedFileTypes += ")$$/";

//("PDF (pdf)");
//("Word (doc/docx)");
//("Excel(xls/xlsx)");
//("Andere Dateiformate");
//allowTypes="/(\.|\/)(gif|jpe?g|png)$$/"' #txt
Ds0 f6 type component.CaseDocument.CaseDocumentData #txt
Ds0 f6 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>display files</name>
        <nameStyle>13,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ds0 f6 78 340 36 24 -37 -35 #rect
Ds0 f6 @|StepIcon #fIcon
Ds0 f2 expr out #txt
Ds0 f2 96 364 96 429 #arcP
Ds0 f8 actionDecl 'component.CaseDocument.CaseDocumentData out;
' #txt
Ds0 f8 actionTable 'out=in;
' #txt
Ds0 f8 actionCode 'import ch.ivy.addon.portalkit.util.DocumentFileUtils;
in.uploadingFileExists = DocumentFileUtils.checkFileExist(ivy.case,in.selectedFile.getInputstream(),in.selectedFile.getFileName());' #txt
Ds0 f8 type component.CaseDocument.CaseDocumentData #txt
Ds0 f8 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>check exist</name>
        <nameStyle>11,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ds0 f8 486 156 36 24 23 -10 #rect
Ds0 f8 @|StepIcon #fIcon
Ds0 f5 guid 14DD74D612721654 #txt
Ds0 f5 type component.CaseDocument.CaseDocumentData #txt
Ds0 f5 method downloadFile(String,String) #txt
Ds0 f5 disableUIEvents false #txt
Ds0 f5 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<java.lang.String filePath,java.lang.String fileName> param = methodEvent.getInputArguments();
' #txt
Ds0 f5 inActionCode 'import ch.ivy.addon.portalkit.util.DocumentFileUtils;
out.downloadFile = DocumentFileUtils.downloadFile(param.filePath, param.fileName, ivy.case);' #txt
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
Ds0 f5 741 53 22 22 14 0 #rect
Ds0 f5 @|RichDialogMethodStartIcon #fIcon
Ds0 f11 expr out #txt
Ds0 f11 752 75 752 181 #arcP
Ds0 f16 actionDecl 'component.CaseDocument.CaseDocumentData out;
' #txt
Ds0 f16 actionTable 'out=in;
' #txt
Ds0 f16 actionCode 'import ch.ivy.addon.portalkit.util.DocumentFileUtils;
import org.primefaces.context.RequestContext;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;


FacesContext context = FacesContext.getCurrentInstance();
DocumentFileUtils.deleteFile(in.selectedFilePath, ivy.case);
in.selectedFilePath = "";
context.addMessage("documentFile", new FacesMessage(ivy.cms.co("/ch.ivy.addon.portalkit.ui.jsf/documentFiles/deleteSuceed"), ""));' #txt
Ds0 f16 type component.CaseDocument.CaseDocumentData #txt
Ds0 f16 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>delete file</name>
        <nameStyle>11,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ds0 f16 206 180 36 24 20 -2 #rect
Ds0 f16 @|StepIcon #fIcon
Ds0 f19 expr out #txt
Ds0 f19 224 204 114 352 #arcP
Ds0 f19 1 224 352 #addKink
Ds0 f19 0 0.5352887443931681 0 0 #arcLabel
Ds0 f21 actionDecl 'component.CaseDocument.CaseDocumentData out;
' #txt
Ds0 f21 actionTable 'out=in;
' #txt
Ds0 f21 actionCode 'import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

FacesContext context = FacesContext.getCurrentInstance();
context.addMessage("choose-uploading-file", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Die Datei " + in.selectedFile.getFileName() + " exisitert bereits!" ,null));' #txt
Ds0 f21 type component.CaseDocument.CaseDocumentData #txt
Ds0 f21 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>show warning</name>
        <nameStyle>12,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ds0 f21 622 276 36 24 24 -7 #rect
Ds0 f21 @|StepIcon #fIcon
Ds0 f24 guid 14DD7D57754BAFCF #txt
Ds0 f24 type component.CaseDocument.CaseDocumentData #txt
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
Ds0 f24 373 53 22 22 14 0 #rect
Ds0 f24 @|RichDialogMethodStartIcon #fIcon
Ds0 f26 actionDecl 'component.CaseDocument.CaseDocumentData out;
' #txt
Ds0 f26 actionTable 'out=in;
' #txt
Ds0 f26 actionCode 'import ch.ivy.addon.portalkit.util.DocumentFileUtils;
import org.primefaces.context.RequestContext;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

FacesContext context = FacesContext.getCurrentInstance();
Boolean uploadSuccesfully = DocumentFileUtils.uploadToDirectory(ivy.case,in.selectedFile.getInputstream(),in.selectedFile.getFileName());
if (uploadSuccesfully) {
	context.addMessage("documentFile", new FacesMessage( ivy.cms.co("/Dialogs/components/CaseDocument/uploadSucceed"), "" ));
	ivy.log.debug("FileUpload erfolgreich" + ivy.cms.co("/Dialogs/components/CaseDocument/uploadSucceed"));
} else {
	context.addMessage("documentFile", new FacesMessage(ivy.cms.co("/Dialogs/components/CaseDocument/uploadFailed"), "" ));
	}

' #txt
Ds0 f26 type component.CaseDocument.CaseDocumentData #txt
Ds0 f26 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>upload file to folder</name>
        <nameStyle>21,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ds0 f26 366 340 36 24 -54 14 #rect
Ds0 f26 @|StepIcon #fIcon
Ds0 f10 expr out #txt
Ds0 f10 366 352 114 352 #arcP
Ds0 f28 expr out #txt
Ds0 f28 384 75 384 340 #arcP
Ds0 f28 0 0.7392162041749724 0 0 #arcLabel
Ds0 f25 type component.CaseDocument.CaseDocumentData #txt
Ds0 f25 621 149 22 22 14 0 #rect
Ds0 f25 @|RichDialogProcessEndIcon #fIcon
Ds0 f3 guid 1511A1DC42BEBDB1 #txt
Ds0 f3 type component.CaseDocument.CaseDocumentData #txt
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
Ds0 f3 493 53 22 22 14 0 #rect
Ds0 f3 @|RichDialogMethodStartIcon #fIcon
Ds0 f31 type component.CaseDocument.CaseDocumentData #txt
Ds0 f31 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>file exists?</name>
        <nameStyle>12,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ds0 f31 490 274 28 28 12 -22 #rect
Ds0 f31 @|AlternativeIcon #fIcon
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
Ds0 f20 518 288 622 288 #arcP
Ds0 f20 0 0.7307692307692307 0 -8 #arcLabel
Ds0 f30 expr out #txt
Ds0 f30 504 75 504 106 #arcP
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
Ds0 f34 518 120 632 149 #arcP
Ds0 f34 1 632 120 #addKink
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
Ds0 f9 504 134 504 156 #arcP
Ds0 f9 0 0.36666666666666664 13 0 #arcLabel
Ds0 f29 type component.CaseDocument.CaseDocumentData #txt
Ds0 f29 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>file valid?</name>
        <nameStyle>11,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ds0 f29 490 106 28 28 -66 -9 #rect
Ds0 f29 @|AlternativeIcon #fIcon
Ds0 f12 guid 14DD7586B15ADC83 #txt
Ds0 f12 type component.CaseDocument.CaseDocumentData #txt
Ds0 f12 method deleteFile(String) #txt
Ds0 f12 disableUIEvents false #txt
Ds0 f12 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<java.lang.String filePath> param = methodEvent.getInputArguments();
' #txt
Ds0 f12 inParameterMapAction 'out.selectedFilePath=param.filePath;
' #txt
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
Ds0 f12 213 53 22 22 14 0 #rect
Ds0 f12 @|RichDialogMethodStartIcon #fIcon
Ds0 f18 expr out #txt
Ds0 f18 224 75 224 180 #arcP
Ds0 f7 expr out #txt
Ds0 f7 96 75 96 340 #arcP
Ds0 f0 guid 14DD6789B76C9299 #txt
Ds0 f0 type component.CaseDocument.CaseDocumentData #txt
Ds0 f0 method start(java.lang.Integer) #txt
Ds0 f0 disableUIEvents true #txt
Ds0 f0 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<java.lang.Integer noOfFiles> param = methodEvent.getInputArguments();
' #txt
Ds0 f0 inParameterMapAction 'out.numberOfFilesLimit=param.noOfFiles;
' #txt
Ds0 f0 inActionCode 'out.caseId = ivy.case.getId();
out.toManyFiles = false;' #txt
Ds0 f0 outParameterDecl '<java.lang.Integer noOfFiles> result;
' #txt
Ds0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start()</name>
        <nameStyle>7,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ds0 f0 85 53 22 22 14 0 #rect
Ds0 f0 @|RichDialogInitStartIcon #fIcon
Ds0 f13 guid 15AFC23DB22ECD19 #txt
Ds0 f13 type component.CaseDocument.CaseDocumentData #txt
Ds0 f13 method setFileLimit(java.lang.Integer) #txt
Ds0 f13 disableUIEvents false #txt
Ds0 f13 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<java.lang.Integer fileLimit> param = methodEvent.getInputArguments();
' #txt
Ds0 f13 inActionCode 'out.numberOfFilesLimit = param.fileLimit;' #txt
Ds0 f13 outParameterDecl '<> result;
' #txt
Ds0 f13 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>setFileLimit(Integer)</name>
        <nameStyle>21,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ds0 f13 948 52 24 24 -55 15 #rect
Ds0 f13 @|RichDialogMethodStartIcon #fIcon
Ds0 f14 type component.CaseDocument.CaseDocumentData #txt
Ds0 f14 947 179 26 26 0 12 #rect
Ds0 f14 @|RichDialogProcessEndIcon #fIcon
Ds0 f15 expr out #txt
Ds0 f15 960 76 960 179 #arcP
Ds0 f17 actionDecl 'component.CaseDocument.CaseDocumentData out;
' #txt
Ds0 f17 actionTable 'out=in;
' #txt
Ds0 f17 actionCode 'ivy.log.debug("Number of Files uploaded:" + in.caseDocuments.size());
ivy.log.debug("Number of Files allowed:" + in.numberOfFilesLimit);


if(in.numberOfFilesLimit!=0){
	ivy.log.debug("Number of Files limit active");
	if(in.caseDocuments.size()<in.numberOfFilesLimit){
		in.toManyFiles=false;
		ivy.log.debug("Number of Files not reached");
	}else{
		in.toManyFiles=true;
		ivy.log.debug("Number of Files have reached the limit!");
	}
}else{
	in.toManyFiles=false;
	ivy.log.debug("Number of Files limit unactive");
	
}

ivy.log.debug("Number of Files check result:" + in.toManyFiles);' #txt
Ds0 f17 security system #txt
Ds0 f17 type component.CaseDocument.CaseDocumentData #txt
Ds0 f17 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>check number
of files</name>
        <nameStyle>13,7
8,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ds0 f17 484 372 40 24 26 -15 #rect
Ds0 f17 @|StepIcon #fIcon
Ds0 f32 actionDecl 'component.CaseDocument.CaseDocumentData out;
' #txt
Ds0 f32 actionTable 'out=in;
' #txt
Ds0 f32 actionCode 'import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

FacesContext context = FacesContext.getCurrentInstance();
context.addMessage("choose-uploading-file", new FacesMessage(FacesMessage.SEVERITY_INFO, ivy.cms.co("/Dialogs/components/CaseDocument/fileLimitMessage") + " (max." + in.numberOfFilesLimit + ")"  ,null));' #txt
Ds0 f32 type component.CaseDocument.CaseDocumentData #txt
Ds0 f32 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>show warning</name>
        <nameStyle>12,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ds0 f32 624 468 32 24 35 -6 #rect
Ds0 f32 @|StepIcon #fIcon
Ds0 f35 type component.CaseDocument.CaseDocumentData #txt
Ds0 f35 488 464 32 32 0 16 #rect
Ds0 f35 @|AlternativeIcon #fIcon
Ds0 f36 expr out #txt
Ds0 f36 504 396 504 464 #arcP
Ds0 f37 expr out #txt
Ds0 f37 504 180 504 274 #arcP
Ds0 f22 expr in #txt
Ds0 f22 outCond in.toManyFiles #txt
Ds0 f22 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>to many</name>
        <nameStyle>7,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ds0 f22 520 480 624 480 #arcP
Ds0 f22 0 0.4807692307692308 0 -8 #arcLabel
Ds0 f27 type component.CaseDocument.CaseDocumentData #txt
Ds0 f27 627 371 26 26 0 12 #rect
Ds0 f27 @|RichDialogProcessEndIcon #fIcon
Ds0 f38 expr out #txt
Ds0 f38 640 468 640 397 #arcP
Ds0 f39 expr out #txt
Ds0 f39 640 300 640 371 #arcP
Ds0 f39 0 0.2466712619605421 0 0 #arcLabel
Ds0 f23 expr in #txt
Ds0 f23 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>no</name>
        <nameStyle>2,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ds0 f23 504 302 504 372 #arcP
Ds0 f23 0 0.2714285714285714 16 0 #arcLabel
Ds0 f33 expr in #txt
Ds0 f33 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>ok, or no limit</name>
        <nameStyle>15,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ds0 f33 488 480 384 364 #arcP
Ds0 f33 1 384 480 #addKink
Ds0 f33 0 0.3942307692307692 0 -10 #arcLabel
>Proto Ds0 .type component.CaseDocument.CaseDocumentData #txt
>Proto Ds0 .processKind HTML_DIALOG #txt
>Proto Ds0 -8 -8 16 16 16 26 #rect
>Proto Ds0 '' #fIcon
Ds0 f0 mainOut f7 tail #connect
Ds0 f7 head f6 mainIn #connect
Ds0 f6 mainOut f2 tail #connect
Ds0 f2 head f1 mainIn #connect
Ds0 f5 mainOut f11 tail #connect
Ds0 f11 head f4 mainIn #connect
Ds0 f16 mainOut f19 tail #connect
Ds0 f19 head f6 mainIn #connect
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
Ds0 f31 out f20 tail #connect
Ds0 f20 head f21 mainIn #connect
Ds0 f12 mainOut f18 tail #connect
Ds0 f18 head f16 mainIn #connect
Ds0 f13 mainOut f15 tail #connect
Ds0 f15 head f14 mainIn #connect
Ds0 f17 mainOut f36 tail #connect
Ds0 f36 head f35 in #connect
Ds0 f8 mainOut f37 tail #connect
Ds0 f37 head f31 in #connect
Ds0 f35 out f22 tail #connect
Ds0 f22 head f32 mainIn #connect
Ds0 f32 mainOut f38 tail #connect
Ds0 f38 head f27 mainIn #connect
Ds0 f21 mainOut f39 tail #connect
Ds0 f39 head f27 mainIn #connect
Ds0 f31 out f23 tail #connect
Ds0 f23 head f17 mainIn #connect
Ds0 f35 out f33 tail #connect
Ds0 f33 head f26 mainIn #connect
