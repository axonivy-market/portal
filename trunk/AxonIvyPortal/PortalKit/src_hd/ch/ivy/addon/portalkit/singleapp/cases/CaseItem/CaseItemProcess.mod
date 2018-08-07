[Ivy]
[>Created: Thu Jul 28 11:20:47 ICT 2016]
153AC8F1D34C2E0D 3.18 #module
>Proto >Proto Collection #zClass
Cs0 CaseItemProcess Big #zClass
Cs0 RD #cInfo
Cs0 #process
Cs0 @TextInP .ui2RdDataAction .ui2RdDataAction #zField
Cs0 @TextInP .rdData2UIAction .rdData2UIAction #zField
Cs0 @TextInP .resExport .resExport #zField
Cs0 @TextInP .type .type #zField
Cs0 @TextInP .processKind .processKind #zField
Cs0 @AnnotationInP-0n ai ai #zField
Cs0 @MessageFlowInP-0n messageIn messageIn #zField
Cs0 @MessageFlowOutP-0n messageOut messageOut #zField
Cs0 @TextInP .xml .xml #zField
Cs0 @TextInP .responsibility .responsibility #zField
Cs0 @RichDialogProcessEnd f1 '' #zField
Cs0 @GridStep f11 '' #zField
Cs0 @PushWFArc f2 '' #zField
Cs0 @RichDialogInitStart f0 '' #zField
Cs0 @PushWFArc f3 '' #zField
>Proto Cs0 Cs0 CaseItemProcess #zField
Cs0 f1 type ch.ivy.addon.portalkit.singleapp.cases.CaseItem.CaseItemData #txt
Cs0 f1 53 213 22 22 14 0 #rect
Cs0 f1 @|RichDialogProcessEndIcon #fIcon
Cs0 f11 actionDecl 'ch.ivy.addon.portalkit.singleapp.cases.CaseItem.CaseItemData out;
' #txt
Cs0 f11 actionTable 'out=in;
' #txt
Cs0 f11 actionCode 'import ch.ivy.addon.portalkit.service.PermissionCheckerService;
import ch.ivyteam.ivy.security.IPermission;

in.internalCase = ivy.wf.findCase(in.caseId);
PermissionCheckerService permissionCheker = new PermissionCheckerService();
in.canChangeCaseDescription = permissionCheker.hasPermission(IPermission.CASE_WRITE_DESCRIPTION);
in.canChangeCaseName = permissionCheker.hasPermission(IPermission.CASE_WRITE_NAME);' #txt
Cs0 f11 security system #txt
Cs0 f11 type ch.ivy.addon.portalkit.singleapp.cases.CaseItem.CaseItemData #txt
Cs0 f11 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>get case</name>
        <nameStyle>8,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f11 46 148 36 24 20 -2 #rect
Cs0 f11 @|StepIcon #fIcon
Cs0 f2 expr out #txt
Cs0 f2 64 172 64 213 #arcP
Cs0 f0 guid 153ACF5FD10306C5 #txt
Cs0 f0 type ch.ivy.addon.portalkit.singleapp.cases.CaseItem.CaseItemData #txt
Cs0 f0 method start(Long) #txt
Cs0 f0 disableUIEvents true #txt
Cs0 f0 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<java.lang.Long caseId> param = methodEvent.getInputArguments();
' #txt
Cs0 f0 inParameterMapAction 'out.caseId=param.caseId;
' #txt
Cs0 f0 outParameterDecl '<> result;
' #txt
Cs0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start(Long)</name>
        <nameStyle>11,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f0 53 85 22 22 14 0 #rect
Cs0 f0 @|RichDialogInitStartIcon #fIcon
Cs0 f3 expr out #txt
Cs0 f3 64 107 64 148 #arcP
>Proto Cs0 .type ch.ivy.addon.portalkit.singleapp.cases.CaseItem.CaseItemData #txt
>Proto Cs0 .processKind HTML_DIALOG #txt
>Proto Cs0 -8 -8 16 16 16 26 #rect
>Proto Cs0 '' #fIcon
Cs0 f11 mainOut f2 tail #connect
Cs0 f2 head f1 mainIn #connect
Cs0 f0 mainOut f3 tail #connect
Cs0 f3 head f11 mainIn #connect
