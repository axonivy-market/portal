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
Es0 @PushWFArc f15 '' #zField
Es0 @PushWFArc f11 '' #zField
Es0 @PushWFArc f4 '' #zField
Es0 @PushWFArc f14 '' #zField
Es0 @PushWFArc f12 '' #zField
Es0 @PushWFArc f17 '' #zField
Es0 @PushWFArc f18 '' #zField
Es0 @PushWFArc f9 '' #zField
Es0 @UdMethod f5 '' #zField
Es0 @UdProcessEnd f6 '' #zField
Es0 @GridStep f20 '' #zField
Es0 @PushWFArc f21 '' #zField
Es0 @PushWFArc f2 '' #zField
Es0 @PushWFArc f7 '' #zField
>Proto Es0 Es0 ExpressManagementProcess #zField
Es0 f1 339 83 26 26 0 12 #rect
Es0 f1 @|UdProcessEndIcon #fIcon
Es0 f13 336 304 32 32 0 16 #rect
Es0 f13 @|AlternativeIcon #fIcon
Es0 f10 actionTable 'out=in;
' #txt
Es0 f10 actionCode 'import org.apache.commons.lang3.StringUtils;
import javax.faces.application.FacesMessage;
import javax.ws.rs.core.MediaType;

in.isError = false;
in.importOutput = StringUtils.EMPTY;

if (in.importExpressFile == null || in.importExpressFile.getFile().getSize() == 0) {
  in.isError = true;
  in.validateMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, ivy.cms.co("/ch.ivy.addon.portalkit.ui.jsf/components/expressManagement/fileEmptyMessage"), null);
} else if (!in.importExpressFile.getFile().getContentType().equals(MediaType.APPLICATION_JSON)) {
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
Es0 f10 168 298 112 44 -21 -8 #rect
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
Es0 f90 83 403 26 26 -39 23 #rect
Es0 f90 @|UdMethodIcon #fIcon
Es0 f3 755 307 26 26 0 12 #rect
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
Es0 f96 376 394 144 44 -64 -8 #rect
Es0 f96 @|StepIcon #fIcon
Es0 f94 actionTable 'out=in;
' #txt
Es0 f94 actionCode 'import ch.ivy.addon.portalkit.enums.ExpressManagementProperty;
import ch.ivy.addon.portalkit.util.ExpressManagementUtils;

ExpressManagementUtils utils = new ExpressManagementUtils();
List<String> results = utils.importExpressProcesses(in.importExpressFile.getFile());
in.importStatus = results.get(0);
in.importOutput = results.get(1);

if (in.importStatus.equalsIgnoreCase(ExpressManagementProperty.FAILED.getLabel())) {
  in.isError = true;
}
' #txt
Es0 f94 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>import express process</name>
    </language>
</elementInfo>
' #txt
Es0 f94 440 298 144 44 -65 -8 #rect
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
Es0 f89 83 307 26 26 -61 26 #rect
Es0 f89 @|UdMethodIcon #fIcon
Es0 f16 actionTable 'out=in;
' #txt
Es0 f16 actionCode 'import ch.ivy.addon.portalkit.enums.ExpressManagementProperty;
import javax.faces.context.FacesContext;

FacesContext.getCurrentInstance().addMessage("import-express-form:import-express-dialog-message", in.validateMessage);
in.importStatus = ExpressManagementProperty.FAILED.getLabel();' #txt
Es0 f16 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>throw messages</name>
    </language>
</elementInfo>
' #txt
Es0 f16 456 202 112 44 -46 -8 #rect
Es0 f16 @|StepIcon #fIcon
Es0 f15 568 224 768 307 #arcP
Es0 f15 1 768 224 #addKink
Es0 f15 1 0.23780487804878048 0 -21 #arcLabel
Es0 f11 109 320 168 320 #arcP
Es0 f4 584 320 755 320 #arcP
Es0 f4 0 0.43627200676149086 0 0 #arcLabel
Es0 f14 280 320 336 320 #arcP
Es0 f12 expr in #txt
Es0 f12 outCond !in.isError #txt
Es0 f12 368 320 440 320 #arcP
Es0 f17 expr in #txt
Es0 f17 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>validation failed</name>
    </language>
</elementInfo>
' #txt
Es0 f17 352 304 456 224 #arcP
Es0 f17 1 352 224 #addKink
Es0 f17 1 0.23780487804878048 0 -21 #arcLabel
Es0 f18 109 416 376 416 #arcP
Es0 f9 520 416 768 333 #arcP
Es0 f9 1 768 416 #addKink
Es0 f9 0 0.7789202822539795 0 0 #arcLabel
Es0 f5 guid 16F83F5852F5775F #txt
Es0 f5 method updateTableRowCount() #txt
Es0 f5 inParameterDecl '<> param;' #txt
Es0 f5 inActionCode 'if (!out.isError) {
  out.dataModel.updateRowCount();
}' #txt
Es0 f5 outParameterDecl '<> result;' #txt
Es0 f5 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>updateTableRowCount()</name>
    </language>
</elementInfo>
' #txt
Es0 f5 83 531 26 26 -25 15 #rect
Es0 f5 @|UdMethodIcon #fIcon
Es0 f6 403 531 26 26 0 12 #rect
Es0 f6 @|UdProcessEndIcon #fIcon
Es0 f20 actionTable 'out=in;
' #txt
Es0 f20 actionCode 'import ch.ivy.addon.portalkit.datamodel.ExpressProcessLazyDataModel;

in.dataModel = new ExpressProcessLazyDataModel();' #txt
Es0 f20 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Init dataModel</name>
    </language>
</elementInfo>
' #txt
Es0 f20 168 74 112 44 -38 -8 #rect
Es0 f20 @|StepIcon #fIcon
Es0 f21 109 96 168 96 #arcP
Es0 f2 280 96 339 96 #arcP
Es0 f7 109 544 403 544 #arcP
>Proto Es0 .type ch.ivy.addon.portalkit.component.ExpressManagement.ExpressManagementData #txt
>Proto Es0 .processKind HTML_DIALOG #txt
>Proto Es0 -8 -8 16 16 16 26 #rect
>Proto Es0 '' #fIcon
Es0 f94 mainOut f4 tail #connect
Es0 f4 head f3 mainIn #connect
Es0 f96 mainOut f9 tail #connect
Es0 f9 head f3 mainIn #connect
Es0 f89 mainOut f11 tail #connect
Es0 f11 head f10 mainIn #connect
Es0 f10 mainOut f14 tail #connect
Es0 f14 head f13 in #connect
Es0 f13 out f12 tail #connect
Es0 f12 head f94 mainIn #connect
Es0 f13 out f17 tail #connect
Es0 f17 head f16 mainIn #connect
Es0 f16 mainOut f15 tail #connect
Es0 f15 head f3 mainIn #connect
Es0 f90 mainOut f18 tail #connect
Es0 f18 head f96 mainIn #connect
Es0 f0 mainOut f21 tail #connect
Es0 f21 head f20 mainIn #connect
Es0 f20 mainOut f2 tail #connect
Es0 f2 head f1 mainIn #connect
Es0 f5 mainOut f7 tail #connect
Es0 f7 head f6 mainIn #connect
