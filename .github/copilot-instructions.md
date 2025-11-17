# Copilot Instructions for Portal Repository

## Code Review Guidelines

### Critical Elements Requiring Extra Attention

#### Task Switch Gateway Changes
**CRITICAL**: Any changes to TaskSwitchGateway elements in process files must be flagged and carefully reviewed.

- **Location**: All `.p.json` files under `processes/**/*.p.json`
- **Identification**: Look for elements with `"type": "TaskSwitchGateway"`
- **Action Required**: 
  - ⚠️ **ALWAYS warn** if any changes are detected in or around TaskSwitchGateway elements
  - Review should cover:
    - Addition of new TaskSwitchGateway elements
    - Removal of existing TaskSwitchGateway elements
    - Modifications to TaskSwitchGateway properties (e.g., `visual`, `config`, `connect`)
    - Changes to tasks within TaskSwitchGateway configurations
    - Changes to connections (`connect`) from/to TaskSwitchGateway elements

**Warning Message Template**:
```
⚠️ WARNING: TaskSwitchGateway Change Detected

This PR contains changes to TaskSwitchGateway in process file(s).
Please ensure:
- All task definitions are correct
- Task routing logic is preserved
- No unintended task removals occurred
- Process flow integrity is maintained
```

#### Detection Pattern

When reviewing changes in `processes/**/*.p.json` files:

1. Check for any lines containing `"type": "TaskSwitchGateway"`
2. If found in the diff, examine the full context:
   - Look for added/removed task definitions within the TaskSwitchGateway
   - Check for changes to connection paths
   - Verify visual positioning changes don't break flow logic
3. Flag ALL such changes with a warning, regardless of how minor they appear

**Example to Flag**:
```json
{
  "id": "S10-g1",
  "type": "TaskSwitchGateway",
  "name": "TaskSwitch",
  "config": {
    "tasks": [
      {
        "id": "TaskA",
        "name": "Task Switch A"
      }
    ]
  }
}
```

Any modification to the above structure or its surrounding elements should trigger a review warning.

---

## Additional Review Rules
---
applyTo: "**"
---
When reviewing code, focus on:

## General Standards
- New CMS entries should be capitalized and placed in `cms/` folder
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
