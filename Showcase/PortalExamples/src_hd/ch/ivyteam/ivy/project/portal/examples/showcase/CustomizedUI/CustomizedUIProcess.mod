[Ivy]
169D807F713AC26B 7.5.0 #module
>Proto >Proto Collection #zClass
Cs0 CustomizedUIProcess Big #zClass
Cs0 RD #cInfo
Cs0 #process
Cs0 @TextInP .type .type #zField
Cs0 @TextInP .processKind .processKind #zField
Cs0 @AnnotationInP-0n ai ai #zField
Cs0 @MessageFlowInP-0n messageIn messageIn #zField
Cs0 @MessageFlowOutP-0n messageOut messageOut #zField
Cs0 @TextInP .xml .xml #zField
Cs0 @TextInP .responsibility .responsibility #zField
Cs0 @UdInit f0 '' #zField
Cs0 @UdProcessEnd f1 '' #zField
Cs0 @UdEvent f3 '' #zField
Cs0 @UdExitEnd f4 '' #zField
Cs0 @PushWFArc f5 '' #zField
Cs0 @GridStep f6 '' #zField
Cs0 @PushWFArc f7 '' #zField
Cs0 @PushWFArc f2 '' #zField
>Proto Cs0 Cs0 CustomizedUIProcess #zField
Cs0 f0 guid 169D807F74964FAF #txt
Cs0 f0 method start() #txt
Cs0 f0 inParameterDecl '<> param;' #txt
Cs0 f0 outParameterDecl '<> result;' #txt
Cs0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start()</name>
    </language>
</elementInfo>
' #txt
Cs0 f0 83 51 26 26 -16 15 #rect
Cs0 f0 @|UdInitIcon #fIcon
Cs0 f1 339 51 26 26 0 12 #rect
Cs0 f1 @|UdProcessEndIcon #fIcon
Cs0 f3 guid 169D807F78F5AD94 #txt
Cs0 f3 actionTable 'out=in;
' #txt
Cs0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>close</name>
    </language>
</elementInfo>
' #txt
Cs0 f3 83 147 26 26 -15 12 #rect
Cs0 f3 @|UdEventIcon #fIcon
Cs0 f4 339 147 26 26 0 12 #rect
Cs0 f4 @|UdExitEndIcon #fIcon
Cs0 f5 expr out #txt
Cs0 f5 109 160 339 160 #arcP
Cs0 f6 actionTable 'out=in;
' #txt
Cs0 f6 actionCode 'import ch.ivyteam.ivy.project.portal.examples.showcase.ShowcaseEntity2;
import ch.ivyteam.ivy.project.portal.examples.showcase.ShowcaseEntity1;


for (int i = 0; i < 20; i++) {
	ShowcaseEntity1 entity1 = new ShowcaseEntity1();
	entity1.address = "4810  Murphy Court, Riverside, CA";
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

in.steps.add("Create report");
in.steps.add("Approval");
in.steps.add("Finalize report");' #txt
Cs0 f6 160 42 112 44 0 -8 #rect
Cs0 f6 @|StepIcon #fIcon
Cs0 f7 expr out #txt
Cs0 f7 109 64 160 64 #arcP
Cs0 f2 expr out #txt
Cs0 f2 272 64 339 64 #arcP
>Proto Cs0 .type ch.ivyteam.ivy.project.portal.examples.showcase.CustomizedUI.CustomizedUIData #txt
>Proto Cs0 .processKind HTML_DIALOG #txt
>Proto Cs0 -8 -8 16 16 16 26 #rect
>Proto Cs0 '' #fIcon
Cs0 f3 mainOut f5 tail #connect
Cs0 f5 head f4 mainIn #connect
Cs0 f0 mainOut f7 tail #connect
Cs0 f7 head f6 mainIn #connect
Cs0 f6 mainOut f2 tail #connect
Cs0 f2 head f1 mainIn #connect
