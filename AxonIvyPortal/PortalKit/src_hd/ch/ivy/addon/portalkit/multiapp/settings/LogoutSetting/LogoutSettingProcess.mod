[Ivy]
15EF07D454EF13E4 7.5.0 #module
>Proto >Proto Collection #zClass
Ps0 LogoutSettingProcess Big #zClass
Ps0 RD #cInfo
Ps0 #process
Ps0 @TextInP .type .type #zField
Ps0 @TextInP .processKind .processKind #zField
Ps0 @AnnotationInP-0n ai ai #zField
Ps0 @MessageFlowInP-0n messageIn messageIn #zField
Ps0 @MessageFlowOutP-0n messageOut messageOut #zField
Ps0 @TextInP .xml .xml #zField
Ps0 @TextInP .responsibility .responsibility #zField
Ps0 @UdInit f0 '' #zField
Ps0 @UdProcessEnd f1 '' #zField
Ps0 @PushWFArc f2 '' #zField
Ps0 @UdMethod f3 '' #zField
Ps0 @UdProcessEnd f4 '' #zField
Ps0 @CallSub f8 '' #zField
Ps0 @PushWFArc f9 '' #zField
Ps0 @UdMethod f10 '' #zField
Ps0 @CallSub f11 '' #zField
Ps0 @PushWFArc f14 '' #zField
Ps0 @UdProcessEnd f15 '' #zField
Ps0 @PushWFArc f5 '' #zField
Ps0 @PushWFArc f6 '' #zField
>Proto Ps0 Ps0 LogoutSettingProcess #zField
Ps0 f0 guid 15EF07DBEF876B54 #txt
Ps0 f0 method start() #txt
Ps0 f0 inParameterDecl '<> param;' #txt
Ps0 f0 outParameterDecl '<> result;' #txt
Ps0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start()</name>
    </language>
</elementInfo>
' #txt
Ps0 f0 51 51 26 26 -16 15 #rect
Ps0 f0 @|UdInitIcon #fIcon
Ps0 f1 307 51 26 26 0 12 #rect
Ps0 f1 @|UdProcessEndIcon #fIcon
Ps0 f2 expr out #txt
Ps0 f2 77 64 307 64 #arcP
Ps0 f3 guid 15EF07E1D7F2C49C #txt
Ps0 f3 method logout() #txt
Ps0 f3 inParameterDecl '<> param;' #txt
Ps0 f3 outParameterDecl '<> result;' #txt
Ps0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>logout()</name>
    </language>
</elementInfo>
' #txt
Ps0 f3 51 147 26 26 -21 15 #rect
Ps0 f3 @|UdMethodIcon #fIcon
Ps0 f4 307 147 26 26 0 12 #rect
Ps0 f4 @|UdProcessEndIcon #fIcon
Ps0 f8 processCall 'Functional Processes/Logout:call(Boolean)' #txt
Ps0 f8 requestActionDecl '<Boolean isTaskReserve> param;' #txt
Ps0 f8 requestMappingAction 'param.isTaskReserve=false;
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
Ps0 f10 guid 15EF0CF6DE58BE72 #txt
Ps0 f10 method reserveTask() #txt
Ps0 f10 inParameterDecl '<> param;' #txt
Ps0 f10 outParameterDecl '<> result;' #txt
Ps0 f10 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>reserveTask()</name>
    </language>
</elementInfo>
' #txt
Ps0 f10 51 245 26 26 -38 15 #rect
Ps0 f10 @|UdMethodIcon #fIcon
Ps0 f11 processCall 'Functional Processes/Logout:call(Boolean)' #txt
Ps0 f11 requestActionDecl '<Boolean isTaskReserve> param;' #txt
Ps0 f11 requestMappingAction 'param.isTaskReserve=true;
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
Ps0 f14 expr out #txt
Ps0 f14 76 257 137 257 #arcP
Ps0 f15 307 245 26 26 0 12 #rect
Ps0 f15 @|UdProcessEndIcon #fIcon
Ps0 f5 expr out #txt
Ps0 f5 248 160 307 160 #arcP
Ps0 f6 expr out #txt
Ps0 f6 249 257 307 257 #arcP
>Proto Ps0 .type ch.ivy.addon.portalkit.multiapp.settings.LogoutSetting.LogoutSettingData #txt
>Proto Ps0 .processKind HTML_DIALOG #txt
>Proto Ps0 -8 -8 16 16 16 26 #rect
>Proto Ps0 '' #fIcon
Ps0 f0 mainOut f2 tail #connect
Ps0 f2 head f1 mainIn #connect
Ps0 f3 mainOut f9 tail #connect
Ps0 f9 head f8 mainIn #connect
Ps0 f10 mainOut f14 tail #connect
Ps0 f14 head f11 mainIn #connect
Ps0 f8 mainOut f5 tail #connect
Ps0 f5 head f4 mainIn #connect
Ps0 f11 mainOut f6 tail #connect
Ps0 f6 head f15 mainIn #connect
