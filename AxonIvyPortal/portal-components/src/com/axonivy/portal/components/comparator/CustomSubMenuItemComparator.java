package com.axonivy.portal.components.comparator;

import java.util.Comparator;
import java.util.Optional;

import com.axonivy.portal.components.configuration.CustomSubMenuItem;

public class CustomSubMenuItemComparator implements Comparator<CustomSubMenuItem> {

  @Override
  public int compare(CustomSubMenuItem sub1,CustomSubMenuItem sub2) {
    int indexCompare = Optional.ofNullable(sub1).map(CustomSubMenuItem::getIndex).orElse(Integer.MAX_VALUE)
        .compareTo(Optional.ofNullable(sub2).map(CustomSubMenuItem::getIndex).orElse(Integer.MAX_VALUE));
    int nameCompare = Optional.ofNullable(sub1).map(CustomSubMenuItem::getLabel).orElse("")
        .compareTo(Optional.ofNullable(sub2).map(CustomSubMenuItem::getLabel).orElse(""));

    return indexCompare == 0 ? nameCompare : indexCompare;
  }
}