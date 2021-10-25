[Ivy]
17B2F3DF898D03CF 9.3.0 #module
>Proto >Proto Collection #zClass
Is0 InvestmentListProcess Big #zClass
Is0 RD #cInfo
Is0 #process
Is0 @AnnotationInP-0n ai ai #zField
Is0 @TextInP .type .type #zField
Is0 @TextInP .processKind .processKind #zField
Is0 @TextInP .xml .xml #zField
Is0 @TextInP .responsibility .responsibility #zField
Is0 @UdInit f0 '' #zField
Is0 @UdProcessEnd f1 '' #zField
Is0 @UdEvent f3 '' #zField
Is0 @UdExitEnd f4 '' #zField
Is0 @PushWFArc f5 '' #zField
Is0 @GridStep f6 '' #zField
Is0 @PushWFArc f7 '' #zField
Is0 @PushWFArc f2 '' #zField
>Proto Is0 Is0 InvestmentListProcess #zField
Is0 f0 guid 17B2F3DF8A22ADB7 #txt
Is0 f0 method start(String,String,String,String) #txt
Is0 f0 inParameterDecl '<String startFrom,String startTo,String user,String email> param;' #txt
Is0 f0 inParameterMapAction 'out.email=param.email;
out.name=param.user;
out.startFrom=ch.ivy.addon.portalkit.util.Dates.parse(param.startFrom);
out.startTo=ch.ivy.addon.portalkit.util.Dates.parse(param.startTo);
' #txt
Is0 f0 outParameterDecl '<> result;' #txt
Is0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start(String,String,String,String)</name>
    </language>
</elementInfo>
' #txt
Is0 f0 83 51 26 26 -54 26 #rect
Is0 f1 339 51 26 26 0 12 #rect
Is0 f3 guid 17B2F3DF8AF6FC3D #txt
Is0 f3 actionTable 'out=in;
' #txt
Is0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>close</name>
    </language>
</elementInfo>
' #txt
Is0 f3 83 147 26 26 -15 15 #rect
Is0 f4 211 147 26 26 0 12 #rect
Is0 f5 109 160 211 160 #arcP
Is0 f6 actionTable 'out=in;
' #txt
Is0 f6 actionCode 'import com.axonivy.portal.developerexamples.Investment;
Investment investment1 = new com.axonivy.portal.developerexamples.Investment();
investment1.setId("001");
investment1.setProfit("1,000$$");
investment1.setProfitPercentage("10%");
investment1.setStartDate(new Date("2021-11-05"));
investment1.setInvestedMoney("10,000$$");

Investment investment2 = new com.axonivy.portal.developerexamples.Investment();
investment2.setId("002");
investment2.setProfit("3,500$$");
investment2.setProfitPercentage("7%");
investment2.setStartDate(new Date("2021-07-05"));
investment2.setInvestedMoney("50,000$$");

Investment investment3 = new com.axonivy.portal.developerexamples.Investment();
investment3.setId("003");
investment3.setProfit("157.5$$");
investment3.setProfitPercentage("1.5%");
investment3.setStartDate(new Date("2021-08-04"));
investment3.setInvestedMoney("10,500$$");

Investment investment4 = new com.axonivy.portal.developerexamples.Investment();
investment4.setId("004");
investment4.setProfit("1,500$$");
investment4.setProfitPercentage("15%");
investment4.setStartDate(new Date("2021-01-15"));
investment4.setInvestedMoney("10,000$$");

in.investments.add(investment1);
in.investments.add(investment2);
in.investments.add(investment3);
in.investments.add(investment4);' #txt
Is0 f6 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>Initialize data</name>
    </language>
</elementInfo>
' #txt
Is0 f6 168 42 112 44 -35 -8 #rect
Is0 f7 109 64 168 64 #arcP
Is0 f2 280 64 339 64 #arcP
>Proto Is0 .type com.axonivy.portal.developerexamples.testdata.InvestmentList.InvestmentListData #txt
>Proto Is0 .processKind HTML_DIALOG #txt
>Proto Is0 -8 -8 16 16 16 26 #rect
Is0 f3 mainOut f5 tail #connect
Is0 f5 head f4 mainIn #connect
Is0 f0 mainOut f7 tail #connect
Is0 f7 head f6 mainIn #connect
Is0 f6 mainOut f2 tail #connect
Is0 f2 head f1 mainIn #connect
