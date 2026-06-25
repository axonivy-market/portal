package ch.ivy.addon.portalkit.jsf;

import javax.faces.context.FacesContext;

/**
 * <p>
 * Small utility to set/get {@code cc.attrs} in HtmlDialog logic or {@code @ManagedBean}.
 * </p>
 * 
 * <p>
 * Typical use of {@code Attrs} is in HtmlDialog's process (methods & events)
 * </p>
 * 
 * <pre>
 * <code>
 *  Attrs attrs = Attrs.currentContext();
 *  String name = attrs.get("name");
 *  SomeClass someClass = attrs.get("argumentSomeClass");
 * </code>
 * </pre>
 */
public class Attrs {

  private Attrs(FacesContext facesContext) {
    if (facesContext == null) {
      throw new IllegalArgumentException("FacesContext must not be null to use Attrs");
    }
  }
  
  public static Attrs currentContext() {
    return new Attrs(FacesContext.getCurrentInstance());
  }
  
  public <T> T get(String attribute) {
    String attributeExpression = String.format("#{cc.attrs.%s}", attribute);
    return getAttribute(attributeExpression);
  }
  
  public <T> T getBuildInAttribute(String attribute){
    String attributeExpression = String.format("#{cc.%s}", attribute);
    return getAttribute(attributeExpression);
  }
  
  
  private <T> T getAttribute(String attributeExpression){
    FacesContext fc = FacesContext.getCurrentInstance();
    @SuppressWarnings("unchecked")
    T attributeValue = (T) fc.getApplication().evaluateExpressionGet(fc, attributeExpression , Object.class);
    return attributeValue;
  }
  
  @SuppressWarnings({"unchecked"})
  public <T> T getAttribute(String attributeExpression, Class<? extends Object> expectedType){
    FacesContext fc = FacesContext.getCurrentInstance();
    T attributeValue = (T) fc.getApplication().evaluateExpressionGet(fc, attributeExpression , expectedType);
    return attributeValue;
  }
}
