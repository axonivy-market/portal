[Ivy]
15EF07D454EF13E4 3.20 #module
>Proto >Proto Collection #zClass
Ps0 LogoutSettingProcess Big #zClass
Ps0 RD #cInfo
Ps0 #process
Ps0 @TextInP .ui2RdDataAction .ui2RdDataAction #zField
Ps0 @TextInP .rdData2UIAction .rdData2UIAction #zField
Ps0 @TextInP .resExport .resExport #zField
Ps0 @TextInP .type .type #zField
Ps0 @TextInP .processKind .processKind #zField
Ps0 @AnnotationInP-0n ai ai #zField
Ps0 @MessageFlowInP-0n messageIn messageIn #zField
Ps0 @MessageFlowOutP-0n messageOut messageOut #zField
Ps0 @TextInP .xml .xml #zField
Ps0 @TextInP .responsibility .responsibility #zField
Ps0 @RichDialogInitStart f0 '' #zField
Ps0 @RichDialogProcessEnd f1 '' #zField
Ps0 @PushWFArc f2 '' #zField
Ps0 @RichDialogMethodStart f3 '' #zField
Ps0 @RichDialogProcessEnd f4 '' #zField
Ps0 @GridStep f6 '' #zField
Ps0 @PushWFArc f5 '' #zField
Ps0 @CallSub f8 '' #zField
Ps0 @PushWFArc f9 '' #zField
Ps0 @PushWFArc f7 '' #zField
Ps0 @RichDialogMethodStart f10 '' #zField
Ps0 @CallSub f11 '' #zField
Ps0 @GridStep f12 '' #zField
Ps0 @PushWFArc f13 '' #zField
Ps0 @PushWFArc f14 '' #zField
Ps0 @RichDialogProcessEnd f15 '' #zField
Ps0 @PushWFArc f16 '' #zField
>Proto Ps0 Ps0 LogoutSettingProcess #zField
Ps0 f0 guid 15EF07DBEF876B54 #txt
Ps0 f0 type ch.ivy.addon.portalkit.multiapp.settings.LogoutSetting.LogoutSettingData #txt
Ps0 f0 method start() #txt
Ps0 f0 disableUIEvents true #txt
Ps0 f0 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<> param = methodEvent.getInputArguments();
' #txt
Ps0 f0 outParameterDecl '<> result;
' #txt
Ps0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start()</name>
    </language>
</elementInfo>
' #txt
Ps0 f0 51 51 26 26 -16 15 #rect
Ps0 f0 @|RichDialogInitStartIcon #fIcon
Ps0 f1 type ch.ivy.addon.portalkit.multiapp.settings.LogoutSetting.LogoutSettingData #txt
Ps0 f1 339 51 26 26 0 12 #rect
Ps0 f1 @|RichDialogProcessEndIcon #fIcon
Ps0 f2 expr out #txt
Ps0 f2 77 64 339 64 #arcP
Ps0 f3 guid 15EF07E1D7F2C49C #txt
Ps0 f3 type ch.ivy.addon.portalkit.multiapp.settings.LogoutSetting.LogoutSettingData #txt
Ps0 f3 method logout() #txt
Ps0 f3 disableUIEvents false #txt
Ps0 f3 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<> param = methodEvent.getInputArguments();
' #txt
Ps0 f3 outParameterDecl '<> result;
' #txt
Ps0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>logout()</name>
    </language>
</elementInfo>
' #txt
Ps0 f3 51 147 26 26 -21 15 #rect
Ps0 f3 @|RichDialogMethodStartIcon #fIcon
Ps0 f4 type ch.ivy.addon.portalkit.multiapp.settings.LogoutSetting.LogoutSettingData #txt
Ps0 f4 467 147 26 26 0 12 #rect
Ps0 f4 @|RichDialogProcessEndIcon #fIcon
Ps0 f6 actionDecl 'ch.ivy.addon.portalkit.multiapp.settings.LogoutSetting.LogoutSettingData out;
' #txt
Ps0 f6 actionTable 'out=in;
' #txt
Ps0 f6 actionCode 'ivy.session.logoutSessionUser();
ivy.session.getSecurityContext().destroySession(ivy.session.getIdentifier());' #txt
Ps0 f6 security system #txt
Ps0 f6 type ch.ivy.addon.portalkit.multiapp.settings.LogoutSetting.LogoutSettingData #txt
Ps0 f6 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Logout</name>
        <nameStyle>6
</nameStyle>
    </language>
</elementInfo>
' #txt
Ps0 f6 296 138 112 44 -19 -8 #rect
Ps0 f6 @|StepIcon #fIcon
Ps0 f5 expr out #txt
Ps0 f5 408 160 467 160 #arcP
Ps0 f8 type ch.ivy.addon.portalkit.multiapp.settings.LogoutSetting.LogoutSettingData #txt
Ps0 f8 processCall 'Functional Processes/Logout:call()' #txt
Ps0 f8 doCall true #txt
Ps0 f8 requestActionDecl '<> param;
' #txt
Ps0 f8 responseActionDecl 'ch.ivy.addon.portalkit.multiapp.settings.LogoutSetting.LogoutSettingData out;
' #txt
Ps0 f8 responseMappingAction 'out=in;
' #txt
Ps0 f8 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Extend logout</name>
        <nameStyle>13
</nameStyle>
    </language>
</elementInfo>
' #txt
Ps0 f8 136 138 112 44 -37 -8 #rect
Ps0 f8 @|CallSubIcon #fIcon
Ps0 f9 expr out #txt
Ps0 f9 77 160 136 160 #arcP
Ps0 f7 expr out #txt
Ps0 f7 248 160 296 160 #arcP
Ps0 f10 guid 15EF0CF6DE58BE72 #txt
Ps0 f10 type ch.ivy.addon.portalkit.multiapp.settings.LogoutSetting.LogoutSettingData #txt
Ps0 f10 method reserveTask() #txt
Ps0 f10 disableUIEvents false #txt
Ps0 f10 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<> param = methodEvent.getInputArguments();
' #txt
Ps0 f10 outParameterDecl '<> result;
' #txt
Ps0 f10 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>reserveTask()</name>
    </language>
</elementInfo>
' #txt
Ps0 f10 51 245 26 26 -38 15 #rect
Ps0 f10 @|RichDialogMethodStartIcon #fIcon
Ps0 f11 type ch.ivy.addon.portalkit.multiapp.settings.LogoutSetting.LogoutSettingData #txt
Ps0 f11 processCall 'Functional Processes/Logout:call()' #txt
Ps0 f11 doCall true #txt
Ps0 f11 requestActionDecl '<> param;
' #txt
Ps0 f11 responseActionDecl 'ch.ivy.addon.portalkit.multiapp.settings.LogoutSetting.LogoutSettingData out;
' #txt
Ps0 f11 responseMappingAction 'out=in;
' #txt
Ps0 f11 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Extend logout</name>
        <nameStyle>13
</nameStyle>
    </language>
</elementInfo>
' #txt
Ps0 f11 137 235 112 44 -37 -8 #rect
Ps0 f11 @|CallSubIcon #fIcon
Ps0 f12 actionDecl 'ch.ivy.addon.portalkit.multiapp.settings.LogoutSetting.LogoutSettingData out;
' #txt
Ps0 f12 actionTable 'out=in;
' #txt
Ps0 f12 actionCode 'ivy.session.parkTask(ivy.task);
ivy.session.logoutSessionUser();
ivy.session.getSecurityContext().destroySession(ivy.session.getIdentifier());' #txt
Ps0 f12 security system #txt
Ps0 f12 type ch.ivy.addon.portalkit.multiapp.settings.LogoutSetting.LogoutSettingData #txt
Ps0 f12 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Logout and
reserve</name>
        <nameStyle>18
</nameStyle>
    </language>
</elementInfo>
' #txt
Ps0 f12 297 235 112 44 -31 -16 #rect
Ps0 f12 @|StepIcon #fIcon
Ps0 f13 expr out #txt
Ps0 f13 249 257 297 257 #arcP
Ps0 f14 expr out #txt
Ps0 f14 76 257 137 257 #arcP
Ps0 f15 type ch.ivy.addon.portalkit.multiapp.settings.LogoutSetting.LogoutSettingData #txt
Ps0 f15 467 245 26 26 0 12 #rect
Ps0 f15 @|RichDialogProcessEndIcon #fIcon
Ps0 f16 expr out #txt
Ps0 f16 409 257 467 257 #arcP
>Proto Ps0 .type ch.ivy.addon.portalkit.multiapp.settings.LogoutSetting.LogoutSettingData #txt
>Proto Ps0 .processKind HTML_DIALOG #txt
>Proto Ps0 -8 -8 16 16 16 26 #rect
>Proto Ps0 '' #fIcon
Ps0 f0 mainOut f2 tail #connect
Ps0 f2 head f1 mainIn #connect
Ps0 f6 mainOut f5 tail #connect
Ps0 f5 head f4 mainIn #connect
Ps0 f3 mainOut f9 tail #connect
Ps0 f9 head f8 mainIn #connect
Ps0 f8 mainOut f7 tail #connect
Ps0 f7 head f6 mainIn #connect
Ps0 f11 mainOut f13 tail #connect
Ps0 f13 head f12 mainIn #connect
Ps0 f10 mainOut f14 tail #connect
Ps0 f14 head f11 mainIn #connect
Ps0 f12 mainOut f16 tail #connect
Ps0 f16 head f15 mainIn #connect
