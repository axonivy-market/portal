[Ivy]
1791D27754935B10 9.3.1 #module
>Proto >Proto Collection #zClass
Te0 TaskDetailsCustomWidgetExample Big #zClass
Te0 B #cInfo
Te0 #process
Te0 @AnnotationInP-0n ai ai #zField
Te0 @TextInP .type .type #zField
Te0 @TextInP .processKind .processKind #zField
Te0 @TextInP .xml .xml #zField
Te0 @TextInP .responsibility .responsibility #zField
Te0 @UserDialog f5 '' #zField
Te0 @EndTask f12 '' #zField
Te0 @EndTask f3 '' #zField
Te0 @StartRequest f4 '' #zField
Te0 @UserDialog f11 '' #zField
Te0 @TaskSwitch f8 '' #zField
Te0 @StartRequest f7 '' #zField
Te0 @EndTask f13 '' #zField
Te0 @GridStep f6 '' #zField
Te0 @UserDialog f10 '' #zField
Te0 @EndTask f14 '' #zField
Te0 @PushWFArc f9 '' #zField
Te0 @PushWFArc f15 '' #zField
Te0 @PushWFArc f16 '' #zField
Te0 @PushWFArc f17 '' #zField
Te0 @PushWFArc f19 '' #zField
Te0 @PushWFArc f18 '' #zField
Te0 @TkArc f20 '' #zField
Te0 @PushWFArc f21 '' #zField
Te0 @PushWFArc f22 '' #zField
>Proto Te0 Te0 TaskDetailsCustomWidgetExample #zField
Te0 f5 dialogId com.axonivy.portal.developerexamples.customization.InvoiceDetails #txt
Te0 f5 startMethod start(com.axonivy.portal.developerexamples.Invoice) #txt
Te0 f5 requestActionDecl '<com.axonivy.portal.developerexamples.Invoice invoice> param;' #txt
Te0 f5 requestMappingAction 'param.invoice=in.invoice;
' #txt
Te0 f5 responseMappingAction 'out=in;
' #txt
Te0 f5 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>InvoiceDetails</name>
    </language>
</elementInfo>
' #txt
Te0 f5 312 26 112 44 -38 -8 #rect
Te0 f12 513 97 30 30 0 15 #rect
Te0 f3 513 33 30 30 0 15 #rect
Te0 f4 outLink invoiceDetails.ivp #txt
Te0 f4 inParamDecl '<String invoiceId,String startedTaskId,String startedTaskCategory,String invoiceDescription> param;' #txt
Te0 f4 inParamTable 'out.invoiceDescription=param.invoiceDescription;
out.invoiceId=param.invoiceId;
out.taskCategory=param.startedTaskCategory;
out.taskId=param.startedTaskId;
' #txt
Te0 f4 requestEnabled true #txt
Te0 f4 triggerEnabled false #txt
Te0 f4 callSignature invoiceDetails(String,String,String,String) #txt
Te0 f4 startName 'Show invoice details' #txt
Te0 f4 caseData businessCase.attach=true #txt
Te0 f4 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>invoiceDetails.ivp</name>
    </language>
</elementInfo>
' #txt
Te0 f4 @C|.responsibility Everybody #txt
Te0 f4 65 33 30 30 -21 17 #rect
Te0 f11 dialogId com.axonivy.portal.developerexamples.customization.InvoiceDetails #txt
Te0 f11 startMethod start(com.axonivy.portal.developerexamples.Invoice) #txt
Te0 f11 requestActionDecl '<com.axonivy.portal.developerexamples.Invoice invoice> param;' #txt
Te0 f11 responseMappingAction 'out=in;
' #txt
Te0 f11 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>InvoiceDetails</name>
    </language>
</elementInfo>
' #txt
Te0 f11 312 186 112 44 -38 -8 #rect
Te0 f8 actionTable 'out=in1;
' #txt
Te0 f8 outLinks "TaskA.ivp","TaskB.ivp","TaskC.ivp" #txt
Te0 f8 taskData 'TaskB.CATEGORY=manage
TaskB.NAM=Manage Invoice
TaskB.customFields.STRING.invoiceDescription="Inventory invoice"
TaskA.CATEGORY=support
TaskA.NAM=Support Customer
TaskC.CATEGORY=system
TaskC.NAM=Proceed Invoice' #txt
Te0 f8 192 96 32 32 0 16 #rect
Te0 f7 outLink SalesManagement.ivp #txt
Te0 f7 inParamDecl '<> param;' #txt
Te0 f7 requestEnabled true #txt
Te0 f7 triggerEnabled false #txt
Te0 f7 callSignature SalesManagement() #txt
Te0 f7 startName 'Sales Management' #txt
Te0 f7 caseData businessCase.attach=true #txt
Te0 f7 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>SalesManagement.ivp</name>
    </language>
</elementInfo>
' #txt
Te0 f7 @C|.responsibility Everybody #txt
Te0 f7 65 97 30 30 -24 17 #rect
Te0 f13 513 193 30 30 0 15 #rect
Te0 f6 actionTable 'out=in;
' #txt
Te0 f6 actionCode 'import com.axonivy.portal.developerexamples.Customer;
import com.axonivy.portal.developerexamples.Invoice;
import com.axonivy.portal.developerexamples.Item;


Customer customer = new Customer();
customer.id = "00001";
customer.name = "Steve";
customer.type = "Normal";

in.invoice = new Invoice();
in.invoice.id = in.invoiceId;
in.invoice.description = in.invoiceDescription;
in.invoice.taskId = in.taskId;
in.invoice.taskCategory = in.taskCategory;
in.invoice.customer = customer;

Item item1 = new Item();
item1.id = "001";
item1.name = "Asus P1440FA-FA0674T i3";
item1.quantity = 2;
in.invoice.items.add(item1);

Item item2 = new Item();
item2.id = "002";
item2.name = "Lenovo Ideapad L340-15IRH 81LK00FAVN i5 ";
item2.quantity = 6;
in.invoice.items.add(item2);

Item item3 = new Item();
item3.id = "003";
item3.name = "ThinkPad X1 Carbon Gen 8 FHD";
item3.quantity = 1;
in.invoice.items.add(item3);

Item item4 = new Item();
item4.id = "004";
item4.name = "Lenovo ThinkPad X1 Carbon Gen 6";
item4.quantity = 6;
in.invoice.items.add(item4);' #txt
Te0 f6 152 26 112 44 0 -8 #rect
Te0 f10 dialogId com.axonivy.portal.developerexamples.customization.InvoiceDetails #txt
Te0 f10 startMethod start(com.axonivy.portal.developerexamples.Invoice) #txt
Te0 f10 requestActionDecl '<com.axonivy.portal.developerexamples.Invoice invoice> param;' #txt
Te0 f10 responseMappingAction 'out=in;
' #txt
Te0 f10 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>InvoiceDetails</name>
    </language>
</elementInfo>
' #txt
Te0 f10 312 90 112 44 -38 -8 #rect
Te0 f14 513 289 30 30 0 15 #rect
Te0 f9 264 48 312 48 #arcP
Te0 f15 95 48 152 48 #arcP
Te0 f16 expr data #txt
Te0 f16 outCond ivp=="TaskB.ivp" #txt
Te0 f16 208 128 312 208 #arcP
Te0 f16 1 208 208 #addKink
Te0 f16 1 0.21965692365617775 0 0 #arcLabel
Te0 f17 expr data #txt
Te0 f17 outCond ivp=="TaskA.ivp" #txt
Te0 f17 224 112 312 112 #arcP
Te0 f19 expr data #txt
Te0 f19 outCond ivp=="TaskC.ivp" #txt
Te0 f19 208 128 513 304 #arcP
Te0 f19 1 208 304 #addKink
Te0 f19 0 0.5080371107065694 0 0 #arcLabel
Te0 f18 424 112 513 112 #arcP
Te0 f20 var in1 #txt
Te0 f20 95 112 192 112 #arcP
Te0 f21 424 208 513 208 #arcP
Te0 f22 424 48 513 48 #arcP
>Proto Te0 .type com.axonivy.portal.developerexamples.TaskDetailsCustomWidgetExampleData #txt
>Proto Te0 .processKind NORMAL #txt
>Proto Te0 0 0 32 24 18 0 #rect
>Proto Te0 @|BIcon #fIcon
Te0 f4 mainOut f15 tail #connect
Te0 f15 head f6 mainIn #connect
Te0 f6 mainOut f9 tail #connect
Te0 f9 head f5 mainIn #connect
Te0 f5 mainOut f22 tail #connect
Te0 f22 head f3 mainIn #connect
Te0 f7 mainOut f20 tail #connect
Te0 f20 head f8 in #connect
Te0 f8 out f17 tail #connect
Te0 f17 head f10 mainIn #connect
Te0 f8 out f16 tail #connect
Te0 f16 head f11 mainIn #connect
Te0 f10 mainOut f18 tail #connect
Te0 f18 head f12 mainIn #connect
Te0 f11 mainOut f21 tail #connect
Te0 f21 head f13 mainIn #connect
Te0 f8 out f19 tail #connect
Te0 f19 head f14 mainIn #connect
