[Ivy]
[>Created: Thu Oct 08 10:31:43 ICT 2015]
150457FDD8FE8AD6 3.17 #module
>Proto >Proto Collection #zClass
In0 InternalCustomApplications Big #zClass
In0 B #cInfo
In0 #process
In0 @TextInP .resExport .resExport #zField
In0 @TextInP .type .type #zField
In0 @TextInP .processKind .processKind #zField
In0 @AnnotationInP-0n ai ai #zField
In0 @TextInP .xml .xml #zField
In0 @TextInP .responsibility .responsibility #zField
In0 @RichDialog f8 '' #zField
In0 @EndTask f6 '' #zField
In0 @StartRequest f5 '' #zField
In0 @PushWFArc f7 '' #zField
In0 @PushWFArc f0 '' #zField
>Proto In0 In0 InternalCustomApplications #zField
In0 f8 targetWindow NEW:card: #txt
In0 f8 targetDisplay TOP #txt
In0 f8 richDialogId ch.ivy.addon.portal.generic.CustomApplication #txt
In0 f8 startMethod start(String,String) #txt
In0 f8 type ch.ivy.addon.portal.generic.CustomApplications #txt
In0 f8 requestActionDecl '<String url, String appName> param;' #txt
In0 f8 requestMappingAction 'param.url=in.url;
param.appName=in.appName;
' #txt
In0 f8 responseActionDecl 'ch.ivy.addon.portal.generic.CustomApplications out;
' #txt
In0 f8 responseMappingAction 'out=in;
' #txt
In0 f8 windowConfiguration '* ' #txt
In0 f8 isAsynch false #txt
In0 f8 isInnerRd false #txt
In0 f8 userContext '* ' #txt
In0 f8 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>CustomApplication</name>
        <nameStyle>17,7
</nameStyle>
    </language>
</elementInfo>
' #txt
In0 f8 110 156 36 25 20 -2 #rect
In0 f8 @|RichDialogIcon #fIcon
In0 f6 type ch.ivy.addon.portal.generic.CustomApplications #txt
In0 f6 115 283 26 26 14 0 #rect
In0 f6 @|EndIcon #fIcon
In0 f5 outLink InternalCustomApplication.ivp #txt
In0 f5 type ch.ivy.addon.portal.generic.CustomApplications #txt
In0 f5 inParamDecl '<java.lang.String appName,java.lang.String url> param;' #txt
In0 f5 inParamTable 'out.appName=param.appName;
out.url=param.url;
' #txt
In0 f5 actionDecl 'ch.ivy.addon.portal.generic.CustomApplications out;
' #txt
In0 f5 guid 14DF4DB4467D783B #txt
In0 f5 requestEnabled true #txt
In0 f5 triggerEnabled false #txt
In0 f5 callSignature InternalCustomApplication(String,String) #txt
In0 f5 persist false #txt
In0 f5 taskData '#
#Tue Jun 30 14:17:35 ICT 2015
TaskTriggered.ROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.EXPRI=2
TaskTriggered.TYPE=0
TaskTriggered.PRI=2
TaskTriggered.EXROL=Everybody
' #txt
In0 f5 caseData '#
#Tue Jun 30 14:17:35 ICT 2015
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
In0 f5 showInStartList 0 #txt
In0 f5 taskAndCaseSetupAction 'import ch.ivyteam.ivy.workflow.TaskUpdateDefinition;
ch.ivyteam.ivy.workflow.TaskUpdateDefinition taskUpdDef = new ch.ivyteam.ivy.workflow.TaskUpdateDefinition();
import ch.ivyteam.ivy.request.impl.DefaultCalendarProxy;
DefaultCalendarProxy calendarProxy = ivy.cal as DefaultCalendarProxy;
taskUpdDef.setPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
taskUpdDef.setExpiryActivator("Everybody");
taskUpdDef.setExpiryPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
engine.updateCurrentTask(taskUpdDef);
' #txt
In0 f5 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>InternalCustomApplication.ivp</name>
        <nameStyle>29,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
In0 f5 @C|.responsibility Everybody #txt
In0 f5 115 27 26 26 14 0 #rect
In0 f5 @|StartRequestIcon #fIcon
In0 f7 expr out #txt
In0 f7 128 180 128 283 #arcP
In0 f0 expr out #txt
In0 f0 128 53 128 155 #arcP
>Proto In0 .type ch.ivy.addon.portal.generic.CustomApplications #txt
>Proto In0 .processKind NORMAL #txt
>Proto In0 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language/>
</elementInfo>
' #txt
>Proto In0 0 0 32 24 18 0 #rect
>Proto In0 @|BIcon #fIcon
In0 f8 mainOut f7 tail #connect
In0 f7 head f6 mainIn #connect
In0 f5 mainOut f0 tail #connect
In0 f0 head f8 mainIn #connect
