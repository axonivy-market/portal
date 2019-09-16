function ExpressDynaform() {
    var inputTextLabel = $("label[for$='hidden-input-field-text']");
    if (inputTextLabel) {
	inputTextLabel.attr('style', 'visibility: hidden !important');
    }
}