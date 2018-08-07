[Ivy]
[>Created: Tue May 30 16:47:40 ICT 2017]
1589822F106E69C9 3.20 #module
>Proto >Proto Collection #zClass
Ms0 ManageUsergroups Big #zClass
Ms0 B #cInfo
Ms0 #process
Ms0 @TextInP .resExport .resExport #zField
Ms0 @TextInP .type .type #zField
Ms0 @TextInP .processKind .processKind #zField
Ms0 @AnnotationInP-0n ai ai #zField
Ms0 @MessageFlowInP-0n messageIn messageIn #zField
Ms0 @MessageFlowOutP-0n messageOut messageOut #zField
Ms0 @TextInP .xml .xml #zField
Ms0 @TextInP .responsibility .responsibility #zField
Ms0 @StartRequest f0 '' #zField
Ms0 @EndTask f1 '' #zField
Ms0 @RichDialog f3 '' #zField
Ms0 @PushWFArc f4 '' #zField
Ms0 @PushWFArc f2 '' #zField
>Proto Ms0 Ms0 ManageUsergroups #zField
Ms0 f0 outLink ManageUsergroups.ivp #txt
Ms0 f0 type gawfs.ManageUsergroupsData #txt
Ms0 f0 inParamDecl '<> param;' #txt
Ms0 f0 actionDecl 'gawfs.ManageUsergroupsData out;
' #txt
Ms0 f0 guid 1589822F110464C3 #txt
Ms0 f0 requestEnabled true #txt
Ms0 f0 triggerEnabled false #txt
Ms0 f0 callSignature ManageUsergroups() #txt
Ms0 f0 persist false #txt
Ms0 f0 startName 'Manage Usergroups' #txt
Ms0 f0 taskData 'TaskTriggered.ROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.EXPRI=2
TaskTriggered.TYPE=0
TaskTriggered.PRI=2
TaskTriggered.EXROL=Everybody' #txt
Ms0 f0 caseData businessCase.attach=false #txt
Ms0 f0 showInStartList 1 #txt
Ms0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>ManageUsergroups.ivp</name>
        <nameStyle>20,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ms0 f0 @C|.responsibility Everybody #txt
Ms0 f0 81 49 30 30 -64 17 #rect
Ms0 f0 @|StartRequestIcon #fIcon
Ms0 f1 type gawfs.ManageUsergroupsData #txt
Ms0 f1 401 49 30 30 0 15 #rect
Ms0 f1 @|EndIcon #fIcon
Ms0 f3 targetWindow NEW:card: #txt
Ms0 f3 targetDisplay TOP #txt
Ms0 f3 richDialogId ch.ivy.gawfs.usergroupMgmt.GroupMgmt #txt
Ms0 f3 startMethod start() #txt
Ms0 f3 type gawfs.ManageUsergroupsData #txt
Ms0 f3 requestActionDecl '<> param;' #txt
Ms0 f3 responseActionDecl 'gawfs.ManageUsergroupsData out;
' #txt
Ms0 f3 responseMappingAction 'out=in;
' #txt
Ms0 f3 windowConfiguration '* ' #txt
Ms0 f3 isAsynch false #txt
Ms0 f3 isInnerRd false #txt
Ms0 f3 userContext '* ' #txt
Ms0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>UsergroupAdministration Dialog</name>
        <nameStyle>30,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ms0 f3 168 42 192 44 -88 -8 #rect
Ms0 f3 @|RichDialogIcon #fIcon
Ms0 f4 expr out #txt
Ms0 f4 111 64 168 64 #arcP
Ms0 f2 expr out #txt
Ms0 f2 360 64 401 64 #arcP
>Proto Ms0 .type gawfs.ManageUsergroupsData #txt
>Proto Ms0 .processKind NORMAL #txt
>Proto Ms0 0 0 32 24 18 0 #rect
>Proto Ms0 @|BIcon #fIcon
Ms0 f0 mainOut f4 tail #connect
Ms0 f4 head f3 mainIn #connect
Ms0 f3 mainOut f2 tail #connect
Ms0 f2 head f1 mainIn #connect
