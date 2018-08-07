[Ivy]
[>Created: Fri Apr 24 16:58:40 ICT 2015]
14BD99325F986F67 3.17 #module
>Proto >Proto Collection #zClass
Ss0 Settings Big #zClass
Ss0 B #cInfo
Ss0 #process
Ss0 @TextInP .resExport .resExport #zField
Ss0 @TextInP .type .type #zField
Ss0 @TextInP .processKind .processKind #zField
Ss0 @AnnotationInP-0n ai ai #zField
Ss0 @TextInP .xml .xml #zField
Ss0 @TextInP .responsibility .responsibility #zField
Ss0 @StartRequest f0 '' #zField
Ss0 @EndTask f1 '' #zField
Ss0 @RichDialog f3 '' #zField
Ss0 @PushWFArc f4 '' #zField
Ss0 @PushWFArc f2 '' #zField
Ss0 @StartRequest f5 '' #zField
Ss0 @EndTask f6 '' #zField
Ss0 @RichDialog f7 '' #zField
Ss0 @PushWFArc f8 '' #zField
Ss0 @PushWFArc f9 '' #zField
Ss0 @RichDialog f10 '' #zField
Ss0 @EndTask f11 '' #zField
Ss0 @StartRequest f12 '' #zField
Ss0 @PushWFArc f13 '' #zField
Ss0 @PushWFArc f14 '' #zField
>Proto Ss0 Ss0 Settings #zField
Ss0 f0 outLink EmailSetting.ivp #txt
Ss0 f0 type portalKit_test.Data #txt
Ss0 f0 inParamDecl '<> param;' #txt
Ss0 f0 actionDecl 'portalKit_test.Data out;
' #txt
Ss0 f0 guid 14BD9934A90CA048 #txt
Ss0 f0 requestEnabled true #txt
Ss0 f0 triggerEnabled false #txt
Ss0 f0 callSignature EmailSetting() #txt
Ss0 f0 persist false #txt
Ss0 f0 startName 'Internal Email Setting' #txt
Ss0 f0 taskData '#
#Fri Apr 24 16:52:54 ICT 2015
TaskTriggered.ROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.EXPRI=2
TaskTriggered.TYPE=0
TaskTriggered.PRI=2
TaskTriggered.EXROL=Everybody
' #txt
Ss0 f0 caseData '#
#Fri Apr 24 16:52:54 ICT 2015
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
Ss0 f0 showInStartList 1 #txt
Ss0 f0 taskAndCaseSetupAction 'import ch.ivyteam.ivy.workflow.TaskUpdateDefinition;
ch.ivyteam.ivy.workflow.TaskUpdateDefinition taskUpdDef = new ch.ivyteam.ivy.workflow.TaskUpdateDefinition();
import ch.ivyteam.ivy.request.impl.DefaultCalendarProxy;
DefaultCalendarProxy calendarProxy = ivy.cal as DefaultCalendarProxy;
taskUpdDef.setPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
taskUpdDef.setExpiryActivator("Everybody");
taskUpdDef.setExpiryPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
engine.updateCurrentTask(taskUpdDef);
' #txt
Ss0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>EmailSetting.ivp</name>
        <nameStyle>16,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ss0 f0 @C|.responsibility Everybody #txt
Ss0 f0 51 43 26 26 14 0 #rect
Ss0 f0 @|StartRequestIcon #fIcon
Ss0 f1 type portalKit_test.Data #txt
Ss0 f1 51 267 26 26 14 0 #rect
Ss0 f1 @|EndIcon #fIcon
Ss0 f3 targetWindow NEW:card: #txt
Ss0 f3 targetDisplay TOP #txt
Ss0 f3 richDialogId ch.ivy.addon.portalkit.showroom.multiapp.setting.EmailSettingHome #txt
Ss0 f3 startMethod start() #txt
Ss0 f3 type portalKit_test.Data #txt
Ss0 f3 requestActionDecl '<> param;' #txt
Ss0 f3 responseActionDecl 'portalKit_test.Data out;
' #txt
Ss0 f3 responseMappingAction 'out=in;
' #txt
Ss0 f3 windowConfiguration '* ' #txt
Ss0 f3 isAsynch false #txt
Ss0 f3 isInnerRd false #txt
Ss0 f3 userContext '* ' #txt
Ss0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>show dialog</name>
        <nameStyle>11,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ss0 f3 46 148 36 24 20 -2 #rect
Ss0 f3 @|RichDialogIcon #fIcon
Ss0 f4 expr out #txt
Ss0 f4 64 69 64 148 #arcP
Ss0 f2 expr out #txt
Ss0 f2 64 172 64 267 #arcP
Ss0 f5 outLink AbsenceSetting.ivp #txt
Ss0 f5 type portalKit_test.Data #txt
Ss0 f5 inParamDecl '<> param;' #txt
Ss0 f5 actionDecl 'portalKit_test.Data out;
' #txt
Ss0 f5 guid 14BFCD9DE13E4DE5 #txt
Ss0 f5 requestEnabled true #txt
Ss0 f5 triggerEnabled false #txt
Ss0 f5 callSignature AbsenceSetting() #txt
Ss0 f5 persist false #txt
Ss0 f5 startName 'Internal Abence Setting' #txt
Ss0 f5 taskData '#
#Fri Apr 24 16:53:10 ICT 2015
TaskTriggered.ROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.EXPRI=2
TaskTriggered.TYPE=0
TaskTriggered.PRI=2
TaskTriggered.EXROL=Everybody
' #txt
Ss0 f5 caseData '#
#Fri Apr 24 16:53:10 ICT 2015
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
Ss0 f5 showInStartList 1 #txt
Ss0 f5 taskAndCaseSetupAction 'import ch.ivyteam.ivy.workflow.TaskUpdateDefinition;
ch.ivyteam.ivy.workflow.TaskUpdateDefinition taskUpdDef = new ch.ivyteam.ivy.workflow.TaskUpdateDefinition();
import ch.ivyteam.ivy.request.impl.DefaultCalendarProxy;
DefaultCalendarProxy calendarProxy = ivy.cal as DefaultCalendarProxy;
taskUpdDef.setPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
taskUpdDef.setExpiryActivator("Everybody");
taskUpdDef.setExpiryPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
engine.updateCurrentTask(taskUpdDef);
' #txt
Ss0 f5 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>AbsenceSetting.ivp</name>
        <nameStyle>18,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ss0 f5 @C|.responsibility Everybody #txt
Ss0 f5 211 43 26 26 14 0 #rect
Ss0 f5 @|StartRequestIcon #fIcon
Ss0 f6 type portalKit_test.Data #txt
Ss0 f6 211 267 26 26 14 0 #rect
Ss0 f6 @|EndIcon #fIcon
Ss0 f7 targetWindow NEW:card: #txt
Ss0 f7 targetDisplay TOP #txt
Ss0 f7 richDialogId ch.ivy.addon.portalkit.showroom.multiapp.setting.AbsenceAndSubstituteHome #txt
Ss0 f7 startMethod start() #txt
Ss0 f7 type portalKit_test.Data #txt
Ss0 f7 requestActionDecl '<> param;' #txt
Ss0 f7 responseActionDecl 'portalKit_test.Data out;
' #txt
Ss0 f7 responseMappingAction 'out=in;
' #txt
Ss0 f7 windowConfiguration '* ' #txt
Ss0 f7 isAsynch false #txt
Ss0 f7 isInnerRd false #txt
Ss0 f7 userContext '* ' #txt
Ss0 f7 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>show dialog</name>
        <nameStyle>11,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ss0 f7 206 148 36 24 20 -2 #rect
Ss0 f7 @|RichDialogIcon #fIcon
Ss0 f8 expr out #txt
Ss0 f8 224 69 224 148 #arcP
Ss0 f9 expr out #txt
Ss0 f9 224 172 224 267 #arcP
Ss0 f10 targetWindow NEW:card: #txt
Ss0 f10 targetDisplay TOP #txt
Ss0 f10 richDialogId ch.ivy.addon.portalkit.showroom.multiapp.setting.CentralLanguageHome #txt
Ss0 f10 startMethod start() #txt
Ss0 f10 type portalKit_test.Data #txt
Ss0 f10 requestActionDecl '<> param;' #txt
Ss0 f10 responseActionDecl 'portalKit_test.Data out;
' #txt
Ss0 f10 responseMappingAction 'out=in;
' #txt
Ss0 f10 windowConfiguration '* ' #txt
Ss0 f10 isAsynch false #txt
Ss0 f10 isInnerRd false #txt
Ss0 f10 userContext '* ' #txt
Ss0 f10 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>show dialog</name>
        <nameStyle>11,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ss0 f10 390 148 36 24 20 -2 #rect
Ss0 f10 @|RichDialogIcon #fIcon
Ss0 f11 type portalKit_test.Data #txt
Ss0 f11 395 275 26 26 14 0 #rect
Ss0 f11 @|EndIcon #fIcon
Ss0 f12 outLink LanguageSetting.ivp #txt
Ss0 f12 type portalKit_test.Data #txt
Ss0 f12 inParamDecl '<> param;' #txt
Ss0 f12 actionDecl 'portalKit_test.Data out;
' #txt
Ss0 f12 guid 14C01F9734543AB7 #txt
Ss0 f12 requestEnabled true #txt
Ss0 f12 triggerEnabled false #txt
Ss0 f12 callSignature LanguageSetting() #txt
Ss0 f12 persist false #txt
Ss0 f12 startName 'Internal Language Setting' #txt
Ss0 f12 taskData '#
#Fri Apr 24 16:53:23 ICT 2015
TaskTriggered.ROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.EXPRI=2
TaskTriggered.TYPE=0
TaskTriggered.PRI=2
TaskTriggered.EXROL=Everybody
' #txt
Ss0 f12 caseData '#
#Fri Apr 24 16:53:23 ICT 2015
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
Ss0 f12 showInStartList 1 #txt
Ss0 f12 taskAndCaseSetupAction 'import ch.ivyteam.ivy.workflow.TaskUpdateDefinition;
ch.ivyteam.ivy.workflow.TaskUpdateDefinition taskUpdDef = new ch.ivyteam.ivy.workflow.TaskUpdateDefinition();
import ch.ivyteam.ivy.request.impl.DefaultCalendarProxy;
DefaultCalendarProxy calendarProxy = ivy.cal as DefaultCalendarProxy;
taskUpdDef.setPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
taskUpdDef.setExpiryActivator("Everybody");
taskUpdDef.setExpiryPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
engine.updateCurrentTask(taskUpdDef);
' #txt
Ss0 f12 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>LanguageSetting.ivp</name>
        <nameStyle>19,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ss0 f12 @C|.responsibility Everybody #txt
Ss0 f12 395 51 26 26 13 -8 #rect
Ss0 f12 @|StartRequestIcon #fIcon
Ss0 f13 expr out #txt
Ss0 f13 408 77 408 148 #arcP
Ss0 f14 expr out #txt
Ss0 f14 408 172 408 275 #arcP
>Proto Ss0 .type portalKit_test.Data #txt
>Proto Ss0 .processKind NORMAL #txt
>Proto Ss0 0 0 32 24 18 0 #rect
>Proto Ss0 @|BIcon #fIcon
Ss0 f0 mainOut f4 tail #connect
Ss0 f4 head f3 mainIn #connect
Ss0 f3 mainOut f2 tail #connect
Ss0 f2 head f1 mainIn #connect
Ss0 f5 mainOut f8 tail #connect
Ss0 f8 head f7 mainIn #connect
Ss0 f7 mainOut f9 tail #connect
Ss0 f9 head f6 mainIn #connect
Ss0 f12 mainOut f13 tail #connect
Ss0 f13 head f10 mainIn #connect
Ss0 f10 mainOut f14 tail #connect
Ss0 f14 head f11 mainIn #connect
