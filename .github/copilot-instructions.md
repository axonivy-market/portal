---
applyTo: "**"
---
When reviewing code, focus on:

## General Standards
- New CMS entries should be capitalized and placed in `cms/` folder
- Avoid inline-style in xhtml
- Use `===` not `==` in Javascript files

### reStructuredText (.rst) - Documentation

#### Critical Rules (to avoid build warnings)
- Heading underlines must be at least as long as title text
- Use consistent hierarchy: `*` (title) → `=` (section) → `-` (subsection) → `^` (sub-subsection)
- Blank line before/after directives
- Indentation: 3 spaces for directive content
- No warning when building docs

## Product Name Usage in Documentation

**Portal** is the product name. Follow these grammar rules:
- Use "Portal" (standalone product name - no article) like "in Portal", "Portal provides..."
- Use "the Portal [feature]" when referring to specific features of the product like "the Portal dashboard", "the Portal task list"
