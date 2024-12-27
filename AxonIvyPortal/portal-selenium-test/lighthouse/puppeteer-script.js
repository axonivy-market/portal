import fs from "fs";
import path from "path";
import puppeteer from "puppeteer";
import { fileURLToPath } from "url";
import { dirname } from "path";

const __filename = fileURLToPath(import.meta.url);
const __dirname = dirname(__filename);

// Add type: module to package.json
const packageJson = {
  type: "module",
};

(async () => {
  try {
    // Dynamically import lighthouse
    const lighthouse = (await import("lighthouse")).default;

    // Read user credentials
    const csvFilePath = path.join(__dirname, "../jmeter/data/users_local.csv");
    const users = fs
      .readFileSync(csvFilePath, "utf-8")
      .split("\n")
      .filter(Boolean)
      .map((line) => {
        const [username, password] = line.trim().split(",");
        return { username, password };
      });

    // Launch browser
    const browser = await puppeteer.launch({
      headless: "new",
      args: [
        "--no-sandbox",
        "--disable-setuid-sandbox",
        "--disable-gpu",
        "--disable-dev-shm-usage",
      ],
    });

    const page = await browser.newPage();
    await page.setViewport({ width: 1920, height: 1080 });

    // Login process
    const user = users[0];
    await page.goto("http://localhost:8080/Portal", {
      waitUntil: "networkidle0",
    });
    await page.waitForSelector("#username");
    await page.type("#username", user.username);
    await page.type("#password", user.password);
    await page.click('button[type="submit"]');
    await page.waitForNavigation({ waitUntil: "networkidle0" });

    // Run Lighthouse
    const { lhr } = await lighthouse(page.url(), {
      port: new URL(browser.wsEndpoint()).port,
      output: ["json", "html"],
      logLevel: "info",
      onlyCategories: ["performance", "accessibility", "best-practices", "seo"],
    });

    // Ensure directory exists
    if (!fs.existsSync("lighthouse-reports")) {
      fs.mkdirSync("lighthouse-reports");
    }

    // Save reports
    fs.writeFileSync("lighthouse-reports/report.html", lhr.report[1]);
    fs.writeFileSync(
      "lighthouse-reports/report.json",
      JSON.stringify(lhr, null, 2)
    );

    await browser.close();
  } catch (error) {
    console.error("Error:", error);
    process.exit(1);
  }
})();
