[Ivy]
176A77E608A844A8 9.3.0 #module
>Proto >Proto Collection #zClass
Cd0 RestorePortalDashboardToDefault Big #zClass
Cd0 B #cInfo
Cd0 #process
Cd0 @TextInP .type .type #zField
Cd0 @TextInP .processKind .processKind #zField
Cd0 @TextInP .xml .xml #zField
Cd0 @TextInP .responsibility .responsibility #zField
Cd0 @StartRequest f0 '' #zField
Cd0 @EndTask f1 '' #zField
Cd0 @GridStep f3 '' #zField
Cd0 @PushWFArc f4 '' #zField
Cd0 @PushWFArc f2 '' #zField
>Proto Cd0 Cd0 RestorePortalDashboardToDefault #zField
Cd0 f0 outLink start.ivp #txt
Cd0 f0 inParamDecl '<> param;' #txt
Cd0 f0 requestEnabled true #txt
Cd0 f0 triggerEnabled false #txt
Cd0 f0 callSignature start() #txt
Cd0 f0 startName 'Restore Portal Dashboard to default' #txt
Cd0 f0 caseData businessCase.attach=true #txt
Cd0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start.ivp</name>
    </language>
</elementInfo>
' #txt
Cd0 f0 @C|.responsibility Everybody #txt
Cd0 f0 81 49 30 30 -20 17 #rect
Cd0 f1 337 49 30 30 0 15 #rect
Cd0 f3 actionTable 'out=in;
' #txt
Cd0 f3 actionCode 'import ch.ivyteam.ivy.application.property.ICustomProperty;

List<ICustomProperty> properties = ivy.wf.getApplication().customProperties().findAllStartingWith("dashboard.widgets");
for (ICustomProperty property : properties) {
	ivy.wf.getApplication().customProperties().delete(property.getName());
}' #txt
Cd0 f3 security system #txt
Cd0 f3 168 42 112 44 0 -8 #rect
Cd0 f4 111 64 168 64 #arcP
Cd0 f2 280 64 337 64 #arcP
>Proto Cd0 .type internaltest.Data #txt
>Proto Cd0 .processKind NORMAL #txt
>Proto Cd0 0 0 32 24 18 0 #rect
>Proto Cd0 @|BIcon #fIcon
Cd0 f0 mainOut f4 tail #connect
Cd0 f4 head f3 mainIn #connect
Cd0 f3 mainOut f2 tail #connect
Cd0 f2 head f1 mainIn #connect
