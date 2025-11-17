---
applyTo: "**"
---
When reviewing code, focus on:

## General Standards
- **Formatting**: 2 spaces indentation (no tabs), 120 char line length, braces at end of line
- New CMS entries should be capitalized and placed in `cms/` folder
- Warning when modifying Task Switch elements (may affect workflow behavior)
- When create new **DTOs**: Suffix with `Dto` (e.g., `RoleDto`, `BusinessDetailsDto`)
- Avoid one-character variable name
- Avoid inline-style in xhtml
- Define constants with descriptive names or add comments
```javascript
// Good
const DEFAULT_TIMEOUT_MS = 3000;
setTimeout(taskFunction, DEFAULT_TIMEOUT_MS);

// Bad
setTimeout(taskFunction, 3000); // What is 3000? Why?
```
- Use `===` not `==` in javascript files

### Java - Portal-Specific Patterns

#### 1. Null Safety (CRITICAL)
- Use `Objects.nonNull()` / `Objects.isNull()` 
- Use `CollectionUtils.emptyIfNull()` for collections
- Use `StringUtils.isEmpty()`, `StringUtils.EMPTY`, `StringUtils.SPACE`

### reStructuredText (.rst) - Documentation

#### Critical Rules (to avoid build warnings)
- Heading underlines must be at least as long as title text
- Use consistent hierarchy: `*` (title) → `=` (section) → `-` (subsection) → `^` (sub-subsection)
- Blank line before/after directives
- Indentation: 3 spaces for directive content