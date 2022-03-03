[Ivy]
175F92F71BC45295 9.4.0 #module
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
Mn0 @PushWFArc f4 '' #zField
Mn0 @PushWFArc f11 '' #zField
Mn0 Bk0 S11 'Sub 1' #zField
Mn0 @InfoButton f2 '' #zField
>Proto Mn0 Mn0 Migration #zField
Bk0 @AnnotationInP-0n ai ai #zField
Bk0 @TextInP .type .type #zField
Bk0 @TextInP .processKind .processKind #zField
Bk0 @TextInP .xml .xml #zField
Bk0 @TextInP .responsibility .responsibility #zField
Bk0 @PushWFArc f10 '' #zField
Bk0 @UserDialog f7 '' #zField
Bk0 @UserDialog f3 '' #zField
Bk0 @Alternative f5 '' #zField
Bk0 @PushWFArc f9 '' #zField
Bk0 @PushWFArc f8 '' #zField
Bk0 @PushTrueWFInG-01 g0 '' #zField
Bk0 @PushWFArc f0 '' #zField
Bk0 @PushTrueWFOutG-01 g1 '' #zField
Bk0 @PushWFArc f1 '' #zField
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
Mn0 f0 81 177 30 30 -21 17 #rect
Mn0 f1 529 177 30 30 0 15 #rect
Mn0 f4 111 192 272 192 #arcP
Mn0 f11 400 192 529 192 #arcP
Mn0 S11 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language lang="en">
        <name>Start migrating Portal</name>
    </language>
</elementInfo>
' #txt
Mn0 S11 272 170 128 44 -57 -8 #rect
Mn0 f2 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>**This is migration process for Portal configuration**&#13;
Details in the stories: IVYPORTAL-12037, IVYPORTAL-12210</name>
    </language>
</elementInfo>
' #txt
Mn0 f2 80 66 384 44 -186 -18 #rect
>Proto Mn0 .type portalmigration.Data #txt
>Proto Mn0 .processKind NORMAL #txt
>Proto Mn0 0 0 32 24 18 0 #rect
>Proto Mn0 @|BIcon #fIcon
Bk0 f10 expr in #txt
Bk0 f10 outCond ivy.session.isSessionUserUnknown() #txt
Bk0 f10 192 176 272 256 #arcP
Bk0 f10 1 192 256 #addKink
Bk0 f10 1 0.10814047932673854 0 0 #arcLabel
Bk0 f7 dialogId portal.migration.Login #txt
Bk0 f7 startMethod start() #txt
Bk0 f7 requestActionDecl '<> param;' #txt
Bk0 f7 responseMappingAction 'out=in;
' #txt
Bk0 f7 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Login</name>
    </language>
</elementInfo>
' #txt
Bk0 f7 272 234 112 44 -15 -8 #rect
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
Bk0 f3 416 138 112 44 -38 -8 #rect
Bk0 f5 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>is login?</name>
    </language>
</elementInfo>
' #txt
Bk0 f5 176 144 32 32 -24 -38 #rect
Bk0 f9 384 256 472 182 #arcP
Bk0 f9 1 472 256 #addKink
Bk0 f9 0 0.7730691931279445 0 0 #arcLabel
Bk0 f8 expr in #txt
Bk0 f8 208 160 416 160 #arcP
Bk0 g0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language lang="en">
        <name>in 1</name>
    </language>
</elementInfo>
' #txt
Bk0 g0 51 147 26 26 0 5 #rect
Bk0 f0 77 160 176 160 #arcP
Bk0 g1 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language lang="en">
        <name>out 1</name>
    </language>
</elementInfo>
' #txt
Bk0 g1 595 147 26 26 0 5 #rect
Bk0 f1 528 160 595 160 #arcP
>Proto Bk0 0 0 32 24 18 0 #rect
>Proto Bk0 @|BpmnUserTaskIcon #fIcon
Mn0 f11 head f1 mainIn #connect
Mn0 f0 mainOut f4 tail #connect
Mn0 f4 head S11 g0 #connect
Mn0 S11 g1 f11 tail #connect
Bk0 f7 mainOut f9 tail #connect
Bk0 f9 head f3 mainIn #connect
Bk0 f5 out f10 tail #connect
Bk0 f10 head f7 mainIn #connect
Bk0 f5 out f8 tail #connect
Bk0 f8 head f3 mainIn #connect
Bk0 g0 m f0 tail #connect
Bk0 f0 head f5 in #connect
Bk0 f1 head g1 m #connect
Bk0 f3 mainOut f1 tail #connect
Bk0 0 0 672 416 0 #ivRect
