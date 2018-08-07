[Ivy]
[>Created: Fri Feb 26 17:11:12 ICT 2016]
152E8EDB33C1BDC1 3.18 #module
>Proto >Proto Collection #zClass
Cs0 CaseWidgetProcess Big #zClass
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
Cs0 @RichDialogInitStart f0 '' #zField
Cs0 @RichDialogProcessEnd f1 '' #zField
Cs0 @PushWFArc f2 '' #zField
Cs0 @RichDialogMethodStart f6 '' #zField
Cs0 @RichDialogProcessEnd f7 '' #zField
Cs0 @GridStep f9 '' #zField
Cs0 @PushWFArc f10 '' #zField
Cs0 @PushWFArc f8 '' #zField
Cs0 @RichDialogMethodStart f11 '' #zField
Cs0 @RichDialogProcessEnd f12 '' #zField
Cs0 @GridStep f3 '' #zField
Cs0 @PushWFArc f4 '' #zField
Cs0 @PushWFArc f5 '' #zField
>Proto Cs0 Cs0 CaseWidgetProcess #zField
Cs0 f0 guid 152E8EDB3E3A6957 #txt
Cs0 f0 type ch.ivy.addon.portalkit.component.CaseWidget.CaseWidgetData #txt
Cs0 f0 method start() #txt
Cs0 f0 disableUIEvents true #txt
Cs0 f0 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<> param = methodEvent.getInputArguments();
' #txt
Cs0 f0 outParameterDecl '<> result;
' #txt
Cs0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start()</name>
    </language>
</elementInfo>
' #txt
Cs0 f0 53 85 22 22 14 0 #rect
Cs0 f0 @|RichDialogInitStartIcon #fIcon
Cs0 f1 type ch.ivy.addon.portalkit.component.CaseWidget.CaseWidgetData #txt
Cs0 f1 525 85 22 22 14 0 #rect
Cs0 f1 @|RichDialogProcessEndIcon #fIcon
Cs0 f2 expr out #txt
Cs0 f2 75 96 525 96 #arcP
Cs0 f6 guid 1530C78D050B0AA4 #txt
Cs0 f6 type ch.ivy.addon.portalkit.component.CaseWidget.CaseWidgetData #txt
Cs0 f6 method filter() #txt
Cs0 f6 disableUIEvents false #txt
Cs0 f6 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<> param = methodEvent.getInputArguments();
' #txt
Cs0 f6 outParameterDecl '<> result;
' #txt
Cs0 f6 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>filter()</name>
    </language>
</elementInfo>
' #txt
Cs0 f6 53 373 22 22 14 0 #rect
Cs0 f6 @|RichDialogMethodStartIcon #fIcon
Cs0 f7 type ch.ivy.addon.portalkit.component.CaseWidget.CaseWidgetData #txt
Cs0 f7 525 373 22 22 14 0 #rect
Cs0 f7 @|RichDialogProcessEndIcon #fIcon
Cs0 f9 actionDecl 'ch.ivy.addon.portalkit.component.CaseWidget.CaseWidgetData out;
' #txt
Cs0 f9 actionTable 'out=in;
' #txt
Cs0 f9 actionCode 'import ch.ivy.addon.portalkit.service.FilterService;

FilterService service = new FilterService(in.filteringKeyword);
in.filteredCases = service.filterCases(in.cases);
' #txt
Cs0 f9 type ch.ivy.addon.portalkit.component.CaseWidget.CaseWidgetData #txt
Cs0 f9 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>filter cases 
based on keyword</name>
        <nameStyle>30,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f9 270 372 36 24 20 14 #rect
Cs0 f9 @|StepIcon #fIcon
Cs0 f10 expr out #txt
Cs0 f10 75 384 270 384 #arcP
Cs0 f8 expr out #txt
Cs0 f8 306 384 525 384 #arcP
Cs0 f11 guid 1530D5DA30DFC025 #txt
Cs0 f11 type ch.ivy.addon.portalkit.component.CaseWidget.CaseWidgetData #txt
Cs0 f11 method setCases(java.util.List<ch.ivy.addon.portalkit.bo.RemoteCase>) #txt
Cs0 f11 disableUIEvents false #txt
Cs0 f11 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<java.util.List<ch.ivy.addon.portalkit.bo.RemoteCase> cases> param = methodEvent.getInputArguments();
' #txt
Cs0 f11 inParameterMapAction 'out.cases=param.cases;
' #txt
Cs0 f11 outParameterDecl '<> result;
' #txt
Cs0 f11 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>setCases(List&lt;RemoteCase&gt;)</name>
        <nameStyle>26,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f11 53 245 22 22 14 0 #rect
Cs0 f11 @|RichDialogMethodStartIcon #fIcon
Cs0 f12 type ch.ivy.addon.portalkit.component.CaseWidget.CaseWidgetData #txt
Cs0 f12 525 245 22 22 14 0 #rect
Cs0 f12 @|RichDialogProcessEndIcon #fIcon
Cs0 f3 actionDecl 'ch.ivy.addon.portalkit.component.CaseWidget.CaseWidgetData out;
' #txt
Cs0 f3 actionTable 'out=in;
' #txt
Cs0 f3 actionCode 'import ch.ivy.addon.portalkit.service.FilterService;

if ("".equals(in.filteringKeyword)) {
	in.filteredCases = new List();
	in.filteredCases.addAll(in.cases);
} else {
	FilterService service = new FilterService(in.filteringKeyword);
	in.filteredCases = service.filterCases(in.cases);
}
' #txt
Cs0 f3 type ch.ivy.addon.portalkit.component.CaseWidget.CaseWidgetData #txt
Cs0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>filter cases 
based on keyword (if any)</name>
        <nameStyle>39,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Cs0 f3 270 244 36 24 19 15 #rect
Cs0 f3 @|StepIcon #fIcon
Cs0 f4 expr out #txt
Cs0 f4 75 256 270 256 #arcP
Cs0 f5 expr out #txt
Cs0 f5 306 256 525 256 #arcP
>Proto Cs0 .type ch.ivy.addon.portalkit.component.CaseWidget.CaseWidgetData #txt
>Proto Cs0 .processKind HTML_DIALOG #txt
>Proto Cs0 -8 -8 16 16 16 26 #rect
>Proto Cs0 '' #fIcon
Cs0 f0 mainOut f2 tail #connect
Cs0 f2 head f1 mainIn #connect
Cs0 f6 mainOut f10 tail #connect
Cs0 f10 head f9 mainIn #connect
Cs0 f9 mainOut f8 tail #connect
Cs0 f8 head f7 mainIn #connect
Cs0 f11 mainOut f4 tail #connect
Cs0 f4 head f3 mainIn #connect
Cs0 f3 mainOut f5 tail #connect
Cs0 f5 head f12 mainIn #connect
