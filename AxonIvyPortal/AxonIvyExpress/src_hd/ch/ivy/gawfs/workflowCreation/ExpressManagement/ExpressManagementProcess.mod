[Ivy]
16F404B50B5F4DEC 7.5.0 #module
>Proto >Proto Collection #zClass
Es0 ExpressManagementProcess Big #zClass
Es0 RD #cInfo
Es0 #process
Es0 @TextInP .type .type #zField
Es0 @TextInP .processKind .processKind #zField
Es0 @TextInP .xml .xml #zField
Es0 @TextInP .responsibility .responsibility #zField
Es0 @UdInit f0 '' #zField
Es0 @UdProcessEnd f1 '' #zField
Es0 @UdMethod f90 '' #zField
Es0 @GridStep f96 '' #zField
Es0 @GridStep f94 '' #zField
Es0 @UdMethod f89 '' #zField
Es0 @UdProcessEnd f3 '' #zField
Es0 @PushWFArc f4 '' #zField
Es0 @PushWFArc f9 '' #zField
Es0 @GridStep f10 '' #zField
Es0 @PushWFArc f11 '' #zField
Es0 @Alternative f13 '' #zField
Es0 @PushWFArc f14 '' #zField
Es0 @PushWFArc f12 '' #zField
Es0 @GridStep f16 '' #zField
Es0 @PushWFArc f17 '' #zField
Es0 @PushWFArc f15 '' #zField
Es0 @PushWFArc f18 '' #zField
Es0 @PushWFArc f2 '' #zField
>Proto Es0 Es0 ExpressManagementProcess #zField
Es0 f0 guid 16F404B50BFCCFFB #txt
Es0 f0 method start(ch.ivy.addon.portalkit.datamodel.ExpressProcessLazyDataModel) #txt
Es0 f0 inParameterDecl '<ch.ivy.addon.portalkit.datamodel.ExpressProcessLazyDataModel dataModel> param;' #txt
Es0 f0 inParameterMapAction 'out.dataModel=param.dataModel;
' #txt
Es0 f0 outParameterDecl '<> result;' #txt
Es0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start(ExpressProcessLazyDataModel)</name>
    </language>
</elementInfo>
' #txt
Es0 f0 83 115 26 26 -16 15 #rect
Es0 f0 @|UdInitIcon #fIcon
Es0 f1 371 115 26 26 0 12 #rect
Es0 f1 @|UdProcessEndIcon #fIcon
Es0 f90 guid 16F404C71BD53E2F #txt
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
Es0 f90 83 435 26 26 -39 23 #rect
Es0 f90 @|UdMethodIcon #fIcon
Es0 f96 actionTable 'out=in;
' #txt
Es0 f96 actionCode 'import ch.ivy.gawfs.ExpressProcessUtils;

if (in.selectedExpresses != null && !in.selectedExpresses.isEmpty()) {
  ExpressProcessUtils utils = new ExpressProcessUtils();	
  in.exportExpressFile = utils.exportExpressProcess(in.selectedExpresses);
}

' #txt
Es0 f96 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>export express process</name>
    </language>
</elementInfo>
' #txt
Es0 f96 376 426 144 44 -64 -8 #rect
Es0 f96 @|StepIcon #fIcon
Es0 f94 actionTable 'out=in;
' #txt
Es0 f94 actionCode 'import ch.ivy.gawfs.ExpressProcessUtils;

ExpressProcessUtils utils = new ExpressProcessUtils();	
List<String> results = utils.importExpressProcesses(in.importExpressFile.getFile());
in.importStatus = results.get(0);
in.importOutput = results.get(1);


' #txt
Es0 f94 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>import express process</name>
    </language>
</elementInfo>
' #txt
Es0 f94 440 330 144 44 -65 -8 #rect
Es0 f94 @|StepIcon #fIcon
Es0 f89 guid 16F404C71BD56412 #txt
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
Es0 f89 83 339 26 26 -61 26 #rect
Es0 f89 @|UdMethodIcon #fIcon
Es0 f3 755 339 26 26 0 12 #rect
Es0 f3 @|UdProcessEndIcon #fIcon
Es0 f4 584 352 755 352 #arcP
Es0 f4 0 0.43627200676149086 0 0 #arcLabel
Es0 f9 520 448 768 365 #arcP
Es0 f9 1 768 448 #addKink
Es0 f9 0 0.7789202822539795 0 0 #arcLabel
Es0 f10 actionTable 'out=in;
' #txt
Es0 f10 actionCode 'import org.apache.commons.lang3.StringUtils;
import javax.faces.application.FacesMessage;
import javax.ws.rs.core.MediaType;

in.importOutput = StringUtils.EMPTY;

if (in.importExpressFile == null || in.importExpressFile.getFile().getSize() == 0) {
  in.isError = true;
  in.validateMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, ivy.cms.co("/Dialogs/ExpressManagement/fileEmptyMessage"), null);
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
Es0 f10 168 330 112 44 -21 -8 #rect
Es0 f10 @|StepIcon #fIcon
Es0 f11 109 352 168 352 #arcP
Es0 f13 336 336 32 32 0 16 #rect
Es0 f13 @|AlternativeIcon #fIcon
Es0 f14 280 352 336 352 #arcP
Es0 f12 expr in #txt
Es0 f12 outCond !in.isError #txt
Es0 f12 368 352 440 352 #arcP
Es0 f16 actionTable 'out=in;
' #txt
Es0 f16 actionCode 'import javax.faces.context.FacesContext;

FacesContext.getCurrentInstance().addMessage("import-express-form:import-express-dialog-message", in.validateMessage);
in.isError = false;' #txt
Es0 f16 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>throw messages</name>
    </language>
</elementInfo>
' #txt
Es0 f16 456 234 112 44 -46 -8 #rect
Es0 f16 @|StepIcon #fIcon
Es0 f17 expr in #txt
Es0 f17 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>validation failed</name>
    </language>
</elementInfo>
' #txt
Es0 f17 352 336 456 256 #arcP
Es0 f17 1 352 256 #addKink
Es0 f17 1 0.23780487804878048 0 -21 #arcLabel
Es0 f15 568 256 768 339 #arcP
Es0 f15 1 768 256 #addKink
Es0 f15 1 0.23780487804878048 0 -21 #arcLabel
Es0 f18 109 448 376 448 #arcP
Es0 f2 109 128 371 128 #arcP
>Proto Es0 .type ch.ivy.gawfs.workflowCreation.ExpressManagement.ExpressManagementData #txt
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
Es0 f0 mainOut f2 tail #connect
Es0 f2 head f1 mainIn #connect
