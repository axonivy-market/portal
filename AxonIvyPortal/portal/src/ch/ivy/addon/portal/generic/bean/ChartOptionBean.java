package ch.ivy.addon.portal.generic.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.axonivy.portal.bo.StatisticData;
import com.axonivy.portal.service.StatisticDataService;

@ViewScoped
@ManagedBean
public class ChartOptionBean implements Serializable {

  private static final long serialVersionUID = -4224901891867040681L;

  protected List<StatisticData> statisticDataList;

  @PostConstruct
  public void init() {
    statisticDataList = StatisticDataService.getInstance().findAllCharts();
  }

  public List<StatisticData> getStatisticDataList() {
    return statisticDataList;
  }

  public void setStatisticDataList(List<StatisticData> statisticDataList) {
    this.statisticDataList = statisticDataList;
  }

}
