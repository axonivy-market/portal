[Ivy]
[>Created: Mon Feb 29 10:02:12 ICT 2016]
137A1AD8C8C617F9 3.18 #module
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
Ce0 @GridStep f8 '' #zField
Ce0 @StartWS f10 '' #zField
Ce0 @PushWFArc f11 '' #zField
Ce0 @GridStep f14 '' #zField
Ce0 @StartWS f15 '' #zField
Ce0 @PushWFArc f16 '' #zField
Ce0 @GridStep f18 '' #zField
Ce0 @StartWS f20 '' #zField
Ce0 @PushWFArc f21 '' #zField
Ce0 @GridStep f23 '' #zField
Ce0 @StartWS f24 '' #zField
Ce0 @PushWFArc f26 '' #zField
Ce0 @GridStep f33 '' #zField
Ce0 @StartWS f34 '' #zField
Ce0 @PushWFArc f36 '' #zField
Ce0 @StartWS f38 '' #zField
Ce0 @GridStep f39 '' #zField
Ce0 @PushWFArc f41 '' #zField
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
Ce0 @PushWFArc f31 '' #zField
Ce0 @PushWFArc f25 '' #zField
Ce0 @PushWFArc f27 '' #zField
Ce0 @PushWFArc f32 '' #zField
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
Ce0 @StartWS f65 '' #zField
Ce0 @GridStep f66 '' #zField
Ce0 @PushWFArc f67 '' #zField
Ce0 @PushWFArc f68 '' #zField
>Proto Ce0 Ce0 CaseService #zField
Ce0 f8 actionDecl 'ch.ivy.ws.addon.CaseServiceData out;
' #txt
Ce0 f8 actionTable 'out=in;
' #txt
Ce0 f8 actionCode 'import ch.ivy.ws.addon.bo.CaseServiceResult;
import ch.ivy.ws.addon.WSException;
import ch.ivy.ws.addon.WsServiceFactory;

try{
	CaseServiceResult csResult = WsServiceFactory.getCaseService().findByFilter(in.user,in.simpleFilter, null, in.applications);
  in.ivyCases =	csResult.getCases();
	in.errors = csResult.getErrors();
	
}catch(WSException e){
	in.errors.add(e);
}' #txt
Ce0 f8 type ch.ivy.ws.addon.CaseServiceData #txt
Ce0 f8 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>find cases
by filter</name>
        <nameStyle>20,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ce0 f8 790 140 36 24 20 -2 #rect
Ce0 f8 @|StepIcon #fIcon
Ce0 f10 inParamDecl '<List<java.lang.String> apps,java.lang.String user,java.lang.String filter> param;' #txt
Ce0 f10 inParamTable 'out.applications=param.apps;
out.simpleFilter=param.filter;
out.user=param.user;
' #txt
Ce0 f10 outParamDecl '<List<ch.ivy.ws.addon.WSException> errors,List<ch.ivy.ws.addon.types.IvyCase> ivyCases> result;
' #txt
Ce0 f10 outParamTable 'result.errors=in.errors;
result.ivyCases=in.ivyCases;
' #txt
Ce0 f10 actionDecl 'ch.ivy.ws.addon.CaseServiceData out;
' #txt
Ce0 f10 callSignature findByFilter(List<String>,String,String) #txt
Ce0 f10 useUserDefinedException false #txt
Ce0 f10 taskData '#
#Tue Jul 01 08:55:43 CEST 2014
TaskTriggered.PRI=2
' #txt
Ce0 f10 caseData '#
#Tue Jul 01 08:55:43 CEST 2014
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
Ce0 f10 taskAndCaseSetupAction 'import ch.ivyteam.ivy.workflow.TaskUpdateDefinition;
ch.ivyteam.ivy.workflow.TaskUpdateDefinition taskUpdDef = new ch.ivyteam.ivy.workflow.TaskUpdateDefinition();
import ch.ivyteam.ivy.request.impl.DefaultCalendarProxy;
DefaultCalendarProxy calendarProxy = ivy.cal as DefaultCalendarProxy;
taskUpdDef.setPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
taskUpdDef.setExpiryActivator("Everybody");
taskUpdDef.setExpiryPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
engine.updateCurrentTask(taskUpdDef);
' #txt
Ce0 f10 type ch.ivy.ws.addon.CaseServiceData #txt
Ce0 f10 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>findByFilter</name>
        <nameStyle>12,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ce0 f10 @C|.responsibility Everybody #txt
Ce0 f10 795 51 26 26 14 0 #rect
Ce0 f10 @|StartWSIcon #fIcon
Ce0 f11 expr out #txt
Ce0 f11 808 77 808 140 #arcP
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
Ce0 f14 1942 140 36 24 20 -2 #rect
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
Ce0 f15 taskAndCaseSetupAction 'import ch.ivyteam.ivy.workflow.TaskUpdateDefinition;
ch.ivyteam.ivy.workflow.TaskUpdateDefinition taskUpdDef = new ch.ivyteam.ivy.workflow.TaskUpdateDefinition();
import ch.ivyteam.ivy.request.impl.DefaultCalendarProxy;
DefaultCalendarProxy calendarProxy = ivy.cal as DefaultCalendarProxy;
taskUpdDef.setPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
taskUpdDef.setExpiryActivator("Everybody");
taskUpdDef.setExpiryPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
engine.updateCurrentTask(taskUpdDef);
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
Ce0 f15 1947 51 26 26 14 0 #rect
Ce0 f15 @|StartWSIcon #fIcon
Ce0 f16 expr out #txt
Ce0 f16 1960 77 1960 140 #arcP
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
Ce0 f18 2094 140 36 24 20 -2 #rect
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
Ce0 f20 taskAndCaseSetupAction 'import ch.ivyteam.ivy.workflow.TaskUpdateDefinition;
ch.ivyteam.ivy.workflow.TaskUpdateDefinition taskUpdDef = new ch.ivyteam.ivy.workflow.TaskUpdateDefinition();
import ch.ivyteam.ivy.request.impl.DefaultCalendarProxy;
DefaultCalendarProxy calendarProxy = ivy.cal as DefaultCalendarProxy;
taskUpdDef.setPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
taskUpdDef.setExpiryActivator("Everybody");
taskUpdDef.setExpiryPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
engine.updateCurrentTask(taskUpdDef);
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
Ce0 f20 2099 51 26 26 14 0 #rect
Ce0 f20 @|StartWSIcon #fIcon
Ce0 f21 expr out #txt
Ce0 f21 2112 77 2112 140 #arcP
Ce0 f23 actionDecl 'ch.ivy.ws.addon.CaseServiceData out;
' #txt
Ce0 f23 actionTable 'out=in;
' #txt
Ce0 f23 actionCode 'import ch.ivy.ws.addon.bo.CaseServiceResult;
import ch.ivyteam.ivy.workflow.CaseState;
import ch.ivy.ws.addon.WSException;
import ch.ivy.ws.addon.WsServiceFactory;

try{
	CaseServiceResult csResult = WsServiceFactory.getCaseService().findByFilter(in.user,in.simpleFilter, CaseState.DONE, in.applications);
	in.ivyCases = csResult.getCases();
	in.errors = csResult.getErrors();
}catch(WSException e){
	in.errors.add(e);
}' #txt
Ce0 f23 type ch.ivy.ws.addon.CaseServiceData #txt
Ce0 f23 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>find only
done cases
by filter</name>
        <nameStyle>30,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ce0 f23 926 140 36 24 20 -2 #rect
Ce0 f23 @|StepIcon #fIcon
Ce0 f24 inParamDecl '<List<java.lang.String> apps,java.lang.String user,java.lang.String filter> param;' #txt
Ce0 f24 inParamTable 'out.applications=param.apps;
out.simpleFilter=param.filter;
out.user=param.user;
' #txt
Ce0 f24 outParamDecl '<List<ch.ivy.ws.addon.WSException> errors,List<ch.ivy.ws.addon.types.IvyCase> ivyCases> result;
' #txt
Ce0 f24 outParamTable 'result.errors=in.errors;
result.ivyCases=in.ivyCases;
' #txt
Ce0 f24 actionDecl 'ch.ivy.ws.addon.CaseServiceData out;
' #txt
Ce0 f24 callSignature findDoneByFilter(List<String>,String,String) #txt
Ce0 f24 useUserDefinedException false #txt
Ce0 f24 taskData '#
#Tue Jul 01 10:46:11 CEST 2014
TaskTriggered.PRI=2
' #txt
Ce0 f24 caseData '#
#Tue Jul 01 10:46:11 CEST 2014
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
Ce0 f24 taskAndCaseSetupAction 'import ch.ivyteam.ivy.workflow.TaskUpdateDefinition;
ch.ivyteam.ivy.workflow.TaskUpdateDefinition taskUpdDef = new ch.ivyteam.ivy.workflow.TaskUpdateDefinition();
import ch.ivyteam.ivy.request.impl.DefaultCalendarProxy;
DefaultCalendarProxy calendarProxy = ivy.cal as DefaultCalendarProxy;
taskUpdDef.setPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
taskUpdDef.setExpiryActivator("Everybody");
taskUpdDef.setExpiryPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
engine.updateCurrentTask(taskUpdDef);
' #txt
Ce0 f24 type ch.ivy.ws.addon.CaseServiceData #txt
Ce0 f24 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>findDoneByFilter</name>
        <nameStyle>16,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ce0 f24 @C|.responsibility Everybody #txt
Ce0 f24 931 51 26 26 14 0 #rect
Ce0 f24 @|StartWSIcon #fIcon
Ce0 f26 expr out #txt
Ce0 f26 944 77 944 140 #arcP
Ce0 f33 actionDecl 'ch.ivy.ws.addon.CaseServiceData out;
' #txt
Ce0 f33 actionTable 'out=in;
' #txt
Ce0 f33 actionCode 'import ch.ivy.ws.addon.bo.CaseServiceResult;
import ch.ivy.ws.addon.WSException;
import ch.ivy.ws.addon.WsServiceFactory;

CaseServiceResult csResult = WsServiceFactory.getCaseService().findCase(in.ivyCase.id);
in.ivyCase = csResult.getOneCase();
in.errors = csResult.getErrors();
' #txt
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
Ce0 f34 taskAndCaseSetupAction 'import ch.ivyteam.ivy.workflow.TaskUpdateDefinition;
ch.ivyteam.ivy.workflow.TaskUpdateDefinition taskUpdDef = new ch.ivyteam.ivy.workflow.TaskUpdateDefinition();
import ch.ivyteam.ivy.request.impl.DefaultCalendarProxy;
DefaultCalendarProxy calendarProxy = ivy.cal as DefaultCalendarProxy;
taskUpdDef.setPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
taskUpdDef.setExpiryActivator("Everybody");
taskUpdDef.setExpiryPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
engine.updateCurrentTask(taskUpdDef);
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
Ce0 f38 inParamDecl '<List<java.lang.String> apps,java.lang.String user> param;' #txt
Ce0 f38 inParamTable 'out.applications=param.apps;
out.user=param.user;
' #txt
Ce0 f38 outParamDecl '<java.util.List<ch.ivy.ws.addon.WSException> errors,List<ch.ivy.ws.addon.types.IvyCase> ivyCases> result;
' #txt
Ce0 f38 outParamTable 'result.errors=in.errors;
result.ivyCases=in.ivyCases;
' #txt
Ce0 f38 actionDecl 'ch.ivy.ws.addon.CaseServiceData out;
' #txt
Ce0 f38 callSignature findAllCases(List<String>,String) #txt
Ce0 f38 useUserDefinedException false #txt
Ce0 f38 taskData '#
#Fri Jun 27 16:12:03 CEST 2014
TaskTriggered.PRI=2
' #txt
Ce0 f38 caseData '#
#Fri Jun 27 16:12:03 CEST 2014
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
Ce0 f38 taskAndCaseSetupAction 'import ch.ivyteam.ivy.workflow.TaskUpdateDefinition;
ch.ivyteam.ivy.workflow.TaskUpdateDefinition taskUpdDef = new ch.ivyteam.ivy.workflow.TaskUpdateDefinition();
import ch.ivyteam.ivy.request.impl.DefaultCalendarProxy;
DefaultCalendarProxy calendarProxy = ivy.cal as DefaultCalendarProxy;
taskUpdDef.setPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
taskUpdDef.setExpiryActivator("Everybody");
taskUpdDef.setExpiryPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
engine.updateCurrentTask(taskUpdDef);
' #txt
Ce0 f38 type ch.ivy.ws.addon.CaseServiceData #txt
Ce0 f38 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>findAllCases</name>
        <nameStyle>12,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ce0 f38 @C|.responsibility Everybody #txt
Ce0 f38 475 51 26 26 14 0 #rect
Ce0 f38 @|StartWSIcon #fIcon
Ce0 f39 actionDecl 'ch.ivy.ws.addon.CaseServiceData out;
' #txt
Ce0 f39 actionTable 'out=in;
' #txt
Ce0 f39 actionCode 'import ch.ivy.ws.addon.bo.CaseServiceResult;
import ch.ivy.ws.addon.WSException;
import ch.ivy.ws.addon.WsServiceFactory;

try{
	//if(in.applications.size()>0){
		CaseServiceResult csResult = WsServiceFactory.getCaseService().findAllCases(in.user, in.applications);
		in.ivyCases = csResult.getCases();
		in.errors = csResult.getErrors();
	//}
}catch(WSException e){
	in.errors.add(e);
}' #txt
Ce0 f39 type ch.ivy.ws.addon.CaseServiceData #txt
Ce0 f39 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>find all
cases</name>
        <nameStyle>9,7
5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ce0 f39 470 140 36 24 20 -2 #rect
Ce0 f39 @|StepIcon #fIcon
Ce0 f41 expr out #txt
Ce0 f41 488 77 488 140 #arcP
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
Ce0 f0 taskAndCaseSetupAction 'import ch.ivyteam.ivy.workflow.TaskUpdateDefinition;
ch.ivyteam.ivy.workflow.TaskUpdateDefinition taskUpdDef = new ch.ivyteam.ivy.workflow.TaskUpdateDefinition();
import ch.ivyteam.ivy.request.impl.DefaultCalendarProxy;
DefaultCalendarProxy calendarProxy = ivy.cal as DefaultCalendarProxy;
taskUpdDef.setPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
taskUpdDef.setExpiryActivator("Everybody");
taskUpdDef.setExpiryPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
engine.updateCurrentTask(taskUpdDef);
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
Ce0 f0 1563 51 26 26 14 0 #rect
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
Ce0 f2 1558 140 36 24 20 -2 #rect
Ce0 f2 @|StepIcon #fIcon
Ce0 f3 expr out #txt
Ce0 f3 1576 77 1576 140 #arcP
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
Ce0 f5 taskAndCaseSetupAction 'import ch.ivyteam.ivy.workflow.TaskUpdateDefinition;
ch.ivyteam.ivy.workflow.TaskUpdateDefinition taskUpdDef = new ch.ivyteam.ivy.workflow.TaskUpdateDefinition();
import ch.ivyteam.ivy.request.impl.DefaultCalendarProxy;
DefaultCalendarProxy calendarProxy = ivy.cal as DefaultCalendarProxy;
taskUpdDef.setPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
taskUpdDef.setExpiryActivator("Everybody");
taskUpdDef.setExpiryPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
engine.updateCurrentTask(taskUpdDef);
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
Ce0 f5 1715 51 26 26 14 0 #rect
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
Ce0 f7 1710 140 36 24 20 -2 #rect
Ce0 f7 @|StepIcon #fIcon
Ce0 f43 expr out #txt
Ce0 f43 1728 77 1728 140 #arcP
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
Ce0 f45 taskAndCaseSetupAction 'import ch.ivyteam.ivy.workflow.TaskUpdateDefinition;
ch.ivyteam.ivy.workflow.TaskUpdateDefinition taskUpdDef = new ch.ivyteam.ivy.workflow.TaskUpdateDefinition();
import ch.ivyteam.ivy.request.impl.DefaultCalendarProxy;
DefaultCalendarProxy calendarProxy = ivy.cal as DefaultCalendarProxy;
taskUpdDef.setPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
taskUpdDef.setExpiryActivator("Everybody");
taskUpdDef.setExpiryPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
engine.updateCurrentTask(taskUpdDef);
' #txt
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
Ce0 f45 2331 51 26 26 14 0 #rect
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
Ce0 f47 2326 140 36 24 20 -2 #rect
Ce0 f47 @|StepIcon #fIcon
Ce0 f48 expr out #txt
Ce0 f48 2344 77 2344 140 #arcP
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
Ce0 f50 taskAndCaseSetupAction 'import ch.ivyteam.ivy.workflow.TaskUpdateDefinition;
ch.ivyteam.ivy.workflow.TaskUpdateDefinition taskUpdDef = new ch.ivyteam.ivy.workflow.TaskUpdateDefinition();
import ch.ivyteam.ivy.request.impl.DefaultCalendarProxy;
DefaultCalendarProxy calendarProxy = ivy.cal as DefaultCalendarProxy;
taskUpdDef.setPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
taskUpdDef.setExpiryActivator("Everybody");
taskUpdDef.setExpiryPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
engine.updateCurrentTask(taskUpdDef);
' #txt
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
Ce0 f50 2475 51 26 26 14 0 #rect
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
Ce0 f53 2470 140 36 24 20 -2 #rect
Ce0 f53 @|StepIcon #fIcon
Ce0 f54 expr out #txt
Ce0 f54 2488 77 2488 140 #arcP
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
Ce0 f55 taskAndCaseSetupAction 'import ch.ivyteam.ivy.workflow.TaskUpdateDefinition;
ch.ivyteam.ivy.workflow.TaskUpdateDefinition taskUpdDef = new ch.ivyteam.ivy.workflow.TaskUpdateDefinition();
import ch.ivyteam.ivy.request.impl.DefaultCalendarProxy;
DefaultCalendarProxy calendarProxy = ivy.cal as DefaultCalendarProxy;
taskUpdDef.setPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
taskUpdDef.setExpiryActivator("Everybody");
taskUpdDef.setExpiryPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
engine.updateCurrentTask(taskUpdDef);
' #txt
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
Ce0 f55 2651 51 26 26 14 0 #rect
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
Ce0 f57 2646 140 36 24 20 -2 #rect
Ce0 f57 @|StepIcon #fIcon
Ce0 f58 expr out #txt
Ce0 f58 2664 77 2664 140 #arcP
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
Ce0 f31 expr out #txt
Ce0 f31 944 164 944 250 #arcP
Ce0 f25 expr out #txt
Ce0 f25 320 164 930 264 #arcP
Ce0 f25 1 320 264 #addKink
Ce0 f25 1 0.3893137469825231 0 0 #arcLabel
Ce0 f27 expr out #txt
Ce0 f27 488 164 930 264 #arcP
Ce0 f27 1 488 264 #addKink
Ce0 f27 1 0.3243395019937099 0 0 #arcLabel
Ce0 f32 expr out #txt
Ce0 f32 808 164 930 264 #arcP
Ce0 f32 1 808 264 #addKink
Ce0 f32 1 0.16558994443666053 0 0 #arcLabel
Ce0 f12 expr out #txt
Ce0 f12 1576 164 958 264 #arcP
Ce0 f12 1 1576 264 #addKink
Ce0 f12 1 0.26450091240089235 0 0 #arcLabel
Ce0 f4 expr out #txt
Ce0 f4 1728 164 958 264 #arcP
Ce0 f4 1 1728 264 #addKink
Ce0 f4 1 0.3377606765990146 0 0 #arcLabel
Ce0 f37 expr out #txt
Ce0 f37 1960 164 958 264 #arcP
Ce0 f37 1 1960 264 #addKink
Ce0 f37 1 0.39383478216831114 0 0 #arcLabel
Ce0 f17 expr out #txt
Ce0 f17 2112 164 958 264 #arcP
Ce0 f17 1 2112 264 #addKink
Ce0 f17 1 0.41979192209425265 0 0 #arcLabel
Ce0 f22 expr out #txt
Ce0 f22 2344 164 958 264 #arcP
Ce0 f22 1 2344 264 #addKink
Ce0 f22 1 0.441217750988184 0 0 #arcLabel
Ce0 f42 expr out #txt
Ce0 f42 2488 164 958 264 #arcP
Ce0 f42 1 2488 264 #addKink
Ce0 f42 1 0.4461560836759817 0 0 #arcLabel
Ce0 f44 expr out #txt
Ce0 f44 2664 164 958 264 #arcP
Ce0 f44 1 2664 264 #addKink
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
Ce0 f1 taskAndCaseSetupAction 'import ch.ivyteam.ivy.workflow.TaskUpdateDefinition;
ch.ivyteam.ivy.workflow.TaskUpdateDefinition taskUpdDef = new ch.ivyteam.ivy.workflow.TaskUpdateDefinition();
import ch.ivyteam.ivy.request.impl.DefaultCalendarProxy;
DefaultCalendarProxy calendarProxy = ivy.cal as DefaultCalendarProxy;
taskUpdDef.setPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
taskUpdDef.setExpiryActivator("Everybody");
taskUpdDef.setExpiryPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
engine.updateCurrentTask(taskUpdDef);
' #txt
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
Ce0 f1 643 51 26 26 14 0 #rect
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
Ce0 f6 638 140 36 24 20 -2 #rect
Ce0 f6 @|StepIcon #fIcon
Ce0 f9 expr out #txt
Ce0 f9 656 77 656 140 #arcP
Ce0 f13 expr out #txt
Ce0 f13 656 164 930 264 #arcP
Ce0 f13 1 656 264 #addKink
Ce0 f13 1 0.3122065258061619 0 0 #arcLabel
Ce0 f19 inParamDecl '<ch.ivy.ws.addon.service.CaseSearchCriteria caseSearchCriteria> param;' #txt
Ce0 f19 inParamTable 'out.caseSearchCriteria=param.caseSearchCriteria;
' #txt
Ce0 f19 outParamDecl '<List<ch.ivy.ws.addon.WSException> errors,List<ch.ivy.ws.addon.types.IvyCase> ivyCases> result;
' #txt
Ce0 f19 outParamTable 'result.errors=in.errors;
result.ivyCases=in.ivyCases;
' #txt
Ce0 f19 actionDecl 'ch.ivy.ws.addon.CaseServiceData out;
' #txt
Ce0 f19 callSignature findCasesByCriteria(ch.ivy.ws.addon.service.CaseSearchCriteria) #txt
Ce0 f19 useUserDefinedException false #txt
Ce0 f19 taskData TaskTriggered.PRI=2 #txt
Ce0 f19 taskAndCaseSetupAction 'import ch.ivyteam.ivy.workflow.TaskUpdateDefinition;
ch.ivyteam.ivy.workflow.TaskUpdateDefinition taskUpdDef = new ch.ivyteam.ivy.workflow.TaskUpdateDefinition();
import ch.ivyteam.ivy.request.impl.DefaultCalendarProxy;
DefaultCalendarProxy calendarProxy = ivy.cal as DefaultCalendarProxy;
taskUpdDef.setPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
taskUpdDef.setExpiryActivator("Everybody");
taskUpdDef.setExpiryPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
engine.updateCurrentTask(taskUpdDef);
' #txt
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
	CaseServiceResult result = WsServiceFactory.getCaseService().findCasesByCriteria(in.caseSearchCriteria);
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
Ce0 f46 taskAndCaseSetupAction 'import ch.ivyteam.ivy.workflow.TaskUpdateDefinition;
ch.ivyteam.ivy.workflow.TaskUpdateDefinition taskUpdDef = new ch.ivyteam.ivy.workflow.TaskUpdateDefinition();
import ch.ivyteam.ivy.request.impl.DefaultCalendarProxy;
DefaultCalendarProxy calendarProxy = ivy.cal as DefaultCalendarProxy;
taskUpdDef.setPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
taskUpdDef.setExpiryActivator("Everybody");
taskUpdDef.setExpiryPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
engine.updateCurrentTask(taskUpdDef);
' #txt
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
Ce0 f46 2851 51 26 26 14 0 #rect
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
Ce0 f51 2846 140 36 24 20 -2 #rect
Ce0 f51 @|StepIcon #fIcon
Ce0 f52 expr out #txt
Ce0 f52 2864 77 2864 140 #arcP
Ce0 f56 expr out #txt
Ce0 f56 2864 164 958 264 #arcP
Ce0 f56 1 2864 264 #addKink
Ce0 f56 1 0.46295323869949495 0 0 #arcLabel
Ce0 f65 inParamDecl '<ch.ivy.ws.addon.service.CaseSearchCriteria caseSearchCriteria,java.lang.String userName> param;' #txt
Ce0 f65 inParamTable 'out.caseSearchCriteria=param.caseSearchCriteria;
out.user=param.userName;
' #txt
Ce0 f65 outParamDecl '<List<ch.ivy.ws.addon.types.IvyCase> ivyCases,List<ch.ivy.ws.addon.WSException> error> result;
' #txt
Ce0 f65 outParamTable 'result.ivyCases=in.ivyCases;
result.error=in.errors;
' #txt
Ce0 f65 actionDecl 'ch.ivy.ws.addon.CaseServiceData out;
' #txt
Ce0 f65 callSignature findCasesWithDestroyPermissionByCriteria(ch.ivy.ws.addon.service.CaseSearchCriteria,String) #txt
Ce0 f65 useUserDefinedException false #txt
Ce0 f65 taskData TaskTriggered.PRI=2 #txt
Ce0 f65 taskAndCaseSetupAction 'import ch.ivyteam.ivy.workflow.TaskUpdateDefinition;
ch.ivyteam.ivy.workflow.TaskUpdateDefinition taskUpdDef = new ch.ivyteam.ivy.workflow.TaskUpdateDefinition();
import ch.ivyteam.ivy.request.impl.DefaultCalendarProxy;
DefaultCalendarProxy calendarProxy = ivy.cal as DefaultCalendarProxy;
taskUpdDef.setPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
taskUpdDef.setExpiryActivator("Everybody");
taskUpdDef.setExpiryPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
engine.updateCurrentTask(taskUpdDef);
' #txt
Ce0 f65 type ch.ivy.ws.addon.CaseServiceData #txt
Ce0 f65 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>findCasesWithPermissionByCriteria(CaseSearchCriteria,String)</name>
        <nameStyle>60,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ce0 f65 @C|.responsibility Everybody #txt
Ce0 f65 1139 51 26 26 -74 14 #rect
Ce0 f65 @|StartWSIcon #fIcon
Ce0 f66 actionDecl 'ch.ivy.ws.addon.CaseServiceData out;
' #txt
Ce0 f66 actionTable 'out=in;
' #txt
Ce0 f66 actionCode 'import ch.ivy.ws.addon.WSException;
import ch.ivy.ws.addon.WsServiceFactory;
import ch.ivy.ws.addon.bo.CaseServiceResult;

try{
	CaseServiceResult csResult = WsServiceFactory.getCaseService().findCasesWithDestroyPermissionByCriteria(in.caseSearchCriteria,in.user);
	in.ivyCases = csResult.getCases();
	in.errors = csResult.getErrors();
}catch(WSException e){
	in.errors.add(e);
}' #txt
Ce0 f66 type ch.ivy.ws.addon.CaseServiceData #txt
Ce0 f66 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>findCasesWithDestroyPermissionByCriteria</name>
        <nameStyle>40
</nameStyle>
    </language>
</elementInfo>
' #txt
Ce0 f66 1134 156 36 24 20 -2 #rect
Ce0 f66 @|StepIcon #fIcon
Ce0 f67 expr out #txt
Ce0 f67 1152 77 1152 156 #arcP
Ce0 f68 expr out #txt
Ce0 f68 1152 180 958 264 #arcP
Ce0 f68 1 1152 264 #addKink
Ce0 f68 1 0.22104833866100623 0 0 #arcLabel
>Proto Ce0 .webServiceName ch.ivy.ws.addon.CaseService #txt
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
    <swimlaneSize>1472</swimlaneSize>
    <swimlaneSize>384</swimlaneSize>
    <swimlaneSize>384</swimlaneSize>
    <swimlaneSize>784</swimlaneSize>
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
Ce0 f10 mainOut f11 tail #connect
Ce0 f11 head f8 mainIn #connect
Ce0 f15 mainOut f16 tail #connect
Ce0 f16 head f14 mainIn #connect
Ce0 f20 mainOut f21 tail #connect
Ce0 f21 head f18 mainIn #connect
Ce0 f24 mainOut f26 tail #connect
Ce0 f26 head f23 mainIn #connect
Ce0 f34 mainOut f36 tail #connect
Ce0 f36 head f33 mainIn #connect
Ce0 f38 mainOut f41 tail #connect
Ce0 f41 head f39 mainIn #connect
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
Ce0 f23 mainOut f31 tail #connect
Ce0 f31 head f29 in #connect
Ce0 f33 mainOut f25 tail #connect
Ce0 f25 head f29 in #connect
Ce0 f39 mainOut f27 tail #connect
Ce0 f27 head f29 in #connect
Ce0 f8 mainOut f32 tail #connect
Ce0 f32 head f29 in #connect
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
Ce0 f65 mainOut f67 tail #connect
Ce0 f67 head f66 mainIn #connect
Ce0 f66 mainOut f68 tail #connect
Ce0 f68 head f29 in #connect
