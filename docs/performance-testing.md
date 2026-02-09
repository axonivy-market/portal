# Performance Testing with JMeter

## Prerequisites

- JMeter 5.5

## Quick Start

### 1. Navigate to Test Directory

```bash
cd AxonIvyPortal/portal-selenium-test/jmeter
```

### 2. Configure Properties (Optional)

Edit `test.properties` to match your environment

### 3. Run Tests

> **Key Point:** Use the **`-q` flag** to load properties.

```bash
jmeter -n -t portal_walkthrough_testplan.jmx -q test.properties -l results.jtl
```

**GUI Mode:**
```bash
jmeter -q test.properties -t portal_walkthrough_testplan.jmx
```
