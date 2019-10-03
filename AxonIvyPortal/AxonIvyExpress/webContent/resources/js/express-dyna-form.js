/* Adapt Serenity style for DynaForm
 * This function removes the label container row of dynamic form (p:dynaForm)
 * Make attribute 'for' of the label dynaForm to a hidden-input 
 * Then find this label and hide them on UI
 * Add a class as 'md-inputfield' on 'p:dynaForm type="InputTextField"' so adapt Serenity style
 */
function ExpressDynaform() {
    // Find the label row on DOM - the label of dynaForm will be auto generated and displayed as a row on table, we need to hide it
    var inputTextLabel = $("label[for$='hidden-input-field-text']");
    if (inputTextLabel.length > 0) {
      inputTextLabel.attr('style', 'visibility: hidden !important');
    }
}