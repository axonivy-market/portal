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
Ts0 f30 168 170 112 44 -44 -8 #rect
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
Ts0 f6 83 179 26 26 -40 15 #rect
Ts0 f8 371 179 26 26 0 12 #rect
Ts0 f7 280 192 371 192 #arcP
Ts0 f9 109 192 168 192 #arcP
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
