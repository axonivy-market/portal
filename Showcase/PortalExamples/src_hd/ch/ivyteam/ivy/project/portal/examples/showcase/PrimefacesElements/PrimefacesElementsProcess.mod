[Ivy]
169C7900CA1DBEF8 7.5.0 #module
>Proto >Proto Collection #zClass
Ps0 PrimefacesElementsProcess Big #zClass
Ps0 RD #cInfo
Ps0 #process
Ps0 @TextInP .type .type #zField
Ps0 @TextInP .processKind .processKind #zField
Ps0 @AnnotationInP-0n ai ai #zField
Ps0 @MessageFlowInP-0n messageIn messageIn #zField
Ps0 @MessageFlowOutP-0n messageOut messageOut #zField
Ps0 @TextInP .xml .xml #zField
Ps0 @TextInP .responsibility .responsibility #zField
Ps0 @UdProcessEnd f9 '' #zField
Ps0 @UdProcessEnd f8 '' #zField
Ps0 @UdEvent f10 '' #zField
Ps0 @UdExitEnd f4 '' #zField
Ps0 @UdEvent f3 '' #zField
Ps0 @UdInit f6 '' #zField
Ps0 @GridStep f1 '' #zField
Ps0 @PushWFArc f5 '' #zField
Ps0 @PushWFArc f0 '' #zField
Ps0 @PushWFArc f11 '' #zField
Ps0 @PushWFArc f2 '' #zField
>Proto Ps0 Ps0 PrimefacesElementsProcess #zField
Ps0 f9 531 115 26 26 0 12 #rect
Ps0 f9 @|UdProcessEndIcon #fIcon
Ps0 f9 -1|-1|-9671572 #nodeStyle
Ps0 f8 207 209 26 26 0 12 #rect
Ps0 f8 @|UdProcessEndIcon #fIcon
Ps0 f8 -1|-1|-9671572 #nodeStyle
Ps0 f10 guid 169C790C82463D89 #txt
Ps0 f10 actionTable 'out=in;
' #txt
Ps0 f10 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>send</name>
        <nameStyle>4,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ps0 f10 79 209 26 26 -14 12 #rect
Ps0 f10 @|UdEventIcon #fIcon
Ps0 f10 -1|-1|-9671572 #nodeStyle
Ps0 f4 211 51 26 26 0 12 #rect
Ps0 f4 @|UdExitEndIcon #fIcon
Ps0 f3 guid 169C790C824A369F #txt
Ps0 f3 actionTable 'out=in;
' #txt
Ps0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>close</name>
    </language>
</elementInfo>
' #txt
Ps0 f3 83 51 26 26 -15 12 #rect
Ps0 f3 @|UdEventIcon #fIcon
Ps0 f6 guid 169C790C82413383 #txt
Ps0 f6 method start() #txt
Ps0 f6 inParameterDecl '<> param;' #txt
Ps0 f6 outParameterDecl '<> result;' #txt
Ps0 f6 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start()</name>
    </language>
</elementInfo>
' #txt
Ps0 f6 79 113 26 26 -16 15 #rect
Ps0 f6 @|UdInitIcon #fIcon
Ps0 f6 -1|-1|-9671572 #nodeStyle
Ps0 f1 actionTable 'out=in;
' #txt
Ps0 f1 actionCode 'import ch.ivyteam.ivy.project.portal.examples.showcase.Person;



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
Ps0 f1 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Init</name>
    </language>
</elementInfo>
' #txt
Ps0 f1 256 106 112 44 -8 -8 #rect
Ps0 f1 @|StepIcon #fIcon
Ps0 f5 expr out #txt
Ps0 f5 109 64 211 64 #arcP
Ps0 f0 expr out #txt
Ps0 f0 368 128 531 128 #arcP
Ps0 f11 expr out #txt
Ps0 f11 105 222 207 222 #arcP
Ps0 f2 expr out #txt
Ps0 f2 104 126 256 128 #arcP
>Proto Ps0 .type ch.ivyteam.ivy.project.portal.examples.showcase.PrimefacesElements.PrimefacesElementsData #txt
>Proto Ps0 .processKind HTML_DIALOG #txt
>Proto Ps0 -8 -8 16 16 16 26 #rect
>Proto Ps0 '' #fIcon
Ps0 f3 mainOut f5 tail #connect
Ps0 f5 head f4 mainIn #connect
Ps0 f10 mainOut f11 tail #connect
Ps0 f11 head f8 mainIn #connect
Ps0 f6 mainOut f2 tail #connect
Ps0 f2 head f1 mainIn #connect
Ps0 f1 mainOut f0 tail #connect
Ps0 f0 head f9 mainIn #connect
