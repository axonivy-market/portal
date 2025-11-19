package ch.ivy.addon.portalkit.bo;

/**
 * Defines JSON serialization views for Portal objects.
 * <p>
 * These marker classes are used with JSON serialization frameworks (such as Jackson)
 * to control which fields are included in the serialized output.
 * </p>
 */
public class PortalJsonViews {
  
  /**
   * Public view for JSON serialization.
   * <p>
   * Fields annotated with this view will be included in the JSON output
   * intended for public or external consumers.
   * </p>
   */
  public static class Public {}
  
  /**
   * Internal view for JSON serialization.
   * <p>
   * Fields annotated with this view will be included in the JSON output
   * intended for internal use only. This view extends {@link Public}, so all fields
   * visible in the public view are also visible in the internal view.
   * </p>
   */
  public static class Internal extends Public {}

}
