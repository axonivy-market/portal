# JMeter Java DSL Performance Test Documentation

## Introduction

This project implements performance testing for a Portal application using **JMeter Java DSL**, a modern Java-based approach to performance testing that provides a fluent API for creating JMeter test plans programmatically. The JMeter Java DSL eliminates the need for XML-based test plans by allowing you to write tests in pure Java code, making them more maintainable, version-controllable, and easier to integrate into CI/CD pipelines.

### Key Benefits of JMeter Java DSL:
- **Type Safety**: Compile-time validation of test plans
- **IDE Support**: Debugging capabilities
- **Maintainability**: Easy refactoring and code reuse
- **Version Control Friendly**: Pure Java code instead of XML files
- **Integration**: Seamless integration with JUnit and testing frameworks
## Project Structure

```
portal-performance-test/
├── src_test/com/axonivy/portal/
│   └── PerformancePortalTest.java    # Main test class
├── jmeter/
│   ├── test.properties               # Configuration properties
│   └── <credentials>.csv             # User credentials for server testing
│                                     # In Portal, we use it with Jenkins credentials
└── target/
    └── jtls/                         # JTL result files
```

## Properties Configuration

The test configuration is managed through `test.properties` file. Here are the key properties you need to configure:

> **📝 Note:** The properties in Number are just for reference, currently you have to put the actual values in your test (Check the PerformancePortalTest).

### Server Properties
```properties
# Security System name (empty for local, "Portal" for server)
security.system.name=Portal

# Application and portal names
portal.application.name=Portal
portal.portal=portal

# Server configuration
portal.server.host=portal01.server.ivy-cloud.com
portal.server.port=9000
```

### Performance Thresholds
```properties
# Response time expectations (in milliseconds)
portal.duration.normal=2000    # Normal page load time
portal.duration.ajax=500       # AJAX request time
```

### Thread Configuration
```properties
# Load testing parameters
portal.thread.numberOfUser=10  # Number of concurrent users
portal.thread.rampup=10        # Ramp-up time in seconds
portal.thread.loop=1           # Number of iterations per user
```

### CSV Data Files
```properties
# User credential files
<your_credentials_file>.csv=jmeter/data/<your_credentials_file>.csv
```

## CSV Data Configuration

### User Credentials Format
CSV files contain user credentials in the format: `username,password`

Example (`users_server.csv`):
```csv
username,password
username2,password2
username3,password3
```

### CSV Data Set Configuration in Code
```java
csvDataSet(csvFilePath)
  .variableNames("username,password")  // Define column names
  .delimiter(",")                      // CSV delimiter
  .ignoreFirstLine(false)             // Set to true if CSV has headers
```

## Credential Security Management

### 🔒 Keeping Credentials Secure

Your credential files contain sensitive information and should **never** be committed to version control. Here are several approaches to manage them securely:

> **📝 Note:** This is just the example how files are currently being used in Portal projects. There're multiple ways to handle Credentials, other solutions are possible.

**Jenkins Secret Files**
1. Go to Jenkins → Manage Jenkins → Manage Credentials
2. Add credentials of type "Secret file" for each CSV file
3. In your Jenkinsfile:
```groovy
pipeline {
  agent any
  stages {
    stage('Setup Credentials') {
      steps {
        script {
          withCredentials([
            // Jenkins > Manage Jenkins > Manage Credentials
            file(credentialsId: 'your_credentials', variable: 'YOUR_CREDENTIALS_CSV'),
          ]) {
            // Copy credential files to expected locations
            sh '''
              cp "$YOUR_CREDENTIALS_CSV" "${jmeterDslSourceDir}/jmeter/data/your_credentials.csv"
            '''
          }
        }
      }
    }
    stage('Test') {
      steps {
        bat 'mvn test'
      }
    }
  }
}
```
### Security Best Practices

1. **Never commit actual credentials** to version control
2. **Use template files** to document expected format
3. **Restrict access** to credential files on Jenkins server
4. **Audit credential access** in Jenkins
5. **Rotate credentials** regularly
6. **Use least privilege** principle for test accounts
7. **Monitor test executions** for suspicious activity

### Troubleshooting Credential Issues

**File Not Found Errors:**
```bash
# Check if files exist
ls -la src/test/resources/*.csv

# Verify file permissions
chmod 644 src/test/resources/*.csv
```

**Authentication Failures:**
- Verify credentials are correct and not expired
- Check if test accounts are locked
- Ensure proper CSV format (no extra spaces, correct delimiter)

**Jenkins Integration Issues:**
- Verify credential IDs match in Jenkinsfile
- Check file paths are correct for your OS
- Ensure Jenkins has read access to credential files

## HTTP Samplers Examples

### Basic HTTP Sampler
```java
httpSampler("PortalStart",
  "/${__P(security.system.name)}/${__P(portal.application.name)}/pro/${__P(portal.portal)}/1549F58C18A6C562/DefaultApplicationHomePage.ivp")
  .method("GET")
```

### HTTP Sampler with Parameters
```java
httpSampler("Login", "${url}")
  .method("POST")
  .param("javax.faces.partial.ajax", "true")
  .param("javax.faces.source", "login-form:login-command")
  .param("login:login-form:username", "${username}")
  .param("login:login-form:password", "${password}")
  .param("javax.faces.ViewState", "${viewState}")
```

### HTTP Sampler with GET Parameters
```java
httpSampler("UpdateSettingToAccessDetailWhenClickOnLineInTaskList",
  "/${__P(security.system.name)}/${__P(portal.application.name)}/pro/PortalKitTestHelper/17208192E0AF4185/updatePortalSetting.ivp")
  .method("GET")
  .param("settingName", "Portal.Tasks.BehaviourWhenClickingOnLineInTaskList")
  .param("settingValue", "ACCESS_TASK_DETAILS")
```

## Passing Values and Variables

### Using Properties from test.properties
Use the `${__P(property.name)}` syntax to reference properties:
```java
.host("${__P(portal.server.host)}")           // Gets portal.server.host from properties
.param("settingName", "${__P(setting.name)}") // Property in parameter value
```

### Using Variables from CSV Data
Reference CSV column names as variables:
```java
.param("login:login-form:username", "${username}")  // From csvDataSet variableNames
.param("login:login-form:password", "${password}")  // From csvDataSet variableNames
```

### Using Extracted Variables
Variables extracted by regex extractors can be used in subsequent requests:
```java
httpSampler("Login", "${url}")                    // Uses extracted 'url' variable
.param("javax.faces.ViewState", "${viewState}")   // Uses extracted 'viewState' variable
```

## Regular Expression Extractors

Extractors capture values from HTTP responses for use in subsequent requests:

### Basic Regex Extractor
```java
.children(
  regexExtractor("url", "action=\"([^\"]+)\""),  // Extract form action URL
  regexExtractor("viewState", "id=\"j_id__v_0:javax.faces.ViewState:1\" value=(\"[\\S]+\") ")  // Extract ViewState
)
```

### Redirect URL Extractor
```java
.children(
  regexExtractor("redirectURL", "<redirect url=\"([^\"]+)\">")     // Extract redirect URL from response
)
```

### Extractor Configuration Parameters:
- **First Parameter**: Variable name to store the extracted value
- **Second Parameter**: Regular expression pattern
- **Capture Groups**: Use parentheses `()` to define what to capture
- **Usage**: Access extracted values using `${variableName}`

### Common Regex Patterns:
```java
// Extract URLs from action attributes
regexExtractor("url", "action=\"([^\"]+)\"")

// Extract ViewState from JSF pages
regexExtractor("viewState", "id=\"j_id__v_0:javax.faces.ViewState:1\" value=(\"[\\S]+\") ")

// Extract redirect URLs from XML responses
regexExtractor("redirectURL", "<redirect url=\"([^\"]+)\">")
```

## Response Assertions

Response assertions validate that HTTP requests return expected results:

### Response Code Assertion
```java
.children(
  responseAssertion().fieldToTest(TargetField.RESPONSE_CODE).equalsToStrings("200")
)
```

### Response Body Assertion
```java
.children(
  responseAssertion().fieldToTest(TargetField.RESPONSE_DATA).containsSubstrings("Success")
)
```

### Available Target Fields:
- `TargetField.RESPONSE_CODE`: HTTP status code (200, 404, 500, etc.)
- `TargetField.RESPONSE_DATA`: Response body content
- `TargetField.RESPONSE_HEADERS`: HTTP response headers
- `TargetField.RESPONSE_MESSAGE`: HTTP response message

### Assertion Methods:
- `.equalsToStrings(value)`: Exact match
- `.containsSubstrings(value)`: Contains substring
- `.matchesRegex(pattern)`: Matches regular expression
- `.notContainsSubstrings(value)`: Does not contain substring

## Test Execution Configuration

### Thread Group Configuration
```java
threadGroup("test_name")
  .rampTo(numberOfUsers, Duration.ofSeconds(numberOfUsers))  // Ramp up users
  .holdIterating(1)                                          // Number of iterations
```

### HTTP Defaults
```java
httpDefaults()
  .host("${__P(portal.server.host)}")  // Default host for all requests
  .port(9000)                          // Default port for all requests
```

### HTTP Headers
```java
httpHeaders()
  .header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9")
  .header("Accept-Encoding", "gzip, deflate, br")
  .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36")
```

### Cookies Management
```java
httpCookies()  // Enables automatic cookie handling
```

## Reporting and Results

### JTL Writer (Raw Results)
```java
jtlWriter(jtlDirName, testName + ".jtl")  // Saves raw test results
```

### Results Tree Visualizer (for debugging)
```java
resultsTreeVisualizer()  // Uncomment for local debugging only
```

## Running the Tests

### Prerequisites
1. Java 11 or higher
2. Maven
3. Access to the target Portal server

### Execution Commands
```bash
# Run all tests
mvn test
```

### Test Validation
The test automatically validates results and fails if:
- Error count > 0
- Response codes are not as expected (non-200)
- Assertions fail

### Example Test Output
```
=== Running test with 1 admin user ===
1 admin user test completed successfully: 45 samples, 0 errors
=== Running test with 1 normal user ===
1 normal user test completed successfully: 45 samples, 0 errors
=== Running test with 10 users ===
10 users test completed successfully: 450 samples, 0 errors
```

## Best Practices

1. **Property Externalization**: Use properties files for environment-specific values
2. **Data-Driven Testing**: Use CSV files for user credentials and test data
3. **Response Validation**: Always include response assertions
4. **Variable Extraction**: Extract dynamic values like ViewState and form URLs
5. **Correlation**: Use extracted variables to handle dynamic content
6. **Cookie Management**: Enable automatic cookie handling for session management
7. **Reporting**: Generate JTL files and HTML reports for analysis (currently we don't using HTML reports)
8. **Error Handling**: Implement proper test validation and failure handling

## Quick Setup Guide

### For Local Development

1. **Clone the repository**
2. **Set up credentials** (choose one):
3. **Configure test properties** (if needed)
   ```bash
   # Edit server settings
   notepad src/test/resources/test.properties
   ```

4. **Run tests**
   ```bash
   mvn test
   ```

### For Jenkins CI/CD

1. **Set up credential files in Jenkins** (see detailed instructions above)
2. **Use the provided Jenkinsfile** for pipeline configuration
3. **Configure build parameters** as needed
4. **Run the pipeline**

## Troubleshooting

### Common Issues:
1. **401/403 Errors**: Check user credentials in CSV files
2. **ViewState Errors**: Ensure ViewState extraction regex is correct
3. **Timeout Issues**: Adjust response time expectations in properties
4. **Connection Errors**: Verify server host and port configuration

### Debug Mode:
Uncomment `resultsTreeVisualizer()` for detailed request/response inspection during local development.