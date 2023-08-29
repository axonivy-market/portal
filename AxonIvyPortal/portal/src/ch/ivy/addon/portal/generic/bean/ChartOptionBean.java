package ch.ivy.addon.portal.generic.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.axonivy.portal.bo.StatisticChartData;
import com.axonivy.portal.service.StatisticChartDataService;

@ViewScoped
@ManagedBean
public class ChartOptionBean implements Serializable {

  private static final long serialVersionUID = -4224901891867040681L;

  protected List<StatisticChartData> statisticDataList;

  @PostConstruct
  public void init() {
    statisticDataList = StatisticChartDataService.getInstance().findAllCharts();
  }

  public List<StatisticChartData> getStatisticDataList() {
    return statisticDataList;
  }

  public void setStatisticDataList(List<StatisticChartData> statisticDataList) {
    this.statisticDataList = statisticDataList;
  }

}
