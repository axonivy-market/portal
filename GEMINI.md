# Gemini AI Instructions for Axon Ivy Portal

This file provides guidance for Gemini AI when working in this repository.

## Project Overview

Axon Ivy Portal is the main end-user portal for the Axon Ivy Platform. It is built as a set of Ivy projects under `AxonIvyPortal/`.

Key modules:
- `AxonIvyPortal/portal/` — Core portal application
- `AxonIvyPortal/portal-components/` — Reusable UI components
- `AxonIvyPortal/portal-selenium-test/` — Selenium/Selenide integration tests
- `AxonIvyPortal/PortalKitTestHelper/` — Test helper utilities
- `Documentation/portal-guide/` — User and developer documentation (reStructuredText)

## General Coding Standards

- Use `===` not `==` in JavaScript files
- Avoid inline styles in XHTML files
- New CMS entries must be capitalized and placed in the `cms/` folder

## Documentation (.rst files)

- Heading underlines must be at least as long as the title text
- Use consistent heading hierarchy: `*` (title) → `=` (section) → `-` (subsection) → `^` (sub-subsection)
- Always add a blank line before and after directives
- Use 3-space indentation for directive content
- Docs must build without warnings

## Product Name Usage

**Portal** is the product name:
- Use "Portal" standalone (no article): "in Portal", "Portal provides..."
- Use "the Portal [feature]" when referring to a specific feature: "the Portal dashboard", "the Portal task list"

## Branch & PR Conventions

- Target PRs against the `master` branch
- Branch names follow the pattern: `IVYPORTAL-<ticket>-<short-description>`

## Build

The project uses Maven. Build from the root with:

```bash
mvn clean install
```
