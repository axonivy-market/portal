[Ivy]
16285772A1F890EE 9.3.1 #module
>Proto >Proto Collection #zClass
Ur0 UploadDocumentChecker Big #zClass
Ur0 B #cInfo
Ur0 #process
Ur0 @TextInP .type .type #zField
Ur0 @TextInP .processKind .processKind #zField
Ur0 @AnnotationInP-0n ai ai #zField
Ur0 @MessageFlowInP-0n messageIn messageIn #zField
Ur0 @MessageFlowOutP-0n messageOut messageOut #zField
Ur0 @TextInP .xml .xml #zField
Ur0 @TextInP .responsibility .responsibility #zField
Ur0 @StartSub f0 '' #zField
Ur0 @EndSub f1 '' #zField
Ur0 @GridStep f3 '' #zField
Ur0 @PushWFArc f2 '' #zField
Ur0 @GridStep f5 '' #zField
Ur0 @PushWFArc f6 '' #zField
Ur0 @Alternative f7 '' #zField
Ur0 @PushWFArc f4 '' #zField
Ur0 @PushWFArc f9 '' #zField
Ur0 @GridStep f10 '' #zField
Ur0 @PushWFArc f8 '' #zField
Ur0 @Alternative f12 '' #zField
Ur0 @PushWFArc f13 '' #zField
Ur0 @PushWFArc f11 '' #zField
Ur0 @PushWFArc f14 '' #zField
>Proto Ur0 Ur0 UploadDocumentChecker #zField
Ur0 f0 inParamDecl '<org.primefaces.model.UploadedFile uploadFile> param;' #txt
Ur0 f0 inParamTable 'out.uploadedFile=param.uploadFile;
' #txt
Ur0 f0 outParamDecl '<ch.ivy.addon.portalkit.enums.UploadDocumentCheckStatus uploadDocumentCheckStatus,String message> result;' #txt
Ur0 f0 outParamTable 'result.uploadDocumentCheckStatus=in.checkStatus;
result.message=in.message;
' #txt
Ur0 f0 callSignature call(org.primefaces.model.UploadedFile) #txt
Ur0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>call(UploadedFile)</name>
        <nameStyle>18,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ur0 f0 81 49 30 30 -50 17 #rect
Ur0 f1 81 705 30 30 0 15 #rect
Ur0 f3 actionTable 'out=in;
' #txt
Ur0 f3 actionCode 'import ch.ivy.addon.portalkit.enums.UploadDocumentCheckStatus;
import ch.ivy.addon.portalkit.service.CaseDocumentService;

if(CaseDocumentService.isDocumentSafe(in.uploadedFile)){
	in.checkStatus = UploadDocumentCheckStatus.OK;
}
else{
	in.checkStatus = UploadDocumentCheckStatus.FAIL;
	in.message = ivy.cms.co("/ch.ivy.addon.portalkit.ui.jsf/documentFiles/fileContainScript");
}
' #txt
Ur0 f3 security system #txt
Ur0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Check file contains code</name>
    </language>
</elementInfo>
' #txt
Ur0 f3 24 538 144 44 -67 -8 #rect
Ur0 f2 expr out #txt
Ur0 f2 96 582 96 705 #arcP
Ur0 f5 actionTable 'out=in;
' #txt
Ur0 f5 actionCode 'import ch.ivy.addon.portalkit.service.CaseDocumentService;
import ch.ivy.addon.portalkit.enums.UploadDocumentCheckStatus;

in.checkStatus = UploadDocumentCheckStatus.OK;

if(!CaseDocumentService.isDocumentTypeValid(in.uploadedFile.getFileName())) {
	in.checkStatus = UploadDocumentCheckStatus.FAIL;
	in.message = ivy.cms.co("/Dialogs/components/CaseDocument/invalidFileMessage");
}
' #txt
Ur0 f5 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Check file extension&#xD;
in whitelist</name>
    </language>
</elementInfo>
' #txt
Ur0 f5 24 154 144 44 -51 -16 #rect
Ur0 f6 expr out #txt
Ur0 f6 96 79 96 154 #arcP
Ur0 f7 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <desc>Enable script check
in upload file</desc>
    </language>
</elementInfo>
' #txt
Ur0 f7 80 448 32 32 0 16 #rect
Ur0 f4 expr in #txt
Ur0 f4 outCond 'in.checkStatus == ch.ivy.addon.portalkit.enums.UploadDocumentCheckStatus.OK && ch.ivy.addon.portalkit.service.CaseDocumentService.enableScriptCheckingForUploadedDocument()' #txt
Ur0 f4 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Yes</name>
        <nameStyle>3,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ur0 f4 96 480 96 538 #arcP
Ur0 f4 0 0.4482758620689655 -25 0 #arcLabel
Ur0 f9 expr in #txt
Ur0 f9 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>No</name>
        <nameStyle>2,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ur0 f9 112 464 111 720 #arcP
Ur0 f9 1 224 464 #addKink
Ur0 f9 2 224 720 #addKink
Ur0 f9 1 0.1640625 16 0 #arcLabel
Ur0 f10 actionTable 'out=in;
' #txt
Ur0 f10 actionCode 'import ch.ivy.addon.portalkit.enums.UploadDocumentCheckStatus;
import ch.ivy.addon.portalkit.service.CaseDocumentService;

if(CaseDocumentService.isDocumentTypeHasVirus(in.uploadedFile)){
		in.checkStatus = UploadDocumentCheckStatus.FAIL;
		in.message = ivy.cms.co("/ch.ivy.addon.portalkit.ui.jsf/documentFiles/fileContainVirus");
}
else{
		in.checkStatus = UploadDocumentCheckStatus.OK;
}
' #txt
Ur0 f10 security system #txt
Ur0 f10 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Check file contains virus</name>
    </language>
</elementInfo>
' #txt
Ur0 f10 24 314 144 44 -66 -8 #rect
Ur0 f8 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Pass extension, virus check and&#13;
enable script checking&#13;
in upload file</name>
    </language>
</elementInfo>
' #txt
Ur0 f8 96 358 96 448 #arcP
Ur0 f8 0 0.8111111111111111 87 0 #arcLabel
Ur0 f12 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <desc>Enable virus scanner&#13;
in upload file</desc>
    </language>
</elementInfo>
' #txt
Ur0 f12 80 256 32 32 0 16 #rect
Ur0 f13 expr out #txt
Ur0 f13 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Pass extension check and
enable virus scanner
in upload file</name>
        <nameStyle>60,5
</nameStyle>
    </language>
</elementInfo>
' #txt
Ur0 f13 96 198 96 256 #arcP
Ur0 f13 0 0.7068965517241379 87 0 #arcLabel
Ur0 f11 expr in #txt
Ur0 f11 outCond 'in.checkStatus == ch.ivy.addon.portalkit.enums.UploadDocumentCheckStatus.OK && ch.ivy.addon.portalkit.service.CaseDocumentService.enableVirusScannerForUploadedDocument()' #txt
Ur0 f11 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Yes</name>
    </language>
</elementInfo>
' #txt
Ur0 f11 96 288 96 314 #arcP
Ur0 f11 0 0.5172413793103449 18 0 #arcLabel
Ur0 f14 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>No</name>
    </language>
</elementInfo>
' #txt
Ur0 f14 112 272 96 448 #arcP
Ur0 f14 1 288 272 #addKink
Ur0 f14 2 288 392 #addKink
Ur0 f14 3 96 392 #addKink
Ur0 f14 1 0.4845845674308709 0 0 #arcLabel
>Proto Ur0 .type ch.ivy.add.portalkit.UploadDocumentCheckerData #txt
>Proto Ur0 .processKind CALLABLE_SUB #txt
>Proto Ur0 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language/>
</elementInfo>
' #txt
>Proto Ur0 0 0 32 24 18 0 #rect
>Proto Ur0 @|BIcon #fIcon
Ur0 f3 mainOut f2 tail #connect
Ur0 f2 head f1 mainIn #connect
Ur0 f0 mainOut f6 tail #connect
Ur0 f6 head f5 mainIn #connect
Ur0 f7 out f4 tail #connect
Ur0 f4 head f3 mainIn #connect
Ur0 f7 out f9 tail #connect
Ur0 f9 head f1 mainIn #connect
Ur0 f10 mainOut f8 tail #connect
Ur0 f8 head f7 in #connect
Ur0 f5 mainOut f13 tail #connect
Ur0 f13 head f12 in #connect
Ur0 f12 out f11 tail #connect
Ur0 f11 head f10 mainIn #connect
Ur0 f12 out f14 tail #connect
Ur0 f14 head f7 in #connect
