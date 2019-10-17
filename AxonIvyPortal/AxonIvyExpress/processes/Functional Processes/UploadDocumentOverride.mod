[Ivy]
16B267B1A01CFA7E 7.5.0 #module
>Proto >Proto Collection #zClass
Ut0 UploadDocument Big #zClass
Ut0 B #cInfo
Ut0 #process
Ut0 @TextInP .type .type #zField
Ut0 @TextInP .processKind .processKind #zField
Ut0 @AnnotationInP-0n ai ai #zField
Ut0 @MessageFlowInP-0n messageIn messageIn #zField
Ut0 @MessageFlowOutP-0n messageOut messageOut #zField
Ut0 @TextInP .xml .xml #zField
Ut0 @TextInP .responsibility .responsibility #zField
Ut0 @StartSub f0 '' #zField
Ut0 @EndSub f1 '' #zField
Ut0 @GridStep f36 '' #zField
Ut0 @Alternative f72 '' #zField
Ut0 @GridStep f71 '' #zField
Ut0 @GridStep f38 '' #zField
Ut0 @Alternative f74 '' #zField
Ut0 @Alternative f73 '' #zField
Ut0 @Alternative f70 '' #zField
Ut0 @Alternative f53 '' #zField
Ut0 @CallSub f75 '' #zField
Ut0 @PushWFArc f92 '' #zField
Ut0 @PushWFArc f88 '' #zField
Ut0 @PushWFArc f78 '' #zField
Ut0 @PushWFArc f83 '' #zField
Ut0 @PushWFArc f79 '' #zField
Ut0 @PushWFArc f93 '' #zField
Ut0 @PushWFArc f87 '' #zField
Ut0 @PushWFArc f80 '' #zField
Ut0 @PushWFArc f90 '' #zField
Ut0 @PushWFArc f91 '' #zField
Ut0 @PushWFArc f3 '' #zField
Ut0 @PushWFArc f2 '' #zField
Ut0 @PushWFArc f4 '' #zField
>Proto Ut0 Ut0 UploadDocument #zField
Ut0 f0 inParamDecl '<ch.ivyteam.ivy.workflow.ICase businessCase,org.primefaces.model.UploadedFile uploadedFile> param;' #txt
Ut0 f0 inParamTable 'out.businessCase=param.businessCase;
out.uploadedFile=param.uploadedFile;
' #txt
Ut0 f0 outParamDecl '<ch.ivyteam.ivy.workflow.document.IDocument uploadedDocument,String message,ch.ivy.addon.portalkit.enums.UploadDocumentCheckStatus status> result;' #txt
Ut0 f0 outParamTable 'result.uploadedDocument=in.uploadedDocument;
result.message=in.message;
result.status=in.status;
' #txt
Ut0 f0 callSignature call(ch.ivyteam.ivy.workflow.ICase,org.primefaces.model.UploadedFile) #txt
Ut0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>call(ICase,UploadedFile)</name>
    </language>
</elementInfo>
' #txt
Ut0 f0 81 105 30 30 -76 15 #rect
Ut0 f0 @|StartSubIcon #fIcon
Ut0 f1 1257 105 30 30 0 15 #rect
Ut0 f1 @|EndSubIcon #fIcon
Ut0 f36 actionTable 'out=in;
' #txt
Ut0 f36 actionCode 'import ch.ivy.addon.portalkit.enums.UploadDocumentCheckStatus;
import org.apache.commons.io.FileUtils;
import java.util.Arrays;
import ch.ivy.addon.portalkit.masterdata.MasterData;

in.message = "";
in.status = UploadDocumentCheckStatus.OK;

if (in.uploadedFile == null) {
	in.message = ivy.cms.co("/Dialogs/components/CaseDocument/invalidFileMessage");
	in.status = UploadDocumentCheckStatus.FAIL;
} else if (in.uploadedFile.getSize() == 0) {
	in.message = ivy.cms.co("/Dialogs/components/CaseDocument/emptyFileMessage");
	in.status = UploadDocumentCheckStatus.FAIL;
} else {
	Long maxFileUploadSize = MasterData.getFileUploadSizeLimit();
	if (in.uploadedFile.getSize() > maxFileUploadSize) {
		in.message = ivy.cms.co("/ch.ivy.addon.portalkit.ui.jsf/common/errorFileUploadSize", Arrays.asList(FileUtils.byteCountToDisplaySize(maxFileUploadSize)));
	}
	in.status = UploadDocumentCheckStatus.FAIL;
}' #txt
Ut0 f36 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Validate</name>
    </language>
</elementInfo>
' #txt
Ut0 f36 200 98 112 44 -22 -8 #rect
Ut0 f36 @|StepIcon #fIcon
Ut0 f72 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Not exist?</name>
    </language>
</elementInfo>
' #txt
Ut0 f72 904 104 32 32 -27 -34 #rect
Ut0 f72 @|AlternativeIcon #fIcon
Ut0 f71 actionTable 'out=in;
' #txt
Ut0 f71 actionCode 'import ch.ivy.addon.portalkit.util.DocumentFileUtils;
import ch.ivy.addon.portalkit.service.CaseDocumentService;
import ch.ivy.addon.portalkit.enums.UploadDocumentCheckStatus;

String fileName = in.uploadedFile.getFileName();
boolean isUploaded = DocumentFileUtils.uploadExpressDocument(ivy.case, in.uploadedFile.getInputstream(), fileName);

if (isUploaded) {
	out.status = UploadDocumentCheckStatus.OK;
	out.message = ivy.cms.co("/ch.ivy.addon.portalkit.ui.jsf/documentFiles/uploadSucceed");
} else {
	out.message = ivy.cms.co("/ch.ivy.addon.portalkit.ui.jsf/documentFiles/uploadFailed");
	out.status = UploadDocumentCheckStatus.FAIL;
}' #txt
Ut0 f71 security system #txt
Ut0 f71 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Upload document</name>
    </language>
</elementInfo>
' #txt
Ut0 f71 1008 98 112 44 -49 -8 #rect
Ut0 f71 @|StepIcon #fIcon
Ut0 f38 actionTable 'out=in;
' #txt
Ut0 f38 actionCode 'import ch.ivy.addon.portalkit.util.DocumentFileUtils;
import ch.ivy.addon.portalkit.enums.UploadDocumentCheckStatus;
import org.primefaces.model.UploadedFile;
import ch.ivy.addon.portalkit.service.CaseDocumentService;

String fileName = in.uploadedFile.getFileName();
boolean doesDocumentExist = DocumentFileUtils.doesDocumentExist(ivy.case, fileName);

if (doesDocumentExist) {
	in.message = ivy.cms.co("/ch.ivy.addon.portalkit.ui.jsf/documentFiles/uploadFileExists", [fileName]);
	in.status = UploadDocumentCheckStatus.FAIL;
}' #txt
Ut0 f38 security system #txt
Ut0 f38 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Check exist</name>
    </language>
</elementInfo>
' #txt
Ut0 f38 736 98 112 44 -31 -8 #rect
Ut0 f38 @|StepIcon #fIcon
Ut0 f74 648 200 32 32 0 16 #rect
Ut0 f74 @|AlternativeIcon #fIcon
Ut0 f73 904 200 32 32 0 16 #rect
Ut0 f73 @|AlternativeIcon #fIcon
Ut0 f70 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>valid file?</name>
        <nameStyle>11,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ut0 f70 362 106 28 28 -30 -34 #rect
Ut0 f70 @|AlternativeIcon #fIcon
Ut0 f53 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Document check</name>
        <nameStyle>14,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ut0 f53 648 104 32 32 -54 -37 #rect
Ut0 f53 @|AlternativeIcon #fIcon
Ut0 f75 processCall 'Functional Processes/UploadDocumentChecker:call(org.primefaces.model.UploadedFile)' #txt
Ut0 f75 requestActionDecl '<org.primefaces.model.UploadedFile uploadFile> param;' #txt
Ut0 f75 requestMappingAction 'param.uploadFile=in.uploadedFile;
' #txt
Ut0 f75 responseActionDecl 'gawfs.UploadDocumentOverrideData out;
' #txt
Ut0 f75 responseMappingAction 'out=in;
out.message=result.message;
out.status=result.uploadDocumentCheckStatus;
' #txt
Ut0 f75 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>UploadDocumentChecker</name>
        <nameStyle>21,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ut0 f75 440 98 160 44 -71 -8 #rect
Ut0 f75 @|CallSubIcon #fIcon
Ut0 f92 expr in #txt
Ut0 f92 680 216 904 216 #arcP
Ut0 f92 0 0.3194444444444444 0 -10 #arcLabel
Ut0 f88 expr in #txt
Ut0 f88 920 136 920 200 #arcP
Ut0 f88 0 0.6247201550720135 0 0 #arcLabel
Ut0 f78 expr in #txt
Ut0 f78 outCond org.apache.commons.lang.StringUtils.isBlank(in.message) #txt
Ut0 f78 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>YES</name>
        <nameStyle>3
</nameStyle>
    </language>
</elementInfo>
' #txt
Ut0 f78 390 120 440 120 #arcP
Ut0 f78 0 0.26 0 -11 #arcLabel
Ut0 f83 expr in #txt
Ut0 f83 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>NO</name>
        <nameStyle>2
</nameStyle>
    </language>
</elementInfo>
' #txt
Ut0 f83 376 134 648 216 #arcP
Ut0 f83 1 376 216 #addKink
Ut0 f83 1 0.11556836310247781 -1 -9 #arcLabel
Ut0 f79 expr out #txt
Ut0 f79 312 120 362 120 #arcP
Ut0 f93 expr in #txt
Ut0 f93 outCond 'in.status == ch.ivy.addon.portalkit.enums.UploadDocumentCheckStatus.OK' #txt
Ut0 f93 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>YES</name>
        <nameStyle>3
</nameStyle>
    </language>
</elementInfo>
' #txt
Ut0 f93 936 120 1008 120 #arcP
Ut0 f93 0 0.3333333333333333 -1 -8 #arcLabel
Ut0 f87 expr in #txt
Ut0 f87 outCond 'in.status == ch.ivy.addon.portalkit.enums.UploadDocumentCheckStatus.OK' #txt
Ut0 f87 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>OK</name>
        <nameStyle>2,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ut0 f87 680 120 736 120 #arcP
Ut0 f87 0 0.3333333333333333 0 -8 #arcLabel
Ut0 f80 expr out #txt
Ut0 f80 600 120 648 120 #arcP
Ut0 f80 0 0.9770034044244577 0 0 #arcLabel
Ut0 f90 expr in #txt
Ut0 f90 664 136 664 200 #arcP
Ut0 f91 expr out #txt
Ut0 f91 848 120 904 120 #arcP
Ut0 f91 0 0.3333333333333333 0 -8 #arcLabel
Ut0 f3 expr out #txt
Ut0 f3 111 120 200 120 #arcP
Ut0 f2 expr out #txt
Ut0 f2 1120 120 1257 120 #arcP
Ut0 f4 expr in #txt
Ut0 f4 936 216 1272 135 #arcP
Ut0 f4 1 1272 216 #addKink
Ut0 f4 0 0.6389354311645866 0 0 #arcLabel
>Proto Ut0 .type gawfs.UploadDocumentOverrideData #txt
>Proto Ut0 .processKind CALLABLE_SUB #txt
>Proto Ut0 0 0 32 24 18 0 #rect
>Proto Ut0 @|BIcon #fIcon
Ut0 f70 out f78 tail #connect
Ut0 f78 head f75 mainIn #connect
Ut0 f75 mainOut f80 tail #connect
Ut0 f80 head f53 in #connect
Ut0 f36 mainOut f79 tail #connect
Ut0 f79 head f70 in #connect
Ut0 f70 out f83 tail #connect
Ut0 f83 head f74 in #connect
Ut0 f90 head f74 in #connect
Ut0 f53 out f87 tail #connect
Ut0 f87 head f38 mainIn #connect
Ut0 f53 out f90 tail #connect
Ut0 f38 mainOut f91 tail #connect
Ut0 f91 head f72 in #connect
Ut0 f72 out f93 tail #connect
Ut0 f93 head f71 mainIn #connect
Ut0 f74 out f92 tail #connect
Ut0 f92 head f73 in #connect
Ut0 f72 out f88 tail #connect
Ut0 f88 head f73 in #connect
Ut0 f0 mainOut f3 tail #connect
Ut0 f3 head f36 mainIn #connect
Ut0 f71 mainOut f2 tail #connect
Ut0 f2 head f1 mainIn #connect
Ut0 f73 out f4 tail #connect
Ut0 f4 head f1 mainIn #connect
