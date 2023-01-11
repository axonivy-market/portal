package ch.ivy.addon.portalkit.util;

import java.util.Arrays;

import org.apache.commons.lang3.StringUtils;

import ch.ivy.addon.portalkit.configuration.AbstractConfiguration;

public class JsonVersion implements Comparable<JsonVersion> {
  public static final JsonVersion DEFAULT = new JsonVersion("10.0.0");
  public static final JsonVersion LATEST = new JsonVersion("10.0.0");
  
  private String value;

  public JsonVersion(String value) {
    this.value = value;
  }

  @Override
  public int compareTo(JsonVersion other) {
    if (other == null) {
      return -1;
    }
    int[] myParts = toNumbers(getValue());
    int[] otherParts = toNumbers(other.getValue());
    for(int i=0; i<myParts.length; i++) {
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
    return Arrays.stream(StringUtils.split(version, '.'))
            .mapToInt(part -> Integer.parseInt(part))
            .toArray();
  }

  public static AbstractConfiguration initJsonVersionIfEmpty(AbstractConfiguration json) {
    if (StringUtils.isNoneBlank(json.getVersion())) {
      return json;
    }
    json.setVersion(JsonVersion.DEFAULT.getValue());
    return json;
  }

  public boolean isLatest() {
    return LATEST.equals(this);
  }

  public boolean isOlderThan(JsonVersion other) {
    return this.compareTo(other) < 0;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

}
