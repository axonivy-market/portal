$(function() {
  var simpleLargeScreen = new CaseListLargeScreenHandler();
  var simpleMediumScreen = new CaseListMediumScreenHandler();
  var simpleSmallScreen = new CaseListSmallScreenHandler();
  var responsiveToolkit = ResponsiveToolkit(simpleLargeScreen, simpleMediumScreen, simpleSmallScreen);
	responsiveToolkit.updateLayoutWithoutAnimation();
})