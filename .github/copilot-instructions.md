# GitHub Copilot Pull Request Review Instructions

## Repository Context
This is the **Axon Ivy Portal** repository - an enterprise web application portal for the Axon Ivy Platform. The codebase consists of multiple Maven modules with IAR (Ivy Archive) packaging, JSF/PrimeFaces frontend, and comprehensive Selenium test suites.

## Architecture Overview
- **Technology Stack**: Java 8+, JSF, PrimeFaces, Maven, Selenium WebDriver
- **Main Modules**:
  - `AxonIvyPortal/portal` - Core portal functionality
  - `AxonIvyPortal/portal-components` - Reusable UI components
  - `AxonIvyPortal/portal-migration` - Version migration logic
  - `AxonIvyPortal/portal-selenium-test` - UI automation tests
  - `Showcase/` - Example implementations

## Pull Request Review Focus Areas

### 🔒 **Security Reviews**
**CRITICAL - Always check for:**
- Dependency vulnerabilities (especially iTextPDF, jsoup versions)
- Proper input sanitization in REST endpoints
- Encryption/decryption implementations (check `SecureMessage.java` patterns)
- SQL injection risks in query builders
- XSS prevention in JSF components
- Authentication/authorization bypasses
- Sensitive data exposure in logs

**Review Pattern:**
```java
// ❌ Avoid - Direct user input without sanitization
String userInput = request.getParameter("data");
query.append(userInput);

// ✅ Prefer - Parameterized queries and sanitization
String sanitizedInput = SanitizeAPI.sanitizeHtml(request.getParameter("data"));
query.setParameter("data", sanitizedInput);
```

### 🏗️ **Code Quality & Architecture**

#### JSF Bean Practices
**Look for:**
- Legacy `@ManagedBean` usage (should migrate to CDI `@Named`)
- Proper scoping (`@ViewScoped`, `@SessionScoped`, `@RequestScoped`)
- Missing `serialVersionUID` in `Serializable` classes
- Large bean classes (>500 lines suggest refactoring needed)

```java
// ❌ Legacy approach
@ManagedBean
@ViewScoped
public class MyBean implements Serializable {
    // Missing serialVersionUID
}

// ✅ Modern approach
@Named
@ViewScoped
public class MyBean implements Serializable {
    private static final long serialVersionUID = 1L;
}
```

#### Service Layer Patterns
**Check for:**
- Proper use of `Sudo.get()` for security elevation
- Transaction boundaries in service methods
- Null safety and defensive programming
- Proper exception handling with custom portal exceptions

#### Configuration Management
**Review:**
- Changes to `AbstractConfiguration` hierarchy
- JSON serialization annotations consistency
- Migration scripts for configuration updates
- Portal permission and security member handling

### 🧪 **Testing Requirements**

#### Selenium Test Reviews
**For changes in `portal-selenium-test/`:**
- Page Object Model compliance
- Proper use of `@IvyWebTest` annotation
- Selenide conditions and assertions
- Test data cleanup and isolation
- Screenshot test coverage for UI changes

```java
// ✅ Good practice
@Test
public void testTaskListDisplay() {
    TaskWidgetPage taskWidget = new TaskWidgetPage();
    taskWidget.waitForTaskListToLoad();
    
    taskWidget.getTaskCount().shouldBe(Condition.text("5 tasks"));
    taskWidget.getFirstTaskName().shouldHave(Condition.text("Sample Task"));
}
```

#### Unit Test Coverage
**For service/utility changes:**
- Ensure new public methods have corresponding tests
- Mock external dependencies appropriately
- Test edge cases and error scenarios
- Verify security permission checks

### 📦 **Dependency Management**

#### Maven POM Reviews
**Check for:**
- Version consistency across modules
- Known vulnerable dependencies
- Proper exclusions for transitive dependencies
- Ivy engine version compatibility

**Key Dependencies to Monitor:**
```xml
<!-- Always verify these versions for security -->
<dependency>
    <groupId>org.jsoup</groupId>
    <artifactId>jsoup</artifactId>
    <version><!-- Should be latest stable --></version>
</dependency>
<dependency>
    <groupId>com.itextpdf</groupId>
    <artifactId>itextpdf</artifactId>
    <version><!-- Should be 7.x+ for security --></version>
</dependency>
```

### 🔄 **Migration & Backward Compatibility**

#### Portal Migration Scripts
**For changes in `portal-migration/`:**
- Version-specific migration logic correctness
- Data transformation safety
- Rollback procedures
- Configuration schema changes

#### Deprecated Code Management
**Review:**
- Proper `@Deprecated` annotations with `since` and `forRemoval`
- Migration paths for deprecated APIs
- Timeline for removal (align with portal versioning)

```java
// ✅ Proper deprecation
@Deprecated(since = "12.0", forRemoval = true)
public void oldMethod() {
    // Implementation with clear migration path
}
```

### 🎨 **UI/UX Changes**

#### JSF/XHTML Reviews
**Check for:**
- Accessibility compliance (ARIA labels, semantic HTML)
- Responsive design considerations
- Proper i18n message keys
- PrimeFaces component usage patterns
- Theme consistency

#### CSS/JavaScript Changes
**Review:**
- Browser compatibility
- Performance impact
- Portal theme consistency
- Mobile responsiveness

### 📊 **Performance Considerations**

**Database Operations:**
- Query efficiency and indexing
- Lazy loading patterns
- Pagination implementation
- Connection pooling usage

**Frontend Performance:**
- Component rendering optimization
- AJAX request patterns
- Resource loading strategies
- Cache utilization

### 🚀 **CI/CD & Build Changes**

#### Jenkins Pipeline Reviews
**For `build/` directory changes:**
- Docker container security
- Build artifact integrity
- Test execution coverage
- Deployment safety

#### Documentation Updates
**For `Documentation/` changes:**
- Technical accuracy
- Version-specific content
- API documentation completeness
- User guide clarity

## Common Anti-Patterns to Flag

### ❌ **Avoid These Patterns:**
```java
// Direct database access without proper abstraction
ITask task = Ivy.wf().getTaskQuery().where()...

// Hardcoded strings instead of CMS
button.setText("Submit");

// Missing null checks
user.getName().toLowerCase();

// Exposing internal IDs in URLs
/portal/task?id=123

// Catch-all exception handling
try { ... } catch (Exception e) { /* ignore */ }
```

### ✅ **Encourage These Patterns:**
```java
// Service layer abstraction
TaskService.getInstance().findTaskById(taskId);

// Internationalization
button.setText(Ivy.cms().co("/path/to/submit"));

// Null safety
Optional.ofNullable(user).map(User::getName).orElse("");

// UUID-based public identifiers
/portal/task?uuid=550e8400-e29b-41d4-a716-446655440000

// Specific exception handling
try { ... } catch (PortalException e) { handlePortalError(e); }
```

## Review Checklist Template

When reviewing PRs, consider this checklist:

- [ ] **Security**: No new vulnerabilities introduced
- [ ] **Testing**: Adequate test coverage for changes
- [ ] **Documentation**: Updated for API/behavior changes
- [ ] **Performance**: No significant performance degradation
- [ ] **Backward Compatibility**: Migration path for breaking changes
- [ ] **Code Style**: Follows portal conventions
- [ ] **Dependencies**: No vulnerable or unnecessary additions
- [ ] **Configuration**: Proper handling of portal settings
- [ ] **Accessibility**: UI changes maintain accessibility standards
- [ ] **Browser Support**: Cross-browser compatibility verified

## Additional Context

### Portal-Specific Terminology
- **IAR**: Ivy Archive - deployment unit for Ivy applications
- **CMS**: Content Management System for i18n
- **PMV**: Process Model Version
- **Portal Kit**: Core portal functionality framework
- **Widget**: Configurable UI components in the portal

### Version Support Matrix
- Portal 12.0.x: Current development version
- Portal 11.x.x: Maintenance support
- Portal 10.0.x: Limited support
- Portal 8.0.x: Legacy support

Use this context to ensure changes align with the supported version strategy and don't break compatibility unnecessarily.