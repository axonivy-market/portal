[Ivy]
180CC1E9248E252B 7.5.0 #module
>Proto >Proto Collection #zClass
Ut0 UploadDocumentItem Big #zClass
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
Ut0 @PushWFArc f87 '' #zField
Ut0 @PushWFArc f80 '' #zField
Ut0 @PushWFArc f90 '' #zField
Ut0 @PushWFArc f91 '' #zField
Ut0 @PushWFArc f3 '' #zField
Ut0 @PushWFArc f2 '' #zField
Ut0 @InfoButton f5 '' #zField
Ut0 @AnnotationArc f6 '' #zField
Ut0 @PushWFArc f15 '' #zField
Ut0 @PushWFArc f7 '' #zField
>Proto Ut0 Ut0 UploadDocumentItem #zField
Ut0 f0 inParamDecl '<ch.ivyteam.ivy.workflow.ICase businessCase,org.primefaces.model.UploadedFile uploadedFile,Boolean enableScriptCheckingForUploadedDocument,Boolean enableVirusScannerForUploadedDocument,String allowedUploadFileTypes> param;' #txt
Ut0 f0 inParamTable 'out.allowedUploadFileTypes=param.allowedUploadFileTypes;
out.businessCase=param.businessCase;
out.enableScriptCheckingForUploadedDocument=param.enableScriptCheckingForUploadedDocument;
out.enableVirusScannerForUploadedDocument=param.enableVirusScannerForUploadedDocument;
out.uploadedFile=param.uploadedFile;
' #txt
Ut0 f0 outParamDecl '<ch.ivyteam.ivy.workflow.document.IDocument uploadedDocument,String message,com.axonivy.portal.component.enums.UploadDocumentCheckStatus status> result;' #txt
Ut0 f0 outParamTable 'result.uploadedDocument=in.uploadedDocument;
result.message=in.message;
result.status=in.status;
' #txt
Ut0 f0 callSignature call(ch.ivyteam.ivy.workflow.ICase,org.primefaces.model.UploadedFile,Boolean,Boolean,String) #txt
Ut0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>call(ICase,UploadedFile,&#13;
Boolean,Boolean,String)</name>
    </language>
</elementInfo>
' #txt
Ut0 f0 81 113 30 30 -60 -46 #rect
Ut0 f0 @|StartSubIcon #fIcon
Ut0 f1 1201 113 30 30 0 15 #rect
Ut0 f1 @|EndSubIcon #fIcon
Ut0 f36 actionTable 'out=in;
' #txt
Ut0 f36 actionCode 'import com.axonivy.portal.component.masterdata.MasterData;
import com.axonivy.portal.component.enums.UploadDocumentCheckStatus;
import org.apache.commons.io.FileUtils;
import java.util.Arrays;

in.message = "";
in.status = UploadDocumentCheckStatus.OK;

if (in.uploadedFile == null) {
	in.message = ivy.cms.co("/Dialogs/com/axonivy/portal/component/DocumentTable/InvalidFileMessage");
	in.status = UploadDocumentCheckStatus.FAIL;
} else if (in.uploadedFile.getSize() == 0) {
	in.message = ivy.cms.co("/Dialogs/com/axonivy/portal/component/DocumentTable/EmptyFileMessage");
	in.status = UploadDocumentCheckStatus.FAIL;
} else {
	Long maxFileUploadSize = MasterData.getFileUploadSizeLimit();
	if (in.uploadedFile.getSize() > maxFileUploadSize) {
		in.message = ivy.cms.co("/Dialogs/com/axonivy/portal/component/DocumentTable/ErrorFileUploadSize", Arrays.asList(FileUtils.byteCountToDisplaySize(maxFileUploadSize)));
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
Ut0 f36 200 106 112 44 -22 -8 #rect
Ut0 f36 @|StepIcon #fIcon
Ut0 f72 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Not exist?</name>
    </language>
</elementInfo>
' #txt
Ut0 f72 904 112 32 32 -27 -34 #rect
Ut0 f72 @|AlternativeIcon #fIcon
Ut0 f71 actionTable 'out=in;
' #txt
Ut0 f71 actionCode 'import com.axonivy.portal.component.service.CaseDocumentService;
import com.axonivy.portal.component.enums.UploadDocumentCheckStatus;
import java.util.Arrays;
import org.primefaces.model.UploadedFile;

out.message = "";
out.status = UploadDocumentCheckStatus.OK;
String fileName = in.uploadedFile.getFileName();
out.uploadedDocument = CaseDocumentService.newInstance(in.businessCase).upload(fileName, in.uploadedFile.getInputstream());

if (out.#uploadedDocument != null) {	
	String note = ivy.cms.co("/Dialogs/com/axonivy/portal/component/DocumentTable/UploadDocumentNote", Arrays.asList(ivy.session.getSessionUserName(), fileName));
	in.businessCase.createNote(ivy.session, note);
	out.message = ivy.cms.co("/Dialogs/com/axonivy/portal/component/DocumentTable/UploadSucceed");
} else {
	out.message = ivy.cms.co("/Dialogs/com/axonivy/portal/component/DocumentTable/UploadFailed");
	out.status = UploadDocumentCheckStatus.FAIL;
}' #txt
Ut0 f71 security system #txt
Ut0 f71 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Upload document item&#13;
and create note</name>
    </language>
</elementInfo>
' #txt
Ut0 f71 1008 106 160 44 -59 -16 #rect
Ut0 f71 @|StepIcon #fIcon
Ut0 f38 actionTable 'out=in;
' #txt
Ut0 f38 actionCode 'import com.axonivy.portal.component.enums.UploadDocumentCheckStatus;
import org.primefaces.model.UploadedFile;
import com.axonivy.portal.component.service.CaseDocumentService;

String fileName = in.uploadedFile.getFileName();
boolean doesDocumentExist = CaseDocumentService.newInstance(in.businessCase).doesDocumentExist(fileName);

if (doesDocumentExist) {
	in.message = ivy.cms.co("/Dialogs/com/axonivy/portal/component/DocumentTable/UploadFileExists", [fileName]);
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
Ut0 f38 736 106 112 44 -31 -8 #rect
Ut0 f38 @|StepIcon #fIcon
Ut0 f74 648 208 32 32 0 16 #rect
Ut0 f74 @|AlternativeIcon #fIcon
Ut0 f73 904 208 32 32 0 16 #rect
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
Ut0 f70 362 114 28 28 -30 -34 #rect
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
Ut0 f53 648 112 32 32 -54 -37 #rect
Ut0 f53 @|AlternativeIcon #fIcon
Ut0 f75 processCall 'Functional Processes/UploadDocumentItemChecker:call(org.primefaces.model.UploadedFile,Boolean,Boolean,String)' #txt
Ut0 f75 requestActionDecl '<org.primefaces.model.UploadedFile uploadFile,Boolean enableVirusScannerForUploadedDocument,Boolean enableScriptCheckingForUploadedDocument,String allowedUploadFileTypes> param;' #txt
Ut0 f75 requestMappingAction 'param.uploadFile=in.uploadedFile;
param.enableVirusScannerForUploadedDocument=in.enableVirusScannerForUploadedDocument;
param.enableScriptCheckingForUploadedDocument=in.enableScriptCheckingForUploadedDocument;
param.allowedUploadFileTypes=in.allowedUploadFileTypes;
' #txt
Ut0 f75 responseActionDecl 'com.axonivy.portal.component.UploadDocumentItemData out;
' #txt
Ut0 f75 responseMappingAction 'out=in;
out.message=result.message;
out.status=result.uploadDocumentCheckStatus;
' #txt
Ut0 f75 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>UploadDocumentItemChecker</name>
        <nameStyle>25,5
</nameStyle>
    </language>
</elementInfo>
' #txt
Ut0 f75 432 106 176 44 -83 -8 #rect
Ut0 f75 @|CallSubIcon #fIcon
Ut0 f92 expr in #txt
Ut0 f92 680 224 904 224 #arcP
Ut0 f92 0 0.3194444444444444 0 -10 #arcLabel
Ut0 f88 expr in #txt
Ut0 f88 920 144 920 208 #arcP
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
Ut0 f78 390 128 432 128 #arcP
Ut0 f78 0 0.26 1 -11 #arcLabel
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
Ut0 f83 376 142 648 224 #arcP
Ut0 f83 1 376 224 #addKink
Ut0 f83 1 0.11556836310247781 -1 -9 #arcLabel
Ut0 f79 expr out #txt
Ut0 f79 312 128 362 128 #arcP
Ut0 f87 expr in #txt
Ut0 f87 outCond 'in.status == com.axonivy.portal.component.enums.UploadDocumentCheckStatus.OK' #txt
Ut0 f87 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>OK</name>
        <nameStyle>2,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ut0 f87 680 128 736 128 #arcP
Ut0 f87 0 0.3333333333333333 0 -8 #arcLabel
Ut0 f80 expr out #txt
Ut0 f80 608 128 648 128 #arcP
Ut0 f80 0 0.9770034044244577 0 0 #arcLabel
Ut0 f90 expr in #txt
Ut0 f90 664 144 664 208 #arcP
Ut0 f91 expr out #txt
Ut0 f91 848 128 904 128 #arcP
Ut0 f91 0 0.3333333333333333 0 -8 #arcLabel
Ut0 f3 expr out #txt
Ut0 f3 111 128 200 128 #arcP
Ut0 f2 expr out #txt
Ut0 f2 1168 128 1201 128 #arcP
Ut0 f5 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Override this sub process to upload your file to DMS&#xD;
This sub process also contains some validation, so if you override it, you have to implement validation by your own</name>
    </language>
</elementInfo>
' #txt
Ut0 f5 160 322 640 44 -310 -16 #rect
Ut0 f5 @|IBIcon #fIcon
Ut0 f6 160 344 100 142 #arcP
Ut0 f15 expr in #txt
Ut0 f15 outCond 'in.status == com.axonivy.portal.component.enums.UploadDocumentCheckStatus.OK' #txt
Ut0 f15 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>YES</name>
        <nameStyle>3
</nameStyle>
    </language>
</elementInfo>
' #txt
Ut0 f15 936 128 1008 128 #arcP
Ut0 f15 0 0.296875 0 -12 #arcLabel
Ut0 f7 expr in #txt
Ut0 f7 936 224 1216 143 #arcP
Ut0 f7 1 1216 224 #addKink
Ut0 f7 0 0.7536889668139595 0 0 #arcLabel
>Proto Ut0 .type com.axonivy.portal.component.UploadDocumentItemData #txt
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
Ut0 f74 out f92 tail #connect
Ut0 f92 head f73 in #connect
Ut0 f88 head f73 in #connect
Ut0 f0 mainOut f3 tail #connect
Ut0 f3 head f36 mainIn #connect
Ut0 f71 mainOut f2 tail #connect
Ut0 f2 head f1 mainIn #connect
Ut0 f5 ao f6 tail #connect
Ut0 f6 head f0 @CG|ai #connect
Ut0 f72 out f15 tail #connect
Ut0 f15 head f71 mainIn #connect
Ut0 f72 out f88 tail #connect
Ut0 f73 out f7 tail #connect
Ut0 f7 head f1 mainIn #connect
