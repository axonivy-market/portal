const fs = require("fs");
const path = require("path");
const puppeteer = require("puppeteer");
const lighthouse = require("lighthouse");
const { URL } = require("url");
const { exec } = require("child_process");

// Read user credentials
const csvFilePath = path.join(
  __dirname,
  "AxonIvyPortal/portal-selenium-test/jmeter/data/users_local.csv"
);
const users = fs
  .readFileSync(csvFilePath, "utf-8")
  .split("\n")
  .map((line) => {
    const [username, password] = line.split(",");
    return { username, password };
  });

// Puppeteer script to navigate to the dashboard
(async () => {
  const browser = await puppeteer.launch({ headless: false });
  const page = await browser.newPage();

  // Use the first user for login
  const user = users[0];
  const loginUrl = "http://localhost:8080/Portal"; // Replace with actual login URL

  await page.goto(loginUrl);
  await page.type("#username", user.username);
  await page.type("#password", user.password);
  await page.click("#login-button"); // Replace with actual login button selector

  // Wait for navigation to dashboard
  await page.waitForNavigation();

  // Run Lighthouse audit
  const { lhr } = await lighthouse(page.url(), {
    port: new URL(browser.wsEndpoint()).port,
    output: "html",
    logLevel: "info",
  });

  // Save Lighthouse report
  const reportHtml = lhr.report;
  fs.writeFileSync("lighthouse-report.html", reportHtml);

  await browser.close();
})();
