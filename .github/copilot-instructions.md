# GitHub Copilot Instructions for Axon Ivy Portal

## Project Context
Enterprise BPM portal built on Axon Ivy Platform using Java, PrimeFaces/JSF, and Maven.

**Key Technologies**: Java, XHTML/JSF, PrimeFaces, PrimeFlex, Axon Ivy API, Apache Commons

## Project Structure

**Main Modules**:
- `portal` - Core portal application
- `portal-components` - Reusable UI components library  
- `portal-selenium-test` - UI tests
- `Documentation/` - Sphinx documentation

**Directory Layout**:
- `src/` - Java source
- `src_hd/` - XHTML UI files
- `webContent/` - Web resources (CSS, JS, images)
- `processes/` - Process definitions

## Code Standards

### Java

#### Formatting
- **Indentation**: 2 spaces (NO TABS)
- **Braces**: End of line, never new line
- **Spacing**: Space after comma, before/after operators, after keywords
- **Imports**: Remove unused, standard order (java.*, javax.*, org.*, com.*, ch.*)

#### Patterns
1. **Null Safety**: Use `Objects.nonNull()`, `CollectionUtils.emptyIfNull()`, `StringUtils.isEmpty()`
2. **Constants**: Use `StringUtils.EMPTY` not `""`, `StringUtils.SPACE` not `" "`
3. **Logging**: Use `Ivy.log()` with parameters, NEVER `System.out.println()`
4. **Streams**: Prefer Java streams for collections

#### Naming
- **Classes**: PascalCase, suffix DTOs with `DTO`, Beans with `Bean`
- **Methods**: camelCase, action verbs
- **Variables**: camelCase, initialize where declared
- **Constants**: UPPER_SNAKE_CASE
- **Packages**: lowercase
  - **New files in portal module**: Use `com.axonivy.portal.*` (preferred)
  - Legacy: `ch.ivy.addon.portalkit.*` (existing files only, don't add new files here)
  - **portal-components**: Use `com.axonivy.portal.components.*`

#### Documentation
- Never ignore caught exceptions - log or document why

### HTML/XHTML

#### Naming
- **IDs/Classes**: Use hyphens: `user-name-input` (not `userName_input`)
- **Suffixes**: `-input`, `-select`, `-command`, `-container`, `-form`

#### Standards
- Lowercase tags, quoted attributes
- Use `&amp;` in URLs
- All images need `alt` attributes
- No inline `style` - use CSS classes
- Prefer PrimeFlex utilities: `w-full`, `flex`, `mb-4`, `p-3`, `gap-2`

### CSS

- 2 spaces indentation (no tabs)
- Single quotes for values
- Omit units after 0
- Use shorthand properties

### JavaScript

- camelCase for variables/functions, UPPERCASE for constants
- Use `===` not `==`
- Avoid magic numbers - define constants
- 2 spaces indentation
- Space after keywords, around operators

## Common Anti-Patterns to Flag

1. **Avoid hardcoded values** - Use constants or configuration
2. **Don't use empty string literals** - Use `StringUtils.EMPTY` instead of `""`
3. **Don't use magic numbers** - Define constants with descriptive names or add explanatory comments
4. **Don't catch generic exceptions** - Be specific with exception handling
5. **Never ignore caught exceptions** - Always log or document why it's ignored
6. **Avoid deep nesting** - Extract methods for better readability
7. **Don't use raw types** - Always use generics with collections
8. **Avoid mutable static fields** - Ensure thread safety
9. **Don't ignore null checks** - Always validate inputs
10. **Avoid business logic in UI beans** - Delegate to service/utility classes
11. **Don't start variable names with `_` or `$`** - Follow proper naming conventions
12. **Don't defer variable initialization** - Initialize where declared unless computation is needed first

## File-Specific Review Focus

### When reviewing `.java` files:
- ✓ 2 spaces indentation (no tabs)
- ✓ Remove unused imports
- ✓ Null safety handling
- ✓ Proper exception handling
- ✓ Resource leaks (streams, connections)
- ✓ Thread safety for shared resources

### When reviewing `.xhtml` files:
- ✓ 2 spaces indentation (no tabs)
- ✓ PrimeFlex utility classes for styling
- ✓ Accessibility attributes (ARIA labels, alt text)
- ✓ Proper i18n/localization
- ✓ ID naming uses hyphens with proper suffixes
- ✓ No inline `style` attributes
- ✓ All images have `alt` attributes
- ✓ Keyboard accessible
- ✓ Proper contrast ratios

### When reviewing `.css` files:
- ✓ 2 spaces indentation (no tabs)
- ✓ Single quotes for values
- ✓ Shorthand properties
- ✓ Omit units after 0

### When reviewing `.js` files:
- ✓ No magic numbers - use named constants
- ✓ camelCase for variables/functions, UPPERCASE for constants
- ✓ Use `===` not `==`
- ✓ Avoid global variables
- ✓ 2 spaces indentation

### When reviewing `.rst` files:
- ✓ Follow Read the Docs formatting standards
- ✓ Consistent heading underlines
- ✓ 3 spaces indentation for directive content
- ✓ Blank lines before/after directives
- ✓ No trailing whitespace

## Accessibility Standards (WCAG 2.2)

**Critical Requirements**:
1. **Non-text Content**: All images have `alt`, icon buttons have `ariaLabel`
2. **Info & Relationships**: Proper ARIA roles, `<label for="">` for inputs
3. **Keyboard Access**: All functionality keyboard accessible, focus management in dialogs
4. **Contrast**: 4.5:1 for normal text, 3:1 for large text
5. **Error Identification**: Clear error messages visible to all users

**Common Patterns**:
```javascript
// Left Menu ARIA
let parentMenu = $("[id$='main-menu']");
parentMenu.attr('role', 'menu');

// Dialog Focus
function focusFirstVisibleElementInPanel(widgetVar, selector) {
  var panel = PF(widgetVar).jq;
  panel.find(selector).first().focus();
}
```

## Security Considerations

1. **Data Sanitization**: Sanitize inputs, prevent XSS vulnerabilities
2. **No Sensitive Data**: Never commit passwords, API keys, tokens, credentials, or personal data
3. **No Binary Files**: Don't commit compiled files (.class, .jar), images (use CMS), or large binary files

## Review Prompts

When reviewing code, consider:
- Does this follow Axon Ivy Portal coding patterns?
- Are all edge cases and null values handled?
- Does this maintain backward compatibility?

---

*Keep this file focused on actionable standards that improve code quality and consistency.*
