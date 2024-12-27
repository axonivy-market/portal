import fs from "fs";
import path from "path";
import puppeteer from "puppeteer";
import lighthouse from "lighthouse";
import { fileURLToPath } from "url";
import { dirname } from "path";

const __filename = fileURLToPath(import.meta.url);
const __dirname = dirname(__filename);

const debugLog = (msg) => console.log(`[Debug] ${msg}`);

(async () => {
  let browser;
  try {
    // Health check
    debugLog("Checking server status...");
    const healthCheck = await fetch("http://localhost:8080/portal/health");
    if (!healthCheck.ok) {
      throw new Error(`Server health check failed: ${healthCheck.status}`);
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

    // Test portal access
    const portalUrl = "http://localhost:8080/portal/faces/login.xhtml";
    debugLog(`Navigating to ${portalUrl}`);

    const response = await page.goto(portalUrl, {
      waitUntil: ["networkidle0", "domcontentloaded"],
      timeout: 60000,
    });

    if (!response.ok()) {
      throw new Error(`Page load failed: ${response.status()}`);
    }
    debugLog(`Portal loaded: ${response.status()}`);

    // Login
    debugLog("Attempting login...");
    await page.waitForSelector("#username", { visible: true, timeout: 30000 });
    await page.type("#username", "demo");
    await page.type("#password", "demo");

    await Promise.all([
      page.click('button[type="submit"]'),
      page.waitForNavigation({
        waitUntil: "networkidle0",
        timeout: 30000,
      }),
    ]);
    debugLog("Login successful");

    // Run Lighthouse
    debugLog("Starting Lighthouse audit...");
    const { lhr } = await lighthouse(page.url(), {
      port: new URL(browser.wsEndpoint()).port,
      output: ["html", "json"],
      logLevel: "info",
    });

    // Save reports
    fs.writeFileSync("lighthouse-report.html", lhr.report[0]);
    fs.writeFileSync(
      "lighthouse-reports/report.json",
      JSON.stringify(lhr, null, 2)
    );
    debugLog("Reports saved");
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
