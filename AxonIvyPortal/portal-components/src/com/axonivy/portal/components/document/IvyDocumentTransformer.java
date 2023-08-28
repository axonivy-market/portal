package com.axonivy.portal.components.document;

import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import com.axonivy.portal.components.ivydata.bo.IvyDocument;

import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.scripting.objects.File;
import ch.ivyteam.ivy.workflow.document.IDocument;

public class IvyDocumentTransformer {
  public IvyDocument transform(IDocument document) {
    try {
    File  file = new File(document.getPath().asString());
    return IvyDocument.builder()
              .id(String.valueOf(document.getId()))
              .name(document.getName())
              .path(document.getPath().asString())
              .size(document.getSize())
              .creation(document.getCreation())
              .lastModification(document.getLastModification())
              .uuid(document.uuid())
              .contentType(Files.probeContentType(file.getJavaFile().toPath()))
              .create();
    } catch (IOException e) {
      Ivy.log().error(e);
      return new IvyDocument();
    }
  }

  public List<IvyDocument> transform(List<IDocument> documents) {
    List<IvyDocument> result = new ArrayList<>();

    for (IDocument document : documents) {
      result.add(transform(document));
    }

    return result;
  }
}
