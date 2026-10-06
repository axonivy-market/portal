package com.axonivy.portal.smart.statistic.service;

import static com.axonivy.portal.enums.statistic.ChartType.BAR;
import static com.axonivy.portal.enums.statistic.ChartType.LINE;
import static com.axonivy.portal.enums.statistic.ChartType.PIE;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.axonivy.portal.bo.BarChartConfig;
import com.axonivy.portal.bo.ColumnChartConfig;
import com.axonivy.portal.bo.LineChartConfig;
import com.axonivy.portal.bo.PieChartConfig;
import com.axonivy.portal.bo.Statistic;
import com.axonivy.portal.enums.statistic.ChartType;

import ch.ivy.addon.portalkit.dto.DisplayName;

public final class ChartAppearanceService {

  public static final int MIN_COLOR_SLOTS = 8;

  private ChartAppearanceService() {}

  public static ColumnChartConfig columnConfigOf(Statistic chart) {
    ChartType chartType = chart.getChartType();
    if (BAR == chartType) {
      return chart.getBarChartConfig();
    }
    return LINE == chartType ? chart.getLineChartConfig() : null;
  }

  public static List<String> storedColors(Statistic chart) {
    ColumnChartConfig columnConfig = columnConfigOf(chart);
    if (columnConfig != null) {
      return mutableCopy(columnConfig.getBackgroundColors());
    }
    if (PIE == chart.getChartType() && chart.getPieChartConfig() != null) {
      return mutableCopy(chart.getPieChartConfig().getBackgroundColors());
    }
    return new ArrayList<>();
  }

  public static void updateTitlesAndColors(Statistic chart, List<DisplayName> xTitles,
      List<DisplayName> yTitles, List<String> backgroundColors) {
    List<String> colors = mutableCopy(backgroundColors);
    colors.removeIf(Objects::isNull);

    switch (chart.getChartType()) {
      case BAR, LINE -> {
        ColumnChartConfig config = getOrCreateColumnConfig(chart);
        config.setxTitles(xTitles);
        config.setyTitles(yTitles);
        config.setBackgroundColors(colors);
      }
      case PIE -> {
        if (chart.getPieChartConfig() == null) {
          chart.setPieChartConfig(new PieChartConfig());
        }
        chart.getPieChartConfig().setBackgroundColors(colors);
      }
      default -> { }
    }
  }

  private static ColumnChartConfig getOrCreateColumnConfig(Statistic chart) {
    ColumnChartConfig existing = columnConfigOf(chart);
    if (existing != null) {
      return existing;
    }
    return switch (chart.getChartType()) {
      case BAR -> {
        BarChartConfig config = new BarChartConfig();
        chart.setBarChartConfig(config);
        yield config;
      }
      case LINE -> {
        LineChartConfig config = new LineChartConfig();
        chart.setLineChartConfig(config);
        yield config;
      }
      default -> throw new IllegalArgumentException(
          "Not a chart type with axes: " + chart.getChartType());
    };
  }

  public static void padColors(List<String> backgroundColors) {
    while (backgroundColors.size() < MIN_COLOR_SLOTS) {
      backgroundColors.add(null);
    }
  }

  public static boolean isCartesian(ChartType chartType) {
    return BAR == chartType || LINE == chartType;
  }

  private static List<String> mutableCopy(List<String> colors) {
    return colors == null ? new ArrayList<>() : new ArrayList<>(colors);
  }
}
