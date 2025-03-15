# Test Automation Framework

This framework is designed to automate the testing of both the UI and API functionalities of a web application, following best practices and design patterns to ensure maintainability and scalability.

## 📋 Prerequisites

Before you get started, ensure you have the following installed:

- **Java** (JDK 11+ recommended)
- **Maven** (for dependency management)
- **Git** (to clone the repository)
- **Bash** (for running the provided script)
- **Allure** (for generating test reports)
- **Chrome/Firefox WebDriver** (depending on the browser you want to test)

## ⚙️ Installation

1. **Clone the Repository:**
   ```bash
   git clone https://github.com/MuhammadMamduh/foodics-task.git
   cd foodics-task

# Test Automation Framework

This framework is designed to automate the testing of both the UI and API functionalities of a web application, following best practices and design patterns to ensure maintainability and scalability.

## 🏗️ Key Features

- **UI and API Test Cases**: Comprehensive test cases covering both UI and API functionalities.
- **Single Responsibility Principle**: Applied in the `components` package to maintain clean and modular code.
- **Page Object Model (POM)**: Implemented to separate test logic from page structure, enhancing maintainability.
- **Composition Relationship**: Clear composition between `components` and `pages` for better organization and reusability.
- **Factory Design Pattern**: Used to abstract the creation and management of the WebDriver.
- **Test Execution with TestNG**: Utilized TestNG for running and organizing test cases.
- **Independent Test Suites**:
    - **UI Test Suite**: Runs all UI-based test cases.
    - **API Test Suite**: Runs all API-based test cases.

## ⚙️ Configuration

- **Properties File**: Stores the API base URL for easy configuration.
- **Log Management**: Integrated with `log4j2` to generate readable logs at:  

- **Test Reporting**: Generates clean and detailed test reports using **Allure Reports** at:  src/test/execution-results/test-report
- 

## 🚀 Running the Tests

A simple bash script is provided to act as the entry point for running the framework:
```bash
./starter.sh
