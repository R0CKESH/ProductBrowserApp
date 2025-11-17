# Product Browser App (Kotlin Multiplatform)

This is a Kotlin Multiplatform Mobile (KMM) application built for the Revest Solutions technical assignment.

The application is a product catalog browser built using Kotlin Compose Multiplatform, targeting both Android and iOS from a single, shared codebase. It features a rich, category-based UI, search, filtering, and a modern, scalable architecture.

---

## 📋 Summary of Business Requirements

The app serves as a prototype for a mobile product catalog, allowing users to:

### ✔ View Products
Display a list of all products, grouped by category, showing the product name, price, and thumbnail.

### ✔ View Product Details
Tap a product to view detailed information, including its title, full description, brand, price, and rating.

### ✔ Search Products
Search for products by keyword using an API-based search.

### ✔ Filter Products
Filter the product list by selecting a specific category.

---

## 🏛️ Project Architecture Overview

The project follows Clean Architecture principles, strictly separating concerns into different layers within the `composeApp/commonMain` module.

---

## 🔑 Core Principles

### **Kotlin Multiplatform (KMP)**
All code (UI, business logic, data, and presentation) is written in Kotlin and shared 100% between Android and iOS.

### **Compose Multiplatform**
The entire UI is built with Compose Multiplatform, providing a single, shared, declarative UI layer for both platforms.

---

## 📂 Layers Overview

### **Data Layer (`data` package)**
- **Repository Pattern:** ProductRepository interface abstracts the data source.
- **Ktor Client:** For networking with dummyjson.com API.
- **kotlinx.serialization:** For JSON parsing.

### **Domain Layer (`domain` package)**
- **Use Cases:** All business logic (e.g., `GetProductsUseCase`, `SearchProductsUseCase`).
- Ensures clear separation between logic and UI.

### **Presentation Layer (`presentation` package)**
- **Decompose:** Navigation + lifecycle-aware components.
- **RootComponent:** Manages navigation stack.
- **ProductListComponent:** Manages home screen state.

### **Koin (Dependency Injection)**
Used to provide repositories, use cases, and components.  
Enhances testability and modularity.

---

## 📚 Key Libraries Used

- **Compose Multiplatform** – Shared UI
- **Ktor** – Networking
- **kotlinx.serialization** – JSON
- **Decompose** – Navigation & State
- **Koin** – Dependency Injection
- **Kamel** – Image loading
- **Compose Shimmer** – Loading animation

---

## 🛠️ How to Build & Run

### **Prerequisites**
- Android Studio (latest: Koala / Iguana)
- Kotlin Multiplatform Mobile Plugin
- Xcode (for iOS)

---

### **▶ Android**
1. Open project in Android Studio
2. Let Gradle sync
3. Select **composeApp** run configuration
4. Choose emulator or physical device
5. Run the app

---

### **🍏 iOS**
1. Install Xcode
2. Open the project in Android Studio
3. Choose **iosSimulatorArm64** target
4. Run the app

_Alternative:_  
Open `iosApp/iosApp.xcworkspace` in Xcode after first sync.

---

## 💡 Trade-offs & Assumptions

- **Dependency Injection:** Chose Koin for scalability though assignment allowed manual DI.
- **State Management:** Decompose for lifecycle safety and navigation.
- **UI Improvements:** Added category grouping for richer UX.
- **Image Loading:** Used Kamel for pure KMP support.
- **Loading UX:** Shimmer used instead of simple loaders.
- **Error Handling:** Basic network error UI in place.

---

<h2>📱 App Demo (9:16 Vertical Video)</h2>


<p align="center">
  <img src="screenshots/demo.gif" width="800"  alt="demo"/>
</p>







