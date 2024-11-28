package com.axonivy.portal.bo.jsonversion;

import java.util.Arrays;

import org.apache.commons.lang3.StringUtils;

public abstract class AbstractJsonVersion implements Comparable<AbstractJsonVersion> {
  public static final String VERSION_FIELD_NAME = "version";

  public static final String LATEST = "12.0.0";
  public static final String OLDEST = "10.0.0";

  private String value;

  public AbstractJsonVersion(String value) {
    this.value = value;
  }

  @Override
  public int compareTo(AbstractJsonVersion other) {
    if (other == null) {
      return -1;
    }
    int[] myParts = toNumbers(getValue());
    int[] otherParts = toNumbers(other.getValue());
    for (int i = 0; i < myParts.length; i++) {
      int otherPart = 0;
      if (i < otherParts.length) {
        otherPart = otherParts[i];
      }
      int compare = Integer.compare(myParts[i], otherPart);
      if (compare != 0) {
        return compare;
      }
    }
    if (myParts.length < otherParts.length) {
      if (Arrays.stream(otherParts, myParts.length, otherParts.length).anyMatch(num -> num > 0)) {
        return -1;
      }
    }
    return 0;
  }

  private static int[] toNumbers(String version) {
    return Arrays.stream(StringUtils.split(version, '.')).mapToInt(part -> Integer.parseInt(part)).toArray();
  }

  public boolean isLatest() {
    return this.getValue().contentEquals(LATEST);
  }

  public boolean isOlderThan(AbstractJsonVersion other) {
    return this.compareTo(other) < 0;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }
}
