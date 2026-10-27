package com.axonivy.portal.components.util;

public record ImageUploadResult(String imageLocation, String imageType, boolean isInvalid, String message) {
  public ImageUploadResult(String imageLocation, String imageType, boolean isInvalid) {
    this(imageLocation, imageType, isInvalid, null);
  }
}
