import fs from "fs";
import path from "path";
import puppeteer from "puppeteer";
import lighthouse, { startFlow, desktopConfig } from "lighthouse";
import { fileURLToPath } from "url";
import { dirname } from "path";

const __filename = fileURLToPath(import.meta.url);
const __dirname = dirname(__filename);

const PORTAL_URL = "http://localhost:8080/demo-portal";
const LOGIN_URL = `${PORTAL_URL}/login`;
const DASHBOARD_URL = `${PORTAL_URL}/pro/portal/1549F58C18A6C562/DashboardPage.ivp?dashboardId=1`;
const CREATE_TASK_URL = `${PORTAL_URL}/pro/portal-developer-examples/162511D2577DBA88/CategoriedLeaveRequest.ivp?embedInFrame=`;

const debugLog = (msg) => console.log(`[Debug] ${msg}`);

(async () => {
  let browser;
  try {
    // Check server availability first
    debugLog("Checking server status...");
    const serverCheck = await fetch(PORTAL_URL);
    if (!serverCheck.ok) {
      throw new Error(`Server not available: ${serverCheck.status}`);
    }
    debugLog("Server is healthy");

    // Launch browser
    debugLog("Launching browser...");
    browser = await puppeteer.launch({
      headless: "new",
      args: [
        "--no-sandbox",
        "--disable-setuid-sandbox",
        "--disable-gpu",
        "--window-size=1920,1080",
      ],
      defaultViewport: null,
    });

    const page = await browser.newPage();
    await page.setViewport({ width: 1920, height: 1080 });

    // Set User Agent to a desktop one
    await page.setUserAgent(
      "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/94.0.4606.81 Safari/537.36"
    );

    debugLog("Browser launched");

    // Navigate to login page
    debugLog(`Navigating to ${LOGIN_URL}`);
    const loginResponse = await page.goto(LOGIN_URL, {
      waitUntil: "networkidle0",
      timeout: 30000,
    });

    if (!loginResponse.ok()) {
      throw new Error(`Login page failed to load: ${loginResponse.status()}`);
    }
    debugLog(`Login page loaded: ${loginResponse.status()}`);

    // Handle login form
    debugLog("Attempting login...");
    await page.waitForSelector("#login\\:login-form\\:username", {
      visible: true,
    });
    await page.type("#login\\:login-form\\:username", "demo");
    await page.type("#login\\:login-form\\:password", "demo");

    await Promise.all([
      page.click("#login\\:login-form\\:login-command"),
      page.waitForNavigation({ waitUntil: "networkidle0" }),
    ]);
    debugLog("Login successful");

    // Creating task
    const createTaskResponse = await page.goto(CREATE_TASK_URL, {
      waitUntil: "networkidle0",
      timeout: 30000,
    });
    debugLog(`Creating task by navigating to ${CREATE_TASK_URL}`);
    if (!createTaskResponse.ok()) {
      throw new Error(
        `Create task page failed to load: ${createTaskResponse.status()}`
      );
    }
    debugLog(`Create task page: ${loginResponse.status()}`);

    // Redirected to dashboard
    await page.goto(DASHBOARD_URL, {
      waitUntil: "networkidle0",
      timeout: 30000,
    });
    debugLog("Redirected to dashboard");

    debugLog("Verifying dashboard loaded...");
    await page.waitForSelector(".ui-g.js-dashboard__wrapper.js-view-mode", {
      visible: true,
      timeout: 30000,
    });
    debugLog("Dashboard loaded successfully");

    // Run Lighthouse user flow audit (desktop)
    // Using desktopConfig and disabling Lighthouse viewport emulation to inherit Puppeteer's settings.
    const flow = await startFlow(page, {
      config: desktopConfig,
      flags: { screenEmulation: { disabled: true } },
    });
    // Use navigate() to capture a navigation step (here we reuse current URL)
    await flow.navigate(page.url());
    const flowReportHtml = await flow.generateReport();

    debugLog("Puppeteer viewport:");
    debugLog(await page.viewport());

    debugLog("User Flow Report generated");

    // Ensure reports directory exists
    const reportsDir = "lighthouse-reports";
    if (!fs.existsSync(reportsDir)) {
      fs.mkdirSync(reportsDir, { recursive: true });
    }

    // Save the flow report
    fs.writeFileSync("lighthouse-flow-report.html", flowReportHtml);
    debugLog("Flow HTML report saved successfully");
  } catch (error) {
    debugLog(`Error: ${error.message}`);
    console.error(error);
    process.exit(1);
  } finally {
    if (browser) {
      await browser.close();
    }
  }
})();