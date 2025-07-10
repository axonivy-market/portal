package com.axonivy.portal.userexamples.bean;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class NoteBean {

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
