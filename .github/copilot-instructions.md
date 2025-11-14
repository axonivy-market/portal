# GitHub Copilot PR Review Instructions for Axon Ivy Portal

## Project Context
**Axon Ivy Portal** - Enterprise BPM portal built on Axon Ivy Platform

### Key Info
- **Version**: 13.2.0-SNAPSHOT
- **Tech Stack**: Java, Maven, PrimeFaces (XHTML/JSF), PrimeFlex, Selenium
- **Main Modules**: portal, portal-components, portal-selenium-test, Showcase apps, Documentation
- **Source Dirs**: `src/` (Java), `src_hd/` (XHTML), `webContent/` (CSS/JS), `cms/` (CMS)

---

## Code Review Guidelines

### General Standards
- **Formatting**: 2 spaces indentation (no tabs), 120 char line length, braces at end of line

### Java - Portal-Specific Patterns

#### 1. Security Context (CRITICAL)
Always use `Sudo.get()` for security operations:
```java
public static List<IRole> getAllRoles() {
  return Sudo.get(() -> {
    return ISecurityContext.current().roles().all();
  });
}
```

#### 2. Null Safety (CRITICAL)
- Use `Objects.nonNull()` / `Objects.isNull()` 
- Use `CollectionUtils.emptyIfNull()` for collections
- Use `StringUtils.isEmpty()`, `StringUtils.EMPTY`, `StringUtils.SPACE`

#### 3. Stream API Pattern
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

**Classes**
- **DTOs**: Suffix with `Dto` (e.g., `RoleDto`, `BusinessDetailsDto`)

**Variables**
- Avoid one-character names except for temporary variables

#### PrimeFlex First (CRITICAL)
- **Prefer PrimeFlex utilities** over custom CSS: `w-full`, `flex`, `justify-content-between`, `align-items-center`, `mb-4`, `p-3`, `gap-2`
- **NO inline styles** - use CSS classes

#### Accessibility (CRITICAL - WCAG 2.2)
- All `<img>` need `alt` attributes
- Icon-only buttons need `ariaLabel`
- All inputs need `<label for="">` 
- Modal dialogs: `focus="@(.ui-fileupload-choose)"`
- Menus: `role="menu"`, `role="menuitem"`
- Every page needs proper `<title>` and heading structure

#### Magic Numbers (CRITICAL)
- **Never use magic numbers** - define constants with descriptive names or add comments
```javascript
// Good
const DEFAULT_TIMEOUT_MS = 3000;
setTimeout(taskFunction, DEFAULT_TIMEOUT_MS);

// Bad
setTimeout(taskFunction, 3000); // What is 3000? Why?
```

#### Key Standards
- Use `===` not `==`
- Avoid global variables

### reStructuredText (.rst) - Documentation

#### Critical Rules (to avoid build warnings)
- Heading underlines must be at least as long as title text
- Use consistent hierarchy: `*` (title) → `=` (section) → `-` (subsection) → `^` (sub-subsection)
- Blank line before/after directives
- Indentation: 3 spaces for directive content

#### Common Directives
```rst
.. code-block:: java
   
   // Code here

.. image:: path/to/image.png
   :alt: Alt text
   :width: 600px

`Link text <URL>`_
:ref:`label-name`
```

---

## Security & Portal Specifics

### Localization
- All user-facing strings externalized (CMS/resource bundles)
