[Ivy]
1725FB50A0E1F50A 9.2.0 #module
>Proto >Proto Collection #zClass
Ls0 LendingDetailProcess Big #zClass
Ls0 RD #cInfo
Ls0 #process
Ls0 @TextInP .type .type #zField
Ls0 @TextInP .processKind .processKind #zField
Ls0 @TextInP .xml .xml #zField
Ls0 @TextInP .responsibility .responsibility #zField
Ls0 @UdInit f0 '' #zField
Ls0 @UdProcessEnd f1 '' #zField
Ls0 @PushWFArc f2 '' #zField
Ls0 @UdEvent f3 '' #zField
Ls0 @UdExitEnd f4 '' #zField
Ls0 @PushWFArc f5 '' #zField
Ls0 @UdMethod f7 '' #zField
Ls0 @UserDialog f8 '' #zField
Ls0 @UserDialog f10 '' #zField
Ls0 @PushWFArc f11 '' #zField
Ls0 @UdMethod f6 '' #zField
Ls0 @GridStep f12 '' #zField
Ls0 @PushWFArc f13 '' #zField
Ls0 @PushWFArc f9 '' #zField
Ls0 @UdEvent f14 '' #zField
Ls0 @UserDialog f15 '' #zField
Ls0 @PushWFArc f16 '' #zField
>Proto Ls0 Ls0 LendingDetailProcess #zField
Ls0 f0 guid 1725FB50A476B54F #txt
Ls0 f0 method start(ch.ivy.addon.portalkit.bo.CaseMapDetail,Integer) #txt
Ls0 f0 inParameterDecl '<ch.ivy.addon.portalkit.bo.CaseMapDetail caseMapDetail,Integer index> param;' #txt
Ls0 f0 inParameterMapAction 'out.caseMapDetail=param.caseMapDetail;
out.stageIndex=param.index;
' #txt
Ls0 f0 outParameterDecl '<> result;' #txt
Ls0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start(CaseMapDetail,Integer)</name>
    </language>
</elementInfo>
' #txt
Ls0 f0 83 51 26 26 -16 15 #rect
Ls0 f0 @|UdInitIcon #fIcon
Ls0 f1 299 51 26 26 0 12 #rect
Ls0 f1 @|UdProcessEndIcon #fIcon
Ls0 f2 109 64 299 64 #arcP
Ls0 f3 guid 1725FB50A5B20F7F #txt
Ls0 f3 actionTable 'out=in;
' #txt
Ls0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>close</name>
    </language>
</elementInfo>
' #txt
Ls0 f3 83 147 26 26 -15 15 #rect
Ls0 f3 @|UdEventIcon #fIcon
Ls0 f4 211 147 26 26 0 12 #rect
Ls0 f4 @|UdExitEndIcon #fIcon
Ls0 f5 109 160 211 160 #arcP
Ls0 f7 guid 1726DF016BB85891 #txt
Ls0 f7 method navigateToOverviewPage() #txt
Ls0 f7 inParameterDecl '<> param;' #txt
Ls0 f7 outParameterDecl '<> result;' #txt
Ls0 f7 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>navigateToOverviewPage()</name>
    </language>
</elementInfo>
' #txt
Ls0 f7 83 339 26 26 -28 15 #rect
Ls0 f7 @|UdMethodIcon #fIcon
Ls0 f8 dialogId com.axonivy.portal.userexamples.credit.LendingOverview #txt
Ls0 f8 startMethod start(ch.ivy.addon.portalkit.persistence.domain.UserProcess) #txt
Ls0 f8 requestActionDecl '<ch.ivy.addon.portalkit.persistence.domain.UserProcess userProcess> param;' #txt
Ls0 f8 requestMappingAction 'param.userProcess=in.userProcess;
' #txt
Ls0 f8 responseMappingAction 'out=in;
' #txt
Ls0 f8 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>LendingOverview</name>
    </language>
</elementInfo>
' #txt
Ls0 f8 408 330 112 44 -47 -8 #rect
Ls0 f8 @|UserDialogIcon #fIcon
Ls0 f10 dialogId com.axonivy.portal.userexamples.credit.LendingDetail #txt
Ls0 f10 startMethod start(ch.ivy.addon.portalkit.bo.CaseMapDetail,Integer) #txt
Ls0 f10 requestActionDecl '<ch.ivy.addon.portalkit.bo.CaseMapDetail caseMapDetail,Integer index> param;' #txt
Ls0 f10 requestMappingAction 'param.caseMapDetail=in.caseMapDetail;
param.index=in.stageIndex;
' #txt
Ls0 f10 responseMappingAction 'out=in;
' #txt
Ls0 f10 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>LendingDetail</name>
    </language>
</elementInfo>
' #txt
Ls0 f10 296 234 112 44 -38 -8 #rect
Ls0 f10 @|UserDialogIcon #fIcon
Ls0 f11 109 256 296 256 #arcP
Ls0 f6 guid 1726DEFF21B6E30F #txt
Ls0 f6 method navigate(ch.ivy.addon.portalkit.bo.CaseMapDetail,Integer) #txt
Ls0 f6 inParameterDecl '<ch.ivy.addon.portalkit.bo.CaseMapDetail caseMapDetail,Integer stageIndex> param;' #txt
Ls0 f6 inParameterMapAction 'out.caseMapDetail=param.caseMapDetail;
out.stageIndex=param.stageIndex;
' #txt
Ls0 f6 outParameterDecl '<> result;' #txt
Ls0 f6 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>navigate(CaseMapDetail,Integer)</name>
    </language>
</elementInfo>
' #txt
Ls0 f6 83 243 26 26 -25 15 #rect
Ls0 f6 @|UdMethodIcon #fIcon
Ls0 f12 actionTable 'out=in;
' #txt
Ls0 f12 actionCode 'import ch.ivy.addon.portalkit.persistence.domain.UserProcess;

in.userProcess = new UserProcess();
in.userProcess.setLink(in.caseMapDetail.startLink);
in.userProcess.setIcon(in.caseMapDetail.icon);
in.userProcess.setProcessName(in.caseMapDetail.getName());
in.userProcess.setDescription(in.caseMapDetail.getDescription());
' #txt
Ls0 f12 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>create user process</name>
    </language>
</elementInfo>
' #txt
Ls0 f12 240 330 128 44 -55 -8 #rect
Ls0 f12 @|StepIcon #fIcon
Ls0 f13 109 352 240 352 #arcP
Ls0 f9 368 352 408 352 #arcP
Ls0 f14 guid 17297FBEEBF8A2C6 #txt
Ls0 f14 actionTable 'out=in;
' #txt
Ls0 f14 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>cancel</name>
    </language>
</elementInfo>
' #txt
Ls0 f14 83 435 26 26 -14 15 #rect
Ls0 f14 @|UdEventIcon #fIcon
Ls0 f15 dialogId com.axonivy.portal.userexamples.ExampleHomePage #txt
Ls0 f15 startMethod start() #txt
Ls0 f15 requestActionDecl '<> param;' #txt
Ls0 f15 responseMappingAction 'out=in;
' #txt
Ls0 f15 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>ExampleHomePage</name>
    </language>
</elementInfo>
' #txt
Ls0 f15 160 426 128 44 -55 -8 #rect
Ls0 f15 @|UserDialogIcon #fIcon
Ls0 f16 109 448 160 448 #arcP
>Proto Ls0 .type com.axonivy.portal.userexamples.credit.LendingDetail.LendingDetailData #txt
>Proto Ls0 .processKind HTML_DIALOG #txt
>Proto Ls0 -8 -8 16 16 16 26 #rect
>Proto Ls0 '' #fIcon
Ls0 f0 mainOut f2 tail #connect
Ls0 f2 head f1 mainIn #connect
Ls0 f3 mainOut f5 tail #connect
Ls0 f5 head f4 mainIn #connect
Ls0 f6 mainOut f11 tail #connect
Ls0 f11 head f10 mainIn #connect
Ls0 f7 mainOut f13 tail #connect
Ls0 f13 head f12 mainIn #connect
Ls0 f12 mainOut f9 tail #connect
Ls0 f9 head f8 mainIn #connect
Ls0 f14 mainOut f16 tail #connect
Ls0 f16 head f15 mainIn #connect
