package com.axonivy.portal.component.example.document;

import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.scripting.objects.File;
import ch.ivyteam.ivy.workflow.document.IDocument;

public class CustomizedIvyDocumentTransformer {

  public CustomizedIvyDocument transform(IDocument document) {
    CustomizedIvyDocument result = new CustomizedIvyDocument();

    result.setId(String.valueOf(document.getId()));
    result.setName(document.getName());
    result.setPath(document.getPath().asString());
    result.setSize(document.getSize());
    result.setCreation(document.getCreation());
    result.setLastModification(document.getLastModification());

    try {
      File file = new File(document.getPath().asString());
      result.setContentType(Files.probeContentType(file.getJavaFile().toPath()));
    } catch (IOException e) {
      Ivy.log().error(e);
    }

    return result;
  }

  public List<CustomizedIvyDocument> transform(List<IDocument> documents) {
    List<CustomizedIvyDocument> result = new ArrayList<>();

    for (IDocument document : documents) {
      result.add(transform(document));
    }

    return result;
  }
}
