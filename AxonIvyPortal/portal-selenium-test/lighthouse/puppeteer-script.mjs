import fs from "fs";
import path from "path";
import puppeteer from "puppeteer";
import lighthouse from "lighthouse";
import { fileURLToPath } from "url";
import { dirname } from "path";

const __filename = fileURLToPath(import.meta.url);
const __dirname = dirname(__filename);

const PORTAL_URL = "http://localhost:8080";
const LOGIN_URL = `${PORTAL_URL}/demo-portal/login`;
const DASHBOARD_URL = `${PORTAL_URL}/demo-portal/pro/portal/1549F58C18A6C562/DashboardPage.ivp?dashboardId=1`;

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
    });

    const page = await browser.newPage();
    await page.setViewport({ width: 1920, height: 1080 });
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

    // Verify dashboard loaded
    await page.goto(DASHBOARD_URL, {
      waitUntil: "networkidle0",
      timeout: 30000,
    });

    debugLog("Verifying dashboard loaded...");
    await page.waitForSelector(".ui-g.js-dashboard__wrapper.js-view-mode", {
      visible: true,
      timeout: 30000,
    });
    debugLog("Dashboard loaded successfully");

    // Run Lighthouse audit
    debugLog("Starting Lighthouse audit...");
    const runnerResult = await lighthouse(page.url(), {
      port: new URL(browser.wsEndpoint()).port,
      output: ["html", "json"],
      onlyCategories: ["performance", "accessibility", "best-practices", "seo"],
    });

    // Ensure reports directory exists
    const reportsDir = "lighthouse-reports";
    if (!fs.existsSync(reportsDir)) {
      fs.mkdirSync(reportsDir, { recursive: true });
    }

    // Save reports
    debugLog("Saving reports...");
    try {
      if (Array.isArray(runnerResult.report)) {
        // Handle array of reports (HTML is typically the first element)
        fs.writeFileSync("lighthouse-report.html", runnerResult.report[0]);
        debugLog("HTML report saved from array");
      } else if (typeof runnerResult.report === "string") {
        // Handle string report
        fs.writeFileSync("lighthouse-report.html", runnerResult.report);
        debugLog("HTML report saved from string");
      } else {
        debugLog(`Unexpected report format: ${typeof runnerResult.report}`);
        debugLog(`Report content: ${JSON.stringify(runnerResult.report)}`);
      }

      if (runnerResult.lhr) {
        fs.writeFileSync(
          path.join(reportsDir, "report.json"),
          JSON.stringify(runnerResult.lhr, null, 2)
        );
        debugLog("JSON report saved");
      }

      if (
        !fs.existsSync("lighthouse-report.html") &&
        !fs.existsSync(path.join(reportsDir, "report.json"))
      ) {
        throw new Error("No reports were generated");
      }
    } catch (error) {
      debugLog(`Error saving reports: ${error.message}`);
      throw error;
    }

    debugLog("Reports saved successfully");
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
