package com.axonivy.portal.components.document;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;

import org.junit.jupiter.api.Test;

import com.axonivy.portal.components.document.SVGSecurityScanner;

import ch.ivyteam.ivy.environment.IvyTest;

@IvyTest
class TestSVGSecurityScanner {

  @Test
  void cleanSvg_isSafe() {
    String svg = "<svg xmlns='http://www.w3.org/2000/svg'><rect width='100' height='100' fill='blue'/></svg>";
    assertThat(SVGSecurityScanner.isSafe(svg)).isTrue();
  }

  @Test
  void svgWithDefs_isSafe() {
    String svg = """
        <svg xmlns='http://www.w3.org/2000/svg'>
          <defs><style>.box { fill: red; }</style></defs>
          <rect class='box' width='50' height='50'/>
        </svg>""";
    assertThat(SVGSecurityScanner.isSafe(svg)).isTrue();
  }

  @Test
  void scriptElement_isNotSafe() {
    String svg = "<svg xmlns='http://www.w3.org/2000/svg'><script>alert(1)</script></svg>";
    assertThat(SVGSecurityScanner.isSafe(svg)).isFalse();
  }

  @Test
  void foreignObject_isNotSafe() {
    String svg = "<svg xmlns='http://www.w3.org/2000/svg'><foreignObject><body>evil</body></foreignObject></svg>";
    assertThat(SVGSecurityScanner.isSafe(svg)).isFalse();
  }

  @Test
  void namespacedScript_isNotSafe() {
    String svg = "<svg xmlns='http://www.w3.org/2000/svg'><svg:script>alert(1)</svg:script></svg>";
    assertThat(SVGSecurityScanner.isSafe(svg)).isFalse();
  }

  @Test
  void onloadAttribute_isNotSafe() {
    String svg = "<svg xmlns='http://www.w3.org/2000/svg' onload='alert(1)'><rect width='10' height='10'/></svg>";
    assertThat(SVGSecurityScanner.isSafe(svg)).isFalse();
  }

  @Test
  void onmouseoverAttribute_isNotSafe() {
    String svg = "<svg xmlns='http://www.w3.org/2000/svg'><rect onmouseover='alert(1)' width='10' height='10'/></svg>";
    assertThat(SVGSecurityScanner.isSafe(svg)).isFalse();
  }

  @Test
  void onclickAttribute_isNotSafe() {
    String svg = "<svg xmlns='http://www.w3.org/2000/svg'><rect onclick='alert(1)' width='10' height='10'/></svg>";
    assertThat(SVGSecurityScanner.isSafe(svg)).isFalse();
  }

  @Test
  void javascriptHref_isNotSafe() {
    String svg = "<svg xmlns='http://www.w3.org/2000/svg'><a href='javascript:alert(1)'><rect width='10' height='10'/></a></svg>";
    assertThat(SVGSecurityScanner.isSafe(svg)).isFalse();
  }

  @Test
  void vbscriptHref_isNotSafe() {
    String svg = "<svg xmlns='http://www.w3.org/2000/svg'><a href='vbscript:MsgBox(1)'><rect width='10' height='10'/></a></svg>";
    assertThat(SVGSecurityScanner.isSafe(svg)).isFalse();
  }

  @Test
  void dataHref_isNotSafe() {
    String svg = "<svg xmlns='http://www.w3.org/2000/svg'><a href='data:text/html,<script>alert(1)</script>'><rect width='10' height='10'/></a></svg>";
    assertThat(SVGSecurityScanner.isSafe(svg)).isFalse();
  }

  @Test
  void entityDeclaration_isNotSafe() {
    String svg = "<!DOCTYPE svg [<!ENTITY xxe SYSTEM 'file:///etc/passwd'>]><svg xmlns='http://www.w3.org/2000/svg'>&xxe;</svg>";
    assertThat(SVGSecurityScanner.isSafe(svg)).isFalse();
  }

  @Test
  void animateElement_isNotSafe() {
    String svg = "<svg xmlns='http://www.w3.org/2000/svg'><rect width='10' height='10'><animate attributeName='onload' values='alert(1)'/></rect></svg>";
    assertThat(SVGSecurityScanner.isSafe(svg)).isFalse();
  }

  @Test
  void animateTransform_isNotSafe() {
    String svg = "<svg xmlns='http://www.w3.org/2000/svg'><rect width='10' height='10'><animateTransform attributeName='transform' type='rotate' from='0' to='360'/></rect></svg>";
    assertThat(SVGSecurityScanner.isSafe(svg)).isFalse();
  }

  @Test
  void setElement_isNotSafe() {
    String svg = "<svg xmlns='http://www.w3.org/2000/svg'><rect width='10' height='10'><set attributeName='href' to='javascript:alert(1)'/></rect></svg>";
    assertThat(SVGSecurityScanner.isSafe(svg)).isFalse();
  }

  @Test
  void cssExpressionInStyleAttribute_isNotSafe() {
    String svg = "<svg xmlns='http://www.w3.org/2000/svg'><rect style='width:expression(alert(1))' width='10' height='10'/></svg>";
    assertThat(SVGSecurityScanner.isSafe(svg)).isFalse();
  }

  @Test
  void cssExpressionInStyleElement_isNotSafe() {
    String svg = "<svg xmlns='http://www.w3.org/2000/svg'><style>rect { width: expression(alert(1)); }</style><rect width='10' height='10'/></svg>";
    assertThat(SVGSecurityScanner.isSafe(svg)).isFalse();
  }

  @Test
  void cssUrlJavascriptInStyleElement_isNotSafe() {
    String svg = "<svg xmlns='http://www.w3.org/2000/svg'><style>rect { background: url(javascript:alert(1)); }</style><rect width='10' height='10'/></svg>";
    assertThat(SVGSecurityScanner.isSafe(svg)).isFalse();
  }

  @Test
  void javascriptHrefCaseInsensitive_isNotSafe() {
    String svg = "<svg xmlns='http://www.w3.org/2000/svg'><a href='JAVASCRIPT:alert(1)'><rect width='10' height='10'/></a></svg>";
    assertThat(SVGSecurityScanner.isSafe(svg)).isFalse();
  }

  @Test
  void animateMotion_isNotSafe() {
    String svg = "<svg xmlns='http://www.w3.org/2000/svg'><circle r='5'><animateMotion dur='2s' repeatCount='indefinite' path='M0,0 L100,100'/></circle></svg>";
    assertThat(SVGSecurityScanner.isSafe(svg)).isFalse();
  }

  @Test
  void animateColor_isNotSafe() {
    String svg = "<svg xmlns='http://www.w3.org/2000/svg'><rect width='10' height='10'><animateColor attributeName='fill' from='red' to='blue' dur='2s'/></rect></svg>";
    assertThat(SVGSecurityScanner.isSafe(svg)).isFalse();
  }

  @Test
  void xlinkHrefJavascript_isNotSafe() {
    String svg = "<svg xmlns='http://www.w3.org/2000/svg' xmlns:xlink='http://www.w3.org/1999/xlink'><a xlink:href='javascript:alert(1)'><rect width='10' height='10'/></a></svg>";
    assertThat(SVGSecurityScanner.isSafe(svg)).isFalse();
  }

  @Test
  void obfuscatedJavascriptHref_isNotSafe() {
    // tab character (%09) between "java" and "script" — canonicalizeScheme strips it
    String svg = "<svg xmlns='http://www.w3.org/2000/svg'><a href='java%09script:alert(1)'><rect width='10' height='10'/></a></svg>";
    assertThat(SVGSecurityScanner.isSafe(svg)).isFalse();
  }

  @Test
  void cssUrlJavascriptInStyleAttribute_isNotSafe() {
    String svg = "<svg xmlns='http://www.w3.org/2000/svg'><rect style='background:url(javascript:alert(1))' width='10' height='10'/></svg>";
    assertThat(SVGSecurityScanner.isSafe(svg)).isFalse();
  }

  @Test
  void inputStream_cleanSvg_isSafe() throws Exception {
    String svg = "<svg xmlns='http://www.w3.org/2000/svg'><rect width='100' height='100' fill='green'/></svg>";
    var stream = new ByteArrayInputStream(svg.getBytes(StandardCharsets.UTF_8));
    assertThat(new SVGSecurityScanner().isSafe(stream)).isTrue();
  }

  @Test
  void inputStream_scriptElement_isNotSafe() throws Exception {
    String svg = "<svg xmlns='http://www.w3.org/2000/svg'><script>alert(1)</script></svg>";
    var stream = new ByteArrayInputStream(svg.getBytes(StandardCharsets.UTF_8));
    assertThat(new SVGSecurityScanner().isSafe(stream)).isFalse();
  }
}
