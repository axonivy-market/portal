package ch.ivy.addon.portalkit.converter;

import javax.faces.convert.FacesConverter;

import ch.ivy.addon.portalkit.statistics.StatisticChart;

@FacesConverter("statisticConverter")
public class StatisticConverter extends AbstractConverter<StatisticConverter> {

  @Override
  protected Class<StatisticConverter> getType() {
    return StatisticConverter.class;
  }

  @Override
  protected boolean isEmptyObject(Object item) {
    if (item == null || !(item instanceof StatisticChart)) {
      return false;
    }
    var chart = (StatisticChart) item;
    return chart.getType() == null && chart.getNames() == null;
  }

  @Override
  protected StatisticChart createNewObject(String selectedValue) {
    var newChart = new StatisticChart();
    newChart.setName(selectedValue);
    return newChart;
  }
}
