[Ivy]
169C78AD20FEB2C2 7.5.0 #module
>Proto >Proto Collection #zClass
Ps0 PrimefacesDataTableProcess Big #zClass
Ps0 RD #cInfo
Ps0 #process
Ps0 @TextInP .type .type #zField
Ps0 @TextInP .processKind .processKind #zField
Ps0 @AnnotationInP-0n ai ai #zField
Ps0 @MessageFlowInP-0n messageIn messageIn #zField
Ps0 @MessageFlowOutP-0n messageOut messageOut #zField
Ps0 @TextInP .xml .xml #zField
Ps0 @TextInP .responsibility .responsibility #zField
Ps0 @GridStep f11 '' #zField
Ps0 @UdProcessEnd f9 '' #zField
Ps0 @GridStep f6 '' #zField
Ps0 @UdEvent f3 '' #zField
Ps0 @UdExitEnd f4 '' #zField
Ps0 @UdEvent f8 '' #zField
Ps0 @UdProcessEnd f1 '' #zField
Ps0 @UdInit f0 '' #zField
Ps0 @PushWFArc f7 '' #zField
Ps0 @PushWFArc f5 '' #zField
Ps0 @PushWFArc f2 '' #zField
Ps0 @PushWFArc f10 '' #zField
Ps0 @PushWFArc f12 '' #zField
Ps0 @GridStep f16 '' #zField
>Proto Ps0 Ps0 PrimefacesDataTableProcess #zField
Ps0 f11 actionTable 'out=in;
' #txt
Ps0 f11 actionCode 'import javax.faces.context.FacesContext;
import javax.faces.application.FacesMessage;

Boolean errmessage = FacesContext.getCurrentInstance().getMessageList().size() >= 1 ? true : false;

if(!errmessage)
{
	ivy.log.debug("Item updated: {0}", in.selectedScore);
}' #txt
Ps0 f11 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Log</name>
        <nameStyle>3,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ps0 f11 176 242 112 44 -10 -8 #rect
Ps0 f11 @|StepIcon #fIcon
Ps0 f9 347 251 26 26 0 12 #rect
Ps0 f9 @|UdProcessEndIcon #fIcon
Ps0 f6 actionTable 'out=in;
' #txt
Ps0 f6 actionCode 'import ch.ivyteam.ivy.project.portal.examples.showcase.Score;


// init list with some items
// init the score date with an ivy-Date which has no time. Important for the date filter in the DataTable
out.scores.add((new Score()).setId(100).setName("Beni").setPoints(2563).setDate(new Date()));
out.scores.add((new Score()).setId(110).setName("Achmed").setPoints(346).setDate(new Date()));
out.scores.add((new Score()).setId(221).setName("Cyril").setPoints(4654).setDate(new Date()));
out.scores.add((new Score()).setId(238).setName("Birgit").setPoints(6666).setDate(new Date()));
out.scores.add((new Score()).setId(340).setName("Maurice").setPoints(324).setDate(new Date()));
out.scores.add((new Score()).setId(450).setName("Daisy").setPoints(2679).setDate(new Date()));
out.scores.add((new Score()).setId(455).setName("Yvonne").setPoints(1324).setDate(new Date()));
out.scores.add((new Score()).setId(550).setName("Nadia").setPoints(2639).setDate(new Date()));
out.scores.add((new Score()).setId(634).setName("Mike").setPoints(9324).setDate(new Date()));
out.scores.add((new Score()).setId(650).setName("Peter").setPoints(2479).setDate(new Date()));
out.scores.add((new Score()).setId(700).setName("Charly").setPoints(3424).setDate(new Date()));
out.scores.add((new Score()).setId(850).setName("Tim").setPoints(2889).setDate(new Date()));

// init List data for table Cell edit
out.scoresList = out.scores;

// init list of names
for(Score scr : out.scores)
{
	out.names.add(scr.name);	
}' #txt
Ps0 f6 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>load Data</name>
        <nameStyle>9
</nameStyle>
    </language>
</elementInfo>
' #txt
Ps0 f6 178 50 112 44 -26 -8 #rect
Ps0 f6 @|StepIcon #fIcon
Ps0 f3 guid 169DD4CD81F37B33 #txt
Ps0 f3 actionTable 'out=in;
' #txt
Ps0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>close</name>
    </language>
</elementInfo>
' #txt
Ps0 f3 91 155 26 26 -15 12 #rect
Ps0 f3 @|UdEventIcon #fIcon
Ps0 f4 219 155 26 26 0 12 #rect
Ps0 f4 @|UdExitEndIcon #fIcon
Ps0 f8 guid 169DD4CD820AF17C #txt
Ps0 f8 actionTable 'out=in;
' #txt
Ps0 f8 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>save</name>
        <nameStyle>4,5,7
</nameStyle>
    </language>
</elementInfo>
' #txt
Ps0 f8 91 251 26 26 -13 15 #rect
Ps0 f8 @|UdEventIcon #fIcon
Ps0 f1 347 59 26 26 0 12 #rect
Ps0 f1 @|UdProcessEndIcon #fIcon
Ps0 f0 guid 169DD4CD820471FD #txt
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
Ps0 f0 91 59 26 26 -16 15 #rect
Ps0 f0 @|UdInitIcon #fIcon
Ps0 f7 expr out #txt
Ps0 f7 290 72 347 72 #arcP
Ps0 f5 expr out #txt
Ps0 f5 117 168 219 168 #arcP
Ps0 f2 expr out #txt
Ps0 f2 117 72 178 72 #arcP
Ps0 f10 expr out #txt
Ps0 f10 288 264 347 264 #arcP
Ps0 f12 expr out #txt
Ps0 f12 117 264 176 264 #arcP
Ps0 f16 actionTable 'out=in;
' #txt
Ps0 f16 actionCode 'import ch.ivyteam.ivy.project.portal.examples.showcase.Score;

// add new item to list
// init the score date with an ivy-Date which has no time. Important for the date filter in the DataTable
out.scores.add((new Score()).setId(999).setName("New Row").setPoints(2563).setDate(new Date()));

' #txt
Ps0 f16 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Add Data</name>
        <nameStyle>3,5
5,5
</nameStyle>
    </language>
</elementInfo>
' #txt
Ps0 f16 170 394 112 44 -25 -8 #rect
Ps0 f16 @|StepIcon #fIcon
>Proto Ps0 .type ch.ivyteam.ivy.project.portal.examples.showcase.PrimefacesDataTable.PrimefacesDataTableData #txt
>Proto Ps0 .processKind HTML_DIALOG #txt
>Proto Ps0 -8 -8 16 16 16 26 #rect
>Proto Ps0 '' #fIcon
Ps0 f3 mainOut f5 tail #connect
Ps0 f5 head f4 mainIn #connect
Ps0 f8 mainOut f12 tail #connect
Ps0 f12 head f11 mainIn #connect
Ps0 f11 mainOut f10 tail #connect
Ps0 f10 head f9 mainIn #connect
Ps0 f0 mainOut f2 tail #connect
Ps0 f2 head f6 mainIn #connect
Ps0 f6 mainOut f7 tail #connect
Ps0 f7 head f1 mainIn #connect
