var onKeyPressForm = function(errorMessageId, loginCommandId) {
  if (event.keyCode == 13) {
    $(PrimeFaces.escapeClientId(loginCommandId)).click();
    return false;
  }
  
  $(PrimeFaces.escapeClientId(errorMessageId)).hide();
};