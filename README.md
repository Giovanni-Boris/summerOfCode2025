# 🧠 Kotlin Source Structure Gradle Plugin

A prototype Gradle plugin designed to extract Kotlin source code structure information from a Gradle-based project. This plugin is part of a proposed solution for integrating Kotlin support into the **Build Server Protocol (BSP)**, as described in the GSoC 2025 idea:  
**"Build Server Protocol: Add Support for Kotlin"**.

---

## 📌 Purpose

The goal of this plugin is to serve as a proof of concept for how a Gradle plugin can expose Kotlin-specific project structure to be consumed by a BSP server, and ultimately by IDEs (JetBrains and others). It demonstrates how a build tool can expose semantic code structure in a standardized way, forming a critical part of Kotlin’s BSP integration roadmap.

---

## 🔧 What It Does

The plugin registers a custom Gradle task called `sourceStructure`, which:

- Scans the Kotlin source directory (`src/main/kotlin`)
- Parses each file using the Kotlin PSI (Program Structure Interface)
- Prints top-level structural elements:
  - `class`, `object`, `interface`
  - `fun`
  - `val` / `var`

This information is essential for tools and IDEs to provide intelligent features such as code navigation, outline views, and symbol indexing in BSP-enabled environments.

---

## ⚙️ Usage

### Apply the plugin

You can include this plugin in your `buildSrc` directory or publish it to a local Maven repository for testing.

### Apply Kotlin plugin

Ensure the Kotlin plugin is applied:

```kotlin
plugins {
    `kotlin-dsl`
    id("org.example.sumerOfCode-source-structure-plugin") version "1.0.0"
}
