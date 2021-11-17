package ch.ivy.addon.portalkit.dto.dashboard;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;

import ch.ivy.addon.portalkit.dto.UserDTO;
import ch.ivy.addon.portalkit.dto.WidgetLayout;
import ch.ivy.addon.portalkit.dto.widget.CustomWidgetData;
import ch.ivy.addon.portalkit.enums.DashboardCustomParamType;
import ch.ivy.addon.portalkit.enums.DashboardWidgetType;
import ch.ivy.addon.portalkit.publicapi.ProcessStartAPI;
import ch.ivy.addon.portalkit.util.Dates;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.IUser;
import ch.ivyteam.ivy.workflow.start.StartParameter;

public class CustomDashboardWidget extends DashboardWidget {
  private static final long serialVersionUID = 6901163427361921809L;

  private CustomWidgetData data;
  private String info;

  @Override
  public DashboardWidgetType getType() {
    return DashboardWidgetType.CUSTOM;
  }

  public CustomWidgetData getData() {
    return data;
  }

  public void setData(CustomWidgetData data) {
    this.data = data;
  }

  public String getInfo() {
    return info;
  }

  public void setInfo(String info) {
    this.info = info;
  }
  
  @JsonIgnore
  public static CustomDashboardWidget buildDefaultWidget(String id, String name) {
    CustomDashboardWidget result = new CustomDashboardWidget();
    result.setId(id);
    result.setName(name);
    result.setLayout(new WidgetLayout());
    result.getLayout().setWidth(10);
    result.getLayout().setHeight(6);
    result.getLayout().setAxisX(0);
    result.getLayout().setAxisY(0);
    result.setData(new CustomWidgetData());
    return result;
  }

  @JsonIgnore
  public void loadParametersFromProcess() {
    data.setParams(new ArrayList<>());
    data.setStartProcessParams(ProcessStartAPI
        .findStartElementByProcessStartFriendlyRequestPath(data.getProcessStart()).startParameters());

    if (CollectionUtils.isNotEmpty(data.getStartProcessParams())) {
      for (StartParameter param : data.getStartProcessParams()) {
        CustomDashboardWidgetParam customParam = new CustomDashboardWidgetParam();
        customParam.setName(param.name());
        if (param.name().startsWith(DashboardCustomParamType.STRING.getPrefix())) {
          customParam.setType(DashboardCustomParamType.STRING);
          customParam.setName(removePrefixFromParamName(param.name(), DashboardCustomParamType.STRING));
        }
        if (param.name().startsWith(DashboardCustomParamType.BOOLEAN.getPrefix())) {
          customParam.setType(DashboardCustomParamType.BOOLEAN);
          customParam.setName(removePrefixFromParamName(param.name(), DashboardCustomParamType.BOOLEAN));
        }
        if (param.name().startsWith(DashboardCustomParamType.DATE.getPrefix())) {
          customParam.setType(DashboardCustomParamType.DATE);
          customParam.setName(removePrefixFromParamName(param.name(), DashboardCustomParamType.DATE));
        }
        if (param.name().startsWith(DashboardCustomParamType.USER.getPrefix())) {
          customParam.setType(DashboardCustomParamType.USER);
          customParam.setName(removePrefixFromParamName(param.name(), DashboardCustomParamType.USER));
        }
        data.getParams().add(customParam);
      }
    }

    data.setParams(data.getParams().stream()
        .sorted(Comparator.comparing(CustomDashboardWidgetParam::getName))
        .sorted(Comparator.comparing(CustomDashboardWidgetParam::getType))
        .collect(Collectors.toList()));
  }

  private String removePrefixFromParamName(String paramName, DashboardCustomParamType paramType) {
    return paramName.replaceFirst("^" + paramType.getPrefix(), "");
  }
  
  public void loadParameters() {
    if (CollectionUtils.isNotEmpty(data.getParams())) {
      for(CustomDashboardWidgetParam param : data.getParams()) {
        switch (param.getType()) {
        case BOOLEAN:
          param.setValueBoolean(Boolean.parseBoolean(param.getValue()));
          break;
        case DATE:
          try {
            param.setValueDate(Dates.parse(param.getValue()));
          } catch (ParseException e) {
            param.setValueDate(null);
          }
          break;
        case USER:
          IUser user = Ivy.wf().getSecurityContext().users().find(param.getValue());
          if (user != null) {
            param.setValueUser(new UserDTO(user));
          }
        default:
          break;
        }
      }
    }
  }

  @JsonIgnore
  private void setDefaultParam(String name, CustomDashboardWidgetParam param) {
    param.setName(name);
    param.setType(DashboardCustomParamType.STRING);
    param.setValue(data.getStartableProcessStart().customFields().value(name));
  }

@Override
public void resetWidgetFilters() {
	// TODO Auto-generated method stub
	
}
}
