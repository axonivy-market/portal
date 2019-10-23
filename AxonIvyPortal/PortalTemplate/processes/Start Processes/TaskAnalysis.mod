[Ivy]
163D97C604D3D4C9 7.5.0 #module
>Proto >Proto Collection #zClass
Ts0 TaskAnalysis Big #zClass
Ts0 B #cInfo
Ts0 #process
Ts0 @TextInP .type .type #zField
Ts0 @TextInP .processKind .processKind #zField
Ts0 @AnnotationInP-0n ai ai #zField
Ts0 @MessageFlowInP-0n messageIn messageIn #zField
Ts0 @MessageFlowOutP-0n messageOut messageOut #zField
Ts0 @TextInP .xml .xml #zField
Ts0 @TextInP .responsibility .responsibility #zField
Ts0 @StartRequest f0 '' #zField
Ts0 @EndTask f1 '' #zField
Ts0 @UserDialog f5 '' #zField
Ts0 @PushWFArc f2 '' #zField
Ts0 @Alternative f3 '' #zField
Ts0 @PushWFArc f6 '' #zField
Ts0 @PushWFArc f7 '' #zField
Ts0 @GridStep f8 '' #zField
Ts0 @PushWFArc f9 '' #zField
Ts0 @PushWFArc f4 '' #zField
>Proto Ts0 Ts0 TaskAnalysis #zField
Ts0 f0 outLink start.ivp #txt
Ts0 f0 inParamDecl '<> param;' #txt
Ts0 f0 requestEnabled true #txt
Ts0 f0 triggerEnabled false #txt
Ts0 f0 callSignature start() #txt
Ts0 f0 persist false #txt
Ts0 f0 taskData 'TaskTriggered.EXPRI=2
TaskTriggered.EXROL=Everybody
TaskTriggered.EXTYPE=0
TaskTriggered.PRI=2
TaskTriggered.ROL=Everybody
TaskTriggered.TYPE=0' #txt
Ts0 f0 caseData businessCase.attach=true #txt
Ts0 f0 showInStartList 0 #txt
Ts0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start.ivp</name>
        <nameStyle>9,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ts0 f0 @C|.responsibility Everybody #txt
Ts0 f0 81 49 30 30 -21 17 #rect
Ts0 f0 @|StartRequestIcon #fIcon
Ts0 f1 617 49 30 30 0 15 #rect
Ts0 f1 @|EndIcon #fIcon
Ts0 f5 dialogId ch.ivy.addon.portal.generic.TaskAnalysis #txt
Ts0 f5 startMethod start(String) #txt
Ts0 f5 requestActionDecl '<String menuState> param;' #txt
Ts0 f5 requestMappingAction 'param.menuState=ch.ivy.addon.portalkit.util.MenuUtils.getMenuState();
' #txt
Ts0 f5 responseActionDecl 'ch.ivy.addon.portal.generic.TaskAnalysisData out;
' #txt
Ts0 f5 responseMappingAction 'out=in;
' #txt
Ts0 f5 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Task Analysis dialog</name>
        <nameStyle>20
</nameStyle>
    </language>
</elementInfo>
' #txt
Ts0 f5 440 42 128 44 -56 -8 #rect
Ts0 f5 @|UserDialogIcon #fIcon
Ts0 f2 expr out #txt
Ts0 f2 568 64 617 64 #arcP
Ts0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>has permission?</name>
        <nameStyle>15,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ts0 f3 320 48 32 32 -47 18 #rect
Ts0 f3 @|AlternativeIcon #fIcon
Ts0 f6 expr in #txt
Ts0 f6 outCond in.hasPermission #txt
Ts0 f6 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>yes</name>
        <nameStyle>3,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ts0 f6 352 64 440 64 #arcP
Ts0 f6 0 0.4166666666666667 0 -11 #arcLabel
Ts0 f7 expr in #txt
Ts0 f7 .xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>no</name>
        <nameStyle>2,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ts0 f7 336 80 632 79 #arcP
Ts0 f7 1 336 112 #addKink
Ts0 f7 2 632 112 #addKink
Ts0 f7 1 0.23529411764705882 0 -10 #arcLabel
Ts0 f8 actionTable 'out=in;
' #txt
Ts0 f8 actionCode 'import ch.ivy.addon.portalkit.enums.PortalPermission;
import ch.ivy.addon.portalkit.service.PermissionCheckerService;
PermissionCheckerService service = new PermissionCheckerService();
in.hasPermission = service.hasPermission(PortalPermission.STATISTIC_ANALYZE_TASK.getPermission());' #txt
Ts0 f8 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>check permission</name>
    </language>
</elementInfo>
' #txt
Ts0 f8 168 42 112 44 -49 -8 #rect
Ts0 f8 @|StepIcon #fIcon
Ts0 f9 expr out #txt
Ts0 f9 111 64 168 64 #arcP
Ts0 f4 expr out #txt
Ts0 f4 280 64 320 64 #arcP
>Proto Ts0 .type ch.ivy.addon.portal.generic.TaskAnalysisData #txt
>Proto Ts0 .processKind NORMAL #txt
>Proto Ts0 0 0 32 24 18 0 #rect
>Proto Ts0 @|BIcon #fIcon
Ts0 f5 mainOut f2 tail #connect
Ts0 f2 head f1 mainIn #connect
Ts0 f3 out f6 tail #connect
Ts0 f6 head f5 mainIn #connect
Ts0 f3 out f7 tail #connect
Ts0 f7 head f1 mainIn #connect
Ts0 f0 mainOut f9 tail #connect
Ts0 f9 head f8 mainIn #connect
Ts0 f8 mainOut f4 tail #connect
Ts0 f4 head f3 in #connect
