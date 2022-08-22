package ch.ivy.addon.portalkit.converter;

import javax.faces.convert.FacesConverter;

import org.apache.commons.lang3.StringUtils;

import ch.ivy.addon.portalkit.configuration.UserProcess;

@FacesConverter("userProcessConverter")
public class UserProcessConverter extends AbstractConverter<UserProcessConverter> {

  @Override
  protected Class<UserProcessConverter> getType() {
    return UserProcessConverter.class;
  }

  @Override
  protected boolean isEmptyObject(Object item) {
    if (item == null || (item instanceof UserProcess)) {
      return false;
    }
    var userProcess = (UserProcess) item;
    return StringUtils.isBlank(userProcess.getProcessName()) && StringUtils.isBlank(userProcess.getLink());
  }

  @Override
  protected UserProcess createNewObject(String selectedValue) {
    return new UserProcess(selectedValue, "");
  }
}
