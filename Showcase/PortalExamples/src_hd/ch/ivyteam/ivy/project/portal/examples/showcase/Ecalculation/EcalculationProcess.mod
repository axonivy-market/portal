[Ivy]
169C7843875E0B79 3.26 #module
>Proto >Proto Collection #zClass
Es0 EcalculationProcess Big #zClass
Es0 RD #cInfo
Es0 #process
Es0 @TextInP .ui2RdDataAction .ui2RdDataAction #zField
Es0 @TextInP .rdData2UIAction .rdData2UIAction #zField
Es0 @TextInP .resExport .resExport #zField
Es0 @TextInP .type .type #zField
Es0 @TextInP .processKind .processKind #zField
Es0 @AnnotationInP-0n ai ai #zField
Es0 @MessageFlowInP-0n messageIn messageIn #zField
Es0 @MessageFlowOutP-0n messageOut messageOut #zField
Es0 @TextInP .xml .xml #zField
Es0 @TextInP .responsibility .responsibility #zField
Es0 @RichDialogInitStart f0 '' #zField
Es0 @RichDialogProcessStart f3 '' #zField
Es0 @GridStep f6 '' #zField
Es0 @RichDialogEnd f4 '' #zField
Es0 @RichDialogProcessEnd f1 '' #zField
Es0 @PushWFArc f5 '' #zField
Es0 @PushWFArc f7 '' #zField
Es0 @PushWFArc f2 '' #zField
>Proto Es0 Es0 EcalculationProcess #zField
Es0 f0 guid 169C784B8FCB9424 #txt
Es0 f0 type ch.ivyteam.ivy.project.portal.examples.showcase.Ecalculation.EcalculationData #txt
Es0 f0 method start() #txt
Es0 f0 disableUIEvents true #txt
Es0 f0 inParameterDecl 'ch.ivyteam.ivy.project.portal.examples.showcase.Ecalculation.EcalculationData out;
' #txt
Es0 f0 outParameterDecl '<> result;
' #txt
Es0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start()</name>
    </language>
</elementInfo>
' #txt
Es0 f0 51 51 26 26 -16 15 #rect
Es0 f0 @|RichDialogInitStartIcon #fIcon
Es0 f3 guid 169C784B8FC6C1A2 #txt
Es0 f3 type ch.ivyteam.ivy.project.portal.examples.showcase.Ecalculation.EcalculationData #txt
Es0 f3 actionDecl 'ch.ivyteam.ivy.project.portal.examples.showcase.Ecalculation.EcalculationData out;
' #txt
Es0 f3 actionTable 'out=in;
' #txt
Es0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>close</name>
    </language>
</elementInfo>
' #txt
Es0 f3 51 147 26 26 -15 12 #rect
Es0 f3 @|RichDialogProcessStartIcon #fIcon
Es0 f6 actionDecl 'ch.ivyteam.ivy.project.portal.examples.showcase.Ecalculation.EcalculationData out;
' #txt
Es0 f6 actionTable 'out=in;
' #txt
Es0 f6 actionCode 'import ch.ivyteam.ivy.project.portal.examples.showcase.ShowcaseEntity2;
import ch.ivyteam.ivy.project.portal.examples.showcase.ShowcaseEntity1;


for (int i = 0; i < 20; i++) {
	ShowcaseEntity1 entity1 = new ShowcaseEntity1();
	entity1.address = "Neubaugasse 43 1070 Vienna";
	entity1.code = "00" + i + "-6" + i+2 + "-1000";
	entity1.name = "Relation " + i;
	entity1.number = "" + i;
	in.table1.add(entity1);
	in.table2.add(entity1);
}

for (int i = 0; i < 20; i++) {
	ShowcaseEntity2 entity2 = new ShowcaseEntity2();
	entity2.endDate = "9/6/2018";
	entity2.startDate = "9/6/2018";
	entity2.objectNumber = "" + i;
	entity2.objectName = "Object " + i;
	entity2.type = "type " + i;
	in.table3.add(entity2);
}

in.steps.add("IST-Kalkulation");
in.steps.add("TEST-Kalkulation");
in.steps.add("Fix-Kalkulation");' #txt
Es0 f6 type ch.ivyteam.ivy.project.portal.examples.showcase.Ecalculation.EcalculationData #txt
Es0 f6 136 42 112 44 0 -8 #rect
Es0 f6 @|StepIcon #fIcon
Es0 f4 type ch.ivyteam.ivy.project.portal.examples.showcase.Ecalculation.EcalculationData #txt
Es0 f4 guid 169BE80C5073F16E #txt
Es0 f4 179 147 26 26 0 12 #rect
Es0 f4 @|RichDialogEndIcon #fIcon
Es0 f1 type ch.ivyteam.ivy.project.portal.examples.showcase.Ecalculation.EcalculationData #txt
Es0 f1 307 51 26 26 0 12 #rect
Es0 f1 @|RichDialogProcessEndIcon #fIcon
Es0 f5 expr out #txt
Es0 f5 77 160 179 160 #arcP
Es0 f7 expr out #txt
Es0 f7 77 64 136 64 #arcP
Es0 f2 expr out #txt
Es0 f2 248 64 307 64 #arcP
>Proto Es0 .type ch.ivyteam.ivy.project.portal.examples.showcase.Ecalculation.EcalculationData #txt
>Proto Es0 .processKind HTML_DIALOG #txt
>Proto Es0 -8 -8 16 16 16 26 #rect
>Proto Es0 '' #fIcon
Es0 f3 mainOut f5 tail #connect
Es0 f5 head f4 mainIn #connect
Es0 f0 mainOut f7 tail #connect
Es0 f7 head f6 mainIn #connect
Es0 f6 mainOut f2 tail #connect
Es0 f2 head f1 mainIn #connect
