[Ivy]
17F2050944B46BB0 9.4.6 #module
>Proto >Proto Collection #zClass
Dn0 DashboardCreation Big #zClass
Dn0 B #cInfo
Dn0 #process
Dn0 @TextInP .colors .colors #zField
Dn0 @TextInP color color #zField
Dn0 @AnnotationInP-0n ai ai #zField
Dn0 @TextInP .type .type #zField
Dn0 @TextInP .processKind .processKind #zField
Dn0 @TextInP _s0 .colors #zField
Dn0 @TextInP .xml .xml #zField
Dn0 @TextInP .responsibility .responsibility #zField
Dn0 @StartRequest f0 '' #zField
Dn0 @EndTask f1 '' #zField
Dn0 @GridStep f3 '' #zField
Dn0 @PushWFArc f4 '' #zField
Dn0 @PushWFArc f2 '' #zField
Dn0 @EndTask f166 '' #zField
Dn0 @StartRequest f165 '' #zField
Dn0 @GridStep f168 '' #zField
Dn0 @PushWFArc f169 '' #zField
Dn0 @PushWFArc f167 '' #zField
>Proto Dn0 Dn0 DashboardCreation #zField
Dn0 f0 outLink createSampleDashboard.ivp #txt
Dn0 f0 inParamDecl '<> param;' #txt
Dn0 f0 requestEnabled true #txt
Dn0 f0 triggerEnabled false #txt
Dn0 f0 callSignature createSampleDashboard() #txt
Dn0 f0 caseData businessCase.attach=true #txt
Dn0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>createSampleDashboard.ivp</name>
    </language>
</elementInfo>
' #txt
Dn0 f0 @C|.responsibility Everybody #txt
Dn0 f0 41 49 30 30 -21 17 #rect
Dn0 f1 369 49 30 30 0 15 #rect
Dn0 f3 actionTable 'out=in;
' #txt
Dn0 f3 actionCode 'import ch.ivy.addon.portalkit.test.util.DashboardCreationUtils;
DashboardCreationUtils.createDashboard();' #txt
Dn0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>CreateDashboard</name>
    </language>
</elementInfo>
' #txt
Dn0 f3 160 42 112 44 -49 -8 #rect
Dn0 f4 71 64 160 64 #arcP
Dn0 f2 color default #txt
Dn0 f2 272 64 369 64 #arcP
Dn0 f166 369 177 30 30 0 15 #rect
Dn0 f165 outLink createPublicDashboardByJSonFile.ivp #txt
Dn0 f165 inParamDecl '<String filePath> param;' #txt
Dn0 f165 inParamTable 'out.dashboardFilePath=param.filePath;
' #txt
Dn0 f165 requestEnabled true #txt
Dn0 f165 triggerEnabled false #txt
Dn0 f165 callSignature createPublicDashboardByJSonFile(String) #txt
Dn0 f165 persist false #txt
Dn0 f165 caseData businessCase.attach=true #txt
Dn0 f165 showInStartList true #txt
Dn0 f165 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>createPublicDashboardByJSonFile.ivp</name>
    </language>
</elementInfo>
' #txt
Dn0 f165 @C|.responsibility Everybody #txt
Dn0 f165 49 177 30 30 -21 17 #rect
Dn0 f168 actionTable 'out=in;
' #txt
Dn0 f168 actionCode 'import java.io.InputStream;
import java.io.FileInputStream;
import java.nio.charset.StandardCharsets;
import ch.ivy.addon.portalkit.enums.PortalVariable;

InputStream inputstream = new FileInputStream(in.dashboardFilePath);
String config = new String(inputstream.readAllBytes(), StandardCharsets.UTF_8);
ivy.var.set(PortalVariable.DASHBOARD.key, config);' #txt
Dn0 f168 security system #txt
Dn0 f168 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Update Portal dashboard</name>
    </language>
</elementInfo>
' #txt
Dn0 f168 144 170 144 44 -69 -8 #rect
Dn0 f169 79 192 144 192 #arcP
Dn0 f167 color default #txt
Dn0 f167 288 192 369 192 #arcP
>Proto Dn0 .colors 'default=;
' #txt
>Proto Dn0 .type portalKit_test.DashboardCreationData #txt
>Proto Dn0 .processKind NORMAL #txt
>Proto Dn0 0 0 32 24 18 0 #rect
>Proto Dn0 @|BIcon #fIcon
Dn0 f0 mainOut f4 tail #connect
Dn0 f4 head f3 mainIn #connect
Dn0 f3 mainOut f2 tail #connect
Dn0 f2 head f1 mainIn #connect
Dn0 f165 mainOut f169 tail #connect
Dn0 f169 head f168 mainIn #connect
Dn0 f168 mainOut f167 tail #connect
Dn0 f167 head f166 mainIn #connect
