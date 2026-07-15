package com.axonivy.portal.components.document;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;

import org.junit.jupiter.api.Test;

import ch.ivyteam.ivy.environment.IvyTest;

/**
 * {@code isSafe(String)} is pure logic and never touches Ivy, but
 * {@code isSafe(InputStream)} calls {@code Ivy.log()} on its error path
 * (e.g. a null stream), so the whole class runs under {@code @IvyTest}.
 */
@IvyTest
class TestSVGSecurityScanner {

  @Test
  void isSafe_plainSvg_returnsTrue() {
    String svg = "<svg xmlns=\"http://www.w3.org/2000/svg\"><circle cx=\"5\" cy=\"5\" r=\"4\"/></svg>";
    assertThat(SVGSecurityScanner.isSafe(svg)).isTrue();
  }

  @Test
  void isSafe_scriptElement_returnsFalse() {
    String svg = "<svg><script>alert(1)</script></svg>";
    assertThat(SVGSecurityScanner.isSafe(svg)).isFalse();
  }

  @Test
  void isSafe_namespacedScriptElement_returnsFalse() {
    String svg = "<svg><svg:script>alert(1)</svg:script></svg>";
    assertThat(SVGSecurityScanner.isSafe(svg)).isFalse();
  }

  @Test
  void isSafe_foreignObjectElement_returnsFalse() {
    String svg = "<svg><foreignObject><body onload=\"alert(1)\"/></foreignObject></svg>";
    assertThat(SVGSecurityScanner.isSafe(svg)).isFalse();
  }

  @Test
  void isSafe_animateElement_returnsFalse() {
    String svg = "<svg><animate attributeName=\"x\" to=\"1\"/></svg>";
    assertThat(SVGSecurityScanner.isSafe(svg)).isFalse();
  }

  @Test
  void isSafe_onEventHandlerAttribute_returnsFalse() {
    String svg = "<svg onload=\"alert(1)\"><rect/></svg>";
    assertThat(SVGSecurityScanner.isSafe(svg)).isFalse();
  }

  @Test
  void isSafe_hrefWithJavascriptScheme_returnsFalse() {
    String svg = "<svg><a href=\"javascript:alert(1)\"><rect/></a></svg>";
    assertThat(SVGSecurityScanner.isSafe(svg)).isFalse();
  }

  @Test
  void isSafe_xlinkHrefWithDataScheme_returnsFalse() {
    String svg = "<svg><image xlink:href=\"data:text/html,<script>alert(1)</script>\"/></svg>";
    assertThat(SVGSecurityScanner.isSafe(svg)).isFalse();
  }

  @Test
  void isSafe_hrefWithNormalUrl_returnsTrue() {
    String svg = "<svg><a href=\"https://example.com\"><rect/></a></svg>";
    assertThat(SVGSecurityScanner.isSafe(svg)).isTrue();
  }

  @Test
  void isSafe_obfuscatedJavascriptSchemeWithEncodedControlChar_returnsFalse() {
    String svg = "<svg><a href=\"java%09script:alert(1)\"><rect/></a></svg>";
    assertThat(SVGSecurityScanner.isSafe(svg)).isFalse();
  }

  @Test
  void isSafe_styleAttributeWithCssExpression_returnsFalse() {
    String svg = "<svg><rect style=\"width:expression(alert(1))\"/></svg>";
    assertThat(SVGSecurityScanner.isSafe(svg)).isFalse();
  }

  @Test
  void isSafe_styleAttributeWithDangerousCssUrl_returnsFalse() {
    String svg = "<svg><rect style=\"background:url(javascript:alert(1))\"/></svg>";
    assertThat(SVGSecurityScanner.isSafe(svg)).isFalse();
  }

  @Test
  void isSafe_styleElementWithCssExpression_returnsFalse() {
    String svg = "<svg><style>rect{width:expression(alert(1))}</style></svg>";
    assertThat(SVGSecurityScanner.isSafe(svg)).isFalse();
  }

  @Test
  void isSafe_styleElementWithDangerousCssUrl_returnsFalse() {
    String svg = "<svg><style>rect{background:url(data:text/html,x)}</style></svg>";
    assertThat(SVGSecurityScanner.isSafe(svg)).isFalse();
  }

  @Test
  void isSafe_styleElementWithNormalCss_returnsTrue() {
    String svg = "<svg><style>.red{fill:red}</style><rect class=\"red\"/></svg>";
    assertThat(SVGSecurityScanner.isSafe(svg)).isTrue();
  }

  @Test
  void isSafe_internalEntityDeclaration_returnsFalse() {
    String svg = "<!DOCTYPE svg [<!ENTITY xxe \"boom\">]><svg>&xxe;</svg>";
    assertThat(SVGSecurityScanner.isSafe(svg)).isFalse();
  }

  @Test
  void isSafeInputStream_nullStream_returnsFalse() {
    assertThat(new SVGSecurityScanner().isSafe(null)).isFalse();
  }

  @Test
  void isSafeInputStream_safeSvgBytes_returnsTrue() {
    String svg = "<svg xmlns=\"http://www.w3.org/2000/svg\"><circle cx=\"5\" cy=\"5\" r=\"4\"/></svg>";
    var inputStream = new ByteArrayInputStream(svg.getBytes(StandardCharsets.UTF_8));
    assertThat(new SVGSecurityScanner().isSafe(inputStream)).isTrue();
  }

  @Test
  void isSafeInputStream_maliciousSvgBytes_returnsFalse() {
    String svg = "<svg><script>alert(1)</script></svg>";
    var inputStream = new ByteArrayInputStream(svg.getBytes(StandardCharsets.UTF_8));
    assertThat(new SVGSecurityScanner().isSafe(inputStream)).isFalse();
  }
}
