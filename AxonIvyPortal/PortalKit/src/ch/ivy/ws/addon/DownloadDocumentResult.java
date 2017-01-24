/**
 * DownloadDocumentResult.java
 *
 * This file was auto-generated from WSDL by the Apache Axis2 version: 1.3 Built on : Aug 10, 2007
 * (04:45:58 LKT)
 */
package ch.ivy.ws.addon;

import javax.xml.stream.events.XMLEvent;


/**
 * MUST BE READ: <br/>
 * DownloadDocumentResult bean class is compiled and copied to case webservice jar in designer to
 * download a file. <br/>
 * Everytime re-generate case webservice, please run ant build to re-compiled and copied it to case
 * webservice jar.
 */
@SuppressWarnings("restriction")
public class DownloadDocumentResult implements org.apache.axis2.databinding.ADBBean {
  private final static int DOCUMENT_BASE64_CONTENT_BUFFER_SIZE = 200000;

  /**
   * field for DocumentContent
   */
  protected javax.activation.DataHandler localDocumentContent;

  /*
   * This tracker boolean wil be used to detect whether the user called the set method for this
   * attribute. It will be used to determine whether to include this field in the serialized XML
   */
  protected boolean localDocumentContentTracker = false;

  /**
   * field for Errors This was an Array!
   */
  protected ch.ivy.ws.addon.WsException[] localErrors;

  /*
   * This tracker boolean wil be used to detect whether the user called the set method for this
   * attribute. It will be used to determine whether to include this field in the serialized XML
   */
  protected boolean localErrorsTracker = false;

  /*
   * This type was generated from the piece of schema that had name = downloadDocumentResult
   * Namespace URI = http://addon.ws.ivy.ch/ Namespace Prefix = ns1
   */
  private static java.lang.String generatePrefix(java.lang.String namespace) {
    if (namespace.equals("http://addon.ws.ivy.ch/")) {
      return "ns1";
    }

    return org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
  }

  /**
   * Auto generated getter method
   * 
   * @return javax.activation.DataHandler
   */
  public javax.activation.DataHandler getDocumentContent() {
    return localDocumentContent;
  }

  /**
   * Auto generated setter method
   * 
   * @param param DocumentContent
   */
  public void setDocumentContent(javax.activation.DataHandler param) {
    if (param != null) {
      // update the setting tracker
      localDocumentContentTracker = true;
    } else {
      localDocumentContentTracker = false;
    }

    this.localDocumentContent = param;
  }

  /**
   * Auto generated getter method
   * 
   * @return ch.ivy.ws.addon.WsException[]
   */
  public ch.ivy.ws.addon.WsException[] getErrors() {
    return localErrors;
  }

  /**
   * validate the array for Errors
   */
  protected void validateErrors(ch.ivy.ws.addon.WsException[] param) {}

  /**
   * Auto generated setter method
   * 
   * @param param Errors
   */
  public void setErrors(ch.ivy.ws.addon.WsException[] param) {
    validateErrors(param);

    if (param != null) {
      // update the setting tracker
      localErrorsTracker = true;
    } else {
      localErrorsTracker = true;
    }

    this.localErrors = param;
  }

  /**
   * Auto generated add method for the array for convenience
   * 
   * @param param ch.ivy.ws.addon.WsException
   */
  public void addErrors(ch.ivy.ws.addon.WsException param) {
    if (localErrors == null) {
      localErrors = new ch.ivy.ws.addon.WsException[] {};
    }

    // update the setting tracker
    localErrorsTracker = true;

    java.util.List list = org.apache.axis2.databinding.utils.ConverterUtil.toList(localErrors);
    list.add(param);
    this.localErrors = (ch.ivy.ws.addon.WsException[]) list.toArray(new ch.ivy.ws.addon.WsException[list.size()]);
  }

  /**
   * isReaderMTOMAware
   * 
   * @return true if the reader supports MTOM
   */
  public static boolean isReaderMTOMAware(javax.xml.stream.XMLStreamReader reader) {
    boolean isReaderMTOMAware = false;

    try {
      isReaderMTOMAware =
          java.lang.Boolean.TRUE.equals(reader.getProperty(org.apache.axiom.om.OMConstants.IS_DATA_HANDLERS_AWARE));
    } catch (java.lang.IllegalArgumentException e) {
      isReaderMTOMAware = false;
    }

    return isReaderMTOMAware;
  }

  /**
   *
   * @param parentQName
   * @param factory
   * @return org.apache.axiom.om.OMElement
   */
  public org.apache.axiom.om.OMElement getOMElement(final javax.xml.namespace.QName parentQName,
      final org.apache.axiom.om.OMFactory factory) throws org.apache.axis2.databinding.ADBException {
    org.apache.axiom.om.OMDataSource dataSource = new org.apache.axis2.databinding.ADBDataSource(this, parentQName) {
      public void serialize(org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter)
          throws javax.xml.stream.XMLStreamException {
        DownloadDocumentResult.this.serialize(parentQName, factory, xmlWriter);
      }
    };

    return new org.apache.axiom.om.impl.llom.OMSourcedElementImpl(parentQName, factory, dataSource);
  }

  public void serialize(final javax.xml.namespace.QName parentQName, final org.apache.axiom.om.OMFactory factory,
      org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter)
      throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException {
    java.lang.String prefix = null;
    java.lang.String namespace = null;

    prefix = parentQName.getPrefix();
    namespace = parentQName.getNamespaceURI();

    if (namespace != null) {
      java.lang.String writerPrefix = xmlWriter.getPrefix(namespace);

      if (writerPrefix != null) {
        xmlWriter.writeStartElement(namespace, parentQName.getLocalPart());
      } else {
        if (prefix == null) {
          prefix = generatePrefix(namespace);
        }

        xmlWriter.writeStartElement(prefix, parentQName.getLocalPart(), namespace);
        xmlWriter.writeNamespace(prefix, namespace);
        xmlWriter.setPrefix(prefix, namespace);
      }
    } else {
      xmlWriter.writeStartElement(parentQName.getLocalPart());
    }

    if (localDocumentContentTracker) {
      namespace = "";

      if (!namespace.equals("")) {
        prefix = xmlWriter.getPrefix(namespace);

        if (prefix == null) {
          prefix = generatePrefix(namespace);

          xmlWriter.writeStartElement(prefix, "documentContent", namespace);
          xmlWriter.writeNamespace(prefix, namespace);
          xmlWriter.setPrefix(prefix, namespace);
        } else {
          xmlWriter.writeStartElement(namespace, "documentContent");
        }
      } else {
        xmlWriter.writeStartElement("documentContent");
      }

      if (localDocumentContent != null) {
        xmlWriter.writeDataHandler(localDocumentContent);
      }

      xmlWriter.writeEndElement();
    }

    if (localErrorsTracker) {
      if (localErrors != null) {
        for (int i = 0; i < localErrors.length; i++) {
          if (localErrors[i] != null) {
            localErrors[i].serialize(new javax.xml.namespace.QName("", "errors"), factory, xmlWriter);
          } else {
            // write null attribute
            java.lang.String namespace2 = "";

            if (!namespace2.equals("")) {
              java.lang.String prefix2 = xmlWriter.getPrefix(namespace2);

              if (prefix2 == null) {
                prefix2 = generatePrefix(namespace2);

                xmlWriter.writeStartElement(prefix2, "errors", namespace2);
                xmlWriter.writeNamespace(prefix2, namespace2);
                xmlWriter.setPrefix(prefix2, namespace2);
              } else {
                xmlWriter.writeStartElement(namespace2, "errors");
              }
            } else {
              xmlWriter.writeStartElement("errors");
            }

            // write the nil attribute
            writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);
            xmlWriter.writeEndElement();
          }
        }
      } else {
        // write null attribute
        java.lang.String namespace2 = "";

        if (!namespace2.equals("")) {
          java.lang.String prefix2 = xmlWriter.getPrefix(namespace2);

          if (prefix2 == null) {
            prefix2 = generatePrefix(namespace2);

            xmlWriter.writeStartElement(prefix2, "errors", namespace2);
            xmlWriter.writeNamespace(prefix2, namespace2);
            xmlWriter.setPrefix(prefix2, namespace2);
          } else {
            xmlWriter.writeStartElement(namespace2, "errors");
          }
        } else {
          xmlWriter.writeStartElement("errors");
        }

        // write the nil attribute
        writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);
        xmlWriter.writeEndElement();
      }
    }

    xmlWriter.writeEndElement();
  }

  /**
   * Util method to write an attribute with the ns prefix
   */
  private void writeAttribute(java.lang.String prefix, java.lang.String namespace, java.lang.String attName,
      java.lang.String attValue, javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {
    if (xmlWriter.getPrefix(namespace) == null) {
      xmlWriter.writeNamespace(prefix, namespace);
      xmlWriter.setPrefix(prefix, namespace);
    }

    xmlWriter.writeAttribute(namespace, attName, attValue);
  }

  /**
   * Util method to write an attribute without the ns prefix
   */
  private void writeAttribute(java.lang.String namespace, java.lang.String attName, java.lang.String attValue,
      javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {
    if (namespace.equals("")) {
      xmlWriter.writeAttribute(attName, attValue);
    } else {
      registerPrefix(xmlWriter, namespace);
      xmlWriter.writeAttribute(namespace, attName, attValue);
    }
  }

  /**
   * Util method to write an attribute without the ns prefix
   */
  private void writeQNameAttribute(java.lang.String namespace, java.lang.String attName,
      javax.xml.namespace.QName qname, javax.xml.stream.XMLStreamWriter xmlWriter)
      throws javax.xml.stream.XMLStreamException {
    java.lang.String attributeNamespace = qname.getNamespaceURI();
    java.lang.String attributePrefix = xmlWriter.getPrefix(attributeNamespace);

    if (attributePrefix == null) {
      attributePrefix = registerPrefix(xmlWriter, attributeNamespace);
    }

    java.lang.String attributeValue;

    if (attributePrefix.trim().length() > 0) {
      attributeValue = attributePrefix + ":" + qname.getLocalPart();
    } else {
      attributeValue = qname.getLocalPart();
    }

    if (namespace.equals("")) {
      xmlWriter.writeAttribute(attName, attributeValue);
    } else {
      registerPrefix(xmlWriter, namespace);
      xmlWriter.writeAttribute(namespace, attName, attributeValue);
    }
  }

  /**
   * method to handle Qnames
   */
  private void writeQName(javax.xml.namespace.QName qname, javax.xml.stream.XMLStreamWriter xmlWriter)
      throws javax.xml.stream.XMLStreamException {
    java.lang.String namespaceURI = qname.getNamespaceURI();

    if (namespaceURI != null) {
      java.lang.String prefix = xmlWriter.getPrefix(namespaceURI);

      if (prefix == null) {
        prefix = generatePrefix(namespaceURI);
        xmlWriter.writeNamespace(prefix, namespaceURI);
        xmlWriter.setPrefix(prefix, namespaceURI);
      }

      if (prefix.trim().length() > 0) {
        xmlWriter.writeCharacters(prefix + ":"
            + org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
      } else {
        // i.e this is the default namespace
        xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
      }
    } else {
      xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
    }
  }

  private void writeQNames(javax.xml.namespace.QName[] qnames, javax.xml.stream.XMLStreamWriter xmlWriter)
      throws javax.xml.stream.XMLStreamException {
    if (qnames != null) {
      // we have to store this data until last moment since it is not possible to write any
      // namespace data after writing the charactor data
      java.lang.StringBuffer stringToWrite = new java.lang.StringBuffer();
      java.lang.String namespaceURI = null;
      java.lang.String prefix = null;

      for (int i = 0; i < qnames.length; i++) {
        if (i > 0) {
          stringToWrite.append(" ");
        }

        namespaceURI = qnames[i].getNamespaceURI();

        if (namespaceURI != null) {
          prefix = xmlWriter.getPrefix(namespaceURI);

          if ((prefix == null) || (prefix.length() == 0)) {
            prefix = generatePrefix(namespaceURI);
            xmlWriter.writeNamespace(prefix, namespaceURI);
            xmlWriter.setPrefix(prefix, namespaceURI);
          }

          if (prefix.trim().length() > 0) {
            stringToWrite.append(prefix).append(":")
                .append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
          } else {
            stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
          }
        } else {
          stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
        }
      }

      xmlWriter.writeCharacters(stringToWrite.toString());
    }
  }

  /**
   * Register a namespace prefix
   */
  private java.lang.String registerPrefix(javax.xml.stream.XMLStreamWriter xmlWriter, java.lang.String namespace)
      throws javax.xml.stream.XMLStreamException {
    java.lang.String prefix = xmlWriter.getPrefix(namespace);

    if (prefix == null) {
      prefix = generatePrefix(namespace);

      while (xmlWriter.getNamespaceContext().getNamespaceURI(prefix) != null) {
        prefix = org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
      }

      xmlWriter.writeNamespace(prefix, namespace);
      xmlWriter.setPrefix(prefix, namespace);
    }

    return prefix;
  }

  /**
   * databinding method to get an XML representation of this object
   *
   */
  public javax.xml.stream.XMLStreamReader getPullParser(javax.xml.namespace.QName qName)
      throws org.apache.axis2.databinding.ADBException {
    java.util.ArrayList elementList = new java.util.ArrayList();
    java.util.ArrayList attribList = new java.util.ArrayList();

    if (localDocumentContentTracker) {
      elementList.add(new javax.xml.namespace.QName("", "documentContent"));

      elementList.add(localDocumentContent);
    }

    if (localErrorsTracker) {
      if (localErrors != null) {
        for (int i = 0; i < localErrors.length; i++) {
          if (localErrors[i] != null) {
            elementList.add(new javax.xml.namespace.QName("", "errors"));
            elementList.add(localErrors[i]);
          } else {
            elementList.add(new javax.xml.namespace.QName("", "errors"));
            elementList.add(null);
          }
        }
      } else {
        elementList.add(new javax.xml.namespace.QName("", "errors"));
        elementList.add(localErrors);
      }
    }

    return new org.apache.axis2.databinding.utils.reader.ADBXMLStreamReaderImpl(qName, elementList.toArray(),
        attribList.toArray());
  }

  /**
   * Factory class that keeps the parse method
   */
  public static class Factory {
    /**
     * static method to create the object Precondition: If this object is an element, the current or
     * next start element starts this object and any intervening reader events are ignorable If this
     * object is not an element, it is a complex type and the reader is at the event just after the
     * outer start element Postcondition: If this object is an element, the reader is positioned at
     * its end element If this object is a complex type, the reader is positioned at the end element
     * of its outer element
     */
    public static DownloadDocumentResult parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception {
      DownloadDocumentResult object = new DownloadDocumentResult();

      int event;
      java.lang.String nillableValue = null;
      java.lang.String prefix = "";
      java.lang.String namespaceuri = "";

      try {
        while (!reader.isStartElement() && !reader.isEndElement())
          reader.next();

        if (reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "type") != null) {
          java.lang.String fullTypeName = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "type");

          if (fullTypeName != null) {
            java.lang.String nsPrefix = null;

            if (fullTypeName.indexOf(":") > -1) {
              nsPrefix = fullTypeName.substring(0, fullTypeName.indexOf(":"));
            }

            nsPrefix = (nsPrefix == null) ? "" : nsPrefix;

            java.lang.String type = fullTypeName.substring(fullTypeName.indexOf(":") + 1);

            if (!"downloadDocumentResult".equals(type)) {
              // find namespace for the prefix
              java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);

              return (DownloadDocumentResult) ch.ivy.ws.addon.ExtensionMapper.getTypeObject(nsUri, type, reader);
            }
          }
        }

        // Note all attributes that were handled. Used to differ normal attributes
        // from anyAttributes.
        java.util.Vector handledAttributes = new java.util.Vector();

        reader.next();

        java.util.ArrayList list2 = new java.util.ArrayList();

        while (!reader.isStartElement() && !reader.isEndElement())
          reader.next();

        if (reader.isStartElement() && new javax.xml.namespace.QName("", "documentContent").equals(reader.getName())) {
          reader.next();

          if (isReaderMTOMAware(reader)
              && java.lang.Boolean.TRUE.equals(reader.getProperty(org.apache.axiom.om.OMConstants.IS_BINARY))) {
            // MTOM aware reader - get the datahandler directly and put it in the object
            object.setDocumentContent((javax.activation.DataHandler) reader
                .getProperty(org.apache.axiom.om.OMConstants.DATA_HANDLER));
          } else {
            if ((reader.getEventType() == javax.xml.stream.XMLStreamConstants.START_ELEMENT)
                && reader.getName().equals(
                    new javax.xml.namespace.QName(org.apache.axiom.om.impl.MTOMConstants.XOP_NAMESPACE_URI,
                        org.apache.axiom.om.impl.MTOMConstants.XOP_INCLUDE))) {
              java.lang.String id = org.apache.axiom.om.util.ElementHelper.getContentID(reader, "UTF-8");
              object
                  .setDocumentContent(((org.apache.axiom.soap.impl.builder.MTOMStAXSOAPModelBuilder) ((org.apache.axiom.om.impl.llom.OMStAXWrapper) reader)
                      .getBuilder()).getDataHandler(id));
              reader.next();

              reader.next();
            } else if (reader.hasText()) {
              /*------------------
               * START PATCH HERE
               * 
               * Since the reader does not obtain all characters between <documentContent></documentContent>
               * so we have to do it manually.
               */
              StringBuilder base64Content = new StringBuilder(DOCUMENT_BASE64_CONTENT_BUFFER_SIZE);
              base64Content.append(reader.getText());

              /*
               * Continue reading text until reaching the end tag of documentContent
               */
              while (XMLEvent.CHARACTERS == reader.next()) {
                if (reader.hasText()) {
                  base64Content.append(reader.getText());
                }
              }

              object.setDocumentContent(org.apache.axis2.databinding.utils.ConverterUtil
                  .convertToBase64Binary(base64Content.toString()));
              /*
               * END PATCH HERE -----------------
               */
              
              /*
               * The following statements, which are commented, are old, haven't worked well.
               */
              /*
              // Do the usual conversion
              java.lang.String content = reader.getText();
              object
                  .setDocumentContent(org.apache.axis2.databinding.utils.ConverterUtil.convertToBase64Binary(content));
              
              reader.next();
              */
            }
          }

          reader.next();
        } // End of if for expected property start element

        else {
        }

        while (!reader.isStartElement() && !reader.isEndElement())
          reader.next();

        if (reader.isStartElement() && new javax.xml.namespace.QName("", "errors").equals(reader.getName())) {
          // Process the array and step past its final element's end.
          nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");

          if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
            list2.add(null);
            reader.next();
          } else {
            list2.add(ch.ivy.ws.addon.WsException.Factory.parse(reader));
          }

          // loop until we find a start element that is not part of this array
          boolean loopDone2 = false;

          while (!loopDone2) {
            // We should be at the end element, but make sure
            while (!reader.isEndElement())
              reader.next();

            // Step out of this element
            reader.next();

            // Step to next element event.
            while (!reader.isStartElement() && !reader.isEndElement())
              reader.next();

            if (reader.isEndElement()) {
              // two continuous end elements means we are exiting the xml structure
              loopDone2 = true;
            } else {
              if (new javax.xml.namespace.QName("", "errors").equals(reader.getName())) {
                nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");

                if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
                  list2.add(null);
                  reader.next();
                } else {
                  list2.add(ch.ivy.ws.addon.WsException.Factory.parse(reader));
                }
              } else {
                loopDone2 = true;
              }
            }
          }

          // call the converter utility to convert and set the array
          object.setErrors((ch.ivy.ws.addon.WsException[]) org.apache.axis2.databinding.utils.ConverterUtil
              .convertToArray(ch.ivy.ws.addon.WsException.class, list2));
        } // End of if for expected property start element

        else {
        }

        while (!reader.isStartElement() && !reader.isEndElement())
          reader.next();

        if (reader.isStartElement()) {
          // A start element we are not expecting indicates a trailing invalid property
          throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getLocalName());
        }
      } catch (javax.xml.stream.XMLStreamException e) {
        throw new java.lang.Exception(e);
      }

      return object;
    }
  } // end of factory class
}
