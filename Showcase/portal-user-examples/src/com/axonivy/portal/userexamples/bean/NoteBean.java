package com.axonivy.portal.userexamples.bean;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import jakarta.inject.Named;
import jakarta.faces.view.ViewScoped;

@Named
@ViewScoped
public class NoteBean implements Serializable {

  private String decodedData;

  // Getter and Setter for decodedData
  public String getDecodedData() {
    return decodedData;
  }

  public void setDecodedData(String decodedData) {
    this.decodedData = decodedData;
  }

  // Method to decode the URL-encoded data
  public void decodeNoteData(String note) {
    try {
      this.decodedData = URLDecoder.decode(note, "UTF-8");
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
      this.decodedData = "Error decoding data";
    }
  }
}
