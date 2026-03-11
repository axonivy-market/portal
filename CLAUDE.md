# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

**Axon Ivy Portal** is the main end-user portal for the Axon Ivy Platform. It is a Maven multi-module project using Ivy's proprietary `.iar` (Ivy Application aRchive) packaging format.

Key modules under `AxonIvyPortal/`:
- `portal/` — Core portal application
- `portal-components/` — Reusable UI components
- `portal-selenium-test/` — Selenide-based integration tests
- `PortalKitTestHelper/` — Test helper utilities

Showcase and demo apps live under `Showcase/`. Documentation (reStructuredText/Sphinx) lives under `Documentation/portal-guide/`.

## Build

```bash
# Full build from root
mvn clean install

# Build a specific module
mvn clean install -f AxonIvyPortal/portal/pom.xml
```

The project targets the Axon Ivy Platform (currently `14.0.0-SNAPSHOT`). Build requires JDK 25 and Maven 3.9.8+.

## Testing

Integration tests use Selenide and require an Axon Ivy Engine instance and a Selenium remote WebDriver (Firefox). They are typically run via CI (Jenkins/GitHub Actions).

```bash
# Run integration tests (requires engine and Selenium hub)
mvn clean test -f AxonIvyPortal/portal-selenium-test/customized_pom.xml \
  -Dtest=<test-pattern> \
  -DbrowserType=FIREFOX \
  -Divy.engine.directory=<engine-dir> \
  -Dselenide.remote=http://<selenium-hub>:4444/wd/hub
```

Note: Use `customized_pom.xml` (not `pom.xml`) for the selenium test module — it uses JAR packaging required for running tests.

## Branch & PR Conventions

- Target PRs against `master`
- Branch names follow the pattern: `IVYPORTAL-<ticket>-<short-description>`

## Coding Standards

- Use `===` not `==` in JavaScript files
- Avoid inline styles in XHTML files
- New CMS entries must be capitalized and placed in the `cms/` folder

## Documentation (.rst files)

- Heading underlines must be at least as long as the title text
- Use consistent heading hierarchy: `*` (title) → `=` (section) → `-` (subsection) → `^` (sub-subsection)
- Always add a blank line before and after directives
- Use 3-space indentation for directive content
- Docs must build without warnings

## Product Name Usage in Documentation

- Use "Portal" standalone (no article): "in Portal", "Portal provides..."
- Use "the Portal [feature]" when referring to a specific feature: "the Portal dashboard", "the Portal task list"
