[Ivy]
1802215F521FBBAA 9.4.6 #module
>Proto >Proto Collection #zClass
Ts0 TaskDocumentsProcess Big #zClass
Ts0 RD #cInfo
Ts0 #process
Ts0 @AnnotationInP-0n ai ai #zField
Ts0 @TextInP .type .type #zField
Ts0 @TextInP .processKind .processKind #zField
Ts0 @TextInP .colors .colors #zField
Ts0 @TextInP .xml .xml #zField
Ts0 @TextInP .responsibility .responsibility #zField
Ts0 @TextInP color color #zField
Ts0 @UdInit f0 '' #zField
Ts0 @UdProcessEnd f1 '' #zField
Ts0 @PushWFArc f2 '' #zField
Ts0 @UdEvent f3 '' #zField
Ts0 @UdExitEnd f4 '' #zField
Ts0 @PushWFArc f5 '' #zField
Ts0 @CallSub f30 '' #zField
Ts0 @UdMethod f6 '' #zField
Ts0 @UdProcessEnd f8 '' #zField
Ts0 @PushWFArc f7 '' #zField
Ts0 @PushWFArc f9 '' #zField
Ts0 @UdProcessEnd f10 '' #zField
Ts0 @CallSub f11 '' #zField
Ts0 @UdMethod f12 '' #zField
Ts0 @CallSub f38 '' #zField
Ts0 @UdProcessEnd f22 '' #zField
Ts0 @GridStep f50 '' #zField
Ts0 @CallSub f19 '' #zField
Ts0 @UdMethod f15 '' #zField
Ts0 @PushWFArc f39 '' #zField
Ts0 @PushWFArc f20 '' #zField
Ts0 @PushWFArc f26 '' #zField
Ts0 @PushWFArc f24 '' #zField
Ts0 @PushWFArc f25 '' #zField
Ts0 @PushWFArc f17 '' #zField
>Proto Ts0 Ts0 TaskDocumentsProcess #zField
Ts0 f0 guid 1802215F56A1C1AA #txt
Ts0 f0 method start(Long) #txt
Ts0 f0 inParameterDecl '<Long taskId> param;' #txt
Ts0 f0 inActionCode 'import ch.ivy.addon.portalkit.util.TaskUtils;
out.task = TaskUtils.findTaskById(param.taskId);
' #txt
Ts0 f0 outParameterDecl '<> result;' #txt
Ts0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start(Long)</name>
    </language>
</elementInfo>
' #txt
Ts0 f0 83 51 26 26 -16 15 #rect
Ts0 f1 211 51 26 26 0 12 #rect
Ts0 f2 109 64 211 64 #arcP
Ts0 f3 guid 1802215F5712F489 #txt
Ts0 f3 actionTable 'out=in;
' #txt
Ts0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>close</name>
    </language>
</elementInfo>
' #txt
Ts0 f3 371 51 26 26 -15 15 #rect
Ts0 f4 499 51 26 26 0 12 #rect
Ts0 f5 397 64 499 64 #arcP
Ts0 f30 processCall 'Functional Processes/GetDocumentList:call(ch.ivyteam.ivy.workflow.ICase)' #txt
Ts0 f30 requestActionDecl '<ch.ivyteam.ivy.workflow.ICase businessCase> param;' #txt
Ts0 f30 requestMappingAction 'param.businessCase=in.task.getCase().getBusinessCase();
' #txt
Ts0 f30 responseActionDecl 'ch.ivy.addon.portalkit.component.TaskItemDocuments.TaskItemDocumentsData out;
' #txt
Ts0 f30 responseMappingAction 'out=in;
out.documents=result.documents;
' #txt
Ts0 f30 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Find documents</name>
    </language>
</elementInfo>
' #txt
Ts0 f30 168 138 112 44 -44 -8 #rect
Ts0 f6 guid 1802244D8B3A4339 #txt
Ts0 f6 method initData() #txt
Ts0 f6 inParameterDecl '<> param;' #txt
Ts0 f6 outParameterDecl '<> result;' #txt
Ts0 f6 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>initData()</name>
    </language>
</elementInfo>
' #txt
Ts0 f6 83 147 26 26 -40 15 #rect
Ts0 f8 371 147 26 26 0 12 #rect
Ts0 f7 280 160 371 160 #arcP
Ts0 f9 109 160 168 160 #arcP
Ts0 f10 371 243 26 26 0 12 #rect
Ts0 f11 processCall 'Functional Processes/DownloadDocument:call(ch.ivyteam.ivy.workflow.ICase,ch.ivy.addon.portalkit.ivydata.bo.IvyDocument)' #txt
Ts0 f11 requestActionDecl '<ch.ivyteam.ivy.workflow.ICase bussinessCase,ch.ivy.addon.portalkit.ivydata.bo.IvyDocument document> param;' #txt
Ts0 f11 requestMappingAction 'param.bussinessCase=in.task.getCase().getBusinessCase();
param.document=in.document;
' #txt
Ts0 f11 responseActionDecl 'ch.ivy.addon.portalkit.component.TaskItemDocuments.TaskItemDocumentsData out;
' #txt
Ts0 f11 responseMappingAction 'out=in;
out.streamedContent=result.streamedContent;
' #txt
Ts0 f11 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Download Document</name>
    </language>
</elementInfo>
' #txt
Ts0 f11 168 234 128 44 -58 -8 #rect
Ts0 f12 guid 180402B1B9EC50AB #txt
Ts0 f12 method deleteDocument() #txt
Ts0 f12 inParameterDecl '<> param;' #txt
Ts0 f12 outParameterDecl '<> result;' #txt
Ts0 f12 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>deleteDocument()</name>
    </language>
</elementInfo>
' #txt
Ts0 f12 83 339 26 26 -49 15 #rect
Ts0 f38 processCall 'Functional Processes/GetDocumentList:call(ch.ivyteam.ivy.workflow.ICase)' #txt
Ts0 f38 requestActionDecl '<ch.ivyteam.ivy.workflow.ICase businessCase> param;' #txt
Ts0 f38 requestMappingAction 'param.businessCase=in.task.getCase().getBusinessCase();
' #txt
Ts0 f38 responseActionDecl 'ch.ivy.addon.portalkit.component.TaskItemDocuments.TaskItemDocumentsData out;
' #txt
Ts0 f38 responseMappingAction 'out=in;
out.documents=result.documents;
' #txt
Ts0 f38 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Find documents</name>
    </language>
</elementInfo>
' #txt
Ts0 f38 512 330 112 44 -44 -8 #rect
Ts0 f22 659 339 26 26 0 12 #rect
Ts0 f50 actionTable 'out=in;
' #txt
Ts0 f50 actionCode 'import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

FacesContext.getCurrentInstance().addMessage("task-document-global-messages", new FacesMessage(in.deleteDocumentMessage, "" ));' #txt
Ts0 f50 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Show success&#xD;
message</name>
    </language>
</elementInfo>
' #txt
Ts0 f50 328 330 128 44 -40 -16 #rect
Ts0 f19 processCall 'Functional Processes/DeleteDocument:call(ch.ivyteam.ivy.workflow.ICase,ch.ivy.addon.portalkit.ivydata.bo.IvyDocument)' #txt
Ts0 f19 requestActionDecl '<ch.ivyteam.ivy.workflow.ICase businessCase,ch.ivy.addon.portalkit.ivydata.bo.IvyDocument document> param;' #txt
Ts0 f19 requestMappingAction 'param.businessCase=in.task.getCase().getBusinessCase();
param.document=in.document;
' #txt
Ts0 f19 responseActionDecl 'ch.ivy.addon.portalkit.component.TaskItemDocuments.TaskItemDocumentsData out;
' #txt
Ts0 f19 responseMappingAction 'out=in;
out.deleteDocumentMessage=result.message;
' #txt
Ts0 f19 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Delete Document</name>
    </language>
</elementInfo>
' #txt
Ts0 f19 168 330 112 44 -48 -8 #rect
Ts0 f15 guid 180402B1B9E2C8F4 #txt
Ts0 f15 method downloadDocument(ch.ivy.addon.portalkit.ivydata.bo.IvyDocument) #txt
Ts0 f15 inParameterDecl '<ch.ivy.addon.portalkit.ivydata.bo.IvyDocument document> param;' #txt
Ts0 f15 inParameterMapAction 'out.document=param.document;
' #txt
Ts0 f15 outParameterDecl '<org.primefaces.model.StreamedContent streamedContent> result;' #txt
Ts0 f15 outParameterMapAction 'result.streamedContent=in.streamedContent;
' #txt
Ts0 f15 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>downloadDocument(IvyDocument)</name>
    </language>
</elementInfo>
' #txt
Ts0 f15 83 243 26 26 -89 15 #rect
Ts0 f39 expr out #txt
Ts0 f39 456 352 512 352 #arcP
Ts0 f20 expr out #txt
Ts0 f20 296 256 371 256 #arcP
Ts0 f26 expr out #txt
Ts0 f26 280 352 328 352 #arcP
Ts0 f24 expr out #txt
Ts0 f24 624 352 659 352 #arcP
Ts0 f25 expr out #txt
Ts0 f25 109 352 168 352 #arcP
Ts0 f17 expr out #txt
Ts0 f17 109 256 168 256 #arcP
>Proto Ts0 .type ch.ivy.addon.portal.generic.TaskDocuments.TaskDocumentsData #txt
>Proto Ts0 .processKind HTML_DIALOG #txt
>Proto Ts0 -8 -8 16 16 16 26 #rect
Ts0 f0 mainOut f2 tail #connect
Ts0 f2 head f1 mainIn #connect
Ts0 f3 mainOut f5 tail #connect
Ts0 f5 head f4 mainIn #connect
Ts0 f30 mainOut f7 tail #connect
Ts0 f7 head f8 mainIn #connect
Ts0 f6 mainOut f9 tail #connect
Ts0 f9 head f30 mainIn #connect
Ts0 f15 mainOut f17 tail #connect
Ts0 f17 head f11 mainIn #connect
Ts0 f11 mainOut f20 tail #connect
Ts0 f20 head f10 mainIn #connect
Ts0 f50 mainOut f39 tail #connect
Ts0 f39 head f38 mainIn #connect
Ts0 f38 mainOut f24 tail #connect
Ts0 f24 head f22 mainIn #connect
Ts0 f12 mainOut f25 tail #connect
Ts0 f25 head f19 mainIn #connect
Ts0 f19 mainOut f26 tail #connect
Ts0 f26 head f50 mainIn #connect
