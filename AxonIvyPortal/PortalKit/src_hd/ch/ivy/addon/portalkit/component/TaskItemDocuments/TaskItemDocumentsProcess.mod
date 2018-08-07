[Ivy]
1549452C001D8A69 3.20 #module
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
Ts0 @PushWFArc f2 '' #zField
Ts0 @RichDialogMethodStart f21 '' #zField
Ts0 @RichDialogMethodStart f57 '' #zField
Ts0 @Alternative f35 '' #zField
Ts0 @RichDialogProcessEnd f60 '' #zField
Ts0 @GridStep f26 '' #zField
Ts0 @GridStep f49 '' #zField
Ts0 @RichDialogProcessEnd f45 '' #zField
Ts0 @Alternative f48 '' #zField
Ts0 @GridStep f47 '' #zField
Ts0 @RichDialogMethodStart f44 '' #zField
Ts0 @RichDialogMethodStart f53 '' #zField
Ts0 @RichDialogMethodStart f6 '' #zField
Ts0 @CallSub f24 '' #zField
Ts0 @RichDialogProcessEnd f54 '' #zField
Ts0 @Alternative f28 '' #zField
Ts0 @RichDialogProcessEnd f40 '' #zField
Ts0 @RichDialogMethodStart f39 '' #zField
Ts0 @CallSub f58 '' #zField
Ts0 @GridStep f13 '' #zField
Ts0 @CallSub f16 '' #zField
Ts0 @GridStep f56 '' #zField
Ts0 @GridStep f62 '' #zField
Ts0 @CallSub f42 '' #zField
Ts0 @RichDialogProcessEnd f18 '' #zField
Ts0 @RichDialogProcessEnd f22 '' #zField
Ts0 @GridStep f30 '' #zField
Ts0 @PushWFArc f17 '' #zField
Ts0 @PushWFArc f59 '' #zField
Ts0 @PushWFArc f25 '' #zField
Ts0 @PushWFArc f23 '' #zField
Ts0 @PushWFArc f27 '' #zField
Ts0 @PushWFArc f29 '' #zField
Ts0 @PushWFArc f15 '' #zField
Ts0 @PushWFArc f32 '' #zField
Ts0 @PushWFArc f31 '' #zField
Ts0 @PushWFArc f36 '' #zField
Ts0 @PushWFArc f37 '' #zField
Ts0 @PushWFArc f38 '' #zField
Ts0 @PushWFArc f43 '' #zField
Ts0 @PushWFArc f46 '' #zField
Ts0 @PushWFArc f50 '' #zField
Ts0 @PushWFArc f51 '' #zField
Ts0 @PushWFArc f52 '' #zField
Ts0 @PushWFArc f55 '' #zField
Ts0 @PushWFArc f63 '' #zField
Ts0 @PushWFArc f61 '' #zField
Ts0 @PushWFArc f20 '' #zField
Ts0 @PushWFArc f10 '' #zField
Ts0 @PushWFArc f3 '' #zField
>Proto Ts0 Ts0 TaskItemDocumentsProcess #zField
Ts0 f0 guid 1549452C02A2D7AA #txt
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
Ts0 f0 53 85 22 22 14 0 #rect
Ts0 f0 @|RichDialogInitStartIcon #fIcon
Ts0 f1 type ch.ivy.addon.portalkit.component.TaskItemDocuments.TaskItemDocumentsData #txt
Ts0 f1 53 213 22 22 14 0 #rect
Ts0 f1 @|RichDialogProcessEndIcon #fIcon
Ts0 f2 expr out #txt
Ts0 f2 64 107 64 213 #arcP
Ts0 f21 guid 1549458BA614E531 #txt
Ts0 f21 type ch.ivy.addon.portalkit.component.TaskItemDocuments.TaskItemDocumentsData #txt
Ts0 f21 method downloadDocument(ch.ivy.addon.portalkit.bo.RemoteTask,ch.ivy.ws.addon.IvyDocument) #txt
Ts0 f21 disableUIEvents false #txt
Ts0 f21 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<ch.ivy.addon.portalkit.bo.RemoteTask task,ch.ivy.ws.addon.IvyDocument document> param = methodEvent.getInputArguments();
' #txt
Ts0 f21 inParameterMapAction 'out.document=param.document;
out.task=param.task;
' #txt
Ts0 f21 outParameterDecl '<> result;
' #txt
Ts0 f21 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>downloadDocument
(RemoteTask,IvyDocument)</name>
        <nameStyle>41,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ts0 f21 733 85 22 22 14 0 #rect
Ts0 f21 @|RichDialogMethodStartIcon #fIcon
Ts0 f57 guid 1549458BA665DE38 #txt
Ts0 f57 type ch.ivy.addon.portalkit.component.TaskItemDocuments.TaskItemDocumentsData #txt
Ts0 f57 method resetMessageUploadDocument() #txt
Ts0 f57 disableUIEvents false #txt
Ts0 f57 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<> param = methodEvent.getInputArguments();
' #txt
Ts0 f57 outParameterDecl '<> result;
' #txt
Ts0 f57 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>resetMessageUploadDocument()</name>
        <nameStyle>28,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ts0 f57 1197 77 22 22 14 -9 #rect
Ts0 f57 @|RichDialogMethodStartIcon #fIcon
Ts0 f35 type ch.ivy.addon.portalkit.component.TaskItemDocuments.TaskItemDocumentsData #txt
Ts0 f35 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>valide file?</name>
        <nameStyle>12,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ts0 f35 442 130 28 28 14 0 #rect
Ts0 f35 @|AlternativeIcon #fIcon
Ts0 f60 type ch.ivy.addon.portalkit.component.TaskItemDocuments.TaskItemDocumentsData #txt
Ts0 f60 1197 277 22 22 14 0 #rect
Ts0 f60 @|RichDialogProcessEndIcon #fIcon
Ts0 f26 actionDecl 'ch.ivy.addon.portalkit.component.TaskItemDocuments.TaskItemDocumentsData out;
' #txt
Ts0 f26 actionTable 'out=in;
' #txt
Ts0 f26 actionCode 'import org.primefaces.model.DefaultStreamedContent;
import java.io.InputStream;

InputStream inputStream = in.documentContent.getInputStream();
in.documentFile = new DefaultStreamedContent(inputStream, in.document.getContentType(), in.document.getName());

' #txt
Ts0 f26 type ch.ivy.addon.portalkit.component.TaskItemDocuments.TaskItemDocumentsData #txt
Ts0 f26 726 204 36 24 20 -2 #rect
Ts0 f26 @|StepIcon #fIcon
Ts0 f49 actionDecl 'ch.ivy.addon.portalkit.component.TaskItemDocuments.TaskItemDocumentsData out;
' #txt
Ts0 f49 actionTable 'out=in;
' #txt
Ts0 f49 actionCode 'import ch.ivy.ws.addon.WsException;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

for (WsException error : in.errors) {
	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, error.userText, null));
}' #txt
Ts0 f49 type ch.ivy.addon.portalkit.component.TaskItemDocuments.TaskItemDocumentsData #txt
Ts0 f49 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>show error message</name>
        <nameStyle>18,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ts0 f49 1614 268 36 24 20 -2 #rect
Ts0 f49 @|StepIcon #fIcon
Ts0 f45 type ch.ivy.addon.portalkit.component.TaskItemDocuments.TaskItemDocumentsData #txt
Ts0 f45 941 269 22 22 14 0 #rect
Ts0 f45 @|RichDialogProcessEndIcon #fIcon
Ts0 f48 type ch.ivy.addon.portalkit.component.TaskItemDocuments.TaskItemDocumentsData #txt
Ts0 f48 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>remove successfully?</name>
        <nameStyle>20,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ts0 f48 1426 202 28 28 14 0 #rect
Ts0 f48 @|AlternativeIcon #fIcon
Ts0 f47 actionDecl 'ch.ivy.addon.portalkit.component.TaskItemDocuments.TaskItemDocumentsData out;
' #txt
Ts0 f47 actionTable 'out=in;
' #txt
Ts0 f47 actionCode in.documents.remove(in.document); #txt
Ts0 f47 type ch.ivy.addon.portalkit.component.TaskItemDocuments.TaskItemDocumentsData #txt
Ts0 f47 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>remove document from list
if no errors</name>
        <nameStyle>26,7
12,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ts0 f47 1422 268 36 24 21 -11 #rect
Ts0 f47 @|StepIcon #fIcon
Ts0 f44 guid 1549458BA6A1C11C #txt
Ts0 f44 type ch.ivy.addon.portalkit.component.TaskItemDocuments.TaskItemDocumentsData #txt
Ts0 f44 method onSelectDeletedDocument(ch.ivy.addon.portalkit.bo.RemoteTask,ch.ivy.ws.addon.IvyDocument) #txt
Ts0 f44 disableUIEvents false #txt
Ts0 f44 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<ch.ivy.addon.portalkit.bo.RemoteTask task,ch.ivy.ws.addon.IvyDocument document> param = methodEvent.getInputArguments();
' #txt
Ts0 f44 inParameterMapAction 'out.document=param.document;
out.task=param.task;
' #txt
Ts0 f44 outParameterDecl '<> result;
' #txt
Ts0 f44 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>onSelectDeletedDocument
(RemoteTask,IvyDocument)</name>
        <nameStyle>48,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ts0 f44 941 85 22 22 26 -17 #rect
Ts0 f44 @|RichDialogMethodStartIcon #fIcon
Ts0 f53 guid 1549458BA68441E0 #txt
Ts0 f53 type ch.ivy.addon.portalkit.component.TaskItemDocuments.TaskItemDocumentsData #txt
Ts0 f53 method uploadDocument(org.primefaces.event.FileUploadEvent) #txt
Ts0 f53 disableUIEvents false #txt
Ts0 f53 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<org.primefaces.event.FileUploadEvent uploadEvent> param = methodEvent.getInputArguments();
' #txt
Ts0 f53 inParameterMapAction 'out.documentUploadEvent=param.uploadEvent;
' #txt
Ts0 f53 outParameterDecl '<> result;
' #txt
Ts0 f53 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>uploadDocument(FileUploadEvent)</name>
        <nameStyle>31,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ts0 f53 445 85 22 22 14 0 #rect
Ts0 f53 @|RichDialogMethodStartIcon #fIcon
Ts0 f6 guid 1549458BA5FB13F8 #txt
Ts0 f6 type ch.ivy.addon.portalkit.component.TaskItemDocuments.TaskItemDocumentsData #txt
Ts0 f6 method initData(ch.ivy.addon.portalkit.bo.RemoteTask) #txt
Ts0 f6 disableUIEvents false #txt
Ts0 f6 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<ch.ivy.addon.portalkit.bo.RemoteTask task> param = methodEvent.getInputArguments();
' #txt
Ts0 f6 inParameterMapAction 'out.task=param.task;
' #txt
Ts0 f6 outParameterDecl '<> result;
' #txt
Ts0 f6 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>initData(RemoteTask)</name>
        <nameStyle>20,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ts0 f6 173 85 22 22 14 0 #rect
Ts0 f6 @|RichDialogMethodStartIcon #fIcon
Ts0 f24 type ch.ivy.addon.portalkit.component.TaskItemDocuments.TaskItemDocumentsData #txt
Ts0 f24 processCall MultiPortal/CaseService:downloadDocument(ch.ivy.addon.portalkit.persistence.domain.Server,Long,Long) #txt
Ts0 f24 doCall true #txt
Ts0 f24 requestActionDecl '<ch.ivy.addon.portalkit.persistence.domain.Server server,java.lang.Long documentId,java.lang.Long caseId> param;
' #txt
Ts0 f24 requestMappingAction 'param.server=in.task.applicationRegister.server;
param.documentId=in.document.id;
param.caseId=in.task.case.getId();
' #txt
Ts0 f24 responseActionDecl 'ch.ivy.addon.portalkit.component.TaskItemDocuments.TaskItemDocumentsData out;
' #txt
Ts0 f24 responseMappingAction 'out=in;
out.documentContent=result.documentContent;
out.errors=result.errors;
' #txt
Ts0 f24 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>CaseService
downloadDocument</name>
        <nameStyle>28,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ts0 f24 726 140 36 24 23 -8 #rect
Ts0 f24 @|CallSubIcon #fIcon
Ts0 f54 type ch.ivy.addon.portalkit.component.TaskItemDocuments.TaskItemDocumentsData #txt
Ts0 f54 445 413 22 22 14 0 #rect
Ts0 f54 @|RichDialogProcessEndIcon #fIcon
Ts0 f28 type ch.ivy.addon.portalkit.component.TaskItemDocuments.TaskItemDocumentsData #txt
Ts0 f28 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>upload successfully?</name>
        <nameStyle>20,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ts0 f28 442 298 28 28 14 0 #rect
Ts0 f28 @|AlternativeIcon #fIcon
Ts0 f40 type ch.ivy.addon.portalkit.component.TaskItemDocuments.TaskItemDocumentsData #txt
Ts0 f40 1429 341 22 22 14 0 #rect
Ts0 f40 @|RichDialogProcessEndIcon #fIcon
Ts0 f39 guid 1549458BA63D71F0 #txt
Ts0 f39 type ch.ivy.addon.portalkit.component.TaskItemDocuments.TaskItemDocumentsData #txt
Ts0 f39 method deleteDocument() #txt
Ts0 f39 disableUIEvents false #txt
Ts0 f39 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<> param = methodEvent.getInputArguments();
' #txt
Ts0 f39 outParameterDecl '<> result;
' #txt
Ts0 f39 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>deleteDocument()</name>
        <nameStyle>16,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ts0 f39 1429 77 22 22 14 0 #rect
Ts0 f39 @|RichDialogMethodStartIcon #fIcon
Ts0 f58 type ch.ivy.addon.portalkit.component.TaskItemDocuments.TaskItemDocumentsData #txt
Ts0 f58 processCall MultiPortal/CaseService:uploadDocument(String,javax.activation.DataHandler,ch.ivy.addon.portalkit.persistence.domain.Server,Long) #txt
Ts0 f58 doCall true #txt
Ts0 f58 requestActionDecl '<java.lang.String documentName,javax.activation.DataHandler documentContent,ch.ivy.addon.portalkit.persistence.domain.Server server,java.lang.Long caseId> param;
' #txt
Ts0 f58 requestMappingAction 'param.documentName=in.documentContent.getName();
param.documentContent=in.documentContent;
param.server=in.task.applicationRegister.server;
param.caseId=in.task.case.getId();
' #txt
Ts0 f58 responseActionDecl 'ch.ivy.addon.portalkit.component.TaskItemDocuments.TaskItemDocumentsData out;
' #txt
Ts0 f58 responseMappingAction 'out=in;
out.document=result.document;
out.errors=result.errors;
' #txt
Ts0 f58 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>CaseService</name>
        <nameStyle>11,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ts0 f58 438 252 36 24 20 -2 #rect
Ts0 f58 @|CallSubIcon #fIcon
Ts0 f13 actionDecl 'ch.ivy.addon.portalkit.component.TaskItemDocuments.TaskItemDocumentsData out;
' #txt
Ts0 f13 actionTable 'out=in;
' #txt
Ts0 f13 actionCode in.documents.add(in.document); #txt
Ts0 f13 type ch.ivy.addon.portalkit.component.TaskItemDocuments.TaskItemDocumentsData #txt
Ts0 f13 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>add document to list
if no errors</name>
        <nameStyle>21,7
12,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ts0 f13 438 356 36 24 21 -11 #rect
Ts0 f13 @|StepIcon #fIcon
Ts0 f16 type ch.ivy.addon.portalkit.component.TaskItemDocuments.TaskItemDocumentsData #txt
Ts0 f16 processCall MultiPortal/CaseService:findDocuments(ch.ivy.addon.portalkit.persistence.domain.Server,Long) #txt
Ts0 f16 doCall true #txt
Ts0 f16 requestActionDecl '<ch.ivy.addon.portalkit.persistence.domain.Server server,java.lang.Long caseId> param;
' #txt
Ts0 f16 requestMappingAction 'param.server=in.task.applicationRegister.server;
param.caseId=in.task.case.getId();
' #txt
Ts0 f16 responseActionDecl 'ch.ivy.addon.portalkit.component.TaskItemDocuments.TaskItemDocumentsData out;
' #txt
Ts0 f16 responseMappingAction 'out=in;
out.documents=result.documents;
out.errors=result.errors;
' #txt
Ts0 f16 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>CaseService
find documents</name>
        <nameStyle>26,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ts0 f16 166 164 36 24 20 -2 #rect
Ts0 f16 @|CallSubIcon #fIcon
Ts0 f56 actionDecl 'ch.ivy.addon.portalkit.component.TaskItemDocuments.TaskItemDocumentsData out;
' #txt
Ts0 f56 actionTable 'out=in;
' #txt
Ts0 f56 actionCode 'import ch.ivy.addon.portalkit.webservice.InputStreamDataSource;
import javax.activation.DataHandler;
import org.primefaces.model.UploadedFile;

UploadedFile uploadedFile = in.documentUploadEvent.getFile();
InputStreamDataSource source = new InputStreamDataSource(uploadedFile.getInputstream(), uploadedFile.getContentType(), uploadedFile.getFileName());
in.documentContent = new DataHandler(source);' #txt
Ts0 f56 type ch.ivy.addon.portalkit.component.TaskItemDocuments.TaskItemDocumentsData #txt
Ts0 f56 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>get document information</name>
        <nameStyle>24,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ts0 f56 438 196 36 24 20 -2 #rect
Ts0 f56 @|StepIcon #fIcon
Ts0 f62 actionDecl 'ch.ivy.addon.portalkit.component.TaskItemDocuments.TaskItemDocumentsData out;
' #txt
Ts0 f62 actionTable 'out=in;
' #txt
Ts0 f62 actionCode 'import java.util.Iterator;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
Iterator messages = FacesContext.getCurrentInstance().getMessages();

while(messages.hasNext()) {
	messages.next();
	messages.remove();
}
' #txt
Ts0 f62 type ch.ivy.addon.portalkit.component.TaskItemDocuments.TaskItemDocumentsData #txt
Ts0 f62 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>reset message</name>
        <nameStyle>13,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ts0 f62 1190 172 36 24 20 -2 #rect
Ts0 f62 @|StepIcon #fIcon
Ts0 f42 type ch.ivy.addon.portalkit.component.TaskItemDocuments.TaskItemDocumentsData #txt
Ts0 f42 processCall MultiPortal/CaseService:deleteDocument(ch.ivy.addon.portalkit.persistence.domain.Server,Long,Long) #txt
Ts0 f42 doCall true #txt
Ts0 f42 requestActionDecl '<ch.ivy.addon.portalkit.persistence.domain.Server server,java.lang.Long documentId,java.lang.Long caseId> param;
' #txt
Ts0 f42 requestMappingAction 'param.server=in.task.applicationRegister.server;
param.documentId=in.document.id;
param.caseId=in.task.case.getId();
' #txt
Ts0 f42 responseActionDecl 'ch.ivy.addon.portalkit.component.TaskItemDocuments.TaskItemDocumentsData out;
' #txt
Ts0 f42 responseMappingAction 'out=in;
out.errors=result.errors;
' #txt
Ts0 f42 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>CaseService
deleteDocument</name>
        <nameStyle>26,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ts0 f42 1422 140 36 24 20 -2 #rect
Ts0 f42 @|CallSubIcon #fIcon
Ts0 f18 type ch.ivy.addon.portalkit.component.TaskItemDocuments.TaskItemDocumentsData #txt
Ts0 f18 173 245 22 22 14 0 #rect
Ts0 f18 @|RichDialogProcessEndIcon #fIcon
Ts0 f22 type ch.ivy.addon.portalkit.component.TaskItemDocuments.TaskItemDocumentsData #txt
Ts0 f22 733 269 22 22 14 0 #rect
Ts0 f22 @|RichDialogProcessEndIcon #fIcon
Ts0 f30 actionDecl 'ch.ivy.addon.portalkit.component.TaskItemDocuments.TaskItemDocumentsData out;
' #txt
Ts0 f30 actionTable 'out=in;
' #txt
Ts0 f30 actionCode 'import ch.ivy.ws.addon.WsException;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

for (WsException error : in.errors) {
	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, error.userText, null));
}' #txt
Ts0 f30 type ch.ivy.addon.portalkit.component.TaskItemDocuments.TaskItemDocumentsData #txt
Ts0 f30 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>show error
message</name>
        <nameStyle>18,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ts0 f30 614 356 36 24 24 -15 #rect
Ts0 f30 @|StepIcon #fIcon
Ts0 f17 expr out #txt
Ts0 f17 184 107 184 164 #arcP
Ts0 f59 expr out #txt
Ts0 f59 456 220 456 252 #arcP
Ts0 f25 expr out #txt
Ts0 f25 744 107 744 140 #arcP
Ts0 f23 expr out #txt
Ts0 f23 744 228 744 269 #arcP
Ts0 f27 expr out #txt
Ts0 f27 744 164 744 204 #arcP
Ts0 f29 expr out #txt
Ts0 f29 456 276 456 298 #arcP
Ts0 f15 expr in #txt
Ts0 f15 outCond in.errors.isEmpty() #txt
Ts0 f15 456 326 456 356 #arcP
Ts0 f32 expr out #txt
Ts0 f32 632 380 467 424 #arcP
Ts0 f32 1 632 424 #addKink
Ts0 f32 1 0.2544295989340298 0 0 #arcLabel
Ts0 f31 expr in #txt
Ts0 f31 470 312 632 356 #arcP
Ts0 f31 1 632 312 #addKink
Ts0 f31 0 0.7001921317088737 0 0 #arcLabel
Ts0 f36 expr out #txt
Ts0 f36 456 107 456 130 #arcP
Ts0 f37 expr in #txt
Ts0 f37 outCond 'in.documentUploadEvent.getFile() != null && in.documentUploadEvent.getFile().getSize() > 0' #txt
Ts0 f37 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>yes</name>
        <nameStyle>3,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ts0 f37 456 158 456 196 #arcP
Ts0 f37 0 0.41304347826086957 14 0 #arcLabel
Ts0 f38 expr in #txt
Ts0 f38 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>no</name>
        <nameStyle>2,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ts0 f38 442 144 445 424 #arcP
Ts0 f38 1 392 144 #addKink
Ts0 f38 2 392 424 #addKink
Ts0 f38 1 0.1 -12 0 #arcLabel
Ts0 f43 expr out #txt
Ts0 f43 1440 99 1440 140 #arcP
Ts0 f46 expr out #txt
Ts0 f46 952 107 952 269 #arcP
Ts0 f50 expr in #txt
Ts0 f50 outCond in.errors.isEmpty() #txt
Ts0 f50 1440 230 1440 268 #arcP
Ts0 f51 expr in #txt
Ts0 f51 1454 216 1632 268 #arcP
Ts0 f51 1 1632 216 #addKink
Ts0 f51 0 0.7001921317088737 0 0 #arcLabel
Ts0 f52 expr out #txt
Ts0 f52 1440 164 1440 202 #arcP
Ts0 f55 expr out #txt
Ts0 f55 1632 292 1451 352 #arcP
Ts0 f55 1 1632 352 #addKink
Ts0 f55 1 0.3173982703485647 0 0 #arcLabel
Ts0 f63 expr out #txt
Ts0 f63 1208 99 1208 172 #arcP
Ts0 f61 expr out #txt
Ts0 f61 1208 196 1208 277 #arcP
Ts0 f20 expr out #txt
Ts0 f20 456 380 456 413 #arcP
Ts0 f10 expr out #txt
Ts0 f10 184 188 184 245 #arcP
Ts0 f3 expr out #txt
Ts0 f3 1440 292 1440 341 #arcP
>Proto Ts0 .type ch.ivy.addon.portalkit.component.TaskItemDocuments.TaskItemDocumentsData #txt
>Proto Ts0 .processKind HTML_DIALOG #txt
>Proto Ts0 -8 -8 16 16 16 26 #rect
>Proto Ts0 '' #fIcon
Ts0 f0 mainOut f2 tail #connect
Ts0 f2 head f1 mainIn #connect
Ts0 f6 mainOut f17 tail #connect
Ts0 f17 head f16 mainIn #connect
Ts0 f56 mainOut f59 tail #connect
Ts0 f59 head f58 mainIn #connect
Ts0 f13 mainOut f20 tail #connect
Ts0 f20 head f54 mainIn #connect
Ts0 f24 mainOut f27 tail #connect
Ts0 f27 head f26 mainIn #connect
Ts0 f26 mainOut f23 tail #connect
Ts0 f23 head f22 mainIn #connect
Ts0 f21 mainOut f25 tail #connect
Ts0 f25 head f24 mainIn #connect
Ts0 f58 mainOut f29 tail #connect
Ts0 f29 head f28 in #connect
Ts0 f28 out f15 tail #connect
Ts0 f15 head f13 mainIn #connect
Ts0 f28 out f31 tail #connect
Ts0 f31 head f30 mainIn #connect
Ts0 f30 mainOut f32 tail #connect
Ts0 f32 head f54 mainIn #connect
Ts0 f53 mainOut f36 tail #connect
Ts0 f36 head f35 in #connect
Ts0 f35 out f37 tail #connect
Ts0 f37 head f56 mainIn #connect
Ts0 f35 out f38 tail #connect
Ts0 f38 head f54 mainIn #connect
Ts0 f39 mainOut f43 tail #connect
Ts0 f43 head f42 mainIn #connect
Ts0 f44 mainOut f46 tail #connect
Ts0 f46 head f45 mainIn #connect
Ts0 f48 out f50 tail #connect
Ts0 f50 head f47 mainIn #connect
Ts0 f48 out f51 tail #connect
Ts0 f51 head f49 mainIn #connect
Ts0 f42 mainOut f52 tail #connect
Ts0 f52 head f48 in #connect
Ts0 f49 mainOut f55 tail #connect
Ts0 f55 head f40 mainIn #connect
Ts0 f57 mainOut f63 tail #connect
Ts0 f63 head f62 mainIn #connect
Ts0 f62 mainOut f61 tail #connect
Ts0 f61 head f60 mainIn #connect
Ts0 f16 mainOut f10 tail #connect
Ts0 f10 head f18 mainIn #connect
Ts0 f47 mainOut f3 tail #connect
Ts0 f3 head f40 mainIn #connect
