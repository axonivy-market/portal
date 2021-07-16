[Ivy]
169C767D49174098 9.2.0 #module
>Proto >Proto Collection #zClass
As0 ApplicationDialogProcess Big #zClass
As0 RD #cInfo
As0 #process
As0 @TextInP .type .type #zField
As0 @TextInP .processKind .processKind #zField
As0 @AnnotationInP-0n ai ai #zField
As0 @MessageFlowInP-0n messageIn messageIn #zField
As0 @MessageFlowOutP-0n messageOut messageOut #zField
As0 @TextInP .xml .xml #zField
As0 @TextInP .responsibility .responsibility #zField
As0 @UdProcessEnd f17 '' #zField
As0 @GridStep f6 '' #zField
As0 @UdMethod f9 '' #zField
As0 @GridStep f13 '' #zField
As0 @UdEvent f28 '' #zField
As0 @UdProcessEnd f1 '' #zField
As0 @GridStep f11 '' #zField
As0 @UdEvent f10 '' #zField
As0 @UdInit f0 '' #zField
As0 @UdEvent f3 '' #zField
As0 @UdMethod f24 '' #zField
As0 @UdExitEnd f4 '' #zField
As0 @UdProcessEnd f23 '' #zField
As0 @UdProcessEnd f19 '' #zField
As0 @PushWFArc f22 '' #zField
As0 @PushWFArc f16 '' #zField
As0 @PushWFArc f34 '' #zField
As0 @PushWFArc f5 '' #zField
As0 @PushWFArc f2 '' #zField
As0 @PushWFArc f14 '' #zField
As0 @PushWFArc f7 '' #zField
As0 @PushWFArc f20 '' #zField
As0 @GridStep f12 '' #zField
As0 @PushWFArc f21 '' #zField
As0 @UdProcessEnd f18 '' #zField
As0 @PushWFArc f15 '' #zField
As0 @UdMethod f8 '' #zField
As0 @UdExitEnd f25 '' #zField
As0 @PushWFArc f27 '' #zField
>Proto As0 As0 ApplicationDialogProcess #zField
As0 f17 467 283 26 26 0 12 #rect
As0 f6 actionTable 'out=in;
' #txt
As0 f6 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Init</name>
    </language>
</elementInfo>
' #txt
As0 f6 200 42 112 44 -8 -8 #rect
As0 f9 guid 169C76B36E1DA3A7 #txt
As0 f9 method delete(com.axonivy.portal.developerexamples.showcase.GdprFile) #txt
As0 f9 inParameterDecl '<com.axonivy.portal.developerexamples.showcase.GdprFile file> param;' #txt
As0 f9 inParameterMapAction 'out.selectedGdprFile=param.file;
' #txt
As0 f9 outParameterDecl '<> result;' #txt
As0 f9 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>delete(File)</name>
    </language>
</elementInfo>
' #txt
As0 f9 83 475 26 26 -52 15 #rect
As0 f13 actionTable 'out=in;
' #txt
As0 f13 actionCode 'try{
	in.gdprRequest.files.remove(in.selectedGdprFile);
	in.selectedGdprFile.getFile().getJavaFile().delete();
	in.selectedGdprFile= null;
	in.renderPdfVeiwer = false;
}
catch(Exception e){
	ivy.log.error("File was not deleted, ", e);
}
' #txt
As0 f13 security system #txt
As0 f13 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Delete</name>
    </language>
</elementInfo>
' #txt
As0 f13 232 466 112 44 -18 -8 #rect
As0 f28 guid 169C76B36E1CC2C6 #txt
As0 f28 actionTable 'out=in;
' #txt
As0 f28 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>proceed</name>
    </language>
</elementInfo>
' #txt
As0 f28 83 539 26 26 -47 15 #rect
As0 f1 371 51 26 26 0 12 #rect
As0 f11 actionTable 'out=in;
' #txt
As0 f11 actionCode 'import org.apache.commons.io.FileUtils;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

if(!in.isStrongboxProcess){
	File f = new File(in.selectedGdprFile.getFile().getName());
	
	ivy.log.error(in.selectedGdprFile.getFile().getAbsolutePath());
	FileUtils.copyFile(in.selectedGdprFile.getFile().getJavaFile(), f.getJavaFile());

	in.pdfFilenameUrl = ivy.html.fileref(f);

	if(in.selectedGdprFile.getFile().getName().endsWith(".pdf")){
		in.renderPdfVeiwer = true;
	}
	else{
		in.renderPdfVeiwer = false;
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, ivy.cms.co("/Dialogs/GdprRequest/Messages/warning"), ivy.cms.co("/Dialogs/GdprRequest/Messages/onlyPdfRendering")));
	}	
}' #txt
As0 f11 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Set URL</name>
    </language>
</elementInfo>
' #txt
As0 f11 232 274 112 44 -23 -8 #rect
As0 f10 guid 169C76B36E183099 #txt
As0 f10 actionTable 'out=in;
' #txt
As0 f10 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>selectPeriod</name>
    </language>
</elementInfo>
' #txt
As0 f10 83 283 26 26 -47 15 #rect
As0 f0 guid 169C76B36E187A99 #txt
As0 f0 method start(com.axonivy.portal.developerexamples.showcase.GdprRequest) #txt
As0 f0 inParameterDecl '<com.axonivy.portal.developerexamples.showcase.GdprRequest gdprRequest> param;' #txt
As0 f0 inParameterMapAction 'out.gdprRequest=param.gdprRequest;
' #txt
As0 f0 outParameterDecl '<> result;' #txt
As0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start(GdprRequest)</name>
    </language>
</elementInfo>
' #txt
As0 f0 83 51 26 26 -16 15 #rect
As0 f3 guid 169C76B36E13A297 #txt
As0 f3 actionTable 'out=in;
' #txt
As0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>close</name>
    </language>
</elementInfo>
' #txt
As0 f3 83 155 26 26 -15 12 #rect
As0 f24 guid 169C76B36E1C02EC #txt
As0 f24 method handleFileUploader(org.primefaces.event.FileUploadEvent) #txt
As0 f24 inParameterDecl '<org.primefaces.event.FileUploadEvent event> param;' #txt
As0 f24 inParameterMapAction 'out.uploadEvent=param.event;
' #txt
As0 f24 inActionCode 'import com.axonivy.portal.developerexamples.showcase.GdprRequest;
import com.axonivy.portal.developerexamples.showcase.GdprFile;
import java.io.FileOutputStream;
import ch.ivyteam.io.FileUtil;

GdprFile gdprFile = new GdprFile();
File iFile = new File(out.uploadEvent.getFile().getFileName(), true);
iFile.writeBinary(out.uploadEvent.getFile().getContents());
iFile.createNewFile();

gdprFile.file = iFile;
gdprFile.isNewAdded = true;
out.gdprRequest.files.add(gdprFile);

// Do not store JSF Event in a Html Dialog data field
out.uploadEvent = null;' #txt
As0 f24 outParameterDecl '<> result;' #txt
As0 f24 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>handleFileUploader(FileUploadEvent)</name>
    </language>
</elementInfo>
' #txt
As0 f24 83 219 26 26 -103 15 #rect
As0 f4 243 155 26 26 0 12 #rect
As0 f23 467 219 26 26 0 12 #rect
As0 f19 467 475 26 26 0 12 #rect
As0 f22 expr out #txt
As0 f22 344 488 467 488 #arcP
As0 f16 expr out #txt
As0 f16 109 488 232 488 #arcP
As0 f34 expr out #txt
As0 f34 109 232 467 232 #arcP
As0 f5 expr out #txt
As0 f5 109 168 243 168 #arcP
As0 f2 expr out #txt
As0 f2 312 64 371 64 #arcP
As0 f14 expr out #txt
As0 f14 109 296 232 296 #arcP
As0 f7 expr out #txt
As0 f7 109 64 200 64 #arcP
As0 f20 expr out #txt
As0 f20 344 296 467 296 #arcP
As0 f12 actionTable 'out=in;
' #txt
As0 f12 actionCode 'import java.io.InputStream;
import org.primefaces.model.DefaultStreamedContent;
import java.io.FileInputStream;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

ServletContext servletContext = FacesContext.getCurrentInstance().getExternalContext().getContext() as ServletContext;

try {
		
	if(in.#selectedGdprFile != null){ 
	   	FileInputStream stream = new FileInputStream(in.selectedGdprFile.getFile().getJavaFile().getAbsolutePath());
	 	String contentType = servletContext.getMimeType("");
	  	 in.streamedContent = new DefaultStreamedContent(stream, contentType, in.selectedGdprFile.getFile().getJavaFile().getName());
	}else{
		ivy.log.error("File is null");
	}
}
catch (Exception e){
	ivy.log.error(e);
}' #txt
As0 f12 security system #txt
As0 f12 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Download</name>
    </language>
</elementInfo>
' #txt
As0 f12 232 352 112 48 -28 -8 #rect
As0 f21 expr out #txt
As0 f21 344 376 467 376 #arcP
As0 f18 467 363 26 26 0 12 #rect
As0 f15 expr out #txt
As0 f15 109 376 232 376 #arcP
As0 f8 guid 169C76B36E2BC446 #txt
As0 f8 method show(com.axonivy.portal.developerexamples.showcase.GdprFile) #txt
As0 f8 inParameterDecl '<com.axonivy.portal.developerexamples.showcase.GdprFile file> param;' #txt
As0 f8 inParameterMapAction 'out.selectedGdprFile=param.file;
' #txt
As0 f8 outParameterDecl '<> result;' #txt
As0 f8 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>show(File)</name>
    </language>
</elementInfo>
' #txt
As0 f8 83 363 26 26 -52 15 #rect
As0 f25 459 539 26 26 0 12 #rect
As0 f27 expr out #txt
As0 f27 109 552 459 552 #arcP
As0 f27 0 0.5126441679273225 0 0 #arcLabel
>Proto As0 .type com.axonivy.portal.developerexamples.showcase.ApplicationDialog.ApplicationDialogData #txt
>Proto As0 .processKind HTML_DIALOG #txt
>Proto As0 -8 -8 16 16 16 26 #rect
As0 f3 mainOut f5 tail #connect
As0 f5 head f4 mainIn #connect
As0 f6 mainOut f2 tail #connect
As0 f2 head f1 mainIn #connect
As0 f0 mainOut f7 tail #connect
As0 f7 head f6 mainIn #connect
As0 f10 mainOut f14 tail #connect
As0 f14 head f11 mainIn #connect
As0 f8 mainOut f15 tail #connect
As0 f15 head f12 mainIn #connect
As0 f9 mainOut f16 tail #connect
As0 f16 head f13 mainIn #connect
As0 f11 mainOut f20 tail #connect
As0 f20 head f17 mainIn #connect
As0 f12 mainOut f21 tail #connect
As0 f21 head f18 mainIn #connect
As0 f13 mainOut f22 tail #connect
As0 f22 head f19 mainIn #connect
As0 f24 mainOut f34 tail #connect
As0 f34 head f23 mainIn #connect
As0 f28 mainOut f27 tail #connect
As0 f27 head f25 mainIn #connect
