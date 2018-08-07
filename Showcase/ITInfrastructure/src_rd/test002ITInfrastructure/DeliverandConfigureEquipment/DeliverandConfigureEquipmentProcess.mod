[Ivy]
[>Created: Thu Jul 24 13:14:46 CEST 2014]
147681498E75D5F8 3.17 #module
>Proto >Proto Collection #zClass
Ds0 DeliverandConfigureEquipmentProcess Big #zClass
Ds0 RD #cInfo
Ds0 #process
Ds0 @TextInP .ui2RdDataAction .ui2RdDataAction #zField
Ds0 @TextInP .rdData2UIAction .rdData2UIAction #zField
Ds0 @TextInP .resExport .resExport #zField
Ds0 @TextInP .type .type #zField
Ds0 @TextInP .processKind .processKind #zField
Ds0 @AnnotationInP-0n ai ai #zField
Ds0 @MessageFlowInP-0n messageIn messageIn #zField
Ds0 @MessageFlowOutP-0n messageOut messageOut #zField
Ds0 @TextInP .xml .xml #zField
Ds0 @TextInP .responsibility .responsibility #zField
Ds0 @RichDialogInitStart f0 '' #zField
Ds0 @RichDialogProcessEnd f1 '' #zField
Ds0 @PushWFArc f2 '' #zField
>Proto Ds0 Ds0 DeliverandConfigureEquipmentProcess #zField
Ds0 f0 guid 14768149918D876D #txt
Ds0 f0 type test002ITInfrastructure.DeliverandConfigureEquipment.DeliverandConfigureEquipmentData #txt
Ds0 f0 method start(String,String,String,Number,Boolean) #txt
Ds0 f0 disableUIEvents true #txt
Ds0 f0 inParameterDecl 'ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent methodEvent = event as ch.ivyteam.ivy.richdialog.exec.RdMethodCallEvent;
<java.lang.String Mitarbeiter,java.lang.String Manager,java.lang.String Equipment,java.lang.Number Durchwahl,java.lang.Boolean Approved> param = methodEvent.getInputArguments();
' #txt
Ds0 f0 inParameterMapAction 'out.Mitarbeiter=param.Mitarbeiter;
out.Manager=param.Manager;
out.Equipment=param.Equipment;
out.Durchwahl=param.Durchwahl;
out.Approved=param.Approved;
' #txt
Ds0 f0 outParameterDecl '<java.lang.String Mitarbeiter,java.lang.String Manager,java.lang.String Equipment,java.lang.Number Durchwahl,java.lang.Boolean Delivered,java.lang.String Lieferant> result;
' #txt
Ds0 f0 outParameterMapAction 'result.Mitarbeiter=in.Mitarbeiter;
result.Manager=in.Manager;
result.Equipment=in.Equipment;
result.Durchwahl=in.Durchwahl;
result.Delivered=in.Delivered;
result.Lieferant=in.Lieferant;
' #txt
Ds0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start(String,String,String,Number,Boolean)</name>
    </language>
</elementInfo>
' #txt
Ds0 f0 51 51 26 26 -115 15 #rect
Ds0 f0 @|RichDialogInitStartIcon #fIcon
Ds0 f0 -1|-1|-9671572 #nodeStyle
Ds0 f1 type test002ITInfrastructure.DeliverandConfigureEquipment.DeliverandConfigureEquipmentData #txt
Ds0 f1 243 51 26 26 0 12 #rect
Ds0 f1 @|RichDialogProcessEndIcon #fIcon
Ds0 f1 -1|-1|-9671572 #nodeStyle
Ds0 f2 expr out #txt
Ds0 f2 77 64 243 64 #arcP
>Proto Ds0 .type test002ITInfrastructure.DeliverandConfigureEquipment.DeliverandConfigureEquipmentData #txt
>Proto Ds0 .processKind RICH_DIALOG #txt
>Proto Ds0 -8 -8 16 16 16 26 #rect
>Proto Ds0 '' #fIcon
Ds0 f0 mainOut f2 tail #connect
Ds0 f2 head f1 mainIn #connect
