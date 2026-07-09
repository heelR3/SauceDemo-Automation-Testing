# SauceDemo Automation Testing Framework

## Project Overview

This project is an end-to-end web automation testing framework designed for the SauceDemo application using Selenium WebDriver, Java, Maven, TestNG, Cucumber BDD, and Page Object Model (POM).

The framework supports automated execution of Login, Inventory, Cart, Checkout, Navigation, and Logout functionalities with Allure/Extent/Cucumber reportings and screenshot capture.

-----------------------

## Tech Stack Used

- Java 17    --- For Programming Language
- Selenium WebDriver ---  For Browser Automation
- Maven --- Build Management Tool
- TestNG --- Test Execution Framework
- Cucumber BDD --- Behavior Driven Development
- Apache POI --- Excel Data Handling
- Extent Reports --- HTML Reporting
- Allure Reports --- Advance Reporting
- WebDriverManager --- Driver Management
- Git and GitHub --- Version Control

---------------------------

## Project Structure
```text
WebsiteTesting
│
├── src/test/java
│
│   ├── hooks
│   │     └── Hooks.java
│
│   ├── pages
│   │     ├── LoginPage.java
│   │     ├── InventoryPage.java
│   │     ├── CartPage.java
│   │     ├── CheckoutPage.java
│   │     ├── NavigationPage.java
│   │     └── LogoutPage.java
│
│   ├── runner
│   │     └── TestRunner.java
│
│   ├── stepdefinitions
│   │     ├── LoginSteps.java
│   │     ├── InventorySteps.java
│   │     ├── CartSteps.java
│   │     ├── CheckoutSteps.java
│   │     ├── NavigationSteps.java
│   │     └── LogoutSteps.java
│
│   └── utils
│         ├── DriverFactory.java
│         ├── ConfigReader.java
│         ├── WaitUtil.java
│         ├── ExcelReader.java
│         ├── ExcelWriter.java
│         └── ScreenshotUtil.java
│
├── src/test/resources
│
│   ├── features
│   ├── testdata
│   ├── config.properties
│   └── extent.properties
│
├── screenshots
├── allure-results
├── target
├── test-output
├── pom.xml
└── testNG.xml
```

----------------------------

## Implemented Modules

### Login Module

- Valid Login
- Invalid Login
- Locked Out User Validation
- Empty Username Validation
- Empty Password Validation


### Inventory Module

- Verify Inventory Page
- Verify Product Listing
- Product Details Validation
- Product Information Verification

### Cart Module

- Add Product to Cart
- Add Multiple Products
- Remove Product from Cart
- Verify Cart Contents
- Cart Navigation

### Checkout Module

- Successful Checkout
- Checkout Without First Name
- Checkout Without Last Name
- Checkout Without Postal Code
- Checkout Validation Messages

### Navigation Module

- Open Menu
- Navigate to All Items
- Navigate to About Page
- Reset Application State


### Logout Module

- Successful Logout
- Verify Login Page After Logout


---

## Framework Features

- Page Object Model Design Pattern
- Cucumber BDD Scenarios
- Data Driven Testing using Excel
- Screenshot Capture
- Explicit Wait Implementation
- Maven Build Management
- TestNG Execution
- Exception Handling
- GitHub Version Control

-------------------------

## Test Data Management

Test data is maintained in:

```text
src/test/resources/testdata/TestData.xlsx
```

Apache POI is used for reading and writing test data.

-----------------

## Reports Generated

### Cucumber Report

```text
target/cucumber-reports/cucumber.html
```

### Extent Report

```text
test-output/ExtentReport.html
```

### Allure Report

```text
target/site/allure-maven-plugin/index.html
```

### TestNG Report

```text
test-output/index.html
```

---

## Screenshots

Captured screenshots are stored in:

```text
screenshots/
```

-------------------------

## Execution Methods

### Run Using Test Runner

```text
Run As → TestNG Test
```

### Run Using TestNG XML

```text
Run As → TestNG Suite
```

### Run Using Maven

```bash
mvn clean test
```

---------------------------

## Maven Commands

Run Tests

```bash
mvn clean test
```

Generate Allure Report

```bash
mvn allure:report
```

Generate Project Build

```bash
mvn clean install
```

------------------------

## Reporting Summary

The framework generates:

- Cucumber HTML Reports
- Extent Reports
- Allure Reports
- TestNG Reports
- Execution Screenshots

------------------------

