[Ivy]
175F92F71BC45295 9.3.0 #module
>Proto >Proto Collection #zClass
Mn0 Migration Big #zClass
Mn0 B #cInfo
Mn0 #process
Mn0 @TextInP .type .type #zField
Mn0 @TextInP .processKind .processKind #zField
Mn0 @TextInP .xml .xml #zField
Mn0 @TextInP .responsibility .responsibility #zField
Mn0 @StartRequest f0 '' #zField
Mn0 @EndTask f1 '' #zField
Mn0 @UserDialog f3 '' #zField
Mn0 @PushWFArc f4 '' #zField
Mn0 @PushWFArc f2 '' #zField
>Proto Mn0 Mn0 Migration #zField
Mn0 f0 outLink startMigrateConfiguration.ivp #txt
Mn0 f0 inParamDecl '<> param;' #txt
Mn0 f0 requestEnabled true #txt
Mn0 f0 triggerEnabled false #txt
Mn0 f0 callSignature startMigrateConfiguration() #txt
Mn0 f0 startName 'Migrate Configuration' #txt
Mn0 f0 caseData businessCase.attach=true #txt
Mn0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>startMigrateConfiguration.ivp</name>
    </language>
</elementInfo>
' #txt
Mn0 f0 @C|.responsibility Everybody #txt
Mn0 f0 81 49 30 30 -21 17 #rect
Mn0 f1 337 49 30 30 0 15 #rect
Mn0 f3 dialogId portal.migration.MigrationData #txt
Mn0 f3 startMethod start() #txt
Mn0 f3 requestActionDecl '<> param;' #txt
Mn0 f3 responseMappingAction 'out=in;
' #txt
Mn0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>MigrationData</name>
    </language>
</elementInfo>
' #txt
Mn0 f3 168 42 112 44 -38 -8 #rect
Mn0 f4 111 64 168 64 #arcP
Mn0 f2 280 64 337 64 #arcP
>Proto Mn0 .type portalmigration.Data #txt
>Proto Mn0 .processKind NORMAL #txt
>Proto Mn0 0 0 32 24 18 0 #rect
>Proto Mn0 @|BIcon #fIcon
Mn0 f0 mainOut f4 tail #connect
Mn0 f4 head f3 mainIn #connect
Mn0 f3 mainOut f2 tail #connect
Mn0 f2 head f1 mainIn #connect
