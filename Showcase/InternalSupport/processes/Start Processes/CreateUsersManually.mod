[Ivy]
15F80B590709168C 3.20 #module
>Proto >Proto Collection #zClass
Cy0 CreateUsersManually Big #zClass
Cy0 B #cInfo
Cy0 #process
Cy0 @TextInP .resExport .resExport #zField
Cy0 @TextInP .type .type #zField
Cy0 @TextInP .processKind .processKind #zField
Cy0 @AnnotationInP-0n ai ai #zField
Cy0 @MessageFlowInP-0n messageIn messageIn #zField
Cy0 @MessageFlowOutP-0n messageOut messageOut #zField
Cy0 @TextInP .xml .xml #zField
Cy0 @TextInP .responsibility .responsibility #zField
Cy0 @StartRequest f0 '' #zField
Cy0 @EndTask f1 '' #zField
Cy0 @GridStep f3 '' #zField
Cy0 @PushWFArc f4 '' #zField
Cy0 @PushWFArc f2 '' #zField
>Proto Cy0 Cy0 CreateUsersManually #zField
Cy0 f0 outLink start.ivp #txt
Cy0 f0 type internaltest.Data #txt
Cy0 f0 inParamDecl '<> param;' #txt
Cy0 f0 actionDecl 'internaltest.Data out;
' #txt
Cy0 f0 guid 15F80B59081D7F16 #txt
Cy0 f0 requestEnabled true #txt
Cy0 f0 triggerEnabled false #txt
Cy0 f0 callSignature start() #txt
Cy0 f0 caseData businessCase.attach=true #txt
Cy0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start.ivp</name>
    </language>
</elementInfo>
' #txt
Cy0 f0 @C|.responsibility Everybody #txt
Cy0 f0 81 49 30 30 -21 17 #rect
Cy0 f0 @|StartRequestIcon #fIcon
Cy0 f1 type internaltest.Data #txt
Cy0 f1 337 49 30 30 0 15 #rect
Cy0 f1 @|EndIcon #fIcon
Cy0 f3 actionDecl 'internaltest.Data out;
' #txt
Cy0 f3 actionTable 'out=in;
' #txt
Cy0 f3 actionCode 'for (int i = 1; i < 30001; i++) {
	String userName = "trajan" + i;
	String fullUserName = "Traianus " + i;
	String password = "1";
	String email = "trajan@gmail121212.com";
	ivy.wf.getSecurityContext().createUser(userName, fullUserName, password ,java.util.Locale.ENGLISH, email, "");
	if (i% 100 == 0) {
		ivy.log.debug("trajan" + i);
	}
}

for (int i = 1; i < 10001; i++) {
	String userName = "octavian" + i;
	String fullUserName = "Augustus Caesar " + i;
	String password = "1";
	String email = "octavian@gmail121212.com";
	ivy.wf.getSecurityContext().createUser(userName, fullUserName, password ,java.util.Locale.ENGLISH, email, "");
	if (i% 100 == 0) {
		ivy.log.debug("octavian" + i);
	}
}

for (int i = 1; i < 5001; i++) {
	String userName = "charles" + i;
	String fullUserName = "Charles " + i;
	String password = "1";
	String email = "n1am.main1guyenh1oang@gmail121212.com";
	ivy.wf.getSecurityContext().createUser(userName, fullUserName, password ,java.util.Locale.ENGLISH, email, "");
	if (i% 100 == 0) {
		ivy.log.debug("charles" + i);
	}
}

for (int i = 1; i < 301; i++) {
	String userName = "arthur" + i;
	String fullUserName = "Arthur " + i;
	String password = "1";
	String email = "n1am.main1guyenh1oang@gmail121212.com";
	ivy.wf.getSecurityContext().createUser(userName, fullUserName, password ,java.util.Locale.ENGLISH, email, "");
	if (i% 100 == 0) {
		ivy.log.debug("arthur" + i);
	}
}' #txt
Cy0 f3 security system #txt
Cy0 f3 type internaltest.Data #txt
Cy0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Create users for test</name>
        <nameStyle>21
</nameStyle>
    </language>
</elementInfo>
' #txt
Cy0 f3 160 42 128 44 -56 -8 #rect
Cy0 f3 @|StepIcon #fIcon
Cy0 f4 expr out #txt
Cy0 f4 111 64 160 64 #arcP
Cy0 f2 expr out #txt
Cy0 f2 288 64 337 64 #arcP
>Proto Cy0 .type internaltest.Data #txt
>Proto Cy0 .processKind NORMAL #txt
>Proto Cy0 0 0 32 24 18 0 #rect
>Proto Cy0 @|BIcon #fIcon
Cy0 f0 mainOut f4 tail #connect
Cy0 f4 head f3 mainIn #connect
Cy0 f3 mainOut f2 tail #connect
Cy0 f2 head f1 mainIn #connect
