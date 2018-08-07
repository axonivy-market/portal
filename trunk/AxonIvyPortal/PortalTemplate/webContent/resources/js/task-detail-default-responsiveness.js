$(function() {
	var taskListLargeScreen = new TaskListLargeScreenHandler();
	var taskListMediumScreen = new TaskListMediumScreenHandler();
	var taskListSmallScreen = new TaskListSmallScreenHandler();
	var responsiveToolkit = ResponsiveToolkit(taskListLargeScreen, taskListMediumScreen, taskListSmallScreen);
	responsiveToolkit.updateLayoutWithoutAnimation();
})