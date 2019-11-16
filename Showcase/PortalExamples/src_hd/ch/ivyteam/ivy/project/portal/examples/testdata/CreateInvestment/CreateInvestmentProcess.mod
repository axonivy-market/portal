[Ivy]
16E5DBBE43239E02 7.5.0 #module
>Proto >Proto Collection #zClass
Cs0 CreateInvestmentProcess Big #zClass
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
Cs0 @GridStep f6 '' #zField
Cs0 @PushWFArc f7 '' #zField
Cs0 @PushWFArc f2 '' #zField
Cs0 @PushWFArc f5 '' #zField
Cs0 @UdExitEnd f4 '' #zField
>Proto Cs0 Cs0 CreateInvestmentProcess #zField
Cs0 f0 guid 16B3F8C452E79D83 #txt
Cs0 f0 method start() #txt
Cs0 f0 inParameterDecl '<> param;' #txt
Cs0 f0 outParameterDecl '<ch.ivyteam.ivy.project.portal.examples.Investment investment> result;' #txt
Cs0 f0 outParameterMapAction 'result.investment=in.selectedInvestment;
' #txt
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
Cs0 f3 guid 16B3F8C455B51909 #txt
Cs0 f3 actionTable 'out=in;
' #txt
Cs0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>close</name>
    </language>
</elementInfo>
' #txt
Cs0 f3 83 195 26 26 -15 12 #rect
Cs0 f3 @|UdEventIcon #fIcon
Cs0 f6 actionTable 'out=in;
' #txt
Cs0 f6 actionCode 'import ch.ivyteam.ivy.project.portal.examples.Company;

Company company1 = new Company();
company1.id = "0001";
company1.name = "Axon Active";
company1.description = "Our Agile software development process is designed based on the fundamentals of Agile methodology.";


in.selectedInvestment.id = "00001";
in.selectedInvestment.company = company1;' #txt
Cs0 f6 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Init fake data</name>
    </language>
</elementInfo>
' #txt
Cs0 f6 168 42 112 44 -34 -8 #rect
Cs0 f6 @|StepIcon #fIcon
Cs0 f7 expr out #txt
Cs0 f7 109 64 168 64 #arcP
Cs0 f2 expr out #txt
Cs0 f2 280 64 339 64 #arcP
Cs0 f5 expr out #txt
Cs0 f5 109 208 339 208 #arcP
Cs0 f4 339 195 26 26 0 12 #rect
Cs0 f4 @|UdExitEndIcon #fIcon
>Proto Cs0 .type ch.ivyteam.ivy.project.portal.examples.testdata.CreateInvestment.CreateInvestmentData #txt
>Proto Cs0 .processKind HTML_DIALOG #txt
>Proto Cs0 -8 -8 16 16 16 26 #rect
>Proto Cs0 '' #fIcon
Cs0 f3 mainOut f5 tail #connect
Cs0 f5 head f4 mainIn #connect
Cs0 f0 mainOut f7 tail #connect
Cs0 f7 head f6 mainIn #connect
Cs0 f6 mainOut f2 tail #connect
Cs0 f2 head f1 mainIn #connect
