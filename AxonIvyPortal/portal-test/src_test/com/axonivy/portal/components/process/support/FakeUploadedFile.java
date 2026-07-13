package com.axonivy.portal.components.process.support;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.primefaces.model.file.UploadedFile;

/**
 * Minimal in-memory {@link UploadedFile} test double for process tests that exercise
 * document upload / checking logic. Only the members actually read by the code under
 * test ({@code getFileName}, {@code getInputStream}, {@code getContent}) carry data;
 * the rest return harmless defaults.
 */
public class FakeUploadedFile implements UploadedFile {

  private final String fileName;
  private final byte[] content;
  private final String contentType;

  public FakeUploadedFile(String fileName, byte[] content) {
    this(fileName, content, "application/octet-stream");
  }

  public FakeUploadedFile(String fileName, byte[] content, String contentType) {
    this.fileName = fileName;
    this.content = content == null ? new byte[0] : content;
    this.contentType = contentType;
  }

  @Override
  public String getFileName() {
    return fileName;
  }

  @Override
  public InputStream getInputStream() {
    return new ByteArrayInputStream(content);
  }

  @Override
  public byte[] getContent() {
    return content;
  }

  @Override
  public String getContentType() {
    return contentType;
  }

  @Override
  public long getSize() {
    return content.length;
  }

  @Override
  public void write(String filePath) {
    // no-op: tests never persist the upload to disk
  }

  @Override
  public void delete() {
    // no-op
  }
}
