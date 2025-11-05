# GitHub Copilot Review Instructions for Axon Ivy Portal

## Project Overview
This is the **Axon Ivy Portal** - the main end-user portal for the Axon Ivy Platform. It's a comprehensive enterprise portal solution built on the Axon Ivy BPM platform.

### Repository Information
- **Repository**: portal
- **Owner**: pvthai-axonivy  
- **Primary Branch**: master
- **License**: Apache License 2.0
- **Current Version**: 13.2.0-SNAPSHOT
- **Supported Versions**: 13.x.x, 12.0.x, 10.0.x

## Technology Stack

### Core Technologies
- **Language**: Java (Axon Ivy Platform)
- **Build Tool**: Maven
- **UI Framework**: PrimeFaces (XHTML/JSF)
- **CSS Framework**: PrimeFlex (utility-first CSS)
- **Testing**: Selenium/Selenide (web-tester)
- **Performance Testing**: JMeter
- **Documentation**: Sphinx/reStructuredText

### Key Dependencies
- Axon Ivy API (ivy-api, ivy-notification-channel-api, ivy-cluster-api)
- Google Gson
- Apache Commons Collections4, Lang3
- PrimeFaces components

## Project Structure

### Main Modules
1. **AxonIvyPortal/portal** - Core portal application (IAR)
2. **AxonIvyPortal/portal-components** - Reusable portal components library (IAR)
3. **AxonIvyPortal/portal-selenium-test** - Selenium-based UI tests
4. **AxonIvyPortal/portal-performance-test** - Performance testing suite
5. **AxonIvyPortal/portal-product** - Product packaging and distribution
6. **AxonIvyPortal/PortalKitTestHelper** - Testing utilities
7. **Showcase/** - Example applications demonstrating portal features
   - InternalSupport
   - portal-components-examples
   - portal-demo-app
   - portal-developer-examples
   - portal-user-examples
8. **Documentation/** - Portal user and developer guides

### Source Directory Structure
- `src/` - Java source code
- `src_hd/` - HTML Dialog (XHTML) files
- `src_dataClasses/` - Data class definitions
- `src_generated/` - Auto-generated code
- `src_wsproc/` - Workflow process definitions
- `src_test/` - Test code (for selenium-test module)
- `webContent/` - Web resources (CSS, JS, images)
- `cms/` - Content Management System files
- `processes/` - Process model files

## Code Review Guidelines

### Java Code Standards

#### Code Formatting
- **Indentation**: Use 2 spaces for indentation (NO TABS)
- **Line Endings**: Consistent line endings
- **Maximum Line Length**: 120 characters (split longer lines)
- **Braces Position**: End of line (not new line) for classes, methods, blocks, if/else, try/catch, etc.
  ```java
  // Good - braces at end of line
  public class MyClass {
    public void myMethod() {
      if (condition) {
        doSomething();
      } else {
        doSomethingElse();
      }
    }
  }
  
  // Bad - braces on new line
  public class MyClass
  {
    public void myMethod()
    {
      if (condition)
      {
        doSomething();
      }
    }
  }
  ```
- **Spacing Rules**:
  - Space after comma in parameters, arguments, type arguments: `method(arg1, arg2, arg3)`
  - Space before and after binary operators: `x = y + z`, `a && b`
  - Space before opening brace: `if (condition) {`
  - Space after keywords: `if (`, `while (`, `for (`, `try {`
  - NO space after opening paren or before closing paren: `if (condition)` not `if ( condition )`
  - NO space before semicolon: `statement;` not `statement ;`
  - NO space after unary operators: `!flag`, `++i`
- **Blank Lines**:
  - 1 blank line after package declaration
  - 1 blank line after imports
  - 1 blank line before methods
  - 2 blank lines between type declarations
  - Preserve up to 3 consecutive blank lines
- **Imports**: Remove unused imports - keep imports clean and organized
- **Import Order**: Standard Java import ordering (java.*, javax.*, org.*, com.*, ch.*)

#### Package Structure
- **Core utilities**: `ch.ivy.addon.portalkit.util.*`
- **Business beans**: `ch.ivy.addon.portalkit.bean.*`
- **Enumerations**: `ch.ivy.addon.portalkit.enums.*`
- **DTOs**: `com.axonivy.portal.components.dto.*`
- **Public API**: `com.axonivy.portal.components.publicapi.*`
- **Converters**: `com.axonivy.portal.components.converter.*`
- **Constants**: `com.axonivy.portal.components.constant.*`

#### Code Patterns to Enforce

1. **Security Context Usage**
   - Always use `Sudo.get()` when accessing security-related operations
   - Example pattern from RoleUtils:
   ```java
   public static List<IRole> getAllRoles() {
     return Sudo.get(() -> {
       return ISecurityContext.current().roles().all();
     });
   }
   ```

2. **Null Safety**
   - Use `Objects.nonNull()` and `Objects.isNull()` for null checks
   - Use `CollectionUtils.emptyIfNull()` when working with collections
   - Use `StringUtils.isEmpty()` for string validation
   - Use `StringUtils.EMPTY` instead of `""` for empty string constants
   - Use `StringUtils.SPACE` instead of `" "` for space constants
   - Use Apache Commons utilities for safe operations

3. **Utility Classes**
   - Mark utility classes as `final`
   - Provide private constructor: `private UtilityName() {}`
   - All methods should be `static`

4. **Documentation Standards**
   - All public methods must have Javadoc
   - Include `@param` and `@return` tags
   - Document `<p>` sections for detailed explanations
   - Describe special behavior, conditions, and edge cases
   
5. **Logging Standards**
   - Use Axon Ivy's logging framework (`ch.ivyteam.log.Logger` or `Ivy.log()`)
   - Use appropriate log levels: `error()`, `warn()`, `info()`, `debug()`
   - Never use `System.out.println()` or `System.err.println()` - use logger instead
   - Use parameterized logging: `Ivy.log().info("User {0} logged in", username)` instead of string concatenation
   - Log exceptions with stack traces: `Ivy.log().error("Error processing request", exception)`
   - Use meaningful log messages that provide context
   - Avoid logging sensitive information (passwords, tokens, personal data)
   - Example:
   ```java
   // For context-based logging (preferred in most cases)
   Ivy.log().info("Processing order {0} for user {1}", orderId, userId);
   Ivy.log().error("Failed to connect to database", exception);
   
   // For class-based logging (when Ivy context not available)
   Logger.getLogger(ClassName.class).error("PortalSessionExtension destroySession", e);
   
   // Bad - Never do this
   System.out.println("Processing order: " + orderId); // Never use System.out
   Ivy.log().info("Error: " + exception.getMessage()); // Don't concatenate, use parameters
   ```

6. **Stream API Usage**
   - Prefer Java Streams for collection filtering and transformations
   - Use method references and predicates
   - Example pattern:
   ```java
   private static Predicate<IRole> predicateIsHiddenRole() {
     return role -> Objects.nonNull(role.getProperty(AdditionalProperty.HIDE.toString()));
   }
   
   private static List<IRole> filterRole(List<IRole> roles, Predicate<IRole> predicate) {
     return CollectionUtils.emptyIfNull(roles).stream()
       .filter(predicate)
       .collect(Collectors.toList());
   }
   ```

#### Naming Conventions

**Packages**
- **All-lowercase ASCII letters**
- Should be one of the top-level domain names (com, edu, gov, mil, net, org) or English two-letter country codes
- Example: `com.axonivy.portal`, `ch.ivy.addon.portalkit`, `com.sun.eng`

**Classes**
- **PascalCase** with first letter of each word capitalized (e.g., `RoleUtils`, `AdminSettingBean`)
- **Should be nouns** - Keep simple and descriptive
- **DTOs**: Suffix with `DTO` (e.g., `RoleDTO`, `BusinessDetailsDTO`)
- **Beans**: Suffix with `Bean` (e.g., `AvatarBean`, `CaseActionBean`)
- **Enums**: Singular noun in PascalCase

**Methods/Functions**
- **camelCase** with lowercase first letter
- Use descriptive action verbs (e.g., `getAllRoles`, `filterVisibleRoles`)
- **Functions should do one thing** - They should do it well and do it only
- Keep methods focused and single-purpose

**Variables**
- **camelCase** with lowercase first letter
- **Should not start with underscore `_` or dollar sign `$`**
- Should be short yet meaningful
- Avoid one-character names except for temporary variables (i, j, k, m, n for integers; c, d, e for characters)
- **Initialize where declared** - Only defer initialization if initial value depends on computation

**Constants**
- **UPPER_SNAKE_CASE** - All uppercase with words separated by underscores
- Example: `HIDE_IN_DELEGATION`, `DEFAULT_HIDDEN_ROLES`, `MAX_INDEX`
- Use with `static final` for primitive/immutable types
- Consider using Enum for related constants (Java 1.5+)

**Private Methods**
- camelCase with descriptive names

   ```

#### Exception Handling
- **Never ignore caught exceptions** - Except in rare justified cases
- **Log or re-throw exceptions** - Typical responses: log it, or re-throw as `AssertionError` if "impossible"
- **Document ignored exceptions** - If truly appropriate to ignore, explain in a comment
- **Example of justified ignore**:
  ```java
  try {
    int i = Integer.parseInt(response);
    return handleNumericResponse(i);
  } catch (NumberFormatException ok) {
    // it's not numeric; that's fine, just continue
  }
  ```
- **Test exception naming** - In tests, use `expected` for exceptions that are intentionally caught:
  ```java
  try {
    emptyStack.pop();
    fail();
  } catch (NoSuchElementException expected) {
    // Expected exception, test passes
  }
  ```

### Bean (Managed Bean) Patterns
- Beans represent UI backing beans for PrimeFaces/JSF
- Follow JavaBean conventions (getters/setters)
- Ensure proper scope annotations
- Maintain separation between UI logic and business logic

### DTO (Data Transfer Object) Patterns
- Keep DTOs simple and focused on data transport
- Provide constructors that accept domain objects (e.g., `new RoleDTO(IRole role)`)
- Include necessary transformation logic

### HTML/CSS Code Standards

#### ID and Class Naming Convention
- **Separate words with hyphens**: Use `name-element-id-like-this` format
- **Never use underscores or camelCase**: Avoid `error_status` or `errorStatus`
- **Use lowercase only**: `video-id`, `ads-sample`
- **Name by intention, not function or appearance**: 
  - ✓ Good: `username-input`, `customer-autocomplete`
  - ✗ Bad: `left-menu`, `red-ok-button`, `top-rectangle-container`

#### Name Suffix Convention
- **Input elements**: `-input`, `-select`
- **Tables/Lists**: `-view`, `-browser`, `-explorer`
- **Labels/Text**: Name follows its intention
- **Buttons/Links**: `-command`, `-action`
- **Panels/Dialogs**: `-container`
- **Forms**: `-form`

**JSF Specific**: 
- Name `widgetVar` same as `id`
- Use `#{cc.clientId}` when adding id for component

#### HTML Tag Standards
- **Use lowercase tags**: `<table></table>` not `<TABLE></TABLE>`
- **Quote all attributes**: `<option value="0">` not `<option value=0>`
- **Use `&amp;` in URLs**: `?uniqcode=EMP&amp;VIEW=MAIN`
- **Always include `alt` attribute** for media tags (use `alt=""` for decorative images)
- **Don't close void elements**: `<br>` not `<br />`
- **Use semantic HTML**: Use elements for their intended purpose (e.g., `<a>` for links, not `<div onclick>`)

#### CSS Standards
- **Use single quotes** for attribute selectors and property values: `font-family: 'open sans', arial, sans-serif;`
- **No quotes in URI values**: `@import url(//www.example.com/css/style.css);`
- **Avoid qualifying ID/class with type selectors**: 
  - ✗ Bad: `ul#example {}`, `div.error {}`
  - ✓ Good: `#example {}`, `.error {}`
- **Use shorthand properties**: `padding: 0 1em 2em;` instead of separate properties
- **Omit units after 0**: `margin: 0;` not `margin: 0px;`
- **Omit leading 0 in decimals**: `font-size: .8em;` not `font-size: 0.8em;`
- **Use 3-char hex notation**: `color: #ebc;` instead of `color: #eebbcc;`
- **Group sections with comments**:
  ```css
  /* Header */
  #portal-header {}
  
  /* Footer */
  #portal-footer {}
  ```

#### Style and Structure Separation
- **No inline styles**: Use CSS classes instead of `style` attributes
  - ✗ Bad: `<div style="width:100px;align:center;">`
  - ✓ Good: `<div class="message">`
- **Reference images from CSS**: Not directly in HTML when possible
- **Separate concerns**: Keep structure (HTML), presentation (CSS), and behavior (JS) apart
- **Minimize comments in HTML**: Use descriptive element names instead of heavy commenting

#### Code Formatting for HTML/CSS
- **Indentation**: 2 spaces (no tabs)
- **Maximum line length**: 120 characters
- **Space after colon in CSS**: `font-size: 10px;` not `font-size:10px;`

### JavaScript Code Standards

#### Naming Conventions
- **Variables and functions**: camelCase (e.g., `userName`, `getElementById`, `calculateTotal`)
- **Global variables and constants**: UPPERCASE (e.g., `TIMEOUT_IN_MILLISECONDS`, `MAX_RETRY_COUNT`)
- **Special cases for acronyms**: 
  - `XMLHttpRequest` → `XmlHttpRequest`
  - `iOS` → `Ios`
  - `employeeID` → `employeeId`
  - `userName` → `username`

#### Code Formatting
- **Indentation**: 2 spaces (no tabs)
- **Line length**: ≤ 120 characters - break long lines
- **Braces Position**: End of line (not new line) for functions, blocks, if/else, try/catch, etc.
  ```javascript
  // Good - braces at end of line
  function myFunction() {
    if (condition) {
      doSomething();
    } else {
      doSomethingElse();
    }
  }
  
  // Bad - single line without braces
  if (condition) doSomething();
  
  // Bad - braces on new line
  function myFunction()
  {
    if (condition)
    {
      doSomething();
    }
  }
  ```
- **Spacing Rules**:
  - Space before and after binary operators: `x = y + z`, `a && b`
  - Space before opening brace: `if (condition) {`, `function() {`
  - Space after keywords: `if (`, `while (`, `for (`, `try {`
  - Space after comma: `myFunction(arg1, arg2, arg3)`, `var arr = [1, 2, 3];`
  - Space after colon in object literals: `{key: value}`
  - Space before colon in object literals: `{key: value}`
  - NO space after opening paren or before closing paren: `if (condition)` not `if ( condition )`
  - NO space before semicolon: `statement;` not `statement ;`
  - NO space after unary operators: `!flag`, `++i`
  - NO space before opening bracket in arrays: `arr[0]` not `arr [0]`
- **Blank Lines**:
  - 1 blank line before methods
  - 1 blank line after imports
  - Preserve up to 1 consecutive blank line
- **Arrays and function calls**: No space before opening bracket/paren
  ```javascript
  var arr = [1, 2, 3];
  myFunction(param1, param2);
  ```

#### Variable Declarations
- **Assignments in declarations**: Must be on their own line
- **Group non-initialized declarations**: List together at start
- **Indentation**: Each line after initial must be indented once
  ```javascript
  // Good
  var firstName, lastName, age;
  var userName = 'john';
  var isActive = true;
  
  // Bad
  var userName = 'john', isActive = true;
  ```

#### Comments
- **Start with capital letter**: Don't require period unless full sentences
- **Single space after comment token**: `// Comment` not `//Comment`
  ```javascript
  // This is a good comment
  // This is another comment. This one is a full sentence.
  ```

#### Best Practices
- **Avoid global variables**: Can be overwritten by other scripts
- **Avoid `new` for primitives**: Treat numbers, strings, booleans as primitive values, not objects
  ```javascript
  // Good
  var x = '';
  var y = 0;
  var z = false;
  
  // Bad - slows execution and causes side effects
  var x = new String('');
  var y = new Number(0);
  var z = new Boolean(false);
  ```
- **Use `===` instead of `==`**: The `==` operator converts types before comparison, `===` forces type checking
  ```javascript
  // Good
  if (x === 5) {}
  
  // Bad - type coercion issues
  if (x == 5) {}
  ```
- **Beware automatic type conversions**: JavaScript can convert numbers to strings in mathematical operations

#### Common Pitfalls
- **Float precision**: All numbers are 64-bit floating point
  ```javascript
  var x = 0.1 + 0.2; // Not exactly 0.3
  // Use precision: (x * 10 + y * 10) / 10
  ```
- **Breaking strings**: Use proper methods
  ```javascript
  // Good
  var text = 'This is a long string ' +
             'broken into multiple lines';
  
  // Also good
  var text = 'This is a long string \
  broken into multiple lines';
  
  // Bad - syntax error
  var text = 'This is a long string
  broken into multiple lines';
  ```

#### Magic Numbers and Constants
- **Never use magic numbers** - All numeric literals must be named constants or have explanatory comments
- **Define constants at the top** - Use `const` for all constant values
- **Descriptive naming** - Constants should clearly describe their purpose
- **Comment complex logic** - Add comments explaining the reasoning behind calculations

Example of GOOD practice:
```javascript
const DEFAULT_TIMEOUT_MS = 3000;
const MAX_RETRY_ATTEMPTS = 3;
const ANIMATION_DURATION = 300; // milliseconds for smooth transitions

function scheduleTask() {
  setTimeout(taskFunction, DEFAULT_TIMEOUT_MS);
}
```

Example of BAD practice (avoid this):
```javascript
function scheduleTask() {
  setTimeout(taskFunction, 3000); // What is 3000? Why this value?
}
```

#### Code Clarity
- Extract complex expressions into well-named variables
- Add comments for non-obvious business logic
- Use meaningful variable and function names
- Keep functions focused and single-purpose

#  ```

### reStructuredText (.rst) Code Standards

#### Purpose
- Used for Sphinx documentation in `Documentation/portal-guide/`
- Must follow Read the Docs formatting to avoid build warnings
- Critical for maintaining clean documentation builds

#### Heading Hierarchy
Use consistent underline characters in this order:
```rst
Page Title (Level 1)
********************

Section (Level 2)
=================

Subsection (Level 3)
--------------------

Sub-subsection (Level 4)
^^^^^^^^^^^^^^^^^^^^^^^^

Paragraph (Level 5)
"""""""""""""""""""
```

**Rules**:
- Underline must be at least as long as the title text
- Use same character for same heading level throughout document
- Blank line before and after headings

#### Directives and Syntax

**Code Blocks**:
```rst
.. code-block:: java
   
   public class Example {
     // Code here
   }
```

**Images**:
```rst
.. image:: path/to/image.png
   :alt: Alternative text
   :width: 600px
```

**Links**:
```rst
`Link text <https://example.com>`_
```

**Internal References**:
```rst
.. _label-name:

Section Title
=============

Reference it with :ref:`label-name`
```

**Includes**:
```rst
.. include:: ../includes/_common-icon.rst
```

**Table of Contents**:
```rst
.. toctree::
   :maxdepth: 2
   :caption: Portal
   
   portal-user-guide/index
   portal-developer-guide/index
```

#### Formatting Rules
- **Indentation**: 3 spaces for directive content
- **Blank lines**: Required before and after directives
- **Lists**: Consistent markers (-, *, or +)
- **Escaping**: Use backslash for special characters: ``\*not bold\*``
- **Inline code**: Use double backticks: `` `code` ``
- **Bold**: ``**bold text**``
- **Italic**: ``*italic text*``
- **No trailing whitespace**: Remove all trailing spaces

#### Common Warnings to Avoid
- Missing blank line before/after directive
- Heading underline too short
- Undefined reference labels
- Malformed hyperlinks
- Inconsistent indentation in directives
- Missing image files
- Broken include paths

#### Best Practices
- Keep line length reasonable (≤ 120 characters for readability)
- Use descriptive labels for cross-references
- Always provide alt text for images
- Test builds locally before committing
- Check Sphinx warnings during documentation builds

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

### Testing Standards

#### Selenium Tests
- Located in `portal-selenium-test/src_test/`
- Use Selenide framework (web-tester dependency)
- Follow page object pattern for UI elements
- Tests should be independent and idempotent

#### Performance Tests
- JMeter-based performance tests in `portal-performance-test/`
- Follow DSL patterns for test definition
- Include realistic load scenarios

### File-Specific Review Focus

#### When reviewing `.java` files:
- ✓ Check indentation is exactly 2 spaces (no tabs)
- ✓ Verify all unused imports are removed
- ✓ Check Javadoc completeness
- ✓ Verify null safety handling
- ✓ Ensure proper use of Sudo for security operations
- ✓ Validate proper exception handling
- ✓ Check for resource leaks (streams, connections)
- ✓ Verify thread safety for shared resources
- ✓ Look for potential performance issues (N+1 queries, inefficient loops)

#### When reviewing `.xhtml` files:
- ✓ Check indentation is exactly 2 spaces (no tabs)
- ✓ Check for proper PrimeFaces component usage
- ✓ Use PrimeFlex utility classes for styling (e.g., `w-full`, `flex`, `justify-content-between`, `align-items-center`, `mb-4`, `p-3`, `gap-2`)
- ✓ Prefer PrimeFlex utilities over inline styles or custom CSS classes
- ✓ Verify backing bean bindings
- ✓ Ensure accessibility attributes (ARIA labels)
- ✓ Check for proper i18n/localization
- ✓ Validate responsive design considerations
- ✓ Check ID naming uses hyphens (e.g., `user-name-input`, not `userName_input`)
- ✓ Verify ID suffixes follow conventions (`-input`, `-command`, `-container`, etc.)
- ✓ Ensure no inline `style` attributes (use CSS classes)
- ✓ Check all HTML tags are lowercase
- ✓ Verify all attributes are quoted
- ✓ Ensure `&amp;` is used in URLs instead of `&`
- ✓ Check all `<img>` tags have `alt` attributes
- ✓ Verify semantic HTML usage (proper element for purpose)
- ✓ **Accessibility checks**:
  - All images have descriptive `alt` attributes
  - Icon-only buttons have `ariaLabel` attributes
  - All inputs have `<label for="">` elements
  - Interactive elements have proper ARIA roles (`role="menu"`, `role="menuitem"`, etc.)
  - Focus management in dialogs (`focus` attribute or `focusFirstVisibleElementInPanel()`)
  - Required fields marked with `aria-required="true"`
  - Tab order matches visual order
  - Keyboard accessible (test without mouse)
  - Proper contrast ratios (4.5:1 for normal text, 3:1 for large text)
  - Page has proper `<title>` and heading structure

#### When reviewing `.css` files:
- ✓ Check indentation is exactly 2 spaces (no tabs)
- ✓ Verify use of single quotes for attribute selectors and property values
- ✓ Check IDs/classes aren't qualified with type selectors
- ✓ Ensure shorthand properties are used where possible
- ✓ Verify units are omitted after 0 values
- ✓ Check leading 0 is omitted in decimals (`.8em` not `0.8em`)
- ✓ Verify 3-character hex notation is used where possible
- ✓ Check sections are grouped with comments
- ✓ Ensure space after colon in property declarations

#### When reviewing `.js` or JavaScript code:
- ✓ Check for magic numbers - all numeric literals should be named constants or have comments
- ✓ Verify constants are defined at the top of the file/function with descriptive names
- ✓ Ensure complex logic has explanatory comments
- ✓ Check that variable and function names are meaningful and descriptive
- ✓ Validate that functions are focused and not too complex
- ✓ Verify camelCase for variables/functions, UPPERCASE for constants
- ✓ Check spacing around operators (spaces required)
- ✓ Ensure `if/else/for/while/try` have braces and go on multiple lines
- ✓ Verify line length ≤ 120 characters
- ✓ Check for global variables (should be avoided)
- ✓ Flag use of `new` with primitives (String, Number, Boolean)
- ✓ Flag use of `==` instead of `===`
- ✓ Check for proper string line breaks (use `+` or `\`)
- ✓ Verify comments start with capital and have space after `//`

#### When reviewing `.rst` (reStructuredText) files:
- ✓ Follow Read the Docs formatting standards to avoid warnings
- ✓ Use consistent heading underlines (=, -, ^, ", ~, *, +, #)
- ✓ Ensure heading underlines are at least as long as the title text
- ✓ Check proper indentation (3 spaces for directive content)
- ✓ Verify blank line before and after directives
- ✓ Check proper syntax for:
  - Links: `` `Link text <URL>`_ ``
  - Internal refs: ``:ref:`label-name` ``
  - Images: `.. image:: path/to/image.png`
  - Code blocks: `.. code-block:: language`
  - Includes: `.. include:: path/to/file.rst`
  - TOC trees: `.. toctree::`
- ✓ Ensure consistent bullet list markers (-, *, +)
- ✓ Verify proper escaping of special characters
- ✓ Check for missing blank lines that cause warnings
- ✓ Validate cross-reference labels are defined
- ✓ Ensure no trailing whitespace

#### When reviewing `pom.xml` files:
- ✓ Check indentation is exactly 2 spaces (no tabs)
- ✓ Verify version consistency (current: 13.2.0-SNAPSHOT)
- ✓ Check for proper dependency scopes
- ✓ Ensure parent POM reference is correct
- ✓ Validate repository configurations

#### When reviewing process/workflow files:
- ✓ Ensure proper error handling
- ✓ Verify business logic correctness
- ✓ Check for proper role/permission assignments

### Accessibility Standards (WCAG 2.2)

Portal follows Web Content Accessibility Guidelines (WCAG) 2.2 to ensure accessibility for people with disabilities.

#### Key Accessibility Requirements

**1. Non-text Content (1.1.1 - Level A)**
- All images must have `alt` attributes describing their purpose
- Icon-only buttons must have `ariaLabel` attributes
- User avatars, widget icons, and navigation icons must be accessible
- Example: `<p:graphicImage alt="User profile picture" />` or `<p:commandButton ariaLabel="Start process" />`

**2. Info & Relationships (1.3.1 - Level A)**
- Define proper ARIA roles for menus and navigation: `role="menu"`, `role="menuitem"`
- Use `<label for="">` for all input fields
- Ensure proper table headers and relationships
- Date pickers must show format patterns as placeholders

**3. Meaningful Sequence (1.3.2 - Level A)**
- DOM reading order must match visual order
- Provide descriptive `ariaLabel` for interactive elements
- Pattern examples:
  - Process: `"Process name: ${name}. Description: ${description}"`
  - Task: `"Task - Prio: ${priority} - Name: ${taskname} - Responsible: ${responsible}"`
  - Case: `"Case ID: ${caseid} - Name: ${name} - State: ${state}"`

**4. Contrast (1.4.3 - Level AA)**
- Normal text: minimum 4.5:1 contrast ratio
- Large text: minimum 3:1 contrast ratio
- Use color contrast checker tools to verify

**5. Resize Text (1.4.4 - Level AA)**
- Content must remain functional when zoomed up to 200%
- Truncated cell content must be accessible on focus
- No horizontal scrolling at 200% zoom

**6. Keyboard Access (2.1.1 - Level A)**
- All functionality must be keyboard accessible
- Test new features without using a mouse
- Modal dialogs must focus on first element: `focus="@(.ui-fileupload-choose)"`
- Non-modal dialogs must call `focusFirstVisibleElementInPanel()`
- Elements requiring focus must have `tabindex="0"`
- Support ESC key to close dialogs

**7. No Keyboard Trap (2.1.2 - Level A)**
- Users must be able to tab into and out of all components
- Never trap keyboard focus in any element

**8. Bypass Blocks (2.4.1 - Level A)**
- Every page must have headings (`h1`, `h2`, etc.) or `role="heading"` with `aria-level`
- Use landmarks: `role="region"`, `role="navigation"`, `role="banner"`
- Every widget should have `role="region"` with `aria-label`

**9. Page Titled (2.4.2 - Level A)**
- Every page must have descriptive `<title>`: `<Dashboard name> - Portal - <Application name>`
- Custom pages should define: `<ui:define name="title">`

**10. Focus Order (2.4.3 - Level A)**
- Tab order must follow logical, visual sequence
- Rearrange DOM if needed to match visual order

**11. Link Purpose (2.4.4 - Level A)**
- Link text must clearly describe its purpose
- Icon-only links must have `aria-label`

**12. Focus Visible (2.4.7 - Level AA)**
- Focused elements must have visible indicator
- Add `ui-focused` class if missing
- Override focus CSS for better visibility

**13. Label in Name (2.5.3 - Level A)**
- Visible labels must match accessible names
- `ariaLabel` must include the visible text
- Icon-only buttons need descriptive accessible names

**14. Language of Page (3.1.1 - Level A)**
- Set `lang` attribute on `<html>` tag
- Update when user changes language: `<html lang="en">`

**15. Consistent Navigation (3.2.3 - Level AA)**
- Navigation elements must be in consistent positions
- Menus and search boxes should not move between pages

**16. Error Identification (3.3.1 - Level A)**
- Error messages must be clear and visible
- Indicate which field has an error
- Don't rely on color alone

**17. Labels or Instructions (3.3.2 - Level A)**
- All inputs must have clear labels with `<label for="">`
- Required fields: `<span aria-hidden="true">*</span>` with `aria-required="true"`
- Radio button groups must use `<fieldset>` and `<legend>`

**18. Error Prevention (3.3.4 - Level AA)**
- Confirm dialogs for irreversible actions (delete, submit, etc.)
- Allow users to review before final submission

**19. Name, Role, Value (4.1.2 - Level A)**
- All interactive elements must expose name, role, and state to assistive technologies
- Use proper ARIA attributes
- Example: `role="radio"` for radio buttons

#### Common Portal Accessibility Patterns

**Left Menu (Collapsed Mode)**
```javascript
let parentMenu = $("[id$='main-menu']");
parentMenu.attr('role', 'menu');
parentMenu.find('li a').each((index, link) => {
  if ($(link).attr('aria-label') === undefined) {
    $(link).attr('aria-label', $(link).text());
  }
});
```

**SelectOneButton Fix**
```javascript
$('.ui-selectonebutton .ui-button').each((index, btn) => {
  if ($(btn).attr('role') === undefined) {
    $(btn).attr('role', 'radio');
  }
});
```

**Dialog Focus Management**
```javascript
function focusFirstVisibleElementInPanel(widgetVar, selector) {
  var panel = PF(widgetVar).jq;
  var first = panel.find(selector).first();
  if (first.length) {
    first.focus();
  }
}
```

#### Accessibility Testing Tools
- **NVDA**: Screen reader for Windows
- **Color Contrast Checker**: Chrome/Firefox extension
- **Landmark Navigation**: Keyboard landmark checker
- **HeadingsMap**: Heading structure checker
- **axe DevTools**: Comprehensive accessibility testing (recommended)
- **Chrome Lighthouse**: Built-in accessibility auditing

### Security Considerations

1. **Role-Based Access Control**
   - Always respect HIDE and HIDE_IN_DELEGATION properties
   - Use proper filtering methods (filterVisibleRoles, filterHiddenRoles)
   - Validate user permissions before operations

2. **Property Access**
   - Use `AdditionalProperty` enum for standard properties
   - Validate property values before use

3. **Data Sanitization**
   - Sanitize user inputs in UI components
   - Prevent XSS vulnerabilities in XHTML
   - Validate and escape data in JSF expressions

### Localization & Internationalization
- Portal supports multiple languages via Weblate
- All user-facing strings should be externalized
- Use proper CMS/resource bundle references
- Never hardcode user-visible text

### Performance Considerations

1. **Lazy Loading** - Use lazy initialization for expensive operations
2. **Caching** - Cache frequently accessed data appropriately
3. **Stream Processing** - Prefer streams for large collections
4. **Database Queries** - Optimize Ivy API calls, avoid N+1 patterns
5. **UI Rendering** - Minimize re-renders in PrimeFaces components

## Build & CI/CD Context

### Jenkins Pipelines
- Multiple Jenkinsfiles in `build/` directory
- CI types: integration, gui-test, performance-test, document-screenshot
- Docker-based build environments

### Packaging
- IAR (Ivy Archive) format for modules
- Product assembly via `portal-product/`
- Distribution via Axon Ivy Market

## Documentation Standards

### Code Comments
- Use `//` for single-line comments
- Use `/* */` for multi-line explanations
- Javadoc (`/** */`) for all public APIs

### README Files
- Each major module should have a README
- Include setup, usage, and contribution guidelines

## Quality Metrics to Check

1. **Code Coverage** - Adequate test coverage for new features
2. **Complexity** - Keep cyclomatic complexity low (< 10 per method)
3. **Maintainability** - Code should be self-documenting
4. **DRY Principle** - Avoid code duplication
5. **SOLID Principles** - Especially Single Responsibility

## Common Review Scenarios

### When Adding New Features:
1. Check if public API needs updates
2. Verify backward compatibility
3. Ensure proper documentation
4. Add appropriate tests
5. Update showcase examples if applicable

### When Fixing Bugs:
1. Verify root cause is addressed
2. Add regression test
3. Check for similar issues in codebase
4. Update documentation if behavior changes

### When Refactoring:
1. Ensure no functional changes
2. Verify all tests still pass
3. Check performance impact
4. Update comments/documentation

## Specific Review Prompts

When reviewing code, consider:
- "Does this follow the Axon Ivy Portal coding patterns?"
- "Is security properly handled with Sudo.get() where needed?"
- "Are all edge cases and null values handled?"
- "Is this properly documented for other developers?"
- "Would this work correctly in a multi-tenant environment?"
- "Is this internationalization-ready?"
- "Does this maintain backward compatibility?"
- "Are there potential performance bottlenecks?"

## Contributing Guidelines Reference

All changes should:
1. Be tested and working
2. Target the `master` branch
3. Follow the code standards outlined above
4. Include appropriate documentation
5. Consider localization needs

## Additional Context

### Axon Ivy Platform Specifics
- **IRole, IUser, ISecurityContext**: Core security interfaces
- **IAR**: Ivy Archive - deployable module format
- **Process Elements**: Workflow definitions in visual designer
- **HTML Dialogs**: XHTML-based UI components

### Portal Architecture Patterns
- **Component-based**: Reusable components in `portal-components`
- **Modular**: Clear separation between modules
- **Extensible**: Public APIs for customization
- **Showcase-driven**: Examples demonstrate capabilities

---

*This instruction file should be updated as coding standards evolve and new patterns emerge in the portal codebase.*
