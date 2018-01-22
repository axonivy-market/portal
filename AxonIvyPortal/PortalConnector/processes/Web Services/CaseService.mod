[Ivy]
137A1AD8C8C617F9 3.20 #module
>Proto >Proto Collection #zClass
Ce0 CaseService Big #zClass
Ce0 WS #cInfo
Ce0 #process
Ce0 @TextInP .webServiceName .webServiceName #zField
Ce0 @TextInP .implementationClassName .implementationClassName #zField
Ce0 @TextInP .authenticationType .authenticationType #zField
Ce0 @TextInP .resExport .resExport #zField
Ce0 @TextInP .type .type #zField
Ce0 @TextInP .processKind .processKind #zField
Ce0 @AnnotationInP-0n ai ai #zField
Ce0 @TextInP .xml .xml #zField
Ce0 @TextInP .responsibility .responsibility #zField
Ce0 @GridStep f14 '' #zField
Ce0 @StartWS f15 '' #zField
Ce0 @PushWFArc f16 '' #zField
Ce0 @GridStep f18 '' #zField
Ce0 @StartWS f20 '' #zField
Ce0 @PushWFArc f21 '' #zField
Ce0 @GridStep f33 '' #zField
Ce0 @StartWS f34 '' #zField
Ce0 @PushWFArc f36 '' #zField
Ce0 @StartWS f0 '' #zField
Ce0 @GridStep f2 '' #zField
Ce0 @PushWFArc f3 '' #zField
Ce0 @StartWS f5 '' #zField
Ce0 @GridStep f7 '' #zField
Ce0 @PushWFArc f43 '' #zField
Ce0 @StartWS f45 '' #zField
Ce0 @GridStep f47 '' #zField
Ce0 @PushWFArc f48 '' #zField
Ce0 @StartWS f50 '' #zField
Ce0 @GridStep f53 '' #zField
Ce0 @PushWFArc f54 '' #zField
Ce0 @StartWS f55 '' #zField
Ce0 @GridStep f57 '' #zField
Ce0 @PushWFArc f58 '' #zField
Ce0 @CallSub f28 '' #zField
Ce0 @Alternative f29 '' #zField
Ce0 @EndWS f30 '' #zField
Ce0 @PushWFArc f60 '' #zField
Ce0 @PushWFArc f61 '' #zField
Ce0 @PushWFArc f25 '' #zField
Ce0 @PushWFArc f12 '' #zField
Ce0 @PushWFArc f4 '' #zField
Ce0 @PushWFArc f37 '' #zField
Ce0 @PushWFArc f17 '' #zField
Ce0 @PushWFArc f22 '' #zField
Ce0 @PushWFArc f42 '' #zField
Ce0 @PushWFArc f44 '' #zField
Ce0 @StartWS f1 '' #zField
Ce0 @GridStep f6 '' #zField
Ce0 @PushWFArc f9 '' #zField
Ce0 @PushWFArc f13 '' #zField
Ce0 @StartWS f19 '' #zField
Ce0 @GridStep f35 '' #zField
Ce0 @PushWFArc f40 '' #zField
Ce0 @PushWFArc f49 '' #zField
Ce0 @StartWS f46 '' #zField
Ce0 @GridStep f51 '' #zField
Ce0 @PushWFArc f52 '' #zField
Ce0 @PushWFArc f56 '' #zField
Ce0 @StartWS f8 '' #zField
Ce0 @GridStep f10 '' #zField
Ce0 @PushWFArc f11 '' #zField
Ce0 @PushWFArc f23 '' #zField
Ce0 @StartWS f24 '' #zField
Ce0 @GridStep f27 '' #zField
Ce0 @PushWFArc f31 '' #zField
Ce0 @PushWFArc f26 '' #zField
Ce0 @StartWS f32 '' #zField
Ce0 @GridStep f38 '' #zField
Ce0 @PushWFArc f39 '' #zField
Ce0 @PushWFArc f41 '' #zField
Ce0 @StartWS f59 '' #zField
Ce0 @GridStep f62 '' #zField
Ce0 @PushWFArc f63 '' #zField
Ce0 @PushWFArc f64 '' #zField
Ce0 @StartWS f65 '' #zField
Ce0 @GridStep f66 '' #zField
Ce0 @PushWFArc f67 '' #zField
Ce0 @PushWFArc f68 '' #zField
>Proto Ce0 Ce0 CaseService #zField
Ce0 f14 actionDecl 'ch.ivy.ws.addon.CaseServiceData out;
' #txt
Ce0 f14 actionTable 'out=in;
' #txt
Ce0 f14 actionCode 'import ch.ivy.ws.addon.bo.NoteServiceResult;
import ch.ivy.ws.addon.WSException;
import ch.ivy.ws.addon.WsServiceFactory;

try{
	NoteServiceResult nsResult = WsServiceFactory.getCaseService().findNotes(in.ivyCase.id);
	in.notes = nsResult.getNotes();
	in.errors = nsResult.getErrors();
}catch(WSException e){
	in.errors.add(e);
}

' #txt
Ce0 f14 type ch.ivy.ws.addon.CaseServiceData #txt
Ce0 f14 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>find notes
by case id</name>
        <nameStyle>21,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ce0 f14 1270 140 36 24 20 -2 #rect
Ce0 f14 @|StepIcon #fIcon
Ce0 f15 inParamDecl '<java.lang.Long id> param;' #txt
Ce0 f15 inParamTable 'out.ivyCase.id=param.id;
' #txt
Ce0 f15 outParamDecl '<List<ch.ivy.ws.addon.WSException> errors,List<ch.ivy.ws.addon.types.IvyNote> notes> result;
' #txt
Ce0 f15 outParamTable 'result.errors=in.errors;
result.notes=in.notes;
' #txt
Ce0 f15 actionDecl 'ch.ivy.ws.addon.CaseServiceData out;
' #txt
Ce0 f15 callSignature findNotes(Long) #txt
Ce0 f15 useUserDefinedException false #txt
Ce0 f15 taskData '#
#Tue Jul 01 11:47:42 CEST 2014
TaskTriggered.PRI=2
' #txt
Ce0 f15 caseData '#
#Tue Jul 01 11:47:42 CEST 2014
businessCalendarName=
businessCreator.user=
businessMilestone.timestamp=
businessObject.code=
businessObject.docDb.code=
businessObject.folder.id=
businessObject.name=
businessPriority=
businessStart.timestamp=
case.description=
case.name=
correspondent.id=
mainContact.docDb.code=
mainContact.folder.id=
mainContact.id=
mainContact.name=
mainContact.type=
process.code=
process.name=
processCategory.code=
processCategory.name=
subType.code=
subType.name=
type.code=
type.name=
' #txt
Ce0 f15 type ch.ivy.ws.addon.CaseServiceData #txt
Ce0 f15 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>findNotes</name>
        <nameStyle>9,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ce0 f15 @C|.responsibility Everybody #txt
Ce0 f15 1275 51 26 26 14 0 #rect
Ce0 f15 @|StartWSIcon #fIcon
Ce0 f16 expr out #txt
Ce0 f16 1288 77 1288 140 #arcP
Ce0 f18 actionDecl 'ch.ivy.ws.addon.CaseServiceData out;
' #txt
Ce0 f18 actionTable 'out=in;
' #txt
Ce0 f18 actionCode 'import ch.ivy.ws.addon.bo.NoteServiceResult;
import ch.ivy.ws.addon.WSException;
import ch.ivy.ws.addon.WsServiceFactory;

try{
		NoteServiceResult nsResult = WsServiceFactory.getCaseService().createNote(in.user,in.ivyCase.id,in.note.message);
		in.note = nsResult.getNewNote();
		in.errors = nsResult.getErrors();
}catch(WSException e){
	in.errors.add(e);
}

' #txt
Ce0 f18 type ch.ivy.ws.addon.CaseServiceData #txt
Ce0 f18 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>create note
for case</name>
        <nameStyle>20,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ce0 f18 1422 140 36 24 20 -2 #rect
Ce0 f18 @|StepIcon #fIcon
Ce0 f20 inParamDecl '<java.lang.String user,java.lang.String message,java.lang.Long id> param;' #txt
Ce0 f20 inParamTable 'out.ivyCase.id=param.id;
out.note.id=param.id;
out.note.message=param.message;
out.user=param.user;
' #txt
Ce0 f20 outParamDecl '<List<ch.ivy.ws.addon.WSException> errors,ch.ivy.ws.addon.types.IvyNote note> result;
' #txt
Ce0 f20 outParamTable 'result.errors=in.errors;
result.note=in.note;
' #txt
Ce0 f20 actionDecl 'ch.ivy.ws.addon.CaseServiceData out;
' #txt
Ce0 f20 callSignature createNote(String,String,Long) #txt
Ce0 f20 useUserDefinedException false #txt
Ce0 f20 taskData '#
#Tue Jul 01 13:39:38 CEST 2014
TaskTriggered.PRI=2
' #txt
Ce0 f20 caseData '#
#Tue Jul 01 13:39:38 CEST 2014
businessCalendarName=
businessCreator.user=
businessMilestone.timestamp=
businessObject.code=
businessObject.docDb.code=
businessObject.folder.id=
businessObject.name=
businessPriority=
businessStart.timestamp=
case.description=
case.name=
correspondent.id=
mainContact.docDb.code=
mainContact.folder.id=
mainContact.id=
mainContact.name=
mainContact.type=
process.code=
process.name=
processCategory.code=
processCategory.name=
subType.code=
subType.name=
type.code=
type.name=
' #txt
Ce0 f20 type ch.ivy.ws.addon.CaseServiceData #txt
Ce0 f20 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>createNote</name>
        <nameStyle>10,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ce0 f20 @C|.responsibility Everybody #txt
Ce0 f20 1427 51 26 26 14 0 #rect
Ce0 f20 @|StartWSIcon #fIcon
Ce0 f21 expr out #txt
Ce0 f21 1440 77 1440 140 #arcP
Ce0 f33 actionDecl 'ch.ivy.ws.addon.CaseServiceData out;
' #txt
Ce0 f33 actionTable 'out=in;
' #txt
Ce0 f33 actionCode 'import ch.ivy.ws.addon.bo.CaseServiceResult;
import ch.ivy.ws.addon.WSException;
import ch.ivy.ws.addon.WsServiceFactory;

try {
  CaseServiceResult csResult = WsServiceFactory.getCaseService().findCase(in.ivyCase.id);
  in.ivyCase = csResult.getOneCase();
  in.errors = csResult.getErrors();
} catch (WSException e) {
	in.errors.add(e);
}' #txt
Ce0 f33 type ch.ivy.ws.addon.CaseServiceData #txt
Ce0 f33 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>find case
by id</name>
        <nameStyle>10,7
5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ce0 f33 302 140 36 24 20 -2 #rect
Ce0 f33 @|StepIcon #fIcon
Ce0 f34 inParamDecl '<java.lang.Long id> param;' #txt
Ce0 f34 inParamTable 'out.ivyCase.id=param.id;
' #txt
Ce0 f34 outParamDecl '<List<ch.ivy.ws.addon.WSException> errors,ch.ivy.ws.addon.types.IvyCase ivyCase> result;
' #txt
Ce0 f34 outParamTable 'result.errors=in.errors;
result.ivyCase=in.ivyCase;
' #txt
Ce0 f34 actionDecl 'ch.ivy.ws.addon.CaseServiceData out;
' #txt
Ce0 f34 callSignature findCase(Long) #txt
Ce0 f34 useUserDefinedException false #txt
Ce0 f34 taskData '#
#Fri Jul 04 15:05:36 CEST 2014
TaskTriggered.PRI=2
' #txt
Ce0 f34 caseData '#
#Fri Jul 04 15:05:36 CEST 2014
businessCalendarName=
businessCreator.user=
businessMilestone.timestamp=
businessObject.code=
businessObject.docDb.code=
businessObject.folder.id=
businessObject.name=
businessPriority=
businessStart.timestamp=
case.description=
case.name=
correspondent.id=
mainContact.docDb.code=
mainContact.folder.id=
mainContact.id=
mainContact.name=
mainContact.type=
process.code=
process.name=
processCategory.code=
processCategory.name=
subType.code=
subType.name=
type.code=
type.name=
' #txt
Ce0 f34 type ch.ivy.ws.addon.CaseServiceData #txt
Ce0 f34 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>findCase</name>
        <nameStyle>8,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ce0 f34 @C|.responsibility Everybody #txt
Ce0 f34 307 51 26 26 14 0 #rect
Ce0 f34 @|StartWSIcon #fIcon
Ce0 f36 expr out #txt
Ce0 f36 320 77 320 140 #arcP
Ce0 f0 inParamDecl '<java.lang.Long id> param;' #txt
Ce0 f0 inParamTable 'out.ivyCase.id=param.id;
' #txt
Ce0 f0 outParamDecl '<List<ch.ivy.ws.addon.WSException> errors,List<ch.ivy.ws.addon.types.IvyAdditionalProperty> addtionalProperties> result;
' #txt
Ce0 f0 outParamTable 'result.errors=in.errors;
result.addtionalProperties=in.ivyAdditionalPropertiesList;
' #txt
Ce0 f0 actionDecl 'ch.ivy.ws.addon.CaseServiceData out;
' #txt
Ce0 f0 callSignature getAddtionalProperties(Long) #txt
Ce0 f0 useUserDefinedException false #txt
Ce0 f0 taskData '#
#Fri Aug 22 11:48:01 CEST 2014
TaskTriggered.PRI=2
' #txt
Ce0 f0 caseData '#
#Fri Aug 22 11:48:01 CEST 2014
businessCalendarName=
businessCreator.user=
businessMilestone.timestamp=
businessObject.code=
businessObject.docDb.code=
businessObject.folder.id=
businessObject.name=
businessPriority=
businessStart.timestamp=
case.description=
case.name=
correspondent.id=
mainContact.docDb.code=
mainContact.folder.id=
mainContact.id=
mainContact.name=
mainContact.type=
process.code=
process.name=
processCategory.code=
processCategory.name=
subType.code=
subType.name=
type.code=
type.name=
' #txt
Ce0 f0 type ch.ivy.ws.addon.CaseServiceData #txt
Ce0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>getAddtional
Properties</name>
        <nameStyle>23,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ce0 f0 @C|.responsibility Everybody #txt
Ce0 f0 891 51 26 26 14 0 #rect
Ce0 f0 @|StartWSIcon #fIcon
Ce0 f2 actionDecl 'ch.ivy.ws.addon.CaseServiceData out;
' #txt
Ce0 f2 actionTable 'out=in;
' #txt
Ce0 f2 actionCode 'import ch.ivy.ws.addon.bo.CaseServiceResult;
import ch.ivy.ws.addon.WSException;
import ch.ivy.ws.addon.WsServiceFactory;

try{
	CaseServiceResult csResult = WsServiceFactory.getCaseService().getAdditionalProperties(in.ivyCase.id);
	in.errors = csResult.getErrors();
	in.ivyAdditionalPropertiesList = csResult.getAddtionalProperties();
	
}catch(WSException e){
	in.errors.add(e);
}' #txt
Ce0 f2 type ch.ivy.ws.addon.CaseServiceData #txt
Ce0 f2 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>find properties</name>
        <nameStyle>15,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ce0 f2 886 140 36 24 20 -2 #rect
Ce0 f2 @|StepIcon #fIcon
Ce0 f3 expr out #txt
Ce0 f3 904 77 904 140 #arcP
Ce0 f5 inParamDecl '<java.lang.Long caseId,List<ch.ivy.ws.addon.types.IvyAdditionalProperty> additionalProperties> param;' #txt
Ce0 f5 inParamTable 'out.ivyAdditionalPropertiesList=param.additionalProperties;
out.ivyCase.id=param.caseId;
' #txt
Ce0 f5 outParamDecl '<List<ch.ivy.ws.addon.WSException> errors> result;
' #txt
Ce0 f5 outParamTable 'result.errors=in.errors;
' #txt
Ce0 f5 actionDecl 'ch.ivy.ws.addon.CaseServiceData out;
' #txt
Ce0 f5 callSignature setAdditionalProperties(Long,List<ch.ivy.ws.addon.types.IvyAdditionalProperty>) #txt
Ce0 f5 useUserDefinedException false #txt
Ce0 f5 taskData '#
#Thu Aug 21 10:27:12 CEST 2014
TaskTriggered.PRI=2
' #txt
Ce0 f5 caseData '#
#Thu Aug 21 10:27:12 CEST 2014
businessCalendarName=
businessCreator.user=
businessMilestone.timestamp=
businessObject.code=
businessObject.docDb.code=
businessObject.folder.id=
businessObject.name=
businessPriority=
businessStart.timestamp=
case.description=
case.name=
correspondent.id=
mainContact.docDb.code=
mainContact.folder.id=
mainContact.id=
mainContact.name=
mainContact.type=
process.code=
process.name=
processCategory.code=
processCategory.name=
subType.code=
subType.name=
type.code=
type.name=
' #txt
Ce0 f5 type ch.ivy.ws.addon.CaseServiceData #txt
Ce0 f5 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>setAdditional
Properties</name>
        <nameStyle>24,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ce0 f5 @C|.responsibility Everybody #txt
Ce0 f5 1043 51 26 26 14 0 #rect
Ce0 f5 @|StartWSIcon #fIcon
Ce0 f7 actionDecl 'ch.ivy.ws.addon.CaseServiceData out;
' #txt
Ce0 f7 actionTable 'out=in;
' #txt
Ce0 f7 actionCode 'import ch.ivy.ws.addon.bo.CaseServiceResult;
import ch.ivy.ws.addon.WSException;
import ch.ivy.ws.addon.WsServiceFactory;

try{
	CaseServiceResult csResult = WsServiceFactory.getCaseService().setAdditionalProperties(in.ivyCase.id, in.ivyAdditionalPropertiesList);
	in.errors = csResult.getErrors();
	
}catch(WSException e){
	in.errors.add(e);
}' #txt
Ce0 f7 type ch.ivy.ws.addon.CaseServiceData #txt
Ce0 f7 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>set
properties
for case</name>
        <nameStyle>4,7
19,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ce0 f7 1038 140 36 24 20 -2 #rect
Ce0 f7 @|StepIcon #fIcon
Ce0 f43 expr out #txt
Ce0 f43 1056 77 1056 140 #arcP
Ce0 f45 inParamDecl '<java.lang.Long id> param;' #txt
Ce0 f45 inParamTable 'out.ivyCase.id=param.id;
' #txt
Ce0 f45 outParamDecl '<List<ch.ivy.ws.addon.WSException> errors,List<ch.ivy.ws.addon.types.IvyDocument> documents> result;
' #txt
Ce0 f45 outParamTable 'result.errors=in.errors;
result.documents=in.documents;
' #txt
Ce0 f45 actionDecl 'ch.ivy.ws.addon.CaseServiceData out;
' #txt
Ce0 f45 callSignature findDocuments(Long) #txt
Ce0 f45 useUserDefinedException false #txt
Ce0 f45 taskData TaskTriggered.PRI=2 #txt
Ce0 f45 type ch.ivy.ws.addon.CaseServiceData #txt
Ce0 f45 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>findDocuments</name>
        <nameStyle>13,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ce0 f45 @C|.responsibility Everybody #txt
Ce0 f45 1659 51 26 26 14 0 #rect
Ce0 f45 @|StartWSIcon #fIcon
Ce0 f47 actionDecl 'ch.ivy.ws.addon.CaseServiceData out;
' #txt
Ce0 f47 actionTable 'out=in;
' #txt
Ce0 f47 actionCode 'import ch.ivy.ws.addon.bo.CaseServiceResult;
import ch.ivy.ws.addon.WSException;
import ch.ivy.ws.addon.WsServiceFactory;

try{
	CaseServiceResult csResult = WsServiceFactory.getCaseService().findDocuments(in.ivyCase.id);
	in.documents = csResult.getDocuments();
	in.errors = csResult.getErrors();
}catch(WSException e){
	in.errors.add(e);
}

' #txt
Ce0 f47 type ch.ivy.ws.addon.CaseServiceData #txt
Ce0 f47 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>find documents</name>
        <nameStyle>14,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ce0 f47 1654 140 36 24 20 -2 #rect
Ce0 f47 @|StepIcon #fIcon
Ce0 f48 expr out #txt
Ce0 f48 1672 77 1672 140 #arcP
Ce0 f50 inParamDecl '<java.lang.Long caseID,java.lang.String documentName,ch.ivyteam.ivy.scripting.objects.Binary documentContent> param;' #txt
Ce0 f50 inParamTable 'out.documentContent=param.documentContent;
out.documentName=param.documentName;
out.ivyCase.id=param.caseID;
' #txt
Ce0 f50 outParamDecl '<ch.ivy.ws.addon.types.IvyDocument document,List<ch.ivy.ws.addon.WSException> errors> result;
' #txt
Ce0 f50 outParamTable 'result.document=in.document;
result.errors=in.errors;
' #txt
Ce0 f50 actionDecl 'ch.ivy.ws.addon.CaseServiceData out;
' #txt
Ce0 f50 callSignature uploadDocument(Long,String,Binary) #txt
Ce0 f50 useUserDefinedException false #txt
Ce0 f50 taskData TaskTriggered.PRI=2 #txt
Ce0 f50 type ch.ivy.ws.addon.CaseServiceData #txt
Ce0 f50 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>uploadDocument</name>
        <nameStyle>14,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ce0 f50 @C|.responsibility Everybody #txt
Ce0 f50 1803 51 26 26 14 0 #rect
Ce0 f50 @|StartWSIcon #fIcon
Ce0 f53 actionDecl 'ch.ivy.ws.addon.CaseServiceData out;
' #txt
Ce0 f53 actionTable 'out=in;
' #txt
Ce0 f53 actionCode 'import ch.ivy.ws.addon.bo.CaseServiceResult;
import ch.ivy.ws.addon.WSException;
import ch.ivy.ws.addon.WsServiceFactory;

try{
	CaseServiceResult csResult = WsServiceFactory.getCaseService().uploadDocument(in.ivyCase.id, in.documentName, in.documentContent);
	in.document = csResult.getDocument();
	in.errors = csResult.getErrors();
}catch(WSException e){
	in.errors.add(e);
}' #txt
Ce0 f53 type ch.ivy.ws.addon.CaseServiceData #txt
Ce0 f53 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>upload document</name>
        <nameStyle>15,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ce0 f53 1798 140 36 24 20 -2 #rect
Ce0 f53 @|StepIcon #fIcon
Ce0 f54 expr out #txt
Ce0 f54 1816 77 1816 140 #arcP
Ce0 f55 inParamDecl '<java.lang.Long caseID,java.lang.Long documentId> param;' #txt
Ce0 f55 inParamTable 'out.documentId=param.documentId;
out.ivyCase.id=param.caseID;
' #txt
Ce0 f55 outParamDecl '<ch.ivyteam.ivy.scripting.objects.Binary documentContent,List<ch.ivy.ws.addon.WSException> errors> result;
' #txt
Ce0 f55 outParamTable 'result.documentContent=in.documentContent;
result.errors=in.errors;
' #txt
Ce0 f55 actionDecl 'ch.ivy.ws.addon.CaseServiceData out;
' #txt
Ce0 f55 callSignature downloadDocument(Long,Long) #txt
Ce0 f55 useUserDefinedException false #txt
Ce0 f55 taskData TaskTriggered.PRI=2 #txt
Ce0 f55 type ch.ivy.ws.addon.CaseServiceData #txt
Ce0 f55 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>downloadDocument</name>
        <nameStyle>16,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ce0 f55 @C|.responsibility Everybody #txt
Ce0 f55 1979 51 26 26 14 0 #rect
Ce0 f55 @|StartWSIcon #fIcon
Ce0 f57 actionDecl 'ch.ivy.ws.addon.CaseServiceData out;
' #txt
Ce0 f57 actionTable 'out=in;
' #txt
Ce0 f57 actionCode 'import ch.ivy.ws.addon.bo.CaseServiceResult;
import ch.ivy.ws.addon.WSException;
import ch.ivy.ws.addon.WsServiceFactory;

try{
	CaseServiceResult csResult = WsServiceFactory.getCaseService().downloadDocument(in.ivyCase.id, in.documentId);
	in.documentContent = csResult.getDocumentContent();
	in.errors = csResult.getErrors();
}catch(WSException e){
	in.errors.add(e);
}' #txt
Ce0 f57 type ch.ivy.ws.addon.CaseServiceData #txt
Ce0 f57 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>download document</name>
        <nameStyle>17,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ce0 f57 1974 140 36 24 20 -2 #rect
Ce0 f57 @|StepIcon #fIcon
Ce0 f58 expr out #txt
Ce0 f58 1992 77 1992 140 #arcP
Ce0 f28 type ch.ivy.ws.addon.CaseServiceData #txt
Ce0 f28 processCall FunctionalProcesses/ErrorLog:logError(List<java.lang.Exception>) #txt
Ce0 f28 doCall true #txt
Ce0 f28 requestActionDecl '<java.util.List<java.lang.Exception> errors> param;
' #txt
Ce0 f28 requestMappingAction 'param.errors=in.errors;
' #txt
Ce0 f28 responseActionDecl 'ch.ivy.ws.addon.CaseServiceData out;
' #txt
Ce0 f28 responseMappingAction 'out=in;
' #txt
Ce0 f28 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>log errors if any</name>
        <nameStyle>17,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ce0 f28 926 308 36 24 20 -2 #rect
Ce0 f28 @|CallSubIcon #fIcon
Ce0 f29 type ch.ivy.ws.addon.CaseServiceData #txt
Ce0 f29 930 250 28 28 14 0 #rect
Ce0 f29 @|AlternativeIcon #fIcon
Ce0 f30 type ch.ivy.ws.addon.CaseServiceData #txt
Ce0 f30 931 379 26 26 14 0 #rect
Ce0 f30 @|EndWSIcon #fIcon
Ce0 f60 expr in #txt
Ce0 f60 944 278 944 308 #arcP
Ce0 f61 expr out #txt
Ce0 f61 944 332 944 379 #arcP
Ce0 f25 expr out #txt
Ce0 f25 320 164 930 264 #arcP
Ce0 f25 1 320 264 #addKink
Ce0 f25 1 0.3893137469825231 0 0 #arcLabel
Ce0 f12 expr out #txt
Ce0 f12 904 164 930 264 #arcP
Ce0 f12 1 904 264 #addKink
Ce0 f12 1 0.26450091240089235 0 0 #arcLabel
Ce0 f4 expr out #txt
Ce0 f4 1056 164 958 264 #arcP
Ce0 f4 1 1056 264 #addKink
Ce0 f4 1 0.3377606765990146 0 0 #arcLabel
Ce0 f37 expr out #txt
Ce0 f37 1288 164 958 264 #arcP
Ce0 f37 1 1288 264 #addKink
Ce0 f37 1 0.39383478216831114 0 0 #arcLabel
Ce0 f17 expr out #txt
Ce0 f17 1440 164 958 264 #arcP
Ce0 f17 1 1440 264 #addKink
Ce0 f17 1 0.41979192209425265 0 0 #arcLabel
Ce0 f22 expr out #txt
Ce0 f22 1672 164 958 264 #arcP
Ce0 f22 1 1672 264 #addKink
Ce0 f22 1 0.441217750988184 0 0 #arcLabel
Ce0 f42 expr out #txt
Ce0 f42 1816 164 958 264 #arcP
Ce0 f42 1 1816 264 #addKink
Ce0 f42 1 0.4461560836759817 0 0 #arcLabel
Ce0 f44 expr out #txt
Ce0 f44 1992 164 958 264 #arcP
Ce0 f44 1 1992 264 #addKink
Ce0 f44 1 0.45490633336155467 0 0 #arcLabel
Ce0 f1 inParamDecl '<java.lang.Integer caseId> param;' #txt
Ce0 f1 inParamTable 'out.ivyCase.id=param.caseId;
' #txt
Ce0 f1 outParamDecl '<List<ch.ivy.ws.addon.WSException> errors> result;
' #txt
Ce0 f1 outParamTable 'result.errors=in.errors;
' #txt
Ce0 f1 actionDecl 'ch.ivy.ws.addon.CaseServiceData out;
' #txt
Ce0 f1 callSignature destroyCase(Integer) #txt
Ce0 f1 useUserDefinedException false #txt
Ce0 f1 taskData TaskTriggered.PRI=2 #txt
Ce0 f1 type ch.ivy.ws.addon.CaseServiceData #txt
Ce0 f1 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>destroyCase</name>
        <nameStyle>11,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ce0 f1 @C|.responsibility Everybody #txt
Ce0 f1 467 51 26 26 14 0 #rect
Ce0 f1 @|StartWSIcon #fIcon
Ce0 f6 actionDecl 'ch.ivy.ws.addon.CaseServiceData out;
' #txt
Ce0 f6 actionTable 'out=in;
' #txt
Ce0 f6 actionCode 'import ch.ivy.ws.addon.WSException;
import ch.ivy.ws.addon.WsServiceFactory;
import ch.ivy.ws.addon.bo.CaseServiceResult;
try{
	WsServiceFactory.getCaseService().destroyCase(in.ivyCase.id);
}catch(WSException e){
	in.errors.add(e);
}
' #txt
Ce0 f6 type ch.ivy.ws.addon.CaseServiceData #txt
Ce0 f6 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>destroy case</name>
        <nameStyle>12
</nameStyle>
    </language>
</elementInfo>
' #txt
Ce0 f6 462 140 36 24 20 -2 #rect
Ce0 f6 @|StepIcon #fIcon
Ce0 f9 expr out #txt
Ce0 f9 480 77 480 140 #arcP
Ce0 f13 expr out #txt
Ce0 f13 480 164 930 264 #arcP
Ce0 f13 1 480 264 #addKink
Ce0 f13 1 0.3122065258061619 0 0 #arcLabel
Ce0 f19 inParamDecl '<java.lang.Integer startIndex,java.lang.Integer count,ch.ivy.ws.addon.service.CaseSearchCriteria caseSearchCriteria> param;' #txt
Ce0 f19 inParamTable 'out.caseSearchCriteria=param.caseSearchCriteria;
out.count=param.count;
out.startIndex=param.startIndex;
' #txt
Ce0 f19 outParamDecl '<List<ch.ivy.ws.addon.WSException> errors,List<ch.ivy.ws.addon.types.IvyCase> ivyCases> result;
' #txt
Ce0 f19 outParamTable 'result.errors=in.errors;
result.ivyCases=in.ivyCases;
' #txt
Ce0 f19 actionDecl 'ch.ivy.ws.addon.CaseServiceData out;
' #txt
Ce0 f19 callSignature findCasesByCriteria(Integer,Integer,ch.ivy.ws.addon.service.CaseSearchCriteria) #txt
Ce0 f19 useUserDefinedException false #txt
Ce0 f19 taskData TaskTriggered.PRI=2 #txt
Ce0 f19 type ch.ivy.ws.addon.CaseServiceData #txt
Ce0 f19 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>findCasesByCriteria(CaseSearchCriteria)</name>
        <nameStyle>39,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ce0 f19 @C|.responsibility Everybody #txt
Ce0 f19 19 51 26 26 14 0 #rect
Ce0 f19 @|StartWSIcon #fIcon
Ce0 f35 actionDecl 'ch.ivy.ws.addon.CaseServiceData out;
' #txt
Ce0 f35 actionTable 'out=in;
' #txt
Ce0 f35 actionCode 'import ch.ivy.ws.addon.WsServiceFactory;
import ch.ivy.ws.addon.bo.CaseServiceResult;
import ch.ivy.ws.addon.WSException;

try {
	CaseServiceResult result = WsServiceFactory.getCaseService().findCasesByCriteria(in.caseSearchCriteria, in.startIndex, in.count);
	in.ivyCases = result.cases;
	in.errors = result.errors;
} catch (WSException e) {
	in.errors.add(e);
}' #txt
Ce0 f35 type ch.ivy.ws.addon.CaseServiceData #txt
Ce0 f35 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>findCasesByCriteria</name>
        <nameStyle>19,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ce0 f35 14 148 36 24 20 -2 #rect
Ce0 f35 @|StepIcon #fIcon
Ce0 f40 expr out #txt
Ce0 f40 32 77 32 148 #arcP
Ce0 f40 0 0.700182762396949 0 0 #arcLabel
Ce0 f49 expr out #txt
Ce0 f49 32 172 930 264 #arcP
Ce0 f49 1 32 264 #addKink
Ce0 f49 1 0.35701468075883286 0 0 #arcLabel
Ce0 f46 inParamDecl '<java.lang.Long caseID,java.lang.Long documentId> param;' #txt
Ce0 f46 inParamTable 'out.documentId=param.documentId;
out.ivyCase.id=param.caseID;
' #txt
Ce0 f46 outParamDecl '<List<ch.ivy.ws.addon.WSException> errors> result;' #txt
Ce0 f46 outParamTable 'result.errors=in.errors;
' #txt
Ce0 f46 actionDecl 'ch.ivy.ws.addon.CaseServiceData out;
' #txt
Ce0 f46 callSignature removeDocument(Long,Long) #txt
Ce0 f46 useUserDefinedException false #txt
Ce0 f46 taskData TaskTriggered.PRI=2 #txt
Ce0 f46 type ch.ivy.ws.addon.CaseServiceData #txt
Ce0 f46 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>removeDocument</name>
        <nameStyle>14,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ce0 f46 @C|.responsibility Everybody #txt
Ce0 f46 2179 51 26 26 14 0 #rect
Ce0 f46 @|StartWSIcon #fIcon
Ce0 f51 actionDecl 'ch.ivy.ws.addon.CaseServiceData out;
' #txt
Ce0 f51 actionTable 'out=in;
' #txt
Ce0 f51 actionCode 'import ch.ivy.ws.addon.bo.CaseServiceResult;
import ch.ivy.ws.addon.WSException;
import ch.ivy.ws.addon.WsServiceFactory;

try{
	CaseServiceResult csResult = WsServiceFactory.getCaseService().removeDocument(in.ivyCase.id, in.documentId);
	in.errors = csResult.getErrors();
}catch(WSException e){
	in.errors.add(e);
}' #txt
Ce0 f51 type ch.ivy.ws.addon.CaseServiceData #txt
Ce0 f51 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>remove document</name>
        <nameStyle>15
</nameStyle>
    </language>
</elementInfo>
' #txt
Ce0 f51 2174 140 36 24 20 -2 #rect
Ce0 f51 @|StepIcon #fIcon
Ce0 f52 expr out #txt
Ce0 f52 2192 77 2192 140 #arcP
Ce0 f56 expr out #txt
Ce0 f56 2192 164 958 264 #arcP
Ce0 f56 1 2192 264 #addKink
Ce0 f56 1 0.46295323869949495 0 0 #arcLabel
Ce0 f8 inParamDecl '<ch.ivy.ws.addon.service.CaseSearchCriteria caseSearchCriteria> param;' #txt
Ce0 f8 inParamTable 'out.caseSearchCriteria=param.caseSearchCriteria;
' #txt
Ce0 f8 outParamDecl '<List<ch.ivy.ws.addon.WSException> errors,java.lang.Long caseCount> result;
' #txt
Ce0 f8 outParamTable 'result.errors=in.errors;
result.caseCount=in.caseCount;
' #txt
Ce0 f8 actionDecl 'ch.ivy.ws.addon.CaseServiceData out;
' #txt
Ce0 f8 callSignature countCasesByCriteria(ch.ivy.ws.addon.service.CaseSearchCriteria) #txt
Ce0 f8 useUserDefinedException false #txt
Ce0 f8 taskData TaskTriggered.PRI=2 #txt
Ce0 f8 type ch.ivy.ws.addon.CaseServiceData #txt
Ce0 f8 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>countCasesByCriteria(CaseSearchCriteria)</name>
    </language>
</elementInfo>
' #txt
Ce0 f8 @C|.responsibility Everybody #txt
Ce0 f8 603 51 26 26 14 0 #rect
Ce0 f8 @|StartWSIcon #fIcon
Ce0 f10 actionDecl 'ch.ivy.ws.addon.CaseServiceData out;
' #txt
Ce0 f10 actionTable 'out=in;
' #txt
Ce0 f10 actionCode 'import ch.ivy.ws.addon.WsServiceFactory;
import ch.ivy.ws.addon.bo.CaseServiceResult;
import ch.ivy.ws.addon.WSException;

try {
	CaseServiceResult result = WsServiceFactory.getCaseService().countCasesByCriteria(in.caseSearchCriteria);
	in.caseCount = result.caseCount;
	in.errors = result.errors;
} catch (WSException e) {
	in.errors.add(e);
}' #txt
Ce0 f10 type ch.ivy.ws.addon.CaseServiceData #txt
Ce0 f10 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>count Case</name>
        <nameStyle>10,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ce0 f10 598 140 36 24 20 -2 #rect
Ce0 f10 @|StepIcon #fIcon
Ce0 f11 expr out #txt
Ce0 f11 616 77 616 140 #arcP
Ce0 f11 0 0.700182762396949 0 0 #arcLabel
Ce0 f23 expr out #txt
Ce0 f23 616 164 930 264 #arcP
Ce0 f23 1 616 264 #addKink
Ce0 f23 1 0.27058643387471143 0 0 #arcLabel
Ce0 f24 inParamDecl '<ch.ivy.ws.addon.types.IvyCase ivyCase> param;' #txt
Ce0 f24 inParamTable 'out.ivyCase=param.ivyCase;
' #txt
Ce0 f24 outParamDecl '<java.util.List<ch.ivy.ws.addon.WSException> error> result;
' #txt
Ce0 f24 outParamTable 'result.error=in.errors;
' #txt
Ce0 f24 actionDecl 'ch.ivy.ws.addon.CaseServiceData out;
' #txt
Ce0 f24 callSignature saveCase(ch.ivy.ws.addon.types.IvyCase) #txt
Ce0 f24 useUserDefinedException false #txt
Ce0 f24 taskData TaskTriggered.PRI=2 #txt
Ce0 f24 type ch.ivy.ws.addon.CaseServiceData #txt
Ce0 f24 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>saveCase(IvyCase)</name>
        <nameStyle>17,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ce0 f24 @C|.responsibility Everybody #txt
Ce0 f24 2323 51 26 26 14 0 #rect
Ce0 f24 @|StartWSIcon #fIcon
Ce0 f27 actionDecl 'ch.ivy.ws.addon.CaseServiceData out;
' #txt
Ce0 f27 actionTable 'out=in;
' #txt
Ce0 f27 actionCode 'import ch.ivy.ws.addon.WSException;
import ch.ivy.ws.addon.WsServiceFactory;

try {
 WsServiceFactory.getCaseService().saveCase(in.ivyCase);
} catch (WSException e) {
 in.errors.add(e);
}' #txt
Ce0 f27 type ch.ivy.ws.addon.CaseServiceData #txt
Ce0 f27 2318 148 36 24 20 -2 #rect
Ce0 f27 @|StepIcon #fIcon
Ce0 f31 expr out #txt
Ce0 f31 2336 77 2336 148 #arcP
Ce0 f31 0 0.43214804063860646 0 0 #arcLabel
Ce0 f26 expr out #txt
Ce0 f26 2336 172 958 264 #arcP
Ce0 f26 1 2336 264 #addKink
Ce0 f26 1 0.43214804063860646 0 0 #arcLabel
Ce0 f32 inParamDecl '<ch.ivy.ws.addon.service.CaseSearchCriteria caseSearchCriteria> param;' #txt
Ce0 f32 inParamTable 'out.caseSearchCriteria=param.caseSearchCriteria;
' #txt
Ce0 f32 outParamDecl '<ch.ivy.ws.addon.types.CaseStateStatistic caseStateStatistic,java.util.List<ch.ivy.ws.addon.WSException> errors> result;
' #txt
Ce0 f32 outParamTable 'result.caseStateStatistic=in.caseStateStatistic;
result.errors=in.errors;
' #txt
Ce0 f32 actionDecl 'ch.ivy.ws.addon.CaseServiceData out;
' #txt
Ce0 f32 callSignature analyzeCaseStateStatistic(ch.ivy.ws.addon.service.CaseSearchCriteria) #txt
Ce0 f32 useUserDefinedException false #txt
Ce0 f32 taskData TaskTriggered.PRI=2 #txt
Ce0 f32 type ch.ivy.ws.addon.CaseServiceData #txt
Ce0 f32 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>analyzeCaseStateStatistic(CaseSearchCriteria)</name>
        <nameStyle>45,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ce0 f32 @C|.responsibility Everybody #txt
Ce0 f32 2482 49 30 30 18 3 #rect
Ce0 f32 @|StartWSIcon #fIcon
Ce0 f38 actionDecl 'ch.ivy.ws.addon.CaseServiceData out;
' #txt
Ce0 f38 actionTable 'out=in;
' #txt
Ce0 f38 actionCode 'import ch.ivy.ws.addon.WsServiceFactory;
import ch.ivy.ws.addon.bo.CaseServiceResult;
import ch.ivy.ws.addon.WSException;

try {
	CaseServiceResult result = WsServiceFactory.getCaseService().analyzeCaseStateStatistic(in.caseSearchCriteria);
	in.caseStateStatistic = result.caseStateStatistic;
	in.errors = result.errors;
} catch (WSException e) {
	in.errors.add(e);
}' #txt
Ce0 f38 type ch.ivy.ws.addon.CaseServiceData #txt
Ce0 f38 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>analyze case state chart</name>
        <nameStyle>24
</nameStyle>
    </language>
</elementInfo>
' #txt
Ce0 f38 2479 151 36 24 20 -2 #rect
Ce0 f38 @|StepIcon #fIcon
Ce0 f39 expr out #txt
Ce0 f39 2497 79 2497 151 #arcP
Ce0 f41 expr out #txt
Ce0 f41 2497 175 958 264 #arcP
Ce0 f41 1 2497 264 #addKink
Ce0 f41 1 0.4674238080285616 0 0 #arcLabel
Ce0 f59 inParamDecl '<ch.ivy.ws.addon.service.CaseSearchCriteria caseSearchCriteria> param;' #txt
Ce0 f59 inParamTable 'out.caseSearchCriteria=param.caseSearchCriteria;
' #txt
Ce0 f59 outParamDecl '<ch.ivy.ws.addon.types.ElapsedTimeStatistic elapsedTimeStatistic,java.util.List<ch.ivy.ws.addon.WSException> errors> result;
' #txt
Ce0 f59 outParamTable 'result.elapsedTimeStatistic=in.elapsedTimeStatistic;
result.errors=in.errors;
' #txt
Ce0 f59 actionDecl 'ch.ivy.ws.addon.CaseServiceData out;
' #txt
Ce0 f59 callSignature analyzeElapsedTimeByCaseCategory(ch.ivy.ws.addon.service.CaseSearchCriteria) #txt
Ce0 f59 useUserDefinedException false #txt
Ce0 f59 taskData TaskTriggered.PRI=2 #txt
Ce0 f59 type ch.ivy.ws.addon.CaseServiceData #txt
Ce0 f59 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>analyzeElapsedTimeByCaseCategory(CaseSearchCriteria)</name>
        <nameStyle>52,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ce0 f59 @C|.responsibility Everybody #txt
Ce0 f59 2769 49 30 30 13 9 #rect
Ce0 f59 @|StartWSIcon #fIcon
Ce0 f62 actionDecl 'ch.ivy.ws.addon.CaseServiceData out;
' #txt
Ce0 f62 actionTable 'out=in;
' #txt
Ce0 f62 actionCode 'import ch.ivy.ws.addon.WsServiceFactory;
import ch.ivy.ws.addon.bo.CaseServiceResult;
import ch.ivy.ws.addon.WSException;

try {
	CaseServiceResult result = WsServiceFactory.getCaseService().analyzeElapsedTimeByCaseCategory(in.caseSearchCriteria);
	in.elapsedTimeStatistic = result.elapsedTimeStatistic;
	in.errors = result.errors;
} catch (WSException e) {
	in.errors.add(e);
}' #txt
Ce0 f62 type ch.ivy.ws.addon.CaseServiceData #txt
Ce0 f62 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>analyze case state chart</name>
        <nameStyle>24
</nameStyle>
    </language>
</elementInfo>
' #txt
Ce0 f62 2766 150 36 24 20 -2 #rect
Ce0 f62 @|StepIcon #fIcon
Ce0 f63 expr out #txt
Ce0 f63 2784 79 2784 150 #arcP
Ce0 f64 expr out #txt
Ce0 f64 2784 174 958 264 #arcP
Ce0 f64 1 2784 264 #addKink
Ce0 f64 1 0.4716540658197106 0 0 #arcLabel
Ce0 f65 inParamDecl '<java.lang.String language,ch.ivy.ws.addon.service.CaseSearchCriteria caseSearchCriteria> param;' #txt
Ce0 f65 inParamTable 'out.caseSearchCriteria=param.caseSearchCriteria;
out.language=param.language;
' #txt
Ce0 f65 outParamDecl '<java.util.List<ch.ivy.ws.addon.CategoryData> categories,java.util.List<ch.ivy.ws.addon.WSException> errors> result;
' #txt
Ce0 f65 outParamTable 'result.categories=in.categories;
result.errors=in.errors;
' #txt
Ce0 f65 actionDecl 'ch.ivy.ws.addon.CaseServiceData out;
' #txt
Ce0 f65 callSignature findCaseCategoriesByCriteria(String,ch.ivy.ws.addon.service.CaseSearchCriteria) #txt
Ce0 f65 useUserDefinedException false #txt
Ce0 f65 taskData TaskTriggered.PRI=2 #txt
Ce0 f65 type ch.ivy.ws.addon.CaseServiceData #txt
Ce0 f65 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>findCaseCategoriesByCriteria(CaseSearchCriteria)</name>
        <nameStyle>48,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ce0 f65 @C|.responsibility Everybody #txt
Ce0 f65 3121 48 30 30 13 9 #rect
Ce0 f65 @|StartWSIcon #fIcon
Ce0 f66 actionDecl 'ch.ivy.ws.addon.CaseServiceData out;
' #txt
Ce0 f66 actionTable 'out=in;
' #txt
Ce0 f66 actionCode 'import ch.ivy.ws.addon.WsServiceFactory;
import ch.ivy.ws.addon.bo.CaseServiceResult;
import ch.ivy.ws.addon.WSException;

try {
	CaseServiceResult result = WsServiceFactory.getCaseService().findCategories(in.caseSearchCriteria.jsonQuery, in.caseSearchCriteria.involvedUsername, in.caseSearchCriteria.involvedApplications, in.language);
	in.categories = result.categories;
	in.errors = result.errors;
} catch (WSException e) {
	in.errors.add(e);
}' #txt
Ce0 f66 type ch.ivy.ws.addon.CaseServiceData #txt
Ce0 f66 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>find case categories</name>
        <nameStyle>20
</nameStyle>
    </language>
</elementInfo>
' #txt
Ce0 f66 3118 149 36 24 20 -2 #rect
Ce0 f66 @|StepIcon #fIcon
Ce0 f67 expr out #txt
Ce0 f67 3136 78 3136 149 #arcP
Ce0 f68 expr out #txt
Ce0 f68 3136 173 958 264 #arcP
Ce0 f68 1 3136 264 #addKink
Ce0 f68 1 0.4760818495321147 0 0 #arcLabel
>Proto Ce0 .webServiceName ch.ivy.ws.addon.CaseService #txt
>Proto Ce0 .authenticationType 'HTTP Basic' #txt
>Proto Ce0 .type ch.ivy.ws.addon.CaseServiceData #txt
>Proto Ce0 .processKind WEB_SERVICE #txt
>Proto Ce0 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <swimlaneLabel>Cases</swimlaneLabel>
        <swimlaneLabel>Additional Properties</swimlaneLabel>
        <swimlaneLabel>Notes</swimlaneLabel>
        <swimlaneLabel>Documents</swimlaneLabel>
        <swimlaneLabel></swimlaneLabel>
    </language>
    <swimlaneSize>800</swimlaneSize>
    <swimlaneSize>384</swimlaneSize>
    <swimlaneSize>384</swimlaneSize>
    <swimlaneSize>960</swimlaneSize>
    <swimlaneColor gradient="false">-13382401</swimlaneColor>
    <swimlaneColor gradient="false">-3355393</swimlaneColor>
    <swimlaneColor gradient="false">-10027162</swimlaneColor>
    <swimlaneColor gradient="false">-13159</swimlaneColor>
    <swimlaneType>POOL</swimlaneType>
    <swimlaneType>POOL</swimlaneType>
    <swimlaneType>POOL</swimlaneType>
    <swimlaneType>POOL</swimlaneType>
    <swimlaneSpaceBefore>0</swimlaneSpaceBefore>
    <swimlaneSpaceBefore>0</swimlaneSpaceBefore>
    <swimlaneSpaceBefore>0</swimlaneSpaceBefore>
    <swimlaneSpaceBefore>0</swimlaneSpaceBefore>
</elementInfo>
' #txt
>Proto Ce0 -8 -8 16 16 16 26 #rect
>Proto Ce0 '' #fIcon
Ce0 f15 mainOut f16 tail #connect
Ce0 f16 head f14 mainIn #connect
Ce0 f20 mainOut f21 tail #connect
Ce0 f21 head f18 mainIn #connect
Ce0 f34 mainOut f36 tail #connect
Ce0 f36 head f33 mainIn #connect
Ce0 f0 mainOut f3 tail #connect
Ce0 f3 head f2 mainIn #connect
Ce0 f5 mainOut f43 tail #connect
Ce0 f43 head f7 mainIn #connect
Ce0 f45 mainOut f48 tail #connect
Ce0 f48 head f47 mainIn #connect
Ce0 f50 mainOut f54 tail #connect
Ce0 f54 head f53 mainIn #connect
Ce0 f55 mainOut f58 tail #connect
Ce0 f58 head f57 mainIn #connect
Ce0 f29 out f60 tail #connect
Ce0 f60 head f28 mainIn #connect
Ce0 f28 mainOut f61 tail #connect
Ce0 f61 head f30 mainIn #connect
Ce0 f33 mainOut f25 tail #connect
Ce0 f25 head f29 in #connect
Ce0 f2 mainOut f12 tail #connect
Ce0 f12 head f29 in #connect
Ce0 f7 mainOut f4 tail #connect
Ce0 f4 head f29 in #connect
Ce0 f14 mainOut f37 tail #connect
Ce0 f37 head f29 in #connect
Ce0 f18 mainOut f17 tail #connect
Ce0 f17 head f29 in #connect
Ce0 f47 mainOut f22 tail #connect
Ce0 f22 head f29 in #connect
Ce0 f53 mainOut f42 tail #connect
Ce0 f42 head f29 in #connect
Ce0 f57 mainOut f44 tail #connect
Ce0 f44 head f29 in #connect
Ce0 f1 mainOut f9 tail #connect
Ce0 f9 head f6 mainIn #connect
Ce0 f6 mainOut f13 tail #connect
Ce0 f13 head f29 in #connect
Ce0 f19 mainOut f40 tail #connect
Ce0 f40 head f35 mainIn #connect
Ce0 f35 mainOut f49 tail #connect
Ce0 f49 head f29 in #connect
Ce0 f46 mainOut f52 tail #connect
Ce0 f52 head f51 mainIn #connect
Ce0 f51 mainOut f56 tail #connect
Ce0 f56 head f29 in #connect
Ce0 f8 mainOut f11 tail #connect
Ce0 f11 head f10 mainIn #connect
Ce0 f10 mainOut f23 tail #connect
Ce0 f23 head f29 in #connect
Ce0 f24 mainOut f31 tail #connect
Ce0 f31 head f27 mainIn #connect
Ce0 f27 mainOut f26 tail #connect
Ce0 f26 head f29 in #connect
Ce0 f32 mainOut f39 tail #connect
Ce0 f39 head f38 mainIn #connect
Ce0 f38 mainOut f41 tail #connect
Ce0 f41 head f29 in #connect
Ce0 f59 mainOut f63 tail #connect
Ce0 f63 head f62 mainIn #connect
Ce0 f62 mainOut f64 tail #connect
Ce0 f64 head f29 in #connect
Ce0 f65 mainOut f67 tail #connect
Ce0 f67 head f66 mainIn #connect
Ce0 f66 mainOut f68 tail #connect
Ce0 f68 head f29 in #connect
