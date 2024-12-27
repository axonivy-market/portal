import fs from "fs";
import path from "path";
import puppeteer from "puppeteer";
import lighthouse from "lighthouse";
import { fileURLToPath } from "url";
import { dirname } from "path";

const __filename = fileURLToPath(import.meta.url);
const __dirname = dirname(__filename);

(async () => {
  try {
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

    const formData = {
      "javax.faces.partial.ajax": "true",
      "javax.faces.source": "login-form:login-command",
      "javax.faces.partial.execute": "@all",
      "javax.faces.partial.render": "login:login-form",
      "login:login-form:username": username, // Replace with actual username
      "login:login-form:password": password, // Replace with actual password
      "javax.faces.ViewState": "dynamic-view-state", // Fetch dynamically if required
      "login:login-form_SUBMIT": "1",
    };

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

    await page.evaluate((data) => {
      const form = document.createElement("form");
      form.method = "POST";
      form.action = location.href; // Current page URL

      Object.entries(data).forEach(([key, value]) => {
        const input = document.createElement("input");
        input.type = "hidden";
        input.name = key;
        input.value = value;
        form.appendChild(input);
      });

      document.body.appendChild(form);
      form.submit();
    }, formData);

    // Wait for navigation after login
    await page.waitForNavigation();

    // Run Lighthouse
    const { lhr } = await lighthouse(page.url(), {
      port: new URL(browser.wsEndpoint()).port,
      output: ["html", "json"],
      logLevel: "info",
      onlyCategories: ["performance", "accessibility", "best-practices", "seo"],
    });

    // Save reports
    fs.writeFileSync("lighthouse-report.html", lhr.report[0]);
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
