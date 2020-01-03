[Ivy]
16F404B50B5F4DEC 7.5.0 #module
>Proto >Proto Collection #zClass
Es0 ExpressHelperProcess Big #zClass
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
Es0 @PushWFArc f95 '' #zField
Es0 @PushWFArc f97 '' #zField
Es0 @GridStep f6 '' #zField
Es0 @PushWFArc f7 '' #zField
Es0 @PushWFArc f2 '' #zField
Es0 @UdProcessEnd f3 '' #zField
Es0 @PushWFArc f4 '' #zField
Es0 @PushWFArc f9 '' #zField
>Proto Es0 Es0 ExpressHelperProcess #zField
Es0 f0 guid 16F404B50BFCCFFB #txt
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
Es0 f0 83 115 26 26 -16 15 #rect
Es0 f0 @|UdInitIcon #fIcon
Es0 f1 339 115 26 26 0 12 #rect
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
Es0 f90 83 339 26 26 -39 23 #rect
Es0 f90 @|UdMethodIcon #fIcon
Es0 f96 actionTable 'out=in;
' #txt
Es0 f96 actionCode 'import javax.faces.context.FacesContext;
import javax.faces.application.FacesMessage;
import ch.ivy.gawfs.ExpressProcessUtils;

if (in.selectedExpresses == null || in.selectedExpresses.isEmpty()) {
ivy.log.info("== huhu");
	  FacesMessage message = new FacesMessage("Process Unselected");
    FacesContext.getCurrentInstance().addMessage("express-helper-messages", message);
} else {
ivy.log.info("== haha");
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
Es0 f96 248 330 144 44 -64 -8 #rect
Es0 f96 @|StepIcon #fIcon
Es0 f94 actionTable 'out=in;
' #txt
Es0 f94 actionCode 'import javax.faces.context.FacesContext;
import javax.faces.application.FacesMessage;
import ch.ivy.gawfs.ExpressProcessUtils;

if (in.importExpressFile == null) {
ivy.log.info("== huhu");
in.importOutput = "Null File";
	  FacesMessage message = new FacesMessage("Process Unselected");
    FacesContext.getCurrentInstance().addMessage("express-helper-messages", message);
} else {
ivy.log.info("== haha");
  ExpressProcessUtils utils = new ExpressProcessUtils();	
  in.importOutput = utils.importExpressProcess(in.importExpressFile.getFile());
}


' #txt
Es0 f94 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>import express process</name>
    </language>
</elementInfo>
' #txt
Es0 f94 248 234 144 44 -65 -8 #rect
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
Es0 f89 83 243 26 26 -40 21 #rect
Es0 f89 @|UdMethodIcon #fIcon
Es0 f95 109 256 248 256 #arcP
Es0 f97 109 352 248 352 #arcP
Es0 f6 actionTable 'out=in;
' #txt
Es0 f6 actionCode 'import ch.ivy.gawfs.ExpressProcessUtils;

ExpressProcessUtils utils = new ExpressProcessUtils();
in.expressWorkflows = utils.findExpressProcesses();' #txt
Es0 f6 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>fetch express process</name>
    </language>
</elementInfo>
' #txt
Es0 f6 160 106 128 44 -60 -8 #rect
Es0 f6 @|StepIcon #fIcon
Es0 f7 109 128 160 128 #arcP
Es0 f2 288 128 339 128 #arcP
Es0 f3 563 243 26 26 0 12 #rect
Es0 f3 @|UdProcessEndIcon #fIcon
Es0 f4 392 256 563 256 #arcP
Es0 f4 0 0.43627200676149086 0 0 #arcLabel
Es0 f9 392 352 576 269 #arcP
Es0 f9 1 576 352 #addKink
Es0 f9 0 0.7789202822539795 0 0 #arcLabel
>Proto Es0 .type ch.ivy.gawfs.workflowCreation.ExpressHelper.ExpressHelperData #txt
>Proto Es0 .processKind HTML_DIALOG #txt
>Proto Es0 -8 -8 16 16 16 26 #rect
>Proto Es0 '' #fIcon
Es0 f89 mainOut f95 tail #connect
Es0 f95 head f94 mainIn #connect
Es0 f90 mainOut f97 tail #connect
Es0 f97 head f96 mainIn #connect
Es0 f0 mainOut f7 tail #connect
Es0 f7 head f6 mainIn #connect
Es0 f6 mainOut f2 tail #connect
Es0 f2 head f1 mainIn #connect
Es0 f94 mainOut f4 tail #connect
Es0 f4 head f3 mainIn #connect
Es0 f96 mainOut f9 tail #connect
Es0 f9 head f3 mainIn #connect
