[Ivy]
16E81ED19D184FCE 7.5.0 #module
>Proto >Proto Collection #zClass
Ms0 ModenaElementsProcess Big #zClass
Ms0 RD #cInfo
Ms0 #process
Ms0 @TextInP .type .type #zField
Ms0 @TextInP .processKind .processKind #zField
Ms0 @TextInP .xml .xml #zField
Ms0 @TextInP .responsibility .responsibility #zField
Ms0 @UdInit f0 '' #zField
Ms0 @UdProcessEnd f1 '' #zField
Ms0 @UdEvent f3 '' #zField
Ms0 @UdExitEnd f4 '' #zField
Ms0 @PushWFArc f5 '' #zField
Ms0 @GridStep f6 '' #zField
Ms0 @PushWFArc f7 '' #zField
Ms0 @PushWFArc f2 '' #zField
Ms0 @UdEvent f8 '' #zField
Ms0 @GridStep f9 '' #zField
Ms0 @PushWFArc f10 '' #zField
>Proto Ms0 Ms0 ModenaElementsProcess #zField
Ms0 f0 guid 16E81ED1A1CDA754 #txt
Ms0 f0 method start() #txt
Ms0 f0 inParameterDecl '<> param;' #txt
Ms0 f0 outParameterDecl '<> result;' #txt
Ms0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start()</name>
    </language>
</elementInfo>
' #txt
Ms0 f0 83 51 26 26 -15 15 #rect
Ms0 f0 @|UdInitIcon #fIcon
Ms0 f1 403 51 26 26 0 12 #rect
Ms0 f1 @|UdProcessEndIcon #fIcon
Ms0 f3 guid 16E81ED1A25A1B24 #txt
Ms0 f3 actionTable 'out=in;
' #txt
Ms0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>close</name>
    </language>
</elementInfo>
' #txt
Ms0 f3 83 147 26 26 -14 15 #rect
Ms0 f3 @|UdEventIcon #fIcon
Ms0 f4 211 147 26 26 0 12 #rect
Ms0 f4 @|UdExitEndIcon #fIcon
Ms0 f5 109 160 211 160 #arcP
Ms0 f6 actionTable 'out=in;
' #txt
Ms0 f6 actionCode 'import ch.ivyteam.ivy.project.portal.examples.showcase.Person;



List<Person> listSource = new List();
Person person = new Person();
person.name = "Stalder";
person.firstname = "Renato";

listSource.add(person);

person = new Person();
person.name = "Bütler";
person.firstname = "Bruno";
listSource.add(person);

person = new Person();
person.name = "Weiss";
person.firstname = "Reto";
listSource.add(person);

person = new Person();
person.name = "Hürlimann";
person.firstname = "Caty";
listSource.add(person);

person = new Person();
person.name = "Kis";
person.firstname = "Tamas";
listSource.add(person);

person = new Person();
person.name = "Dänzer";
person.firstname = "Michael";
listSource.add(person);

in.persons.source = listSource;

in.cities.add("London");
in.cities.add("Miami");
in.cities.add("Berlin");
in.cities.add("Paris");
in.cities.add("Rome");
' #txt
Ms0 f6 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Init</name>
    </language>
</elementInfo>
' #txt
Ms0 f6 184 42 112 44 -8 -8 #rect
Ms0 f6 @|StepIcon #fIcon
Ms0 f7 109 64 184 64 #arcP
Ms0 f2 296 64 403 64 #arcP
Ms0 f8 guid 16EA73AFC2B378BC #txt
Ms0 f8 actionTable 'out=in;
' #txt
Ms0 f8 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>cancel</name>
    </language>
</elementInfo>
' #txt
Ms0 f8 83 243 26 26 -14 15 #rect
Ms0 f8 @|UdEventIcon #fIcon
Ms0 f9 actionTable 'out=in;
' #txt
Ms0 f9 actionCode 'import ch.ivy.addon.portal.generic.navigation.PortalNavigator;
PortalNavigator navigator =new PortalNavigator();
navigator.navigateToPortalEndPageInFrame();' #txt
Ms0 f9 168 234 112 44 0 -8 #rect
Ms0 f9 @|StepIcon #fIcon
Ms0 f10 109 256 168 256 #arcP
>Proto Ms0 .type ch.ivyteam.ivy.project.portal.examples.testdata.ModenaElements.ModenaElementsData #txt
>Proto Ms0 .processKind HTML_DIALOG #txt
>Proto Ms0 -8 -8 16 16 16 26 #rect
>Proto Ms0 '' #fIcon
Ms0 f3 mainOut f5 tail #connect
Ms0 f5 head f4 mainIn #connect
Ms0 f0 mainOut f7 tail #connect
Ms0 f7 head f6 mainIn #connect
Ms0 f6 mainOut f2 tail #connect
Ms0 f2 head f1 mainIn #connect
Ms0 f8 mainOut f10 tail #connect
Ms0 f10 head f9 mainIn #connect
