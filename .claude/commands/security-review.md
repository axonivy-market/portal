You are a security-focused code reviewer. Perform a comprehensive security audit of all pending changes in this repository.

## Phase 1: Repository Context Research

Before analyzing changes, establish the security context:

1. Read the project's security-related configuration files (pom.xml dependencies, web.xml, ivy configuration)
2. Identify existing security patterns, frameworks, and sanitization approaches used in the codebase
3. Note any security middleware, authentication/authorization patterns, and input validation frameworks

## Phase 2: Analyze Changes

Run `git diff HEAD` to see all pending changes. For each changed file:

1. Understand the purpose of the change
2. Trace data flows from user input to sensitive operations
3. Compare new code against established security patterns in the codebase
4. Check for deviations from secure coding practices

## Phase 3: Vulnerability Assessment

Focus on these vulnerability categories relevant to this Java/Axon Ivy Portal project:

### High Priority
- **Injection Attacks**: SQL injection, LDAP injection, XPath injection, command injection, EL injection in XHTML templates
- **Authentication & Authorization Bypass**: Broken access control in Ivy process flows, privilege escalation through task permissions
- **Cross-Site Scripting (XSS)**: Reflected/stored XSS in XHTML Facelets templates, DOM-based XSS in JavaScript
- **Insecure Deserialization**: Unsafe Java object deserialization
- **XML External Entity (XXE)**: Unsafe XML parsing in process definitions or configuration
- **Server-Side Request Forgery (SSRF)**: Unvalidated URLs in REST client calls

### Medium Priority
- **Sensitive Data Exposure**: Hardcoded credentials, API keys, or secrets in configuration files
- **Cryptographic Issues**: Weak algorithms, insecure random number generation
- **Path Traversal**: File access without proper path sanitization
- **Business Logic Flaws**: Race conditions in workflow processes, improper state management
- **Insecure Direct Object References**: Unvalidated task/case IDs in portal operations

### Axon Ivy Specific
- **Process Permission Bypass**: Incorrect role assignments in process definitions
- **CMS Injection**: Unsanitized content in CMS entries rendered in templates
- **Ivy Script Injection**: Unsafe expressions in process element scripts
- **REST API Security**: Missing authentication on custom REST endpoints
- **SubProcess Security**: Unvalidated parameters passed between processes

## False Positive Filtering Rules

EXCLUDE the following from findings:
- Denial of Service vulnerabilities
- Rate limiting concerns
- Memory/CPU exhaustion in safe languages
- Disk-stored credentials in test/example files
- Environment variables (these are trusted values in this context)
- Client-side-only permission checks that are backed by server-side enforcement
- Test code security issues (files under `*Test.java` or `*-test/`)
- Generic findings with confidence below 80%

## Output Format

For each finding, report:

### [SEVERITY: HIGH/MEDIUM] - Brief Title

- **File**: `path/to/file.java:line_number`
- **Category**: e.g., SQL Injection, XSS, etc.
- **Confidence**: X/10
- **Description**: Clear explanation of the vulnerability
- **Exploitation Scenario**: How an attacker could exploit this
- **Remediation**: Specific fix with code example

---

Only report findings with confidence >= 8/10 and severity HIGH or MEDIUM. Provide actionable, specific remediation guidance for each finding.
