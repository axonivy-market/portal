[Ivy]
[>Created: Tue May 05 16:22:11 ICT 2015]
147635456D1D6FFF 3.17 #module
>Proto >Proto Collection #zClass
er0 equipNewUser Big #zClass
er0 B #cInfo
er0 #process
Ct0 Component Big #zClass
Ct0 B #cInfo
Ct1 Component Big #zClass
Ct1 B #cInfo
er0 @TextInP .resExport .resExport #zField
er0 @TextInP .type .type #zField
er0 @TextInP .processKind .processKind #zField
er0 @AnnotationInP-0n ai ai #zField
er0 @MessageFlowInP-0n messageIn messageIn #zField
er0 @MessageFlowOutP-0n messageOut messageOut #zField
er0 @TextInP .xml .xml #zField
er0 @TextInP .responsibility .responsibility #zField
er0 @StartRequest f0 '' #zField
er0 @EndTask f3 '' #zField
er0 Ct0 1b0 '1 Sub' #zField
er0 Ct1 2b0 '2 Sub' #zField
er0 @PushWFArc f1 '' #zField
er0 @PushWFArc f6 '' #zField
er0 @GridStep f2 '' #zField
er0 @PushWFArc f4 '' #zField
er0 @PushWFArc f5 '' #zField
>Proto er0 er0 equipNewUser #zField
Ct0 @TextInP .resExport .resExport #zField
Ct0 @TextInP .type .type #zField
Ct0 @TextInP .processKind .processKind #zField
Ct0 @AnnotationInP-0n ai ai #zField
Ct0 @TextInP .xml .xml #zField
Ct0 @TextInP .responsibility .responsibility #zField
Ct0 @PushTrueWFInG-01 g0 '' #zField
Ct0 @PushTrueWFOutG-01 g1 '' #zField
Ct0 @RichDialog f3 '' #zField
Ct0 @TaskSwitchSimple f1 '' #zField
Ct0 @PushWFArc f4 '' #zField
Ct0 @TkArc f0 '' #zField
Ct0 @PushWFArc f2 '' #zField
>Proto Ct0 Ct0 Component #zField
Ct1 @TextInP .resExport .resExport #zField
Ct1 @TextInP .type .type #zField
Ct1 @TextInP .processKind .processKind #zField
Ct1 @AnnotationInP-0n ai ai #zField
Ct1 @TextInP .xml .xml #zField
Ct1 @TextInP .responsibility .responsibility #zField
Ct1 @PushTrueWFInG-01 g0 '' #zField
Ct1 @PushTrueWFOutG-01 g1 '' #zField
Ct1 @RichDialog f3 '' #zField
Ct1 @TaskSwitchSimple f1 '' #zField
Ct1 @PushWFArc f4 '' #zField
Ct1 @TkArc f0 '' #zField
Ct1 @PushWFArc f2 '' #zField
>Proto Ct1 Ct1 Component #zField
er0 f0 outLink requestUserEquipment.ivp #txt
er0 f0 type test002ITInfrastructure.equipNewUser #txt
er0 f0 inParamDecl '<> param;' #txt
er0 f0 actionDecl 'test002ITInfrastructure.equipNewUser out;
' #txt
er0 f0 guid 147678A221034B8B #txt
er0 f0 requestEnabled true #txt
er0 f0 triggerEnabled false #txt
er0 f0 callSignature requestUserEquipment() #txt
er0 f0 persist false #txt
er0 f0 startName 'Request User Equipment' #txt
er0 f0 startDescription 'Manager request for user''s hardware equipment and configuration' #txt
er0 f0 taskData '#
#Fri Apr 24 15:18:27 ICT 2015
TaskTriggered.ROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.EXPRI=2
TaskTriggered.TYPE=0
TaskTriggered.PRI=2
TaskTriggered.EXROL=Manager
' #txt
er0 f0 caseData '#
#Fri Apr 24 15:18:27 ICT 2015
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
processCategory.code=requestUserEquipment
processCategory.name=
subType.code=
subType.name=
type.code=
type.name=
' #txt
er0 f0 showInStartList 1 #txt
er0 f0 taskAndCaseSetupAction 'ivy.case.setProcessCategory(engine.expandMacros("requestUserEquipment"), engine.expandMacros(""));
import ch.ivyteam.ivy.workflow.TaskUpdateDefinition;
ch.ivyteam.ivy.workflow.TaskUpdateDefinition taskUpdDef = new ch.ivyteam.ivy.workflow.TaskUpdateDefinition();
import ch.ivyteam.ivy.request.impl.DefaultCalendarProxy;
DefaultCalendarProxy calendarProxy = ivy.cal as DefaultCalendarProxy;
taskUpdDef.setPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
taskUpdDef.setExpiryActivator("Manager");
taskUpdDef.setExpiryPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
engine.updateCurrentTask(taskUpdDef);
' #txt
er0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>requestUserEquipment</name>
        <nameStyle>20,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
er0 f0 @C|.responsibility Manager #txt
er0 f0 89 73 30 30 16 0 #rect
er0 f0 @|StartRequestIcon #fIcon
er0 f0 -1|-1|-9671572 #nodeStyle
er0 f3 type test002ITInfrastructure.equipNewUser #txt
er0 f3 409 449 30 30 16 0 #rect
er0 f3 @|EndIcon #fIcon
er0 f3 -1|-1|-9671572 #nodeStyle
er0 1b0 .resExport export #txt
er0 1b0 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language lang="en">
        <name>OrderEquipment</name>
        <nameStyle>14,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
er0 1b0 27 224 154 32 -49 -8 #rect
er0 1b0 @|BIcon #fIcon
er0 1b0 g0 0 -16 #fFoot
er0 1b0 g1 77 8 #fFoot
er0 2b0 .resExport export #txt
er0 2b0 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language lang="en">
        <name>ProvideEquipment</name>
        <nameStyle>16,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
er0 2b0 339 312 170 32 -54 -8 #rect
er0 2b0 @|BIcon #fIcon
er0 2b0 g0 -3 -16 #fFoot
er0 2b0 g1 0 16 #fFoot
er0 f1 181 248 421 312 #arcP
er0 f1 1 421 248 #addKink
er0 f1 0 0.725 0 0 #arcLabel
er0 f6 424 344 424 449 #arcP
er0 f6 0 0.5219794477775865 0 0 #arcLabel
er0 f2 actionDecl 'test002ITInfrastructure.equipNewUser out;
' #txt
er0 f2 actionTable 'out=in;
' #txt
er0 f2 actionCode 'import ch.ivy.addon.portalkit.util.CaseUtils;
CaseUtils.setCaseDetailsProcess(ivy.case,ivy.html.startref("Start Processes/InternalPortalHome/caseDetails.ivp"));' #txt
er0 f2 type test002ITInfrastructure.equipNewUser #txt
er0 f2 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>set case details link</name>
        <nameStyle>21,7
</nameStyle>
    </language>
</elementInfo>
' #txt
er0 f2 86 140 36 24 20 -2 #rect
er0 f2 @|StepIcon #fIcon
er0 f4 expr out #txt
er0 f4 104 103 104 140 #arcP
er0 f5 expr out #txt
er0 f5 104 164 104 224 #arcP
>Proto er0 .type test002ITInfrastructure.equipNewUser #txt
>Proto er0 .processKind NORMAL #txt
>Proto er0 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <swimlaneLabel>Manager</swimlaneLabel>
        <swimlaneLabel>IT Administrator</swimlaneLabel>
    </language>
    <swimlaneOrientation>true</swimlaneOrientation>
    <swimlaneSize>256</swimlaneSize>
    <swimlaneSize>304</swimlaneSize>
    <swimlaneColor>-26215</swimlaneColor>
    <swimlaneColor>-10027162</swimlaneColor>
</elementInfo>
' #txt
>Proto er0 0 0 32 24 18 0 #rect
>Proto er0 @|BIcon #fIcon
Ct0 g0 245 85 6 6 14 -12 #rect
Ct0 g0 @|MIGIcon #fIcon
Ct0 g1 601 333 6 6 10 16 #rect
Ct0 g1 @|MOGIcon #fIcon
Ct0 f3 targetWindow NEW:card: #txt
Ct0 f3 targetDisplay TOP #txt
Ct0 f3 richDialogId test002ITInfrastructure.SubmitITOrderForm #txt
Ct0 f3 startMethod start() #txt
Ct0 f3 type test002ITInfrastructure.equipNewUser #txt
Ct0 f3 requestActionDecl '<> param;' #txt
Ct0 f3 responseActionDecl 'test002ITInfrastructure.equipNewUser out;
' #txt
Ct0 f3 responseMappingAction 'out=in;
out.Approved=result.Approved;
out.Durchwahl=result.Durchwahl;
out.Equipment=result.Equipment;
out.Manager=result.Manager;
out.Mitarbeiter=result.Mitarbeiter;
' #txt
Ct0 f3 windowConfiguration '* ' #txt
Ct0 f3 isAsynch false #txt
Ct0 f3 isInnerRd false #txt
Ct0 f3 userContext '* ' #txt
Ct0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Submit IT Order Form</name>
        <nameStyle>20,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ct0 f3 184 250 128 44 -59 -8 #rect
Ct0 f3 @|RichDialogIcon #fIcon
Ct0 f3 -1|-1|-9671572 #nodeStyle
Ct0 f1 actionDecl 'test002ITInfrastructure.equipNewUser out;
' #txt
Ct0 f1 actionTable 'out=in1;
' #txt
Ct0 f1 outTypes "test002ITInfrastructure.equipNewUser" #txt
Ct0 f1 outLinks "TaskA.ivp" #txt
Ct0 f1 caseData '#
#Thu Apr 23 15:23:52 ICT 2015
businessCalendarName=
businessCreator.user=
businessMilestone.timestamp=
businessObject.code=
businessObject.docDb.code=
businessObject.folder.id=
businessObject.name=
businessPriority=
businessStart.timestamp=
case.description=Status Requested for User
case.name=equipNewUser Status Requested
correspondent.id=
mainContact.docDb.code=
mainContact.folder.id=
mainContact.id=
mainContact.name=
mainContact.type=
process.code=
process.name=
processCategory.code=Cat1
processCategory.name=Equipment and Order Process
subType.code=
subType.name=
type.code=
type.name=
' #txt
Ct0 f1 taskData '#
#Thu Apr 23 15:23:52 ICT 2015
TaskA.DEL=new Duration(2)
TaskA.DESC=Define IT equipment\nFill in Form 
TaskA.EXP=new Duration(10)
TaskA.EXPRI=2
TaskA.EXROL=Manager
TaskA.EXTYPE=0
TaskA.KINDC=ABC123
TaskA.KINDN=Peter Jauernig
TaskA.NAM=Enter IT Equipment Order Request
TaskA.PRI=2
TaskA.ROL=Manager
TaskA.SKIP_TASK_LIST=false
TaskA.TYPE=0
TaskA.customFields.decimal.3=0699
TaskA.customFields.decimal.4=12345
TaskA.customFields.varchar.1="PC"
TaskA.customFields.varchar.2="Maus"
TaskA.customFields.varchar.3="Tastatur"
TaskA.customFields.varchar.4="Headset"
TaskA.customFields.varchar.5="Mobile"
' #txt
Ct0 f1 taskAction 'ivy.case.setName(engine.expandMacros("equipNewUser Status Requested"));
ivy.case.setDescription(engine.expandMacros("Status Requested for User"));
ivy.case.setProcessCategory(engine.expandMacros("Cat1"), engine.expandMacros("Equipment and Order Process"));
import ch.ivyteam.ivy.workflow.TaskDefinition;
List<TaskDefinition> taskDefinitions;
TaskDefinition taskDef;import ch.ivyteam.ivy.request.impl.DefaultCalendarProxy;
DefaultCalendarProxy calendarProxy = ivy.cal as DefaultCalendarProxy;
taskDef = new TaskDefinition();
taskDef.setStartRequestPath("TaskA.ivp");
taskDef.setName(engine.expandMacros("Enter IT Equipment Order Request"));
taskDef.setDescription(engine.expandMacros("Define IT equipment
Fill in Form"));
taskDef.setKindCode(engine.expandMacros("ABC123"));
taskDef.setKindName(engine.expandMacros("Peter Jauernig"));
taskDef.setAutoStartTask(false);
taskDef.setActivator("Manager");
taskDef.setDelayPeriod(1000 * (new Duration(2)).toNumber());
taskDef.setPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
taskDef.setExpiryPeriod(1000 * (new Duration(10)).toNumber());
taskDef.setExpiryActivator("Manager");
taskDef.setExpiryPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
taskDef.setCustomVarCharField1("PC");
taskDef.setCustomVarCharField2("Maus");
taskDef.setCustomVarCharField3("Tastatur");
taskDef.setCustomDecimalField3(0699);
taskDef.setCustomVarCharField4("Headset");
taskDef.setCustomDecimalField4(12345);
taskDef.setCustomVarCharField5("Mobile");
taskDefinitions.add(taskDef);
' #txt
Ct0 f1 type test002ITInfrastructure.equipNewUser #txt
Ct0 f1 template "traffic-control.jsp" #txt
Ct0 f1 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>EnterITEquipmentOrderRequest</name>
        <nameStyle>28,7
</nameStyle>
        <desc>Manager collects user requirements for IT Equipement and gives approve. 
Fill in IT equipment application form </desc>
    </language>
</elementInfo>
' #txt
Ct0 f1 233 153 30 30 14 0 #rect
Ct0 f1 @|TaskSwitchSimpleIcon #fIcon
Ct0 f1 -1|-1|-9671572 #nodeStyle
Ct0 f4 expr data #txt
Ct0 f4 outCond ivp=="TaskA.ivp" #txt
Ct0 f4 248 183 248 250 #arcP
Ct0 f0 type test002ITInfrastructure.equipNewUser #txt
Ct0 f0 var in1 #txt
Ct0 f0 template traffic-control.jsp #txt
Ct0 f0 248 91 248 153 #arcP
Ct0 f2 expr out #txt
Ct0 f2 248 294 601 336 #arcP
Ct0 f2 1 248 336 #addKink
Ct0 f2 1 0.38319860958797275 0 0 #arcLabel
>Proto Ct0 0 0 32 24 18 0 #rect
>Proto Ct0 @|BIcon #fIcon
Ct1 g0 -6 157 6 6 2 -39 #rect
Ct1 g0 @|MIGIcon #fIcon
Ct1 g1 245 501 6 6 10 16 #rect
Ct1 g1 @|MOGIcon #fIcon
Ct1 f3 targetWindow NEW:card: #txt
Ct1 f3 targetDisplay TOP #txt
Ct1 f3 richDialogId test002ITInfrastructure.DeliverandConfigureEquipment #txt
Ct1 f3 startMethod start(String,String,String,Number,Boolean) #txt
Ct1 f3 type test002ITInfrastructure.equipNewUser #txt
Ct1 f3 requestActionDecl '<String Mitarbeiter, String Manager, String Equipment, Number Durchwahl, Boolean Approved> param;' #txt
Ct1 f3 requestMappingAction 'param.Mitarbeiter=in.Mitarbeiter;
param.Manager=in.Manager;
param.Equipment=in.Equipment;
param.Durchwahl=in.Durchwahl;
param.Approved=in.Approved;
' #txt
Ct1 f3 responseActionDecl 'test002ITInfrastructure.equipNewUser out;
' #txt
Ct1 f3 responseMappingAction 'out=in;
out.Delivered=result.Delivered;
out.Durchwahl=result.Durchwahl;
out.Equipment=result.Equipment;
out.Lieferant=result.Lieferant;
out.Manager=result.Manager;
out.Mitarbeiter=result.Mitarbeiter;
' #txt
Ct1 f3 windowConfiguration '* ' #txt
Ct1 f3 isAsynch false #txt
Ct1 f3 isInnerRd false #txt
Ct1 f3 userContext '* ' #txt
Ct1 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>DeliverEquipment</name>
        <nameStyle>16,7
</nameStyle>
        <desc>IT Administration fulfills order, delivers and configures equipment. </desc>
    </language>
</elementInfo>
' #txt
Ct1 f3 264 314 112 44 -49 -8 #rect
Ct1 f3 @|RichDialogIcon #fIcon
Ct1 f3 -1|-1|-9671572 #nodeStyle
Ct1 f1 actionDecl 'test002ITInfrastructure.equipNewUser out;
' #txt
Ct1 f1 actionTable 'out=in1;
' #txt
Ct1 f1 outTypes "test002ITInfrastructure.equipNewUser" #txt
Ct1 f1 outLinks "TaskA.ivp" #txt
Ct1 f1 caseData '#
#Thu Apr 23 15:28:20 ICT 2015
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
Ct1 f1 taskData '#
#Thu Apr 23 15:28:20 ICT 2015
TaskA.DEL=new Duration(20)
TaskA.DESC=IT Administrator buys necessary equipment and initiates installation 
TaskA.EXP=new Duration(30)
TaskA.EXPRI=2
TaskA.EXROL=Purchasing
TaskA.EXTYPE=0
TaskA.NAM=Fulfill Order Request
TaskA.PRI=2
TaskA.ROL=Purchasing
TaskA.SKIP_TASK_LIST=false
TaskA.TYPE=0
TaskA.customFields.decimal.1=in1.Durchwahl
TaskA.customFields.varchar.1=in1.Equipment
TaskA.customFields.varchar.2=in1.Mitarbeiter
' #txt
Ct1 f1 taskAction 'import ch.ivyteam.ivy.workflow.TaskDefinition;
List<TaskDefinition> taskDefinitions;
TaskDefinition taskDef;import ch.ivyteam.ivy.request.impl.DefaultCalendarProxy;
DefaultCalendarProxy calendarProxy = ivy.cal as DefaultCalendarProxy;
taskDef = new TaskDefinition();
taskDef.setStartRequestPath("TaskA.ivp");
taskDef.setName(engine.expandMacros("Fulfill Order Request"));
taskDef.setDescription(engine.expandMacros("IT Administrator buys necessary equipment and initiates installation"));
taskDef.setAutoStartTask(false);
taskDef.setActivator("Purchasing");
taskDef.setDelayPeriod(1000 * (new Duration(20)).toNumber());
taskDef.setPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
taskDef.setExpiryPeriod(1000 * (new Duration(30)).toNumber());
taskDef.setExpiryActivator("Purchasing");
taskDef.setExpiryPriority(ch.ivyteam.ivy.workflow.WorkflowPriority.valueOf(2));
taskDef.setCustomVarCharField1(in1.Equipment);
taskDef.setCustomDecimalField1(in1.Durchwahl);
taskDef.setCustomVarCharField2(in1.Mitarbeiter);
taskDefinitions.add(taskDef);
' #txt
Ct1 f1 type test002ITInfrastructure.equipNewUser #txt
Ct1 f1 template "traffic-control.jsp" #txt
Ct1 f1 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>FulfillOrderRequest</name>
        <nameStyle>19,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ct1 f1 305 217 30 30 17 -6 #rect
Ct1 f1 @|TaskSwitchSimpleIcon #fIcon
Ct1 f1 -1|-1|-9671572 #nodeStyle
Ct1 f4 expr data #txt
Ct1 f4 outCond ivp=="TaskA.ivp" #txt
Ct1 f4 320 247 320 314 #arcP
Ct1 f0 type test002ITInfrastructure.equipNewUser #txt
Ct1 f0 var in1 #txt
Ct1 f0 template traffic-control.jsp #txt
Ct1 f0 0 160 320 217 #arcP
Ct1 f0 1 320 160 #addKink
Ct1 f0 0 0.5966121756471946 0 0 #arcLabel
Ct1 f2 expr out #txt
Ct1 f2 264 336 248 501 #arcP
Ct1 f2 1 248 336 #addKink
Ct1 f2 1 0.2977891606853738 0 0 #arcLabel
>Proto Ct1 0 0 32 24 18 0 #rect
>Proto Ct1 @|BIcon #fIcon
er0 1b0 g1 f1 tail #connect
er0 f1 head 2b0 g0 #connect
er0 2b0 g1 f6 tail #connect
er0 f6 head f3 mainIn #connect
er0 f0 mainOut f4 tail #connect
er0 f4 head f2 mainIn #connect
er0 f2 mainOut f5 tail #connect
er0 f5 head 1b0 g0 #connect
Ct0 f1 out f4 tail #connect
Ct0 f4 head f3 mainIn #connect
Ct0 g0 m f0 tail #connect
Ct0 f0 head f1 in #connect
Ct0 f3 mainOut f2 tail #connect
Ct0 f2 head g1 m #connect
Ct0 0 0 600 500 0 #ivRect
Ct1 f1 out f4 tail #connect
Ct1 f4 head f3 mainIn #connect
Ct1 g0 m f0 tail #connect
Ct1 f0 head f1 in #connect
Ct1 f3 mainOut f2 tail #connect
Ct1 f2 head g1 m #connect
Ct1 0 0 600 500 0 #ivRect
