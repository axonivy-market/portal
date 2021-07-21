[Ivy]
16F7F951DB019946 7.5.0 #module
>Proto >Proto Collection #zClass
Es0 ExpressManagementProcess Big #zClass
Es0 RD #cInfo
Es0 #process
Es0 @TextInP .type .type #zField
Es0 @TextInP .processKind .processKind #zField
Es0 @TextInP .xml .xml #zField
Es0 @TextInP .responsibility .responsibility #zField
Es0 @UdProcessEnd f1 '' #zField
Es0 @Alternative f13 '' #zField
Es0 @GridStep f10 '' #zField
Es0 @UdMethod f90 '' #zField
Es0 @UdProcessEnd f3 '' #zField
Es0 @UdInit f0 '' #zField
Es0 @GridStep f96 '' #zField
Es0 @GridStep f94 '' #zField
Es0 @UdMethod f89 '' #zField
Es0 @GridStep f16 '' #zField
Es0 @PushWFArc f11 '' #zField
Es0 @PushWFArc f14 '' #zField
Es0 @PushWFArc f17 '' #zField
Es0 @PushWFArc f18 '' #zField
Es0 @PushWFArc f9 '' #zField
Es0 @GridStep f20 '' #zField
Es0 @PushWFArc f8 '' #zField
Es0 @UdMethod f5 '' #zField
Es0 @UdProcessEnd f6 '' #zField
Es0 @PushWFArc f7 '' #zField
Es0 @PushWFArc f12 '' #zField
Es0 @PushWFArc f2 '' #zField
Es0 @GridStep f33 '' #zField
Es0 @PushWFArc f19 '' #zField
Es0 @PushWFArc f15 '' #zField
Es0 @PushWFArc f21 '' #zField
>Proto Es0 Es0 ExpressManagementProcess #zField
Es0 f1 267 83 26 26 0 12 #rect
Es0 f1 @|UdProcessEndIcon #fIcon
Es0 f13 336 368 32 32 0 16 #rect
Es0 f13 @|AlternativeIcon #fIcon
Es0 f10 actionTable 'out=in;
' #txt
Es0 f10 actionCode 'import ch.ivy.addon.portalkit.service.CaseDocumentService;
import org.apache.commons.io.FilenameUtils;

import org.apache.commons.lang3.StringUtils;
import javax.faces.application.FacesMessage;

in.isError = false;
in.importOutput = StringUtils.EMPTY;

if (in.importExpressFile == null || in.importExpressFile.getFile().getSize() == 0) {
  in.isError = true;
  in.validateMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, ivy.cms.co("/ch.ivy.addon.portalkit.ui.jsf/components/expressManagement/fileEmptyMessage"), null);
} else if (CaseDocumentService.enableVirusScannerForUploadedDocument() && CaseDocumentService.isDocumentTypeHasVirus(in.importExpressFile.getFile())) {
  in.isError = true;
  in.validateMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, ivy.cms.co("/ch.ivy.addon.portalkit.ui.jsf/documentFiles/fileContainVirus"), null);
} else if (CaseDocumentService.enableScriptCheckingForUploadedDocument() && !CaseDocumentService.isDocumentSafe(in.importExpressFile.getFile())) {
  in.isError = true;
  in.validateMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, ivy.cms.co("/ch.ivy.addon.portalkit.ui.jsf/documentFiles/fileContainScript"), null);
} else if (!FilenameUtils.isExtension(in.importExpressFile.getFile().getFileName(), "json")) {
  in.isError = true;
  in.validateMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, ivy.cms.co("/Dialogs/components/CaseDocument/invalidFileMessage"), null);
}' #txt
Es0 f10 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>validate</name>
    </language>
</elementInfo>
' #txt
Es0 f10 168 362 112 44 -21 -8 #rect
Es0 f10 @|StepIcon #fIcon
Es0 f90 guid 16F82C1E20EF8CDD #txt
Es0 f90 method exportExpress() #txt
Es0 f90 inParameterDecl '<> param;' #txt
Es0 f90 outParameterDecl '<org.primefaces.model.StreamedContent exportFile> result;' #txt
Es0 f90 outParameterMapAction 'result.exportFile=in.exportExpressFile;
' #txt
Es0 f90 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>exportExpress()</name>
    </language>
</elementInfo>
' #txt
Es0 f90 83 467 26 26 -39 23 #rect
Es0 f90 @|UdMethodIcon #fIcon
Es0 f3 755 371 26 26 0 12 #rect
Es0 f3 @|UdProcessEndIcon #fIcon
Es0 f0 guid 16F82C1E20E8A359 #txt
Es0 f0 method start() #txt
Es0 f0 inParameterDecl '<> param;' #txt
Es0 f0 outParameterDecl '<> result;' #txt
Es0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start()</name>
    </language>
</elementInfo>
' #txt
Es0 f0 83 83 26 26 -16 15 #rect
Es0 f0 @|UdInitIcon #fIcon
Es0 f96 actionTable 'out=in;
' #txt
Es0 f96 actionCode 'import ch.ivy.addon.portalkit.util.ExpressManagementUtils;

if (in.selectedExpressProcessList != null && !in.selectedExpressProcessList.isEmpty()) {
  ExpressManagementUtils utils = new ExpressManagementUtils();	
  in.exportExpressFile = utils.exportExpressProcess(in.selectedExpressProcessList);
}

' #txt
Es0 f96 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>export express process</name>
    </language>
</elementInfo>
' #txt
Es0 f96 376 458 144 44 -64 -8 #rect
Es0 f96 @|StepIcon #fIcon
Es0 f94 actionTable 'out=in;
' #txt
Es0 f94 actionCode 'import ch.ivy.addon.portalkit.bo.ExpressProcess;
import ch.ivy.addon.portalkit.enums.ExpressMessageType;
import ch.ivy.addon.portalkit.util.ExpressManagementUtils;

ExpressManagementUtils utils = new ExpressManagementUtils();
List<String> results = utils.importExpressProcesses(in.importExpressFile.getFile());
try {
	in.importStatus = results.get(0).toString();
	in.importOutput = results.get(1).toString();
	if (!in.importStatus.equalsIgnoreCase(ExpressMessageType.FAILED.getLabel())) {
    in.expressProcessList.addAll(results.get(2) as List<ch.ivy.addon.portalkit.bo.ExpressProcess>);
  }
} catch (Exception e) {
  in.importStatus = ExpressMessageType.FAILED.getLabel();
  in.importOutput = e.getMessage();
}


if (in.importStatus.equalsIgnoreCase(ExpressMessageType.FAILED.getLabel())) {
  in.isError = true;
}' #txt
Es0 f94 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>import express process</name>
    </language>
</elementInfo>
' #txt
Es0 f94 440 362 144 44 -65 -8 #rect
Es0 f94 @|StepIcon #fIcon
Es0 f89 guid 16F82C1E20EF8A16 #txt
Es0 f89 method importExpress(org.primefaces.event.FileUploadEvent) #txt
Es0 f89 inParameterDecl '<org.primefaces.event.FileUploadEvent event> param;' #txt
Es0 f89 inParameterMapAction 'out.importExpressFile=param.event;
' #txt
Es0 f89 outParameterDecl '<> result;' #txt
Es0 f89 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>importExpress(FileUploadEvent)</name>
    </language>
</elementInfo>
' #txt
Es0 f89 83 371 26 26 -61 26 #rect
Es0 f89 @|UdMethodIcon #fIcon
Es0 f16 actionTable 'out=in;
' #txt
Es0 f16 actionCode 'import ch.ivy.addon.portalkit.enums.ExpressMessageType;
import javax.faces.context.FacesContext;

FacesContext.getCurrentInstance().addMessage("import-express-form:import-express-dialog-message", in.validateMessage);
in.importStatus = ExpressMessageType.FAILED.getLabel();' #txt
Es0 f16 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>throw messages</name>
    </language>
</elementInfo>
' #txt
Es0 f16 456 266 112 44 -46 -8 #rect
Es0 f16 @|StepIcon #fIcon
Es0 f11 109 384 168 384 #arcP
Es0 f14 280 384 336 384 #arcP
Es0 f17 expr in #txt
Es0 f17 outCond in.isError #txt
Es0 f17 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>validation failed</name>
    </language>
</elementInfo>
' #txt
Es0 f17 352 368 456 288 #arcP
Es0 f17 1 352 288 #addKink
Es0 f17 1 0.23780487804878048 0 -21 #arcLabel
Es0 f18 109 480 376 480 #arcP
Es0 f9 520 480 768 397 #arcP
Es0 f9 1 768 480 #addKink
Es0 f9 0 0.7789202822539795 0 0 #arcLabel
Es0 f20 actionTable 'out=in;
' #txt
Es0 f20 actionCode 'import ch.ivy.addon.portalkit.util.ExpressManagementUtils;

ExpressManagementUtils utils = new ExpressManagementUtils();
in.expressProcessList = utils.findExpressProcesses();' #txt
Es0 f20 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Init dataModel</name>
    </language>
</elementInfo>
' #txt
Es0 f20 168 170 112 44 -38 -8 #rect
Es0 f20 @|StepIcon #fIcon
Es0 f8 expr in #txt
Es0 f8 368 384 440 384 #arcP
Es0 f5 guid 16F9DB8B6F6455FE #txt
Es0 f5 method initialize() #txt
Es0 f5 inParameterDecl '<> param;' #txt
Es0 f5 outParameterDecl '<> result;' #txt
Es0 f5 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>initialize()</name>
    </language>
</elementInfo>
' #txt
Es0 f5 83 179 26 26 -25 15 #rect
Es0 f5 @|UdMethodIcon #fIcon
Es0 f6 339 179 26 26 0 12 #rect
Es0 f6 @|UdProcessEndIcon #fIcon
Es0 f7 109 192 168 192 #arcP
Es0 f12 109 96 267 96 #arcP
Es0 f2 280 192 339 192 #arcP
Es0 f33 actionTable 'out=in;
' #txt
Es0 f33 actionCode '// Do not store JSF Event in a Html Dialog data field
out.importExpressFile = null;' #txt
Es0 f33 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Release JSF Event</name>
    </language>
</elementInfo>
' #txt
Es0 f33 616 266 112 44 -52 -8 #rect
Es0 f33 @|StepIcon #fIcon
Es0 f19 568 288 616 288 #arcP
Es0 f19 0 0.7764586744807378 -21 0 #arcLabel
Es0 f15 728 288 768 371 #arcP
Es0 f15 1 768 288 #addKink
Es0 f15 0 0.9395405286076128 0 0 #arcLabel
Es0 f21 584 384 672 310 #arcP
Es0 f21 1 672 384 #addKink
Es0 f21 0 0.6361670253123877 0 0 #arcLabel
>Proto Es0 .type ch.ivy.addon.portalkit.component.ExpressManagement.ExpressManagementData #txt
>Proto Es0 .processKind HTML_DIALOG #txt
>Proto Es0 -8 -8 16 16 16 26 #rect
>Proto Es0 '' #fIcon
Es0 f96 mainOut f9 tail #connect
Es0 f9 head f3 mainIn #connect
Es0 f89 mainOut f11 tail #connect
Es0 f11 head f10 mainIn #connect
Es0 f10 mainOut f14 tail #connect
Es0 f14 head f13 in #connect
Es0 f17 head f16 mainIn #connect
Es0 f90 mainOut f18 tail #connect
Es0 f18 head f96 mainIn #connect
Es0 f13 out f17 tail #connect
Es0 f13 out f8 tail #connect
Es0 f8 head f94 mainIn #connect
Es0 f5 mainOut f7 tail #connect
Es0 f7 head f20 mainIn #connect
Es0 f0 mainOut f12 tail #connect
Es0 f12 head f1 mainIn #connect
Es0 f20 mainOut f2 tail #connect
Es0 f2 head f6 mainIn #connect
Es0 f16 mainOut f19 tail #connect
Es0 f19 head f33 mainIn #connect
Es0 f33 mainOut f15 tail #connect
Es0 f15 head f3 mainIn #connect
Es0 f94 mainOut f21 tail #connect
Es0 f21 head f33 mainIn #connect
