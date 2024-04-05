package com.axonivy.portal.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import org.apache.commons.lang3.StringUtils;

import com.axonivy.portal.components.util.FacesMessageUtils;

import ch.ivy.addon.portalkit.support.HtmlParser;
import ch.ivyteam.ivy.environment.Ivy;

@FacesValidator(value = "newsEditorValidator")
public class NewsEditorValidator implements Validator {

  private static final int MAX_NEWS_CONTENT_LENGTH = 1000;

  @Override
  public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
    if (value == null) {
      return;
    }
    String content = (String) value;
    content = HtmlParser.extractTextFromHtml(content);
    if (StringUtils.isNoneBlank(content)) {
      if (content.length() > MAX_NEWS_CONTENT_LENGTH) {
        var message = FacesMessageUtils.sanitizedMessage(FacesMessage.SEVERITY_ERROR,
            Ivy.cms().co("/Dialogs/com/axonivy/portal/dashboard/component/NewsWidgetConfiguration/NewsContentMaxLengthMessage"),
            "");
        FacesContext.getCurrentInstance().addMessage(null, message);
        FacesContext.getCurrentInstance().validationFailed();
      }
    }
  }
}
