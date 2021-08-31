[Ivy]
175F92F71BC45295 9.3.0 #module
>Proto >Proto Collection #zClass
Mn0 Migration Big #zClass
Mn0 B #cInfo
Mn0 #process
Bk0 BpmnUserTask Big #zClass
Bk0 BpmnUserTask #cInfo
Mn0 @TextInP .type .type #zField
Mn0 @TextInP .processKind .processKind #zField
Mn0 @TextInP .xml .xml #zField
Mn0 @TextInP .responsibility .responsibility #zField
Mn0 @StartRequest f0 '' #zField
Mn0 @EndTask f1 '' #zField
Mn0 @PushWFArc f11 '' #zField
Mn0 @PushWFArc f12 '' #zField
Mn0 Bk0 S11 'Sub 1' #zField
>Proto Mn0 Mn0 Migration #zField
Bk0 @AnnotationInP-0n ai ai #zField
Bk0 @TextInP .type .type #zField
Bk0 @TextInP .processKind .processKind #zField
Bk0 @TextInP .xml .xml #zField
Bk0 @TextInP .responsibility .responsibility #zField
Bk0 @PushWFArc f10 '' #zField
Bk0 @UserDialog f3 '' #zField
Bk0 @GridStep f9 '' #zField
Bk0 @PushTrueWFInG-01 g0 '' #zField
Bk0 @PushTrueWFOutG-01 g1 '' #zField
Bk0 @PushWFArc f1 '' #zField
Bk0 @Alternative f6 '' #zField
Bk0 @PushWFArc f8 '' #zField
Bk0 @UserDialog f11 '' #zField
Bk0 @PushWFArc f13 '' #zField
Bk0 @PushWFArc f0 '' #zField
Bk0 @PushWFArc f2 '' #zField
>Proto Bk0 Bk0 BpmnUserTask #zField
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
Mn0 f1 497 49 30 30 0 15 #rect
Mn0 f11 111 64 256 64 #arcP
Mn0 f12 384 64 497 64 #arcP
Mn0 S11 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language lang="en">
        <name>Start Portal Migration</name>
    </language>
</elementInfo>
' #txt
Mn0 S11 256 42 128 44 -56 -8 #rect
>Proto Mn0 .type portalmigration.Data #txt
>Proto Mn0 .processKind NORMAL #txt
>Proto Mn0 0 0 32 24 18 0 #rect
>Proto Mn0 @|BIcon #fIcon
Bk0 f10 512 160 568 160 #arcP
Bk0 f3 dialogId portal.migration.MigrationData #txt
Bk0 f3 startMethod start() #txt
Bk0 f3 requestActionDecl '<> param;' #txt
Bk0 f3 responseMappingAction 'out=in;
' #txt
Bk0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>MigrationData</name>
    </language>
</elementInfo>
' #txt
Bk0 f3 400 138 112 44 -38 -8 #rect
Bk0 f9 actionTable 'out=in;
' #txt
Bk0 f9 actionCode ivy.session.logoutSessionUser(); #txt
Bk0 f9 security system #txt
Bk0 f9 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>logout</name>
    </language>
</elementInfo>
' #txt
Bk0 f9 568 138 112 44 -17 -8 #rect
Bk0 g0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language lang="en">
        <name>in 1</name>
    </language>
</elementInfo>
' #txt
Bk0 g0 51 147 26 26 -17 10 #rect
Bk0 g1 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language lang="en">
        <name>out 1</name>
    </language>
</elementInfo>
' #txt
Bk0 g1 763 147 26 26 0 5 #rect
Bk0 f1 680 160 763 160 #arcP
Bk0 f6 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>is login?</name>
    </language>
</elementInfo>
' #txt
Bk0 f6 184 144 32 32 -22 -40 #rect
Bk0 f8 77 160 184 160 #arcP
Bk0 f11 dialogId portal.migration.Login #txt
Bk0 f11 startMethod start() #txt
Bk0 f11 requestActionDecl '<> param;' #txt
Bk0 f11 responseMappingAction 'out=in;
' #txt
Bk0 f11 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Login</name>
    </language>
</elementInfo>
' #txt
Bk0 f11 272 234 112 44 -15 -8 #rect
Bk0 f13 384 256 456 182 #arcP
Bk0 f13 1 456 256 #addKink
Bk0 f13 0 0.8118944338658466 0 0 #arcLabel
Bk0 f0 expr in #txt
Bk0 f0 outCond ivy.session.isSessionUserUnknown() #txt
Bk0 f0 200 176 272 256 #arcP
Bk0 f0 1 200 256 #addKink
Bk0 f0 1 0.1556203489120305 0 0 #arcLabel
Bk0 f2 expr in #txt
Bk0 f2 216 160 400 160 #arcP
>Proto Bk0 0 0 32 24 18 0 #rect
>Proto Bk0 @|BpmnUserTaskIcon #fIcon
Mn0 f12 head f1 mainIn #connect
Mn0 f0 mainOut f11 tail #connect
Mn0 f11 head S11 g0 #connect
Mn0 S11 g1 f12 tail #connect
Bk0 f3 mainOut f10 tail #connect
Bk0 f10 head f9 mainIn #connect
Bk0 f1 head g1 m #connect
Bk0 f9 mainOut f1 tail #connect
Bk0 g0 m f8 tail #connect
Bk0 f8 head f6 in #connect
Bk0 f11 mainOut f13 tail #connect
Bk0 f13 head f3 mainIn #connect
Bk0 f6 out f0 tail #connect
Bk0 f0 head f11 mainIn #connect
Bk0 f6 out f2 tail #connect
Bk0 f2 head f3 mainIn #connect
Bk0 0 0 896 320 0 #ivRect
