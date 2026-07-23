package com.axonivy.portal.validator;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.validator.FacesValidator;
import jakarta.faces.validator.Validator;
import jakarta.faces.validator.ValidatorException;

import org.apache.commons.lang3.StringUtils;

import com.axonivy.portal.components.util.FacesMessageUtils;

import ch.ivy.addon.portalkit.support.HtmlParser;
import ch.ivyteam.ivy.environment.Ivy;

@FacesValidator(value = "newsEditorValidator")
public class NewsEditorValidator implements Validator<Object> {

  private static final int MAX_NEWS_CONTENT_LENGTH = 1000;

  @Override
  public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
    if (value == null) {
      return;
    }
    String content = (String) value;
    content = HtmlParser.extractTextFromHtml(content);
    if (StringUtils.isNoneBlank(content) && content.length() > MAX_NEWS_CONTENT_LENGTH) {
      var message = FacesMessageUtils.sanitizedMessage(FacesMessage.SEVERITY_ERROR,
          Ivy.cms().co("/Dialogs/com/axonivy/portal/dashboard/component/NewsWidgetConfiguration/NewsContentMaxLengthMessage"),
          "");
      FacesContext.getCurrentInstance().addMessage(null, message);
      FacesContext.getCurrentInstance().validationFailed();
    }
  }
}
