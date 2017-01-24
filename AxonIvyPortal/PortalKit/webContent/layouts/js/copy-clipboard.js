$(document).ready(function() {
	ZeroClipboard.config({
		swfPath : flashFileDirectory
	});
	
	var clipboardClient = new ZeroClipboard($('#copy-clipboard'));
	clipboardClient.on('ready', function() {
		$('#copy-clipboard').attr('data-clipboard-target', "task-details-url");
		clipboardClient.on('copy', function(event) {
			var clipboard = event.clipboardData;
			var targetElement = event.relatedTarget;
			clipboard.setData('text/plain', targetElement.value);
			PF('clipboard-growl').renderMessage({"summary":copiedToClipboardMessage, "severity":"info"});
		});
	});

	clipboardClient.on('error', function() {
		$("#task-details-url-label").text(copyHintMessage);
		$("#copy-clipboard").on("click", function() {
			PF("copy-clipboard-panel").show();
			$('#task-details-url').select();
		});
		ZeroClipboard.destroy();
	});
});