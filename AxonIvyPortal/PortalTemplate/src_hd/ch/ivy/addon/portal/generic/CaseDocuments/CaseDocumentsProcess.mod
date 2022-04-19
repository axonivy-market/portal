[Ivy]
18022687D24C1D82 9.4.6 #module
>Proto >Proto Collection #zClass
Ts0 CaseDocumentsProcess Big #zClass
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
Ts0 @CallSub f10 '' #zField
Ts0 @UdProcessEnd f24 '' #zField
Ts0 @UdProcessEnd f11 '' #zField
Ts0 @CallSub f31 '' #zField
Ts0 @GridStep f50 '' #zField
Ts0 @UdMethod f13 '' #zField
Ts0 @CallSub f20 '' #zField
Ts0 @UdMethod f15 '' #zField
Ts0 @PushWFArc f25 '' #zField
Ts0 @PushWFArc f16 '' #zField
Ts0 @PushWFArc f22 '' #zField
Ts0 @PushWFArc f17 '' #zField
Ts0 @PushWFArc f56 '' #zField
Ts0 @PushWFArc f19 '' #zField
>Proto Ts0 Ts0 CaseDocumentsProcess #zField
Ts0 f0 guid 1802215F56A1C1AA #txt
Ts0 f0 method start(Long) #txt
Ts0 f0 inParameterDecl '<Long caseId> param;' #txt
Ts0 f0 inActionCode 'import ch.ivy.addon.portalkit.util.CaseUtils;
out.selectedCase = CaseUtils.findCase(param.caseId);' #txt
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
Ts0 f30 requestMappingAction 'param.businessCase=in.selectedCase;
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
Ts0 f10 processCall 'Functional Processes/DeleteDocument:call(ch.ivyteam.ivy.workflow.ICase,ch.ivy.addon.portalkit.ivydata.bo.IvyDocument)' #txt
Ts0 f10 requestActionDecl '<ch.ivyteam.ivy.workflow.ICase businessCase,ch.ivy.addon.portalkit.ivydata.bo.IvyDocument document> param;' #txt
Ts0 f10 requestMappingAction 'param.businessCase=in.selectedCase;
param.document=in.document;
' #txt
Ts0 f10 responseActionDecl 'ch.ivy.addon.portalkit.component.CaseItemDocument.CaseItemDocumentData out;
' #txt
Ts0 f10 responseMappingAction 'out=in;
out.deleteDocumentMessage=result.message;
' #txt
Ts0 f10 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Delete Document</name>
    </language>
</elementInfo>
' #txt
Ts0 f10 168 330 112 44 -48 -8 #rect
Ts0 f24 371 243 26 26 0 12 #rect
Ts0 f11 675 339 26 26 0 12 #rect
Ts0 f31 processCall 'Functional Processes/GetDocumentList:call(ch.ivyteam.ivy.workflow.ICase)' #txt
Ts0 f31 requestActionDecl '<ch.ivyteam.ivy.workflow.ICase businessCase> param;' #txt
Ts0 f31 requestMappingAction 'param.businessCase=in.selectedCase;
' #txt
Ts0 f31 responseActionDecl 'ch.ivy.addon.portalkit.component.CaseItemDocument.CaseItemDocumentData out;
' #txt
Ts0 f31 responseMappingAction 'out=in;
out.documents=result.documents;
' #txt
Ts0 f31 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Find documents</name>
    </language>
</elementInfo>
' #txt
Ts0 f31 512 330 112 44 -44 -8 #rect
Ts0 f50 actionTable 'out=in;
' #txt
Ts0 f50 actionCode 'import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

FacesContext.getCurrentInstance().addMessage("case-document-global-messages", new FacesMessage(in.deleteDocumentMessage, "" ));' #txt
Ts0 f50 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Show delete&#xD;
message</name>
    </language>
</elementInfo>
' #txt
Ts0 f50 336 330 112 44 -34 -16 #rect
Ts0 f13 guid 18040F4FD24AB424 #txt
Ts0 f13 method downloadDocument(ch.ivy.addon.portalkit.ivydata.bo.IvyDocument) #txt
Ts0 f13 inParameterDecl '<ch.ivy.addon.portalkit.ivydata.bo.IvyDocument document> param;' #txt
Ts0 f13 inParameterMapAction 'out.document=param.document;
' #txt
Ts0 f13 outParameterDecl '<org.primefaces.model.StreamedContent streamedContent> result;' #txt
Ts0 f13 outParameterMapAction 'result.streamedContent=in.streamedContent;
' #txt
Ts0 f13 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>downloadDocument(IvyDocument)</name>
    </language>
</elementInfo>
' #txt
Ts0 f13 83 243 26 26 -91 17 #rect
Ts0 f20 processCall 'Functional Processes/DownloadDocument:call(ch.ivyteam.ivy.workflow.ICase,ch.ivy.addon.portalkit.ivydata.bo.IvyDocument)' #txt
Ts0 f20 requestActionDecl '<ch.ivyteam.ivy.workflow.ICase bussinessCase,ch.ivy.addon.portalkit.ivydata.bo.IvyDocument document> param;' #txt
Ts0 f20 requestMappingAction 'param.bussinessCase=in.selectedCase;
param.document=in.document;
' #txt
Ts0 f20 responseActionDecl 'ch.ivy.addon.portalkit.component.CaseItemDocument.CaseItemDocumentData out;
' #txt
Ts0 f20 responseMappingAction 'out=in;
out.streamedContent=result.streamedContent;
' #txt
Ts0 f20 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Download Document</name>
    </language>
</elementInfo>
' #txt
Ts0 f20 168 234 128 44 -58 -8 #rect
Ts0 f15 guid 18040F4FD2418511 #txt
Ts0 f15 method deleteDocument() #txt
Ts0 f15 inParameterDecl '<> param;' #txt
Ts0 f15 outParameterDecl '<> result;' #txt
Ts0 f15 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>deleteDocument()</name>
    </language>
</elementInfo>
' #txt
Ts0 f15 83 339 26 26 -49 15 #rect
Ts0 f25 expr out #txt
Ts0 f25 296 256 371 256 #arcP
Ts0 f16 expr out #txt
Ts0 f16 624 352 675 352 #arcP
Ts0 f22 expr out #txt
Ts0 f22 109 256 168 256 #arcP
Ts0 f17 expr out #txt
Ts0 f17 280 352 336 352 #arcP
Ts0 f56 expr out #txt
Ts0 f56 448 352 512 352 #arcP
Ts0 f19 expr out #txt
Ts0 f19 109 352 168 352 #arcP
>Proto Ts0 .type ch.ivy.addon.portal.generic.CaseDocuments.CaseDocumentsData #txt
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
Ts0 f50 mainOut f56 tail #connect
Ts0 f56 head f31 mainIn #connect
Ts0 f31 mainOut f16 tail #connect
Ts0 f16 head f11 mainIn #connect
Ts0 f13 mainOut f22 tail #connect
Ts0 f22 head f20 mainIn #connect
Ts0 f20 mainOut f25 tail #connect
Ts0 f25 head f24 mainIn #connect
Ts0 f15 mainOut f19 tail #connect
Ts0 f19 head f10 mainIn #connect
Ts0 f10 mainOut f17 tail #connect
Ts0 f17 head f50 mainIn #connect
