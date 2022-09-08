package ch.ivy.addon.portalkit.bo;

public class OAuthProvider {
  public final String displayName;
  public final String image;
  public final String link;

  public OAuthProvider(String displayName, String image, String link) {
    this.displayName = displayName;
    this.image = image;
    this.link = link;
  }

  public String getDisplayName() {
    return displayName;
  }

  public String getImage() {
    return image;
  }

  public String getLink() {
    return link;
  }
}
